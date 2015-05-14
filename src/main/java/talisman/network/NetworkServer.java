package talisman.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import essence.event.EventFeed;
import essence.inject.Inject;
import essence.packet.IncomingPacket;
import essence.packet.PacketController;
import essence.packet.PacketMessage;
import essence.packet.PacketMessageDecoder;
import essence.packet.PacketMessageEncoder;
import essence.packet.PacketNetworkServer;
import essence.task.TaskService;
import essence.task.Tasks;
import essence.util.logging.Logger;

final class NetworkServer extends PacketNetworkServer<NetworkClient> {

	private static final int DEFAULT_BUFFER_SIZE = 5000;

	private final Logger logger;
	private final TaskService tasks;
	private final EventFeed feed;

	@Inject
	NetworkServer(PacketController controller, PacketMessageEncoder encoder, PacketMessageDecoder decoder,
			Logger logger, TaskService tasks, EventFeed feed) {
		super(controller, encoder, decoder);
		this.logger = logger;
		this.tasks = tasks;
		this.feed = feed;
	}

	@Override
	public void start(int port) {
		tasks.submit(Tasks.immediate(() -> {
			try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {
				server.bind(new InetSocketAddress(port));
				tasks.submit(Tasks.continuous(1, () -> {
					for (NetworkClient client : getClients())
						handleRead(client);
				}));
				while (server.isOpen()) {
					try {
						AsynchronousSocketChannel channel = server.accept().get();
						if (channel.isOpen()) {
							NetworkClient client = new NetworkClient(getController(), channel);
							addClient(client);
							logger.debug("Accepted client from remote address " + channel.getRemoteAddress());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));
	}

	private void handleRead(NetworkClient client) {
		AsynchronousSocketChannel channel = client.getChannel();
		if (!channel.isOpen()) {
			removeClient(client);
			return;
		}
		ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
		channel.read(buffer, null, new CompletionHandler<Integer, Object>() {
			@Override
			public void completed(Integer result, Object attachment) {
				PacketMessage message = getDecoder().decode(buffer.array());
				logger.debug("Received packet " + message.getID());
				IncomingPacket packet = getController().encode(client, message);
				if (packet != null)
					feed.post(packet);
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				removeClient(client);
				exc.printStackTrace();
			}
		});
	}

}

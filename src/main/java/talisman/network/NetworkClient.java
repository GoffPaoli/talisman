package talisman.network;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

import essence.packet.PacketClient;
import essence.packet.PacketController;
import essence.packet.PacketMessage;

final class NetworkClient extends PacketClient {

	private final AsynchronousSocketChannel channel;

	NetworkClient(PacketController controller, AsynchronousSocketChannel channel) {
		super(controller);
		this.channel = channel;
	}

	@Override
	public void write(PacketMessage message) {
		channel.write(ByteBuffer.wrap(message.getData()));
	}

	AsynchronousSocketChannel getChannel() {
		return channel;
	}

}

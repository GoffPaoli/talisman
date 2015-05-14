package talisman.network;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import essence.packet.PacketNetworkServer;

final class NetworkClientHandler implements
		CompletionHandler<AsynchronousSocketChannel, PacketNetworkServer<NetworkClient>> {

	private NetworkClient client;

	@Override
	public void completed(AsynchronousSocketChannel result, PacketNetworkServer<NetworkClient> attachment) {
		client = new NetworkClient(attachment.getController(), result);
	}

	@Override
	public void failed(Throwable exc, PacketNetworkServer<NetworkClient> attachment) {
		if (client != null)
			attachment.removeClient(client);
	}

}

package talisman.network.netty;

import io.netty.channel.Channel;
import essence.packet.PacketClient;
import essence.packet.PacketController;
import essence.packet.PacketMessage;

final class NettyNetworkClient extends PacketClient {

	protected NettyNetworkClient(PacketController controller) {
		super(controller);
	}

	private Channel channel;

	@Override
	public void write(PacketMessage message) {
		channel.writeAndFlush(message);
	}

	Channel getChannel() {
		return channel;
	}

	void setChannel(Channel channel) {
		this.channel = channel;
	}

}

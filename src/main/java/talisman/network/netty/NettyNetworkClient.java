package talisman.network.netty;

import java.util.UUID;

import essence.network.AbstractNetworkClient;
import essence.packet.PacketMessage;

final class NettyNetworkClient extends AbstractNetworkClient<PacketMessage> {

	NettyNetworkClient(UUID uuid) {
		super(uuid);
	}

	@Override
	public void write(PacketMessage message) {
		
	}

}

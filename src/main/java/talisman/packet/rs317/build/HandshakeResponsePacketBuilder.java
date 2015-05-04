package talisman.packet.rs317.build;

import essence.packet.BuildsPacket;
import essence.packet.PacketBuilder;
import essence.packet.PacketMessage;
import essence.packet.PacketOutputStream;
import essence.packet.game.HandshakeResponsePacket;

@BuildsPacket(HandshakeResponsePacket.class)
public final class HandshakeResponsePacketBuilder implements PacketBuilder<HandshakeResponsePacket> {

	@Override
	public PacketMessage build(HandshakeResponsePacket packet) {
		PacketOutputStream out = PacketOutputStream.get();
		out.writeBytes(new byte[8]);
		out.write(packet.getResponse());
		out.writeLong(packet.getSessionKey());
		return PacketMessage.headless(out);
	}

}

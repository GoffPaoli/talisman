package talisman.packet.rs317.build;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.game.HandshakeResponsePacket;

@DecodesPacket(HandshakeResponsePacket.class)
public final class HandshakeResponsePacketBuilder implements PacketDecoder<HandshakeResponsePacket> {

	@Override
	public PacketMessage decode(HandshakeResponsePacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.writeBytes(new byte[8]);
		writer.write(packet.getResponse());
		writer.writeLong(packet.getSessionKey());
		return PacketMessage.headless(writer);
	}

}

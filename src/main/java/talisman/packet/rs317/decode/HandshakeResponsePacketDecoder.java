package talisman.packet.rs317.decode;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.lobby.HandshakeResponsePacket;

@DecodesPacket(HandshakeResponsePacket.class)
public final class HandshakeResponsePacketDecoder implements PacketDecoder<HandshakeResponsePacket> {

	@Override
	public PacketMessage decode(HandshakeResponsePacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.writeBytes(new byte[8]);
		writer.write(packet.getID());
		writer.writeLong(packet.getSessionKey());
		return PacketMessage.headless(writer);
	}

}

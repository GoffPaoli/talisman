package talisman.packet.rs317.parse;

import essence.packet.EncodesPacket;
import essence.packet.PacketEncoder;
import essence.packet.PacketMessage;
import essence.packet.PacketReader;
import essence.packet.game.HandshakePacket;

@EncodesPacket(14)
public final class HandshakePacketParser implements PacketEncoder<HandshakePacket> {

	@Override
	public HandshakePacket encode(PacketMessage message) {
		PacketReader reader = PacketReader.get(message);
		int nameHash = reader.readUnsignedByte();
		return HandshakePacket.get(nameHash);
	}

}

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
		PacketReader in = PacketReader.get(message);
		int nameHash = in.readUnsignedByte();
		return HandshakePacket.get(nameHash);
	}

}

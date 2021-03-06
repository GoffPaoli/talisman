package talisman.packet.rs317.encode;

import essence.packet.EncodesPacket;
import essence.packet.PacketClient;
import essence.packet.PacketEncoder;
import essence.packet.PacketMessage;
import essence.packet.PacketReader;
import essence.packet.incoming.HandshakePacket;

@EncodesPacket(14)
public final class HandshakePacketEncoder implements PacketEncoder<HandshakePacket> {

	@Override
	public HandshakePacket encode(PacketClient client, PacketMessage message) {
		PacketReader reader = PacketReader.get(message);
		int nameHash = reader.readUnsignedByte();
		return HandshakePacket.get(client, nameHash);
	}

}

package talisman.packet.rs317.parse;

import essence.packet.PacketInputStream;
import essence.packet.PacketMessage;
import essence.packet.PacketParser;
import essence.packet.ParsesPacket;
import essence.packet.game.HandshakePacket;

@ParsesPacket(14)
public final class HandshakePacketParser implements PacketParser<HandshakePacket> {

	@Override
	public HandshakePacket parse(PacketMessage message) {
		PacketInputStream in = PacketInputStream.get(message);
		int nameHash = in.readUnsignedByte();
		return HandshakePacket.get(nameHash);
	}

}

package talisman.packet.rs317.decode;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.outgoing.GameMessagePacket;

@DecodesPacket(GameMessagePacket.class)
public final class GameMessagePacketDecoder implements PacketDecoder<GameMessagePacket> {

	private static final int PACKET_ID = 253;

	@Override
	public PacketMessage decode(GameMessagePacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.writeString(packet.getMessage());
		return PacketMessage.get(writer, PACKET_ID);
	}

}

package talisman.packet.rs317.decode;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.outgoing.PlayerDetailsPacket;

@DecodesPacket(PlayerDetailsPacket.class)
public final class PlayerDetailsPacketDecoder implements PacketDecoder<PlayerDetailsPacket> {

	private static final int PACKET_ID = 249;

	@Override
	public PacketMessage decode(PlayerDetailsPacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.writeByteA(packet.isMember() ? 1 : 0);
		writer.writeLEShortA(packet.getPlayerIndex());
		return PacketMessage.get(writer, PACKET_ID);
	}

}

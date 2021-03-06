package talisman.packet.rs317.decode;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.outgoing.MapRegionPacket;

@DecodesPacket(MapRegionPacket.class)
public final class MapRegionPacketDecoder implements PacketDecoder<MapRegionPacket> {

	private static final int PACKET_ID = 73;

	@Override
	public PacketMessage decode(MapRegionPacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.writeShortA(packet.getRegionX() + 6);
		writer.writeShort(packet.getRegionY() + 6);
		return PacketMessage.get(writer, PACKET_ID);
	}

}

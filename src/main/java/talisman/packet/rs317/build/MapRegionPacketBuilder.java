package talisman.packet.rs317.build;

import essence.packet.BuildsPacket;
import essence.packet.PacketBuilder;
import essence.packet.PacketMessage;
import essence.packet.PacketOutputStream;
import essence.packet.game.MapRegionPacket;

@BuildsPacket(MapRegionPacket.class)
public final class MapRegionPacketBuilder implements PacketBuilder<MapRegionPacket> {

	private static final int PACKET_ID = 73;

	@Override
	public PacketMessage build(MapRegionPacket packet) {
		PacketOutputStream out = PacketOutputStream.get();
		out.writeShortA(packet.getRegionX() + 6);
		out.writeShort(packet.getRegionY() + 6);
		return PacketMessage.get(out, PACKET_ID);
	}

}

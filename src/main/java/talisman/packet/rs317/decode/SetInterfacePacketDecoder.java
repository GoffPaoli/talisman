package talisman.packet.rs317.decode;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.outgoing.SetInterfacePacket;

@DecodesPacket(SetInterfacePacket.class)
public final class SetInterfacePacketDecoder implements PacketDecoder<SetInterfacePacket> {

	private static final int PACKET_ID = 71;

	@Override
	public PacketMessage decode(SetInterfacePacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.writeShort(packet.getInterfaceID());
		writer.writeByteA(packet.getSidebarID());
		return PacketMessage.get(writer, PACKET_ID);
	}

}

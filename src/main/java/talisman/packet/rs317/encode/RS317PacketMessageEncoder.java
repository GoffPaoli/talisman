package talisman.packet.rs317.encode;

import essence.packet.PacketMessage;
import essence.packet.PacketMessageEncoder;
import essence.packet.PacketWriter;

public final class RS317PacketMessageEncoder implements PacketMessageEncoder {

	@Override
	public byte[] encode(PacketMessage message) {
		PacketWriter writer = PacketWriter.get(message.getData().length + 3);

		if (!message.isHeadless())
			writeHeader(message, writer);
		writer.writeBytes(message.getData());

		return writer.toByteArray();
	}

	public static final int PACKET_SIZE_BYTE = -1;
	public static final int PACKET_SIZE_SHORT = -2;

	private static void writeHeader(PacketMessage message, PacketWriter writer) {
		int id = message.getID();
		writer.write(id);

		int length = sizes[id];
		if (length == PACKET_SIZE_BYTE)
			writer.writeByte(message.getData().length);
		else if (length == PACKET_SIZE_SHORT)
			writer.writeShort(message.getData().length);
		else
			writer.writeByte(length);
	}

	private static final int[] sizes = { 0, 0, 0, 0, 6, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
			0, 0, 0, 0, 0, 0, 0, 0, -2, 4, 3, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 6, 0, 0, 9, 0, 0, -2, 0, 0, 0, 0, 0, 0, -2,
			1, 0, 0, 2, -2, 0, 0, 0, 0, 6, 3, 2, 4, 2, 4, 0, 0, 0, 4, 0, -2, 0, 0, 7, 2, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 2, 0, 1, 0, 2, 0, 0, -1, 4, 1, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 15, 0, 0, 0, 4, 4, 0, 0, 0, -2, 0, 0, 0, 0,
			0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 14, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 4, 0, 0, 0, 2, 0,
			6, 0, 0, 0, 0, 3, 0, 0, 5, 0, 10, 6, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 4,
			0, 0, 0, 0, 0, 3, 0, 2, 0, 0, 0, 0, 0, -2, 7, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 2, -2, 0, 0, 0, 0, 6, 0, 4, 3, 0, 0, 0, -1, 6, 0, 0 };

}

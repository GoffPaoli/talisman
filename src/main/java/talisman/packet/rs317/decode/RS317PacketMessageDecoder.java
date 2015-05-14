package talisman.packet.rs317.decode;

import essence.packet.PacketMessage;
import essence.packet.PacketMessageDecoder;
import essence.packet.PacketReader;

public final class RS317PacketMessageDecoder implements PacketMessageDecoder {

	@Override
	public PacketMessage decode(byte[] data) {
		PacketReader reader = PacketReader.get(data);

		int id = reader.readUnsignedByte();
		int length = getPacketLength(id, reader);

		if (reader.available() < length)
			return null;

		byte[] packetData = new byte[length];
		reader.read(packetData, 0, length);

		return PacketMessage.get(packetData, id);
	}

	private static final int PACKET_SIZE_BYTE = -1;
	private static final int PACKET_SIZE_SHORT = -2;

	private static int getPacketLength(int id, PacketReader reader) {
		if (id >= sizes.length)
			return Integer.MAX_VALUE;
		int possibleLength = sizes[id];
		if (possibleLength == PACKET_SIZE_BYTE)
			return reader.readUnsignedByte();
		if (possibleLength == PACKET_SIZE_SHORT)
			return reader.readShort();
		return possibleLength;
	}

	private static final int[] sizes = { 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 4, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 6, 0 };

}

package talisman.packet.rs317;

import essence.packet.PacketMessage;
import essence.packet.PacketMessageDecoder;
import essence.packet.PacketReader;

public final class RS317Decoder implements PacketMessageDecoder {

	@Override
	public PacketMessage decode(byte[] data) {
		PacketReader in = PacketReader.get(data);

		int id = in.readUnsignedByte();
		int length = in.available();
		byte[] packetData = new byte[length];
		in.read(packetData, 0, length);

		return PacketMessage.get(packetData, id);
	}

}

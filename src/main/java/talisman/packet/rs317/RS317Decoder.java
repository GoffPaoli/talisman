package talisman.packet.rs317;

import essence.packet.PacketInputStream;
import essence.packet.PacketMessage;
import essence.packet.PacketMessageDecoder;

public final class RS317Decoder implements PacketMessageDecoder {

	@Override
	public PacketMessage decode(byte[] data) {
		PacketInputStream in = PacketInputStream.get(data);

		int id = in.readUnsignedByte();
		int length = in.available();
		byte[] packetData = new byte[length];
		in.read(packetData, 0, length);

		return PacketMessage.get(packetData, id);
	}

}

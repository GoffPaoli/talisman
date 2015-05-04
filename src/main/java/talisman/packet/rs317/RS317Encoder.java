package talisman.packet.rs317;

import essence.packet.PacketMessage;
import essence.packet.PacketMessageEncoder;
import essence.packet.PacketWriter;

public final class RS317Encoder implements PacketMessageEncoder {

	@Override
	public byte[] encode(PacketMessage message) {
		PacketWriter writer = PacketWriter.get();

		if (!message.isHeadless())
			writer.writeByte(message.getID());
		byte[] data = message.getData();
		writer.writeByte(data.length);
		writer.writeBytes(data);

		return writer.toByteArray();
	}

}

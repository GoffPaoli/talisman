package talisman.packet.rs317;

import essence.packet.PacketMessage;
import essence.packet.PacketMessageEncoder;
import essence.packet.PacketOutputStream;

public final class RS317Encoder implements PacketMessageEncoder {

	@Override
	public byte[] encode(PacketMessage message) {
		PacketOutputStream out = PacketOutputStream.get();

		if (!message.isHeadless())
			out.writeByte(message.getID());
		byte[] data = message.getData();
		out.writeByte(data.length);
		out.writeBytes(data);

		return out.toByteArray();
	}

}

package talisman.packet.rs317.encode;

import essence.packet.EncodesPacket;
import essence.packet.PacketEncoder;
import essence.packet.PacketMessage;
import essence.packet.PacketReader;
import essence.packet.lobby.LoginPacket;

@EncodesPacket({ 16, 18 })
public final class LoginPacketEncoder implements PacketEncoder<LoginPacket> {

	@Override
	public LoginPacket encode(PacketMessage message) {
		PacketReader reader = PacketReader.get(message);

		int magic = reader.readUnsignedByte();
		int clientVersion = reader.readUnsignedShort();
		boolean highDetail = reader.readBoolean();

		int[] fileChecksums = new int[9];
		for (int i = 0; i < fileChecksums.length; i++)
			fileChecksums[i] = reader.readInteger();

		int blockLength = reader.readUnsignedByte();
		int blockID = reader.readUnsignedByte();

		int[] sessionKeys = new int[4];
		for (int i = 0; i < sessionKeys.length; i++)
			sessionKeys[i] = reader.readInteger();

		int uid = reader.readInteger();
		String username = reader.readString();
		String password = reader.readString();

		return LoginPacket.get(magic, clientVersion, highDetail, fileChecksums, blockLength, blockID, sessionKeys,
				uid, username, password);
	}

}

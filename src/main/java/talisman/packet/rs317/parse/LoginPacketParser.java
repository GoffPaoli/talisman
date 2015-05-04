package talisman.packet.rs317.parse;

import essence.packet.EncodesPacket;
import essence.packet.PacketEncoder;
import essence.packet.PacketMessage;
import essence.packet.PacketReader;
import essence.packet.game.LoginPacket;

@EncodesPacket({ 16, 18 })
public final class LoginPacketParser implements PacketEncoder<LoginPacket> {

	@Override
	public LoginPacket encode(PacketMessage message) {
		PacketReader in = PacketReader.get(message);

		int magic = in.readUnsignedByte();
		int clientVersion = in.readUnsignedShort();
		boolean highDetail = in.read() == 1;

		int[] fileChecksums = new int[9];
		for (int i = 0; i < fileChecksums.length; i++)
			fileChecksums[i] = in.readInteger();

		int blockLength = in.readUnsignedByte();
		int blockID = in.readUnsignedByte();

		int[] sessionKeys = new int[4];
		for (int i = 0; i < sessionKeys.length; i++)
			sessionKeys[i] = in.readInteger();

		int userID = in.readInteger();
		String username = in.readString();
		String password = in.readString();

		return LoginPacket.get(magic, clientVersion, highDetail, fileChecksums, blockLength, blockID, sessionKeys,
				userID, username, password);
	}

}

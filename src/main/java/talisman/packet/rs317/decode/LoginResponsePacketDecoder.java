package talisman.packet.rs317.decode;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.lobby.LoginResponsePacket;

@DecodesPacket(LoginResponsePacket.class)
public final class LoginResponsePacketDecoder implements PacketDecoder<LoginResponsePacket> {

	@Override
	public PacketMessage decode(LoginResponsePacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.write(packet.getID());
		writer.write(packet.getCrown());
		writer.writeBoolean(packet.isFlagged());
		return PacketMessage.headless(writer);
	}

}

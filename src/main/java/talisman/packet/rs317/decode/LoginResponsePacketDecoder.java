package talisman.packet.rs317.decode;

import essence.packet.DecodesPacket;
import essence.packet.PacketDecoder;
import essence.packet.PacketMessage;
import essence.packet.PacketWriter;
import essence.packet.game.LoginResponsePacket;

@DecodesPacket(LoginResponsePacket.class)
public final class LoginResponsePacketDecoder implements PacketDecoder<LoginResponsePacket> {

	@Override
	public PacketMessage decode(LoginResponsePacket packet) {
		PacketWriter writer = PacketWriter.get();
		writer.write(packet.getResponse());
		writer.write(packet.getUserStatus());
		writer.write(packet.isFlagged() ? 1 : 0);
		return PacketMessage.headless(writer);
	}

}

package talisman.packet.rs317.build;

import essence.packet.BuildsPacket;
import essence.packet.PacketBuilder;
import essence.packet.PacketMessage;
import essence.packet.PacketOutputStream;
import essence.packet.game.LoginResponsePacket;

@BuildsPacket(LoginResponsePacket.class)
public final class LoginResponsePacketBuilder implements PacketBuilder<LoginResponsePacket> {

	@Override
	public PacketMessage build(LoginResponsePacket packet) {
		PacketOutputStream out = PacketOutputStream.get();
		out.write(packet.getResponse());
		out.write(packet.getUserStatus());
		out.write(packet.isFlagged() ? 1 : 0);
		return PacketMessage.headless(out);
	}

}

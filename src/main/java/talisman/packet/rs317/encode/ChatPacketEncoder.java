package talisman.packet.rs317.encode;

import essence.packet.EncodesPacket;
import essence.packet.PacketClient;
import essence.packet.PacketEncoder;
import essence.packet.PacketMessage;
import essence.packet.incoming.ChatPacket;

@EncodesPacket(4)
public final class ChatPacketEncoder implements PacketEncoder<ChatPacket> {

	@Override
	public ChatPacket encode(PacketClient client, PacketMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

}

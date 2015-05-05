package talisman.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import essence.packet.PacketMessage;
import essence.packet.PacketMessageEncoder;

@Sharable
final class NettyMessageEncoder extends MessageToByteEncoder<PacketMessage> {

	private final PacketMessageEncoder encoder;

	NettyMessageEncoder(PacketMessageEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, PacketMessage msg, ByteBuf out) throws Exception {
		out.writeBytes(encoder.encode(msg));
	}

}

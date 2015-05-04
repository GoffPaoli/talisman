package talisman.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import essence.packet.PacketMessageDecoder;

final class NettyMessageDecoder extends ByteToMessageDecoder {

	private final PacketMessageDecoder decoder;

	NettyMessageDecoder(PacketMessageDecoder decoder) {
		this.decoder = decoder;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
		out.add(decoder.decode(in.array()));
	}

}

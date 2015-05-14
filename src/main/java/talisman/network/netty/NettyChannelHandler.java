package talisman.network.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import essence.event.EventFeed;
import essence.inject.Inject;
import essence.packet.PacketMessage;

final class NettyChannelHandler extends ChannelHandlerAdapter {

	private final EventFeed feed;
	private final NettyNetworkServer server;
	private final NettyNetworkClient client;

	@Inject
	NettyChannelHandler(EventFeed feed, NettyNetworkServer server, NettyNetworkClient client) {
		this.feed = feed;
		this.server = server;
		this.client = client;
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		client.setChannel(ctx.channel());
		server.addClient(client);
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		server.removeClient(client);
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg != null && msg instanceof PacketMessage)
			feed.post(msg);
		super.channelRead(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// We handle channel unregistered, no need to do it here
		if (!(cause instanceof IOException))
			cause.printStackTrace();
	}

}

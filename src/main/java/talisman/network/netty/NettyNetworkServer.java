package talisman.network.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import essence.game.World;
import essence.inject.Inject;
import essence.network.AbstractNetworkServer;
import essence.packet.PacketMessage;
import essence.packet.PacketMessageDecoder;
import essence.packet.PacketMessageEncoder;

final class NettyNetworkServer extends AbstractNetworkServer<PacketMessage> {

	private final ServerBootstrap bootstrap = new ServerBootstrap();
	
	private final PacketMessageEncoder encoder;
	private final PacketMessageDecoder decoder;

	@Inject
	NettyNetworkServer(PacketMessageEncoder encoder, PacketMessageDecoder decoder) {
		super(World.PLAYER_CAPACITY);
		this.encoder = encoder;
		this.decoder = decoder;
	}

	@Override
	public void start(int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		bootstrap.group(bossGroup, workerGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.ALLOW_HALF_CLOSURE, true);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) {
				ChannelPipeline p = ch.pipeline();
				p.addLast("encoder", new NettyMessageEncoder(encoder));
				p.addLast("decoder", new NettyMessageDecoder(decoder));
				p.addLast("handler", new NettyChannelHandler());
			}
		});
		bootstrap.bind(port);
	}

}

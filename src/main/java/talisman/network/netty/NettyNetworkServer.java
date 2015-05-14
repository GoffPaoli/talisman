package talisman.network.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import essence.inject.Inject;
import essence.inject.Injector;
import essence.packet.PacketController;
import essence.packet.PacketMessageDecoder;
import essence.packet.PacketMessageEncoder;
import essence.packet.PacketNetworkServer;

final class NettyNetworkServer extends PacketNetworkServer<NettyNetworkClient> {

	private final ServerBootstrap bootstrap = new ServerBootstrap();

	private final Injector injector;

	@Inject
	NettyNetworkServer(PacketController controller, PacketMessageEncoder encoder, PacketMessageDecoder decoder,
			Injector injector) {
		super(controller, encoder, decoder);
		this.injector = injector;
	}

	@Override
	public void start(int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		bootstrap.group(bossGroup, workerGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) {
				ChannelPipeline p = ch.pipeline();
				p.addLast("encoder", new NettyMessageEncoder(getEncoder()));
				p.addLast("decoder", new NettyMessageDecoder(getDecoder()));
				p.addLast("handler", injector.getInstance(NettyChannelHandler.class));
			}
		});
		bootstrap.bind(port);
	}

}

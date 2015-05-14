package talisman.network.netty;

import essence.inject.Scopes;
import essence.packet.PacketNetworkServer;
import essence.plugin.AbstractPlugin;
import essence.plugin.PluginManifest;

@PluginManifest(name = "Talisman Netty Networking")
public final class NettyNetworkPlugin extends AbstractPlugin {

	@Override
	public void configure() {
		bind(PacketNetworkServer.class).to(NettyNetworkServer.class).in(Scopes.SINGLETON);
		bind(NettyChannelHandler.class).toSelf();
		bind(NettyNetworkClient.class).toSelf();
	}

}

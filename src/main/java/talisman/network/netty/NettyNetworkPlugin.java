package talisman.network.netty;

import essence.inject.Scopes;
import essence.network.NetworkServer;
import essence.plugin.AbstractPlugin;
import essence.plugin.PluginManifest;

@PluginManifest(name = "Talisman Networking (Netty)")
public final class NettyNetworkPlugin extends AbstractPlugin {

	@Override
	public void configure() {
		bind(NetworkServer.class).to(NettyNetworkServer.class).in(Scopes.SINGLETON);
	}

}

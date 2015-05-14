package talisman.network;

import essence.inject.Scopes;
import essence.packet.PacketNetworkServer;
import essence.plugin.AbstractPlugin;
import essence.plugin.PluginManifest;

@PluginManifest(name = "Talisman Networking")
public final class NetworkPlugin extends AbstractPlugin {

	@Override
	public void configure() {
		bind(PacketNetworkServer.class).to(NetworkServer.class).in(Scopes.SINGLETON);
	}

}

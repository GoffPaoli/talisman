package talisman;

import talisman.network.netty.NettyNetworkPlugin;
import talisman.packet.rs317.RS317Plugin;
import essence.inject.Inject;
import essence.plugin.AbstractPlugin;
import essence.plugin.PluginManifest;
import essence.util.logging.Logger;

@PluginManifest(name = "Talisman", children = { NettyNetworkPlugin.class, RS317Plugin.class })
public final class TalismanPlugin extends AbstractPlugin {

	private final Logger logger;

	@Inject
	TalismanPlugin(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void activate() {
		logger.info("Talisman has been activated.");
	}

}

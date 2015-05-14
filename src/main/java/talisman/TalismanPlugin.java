package talisman;

import java.util.concurrent.ThreadLocalRandom;

import essence.event.EventFeed;
import essence.event.Subscribe;
import essence.inject.Inject;
import essence.packet.incoming.HandshakePacket;
import essence.packet.incoming.LoginPacket;
import essence.packet.outgoing.HandshakeResponsePacket;
import essence.packet.outgoing.LoginResponsePacket;
import essence.plugin.AbstractPlugin;
import essence.plugin.PluginManifest;
import essence.util.logging.Logger;

@PluginManifest(name = "Talisman Core")
public final class TalismanPlugin extends AbstractPlugin {

	private final Logger logger;
	private final EventFeed feed;

	@Inject
	TalismanPlugin(Logger logger, EventFeed feed) {
		this.logger = logger;
		this.feed = feed;
	}

	@Override
	public void activate() {
		feed.subscribe(this);
	}

	@Subscribe
	private void onHandshake(HandshakePacket packet) {
		packet.getClient().write(HandshakeResponsePacket.get(0, ThreadLocalRandom.current().nextLong()));
	}

	@Subscribe
	private void onLogin(LoginPacket packet) {
		logger.debug("Login request from " + packet.getUsername());
		packet.getClient().write(LoginResponsePacket.get(2, 2, false));
	}

}

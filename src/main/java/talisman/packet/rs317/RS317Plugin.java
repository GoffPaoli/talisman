package talisman.packet.rs317;

import essence.inject.Scopes;
import essence.packet.PacketMessageDecoder;
import essence.packet.PacketMessageEncoder;
import essence.plugin.AbstractPlugin;
import essence.plugin.PluginManifest;

@PluginManifest(name = "Talisman RS-317")
public final class RS317Plugin extends AbstractPlugin {

	@Override
	public void configure() {
		bind(PacketMessageEncoder.class).to(RS317Encoder.class).in(Scopes.SINGLETON);
		bind(PacketMessageDecoder.class).to(RS317Decoder.class).in(Scopes.SINGLETON);
	}

}

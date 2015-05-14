package talisman.packet.rs317;

import talisman.packet.rs317.decode.HandshakeResponsePacketDecoder;
import talisman.packet.rs317.decode.LoginResponsePacketDecoder;
import talisman.packet.rs317.decode.MapRegionPacketDecoder;
import talisman.packet.rs317.decode.PlayerDetailsPacketDecoder;
import talisman.packet.rs317.decode.RS317PacketMessageDecoder;
import talisman.packet.rs317.decode.SetInterfacePacketDecoder;
import talisman.packet.rs317.encode.HandshakePacketEncoder;
import talisman.packet.rs317.encode.LoginPacketEncoder;
import talisman.packet.rs317.encode.RS317PacketMessageEncoder;
import essence.inject.Inject;
import essence.inject.Scopes;
import essence.packet.PacketController;
import essence.packet.PacketMessageDecoder;
import essence.packet.PacketMessageEncoder;
import essence.plugin.AbstractPlugin;
import essence.plugin.PluginManifest;

@PluginManifest(name = "Talisman RS-317")
public final class RS317Plugin extends AbstractPlugin {

	private final PacketController controller;

	@Inject
	RS317Plugin(PacketController controller) {
		this.controller = controller;
	}

	@Override
	public void configure() {
		bind(PacketMessageEncoder.class).to(RS317PacketMessageEncoder.class).in(Scopes.SINGLETON);
		bind(PacketMessageDecoder.class).to(RS317PacketMessageDecoder.class).in(Scopes.SINGLETON);
	}

	@Override
	public void activate() {
		controller.addEncoder(new HandshakePacketEncoder());
		controller.addDecoder(new HandshakeResponsePacketDecoder());
		controller.addEncoder(new LoginPacketEncoder());
		controller.addDecoder(new LoginResponsePacketDecoder());
		controller.addDecoder(new MapRegionPacketDecoder());
		controller.addDecoder(new SetInterfacePacketDecoder());
		controller.addDecoder(new PlayerDetailsPacketDecoder());
	}

}

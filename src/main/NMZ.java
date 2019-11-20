package main;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.widgets.message.Message;

import main.nodes.AbsorptionNode;
import main.nodes.FlickHealNode;
import main.nodes.Node;
import main.nodes.OverloadNode;
import main.nodes.StopScriptNode;

@ScriptManifest(author = "Marcusarailus", name = "NMZ", version = 1.0, description = "does nmz things", category = Category.COMBAT)
public class NMZ extends AbstractScript {
	
	public static int absorptionHitpoints = 900;
	
	private final Node[] nodes = {
			new StopScriptNode(this), new FlickHealNode(this), new OverloadNode(this), new AbsorptionNode(this)};
	
	@Override
	public int onLoop(){
		for(final Node node : nodes){
			if(node.validate()){
				return node.execute();
			}
		}
		return Calculations.random(0, 23);
	}
	
	@Override
	public void onMessage(final Message message){
		if(message.getMessage().contains("hitpoints of damage")){
			absorptionHitpoints = Integer.parseInt(message.getMessage().split("have ")[1].split(" hitpoints")[0]);
		}
	}
}

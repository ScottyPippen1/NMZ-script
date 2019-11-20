package main.nodes;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.skills.Skill;

public final class StopScriptNode extends Node {

	public StopScriptNode(final MethodContext cm) {
		super(cm);
	}

	@Override
	public boolean validate() {
		//if NPC "Dominic Onion" exists or hitpoints are greater than 60
		return cm.getNpcs().closest("Dominic Onion") != null || cm.getNpcs().closest("Dominic") != null || cm.getSkills().getBoostedLevels(Skill.HITPOINTS) >= 60;
	}

	@Override
	public int execute() {
		//logs out
		cm.getTabs().logout();
		return Calculations.random(666, 1666);
	}

	@Override
	public String getName() {
		return "Stopping script";
	}
	
}

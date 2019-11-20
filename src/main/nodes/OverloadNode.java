package main.nodes;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.items.Item;

public final class OverloadNode extends Node {
	
	private Item overload;

	public OverloadNode(final MethodContext cm) {
		super(cm);
	}

	@Override
	public boolean validate() {
		//if hitpoints are greater than 50 and inventory contains overload potion
		return cm.getSkills().getBoostedLevels(Skill.HITPOINTS) > 50 && (overload = cm.getInventory().get(new Filter<Item>(){
			@Override
			public boolean match(final Item item){
				return item != null && item.getName() != null && item.getName().startsWith("Overload");
			}
		})) != null;
	}

	@Override
	public int execute() {
		//if inventory isn't open, open it
		if(!cm.getTabs().isOpen(Tab.INVENTORY)){
			cm.getTabs().open(Tab.INVENTORY);
			MethodProvider.sleep(434, 595);
		}
		//if overload potions is clicked, wait until hitpoints are less than 10
		if(overload.interact("Drink")){
			MethodProvider.sleepUntil(new Condition(){
				@Override
				public boolean verify(){
					return cm.getSkills().getBoostedLevels(Skill.HITPOINTS) < 10;
				}
			}, Calculations.random(7564, 9897));
		}
		return Calculations.random(434, 769);
	}

	@Override
	public String getName() {
		return "Sip overload";
	}

}

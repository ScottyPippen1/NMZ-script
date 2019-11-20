package main.nodes;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.items.Item;

import main.NMZ;

public final class AbsorptionNode extends Node {
	
	private final Filter<Item> absorbFilter = new Filter<Item>(){
		@Override
		public boolean match(final Item item){
			return item != null && item.getName() != null && item.getName().startsWith("Absorption");
		}
	};
	
	private int nextAbsorb = Calculations.random(400, 600);
	
	private Item absorb;
	
	public AbsorptionNode(final MethodContext cm) {
		super(cm);
	}

	@Override
	public boolean validate() {
		return NMZ.absorptionHitpoints <= nextAbsorb && (absorb = cm.getInventory().get(absorbFilter)) != null;
	}

	@Override
	public int execute() {
		//if inventory isn't open, open it
		if(!cm.getTabs().isOpen(Tab.INVENTORY)){
			cm.getTabs().open(Tab.INVENTORY);
			MethodProvider.sleep(434, 594);
		}
		//determines when to drink more absorption pots
		nextAbsorb = Calculations.random(400, 600);
		//determines how many sips to take of the absorption pot
		for(int i = 0; i < Calculations.random(8, 20); i++){
			//click absorption pot
			absorb.interact("Drink");
			if((absorb = cm.getInventory().get(absorbFilter)) == null || NMZ.absorptionHitpoints > 900){
				break;
			}
			MethodProvider.sleep(497, 793);
		}
		return Calculations.random(432, 831);
	}

	@Override
	public String getName() {
		return "Sip absorb";
	}

}

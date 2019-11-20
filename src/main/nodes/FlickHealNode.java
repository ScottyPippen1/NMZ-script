package main.nodes;

import java.awt.Rectangle;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;

public final class FlickHealNode extends Node{
	
	private long lastFlick = System.currentTimeMillis();
	
	private long flickDelay = Calculations.random(6666, 33333);
	
	public FlickHealNode(final MethodContext cm) {
		super(cm);
	}

	@Override
	public boolean validate() {
		return (lastFlick + flickDelay) <= System.currentTimeMillis();
	}

	@Override
	public int execute() {
		if(toggleQuickPrayerCustom(true)){
			if(toggleQuickPrayerCustom(false)){
				lastFlick = System.currentTimeMillis();
				flickDelay = Calculations.random(6666, 33333);
			}
		}
		return Calculations.random(443, 596);
	}

	@Override
	public String getName() {
		return "Flick quick pray";
	}
	
	public boolean toggleQuickPrayerCustom(boolean enable){
		//if quick prayer button is enabled click again to disable
		if(cm.getPrayer().isQuickPrayerActive() == enable){
			return true;
		}
		//click quick prayer button if its time to flick prayer
		return cm.getMouse().click(new Rectangle(525, 84, 40, 20));
	}

}

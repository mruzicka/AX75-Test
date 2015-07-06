/**
 * Created by michal.ruzicka on 4/4/14.
 */

import com.siemens.mp.game.Light;

public class PlatformUtilsSiemens extends PlatformUtilsDefault {
	public void setLightOn(boolean on) {
		if (on)
			Light.setLightOn();
		else
			Light.setLightOff();
	}

	public int getSoftKeyCode(int softKey) {
		switch (softKey) {
			case SOFT_LEFT:
				return -1;
			case SOFT_RIGHT:
				return -4;
			case SOFT_START:
				return -11;
			case SOFT_STOP:
				return -12;
			default:
				return 0;
		}
	}
}
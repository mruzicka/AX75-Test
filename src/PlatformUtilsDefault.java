/**
 * Created by michal.ruzicka on 4/4/14.
 */
public class PlatformUtilsDefault extends PlatformUtils {
	public void setLightOn(boolean on) {
	}

	public int getSoftKeyCode(int softKey) {
		switch (softKey) {
			case SOFT_LEFT:
				return -6;
			case SOFT_RIGHT:
				return -7;
			case SOFT_START:
				return -10;
			case SOFT_STOP:
				return -11;
			default:
				return 0;
		}
	}
}

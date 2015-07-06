/**
 * Created by michal.ruzicka on 4/4/14.
 */

public abstract class PlatformUtils {
	public static final int SOFT_LEFT = 1;
	public static final int SOFT_RIGHT = 2;
	public static final int SOFT_START = 3;
	public static final int SOFT_STOP = 4;

	public static PlatformUtils getPlatformUtils() {
		try {
			Class.forName("com.siemens.mp.io.File");
			return (PlatformUtils) Class.forName("PlatformUtilsSiemens").newInstance();
		} catch (Throwable t) {
			return new PlatformUtilsDefault();
		}
	}

	public abstract void setLightOn(boolean on);

	public abstract int getSoftKeyCode(int softKey);
}

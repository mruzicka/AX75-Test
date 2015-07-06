/**
 * Created by michal.ruzicka on 4/4/14.
 */
public class Utils {
	public static boolean safeEquals(Object l, Object r) {
		return l == null
				? r == null
				: l.equals(r);
	}
}

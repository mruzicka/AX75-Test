/**
 * Created by michal.ruzicka on 4/2/14.
 */

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class AX75Test extends MIDlet {
	private static final String[] CLDC_MIDPPROPS = {
			"microedition.profiles", "microedition.configuration", "microedition.locale", "microedition.platform",
			"microedition.encoding", "microedition.commports", "microedition.hostname"
	};
	private static final int[] RAINBOW_COLORS = {
			0xff0000, 0xff7f00, 0xffff00, 0x7fff00,
			0x00ff00, 0x00ff7f, 0x00ffff, 0x007fff,
			0x0000ff, 0x7f00ff, 0xff00ff, 0xff007f
	};

	private PlatformUtils platformUtils = PlatformUtils.getPlatformUtils();

	private Display display; // Reference to Display object for this MIDlet
	private CircleCanvas canvas; // Canvas to display a drawing

	private int propertyIndex;
	private int colorIndex;

	// MIDlet constructor
	public AX75Test() {
		display = Display.getDisplay(this);
		canvas = new CircleCanvas();

		canvas.setCommandListener(new CommandListener() {
			public void commandAction(Command command, Displayable displayable) {
				if (command instanceof ExecutableCommand)
					((ExecutableCommand) command).execute(displayable);
			}
		});

		canvas.addKeyCommand(platformUtils.getSoftKeyCode(PlatformUtils.SOFT_RIGHT), new ExecutableCommand() {
			public void execute(Displayable displayable) {
				destroyApp(false);
				notifyDestroyed();
			}
		});

		canvas.addGameCommand(Canvas.UP, new ExecutableCommand() {
			public void execute(Displayable displayable) {
				if (--propertyIndex < 0)
					propertyIndex += CLDC_MIDPPROPS.length;
				displayProperty();
			}
		});
		canvas.addGameCommand(Canvas.DOWN, new ExecutableCommand() {
			public void execute(Displayable displayable) {
				if (++propertyIndex >= CLDC_MIDPPROPS.length)
					propertyIndex -= CLDC_MIDPPROPS.length;
				displayProperty();
			}
		});

		canvas.addGameCommand(Canvas.LEFT, new ExecutableCommand() {
			public void execute(Displayable displayable) {
				if (--colorIndex < 0)
					colorIndex += RAINBOW_COLORS.length;
				canvas.setColor(RAINBOW_COLORS[colorIndex]);
			}
		});
		canvas.addGameCommand(Canvas.RIGHT, new ExecutableCommand() {
			public void execute(Displayable displayable) {
				if (++colorIndex >= RAINBOW_COLORS.length)
					colorIndex -= RAINBOW_COLORS.length;
				canvas.setColor(RAINBOW_COLORS[colorIndex]);
			}
		});

		canvas.setColor(RAINBOW_COLORS[colorIndex]);
		displayProperty();
	}

	protected void displayProperty() {
		String propertyName = CLDC_MIDPPROPS[propertyIndex];
		canvas.setText(propertyName + '\n' + System.getProperty(propertyName));
	}

	// Called by application manager to start the MIDlet.
	public void startApp() {
		display.setCurrent(canvas);
		platformUtils.setLightOn(true);
	}

	// A required method
	public void pauseApp() {
		platformUtils.setLightOn(false);
	}

	// A required method
	public void destroyApp(boolean unconditional) {
		platformUtils.setLightOn(false);
	}
}
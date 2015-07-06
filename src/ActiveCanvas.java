/**
 * Created by michal.ruzicka on 4/2/14.
 */

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import java.util.Hashtable;

public abstract class ActiveCanvas extends Canvas {
	protected Hashtable keyCommands = new Hashtable();
	protected Hashtable gameCommands = new Hashtable();
	protected CommandListener commandListener;

	public Command addKeyCommand(int keyCode, Command command) {
		return (Command) keyCommands.put(new Integer(keyCode), command);
	}

	public Command removeKeyCommand(int keyCode) {
		return (Command) keyCommands.remove(new Integer(keyCode));
	}

	public Command addGameCommand(int gameAction, Command command) {
		return (Command) gameCommands.put(new Integer(gameAction), command);
	}

	public Command removeGameCommand(int gameAction) {
		return (Command) gameCommands.remove(new Integer(gameAction));
	}

	protected void keyPressed(int keyCode) {
		if (commandListener != null) {
			int gameAction = getGameAction(keyCode);
			if (gameAction != 0 && !gameCommands.isEmpty()) {
				Command command = (Command) gameCommands.get(new Integer(gameAction));

				if (command != null) {
					commandListener.commandAction(command, this);
					return;
				}
			}
			if (!keyCommands.isEmpty()) {
				Command command = (Command) keyCommands.get(new Integer(keyCode));

				if (command != null)
					commandListener.commandAction(command, this);
			}
		}
	}

	public void setCommandListener(CommandListener listener) {
		super.setCommandListener(listener);
		commandListener = listener;
	}
}
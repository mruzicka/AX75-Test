/**
 * Created by michal.ruzicka on 4/4/14.
 */

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;

public abstract class ExecutableCommand extends Command {
	public ExecutableCommand() {
		super("", ITEM, 1);
	}

	public ExecutableCommand(String label, int commandType, int priority) {
		super(label, commandType, priority);
	}

	abstract public void execute(Displayable displayable);
}

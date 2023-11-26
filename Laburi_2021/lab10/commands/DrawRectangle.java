package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class DrawRectangle implements DrawCommand {
	private DiagramCanvas diagram;

	DiagramComponent component = new DiagramComponent();

	public DrawRectangle(DiagramCanvas diagram) {
		this.diagram = diagram;
	}

	@Override
	public void execute() {
		diagram.addComponent(component);
	}

	@Override
	public void undo() {
		diagram.removeComponent(component);
	}

	@Override
	public String toString() {
		return "DrawRectangle{" +
				"diagram=" + diagram +
				'}';
	}
}

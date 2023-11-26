package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class ChangeColor implements DrawCommand {
	private final DiagramCanvas diagram;
	private DiagramComponent component;
	private String newColor;
	private String previousColor;
	private int id;

	public ChangeColor(DiagramCanvas diagram, int id, String newColor) {
		this.diagram = diagram;
		this.id = id;
		this.component = diagram.getComponent(id);
		this.newColor = newColor;
	}

	@Override
	public void execute() {
		previousColor = component.getColor();
		component.setColor(newColor);
	}

	@Override
	public void undo() {
		newColor = previousColor;
		previousColor = component.getColor();
		component.setColor(newColor);
	}

	@Override
	public String toString() {
		return "ChangeColor{" +
				"diagramCanvas=" + diagram +
				", newColor='" + newColor + '\'' +
				", previousColor='" + previousColor + '\'' +
				", id=" + id +
				'}';
	}
}

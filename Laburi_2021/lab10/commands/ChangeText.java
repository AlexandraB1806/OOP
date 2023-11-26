package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class ChangeText implements DrawCommand {
	private final DiagramCanvas diagram;
	private DiagramComponent component;
	private String newText;
	private String previousText;
	private int id;

	public ChangeText(DiagramCanvas diagram, int id, String newText) {
		this.diagram = diagram;
		this.id = id;
		this.component = diagram.getComponent(id);
		this.newText = newText;
	}

	@Override
	public void execute() {
		previousText = component.getText();
		component.setText(newText);
	}

	@Override
	public void undo() {
		newText = previousText;
		previousText = component.getText();
		component.setText(newText);
	}

	@Override
	public String toString() {
		return "ChangeText{" +
				"diagramCanvas=" + diagram +
				", newText='" + newText + '\'' +
				", previousText='" + previousText + '\'' +
				", id=" + id +
				'}';
	}
}

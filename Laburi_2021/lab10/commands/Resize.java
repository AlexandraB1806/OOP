package lab10.commands;

import lab10.diagram.DiagramCanvas;
import lab10.diagram.DiagramComponent;

public class Resize implements DrawCommand {
	private DiagramCanvas diagram;
	private DiagramComponent component;
	private int id;
	private int percentage;
	private int previousWidth;
	private int previousHeight;
	private int newWidth;
	private int newHeight;

	public Resize(DiagramCanvas diagram, int id, int percentage) {
		this.diagram = diagram;
		this.id = id;
		this.component = diagram.getComponent(id);
		this.percentage = percentage;
	}

	@Override
	public void execute() {
		previousWidth = component.getWidth();
		newWidth = (percentage * previousWidth) / 100;
		component.setWidth(newWidth);

		previousHeight = component.getHeight();
		newHeight = (percentage * previousHeight) / 100;
		component.setHeight(newHeight);
	}

	@Override
	public void undo() {
		newWidth = previousWidth;
		previousWidth = component.getWidth();
		component.setWidth(newWidth);

		newHeight = previousHeight;
		previousHeight = component.getHeight();
		component.setHeight(newHeight);
	}

	@Override
	public String toString() {
		return "Resize{" +
				"diagram=" + diagram +
				", component=" + component +
				", id=" + id +
				", percentage=" + percentage +
				", previousWidth=" + previousWidth +
				", previousHeight=" + previousHeight +
				", newWidth=" + newWidth +
				", newHeight=" + newHeight +
				'}';
	}
}

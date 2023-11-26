package lab10.commands;

import lab10.diagram.DiagramCanvas;

public class ConnectComponents implements DrawCommand {
	private DiagramCanvas diagram;
	private int id1, id2;

	public ConnectComponents(DiagramCanvas diagram, int id1, int id2) {
		this.diagram = diagram;
		this.id1 = id1;
		this.id2 = id2;
	}

	@Override
	public void execute() {
		diagram.getComponent(id1).connectTo(String.valueOf(id2));
		diagram.getComponent(id2).connectTo(String.valueOf(id1));
	}

	@Override
	public void undo() {
		diagram.getComponent(id1).removeConnection(String.valueOf(id2));
		diagram.getComponent(id2).removeConnection(String.valueOf(id1));
	}

	@Override
	public String toString() {
		return "ConnectComponents{" +
				"diagram=" + diagram +
				", id1=" + id1 +
				", id2=" + id2 +
				'}';
	}
}

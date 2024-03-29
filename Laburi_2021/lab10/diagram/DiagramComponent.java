package lab10.diagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a diagram object, currently just a rectangle with text and color that can be connected to other components.
 */
public class DiagramComponent {
    private String text = "text";
    private String color = "WHITE";
    private int height = 40;
    private int width = 100;

    @Override
    public String toString() {
        return "[" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", connectedComponents=" + connectedComponents +
                ']';
    }

    private List<String> connectedComponents = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void connectTo(String componentId) {
        connectedComponents.add(componentId);
    }

    public void removeConnection(String componentId) {
        connectedComponents.remove(componentId);
    }
}

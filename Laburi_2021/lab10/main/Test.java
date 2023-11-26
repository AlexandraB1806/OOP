package lab10.main;

public class Test {
    public static void main(String[] args) {
        Client client = new Client();

        // Execute various tests

        testDraw(client);
        System.out.println(new String(new char[80]).replace("\0", "-"));

        testTextAndColor(client);
        System.out.println(new String(new char[80]).replace("\0", "-"));

        testResize(client);
        System.out.println(new String(new char[80]).replace("\0", "-"));

        testConnect(client);
        System.out.println(new String(new char[80]).replace("\0", "-"));

        testAllCommands(client);
        System.out.println(new String(new char[80]).replace("\0", "-"));

        testSimpleUndoRedo(client);
        System.out.println(new String(new char[80]).replace("\0", "-"));

        testComplexUndoRedo(client);
        System.out.println(new String(new char[80]).replace("\0", "-"));
    }

    private static void testDraw(Client client) {
        client.newDiagram();

        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");

        client.showDiagram();
    }

    private static void testTextAndColor(Client client) {
        testDraw(client);

        client.executeAction("change color", "0", "RED");
        client.executeAction("change color", "1", "BLUE");
        client.executeAction("draw rectangle");
        client.executeAction("change text", "0", "MyClass1");
        client.executeAction("change text", "5", "MyClass2");

        client.showDiagram();
    }

    private static void testConnect(Client client) {
        client.executeAction("connect", "0", "1");
        client.executeAction("connect", "1", "2");
        client.executeAction("connect", "2", "3");
        client.executeAction("connect", "4", "3");

        client.showDiagram();
    }

    private static void testResize(Client client) {
        client.executeAction("resize", "0", "50");
        client.executeAction("resize", "2", "20");
        client.executeAction("resize", "4", "70");

        client.showDiagram();
    }

    private static void testAllCommands(Client client) {
        testDraw(client);
        testTextAndColor(client);
        testConnect(client);
        testResize(client);
    }

    private static void testSimpleUndoRedo(Client client) {
        client.newDiagram();

        client.executeAction("draw rectangle");
        client.executeAction("change color", "0", "ORANGE");
        client.showDiagram();

        client.executeAction("draw rectangle");
        client.showDiagram();

        client.undo();
        client.showDiagram();

        client.redo();
        client.showDiagram();
    }

    private static void testComplexUndoRedo(Client client) {
        client.newDiagram();

        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("change color", "2", "PINK");
        client.executeAction("change text", "1", "MyText");
        client.executeAction("resize", "0", "30");
        client.showDiagram();

        client.executeAction("draw rectangle");
        client.executeAction("connect", "0", "1");
        client.executeAction("connect", "0", "2");
        client.executeAction("connect", "3", "2");
        client.showDiagram();

        client.executeAction("draw rectangle");
        // Does not draw a new rectangle
        client.undo();
        client.showDiagram();
        // Draws a new rectangle
        client.redo();
        client.showDiagram();
        // Cancel the drawing of a new rectangle
        client.undo();
        // Cancel the 3 connections done before
        client.undo();
        client.undo();
        client.undo();
        client.showDiagram();
        // Reconnect 0-1
        client.redo();
        client.showDiagram();
        // Reconnect 0-2
        client.redo();
        client.showDiagram();

        client.executeAction("change color", "0", "PURPLE");
        client.executeAction("change text", "3", "MySecondText");
        client.showDiagram();
        // Cancel writing "MySecondText"
        client.undo();
        client.showDiagram();
        // Cancel the PURPLE colour
        client.undo();
        client.showDiagram();
    }
}

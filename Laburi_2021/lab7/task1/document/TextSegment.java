package lab7.task1.document;

/**
 * Represents a text segment of a document that needs to be parsed.
 */
public abstract class TextSegment {
    private String content;

    TextSegment(String content) {
        this.content = content;
    }

    public TextSegment() { }

    public String getContent() {
        return content;
    }

    // Method for applying the visitor
    public abstract void accept(DocumentVisitor documentVisitor);

    // Nefolosirea patternului Visitor
//    public void accept(DocumentVisitor documentVisitor) {
//       documentVisitor.visit(this);
//    }
}

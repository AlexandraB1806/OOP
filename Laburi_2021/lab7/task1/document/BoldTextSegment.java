package lab7.task1.document;

public class BoldTextSegment extends TextSegment{

	public BoldTextSegment(String content) {
		super(content);
	}

	@Override
	public void accept(DocumentVisitor documentVisitor) {
		documentVisitor.visit(this);
	}

	// Daca nu se foloseste patternul Visitor, metoda accept
	// este deja implementata in TextSegment, deci nu va
	// mai fi implementata si aici
}

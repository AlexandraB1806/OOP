package lab7.task1.document;

public interface DocumentVisitor {
	// Folosirea patternului Visitor.
	void visit (ItalicTextSegment italicText);
	void visit (BoldTextSegment boldText);
	void visit (UrlSegment urlText);
	void visit (PlainTextSegment plainText);

	// Nefolosirea patternului Visitor.
	// Cum DocumentVisitor este o interfata, aceasta metoda va fi realizata
	// in clasele ce implementeaza interfata: DokuWikiVisitor si MarkdownVisitor.
	// void visit(TextSegment text);
}

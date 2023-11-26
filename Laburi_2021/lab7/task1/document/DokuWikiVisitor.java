package lab7.task1.document;

public class DokuWikiVisitor implements DocumentVisitor{
	private final StringBuilder string = new StringBuilder();

	public StringBuilder getString() {
		return string;
	}

	@Override
	public void visit(ItalicTextSegment italicText) {
		string.append("//");
		string.append(italicText.getContent());
		string.append("//");
	}

	@Override
	public void visit(BoldTextSegment boldText) {
		string.append("**");
		string.append(boldText.getContent());
		string.append("**");
	}

	@Override
	public void visit(UrlSegment urlText) {
		string.append("[[");
		string.append(urlText.getUrl());
		string.append("|");
		string.append(urlText.getDescription());
		string.append("]]");
	}

	@Override
	public void visit(PlainTextSegment plainText) {
		string.append(plainText.getContent());
	}

	// Nefolosirea patternului Visitor
//	public void visit(TextSegment txt) {
//		if (txt instanceof BoldTextSegment) {
//			visit((BoldTextSegment) txt);
//		} else if (txt instanceof ItalicTextSegment) {
//			visit((ItalicTextSegment) txt);
//		} else if (txt instanceof UrlSegment){
//			visit((UrlSegment) txt);
//		} else {
//			visit((PlainTextSegment) txt);
//		}
//	}
}

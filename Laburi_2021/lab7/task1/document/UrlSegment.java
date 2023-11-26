package lab7.task1.document;

public class UrlSegment extends TextSegment{
	private final String url;
	private final String description;

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public UrlSegment(String url, String description) {
		this.url = url;
		this.description = description;
	}

	@Override
	public void accept(DocumentVisitor documentVisitor) {
		documentVisitor.visit(this);
	}

	// Daca nu se foloseste patternul Visitor, metoda accept
	// este deja implementata in TextSegment, deci nu va
	// mai fi implementata si aici
}

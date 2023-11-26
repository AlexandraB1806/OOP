package lab7.task1;

import lab7.task1.document.DokuWikiVisitor;
import lab7.task1.document.MarkdownVisitor;
import lab7.task1.document.TextSegment;

import java.util.List;

/**
 * Uses visitors to parse documents and provide dokuwiki and markdown outputs.
 */
public class WikiGenerator {

    private final List<TextSegment> textSegments;

    public WikiGenerator(List<TextSegment> textSegments) {
        this.textSegments = textSegments;
    }

    public StringBuilder getDokuWikiDocument() {
        // Apply dokuwiki visitor on the text segments
        DokuWikiVisitor dokuWikiVisitor = new DokuWikiVisitor();
        for (TextSegment segment : textSegments) {
            segment.accept(dokuWikiVisitor);
        }
        return dokuWikiVisitor.getString();
    }

    public StringBuilder getMarkdownDocument() {
        // Apply Markdown visitor on the text segments
        MarkdownVisitor markdownVisitor = new MarkdownVisitor();
        for (TextSegment segment : textSegments) {
            segment.accept(markdownVisitor);
        }
        return markdownVisitor.getString();
    }
}

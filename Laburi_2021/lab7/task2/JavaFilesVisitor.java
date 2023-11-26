package lab7.task2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Counter for java files of a given directory using java.nio API
 */
public class JavaFilesVisitor extends SimpleFileVisitor<Path> {

    private ArrayList<Path> javaFiles;

    public final ArrayList<Path> getJavaFiles() {
        return javaFiles;
    }

    /**
     * Override the visitFile(Path file, BasicFileAttributes attr) method of the SimpleFileVisitor.
     * Add to javaFiles all the Java related files: the ones with .java and .class extensions.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile() && (file.toString().endsWith(".java") || file.toString().endsWith(".class"))) {
            if (javaFiles == null) {
                javaFiles = new ArrayList<>();
            }
            javaFiles.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) {

        Path startingDir = Paths.get("./src/lab7");
        JavaFilesVisitor filesVisitor = new JavaFilesVisitor();

        /*
         * The walkFileTree methods does a depth-first traversal of a directory, starting from startingDir.
         * When it reaches a file, the visitFile method is invoked on the currently visited file.
         */
        try {
            Files.walkFileTree(startingDir, filesVisitor);
            ArrayList<Path> javaFiles = filesVisitor.getJavaFiles();

            // Print the number of files visited and their names
            if (javaFiles == null) {
                System.out.println("Couldn't find the file!");
            }
            System.out.println(javaFiles.size());
            for (Path path : javaFiles) {
                System.out.println(path.toString() + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

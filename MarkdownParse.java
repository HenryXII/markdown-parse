// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {
    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        int lastClosedParen = markdown.lastIndexOf(")");
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);

            boolean isImage = false;

            if (markdown.indexOf("!", nextOpenBracket-1) == nextOpenBracket - 1) isImage = true;
            

            if (nextOpenBracket == 0) isImage = false;

            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", currentIndex);
            int closeParen = markdown.indexOf(")", openParen);

            if(nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1) {
		        break;
	        }

            if (openParen - nextCloseBracket > 2) {
                currentIndex = markdown.indexOf("[", currentIndex + 1);

                if (currentIndex == lastClosedParen) {
                    break;
                } else if (currentIndex < 0) {
                    break;
                }
                continue;
            }

            if (!isImage) toReturn.add(markdown.substring(openParen + 1, closeParen));
            if (closeParen > currentIndex){
                currentIndex = closeParen + 1;
            }else {
                break;
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        File file=new File(args[0]);
        Map<String, List<String>> links = getLinks(file);
        System.out.println(links.values());
    }
}

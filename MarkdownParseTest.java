import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    
    @Test
    public void testGetLinks1(){
        String fileName="test-file.md";
        List<String> expected=List.of("https://something.com","some-page.html");
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    @Test
    public void testGetLinks2(){
        String fileName="test-file2.md";
        List<String> expected=List.of("https://github.com");
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
    @Test
    public void testGetLinks3(){
        String fileName="test-file3.md";
        List<String> expected=List.of();
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
    @Test
    public void testGetLinks4(){
        String fileName="test-file4.md";
        List<String> expected=List.of("https://github2.com");
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
    @Test
    public void testGetLinks5(){
        String fileName="test-file5.md";
        List<String> expected=List.of("https://github2.com");
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
    @Test
    public void testlab1(){
        String fileName="test-lab4-1.md";
        List<String> expected=List.of("`google.com","google.com","ucsd.edu");
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
    @Test
    public void testlab2(){
        String fileName="test-lab4-2.md";
        List<String> expected=List.of("a.com", "a.com(())", "example.com");
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
    @Test
    public void testlab3(){
        String fileName="test-lab4-3.md";
        List<String> expected=List.of("https://www.twitter.com", "https://ucsd-cse15l-w22.github.io/", "https://cse.ucsd.edu/");
        try {
            assertEquals(expected,MarkdownParse.getLinks(Files.readString(Path.of(fileName))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }

}
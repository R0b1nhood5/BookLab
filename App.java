import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.net.*;

public class App {

    // Extract information from the URL
    // Convert to PigLatin
    public static String readURL(String requestURL) {
        // Instantiating the URL class
        String result = "";
        try {
            URL url = new URL(requestURL);
            // Retrieving the contents of the specified page
            Scanner sc = new Scanner(url.openStream(), StandardCharsets.UTF_8.toString());
            sc.useDelimiter("\\A"); // reads all the input from url in a single stroke. 
            // Instantiating the StringBuffer class to hold the result
            StringBuffer sb = new StringBuffer();
            while (sc.hasNext()) {
                sb.append(sc.next());
                // System.out.println(sc.next());
            }
            // Retrieving the String from the String Buffer object
            result = sb.toString();
            sc.close();

            // Process the book
            String line = "";
            String translatedLine = "";
            StringBuffer sp = new StringBuffer(); // translated book.
            int wordCount = 0;
            Book book = new Book();

            // HTML tags can be removed with result.replaceAll("<[^>]*>", "");
            Scanner scan = new Scanner(result);
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                wordCount += book.getWordCount(line);
                translatedLine = book.translateSentence(line);
                sp.append(translatedLine);
            }
            scan.close();

            // Write the PigLatin book.
            String pigLatinBook = sp.toString();
            FileWriter fw = new FileWriter("pigLatin.txt");
            System.out.println("Writing PigLatin book to output (size: " + pigLatinBook.length() + " , word count: "
                    + wordCount + ")");
            fw.write(pigLatinBook);
            fw.close();

        } catch (MalformedURLException me) {
            System.out.println("MalformedURLException: " + me);
            me.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe);
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String book = "";
        Book aBook = new Book();
        System.out.println(aBook.pigLatin("hello"));
        System.out.println(aBook.translateWord(""));
        try {
            System.out.println("Book Title: " + "Alice in the wonderland.");
            // Read the URL
            book = readURL("https://www.gutenberg.org/cache/epub/11/pg11.txt");
            // System.out.println("Book Length: " + book.length());
            // System.out.println("Book Excerpt: " + book.substring(0, 4096));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

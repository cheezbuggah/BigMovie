import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MoviesParser extends Parser {

    

    @Override
    public void Parse(){
        final String regex = "(.*?)\\s*\\((\\d\\d\\d\\d.{0,99}?|\\?\\?\\?\\?.{0,99}?)\\)?\\)\\s*(\\((\\D{0,3}?)\\)\\s*)?(\\{\\{(SUSPENDED)\\}\\})?\\s*(.*)";

        Pattern pattern = Pattern.compile(regex);

        try
        {
            pw = new PrintWriter("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\movies.csv");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Path path = Paths.get("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\movies.list");
        try (Stream<String> lines = Files.lines(path, Charset.forName("ISO_8859_1"))) {
            lines.map(pattern::matcher)
                    .filter(line -> line.matches())
                    .filter(Matcher::matches)
                    .forEach(matcher ->
                            pw.print(((matcher.group(6) == null || !matcher.group(6).contains("SUSPENDED")) && !matcher.group(1).startsWith("\""))?
                                    ("\"" + matcher.group(1).replace("\"","'").trim() + "\",\"" +      //Name /Does not print when movie is suspended
                            matcher.group(4)+  "\",\"" +    //Type ((TV)/(V))
                            matcher.group(2) + "\"\n"):""));  //Movie release year
            pw.close();
        }
        catch (IOException e)
        {
           
            e.printStackTrace();
        }
    }
}


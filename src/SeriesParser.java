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


public class SeriesParser extends Parser {

    

    @Override
    public void Parse(){
        final String regex = "(\\\".*\\\")\\s\\((.*)\\)\\s(\\{((.*?)(\\((\\#[^a-zA-Z]*)\\)\\s)?)(\\((\\#[^a-zA-Z]*|[\\d,-]{10})\\))?\\})?(\\{\\{SUSPENDED\\}\\})?\\t+(.*)";

        Pattern pattern = Pattern.compile(regex);

        try
        {
            pw = new PrintWriter("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\series.csv");
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
                    .forEach(matcher -> pw.print((matcher.group(7) == null && matcher.group(4) == null || matcher.group(7) == null && !matcher.group(4).contains("{SUSPENDED"))?
                            ("\"" + matcher.group(1).replace("\"","'") + "\",\"" +                                                              //Name /Does not print when series is suspended
                            matcher.group(2) + "\",\"" +                                                                                        //Year of serie
                            ((matcher.group(4) != null)? matcher.group(4).replace(","," ").replace("\"","'").trim()+ "\",\"" : "\",\"") +       //Episode name
                            matcher.group(9) + "\",\"" +                                                                                        //Episode number
                            matcher.group(11) + "\"\n"):""));                                                                                    //Year of episode
            pw.close();
        }
        catch (IOException e)
        {
            
            e.printStackTrace();
        }
    }
}


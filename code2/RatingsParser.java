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

public class RatingsParser extends Parser {
    
    
    @Override
    public void Parse(){
        final String regex = "\\s*.{10}\\s*(\\d*)\\s*(\\d*.\\d)\\s*(.*?)\\s*\\(((\\d\\d\\d\\d.{0,4}?|\\?\\?\\?\\?.{0,4}?))\\)\\s*(\\{(.*)\\((.*)\\)\\}|)\\s*(\\(.*\\)|)";

        Pattern pattern = Pattern.compile(regex);

        try 
        {
            pw = new PrintWriter("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\rating.csv");
        }         
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Path path = Paths.get("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\ratings.list");
        try (Stream<String> lines = Files.lines(path, Charset.forName("ISO_8859_1"))) {

            lines.map(pattern::matcher)
                    .skip(295)
                    .filter(line -> line.matches())
                    .filter(Matcher::matches)
                    .forEach(matcher -> pw.println("\"" + matcher.group(1)  +  "\",\"" +                                                        //Votes amount
                                                        matcher.group(2) + "\",\"" +                                                            //Avg rating
                                                        matcher.group(3).replace("\"","'").trim() + "\",\"" +                  //Title
                                                        matcher.group(4) + "\",\"" +                                                            //Year
                            ((matcher.group(7) != null)? matcher.group(7).replace(","," ").replace("\"","'").trim()+ "\",\"" : "\",\"") +       //Episode name
                                                        matcher.group(8) + "\""));                                                              //#Ep_number
            pw.close();
        } 
        catch (IOException e) 
        {
            
            e.printStackTrace();
        }
    }
}


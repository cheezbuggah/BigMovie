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


public class RunningTimesParser extends Parser {
    
    @Override
    public void Parse(){
        final String regex = "(.*?)\\s+(?>\\((\\d\\d\\d\\d.{0,99}?|\\?{4}.{0,99}?)\\))?\\s(?>\\((.*?)\\))?(\\{(.*?)(\\((\\#.*)\\))?\\})?(\\s\\{.*\\})?\\s*(?>(\\D*):|)?(\\d*)\\s*(\\((.*)\\)|:.*|)";

        Pattern pattern = Pattern.compile(regex);
        PrintWriter pw = null;

        try
        {
            pw = new PrintWriter("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\running-times.csv");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Path path = Paths.get("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\running-times.list");
        try (Stream<String> lines = Files.lines(path, Charset.forName("ISO_8859_1"))) {

            PrintWriter finalPw = pw;
            lines.map(pattern::matcher)
                    .filter(line -> line.matches())
                    .filter(Matcher::matches)
                    .forEach(matcher -> finalPw.println("\"" + matcher.group(1).replace("\"","'") + "\",\"" +                  //Name
                                        matcher.group(2) + "\",\"" +                                                                            //Year
                                        ((matcher.group(5) != null)? matcher.group(5).replace(","," ").replace("\"","'")+ "\",\"" : "\",\"") +  //Title ep
                                        matcher.group(7) + "\",\"" +                                                                            //#Ep
                                        matcher.group(9) + "\",\"" +                                                                            //Country
                                        matcher.group(10) + "\""));                                                                             //Time(min)
            pw.close();
        }
        catch (IOException e)
        {
           
            e.printStackTrace();
        }
    }
}
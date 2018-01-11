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


public class ActorsParser extends Parser{
    
    
    
    
    
    
    @Override
    public void Parse(){
        final String regex = "((\\'(.*)\\'\\s?)?((.*?)\\,\\s)?((.*)\\t+))?(.*?)\\s\\((.{4}\\/?[I,V,X,-]*)\\)\\s*(\\((V|TV|VG)?\\))?\\s*(\\{((.*?)(\\((\\#[^a-zA-Z]*)\\)\\s)?)(\\((\\#[^a-zA-Z]*|[\\d,-]{10})\\))?\\})?\\s*(\\((.*)\\))?\\s*(\\[(.*)\\])?\\s*(\\<(.*)\\>)?\\s*";
        Pattern pattern = Pattern.compile(regex);
        
        try 
        {
            pw = new PrintWriter("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\actors.csv");
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String[] actor = {""};
        Path path = Paths.get("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\actors.list");
        try (Stream<String> lines = Files.lines(path, Charset.forName("ISO_8859_1"))) {
            lines.map(pattern::matcher)
                    .skip(233)
                    .filter(line -> line.matches())
                    .filter(Matcher::matches)
                    .forEach(matcher -> { if(matcher.group(5) != null){
                        actor[0] = "\""+
                                ((matcher.group(3) != null) ? matcher.group(3).trim() : "null" ) + "\",\"" +                     //Nickname
                                ((matcher.group(5) != null) ? matcher.group(5).replace("\"","\'").trim() : "null" ) + "\",\"" +                     //Surname
                        ((matcher.group(7) != null) ? matcher.group(7).replace("\"","\'").trim() : "null" ) + "\"" ;}                               //Firstname
                        pw.println(actor[0] + ",\"" + matcher.group(8).replace("\"","\'").trim() + "\",\"" + matcher.group(9) + "\",\"" +           //Actor & Title & Year
                                ((matcher.group(13) != null) ? matcher.group(13).replace("\"","\'").replace(","," ").trim() : "null" ) + "\",\"" +  //Ep_Title
                                ((matcher.group(18) != null) ? matcher.group(18).replace("\"","\'").trim() : "null" ) + "\",\"" +                   //Ep_Number || Ep_Date
                                ((matcher.group(22) != null) ? matcher.group(22).replace("\"","\'").trim() : "null") + "\"");                       //Role
                    });

            pw.close();
        } 
        catch (IOException e) 
        {
            
            e.printStackTrace();
            
        }
    }
}
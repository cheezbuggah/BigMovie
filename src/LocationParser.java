import java.io.File;
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


public class LocationParser extends Parser {

    
    
    @Override
    public void Parse(){
        regex = "(.*?)\\s*\\((.{4}[\\/,I,V,X,\\-,\\d]*)\\)\\s*(\\{((.*?)(\\((\\#[^a-zA-Z]*)\\)\\s)?)(\\((\\#[^a-zA-Z]*|.{10})\\))?\\})?(\\(.*\\)|)\\s*(.*?,|)\\s*(.*?,|)\\s*(.*?,|)\\s*(.*?,|)\\s*(.*?,|)(\\w* \\w*|\\w*|)\\s*(\\(.*\\)|)";

        pattern = Pattern.compile(regex); 
        

        //Creates a new PrintWriter and catches exceptions
        try 
        {
            pw = new PrintWriter("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\locations.csv");
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        
        Path path = Paths.get("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\locations.list");
        try (Stream<String> lines = Files.lines(path, Charset.forName("ISO_8859_1"))) {
            lines.map(pattern::matcher)
                    .filter(line -> line.matches())
                    .filter(Matcher::matches)
                    .forEach(matcher -> pw.println("\"" + matcher.group(1).replace("\"","'").trim() + "\",\"" +                //Name
                                                        matcher.group(2).trim() + "\",\"" +                                                     //Year
                            ((matcher.group(4) != null) ? matcher.group(4).replace("\"","\'").replace(","," ").trim() : "null" ) + "\",\"" +    //Ep_Title                                      //Episode Title
                            ((matcher.group(9) != null) ? matcher.group(9).replace("\"","\'").replace(","," ").trim() : "null" ) + "\",\"" +    //Ep_Number || Ep_Date                                              //Episode Number
                                                        matcher.group(16).replace(',', ' ').trim() + "\",\"" +                                  //Country
                                                        matcher.group(17).replace(',', ' ').replace("\"","'").trim() + "\""));                  //Scene
            pw.close();
        }
        catch (IOException e) 
        {
            
            e.printStackTrace();
        }
    }   
}

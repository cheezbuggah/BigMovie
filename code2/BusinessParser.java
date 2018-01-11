import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessParser extends Parser {
        
    

    final String regex = "MV:\\s(.*)\\s(\\(.{4}\\))\\s*(\\(.*\\))?\\s*BT:\\s*(\\w*)\\s*(.*?\\d )";

    Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    PrintWriter pw = null;

    @Override
    public void Parse(){
        
        
        try 
        {
            char[] cbuf = new char[1024];
            StringBuilder builder = new StringBuilder();
           

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\business.list"),"ISO-8859-1"));

                while (reader.read(cbuf) != -1) {
                    builder.append(cbuf);
                }
                reader.close();
            } catch (IOException e) {
                
                e.printStackTrace();
            }
            pw = new PrintWriter("C:\\Users\\ducod_000\\Desktop\\12\\src\\main\\lists\\business.csv");
            String test = builder.toString();
            Matcher m = Pattern.compile(regex,Pattern.MULTILINE).matcher(test);
            while(m.find()){
                pw.print("\""+ m.group(1).replace("\"","").trim() + "\",");                                     //title
                pw.print("\""+ m.group(2).substring(1,m.group(2).length()-1) + "\",");                          //year
                pw.print("\"" + m.group(4) + "\",");                                                            //Currency
                pw.println("\"" + m.group(5).replace(",","").trim().replace("$","").replace(".","") + "\"");    //Amount
            }
            
            pw.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }        
}
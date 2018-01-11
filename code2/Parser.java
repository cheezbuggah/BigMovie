
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.regex.Pattern;


abstract class Parser {
    String regex;
    Path path;
    PrintWriter pw = null;
    Pattern pattern;
    
    abstract void Parse();
}

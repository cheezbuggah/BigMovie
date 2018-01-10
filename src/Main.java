import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;



public class Main {
    //public static ParserGUI gui;
    public static void main(String[] args) {
       //ActorsParser actorparse = new ActorsParser();
       // actorparse.Parse();
       //ActressesParser actresparse = new ActressesParser();
       //actresparse.Parse();
      // BusinessParser businessparse = new BusinessParser();
       // businessparse.Parse();
     //  LocationParser locationparse = new LocationParser();
      //  locationparse.Parse();
      // MoviesParser moviesparse = new MoviesParser();
     //   moviesparse.Parse();
     //  RatingsParser ratinsgparse = new RatingsParser();
      //  ratinsgparse.Parse();
     //  RunningTimesParser runningtimesparse = new RunningTimesParser();
      //  runningtimesparse.Parse();
      // SeriesParser seriesparse = new SeriesParser();
      //  seriesparse.Parse();
        connector connected = new connector();
            connected.connecting();
        ScriptRunner scriptRunner = new ScriptRunner(Connection connection,false, true);
            
        
    }
    
}
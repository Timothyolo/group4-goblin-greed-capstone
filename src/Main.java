import org.json.simple.parser.ParseException;
import java.io.IOException;
import com.Imports.ImportJSON;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        ImportJSON assets = new ImportJSON();
        System.out.println(assets.getItems());
        System.out.println(assets.getNpcs());
        System.out.println(assets.getMap());









    }
}

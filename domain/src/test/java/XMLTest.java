import com.enzosagnelonge.data.Goods;
import com.enzosagnelonge.data.Movement;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDateTime;
import java.util.UUID;

public class XMLTest {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        Goods goods = new Goods(UUID.randomUUID(), "AWB", "798E93904", 5, 50, 1, 50, "ddeui hufeiu");
        Movement movement = new Movement(UUID.randomUUID(), LocalDateTime.now(), "ENZO", LocalDateTime.now(), "BORDEAUX", goods, "OK", "BDXS", "Shop", "reef", "jsp", Movement.TypeMovement.In);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        // for pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(movement.toXML(doc));


        StreamResult console = new StreamResult(System.out);
        // write data
        transformer.transform(source, console);
        //transformer.transform(source, file);

    }

}

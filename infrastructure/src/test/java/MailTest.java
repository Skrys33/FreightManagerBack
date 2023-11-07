import com.enzosagnelonge.data.Goods;
import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.data.Warehouse;

import javax.mail.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.UUID;

public class MailTest {
    public static void main(String[] args) throws GeneralSecurityException, IOException, MessagingException, ParserConfigurationException, TransformerException {
        Goods goods = new Goods("AWB", "798E93904", 5, 50, 1, 50, "ddeui hufeiu");
        Warehouse fromWarehouse = new Warehouse("OK", "BDXS");
        Warehouse toWarehouse = new Warehouse("OK", "BDXS");

        Movement movement = new Movement(UUID.randomUUID(), LocalDateTime.now(), "ENZO", LocalDateTime.now(), "BORDEAUX", goods, fromWarehouse, toWarehouse, "Shop", "reef", "jsp", Movement.TypeMovement.In);

        //MailService.sendMail(movement);
//        new GmailService().sendMail(movement);
    }
}

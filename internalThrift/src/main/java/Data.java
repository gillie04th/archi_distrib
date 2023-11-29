import thrift.*;

import java.util.ArrayList;
import java.util.List;

public class Data {

    static List<ModelTO> data;


    public static void getData() {
        data = new ArrayList<ModelTO>();

        data.add(new ModelTO("John", "Doe", "1234567890", "123 Main St", "12345", "Anytown", "USA", 1234567890, "Acme", "CA"));
        data.add(new ModelTO("Jane", "Doe", "1245862289", "321 Main St", "12345", "Le Mans", "FRA", 1234567809, "ouicompagny", "Pay de la Loire"));
        data.add(new ModelTO("Simon", "Geslin", "0246585869", "63 rue elblé", "49000", "Angers", "FRA", 1234567981, "frenchie", "ANJ"));
        data.add(new ModelTO("Matteo", "Richefeu", "789456120", "10 rue Jean Bodin", "49000", "Angers", "FRA", 29112023, "Infotel", "ANJ"));
        data.add(new ModelTO("Jean", "Dupont", "789456120", "12 rue Jean Bodin", "49000", "Angers", "FRA", 29112024, "gang", "ANJ"));
    }
}

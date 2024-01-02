package Service;

import Model.ModelTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Data {

    public static List<ModelTO> data;
    
    private static double revenu(){
        return ThreadLocalRandom.current().nextDouble(2000000, 6000000);
    }

    public static List<ModelTO> getData() {
        if(data == null) {
            data = new ArrayList<ModelTO>();
            data.add(new ModelTO("John", "Lee", "1234567890", "123 Main St", "12345", "Anytown", "USA", "20-09-2000", "Acme", "CA").setAnnualRevenue(revenu()));
            data.add(new ModelTO("Jane", "Leer", "1245862289", "321 Main St", "12345", "Le Mans", "FRA", "19-01-1998", "ouicompagny", "Pays de la Loire").setAnnualRevenue(revenu()));
            data.add(new ModelTO("Simon", "Geslin", "0246585869", "63 rue elblé", "49000", "Angers", "FRA", "06-02-2012", "frenchie", "CA").setAnnualRevenue(revenu()));
            data.add(new ModelTO("Matteo", "Richefeu", "789456120", "10 rue Jean Bodin", "49000", "Angers", "FRA", "29-06-2023", "Infotel", "CA").setAnnualRevenue(revenu()));
            data.add(new ModelTO("Jean", "Dupont", "789456120", "12 rue Jean Bodin", "49000", "Angers", "FRA", "01-12-1990", "gang", "ANJ").setAnnualRevenue(revenu()));
        }
        return data;
    }

    public static void addLead(ModelTO lead) {
        data.add(lead);
    }
}

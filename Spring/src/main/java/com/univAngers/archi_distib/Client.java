package com.univAngers.archi_distib;

import com.univAngers.archi_distib.Controller.LeadController;
import com.univAngers.archi_distib.DTO.VirtualLeadDto;
import com.univAngers.archi_distib.Factories.LeadControllerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws ParseException {

        LeadController controller = LeadControllerFactory.getInstance();

        boolean exit = false;

        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.print("virtualCrmClient: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 0) {
                System.out.println("Aucune commande fourni.");
                help();
                continue;
            }

            String command = parts[0];


            switch (command) {
                case "help":

                    help();

                    break;
                case "exit":

                    exit = true;

                    System.out.println("Fermeture du client.");

                    break;
                case "findLeadsByDate":
                    if (parts.length != 3) {
                        System.out.println("findLeadsByDate nécessite 2 arguments.");
                        continue;
                    }

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                    Calendar startDate = Calendar.getInstance();
                    Calendar endDate = Calendar.getInstance();
                    startDate.setTime(formatter.parse(parts[1]));
                    endDate.setTime(formatter.parse(parts[2]));

                    displayLeads(controller.findLeadByDate(startDate, endDate));

                    break;
                case "findLeads":

                    if (parts.length != 4) {
                        System.out.println("findLeads nécessite 3 arguments.");
                        continue;
                    }

                    Double lowAnnualRevenue = Double.parseDouble(parts[1]);
                    Double highAnnualRevenue = Double.parseDouble(parts[2]);
                    String status = parts[3];

                    displayLeads(controller.findLeads(lowAnnualRevenue, highAnnualRevenue, status));

                    break;
                default:
                    System.out.println("Commande Inconnue : " + command);
                    System.out.println("Veuillez consulter l'aide pour voir la liste des commandes en tappant \"help\".");
                    break;
            }
        }
    }

    public static void help() {
        System.out.println("Liste des commandes :");
        System.out.println("\t - findLeads <lowAnnualRevenue> <highAnnualRevenue> <status>");
        System.out.println("\t - findLeadsByDate <startDate> <endDate>");
    }

    public static void displayLeads(List<VirtualLeadDto> leads) {
        for (VirtualLeadDto lead : leads) {
            System.out.println(lead.toString());
        }
    }
}

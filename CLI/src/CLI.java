import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univAngers.archi_distib.DTO.VirtualLeadDto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CLI {

    public static void main(String[] args) {
        String url;
        Scanner sc = new Scanner(System.in);
        String[] commande;
        while (true) {
            url = "https://localhost/leads/";
            System.out.println("Saisissez une commande :");
            commande = sc.nextLine().split(" ");
            if (commande.length > 0) {
                switch (commande[0]) {
                    case "findLeads":
                        if (commande.length != 5) {
                            System.out.println("Invalid number of arguments for findLeads");
                            break;
                        }
                        url += commande[2] + "/" + commande[4];
                        double min = Double.parseDouble(commande[2]);
                        double max = Double.parseDouble(commande[4]);
                        // Call the API to find leads within the given range
                        // Display the results break;
                    case "findLeadsByDate":
                        if (commande.length != 3) {
                            System.out.println("Syntaxe invalide pour findLeadsByDate\nRespecter : findLeadsByDate DateDebut DateFin");
                            break;
                        }
                        ArrayList<VirtualLeadDto> leads = new ArrayList<VirtualLeadDto>();
                        url += commande[1] + "/" + commande[2];
                        //Date from = new SimpleDateFormat("yyyy-MM-dd").parse(args[2]);
                        // Date to = new SimpleDateFormat("yyyy-MM-dd").parse(args[4]);
                        // Call the API to find leads within the given date range
                        // Display the results
                        HttpHeaders headers = new HttpHeaders();
                        HttpEntity<String> request = new HttpEntity<>("", headers);
                        RestTemplate restTemplate = new RestTemplate();
                        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            JsonNode body = mapper.readTree(response.getBody());
                            //System.out.println(response.getStatusCode() + " - body : " + response.getBody()); JsonNode records = body.get("records");
                            // System.out.println(body.get("records"));
                            Calendar calendar = Calendar.getInstance();
                            for (JsonNode record : records) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                                OffsetDateTime dateTime = OffsetDateTime.parse(record.get("CreatedDate").textValue(), formatter);
                                calendar.setTime(Date.from(dateTime.toInstant()));
                                VirtualLeadDto lead = new VirtualLeadDto(record.get("FirstName").textValue(), record.get("LastName").textValue(), record.get("AnnualRevenue").asDouble(), record.get("Phone").textValue(), record.get("Street").textValue(), record.get("PostalCode").textValue(), record.get("City").textValue(), record.get("Country").textValue(), calendar, record.get("Company").textValue(), record.get("State").textValue());
                                leads.add(lead);
                                System.out.println(lead);
                            }
                        } catch (JsonProcessingException e) {
                            System.out.println("Error");
                        }
                        break;
                    default:
                        System.out.println("Commande invalide");
                }
            } else {
                System.out.println("No command provided");
            }
        }
    }
}
package com.univAngers.archi_distib.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univAngers.archi_distib.DTO.VirtualLeadDto;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SalesforceService {

    private Dotenv dotenv = Dotenv.load();
    private String instance_url;
    private String bearer;
    private String login_url = "https://login.salesforce.com/services/oauth2/token";

    public List<VirtualLeadDto> findLeads(double lowAnnualRevenue, double highAnnualRevenue) {
        ArrayList<VirtualLeadDto> leads = new ArrayList<VirtualLeadDto>();
        int tries = 0;

        do {
            if (bearer == null) {
                login();
            }
            tries++;

            // Requête GET vers l'API Salesforce pour récupérer les Leads
            String url = instance_url + "/services/data/v59.0/query?q=SELECT+Id,+Firstname,+Lastname,+AnnualRevenue,+Phone,+Street,+PostalCode,+City,+Country,+CreatedDate,+Company,+State+FROM+Lead+WHERE+AnnualRevenue+>+" + (int) lowAnnualRevenue + "+AND+AnnualRevenue+<+" + (int) highAnnualRevenue;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", bearer);

            HttpEntity<String> request = new HttpEntity<>("", headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode body = mapper.readTree(response.getBody());
//                System.out.println(response.getStatusCode() + " - body : " + response.getBody());
                JsonNode records = body.get("records");
//                System.out.println(body.get("records"));

                Calendar calendar = Calendar.getInstance();
                for(JsonNode record : records) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    OffsetDateTime dateTime = OffsetDateTime.parse(record.get("CreatedDate").textValue(), formatter);
                    calendar.setTime(Date.from(dateTime.toInstant()));

                    VirtualLeadDto lead = new VirtualLeadDto(record.get("FirstName").textValue(),record.get("LastName").textValue(),record.get("AnnualRevenue").asDouble(),record.get("Phone").textValue(),record.get("Street").textValue(),record.get("PostalCode").textValue(),record.get("City").textValue(),record.get("Country").textValue(),calendar,record.get("Company").textValue(),record.get("State").textValue());
                    leads.add(lead);
//                    System.out.println(lead);
                }

                if (response.getStatusCode() == HttpStatusCode.valueOf(200)) {
                    tries = 3;
                } else if (body.get("error_code").equals("INVALID_SESSION_ID")) {
                    bearer = null;
                }
            } catch (JsonProcessingException e) {
                System.out.println("Error");
            }

        } while (tries < 3);
        return leads;
    }

    private void login() {


        String grantType = dotenv.get("GRANT_TYPE");
        String clientId = dotenv.get("CLIENT_ID");
        String clientSecret = dotenv.get("CLIENT_SECRET");
        String username = dotenv.get("LOGIN");
        String password = dotenv.get("PASSWORD");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", grantType);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//        System.out.println(request.getBody());

        ResponseEntity<String> response = restTemplate.postForEntity(login_url, request, String.class);

//        System.out.println(response.getStatusCode() + " - body : " + response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(response.getBody());
            bearer = "Bearer " + jsonNode.get("access_token").textValue();
            instance_url = jsonNode.get("instance_url").textValue();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

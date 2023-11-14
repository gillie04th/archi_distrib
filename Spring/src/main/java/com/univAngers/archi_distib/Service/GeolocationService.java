package com.univAngers.archi_distib.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.univAngers.archi_distib.DTO.GeographicPointDto;
import com.univAngers.archi_distib.DTO.VirtualLeadDto;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

public class GeolocationService {

    private GeographicPointDto requestGeolocationApi(VirtualLeadDto lead) throws JsonProcessingException {

        String url = "https://nominatim.openstreetmap.org/search?" +
                "city=" + lead.getCity() +
                "&country=" + lead.getCountry() +
                "&postalcode=" + lead.getPostalCode() +
                "&street=" + lead.getStreet() +
                "&format=json&limit=1";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(result).get(0);

//        System.out.println(jsonNode.get("lat") + ", " + jsonNode.get("lon"));

        return new GeographicPointDto(jsonNode.get("lat").asDouble(), jsonNode.get("lon").asDouble());
    }

    public void getGpsCoordinates(VirtualLeadDto lead) throws JsonProcessingException {
        lead.setGeographicPointTO(requestGeolocationApi(lead));
    }

    public void getGpsCoordinates(List<VirtualLeadDto> leads) throws JsonProcessingException {
        for(VirtualLeadDto lead : leads) {
            lead.setGeographicPointTO(requestGeolocationApi(lead));
        }
    }

}

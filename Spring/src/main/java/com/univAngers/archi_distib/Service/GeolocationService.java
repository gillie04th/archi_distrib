package com.univAngers.archi_distib.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private GeographicPointDto requestGeolocationApi(VirtualLeadDto lead){


        String url = "https://nominatim.openstreetmap.org/search?" +
                "city=" + lead.getCity() +
                "&country=" + lead.getCountry() +
                "&postalcode=" + lead.getPostalCode() +
                "&street=" + lead.getStreet() +
                "&format=json&limit=1";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        System.out.println(result);

        return new GeographicPointDto(1.0,1.0);
    }

    public static void getGpsCoordinates(VirtualLeadDto lead) {

    }

    public static void getGpsCoordinates(List<VirtualLeadDto> leads) {

    }

}

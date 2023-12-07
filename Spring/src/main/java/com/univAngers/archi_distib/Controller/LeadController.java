package com.univAngers.archi_distib.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.univAngers.archi_distib.DTO.VirtualLeadDto;
import com.univAngers.archi_distib.Service.GeolocationService;
import com.univAngers.archi_distib.Service.SalesforceService;
import com.univAngers.archi_distib.Service.VirtualCrmService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController implements VirtualCrmService {

    GeolocationService locationService = new GeolocationService();
    SalesforceService salesforceService = new SalesforceService();

    @GetMapping("/all/{lowAnnualRevenue}/{highAnnualRevenue}")
    @Override
    public List<VirtualLeadDto> findLeads(Double lowAnnualRevenue, Double highAnnualRevenue) {
        List<VirtualLeadDto> leads = new ArrayList<VirtualLeadDto>();

        leads.addAll(salesforceService.findLeads(500000000, 1000000000));
        try {
            locationService.getGpsCoordinates(leads);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return leads;
    }

    @GetMapping("/{startDate}/{endDate}")
    @Override
    public VirtualLeadDto findLeadByDate(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar startDate, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar endDate) {
        VirtualLeadDto lead = new VirtualLeadDto("Machin","TRUC",200000.43,"06666666","4 boulevard de Lavoisier","49000","Angers","France",Calendar.getInstance(),"UA","France");
        try {
            locationService.getGpsCoordinates(lead);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return lead;
    }
}

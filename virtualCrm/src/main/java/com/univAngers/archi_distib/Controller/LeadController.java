package com.univAngers.archi_distib.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.univAngers.archi_distib.DTO.VirtualLeadDto;
import com.univAngers.archi_distib.Service.GeolocationService;
import com.univAngers.archi_distib.Service.InternalLeadService;
import com.univAngers.archi_distib.Service.SalesforceService;
import com.univAngers.archi_distib.Service.VirtualCrmService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController implements VirtualCrmService {

    GeolocationService locationService;
    SalesforceService salesforceService;
    InternalLeadService internalLeadService;

    public LeadController() {
         locationService = new GeolocationService();
         salesforceService  = new SalesforceService();
         internalLeadService = new InternalLeadService();
    }

    @GetMapping("/{lowAnnualRevenue}/{highAnnualRevenue}/{state}")
    @Override
    public List<VirtualLeadDto> findLeads(@PathVariable Double lowAnnualRevenue, @PathVariable Double highAnnualRevenue, @PathVariable String state) {
        List<VirtualLeadDto> leads = new ArrayList<VirtualLeadDto>();

//        System.out.println(lowAnnualRevenue + " " + highAnnualRevenue + " " + state);

        leads.addAll(salesforceService.findLeads(lowAnnualRevenue, highAnnualRevenue, state));
        leads.addAll(internalLeadService.findLeads(lowAnnualRevenue, highAnnualRevenue, state));
        try {
            locationService.getGpsCoordinates(leads);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return leads;
    }

    @GetMapping("/{startDate}/{endDate}")
    @Override
    public List<VirtualLeadDto> findLeadByDate(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar startDate, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar endDate) {
        List<VirtualLeadDto> leads = new ArrayList<VirtualLeadDto>();

        leads.addAll(salesforceService.findLeadsByDate(startDate, endDate));
        leads.addAll(internalLeadService.findLeadsByDate(startDate, endDate));
        try {
            locationService.getGpsCoordinates(leads);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return leads;
    }
}

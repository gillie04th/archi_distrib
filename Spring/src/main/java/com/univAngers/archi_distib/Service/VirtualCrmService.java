package com.univAngers.archi_distib.Service;

import com.univAngers.archi_distib.DTO.VirtualLeadDto;

import java.util.Calendar;
import java.util.List;

public interface VirtualCrmService {

    public List<VirtualLeadDto> findLeads(Double lowAnnualRevenue, Double highAnnualRevenue);

    public VirtualLeadDto findLeadByDate(Calendar startDate, Calendar endDate);

}
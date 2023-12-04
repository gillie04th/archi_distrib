namespace java com.univAngers.archi_distrib

struct LeadTO {
  1: required string firstName,
  2: required string lastName,
  3: optional double annualRevenue,
  4: required string phone,
  5: required string street,
  6: required string postalCode,
  7: required string city,
  8: required string country,
  9: required string creationDate,
  10: required string company,
  11: required string state
}

service InternalCRMService {
  void addLead(1: LeadTO lead),
  list<LeadTO> findLeads(1: double lowAnnualRevenue, 2: double highAnnualRevenue, 3: string state),
  list<LeadTO> findLeadsByDate(1: string startDate, 2: string endDate),
  void deleteLead(1: LeadTO lead)
}

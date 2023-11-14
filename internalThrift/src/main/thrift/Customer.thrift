namespace java InternalCRMService

struct LeadTO {
  1: required string firstName,
  2: required string lastName,
  3: optional double annualRevenue,
  4: required string phone,
  5: required string street,
  6: required string postalCode,
  7: required string city,
  8: required string country,
  9: required i64 creationDate,
  10: required string company,
  11: required string state
}

struct InternalLeadDto {
  1: required string name,
  2: optional double annualRevenue,
  3: required string phone,
  4: required string street,
  5: required string postalCode,
  6: required string city,
  7: required string country,
  8: required i64 creationDate,
  9: required string company,
  10: required string state,
}

service InternalCRMService {
  void ping(),

  void addLead(1: LeadTO lead),
  list<LeadTO> findLeads(1: double lowAnnualRevenue, 2: double highAnnualRevenue, 3: string state),
  list<LeadTO> findLeadsByDate(1: string startDate, 2: string endDate),
  void deleteLead(1: LeadTO lead),

  oneway void zip()
}

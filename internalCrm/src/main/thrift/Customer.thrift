namespace java thrift

struct InternalLeadDto {
  1: required string name,
  2: optional double annualRevenue,
  3: required string phone,
  4: required string street,
  5: required string postalCode,
  6: required string city,
  7: required string country,
  8: required string creationDate,
  9: required string company,
  10: required string state,
}

service InternalCRMService {
  void ping(),
  void addLead(1: InternalLeadDto lead),
  list<InternalLeadDto> findLeads(1: double lowAnnualRevenue, 2: double highAnnualRevenue, 3: string state),
  list<InternalLeadDto> findLeadsByDate(1: string startDate, 2: string endDate),
  void deleteLead(1: InternalLeadDto lead),
}

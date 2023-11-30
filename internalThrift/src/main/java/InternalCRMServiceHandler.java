import org.apache.thrift.TException;
import thrift.InternalCRMService;
import thrift.InternalLeadDto;

import java.util.List;

public class InternalCRMServiceHandler implements InternalCRMService.Iface {

    thrift.InternalCRMService service;
    List<ModelTO> data = Data.getData();

    public InternalCRMServiceHandler() {
        this.service = new thrift.InternalCRMService();
        Data.getData();
    }

    @Override
    public void ping() throws TException {
        System.out.println("ping()");
    }

    @Override
    public void addLead(InternalLeadDto lead) throws TException {
        ModelTO modelTO = new ModelTO(lead.getName().split(" "), lead.getName().split(" "), lead.getPhone(), lead.getStreet(), lead.getPostalCode(), lead.getCity(), lead.getCountry(), lead.getCreationDate(), lead.getCompany(), lead.getState());
        this.data.add(lead);
    }

    @Override
        public List<InternalLeadDto> findLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) throws TException {
            return this.data.stream().filter(
                    lead -> lead.getAnnualRevenue() >= lowAnnualRevenue &&
                    lead.getAnnualRevenue() <= highAnnualRevenue &&
                    lead.getState().equals(state)).toList();
    }

    @Override
    public List<InternalLeadDto> findLeadsByDate(String startDate, String endDate) throws TException {
        return this.data.stream().filter(
                lead -> lead.getCreationDate().compareTo(startDate) >= 0  &&
                lead.getCreationDate().compareTo(endDate) >= 0 ).toList();
    }

    @Override
    public void deleteLead(InternalLeadDto lead) throws TException {
        this.data.remove(lead);
    }
}

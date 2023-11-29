import org.apache.thrift.TException;
import thrift.InternalCRMService;
import thrift.ModelTO;

import java.util.List;

public class InternalCRMServiceHandler implements InternalCRMService.Iface {

    thrift.InternalCRMService service;

    public InternalCRMServiceHandler() {
        this.service = new thrift.InternalCRMService();
        Data.getData();
    }

    @Override
    public void ping() throws TException {
        System.out.println("ping()");
    }

    @Override
    public void addLead(ModelTO lead) throws TException {
        Data.data.add(lead);
    }

    @Override
    public List<ModelTO> findLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) throws TException {
        return null;
    }

    @Override
    public List<ModelTO> findLeadsByDate(String startDate, String endDate) throws TException {
        return null;
    }

    @Override
    public void deleteLead(ModelTO lead) throws TException {
        Data.data.remove(lead);
    }
}

import org.apache.thrift.TException;
import thrift.InternalCRMService;
import thrift.ModelTO;

import java.util.List;

public class InternalCRMServiceHandler implements InternalCRMService.Iface {

    @Override
    public void ping() throws TException {
        System.out.printf("ping");
    }

    @Override
    public void addLead(ModelTO lead) throws TException {

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

    }
}

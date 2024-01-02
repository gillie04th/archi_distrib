import org.apache.thrift.TException;
import thrift.InternalCRMService;
import thrift.InternalLeadDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        ModelTO modelTO = new ModelTO(lead.getName().split(" ")[0], lead.getName().split(" ")[1], lead.getPhone(), lead.getStreet(), lead.getPostalCode(), lead.getCity(), lead.getCountry(), lead.getCreationDate(), lead.getCompany(), lead.getState());
        Data.addLead(modelTO);
    }

    @Override
    public List<InternalLeadDto> findLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) throws TException {
        List<ModelTO> models = this.data.stream().filter(
                lead -> lead.getAnnualRevenue() >= lowAnnualRevenue &&
                        lead.getAnnualRevenue() <= highAnnualRevenue &&
                        lead.getState().equals(state)
        ).toList();
        return models.stream().map(lead -> lead.convertToVirtualLeadDTO()).toList();
    }

    @Override
    public List<InternalLeadDto> findLeadsByDate(String startDate, String endDate) throws TException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        List<ModelTO> models = this.data.stream().filter(
                lead -> {
                    Boolean res = false;
                    try {
                        res = sdf.parse(lead.getCreationDate()).before(sdf.parse(startDate)) &&
                                sdf.parse(lead.getCreationDate()).before(sdf.parse(endDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return res;
                }).toList();
        return models.stream().map(lead -> lead.convertToVirtualLeadDTO()).toList();
    }

    @Override
    public void deleteLead(InternalLeadDto lead) throws TException {
        this.data.remove(lead);
    }
}

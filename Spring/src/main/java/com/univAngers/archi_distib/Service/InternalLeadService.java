package com.univAngers.archi_distib.Service;

import com.univAngers.archi_distib.DTO.VirtualLeadDto;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Calendar;
import java.util.List;

public class InternalLeadService {

    TTransport transport;
    thrift.InternalCRMService.Client client;

    public List<VirtualLeadDto> findLeads(double lowAnnualRevenue, double highAnnualRevenue, String state) {
        List<VirtualLeadDto> leads = null;

        openConnection();
        try {
            leads = client.findLeads(lowAnnualRevenue, highAnnualRevenue, state).stream().map(lead -> new VirtualLeadDto(lead)).toList();
            leads.stream().forEach(lead -> System.out.println(lead.getFirstName()));
        } catch (TException e) {
            throw new RuntimeException(e);
        }
        transport.close();

        return leads;
    }

    public List<VirtualLeadDto> findLeadsByDate(Calendar startDate, Calendar endDate) {
        List<VirtualLeadDto> leads = null;

        openConnection();
        try {
            leads = client.findLeadsByDate(startDate.toString(), endDate.toString()).stream().map(lead -> new VirtualLeadDto(lead)).toList();
        } catch (TException e) {
            throw new RuntimeException(e);
        }
        transport.close();

        return leads;
    }

    private void openConnection() {
        try {
            transport = new TSocket("localhost", 8081);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new thrift.InternalCRMService.Client(protocol);
        } catch (TException x) {
            x.printStackTrace();
        }
    }
}

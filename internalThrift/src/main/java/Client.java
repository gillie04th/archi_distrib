import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.InternalCRMService;
import thrift.InternalLeadDto;

import java.util.List;

public class Client {
   public static void main(String[] args) {
           try {
               TTransport transport;
               transport = new TSocket("localhost", 8080);
               transport.open();


               TProtocol protocol = new TBinaryProtocol(transport);
               InternalCRMService.Client client = new InternalCRMService.Client(protocol);
               perform(client);

               transport.close();
           } catch (TException x) {
               x.printStackTrace();
           }
        }

        private static void perform(InternalCRMService.Client client) throws TException {
            List<?> leads = client.findLeads(2000000,6000000,"ANJ");
            leads.stream().forEach(lead -> System.out.println(lead.toString() + "\n"));
   }
}

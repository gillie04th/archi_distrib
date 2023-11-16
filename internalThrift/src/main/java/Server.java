import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import thrift.InternalCRMService;
import thrift.InternalLeadDto;

public class Server {

    public static InternalCRMService crm;
    public static InternalCRMService.Processor processor;
    public static void main(String [] args) {
        try {
            crm = new InternalCRMService();
            //TODO regarder cette partie de code fonctionne pas a comparer avec la doc de thrift
            processor = new InternalCRMService.Processor(crm);


            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(InternalCRMService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(8080);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

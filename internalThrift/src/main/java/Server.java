import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import thrift.InternalCRMService;

public class Server {

    public static InternalCRMServiceHandler handler;
    public static InternalCRMService.Processor processor;
    public static void main(String [] args) {
        try {
            handler = new InternalCRMServiceHandler();
            processor = new InternalCRMService.Processor(handler);


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

package beatsServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import protoManagers.PatientManager;


public class TestServer {

    private final int port;

    public TestServer(int port){
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker =new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInit());
            bootstrap.bind(port).sync().channel().closeFuture().sync();

        }

        finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
    public static void main(String[] args) {
        try {


            PatientManager.addPatient(1234, "paul ToLoveRu and overwight", 4);
            PatientManager.addPatient(1235, "tycho mentally instable", 16);
            PatientManager.addPatient(1236, "Japanse Otaku", 12);
            PatientManager.addPatient(1237, "kevin chips in mijn jas", 20);
            PatientManager.addPatient(1238, "Tom Asuna is my life", 5);
            PatientManager.addPatient(1239, "Robin Hood", 27);
            PatientManager.addPatient(1241, "Dave Keta", 21);
            TestServer test = new TestServer(8088);
            test.run();
            //only test
            //MessageManager.requestProcessingModule(MessageManager.createRequestMessage(1235));
        }catch (InterruptedException e){
            System.out.println(e.getCause());
        }
    }
}

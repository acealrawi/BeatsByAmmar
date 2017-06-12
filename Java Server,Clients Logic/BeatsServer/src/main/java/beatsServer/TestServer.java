package beatsServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import protoManagers.MessageManager;
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


            PatientManager.addPatient(1234, "Paul", 18,"m",90, "E-sport");
            PatientManager.addPatient(1235, "Tycho", 18,"m",85, "E-sport");
            PatientManager.addPatient(1236, "Ali", 20,"m",75, "E-sport");
            PatientManager.addPatient(1237, "kevin", 20,"m",65, "E-sport");
            PatientManager.addPatient(1238, "Tom", 20,"m",70, "E-sport");
            PatientManager.addPatient(1239, "Robin", 20,"m",60, "E-sport");
            PatientManager.addPatient(1241, "Dave", 21,"m",85, "E-sport");
            TestServer test = new TestServer(8088);
            test.run();
//            while (true) {
//                TestServerProtoHandler.channel.writeAndFlush(MessageManager.createSensorMessage(12));
//            }
            //only test
            //MessageManager.requestProcessingModule(MessageManager.createRequestMessage(1235));
        }catch (InterruptedException e){
            System.out.println(e.getCause());
        }
    }
}

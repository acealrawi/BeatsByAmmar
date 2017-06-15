
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
            bootstrap.bind(port).sync().channel().closeFuture().sync(); //bind the server to the device ip address and the chosen port.

        }

        finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
    public static void main(String[] args) {
        try {

            //creates few patients
            PatientManager.addPatient(1234, "Paul", 18,"M",90, "E-sport");
            PatientManager.addPatient(1235, "Tycho", 18,"M",85, "E-sport");
            PatientManager.addPatient(1236, "Ali", 20,"M",75, "E-sport");
            PatientManager.addPatient(1237, "kevin", 20,"M",65, "E-sport");
            PatientManager.addPatient(1238, "Tom", 20,"M",70, "E-sport");
            PatientManager.addPatient(1239, "Robin", 20,"M",60, "E-sport");
            PatientManager.addPatient(1241, "Dave", 21,"M",85, "Fitness");
            TestServer test = new TestServer(8088);
            test.run();

        }catch (InterruptedException e){
            System.out.println(e.getCause());
        }
    }
}

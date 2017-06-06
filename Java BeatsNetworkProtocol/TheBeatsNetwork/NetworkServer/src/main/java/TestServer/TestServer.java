package TestServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Gebruiker on 5/12/2017.
 */
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


    public static void main(String[] args) throws InterruptedException {
        TestServer test = new TestServer(8088);
        test.run();
    }
}

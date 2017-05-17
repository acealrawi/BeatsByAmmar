package TestClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Created by Gebruiker on 5/11/2017.
 */
public class TestClient {


    private final int port;
    private final String host;
    TestClient(String host ,int port){
        this.host =host;
        this.port=port;
    }

    public void run() throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new TestClientInit());
            Channel channel = b.connect(host,port).sync().channel();
            System.out.println("Connected");
            channel.writeAndFlush("sup");
            System.out.println("sent");



        }
        finally {
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {
        TestClient test = new TestClient("localhost",8088);
        test.run();
//        System.out.println("hi");

    }
}

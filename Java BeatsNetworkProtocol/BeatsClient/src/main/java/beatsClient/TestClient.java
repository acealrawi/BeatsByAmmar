package beatsClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import proto.ProtoMessageOuterClass.ProtoMessage;
import protoManagers.MessageManager;

import java.util.Scanner;


public class TestClient {



    EventLoopGroup group = new NioEventLoopGroup();
    private final int port = 8088;
    private final String host = "localhost";
    private Channel channel;




    public void run() throws Exception{
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.option(ChannelOption.SO_KEEPALIVE,true);
            b.channel(NioSocketChannel.class);
            b.handler(new TestClientInit());
//            ChannelFuture future = b.connect(host,port).sync();
//            channel = future.channel();
            channel = b.connect(host,port).sync().channel();
        }
        catch (Exception e){
            System.out.println("Connection Error: "+e.getMessage());
        }
    }

    public void writeMessage(ProtoMessage message){
         channel.writeAndFlush(message);
    }

    public static void main(String[] args) throws Exception {
        TestClient test = new TestClient();
        test.run();
        Scanner scan = new Scanner(System.in);
        while (true){
            test.writeMessage(MessageManager.createRequestMessage(scan.nextInt()));
        }




    }
}

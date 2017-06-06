
package MVC;

import Client.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import proto.ProtoMessageOuterClass.ProtoMessage;
import protoManagers.MessageManager;

import java.util.Scanner;



public class Model {

    private static String host = "localhost";
    private static int port = 8088;
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static Channel channel;

    public static void startClient(){
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());
            channel = bootstrap.connect(host,port).sync().channel();
        }
        catch (Exception e){
            System.out.println("Connection Error: "+e.getMessage());
        }

    }
    public static void stopClient() {
        group.shutdownGracefully();
    }

    public static void sendRequest(int number){
        channel.writeAndFlush(MessageManager.createRequestMessage(number));
    }
    public static void sendString(String msg){
        channel.writeAndFlush(msg);
    }











}

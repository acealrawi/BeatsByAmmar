import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client  {

    String host = "localhost";
    int port = 8088;

    EventLoopGroup workerGroup = new NioEventLoopGroup();
    ChannelFuture f;
    private Channel ch;

    public Client() {
    }

    public void startClient() throws InterruptedException {
        Bootstrap boot = new Bootstrap();
        boot.group(workerGroup);
        boot.channel(NioSocketChannel.class);
        boot.option(ChannelOption.SO_KEEPALIVE, true);
        boot.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                // Inbound
                ch.pipeline().addLast("encoder",new StringDecoder());
                ch.pipeline().addLast("decoder",new StringEncoder());

                // Handler
                ch.pipeline().addLast("handler",new ClientHandler());
            }
        });

        // Start the client
        f = boot.connect(host, port).sync();
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("connected to server");
                ch = f.channel();
            }
        });
    }

    public void stopClient() {
        workerGroup.shutdownGracefully();
    }

    private void writeMessage(String input) {

        ChannelFuture fut = ch.writeAndFlush(input);
        fut.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("send message");
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        client.startClient();

        System.out.println("running.\n\n");
        final Scanner scanner = new Scanner(System.in);

        while (true) {

            final String input = scanner.nextLine();

            if ("q".equals(input.trim())) {
                break;
            } else {
                client.writeMessage(input);
            }
        }

        scanner.close();
        client.stopClient();  //call this at some point to shutdown the client
    }
}
package TestServer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * Created by Gebruiker on 5/12/2017.
 */
public class TestServerInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //pipeline.addLast("ProtobufEncode", new ProtobufEncoder());
        pipeline.addLast("StringDecoder",new StringDecoder());
        pipeline.addLast("StringEncoder",new StringEncoder());
        pipeline.addLast("handler",new TestServerHandler());
    }
}

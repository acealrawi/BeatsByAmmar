package TestClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * Created by Gebruiker on 5/11/2017.
 */
public class TestClientInit extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast("ProtobufEncode", new ProtobufEncoder());
        pipeline.addLast("StringDecoder",new StringDecoder());
        pipeline.addLast("StringEncoder",new StringEncoder());
        pipeline.addLast("handler",new TestClientHandlerT());
    }
}

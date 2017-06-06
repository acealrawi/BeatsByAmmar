package beatsClient;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import proto.ProtoMessageOuterClass.ProtoMessage;


/**
 * Created by Gebruiker on 5/11/2017.
 */
public class TestClientInit extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("ProtoEncoder", new ProtobufEncoder());
        pipeline.addLast("ProtoDEcoder", new ProtobufDecoder(ProtoMessage.getDefaultInstance()));
        pipeline.addLast("ProtoHandler", new TestClientProtoHandler());
//        pipeline.addLast("decoder",new StringDecoder());
//        pipeline.addLast("encoder", new StringEncoder());
//        pipeline.addLast("handler", new TestServerStringHandler());
    }
}

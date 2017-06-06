package beatsServer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import proto.ProtoMessageOuterClass;


/**
 * Created by Gebruiker on 5/12/2017.
 */
public class TestServerInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("decoder",new StringDecoder());
//        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("ProtoMessageDecoder", new ProtobufDecoder(ProtoMessageOuterClass.ProtoMessage.getDefaultInstance()));
        pipeline.addLast("ProtoMessageEncoder", new ProtobufEncoder());
//        pipeline.addLast("string",new TestServerStringHandler());
        pipeline.addLast("Protohandler",new TestServerProtoHandler());


    }
}

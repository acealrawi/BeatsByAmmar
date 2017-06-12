package beatsServer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import proto.ProtoMessageOuterClass;


/**
 * Created by Gebruiker on 5/12/2017.
 */
public class TestServerInit extends ChannelInitializer<SocketChannel> {
    /**
     * In this class we add all the handlers and decoders that are going to be used by the server.
     *
     * @param ch
     * @throws Exception
     */

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("ProtoMessageEncoder", new ProtobufEncoder());// Encode outgoing protobuf messages to byteBuf
        pipeline.addLast("ByteArrayDecoder", new ByteArrayDecoder()); // Decode incoming byteBuf to byte array
        pipeline.addLast("ByteArrayHandler", new ByteArrayHandler()); // handles incoming byte arrays en outgoing protobuf messages



    }
}

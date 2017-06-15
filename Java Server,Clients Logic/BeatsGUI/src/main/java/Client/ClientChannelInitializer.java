package Client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import proto.ProtoMessageOuterClass.ProtoMessage;

/**
 * Created by Gebruiker on 5/23/2017.
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline().addLast("ByteArrayEncoder", new ByteArrayEncoder());// encode outgoing ByteArray data
        socketChannel.pipeline().addLast("protoDecoder",new ProtobufDecoder(ProtoMessage.getDefaultInstance())); //decode incoming protobuf messages
        socketChannel.pipeline().addLast("clientHandler",new ClientProtoHandler());// handles incoming protobuf messages.

    }
}

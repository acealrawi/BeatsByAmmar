package Client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import proto.ProtoMessageOuterClass.ProtoMessage;

/**
 * Created by Gebruiker on 5/23/2017.
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("protoEncoder",new ProtobufEncoder());
        socketChannel.pipeline().addLast("protoDecoder",new ProtobufDecoder(ProtoMessage.getDefaultInstance()));
        socketChannel.pipeline().addLast("clientHandler",new ClientProtoHandler());
    }
}

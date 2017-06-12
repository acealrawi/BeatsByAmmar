package beatsServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import proto.ProtoMessageOuterClass;

import java.util.List;

/**
 * Created by Gebruiker on 6/8/2017.
 */
public class DynamicHandler extends ByteToMessageDecoder {
//    ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    Channel clientChannel ;
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {

        final int magic1 = in.getUnsignedByte(in.readerIndex());
        if (isProto(magic1)) {
            clientChannel = ctx.channel();

            switchToProto(ctx);
        }
        else {
            // Unknown protocol; discard everything and close the connection.
            switchToString(ctx);
        }
    }
    private boolean isProto(int magic1) {
        return magic1 == 10;
    }

    private void switchToProto(ChannelHandlerContext ctx){
        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.addLast("ProtoMessageDecoder", new ProtobufDecoder(ProtoMessageOuterClass.ProtoMessage.getDefaultInstance()));
        pipeline.addLast("ProtoMessageEncoder", new ProtobufEncoder());
        pipeline.addLast("Protohandler",new TestServerProtoHandler());
        pipeline.remove(this);

    }
    private void switchToString(ChannelHandlerContext ctx){
        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("string",new TestServerStringHandler(clientChannel));
        pipeline.remove(this);
    }
}

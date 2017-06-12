package beatsServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import proto.ProtoMessageOuterClass.ProtoMessage;

/**
 * Created by Gebruiker on 6/8/2017.
 */
public class ByteBufHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("i came");


        ByteBuf in = (ByteBuf) msg;
        try {


            //System.out.println((char) in.readByte());
//            System.out.println((char) in.readByte());
//            System.out.println(in.array().toString());


            System.out.println(in.getUnsignedByte(in.readerIndex()));
            System.out.println(in.getUnsignedByte(in.readerIndex()+1));

           // System.out.flush();

        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }
}

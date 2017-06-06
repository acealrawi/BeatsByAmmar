package TestClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Gebruiker on 5/13/2017.
 */
public class TestClientHandlerT extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        ctx.writeAndFlush("xcd");
    }
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        ctx.writeAndFlush("ctx");
    }
}

package com.netty4;


import com.netty4.entity.CmppActiveTest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;



/**
 * 心跳Handler
 * @author weixiang
 * @date 2018/8/2 15:37
 */
public class HeartHandler extends ChannelInboundHandlerAdapter {



    private NettyClient client;
    public HeartHandler(NettyClient client){
        this.client=client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("初始化创建连接。。。");
        super.channelActive(ctx);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    	/**
    	 * Netty 的超时类型 IdleState 主要分为：
		   ALL_IDLE : 一段时间内没有数据接收或者发送
		   READER_IDLE ： 一段时间内没有数据接收
		   WRITER_IDLE ： 一段时间内没有数据发送
    	 */
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			IdleState state = event.state();
			if (state == IdleState.WRITER_IDLE || state == IdleState.ALL_IDLE) {
				client.submit(new CmppActiveTest());
				System.out.println("心跳启动!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}

	  }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("掉线了~~~~~~ 开启重连机制");
        //默认重连3次
        client.reConnect(3);
    }
}

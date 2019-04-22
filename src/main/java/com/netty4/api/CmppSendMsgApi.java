package com.netty4.api;

import java.util.logging.Logger;

import com.netty4.NettyClient;
import com.netty4.Utils.Command;
import com.netty4.Utils.MsgUtils;
import com.netty4.entity.CmppConnectResp;
import com.netty4.entity.CmppMessageHeader;
import com.netty4.entity.CmppSubmit;

public class CmppSendMsgApi {
	
	private static final Logger logger = Logger.getLogger(CmppSendMsgApi.class.getName());

	private NettyClient nettyClient;

	// ip 做配置项
	public  String host = "10.154.164.93";
	// 端口 做配置项
	public  int port = 7890;
	// 账号 做配置项
	public  String serviceId = "918622";
	// 密码 做配置项
	public  String pwd = "HNiptv1@";
	// 源号码 SP的服务代码或前缀为服务代码的长号码 做配置项
	public String srcId = "10658498";
	

	public void connnect(){
		logger.info("**********开始连接短信网关**********");
		nettyClient = new NettyClient(host, port, serviceId, pwd);
		nettyClient.start();
		if(CmppConnectResp.connectFlag){
			logger.info("**********短信网关认证连接成功**********");
        }else{
        	logger.info("**********短信网关认证连接失败**********");
        }
	}
	
	/**
	 * 发送短信
	 * @param msg 短信内容
	 * @return
	 */
	public boolean sendMsg(String mobilePhone,String msg){
		boolean sendFlag = false;
		if(CmppConnectResp.connectFlag){
			int sequenceId = MsgUtils.getSequence();

			CmppMessageHeader submit = new CmppSubmit(Command.CMPP2_VERSION, serviceId, srcId, sequenceId, mobilePhone,
					msg);

			sendFlag = nettyClient.submit(submit);

			if (sendFlag) {
				logger.info("**********短信发送给短信网关成功**********");
			} else {
				logger.info("**********短信发送给短信网关失败**********");
			}
		}else{
			logger.info("**********短信网关认证连接失败,无法发送短信**********");
		}
		return sendFlag;
	}
	
//	public static void main(String[] args) {
//
//		System.out.println("**********开始连接短信网关**********");
//
//		NettyClient client = new NettyClient(host, port, serviceId, pwd);
//
//		boolean connectFlag = client.start();
//
//		if (connectFlag) {
//			// 连接正常
//			int i = 0;
//
//			while (true) {
//				String mobile = "18374815562";
//
//				String content = "第" + (i + 1) + "次的CMPP验证码8888";
//
//				System.out.println("**********第" + (i + 1) + "次向手机号:" + mobile + "发送内容:" + content + "**********");
//
//				int sequenceId = MsgUtils.getSequence();
//
//				CmppMessageHeader submit = new CmppSubmit(Command.CMPP2_VERSION, serviceId, srcId, sequenceId, mobile,
//						content);
//
//				boolean sendFlag = client.submit(submit);
//
//				if (sendFlag) {
//					System.out.println("**********短信发送成功成功**********");
//				} else {
//					System.out.println("**********短信发送成功失败**********");
//				}
//				i++;
//				try {
//					Thread.sleep(30000);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}

}

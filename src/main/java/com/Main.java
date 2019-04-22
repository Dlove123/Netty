package com;

import com.netty4.NettyClient;
import com.netty4.Utils.Command;
import com.netty4.Utils.MsgUtils;
import com.netty4.entity.CmppMessageHeader;
import com.netty4.entity.CmppSubmit;

/**
 * @author weixiang
 * @date 2018/8/6 16:31
 */
public class Main {

	// ip 做配置项
	public static String host = "10.154.164.93";
	// 端口 做配置项
	public static int port = 7890;
	// 账号 做配置项
	public static String serviceId = "918622";
	// 密码 做配置项
	public static String pwd = "HNiptv1@";

	/**
	 * 做配置项 显示用户在短信上来源号码 源号码 SP的服务代码或前缀为服务代码的长号码,
	 * 网关将该号码完整的填到SMPP协议Submit_SM消息相应的source_addr字段， 该号码最终在用户手机上显示为短消息的主叫号码
	 */
	public static String srcId = "10658498";

	public static void main(String[] args) {
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
	}
}

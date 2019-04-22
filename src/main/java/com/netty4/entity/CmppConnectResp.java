package com.netty4.entity;

import org.apache.commons.lang3.ArrayUtils;

import com.netty4.Utils.MsgUtils;

/**
 * DL
 * @author Administrator
 * 封装连接回执的实体类
 */
public class CmppConnectResp {

	private String status;

	private String authenticatorIsmg;
	
	private String version;
	
	//true:认证通过  false:认证失败
	public static boolean connectFlag = false;

	public String getStatus() {
		return status;
	}

	public String getAuthenticatorIsmg() {
		return authenticatorIsmg;
	}

	public String getVersion() {
		return version;
	}
	
	public CmppConnectResp(byte[] bytes){
		//当前未解析字段 先暂且认为是认证成功的,等后续需要再解析字段判断
		connectFlag = true;
		//转换成字符串
//		String connectResp = new String(bytes);
//		System.out.println("**********服务端的认证回执:"+connectResp+"**********");
//        this.msgId= MsgUtils.bytesToInt(ArrayUtils.subarray(bytes, 8, 12));
//        this.msgId2=MsgUtils.bytesToLong(ArrayUtils.subarray(bytes, 12, 20));
//        this.state=bytes[20];
    }
}

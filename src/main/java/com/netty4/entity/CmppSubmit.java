package com.netty4.entity;

import com.netty4.Utils.Command;
import com.netty4.Utils.MsgUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;

/**
 * @author weixiang
 * @date 2018/8/2 10:28
 */
public class CmppSubmit extends CmppMessageHeader {

    int msgId = 0;

    /**
     * 相同Msg_Id的信息总条数，从1开始
     */
    byte pkTotal = 1;

    /**
     * 相同Msg_Id的信息序号，从1开始
     */
    byte pkNumber = 1;

    /**
     * 是否要求返回状态确认报告：
		0：不需要
		1：需要
		2：产生SMC话单
		 （该类型短信仅供网关计费使用，不发送给目的终端)
     */
    byte registeredDelivery = 1;

    /**
     * 信息级别
     */
    byte msgLevel = 0;

    String serviceId;// 10位长度

    /**
     * 计费用户类型字段
	0：对目的终端MSISDN计费；
	1：对源终端MSISDN计费；
	2：对SP计费;
	3：表示本字段无效，对谁计费参见Fee_terminal_Id字段。
     */
    byte feeUserType = 2;

    String feeTerminalId;// 21位长度

    byte feeTerminalType = 0;

    byte tp_pid = 0;

    byte tp_udhi = 0;

    /**
     0：ASCII串
     3：短信写卡操作
     4：二进制信息
     8：UCS2编码
     15：含GB汉字
     */
    byte msgFmt = 8;

    String msgSrc;// 6位长度

    /**
     * 01:对“计费用户号码”免费
      02：对“计费用户号码”按条计信息费
      03：对“计费用户号码”按包月收取信息费
      04：对“计费用户号码”的信息费封顶
      05：对“计费用户号码”的收费是由SP实现
     */
    String feeType;// 2位长度

    String feeCode;// 6位长度

    String vaildTime = "";// 17位长度

    String atTime = "";// 17位长度

    String srcId;// 21位长度

    byte destUsrTl = 1;

    String destTerminalId;// 21位长度

    byte destTerminalType = 0;

    byte msgLength; // 1位长度

    byte[] msgContent;

    String linkId = "";// 保留字

    private int terminalIdLen;
    private int linkIdLen;
    private int submitExpMsgLen;

    public CmppSubmit(byte version, String serviceId, String srcId, int SequenceId,String mobile,String content) {
        super(Command.CMPP_SUBMIT, version);
        if (version == Command.CMPP2_VERSION) {
            terminalIdLen = 21;
            linkIdLen = 8;
            submitExpMsgLen = Command.CMPP2_SUBMIT_LEN_EXPMSGLEN;
        } else {
            terminalIdLen = 32;
            linkIdLen = 20;
            submitExpMsgLen = Command.CMPP3_SUBMIT_LEN_EXPMSGLEN;
        }

        this.serviceId = serviceId;
        this.feeTerminalId = "86"+mobile;
        this.feeType = "01";
        this.feeCode = "000000";
        //this.srcId = srcId+"1630";
        this.srcId = srcId;
        this.linkId = "";
        try {
            msgContent=content.getBytes("UTF-16BE");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.msgId=SequenceId;
        this.sequenceId=this.msgId;
        this.destTerminalId=mobile;

    }


    @Override
    public byte[] toByteArray(){
    	
        ByteBuf buf = Unpooled.buffer(130 + 8 + ((byte) 1) * 21 + msgContent.length);
        buf.writeInt(130 + 8 + ((byte) 1) * 21 + msgContent.length);
        buf.writeInt(commandId);
        buf.writeInt(msgId);
        buf.writeLong(0);
        buf.writeByte(pkTotal);
        buf.writeByte(pkNumber);
        buf.writeByte(registeredDelivery);
        buf.writeByte(msgLevel);
        buf.writeBytes(MsgUtils.getLenBytes(serviceId, 10));
        buf.writeByte(feeUserType);
        buf.writeBytes(MsgUtils.getLenBytes(feeTerminalId, 21));
        buf.writeByte(tp_pid);
        buf.writeByte(tp_udhi);
        msgFmt = 8;
        buf.writeByte(msgFmt);

        buf.writeBytes(MsgUtils.getLenBytes(serviceId, 6));
        buf.writeBytes(MsgUtils.getLenBytes(feeType, 2));
        buf.writeBytes(MsgUtils.getLenBytes(feeCode, 6));
        buf.writeBytes(MsgUtils.getLenBytes(vaildTime, 17));
        buf.writeBytes(MsgUtils.getLenBytes(atTime, 17));
        buf.writeBytes(MsgUtils.getLenBytes(srcId, 21));
        buf.writeByte(destUsrTl);
        buf.writeBytes(MsgUtils.getLenBytes(destTerminalId, 21));
        buf.writeByte(msgContent.length);
        buf.writeBytes(msgContent);
        buf.writeLong((long) 0);

        return buf.array();
    }
    
    public static void main(String[] args) {
        byte msgFmt = 8;
        System.out.println(msgFmt);
	}
}

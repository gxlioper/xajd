package com.zfsoft.ms.sms;

import com.zfsoft.ms.MessageBody;
import com.zfsoft.ms.MessageOutputProvider;
import com.zfsoft.ms.sms.factory.SmsClientFactory;
import xgxt.action.Base;

public class SMSMessageOutputProvider implements MessageOutputProvider{

    @Override
    public boolean output(MessageBody data) throws Exception {
        if (data.getBody().get("sendTo") == null) {
            throw new IllegalArgumentException("短信发送参数: sendTo not found. ");
        } else if (data.getBody().get("content") == null) {
            throw new IllegalArgumentException("短信发送参数: content not found. ");
        } else if(Base.getInitProperties().get("smsProvider") == null){
            throw new IllegalArgumentException("短信发送接口供应商: smsProvider not found. ");
        } else {
            String sendTo = (String)data.getBody().get("sendTo");
            String content = (String)data.getBody().get("content");

            String smsProvider = Base.getInitProperties().get("smsProvider");
            SmsClient client = SmsClientFactory.getClient(smsProvider);
            return client == null ? false : client.send(sendTo,content);
        }
    }
}

package com.zfsoft.ms.sms;

public interface SmsClient {

    public boolean send(String content, String mobile);

//    public SmsPropertiesProvider getPropsProvider();
//
//    public void setPropsProvider(SmsPropertiesProvider propsProvider);

}

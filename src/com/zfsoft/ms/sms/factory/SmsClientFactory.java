package com.zfsoft.ms.sms.factory;

import com.zfsoft.ms.sms.SmsClient;
import com.zfsoft.ms.sms.def.Baidu106SmsClient;
import com.zfsoft.ms.sms.def.GreenTownSmsClient;
import com.zfsoft.ms.sms.def.ZdtSmsClient;

public class SmsClientFactory {

    public static SmsClient getClient(String smsProvider){

        if("baidu106".equals(smsProvider)){
            return new Baidu106SmsClient();
        } else if("greenTown".equals(smsProvider)){
            return new GreenTownSmsClient();
        } else if("zdt".equals(smsProvider)) {
            return new ZdtSmsClient();
        } else {
            return null;
        }
    }
}

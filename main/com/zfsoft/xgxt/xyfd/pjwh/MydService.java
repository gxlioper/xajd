package com.zfsoft.xgxt.xyfd.pjwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

import java.util.HashMap;

/**
 * Created by llf on 2019/8/17.
 */
public class MydService extends SuperServiceImpl<MydForm,MydDao> {

    public HashMap<String,String> getPjxx(MydForm t) throws Exception{
        return dao.getPjxx(t);
    }

    public HashMap<String,String> getJlxx(MydForm t) throws Exception{
        return dao.getJlxx(t);
    }
    @TransactionControl
    public boolean savePj(MydForm t) throws Exception{
        boolean result = dao.savePj(t);
        if(result){
            if(t.getLx().equals("fd")){ //�γ̸���
                result = dao.updateFdjl(t.getJlbh());
            }else if(t.getLx().equals("zx")){ //ѧҵ��רҵ��ѯ
                result = dao.updateZxjl(t.getJlbh());
            }
        }
        return result;
    }
}

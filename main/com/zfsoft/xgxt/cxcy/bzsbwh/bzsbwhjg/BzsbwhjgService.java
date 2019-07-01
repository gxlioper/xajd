package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import net.sf.json.JSONObject;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-06 10:58
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BzsbwhjgService extends SuperServiceImpl<BzsbwhjgForm,BzsbwhjgDao> {
    private static final String MESSAGE_REPEAT = "ͬһѧ��ѧ����Ŀ���Ʋ����ظ���";

    public boolean save(BzsbwhjgForm model, User user) throws Exception{
        boolean rs = true;
        if(dao.checkExistForSave(model)){
            JSONObject json = new JSONObject();
            json.put("message", MESSAGE_REPEAT);
            throw new SystemException(json);
        }
        if(StringUtils.isNotNull(model.getJgid())){
            rs = dao.runUpdate(model);
        }else{
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            model.setSjly("0");
            rs = dao.runInsert(model);
        }
        return rs;
    }

    public HashMap<String,String> getInfoById(String jgid) {
        return dao.getInfoById(jgid);
    }
}

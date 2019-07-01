package com.zfsoft.xgxt.cxcy.sbwh.jzsb;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import net.sf.json.JSONObject;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;

/**
 * @�๦������:���´�ҵ-�ϱ�ά��-�����ϱ�
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-07 09:13
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JzsbService extends SuperServiceImpl<JzsbForm,JzsbDao> {
    private static final String MESSAGE_REPEAT = "ͬһʱ������������н������ݣ�";

    public boolean save(JzsbForm model, User user) throws Exception {
        boolean rs = true;
        if(dao.checkExistForSave(model)){
            JSONObject json = new JSONObject();
            json.put("message", MESSAGE_REPEAT);
            throw new SystemException(json);
        }
        if(StringUtils.isNotNull(model.getId())){
            rs = dao.runUpdate(model);
        }else{
            model.setTbr(user.getUserName());
            model.setTbsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            rs = dao.runInsert(model);
        }
        return rs;
    }

    public HashMap<String,String> getInfoById(String id) {
        return dao.getInfoById(id);
    }
}

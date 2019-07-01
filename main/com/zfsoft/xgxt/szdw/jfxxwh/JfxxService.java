package com.zfsoft.xgxt.szdw.jfxxwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import org.springframework.util.CollectionUtils;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class JfxxService extends SuperServiceImpl<JfxxForm,JfxxDao> {
    public List<HashMap<String,String>> jfcygxList(){
        return dao.jfcygxList();
    }

    public boolean saveAdd(JfxxForm t) throws Exception {
        t.setJgid(UniqID.getInstance().getUniqIDHash());
        if(!CollectionUtils.isEmpty(t.getJfcyxx())){
            dao.saveCy(t);
        }

        return dao.runInsert(t);
    }
    public boolean saveUpdate(JfxxForm t) throws Exception {
        if(!CollectionUtils.isEmpty(t.getJfcyxx())){
            dao.saveCy(t);
        }
        return dao.runUpdate(t);
    }

    public List<HashMap<String,String>> getCyList(String jgid){
        return dao.getCyList(jgid);
    }

    public List<HashMap<String,String>> selectXs(JfxxForm t,User user) throws Exception {
        return dao.selectXs(t,user);
    }
}

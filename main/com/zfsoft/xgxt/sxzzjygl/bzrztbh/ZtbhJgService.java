package com.zfsoft.xgxt.sxzzjygl.bzrztbh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import org.springframework.util.CollectionUtils;
import xgxt.action.Base;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class ZtbhJgService extends SuperServiceImpl<ZtbhJgForm,ZtbhJgDao> {
    public boolean ztbhJgSave(ZtbhJgForm model) throws Exception {
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        model.setJgid(UniqID.getInstance().getUniqIDHash());
        dao.saveBj(model);
        return dao.runInsert(model);
    }
    public String yz(ZtbhJgForm model){
        List<CjbJxxForm> bjxxs = model.getBjxxs();
        if(CollectionUtils.isEmpty(bjxxs)){
            return "����д�༶��Ϣ��";
        }
        for(CjbJxxForm i : bjxxs){
            if(StringUtil.isNull(i.getBjdm())){
                return "��ѡ��༶��";
            }
            if(StringUtil.isNull(i.getYdrs())){
                return "������Ӧ������";
            }
            if(StringUtil.isNull(i.getSdrs())){
                return "������ʵ������";
            }
            if(StringUtil.isNull(i.getQqrs())){
                return "������ȱ������";
            }
        }
        return "true";
    }
    public boolean update(ZtbhJgForm model)throws Exception {
        dao.saveBj(model);
        return dao.runUpdateTrim(model);
    }
    public List<HashMap<String,String>> getXxList(ZtbhJgForm model, User user) throws Exception {
        return dao.getXxList(model,user);
    }

    public List<HashMap<String,String>> getBjList(ZtbhJgForm model, User user) throws Exception {
        return dao.getBjList(model,user);
    }

    public String[] getHdfzr(ZtbhJgForm model) {
        return dao.getHdfzr(model);
    }

    public String[] getBjmc(ZtbhJgForm model) {
        return dao.getBjmc(model);
    }

    public List<HashMap<String,String>> getDCList(ZtbhJgForm model, User user) throws Exception {
        return dao.getDCList(model,user);
    }
    public HashMap<String,String> getBjxx(String bjdm){
        return dao.getBjxx(bjdm);
    }

    public List<HashMap<String,String>> getBjxxByJgid(String jgid){
        return dao.getBjxxByJgid(jgid);
    }

    public boolean delBj(String[] jgids) throws Exception {
        return dao.delBj(jgids);
    }
}

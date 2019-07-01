package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.bbzc.XqbdzcAction;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.util.CollectionUtils;
import xgxt.action.Base;
import xgxt.form.User;

import java.io.File;
import java.util.ArrayList;
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
            return "请填写班级信息！";
        }
        for(CjbJxxForm i : bjxxs){
            if(StringUtil.isNull(i.getBjdm())){
                return "请选择班级！";
            }
            if(StringUtil.isNull(i.getYdrs())){
                return "请输入应到人数";
            }
            if(StringUtil.isNull(i.getSdrs())){
                return "请输入实到人数";
            }
            if(StringUtil.isNull(i.getQqrs())){
                return "请输入缺勤人数";
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

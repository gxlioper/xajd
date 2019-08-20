package com.zfsoft.xgxt.xyfd.xyzyzxwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsDao;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsForm;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgDao;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgForm;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/8/14.
 */
public class XyzyzxService extends SuperServiceImpl<XyzyzxForm,XyzyzxDao> {
    private FdjsDao fdjsDao = new FdjsDao();
    private PbjgDao pbjgDao = new PbjgDao();

    public List<HashMap<String,String>> getZxyyList() throws Exception{
        return dao.getZxyyList();
    }

    public HashMap<String,String> getFdjs(User user) throws Exception{
        if(user.getUserType().equals("stu")){
            PbjgForm pbjgForm = new PbjgForm();
            pbjgForm.setXh(user.getUserName());
            return pbjgDao.getPb(pbjgForm);
        }else {
            FdjsForm fdjsForm = new FdjsForm();
            fdjsForm.setZgh(user.getUserName());
            return fdjsDao.getTea(fdjsForm);
        }
    }
    public String[] deleteZxjl(String[] ids) throws Exception {
        List<String> delId=new ArrayList<String>();//可删除的id集合
        StringBuffer noDel = new StringBuffer();
        boolean isHaveNoId = false;
        if(null==ids||ids.length<=0){
            return null;
        }
        for(String str:ids){
            if(true){
                delId.add(str);//记录删除id
            }else{
                noDel.append(str);
                noDel.append(",</br>");
                isHaveNoId=true;
            }
        }
        int i=delId.size()>0?runDelete(ids):0;

        String str=noDel.toString();
        //去除最后多余逗号
        str=isHaveNoId?str:"-1";
        return new String[]{String.valueOf(i),str};
    }
    public HashMap<String,String> getZxjlxx(XyzyzxForm t) throws Exception{
        return dao.getZxjlxx(t);
    }

    public List<HashMap<String,String>> getFdjsList(XyzyzxForm t,User user) throws Exception{
        return dao.getFdjsList(t,user);
    }
}

package com.zfsoft.xgxt.xyfd.yytj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyForm;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/22.
 */
public class YytjService extends SuperServiceImpl<FdyyForm,YytjDao> {

    public List<HashMap<String,String>> getYytjList(FdyyForm t,User user) throws Exception{
        if(t.getCxmb().equals("jspj")){
            return dao.getJspjList(t,user);
        }else if (t.getCxmb().equals("yyqx")){
            return dao.getYyqxList(t,user);
        }else {
            return dao.getPageList(t,user);
        }
    }

    public HashMap<String,String> getPjtj(String djh) throws Exception{
        if(djh.startsWith("JS")){
            return dao.getJspjxx(djh);
        }else {
            return dao.getPbpjxx(djh);
        }
    }

    public List<HashMap<String,String>> getPjList(String djh) throws Exception{
        return dao.getPjList(djh);
    }

    public List<HashMap<String,String>> getXjpftj(String djh) throws Exception{
        return dao.getXjpftj(djh);
    }

    public HashMap<String,String> getJsqxxx(String djh) throws Exception{
        return dao.getJsqxxx(djh);
    }

    public List<HashMap<String,String>> getQxjl(String djh) throws Exception{
        return dao.getQxjl(djh);
    }

    public List<HashMap<String,String>> getQxyytj(String djh) throws Exception{
        return dao.getQxyytj(djh);
    }

}

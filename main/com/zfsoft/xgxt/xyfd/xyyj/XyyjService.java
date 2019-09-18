package com.zfsoft.xgxt.xyfd.xyyj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/9/10.
 */
public class XyyjService extends SuperServiceImpl<XyyjForm,XyyjDao> {

    public List<HashMap<String,String>> getXscjList(XyyjForm t, User user) throws Exception{
        return dao.getXscjList(t,user);
    }

    public List<HashMap<String,String>> xscjfb(XyyjForm t) throws Exception{
        return dao.xscjfb(t);
    }

    public List<HashMap<String,String>> xfcjqs(XyyjForm t) throws Exception{
        return dao.xfcjqs(t);
    }

    public List<HashMap<String,String>> kccj(XyyjForm t,User user) throws Exception{
        return dao.kccj(t,user);
    }

    public HashMap<String,String> getXsxx(XyyjForm t) throws Exception{
        return dao.getXsxx(t);
    }

    public List<HashMap<String,String>> kccjqs(XyyjForm t) throws Exception{
        return dao.kccjqs(t);
    }

    public List<HashMap<String,String>> zycj(XyyjForm t,User user) throws Exception{
        return dao.zycj(t,user);
    }

    public List<HashMap<String,String>> zycjqs(XyyjForm t) throws Exception{
        return dao.zycjqs(t);
    }

}

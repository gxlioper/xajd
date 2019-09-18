package com.zfsoft.xgxt.xyfd.yjgywh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/9/17.
 */
public class YjgyService extends SuperServiceImpl<YjgyForm,YjgyDao> {

    public HashMap<String,String> getXnxq() throws Exception{
        return dao.getXnxq();
    }

    public List<HashMap<String,String>> selectJs(YjgyForm t) throws Exception{
        return dao.selectJs(t);
    }

    public HashMap<String,String> getYjzjjl(YjgyForm t) throws Exception{
        return dao.getYjzjjl(t);
    }

    public boolean saveZjjl(YjgyForm t) throws Exception{
        HashMap<String,String> xnxq = dao.getXnxq();
        t.setXn(xnxq.get("xn"));
        t.setXq(xnxq.get("xq"));
        HashMap<String,String> yjzjxx = dao.getYjzjjl(t);
        if(yjzjxx.size()==0){//该学年学期没有预警转介
            return dao.runInsert(t);
        }else {
            t.setId(yjzjxx.get("id"));
            return dao.runUpdate(t);
        }
    }
}

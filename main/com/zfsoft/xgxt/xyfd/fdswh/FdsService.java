package com.zfsoft.xgxt.xyfd.fdswh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/6/24.
 */
public class FdsService extends SuperServiceImpl<FdsForm,FdsDao> {

    public boolean saveFds(FdsForm t) throws Exception{
        return dao.saveFds(t);
    }

    public boolean updateFds(FdsForm t) throws Exception{
        return dao.updateFds(t);
    }

    public HashMap<String,String> getFds(FdsForm t) throws Exception{
        return dao.getFds(t);
    }

    public String[] deleteFds(String[] ids) throws Exception {
        List<String> delId=new ArrayList<String>();//可删除的id集合
        StringBuffer noDel = new StringBuffer();
        boolean isHaveNoId = false;
        if(null==ids||ids.length<=0){
            return null;
        }
        for(String str:ids){
            if(dao.isCanDel(str)){
                delId.add(str);//记录删除id
            }else{
                HashMap<String, String> hm=dao.getFds(str);
                noDel.append(hm.get("fdsmc"));
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
}

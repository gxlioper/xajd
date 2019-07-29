package com.zfsoft.xgxt.xyfd.fdjswh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by llf on 2019/6/24.
 */
public class FdjsService extends SuperServiceImpl<FdjsForm, FdjsDao> {

    public boolean saveFds(FdjsForm t) throws Exception{
        String maxDjh = dao.getDjh();
        int num = 0;
        String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
        if(StringUtils.isNotEmpty(maxDjh)&&maxDjh.substring(2,4).equals(yearLast)){
            num = Integer.parseInt(maxDjh.substring(2,maxDjh.length()))+1;
        }else {
            num = Integer.parseInt(yearLast+"0001");
        }
        t.setDjh("JS"+num);
        return dao.saveFdjs(t);
    }

    public boolean updateFdjs(FdjsForm t) throws Exception{
        return dao.updateFdjs(t);
    }

    public HashMap<String,String> getFdjs(FdjsForm t) throws Exception{
        return dao.getFdjs(t);
    }
    public HashMap<String,String> getTea(FdjsForm t) throws Exception{
        return dao.getTea(t);
    }

    public String[] deleteFdjs(String[] ids) throws Exception {
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

    public List<HashMap<String, String>> getAllTeacher(FdjsForm t) throws Exception{
        return dao.getAllTeacher(t);
    }

    public List<HashMap<String,String>> getKxFds(FdjsForm t) throws Exception{
        return dao.getKxFds(t);
    }
}

package com.zfsoft.xgxt.hdgl.hdqd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxDao;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class HdqdService extends SuperServiceImpl<HdqdForm,HdqdDao> {

    public boolean save(HdqdForm t) throws Exception {
        boolean result = false;
        if(!StringUtil.isNull(t.getDwid())&&!StringUtil.isNull(t.getDzxm())){
            t.setDwzw("0");//队员
            result = dao.save(t);
        }else{
            //需要创建队伍
            //现获取队伍id
            HdxxDao hdxxDao = new HdxxDao();
            String dwid = String.valueOf(hdxxDao.getDws(t.getHdid())+1);
            //排除重复id
            while (dao.isExistDw(t,dwid)){
                Integer id = Integer.parseInt(dwid);
                id++;
                dwid = id.toString();
            }
            t.setDwid(dwid);
            t.setDwzw("1");//队长
            result = dao.save(t);
        }
        if(result){
            if(!isExistqd(t)){
                result = dao.insertQd(t);
            }
        }
        return result;
    }
    public boolean isExist(HdqdForm t){
        return dao.isExist(t);
    }
    public HashMap<String,String> getHdqd(HdqdForm t) throws Exception{
        return dao.getHdqd(t);
    }

    public boolean isExistqd(HdqdForm t){
        return dao.isExistqd(t);
    }

    public HashMap<String,String> getDwxx(HdqdForm t) throws Exception{
        return dao.getDwxx(t);
    }
    public boolean modifyQd(HdqdForm t) throws Exception{
        boolean result = false;
        if(isExistqd(t)){
            result = dao.updateQd(t);
        }else {
            result = dao.insertQd(t);
        }
        return result;
    }

    public HashMap<String,Object> del(HdqdForm t) throws Exception{
        HashMap<String,Object> msg = new HashMap<>();
        //组队活动
        if("0".equals(t.getBmlx())){
            HashMap<String,String> hdqd = dao.isDz(t);
            BeanUtils.copyProperties(t,hdqd);
            if("1".equals(t.getDwzw())){
                //该组是否有其他队员
                if(dao.hasDy(t)){
                    msg.put("result",false);
                    msg.put("error","学号"+t.getXh()+"的学生为所处队伍队长，队伍存在其他队员，请先删除其他队员");
                    return msg;
                }
            }
        }
        if(dao.delqdxx(t)){
            msg.put("result",dao.delqdhdxx(t));
        }else {
            msg.put("result",false);
        }
        msg.put("error","");
        return msg;
    }

}

package com.zfsoft.xgxt.hdgl.hdqd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxDao;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class HdqdService extends SuperServiceImpl<HdqdForm,HdqdDao> {

    public boolean save(HdqdForm t) throws Exception {
        boolean result = false;
        if(!StringUtil.isNull(t.getDwid())&&!StringUtil.isNull(t.getDzxm())){
            t.setDwzw("0");//��Ա
            result = dao.save(t);
        }else{
            //��Ҫ��������
            //�ֻ�ȡ����id
            HdxxDao hdxxDao = new HdxxDao();
            String dwid = String.valueOf(hdxxDao.getDws(t.getHdid())+1);
            //�ų��ظ�id
            while (dao.isExistDw(t,dwid)){
                Integer id = Integer.parseInt(dwid);
                id++;
                dwid = id.toString();
            }
            t.setDwid(dwid);
            t.setDwzw("1");//�ӳ�
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
        //��ӻ
        if("0".equals(t.getBmlx())){
            HashMap<String,String> hdqd = dao.isDz(t);
            BeanUtils.copyProperties(t,hdqd);
            if("1".equals(t.getDwzw())){
                //�����Ƿ���������Ա
                if(dao.hasDy(t)){
                    msg.put("result",false);
                    msg.put("error","ѧ��"+t.getXh()+"��ѧ��Ϊ��������ӳ����������������Ա������ɾ��������Ա");
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

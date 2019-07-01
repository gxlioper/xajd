package com.zfsoft.xgxt.zhdj.jjfzbfgl;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import xgxt.form.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JjfzbfService extends SuperServiceImpl<JjfzbfForm,JjfzbfDao> {
    public List<HashMap<String,String>> getBfrList(JjfzbfForm model, User user) throws Exception {
        return dao.getBfrList(model,user);
    }

    public List<HashMap<String,String>> getJjfzList(JjfzbfForm model, User user) throws Exception {
        return dao.getJjfzList(model,user);
    }

    public boolean saveJjfzbf(JjfzbfForm model) throws Exception {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //时间
        Date date = new Date();
        String jlsj = sf.format(date);
        model.setJlsj(jlsj);
        model.setZhxgsj(jlsj);
        return dao.runInsert(model);
    }

    public List<HashMap<String, String>> getjjfzInfo(JjfzbfForm myForm) {
        return dao.getjjfzInfo(myForm);
    }

    public List<HashMap<String,String>> getbfrInfo(JjfzbfForm myForm) {
        return dao.getbfrInfo(myForm);
    }

    public boolean updateSava(JjfzbfForm model) throws Exception {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //时间
        Date date = new Date();
        String zhxgsj = sf.format(date);
        model.setZhxgsj(zhxgsj);
        return dao.runUpdate(model);

    }

    public boolean delJjfzbf(JjfzbfForm model) throws Exception {
        //判断该帮扶计划时候存在实施情况
        boolean rs = dao.checkSsqk(model);
        if(!rs){
            throw new SystemException(MessageKey.GZJL_LBGL_NOTDEL);
        }
        return dao.delJjfzbf(model);
    }

    public List<HashMap<String,String>> getZyssList(JjfzbfForm model) {
        return dao.getZyssList(model);
    }

    public List<HashMap<String,String>> getHbList(JjfzbfForm model) {
        return dao.getHbList(model);
    }

    public boolean delSsqk(JjfzbfForm model) throws Exception {
       return dao.delSsqk(model);
    }

    @TransactionControl
    public boolean insertZyss(JjfzbfForm model) throws Exception {
        List<String[]> paraList = new ArrayList<String[]>();
        String bfid = model.getBfid();
        String[] zysss = model.getZysss();
        /**
         * 参数组合
         */
        for (int i = 0; i < zysss.length; i++) {
            String[] zyss=zysss[i].split(",");
            paraList.add(new String[]{bfid,zyss[0],zyss[1],zyss[2],"1"});
        }
        boolean rs = dao.insertZyss(paraList);
        if(!rs){
            throw new SystemException(MessageKey.SYS_SAVE_FAIL);
        }
        return rs;
    }

    @TransactionControl
    public boolean insertHb(JjfzbfForm model) throws Exception {

        List<String[]> paraList = new ArrayList<String[]>();
        String bfid = model.getBfid();
        String[] hbs = model.getHbs();
        /**
         * 参数组合
         */
        for (int i = 0; i < hbs.length; i++) {
            String[] hb=hbs[i].split(",");
            paraList.add(new String[]{bfid,hb[0],hb[1],hb[2],"2",hb[3]});
        }
        boolean rs = dao.insertHb(paraList);
        if(!rs){
            throw new SystemException(MessageKey.SYS_SAVE_FAIL);
        }
        return rs;
    }

    public List<HashMap<String,String>> getDCList(JjfzbfForm model, User user) throws Exception {
        return dao.getDCList(model,user);
    }
}

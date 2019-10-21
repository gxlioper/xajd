package com.zfsoft.xgxt.xyfd.wfcyywh;

import com.zfsoft.ms.mail.util.StringUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import common.newp.StringUtil;
import xgxt.action.Base;
import xgxt.form.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by llf on 2019/8/6.
 */
public class FdyyService extends SuperServiceImpl<FdyyForm,FdyyDao> {

    public HashMap<String,String> getFdkc(FdyyForm t) throws Exception{
        return dao.getFdkc(t);
    }
    public boolean saveWdyy(FdyyForm t) throws Exception{
        String maxYyh = dao.getMaxYyh();
        String yyqz = new SimpleDateFormat("yyMMdd", Locale.CHINESE).format(Calendar.getInstance().getTime());
        String yyh = "";
        if(StringUtils.isNotEmpty(maxYyh)&&maxYyh.substring(0,6).equals(yyqz)){
            yyh = (Integer.parseInt(maxYyh)+1)+"";
        }else {
            yyh = yyqz+"0001";
        }
        t.setYyh(yyh);
        if(t.getType().equals("submit")){
            t.setZt("5"); //提交预约
        }else {
            t.setZt("0"); //未提交预约
        }
        t.setYytj("0");
        t.setXn(Base.currXn);
        t.setXq(Base.currXq);
        return dao.runInsert(t);
    }
    public boolean isPb(User user) throws Exception{
        return dao.isPb(user);
    }
    public boolean isJs(User user) throws Exception{
        return dao.isJs(user);
    }
    public boolean submit(FdyyForm t) throws Exception{
        t.setZt("5");
        return dao.runUpdate(t);
    }

    /**
     * 确认预约（设置预约状态为待辅导）
     * @param t
     * @return
     * @throws Exception
     */
    public boolean submitYy(FdyyForm t) throws Exception{
        t.setZt("1");
        return dao.runUpdate(t);
    }

    /**
     * 撤销预约（设置为未提交）学生用
     * @param t
     * @return
     * @throws Exception
     */
    public boolean cancel(FdyyForm t) throws Exception{
        t.setZt("0");
        return dao.runUpdate(t);
    }

    /**
     * 取消预约（设置为已取消）辅导教师用
     * @param t
     * @return
     * @throws Exception
     */
    public boolean cancelYy(FdyyForm t) throws Exception{
        t.setZt("3");
        return dao.runUpdate(t);
    }

    /**
     * 取消预约（有取消原因记录）学生取消已确认的预约时使用
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public boolean qxYy(FdyyForm t, User user) throws Exception{
        t.setZt("3");
        if(dao.qxYy(t,user)){
            return dao.runUpdate(t);
        }
        return false;
    }

    public boolean updateWdyy(FdyyForm t) throws Exception{
        FdyyForm model = new FdyyForm();
        model.setYyid(t.getYyid());
        if(t.getType().equals("submit")){
            model.setZt("5"); //提交预约
        }else {
            model.setZt("0"); //未提交预约
        }
        model.setFdsj(t.getFdsj());
        return dao.runUpdate(model);
    }

    public List<HashMap<String,String>> yykcList(FdyyForm t,User user) throws Exception{
        return dao.yykcList(t,user);
    }
    public HashMap<String,String> getFdyyByYyh(FdyyForm t) throws Exception{
        return dao.getFdyyByYyh(t);
    }

    public boolean saveFdjl(FdyyForm t,User user) throws Exception{
        boolean result = dao.saveFdjl(t,user);
        if(result){
            t.setZt("4"); //设为已辅导
            result = dao.updateFdyyByYyh(t);
        }
        return result;
    }

    public HashMap<String,String> getFdjl(FdyyForm t) throws Exception{
        return dao.getFdjl(t);
    }

    public HashMap<String,String> getKcpj(FdyyForm t) throws Exception{
        return dao.getKcpj(t);
    }

    @TransactionControl
    public boolean saveKcpj(FdyyForm t,User user) throws Exception{
        boolean result = false;
        if(!StringUtil.isNull(t.getPjid())){
            result = dao.updateKcpj(t,user);
        }else {
            result = dao.saveKcpj(t,user);
        }
        if(result){
            result = dao.updateFdjl(t.getYyh());
        }
        return result;
    }

    public List<HashMap<String, String>> getMyYyList(FdyyForm t) throws Exception{
        return dao.getMyYyList(t);
    }

    public List<HashMap<String,String>> getQxyyList() throws Exception{
        return dao.getQxyyList();
    }

    public List<HashMap<String,String>> getDclYjList(User user) throws Exception{
        return dao.getDclYjList(user);
    }
}

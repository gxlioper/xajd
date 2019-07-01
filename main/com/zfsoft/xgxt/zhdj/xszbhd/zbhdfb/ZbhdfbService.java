package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述: 西北工业：智慧党校-学生支部活动管理
 * @作者： lgx [工号:1553]
 * @时间： 2018-5-30 上午09:11:30
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdfbService extends SuperServiceImpl<ZbhdfbForm,ZbhdfbDAO>{

    public HashMap<String,String> gethdDetail(ZbhdfbForm model) {

        return  dao.gethdDetail( model);
    }

    public List<HashMap<String, String>> gethdjdDetail(ZbhdfbForm model) {

        return dao.gethdjdDetail( model);
    }
    /**
     * @方法描述: 获取未选择的党支部
     * @作者： lgx [工号:1553]
     * @时间： 2018-6-5 下午07:51:36
     * @版本： V1.0
     * @修改记录: 类修改者-修改日期-修改说明
     */
    public List<HashMap<String,String>> getWxzDzbList(ZbhdfbForm model, User user) throws Exception {
        return dao.getWxzDzbList( model,  user);
    }
    /**
     * @方法描述: 获取已选择的党支部
     * @作者： lgx [工号:1553]
     * @时间： 2018-6-5 下午07:51:36
     * @版本： V1.0
     * @修改记录: 类修改者-修改日期-修改说明
     */
    public List<HashMap<String,String>> getYxzDzbList(ZbhdfbForm model, User user) throws Exception {
        return dao.getYxzDzbList( model,  user);
    }
    /**
     * @描述:
     * @作者：lgx [工号：1553]
     * @日期：2018/6/6 14:21
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: qtdzb判断是否选择全部党支部
     * @return:
     */
    public boolean save(ZbhdfbForm model, boolean qtdzb) throws Exception {
        if("add".equals(model.getType())){
            String guid = UniqID.getInstance().getUniqIDHash();
            model.setHdid(guid);
        }
        boolean rs = dao.save(model);
        if(rs && qtdzb){
            rs = dao.saveQtdzb(model.getHdid());
        }
        return  rs;
    }

    public int checkEdit(ZbhdfbForm model) {
        int rs = 0;
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        try {
            Date now = new Date();
            Date kssj = sf.parse(model.getKssj());
            Date jzsj = sf.parse(model.getJzsj());
            //判断是否在活动时间内
            if(now.getTime() > kssj.getTime() &&
                    now.getTime() < jzsj.getTime()){
                return 2;
            }else{
                String count = dao.getYtj(model);
                if(Integer.parseInt(count) > 0){
                    return 1;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int checkEditforDel(String[] ids) {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        try {
            Date now = new Date();
            for (String hdid : ids) {
                ZbhdfbForm model = dao.getModel(hdid);
                Date kssj = sf.parse(model.getKssj());
                Date jzsj = sf.parse(model.getJzsj());
                //判断是否在活动时间内
                if(now.getTime() > kssj.getTime() &&
                        now.getTime() < jzsj.getTime()){
                    return 2;
                }else{
                    String count = dao.getYtj(model);
                    if(Integer.parseInt(count) > 0){
                        return 1;
                    }
                }
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * @描述:删除参加活动的党支部
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 8:55
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ids]
     * @return: boolean
     */
    public boolean delCjDzb(String[] ids) throws Exception {
        return dao.delCjDzb(ids);
    }

    public List<HashMap<String,String>> getAllDzbList(ZbhdfbForm model, User user) throws Exception {
       return dao.getAllDzbList( model,  user);
    }

    public boolean saveDzb(ZbhdfbForm model, String[] ids) throws SQLException {
        return dao.saveDzb(model,ids);
    }

    public int delDzb(ZbhdfbForm model, String[] ids) throws Exception {
        return dao.delDzb(model,ids);
    }
}

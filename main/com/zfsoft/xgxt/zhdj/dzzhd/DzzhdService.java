package com.zfsoft.xgxt.zhdj.dzzhd;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-党组织生活管理-党组织活动管理
 * @类功能描述: 党团建设活动管理service
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/19 16:02
 */
public class DzzhdService extends SuperServiceImpl<DzzhdForm,DzzhdDao> {

    public DzzhdService(){}
    public static volatile DzzhdService dzzhdService = null;
    public static DzzhdService getDzzhdService(){
        if(dzzhdService == null){
            synchronized (DzzhdService.class){
                if(dzzhdService == null){
                    dzzhdService = new DzzhdService();
                }
            }
        }
        return dzzhdService;
    }

    private DzzhdDao dzzhdDao = DzzhdDao.getDzzhdDao();
    
    /**
     * @描述: 根据活动id获取活动信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/19 16:41
     * @修改记录: 修改人-修改时间-修改描述
     * @param hdId 
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getById(String hdId){
        HashMap<String,String> hdInfo = dzzhdDao.getById(hdId);
        if(hdInfo != null && hdInfo.size() > 0
        && StringUtils.isNotNull(hdInfo.get("mxdx"))){
            hdInfo.put("mxdxmc",getMxdxmc(hdInfo.get("mxdx")));
        }
        return hdInfo;
    }

    /**
     * @描述: 获取我发布的活动信息列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/19 17:21
     * @修改记录: 修改人-修改时间-修改描述
     * @param model
	* @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    public List<HashMap<String,String>> getFbPageList(DzzhdForm model, User user){
        List<HashMap<String,String>> fbPageList =  dzzhdDao.getFbPageList(model,user);
        return joinZzmmmcList(fbPageList);
    }


    /**
     * @描述: 获取政治面貌信息列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/19 17:22
     * @修改记录: 修改人-修改时间-修改描述
     * @param
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    public HashMap<String,String> getZzmmInfo(){
        HashMap<String,String> zzmmInfo = new HashMap<>();
        List<HashMap<String,String>> zzmmList = dzzhdDao.getZzmmList();
        if(zzmmList == null || zzmmList.size() == 0){
            return null;
        }
        for(HashMap<String,String> item : zzmmList){
            zzmmInfo.put(item.get("zzmmdm"),item.get("zzmmmc"));
        }
        return zzmmInfo;
    }
    //获取政治面貌列表
    public List<HashMap<String,String>> getZzmmList(){
        return dzzhdDao.getZzmmList();
    }
    //加入政治面貌信息
    private  List<HashMap<String,String>> joinZzmmmcList( List<HashMap<String,String>> list){
        if(list!= null && list.size() > 0){
            StringBuilder mxdxmc;
            HashMap<String,String> zzmmInfo = getZzmmInfo();
            for(HashMap<String,String> item : list){
                if(StringUtils.isNotNull(item.get("mxdx"))){
                    String[] mxdxs = item.get("mxdx").split(",");
                    mxdxmc = new StringBuilder();
                    for(String zzmmdm : mxdxs){
                        mxdxmc.append(zzmmInfo.get(zzmmdm)+",");
                    }
                    if(mxdxmc.length() > 0 ){
                        item.put("mxdxmc",mxdxmc.substring(0,mxdxmc.length()-1));
                    }
                }
            }
        }
        return list;
    }

    private String getMxdxmc(String mxdxdms){
        HashMap<String,String> zzmmInfo = getZzmmInfo();
        String[] mxdxs = mxdxdms.split(",");
        StringBuilder mxdxmc = new StringBuilder();
        for(String zzmmdm : mxdxs){
            mxdxmc.append(zzmmInfo.get(zzmmdm)+",");
        }
        if(mxdxmc.length() > 0 ){
            return mxdxmc.substring(0,mxdxmc.length()-1);
        }
        return "";
    }

    /**
     * @描述: 党组织活动信息保存
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 10:40
     * @修改记录: 修改人-修改时间-修改描述
     * @param model
	* @param user
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     **/
    public HashMap<String,Object> hdSave(DzzhdForm model,User user){
        HashMap<String,Object> result = new HashMap<>();
        if(StringUtils.isNull(model.getHdmc())){
            result.put("code",0);
            result.put("msg","活动名称不能为空!");
        }
        if(StringUtils.isNotNull(model.getId())){
            //存在活动id修改
            HashMap<String,String> dzzhdInfo = dzzhdDao.getById(model.getId());
            if(dzzhdInfo != null && model.getId().equals(dzzhdInfo.get("id"))){
                boolean flag = dzzhdDao.update(model);
                if(flag){
                    result.put("code",1);
                    result.put("msg","保存成功!");
                    result.put("data",model.getId());
                }else{
                    result.put("code",0);
                    result.put("msg","保存失败!");
                }
            }else{
                result.put("code",0);
                result.put("msg","活动信息不存在!");
            }
        }else{
            //新增
            String id = UniqID.getInstance().getUniqIDHash().toUpperCase();
            model.setCjsj(DateTranCnDate.timeStampToDate(System.currentTimeMillis()));
            model.setCjr(user.getUserName());
            model.setCjrxm(user.getRealName());
            model.setId(id);
            boolean flag = dzzhdDao.add(model);
            if(flag){
                result.put("code",1);
                result.put("msg","保存成功!");
                result.put("data",id);
            }else{
                result.put("code",0);
                result.put("msg","保存失败!");
            }
        }
        return result;
    }

    public HashMap<String,Object> removeById(String hdid){
        HashMap<String,Object> result = new HashMap<>();
        //判断是否存在活动人员信息
        Integer hdryNums = dzzhdDao.getHdryCount(hdid);
        if(hdryNums != null && hdryNums.intValue() > 0){
            result.put("code",0);
            result.put("msg","此活动存在人员名单，请先移除人员信息。");
            return result;
        }
        boolean flag = dzzhdDao.removeById(hdid);
        if(flag){
            result.put("code",1);
            result.put("msg","删除成功!");
        }else{
            result.put("code",0);
            result.put("msg","删除失败!");
        }
        return result;
    }

    /**
     * @描述: 获取参与人员信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 16:53
     * @修改记录: 修改人-修改时间-修改描述
     * @param model
	* @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    public List<HashMap<String,String>> getHdRyxxList(DzzhdForm model,User user){
        List<HashMap<String,String>> list = null;
        if("1".equals(model.getJoinStatus())){
            //已参与人员列表
            list = dzzhdDao.getDzzRyList(model,user);
        }else{
            //可添加人员列表(查询数据范围为对应设置的政治面貌人员)
            HashMap<String,String> hdInfo = dzzhdDao.getById(model.getId());
            String mxdx = hdInfo.get("mxdx");
            if(StringUtils.isNotNull(mxdx)){
                model.setMxdx(mxdx);
            }
            list = dzzhdDao.getStuxxList(model,user);
        }
        return list;
    }

    /**
     * @描述: 批量添加党团活动信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/2 11:18
     * @修改记录: 修改人-修改时间-修改描述
     * @param hdid
	* @param xhs
     * @return boolean
     **/
    public boolean batchAdd(String hdid,String xhs){
        return dzzhdDao.batchAdd(hdid,xhs);
    }
    /**
     * @描述: 批量移除党团活动信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/2 11:18
     * @修改记录: 修改人-修改时间-修改描述
     * @param hdid
	* @param xhs
     * @return boolean
     **/
    public boolean batchRemove(String hdid,String xhs){
        return dzzhdDao.batchRemove(hdid,xhs);
    }

    /**
     * @描述: 根据活动id、学号获取活动信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/2 11:17
     * @修改记录: 修改人-修改时间-修改描述
     * @param hdid 活动id
	* @param xh 学号
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getHdxdInfo(String hdid,String xh){
        return dzzhdDao.getHdxdInfo(hdid,xh);
    }

    /**
     * @描述: 活动心得保存
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/2 11:18
     * @修改记录: 修改人-修改时间-修改描述
     * @param data
     * @return boolean
     **/
    public boolean saveHdxd(HashMap<String,String> data){
        return dzzhdDao.saveHdxd(data);
    }


}

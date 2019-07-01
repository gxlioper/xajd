/**
 * @部门:学工产品事业部
 * @日期：2018-2-8 下午03:46:25 
 */  
package com.zfsoft.xgxt.hdgl.hdjdsh;

import com.zfsoft.ms.mail.util.CollectionUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxDao;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 活动阶段审核service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-02-08 03:43
 */
public class HdjdshService extends SuperServiceImpl<HdxxForm, HdjdshDao> {

    private HdxxDao hdxxDao = new HdxxDao();
    /**
     *  获取活动阶段审核成员列表，个人或组队.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-10 16:31
     * @param model
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw Exception
     */
    public List<HashMap<String,String>> getHdjdshCyList(HdxxForm model,User user) throws Exception {
        String bmlx = model.getBmlx();
        List<HashMap<String,String>> resultList;
        if(StringUtils.isNotNull(bmlx)){
            if("1".equals(bmlx)){
                resultList = dao.getHdjdshGrList(model,user);
            }else {
                resultList = dao.getHdjdshDwList(model,user);
            }
        }else {
            resultList = null;
        }
        return resultList;
    }

    /**
     *  查询已完成阶段信息列表.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-07 17:01
     * @param model
     * @param jdsx
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getYwcHdjdshCyList(HdxxForm model,String jdsx) throws Exception {

        //在活动成员（判断是个人还是组队）表中获取sqid
        String hdsqid = dao.getHdsqid(model);
        List<HashMap<String,String>> list = dao.getYwcHdjdshCyList(model,hdsqid,jdsx);
        return list;
    }

    /**
     *  根据活动id获取当前活动阶段信息.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-08 17:05
     * @param hdid
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getCurrentHdjdInfo(String hdid) throws Exception {

        return dao.getCurrentHdjdInfo(hdid);
    }

    /**
     *  根据活动id，阶段id，获取指定活动阶段信息..
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-08 17:05
     * @param hdid
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getHdjdInfoWithJdid(String hdid,String jdid) throws Exception {

        return dao.getHdjdInfoWithJdid(hdid,jdid);
    }

    /**
     *  查询活动阶段信息，包含各阶段待处理人数.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-15 17:46
     * @param model
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getHdjdList(HdxxForm model,User user) throws Exception {
        return dao.getHdjdList(model,user);
    }

    public boolean sfwdf(String hdsqid,String jdid,String zgh){
        return dao.sfwdf(hdsqid, jdid, zgh);
    }
    /**
     * 判断活动是否是需要打分的活动
     * @param hdid
     * @return
     */
    public boolean isExist(String hdid){
        return dao.isExist(hdid);
    }
    public boolean saveDf(HdxxForm model,User user) throws Exception {
        String hdsqid = dao.getHdsqid(model);
        model.setHdsqid(hdsqid);
        model.setSqid(hdsqid);
        boolean flag = dao.saveDf(model,user);
        HashMap<String,String> map = getHdjdInfoWithJdid(model.getHdid(),model.getJdid());
        String prejdid = map.get("prejdid");
        String prejdlx = map.get("prejdlx");
        if(flag){
            int dfisFinish = dao.dfIsFinish(model);
            //3个专家全部打完分数，设置教师阶段审核状态通过
            if(dfisFinish == 0){
                if("1".equals(model.getBmlx())){//个人
                    //更新成员阶段
                    flag = dao.updateCyjd(map.get("nextjdid"),hdsqid);
                    //上一阶段是学生阶段
                    if("1".equals(prejdlx)){
                        //更新学生阶段信息表（上一阶段）
                        model.setShzt("1");
                        flag = dao.updateXsjdShzt(prejdid,model);
                        if(flag){
                            flag = dao.updateJd(model);
                        }
                    } else {
                        flag = dao.updateJd(model);
                    }
                } else {
                    //更新成员阶段
                    flag = dao.updateZdCyjd(map.get("nextjdid"),hdsqid);
                    //上一阶段是学生阶段
                    if("1".equals(prejdlx)){
                        //更新学生阶段信息表（上一阶段）
                        model.setShzt("1");
                        flag = dao.updateZdjdShzt(prejdid,model);
                        if(flag){
                            flag = dao.updateJd(model);
                        }
                    } else {
                        flag = dao.updateJd(model);
                    }
                }
            }
        }
        return flag;
    }
    /**
     *  阶段审核通过.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-16 17:25
     * @param model
     * @return boolean
     * @throw
     */
    public boolean jdshtg(HdxxForm model,User user) throws Exception {

        String hdid = model.getHdid();
        String jdid = model.getJdid();
        HashMap<String,String> hdjdInfo = getHdjdInfoWithJdid(hdid,jdid);
        String bmlx = model.getBmlx();
        String prejdid = hdjdInfo.get("prejdid");
        String prejdlx = hdjdInfo.get("prejdlx");
        String nextjdid = hdjdInfo.get("nextjdid");
        boolean result;
        model.setShzt("1");
        model.setZgh(user.getUserName());
        boolean sfdfhd = isExist(hdid);
        //上一阶段是学生阶段
        if("1".equals(prejdlx)){
            if("1".equals(bmlx)){//个人
                //更新学生阶段信息表（上一阶段）
                result = dao.updateXsjdShzt(prejdid,model);
            }else{//组队
                String dwid = model.getDwid();
                //更新组队阶段信息表（上一阶段）
                result = dao.updateZdjdShzt(prejdid,model);
                //如果是组队活动最后一个阶段，插入组队结果信息表
                if(dao.isLastNode(hdid, jdid)){
                    result = dao.insertZdjgxx(model);
                }
            }
            //向教师阶段信息表插入数据
            if(result){
                String hdsqid = dao.getHdsqid(model);
                model.setHdsqid(hdsqid);
                result = dao.insertJsjdInfo(jdid,model);
            }
        }else if("2".equals(prejdlx)) {//上一 阶段是教师阶段
            //向教师阶段信息表插入数据
            String hdsqid = dao.getHdsqid(model);
            model.setHdsqid(hdsqid);
            result = dao.insertJsjdInfo(jdid,model);
            //是否是打分活动
            if(sfdfhd){
                String nextJdid = StringUtils.isNull(model.getNextjdid()) ? nextjdid : model.getNextjdid();
                if("1".equals(hdjdInfo.get("sftj"))){
                    List<String> jdids = dao.getZjjd(jdid,nextJdid,hdid);
                    if(!CollectionUtils.isEmpty(jdids)){
                        List<HdxxForm> hdxxForms = new ArrayList<HdxxForm>();
                        HdxxForm form;
                        for(String s : jdids){
                            form = new HdxxForm();
                            form.setHdid(hdid);
                            form.setJdid(s);
                            form.setShzt("1");
                            form.setHdsqid(model.getHdsqid());
                            form.setShyj(model.getShyj());
                            form.setZgh(model.getZgh());
                            hdxxForms.add(form);
                        }
                        for(HdxxForm form1 : hdxxForms){
                            dao.insertJsjdInfo(form1.getJdid(),form1);
                        }
                    } else {
                        //绑定下一轮打分专家
                        hdxxDao.zpzjbd(hdid,hdsqid,jdid);
                    }
                }
                if("1".equals(bmlx)){//个人
                    result = dao.updateCyjd(nextJdid,hdsqid);
                } else {//组队
                    result = dao.updateZdCyjd(nextJdid,hdsqid);
                }
            }
            //如果是组队活动最后一个阶段，插入组队结果信息表
            if(!"1".equals(bmlx) && dao.isLastNode(hdid, jdid)){
                result = dao.insertZdjgxx(model);
            }
        }else {
            throw new Exception("审核操作不合法");
        }
        return result;
    }

    /**
     *  阶段审核退回.不通过
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-16 17:25
     * @param model
     * @return boolean
     * @throw Exception
     */
    public boolean jdshth(HdxxForm model,User user) throws Exception {

        String hdid = model.getHdid();
        String jdid = model.getJdid();
        HashMap<String,String> hdjdInfo = getHdjdInfoWithJdid(hdid,jdid);
        String bmlx = model.getBmlx();
        String prejdid = hdjdInfo.get("prejdid");
        String prejdlx = hdjdInfo.get("prejdlx");

        boolean result;
        model.setZgh(user.getUserName());

        //上一阶段是学生阶段
//        if("1".equals(prejdlx)){
            if("1".equals(bmlx)){//个人
                //更新学生阶段信息表（上一阶段）
                result = dao.updateXsjdShzt(prejdid,model);
            }else{//组队
                String dwid = model.getDwid();
                //更新组队阶段信息表（上一阶段）
                result = dao.updateZdjdShzt(prejdid,model);
            }
            //向教师阶段信息表插入数据 （退回的审核意见等）
            if(result){
                String hdsqid = dao.getHdsqid(model);
                model.setHdsqid(hdsqid);
                result = dao.insertJsjdInfo(jdid,model);
            }
        String hdsqid = dao.getHdsqid(model);
        if("1".equals(bmlx)){//个人
            result = dao.updateCyjd(null,hdsqid);
        } else {//组队
            result = dao.updateZdCyjd(null,hdsqid);
        }
        //如果是组队活动最后一个阶段，插入组队结果信息表
        if(!"1".equals(bmlx) && dao.isLastNode(hdid, jdid)){
            result = dao.insertZdjgxx(model);
        }
        /*}else {
            throw new Exception("审核操作不合法");
        }*/
        return result;
    }

    /**
     *  根据活动id获取奖项列表.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-22 10:05
     * @param hdid
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getJxList(String hdid) {

        return dao.getJxList(hdid);
    }
    //获取当前阶段以后的阶段
    public List<HashMap<String,String>> getJdList(String hdid,String currentJdid){
        return dao.getJdList(hdid,currentJdid);
    }
    /**
     *  查询指定活动阶段审核信息.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-28 10:57
     * @param model
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getHdjdShInfo(HdxxForm model) {

        String hdsqid = dao.getHdsqid(model);
        model.setHdsqid(hdsqid);
        return dao.getHdjdShInfo(model);
    }

    /**
     *  判断是否专家团成员.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-29 16:21
     * @param hdid
     * @param userName
     * @return boolean
     * @throw
     */
    public boolean isZjtcy(String hdid, String userName) {

        return dao.isZjtcy(hdid,userName);
    }
}

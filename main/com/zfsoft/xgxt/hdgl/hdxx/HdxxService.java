/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdxx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgDao;
import org.apache.struts.upload.FormFile;
import xgxt.DAO.PicDAO;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * @author ：柳俊（1282）
 * @version V1.0
 * @className ： HdxxService
 * @description ： TODO(描述这个类的作用)
 * @date ： 2018-1-31 上午11:38:12
 */

public class HdxxService extends SuperServiceImpl<HdxxForm, HdxxDao> {

    /**
     * @param model
     * @return
     * @throws Exception
     * @description ： 保存报名
     * @author ： 柳俊（1282）
     * @date ：2018-1-31 上午11:46:51
     */
    public boolean saveBm(HdxxForm model) throws Exception {
        return dao.saveBm(model);
    }

    public boolean update(HdxxForm model) throws Exception {
        String[] btZd = model.getBtzd();
        if (btZd != null && btZd.length > 0) {
            dao.updateZd(btZd, model.getHdid());
        }

        return dao.runUpdate(model);
    }

    /**
     * @param model
     * @return
     * @description ： 获取活动信息
     * @author ： 柳俊（1282）
     * @date ：2018-2-1 上午11:14:30
     */
    public HashMap<String, String> getHdxx(HdxxForm model) {
        return dao.getHdxx(model);
    }

    public List<HashMap<String, String>> zxHdxxList(HdxxForm model,User user) throws Exception{
        Pages pages = new Pages();
        pages.setPageSize(5);
        model.setPages(pages);
        return dao.zxHdxxList(model,user);
    }

    //获取报名申请信息
    public HashMap<String, String> getBmSqxx(String sqid, String bmlx) {
        return dao.getBmSqxx(sqid, bmlx);
    }
    //获取报名申请信息
    public HashMap<String, String> getBmSqxx(HdxxForm t) {
        return dao.getBmSqxx(t);
    }

    /**
     * 报名按钮点击验证
     *
     * @param hdid
     * @param user
     * @return
     * @throws Exception
     */
    public String yz(String hdid, User user) throws Exception {
        //1：验证是否已经报名
        if (dao.sfbm(hdid, user)) {
            return "您已报名，请勿重复操作！";
        }
        //2：验证报名不需要审核的活动的人数是否超过上限
//        HdxxForm hdxx = dao.getModel(hdid);
//        if ("0".equals(hdxx.getBmsfsh())) {
//            int hdrssx = hdxx.getBmrs() == null ? 0 : Integer.parseInt(hdxx.getBmrs());//活动人数上限
//            int bmrs = dao.bmrs(hdid);//已报名人数
//            if ((bmrs + 1) > hdrssx) {
//                return "报名人数已超出限额！";
//            }
//        }
        return "true";
    }

    /**
     * 保存必填字段验证
     *
     * @param t
     * @return
     * @throws Exception
     */
    public String saveCheck(HdxxForm t) throws Exception {
        List<HashMap<String, String>> zdList = this.getBtZdList(t.getHdid());
        for (HashMap<String, String> zd : zdList) {
            if ("1".equals(zd.get("sfbt"))) {
                String zdName = zd.get("zd");
                String methodName = "get" + zdName.substring(0, 1).toUpperCase() + zdName.substring(1);
                Method method = t.getClass().getMethod(methodName);
                String s = (String) method.invoke(t);
                if (StringUtil.isNull(s)) {
                    return "请将必填字段填写完成！";
                }
            }
        }
        return "true";
    }

    //获取报名表单简历单项字段
    public List<HashMap<String, String>> getBtZdList(String hdid) {
        return dao.getBtZdList(hdid);
    }

    public String saveStuPic(HdxxForm model) {
        PicDAO picDao = new PicDAO();

        // 学号
        String xh = model.getXh();

        // 文件
        FormFile file = model.getStuPic();
        String fileName = file.getFileName();

        boolean isAllowType = fileName.endsWith("jpg") || fileName.endsWith("jpeg")
                || fileName.endsWith("gif")
                || fileName.endsWith("png")
                || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF") || fileName.endsWith("PNG") || fileName.endsWith("BMP");

        if (!isAllowType) {
            return "上传失败，非法的文件格式！";
        }

        if (file.getFileSize() > 1024 * 1024) {
            return "上传失败，文件大小超过1M！";
        } else {
            try {
                picDao.savePic(file.getInputStream(), xh, "stu");
                return "true";
            } catch (Exception e) {
                e.printStackTrace();
                return "上传失败，请重新上传！";
            }
        }
    }

    /**
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @description ： 活动详情列表
     * @author ： 柳俊（1282）
     * @date ：2018-2-1 下午05:30:19
     */
    public List<HashMap<String, String>> getHdxqList(HdxxForm model, User user) throws Exception {
        return dao.getHdxqPageList(model, user);
    }

    /**
     * 获取活动管理列表数据.
     *
     * @param model
     * @param user
     * @return java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-05 14:55
     * @throw
     */
    public List<HashMap<String, String>> getHdglList(HdxxForm model, User user) throws Exception {

        return dao.getHdglPageList(model, user);
    }

    /**
     * @return
     * @description ： 获取成员列表
     * @author ： 柳俊（1282）
     * @date ：2018-2-2 上午09:21:20
     */
    public List<HashMap<String, String>> getCyList(HdxxForm model, User user) throws Exception {
        return dao.getCyList(model, user);
    }

    public List<HashMap<String, String>> gethasPCyList(HdxxForm model, User user) throws Exception {
        return dao.gethasPCyList(model,user);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description ： 保存审核状态
     * @author ： 柳俊（1282）
     * @date ：2018-2-5 上午11:40:32
     */
    public boolean updateShzt(HdxxForm t) throws Exception {
        return dao.updateShzt(t);
    }

    /**
     * @param t
     * @return
     * @description ： 判断通过人数是否已超上限
     * @author ： 柳俊（1282）
     * @date ：2018-2-6 上午09:22:35
     */
    public boolean beyondMaximum(HdxxForm t) {
        String hdid = t.getHdid();
        Integer rs = dao.getRs(hdid);
        Integer peoplePermitted = dao.getPeoplePermitted(hdid);
        String[] ids = t.getIds();
        int tgrs = ids.length;
        if (rs == 0) {
            return false;
        } else {
            if (peoplePermitted + tgrs > rs) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param t
     * @param user
     * @return
     * @description ： 获取活动阶段信息
     * @author ： 柳俊（1282）
     * @date ：2018-2-6 下午04:35:19
     */
    public HashMap<String, String> getHdjdInfo(HdxxForm t, User user) {
        return dao.getHdjdInfo(t, user);
    }

    /**
     * 教师获取活动阶段信息.
     *
     * @param t
     * @return java.util.HashMap<java.lang.String                               ,                               java.lang.String>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-10 15:05
     * @throw
     */
    public HashMap<String, String> getHdjdInfo(HdxxForm t) {

        return dao.getHdjdInfo(t);
    }


    /**
     * @param t
     * @return
     * @description ： 获取活动阶段list
     * @author ： 柳俊（1282）
     * @date ：2018-2-7 上午08:57:47
     */
    public List<HashMap<String, String>> getHdjdList(HdxxForm t) {
        return dao.getHdjdList(t);
    }

    /**
     * 活动下架.
     *
     * @param hdid
     * @return boolean
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-06 15:29
     * @throw Exception
     */
    public boolean hdxjById(String hdid) throws Exception {

        return dao.hdxjById(hdid);
    }

    /**
     * 获取专家团成员列表.
     *
     * @param sffp
     * @return java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-07 14:09
     * @throw Exception
     */
    public List<HashMap<String, String>> getZjtcyList(HdxxForm hdxxForm, String sffp) throws Exception {

        return dao.getZjtcyList(hdxxForm, sffp);
    }

    /**
     * 活动管理，保存专家团成员分配.
     *
     * @param model
     * @return boolean
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-08 09:55
     * @throw Exception
     */
    public boolean zjtcyFp(HdxxForm model) throws Exception {

        String[] ids = model.getIds();    //活动id数组
        String[] zghs = model.getZghs();    //专家团成员zgh数组
        String jdid = model.getJdid();
        if (ids == null || zghs == null) {
            return false;
        }

        return dao.zjtcyFp(ids, zghs, jdid);
    }

    /**
     * 活动管理，取消专家团成员分配.
     *
     * @param model
     * @return boolean
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-08 09:55
     * @throw Exception
     */
    public boolean zjtcyQxfp(HdxxForm model) throws Exception {

        String hdid = model.getHdid();    //活动id
        String[] zghs = model.getZghs();    //专家团成员zgh数组
        String jdid = model.getJdid();
        if (hdid == null || zghs == null) {
            return false;
        }
        return dao.zjtcyQxfp(hdid, zghs, jdid);
    }

    public String yz(HdxxForm t) {
        if ("是".equals(t.getSflx())) {
            if (StringUtil.isNull(t.getMc()) || StringUtil.isNull(t.getJb())
                    || StringUtil.isNull(t.getZddw()) || StringUtil.isNull(t.getZdjs())
                    || StringUtil.isNull(t.getNrjs()) || StringUtil.isNull(t.getFjid())) {
                return "请将必填项填写完整!";
            }
        }
        return "true";
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description ： 更新学生阶段填写
     * @author ： lj（1282）
     * @date ：2018-2-7 下午02:45:38
     */
    public boolean updateStudentStage(HdxxForm t) throws Exception {
        return dao.updateStudentStage(t);
    }

    /**
     * 验证活动人数上限
     *
     * @param t
     * @return
     * @throws Exception
     */
    public String checkZrs(HdxxForm t) throws Exception {
        HdxxForm hdxx = dao.getModel(t.getHdid());
        String dwrs = hdxx.getDwrs();//队伍约定人数
        String hdzrs = hdxx.getBmrs();//活动允许总人数
        int dws = dao.getDws(t.getHdid());//活动已有队伍数
        if ((dws + 1) * Integer.parseInt(dwrs) > Integer.parseInt(hdzrs)) {
            return "人数将超过活动上限！";
        }
        return "true";
    }

    /**
     * 验证队伍人数上限
     *
     * @param t
     * @return
     * @throws Exception
     */
    public String checkDwrs(HdxxForm t) throws Exception {
        HdxxForm hdxx = dao.getModel(t.getHdid());
        String dwrs = hdxx.getDwrs();//队伍允许总人数
        int currentDwrs = dao.getDwrs(t.getHdid(), t.getDwid());
        if (currentDwrs + 1 > Integer.parseInt(dwrs)) {
            return "队伍人数已满！";
        }
        return "true";
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description ： 创建组队
     * @author ： lj（1282）
     * @date ：2018-2-9 下午02:40:52
     */
    public synchronized boolean createGroup(HdxxForm t, String[] xhs) throws Exception {
        String dwid = String.valueOf(dao.getDws(t.getHdid()) + 1);
        t.setDwid(dwid);
        return dao.insertZdCy(t, xhs);
    }

    /**
     * @param hdid TODO
     * @return
     * @description ： 获取队伍列表
     * @author ： lj（1282）
     * @date ：2018-2-10 下午03:21:58
     */
    public List<HashMap<String, String>> getDwList(String userName, String hdid) {
        return dao.getDwList(userName, hdid);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description ： 加入组队
     * @author ： lj（1282）
     * @date ：2018-2-10 下午03:50:36
     */
    public boolean jrzd(HdxxForm t) throws Exception {
        //设置为成员
        t.setDwzw("0");
        return dao.insertZdCy(t);
    }

    /**
     * @param t
     * @param user
     * @return
     * @description ： 获取活动阶段信息（组队）
     * @author ： lj（1282）
     * @date ：2018-2-28 上午11:57:14
     */
    public HashMap<String, String> getHdjdInfoForZd(HdxxForm t, User user) {
        return dao.getHdjdInfoForZd(t, user);
    }

    public String getDwid(HdxxForm form,String xh) throws Exception{
        return dao.getDwid(form,xh);
    }
    /**
     * @param t
     * @param user
     * @return
     * @throws Exception
     * @description ： 插入评论
     * @author ： lj（1282）
     * @date ：2018-3-5 上午11:46:54
     */
    public boolean insertPl(HdxxForm t, User user) throws Exception {
        return dao.insertPl(t, user);
    }

    /**
     * @param t
     * @param user
     * @return
     * @description ： 获取评论内容
     * @author ： lj（1282）
     * @date ：2018-3-5 下午03:48:44
     */
    public HashMap<String, String> getPlnrByUser(HdxxForm t, User user) throws Exception {
        return dao.getPlnrByUser(t, user);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description ： 更新评论内容
     * @author ： lj（1282）
     * @date ：2018-3-5 下午04:22:45
     */
    public boolean updatePlnr(HdxxForm t) throws Exception {
        return dao.updatePlnr(t);
    }

    /**
     * @return
     * @throws Exception
     * @description ： 获取活动评论list
     * @author ： lj（1282）
     * @date ：2018-3-8 上午10:42:13
     */
    public List<HashMap<String, String>> getHdplList(HdxxForm t) throws Exception {
        return dao.getHdplList(t);
    }

    /**
     * @param plid
     * @return
     * @description ： 获取活动评论信息
     * @author ： lj（1282）
     * @date ：2018-3-9 上午09:16:19
     */
    public HashMap<String, String> getHdPlInfo(String plid) {
        return dao.getHdPlInfo(plid);
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception
     * @description ： 回复评论
     * @author ： lj（1282）
     * @date ：2018-3-9 上午11:56:44
     */
    public boolean replyPl(HdxxForm t, User user) throws Exception {
        return dao.replyPl(t, user);
    }

    /**
     * @param plid
     * @return
     * @throws Exception
     * @description ： 删除评论
     * @author ： lj（1282）
     * @date ：2018-3-9 下午03:43:02
     */
    public boolean delPl(String plid) throws Exception {
        return dao.delPl(plid);
    }

    /**
     * 根据dwid获取队伍列表.
     *
     * @param hdxxForm
     * @return java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-08 17:46
     * @throw
     */
    public List<HashMap<String, String>> getDwList(HdxxForm hdxxForm) {

        return dao.getDwList(hdxxForm);
    }

    /**
     * 获取个人活动报名信息.
     *
     * @param model
     * @return java.util.HashMap<java.lang.String                               ,                               java.lang.String>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-09 16:38
     * @throw
     */
    public HashMap<String, String> getGrbmxx(HdxxForm model) {

        return dao.getGrbmxx(model);
    }

    /**
     * @param model
     * @return
     * @throws Exception
     * @description ： 点赞
     * @author ： lj（1282）
     * @date ：2018-3-13 下午05:29:46
     */
    public boolean dz(HdxxForm model) throws Exception {
        return dao.dz(model);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description ： 获取下一阶段活动信息
     * @author ： lj（1282）
     * @date ：2018-3-15 下午03:56:21
     */
    public HashMap<String, String> getNextHdInfo(HdxxForm t) throws Exception {
        return dao.getNextHdInfo(t);
    }

    /**
     * @param t
     * @return
     * @throws Exception
     * @description ： 获取组队下一阶段活动信息
     * @author ： lj（1282）
     * @date ：2018-3-26 下午03:38:53
     */
    public HashMap<String, String> getNextHdInfoForZd(HdxxForm t) throws Exception {
        return dao.getNextHdInfoForZd(t);
    }

    /**
     * @param hdid
     * @return
     * @throws SQLException
     * @description ： 获取活动标签
     * @author ： lj（1282）
     * @date ：2018-4-2 下午05:07:22
     */
    public List<String> getHdbq(String hdid) throws SQLException {
        return dao.getHdbq(hdid);
    }

    /**
     * @return
     * @description ： 设置活动阶段
     * @author ： lj（1282）
     * @date ：2018-4-4 下午04:16:53
     */
    public boolean szHdjd(HdxxForm t) throws Exception {
        boolean result = dao.runUpdate(t);
        if (result) {
            String[] jdlxs = t.getJdlxs();
            if (null != jdlxs) {
                String[] jdids = new String[jdlxs.length];
                dao.deleteJds(t);
                for (int i = 0; i < jdlxs.length; i++) {
                    String jdid = UniqID.getInstance().getUniqIDHash();
                    jdids[i] = jdid;
                }
                t.setJdids(jdids);
                result = dao.insertJdglByHdId(t);
            }
            result = szHdLxAndBq(t);
        }
        return result;
    }

    /**
     * @param t
     * @return
     * @description ： 是否可以阶段设置
     * @author ： lj（1282）
     * @date ：2018-4-8 上午10:30:28
     */
    public boolean sfJdsz(HdxxForm t) {
        //如果阶段里存在人员，则不允许删除
        boolean result = dao.countJdrs(t);
        return result == false ? true : false;
    }

    /**
     * @param form
     * @return
     * @throws Exception
     * @description ： 获取活动阶段数
     * @author ： lj（1282）
     * @date ：2018-4-8 下午03:15:02
     */
    public List<HashMap<String, String>> getHdjdLists(HdxxForm form) throws Exception {
        return dao.getHdjdLists(form);
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception
     * @description ： 获取活动结果列表
     * @author ： lj（1282）
     * @date ：2018-4-12 下午04:01:35
     */
    public List<HashMap<String, String>> getPageListForResult(HdxxForm t, User user) throws Exception {
        return dao.getPageListForResult(t, user);
    }

    public List<HashMap<String, String>> getPageListForHdxjg(HdxxForm t) throws Exception {
        return dao.getPageListForHdxjg(t);
    }

    public List<HashMap<String, String>> getPageListForHdxjgByStu(HdxxForm t, User user) throws Exception {
        if ("stu".equals(user.getUserType())) {
            return dao.getPageListForHdxjgForStu(t, user);
        } else {
            return dao.getPageListForHdxjgByStu(t, user);
        }
    }

    public List<HashMap<String, String>> getPageListForJzjg(HdxxForm t) throws Exception {
        return dao.getPageListForJzjg(t);
    }

    public List<HashMap<String, String>> getPageListForJzjgByStu(HdxxForm t, User user) throws Exception {
        if ("stu".equals(user.getUserType())) {
            return dao.getPageListForJzjgForStu(t, user);
        } else {
            return dao.getPageListForJzjgByStu(t, user);
        }
    }

    /**
     * @param form
     * @return
     * @description ： 设置类型和标签
     * @author ： lj（1282）
     * @date ：2018-4-12 下午05:15:01
     */
    public boolean szHdLxAndBq(HdxxForm form) throws Exception {
        boolean result = dao.szHdLx(form);
        if (result) {
            HdbljgDao jgDao = new HdbljgDao();
            String[] nlbqs = form.getNlbqs();/*获取能力标签*/
            //删除活动标签
            result = dao.deleteHdbq(form);
            boolean result1 = jgDao.deleteNlbq(new String[]{form.getHdid()});
            if (result && result1) {
                //插入活动标签
                result = dao.insertHdbq(form);
                /*插入能力标签*/
                result = jgDao.BatchInsertNlbqx(form.getHdid(), nlbqs);
            }
            dao.deleteHdjx(form);
            String[] jxmcs = form.getJxmcs();
            if (null != jxmcs && jxmcs.length > 0) {
                result = dao.insertHdjx(form);
            }
        }
        return result;

    }

    public String getdsCount(String xh) {
        return dao.getdsCount(xh);
    }

    public List<HashMap<String, String>> getHdlistByXh(String xh) {
        return dao.getHdlistByXh(xh);
    }

    public String getjsCount(String xh) {
        return dao.getjsCount(xh);
    }

    public String getjzCount(String xh) {
        return dao.getjzCount(xh);
    }

    public String gethdCount(String xh) {
        return dao.gethdCount(xh);
    }

    public List<HashMap<String, String>> getHdXflistByXh(String xh) {
        return dao.getHdXflistByXh(xh);
    }

    public HashMap<String, String> getNjPmByXh(String xh) {
        return dao.getNjPmByXh(xh);
    }

    public List<HashMap<String, String>> getHdslistByXh(String xh) {
        return dao.getHdslistByXh(xh);
    }

    /**
     * @param form
     * @return
     * @throws Exception
     * @description ： 获取活动奖项
     * @author ： lj（1282）
     * @date ：2018-4-23 下午03:32:36
     */
    public List<HashMap<String, String>> getHdjxs(HdxxForm form) throws Exception {
        return dao.getHdjxs(form);
    }

    /**
     * 获取派票人员集合
     *
     * @return
     */
    public List<HashMap<String, String>> getPpPersonList(HdxxForm form) {

        if (HdxxDao.randomAndByTime.equals(form.getPptype())) {
            return dao.getPpPersonList2(form);
        } else {
            return dao.getPpPersonList(form);
        }
    }

    public HdxxForm getModel(HdxxForm form) throws Exception {
        return dao.getModel(form.getHdid());
    }

    public String checkPpRs(HdxxForm form) throws Exception {
        String shtgrs = dao.getYshrs(form);//审核通过人数
        String pprs = dao.getYpprs(form);//已派票人数
        HdxxForm hdxx = dao.getModel(form.getHdid());

        if (Integer.parseInt(pprs)>=Integer.parseInt(hdxx.getBmrs())) {
            return "人数已满，无法再派票！";
        }
        String ppnum = form.getPpnum();
        if (Integer.parseInt(ppnum) > (Integer.parseInt(hdxx.getBmrs()) - Integer.parseInt(pprs))) {
            return "已有" + pprs + "人得票，剩余" + String.valueOf(Integer.parseInt(hdxx.getBmrs()) - Integer.parseInt(pprs)) + "票可以派票！";
        }

        return "true";
    }

    public boolean update(List<String> p, String bmlx) throws SQLException {
        return dao.setPp(p, bmlx);
    }

    /**
     * @param hdid
     * @return
     * @throws Exception HashMap<String,String> 返回类型
     * @throws
     * @描述: 根据活动id获取活动信息
     * @作者： MengWei[工号：1186]
     * @日期： 2018-6-11 下午04:58:01
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public HashMap<String, String> getHdxxByHdid(String hdid) throws Exception {
        return dao.getHdxxByHdid(hdid);
    }

    /**
     * @param hdid
     * @return
     * @throws Exception HashMap<String,String> 返回类型
     * @throws
     * @描述: 阶段成员信息
     * @作者： MengWei[工号：1186]
     * @日期： 2018-6-11 下午06:07:12
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getJdcyxxList(String hdid) throws Exception {
        return dao.getJdcyxxList(hdid);
    }

    /**
     * 获取活动类型list，画面下拉检索用
     *
     * @return
     */
    public List<HashMap<String, String>> getHdlx() {
        return dao.getHdlx();
    }

    public List<HashMap<String, String>> getJdxx(String hdid) {
        return dao.getJdxx(hdid);
    }

    public String checkBm(HdxxForm t, String[] xhs) throws Exception {
        List<String> xhlist = Arrays.asList(xhs);
        List<String> xhlists = new ArrayList<>(xhlist);
        xhlists.add(t.getXh());
        xhs = xhlists.toArray(new String[xhlists.size()]);
        StringBuilder msg = new StringBuilder();
        boolean result = false;
        for (int i = 0; i < xhs.length; i++) {
            if (dao.sfzdbm(t.getHdid(), xhs[i])) {
                msg.append("【" + xhs[i] + "】");
                result = true;
            }
        }
        if (result) {
            msg.append("已加入其他队伍");
        }else {
            msg.append("true");
        }
        return msg.toString();
    }

    /**
     * 获得所有有效的不需要报名审核活动的列表
     */
    public List<HashMap<String, String>> getHdxxList() {
        return dao.getHdxxList();
    }

    /**
     * 获得审核通过的人员
     *
     * @param hdId
     * @return
     */
    public List<String> getHdryList(String hdId) {
        try {
            return dao.getHdryList(hdId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 派票
     *
     * @param
     * @return
     */
    public void pp() {
        //所有未派票的活动
        List<HashMap<String, String>> hdList = getHdxxList();
        for (HashMap<String, String> hashMap : hdList) {
            //报名的人
            List<String> ryList = getHdryList(hashMap.get("hdid"));
            if (StringUtils.isNull(hashMap.get("bmrs"))) {
                continue;
            }
            int bmrs = Integer.parseInt(hashMap.get("bmrs"));
            Date jssj;
            try {
                jssj = DateUtils.parse(hashMap.get("bmjssj"));
            } catch (Exception e) {
                continue;
            }
            if ("1".equals(hashMap.get("ppfs")) || "2".equals(hashMap.get("ppfs"))) {
                if ("1".equals(hashMap.get("ppfs"))) {
                    Collections.shuffle(ryList);
                }
                if (ryList.size() > bmrs) {
                    ryList = ryList.subList(0, bmrs);
                }
            }

            if ("3".equals(hashMap.get("ppfs"))) {
                int zzbmrs = Integer.parseInt(hashMap.get("zzbmrs"));
                if (ryList.size() > bmrs) {
                    List<String> randomList = ryList.subList(zzbmrs, ryList.size());
                    Collections.shuffle(randomList);
                    ryList = ryList.subList(0, zzbmrs);
                    int randomCount = Math.abs(bmrs - zzbmrs);
                    ryList.addAll(randomList.subList(0, randomCount));
                }
            }
            if (jssj.before(new Date())) {
                List<String[]> params = new ArrayList<>();
                for (String id : ryList) {
                    params.add(new String[]{id});
                }
                try {
                    dao.pp(params);

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }

        }
    }
}

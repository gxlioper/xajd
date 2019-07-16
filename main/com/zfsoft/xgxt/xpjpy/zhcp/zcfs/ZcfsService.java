/**
 * @部门:学工产品事业部
 * @日期：2013-7-23 下午01:35:39
 */
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import common.Globals;
import jxl.CellFeatures;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.Boolean;
import java.sql.SQLException;
import java.util.*;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分数
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-23 下午01:35:39
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZcfsService extends
        SuperServiceImpl<ZcfsModel, ZcfsDao> {

    private ZcfsDao dao = new ZcfsDao();
    private CsszDao csszDao = new CsszDao();
    public static final String DEFAULT_PMFS = "bjpm";
    public static final String DEFAULT_QXTJ = "2";  //取消提交

    public static String _BCZSCID = "-1";

    public ZcfsService() {
        super.setDao(dao);
    }

    /**
     * @return boolean 返回类型
     * @描述: 是否有班级已经提交了综测分
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-23 下午01:39:38
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean isHaveSubmitClass(String xn, String xq) {

        return Integer.valueOf(dao.getYtjZcfNum(xn, xq)) > 0;
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @描述: 综测班级提交查询
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-24 上午10:16:40
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZcbjList(ZcfsModel t, User user)
            throws Exception {

        return dao.getZcbjList(t, user);
    }


    /*
	 *
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
    public List<HashMap<String, String>> getPageList(ZcfsModel t, User user)
            throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();
        //根据ID取得班级代码和名称
        List<HashMap<String, String>> bjxxMap = getBjxxByIds(t, user);

        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            //综测项目级别为 年级
            zcxmList = zcxmDao.getEditZcxmByNj(bjxxMap, t, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            //综测项目级别是为 院系
            zcxmList = zcxmDao.getEditZcxmByXy(bjxxMap, t, user);
        } else {
            zcxmList = zcxmDao.getNoChildZcfxm();
        }
        return dao.getPageList(t, zcxmList, user);
    }

    public List<HashMap<String, String>> getPageListOfYf(ZcfsModel t, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();
        // 根据ID取得班级代码和名称
        List<HashMap<String, String>> bjxxMap = getBjxxByIds(t, user);

        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            // 综测项目级别为 年级
            zcxmList = zcxmDao.getEditZcxmByNj(bjxxMap, t, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            // 综测项目级别是为 院系
            zcxmList = zcxmDao.getEditZcxmByXy(bjxxMap, t, user);
        } else {
            zcxmList = zcxmDao.getNoChildZcfxm();
        }
        return dao.getPageListOfYf(t, zcxmList, user);
    }


    /**
     * @param t
     * @param user
     * @return
     * @throws Exception boolean 返回类型
     * @描述: 保存综测分数
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-24 下午03:07:41
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean saveZcfs(ZcfsModel t, User user) throws Exception {

        //判断该学年、学期，该学生是否已经录入了此项目的分数
        HashMap<String, String> zcfsMap = dao.getFsid(t);

        t.setLrr(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));

        //无：插入 有：更新
        if (StringUtil.isNull(zcfsMap.get("id"))) {
            return dao.runInsert(t);
        } else {
            t.setId(zcfsMap.get("id"));
            return dao.runUpdate(t);
        }
    }

    public boolean saveZcfsYf(ZcfsModel t, User user) throws Exception {

        //判断该学年、学期，该学生是否已经录入了此项目的分数
        HashMap<String, String> zcfsMap = dao.getFsid(t);
        HashMap<String, String> zcfsYfMap = dao.getFsOfYf(t);
        String zcfs = "0";
        t.setLrr(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
        //月综测分录入

        //无：插入 有：更新
        if (StringUtil.isNull(zcfsYfMap.get("guid"))) {
            dao.insertZcfsYf(t);

        } else {
            t.setId(zcfsYfMap.get("guid"));
            zcfs = zcfsYfMap.get("fs");//记录修改前分数
            dao.updateZcfsYf(t);

        }

        if (null == t.getFs() || "".equals(t.getFs())) {
            t.setFs("0");
        }
        if (null == zcfs || "".equals(zcfs)) {
            zcfs = "0";
        }

        //无：插入 有：更新
        if (StringUtil.isNull(zcfsMap.get("id"))) {
            return dao.runInsert(t);
        } else {
            String fsSum = String.valueOf((Double.parseDouble(zcfsMap.get("fs")) - Double.parseDouble(zcfs) + Double.parseDouble(t.getFs())));
            t.setFs(fsSum);
            t.setId(zcfsMap.get("id"));
            return dao.runUpdate(t);
        }

    }

    public boolean saveZcfsYfPl(ZcfsModel t, User user) throws Exception {
        t.setLrr(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
        return dao.updateZcfs(t);
    }


    /**
     * @param model
     * @return boolean 返回类型
     * @throws Exception
     * @描述: 检测班级综测分是否可以提交
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-24 下午04:57:03
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean isCanSubmit(ZcfsModel model) throws Exception {

        boolean isCanSubmit = false;

        //将默认项目 和 同步接口项目的分数加到分数记录表
        initDefaultZcfs(model.getXn(), model.getXq(), model.getId());

        //可录项目数*学生数  <= 分数记录数
        isCanSubmit = Boolean.valueOf(dao.getSfyWlr(model));

        if (!isCanSubmit) {
            return false;
        }

        //检测是否有NULL分值 的分数记录
        isCanSubmit = Integer.valueOf(dao.getNullZcf(model)) == 0;

        return isCanSubmit;
    }


    /**
     * @param model
     * @return
     * @throws Exception String[] 返回类型
     * @throws
     * @描述:批量提交
     * @作者：cq [工号：785]
     * @日期：2015-2-5 下午03:54:40
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public String[] isNotCanSubmit(ZcfsModel model, User user) throws Exception {

        String[] ids = model.getId().split(",");

        List<String> canId = new ArrayList<String>();//可以提交的id集合
        StringBuffer noSubmit = new StringBuffer();
        boolean isNotCanSubmit = false;
        int i = 0;  //记录提交个数

        if (null == ids || ids.length <= 0) {
            return null;
        }
        for (String str : ids) {
            model.setId(str);
            if (isCanSubmit(model)) {
                submitZcfs(model, user); //单个提交
                canId.add(str);//记录id
                i++;
            } else {
                HashMap<String, String> bjInfo = dao.getBjInfo(str);
                noSubmit.append(bjInfo.get("bjmc"));
                noSubmit.append(",</br>");
                isNotCanSubmit = true;
            }
        }


        String str = noSubmit.toString();
        //去除最后多余逗号
        str = isNotCanSubmit ? str : _BCZSCID;
        return new String[]{String.valueOf(i), str};

    }


    /**
     * @param model
     * @param user
     * @return boolean 返回类型
     * @描述: 提交综测分
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-24 下午04:58:02
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean submitZcfs(ZcfsModel model, User user) {

        Map<String, String> map = getBjxxById(model.getId());

        try {

            //计算综测分
            Thread thread = new Thread(new ComputeZcpm(map.get("xn"), map.get("xq"), map.get("bjdm")));
            thread.start();

            boolean result = dao.submitBjzcf(model.getId(), user.getUserName());
//			班级状态提交完成后更新参数设置内的人数设定，参数配置表（0:不更新,1:更新）
            CsszService csszService = new CsszService();
            if (("1").equals(csszService.getCsz("szyf"))) {
                if (dao.checkIsNotExistsInYfTjb(model.getId(), model.getZcyf())) {
                    dao.submitBjzcfYf(model.getId(), model.getZcyf(), user.getUserName());
                } else {
                    dao.udpateYfTjb(model.getId(), model.getZcyf());
                }

            }
            if ("1".equals(csszService.getCsz("gxrssd")) && result) {
                // 分数提交完成后根据比例更新项目设置当中的人数
                updateRssz(map.get("bjdm"));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * @return boolean 返回类型
     * @throws Exception
     * @throws
     * @描述:综测分数提交后更新项目设置当中的人数分配方式
     * @作者：cq [工号：785]
     * @日期：2014-7-22 下午05:23:52
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean updateRssz(String bjdm) throws Exception {

        List<HashMap<String, String>> map = dao.getDqxmInfo();

        boolean updateRssz = true;

        if (null != map && map.size() > 0) {

            for (int i = 0; i < map.size(); i++) {

                if (StringUtil.isNull(map.get(i).get("rsfpfs"))) {

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_BJ)) {
                    // 班级
                    updateRssz = dao.updateBjRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_NJXY)) {
                    // 年级学院
                    updateRssz = dao.updateNjxyRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_NJZY)) {
                    // 年级专业
                    updateRssz = dao.updateNjZyRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_XY)) {
                    // 学院
                    updateRssz = dao.updateXyRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_CPZ)) {
                    // 参评组
                    updateRssz = dao.updateCpzRssz(bjdm, map.get(i).get("xmdm"));

                } else if (map.get(i).get("rsfpfs").equals(Constants.RSKZFW_XX)) {
                    // 学校
                    updateRssz = dao.updateXxRssz(map.get(i).get("xmdm"));

                }

                //判断如果执行不成功跳出循环
                if (updateRssz == false) {
                    break;
                }

            }
        }

        System.out.println(updateRssz == true ? "参数设置人数自动更新成功(班级代码：" + bjdm + ")" : "参数设置人数自动更新失败(班级代码：" + bjdm + ")");

        return updateRssz;
    }

    /**
     * @throws Exception
     * @描述: 初始化默认综则分数
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-26 下午01:36:26
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public void initDefaultZcfs(ZcfsModel model, User user) throws Exception {

        //根据ID取得班级代码和名称
        List<HashMap<String, String>> bjxxMap = getBjxxByIds(model, user);
        //默认项目综测分
        dao.insertDefaultZcxmf(model.getXn(), model.getXq(), bjxxMap, model, user);
        //接口同步项目插入空分值
        dao.insertTbxmf(model.getXn(), model.getXq(), bjxxMap, model);
        //江西陶瓷 非同步分置0
        if ("12930".equals(Base.xxdm) || "12688".equals(Base.xxdm) || "12942".equals(Base.xxdm)) {
            dao.insertFtbf(model.getXn(), model.getXq(), bjxxMap, model);
        }
        //将比例为百分之零的初始化分数为0
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        //项目级别为“年级 ”
        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByNj(model.getXn(), model.getXq(), bjxxMap, model);
        }

        //项目级别为“院系”
        if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByXy(model.getXn(), model.getXq(), bjxxMap, model);
        }

    }


    /**
     * @param xn
     * @param xq
     * @param id void 返回类型
     * @throws Exception
     * @描述: 初始化默认综则分数（为支持批量重写
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-26 下午01:36:26
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public void initDefaultZcfs(String xn, String xq, String id) throws Exception {

        //根据ID取得班级代码和名称
        Map<String, String> map = getBjxxById(id);
        String bjdm = map.get("bjdm");
        //默认项目综测分
        dao.insertDefaultZcxmf(xn, xq, bjdm);
        //接口同步项目插入空分值
        dao.insertTbxmf(xn, xq, bjdm);

        //将比例为百分之零的初始化分数为0
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        //项目级别为“年级 ”
        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByNj(xn, xq, bjdm);
        }

        //项目级别为“院系”
        if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            dao.insertMrfsByXy(xn, xq, bjdm);
        }

    }


    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> 返回类型
     * @描述: 批量取班级信息
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-26 下午01:51:20
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getBjxxByIds(ZcfsModel model, User user) throws Exception {

        return dao.getBjxxByIds(model, user);
    }


    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> 返回类型
     * @描述: 按ID取班级信息
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-26 下午01:51:20
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public HashMap<String, String> getBjxxById(String id) {

        return dao.getBjxxById(id);
    }


    /**
     * @return File 返回类型
     * @描述: 创建导入模版
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-29 上午08:51:31
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public File createImportTemplate(ZcfsModel model, User user) throws Exception {

        WritableWorkbook wwb = null;

        //导出文件存放 的临时目录
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        List<HashMap<String, String>> bjxxMap = dao.getBjxxById(model, user);
        //创建导出文件
        File file = new File(tempDir.getPath() + "/" + "综测分导入模版.xls");
        file.setWritable(true);

        try {
            FileOutputStream stream = new FileOutputStream(file);
            //创建excel工作表
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("综测分导入模版", 0);

            ZcxmDao zcxmDao = new ZcxmDao();
            //可编辑综测分的综测项目
            CsszDao csszDao = new CsszDao();
            CsszModel csszModel = csszDao.getModel();
            List<HashMap<String, String>> zcxmList = null;

            if (!"10364".equals(Base.xxdm)) {
                if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
                    //综测项目级别为 年级
                    zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap, model, user);
                } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
                    //综测项目级别是为 院系
                    zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
                } else {
                    zcxmList = zcxmDao.getAllowEditZcfxm();
                }
            } else {
                if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
                    //综测项目级别是为 院系
                    zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
                } else {
                    zcxmList = zcxmDao.getAllowEditZcfxm();
                }
            }


            //固定表头学号、姓名
            ws.addCell(new Label(0, 0, "学号"));
            ws.addCell(new Label(1, 0, "姓名"));

            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(2 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //将项目代码作为注释，用于识别导入
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }
            /*成都市技师学院增加综测年月列*/
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {
                Label label = new Label(2 + zcxmList.size(), 0, "综测月份");
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //将项目代码作为注释，用于识别导入
                wcfeatures.setComment("请与综测月份下拉框选择的综测月份一致");
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }

            //不分页
            model.getPages().setPageSize(Integer.MAX_VALUE);
            //查询学生及已经录入的分数
            String szyf = csszDao.getCsz("szyf");
//            List<HashMap<String, String>> zcfList = new ArrayList<HashMap<String, String>>();
//            if ("1".equals(szyf)) {
//                zcfList = dao.getPageListOfYf(model, zcxmList, user);
//            } else {
//                zcfList = dao.getPageList(model, zcxmList, user);
//            }
//
//            for (int i = 0, j = zcfList.size(); i < j; i++) {
//                ws.addCell(new Label(0, i + 1, zcfList.get(i).get("xh")));
//                ws.addCell(new Label(1, i + 1, zcfList.get(i).get("xm")));
//                /*成都市技师学院增加综测年月列数据*/
//                if (!StringUtil.isNull(model.getZcyf())
//                        && !"undefined".equals(model.getZcyf())) {
//                    ws.addCell(new Label(2 + zcxmList.size(), i + 1, model.getZcyf()));
//                }
//                for (int m = 0, n = zcxmList.size(); m < n; m++) {
//                    ws.addCell(new Label(m + 2, i + 1, zcfList.get(i).get("fs" + m)));
//                }
//            }
//
//            WritableSheet ws1 = wwb.createSheet("项目等级名", 1);
//            //等级名称
//            List<String> DjmcList = getDjmc();
//            //等级list
//            List<HashMap<String, String>> djList = getDj();
//
//            if (!DjmcList.isEmpty()) {
//                WritableCellFormat wcf = new WritableCellFormat();
//                WritableFont wf = new WritableFont(WritableFont.ARIAL);
//                wf.setBoldStyle(WritableFont.BOLD);
//                wf.setPointSize(10);
//                wf.setColour(Colour.BLUE);
//                wcf.setAlignment(Alignment.LEFT);
//                wcf.setWrap(true);
//                wcf.setFont(wf);
//
//                for (int i = 0; i < DjmcList.size(); i++) {
//                    ws1.addCell(new Label(i, 0, DjmcList.get(i), wcf));
//
//                    int z = 1;
//                    for (int j = 0; j < djList.size(); j++) {
//                        if (DjmcList.get(i).equals(djList.get(j).get("xmmc"))) {
//                            ws1.addCell(new Label(i, z, djList.get(j).get("mc")));
//                            z++;
//                        }
//
//                    }
//                }
//            }


            wwb.write();
            wwb.close();
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;
    }

    /**
     * 针对所有学生的导入模板
     *
     * @param model
     * @return
     * @throws Exception
     */

    public File createImportTemplateForAll(ZcfsModel model) throws Exception {
        WritableWorkbook wwb = null;

        //导出文件存放 的临时目录
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        //创建导出文件
        File file = new File(tempDir.getPath() + "/" + "综测分导入模版.xls");
        file.setWritable(true);

        try {
            FileOutputStream stream = new FileOutputStream(file);
            //创建excel工作表
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("综测分导入模版", 0);

            ZcxmDao zcxmDao = new ZcxmDao();
            //可编辑综测分的综测项目
            CsszDao csszDao = new CsszDao();
            CsszModel csszModel = csszDao.getModel();
            List<HashMap<String, String>> zcxmList = zcxmDao.getAllowEditZcfxm();
            ws.addCell(new Label(0, 0, "学号"));
            ws.addCell(new Label(1, 0, "姓名"));

            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(2 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //将项目代码作为注释，用于识别导入
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }
            WritableSheet ws1 = wwb.createSheet("项目等级名", 1);
            //等级名称
            List<String> DjmcList = getDjmc();
            //等级list
            List<HashMap<String, String>> djList = getDj();

            if (!DjmcList.isEmpty()) {
                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setBoldStyle(WritableFont.BOLD);
                wf.setPointSize(10);
                wf.setColour(Colour.BLUE);
                wcf.setAlignment(Alignment.LEFT);
                wcf.setWrap(true);
                wcf.setFont(wf);

                for (int i = 0; i < DjmcList.size(); i++) {
                    ws1.addCell(new Label(i, 0, DjmcList.get(i), wcf));

                    int z = 1;
                    for (int j = 0; j < djList.size(); j++) {
                        if (DjmcList.get(i).equals(djList.get(j).get("xmmc"))) {
                            ws1.addCell(new Label(i, z, djList.get(j).get("mc")));
                            z++;
                        }

                    }
                }
            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;

    }


    /**
     * @param model
     * @throws FileNotFoundException
     * @throws IOException           void 返回类型
     * @throws BiffException
     * @描述: 导入综测分数
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-29 上午10:56:25
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public File importZcfs(ZcfsModel model, User user) throws Exception {

        FormFile importFile = model.getImportFile();
        //将FormFile 转换为File 对象
        File file = FileUtil.conversionFormFile(importFile);

        //获取Excel工作表
        Workbook book = Workbook.getWorkbook(file);
        //班级信息
        List<HashMap<String, String>> bjxxMap = dao.getBjxxById(model, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        //可编辑综测分的综测项目
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            //综测项目级别为 年级
            zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap, model, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            //综测项目级别是为 院系
            zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
        } else {
            zcxmList = zcxmDao.getAllowEditZcfxm();
        }

        WritableWorkbook wwb = Workbook.createWorkbook(file, book);
        WritableSheet ws = wwb.getSheet(0);
        //设置错误消息提示·列宽
        ws.setColumnView(zcxmList.size() + 2, 30);

        //参评班级学生
        //String[] stus = dao.getStuById(model, user);

        //导入模版与综测结构比较验证
        for (int i = 0, j = zcxmList.size(); i < j; i++) {

            String xmdm = zcxmList.get(i).get("xmdm");
            String xmmc = zcxmList.get(i).get("xmmc");

            CellFeatures cellFeatures = ws.getCell(2 + i, 0).getCellFeatures();

            if (cellFeatures == null) {
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }

            String cellComment = cellFeatures.getComment();
            String cellContent = ws.getCell(2 + i, 0).getContents();

            if (cellComment == null || cellContent == null) {
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }

            //验证下表头是否与综测结构的导出模版一致
            if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())) {
                throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
            }
        }

        /*
         * 检测导入数据
         */
        int rows = ws.getRows();
        List<String[]> params = new ArrayList<String[]>();

        boolean checkResult = true;

        for (int i = 1; i < rows; i++) {
            StringBuilder errorMessage = new StringBuilder();

            String xh = ws.getCell(0, i).getContents();
            String xm = ws.getCell(1, i).getContents();
            String[] param = null;
            if (StringUtil.isNull(xh) || StringUtil.isNull(xm)) {
                //学号和姓名怎么可以为空呢？
                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
//            } else if (!ArrayUtil.contains(stus, xh)) {//验证学号有效性
//                errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_FAIL));
            } else {

                for (int m = 0; m < zcxmList.size(); m++) {
                    String xmfz = ws.getCell(m + 2, i).getContents().trim();

                    if (StringUtil.isNull(xmfz)) {
                        //项目分数不能为空
                        //errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NULL));
                        //break;
                    }

                    //综测项目类型  4 为等级，可以录入非数字
                    String xmlx = zcxmList.get(m).get("xmlx");

                    //综测项目名称
                    String drxmmc = zcxmList.get(m).get("xmmc");

                    if (!"4".equals(xmlx)) {

                        //验证项目分数的 数字有效性
                        Double xmf = 0.0;

                        try {
                            if (StringUtils.isNotEmpty(xmfz)) {
                                xmf = Double.valueOf(xmfz.trim());
                            }
                        } catch (Exception e) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NOTNUMBER,
                                    new Object[]{zcxmList.get(m).get("xmmc")}));
                            break;
                        }

                        //验证长度
                        if (xmfz.length() > 10) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH));
                            break;
                        }

                        //验证最大、最小限制
                        Double max = Double.valueOf(zcxmList.get(m).get("zdfs"));
                        Double min = Double.valueOf(zcxmList.get(m).get("zxfs"));

                        if (xmf > max || xmf < min) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDZX,
                                    new Object[]{zcxmList.get(m).get("xmmc"), max, min}));
                            break;
                        }
                    } else {
                        boolean checkFlag = false;
                        //验证录入的等级是否符合要求
                        List<HashMap<String, String>> djList = dao.getDj();
                        for (int j = 0; j < djList.size(); j++) {
                            if (djList.get(j).get("mc").equals(xmfz) && djList.get(j).get("xmmc").equals(drxmmc)) {
                                xmfz = djList.get(j).get("dm");
                                checkFlag = true;
                                break;
                            }
                        }

                        if (!checkFlag) {
                            errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
                                    new Object[]{xmfz}));
                            break;
                        }
                    }
                    param = null;
                    if (!StringUtil.isNull(model.getZcyf())
                            && !"undefined".equals(model.getZcyf())) {//按月导入综测分
                        param = new String[]{xh, zcxmList.get(m).get("xn"),
                                zcxmList.get(m).get("xq"),
                                model.getZcyf(),
                                zcxmList.get(m).get("xmdm"),
                                xmfz, user.getUserName(),
                                xh, zcxmList.get(m).get("xmdm"), model.getZcyf()};//批量插入(月份)
                    } else {
                        param = new String[]{xh, zcxmList.get(m).get("xn"),
                                zcxmList.get(m).get("xq"),
                                zcxmList.get(m).get("xmdm"),
                                xmfz, user.getUserName(),
                                xh, zcxmList.get(m).get("xmdm")};
                    }

                    //按月导入综测分
                    if (!StringUtil.isNull(model.getZcyf())
                            && !"undefined".equals(model.getZcyf())) {

                        boolean pdcs = true;

                        String zcyf = ws.getCell(zcxmList.size() + 2, i).getContents();
                        if (!zcyf.equals(model.getZcyf())) {
                            //errorMessage.append(",只能导入"+model.getZcyf()+"的数据！");
                            pdcs = false;
                        }

                        if (pdcs) {
                            params.add(param);
                        }
                    } else {
                        params.add(param);
                    }
                }
            }

            //按月导入综测分
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {
                String zcyf = ws.getCell(zcxmList.size() + 2, i).getContents();
                if (!zcyf.equals(model.getZcyf())) {
                    errorMessage.append(",只能导入" + model.getZcyf() + "的数据！");
                }
            }


            //错误消息追加
            if (errorMessage.length() > 0) {

                WritableCellFormat wcf = new WritableCellFormat();
                WritableFont wf = new WritableFont(WritableFont.ARIAL);
                wf.setColour(Colour.RED);
                wcf.setFont(wf);
                wcf.setAlignment(Alignment.CENTRE);

                int y = 2;

                /**
                 * 成都市技师学院按月导入综测分，增加综测月份列
                 */
                if (!StringUtil.isNull(model.getZcyf())
                        && !"undefined".equals(model.getZcyf())) {
                    y = 3;
                }
                ws.addCell(new Label(zcxmList.size() + y, i, errorMessage.toString(), wcf));
                checkResult = false;
            }
        }


        //验证OK的导入，失败的导出
        if (!params.isEmpty()) {
            System.out.println("action========" + System.currentTimeMillis());
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {//按月导入综测分
                dao.batchInsertZcfsYf(params);//批量插入(月份)
                ZcfsModel form = new ZcfsModel();
                for (int j = 0; j < params.size(); j++) {
                    form.setXh(params.get(j)[0]);
                    form.setXn(params.get(j)[1]);
                    form.setXq(params.get(j)[2]);
                    form.setZcyf(params.get(j)[3]);
                    form.setXmdm(params.get(j)[4]);
                    form.setFs(params.get(j)[5]);
                    saveZcfsYfPl(form, user);
                }
            } else {
                dao.batchInsertZcfs(params);//批量插入
            }
            System.out.println("end==========" + System.currentTimeMillis());
        }

        //TODO


        if (!checkResult) {
            WritableSheet ws1 = wwb.createSheet("错误数据", 1);
            ws1.addCell(new Label(0, 0, ws.getCell(0, 0).getContents()));
            ws1.addCell(new Label(1, 0, ws.getCell(1, 0).getContents()));
            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(2 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //将项目代码作为注释，用于识别导入
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws1.addCell(label);
            }

            int z = 1;//已打印行号
            int t = 2;
            int x = 1;
            if (!StringUtil.isNull(model.getZcyf())
                    && !"undefined".equals(model.getZcyf())) {
                t = 3;
                x = 2;
            }
            for (int i = 0; i < rows; i++) {
                if (!StringUtils.isBlank((ws.getCell(zcxmList.size() + t, i).getContents()).trim())) {
                    ws1.addCell(new Label(0, z, ws.getCell(0, i).getContents()));
                    ws1.addCell(new Label(1, z, ws.getCell(1, i).getContents()));
                    for (int j = 0; j < zcxmList.size() + x; j++) {
                        ws1.addCell(new Label(j + 2, z, ws.getCell(j + 2, i).getContents()));
                    }
                    z++;
                }
            }

            wwb.removeSheet(0);
            wwb.write();
            wwb.close();
            return file;
        }
        return null;
    }


    /**
     * @param id
     * @param xmdm
     * @return
     * @throws Exception
     * @throws Exception boolean 返回类型
     * @描述: 同步接口数据
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-29 下午06:50:22
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean getIntefaceData(ZcfsModel form, String xmdm, User user) throws Exception {

        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {
            ZcxmModel model = zcxmDao.getModel(xmdm);

            for (int i = 0; i < bjList.size(); i++) {
                bool = dao.getIntefaceData(xmdm, bjList.get(i).get("bjdm"), model.getJktb());
                if (!bool) {
                    break;
                }
            }
            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdm
     * @param user
     * @return
     * @throws Exception boolean 返回类型
     * @throws
     * @描述：内蒙古电子日常行为分同步到综测分中
     * @作者：喻鑫源[工号：1206]
     * @日期：2018-3-2 上午10:05:48
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean getIntefaceDataNmgdz(ZcfsModel form, User user) throws Exception {

        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        boolean bool = false;

        try {

            for (int i = 0; i < bjList.size(); i++) {
                bool = dao.getIntefaceDataZdxm(bjList.get(i).get("bjdm"), " pro_zhcp_zhcj_12673");
                if (!bool) {
                    break;
                }
            }
            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    public boolean getIntefaceDataNmgzc(ZcfsModel form, User user) throws Exception {

        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        boolean bool = false;

        try {

            for (int i = 0; i < bjList.size(); i++) {
                bool = dao.getIntefaceDataZdxm(bjList.get(i).get("bjdm"), " pro_zhcp_zhcj_12942");
                if (!bool) {
                    break;
                }
            }
            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean 返回类型
     * @throws
     * @描述: 同步接口数据（上海戏剧个性化）
     * @作者：沈晓波[工号：1123]
     * @日期：2015-11-25 下午03:28:43
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean getIntefaceDataS(ZcfsModel form, String xmdms, User user) throws Exception {

        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "00");

            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }


    public boolean getIntefaceDataAll(ZcfsModel form, String xmdms, User user) throws Exception {
        List<HashMap<String, String>> jktbs = null;
        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            jktbs = zcxmDao.getJktbSHHYAll();
            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @throws
     * @描述:综测分取消查询
     * @作者：cq [工号：785]
     * @日期：2013-7-30 上午10:42:44
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZcfqxList(ZcfsModel t, User user)
            throws Exception {

        return dao.getZcfqxList(t, user);
    }

    /**
     * @param values
     * @param user
     * @return boolean 返回类型
     * @throws Exception
     * @throws
     * @描述:改变综测分，取消提交
     * @作者：cq [工号：785]
     * @日期：2013-7-30 下午01:39:42
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean cancelTj(User user, ZcfsModel zcfsForm) throws Exception {

        String id = zcfsForm.getId();

        boolean cancelTj = false;
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        String xn = csszModel.getXn();
        String xq = csszModel.getXq();


        //插入取消记录
        cancelTj = dao.insertTzjl(id, user, zcfsForm, xn, xq);

        if (!cancelTj) {
            return false;
        }

        //更新评奖人员库
        cancelTj = dao.updateCpmd(id, user, DEFAULT_QXTJ);


        if (cancelTj) {
            Map<String, String> map = getBjxxById(id);

            //计算综测分
            Thread thread = new Thread(new ComputeZcpm(map.get("xn"), map.get("xq"), map.get("bjdm")));
            thread.start();

            //	重庆理工大学个性化判断，班级状态提交完成后更新参数设置内的人数设定
            if (Globals.XXDM_CQLGDX.equals(Base.xxdm)) {
                // 分数提交完成后根据比例更新项目设置当中的人数
                updateRssz(map.get("bjdm"));
            }

        }

        return cancelTj;
    }

    /**
     * @param zcfsForm
     * @param user
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述:综测结果查询_参评组
     * @作者：cq [工号：785]
     * @日期：2013-7-31 下午04:09:13
     * @修改记录: 修改者名字-修改日期-修改内容
     */

    public List<HashMap<String, String>> getZcfjgList(ZcfsModel t, User user) throws Exception {

        //排名方式
        String pmfs = t.getPmfs();
        if (StringUtil.isNull(pmfs)) {
            pmfs = DEFAULT_PMFS;
        }

        ZcxmDao zcxmDao = new ZcxmDao();

        //前两级综测项目
        List<HashMap<String, String>> zcxmList = zcxmDao.getFirstAndSecondZcxm(t);

        return dao.getZcfjgList(t, user, pmfs, zcxmList);
    }


    public List<HashMap<String, String>> getZcfjgList(String xn, String xq, String bjdms) throws Exception {

        String[] bjdm = bjdms.split(",");
        String params = "";
        for (int i = 0; i < bjdm.length; i++) {
            params += "'" + bjdm[i] + "',";
        }
        if (params.length() > 0) {
            params = params.substring(0, params.length() - 1);
        }

        ZcxmDao zcxmDao = new ZcxmDao();
        //前两级综测项目
        List<HashMap<String, String>> zcxmList = zcxmDao.getFirstAndSecondZcxm();

        return dao.getZcfjgList(params, xn, xq, DEFAULT_PMFS, zcxmList);
    }

    /**
     * @param zcfsForm
     * @param user
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws Exception
     * @throws
     * @描述:查询综测取消记录
     * @作者：cq [工号：785]
     * @日期：2013-8-1 下午03:06:21
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZcfqxjlList(ZcfsModel zcfsForm,
                                                        User user) throws Exception {

        return dao.getZcfqxjlList(zcfsForm, user);
    }


    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @描述: 按学号查询指定评奖周期综测分
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-8-5 上午10:16:31
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZcfListByXh(String xh, String xn, String xq) {
        return dao.getZcfListByXh(xh, xn, xq);
    }

    /**
     * 按学年、学期、学号查询所有综测项目分
     */
    public List<HashMap<String, String>> getZcfListAllByXh(String xh, String xn, String xq) {
        return dao.getZcfListAllByXh(xh, xn, xq);
    }

    /**
     * 按学年、学期、(班级或学号)查询所有综测项目分（江苏信息职业技术学院）
     */
    public List<HashMap<String, String>> getZcfListAllByBjdm_13108(String bjdm, String xh, String xn, String xq) {
        return dao.getZcfListAllByBjdm_13108(bjdm, xh, xn, xq);
    }

    /**
     * 按学年、学期、班级代码查询综测项目平均分（江苏信息职业技术学院）
     */
    public HashMap<String, String> getZcfAvgByBjdm_13108(String bjdm, String xn, String xq) {
        return dao.getZcfAvgByBjdm_13108(bjdm, xn, xq);
    }

    /**
     * 按学年、学期、班级代码查询班级信息
     */
    public HashMap<String, String> getBjxx(String bjdm, String xn, String xq) {
        return dao.getBjxx(bjdm, xn, xq);
    }

    /**
     * 按学年、学号查询，返回上、下学期综测分
     */
    public List<HashMap<String, String>> getZcfListByXnXh(String xh, String xn) {
        return dao.getZcfListByXnXh(xh, xn);
    }

    /**
     * 获得学生成绩
     *
     * @param kcxz 选修/必修
     */
    public List<HashMap<String, String>> getCjListByXhXnXq(String xh, String xn, String xq, String kcxz) {
        return dao.getCjListByXhXnXq(xh, xn, xq, kcxz);
    }

    /**
     * 获得学生成绩
     *
     * @param kcxz 选修/必修
     */
    public List<HashMap<String, String>> getCjListByXhXn(String xh, String xn, String kcxz) {
        return dao.getCjListByXhXn(xh, xn, kcxz);
    }

    /**
     * 获得学生成绩
     *
     * @param kcxz 选修/必修
     */
    public List<HashMap<String, String>> getCjListByXh(String xh) {
        return dao.getCjListByXh(xh);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @描述: 按学号查询指定评奖周期智育分
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-8-5 上午10:16:31
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZyfListByXh(String xh, String xn, String xq) {
        return dao.getZyfListByXh(xh, xn, xq);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @描述: 按学号查询全部综测分
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-8-5 上午10:17:06
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getAllZcfListByXh(String xh) {
        return dao.getAllZcfListByXh(xh);
    }


    /**
     * @param zcfsForm
     * @param user
     * @return
     * @throws Exception File 返回类型
     * @描述: 获取班级综测分的导出文件
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-8-6 上午09:08:47
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public File getBjZcfFile(ZcfsModel zcfsForm, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();

        List<HashMap<String, String>> zcxmList = zcxmDao.getNoChildZcfxm();
        //构建导出表头
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("xh", "学号");
        map.put("xm", "姓名");

        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            map.put("fs" + i, zcxmList.get(i).get("xmmc"));
        }
        //导出数据
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);
        List<HashMap<String, String>> dataList = dao.getPageList(zcfsForm, zcxmList, user);

        IExportService export = new ExportExcelImpl();
        return export.getExportFile(map, dataList);
    }

    public File getBjZcfFileOfYf(ZcfsModel zcfsForm, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();

        List<HashMap<String, String>> zcxmList = zcxmDao.getNoChildZcfxm();
        //构建导出表头
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("xh", "学号");
        map.put("xm", "姓名");

        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            map.put("fs" + i, zcxmList.get(i).get("xmmc"));
        }
        //导出数据
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);
        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        if ("all".equals(zcfsForm.getZcyf())) {
            dataList = dao.getPageList(zcfsForm, zcxmList, user);
        } else {
            dataList = dao.getPageListOfYf(zcfsForm, zcxmList, user);
        }


        IExportService export = new ExportExcelImpl();
        return export.getExportFile(map, dataList);
    }

    /**
     * @param zcfsForm
     * @return
     * @throws Exception File 返回类型
     * @throws
     * @描述:综测分结果导出
     * @作者：cq [工号：785]
     * @日期：2013-8-6 上午11:34:28
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public File getZcfjgFile(ZcfsModel zcfsForm, User user) throws Exception {

        ZcxmDao zcxmDao = new ZcxmDao();

        List<HashMap<String, String>> zcxmList = zcxmDao.getFirstAndSecondZcxm(zcfsForm);

        //排名方式
        String pmfs = zcfsForm.getPmfs();
        if (StringUtil.isNull(pmfs)) {
            pmfs = DEFAULT_PMFS;
        }

        //构建导出表头
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("xh", "学号");
        map.put("xm", "姓名");
        map.put("bjmc", "班级");

        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            map.put("fs" + i, zcxmList.get(i).get("xmmc"));
            if (!"10335".equals(Base.xxdm)) {
                map.put("pm" + i, "排名");
            }
        }
        //导出数据
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);

        List<HashMap<String, String>> dataList = dao.getZcfjgList(zcfsForm, user, pmfs, zcxmList);

        IExportService export = new ExportExcelImpl();
        return export.getExportFile(map, dataList);
    }

    /**
     * 浙江机电职业技术学院 导出综测详表
     */
    public File getZcfjgFile_12861(ZcfsModel zcfsForm, User user) throws Exception {
        // 宋体,14,CENTRE,边框
        WritableCellFormat s14CentreFormat = new WritableCellFormat();
        WritableFont s14CentreFont = new WritableFont(WritableFont.createFont("宋体"), 14);
        s14CentreFormat.setFont(s14CentreFont);
        s14CentreFormat.setAlignment(Alignment.CENTRE);
        s14CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 宋体,12,CENTRE,边框
        WritableCellFormat s12CentreFormat = new WritableCellFormat();
        WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("宋体"), 12);
        s12CentreFormat.setFont(s12CentreFont);
        s12CentreFormat.setAlignment(Alignment.CENTRE);
        s12CentreFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 宋体,12,Left,边框
        WritableCellFormat s12LeftFormat = new WritableCellFormat();
        WritableFont s12LeftFont = new WritableFont(WritableFont.createFont("宋体"), 12);
        s12LeftFormat.setFont(s12LeftFont);
        s12LeftFormat.setAlignment(Alignment.LEFT);
        s12LeftFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 宋体,12,Right,边框
        WritableCellFormat s12RightFormat = new WritableCellFormat();
        WritableFont s12RightFont = new WritableFont(WritableFont.createFont("宋体"), 12);
        s12RightFormat.setFont(s12RightFont);
        s12RightFormat.setAlignment(Alignment.RIGHT);
        s12RightFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 左边框
        WritableCellFormat leftBorderFormat = new WritableCellFormat();
        leftBorderFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
        // 学年
        String xn = zcfsForm.getSearchModel().getSearch_tj_xn()[0];
        String[] xqArr = zcfsForm.getSearchModel().getSearch_tj_xq();
        String xq = "";
        if (xqArr != null && xqArr.length > 0) {
            xq = xqArr[0];
        }
        //学期名称
        List<HashMap<String, String>> xqList = Base.getXqList();
        String xqmc = "";
        for (HashMap<String, String> map : xqList) {
            if (map.get("xqdm").equals(xq)) {
                xqmc = map.get("xqmc");
                break;
            }
        }
        zcfsForm.setXn(xn);
        zcfsForm.setXn(xq);
        ZcxmDao zcxmDao = new ZcxmDao();
        List<HashMap<String, String>> zcxmList = zcxmDao.getZcxm_12861(zcfsForm);
        //排名方式
        String pmfs = zcfsForm.getPmfs();
        if (StringUtil.isNull(pmfs)) {
            pmfs = DEFAULT_PMFS;
        }
        //导出数据
        zcfsForm.getPages().setPageSize(Integer.MAX_VALUE);
        List<HashMap<String, String>> dataList = dao.getZcfjgList_12861(zcfsForm, user, pmfs, zcxmList);
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        // 创建导出文件
        File file = new File(tempDir.getPath() + "/" + String.valueOf(System.currentTimeMillis()) + ".xls");
        file.setWritable(true);
        FileOutputStream stream = new FileOutputStream(file);
        // 创建excel工作表
        WritableWorkbook wwb = Workbook.createWorkbook(stream);
        WritableSheet ws = wwb.createSheet("学生综合测评表", 0);
        String[] xnArrTemp = xn.split("-");
        String xnTemp = xnArrTemp[0].substring(2) + "/" + xnArrTemp[1].substring(2);
        // ============ 智育评定表（浙江机电职业技术学院） begin ==========
        HashMap<String, String> jqcjMap = zcxmDao.getGxhxm_searchTj_xnxq_12861(zcfsForm);
        String[] xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr = new String[]{"总成绩", "课程门数", jqcjMap.get("xmmc"), "名次", "不及格门数", "英语及其他"};
        String[] xg_zhcp_zypdb_zjjdzyjsxy_zd_arr = new String[]{"zcj", "kcms", "pjcj", "mc", "bjgms", "yyjqt"};
        // ============ 智育评定表（浙江机电职业技术学院） end ==========
        int col = 7 + zcxmList.size() + xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr.length;
        // 第1行
        ws.addCell(new Label(0, 0, "                             " + xnTemp + "学年" + xqmc + "学生综合测评表", s14CentreFormat));
        ws.mergeCells(0, 0, col, 0);
        ws.addCell(new Label(0, 1, "学号", s12LeftFormat));
        ws.mergeCells(0, 1, 0, 2);
        ws.addCell(new Label(1, 1, "姓名", s12LeftFormat));
        ws.mergeCells(1, 1, 1, 2);
        ws.addCell(new Label(2, 1, "班级", s12LeftFormat));
        ws.mergeCells(2, 1, 2, 2);
        int colTemp = 3;
        int colMergeStart = colTemp;
        int zfflagCount = 1;
        for (int i = 0, j = zcxmList.size(); i < j; i++) {
            String xmmc = zcxmList.get(i).get("xmmc");
            String qzbl = zcxmList.get(i).get("qzbl");
            String zfflag = zcxmList.get(i).get("zfflag");
            if ("N1".equals(zfflag)) {
                ws.addCell(new Label(colTemp, 2, "总分", s12LeftFormat));
                colTemp++;
                ws.addCell(new Label(colTemp, 2, "小计", s12LeftFormat));
                colTemp++;
                // ============== 标题合并 begin ==============
                ws.addCell(new Label(colMergeStart, 1, xmmc + "（" + qzbl + "%）", s12CentreFormat));
                ws.mergeCells(colMergeStart, 1, (colTemp - 1), 1);
//				System.out.println("==="+colMergeStart+"  " + (colTemp - 1));
                colMergeStart = colTemp;
                // ============== 标题合并 end ==============
                zfflagCount++;
                if (zfflagCount == 2) {
                    // ============ 智育评定表（浙江机电职业技术学院） begin ==========
                    for (int k = 0; k < xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr.length; k++) {
                        ws.addCell(new Label(colTemp, 2, xg_zhcp_zypdb_zjjdzyjsxy_zdmc_arr[k], s12LeftFormat));
                        colTemp++;
                    }
                    // ============ 智育评定表（浙江机电职业技术学院） end ==========
                }
            } else if ("N0".equals(zfflag)) {
                ws.addCell(new Label(colTemp, 1, "总分", s12LeftFormat));
                ws.mergeCells(colTemp, 1, colTemp, 2);
                colTemp++;
                ws.addCell(new Label(colTemp, 1, "名次", s12LeftFormat));
                ws.mergeCells(colTemp, 1, colTemp, 2);
                colTemp++;
                ws.addCell(new Label(colTemp, 1, "备注", s12LeftFormat));
                ws.mergeCells(colTemp, 1, colTemp, 2);
                colTemp++;
            } else {
                ws.addCell(new Label(colTemp, 2, xmmc, s12LeftFormat));
                colTemp++;
            }
        }
        int row = 3;
        // 数据集写入
        for (int m = 0; m < dataList.size(); m++) {
            HashMap<String, String> dataMap = dataList.get(m);
            ws.addCell(new Label(0, row, dataMap.get("xh"), s12LeftFormat));
            ws.addCell(new Label(1, row, dataMap.get("xm"), s12LeftFormat));
            ws.addCell(new Label(2, row, dataMap.get("bjmc"), s12LeftFormat));
            colTemp = 3; // 重置列
            zfflagCount = 1;
            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                String fs = dataMap.get("fs" + i);
                String zfflag = zcxmList.get(i).get("zfflag");
                if ("N1".equals(zfflag)) {
                    ws.addCell(new Label(colTemp, row, fs, s12RightFormat));
                    colTemp++;
                    String fsn = dataMap.get("fsn" + i);
                    ws.addCell(new Label(colTemp, row, fsn, s12RightFormat));
                    colTemp++;
                    zfflagCount++;
                    if (zfflagCount == 2) {
                        // ============ 智育评定表（浙江机电职业技术学院） begin ==========
                        for (int k = 0; k < xg_zhcp_zypdb_zjjdzyjsxy_zd_arr.length; k++) {
                            String xg_zhcp_zypdb_zjjdzyjsxy_zd_v = dataMap.get(xg_zhcp_zypdb_zjjdzyjsxy_zd_arr[k]);
                            ws.addCell(new Label(colTemp, row, xg_zhcp_zypdb_zjjdzyjsxy_zd_v, s12RightFormat));
                            colTemp++;
                        }
                        // ============ 智育评定表（浙江机电职业技术学院） end ==========
                    }
                } else if ("N0".equals(zfflag)) {
                    ws.addCell(new Label(colTemp, row, fs, s12RightFormat));
                    colTemp++;
                    String bjpm = dataMap.get("bjpm");
                    ws.addCell(new Label(colTemp, row, bjpm, s12RightFormat));
                    colTemp++;
                    ws.addCell(new Label(colTemp, row, "", s12RightFormat));
                    colTemp++;
                } else {
                    ws.addCell(new Label(colTemp, row, fs, s12RightFormat));
                    colTemp++;
                }
            }
            row++;
        }
        wwb.write();
        wwb.close();
        return file;
    }

    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> 返回类型
     * @throws
     * @描述:根据ID查询取消记录
     * @作者：cq [工号：785]
     * @日期：2013-8-15 上午09:31:28
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public HashMap<String, String> getQxjl(String id) {

        return dao.getQxjl(id);
    }


    /**
     * @param xh
     * @param xn
     * @param xq
     * @return HashMap<String                                                               ,                                                               String> 返回类型
     * @throws
     * @描述: 综测总分信息
     * @作者：屈朋辉[工号：445]
     * @日期：2013-8-29 上午11:07:23
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public HashMap<String, String> getZczfByXh(String xh, String xn, String xq) {
        return dao.getZczfByXh(xh, xn, xq);
    }


    /**
     * @param xh
     * @param xn
     * @param xq
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述:根据学号查询学生参评班级
     * @作者：cq [工号：785]
     * @日期：2013-11-10 上午11:26:33
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public HashMap<String, String> getCpbjListByXh(String xh, String xn, String xq) {
        return dao.getCpbjListByXh(xh, xn, xq);
    }


    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述:通过学号查询终测分数
     * @作者：ligl
     * @日期：2013-11-30 下午03:07:04
     * @修改记录:
     */
    public List<HashMap<String, String>> getZcfsList(String xh) {
        return dao.getZcfsList(xh);
    }

    /**
     * @描述:在校生信息打印学籍卡时获取综测信息（华东交通大学理工学院）
     * @作者：lgx [工号：1553]
     * @日期：2018/8/22 11:48
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [xh, rownum]
     * @return: java.util.List<java.util.HashMap                               <                               java.lang.String                               ,                               java.lang.String>>
     */
    public List<HashMap<String, String>> getZcfsListForWord(String xh, int rownum) {
        List<HashMap<String, String>> list = dao.getZcfsListForWord(xh, String.valueOf(rownum));
        int m = rownum - list.size();
        for (int i = 0; i < m; i++) {
            list.add(new HashMap<String, String>());
        }
        return list;
    }

    /**
     * @描述:通过学号查询综测分数
     */
    public List<HashMap<String, String>> getZcfsNList(String xh) {
        return dao.getZcfsNList(xh);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述:通过学号查询终测分数(老版本)
     * @作者：ligl
     * @日期：2013-11-30 下午03:07:04
     * @修改记录:
     */
    public List<HashMap<String, String>> getZcfsListOld(String xh) {
        return dao.getZcfsListOld(xh);
    }

    /**
     * @param zcfsForm
     * @param user
     * @return boolean 返回类型
     * @throws Exception
     * @throws
     * @描述:查看是否还有未提交记录
     * @作者：cq [工号：785]
     * @日期：2015-2-9 下午03:55:46
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean isSubmitInfo(ZcfsModel zcfsForm, User user) throws Exception {

        String num = dao.isSubmitInfo(zcfsForm, user);

        return Integer.valueOf(num) > 0;

    }

    /**
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述:获得等级list
     * @作者：cq [工号：785]
     * @日期：2015-2-13 下午03:39:06
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getDj() {

        return dao.getDj();
    }

    /**
     * 获得等级名称list
     *
     * @throws SQLException
     */
    public List<String> getDjmc() throws SQLException {
        return dao.getDjmc();
    }

    /**
     * @param zcfsForm
     * @param user
     * @return boolean 返回类型
     * @throws Exception
     * @throws
     * @描述:验证下载条数
     * @作者：cq [工号：785]
     * @日期：2015-3-4 上午11:33:26
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean isCanDownload(ZcfsModel model, User user, String num) throws Exception {

        List<HashMap<String, String>> bjxxMap = dao.getBjxxById(model, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        //可编辑综测分的综测项目
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();
        List<HashMap<String, String>> zcxmList = null;

        if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())) {
            //综测项目级别为 年级
            zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap, model, user);
        } else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())) {
            //综测项目级别是为 院系
            zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap, model, user);
        } else {
            zcxmList = zcxmDao.getAllowEditZcfxm();
        }

        //查询学生及已经录入的分数
        List<HashMap<String, String>> zcfList = dao.getPageList(model, zcxmList, user);

        boolean boo = false;
        //判断是否是否有结果集
        if (zcfList != null && 0 != zcfList.size()) {
            boo = Integer.parseInt(zcfList.get(0).get("total")) > Integer.parseInt(num) ? false : true;
        }

        return boo;

    }

    /**
     * @param xh
     * @return
     * @throws SQLException String 返回类型
     * @throws
     * @描述:班级人数
     * @作者：夏夏[工号：1104]
     * @日期：2015-6-9 上午09:06:48
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public String getBjrs(String xh) throws SQLException {

        return dao.getBjrs(xh);
    }

    /**
     * @param xh
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述:获取学生的所有综测排名
     * @作者：ChenQ[工号：856]
     * @日期：2015-6-29 下午03:21:44
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getAllZcpmWithXh(String xh) {
        return dao.getAllZcpmWithXh(xh);
    }

    public File createImportTemplateDc(List<HashMap<String, String>> zcfList, List<HashMap<String, String>> zcxmList, User user) throws Exception {

        WritableWorkbook wwb = null;

        //导出文件存放 的临时目录
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

//		List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);
        //创建导出文件
        File file = new File(tempDir.getPath() + "/" + "综测分导出.xls");
        file.setWritable(true);

        try {
            FileOutputStream stream = new FileOutputStream(file);
            //创建excel工作表
            wwb = Workbook.createWorkbook(stream);

            WritableSheet ws = wwb.createSheet("综测分导出", 0);

            ZcxmDao zcxmDao = new ZcxmDao();
            //可编辑综测分的综测项目
            CsszDao csszDao = new CsszDao();
            CsszModel csszModel = csszDao.getModel();

//			if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
//				//综测项目级别为 年级
//				zcxmList = zcxmDao.getAllowEditZcfxmByNj(bjxxMap,model,user);
//			} else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())){
//				//综测项目级别是为 院系
//				zcxmList = zcxmDao.getAllowEditZcfxmByXy(bjxxMap,model,user);
//			} else {
//				zcxmList = zcxmDao.getAllowEditZcfxm();
//			}

            //固定表头学号、姓名
            ws.addCell(new Label(0, 0, "学号"));
            ws.addCell(new Label(1, 0, "姓名"));//TODO
            ws.addCell(new Label(2, 0, "班级名称"));

            for (int i = 0, j = zcxmList.size(); i < j; i++) {
                Label label = new Label(3 + i, 0, zcxmList.get(i).get("xmmc"));
                WritableCellFeatures wcfeatures = new WritableCellFeatures();
                //将项目代码作为注释，用于识别导入
                wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
                label.setCellFeatures(wcfeatures);
                ws.addCell(label);
            }


            for (int i = 0, j = zcfList.size(); i < j; i++) {
                ws.addCell(new Label(0, i + 1, zcfList.get(i).get("xh")));
                ws.addCell(new Label(1, i + 1, zcfList.get(i).get("xm")));
                ws.addCell(new Label(2, i + 1, zcfList.get(i).get("bjmc")));
                for (int m = 0, n = zcxmList.size(); m < n; m++) {
                    ws.addCell(new Label(m + 3, i + 1, zcfList.get(i).get("fs" + m)));
                }
            }


            wwb.write();
            wwb.close();
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
        }

        file.setWritable(true);
        return file;
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean 返回类型
     * @throws
     * @描述: 学业水平评价综测分（浙大宁波理工）
     * @作者：沈晓波[工号：1123]
     * @日期：2016-5-19 上午10:51:26
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean getIntefaceDataXysp(ZcfsModel form, String xmdms, User user) throws Exception {

        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "01");

            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean 返回类型
     * @throws
     * @描述: 思想道德评价综测分（浙大宁波理工）
     * @作者：沈晓波[工号：1123]
     * @日期：2016-5-19 上午10:51:26
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean getIntefaceDataSxdd(ZcfsModel form, String xmdms, User user) throws Exception {

        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "02");

            for (int j = 0; j < jktbs.size(); j++) {
                for (int i = 0; i < bjList.size(); i++) {
                    bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), jktbs.get(j).get("jktb"));
                    if (!bool) {
                        break;
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * @param form
     * @param xmdms
     * @param user
     * @return
     * @throws Exception boolean 返回类型
     * @描述：同步综测分_只传入bjdm
     * @作者：卓耐[工号:1391]
     * @日期：2017年2月28日
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean getIntefaceDatazcf(ZcfsModel form, User user) throws Exception {
        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);
        boolean bool = false;
        //执行学校对应的存储过程
        String proName = "";
        if ("13011".equals(Base.xxdm)) {
            proName = "pro_zhcp_qdjdzctb";
        }
        for (HashMap<String, String> map : bjList) {
            bool = dao.getIntefaceData(map.get("bjdm"), proName);
            if (!bool) {
                break;
            }
        }
        return bool;

    }

    /**
     * @param bjdm
     * @param xn
     * @param xq
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述:月份提交状态
     * @作者：yxy[工号：1206]
     * @日期：2016-9-5 下午03:23:45
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getYfTjzt(String bjdm, String xn, String xq) {
        return dao.getYfTjzt(bjdm, xn, xq);
    }

    /**
     * @param model
     * @param user
     * @return
     * @throws Exception String[] 返回类型
     * @throws
     * @描述: 成都市技师学院按月提交
     * @作者：yxy[工号：1206]
     * @日期：2016-9-6 上午10:38:38
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public String[] isNotCanSubmitYf(ZcfsModel model, User user) throws Exception {

        String[] ids = model.getId().split(",");

        List<String> canId = new ArrayList<String>();//可以提交的id集合
        StringBuffer noSubmit = new StringBuffer();
        boolean isNotCanSubmit = false;
        int i = 0;  //记录提交个数

        if (null == ids || ids.length <= 0) {
            return null;
        }
        for (String str : ids) {
            model.setId(str);
            if (isCanSubmitYf(model)) {
                submitZcfs(model, user); //单个提交
                canId.add(str);//记录id
                i++;
            } else {
                HashMap<String, String> bjInfo = dao.getBjInfo(str);
                noSubmit.append(bjInfo.get("bjmc"));
                noSubmit.append(",</br>");
                isNotCanSubmit = true;
            }
        }


        String str = noSubmit.toString();
        //去除最后多余逗号
        str = isNotCanSubmit ? str : _BCZSCID;
        return new String[]{String.valueOf(i), str};

    }

    /**
     * @param model
     * @return
     * @throws Exception boolean 返回类型
     * @throws
     * @描述: 成都市技术学院检验综测分是否可以提交
     * @作者：yxy[工号：1206]
     * @日期：2016-9-6 上午10:44:30
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean isCanSubmitYf(ZcfsModel model) throws Exception {

        boolean isCanSubmit = false;

        //将默认项目 和 同步接口项目的分数加到分数记录表
        initDefaultZcfs(model.getXn(), model.getXq(), model.getId());

        //可录项目数*学生数  <= 分数记录数
        isCanSubmit = Boolean.valueOf(dao.getSfyWlrYf(model));

        if (!isCanSubmit) {
            return false;
        }

        //检测是否有NULL分值 的分数记录
        isCanSubmit = Integer.valueOf(dao.getNullZcfYf(model)) == 0;

        return isCanSubmit;
    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @throws
     * @描述: 成都市技师学院按月提交综测分-综测分取消查询
     * @作者：yxy[工号：1206]
     * @日期：2016-9-6 下午02:05:18
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZcfqxListYf(ZcfsModel t, User user)
            throws Exception {
        return dao.getZcfqxListYf(t, user);
    }

    /**
     * @param user
     * @param zcfsForm
     * @return
     * @throws Exception boolean 返回类型
     * @throws
     * @描述: 取消按月提交综测分
     * @作者：yxy[工号：1206]
     * @日期：2016-9-6 下午03:47:13
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean cancelTjOfYf(User user, ZcfsModel zcfsForm) throws Exception {

        String id = zcfsForm.getId();

        boolean cancelTj = false;
        CsszDao csszDao = new CsszDao();
        CsszModel csszModel = csszDao.getModel();

        String xn = csszModel.getXn();
        String xq = csszModel.getXq();


        //插入取消记录
        cancelTj = dao.insertTzjlOfYf(id, user, zcfsForm, xn, xq);

        if (!cancelTj) {
            return false;
        }

        //更新评奖人员库
        cancelTj = dao.updateCpmdOfYf(id, user, DEFAULT_QXTJ);


        if (cancelTj) {
            //成都市技师学院，某班级单位在该综测周期内所有月份记录都取消提交，更新xg_zhcp_fstjjlb表
            dao.updateAytjzcfLastOne(id, xn, xq);
            List<HashMap<String, String>> list = getBjxxByIdOfYf(id);

            //计算综测分
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                Thread thread = new Thread(new ComputeZcpm(map.get("xn"), map.get("xq"), map.get("bjdm")));
                thread.start();
            }


            //	重庆理工大学个性化判断，班级状态提交完成后更新参数设置内的人数设定
//			if(Globals.XXDM_CQLGDX.equals(Base.xxdm)){
//				// 分数提交完成后根据比例更新项目设置当中的人数
//				updateRssz(map.get("bjdm"));
//			}
//
        }

        return cancelTj;
    }

    /**
     * @param id
     * @return HashMap<String                                                               ,                                                               String> 返回类型
     * @throws
     * @描述: 成都市技师学院取消综测分获取班级信息
     * @作者：yxy[工号：1206]
     * @日期：2016-9-6 下午04:30:27
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getBjxxByIdOfYf(String id) {
        return dao.getBjxxByIdOfYf(id);
    }

    /**
     * @param xn
     * @param xq
     * @return List<HashMap                                                               <                                                               String                                                               ,                                                               String>> 返回类型
     * @throws
     * @描述: 成都市技师学院按月提交统计查询
     * @作者：yxy[工号：1206]
     * @日期：2016-9-7 上午11:33:19
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZcfAyTjTjcxList(String xn, String xq) {
        return dao.getZcfAyTjTjcxList(xn, xq);

    }

    /**
     * @param t
     * @param user
     * @return
     * @throws Exception List<HashMap<String,String>> 返回类型
     * @描述: 综测班级提交查询
     * @作者：Penghui.Qu [工号：445]
     * @日期：2013-7-24 上午10:16:40
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZcbjListCk(ZcfsModel t, User user, String tjzt, String yf)
            throws Exception {
        return dao.getZcbjListCk(t, user, tjzt, yf);
    }

    /**
     * @param xmmc
     * @param xn
     * @param xq
     * @return HashMap<String                                                               ,                                                               String> 返回类型
     * @throws
     * @描述: 根据项目名称学年学期获取综测分数[通用]
     * @作者：喻鑫源[工号：1206]
     * @日期：2017-11-15 上午11:37:19
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public HashMap<String, String> getZcfsByXmXnXqXh(String xmmc, String xn, String xq, String xh) {
        return dao.getZcfsByXmXnXqXh(xmmc, xn, xq, xh);
    }

    /**
     * 获取各科成绩、总分及排名信息列表.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public List<HashMap<String, String>> getXscjList(ZcfsModel zcfsForm, User user) throws Exception {

        return dao.getXscjList(zcfsForm, user);
    }

    /**
     * 生成成绩汇总表Excel文件.
     *
     * @param bjcjhzModel
     * @return java.io.File
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:43
     * @throw Exception
     */
    public File getCjhzbFile(BjcjhzModel bjcjhzModel) throws Exception {

        Map<String, XscjhzModel> xscjhzModelMap = bjcjhzModel.getXscjhzModelMap();
        Collection<XscjhzModel> collection = xscjhzModelMap.values();
        File file = null;

        if (collection != null && !collection.isEmpty()) {
            XscjhzModel[] xscjhzModelArr = collection.toArray(new XscjhzModel[]{});

            //一级标题字体
            WritableFont titleFont_level1 = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            //二级标题字体
            WritableFont titleFont_level2 = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            //表头字体
            WritableFont headFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
            //正文字体
            WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);

            //一级标题单元格格式
            WritableCellFormat titleCf_level1 = new WritableCellFormat(titleFont_level1);
            //二级标题单元格格式
            WritableCellFormat titleCf_level2 = new WritableCellFormat(titleFont_level2);
            //表头单元格格式
            WritableCellFormat headCf_oh = new WritableCellFormat(headFont);
            WritableCellFormat headCf_ov = new WritableCellFormat(headFont);
            //正文单元格格式
            WritableCellFormat bodyCf = new WritableCellFormat(bodyFont);

            titleCf_level1.setAlignment(Alignment.CENTRE);
            titleCf_level1.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleCf_level1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            titleCf_level2.setAlignment(Alignment.CENTRE);
            titleCf_level2.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleCf_level2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            headCf_oh.setAlignment(Alignment.CENTRE);
            headCf_oh.setVerticalAlignment(VerticalAlignment.CENTRE);
            headCf_oh.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            headCf_ov.setAlignment(Alignment.CENTRE);
            headCf_ov.setVerticalAlignment(VerticalAlignment.CENTRE);
            headCf_ov.setOrientation(Orientation.VERTICAL);
            headCf_ov.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            bodyCf.setAlignment(Alignment.CENTRE);
            bodyCf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            String xn = bjcjhzModel.getXn() == null ? "" : bjcjhzModel.getXn();
            String xqmc = bjcjhzModel.getXqmc() == null ? "" : bjcjhzModel.getXqmc();
            String bjmc = bjcjhzModel.getBjmc() == null ? "" : bjcjhzModel.getBjmc();
            String bzr = bjcjhzModel.getBzr() == null ? "" : bjcjhzModel.getBzr();
            int bjrs = xscjhzModelArr.length;
            Map<String, Set<String>> bjkcMap = bjcjhzModel.getBjkcMap();

            String title_level1 = xn + "学年" + xqmc + "学生成绩汇总表";
            String title_level2 = "班级：" + bjmc + "    " + "班主任：" + bzr + "    " + "班级人数：" + bjrs;

            String fileName = bjmc + ".xls";
            file = new File(System.getProperty("java.io.tmpdir"), fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            //创建工作簿
            WritableWorkbook wwb = Workbook.createWorkbook(file);
            //创建工作表
            WritableSheet sheet = wwb.createSheet(bjmc, 0);

            //设置各列列宽
            sheet.setColumnView(0, 12);
            sheet.setColumnView(1, 12);
            sheet.setColumnView(2, 6);

            int s = 0;
            for (Set<String> kcSet : bjkcMap.values()) {
                int size = kcSet.size();
                //合并单元格
                sheet.mergeCells(3 + s, 3, 3 + s + size - 1, 3);    //课程类型
                s += size;
            }

            for (int n = 0; n < s; n++) {
                sheet.setColumnView(3 + n, 6);
            }

            sheet.setColumnView(3 + s, 6);
            sheet.setColumnView(4 + s, 6);
            sheet.setColumnView(5 + s, 6);
            sheet.setColumnView(6 + s, 6);
            sheet.setColumnView(7 + s, 6);
            sheet.setColumnView(8 + s, 6);

            //设置行高
//			sheet.setRowView(4, true);
            sheet.setRowView(4, 1600);

            //合并单元格
            sheet.mergeCells(0, 0, 8 + s, 0);    //xx学年xx学期学生成绩汇总表
            sheet.mergeCells(0, 1, 8 + s, 1);    //班级 班主任 学生人数
            sheet.mergeCells(0, 2, 0, 4);    //学号
            sheet.mergeCells(1, 2, 1, 4);    //姓名
            sheet.mergeCells(2, 2, 2, 4);    //性别
            sheet.mergeCells(3, 2, 3 + s, 2);    //学习成绩
            sheet.mergeCells(3 + s, 3, 3 + s, 4);        //平均分
            sheet.mergeCells(4 + s, 2, 4 + s, 4);        //学期德育学分
            sheet.mergeCells(5 + s, 2, 5 + s, 4);        //综合成绩
            sheet.mergeCells(6 + s, 2, 8 + s, 2);        //名次
            sheet.mergeCells(6 + s, 3, 6 + s, 4);        //文化名次
            sheet.mergeCells(7 + s, 3, 7 + s, 4);        //德育学分名次
            sheet.mergeCells(8 + s, 3, 8 + s, 4);        //综合排名

            //创建标题及表头
            Label title_1 = new Label(0, 0, title_level1, titleCf_level1);
            Label title_2 = new Label(0, 1, title_level2, titleCf_level2);

            Label head_xh = new Label(0, 2, "学号", headCf_ov);
            Label head_xm = new Label(1, 2, "姓名", headCf_ov);
            Label head_xb = new Label(2, 2, "性别", headCf_ov);
            Label head_xxcj = new Label(3, 2, "学习成绩", headCf_oh);
            Label head_pjf = new Label(3 + s, 3, "平均分", headCf_ov);
            Label head_xqdyxf = new Label(4 + s, 2, "学期德育学分", headCf_ov);
            Label head_zhcj = new Label(5 + s, 2, "综合成绩", headCf_ov);
            Label head_mc = new Label(6 + s, 2, "名次", headCf_oh);
            Label head_whmc = new Label(6 + s, 3, "文化名次", headCf_ov);
            Label head_dyxfmc = new Label(7 + s, 3, "德育学分名次", headCf_ov);
            Label head_zhpm = new Label(8 + s, 3, "综合排名", headCf_ov);


            sheet.addCell(title_1);
            sheet.addCell(title_2);

            sheet.addCell(head_xh);
            sheet.addCell(head_xm);
            sheet.addCell(head_xb);
            sheet.addCell(head_xxcj);
            sheet.addCell(head_pjf);

            sheet.addCell(head_xqdyxf);
            sheet.addCell(head_zhcj);
            sheet.addCell(head_mc);
            sheet.addCell(head_whmc);
            sheet.addCell(head_dyxfmc);
            sheet.addCell(head_zhpm);

            int k = 0;
            for (Map.Entry<String, Set<String>> entry : bjkcMap.entrySet()) {
                String kclx = entry.getKey();
                Set<String> kcSet = entry.getValue();
                int size = kcSet.size();

                Label label = new Label(3 + k, 3, kclx, headCf_oh);
                sheet.addCell(label);
                int j = 0;
                for (String kcmc : kcSet) {
                    Label label_kcmc = new Label(3 + k + j, 4, kcmc, headCf_ov);
                    sheet.addCell(label_kcmc);
                    j++;
                }
                k += size;
            }

            //遍历创建单元格
            for (int j = 0; j < xscjhzModelArr.length; j++) {
                XscjhzModel xscjhzModel = xscjhzModelArr[j];
                Label body_xh = new Label(0, j + 5, xscjhzModel.getXh(), bodyCf);    //学号
                Label body_xm = new Label(1, j + 5, xscjhzModel.getXm(), bodyCf);    //姓名
                Label body_xb = new Label(2, j + 5, xscjhzModel.getXb(), bodyCf);    //性别

                sheet.addCell(body_xh);
                sheet.addCell(body_xm);
                sheet.addCell(body_xb);

                Map<String, String> cjMap = xscjhzModel.getCjMap();
                int m = 0;
                for (Set<String> kcSet : bjkcMap.values()) {
                    int size = kcSet.size();
                    int h = 0;
                    for (String kcmc : kcSet) {
                        Label label_kccj = new Label(3 + m + h, j + 5, cjMap.get(kcmc), bodyCf);
                        sheet.addCell(label_kccj);
                        h++;
                    }
                    m += size;
                }

                Label body_pjf = new Label(3 + m, j + 5, xscjhzModel.getPjf(), bodyCf);    //平均分
                Label body_xqdyxf = new Label(4 + m, j + 5, xscjhzModel.getDyf(), bodyCf);    //学期德育学分
                Label body_zhcj = new Label(5 + m, j + 5, xscjhzModel.getZhf(), bodyCf);    //综合成绩
                Label body_whmc = new Label(6 + m, j + 5, xscjhzModel.getPjfpm(), bodyCf);    //文化名次
                Label body_dyxfmc = new Label(7 + m, j + 5, xscjhzModel.getDyfpm(), bodyCf);    //德育学分名次
                Label body_zhpm = new Label(8 + m, j + 5, xscjhzModel.getZhfpm(), bodyCf);    //综合排名

                sheet.addCell(body_pjf);
                sheet.addCell(body_xqdyxf);
                sheet.addCell(body_zhcj);
                sheet.addCell(body_whmc);
                sheet.addCell(body_dyxfmc);
                sheet.addCell(body_zhpm);
            }
            wwb.write();
            wwb.close();
        }

        return file;
    }


    /**
     * 获取总成绩排名.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public String getCjpm(String xn, String xh, String bjmc) {
        return dao.getCjpm(xn, xh, bjmc);
    }

    /**
     * 获取必修课数.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public String getBxks(String xn, String xh) {
        return dao.getBxks(xn, xh);
    }

    /**
     * 获取必修课及格数.
     *
     * @param zcfsForm
     * @param user
     * @return java.util.List<java.util.HashMap                                                               <                                                               java.lang.String                                                               ,                                                               java.lang.String>>
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2017-12-06 16:08
     * @throw Exception
     */
    public String getBxkjgs(String xn, String xh) {
        return dao.getBxkjgs(xn, xh);
    }

    /**
     * @param zcfsForm
     * @param xmdms
     * @param user
     * @return boolean 返回类型
     * @throws Exception
     * @throws
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：张昌路[工号：982]
     * @日期：2018-5-18 下午01:43:07
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean getIntefaceData_12688(String type, ZcfsModel zcfsForm, String xmdms, User user) throws Exception {

        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(zcfsForm, user);

        ZcxmDao zcxmDao = new ZcxmDao();
        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(xmdms, "12688");
            String proName = "";
            String xmmc = "";
            if ("zy".equals(type)) {
                proName = "pro_zhcpzyftb_12688";
                xmmc = "智育分";
            } else if ("ty".equals(type)) {
                proName = "pro_zhcptyftb_12688";
                xmmc = "体育分";
            } else {
                proName = "pro_zhcpdyftb_12688";
                xmmc = "德育分";
            }

            for (int j = 0; j < jktbs.size(); j++) {
                if (xmmc.equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), proName);
                        if (!bool) {
                            break;
                        }
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    public boolean getIntefaceData_13431(ZcfsModel form, User user) throws Exception {
        ZcxmDao zcxmDao = new ZcxmDao();
        // 批量获得班级信息
        List<HashMap<String, String>> bjList = getBjxxByIds(form, user);

        boolean bool = false;

        try {

            List<HashMap<String, String>> jktbs = zcxmDao.getJktbS(form.getXmdms(), "13431");

            for (int j = 0; j < jktbs.size(); j++) {
                if ("体育分".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcptyftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
                if ("智育分".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcpzyftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
                if ("加分".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcpjiaftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
                if ("减分".equals(jktbs.get(j).get("xmmc"))) {
                    for (int i = 0; i < bjList.size(); i++) {
                        bool = dao.getIntefaceData(jktbs.get(j).get("xmdm"), bjList.get(i).get("bjdm"), "pro_zhcpjianftb_13431");
                        if (!bool) {
                            break;
                        }
                    }
                }
            }

            return bool;
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
    }

    /**
     * 同步学分成绩到综测智育分
     *
     * @return
     */
    public boolean tbXfcj(User user) throws Exception {
        try {
            CsszModel csszModel = csszDao.getModel();
            String zcxmId = dao.getZcxmIdByMc("课程考核成绩", csszModel.getXn());
            List<HashMap<String, String>> xfcjList = dao.getXfcj(csszModel.getXn(), null);
            List<String[]> result=new ArrayList<>();

            for (HashMap<String, String> hashMap : xfcjList) {
                String[] param={hashMap.get("xh"),hashMap.get("xn"),"on",zcxmId,
                hashMap.get("xfcj"),user.getUserName(),hashMap.get("xh"),zcxmId};
                result.add(param);
            }
            dao.batchWhZcfs(result);
        } catch (Exception e) {
            throw new SystemException(MessageKey.SYS_SYNC_FAIL);
        }
        return true;

    }

}

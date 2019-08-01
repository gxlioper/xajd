package com.zfsoft.xgxt.xszz.xfjm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import common.newp.StringUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:
 * @类功能描述:
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/3 10:49
 */
public class XfjmService extends SuperServiceImpl<XfjmForm,XfjmDao> {
    public XfjmService(){}
    public static volatile XfjmService xfjmService = null;
    public String url = "";
    public static XfjmService getXfjmService(){
        if(xfjmService == null){
            synchronized (XfjmService.class){
                if(xfjmService == null){
                    xfjmService = new XfjmService();
                }
            }
        }
        return xfjmService;
    }

    private XfjmDao xfjmDao = XfjmDao.getXfjmDao();
    private XfjmCsszService csszService = new XfjmCsszService();
    private ShlcInterface shlc = new CommShlcImpl();
    private ShlcDao shlcDao = new ShlcDao();
    
    /**
     * @描述: 根据申请id获取学费减免申请信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/3 14:14
     * @修改记录: 修改人-修改时间-修改描述
     * @param sqid 
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getById(String sqid){
        return dao.getById(sqid);
    }

    /**
     * @描述: 保存学费减免申请信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/4 13:34
     * @修改记录: 修改人-修改时间-修改描述
     * @param model
	* @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> sqSave(XfjmForm model, User user){
        Map<String, Object> result = new HashMap<>();
        try {
            if (StringUtils.isNull(model.getXh()) && "stu".equals(user.getUserType())) {
                model.setXh(user.getUserName());
            }
            //判断时间段是否开启
            CsszModel cssz = csszService.getModel();
            if (cssz != null && !"1".equals(cssz.getSqkg())) {
                result.put("code", 0);
                result.put("msg", "暂未开放学费减免申请!");
                return result;
            }
            //判断学生是否完成困难生认定
            HashMap<String, String> knsrdjg = getKnsrdjg(model.getXh(), Base.currXn);
            if (knsrdjg == null || StringUtils.isNull(knsrdjg.get("guid"))) {
                result.put("code", 0);
                result.put("msg", "请先完成困难生认定后进行此操作!");
                return result;
            }
            if (StringUtils.isNotNull(model.getId())) {
                //修改
                //判断审核状态（提交审核状态只能为未提交 5待审核）
                if(!"0".equals(model.getShzt()) && !"5".equals(model.getShzt())){
                    result.put("code", 0);
                    result.put("msg", "提交数据有误，请重试!");
                    return result;
                }
                HashMap<String,String> xfjm = xfjmDao.getById(model.getId());
                if(xfjm != null && StringUtils.isNotNull(xfjm.get("shzt"))){
                    //判断之前状态
                    String oldShzt = xfjm.get("shzt");
                    //已审核
                    if(!"0".equals(oldShzt)){
                        result.put("code", 0);
                        result.put("msg", "只能修改未提交的申请信息!");
                        return result;
                    }
                    //如果之前为未提交，现在提交则重新申请审核流
                    if("0".equals(oldShzt) && "5".equals(model.getShzt())){
                        //保存审核流
                        try {
                             shlc.runSubmit(model.getId(), xfjm.get("shlc"), model.getXh(), "xszz_new_xfjmsh.do", "xszz_new_xfjmsq.do");
                        }catch (Exception e){
                            e.printStackTrace();
                            result.put("code", 0);
                            result.put("msg", "请勿重复提交申请!");
                            return result;
                        }
                    }
                    //修改申请信息
                    boolean flag = xfjmDao.updateById(model);
                    if(flag){
                        result.put("code", 1);
                        result.put("msg", "提交成功!");
                        result.put("id",model.getId());
                        return result;
                    }else{
                        result.put("code", 0);
                        result.put("msg", "提交失败!");
                        return result;
                    }
                }else{
                    result.put("code", 0);
                    result.put("msg", "申请信息有误，提交失败!");
                    return result;
                }
            } else {
                //申请(审核中)
                //判断本学年是否已经申请过了
                HashMap<String,String> xfjms = xfjmDao.getCurrXfjm(model.getXh(),Base.currXn);
                if(xfjms != null && StringUtils.isNotNull(xfjms.get("id"))){
                    result.put("code", 0);
                    result.put("msg", "本学年已有申请记录，请勿重复申请!");
                    return result;
                }
                //保存申请信息
                model.setSqsj(DateTranCnDate.timeStampToDate(System.currentTimeMillis()));
                model.setShlc(cssz.getSplc());
                String guid = UniqID.getInstance().getUniqIDHash();
                model.setId(guid.toUpperCase());
                if(StringUtils.isNull(model.getXn())){
                    model.setXn(Base.currXn);
                }
                boolean flag = xfjmDao.insert(model);
                //提交申请
                if (flag && "5".equals(model.getShzt())) {
                    //保存审核流
                    try {
                        flag = shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "xszz_new_xfjmsh.do", "xszz_new_xfjmsq.do");
                    }catch (Exception e){
                        e.printStackTrace();
                        result.put("code", 0);
                        result.put("msg", "请勿重复提交申请!");
                    }
                }
                if(flag){
                    result.put("code", 1);
                    result.put("msg", "操作成功!");
                    result.put("id",model.getId());
                    return result;
                }else{
                    result.put("code", 0);
                    result.put("msg", "操作失败!");
                    return result;
                }
            }
            //保存审核流信息
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", -1);
            result.put("msg", e.getMessage());

        }
        return result;
    }


    public Map<String,Object> jgSave(XfjmForm model, User user){
        Map<String, Object> result = new HashMap<>();
        try {
            //判断时间段是否开启
            CsszModel cssz = csszService.getModel();
            if (cssz != null && !"1".equals(cssz.getSqkg())) {
                result.put("code", 0);
                result.put("msg", "暂未开放学费减免申请!");
                return result;
            }
            //判断学生是否完成困难生认定
            HashMap<String, String> knsrdjg = getKnsrdjg(model.getXh(), Base.currXn);
            if (knsrdjg == null || StringUtils.isNull(knsrdjg.get("guid"))) {
                result.put("code", 0);
                result.put("msg", "请先完成困难生认定后进行此操作!");
                return result;
            }
            if (StringUtils.isNotNull(model.getId())) {
                //修改
                boolean flag = xfjmDao.updateJgId(model);
                if(flag){
                    result.put("code", 1);
                    result.put("msg", "提交成功!");
                    result.put("id",model.getId());
                    return result;
                }else{
                    result.put("code", 0);
                    result.put("msg", "提交失败!");
                    return result;
                }
            } else {
                //增加
                //判断本学年是否已经申请过了
                HashMap<String,String> xfjms = xfjmDao.getCurrXfjm(model.getXh(),Base.currXn);
                if(xfjms != null && StringUtils.isNotNull(xfjms.get("id"))){
                    result.put("code", 0);
                    result.put("msg", "本学年已有申请记录，请勿重复申请!");
                    return result;
                }
                //保存申请信息
                model.setSqsj(DateTranCnDate.timeStampToDate(System.currentTimeMillis()));
                String guid = UniqID.getInstance().getUniqIDHash();
                model.setId(guid.toUpperCase());
                if(StringUtils.isNull(model.getXn())){
                    model.setXn(Base.currXn);
                }
                boolean flag = xfjmDao.insert(model);
                if(flag){
                    result.put("code", 1);
                    result.put("msg", "操作成功!");
                    result.put("id",model.getId());
                    return result;
                }else{
                    result.put("code", 0);
                    result.put("msg", "操作失败!");
                    return result;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", -1);
            result.put("msg", e.getMessage());

        }
        return result;
    }

    /**
     * @描述: 根据id删除申请信息（未提交/退回申请删除）
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/3 19:54
     * @修改记录: 修改人-修改时间-修改描述
     * @param sqid 申请id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> removeById(String sqid){
        Map<String,Object> result = new HashMap<>();
        HashMap<String,String> xfjmInfo = xfjmDao.getById(sqid);
        if(xfjmInfo != null && xfjmInfo.containsKey("id")){
            //只能删除退回的和未提交的申请
            if(!"0".equalsIgnoreCase(xfjmInfo.get("shzt"))){
                result.put("code",0);
                result.put("msg","只能删除未提交的申请信息!");
            }else{
                try {
                    shlc.deleteShjl(sqid);
                }catch (Exception e){
                    e.printStackTrace();
                }
                boolean flag = xfjmDao.removeById(sqid);
                if(flag){
                    result.put("code",1);
                    result.put("msg","删除成功!");
                }else{
                    result.put("code",0);
                    result.put("msg","删除失败!");
                }
            }
        }else{
            result.put("code",0);
            result.put("msg","删除信息有误!");
        }
        return result;
    }

    public Map<String,Object> removeJg(String sqid){
        Map<String,Object> result = new HashMap<>();
        HashMap<String,String> xfjmInfo = xfjmDao.getById(sqid);
        if(xfjmInfo != null && xfjmInfo.containsKey("id")){
            //只能删除退回的和未提交的申请
            if(StringUtils.isNotNull(xfjmInfo.get("shlc"))){
                //存在审核流程，先删除审核流程
                try {
                    shlc.deleteShjl(sqid);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            boolean flag = xfjmDao.removeById(sqid);
            if(flag){
                result.put("code",1);
                result.put("msg","删除成功!");
            }else{
                result.put("code",0);
                result.put("msg","删除失败!");
            }

        }else{
            result.put("code",0);
            result.put("msg","删除信息有误!");
        }
        return result;
    }

    /**
     * @描述: 修改审核状态
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/3 20:20
     * @修改记录: 修改人-修改时间-修改描述
     * @param id 申请id
     * @param shzt 审核状态 0未提交 1通过 2未通过 3退回 5审核中
     * @return boolean
     **/
    public Map<String,Object> sqztxg(String id,String shzt){
        Map<String,Object> result = new HashMap<>();
        try {
            //申请参数设置
            CsszModel cssz = csszService.getModel();
            if(!"1".equals(cssz.getSqkg())){
                result.put("code",0);
                result.put("msg","暂未开放学费减免申请!");
            }else{
                HashMap<String,String> xfjmInfo = xfjmDao.getById(id);
                if(xfjmInfo == null || !xfjmInfo.containsKey("id")){
                    result.put("code",0);
                    result.put("msg","申请信息有误!");
                }else{
                    if(!"5".equals(shzt) && !"3".equals(shzt)){
                        result.put("code",0);
                        result.put("msg","提交信息有误!");
                        return result;
                    }else{
                        if("5".equals(shzt)){
                            //提交申请 保存审核流
                            //保存审核流
                            shlc.runSubmit(xfjmInfo.get("id"), xfjmInfo.get("shlc"), xfjmInfo.get("xh"), "xszz_new_xfjmsh.do", "xszz_new_xfjmsq.do");
                        }else{
                            //撤销删除审核流程
                            shzt = Constants.YW_WTJ;//未提交
                            //只有刚提交并且第一级未审核的前提下，申请人可以撤销
                            try {
                                //撤销操作
                                boolean sfysh = shlc.firstStepCancle(id, xfjmInfo.get("shlc"));
                            }catch (Exception e){
                                //不存在审核信息或在审核流中
                                result.put("code", 0);
                                result.put("msg", "已在审核中无法撤销!");
                                e.printStackTrace();
                                return result;
                            }
                        }
                        //修改审核状态
                        boolean flag = xfjmDao.sqztxg(id,shzt);
                        if(flag){
                            result.put("code",1);
                            result.put("msg","操作成功!");
                        }else{
                            result.put("code",0);
                            result.put("msg","操作失败!");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @描述: 根据学号学年，获取困难生认定结果信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/4 10:41
     * @修改记录: 修改人-修改时间-修改描述
     * @param xh 学号
     * @param xn 学年
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getKnsrdjg(String xh,String xn){
        return xfjmDao.getKnsrdjg(xh,xn);
    }

    //待审核
    public List<HashMap<String,String>> getShPageList(XfjmForm model,User user){
        try {
            return xfjmDao.getDshPageList(model,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //审核结果
    public List<HashMap<String,String>> getJgPageList(XfjmForm model,User user){
        try {
            return xfjmDao.getJgPageList(model,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HashMap<String,String>> getJgList(XfjmForm model,User user){
        try {
            return xfjmDao.getJgList(model,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @描述: 学费减免信息审核
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/8 10:28
     * @修改记录: 修改人-修改时间-修改描述
     * @param t
	* @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> shSave(XfjmForm t,User user){
        Map<String,Object> result = new HashMap<>();
        ShxxModel shlModel = new ShxxModel();
        shlModel.setYwid(t.getId());//业务id
        shlModel.setShlc(t.getShlc());//审核流程
        shlModel.setShr(user.getUserName());//审核人
        shlModel.setShyj(t.getShyj());//审核意见
        shlModel.setShzt(t.getShjg());//审核状态
        shlModel.setThgw(t.getThgw());//退回岗位
        shlModel.setGwid(t.getXtgwid());//岗位id
        shlModel.setSqrid(t.getXh());//申请人学号
        shlModel.setTzlj("xszz_new_xfjmsh.do");
        shlModel.setTzljsq("xszz_new_xfjmsq.do");
        //审核流信息存储
        try{
            String zhzt = shlc.runAuditing(shlModel);
            XfjmForm xfjmForm = new XfjmForm();
            xfjmForm.setId(t.getId());
            xfjmForm.setShzt(zhzt);//审核流状态 通过 不通过 退回 待审核
            // 最后一级审核通过时，把减免金额插入学费减免表
            if(zhzt.equals(Constants.SH_TG)){
                xfjmForm.setXfjmje(t.getXfjmje());
                //修改审核状态
            }
            boolean flag = xfjmDao.updateShById(xfjmForm);
            if(flag){
                result.put("code",1);
                result.put("msg","操作成功!");
            }else {
                result.put("code",0);
                result.put("msg","操作失败");
            }
        }catch (Exception e){
            //审核异常
            e.printStackTrace();
            result.put("code",-1);
            result.put("msg",e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * @描述: 根据审核流程及待审核岗位判断是否是最后一级审核
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/8 11:35
     * @修改记录: 修改人-修改时间-修改描述
     * @param shlc 审核流程
	* @param dshGwid 待审核岗位
     * @return boolean
     **/
    public boolean isLastGw(String shlc,String dshGwid){
        // 查询下一级审批岗位

        String xjgw = shlcDao.getNextGwid(shlc, dshGwid);
        //判断当前审核岗位是否为最后一级
        String lastGwid = shlcDao.getLastGwid(shlc);
        if (lastGwid.equalsIgnoreCase(dshGwid) && StringUtils.isNull(xjgw)) {
            return true;
        }
        return false;
    }

    /**
     * @描述: 学费减免信息导入
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/11 9:31
     * @修改记录: 修改人-修改时间-修改描述
     * @param request
	* @param response 
     * @return java.lang.String
     **/
    public String importXfjmjg(HttpServletRequest request, HttpServletResponse response){
        try{
            //1.获取待导入数据
            List<HashMap<String,String>> jmxxList = new ArrayList<>();
            String path = request.getAttribute("filePath").toString();
            Sheet sheet = Workbook.getWorkbook(new File(path)).getSheet(0);
            //获取工作簿行数
            int rowCount = sheet.getRows();
            //判断模板是否正确
            String modelTitle = ExcelMethods.getOneCellContent(sheet, 0, 0);
            if(modelTitle == null || "".equals(modelTitle)
                    || modelTitle.trim().indexOf("学号")==-1){
                return "模板有误，请重新下载导入！";
            }
            HashMap<String,String> item;
            List<String[]> failGSList = new ArrayList<String[]>();
            int dataNums = rowCount - 1;
            for(int rowIndex =1;rowIndex < rowCount;rowIndex++){
                String xh = ExcelMethods.getOneCellContent(sheet, 0, rowIndex);
                String jmje = ExcelMethods.getOneCellContent(sheet, 1, rowIndex);
                String jmxn = ExcelMethods.getOneCellContent(sheet, 1, rowIndex);
                if(xh != null && xh.trim().length() > 0){
                    item = new HashMap<String, String>();
                    //判断学号是否存在困难生认定表中
                    if(jmxn == null || jmxn.trim().length()!=9){
                        jmxn = Base.currXn;//不存在学年则默认使用当前学年
                    }
                    HashMap<String,String> knsrdInfo = dao.knsrdinfo(xh,jmxn);
                    if(knsrdInfo == null || !knsrdInfo.containsKey("xh") ||StringUtils.isNull(knsrdInfo.get("xh"))){
                        //本学年不存在困难生认定信息
                        String[] faillxs = {xh,jmje,jmxn,"该生此学年不存在困难生认定信息!"};
                        failGSList.add(faillxs);
                    }else{
                        if(knsrdInfo.containsKey("id") && StringUtils.isNotNull(knsrdInfo.get("id"))){
                            //存在困难生信息并且该生已有减免记录
                            String[] faillxs = {xh,jmje,jmxn,"该生此学年学费减免信息已存在!"};
                            failGSList.add(faillxs);
                        }else{
                            //存在认定信息且不存在学费减免表中，添加信息
                            item.put("xh",xh.trim());//学号
                            item.put("xn",jmxn.trim());//减免学年
                            item.put("jmje",jmje);//减免金额
                            jmxxList.add(item);
                        }
                    }
                }else {
                    String[] faillxs = {xh,jmje,jmxn,"学号不能为空!"};
                    failGSList.add(faillxs);
                }
            }
            int successCount = 0;
            //2.判断数据有效性 存在学号 新增 不存在学号 学号已存在
            if(jmxxList.size()> 0){
                //3.导入数据
                int[] resultArray = dao.batchImport(jmxxList);
                if(resultArray != null && resultArray.length == jmxxList.size()){
                    for (int i = 0; i < resultArray.length; i++) {
                        switch (resultArray[i]) {
                            case 1:
                                successCount++;
                                break;
                            case 0:
                            case -1:
                                String[] failXhs = {jmxxList.get(i).get("xh"),jmxxList.get(i).get("jmje"),jmxxList.get(i).get("jmxn"),"导入信息有误或已存在"};
                                failGSList.add(failXhs);
                                break;
                        }
                    }
                }
            }
            if(failGSList.size() > 0){
                //存在导入失败数据
                response.reset();
                response.setContentType("application/vnd.ms-excel");
                String[] colCN = {"学号","减免金额","减免学年","错误信息"};
                String[] info = {"共计"+dataNums+"条数据","成功导入"+(dataNums-failGSList.size())+"条","失败"+failGSList.size()+"条","有误数据"+failGSList.size()+"条"};
                failGSList.add(info);
                Excel2Oracle.exportData(failGSList,colCN ,colCN, response.getOutputStream());
                return "数据导入成功，但有"+failGSList.size()+"条数据有误或已存在!";
            }
            if(successCount>0){
                return "导入成功！成功导入数据"+successCount+"条！";
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "导入失败！";
    }


    public HashMap<String,String> getZypmInfo(String xh,String xn){
        //获取专业班级排名
        HashMap<String,String> zybjpmInfo =  xfjmDao.getZybjpm(xh,xn);
        //获取专业排名
        HashMap<String,String> zypmInfo = xfjmDao.getZypm(xh,xn);
        HashMap<String,String> pmInfo = new HashMap<>();
        String zybjpm = "";
        String zypm = "";
        if(zybjpmInfo != null && zybjpmInfo.containsKey("zybjpm")){
            zybjpm = zybjpmInfo.get("zybjpm");
        }
        if(zypmInfo != null && zypmInfo.containsKey("zypm")){
            zypm = zypmInfo.get("zypm");
        }
        pmInfo.put("zybjpm",zybjpm);
        pmInfo.put("zypm",zypm);
        return pmInfo;
    }
 }

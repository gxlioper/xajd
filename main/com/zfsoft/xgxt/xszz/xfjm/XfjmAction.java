package com.zfsoft.xgxt.xszz.xfjm;

import com.alibaba.fastjson.JSON;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 资助管理-学费减免
 * @类功能描述: 学费减免申请审核结果
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/3 10:49
 */
public class XfjmAction extends SuperAction<XfjmForm,XfjmService> {

    private XfjmService xfjmService = XfjmService.getXfjmService();
    XsxxService xsxxService = new XsxxService();
    /**
     * @描述: 分页获取学费减免列表(申请)
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/3 11:10
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url="xszz_new_xfjmsq.do",rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        String doType = request.getParameter("doType");
        String status = request.getParameter("status");
        if (QUERY.equalsIgnoreCase(doType)) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            model.setStatus(status);
            List<HashMap<String, String>> resultList = xfjmService.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("status",status);
        request.setAttribute("path", "xszz_new_xfjm_sq.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("sqList");
    }

    /**
     * @描述: 学费减免审核列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/18 15:32
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url="xszz_new_xfjmsh.do",rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getShPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        String doType = request.getParameter("doType");
        if (QUERY.equalsIgnoreCase(doType)) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = xfjmService.getShPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xszz_new_xfjmsh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("shList");
    }

    /**
     * @描述: 学费减免结果列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/18 15:32
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url="xszz_new_xfjmjg.do",rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getJgPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        String doType = request.getParameter("doType");
        if (QUERY.equalsIgnoreCase(doType)) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = xfjmService.getJgPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xszz_new_xfjmjg.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jgList");
    }

    /**
     * @描述: 学费减免结果导出
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/18 15:32
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = xfjmService.getJgList(model, user);//查询出所有记录，不分页
        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }

    /**
     * @描述: 申请信息查询
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/8 8:57
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	 * @param form
	 * @param request
	 * @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward xfjmSq(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        String status = request.getParameter("status");
        String xh = request.getParameter("xh");
        if("stu".equals(user.getUserType())) {
            xh = user.getUserName();
        }
        String sqid = model.getId();
        HashMap<String,String> sqinfo = new HashMap<>();
        if(StringUtils.isNotNull(sqid)){
            //申请id存在则获取申请信息
            sqinfo = xfjmService.getById(sqid);
            request.setAttribute("sqinfo",sqinfo);
            request.setAttribute("shzt",sqinfo.get("shzt"));
            //获取专业排名信息
            HashMap<String,String> pmInfo = xfjmService.getZypmInfo(xh,sqinfo.get("xn"));
            request.setAttribute("pminfo",pmInfo);
        }else{
            //申请的时候获取专业排名默认为本学期
            HashMap<String,String> pmInfo = xfjmService.getZypmInfo(xh,Base.currXn);
            request.setAttribute("pminfo",pmInfo);
        }
        //加载学生基本信息
        HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
        request.setAttribute("xsjbxx",xsjbxx);
        //判断本学年是否有困难生认定结果记录
        HashMap<String,String> knrsdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
        if(knrsdjg != null && knrsdjg.containsKey("guid")){
            request.setAttribute("knsrdjg",knrsdjg);
            request.setAttribute("sfrdkns","1");
        }else{
            //是否认定困难生
            request.setAttribute("sfrdkns","0");
        }
        request.setAttribute("status",status);
        return mapping.findForward("xfjmsq");
    }

    /**
     * @描述: 学费减免结果编辑
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/18 15:33
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward xfjmJgEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        String status = request.getParameter("status");
        String xh = request.getParameter("xh");
        String sqid = model.getId();
        HashMap<String,String> sqinfo = new HashMap<>();
        if(StringUtils.isNotNull(sqid)){
            //申请id存在则获取申请信息
            sqinfo = xfjmService.getById(sqid);
            request.setAttribute("sqinfo",sqinfo);
            request.setAttribute("shzt",sqinfo.get("shzt"));
        }else{
            request.setAttribute("sqinfo",sqinfo);
        }

        //加载学生基本信息
        if(StringUtils.isNotNull(xh)){
            HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("xsjbxx",xsjbxx);
            //判断本学年是否有困难生认定结果记录
            HashMap<String,String> knrsdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
            if(knrsdjg != null && knrsdjg.containsKey("guid")){
                request.setAttribute("knsrdjg",knrsdjg);
                request.setAttribute("sfrdkns","1");
            }else{
                //是否认定困难生
                request.setAttribute("sfrdkns","0");
            }
        }
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("knsrd");
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("status",status);
        request.setAttribute("type",status);
        request.setAttribute("userType",user.getUserType());
        return mapping.findForward("xfjmjgAdd");
    }

    /**
     * @描述: 学费减免审核
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/8 9:14
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	 * @param form
	 * @param request
	 * @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward xfjmSh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        String status = request.getParameter("status");
        String xh = request.getParameter("xh");
        String shid = request.getParameter("shid");
        String xtgwid = request.getParameter("xtgwid");
        String sqid = model.getId();
        HashMap<String,String> sqinfo = new HashMap<>();
        if(StringUtils.isNotNull(sqid)){
            //申请id存在则获取申请信息
            sqinfo = xfjmService.getById(sqid);
            request.setAttribute("sqinfo",sqinfo);
            request.setAttribute("shzt",sqinfo.get("shzt"));
            //获取专业排名信息
            HashMap<String,String> pmInfo = xfjmService.getZypmInfo(xh,sqinfo.get("xn"));
            request.setAttribute("pminfo",pmInfo);
        }
        //加载学生基本信息
        HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
        request.setAttribute("xsjbxx",xsjbxx);
        //判断本学年是否有困难生认定结果记录
        HashMap<String,String> knrsdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
        if(knrsdjg != null && knrsdjg.containsKey("guid")){
            request.setAttribute("knsrdjg",knrsdjg);
            request.setAttribute("sfrdkns","1");
        }else{
            //是否认定困难生
            request.setAttribute("sfrdkns","0");
        }
        //判断是否是最后一级审核
        if(sqinfo.containsKey("shlc")){
            String shlc = sqinfo.get("shlc");
            boolean islastShlc = xfjmService.isLastGw(shlc,xtgwid);
            request.setAttribute("islastshl",islastShlc);
        }
        request.setAttribute("xtgwid",xtgwid);
        request.setAttribute("shid",shid);
        request.setAttribute("status",status);
        return mapping.findForward("shEdit");
    }

    /**
     * @描述: 申请提交
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/3 14:05
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward sqSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        //申请提交
        Map<String,Object> result = xfjmService.sqSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }
    /**
     * @描述: 学费减免结果维护
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/9 20:36
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward jgSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        //申请提交
        Map<String,Object> result = xfjmService.jgSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }
    
    /**
     * @描述: 获取困难生认定信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/9 20:05
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward getknsrdInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String xh = request.getParameter("xh");
        HashMap<String, String> knsrdjg = xfjmService.getKnsrdjg(xh, Base.currXn);
        Map<String,Object> result = new HashMap<>();
        if (knsrdjg == null || StringUtils.isNull(knsrdjg.get("guid"))) {
            result.put("code",0);
            result.put("msg","暂无记录");

        }else{
            result.put("code",1);
            result.put("msg","获取成功！");
            result.put("data",knsrdjg);
        }
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @描述: 根据id删除学费减免申请信息（未提交/退回记录）
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/3 19:57
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward removeById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String sqid = request.getParameter("id");
        Map<String,Object> result = xfjmService.removeById(sqid);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @描述: 学费减免结果移除
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/18 15:33
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward removeJg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String sqid = request.getParameter("id");
        Map<String,Object> result = xfjmService.removeJg(sqid);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @描述: 申请状态修改(撤销、提交)
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/3 20:06
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward sqztxg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String sqid = request.getParameter("id");
        String shzt = request.getParameter("shzt");
        Map<String,Object> result = xfjmService.sqztxg(sqid,shzt);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @描述: 审核信息保存
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/18 15:34
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward shSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        XfjmForm model = (XfjmForm) form;
        User user = getUser(request);
        Map<String,Object> result = xfjmService.shSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }


    /**
     * @描述: 批量导入学费减免信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/11 9:32
     * @修改记录: 修改人-修改时间-修改描述
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward importXfjmjg(ActionMapping mapping,ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String doType = request.getParameter("doType");
        if("import".equalsIgnoreCase(doType)){
            uploadFile(mapping, form, request, response);
            //导入数据
            String back = xfjmService.importXfjmjg(request,response);
            request.setAttribute("back", back);
            String sfdcExcel = (String)request.getAttribute("sfdcExcel");
            if(sfdcExcel != null && "yes".equals(sfdcExcel)){
                //return mapping.findForward("");
            }
        }
        return mapping.findForward("importXfjmjg");
    }


    private ActionForward uploadFile(ActionMapping mapping,ActionForm form,
                                     HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        String dir = servlet.getServletContext().getRealPath("/upload");
        File file = new File(dir);
        if(!file.exists()){
            file.mkdir();
        }
        HttpSession session = request.getSession();
        String fName = (String)session.getAttribute("userName");
        String tabName = request.getParameter("tabName");
        Timestamp date = new Timestamp(System.currentTimeMillis());
        fName += date.toString().substring(0, 19);
        fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
        XfjmForm data = (XfjmForm)form;
        FormFile dataFile = data.getFile();
        if(dataFile == null){
            return mapping.findForward("false");
        }
        int size = dataFile.getFileSize();
        InputStream is = dataFile.getInputStream();
        String filePath = dir +"/"+ fName+".xls";
        OutputStream os = new FileOutputStream(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[size];
        while ((bytesRead = is.read(buffer, 0, size)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        os.close();
        request.setAttribute("tabName", tabName);
        request.setAttribute("filePath", filePath);
        return mapping.findForward("success");
    }
}

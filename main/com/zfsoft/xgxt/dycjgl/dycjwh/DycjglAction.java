package com.zfsoft.xgxt.dycjgl.dycjwh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zjly.ylbx.YlbxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DycjglAction extends SuperAction<DycjglForm,DycjglService> {
    private static final String url = "dycjgl_dycjwh_dycjgl.do";
    public static final String DRCGBZ = "导入成功！";
    public static final String DRSBBZ = "导入失败,请仔细核对【出错记录.xls】！";
    public static final String KBG = "空excel表格！";
    public static final String KFILE = "没有文件！";

    public ActionForward dycjglList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dycjglList");
    }

    public ActionForward dycjglListQuery(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglForm model = (DycjglForm) form;
        DycjglService service = new DycjglService();
        HashMap<String,String> xnxqMap = service.getXnXqInfo();
        model.setXq(xnxqMap.get("xqdm"));
        model.setXn(xnxqMap.get("xn"));
        User user = getUser(request);
        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        //查询结果集
        List<HashMap<String,String>> resultList = service.getPageList(model,user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    public ActionForward bjmdfswh(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        List<HashMap<String,String>> xmList = service.getXmList();
        request.setAttribute("xmList",xmList);
        request.setAttribute("bjdm",model.getBjdm());
        //是否合格
        List<HashMap<String,String>> sfhg = service.getSfhg();
        request.setAttribute("sfhgList", JSONArray.fromObject(sfhg));
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjmdfswh");
    }


    public ActionForward viewstuList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        HashMap<String,String> xnxqMap = service.getXnXqInfo();
        model.setXq(xnxqMap.get("xqdm"));
        model.setXn(xnxqMap.get("xn"));
        //学生list
        List<HashMap<String,String>> resultList =  service.getStudentList(model);
        //项目list
        List<HashMap<String,String>> xmList = service.getXmList();
        request.setAttribute("xmList",xmList);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        FormModleCommon.commonRequestSet(request);
        return null;
    }



    public ActionForward viewBkList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        HashMap<String,String> xnxqMap = service.getXnXqInfo();
        model.setXq(xnxqMap.get("xqdm"));
        model.setXn(xnxqMap.get("xn"));
        //学生list
        List<HashMap<String,String>> resultList =  service.getBkList(model);
        //项目list
        List<HashMap<String,String>> xmList = service.getXmList();
        request.setAttribute("xmList",xmList);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        FormModleCommon.commonRequestSet(request);
        return null;
    }


    public ActionForward savedycj(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        User user = getUser(request);
        //获取德育成绩设置的学期和学年
        HashMap<String,String> xnXqInfo =service.getXnXqInfo();
        model.setXn(xnXqInfo.get("xn"));
        model.setXqdm(xnXqInfo.get("xqdm"));
        boolean result = service.savedycj(model, user);
        if (!result){
            //如果失败，则提示
            response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
        }
        return null;
    }


    public ActionForward saveBkqk(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DycjglForm model = (DycjglForm)form;
        DycjbkService service = new DycjbkService();
        DycjglService dycjservice = new DycjglService();
        User user = getUser(request);
        //获取德育成绩设置的学期和学年
        HashMap<String,String> xnXqInfo =dycjservice.getXnXqInfo();
        model.setXn(xnXqInfo.get("xn"));
        model.setXqdm(xnXqInfo.get("xqdm"));
        boolean result = service.savebkqk(model, user);
        if (!result){
            //如果失败，则提示
            response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
        }
        return null;
    }



    public ActionForward saveHg(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DycjglForm model = (DycjglForm)form;
        DycjbkService service = new DycjbkService();
        DycjglService dycjservice = new DycjglService();
        User user = getUser(request);
        //获取德育成绩设置的学期和学年
        HashMap<String,String> xnXqInfo =dycjservice.getXnXqInfo();
        model.setXn(xnXqInfo.get("xn"));
        model.setXqdm(xnXqInfo.get("xqdm"));
        boolean result = service.savebkqk(model, user);
        if (!result){
            //如果失败，则提示
            response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
        }
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglForm model = (DycjglForm)form;
        DycjglService dycjservice = new DycjglService();
        HashMap<String,String> xnXqInfo =dycjservice.getXnXqInfo();
        model.setXn(xnXqInfo.get("xn"));
        model.setXq(xnXqInfo.get("xqdm"));
        model.setBjdm(request.getParameter("bjdm"));
        DycjglService service = new DycjglService();
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList =  service.getStudentByBjdm(model);
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



    public ActionForward exportDataBhg(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglForm model = (DycjglForm)form;
        model.setBjdm(request.getParameter("bjdm"));
        DycjglService dycjservice = new DycjglService();
        HashMap<String,String> xnXqInfo =dycjservice.getXnXqInfo();
        model.setXn(xnXqInfo.get("xn"));
        model.setXq(xnXqInfo.get("xqdm"));
        DycjglService service = new DycjglService();
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList =  service.getBkBhg(model);
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

    public ActionForward toImport(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String bjdm = request.getParameter("bjdm");
        request.setAttribute("bjdm",bjdm);
        return mapping.findForward("toImport");
    }
    public ActionForward downloadExcel(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String bjdm = request.getParameter("bjdm");
        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        HashMap<String,String> xnXqInfo =service.getXnXqInfo();
        model.setXn(xnXqInfo.get("xn"));
        model.setXq(xnXqInfo.get("xqdm"));
        model.setBjdm(bjdm);
        User user = getUser(request);
        File file = service.createImportTemplate(model, user);
        FileUtil.outputExcel(response, file);
        return null;
    }


    public ActionForward importDycj(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        User user = getUser(request);
        request.setAttribute("bjdm",model.getBjdm());
        try {
            File file = service.importDycj(model,user);

            if (file != null){
                FileUtil.outputExcel(response, file);
                return null;
            }
            request.setAttribute("result", true);
            request.setAttribute("message", MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
        } catch (SystemException e) {
            request.setAttribute("result", false);
            request.setAttribute("message", e.getMessage());
        }

        return toImport(mapping, model, request, response);

    }

    public ActionForward importBHG(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String bjdm = request.getParameter("bjdm");
        request.setAttribute("bjdm",bjdm);
        return mapping.findForward("importBHG");
    }


    public ActionForward downloadExclBhg(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String bjdm = request.getParameter("bjdm");
        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        HashMap<String,String> xnXqInfo =service.getXnXqInfo();
        model.setXn(xnXqInfo.get("xn"));
        model.setXq(xnXqInfo.get("xqdm"));
        model.setBjdm(bjdm);
        User user = getUser(request);
        File file = service.createImportTemplateBhg(model, user);
        FileUtil.outputExcel(response, file);
        return null;
    }

    public ActionForward importDycjBhg(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DycjglForm model = (DycjglForm)form;
        DycjglService service = new DycjglService();
        User user = getUser(request);
        request.setAttribute("bjdm",model.getBjdm());
        try {
            File file = service.importDycjBhg(model,user);

            if (file != null){
                FileUtil.outputExcel(response, file);
                return null;
            }
            request.setAttribute("result", true);
            request.setAttribute("message", MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
        } catch (SystemException e) {
            request.setAttribute("result", false);
            request.setAttribute("message", e.getMessage());
        }
        return importBHG(mapping, model, request, response);

    }

    public ActionForward viewBjmd(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DycjglService service = new DycjglService();
        String bjdm = request.getParameter("bjdm");
        request.setAttribute("bjdm",bjdm);
        List<HashMap<String,String>> xmList = service.getXmList();
        request.setAttribute("xmList",xmList);
        return mapping.findForward("viewBjmd");
    }

    public ActionForward viewBjmdQuery(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String bjdm = request.getParameter("bjdm");
        DycjglForm model = (DycjglForm) form;
        model.setBjdm(bjdm);
        DycjglService service = new DycjglService();
        HashMap<String,String> xnxqMap = service.getXnXqInfo();
        model.setXq(xnxqMap.get("xqdm"));
        model.setXn(xnxqMap.get("xn"));
        User user = getUser(request);
        List<HashMap<String,String>> xmList = service.getXmList();
        request.setAttribute("xmList",xmList);
        //查询结果集
        List<HashMap<String,String>> resultList = service.getbjQuery(model,user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }



}

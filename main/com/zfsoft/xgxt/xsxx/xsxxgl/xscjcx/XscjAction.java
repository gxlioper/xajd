package com.zfsoft.xgxt.xsxx.xsxxgl.xscjcx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyzyhfz.gzf.GzfForm;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class XscjAction extends SuperAction<XscjForm,XscjService> {

    public ActionForward xscjList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        XscjForm myForm = (XscjForm)form;
        if(QUERY.equals(myForm.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = getService().getPageList(myForm,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path","xsxx_xsxxgl_cjcx.do");
        return mapping.findForward("xscjList");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XscjForm myForm = (XscjForm)form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        myForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = getService().getAllList(myForm,user);

        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = myForm.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }

    /**
     * 学生成绩分析列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward xscjfxList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        XscjForm myForm = (XscjForm)form;
        if(QUERY.equals(myForm.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = getService().getXscjxxList(myForm,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path","xsxx_xsxxgl_cjfx.do");
        return mapping.findForward("xscjfxList");
    }

    /**
     * 成绩分析导出
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward exportCjfxData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XscjForm myForm = (XscjForm)form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        myForm.setSearchModel(searchModel);
        User user = getUser(request);
        myForm.getPages().setPageSize(Integer.MAX_VALUE);
        List<HashMap<String,String>> resultList = getService().getXscjxxList(myForm,user);

        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = myForm.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }

    /**
     * 查看学生成绩
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward getXscj(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        XscjForm myForm = (XscjForm)form;
        List<HashMap<String, String>> resultList;
        if(StringUtils.isNull(myForm.getType())) {
           resultList = getService().getXscj(myForm.getXh(), myForm.getXn(), myForm.getXq(),null);
        }else {
            resultList=getService().getXscj(myForm.getXh(),myForm.getXn(),myForm.getXq(),myForm.getType());
        }
        request.setAttribute("list",resultList);
        return mapping.findForward("getXscj");

    }

}

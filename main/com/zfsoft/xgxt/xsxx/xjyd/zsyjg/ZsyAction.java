package com.zfsoft.xgxt.xsxx.xjyd.zsyjg;

import com.zfsoft.ms.mail.util.StringUtils;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;


import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 转书院、行政班级调整
 */
public class ZsyAction extends SuperAction<ZsyForm, ZsyService> {
    ZsyService service = TransactionControlProxy.newProxy(new ZsyService());

    public ActionForward zsyList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZsyForm myForm = (ZsyForm) form;
        if (QUERY.equals(myForm.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = getService().getPageList(myForm, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", "xjyd_zsy.do");
        return mapping.findForward("zsyList");
    }

    /**
     * 调整班级
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward tz(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("values");
        if (StringUtils.isNotEmpty(ids)) {
            if (service.tz(ids)) {
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_SUCCESS));
            } else {
                String message = MessageUtil.getText(MESSAGE_FAIL);
                response.getWriter().print(getJsonMessageByKey(message));
            }
        }
        return null;
    }

    /**
     * 数据导出
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZsyForm model = (ZsyForm) form;
        ZsyService service = new ZsyService();

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页

        // 导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());// 当前操作员
        exportModel.setDataList(resultList);// 设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);// 生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }



}

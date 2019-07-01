package com.zfsoft.xgxt.szdw.fdydk;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
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
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class FdydkAction extends SuperAction<FdydkForm, FdydkService> {
    private FdydkService service = new FdydkService();
    private static final String url = "szdw_fdy_fdydk.do";

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO 辅导员带课结果列表
     * @author Wang ChenHui
     * @date 2019/5/24 8:41
     */
    public ActionForward jgList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        FdydkForm model = (FdydkForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jgList");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO 新增结果页面
     * @author Wang ChenHui
     * @date 2019/5/24 10:04
     */
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        String zgh = request.getParameter("zgh");
        HashMap<String, String> fdyInfo = service.getFdyxx(zgh);
        request.setAttribute("fdyxx", fdyInfo);
        List<HashMap<String, String>> kcList = service.getKcList();
        request.setAttribute("kcList", kcList);
        return mapping.findForward("add");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO 保存结果
     * @author Wang ChenHui
     * @date 2019/5/24 10:05
     */
    public ActionForward addDkxx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        FdydkForm model = (FdydkForm) form;
        boolean result = service.runInsert(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO 详情查看
     * @author Wang ChenHui
     * @date 2019/5/24 10:24
     */
    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        FdydkForm model = (FdydkForm) form;
        HashMap<String, String> fdyDkxx = service.getFdydkxx(model.getId());
        request.setAttribute("fdyDkxx", fdyDkxx);
        HashMap<String, String> fdyInfo = service.getFdyxx(fdyDkxx.get("zgh"));
        request.setAttribute("fdyxx", fdyInfo);
        return mapping.findForward("view");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO 修改页面
     * @author Wang ChenHui
     * @date 2019/5/24 10:30
     */
    public ActionForward updatePage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        FdydkForm model = (FdydkForm) form;
        HashMap<String, String> fdyDkxx = service.getFdydkxx(model.getId());
        request.setAttribute("fdyDkxx", fdyDkxx);
        HashMap<String, String> fdyInfo = service.getFdyxx(fdyDkxx.get("zgh"));
        request.setAttribute("fdyxx", fdyInfo);
        List<HashMap<String, String>> kcList = service.getKcList();
        request.setAttribute("kcList", kcList);
        return mapping.findForward("updatePage");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @description: TODO 更新带课信息
     * @author Wang ChenHui
     * @date 2019/5/24 11:01
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        FdydkForm model = (FdydkForm) form;
        boolean result = service.runUpdate(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
    * @description: TODO 删除带课信息
    * @param mapping
	* @param form
	* @param request
	* @param response
    * @return org.apache.struts.action.ActionForward
    * @author Wang ChenHui
    * @date 2019/5/24 11:03
    */
    public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(
                    MessageKey.SYS_DEL_NUM, num) : MessageUtil
                    .getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
    * @description: TODO 导出带课信息
    * @param mapping
	* @param form
	* @param request
	* @param response
    * @return org.apache.struts.action.ActionForward
    * @author Wang ChenHui
    * @date 2019/5/24 11:05
    */
    public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        FdydkForm model = (FdydkForm) form;
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = service.getAllList(model,
                user);// 查询出所有记录，不分页


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

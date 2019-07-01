package com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypxJg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyxx.FdyxxService;
import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx.YwxsypxService;
import com.zfsoft.xgxt.szdw.jfxxwh.JfxxForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
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
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class PxJgAction extends SuperAction<PxJgForm,PxJgService>{
    private YwxsypxService ywxsypxService = new YwxsypxService();
    private FdyxxService fdyxxService = new FdyxxService();
    public ActionForward ywxxypxJgList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        PxJgForm model = (PxJgForm) form;
        if(QUERY.equals(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_fdy_ywxxypx_jg.do");
        return mapping.findForward("ywxxypxjgList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {

        PxJgForm model = (PxJgForm) form;
        User user = getUser(request);
        PxJgService service = getService();
        if(SAVE.equals(model.getType())){
            boolean f = service.runInsert(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }

        List<HashMap<String,String>> bmList = ywxsypxService.getAllBmList();
        request.setAttribute("bmList",bmList);
        return mapping.findForward("add");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        PxJgForm model = (PxJgForm) form;
        User user = getUser(request);
        PxJgService service = getService();
        if(SAVE.equals(model.getType())){
            boolean f = service.runUpdate(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        PxJgForm result = service.getModel(model);
        HashMap<String,String> data = fdyxxService.getFdyInfo1(result.getZgh());
        List<HashMap<String,String>> bmList = ywxsypxService.getAllBmList();
        request.setAttribute("fdyxx",data);
        request.setAttribute("bmList",bmList);
        request.setAttribute("result",result);
        request.setAttribute("filepath",result.getFilepath());
        BeanUtils.copyProperties(model,result);
        return mapping.findForward("update");
    }
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {

        PxJgForm model = (PxJgForm) form;
        PxJgService service = getService();

        PxJgForm result = service.getModel(model);
        HashMap<String,String> data = fdyxxService.getFdyInfo1(result.getZgh());
        request.setAttribute("fdyxx",data);
        request.setAttribute("filepath",result.getFilepath());
        request.setAttribute("model",result);
        return mapping.findForward("view");
    }
    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("values");
        int i = getService().runDelete(ids.split(","));
        String msg = i>0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,i) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(getJsonMessage(msg));
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PxJgForm model = (PxJgForm) form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = getService().getAllList(model,user);

        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }
}

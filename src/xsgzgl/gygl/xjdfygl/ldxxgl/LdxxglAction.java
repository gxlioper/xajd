package xsgzgl.gygl.xjdfygl.ldxxgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxjg.XnjxjgForm;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
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

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class LdxxglAction extends SuperAction<LdxxglForm,LdxxglService> {

    public ActionForward ldxxList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception{
        LdxxglForm model = (LdxxglForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getPageList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_fygl_ldxxgl.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("ldxxList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        LdxxglForm model = (LdxxglForm)form;
        LdxxglService service = getService();
        if(SAVE.equals(model.getType())){
            boolean flag = service.runInsert(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        model.setLdxb("1");
        model.setLdzx("1");
        model.setQsch("1");
        request.setAttribute("xqList",service.getXqList());
        request.setAttribute("xslxList",service.getXslxList());
        //初始化层数
        request.setAttribute("cshcs",service.cshcs());
        //初始化起始层号下拉框
        request.setAttribute("qschList",service.qsch());
        return mapping.findForward("add");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        LdxxglForm model = (LdxxglForm)form;
        LdxxglService service = getService();
        if(SAVE.equals(model.getType())){
            boolean flag = service.runUpdate(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        LdxxglForm data = service.getModel(model.getLddm());
        BeanUtils.copyProperties(model,data);
        request.setAttribute("xslxList",service.getXslxList());
        request.setAttribute("cshcs",service.cshcs());
        //初始化起始层号下拉框
        request.setAttribute("qschList",service.qsch());
        request.setAttribute("xqmc",data.getXqmc());
        request.setAttribute("lddm",data.getLddm());
        return mapping.findForward("update");
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        String lddms = request.getParameter("values");
        int num = getService().runDelete(lddms.split(","));
        String msg = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) :
                MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(JSONObject.fromObject(getJsonMessage(msg)));
        return null;
    }

    public ActionForward qssc(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        LdxxglForm model = (LdxxglForm)form;
        LdxxglService service = getService();
        if(SAVE.equals(model.getType())){
            boolean flag = service.qsscSave(model);
            String msg = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(msg));
            return null;
        }
        LdxxglForm data = service.getModel(model.getLddm());
        BeanUtils.copyProperties(model,data);
        model.setWs("3");
        request.setAttribute("cshcs",service.cshcs());
        request.setAttribute("qschList",service.qsch());
        request.setAttribute("xqmc",data.getXqmc());
        request.setAttribute("ldmc",data.getLdmc());

        if(service.isExistQs(model.getLddm())){
            List<HashMap<String,String>> lcqsxx = service.getQsxx(model.getLddm());
            request.setAttribute("lcqsxx",lcqsxx);
            request.setAttribute("ldcs",data.getLdcs());
            request.setAttribute("qsch",data.getQsch());
            request.setAttribute("ldxb",data.getLdxb());
            return mapping.findForward("qsscCk");
        }
        return mapping.findForward("qssc");
    }

    public ActionForward qsscBc(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        LdxxglForm model = (LdxxglForm)form;
        LdxxglService service = getService();
        boolean flag = service.qsscBc(model);
        String msg = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(msg));
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LdxxglForm model = (LdxxglForm)form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = getService().getAllList(model,user);// 查询出所有记录，不分页

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

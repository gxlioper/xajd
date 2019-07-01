package com.zfsoft.xgxt.zhdj.xsdjsgygc.dyssgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:党员宿舍管理
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-15 13:58
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DyssglAction extends SuperAction<DyssglForm,DyssglService>{
    private DyssglService service = new DyssglService();
    private static final String url = "zhdj_sgygc_dyssgl.do";

    @SystemAuth(url=url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "zhdj_sgygc_dyssgl.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dyssglList");
    }

    public ActionForward dyssglAdd(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("dyssglAdd");
    }

    public ActionForward dyssglUpdate(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        HashMap<String,String> map = service.getUpdateInfo(model);
        request.setAttribute("map",map);
        BeanUtils.copyProperties(model,StringUtils.formatData(map));
        return mapping.findForward("dyssglUpdate");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        boolean result = false;
        String messageKey = "";
        if("add".equals(model.getType())){
            result = service.runInsert(model);
        }
        if("update".equals(model.getType())){
            result = service.runUpdate(model);
        }
        messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //获得id
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

    public ActionForward dyssglView(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        HashMap<String,String> map = service.getViewInfo(model);
        request.setAttribute("map", StringUtils.formatData(map));
        return mapping.findForward("dyssglView");
    }
    /**
     * @描述:选择党员学号
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 16:31
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward selectDy(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = service.getDyList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "zhdj_dyssgl_xzdy.do";
        request.setAttribute("path",path);
        return mapping.findForward("selectDy");
    }
    /**
     * @描述:选择寝室
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 8:47
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward selectQs(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = service.getQsList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "zhdj_dyssgl_xzqs.do";
        request.setAttribute("path",path);
        return mapping.findForward("selectQs");
    }

    /**
     * @描述:获取寝室成员
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 10:05
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward showQscy(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String lddm = request.getParameter("lddm");
        String qsh = request.getParameter("qsh");
        HashMap<String, String> map = service.getQscy(lddm, qsh);
        JSONArray dataList = JSONArray.fromObject(map);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * @描述:提交总结
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 19:26
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward dyssglTjzj(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        HashMap<String,String> map = service.getUpdateInfo(model);
        //获取总结情况信息
        HashMap<String,String> zjmap = service.getZjInfo(model);
        if(zjmap.size()>0){
            BeanUtils.copyProperties(model,zjmap);
        }
        request.setAttribute("map",map);
        return mapping.findForward("dyssglTjzj");
    }

    /**
     * @描述:保存总结信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 19:55
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward save_zj(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        boolean result = false;
        String messageKey = "";
        result = service.save_zj(model);
        messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DyssglForm model = (DyssglForm) form;
        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        model.getPages().setPageSize(Integer.MAX_VALUE);
        // 查询
        List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页
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

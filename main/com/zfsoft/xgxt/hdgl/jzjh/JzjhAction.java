package com.zfsoft.xgxt.hdgl.jzjh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.export.util.DateUtils;
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
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JzjhAction extends SuperAction<JzjhForm,JzjhService> {
    private static final String url = "hdgl_hdgl_jzbgjh.do";
    JzjhService jzjhService = new JzjhService();
    @SystemAuth(url = url)
    public ActionForward jzbgjhList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

        JzjhForm t = (JzjhForm)form;
        User user = getUser(request);
        if(QUERY.equals(t.getType())){
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            t.setSearchModel(searchModel);
            //查询
            List<HashMap<String,String>> resultList = jzjhService.getPageList(t,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", url);
        return mapping.findForward("jzbgList");
    }

    @SystemAuth(url = url)
    public ActionForward add(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        JzjhForm t = (JzjhForm)form;
        if(SAVE.equalsIgnoreCase(t.getType())){
            boolean isExist = jzjhService.isExist(t);
            if(!isExist){
                User u = getUser(request);
                t.setLrr(u.getRealName());
                boolean result = jzjhService.runInsert(t);
                String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(messageKey));
            } else {
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
            }
            return null;
        }
        return mapping.findForward("jzjh_add");
    }

    @SystemAuth(url = url)
    public ActionForward update(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        JzjhForm t = (JzjhForm)form;
        if(UPDATE.equals(t.getType())){
            boolean isExist = jzjhService.isExist(t);
            if(!isExist){
                User u = getUser(request);
                t.setLrr(u.getRealName());
                boolean result = jzjhService.runUpdate(t);
                String message = result ? MessageKey.SYS_SAVE_SUCCESS
                        : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(message));
            }else{
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
            }
            return null;
        }
        JzjhForm upForm = jzjhService.getModel(t);
        BeanUtils.copyProperties(t, StringUtils.formatData(upForm));
        return mapping.findForward("jzjh_update");
    }

    @SystemAuth(url = url)
    public ActionForward del(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        JzjhForm t = (JzjhForm)form;
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            int num = jzjhService.runDelete(values.split(","));
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
     * @description	： 导出
     * @author 		： CP（1352）
     * @date 		：2018-3-13 下午04:40:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JzjhForm model=(JzjhForm) form;
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = jzjhService.getAllList(model);//查询出所有记录，不分页
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
}

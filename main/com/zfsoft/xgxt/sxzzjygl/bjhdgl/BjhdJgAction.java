package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.sxzzjygl.ztbhgl.ZtbhJgForm;
import com.zfsoft.xgxt.sxzzjygl.ztbhgl.ZtbhJgService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
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
 *班级活动结果
 */
public class BjhdJgAction extends SuperAction<BjhdJgForm,BjhdJgService> {
    BjhdJgService service = new BjhdJgService();
    private static final String url = "sxzzjy_bjhdgl_bjhdjg.do";

    /**
     *结果列表
     */
    @SystemAuth(url = url)
    public ActionForward bjhdJg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
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
        //将用户身份置回页面
        request.setAttribute("path", url);
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjhdJg");
    }

    /**
     *结果添加页面
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("bjhdJgAdd");

    }

    /**
     *结果保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgSave(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;
        User user = getUser(request);
        model.setLrr(user.getUserName());
        boolean result = service.bjhdJgSave(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *跳转结果修改页面
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        return mapping.findForward("bjhdJgEdit");
    }


    /**
     *修改结果保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgSaveForEdit(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        User user = getUser(request);
        model.setLrr(user.getUserName());
        boolean result = service.runUpdateTrim(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *结果删除
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     *上传资料
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward  bjhdJgUpload(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        request.setAttribute("model",model);
        return mapping.findForward("bjhdJgUpload");
    }

    /**
     *认定精品班级活动
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward rdjpbjhd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        request.setAttribute("sfjpSelect",model.getSfjp());
        return mapping.findForward("rdjpbjhd");

    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjhdJgForm model = (BjhdJgForm) form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getDCList(model,user);

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


    /**
     *批量认定
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdJgPlrd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        return mapping.findForward("bjhdJgPlrd");
    }


    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForPlRd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        String sfjp = model.getSfjp();
        String[] jgids = model.getJgids();
        boolean result = false;
        for (int i = 0; i <jgids.length ; i++) {
            BjhdJgForm newBjhdJgForm = new BjhdJgForm();
            newBjhdJgForm.setJgid(jgids[i]);
            newBjhdJgForm.setSfjp(sfjp);
            result =service.runUpdate(newBjhdJgForm);
        }
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getHdInfo(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdJgForm model = (BjhdJgForm) form;
        BjhdJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        request.setAttribute("model",model);
        request.setAttribute("fj",myForm.getFj());
        return mapping.findForward("getHdInfo");
    }

}

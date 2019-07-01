package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xtwh.bjdl.BjdlService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 主题班会结果
 */
public class ZtbhJgAction extends SuperAction<ZtbhJgForm,ZtbhJgService> {
    ZtbhJgService service = new ZtbhJgService();
    private static final String url = "sxzzjy_ztbhgl_ztbhjg.do";

    /**
     *结果列表
     */
    @SystemAuth(url = url)
    public ActionForward ztbhJg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        ZtbhJgForm model = (ZtbhJgForm) form;
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
        return mapping.findForward("ztbhJg");
    }



    /**
     *添加结果的页面
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward ztbhJgAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setAttribute("xn",Base.currXn);
        request.setAttribute("xq",Base.getXqmcForXqdm(Base.currXq));
        request.setAttribute("today", DateUtils.getCurrDate());
        return mapping.findForward("ztbhJgAdd");

    }


    /**
     * //获取学生信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getXx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        ZtbhJgForm model = (ZtbhJgForm) form;
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getXxList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", "sxzzjy_ztbhgl_ztbhjg.do?method=getXx");

        return mapping.findForward("getXx");
    }


    /**
     * //获取班级信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBj(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        ZtbhJgForm model = (ZtbhJgForm) form;
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getBjList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        request.setAttribute("path", "sxzzjy_ztbhgl_ztbhjg.do?method=getBj");

        return mapping.findForward("getBj");
    }

    /**
     *结果保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward ztbhJgSave(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZtbhJgForm model = (ZtbhJgForm) form;
        String msg = service.yz(model);
        if("true".equals(msg)){
            User user = getUser(request);
            model.setLrr(user.getUserName());
            boolean result = service.ztbhJgSave(model);
            String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        } else {
            response.getWriter().print(getJsonMessage(msg));
        }

        return null;
    }

    /**
     *结果修改
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward ztbhJgEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ZtbhJgForm model = (ZtbhJgForm) form;
        ZtbhJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        request.setAttribute("xn",model.getXn());
        request.setAttribute("bjxx",service.getBjxx(model.getBjdm()));
        request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
        return mapping.findForward("ztbhJgEdit");
    }


    /**
     *结果修改保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward ztbhJgSaveForEdit(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ZtbhJgForm model = (ZtbhJgForm) form;
        String msg = service.yz(model);
        if("true".equals(msg)){
            User user = getUser(request);
            model.setLrr(user.getUserName());
            boolean result = service.update(model);
            String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        } else {
            response.getWriter().print(getJsonMessage(msg));
        }

        return null;
    }

    /**
     *结果删除
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward ztbhJgDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            service.delBj(ids);
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
    public ActionForward  ztbhJgUpload(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ZtbhJgForm model = (ZtbhJgForm) form;
        ZtbhJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        request.setAttribute("model",model);
        return mapping.findForward("ztbhJgUpload");
    }


    /**
     *认定示范性
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward rdsfxztbh(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZtbhJgForm model = (ZtbhJgForm) form;
        ZtbhJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        request.setAttribute("sfsfxSelect",model.getSfsfx());
        return mapping.findForward("rdsfxztbh");

    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZtbhJgForm model = (ZtbhJgForm) form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(model,user);

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
    public ActionForward ztbhJgPlrd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        return mapping.findForward("ztbhJgPlrd");
    }


    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForPlRd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        ZtbhJgForm model = (ZtbhJgForm) form;
        String sfsfx = model.getSfsfx();
        String[] jgids = model.getJgids();
        boolean result = false;
        for (int i = 0; i <jgids.length ; i++) {
            ZtbhJgForm newZtbhJgForm = new ZtbhJgForm();
            newZtbhJgForm.setJgid(jgids[i]);
            newZtbhJgForm.setSfsfx(sfsfx);
             result =service.runUpdate(newZtbhJgForm);
        }
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getHdInfo(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ZtbhJgForm model = (ZtbhJgForm) form;
        ZtbhJgForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        request.setAttribute("xn",model.getXn());
//        request.setAttribute("bjxx",service.getBjxx(model.getBjdm()));
        request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
        request.setAttribute("model",model);
        request.setAttribute("fj",myForm.getFj());
        request.setAttribute("bjList",service.getBjxxByJgid(myForm.getJgid()));
        return mapping.findForward("getHdInfo");
    }

    public ActionForward getBjxxUpdate(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String jgid = request.getParameter("jgid");
        List<HashMap<String,String>> list = getService().getBjxxByJgid(jgid);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }

}

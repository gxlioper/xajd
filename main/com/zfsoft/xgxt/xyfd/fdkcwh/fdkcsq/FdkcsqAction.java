package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsForm;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgForm;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/2.
 */
public class FdkcsqAction extends SuperAction<FdkcsqForm,FdkcsqService> {
    FdsService fdsService = new FdsService();
    FdkcsqService service = new FdkcsqService();
    FdjsService fdjsService = new FdjsService();
    PbjgService pbjgService = new PbjgService();

    public ActionForward fdkcsqList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcsqForm model = (FdkcsqForm)form;

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
        String path = "xyfd_xyfd_fdkcsq.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdkcsqList");
    }

    /**
     * 加载申请增加页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdkcsqAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        User user = getUser(request);
        boolean isAdmin = service.isAdmin(user);
        request.setAttribute("isAdmin",isAdmin);
        return mapping.findForward("fdkcsqAdd");
    }

    /**
     * 保存/提交申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveAdd(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        User user = getUser(request);
        model.setLrr(user.getUserName());
        model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        model.setMon(StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(StringUtils.join(request.getParameterValues("sund"),","));
        boolean result = service.saveFdkc(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 加载修改页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdkcsqUpdate(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        model = service.getModel(model);
        request.setAttribute("model",model);//辅导课程信息
        StringBuilder fdjs = new StringBuilder();
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){//教师登记号
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "：" + xm);
        }else if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){//朋辈志愿者登记号
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "：" + xm);
        }else {
            throw new Exception("登记号不存在");
        }
        request.setAttribute("fdjsxm",fdjs.toString());
        User user = getUser(request);
        boolean isAdmin = service.isAdmin(user);
        request.setAttribute("isAdmin",isAdmin);
        HashMap<String,String> fdjsxx = service.getFdjsxx(model);//辅导教师（朋辈志愿者）信息
        request.setAttribute("fdjsxx",fdjsxx);
        return mapping.findForward("fdkcsqUpdate");
    }

    /**
     * 保存/提交修改申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveUpdate(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        model.setMon(StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(StringUtils.join(request.getParameterValues("sund"),","));
        boolean result = service.saveUpdate(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 查看朋辈志愿者申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdkcsqView(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        model = service.getModel(model);
        request.setAttribute("model",model);//辅导课程信息
        StringBuilder fdjs = new StringBuilder();
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){//教师登记号
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "：" + xm);
        }else if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){//朋辈志愿者登记号
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "：" + xm);
        }else {
            throw new Exception("登记号不存在");
        }
        request.setAttribute("fdjsxm",fdjs.toString());
        HashMap<String,String> fdjsxx = service.getFdjsxx(model);//辅导教师（朋辈志愿者）信息
        request.setAttribute("fdjsxx",fdjsxx);


        return mapping.findForward("fdkcsqView");
    }

    /**
     * 删除朋辈志愿者申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdkcsqDel(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
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
     * 提交
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FdkcsqForm model = (FdkcsqForm) form;
        String values = request.getParameter("values");
        model.setSqid(values);
        boolean result = service.submit(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
                : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("values");
        String lcid = request.getParameter("splcid");
        // 只有刚提交并且第一级未审核的前提下，申请人可以撤销
        boolean result = service.cancelRecord(id, lcid);
        if (result) {
            // 更新业务状态为'未提交'
            FdkcsqForm model = new FdkcsqForm();
            model.setSqid(id);
            model.setSplc(lcid);
            // 查看是否有退回记录,有：审核状态就为退回
            ShlcDao shlcdao = new ShlcDao();
            if (Integer.valueOf(shlcdao.getExistTh(id)) > 0) {
                model.setShzt(Constants.YW_YTH);
            } else {
                model.setShzt(Constants.YW_WTJ);
            }
            service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 获取朋辈志愿者
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward selectPb(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getPbList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","xyfd_xyfd_pbjg.do");
        return mapping.findForward("selectPb");
    }

    /**
     * 获取辅导教师
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward selectFdjs(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getFdjsList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","xyfd_xyfd_fdjswh.do");
        return mapping.findForward("selectFdjs");
    }

    /**
     * 根据条件获取辅导教师和朋辈信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getFdjs(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){
            FdjsForm fdjsForm = new FdjsForm();
            fdjsForm.setDjh(model.getFdjs());
            HashMap<String, String> result = fdjsService.getFdjs(fdjsForm);
            JSONObject data = new JSONObject();
            data.put("fdjs",result);
            response.getWriter().print(data);

        }else if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){
            PbjgForm pbjgForm = new PbjgForm();
            pbjgForm.setDjh(model.getFdjs());
            HashMap<String, String> result = pbjgService.getPbjs(pbjgForm);
            JSONObject data = new JSONObject();
            data.put("fdjs",result);
            response.getWriter().print(data);
        }

        return null;
    }

    public ActionForward export(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FdkcsqForm myForm=(FdkcsqForm)form;

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
}

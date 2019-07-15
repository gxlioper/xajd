package com.zfsoft.xgxt.dtjs.llxxjl.sq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.dtjs.llxxjl.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class LlxxjlsqAction extends SuperAction<LlxxjlsqForm, LlxxjlsqService> {
    private static final String LLXXJL = "shsjjl";//与社会实践模块共用学生基本信息
    private LlxxjlsqService service = new LlxxjlsqService();
    private CsszService csszService = new CsszService();

    private static final String url = "dtjs_llxxjl_sq.do";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        jbxxList = bdpzService.getJbxxpz(LLXXJL);
    }

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(
                    model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        String sqshkg = csszService.getSqShKg();
        request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg);
        String path = "dtjs_shsjjl_sq.do"; //与社会实践共用高级查询
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("llxxjlSqList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward llxxjlSqAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())) {
            model.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
                    .getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        String path = "llxxjl_sq.do?method=llxxjlSqAdd";
        request.setAttribute("path", path);
        // 学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
        request.setAttribute("xn", Base.currXn);
        request.setAttribute("xq", Base.currXq);
        request.setAttribute("xqmc", Base.getDqxqmc());
        //service.initParam(request, user);
        return mapping.findForward("llxxjlSqAdd");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward llxxjlSqUpdate(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm myForm = (LlxxjlsqForm) form;
        User user = getUser(request);
        LlxxjlsqForm model = service.getModel(myForm);
        if (null != model) {
            BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
                    .getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        BdpzService bdpzService = new BdpzService();
        request.setAttribute("jbxxList", jbxxList);
        String path = "llxxjl_sq.do?method=llxxjlSqUpdate";
        request.setAttribute("path", path);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
        request.setAttribute("xn",model.getXn());
        request.setAttribute("xq", model.getXq());
        request.setAttribute("xqmc", Base.getXqmcForXqdm(model.getXq()));
        //service.initParam(request, user);
        return mapping.findForward("llxxjlSqUpdate");
    }

    @SystemAuth(url = url)
    public ActionForward llxxjlView(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm myForm = (LlxxjlsqForm) form;
        HashMap<String, String> map = service.getInfo(myForm.getSqid());
        if(null!=map){
            BeanUtils.copyProperties(myForm, map);
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
            request.setAttribute("jbxx", xsjbxx);
        }
        // 学生基本信息显示配置
        BdpzService bdpzService = new BdpzService();
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("rs", StringUtils.formatData(map));
        return mapping.findForward("llxxjlSqView");

    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveAdd(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        //判断当前时间是否有申请记录
        boolean isExist = service.checkEdit(model);
        if (!isExist) {
            String message = "学生在"+model.getSj()+"时间内社会实践记录已申请，请确认！";
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        User user = getUser(request);
        model.setLrr(user.getUserName());
        model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        boolean result = service.saveSthdsq(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        //判断当前时间是否有申请记录
       /* boolean isExist = service.checkEdit(model);
        if (isExist) {
            String message = "该时间下学生已有省会时间申请记录，请确认！";
            response.getWriter().print(getJsonMessage(message));
            return null;
        }*/
        boolean result = service.saveUpdate(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward del(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("values");
        String lcid = request.getParameter("splcid");
        // 只有刚提交并且第一级未审核的前提下，申请人可以撤销
        boolean result = service.cancelRecord(id, lcid);
        if (result) {
            // 更新业务状态为'未提交'
            LlxxjlsqForm model = new LlxxjlsqForm();
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

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        String values = request.getParameter("values");
        model.setSqid(values);
        boolean result = service.submit(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
                : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
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

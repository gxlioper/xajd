package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsq;

import com.zfsoft.xgxt.base.action.SuperAction;
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
import com.zfsoft.xgxt.cxcy.cssz.CsszForm;
import com.zfsoft.xgxt.cxcy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
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
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-05 15:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BzsbwhsqAction extends SuperAction<BzsbwhsqForm,BzsbwhsqService> {
    private static final String url = "cxcy_bzsbwh_sq.do";
    private BzsbwhsqService service = new BzsbwhsqService();
    private static final String CXCY = "cxcy";
    private static List<HashMap<String, String>> jbxxList = null;
    static{
        BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
        jbxxList = bdpzService.getJbxxpz(CXCY);
    }

    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhsqForm model = (BzsbwhsqForm)form;
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
        CsszForm ccszModel =new CsszService().getModel();
        request.setAttribute("ccszModel",ccszModel);
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bzsbwhsqList");
    }
    public ActionForward bzsbwhsqAdd(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhsqForm model = (BzsbwhsqForm)form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())) {
            model.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        String path = "cxcy_bzsbwhsq.do?method=bzsbwhsqAdd";
        request.setAttribute("xn",Base.currXn);
        request.setAttribute("xq",Base.currXq);
        request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
        request.setAttribute("path", path);
        return mapping.findForward("bzsbwhsqAdd");
    }

    public ActionForward bzsbwhsqUpdate(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhsqForm model = (BzsbwhsqForm)form;
        User user = getUser(request);
        HashMap<String,String> map = service.getInfoById(model.getSqid());
            // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("map", map);
        return mapping.findForward("bzsbwhsqUpdate");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhsqForm model = (BzsbwhsqForm)form;
        User user = getUser(request);
        boolean rs = true;
        try {
            rs = service.save(model,user);
        }catch (SystemException e) {
            response.getWriter().print(getJsonMessage(e.getMessage()));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward bzsbwhsqDel(ActionMapping mapping, ActionForm form,
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

    public ActionForward bzsbwhsqView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhsqForm model = (BzsbwhsqForm) form;
        HashMap<String,String> map = service.getInfoById(model.getSqid());
            // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("jbxxList", jbxxList);

        request.setAttribute("map",map);
        request.setAttribute("shztmc",map.get("shztmc"));
        return mapping.findForward("bzsbwhsqView");
    }

    public ActionForward bzsbwhsqSubmit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhsqForm model = (BzsbwhsqForm) form;
        BzsbwhsqForm myForm = service.getModel(model);
        boolean result = service.bzsbwhsqSubmit(myForm);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward bzsbwhsqCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ywid = request.getParameter("values");
        String lcid = request.getParameter("splcid");
        boolean rs = service.bzsbwhsqCancel(ywid, lcid);
        if (rs) {
            // 更新业务状态为'未提交'
            // 查看是否有退回记录,有：审核状态就为退回
            ShlcDao shlcdao = new ShlcDao();
            BzsbwhsqForm myForm = new BzsbwhsqForm();
            myForm.setSqid(ywid);
            if (Integer.valueOf(shlcdao.getExistTh(ywid)) > 0) {
                myForm.setShzt(Constants.YW_YTH);
            } else {
                myForm.setShzt(Constants.YW_WTJ);
            }
            service.runUpdate(myForm);
        }
        String messageKey = rs ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BzsbwhsqForm model = (BzsbwhsqForm) form;
        // 生成高级查询对象
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

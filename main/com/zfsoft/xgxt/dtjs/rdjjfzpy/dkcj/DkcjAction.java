package com.zfsoft.xgxt.dtjs.rdjjfzpy.dkcj;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
 * @类功能描述:党课成绩
 * @作者： lgx [工号:1553]
 * @时间： 2019-02-27 10:59
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DkcjAction extends SuperAction<DkcjForm,DkcjService> {
    private DkcjService service = new DkcjService();
    private static final String DKCJ = "dkcj";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        // 学生基本信息显示配置
        jbxxList = bdpzService.getJbxxpz(DKCJ);
    }
    private static final String url = "dtjs_rdjjfzpy_dkcj.do";


    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DkcjForm model = (DkcjForm) form;
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
        String path = "dtjs_rdjjfzpy_dkcj.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dkcjList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward dkcjAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DkcjForm model = (DkcjForm) form;
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
        String path = "dtjs_dkcj.do?method=dkcjAdd";
        request.setAttribute("path", path);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd"));
        request.setAttribute("jbxxList", jbxxList);
        return mapping.findForward("dkcjAdd");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward dkcjUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DkcjForm myForm = (DkcjForm) form;
        DkcjForm model = service.getModel(myForm);
        if (null != model) {
            BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
        }
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
                    .getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        String path = "dtjs_dkcj.do?method=dkcjUpdate";
        request.setAttribute("path", path);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd"));
        return mapping.findForward("dkcjUpdate");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DkcjForm model = (DkcjForm) form;
        boolean result = false;
        User user = getUser(request);
        if("add".equals(model.getType())){
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            result = service.runInsert(model);
        }else if("update".equals(model.getType())){
            result = service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward dkcjDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

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

    @SystemAuth(url = url)
    public ActionForward dkcjView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DkcjForm model = (DkcjForm) form;
        HashMap<String,String> map = service.getInfo(model.getId());
        if (!StringUtil.isNull(map.get("xh"))) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("map", map);
        request.setAttribute("jbxxList", jbxxList);
        return mapping.findForward("dkcjView");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DkcjForm model = (DkcjForm) form;
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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

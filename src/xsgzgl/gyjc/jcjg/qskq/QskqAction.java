package xsgzgl.gyjc.jcjg.qskq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-08-09 17:08
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QskqAction extends SuperAction<QskqForm,QskqService> {
    private  QskqService service = new QskqService();
    private static final String url = "xg_gyjc_qskq.do";

    private static final String GRXFJS = "wmqs_qskq";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        // 学生基本信息显示配置
        jbxxList = bdpzService.getJbxxpz(GRXFJS);
    }
    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QskqForm model = (QskqForm) form;
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
        String path = "xg_gyjc_qskq.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("qskqList");
    }

    @SystemAuth(url = url,rewritable = ReadWrite.WRITEABLE)
    public ActionForward qskqUpdate(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QskqForm model = (QskqForm) form;
        User user = getUser(request);
        HashMap<String,String> map = service.getQskqjgInfo(model);
        BeanUtils.copyProperties(model,map);
        List<HashMap<String,String>> kqlbList = service.getAllKqlbList();
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("kqlbList", kqlbList);
        request.setAttribute("map", map);
        return mapping.findForward("qskqUpdate");
    }

    public ActionForward qskqSave(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QskqForm model = (QskqForm) form;
        User user = getUser(request);
        model.setXgr(user.getUserName());
        model.setXgsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        boolean rs = service.update_save(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward qskqView(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QskqForm model = (QskqForm) form;
        HashMap<String,String> map = service.getQskqjgInfo(model);
        request.setAttribute("map", map);
        // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
        request.setAttribute("jbxx", xsjbxx);
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
        return mapping.findForward("qskqView");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QskqForm model = (QskqForm) form;
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

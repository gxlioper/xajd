package com.zfsoft.xgxt.qgzx.dgxsk;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
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
 * @类功能描述:待岗学生库
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-06 09:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DgxskAction extends SuperAction<DgxskForm,DgxskService> {
    private DgxskService service = new DgxskService();
    private static final String url = "qgzx_gwglnew_dgxsk.do";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        // 学生基本信息显示配置
        jbxxList = new BdpzService().getJbxxpz("cjff");
    }

    @SystemAuth(url = url)
    public ActionForward dgxskManage(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DgxskForm myForm = (DgxskForm) form;
        User user = getUser(request);
        if (QUERY.equals(myForm.getType())) {
            // ==================高级查询相关========================
            CommService cs = new CommService();
            SearchModel searchModel = cs.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            // ==================高级查询相关 end========================
            List<HashMap<String, String>> resultList = service.getPageList(
                    myForm, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "qgzx_gwglnew_dgxsk.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dgxskManage");
    }
    public ActionForward dgxskView(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DgxskForm myForm = (DgxskForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())){
            myForm.setXh(user.getUserName());
        }
        // 查询学生信息
        if (myForm.getXh() != null && !"".equals(myForm.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        WdgwsqService wservice = new WdgwsqService();
        List<HashMap<String, String>> qgxmList= wservice.getQgmxList(myForm.getXh());
        request.setAttribute("qgxmList", new QjjgService().getQjxmList());
        request.setAttribute("qgmxList", qgxmList);
        request.setAttribute("qgxmSize", qgxmList.size());
        return mapping.findForward("dgxskView");
    }


    /**导出***/
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DgxskForm model = (DgxskForm) form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
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

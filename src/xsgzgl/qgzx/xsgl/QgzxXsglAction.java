/**
 * @部门:学工产品事业部
 * @日期：2014-7-4 下午02:27:43
 */
package xsgzgl.qgzx.xsgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
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
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 勤工助学学生维护
 * @作者： 陶钢军 [1075]
 * @时间： 2014-7-4 下午02:27:43 
 * @版本： V5.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QgzxXsglAction extends SuperAction {

    private static final String url = "qgzx_xsgl_xswh.do";

    /**
     *
     * @描述:勤工助学学生维护
     * @作者：陶钢军 [1075]
     * @日期：2014-7-4 下午04:22:35
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward xswh(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        QgzxXsglForm model = (QgzxXsglForm) form;
        QgzxXsglService service = new QgzxXsglService();

        if (QUERY.equals(model.getType())) {
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

        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        String path = "qgzx_xsgl_xswh.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xsWh");

    }

    /**
     *
     * @描述:获取学生
     * @作者：陶钢军 [1075]
     * @日期：2014-7-7 下午04:23:27
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    public ActionForward getStu(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;
        if (QUERY.equals(model.getType())) {
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            //查询
            List<HashMap<String, String>> resultList = service.getXsPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        String path = "qgzx_xsgl.do?method=getStu";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getStu");
    }

    /**
     *
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：夏夏[工号：1104]
     * @日期：2014-7-30 下午02:06:01
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    @SystemLog(description = "访问勤工助学-基础设置-勤工学生维护-增加或修改XH{values}")
    public ActionForward addQgxs(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        String values = request.getParameter("values");

        if (!StringUtil.isNull(values)) {
            boolean result = true;
            String[] xhs = values.split(",");
            for (String xh : xhs) {
                result = service.saveQgzxXs(xh);
            }
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     *
     * @描述:删除操作
     * @作者：陶钢军 [1075]
     * @日期：2014-7-7 下午05:18:45
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    @SystemLog(description = "访问勤工助学-基础设置-勤工学生维护-删除XH{values}")
    public ActionForward deleteQgxs(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        String values = request.getParameter("values");

        if (!StringUtil.isNull(values)) {
            int num = service.runDelete(values.split(","));
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * @功能描述:维护是否购买保险页面
     * @auther: 王晨辉[1692]
     */
    public ActionForward updateSfgmbx(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;
        String xh = model.getXh();
        HashMap<String, String> dataModel = service.getDetail(xh);
        request.setAttribute("dataModel", dataModel);
        return mapping.findForward("updateSfgmbx");
    }

    /**
     * @功能描述:修改是否购买保险信息
     * @auther: 王晨辉[1692]
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;
        boolean result = service.updateSfgmbx(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @功能描述:批量处理购买保险信息
     * @auther: 王晨辉[1692]
     */
    public ActionForward plUpdate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        String values = request.getParameter("values");
        String[] xhs = values.split(",");
        for (String xh:xhs){
            boolean result = service.plUpdate(xh);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     * @功能描述:导出勤工人员信息
     * @auther: 王晨辉[1692]
     */
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getQgryAllList(model,user);
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

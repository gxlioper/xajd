package xsgzgl.gygl.xjdfygl.fsbj;

import com.zfsoft.ms.mail.util.CollectionUtils;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class FsbjAction extends SuperAction<FsbjForm,FsbjServie> {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FsbjForm model = (FsbjForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getPageList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_fygl_fsbj.do");
        return mapping.findForward("list");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        FsbjForm model = (FsbjForm) form;
        FsbjServie servie = getService();
        String pks = request.getParameter("pks");
        if(SAVE.equals(model.getType())){
            User user = getUser(request);
            model.setLlr(user.getUserName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            model.setLlsj(sdf.format(new Date()));
            boolean flag = servie.save(model,pks);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        if(!StringUtils.isNull(pks)){
            request.setAttribute("pks",pks);
            request.setAttribute("xzgs",pks.split(",").length);
        } else {
            List<String> cxpks = servie.getWfsQsgs();
            request.setAttribute("cxgs", CollectionUtils.isEmpty(cxpks) ? "0" : cxpks.size());
        }
        request.setAttribute("nd", Base.currNd);
        return mapping.findForward("add");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        FsbjForm model = (FsbjForm)form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = getService().getAllList(model,user);// 查询出所有记录，不分页

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

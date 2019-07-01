package com.zfsoft.xgxt.hdgl.hdgltjcx;

import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.hdgl.jzjh.JzjhForm;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.BaseAction;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class TjAction extends BaseAction{
    private static final String url = "hdgl_hdgl_sgybtj.do";
    TjService service = new TjService();

    public ActionForward tjList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        TjForm t = (TjForm)form;
        if(QUERY.equalsIgnoreCase(t.getType())){
            User user = getUser(request);

            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            t.setSearchModel(searchModel);
            List<HashMap<String,String>> resultList = service.getPageList(t,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);

            return null;
        }
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        SearchModel searchModel=new SearchModel();
        request.setAttribute("searchTj", searchModel);
        return mapping.findForward("tjcx");
    }

    /**
     * @description	： 导出
     * @author 		： CP（1352）
     * @date 		：2018-3-13 下午04:40:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TjForm t=(TjForm) form;
        User user = getUser(request);
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        t.setSearchModel(searchModel);
        List<HashMap<String,String>> resultList = service.getAllList(t,user);//查询出所有记录，不分页
        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = t.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }
}

package com.zfsoft.xgxt.zhdj.xszbhd.zbhdjg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
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
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:支部活动结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-11 14:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdjgAction extends SuperAction<ZbhdjgForm,ZbhdjgService> {
    private  ZbhdjgService service = new ZbhdjgService();
    private static final String url = "zhdj_zbhd_zbhdjg.do";

    @SystemAuth(url = url)
    public ActionForward getJgList(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdjgForm model = (ZbhdjgForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            String flag = request.getParameter("flag");
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
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        String path = "zhdj_zbhd_zbhdjg.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("hdjgList");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdjgForm model = (ZbhdjgForm) form;
        HashMap<String,String> map = service.getMap(model);
        request.setAttribute("map",map);
        return mapping.findForward("view");
    }
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZbhdjgForm model = (ZbhdjgForm) form;
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

package xsgzgl.gyjc.bbtj;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gyjc.jcjg.WsccForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class BbscAction extends SuperAction<BbscForm,BbscService> {

    private static final String url = "xg_gyjc_bbsc.do";
    private BbscService service = new BbscService();

    public ActionForward getResultList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        BbscForm model = (BbscForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getResultList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        //将用户身份置回页面
        request.setAttribute("userType", user.getUserStatus());
        request.setAttribute("xydm",user.getUserDep());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }

    public ActionForward bbExport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        BbscForm model = (BbscForm) form;
        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAll(model,user);
        File file = service.getBbscFile(resultList);//生成导出文件
        FileUtil.outputExcel(response, file);





        return null;
    }




}

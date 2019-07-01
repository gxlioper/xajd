package com.zfsoft.xgxt.xlzx.xlwjga.zdgzxs;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyxx.FdyxxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
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
 * @类功能描述:重点关注学生
 * @作者： lgx [工号:1553]
 * @时间： 2019-04-10 14:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZdgzxsAction extends SuperAction<ZdgzxsForm,ZdgzxsService> {
    private ZdgzxsService service = new ZdgzxsService();
    private static final String url = "xlzx_xlwjga_zdgzxs.do";
    private static List<HashMap<String, String>> jbxxList = null;
    private static final String ZDGZXS = "zdgzxs";
    static {
        BdpzService bdpzService = new BdpzService();
        // 学生基本信息显示配置
        jbxxList = bdpzService.getJbxxpz(ZDGZXS);
    }

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZdgzxsForm model = (ZdgzxsForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())){
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            //查询
            List<HashMap<String,String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //默认高级查询条件
       /* SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_wjgabz(new String[]{ "1" });
        request.setAttribute("searchTj", searchModel);*/
        String path = "xlzx_xlwjga_zdgzxs.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("zdgzxsList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward zdgzxsAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZdgzxsForm myForm = (ZdgzxsForm) form;
        User user = getUser(request);
        String xh = myForm.getXh();
        request.setAttribute("jbxxList", jbxxList);
        if (!StringUtil.isNull(xh)) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
        }
        HashMap<String, String> zxsMap = new FdyxxService().getFdyInfo(user.getUserName());
        String path = "xlzx_zdgzxs.do?method=zdgzxsAdd";
        request.setAttribute("path", path);
        request.setAttribute("zxsMap", zxsMap);
        return mapping.findForward("zdgzxsAdd");
    }


    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward zdgzxsUpdate(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZdgzxsForm myForm = (ZdgzxsForm) form;
        HashMap<String,String> map = service.getInfoById(myForm.getId());
        if(map != null){
            BeanUtils.copyProperties(myForm,map);
            request.setAttribute("map", map);
            String xh = myForm.getXh();
            request.setAttribute("jbxxList", jbxxList);
            if (!StringUtil.isNull(xh)) {
                // 加载学生基本信息
                XsxxService xsxxService = new XsxxService();
                HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
                request.setAttribute("jbxx", xsjbxx);
            }
        }
        return mapping.findForward("zdgzxsUpdate");
    }

    @SystemAuth(url = url)
    public ActionForward zdgzxsView(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZdgzxsForm myForm = (ZdgzxsForm) form;
        HashMap<String,String> map = service.getInfoById(myForm.getId());
        String xh = map.get("xh");
        request.setAttribute("jbxxList", jbxxList);
        // 加载学生基本信息
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("map", map);
        return mapping.findForward("zdgzxsView");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward zdgzxsSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZdgzxsForm model = (ZdgzxsForm) form;
        boolean result = false;
        User user = getUser(request);
        if("add".equals(model.getType())){
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd HH:mm:ss"));
            result = service.runInsert(model);
        }else{
            result = service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="心理健康-心理危机管理-重点关注学生-删除VALUES:{values}")
    public ActionForward zdgzxsDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            int num = service.runDelete(values.split(","));
            boolean result =  num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZdgzxsForm model = (ZdgzxsForm) form;
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

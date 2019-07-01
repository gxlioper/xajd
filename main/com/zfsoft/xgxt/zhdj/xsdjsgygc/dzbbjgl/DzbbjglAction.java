package com.zfsoft.xgxt.zhdj.xsdjsgygc.dzbbjgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zhdj.ZhdjComm;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述: 学生党建“三个一”工程-党支部班级管理
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-13 17:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DzbbjglAction extends SuperAction<DzbbjglForm,DzbbjglService>{
    private static final String url = "zhdj_sgygc_dzbbjgl.do";
    private DzbbjglService service = new DzbbjglService();

    @SystemAuth(url=url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DzbbjglForm model = (DzbbjglForm) form;
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
        /*SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);*/
        String path = "zhdj_sgygc_dzbbjgl.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dzbbjglList");
    }
    public ActionForward dzbbjglAdd(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        User user = getUser(request);
        //是否党支部书记
        boolean isDzbsj = ZhdjComm.isDzbsj(user);
       if(isDzbsj){
           //根据党支部书记获取党支部信息
           HashMap<String,String> map = service.getDzbBySj(user.getUserName());
           request.setAttribute("map",map);
       }
       request.setAttribute("isDzbsj",isDzbsj);
        return mapping.findForward("dzbbjglAdd");
    }
    public ActionForward dzbbjglUpdate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DzbbjglForm model = (DzbbjglForm) form;
        model = service.getModel(model);
        User user = getUser(request);
        boolean isDzbsj = ZhdjComm.isDzbsj(user);
        HashMap<String,String> map = service.getDzbbjglInfo(model);
        request.setAttribute("map",map);
        request.setAttribute("isDzbsj",isDzbsj);
        return mapping.findForward("dzbbjglUpdate");
    }

    /**
     * @描述:选择党支部
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 9:14
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward selectDzb(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DzbbjglForm model = (DzbbjglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = service.getDzbList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
           return null;
        }
        String path = "zhdj_dzbbjgl_xzdzb.do";
        request.setAttribute("path",path);
        return mapping.findForward("selectDzb");
    }
    /**
     * @描述:选择班级
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 9:14
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward selectBj(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DzbbjglForm model = (DzbbjglForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = service.getBjList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "zhdj_dzbbjgl_xzbj.do";
        request.setAttribute("path",path);
        request.setAttribute("dzbid",model.getDzbid());
        return mapping.findForward("selectBj");
    }
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DzbbjglForm model = (DzbbjglForm) form;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cjsj =formatter.format(new Date());
        model.setCjsj(cjsj);
        boolean result = false;
        String messageKey = "";
        if("add".equals(model.getType())){
            result = service.runInsert(model);
        }
        if("update".equals(model.getType())){
            result = service.runUpdate(model);
        }
        messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //获得id
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

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DzbbjglForm model = (DzbbjglForm) form;
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

package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.bjybb;

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
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
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
 * 安徽农业大学
 * 学生工作情况班级月报表action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkBjYbbAction extends SuperAction<XsgzqkBjYbbForm, XsgzqkBjYbbService> {

    private XsgzqkBjYbbService service = new XsgzqkBjYbbService();
    private static final String url = "rcsw_xsgzqkbb_xyybb.do";

    /**
     *  转到班级月报表列表页面（学院报表的填写）.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-24 15:35
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward bjYbbList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        request.setAttribute("path", url);
        request.setAttribute("czpath","rcsw_xsgzqkbb_bjybb.do");

        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjYbbList");
    }

    /**
     *  获取班级月报表列表json数据.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-17 15:25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward getBjYbbListData(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkBjYbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        // 查询
        List<HashMap<String, String>> resultList = service.getPageList(xsgzqkBjYbbForm);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     *  转到班级月报表增加页面.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward bjYbbAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        return mapping.findForward("bjYbbAdd");
    }

    /**
     *  转到班级月报表修改页面.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbEdit(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        User user = getUser(request);
        HashMap<String,String> bjYbb = service.getBjYbbById(xsgzqkBjYbbForm.getId());

        BeanUtils.copyProperties(xsgzqkBjYbbForm, bjYbb);

        return mapping.findForward("bjYbbEdit");
    }

    /**
     *  转到班级列表页面，供选择.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 15:44
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjManage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        SearchModel searchModel=new SearchModel();

        String xyybbid = xsgzqkBjYbbForm.getXyybbid();
        request.setAttribute("xyybbid",xyybbid);

        request.setAttribute("searchTj", searchModel);
        String path = "rcsw_xsgzqkbb_bjybbgl.do?method=bjManage";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjManage");
    }

    /**
     *  获取班级列表JSON数据.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 15:48
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward getBjListData(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        User user = getUser(request);

        //查询过滤学院代码
        String xydm = service.getXydmByXyybbid(xsgzqkBjYbbForm.getXyybbid());
        xsgzqkBjYbbForm.setXydm(xydm);

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkBjYbbForm.setSearchModel(searchModel);
        // 查询
        List<HashMap<String, String>> resultList = service.getBjList(xsgzqkBjYbbForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     *  班级月报表增加的保存.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbSaveForAdd(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        //重复性验证
        if(service.isBjYfRepeat(xsgzqkBjYbbForm)){
            //存在重复
            String messageKey = MessageKey.RCSW_XSGZQKBB_YBB_BJYFREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else{
            //不存在重复
            boolean result = service.runInsert(xsgzqkBjYbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  班级月报表修改的保存.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbSaveForEdit(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        //重复性验证
        if(service.isBjYfRepeat(xsgzqkBjYbbForm)){
            //存在重复
            String messageKey = MessageKey.RCSW_XSGZQKBB_YBB_BJYFREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else {
            //不存在重复
            boolean result = service.runUpdate(xsgzqkBjYbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  班级月报表批量删除（删除前需判断是否有班级数据录入）.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 17:18
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbDel(ActionMapping mapping, ActionForm form,
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

    /**
     *  班级月报表通用导出.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 19:31
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkBjYbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(xsgzqkBjYbbForm);

        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = xsgzqkBjYbbForm.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }
}

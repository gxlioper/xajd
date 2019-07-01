package com.zfsoft.xgxt.zhdj.jjfzbfgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zhdj.xsdzbhdygl.CyglForm;
import com.zfsoft.xgxt.zhdj.xsdzbhdygl.DzbglForm;
import com.zfsoft.xgxt.zhdj.xsdzbhdygl.DzbglService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.gyjc.jcjg.WsjcForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JjfzbfAction extends SuperAction<JjfzbfForm,JjfzbfService> {
    private JjfzbfService service = new  JjfzbfService();
    private static final String url = "zhdj_sgygc_jjfzbfgl.do";
    private static final String DATE_FORMAT = "yyyy-MM-dd";


    public ActionForward getJjfzbfList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
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
        //将用户身份置回页面
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        return mapping.findForward("addPage");
    }


    //选择帮扶人
    public ActionForward getBfr(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getBfrList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        request.setAttribute("path", "zhdj_sgygc_jjfzbfgl.do?method=getBfr");

        return mapping.findForward("getBfr");
    }

    //获取积极分子

    public ActionForward getJjfz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getJjfzList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        request.setAttribute("path", "zhdj_sgygc_jjfzbfgl.do?method=getJjzf");

        return mapping.findForward("getJjfz");
    }

    //新增帮扶计划

    public ActionForward saveJjfzbf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        boolean  rs = service.saveJjfzbf(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    //修改帮扶计划

    public ActionForward updateBfjh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        JjfzbfForm myForm =service.getModel(model);
        //取得积极分子和帮扶人的信息
        List<HashMap<String,String>> jjfzList= service.getjjfzInfo(myForm);
        request.setAttribute("bfid",model.getBfid());
        request.setAttribute("jjfz",jjfzList.get(0));
        List<HashMap<String,String>> bfrList= service.getbfrInfo(myForm);
        request.setAttribute("bfr",bfrList.get(0));
        request.setAttribute("bfjhnr",myForm.getBfjhnr());
        request.setAttribute("bfjhmb",myForm.getBfjhmb());
        request.setAttribute("bfjhcs",myForm.getBfjhcs());
        return mapping.findForward("update");
    }

    //修改保存帮扶计划
    public ActionForward saveUpdateJjfzbf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        boolean  rs = service.updateSava(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

   //删除帮扶计划
   public ActionForward delBfjh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {//判断要删除的日程是否存在数据

       JjfzbfForm model = (JjfzbfForm) form;
       boolean rs = service.delJjfzbf(model);
       String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
       response.getWriter().print(getJsonMessageByKey(messageKey));
       return null;
   }

   //查看帮扶计划
   public ActionForward ckBfjh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
       JjfzbfForm model = (JjfzbfForm) form;
       JjfzbfForm myForm =service.getModel(model);
       //取得积极分子和帮扶人的信息
       List<HashMap<String,String>> jjfzList= service.getjjfzInfo(myForm);
       request.setAttribute("bfid",model.getBfid());
       request.setAttribute("jjfz",jjfzList.get(0));
       List<HashMap<String,String>> bfrList= service.getbfrInfo(myForm);
       request.setAttribute("bfr",bfrList.get(0));
       request.setAttribute("bfjhnr",myForm.getBfjhnr());
       request.setAttribute("bfjhmb",myForm.getBfjhmb());
       request.setAttribute("bfjhcs",myForm.getBfjhcs());
       return mapping.findForward("ckBfjh");
   }

   //维护实施情况

    public ActionForward whSsqk(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        JjfzbfForm myForm =service.getModel(model);
        //取得积极分子和帮扶人的信息
        List<HashMap<String,String>> jjfzList= service.getjjfzInfo(myForm);
        request.setAttribute("bfid",model.getBfid());
        request.setAttribute("jjfz",jjfzList.get(0));
        List<HashMap<String,String>> bfrList= service.getbfrInfo(myForm);
        request.setAttribute("bfr",bfrList.get(0));
        //获取两种类型的实施情况表（主要事实 1、汇报 2）
        List<HashMap<String ,String>>zyssList = service.getZyssList(model);//主要事实
        List<HashMap<String ,String>> hbList = service.getHbList(model);//汇报
        request.setAttribute("zyssList",zyssList);
        request.setAttribute("zyssNum",zyssList.size());
        request.setAttribute("hbList",hbList);
        request.setAttribute("hbNum",hbList.size());
        return mapping.findForward("whSsqk");
    }

    public ActionForward whSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        JjfzbfService jjfzbfService = TransactionControlProxy.newProxy(new JjfzbfService());
        //根据bfid先删除实施情况表
        boolean  rs = service.delSsqk(model);
        //在根据bfid插入实施情况表
        if(model.getZysss()!=null)
        {
            rs = jjfzbfService.insertZyss(model);
        }
        if(model.getHbs()!=null)
        {
            rs = jjfzbfService.insertHb(model);
        }
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 查看实施情况
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward ckSsqk(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        JjfzbfForm model = (JjfzbfForm) form;
        JjfzbfForm myForm =service.getModel(model);
        //取得积极分子和帮扶人的信息
        List<HashMap<String,String>> jjfzList= service.getjjfzInfo(myForm);
        request.setAttribute("bfid",model.getBfid());
        request.setAttribute("jjfz",jjfzList.get(0));
        List<HashMap<String,String>> bfrList= service.getbfrInfo(myForm);
        request.setAttribute("bfr",bfrList.get(0));
        //获取两种类型的实施情况表（主要事实 1、汇报 2）
        List<HashMap<String ,String>>zyssList = service.getZyssList(model);//主要事实
        List<HashMap<String ,String>> hbList = service.getHbList(model);//汇报
        request.setAttribute("zyssList",zyssList);
        request.setAttribute("zyssNum",zyssList.size());
        request.setAttribute("hbList",hbList);
        request.setAttribute("hbNum",hbList.size());
        return mapping.findForward("ckSsqk");
    }


    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjfzbfForm model = (JjfzbfForm) form;

        //生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getDCList(model,user);

        //导出功能代码
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//当前操作员
        exportModel.setDataList(resultList);//设置数据
        exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
        File file = exportService.getExportFile(exportModel);//生成导出文件
        FileUtil.outputExcel(response, file);
        return null;
    }




}

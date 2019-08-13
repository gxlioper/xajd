package com.zfsoft.xgxt.xszz.lstd;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：绿色通道action
 * @作者：WANCHEN
 * @日期：2018-09-25
 */
public class LstdAction extends BasicAction {

    private LstdService service = new LstdService();
//    private JcszService jcszService=new JcszService();
    private BdpzService bdpzService = new BdpzService();
    private XsxxService xsxxService = new XsxxService();
    private JtqkdcService jtqkdcService = new JtqkdcService();
    private CommShlcImpl shlc = new CommShlcImpl();
    private XtwhShlcService shlcService = new XtwhShlcService();

    public ActionForward jcsz(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LstdForm model = (LstdForm) form;
        if(SAVE.equals(model.getType())){
            boolean flag = service.saveJcsz(model);
            String msg = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(msg));
            return null;
        }
        Map map = service.getJcsz();
        request.setAttribute("model",map);
        List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xszz");
        request.setAttribute("shlcList", shlc);
        return mapping.findForward("jcsz");
    }
    @SystemAuth(url = "xszz_lstdsq.do")
    public ActionForward lstdsqList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            //查询
            List<HashMap<String,String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xszz_lstdsq.do?method=lstdsqList");
        request.setAttribute("isOpean",service.isOpean());
        return mapping.findForward("lstdsqList");
    }

    /**
     * 申请画面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "xszz_lstdsq.do")
    public ActionForward lstdsqZj(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;
        User user = getUser(request);
        model.setXh(user.getUserName());
        if (SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())){
            if(!service.isExist(model)){
                boolean flag = service.saveLstdSq(model);
                if(flag){
                    response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_SUCCESS));
                } else {
                    response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
                }
            } else {
                Map<String,String> map = new HashMap<String, String>();
                map.put("message", "本学期已申请过绿色通道！");
                JSONObject json = JSONObject.fromObject(map);
                response.getWriter().print(json);
            }

            return null;
        }
        Map map = service.getJcsz();
        request.setAttribute("jssj",map.get("sqjssj"));

        return mapping.findForward("lstdsqZj");
    }

    /**
     * 修改画面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "xszz_lstdsq.do")
    public ActionForward lstdsqXg(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;
        LstdForm result = service.getModel(model.getSqid());
        if (SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())){
            boolean flag = service.updateLstdsq(model);
            if(flag){
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_SUCCESS));
            } else {
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
            }
            return null;
        }
        Map map = service.getJcsz();
        request.setAttribute("jssj",map.get("sqjssj"));
        request.setAttribute("hjfs",result.getHjfs());
        BeanUtils.copyProperties(model, StringUtils.formatData(result));
        return mapping.findForward("lstdsqXg");
    }

    /**
     * 删除绿色通道
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delLstdsq(ActionMapping mapping, ActionForm form,
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
     * 审核列表页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "xszz_lstdsh.do")
    public ActionForward lstdshList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            //查询
            List<HashMap<String,String>> resultList = service.getShList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xszz_lstdsh.do?method=lstdshList");
//        JcszForm jcszForm = jcszService.getModel();
//        request.setAttribute("isopen", jcszForm.getIsopen());
        return mapping.findForward("lstdshList");
    }

    public ActionForward shExport(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        List<HashMap<String, String>> resultList = null;
        model.getPages().setMaxPage(Integer.MAX_VALUE);
        resultList = service.getShList(model,user);// 查询出所有记录，不分页

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

    @SystemAuth(url = "xszz_lstdsh.do")
    public ActionForward lstdsh(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;
        if(SAVE.equals(model.getType())){
            User user = getUser(request);
            boolean flag = service.saveSh(model,user);
            String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        //学生基本信息显示配置
        List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("knsrd");
        HashMap<String,String> jbxx = xsxxService.getXsjbxxMore(model.getXh());
        //按学号加载成员列表
        List<HashMap<String,String>> cyList = jtqkdcService.getJtcyList(model.getXh());
        //家庭情况调查信息加载
        JtqkdcForm jtqkModel = jtqkdcService.getModel(model.getXh());
        //家庭成员显示配置
        List<HashMap<String,String>> jtcyList = bdpzService.getBdpz("jtcy");

        LstdForm data = service.getModel(model);
        //截止时间
        Map map = service.getJcsz();
        request.setAttribute("jssj",map.get("sqjssj"));
        request.setAttribute("data",data);
        request.setAttribute("jtqkModel",jtqkModel);
        request.setAttribute("jtcyList", jtcyList);
        request.setAttribute("cyList", cyList);
        request.setAttribute("jbxxList",jbxxList);
        request.setAttribute("jbxx",jbxx);
        request.setAttribute("model", StringUtils.formatData(model));
        return mapping.findForward("lstdsh");
    }

    @SystemAuth(url = "xszz_lstdsh.do")
    public ActionForward lstdplsh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LstdForm model = (LstdForm) form;
        User user = getUser(request);
        if("SAVE".equalsIgnoreCase(model.getType())){
            String message = service.savePlsh(model, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("lstdplsh");
    }

    @SystemAuth(url = "xszz_lstdsq.do")
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sqid = request.getParameter("values");
        String lcid = request.getParameter("splcid");
        //只有刚提交并且第一级未审核的前提下，申请人可以撤销
        boolean result = service.cancelLstdsq(sqid,lcid);
        if(result){
            //更新业务状态为'未提交'
            LstdForm model = new LstdForm();
            model.setSqid(sqid);
            model.setShlc(lcid);
            ShlcDao shlcdao = new ShlcDao();
            if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
                model.setShzt(Constants.YW_YTH);
            }else{
                model.setShzt(Constants.YW_WTJ);
            }
            service.updateCxLstdsq(model);
        }
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = "xszz_lstdsh.do")
    public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LstdForm model = (LstdForm) form;
        String sqid = request.getParameter("sqid");
        String shzt = request.getParameter("shzt");
        model.setShzt(shzt);
        model.setSqid(sqid);
        //撤销审核，最后一级。
        boolean isSuccess = service.CancelSh(model);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;

    }

    @SystemAuth(url = "xszz_lstdsq.do")
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LstdForm model = (LstdForm) form;
        String sqid = request.getParameter("values");
        String xh = request.getParameter("xh");
        model.setSqid(sqid);
        model.setXh(xh);
        //判断提交时间段是否开放
        boolean result = service.submitLstd(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward print(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LstdForm model = (LstdForm) form;
        List<PrintModel> printModels = new ArrayList<PrintModel>();
        PrintModel printModel;
        LstdForm result = new LstdForm();
        HashMap<String,String> xsjbxx;
        List<HashMap<String,String>> jtcyList;
        List<HashMap<String,String>> shyjlist;
        String jgid = request.getParameter("jgid");
        printModel = new PrintModel();
        if(!StringUtil.isNull(model.getSqid())){
            result = service.getModel(model.getSqid());
            shyjlist = shlc.getShyjListByYwid(model.getSqid());
            result.setSyshyj(shyjlist.get(0).get("shyj"));
            result.setXxshyj(shyjlist.get(1).get("shyj"));
        }else {
            LstdjgService lstdjgService = new LstdjgService();
            LstdjgForm lstdjgForm = new LstdjgForm();
            lstdjgForm = lstdjgService.getModel(jgid);
            BeanUtils.copyProperties(result,lstdjgForm);
        }
        xsjbxx = xsxxService.getXsjbxxMore(result.getXh());
        jtcyList = jtqkdcService.getJtcyList(result.getXh());
        printModel.setLstdForm(result);
        printModel.setXsjbxx(xsjbxx);
        printModel.setJtcyList(jtcyList);
        printModel.setSize(String.valueOf(jtcyList.size()+1));
        printModels.add(printModel);
        request.setAttribute("printModels",printModels);
        return mapping.findForward("print");
    }

    @SystemAuth(url = "xszz_lstdjg.do")
    public ActionForward lstdjgList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            //生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            //查询
            List<HashMap<String,String>> resultList = service.getJgList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xszz_lstdjg.do?method=lstdjgList");
        return mapping.findForward("lstdjgList");
    }

    @SystemAuth(url = "xszz_lstdjg.do")
    public ActionForward lstdjgZj(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;
        if (SAVE.equalsIgnoreCase(model.getType())){
            boolean flag = service.saveLstdJg(model);
            if(flag){
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_SUCCESS));
            } else {
                response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
            }
            return null;
        }

        //学生基本信息显示配置
        if(!StringUtil.isNull(model.getXh())){
            HashMap<String,String> jbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx",jbxx);
        }
        List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("knsrd");
        request.setAttribute("jbxxList",jbxxList);
        request.setAttribute("path", "xszz_lstd.do?method=lstdjgZj");
        return mapping.findForward("lstdjgZj");
    }

    public ActionForward delLstdjg(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String values = request.getParameter("values");

        if (!StringUtil.isNull(values)){
            int num = service.deleteJg(values.split(","));
            boolean result =  num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    public ActionForward export(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LstdForm model = (LstdForm) form;

        // 生成高级查询对象
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        List<HashMap<String, String>> resultList = null;
        model.getPages().setMaxPage(Integer.MAX_VALUE);
        resultList = service.getJgList(model,user);// 查询出所有记录，不分页

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

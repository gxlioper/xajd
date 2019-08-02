package com.zfsoft.xgxt.xyfd.pbwh.pbsq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshForm;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
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
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/7/30.
 */
public class PbsqAction extends SuperAction<PbsqForm,PbsqService> {
    FdsService fdsService = new FdsService();
    PbsqService service = new PbsqService();
    public ActionForward pbsqList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbsqForm model = (PbsqForm)form;

        if (QUERY.equalsIgnoreCase(model.getType())) {
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
        // 默认高级查询条件
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        String path = "xyfd_xyfd_pbsq.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("pbsqList");
    }

    /**
     * 加载申请增加页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward pbsqAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        PbsqForm model = (PbsqForm)form;
        User user = getUser(request);
        model.setXh(user.getUserName());
        HashMap<String,String> zypmlist = service.getZypmlist(model,new User());//专业排名信息
        List<HashMap<String,String>> jlxxlist = service.getJlxx(model);//奖助学金及表彰奖励
        HashMap<String,String> xsxxlist = service.getXsxx(model);//学生信息
        request.setAttribute("zypmlist",zypmlist);
        StringBuilder jlxx = new StringBuilder();
        for(int i=0;i<jlxxlist.size();i++){
            jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
        }
        request.setAttribute("jlxx",jlxx);
        request.setAttribute("xsxxlist",xsxxlist);

        return mapping.findForward("pbsqAdd");
    }

    /**
     * 保存/提交申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveAdd(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        PbsqForm model = (PbsqForm)form;
        User user = getUser(request);
        model.setXh(user.getUserName());
        model.setLrr(user.getUserName());
        model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        model.setMon(StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(StringUtils.join(request.getParameterValues("sund"),","));
        boolean result = service.savePbsq(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 加载修改页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward pbsqUpdate(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        PbsqForm model = (PbsqForm)form;
        HashMap<String,String> pbxxlist = service.getPbxx(model);
        request.setAttribute("pbxxlist",pbxxlist);//朋辈志愿者信息
        BeanUtils.copyProperties(model,pbxxlist);
        HashMap<String,String> zypmlist = service.getZypmlist(model,new User());//专业排名信息
        List<HashMap<String,String>> jlxxlist = service.getJlxx(model);//奖助学金及表彰奖励
        HashMap<String,String> xsxxlist = service.getXsxx(model);//学生信息
        request.setAttribute("zypmlist",zypmlist);
        StringBuilder jlxx = new StringBuilder();
        for(int i=0;i<jlxxlist.size();i++){
            jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
        }
        request.setAttribute("jlxx",jlxx);
        request.setAttribute("xsxxlist",xsxxlist);
        FdsForm fdsForm = new FdsForm();
        fdsForm.setId(model.getFds());
        HashMap<String,String> fdsxx = fdsService.getFds(fdsForm);
        request.setAttribute("fdsxx",fdsxx);//辅导室信息

        return mapping.findForward("pbsqUpdate");
    }

    /**
     * 保存/提交修改申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveUpdate(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        PbsqForm model = (PbsqForm)form;
        model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        model.setMon(StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(StringUtils.join(request.getParameterValues("sund"),","));
        boolean result = service.saveUpdate(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 查看朋辈志愿者申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward pbsqView(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        PbsqForm model = (PbsqForm)form;
        //=====================朋辈志愿者信息=====================//
        HashMap<String,String> pbxxlist = service.getPbxx(model);
        BeanUtils.copyProperties(model,pbxxlist);
//        request.setAttribute("pbxxlist",pbxxlist);
        request.setAttribute("rs", xgxt.utils.String.StringUtils.formatData(pbxxlist));

        //==================专业排名信息=====================//
        HashMap<String,String> zypmlist = service.getZypmlist(model,new User());
        request.setAttribute("zypmlist",zypmlist);
        //======================学生信息=====================//
        HashMap<String,String> xsxxlist = service.getXsxx(model);
        request.setAttribute("xsxxlist",xsxxlist);
        //=================奖助学金及表彰奖励====================//
        List<HashMap<String,String>> jlxxlist = service.getJlxx(model);
        StringBuilder jlxx = new StringBuilder();
        for(int i=0;i<jlxxlist.size();i++){
            jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
        }
        request.setAttribute("jlxx",jlxx);
        //======================辅导室信息=====================//
        FdsForm fdsForm = new FdsForm();
        fdsForm.setId(model.getFds());
        HashMap<String,String> fdsxx = fdsService.getFds(fdsForm);
        request.setAttribute("fdsxx",fdsxx);

        return mapping.findForward("pbsqView");
    }

    /**
     * 删除朋辈志愿者申请
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward pbsqDel(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
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

    /**
     * 提交
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PbsqForm model = (PbsqForm) form;
        String values = request.getParameter("values");
        model.setSqid(values);
        boolean result = service.submit(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
                : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("values");
        String lcid = request.getParameter("splcid");
        // 只有刚提交并且第一级未审核的前提下，申请人可以撤销
        boolean result = service.cancelRecord(id, lcid);
        if (result) {
            // 更新业务状态为'未提交'
            PbsqForm model = new PbsqForm();
            model.setSqid(id);
            model.setSplc(lcid);
            // 查看是否有退回记录,有：审核状态就为退回
            ShlcDao shlcdao = new ShlcDao();
            if (Integer.valueOf(shlcdao.getExistTh(id)) > 0) {
                model.setShzt(Constants.YW_YTH);
            } else {
                model.setShzt(Constants.YW_WTJ);
            }
            service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}

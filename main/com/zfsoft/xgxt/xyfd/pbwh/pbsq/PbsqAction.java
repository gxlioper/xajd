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
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        String path = "xyfd_xyfd_pbsq.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("pbsqList");
    }

    /**
     * ������������ҳ��
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
        HashMap<String,String> zypmlist = service.getZypmlist(model,new User());//רҵ������Ϣ
        List<HashMap<String,String>> jlxxlist = service.getJlxx(model);//����ѧ�𼰱��ý���
        HashMap<String,String> xsxxlist = service.getXsxx(model);//ѧ����Ϣ
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
     * ����/�ύ����
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
     * �����޸�ҳ��
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
        request.setAttribute("pbxxlist",pbxxlist);//��־Ը����Ϣ
        BeanUtils.copyProperties(model,pbxxlist);
        HashMap<String,String> zypmlist = service.getZypmlist(model,new User());//רҵ������Ϣ
        List<HashMap<String,String>> jlxxlist = service.getJlxx(model);//����ѧ�𼰱��ý���
        HashMap<String,String> xsxxlist = service.getXsxx(model);//ѧ����Ϣ
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
        request.setAttribute("fdsxx",fdsxx);//��������Ϣ

        return mapping.findForward("pbsqUpdate");
    }

    /**
     * ����/�ύ�޸�����
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
     * �鿴��־Ը������
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
        //=====================��־Ը����Ϣ=====================//
        HashMap<String,String> pbxxlist = service.getPbxx(model);
        BeanUtils.copyProperties(model,pbxxlist);
//        request.setAttribute("pbxxlist",pbxxlist);
        request.setAttribute("rs", xgxt.utils.String.StringUtils.formatData(pbxxlist));

        //==================רҵ������Ϣ=====================//
        HashMap<String,String> zypmlist = service.getZypmlist(model,new User());
        request.setAttribute("zypmlist",zypmlist);
        //======================ѧ����Ϣ=====================//
        HashMap<String,String> xsxxlist = service.getXsxx(model);
        request.setAttribute("xsxxlist",xsxxlist);
        //=================����ѧ�𼰱��ý���====================//
        List<HashMap<String,String>> jlxxlist = service.getJlxx(model);
        StringBuilder jlxx = new StringBuilder();
        for(int i=0;i<jlxxlist.size();i++){
            jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
        }
        request.setAttribute("jlxx",jlxx);
        //======================��������Ϣ=====================//
        FdsForm fdsForm = new FdsForm();
        fdsForm.setId(model.getFds());
        HashMap<String,String> fdsxx = fdsService.getFds(fdsForm);
        request.setAttribute("fdsxx",fdsxx);

        return mapping.findForward("pbsqView");
    }

    /**
     * ɾ����־Ը������
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
     * �ύ
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
        // ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
        boolean result = service.cancelRecord(id, lcid);
        if (result) {
            // ����ҵ��״̬Ϊ'δ�ύ'
            PbsqForm model = new PbsqForm();
            model.setSqid(id);
            model.setSplc(lcid);
            // �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
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

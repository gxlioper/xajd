package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsForm;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgForm;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgService;
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
 * Created by llf on 2019/8/2.
 */
public class FdkcsqAction extends SuperAction<FdkcsqForm,FdkcsqService> {
    FdsService fdsService = new FdsService();
    FdkcsqService service = new FdkcsqService();
    FdjsService fdjsService = new FdjsService();
    PbjgService pbjgService = new PbjgService();

    public ActionForward fdkcsqList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcsqForm model = (FdkcsqForm)form;

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
        String path = "xyfd_xyfd_fdkcsq.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdkcsqList");
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
    public ActionForward fdkcsqAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        User user = getUser(request);
        boolean isAdmin = service.isAdmin(user);
        request.setAttribute("isAdmin",isAdmin);
        return mapping.findForward("fdkcsqAdd");
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
        FdkcsqForm model = (FdkcsqForm)form;
        User user = getUser(request);
        model.setLrr(user.getUserName());
        model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        model.setMon(StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(StringUtils.join(request.getParameterValues("sund"),","));
        boolean result = service.saveFdkc(model);
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
    public ActionForward fdkcsqUpdate(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        model = service.getModel(model);
        request.setAttribute("model",model);//�����γ���Ϣ
        StringBuilder fdjs = new StringBuilder();
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){//��ʦ�ǼǺ�
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "��" + xm);
        }else if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){//��־Ը�ߵǼǺ�
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "��" + xm);
        }else {
            throw new Exception("�ǼǺŲ�����");
        }
        request.setAttribute("fdjsxm",fdjs.toString());
        User user = getUser(request);
        boolean isAdmin = service.isAdmin(user);
        request.setAttribute("isAdmin",isAdmin);
        HashMap<String,String> fdjsxx = service.getFdjsxx(model);//������ʦ����־Ը�ߣ���Ϣ
        request.setAttribute("fdjsxx",fdjsxx);
        return mapping.findForward("fdkcsqUpdate");
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
        FdkcsqForm model = (FdkcsqForm)form;
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
    public ActionForward fdkcsqView(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        model = service.getModel(model);
        request.setAttribute("model",model);//�����γ���Ϣ
        StringBuilder fdjs = new StringBuilder();
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){//��ʦ�ǼǺ�
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "��" + xm);
        }else if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){//��־Ը�ߵǼǺ�
            String xm = service.getFdjs(model);
            fdjs.append(model.getFdjs() + "��" + xm);
        }else {
            throw new Exception("�ǼǺŲ�����");
        }
        request.setAttribute("fdjsxm",fdjs.toString());
        HashMap<String,String> fdjsxx = service.getFdjsxx(model);//������ʦ����־Ը�ߣ���Ϣ
        request.setAttribute("fdjsxx",fdjsxx);


        return mapping.findForward("fdkcsqView");
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
    public ActionForward fdkcsqDel(ActionMapping mapping, ActionForm form,
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
        FdkcsqForm model = (FdkcsqForm) form;
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
            FdkcsqForm model = new FdkcsqForm();
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

    /**
     * ��ȡ��־Ը��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward selectPb(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPbList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","xyfd_xyfd_pbjg.do");
        return mapping.findForward("selectPb");
    }

    /**
     * ��ȡ������ʦ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward selectFdjs(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getFdjsList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","xyfd_xyfd_fdjswh.do");
        return mapping.findForward("selectFdjs");
    }

    /**
     * ����������ȡ������ʦ������Ϣ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getFdjs(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdkcsqForm model = (FdkcsqForm)form;
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){
            FdjsForm fdjsForm = new FdjsForm();
            fdjsForm.setDjh(model.getFdjs());
            HashMap<String, String> result = fdjsService.getFdjs(fdjsForm);
            JSONObject data = new JSONObject();
            data.put("fdjs",result);
            response.getWriter().print(data);

        }else if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){
            PbjgForm pbjgForm = new PbjgForm();
            pbjgForm.setDjh(model.getFdjs());
            HashMap<String, String> result = pbjgService.getPbjs(pbjgForm);
            JSONObject data = new JSONObject();
            data.put("fdjs",result);
            response.getWriter().print(data);
        }

        return null;
    }
}

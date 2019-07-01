/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-27 ����04:22:43
 */
package com.zfsoft.xgxt.jjgl.xqwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;
import com.zfsoft.xgxt.jjgl.jjzg.JjzgService;
import com.zfsoft.xgxt.jjgl.zcyh.ZcyhService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-27 ����04:22:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqwhAction extends SuperAction<XqwhForm, XqwhService> {

    /**
     *  @���ԣ� PATH ·��
     */
    private static final String PATH = "jjgl_xqwh.do";

    private static final String url = "jjgl_xqwh.do";

    /**
     *
     * @����:�������ҳ��
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-26 ����06:43:32
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward xqwhCx(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        User user = getUser(request);

        if ("stu".equalsIgnoreCase(user.getUserType())) {
            String msg = "��ģ�鲻�ʻ����ʣ���ȷ�ϣ�";
            request.setAttribute("yhInfo", msg);
            return new ActionForward("/yhInfo.do", false);
        }
        //�ҽ��꼶�б�
        JjnjService jjnjService = new JjnjService();
        List<HashMap<String, String>> jjnjList = jjnjService.getJjnjList();
        request.setAttribute("jjnjList", jjnjList);

        //�ҽ�ѧ���б�
        JjxkService jjxkService = new JjxkService();
        List<HashMap<String, String>> jjxkList = jjxkService.getJjxkList();
        request.setAttribute("jjxkList", jjxkList);
        // ��ȡ�û����Ƿ��д��Ȩ��  �� title
        request.setAttribute("path", PATH);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xqwhCx");
    }


    /**
     *
     * @����:��ѯ�ҽ������б�
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-27 ����09:43:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward queryXqList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XqwhForm model = (XqwhForm) form;

        //��ѯ
        List<HashMap<String, String>> resultList = getService().getPageList(model);
        if (resultList == null) {
            resultList = new ArrayList<>();
        }
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);

        return null;
    }

    /**
     *
     * @����:����ҽ���ʦ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-28 ����10:35:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assign(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //����id����
        String xqid = model.getXqid();
        HashMap<String, String> xqModelMap = null;
        HashMap<String, String> znxxMap = null;
        List<HashMap<String, String>> xqXsSqList = null;
        //�ҽ�������Ϣ
        if (StringUtils.isNotBlank(xqid)) {
            xqModelMap = new JjxqService().getModelMap(xqid);
        }
        //��Ů��Ϣ
        if (xqModelMap != null && StringUtils.isNotBlank(xqModelMap.get("znid"))) {
            znxxMap = new ZcyhService().getZnxxMapById(xqModelMap.get("znid"));
        }
        //�������б�
        if (StringUtils.isNotBlank(xqid)) {
            xqXsSqList = getService().getXqXsSqList(xqid);
        }
        request.setAttribute("xqXsSqList", xqXsSqList);
        request.setAttribute("znxxMap", xgxt.utils.String.StringUtils.formatData(znxxMap));
        request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
        return mapping.findForward("assign");
    }

    /**
     *
     * @����:�ҽ�״̬�ύ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-28 ����10:35:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward changeJJzt(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        String xqid = model.getXqid();
        //��ѯ������Ϣ
        if (StringUtils.isNotBlank(xqid)) {
            HashMap<String, String> xqxxDetailsByXqid = getService().getXqxxDetailsByXqid(xqid);
            request.setAttribute("xqxxMap", xgxt.utils.String.StringUtils.formatData(xqxxDetailsByXqid));
        }
        return mapping.findForward("xgzt");
    }

    /**
     *
     * @����:�޸ļҽ�״̬�ύ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-28 ����10:35:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward changeJJztSubmit(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //ִ��
        HashMap<String, String> assign = getService().changeJjzt(model);
        JSONObject message = JSONObject.fromMap(assign);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @����:�ֹ�����ҽ���ʦ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-28 ����10:35:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assignManual(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //����id����
        String xqid = model.getXqid();
        HashMap<String, String> jjlsxxMap = null;
        Map<String, String> xqwhMap = getService().getXqwhMap(xqid);
        request.setAttribute("xqwhMap", xgxt.utils.String.StringUtils.formatData(xqwhMap));
        //��������Ϣ
        if (StringUtils.isNotBlank(model.getXh())) {
            jjlsxxMap = new JjzgService().getModelMap(model.getXh());
        }
        request.setAttribute("jjlsxxMap", xgxt.utils.String.StringUtils.formatData(jjlsxxMap));
        //Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", URLEncoder.encode("jjgl_xqwhgl.do?method=assignManual&xqid=" + xqid, "gbk"));
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("assignManual");
    }


    /**
     *
     * @����:�ֹ�����ҽ���ʦ�ύ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-28 ����10:35:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assignManualSubmit(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //ִ��
        HashMap<String, String> assign = getService().assignManual(model);
        JSONObject message = JSONObject.fromMap(assign);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @����:�ύ����ҽ���ʦ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-28 ����03:25:02
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward assignSubmit(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        //ִ��
        HashMap<String, String> assign = getService().assign(model);
        JSONObject message = JSONObject.fromMap(assign);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @����:�鿴Ͷ����Ϣ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-27 ����09:43:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward viewTsxx(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XqwhForm model = (XqwhForm) form;
        if (StringUtils.isNotBlank(model.getXqid())) {
            //��ѯ
            List<HashMap<String, String>> tsfkxxList = getService().getTsxxList(model.getXqid());
            request.setAttribute("tsfkxxList", tsfkxxList);
        }

        return mapping.findForward("tsfk");
    }

    /**
     *
     * @����:Ͷ�߷����ύ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-30 ����10:45:06
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward tsfkSubmit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm model = (XqwhForm) form;
        model.setFksj(DateUtils.getCurrTime());
        JSONObject message = null;
        boolean isSuccess = getService().updateTsxx(model);
        message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
        response.getWriter().print(message);
        return null;
    }


    /**
     *
     * @����:�鿴������Ϣ
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-27 ����09:43:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward viewPjxx(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XqwhForm model = (XqwhForm) form;
        if (StringUtils.isNotBlank(model.getXqid())) {
            //��ѯ
            HashMap<String, String> pjxxModel = getService().getPjxxList(model.getXqid());
            request.setAttribute("pjxxModel", xgxt.utils.String.StringUtils.formatData(pjxxModel));
        }

        return mapping.findForward("ckpj");
    }


    /**
     *
     * @����:�鿴
     * @���ߣ���С��[���ţ�1036]
     * @���ڣ�2014-8-28 ����10:35:13
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    @SystemAuth(url = url)
    public ActionForward ck(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XqwhForm xqwhForm = (XqwhForm) form;
        //����id����
        String xqid = xqwhForm.getXqid();

        Map<String, String> xqwhMap = getService().getXqwhMap(xqid);
        request.setAttribute("xqwhMap", xqwhMap);

        return mapping.findForward("ck");
    }

    /**
     * �ҽ���������ҳ��.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //�Ǽ���
        request.setAttribute("djr", getUser(request).getRealName());

        //�ҽ��꼶�б�
        JjnjService jjnjService = new JjnjService();
        List<HashMap<String, String>> jjnjList = jjnjService.getJjnjList();
        request.setAttribute("jjnjList", jjnjList);

        //�ҽ�ѧ���б�
        JjxkService jjxkService = new JjxkService();
        List<HashMap<String, String>> jjxkList = jjxkService.getJjxkList();
        request.setAttribute("jjxkList", jjxkList);
        return mapping.findForward("xqwhAdd");
    }

    /**
     * �ҽ������޸�ҳ��.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        XqwhForm xqwhForm = (XqwhForm) form;
        //����id����
        String xqid = xqwhForm.getXqid();

        Map<String, String> xqwhMap = getService().getXqwhMap(xqid);
        request.setAttribute("xqwhMap", xqwhMap);

        //��Ů��Ϣ
        List<HashMap<String, String>> znxxList = new ZcyhService().getZnxxListByPid(xqwhMap.get("sqr"));
        request.setAttribute("znxxList", znxxList);

        //�ҽ��꼶�б�
        JjnjService jjnjService = new JjnjService();
        List<HashMap<String, String>> jjnjList = jjnjService.getAllList(new JjnjForm());
        request.setAttribute("jjnjList", jjnjList);

        //�ҽ�ѧ���б�
        JjxkService jjxkService = new JjxkService();
        List<HashMap<String, String>> jjxkList = jjxkService.getAllList(new JjxkForm());
        request.setAttribute("jjxkList", jjxkList);
        return mapping.findForward("xqwhEdit");
    }

    /**
     * �ҽ�����ɾ��.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            int num = getService().xqwhDel(values.split(","));
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * ��ת��ѡ��ҳ���Ϣ�б�ҳ��.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showJzxxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return mapping.findForward("showJzxxList");
    }

    /**
     * �ҽ��������ӣ��ı���.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhSaveForAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        XqwhForm xqwhForm = (XqwhForm) form;
        boolean result = getService().xqwhSaveForAdd(xqwhForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * �ҽ������޸ģ��ı���.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    public ActionForward xqwhSaveForEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        XqwhForm xqwhForm = (XqwhForm) form;
        boolean result = getService().xqwhSaveForEdit(xqwhForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward downXys(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getSession().getServletContext().getRealPath(
                "/temp/mb/")+"/jjxys.doc";
        logger.info("path = "+path);
        if (xgxt.utils.String.StringUtils.isNotNull(path)){
            File file = new File(path);
            if (file.exists()){
                response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("jjxys.doc","utf-8"));
                FileUtil.outputFile(response, file);
            }
        }
        return null;
    }

}

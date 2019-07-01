package com.zfsoft.xgxt.jjgl.jjlsjg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgForm;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * �����󣺼ҽ���ʦ���action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-11-19 16:42
 */
public class JjlsjgAction extends SuperAction<JjlsjgForm,JjlsjgService> {

    private final String JJGL = "jjgl";
    private JjlsjgService jjlsjgService = new JjlsjgService();
    private static final String url = "jjgl_jjlsjg.do";

    /**
     * ��ת���ҽ���ʦ����б�ҳ��.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throw Exception
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-11-20 14:16
     */
    @SystemAuth(url = url)
    public ActionForward jjlsjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jjlsjgList");
    }

    /**
     * ��ȡ�ҽ���ʦ����б�JSON����.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throw Exception
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-11-20 14:21
     */
    @SystemAuth(url = url)
    public ActionForward getJjlsjgListData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        jjlsjgForm.setSearchModel(searchModel);
        User user = getUser(request);
        // ��ѯ
        List<HashMap<String, String>> resultList = jjlsjgService.getPageList(jjlsjgForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * @����:�ҽ���ʦ����Ĳ鿴
     * @���ߣ�xuwen[���ţ�1426]
     * @���ڣ�2017��5��9�� ����4:43:20
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
    public ActionForward jjlsjgShow(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        JjlsjgForm model = jjlsjgService.getModel(jjlsjgForm.getXh());
        if(StringUtils.isNotNull(model.getXh())){
            BeanUtils.copyProperties(jjlsjgForm, StringUtils.formatData(model));
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        // ѧ��������Ϣ��ʾ����
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JJGL);
        request.setAttribute("jbxxList", jbxxList);
        return mapping.findForward("jjlsjgShow");
    }

    /**
     * ��ת���ҽ���ʦ�������ҳ��.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throw
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-11-20 17:58
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())) {
            jjlsjgForm.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(jjlsjgForm.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(jjlsjgForm.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }

        String path = "jjgl_jjlsjggl.do?method=jjlsjgAdd";
        request.setAttribute("path", path);
        // ѧ��������Ϣ��ʾ����
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JJGL);
        request.setAttribute("jbxxList", jbxxList);

        //�ҽ��꼶
        List<HashMap<String,String>> jjnjList = new JjnjService().getAllList(new JjnjForm());
        request.setAttribute("jjnjList",jjnjList);

        //�ҽ�ѧ��
        List<HashMap<String,String>> jjxkList = new JjxkService().getAllList(new JjxkForm());
        request.setAttribute("jjxkList",jjxkList);

        return mapping.findForward("jjlsjgAdd");
    }

    /**
     * @����:�ҽ���ʦ������������ı���
     * @���ߣ�xuwen[���ţ�1426]
     * @���ڣ�2017��5��5�� ����3:22:06
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgSaveForAdd(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        boolean isRepeat = jjlsjgService.isRepeat(jjlsjgForm);
        if (isRepeat) {
            response.getWriter().print(getJsonMessage("�ҽ���ʦ�Ѵ���"));
            return null;
        }
        jjlsjgForm.setDjr(getUser(request).getUserName());
        jjlsjgForm.setDjsj(DateUtils.getCurrTime());
        boolean result = jjlsjgService.runInsert(jjlsjgForm);
        String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @����:ת���ҽ���ʦ����޸ĵ���ҳ��
     * @���ߣ�xuwen[���ţ�1426]
     * @���ڣ�2017��5��5�� ����2:04:19
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        JjlsjgForm model = jjlsjgService.getModel(jjlsjgForm);
        if (StringUtils.isNotNull(model.getXh())) {
            BeanUtils.copyProperties(jjlsjgForm, StringUtils.formatData(model));
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        BdpzService bdpzService = new BdpzService();
        List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JJGL);
        request.setAttribute("jbxxList", jbxxList);

        //�ҽ��꼶
        List<HashMap<String,String>> jjnjList = new JjnjService().getAllList(new JjnjForm());
        request.setAttribute("jjnjList",jjnjList);

        //�ҽ�ѧ��
        List<HashMap<String,String>> jjxkList = new JjxkService().getAllList(new JjxkForm());
        request.setAttribute("jjxkList",jjxkList);

        //�����ó���Ŀ
        String [] sckm = jjlsjgForm.getSckm();
        if(sckm != null && sckm.length != 0){
            List<String> sckmList = Arrays.asList(sckm);
            for(HashMap<String,String> jjxk:jjxkList){
                if(sckmList.contains(jjxk.get("jjxkmc"))){
                    jjxk.put("checked","1");
                }
            }
        }

        String path = "jjgl_jjlsjggl.do?method=jjlsjgEdit";
        request.setAttribute("path", path);
        return mapping.findForward("jjlsjgEdit");
    }

    /**
     * @����:�ҽ���ʦ������޸ģ��ı���
     * @���ߣ�xuwen[���ţ�1426]
     * @���ڣ�2017��5��5�� ����3:22:06
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgSaveForEdit(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;

        boolean result = jjlsjgService.runUpdate(jjlsjgForm);
        String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @����:�ҽ���ʦ�����ɾ������������
     * @���ߣ�xuwen[���ţ�1426]
     * @���ڣ�2017��5��9�� ����9:50:05
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
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jjlsjgDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = jjlsjgService.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * @����:�ҽ���ʦ����ĵ���
     * @���ߣ�xuwen[���ţ�1426]
     * @���ڣ�2017��5��9�� ����2:22:44
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
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        JjlsjgForm jjlsjgForm = (JjlsjgForm) form;
        String dcclbh = request.getParameter("dcclbh");

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        jjlsjgForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = jjlsjgService.getAllList(jjlsjgForm,user);// ��ѯ�����м�¼������ҳ
        // �������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = jjlsjgForm.getExportModel();
        exportModel.setZgh(user.getUserName());// ��ǰ����Ա
        exportModel.setDataList(resultList);// ��������
        exportModel.setDcclbh(dcclbh);// ���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
}

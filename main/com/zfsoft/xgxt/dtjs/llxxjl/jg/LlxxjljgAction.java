package com.zfsoft.xgxt.dtjs.llxxjl.jg;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-07-15 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class LlxxjljgAction extends SuperAction<LlxxjljgForm, LlxxjljgService> {
    private static final String SHSJJL = "shsjjl";
    private LlxxjljgService service = new LlxxjljgService();

    private static final String url = "dtjs_llxxjl_jg.do";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        jbxxList = bdpzService.getJbxxpz(SHSJJL);
    }

    /**
     * ����ѧϰ�����б�
     */
    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LlxxjljgForm model = (LlxxjljgForm) form;
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
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        String path = "dtjs_shsjjl_jg.do";//�����ʵ�����ø߼���ѯ
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("llxxjlJgList");
    }
    /**
     * ����ѧϰ��������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward llxxjlJgAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LlxxjljgForm model = (LlxxjljgForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())) {
            model.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        // ѧ��������Ϣ��ʾ����
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        String path = "llxxjl_jg.do?method=llxxjlJgAdd";
        //sqservice.initParam(request, user);
        request.setAttribute("path", path);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
        return mapping.findForward("llxxjlJgAdd");
    }
    /**
     * ����ѧϰ�����鿴
     */
    @SystemAuth(url = url)
    public ActionForward llxxjlJgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LlxxjljgForm myForm = (LlxxjljgForm) form;
        HashMap<String,String> map = service.getInfo(myForm.getJgid());
        if(null!=map && map.size()>0){
            BeanUtils.copyProperties(myForm, map);
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
            request.setAttribute("jbxx", xsjbxx);
        }else {
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        if("ek".equals(myForm.getSjly())||"ekbl".equals(myForm.getSjly())){
            request.setAttribute("rs", service.getEkxx(myForm));
        }else{
            request.setAttribute("rs", StringUtils.formatData(map));
        }
        return mapping.findForward("llxxjlJgView");
    }
    /**
     * ����ѧϰ��������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LlxxjljgForm model = (LlxxjljgForm) form;
        User user = getUser(request);
        boolean result = false;
        //�жϵ�ǰʱ���Ƿ�����д��¼
        boolean isExist = service.checkExistForSave(model);
        if (!isExist) {
            String message = "ѧ����"+model.getSj()+"ʱ��������ѧϰ��¼����д����ȷ�ϣ�";
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        if("add".equals(model.getType())){
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            model.setSjly("0");
            result = service.runInsert(model);
        }else{
            result = service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    /**
     * ����ѧϰ�����޸�
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward llxxjlJgUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LlxxjljgForm myForm = (LlxxjljgForm) form;
        LlxxjljgForm model = service.getModel(myForm);
        User user =getUser(request);
        if(null!=model){
            BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        String path = "llxxjl_jg.do?method=llxxjlJgUpdate";
        request.setAttribute("path", path);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        return mapping.findForward("llxxjlJgUpdate");
    }
    /**
     * ����ѧϰ����ɾ��
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward del(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //���id
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
     * ����ѧϰ��������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjljgForm model = (LlxxjljgForm) form;
        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
        // �������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());// ��ǰ����Ա
        exportModel.setDataList(resultList);// ��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }

}

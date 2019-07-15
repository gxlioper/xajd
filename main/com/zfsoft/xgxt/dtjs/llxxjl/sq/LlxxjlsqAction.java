package com.zfsoft.xgxt.dtjs.llxxjl.sq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.dtjs.llxxjl.cssz.CsszService;
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
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class LlxxjlsqAction extends SuperAction<LlxxjlsqForm, LlxxjlsqService> {
    private static final String LLXXJL = "shsjjl";//�����ʵ��ģ�鹲��ѧ��������Ϣ
    private LlxxjlsqService service = new LlxxjlsqService();
    private CsszService csszService = new CsszService();

    private static final String url = "dtjs_llxxjl_sq.do";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        jbxxList = bdpzService.getJbxxpz(LLXXJL);
    }

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(
                    model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        String sqshkg = csszService.getSqShKg();
        request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg);
        String path = "dtjs_shsjjl_sq.do"; //�����ʵ�����ø߼���ѯ
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("llxxjlSqList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward llxxjlSqAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())) {
            model.setXh(user.getUserName());
        }
        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
                    .getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        String path = "llxxjl_sq.do?method=llxxjlSqAdd";
        request.setAttribute("path", path);
        // ѧ��������Ϣ��ʾ����
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
        request.setAttribute("xn", Base.currXn);
        request.setAttribute("xq", Base.currXq);
        request.setAttribute("xqmc", Base.getDqxqmc());
        //service.initParam(request, user);
        return mapping.findForward("llxxjlSqAdd");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward llxxjlSqUpdate(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm myForm = (LlxxjlsqForm) form;
        User user = getUser(request);
        LlxxjlsqForm model = service.getModel(myForm);
        if (null != model) {
            BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
                    .getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        BdpzService bdpzService = new BdpzService();
        request.setAttribute("jbxxList", jbxxList);
        String path = "llxxjl_sq.do?method=llxxjlSqUpdate";
        request.setAttribute("path", path);
        request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
        request.setAttribute("xn",model.getXn());
        request.setAttribute("xq", model.getXq());
        request.setAttribute("xqmc", Base.getXqmcForXqdm(model.getXq()));
        //service.initParam(request, user);
        return mapping.findForward("llxxjlSqUpdate");
    }

    @SystemAuth(url = url)
    public ActionForward llxxjlView(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm myForm = (LlxxjlsqForm) form;
        HashMap<String, String> map = service.getInfo(myForm.getSqid());
        if(null!=map){
            BeanUtils.copyProperties(myForm, map);
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
            request.setAttribute("jbxx", xsjbxx);
        }
        // ѧ��������Ϣ��ʾ����
        BdpzService bdpzService = new BdpzService();
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("rs", StringUtils.formatData(map));
        return mapping.findForward("llxxjlSqView");

    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveAdd(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        //�жϵ�ǰʱ���Ƿ��������¼
        boolean isExist = service.checkEdit(model);
        if (!isExist) {
            String message = "ѧ����"+model.getSj()+"ʱ�������ʵ����¼�����룬��ȷ�ϣ�";
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        User user = getUser(request);
        model.setLrr(user.getUserName());
        model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        boolean result = service.saveSthdsq(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        //�жϵ�ǰʱ���Ƿ��������¼
       /* boolean isExist = service.checkEdit(model);
        if (isExist) {
            String message = "��ʱ����ѧ������ʡ��ʱ�������¼����ȷ�ϣ�";
            response.getWriter().print(getJsonMessage(message));
            return null;
        }*/
        boolean result = service.saveUpdate(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward del(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("values");
        String lcid = request.getParameter("splcid");
        // ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
        boolean result = service.cancelRecord(id, lcid);
        if (result) {
            // ����ҵ��״̬Ϊ'δ�ύ'
            LlxxjlsqForm model = new LlxxjlsqForm();
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

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;
        String values = request.getParameter("values");
        model.setSqid(values);
        boolean result = service.submit(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
                : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LlxxjlsqForm model = (LlxxjlsqForm) form;

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = service.getAllList(model,
                user);// ��ѯ�����м�¼������ҳ
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

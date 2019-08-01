package com.zfsoft.xgxt.xyfd.pbwh.pbjg;

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
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgForm;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqForm;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqService;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-08-01 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class PbjgAction extends SuperAction<PbjgForm, PbjgService> {
    private PbjgService service = new PbjgService();
    private PbsqService pbsqService = new PbsqService();
    private FdsService fdsService = new FdsService();

    private static final String url = "xyfd_xyfd_pbjg.do";

    /**
     * ��־Ը�߽���б�
     */
    @SystemAuth(url = url)
    public ActionForward pbjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
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
        String path = "xyfd_xyfd_pbjg.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("pbjgList");
    }
    /**
     * ��־Ը�߽������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addPbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
        User user = getUser(request);
        PbsqForm pbsqForm = new PbsqForm();
        if ("stu".equals(user.getUserType())) {
            throw new Exception("û��Ȩ��");
        }
        if(!StringUtil.isNull(model.getXh())){
            pbsqForm.setXh(model.getXh());
            HashMap<String,String> zypmlist = pbsqService.getZypmlist(pbsqForm,new User());//רҵ������Ϣ
            List<HashMap<String,String>> jlxxlist = pbsqService.getJlxx(pbsqForm);//����ѧ�𼰱��ý���
            HashMap<String,String> xsxxlist = pbsqService.getXsxx(pbsqForm);//ѧ����Ϣ
            request.setAttribute("zypmlist",zypmlist);
            StringBuilder jlxx = new StringBuilder();
            for(int i=0;i<jlxxlist.size();i++){
                jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
            }
            request.setAttribute("jlxx",jlxx);
            request.setAttribute("xsxxlist",xsxxlist);
        }

        String path = "xyfd_pbjg.do?method=addPbjg";
        request.setAttribute("path", path);
        return mapping.findForward("addPbjg");
    }
    /**
     * ��־Ը�߽���鿴
     */
    @SystemAuth(url = url)
    public ActionForward viewPbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm)form;
        model = service.getModel(model);
        PbsqForm pbsqForm = new PbsqForm();
        if(!StringUtil.isNull(model.getXh())) {
            pbsqForm.setXh(model.getXh());

            //==================רҵ������Ϣ=====================//
            HashMap<String, String> zypmlist = pbsqService.getZypmlist(pbsqForm, new User());
            request.setAttribute("zypmlist", zypmlist);
            //======================ѧ����Ϣ=====================//
            HashMap<String, String> xsxxlist = pbsqService.getXsxx(pbsqForm);
            request.setAttribute("xsxxlist", xsxxlist);
            //=================����ѧ�𼰱��ý���====================//
            List<HashMap<String, String>> jlxxlist = pbsqService.getJlxx(pbsqForm);
            StringBuilder jlxx = new StringBuilder();
            for (int i = 0; i < jlxxlist.size(); i++) {
                jlxx.append(jlxxlist.get(i).get("xmmc") + " ");
            }
            request.setAttribute("jlxx", jlxx);
            //======================��������Ϣ=====================//
            FdsForm fdsForm = new FdsForm();
            fdsForm.setId(model.getFds());
            HashMap<String, String> fdsxx = fdsService.getFds(fdsForm);
            request.setAttribute("fdsxx", fdsxx);

        }
        request.setAttribute("rs", xgxt.utils.String.StringUtils.formatData(model));
        return mapping.findForward("viewPbjg");
    }
    /**
     * ��־Ը�߽������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
        User user = getUser(request);
        boolean result = false;
        model.setMon(org.apache.commons.lang.StringUtils.join(request.getParameterValues("mond"),","));
        model.setTues(org.apache.commons.lang.StringUtils.join(request.getParameterValues("tuesd"),","));
        model.setWed(org.apache.commons.lang.StringUtils.join(request.getParameterValues("wedd"),","));
        model.setThur(org.apache.commons.lang.StringUtils.join(request.getParameterValues("thurd"),","));
        model.setFri(org.apache.commons.lang.StringUtils.join(request.getParameterValues("frid"),","));
        model.setSat(org.apache.commons.lang.StringUtils.join(request.getParameterValues("satd"),","));
        model.setSun(org.apache.commons.lang.StringUtils.join(request.getParameterValues("sund"),","));
        if("add".equals(model.getType())){
            String maxDjh = service.getDjh();
            int num = 0;
            String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
            if(org.apache.commons.lang.StringUtils.isNotEmpty(maxDjh)&&maxDjh.substring(2,4).equals(yearLast)){
                num = Integer.parseInt(maxDjh.substring(2,maxDjh.length()))+1;
            }else {
                num = Integer.parseInt(yearLast+"0001");
            }
            model.setDjh("PB"+num);    //���ɵǼǺ�
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
     * ��־Ը�߽���޸�
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward updatePbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbjgForm model = (PbjgForm) form;
        model = service.getModel(model);
        PbsqForm pbsqForm = new PbsqForm();
        if(!StringUtil.isNull(model.getXh())){
            pbsqForm.setXh(model.getXh());
            HashMap<String,String> zypmlist = pbsqService.getZypmlist(pbsqForm,new User());//רҵ������Ϣ
            List<HashMap<String,String>> jlxxlist = pbsqService.getJlxx(pbsqForm);//����ѧ�𼰱��ý���
            HashMap<String,String> xsxxlist = pbsqService.getXsxx(pbsqForm);//ѧ����Ϣ
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
        }else {
            throw new Exception("��ѧ��");
        }
        request.setAttribute("pbxxlist",model);
        return mapping.findForward("updatePbjg");
    }
    /**
     * ��־Ը�߽��ɾ��
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
     * ��־Ը�߽������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PbjgForm model = (PbjgForm) form;
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

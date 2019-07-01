package com.zfsoft.xgxt.qgzx.dgxsk;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:����ѧ����
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-07-06 09:49
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DgxskAction extends SuperAction<DgxskForm,DgxskService> {
    private DgxskService service = new DgxskService();
    private static final String url = "qgzx_gwglnew_dgxsk.do";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        // ѧ��������Ϣ��ʾ����
        jbxxList = new BdpzService().getJbxxpz("cjff");
    }

    @SystemAuth(url = url)
    public ActionForward dgxskManage(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DgxskForm myForm = (DgxskForm) form;
        User user = getUser(request);
        if (QUERY.equals(myForm.getType())) {
            // ==================�߼���ѯ���========================
            CommService cs = new CommService();
            SearchModel searchModel = cs.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            // ==================�߼���ѯ��� end========================
            List<HashMap<String, String>> resultList = service.getPageList(
                    myForm, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "qgzx_gwglnew_dgxsk.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dgxskManage");
    }
    public ActionForward dgxskView(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DgxskForm myForm = (DgxskForm) form;
        User user = getUser(request);
        if ("stu".equals(user.getUserType())){
            myForm.setXh(user.getUserName());
        }
        // ��ѯѧ����Ϣ
        if (myForm.getXh() != null && !"".equals(myForm.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        WdgwsqService wservice = new WdgwsqService();
        List<HashMap<String, String>> qgxmList= wservice.getQgmxList(myForm.getXh());
        request.setAttribute("qgxmList", new QjjgService().getQjxmList());
        request.setAttribute("qgmxList", qgxmList);
        request.setAttribute("qgxmSize", qgxmList.size());
        return mapping.findForward("dgxskView");
    }


    /**����***/
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DgxskForm model = (DgxskForm) form;

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

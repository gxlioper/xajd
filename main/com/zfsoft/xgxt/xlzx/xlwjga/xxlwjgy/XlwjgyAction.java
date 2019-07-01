package com.zfsoft.xgxt.xlzx.xlwjga.xxlwjgy;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
 * @�๦������:����Σ����Ԥ
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-04-10 14:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XlwjgyAction extends SuperAction<XlwjgyForm,XlwjgyService> {
    private XlwjgyService service = new XlwjgyService();
    private static final String url = "xlzx_xlwjga_xlwjgy.do";
    private static List<HashMap<String, String>> jbxxList = null;
    private static final String XLWJGY = "xlwjgy";
    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(XLWJGY);
    }

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XlwjgyForm model = (XlwjgyForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())){
            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            //��ѯ
            List<HashMap<String,String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //Ĭ�ϸ߼���ѯ����
       /* SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_wjgabz(new String[]{ "1" });
        request.setAttribute("searchTj", searchModel);*/
        String path = "xlzx_xlwjga_xlwjgy.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xlwjgyList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="������������ѯ-����Σ����������-����Σ�������ϱ�-����")
    public ActionForward xlwjgyAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XlwjgyForm myForm = (XlwjgyForm) form;
        User user = getUser(request);
        String xh = myForm.getXh();
        request.setAttribute("jbxxList", jbxxList);
        if (!StringUtil.isNull(xh)) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
        }
        String path = "xlzx_xlwjgy.do?method=xlwjgyAdd";
        request.setAttribute("path", path);
        return mapping.findForward("xlwjgyAdd");
    }


    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="������������ѯ-����Σ����������-����Σ�������ϱ�-�޸�XH:{xh}")
    public ActionForward xlwjgyUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XlwjgyForm myForm = (XlwjgyForm) form;
        XlwjgyForm model = service.getModel(myForm.getId());
        if(model != null){
            BeanUtils.copyProperties(myForm,model);
        }
        String xh = model.getXh();
        request.setAttribute("jbxxList", jbxxList);
        if (!StringUtil.isNull(xh)) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
        }
        return mapping.findForward("xlwjgyUpdate");
    }

    @SystemAuth(url = url)
    public ActionForward xlwjgyView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XlwjgyForm myForm = (XlwjgyForm) form;
        HashMap<String,String> map = service.getInfoById(myForm.getId());
        String xh = map.get("xh");
        request.setAttribute("jbxxList", jbxxList);
        // ����ѧ��������Ϣ
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("model", map);
        return mapping.findForward("xlwjgyView");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward xlwjgySave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XlwjgyForm model = (XlwjgyForm) form;
        boolean result = false;
        User user = getUser(request);
        if("add".equals(model.getType())){
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd HH:mm:ss"));
            result = service.runInsert(model);
        }else{
            result = service.runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="������-����Σ������-����Σ����Ԥ-ɾ��VALUES:{values}")
    public ActionForward xlwjgyDel(ActionMapping mapping, ActionForm form,
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
    
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        XlwjgyForm model = (XlwjgyForm) form;
        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }

}

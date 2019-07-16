package com.zfsoft.xgxt.dtjs.zhcx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:�ճ������ۺϲ�ѯ
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-07-15 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZhcxAction extends SuperAction<ZhcxForm,ZhcxService> {

    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        jbxxList = bdpzService.getJbxxpz("shsjjl");
    }
    public ActionForward rcjyList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZhcxService service = new ZhcxService();
        ZhcxForm model = (ZhcxForm)form;
        if (QUERY.equals(model.getType())){
            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            //��ѯ
            List<HashMap<String,String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_zm(new String[]{"01","02"});
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path","dtjs_rcjy_zhcx.do");
        return mapping.findForward("rcjyList");
    }
    /**
     *�鿴�ճ�������¼
     */
    public ActionForward rcjyView(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZhcxForm model = (ZhcxForm)form;
        ZhcxService service = new ZhcxService();
        if (StringUtils.isNotNull(model.getXh())){
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
            //��������ѧϰ��¼
            List<HashMap<String,String>> llxxList = service.getllxxListByXh(model);
            request.setAttribute("llxxList",llxxList);
            //�������ʵ����¼
            List<HashMap<String,String>> shsjList = service.getshsjListByXh(model);
            request.setAttribute("shsjList",shsjList);
            //����־Ը�����¼
            List<HashMap<String,String>> zyfwList = service.getzyfwListByXh(model);
            request.setAttribute("zyfwList",zyfwList);
        }else {
            throw new Exception();
        }
        // ѧ��������Ϣ��ʾ����
        request.setAttribute("jbxxList", jbxxList);

        return mapping.findForward("rcjyView");
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZhcxForm model = (ZhcxForm)form;
        ZhcxService service = new ZhcxService();
        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(model,user);

        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
}

package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkzbb;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;

/**
 * ����ũҵ��ѧ
 * ѧ����������ܱ���action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:13
 */
public class XsgzqkZbbAction extends SuperAction<XsgzqkZbbForm, XsgzqkZbbService> {

    private XsgzqkZbbService service = new XsgzqkZbbService();
    private static final String url = "rcsw_xsgzqkbb_zbb.do";

    /**
     *  ת���ܱ����б�ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-17 15:23
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward zbbList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);

        XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
        List<HashMap<String, String>> zcList = xsgzzbsqService.getZcList();
        request.setAttribute("zcListSize", zcList.size());

        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("zbbList");
    }

    /**
     *  ��ȡ�ܱ����б�json����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-17 15:25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward getZbbListData(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkZbbForm xsgzqkZbbForm = (XsgzqkZbbForm) form;

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkZbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getPageList(xsgzqkZbbForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     *  ת���ܱ�������ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward zbbAdd(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkZbbForm xsgzqkZbbForm = (XsgzqkZbbForm) form;
        User user = getUser(request);

        xsgzqkZbbForm.setBsr(user.getUserName());
        xsgzqkZbbForm.setBsrmc(user.getRealName());
        xsgzqkZbbForm.setBmdm(user.getUserDep());
        xsgzqkZbbForm.setBsdwmc(user.getUserDepName());

        XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
        List<HashMap<String, String>> zcList = xsgzzbsqService.getZcList();
        // ========= ��ʼ������������ begin =========
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        DateFormat d = DateFormat.getDateInstance();
        for (HashMap<String, String> zcMap : zcList) {
            Date ksrq = d.parse(zcMap.get("ksrq"));
            Date jsrq = d.parse(zcMap.get("jsrq"));
            boolean flag = now.after(ksrq) && now.before(jsrq);
            if(flag){
                xsgzqkZbbForm.setZc(zcMap.get("dm"));
            }
        }
        // ========= ��ʼ������������ end =========
        request.setAttribute("zcList", zcList);

        // ѧ�� ѧ��list
        xsgzqkZbbForm.setXn(Base.currXn);
        xsgzqkZbbForm.setXq(Base.currXq);

        if("xx".equals(user.getUserStatus())){
            List<HashMap<String,String>> xyList = xsgzzbsqService.getXyList();
            request.setAttribute("xyList",xyList);
        }

        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        return mapping.findForward("zbbAdd");
    }

    /**
     *  ת���ܱ����޸�ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zbbEdit(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkZbbForm xsgzqkZbbForm = (XsgzqkZbbForm) form;
        User user = getUser(request);
        HashMap<String,String> zbb = service.getZbbById(xsgzqkZbbForm.getId());

        BeanUtils.copyProperties(xsgzqkZbbForm, zbb);

        XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
        List<HashMap<String, String>> zcList = xsgzzbsqService.getZcList();

        request.setAttribute("zcList", zcList);

        if("xx".equals(user.getUserStatus())){
            List<HashMap<String,String>> xyList = xsgzzbsqService.getXyList();
            request.setAttribute("xyList",xyList);
        }

        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        return mapping.findForward("zbbEdit");
    }

    /**
     *  ת���ܱ���鿴ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zbbView(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkZbbForm xsgzqkZbbForm = (XsgzqkZbbForm) form;
        User user = getUser(request);
        HashMap<String,String> zbb = service.getZbbById(xsgzqkZbbForm.getId());

        BeanUtils.copyProperties(xsgzqkZbbForm, zbb);
        return mapping.findForward("zbbView");
    }

    /**
     *  �ܱ������ӵĵı���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zbbSaveForAdd(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkZbbForm xsgzqkZbbForm = (XsgzqkZbbForm) form;
        //�ظ�����֤
        if(service.isZcRepeat(xsgzqkZbbForm)){
            //�����ظ�
            String messageKey = MessageKey.RCSW_XSGZQKBB_ZBB_ZCREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else{
            //�������ظ�
            boolean result = service.runInsert(xsgzqkZbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  �ܱ����޸ĵı���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zbbSaveForEdit(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkZbbForm xsgzqkZbbForm = (XsgzqkZbbForm) form;
        //�ظ�����֤
        if(service.isZcRepeat(xsgzqkZbbForm)){
            //�����ظ�
            String messageKey = MessageKey.RCSW_XSGZQKBB_ZBB_ZCREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else {
            //�������ظ�
            boolean result = service.runUpdate(xsgzqkZbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  �ܱ�������ɾ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 17:18
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zbbDel(ActionMapping mapping, ActionForm form,
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

    /**
     *  �ܱ���ͨ�õ���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 19:31
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkZbbForm xsgzqkZbbForm = (XsgzqkZbbForm) form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkZbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(xsgzqkZbbForm,user);

        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = xsgzqkZbbForm.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
    
    public ActionForward getJsdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	XsgzqkZbbForm myForm = (XsgzqkZbbForm) form;
		File wordFile = getJsdjWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getJsdjWord(XsgzqkZbbForm myForm,HttpServletRequest request) throws Exception{
		XsgzqkZbbService xsgzqkZbbService = new XsgzqkZbbService();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		String ids = myForm.getId();
		List<HashMap<String,String>> xsbsbList = xsgzqkZbbService.getXxbsbList(ids);
		data.put("bsb", xsbsbList.get(0));
		data.put("xxnr", HtmlUtil.xmlZy(xsbsbList.get(0).get("xxnr")));
		data.put("clqk", HtmlUtil.xmlZy(xsbsbList.get(0).get("clqk")));
		data.put("bz", HtmlUtil.xmlZy(xsbsbList.get(0).get("bz")));
		String a = xsbsbList.get(0).get("zcksjsrq");
		String b = xsbsbList.get(0).get("bsdwmc");
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//rcsw","ahnydx_10364.xml","ѧ����Ϣ���ͱ�"+"["+b+a+"]");
		return file;
	}
	public ActionForward getJsdjTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzqkZbbForm myForm = (XsgzqkZbbForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
 			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setId(values[i]);
				File file = getJsdjWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}

package xsgzgl.szdw.teainfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.jygl.comman.JyglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import common.Globals;

public class TeaInfoAction extends BasicAction {

	
	
	/**
	 * չ�ֽ�ʦ��Ϣ
	 */
	public ActionForward teaInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		String pkValue = request.getParameter("pkValue");
		
		if (StringUtils.isNotNull(pkValue)){
			model.setZgh(pkValue);
		}
		
		Map<String,String> teaInfo = service.getTeaInfo(model);
		
		request.setAttribute("rs", teaInfo);
		request.setAttribute("zwList", service.getZwList());
		request.setAttribute("classList", service.getTeaClass(new String[]{pkValue}));
		
		//============2012.9.7 edit by great luo========================
		String xxdm = Base.xxdm; 
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			FormModleCommon.requestSetList(new String[] { "mz","jg","zzmm","xy","bm" }, request);
		}else{
			FormModleCommon.requestSetList(new String[] { "mz","jg","zzmm","bm" }, request);
		}
		
		
		return mapping.findForward("teaInfo");
	}

	
	
	/**
	 * ��ʦ��Ϣ����
	 */
	public ActionForward saveTeaInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		
		boolean result = service.saveTeaInfo(model);
		
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return teaInfo(mapping,form,request,response);
	}
	
	
	
	
	/**
	 * ��ʦ��Ϣ����
	 * @throws Exception
	 */
	public ActionForward teaManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		String[] colList = model.getColList();
		String viewName = "view_fdyxx";
		
		if (null == colList){
			colList = (String[])session.getAttribute("teaCols");
			colList = null == colList ? new String[]{"zgh","xm","xb","bmmc","zwmc","lxdh","sfyh","sfbb"} : colList;
			
		} else {
			session.setAttribute("teaCols", colList);
		}
		JyglService jyglService = new JyglService();
		String[] topList = jyglService.getColumn(viewName, colList);
		List<HashMap<String,String>> teaList = service.getTeaList(model);
		
		request.setAttribute("rs", teaList);
		request.setAttribute("colList", colList);
		request.setAttribute("topTr", topList);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("cols", service.getColList());
		request.setAttribute("path", "fdyxx.do");
		request.setAttribute("realTable", "fdyxxb");
		request.setAttribute("yhzList", CommonQueryDAO.getYhzForSzdwList());
		request.setAttribute("dwList", CommonQueryDAO.getDwList());
		request.setAttribute("searchTj", model.getSearchModel());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("teaManage");
	}
	
	
	
	/**
	 * ��ְ֤�����Ƿ����
	 */
	public ActionForward checkZgh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		
		boolean isExists = service.checkZgh(model);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(isExists);
		return null;
	}
	
	
	
	/**
	 * ���ӽ�ʦ----��һ������
	 */
	public ActionForward addTea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		//Ajax�ύ����������ת��
		model.setXm(URLDecoder.decode(model.getXm(),"utf-8"));
		
		boolean result = service.addTea(model);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result);
		return null;
	}



	
	/**
	 * ��ʦ��Ƭ�ϴ�
	 */
	public ActionForward uploadTeaPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		// ============= ִ�б������ ============
		String flag = service.saveTeaPic(model);
		// ============= end ============
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
		return null;
	}

	/**
	 * ��ʦ��Ƭ�ϴ�
	 */
	public ActionForward showTeaPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		
		InputStream is = service.getTeaPhotoStream(model.getZgh());
		
		if (is == null){
			ServletContext application = request.getSession().getServletContext();
			is = new FileInputStream(new File(application.getRealPath("/images/type_pic.gif")));
		}
		
		FileUtil.outputFileStream(is, response.getOutputStream());
		return null;
	}


	/**
	 * ɾ����ʦ��Ϣ
	 */
	public ActionForward delTeaInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] pkValue = request.getParameterValues("primarykey_cbv");
		TeaInfoService service = new TeaInfoService();
		
		boolean result = service.delTeaInfo(pkValue);
		
		request.setAttribute("message", result ? DEL_SUCCESS : "ɾ��ʧ�ܣ�������Ҫɾ���Ľ�ʦ�Ƿ���ࡣ");
		return teaManage(mapping, form, request, response);
	}



	
	/**
	 * ��չ�ֶ���Ϣ
	 */
	public ActionForward getOtherInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		
		List<HashMap<String,String>> zdxxList = service.getOtherInfo(model);
		
		JSONArray zdxx = JSONArray.fromObject(zdxxList);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(zdxx);
		
		return null;
	}



	/**
	 * ��ʦ��Ϣҳ�棬��չ�ֶ��������б����ݼ��� 
	 */
	public ActionForward getSelectData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");
		TeaInfoService service = new TeaInfoService();
		List<HashMap<String,String>> dataList =service.getDataList(tableName);
		JSONArray data = JSONArray.fromObject(dataList);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(data);
		return null;
	}



	/**
	 * ��ʦ��Ϣ����
	 */
	public ActionForward expTeaList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		String[] colList = model.getColList();

		if (null == colList) {
			colList = (String[])request.getSession().getAttribute("teaCols");
			colList = null == colList ? new String[] { "zgh", "xm", "xb","zw","bmdm","sfyh","yzbm","xw","lxdh","bmmc","zwmc","zyzz","zzmm","zzmmmc","xl","sfmc","mzmc","csrq","zwrzsj","jsrzsj","qtfgnj","sjdw","bgdh" ,"cz","txdz","grhjqk","gzjl","bz","zc","lxgzsj","xsgzyjfx","jtzz","yddh","dzyx","bgdd","fblw","kyjl","jrgz","jgxs","sg","tz","tc","jb","djsj","szgzsj","bkbyyx","bksxzy","ssbyyx","jg","mz","ssbyzy","bsbyyx","bsbyzy","fgnj","byyx","sxzy","fdyz","sfbb"} : colList;
		}

		String[] topList = new JyglService().getColumn("view_fdyxx", colList);
		List<String[]> teaList = service.getExpTeaList(model, colList);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(teaList, colList, topList, response.getOutputStream());

		return null;
	}



	/**
	 * ͬ����ʦ��Ϣ���û���
	 */
	public ActionForward tbTeaInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TeaInfoForm model = (TeaInfoForm) form;
		TeaInfoService service = new TeaInfoService();
		String[] pkValue = request.getParameterValues("primarykey_cbv");
		
		boolean result = service.tbTeaInfo(model, pkValue);
		
		request.setAttribute("message", result ? "ͬ���ɹ���(��ʼ����Ϊ888888)" : "ͬ��ʧ��");
		return teaManage(mapping, form, request, response);
	}
	
	
	
	
}

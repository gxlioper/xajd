package xgxt.bdsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.MakeQuery;
import xgxt.utils.SearchUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Զ����Action</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: �����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2011-02-25</p>
 */
public class BdszAction extends BasicAction {
	
	
	/**
	 * �Զ����ֶι���
	 * @author qph
	 * @throws Exception
	 */
	public ActionForward bdszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszForm myForm = (BdszForm) form;
		BdszModel model = new BdszModel();
		BdszService service = new BdszService();
		
		String tableName = "ty_bdsz";
		String viewName = "xg_view_ty_bdsz";
		String doType = request.getParameter("doType");
		
		BeanUtils.copyProperties(model, myForm);

		if ("del".equalsIgnoreCase(doType)) {
			
			//ɾ�����������Զ����ֶ���ҵ��ģ����������򷵻ؾ��幦�����ƺͱ����б�
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			HashMap<String,Object> map = service.delZdyZd(pkValues);
			
			request.setAttribute("result", map.get("result"));
			request.setAttribute("wscList", map.get("wscList"));
		}
		
		//��ѯ
		String[] colList = new String[] { "zdid||tabname", "mkmc","gnmc",
								"zdid", "zdmc","zdlxmc", "zdcd","cxxs" };
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(viewName, colList, null);
		ArrayList<String[]> rs = service.getBdszList(viewName, model,colList);
		
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("path", "ty_bdsz.do");
		FormModleCommon.commonRequestSet(request, tableName, tableName, rs,topTr);
		//����ģ�������б����������б�
		request.setAttribute("gnmkList", service.getGnmkList());
		request.setAttribute("gnmcList", service.getGnmcList());
		return mapping.findForward("bdszManage");
	}
	
	
	/**
	 * �Զ����ֶ�ά��
	 * @author qph
	 * @throws Exception
	 */
	public ActionForward bdszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszForm myForm = (BdszForm) form;
		BdszModel model = new BdszModel();
		BdszService service = new BdszService();
		
		String tableName = "ty_bdsz";
		HashMap<String, String> rs = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String tabname = request.getParameter("tabname");
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		//�޸Ļ���ϸ�������ص�������
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			
			String[] colList = new String[] { "bz", "cxxs", "cxxspx", "mkmc",
					"tabname", "zdcd", "zdid", "zdlx", "zdmc", "zdpx", "mkdm","sfbt" };
			
			rs = service.getBdsz(tableName, colList, "zdid||tabname", pkValue);
		}
		
		
		//�����Զ����ֶ��ֶ�
		if ("save".equalsIgnoreCase(doType)) {
			
			//���Ҫ��ӵ��ֶ��������Ƿ���ڣ����ڷ���false,���򷵻�true;
            result = service.checkTableCol(tabname,model.getZdid());
            
            if(result){
            	result = service.saveBdsz(model,pkValue,request);//�����Զ����ֶ�
            	request.setAttribute("result", result);
            	request.setAttribute("message", result ? "����ɹ�" : "����ʧ�ܣ���Ҫ��ӵ��ֶ��Ѵ���");
            }else{
            	request.setAttribute("result", result);
            	request.setAttribute("message", "�Բ�����Ҫ������ֶ�ID���������Ѵ��ڣ���������д");
            }
		}
	
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("doType", doType);
		//�����Զ����ֶΣ������򣩵�optionѡ���б�
		request.setAttribute("opList", service.getOpList(pkValue));
		//����ģ�������б����������б�
		request.setAttribute("gnmkList", service.getGnmkList());
		request.setAttribute("gnmcList", service.getGnmcList());
		return mapping.findForward("bdszUpdata");
	}
	
	
	
	
	/**
	 * ʹ���Զ����ֶ�ҵ��ģ��ĵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BdszService service = new BdszService();
		
		String realTable = request.getParameter("realTable");
		String tableName = request.getParameter("tableName");
		
		/*if (Base.isNull(realTable) || Base.isNull(tableName)) {
			return new ActionForward("/message/error.jsp",false);
		}*/
		
		//��ѯ����
		String[] queryCol =DealString.toGBK(request.getParameter("queryCol")).split("!!");
		String[] likeCol =DealString.toGBK(request.getParameter("likeCol")).split("!!");
		
		//���Զ����ֶε��С�Ӣ����
		HashMap<String,String[]> map = service.getZdyZd(tableName, realTable);
		String[] zdyZdEn = map.get("zdyZdEn");//�Զ����ֶ�
		String[] zdyZdLx = map.get("zdyZdLx");//�Զ����ֶ�����
		String[] colListEn = map.get("colListEn");
		String[] colListCn = map.get("colListCn");
		
		//ƴ�ӹ�������
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQueryForRequest(queryCol,likeCol,request);
		String query = makeQuery.getQueryString();
		
		//����SQL
		String sql = service.getQueryDataSql(realTable, tableName,zdyZdEn,zdyZdLx, Base.isNull(query) ? "" : query);
		
		List<String[]> list = CommonQueryDAO.commonQueryNotFy(tableName, "", makeQuery.getInputList(),colListEn, sql);
		
		//�����EXCEL
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list, colListEn, colListCn,response.getOutputStream());
		
		return mapping.findForward("");
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * �Զ����ֶ�ͨ�����ݵ���
	 * 
	 * @throws Exception
	 */
	public ActionForward zdyDataExport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BdszService service = new BdszService();
		BdszModel model = new BdszModel();
		User user = getUser(request);
		String realTable =request.getParameter("realTable");
		String tableName =request.getParameter("tableName");
		String zdyTable = request.getParameter("zdyTable");
		String[] queryCol =DealString.toGBK(request.getParameter("queryCol")).split("!!");
		//ģ����ѯ�ֶ�
		String[] likeCol =DealString.toGBK(request.getParameter("likeCol")).split("!!");
		service.getDcZd(tableName,realTable,model,zdyTable);
		String query = "";
		String [] inputList = model.getArrZdid();
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQueryForRequest(queryCol,likeCol,request);
		queryCol = makeQuery.getInputList();
		query = makeQuery.getQueryString();
		
		if ("xsh_stglb".equals(realTable)) {
			query+=" and stmc<>'ѧ����'";
		}
		
		String sql = service.getDcSql(tableName,zdyTable,model.getArrZdid(), realTable, query);
		if("py_bdsz_bcnr".equalsIgnoreCase(zdyTable)){//���������Զ���
			//����Ա,���Ӹ���Ա��ѯ��������
			query += service.appendFdysql(request.getParameter("isFdy"),user.getUserName());
			sql = service.getDcSql(tableName,zdyTable,model.getArrZdid(), tableName, query);
		}
		
		List<String[]> list = CommonQueryDAO.commonQueryNotFy(tableName, "", queryCol, model.getArrZdid(), sql);
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list, model.getArrZdid(), inputList,response.getOutputStream());
		
		return mapping.findForward("");
	}
	
}

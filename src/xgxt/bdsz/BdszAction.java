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
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 自定义表单Action</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2011-02-25</p>
 */
public class BdszAction extends BasicAction {
	
	
	/**
	 * 自定义字段管理
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
			
			//删除操作，若自定义字段在业务模块产生数据则返回具体功能名称和表名列表
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			HashMap<String,Object> map = service.delZdyZd(pkValues);
			
			request.setAttribute("result", map.get("result"));
			request.setAttribute("wscList", map.get("wscList"));
		}
		
		//查询
		String[] colList = new String[] { "zdid||tabname", "mkmc","gnmc",
								"zdid", "zdmc","zdlxmc", "zdcd","cxxs" };
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(viewName, colList, null);
		ArrayList<String[]> rs = service.getBdszList(viewName, model,colList);
		
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("path", "ty_bdsz.do");
		FormModleCommon.commonRequestSet(request, tableName, tableName, rs,topTr);
		//加载模块名称列表、功能名称列表
		request.setAttribute("gnmkList", service.getGnmkList());
		request.setAttribute("gnmcList", service.getGnmcList());
		return mapping.findForward("bdszManage");
	}
	
	
	/**
	 * 自定义字段维护
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

		//修改或详细操作加载单条数据
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			
			String[] colList = new String[] { "bz", "cxxs", "cxxspx", "mkmc",
					"tabname", "zdcd", "zdid", "zdlx", "zdmc", "zdpx", "mkdm","sfbt" };
			
			rs = service.getBdsz(tableName, colList, "zdid||tabname", pkValue);
		}
		
		
		//保存自定义字段字段
		if ("save".equalsIgnoreCase(doType)) {
			
			//检查要添加的字段在主表是否存在，存在返回false,否则返回true;
            result = service.checkTableCol(tabname,model.getZdid());
            
            if(result){
            	result = service.saveBdsz(model,pkValue,request);//保存自定义字段
            	request.setAttribute("result", result);
            	request.setAttribute("message", result ? "保存成功" : "保存失败，您要添加的字段已存在");
            }else{
            	request.setAttribute("result", result);
            	request.setAttribute("message", "对不起，您要保存的字段ID在主表中已存在，请重新填写");
            }
		}
	
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("doType", doType);
		//加载自定义字段（下拉框）的option选项列表
		request.setAttribute("opList", service.getOpList(pkValue));
		//加载模块名称列表、功能名称列表
		request.setAttribute("gnmkList", service.getGnmkList());
		request.setAttribute("gnmcList", service.getGnmcList());
		return mapping.findForward("bdszUpdata");
	}
	
	
	
	
	/**
	 * 使用自定义字段业务模块的导出
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
		
		//查询条件
		String[] queryCol =DealString.toGBK(request.getParameter("queryCol")).split("!!");
		String[] likeCol =DealString.toGBK(request.getParameter("likeCol")).split("!!");
		
		//含自定义字段的中、英文列
		HashMap<String,String[]> map = service.getZdyZd(tableName, realTable);
		String[] zdyZdEn = map.get("zdyZdEn");//自定义字段
		String[] zdyZdLx = map.get("zdyZdLx");//自定义字段类型
		String[] colListEn = map.get("colListEn");
		String[] colListCn = map.get("colListCn");
		
		//拼接过滤条件
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQueryForRequest(queryCol,likeCol,request);
		String query = makeQuery.getQueryString();
		
		//导出SQL
		String sql = service.getQueryDataSql(realTable, tableName,zdyZdEn,zdyZdLx, Base.isNull(query) ? "" : query);
		
		List<String[]> list = CommonQueryDAO.commonQueryNotFy(tableName, "", makeQuery.getInputList(),colListEn, sql);
		
		//输出到EXCEL
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list, colListEn, colListCn,response.getOutputStream());
		
		return mapping.findForward("");
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 自定义字段通用数据导出
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
		//模糊查询字段
		String[] likeCol =DealString.toGBK(request.getParameter("likeCol")).split("!!");
		service.getDcZd(tableName,realTable,model,zdyTable);
		String query = "";
		String [] inputList = model.getArrZdid();
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQueryForRequest(queryCol,likeCol,request);
		queryCol = makeQuery.getInputList();
		query = makeQuery.getQueryString();
		
		if ("xsh_stglb".equals(realTable)) {
			query+=" and stmc<>'学生会'";
		}
		
		String sql = service.getDcSql(tableName,zdyTable,model.getArrZdid(), realTable, query);
		if("py_bdsz_bcnr".equalsIgnoreCase(zdyTable)){//评奖评优自定义
			//辅导员,增加辅导员查询数据条件
			query += service.appendFdysql(request.getParameter("isFdy"),user.getUserName());
			sql = service.getDcSql(tableName,zdyTable,model.getArrZdid(), tableName, query);
		}
		
		List<String[]> list = CommonQueryDAO.commonQueryNotFy(tableName, "", queryCol, model.getArrZdid(), sql);
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list, model.getArrZdid(), inputList,response.getOutputStream());
		
		return mapping.findForward("");
	}
	
}

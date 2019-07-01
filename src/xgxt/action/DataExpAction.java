package xgxt.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.Excel2Oracle;
import xgxt.bdsz.BdszModel;
import xgxt.bdsz.BdszService;
import xgxt.daoActionLogic.StandardOperation;

public class DataExpAction extends DispatchAction{
	
	public ActionForward stencil(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 数据查询		
		String xxdm = StandardOperation.getXxdm();
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		
		String zdyBcnrTable = getTable().get(request.getParameter("dzyDdTab"));
		String sql = "";
		
		response.setContentType("application/vnd.ms-excel");
		
		if (Base.isNull(zdyBcnrTable)) {
			sql = Excel2Oracle.getSqlColumn(xxdm,realTable);	
			Excel2Oracle.exportDataStencil(sql, realTable, response .getOutputStream());
		} else {
			BdszModel model = new BdszModel();
			BdszService service = new BdszService();
			service.getDcZd(realTable,realTable,model,zdyBcnrTable);
			
			sql = service.getDcSql(tableName,zdyBcnrTable,model.getArrZdid(), realTable, " where 1=2");
			Excel2Oracle.exportDataIn(sql,tableName, new String[] {},model.getArrZdmc(),response.getOutputStream()," ");
		}
						
		return mapping.findForward("");
	}
 
	
	HashMap<String,String> getTable(){
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("ty_bdsz", "ty_bdsz_bcnr");//通用
		map.put("py_bdszb", "py_bdsz_bcnr");//评奖模块	
		
		return map;
	}
}

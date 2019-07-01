package xgxt.xljk.zgmsxy.service;

import xgxt.DAO.DAO;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;
import xgxt.xszz.ynys.XszzYnysActionForm;

public class XljkZgmsxySerivce {

	private static XljkZgmsxySerivce service = new XljkZgmsxySerivce();
	
	private XljkZgmsxySerivce() {
	}

//	private static final String[] nullArray = {};

	private static DAO dao = new DAO();
	
	public static XljkZgmsxySerivce getInstance() {
		return service;
	}
	
	//save or update
	public boolean saveCommonInfo(XszzYnysActionForm myForm, String realTable,
			String pk) throws Exception {
		boolean flag = true;
		String value = myForm.getNd() + myForm.getXh(); 
		String[] columns = FormUtils.chgArrayElem2LowerCase(dao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		if (FormUtils.haveOneRecord(realTable, pk, value)) {
			flag = dao.runUpdate(SQL_Util.getDelSqlByPKParam(realTable, pk),
					new String[] { value });
		}
		if (flag) {
			flag = dao.runUpdate(FormUtils.getInsertSql(realTable, columns),
					FormUtils.getProperties(columns, myForm));
		}
		return flag;
	}
	
	
}

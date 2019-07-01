package xgxt.studentInfo.gdgydx;

import xgxt.form.CommanForm;

public class XsxxGdgydxService {
	XsxxGdgydxDAO dao = new XsxxGdgydxDAO();
	
	/**
	 * 同步数据
	 * @param CommanForm model
	 * @return boolean
	 * */
	public boolean sjtb(CommanForm model){
		String whereSql = "";
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		if(nj != null && !"".equalsIgnoreCase(nj)){
			whereSql += " and a.dqszj = '" + nj + "'";
		}
		if(xydm != null && !"".equalsIgnoreCase(xydm)){
			whereSql += " and b.bmdm = '" + xydm + "'";
		}
		if(zydm != null && !"".equalsIgnoreCase(zydm)){
			whereSql += " and b.zydm = '" + zydm + "'";
		}
		if(bjdm != null && !"".equalsIgnoreCase(bjdm)){
			whereSql += " and b.bjdm = '" + bjdm + "'";
		}
		return dao.sjtb(whereSql);
	}
	
	/**
	 * 检查连接是否可用
	 * */
	public void connectUseable() throws Exception{
		String num = "";
		String sql = "select count(*)num from xsjbxxb@dblink_jw";
		num = dao.getOneRs(sql, new String[]{}, "num");
		if(num==null || "".equalsIgnoreCase(num)){
			throw new Exception();
		}
	}
}

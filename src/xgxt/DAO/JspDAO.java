package xgxt.DAO;

import xgxt.action.Base;

public class JspDAO {
	public  String getBmmcByDmForJsp(String dm) {
		DAO dao = DAO.getInstance();
		String sql = "select bmmc from zxbz_xxbmdm where bmdm = ?";
		String bmmc = dao.getOneRs(sql, new String[] {dm}, "bmmc" );
		bmmc = Base.isNull(bmmc)?"无所属部门":bmmc;
		return bmmc;
	}
}

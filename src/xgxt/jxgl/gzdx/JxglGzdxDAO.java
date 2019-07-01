package xgxt.jxgl.gzdx;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.jxgl.JxglTyDAO;
import xgxt.jxgl.JxglTyForm;

public class JxglGzdxDAO extends JxglTyDAO {

	/**
	 * ����⻺ѵѧ�����������Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuLdInfo(JxglTyForm model) {
		
		DAO dao = DAO.getInstance();
		
		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = model.getXn();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select bzmc ldmc, (select c.xm from view_fdyxx c where a.jsdm = c.zgh) ");
		sql.append("jsxm from jxbzdmb a where exists (select 1 from jxgl_jxmdb b ");
		sql.append("where a.bzdm = b.ldbh and b.xh = ? and b.xn = ? )");
		
		String[] inputValue = new String[]{xh,xn}; 
		String[] outputValue = new String[]{"ldmc","jsxm"}; 
		
		HashMap<String, String> map = dao.getMap(sql.toString(), inputValue, outputValue);
		
		return map;
	}
	

	/**
	 * ��ü�����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public String getJgInfo(String jg) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		if (!Base.isNull(jg)) {
			
			String[] arrDq = jg.split("/");
			String shen = (arrDq.length >= 1) ? arrDq[0] : "";
			String jgshi = (arrDq.length >= 2) ? arrDq[1] : "";
			String xian =  (arrDq.length >= 3) ? arrDq[2] : "";
			
			StringBuffer sql = new StringBuffer();
			sql.append("select qxmc from dmk_qx where qxdm = ? ");
			sql.append("union all ");
			sql.append("select qxmc from dmk_qx where qxdm = ? ");
			sql.append("union all ");
			sql.append("select qxmc from dmk_qx where qxdm = ? ");
			
			String[] inputValue = new String[]{shen,jgshi,xian}; 
			String outputValue = "qxmc"; 
			
			String[] arrJg = dao.getRs(sql.toString(), inputValue, outputValue);
		
			jg = "";
			for (String oneJg : arrJg) {
				jg += oneJg;
			}

		}
		
		return jg;
	}
}

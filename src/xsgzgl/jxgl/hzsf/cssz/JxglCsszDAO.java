package xsgzgl.jxgl.hzsf.cssz;

import java.util.HashMap;

import xgxt.DAO.DAO;
/**
 * ��ѵ����-��������-��������
 * @author yeyipin
 * @since 2012.7.26
 */
public class JxglCsszDAO {
	DAO dao = DAO.getInstance();
	/**
	 * ��ò�������
	 * @return
	 */
	public HashMap<String,String> getCssz() {
		String sql = "select lx from xg_jxgl_hzsf_csszb where rownum = 1";
		return dao.getMap(sql, new String[]{}, new String[]{"lx"});
	}
	/**
	 * �������ñ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCssz(JxglCsszForm model) throws Exception {
		boolean flag = false;
		String sql = "delete from xg_jxgl_hzsf_csszb";
		flag = dao.runUpdate(sql, new String[]{});
		if(flag){
			sql = "insert into xg_jxgl_hzsf_csszb(lx) values(?)";
			String[] input = {model.getLx()};
			flag = dao.runUpdate(sql, input);
		}
		return flag;
	}

}

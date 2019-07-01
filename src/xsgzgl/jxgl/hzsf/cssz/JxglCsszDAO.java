package xsgzgl.jxgl.hzsf.cssz;

import java.util.HashMap;

import xgxt.DAO.DAO;
/**
 * 军训管理-基础设置-参数设置
 * @author yeyipin
 * @since 2012.7.26
 */
public class JxglCsszDAO {
	DAO dao = DAO.getInstance();
	/**
	 * 获得参数设置
	 * @return
	 */
	public HashMap<String,String> getCssz() {
		String sql = "select lx from xg_jxgl_hzsf_csszb where rownum = 1";
		return dao.getMap(sql, new String[]{}, new String[]{"lx"});
	}
	/**
	 * 参数设置保存
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

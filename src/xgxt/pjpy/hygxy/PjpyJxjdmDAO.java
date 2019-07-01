
package xgxt.pjpy.hygxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院评奖评优奖学金代码DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyJxjdmDAO extends PjpyHygxyDAO{

	DAO dao = null;
	
	/**
	 * 奖学金类别
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjlbList() throws Exception {
		dao = DAO.getInstance();
		return dao.getList("select distinct jxjlbdm,jxjlbmc from jxjlbdmb",
				new String[] {}, new String[] { "jxjlbdm", "jxjlbmc" });
	}
	
	/**
	 * 奖学金代码保存
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjdmSave(PjpyJxjdmModel model, HttpServletRequest request) throws Exception {
		dao = DAO.getInstance();
		StandardOperation.delete("jxjdmb", "jxjdm", model.getJxjdm(), request);
		return StandardOperation.insert("jxjdmb", new String[] { "jxjdm",
				"jxjmc", "jxjlb", "jxjjb", "jlje", "szjdbz" }, new String[] {
				model.getJxjdm(), DealString.toGBK(model.getJxjmc()),
				DealString.toGBK(model.getJxjlb()), model.getJxjjb(),
				model.getJlje(), model.getSzjdbz() }, request);
	}
	
	/**
	 * 奖学金修改显示
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewJxjdm(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz from jxjdmb where jxjdm=?",
						new String[] { pkValue });
	}
	
	public boolean updateJxjdm(PjpyJxjdmModel model, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		bFlag = StandardOperation.delete("jxjdmb", "jxjdm", model.getJxjdm(), request);
		if (bFlag) {
			return StandardOperation.insert("jxjdmb", new String[] { "jxjdm",
					"jxjmc", "jxjlb", "jxjjb", "jlje", "szjdbz" }, new String[] {
					model.getJxjdm(), DealString.toGBK(model.getJxjmc()),
					DealString.toGBK(model.getJxjlb()), model.getJxjjb(),
					model.getJlje(), model.getSzjdbz() }, request);
		} else {
			return false;
		}
	}
}


package xgxt.pjpy.hygxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ�������Ž�ѧ��������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyHygxyJxjlbdmDAO {

	DAO dao = null;
	
	/**
	 * ���潱ѧ��������
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjlbdm(JxjlbdmModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.insert("jxjlbdmb", new String[] { "jxjlbdm",
				"jxjlbmc", "jxjjd" }, new String[] { model.getJxjlbdm(),
				DealString.toGBK(model.getJxjlbmc()), model.getJxjjd() },
				request);
	}
	
	/**
	 * �޸Ľ�ѧ��������
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjlbdm(JxjlbdmModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.update("jxjlbdmb", new String[] { "jxjlbmc",
				"jxjjd" }, new String[] { DealString.toGBK(model.getJxjlbmc()),
				model.getJxjjd() }, "jxjlbdm", model.getJxjlbdm(), request);
	}
	
	/**
	 * ��ѧ���������޸���ʾ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjlbdmDetails(String pkValue)
			throws Exception {
		dao = DAO.getInstance();
		return dao.getMap(
				"select jxjlbdm,jxjlbmc,jxjjd from jxjlbdmb where jxjlbdm=?",
				new String[] { pkValue }, new String[] { "jxjjd", "jxjlbdm",
						"jxjlbmc" });
	}
}

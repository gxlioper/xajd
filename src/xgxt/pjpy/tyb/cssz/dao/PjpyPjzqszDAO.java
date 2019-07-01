package xgxt.pjpy.tyb.cssz.dao;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.pjpy.tyb.cssz.model.PjpyPjzqszModel;

public class PjpyPjzqszDAO {

	DAO dao = DAO.getInstance();
	
	/**
	 * ��������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePjzqxx(PjpyPjzqszModel model) throws Exception{
		return dao.runUpdate(
				"insert into pjpy_pjzqb (xn,nd,xq,zcxn,zcnd,zcxq) values (?,?,?,?,?,?)",
				new String[] { model.getXnbz(), model.getNdbz(),
						model.getXqbz(), model.getZcxn(), model.getZcnd(),model.getZcxq() });
	}
	
	/**
	 * ɾ������������Ϣ
	 * @return
	 * @throws Exception
	 */
	public boolean delPjzqxx() throws Exception {
		return dao.runUpdate("delete from pjpy_pjzqb", new String[]{});
	}
	
	/**
	 * �޸Ľ�ѧ������ʱ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjpjsj(PjpyPjzqszModel model) throws Exception{
		return dao.runUpdate("update xtszb set jxjsqxn=?,jxjsqxq=?,jxjsqnd=?",
				new String[] { model.getJxjsqxn(),
						model.getJxjsqxq(), model.getJxjsqnd() });
	}
	
	/**
	 * ��ѯ����ʱ��,����
	 * @return
	 */
	public HashMap<String, String> queryPjpysj() {
		HashMap<String, String> rs = dao.getMapNotOut("select jxjsqxn,jxjsqxq,jxjsqnd from xtszb ",
													  new String[]{});
		rs.putAll(dao.getMapNotOut("select xn,nd,xq,zcxn,zcnd,zcxq from pjpy_pjzqb", new String[]{}));
		return rs;
	}
	
	public HashMap<String, String> queryXtszbxx() {
		return dao.getMapNotOut("select jxjsqxn,jxjsqxq,jxjsqnd,(select xqmc from xqdzb b where a.jxjsqxq=b.xqdm) jxjxqmc from xtszb a", new String[]{});
	}
	
}

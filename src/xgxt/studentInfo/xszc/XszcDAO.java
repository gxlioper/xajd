package xgxt.studentInfo.xszc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XszcDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * ��ȡ�����
	 * @param sql ��ѯ���
	 * @param in ��������
	 * @param out �������
	 * @return
	 */
	public List<String[]> getRs(String sql,String[] in,String[] out){
		return dao.rsToVator(sql, in, out);
	}
	
	/**
	 * ��ȡ��ѯ�����ͷ��Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(){
		String[] en = new String[]{"xn","xq","zczt","fdysh"};
		String[] cn = new String[]{"ѧ��","ѧ��","ע��״̬","���״̬"};
		return dao.arrayToList(en,cn);
	}
	
	/**
	 * ����ִ�����
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public boolean zc(String[] sqlArr) throws SQLException{
		return dao.checkBatch(dao.runBatch(sqlArr));
	}
	
	/**
	 * ������������ѧ��ע����Ϣ
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 */
	public HashMap<String, String> getXszcInfo(String pk){
		return dao.getMapNotOut("select a.*,a.xn||a.xq||a.xh pk from xg_view_xsxx_zcqkb a where xn||xq||xh=?", new String[]{pk});
	}
	
	/**
	 * ����������ȡѧ���Ѿ�����ע����Ϣ
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 */
	public String getCont(String pk){
		return dao.getOneRs("select count(1) cont from xg_xsxx_zcqkb where xn||xq||xh=?", new String[]{pk}, "cont");
	}
	
	/**
	 * ɾ��ѧ��ע����Ϣ
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 * @throws Exception
	 */
	public boolean del(String pk) throws Exception{
		return dao.runUpdate("delete from xg_xsxx_zcqkb where xn||xq||xh=?", new String[]{pk});
	}
	
	/**
	 * �����б�ѡ��ά��
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("shzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ���", "ͨ��","��ͨ��" };
			mc = new String[] { "δ���", "ͨ��" ,"��ͨ��"};
		} else if("zczt".equalsIgnoreCase(lx)){
			dm = new String[] { "��ע��", "δע��","�ݻ�ע��" };
			mc = new String[] { "��ע��", "δע��","�ݻ�ע��" };
		}
		return dao.arrayToList(dm, mc);
	}
	

	/**
	 * ѧ���б�(�人��ҵ����ѧԺ)
	 * @return
	 */
	public List<HashMap<String, String>> getXq() {
//		String sql = "select xqdm,xqmc from xqdzb where xqjb<3";
//		return dao.getList(sql, new String[] {}, new String[] {"xqdm","xqmc"});
		String[] dm = new String[]{"��ѧ��","��ѧ��"};
		String[] mc = new String[]{"��ѧ��","��ѧ��"};
		return dao.arrayToList(dm, mc);
	}
}

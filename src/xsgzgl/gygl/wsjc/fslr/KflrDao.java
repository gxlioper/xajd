/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����03:36:55 
 */  
package xsgzgl.gygl.wsjc.fslr;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �۷�¼��dao(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-6-1 ����03:36:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KflrDao extends SuperDAOImpl<KflrForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KflrForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KflrForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
	}
	
	/** 
	 * @����:��ȡ���ҿ۷���ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-1 ����03:39:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKfmx(KflrForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.kf from (select dh,mk,nr,pf,? jcrcid,? lddm,? qsh from xg_gygl_new_wsjc_pfbzb)a");
		sql.append(" left join (select * from xg_gygl_new_wsjc_qskfb b where b.jcrcid = ? and b.lddm = ? and b.qsh = ?)b");
		sql.append(" on a.jcrcid = b.jcrcid and a.lddm = b.lddm and a.qsh = b.qsh and a.dh = b.kfdh order by to_number(dh) asc");
		return dao.getListNotOut(sql.toString(), new String[]{t.getJcrcid(),t.getLddm(),t.getQsh(),t.getJcrcid(),t.getLddm(),t.getQsh()});
	}
	
	/**
	 * @throws SQLException  
	 * @����:��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-1 ����05:01:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plInsert(KflrForm t) throws SQLException{
		boolean flg = true;
		String sql = "insert into xg_gygl_new_wsjc_qskfb values(?,?,?,?,?)";
		String[] str = t.getKfArr();
		List<String[]> list = new ArrayList<String[]>();
		for(String strr : str){
			String[] strrr = strr.split("_");
			list.add(strrr);
		}
		int[] res = dao.runBatch(sql, list);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/** 
	 * @����:����ɾ��(������һ�仰�����������������)
	 * @���ߣ�liujun[���ţ�1282]
	 * @���ڣ�2017-6-2 ����10:23:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plSc(KflrForm t) throws SQLException{
		boolean flg = true;
		String sql = "delete from xg_gygl_new_wsjc_qskfb where jcrcid = ? and lddm = ? and qsh = ? and kfdh = ?";
		String[] str = t.getDelArr();
		List<String[]> list = new ArrayList<String[]>();
		for(String strr : str){
			String[] strrr = strr.split("_");
			list.add(strrr);
		}
		int[] res = dao.runBatch(sql, list);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/**
	 * @throws SQLException  
	 * @����:ɾ���۷���ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-5 ����11:19:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public boolean scKfmx(String[] jcrcid) throws SQLException {
		String sql = "delete from xg_gygl_new_wsjc_qskfb where jcrcid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : jcrcid){
			list.add(new String[]{str});
		}
		return dao.runBatchNotCommit(sql, list);
	}
	
	/**
	 * @throws SQLException  
	 * @����:ɾ������¼��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-8 ����10:09:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcrcid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plScFslr(String[] jcrcid) throws SQLException {
		String sql = "delete from xg_gygl_new_wsjc_qsfsb where jcrcid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : jcrcid){
			list.add(new String[]{str});
		}
		return dao.runBatchNotCommit(sql, list);
	}
	
	/** 
	 * @����:����ɾ������ճ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-8 ����10:38:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcrcid
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plScJcrc(String[] jcrcid) throws SQLException {
		String sql = "delete from xg_gygl_new_wsjc_jcrcb where guid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : jcrcid){
			list.add(new String[]{str});
		}
		return dao.runBatchNotCommit(sql, list);
	}

	
	/**
	 * @throws SQLException  
	 * @����:�������¿۷�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-5 ����01:46:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plUpdateKfyj(String[] str) throws SQLException{
		boolean flg = true;
		String sql = "update xg_gygl_new_wsjc_qsfsb set kfyj = ? where guid = ? and lddm = ? and qsh = ?";
		List<String[]> params = new ArrayList<String[]>();
		for(String strr : str){
			if(null == strr){
				continue;
			}
			String[] strrr = strr.split("_");
			params.add(strrr);
		}
		if(params.size()<1){
			return true;
		}
		int[] res = dao.runBatch(sql, params);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-5 ����05:01:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public void scFslr(KflrForm t) throws Exception{
		String sql = "delete from xg_gygl_new_wsjc_qsfsb where guid = ? and lddm = ? and qsh = ?";
		dao.runDelete(sql, new String[]{t.getJcrcid(),t.getLddm(),t.getQsh()});
	}
	
	/** 
	 * @����:�Ƿ�Ϊ�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-6 ����10:11:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isBcfs(KflrForm t){
		String sql = "select count(1) num from xg_gygl_new_wsjc_qsfsb where guid = ? and lddm = ? and qsh = ?";
		String num = dao.getOneRs(sql, new String[]{t.getJcrcid(),t.getLddm(),t.getQsh()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	/**
	 * @throws SQLException  
	 * @����:����ɾ�������۷�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-19 ����07:18:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	public void plScFskf(List<String[]> list) throws SQLException{		
		String sql = "delete from xg_gygl_new_wsjc_qskfb where jcrcid = ? and lddm = ? and qsh = ?";
		dao.runBatch(sql, list);
	}

}

package com.zfsoft.xgxt.jskp.lxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;

public class LxshDao extends SuperDAOImpl<LxshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(LxshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(LxshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select t.*,");
		sql.append(" t2.xm fzrxm,");
		sql.append(" t3.bmmc,");
		sql.append(" t4.xmlbmc,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.gwid,t5.shr,t5.shyj,t7.mc || '[' ||decode(t5.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t7.gwz,");
		sql.append(" row_number() over(partition by t.sqid order by t5.shsj desc) rn ");
		sql.append(" from xg_jskp_xmsqb t");
		sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) t2");
		sql.append(" on t.fzr = t2.yhm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.zdbm = t3.bmdm");
		sql.append(" left join xg_jskp_xmlbb t4");
		sql.append(" on t.xmlb = t4.xmlbdm");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.sqid = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7");
		sql.append(" on t5.gwid = t7.id ");
		sql.append(" where t.zdbm = '"+user.getUserDep()+"' and t6.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and t5.shzt<>0 and  t5.shzt<>4");
		}else{
			sql.append(" and (t5.shzt=0  or t5.shzt = 4)");
		}
		/*����б���ʾ���������롢������˹���������--���Ի��ж�*/
		sql.append(" and t.sjly <> 'NO' ");
		sql.append("  order by lxsj desc) where 1=1 and rn =1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(LxshForm.class);
		this.setKey("sqid");
		this.setTableName("xg_jskp_xmsqb");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ������Ŀ�걨����״̬
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-18 ����05:58:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXmsbLxzt(String xmid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_jskp_xmsbb t set t.lxzt = '1' where t.xmid = ? ");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{xmid});
	}
	
	/**
	 * 
	 * @����: ������Ŀ���״̬
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����11:45:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXmsbZt(String xmid,String lxzt) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("  update xg_jskp_xmsbb t set t.lxzt =  ? where t.xmid = ?");
		return dao.runUpdate(sql.toString(), new String[]{lxzt,xmid});
	}
	
	/**
	 * 
	 * @����:��֤�Ƿ���δ�ύ�ļ�¼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-21 ����02:06:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isStuSbTj(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_jskp_xmsbb where shzt != '0' and shzt != '3' and xmid = ?");
		String rs = dao.getOneRs(sql.toString(),new String[]{sqid},"cnt");
		return "0".equals(rs)?false:true;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-7 ����07:24:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSbSplc(String xmid,String splc) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_jskp_xmsbb set splcid = ? where xmid = ?");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{splc,xmid});
	}
	
	/**
	 * 
	 * @����: ��ȡ��һ������ֶ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-18 ����04:51:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLastshzd(String ywid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * from xg_xtwh_shztb where ywid = ? and shzt = '1' order by shsj desc) where rownum =1");
		return dao.getMapNotOut(sql.toString(), new String[]{ywid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �ύѧ�������¼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-21 ����09:06:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitSqjl(String xmid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_jskp_xmsbb set shzt = '5' where xmid = ?");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{xmid});
	}
	
}

package com.zfsoft.xgxt.jskp.sbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;

public class JskpXmsbjgDao extends SuperDAOImpl<JskpXmsbjgForm> {
	
	private static final String XN_Yf = "09";	//�л�ѧ����·�

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		String sfsh = new CsszDao().getSfsh();
		if("0".equals(sfsh)){
			sql.append("select * from (");
			sql.append("select t1.jgid,t1.fjid,t1.xh,t2.xm,t2.xb,t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc, ");
			sql.append("t3.xmmc,t3.zdbm,t6.bmmc zdbmmc,t3.xmlb,t4.xmlbmc,t1.hjsj,t3.fzr,t5.xm fzrxm,t3.fzrlxfs, ");
			sql.append("t3.zdls,t3.zdlslxfs,t3.lxly,t1.fs,t3.lxxn xn,t1.sbsj,t3.lxsj,'zlx' xmdl,'������' xmdlmc,''dxq ");
			sql.append("from xg_jskp_xmsbjgb t1 ");
			sql.append("left join view_xsxxb t2 on t1.xh = t2.xh ");
			sql.append("left join xg_jskp_xmsqb t3 on t1.xmid = t3.sqid ");
			sql.append("left join xg_jskp_xmlbb t4 on t3.xmlb = t4.xmlbdm ");
			sql.append("left join (select yhm yhm, xm from yhb union all select xh yhm, xm from xsxxb) t5 on t3.fzr = t5.yhm ");
			sql.append("left join zxbz_xxbmdm t6 on t3.zdbm = t6.bmdm");
			sql.append(" ) t where 1=1 ");
		}else{
			sql.append("select * from (");
			sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xb, ");
			sql.append("t3.xmmc,t3.zdbm,t3.xmlb,t3.zdls,decode(t3.xmdl, 'gdx', '�̶���', '������') xmdlmc,t3.xmdl,t4.xm fzrxm ");
			sql.append("from xg_jskp_xmsbjgb t1 left join view_xsxxb t2 on t1.xh = t2.xh ");
			sql.append("left join xg_jskp_xmjgb t3 on t1.xmid = t3.xmid left join xg_jskp_xmlbb t4 on t3.xmlb = t4.xmlbdm ");
			sql.append(" left join yhb t4 on t3.fzr=t4.yhm ) t where 1 = 1 ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}


	
	public JskpXmsbjgForm getModel(JskpXmsbjgForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.xmmc  from xg_jskp_xmsbjgb t1 left join xg_jskp_xmjgb t2 on t1.xmid = t2.xmid");
		sql.append(" where t1.jgid=? ");
		return super.getModel(sql.toString(),new String[]{model.getJgid()});
	}
	/**
	 * 
	 * @����:ͨ������idɾ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����03:29:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delSbjgById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_jskp_xmsbjgb where sqid=? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(JskpXmsbjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_jskp_xmsbjgb");

	}

	
	/** 
	 * @����:��ȡ�����˵�ѧ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-8-23 ����02:33:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSingleSummary(String xh) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select xn,xmlbmc,sum(fs) fs from ( ");
		sql.append("select c.xmlbmc,a.fs,case when to_char(to_date(hjsj,'yyyy-mm-dd'),'mm') >= "+XN_Yf);
		sql.append(" then to_char(to_date(hjsj,'yyyy-mm-dd'),'yyyy')||'-'||to_char(to_number(to_char(to_date(hjsj,'yyyy-mm-dd'),'yyyy'))+1) ");
		sql.append("else to_char(to_number(to_char(to_date(hjsj,'yyyy-mm-dd'),'yyyy'))-1)||'-'||to_char(to_date(hjsj,'yyyy-mm-dd'),'yyyy') end xn ");
		if("0".equals(new CsszDao().getSfsh())){
			sql.append(" from xg_jskp_xmsbjgb a left join xg_jskp_xmsqb b on a.xmid = b.sqid ");
		}else{
			sql.append(" from xg_jskp_xmsbjgb a left join xg_jskp_xmjgb b on a.xmid = b.xmid ");
		}
		sql.append(" left join xg_jskp_xmlbb c on b.xmlb=c.xmlbdm ");
		sql.append("where a.xh = ? ) group by xn,xmlbmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @����: ����������Ϊ0ʱ������鿴��������д
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-22 ����04:40:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXxckForJgid (String jgid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.xmmc,t1.fs,t1.fjid,t2.lxsj,t2.lxly from xg_jskp_xmsbjgb t1 left join xg_jskp_xmsqb t2 on t1.xmid = t2.sqid where t1.jgid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{jgid});
	}
	
	/**
	 * @����: ��ʵ��������ͳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-27 ����02:30:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param SearchXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDateForSearchXn (JskpXmsbjgForm t, User user, String SearchXn) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "f", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select e.xn,e.xh,e.xm,e.zf,e.nj,e.xymc,e.zymc,e.bjmc,e.zdls,e.fzrxm,e.xmmc, ");
		sql.append("e.cxzf, ");/*��Ŀ���Ϊ�����´�ҵ�����ܷ֡�ѧԺ������רҵ�������༶����*/
		sql.append("rank() over(partition by e.xymc, e.xn order by e.cxzf desc) cxzfxypm, ");
		sql.append("rank() over(partition by e.zymc, e.xn order by e.cxzf desc) cxzfzypm, ");
		sql.append("rank() over(partition by e.bjmc, e.xn order by e.cxzf desc) cxzfbjpm, ");
		sql.append("e.dwzf, ");/*��Ŀ���Ϊ�����⽻�������ܷ֡�ѧԺ������רҵ�������༶����*/
		sql.append("rank() over(partition by e.xymc, e.xn order by e.dwzf desc) dwzfxypm, ");
		sql.append("rank() over(partition by e.zymc, e.xn order by e.dwzf desc) dwzfzypm, ");
		sql.append("rank() over(partition by e.bjmc, e.xn order by e.dwzf desc) dwzfbjpm, ");
		sql.append("e.gyzf, ");/*��Ŀ���Ϊ��������񡿵��ܷ֡�ѧԺ������רҵ�������༶����*/
		sql.append("rank() over(partition by e.xymc, e.xn order by e.gyzf desc) gyzfxypm, ");
		sql.append("rank() over(partition by e.zymc, e.xn order by e.gyzf desc) gyzfzypm, ");
		sql.append("rank() over(partition by e.bjmc, e.xn order by e.gyzf desc) gyzfbjpm, ");
		sql.append("e.shzf, ");/*��Ŀ���Ϊ����Ṥ�������ܷ֡�ѧԺ������רҵ�������༶����*/
		sql.append("rank() over(partition by e.xymc, e.xn order by e.shzf desc) shzfxypm, ");
		sql.append("rank() over(partition by e.zymc, e.xn order by e.shzf desc) shzfzypm, ");
		sql.append("rank() over(partition by e.bjmc, e.xn order by e.shzf desc) shzfbjpm, ");
		sql.append("e.wtzf, ");/*��Ŀ���Ϊ�����������ܷ֡�ѧԺ������רҵ�������༶����*/
		sql.append("rank() over(partition by e.xymc, e.xn order by e.wtzf desc) wtzfxypm, ");
		sql.append("rank() over(partition by e.zymc, e.xn order by e.wtzf desc) wtzfzypm, ");
		sql.append("rank() over(partition by e.bjmc, e.xn order by e.wtzf desc) wtzfbjpm, ");
		/*ѧ�����������ܷ֡�ѧԺ������רҵ�������༶����*/
		sql.append("rank() over(partition by e.xymc, e.xn order by e.zf desc) zfxypm, ");
		sql.append("rank() over(partition by e.zymc, e.xn order by e.zf desc) zfzypm, ");
		sql.append("rank() over(partition by e.bjmc, e.xn order by e.zf desc) zfbjpm ");
		sql.append("from ( ");
		sql.append(" select * from (");
		sql.append(" select a.xh, b.xm, sum(fs) zf, b.nj, b.xydm, b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc, c.lxxn xn, c.zdls, g.xm fzrxm, c.xmmc, ");
		sql.append(" sum(case d.xmlbmc when '��������-���´�ҵ' then to_number(a.fs) else 0 end) cxzf,");
		sql.append(" sum(case d.xmlbmc when '��������-���⽻��' then to_number(a.fs) else 0 end) dwzf,");
		sql.append(" sum(case d.xmlbmc when '��������-�������' then to_number(a.fs) else 0 end) gyzf,");
		sql.append(" sum(case d.xmlbmc when '��������-��Ṥ��' then to_number(a.fs) else 0 end) shzf,");
		sql.append(" sum(case d.xmlbmc when '��������-����' then to_number(a.fs) else 0 end) wtzf");
		sql.append(" from xg_jskp_xmsbjgb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_jskp_xmsqb c on a.jgid = c.sqid");
		sql.append(" left join xg_jskp_xmlbb d on c.xmlb = d.xmlbdm");
		sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb)g on g.yhm = c.fzr ");
		sql.append(" where c.lxxn = ? group by a.xh, b.xm, b.nj, b.xydm, b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc, c.lxxn, c.zdls, g.xm, c.xmmc");
		sql.append(" )f where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(")e ");
		sql.append(" order by zf desc,xymc,zymc,bjmc");
		return dao.getListNotOut(sql.toString(),StringUtils.joinStrArr(new String[]{SearchXn},inputV));
	}
	
	/**
	 * @����: ˼�����ʽ���б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-3-13 ����10:36:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSzszList(JskpXmsbjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.jgid,a.fjid,'zlx' xmdl,'������' xmdlmc,");
		sql.append("a.xh, b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append("a.xmmc,a.zdbm,e.bmmc zdbmmc,a.xmlb,c.xmlbmc,a.zdls,a.zdlslxfs,a.lxsj hjsj,a.fzr,d.xm fzrxm,a.fzrlxfs,a.lxly,a.fs,a.xn,a.sbsj, ");
		sql.append("a.dxq,f.dxqmc ");
		sql.append("from xg_jskp_szszjgb a ");
		sql.append("left join view_xsbfxx b on b.xh = a.xh ");
		sql.append("left join xg_jskp_xmlbb c on c.xmlbdm = a.xmlb ");
		sql.append("left join (select yhm yhm, xm from yhb union all select xh yhm, xm from xsxxb) d on d.yhm = a.fzr ");
		sql.append("left join zxbz_xxbmdm e on a.zdbm = e.bmdm ");
		sql.append("left join xg_jskp_dxq f on a.dxq = f.dxqdm ");
		sql.append(")t where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * ��֤ѧ���Ƿ����ѧ����Ϣ����
	 */
	public String getXhforXxb(String xh){
		String sql = "select xh from xsxxb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "xh");
	}
	
	/**
	 * ��ָ֤�������Ƿ����
	 */
	public String getZdbm(String zdbmmc){
		String sql = "select bmdm zdbm from zxbz_xxbmdm where bmmc = ?";
		return dao.getOneRs(sql, new String[]{zdbmmc}, "zdbm");
	}
	
	/**
	 * ��֤��Ŀ����Ƿ����
	 */
	public String getXmlb(String xmlbmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select xmlbdm from xg_jskp_xmlbb where xmlbmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
	}
	
	/**
	 * ��ѧ���Ƿ����
	 */
	public String getDxqdm(String dxqmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select dxqdm from xg_jskp_dxq where dxqmc = ?");
		String[] inputV = {dxqmc};
		return dao.getOneRs(sql.toString(), inputV, "dxqdm");
	}
	
	/**
	 * ��֤�������Ƿ����
	 */
	public String getFzr(String fzrxm){
		StringBuilder sql = new StringBuilder();
		sql.append("select gh from ( ");
		sql.append("select xh gh,xm from xsxxb ");
		sql.append("union all ");
		sql.append("select zgh gh,xm from fdyxxb ");
		sql.append(") where xm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{fzrxm}, "gh");
	}
	
	/**
	 * ������֤,ͬһѧ����ͬһ��Ŀ��ͬһʱ�䲻�ܴ���2����2�����ϼ�¼.(ͨ��������˵�����ҲҪһ����֤)
	 */
	public boolean checkIsNotExists(String xh, String xmmc, String lxsj){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*) num ");
		sql.append(" from (select xh, lxsj, xmmc");
		sql.append("  from (select a.xh, a.hjsj lxsj, b.xmmc");
		sql.append(" from xg_jskp_xmsbjgb a, xg_jskp_xmsqb b");
		sql.append(" where a.xmid = b.sqid)");
		sql.append(" union all");
		sql.append(" select xh, lxsj, xmmc from xg_jskp_szszjgb)");
		sql.append(" where xh = ? and xmmc = ? and lxsj = ?");
		paraList.add(xh);
		paraList.add(xmmc);
		paraList.add(lxsj);
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
	
	/**��������*/
	public int[] saveDrJgbData(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jskp_szszjgb( ");
		sql.append("jgid,xh,xmmc,zdbm,xmlb,fzr,fzrlxfs,zdls,zdlslxfs,lxly,fs,xn,lxsj,sbsj,drrzgh,dxq");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
}

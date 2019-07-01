package com.zfsoft.xgxt.jskp.lxsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;
import com.zfsoft.xgxt.jskp.xmjg.XmjgForm;

public class LxsqDao extends SuperDAOImpl<LxsqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(LxsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(LxsqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		/**���ں�������Ķ������ⲿ�ִ�����������������������һ�βŷ���ѧУ������2018-03-07*/
		if("0".equals(new CsszDao().getSfsh())){
			sql.append(" select * from (");
			sql.append(" select t.*,t2.xm fzrxm,t3.bmmc,t4.xmlbmc,");
			sql.append(" t5.shzt shzt1,decode(t5.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t5.shzt) shztmc,");
			sql.append(" t5.xh,t6.nj,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc");
			sql.append(" from xg_jskp_xmsqb t");
			sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb)t2");
			sql.append(" on t.fzr = t2.yhm");
			sql.append(" left join zxbz_xxbmdm t3");
			sql.append(" on t.zdbm = t3.bmdm");
			sql.append(" left join xg_jskp_xmlbb t4");
			sql.append(" on t.xmlb = t4.xmlbdm");
			sql.append(" left join xg_jskp_xmsbb t5 on t5.sqid = t.sqid ");
			sql.append(" left join view_xsbfxx t6 on t5.xh = t6.xh ");
			sql.append(")a where 1=1");
			sql.append(searchTj);
			sql.append(searchTjByUser);
			sql.append(" order by lxsj desc");
		}else{
			sql.append(" select * from (");
			sql.append(" select t.*,t2.xm fzrxm,t3.bmmc,t4.xmlbmc,");
			sql.append(" decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t.shzt) shztmc");
			sql.append(" from xg_jskp_xmsqb t");
			sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb)t2");
			sql.append(" on t.fzr = t2.yhm");
			sql.append(" left join zxbz_xxbmdm t3");
			sql.append(" on t.zdbm = t3.bmdm");
			sql.append(" left join xg_jskp_xmlbb t4");
			sql.append(" on t.xmlb = t4.xmlbdm");
			//sql.append(" where fzr = '"+user.getUserName()+"'");
			sql.append(")a where 1=1");
			sql.append(searchTj);
			sql.append(" order by lxsj desc");
		}
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(LxsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_jskp_xmsqb");
	}
	
	/**
	 * 
	 * @����: ��������Ϣ(ѧ��)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-7 ����11:53:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxStu(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xsxxb where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{username});
	}
	
	/**
	 * 
	 * @����: ��������Ϣ(��ʦ)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-7 ����11:54:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxTea(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from fdyxxb where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{username});
	}
	
	/**
	 * 
	 * @����: ��ȡ�ɻ�õ�ѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-7 ����05:11:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAvailableXhList(String[] xhs,String sqid,String ryflag){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("  select * from xsxxb where xh in (");
		for (int i = 0; i < xhs.length; i++) {
			sql.append("?");
			if(i != xhs.length-1){
				sql.append(",");
			}
			paraList.add(xhs[i]);
		}
		sql.append(" )");
		if("xg".equals(ryflag)){
			sql.append(" and xh not in(");
			sql.append(" select xh from xg_jskp_xmsbb where xmid = ? union ");
			sql.append(" select xh from xg_jskp_xmsbjgb where xmid = ?");
			sql.append(")");
			paraList.add(sqid);
			paraList.add(sqid);
		}
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-11 ����09:16:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRy(String sqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_jskp_xmsbb where xmid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>=0 ? true:false;
	}
	
	/**
	 * 
	 * @����: ɾ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-11 ����10:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRys(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_jskp_xmsbb where xmid in ( ");
		for (int i = 0; i < sqids.length; i++) {
			sql.append("?");
			if(i != sqids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), sqids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ������Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-11 ����10:29:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRy(List<String[]> params) throws Exception{
		String sql = " insert into xg_jskp_xmsbb(xh,xmid,hjsj,sbsj,splcid,shzt,lxzt)values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/*���������ò���Ҫ���ʱ��������Ա*/
	public boolean saveRyOne(List<String[]> params) throws Exception{
		String sql = " insert into xg_jskp_xmsbb(sqid,xh,xmid,hjsj,sbsj,splcid,shzt,lxzt,fjid)values(?,?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-11 ����02:36:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(LxsqForm lxsq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt ");
		sql.append(" from (select t.*,");
		sql.append(" case when t.lxsj < substr(t.lxsj, 0, 4) || '-' || t1.cs1 then");
		sql.append(" (substr(t.lxsj, 0, 4) - 1) || '-' || substr(t.lxsj, 0, 4)");
		sql.append(" else");
		sql.append(" substr(t.lxsj, 0, 4) || '-' || (substr(t.lxsj, 0, 4) + 1)");
		sql.append(" end xn");
		sql.append(" from (");
		sql.append(" select xmmc,xmid sqid,lxsj from xg_jskp_xmjgb where xmdl = 'gdx'");
		sql.append(" union ");
		sql.append(" select xmmc,sqid,lxsj from xg_jskp_xmsqb");
		sql.append("  )t");
		sql.append(" left join xg_zqb t1	");
		sql.append(" on 1 = 1) t");
		sql.append(" where xn =");
		sql.append(" (select case when ? < substr(?, 0, 4) || '-' || cs1 then");
		sql.append(" (substr(?, 0, 4) - 1) || '-' || substr(?, 0, 4)");
		sql.append(" else substr(?, 0, 4) || '-' || (substr(?, 0, 4) + 1) end xn");
		sql.append(" from XG_ZQB) and xmmc =?");
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getXmmc());
		if(StringUtils.isNotNull(lxsq.getSqid())){
			sql.append(" and sqid != ?");
			paraList.add(lxsq.getSqid());
		}
		//������Ϊtrue,����δfalse
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * ��֤ѧ�š���Ŀ���ơ�����ʱ���Ƿ��ظ�
	 */
	public boolean checkForPara(LxsqForm lxsq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)count from xg_jskp_xmsqb a,xg_jskp_xmsbb b ");
		sql.append("where a.sqid = b.sqid and a.xmmc = ? and a.lxsj = ? and b.xh = ?");
		paraList.add(lxsq.getXmmc());
		paraList.add(lxsq.getLxsj());
		paraList.add(lxsq.getXh());
		if(StringUtils.isNotNull(lxsq.getSqid())){
			sql.append(" and a.sqid != ?");
			paraList.add(lxsq.getSqid());
		}
		String count = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "count");
		return "0".equals(count) ? true : false;
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ������Ա��ѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-12 ����02:31:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmcyryXhs(String xmid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,t1.XM,t.sqid from xg_jskp_xmsbb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH where t.xmid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{xmid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-12 ����04:55:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getRyszList(LxsqForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.sqid,t1.xmmc,");
		sql.append(" t3.xmlbmc,");
		sql.append(" t.xh,");
		sql.append(" t4.xm,");
		sql.append(" decode(t.shzt,'0','δ�ύ',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '���˻�',");
		sql.append(" '5',");
		sql.append(" '�����',");
		sql.append(" t.shzt) shztmc,");
		sql.append(" '1' sjly,");
		sql.append(" t.shzt");
		sql.append(" from xg_jskp_xmsbb t ");
		sql.append(" left join xg_jskp_xmsqb t1");
		sql.append(" on t.xmid = t1.sqid	");
		sql.append(" left join xg_jskp_xmlbb t3");
		sql.append(" on t1.xmlb = t3.xmlbdm");
		sql.append(" left join xsxxb t4");
		sql.append(" on t.xh = t4.xh");
		sql.append(" where t1.sqid = ?");
		sql.append(" union");
		sql.append(" select t.jgid sqid,t1.xmmc, t3.xmlbmc, t.xh, t4.xm, 'ͨ��' shztmc, t.sjly, '1' shzt");
		sql.append(" from xg_jskp_xmsbjgb t");
		sql.append(" left join xg_jskp_xmsqb t1");
		sql.append(" on t.xmid = t1.sqid");
		sql.append(" left join xg_jskp_xmlbb t3");
		sql.append(" on t1.xmlb = t3.xmlbdm");
		sql.append(" left join xsxxb t4");
		sql.append(" on t.xh = t4.xh");
		sql.append("  where t1.sqid = ?) where 1=1");
		paraList.add(t.getSqid());
		paraList.add(t.getSqid());
		if(StringUtils.isNotNull(t.getXh())){
			sql.append("and (xh like ? or xm like ?)");
			paraList.add("%"+t.getXh()+"%");
			paraList.add("%"+t.getXh()+"%");
		}
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-13 ����10:18:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRys(List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jskp_xmsbb(sqid,xh,xmid,hjsj,sbsj,splcid,shzt,lxzt)values(?,?,?,?,?,?,?,?)");
		return dao.runBatchNotCommit(sql.toString(), params);
	}
	
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-13 ����03:20:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBmmc(String bmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmmc from zxbz_xxbmdm where bmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{bmdm});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-20 ����04:24:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXmry(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < sqids.length; i++) {
			paraList.add(new String[]{sqids[i]});
		}
		sql.append(" delete from xg_jskp_xmsbb where sqid = ?");
		boolean rs = dao.runBatchBoolean(sql.toString(), paraList);
		if(rs){
			sql.setLength(0);
			sql.append(" delete from xg_jskp_xmsbjgb where jgid = ?");
			rs = dao.runBatchBoolean(sql.toString(), paraList);
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: �Ƿ����м�¼��δ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-21 ����09:34:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isNotHaveShjl(String[] ywids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xtwh_shztb t ");
		sql.append(" left join xg_jskp_xmsbb t1 ");
		sql.append(" on t.ywid = t1.sqid ");
		sql.append(" where t1.shzt != '3' and t.shzt != '0' and t.ywid in(");//��Ҫ���˵����ύ��δ��˵ļ�¼���������˻ص������˵ļ�¼
		for (int i = 0; i < ywids.length; i++) {
			sql.append("?");
			if(i != ywids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		String rs = dao.getOneRs(sql.toString(),ywids, "cnt");
		return "0".equals(rs) ? true : false;
	}
	
	/**
	 * @����: �ж�ѧ�����꼶�Ƿ���ڵ���2017
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-17 ����04:15:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isStandardStu(String userName) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)cnt from view_xsbfxx where nj >= 2017 and xh = ? ");
		String rs = dao.getOneRs(sql.toString(), new String[]{userName}, "cnt");
		return "1".equals(rs) ? true : false;
	}
	
	/**
	 * @����: ȡ��Ŀ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-23 ����11:22:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmlbList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select xmlbdm xmlb,xmlbmc from xg_jskp_xmlbb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**ȡ��Ŀ�������ݣ��ٳ���������һ��*/
	public List<HashMap<String,String>> getXmlbListNotLikeNl() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select xmlbdm xmlb,xmlbmc from xg_jskp_xmlbb where xmlbmc not like '%��������%'");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ȡ��Ŀ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-23 ����11:22:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZdbmList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm zdbm,bmmc zdbmmc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**��ѧ������*/
	public List<HashMap<String,String>> getDxqList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select dxqdm,dxqmc from xg_jskp_dxq order by to_number(dxqdm)");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * ��֤���˵�λ�Ƿ����
	 */
	public String getZdbmdm(String zdbmmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm zdbmdm from ZXBZ_XXBMDM where bmmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{zdbmmc}, "zdbmdm");
	}
	
	/**
	 * ��֤��Ŀ����Ƿ����
	 */
	public String getXmlbdm(String xmlbmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select xmlbdm from xg_jskp_xmlbb where xmlbmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
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
	 * ��֤�������Ƿ����
	 */
	public String getCyr(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xm from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xm");
	}
	
	/**
	 * ������֤
	 */
	public boolean checkIsNotExists(String xh, String xmmc, String cysj){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*) num from (");
		sql.append("select * from xg_jskp_xmsqb a left join xg_jskp_xmsbb b on a.sqid = b.xmid");
		sql.append(") where xh = ? and xmmc = ? and lxsj = ? ");
		paraList.add(xh);
		paraList.add(xmmc);
		paraList.add(cysj);
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
	
	/**
	 * ������������xg_jskp_xmsqb
	 */
	public int[] saveDrSqbData(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jskp_xmsqb( ");
		sql.append("sqid,xmmc,zdbm,xmlb,lxsj,fzr,fzrlxfs,zdls,zdlslxfs,lxly,splcid,shzt,zdf,zxf,sjly,lxxn,sjlrr");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * ������������xg_jskp_xmsbb
	 */
	public int[] saveDrSbbData(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jskp_xmsbb( ");
		sql.append("sqid,xh,xmid,hjsj,sbsj,splcid,shzt,lxzt");
		sql.append(" )values(?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * @����: ����ID��ѯѧ���걨��Ŀ����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-4 ����09:03:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuSbDataList(String[] sqidArr) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.*,t2.xm fzrxm,t3.bmmc,t4.xmlbmc,");
		sql.append(" t5.shzt shzt1,decode(t5.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t5.shzt) shztmc,");
		sql.append(" t5.xh,t6.nj,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc");
		sql.append(" from xg_jskp_xmsqb t");
		sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb)t2");
		sql.append(" on t.fzr = t2.yhm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.zdbm = t3.bmdm");
		sql.append(" left join xg_jskp_xmlbb t4");
		sql.append(" on t.xmlb = t4.xmlbdm");
		sql.append(" left join xg_jskp_xmsbb t5 on t5.sqid = t.sqid ");
		sql.append(" left join view_xsbfxx t6 on t5.xh = t6.xh ");
		sql.append(")a where ");
		for(int i = 0; i < sqidArr.length; i++){
			sql.append(" sqid = ? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
}

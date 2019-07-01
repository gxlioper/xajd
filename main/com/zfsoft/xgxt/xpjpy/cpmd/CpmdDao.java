/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:34:13 
 */  
package com.zfsoft.xgxt.xpjpy.cpmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

import common.Globals;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-����ѧ������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:34:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpmdDao extends SuperDAOImpl<CpmdModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CpmdModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: ������Ա��ѯ
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CpmdModel model, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.id,a.xn,a.xq,a.xh,a.xm, ");
		sql.append(" substr(g.tzr,20,length(g.tzr)) tzr,(select xm from yhb a where a.yhm=substr(g.tzr,20,length(g.tzr))) tzrxm, ");
		sql.append(" (case d.xb when '1' then  '��' when '2' then 'Ů' else d.xb end) xb, ");
		sql.append(" e.nj,e.xydm,e.xymc,e.zydm,e.zymc,a.bjdm,e.bjmc,case f.tjzt when '1' then '���ύ' else 'δ�ύ' end tjzt, ");
		sql.append(" case  when a.bjdm is null or a.bjdm=d.bjdm then '��' else '��' end sfdh, ");
		sql.append(" (select bjmc from view_njxyzybj_all b where d.bjdm=b.bjdm) ybj ");
		sql.append(" from xg_pjpy_new_cpmdb a ");
		sql.append(" left join xg_zhcp_fstjjlb b on a.bjdm=b.bjdm and a.xn=b.xn and a.xq=b.xq ");
		sql.append(" left join xg_zhcp_cpzb c on b.cpzdm=c.cpzdm ");
		sql.append(" left join xsxxb d on a.xh=d.xh ");
		sql.append(" left join view_njxyzybj_all e on a.bjdm=e.bjdm ");
		sql.append(" left join xg_zhcp_fstjjlb f on a.bjdm=f.bjdm and a.xn=f.xn and a.xq=f.xq ");
		sql.append(" left join (select xn,xq,xh,max(tzsj||tzr) tzr from xg_pjpy_new_cpmdtzjlb group by xn,xq,xh) g ");
		sql.append(" on a.xh=g.xh and a.xn=g.xn and a.xq=g.xq ");
		sql.append(" where a.bjdm is not null and a.xn=(select xn from xg_pjpy_new_csszb where rownum =1) ");
		sql.append(" and a.xq=(select xq from xg_pjpy_new_csszb where rownum =1) ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
	
	return getPageList(model, sql.toString(), inputV);
	}
	
	/*
	 * ����: ������¼��ѯ
	 */
	public List<HashMap<String, String>> getTzjlList(CpmdModel model, User user)
	throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * from (select a.xh,b.xm, ");
		sql.append(" (case b.xb when '1' then '��' when '2' then 'Ů' else b.xb end) xb, ");
		sql.append(" c.nj,c.xydm,c.xymc,c.zydm,c.zymc,c.bjdm,c.bjmc,c1.bjdm zybj,t7.sydm,t7.symc,");
		sql.append(" (select xm from yhb b where a.tzr=b.yhm)tzrxm,a.tzr,a.tzsj,a.tzbz ");
		sql.append(" from xg_pjpy_new_cpmdtzjlb a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" left join view_njxyzybj_all c on b.bjdm=c.bjdm ");
		sql.append(" left join view_njxyzybj_all c1 on b.zybj=c1.bjdm ");
		sql.append(" left join XG_XTWH_SYBJGLB t6 on b.bjdm = t6.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t7 on t6.sydm = t7.sydm ");
		sql.append(" where a.xn=(select xn from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(" and a.xq=(select xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(" ) t1 where tzr ='" + user.getUserName()+ "' or (1=1");
		sql.append(searchTjByUser);
		sql.append(" )) where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	
	/*
	 * ����: ȡ������ѧ��
	 */
	
	public boolean updateCpmd(String id,User user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();
		
		sql.append(" update xg_pjpy_new_cpmdb ");
		sql.append("set bjdm = '' ");
		sql.append(" where id in ( ");
		String[] ids = id.split(",");
		
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/*
	 * ����: ȡ��������Ա������¼
	 */
	
	public boolean insertTzjl(String id, User user) throws Exception {
		
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" select xn,xq,xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') tzsj,'"+user.getUserName()+"'yhm,");
		sql.append("  '�� '||(select b.bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm)|| ");
		sql.append(" ' ����Ϊ ������' tzbz from xg_pjpy_new_cpmdb a  where id in ( " );
		
		String[] ids = id.split(",");
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}
	

	/**
	 * @throws Exception  
	 * @����:  ��¼�༶����,��һ���༶��������һ���༶
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-26 ����10:59:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param tzhbjdm
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public boolean insertbjTzjl(String xh, String xn, String xq, String tzhbjdm, User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" select xn,xq,xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') tzsj,'"+user.getUserName()+"'yhm, ");
		sql.append(" '�� '||(select b.bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm)|| ");
		sql.append("  ' ����Ϊ '||(select bjmc from view_njxyzybj_all b where b.bjdm= ? ) tzbz from xg_pjpy_new_cpmdb a " );
		sql.append(" where xh= ? and xn = ? and xq = ? ");
		
		return dao.runUpdate(sql.toString(),new String[]{tzhbjdm,xh,xn,xq});
		
	}

	/**
	 * @throws Exception  
	 * @����: 
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-26 ����11:17:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param tzhbjdm
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public boolean updateBjtzCpmd(String xh, String xn, String xq, String tzhbjdm) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update XG_PJPY_NEW_CPMDB set bjdm = ? where xh in ( ? ) and xn = ? and xq = ?");
		
		return dao.runUpdate(sql.toString(), new String[]{tzhbjdm,xh,xn,xq});
		
	}

	/** 
	 * @����: �жϵ�ǰ�Ƿ���ڵ�ǰ����������Ա����
	 * @���ߣ�CQ [���ţ�785]
	 * @���ڣ�2013-7-29 ����02:40:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHavePjry(String xh, String xn, String xq) {
		
		String sql = " select count(1) num from XG_PJPY_NEW_CPMDB where xh = ? and xn = ? and xq = ? ";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh,xn,xq}, "num")) > 0;
	}

	/**
	 * @throws Exception  
	 * @����: ���Ӳ�����Ա����������У����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-29 ����02:49:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param xh
	 * @param tzhbjdm
	 * void �������� 
	 * @throws 
	 */
	public boolean insertBjtzCpmd(String xn, String xq, String xh, String tzhbjdm) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into XG_PJPY_NEW_CPMDB(xn,xq,xh,xm,bjdm) ");
		sql.append(" values( ?, ? ,?,(select xm from xsxxb where xh=?),? ) ");
		
		return dao.runUpdate(sql.toString(),new String[]{xn,xq,xh,xh,tzhbjdm});
		
	}

	/**
	 * @throws Exception  
	 * @����:  ��¼�༶����,�Ӳ���������һ���༶
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-26 ����10:59:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param tzhbjdm
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public boolean insertbjTzjl1(String xn, String xq, String xh, String tzhbjdm, User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" values(?,?,?,to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'),'"+user.getUserName()+"', ");
		sql.append(" '�� ������ ����Ϊ '||(select bjmc from view_njxyzybj_all b where b.bjdm = ?))");
		
		return dao.runUpdate(sql.toString(),new String[]{xn,xq,xh,tzhbjdm});
		
	}

	/** 
	 * @����:����ѧ�ŷ��ص�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-31 ����08:52:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getTzxx(String xh) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.xn, a.xq, a.xh, b.tjzt, substr(c.tjr, 20, length(c.tjr)) tjr, ");
		sql.append(" (select xm from yhb b where substr(c.tjr, 20, length(c.tjr)) = b.yhm) tjrxm, ");
		sql.append(" substr(c.tjr, 1, 19) tjsj, case when a.bjdm<>d.bjdm then '1' end tzzt, ");
		sql.append(" substr(e.tzr,20,length(e.tzr)) tzr, (select xm from yhb b where substr(e.tzr,20, length(e.tzr)) = b.yhm) tzrxm ");
		sql.append(" from xg_pjpy_new_cpmdb a left join xg_zhcp_fstjjlb b on a.xn = b.xn and a.xq = b.xq and a.bjdm = b.bjdm ");
		sql.append(" left join (select xn, xq, xh, max(tzsj || tzr) tjr ");
		sql.append(" from xg_pjpy_new_cpmdtzjlb group by xn, xq, xh) c on a.xn = c.xn and a.xq = c.xq and a.xh = c.xh ");
		sql.append(" left join xsxxb d on a.xh=d.xh ");
		sql.append(" left join ( select xn,xq,xh,max(tzsj||tzr) tzr from xg_pjpy_new_cpmdtzjlb group by xn,xq,xh) e ");
		sql.append(" on a.xn=e.xn and a.xq=e.xq and a.xh=e.xh ");
		sql.append(" where xh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	/** 
	 * @����:�жϵ�ǰ�����Ƿ����۲��¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-31 ����02:42:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean getSfcz() {
		
		String sql = "select count(1) num from xg_pjpy_new_cpmdb a where xn = (select xn from xg_pjpy_new_csszb where rownum = 1) and xq = (select xq from xg_pjpy_new_csszb where rownum = 1) and exists (select 1 from xg_zhcp_zcfsb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and fs is not null )";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{}, "num")) > 0;
	}

	
	/**
	 * @throws Exception 
	 * @return  
	 * @����:��������Ա��ִ�г�ʼ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-31 ����02:57:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	public boolean initDel() throws Exception {

		String sql = "   delete from xg_pjpy_new_cpmdb where xn = (select xn from xg_pjpy_new_csszb where rownum = 1) and xq = (select xq from xg_pjpy_new_csszb where rownum = 1)";
		
		return dao.runUpdate(sql, new String[]{});
		
	}
	
	/**
	 * @throws Exception 
	 * @return  
	 * @����:��������Ա��ִ�г�ʼ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-31 ����02:57:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	public boolean init() throws Exception {

		String sql = "insert into xg_pjpy_new_cpmdb(xn,xq,xh,xm,bjdm��zybj) select (select xn from xg_pjpy_new_csszb where rownum =1)xn,(select xq from xg_pjpy_new_csszb where rownum = 1)xq,xh,xm,bjdm,zybj from view_xsjbxx";
		
		return dao.runUpdate(sql, new String[]{});
		
	}
	
	/**
	 * 
	 * @����: ѧ���۲⣬�Զ���ʼ��ѧ���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-5 ����03:58:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean xnInit() throws Exception{
		
		return dao.runProcuder("{call pro_xg_zhcp_xncpmdb()}", new String[]{});
	}
	
	
	/**
	 * 
	 * @����: ��ѯ���Ӳ���ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����11:30:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAddCpxsList(CpmdModel model) throws Exception{
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (");
		sql.append(" select t2.xh,t2.xm,t3.bjdm cpbjdm,t3.bjmc cpbjmc,t3.nj cpnj ,");
		sql.append(" t3.xydm cpxydm,t3.xymc cpxymc,t3.zydm cpzydm,t3.zymc cpzymc,");
		//�㽭��ѧ���Ի�,���������༶Ϊ�գ����඼Ϊ����״̬
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			sql.append("case  when t2.cpbjdm is null then '��' else '��' end sfcp,case when t2.cpbjdm is null then '0' else '1' end ");
		}else{
			sql.append(" nvl(t4.tjzt,'0') ");
		}
		sql.append(" tjzt from (select t1.xh,t1.xm,nvl(t2.bjdm,t1.bjdm) bjdm,t2.bjdm cpbjdm,t2.xn,t2.xq ");
		sql.append(" from xsxxb t1 left join xg_pjpy_new_cpmdb t2 on t1.xh =t2.xh) t2");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" left join xg_zhcp_fstjjlb t4 on t2.xn=t4.xn and t2.xq=t4.xq and t2.cpbjdm=t4.bjdm");
		sql.append(" where t2.xn||t2.xq in (select xn||xq from xg_pjpy_new_csszb where rownum=1)");
		sql.append(") a left join xg_xtwh_bmsxb b on a.cpxydm=b.bmdm where 1=1 ");
		sql.append(searchTj);
		sql.append(" order by b.sx,a.cpxydm,a.cpzydm,a.cpbjdm");
		
		return super.getPageList(model, sql.toString(), StringUtils.joinStrArr(new String[]{},inputV));
	}

	/**
	 * @throws Exception  
	 * @����:����ѧ�ꡢѧ�Ÿ���ѧ������༶
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-6 ����10:03:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xh
	 * void �������� 
	 * @throws 
	 */
	public boolean updateXnzc(String xn, String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("MERGE INTO xg_pjpy_new_cpmdb a ");
		sql.append("USING (select xn,'on' xq,xh,xm,bjdm from (select a.*,row_number()over(partition by xh order by xq desc) rn from xg_pjpy_new_cpmdb a ");
		sql.append("where xq <> 'on' and xn = ? and xh = ?) ");
		sql.append("where rn =1) b ");
		sql.append("ON (a.xh = b.xh and a.xq = b.xq and a.xn = b.xn) ");
		sql.append("WHEN MATCHED THEN ");
		sql.append("UPDATE ");
		sql.append("SET a.bjdm = b.bjdm ");
		sql.append("WHERE a.xq = b.xq and a.xh = b.xh and a.xn = b.xn ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("INSERT (xn,xq,xh,xm,bjdm) ");
		sql.append("VALUES (b.xn,b.xq, b.xh,b.xm,b.bjdm) ");
		
		return dao.runUpdate(sql.toString(), new String[]{xn,xh});
		
	}

	/**
	 * @throws Exception  
	 * @����:����ID���¶�Ӧ��ѧ���۲�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-6 ����10:19:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public boolean updateXncpmd(String values, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("MERGE INTO xg_pjpy_new_cpmdb a ");
		sql.append("USING (select xn,'on' xq,xh,xm,bjdm from (select a.*,row_number()over(partition by xh order by xq desc) rn from xg_pjpy_new_cpmdb a ");
		sql.append("where xq <> 'on' and xn||xh in (select xn||xh from xg_pjpy_new_cpmdb where id in ( ");
		
		String[] ids = values.split(",");
		for(int i=0; i<ids.length; i++ ){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		
		sql.append(") )) ");
		sql.append("where rn =1) b ");
		sql.append("ON (a.xh = b.xh and a.xq = b.xq and a.xn = b.xn) ");
		sql.append("WHEN MATCHED THEN ");
		sql.append("UPDATE ");
		sql.append("SET a.bjdm = b.bjdm ");
		sql.append("WHERE a.xq = b.xq and a.xh = b.xh and a.xn = b.xn ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("INSERT (xn,xq,xh,xm,bjdm) ");
		sql.append("VALUES (b.xn,b.xq, b.xh,b.xm,b.bjdm) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/** 
	 * @����: ��ȡ�༶����
	 * @���ߣ���ˮ�� [���ţ�1150]
	 * @���ڣ�2014-9-29 ����10:25:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBjrs(String bjdm, String xn, String xq) {
		
		String sql = " select count(1) bjrs from xg_pjpy_new_cpmdb where bjdm = ? and xn = ? and xq = ? ";
		
		return dao.getOneRs(sql, new String[]{bjdm,xn,xq}, "bjrs");
	}
	
	
	/**
	 * 
	 * @����:��ȡ����������
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-10-24 ����03:55:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCpzrs(String xh, String xn, String xq) {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select a.xh, a.xn, a.xq, c.cpzrs ");
		sql.append("  from xg_pjpy_new_cpmdb a ");
		sql.append("  left join xg_zhcp_fstjjlb b ");
		sql.append("    on a.bjdm = b.bjdm ");
		sql.append("    and a.xn = b.xn and a.xq = b.xq ");
		sql.append("  left join (");
		sql.append(" 	select count(xh) cpzrs,cpzdm,xn,xq from ");
		sql.append(" 	(select distinct t.cpzdm,t1.xh,t.xn,t.xq from xg_zhcp_fstjjlb t ");
		sql.append(" 	left join xg_pjpy_new_cpmdb t1 ");
		sql.append(" 		on t.bjdm = t1.bjdm and t.xn = t1.xn and t.xq = t1.xq where t.tjzt = '1') group by xn,xq,cpzdm ");
		sql.append(" 	)c on b.cpzdm = c.cpzdm and a.xn = c.xn and a.xq = c.xq ");
		sql.append(" where a.xh = ? ");
		
		params.add(xh);
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and a.xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and a.xq = ? ");
			params.add(xq);
		}else{
			sql.append(" and a.xq = ? ");
			params.add(CsszService.XQKG);
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "cpzrs");
	}
	
	/**
	 * 
	 * @����: ��ѧ�ꡢѧ�ڲ�ѯ����ѧ���б� 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����7:11:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdmArr
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCpmdList(String[] bjdmArr , String xn ,String xq){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,xm,bjdm from xg_pjpy_new_cpmdb where xn=? and xq=? and (");
		
		for (int i = 0 , j = bjdmArr.length ; i < j; i ++){
			sql.append("bjdm=?");
			if (i+1 != j){
				sql.append(" or ");
			}
		}
		sql.append(")");
		
		return dao.getListNotOut(sql.toString(), StringUtils.joinStrArr(new String[]{xn,xq},bjdmArr));
	}
	
}

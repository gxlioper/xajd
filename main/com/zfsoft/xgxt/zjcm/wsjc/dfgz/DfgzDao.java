/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-2 ����08:59:38 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.dfgz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ý������
 * @�๦������: ��ֹ���
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-3-2 ����08:59:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DfgzDao extends SuperDAOImpl<DfgzForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DfgzForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DfgzForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.dfszid,a.xn,a.xq,(select xqmc from xqdzb c where a.xq=c.xqdm) xqmc,a.ccny,a.wwsj,a.wwzzsj,substr(a.ccny, 0, 4) nd,substr(a.ccny, 6, 2) yf, ");
		sql.append("a.wwsj || ' �� ' || a.wwzzsj kfwhsj,sum(case when b.tjzt='1' then 1 else 0 end ) ytjqs, ");
		sql.append("sum(case when b.tjzt='0' then 1 else 0 end ) wtjqs ");
		sql.append("from xg_zjcm_gygl_wsjc_dfsz a left join xg_zjcm_gygl_wsjc_wsf b  on a.dfszid = b.dfszid ");
		sql.append("group by a.dfszid,a.xn,a.xq,a.ccny,a.wwsj,a.wwzzsj) where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(DfgzForm.class);
		super.setKey("dfszid");
		super.setTableName("xg_zjcm_gygl_wsjc_dfsz");
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	public List<HashMap<String, String>> getPfzList(){
		String sql = "select pfzid,pfzmc||'('||decode(ssxq,'001','��ɳ','002','ͩ��')||')' pfzmc,ssxq from xg_zjcm_gygl_wsjc_pfz";
		return dao.getList(sql, new String[]{}, new String[]{"pfzid","pfzmc","ssxq"});
	}

	/**
	 * @throws Exception  
	 * @����:����ѧ�ꡢѧ�ڡ�������²�ѯ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-11 ����09:37:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * DfgzForm �������� 
	 * @throws 
	 */
	public DfgzForm getDfszInfo(DfgzForm model) throws Exception {
		
		String sql = "select * from xg_zjcm_gygl_wsjc_dfsz where xn = ? and xq = ? and ccny = ?";
		
		return getModel(model, sql, new String[]{model.getXn(),model.getXq(),model.getCcny()});
	}
	
	
	
	/**
	 * 
	 * @����:���ݴ�����ò�ѯ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-11 ����09:48:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dfszid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> pfzszList (String dfszid){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.pfzmc||'('||decode(ssxq,'001','��ɳ','002','ͩ��')||')' pfzmc ");
		sql.append("from xg_zjcm_gygl_wsjc_dfsz_dfz a left join xg_zjcm_gygl_wsjc_pfz b on a.pfzid=b.pfzid ");
		sql.append("where dfszid = ? order by ssxq,to_number(ccbl) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{dfszid});
	}

	/**
	 * @throws Exception  
	 * @����:ɾ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����11:48:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * void �������� 
	 * @throws 
	 */
	public boolean delPfzSz(String dfszid) throws Exception {
		
		String sql = "delete from xg_zjcm_gygl_wsjc_dfsz_dfz where dfszid = ?";
		
		return dao.runUpdate(sql, new String[]{dfszid});
		
	}
	
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-14 ����06:48:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dfszid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delPfzQs(String dfszid) throws Exception {
		
		String sql = "delete from xg_zjcm_gygl_wsjc_wsf where dfszid = ?";
		
		return dao.runUpdate(sql, new String[]{dfszid});
		
	}

	/** 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����01:47:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param dfgzForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean randomQs(DfgzForm myForm, DfgzForm dfgzForm) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		List<String> param = new ArrayList<String>();
		
		sql.append("insert into xg_zjcm_gygl_wsjc_wsf(ssxq,lddm,ldmc,qsh,qsmc,pfzid,dfszid,ccny,ssxy,nj,xn,xq) ");
		sql.append("select (select ssxq from xg_zjcm_gygl_wsjc_pfz where pfzid = ? ) ssxq,lddm,ldmc,qsh,qsh, ? pfzid, ");
		sql.append("? dfszid,? ccny,xydm,nj,(select dqxn from xtszb),(select dqxq from xtszb) from ( ");
		sql.append("select t4.lddm,t4.ldmc,t4.qsh,t4.xydm,t4.nj,round(t4.xycn/t4.sm*100,2) szbl,nvl(t4.cccs,0) cccs from ( ");
		sql.append("select t6.lddm,t6.ldmc,t6.qsh,t6.xydm,t6.nj,t6.sm,t6.xqdm,t5.cccs,");
		sql.append("row_number() over(partition by xydm order by to_number(nvl(t5.cccs,0)),t6.sjs) xycn from ( ");
		sql.append("select t2.lddm,t2.ldmc,t1.qsh,t1.xydm,t1.nj,sum(1) over(partition by xydm) sm,t2.xqdm, ");
		sql.append("sjs from ( ");
		sql.append("select a.lddm,a.qsh,d.nj,d.xydm, ");
		sql.append("row_number()over(partition by a.lddm,a.qsh order by d.xydm) rn, ");
		sql.append("round(dbms_random.value(1, 9999999)) sjs from xg_gygl_new_qsxxb a ");
		sql.append("left join xg_gygl_new_cwxxb b on a.lddm=b.lddm and a.qsh=b.qsh ");
		sql.append("left join xsxxb c on b.xh=c.xh left join view_njxyzybj_all d on c.bjdm = d.bjdm  where b.xh is not null ");
		if("0".equals(dfgzForm.getBhbyb())){
			//�����������ҵ��
			sql.append("and nvl(d.nj,9999)>(select substr(dqxn,6,4) from xtszb)-nvl(c.xz,9999) "); 
		}
		sql.append(") t1 left join xg_gygl_new_ldxxb t2 on t1.lddm = t2.lddm where t1.rn=1 ) t6 ");
		sql.append("left join ( ");
		sql.append("select lddm,qsh,count(1) cccs from xg_zjcm_gygl_wsjc_wsf a left join xg_zjcm_gygl_wsjc_dfsz b on a.dfszid = b.dfszid ");
		sql.append("where b.xn = ? and b.xq = ? group by lddm,qsh");
		sql.append(") t5 on t6.lddm=t5.lddm and t6.qsh=t5.qsh ");
		sql.append("where (t6.lddm,t6.qsh) not in (select lddm,qsh from xg_zjcm_gygl_wsjc_wsf where dfszid = ?) ");
		sql.append("and t6.xqdm = (select ssxq from xg_zjcm_gygl_wsjc_pfz where pfzid = ?) ) t4 ");  //t4 ȡ��ǰ����ȡ��ס�޵�����
		
		sql.append(") where to_number(szbl) <= to_number(?) order by cccs");
		
		param.add(dfgzForm.getPfzid());
		param.add(dfgzForm.getPfzid());
		param.add(myForm.getDfszid());
		param.add(myForm.getCcny());
		param.add(myForm.getXn());
		param.add(myForm.getXq());
		param.add(myForm.getDfszid());
		param.add(dfgzForm.getPfzid());
		param.add(dfgzForm.getCcbl());
		
		return dao.runUpdate(sql.toString(), param.toArray(new String[]{}));
	}

	/**
	 * @throws SQLException  
	 * @����:��������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����02:37:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pfzxxList
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public boolean pfzSzPlbc(List<String[]> pfzxxList) throws SQLException {
		
		String sql = "insert into xg_zjcm_gygl_wsjc_dfsz_dfz(dfszid,pfzid,ccbl,bhbyb) values(?,?,?,?)";
		int[] result = dao.runBatch(sql, pfzxxList);
		return dao.checkBatchResult(result);
		
	}

	/** 
	 * @����:�ж��������Ƿ����ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����02:45:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dfszid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean getWsfTj(String dfszid) {
		String sql = "select count(1) count from xg_zjcm_gygl_wsjc_wsf where tjzt = '1' and dfszid = ? ";
		String num = dao.getOneRs(sql, new String[]{dfszid}, "count");
		return Integer.parseInt(num)>0;
		
	}
	
	/**
	 * 
	 * @����:�ж��Ƿ��г�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-14 ����06:16:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dfszid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean getExistCcsj(String dfszid) {
		String sql = "select count(1) count from xg_zjcm_gygl_wsjc_wsf where dfszid = ? ";
		String num = dao.getOneRs(sql, new String[]{dfszid}, "count");
		return Integer.parseInt(num)>0;
		
	}

	
	/**
	 * 
	 * @����:��ȡ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-14 ����07:29:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCcqsList(DfgzForm t, User user) throws Exception {

			String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select a.*,(select bmmc from zxbz_xxbmdm c where a.ssxy=c.bmdm) xymc,cws,rzrs, d.pfzmc ");
			sql.append("from xg_zjcm_gygl_wsjc_wsf a left join ");
			sql.append("(select lddm,qsh,count(xh) rzrs,count(1)cws from xg_gygl_new_cwxxb group by lddm,qsh ) b ");
			sql.append("on a.lddm=b.lddm and a.qsh=b.qsh left join xg_zjcm_gygl_wsjc_pfz d on a.pfzid=d.pfzid ");
			sql.append("where tjzt = '"+t.getTjzt()+"' and a.dfszid = '"+t.getDfszid()+"') where 1=1 ");
			sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * @throws SQLException  
	 * @����:��ѯ�·�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-15 ����03:05:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getYueFenByXn() throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select ny from ( ");
		sql.append("select case when to_number(lv)>8 then substr(dqxn,1,4)||'-'||replace(lpad(lv,2),' ','0') else ");
		sql.append("substr(dqxn,6,9)||'-'||replace(lpad(lv,2),' ','0') end ny ");
		sql.append("from xtszb,(select LEVEL lv from dual CONNECT BY LEVEL <= 12)) a where not exists ");
		sql.append("(select 1 from xg_zjcm_gygl_wsjc_dfsz b where a.ny=b.ccny) order by ny");

		return dao.getList(sql.toString(), new String[]{}, "ny");
	}
	
	

}

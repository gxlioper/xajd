/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:20:40 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-3-28 ����05:20:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddSbDao extends SuperDAOImpl<CxddSbForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxddSbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	     �༶�ϱ���ѯDAO
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxddSbForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ");
		sql.append(" (select t.bjid,to_char(t1.nj) nj,t1.xymc,t1.xydm,t1.zymc,t1.zydm,t1.bjmc,t.bjdm," +
				" case when t.shzt  != '0' and t.shzt != '3'  then t4.bjrsshz  else t2.bjrs end bjrs," +
				"t3.xm tjr,decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t.shzt) shztmc,t.shzt,t.splc");
		sql.append(" from xg_xsxx_cxpy_pysb_bj t");
		sql.append(" left join view_njxyzybj t1");
		sql.append(" on t.bjdm = t1.bjdm ");
		sql.append(" left join (select count(1) bjrs,bjdm  from xsxxb a  where a.sfzx is null or a.sfzx = '��У'  group by a.bjdm) t2");
		sql.append(" on t.bjdm = t2.bjdm");
		sql.append(" left join yhb t3");
		sql.append(" on t.tjr = t3.yhm");
		sql.append(" left join (select count(1) bjrsshz, bjdm");
		sql.append(" from (select y.bjdm, y1.xh");
		sql.append(" from xg_xsxx_cxpy_pysb_bj y");
		sql.append(" join xg_xsxx_cxpy_pysb_xs y1");
		sql.append(" on y.bjdm = y1.bjdm");
		sql.append(" where y.xn = '"+Base.currXn+"'");
		sql.append(" and y.xq = '"+Base.currXq+"'");
		sql.append(" and y.shzt is not null");
		sql.append(" and (y.shzt != '0' or");
		sql.append(" y.shzt != '3'))");
		sql.append(" group by bjdm) t4");
		sql.append(" on t.bjdm = t4.bjdm");
		sql.append(" where t.xn = '"+Base.currXn+"'");
		sql.append(" and t.xq = '"+Base.currXq+"'");
		sql.append(" union all");
		sql.append(" select sys_guid() || '' bjid,to_char(b.nj) nj,b.xymc,b.xydm,b.zymc,b.zydm,b.bjmc,b.bjdm,b1.bjrs ,'"+user.getRealName()+"' tjr,'δ�ύ' shztmc,'0' shzt, ");
		sql.append(" (select splc from xg_xsxx_cxpy_cssz) splc  ");
		sql.append(" from view_njxyzybj b");
		sql.append(" left join (select count(1) bjrs,bjdm  from xsxxb a  where a.sfzx is null or a.sfzx = '��У'  group by a.bjdm) b1");
		sql.append(" on b.bjdm = b1.bjdm");
		sql.append(" where b.bjdm not in(select bjdm from xg_xsxx_cxpy_pysb_bj c where c.xn = '"+Base.currXn+"' and c.xq = '"+Base.currXq+"') ");
		sql.append(" ) t");
		sql.append(" where 1=1");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(),inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(CxddSbForm.class);
		super.setKey("bjid");
		super.setTableName("xg_xsxx_cxpy_pysb_bj");
	}
	
	/**
	 * 
	 * @����:��ȡ��ѧ�걾ѧ�ڰ༶�ϱ�sql
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-26 ����06:40:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjSqlString(User user){
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from ");
		sql.append(" (select t.bjdm,t.splc,t.shzt");
		sql.append(" from xg_xsxx_cxpy_pysb_bj t");
		sql.append(" left join view_njxyzybj t1");
		sql.append(" on t.bjdm = t1.bjdm ");
		sql.append(" left join yhb t3");
		sql.append(" on t.tjr = t3.yhm");
		sql.append(" where t.xn = '"+Base.currXn+"'");
		sql.append(" and t.xq = '"+Base.currXq+"'");
		sql.append(" union all");
		sql.append(" select b.bjdm, ");
		sql.append(" (select splc from xg_xsxx_cxpy_cssz) splc,'0' shzt  ");
		sql.append(" from view_njxyzybj b");
		sql.append(" where b.bjdm not in(select bjdm from xg_xsxx_cxpy_pysb_bj c where c.xn = '"+Base.currXn+"' and c.xq = '"+Base.currXq+"') ");
		sql.append(" ) t");
		sql.append(" where 1=1");
		sql.append(" ");
	//	sql.append(searchTjByUser);
		return sql.toString();
	}
	
	/**
	 * 
	 * @����:ά������ѧ����ѯDAO
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-26 ����06:20:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsPageList(CxddSbForm t, User user) 
	throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String sql1 = this.getBjSqlString(user);
		String[] bjdm =t.getBjdms().split(",");
		sql.append(" select t.* from(");
		sql.append(" select t.bjdm,");
		sql.append(" t.pj,");
		sql.append(" t.py,");
		sql.append(" t.xh,");
		sql.append(" t.xsid,");
		sql.append(" t1.splc,");
		sql.append(" t1.shzt,");
		sql.append(" t2.xm,");
		sql.append(" t2.bjmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t3.cxdjmc");
		sql.append(" from xg_xsxx_cxpy_pysb_xs t");
		sql.append(" left join("+sql1+") t1");
		sql.append(" on t.bjdm = t1.bjdm");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" left join xsxx_cxdjdmb t3 ");
		sql.append(" on t.pj = t3.cxdjdm");
		sql.append(" where t.xn = '"+Base.currXn+"'");
		sql.append(" and t.xq = '"+Base.currXq+"'");
		if(t.getFlag().equals("gx")){//������ά��������������ǹ�ѡ����
			sql.append(" and t.bjdm in(");
			for(int i=0;i<bjdm.length;i++){
				sql.append("'"+bjdm[i]+"'");
				if(i != bjdm.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
	
		sql.append(" union all");
		sql.append(" select t.bjdm,");
		sql.append(" '' pj,");
		sql.append(" '' py,");
		sql.append(" t.xh,");
		sql.append(" sys_guid() || '' xsid,");
		sql.append(" t1.splc,");
		sql.append(" t1.shzt,");
		sql.append(" t.xm,");
		sql.append(" t.bjmc,");
		sql.append(" t.nj,");
		sql.append(" t.xydm,");
		sql.append(" t.xymc,");
		sql.append(" t.zydm,");
		sql.append(" t.zymc,");
		sql.append(" '' cxdjmc");
		sql.append(" from view_xsjbxx t");
		sql.append(" left join("+sql1+") t1");
		sql.append(" on t.bjdm = t1.bjdm");
		sql.append(" where t.xh not in (select xh");
		sql.append("  from xg_xsxx_cxpy_pysb_xs c");
		sql.append("  where c.xn = '"+Base.currXn+"'");
		sql.append("  and c.xq = '"+Base.currXq+"')");
        if(t.getFlag().equals("gx")){//������ά��������������ǹ�ѡ����
        	sql.append(" and t.bjdm in(");
        	for(int i=0;i<bjdm.length;i++){
				sql.append("'"+bjdm[i]+"'");
				if(i != bjdm.length-1){
					sql.append(",");
				}
			}
        	sql.append(")");
		}
		sql.append(" ) t where 1=1 ");
		if(StringUtils.isNull(t.getFlag1())){
			sql.append("and bjdm not in (select bjdm from xg_xsxx_cxpy_pysb_bj where shzt in ('1','2','5') ");
			sql.append("and xn='"+Base.currXn+"'and xq = '"+Base.currXq+"')");
		}
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
	    if(StringUtils.isNotNull(t.getXh())){
	    	sql.append(" and xh like '%"+t.getXh()+"%'");
	    	sql.append(" or ");
	    	sql.append(" xm like '%"+t.getXh()+"%'");
		}
		sql.append(" order by pj ,xh ");
		return getPageList(t, sql.toString(),inputV);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���°༶������¼����ֹ�༶���࣬���ݷ�ΧΪδ�ύ�������˻ص�����
	 * @����:Ϊ�˺�����չ���ܣ�������������༶�������߲�ѡ�༶������,�÷����Ǵ������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-27 ����02:02:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean UpdateBjtzRecode(String[] bjdms) throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paraArrayList = new ArrayList<String>();
		sql.append(" update xg_xsxx_cxpy_pysb_xs a");
		sql.append(" set a.bjdm =(select b.bjdm from xsxxb b");
		sql.append(" where b.xh = a.xh)");
		sql.append(" where a.xn = ?");
		paraArrayList.add(Base.currXn);
		sql.append(" and a.xq = ?");
		paraArrayList.add(Base.currXq);
		sql.append(" and a.bjdm not in(select bjdm");
		sql.append(" from xg_xsxx_cxpy_pysb_bj");
		sql.append(" where xn = ?");
		paraArrayList.add(Base.currXn);
		sql.append(" and xq = ?");
		paraArrayList.add(Base.currXq);
		sql.append(" and shzt in ('1', '2', '5'))");
		sql.append(" and a.xh not in(select t.xh");
		sql.append(" from xsxxb t");
		sql.append(" where bjdm  in");
		sql.append(" (select bjdm");
		sql.append(" from xg_xsxx_cxpy_pysb_bj");
		sql.append(" where xn = ?");
		paraArrayList.add(Base.currXn);
		sql.append(" and xq = ?");
		paraArrayList.add(Base.currXq);
		sql.append(" and shzt in ('1', '2', '5'))");
		sql.append(" and exists (select 1");
		sql.append(" from xg_xsxx_cxpy_pysb_xs t1");
		sql.append(" where t.xh = t1.xh");
		sql.append(" and t.bjdm != t1.bjdm");
		sql.append(" and t1.xn = ?");
		paraArrayList.add(Base.currXn);
		sql.append(" and t1.xq = ?  ) )");
		paraArrayList.add(Base.currXq);
		sql.append(" and exists (select 1");
		sql.append(" from xsxxb t1");
		sql.append(" where a.xh = t1.xh");
		sql.append(" and a.bjdm != t1.bjdm ");
		sql.append(" and a.xn = ?");
		paraArrayList.add(Base.currXn);
		sql.append(" and a.xq = ?  )");
		paraArrayList.add(Base.currXq);
		if(bjdms != null && bjdms.length != 0){
			sql.append(" and a.bjdm in(");
			for (int i = 0; i < bjdms.length; i++) {
				sql.append("?");
				if(i != bjdms.length-1){
					sql.append(",");
				}
				paraArrayList.add(bjdms[i]);
			}
			sql.append(")");
			
		}
		return dao.runUpdate(sql.toString(), paraArrayList.toArray(new String[]{}));
	}
	
	//�Ƿ�ѧ�걾ѧ��ѧ������ѧ���ϱ�����ά�������������۶��Ѿ�ά��
	public boolean isHaveQxTj(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select (select count(1) num from view_xsjbxx where bjdm = ?) -");
		sql.append(" (select count(1) num1");
		sql.append(" from xg_xsxx_cxpy_pysb_xs");
		sql.append(" where bjdm = ?");
		sql.append(" and xn = ?");
		sql.append(" and xq = ?");
		sql.append(" and pj is not null");
		sql.append(" and py is not null) num");
		sql.append(" from dual");
		String num =  dao.getOneRs(sql.toString(), new String[]{bjdm,bjdm,Base.currXn,Base.currXq}, "num");
		//�����ڵ���������� ����У��ѧ��ʱnum<0,Ҳ�����ύ
		if(num.equalsIgnoreCase("0") || Integer.parseInt(num) < 0){
			return  true;
		}else{
			return  false; 
		}
	}
	
	public  List<HashMap<String, String>> getCxdjdmList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select cxdjdm,cxdjmc from xsxx_cxdjdmb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public boolean saveDataXs(CxddSbForm t,String type) throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
		if(type.equalsIgnoreCase("insert")){
			sql.append(" insert into xg_xsxx_cxpy_pysb_xs(xsid,xh,bjdm,xn,xq,pj,py)");
			sql.append(" values(?,?,?,?,?,?,?)");
			parameter.add(t.getXsid());
			parameter.add(t.getXh());
			parameter.add(t.getBjdm());
			parameter.add(t.getXn());
			parameter.add(t.getXq());
			parameter.add(t.getPj());
			parameter.add(t.getPy());
		}else{
			sql.append(" update xg_xsxx_cxpy_pysb_xs set py=?,pj=? where xsid =? ");
			parameter.add(t.getPy());
			parameter.add(t.getPj());
			parameter.add(t.getXsid());
		}
		
		return dao.runUpdate(sql.toString(),parameter.toArray(new String[]{}));
	}
	
	//�ύʱɾ��ѧ���ϱ����в���У��������
	public boolean delCxddbzx(String bjdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("  delete");
		sql.append(" from xg_xsxx_cxpy_pysb_xs t");
		sql.append(" where t.bjdm = ?");
		sql.append(" and not exists");
		sql.append("  (select 1 from view_xsjbxx t1 where t.xh = t1.xh)");
		return dao.runUpdate(sql.toString(), new String[]{bjdm});
	}
	
}

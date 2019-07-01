/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:23:19 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-28 ����05:23:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddJgDao extends SuperDAOImpl<CxddJgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxddJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxddJgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		//sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t2.xqmc,");
		sql.append(" t3.cxdjmc");
		sql.append(" from xg_xsxx_cxpy_pysb_jg t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh " +
				  " left join xsxx_cxdjdmb t3"+
				  " on t.pj = t3.cxdjdm "+
				" left join xqdzb t2 on t.xq = t2.xqdm " +
				") t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(CxddJgForm.class);
		super.setKey("xsid");
		super.setTableName("xg_xsxx_cxpy_pysb_jg");
	}
	
	//��ȡ���еȵ�����
	public List<HashMap<String, String>> getCxdjList(String cxdj){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameterlist = new ArrayList<String>();
		if(StringUtils.isNotNull(cxdj)){
			sql.append(" select * from  xsxx_cxdjdmb where cxdjdm = ?");
			parameterlist.add(cxdj);
		}else{
			sql.append(" select * from  xsxx_cxdjdmb ");
		}
		
		return dao.getListNotOut(sql.toString(), parameterlist.toArray(new String[]{}));
	}
	
	public String getbjdm(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjdm from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh},"bjdm" );
	}
	
	/**
	 * 
	 * @����: �۲�����ɼ�Map
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getZcfsList(CxddJgForm t,String xmmc) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fs,t1.bjpm, t2.xqmc, t1.xn || ' ' || t2.xqmc zczq, t3.xmmc from xg_zhcp_zcfsb t1 left join xqdzb t2 ");
		sql.append(" on t1.xq=t2.xqdm left join xg_zhcp_zcxmb t3 on t1.xmdm=t3.xmdm where t1.xh = ? and t1.xn = ? and t1.xq = ? and t3.xmmc = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getXh(),t.getXn(),t.getXq(),xmmc});
	}
	
	/**
	 * @description	�� ��ȡ�۲����(����ҽҩ)
	 * @author 		�� ������1282��
	 * @date 		��2017-11-10 ����02:28:16
	 * @param t
	 * @param xmmc
	 * @return
	 */
	public HashMap<String,String> getZcfsTotal(CxddJgForm t,String xmmc) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rank() over(partition by a.bjdm,a.xn,a.xq order by a.fs desc) bjpm from (select a.xh, b.xn, b.xq, a.bjdm, round(((avg(nvl(b.cj,0))*0.6)+(nvl(c.dyfs,0)*0.4)),2) fs");
		sql.append(" from view_xsbfxx a");
		sql.append(" left join cjb b on a.xh = b.xh");
		sql.append(" left join (select a.xn,a.xq,a.xh,a.fs dyfs from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm where b.xmmc = ? and b.xn = ? and b.xq = ?) c");
		sql.append(" on a.xh = c.xh and b.xn = c.xn and b.xq = b.xq");
		sql.append(" group by a.xh,b.xn,b.xq,a.bjdm,c.dyfs)a");
		sql.append(" where a.xn = ? and a.xq = ? and xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[] {xmmc,t.getXn(),t.getXq(),t.getXn(),t.getXq(),t.getXh()});
	} 
	
	/**
	 * @description	�� ��ȡƽ���ɼ�������ʡ����ҽҩ�ߵ�ְҵѧУ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-10 ����09:57:29
	 * @param t
	 * @return
	 */
	public HashMap<String,String> getPjcjAndPm(CxddJgForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rank() over(partition by a.bjdm,a.xn,a.xq order by a.fs desc) bjpm from (select a.xh, a.xn, a.xq, b.bjdm, round(nvl(avg(nvl(cj,0)), 0), 2) fs");
		sql.append(" from cjb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" group by a.xh,a.xn,a.xq,b.bjdm)a");
		sql.append(" where a.xh = ? and a.xn = ? and a.xq = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getXh(),t.getXn(),t.getXq()});
	} 
	
	/**
	 * 
	 * @����:��ȡѧ��������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:05:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,bjmc,bjdm from view_xsjbxx where xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:08:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjrs(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from view_xsbfxx t where sfzx = '��У' and bjdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{bjdm}, "cnt");
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��ѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:20:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXnxqStr(String xn,String xq){
		String xqmc = Base.getXqmcForXqdm(xq);
		xn = xn.replace("-", "/");
		return xn+xqmc;
	}
	
	/**
	 * 
	 * @����:��ȡ�ɼ�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-19 ����05:21:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjList(CxddJgForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select kcmc,cj from view_zhhcjb where xn = ? and xq = ? and  xh = ? order by kcmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{t.getXn(),t.getXq(),t.getXh()});
	}
	
	/**
	 * 
	 * @����: ��ȡ�������List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:17:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjjgList(CxddJgForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xmmc, t.xn || t1.xqmc xnxq, t.sqsj");
		sql.append(" from xg_pjpy_new_pjjgb t");
		sql.append(" left join xqdzb t1");
		sql.append(" on t.xq = t1.xqdm");
		sql.append(" where t.xh = ? and t.xn = ? and t.xq = ? ");
		return dao.getListNotOut(sql.toString(),new String[]{t.getXh(),t.getXn(),t.getXq()});
	}
	
	/**
	 * 
	 * @����: ��ȡΥ�ʹ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:40:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjcfList(CxddJgForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.cflbmc,t.CFSJ,t.XN || t1.xqmc xnxq,t.CFYYMC from xg_view_wjcf_wjcfb t");
		sql.append(" left join xqdzb t1");
		sql.append(" on t.XQ = t1.xqdm");
		sql.append(" where t.XH = ? and t.xn = ? and t.xq = ?");
		return dao.getListNotOut(sql.toString(),new String[]{t.getXh(),t.getXn(),t.getXq()});
	}
	
	/**
	 * @description	�� ����ѧ�꣬ѧ��ȡ�۲��ܷ�����
	 * @author 		�� ������1282��
	 * @date 		��2017-11-13 ����01:39:10
	 * @return
	 */
	public String getZhcjPm(String xh,String xn,String xq){		
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.bjpm from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm where b.xmmc like '%�۲��ܷ�%' and a.xn = ? and a.xq = ? and a.xh = ? ");
		return dao.getOneRs(sb.toString(), new String[]{xn,xq,xh}, "bjpm");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ�����ñ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����03:09:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteSzb() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_KX_CSSZ");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: �������ñ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����03:28:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runInsertSzb(CxddJgForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_KX_CSSZ(kxrq,kkrq,jfjzrq) values(?,?,?)");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{t.getKxrq(),t.getKkrq(),t.getJfjzrq()});
	}
	
	/**
	 * 
	 * @����: ��ȡ��ѧ�������ñ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����04:11:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getKxCsszb(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(to_date(kxrq, 'yyyy-mm-dd hh24:mi:ss'), 'mm') kxm,");
		sql.append(" to_char(to_date(kxrq, 'yyyy-mm-dd hh24:mi:ss'), 'dd') kxd,");
		sql.append(" to_char(to_date(kkrq, 'yyyy-mm-dd hh24:mi:ss'), 'mm') kkm,");
		sql.append(" to_char(to_date(kkrq, 'yyyy-mm-dd hh24:mi:ss'), 'dd') kkd,");
		sql.append(" to_char(to_date(jfjzrq, 'yyyy-mm-dd hh24:mi:ss'), 'mm') jfm,");
		sql.append(" to_char(to_date(jfjzrq, 'yyyy-mm-dd hh24:mi:ss'), 'dd') jfd,");
		sql.append(" kxrq,kkrq,jfjzrq");
		sql.append(" from XG_KX_CSSZ ");
		return dao.getMapNotOut(sql.toString(),new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡѧ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-9 ����05:30:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXfsj(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from rcsw_cwsjb where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	
}

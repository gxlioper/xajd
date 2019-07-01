/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-20 ����11:39:31 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ϰ��Ŀ�걨����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-20 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmsbjgDao extends SuperDAOImpl<XmsbjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XmsbjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XmsbjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfSztzByUser(user, "t", "sbbmdm", "sbr");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc, nvl(t6.xm,t7.xm) sbrxm,nvl(t8.num,0)ybjrs,decode(t1.csms,'1','����','2','����') csmsmc,");
		sql.append("case when '"+user.getUserName()+"' in (select yhm from xg_sztz_glyb) then 'true' when sbr ='"+user.getUserName()+"' then 'true' else 'false' end sfkxg");
		sql.append(",decode (t9.jdnum,'','0',null,'0','1') jdsz");
		sql.append(" from xg_sztz_xmjg t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm left join yhb t6 on t1.sbr= t6.yhm left join xsxxb t7 on t1.sbr=t7.xh");
		sql.append(" left join(select count(xh)num,xmdm from XG_SZTZ_XS_SQJG where ylzd1 is not null group by xmdm)t8 on t1.xmdm=t8.xmdm ");
		sql.append(" left join (select count(1)jdnum,xmdm from xg_sztz_xm_jd group by xmdm) t9");
		sql.append(" on t1.xmdm = t9.xmdm");
		sql.append("  order by t1.sfrm desc,t1.xmkssj desc) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ��Ŀ�걨���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getXmsbjg(XmsbjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		if("13627".equals(Base.xxdm)){
			sql.append("select t1.*,case when t1.sfsljx='1' then '��' else '��' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc,t6.xm,a.mc bkgsmc ");
			sql.append("from xg_sztz_xmjg t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
			sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm left join yhb t6 on t1.sbr = t6.yhm left join XG_SZTZ_BKGS a on a.dm=t1.bkgs");
			sql.append(" where t1.jgid=?");
		}else{
			sql.append("select t1.*,case when t1.sfsljx='1' then '��' else '��' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc,t6.xm ");
			sql.append("from xg_sztz_xmjg t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
			sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm left join yhb t6 on t1.sbr = t6.yhm ");
			sql.append(" where t1.jgid=?");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { t.getJgid() });
	}


	/**
	 *�ж���Ŀ�걨����Ƿ��Ѵ���
	 */
	public boolean isHaveSbjg(XmsbjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_sztz_xmjg where xn=? and xq=? and xmmc=?");
		if(null!=model.getJgid()){
			sql.append(" and jgid<>'"+model.getJgid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getXq(),model.getXmmc()}, "num");
		return Integer.parseInt(num)>0;
	}


	/**
	 * 
	 * ɾ����Ŀ�걨���
	 */
	public boolean delXmsbjg(String jgid) throws Exception {
		String sql = "delete from xg_sztz_xmjg where jgid=?";
		return dao.runUpdate(sql, new String[] { jgid });
	}
	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from( select xmdm from xg_sztz_xsxmsq where xmdm in("+values+")");
		sql.append(" union all select xmdm from xg_sztz_xs_sqjg where xmdm in("+values+"))");
		int result = dao.getOneRsint(sql.toString());
		return result > 0;
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-14 ����03:54:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean setRmxm(List<String[]> params) throws Exception {
		String sql = "update xg_sztz_xmjg set sfrm=? where jgid=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:��ȡ��Ŀ����ѧ���б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-16 ����05:19:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSqxsList(String xmdm,String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bjmc,t2.xm,case when t1.ylzd2='1' then '��' else '��' end sfqqmc,t4.jxmc,(nvl(to_number(t4.fjxf),0)+to_number(t3.jcxf))zxf");
		sql.append(" from xg_sztz_xs_sqjg t1 left join view_xsjbxx t2 on t1.xh=t2.xh left join xg_sztz_xmjg t3 on t1.xmdm=t3.xmdm");
		sql.append(" left join xg_sztz_xm_jx t4 on t1.ylzd1=t4.jgid where t1.xmdm=?");
		if (!StringUtil.isNull(xh)) {
			sql.append(" and (t1.xh like '%'||'" + xh + "'||'%' or t2.xm like '%'||'" + xh + "'||'%') ");
		}
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}
	

	@Override
	protected void setTableInfo() {
		super.setClass(XmsbjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_sztz_xmjg");

	}
	/**
	 * 
	 * @����:��ȡѧ����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-17 ����10:27:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(XmsbjgForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhArr();
		StringBuilder sql = new StringBuilder("select * from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where 1=1 ");
		if(xhs.length>0){
			sql.append("  and a.xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhs[i]+"' ");
			}
			sql.append(")");
			}
		sql.append(" and a.xh in(select xh from XG_SZTZ_XS_SQJG) ");
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	public List<HashMap<String,String>> getCyxyList(String xmdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select *��from XG_SZTZ_XMCYXYB where xmdm=?");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	public String getXylb(String sbbmdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select bmlb��from zxbz_xxbmdm where bmdm=?");
		return dao.getOneRs(sql.toString(), new String[]{sbbmdm}, "bmlb");
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ�׶�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-21 ����11:39:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmjdSzList(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, nvl(t1.num, 0) num");
		sql.append(" from xg_sztz_xm_jd t");
		sql.append(" left join (select jdid, count(1) num from xg_sztz_xm_jdwh group by jdid) t1");
		sql.append(" on t.jdid = t1.jdid");
		sql.append(" where t.xmdm = ? order by to_number(jdsx)");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * 
	 * @����:����׶�����,����������ʽ���������������ԣ����ҿ��Լ��������ݿ⽻������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-22 ����09:07:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param jdid
	 * @param jdmc
	 * @param jdf
	 * @param jdsx
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJdsz(String xmdm,String[] jdid,String[] jdmc,String[] jdf,String[] jdsx) throws Exception{
		ArrayList<String> sqlArr = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_sztz_xm_jd where xmdm = '"+xmdm+"'");
		sqlArr.add(sql.toString());
		if(jdmc != null && jdmc.length > 0)
		for (int i = 0; i < jdmc.length; i++) {
			sql = new StringBuilder();
			if(StringUtils.isNotNull(jdid[i])){
				sql.append("insert into xg_sztz_xm_jd(jdid,jdmc,xmdm,jdf,jdsx) values(");
				sql.append("'"+jdid[i]+"',");
				sql.append("'"+jdmc[i]+"',");
				sql.append("'"+xmdm+"',");
				sql.append("'"+jdf[i]+"',");
				sql.append("'"+jdsx[i]+"'");
				sql.append(")");
			}else{
				sql.append("insert into xg_sztz_xm_jd(jdmc,xmdm,jdf,jdsx) values(");
				sql.append("'"+jdmc[i]+"',");
				sql.append("'"+xmdm+"',");
				sql.append("'"+jdf[i]+"',");
				sql.append("'"+jdsx[i]+"'");
				sql.append(")");
			}
			sqlArr.add(sql.toString());
		}
		int[] result = dao.runBatch(sqlArr.toArray(new String[]{}));
		return result != null && result.length >0 ? true:false;
	}
	
	/**
	 * 
	 * @����:��ȡ�μ�������Ŀ����Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-8 ����01:59:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmcyrs(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,");
		sql.append(" t2.bjmc,");
		sql.append(" t2.xm,");
		sql.append(" case");
		sql.append(" when t1.sfqq = '1' then");
		sql.append(" '��'");
		sql.append(" else");
		sql.append(" '��'");
		sql.append(" end sfqqmc,");
		sql.append(" t4.jxmc,");
		sql.append(" (nvl(to_number(t4.fjxf), 0) + to_number(t3.jcxf)) zxf");
		sql.append(" from xg_sztz_jcftz_jg t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xg_sztz_xmjg t3");
		sql.append(" on t1.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_xm_jx t4");
		sql.append(" on t1.jxdm = t4.jgid");
		sql.append(" where t1.xmdm = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
		
	}
}

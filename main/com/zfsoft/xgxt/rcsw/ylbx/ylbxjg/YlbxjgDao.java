/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:02:27 
 */  
package com.zfsoft.xgxt.rcsw.ylbx.ylbxjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ�����
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2015-1-21 ����11:02:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YlbxjgDao extends SuperDAOImpl<YlbxjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1, ");
		sql.append(" t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
		sql.append(" t5.xqmc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc, ");
		sql.append(" nvl2(t3.rddc, '��', '��') sfkns ");
		sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
		sql.append(" from xg_rcsw_ylbx_jgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		if("10335".equals(Base.xxdm)){
			sql.append(" left join view_knsjgb_fqxrd t3 on t1.xh = t3.xh ");
		}else{
			sql.append(" left join xg_xszz_new_knsjgb t3 on t1.xh = t3.xh and (t1.xn = t3.xn and decode(t3.xq,'on',nvl(t1.xq,1),t3.xq) = nvl(t1.xq,1)) ");
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getXjbxmdList(YlbxjgForm t, User user)
	throws Exception {
	//���ɸ߼���ѯ�������������ֵ 
	String searchTj = SearchService.getSearchTj(t.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
	String[] inputV = SearchService.getTjInput(t.getSearchModel());
	StringBuilder sql = new StringBuilder();
	sql.append(" select a.* from ( ");
	sql.append(" select t1.zd2,(to_number(substr(t1.xn,1,4))+to_number(nvl(t1.zd2,0)))tbjzsj,(to_number(nvl(t2.nj,0)) + to_number(nvl(t2.xz,0))) njxz,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1, ");
	sql.append(" t2.zzmmmc,t2.xm,t2.xb,t2.xz,t2.ssbhzd,t2.jtdz,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
	sql.append(" t5.xqmc ");
	sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc, ");
	sql.append(" nvl2(t3.rddc, '��', '��') sfkns ");
	sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
	sql.append(" from xg_rcsw_ylbx_jgb t1 ");
	sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
	sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
	if("10335".equals(Base.xxdm)){
		sql.append(" left join view_knsjgb_fqxrd t3 on t1.xh = t3.xh ");
	}else{
		sql.append(" left join xg_xszz_new_knsjgb t3 on t1.xh = t3.xh and (t1.xn = t3.xn and decode(t3.xq,'on',nvl(t1.xq,1),t3.xq) = nvl(t1.xq,1)) ");
	}
	sql.append(" ) a where 1 = 1 and a.njxz>a.tbjzsj and  a.tbjzsj is not null");
	sql.append(searchTjByUser);
	sql.append(searchTj);
	return getPageList(t, sql.toString(), inputV);
}
	/** 
	 * ����
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxjgForm t, User user)
		throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1, ");
		sql.append(" t2.mzmc,t2.ssbhzd,t2.jgmc,t2.sydmc,t2.hkszdmc,t2.jtdz,t2.jtyb,t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj,t2.zd5 hkxzmc, ");
		sql.append(" t5.xqmc,t2.zd5 hjxz ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_ylczbzbsb where dm=t1.zd1) zd1mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd2) zd2mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd3) zd3mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd4) zd4mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" ,(select yhmc from dmk_yh where yhdm=t1.zd7) zd7mc ");
		sql.append(" ,nvl2(t3.rddc, '��', '��') sfkns ");
		sql.append(" ,(select mc from XG_RCSW_YLBX_CBLXB where dm=t1.zd12) zd12mc ");
		sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
		if("14008".equals(Base.xxdm)) {
			sql.append(",z.grsjje,z.mzzzje");
		}
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm ");
		}
		sql.append(" from xg_rcsw_ylbx_jgb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		if("10335".equals(Base.xxdm)){
			sql.append(" left join (select a.xh, a.rddc, a.xn, a.xq from view_knsjgb_fqxrd a) t3 on t1.xh = t3.xh ");
		}else{
			sql.append(" left join (select a.xh, a.rddc, a.xn, a.xq from xg_xszz_new_knsjgb a) t3 on t1.xh = t3.xh and t1.xn = t3.xn and t1.xq = t3.xq ");
		}
		if("14008".equals(Base.xxdm)) {
			sql.append(" left join xg_rcsw_ylbx_ylczbzbsb z on t1.zd1 = z.dm ");
		}	
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_ylbx_jgb");
		super.setKey("jgid");
	}
	
	/**
	 * 
	 * @����:�ظ����ж�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����10:37:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(YlbxjgForm model) {
		String jgid = "-1";
		if(!StringUtil.isNull(model.getJgid())){
			jgid = model.getJgid();
		}
		String zd = "xn";
		String[] arr = new String[] { model.getXn(),model.getXh(),jgid};
		if("14073".equals(Base.xxdm)){
			zd = "zd5";
			arr = new String[] { model.getZd5(),model.getXh(),jgid};
		}
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_jgb where "+zd+" = ? and xh = ? and jgid <> ? ");
		String num = dao.getOneRs(sql.toString(), arr, "num");
		return num;
	}
	
	
	/**
	 * 
	 * @����:����ͨ�������ݲ������޸�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����10:44:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_ylbx_jgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
		
	}
	
	
	/*
	 * ��������
	 * 
	 */
	public HashMap<String, String> getBbjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_ylbx_jgb a");
		sb.append(",xsxxb xsxx where a.xh=xsxx.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}
	
	
	/**
	 * 
	 * @����:���������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-1-22 ����04:58:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	
	public Map<String, String> viewOneYlbxjgList(String jgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.filepath,t1.filepath filepathtd,t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30,t1.jgid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.czyh,t1.sjly,t1.sqid,t1.zd1 ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_ylczbzbsb where dm=t1.zd1) zd1mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd2) zd2mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd3) zd3mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd4) zd4mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" ,(select yhmc from dmk_yh where yhdm=t1.zd7) zd7mc ");
		sql.append(",(select mc from XG_RCSW_YLBX_BXGSB where dm=t1.zd8) zd8mc");
		sql.append(" ,(select mc from XG_RCSW_YLBX_CBLXB where dm=t1.zd12) zd12mc, ");
		if("12688".equals(Base.xxdm)){
			sql.append(" t1.bxjg,t1.lpsx,  ");
		}
		sql.append(" t2.xymc,t2.zymc,t2.nj,t2.bjmc,t2.xm,t2.xb from xg_rcsw_ylbx_jgb t1 left join view_xsxxb t2 on t1.xh=t2.xh");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{jgId});
	}
	
	
	/**
	 * 
	 * @����: ȡҽ�ƽɷѵ��κ���Ա����Թ����������ӡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-26 ����10:40:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJfdcRylbInfo(String xh) {
		
		String sql = " select t2.mc dcmc, t3.mc rylb from xg_rcsw_ylbx_jgb t1 left join xg_rcsw_ylbx_ylczbzbsb t2 on t1.zd1 = t2.dm left join xg_rcsw_ylbx_bsddyljgb t3 on t1.zd2 = t3.dm where t1.xh = ? ";
	
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��ѯ�����ѧ����Ƭ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-2-18 ����10:56:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXszpList(YlbxjgForm t, User user) 
		throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder tableSql = new StringBuilder();
	
		tableSql.append("select * from (select a.*, c.XM, c.NJ, c.XYDM, c.ZYDM, c.BJDM,c.sfzh,case when b.zp is null then '��' else '��' end sfdr,case ");
		tableSql.append("when b.xszp is null then '��' else '��' end xssfdr, rownum r from (");
		tableSql.append("select a.* from xg_rcsw_ylbx_jgb a ");
		tableSql.append("where 1=1 ");
		
		tableSql.append(" ) a left join xszpb b on a.xh = b.xh left join view_xsbfxx c on a.xh = c.xh) a where 1=1  and sfdr='��' ");
		tableSql.append(searchTjByUser);
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ");
		String[] outputValue=new String[]{"xh", "xm", "zd30" , "sfzh" };
		return dao.getList(tableSql.toString(), inputV, outputValue);
	}

}

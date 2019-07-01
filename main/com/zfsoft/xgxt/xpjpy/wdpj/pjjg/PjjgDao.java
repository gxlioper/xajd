/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:33:34 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:33:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjjgDao extends SuperDAOImpl<PjjgModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_pjjgb");
		super.setKey("id");
		super.setClass(PjjgModel.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PjjgModel t)
			throws Exception {
	
		
		return null;
		// TODO �Զ����ɷ������
	}

	/**
	 * �����������ѯ
	 */
	public List<HashMap<String, String>> getPageList(PjjgModel t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		SearchModel searchModel = t.getSearchModel();
		String[] xntj = searchModel.getSearch_tj_xn();
		String[] xqtj = searchModel.getSearch_tj_xq();

		sql.append("select * from (select nvl(x1.sydm,x2.sydm1) sydm,nvl(x1.symc,x2.symc1) symc, f.cpzdm,cp.cpzmc, t2.yhmc yhmc2,t1.xn||' '||t1.xqmc pjzq,case when t11.xh is null then '��' else '��' end sfwtgg ");
		sql.append(" ,t1.id,t1.lxdm,t1.XH,t1.XM,t1.XB,t1.NJ,t1.XN,t1.XQ,t1.XQMC,t1.SQSJ,t1.XZDM,t1.XYDM,t1.XYMC,t1.ZYDM,t1.ZYMC,t1.ZYBJ,t1.ZYBJMC,nvl(cm.bjdm,x2.bjdm) bjdm,nvl(vna.bjmc,x2.bjmc) bjmc,t1.XMJE,t1.XMLXMC,t1.XMMC,t1.XZ,t1.MZ,t1.MZMC,t1.XMXZMC ");
		sql.append(" from VIEW_NEW_DC_PJPY_PJJG t1 ");
		sql.append(" left join xg_pjpy_new_cpmdb cm on cm.xh = t1.xh and cm.xn = t1.xn and cm.xq = t1.xq ");
		sql.append(" left join XG_ZHCP_FSTJJLB f on f.bjdm = cm.bjdm and f.xn = t1.xn and f.xq = t1.xq ");
		sql.append(" left join XG_ZHCP_CPZB cp on cp.cpzdm = f.cpzdm");
		sql.append(" left join view_njxyzybj_all vna on cm.bjdm = vna.bjdm ");
		sql.append(" left join XG_XTWH_SYBJGLB x on cm.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x1.sydm = x.sydm ");
		sql.append(" left join view_xsjbxx x2 on t1.xh = x2.xh ");
		sql.append(" left join dmk_yh t2 on t1.shgxzw2=t2.yhdm left join(select * from xg_pjpy_new_tsxsb where lxdm='6') t11 on t1.xn=t11.xn and t1.xq=t11.xq and t1.xh=t11.xh)t1 where 1=1 ");

		//���ֽ�ѧ��ͱ��ý���
		if("2".equals(t.getXzdm())){
			sql.append(" and xzdm='2' ");
		}else{
			sql.append(" and xzdm <> '2' ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by xn,xq,sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:��ȡͬ��רҵ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-29 ����05:08:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTjzyrs(String xh , String xn ,String xq){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		params.add(xh);
		
		sql.append("select a.xh, a.xn, a.xq, c.zyrs").
			append("  from xg_pjpy_new_cpmdb a").
			append("  left join view_njxyzybj_all b").
			append("    on a.bjdm = b.bjdm").
			append("  left join (").
			append("    select count(xh) zyrs,zydm,nj,xn,xq from").
			append("    (select distinct t2.nj,t2.zydm,t1.xh,t.xn,t.xq from xg_zhcp_fstjjlb t").
			append("    left join xg_pjpy_new_cpmdb t1").
			append("      on t.bjdm = t1.bjdm and t.xn = t1.xn and t.xq = t1.xq").
			append("    left join view_njxyzybj_all t2").
			append("      on t1.bjdm = t2.bjdm").
			append("    where t.tjzt = '1' and t.bjdm is not null) group by xn,xq,nj,zydm ").
			append("  ) c ").			
			append("    on b.nj = c.nj").
			append("   and b.zydm = c.zydm").
			append("   and a.xn=c.xn").
			append("   and a.xq=c.xq").
			append(" where a.xh = ?");
		
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
			
		return dao.getMap(sql.toString(), params.toArray(new String[params.size()]), new String[]{"zyrs"}).get("zyrs");
	}

	
	public String getTbjrs(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) bjrs from xsxxb a where a.bjdm = (select bjdm from xsxxb where xh = ? )");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "bjrs");
	}
	
	/**
	 * �������㽱ѧ���㽭��ѧ��
	 */
	public boolean scyxjxj(User user) throws Exception {
		return dao.runProcuder("{call XG_PJ_PROC_AUTOGENERATEJXJ(?,?)}", new String[]{ user.getUserDep(), user.getUserName() });
	}
	
	/** 
	 * @����:���Ӳ���Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ��,��Ŀ���ƣ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-7 ����11:12:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForSave(PjjgModel model) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_pjpy_new_pjjgb where xh=? and xn=? and xmmc=? ");
		String[] inputV = null;
		if(model.getXq() == null) {
			sql.append(" and xq is null ");
			inputV = new String[] { model.getXh(),
					model.getXn(), model.getXmmc().trim() };
		} else {
			sql.append(" and xq=? ");
			inputV = new String[] { model.getXh(),
					model.getXn(), model.getXmmc().trim(), model.getXq() };
		}
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-7 ����11:15:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForUpdate(PjjgModel model) {

		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_pjpy_new_pjjgb where xh=? and xn=?  and xmmc=?  and id<>?");
		String[] inputV = null;
		if(model.getXq() == null) {
			sql.append(" and xq is null ");
			inputV = new String[] { model.getXh(), model.getXn(),
					model.getXmmc().trim(), model.getId()};
		} else {
			sql.append(" and xq=? ");
			inputV = new String[] { model.getXh(), model.getXn(),
					model.getXmmc().trim(), model.getId(), model.getXq()};
		}
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/** 
	 * @����:������������鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-8 ����02:37:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String, String> getOnePjxmjgList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bjdw,a.ylzd1,a.ylzd2,a.ylzd3,a.ylzd4,a.ylzd5,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc, ");
		sql.append(" case when a.lxdm not in (select xmlxdm from xg_pjpy_new_xmlx) then lxdm else ");
		sql.append(" (select xmlxmc from xg_pjpy_new_xmlx b where a.lxdm=b.xmlxdm) end xmlxmc, ");
		sql.append(" case when a.xzdm not in (select xmxzdm from xg_pjpy_new_xmxz) then xzdm else ");
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz b where a.xzdm=b.xmxzdm) end xmxzdm, ");
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz b where a.xzdm=b.xmxzdm)xmxzmc, ");
		sql.append("  a.xmmc,a.xmje,a.sqsj,a.sqly,a.cpnj,a.cpxymc,a.cpzymc,a.cpbjmc, ");
		//����ҽҩ�ߵ�ר��ѧУ���Ի�
		if("70002".equals(Base.xxdm)){
			sql.append(" a.djjl,");
		}
		sql.append(" a.cpxydm,a.cpzydm,a.cpbjdm from xg_pjpy_new_pjjgb a where id = ? ");

		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}

//	/** 
//	 * @����:��ѯ��Ŀ���롢����List
//	 * @���ߣ�cq [���ţ�785]
//	 * @���ڣ�2013-8-14 ����09:37:45
//	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
//	 * @return
//	 * List<HashMap<String,String>> �������� 
//	 * @throws 
//	 */
//	public List<HashMap<String, String>> getxmmc() {
//		
//		StringBuilder sql = new StringBuilder();
//		
//		sql.append("  select xmdm, xmmc from xg_pjpy_new_pjxmb ");
//		sql.append(" where xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
//		
//		return dao.getListNotOut(sql.toString(), null);
//	}

	/** 
	 * @����: ���꼶ѧԺ��ѯָ��������Ŀ�Ļ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:22:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByNjxy(String xmdm, String xn,
			String xq) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.nj,t3.xydm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.nj,t3.xydm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @����: ���꼶רҵ��ѯָ��������Ŀ�Ļ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:32:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByNjzy(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.nj,t3.zydm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.nj,t3.zydm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @����: ��ѧԺ��ѯָ��������Ŀ�Ļ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:33:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByXy(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.xydm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.xydm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @����: ������С���ѯָ��������Ŀ�Ļ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:34:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByCpz(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.cpzdm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join xg_zhcp_fstjjlb t3 on t2.bjdm=t3.bjdm and t1.xn=t3.xn and t1.xq=t3.xq");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.cpzdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @����: ���༶��ѯָ��������Ŀ�Ļ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:36:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByBj(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs,t3.bjdm from xg_pjpy_new_pjjgb t1");
		sql.append(" left join xg_pjpy_new_cpmdb t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		sql.append(" group by t3.bjdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @����: ��ȫУ��ѯָ��������Ŀ�Ļ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:37:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzmeByQx(String xmdm, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) tgrs from xg_pjpy_new_pjjgb t1");
		sql.append(" where t1.xn=? and t1.xq=? and t1.xmdm=?");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	/** 
	 * @����:
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-14 ����03:33:44
	 * @�޸ļ�¼: 
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select xydm,xn,xq,count(*) rs from (  ");
		sql
				.append(" select x.*,y.xydm from xg_pjpy_new_pjjgb x,view_xsjbxx y where  x.xh=y.xh and x.xmmc=? ");
		sql.append(" ) group by xydm,xn,xq order by xn desc,xq desc");
		String[] inputValue = { xmmc };
		List<HashMap<String, String>> result = dao.getListNotOut(
				sql.toString(), inputValue);
		return result;
	}

	
	/**
	 * 
	 * @����: ����ѧ����ѯ�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-3 ����02:36:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getHjqkByXh(String xh){
		
		StringBuilder sql =  new StringBuilder();
		
		sql.append("select t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.sqsj desc");
		
		return dao.rsToListNotOut(sql.toString(), new String[]{xh});
	} 
	/**
	 * 
	 * @����: ����ѧ����ѯ�����
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-9-3 ����02:36:39
	 * @�޸ļ�¼: ����-20160627-ɽ���ƾ���ѧ��������ֻ��ȡ����
	 * @param xh
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.bjdw,(select xmxzmc from xg_pjpy_new_xmxz b where t1.xzdm=b.xmxzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj" , "sqsjs"});
	}
	
	public List<HashMap<String, String>> getHjqkByXhXnMap(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.bjdw,(select xmxzmc from xg_pjpy_new_xmxz b where t1.xzdm=b.xmxzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? and t1.xn=? order by t1.xn desc,t1.xq desc");
		return dao.getArrayList(sql.toString(), new String[]{xh,xn}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj" , "sqsjs"});
	}
	/**
	 * ����ѧ����ѯѧ�������������ѧ����飩
	 */
	public List<HashMap<String, String>> getPjjgGroupByXn(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append(" select a.xn,replace(a.xmmcsTemp,';','��') xmmcs from ( ");
		sql.append(" select xn,WM_CONCAT(xmmc) over (partition by xn order by xn) xmmcsTemp, ");
		sql.append(" row_number() over (partition by xn order by xn desc) rn ");
		sql.append(" from ( ");
		sql.append(" select t1.xn,t2.xqmc,t1.xmmc ");
		sql.append(" from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append(" on t1.xq=t2.xqdm where t1.xh=? order by t1.xn asc,sqsj asc ");
		sql.append(" ) a ");
		sql.append(" ) a where rn=1 ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getHjqkList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc,t1.xn||' '||t2.xqmc pjzq ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ,t1.sqsj desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	
	
	/**
	 * 
	 * @����:��ȡƽ���ɼ�
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2013-11-21 ����09:33:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param Map
	 * @return
	 * Integer �������� 
	 */
	public Map<String, String> getBjgcjNum(String xh,String xn,String xq){
		StringBuilder sql =  new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		sql.append("select count(*) num from view_zhhcjb  where cj <60 and xh=?");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ?");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?");
			params.add(xq);
		}
		
		return dao.getMap(sql.toString(), params.toArray(new String[params.size()]), new String[]{"num"});
	}
	/**
	 * 
	 * @����:��ѯ����ʦ�� ��ѧ���Ի�֤������¼
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-3 ����01:57:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZsbm(PjjgModel model){
		StringBuffer sql= new StringBuffer();
		sql.append("select substr(ylzd1,length(ylzd1)-3,4) zsbm from xg_pjpy_new_pjjgb")
		.append(" where cpnj=? and cpxydm=? and xn=? and ylzd1 is not null order by zsbm desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getCpnj(),model.getCpxydm(),model.getXn()});
	}
	/**
	 * 
	 * @����:������Ŀ���ƻ�ȡ��Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-3 ����03:23:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXmdm(PjjgModel model){
		StringBuffer sql= new StringBuffer();
		sql.append("select xmdm from xg_pjpy_new_pjxmb where xmmc=?");
		return dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "xmdm");
	}
	
	/**
	 * 
	 * @����:�ع������������¼
	 * @���ߣ�945
	 * @���ڣ�2013-12-5 ����09:41:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delJgb(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{ywid};
		sql.append("delete from ");
		sql.append(" xg_pjpy_new_pjjgb ");
		sql.append(" where sjly='1' and ");
		sql.append(" lylcywid = ? ");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * @����: ��ѯ����֤��ģ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-21 ����10:13:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @param user
	 * @return
	 * HashMap �������� 
	 */
	public HashMap<String, String> cxRyzs(String csdm, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.csdm,replace(decode(b.csz, null, a.csz, b.csz),'LODOP.PRINT_INITA(0,0,794,1123,\"��ӡ\");','') csz from ");
		sql.append(" (select csdm,csz from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = 'public') a left join ( ");
		sql.append(" select csdm,csz from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = ?) b on (a.csdm=b.csdm) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{csdm, csdm, user.getUserName()});
	}
	
	/**
	 * @����: ��������֤��ģ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-21 ����10:13:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @param csz
	 * @param user
	 * @return
	 * boolean �������� 
	 */
	public boolean bcRyzs(String csdm, String csz, User user) throws Exception{
		String deleteSql = " delete from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = ? ";
		dao.runDelete(deleteSql, new String[]{csdm, user.getUserName()});
		
		String insertSql = " insert into xg_pjpy_new_ryzsmbszb(csdm,zgh,csz) values(?,?,?) ";
		return dao.runUpdate(insertSql, new String[]{csdm, user.getUserName(), csz});
	}
	
	/**
	 * 
	 * @����: ����ѧ�Ų�ѯ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-25 ����05:19:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJxSum(String xh){
		
		String sql = "select count(1) js,sum(xmje) zje from xg_pjpy_new_pjjgb t1 where t1.xh = ?";
		
		return dao.getMapNotOut(sql, new String[]{xh});
			
	}
	/**
	 * @����: �㽭��ѧ������ܽ����ʾ�����Ի���
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-6-12 ����10:01:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getPjzje(String xh){
		String sql = " select sum(jjze) pjzje from xg_pjpy_new_jxjhzb where xh = ? ";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	/**
	 * 
	 * @����:��ȡ�༶����������Ա����������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-20 ����04:10:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBjxx(String bjdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from(select  count(a.xh) bjrs from view_xsxxb a where a.bjdm=?),");
		sql.append("(select wm_concat(c.xm) fdyxm from fdybjb b left join");
		sql.append(" yhb c on b.zgh=c.yhm where b.bjdm=?),(select wm_concat(e.xm) bzrxm");
		sql.append(" from bzrbbb d left join yhb e on d.zgh=e.yhm where d.bjdm=?)");
		return dao.getMapNotOut(sql.toString(), new String[]{bjdm,bjdm,bjdm});
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-22 ����04:07:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPriJg(String[] values) {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("select b.xm, decode(b.xb, '1', '��', '2', 'Ů', b.xb) xb,b.xh,b.sfzh,b.rxrq,b.byny,c.xymc,a.xmmc,");
		sql.append("a.xn,(select xqmc from xqdzb t where a.xq=t.xqdm) xqmc,to_char(to_date(rxrq,'yyyy-mm-dd'),'yyyy') rxn, ");
		sql.append("to_char(to_date(rxrq,'yyyy-mm-dd'),'mm') rxy, ");
		sql.append("substr(nvl(byny,to_char(sysdate,'yyyy-mm-dd hh24')),0,4) byn,");
		sql.append("substr(nvl(byny,to_char(sysdate,'yyyy-mm-dd hh24')),6,2) byy ");
		sql.append("from xg_pjpy_new_pjjgb a left join xsxxb b on a.xh=b.xh ");
		sql.append("left join view_njxyzybj_all c on b.bjdm=c.bjdm where id in (");
		for (int i = 0; i < values.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(values[i]);
		}
		sql.append(")");
		sql.append("order by a.xh,a.xn,a.xq,a.xmmc ");

		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	//ɽ��������ҽ�����ҵ���Ƽ�����ѧ����Ϣ
	public List<HashMap<String, String>> getXsxxlist(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t.xm,t.bjmc,t.xh,t.zymc,t.xymc xy,");
		sql.append("decode(t.xb,'1','��','2','Ů','��','��','Ů','Ů') xb ");
		sql.append("from  view_xsjbxx t where t.xh in(");
		
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//ɽ��������ҽ��άѧ����ܻ�ȡѧ����Ϣ�Լ���Ŀ���list
	public List<HashMap<String, String>> getShjxjHzbxxList(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t1.xm,decode(t1.xb,'1','��','2','Ů','��','��','Ů','Ů') xb,t.xmje,t1.bjmc,t1.xymc xy,  ");
		sql.append("(select xxmc from xtszb) xxmc,t1.sfzh,t1.zymc, t1.xh,  t1.rxrq, (select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_pjpy_new_pjjgb t join view_xsjbxx t1 on t.xh = t1.xh where t.id in(");
		
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//ɽ��������ҽ��άѧ���ȡ���������ܽ�� map
	public HashMap<String, String> getshjxjTotal(String[] values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select count(*) zrs,sum(xmje) xmze from xg_pjpy_new_pjjgb where id in(");
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getMapNotOut(sql.toString(), values);
	}
	
	//ɽ��������ҽʡ��־��ѧ���ȡ��ϵ�绰 map
	public HashMap<String, String> getlxfs(String value){
		StringBuffer sql = new StringBuffer();
		sql.append("select lxdh from fdyxxb where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{value});
	}
	
	//ɽ��������ҽʡ������ѧ���ȡ�۲��������map
	public HashMap<String, String> getzccj(PjjgModel model){
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.fs, t1.xmdm, t1.bjpm   ");
		sql.append("from Xg_Zhcp_Zcxmb t, XG_ZHCP_ZCFSB t1  ");
		sql.append("  where t.xmdm = t1.xmdm  and t.xn = t1.xn    and t.xn = ? and t1.xh = ? and t.xmdm = ? and t.xq = 'on' and t.xq = t1.xq  ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXn(),model.getXh(),model.getXmdm()});
	}
	
	//ɽ�������ޱ�ҵ����ȡ�۲�ɼ���������
	public List<HashMap<String, String>> getzccjList(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct t1.xn  xnxq,t.fs,t.bjpm  from  XG_ZHCP_ZCFSB t,Xg_Zhcp_Zcxmb t1 ");
		sql.append(" where t.xn = t1.xn and t.xmdm = t1.xmdm and t.xq = t1.xq and t.xq = 'on'");
		sql.append(" and t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	//��άѧ���Ƽ�����ܵ���
	public List<HashMap<String, String>> getDclist(PjjgModel t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum bh,t2.yhmc yhmc2,t1.*,t1.xn||' '||t1.xqmc pjzq from VIEW_NEW_DC_PJPY_PJJG t1 left join dmk_yh t2 on t1.shgxzw2=t2.yhdm where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return dao.getListNotOut(sql.toString(), inputV);
		// TODO �Զ����ɷ������
	}
	/**
	 * 
	 * @����:��ȡ�����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-17 ����08:42:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSpxxInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.shyj ejshyj,b.shzt ejshzt,b.zd2 ejtjdcdm ,b.zd3 ertjdcmc, c.shyj sjshyj,c.shzt sjshzt,c.zd2 sjtjdcdm,c.zd3 sjtjdcmc from(select a.* from(select a.ywid,a.shyj yjshyj,a.shzt yjshzt,a.zd2 yjtjdc,row_number() over(partition by a.ywid order by a.shsj desc) rn from xg_xtwh_shztb a   where a.ywid=?)a where a.rn=3)a left join");
		sql.append("  (select b.* ,row_number() over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b  where b.ywid=?)b on a.ywid=b.ywid and b.rn=2");
		sql.append(" left join (select  c.*,row_number() over(partition by c.ywid order by c.shsj desc) rn from xg_xtwh_shztb c   where c.ywid=?)c on a.ywid=c.ywid and c.rn=1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{guid,guid,guid});
	}
	
	/**
	 * @�������������ʱ��������
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��8��11��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public HashMap<String, String> getSpxxInfo2ji(String ywid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.shyj ejshyj, b.shzt ejshzt, b.zd2 ejtjdcdm, b.zd3 ertjdcmc from ( ");
		sql.append("select a.* from (select a.ywid, a.shyj yjshyj, a.shzt yjshzt, a.zd2 yjtjdc, row_number() over(partition by a.ywid order by a.shsj desc) ");
		sql.append("rn from xg_xtwh_shztb a where a.ywid = ?) a where a.rn = 2) a left join (select b.*, row_number() ");
		sql.append("over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b where b.ywid = ?) b on a.ywid = b.ywid and b.rn = 1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{ywid,ywid});
	}
	
	
	public String getPjxxByXhXnXq(String xh,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,wm_concat(xmmc) xmmc from xg_pjpy_new_pjjgb where ");
		sql.append(" xh=? and xn=? and xq=? group by xh");
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "xmmc");
	}
	
	
	/**
	 * ��ý���Ի� ȡѧ�����޿����
	 */
	
	public HashMap<String, String> getBxk(String xh,String xn){
		
		String sql = "select count(1) count,nvl(round(sum(case when cj >=60 then '1' else '0' end),0),0) hg from view_zhhcjb where (kcxz like '%���޿�%' or kcxz like '%��ѡ��%') and xh = ? and xn = ? ";
		
		return dao.getMapNotOut(sql, new String[]{xh,xn});
	}
	
	public HashMap<String, String> getPm(String xh,String xn){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.*, round((select count(1)/2 zrs from xg_pjpy_new_cpmdb ");
		sql.append("where xn||xq||bjdm in (select xn||xq||bjdm from xg_pjpy_new_cpmdb ");
		sql.append("where xh = ? ) and xn = ? and xq <> 'on'),0) zrs from ( ");
		sql.append("select nvl(round(sum(case when a.xmmc='�۲��ܷ�' then b.bjpm else '0' end)/2,0),0) xnzfpm, ");
		sql.append("nvl(round(sum(case when a.xmmc='������' then b.bjpm else '0' end)/2,0),0) xnzypm ");
		sql.append("from xg_zhcp_zcxmb a left join xg_zhcp_zcfsb b on a.xmdm=b.xmdm ");
		sql.append("where a.xn = ? and a.xq <>'on' ");
		sql.append("and (a.xmmc ='�۲��ܷ�' or a.xmmc ='������') and xh = ? )a ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn,xn,xh});
	}

	/**
	 * 
	 * @����: ���ݴ�ѧ�����ǼǱ�ȡ����(ȡѧ��2��ѧ����������ߵ�)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2016-10-18 ����02:39:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCjPm(String xh,String xn){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xn, ");
		sql.append("       nvl(min(to_number(a.xnzfpm)),'') xnzfpm, ");
		sql.append("       nvl(min(to_number(a.xnzypm)),'') xnzypm, ");
		sql.append("       nvl(max(to_number(b.zrs)),'') zrs ");
		sql.append("  from (select a.xn, ");
		sql.append("               a.xq, ");
		sql.append("               min(case ");
		sql.append("                     when a.xmmc = '�۲��ܷ�' then ");
		sql.append("                      b.bjpm ");
		sql.append("                     else ");
		sql.append("                      null ");
		sql.append("                   end) xnzfpm, ");
		sql.append("               min(case ");
		sql.append("                     when a.xmmc = '������' then ");
		sql.append("                      b.bjpm ");
		sql.append("                     else ");
		sql.append("                      null ");
		sql.append("                   end) xnzypm ");
		sql.append("          from xg_zhcp_zcxmb a ");
		sql.append("          left join xg_zhcp_zcfsb b ");
		sql.append("            on a.xmdm = b.xmdm ");
		sql.append("         where a.xn = ? ");
		sql.append("           and a.xq <> 'on' ");
		sql.append("           and (a.xmmc = '�۲��ܷ�' or a.xmmc = '������') ");
		sql.append("           and xh = ? ");
		sql.append("         group by a.xn, a.xq) a ");
		sql.append("  left join (select xn, xq, count(1) zrs ");
		sql.append("               from xg_pjpy_new_cpmdb ");
		sql.append("              where xn || xq || bjdm in ");
		sql.append("                    (select xn || xq || bjdm ");
		sql.append("                       from xg_pjpy_new_cpmdb ");
		sql.append("                      where xh = ?) ");
		sql.append("              group by xn, xq) b ");
		sql.append("    on b.xn = a.xn ");
		sql.append("   and b.xq = a.xq ");
		sql.append(" group by a.xn ");

		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh,xh});
	}
	
	
	/**
	 * 
	 * @����: ������Ϣ������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-20 ����04:05:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh,String xn,String xq){
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.XH,a.XM,a.XB,nvl(c.CPXYDM, a.XYDM) XYDM,nvl(c.CPXYMC, a.XYMC) XYMC,nvl(c.CPZYDM, a.ZYDM) ZYDM,nvl(c.CPZYMC, a.ZYMC) ZYMC, ");
		sql.append("nvl(c.CPBJDM, a.BJDM) BJDM, nvl(c.CPBJMC, a.BJMC) BJMC,nvl(c.CPNJ, a.NJ) NJ, ");
		sql.append("a.SFZH,a.LXDH,a.XZ,a.DZYX,a.LXDZXX,a.SJHM,a.MM,a.SSBHZD,a.SSCHZD,a.SSBH,a.QSDH,a.SSCH,a.ZSRQ,a.ZSJZRQ, ");
		sql.append("a.SFXYQGZX,a.XGYX,a.YHKH,a.YKTH,a.XMPY,a.SYD,a.CSRQ,a.MZ,a.MZMC,a.ZZMM,a.ZZMMMC,a.CYM,a.SG,a.TZ,a.TC, ");
		sql.append("a.KSLB,a.RXFS,a.PYFS,a.PYCC,a.BZ,a.XSZP,a.WHCD,a.RXQDW,a.JTDH,a.JRGQTSJ,a.JRGCDSJ,a.JTCYGC,a.JG,a.JLCFJL, ");
		sql.append("a.JTDZ,a.JTYB,a.XX,a.JKZKDM,a.JKZK,a.AH,a.SFDK,a.JTJJQK,a.SHGXXM1,a.SHGXGX1,a.SHGXGZDW1,a.SHGXZW1,a.SHGXDWDH1, ");
		sql.append("a.SHGXSJHM1,a.SHGXXM2,a.SHGXGX2,a.SHGXGZDW2,a.SHGXZW2,a.SHGXDWDH2,a.SHGXSJHM2,a.JTQKJJ,a.BYXX,a.KH,a.RXRQ, ");
		sql.append("a.FDYXM,a.GKCJ,a.QQHM,a.HKXZ,a.HKSZD,a.ZYJB,a.SSYQ,a.SSYQMC,a.SSLD,a.JTDZS,a.JTDZX,a.SFZSB,a.SFZFX,a.ZJDM, ");
		sql.append("a.ZJMC,a.SFYBY,a.BYNY,a.ZW,a.XXFX,a.THBS,a.DYBJ,a.SHBJ,a.XZXM,a.XXSZD,a.XW,a.XWZSBH,a.XWZSXLH,a.BJYJL,a.ZSBH, ");
		sql.append("a.ZSXLH,a.ZYFX,a.ZYLB,a.FXZY,a.FXZYFX,a.BXXS,a.BXLX,a.XXXS,a.CSD,a.PYFX,a.DQSZJ,a.ZSJJ,a.KSH,a.ZSLB,a.GJ, ");
		sql.append("a.SFJH,a.CCQJ,a.BYZFFZTDM,a.BYZFFZTMC,a.XWZSXXDZ,a.JGS,a.JGSHI,a.JGX,a.RXNJ,a.NFBY,a.SFZC,a.DASFYL,a.DAYLYY, ");
		sql.append("a.YXDM,a.SFZZ,a.SFSF,a.SFDL,a.DXHWP,a.BYSJ,a.ZXWYYZDM,a.WYDJ,a.JSJDJ,a.YZBM,a.SHZW,a.JYPX,a.XMSJ,a.ZGZS, ");
		sql.append("a.LXDZ,a.JLJN,a.SYBZ1,a.SYBZ2,a.SYBZ3,a.XLDM,a.ZKZH,a.SFCJ,a.GRJL,a.XSLB,a.XSLBMC,a.XSLX,a.XSLXMC,a.SFBYS, ");
		sql.append("a.YHDM,a.YHMC,a.DAH,a.YLBXH,a.RXQDWDH,a.RXQDWDZ,a.RXQDWYB,a.GZBX,a.SFGAT,a.SFSSMZ,a.SFZD,a.HKSHEN,a.HKSHI, ");
		sql.append("a.HKXIAN,a.ZCSXHM,a.RXQWHCD,a.TBSJ,a.BXXZ,a.SFTB,a.SFYQRZS,a.QTYY,a.SFSFS,a.BYZH,a.SYDS,a.XJH,a.JRZZMMRQ,a.SFHQ, ");
		sql.append("a.CSDS,a.CSDSHI,a.CSDXIAN,a.ZD1,a.ZD2,a.ZD3,a.ZD4,a.ZD5,a.ZD6,a.SYDMC,a.YXMC,a.XWMC,a.ZXWYYZMC,a.XLMC,a.ZSLBMC, ");
		sql.append("a.PYFSMC,a.SFZBLX,a.XJLBDM,a.XJLB,a.XJLBMC,a.XJZTM,a.SFZX,a.ZYFXMC,a.SYDSMC,a.YDLBM,a.YDLBMC,a.SYDQMC,a.JTDZXX,a.JGMC,");
		sql.append("a.HKSZDMC,a.CSDMC,k.ldmc||k.qsh qsh from view_xsxxb a left join (select * from ( ");
		sql.append("select a.*,row_number()over(partition by xh,xn,xq order by sqsj desc) rn from xg_pjpy_new_pjjgb a where (xn,nvl(xq,'on')) ");
		sql.append("= (select xn,xq from xg_pjpy_new_csszb)) where rn =1 ) c ");
		sql.append("on a.xh=c.xh left join view_xg_gygl_new_cwxx k on a.xh=k.xh where rownum =1 and a.xh = ? ");
		
		if(!StringUtil.isNull(xn)){
			params.add(xn);
			sql.append(" and c.xn = ? ");
		}
		
		if(!StringUtil.isNull(xq)){
			params.add(xq);
			sql.append(" and c.xq = ? ");
		}
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * 
	 * @����:�Y�����ӌW��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-20 ����06:14:44
	 * @�޸ļ�¼: Meng.Wei-2017-03-05-nvl��ʱ��bjdmдΪbjmc��
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjXs(PjjgModel model, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from ( ");
		
		sql.append(" select a.XH,a.XM,a.XB,nvl(c.XYDM, a.XYDM) XYDM,nvl(c.XYMC, a.XYMC) XYMC, ");
		sql.append(" nvl(c.ZYDM, a.ZYDM) ZYDM,nvl(c.ZYMC, a.ZYMC) ZYMC, ");
		sql.append(" nvl(c.bjdm,a.bjdm) bjdm, nvl(c.BJMC, a.BJMC) BJMC,nvl(c.NJ, a.NJ) NJ, ");
		sql.append(" a.SFZH,a.LXDH,a.XZ,a.SJHM,a.RXRQ,x.sydm,x1.symc,a.zybj,a.zybjmc, ");
		sql.append(" a.YHKH,a.YHDM,a1.YHMC,a.CSRQ,a.MZ,a.MZMC,a.ZZMM,a.ZZMMMC ");
		sql.append(" from view_xsbfxx a left join dmk_yh a1 on a.yhdm = a1.yhdm ");
		sql.append(" left join XG_XTWH_SYBJGLB x on a.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" join (select a.xh,b.* from xg_pjpY_new_cpmdb a left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" where (xn,xq) in (select xn,xq from xg_pjpy_new_csszb)) c ");
		sql.append(" on a.xh=c.xh ) a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯ�ϰ�������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-18 ����11:09:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getHjqkListOld(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc,t1.xn||' '||t2.xqmc pjzq ");
		sql.append("from xg_pjpy_pjlsxxb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ,t1.hdsj desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	
	/**
	 * 
	 * @����:�����ȵ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-21 ����10:15:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDyddList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh,t1.xqmc,t1.pjjg,t1.xssx from xg_xsxx_dyddb t1 where t1.xh = ? order by xssx ");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	
	/**
	 * 
	 * @����: ������˼�������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-16 ����08:36:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmmc) {
		
		StringBuffer sql = new StringBuffer();	
		sql.append(" select t1.shyj from xg_xtwh_shztb t1 left join xg_pjpy_new_pjjgb t2 on t1.ywid = t2.id ");
		sql.append(" where xh = ? and xn = ? and xq = ? and xmmc = ? order by shsj asc ");
		
		String[] inputValue = { xh,xn,xq,xmmc };
		return dao.getListNotOut(sql.toString(), inputValue);
		
	}
	/**
	 * @����: ������ҵ��ѧѧ��������
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-5-11 ����09:30:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgList(String xh,String xn) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xn,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc,a.xmmc ");
		sql.append(" from xg_pjpy_new_pjjgb a ");
		sql.append(" where a.xh = ? and a.xn = ? order by xq");
		return dao.getListNotOut(sql.toString(), new String[] { xh,xn });
	}
	
	/**
	 * 
	 * @����: ȡ��ѧ���������н����̶�ȼ�������Ҫ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-27 ����09:19:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXmmcAllByPjjg(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct WM_CONCAT(replace(t1.xmmc,';',',')) over(partition by t1.xh order by t1.xh) xmmc ");
		sql.append(" from (select distinct xh, t1.xn || ' ' || WM_CONCAT(t1.xmmc) over(partition by t1.xh, t1.xn order by t1.xh, t1.xn) xmmc ");
		sql.append(" from (select xh, xn, xmmc from xg_pjpy_new_pjjgb where xh = ? ) t1) t1 ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xmmc");
	}
	
	/**
	 * 
	 * @����:���޿�ƽ���ɼ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-29 ����03:36:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getPjfRank(PjjgModel model,String xq,String kcxz){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum no,t.* from (select avg(decode(t.cj, '����', 60, t.cj)) pjf,min(decode(t.cj, '����', 60, t.cj)) zdf ");
		sql.append(", xh from cjb t where kcxz = ?  and xh in (select xh from xsxxb where bjdm = ? ) ");
		if(xq != null){
			sql.append(" and xq = ? ");
		}
		sql.append("  and xn = ? group by xh order by avg(decode(t.cj, '����', 60, t.cj)))t ) t1 where t1.xh = ? ");
		if(xq != null){
			return dao.getMapNotOut(sql.toString(), new String[]{kcxz,model.getCpbjdm(),xq,model.getXn(),model.getXh()});
		}else{
			return dao.getMapNotOut(sql.toString(), new String[]{kcxz,model.getCpbjdm(),model.getXn(),model.getXh()});
		}
	}
	
	/**
	 * 
	 * @����:�۲�ƽ���ɼ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-29 ����03:36:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZcPjfRank(PjjgModel model,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum zcpm,t.* from (select avg(t.fs) zcpjf ");
		sql.append(", xh from xg_zhcp_zcfsb t where  xh in (select xh from xsxxb where bjdm = ?) and xq = ? and xn = ? ");
		sql.append(" group by xh order by avg(t.fs))t ) t1 where t1.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getCpbjdm(),xq,model.getXn(),model.getXh()});
	}
	
	/**
	 * 
	 * @����:��ȡѧ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-29 ����05:14:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getDjksMap(String xh,String ksmc,String ksmc1){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum,t.* from ");
		sql.append("(select  djksmc,cj,xn sj from XSDJKSB where xh = ? and (djksmc like '%' || lower(?) || '%' or djksmc like ?  or djksmc like '%' || upper(?) || '%' )order by cj desc ) t");
		sql.append(" where rownum=1");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,ksmc,ksmc1,ksmc});
	}
	
	/**
	 * 
	 * @����:����ѧ��ȡ����ѧ���ڸ�ѧ��Ļ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-30 ����10:40:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgByxn(PjjgModel model){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum no, t.xmmc,to_char(to_date(t.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj,t.xmje from xg_pjpy_new_pjjgb t  ");
		sql.append("where t.xh = ? and  t.xn = ?  ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXn()});
	}
	
	public List<HashMap<String, String>> getXnXqlist(String xh,String lx){
		StringBuilder sql = new StringBuilder();
		if("bxk".equals(lx)){
			sql.append("select t.*,rownum no from ");
			sql.append("(select distinct xn,xq from cjb where xh = ? order by xn,xq) t");
		}else{
			sql.append("select t.*,rownum no from ");
			sql.append("(select distinct xn from xg_zhcp_zcfsb where xh = ? order by xn) t");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-30 ����04:08:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJsjdjkslist(String xh,String ksmc,String ksmc1){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct djksmc,cj,xn tgsj from XSDJKSB where cj in   ");
		sql.append("(select max(cj) from XSDJKSB t where xh = ?  and djksmc like '%'|| ? ||'%' or djksmc like '%' || ? || '%' group by djksmc) ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,ksmc,ksmc1});
	}
	
	public List<HashMap<String, String>>getHjrycssj(String xh,String xmmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) cs,xmmc,xn hjsj from xg_pjpy_new_pjjgb where xh = ? and xmmc like '%' || ? || '%'  group by xmmc,xn ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xmmc});
	}
	
	/**
	 * 
	 * @����:���޿�ƽ���ɼ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-6-29 ����03:36:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZyPjfRank(PjjgModel model,String kcxz){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum no,t.* from (select avg(decode(t.cj, '����', 60, t.cj)) pjf,min(decode(t.cj, '����', 60, t.cj)) zdf ");
		sql.append(", xh from cjb t where kcxz = ?  and xh in (select xh from xsxxb where zydm = ? ) ");
		sql.append("  and xn = ?  group by xh order by avg(decode(t.cj, '����', 60, t.cj)))t ) t1 where t1.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{kcxz,model.getCpzydm(),model.getXn(),model.getXh()});
	}
	
	/**
	 * 
	 * @����:��ȡרҵ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-1 ����02:55:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZyRs(String zydm){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) rs from xsxxb where zydm = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{zydm});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-1 ����05:21:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> ��������  
	 * @throws
	 */
	public List<HashMap<String, String>>  getzzxmjg(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum no,t.xmmc,t.je,to_char(to_date(t.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') sqsj from xg_xszz_new_zzxmjgb t where xn = ? and xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xh});
	}
	
	/**
	 * 
	 * @����:��ȡ�����������
	 * @���ڣ�2015-7-2 ����03:11:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSqcs(PjjgModel model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) sqcs from xg_pjpy_new_xmsq where xh = ? and xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh(),model.getXmdm()});
	}
	
	/**
	 * 
	 * @����:��ȡ�����ô���
	 * @���ߣ�����Դ[���ţ�982]
	 * @���ڣ�2015-7-2 ����03:18:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJxcs(PjjgModel model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) jxcs from xg_pjpy_new_pjjgb where xh = ? and xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh(),model.getXmdm()});
	}
	
	/**
	 * 
	 * @����:��ȡѧ��ѧ��list
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-2 ����03:42:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXnList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,rownum no from ");
		sql.append("(select distinct xn from cjb where xh = ? order by xn) t");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:���޿�ƽ���ɼ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-3  ����03:36:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getNjPjfRank(PjjgModel model,String nj,String kcxz){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum njno,t.* from (select avg(decode(t.cj, '����', 60, t.cj)) pjf,min(decode(t.cj, '����', 60, t.cj)) zdf ");
		sql.append(", xh from cjb t where kcxz = ?  and xh in (select xh from xsxxb where nj = ?  ) ");
		sql.append("  and xn = ? group by xh order by avg(decode(t.cj, '����', 60, t.cj)))t ) t1 where t1.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{kcxz,nj,model.getXn(),model.getXh()});
	}
	
	/**
	 * 
	 * @����:��ȡ�꼶����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-3 ����02:55:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getNjRs(String nj){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) njrs from xsxxb where nj = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{nj});
	}
	
	/*
	 * @����: ����ũҵ����ȡ��������
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-10-26 ����10:00:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getHznydxPjpyMap(String xh){
		StringBuilder sql =  new StringBuilder();
		sql.append(" select sqsjs,xmmc,bjdw from ( select t1.bjdw,(select xmxzmc from xg_pjpy_new_xmxz b where t1.xzdm=b.xmxzdm) xmxzmc,t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn desc,t1.xq desc ) where ROWNUM <= 4 ");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"sqsjs","xmmc","bjdw"});
	}
	
	/**
	 * @������ͨ��sql������ȡ����n������Ϣ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��6�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh,String n){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t.* from (select t1.bjdw, t3.xmxzmc, t1.xn, t2.xqmc, t1.xmmc, t1.xmje, t1.sqsj, to_char(to_date(t1.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy-MM-dd') ");
		sql.append(" sqsjs from xg_pjpy_new_pjjgb t1 left join xqdzb t2 on t1.xq = t2.xqdm left join xg_pjpy_new_xmxz t3 on t1.xzdm = t3.xmxzdm ");
		sql.append(" where t1.xh = ? order by t1.xn desc, t1.xq desc,t1.sqsj desc)t where rownum<= ? ");
		return dao.getArrayList(sql.toString(), new String[]{xh,n}, new String[]{"xn","xqmc","xmmc","xmje","bjdw","xmxzmc","sqsj" , "sqsjs"});
	}
	
	/**
	 * @����������б�,���ڵ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPjjgList_10279(String xn,String xmmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* ,rownum R from( ");
		sql.append(" select t1.xn, t1.xq, t1.xmdm,t1.xmmc,t4.xymc,t4.bjmc,t1.xh,t4.xm,replace(t1.ylzd1,'<br/>','&'||chr(35)||'10;') ylzd1, ");
		sql.append( "replace(t1.ylzd3,'<br/>','&'||chr(35)||'10;') ylzd3,replace(t1.ylzd4,'<br/>','&'||chr(35)||'10;') ylzd4,t1.ylzd5,t8.fs zczf, ");
		sql.append(" case when t5.bjgs>0 then '������' else '����' end jgqk ,case when t6.cfs>0 then '��' else '��' end wjqk,nvl(t7.hjqk,'��') hjqk ");
		sql.append("   from xg_pjpy_new_pjjgb t1 ");
		sql.append("   left join view_xsbfxx t4 ");
		sql.append("    on t1.xh=t4.XH ");
		sql.append("   left join (select fs,xh from XG_ZHCP_ZCFSB where xmdm in(select max(xmdm) from xg_zhcp_zcxmb where xn =? and fjdm='N')) t8 ");
		sql.append("    on t1.xh=t8.xh ");
		sql.append("   left join (select count(1) bjgs,xh from view_zhhcjb where cj < 60 and kcxz like '%����%' group by xh) t5 ");
		sql.append("    on t1.xh=t5.xh ");
		sql.append("   left join (select count(1) cfs,xh from xg_wjcf_wjcfb where nvl(ssjg,0) <> '��������' or nvl(ssjg,0) <> '��������' and jcwh is null group by xh)t6 ");
		sql.append("    on t1.xh=t6.xh ");
		sql.append("   left join (select WM_CONCAT(xmmc) hjqk,xh from (select distinct xh,xmmc from xg_pjpy_new_pjjgb) group by xh) t7 ");
		sql.append("    on t1.xh=t7.xh ");
		sql.append("  where  t1.xn= ? and t1.xmmc= ? ");
		sql.append(" )t ");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xn,xmmc});
	}

	/** 
	 * @��������������ϱ������б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��10�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getPjjghzList(PjjgModel model,User user) throws Exception {
		SearchModel searchModel = model.getSearchModel();
		String[] xydm = searchModel.getSearch_tj_xy();
		searchModel.setSearch_tj_xy(new String[]{});
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		String userSql = "";
		if(StringUtils.equals("12036",Base.xxdm)){
			userSql = SearchService.getSearchTjByUser(user,"t5","xydm","bjdm");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		sql.append("(select t.*,count(1) hjrs from(select t1.xmmc,t1.lxdm,t2.xmlxmc,t1.xzdm,t3.xmxzmc,t1.xn,t1.xq,t4.xqmc from XG_PJPY_NEW_PJJGB t1 ");
		sql.append("left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm ");
		sql.append("left join XG_PJPY_NEW_XMXZ t3 on t1.xzdm=t3.xmxzdm ");
		sql.append("left join xqdzb t4 on t1.xq=t4.xqdm");
		sql.append(" left join view_xsjbxx t5 on t5.xh=t1.xh where 1=1 ");
		if(xydm !=null && xydm.length> 0){
			StringBuffer xySql = new StringBuffer();
			int length = xydm.length;
			xySql.append(" and t5.xydm in ( ");
			for(int i=0;i<length-1;i++ ){
				xySql.append("'"+xydm[i]+"',");
			}
			xySql.append("'"+xydm[length-1]+"')");
			sql.append(xySql);
		}
		sql.append(userSql);
		sql.append(" )t group by xmmc,lxdm,xmlxmc,xzdm,xmxzmc,xn,xq,xqmc ");
		sql.append(") where 1=1  ");
		sql.append(searchTj);
		return getPageList(model,sql.toString(), inputV);
	}
	
	/**
	 * @��������������ϱ���������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��11�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPjjghzMdList(PjjgModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		SearchModel searchModel = model.getSearchModel();
		searchModel.setSearch_tj_xy(new String[]{});
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		sql.append("select t.* ,rownum R from (");
		sql.append("select t1.*,t5.sydmc,t5.zzmmmc,t5.xlmc,t5.xm,t5.nj,t5.bjdm,t5.bjmc,t5.zydm,t5.zymc,t5.xydm, ");
		sql.append("t5.xymc,t5.mzmc,t5.xb,substr(t5.rxrq,0,7) rxny,substr(t5.csrq,0,7) csny,t5.sjhm,t5.sfzh,t5.yhkh,t2.xmlxmc,t3.xmxzmc,t4.xqmc,t5.jgmc  ");
		sql.append("from XG_PJPY_NEW_PJJGB t1 ");
		sql.append("left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm  ");
		sql.append("left join XG_PJPY_NEW_XMXZ t3 on t1.xzdm=t3.xmxzdm  ");
		sql.append("left join xqdzb t4 on t1.xq=t4.xqdm ");
		sql.append("left join view_xsxxb t5 on t1.xh=t5.xh ");
		sql.append(")t where 1=1 and xn=? ");
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			sql.append("and xq= ? ");
		}
		if(StringUtils.equals("12036",Base.xxdm) &&
				StringUtils.equals("getPjjghzMdListByXy",model.getType())){
			if(!"null".equals(model.getXydm()) && StringUtils.isNotNull(model.getXydm())){
				sql.append("and xydm= '"+model.getXydm()+"' ");
			}
		}

		sql.append("and xmmc=? and lxdm=? and xzdm=? ");
		sql.append(searchTj);
		String[] strs=null;
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			strs=new String[]{model.getXn(),model.getXq(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}else{
			strs=new String[]{model.getXn(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}
		
		return getPageList(model,sql.toString(),StringUtils.joinStrArr(strs,inputV));
	}
	
	/**
	 * @������ͨ��sql������ȡ���洢�Ļ���Ϣ
	 * @���ߣ�����[����:1529]
	 * @���ڣ�2017��9��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 */

		
	public HashMap<String, String> getZjlyByXhMap(String xh,String xn){
		//�㽭���ι��ҽ�ѧ���ѯ
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_gjjxjjg where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//�㽭���ε��۲�������ѯ
	public HashMap<String, String> getZjlyByPm(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from VIEW_ZHSZFPM where  xh =? and xn=? ");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//ȡ��־��ѧ���е�������Ϣ
	public HashMap<String,String> getZjlylzByXhMap(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_gjlzjxjjg where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//��־��ѧ���е�ѧϰ�����ѯ
	public HashMap<String,String> getZjlyXxqkCj(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from VIEW_SYS_XSCJXX where xh=? and xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//�㽭ʡ������ѧ���޸��������Ϣ�������ֶΣ�
	public HashMap<String, String> getZjszfByXhMap(String xh,String xn){
		//�㽭���ι��ҽ�ѧ���ѯ
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_zfjxjjg where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	
	}
	//�㽭����ְҵѧԺ��ѧ������������ֶΣ�
	public HashMap<String, String> getZjlyzyxyfByXhMap(String xh,String xn){		
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_bpmx_xyjxjjgb where f_xh=? and f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//ʡ�������ҵ��
	public HashMap<String,String> getZjlySjyxbys(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select a.*,to_char(a.f_sqsj,'yyyy-mm-dd') sqsj from zfsoft_bpmx.view_bpmx_sjyxbysjgb a where a.f_xh=? and a.f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//�㽭����ѧԺ�����ҵ��
	public HashMap<String,String> getZjlyxyyxbys(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select a.*,to_char(a.f_sqsj,'yyyy-mm-dd') sqsj from zfsoft_bpmx.view_bpmx_yjyxbysjgb a where a.f_xh=? and a.f_sqxn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	public boolean isExist(String xh, String xn) {
		String sql = "select count(1) num from zfsoft_bpmx.view_bpmx_yjyxbysjgb where f_xh = ? and f_sqxn=?" ;
		String num = dao.getOneRs(sql, new String[]{xh,xn}, "num");
		return Integer.valueOf(num)>0;
	}
	//�㽭��ҽҩ ��ý�ѧ��ȼ�
	public String getJxjmcByXhXn(String xh, String xn) {
		StringBuilder sql =  new StringBuilder();
		sql.append("SELECT xmmc FROM XG_PJPY_NEW_PJJGB WHERE xh = ? AND xn = ?");
		sql.append("AND (XMMC LIKE '%һ��%' OR XMMC LIKE '%����%' OR XMMC LIKE '%����%')");
		return dao.getOneRs(sql.toString(),new String[]{xh,xn},"xmmc");
	}
	
	/**
	 * @����: �ൺ����ѧԺ���Ի�ȡ-ѧ���ۺϳɼ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-10-16 ����06:40:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXnzhcj(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select fs,bjpm from xg_zhcp_zcfsb where xmdm in ");
		sql.append("(select xmdm from xg_zhcp_zcxmb where xn = ? and xmmc = 'ѧ���ۺϳɼ�') and xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh});
	}
	
	/**
	 * @����: ����ѧ��ȡ��ǰ�������ڵĲ����༶����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-18 ����07:05:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXsszcpbjRsForxh (String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select count(*)bjzrs from xg_pjpy_new_cpmdb where xn = ? and bjdm in ");
		sql.append("(select bjdm from xg_pjpy_new_cpmdb where xh = ? and xn = ?) ");
		return dao.getOneRs(sql.toString(), new String[]{xn,xh,xn}, "bjzrs");
	}
	
	/**
	 * @����: �����г��Ľ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-3 ����08:41:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getExcleZje(PjjgModel model) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql.append("select sum(nvl(t.xmje,0))zje from (");
		sql.append("select t1.*,t2.xmlxmc,t3.xmxzmc,t4.xqmc ");
		sql.append("from XG_PJPY_NEW_PJJGB t1 ");
		sql.append("left join XG_PJPY_NEW_XMLX t2 on t1.lxdm=t2.xmlxdm  ");
		sql.append("left join XG_PJPY_NEW_XMXZ t3 on t1.xzdm=t3.xmxzdm  ");
		sql.append("left join xqdzb t4 on t1.xq=t4.xqdm ");
		sql.append(")t where 1=1 and xn=? ");
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			sql.append("and xq= ? ");
		}
		sql.append("and xmmc=? and lxdm=? and xzdm=? ");
		sql.append(searchTj);
		String[] strs=null;
		if(!"null".equals(model.getXq()) && StringUtils.isNotNull(model.getXq())){
			strs=new String[]{model.getXn(),model.getXq(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}else{
			strs=new String[]{model.getXn(),model.getXmmc(),model.getLxdm(),model.getXzdm()};
		}
		return dao.getOneRs(sql.toString(), StringUtils.joinStrArr(strs,inputV), "zje");
	}
	/**
	 * @������ͨ��sql����ѯ�γ������㡢���á��еȡ�����γ̸�������
	 * @���ߣ�����[����:1529]
	 * @���ڣ�2017��11��9�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	//�����Ƽ���ѧ�������м��Լ����޿�����
	public HashMap<String,String> getXakjdxylzjbxkms(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select  nvl(round(sum(case when cj >=60 and cj<70 then '1' end),0),0)jgms," +
				"nvl(round(sum(case when cj >=70 and cj<80 then '1' end),0),0)zdms ," +
				"nvl(round(sum(case when cj >=80 and cj<90 then '1' end),0),0)lhms," +
				"nvl(round(sum(case when cj >=90 and cj<=100 then '1' end),0),0)yxms," +
				"nvl(round(sum(case when cj >=60  then '1' end),0),0)bxkjgms , " +
				"count(xh) as bxkms from cjb  where  kcxz like '%����%'and xh = ? and xn = ? group by xh");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//�����Ƽ���ѧ����ѧ���ȡ�����
	public List<HashMap<String, String>> getHjqk(String xh,String xn){
		StringBuilder sql =  new StringBuilder();
		sql.append("select t1.xn,t1.xmmc from xg_pjpy_new_pjjgb t1 left join xqdzb t2 on t1.xq=t2.xqdm where t1.xh=?and xn =?");
		return dao.getListNotOut(sql.toString(), new String[] {xh,xn});
	}
	//���칤�̴�ѧ
	public List<HashMap<String, String>> getCjsxqList(String xn, String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select kcmc,cj from view_zhhcjb where xn = ? and xq like '%1%' and  xh = ? order by kcmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xh});
	}
	public List<HashMap<String, String>> getCjxsqList(String xn, String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select kcmc,cj from view_zhhcjb where xn = ? and xq like '%2%' and  xh = ? order by kcmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xh});
	}
	//����ҽҩ--1529
	public String getkssj( String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select kssj from VIEW_NEW_DC_DTXX_JG where xh = ? and jdmc like '%��Ա%'  ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "rtsj");
	}
	public String gettyrs( String cpbjdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(xh) as gs from view_xsbfxx where zzmmmc like '%��Ա%' and bjdm =? ");
		return dao.getOneRs(sql.toString(), new String[]{cpbjdm}, "gs");
	}

	public List<HashMap<String, String>> getHjqkInfoMap(String xh) {
		StringBuilder sql =  new StringBuilder();
		sql.append("select * from xg_xsxx_new_hjqkb where xh=? order by hjsj");
		return dao.getArrayList(sql.toString(), new String[]{xh}, new String[]{"hjsj","fjdw","hjmc","bz"});
	}
	//���������ѧ,ȡ���������߷�
	public  HashMap<String,String> getCjfsList(String xh, String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select min(cj) as min  , max(cj) as max, round(avg(cj),2) as pjcjjdfs  from cjb where xh = ?  and xn = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}

	//���칤�̣�ȡ�����,���ʱ�䣨���Ի���������ˣ�
	public List<HashMap<String, String>> getShrList(String xh, String xn, String xq, String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select to_char(to_date(shsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyyMMdd')  shsj , xm ,shyj from (select t4.id, t4.xh, t4.xn, t4.xq,t4.xmdm, t4.xmmc, t.xm, t.shyj, t.shr, t.shsj, " +
				"t.mc, row_number() over(partition by t4.id, t4.xh, t4.xn, t4.xq, t4.xmdm, t4.xmmc, " +
				"t.mc order by t.shsj desc) rn from XG_PJPY_NEW_PJJGB t4 left join (select t1.*, t2.sqid, t3.*," +
				"t5.xm from xg_xtwh_shztb t1 left join xg_pjpy_new_xmsq t2 on t1.ywid = t2.sqid left join " +
				"xg_xtwh_spgw t3 on t1.gwid = t3.id left join fdyxxb t5 on t1.shr = t5.zgh order by shsj desc) t " +
				"on t4.id = t.ywid where t4.xh = ? and t4.xn = ? and t4.xq = ? and t4.xmdm = ? group by t4.id, t4.xh," +
				" t4.xn, t4.xq, t4.xmdm, t4.xmmc, t.xm, t.shyj, t.shr, t.shsj, t.mc) where rn = 1");
		String[] inputValue = { xh,xn,xq,xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * @description	�� ��ȡ���õ���list
	 * @author 		�� ������1282��
	 * @date 		��2017-12-27 ����02:56:32
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getBzmdList(PjjgModel t) throws Exception{
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,nvl(c.xmmc,a.xmmc) xmmc");
		sql.append(" from xg_pjpy_new_pjjgb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_pjpy_new_pjxmb c on a.xmdm = c.xmdm");
		
		//sql.append(" where a.xmdm is not null and c.xmmc is not null");
		
		//sql.append(" group by a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,a.xmdm,c.xmmc order by xymc,xmmc,bjmc asc");
		sql.append(" ) where 1=1");
		sql.append(searchTj);
		sql.append(" group by xn,xq,xh,xm,xymc,xydm,bjmc,bjdm,xmmc order by xymc,xmmc,bjmc asc");
		return dao.getListNotOut(sql.toString(), inputV);		
	}
	
	/**
	 * @description	�� ��ȡѧԺ�������б�
	 * @author 		�� ������1282��
	 * @date 		��2017-12-28 ����09:10:46
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXyhjrsList(PjjgModel t) throws Exception{
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xn,xqmc,xydm,xymc,xmmc,count(1) xyhjrs from (select * from (");
		sql.append(" select a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,nvl(c.xmmc,a.xmmc) xmmc,d.xqmc");
		sql.append(" from xg_pjpy_new_pjjgb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_pjpy_new_pjxmb c on a.xmdm = c.xmdm");
		sql.append(" left join xqdzb d on a.xq = d.xqdm");
		
		//sql.append(" where a.xmdm is not null and c.xmmc is not null");
		
		//sql.append(" group by a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,a.xmdm,c.xmmc,d.xqmc");
		sql.append(" ) where 1=1");
		sql.append(searchTj);
		sql.append(" group by xn,xq,xh,xm,xymc,xydm,bjmc,bjdm,xmmc,xqmc");
		sql.append(" ) group by xn,xqmc,xydm,xymc,xmmc order by xymc,xmmc asc");
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/**
	 * @description	�� ��ȡ�༶�������б�
	 * @author 		�� ������1282��
	 * @date 		��2017-12-28 ����09:16:33
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getBjhjrsList(PjjgModel t) throws Exception{
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xydm,bjdm,bjmc,xmmc,count(1) bjhjrs from (select * from (");
		sql.append(" select a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,nvl(c.xmmc,a.xmmc) xmmc");
		sql.append(" from xg_pjpy_new_pjjgb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_pjpy_new_pjxmb c on a.xmdm = c.xmdm");
		
		//sql.append(" where a.xmdm is not null and c.xmmc is not null");
		
		//sql.append(" group by a.xn,a.xq,a.xh,b.xm,b.xymc,b.xydm,b.bjmc,b.bjdm,a.xmdm,c.xmmc");
		sql.append(" ) where 1=1");
		sql.append(searchTj);
		sql.append(" group by xn,xq,xh,xm,xymc,xydm,bjmc,bjdm,xmmc");
		sql.append(" ) group by xydm,bjdm,bjmc,xmmc order by xmmc,bjmc asc");
		return dao.getListNotOut(sql.toString(), inputV);
	} 
	
	/**
	 * 
	 * @����: ��ѧ������ܺ�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-24 ����11:48:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjhzzhList(PjjgModel t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum rn from (");
		sql.append(" select t.xh,");
		sql.append(" t1.XM,");
		sql.append(" t1.SFZH,");
		sql.append(" t1.BJMC,");
		sql.append(" t.xmmc,");
		sql.append(" t.xmje,");
		sql.append(" t.sqly,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t1.XB,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.MZ,");
		sql.append(" t1.XZ,");
		sql.append(" t1.XJZTM,");
		sql.append(" t.lxdm,");
		sql.append(" t.xzdm");
		sql.append(" from xg_pjpy_new_pjjgb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" order by t1.XYDM,t1.BJDM,t1.XH");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}

	/**
	 * @throws Exception 
	 * @����:��ȡ�����ñ�������Ϣ(��������ְҵ����ѧԺ)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-3-9 ����03:20:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCwybList(PjjgModel model, User user) throws Exception {
		
		SearchModel searchModel = model.getSearchModel();
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*  from VIEW_NEW_DC_PJPY_PJJG t1  where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	/**
	 * @throws Exception 
	 * @����:��ȡ�����ñ�������Ϣ�ϼ�(��������ְҵ����ѧԺ)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-3-9 ����03:20:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public String getCwybSum(PjjgModel t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(xmje) hj from ( ");
		sql.append("select t1.*  from VIEW_NEW_DC_PJPY_PJJG t1  where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" )");
		
		
		/*SearchModel searchModel = model.getSearchModel();
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT sum(xmje) hj FROM (");
		sql.append("select t1.xn,t1.xmje,t1.xmmc,t1.xh,t2.xm,t2.zymc, t2.sfzh ");
		sql.append(" from xg_pjpy_new_pjjgb t1");
		sql.append("  left join VIEW_XSXXB   t2 on t1.xh = t2.xh");
		sql.append(") t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);*/
		return dao.getOneRs(sql.toString(), inputV,"hj");
	}
	
	/**
	 * 
	 * @����: �й�����ѧԺ���Ի�����List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-21 ����05:01:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param n
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getHjxxList(String xh,String xn,String xq,String n){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.hjmc,t1.hjsj,t1.fjdw from xg_pjpy_hjxxjl t ");
		sql.append(" left join xg_xsxx_new_hjqk_jgb t1 on t.id = t1.id where t.xn = ? and t.xq = ? and t.xh = ? and rownum <= ?");
		sql.append(" order by t1.hjsj desc");
		return dao.getListNotOut(sql.toString(),new String[]{xn,xq,xh,n});
	}
	
	/**
	 * 
	 * @����: ��ȡ���ʱ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-21 ����05:49:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getHjshsjList(String xmdm,String days){
		StringBuilder sql = new StringBuilder();
		sql.append(" select rq,substr(rq,0,4) years,substr(rq,6,2) mon,substr(rq,9,2) days  from");
		sql.append(" (select to_char(( to_date(xxtbsj,'yyyy-mm-dd hh24:mi:ss') - ?),'yyyy-mm-dd') rq from xg_pjpy_new_pjxmb where xmdm = ?)");
		return dao.getMapNotOut(sql.toString(), new String[]{days,xmdm});
		
	}

	public String getXymcBydm(String xydm) {
		String sql = "select xymc from VIEW_NJXYZYBJ_ALL where xydm=?";
		return dao.getOneRs(sql,new String[]{xydm},"xymc");
	}
	
	//�й���Ժ ѧ�����β���Υ����Ϣ
	public  HashMap<String,String> getKkbkxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select kkjl,bkjl,bkjg,wjqk from xsjxj_wx where xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

    public List<HashMap<String,String>> getPjjgExportList(PjjgModel t, User user) throws Exception {

		//���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		SearchModel searchModel = t.getSearchModel();
		String[] xntj = searchModel.getSearch_tj_xn();
		String[] xqtj = searchModel.getSearch_tj_xq();

		sql.append(" select rownum rn,t.* from ( ");

		sql.append("select * from (select x1.sydm,x1.symc, f.cpzdm,cp.cpzmc, t2.yhmc yhmc2,t1.xn||' '||t1.xqmc pjzq,case when t11.xh is null then '��' else '��' end sfwtgg ");
		sql.append(" ,t1.XH,t1.XM,t1.XB,t1.NJ,t1.XN,t1.XQ,t1.XQMC,t1.SQSJ,t1.XZDM,t1.XYDM,t1.XYMC,t1.ZYDM,t1.ZYMC,t1.ZYBJ,t1.ZYBJMC,cm.bjdm,vna.bjmc,t1.XMJE,t1.XMLXMC,t1.XMMC,t1.XZ,t1.MZ,t1.MZMC,t1.XMXZMC,t1.ZZMMMC,t1.sydmc,t1.zd1 ");
		sql.append(" from VIEW_NEW_DC_PJPY_PJJG t1 ");
		sql.append(" left join xg_pjpy_new_cpmdb cm on cm.xh = t1.xh and cm.xn = t1.xn and cm.xq = t1.xq ");
		sql.append(" left join XG_ZHCP_FSTJJLB f on f.bjdm = cm.bjdm and f.xn = t1.xn and f.xq = t1.xq ");
		sql.append(" left join XG_ZHCP_CPZB cp on cp.cpzdm = f.cpzdm");
		sql.append(" left join view_njxyzybj_all vna on cm.bjdm = vna.bjdm ");
		sql.append(" left join XG_XTWH_SYBJGLB x on cm.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x1.sydm = x.sydm ");

		sql.append(" left join dmk_yh t2 on t1.shgxzw2=t2.yhdm left join(select * from xg_pjpy_new_tsxsb where lxdm='6') t11 on t1.xn=t11.xn and t1.xq=t11.xq and t1.xh=t11.xh)t1 where 1=1 ");

		//���ֽ�ѧ��ͱ��ý���
		if("2".equals(t.getXzdm())){
			sql.append(" and xzdm='2' ");
		}else{
			sql.append(" and xzdm <> '2' ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by symc,xmmc,xh ");
		sql.append(" ) t ");

		return dao.getListNotOut(sql.toString(),inputV);
    }
}
	


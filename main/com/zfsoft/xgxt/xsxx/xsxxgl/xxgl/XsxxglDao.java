/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import common.Globals;
import org.apache.commons.lang.StringUtils;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ����Ϣ����
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-23 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XsxxglDao extends SuperDAOImpl<XsxxglModel> {
	private String XSXX_CXJGZDPZ = "xsxx_tygl_cxzxs.do";

	/**
	 * ��ͨ��ѯ����
	 */
	public List<HashMap<String, String>> getPageList(XsxxglModel model)
			throws Exception {
		return null;
	}

	/**
	 * �߼���ѯ����
	 */
	public List<HashMap<String, String>> getPageList(XsxxglModel model,
			User user) throws Exception {
		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sb = new StringBuilder();

		sb.append("select xh pk,a.*,rownum r from (");
		sb.append("select t.* from (select nvl2(a.sjhm, '��', '��') as sfytx,nvl2(b.sfpkx, b.sfpkx, '0') as sfpkx ,nvl2(d.xh, '1', '0') as sfbgb ,a.*,e.*,(select mc from XG_XSXX_JKZTB g where a.jkzk=g.dm)jkzkmc,(select mc from sfdqdmb f where a.xwzsbh=f.dm)sfdqmc,(select pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm) pyccmc,");
		sb.append(" k.sydm,k.symc, ");
		sb.append(" CASE WHEN l.CZ IS NULL OR l.CZ=0 THEN '' ELSE 'ת��' END zr, ");
		sb.append(" l.CZSJ, ");
		sb.append("(select rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm) rxfsmc,");
		sb.append("(select kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm) kslbmc from view_xsxxcxjg a left join dmk_qx b on a.syd = b.qxdm left join (select distinct xh from VIEW_NEW_DC_SZDW_XSDWWH where zzzt = '1') d on a.xh=d.xh ");

		sb.append("left join ")
		.append("(select nvl(t1.bjdm,t2.bjdm) as bjdm2 , t1.fdyxms , t2.bzrxms from (select a.bjdm ,  WM_CONCAT(b.xm) fdyxms from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ")
		.append(" full join ")
		.append("(select a.bjdm ,  WM_CONCAT(b.xm) bzrxms from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t2 on t1.bjdm = t2.bjdm) e ")
		.append(" on a.bjdm = e.bjdm2 ");
		sb.append(" left join xg_xtwh_sybjglb j on a.bjdm=j.bjdm ");
		sb.append(" left join xg_xtwh_sydmb k on j.sydm=k.sydm ");
		sb.append(" left join ZSY_TEST l on a.XH=l.XH ");
		sb.append(") t ");
		sb.append("where 1=1 ");

		if (model.getSfzx() != null && model.getSfzx().equals("0")) {// ����У
			sb.append("  and sfzx='����У'  ");
			sb.append(" and (sfyby = '��' or sfyby is null) ");
		} else {// ��У
			sb.append(" and (sfzx='��У' or sfzx is null) ");
		}
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" ) a ");
		
		return getPageList(model, sb.toString(), inputV);
	}
	
	/**
	 * �����ƶ�ѧ����ѯѧ����Ϣ̫����д����
	 */
	public List<HashMap<String, String>> getXsxxListForYdxg(XsxxglModel model,
			User user) throws Exception {
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUserOfYdxg(user, "t",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( select a.bjdm,a.xydm,a.xh,a.xm,a.jtdz,a.xymc,a.zymc,a.nj,a.bjmc,a.qqhm ,a.sydqmc,a.xb,a.mzmc,a.zzmmmc,");
		sql.append(" a.sfzx,a.sjhm from view_xsxxb a ");
		sql.append(" order by a.nj,a.xymc,a.zymc,a.bjmc");
		sql.append(") t ");
		sql.append("where 1=1 ");
		sql.append(" and nvl(sfzx,'��У')='��У' ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by nj, xymc, zymc, bjmc");
		return getPageList(model, sql.toString(), inputV);
	}
	public List<HashMap<String, String>> getStuCjPageList(XsxxglModel model,
			User user) throws Exception {
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from (");
		sb.append("select a.*,a.xn||b.xqmc xnxq,b.xqmc,b.xqjb,c.BJDM,c.bjmc,c.NJ,c.XYDM,c.XYMC,c.XB,c.xm,c.zymc,c.zydm from cjb a ");
		sb.append(" left join xqdzb b on a.xq=b.xqdm  ");
		sb.append(" left join view_xsbfxx c");
		sb.append(" on a.xh = c.XH");
		sb.append(" ");
		sb.append(" )t where 1=1 ");
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" order by xn desc,xq");
		return getPageList(model, sb.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getTyqntjList(XsxxglModel model,
			User user) throws Exception {
		// ====================��������===================================
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sb = new StringBuilder();
		
		sb.append("select a.nj, count(distinct a.zd1) tzbs, count(xh) xszs,sum(bks) bkxsrs,sum(zks) zkxsrs,sum(bktys_man)bktys_man,sum(bktys_woman)bktys_woman,sum(ftys)ftys,");
		sb.append("sum(zktys_man)zktys_man,sum(zktys_woman)zktys_woman");
		sb.append(" from (select * from view_xg_xsxx_tyqnjbqktj ");
		sb.append(" t ");
		sb.append("where 1=1 ");
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" ) a group by nj");
		return getPageList(model, sb.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:��ȡ�������ݣ�����ҳ 
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:55:33
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllList(XsxxglModel model,
			User user) throws Exception {
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		//�㽭��ѧ���Ի���ͼ  ���ѧ��ȡѧ԰��Ϣ
		String xsxxTable = "view_xsxxb";
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			xsxxTable="view_xsxxb_zjdx";
		}
		
		StringBuilder sb = new StringBuilder();
		sb
		.append("select * from (select a.*,bzr.bzrlbxx,fdy.fdylbxx, bzr.bzrlbxx bzrxms,fdy.fdylbxx fdyxms,")
		.append("(select pyccmc from xg_xsxx_pyccdmb c where a.pycc = c.pyccdm) pyccmc,(select rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs = c.rxfsdm) rxfsmc,(select kslbmc from xg_xsxx_kslbdmb c where a.kslb = c.kslbdm) kslbmc")
		.append(",(select xlmc from xldmb where a.rxqwhcd = xldm) rxqwhcdmc")
		.append(",(select mc from sfdqdmb f where a.xwzsbh=f.dm) sfdqmc")
		.append(",(select xqmc from dm_zju_xq c where a.yxdm = c.dm) zjuxqmc") // У��
		.append(",(select zwmc from xg_szdw_xsgb_zwb c where a.xxfx = c.zwid) zjuzwmc,nvl2(a.sjhm, '��', '��') as sfytx");//�Ƿ�����дα��δ���죬����
		
		//�㽭��ѧѧ����Ϣ�����������϶�����
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			sb.append(" ,nvl(zd.dcmc,'')dcmc");
		}
		//��������ְҵ����ѧԺ
		if("12482".equalsIgnoreCase(Base.xxdm)) {
			sb.append(",(select lbmc from dmk_xslb c where a.shgxgzdw1 = c.lbdm) lbmc");//ѧ���������
		}
	
		//����ʦ��ѧԺ
		if("10606".equals(Base.xxdm)) {
			sb.append(",(select yhmc from dmk_yh c where a.SHGXZW1 = c.yhdm) yhmc2")
			  .append(",(select yhmc from dmk_yh c where a.shgxgx2 = c.yhdm) yhmc3");
		}
		
		//�㽭��ýѧԺ
		if(!"11647".equals(Base.xxdm)) {
			sb.append(" ,c.jtcy1_xm,c.jtcy1_gx,c.jtcy1_gzdz,c.jtcy1_lxdh1,c.jtcy2_xm,c.jtcy2_gx,c.jtcy2_gzdz,c.jtcy2_lxdh1,c.jtcy3_xm,c.jtcy3_gx,c.jtcy3_gzdz,c.jtcy3_lxdh1 ");			
		}
		
		//�㽭��ҽҩ
		if("10344".equals(Base.xxdm)){
			sb.append(" ,qsxx.qsh qsh0,qsxx.cwh cwh0,qsxx.ldmc ldmc0,qsxx.yqmc yqmc0,qsxx.xqmc xqmc0 ");
		}
		//�����Ƽ���
		if("10704".equals(Base.xxdm)){
			sb.append(" ,cwb.qsh,cwb.cwh,cwb.ldmc");
		}
		//����ְҵ
		if("12872".equals(Base.xxdm)){
			sb.append(" ,cwb.qsh,cwb.cwh,cwb.ldmc");
		}
		//���ݴ�ѧ���Ի���ѯ
		if("10351".equals(Base.xxdm)){
			sb.append("  ,wd1.yqmc ssyqmcs,");
			sb.append("  wd2.ldmc || ' ' || wd2.qsh qsh,");
			sb.append("  wd3.xqfdyxm,wd4.xm qsds");
		}
		
		//������Ͽҽҩ�ߵ�ר��ѧУ
//		if("14008".equals(Base.xxdm)){
//			sb.append(" ,h.xjlbmc ydlbmc");
//		}
		//������ʦѧԺ���Ի��ж�
		if("33201".equals(Base.xxdm)){
			sb.append(",(select e.shengmc || e.shimc || e.xianmc");
			sb.append(" from XG_VIEW_DMK_QX e");
			sb.append(" where e.qxdm = a.shgxzw2  ) csdmcs");
		}
		
		//�½�ҽ�ƴ�ѧ��ѧԺ�����������
		if("13560".equalsIgnoreCase(Base.xxdm)){
			sb.append(" ,t1.yzlbmc");
		}
		if("10698".equals(Base.xxdm)){//������ͨ��ѧ
			sb.append(" ,k.sydm,k.symc ");
		}
		//�Ͼ��ߵ�ְҵ����ѧУȡ����
		if("10874".equals(Base.xxdm)){
			sb.append(" ,k.gjdqmc gjmc");
		}
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			sb.append(",case when cwb.ldmc is null then '��' else cwb.ldmc || '-' || cwb.qsh || '-' || cwh end zsxx");
		}
		if("10878".equalsIgnoreCase(Base.xxdm)){
			sb.append(" ,decode(a.zd3,'1','��','2','��','') sfda");
		}
		sb.append(" ,d.shjl1,d.shjl2,d.shjl3 ")		
		.append(" from "+xsxxTable+" a ");
		
		//������Ͽҽҩ�ߵ�ר��ѧУ
//		if("14008".equals(Base.xxdm)){
//			sb.append(" left join xg_xsxx_xjydjgb g on a.xh = g.xh ");
//			sb.append(" left join dm_xjlb h on g.ydlbdm = h.xjlbdm ");
//		}
		
		sb.append("left join ")
		.append("(select a.bjdm ,  WM_CONCAT(b.zgh || b.xm||b.lxdh) fdylbxx from fdybjb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) fdy on a.bjdm = fdy.bjdm ")
		.append("left join ")
		.append("(select a.bjdm ,  WM_CONCAT(b.zgh || b.xm||b.lxdh) bzrlbxx from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) bzr on a.bjdm = bzr.bjdm ");
		
		//�㽭��ýѧԺ
		if(!"11647".equals(Base.xxdm)) {
			sb.append(" left join (select a.*,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdz,b.jtcy2_lxdh1,c.jtcy3_xm,c.jtcy3_gx,c.jtcy3_gzdz,c.jtcy3_lxdh1 from (")
			.append(" select * from (")
			.append(" select a.xh,a.cyxm jtcy1_xm,b.mc jtcy1_gx,a.cyxxdw jtcy1_gzdz,a.cylxdh jtcy1_lxdh1,")
			.append(" row_number() over(partition by a.xh order by a.rowid) rn1 from XG_XSZZ_NEW_JTCYB a left join xszz_jtcygxb b on a.cygx=b.dm ) where rn1=1) a left join ")
			.append(" (select * from (")
			.append(" select a.xh,a.cyxm jtcy2_xm,b.mc jtcy2_gx,a.cyxxdw jtcy2_gzdz,a.cylxdh jtcy2_lxdh1,")
			.append(" row_number() over(partition by a.xh order by a.rowid) rn2 from XG_XSZZ_NEW_JTCYB a left join xszz_jtcygxb b on a.cygx=b.dm ) where rn2=2) b on a.xh=b.xh left join")
			.append(" (select * from (")
			.append(" select a.xh,a.cyxm jtcy3_xm,b.mc jtcy3_gx,a.cyxxdw jtcy3_gzdz,a.cylxdh jtcy3_lxdh1,")
			.append("  row_number() over(partition by a.xh order by a.rowid) rn3 from XG_XSZZ_NEW_JTCYB a left join xszz_jtcygxb b on a.cygx=b.dm ) where rn3=3) c on a.xh=c.xh) c on a.xh=c.xh ");
		}
		if("10698".equals(Base.xxdm)){//������ͨ��ѧ
			sb.append(" left join xg_xtwh_sybjglb j on a.bjdm=j.bjdm ");
			sb.append(" left join xg_xtwh_sydmb k on j.sydm=k.sydm ");
		}
		//�㽭��ѧѧ����Ϣ�����������϶�����
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			sb.append(" left join (");
			sb.append(" select a.xh,a.xn ||':'|| a.dcmc dcmc");
			sb.append(" from (select t.*,t1.dcmc,");
			sb.append(" row_number() over(partition by t.xh order by t.sqsj desc) rn");
			sb.append(" from xg_xszz_new_knsjgb t");
			sb.append(" left join xg_xszz_new_kndcdmb t1");
			sb.append(" on t.rddc = t1.DCDM");
			sb.append(" ) a");
			sb.append(" where rn = 1");
			sb.append(" ) zd on a.xh = zd.xh");
		}
		
		//���ݴ�ѧ���Ի�����
		if("10351".equals(Base.xxdm)){
			sb.append(" left join ZXBZ_SSYQDM wd1");
			sb.append(" on a.ssyq = wd1.yqdm");
			sb.append(" left join VIEW_XG_GYGL_NEW_CWXX wd2");
			sb.append(" on a.xh = wd2.xh");
			sb.append(" left join XG_GYGL_QSDS wd3");
			sb.append(" on wd2.lddm = wd3.lddm and wd2.qsh = wd3.qsh");
			sb.append(" left join fdyxxb wd4");
			sb.append(" on wd3.zgh = wd4.zgh");
		}
		
		//�½���ѧԺ�������������ʾ
		if("13560".equalsIgnoreCase(Base.xxdm)){
			sb.append(" left join dmk_yzlb_hbxy t1 on a.zd4 = t1.yzlbdm");
		}
		
		sb.append(" left join (select a.*,b.shjl2,c.shjl3 from (")
		.append(" select * from (")
		.append(" select a.xh,a.qzrq||' '||a.gzdd||' ֤����:'||a.zmrjhc shjl1,")
		.append(" row_number() over(partition by a.xh order by a.rowid) rn1 from XG_XSXX_XLSHJL a  ) where rn1=1) a left join ")
		.append(" (select * from (")
		.append(" select a.xh,a.qzrq||' '||a.gzdd||' ֤����:'||a.zmrjhc shjl2,")
		.append(" row_number() over(partition by a.xh order by a.rowid) rn2 from XG_XSXX_XLSHJL a  ) where rn2=2) b on a.xh=b.xh left join")
		.append(" (select * from (")
		.append(" select a.xh,a.qzrq||' '||a.gzdd||' ֤����:'||a.zmrjhc shjl3,")
		.append("  row_number() over(partition by a.xh order by a.rowid) rn3 from XG_XSXX_XLSHJL a ) where rn3=3) c on a.xh=c.xh) d on a.xh=d.xh ");
		
		//�㽭��ҽҩ
		if("10344".equals(Base.xxdm)){
			sb.append(" left join (select t1.xh,t1.qsh,t1.cwh,t2.ldmc,t3.yqmc,t4.xqmc from XG_GYGL_NEW_CWXXB t1 ");
			sb.append(" left join XG_GYGL_NEW_LDXXB t2 on t1.lddm=t2.lddm ");
			sb.append(" left join zxbz_ssyqdm t3 on t2.yqdm=t3.yqdm ");
			sb.append(" left join dm_zju_xq t4 on t3.xqdm=t4.dm ");
			sb.append(" where t1.xh is not null) qsxx on a.xh=qsxx.xh ");
		}
		
		//�Ͼ��ߵ�ְҵ����ѧУ
		if("10874".equals(Base.xxdm)){
			sb.append(" left join zxbz_gjdqdm k on a.gj = k.gjdqdm");
		}
		
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			sb.append(" left join VIEW_XG_GYGL_NEW_CWXX cwb on a.xh = cwb.xh ");
		}
		if("10704".equals(Base.xxdm)){
			sb.append(" left join view_xg_gygl_new_cwxx cwb on a.xh = cwb.xh ");
		}
		if("12872".equals(Base.xxdm)){
			sb.append(" left join view_xg_gygl_new_cwxx cwb on a.xh = cwb.xh ");
		}
		sb.append(" ) a where 1=1 ");
		if (model.getSfzx() != null && model.getSfzx().equals("0")) {// ����У
			sb.append("  and sfzx='����У'  ");
		} else {// ��У
			sb.append(" and (sfzx='��У' or sfzx is null) ");
		}
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" order by xymc,bjmc,xh ");

		return getPageList(model, sb.toString(), inputV);
	}
	

	/**
	 * ��ȡѧ����Ϣ��ѯ��������ֶ��б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getCxjgzdpzList() {
		String sql = "select zd,nvl(xgzdmc,zdmc) zdmc,xssx from xg_xsxx_cxjgpzb "
				+ "where sfjgxs='��' and gnlj=? order by to_number(xssx)";
		return dao.getList(sql, new String[] { XSXX_CXJGZDPZ }, new String[] {
				"zd", "zdmc", "xssx" });
	}

	/*
	 * �����ʼ��
	 * by yxy��1206�� �����޸� 2016-10-19
	 */
	public boolean cshYhmm(String mm1, String values,String bz) throws Exception {
		Encrypt ept = new Encrypt();
		boolean flag = true;
		boolean flag2 = false;
		String kl = mm1;
		String[] primarykey_checkVal = values.split(",");
		List<String[]> upParams = new ArrayList<String[]>();
		List<String[]> inParams = new ArrayList<String[]>();
		//ƴ�ӷ���
		String pjf = "";
		//�Ϻ�������Ի��ж�
		if("11458".equals(Base.xxdm)){
			pjf = "sdju";
		}
 		String sql = "select xh,case" +
 				" when  (a.sfzh is null or length(a.sfzh) < 18) " +
 				" then '"+pjf+"' || '000000'" +
 				" else '" + pjf+"' || substr(a.sfzh,'13','6')" +
 				" end mm" +
 				" " +
 				" from view_xsjbxx a where not exists(select 1 from xsmmb b where a.xh=b.xh)";
	    List<HashMap<String, String>> xhAttr = dao.getListNotOut(sql, new String[]{});
		for (int i = 0; i < primarykey_checkVal.length; i++) {
			String sfzmm = "";
			for (int j = 0; j < xhAttr.size(); j++) {
				if (primarykey_checkVal[i].equalsIgnoreCase(xhAttr.get(j).get("xh"))) {
					flag2 = true;
					sfzmm = xhAttr.get(j).get("mm");
					break;
				}
			}
			if (flag2) {
				if(("sfz").equals(bz)){
					inParams.add(new String[] { primarykey_checkVal[i],ept.encrypt(sfzmm) });
				}else{
					inParams.add(new String[] { primarykey_checkVal[i],ept.encrypt(kl)});
				}
				
				flag2 = false;
			} else {
				if(("sfz").equals(bz)){
					sfzmm = this.getExsisMmbXsSfzMm(primarykey_checkVal[i]).get("mm");
					upParams.add(new String[] { ept.encrypt(sfzmm),primarykey_checkVal[i]});
				}else{
					upParams.add(new String[] { ept.encrypt(kl),primarykey_checkVal[i]});
				}
				
			}
		}

		try {
			if (inParams.size() > 0) {
				sql = "insert into xsmmb(xh,mm) values(?,?)";
				int[] res = dao.runBatch(sql, inParams);
				for (int i = 0; i < res.length; i++) {
					flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!flag)
						break;
				}
			}
			if (flag && upParams.size() > 0) {
				sql = "update xsmmb set mm= ? where xh = ?";
				int[] res = dao.runBatch(sql, upParams);
				for (int i = 0; i < res.length; i++) {
					flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!flag)
						break;
				}
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * @����:ɾ��ѧ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����11:02:09
	 * @�޸ļ�¼:
	 * @param keys
	 */
	public boolean delData(String keys) throws Exception {
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		keys = StringUtils.replace(keys, ",", "','");
		keys = "'" + keys + "'";
		String sql = "delete from xsxxb  ";// ɾ��ѧ������
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xsfzxxb ";// ɾ��ѧ��������Ϣ
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xsmmb ";// ɾ��ѧ������
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xszpb ";// ɾ��ѧ����Ƭ
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯѧ����Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����01:59:21
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXsxxByXh(String xh) {
		StringBuffer sb = new StringBuffer();
		String xsxxTable= "view_xsxxb";
		sb.append("select  decode(d.sfpkx,'1','��','0','��','��') sfpkxmc ,a.*,b.lxdh1,b.lxdh2,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_gzdz,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdz,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_gzdz,b.jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2,b.email,b.jtszd,e.zwmc");
		sb.append(",(select pyccmc from xg_xsxx_pyccdmb c where a.pycc = c.pyccdm) pyccmc,(select rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs = c.rxfsdm) rxfsmc,(select kslbmc from xg_xsxx_kslbdmb c where a.kslb = c.kslbdm) kslbmc");
		sb.append(",(select mc from sfdqdmb f where a.xwzsbh=f.dm)sfdqmc,(select xjlbmc from dm_xjlb where a.xjlb = xjlbdm) xjlbmc");
		sb.append(",(select xlmc from xldmb where a.rxqwhcd = xldm) rxqwhcdmc");
		sb.append(",(select xqmc from dm_zju_xq c where a.yxdm = c.dm) zjuxqmc"); // У��
		sb.append(",(select zwmc from xg_szdw_xsgb_zwb c where a.xxfx = c.zwid) zjuzwmc");
		sb.append(" ,k.sydm,k.symc ");
		sb.append(" from "+xsxxTable+" a ");
		sb.append(" left join xsfzxxb b ");
		sb.append(" on a.xh = b.xh ");
		sb.append(" left join dm_xjlb c ");
		sb.append(" on a.xh = c.xjlbdm ");
		sb.append(" left join dmk_qx d on a.syd = d.qxdm ");
		if("10698".equals(Base.xxdm)){//������ͨ��ѧ
			sb.append(" left join xg_xtwh_sybjglb j on a.bjdm=j.bjdm ");
			sb.append(" left join xg_xtwh_sydmb k on j.sydm=k.sydm ");
		}
		sb.append(" left join VIEW_NEW_DC_SZDW_XSDWWH e on e.xh = a.xh ");
		sb.append(" where a.xh=? ");
		return dao.getMapNotOut(sb.toString(), new String[] { xh });
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯѧ����Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����01:59:21
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXsxxByXhForUpdate(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append("a.sfxyqgzx, case when d.zp is null then '0' else '1' end sczp,a.zd6,a.xgyx,a.ah,a.bjdm,a.bjyjl,a.bxlx,a.bxxs,a.byny,a.bysj,a.byxx,a.byzffztdm,a.byzh,a.bz,a.ccqj,a.csd,a.csds,a.csdshi,a.csdxian,a.csrq,a.cym,a.dah,a.dasfyl,a.daylyy,a.dqszj,a.dxhwp,a.dybj,a.dzyx,a.fdyxm,a.fxzy,a.fxzyfx,a.gj,a.gkcj,a.grjl,a.gzbx,a.hkshen,a.hkshi,a.hkszd,a.hkxian,a.hkxz,a.jg,a.jgs,a.jgshi,a.jgx,a.jkzk,a.jlcfjl,a.jljn,a.jrgcdsj,a.jrgqtsj,a.jrzzmmrq,a.jsjdj,a.jtcygc,a.jtdh,a.jtdz,a.jtdzs,a.jtdzx,a.jtjjqk,a.jtqkjj,a.jtyb,a.jypx,a.kh,a.ksh,a.kslb,a.lxdh,a.lxdz,a.mz,a.nfby,a.pycc,a.pyfs,a.pyfx,a.qqhm,a.qsdh,a.rxfs,a.rxnj,a.rxqdw,a.rxqdwdh,a.rxqdwdz,a.rxqdwyb,a.rxqwhcd,a.rxrq,a.rzrq,a.sfbys,a.sfcj,a.sfdk,a.sfdl,a.sfgat,a.sfhq,a.sfjh,a.sfsf,a.sfssmz,a.sfyby,a.sfzc,a.sfzd,a.sfzfx,a.sfzh,a.sfzsb,a.sfzx,a.sfzz,a.sg,a.shbj,a.shgxdwdh1,a.shgxdwdh2,a.shgxgx1,a.shgxgx2,a.shgxgzdw1,a.shgxgzdw2,a.shgxsjhm1,a.shgxsjhm2,a.shgxxm1,a.shgxxm2,a.shgxzw1,a.shgxzw2,a.shzw,a.sjhm,a.ssbh,a.ssch,a.ssld,a.ssyq,a.sybz1,a.sybz2,a.sybz3,a.syd,a.syds,a.sydshi,a.sydx,a.tc,a.thbs,a.tz,a.whcd,a.wydj,a.xb,a.xh,a.xjh,a.xjlb,a.xjlbdm,a.xjztm,a.xldm,a.xm,a.xmpy,a.xmsj,a.xslb,a.xslx,a.xsqrxxbz,a.xszp,a.xw,a.xwzsbh,a.xwzsxlh,a.xwzsxxdz,a.xx,a.xxfx,a.xxszd,a.xxxs,a.xz,a.xzxm,a.yhdm,a.yhkh,a.ykth,a.ylbxh,a.yxdm,a.yzbm,a.zcsxhm,a.zd1,a.zd2,a.zd3,a.zd4,a.zd5,a.zgzs,a.zjdm,a.zkzh,a.zsbh,a.zsjj,a.zsjzrq,a.zslb,a.zsxlh,a.zw,a.zxwyyzdm,a.zyfx,a.zyjb,a.zylb,a.tbsj,a.bxxz,a.sftb,a.sfyqrzs,a.qtyy,a.sfsfs,a.wxh,a.zzmm");
		//�Ͼ��ߵ�ְҵ����ѧУ���Ի�
		if(Base.xxdm.equals("10874")){
			sb.append(",a.gj");
		}
		if(Base.xxdm.equals("13943")){
			sb.append(",a.shgxgzdw1 ");
		}
		sb.append(",b.lxdh1,b.lxdh2,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_gzdz,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdz,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_gzdz,b.jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2,b.email,b.jtszd");
		sb.append(",c1.xymc xymc,c1.xymc xy,c1.zymc,c.bjmc,c1.xydm,c1.zydm,c1.nj,c1.bjdm zybj,c1.bjmc zybjmc ");
		/*�����Ƽ���ѧ����11���ֶΣ�2017-11-20��Meng.Wei*/
		if("10704".equals(Base.xxdm)){
			sb.append(",a.gfjyfmpjpy,a.gfjyfmjcqk,a.gfjyhdcjqk,a.xsjxy,a.xsjxl,a.xsbydjsj,a.djdd,a.xscjrwsj,a.rwpzdw,a.xstwfxsj,a.twpzdw");
		}
		//�½���ҵְҵ����ѧԺ
		if("5046".equals(Base.xxdm)){
			sb.append(" ,a.xxqk,a.zsqk,a.qj,a.zsqj  ");
		}
		sb.append(" from xsxxb a ");
		sb.append(" left join xsfzxxb b ");
		sb.append(" on a.xh = b.xh ");
		sb.append(" left join view_njxyzybj_fdy c on a.bjdm=c.bjdm ");
		sb.append(" left join view_njxyzybj_bzr c1 on a.zybj=c1.bjdm ");
		sb.append(" left join xszpb d on a.xh=d.xh ");
		sb.append(" where a.xh=? ");
		return dao.getMapNotOut(sb.toString(), new String[] { xh });
	}
	
	
	/**
	 * 
	 * @����:�㽭��ѧ���Ի�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-12-1 ����07:54:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxxByXhForUpdate_zjdx(String xh) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("select a.sfxyqgzx,a.zd6,a.xgyx,a.ah, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.bjdm else c.bjdm end bjdm, ");
		sb.append("a.bjyjl,a.bxlx,a.bxxs,a.byny,a.bysj,a.byxx,a.byzffztdm,a.byzh,a.bz,a.ccqj,a.csd,a.csds,a.csdshi,a.csdxian, ");
		sb.append("a.csrq,a.cym,a.dah,a.dasfyl,a.daylyy,a.dqszj,a.dxhwp,a.dybj,a.dzyx,a.fdyxm,a.fxzy,a.fxzyfx,a.gj,a.gkcj, ");
		sb.append("a.grjl,a.gzbx,a.hkshen,a.hkshi,a.hkszd,a.hkxian,a.hkxz,a.jg,a.jgs,a.jgshi,a.jgx,a.jkzk,a.jlcfjl,a.jljn, ");
		sb.append("a.jrgcdsj,a.jrgqtsj,a.jrzzmmrq,a.jsjdj,a.jtcygc,a.jtdh,a.jtdz,a.jtdzs,a.jtdzx,a.jtjjqk,a.jtqkjj,a.jtyb, ");
		sb.append("a.jypx,a.kh,a.ksh,a.kslb,a.lxdh,a.lxdz,a.mz,a.nfby,a.pycc,a.pyfs,a.pyfx,a.qqhm,a.qsdh,a.rxfs,a.rxnj, ");
		sb.append("a.rxqdw,a.rxqdwdh,a.rxqdwdz,a.rxqdwyb,a.rxqwhcd,a.rxrq,a.rzrq,a.sfbys,a.sfcj,a.sfdk,a.sfdl,a.sfgat, ");
		sb.append("a.sfhq,a.sfjh,a.sfsf,a.sfssmz,a.sfyby,a.sfzc,a.sfzd,a.sfzfx,a.sfzh,a.sfzsb,a.sfzx,a.sfzz,a.sg,a.shbj, ");
		sb.append("a.shgxdwdh1,a.shgxdwdh2,a.shgxgx1,a.shgxgx2,a.shgxgzdw1,a.shgxgzdw2,a.shgxsjhm1,a.shgxsjhm2,a.shgxxm1, ");
		sb.append("a.shgxxm2,a.shgxzw1,a.shgxzw2,a.shzw,a.sjhm,a.ssbh,a.ssch,a.ssld,a.ssyq,a.sybz1,a.sybz2,a.sybz3,a.syd, ");
		sb.append("a.syds,a.sydshi,a.sydx,a.tc,a.thbs,a.tz,a.whcd,a.wydj,a.xb,a.xh,a.xjh,a.xjlb,a.xjlbdm,a.xjztm,a.xldm, ");
		sb.append("a.xm,a.xmpy,a.xmsj,a.xslb,a.xslx,a.xsqrxxbz,a.xszp,a.xw,a.xwzsbh,a.xwzsxlh,a.xwzsxxdz,a.xx,a.xxfx, ");
		sb.append("a.xxszd,a.xxxs,a.xz,a.xzxm,a.yhdm,a.yhkh,a.ykth,a.ylbxh,a.yxdm,a.yzbm,a.zcsxhm,a.zd1,a.zd2,a.zd3, ");
		sb.append("a.zd4,a.zd5,a.zgzs,a.zjdm,a.zkzh,a.zsbh,a.zsjj,a.zsjzrq,a.zslb,a.zsxlh,a.zw,a.zxwyyzdm,a.zyfx, ");
		sb.append("a.zyjb,a.zylb,a.tbsj,a.bxxz,a.sftb,a.sfyqrzs,a.qtyy,a.sfsfs,a.zzmm, ");
		sb.append("b.lxdh1,b.lxdh2,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_gzdz,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy2_xm, ");
		sb.append("b.jtcy2_gx,b.jtcy2_gzdz,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_gzdz, ");
		sb.append("b.jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2,b.email,b.jtszd, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.xymc else c.xymc end xymc, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.xymc else c.xymc end xy, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.zymc else c.zymc end zymc, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.bjmc else c.bjmc end bjmc, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.xydm else c.xydm end xydm, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.zydm else c.zydm end zydm, ");
		sb.append("case when (select substr(dqxn,length(dqxn)-3,4) from xtszb) - a.zxzyqrqnj = 2 then d.nj else c.nj end nj ");
		sb.append("from xsxxb a left join xsfzxxb b on a.xh = b.xh ");
		sb.append("left join view_njxyzybj c on a.bjdm = c.bjdm ");
		sb.append("left join view_njxyzybj_all d on a.zxzyqrqbjdm=d.bjdm where a.xh = ? ");
		
		return dao.getMapNotOut(sb.toString(), new String[] { xh });
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��ͥ��Ա��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-19 ����05:00:01
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxList(String xh)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from xg_xszz_new_jtcyb ");
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��ͥ��Ա��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-19 ����05:00:01
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxXsList(String xh)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,b.mc jtcygxmc , c.zzmmmc,d.mc jkzkmc,substr(a.ylzd3,7,8) sfzcsrq ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sb.append(" ,t1.mzmc,t2.gjdqmc,t3.zycymc,t4.zyjszwmc,t5.zwjbmc ");
			sb.append(" ,(select  to_char(sysdate,'yyyy') - to_char(substr(a.csny,0,4)) from dual ) jtcynl ");
		}
		
		sb.append(" from xg_xszz_new_jtcyb a");
		sb.append(" left join xszz_jtcygxb b on a.cygx=b.dm");
		sb.append(" left join zzmmdmb c on a.CYZZMM = c.ZZMMDM left join xg_xsxx_jkztb d ");
		sb.append("  on a.cyjkzk=d.dm");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sb.append(" left join mzdmb t1 on a.cymz = t1.mzdm ");
			sb.append(" left join ZXBZ_GJDQDM t2 on a.ylzd4 = t2.gjdqdm ");
			sb.append(" left join zxbz_zycym t3 on a.ylzd5 = t3.zycydm ");
			sb.append(" left join zxbz_zyjszw t4 on a.zyjszw = t4.zyjszwdm ");
			sb.append(" left join zxbz_zwjb t5 on a.zwjb = t5.zwjbdm ");
		}
				
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	public List<HashMap<String, String>> getJtcyList(String xh,String n)throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,b.mc jtcygxmc , c.zzmmmc,d.mc jkzkmc,substr(a.ylzd3,7,8) sfzcsrq ");
		sb.append(" from xg_xszz_new_jtcyb a");
		sb.append(" left join xszz_jtcygxb b on a.cygx=b.dm");
		sb.append(" left join zzmmdmb c on a.CYZZMM = c.ZZMMDM left join xg_xsxx_jkztb d ");
		sb.append("  on a.cyjkzk=d.dm");
		sb.append(" where xh=? and rownum<=?");
		String[] inputValue = { xh,n };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	/**
	 * @����:����guid��ȡ��ͥ��Ա��Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 9:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [guid]
	 * @return: com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.JtcyxxModel
	 */
	public HashMap<String,String> getJtcyById(String guid) {
		String sql = "select * from XG_XSZZ_NEW_JTCYB where guid=?";
		return dao.getMapNotOut(sql,new String[]{guid});
	}

	/**
	 * 
	 * @����:��ͥ��Ա��Ϣ�޸�
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-19 ����06:43:30
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateJtcyxx(String xh, List<JtcyxxModel> list,User user)
			throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("insert into xg_xszz_new_jtcyb");
		sb.append("(guid,xh,cygx,cyxm,cynl,cyzzmm,cymz,cyzy,cyxxdw,cylxdh,cynsr,cyjkzk,dcsj,ylzd1,ylzd2,ylzd3,ylzd4,ylzd5,jtcyzjlx,jtcyzjhm,csny)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (JtcyxxModel jtcyxxModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, jtcyxxModel.getCygx(),
						jtcyxxModel.getCyxm(),jtcyxxModel.getCynl(),
						jtcyxxModel.getCyzzmm(),jtcyxxModel.getCymz(),
						jtcyxxModel.getCyzy(),jtcyxxModel.getCyxxdw(),
						jtcyxxModel.getCylxdh(),jtcyxxModel.getCynsr(),
						jtcyxxModel.getCyjkzk(), jtcyxxModel.getDcsj(),
						jtcyxxModel.getYlzd1(),jtcyxxModel.getYlzd2(),
						jtcyxxModel.getYlzd3(),jtcyxxModel.getYlzd4(),
						jtcyxxModel.getYlzd5(),jtcyxxModel.getJtcyzjlx(),
						jtcyxxModel.getJtcyzjhm(),jtcyxxModel.getCsny()};
				paramList.add(param);
				//�ж�xg_xszz_new_jtcyb���Ƿ����һ�������ݣ������򲻴�������������Ҳ������
				if(xgxt.utils.String.StringUtils.isNotNull(jtcyxxModel.getGuid())){
					HashMap<String,String> temp = getJtcyById(jtcyxxModel.getGuid());
					//�ж�temp ��jtcyxxModel�ڵ�ֵ�Ƿ���ȣ�������JtcyxxModel��дequals������
					if(!StringUtils.equals(xh,temp.get("xh")) || !StringUtils.equals(jtcyxxModel.getCygx(),temp.get("cygx")) ||
						!StringUtils.equals(jtcyxxModel.getCyxm(),temp.get("cyxm")) || !StringUtils.equals(jtcyxxModel.getCynl(),temp.get("cynl")) ||
						!StringUtils.equals(jtcyxxModel.getCyzzmm(),temp.get("cyzzmm")) || !StringUtils.equals(jtcyxxModel.getCymz(),temp.get("cymz"))||
						!StringUtils.equals(jtcyxxModel.getCyzy(),temp.get("cyzy")) ||!StringUtils.equals(jtcyxxModel.getCyxxdw(),temp.get("cyxxdw")) ||
						!StringUtils.equals(jtcyxxModel.getCylxdh(),temp.get("cylxdh")) ||!StringUtils.equals(jtcyxxModel.getCynsr(),temp.get("cynsr")) ||
						!StringUtils.equals(jtcyxxModel.getCyjkzk(),temp.get("cyjkzk")) ||!StringUtils.equals(jtcyxxModel.getDcsj(),temp.get("dcsj")) ||
						!StringUtils.equals(jtcyxxModel.getYlzd1(),temp.get("ylzd1")) ||!StringUtils.equals(jtcyxxModel.getYlzd2(),temp.get("ylzd2")) ||
						!StringUtils.equals(jtcyxxModel.getYlzd3(),temp.get("ylzd3")) ||!StringUtils.equals(jtcyxxModel.getYlzd4(),temp.get("ylzd4")) ||
						!StringUtils.equals(jtcyxxModel.getYlzd5(),temp.get("ylzd5")) ||!StringUtils.equals(jtcyxxModel.getJtcyzjlx(),temp.get("jtcyzjlx")) ||
						!StringUtils.equals(jtcyxxModel.getJtcyzjhm(),temp.get("jtcyzjhm")) ||!StringUtils.equals(jtcyxxModel.getCsny(),temp.get("csny"))){
						StringBuffer sql = new StringBuffer();
						sql.append("insert into xg_xszz_new_jtcyb_lsb");
						sql.append("(xh,cygx,cyxm,cynl,cyzzmm,cymz,cyzy,cyxxdw,cylxdh,cynsr,cyjkzk,dcsj,");
						sql.append("ylzd1,ylzd2,ylzd3,ylzd4,ylzd5,jtcyzjlx,jtcyzjhm,csny,czr,czrxm,czsj,czlx)");
						sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'�޸�')");
						String[] param1 = {xh, temp.get("cygx"),
								temp.get("cyxm"),temp.get("cynl"),temp.get("cyzzmm"),temp.get("cymz"),
								temp.get("cyzy"),temp.get("cyxxdw"),temp.get("cylxdh"),temp.get("cynsr"),
								temp.get("cyjkzk"),temp.get("dcsj"),temp.get("ylzd1"),temp.get("ylzd2"),
								temp.get("ylzd3"),temp.get("ylzd4"),temp.get("ylzd5"),temp.get("jtcyzjlx"),
								temp.get("jtcyzjhm"),temp.get("csny"),
								user.getUserName(),user.getRealName(), GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss")
						};
						dao.runUpdate(sql.toString(),param1);
					}

				}

			}
			StringBuffer sb1 = new StringBuffer();
			sb1.append("delete from xg_xszz_new_jtcyb ");
			sb1.append(" where xh=?");
			String[] input = { xh };
			dao.runDelete(sb1.toString(), input);// ɾ��ԭ��¼

			dao.runBatch(sb.toString(), paramList);
		}


		return true;
	}

	public boolean updateJtcyxx(String xh, List<JtcyxxModel> list)
			throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("insert into xg_xszz_new_jtcyb");
		sb.append("(guid,xh,cygx,cyxm,cynl,cyzzmm,cymz,cyzy,cyxxdw,cylxdh,cynsr,cyjkzk,dcsj,ylzd1,ylzd2,ylzd3,ylzd4,ylzd5,jtcyzjlx,jtcyzjhm,csny)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		String guid = null;
		if (list != null && list.size() > 0) {

			for (JtcyxxModel jtcyxxModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, jtcyxxModel.getCygx(),
						jtcyxxModel.getCyxm(),jtcyxxModel.getCynl(),
						jtcyxxModel.getCyzzmm(),jtcyxxModel.getCymz(),
						jtcyxxModel.getCyzy(),jtcyxxModel.getCyxxdw(),
						jtcyxxModel.getCylxdh(),jtcyxxModel.getCynsr(),
						jtcyxxModel.getCyjkzk(), jtcyxxModel.getDcsj(),
						jtcyxxModel.getYlzd1(),jtcyxxModel.getYlzd2(),
						jtcyxxModel.getYlzd3(),jtcyxxModel.getYlzd4(),
						jtcyxxModel.getYlzd5(),jtcyxxModel.getJtcyzjlx(),
						jtcyxxModel.getJtcyzjhm(),jtcyxxModel.getCsny()};
				paramList.add(param);
			}

			StringBuffer sb1 = new StringBuffer();
			sb1.append("delete from xg_xszz_new_jtcyb ");
			sb1.append(" where xh=?");
			String[] input = { xh };
			dao.runDelete(sb1.toString(), input);// ɾ��ԭ��¼

			dao.runBatch(sb.toString(), paramList);
		}


		return true;
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��ѵ��Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:20:34
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPxxxList(String xh)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append(" from xg_xsxx_pxxxb ");
		sb.append(" where xh=? ");
		sb.append(" order by pxkssj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:��ѵ��Ϣ�޸�
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:20:52
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updatePxxx(String xh, List<PxxxModel> list) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_pxxxb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_pxxxb");
		sb.append("(pxid,xh,pxkssj,pxjssj,pxdd,pxnr)");
		sb.append(" values(?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (PxxxModel pxxxModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, pxxxModel.getPxkssj(),
						pxxxModel.getPxjssj(), pxxxModel.getPxdd(),
						pxxxModel.getPxnr() };
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ�����
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:20:34
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkList(String xh)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append(" from xg_xsxx_hjqkb ");
		sb.append(" where xh=? ");
		sb.append(" order by hjsj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:������޸�
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-18 ����02:20:52
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateHjqk(String xh, List<HjqkModel> list) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_hjqkb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_hjqkb");
		sb.append("(hjid,xh,hjsj,fjdw,hjmc)");
		sb.append(" values(?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (HjqkModel hjqkModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, hjqkModel.getHjsj(),
						hjqkModel.getFjdw(), hjqkModel.getHjmc() };
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��������£�
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-10-23 ����10:22:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param n
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxHjqkNewList(String xh,String n)
	throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.*,t.hjsj sqsjs,t.hjmc xmmc,t.fjdw bjdw from (select * ");
		sb.append(" from xg_xsxx_new_hjqkb ");
		sb.append(" where xh=? ");
		sb.append(" order by hjsj desc,rowid )t where rownum<= ? ");
		String[] inputValue = { xh, n };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ��������£�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-3 ����01:53:07
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkNewList(String xh)
	throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append(" from xg_xsxx_new_hjqkb ");
		sb.append(" where xh=? ");
		sb.append(" order by hjsj desc,rowid ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:������޸ģ��£�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-3 ����01:53:07
	 * @�޸ļ�¼:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateHjqkNew(String xh, List<HjqkNewModel> list) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_new_hjqkb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼
		sb = new StringBuffer();
		if ("10876".equals(Base.xxdm)) {//�㽭����ѧԺ���Ի�
			sb.append("insert into xg_xsxx_new_hjqkb(hjid,xh,hjsj,fjdw,hjmc,bz,hjje)values(?,?,?,?,?,?,?)");
		}else {
			sb.append("insert into xg_xsxx_new_hjqkb(hjid,xh,hjsj,fjdw,hjmc,bz)values(?,?,?,?,?,?)");
		}
		String guid = null;
		if (list != null && list.size() > 0) {
			for (HjqkNewModel hjqkModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param;
				if ("10876".equals(Base.xxdm)) {//�㽭����ѧԺ���Ի�
					param = new String[]{ guid, xh, hjqkModel.getHjsj(),hjqkModel.getFjdw(), hjqkModel.getHjmc(), hjqkModel.getBz(),hjqkModel.getHjje() };
				}else {
					param = new String[]{ guid, xh, hjqkModel.getHjsj(),hjqkModel.getFjdw(), hjqkModel.getHjmc(), hjqkModel.getBz() };
				}
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}
	
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����10:11:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGgsxjlList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append(" from XG_XSXX_NEW_GGSXJL ");
		sql.append(" where xh = ? ");
		sql.append(" order by fxsj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
			
	}
	
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����11:07:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGgsxjl(String xh, List<GgsxjlModel> list) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from XG_XSXX_NEW_GGSXJL ");
		sql.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼

		sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_GGSXJL");
		sql.append("(ggid,xh,ggsxdw,rzsj,fxsj,gw)");
		sql.append(" values(?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (GgsxjlModel ggsxjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, ggsxjlModel.getGgsxdw(),
						ggsxjlModel.getRzsj(), ggsxjlModel.getFxsj(), ggsxjlModel.getGw() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}

		return true;
	}
		
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����10:11:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDgsxjlList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append(" from XG_XSXX_NEW_DGSXJL ");
		sql.append(" where xh = ? ");
		sql.append(" order by fxsj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
			
	}
	
	/**
	 * 
	 * @����: ����ʵϰ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-17 ����11:07:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateDgsxjl(String xh, List<DgsxjlModel> list) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from XG_XSXX_NEW_DGSXJL ");
		sql.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼

		sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_DGSXJL");
		sql.append("(dgid,xh,ggsxdw,rzsj,fxsj,fjid)");
		sql.append(" values(?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (DgsxjlModel dgsxjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, dgsxjlModel.getGgsxdw(),
						dgsxjlModel.getRzsj(), dgsxjlModel.getFxsj(), dgsxjlModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}

		return true;
	}
	
	/**
	 * 
	 * @����: ���˼������鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:48:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsGrjlList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.xwmc ");
		sql.append(" from xg_xsxx_grjl a ");
		sql.append(" left join xwdmb b on a.xwdm = b.xwdm ");
		sql.append(" where xh = ? ");
		sql.append(" order by xh desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
			
	}
	
	/**
	 * 
	 * @����: ���˼������޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:49:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsGrjl(String xh, List<GrjlModel> list) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_xsxx_grjl ");
		sql.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼

		sql = new StringBuffer();
		sql.append("insert into xg_xsxx_grjl");
		sql.append("(jlid,xh,qssj,zzsj,xxdw,xxnr,xwdm,zmr,zmrdz,bz)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (GrjlModel grjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, grjlModel.getQssj(),
						grjlModel.getZzsj(), grjlModel.getXxdw(),
						grjlModel.getXxnr(), grjlModel.getXwdm(),
						grjlModel.getZmr(), grjlModel.getZmrdz(),
						grjlModel.getBz() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}

		return true;
	}
	
	/**
	 * 
	 * @����: �����������鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:48:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsGzjlList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.zwmc ");
		sql.append(" from xg_xsxx_gzjl a ");
		sql.append(" left join zwb b on a.crdzzw = b.zwdm ");
		sql.append(" where xh = ? ");
		sql.append(" order by xh desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
			
	}
	
	/**
	 * 
	 * @����: �����������޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-19 ����02:49:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsGzjl(String xh, List<GzjlModel> list) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_xsxx_gzjl ");
		sql.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼

		sql = new StringBuffer();
		sql.append("insert into xg_xsxx_gzjl");
		sql.append("(jlid,xh,qssj,zzsj,gzdw,gznr,crdzzw,crzyzw,gzzmr,zmrdz,bz)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (GzjlModel gzjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, gzjlModel.getQssj(),
						gzjlModel.getZzsj(), gzjlModel.getGzdw(),
						gzjlModel.getGznr(), gzjlModel.getCrdzzw(),
						gzjlModel.getCrzyzw(), gzjlModel.getGzzmr(),
						gzjlModel.getZmrdz(), gzjlModel.getBz() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}

		return true;
	}
		
	/**
	 * 
	 * @����: ѧ��������Ϣ���鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-16 ����03:27:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsyhxxList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.yhmc ");
		sql.append(" from XG_XSXX_NEW_XSYHXX a ");
		sql.append(" left join DMK_YH b on a.yhdm = b.yhdm ");
		sql.append(" where xh = ? ");
		sql.append(" order by xh desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
			
	}
	
	/**
	 * 
	 * @����: ѧ��������Ϣ���޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-16 ����03:33:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsyhxx(String xh, List<XsyhxxModel> list) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from XG_XSXX_NEW_XSYHXX ");
		sql.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼

		sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_XSYHXX");
		sql.append("(yhxxid,xh,yhdm,yhkh)");
		sql.append(" values(?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(XsyhxxModel xsyhxxModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, xsyhxxModel.getYhdm(),
						xsyhxxModel.getYhkh() };
				paramList.add(param);			
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
	}
	
	/**
	 * 
	 * @����: ���˽�����Ϣ���鿴��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-6 ����04:03:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrhjxxList(String xh) throws Exception { 
		
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,a.bjdw bmmc ");
		sb.append(" from xg_xsxx_new_grhjqk a ");
		//sb.append(" left join zxbz_xxbmdm b on a.bjdw = b.bmdm ");
		sb.append(" where xh=? ");
		sb.append(" order by hjsj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
		
	}
	
	/**
	 * 
	 * @����: ���˽�����Ϣ���޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-6 ����04:04:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGrhjxx(String xh, List<GrhjxxModel> list) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_xsxx_new_grhjqk ");
		sql.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼

		sql = new StringBuffer();
		sql.append("insert into xg_xsxx_new_grhjqk");
		sql.append("(hjid,xh,hjsj,jxmc,bjdw,jxjb,jxlb)");
		sql.append(" values(?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for(GrhjxxModel grhjxxModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, grhjxxModel.getHjsj(),
						grhjxxModel.getJxmc(), grhjxxModel.getBjdw(),
						grhjxxModel.getJxjb(), grhjxxModel.getJxlb()};
				paramList.add(param);			
			}
			dao.runBatch(sql.toString(), paramList);
		}
		
		return true;
		
	}
	
	/**
	 * 
	 * @����: ���ѧ���춯��ʾ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-27 ����04:39:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZdxjydList(String xh) throws Exception { 
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.*, (select xqmc from XQDZB where xqdm = a.xq) xqmc ");
		sb.append(" from VIEW_ZJDX_XJYDB a ");
		sb.append(" where xh=? ");
		sb.append(" order by ydsj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
		
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ��б�
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuCjList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,a.xn||b.xqmc xnxq,b.xqmc,b.xqjb from cjb a ");
		sb.append(" left join xqdzb b on a.xq=b.xqdm where a.xh=? ");
		sb.append(" order by a.xn desc,a.xq desc,a.kcmc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	public List<HashMap<String, String>> getStuCjOfXnList(String xh,String xn) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,b.xqmc,b.xqjb from cjb a ");
		sb.append(" left join xqdzb b on a.xq=b.xqdm where a.xh=? ");
		sb.append(" and xn=? order by a.xn,a.xq desc,a.kcmc ");
		String[] inputValue = { xh,xn };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	public List<HashMap<String, String>> getStuCjOfXnXqList(String xh,String xn,String xq) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,b.xqmc,b.xqjb from cjb a ");
		sb.append(" left join xqdzb b on a.xq=b.xqdm where a.xh=? ");
		sb.append(" and xn=? and xq=? order by a.xn,a.xq desc,a.kcmc ");
		String[] inputValue = { xh,xn,xq };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:ÿѧ����ѧ�ּ��㡢ƽ��ѧ�ּ���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-9-6 ����05:17:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getStuXFJDOfXnList(String xh,String xn) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(a.xf * a.jd) zxfjd, round((sum(a.xf * a.jd) / sum(a.xf)),2) pjxfjd from cjb a ");
		sb.append(" left join xqdzb b on a.xq=b.xqdm where a.xh=? ");
		sb.append(" and xn=?");
		String[] inputValue = { xh,xn };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ�ȼ����Գɼ�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-28 ����10:14:30
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getStuDjcjList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,b.xqmc,b.xqjb from xsdjksb a  ");
		sb.append(" left join xqdzb b on a.xq=b.xqdm where a.xh=? ");
		sb.append(" order by a.xn desc,a.xq desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * ��ȡѧ����λ��Ϣ�б�
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuQgzxXsgwxxList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select (select bmmc from zxbz_xxbmdm c where c.bmdm=b.yrdwdm) yrdwmc,b.gwmc,a.gwdm");
		sb
				.append(",(select gwxzmc from xg_qgzx_gwxzdmb c where b.gwxzdm=c.gwxzdm) gwxzmc,a.xh,a.sgsj");
		sb
				.append(",(case when a.zgzt='zg' then '�ڸ�' else '�˸�' end) zgzt,a.tgsj,a.tgyy,a.sgsj||'-'||a.tgsj zgsj from xg_qgzx_xsgwxxb a,xg_qgzx_gwxxb b ");
		sb.append("where a.gwdm=b.gwdm and xh=? ");
		sb.append("order by a.sgsj desc,b.yrdwdm,a.gwdm");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * ��ȡѧ����ѵ�������
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuJxkhqk(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*from zfsoft_bpmx.w_xg_jxgl_xsjxkhb a left join zfsoft_bpmx.bpm_pro_run_his b on a.id = b.businesskey where b.status = '2' and f_xh =?");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	/**
	 * ��ȡѧ����𷢷��б�
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuQgzxCjffList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select (select bmmc from zxbz_xxbmdm c where c.bmdm=b.yrdwdm) yrdwmc ");
		sb.append(" ,a.xh,a.gwdm,substr(a.ffsj,0,4)||'��'||substr(a.ffsj,6,2)||'��' yf,a.gs,a.je,b.gwmc ");
		sb.append(" from xg_qgzx_cjff a  ");
		sb.append(" left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm ");
		//sb.append(" left join xg_qgzx_gwffztb c on b.xn = c.xn and b.yrdwdm = c.yrdwdm and a.ffsj = c.ffny ");
		sb.append(" where a.xh=?");
		sb.append(" order by ffsj desc,gwmc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:����ѧ�Ų�ѯΥ�ʹ����б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.zzwh,a.zzsj,a.zzyj,a.sjly,a.cfsbid,a.cflbdm,a.cfyydm,a.cfyj,a.cflsh,a.ssfilepath,a.nd,a.filepath,a.cfid,a.xh,a.xn,a.xq,(case when a.cfggw is not null then a.cfggw else a.cflbmc end) cflbmc,a.cfyymc,a.cfsj,a.cfwh,a.wjsj,a.sbb,a.sbsj,a.wjssjg,a.bz,a.sfsc,a.sssj,a.sswh,a.ssjg,a.cfggw,a.ssyj,a.jcwh,a.jcsj,a.jcyj,a.fjmc,a.fj,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc ");
		sql.append(",(case when cfggw is not null then cfggw else cflbmc end) cflbmc ");
		if("12684".equalsIgnoreCase(Base.xxdm)){
			sql.append(",decode(jcsj,null,decode(ssjg,'wsh','δ���','shz','�����','cxcf','�����ѽ��','���ֳ���'),'�����ѽ��') as jg");
		}else{
			sql.append(",decode(jcwh,null,decode(ssjg,'wsh','δ���','shz','�����','cxcf','�����ѽ��','���ֳ���'),'�����ѽ��') as jg");
		}
		sql.append(" from xg_wjcf_wjcfb a where nvl(ssjg,0) <> '��������' and nvl(ssjg,0) <> '��������' and xh=? order by xn desc,xq desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ���춯�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXjydList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.ydlbmc,t3.xqmc ");
		sql
				.append("from bks_xjydxx t1 left join dm_ydlb t2 on t1.ydlbm=t2.ydlbm ");
		sql.append("  left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" where t1.shzt='ͨ��' and t1.xh=? order by t1.sqsj desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	/**
	 * 
	 * @����:��ȡ��Ԣ��Ϣ�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-29 ����10:38:21
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getStuGyxxList(String xh) {
		StringBuilder sb = new StringBuilder();
		sb.append("select a.*   ");
		if("13645".equals(Base.xxdm)){
			
			sb.append(" , b.oddelec ");
		}
		sb.append(" from view_xg_gygl_new_cwxx a ");
		if("13645".equals(Base.xxdm)){
			
			sb.append(" left join view_xg_bwxy_sdtj b on a.lddm = b.lddm and a.qsh = b.qsh ");
		}
		sb.append(" where xh = ? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * ��ȡ¥������Ա��Ϣ
	 */
	public List<HashMap<String,String>> getGyfdyxx(String xh){
		StringBuilder sb = new StringBuilder();
		sb.append(" select (select c.ldmc from xg_gygl_new_ldxxb c where a.lddm = c.lddm)ldmc,b.xm,b.lxdh ");
		sb.append(" from zjjd_xg_gygl_new_gyfdyb a ");
		sb.append(" left join fdyxxb b on a.yhm=b.zgh ");
		sb.append(" where a.lddm in (select lddm from xg_gygl_new_cwxxb where xh = ?) ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * ��ȡ¥������Ա��Ϣ
	 */
	public List<HashMap<String,String>> getGyglyxx(String xh){
		StringBuilder sb = new StringBuilder();
		sb.append(" select (select c.ldmc from xg_gygl_new_ldxxb c where a.lddm = c.lddm)ldmc,b.xm,b.lxdh ");
		sb.append(" from xg_gygl_new_gyfdyb a ");
		sb.append(" left join fdyxxb b on a.yhm=b.zgh ");
		sb.append(" where a.lddm in (select lddm from xg_gygl_new_cwxxb where xh = ?) ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:��ȡ��Ԣ���ɴ�����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-12 ����02:41:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getGyjlclxxList(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (select a.*,substr(wjsj,1,10) wjrq, (select xqmc from xqdzb where xqdm = wjxq) xqmc,  a.xh || '!!shen!!'  || a.gyjllbdm || '!!shen!!'  || wjsj pkValue," );
		sql.append("(select xm from view_xsxxb b where a.xh=b.xh) xm,");
		sql.append("(select xb from view_xsxxb b where a.xh=b.xh) xb,ldmc||','||qsh||','||cwh||'�Ŵ�' zsqs,");
		sql.append("(select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb," );
		sql.append("nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') cljgmc ");
		sql.append("from xg_gygl_new_gyjlb a ) a where a.xh =? and a.shzt = 'tg' order by a.wjsj desc");
		
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @��������ȡ��Ԣ���ɴ�����Ϣ(����δͨ��)
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��5�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String , String>> getGyjlclxxAllList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,decode(a.shzt,'tg','ͨ��','btg','��ͨ��','δ���') shztmc,substr(wjsj,1,10) wjrq, (select xqmc from xqdzb where xqdm = wjxq) xqmc,  a.xh || '!!shen!!'  || a.gyjllbdm || '!!shen!!'  || wjsj pkValue," );
		sql.append("(select xm from view_xsxxb b where a.xh=b.xh) xm,");
		sql.append("(select xb from view_xsxxb b where a.xh=b.xh) xb,ldmc||','||qsh||','||cwh||'�Ŵ�' zsqs,");
		sql.append("(select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb," );
		sql.append("nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') cljgmc ");
		sql.append("from xg_gygl_new_gyjlb a ) a where a.xh =?  order by a.wjsj desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ԣ�춯��Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-12 ����02:55:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getGyydxxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from VIEW_NEW_DC_GYGL_YGJG a where a.XH = ? order by a.czsj desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ԣ������Ϣ
	 * @���ߣ�1036
	 * @���ڣ�2014-3-14 ����03:13:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getGypyxxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r, a.* , b.xh from view_xg_gygl_pyxxwhb a  left join XG_GYGL_NEW_CWXXB b on a.lddm = b.lddm and a.qsh = b.qsh where 1=1 and b.xh = ?");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	
	/**
	 * 
	 * @����:��ȡ���һ�ܹ�Ԣ���ţ��㽭�����Ի���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-7-2 ����02:43:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getLgGypyxxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r, a.*, b.xh from view_xg_gygl_pyxxwhb a ");
		sql.append("left join XG_GYGL_NEW_CWXXB b on a.lddm = b.lddm and a.qsh = b.qsh ");
		sql.append("where 1 = 1 and b.xh = ? ");
		sql.append("and to_date(a.pysj,'yyyy-mm-dd') between (select max(to_date(kssj,'yyyy-mm-dd')) from xg_gygl_new_wsjc_jcrcb) ");
		sql.append("and (select max(to_date(jssj,'yyyy-mm-dd')) from xg_gygl_new_wsjc_jcrcb) ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡѧ���ɲ���Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����10:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getXsgbxxList(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from VIEW_NEW_DC_SZDW_XSDWWH a where a.XH = ? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ������У��Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����10:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getJqlxxxList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t1.*,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm,t2.xymc, t2.zymc, t2.nj,t3.xqmc,t2.sjhm, "); 
		sql.append(" decode(t1.sjlx,'0','ֱ��¼��','1','����� ') as sjlymc "); 
		sql.append(" from XG_RCSW_JQLX t1 left join view_xsjbxx t2 on t1.xh = t2.xh left join xqdzb t3 on t1.xq=t3.xqdm where t1.sqzt='1' ) a where xh = ?  ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ�����Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:14:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getQjjgxxList(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from VIEW_NEW_DC_RCSW_QJJG a where a.xh = ? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ֤��������Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZjbbxxList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.bbjgid,t1.xh,t1.xszbblxdm,t1.sjly,t1.sqsj,decode(t1.sflq, '1', '��', '0','��',sflq) sflq,t1.lqsj,t1.ffyh, ");
		sql.append(" t2.xm, t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj, ");
		sql.append(" decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk,t3.xszbblxmc ");
		sql.append(" from xg_rcsw_xszbb_xszbbjgb  t1 left join view_xsxxb t2  on t1.xh = t2.xh ");
		sql.append(" left join xg_rcsw_xszbb_bblxwhb t3 on t1.xszbblxdm = t3.xszbblxdm  where t1.xszbblxdm is not null ");
		sql.append(" ) a where xh = ?   ");
		
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ���Żݿ���Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getHcyhkxxList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (");
		sql.append(" select t1.ccqjjgid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,t1.bz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		sql.append(" t1.sjly,t1.ccqjtxid,t2.xm, t2.xb,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj,t3.xqmc ");
		sql.append("  from xg_rcsw_hcyhk_hcccqjjgb ");
		sql.append(" t1  left join view_xsxxb t2 on t1.xh = t2.xh  ");
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm ) a where xh = ? ");
		
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡѧ�ڱ���ע����Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����02:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getXqbdzcxxList(String xh) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String xq = Base.currXq;
		String xn = Base.currXn;
		
		sql.append("select * from (")
		.append("select a.* , b.id , b.zcsj , b.zcr ,")
		.append("nvl2(b.xn , b.xn ,'"+ xn +"') xn , ")
		.append("nvl2(b.xq , b.xq ,'" + xq + "') xq , ")
		.append("(select nvl2(sum(zd1) , '��Ƿ��' , '��Ƿ��') from RCSW_CWSJB where xn = nvl2(b.xn , b.xn ,'" + xn + "') and xq = nvl2(b.xq , b.xq ,'" + xq + "') and xh = a.xh ) sfqf , ")
		.append("(select xqmc from xqdzb where xqdm = nvl2(b.xq , b.xq ,'" + xq + "')) xqmc ,")
		.append(" decode(b.zczt,NULL , '-1' , '0' , '0' , '1' , '1') zczt,")
		.append("decode(b.zczt,NULL , 'δע��' , '1' , '��ע��' , '0' , 'ע�᳷��') zcztmc")
		.append(" ")
		.append("from view_xsjbxx a ")
		.append(" left join xg_xsxx_bdzcb b on a.xh = b.xh ")
		.append(") t1  ")
		.append(" where xn = ? and xq = ?  and xh = ?");
		String[] inputValue = { xn , xq , xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ��ԢΥ����Ϣ�б�
	 * @���ߣ���С��[���ţ�1024]
	 * @���ڣ�2014-4-15 ����03:16:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getGywjxxList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from (select * from (select a.*,substr(wjsj,1,10) wjrq, (select xqmc from xqdzb where xqdm = wjxq) xqmc,  a.xh || '!!shen!!'  || a.gyjllbdm || '!!shen!!'  || wjsj pkValue," );
		sql.append("(select xm from view_xsxxb b where a.xh=b.xh) xm,");
		sql.append("(select xb from view_xsxxb b where a.xh=b.xh) xb,ldmc||','||qsh||','||cwh||'�Ŵ�' zsqs,");
		sql.append("(select gyjllbdlmc from xg_gygl_new_gyjllbdlb b where a.gyjllbdldm=b.gyjllbdldm)||'('||");
		sql.append("(select gyjllbmc from xg_gygl_new_gyjllbdmb b where a.gyjllbdm=b.gyjllbdm)||')' wjlb," );
		sql.append("nvl((select c.gyjlcfmc from xg_gygl_new_gyjlcflbb c where a.cljg=c.gyjlcfdm),'δ����') cljgmc ");
		sql.append("from xg_gygl_new_gyjlb a ) a where xh = ? ) a ");
		
		String[] inputValue = { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	
	/**
	 * 
	 * @����:��ȡ������Ʒ�б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-29 ����10:38:21
	 * @�޸ļ�¼:
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQswpList(String xh) {
		StringBuilder sb = new StringBuilder();
		sb
				.append("select b.*,(select wpdlmc from xg_gygl_wfxy_qswpdlb e where b.wpdldm=e.wpdldm) wpdlmc,");
		sb
				.append("(select wplbmc from xg_gygl_wfxy_qswplbb e where b.wplbdm=e.wplbdm) wplbmc ");
		sb
				.append("from xg_gygl_new_cwxxb a right join xg_gygl_wfxy_qswpxxb b on a.lddm=b.lddm and a.qsh=b.qsh where a.xh=?");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:��ȡ���÷�����Ϣ
	 */
	public List<HashMap<String, String>> getFyffxxList(String xh){
		StringBuilder sb = new StringBuilder();
		sb.append("select * from view_new_dc_rcsw_fyffjg t1 where xh = ?");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 */
	public List<HashMap<String, String>> getKqxxList(String xh){
		StringBuilder sb = new StringBuilder();
		sb.append("select * from view_new_dc_rcsw_kqgl t1 where xh = ? order by xnxq desc,kqsj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ������ϰ������Ϣ
	 */
	public List<HashMap<String, String>> getZwzxKqxxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.qqlxmc,t5.xm,t5.xb");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm left join XG_RCSW_ZWZXKQ_QQLXB t4 on t1.qqlxdm = t4.qqlxdm left join view_xsjbxx t5 on ");
		sql.append(" t1.xh = t5.xh where t1.xh=? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��ȡ������ϰ������Ϣ
	 */
	public List<HashMap<String, String>> getWpsqjgList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jgid,t1.sqid,t1.xn,t1.xh,t1.sqsj,t1.sqly,t1.sjly,t2.*,t3.*,t3.mc xmlbmc ");
		sql.append(" from xg_axjz_axcsxmjgb  t1 left join xg_xszz_axcsxmb t2 on t1.xmdm=t2.xmdm left join xg_xszz_axcslbb t3  on t2.xmlb = t3.dm ");
		sql.append(" where t1.xh = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��ɫͨ����Ϣ
	 */
	public List<HashMap<String, String>> getLstdList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jgid,t1.xh,t1.lxdm,t1.sqly,t1.sqsj,(select xqmc from xqdzb t4 where t1.xq=t4.xqdm) xqmc,t1.bz,t2.lxmc ");
		sql.append(" from xg_rcsw_lstd_jgb  t1 left join xg_rcsw_lstd_lxwhb t2  on t1.lxdm = t2.lxdm  ");
		sql.append(" where t1.xh= ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ż�ȡѧ��ѧ����ᾭ����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-23 ����05:06:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXlshjlList(String xh) {
		StringBuffer sql = new StringBuffer();
		if("12867".equals(Base.xxdm)){
			sql.append("select rownum r ,a.jlid,a.xh, case when a.jzsj is not null then (a.qzrq||'--'||a.jzsj) else a.qzrq end qzsj,a.qzrq,a.jzsj,a.gzdd,zmrjhc,a.zw from XG_XSXX_XLSHJL a where a.xh = ? order by a.qzrq");
		}else{
			sql.append("select rownum r , a.* from XG_XSXX_XLSHJL a where a.xh = ? order by a.qzrq");
		}
		String[] inputValue = new String[] { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @������ѧ�������� ѧ����ᾭ��ֻȡ4��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��13�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXlshjlList4line(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum R,a.* from(select * from (select * from XG_XSXX_XLSHJL  where xh = ? order by qzrq desc) where rownum<=4) a order by a.qzrq ");
		String[] inputValue = new String[] { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	
	public List<HashMap<String, String>> getXlshjlList(String xh,String n) {
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum R,a.* from(select * from (select * from XG_XSXX_XLSHJL  where xh = ? order by qzrq desc) where rownum<=?) a order by a.qzrq ");
		String[] inputValue = new String[] { xh,n };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:����ѧ����ᾭ����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-24 ����08:35:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return boolean ��������
	 * @throws
	 */
	public boolean updateXlshjlxx(String xh, List<XlshjlModel> list)
			throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from XG_XSXX_XLSHJL ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into XG_XSXX_XLSHJL");
		sb.append("(JLID,XH,QZRQ,GZDD,ZMRJHC,ZW,JZSJ,FJID)");
		sb.append(" values(?,?,?,?,?,?,?,?)");

		String guid = null;

		if (list != null && list.size() > 0) {

			for (XlshjlModel xlshjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, xlshjlModel.getQzrq(),xlshjlModel.getGzdd(),
						 xlshjlModel.getZmrjhc() ,  xlshjlModel.getZw(), xlshjlModel.getJzsj(),xlshjlModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/**
	 * ͨ��������ȡ��������ֶ�
	 * 
	 * @param tableName
	 * @return
	 */
	public String[] getColumnByTable(String tableName) {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> rs = dao
				.getList(
						"select column_name from user_col_comments where table_name=upper(?)",
						new String[] { tableName },
						new String[] { "column_name" });
		String[] array = new String[rs.size()];
		for (int i = 0; i < rs.size(); i++) {
			array[i] = rs.get(i).get("column_name");
		}
		return array;
	}

	/**
	 * ����ѧ��������Ϣ
	 * 
	 * @param valueMap
	 * @return
	 */
	public boolean saveInfo(HashMap<String, String> valueMap) throws Exception {
		CommService service = new CommService();
		// ����SQL
		StringBuilder sql = new StringBuilder();
		// �ֶ���
		StringBuilder comments = new StringBuilder();
		// �ֶ�ֵ
		StringBuilder commentsValue = new StringBuilder();
		// ֵ
		List<String> inputV = new ArrayList<String>();
		sql.append(" insert into xsxxb");
		sql.append(" ( ");

		commentsValue.append(" values( ");
		int n = 0;
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {
			String paramName = entry.getKey();
			String paramValue = entry.getValue();
			if (n != 0) {
				comments.append(",");
				commentsValue.append(",");
			}
			comments.append(paramName);
			commentsValue.append("?");
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		commentsValue.append(" ) ");
		// ������ֶ�
		sql.append(comments);
		sql.append(") ");
		// ����ֵ
		sql.append(commentsValue);
		boolean flag = dao.runUpdate(sql.toString(), inputV
				.toArray(new String[] {}));
		return flag;
	}

	/**
	 * 
	 * @����:����ѧ�ŵ�ѧ�����룬��Ƭ��������Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-29 ����03:03:11
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveXsqtxx(XsxxglModel model) throws Exception {
		String[] sql = { "insert into xsfzxxb(xh) values ('" + model.getXh()
				+ "')" };
		int[] result = dao.runBatch(sql);
		boolean flag = dao.checkBatch(result);
		return flag;
	}

	/**
	 * 
	 * @����:�޸�ѧ����Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-3 ����02:20:08
	 * @�޸ļ�¼:
	 * @param valueMap
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateInfo(HashMap<String, String> valueMap)
			throws Exception {
		CommService service = new CommService();
		// ����SQL
		StringBuilder sql = new StringBuilder();
		// �ֶ���
		StringBuilder comments = new StringBuilder();
		// �޸�����
		StringBuilder query = new StringBuilder();
		// �޸��ֶ���ֵ
		List<String> inputV = new ArrayList<String>();
		// �޸���������ֵ
		List<String> queryV = new ArrayList<String>();
		// �����ֶ�
		String pk = "xh";
		sql.append(" update xsxxb set ");
		int n = 0;
		boolean flag = false;
		if (valueMap.get(pk) != null) {
			for (Map.Entry<String, String> entry : valueMap.entrySet()) {
				// ��(�ֶ���)
				String paramName = entry.getKey();
				// ֵ(�ֶ�ֵ)
				String paramValue = entry.getValue();
				// ------------------����ƴ�� begin --------------------
				if (pk.equalsIgnoreCase(paramName)) {
					// ------------�������� begin------------
					query.append(" and ");
					query.append(pk);
					query.append("=?");
					// ------------�������� end------------
					queryV.add(paramValue);
				} else {
					if (n != 0) {
						comments.append(",");
					}
					// -------�޸���Ϣ begin----------
					comments.append(paramName);
					comments.append("=?");
					// -------�޸���Ϣ end ----------
					n++;
					// --------------�޸�ֵ------------------
					inputV.add(service.unicode2Gbk(paramValue));
				}
				// ---------....�����ֶ��ǲ���Ҫ�޸ĵ� end----------
			}
			// ������ֶ�
			sql.append(comments);
			sql.append(" where 1=1  ");
			sql.append(query);
			inputV.addAll(queryV);

			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		}

		return flag;
	}

	/**
	 * 
	 * @����:�޸�ѧ��������Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-3 ����02:20:08
	 * @�޸ļ�¼:
	 * @param valueMap
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateInfoXsfzxx(HashMap<String, String> valueMap)
			throws Exception {
		//////////ѧ��������Ϣ������¼�����ڣ�������/////////
		XsxxglModel model = new XsxxglModel();
		String xh = valueMap.get("xh");
		if(xh != null && !xh.equals("")){
			model.setXh(valueMap.get("xh"));			
			String sql = "select count(xh) cnt from xsfzxxb where xh=?";
			String oneRs = dao.getOneRs(sql, new String[] { xh }, "cnt");
			if(oneRs == null||oneRs.equals("0")){
				saveXsqtxx(model);
			}
		}
		///////////////////
		
		CommService service = new CommService();
		// ����SQL
		StringBuilder sql = new StringBuilder();
		// �ֶ���
		StringBuilder comments = new StringBuilder();
		// �޸�����
		StringBuilder query = new StringBuilder();
		// �޸��ֶ���ֵ
		List<String> inputV = new ArrayList<String>();
		// �޸���������ֵ
		List<String> queryV = new ArrayList<String>();
		// �����ֶ�
		String pk = "xh";
		sql.append(" update xsfzxxb set ");
		int n = 0;
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {
			// ��(�ֶ���)
			String paramName = entry.getKey();
			// ֵ(�ֶ�ֵ)
			String paramValue = entry.getValue();
			// ------------------����ƴ�� begin --------------------
			if (pk.equalsIgnoreCase(paramName)) {
				// ------------�������� begin------------
				query.append(" and ");
				query.append(pk);
				query.append("=?");
				// ------------�������� end------------
				queryV.add(paramValue);
			} else {
				if (n != 0) {
					comments.append(",");
				}
				// -------�޸���Ϣ begin----------
				comments.append(paramName);
				comments.append("=?");
				// -------�޸���Ϣ end ----------
				n++;
				// --------------�޸�ֵ------------------
				inputV.add(service.unicode2Gbk(paramValue));
			}
			// ---------....�����ֶ��ǲ���Ҫ�޸ĵ� end----------
		}
		// ������ֶ�
		sql.append(comments);
		sql.append(" where 1=1  ");
		sql.append(query);
		boolean flag = false;
		inputV.addAll(queryV);
		flag = dao.runUpdate(sql.toString(), inputV.toArray(new String[] {}));
		return flag;
	}

	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ�Ƿ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-29 ����03:03:28
	 * @�޸ļ�¼:
	 * @param xh
	 * @return String ��������
	 * @throws
	 */
	public String chkStuIsExists(String xh) {
		String sql = "select count(xh) cnt from xsxxb where xh=?";
		String oneRs = dao.getOneRs(sql, new String[] { xh }, "cnt");
		return oneRs;
	}

	protected void setTableInfo() {
		super.setTableName("xsxxb");
		super.setKey("xh");
		super.setClass(XsxxglModel.class);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ԣ�������б�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-23 ����11:06:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGywsfList(String xh) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select (select ldmc from xg_gygl_new_ldxxb c where a.lddm=c.lddm)ldmc,a.qsh,b.mc,a.fs,a.dj ");
		sql.append("from xg_gygl_new_wsjc_qsfsb a left join xg_gygl_new_wsjc_jcrcb b on a.guid=b.guid ");
		sql.append("left join xg_gygl_new_cwxxb d on a.lddm=d.lddm and a.qsh=d.qsh where d.xh = ? order by jcrq desc ) where rownum = 1 ");
		
		String[] inputValue = { xh};
		
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	/**
	 * ��ȡ��Ԣ�������б����м�¼��
	 */
	public List<HashMap<String, String>> getGywsfAllList(String xh) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select (select ldmc from xg_gygl_new_ldxxb c where a.lddm=c.lddm)ldmc,a.qsh,b.mc,case when b.jclx='0' or b.jclx is null then a.fs else a.dj end fsdjxj,a.fs,a.dj ");
		sql.append("from xg_gygl_new_wsjc_qsfsb a left join xg_gygl_new_wsjc_jcrcb b on a.guid=b.guid ");
		sql.append("left join xg_gygl_new_cwxxb d on a.lddm=d.lddm and a.qsh=d.qsh where d.xh = ? order by jcrq desc  ");
		
		String[] inputValue = { xh};
		
		return dao.getListNotOut(sql.toString(), inputValue);
	}
		
	/**
	 * 
	 * @����:(�������д����ѯ�������ƽ����Ƽ�ʦ����ѧ���Ի��ֶ���������2)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-5 ����03:24:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYhmc(String yhdm) {
		String sql = " select yhmc from DMK_YH where yhdm = ?";
		
		return dao.getOneRs(sql, new String[]{yhdm}, "yhmc");
		
	}
	
	/**
	 * 
	 * @����: �ж��Ƿ�ͱ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-10-22 ����05:04:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getSfdb(String xh) {
			String sql = null;
			sql = "select sfdb from XG_XSZZ_NEW_JTQKDCB where xh=? ";
			return dao.getOneRs(sql, new String[]{xh}, "sfdb");
			
	}
	
	
	/**
	 * 
	 * @����: ��ȡ��ǰϵͳ������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-10-23 ����10:33:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDqrq(String xh) {
		String sql = null;
		sql = "select to_char(sysdate,'yyyy-mm-dd') dqrq from xsxxb where xh=? ";
		return dao.getOneRs(sql, new String[]{xh}, "dqrq");
		
	}
	
	/**
	 * @����:����ģ�����ƣ���Ҫ������ ��ѧ���ɲ���ѯ����ϲ�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-16 ����08:41:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String , String>> getPjXsgbxxList(String xh) throws Exception{
		StringBuilder sql =  new StringBuilder();
		sql.append("select a.rzsj,a.zwmc,b.xn,b.xqmc,b.xmmc,b.xmje,b.sqsj,b.sqsjs from ( ");
		// ѧ���ɲ�
		sql.append("select rownum xsdwwhrownum,a.* from ( ");
		sql.append("select a.rzsj,a.zwmc from VIEW_NEW_DC_SZDW_XSDWWH a where a.xh = ? and a.zzzt='1' order by rzsj desc ");
		sql.append(") a ");
		sql.append(") a full join ( ");
		// ����
		sql.append("select rownum pjjgbrownum,a.* from ( ");
		sql.append("select t1.xn,t2.xqmc,t1.xmmc,t1.xmje,t1.sqsj , to_char(to_date(t1.sqsj , 'yyyy-MM-dd hh24:mi:ss') , 'yyyy-MM-dd') sqsjs ");
		sql.append("from xg_pjpy_new_pjjgb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.sqsj desc");
		sql.append(") a ");
		sql.append(") b on (a.xsdwwhrownum=b.pjjgbrownum) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh});
	}

	/** 
	 * @����:�㽭��ѧѧ԰��ѯ�����Ը�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-11 ����11:41:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXycx(XsxxglModel model, User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from ( ");
		sql.append("select a.*,b.nj njh,b.xydm xydmh,b.xymc xymch,b.zymc zymch,b.bjmc bjmch, ");
		sql.append(" (select shengmc||shimc||xianmc jgmc from xg_view_dmk_qx where qxdm=a.jg) jgmc, ");
		sql.append(" (select shengmc||shimc||xianmc sydqmc from xg_view_dmk_qx where qxdm=a.syd) sydqmc, ");
		sql.append(" (select shengmc||shimc||xianmc hkszdmc from xg_view_dmk_qx where qxdm=a.hkszd) hkszdmc ");
		sql.append("from view_xsbfxx a left join view_njxyzybj_all b on a.zxzyqrqbjdm = b.bjdm ");
		sql.append("where nvl((select substr(dqxn,length(dqxn)-3,4) nj from xtszb) - a.zxzyqrqnj,0) = 2) ");
		sql.append("where sfzx = '��У' ");
		if(!"xx".equalsIgnoreCase(user.getUserStatus())){
			sql.append("and (xydm = '"+user.getUserDep()+"' or  xydmh = '"+user.getUserDep()+"' )");
		}
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @����:�㽭����ѧԺ���Ի�
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-08 ����11:41:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXszxData(XsxxglModel model, User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.*,rownum rn from view_zjjcxy_byszxqkhz t ) where 1=1 ");
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return dao.getListNotOut( sql.toString(), inputV);
	}
	
	/** 
	 * @����:�㽭����ѧԺ���Ի�
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-10 ����04:41:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZhqkData(XsxxglModel model, User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.*,rownum rn from view_zjjcxy_zxqkhzexport t ) where 1=1 ");
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return dao.getListNotOut( sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:��ȡ�ɶ���Ժ�ĳɼ���Ϣ���ڴ�ӡѧ����
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-30 ����10:51:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCdtyCjxxByxh(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxx_cdty_cjxx where xh=?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
	
	/**
	 * ��ѯѧ��֤��Ϣ
	 */
	public HashMap<String, String> cxXsz(String csdm, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.csdm,replace(decode(b.csz, null, a.csz, b.csz),'LODOP.PRINT_INITA(0,0,794,1123,\"��ӡ\");','') csz from ");
		sql.append(" (select csdm,csz from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = 'public') a left join ( ");
		sql.append(" select csdm,csz from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = ?) b on (a.csdm=b.csdm) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{csdm, csdm, user.getUserName()});
	}
	
	/**
	 * ����ѧ��֤��Ϣ
	 */
	public boolean bcXsz(String csdm, String csz, User user) throws Exception{
		String deleteSql = " delete from xg_pjpy_new_ryzsmbszb where csdm = ? and zgh = ? ";
		dao.runDelete(deleteSql, new String[]{csdm, user.getUserName()});
		
		String insertSql = " insert into xg_pjpy_new_ryzsmbszb(csdm,zgh,csz) values(?,?,?) ";
		return dao.runUpdate(insertSql, new String[]{csdm, user.getUserName(), csz});
	}
	/**
	 * @����: ͨ��ѧ�Ż�ȡ���˼��������ڽ��зָ�--���������߼�����ѧУѧ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-9-29 ����09:43:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrjlList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r,");
		sql.append("a.*,");
		sql.append(" substr(a.qzrq, 0, 4) qsnf, ");
		sql.append(" substr(a.qzrq, 6, 2) qsyf, ");
		sql.append(" substr(a.qzrq, 12, 4) zznf, ");
		sql.append(" substr(a.qzrq, 17, 2) zzyf ");
		sql.append(" from XG_XSXX_XLSHJL a ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by a.qzrq ");
		String[] inputValue = new String[] { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	/**
	 * ��ְͨҵ����ѧԺ���еȵ�
	 */
		public List<HashMap<String, String>> getCxdt(String xh) {
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from ( ");
			sql.append(" select a.xh,a.xsid,a.pj,(select cxdjmc from xsxx_cxdjdmb e where ");
			sql.append(" a.pj=e.cxdjdm) cxdjmc,py cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
			sql.append(" b.xm,b.xb,b.nj,b.xymc,b.zymc,b.bjmc,b.xydm,b.zydm,a.bjdm,a.xn,a.xq from xg_xsxx_cxpy_pysb_jg a left join ");
			sql.append(" view_xsjbxx b on a.xh=b.xh) ");
			sql.append(" where xh = ? ");
			String[] inputValue = new String[] { xh };
			return dao.getListNotOut(sql.toString(), inputValue);
		}
		/**
		 * @����: ��ͨ�Ƽ�ְҵѧԺ���Ի������
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2016-5-10 ����11:00:26
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> gethjqk(String xh) {
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.xn,a.xh,b.xqmc,xmmc from xg_pjpy_new_pjjgb a left join xqdzb b on a.xq = b.xqdm ");
			sql.append(" where xh = ? ");
			String[] inputValue = new String[] { xh };
			return dao.getListNotOut(sql.toString(), inputValue);
		}
	/**
	 * @����: ���������߼�����ѧУѧ����ͨ��ѧ�ų�ȡ1--3ѧ��ѧҵ�ɼ�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-10-15 ����09:27:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXycjoneList(String xh)throws Exception {
		StringBuffer sql = new StringBuffer();
		String[] inputValue = new String[] { xh };
		sql.append(" select kcmc kc1, ");
		sql.append(" nvl(wm_concat(case ");
		sql.append(" when xn = nj || '-' || (nj + 1) and xq = '01' then cj end), ");
		sql.append(" '-') as xn1xq1,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = nj || '-' || (nj + 1) and xq = '02' then cj end),");
		sql.append(" '-') as xn1xq2,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 1) || '-' || (nj + 2) and xq = '01' then cj end),");
		sql.append(" '-') as xn2xq1,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 1) || '-' || (nj + 2) and xq = '02' then cj end),");
		sql.append(" '-') as xn2xq2,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 2) || '-' || (nj + 3) and xq = '01' then cj end),");
		sql.append(" '-') as xn3xq1,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 2) || '-' || (nj + 3) and xq = '02' then cj end), ");
		sql.append(" '-') as xn3xq2");
		sql.append(" from cjb a, view_xsxxb b");
		sql.append(" where a.xh = ? ");
		sql.append(" and a.xh = b.xh");
		sql.append(" and a.xn in (nj || '-' || (nj + 1), ");
		sql.append(" (nj + 1) || '-' || (nj + 2), ");
		sql.append(" (nj + 2) || '-' || (nj + 3)) ");
		sql.append(" group by kcmc");
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	/**
	 * @����: ���������߼�����ѧУѧ����ͨ��ѧ�ų�ȡ4--6ѧ��ѧҵ�ɼ�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-10-19 ����11:21:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXycjtwoList(String xh)throws Exception {
		StringBuffer sql = new StringBuffer();
		String[] inputValue = new String[] { xh };
		sql.append(" select kcmc kc2, ");
		sql.append(" nvl(wm_concat(case ");
		sql.append(" when xn = (nj + 3) || '-' || (nj + 4) and xq = '01' then cj end), ");
		sql.append(" '-') as xn4xq1,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 3) || '-' || (nj + 4) and xq = '02' then cj end),");
		sql.append(" '-') as xn4xq2,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 4) || '-' || (nj + 5) and xq = '01' then cj end),");
		sql.append(" '-') as xn5xq1,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 4) || '-' || (nj + 5) and xq = '02' then cj end),");
		sql.append(" '-') as xn5xq2,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 5) || '-' || (nj + 6) and xq = '01' then cj end),");
		sql.append(" '-') as xn6xq1,");
		sql.append(" nvl(wm_concat(case");
		sql.append(" when xn = (nj + 5) || '-' || (nj + 6) and xq = '02' then cj end), ");
		sql.append(" '-') as xn6xq2");
		sql.append(" from cjb a, view_xsxxb b");
		sql.append(" where a.xh = ? ");
		sql.append(" and a.xh = b.xh");
		sql.append(" and a.xn in ((nj + 3) || '-' || (nj + 4),");
		sql.append(" (nj + 4) || '-' || (nj + 5), ");
		sql.append(" (nj + 5) || '-' || (nj + 6))");
		sql.append(" group by kcmc");
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����: ȡѧ�����������4�������ݴ�ѧ���Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-29 ����11:10:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkNewFourList(String xh) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.hjmc xmmc,a.fjdw bjdw,substr(a.hjsj, '0', '4') || '��' || substr(a.hjsj, '6', '2') || '��' sqsjs ");
		sb.append(" from xg_xsxx_new_hjqkb a ");
		sb.append(" where xh=? and rownum <= 4 ");
		sb.append(" order by a.hjsj desc,rowid ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	/**
	 * @����: �㽭��ѧ������ѧ�ǼǱ�_���Ի������˼������½�ȡ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-4 ����11:49:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXxhgzjlList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT jlid,xh,gzdd,zmrjhc,zw,");
		sql.append(" qzrq qsny, ");
		sql.append(" jzsj zzny ");
		sql.append(" from XG_XSXX_XLSHJL a ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by a.qzrq ");
		String[] inputValue = new String[] { xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	/**
	 * ��ͨ�Ƽ�ְҵ����ѧԺ�ɼ���ѧ��ѧ��ѧ�ڵĳɼ�
	 */
	public List<HashMap<String, String>> getXnXqcj(String xn,String xn1,String xh,String xq)throws Exception {
		StringBuffer sql = new StringBuffer();
		String[] inputValue = new String[] {xn,xn1,xh,xq,};
		sql.append(" select a.kcmc,c.kcxzdm kcxz,a.zxs cxbj,a.xf,a.cj");
		sql.append(" from cjb a ");
		sql.append(" left join  view_xsxxb b on a.xh = b.xh ");
		sql.append(" left join xg_xsxx_kcxzdmb c on c.kcxzmc = a.kcxz ");
		sql.append(" where a.xn in ((nj + to_number(?)) || '-' || (nj + to_number(?)+1)) ");
		sql.append(" and a.xq = ? ");
		sql.append(" and a.xh = ? order by a.kcxz,a.kcmc");
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	/**
	 * ��ͨ�Ƽ�ְҵ����ѧԺ�ɼ�����ȡѧ���γ�ѧ�ں���ѧ��
	 */
	public HashMap<String, String> getKcxq(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ((b.nj + 0) || '-' || (b.nj + 1)||'-'||'01') kcxq1, ");
		sql.append(" ((b.nj + 0) || '-' || (b.nj + 1)||'-'||'02') kcxq2, ");
		sql.append(" ((b.nj + 1) || '-' || (b.nj + 2)||'-'||'01') kcxq3, ");
		sql.append(" ((b.nj + 1) || '-' || (b.nj + 2)||'-'||'02') kcxq4, ");
		sql.append(" ((b.nj + 2) || '-' || (b.nj + 3)||'-'||'01') kcxq5, ");
		sql.append(" ((b.nj + 2) || '-' || (b.nj + 3)||'-'||'02') kcxq6, ");
		sql.append(" ((b.nj + 3) || '-' || (b.nj + 4)||'-'||'01') kcxq7, ");
		sql.append(" ((b.nj + 3) || '-' || (b.nj + 4)||'-'||'02') kcxq8, ");
		sql.append(" ((b.nj + 4) || '-' || (b.nj + 5)||'-'||'01') kcxq9, ");
		sql.append(" ((b.nj + 4) || '-' || (b.nj + 5)||'-'||'02') kcxq10, ");
		sql.append(" sum(nvl(xf, 0)) zxf ");
		sql.append(" from cjb a left join  view_xsbfxx b ");
		sql.append(" on a.xh = b.xh ");
		sql.append(" where a.xh = ? group by b.nj ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	/**
	 * @����: ��ҵ�����Ŀ���ɼ�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-5-25 ����07:48:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBysj(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select bysjtm,bysjcj,bysjzdjs from bysfzxxb where xh =? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	/**
	 * @����: �ȼ����Գɼ�[��ͨ�Ƽ�]
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-5-26 ����07:48:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDjkscj(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select djksmc||':'||cj djkscj from xsdjksb where xh =? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}	

	/**
	 * 
	 * @����: ��ѧ��ƽ�����㣨���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-23 ����10:04:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getXfjdList(String xh,String nj) throws Exception{
		StringBuilder sql = new StringBuilder();
		String dqnd = Base.currNd;
	    int njZ = Integer.parseInt(nj);
		int size = Integer.parseInt(dqnd) - njZ;
		if(size <= 0){
			sql.append(" select '' xn, '' xf, '' jd from dual ");		
		} else {
			for (int i = 0; i < size; i++) {
				
				sql.append(" select '"+(njZ+i)+"-"+(njZ+i+1)+"' xn, zjdx.func_zghdxf('"+xh+"','"+(njZ+i)+"-"+(njZ+i+1)+"') xf, zjdx.func_zgqbkcpjjd('"+xh+"','"+(njZ+i)+"-"+(njZ+i+1)+"') jd from dual ");
				if (i+1 != size){
					sql.append(" union all ");
				}
			}
		}
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/*
	 * ȡ��Ӧѧ���꼶
	 */
	public String getXsnj(String xh) {
		String sql = null;
		sql = "select nj from view_xsbfxx where xh=? ";
		return dao.getOneRs(sql, new String[]{xh}, "nj");
		
	}
	
	/**
	 * 
	 * @����: �����໪ѧ��֤��ӡ��ȡѧ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-24 ����07:01:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXszdyxxXaph(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xymc,");
		sql.append(" zymc,");
		sql.append(" bjmc,");
		sql.append(" to_char(to_date(rxrq, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm') rxny,");
		sql.append(" xm,");
		sql.append(" xb,");
		sql.append(" mzmc,");
		sql.append(" to_char(to_date(csrq,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') csrq,");
		sql.append(" jgmc,");
		sql.append(" xh");
		sql.append(" from view_xsxxb");
		sql.append(" where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����: �񽱸����ϴ�����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-27 ����03:01:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFileData(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t.*, t.bjdw bjdwmc");
		sql.append(" from xg_xsxx_new_grhjqk t");
		sql.append(" left join xg_comm_fileupload_data t1");
		sql.append(" on t.gid = t1.gid");
		//sql.append(" left join zxbz_xxbmdm t2");
		//sql.append(" on t.bjdw = t2.bmdm");
		sql.append(" where xh = ?");
		sql.append(" order by hjsj asc");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-10 ����06:37:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String  getYfjGid(String hjid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select gid from xg_xsxx_new_grhjqk where hjid = ?");
		return dao.getOneRs(sql.toString(), new String[]{hjid}, "gid");
	}
	
	/**
	 * 
	 * @����:���˽�����Ϣ���޸ģ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-10 ����07:09:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @param hjid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGrhjxxByjg(String xh, List<GrhjxxModel> list,String[] hjid) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from xg_xsxx_new_grhjqk ");
		sql.append(" where xh=?");
		String[] input = { xh };
		

		StringBuffer sql1 = new StringBuffer();
		sql1.append("insert into xg_xsxx_new_grhjqk");
		sql1.append("(hjid,xh,hjsj,jxmc,bjdw,jxjb,jxlb,gid)");
		sql1.append(" values(?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				GrhjxxModel grhjxxModel =list.get(i);
				String gid = this.getYfjGid(hjid[i]);
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, grhjxxModel.getHjsj(),
						grhjxxModel.getJxmc(), grhjxxModel.getBjdw(),
						grhjxxModel.getJxjb(), grhjxxModel.getJxlb(),gid};
				paramList.add(param);			
			}
			dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼
			dao.runBatch(sql1.toString(), paramList);
		}
		
		return true;
		
	}
	
	/**
	 * ��ѯ����ѧ��������е�ѧ�����֤��
	 * 
	 */
	public HashMap<String, String> getExsisMmbXsSfzMm(String xh){
		StringBuffer sql = new StringBuffer();
		//ƴ�ӷ�
		String pjf = "";
		//�Ϻ�������Ի��ж�
		if("11458".equals(Base.xxdm)){
			pjf = "sdju";
		}
		sql.append(" select xh,");
		sql.append(" case ");
		sql.append(" when  (a.sfzh is null or length(a.sfzh) < 18)");
		sql.append(" then '"+pjf+"' || '000000'");
		sql.append(" else '"+pjf+"' || substr(a.sfzh,'13','6')");
		sql.append(" end mm");
		sql.append(" from view_xsjbxx a where a.xh = ?");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���������޸���־��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-19 ����04:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ip
	 * @param czr
	 * @param xgmmxh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean runInsertMmXgLog(String ip,String czr,String xgmmxh) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into XG_MMXG_LOG(ip,czr,xgmmxh,xgsj) values(?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'))");
		return dao.runUpdate(sql.toString(), new String[]{ip,czr,xgmmxh});
	}
	
	/**
	 * @����: �Ѹ��˼����͹��������ŵ�һ��ȡֵ
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-10-21 ����10:09:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrjlShjlList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.qssj, a.zzsj, a.xxdw, a.zmr, a.zmrdz ");
		sql.append(" from xg_xsxx_grjl a where a.xh = ? ");
		sql.append(" union all ");
		sql.append(" select b.qssj, b.zzsj, b.gzdw xxdw, b.gzzmr zmr, b.zmrdz ");
		sql.append(" from xg_xsxx_gzjl b where b.xh = ? ");
		String[] inputValue = new String[] { xh,xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @��������ѧ�о� �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��14�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getKxyjList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from XG_XSXX_NEW_KXYJ ");
		sql.append(" where xh = ?  order by sj desc ");
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
			
	}
	
	/**
	 * @��������ѧ�о� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateKxyj(String xh, List<KxyjModel> list) throws Exception {
		List<String[]> paramList = new ArrayList<String[]>();
		String sqlstr="delete from XG_XSXX_NEW_KXYJ where xh=?";
		dao.runDelete(sqlstr, new String[]{xh});// ɾ��ԭ��¼

		StringBuffer sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_KXYJ");
		sql.append("(xh,lwmc,jb,sj,kw)");
		sql.append(" values(?,?,?,?,?)");
		if (list != null && list.size() > 0) {
			for (KxyjModel kxyjModel : list) {
				String[] param = { xh, kxyjModel.getLwmc(),kxyjModel.getJb(), kxyjModel.getSj(),kxyjModel.getKw() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		return true;
	}
	
	/**
	 * @�����������о� �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getKtyjList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from XG_XSXX_NEW_KTYJ ");
		sql.append(" where xh = ?  order by sj desc ");
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
			
	}
	
	/**
	 * @�����������о� �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateKtyj(String xh, List<KtyjModel> list) throws Exception {
		List<String[]> paramList = new ArrayList<String[]>();
		String sqlstr="delete from XG_XSXX_NEW_KTYJ where xh=?";
		dao.runDelete(sqlstr, new String[]{xh});// ɾ��ԭ��¼

		StringBuffer sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_KTYJ");
		sql.append("(xh,ktmc,sj,cylb,jb)");
		sql.append(" values(?,?,?,?,?)");
		if (list != null && list.size() > 0) {
			for (KtyjModel ktyjModel : list) {
				String[] param = { xh, ktyjModel.getKtmc(),ktyjModel.getSj(), ktyjModel.getCylb(),ktyjModel.getJb() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		return true;
	}
	
	/**
	 * @���������´�ҵ��Ŀ �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getCxcyxmList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from XG_XSXX_NEW_CXCYXM ");
		sql.append(" where xh = ?  order by sj desc ");
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
			
	}
	
	/**
	 * @���������´�ҵ��Ŀ �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateCxcyxm(String xh, List<CxcyxmModel> list) throws Exception {
		List<String[]> paramList = new ArrayList<String[]>();
		String sqlstr="delete from XG_XSXX_NEW_CXCYXM where xh=?";
		dao.runDelete(sqlstr, new String[]{xh});// ɾ��ԭ��¼

		StringBuffer sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_CXCYXM");
		sql.append("(xh,cxcyxm,sj,zbdw,jb)");
		sql.append(" values(?,?,?,?,?)");
		if (list != null && list.size() > 0) {
			for (CxcyxmModel cxcyxmModel : list) {
				String[] param = { xh, cxcyxmModel.getCxcyxm(),cxcyxmModel.getSj(), cxcyxmModel.getZbdw(),cxcyxmModel.getJb() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		return true;
	}
	
	/**
	 * @������ѧ�ƾ��� �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXkjsList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from XG_XSXX_NEW_XKJS ");
		sql.append(" where xh = ?  order by sj desc ");
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
			
	}
	
	/**
	 * @������ѧ�ƾ��� �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateXkjs(String xh, List<XkjsModel> list) throws Exception {
		List<String[]> paramList = new ArrayList<String[]>();
		String sqlstr="delete from XG_XSXX_NEW_XKJS where xh=?";
		dao.runDelete(sqlstr, new String[]{xh});// ɾ��ԭ��¼

		StringBuffer sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_XKJS");
		sql.append("(xh,xkjs,sj,jx,zbdw,jb)");
		sql.append(" values(?,?,?,?,?,?)");
		if (list != null && list.size() > 0) {
			for (XkjsModel xkjsModel : list) {
				String[] param = { xh, xkjsModel.getXkjs(),xkjsModel.getSj(),xkjsModel.getJx(),xkjsModel.getZbdw(),xkjsModel.getJb() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		return true;
	}
	
	/**
	 * @����������֤�� �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJnzsList(String xh) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from XG_XSXX_NEW_JNZS ");
		sql.append(" where xh = ?  order by sj desc ");
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
			
	}
	
	/**
	 * @����������֤�� �б� ɽ���ƾ����Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean updateJnzs(String xh, List<JnzsModel> list) throws Exception {
		List<String[]> paramList = new ArrayList<String[]>();
		String sqlstr="delete from XG_XSXX_NEW_JNZS where xh=?";
		dao.runDelete(sqlstr, new String[]{xh});// ɾ��ԭ��¼

		StringBuffer sql = new StringBuffer();
		sql.append("insert into XG_XSXX_NEW_JNZS");
		sql.append("(xh,jnzs,sj,fzdw,dj)");
		sql.append(" values(?,?,?,?,?)");
		if (list != null && list.size() > 0) {
			for (JnzsModel jnzsModel : list) {
				String[] param = { xh,jnzsModel.getJnzs(),jnzsModel.getSj(),jnzsModel.getFzdw(),jnzsModel.getDj() };
				paramList.add(param);
			}
			dao.runBatch(sql.toString(), paramList);
		}
		return true;
	}
	
	/**
	 * @������ѧ����Ϣ(��)-����ע��-ѧ���ɷ� ����ְҵ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��19�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXsjfList(String xh) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select *  from VIEW_CW_BKS_XSSFB ");
		sql.append(" where xh = ?  order by SFQJDM desc ");
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
	}

	/**
	 * @����:���棨У�⣩�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��27�� ����5:43:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param hjqkList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateXsxwhjqk(String xh, List<XsxwhjqkModel> xwhjqkList) throws  Exception{

		//boolean result = false;
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xwhjqkb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xwhjqkb");
		sb.append("(xh,xn,xq,hjjb,hjmc,hjdc,fzdw,fzsj)");
		sb.append(" values(?,?,?,?,?,?,?,?)");
		if (xwhjqkList != null && xwhjqkList.size() > 0) {
			for (XsxwhjqkModel hjqkModel : xwhjqkList) {
				String[] param = {xh, hjqkModel.getXn(),hjqkModel.getXq(), hjqkModel.getHjjb(),
						hjqkModel.getHjmc(),hjqkModel.getHjdc(),hjqkModel.getFzdw(),hjqkModel.getFzsj() };
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/** 
	 * @����:��ȡѧ����У�⣩�������������·ְҵ����ѧԺ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��31�� ����9:34:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxwhjqkList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select j.*,q.xqmc,j.hjmc xmmc ");
		sb.append(" from xg_xsxx_xwhjqkb j ");
		sb.append(" left join xqdzb q on j.xq = q.xqdm ");
		sb.append(" where xh=? ");
		sb.append(" order by xn desc,xq desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * @����:����ȼ�������ϢList(�����Ƽ���ѧ)
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����10:59:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param djkscjList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateDjkscj(String xh, List<DjkscjModel> djkscjList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xskzxx_djkscjb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xskzxx_djkscjb");
		sb.append("(xh,djksdm,zyjnspdm,hqsj,bz��fjid)");
		sb.append(" values(?,?,?,?,?,?)");
		if (djkscjList != null && djkscjList.size() > 0) {
			for (DjkscjModel djkscjModel : djkscjList) {
				String[] param = {xh, djkscjModel.getDjksdm(),djkscjModel.getZyjnspdm(),djkscjModel.getHqsj(),djkscjModel.getBz(),djkscjModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/** 
	 * @����:��ȡ�ȼ�������ϢList(�����Ƽ���ѧ)
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����10:26:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDjkscjList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select t1.*,t2.mc djksmc,t3.mc zyjnspmc from xg_xsxx_xskzxx_djkscjb t1 ");
		sb.append(" left join xg_xsxx_xskzxx_djksdmb t2 ");
		sb.append(" on t1.djksdm = t2.dm ");
		sb.append(" left join xg_xsxx_xskzxx_zyjnspdmb t3 ");
		sb.append(" on t1.zyjnspdm = t3.dm ");
		sb.append(" where xh=? ");
		sb.append(" order by t1.hqsj desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	public List<HashMap<String, String>> getZcfsList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.* from( select a.*,b.xymc,b.xydm,b.zymc,b.zydm,b.bjmc,b.bjdm,b.xb,b.nj, rank() over(partition by b.bjdm order by a.zhszf desc) zhszfpm from (");
		sb.append(" select a.zcfid,a.xn,a.xh,a.xm,a.zhpxf, round((sum(b.xf*b.cj)/sum(b.xf)),0) pjf,");
		sb.append(" ( a.zhpxf*0.7+ round((sum(b.xf*b.cj)/sum(b.xf)),0)*0.3 ) zhszf  ");
		sb.append(" from xg_pjpy_zhpxf a left join cjb b on a.xh=b.xh and a.xn=b.xn group by a.zcfid,a.xn,a.xh,a.xm,a.zhpxf)a left join view_xsjbxx b on a.xh=b.xh  ) t");
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	/** 
	 * @����:��ȡѧ���ɲ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:02:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsgbjlList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  xg_xsxx_xskzxx_xsgbjlb ");
		sb.append(" where xh=? ");
		sb.append(" order by qsrq desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/** 
	 * @����:��ȡ���ʵ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:02:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getShsjqkList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  xg_xsxx_xskzxx_shsjqkb ");
		sb.append(" where xh=? ");
		sb.append(" order by qsrq desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/** 
	 * @����:��ȡ��������������ϢList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:03:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCgcjjlList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  xg_xsxx_xskzxx_cgcjjlb ");
		sb.append(" where xh=? ");
		sb.append(" order by qsrq desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * @����:����ѧ���ɲ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:42:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xsgbjlList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateXsgbjl(String xh, List<XsgbjlModel> xsgbjlList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xskzxx_xsgbjlb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xskzxx_xsgbjlb");
		sb.append("(xh,qsrq,jsrq,dw,zw,zmr,zzzt,fjid)");
		sb.append(" values(?,?,?,?,?,?,?,?)");
		if (xsgbjlList != null && xsgbjlList.size() > 0) {
			for (XsgbjlModel xsgbjlModel : xsgbjlList) {
				String[] param = {xh, xsgbjlModel.getQsrq(),xsgbjlModel.getJsrq(),xsgbjlModel.getDw(),
						xsgbjlModel.getZw(),xsgbjlModel.getZmr(),xsgbjlModel.getZzzt(),xsgbjlModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/**
	 * @����:�������ʵ�����List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:42:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param shsjqkList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateShsjqk(String xh, List<ShsjqkModel> shsjqkList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xskzxx_shsjqkb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xskzxx_shsjqkb");
		sb.append("(xh,qsrq,jsrq,dw,sjnr,sjcg,zmr,fjid)");
		sb.append(" values(?,?,?,?,?,?,?,?)");
		if (shsjqkList != null && shsjqkList.size() > 0) {
			for (ShsjqkModel shsjqkModel : shsjqkList) {
				String[] param = {xh, shsjqkModel.getQsrq(),shsjqkModel.getJsrq(),shsjqkModel.getDw(),
						shsjqkModel.getSjnr(),shsjqkModel.getSjcg(),shsjqkModel.getZmr(),shsjqkModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/**
	 * @����:�����������������ϢList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����8:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param cgcjjlList
	 * @return
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean updateCgcjjl(String xh, List<CgcjjlModel> cgcjjlList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xskzxx_cgcjjlb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xskzxx_cgcjjlb");
		sb.append("(xh,lb,xmmc,gb,qsrq,jsrq,xmfl,jldw,fjid)");
		sb.append(" values(?,?,?,?,?,?,?,?,?)");
		if (cgcjjlList != null && cgcjjlList.size() > 0) {
			for (CgcjjlModel cgcjjlModel : cgcjjlList) {
				String[] param = {xh, cgcjjlModel.getLb(),cgcjjlModel.getXmmc(),cgcjjlModel.getGb(),
						cgcjjlModel.getQsrq(),cgcjjlModel.getJsrq(),cgcjjlModel.getXmfl(),cgcjjlModel.getJldw(),cgcjjlModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/** 
	 * @����:��ȡ��������List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:09:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFblwList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  xg_xsxx_xskzxx_fblwb ");
		sb.append(" where xh=? ");
		sb.append(" order by fbrq desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/** 
	 * @����:��ȡ������ĿList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:09:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKyxmList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  xg_xsxx_xskzxx_kyxmb ");
		sb.append(" where xh=? ");
		sb.append(" order by lxrq desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/** 
	 * @����:��ȡ�����ɹ�List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:09:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getQtcgList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  xg_xsxx_xskzxx_qtcgb ");
		sb.append(" where xh=? ");
		sb.append(" order by fbny desc");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * @����:���淢������List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:32:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param fblwList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateFblw(String xh, List<FblwModel> fblwList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xskzxx_fblwb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xskzxx_fblwb");
		sb.append("(xh,lwmc,kwmc,kwjb,fbrq,jh,qh,brpm,dyzzwds,bz,fjid)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
		if (fblwList != null && fblwList.size() > 0) {
			for (FblwModel fblwModel : fblwList) {
				String[] param = {xh, fblwModel.getLwmc(),fblwModel.getKwmc(),fblwModel.getKwjb(),
						fblwModel.getFbrq(),fblwModel.getJh(),fblwModel.getQh(),fblwModel.getBrpm(),fblwModel.getDyzzwds(),
						fblwModel.getBz(),fblwModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/**
	 * @����:���������ĿList�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param kyxmList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateKyxm(String xh, List<KyxmModel> kyxmList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xskzxx_kyxmb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xskzxx_kyxmb");
		sb.append("(xh,xmmc,xmly,xmbh,lxrq,jsrq,fzr,bz,fjid)");
		sb.append(" values(?,?,?,?,?,?,?,?,?)");
		if (kyxmList != null && kyxmList.size() > 0) {
			for (KyxmModel kyxmModel : kyxmList) {
				String[] param = {xh, kyxmModel.getXmmc(),kyxmModel.getXmly(),kyxmModel.getXmbh(),
						kyxmModel.getLxrq(),kyxmModel.getJsrq(),kyxmModel.getFzr(),kyxmModel.getBz(),kyxmModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/**
	 * @����:���������ɹ�List�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��22�� ����11:32:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param qtcgList
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateQtcg(String xh, List<QtcgModel> qtcgList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_xskzxx_qtcgb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xskzxx_qtcgb");
		sb.append("(xh,cgmc,cglx,cgjb,fbny,cgzrs,brpm,dyzzwds,bz,fjid)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?)");
		if (qtcgList != null && qtcgList.size() > 0) {
			for (QtcgModel qtcgModel : qtcgList) {
				String[] param = {xh, qtcgModel.getCgmc(),qtcgModel.getCglx(),qtcgModel.getCgjb(),
						qtcgModel.getFbny(),qtcgModel.getCgzrs(),qtcgModel.getBrpm(),qtcgModel.getDyzzwds(),qtcgModel.getBz(),qtcgModel.getFjid()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

	/** 
	 * @����:��ȡ��ƶ����Ϣ�������Ƽ���ѧ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��23�� ����8:52:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getFpbxx(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ylzd13,ylzd14,ylzd15,ylzd16,ylzd17 from  xg_xszz_new_jtqkdcb ");
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	public List<HashMap<String, String>> getQfqk(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select xn,xh,sfxm,je from  XG_XSXX_QFQK ");
		sb.append(" where xh=? order by xn desc,sfxm ");
		return dao.getListNotOut(sb.toString(), new String[]{xh});
	}
	
	public List<HashMap<String, String>> getYktxfls(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  XG_XSXX_YKTXFLS where ");
		if("10346".equalsIgnoreCase(Base.xxdm)) {
			sb.append(" to_number(to_char(sysdate,'yyyymmdd')) - to_number(substr(jysj, 0, 8))<= 7 and ");
		}
		sb.append("  xh=? order by jysj desc ");
		return dao.getListNotOut(sb.toString(), new String[]{xh});
	}
	//����ҽҩ
	public List<HashMap<String, String>> getXsyktcuList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  XG_XSXX_YKTCRJL ");
		sb.append(" where xh=? ");
		return dao.getListNotOut(sb.toString(), new String[]{xh});
	}
	public List<HashMap<String, String>> getTsjyList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  XG_XSXX_TSJYXXB ");
		sb.append(" where xh=? order by jysj desc ");
		return dao.getListNotOut(sb.toString(), new String[]{xh});
	}
	
	/**
	 * @description	�� ��ȡ����Ϣ�������Ƽ���
	 * @author 		�� ������1282��
	 * @date 		��2017-11-29 ����10:38:01
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getHjqkListForXakj(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id,hjjb,jlmc,bjdw,hjsj,hjrs,brpm,bz,xh,jldj, decode(hjjb,'1','Ժ��','2','У��','3','ʡ��','4','���Ҽ�','5','����') hjjbmc," +
				"decode(jldj,'1','�صȽ�','2','һ�Ƚ�','3','���Ƚ�','4','���Ƚ�','5','���㽱','6','��','7','����') jldjmc " +
				" from xg_xsxx_kzxx_hjqkb ");
		sb.append(" where xh=? order by hjsj desc ");
		return dao.getListNotOut(sb.toString(), new String[]{xh});
	}
	
	/**
	 * @description	�� ���»�����������Ƽ���ѧ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-29 ����10:41:53
	 * @param xh
	 * @param sxxxList
	 * @return
	 * @throws Exception
	 */
	public boolean updateHjqkListForXakj(String xh, List<HjqkModel> hjqkList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_kzxx_hjqkb ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_kzxx_hjqkb");
		sb.append("(xh,hjjb,jlmc,bjdw,hjsj,hjrs,brpm,bz,jldj)");
		sb.append(" values(?,?,?,?,?,?,?,?,?)");
		if (hjqkList != null && hjqkList.size() > 0) {
			for (HjqkModel model : hjqkList) {
				String[] param = {xh, model.getHjjb(),model.getJlmc(),model.getBjdw(),model.getHjsj(),model.getHjrs(),model.getBrpm(),model.getBz(),model.getJldj()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}
		return true;
	}
	
	/** 
	 * @����:�޸�ʵϰ��Ϣ(�Ͼ��ߵ�ְҵ����ѧԺ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����02:06:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param shsjqkList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateSxxx(String xh, List<SxxxModel> sxxxList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xgxx_new_sxxx ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xgxx_new_sxxx");
		sb.append("(xh,sxrq,sxqx,sxdw,sfwc)");
		sb.append(" values(?,?,?,?,?)");
		if (sxxxList != null && sxxxList.size() > 0) {
			for (SxxxModel model : sxxxList) {
				String[] param = {xh, model.getSxrq(),model.getSxqx(),model.getSxdw(),
						model.getSfwc()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}
		return true;
	}
	
	/** 
	 * @����:�޸ķ�����Ϣ(�Ͼ��ߵ�ְҵ����ѧУ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����02:22:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param fwxxList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateFwxx(String xh, List<FwxxModel> fwxxList) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xgxx_new_fwxx ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xgxx_new_fwxx");
		sb.append("(xh,fwlx,fwsj,fwdd,fwnr)");
		sb.append(" values(?,?,?,?,?)");
		if (fwxxList != null && fwxxList.size() > 0) {
			for (FwxxModel model : fwxxList) {
				String[] param = {xh, model.getFwlx(),model.getFwsj(),model.getFwdd(),
						model.getFwnr()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}
		return true;
	}
	
	
	/** 
	 * @����:��ȡ������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����04:55:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFwxx(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id,fwlx,fwsj,fwdd,fwnr from xg_xgxx_new_fwxx ");
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	
	/** 
	 * @����:��ȡʵϰ��Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-26 ����04:57:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSxxx(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id,sxrq,sxqx,sxdw,sfwc from xg_xgxx_new_sxxx ");
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	} 
	
	/**
	 * �鿴�ɼ���Ϣ
	 * @param xh
	 * @param xkkh
	 * @return
	 */
	public HashMap<String, String> getCjxx(String xh,String xkkh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,a.xn||b.xqmc xnxq,b.xqmc,b.xqjb,c.BJDM,c.bjmc,c.NJ,c.XYDM,c.XYMC,c.XB,c.xm,c.zymc,c.zydm from cjb a ");
		sql.append(" left join xqdzb b on a.xq=b.xqdm");
		sql.append(" left join view_xsbfxx c");
		sql.append(" on a.xh = c.XH");
		sql.append(" where a.xh = ? and a.xkkh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,xkkh});
	}
	
	/**
	 * �������п���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveYhkh(XsxxglModel model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xsxxb set yhkh =  ?  where xh = ?");
		return dao.runUpdate(sql.toString(), new String[]{model.getYhkh(),model.getXh()});
	}

	public List<HashMap<String, String>> getycsjList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct sqid from view_ycsjts ");
	    return dao.getListNotOut(sql.toString(), new String []{});

	}

	public int[] updateXsxxb(List<HashMap<String, String>> rsList) throws Exception {
		StringBuilder sb = new StringBuilder();
		String[] arr = new String[rsList.size()];
		for (int i = 0; i < rsList.size(); i++) {
			sb = new StringBuilder();
			sb.append("update xsxxb set " );
			HashMap<String, String> map = (HashMap<String, String>) rsList.get(i);
			for (String key : map.keySet()) {
				if(key !="xh" && map.get(key)!=null){
					sb.append(" "+key+"= '"+map.get(key)+"',");
				}
			}
			String sql = sb.toString();
			sql = sql.substring(0, sql.length()-1);
			String endsql= sql+ " where xh='"+rsList.get(i).get("xh")+"'";
				arr[i] = endsql;
		}
		return dao.runBatch(arr);
	}

	public List<HashMap<String, String>> getYcXgzdList(String sqid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sqid,zd,zdz,xgqz from view_ycsjts ");
		sb.append(" where sqid=? ");
		return dao.getListNotOut(sb.toString(), new String[] { sqid });
	}

	public String getxhforsqid(String sqid) {
		String sql = "select distinct xh from view_ycsjts where sqid =?";
		return dao.getOneRs(sql, new String[]{sqid}, "xh");

	}
	
	/**
	 * 
	 * @����: ����ʡ�����е�ר��ѧУ����ʵϰ״̬(zd1)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-5 ����04:12:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSxzt(List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xsxxb set zd1 = ? where xh = ?");
		return dao.runBatchBoolean(sql.toString(), params);
	}
/**
 * @description	��ȡ���｡����Ϣ  �½�ҽ�ƴ��ѧԺ
 * @author 		�� CP��1352��
 * @date 		��2017-12-13 ����11:25:10
 * @param xh
 * @return
 */
	public List<HashMap<String, String>> getxljkList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from VIEW_XLZX_TSXSXX a left join(  ");
		sb.append(" with split_data as ( select key, rn, substr(str, instr(str, ',', 1, rn)+1, ");
		sb.append(" instr(str, ',', 1, rn+1) - instr(str, ',', 1, rn) - 1) str  ");
		sb.append(" from (select knlxdm key, ','||knlxdm||',' str from VIEW_XLZX_TSXSXX) a,");
		sb.append(" (select rownum rn from dual connect by rownum < 10) b where instr(str, ',', 1, rn+1) > 0 )");
		sb.append(" select key, substr(max(sys_connect_by_path(knlxmc, ',')), 2) knlxmc from split_data a, tsxs_knlxb b ");
		sb.append(" where a.str = b.knlxdm  start with rn = 1 connect by key = prior key and rn-1 = prior rn  group by key) b on a.knlxdm=b.key ");
		sb.append(" where xh=? ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}
	/**
	 * 
	 * @����: ����ʡ����ҽҩѧҵ�ɼ�1-40
	 * @���ߣ�����[���ţ�1529]
	 * @���ڣ�2018-1-11 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjoneList(String xh)throws Exception {
		StringBuffer sql = new StringBuffer();
		String[] inputValue = new String[] { xh };
		sql.append("select * from (select * from ( select rownum a,kcmc,xq,cj,substr(xn, 0, 4) y,xf from cjb where xh= ?" +
				"  order by  xn,xq,kcmc asc )where rownum<=39) where a>=1");
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	/**
	 * 
	 * @����: ����ʡ����ҽҩѧҵ�ɼ�41-80
	 * @���ߣ�����[���ţ�1529]
	 * @���ڣ�2017-1-11 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjtwoList(String xh)throws Exception {
		StringBuffer sql = new StringBuffer();
		String[] inputValue = new String[] { xh };
		sql.append("select * from (select * from ( select rownum a,kcmc,xq,cj,xf from cjb where xh= ?" +
				" order by  xn,xq,kcmc asc )where rownum<=78) where a>=40");
		return dao.getListNotOut(sql.toString(), inputValue);
	}
/**
 * @description	�� ������ͨ��ѧ-���⾭��
 * @author 		�� CP��1352��
 * @date 		��2018-3-8 ����09:44:31
 * @param xh
 * @param list
 * @return
 * @throws Exception 
 */
	public boolean updateHwjl(String xh, List<HwjlModel> list) throws Exception {
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from xg_xsxx_hwjl ");
		sb.append(" where xh=?");
		String[] input = { xh };
		dao.runDelete(sb.toString(), input);// ɾ��ԭ��¼

		sb = new StringBuffer();
		sb.append("insert into xg_xsxx_hwjl");
		sb.append("(jlid,xh,qssj,jzsj,mdd,sy)");
		sb.append(" values(?,?,?,?,?,?)");

		String guid = null;

		if (list != null && list.size() > 0) {

			for (HwjlModel hwjlModel : list) {
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, hwjlModel.getQssj(),hwjlModel.getJzsj(),
						hwjlModel.getMdd() ,  hwjlModel.getSy()};
				paramList.add(param);
			}
			dao.runBatch(sb.toString(), paramList);
		}

		return true;
	}

public List<HashMap<String, String>> getHwjlList(String xh) {
	StringBuffer sb = new StringBuffer();
	sb.append("select * from xg_xsxx_hwjl ");
	sb.append(" where xh=? ");
	sb.append(" order by qssj desc");
	String[] inputValue = { xh };
	return dao.getListNotOut(sb.toString(), inputValue);
}
	//��������--ȡ����־Ը���ֶ������ж�
	public String getSfqnzyz(String xh){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select sfqnzyz from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "sfqnzyz");
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-4-17 ����02:03:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCyktxxList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_XSXX_CYKTXXB ");
		sb.append(" where xh=? ");
		sb.append(" order by cyktsj desc ");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-4-17 ����02:14:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param cyktxxList
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateCyktxxByjg(String xh, List<CyktxxModel> list, String[] id) throws Exception {
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("delete from XG_XSXX_CYKTXXB ");
		sql.append(" where xh=?");
		String[] input = { xh };
		

		StringBuffer sql1 = new StringBuffer();
		sql1.append("insert into XG_XSXX_CYKTXXB");
		sql1.append("(id,xh,cyktsj,ktmc,ktlb,ktjb,sfwktfzr,scdrw,fjid)");
		sql1.append(" values(?,?,?,?,?,?,?,?,?)");
		String guid = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CyktxxModel cyktxxModel = list.get(i);
				guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				String[] param = { guid, xh, cyktxxModel.getCyktsj(),
						cyktxxModel.getKtmc(), cyktxxModel.getKtlb(),
						cyktxxModel.getKtjb(), cyktxxModel.getSfwktfzr(),cyktxxModel.getScdrw(),cyktxxModel.getFjid()};
				paramList.add(param);			
			}
			dao.runDelete(sql.toString(), input);// ɾ��ԭ��¼
			dao.runBatch(sql1.toString(), paramList);
		}
		
		return true;
		
	}

	/**
	 * @����:ͬ��ͬѧ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 10:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh, bjdm]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getTbtxList(String xh,String bjdm) {
		String sql = "select xh,xm,xb,mz,sjhm from view_xsjbxx where bjdm=? and xh<>?";
		return dao.getListNotOut(sql,new String[]{bjdm,xh});
	}

	/**
	 * @����:ͬ����ͬѧ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 10:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getTsstxList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xh,b.xm,b.symc1 symc,b.xymc,b.zymc,b.sjhm");
		sql.append(" from XG_GYGL_NEW_CWXXB a ");
		sql.append(" left join view_xsjbxx b on b.xh=a.xh");
		sql.append(" where a.xh is not null and a.xh<>? ");
		sql.append(" and lddm||qsh in (select lddm||qsh from XG_GYGL_NEW_CWXXB c where xh=?)");
		return dao.getListNotOut(sql.toString(),new String[]{xh,xh});
	}

	/**
	 * @����:�������Ŀ�ɼ�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 13:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getBjgkmList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xn,b.xqmc,a.kcmc,a.kcxz,a.xf,a.jd,a.cj");
		sql.append(" from cjb a ");
		sql.append(" left join XQDZB b on b.xqdm=a.xq");
		sql.append(" where a.xh=? and a.sfjg <> '��' ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}

	/**
	 * @����:������Ŀ�ɼ�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 13:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getQtkmList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.xqmc");
		sql.append(" from cjb a ");
		sql.append(" left join XQDZB b on b.xqdm=a.xq");
		sql.append(" where a.xh=? and a.sfjg='��' ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	/**
	 * @����:֪��̸����Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getZxthxxList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from xg_view_szdw_thjl t ");
		sql.append(" where t.xh=? ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	/**
	 * @����:�ҷ���Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getJfxxList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select a.*,e.xm jflsxm,");
		sql.append(" case when a.jfxz='01' then 'ʵ��' when a.jfxz='02' then '�绰' when a.jfxz='03' then '΢��' else a.jfxz end jfxzmc");
		sql.append(" from XG_SZDW_JFJLB a");
		sql.append(" LEFT JOIN FDYXXB e ON e.ZGH = a.lrr ");
		sql.append(" ) t where xh=? ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}

	/**
	 * @����:�ڶ�����-���Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getHdxxList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.jgid,t1.xh,t1.xn,t1.xq,t1.hdmc,t1.hdsj,t1.hdxs,t1.hdlx,t1.hddd,t1.cjlx,t1.zdzw,t1.hdzw,t1.hdjx,t1.hdxf,t1.sqid,t1.sjly,t1.sqsj,t1.bz,");
		sql.append(" t2.xm,t2.xb,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,");
		sql.append(" t9.hdlxmc,");
		sql.append(" t8.xqmc,");
		sql.append(" t6.hdbq,t6.hdbqmc,");
		sql.append(" t10.jzlxmc,t11.nlbq,t11.nlbqmc");
		sql.append(" from xg_hdgl_hdbljgb t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xqdzb t8");
		sql.append(" on t1.xq = t8.xqdm");
		sql.append(" left join xg_hdgl_hdlxdmb t9");
		sql.append(" on t1.hdlx = t9.hdlxdm");
		sql.append(" left join (select a.jgid, replace(wm_concat(a.hdbq), ';', ',') hdbq,");
		sql.append("                   replace(wm_concat(b.hdbqmc), ';', ',') hdbqmc");
		sql.append("             from xg_hdgl_hdbqglb a");
		sql.append("             left join xg_hdgl_hdbqdmb b on a.hdbq = b.hdbqdm");
		sql.append(" group by a.jgid) t6 on t1.jgid = t6.jgid");
		sql.append(" left join xg_hdgl_jzlxdmb t10 on t1.jzlx = t10.jzlxdm");
		sql.append(" left join (select c.jgid,replace(wm_concat(c.nlbq), ';', ',') nlbq,");
		sql.append("                   replace(wm_concat(d.nlbqmc), ';', ',') nlbqmc");
		sql.append("             from xg_hdgl_nlbqglb c ");
		sql.append("             left join xg_hdgl_nlbqdmb d on c.nlbq = d.nlbqdm");
		sql.append(" group by c.jgid) t11 on t1.jgid = t11.jgid");
		sql.append(" ) t where xh = ? ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	/**
	 * @����:�ĸ�һ����Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getSgybxxList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append(" select vsb.nj,vs.xh,vsb.xm,t4.sydm,t4.symc,vsb.xydm,vsb.xymc,vsb.zydm,vsb.zymc,vsb.bjmc,vsb.bjdm,vsb.zybj,vsb.zybjmc,");
		sql.append(" (case when a.ydbs is null then 0 else a.ydbs end) ydbs,");
		sql.append(" (case when b.pjjss is null then 0 else b.pjjss end) pjjss,");
		sql.append(" (case when c.jzgs is null then 0 else c.jzgs end) jzgs,");
		sql.append(" (case when d.hdgs is null then 0 else d.hdgs end) hdgs,");
		sql.append(" (case when a.ydbs >= 100 and b.pjjss >= 100 and c.jzgs >=100 and d.hdgs >=100 then '��' else '��' end) sfdb");
		sql.append(" from view_sgybxs vs left join view_xsjbxx vsb on vs.xh = vsb.xh left join xg_xtwh_sybjglb t3 on vsb.bjdm = t3.bjdm ");
		sql.append(" left join xg_xtwh_sydmb t4 on t3.sydm = t4.sydm");
		sql.append(" left join (select xh, count(1) ydbs from xg_dekt_dsglb group by xh) a on vsb.xh = a.xh ");
		sql.append(" left join (select xh, count(1) pjjss from xg_dekt_jspjglb group by xh) b on vsb.xh = b.xh ");
		sql.append(" left join (");
		sql.append(" select count(x.xh) as jzgs,x.xh from (");
		sql.append(" select t.* from (select a.xh,a.hdmc,a.jxmc,a.xf from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%����%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
		sql.append(" union all select a.xh,a.hdmc,a.jxmc,a.hdxf xf");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,f.hdmc");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join view_xsbfxx d on b.xh = d.xh");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%����%') a");
		sql.append(" union all select a.xh,a.hdmc,a.hdjx jxmc,a.hdxf xf from xg_hdgl_hdbljgb a ");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%����%'");
		sql.append(" ) t where 1 = 1 ");
		sql.append(" ) x group by x.xh) c");
		sql.append(" on vsb.xh = c.xh");
		sql.append(" left join (");
		sql.append(" select count(x.xh) as hdgs,x.xh from (");
		sql.append(" select t.* from (select a.xh,a.hdmc,a.jxmc,a.xf from");
		sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,g.jxmc,");
		sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
		sql.append(" from xg_hdgl_jsjdshxxb a");
		sql.append(" left join xg_hdgl_hdjdb b");
		sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
		sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
		sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
		sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%�%'");
		sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
		sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
		sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
		sql.append(" union all select a.xh,a.hdmc,a.jxmc,a.hdxf xf");
		sql.append(" from (select b.xh,a.hdxf,c.jxmc,f.hdmc");
		sql.append(" from xg_hdgl_zdhdxxb a");
		sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
		sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
		sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
		sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%�%') a");
		sql.append(" union all select a.xh,a.hdmc,a.hdjx jxmc,a.hdxf xf from xg_hdgl_hdbljgb a ");
		sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
		sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%�%'");
		sql.append(" ) t where 1 = 1 ");
		sql.append(" ) x group by x.xh ) d");
		sql.append(" on vsb.xh = d.xh ) v");
		sql.append(" where xh = ? ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	/**
	 * @����:�μ�������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getCjstxxList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append("select a.jgid,a.stlx,a.stqc,a.stjc,a.styx,a.ywzddw,a.stzdls,a.stjs,a.gzh,");
		sql.append("decode(a.stzt,0,'Ԥ����',1,'��ʽ',2,'��ע��') stzt,decode(a.sjly,0,'�������',1,'��������') sjly,");
		sql.append("b.xm stzdlsxm ,c.bmmc,(select count(1) from XG_TTGL_STCYB where jgid=a.jgid and shzt='1') strs ");
		sql.append("from xg_ttgl_stgljgb a left join fdyxxb b on a.stzdls=b.zgh ");
		sql.append(" left join zxbz_xxbmdm c on a.ywzddw = c.bmdm  ) ");
		sql.append(" where xh = ? ");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	/**
	 * @����:��ҵ��Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 14:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getByxxList(String xh) {
		return null;
	}

	/**
	 * @����:���ý���
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/21 15:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getBzjlList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_PJPY_NEW_PJJGB  where xh=? and xzdm='2' order by xn,sqsj");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}

	public boolean jtcyDelLsjl(String xh, List<JtcyxxModel> jtcyxxList, User user) throws Exception {
		List<String[]> paramList = new ArrayList<String[]>();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into xg_xszz_new_jtcyb_lsb");
		sql.append("(xh,cygx,cyxm,cynl,cyzzmm,cymz,cyzy,cyxxdw,cylxdh,cynsr,cyjkzk,dcsj,");
		sql.append("ylzd1,ylzd2,ylzd3,ylzd4,ylzd5,jtcyzjlx,jtcyzjhm,csny,czr,czrxm,czsj,czlx)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'ɾ��')");
		for(JtcyxxModel jtcyxxModel:jtcyxxList){
			String[] param = {xh,jtcyxxModel.getCygx(),
					jtcyxxModel.getCyxm(),jtcyxxModel.getCynl(),
					jtcyxxModel.getCyzzmm(),jtcyxxModel.getCymz(),
					jtcyxxModel.getCyzy(),jtcyxxModel.getCyxxdw(),
					jtcyxxModel.getCylxdh(),jtcyxxModel.getCynsr(),
					jtcyxxModel.getCyjkzk(), jtcyxxModel.getDcsj(),
					jtcyxxModel.getYlzd1(),jtcyxxModel.getYlzd2(),
					jtcyxxModel.getYlzd3(),jtcyxxModel.getYlzd4(),
					jtcyxxModel.getYlzd5(),jtcyxxModel.getJtcyzjlx(),
					jtcyxxModel.getJtcyzjhm(),jtcyxxModel.getCsny(),
					user.getUserName(),user.getRealName(), GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss")
			};
			paramList.add(param);
		}
		int[] i = dao.runBatch(sql.toString(), paramList);
		return dao.checkBatch(i);
	}

	public boolean insLsjl(List<HashMap<String, String>> list) throws SQLException {
		List<String[]> paramList = new ArrayList<String[]>();
		StringBuffer sql = new StringBuffer();
			sql.append("insert into XG_XSXX_NEW_ZDXGLSB");
		sql.append("(xh,zd,zdmc,zdz,xgqz,czr,czrxm,czsj)");
		sql.append(" values(?,?,?,?,?,?,?,?)");
		for(HashMap<String, String> map:list){
			String[] param = {map.get("xh"),map.get("zd"),
					map.get("zdmc"),map.get("zdz"),
					map.get("xgqz"),map.get("czr"),
					map.get("czrxm"),map.get("czsj"),
			};
			paramList.add(param);
		}
		int[] i = dao.runBatch(sql.toString(), paramList);
		return dao.checkBatch(i);
	}

	public String getSzdmc(String dz){
		StringBuffer sql = new StringBuffer();
		sql.append("select c.qxmc from dmk_qx c where c.qxdm = substr(?, 0, 2) || '0000'");
		String qxmc = dao.getOneRs(sql.toString(),new String[]{dz},"qxmc");
		sql = new StringBuffer();
		sql.append("select d.qxmc from dmk_qx d where d.qxdm = substr(?, 0, 4) || '00' and ? <> substr(?, 0, 2) || '0000'");
		String qxmc1 = dao.getOneRs(sql.toString(),new String[]{dz,dz,dz},"qxmc");
		sql = new StringBuffer();
		sql.append("select e.qxmc from dmk_qx e where e.qxdm = ? and ");
		sql.append("? <> substr(?, 0, 2) || '0000' and ? <> substr(?, 0, 4) || '00'");
		String qxmc2 = dao.getOneRs(sql.toString(),new String[]{dz,dz,dz,dz,dz},"qxmc");
		return qxmc+qxmc1+qxmc2;
	}

	public List<HashMap<String,String>> getZdLsjList(XsxxglModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from XG_XSXX_NEW_ZDXGLSB where xh=? and zd=?");
		return getPageList(model, sb.toString(), new String[]{model.getXh(),model.getZd()});
	}

	public List<HashMap<String,String>> getJtcyLsjList(XsxxglModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select a.*,b.mc jtcygxmc , c.zzmmmc,d.mc jkzkmc,substr(a.ylzd3,7,8) sfzcsrq  from XG_XSZZ_NEW_JTCYB_LSB a ");
		sb.append(" left join xszz_jtcygxb b on a.cygx=b.dm");
		sb.append(" left join zzmmdmb c on a.CYZZMM = c.ZZMMDM ");
		sb.append(" left join xg_xsxx_jkztb d  on a.cyjkzk=d.dm");
		sb.append("  where xh=? ");
		return getPageList(model, sb.toString(), new String[]{model.getXh()});
	}
}

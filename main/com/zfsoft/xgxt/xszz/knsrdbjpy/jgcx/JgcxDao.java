package com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyForm;

public class JgcxDao extends SuperDAOImpl<JgcxForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_xszz_new_knsrd_bjpyxzpyjg");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JgcxForm t)
			throws Exception {
		return null;
	}

		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JgcxForm t, User user)
			throws Exception {
		String xsxxbZd = " "; // ����ʱ����xsxxb����view_xsxxb
		String xsxxbTable = " view_xsbfxx "; // ����ʱ����xsxxb����view_xsxxb
		String xh = user.getUserName();
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		if(t.getPyyxbl() == null){
			xsxxbZd = " t2.nj,t2.xymc,t2.zymc,t2.bjmc,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.yhkh,t2.sfzh,t2.xydm,t2.zydm,t2.mz,t2.jgmc, ";
			xsxxbTable = " view_xsxxb ";
			t.setPyyxbl(jcszModel.getPyyxbl());
		}
		// =============== ͳ������ < =============
		String pyyxbl = (new BigDecimal(t.getPyyxbl()).divide(new BigDecimal("100"))).toString(); // ������Ч����
		// =============== �Ƿ����� < =============
		List<HashMap<String,String>> sfknList = new OptionUtil().getOptions(OptionUtil.SFKN);
		StringBuilder sfknCaseSql = new StringBuilder();
		StringBuilder sfknSumSql = new StringBuilder();
		StringBuilder sfknMcSql = new StringBuilder(); // �Ƿ����� ����ͳ��
		StringBuilder sfknMc0Sql = new StringBuilder(); // �Ƿ����� ����ͳ�ơ�0��
		String bknIndex = ""; // �������ѡ��ڼ����е�λ��
		String bknMc = ""; // �������ѡ�����
		int sfknSize = sfknList.size();
		for (int i = 0; i < sfknSize; i++) {
			HashMap<String, String> sfknMap = sfknList.get(i);
			String mc = sfknMap.get("mc");
			if(mc.contains("������")){
				bknIndex = String.valueOf(i);
				bknMc = mc;
			}
			sfknCaseSql.append(" case when a.ylzd1='"+mc+"' then 1 else 0 end ylzd1"+i+"temp, ");
			sfknSumSql.append(" sum(ylzd1"+i+"temp) ylzd1"+i+", ");
			sfknMcSql.append(" b.ylzd1"+i+" ||'['||'"+mc+"'||']' ");
			sfknMc0Sql.append(" '0['||'"+mc+"'||']' ");
			if(i < sfknSize - 1){
				sfknMcSql.append(" || '/' || ");
				sfknMc0Sql.append(" || '/' || ");
			}
		}
		sfknMcSql.append(" sfknmc, ");
		// =============== �Ƿ����� > =============
		// =============== ���ѵȼ� < =============
		List<HashMap<String,String>> kndjList = new OptionUtil().getOptions(OptionUtil.KNDJ);
		StringBuilder kndjCaseSql = new StringBuilder();
		StringBuilder kndjSumSql = new StringBuilder();
		StringBuilder pddjMcSql = new StringBuilder(); // �����ȼ�����
		StringBuilder kndjMcSql = new StringBuilder(); // ���ѵȼ� ����ͳ��
		StringBuilder kndjMc0Sql = new StringBuilder(); // ���ѵȼ� ����ͳ�ơ�0��
		int kndjSize = kndjList.size();
		for (int i = 0; i < kndjSize; i++) {
			HashMap<String, String> kndjMap = kndjList.get(i);
			String mc = kndjMap.get("mc");
			kndjCaseSql.append(" case when a.ylzd2='"+mc+"' then 1 else 0 end ylzd2"+i+"temp, ");
			kndjSumSql.append(" sum(ylzd2"+i+"temp) ylzd2"+i+", ");
			pddjMcSql.append(" (case when b.ylzd2"+i+"/a.sumnum>a.pyyxbl then '"+mc+"' else ");
			kndjMcSql.append(" b.ylzd2"+i+" ||'['||'"+mc+"'||']' ");
			kndjMc0Sql.append(" '0['||'"+mc+"'||']' ");
			if(i < kndjSize - 1){
				kndjMcSql.append(" || '/' || ");
				kndjMc0Sql.append(" || '/' || ");
			}else{
				pddjMcSql.append(" ' ' "); 
			}
		}
		kndjMcSql.append(" kndjmc, ");
		for (int i = 0; i < kndjSize; i++) {
			pddjMcSql.append(" end) ");
		}
		// =============== ���ѵȼ� > =============
		// =============== ͳ������ > =============

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select t1.guid,t1.xh,t1.shlc,t1.xn, ");
		sql.append(xsxxbZd);
		sql.append(" t2.xm,t2.bjdm,(case t2.xb when '1' then '��' when '2' then 'Ů' else t2.xb end) xb, ");
		sql.append(" t3.xqmc,t1.sqsj,t4.dcmc,t1.sqly, ");
		sql.append(" t4.dcdm,t1.xq,t1.shzt, ");
		sql.append(" decode(t1.shzt,'6','δ�ύ','7','�༶���鲻ͨ��','�༶����ͨ��') shztbjpymc, ");
		sql.append(" decode(t1.shzt,'6','6','7','7','bjpytg') shztbjpy, ");
		sql.append(" (select dcmc from xg_xszz_new_kndcdmb t where t1.ylzd1=to_char(t.dcdm))sqdcmc, ");
		sql.append(" t1.xn||' '||t3.xqmc knsrdzq, ");
		// =============== ����С����Ϣ < =============
		sql.append(" (case when t5.tjzt='1' then t7.ylzd1 else t8.bjpyxzcyxms end) bjpyxzcyxms, ");
		sql.append(" (case when t5.tjzt='1' then t7.ylzd2 else t8.bjpyxzdbxms end) bjpyxzdbxms, ");
		// =============== ����С����Ϣ > =============
		// =============== ������ < =============
		sql.append(" decode(t7.shzt,'1', 'ͬ������', '0', '��ͬ������','') bjpyjgshzt,t7.pyhsj bjpyjgpyhsj,t7.pyhdd bjpyjgpyhdd,t7.rdly bjpyjgrdly, ");
		// =============== ������ > =============
		// =============== ͳ������ < =============
		sql.append(" decode(t5.sumnum,null,'0/'||nvl(t8.bjpyxzrs,0),t5.sumnum||'/'||(case when t5.tjzt='1' then t5.sumnum else t8.bjpyxzrs end)) yprsmc, ");
		sql.append(" (case when t5.tjzt='1' then 'true' else (case when t5.sumnum=t8.bjpyxzrs then 'true' else 'false' end) end) yprsflag, ");
//		sql.append(" decode(t5.tjzt,'1','���ύ','δ�ύ') tjztmc,t5.tjzt, ");
		sql.append(" (case when t5.tjzt='1' then t5.pjdjmc else (case when t5.sumnum=t8.bjpyxzrs then t5.pjdjmc else '' end) end) pjdjmc, ");
		sql.append(" decode(t5.sfknmc,'',("+sfknMc0Sql.toString()+"),t5.sfknmc) sfknmc, ");
		sql.append(" decode(t5.kndjmc,'',("+kndjMc0Sql.toString()+"),t5.kndjmc) kndjmc, ");
		sql.append(" t5.pjdjdm ");
		// =============== ͳ������ > =============
		sql.append(" from xg_xszz_new_knsrd_knssqb t1 ");
		sql.append(" left join "+xsxxbTable+" t2 on t1.xh=t2.xh ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join xg_xszz_new_kndcdmb t4 on t1.rddc=t4.dcdm ");
		sql.append(" left join ( ");
		// =============== ͳ������ < =============
		sql.append(" select ");
		sql.append(" a.*,b.dcdm pjdjdm,b.dcmc pjdjmc from ( ");
		sql.append(" select ");
		// ================= ���ݡ�������Ч�������������յȼ� < ==============
		sql.append(" case when b.ylzd1"+bknIndex+"/a.sumnum>a.pyyxbl then '"+bknMc+"' else ");
		sql.append(pddjMcSql.toString());
		sql.append(" end pjdjtemp, ");
		// ================= ���ݡ�������Ч�������������յȼ� > ==============
		sql.append(" a.sumnum, ");
		sql.append(sfknMcSql.toString());
		sql.append(kndjMcSql.toString());
		sql.append(" a.tjzt,");
		sql.append(" b.sqr, ");
		sql.append(" b.xn, ");
		sql.append(" b.xq ");
		sql.append(" from ( ");
		sql.append(" select count(1) sumnum,sqr,xn,xq,'"+pyyxbl+"' pyyxbl,tjzt  from xg_xszz_new_knsrd_bjpyxzpy group by sqr,xn,xq,tjzt ");
		sql.append(" ) a ");
		sql.append(" left join ( ");
		sql.append(" select ");
		sql.append(sfknSumSql.toString());
		sql.append(kndjSumSql.toString());
		sql.append(" a.sqr, ");
		sql.append(" a.xn, ");
		sql.append(" a.xq ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(sfknCaseSql.toString());
		sql.append(kndjCaseSql.toString());
		sql.append(" a.sqr, ");
		sql.append(" a.xn, ");
		sql.append(" a.xq ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxzpy a ");
		sql.append(" ) a group by a.sqr,a.xn,a.xq ");
		sql.append(" ) b on a.sqr=b.sqr and a.xn=b.xn and a.xq=b.xq ");
		sql.append(" ) a ");
		sql.append(" left join xg_xszz_new_kndcdmb b on b.dcmc like '%'||a.pjdjtemp||'%' ");
		// =============== ͳ������ > =============
		sql.append(" ) t5 on (t1.xn=t5.xn and t1.xq=t5.xq and t1.xh=t5.sqr) ");
		// =============== ������ < =============
		sql.append(" left join xg_xszz_new_knsrd_bjpyxzpyjg t7 on t1.guid=t7.sqid ");
		// =============== ������ > =============
		sql.append(" left join ( ");
		// =============== ����С����Ϣ < =============
		sql.append(" select * from ( ");
		sql.append(" select a.bjdm,count(1) over (partition by a.bjdm order by a.xh asc) bjpyxzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.bjdm order by a.xh asc) bjpyxzcyxms, ");
		sql.append(" WM_CONCAT(decode(a.sfxsdb,'1',b.xm,'')) over (partition by a.bjdm order by a.xh asc) bjpyxzdbxms, ");
		sql.append(" row_number() over (partition by a.bjdm order by a.xh desc) rn ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxz a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" ) a where rn='1' ");
		// =============== ����С����Ϣ > =============
		sql.append(" ) t8 on (t2.bjdm=t8.bjdm) ");
		sql.append(" where 1=1 ");
		String[] params = inputValue;
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			sql.append(" and exists (select 1 from xsxxb t6 where t6.xh=? and t2.bjdm=t6.bjdm) ");
			params = StringUtils.joinStrArr(new String[]{xh}, inputValue);
		}else{
			sql.append(searchTjByUser);
		}
		sql.append(" and t1.shzt not in ('0','3') ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(),params);
	}
	
	@Override
	public JgcxForm getModel(JgcxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, ");
		sql.append(" t2.xqmc,decode(t.tjzt,'1','���ύ','δ�ύ') tjztmc, ");
		sql.append(" t3.bjpyxzrs, t3.bjpyxzcyxms ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxzpyjg t ");
		sql.append(" left join xqdzb t2 on t.xq=t2.xqdm ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.xn,a.xq,a.sqr,a.tjzt,count(1) over (partition by a.xn,a.xq,a.sqr order by a.sqr asc) bjpyxzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.xn,a.xq,a.sqr order by a.sqr asc) bjpyxzcyxms, ");
		sql.append(" row_number() over (partition by a.xn,a.xq,a.sqr order by a.sqr desc) rn ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxzpy a ");
		sql.append(" left join xsxxb b on a.bjpyr=b.xh ");
		sql.append(" ) a where rn='1' ");
		sql.append(" ) t3 on (t.xn=t3.xn and t.xq=t3.xq and t.sqr=t3.sqr) ");
		sql.append(" where t.sqid=? ");
		return super.getModel(t, sql.toString(), new String[]{t.getSqid()});
	}

	/**
	 * ��ȡ�༶����С�������Ϣ
	 */
	public HashMap<String,String> queryBjpyxzdb(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, ");
		sql.append(" b.bjpyxzrs,b.bjpyxzcyxms,b.bjpyxzdbxms ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxz a ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.bjdm,count(1) over (partition by a.bjdm order by a.xh asc) bjpyxzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.bjdm order by a.xh asc) bjpyxzcyxms, ");
		sql.append(" WM_CONCAT(decode(a.sfxsdb,'1',b.xm,'')) over (partition by a.bjdm order by a.xh asc) bjpyxzdbxms, ");
		sql.append(" row_number() over (partition by a.bjdm order by a.xh desc) rn ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxz a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" ) a where rn='1' ");
		sql.append(" ) b on a.bjdm=b.bjdm ");
		sql.append(" where a.xh=? and a.sfxsdb='1' ");
		return dao.getMapNotOut(sql.toString(), new String[]{ xh });
	}
	/**
	 * �鿴�༶������
	 */
	public List<HashMap<String, String>> jgcxView(JgcxForm t, User user) throws Exception {
		// =============== �Ƿ����� < =============
		List<HashMap<String,String>> sfknList = new OptionUtil().getOptions(OptionUtil.SFKN);
		String bknMc = ""; // �������ѡ�����
		int sfknSize = sfknList.size();
		for (int i = 0; i < sfknSize; i++) {
			HashMap<String, String> sfknMap = sfknList.get(i);
			String mc = sfknMap.get("mc");
			if(mc.contains("������")){
				bknMc = mc;
			}
		}
		// =============== �Ƿ����� > =============
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String xn = t.getXn();
		String xq = t.getXq();
		String xh = t.getSqr();
		String shztbjpy = t.getShztbjpy();
		StringBuilder sql = new StringBuilder();
		if("6".equals(shztbjpy)){ // δ�ύ
			sql.append(" select * from ( ");
			sql.append(" select a.xh,a.sfxsdb,decode(a.sfxsdb,'1','��','��') sfxsdbmc, ");
			sql.append(" case when b.ylzd1='"+bknMc+"' then '"+bknMc+"' else b.ylzd2 end pyjgmc, ");
			sql.append(" c.xm,c.nj,c.xymc,c.zymc,c.bjmc,c.xydm,c.zydm,c.bjdm, ");
			sql.append(" b.bjpyr,b.ylzd1,b.ylzd2,b.ylzd3,b.ylzd4,b.ylzd5,b.ylzd6,b.ylzd7,b.ylzd8,b.ylzd9,b.ylzd10,b.tjzt,b.tjsj ");
			sql.append(" from xg_xszz_new_knsrd_bjpyxz a left join ( ");
			sql.append(" select a.* from xg_xszz_new_knsrd_bjpyxzpy a where a.xn=? and a.xq=? and a.sqr=? ");
			sql.append(" ) b on a.xh=b.bjpyr ");
			sql.append(" left join view_xsbfxx c on a.xh=c.xh ");
			sql.append(" where exists (select 1 from xsxxb z where z.xh=? and a.bjdm=z.bjdm) ");
			sql.append(" ) t1 where 1=1 ");
			sql.append(searchTj);
			//sql.append(searchTjByUser);
			return getPageList(t, sql.toString(),StringUtils.joinStrArr(new String[]{xn,xq,xh,xh}, inputValue));
		}else{
			sql.append(" select * from ( ");
			sql.append(" select b.bjpyr xh,decode(b.bjpyr,d.bjpydb,'1','0') sfxsdb,decode(b.bjpyr,d.bjpydb,'��','��') sfxsdbmc, ");
			sql.append(" case when b.ylzd1='"+bknMc+"' then '"+bknMc+"' else b.ylzd2 end pyjgmc, ");
			sql.append(" c.xm,c.nj,c.xymc,c.zymc,c.bjmc,c.xydm,c.zydm,c.bjdm, ");
			sql.append(" b.bjpyr,b.ylzd1,b.ylzd2,b.ylzd3,b.ylzd4,b.ylzd5,b.ylzd6,b.ylzd7,b.ylzd8,b.ylzd9,b.ylzd10,b.tjzt,b.tjsj ");
			sql.append(" from ( ");
			sql.append(" select a.* from xg_xszz_new_knsrd_bjpyxzpy a where a.xn=? and a.xq=? and a.sqr=? ");
			sql.append(" ) b ");
			sql.append(" left join view_xsbfxx c on b.bjpyr=c.xh ");
			sql.append(" left join xg_xszz_new_knsrd_bjpyxzpyjg d on b.xn=d.xn and b.xq=d.xq and b.sqr=d.sqr ");
			sql.append(" ) t1 where 1=1 ");
			sql.append(searchTj);
			//sql.append(searchTjByUser);
			return getPageList(t, sql.toString(),StringUtils.joinStrArr(new String[]{xn,xq,xh}, inputValue));
		}
	}
	/**
	 * �ύ�༶����
	 */
	public boolean tjBjpyxzpy(String xn, String xq, String sqr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_new_knsrd_bjpyxzpy ");
		sql.append(" set ");
		sql.append(" tjsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), ");
		sql.append(" tjzt = '1' ");
		sql.append(" where xn=? and xq=? and sqr=? ");
		return dao.runUpdate(sql.toString(), new String[]{ xn, xq, sqr });
	}
	/**
	 * �����ύ�༶����
	 */
	public boolean cxBjpyxzpy(String xn, String xq, String sqr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_new_knsrd_bjpyxzpy ");
		sql.append(" set ");
		sql.append(" tjsj = null, ");
		sql.append(" tjzt = '0' ");
		sql.append(" where xn=? and xq=? and sqr=? ");
		return dao.runUpdate(sql.toString(), new String[]{ xn, xq, sqr });
	}
	
	public HashMap<String,String> getBjpyTjdc(KnsrdbjpyForm knsrdbjpyModel){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from(select a.*, b.dcdm pjdjdm, b.dcmc pjdjmc from (select case when b.ylzd10 / a.sumnum >");
		sql.append(" a.pyyxbl then '������' else (case when b.ylzd20 / a.sumnum > a.pyyxbl then 'һ������'");
		sql.append("  else (case  when b.ylzd21 / a.sumnum > a.pyyxbl then '�ر�����' else ' ' end) end)");
		sql.append(" end pjdjtemp, a.sumnum, a.tjzt, b.sqr, b.xn,  b.xq from (select count(1) sumnum, sqr,");
		sql.append(" xn, xq, '0.5' pyyxbl, tjzt from xg_xszz_new_knsrd_bjpyxzpy group by sqr, xn, xq, tjzt) a");
        sql.append("left join (select sum(ylzd10temp) ylzd10, sum(ylzd11temp) ylzd11, sum(ylzd20temp) ylzd20, sum(ylzd21temp) ylzd21,");
        sql.append("a.sqr, a.xn, a.xq from (select case when a.ylzd1 = '������' then 1  else 0 end ylzd10temp,  case");
        sql.append(" when a.ylzd1 = '����' then 1 else 0 end ylzd11temp, case when a.ylzd2 = 'һ������' then");
        sql.append(" 1 else  0 end ylzd20temp,  case when a.ylzd2 = '�ر�����' then 1 else 0 end ylzd21temp, a.sqr,");
        sql.append(" a.xn, a.xq from xg_xszz_new_knsrd_bjpyxzpy a) a group by a.sqr, a.xn, a.xq) b on a.sqr = b.sqr");
        sql.append(" and a.xn = b.xn and a.xq = b.xq) a left join xg_xszz_new_kndcdmb b on b.dcmc like '%' || a.pjdjtemp || '%') ");
        sql.append(" where sqr=? and xn=? and xq=?");
        return dao.getMapNotOut(sql.toString(), new String[]{knsrdbjpyModel.getXh(),knsrdbjpyModel.getXn(),knsrdbjpyModel.getXq()});
	}
	
}

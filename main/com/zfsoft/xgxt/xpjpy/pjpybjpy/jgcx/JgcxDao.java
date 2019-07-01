package com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz.JcszForm;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz.JcszService;

public class JgcxDao extends SuperDAOImpl<JgcxForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_pjpy_new_pjpy_bjpyxzpyjg");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JgcxForm t)
			throws Exception {
		return null;
	}

		/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JgcxForm t, User user)
			throws Exception {
		String xsxxbZd = " "; // 导出时，把xsxxb换成view_xsxxb
		String xsxxbTable = " view_cpmd_xsbfb "; // 导出时，把xsxxb换成view_xsxxb
		String xh = user.getUserName();
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		if(t.getPyyxbl() == null){
			xsxxbZd = " t2.nj,t2.xymc,t2.zymc,t2.bjmc,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.yhkh,t2.sfzh,t2.xydm,t2.zydm,t2.mz,t2.jgmc, ";
			xsxxbTable = " view_cpmd_xsxxb ";
			t.setPyyxbl(jcszModel.getPyyxbl());
		}
		// =============== 统计数据 < =============
		String pyyxbl = (new BigDecimal(t.getPyyxbl()).divide(new BigDecimal("100"))).toString(); // 评议有效比例
		// =============== 是否同意申请 < =============
		List<HashMap<String,String>> pyjgList = new OptionUtil().getOptions(OptionUtil.PYJG);
		StringBuilder pyjgCaseSql = new StringBuilder();
		StringBuilder pyjgSumSql = new StringBuilder();
		StringBuilder pyjgMcSql = new StringBuilder(); // 评议结果
		StringBuilder pyjgtjMcSql = new StringBuilder(); // 是否同意申请 人数统计
		StringBuilder pyjgtjMc0Sql = new StringBuilder(); // 是否同意申请 人数统计【0】
		int pyjgSize = pyjgList.size();
		for (int i = 0; i < pyjgSize; i++) {
			HashMap<String, String> pyjgMap = pyjgList.get(i);
			String dm = pyjgMap.get("dm");
			String mc = pyjgMap.get("mc");
			pyjgCaseSql.append(" case when a.ylzd1='"+dm+"' then 1 else 0 end ylzd1"+i+"temp, ");
			pyjgSumSql.append(" sum(ylzd1"+i+"temp) ylzd1"+i+", ");
			pyjgMcSql.append(" (case when b.ylzd1"+i+"/a.sumnum>a.pyyxbl then '"+mc+"' else ");
			pyjgtjMcSql.append(" b.ylzd1"+i+" ||'['||'"+mc+"'||']' ");
			pyjgtjMc0Sql.append(" '0['||'"+mc+"'||']' ");
			if(i < pyjgSize - 1){
				pyjgtjMcSql.append(" || '/' || ");
				pyjgtjMc0Sql.append(" || '/' || ");
			}else{
				pyjgMcSql.append(" ' ' "); 
			}
		}
		pyjgtjMcSql.append(" pyjgtjmc, ");
		for (int i = 0; i < pyjgSize; i++) {
			pyjgMcSql.append(" end) ");
		}
		// =============== 是否同意申请 > =============
		// =============== 统计数据 > =============

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select t1.sqid,t1.xh,t1.splc,t1.xn,t1.xmdm, ");
		sql.append(xsxxbZd);
		sql.append(" t2.xm,t2.bjdm,(case t2.xb when '1' then '男' when '2' then '女' else t2.xb end) xb, ");
		sql.append(" t3.xqmc,t1.sqsj,t1.sqly, ");
		sql.append(" t1.xq,t1.shzt, ");
		sql.append(" decode(t1.shzt,'6','未提交','7','班级评议不通过','班级评议通过') shztbjpymc, ");
		sql.append(" decode(t1.shzt,'6','6','7','7','bjpytg') shztbjpy, ");
		sql.append(" (select xmmc from xg_pjpy_new_pjxmb t where t1.tzhxmdm=t.xmdm) tzhxmmc, ");
		sql.append(" (select xmmc from xg_pjpy_new_pjxmb t where t1.xmdm=t.xmdm) xmmc, ");
		// =============== 评议小组信息 < =============
		sql.append(" (case when t5.tjzt='1' then t7.ylzd1 else t8.bjpyxzcyxms end) bjpyxzcyxms, ");
		sql.append(" (case when t5.tjzt='1' then t7.ylzd2 else t8.bjpyxzdbxms end) bjpyxzdbxms, ");
		// =============== 评议小组信息 > =============
		// =============== 评议结果 < =============
		sql.append(" decode(t7.shzt,'1', '同意申请', '0', '不同意申请','') bjpyjgshzt,t7.pyhsj bjpyjgpyhsj,t7.pyhdd bjpyjgpyhdd,t7.pyyj bjpyjgpyyj, ");
		// =============== 评议结果 > =============
		// =============== 统计数据 < =============
		sql.append(" decode(t5.sumnum,null,'0/'||nvl(t8.bjpyxzrs,0),t5.sumnum||'/'||(case when t5.tjzt='1' then t5.sumnum else t8.bjpyxzrs end)) yprsmc, ");
		sql.append(" (case when t5.tjzt='1' then 'true' else (case when t5.sumnum=t8.bjpyxzrs then 'true' else 'false' end) end) yprsflag, ");
//		sql.append(" decode(t5.tjzt,'1','已提交','未提交') tjztmc,t5.tjzt, ");
		sql.append(" (case when t5.tjzt='1' then t5.pyjgtempmc else (case when t5.sumnum=t8.bjpyxzrs then t5.pyjgtempmc else '' end) end) pyjgtempmc, ");
		sql.append(" decode(t5.pyjgtjmc,'',("+pyjgtjMc0Sql.toString()+"),t5.pyjgtjmc) pyjgtjmc, ");
		sql.append(" t5.pyjgtempdm ");
		// =============== 统计数据 > =============
		sql.append(" from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join "+xsxxbTable+" t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join ( ");
		// =============== 统计数据 < =============
		sql.append(" select ");
		sql.append(" a.*,decode(a.pyjgcasemc, '同意申请', '1','不同意申请', '0', '') pyjgtempdm,pyjgcasemc pyjgtempmc from ( ");
		sql.append(" select ");
		// ================= 根据【评议有效比例】计算最终结果 < ==============
		sql.append(pyjgMcSql.toString());
		sql.append(" pyjgcasemc, ");
		// ================= 根据【评议有效比例】计算最终结果 > ==============
		sql.append(" a.sumnum, ");
		sql.append(pyjgtjMcSql.toString());
		sql.append(" a.tjzt,");
		sql.append(" b.sqr, ");
		sql.append(" b.xn, ");
		sql.append(" b.xq,b.xmdm ");
		sql.append(" from ( ");
		sql.append(" select count(1) sumnum,sqr,xn,xq,xmdm,'"+pyyxbl+"' pyyxbl,tjzt  from xg_pjpy_new_pjpy_bjpyxzpy group by sqr,xn,xq,xmdm,tjzt ");
		sql.append(" ) a ");
		sql.append(" left join ( ");
		sql.append(" select ");
		sql.append(pyjgSumSql.toString());
		sql.append(" a.sqr, ");
		sql.append(" a.xn, ");
		sql.append(" a.xq,a.xmdm ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(pyjgCaseSql.toString());
		sql.append(" a.sqr, ");
		sql.append(" a.xn, ");
		sql.append(" a.xq,a.xmdm ");
		sql.append(" from xg_pjpy_new_pjpy_bjpyxzpy a ");
		sql.append(" ) a group by a.sqr,a.xn,a.xq,a.xmdm ");
		sql.append(" ) b on a.sqr=b.sqr and a.xn=b.xn and a.xq=b.xq and a.xmdm=b.xmdm ");
		sql.append(" ) a ");
		// =============== 统计数据 > =============
		sql.append(" ) t5 on (t1.xn=t5.xn and t1.xq=t5.xq and t1.xmdm=t5.xmdm and t1.xh=t5.sqr) ");
		// =============== 评议结果 < =============
		sql.append(" left join xg_pjpy_new_pjpy_bjpyxzpyjg t7 on t1.sqid=t7.sqid ");
		// =============== 评议结果 > =============
		sql.append(" left join ( ");
		// =============== 评议小组信息 < =============
		sql.append(" select * from ( ");
		sql.append(" select a.bjdm,count(1) over (partition by a.bjdm order by a.xh asc) bjpyxzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.bjdm order by a.xh asc) bjpyxzcyxms, ");
		sql.append(" WM_CONCAT(decode(a.sfxsdb,'1',b.xm,'')) over (partition by a.bjdm order by a.xh asc) bjpyxzdbxms, ");
		sql.append(" row_number() over (partition by a.bjdm order by a.xh desc) rn ");
		sql.append(" from xg_pjpy_new_pjpy_bjpyxz a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" ) a where rn='1' ");
		// =============== 评议小组信息 > =============
		sql.append(" ) t8 on (t2.bjdm=t8.bjdm) ");
		sql.append(" where 1=1 ");
		String[] params = inputValue;
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb t6 where t6.xh=? and t2.bjdm=t6.bjdm and t2.xn=t6.xn and t2.xq=t6.xq) ");
			params = StringUtils.joinStrArr(new String[]{xh}, inputValue);
		}else{
			sql.append(searchTjByUser);
		}
		sql.append(" and t1.shzt not in ('0','3') ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(),params);
	}
	/**
	 * 查看班级评议结果
	 */
	public List<HashMap<String, String>> jgcxView(JgcxForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String xn = t.getXn();
		String xq = t.getXq();
		String xh = t.getSqr();
		String xmdm = t.getXmdm();
		String shztbjpy = t.getShztbjpy();
		StringBuilder sql = new StringBuilder();
		if("6".equals(shztbjpy)){ // 未提交
			sql.append(" select * from ( ");
			sql.append(" select a.xh,a.sfxsdb,decode(a.sfxsdb,'1','是','否') sfxsdbmc, ");
			sql.append(" decode(b.ylzd1,'1','同意申请','0','不同意申请','') pyjgmc, ");
			sql.append(" c.xm,c.nj,c.xymc,c.zymc,c.bjmc,c.xydm,c.zydm,c.bjdm, ");
			sql.append(" b.bjpyr,b.ylzd1,b.ylzd2,b.ylzd3,b.ylzd4,b.ylzd5,b.ylzd6,b.ylzd7,b.ylzd8,b.ylzd9,b.ylzd10,b.tjzt,b.tjsj ");
			sql.append(" from xg_pjpy_new_pjpy_bjpyxz a left join ( ");
			sql.append(" select a.* from xg_pjpy_new_pjpy_bjpyxzpy a where a.xn=? and a.xq=? and a.sqr=? and a.xmdm=? ");
			sql.append(" ) b on a.xh=b.bjpyr ");
			sql.append(" left join (select * from view_cpmd_xsxxb where xn||xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb)) c on a.xh=c.xh ");
			sql.append(" where exists (select 1 from xg_pjpy_new_cpmdb z where z.xh=? and a.bjdm=z.bjdm and z.xn||z.xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb)) ");
			sql.append(" ) t1 where 1=1 ");
			sql.append(searchTj);
			//sql.append(searchTjByUser);
			return getPageList(t, sql.toString(),StringUtils.joinStrArr(new String[]{xn,xq,xh,xmdm,xh}, inputValue));
		}else{
			sql.append(" select * from ( ");
			sql.append(" select b.bjpyr xh,decode(b.bjpyr,d.bjpydb,'1','0') sfxsdb,decode(b.bjpyr,d.bjpydb,'是','否') sfxsdbmc, ");
			sql.append(" decode(b.ylzd1,'1','同意申请','0','不同意申请','') pyjgmc, ");
			sql.append(" c.xm,c.nj,c.xymc,c.zymc,c.bjmc,c.xydm,c.zydm,c.bjdm, ");
			sql.append(" b.bjpyr,b.ylzd1,b.ylzd2,b.ylzd3,b.ylzd4,b.ylzd5,b.ylzd6,b.ylzd7,b.ylzd8,b.ylzd9,b.ylzd10,b.tjzt,b.tjsj ");
			sql.append(" from ( ");
			sql.append(" select a.* from xg_pjpy_new_pjpy_bjpyxzpy a where a.xn=? and a.xq=? and a.sqr=? and xmdm=? ");
			sql.append(" ) b ");
			sql.append(" left join view_cpmd_xsxxb c on b.bjpyr=c.xh and b.xn=c.xn and b.xq=c.xq ");
			sql.append(" left join xg_pjpy_new_pjpy_bjpyxzpyjg d on b.xn=d.xn and b.xq=d.xq and b.sqr=d.sqr ");
			sql.append(" ) t1 where 1=1 ");
			sql.append(searchTj);
			//sql.append(searchTjByUser);
			return getPageList(t, sql.toString(),StringUtils.joinStrArr(new String[]{xn,xq,xh,xmdm}, inputValue));
		}
	}
	
	@Override
	public JgcxForm getModel(JgcxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, ");
		sql.append(" t2.xqmc,decode(t.tjzt,'1','已提交','未提交') tjztmc, ");
		sql.append(" t3.bjpyxzrs, t3.bjpyxzcyxms ");
		sql.append(" from xg_pjpy_new_pjpy_bjpyxzpyjg t ");
		sql.append(" left join xqdzb t2 on t.xq=t2.xqdm ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.xn,a.xq,a.xmdm,a.sqr,a.tjzt,count(1) over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr asc) bjpyxzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr asc) bjpyxzcyxms, ");
		sql.append(" row_number() over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr desc) rn ");
		sql.append(" from xg_pjpy_new_pjpy_bjpyxzpy a ");
		sql.append(" left join xsxxb b on a.bjpyr=b.xh ");
		sql.append(" ) a where rn='1' ");
		sql.append(" ) t3 on (t.xn=t3.xn and t.xq=t3.xq and t.xmdm=t3.xmdm and t.sqr=t3.sqr) ");
		sql.append(" where t.sqid=? ");
		return super.getModel(t, sql.toString(), new String[]{t.getSqid()});
	}

	/**
	 * 获取评奖班级评议小组代表信息
	 */
	public HashMap<String,String> queryBjpyxzdb(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, ");
		sql.append(" b.bjpyxzrs,b.bjpyxzcyxms,b.bjpyxzdbxms ");
		sql.append(" from xg_pjpy_new_pjpy_bjpyxz a ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.bjdm,count(1) over (partition by a.bjdm order by a.xh asc) bjpyxzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.bjdm order by a.xh asc) bjpyxzcyxms, ");
		sql.append(" WM_CONCAT(decode(a.sfxsdb,'1',b.xm,'')) over (partition by a.bjdm order by a.xh asc) bjpyxzdbxms, ");
		sql.append(" row_number() over (partition by a.bjdm order by a.xh desc) rn ");
		sql.append(" from xg_pjpy_new_pjpy_bjpyxz a ");
		sql.append(" left join xsxxb b on a.xh=b.xh ");
		sql.append(" ) a where rn='1' ");
		sql.append(" ) b on a.bjdm=b.bjdm ");
		sql.append(" where a.xh=? and a.sfxsdb='1' ");
		return dao.getMapNotOut(sql.toString(), new String[]{ xh });
	}
	/**
	 * 提交评奖班级评议
	 */
	public boolean tjBjpyxzpy(String xn, String xq, String xmdm, String sqr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_new_pjpy_bjpyxzpy ");
		sql.append(" set ");
		sql.append(" tjsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), ");
		sql.append(" tjzt = '1' ");
		sql.append(" where xn=? and xq=? and xmdm=? and sqr=? ");
		return dao.runUpdate(sql.toString(), new String[]{ xn, xq, xmdm, sqr });
	}
	/**
	 * 撤销提交评奖班级评议
	 */
	public boolean cxBjpyxzpy(String xn, String xq, String xmdm, String sqr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_new_pjpy_bjpyxzpy ");
		sql.append(" set ");
		sql.append(" tjsj = null, ");
		sql.append(" tjzt = '0' ");
		sql.append(" where xn=? and xq=? and xmdm=? and sqr=? ");
		return dao.runUpdate(sql.toString(), new String[]{ xn, xq, xmdm, sqr });
	}
	
}

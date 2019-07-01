/**
 * @部门:学工产品事业部
 * @日期：2016-6-23 上午09:56:30 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfhz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合素质分管理
 * @类功能描述: 综合素质提升学分汇总表 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-23 上午09:56:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfhzDao extends SuperDAOImpl<ZhfhzForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfhzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfhzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 
	 * @描述: 学生基本信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-23 上午10:32:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.xh,a.xz,a.sjhm ");
		sql.append(" from view_zjly_zhf a ");
		sql.append(" where a.xh=? ");
		return DAO.getInstance().getMap(sql.toString(), new String[]{xh}, new String[]{"xm","xb","xymc","zymc","bjmc","xh","xz","sjhm"});
	}
	
	//E模块不需要判断合格否，只能写死判断了。。。
	public List<HashMap<String, String>> getMkxmList(String xh) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (  ");
		sql.append(" select a.*,(case when xmmkdm = 'E' then '' when to_number(nvl(hgf1, 0)) > mkzf then '不合格' when btf <> 0 then '不合格' else '合格' end) hgf ");
		sql.append("   from (select a.xmmkdm,      ");
		sql.append("     a.xmmkmc,      ");
		sql.append("     to_number(a.xf) xf,       ");
		sql.append("     jfxmdm,jfxmmc,btf,hgf hgf1, ");
		sql.append("     fs jfxmzf,     ");
		sql.append("     (case          ");
		sql.append("       when xmmkdm = 'A' and   ");
		sql.append(" to_number(zf) + to_number(zf1) > to_number(xf) then       ");
		sql.append("        to_number(xf)          ");
		sql.append("       when xmmkdm = 'A' and   ");
		sql.append(" to_number(zf) + to_number(zf1) < to_number(xf) then       ");
		sql.append("        to_number(zf) + to_number(zf1)      ");
		sql.append("       when to_number(zf) > to_number(xf) then  ");
		sql.append("        to_number(xf)          ");
		sql.append("       else         ");
		sql.append("        to_number(zf)          ");
		sql.append("     end) mkzf,     ");
		sql.append("     sxsm,          ");
		sql.append("     cysj,lb          ");
		sql.append("from (select a.xmmkdm,         ");
		sql.append("  a.jfxmdm,         ");
		sql.append("  a.xmmkmc,         ");
		sql.append("  a.jfxmmc,         ");
		sql.append("  (case  ");
		sql.append("when to_number(a.fs) > to_number(b.xdfs) then          ");
		sql.append(" to_number(b.xdfs)");
		sql.append("else ");
		sql.append(" to_number(a.fs)");
		sql.append("  end) fs,          ");
		sql.append("  (select nvl(sum(fs), 0)        ");
		sql.append(" from (select xmmkdm,        ");
		sql.append("   jfxmmc,        ");
		sql.append("   (case          ");
		sql.append("     when sum(e.fs) > to_number(xdfs) then  ");
		sql.append("      to_number(xdfs) ");
		sql.append("     else         ");
		sql.append("      sum(e.fs)   ");
		sql.append("   end) fs        ");
		sql.append("         from view_zjly_zhf e");
		sql.append("        where fs is not null ");
		sql.append("          and e.xh = ?       ");
		sql.append("          and not exists     ");
		sql.append("        (select 1 ");
		sql.append("      from xg_zjly_zhszf_jdszb g ");
		sql.append("     where e.jfxmdm = g.jfxmdm)  ");
		sql.append("        group by xmmkdm, jfxmmc, xdfs) j    ");
		sql.append("where a.xmmkdm = j.xmmkdm) zf,   ");
		sql.append("  (select nvl(to_number(max(fs)), 0) jfxmzf     ");
		sql.append(" from view_zjly_zhf h        ");
		sql.append("where exists (select 1       ");
		sql.append("         from xg_zjly_zhszf_jdszb i         ");
		sql.append("        where h.jfxmdm = i.jfxmdm)          ");
		sql.append("  and xh = ?    ");
		sql.append("  and fs is not null) zf1,   ");
		sql.append("  c.xf,  ");
		sql.append("  c.hgf, ");
		sql.append("  (select n from (select count(1) n, xmmkdm     ");
		sql.append(" from xg_zjly_zhszf_jfxmb e  ");
		sql.append("where sfbt = '1'");
		sql.append("  and not exists");
		sql.append("(select 1       ");
		sql.append("          from view_zjly_zhf f   ");
		sql.append("        where e.jfxmdm = f.jfxmdm");
		sql.append("          and f.xh = ?) group by xmmkdm) j where a.xmmkdm = j.xmmkdm) btf,       ");
		sql.append("  t1.sxsm,          ");
		sql.append("  t1.cysj,t1.lb");
		sql.append("        from (select a.xmmkdm, ");
		sql.append("      a.jfxmdm, ");
		sql.append("      a.xmmkmc, ");
		sql.append("      a.jfxmmc, ");
		sql.append("      sum(a.fs) fs");
		sql.append(" from view_zjly_zhf a  ");
		sql.append("where a.xh = ?  ");
		sql.append("  and a.xmmkdm is not null  and a.shzt!='2'  ");
		sql.append(" group by a.xmmkdm, a.xmmkmc, a.jfxmmc, a.jfxmdm) a ");
		sql.append("        left join xg_zjly_zhszf_jfxmb b     ");
		sql.append("          on a.jfxmdm = b.jfxmdm ");
		sql.append("        left join xg_zjly_zhszf_mkxmb c     ");
		sql.append("          on a.xmmkdm = c.xmmkdm ");
		sql.append("        left join (   ");
		sql.append("  select xh,jfxmdm,sxsm,cysj, replace(max(lb), ';', '<br />') lb from( ");
		sql.append(" select xh,jfxmdm,sxsm,cysj, ");
		sql.append(" WM_CONCAT(lb) over(partition by jfxmdm order by jfxmdm) lb from (");
		sql.append("          select xh,jfxmdm, ");
		sql.append("          replace(max(sxsm), ';', '<br />') sxsm,      ");
		sql.append("          replace(max(cysj), ';', '<br />') cysj,lb       ");
		sql.append("     from (select xh,        ");
		sql.append("       jfxmdm,    ");
		sql.append("       WM_CONCAT(sxsm) over(partition by jfxmdm order by jfxmdm) sxsm,");
		sql.append("       WM_CONCAT(cysj) over(partition by jfxmdm order by jfxmdm) cysj,lb ");
		sql.append("  from (select a.xh,  ");
		sql.append("a.jfxmdm,         ");
		sql.append("a.sxsm,");
		sql.append("a.cysj, ");
		sql.append("  case when to_number(length(a.lrr))>10 then '学生申请' else (select bmmc||'导入' from view_fdyxx where zgh =a.lrr) end lb ");
		sql.append("          from xg_zjly_zhszf_drjlb a        ");
		sql.append("         where xh = ? and a.shzt!='2'))          ");
		sql.append("    group by jfxmdm, xh,lb ))  group by jfxmdm,xh,sxsm,cysj ) t1  ");
		sql.append("          on a.jfxmdm = t1.jfxmdm) a) a     ");
		sql.append("  order by xmmkdm, to_number(nvl(jfxmdm,0))) a   ");

		return dao.getListNotOut(sql.toString(), new String[]{xh,xh,xh,xh,xh});
	}
/**
 *  (20170309废弃)
 */
	public List<HashMap<String, String>> getMkxmListforword(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (  ");
		sql.append(" select a.*,(case when xmmkdm = 'E' then '' when to_number(nvl(hgf1, 0)) > mkzf then '不合格' when btf <> 0 then '不合格' else '合格' end) hgf ");
		sql.append("   from (select a.xmmkdm,      ");
		sql.append("     a.xmmkmc,      ");
		sql.append("     to_number(a.xf) xf,       ");
		sql.append("     jfxmdm,jfxmmc,btf,hgf hgf1, ");
		sql.append("     fs jfxmzf,     ");
		sql.append("     (case          ");
		sql.append("       when xmmkdm = 'A' and   ");
		sql.append(" to_number(zf) + to_number(zf1) > to_number(xf) then       ");
		sql.append("        to_number(xf)          ");
		sql.append("       when xmmkdm = 'A' and   ");
		sql.append(" to_number(zf) + to_number(zf1) < to_number(xf) then       ");
		sql.append("        to_number(zf) + to_number(zf1)      ");
		sql.append("       when to_number(zf) > to_number(xf) then  ");
		sql.append("        to_number(xf)          ");
		sql.append("       else         ");
		sql.append("        to_number(zf)          ");
		sql.append("     end) mkzf,     ");
		sql.append("     sxsm,          ");
		sql.append("     cysj,lb          ");
		sql.append("from (select a.xmmkdm,         ");
		sql.append("  a.jfxmdm,         ");
		sql.append("  a.xmmkmc,         ");
		sql.append("  a.jfxmmc,         ");
		sql.append("  (case  ");
		sql.append("when to_number(a.fs) > to_number(b.xdfs) then          ");
		sql.append(" to_number(b.xdfs)");
		sql.append("else ");
		sql.append(" to_number(a.fs)");
		sql.append("  end) fs,          ");
		sql.append("  (select nvl(sum(fs), 0)        ");
		sql.append(" from (select xmmkdm,        ");
		sql.append("   jfxmmc,        ");
		sql.append("   (case          ");
		sql.append("     when sum(e.fs) > to_number(xdfs) then  ");
		sql.append("      to_number(xdfs) ");
		sql.append("     else         ");
		sql.append("      sum(e.fs)   ");
		sql.append("   end) fs        ");
		sql.append("         from view_zjly_zhf e");
		sql.append("        where fs is not null ");
		sql.append("          and e.xh = ?       ");
		sql.append("          and not exists     ");
		sql.append("        (select 1 ");
		sql.append("      from xg_zjly_zhszf_jdszb g ");
		sql.append("     where e.jfxmdm = g.jfxmdm)  ");
		sql.append("        group by xmmkdm, jfxmmc, xdfs) j    ");
		sql.append("where a.xmmkdm = j.xmmkdm) zf,   ");
		sql.append("  (select nvl(to_number(max(fs)), 0) jfxmzf     ");
		sql.append(" from view_zjly_zhf h        ");
		sql.append("where exists (select 1       ");
		sql.append("         from xg_zjly_zhszf_jdszb i         ");
		sql.append("        where h.jfxmdm = i.jfxmdm)          ");
		sql.append("  and xh = ?    ");
		sql.append("  and fs is not null) zf1,   ");
		sql.append("  c.xf,  ");
		sql.append("  c.hgf, ");
		sql.append("  (select n from (select count(1) n, xmmkdm     ");
		sql.append(" from xg_zjly_zhszf_jfxmb e  ");
		sql.append("where sfbt = '1'");
		sql.append("  and not exists");
		sql.append("(select 1       ");
		sql.append("          from view_zjly_zhf f   ");
		sql.append("        where e.jfxmdm = f.jfxmdm");
		sql.append("          and f.xh = ?) group by xmmkdm) j where a.xmmkdm = j.xmmkdm) btf,       ");
		sql.append("  t1.sxsm,          ");
		sql.append("  t1.cysj,t1.lb");
		sql.append("        from (select a.xmmkdm, ");
		sql.append("      a.jfxmdm, ");
		sql.append("      a.xmmkmc, ");
		sql.append("      a.jfxmmc, ");
		sql.append("      sum(a.fs) fs");
		sql.append(" from view_zjly_zhf a  ");
		sql.append("where a.xh = ?  ");
		sql.append("  and a.xmmkdm is not null  and a.shzt!='2'  ");
		sql.append(" group by a.xmmkdm, a.xmmkmc, a.jfxmmc, a.jfxmdm) a ");
		sql.append("        left join xg_zjly_zhszf_jfxmb b     ");
		sql.append("          on a.jfxmdm = b.jfxmdm ");
		sql.append("        left join xg_zjly_zhszf_mkxmb c     ");
		sql.append("          on a.xmmkdm = c.xmmkdm ");
		sql.append("        left join (   ");
		sql.append("  select xh,jfxmdm,sxsm,cysj, replace(max(lb), ';', '\n') lb from( ");
		sql.append(" select xh,jfxmdm,sxsm,cysj, ");
		sql.append(" WM_CONCAT(lb) over(partition by jfxmdm order by jfxmdm) lb from (");
		sql.append("          select xh,jfxmdm, ");
		sql.append("          replace(max(sxsm), ';', '\n') sxsm,      ");
		sql.append("          replace(max(cysj), ';', '\n') cysj,lb       ");
		sql.append("     from (select xh,        ");
		sql.append("       jfxmdm,    ");
		sql.append("       WM_CONCAT(sxsm) over(partition by jfxmdm order by jfxmdm) sxsm,");
		sql.append("       WM_CONCAT(cysj) over(partition by jfxmdm order by jfxmdm) cysj,lb ");
		sql.append("  from (select a.xh,  ");
		sql.append("a.jfxmdm,         ");
		sql.append("a.sxsm,");
		sql.append("a.cysj, ");
		sql.append("  case when to_number(length(a.lrr))>10 then '学生申请' else (select bmmc||'导入' from view_fdyxx where zgh =a.lrr) end lb ");
		sql.append("          from xg_zjly_zhszf_drjlb a        ");
		sql.append("         where xh = ? and a.shzt!='2'))          ");
		sql.append("    group by jfxmdm, xh,lb ))  group by jfxmdm,xh,sxsm,cysj ) t1  ");
		sql.append("          on a.jfxmdm = t1.jfxmdm) a) a     ");
		sql.append("  order by xmmkdm, to_number(nvl(jfxmdm,0))) a   ");

		return dao.getListNotOut(sql.toString(), new String[]{xh,xh,xh,xh,xh});
	}
	
	
	public HashMap<String,String> getZfs(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(t.mkzf) zfs      ");
		sql.append("   from (select distinct xmmkdm, mkzf     ");
		sql.append("           from (select a.*  ");
		sql.append("      from (select a.xmmkdm, ");
		sql.append("     a.xmmkmc, ");
		sql.append("     to_number(a.xf) xf,     ");
		sql.append("     jfxmmc,   ");
		sql.append("     fs jfxmzf,");
		sql.append("     (case     ");
		sql.append("       when xmmkdm = 'A' and ");
		sql.append("            to_number(zf) + to_number(zf1) >            ");
		sql.append("            to_number(xf) then            ");
		sql.append("        to_number(xf)        ");
		sql.append("       when xmmkdm = 'A' and ");
		sql.append("            to_number(zf) + to_number(zf1) <            ");
		sql.append("            to_number(xf) then            ");
		sql.append("        to_number(zf) + to_number(zf1)    ");
		sql.append("       when to_number(zf) > to_number(xf) then          ");
		sql.append("        to_number(xf)        ");
		sql.append("       else    ");
		sql.append("        to_number(zf)        ");
		sql.append("     end) mkzf,");
		sql.append("     (case     ");
		sql.append("       when xmmkdm = 'E' then");
		sql.append("        ''     ");
		sql.append("       when to_number(nvl(hgf, 0)) > zf then            ");
		sql.append("        '不合格'");
		sql.append("       when btf = 1 then     ");
		sql.append("        '不合格'");
		sql.append("       else    ");
		sql.append("        '合格' ");
		sql.append("     end) hgf, ");
		sql.append("     sxsm,     ");
		sql.append("     cysj      ");
		sql.append("from (select a.xmmkdm,       ");
		sql.append("a.jfxmdm,       ");
		sql.append("a.xmmkmc,       ");
		sql.append("a.jfxmmc,       ");
		sql.append("(case           ");
		sql.append("  when to_number(a.fs) >     ");
		sql.append("       to_number(b.xdfs) then");
		sql.append("   to_number(b.xdfs)         ");
		sql.append("  else          ");
		sql.append("   to_number(a.fs)           ");
		sql.append("end) fs,        ");
		sql.append("(select nvl(sum(fs), 0)      ");
		sql.append("   from (select xmmkdm,      ");
		sql.append("  jfxmmc,      ");
		sql.append("  (case        ");
		sql.append("    when sum(e.fs) >         ");
		sql.append("         to_number(xdfs) then");
		sql.append("     to_number(xdfs)         ");
		sql.append("    else       ");
		sql.append("     sum(e.fs) ");
		sql.append("  end) fs      ");
		sql.append("           from view_zjly_zhf e            ");
		sql.append("          where fs is not null");
		sql.append("            and e.xh = ?     ");
		sql.append("            and shzt = '1'   ");
		sql.append("            and not exists   ");
		sql.append("          (select 1          ");
		sql.append("     from xg_zjly_zhszf_jdszb g           ");
		sql.append("    where e.jfxmdm = g.jfxmdm)            ");
		sql.append("          group by xmmkdm, jfxmmc, xdfs) j ");
		sql.append("  where a.xmmkdm = j.xmmkdm) zf,           ");
		sql.append("(select nvl(to_number(max(fs)), 0) jfxmzf  ");
		sql.append("   from view_zjly_zhf h      ");
		sql.append("  where exists (select 1     ");
		sql.append("           from xg_zjly_zhszf_jdszb i      ");
		sql.append("          where h.jfxmdm = i.jfxmdm)       ");
		sql.append("    and xh = ?  ");
		sql.append("    and fs is not null       ");
		sql.append("    and shzt = '1') zf1,     ");
		sql.append("c.xf,           ");
		sql.append("c.hgf,          ");
		sql.append("(select distinct 1           ");
		sql.append("   from xg_zjly_zhszf_jfxmb g");
		sql.append("  where sfbt = '1'           ");
		sql.append("    and not exists           ");
		sql.append("  (select 1     ");
		sql.append("           from xg_zjly_zhszf_drjlb f      ");
		sql.append("          where g.jfxmdm = f.jfxmdm        ");
		sql.append("            and f.xh = ? )) btf,            ");
		sql.append("t1.sxsm,        ");
		sql.append("t1.cysj         ");
		sql.append("        from (select a.xmmkdm,            ");
		sql.append("        a.jfxmdm,            ");
		sql.append("        a.xmmkmc,            ");
		sql.append("        a.jfxmmc,            ");
		sql.append("        sum(a.fs) fs         ");
		sql.append("   from view_zjly_zhf a      ");
		sql.append("  where a.xh = ?");
		sql.append("    and a.xmmkdm is not null ");
		sql.append("  group by a.xmmkdm,         ");
		sql.append("           a.xmmkmc,         ");
		sql.append("           a.jfxmmc,         ");
		sql.append("           a.jfxmdm) a       ");
		sql.append("        left join xg_zjly_zhszf_jfxmb b   ");
		sql.append("          on a.jfxmdm = b.jfxmdm          ");
		sql.append("        left join xg_zjly_zhszf_mkxmb c   ");
		sql.append("          on a.xmmkdm = c.xmmkdm          ");
		sql.append("        left join (select xh,");
		sql.append("            jfxmdm,          ");
		sql.append("            replace(max(sxsm),");
		sql.append("      ';',     ");
		sql.append("      '\n') sxsm,        ");
		sql.append("            replace(max(cysj),");
		sql.append("      ';',     ");
		sql.append("      '\n') cysj         ");
		sql.append("       from (select xh,      ");
		sql.append("      jfxmdm,  ");
		sql.append("      WM_CONCAT(sxsm) over(partition by jfxmdm order by jfxmdm) sxsm,");
		sql.append("      WM_CONCAT(cysj) over(partition by jfxmdm order by jfxmdm) cysj ");
		sql.append(" from (select a.xh,          ");
		sql.append(" a.jfxmdm,      ");
		sql.append("  a.sxsm,        ");
		sql.append("  a.cysj     ");
		sql.append("      from xg_zjly_zhszf_drjlb a            ");
		sql.append("     where xh = ? ))  ");
		sql.append("  group by jfxmdm, xh) t1          ");
		sql.append("  on a.jfxmdm = t1.jfxmdm) a) a");
		sql.append("     order by xmmkdm, jfxmmc)  ");
		sql.append("          order by xmmkdm) t       ");
		
		return DAO.getInstance().getMap(sql.toString(), new String[]{xh,xh,xh,xh,xh}, new String[]{"zfs"});
	}

	/** 
	 * @描述:模块-模块总分-是否合格
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-9 上午11:58:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * void 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getmkzf(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select xmmkmc,xmmkdm,sum(xmzf) mkzf,case when sum(sfbt) = 0 then '不合格' when (sum(sfbt) > 0 and sum(xmzf) >= hgf) then  '合格'  when (sum(sfbt) > 0 and sum(xmzf) < hgf) then '不合格' end sfhg from ( " );
		sql.append(" select jfxmmc,xmmkmc,xmmkdm,nvl(xmzf,'0')xmzf,hgf,to_number(sfbt) sfbt from ( ");
		sql.append(" select case when to_number(xmf)>to_number(xdfs) then xdfs else to_char(xmf,'fm9999990.9999') end  xmzf,k.jfxmmc,k.xmmkmc,k.xmmkdm,k.hgf,k.sfbt ");
		sql.append(" from (select (select sum(b.fs)from VIEW_ZJLY_ZHF b where b.jfxmdm = t.jfxmdm");
		sql.append(" and xh =? and shzt='1') xmf,t.jfxmmc,t.xmmkmc,t.xmmkdm,t.hgf,t1.xdfs,t1.sfbt from VIEW_ZJLY_ZHF t left join XG_ZJLY_ZHSZF_JFXMB t1 ");
		sql.append(" on t.jfxmdm = t1.jfxmdm where xh =?) k ");
		sql.append(" )group by jfxmmc,xmmkmc,xmmkdm,xmzf,hgf,sfbt ) group by xmmkmc,xmmkdm,hgf order by xmmkdm");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh});
	}
	
	/**
	 * @描述:分模块取项目list
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-9 下午04:33:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getsingleMkXmlist(String xh,String mkmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.jfxmmc,t.jfxmdm, t.fs,case when to_number(t.xmf) > to_number(t1.xdfs) then t1.xdfs else to_char(t.xmf,'fm9999990.9999') end xmzf,t.lb,t.sxsm,t.sxf,t.cysj " );
		sql.append("from ( ");
		sql.append("select a.jfxmdm,a.jfxmmc,WM_CONCAT(a.fs) over(partition by a.jfxmdm order by a.jfxmdm) fs,"); 
		sql.append("(select sum(b.fs)from view_zjly_zhf b where b.jfxmdm = a.jfxmdm and xh =?) xmf,a.lb,a.sxsm,a.sxf,a.cysj ");
		sql.append("from ( ");
		sql.append("select jfxmdm,jfxmmc, fs, lb, sxsm, fs sxf, cysj from view_zjly_zhf t where xh = ? and xmmkmc = ?) a) t ");
		sql.append("left join xg_zjly_zhszf_jfxmb t1 on t.jfxmdm = t1.jfxmdm");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh,mkmc});
	}
	
	
	
	
}

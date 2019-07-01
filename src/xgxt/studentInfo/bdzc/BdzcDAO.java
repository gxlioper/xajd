package xgxt.studentInfo.bdzc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class BdzcDAO {
	
	DAO dao = DAO.getInstance();
	
	
	/**
	 * 注册时间设置(2011.11.4 qlj 优化)
	 * @param pkValue
	 * @param zckssj
	 * @param zcjssj
	 * @param hzcjssj
	 * @return
	 * @throws Exception 
	 */
	public boolean saveSjsz(String[] pkValue, String[] zckssj, String[] zcjssj,
			String[] hzcjssj) throws Exception {
		
		//初始化报到时间
		initBdsj();
		
		String[] sqlArr = new String[pkValue.length];

		
		for (int i = 0; i < pkValue.length; i++) {
		
			
			//插入SQL
			StringBuilder sb = new StringBuilder();
			sb.append(" update xsxx_bdzc_sjsz set");
			sb.append(" zckssj=" );
			sb.append("'"+zckssj[i]+"'");
			sb.append(" ,zcjssj=" );
			sb.append("'"+zcjssj[i]+"'");
			sb.append(" ,hzcjssj=" );
			sb.append("'"+hzcjssj[i]+"'");
			//view_xsxx_bdzc_sjsz
			sb.append("  where xh||xn||xq='");
			sb.append(pkValue[i]);
			sb.append("'");
			
			sqlArr[i] = sb.toString();
		}
		
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	
	
	/**
	 * 下拉列表选项维护
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("shzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "未审核", "通过","不通过" };
			mc = new String[] { "未审核", "通过" ,"不通过"};
		} else if ("zczt".equalsIgnoreCase(lx)) {
			dm = new String[] { "未注册", "注册" };
			mc = new String[] { "未注册", "注册"};
		} else if ("ishzc".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否"};
		} else if ("tjjg".equals(lx)) {
			dm = new String[] { "xy", "zy","bj" };
			mc = new String[] { Base.YXPZXY_KEY, "专业","班级"};
		}
		return dao.arrayToList(dm, mc);
	}
	
	
	
	/**
	 * 初始化注册状态
	 * @return
	 * @throws Exception
	 */
	public boolean zctb() throws Exception {
		String sql = "update xsxxb set sfzc='未注册'";
		
		return dao.runProcuder(sql, new String[] {});
	}
	
	
	
	/**
	 * 批量修改注册状态
	 * @param xh
	 * @param sfzc
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> saveZczt(String[] pkValue, String[] xn,
			String[] xq, String zczt) throws SQLException {
		/*
		 * 修改注册状态必须是在时间设置范围内的，
		 * 默认时间段为注册开始时间－注册结束时间
		 * 如果缓注册时间不为空并且在bdzc_hzc里的学校审核为通过时
		 * 时间段为注册开始时间－缓注册结束时间
		 */
		String[] sqlArr = new String[pkValue.length*3];
		int n = 0;
		StringBuilder sqlBuilder = new StringBuilder();
		//查询有几条记录在时间设置范围内
		sqlBuilder.append("select count(*) count from view_bdzc_zcdj a where (");
		
		for ( int i=0 ; i < pkValue.length; i++) {
			sqlBuilder.append(" a.xh||a.xn||a.xq='");
			sqlBuilder.append(pkValue[i]);
			sqlBuilder.append("'");
			
			if ( i!= pkValue.length-1) {
				sqlBuilder.append(" or ");
			}
			
			StringBuilder delBuilder = new StringBuilder();
			delBuilder.append("delete from bdzc_zcdj a where a.xh||a.xn||a.xq='");
			delBuilder.append(pkValue[i]);
			delBuilder.append("' and (select to_number(to_char(sysdate,'yyyymmdd')) from dual )");
			delBuilder.append("between (select zckssj from xsxx_bdzc_sjsz where xh||xn||xq='");
			delBuilder.append(pkValue[i]);
			delBuilder.append("')");
			delBuilder.append("and (select nvl(case when xh in ");
			delBuilder.append("(select a.xh from bdzc_hzc a where a.xxsh='通过' ");
			delBuilder.append("and xh=a.xh and xn=a.xn and xq=a.xq) then hzcjssj end ,zcjssj)");
			delBuilder.append("from xsxx_bdzc_sjsz where xh||xn||xq='");
			delBuilder.append(pkValue[i]);
			delBuilder.append("')");
			
			
			
			sqlArr[n] = delBuilder.toString();
			n++;
			
			StringBuilder sb = new StringBuilder();
			sb.append("insert into bdzc_zcdj select a.xh,a.xn,a.xq,'");
			sb.append(zczt);
			sb.append("' from view_bdzc_zcdj a where a.xh||a.xn||a.xq='");
			sb.append(pkValue[i]);
			sb.append("' and (select to_number(to_char(sysdate,'yyyymmdd')) from dual ) ");
			sb.append("between (select zckssj from xsxx_bdzc_sjsz where xh||xn||xq='");
			sb.append(pkValue[i]);
			sb.append("')");
			sb.append("and (select nvl(case when xh in ");
			sb.append("(select a.xh from bdzc_hzc a where a.xxsh='通过' ");
			sb.append("and xh=a.xh and xn=a.xn and xq=a.xq) then hzcjssj end ,zcjssj)");
			sb.append("from xsxx_bdzc_sjsz where xh||xn||xq='");
			sb.append(pkValue[i]);
			sb.append("')");
			
			sqlArr[n++] = sb.toString();
			
			//修改学生信息里的注册状态
			if (Base.currXn.equals(xn[i]) && Base.currXq.equals(xq[i])) {
				StringBuilder xsxxBuilder = new StringBuilder();
				xsxxBuilder.append("update xsxxb b set sfzc='");
				xsxxBuilder.append(zczt);
				xsxxBuilder.append("' where xh=");
				xsxxBuilder.append("(select xh from view_bdzc_zcdj a where ");
				xsxxBuilder.append("a.xh||a.xn||a.xq='");
				xsxxBuilder.append(pkValue[i]);
				xsxxBuilder.append("') and (select to_number(to_char(sysdate,'yyyymmdd')) from dual )");
				xsxxBuilder.append("between (select zckssj from xsxx_bdzc_sjsz where xh=b.xh and xn='");
				xsxxBuilder.append(Base.currXn);
				xsxxBuilder.append("' and xq='");
				xsxxBuilder.append(Base.currXq);
				xsxxBuilder.append("')");
				xsxxBuilder.append("and (select nvl(case when xh in ");
				xsxxBuilder.append("(select a.xh from bdzc_hzc a where a.xxsh='通过' ");
				xsxxBuilder.append("and xh=a.xh) then hzcjssj end ,zcjssj) ");
				xsxxBuilder.append("from xsxx_bdzc_sjsz where xh=b.xh and xn='");
				xsxxBuilder.append(Base.currXn);
				xsxxBuilder.append("' and xq='");
				xsxxBuilder.append(Base.currXq);
				xsxxBuilder.append("')");
				
				sqlArr[n++] = xsxxBuilder.toString();
			}
		}
		
		//接上查询
		sqlBuilder.append(" ) and (select to_number(to_char(sysdate,'yyyymmdd')) from dual ) ");
		sqlBuilder.append("between (select zckssj from xsxx_bdzc_sjsz where xh=a.xh and xn=a.xn and xq=a.xq)");
		sqlBuilder.append(" and (select nvl(case when xh in ");
		sqlBuilder.append("(select a.xh from bdzc_hzc a where a.xxsh='通过' ");
		sqlBuilder.append("and xh=a.xh) then hzcjssj end ,zcjssj) ");
		sqlBuilder.append("from xsxx_bdzc_sjsz where xh=a.xh and xn=a.xn and xq=a.xq)");
		
		int[] result = dao.runBatch(sqlArr);
		boolean flg = dao.checkBatch(result);
		String count = dao.getOneRs(sqlBuilder.toString(), new String[] {}, "count");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", String.valueOf(flg));
		map.put("count", count);
		return map;
	}
	
	/**
	 * 注册状态同步学生信息
	 * @param xh
	 * @param sfzc
	 * @return
	 * @throws Exception 
	 */
	public boolean tbZczt() throws Exception {
		StringBuilder sql=new StringBuilder();
		sql.append(" update xsxxb a set sfzc=( ");
		sql.append("select zczt from bdzc_zcdj b where xn=(select dqxn from xtszb where rownum=1) ");
		sql.append("and xq=(select dqxq from xtszb where rownum =1) and  a.xh=b.xh) ");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 获取班级基本信息
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getClassInfo(String bjdm) {
		String sql = "select * from view_njxyzybj where bjdm = ?";

		return dao.getMap(sql, new String[] { bjdm }, new String[] { "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc" });
	}
	
	
	
	/**
	 * 地大的学期列表
	 * @return
	 */
	public List<HashMap<String, String>> getXq() {
		String sql = "select xqdm,xqmc from xqdzb where xqjb<3";
		return dao.getList(sql, new String[] {}, new String[] {"xqdm","xqmc"});
	}
	
	
	
	/**
	 * 是否在注册时间段
	 * @param pkValue
	 * @return
	 */
	public String getZcsj(String pkValue) {
		StringBuilder sql = new StringBuilder();
		sql.append("select case when to_number(nowDate) between zckssj and jssj ");
		sql.append("then 'true' else 'false' end iszcsj from(");
		sql.append("select a.zckssj,to_char(sysdate,'yyyymmdd') nowDate,");
		sql.append("case when a.hzcjssj > '' and a.xh in ");
		sql.append("(select xh from bdzc_hzc where xn=a.xn and xq=a.xq and xh=a.xh and xxsh='通过')");
		sql.append(" then hzcjssj else zcjssj end jssj from xsxx_bdzc_sjsz a where a.xh||a.xn||a.xq='");
		sql.append(pkValue);
		sql.append("')");
		
		return dao.getOneRs(sql.toString(), new String[] {}, "iszcsj");
	}


	/**
	 * 单个注册
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	public boolean updateZczt(String pkValue) throws SQLException {
		String[] sqlArr = new String[3];
		sqlArr[0] = "delete from bdzc_zcdj where xh||xn||xq='"+pkValue+"'";
		sqlArr[1] = "insert into bdzc_zcdj select xh,xn,xq,'注册' from view_bdzc_zcdj where xh||xn||xq='"+pkValue+"'";
		sqlArr[2] ="update xsxxb set sfzc='注册' where xh=(select xh from view_bdzc_zcdj where xh||xn||xq='"+pkValue+"')";
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}


	/**
	 * 是否缓注册时间
	 * @param xh
	 * @return
	 */
	public String isHzcsj(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select case when to_number(to_char(sysdate,'yyyymmdd'))");
		sql.append(" between zckssj and hzcjssj then 'true' else 'false'");
		sql.append("  end ishzcsj from xsxx_bdzc_sjsz where xh=?");
		
		return dao.getOneRs(sql.toString(), new String[] {xh}, "ishzcsj");
	}
	
	
	/**
	 * 是否欠费
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> getSfqf(String xh,String xn,String xq) {
		
		String sql = "select case when ysje=0 then '未缴学费' when ysje>0 and ysje>ssje then '是' else '否' end sfqf," +
				"ysje-ssje qfje from view_bdzc_zcdj where xh=? and xn=? and xq=?";
		
		return dao.getMap(sql, new String[] {xh,xn,xq}, new String[] {"sfqf","qfje"});
	}
	
	/**
	 * 李涛  增加 2010-8-27
	 * 检查该生是否有缴费
	 * @param xh
	 * @param nd
	 * @return
	 */
	public boolean checkStusfJf(String xh, String nd) {
		String count = dao.getOneRs("select count(xh) cnt from cw_bks_xssfb a where xh=? and SFQJDM = ?", new String[]{xh, nd}, "cnt");
		return "0".equalsIgnoreCase(count) ? false : true;
	}
	
	
	public boolean initBdsj() throws Exception{
		StringBuilder sql=new StringBuilder();
		DAO dao=DAO.getInstance();
		sql.append(" insert into xsxx_bdzc_sjsz(xh,xn,xq) ");
		sql.append(" select xh,xn,xq from(");
		sql.append(" select xh,(select dqxn from xtszb where rownum='1')xn,");
		sql.append("   (select dqxq from xtszb where rownum='1')xq");
        sql.append("   from xsxxb union ");
        sql.append("  select xh,(select dqxn from xtszb where rownum='1')xn, ");
        sql.append("   (select dqxq from xtszb where rownum='1')xq ");
        sql.append("   from bks_xsjbxx a where not exists(select 1 from xsxxb b where a.xh=b.xh)  ");
        sql.append("  )a where not exists (select 1 from xsxx_bdzc_sjsz b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)   "); 
        return dao.runUpdate(sql.toString(), new String[]{});
        
	}
}

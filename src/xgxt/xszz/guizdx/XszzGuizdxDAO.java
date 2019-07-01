package xgxt.xszz.guizdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.jxgl.JxglTyDAO;
import xgxt.jxgl.JxglTyForm;
import xgxt.rcsw.gzdx.RcswGzdxModel;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.xszz.XszzDAO;
import xgxt.xszz.XszzTyForm;

public class XszzGuizdxDAO extends XszzDAO {

	//年度月份
	public final String[] ND_YF = new String[] { "01", "02", "03", "04",
			"05", "06", "07", "08", "09", "10", "11", "12" };
	
	/**
	 * 获得专业列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getZyList(XszzTyForm model,String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		Pages pages = model.getPages();

		//年度
		String nd = model.getNd();
		
		String[] queryList = new String[] { "xydm" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* ,rownum r from ( ");
		sql.append("select distinct a.zydm,a.zymc,a.xydm,a.xymc, '");
		sql.append(nd);
		sql.append("'nd,");
		sql.append("(select b.bzlx from (");
		sql.append(getFsbzTable());
		sql.append(") b where b.nd = '");
		sql.append(nd);
		sql.append("' and a.zydm = b.zydm) bzlx");
		sql.append(" from view_njxyzybj a ");
		sql.append(query);
		sql.append(" order by zydm");
		sql.append(") t");
	
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ( ");
		sb.append(sql);
		sb.append(") where r > ");
		sb.append(pages.getStart());
		sb.append(" and r <= ");
		sb.append((pages.getStart() + pages.getPageSize()));
		
		String[] valueList = myQuery.getInputList();
		
		ArrayList<String[]> list = dao.rsToVator(sb.toString(), valueList,
				colList);
		
		sb = new StringBuffer();
		sb.append("select count(*) count from (");
		sb.append(sql);	
		sb.append(")");
		
		String count = dao.getOneRs(sb.toString(), valueList, "count");
		
		if(!Base.isNull(count)){
			pages.setMaxRecord(Integer.parseInt(count));
		}
		
		return list;
	}
	
	/**
	 * 获得副食补助项目列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public List<HashMap<String, String>> getFsbzXmList(XszzTyForm model) {

		DAO dao = DAO.getInstance();

		String nd = model.getNd();
		String sql = "select a.xmdm,(select b.mc from xszz_fsbzlxb b where b.dm = a.xmdm) xmmc from xszz_fsbzffb a where nd = ?";

		String[] inputValue = new String[] { nd };
		String[] outputValue = new String[] { "xmdm", "xmmc" };

		List<HashMap<String, String>> list = dao.getList(sql, inputValue,
				outputValue);

		return list;
	}
		
	/**
	 * 保存副食补助专业项目
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveFsbzZyxm(List<HashMap<String, String>> list,
			String tableName) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		String[] exec_del = null;
		String[] exec_ins = null;
		
		int n = 0;
		
		if (list != null && list.size() > 0) {

			exec_del = new String[list.size()];
			exec_ins = new String[list.size()];
			
			for (int i=0;i<list.size();i++) {
				
				HashMap<String, String> map = list.get(i);
				
				StringBuffer sql = new StringBuffer();

				// 年度
				String nd = map.get("nd");
				// 专业代码
				String zydm = map.get("zydm");
				// 补助类型
				String bzlx = map.get("bzlx");

				sql.append(" delete from ");
				sql.append(tableName);
				sql.append(" where nd = '" + nd + "' ");
				sql.append(" and zydm = '" + zydm + "' ");

				exec_del[i] = sql.toString();

				//补助类型不能不空
				if (!Base.isNull(bzlx)) {
					sql = new StringBuffer();
					sql.append(" insert into ");
					sql.append(tableName);
					sql.append(" (nd,zydm,bzlx)");
					sql.append(" values( ");
					sql.append("'" + nd + "'");
					sql.append(",'" + zydm + "'");
					sql.append(",'" + bzlx + "'");
					sql.append(" ) ");

					exec_ins[i] = sql.toString();
				}
			}
			
			flag = dao.saveArrDate(exec_del);
			if(flag){
				flag = dao.saveArrDate(exec_ins);
			}
		}
		return flag;
	}
	
	/**
	 * 获得副食补助sql
	 * 
	 * @author luojw
	 */
	public String getFsbzTable(){
		
		StringBuffer table = new StringBuffer();
		
		table.append("select t.zydm, t.nd, ");
		table.append("max(ltrim(sys_connect_by_path((t.bzlx) || '', '!!@@!!'), '!!@@!!')) bzlx ");
		table.append("from (select distinct (a.zydm),a.nd,a.bzlx, ");
		table.append("row_number() over(partition by a.zydm, a.nd order by a.zydm, a.nd) pno, ");
		table.append("row_number() over(partition by a.zydm, a.nd order by a.zydm, a.nd) - 1 sno ");
		table.append("from xszz_fsbzzyb a order by a.zydm, a.nd, pno) t ");
		table.append("start with pno = 1 connect by prior pno = sno ");
		table.append("and prior t.zydm || t.nd = t.zydm || t.nd ");
		table.append("group by t.zydm, t.nd order by t.zydm || t.nd ");
		
		return table.toString();
	}

	/**
	 * 获得副食补助学生发放列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getFsbzXsffList(XszzTyForm model,String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		Pages pages = model.getPages();

		// 年度
		String nd = model.getNd();
		// 月份
		String yf = model.getYf();
		// 补助类型
		String bzlx = model.getBzlx();
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj" };

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* ,rownum r from ( select * from (");
		sql.append("select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc, ");
		sql.append("a.bjdm,a.bjmc,'"+nd+"' nd,'"+yf+"' || '月'  yf,nvl(c.bzlx, '无') bzlx, ");
		sql.append("nvl((select d.mc from xszz_fsbzlxb d where c.bzlx = d.dm), '无') bzmc, ");
		sql.append("decode(b.xh, '', 'no', 'yes') sfdj from view_xsjbxx a ");
		sql.append("left join xszz_fsbz_xsffb b on a.xh = b.xh ");
		sql.append("and b.yf = '"+yf+"' and b.nd = '"+nd+"' and b.bzlx = '"+bzlx+"' ");
		sql.append("left join xszz_fsbzzyb c on a.zydm = c.zydm and c.nd = '"+nd+"' and c.bzlx = '"+bzlx+"' ");
		sql.append(")");
		sql.append(query);
		sql.append(") t");
	
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ( ");
		sb.append(sql);
		sb.append(") where r > ");
		sb.append(pages.getStart());
		sb.append(" and r <= ");
		sb.append((pages.getStart() + pages.getPageSize()));
		
		String[] valueList = myQuery.getInputList();
		ArrayList<String[]> list = dao.rsToVator(sb.toString(), valueList,
				colList);
		
		sb = new StringBuffer();
		sb.append("select count(*) count from (");
		sb.append(sql);	
		sb.append(")");
		
		String count = dao.getOneRs(sb.toString(), valueList, "count");
		
		if(!Base.isNull(count)){
			pages.setMaxRecord(Integer.parseInt(count));
		}
		
		return list;
	}
	
	/**
	 * 获得副食补助未发放sql
	 * 
	 * @author luojw
	 */
	public String getFsbzWffTable(XszzTyForm model){

		// 年度
		String nd = model.getQueryequals_nd();
		// 补助类型
		String bzlx = model.getQueryequals_bzlx();
		
		StringBuffer table = new StringBuffer();
		
		if (this.ND_YF != null && this.ND_YF.length > 0) {
			
			table.append("select a.*,'"+bzlx+"' bzlx,'"+nd+"' nd, ");
			table.append("(select c.mc from xszz_fsbzlxb c where c.dm = '"+bzlx+"') bzmc from (");
			for (int i=0;i<this.ND_YF.length;i++) {
				
				//月份
				String yf = this.ND_YF[i];
				
				if (i != 0) {
					table.append("union all ");
				}
				table.append("select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc, ");
				table.append("a.zydm,a.zymc,a.bjdm,a.bjmc,'"+yf+"' yf ");
				table.append("from view_xsjbxx a where exists (select 1 ");
				table.append("from xszz_fsbzzyb b where a.zydm = b.zydm ");
				table.append("and b.nd = '"+nd+"' and b.bzlx ='"+bzlx+"') ");
			}
			table.append(") a ");
			table.append("where not exists (select 1 from xszz_fsbz_xsffb c ");
			table.append("where a.xh = c.xh and c.nd = '"+nd+"' and c.yf = a.yf ");
			table.append(")");
		}
		
		return table.toString();
	}
	
	/**
	 * 获得副食补助未发放列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getFsbzWffList(XszzTyForm model,String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		Pages pages = model.getPages();
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "nd",
				"yf", "bzlx" };

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();
		
		//拼接sql
		String table = getFsbzWffTable(model);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* ,rownum r from ( select * from (");
		sql.append(table);
		sql.append(")");
		sql.append(query);
		sql.append(") t");
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ( ");
		sb.append(sql);
		sb.append(") where r > ");
		sb.append(pages.getStart());
		sb.append(" and r <= ");
		sb.append((pages.getStart() + pages.getPageSize()));
		
		String[] valueList = myQuery.getInputList();
		ArrayList<String[]> list = dao.rsToVator(sb.toString(), valueList,
				colList);
		
		sb = new StringBuffer();
		sb.append("select count(*) count from (");
		sb.append(sql);	
		sb.append(")");
		
		String count = dao.getOneRs(sb.toString(), valueList, "count");
		
		if(!Base.isNull(count)){
			pages.setMaxRecord(Integer.parseInt(count));
		}
		
		return list;
	}
}

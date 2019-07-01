package xgxt.jxgl.cdty.gfjy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CdtyGfjyDAO extends CommDAO {

	/**
	 * 删除国防课程(2011.11.15 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delGfkc(CdtyGfjyForm myForm) throws Exception {

		String[] pkValue = myForm.getPkV();
		String[] sql = new String[pkValue.length];

		for (int i = 0; i < pkValue.length; i++) {

			sql[i] = " delete from xg_jxgl_gfjygl where guid='" + pkValue[i]
					+ "' ";
		}

		return saveArrDate(sql);
	}

	/**
	 * 获取国防教育课程信息列表(2011.11.15 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getGfkcList(CdtyGfjyForm myForm) throws Exception {

		String[] colList = { "guid", "nd", "kcmc", "bl" };
		String[] col = { "nd" };
		String[] colLike = { "kcmc" };
		StringBuilder sql = new StringBuilder();
		MakeQuery mQuery = new MakeQuery();

		mQuery.makeQuery(col, colLike, myForm);
		sql.append("  select rownum r,guid,nd, ");
		sql.append("  kcmc,bl||'%' bl from xg_jxgl_gfjygl a ");

		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString(), mQuery.getInputList(), colList, myForm);
	}

	/**
	 * 保存国防课程名称(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveKcxx(CdtyGfjyForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String nd = myForm.getNd();
		String kcmc = myForm.getKcmc();
		String bl = myForm.getBl();

		sql.append(" insert into xg_jxgl_gfjygl(nd,kcmc,bl)  ");
		sql.append(" values(?,?,?)");
		String[] inputV = { nd, kcmc, bl };
		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 修改课程信息(2011.11.15 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateKcxx(CdtyGfjyForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String kcmc = myForm.getKcmc();
		String bl = myForm.getBl();
		String guid = myForm.getCheckVal()[0];

		sql.append(" update xg_jxgl_gfjygl  ");
		sql.append(" set kcmc=?, ");
		sql.append(" bl=? ");
		sql.append(" where guid= ? ");

		String[] inputV = { kcmc, bl, guid };
		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 获取课程信息信息
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneKcmc(CdtyGfjyForm myForm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String pkValue = myForm.getCheckVal()[0];
		sql.append(" select guid,kcmc,nd,bl from xg_jxgl_gfjygl");
		sql.append(" where guid= ? ");
		String colList[] = { "guid", "nd", "kcmc", "bl" };
		return dao.getMap(sql.toString(), new String[] { pkValue }, colList);
	}

	/**
	 * 获取本年度的国防教育课程列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getKcList() {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid,kcmc,nd,bl from xg_jxgl_gfjygl ");
		sql.append(" where nd=(select dqnd from xtszb where rownum=1) order by guid ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"guid", "kcmc" });
	}
	
	/**
	 * 获取本年度的国防教育课程列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getEverKcList(CdtyGfjyForm myForm) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid,kcmc,nd,bl from xg_jxgl_gfjygl ");
		sql.append(" where nd='"+myForm.getNj()+"' order by guid ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"guid", "kcmc" });
	}

	/**
	 * 获取本年度的国防教育课程列表
	 * 
	 * @return
	 */
	public ArrayList<String[]> getGfcjList(CdtyGfjyForm myForm)
			throws Exception {

		DAO dao = DAO.getInstance();
		MakeQuery mQuery = new MakeQuery();
		List<HashMap<String, String>> kcList = getKcList();
		List<String> kcfList = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();
		StringBuilder sumSql = new StringBuilder();
		StringBuilder caseSql = new StringBuilder();

		String[] cList = { "nj", "xydm", "zydm", "bjdm" };
		String[] cLikeList = { "xh", "xm" };

		mQuery.makeQuery(cList, cLikeList, myForm);
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
		sql.append("  select rownum r,a.* from ( ");
		sql.append("  select b.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc ");
		sumSql.append(" select xh ");
		caseSql.append(" select a.xh,a.nj,a.kcdm ");
		for (int i = 0; i < kcList.size(); i++) {
			HashMap<String, String> kcMap = kcList.get(i);
			sumSql.append(" ,sum(guid_kc_" + i + ")guid_kc_" + i);
			caseSql.append(" ,case when a.kcdm='" + kcMap.get("guid")
					+ "' then fs end guid_kc_" + i);
			sql.append(" ,guid_kc_" + i);
			kcfList.add("guid_kc_" + i);
		}
		sumSql.append(" from (  ");
		sql.append("  from( ");
		sql.append(sumSql);
		sql.append(caseSql);
		sql.append(" from( ");
		sql.append(" select a.xh,a.nj,b.guid kcdm,b.kcmc from view_xsjbxx a,xg_jxgl_gfjygl b where ");
		sql.append(" a.nj=(select dqnd from xtszb where rownum=1) )a ");
		sql.append(" left join xg_jxgl_gfjyfb b on a.xh=b.xh and a.kcdm=b.kcdm )a ");
		sql.append(" group by xh )a ");
		sql.append(" left join view_xsjbxx b on a.xh=b.xh)a ");
		
		User user = myForm.getUser();
		String userName = user.getUserName();
		String userDep = user.getUserDep();
		String userType = user.getUserType();
		String fdyQx = user.getFdyQx();
		String bzrQx = user.getBzrQx();
		
		StringBuilder userQuery=new StringBuilder();
		if ("true".equalsIgnoreCase(fdyQx) && "true".equalsIgnoreCase(bzrQx)) {
			userQuery.append(" and exists(select 1 from fdybjb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ");
			userQuery.append("  union select 1 from bzrbbb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ");
			userQuery.append(" ) ");
		} else if ("true".equalsIgnoreCase(fdyQx)) {
			userQuery.append(" and exists(select 1 from fdybjb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ) ");
		} else if ("true".equalsIgnoreCase(bzrQx)) {
			userQuery.append(" and exists(select 1 from fdybjb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ) ");
		} else if ("xy".equalsIgnoreCase(userType)) {
			userQuery.append(" and a.xydm='" + userDep + "' ");
		}
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString()+userQuery, mQuery.getInputList(), dao.unionArray(
				colList, kcfList.toArray(new String[] {})), myForm);
	}

	/**
	 * 获取本年度的国防教育课程列表
	 * 
	 * @return
	 */
	public List<String[]> getGfcj(CdtyGfjyForm myForm, String lx)
			throws Exception {

		DAO dao = DAO.getInstance();
		MakeQuery mQuery = new MakeQuery();
		List<HashMap<String, String>> kcList = getEverKcList(myForm);
		List<String> kcfList = new ArrayList<String>();
		String[]inputV={myForm.getNj()};
		StringBuilder sql = new StringBuilder();
		StringBuilder sumSql = new StringBuilder();
		StringBuilder caseSql = new StringBuilder();

		String[] cList = { "nj", "xydm", "zydm", "bjdm" };
		String[] cLikeList = { "xh", "xm" };

		mQuery.makeQuery(cList, cLikeList, myForm);
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
		sql.append("  select rownum r,a.* from (select b.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.zf ");
		sumSql.append(" select xh ");
		caseSql.append(" select a.xh,a.nj,a.kcdm ");
		for (int i = 0; i < kcList.size(); i++) {
			HashMap<String, String> kcMap = kcList.get(i);
			sumSql.append(" ,sum(guid_kc_" + i + ")guid_kc_" + i);
			caseSql.append(" ,case when a.kcdm='" + kcMap.get("guid")
					+ "' then fs end guid_kc_" + i);
			sql.append(" ,guid_kc_" + i);
			kcfList.add("guid_kc_" + i);
		}
		sumSql.append(" from (  ");
		sql.append("  from( ");
		sql.append(sumSql);
		sql.append(caseSql);
		sql.append(" from( ");
		sql.append(" select a.xh,a.nj,b.guid kcdm,b.kcmc from view_xsjbxx a,xg_jxgl_gfjygl b  ");
		sql.append(" )a ");
		sql.append(" left join xg_jxgl_gfjyfb b on a.xh=b.xh and a.kcdm=b.kcdm )a ");
		sql.append(" group by xh )a ");
		sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
		sql.append(" left join ");
		sql.append("  (select a.xh,sum(to_number(fs)*to_number(bl)/100)zf ");
		sql.append("  from xg_jxgl_gfjyfb a left join (select * from xg_jxgl_gfjygl where nd=? ) b on a.kcdm=b.guid ");
		sql.append("    group by xh)c on b.xh=c.xh)a ");
		kcfList.add("zf");

		List<String[]> rs = new ArrayList<String[]>();
		User user = myForm.getUser();
		String userName = user.getUserName();
		String userDep = user.getUserDep();
		String userType = user.getUserType();
		String fdyQx = user.getFdyQx();
		String bzrQx = user.getBzrQx();
		StringBuilder userQuery=new StringBuilder();
		if ("true".equalsIgnoreCase(fdyQx) && "true".equalsIgnoreCase(bzrQx)) {
			userQuery.append(" and exists(select 1 from fdybjb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ");
			userQuery.append("  union select 1 from bzrbbb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ");
			userQuery.append(" ) ");
		} else if ("true".equalsIgnoreCase(fdyQx)) {
			userQuery.append(" and exists(select 1 from fdybjb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ) ");
		} else if ("true".equalsIgnoreCase(bzrQx)) {
			userQuery.append(" and exists(select 1 from fdybjb c where c.zgh='"
					+ userName + "' and c.bjdm=a.bjdm ) ");
		} else if ("xy".equalsIgnoreCase(userType)) {
			userQuery.append(" and a.xydm='" + userDep + "' ");
		}
		if ("cx".equalsIgnoreCase(lx)) {
			rs = CommonQueryDAO.commonQuery(sql.toString(), mQuery
					.getQueryString()+userQuery,dao.unionArray(inputV,mQuery.getInputList()), dao.unionArray(
					colList, kcfList.toArray(new String[] {})), myForm);
		} else if ("tj".equalsIgnoreCase(lx)) {
			
			rs = CommonQueryDAO.commonQueryNotFy(sql.toString(), mQuery
					.getQueryString()+userQuery, dao.unionArray(inputV,mQuery.getInputList()), dao.unionArray(
					colList, kcfList.toArray(new String[] {})), myForm);
		}
		return rs;
	}
	
	/**
	 * 根据年级获取国防教育课程
	 * @param drnj
	 * @return
	 */
	public List<HashMap<String,String>>getDrzdList(String drnj){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql= new StringBuilder();
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select guid,kcmc from xg_jxgl_gfjygl where 1=1 ");
		
		if(!Base.isNull(drnj)){
			
			sql.append(" and nd =? ");
			inputV.add(drnj);
			
		}else{
			
			sql.append("  and nd=(select dqnd from xtszb) ");
		}
		
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}),new String[]{"guid","kcmc"});
	}
}

package xgxt.xsgygl.wsjc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyForm;

public class GyglWsjcDAO extends GyglTyDAO {

	/**
	 * 获得空白报表导出内容
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getExpKbbbList(GyglTyForm model,GyglWsjcModel wsjcModel)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 学院代码
		String xydm = model.getXydm();
		// 校区代码
		String xqdm = model.getXqdm();
		// 楼栋代码
		String lddm = model.getLddm();
		// 层数
		String cs = model.getCs();
		// 寝室号
		String qsh = model.getQsh();
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 公寓版本
		String edition = model.getEdition();
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select t.* ");
		sql.append(",'" + Base.currXn + "' xn ");
		sql.append(",'" + Base.currXq + "' xq ");
		sql.append(",(select xqmc from xqdzb where xqdm = '" + Base.currXq + "') xqm ");
		sql.append(",'" + Base.currNd + "' nd ");
		
		if ("new".equalsIgnoreCase(edition)) {
			sql.append(",(select distinct b.xymc from view_njxyzybj b where t.xydm = b.xydm) xymc ");
		}
		
		sql.append(",to_char(sysdate, 'yyyymmdd') nowdate");
		sql.append(" from ( select");
		
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" b.xydm,(select distinct c.xymc from view_njxyzybj c where b.xydm = c.xydm) xymc, ");
		}else{
			sql.append(" case when b.fpdx = 'xy' or b.fpdx = 'njxy' then b.bmdm ");
			sql.append(" when b.fpdx = 'njzy' then (select distinct c.xydm from view_njxyzybj c where c.zydm = b.bmdm) ");
			sql.append(" when b.fpdx = 'bj' then (select distinct c.xydm from view_njxyzybj c where c.bjdm = b.bmdm) ");
			sql.append(" else '' end xydm, ");
		}
		
		sql.append(" a.xqdm,a.xqmc,a.lddm,a.ldmc,a.cs,a.qsh ");
		sql.append(" from view_ssxx a left join ");
		
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" ssfpb b on a.ssbh = b.ssbh ");
		} else if ("new".equalsIgnoreCase(edition)) {
			sql.append(" xg_gygl_qsfpb b on a.lddm = b.lddm ");
			sql.append(" and a.cs = b.cs and a.qsh = b.qsh ");
		}
		
		sql.append(" order by xqdm, lddm, cs, qsh ");
		sql.append(" ) t where 1 = 1 ");
		sql.append(Base.isNull(xydm) ? "" : " and t.xydm = ? ");
		sql.append(Base.isNull(xqdm) ? "" : " and t.xqdm = ? ");
		sql.append(Base.isNull(lddm) ? "" : " and t.lddm = ? ");
		sql.append(Base.isNull(cs) ? "" : " and t.cs = ? ");
		sql.append(Base.isNull(qsh) ? "" : " and t.qsh = ? ");
		//公寓辅导员权限
		sql.append(getGyfdyQuery(model, "t"));

		String[] queryList = new String[] { "xydm", "xqdm", "lddm", "cs", "qsh" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		String[] colList = null;
		
		// 检查周期为按日检查
		if ("日".equalsIgnoreCase(jczq)) {
			colList = new String[] { "xn", "xq", "xqm", "nd", "xydm", "xymc",
					"xqdm", "xqmc", "lddm", "ldmc", "cs", "qsh", "nowdate" };
		} else {
			colList = new String[] { "xn", "xq", "xqm", "nd", "xydm", "xymc",
					"xqdm", "xqmc", "lddm", "ldmc", "cs", "qsh" };
		}

		ArrayList<String[]> list = commonQueryNotFy(sql.toString(), "",
				inPutList, colList, model);

		return list;
	}

	/**
	 * 公寓辅导员权限
	 * 
	 * @param userName
	 * @param bm
	 */
	public String getGyfdyQuery(GyglTyForm model, String bm) {

		// 用户名
		String userName = model.getUserName();

		StringBuilder sql = new StringBuilder();
		
		bm = StringUtils.isNotNull(bm) ? (bm + ".") : bm;
		if (isGyfdy(userName)) {
			sql.append(" and exists(select 1 from gygl_lzxxb x ");
			sql.append(" where " + bm + "xqdm = x.xqdm ");
			sql.append(" and " + bm + "lddm = x.lddm ");
			sql.append(" and x.yhm = '" + userName + "')");
		}

		return sql.toString();
	}

	/**
	 * 获得卫生分录入列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getSsldList(GyglTyForm model,GyglWsjcModel wsjcModel)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 是否打分
		String sfdf = model.getSfdf();
		// 检查周次
		String jczc = model.getJczc();
		// 检查时间
		String jcsj = model.getJcsj();
		// 公寓版本
		String edition = model.getEdition();
		
		String[] queryList = new String[] { "xydm", "xqdm", "lddm", "cs", "qsh" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		String[] colList = new String[] { "pk", "xymc", "xqmc", "ldmc", "cs",
				"qsh", "lddm" };

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*,rownum r from (select a.* ");
		
	    if ("new".equalsIgnoreCase(edition)) {
			sql.append(",(select distinct b.xymc from view_njxyzybj b where a.xydm = b.xydm) xymc ");
		}
	    
	    sql.append(" from (select ");
	    
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" b.xydm,(select distinct c.xymc from view_njxyzybj c where b.xydm = c.xydm) xymc, ");
		}else{
			sql.append(" case when b.fpdx = 'xy' or b.fpdx = 'njxy' then b.bmdm ");
			sql.append(" when b.fpdx = 'njzy' then (select distinct c.xydm from view_njxyzybj c where c.zydm = b.bmdm) ");
			sql.append(" when b.fpdx = 'bj' then (select distinct c.xydm from view_njxyzybj c where c.bjdm = b.bmdm) ");
			sql.append(" else '' end xydm, ");
		}
	
		sql.append(" a.xqdm,a.xqmc,a.lddm,a.ldmc,a.cs,a.qsh ");
		sql.append(" from view_ssxx a left join ");
		
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" ssfpb b on a.ssbh = b.ssbh ");
		} else if ("new".equalsIgnoreCase(edition)) {
			sql.append(" xg_gygl_qsfpb b on a.lddm = b.lddm ");
			sql.append(" and a.cs = b.cs and a.qsh = b.qsh ");
		}
		
		sql.append(" ) a ");
		sql.append(myQuery.getQueryString());
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "a"));
		
		//是否打分
		if (!Base.isNull(sfdf)) {
			if ("是".equalsIgnoreCase(sfdf)) {
				sql.append(" and exists (");
				sql.append(" select 1 from gygl_wsjc_wsfwhb b ");
				sql.append(" where b.jcld = a.lddm ");
				sql.append(" and b.jccs = a.cs ");
				sql.append(" and b.jcqs = a.qsh");
				sql.append("周".equalsIgnoreCase(jczq) ? " and b.jczc = '" + jczc + "'" : "");
				sql.append("日".equalsIgnoreCase(jczq) ? " and b.jcsj = '" + jcsj + "'" : "");
				sql.append("分数".equalsIgnoreCase(lrxs) ? " and b.wsffs is not null" : "");
				sql.append("等级".equalsIgnoreCase(lrxs) ? " and b.wsfdj is not null" : "");
				sql.append(" ) ");
			}else{
				sql.append(" and (exists (");
				sql.append(" select 1 from gygl_wsjc_wsfwhb b ");
				sql.append(" where b.jcld = a.lddm ");
				sql.append(" and b.jccs = a.cs ");
				sql.append(" and b.jcqs = a.qsh");
				sql.append("周".equalsIgnoreCase(jczq) ? " and b.jczc = '" + jczc + "'" : "");
				sql.append("日".equalsIgnoreCase(jczq) ? " and b.jcsj = '" + jcsj + "'" : "");
				sql.append("分数".equalsIgnoreCase(lrxs) ? " and b.wsffs is null" : "");
				sql.append("等级".equalsIgnoreCase(lrxs) ? " and b.wsfdj is null" : "");
				sql.append(" ) or ");
				sql.append(" not exists (");
				sql.append(" select 1 from gygl_wsjc_wsfwhb b ");
				sql.append(" where b.jcld = a.lddm ");
				sql.append(" and b.jccs = a.cs ");
				sql.append(" and b.jcqs = a.qsh");
				sql.append("周".equalsIgnoreCase(jczq) ? " and b.jczc = '" + jczc + "'" : "");
				sql.append("日".equalsIgnoreCase(jczq) ? " and b.jcsj = '" + jcsj + "'" : "");
				sql.append(" )) ");
			}
		}
		
		sql.append(" order by xqdm, lddm, cs, qsh ) t");

		Pages pages = model.getPages();
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(
				"select count(*) count from (" + sql.toString() + ") a ",
				inPutList, "count")));

		sql.append(" ) t where r > ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));

		ArrayList<String[]> list = commonQueryNotFy(
				"select lddm||cs||qsh pk,t.* from (" + sql.toString(), "",
				inPutList, colList, model);

		return list;

	}

	/**
	 * 获得卫生分录入列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getWsfxxList(GyglTyForm model) {

		DAO dao = DAO.getInstance();

		// 当年学年
		String xn = model.getXn();
		// 当年学期
		String xq = model.getXq();
		// 当年年度
		String nd = model.getNd();
		// 楼栋
		String lddm = model.getLddm();
		// 层数
		String cs = model.getCs();
		// 寝室号
		String qsh = model.getQsh();

		// 检查周次
		String jczc = model.getJczc();
		// 检查时间
		String jcsj = model.getJcsj();

		String[] colList = new String[] { "pk", "jcsj", "wsffs", "wsfdj",
				"bmmc", "jcbm","bz" };

		StringBuilder sql = new StringBuilder();
		sql.append(" select jcld || jccs || jcqs pk, jcsj,bz,jcbm, ");
		sql.append("(select bmmc from gywsjcbmb where jcbm = bmdm) bmmc, ");
		sql.append(" wsffs, wsfdj from gygl_wsjc_wsfwhb a ");
		sql.append(" where exists(select 1 from view_ssxx b ");
		sql.append(" where a.jcld=b.lddm and a.jccs=b.cs and a.jcqs=b.qsh ");
		sql.append(Base.isNull(lddm) ? "" : " and b.lddm = '" + lddm + "' ");
		sql.append(Base.isNull(cs) ? "" : " and b.cs = '" + cs + "' ");
		sql.append(Base.isNull(qsh) ? "" : " and b.qsh = '" + qsh + "' ");
		sql.append(")");
		sql.append(" and xn = ? and xq = ? and nd = ? ");
		sql.append(Base.isNull(jczc) ? "" : " and jczc = '" + jczc + "' ");
		sql.append(!Base.isNull(jczc) ? "" : " and jcsj = '" + jcsj + "' ");

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[] {
				xn, xq, nd }, colList);

		return list;
	}
	
	/**
	 * 获得寝室卫生分情况
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getQsWsfInfo(GyglTyForm model,
			GyglWsjcModel wsjcModel) {

		DAO dao = DAO.getInstance();
		
		// 宿舍编号 lddm+cs+qsh
		String ssbh = model.getPkValue();
		// 检查周次
		String jczc = model.getJczc();
		// 检查时间
		String jcsj = model.getJcsj();
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 公寓版本
		String edition = model.getEdition();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*, ");
		
	    if ("new".equalsIgnoreCase(edition)) {
			sql.append("(select distinct b.xymc from view_njxyzybj b where t.xydm = b.xydm) xymc ");
		}
	    
		sql.append(" from( ");
		sql.append(" select a.xqmc,a.ldmc,a.cs,a.qsh, ");
		
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" nvl((select distinct c.xymc from view_njxyzybj c ");
			sql.append(" where c.xydm = b.xydm),'未分配') xymc, ");
		}else{
			sql.append(" case when b.fpdx = 'xy' or b.fpdx = 'njxy' then b.bmdm ");
			sql.append(" when b.fpdx = 'njzy' then (select distinct c.xydm from view_njxyzybj c where c.zydm = b.bmdm) ");
			sql.append(" when b.fpdx = 'bj' then (select distinct c.xydm from view_njxyzybj c where c.bjdm = b.bmdm) ");
			sql.append(" else '' end xydm, ");
		}
		
		sql.append(" nvl((select c.bmmc from gywsjcbmb c where c.bmdm = d.jcbm), ");
		sql.append(" '未录入') jcbm,d.jczc,d.jcsj,d.wsffs, ");
		sql.append(" d.wsfdj,d.bz from (select * from view_ssxx where lddm||cs||qsh = ?) a ");
		
		sql.append(" left join  ");
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" ssfpb b on a.ssbh = b.ssbh ");
		} else if ("new".equalsIgnoreCase(edition)) {
			sql.append(" xg_gygl_qsfpb b on a.lddm = b.lddm ");
			sql.append(" and a.cs = b.cs and a.qsh = b.qsh ");
		}
		
		sql.append(" left join gygl_wsjc_wsfwhb d on d.jcld = a.lddm ");
		sql.append(" and d.jccs = a.cs and d.jcqs = a.qsh ");
		sql.append("周".equalsIgnoreCase(jczq) ? " and d.jczc = '" + jczc + "'" : "");
		sql.append("日".equalsIgnoreCase(jczq) ? " and d.jcsj = '" + jcsj + "'" : "");
		sql.append(" ) t ");
		
		String[] outputValue = new String[] { "xqmc", "ldmc", "cs", "qsh",
				"xymc", "jcbm", "jczc", "jcsj", "wsffs", "wsfdj", "bz" };
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { ssbh }, outputValue);

		return map;
	}
	
	/**
	 * 获得寝室入住情况
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getQsrzInfo(GyglTyForm model) {

		DAO dao = DAO.getInstance();

		String ssbh = model.getPkValue();

		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,xymc,zymc,bjmc,cwh ");
		sql.append(" from view_xszsxx ");
		sql.append(" where lddm||cs||qsh = ? ");
		sql.append(" order by cwh ");

		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] { ssbh }, new String[] { "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "cwh" });

		return list;
	}
	
	/**
	 * 获得卫生分结果列表(寝室)
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getQsWsjcInfoList(GyglTyForm model,GyglWsjcModel wsjcModel)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		
		String xh = model.getXh();
		String xm = model.getXm();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		
		String kszc = model.getKszc();
		String jszc = model.getJszc();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		
		String fsxx = model.getFsxx();
		String fssx = model.getFssx();
		
		String wsfdj = model.getDj();
		String zzmm = model.getZzmm();
		
		boolean flag = false;
		if (!Base.isNull(xh) || !Base.isNull(xm) || !Base.isNull(nj)
				|| !Base.isNull(xydm) || !Base.isNull(zydm)
				|| !Base.isNull(bjdm) || !Base.isNull(zzmm)) {
			flag = true;
		}
		
		
		String[] queryList = new String[] { "xn", "xq", "nd", "xqdm", "lddm",
				"cs", "qsh", "jcbm" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		String[] colList = new String[] { "pk", "xn", "xq", "nd", "xqmc",
				"ldmc", "cs", "qsh", "sj", "jcbm","wsffs","wsfdj" };
		
		ArrayList<String> inputList =  new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select lddm||cs||qsh||jcsj||jczc pk, ");
		sql.append(" xn,nd,xqmc,ldmc,cs,qsh,jczc,jcsj,wsffs,wsfdj, ");
		sql.append("周".equalsIgnoreCase(jczq) ? "'第'||jczc||'周' sj," : "jcsj sj,");
		sql.append(" (select xqmc from xqdzb where xq = xqdm) xq, ");
		sql.append(" (select bmmc from gywsjcbmb where bmdm = jcbm) jcbm ");
		sql.append(" from ( ");
		sql.append(" select a.xn,a.xq,a.nd,b.xqdm,b.xqmc,a.jcbm, ");
		sql.append(" b.lddm,b.ldmc,b.cs,b.qsh,a.jczc,a.jcsj,a.wsffs, ");
		sql.append(" a.wsfdj from gygl_wsjc_wsfwhb a, view_ssxx b ");
		sql.append(" where a.jcld = b.lddm and a.jccs = b.cs ");
		sql.append(" and a.jcqs = b.qsh and ");
		sql.append(" (a.wsffs is not null or a.wsfdj is not null) ");
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "b"));
		
		if (flag) {
			
			String[] queryListXs = new String[] { "nj", "xydm", "zydm", "bjdm",
					"zzmm" };
			String[] queryLikeListXs = new String[] {"xh","xm"};
			MakeQuery myQueryXs = new MakeQuery();
			myQueryXs.makeQuery(queryListXs, queryLikeListXs, model);	
			String[] inPutListXs = myQueryXs.getInputList();
			ArrayList<String> inputListXs =  new ArrayList<String>(Arrays.asList(inPutListXs));
			
			inputList.addAll(inputListXs);
			
			sql.append(" and exists (select 1 from view_xszsxx c ");
			sql.append(myQueryXs.queryStr());
			sql.append(" and b.lddm = c.lddm and b.cs = c.cs and b.qsh = c.qsh ");
			sql.append(") ");
		}
		sql.append(" ) ");
		sql.append(myQuery.queryStr());
		sql.append("周".equalsIgnoreCase(jczq) ? " and jczc <> '无'" : "");
		sql.append("分数".equalsIgnoreCase(lrxs) ? " and wsffs is not null ": " and wsfdj is not null ");
		sql.append(Base.isNull(kszc) ? "" : " and jczc >= '" + kszc + "' ");
		sql.append(Base.isNull(jszc) ? "" : " and jczc <= '" + jszc + "' ");
		sql.append(Base.isNull(kssj) ? "" : " and jcsj >= '" + kssj + "' ");
		sql.append(Base.isNull(jssj) ? "" : " and jcsj <= '" + jssj + "' ");
		
		if("分数".equalsIgnoreCase(lrxs)){
			sql.append(Base.isNull(fsxx) ? "" : " and wsffs >= '" + fsxx + "' ");
			sql.append(Base.isNull(fssx) ? "" : " and wsffs <= '" + fssx + "' ");
			sql.append(Base.isNull(wsfdj) ? "" : " and wsfdj = '" + wsfdj + "' ");
		}
		
		if("等级".equalsIgnoreCase(lrxs)&&!Base.isNull(wsfdj)){
			sql.append(" and wsfdj = '" + wsfdj + "' ");
		}

		inputList.addAll(Arrays.asList(inPutList));
		
		Pages pages = model.getPages();
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(
				"select count(*) count from (" + sql.toString() + ") a ",
				inputList.toArray(new String[] {}), "count")));
		
		
		
		StringBuilder actSql = new StringBuilder("select t.* from (select t.*,rownum r from (");
		actSql.append(sql);
		actSql.append(" ) t ) t where r > ");
		actSql.append(pages.getStart());
		actSql.append(" and r <= ");
		actSql.append((pages.getStart() + pages.getPageSize()));
		//System.out.println(actSql);
		ArrayList<String[]> list = dao.rsToVator(actSql.toString(), inputList
				.toArray(new String[] {}), colList);

		return list;
	}

	/**
	 * 获得卫生分结果列表(学生)
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getXsWsjcInfoList(GyglTyForm model,GyglWsjcModel wsjcModel)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		
		String xqdm = model.getXqdm();
		String lddm = model.getLddm();
		String cs = model.getCs();
		String qsh = model.getQsh();
		String jcbm = model.getJcbm();
		
		String kszc = model.getKszc();
		String jszc = model.getJszc();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		
		String fsxx = model.getFsxx();
		String fssx = model.getFssx();
		
		String wsfdj = model.getDj();
		
		
		boolean flag = false;
		if (!Base.isNull(xqdm) || !Base.isNull(lddm) || !Base.isNull(cs)
				|| !Base.isNull(qsh) || !Base.isNull(jcbm)) {
			flag = true;
		}
			
		String[] queryList = new String[] { "xn", "xq", "nd", "nj", "xydm",
				"zydm", "bjdm", "zzmm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		String[] colList = new String[] { "pk", "xh", "xm", "xn", "xq", "nd",
				"xqmc", "ldmc", "cs", "qsh", "sj", "jcbm", "wsffs", "wsfdj" };
		
		ArrayList<String> inputList =  new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xh||jcsj||jczc pk, ");
		sql.append(" xh,xm,xn,nd,xqmc,ldmc,cs,qsh,jczc,jcsj,wsffs,wsfdj, ");
		sql.append("周".equalsIgnoreCase(jczq) ? "'第'||jczc||'周' sj," : "jcsj sj,");
		sql.append(" (select xqmc from xqdzb where xq = xqdm) xq, ");
		sql.append(" (select bmmc from gywsjcbmb where bmdm = jcbm) jcbm ");
		sql.append(" from (select b.xn,b.xq,b.nd,a.xqdm,a.xqmc, ");
		sql.append(" a.lddm,a.ldmc,a.cs,a.qsh,a.xh,a.xm,a.nj,a.xymc,a.xydm, ");
		sql.append(" a.zymc,a.zydm,a.bjmc,a.bjdm,b.jczc,b.jcsj,b.jcbm,a.zzmm, ");
		sql.append(" b.wsffs,b.wsfdj from gygl_wsjc_wsfwhb b ");
		sql.append(" left join view_xszsxx a on a.lddm = b.jcld ");
		sql.append(" and a.cs = b.jccs and a.qsh = b.jcqs) a ");
		sql.append(myQuery.queryStr());
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "a"));
		sql.append(" and xh is not null ");
		sql.append("周".equalsIgnoreCase(jczq) ? " and jczc <> '无'" : "");
		sql.append("分数".equalsIgnoreCase(lrxs) ? " and wsffs is not null ": " and wsfdj is not null ");
		sql.append(Base.isNull(kszc) ? "" : " and jczc >= '" + kszc + "' ");
		sql.append(Base.isNull(jszc) ? "" : " and jczc <= '" + jszc + "' ");
		sql.append(Base.isNull(kssj) ? "" : " and jcsj >= '" + kssj + "' ");
		sql.append(Base.isNull(jssj) ? "" : " and jcsj <= '" + jssj + "' ");
		if (flag) {
			
			String[] queryListQs = new String[] { "xqdm", "lddm", "cs", "qsh",
					"jcbm" };		
			String[] queryLikeListQs = new String[] {};
			
			MakeQuery myQueryQs = new MakeQuery();
			myQueryQs.makeQuery(queryListQs, queryLikeListQs, model);	
			String[] inPutListQs = myQueryQs.getInputList();
			ArrayList<String> inputListXs =  new ArrayList<String>(Arrays.asList(inPutListQs));
			
			inputList.addAll(inputListXs);
			
			sql.append(" and exists (select 1 from view_ssxx c ");
			sql.append(myQueryQs.queryStr());
			sql.append(" and a.lddm = c.lddm and a.cs = c.cs and a.qsh = c.qsh ");
			sql.append(") ");
		}
		
		if("分数".equalsIgnoreCase(lrxs)){
			sql.append(Base.isNull(fsxx) ? "" : " and wsffs >= '" + fsxx + "' ");
			sql.append(Base.isNull(fssx) ? "" : " and wsffs <= '" + fssx + "' ");
			sql.append(Base.isNull(wsfdj) ? "" :" and wsfdj = '" + wsfdj + "' ");
		}
		
		if("等级".equalsIgnoreCase(lrxs)&&!Base.isNull(wsfdj)){
			sql.append(Base.isNull(wsfdj) ? "" :" and wsfdj = '" + wsfdj + "' ");
		}

		inputList.addAll(Arrays.asList(inPutList));
	
		Pages pages = model.getPages();
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(
				"select count(*) count from (" + sql.toString() + ")",
				inputList.toArray(new String[] {}), "count")));
		
		
	
		sql.append("order by jcsj");
		
		StringBuilder actSql = new StringBuilder("select t.* from (select t.*,rownum r from (");
		actSql.append(sql);
		actSql.append(" ) t ) t where r > ");
		actSql.append(pages.getStart());
		actSql.append(" and r <= ");
		actSql.append((pages.getStart() + pages.getPageSize()));
		
		ArrayList<String[]> list = dao.rsToVator(actSql.toString(), inputList
				.toArray(new String[] {}), colList);

		return list;
	}
	
	/**
	 * 获得寝室卫生分结果
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getQsWsfjg(GyglTyForm model,
			GyglWsjcModel wsjcModel) {

		DAO dao = DAO.getInstance();

		// 主键 lddm+cs+qsh+jcsj+jczc
		String pk = model.getPkValue();
		// 公寓版本
		String edition = model.getEdition();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.* ");
		if ("new".equalsIgnoreCase(edition)) {
			sql.append(",(select distinct b.xymc from view_njxyzybj b where a.xydm = b.xydm) xymc ");
		}
		sql.append(" from ( ");
		sql.append(" select a.xn,a.xq,a.nd,a.xqmc,a.lddm, ");
		sql.append(" a.ldmc,a.cs,a.qsh,a.jczc,a.wsffs fssx, ");
		sql.append(" a.jcsj,a.wsffs,a.wsfdj,a.bz, ");
		
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" (select distinct c.xymc from view_njxyzybj c where b.xydm = c.xydm) xymc, ");
		}else{
			sql.append(" case when b.fpdx = 'xy' or b.fpdx = 'njxy' then b.bmdm ");
			sql.append(" when b.fpdx = 'njzy' then (select distinct c.xydm from view_njxyzybj c where c.zydm = b.bmdm) ");
			sql.append(" when b.fpdx = 'bj' then (select distinct c.xydm from view_njxyzybj c where c.bjdm = b.bmdm) ");
			sql.append(" else '' end xydm, ");
		}
		
		sql.append(" (select distinct c.bmmc from gywsjcbmb c where a.jcbm = c.bmdm) jcbm ");
		sql.append(" from (select a.xn, a.xq, a.nd, b.xqdm, ");
		sql.append(" b.xqmc, b.lddm, b.ldmc, b.cs, b.qsh, ");
		sql.append(" b.ssbh, a.jcbm, a.jczc, a.jcsj, a.wsffs, ");
		sql.append(" a.wsfdj, a.bz from gygl_wsjc_wsfwhb a, view_ssxx b ");
		sql.append(" where a.jcld = b.lddm and a.jccs = b.cs ");
		sql.append(" and b.lddm||b.cs||b.qsh||a.jcsj||a.jczc = ? ");
		sql.append(" and a.jcqs = b.qsh) a left join ");
		
		if ("old".equalsIgnoreCase(edition)) {
			sql.append(" ssfpb b on a.ssbh = b.ssbh ");
		} else if ("new".equalsIgnoreCase(edition)) {
			sql.append(" xg_gygl_qsfpb b on a.lddm = b.lddm ");
			sql.append(" and a.cs = b.cs and a.qsh = b.qsh ");
		}
		sql.append(" ) a");
		
		String[] outputValue = new String[] { "xqmc", "lddm", "ldmc", "cs",
				"qsh", "xymc", "jcbm", "jczc", "jcsj", "wsffs", "wsfdj", "bz",
				"fssx" };
		
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { pk }, outputValue);

		return map;
	}
	
	/**
	 * 获得学生卫生分结果
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXsWsfjg(GyglTyForm model,
			GyglWsjcModel wsjcModel) {

		DAO dao = DAO.getInstance();
		
		// 主键 xh||jcsj||jczc
		String pk = model.getPkValue();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,");
		sql.append(" (select xqmc from xqdzb where xq = xqdm) xqm, ");
		sql.append(" (select bmmc from gywsjcbmb where jcbm = bmdm) bmmc ");
		sql.append(" from ( ");
		sql.append(" select a.*,b.xn,b.xq,b.nd,b.jcbm,b.jczc, ");
		sql.append(" b.jcsj,b.wsffs,b.wsfdj,b.bz jcbz from (select a.* ");
		sql.append(" from view_xszsxx a where exists (select 1 ");
		sql.append(" from gygl_wsjc_wsfwhb b where a.lddm = b.jcld ");
		sql.append(" and a.cs = b.jccs and a.qsh = b.jcqs)) a ");
		sql.append(" left join gygl_wsjc_wsfwhb b on a.lddm = b.jcld ");
		sql.append(" and a.cs = b.jccs and a.qsh = b.jcqs ");
		sql.append(" ) t where xh||jcsj||jczc = ? "); 
		
		String[] outputValue = new String[] { "xn", "xqm", "nd", "xh", "xm",
				"xqmc", "ldmc", "cs", "qsh", "nj", "xymc", "zymc", "bjmc",
				"xb", "jczc", "wsffs", "wsfdj", "jcbz","zzmmmc","bmmc" };
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { pk }, outputValue);

		return map;
	}

	/**
	 * 保存检查部门
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJcbm(GyglTyForm model, SaveForm saveForm)
			throws Exception {

		DAO dao = DAO.getInstance();

		// 检查部门
		String[] jcbm = model.getArr_jcbm();
		// 备注
		String[] bz = model.getJcbz();

		String pk = saveForm.getPk();
		String[] pkValue = saveForm.getPkValue();

		boolean flag = false;

		if (jcbm != null && jcbm.length > 0) {

			String[] sql = new String[jcbm.length];

			for (int i = 0; i < jcbm.length; i++) {
				sql[i] = "update gygl_wsjc_wsfwhb set jcbm = '" + jcbm[i]
						+ "',bz = '" + bz[i] + "' where " + pk + "='"
						+ pkValue[i] + "'";
			}

			flag = dao.saveArrDate(sql);
		}

		return flag;
	}

	/**
	 * 获得寝室总数
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getQszsTjList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 统计范围
		String tjfw = model.getTjfw();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		
		String[] queryList = new String[] { "xqdm", "lddm", "cs", "qsh", "nj",
				"xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		
		StringBuilder sql = new StringBuilder();
		StringBuilder groupSql = new StringBuilder(" group by ");
		String pk = "";
		String tj = "";
		String gro = "";
		String orderBy = " order by ";
		
		ArrayList<String> outputValue = new ArrayList<String>();
		outputValue.add("pk");
		
		sql.append(" select count(1) zqs ");

		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）
			pk = "nj";
			tj = " b.nj ";
			sql.append(" ,nj ");
			outputValue.add("nj");
			groupSql.append(" nj ");
			orderBy+="nj";
		}else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）
			pk = "xydm";
			tj = " b.xydm,b.xymc ";
			sql.append(" ,xymc ");
			outputValue.add("xymc");
			groupSql.append(" xymc,xydm ");
			orderBy+="xydm";
		}else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）
			pk = "zydm";
			tj = " b.zydm,b.zymc ";
			sql.append(" ,zymc ");
			outputValue.add("zymc");
			groupSql.append(" zymc,zydm ");
			orderBy+="zydm";
		}else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）
			pk = "bjdm";
			tj = " b.bjdm,b.bjmc ";
			sql.append(" ,bjmc ");
			outputValue.add("bjmc");
			groupSql.append(" bjmc,bjdm ");
			orderBy+="bjdm";
		}

		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			pk += "||nj";
			tj += " ,b.nj ";
			sql.append(" ,nj ");
			outputValue.add("nj");
			groupSql.append(" ,nj ");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			pk += "||xydm";
			tj += " ,b.xydm,b.xymc ";
			gro += " ,xydm ";
			sql.append(" ,xymc ");
			outputValue.add("xymc");
			groupSql.append(" ,xymc,xydm ");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			pk += "||zydm";
			tj += " ,b.zydm,b.zymc ";
			gro += " ,zymc ";
			sql.append(" ,zymc ");
			outputValue.add("zymc");
			groupSql.append(" ,zymc,zydm ");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			pk += "||bjdm";
			tj += " ,b.bjdm,b.bjmc ";
			gro += " ,bjmc ";
			sql.append(" ,bjmc ");
			outputValue.add("bjmc");
			groupSql.append(" ,bjmc,bjdm ");
		}
		
		groupSql.append(gro);
		
		sql.append(", " + pk + " pk ");
		sql.append(" from ( ");

		sql.append(" select distinct b.xqdm,b.lddm,b.cs,b.qsh, ");
		sql.append(tj);
		sql.append(" from view_xszsxx b ");

		sql.append(" ) a ");
		sql.append(myQuery.queryStr());
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "a"));
		sql.append(groupSql);

		sql.append(orderBy);
		
		outputValue.add("zqs");
		
		
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inPutList,
				outputValue.toArray(new String[] {}));

		return list;
	}

	/**
	 * 获得寝室排名
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getQspmTjList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 统计范围
		String tjfw = model.getTjfw();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 开始周次
		String kszc = model.getKszc();
		// 检查周次
		String jszc = model.getJszc();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 卫生分等级
		String dj = model.getDj();
		// 等级列表
		List<HashMap<String, String>> wsdjList = wsjcModel.getWsdjList();
		
		String pk = "";
		
		String[] queryList = new String[] { "xn", "xq", "nd", "xqdm", "lddm",
				"cs", "qsh", "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		
		StringBuilder sql = new StringBuilder();
		
		ArrayList<String> outputValue = new ArrayList<String>();
		outputValue.add("pk");
		// outputValue.add("xn");
		// outputValue.add("xq");
		// outputValue.add("nd");
		
		StringBuilder groupSql = new StringBuilder(" group by xn,xq,nd ");
		
		sql.append(" select t.* ");

		if (wsdjList != null && wsdjList.size() > 0) {
			for (int i = 0; i < wsdjList.size(); i++) {
				sql.append(",(rank() over(order by to_number(sl" + i + ") desc nulls last)) sl" + i + "pm ");
				
				String wsdj = wsdjList.get(i).get("wsfdj");
				
				if (Base.isNull(dj)) {
					outputValue.add("sl" + i);
					outputValue.add("sl" + i + "pm");
				}else if(wsdj.equalsIgnoreCase(dj)){
					outputValue.add("sl" + i);
					outputValue.add("sl" + i + "pm");
				}
			}
		}

		sql.append(" from (select xn,xq,nd ");
		
		String tj = "";
		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）
			pk = "nj";
			tj = " nj ";
			sql.append(" ,nj ");
			//outputValue.add("nj");
			groupSql.append(" ,nj ");
		}else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）
			pk = "xydm";
			tj = " xydm ";
			sql.append(" ,xydm ");
			//outputValue.add("xydm");
			groupSql.append(" ,xydm ");
		}else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）
			pk = "zydm";
			tj = " zydm ";
			sql.append(" ,zydm ");
			//outputValue.add("zydm");
			groupSql.append(" ,zydm ");
		}else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）
			pk = "bjdm";
			tj = " bjdm ";
			sql.append(" ,bjdm ");
			//outputValue.add("bjdm");
			groupSql.append(" ,bjdm ");
		}

		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			pk += "||nj";
			tj += " ,nj ";
			sql.append(" ,nj ");
			//outputValue.add("nj");
			groupSql.append(" ,nj ");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			pk += "||xydm";
			tj += " ,xydm ";
			sql.append(" ,xydm ");
			//outputValue.add("xydm");
			groupSql.append(" ,xydm ");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			pk += "||zydm";
			tj += " ,zydm ";
			sql.append(" ,zydm ");
			//outputValue.add("zydm");
			groupSql.append(" ,zydm ");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			pk += "||bjdm";
			tj += " ,bjdm ";
			sql.append(" ,bjdm ");
			//outputValue.add("bjdm");
			groupSql.append(" ,bjdm ");
		}
		
		sql.append("," + pk + " pk ");
//		
//		if (wsdjList != null && wsdjList.size() > 0) {
//			for (int i = 0; i < wsdjList.size(); i++) {
//				sql.append(",(rank() over(order by to_number(sl" + i + ") desc nulls last)) sl" + i + "pm ");
//			}
//		}
//		
//		sql.append(" from (select xqdm,lddm,cs,qsh,xydm,nj,zydm, ");
//		sql.append(" bjdm,xn,xq,nd ");
		
		if (wsdjList != null && wsdjList.size() > 0) {
			for (int i = 0; i < wsdjList.size(); i++) {
				sql.append(",sum(sl" + i + ") sl" + i);
			}
		}
				
		sql.append(" from (select t.* ");
		if (wsdjList != null && wsdjList.size() > 0) {
			for (int i = 0; i < wsdjList.size(); i++) {
				String wsdj = wsdjList.get(i).get("wsfdj");
				sql.append(" ,case when t.wsfdj = '" + wsdj + "' then 1 else 0 end sl" + i);
			}
		}
			
		sql.append(" from (select "+tj+",xn,xq,nd,wsfdj,jczc,jcsj, ");
		sql.append(" xqdm,lddm,cs,qsh ");
		sql.append(" from ( ");
		
		sql.append(" select distinct xqdm,lddm,cs,qsh, ");
		sql.append(tj);
		sql.append(" ,wsffs,wsfdj,jczc,jcsj,xn,xq,nd ");
		sql.append(" from ( ");
		
		sql.append(" select b.xqdm,b.lddm,b.cs,b.qsh, ");
		sql.append(" b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc");
		sql.append(" ,a.wsffs,a.wsfdj,a.jczc,a.jcsj,a.xn,a.xq,a.nd ");
		
		sql.append(" from gygl_wsjc_wsfwhb a ");
		sql.append(" left join view_xszsxx b on a.jcld = b.lddm ");
		sql.append(" and a.jccs = b.cs and a.jcqs = b.qsh ");
		sql.append(" ) ");
		sql.append(myQuery.queryStr());
		sql.append(" ) t where "+pk+" is not null ");
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "t"));
		
		sql.append(") t where 1 = 1 ");
		
		sql.append(Base.isNull(kszc) ? "" : " and jczc >= '" + kszc + "' ");
		sql.append(Base.isNull(jszc) ? "" : " and jczc <= '" + jszc + "' ");
		
		sql.append(Base.isNull(kssj) ? "" : " and jcsj >= '" + kssj + "' ");
		sql.append(Base.isNull(jssj) ? "" : " and jcsj <= '" + jssj + "' ");
		
		sql.append(Base.isNull(dj) ? "" : "and wsfdj = '" + dj + "'");
		sql.append(" )  ");
		sql.append(groupSql);
		sql.append(" ) t ");

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inPutList,
				outputValue.toArray(new String[] {}));

		return list;
	}
	
	/**
	 * 获得人数总数
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXszsTjList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 统计范围
		String tjfw = model.getTjfw();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		
		StringBuilder sql = new StringBuilder();
		StringBuilder groupSql = new StringBuilder(" group by ");
		String pk = "";
		
		ArrayList<String> outputValue = new ArrayList<String>();
		outputValue.add("pk");
		
		sql.append(" select count(1) zrs ");

		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）
			pk = "nj";
			sql.append(" ,nj ");
			outputValue.add("nj");
			groupSql.append(" nj ");
		} else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）
			pk = "xydm";
			sql.append(" ,xymc ");
			outputValue.add("xymc");
			groupSql.append(" xymc,xydm ");
		} else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）
			pk = "zydm";
			sql.append(" ,zymc ");
			outputValue.add("zymc");
			groupSql.append(" zymc,zydm ");
		} else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）
			pk = "bjdm";
			sql.append(" ,bjmc ");
			outputValue.add("bjmc");
			groupSql.append(" bjmc,bjdm ");
		}
		
		sql.append("," + pk + " pk");
		sql.append(" from ( ");

		sql.append(" select c.xh,c.xm,b.xqdm,b.lddm,b.cs, ");
		sql.append(" b.qsh,c.xydm,c.nj,c.zydm,c.bjdm,a.wsffs, ");
		sql.append(" a.wsfdj,a.jczc,a.jcsj,a.xn,a.xq,a.nd, ");
		sql.append(" c.xymc,c.zymc,c.bjmc from view_xsjbxx c ");
		sql.append(" left join view_xszsxx b on b.xh = c.xh ");
		sql.append(" left join gygl_wsjc_wsfwhb a on a.jcld = b.lddm ");
		sql.append(" and a.jccs = b.cs and a.jcqs = b.qsh ");

		sql.append(" ) a ");
		sql.append(myQuery.queryStr());
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "a"));
		sql.append(groupSql);

		outputValue.add("zrs");
			
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inPutList,
				outputValue.toArray(new String[] {}));

		return list;
	}

	/**
	 * 获得人数排名
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXspmTjList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 统计范围
		String tjfw = model.getTjfw();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 开始周次
		String kszc = model.getKszc();
		// 检查周次
		String jszc = model.getJszc();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 卫生分等级
		String dj = model.getDj();
		// 等级列表
		List<HashMap<String, String>> wsdjList = wsjcModel.getWsdjList();
		
		String pk = "";
		
		String[] queryList = new String[] { "xn", "xq", "nd", "xqdm", "lddm",
				"cs", "qsh", "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		
		StringBuilder sql = new StringBuilder();
		
		ArrayList<String> outputValue = new ArrayList<String>();
		outputValue.add("pk");
		
		StringBuilder groupSql = new StringBuilder(" group by xn,xq,nd ");
		
		sql.append(" select t.* ");

		if (wsdjList != null && wsdjList.size() > 0) {
			for (int i = 0; i < wsdjList.size(); i++) {
				sql.append(",(rank() over(order by to_number(sl" + i + ") desc nulls last)) sl" + i + "pm ");
				
				String wsdj = wsdjList.get(i).get("wsfdj");
				
				if (Base.isNull(dj)) {
					outputValue.add("sl" + i);
					outputValue.add("sl" + i + "pm");
				}else if(wsdj.equalsIgnoreCase(dj)){
					outputValue.add("sl" + i);
					outputValue.add("sl" + i + "pm");
				}
			}
		}

		sql.append(" from (select xn,xq,nd ");
		
		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）
			pk = "nj";
			sql.append(" ,nj ");
			//outputValue.add("nj");
			groupSql.append(" ,nj ");
		}else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）
			pk = "xydm";
			sql.append(" ,xydm ");
			//outputValue.add("xydm");
			groupSql.append(" ,xydm ");
		}else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）
			pk = "zydm";
			sql.append(" ,zydm ");
			//outputValue.add("zydm");
			groupSql.append(" ,zydm ");
		}else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）
			pk = "bjdm";
			sql.append(" ,bjdm ");
			//outputValue.add("bjdm");
			groupSql.append(" ,bjdm ");
		}

		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			pk += "||nj";
			sql.append(" ,nj ");
			//outputValue.add("nj");
			groupSql.append(" ,nj ");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			pk += "||xydm";
			sql.append(" ,xydm ");
			//outputValue.add("xydm");
			groupSql.append(" ,xydm ");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			pk += "||zydm";
			sql.append(" ,zydm ");
			//outputValue.add("zydm");
			groupSql.append(" ,zydm ");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			pk += "||bjdm";
			sql.append(" ,bjdm ");
			//outputValue.add("bjdm");
			groupSql.append(" ,bjdm ");
		}
		
		sql.append("," + pk + " pk ");

		if (wsdjList != null && wsdjList.size() > 0) {
			for (int i = 0; i < wsdjList.size(); i++) {
				sql.append(",sum(sl" + i + ") sl" + i);
			}
		}
				
		sql.append(" from (select t.* ");
		if (wsdjList != null && wsdjList.size() > 0) {
			for (int i = 0; i < wsdjList.size(); i++) {
				String wsdj = wsdjList.get(i).get("wsfdj");
				sql.append(" ,case when t.wsfdj = '" + wsdj + "' then 1 else 0 end sl" + i);
			}
		}

		sql.append(" from ( ");
		sql.append(" select c.xh,c.xm,b.xqdm,b.lddm,b.cs, ");
		sql.append(" b.qsh,c.xydm,c.nj,c.zydm,c.bjdm,a.wsffs, ");
		sql.append(" a.wsfdj,a.jczc,a.jcsj,a.xn,a.xq,a.nd, ");
		sql.append(" c.xymc,c.zymc,c.bjmc from view_xsjbxx c ");
		sql.append(" left join view_xszsxx b on b.xh = c.xh ");
		sql.append(" left join gygl_wsjc_wsfwhb a on a.jcld = b.lddm ");
		sql.append(" and a.jccs = b.cs and a.jcqs = b.qsh ");
		sql.append(" ) t ");
		sql.append(myQuery.queryStr());
		
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "t"));
		
		sql.append(" and jcsj is not null ");
		sql.append(Base.isNull(kszc) ? "" : " and jczc >= '" + kszc + "' ");
		sql.append(Base.isNull(jszc) ? "" : " and jczc <= '" + jszc + "' ");
		
		sql.append(Base.isNull(kssj) ? "" : " and jcsj >= '" + kssj + "' ");
		sql.append(Base.isNull(jssj) ? "" : " and jcsj <= '" + jssj + "' ");
		
		sql.append(Base.isNull(dj) ? "" : "and wsfdj = '" + dj + "'");
		sql.append(" )  ");
		sql.append(groupSql);
		sql.append(" ) t ");

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inPutList,
				outputValue.toArray(new String[] {}));

		return list;
	}

	/**
	 * 获得卫生分导出列表(寝室)
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getQsWsjcExpList(GyglTyForm model,GyglWsjcModel wsjcModel)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		
		String xh = model.getXh();
		String xm = model.getXm();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		
		String kszc = model.getKszc();
		String jszc = model.getJszc();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		
		String fsxx = model.getFsxx();
		String fssx = model.getFssx();
		
		String wsfdj = model.getDj();
		String zzmm = model.getZzmm();
		
		boolean flag = false;
		if (!Base.isNull(xh) || !Base.isNull(xm) || !Base.isNull(nj)
				|| !Base.isNull(xydm) || !Base.isNull(zydm)
				|| !Base.isNull(bjdm) || !Base.isNull(zzmm)) {
			flag = true;
		}
		
		
		String[] queryList = new String[] { "xn", "xq", "nd", "xqdm", "lddm",
				"cs", "qsh", "jcbm" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		String[] colList = new String[] {"xn", "xq", "xqm", "nd",
				"xqdm", "xqmc", "lddm", "ldmc", "cs", "qsh", "jczc", "jcsj",
				"sj", "jcbm", "bmmc", "wsffs", "wsfdj" };
		
		ArrayList<String> inputList =  new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select lddm||cs||qsh||jcsj||jczc pk, ");
		sql.append(" xn,nd,xqdm,xqmc,lddm,ldmc,cs,qsh,jczc,jcsj,wsffs,wsfdj, ");
		sql.append("周".equalsIgnoreCase(jczq) ? "'第'||jczc||'周' sj," : "jcsj sj,");
		sql.append(" (select xqmc from xqdzb where xq = xqdm) xqm,xq, ");
		sql.append(" (select bmmc from gywsjcbmb where bmdm = jcbm) bmmc,jcbm ");
		sql.append(" from ( ");
		sql.append(" select a.xn,a.xq,a.nd,b.xqdm,b.xqmc,a.jcbm, ");
		sql.append(" b.lddm,b.ldmc,b.cs,b.qsh,a.jczc,a.jcsj,a.wsffs, ");
		sql.append(" a.wsfdj from gygl_wsjc_wsfwhb a, view_ssxx b ");
		sql.append(" where a.jcld = b.lddm and a.jccs = b.cs ");
		sql.append(" and a.jcqs = b.qsh and ");
		sql.append(" (a.wsffs is not null or a.wsfdj is not null) ");
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "b"));
		
		if (flag) {
			
			String[] queryListXs = new String[] { "nj", "xydm", "zydm", "bjdm",
					"zzmm" };
			String[] queryLikeListXs = new String[] {"xh","xm"};
			MakeQuery myQueryXs = new MakeQuery();
			myQueryXs.makeQuery(queryListXs, queryLikeListXs, model);	
			String[] inPutListXs = myQueryXs.getInputList();
			ArrayList<String> inputListXs =  new ArrayList<String>(Arrays.asList(inPutListXs));
			
			inputList.addAll(inputListXs);
			
			sql.append(" and exists (select 1 from view_xszsxx c ");
			sql.append(myQueryXs.queryStr());
			sql.append(" and b.lddm = c.lddm and b.cs = c.cs and b.qsh = c.qsh ");
			sql.append(") ");
		}
		sql.append(" ) ");
		sql.append(myQuery.queryStr());
		sql.append("周".equalsIgnoreCase(jczq) ? " and jczc <> '无'" : "");
		sql.append("分数".equalsIgnoreCase(lrxs) ? " and wsffs is not null ": " and wsfdj is not null ");
		sql.append(Base.isNull(kszc) ? "" : " and jczc >= '" + kszc + "' ");
		sql.append(Base.isNull(jszc) ? "" : " and jczc <= '" + jszc + "' ");
		sql.append(Base.isNull(kssj) ? "" : " and jcsj >= '" + kssj + "' ");
		sql.append(Base.isNull(jssj) ? "" : " and jcsj <= '" + jssj + "' ");
		
		if("分数".equalsIgnoreCase(lrxs)){
			sql.append(Base.isNull(fsxx) ? "" : " and wsffs >= '" + fsxx + "' ");
			sql.append(Base.isNull(fssx) ? "" : " and wsffs <= '" + fssx + "' ");
		}
		
		if("等级".equalsIgnoreCase(lrxs)){
			sql.append(" and wsfdj = '" + wsfdj + "' ");
		}

		inputList.addAll(Arrays.asList(inPutList));
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputList
				.toArray(new String[] {}), colList);

		return list;
	}
	
	/**
	 * 获得卫生分导出列表(学生)
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getXsWsjcExpList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = DAO.getInstance();

		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		
		String xqdm = model.getXqdm();
		String lddm = model.getLddm();
		String cs = model.getCs();
		String qsh = model.getQsh();
		String jcbm = model.getJcbm();
		
		String kszc = model.getKszc();
		String jszc = model.getJszc();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		
		String fsxx = model.getFsxx();
		String fssx = model.getFssx();
		
		String wsfdj = model.getDj();
		
		
		boolean flag = false;
		if (!Base.isNull(xqdm) || !Base.isNull(lddm) || !Base.isNull(cs)
				|| !Base.isNull(qsh) || !Base.isNull(jcbm)) {
			flag = true;
		}
			
		String[] queryList = new String[] { "xn", "xq", "nd", "nj", "xydm",
				"zydm", "bjdm", "zzmm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inPutList = myQuery.getInputList();
		String[] colList = new String[] { "xh", "xm", "xn", "xq", "xqm",
				"nd", "xqdm", "xqmc", "lddm", "ldmc", "cs", "qsh", "jczc",
				"jcsj", "sj", "jcbm", "bmmc", "wsffs", "wsfdj" };
		
		ArrayList<String> inputList =  new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xh||jcsj||jczc pk, ");
		sql.append(" xh,xm,xn,nd,xqdm,xqmc,lddm,ldmc,cs,qsh,jczc,jcsj,wsffs,wsfdj, ");
		sql.append("周".equalsIgnoreCase(jczq) ? "'第'||jczc||'周' sj," : "jcsj sj,");
		sql.append(" (select xqmc from xqdzb where xq = xqdm) xqm,xq, ");
		sql.append(" (select bmmc from gywsjcbmb where bmdm = jcbm) bmmc, jcbm ");
		sql.append(" from (select b.xn,b.xq,b.nd,a.xqdm,a.xqmc, ");
		sql.append(" a.lddm,a.ldmc,a.cs,a.qsh,a.xh,a.xm,a.nj,a.xymc,a.xydm, ");
		sql.append(" a.zymc,a.zydm,a.bjmc,a.bjdm,b.jczc,b.jcsj,b.jcbm, ");
		sql.append(" b.wsffs,b.wsfdj from gygl_wsjc_wsfwhb b ");
		sql.append(" left join view_xszsxx a on a.lddm = b.jcld ");
		sql.append(" and a.cs = b.jccs and a.qsh = b.jcqs) a ");
		sql.append(myQuery.queryStr());
		// 公寓辅导员权限
		sql.append(getGyfdyQuery(model, "a"));
		sql.append(" and xh is not null ");
		sql.append("周".equalsIgnoreCase(jczq) ? " and jczc <> '无'" : "");
		sql.append("分数".equalsIgnoreCase(lrxs) ? " and wsffs is not null ": " and wsfdj is not null ");
		sql.append(Base.isNull(kszc) ? "" : " and jczc >= '" + kszc + "' ");
		sql.append(Base.isNull(jszc) ? "" : " and jczc <= '" + jszc + "' ");
		sql.append(Base.isNull(kssj) ? "" : " and jcsj >= '" + kssj + "' ");
		sql.append(Base.isNull(jssj) ? "" : " and jcsj <= '" + jssj + "' ");
		
		if (flag) {
			
			String[] queryListQs = new String[] { "xqdm", "lddm", "cs", "qsh",
					"jcbm" };		
			String[] queryLikeListQs = new String[] {};
			
			MakeQuery myQueryQs = new MakeQuery();
			myQueryQs.makeQuery(queryListQs, queryLikeListQs, model);	
			String[] inPutListQs = myQueryQs.getInputList();
			ArrayList<String> inputListXs =  new ArrayList<String>(Arrays.asList(inPutListQs));
			
			inputList.addAll(inputListXs);
			
			sql.append(" and exists (select 1 from view_ssxx c ");
			sql.append(myQueryQs.queryStr());
			sql.append(" and a.lddm = c.lddm and a.cs = c.cs and a.qsh = c.qsh ");
			sql.append(") ");
		}
		
		if("分数".equalsIgnoreCase(lrxs)){
			sql.append(Base.isNull(fsxx) ? "" : " and wsffs >= '" + fsxx + "' ");
			sql.append(Base.isNull(fssx) ? "" : " and wsffs <= '" + fssx + "' ");
		}
		
		if("等级".equalsIgnoreCase(lrxs)){
			sql.append(" and wsfdj = '" + wsfdj + "' ");
		}

		inputList.addAll(Arrays.asList(inPutList));
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputList
				.toArray(new String[] {}), colList);

		return list;
	}

	/**
	 * 获得卫生分表中分数非空但是等级为空的主键
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsfbPk(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select jcld||jccs||jcqs||jcsj||jczc pk,wsffs  ");
		sql.append(" from gygl_wsjc_wsfwhb ");
		sql.append(" where wsffs is not null ");
		sql.append(" and wsfdj is null ");

		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] {}, new String[] { "pk", "wsffs" });

		return list;
	}
	
	/**
	 * 获得学生卫生检查分项目信息
	 * @param xmxz
	 * @return
	 */
	public List<HashMap<String, String>> getWsjcInfo(String xmxz){
		String tableName = "XG_GYGL_WSJCDMWHB";
		String query = " where xmxz=?";
		return CommonQueryDAO.commonQueryforList(tableName, query, new String[]{xmxz}, new String[]{"xmdm","xmmc"}, "");
	}
}


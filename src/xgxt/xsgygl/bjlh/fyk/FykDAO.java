package xgxt.xsgygl.bjlh.fyk;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
	
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;

public class FykDAO extends BjlhGyglDAO{

	DAO dao = DAO.getInstance();

	private ArrayList<String> values = new ArrayList<String>();
	
	//单个学院房间总数，床位总数，空床位总数，行李床位总数
	StringBuffer QUERY_XYFJZS = new StringBuffer("select xydm,(case when xydm='0202' then '团委' when xydm='0405' then '体育教育学部' when xydm='0110' ")
	                                   .append("then '科研处' when xydm='0117' then '成人教育处' else (select xymc from view_njxyzybj b ")
	                                   .append("where a.xydm=b.xydm and rownum=1)  end) xymc,count(*) fjzs, sum(cws) cwzs,sum(kcws) ")
	                                   .append("kcwzs,sum(xlcws) xlcws from view_bjlh_tjjbxx a ");
//	 StringBuffer QUERY_XYFSZS = new StringBuffer("select xydm,(case when xydm='0202' then '团委' when xydm='0405' then '体育教育学部' when xydm='0110' then '科研处' when xydm='0117' then '成人教育处' else ((select xymc from view_xsjbxx b where a.xydm=b.xydm and rownum=1))  end) xymc,sum(fjzs) fjzs,sum(cwzs) cwzs,sum(xlcws) xlcws,sum(kcwzs) kcwzs from (select xydm,count(*) fjzs, sum(cws) cwzs,sum(xlcws) xlcws,sum(cws-yzrs) kcwzs")
//									           .append(" from (select a.xydm, a.ssbh, a.cws,(select xqdm from sslddmb d where b.lddm=d.lddm) xqdm, b.lddm, b.cs, b.qsh,")
//									           .append("(select count(*) from (select lddm,cs,qsh,cwh,(select xydm from ssfpb b where a.lddm||a.cs||a.qsh=b.ssbh) xydm from bjlh_xlcwb a) c where a.xydm=c.xydm and a.ssbh=c.lddm||c.cs||c.qsh) xlcws,")
//									           .append("(select count(*) from (")
//									           .append("select xh,lddm,cs,lx,qsh,(select xydm from view_bjlh_xsxx b where a.xh=b.xh) xydm from  bjlh_xszsxxb a where zzbj='yes'")
//									           .append(") c where c.lddm||c.cs||c.qsh=a.ssbh and a.xydm=c.xydm) yzrs from ssfpb a, bjlh_ssxxb b")
//									           .append(" where a.ssbh = b.lddm || b.cs || b.qsh)");
	 //单个学院住宿舍信息表中的床位总数,同时该数据不存宿舍分配表中
//	 StringBuffer QUERY_XYXSZSZCWS = new StringBuffer(" union select xydm,0 fjzs, count(*) cwzs,0 xlcws,0 kcwzs from (")
//	 	                                     .append("select a.*,(select xqdm from sslddmb d where a.lddm=d.lddm) xqdm,(select xydm from view_bjlh_xsxx b where a.xh=b.xh) xydm from bjlh_xszsxxb a ")
//	 	                                     .append("where zzbj='yes' and not exists (select 1 from ssfpb e where a.lddm||a.cs||a.qsh=e.ssbh)")
//	 	                                     .append(") d ");
	//单个学院每个校区房间总数，床位总数，空床位总数，行李床位总数
	StringBuffer QUERY_XYXQFJZS = new StringBuffer("select xydm,xqdm,count(*) fjzs, sum(cws) cwzs,sum(kcws) kcwzs,sum(xlcws) xlcws from view_bjlh_tjjbxx a ");
//	 StringBuffer QUERY_XYXQFSZS = new StringBuffer("select xydm,xqdm,sum(fjzs) fjzs,sum(cwzs) cwzs,sum(xlcws) xlcws,sum(kcwzs) kcwzs from (select xydm,xqdm,count(*) fjzs, sum(cws) cwzs,sum(xlcws) xlcws,sum(cws-yzrs) kcwzs")
//									           .append(" from (select a.xydm, a.ssbh, a.cws, b.lddm, b.cs, b.qsh,(select xqdm from sslddmb c where b.lddm=c.lddm) xqdm,")
//									           .append("(select count(*) from (select lddm,cs,qsh,cwh,(select xydm from ssfpb b where a.lddm||a.cs||a.qsh=b.ssbh) xydm from bjlh_xlcwb a) c where a.xydm=c.xydm and a.ssbh=c.lddm||c.cs||c.qsh) xlcws,")
//									           .append("(select count(*) from (")
//									           .append("select xh,lddm,cs,lx,qsh,(select xydm from view_bjlh_xsxx b where a.xh=b.xh) xydm from  bjlh_xszsxxb a where zzbj='yes'")
//									           .append(") c where c.lddm||c.cs||c.qsh=a.ssbh and a.xydm=c.xydm) yzrs from ssfpb a, bjlh_ssxxb b")
//									           .append(" where a.ssbh = b.lddm || b.cs || b.qsh)");
	 //单个学院住宿舍信息表中的床位总数,同时该数据不存宿舍分配表中
//	 StringBuffer QUERY_XYXQXSZSZCWS = new StringBuffer(" union select xydm,xqdm,0 fjzs, count(*) cwzs,0 xlcws,0 kcwzs from (")
//	 	                                     .append("select a.*,(select xqdm from sslddmb b where a.lddm=b.lddm) xqdm,(select xydm from view_bjlh_xsxx b where a.xh=b.xh) xydm from bjlh_xszsxxb a ")
//	 	                                     .append("where zzbj='yes' and not exists (select 1 from ssfpb e where a.lddm||a.cs||a.qsh=e.ssbh)")
//	 	                                     .append(") d ");
	//单个学院的每个校区的每个楼栋房间总数，床位总数，空床位总数，行李床位总数
	StringBuffer QUERY_XYXQLDFJZS = new StringBuffer("select xydm,xqdm,lddm,(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,count(*) fjzs, sum(cws) cwzs,sum(kcws) kcwzs,sum(xlcws) xlcws from view_bjlh_tjjbxx a ");
//	 StringBuffer QUERY_XYXQLDFSZS = new StringBuffer("select xydm,xqdm,lddm,(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,sum(fjzs) fjzs,sum(cwzs) cwzs,sum(xlcws) xlcws,sum(kcwzs) kcwzs from (select xydm,xqdm,lddm,count(*) fjzs, sum(cws) cwzs,sum(xlcws) xlcws,sum(cws-yzrs) kcwzs")
//											    .append(" from (select a.xydm, a.ssbh, a.cws, b.lddm, b.cs, b.qsh,(select xqdm from sslddmb c where b.lddm=c.lddm) xqdm,")
//											    .append("(select count(*) from (select lddm,cs,qsh,cwh,(select xydm from ssfpb b where a.lddm||a.cs||a.qsh=b.ssbh) xydm from bjlh_xlcwb a) c where a.xydm=c.xydm and a.ssbh=c.lddm||c.cs||c.qsh) xlcws,")
//											    .append("(select count(*) from (")
//											    .append("select xh,lddm,cs,lx,qsh,(select xydm from view_bjlh_xsxx b where a.xh=b.xh) xydm from  bjlh_xszsxxb a where zzbj='yes'")
//											    .append(") c where c.lddm||c.cs||c.qsh=a.ssbh and a.xydm=c.xydm) yzrs from ssfpb a, bjlh_ssxxb b")
//											    .append(" where a.ssbh = b.lddm || b.cs || b.qsh) a");
	 //单个学院住宿舍信息表中的床位总数,同时该数据不存宿舍分配表中
//	 StringBuffer QUERY_XYXQLDXSZSZCWS = new StringBuffer(" union select xydm,xqdm,lddm,0 fjzs, count(*) cwzs,0 xlcws,0 kcwzs from (")
//	 	                                     .append("select a.*,(select xqdm from sslddmb b where a.lddm=b.lddm) xqdm,(select xydm from view_bjlh_xsxx b where a.xh=b.xh) xydm from bjlh_xszsxxb a ")
//	 	                                     .append("where zzbj='yes' and not exists (select 1 from ssfpb e where a.lddm||a.cs||a.qsh=e.ssbh)")
//	 	                                     .append(") d ");
	//按校区查询所有房间数
	StringBuffer QUERY_XQFJZS = new StringBuffer("select xqdm,count(*) fjzs, sum(cws) cwzs,sum(kcws) kcwzs,sum(xlcws) xlcws from view_bjlh_tjjbxx ");
	
	//按校区楼栋查询所有房间数
	StringBuffer QUERY_XQLDFJZS = new StringBuffer("select xqdm,lddm,(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,count(*) fjzs, sum(cws) cwzs,sum(kcws) kcwzs,sum(xlcws) xlcws from view_bjlh_tjjbxx a ");
	
	//按校区楼栋学院查询所有房间数
	StringBuffer QUERY_XQLDXYFJZS = new StringBuffer("select xqdm,lddm,xydm,count(*) fjzs, sum(cws) cwzs,sum(kcws) kcwzs,sum(xlcws) xlcws from view_bjlh_tjjbxx a ");
	
	//保留数据校区房间数
	StringBuffer QUERY_BLXQFJZS = new StringBuffer("select xqdm,(select xqmc from dm_zju_xq b where a.xqdm=b.dm) xqmc,count(*) fjzs, sum(nvl(cws,0)) cwzs,0 kcwzs,0 xlcws from (")
								          .append("select * from (select (select xqdm from sslddmb b where a.lddm=b.lddm) xqdm,lddm,cs,qsh,cws,fbbj from bjlh_ssxxb a) a ");
	//保留数据校区楼栋房间数
	StringBuffer QUERY_BLXQLDFJZS = new StringBuffer("select xqdm,lddm,(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,count(*) fjzs, sum(cws) cwzs,0 kcwzs,0 xlcws from (")
	                                        .append("select * from (select (select xqdm from sslddmb b where a.lddm=b.lddm) xqdm,lddm,cs,qsh,cws,fbbj from bjlh_ssxxb a) a ");
	
	/**
	 * 传入二个数组返回列表，英文在前，中文在后
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> getList(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 拼接查询条件及值
	 * @param model
	 * @return
	 */
	public String getWhereSql(FykModel model) {
		StringBuffer sql = new StringBuffer();
		values = new ArrayList<String>();
		if (!StringUtils.isNull(model.getXqdm())) {
			sql.append(" and xqdm = ?");
			values.add(model.getXqdm());
		}
		if (!StringUtils.isNull(model.getLddm())) {
			sql.append(" and lddm = ?");
			values.add(model.getLddm());
		}
		if (!StringUtils.isNull(model.getCs())) {
			sql.append(" and cs = ?");
			values.add(model.getCs());
		}
		if (!StringUtils.isNull(model.getQsh())) {
			sql.append(" and qsh = ?");
			values.add(model.getQsh());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			sql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		return sql.toString();
	}
	
	
	/**
	 * 如果学院列表不为空，则输出这个学院下面的房间总数，床位总数，空床位总数，行李床位总数
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> queryXyfjsInfo(FykModel model) throws Exception {
		//如果是团委,体育,等部门则将学院代码替换成相应部门代码,同时将分配标记置空
		String whereSql = "";
		model = replaceXydm(model);
		if ("qrz".equalsIgnoreCase(model.getFbbj())) {
			whereSql = " and xydm not in ('0202','0405','0110','0117')";
		}
		
		model = replaceXydm(model);
		String[] queryList = new String[] { "xydm",  "lddm", "xqdm", "cs", "qsh", "zt" };
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "xydm", "xymc", "fjzs", "cwzs", "kcwzs","xlcws" };
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_XYFJZS);
		sql.append(queryObject.getQueryString());
		sql.append(whereSql);
		sql.append(" group by xydm order by xydm");
		return dao.rsToVator(sql.toString(),queryObject.getInputList(), colList);
	}
	
	/**
	 * 查询一个学院下面的各校区下面的房间总数，床位总数，空床位总数，行李床位总数
	 * @param xqdmList
	 * @param model
	 * @return
	 */
	public List<String[]> queryXyXqfjsInfo(FykModel model) throws Exception {
		String whereSql = "";
		if ("qrz".equalsIgnoreCase(model.getFbbj())) {
			whereSql = " and xydm not in ('0202','0405','0110','0117')";
		}
		model = replaceXydm(model);
		String[] queryList = new String[] { "xydm",  "lddm", "xqdm", "cs", "qsh", "zt"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "xydm", "xqdm", "fjzs", "cwzs", "kcwzs" , "xlcws"};
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_XYXQFJZS);
		sql.append(queryObject.getQueryString());
		sql.append(whereSql);
		sql.append(" group by xydm,xqdm order by xydm,xqdm");
		return dao.rsToVator(sql.toString() ,queryObject.getInputList(), colList);
	}
	
	/**
	 * 查询一个学院下面的各校区下面各楼栋下面的房间总数，床位总数，空床位总数，行李床位总数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXyXqLdfjsInfo(FykModel model) throws Exception {
		String whereSql = "";
		if ("qrz".equalsIgnoreCase(model.getFbbj())) {
			whereSql = " and xydm not in ('0202','0405','0110','0117')";
		}
		model = replaceXydm(model);
		String[] queryList = new String[] { "xydm",  "lddm", "xqdm", "cs", "qsh", "zt"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "xydm", "xqdm", "lddm", "ldmc", "fjzs", "cwzs", "kcwzs", "xlcws" };
		
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_XYXQLDFJZS)
		   .append(queryObject.getQueryString())
		   .append(whereSql)
		   .append(" group by xydm,xqdm,lddm order by xydm,xqdm,lddm");
		return dao.rsToVator(sql.toString(),queryObject.getInputList(), colList);
	}
	
	/**
	 * 传特殊部门学院代码进行替换
	 * @param model
	 * @return
	 */
	public FykModel replaceXydm(FykModel model) {
		if (TWDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(TWDM);
		} else if (TYDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(TYDM);
		} else if (KYDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(KYDM);
		} else if (CJDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(CJDM);
		} 
		return model;
	}
	
	/**
	 * 按校区查询所有房间数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXqfjs(FykModel model) throws Exception{
		String whereSql = "";
		if ("qrz".equalsIgnoreCase(model.getFbbj())) {
			whereSql = " and xydm not in ('0202','0405','0110','0117')";
		}
		model = replaceXydm(model);
		String[] queryList = new String[] { "xydm",  "lddm", "xqdm", "cs", "qsh", "zt"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] {"xqdm", "fjzs", "cwzs", "kcwzs" , "xlcws"};
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_XQFJZS);
		sql.append(queryObject.getQueryString());
		sql.append(whereSql);
		sql.append(" group by xqdm order by xqdm");
		return dao.rsToVator(sql.toString(), queryObject.getInputList(), colList);
	}
	
	/**
	 * 按校区楼栋查询房间数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXqldfjs(FykModel model) throws Exception{
		String whereSql = "";
		if ("qrz".equalsIgnoreCase(model.getFbbj())) {
			whereSql = " and xydm not in ('0202','0405','0110','0117')";
		}
		model = replaceXydm(model);
		String[] queryList = new String[] { "xydm",  "lddm", "xqdm", "cs", "qsh", "zt"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] {"xqdm", "lddm", "ldmc", "fjzs", "cwzs", "kcwzs" , "xlcws"};
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_XQLDFJZS);
		sql.append(queryObject.getQueryString());
		sql.append(whereSql);
		sql.append(" group by xqdm,lddm order by xqdm,lddm");
		return dao.rsToVator(sql.toString(), queryObject.getInputList(), colList);
	}
	
	/**
	 * 按校区,楼栋,学院分组查询房间数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXqldxyfjs(FykModel model) throws Exception{
		String whereSql = "";
		if ("qrz".equalsIgnoreCase(model.getFbbj())) {
			whereSql = " and xydm not in ('0202','0405','0110','0117')";
		}
		model = replaceXydm(model);
		String[] queryList = new String[] { "xydm",  "lddm", "xqdm", "cs", "qsh", "zt"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] {"xqdm", "lddm", "xydm", "fjzs", "cwzs", "kcwzs" , "xlcws"};
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_XQLDXYFJZS);
		sql.append(queryObject.getQueryString());
		sql.append(whereSql);
		sql.append(" group by xqdm,lddm,xydm order by xqdm,lddm,xydm");
		return dao.rsToVator(sql.toString(), queryObject.getInputList(), colList);
	}
	
	/**
	 * 查询保留校区的房间数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryBlXqfjs(FykModel model) throws Exception{
		String[] queryList = new String[] { "lddm", "xqdm", "cs", "qsh"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] {"xqdm", "xqmc", "fjzs", "cwzs", "kcwzs", "xlcws" };
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_BLXQFJZS);
		sql.append(queryObject.getQueryString());
		sql.append(" and fbbj='保留' ) a group by xqdm");
		return dao.rsToVator(sql.toString(), queryObject.getInputList(), colList);
	}
	
	/**
	 * 查询保留校区楼栋房间数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryBlxqldfjs(FykModel model) throws Exception {
		String[] queryList = new String[] { "lddm", "xqdm", "cs", "qsh"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] {"xqdm","lddm","ldmc", "fjzs", "cwzs", "kcwzs" , "xlcws"};
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY_BLXQLDFJZS);
		sql.append(queryObject.getQueryString());
		sql.append(" and fbbj='保留') a group by xqdm,lddm");
		return dao.rsToVator(sql.toString(), queryObject.getInputList(), colList);
	}
	
	/**
	 * 获得公寓管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, FykModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xydm", "xqdm", "lddm", "cs", "qsh" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	/**
	 * 获得学院信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getXyxxList(FykModel model){
		
		String xydm = model.getXydm();
		String xqdm = model.getXqdm();
		String lddm = model.getLddm();
		String cs = model.getCs();
		String qsh = model.getQsh();
		String lx = model.getLx();
		String query = "";
		
		if("团委生".equalsIgnoreCase(lx)){
			xydm = TWDM;
		}else if("体优生".equalsIgnoreCase(lx)){
			xydm = TYDM;
		}else if("研究生".equalsIgnoreCase(lx)){
			xydm = KYDM;
		}else if("成教生".equalsIgnoreCase(lx)){
			xydm = CJDM;
		}else if("全日制".equalsIgnoreCase(lx)){
			query = " and xydm  not in ('" + TWDM + "','" + TYDM + "','" + KYDM + "','" + CJDM + "')";
		}
		
		xydm = Base.isNull(xydm) ? "%" : xydm;
		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;
		cs = Base.isNull(cs) ? "%" : cs;
		qsh = Base.isNull(qsh) ? "%" : qsh;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select xydm,xymc,count(qsh) fjs,sum(cws) zcws,sum(kcws) kcws,sum(xlcws) xlcws ");
		sql.append("from (select a.lddm, (select b.ldmc from sslddmb b where b.lddm = a.LDDM) ldmc, ");
		sql.append("a.cs, a.qsh, a.cws, a.FBBJ, a.xqdm, (select b.xqmc from dm_zju_xq b, sslddmb c ");
		sql.append("where c.xqdm = b.dm and a.lddm = c.lddm) xqmc, a.xydm, (case ");
		sql.append("when a.xydm = '0117' then '成人教育处' when a.xydm = '0202' then ");
		sql.append("'团委' when a.xydm = '0405' then '体育教学部' when a.xydm = '0110' then ");
		sql.append("'科研处' else (select distinct (b.xymc) from view_njxyzybj b ");
		sql.append("where a.xydm = b.xydm) end) xymc, a.xlcws, a.QTXYYZRS, a.BXYYZRS, ");
		sql.append("a.kcws, a.zt from view_bjlh_tjjbxx a where xydm like ? and xqdm like ? ");
		sql.append("and lddm like ? and cs like ? and qsh like ? "+query+") group by xydm, xymc order by xydm");
		String[] colList = new String[]{"xydm", "xymc", "fjs", "zcws", "kcws", "xlcws"}; 
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{xydm,xqdm,lddm,cs,qsh}, colList);
		//System.out.println(sql);
		return list;
	}
	
	/**
	 * 获得校区信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getXqxxList(FykModel model){
		
		String xydm = model.getXydm();
		String xqdm = model.getXqdm();
		String lddm = model.getLddm();
		String cs = model.getCs();
		String qsh = model.getQsh();
		String lx = model.getLx();
		String query = "";
		
		if("团委生".equalsIgnoreCase(lx)){
			xydm = TWDM;
		}else if("体优生".equalsIgnoreCase(lx)){
			xydm = TYDM;
		}else if("研究生".equalsIgnoreCase(lx)){
			xydm = KYDM;
		}else if("成教生".equalsIgnoreCase(lx)){
			xydm = CJDM;
		}else if("全日制".equalsIgnoreCase(lx)){
			query = " and xydm  not in ('" + TWDM + "','" + TYDM + "','" + KYDM + "','" + CJDM + "')";
		}
		
		xydm = Base.isNull(xydm) ? "%" : xydm;
		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;
		cs = Base.isNull(cs) ? "%" : cs;
		qsh = Base.isNull(qsh) ? "%" : qsh;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select xydm,xqdm,xqmc,count(qsh) fjs,sum(cws) zcws,sum(kcws) kcws,sum(xlcws) xlcws ");
		sql.append("from (select a.lddm, (select b.ldmc from sslddmb b where b.lddm = a.LDDM) ldmc, ");
		sql.append("a.cs, a.qsh, a.cws, a.FBBJ, a.xqdm, (select b.xqmc from dm_zju_xq b, sslddmb c ");
		sql.append("where c.xqdm = b.dm and a.lddm = c.lddm) xqmc, a.xydm, (case ");
		sql.append("when a.xydm = '0117' then '成人教育处' when a.xydm = '0202' then ");
		sql.append("'团委' when a.xydm = '0405' then '体育教学部' when a.xydm = '0110' then ");
		sql.append("'科研处' else (select distinct (b.xymc) from view_njxyzybj b ");
		sql.append("where a.xydm = b.xydm) end) xymc, a.xlcws, a.QTXYYZRS, a.BXYYZRS, ");
		sql.append("a.kcws, a.zt from view_bjlh_tjjbxx a where xydm like ? and xqdm like ? ");
		sql.append("and lddm like ? and cs like ? and qsh like ? "+query+") group by xydm,xqdm,xqmc order by xydm,xqdm");
		String[] colList = new String[]{"xydm", "xqdm","xqmc","fjs", "zcws", "kcws", "xlcws"}; 
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{xydm,xqdm,lddm,cs,qsh}, colList);
		//System.out.println(sql);
		return list;
	}
	
	/**
	 * 获得楼栋信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getLdxxList(FykModel model){
		
		String xydm = model.getXydm();
		String xqdm = model.getXqdm();
		String lddm = model.getLddm();
		String cs = model.getCs();
		String qsh = model.getQsh();
		String lx = model.getLx();
		String query = "";
		
		if("团委生".equalsIgnoreCase(lx)){
			xydm = TWDM;
		}else if("体优生".equalsIgnoreCase(lx)){
			xydm = TYDM;
		}else if("研究生".equalsIgnoreCase(lx)){
			xydm = KYDM;
		}else if("成教生".equalsIgnoreCase(lx)){
			xydm = CJDM;
		}else if("全日制".equalsIgnoreCase(lx)){
			query = " and xydm  not in ('" + TWDM + "','" + TYDM + "','" + KYDM + "','" + CJDM + "')";
		}
		
		xydm = Base.isNull(xydm) ? "%" : xydm;
		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;
		cs = Base.isNull(cs) ? "%" : cs;
		qsh = Base.isNull(qsh) ? "%" : qsh;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select xydm,xqdm,lddm,ldmc,count(qsh) fjs,sum(cws) zcws,sum(kcws) kcws,sum(xlcws) xlcws ");
		sql.append("from (select a.lddm, (select b.ldmc from sslddmb b where b.lddm = a.LDDM) ldmc, ");
		sql.append("a.cs, a.qsh, a.cws, a.FBBJ, a.xqdm, (select b.xqmc from dm_zju_xq b, sslddmb c ");
		sql.append("where c.xqdm = b.dm and a.lddm = c.lddm) xqmc, a.xydm, (case ");
		sql.append("when a.xydm = '0117' then '成人教育处' when a.xydm = '0202' then ");
		sql.append("'团委' when a.xydm = '0405' then '体育教学部' when a.xydm = '0110' then ");
		sql.append("'科研处' else (select distinct (b.xymc) from view_njxyzybj b ");
		sql.append("where a.xydm = b.xydm) end) xymc, a.xlcws, a.QTXYYZRS, a.BXYYZRS, ");
		sql.append("a.kcws, a.zt from view_bjlh_tjjbxx a where xydm like ? and xqdm like ? ");
		sql.append("and lddm like ? and cs like ? and qsh like ? "+query+") group by xydm,xqdm,lddm,ldmc order by xydm,xqdm,lddm");
		String[] colList = new String[]{"xydm", "xqdm","lddm","ldmc","fjs", "zcws", "kcws", "xlcws"}; 
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{xydm,xqdm,lddm,cs,qsh}, colList);
		//System.out.println(sql);
		return list;
	}
	
	/**
	 * 获得层数信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getCsxxList(FykModel model){
		
		String xydm = model.getXydm();
		String xqdm = model.getXqdm();
		String lddm = model.getLddm();
		String cs = model.getCs();
		String qsh = model.getQsh();
		String lx = model.getLx();
		String query = "";
		
		if("团委生".equalsIgnoreCase(lx)){
			xydm = TWDM;
		}else if("体优生".equalsIgnoreCase(lx)){
			xydm = TYDM;
		}else if("研究生".equalsIgnoreCase(lx)){
			xydm = KYDM;
		}else if("成教生".equalsIgnoreCase(lx)){
			xydm = CJDM;
		}else if("全日制".equalsIgnoreCase(lx)){
			query = " and xydm  not in ('" + TWDM + "','" + TYDM + "','" + KYDM + "','" + CJDM + "')";
		}
		
		xydm = Base.isNull(xydm) ? "%" : xydm;
		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;
		cs = Base.isNull(cs) ? "%" : cs;
		qsh = Base.isNull(qsh) ? "%" : qsh;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xqdm,a.xydm,a.lddm,a.cs,count(a.qsh) fjs,sum(a.cws) zcws, ");
		sql.append("sum(a.xlcws) xlcws,sum(a.kcws) kcws from view_bjlh_tjjbxx a where xydm like ? ");
		sql.append("and xqdm like ? and lddm like ? and cs like ? and qsh like ? "+query);
		sql.append(" group by xydm,xqdm,lddm,cs order by xydm, xqdm, lddm,cs ");
		String[] colList = new String[]{"xydm", "xqdm","lddm","cs", "fjs","zcws","kcws", "xlcws"}; 
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{xydm,xqdm,lddm,cs,qsh}, colList);
		//System.out.println(sql);
		return list;
	}
	
	/**
	 * 获得寝室信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getQsxxList(FykModel model){
		
		String xydm = model.getXydm();
		String xqdm = model.getXqdm();
		String lddm = model.getLddm();
		String cs = model.getCs();
		String qsh = model.getQsh();
		String lx = model.getLx();
		String query = "";
		
		if("团委生".equalsIgnoreCase(lx)){
			xydm = TWDM;
		}else if("体优生".equalsIgnoreCase(lx)){
			xydm = TYDM;
		}else if("研究生".equalsIgnoreCase(lx)){
			xydm = KYDM;
		}else if("成教生".equalsIgnoreCase(lx)){
			xydm = CJDM;
		}else if("全日制".equalsIgnoreCase(lx)){
			query = " and xydm  not in ('" + TWDM + "','" + TYDM + "','" + KYDM + "','" + CJDM + "')";
		}
		
		xydm = Base.isNull(xydm) ? "%" : xydm;
		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;
		cs = Base.isNull(cs) ? "%" : cs;
		qsh = Base.isNull(qsh) ? "%" : qsh;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.lddm,a.cs,a.qsh,a.cws,a.FBBJ,a.xqdm,a.xydm, ");
		sql.append("a.xlcws,a.QTXYYZRS,a.bxyyzrs,a.kcws,a.zt, ");
		sql.append("case when a.zt = '满的' then 'green' when a.zt = '空的' then 'red' when a.zt = '闲的' then 'orange' else 'blue' end col ");
		sql.append("from view_bjlh_tjjbxx a where xydm like ? and xqdm like ? and lddm like ? and cs like ? and qsh like ? "+query+" order by xydm, xqdm, lddm,cs,qsh ");
		String[] colList = new String[]{"xydm", "xqdm","lddm","cs", "qsh","bxyyzrs","cws", "kcws", "xlcws","zt","col"}; 
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{xydm,xqdm,lddm,cs,qsh}, colList);
		//System.out.println(sql);
		return list;
	}
	
	/**
	 * 获得学生住宿列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszsList(String[] colList,String pk) {
		String sql = "select xh,xm,xb,lx,nj,xymc,zymc,bjmc,cwm from view_bjlh_xszsxx where lddm||cs||qsh = ? and zzbj = 'yes' order by cwh";
		ArrayList<String[]> rs = dao.rsToVator(sql, new String[] { pk },
				colList);
		return rs;
	}
}

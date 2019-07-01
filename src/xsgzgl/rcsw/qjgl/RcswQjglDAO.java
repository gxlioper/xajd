package xsgzgl.rcsw.qjgl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 日常事务_请假管理_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class RcswQjglDAO extends CommDAO {

	/**
	 * 获得请假流程列表表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getQjlcList(RcswQjglForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String lxmc = model.getLxmc().trim();
		String kfxg = model.getKfxg().trim();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.* from (");
		sql.append("select a.id,a.lxmc,a.lcid,nvl(c.num,0) num, ");
		sql.append("(select b.mc from xg_xtwh_splc b where a.lcid = b.id) lcmc, ");
		sql.append("a.mints,a.maxts,a.xgr,a.xgsj,(select mc from xg_rcsw_qjgl_qjlxdmb b where a.qjlx=b.dm)qjlx ");
		sql.append("from xg_rcsw_qjgl_qjlxb a ");
		sql.append("left join ");
		sql.append("(select qjid,count(1) num from xg_rcsw_qjgl_qjsqb where sqjg <> '批准请假' and sqjg not like '%不通过%' group by qjid) c ");
		sql.append("on a.id=c.qjid ");
		sql.append(") a ");
		sql.append(" where 1=1 ");
		sql.append(Base.isNull(lxmc) ? "" : " and lxmc like '%" + lxmc + "%'");
		sql.append("yes".equalsIgnoreCase(kfxg) ? " and num = '0' ":"");
		sql.append("no".equalsIgnoreCase(kfxg) ? " and num <> '0' ":"");
		
		String[] colList = new String[] { "id", "lxmc", "lcmc", "mints",
				"maxts", "num" };
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), sql.toString(), new String[]{},
						colList);

		return list;
	}
	
	/**
	 * 删除请假流程
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delQjlc(RcswQjglForm model, User user) throws Exception {

		String[] pkValue = model.getCheckVal();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_rcsw_qjgl_qjlxb ");
		sql.append("where 1=1 ");
		sql.append("and( ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append("or ");
			}
			sql.append(" id = ? ");
		}
		sql.append(") ");

		DAO dao = DAO.getInstance();
		boolean flag = dao.runUpdate(sql.toString(), pkValue);

		return flag;

	}

	//	===================以上请假流程设置======================================
	
	/**
	 * 获得我的请假列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMyqjList(RcswQjglForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 学年
		String xn = model.getXn().trim();
		// 学期
		String xq = model.getXq().trim();
		// 开始时间
		String kssj = model.getKssj().trim();
		// 结束时间
		String jssj = model.getJssj().trim();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.* from (");
		sql.append("select a.xh,a.id,a.xn,a.xq,a.sqts,a.sqsj,a.sqjg, ");
		sql.append("(select b.lxmc from xg_rcsw_qjgl_qjlxb b where a.qjid = b.id) lxmc ");
		sql.append("from xg_rcsw_qjgl_qjsqb a ");
		sql.append("order by a.sqsj desc ) a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xh=? ");
		sql.append(Base.isNull(xn) ? "" : " and a.xn ='" + xn + "'");
		sql.append(Base.isNull(xq) ? "" : " and a.xq ='" + xq + "'");
		sql.append(Base.isNull(kssj) ? "" : " and substr(replace(a.sqsj,'-',''),0,8) >='" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and substr(replace(a.sqsj,'-',''),0,8) <='" + jssj + "'");
		
		String[] colList = new String[] { "id", "lxmc", "sqts", "sqsj", "sqjg" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), sql.toString(),
						new String[] {user.getUserName()}, colList);

		return list;
	}
	
	/**
	 * 获得申请类型列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSqlxList(RcswQjglForm model,
			User user) {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.id,a.lxmc,a.mints,a.maxts,a.qjlx ");
		sql.append("from xg_rcsw_qjgl_qjlxb a where to_number(a.mints)<? and to_number(a.maxts)>=? ");
		sql.append("order by mints ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {model.getSqts(),model.getSqts()},
				new String[] { "id", "lxmc", "mints", "maxts","qjlx" });

		return list;

	}
	
	/**
	 * 获得岗位ID列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getGwidList(RcswQjglForm model) {

		String qjid = model.getQjid();

		StringBuilder sql = new StringBuilder();
		sql.append("select b.xh, b.spgw ");
		sql.append("from xg_rcsw_qjgl_qjlxb a, xg_xtwh_spbz b ");
		sql.append("where a.id = ? ");
		sql.append("and a.lcid = b.splc ");
		sql.append("order by b.xh ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { qjid }, new String[] { "xh", "spgw" });

		return list;

	}
	
	/**
	 * 流程图
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getLctList(RcswQjglForm model) {

		String qjid = model.getSqid();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,t.mc,f.shzt from xg_xtwh_spgw t ");
		sql.append("left join xg_xtwh_spbz a on t.id =a.spgw "); 
		sql.append("left join xg_xtwh_splc b on a.splc=b.id " );
		sql.append("left join xg_rcsw_qjgl_qjlxb d on d.lcid=b.id ");
		sql.append("left join xg_rcsw_qjgl_qjsqb e on e.qjid=d.id ");
		sql.append("left join xg_rcsw_qjgl_qjshb f on f.sqid=e.id and f.gwid=t.id ");
		sql.append("where e.id=? ");
		sql.append("order by a.xh ");
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { qjid }, new String[] { "xh", "mc", "shzt" });
		return list;
	}
	
	/**
	 * 获得最后申请的请假ID
	 * 
	 * @author 伟大的骆
	 */
	public String getQjgwId(RcswQjglForm model, User user) {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.id from (select a.id ");
		sql.append("from xg_rcsw_qjgl_qjsqb a ");
		sql.append("where a.xh=? ");
		sql.append("order by sqsj desc ");
		sql.append(") a where rownum = 1");

		DAO dao = DAO.getInstance();

		return dao.getOneRs(sql.toString(),
				new String[] {model.getXh() }, "id");

	}
	
	public String minDaysIsLegality(RcswQjglForm model){
		if(!model.getId().equals("")){
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) as ff from xg_rcsw_qjgl_qjlxb where (?>to_number(mints) and ?<to_number(maxts)) or (?>to_number(mints) and ?<=to_number(maxts)) and id!=? ");
			DAO dao = DAO.getInstance();
			return dao.getOneRs(sql.toString(),new String[] {model.getMints(),model.getMints(),model.getMaxts(),model.getMaxts(),model.getId() }, "ff");
		}else{
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) as ff from xg_rcsw_qjgl_qjlxb where (?>to_number(mints) and ?<to_number(maxts)) or (?>to_number(mints) and ?<=to_number(maxts))  ");
			DAO dao = DAO.getInstance();
			return dao.getOneRs(sql.toString(),new String[] {model.getMints(),model.getMints(),model.getMaxts(),model.getMaxts()}, "ff");
		}
	}
	
	public HashMap<String, String> getQjStuInfo(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh", 
					"csrq", "mzmc", "zzmmmc",  "jtdh","jtszd","jtyb", "lxdh" };
			map = commonQueryOne("view_xsxxb_zxs", colList, "xh",xh);
		}
		return map;
	}

	/**
	 * 删除我的请假（申请）
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delMyqjSq(RcswQjglForm model, User user) throws Exception {

		String[] pkValue = model.getCheckVal();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_rcsw_qjgl_qjsqb ");
		sql.append("where 1=1 ");
		sql.append("and( ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append("or ");
			}
			sql.append(" id = ? ");
		}
		sql.append(") ");

		DAO dao = DAO.getInstance();
		boolean flag = dao.runUpdate(sql.toString(), pkValue);

		return flag;

	}
	
	/**
	 * 删除我的请假(审核)
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delMyqjSh(RcswQjglForm model, User user) throws Exception {

		String[] pkValue = model.getCheckVal();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_rcsw_qjgl_qjshb ");
		sql.append("where 1=1 ");
		sql.append("and( ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append("or ");
			}
			sql.append(" sqid = ? ");
		}
		sql.append(") ");

		DAO dao = DAO.getInstance();
		boolean flag = dao.runUpdate(sql.toString(), pkValue);

		return flag;

	}

	//	===================以上我的请假======================================
	
	/**
	 * 获得用户岗位信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getShgwInfoList(RcswQjglForm model,
			User user) {

		String czxm = model.getCzxm();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select e.lxmc,c.splc lcid, d.mc lcmc, ");
		sql.append("a.id gwid, a.mc gwmc, c.xh lv ");
		sql.append("from xg_xtwh_spgw   a, ");
		sql.append("xg_xtwh_spgwyh b, ");
		sql.append("xg_xtwh_spbz   c, ");
		sql.append("xg_xtwh_splc   d, ");
		sql.append("xg_rcsw_qjgl_qjlxb e ");
		sql.append("where a.id = b.spgw ");
		sql.append("and c.spgw = b.spgw ");
		sql.append("and d.id = c.splc ");
		sql.append("and e.lcid = c.splc ");
		sql.append("and b.spyh = ? ");
		sql.append(Base.isNull(czxm) ? "" : "and e.id='" + czxm + "' ");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { user.getUserName() }, new String[] { "lxmc","lcid",
						"gwid", "gwmc", "lv" });

		return list;

	}
	
	/**
	 * 获得我的工作列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getMygzList(
			List<HashMap<String, String>> shgwList, RcswQjglForm model,
			User user) {

		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();

		StringBuilder sql = new StringBuilder();

		sql.append("select lxid,lxmc,count(1) num from ( ");
		sql.append("select * from (select c.id lxid,c.lxmc,c.lcid, ");
		sql.append("b.sqid,a.xn,a.xq,d.xh lv,e.maxlv,b.gwid, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj,f.xydm,f.bjdm ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz d, ");
		sql.append("(select max(xh) maxlv, splc from xg_xtwh_spbz group by splc) e, ");
		sql.append("view_xsjbxx f ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid ");
		sql.append("and f.xh = a.xh ");
		sql.append("and e.splc = d.splc) a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.xn=? ");
		sql.append("and a.xq=? ");
		
		sql.append("and (");
		//最小级别
		sql.append("(a.lv = 1 and a.shzt = '未审核' and exists ");
		sql.append("(select 1 from (select c.lxmc,c.lcid,a.xh, ");
		sql.append("a.xn,a.xq,a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and (b.lv = a.lv + 1 or a.lv=a.maxlv) ");
		sql.append("and b.shzt = '未审核')) ");
		     
		// 其他级别
		sql.append("or ");
		sql.append("(a.lv <> 1 and a.lv <> a.maxlv and a.shzt = '未审核' and exists ");
		sql.append("(select 1 from (select c.lxmc,c.lcid,a.xh,a.xn, ");
		sql.append("a.xq,a.sqsj,b.sqid,b.gwid,d.xh lv,b.shr,b.shzt, ");
		sql.append("b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = '通过') and exists ");
		sql.append("(select 1 from (select c.lxmc, ");
		sql.append("c.lcid,a.xh,a.xn,a.xq,a.sqsj,b.sqid, ");
		sql.append("b.gwid,d.xh lv,b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv + 1 ");
		sql.append("and b.shzt = '未审核')) ");
		
		// 最大级别
		sql.append("or ");
		sql.append("(a.lv = a.maxlv and a.shzt = '未审核' and exists ");
		sql.append("(select 1 from (select c.lxmc,c.lcid,a.xh, ");
		sql.append("a.xn,a.xq,a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = '通过')) ");
		
		sql.append(")");
			
		sql.append("and (");
		
		if(shgwList!=null && shgwList.size()>0){

			for (int i = 0; i < shgwList.size(); i++) {
	
				HashMap<String, String> map = shgwList.get(i);
				// 岗位名称
				String lcid = map.get("lcid");
				// 岗位名称
				String gwid = map.get("gwid");
				// 岗位名称
				String gwmc = map.get("gwmc");
	
				if (i != 0) {
					sql.append("or ");
				}
	
				sql.append("(");
				sql.append("a.lcid='" + lcid + "' ");
				sql.append("and a.gwid='" + gwid + "' ");
	
				String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
						"xydm", "bjdm");
				sql.append(searchTjByUser);
	
				sql.append(")");
			}
			
		}else{
			sql.append(" 1=2 ");
		}
		
		sql.append(")");
		
		sql.append(") group by lxid,lxmc");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao
				.getList(sql.toString(), new String[] { xn, xq }, new String[] {
						"lxid", "lxmc", "num" });
		
		return list;
	}
	
	/**
	 * 获得初始化项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCshXmList(RcswQjglForm model,
			User user) {

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct e.id xmdm,e.lxmc xmmc ");
		sql.append("from xg_xtwh_spgw   a, ");
		sql.append("xg_xtwh_spgwyh b, ");
		sql.append("xg_xtwh_spbz   c, ");
		sql.append("xg_xtwh_splc   d, ");
		sql.append("xg_rcsw_qjgl_qjlxb e ");
		sql.append("where a.id = b.spgw ");
		sql.append("and c.spgw = b.spgw ");
		sql.append("and d.id = c.splc ");
		sql.append("and e.lcid = c.splc ");
		sql.append("and b.spyh = ? ");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { user.getUserName() }, new String[] { "xmdm",
						"xmmc" });

		return list;

	}
	
	public List<HashMap<String, String>> getShList(RcswQjglForm model,
			User user) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.mc,a.shr,a.shzt,a.shsj,a.shyj from  xg_rcsw_qjgl_qjshb a left join xg_rcsw_qjgl_qjsqb t1 on a.sqid=t1.id");
		sql.append(" left join xg_rcsw_qjgl_qjlxb t3 on t1.qjid=t3.id left join xg_xtwh_splc t2 on t3.lcid=t2.id");
		sql.append(" left join xg_xtwh_spbz b on t2.id=b.splc and a.gwid=b.spgw left join xg_xtwh_spgw c on b.spgw=c.id");
		sql.append(" where sqid=? order by b.xh ");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { model.getSqid() }, new String[] { "mc","shr","shzt","shsj","shyj" });
		return list;

	}
	

	/**
	 * 获得我的审核列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getMyshList(RcswQjglForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		SearchModel searchModel=model.getSearchModel();
		
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[]inputV=SearchService.getTjInput(searchModel);
		
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 操作项目
		String czxm = model.getCzxm();
		// 审核岗位
		String shgw = model.getShgw();
		
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from (select c.lxmc,c.lcid, ");
		sql.append("a.xh,f.xm,f.nj,f.xydm,f.xymc,f.zydm, ");
		sql.append("f.zymc,f.bjdm,f.bjmc,a.sqts,a.qjid, ");
		sql.append("b.sqid,a.xn,a.xq,d.xh lv,e.maxlv,b.gwid, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz d, ");
		sql.append("(select max(xh) maxlv, splc from xg_xtwh_spbz group by splc) e, ");
		sql.append("view_xsjbxx f ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid ");
		sql.append("and f.xh = a.xh ");
		sql.append("and e.splc = d.splc) a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.xn=? ");
		sql.append("and a.xq=? ");
		sql.append("and a.qjid=? ");
		sql.append("and a.gwid=? ");
		
		sql.append("and (");
		//最小级别
		sql.append("(a.lv = 1 and a.shzt = '未审核' and exists ");
		sql.append("(select 1 from (select c.lxmc,c.lcid,a.xh, ");
		sql.append("a.xn,a.xq,a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and (b.lv = a.lv + 1 or a.lv=a.maxlv ) ");
		sql.append("and b.shzt = '未审核')) ");
		     
		// 其他级别
		sql.append("or ");
		sql.append("(a.lv <> 1 and a.lv <> a.maxlv and a.shzt = '未审核' and exists ");
		sql.append("(select 1 from (select c.lxmc,c.lcid,a.xh,a.xn, ");
		sql.append("a.xq,a.sqsj,b.sqid,b.gwid,d.xh lv,b.shr,b.shzt, ");
		sql.append("b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = '通过') and exists ");
		sql.append("(select 1 from (select c.lxmc, ");
		sql.append("c.lcid,a.xh,a.xn,a.xq,a.sqsj,b.sqid, ");
		sql.append("b.gwid,d.xh lv,b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv + 1 ");
		sql.append("and b.shzt = '未审核')) ");
		
		// 最大级别
		sql.append("or ");
		sql.append("(a.lv = a.maxlv and a.shzt = '未审核' and exists ");
		sql.append("(select 1 from (select c.lxmc,c.lcid,a.xh, ");
		sql.append("a.xn,a.xq,a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from xg_rcsw_qjgl_qjsqb a, ");
		sql.append("xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = '通过')) ");
		sql.append(")");
		sql.append(searchTj);
		sql.append(searchTjByUser);
//		
		DAO dao=DAO.getInstance();
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), sql.toString(),
					dao.unionArray(new String[] { xn, xq, czxm, shgw },inputV), new String[] {
								"sqid", "xh", "xm", "bjmc", "sqts", "shzt" });

		return list;
	}
	
	/**
	 * 获得我的请假信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getMyqjInfo(RcswQjglForm model, User user) {

		// 申请ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,b.xm,b.xb,b.nj,b.xymc,b.zymc,b.bjmc,a.lxdh,a.jtdh,a.qjcl,a.qjid,");
		sql.append("a.jtdz,a.kssj,a.jssj,a.sqts,a.sqly,a.bz,a.sqts,a.kzzd1,");
		sql.append("(select c.lxmc from xg_rcsw_qjgl_qjlxb c where a.qjid=c.id) lxmc ");
		sql.append("from xg_rcsw_qjgl_qjsqb a,view_xsjbxx b ");
		sql.append("where a.xh=b.xh ");
		sql.append("and a.id=? ");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { sqid }, new String[] { "xh", "xm", "xb", "bjmc", "lxdh", "jtdh",
						"jtdz", "kssj", "jssj", "sqts", "sqly", "bz", "lxmc","nj" ,"xymc","zymc",
						"sqts","qjcl","qjid","kzzd1"});

		return map;
	}
	
	/**
	 * 获得我的请假信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getShInfoList(RcswQjglForm model) {

		// 申请ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();

		sql.append("select * from (select b.gwid,b.shr,b.shzt,b.shsj,b.shyj, ");
		sql.append("(select e.xm from yhb e where e.yhm=b.shr)shrxm,d.xh lv ");
		sql.append("from xg_rcsw_qjgl_qjsqb a,xg_rcsw_qjgl_qjshb b, ");
		sql.append("xg_rcsw_qjgl_qjlxb c, xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid and c.id = a.qjid ");
		sql.append("and d.spgw = b.gwid and d.splc = c.lcid ");
		sql.append("and b.sqid = ?) order by lv ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { sqid }, new String[] { "gwid", "shr", "shzt",
						"shsj", "shyj", "lv", "shrxm" });
		return list;
	}
	
	public boolean updateQjlc(RcswQjglForm model) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("update xg_rcsw_qjgl_qjlxb a set a.lxmc=?,a.lcid=?,a.mints=?,a.maxts=?,a.xgr=? where a.id=? ");
		String[] params = new String[]{model.getLxmc(),model.getLcid(),model.getMints(),model.getMaxts(),model.getXgr(),model.getId()};
		
		return dao.runUpdate(sql.toString(), params);
	}
	
	/**
	 * 获得我的请假信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveShzt(RcswQjglForm model, User user) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append("update xg_rcsw_qjgl_qjshb a ");
		sql.append("set a.shr=? ");
		sql.append(",a.shzt=? ");
		sql.append(",a.shsj=? ");
		sql.append(",a.shyj=? ");
		sql.append("where sqid=? ");
		sql.append("and gwid=? ");

		String[] pk = model.getCheckVal();

		// 审核状态
		String shzt = model.getShzt();

		// 申请ID
		String sqid = model.getSqid();

		// 审核岗位
		String shgw = model.getShgw();

		// 审核时间
		String shsj = getNowTime("yyyy-mm-dd hh24:mi:ss");

		// 审核意见
		String shyj = model.getShyj();

		List<String[]> params = new ArrayList<String[]>();

		if (pk != null && pk.length > 0) {
			
			for(int i=0;i<pk.length;i++){
				String[] value = new String[] { user.getUserName(), shzt, shsj,
						shyj, pk[i], shgw };
				params.add(value);
			}
			
		} else {
			String[] value = new String[] { user.getUserName(), shzt, shsj,
					shyj, sqid, shgw };

			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params,
				"xg_rcsw_qjgl_qjshb", user);

		return flag;
	}
	
	/**
	 * 保存申请结果
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveSqjg(RcswQjglForm model, User user) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append("update xg_rcsw_qjgl_qjsqb a ");
		sql.append("set a.sqjg=? ");
		sql.append("where id=? ");

		String[] pk = model.getCheckVal();

		// 审核状态
		String shzt = model.getShzt();
		
		// 申请ID
		String sqid = model.getSqid();

		// 申请结果
		String sqjg = "";

		// 审核岗位
		HashMap<String, String> lcMap = getShlcBySqid(sqid);
		model.setLcid(lcMap.get("lcid"));
		HashMap<String, String> map = getShgwInfo(model, user);
		

		if(map.get("lv").equalsIgnoreCase(map.get("maxlv"))){
			
			if("通过".equalsIgnoreCase(shzt)){
				sqjg = "批准请假";
			}else{
				sqjg = map.get("gwmc") + "审核" + shzt;
			}
		}else{
			sqjg = map.get("gwmc") + "审核" + shzt;
		}

		List<String[]> params = new ArrayList<String[]>();

		if (pk != null && pk.length > 0) {
			
			for(int i=0;i<pk.length;i++){
				String[] value = new String[] { sqjg,  pk[i] };
				params.add(value);
			}
			
		} else {
			String[] value = new String[] { sqjg, sqid };

			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params,
				"xg_rcsw_qjgl_qjsqb", user);

		return flag;
	}
	
	/**
	 * 获得我的请假信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getShgwInfo(RcswQjglForm model, User user) {

		// 审核岗位
		String shgw = model.getShgw();
		// 审核流程ID
		String shlc= model.getLcid();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.id gwid, a.mc gwmc, b.xh lv,c.maxlv ");
		sql.append("from xg_xtwh_spgw a, xg_xtwh_spbz b, ");
		sql.append("(select max(xh) maxlv, splc from xg_xtwh_spbz group by splc) c ");
		sql.append("where a.id = b.spgw ");
		sql.append("and c.splc = b.splc ");
		sql.append("and c.splc = b.splc ");
		sql.append("and a.id = ? ");
		sql.append("and c.splc = ? ");
		
		DAO dao = DAO.getInstance();

		return dao.getMap(sql.toString(), new String[] { shgw,shlc }, new String[] {
				"gwmc", "gwid", "lv", "maxlv" });

	}
	// ===================以上我的工作======================================
	
	/**
	 * 获得结果查询列表
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getMycxList(RcswQjglForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		SearchModel searchModel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[]inputV=SearchService.getTjInput(searchModel);
		// 学年
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,id,xh,xm,bjmc,lxmc,sqsj,sqts,sqjg,nj,xymc,zymc,xydm,zydm,bjdm,sqjgxx  ");
		sql.append(" from xg_view_rcsw_qjgl_qjsq a  where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by sqsj desc ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", inputV, new String[] {"id", "xh", "xm",
			"nj","xymc","zymc","bjmc","lxmc","sqsj","sqts", "sqjgxx" }, model);
	}
	
	/**
	 * 获得我的请假信息
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getQjshInfo(RcswQjglForm model, User user) {

		// 申请ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.mc gwmc from(  ");
		sql.append(" select a.sqid,a.gwid,a.shr,a.shzt,a.shsj,a.shyj,b.lcid  ");
		sql.append(" from xg_rcsw_qjgl_qjshb a,xg_rcsw_qjgl_qjlxb b,xg_rcsw_qjgl_qjsqb c   ");
		sql.append(" where sqid=? and b.id=c.qjid  and a.sqid=c.id "); 
		sql.append(" )a left join (select a.id,a.mc, b.xh,b.splc from xg_xtwh_spgw   ");
		sql.append(" a left join xg_xtwh_spbz b on a.id=b.spgw group by a.id,a.mc,xh,b.splc)   ");
		sql.append(" b on a.gwid=b.id and a.lcid=b.splc  order by xh   ");
		DAO dao = DAO.getInstance();
		return   dao.getList(sql.toString(),
				new String[] { sqid }, new String[] {"gwmc","shr", "shyj", "shsj", "shzt"});

		
	}
	
	/**
	 * 根据申请ID获取审核流程
	 * @param id
	 * @return
	 */
	public HashMap<String,String>getShlcBySqid(String id){
		DAO dao = DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select lcid from xg_rcsw_qjgl_qjlxb a,");
		sql.append(" xg_rcsw_qjgl_qjsqb b where a.id=b.qjid and b.id=? ");
		return dao.getMap(sql.toString(), new String[]{id}, new String[]{"lcid"});
	}
	
	
	/**
	 * 获取请假类别列表
	 * @ auther qlj
	 */
	public List<HashMap<String,String>>getQjlbList(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from  xg_rcsw_qjgl_qjlxdmb ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * 根据申请ID查询审批岗位
	 * @param sqid
	 * @return
	 * @throws SQLException
	 */
	public String[] getShgwBySqid(String sqid) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select spgw from xg_xtwh_spbz where splc=(");
		sql.append("select t2.lcid from xg_rcsw_qjgl_qjsqb t1 ");
		sql.append("left join xg_rcsw_qjgl_qjlxb t2 on t1.qjid=t2.id ");
		sql.append("where t1.id=?");
		sql.append(") order by xh");
		
		return DAO.getInstance().getArray(sql.toString(), new String[]{sqid}, "spgw");
	}
	
	/**
	 * 日常事务结果查询自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getMycxExportList(RcswQjglForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		SearchModel searchModel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[]inputV=SearchService.getTjInput(searchModel);
		// 学年
		StringBuilder sql = new StringBuilder();
		
		//sql.append(" select * from xg_view_rcsw_qjgl_qjsq ");
		
		sql.append(" select rownum r,a.* ");
		sql.append(" from xg_view_rcsw_qjgl_qjsq a  where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by sqsj desc ");
		String[] colList = new String[] {"id", "xh", "xm","xn","kssj","jssj","xb","xq",
				"nj","xymc","zymc","bjmc","lxmc","sqsj","sqts", "sqjgxx" };
		
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList, model);
	}
}

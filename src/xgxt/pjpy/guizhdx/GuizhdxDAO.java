package xgxt.pjpy.guizhdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 贵州大学评奖
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 屈朋辉
 * @version 1.0
 */
public class GuizhdxDAO {

	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * @param model
	 * @return StringBuilder
	 * */
	public StringBuilder getWhereSql(GuizhdxForm model){
		value = new ArrayList<String>();
		String xh = model.getQuerylike_xh();
		String xm = model.getQuerylike_xm();
		String xn = model.getQueryequals_xn();
		String nd = model.getQueryequals_nd();
		String xq = model.getQueryequals_xq();
		String xydm = model.getQueryequals_xydm();
		String zydm = model.getQueryequals_zydm();
		String bjdm = model.getQueryequals_bjdm();
		String nj = model.getQueryequals_nj();
		String jxjdm = model.getQueryequals_jxjdm();
		String xysh = model.getQueryequals_xysh();
		String shzt = model.getQueryequals_shzt();
		
		StringBuilder sb = new StringBuilder("where 1=1 ");
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(zydm !=null && !zydm.equalsIgnoreCase("")){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(bjdm !=null && !bjdm.equalsIgnoreCase("")){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(xh !=null && !xh.equalsIgnoreCase("")){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(xm !=null && !xm.equalsIgnoreCase("")){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(nd !=null && !nd.equalsIgnoreCase("")){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(xq !=null && !xq.equalsIgnoreCase("")){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(StringUtils.isNotNull(jxjdm)){
			sb.append( " and jxjdm=?");
			value.add(jxjdm);
		}
		if(StringUtils.isNotNull(xysh)){
			sb.append( " and xysh=?");
			value.add(xysh);
		}
		if(StringUtils.isNotNull(shzt)){
			sb.append( " and shzt=?");
			value.add(shzt);
		}
		return sb;
	}
	
	/**
	 * 获取奖学金剩余名额
	 * @param xydm
	 * @param jxjdm
	 * @param xn
	 * @return
	 */
	public String getSymeForJxj(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.szrs-b.count count from (select szrs from guizdx_pjpy_rssz ");
		sql.append("where bmlx=? and szlx='jxj' and szbm=? and  jxjdm =?");
		sql.append(" and xn =?  and sznj=?) a, ");
		sql.append("(select count(*) count from view_typj_jxjsqb where  ");
		
		if (!map.isEmpty() && "1".equals(map.get("shjb"))) {
			sql.append(" jxjdm = ? and xn = ? and nj=? and shzt='通过') b");
		} else {
			if ("zy".equals(map.get("bmlx"))) {
				sql.append("zydm=? and jxjdm = ? and xn = ? and nj=? and fdysh='通过') b");
			} else {
				sql.append("xydm=? and jxjdm = ? and xn = ? and nj=? and xysh='通过') b");
			}
			
		}

		return dao.getOneRs(sql.toString(),new String[] { map.get("bmlx"),map.get("bmdm"), map.get("jxjdm"),
						map.get("xn"), map.get("nj"), map.get("bmdm"),
						map.get("jxjdm"), map.get("xn"), map.get("nj") },"count");
	}

	
	/**
	 * 获取荣誉称号剩余名额
	 * @param xydm
	 * @param jxjdm
	 * @param xn
	 * @return
	 */
	public String getSymeForRych(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.szrs-b.count count from (select szrs from ");
		sql.append("guizdx_pjpy_rssz where bmlx='xy' and szlx='rych' ");
		sql.append("and szbm=? and  jxjdm =? and xn =?  and sznj=?) a,");
		sql.append("(select count(*) count from view_typj_rychsq where ");
		
		if (!map.isEmpty() && "1".equals(map.get("shjb"))) {
			sql.append("xydm=? and rychdm = ? and xn = ? and nj=? and shzt='通过') b");
		} else {
			if ("zy".equals(map.get("bmlx"))) {
				sql.append("zydm=? and rychdm = ? and xn = ? and nj=? and fdysh='通过') b");
			} else {
				sql.append("xydm=? and rychdm = ? and xn = ? and nj=? and xysh='通过') b");
			}
			
		}

		return dao.getOneRs(sql.toString(), new String[] { map.get("bmdm"),
				map.get("rychdm"), map.get("xn"), map.get("nj"),
				map.get("bmdm"), map.get("rychdm"), map.get("xn"),
				map.get("nj") }, "count");
	}

	
	/**
	 * 某班的班主任
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	public String getBzr(String bjdm) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select a.zgh,(select xm from yhb where yhm = a.zgh ) xm from bzrbbb a where bjdm = ?";
		String[] bzr = dao.getArray(sql, new String[] { bjdm }, "xm");
		
		return Arrays.toString(bzr).replace("[", "").replace("]", "");
	}

	
	/**
	 * 先进班级的剩余名额 
	 * @param xn
	 * @param xydm
	 * @return
	 */
	public String getXjbjSyme(String xn, String xydm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (select me from zjlg_xjbjmeb where xn=? and xydm=?)-");
		sql.append("(select count(*) from view_gzdx_xjbj where xn=? and xydm=?");
		sql.append(" and xysh='通过') syme from dual");
		
		return dao.getOneRs(sql.toString(), new String[] {xn,xydm,xn,xydm}, "syme");
	}
	
	
	/**
	 * 当前时间
	 * @return
	 */
	public String getNow() {
		DAO dao = DAO.getInstance();
		return dao.getNowTime("YYYYMMDD");
	}

	
	/**
	 * 获取奖学金审核级别
	 * @param jxjdm
	 * @return
	 */
	public String getShjb(String jxjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select nvl(fjsh,3) fjsh from view_typj_tjsz where jxjdm = ?";
		return dao.getOneRs(sql, new String[] { jxjdm }, "fjsh");
	}

	
	/**
	 * 获取荣誉称号审核级别
	 * @param jxjdm
	 * @return
	 */
	public String getShjbForRych(String rychdm) {
		DAO dao = DAO.getInstance();
		String sql = "select nvl(shjb,3) shjb from view_typj_rychshjb where rychdm = ?";
		return dao.getOneRs(sql, new String[] { rychdm }, "shjb");
	}
	
	
	
	/**
	 * 批量保存奖学金审核级别
	 * @param dm
	 * @param shjb
	 * @param flg
	 * @return
	 * @throws SQLException
	 */
	 public boolean saveBatchShjbForJxj(String[] dm, String shjb)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String[] sqlArr = new String[dm.length + 1];
		int n = 0;

		StringBuilder delBuilder = new StringBuilder();

		delBuilder.append("delete from typj_tjsz where ");

		for (int i = 0; i < dm.length; i++) {
			delBuilder.append("jxjdm='");
			delBuilder.append(dm[i]);
			delBuilder.append("'");

			if (i != dm.length - 1) {
				delBuilder.append(" or ");
			}

			sqlArr[n] = delBuilder.toString();
			n++;

			StringBuilder sb = new StringBuilder();
			sb.append("insert into typj_tjsz(jxjdm,fjsh) values ('");
			sb.append(dm[i]);
			sb.append("','");
			sb.append(shjb);
			sb.append("')");
			sqlArr[n++] = sb.toString();
		}
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	 
	 
	 /**
	 * 批量保存荣誉称号审核级别
	 * 
	 * @param dm
	 * @param shjb
	 * @param flg
	 * @return
	 * @throws SQLException
	 */
	public boolean saveBatchShjbForRych(String[] dm, String shjb)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String[] sqlArr = new String[dm.length + 1];
		int n = 0;

		StringBuilder delBuilder = new StringBuilder();

		delBuilder.append("delete from typj_rychshjb where ");

		for (int i = 0; i < dm.length; i++) {
			delBuilder.append("rychdm='");
			delBuilder.append(dm[i]);
			delBuilder.append("'");

			if (i != dm.length - 1) {
				delBuilder.append(" or ");
			}

			sqlArr[n] = delBuilder.toString();
			n++;

			StringBuilder sb = new StringBuilder();
			sb.append("insert into typj_rychshjb(rychdm,shjb) values ('");
			sb.append(dm[i]);
			sb.append("','");
			sb.append(shjb);
			sb.append("')");
			sqlArr[n++] = sb.toString();
		}
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}


	/**
	 * 是否违纪
	 * @param xh
	 * @param xn
	 * @return
	 */
	protected String getIswj(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		
		String sql = "select case when count>0 then 'true' else 'false' end iswj " +
					 "from (select count(*) count from wjcfb where xh=? and xn=?)";
		
		return dao.getOneRs(sql, new String[] {map.get("xh"),map.get("xn")}, "iswj");
	}
	
	
	/**
	 * 条件列表
	 * @param map
	 * @return
	 */
	protected List<HashMap<String, String>> getTjList(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		String sql = "select * from zjcm_pjpy_tjsz where xn=? and lx=? and jxjdm=?";

		return dao.getList(sql, new String[] { map.get("xn"), map.get("lx"),
				map.get("jxjdm") }, new String[] { "tjzd", "tjlx", "tjz" });
	}
	
	/**
	 * 综测分成绩 
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> getZcf(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(a.fs,0) fs,a.pm,(select mc from pjpy_zctjszb b where a.dm=b.dm) mc ");
		sql.append("from pjpy_zhszcpb a where jb='2' and xh=? and xn=? union ");
		sql.append("select nvl(fs,0) fs,pm,'总分' mc from pjpy_zhszcpb where jb='1' and xh=?");
		sql.append(" and xn=?");
		
		return dao.getList(sql.toString(), new String[] { map.get("xh"),
				map.get("xn"), map.get("xh"), map.get("xn") }, new String[] {
				"fs", "pm", "mc" });
	}
	
	/**
	 * 总分成绩 
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> getZf(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select zyjd,zypm,zypmbl,zpmbl from view_pjpy_guizhdx_cjpm  where xh=? and xn = ?");
		return dao.getList(sql.toString(), new String[] { map.get("xh"), Base.getJxjsqxn() }, new String[] {
				"zyjd","zypm", "zypmbl", "zpmbl" });
	}
	
	
	/**
	 * 最低成绩
	 * @param map
	 * @return
	 */
	protected String getMincj(HashMap<String, String> map) {
		
		DAO dao = DAO.getInstance();
		String sql = "select min(cj) cj from view_zhhcjb where xh=? and xn=?";
		
		return dao.getOneRs(sql, new String[] {map.get("xh"),map.get("xn")}, "cj");
	}
	
	
	/**
	 * 不及格门次
	 * @param map
	 * @return
	 */
	protected String getBjgmc(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		
		String sql = "select count(*) count from cjb where cj<60 and xh=? and xn=?";
		return dao.getOneRs(sql, new String[] {map.get("xh"),map.get("xn")}, "count");
	}
	
	
	
	/**
	 * 宁波城市－－评奖学年学绩分在班级的前百分之几
	 * @param map
	 * @return
	 */
	protected String getXjfPm(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,trunc(a.r/(select count(*) from view_xsjbxx ");
		sql.append("where bjdm=(select bjdm from xsxxb where xh=?))*100,2) pm from(");
		sql.append("select rownum r,a.* from(");
		sql.append("select a.*,nvl(b.zycj,'0') zycj from (select xh,xm from xsxxb ");
		sql.append("where bjdm=(select bjdm from xsxxb where xh=?) ) a ");
		sql.append("left join (select xh,zycj from view_nbcs_xjf where xn=?) b ");
		sql.append("on a.xh=b.xh order by zycj desc");
		sql.append(") a) a where a.xh=?");
		
		return dao.getOneRs(sql.toString(), new String[] {map.get("xh"),
							map.get("xh"),map.get("xn"),map.get("xh")}, "pm");
	}
	
	
	/**
	 * 宁波城市－－获取学绩分及排名 
	 * @param map
	 * @return
	 */
	protected HashMap<String,String> getXjf(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (select rownum xjfpm,a.* from(select * from (");
		sql.append("select a.*,nvl(b.zycj,'0') xjfcj from (select xh,xm from xsxxb ");
		sql.append("where bjdm=(select bjdm from xsxxb where xh=?) ) a ");
		sql.append("left join (select xh,zycj from view_nbcs_xjf where xn=?) b ");
		sql.append("on a.xh=b.xh) order by xjfcj desc");
		sql.append(") a) where xh=?");
		
		return dao.getMap(sql.toString(), new String[] {map.get("xh"),
							map.get("xn"),map.get("xh")}, new String[] {"xjfcj","xjfpm"});
	}
	
	
	/**
	 * 判断必修课是否重修
	 * @param map
	 * @return
	 */
	protected boolean iscx(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		
		String sql = "select count(*) count from cjb where kcxz like '%必修%' and cxcj is not null and xh=? and xn=?";
		String count = dao.getOneRs(sql, new String[] {map.get("xh"),map.get("xn")}, "count");
		
		if (Integer.valueOf(count)>0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 学生辅助信息
	 * @param xh
	 * @return
	 */
	protected HashMap<String, String> getXsfzxx(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xsfzxxb where xh=?";
		String[] colList = new String[] {"srly","jtzsr","jtrjsr","jtszd","yb","hkszd"};
		
		return dao.getMap(sql, new String[] {xh}, colList);
	}
	
	/**
	 * 获取学生成绩排名表信息
	 */
	public HashMap<String,String>getCjpmXx(String xh){
		DAO dao =DAO.getInstance();
		String sql= "select * from  view_pjpy_guizhdx_cjpm  where xh=? and xn=?";
		String[] colList = new String[] {"zyjd","zpmbl","zypm","zypmbl"};
		return dao.getMap(sql, new String[]{xh,Base.getJxjsqxn()}, colList);
	}
	
	
	/**
	 * 本年级、专业总人数
	 * @param xh
	 * @return
	 */
	public String getZyrs(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) count from view_xsjbxx where nj=(select nj from view_xsjbxx where xh=?) and xydm=(select xydm from view_xsjbxx where xh=?)";
		
		return dao.getOneRs(sql, new String[] {xh,xh}, "count");
	}
	
	
	/**
	 * 查询班级学生评奖评优信息
	 * @param map 里面key必须要有 bjdm,xn
	 * @return {"学年", "班级", "获奖奖项", "获奖人数"}
	 */
	public List<String[]> getStuPjpyxx(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("bjdm")) && StringUtils.isNotNull(map.get("xn"))) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("select xn,bjmc,jxjmc mc,(count(xh)) rs from  ");
			sql.append("(select a.xn,a.xh,a.bjmc,a.bjdm,a.jxjmc,");
			sql.append("case when shjb=1 then a.shzt else a.xxsh end shzt from view_typj_jxjsqb a) ");
			sql.append("where xn=? and bjdm=? and shzt='通过'  group by xn,bjmc,jxjmc");
			sql.append(" union all ");
			sql.append("select xn,bjmc,rychmc mc,(count(xh)) rs from ");
			sql.append("(select a.xn,a.xh,a.bjmc,a.bjdm, a.rychmc,");
			sql.append("case when shjb=1 then a.shzt else a.xxsh end shzt from view_typj_rychsq a) ");
			sql.append("where xn=? and bjdm=? and shzt='通过' group by xn,bjmc,rychmc");
			
			return dao.rsToVator(sql.toString(),
							new String[] { map.get("xn"), map.get("bjdm"),map.get("xn"), map.get("bjdm") },
							new String[] {"xn", "bjmc", "mc", "rs"});
		} else {
			System.out.println("传入参数中,班级代码,学年不能为空!!!!!!");
			return null;
		}
	}
	
	/**
	 * 查询单个学生成绩信息
	 * @param map 里面key必须要有 xh.  
	 *            如果传入xn,xq 则根据所传入的xn,xq进行查询,否则查询学生所有的处分信息
	 * @return {学号,姓名,学年,课程名称,课程性质,成绩,补考成绩}
	 */
	public List<HashMap<String,String>> queryStuCjxx(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		
		if (!map.isEmpty() && StringUtils.isNotNull(map.get("xh"))) {
			StringBuilder whereSql = new StringBuilder("");
			if (StringUtils.isNotNull(map.get("xn"))) {
				whereSql.append(" and xn='");
				whereSql.append(map.get("xn"));
				whereSql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xq"))) {
				whereSql.append(" and xq='");
				whereSql.append(map.get("xq"));
				whereSql.append("'");
			}
			if (StringUtils.isNotNull(map.get("xh"))) {
				whereSql.append(" and xh='");
				whereSql.append(map.get("xh"));
				whereSql.append("'");
			}
			//whereSql.append(" order by bjdm,xh");
			return dao.getList("select xh,xm,xn,xq,kcmc,kcxz,cj,bkcj from view_cjb a " +
					"where 1=1 " + whereSql.toString(),
							new String[] {},
							new String[] {"xh","xm","xn","xq","kcmc","kcxz","cj","bkcj"});
		} else {
			System.out.println("传入参数中,学号不能为空!!!!!!");
			return null;
		}
	}
	
	
	/**
	 * 根据奖学金代码获取相应的问卷
	 * @param jxjdm
	 * @return
	 */
	public String getWjid(String jxjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select wjid from jxjdmb where jxjdm=?";
		
		return dao.getOneRs(sql, new String[] {jxjdm}, "wjid");
	}
	
	/**
	 * 判断记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select count(*) num from ",
				          tableName,
				          " where ",
				          pk,
				          "=?");
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return Integer.parseInt(num) >0 ? true : false;
	}


	

	
	/**
	 * 补考科目数
	 * @param map
	 * @return
	 */
	public String getBkkms(HashMap<String,String> map) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when bks=0 then '无' else to_char(bks) end bks from (");
		sql.append("select count(bkcj) bks  from cjb where 1=1 ");

		if (!Base.isNull(map.get("xh"))) {
			sql.append(" and xh='");
			sql.append(map.get("xh"));
			sql.append("'");
		}
		
		if (!Base.isNull(map.get("xn"))) {
			sql.append(" and xn='");
			sql.append(map.get("xn"));
			sql.append("'");
		}
		
		sql.append(")");
		return dao.getOneRs(sql.toString(), new String[] {}, "bks");
	}
	
	
	
	/**
	 * 贵州大学综测和分数
	 * @param map
	 * @return
	 */
	public HashMap<String,String> getGuizhdxCj(HashMap<String,String> map){
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from view_pjpy_guizhdx_cjpm where 1=1");
		
		if (!Base.isNull(map.get("xh"))) {
			sql.append(" and xh='");
			sql.append(map.get("xh"));
			sql.append("'");
		}
		
		if (!Base.isNull(map.get("xn"))) {
			sql.append(" and xn='");
			sql.append(map.get("xn"));
			sql.append("'");
		}
		
		return dao.getMap(sql.toString(), new String[] {}, new String[] {"zycj","zypm","zpm","fs"});
	}
	
	/**
	 * 查询奖学金上报信息
	 * @param model
	 * @param output
	 * @return List<String[]>
	 * */
	public List<String[]> selectJxjsb(GuizhdxForm model, String[] output,User user){
		DAO dao = DAO.getInstance();
		Pages pages = model.getPages();
		String sql = "";
		//查询条件
		String whereSql = getWhereSql(model).toString();
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		//主查询语句
		String mainSql = getJxjsbMainSql(model.getQueryequals_xn(), model.getQueryequals_nd(), model.getQueryequals_xq());
		if("true".equalsIgnoreCase(user.getFdyQx())){//辅导员
			whereSql += " and exists (select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + DealString.replaceImmitStr(user.getUserName())+ "')";
		}
		//查询总记录数
		sql = "select count(*) num from(" + mainSql + whereSql+")";
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(sql, input, "num")));
		//查询记录
		sql = StringUtils.joinStr("select a.* from(select a.*,rownum r from(",
				mainSql,
				")a ",
				whereSql,
				") a where r>",
				pages.getStart()+"",
				" and r<=",
				(pages.getStart()+pages.getPageSize())+"");
		return dao.rsToVator(sql, input, output);
	}
	
	/**
	 * 获取奖学金上报主查询语句
	 * @return String
	 * */
	public String getJxjsbMainSql(String xn, String nd, String xq){
		//主查询语句
		xn = StringUtils.isNull(xn) ? Base.getJxjsqxn() : xn;
		xq = StringUtils.isNull(xq) ? Base.getJxjsqxq() : xq;
		nd = StringUtils.isNull(nd) ? Base.getJxjsqnd() : nd;
		return StringUtils.joinStr("select pk,xh,xn,nd,xq,xqmc,jxjdm,jxjmc,fdysh,",
				"xysh,xxsh,shzt,(case substr(je,0,1) when '.' then to_char('0'||je) else to_char(je) end) je,xm,xb,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xz ",
				"from (select a.xh||a.xn||a.xq||a.jxjdm pk,a.xh,a.xn,a.nd,a.xq,",
				"(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.jxjdm,",
				"a.jxjmc,a.fdysh,a.xysh,a.xxsh,a.shzt,",
				"(case a.jxjmc when '单项奖' then a.je when '单项奖学金' then a.je when '特别奖' then a.je when '特别奖学金' then a.je else (select jlje from jxjdmb c where a.jxjdm=c.jxjdm)end)je,",
				"a.xm,a.xb,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xz from(",
				"select (select jxjmc from jxjdmb c where a.jxjdm=c.jxjdm)jxjmc, ",
			    "b.xh,b.xn,b.xq,b.nd,a.jxjdm,a.fdysh,a.xysh,",
			    "a.xxsh,a.shzt,a.je,b.xm,b.xb,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,",
				"b.bjmc,b.nj,b.xz from (select a.*,'",
				xn,"' xn,'",nd,"'nd,'",xq,"' xq from view_xsjbxx a) b left join typj_jxjsqb a on a.xh=b.xh and b.xn=a.xn and a.nd=b.nd and a.xq=b.xq",
				")a) a ");
	}
	
	/**
	 * 保存奖学金上报
	 * @param pkValues
	 * @param model
	 * @param user
	 * @return boolean
	 * */
	public boolean saveJxjsb(String[] pkValues,GuizhdxForm model, User user){
		DAO dao = DAO.getInstance();
		boolean result = false;
		String[] sqlArr = new String[pkValues.length*2];
		int index = 0;
		for(int i=0; i<pkValues.length; i++){
			String pkV = dao.getOneRs("select xh||xn||xq pk from (" + getJxjsbMainSql(model.getQueryequals_xn(), model.getQueryequals_nd(), model.getQueryequals_xq()) + ")a where pk=?", new String[]{pkValues[i]}, "pk");
			sqlArr[index++] = "delete from typj_jxjsqb a where xh||xn||xq||jxjdm='" + pkValues[i] + "'";
			sqlArr[index++] = "insert into typj_jxjsqb(xh,xn,nd,xq,jxjdm,je,xysh)select xh,xn,nd,xq,'" + model.getJxjdm() +"','" + model.getJxjje()+ "','通过' from (" + getJxjsbMainSql(model.getQueryequals_xn(), model.getQueryequals_nd(), model.getQueryequals_xq())+")a where pk='" + pkV + "' and rownum=1";
		}
				
		try {
			result = dao.checkBatch(dao.runBatch(sqlArr,"typj_jxjsqb",user));			
		} catch (SQLException e) {
			result = true;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 取消奖学金上报
	 * @param pkValues
	 * @param user
	 * @return boolean
	 * */
	public boolean delJxjsb(String[] pkValues, User user){
		DAO dao = DAO.getInstance();
		boolean result = false;
		String[] sqlArr = new String[pkValues.length];
		for(int i=0; i<pkValues.length; i++){
			sqlArr[i] = "delete from typj_jxjsqb a where xh||xn||xq||jxjdm='" + pkValues[i] + "'";
		}		
		
		try {
			result = dao.checkBatch(dao.runBatch(sqlArr,"typj_jxjsqb",user));			
		} catch (SQLException e) {
			result = true;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 检测是否可上报评奖学金
	 * */
	public boolean checkJxjsb(String[] pkValues,HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		for(int i=0; i<pkValues.length; i++){
			String num = dao.getOneRs("select count(*)count from (" 
					+ getJxjsbMainSql(map.get("xn"), map.get("nd"), map.get("xq")) 
					+ ") where pk=? and jxjdm is not null", new String[]{pkValues[i]}, "count");
			if(Integer.parseInt(num)>0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 检测是否可取消综合测评奖学金上报
	 * */
	public boolean checkQxJxjsb(String[] pkValues,HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		for(int i=0; i<pkValues.length; i++){
			String num = dao.getOneRs("select count(*)count from (" 
					+ getJxjsbMainSql(map.get("xn"), map.get("nd"), map.get("xq")) 
					+ ") where pk=? and jxjdm is not null and shzt='通过'", new String[]{pkValues[i]}, "count");
			if(Integer.parseInt(num)>0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 检测学院人数是否超限
	 * @param map
	 * @param sbrs
	 * @return int
	 * */
	public int checkXyjxjsbrs(HashMap<String, String> map, int sbrs){
		DAO dao = DAO.getInstance();
		int fprs = 0;
		int ysbrs = 0;
		String[] input = new String[]{map.get("xydm"), map.get("xn"),map.get("jxjdm"), map.get("nj")};
		String sql = "select nvl(jxjrs,0) fprs from xyjxjrs where bmlb='xydm' and bmdm=? and key='jxj' and xn=? and jxjdm=? and nj=?";
		fprs = Integer.parseInt(dao.getOneRs(sql, input, "fprs"));
		
		sql = "select count(*) ysbrs from view_typj_jxjsqb where xydm=? and xn=? and jxjdm=? and nj=?";
		ysbrs = Integer.parseInt(dao.getOneRs(sql, input, "ysbrs"));
				
		return fprs - (ysbrs+sbrs);
	}
	
	/**
	 * 判断剩余金额是否满足
	 * @param map
	 * @param sbrs
	 * @return float
	 * */
	public float checkJxjje(HashMap<String, String> map, int sbrs){
		DAO dao = DAO.getInstance();		
		String sql = "select nvl(sum(je),0)je from view_typj_jxjsqb a where (jxjmc='特别奖' or jxjmc='特别奖学金' or jxjmc='单项奖' or jxjmc='单项奖学金') and xydm=? and nj=? and xn=?";
		//支出金额
		float zcje = Float.parseFloat(dao.getOneRs(sql, new String[]{map.get("xydm"), map.get("nj"), map.get("xn")}, "je"));
		//分配金额
		sql = "select nvl(jxjje,0)jxjje from pjpy_ty_xyjxjjeszb a where xydm=? and nj=? and xn=?";
		String fpjeStr = dao.getOneRs(sql, new String[]{map.get("xydm"), map.get("nj"), map.get("xn")}, "jxjje");
		fpjeStr = StringUtils.isNull(fpjeStr) ? "0" : fpjeStr;
		float fpje = Float.parseFloat(fpjeStr);
		//支付金额
		String jxjje = StringUtils.isNull(map.get("jxjje")) ? "0" : map.get("jxjje");
		float zfje = sbrs*Float.parseFloat(jxjje);
		
		return fpje - (zcje+zfje);
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param hasNull 
	 * @author lr
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue, boolean hasNull){
		DAO dao = DAO.getInstance();	
		if (Base.isNull(msg)) {
			msg = "----请选择-----";
		}

		StringBuffer sql = new StringBuffer();
		if(hasNull){
			sql.append("select '' dm, '" + msg + "'mc from dual union");
		}
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");

		return dao.getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
}

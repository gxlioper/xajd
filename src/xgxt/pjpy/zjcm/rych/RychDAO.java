package xgxt.pjpy.zjcm.rych;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class RychDAO extends CommonQueryDAO{ 
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 获得评奖学年学期排名
	 */
	public String getPm(String xh,String xn,String xq,String pm) {
		DAO dao = new DAO();
		xq = getXqmc(xq);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select xh,(rank() over(partition by xn,xq,bjdm order by to_number("+pm+") ");
		sql.append("desc nulls last)) pm from view_zjcm_zhf where xn = ? and xq = ?) where xh = ?");   
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,xh}, "pm");
	}
	
	/**
	 * 获得学期名称
	 * 
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqmc from xqdzb where xqdm=?",
				new String[] { xqdm }, "xqmc");
	}

	public List<HashMap<String, String>> getRychList() {
		/**
		 * 获取荣誉称号列表
		 */
		DAO dao = DAO.getInstance();
		String sql = "select rychdm dm, rychmc mc from rychdmb";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 判断荣誉称号条件
	 */
	public String Rychtj(RychModel model,String zhpm) {
		DAO dao = new DAO();
		String msg = "";
		
		String xh = model.getXh();
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String rychdm = model.getRychdm();
		String bjgkms = model.getBjgkms();
		
		String sql = "select * from zjcm_pjpy_tjsz where jxjdm = ? and xn = ? and xq = ? and  szlx= 'rych'";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {
				rychdm, xn, xq }, new String[] { "tjzd", "tjlx", "tjz" });
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				if ("zhpm".equalsIgnoreCase(map.get("tjzd"))) {
					String tjlx = map.get("tjlx");
					float tjpm = Float.parseFloat(getTjpm(xh, map.get("tjz")));
					float pm = Float.parseFloat(zhpm);
					if (">".equalsIgnoreCase(tjlx)) {
						if (pm < tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if (">=".equalsIgnoreCase(tjlx)) {
						if (pm <= tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("=".equalsIgnoreCase(tjlx)) {
						if (pm == tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<".equalsIgnoreCase(tjlx)) {
						if (pm > tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<=".equalsIgnoreCase(tjlx)) {
						if (pm >= tjpm) {
							continue;
						} else {
							msg = "该学生综合素质排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				} else if ("dypm".equalsIgnoreCase(map.get("tjzd"))) {
					String tjlx = map.get("tjlx");
					float tjpm = Float.parseFloat(getTjpm(xh, map.get("tjz")));
					float pm = Float.parseFloat(getPm(xh, xn, xq, "dyf"));
					if (">".equalsIgnoreCase(tjlx)) {
						if (pm < tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if (">=".equalsIgnoreCase(tjlx)) {
						if (pm <= tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("=".equalsIgnoreCase(tjlx)) {
						if (pm == tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<".equalsIgnoreCase(tjlx)) {
						if (pm > tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<=".equalsIgnoreCase(tjlx)) {
						if (pm >= tjpm) {
							continue;
						} else {
							msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				} else if ("zypm".equalsIgnoreCase(map.get("tjzd"))) {
					String tjlx = map.get("tjlx");
					float tjpm = Float.parseFloat(getTjpm(xh, map.get("tjz")));
					float pm = Float.parseFloat(getPm(xh, xn, xq, "zyf"));
					if (">".equalsIgnoreCase(tjlx)) {
						if (pm < tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if (">=".equalsIgnoreCase(tjlx)) {
						if (pm <= tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("=".equalsIgnoreCase(tjlx)) {
						if (pm == tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<".equalsIgnoreCase(tjlx)) {
						if (pm > tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					} else if ("<=".equalsIgnoreCase(tjlx)) {
						if (pm >= tjpm) {
							continue;
						} else {
							msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				} else if ("bjgkm".equalsIgnoreCase(map.get("tjzd"))) {
					if (Base.isNull(bjgkms)) {
						continue;
					} else {
						if (Integer.parseInt(bjgkms) > 0) {
							msg = "该学生有不及格科目,不满足本奖学金的申请条件,请确认！！！";
							return msg;
						}
					}
				}
			}
		}
		return msg;
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 获得条件排名
	 */
	public String getTjpm(String xh, String tjz) {
		DAO dao = new DAO();
		StringBuffer sql = new StringBuffer();
		sql.append("select num * " + tjz + " / 100 tjpm from (select count(*) num ");
		sql.append("from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh = ?))");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "tjpm");
	}

	/**
	 * 荣誉称号申请信息保存
	 */
	public boolean serv_rychSave(RychModel  model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xh     = model.getXh();
		String rychdm = model.getRychdm();
		String bz     = model.getBz();
		String zdlcql = model.getZdlcql();
		String zysj    = model.getZysj();
		String xn     = Base.getJxjsqxn();
        String xq     = Base.getJxjsqxq();
		String  pk    = "xh||xn||xqdm||rychdm";
		String sql    = "";		
		String tag = dao.getOneRs("select count(xh)cout from zjcm_rychsqb where "+pk+"=?",new String[]{xh+xn+xq+rychdm},"cout");
		if("0".equalsIgnoreCase(tag)){
			sql = " insert into zjcm_rychsqb(xh,xn,rychdm,bz,zysj,zdlcql,xqdm) values(?,?,?,?,?,?,?) ";
		}else{
			sql = " update zjcm_rychsqb set xh=?,xn=?,rychdm=?,bz=?,zysj=?,zdlcql=?,xqdm=? where "+pk+"='"+xh+xn+xq+rychdm+"'";
		}
		done = dao.runUpdate(sql, new String[]{xh,xn,rychdm,bz,zysj,zdlcql,xq});
		return done;
	}

	public boolean saveRychSh(String[] checkVal, String shrych, String shzt, String userType) throws SQLException {

		DAO dao       = DAO.getInstance();
		boolean flg = false;
		
		String sh = "tg".equalsIgnoreCase(shzt) ? "通过" : "未通过";
		String sql="";
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());
		String shr = "";
		String shsj = "";
		
		if ("xy".equalsIgnoreCase(userType)) {
			shr="xysh";
			shsj="xyshsj";
		} else {
			shr="xxsh";
			shsj="xxshsj";
		}
		
		String[] inssql = new String [checkVal.length];
		for (int i = 0; i < checkVal.length; i++) {
			String pk = checkVal[i];
			sql = "update zjcm_rychsqb set " + shr + " = '" + sh + "'," + shsj
					+ "='" + time + "' where xh||xn||xqdm||rychdm ='" + pk + "'";
			inssql[i] = sql;
		}

		int[] res = dao.runBatch(inssql);
		
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}

	public boolean delRychSq(String[] checkVal) throws SQLException {
		DAO dao       = DAO.getInstance();
		boolean flg = false;

		StringBuffer sb = new StringBuffer();
		String sql = "";

		for (int i = 0; i < checkVal.length; i++) {
			String pk = checkVal[i];
			sql = "delete from zjcm_rychsqb where xh||xn||xqdm||rychdm ='" + pk
					+ "'";
			sb.append(sql);
			sb.append("!!#!!");
		}

		String[] delsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(delsql);

		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}

	public ArrayList<String[]> getRychSqList(String tableName, RychModel model, String[] colList, String userType, String string) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		String [] queryList = new String []{"xydm","zydm","bjdm","xqdm","nj","xn","rychdm","xysh","xxsh"};
		String [] queryLikeList = new String []{"xh","xm"};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList,queryLikeList,model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(), myQuery.getInputList(), colList, "", model);
	}

	public boolean saveRychsh(RychModel model, String pk, String shzt, String userType, HttpServletRequest request) throws Exception {

		String[] col = null;
		String[] value = null;
		String sh = "tg".equalsIgnoreCase(shzt) ? "通过" : "未通过";

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());
		if ("xy".equalsIgnoreCase(userType)) {
			col = new String[] { "xyshyj", "xyshsj", "xysh" };
			value = new String[] { DealString.toGBK(model.getXyshyj()), time, sh };
		} else {
			col = new String[] { "xxshyj", "xxshsj", "xxsh" };
			value = new String[] { DealString.toGBK(model.getXxshyj()), time, sh };
		}

		boolean flg = StandardOperation.update("zjcm_rychsqb", col, value,
				"xh||xn||xqdm||rychdm", pk, request);

		return flg;
	}

	public HashMap<String, String> getXsRychXx(String pk) {
			String[] colList = new String[] { "bjdm","bjmc","bz","csrq","mzmc","nj","pk","rychdm","rychmc","sqsj","xb","xh","xm","xn","xqdm","xqmc",
					"xxsh","xxshsj","xxshyj","xydm","xymc","xysh","xyshsj","xyshyj","zdlcql","zw","zydm","zymc","zysj","zzmmmc" };
			return commonQueryOne("view_zjcm_rychsq", colList, "pk", pk);
	}
	
	/**
	 * 获得违纪处分列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select * from view_wjcf where xh = ? and xxsh = '通过'";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { xh }, new String[] {"xn","xqmc","cflbmc"});
		return list;
	}

	/**
	 * 获得打印信息
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getPrintXx(HashMap<String, String> rs) {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String xh = rs.get("xh");
		String xn = Base.getJxjsqxn();
		String xq= Base.getJxjsqxq();
		String xqmc= Base.getJxjsqxqmc();
		rs.put("xn",xn);
		rs.put("xqmc",xqmc);
		String zw = dao.getOneRs("select bjgbmc from view_bjgbxx  where xh = ? ", new String []{xh}, "bjgbmc");
		rs.put("zw", zw);
		
		if(xh ==null||xh.equalsIgnoreCase("")){
			return rs;
		}else{
			rs = CommonQueryDAO.commonQueryOne3("view_stu_details", new String[]{"xh","xm","xb","xymc","zymc","bjmc","zzmmmc","mzmc","csrq"}, "xh", xh,rs,"");
			ArrayList<String[]> cjList =  CommonQueryDAO.commonQueryNotFy("cjb", " where xh = ? and xn = ? and xq = ?" , new String []{xh,xn,xq}, new String []{"kcmc","cj"}, "");
			int cjListLeng = 0;
			double cjh = 0.0;
			if(cjList!=null){
				cjListLeng = cjList.size();
			}
			for(int i=0;i<21;i++){
				if(i<cjListLeng){
					rs.put("kcmc"+i,cjList.get(i)[0]);
					rs.put("cj"+i,cjList.get(i)[1]);
					cjh+=Double.parseDouble(cjList.get(i)[1]);
				}else{
					rs.put("kcmc"+i,"");
					rs.put("cj"+i,"");
				}
			}
			if(cjListLeng==0){
				rs.put("pjcj", "");
			}else{
				rs.put("pjcj", ((Long)Math.round(cjh/cjListLeng)).toString());
			}
			return rs;
		}
	}

	public String getRychmc(String rychdm) {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql = "select rychmc from rychdmb where rychdm = ?";
		return dao.getOneRs(sql,new String []{rychdm},"rychmc");
	}
}

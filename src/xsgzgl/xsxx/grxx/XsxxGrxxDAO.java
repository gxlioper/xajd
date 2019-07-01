package xsgzgl.xsxx.grxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.rcsw.qjgl.RcswQjglForm;
import xsgzgl.xsxx.model.CsszModel;
import xsgzgl.xsxx.model.ZdxgModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个人信息_DAO类
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

public class XsxxGrxxDAO extends CommDAO {
	
	/**
	 * 获得学生信息
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public HashMap<String, String> getXsxxInfo(XsxxGrxxForm model,
			List<HashMap<String, String>> zdList) {

		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		
		ArrayList<String> outValue = new ArrayList<String>();
		if (zdList != null && zdList.size() > 0) {
			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				String zd = map.get("zd");
				String bm = map.get("bm");
				
				if (i != 0) {
					sql.append(",");
				}

				if ("bjdm".equalsIgnoreCase(zd)) {// 所在部门
					sql.append("nj||'(年级)'||' '");
					sql.append("||xymc||'("+Base.YXPZXY_KEY+")'||' '  ");
					sql.append("||zymc||'(专业)'||' '");
					sql.append("||bjmc||'(班级)' bjdm ");
				} else if ("mz".equalsIgnoreCase(zd)) {// 民族
					sql.append("(select b.mzmc from mzdmb b where a.mz=b.mzdm) mz ");
				} else if ("zzmm".equalsIgnoreCase(zd)) {// 政治面貌
					sql.append("(select b.zzmmmc from zzmmdmb b where a.zzmm=b.zzmmdm) zzmm ");
				} else if ("pycc".equalsIgnoreCase(zd)) {// 培养层次
					sql.append("(select b.pyccmc from xg_xsxx_pyccdmb b where a.pycc=b.pyccdm) pycc ");
				} else if ("hkszd".equalsIgnoreCase(zd)) {// 户口所在地
					sql.append("(select c.sydq from dmk_sydq c where c.sydqdm = substr(a.hkszd, 0, 6)) || ' ' ||");
					sql.append("(select d.qxmc from dmk_qx d where d.qxdm = substr(a.hkszd, 8, 6)) || ' '||");
					sql.append("(select e.qxmc from dmk_qx e where e.qxdm = substr(a.hkszd, 15, 6)) hkszd ");
				} else if ("jg".equalsIgnoreCase(zd)) {//籍贯
					sql.append("(select c.sydq from dmk_sydq c where c.sydqdm = substr(a.jg, 0, 6)) || ' ' ||");
					sql.append("(select d.qxmc from dmk_qx d where d.qxdm = substr(a.jg, 8, 6)) || ' '||");
					sql.append("(select e.qxmc from dmk_qx e where e.qxdm = substr(a.jg, 15, 6)) jg ");
				} else if ("syd".equalsIgnoreCase(zd)) {//生源地
					sql.append("(select c.sydq from dmk_sydq c where c.sydqdm = substr(a.syd, 0, 6)) || ' ' ||");
					sql.append("(select d.qxmc from dmk_qx d where d.qxdm = substr(a.syd, 8, 6)) || ' '||");
					sql.append("(select e.qxmc from dmk_qx e where e.qxdm = substr(a.syd, 15, 6)) syd ");
				} else if ("yhdm".equalsIgnoreCase(zd)) {// 银行名称
					sql.append("(select b.yhmc from dmk_yh b where a.yhdm=b.yhdm) yhdm ");
				} else if ("pyfs".equalsIgnoreCase(zd)) {// 培养方式
					sql.append("(select b.pyfsmc from xg_xsxx_pyfsdmb b where a.pyfs=b.pyfsdm) pyfs ");
				} else if ("kslb".equalsIgnoreCase(zd)) {// 考生类别
					sql.append("(select b.kslbmc from xg_xsxx_kslbdmb b where a.kslb=b.kslbdm) kslb ");
				} else if ("rxfs".equalsIgnoreCase(zd)) {// 入学方式
					sql.append("(select b.rxfsmc from xg_xsxx_rxfsdmb b where a.rxfs=b.rxfsdm) rxfs ");
				} else if ("jtcy1_mz".equalsIgnoreCase(zd)) {// 家庭成员1_民族
					sql.append("(select b.mzmc from mzdmb b where c.jtcy1_mz=b.mzdm) jtcy1_mz ");
				} else if ("jtcy1_zzmm".equalsIgnoreCase(zd)) {// 家庭成员1_政治面貌
					sql.append("(select b.zzmmmc from zzmmdmb b where c.jtcy1_zzmm=b.zzmmdm) jtcy1_zzmm ");
				} else if ("jtcy2_mz".equalsIgnoreCase(zd)) {// 家庭成员2_民族
					sql.append("(select b.mzmc from mzdmb b where c.jtcy2_mz=b.mzdm) jtcy2_mz ");
				} else if ("jtcy2_zzmm".equalsIgnoreCase(zd)) {// 家庭成员2_政治面貌
					sql.append("(select b.zzmmmc from zzmmdmb b where c.jtcy2_zzmm=b.zzmmdm) jtcy2_zzmm ");
				} else if ("jtcy3_mz".equalsIgnoreCase(zd)) {// 家庭成员3_民族
					sql.append("(select b.mzmc from mzdmb b where c.jtcy3_mz=b.mzdm) jtcy3_mz ");
				} else if ("jtcy3_zzmm".equalsIgnoreCase(zd)) {// 家庭成员3_政治面貌
					sql.append("(select b.zzmmmc from zzmmdmb b where c.jtcy3_zzmm=b.zzmmdm) jtcy3_zzmm ");
				} else {
					sql.append(bm + "." + zd);
				}
				
				outValue.add(zd);
			}
		}
		sql.append(" from view_xsxxb a ");
		sql.append(" left join xsfzxxb c on a.xh = c.xh ");
		sql.append(" where a.xh = ? ");

		DAO dao = DAO.getInstance();

		if (zdList != null && zdList.size() > 0) {

		}
		
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, outValue.toArray(new String[]{}));

		return map;
	}
	
	/**
	 * 保存字段修改
	 * 
	 * @author luojw
	 */
	public boolean saveZdxg(XsxxGrxxForm model, User user) {

		DAO dao = DAO.getInstance();
		
		ZdxgModel zdxgModel = model.getZdxgModel();
		
		String xh = zdxgModel.getXh();
		String sqid = zdxgModel.getSqid();
		String xgr = user.getUserName();
		String xgzd = zdxgModel.getXgzd();
		String ysz = zdxgModel.getXgzd();
		String xgz = zdxgModel.getXgz();
		
		String bm = dao.getOneValue("xg_xsxx_grxx_zdszb", "bm", "zd", xgzd);
		String tableName = ("a".equalsIgnoreCase(bm)) ? "view_xsxxb" : "xsfzxxb";
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_grxx_zdxgb");
		sql.append("(sqid,xh,xgr,xgzd,ysz,xgz)");
		sql.append("select '' sqid, xh ");
		sql.append(",'" + xgr + "' xgr ");
		sql.append(",'" + xgzd + "' xgzd ");
		sql.append("," + ysz + " ysz ");
		sql.append(",'" + xgz + "' xgz ");
		sql.append("from ");
		sql.append(tableName);
		sql.append(" a ");
		sql.append("where a.xh = ? ");
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{xh});
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获得所在部门
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public String getSzbm(String bjdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("nj||'(年级)'||' '");
		sql.append("||xymc||'("+Base.YXPZXY_KEY+")'||' '  ");
		sql.append("||zymc||'(专业)'||' '");
		sql.append("||bjmc||'(班级)' szbm ");
		sql.append("from view_njxyzybj ");
		sql.append("where bjdm = ? ");

		String szbm = dao.getOneRs(sql.toString(), new String[] { bjdm },
				"szbm");

		return szbm;
	}
	
	/**
	 * 获得省市县
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getSsx(String ssx) {

		DAO dao = DAO.getInstance();
		String[] ssxArr = ssx.split("/");
		String sheng = "";
		String shi = "";
		String xian = "";
		if(null!=ssxArr){
			if(ssxArr.length==1){
				sheng = ssx.split("/")[0];
			}else if(ssxArr.length==2){
				sheng = ssx.split("/")[0];
				shi = ssx.split("/")[1];
			}else if(ssxArr.length==3){
				sheng = ssx.split("/")[0];
				shi = ssx.split("/")[1];
				xian = ssx.split("/")[2];
			}
		}

		StringBuilder sql = new StringBuilder();

		sql.append("select qxdm,qxmc ");
		sql.append("from dmk_qx ");
		sql.append("where qxdm = ? ");
		sql.append("or qxdm = ? ");
		sql.append("or qxdm = ? ");
		sql.append("order by to_number(qxdm) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { sheng, shi, xian }, new String[] { "qxmc" });

		return list;
	}

	/**
	 * 初始化字段修改表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public static Boolean initZdxgb(String xh) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append("xg_xsxx_grxx_zdxgb ");
		sql.append("where 1=1 ");
		sql.append("and sqid is null ");
		sql.append("and xh=? ");

		return dao.runUpdate(sql.toString(), new String[] { xh });
	}	
	
	/**
	 * 增加申请
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean insertXgsq(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_grxx_xgsqb ");
		sql.append("(xh,sqr,sqjg) ");
		sql.append("values(?,?,?)");

		return dao.runUpdate(sql.toString(), new String[] { model.getXh(),
				user.getUserName(), "未审核" });
	}
	
	/**
	 * 修改申请
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateXgsq(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgsqb ");
		sql.append("set sqjg = ? ");
		sql.append("where id = ? ");

		return dao.runUpdate(sql.toString(), new String[] { "未审核",
				model.getSqid() });
	}
	
	/**
	 * 修改审核
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateXgsh(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgshb ");
		sql.append("set shzt = ? ");
		sql.append(",shr = '' ");
		sql.append(",shyj = '' ");
		sql.append("where sqid = ? ");

		return dao.runUpdate(sql.toString(), new String[] { "未审核",
				model.getSqid() });
	}
	
	/**
	 * 获得申请ID
	 * 
	 * @author luojw
	 */
	public String getSqid(XsxxGrxxForm model, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.id ");
		sql.append("from xg_xsxx_grxx_xgsqb a ");
		sql.append("where exists (select 1 ");
		sql.append("from (select xh, max(sqsj) lastTime ");
		sql.append("from xg_xsxx_grxx_xgsqb ");
		sql.append("group by xh) b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.sqsj = b.lastTime) ");
		sql.append("and a.xh=? ");
		sql.append("and a.sqjg<>? ");
		sql.append("and sqsj >= (select sqkssj from xg_xsxx_grxx_szb where rownum = 1) ");
		sql.append("and sqsj <= (select sqjssj from xg_xsxx_grxx_szb where rownum = 1) ");
		
		return dao.getOneRs(sql.toString(), new String[] { model.getXh(),"审核通过" },
				"id");
	}
	
	/**
	 * 增加申请
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean insertXgsh(XsxxGrxxForm model, User user) throws Exception {

		CsszModel csszModel = model.getCsszModel();

		// 申请ID
		String sqid = model.getSqid();

		// 流程ID
		String lcid = csszModel.getLcid();
		
		// 岗位列表
		List<HashMap<String, String>> gwList = csszModel.getGwList();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_grxx_xgshb ");
		sql.append("(sqid,gwid,lcid) ");
		sql.append("values(?,?,?)");

		boolean flag = true;

		List<String[]> params = new ArrayList<String[]>();
		if (gwList != null && gwList.size() > 0) {

			for (int i = 0; i < gwList.size(); i++) {
				String[] value = new String[] { sqid,
						gwList.get(i).get("gwid"), lcid };
				params.add(value);
			}

			flag = saveArrDate(sql.toString(), params, "xg_xsxx_grxx_xgshb",
					user);
		}

		return flag;
	}
	
	/**
	 * 保存申请ID
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveSqid(XsxxGrxxForm model) throws Exception {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = model.getXh();
		// 申请ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_zdxgb a set sqid=? ");
		sql.append("where exists (select 1 ");
		sql.append("from (select xh, xgzd, max(xgsj) lastTime ");
		sql.append("from xg_xsxx_grxx_zdxgb ");
		sql.append("group by xh, xgzd) b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xgzd = b.xgzd ");
		sql.append("and a.xgsj = b.lastTime) ");
		sql.append("and a.xh=? ");
		sql.append("and a.sqid is null ");
		
		boolean flag = dao.runUpdate(sql.toString(), new String[] { sqid, xh });

		return flag;
	}

	/**
	 * 获得审核信息列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getShInfoList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();

		// 申请ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();
		sql.append("select gwid,shzt,shyj from xg_xsxx_grxx_xgshb ");
		sql.append("where sqid = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { sqid }, new String[] { "gwid", "shzt", "shyj" });

		return list;
	}
	
	/**
	 * 获得字段修改信息列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXgInfoList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();
		
		// 学号
		String xh = model.getXh();
		// 申请ID
		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();	
		sql.append("select a.xgzd zd, a.ysz,a.xgz, b.zdlx, b.source_table, b.select_dm, b.select_mc ");
		sql.append("from xg_xsxx_grxx_zdxgb a, xg_xsxx_grxx_zdszb b ");
		sql.append("where a.xgzd = b.zd ");
		sql.append("and exists (select 1 ");
		sql.append("from (select sqid, xh, xgzd, max(xgsj) lastTime ");
		sql.append("from xg_xsxx_grxx_zdxgb ");
		sql.append("group by sqid, xh, xgzd) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and a.xh = b.xh ");
		sql.append("and a.xgsj = b.lastTime ");
		sql.append("and a.xgzd = b.xgzd) ");
		sql.append("and a.xh = ? ");
		sql.append("and a.sqid = ? ");		   

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh, sqid }, new String[] { "zd", "zdlx", "xgz",
						"ysz", "source_table", "select_dm", "select_mc" });

		return list;
	}
	
	/**
	 * 获得修改字段列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXgzdList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();
		
		// 学号
		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();	
		sql.append("select a.xh,c.zd, a.xgz, c.bm ");
		sql.append("from xg_xsxx_grxx_zdxgb a, xg_xsxx_grxx_zdszb c ");
		sql.append("where a.xgzd = c.zd ");
		sql.append("and exists (select 1 ");
		sql.append("from (select xh, xgzd, max(xgsj) lastTime ");
		sql.append("from xg_xsxx_grxx_zdxgb ");
		sql.append("group by xh, xgzd) b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xgzd = b.xgzd ");
		sql.append("and a.xgsj = b.lastTime) ");
		sql.append("and a.xh = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, new String[] { "xh","zd", "xgz", "bm" });

		return list;
	}
	
	/**
	 * 复制内容往学生信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public boolean copyToXsxxb(String xh) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xsxxb ");
		sql.append("(xh,xm,xb,sfzh,nj,xjztm,xz,xydm,zydm,bjdm");
		sql.append(",ksh)");

		sql.append("select xh,xm,xbm xb,sfzh,to_char(nj) nj,xjztm,substr(xz, 0, 1) xz,bmdm xydm,zydm,bjdm");
		sql.append(",ksh ");
		sql.append("from bks_xsjbxx a ");
		sql.append("where a.xh=? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { xh });

		return flag;
	}
	
	/**
	 * 复制内容往辅助信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public boolean copyToFzxxb(String xh) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xsfzxxb ");
		sql.append("(xh)");

		sql.append("select xh ");
		sql.append("from bks_xsjbxx a ");
		sql.append("where a.xh=? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { xh });

		return flag;
	}
	
	/**
	 * 提交个人信息修改
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public boolean submitGrxx(XsxxGrxxForm model,
			List<HashMap<String, String>> zdxgList) {

		CsszModel csszModel = model.getCsszModel();

		// 是否审核
		String sfsh = csszModel.getSfsh();
		
		boolean flag = true;

		if (zdxgList != null && zdxgList.size() > 0) {
			
			String[] commitSql = new String[zdxgList.size()];
				
			for (int i = 0; i < zdxgList.size(); i++) {
				HashMap<String, String> map = zdxgList.get(i);
				
				String xh = map.get("xh");//学号
				String zd = map.get("zd");//字段
				String xgz = map.get("xgz");//修改值
				String bm = map.get("bm");//别名
				
				StringBuilder sql = new StringBuilder();
				
				String tableName = ("a".equalsIgnoreCase(bm)) ? "xsxxb"
						: "xsfzxxb";
				
				sql.append(" update ");
				sql.append(tableName);
				sql.append(" a set ");
				sql.append(zd);
				sql.append(" ='" + xgz + "' ");
				sql.append(" where xh = '" + xh + "' ");
				if ("是".equalsIgnoreCase(sfsh)) {
					
					String sqid = model.getSqid();
					String shgw = model.getShgw();
					
					sql.append(" and exists (select 1 ");
					sql.append(" from (select b.xh, c.sqid, c.gwid, c.shzt ");
					sql.append(" from xg_xsxx_grxx_xgsqb b, xg_xsxx_grxx_xgshb c ");
					sql.append(" where b.id = c.sqid) d ");
					sql.append(" where a.xh = d.xh ");
					sql.append(" and d.sqid = '"+sqid+"' ");
					sql.append(" and d.gwid = '"+shgw+"') ");
				}
				
				commitSql[i] = sql.toString();
			}
			
			try {
				flag=saveArrDate(commitSql);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return flag;
	}

	/**
	 * 获得修改审核列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXgshList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 审核岗位
		String shgw = model.getShgw();
		
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from (select c.lcid, ");
		sql.append("a.xh,f.xm,f.nj,f.xydm,f.xymc,f.zydm, ");
		sql.append("f.zymc,f.bjdm,f.bjmc,a.sqsj, ");
		sql.append("b.sqid,d.xh lv,e.maxlv,b.gwid, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz d, ");
		sql.append("(select max(xh) maxlv, splc from xg_xtwh_spbz group by splc) e, ");
		
		sql.append("(");
		sql.append("select g.xh,g.xm,h.nj,h.xydm,h.xymc,h.zydm,h.zymc,h.bjdm,h.bjmc ");
		sql.append("from " + Base.xsxxb + " g ");
		sql.append("left join view_njxyzybj_all h on g.bjdm = h.bjdm ");
		sql.append(") f ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid ");
		sql.append("and f.xh = a.xh ");
		sql.append("and e.splc = d.splc) a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.gwid='" + shgw + "' ");
		
		//过滤条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		sql.append(searchTj);
		sql.append(searchTjByUser);

		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append("and (");
		//最小级别
		sql.append("(a.lv = 1 and (a.shzt = '未审核' or a.shzt = '需重审') and exists ");
		sql.append("(select 1 from (select c.lcid,a.xh, ");
		sql.append("a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and ((b.lv = a.lv + 1) or a.lv = a.maxlv) ");
		sql.append("and (b.shzt = '未审核' or b.shzt = '退回'))) ");
		     
		// 其他级别
		sql.append("or ");
		sql.append("(a.lv <> 1 and a.lv <> a.maxlv and (a.shzt = '未审核' or a.shzt = '需重审') and exists ");
		sql.append("(select 1 from (select c.lcid,a.xh, ");
		sql.append("a.sqsj,b.sqid,b.gwid,d.xh lv,b.shr,b.shzt, ");
		sql.append("b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = '通过') and exists ");
		sql.append("(select 1 from (select ");
		sql.append("c.lcid,a.xh,a.sqsj,b.sqid, ");
		sql.append("b.gwid,d.xh lv,b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv + 1 ");
		sql.append("and (b.shzt = '未审核' or b.shzt = '退回'))) ");
		
		// 最大级别
		sql.append("or ");
		sql.append("(a.lv = a.maxlv and (a.shzt = '未审核' or a.shzt = '需重审') and exists ");
		sql.append("(select 1 from (select c.lcid,a.xh, ");
		sql.append("a.sqsj,b.sqid,b.gwid,d.xh lv, ");
		sql.append("b.shr,b.shzt,b.shsj,b.shyj ");
		sql.append("from  ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("xg_xsxx_grxx_xgshb b, ");
		sql.append("(select lcid from xg_xsxx_grxx_szb where rownum = 1) c, ");
		sql.append("xg_xtwh_spbz       d ");
		sql.append("where a.id = b.sqid ");
		sql.append("and d.spgw = b.gwid ");
		sql.append("and d.splc = c.lcid) b ");
		sql.append("where a.sqid = b.sqid ");
		sql.append("and b.lv = a.lv - 1 ");
		sql.append("and b.shzt = '通过')) ");
		
		sql.append(")");
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), sql.toString(), inputV,
						new String[] { "sqid", "xh", "xm", "bjmc", "sqsj",
								"shzt" });

		return list;
	}
	
	/**
	 * 保存申请结果
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public boolean updateSqjg(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// 申请ID
		String sqid = model.getSqid();
		// 审核岗位名称
		String shgwmc = model.getShgwmc();
		// 审核状态
		String shzt = model.getShzt();
		// 申请结果
		String sqjg = "";
		// 最大级
		boolean isMax = model.isMax();
		// 最小级
		boolean isMin = model.isMin();
		
		if ("退回".equalsIgnoreCase(shzt)) {
			if (isMin) {
				sqjg = "退回";
			} else {
				sqjg = shgwmc + "退回";
			}
		} else if ("通过".equalsIgnoreCase(shzt)) {
			if (isMax) {
				sqjg = "审核通过";
			} else {
				sqjg = shgwmc + "通过";
			}
		} else {
			sqjg = shgwmc + "审核" + shzt;
		}
				
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgsqb ");
		sql.append("set sqjg = ? ");
		sql.append("where id = ?");

		boolean flag = dao.runUpdate(sql.toString(),
				new String[] { sqjg, sqid });

		return flag;
	}
	
	/**
	 * 保存重审状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public boolean updateCszt(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// 申请ID
		String sqid = model.getSqid();
		// 上级岗位ID
		String pre_gwid = model.getPre_gwid();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgshb ");
		sql.append("set shzt = '需重审' ");
		sql.append("where sqid = ? ");
		sql.append("and gwid = ? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { sqid,
				pre_gwid });

		return flag;
	}
	
	/**
	 * 保存通过状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public boolean updateTgzt(XsxxGrxxForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// 申请ID
		String sqid = model.getSqid();
		// 后级岗位ID
		String next_gwid = model.getNext_gwid();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_xgshb ");
		sql.append("set shzt = '未审核' ");
		sql.append("where sqid = ? ");
		sql.append("and gwid = ? ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] { sqid,
			next_gwid });

		return flag;
	}
	
	/**
	 * 获得结果查询列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getJgcxList(XsxxGrxxForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from (select a.id sqid, ");
		sql.append("a.xh,f.xm,f.nj,f.xydm,f.xymc,f.zydm, ");
		sql.append("f.zymc,f.bjdm,f.bjmc,a.sqsj,a.sqjg ");;
		sql.append("from ");
		sql.append("xg_xsxx_grxx_xgsqb a, ");
		sql.append("view_xsjbxx f ");
		sql.append("where f.xh = a.xh ");
		sql.append("order by a.sqsj desc ");
		sql.append(") a ");
		sql.append("where 1 = 1 ");
		
		// 过滤条件

		SearchModel searchModel = model.getSearchModel();
		String[] sqjg = searchModel.getSearch_tj_sqjg();// 申请结果
//		String[] sftj = model.getSearchModel().getSearch_tj_sf();// 是否提交
		searchModel.setSearch_tj_sf(null);
		searchModel.setSearch_tj_sqjg(null);
		
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
//		if (sftj != null && sftj.length > 0) {
//			for (int i = 0; i < sftj.length; i++) {
//				if ("否".equalsIgnoreCase(sftj[i])) {
//					
//					sql = new StringBuilder();
//					sql.append("select rownum r,a.* from (select '' sqid, ");
//					sql.append("a.xh,a.xm,a.nj,a.xydm,v.xymc,a.zydm, ");
//					sql.append("v.zymc,a.bjdm,v.bjmc,'未提交' sqsj,'未提交' sqjg ");;
//					sql.append("from ");
//					sql.append(Base.xsxxb+ " a ");
//					sql.append("left join view_njxyzybj v on a.bjdm = v.bjdm ");
//					sql.append("where not exists(");
//					sql.append("select 1 ");
//					sql.append("from xg_xsxx_grxx_xgsqb b ");
//					sql.append("where sqsj >= (select sqkssj from xg_xsxx_grxx_szb where rownum = 1) ");
//					sql.append("and sqsj <= (select sqjssj from xg_xsxx_grxx_szb where rownum = 1) ");
//					sql.append("and a.xh = b.xh");
//					sql.append(")");
//					//sql.append("order by a.xh ");
//					sql.append(") a ");
//					sql.append("where 1 = 1 ");
//				} else {
					if (sqjg != null && sqjg.length > 0) {

						sql.append("and( ");

						for (int j = 0; j < sqjg.length; j++) {

							if (j != 0) {
								sql.append(" or ");
							}

							if ("未审核".equalsIgnoreCase(sqjg[j])) {
								sql.append("sqjg = '未审核' ");
							} else if ("审核通过".equalsIgnoreCase(sqjg[j])) {
								sql.append("sqjg = '审核通过' ");
							} else if ("审核不通过".equalsIgnoreCase(sqjg[j])) {
								sql.append("sqjg like '%不通过%' ");
							} else {
								sql.append("(sqjg <> '未审核' and sqjg <> '审核通过' and sqjg not like '%不通过%')");
							}
						}

						sql.append(") ");
					}
//				}
//			}
//		}
		
		sql.append(searchTj);
		sql.append(searchTjByUser);


		String[] inputV = SearchService.getTjInput(searchModel);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), sql.toString(), inputV,
						new String[] { "sqid", "xh", "xm", "bjmc", "sqsj",
								"sqjg" });

		return list;
	}
	
	/**
	 * 获得审核人信息
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getShrList(XsxxGrxxForm model) {

		DAO dao = DAO.getInstance();

		String sqid = model.getSqid();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.gwid,a.shr, ");
		sql.append("(select b.xm from yhb b where a.shr = b.yhm) xm, ");
		sql.append("(select c.mc from xg_xtwh_spgw c where a.gwid = c.id) gwmc,");
		sql.append("shzt,shsj,shyj ");
		sql.append("from xg_xsxx_grxx_xgshb a ");
		sql.append("where a.sqid = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { sqid }, new String[] { "gwid", "shr", "xm",
						"shzt", "gwmc", "shsj", "shyj" });

		return list;
	}
}

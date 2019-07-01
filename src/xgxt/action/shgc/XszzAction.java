package xgxt.action.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.SearchUtils;

public class XszzAction extends BaseAction {
	
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}
	
	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 放款信息的查询
	 * @return
	 */
	public ActionForward fkxxList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
//		String url = "xszz.do?method=gjzxdk";
//		if (this.power(mapping, request, url) != null) {// 权限控制代码
//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
//			return this.power(mapping, request, url);
//		} else {
			// 真正的功能实现在这里.
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userOnLine").toString();
		String realTable = "zxdk_fkxx";
		String pk = "xh||hth";
		String tips = "学生资助 - 信息维护 - 放款信息";
		String rsNum = "0";// 返回的记录数
		String tableName = "zxdk_fkxx";
		String[] colList = new String[] { "主键", "xh", "xxmc", "xm", "sfzh", "hth", "htje", "zje", "bz" };
		boolean isHTXX = true;
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String xh = Base.chgNull(request.getParameter("xh"), "", 0);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 0);
		String hth = Base.chgNull(request.getParameter("hth"), "", 0);
		
		querry.append(SearchUtils.equalSql("xh", xh));
		querry.append(SearchUtils.equalSql("sfzh", sfzh));
		querry.append(SearchUtils.equalSql("hth", hth));
		
		
		if(isHTXX){
			request.setAttribute("isHTXX", "is");
		}else{
			request.setAttribute("isHTXX", "no");
		}
		
		Vector<Object> rs = new Vector<Object>();
		
		String sql = "select " + pk + " 主键,a.* from " + tableName + " a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[]{}, new String[]{"xxmc"})[0];
		request.setAttribute("xxmc", xxmc);//取学校名称信息
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("fkxxList");
		
//		}
	}
	
	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 根据放款信息的学号和合同号 删除这条记录
	 * @return
	 * @throws Exception 
	 */
	public ActionForward fkxxDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
//		String url = "xszz.do?method=gjzxdk";
//		if (this.power(mapping, request, url) != null) {// 权限控制代码
//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
//			return this.power(mapping, request, url);
//		} else {
			// 真正的功能实现在这里.
//		HttpSession session = request.getSession();
		

//		StringBuffer sql = new StringBuffer("select replace(NVL(fkje,'0'),' ','0') fkje,xh from zxdk_fkxx where sxh=?");
//		String[] temp = dao.getOneRs(sql, new String[] { sxh },
//				new String[] { "fkje", "xh" });
//		sql = "select replace(NVL(yhdkje,'0'),' ','0') yhdkje from zxdk_xsjbxx where xh=?";
//		String tempYhje = dao.getOneRs(sql, new String[] { temp[1] },
//				"yhdkje");
//		tempYhje = String.valueOf(Integer.parseInt(tempYhje)
//				- Integer.parseInt(temp[0]));
//		sql = "update zxdk_xsjbxx set yhdkje=? where xh=?";
//		dao.runUpdate(sql, new String[] { tempYhje, temp[1] });
		DAO dao = DAO.getInstance();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String yhdkjeUpdateSql = "UPDATE zxdk_xsjbxx SET yhdkje = (yhdkje-(SELECT nvl(sum(zje), '0') zje from zxdk_fkxx where xh||hth=? and rownum=1)) where xh=?";
		dao.runUpdate(yhdkjeUpdateSql, new String[] {pkVal, xh});
//		String sql = "DELETE zxdk_fkxx WHERE xh||hth=?";
		String message = "删除成功";
		if (!StandardOperation.delete("zxdk_fkxx", "xh||hth", pkVal, request)) {
			message = "删除失败";
		}
		request.setAttribute("url", "shgc_xszz.do?method=fkxxList");
		request.setAttribute("message", message);
		return new ActionForward("/logicError.do", false);
//		}
	}
	
	/**
	 * @describe 贷款确认书 数据保存	这条记录存在时进行修改操作，不存在时进行插入操作
	 */
	public ActionForward fkxxSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//			String url = "xszz.do?method=gjzxdk";
//			if (this.power(mapping, request, url) != null) {// 权限控制代码
//				request.setAttribute("errMsg", "对不起，您目前没有权限！");
//				return this.power(mapping, request, url);
//			} else {
				// 真正的功能实现在这里.
				DAO dao = DAO.getInstance();
				String xh = Base.chgNull(request.getParameter("xh"), "", 1);
				String xxmc = Base.chgNull(request.getParameter("xxmc"), "", 1);
				String xm = Base.chgNull(request.getParameter("xm"), "", 1);      
				String zje      = Base.chgNull(request.getParameter("zje"), "", 1);
				String sfzh     = Base.chgNull(request.getParameter("sfzh"), "", 1);
				String nf5       = Base.chgNull(request.getParameter("nf5"), "", 1);
				String nf4     = Base.chgNull(request.getParameter("nf4"), "", 1);
				String nf3     = Base.chgNull(request.getParameter("nf3"), "", 1);
				String nf2    = Base.chgNull(request.getParameter("nf2"), "", 1);
				String nf1     = Base.chgNull(request.getParameter("nf1"), "", 1);
				String ljfkje    = Base.chgNull(request.getParameter("ljfkje"), "", 1);
				String htqx     = Base.chgNull(request.getParameter("htqx"), "", 1);
				String htje     = Base.chgNull(request.getParameter("htje"), "", 1);
				String hth       = Base.chgNull(request.getParameter("hth"), "", 1);
				if (hth.equals("")) {
					hth = Base.chgNull(request.getParameter("hthV"), "", 1);
				}
				String fkrq5       = Base.chgNull(request.getParameter("fkrq5"), "", 1);
				String fkrq4       = Base.chgNull(request.getParameter("fkrq4"), "", 1);
				String fkrq3     = Base.chgNull(request.getParameter("fkrq3"), "", 1);
				String fkrq2     = Base.chgNull(request.getParameter("fkrq2"), "", 1);
				String fkrq1     = Base.chgNull(request.getParameter("fkrq1"), "", 1);
				String fkje5     = Base.chgNull(request.getParameter("fkje5"), "", 1);
				String fkje4     = Base.chgNull(request.getParameter("fkje4"), "", 1);
				String fkje3     = Base.chgNull(request.getParameter("fkje3"), "", 1);
				String fkje2     = Base.chgNull(request.getParameter("fkje2"), "", 1);
				String fkje1       = Base.chgNull(request.getParameter("fkje1"), "", 1);
				String dkyh       = Base.chgNull(request.getParameter("dkyh"), "", 1);
				String dkll       = Base.chgNull(request.getParameter("dkll"), "", 1);
				String bz    = Base.chgNull(request.getParameter("bz"), "", 1);
				
//				String nd = dao.getMapNotOut("select dqnd nd from xtszb", null).get("nd");
				
				//String yhdkje = dao.getMapNotOut("select nvl(yhdkje, '0') yhdkje from zxdx_xsjbxx where xh=?", new String[] {xh}).get("yhdkje");	//学生助学贷款已获得的总金额
				//String zje1 = Base.chgNull(dao.getMapNotOut("SELECT zje from zxdk_fkxx where xh=? and hth=?", new String[] {xh, hth}).get("zje"), "0", 1);	//总金额
				String yhdkjeUpdateSql = "UPDATE zxdk_xsjbxx SET yhdkje = ? where xh=?";
				dao.runUpdate(yhdkjeUpdateSql, new String[] { zje, xh});
				
				StringBuffer sql = new StringBuffer("select count(xh) jls from zxdk_fkxx where xh=? and hth=?");
				int jls = Integer.parseInt(dao.getMapNotOut(sql.toString(), new String[]{xh, hth}).get("jls"));
				String message = "操作成功";
				if (jls == 0) {	//记录不存在，就进行插入操作。
					String[] colName = new String[] { "bz", "dkll", "dkyh", "fkje1",
					"fkje2", "fkje3", "fkje4", "fkje5", "fkrq1", "fkrq2",
					"fkrq3", "fkrq4", "fkrq5", "hth", "htje", "htqx", "ljfkje",
					"nf1", "nf2", "nf3", "nf4", "nf5", "sfzh", "xh", "xm",
					"xxmc", "zje" };
			message = "增加数据成功";
			if (!StandardOperation.insert("zxdk_fkxx", colName, new String[] {
					bz, dkll, dkyh, fkje1, fkje2, fkje3, fkje4, fkje5, fkrq1,
					fkrq2, fkrq3, fkrq4, fkrq5, hth, htje, htqx, ljfkje, nf1,
					nf2, nf3, nf4, nf5, sfzh, xh, xm, xxmc, zje }, request)) {
				message = "增加数据失败";
			} 
					
				} else {	//记录存在，就进行更新操作。
//					sql = new StringBuffer("UPDATE zxdk_fkxx SET bz=?,dkll=?,dkyh=?,fkje1=?,fkje2=?,fkje3=?,fkje4=?,fkje5=?,fkrq1=?,fkrq2=?,fkrq3=?,fkrq4=?,fkrq5=?,htje=?,htqx=?,ljfkje=?, nf1=?,nf2=?,nf3=?,nf4=?,nf5=?,sfzh=?,xm=?,xxmc=?,zje=? where xh=? and hth=?");
					message = "修改数据成功";
			if (!StandardOperation.update("zxdk_fkxx", new String[] { "bz",
					"dkll", "dkyh", "fkje1", "fkje2", "fkje3", "fkje4",
					"fkje5", "fkrq1", "fkrq2", "fkrq3", "fkrq4", "fkrq5",
					"htje", "htqx", "ljfkje", "nf1", "nf2", "nf3", "nf4",
					"nf5", "sfzh", "xm", "xxmc", "zje" }, new String[] { bz,
					dkll, dkyh, fkje1, fkje2, fkje3, fkje4, fkje5, fkrq1,
					fkrq2, fkrq3, fkrq4, fkrq5, htje, htqx, ljfkje, nf1, nf2,
					nf3, nf4, nf5, sfzh, xm, xxmc, zje }, "xh||hth", xh + hth, request)) {
				message = "修改数据失败";
			}
				}
//				sql = "select a.*, b.* from view_zbdx_xsjbxx a, zbdx_dkqrs b where a.xh=b.xh(+) and a.xh=? and b.nd(+)=?";
//				request.setAttribute("map", dao.getMapNotOut(sql, new String[]{xh, nd}));
				request.setAttribute("url", "shgc_xszz.do?method=fkxxList");
				request.setAttribute("message", message);
				return new ActionForward("/logicError.do", false);
//			}
	}
	
	/**
	 * 
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 得到和放款信息相关的学生基本信息
	 */
	public ActionForward getFkxxInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {	//通过学号得到学生的基本信息
		
//		String url = "xszz.do?method=gjzxdk";
//		if (this.power(mapping, request, url) != null) {// 权限控制代码
//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
//			return this.power(mapping, request, url);
//		} else {
			// 真正的功能实现在这里.
			DAO dao = DAO.getInstance();
			Map map = null;
//			String nd = dao.getMapNotOut("select dqnd nd from xtszb", null).get("nd");
			String xh = Base.chgNull(request.getParameter("xh"), "", 0);
			String hth = Base.chgNull(request.getParameter("hth"), "", 0);
			
			String xszt = dao.getXszt(xh);
			if (!hth.equals("")) {
				StringBuffer sql = new StringBuffer("select count(*) jls from zxdk_fkxx a where a.xh=? and a.hth=?");
				int jls = Integer.parseInt(dao.getMapNotOut(sql.toString(), new String[]{xh, hth}).get("jls"));
				if (jls == 0) {	//记录不存在，就进行插入操作。
					sql = new StringBuffer("select b.xm, b.sfzh, b.xxmc, a.xh, a.hth, a.dkll, a.htzje htje, (select yhmc||zhmc dkyh from zxdk_hkxyszb1 where rownum =1) dkyh, a.hthkjzrq htqx, '"+xszt+"' xszt, c.kh from zxdk_htxx a, zxdk_xsjbxx b,view_stu_details c where a.xh=? and a.hth=? and a.xh=b.xh and a.xh=c.xh");
					map = dao.getMapNotOut(sql.toString(), new String[]{xh, hth});
				} else {	//记录存在，就进行更新操作。
					sql = new StringBuffer("select b.xm, b.sfzh, a.*, '"+xszt+"' xszt,c.kh from zxdk_fkxx a, zxdk_xsjbxx b,view_stu_details c where a.xh=? and a.hth=? and a.xh=b.xh and a.xh=c.xh");
					map = dao.getMapNotOut(sql.toString(), new String[]{xh, hth});
				}
			} else {
				StringBuffer sql = new StringBuffer("select a.*, '"+xszt+"' xszt, b.kh from zxdk_xsjbxx a, view_stu_details b where a.xh=? and a.xh=b.xh(+)");
				map = dao.getMapNotOut(sql.toString(), new String[]{xh});
			}
			
			if (map.size()==0) {
				request.setAttribute("xskmydr", "YES");
			}
			request.setAttribute("map", map);
						
			return mapping.findForward("zxdk_fkxx");
//		}
	}
	
	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 根据主键得到放款信息记录
	 */
	public ActionForward fkxxEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
//	String url = "xszz.do?method=gjzxdk";
//	if (this.power(mapping, request, url) != null) {// 权限控制代码
//		request.setAttribute("errMsg", "对不起，您目前没有权限！");
//		return this.power(mapping, request, url);
//	} else {
		// 真正的功能实现在这里.
		DAO dao = DAO.getInstance();
		String userType = dao.getUserType(request.getSession().getAttribute("userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String xh = Base.chgNull(request.getParameter("xh"), "", 0);
		
		String sql = "select b.xm, b.sfzh, a.*, '"+dao.getXszt(xh)+"' xszt, c.kh from zxdk_fkxx a, zxdk_xsjbxx b, view_stu_details c where a.xh||a.hth=? and a.xh=b.xh and b.xh=c.xh";
		
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("map", dao.getMapNotOut(sql, new String[]{pkVal}));
		
		return mapping.findForward("fkxxEdit");
//	}

	}

	/**
	 * @describe 资助项目的查询
	 * @author zhoumi
	 * @return
	 */
	public ActionForward zzxmList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "bbdm";
		String tips = "学生资助 - 基础数据维护 - 资助项目维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "xszz_shgc_zzbbdmb";
		String[] colList = new String[] { "主键", "bbdm", "bbmc" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);
		String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 0);
		
		querry.append(SearchUtils.likeSql("bbdm", bbdm));
		querry.append(SearchUtils.likeSql("bbmc", bbmc));
		
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);
		map.put("bbmc", bbmc);
		
		String sql = "select bbdm 主键,a.* from xszz_shgc_zzbbdmb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("zzxmList");
	}

	/**
	 * @describe 根据主键得到资助项目详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzxmEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select bbdm,bbmc from xszz_shgc_zzbbdmb where bbdm=?";
		String[] outString = new String[] { "bbdm", "bbmc" };
		
		if("save".equalsIgnoreCase(act)){
			String bbdm    = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String bbmc    = Base.chgNull(request.getParameter("bbmc"), "", 1);
			String bbwbgs    = Base.chgNull(request.getParameter("bbwbgs"), "", 1);
			boolean b = false;
			
			b = StandardOperation.update("xszz_shgc_zzbbdmb", new String[] {
					"bbmc", "bbwbgs" }, new String[] { bbmc, bbwbgs }, "bbdm",
					bbdm, request);
			String num = dao.getOneRs(
					"select count(*) num from xszz_shgc_zzbbdmb where bbdm=?",
					new String[] { bbdm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation.insert("xszz_shgc_zzbbdmb", new String[] {
						"bbdm", "bbmc", "bbwbgs" }, new String[] { bbdm, bbmc,
						bbwbgs }, request);
			}
			if(b){
				pkVal = bbdm;
				request.setAttribute("ok", "ok");
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		CLOB clob = dao.getOneClob(
				"select bbwbgs from xszz_shgc_zzbbdmb where bbdm=?",
				new String[] { pkVal }, "bbwbgs");
		if (null != clob) {
			map.put("bbwbgs", clob.getSubString(1L, (int) clob.length()));
		}
		
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzxmEdit");
	}
	
	/**
	 * @describe 删除资助项目
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzxmDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*5];
		int x = 0;
		for (int i = 0; i < pkT.length; i++) {
			sqlT[x] = "delete xszz_shgc_zzbbxssqb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_shgc_zzsjb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_shgc_zzjeb where bbdm='"+pkT[i]+"'";
			x++;
			String[] colT = dao
					.getArray(
							"select zddm col from xszz_shgc_bbzdyzd where bbdm=?",
							new String[] { pkT[i] }, "col");
			for (int j = 0; j < colT.length; j++) {
				StandardOperation.update("xszz_shgc_zzbbxssqb",
						"update xszz_shgc_zzbbxssqb set zdy" + colT[j]
								+ "='' where 1=1", request);
				String[] outV = xszzDao.getViewComm("view_xszz_shgc_zzbbxssqb",
						"zdy" + colT[j]);
				if (dao
						.runUpdateTab("alter table xszz_shgc_zzbbxssqb drop column zdy"
								+ colT[j])) {
					String sqlTemp = "create or replace view view_xszz_shgc_zzbbxssqb as select a.*,d.bbmc,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from xszz_shgc_zzbbxssqb a,view_stu_details b,xszz_shgc_zzbbdmb d where a.xh=b.xh and a.bbdm=d.bbdm";
					dao.creatView(sqlTemp, outV);
					dao
							.runUpdateTab("comment on table VIEW_XSZZ_SHGC_ZZBBXSSQB is '资助申请信息'");
					StandardOperation
							.delete(
									"delete cxb where ssmk='assis' and cxbm='VIEW_XSZZ_SHGC_ZZBBXSSQB' and sxxm='ZDY"
											+ colT[j].toUpperCase() + "'",
									"cxb", request);
				}
			}
			sqlT[x] = "delete xszz_shgc_bbzdyzd where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_shgc_zzbbdmb where bbdm='"+pkT[i]+"'";
			x++;
		}
		dao.runBatch(sqlT);
		return new ActionForward("/shgc_xszz.do?method=zzxmList&go=go", false);
	}
	
	/**
	 * @describe 资助金额的查询
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzjeList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "bbdm||zzje";
		String tips = "学生资助 - 基础数据维护 - 资助金额维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_xszz_shgc_zzjeb";
		String[] colList = new String[] { "主键", "bbdm", "bbmc", "zzje" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);
		String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 0);
		
		querry.append(SearchUtils.equalSql("bbdm", bbdm));
		querry.append(SearchUtils.likeSql("bbmc", bbmc));
		
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);
		map.put("bbmc", bbmc);
		
		String sql = "select bbdm||zzje 主键,a.* from view_xszz_shgc_zzjeb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("zzjeList");
	}

	/**
	 * @describe 根据主键得到资助金额详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzjeEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute("userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select bbdm,bbmc,zzje from view_xszz_shgc_zzjeb where bbdm||zzje=?";
		String[] outString = new String[]{"bbdm", "bbmc", "zzje"};
		
		if("save".equalsIgnoreCase(act)){
			String bbdm    = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String zzje    = Base.chgNull(request.getParameter("zzje"), "", 1);
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from xszz_shgc_zzjeb where bbdm||zzje=?",
					new String[] { bbdm + zzje }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation.insert("xszz_shgc_zzjeb", new String[] {
						"bbdm", "zzje" }, new String[] { bbdm, zzje }, request);
				if(b){
					request.setAttribute("ok", "ok");
				}else{
					request.setAttribute("ok", "no");
				}
			} else {
				request.setAttribute("have","have");
			}
			pkVal = bbdm + zzje;
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzjeEdit");
	}
	
	/**
	 * @describe 删除资助金额数据
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzjeDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		
		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for(int i = 0; i < pkT.length; i++){
			sqlT[i] = "delete xszz_shgc_zzjeb where bbdm||zzj='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
		return new ActionForward("/shgc_xszz.do?method=zzjeList&go=go", false);
	}

	/**
	 * @describe 资助申请时间设定
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzsjList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "bbdm||xydm";
		String tips = "学生资助 - 基础数据维护 - 资助申请时间维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_xszz_shgc_zzsjb";
		String[] colList = new String[] { "主键", "bbmc", "xymc", "xyrs", "knsrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("bbdm", bbdm));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);
		map.put("xydm", xydm);
		
		String sql = "select bbdm||xydm 主键,a.* from view_xszz_shgc_zzsjb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("zzsjList");
	}

	/**
	 * @describe 根据主键得到资助时间设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select bbdm,bbmc,xydm,xymc,xyrs,knsrs,kssj,jssj from view_xszz_shgc_zzsjb where bbdm||xydm=?";
		String[] outString = new String[] { "bbdm", "bbmc", "xydm", "xymc",
				"xyrs", "knsrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String bbdm    = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String xydm    = Base.chgNull(request.getParameter("xydm"), "", 1);
			String knsrs    = Base.chgNull(request.getParameter("knsrs"), "", 1);
			String kssj    = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj    = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_xszz_shgc_zzsjb where bbdm||xydm=?",
					new String[] { bbdm + xydm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation
						.insert("xszz_shgc_zzsjb", new String[] { "bbdm",
								"xydm", "knsrs", "kssj", "jssj" },
								new String[] { bbdm, xydm, knsrs, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("xszz_shgc_zzsjb", new String[] {
						"knsrs", "kssj", "jssj" }, new String[] { knsrs, kssj,
						jssj }, "bbdm||xydm", bbdm + xydm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = bbdm + xydm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzsjEdit");
	}
	
	/**
	 * @describe 批量设置资助申请时间
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzsjPlsz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String knsrs = "否";
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if("save".equalsIgnoreCase(act)){
			knsrs = Base.chgNull(request.getParameter("knsrs"), "", 1);
			kssj  = Base.chgNull(request.getParameter("kssj"), "", 1);
			jssj  = Base.chgNull(request.getParameter("jssj"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for(int i = 0; i < pkT.length; i++){
				sqlT[i] = "update xszz_shgc_zzsjb set knsrs='" + knsrs
						+ "',kssj='" + kssj + "',jssj='" + jssj
						+ "' where bbdm||xydm='" + pkT[i] + "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("knsrs", knsrs);
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zzsjPlsz");
	}
	
	/**
	 * @describe 初始化资助申请时间设置数据
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzsjcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String sfbc = Base.chgNull(request.getParameter("sfbc"), "", 1);

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		ArrayList<String> xyList = xszzDao.getArrayList(
				"select xydm from view_xsjbxx group by xydm", "xydm");
		ArrayList<String> bbList = xszzDao.getArrayList(
				"select bbdm from xszz_shgc_zzbbdmb group by bbdm", "bbdm");
		if ("no".equalsIgnoreCase(sfbc)) {
			StandardOperation.delete("delete xszz_shgc_zzsjb where 1=1",
					"xszz_shgc_zzsjb", request);
			dao
					.runUpdate(
							"insert into xszz_shgc_zzsjb(bbdm,xydm) (select a.xmdm,b.xydm from xszz_shgc_zzbbdmb a,(select xydm from view_xsjbxx group by xydm) b)",
							new String[] {});
		} else {
			String[] sqlT = new String[xyList.size()*bbList.size()];
			int x = 0;
			for (Iterator<String> it = xyList.iterator(); it.hasNext();) {
				String xydm = it.next();
				for (Iterator<String> its = bbList.iterator(); its.hasNext();) {
					String bbdm = its.next();
					String num = dao
							.getOneRs(
									"select count(*) num from xszz_shgc_zzsjb where bbdm=? and xydm=?",
									new String[] { bbdm, xydm }, "num");
					if ("0".equalsIgnoreCase(num)) {
						sqlT[x] = "insert into xszz_shgc_zzsjb(bbdm.xydm) values('"+bbdm+"','"+xydm+"')";
						x++;
					}
				}
			}
			dao.runBatch(sqlT);
		}
		request.setAttribute("endCsh", "end");
		return new ActionForward("/shgc_xszz.do?method=zzsjList&go=go", false);
	}

	/**
	 * @describe 资助报表自定义字段的查询
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzbbzdyzdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "zddm";
		String tips = "学生资助 - 基础数据维护 - 资助报表自定义字段维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_xszz_shgc_bbzdyzd";
		String[] colList = new String[] { "主键", "bbmc", "zddm", "zdmc", "zdlx",
				"zddx", "bxwsz" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);

		querry.append(SearchUtils.equalSql("bbdm", bbdm));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);

		String sql = "select zddm 主键,a.* from view_xszz_shgc_bbzdyzd a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("zzbbzdyzdList");
	}

	/**
	 * @describe 根据主键得到资助自定义字段详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zzbbzdyzdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String acDo = Base.chgNull(request.getParameter("acDo"), "", 0);
		String sql = "select bbdm,bbmc,zddm,zdmc,zdlx,zddx,bxwsz from view_xszz_shgc_bbzdyzd where zddm=?";
		String[] outString = new String[] { "bbdm", "bbmc", "zddm", "zdmc",
				"zdlx", "zddx", "bxwsz" };

		if ("save".equalsIgnoreCase(acDo)) {
			String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String zddm = Base.chgNull(request.getParameter("zddm"), "", 1);
			String zdmc = Base.chgNull(request.getParameter("zdmc"), "", 1);
			String zdlx = Base.chgNull(request.getParameter("zdlx"), "", 1);
			String zddx = Base.chgNull(request.getParameter("zddx"), "", 1);
			String bxwsz = Base.chgNull(request.getParameter("bxwsz"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_xszz_shgc_bbzdyzd where zddm=?",
							new String[] { zddm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				String sqlTemp = "alter table xszz_shgc_zzbbxssqb add (zdy"
						+ zddm + " varchar(" + zddx + "))";
				dao.runUpdateTab(sqlTemp);
					String[] outV = dao.getViewComm("view_xszz_shgc_zzbbxssqb");
					sqlTemp = "create or replace view view_xszz_shgc_zzbbxssqb as select a.*,d.bbmc,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from xszz_shgc_zzbbxssqb a,view_stu_details b,xszz_shgc_zzbbdmb d where a.xh=b.xh and a.bbdm=d.bbdm";
					dao.creatView(sqlTemp, outV);
					sqlTemp = "comment on column xszz_shgc_zzbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					dao
							.runUpdateTab("comment on table VIEW_XSZZ_SHGC_ZZBBXSSQB is '资助申请信息'");
					sqlTemp = "comment on column view_xszz_shgc_zzbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
						StandardOperation.insert("cxb", new String[] { "ssmk",
								"cxbm", "sxxm", "sxbj", "xxmc" },
								new String[] {
										"assis",
										"VIEW_XSZZ_SHGC_ZZBBXSSQB",
										"ZDY" + zddm.toUpperCase(), "0",
										"10856" }, request);
						b = StandardOperation.insert("xszz_shgc_bbzdyzd",
								new String[] { "bbdm", "zddm", "zdmc", "zdlx",
										"zddx", "bxwsz" }, new String[] { bbdm,
										zddm, zdmc, zdlx, zddx, bxwsz },
								request);
//					}
//				}
			} else {
				if("add".equalsIgnoreCase(act)){
					request.setAttribute("have", "have");
				} else {
					String sqlTemp = "comment on column xszz_shgc_zzbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					sqlTemp = "comment on column view_xszz_shgc_zzbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
						b = StandardOperation
								.update(
										"xszz_shgc_bbzdyzd",
										new String[] { "zdmc", "zdlx", "zddx",
												"bxwsz" },
										new String[] { zdmc, zdlx, zddx, bxwsz },
										"zddm", zddm, request);
//					}
				}
			}
			pkVal = zddm;
			if (b) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("rs", map);
		request.setAttribute("acDo", acDo);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzbbzdyzdEdit");
	}

	/**
	 * @describe 删除资助自定义字段数据
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzbbzdyzdDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String[] pkT = pkDel.split("!!splitOne!!");
		for (int i = 0; i < pkT.length; i++) {
			if (!"".equalsIgnoreCase(pkT[i])) {
				StandardOperation.update("xszz_shgc_zzbbxssqb",
						"update xszz_shgc_zzbbxssqb set zdy" + pkT[i]
								+ "='' where 1=1", request);
				String[] outV = xszzDao.getViewComm("view_xszz_shgc_zzbbxssqb",
						"zdy" + pkT[i]);
				if (dao
						.runUpdateTab("alter table xszz_shgc_zzbbxssqb drop column zdy"
								+ pkT[i])) {
					String sqlTemp = "create or replace view view_xszz_shgc_zzbbxssqb as select a.*,d.bbmc,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from xszz_shgc_zzbbxssqb a,view_stu_details b,xszz_shgc_zzbbdmb d where a.xh=b.xh and a.bbdm=d.bbdm";
					dao.creatView(sqlTemp, outV);
					if (dao
							.runUpdateTab("comment on table VIEW_XSZZ_SHGC_ZZBBXSSQB is '资助申请信息'")) {
						StandardOperation
								.delete(
										"delete cxb where ssmk='assis' and cxbm='VIEW_XSZZ_SHGC_ZZBBXSSQB' and sxxm='ZDY"
												+ pkT[i].toUpperCase() + "'",
										"cxb", request);
						StandardOperation.delete("xszz_shgc_bbzdyzd", "zddm",
								pkT[i], request);
					}
				}
			}
		}
		return new ActionForward("/shgc_xszz.do?method=zzbbzdyzdList&go=go",
				false);
	}

	/**
	 * @describe 资助申请页面
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.currNd;

		StringBuffer sb = new StringBuffer(" xh='");
		sb.append(xh);
		sb.append("' ");
		List<String[]> zdyzdList = xszzDao.getShgcZdyzdList(bbdm);
		String zdyzdXxxx = xszzDao.getShgcZdyzdXxxxList(bbdm);
		if(zdyzdList.size() == 0){
			request.setAttribute("isNULL", "is");
		} else {
			request.setAttribute("isNULL", "no");
		}
		request.setAttribute("zdyzdList", zdyzdList);
		request.setAttribute("zdyzdXxxx", zdyzdXxxx);
		boolean sfkns = false;
		String sfksq = "-1";
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
		} else {
			pkVal = nd + bbdm + xh;
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;
			sfkns = dao.isKns(xh);

			sql1 = "select a.kssj,a.jssj,a.knsrs from view_xszz_shgc_zzsjb a,view_xsjbxx b where a.bbdm=? and b.xh=? and a.xydm=b.xydm";
			jxjksjssj = dao.getOneRs(sql1, new String[] { bbdm, xh },
					new String[] { "kssj", "jssj", "knsrs" });
			if (jxjksjssj == null){
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
			} else if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0
					&& (("否".equalsIgnoreCase(jxjksjssj[2]) || ("".equalsIgnoreCase(jxjksjssj[2]))) || ("是"
							.equalsIgnoreCase(jxjksjssj[2]) && sfkns))) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {// /学生填写申请
					String sqly = Base.chgNull(request.getParameter("sqly"),"", 1);
					String lxdh = Base.chgNull(request.getParameter("lxdh"),"",1);
					String rxqhk = Base.chgNull(request.getParameter("rxqhk"),"",1);
					String jtzz = Base.chgNull(request.getParameter("jtzz"),"",1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),"",1);
					String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"),"",1);
					String sfyycjcshzyhd = Base.chgNull(request.getParameter("sfyycjcshzyhd"),"",1);
					String sfyysqgjzxdkhqgzx = Base.chgNull(request.getParameter("sfyysqgjzxdkhqgzx"),"",1);
					String sfjq = Base.chgNull(request.getParameter("sfjq"),"",1);
					String sfge = Base.chgNull(request.getParameter("sfge"),"",1);
					String sfdq = Base.chgNull(request.getParameter("sfdq"),"",1);
					String sfcj = Base.chgNull(request.getParameter("sfcj"),"",1);
					String sfjls = Base.chgNull(request.getParameter("sfjls"),"",1);
					String sfly = Base.chgNull(request.getParameter("sfly"),"",1);
					String sfzb = Base.chgNull(request.getParameter("sfzb"),"",1);
					String jtcy1_xm = Base.chgNull(request.getParameter("jtcy1_xm"),"",1);
					String jtcy1_nl = Base.chgNull(request.getParameter("jtcy1_nl"),"",1);
					String jtcy1_gx = Base.chgNull(request.getParameter("jtcy1_gx"),"",1);
					String jtcy1_gzdw = Base.chgNull(request.getParameter("jtcy1_gzdw"),"",1);
					String jtcy1_zy = Base.chgNull(request.getParameter("jtcy1_zy"),"",1);
					String jtcy1_nsr = Base.chgNull(request.getParameter("jtcy1_nsr"),"",1);
					String jtcy1_jkzk = Base.chgNull(request.getParameter("jtcy1_jkzk"),"",1);
					String jtcy2_xm = Base.chgNull(request.getParameter("jtcy2_xm"),"",1);
					String jtcy2_nl = Base.chgNull(request.getParameter("jtcy2_nl"),"",1);
					String jtcy2_gx = Base.chgNull(request.getParameter("jtcy2_gx"),"",1);
					String jtcy2_gzdw = Base.chgNull(request.getParameter("jtcy2_gzdw"),"",1);
					String jtcy2_zy = Base.chgNull(request.getParameter("jtcy2_zy"),"",1);
					String jtcy2_nsr = Base.chgNull(request.getParameter("jtcy2_nsr"),"",1);
					String jtcy2_jkzk = Base.chgNull(request.getParameter("jtcy2_jkzk"),"",1);
					String jtcy3_xm = Base.chgNull(request.getParameter("jtcy3_xm"),"",1);
					String jtcy3_nl = Base.chgNull(request.getParameter("jtcy3_nl"),"",1);
					String jtcy3_gx = Base.chgNull(request.getParameter("jtcy3_gx"),"",1);
					String jtcy3_gzdw = Base.chgNull(request.getParameter("jtcy3_gzdw"),"",1);
					String jtcy3_zy = Base.chgNull(request.getParameter("jtcy3_zy"),"",1);
					String jtcy3_nsr = Base.chgNull(request.getParameter("jtcy3_nsr"),"",1);
					String jtcy3_jkzk = Base.chgNull(request.getParameter("jtcy3_jkzk"),"",1);
					String jtcy4_xm = Base.chgNull(request.getParameter("jtcy4_xm"),"",1);
					String jtcy4_nl = Base.chgNull(request.getParameter("jtcy4_nl"),"",1);
					String jtcy4_gx = Base.chgNull(request.getParameter("jtcy4_gx"),"",1);
					String jtcy4_gzdw = Base.chgNull(request.getParameter("jtcy4_gzdw"),"",1);
					String jtcy4_zy = Base.chgNull(request.getParameter("jtcy4_zy"),"",1);
					String jtcy4_nsr = Base.chgNull(request.getParameter("jtcy4_nsr"),"",1);
					String jtcy4_jkzk = Base.chgNull(request.getParameter("jtcy4_jkzk"),"",1);
					String jtcy5_xm = Base.chgNull(request.getParameter("jtcy5_xm"),"",1);
					String jtcy5_nl = Base.chgNull(request.getParameter("jtcy5_nl"),"",1);
					String jtcy5_gx = Base.chgNull(request.getParameter("jtcy5_gx"),"",1);
					String jtcy5_gzdw = Base.chgNull(request.getParameter("jtcy5_gzdw"),"",1);
					String jtcy5_zy = Base.chgNull(request.getParameter("jtcy5_zy"),"",1);
					String jtcy5_nsr = Base.chgNull(request.getParameter("jtcy5_nsr"),"",1);
					String jtcy5_jkzk = Base.chgNull(request.getParameter("jtcy5_jkzk"),"",1);
					String jtrjnsr = Base.chgNull(request.getParameter("jtrjnsr"),"",1);
					String xszbdszqk = Base.chgNull(request.getParameter("xszbdszqk"),"",1);
					String jtzszrzhqk = Base.chgNull(request.getParameter("jtzszrzhqk"),"",1);
					String jtzstfywsj = Base.chgNull(request.getParameter("jtzstfywsj"),"",1);
					String qtqk = Base.chgNull(request.getParameter("qtqk"),"",1);
					String mzbm_txdz = Base.chgNull(request.getParameter("mzbm_txdz"),"",1);
					String mzbm_yzbm = Base.chgNull(request.getParameter("mzbm_yzbm"),"",1);
					String mzbm_lxdh = Base.chgNull(request.getParameter("mzbm_lxdh"),"",1);
					String syd = Base.chgNull(request.getParameter("syd"),"",1);
					String kns = "";
					if(sfkns){
						kns = "是";
					}else{
						kns = "否";
					}

					sql = "select count(*) num from xszz_shgc_zzbbxssqb where nd||bbdm||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("xszz_shgc_zzbbxssqb",
								new String[] { "nd", "bbdm", "xh", "sqly",
										"lxdh", "rxqhk", "jtzz", "yzbm",
										"jtlxdh", "sfyycjcshzyhd",
										"sfyysqgjzxdkhqgzx", "sfjq", "sfge",
										"sfdq", "sfcj", "sfjls", "sfly",
										"sfzb", "jtcy1_xm", "jtcy1_nl",
										"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
										"jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm",
										"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
										"jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
										"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
										"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
										"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
										"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
										"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm",
										"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
										"jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk",
										"jtrjnsr", "xszbdszqk", "jtzszrzhqk",
										"jtzstfywsj", "qtqk", "mzbm_txdz",
										"mzbm_yzbm", "mzbm_lxdh", "sfkns", "syd" },
								new String[] { nd, bbdm, xh, sqly, lxdh, rxqhk,
										jtzz, yzbm, jtlxdh, sfyycjcshzyhd,
										sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq,
										sfcj, sfjls, sfly, sfzb, jtcy1_xm,
										jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
										jtcy1_zy, jtcy1_nsr, jtcy1_jkzk,
										jtcy2_xm, jtcy2_nl, jtcy2_gx,
										jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
										jtcy2_jkzk, jtcy3_xm, jtcy3_nl,
										jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
										jtcy3_nsr, jtcy3_jkzk, jtcy4_xm,
										jtcy4_nl, jtcy4_gx, jtcy4_gzdw,
										jtcy4_zy, jtcy4_nsr, jtcy4_jkzk,
										jtcy5_xm, jtcy5_nl, jtcy5_gx,
										jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
										jtcy5_jkzk, jtrjnsr, xszbdszqk,
										jtzszrzhqk, jtzstfywsj, qtqk,
										mzbm_txdz, mzbm_yzbm, mzbm_lxdh, kns, syd },
								request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (Iterator it = zdyzdList.iterator(); it
										.hasNext();) {
									String[] tempSr = (String[]) it.next();
									String srName = "zdy" + tempSr[0];
									String sr = Base.chgNull(request
											.getParameter(srName), "", 1);
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update xszz_shgc_zzbbxssqb set "
										+ sb.toString()
										+ " where nd||bbdm||xh='" + pkVal + "'";
								StandardOperation.update("xszz_shgc_zzbbxssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_shgc_zzbbxssqb where nd||bbdm||xh=? and xxsh='通过'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update(
									"xszz_shgc_zzbbxssqb", new String[] {
											"sqly", "sqsj", "xysh", "xyshyj",
											"xyshsj", "xypzje", "xxsh",
											"xxshyj", "xxshsj", "xxpzje",
											"lxdh", "rxqhk", "jtzz", "yzbm",
											"jtlxdh", "sfyycjcshzyhd",
											"sfyysqgjzxdkhqgzx", "sfjq",
											"sfge", "sfdq", "sfcj", "sfjls",
											"sfly", "sfzb", "jtcy1_xm",
											"jtcy1_nl", "jtcy1_gx",
											"jtcy1_gzdw", "jtcy1_zy",
											"jtcy1_nsr", "jtcy1_jkzk",
											"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
											"jtcy2_gzdw", "jtcy2_zy",
											"jtcy2_nsr", "jtcy2_jkzk",
											"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
											"jtcy3_gzdw", "jtcy3_zy",
											"jtcy3_nsr", "jtcy3_jkzk",
											"jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
											"jtcy4_gzdw", "jtcy4_zy",
											"jtcy4_nsr", "jtcy4_jkzk",
											"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
											"jtcy5_gzdw", "jtcy5_zy",
											"jtcy5_nsr", "jtcy5_jkzk",
											"jtrjnsr", "xszbdszqk",
											"jtzszrzhqk", "jtzstfywsj", "qtqk",
											"mzbm_txdz", "mzbm_yzbm",
											"mzbm_lxdh", "sfkns", "syd" },
									new String[] { sqly, rightNow, "未审核", "",
											"", "0", "未审核", "", "", "0", lxdh,
											rxqhk, jtzz, yzbm, jtlxdh,
											sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
											sfjq, sfge, sfdq, sfcj, sfjls,
											sfly, sfzb, jtcy1_xm, jtcy1_nl,
											jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
											jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
											jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
											jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
											jtcy3_xm, jtcy3_nl, jtcy3_gx,
											jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
											jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
											jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
											jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
											jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
											jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
											jtrjnsr, xszbdszqk, jtzszrzhqk,
											jtzstfywsj, qtqk, mzbm_txdz,
											mzbm_yzbm, mzbm_lxdh, kns, syd },
									"nd||bbdm||xh", pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (Iterator it = zdyzdList.iterator(); it
											.hasNext();) {
										String[] tempSr = (String[]) it.next();
										String srName = "zdy" + tempSr[0];
										String sr = Base.chgNull(request
												.getParameter(srName), "", 1);
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update xszz_shgc_zzbbxssqb set "
											+ sb.toString()
											+ " where nd||bbdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("xszz_shgc_zzbbxssqb", sql, request);
								}
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
						} else {
							request.setAttribute("have", "have");
						}
					}
				}
			} else {// 不在申请时间范围内
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// 不能申请
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			sfkns = dao.isKns(xh);

			sql1 = "select a.knsrs from view_xszz_shgc_zzsjb a,view_xsjbxx b where a.bbdm=? and b.xh=? and a.xydm=b.xydm";
			String knsT = dao
					.getOneRs(sql1, new String[] { bbdm, xh }, "knsrs");
			if (("否".equalsIgnoreCase(knsT) || ("".equalsIgnoreCase(knsT)))
					|| ("是".equalsIgnoreCase(knsT) && sfkns)) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					String lxdh = Base.chgNull(request.getParameter("lxdh"),"",1);
					String rxqhk = Base.chgNull(request.getParameter("rxqhk"),"",1);
					String jtzz = Base.chgNull(request.getParameter("jtzz"),"",1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),"",1);
					String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"),"",1);
					String sfyycjcshzyhd = Base.chgNull(request.getParameter("sfyycjcshzyhd"),"",1);
					String sfyysqgjzxdkhqgzx = Base.chgNull(request.getParameter("sfyysqgjzxdkhqgzx"),"",1);
					String sfjq = Base.chgNull(request.getParameter("sfjq"),"",1);
					String sfge = Base.chgNull(request.getParameter("sfge"),"",1);
					String sfdq = Base.chgNull(request.getParameter("sfdq"),"",1);
					String sfcj = Base.chgNull(request.getParameter("sfcj"),"",1);
					String sfjls = Base.chgNull(request.getParameter("sfjls"),"",1);
					String sfly = Base.chgNull(request.getParameter("sfly"),"",1);
					String sfzb = Base.chgNull(request.getParameter("sfzb"),"",1);
					String jtcy1_xm = Base.chgNull(request.getParameter("jtcy1_xm"),"",1);
					String jtcy1_nl = Base.chgNull(request.getParameter("jtcy1_nl"),"",1);
					String jtcy1_gx = Base.chgNull(request.getParameter("jtcy1_gx"),"",1);
					String jtcy1_gzdw = Base.chgNull(request.getParameter("jtcy1_gzdw"),"",1);
					String jtcy1_zy = Base.chgNull(request.getParameter("jtcy1_zy"),"",1);
					String jtcy1_nsr = Base.chgNull(request.getParameter("jtcy1_nsr"),"",1);
					String jtcy1_jkzk = Base.chgNull(request.getParameter("jtcy1_jkzk"),"",1);
					String jtcy2_xm = Base.chgNull(request.getParameter("jtcy2_xm"),"",1);
					String jtcy2_nl = Base.chgNull(request.getParameter("jtcy2_nl"),"",1);
					String jtcy2_gx = Base.chgNull(request.getParameter("jtcy2_gx"),"",1);
					String jtcy2_gzdw = Base.chgNull(request.getParameter("jtcy2_gzdw"),"",1);
					String jtcy2_zy = Base.chgNull(request.getParameter("jtcy2_zy"),"",1);
					String jtcy2_nsr = Base.chgNull(request.getParameter("jtcy2_nsr"),"",1);
					String jtcy2_jkzk = Base.chgNull(request.getParameter("jtcy2_jkzk"),"",1);
					String jtcy3_xm = Base.chgNull(request.getParameter("jtcy3_xm"),"",1);
					String jtcy3_nl = Base.chgNull(request.getParameter("jtcy3_nl"),"",1);
					String jtcy3_gx = Base.chgNull(request.getParameter("jtcy3_gx"),"",1);
					String jtcy3_gzdw = Base.chgNull(request.getParameter("jtcy3_gzdw"),"",1);
					String jtcy3_zy = Base.chgNull(request.getParameter("jtcy3_zy"),"",1);
					String jtcy3_nsr = Base.chgNull(request.getParameter("jtcy3_nsr"),"",1);
					String jtcy3_jkzk = Base.chgNull(request.getParameter("jtcy3_jkzk"),"",1);
					String jtcy4_xm = Base.chgNull(request.getParameter("jtcy4_xm"),"",1);
					String jtcy4_nl = Base.chgNull(request.getParameter("jtcy4_nl"),"",1);
					String jtcy4_gx = Base.chgNull(request.getParameter("jtcy4_gx"),"",1);
					String jtcy4_gzdw = Base.chgNull(request.getParameter("jtcy4_gzdw"),"",1);
					String jtcy4_zy = Base.chgNull(request.getParameter("jtcy4_zy"),"",1);
					String jtcy4_nsr = Base.chgNull(request.getParameter("jtcy4_nsr"),"",1);
					String jtcy4_jkzk = Base.chgNull(request.getParameter("jtcy4_jkzk"),"",1);
					String jtcy5_xm = Base.chgNull(request.getParameter("jtcy5_xm"),"",1);
					String jtcy5_nl = Base.chgNull(request.getParameter("jtcy5_nl"),"",1);
					String jtcy5_gx = Base.chgNull(request.getParameter("jtcy5_gx"),"",1);
					String jtcy5_gzdw = Base.chgNull(request.getParameter("jtcy5_gzdw"),"",1);
					String jtcy5_zy = Base.chgNull(request.getParameter("jtcy5_zy"),"",1);
					String jtcy5_nsr = Base.chgNull(request.getParameter("jtcy5_nsr"),"",1);
					String jtcy5_jkzk = Base.chgNull(request.getParameter("jtcy5_jkzk"),"",1);
					String jtrjnsr = Base.chgNull(request.getParameter("jtrjnsr"),"",1);
					String xszbdszqk = Base.chgNull(request.getParameter("xszbdszqk"),"",1);
					String jtzszrzhqk = Base.chgNull(request.getParameter("jtzszrzhqk"),"",1);
					String jtzstfywsj = Base.chgNull(request.getParameter("jtzstfywsj"),"",1);
					String qtqk = Base.chgNull(request.getParameter("qtqk"),"",1);
					String mzbm_txdz = Base.chgNull(request.getParameter("mzbm_txdz"),"",1);
					String mzbm_yzbm = Base.chgNull(request.getParameter("mzbm_yzbm"),"",1);
					String mzbm_lxdh = Base.chgNull(request.getParameter("mzbm_lxdh"),"",1);
					String syd = Base.chgNull(request.getParameter("syd"),"",1);
					String kns = "";
					if(sfkns){
						kns = "是";
					}else{
						kns = "否";
					}

					sql = "select count(*) num from xszz_shgc_zzbbxssqb where nd||bbdm||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("xszz_shgc_zzbbxssqb",
								new String[] { "nd", "bbdm", "xh", "sqly",
										"lxdh", "rxqhk", "jtzz", "yzbm",
										"jtlxdh", "sfyycjcshzyhd",
										"sfyysqgjzxdkhqgzx", "sfjq", "sfge",
										"sfdq", "sfcj", "sfjls", "sfly",
										"sfzb", "jtcy1_xm", "jtcy1_nl",
										"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
										"jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm",
										"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
										"jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
										"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
										"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
										"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
										"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
										"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm",
										"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
										"jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk",
										"jtrjnsr", "xszbdszqk", "jtzszrzhqk",
										"jtzstfywsj", "qtqk", "mzbm_txdz",
										"mzbm_yzbm", "mzbm_lxdh", "sfkns", "syd" },
								new String[] { nd, bbdm, xh, sqly, lxdh, rxqhk,
										jtzz, yzbm, jtlxdh, sfyycjcshzyhd,
										sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq,
										sfcj, sfjls, sfly, sfzb, jtcy1_xm,
										jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
										jtcy1_zy, jtcy1_nsr, jtcy1_jkzk,
										jtcy2_xm, jtcy2_nl, jtcy2_gx,
										jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
										jtcy2_jkzk, jtcy3_xm, jtcy3_nl,
										jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
										jtcy3_nsr, jtcy3_jkzk, jtcy4_xm,
										jtcy4_nl, jtcy4_gx, jtcy4_gzdw,
										jtcy4_zy, jtcy4_nsr, jtcy4_jkzk,
										jtcy5_xm, jtcy5_nl, jtcy5_gx,
										jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
										jtcy5_jkzk, jtrjnsr, xszbdszqk,
										jtzszrzhqk, jtzstfywsj, qtqk,
										mzbm_txdz, mzbm_yzbm, mzbm_lxdh, kns, syd },
								request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (Iterator it = zdyzdList.iterator(); it
										.hasNext();) {
									String[] tempSr = (String[]) it.next();
									String srName = "zdy" + tempSr[0];
									String sr = Base.chgNull(request
											.getParameter(srName), "", 1);
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update xszz_shgc_zzbbxssqb set "
										+ sb.toString()
										+ " where nd||bbdm||xh='" + pkVal + "'";
								StandardOperation.update("xszz_shgc_zzbbxssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_shgc_zzbbxssqb where nd||bbdm||xh=? and xxsh='通过'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update(
									"xszz_shgc_zzbbxssqb", new String[] {
											"sqly", "sqsj", "xysh", "xyshyj",
											"xyshsj", "xypzje", "xxsh",
											"xxshyj", "xxshsj", "xxpzje",
											"lxdh", "rxqhk", "jtzz", "yzbm",
											"jtlxdh", "sfyycjcshzyhd",
											"sfyysqgjzxdkhqgzx", "sfjq",
											"sfge", "sfdq", "sfcj", "sfjls",
											"sfly", "sfzb", "jtcy1_xm",
											"jtcy1_nl", "jtcy1_gx",
											"jtcy1_gzdw", "jtcy1_zy",
											"jtcy1_nsr", "jtcy1_jkzk",
											"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
											"jtcy2_gzdw", "jtcy2_zy",
											"jtcy2_nsr", "jtcy2_jkzk",
											"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
											"jtcy3_gzdw", "jtcy3_zy",
											"jtcy3_nsr", "jtcy3_jkzk",
											"jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
											"jtcy4_gzdw", "jtcy4_zy",
											"jtcy4_nsr", "jtcy4_jkzk",
											"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
											"jtcy5_gzdw", "jtcy5_zy",
											"jtcy5_nsr", "jtcy5_jkzk",
											"jtrjnsr", "xszbdszqk",
											"jtzszrzhqk", "jtzstfywsj", "qtqk",
											"mzbm_txdz", "mzbm_yzbm",
											"mzbm_lxdh", "sfkns", "syd" },
									new String[] { sqly, rightNow, "未审核", "",
											"", "0", "未审核", "", "", "0", lxdh,
											rxqhk, jtzz, yzbm, jtlxdh,
											sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
											sfjq, sfge, sfdq, sfcj, sfjls,
											sfly, sfzb, jtcy1_xm, jtcy1_nl,
											jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
											jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
											jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
											jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
											jtcy3_xm, jtcy3_nl, jtcy3_gx,
											jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
											jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
											jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
											jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
											jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
											jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
											jtrjnsr, xszbdszqk, jtzszrzhqk,
											jtzstfywsj, qtqk, mzbm_txdz,
											mzbm_yzbm, mzbm_lxdh, kns, syd },
									"nd||bbdm||xh", pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (Iterator it = zdyzdList.iterator(); it
											.hasNext();) {
										String[] tempSr = (String[]) it.next();
										String srName = "zdy" + tempSr[0];
										String sr = Base.chgNull(request
												.getParameter(srName), "", 1);
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update xszz_shgc_zzbbxssqb set "
											+ sb.toString()
											+ " where nd||bbdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("xszz_shgc_zzbbxssqb", sql, request);
								}
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
						} else {
							request.setAttribute("have", "have");
						}
					}
				}
			} else {// 不在申请时间范围内
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// 不能申请
			}
		}

		sql = "select nd,bbdm,xh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,bbmc,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,syd,csrq,mzmc,zzmmmc,kh,rxny,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sfkns from view_xszz_shgc_zzbbxssqb where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		sql = "select nd,bbdm,xh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,bbmc,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,syd,csrq,mzmc,zzmmmc,kh,rxny,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sfkns from view_xszz_shgc_zzbbxssqb where nd||bbdm||xh=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if ((null != xh) && sfkns) {
				sql = "select aa.xh,aa.xm,aa.xb,aa.nj,aa.xymc,aa.zymc,aa.bjmc,aa.sfzh,aa.ssbh,aa.qsdh,bb.syd,(select z.rxny from bks_xsjbxx z where z.xh=aa.xh) rxny,aa.csrq,aa.mzmc,aa.zzmmmc,aa.kh,bb.nd,bb.lxdh,bb.rxqhk,bb.jtzz,bb.yzbm,bb.jtlxdh,bb.sfyycjcshzyhd,bb.sfyysqgjzxdkhqgzx,bb.sfjq,bb.sfge,bb.sfdq,bb.sfcj,bb.sfjls,bb.sfly,bb.sfzb,bb.jtcy1_xm,bb.jtcy1_nl,bb.jtcy1_gx,bb.jtcy1_gzdw,bb.jtcy1_zy,bb.jtcy1_nsr,bb.jtcy1_jkzk,bb.jtcy2_xm,bb.jtcy2_nl,bb.jtcy2_gx,bb.jtcy2_gzdw,bb.jtcy2_zy,bb.jtcy2_nsr,bb.jtcy2_jkzk,bb.jtcy3_xm,bb.jtcy3_nl,bb.jtcy3_gx,bb.jtcy3_gzdw,bb.jtcy3_zy,bb.jtcy3_nsr,bb.jtcy3_jkzk,bb.jtcy4_xm,bb.jtcy4_nl,bb.jtcy4_gx,bb.jtcy4_gzdw,bb.jtcy4_zy,bb.jtcy4_nsr,bb.jtcy4_jkzk,bb.jtcy5_xm,bb.jtcy5_nl,bb.jtcy5_gx,bb.jtcy5_gzdw,bb.jtcy5_zy,bb.jtcy5_nsr,bb.jtcy5_jkzk,bb.jtrjnsr,bb.xszbdszqk,bb.jtzszrzhqk,bb.jtzstfywsj,bb.qtqk,bb.mzbm_txdz,bb.mzbm_yzbm,bb.mzbm_lxdh from (select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.sfzh,a.ssbh,a.qsdh,a.lydq,a.csrq,a.mzmc,a.zzmmmc,a.kh from view_stu_details a where a.xh=?) aa , (select k.xh,k.nd,k.lxdh,k.rxqhk,k.jtzz,k.yzbm,k.jtlxdh,k.sfyycjcshzyhd,k.sfyysqgjzxdkhqgzx,k.sfjq,k.sfge,k.sfdq,k.sfcj,k.sfjls,k.sfly,k.sfzb,k.jtcy1_xm,k.jtcy1_nl,k.jtcy1_gx,k.jtcy1_gzdw,k.jtcy1_zy,k.jtcy1_nsr,k.jtcy1_jkzk,k.jtcy2_xm,k.jtcy2_nl,k.jtcy2_gx,k.jtcy2_gzdw,k.jtcy2_zy,k.jtcy2_nsr,k.jtcy2_jkzk,k.jtcy3_xm,k.jtcy3_nl,k.jtcy3_gx,k.jtcy3_gzdw,k.jtcy3_zy,k.jtcy3_nsr,k.jtcy3_jkzk,k.jtcy4_xm,k.jtcy4_nl,k.jtcy4_gx,k.jtcy4_gzdw,k.jtcy4_zy,k.jtcy4_nsr,k.jtcy4_jkzk,k.jtcy5_xm,k.jtcy5_nl,k.jtcy5_gx,k.jtcy5_gzdw,k.jtcy5_zy,k.jtcy5_nsr,k.jtcy5_jkzk,k.jtrjnsr,k.xszbdszqk,k.jtzszrzhqk,k.jtzstfywsj,k.qtqk,k.mzbm_txdz,k.mzbm_yzbm,k.mzbm_lxdh,k.syd from shgc_knsxx k where k.xxsh in ('一般困难','特别困难','特殊') and k.xh=? and k.nd=?) bb where aa.xh=bb.xh";
				String[] colName = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "sfzh", "ssbh", "qsdh", "syd",
						"rxny", "csrq", "mzmc", "zzmmmc", "kh", "nd", "lxdh",
						"rxqhk", "jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
						"sfyysqgjzxdkhqgzx", "sfjq", "sfge", "sfdq", "sfcj",
						"sfjls", "sfly", "sfzb", "jtcy1_xm", "jtcy1_nl",
						"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
						"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
						"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
						"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
						"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
						"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
						"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
						"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
						"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
						"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
						"mzbm_lxdh" };
				String[] outVal = dao.getOneRs(sql,
						new String[] { xh, xh, nd }, colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "sfzh", "ssbh", "qsdh", "syd",
							"rxny", "csrq", "mzmc", "zzmmmc", "kh", "nd",
							"lxdh", "rxqhk", "jtzz", "yzbm", "jtlxdh",
							"sfyycjcshzyhd", "sfyysqgjzxdkhqgzx", "sfjq",
							"sfge", "sfdq", "sfcj", "sfjls", "sfly", "sfzb",
							"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
							"jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm",
							"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
							"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
							"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
							"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
							"jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
							"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
							"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
							"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
							"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
							"mzbm_lxdh" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
				}
			} else if(null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.sfzh,a.ssbh,a.qsdh,a.lydq,a.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,a.mzmc,a.zzmmmc,a.kh,'"+nd+"' nd from view_stu_details a where a.xh=?";
				String[] colN = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "sfzh", "ssbh", "qsdh", "lydq",
						"csrq", "rxny", "mzmc", "zzmmmc", "kh", "nd" };
				String[] outV = dao.getOneRs(sql,
						new String[] { xh }, colN);
				if (outV != null) {
					colN = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "sfzh", "ssbh", "qsdh", "lydq",
							"csrq", "rxny", "mzmc", "zzmmmc", "kh", "nd" };
					for (int i = 0; i < colN.length; i++) {
						if (null != outV[i]) {
							map.put(colN[i], outV[i]);
						} else {
							map.put(colN[i], "");
						}
					}
				}
			}
		} else {
			int len1 = outString.length;
			int len2 = outValue.length;
			int max = 0;
			if (len1 >= len2) {
				max = len2;
			} else {
				max = len1;
			}
			for (int i = 0; i < max; i++) {
				outString[i] = outString[i].toLowerCase();
				if (outValue[i] != null) {
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}
		
		if (zdyzdList.size() != 0) {
			String st = " xh";
			for(String[] str : zdyzdList){
				st += ",zdy" + str[0];
			}
			sql = "select "+st+" from view_xszz_shgc_zzbbxssqb where 1=2";
			outString = dao.getColumnName(sql);// 获得列名数组
			sql = "select "+st+" from view_xszz_shgc_zzbbxssqb where nd||bbdm||xh=? ";
			outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
			if (outValue != null) {
				int len1 = outString.length;
				int len2 = outValue.length;
				int max = 0;
				if (len1 >= len2) {
					max = len2;
				} else {
					max = len1;
				}
				for (int i = 0; i < max; i++) {
					outString[i] = outString[i].toLowerCase();
					if (outValue[i] != null) {
						map.put(outString[i], outValue[i]);
					} else {
						map.put(outString[i], "");
					}
				}
			}
		}
		
		if (null == map.get("bbdm") || "".equalsIgnoreCase(map.get("bbdm"))){
			map.put("bbdm", bbdm);
		}
		if(sfkns){
			request.setAttribute("isKns", "is");
		} else {
			request.setAttribute("isKns", "no");
		}
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzsq");
	}
	
	/**
	 * @describe 资助申请报表打印
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String sqly = Base.chgNull(request.getParameter("sqly"), "", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xyshsj = Base.chgNull(request.getParameter("xyshsj"), "", 1);
		String xypzje = Base.chgNull(request.getParameter("xypzje"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String xxshsj = Base.chgNull(request.getParameter("xxshsj"), "", 1);
		String xxpzje = Base.chgNull(request.getParameter("xxpzje"), "", 1);
		String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String ssbh = Base.chgNull(request.getParameter("ssbh"), "", 1);
		String qsdh = Base.chgNull(request.getParameter("qsdh"), "", 1);
		String syd = Base.chgNull(request.getParameter("syd"), "", 1);
		String csrq = Base.chgNull(request.getParameter("csrq"), "", 1);
		String mzmc = Base.chgNull(request.getParameter("mzmc"), "", 1);
		String zzmmmc = Base.chgNull(request.getParameter("zzmmmc"), "", 1);
		String kh = Base.chgNull(request.getParameter("kh"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String lxdh = Base.chgNull(request.getParameter("lxdh"), "", 1);
		String rxqhk = Base.chgNull(request.getParameter("rxqhk"), "", 1);
		String jtzz = Base.chgNull(request.getParameter("jtzz"), "", 1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
		String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"), "", 1);
		String sfyycjcshzyhd = Base.chgNull(request
				.getParameter("sfyycjcshzyhd"), "", 1);
		String sfyysqgjzxdkhqgzx = Base.chgNull(request
				.getParameter("sfyysqgjzxdkhqgzx"), "", 1);
		String sfjq = Base.chgNull(request.getParameter("sfjq"), "", 1);
		String sfge = Base.chgNull(request.getParameter("sfge"), "", 1);
		String sfdq = Base.chgNull(request.getParameter("sfdq"), "", 1);
		String sfcj = Base.chgNull(request.getParameter("sfcj"), "", 1);
		String sfjls = Base.chgNull(request.getParameter("sfjls"), "", 1);
		String sfly = Base.chgNull(request.getParameter("sfly"), "", 1);
		String sfzb = Base.chgNull(request.getParameter("sfzb"), "", 1);
		String jtcy1_xm = Base.chgNull(request.getParameter("jtcy1_xm"), "", 1);
		String jtcy1_nl = Base.chgNull(request.getParameter("jtcy1_nl"), "", 1);
		String jtcy1_gx = Base.chgNull(request.getParameter("jtcy1_gx"), "", 1);
		String jtcy1_gzdw = Base.chgNull(request.getParameter("jtcy1_gzdw"),
				"", 1);
		String jtcy1_zy = Base.chgNull(request.getParameter("jtcy1_zy"), "", 1);
		String jtcy1_nsr = Base.chgNull(request.getParameter("jtcy1_nsr"), "",
				1);
		String jtcy1_jkzk = Base.chgNull(request.getParameter("jtcy1_jkzk"),
				"", 1);
		String jtcy2_xm = Base.chgNull(request.getParameter("jtcy2_xm"), "", 1);
		String jtcy2_nl = Base.chgNull(request.getParameter("jtcy2_nl"), "", 1);
		String jtcy2_gx = Base.chgNull(request.getParameter("jtcy2_gx"), "", 1);
		String jtcy2_gzdw = Base.chgNull(request.getParameter("jtcy2_gzdw"),
				"", 1);
		String jtcy2_zy = Base.chgNull(request.getParameter("jtcy2_zy"), "", 1);
		String jtcy2_nsr = Base.chgNull(request.getParameter("jtcy2_nsr"), "",
				1);
		String jtcy2_jkzk = Base.chgNull(request.getParameter("jtcy2_jkzk"),
				"", 1);
		String jtcy3_xm = Base.chgNull(request.getParameter("jtcy3_xm"), "", 1);
		String jtcy3_nl = Base.chgNull(request.getParameter("jtcy3_nl"), "", 1);
		String jtcy3_gx = Base.chgNull(request.getParameter("jtcy3_gx"), "", 1);
		String jtcy3_gzdw = Base.chgNull(request.getParameter("jtcy3_gzdw"),
				"", 1);
		String jtcy3_zy = Base.chgNull(request.getParameter("jtcy3_zy"), "", 1);
		String jtcy3_nsr = Base.chgNull(request.getParameter("jtcy3_nsr"), "",
				1);
		String jtcy3_jkzk = Base.chgNull(request.getParameter("jtcy3_jkzk"),
				"", 1);
		String jtcy4_xm = Base.chgNull(request.getParameter("jtcy4_xm"), "", 1);
		String jtcy4_nl = Base.chgNull(request.getParameter("jtcy4_nl"), "", 1);
		String jtcy4_gx = Base.chgNull(request.getParameter("jtcy4_gx"), "", 1);
		String jtcy4_gzdw = Base.chgNull(request.getParameter("jtcy4_gzdw"),
				"", 1);
		String jtcy4_zy = Base.chgNull(request.getParameter("jtcy4_zy"), "", 1);
		String jtcy4_nsr = Base.chgNull(request.getParameter("jtcy4_nsr"), "",
				1);
		String jtcy4_jkzk = Base.chgNull(request.getParameter("jtcy4_jkzk"),
				"", 1);
		String jtcy5_xm = Base.chgNull(request.getParameter("jtcy5_xm"), "", 1);
		String jtcy5_nl = Base.chgNull(request.getParameter("jtcy5_nl"), "", 1);
		String jtcy5_gx = Base.chgNull(request.getParameter("jtcy5_gx"), "", 1);
		String jtcy5_gzdw = Base.chgNull(request.getParameter("jtcy5_gzdw"),
				"", 1);
		String jtcy5_zy = Base.chgNull(request.getParameter("jtcy5_zy"), "", 1);
		String jtcy5_nsr = Base.chgNull(request.getParameter("jtcy5_nsr"), "",
				1);
		String jtcy5_jkzk = Base.chgNull(request.getParameter("jtcy5_jkzk"),
				"", 1);
		String jtrjnsr = Base.chgNull(request.getParameter("jtrjnsr"), "", 1);
		String xszbdszqk = Base.chgNull(request.getParameter("xszbdszqk"), "",
				1);
		String jtzszrzhqk = Base.chgNull(request.getParameter("jtzszrzhqk"),
				"", 1);
		String jtzstfywsj = Base.chgNull(request.getParameter("jtzstfywsj"),
				"", 1);
		String qtqk = Base.chgNull(request.getParameter("qtqk"), "", 1);
		String mzbm_txdz = Base.chgNull(request.getParameter("mzbm_txdz"), "",
				1);
		String mzbm_yzbm = Base.chgNull(request.getParameter("mzbm_yzbm"), "",
				1);
		String mzbm_lxdh = Base.chgNull(request.getParameter("mzbm_lxdh"), "",
				1);
		if("未审核".equalsIgnoreCase(xysh)){
			xysh=" ";
			xyshyj=" ";
			xypzje=" ";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh=" ";
			xxshyj=" ";
			xxpzje=" ";
		}
		String sqsj_year = "";
		String sqsj_mon = "";
		String sqsj_day = "";
		String xyshsj_year = "";
		String xyshsj_mon = "";
		String xyshsj_day = "";
		String xxshsj_year = "";
		String xxshsj_mon = "";
		String xxshsj_day = "";
		if(sqsj != null && !"".equalsIgnoreCase(sqsj)){
			sqsj_year = sqsj.substring(0,4);
			sqsj_mon = sqsj.substring(5, 7);
			sqsj_day = sqsj.substring(8);
		}
		if(xyshsj != null && !"".equalsIgnoreCase(xyshsj)){
			xyshsj_year = xyshsj.substring(0,4);
			xyshsj_mon = xyshsj.substring(5, 7);
			xyshsj_day = xyshsj.substring(8);
		}
		if(xxshsj != null && !"".equalsIgnoreCase(xxshsj)){
			xxshsj_year = xxshsj.substring(0,4);
			xxshsj_mon = xxshsj.substring(5, 7);
			xxshsj_day = xxshsj.substring(8);
		}

		CLOB clob = dao.getOneClob(
				"select bbwbgs from xszz_shgc_zzbbdmb where bbdm=?",
				new String[] { bbdm }, "bbwbgs");
		String htmlStr = "";
		if (null != clob) {
			htmlStr = clob.getSubString(1L, (int) clob.length());
		}
		String[] outValue = new String[] { nd, bbdm, xh, sqly, sqsj, xysh,
				xyshyj, xyshsj, xypzje, xxsh, xxshyj, xxshsj, xxpzje, bbmc, xm,
				xb, sfzh, nj, xymc, zymc, bjmc, ssbh, qsdh, syd, csrq, mzmc,
				zzmmmc, kh, rxny, lxdh, rxqhk, jtzz, yzbm, jtlxdh,
				sfyycjcshzyhd, sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq, sfcj,
				sfjls, sfly, sfzb, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
				jtcy1_zy, jtcy1_nsr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx,
				jtcy2_gzdw, jtcy2_zy, jtcy2_nsr, jtcy2_jkzk, jtcy3_xm,
				jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
				jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
				jtcy4_nsr, jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx,
				jtcy5_gzdw, jtcy5_zy, jtcy5_nsr, jtcy5_jkzk, jtrjnsr,
				xszbdszqk, jtzszrzhqk, jtzstfywsj, qtqk, mzbm_txdz, mzbm_yzbm,
				mzbm_lxdh,sqsj_year,sqsj_mon,sqsj_day,xyshsj_year,xyshsj_mon,xyshsj_day,
				xxshsj_year,xxshsj_mon,xxshsj_day };
		String[] outString = new String[] { "$nd$", "$bbdm$", "$xh$", "$sqly$",
				"$sqsj$", "$xysh$", "$xyshyj$", "$xyshsj$", "$xypzje$",
				"$xxsh$", "$xxshyj$", "$xxshsj$", "$xxpzje$", "$bbmc$", "$xm$",
				"$xb$", "$sfzh$", "$nj$", "$xymc$", "$zymc$", "$bjmc$",
				"$ssbh$", "$qsdh$", "$syd$", "$csrq$", "$mzmc$", "$zzmmmc$",
				"$kh$", "$rxny$", "$lxdh$", "$rxqhk$", "$jtzz$", "$yzbm$",
				"$jtlxdh$", "$sfyycjcshzyhd$", "$sfyysqgjzxdkhqgzx$", "$sfjq$",
				"$sfge$", "$sfdq$", "$sfcj$", "$sfjls$", "$sfly$", "$sfzb$",
				"$jtcy1_xm$", "$jtcy1_nl$", "$jtcy1_gx$", "$jtcy1_gzdw$",
				"$jtcy1_zy$", "$jtcy1_nsr$", "$jtcy1_jkzk$", "$jtcy2_xm$",
				"$jtcy2_nl$", "$jtcy2_gx$", "$jtcy2_gzdw$", "$jtcy2_zy$",
				"$jtcy2_nsr$", "$jtcy2_jkzk$", "$jtcy3_xm$", "$jtcy3_nl$",
				"$jtcy3_gx$", "$jtcy3_gzdw$", "$jtcy3_zy$", "$jtcy3_nsr$",
				"$jtcy3_jkzk$", "$jtcy4_xm$", "$jtcy4_nl$", "$jtcy4_gx$",
				"$jtcy4_gzdw$", "$jtcy4_zy$", "$jtcy4_nsr$", "$jtcy4_jkzk$",
				"$jtcy5_xm$", "$jtcy5_nl$", "$jtcy5_gx$", "$jtcy5_gzdw$",
				"$jtcy5_zy$", "$jtcy5_nsr$", "$jtcy5_jkzk$", "$jtrjnsr$",
				"$xszbdszqk$", "$jtzszrzhqk$", "$jtzstfywsj$", "$qtqk$",
				"$mzbm_txdz$", "$mzbm_yzbm$", "$mzbm_lxdh$", "$sqsj_year$",
				"$sqsj_mon$", "$sqsj_day$", "$xyshsj_year$", "$xyshsj_mon$",
				"$xyshsj_day$", "$xxshsj_year$", "$xxshsj_mon$", "$xxshsj_day$" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null && !(outValue[i].equals(""))) {
				htmlStr = htmlStr.replace(outString[i], outValue[i]);
			} else {
				htmlStr = htmlStr.replace(outString[i], " ");
			}
		}

		List<String[]> zdyzdList = xszzDao.getShgcZdyzdList(bbdm);
		if (zdyzdList.size() != 0) {
			for (Iterator it = zdyzdList.iterator(); it.hasNext();) {
				String[] tempSr = (String[]) it.next();
				String srName = "zdy" + tempSr[0] + "";
				String sr = Base.chgNull(request.getParameter(srName), "", 1);
				htmlStr = htmlStr.replace("$"+srName+"$", sr);
			}
		}
		
		request.setAttribute("htmlStr", htmlStr);
		request.setAttribute("bbmc", bbmc);
		return mapping.findForward("zzsqb");
	}
	
	/**
	 * @describe 资助审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsqxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String shzt = Base.chgNull(request.getParameter("shzt"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_shgc_zzbbxssqb";
		pk = "nd||bbdm||xh";
		tableName = "view_xszz_shgc_zzbbxssqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(shzt)) {
			if ("xy".equalsIgnoreCase(userType)) {
				querry.append(" and xysh='");
				querry.append(shzt);
				querry.append("' ");
			} else {
				querry.append(" and xxsh='");
				querry.append(shzt);
				querry.append("' ");
			}
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 资助项目审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 资助项目";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "bbmc", "xh", "xm",
					"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "xysh", "xxsh", "sqsj", "sfkns" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.bbdm pk2,a.nd,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.bbdm pk2,a.nd,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "pk2", "nd", "bbmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "sfkns", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "nd", "bbmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.bbdm pk2,a.nd,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.bbdm pk2,a.nd,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("shzt", shzt);
		map.put("xh", xh);
		map.put("bbdm", bbdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("zzsqxxList");
	}
	
	/**
	 * @describe 资助审核单个信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsqXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String actDo = Base.chgNull(request.getParameter("actDo"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String yesNo = Base.chgNull(request.getParameter("yesNo"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String zzje = Base.chgNull(request.getParameter("zzje"), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete xszz_shgc_zzbbxssqb where nd||bbdm||xh='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_shgc_zzbbxssqb where nd||bbdm||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/shgc_xszz.do?method=zzsqxxList&go=go", false);
			return newFwd;
		}
		
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update xszz_shgc_zzbbxssqb set xysh='通过',xyshsj='"
							+ now
							+ "',xypzje='0' where nd||bbdm||xh='"
							+ pkT
							+ "'";
				} else {
					String xypzjeT = dao
							.getOneRs(
									"select xypzje from xszz_shgc_zzbbxssqb where nd||bbdm||xh=?",
									new String[] { pkT }, "xypzje");
					sqlT[i] = "update xszz_shgc_zzbbxssqb set xxsh='通过',xyshsj='"
							+ now
							+ "',xypzje='"
							+ xypzjeT
							+ "' where nd||bbdm||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/shgc_xszz.do?method=zzsqxxList&go=go", false);
			return newFwd;
		}
		
		if ("notPass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					String xxshT = dao
							.getOneRs(
									"select xxsh from xszz_shgc_zzbbxssqb where nd||bbdm||xh=?",
									new String[] { pkT }, "xxsh");
					if (!"通过".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update xszz_shgc_zzbbxssqb set xysh='不通过',xyshsj='"
								+ now
								+ "',xypzje='0' where nd||bbdm||xh='"
								+ pkT + "'";
					}
				} else {
					sqlT[i] = "update xszz_shgc_zzbbxssqb set xxsh='不通过',xxshsj='"
							+ now
							+ "',xxpzje='0' where nd||bbdm||xh='"
							+ pkT
							+ "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/shgc_xszz.do?method=zzsqxxList&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				String xxshT = dao.getOneRs("select xxsh from xszz_shgc_zzbbxssqb where nd||bbdm||xh=?", new String[]{pkVal}, "xxsh");
				if("通过".equalsIgnoreCase(xxshT)){
					request.setAttribute("xxshjg", "pass");
				} else {
					StandardOperation
							.update(
									"xszz_shgc_zzbbxssqb",
									new String[] { "xysh", "xyshyj", "xyshsj",
											"xypzje" },
									new String[] { yesNo, xyshyj, now, zzje },
									"nd||bbdm||xh", pkVal, request);
				}
			} else {
				StandardOperation.update("xszz_shgc_zzbbxssqb", new String[] {
						"xxsh", "xxshyj", "xxshsj", "xxpzje" }, new String[] {
						yesNo, xxshyj, now, zzje }, "nd||bbdm||xh", pkVal,
						request);
			}
		}
		realTable = "xszz_shgc_zzbbxssqb";
		pk = "nd||bbdm||xh";
		sql = "select nd,bbdm,xh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,bbmc,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,syd,csrq,mzmc,zzmmmc,kh,rxny,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sfkns from view_xszz_shgc_zzbbxssqb where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,a.nd,a.bbdm,a.xh,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.bbmc,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.syd,a.csrq,a.mzmc,a.zzmmmc,a.kh,a.rxny,a.lxdh,a.rxqhk,a.jtzz,a.yzbm,a.jtlxdh,a.sfyycjcshzyhd,a.sfyysqgjzxdkhqgzx,a.sfjq,a.sfge,a.sfdq,a.sfcj,a.sfjls,a.sfly,a.sfzb,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,a.jtrjnsr,a.xszbdszqk,a.jtzszrzhqk,a.jtzstfywsj,a.qtqk,a.mzbm_txdz,a.mzbm_yzbm,a.mzbm_lxdh,a.sfkns,a.xysh yesNo,a.xypzje zzje "
				+ "from view_xszz_shgc_zzbbxssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,a.nd,a.bbdm,a.xh,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.bbmc,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.syd,a.csrq,a.mzmc,a.zzmmmc,a.kh,a.rxny,a.lxdh,a.rxqhk,a.jtzz,a.yzbm,a.jtlxdh,a.sfyycjcshzyhd,a.sfyysqgjzxdkhqgzx,a.sfjq,a.sfge,a.sfdq,a.sfcj,a.sfjls,a.sfly,a.sfzb,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,a.jtrjnsr,a.xszbdszqk,a.jtzszrzhqk,a.jtzstfywsj,a.qtqk,a.mzbm_txdz,a.mzbm_yzbm,a.mzbm_lxdh,a.sfkns,a.xxsh yesNo,a.xxpzje zzje "
				+ "from view_xszz_shgc_zzbbxssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[(outString.length+3)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		colList[i+1] = "yesNo";
		colList[i+2] = "zzje";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		String xh = "";
		List<String[]> zdyzdList = new ArrayList<String[]>();
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("xh")) {
				xh = rs[i];
			}
			if (colList[i].equalsIgnoreCase("bbdm")) {
				
				zdyzdList = xszzDao.getShgcZdyzdList(rs[i]);
				if (zdyzdList.size() == 0) {
					request.setAttribute("isNULL", "is");
				} else {
					request.setAttribute("isNULL", "no");
				}
				request.setAttribute("zdyzdList", zdyzdList);
				List<HashMap<String, String>> zzjeList = xszzDao.getShgcZzjeList(rs[i]);
				request.setAttribute("zzjeList", zzjeList);
			}
			hs.put(colList[i], rs[i]);
		}

		if (zdyzdList.size() != 0) {
			String st = " xh";
			for(String[] str : zdyzdList){
				st += ",zdy" + str[0];
			}
			sql = "select "+st+" from view_xszz_shgc_zzbbxssqb where 1=2";
			outString = dao.getColumnName(sql);// 获得列名数组
			sql = "select "+st+" from view_xszz_shgc_zzbbxssqb where nd||bbdm||xh=? ";
			String[] outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
			if (outValue != null) {
				int len1 = outString.length;
				int len2 = outValue.length;
				int max = 0;
				if (len1 >= len2) {
					max = len2;
				} else {
					max = len1;
				}
				for (int j = 0; j < max; j++) {
					outValue[j] = ((outValue[j] == null) || outValue[j].equalsIgnoreCase("")) ? " "
							: outValue[j];
					outString[j] = outString[j].toLowerCase();
					hs.put(outString[j].toLowerCase(), outValue[j]);
				}
			}
		}
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("rs", hs);
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			request.setAttribute("xsQtJxjjlList", xszzDao.getShgcXsQtJxjjlList(xh));
			request.setAttribute("xsYxJxjjlList", xszzDao.getShgcXsYxJxjjlList(xh));
			request.setAttribute("xsZqJxjjlList", xszzDao.getShgcXsZqJxjjlList(xh));
		} else {
			request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		}
		request.setAttribute("xszzHdjeList", xszzDao.getShgcXszzHdjeList(xh));
		request.setAttribute("zjeList", xszzDao.getShgcXshdZjeList(xh,xxdm));
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_xszz_shgc_zzbbxssqb");
		request.setAttribute("act", "zzsh");
		return mapping.findForward("zzsqXxxx");
	}
	
	/**
	 * @describe 资助列表导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsqExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		sql = "select * from view_xszz_shgc_zzbbxssqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_xszz_shgc_zzbbxssqb where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszz_shgc_zzbbxssqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("zzsqExp");
	}

	/**
	 * @describe 资助金额汇总列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzjehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String rsNum = "0";// 返回的记录数
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xysh)) {
			querry.append(" and xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		String je = "0";
		tips = "当前所在位置：学生资助 - 审核 - 资助金额汇总";
		String sqlT = "";
		if (userType.equalsIgnoreCase("xx")) {
			colList = new String[] { "nd", "bbmc", "xh", "xm",
					"bjmc", "nj", "sqsj", "xysh", "xxsh", "xxpzje" };
		} else{
			colList = new String[] { "nd", "bbmc", "xh", "xm",
					"bjmc", "nj", "sqsj", "xysh", "xxsh", "xypzje" };
		}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select nvl(sum(ROUND(xxpzje)),0) num from view_xszz_shgc_zzbbxssqb "
					+ querry.toString();
				je = dao.getOneRs(sql, new String[]{}, "num");
				sqlT = "select ' ' nd,' ' bbmc,' ' xh,' ' xm,' ' bjmc, ' ' nj,' ' sqsj,'合计' xysh, ' ' xxsh, '"+je+"' xxpzje from dual";
				sql = "select nd,bbmc,xh,xm,bjmc,nj,sqsj,xysh,xxsh,xxpzje from view_xszz_shgc_zzbbxssqb "
					+ querry.toString()
					+ " order by nd desc";
			} else {
				sql = "select nvl(sum(ROUND(xypzje)),0) num from view_xszz_shgc_zzbbxssqb "
					+ querry.toString()
					+ " and xydm='"
					+ userDep;
				je = dao.getOneRs(sql, new String[]{}, "num");
				sqlT = "select ' ' nd,' ' bbmc,' ' xh,' ' xm,' ' bjmc, ' ' nj,' ' sqsj,'合计' xysh, ' ' xxsh, '"+je+"' xypzje from dual";
				sql = "select nd,bbmc,xh,xm,bjmc,nj,sqsj,xysh,xxsh,xypzje from view_xszz_shgc_zzbbxssqb "
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by nd desc";
			}
		colListCN = dao.getColumnNameCN(colList, "view_xszz_shgc_zzbbxssqb");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rs.addAll(dao.rsToVator(sqlT, new String[] {}, colList));
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("bbdm", bbdm);
		map.put("xysh", xysh);
		map.put("xxsh", xxsh);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("xyshList", xszzDao.xyshList());
		request.setAttribute("xxshList", xszzDao.xxshList());
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("xszzjehz");
	}
	
	/**
	 * @describe 资助金额汇总导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzjehzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xysh)) {
			querry.append(" and xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		
		sql = "select * from view_xszz_shgc_zzbbxssqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_xszz_shgc_zzbbxssqb where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		sql = "select nvl(sum(ROUND(xypzje)),0) xyzje,nvl(sum(ROUND(xxpzje)),0) xxzje from view_xszz_shgc_zzbbxssqb " + querry.toString();
		String[] je = dao.getOneRs(sql, new String[]{}, new String[]{"xyzje","xxzje"});
		
		StringBuffer sqlBf = new StringBuffer("select '合计' nd");
		for(int i = 1; i< colList.length; i++){
			if("xypzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[0]+"' xypzje");
			} else if("xxpzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[1]+"' xxpzje");
			} else {
				sqlBf.append(",' ' "+colList[i].toLowerCase());
			}
		}
		sqlBf.append(" from view_xszz_shgc_zzbbxssqb " + querry.toString());
		rs.addAll(dao.rsToVator(sqlBf.toString(), new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszz_shgc_zzbbxssqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("xszzjehzExp");
	}

	/**
	 * @describe 助学贷款时间初始化
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdksjcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StandardOperation.delete("shgc_gjzxdk_sjb", new String[]{"1"}, new String[]{"1"}, request);
		String[] sqlT = new String[3];
		sqlT[0] = "insert into shgc_gjzxdk_sjb(xmmc,xydm) select '困难生' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[1] = "insert into shgc_gjzxdk_sjb(xmmc,xydm) select '国家助学贷款' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[2] = "insert into shgc_gjzxdk_sjb(xmmc,xydm) select '还款协议' xmmc,xydm from view_njxyzybj group by xydm";
		dao.runBatch(sqlT);
		return new ActionForward("/shgc_xszz.do?method=zxdksjList&go=go", false);
	}
	
	/**
	 * @describe 助学贷款申请时间设定
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdksjList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "bbdm||xydm";
		String tips = "学生资助 - 基础数据维护 - 助学贷款申请时间维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_shgc_gjzxdk_sjb";
		String[] colList = new String[] { "主键", "xmmc", "xymc", "xyrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限
		
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		map.put("xydm", xydm);
		
		String sql = "select xmmc||xydm 主键,a.* from view_shgc_gjzxdk_sjb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("zxdkxmList", xszzDao.getShgcZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("zxdksjList");
	}

	/**
	 * @describe 根据主键得到助学贷款时间设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdksjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmmc,xydm,xymc,xyrs,kssj,jssj from view_shgc_gjzxdk_sjb where xmmc||xydm=?";
		String[] outString = new String[] { "xmmc", "xydm", "xymc",
				"xyrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmmc    = Base.chgNull(request.getParameter("xmmc"), "", 1);
			String xydm    = Base.chgNull(request.getParameter("xydm"), "", 1);
			String kssj    = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj    = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_shgc_gjzxdk_sjb where xmmc||xydm=?",
					new String[] { xmmc + xydm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation
						.insert("shgc_gjzxdk_sjb", new String[] { "xmmc",
								"xydm", "kssj", "jssj" },
								new String[] { xmmc, xydm, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("shgc_gjzxdk_sjb", new String[] {
						"kssj", "jssj" }, new String[] { kssj,
						jssj }, "xmmc||xydm", xmmc + xydm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = xmmc + xydm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("zxdkxmList", xszzDao.getShgcZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxdksjEdit");
	}
	
	/**
	 * @describe 批量设置助学贷款申请时间
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdksjPlsz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if("save".equalsIgnoreCase(act)){
			kssj  = Base.chgNull(request.getParameter("kssj"), "", 1);
			jssj  = Base.chgNull(request.getParameter("jssj"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update shgc_gjzxdk_sjb set kssj='" + kssj
						+ "',jssj='" + jssj + "' where xmmc||xydm='" + pkT[i]
						+ "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zxdksjPlsz");
	}
}

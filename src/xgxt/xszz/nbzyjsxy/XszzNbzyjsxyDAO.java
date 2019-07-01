package xgxt.xszz.nbzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 宁波职业技术学院学生资助DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 周觅
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2009-07-13
 * </p>
 */
public class XszzNbzyjsxyDAO {
	DAO dao = DAO.getInstance();

	List<String> values = new ArrayList<String>();// 查询条件值

	/**
	 * 公用方法：获取查询条件
	 * 
	 * @param queryZxxsdkModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = new StringBuffer(" ");
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xn = queryModel.getXn();
		String nd = queryModel.getNd();
		String xh = DealString.toGBK(queryModel.getXh());
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String sfzh = DealString.toGBK(queryModel.getSfzh());
		String sj = DealString.toGBK(queryModel.getSj());
		String jsdm = queryModel.getJsdm();
		String xhyhm = DealString.toGBK(queryModel.getXhyhm());
		String dwlxdh = DealString.toGBK(queryModel.getDwlxdh());
		String bjrsh = queryModel.getBjrsh();
		String xysh = queryModel.getXysh();
		String xxsh = queryModel.getXxsh();
		String xm = DealString.toGBK(queryModel.getXm());
		String bm = DealString.toGBK(queryModel.getBm());
		String dw = DealString.toGBK(queryModel.getDw());
		String sfjk = DealString.toGBK(queryModel.getSfjk());

		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.trim().equals(""))) {
			xydm = userDep;
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}// end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		} else {
			if (userBj.size() != 0) {
				whereSql.append(" and bjdm in ('###'");
				for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
					whereSql.append(", '");
					whereSql.append(it.next());
					whereSql.append("'");
				}
				whereSql.append(" ) ");
			}
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(sfzh)) {
			whereSql.append(" and sfzh = ?");
			values.add(sfzh);
		}// end if
		if (!StringUtils.isNull(sj)) {
			whereSql.append(" and sj = ?");
			values.add(sj);
		}// end if
		if (!StringUtils.isNull(jsdm)) {
			whereSql.append(" and jsdm = ?");
			values.add(jsdm);
		}// end if
		if (!StringUtils.isNull(xhyhm)) {
			whereSql.append(" and xhyhm = ?");
			values.add(xhyhm);
		}// end if
		if (!StringUtils.isNull(dwlxdh)) {
			whereSql.append(" and dwlxdh = ?");
			values.add(dwlxdh);
		}// end if
		if (!StringUtils.isNull(bjrsh)) {
			whereSql.append(" and bjrsh = ?");
			values.add(bjrsh);
		}// end if
		if (!StringUtils.isNull(xysh)) {
			whereSql.append(" and xysh = ?");
			values.add(xysh);
		}// end if
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh = ?");
			values.add(xxsh);
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm = ?");
			values.add(xm);
		}// end if
		if (!StringUtils.isNull(bm)) {
			whereSql.append(" and bm like ?");
			values.add("%"+bm+"%");
		}// end if
		if (!StringUtils.isNull(dw)) {
			whereSql.append(" and dw like ?");
			values.add("%"+dw+"%");
		}// end if
		if (!StringUtils.isNull(sfjk)) {
			if ("1".equalsIgnoreCase(sfjk)){
				whereSql.append(" and jkcs > 0");
			}
			if ("0".equalsIgnoreCase(sfjk)){
				whereSql.append(" and jkcs = 0");
			}
		}// end if
		return whereSql;
	}

	/**
	 * 导出数据 getExpDate ---- 款导出数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getExpDate(QueryModel queryZxxsdkModel,
			String tabName, HttpServletRequest request) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();

		String sql = "select * from " + tabName + " where 1=1 ";
		StringBuffer whereSql = getWhereSql(queryZxxsdkModel, request);
		String[] colList = dao.getColumnName("select * from " + tabName
				+ " where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, colList));
		return rs;
	}

	/**
	 * 导出数据表头 getExpTit ---- 导出数据表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getExpTit(String tabName) throws Exception {
		String[] colList = dao.getColumnName("select * from " + tabName
				+ " where 1=2");// 获得列名数组
		return dao.getColumnNameCN(colList, tabName);
	}

	/**
	 * 获取学生信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny,to_char(sysdate,'yyyy-mm-dd') sqsj from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny",
				"mzmc", "zzmmmc", "rxny", "byny", "sqsj" };
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}
	
	/**
	 * 获取捐款人为学生或用户相关信息
	 * 
	 * @param xhyhm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByXhyhm(String xhyhm) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.xh xhyhm,a.xm,'宁波职业技术学院' dw,a.bjmc bm,a.sfzh from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xhyhm", "xm", "dw", "bm", "sfzh" };
		String[] sLen = dao.getOneRs(sql, new String[] { xhyhm }, colList);
		if (sLen == null) {
			sql = "select a.yhm xhyhm,a.xm,'宁波职业技术学院' dw,(select b.bmmc from zxbz_xxbmdm b where a.szbm=b.bmdm) bm from yhb a where a.yhm=?";
			colList = new String[] { "xhyhm", "xm", "dw", "bm" };
			sLen = dao.getOneRs(sql, new String[] { xhyhm }, colList);
			if (sLen != null) {
				for (int i = 0; i < colList.length; i++) {
					stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
							: "");
				}// end for
			}
		} else {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}
		return stuList;
	}
	
	/**
	 * 获取捐款人相关信息
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByGuid(String guid) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.guid,a.xhyhm,a.jsdm,a.jsmc,a.xm,a.dw,a.bm,a.ly,a.dwlxdh,a.sj,a.dwdz,a.jtdz,a.sfzh,a.dwyb,a.jtyb,a.bz,a.jkje from view_nbzy_syjj_jkrxxb a where a.guid = ?";
		String[] colList = new String[] { "guid", "xhyhm", "jsdm", "jsmc", "xm", "dw", "bm", "ly", "dwlxdh", "sj", "dwdz", "jtdz", "sfzh", "dwyb", "jtyb", "bz", "jkje" };
		String[] sLen = dao.getOneRs(sql, new String[] { guid }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}
		return stuList;
	}
	
	/**
	 * 获取捐款人相关信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByModel(JkrxxModel model) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		
		if ("".equalsIgnoreCase(model.getGuid()) || null == model.getGuid()) {
			String sql = "select a.guid,a.xhyhm,a.jsdm,a.jsmc,a.xm,a.dw,a.bm,a.ly,a.dwlxdh,a.sj,a.dwdz,a.jtdz,a.sfzh,a.dwyb,a.jtyb,a.bz,a.jkje from view_nbzy_syjj_jkrxxb a where rownum=1";
			String[] colList = new String[] { "guid", "xhyhm", "jsdm", "jsmc",
					"xm", "dw", "bm", "ly", "dwlxdh", "sj", "dwdz", "jtdz",
					"sfzh", "dwyb", "jtyb", "bz", "jkje" };
			
			StringBuffer whereSql = new StringBuffer(" ");
			List<String> valuesT = new ArrayList<String>();// 查询条件值
			String xhyhm = Base.chgNull(model.getXhyhm(), "", 1);
			String jsdm = Base.chgNull(model.getJsdm(), "", 1);
			String xm = Base.chgNull(model.getXm(), "", 1);
			String dw = Base.chgNull(model.getDw(), "", 1);
			String bm = Base.chgNull(model.getBm(), "", 1);
			String ly = Base.chgNull(model.getLy(), "", 1);
			String dwlxdh = Base.chgNull(model.getDwlxdh(), "", 1);
			String sj = Base.chgNull(model.getSj(), "", 1);
			String dwdz = Base.chgNull(model.getDwdz(), "", 1);
			String jtdz = Base.chgNull(model.getJtdz(), "", 1);
			String sfzh = Base.chgNull(model.getSfzh(), "", 1);
			String dwyb = Base.chgNull(model.getDwyb(), "", 1);
			String jtyb = Base.chgNull(model.getJtyb(), "", 1);
			String bz = Base.chgNull(model.getBz(), "", 1);
			
			if (!StringUtils.isNull(bz)) {
				whereSql.append(" and bz = ?");
				valuesT.add(bz);
			} else {
				whereSql.append(" and bz is null");
			}
			if (!StringUtils.isNull(jtyb)) {
				whereSql.append(" and jtyb = ?");
				valuesT.add(jtyb);
			} else {
				whereSql.append(" and jtyb is null");
			}
			if (!StringUtils.isNull(dwyb)) {
				whereSql.append(" and dwyb = ?");
				valuesT.add(dwyb);
			} else {
				whereSql.append(" and dwyb is null");
			}
			if (!StringUtils.isNull(jtdz)) {
				whereSql.append(" and jtdz = ?");
				valuesT.add(jtdz);
			} else {
				whereSql.append(" and jtdz is null");
			}
			if (!StringUtils.isNull(sfzh)) {
				whereSql.append(" and sfzh = ?");
				valuesT.add(sfzh);
			} else {
				whereSql.append(" and sfzh is null");
			}
			if (!StringUtils.isNull(dwdz)) {
				whereSql.append(" and dwdz = ?");
				valuesT.add(dwdz);
			} else {
				whereSql.append(" and dwdz is null");
			}
			if (!StringUtils.isNull(sj)) {
				whereSql.append(" and sj = ?");
				valuesT.add(sj);
			} else {
				whereSql.append(" and sj is null");
			}
			if (!StringUtils.isNull(dwlxdh)) {
				whereSql.append(" and dwlxdh = ?");
				valuesT.add(dwlxdh);
			} else {
				whereSql.append(" and dwlxdh is null");
			}
			if (!StringUtils.isNull(ly)) {
				whereSql.append(" and ly = ?");
				valuesT.add(ly);
			} else {
				whereSql.append(" and ly is null");
			}
			if (!StringUtils.isNull(bm)) {
				whereSql.append(" and bm = ?");
				valuesT.add(bm);
			} else {
				whereSql.append(" and bm is null");
			}
			if (!StringUtils.isNull(dw)) {
				whereSql.append(" and dw = ?");
				valuesT.add(dw);
			} else {
				whereSql.append(" and dw is null");
			}
			if (!StringUtils.isNull(xhyhm)) {
				whereSql.append(" and xhyhm = ?");
				valuesT.add(xhyhm);
			} else {
				whereSql.append(" and xhyhm is null");
			}
			if (!StringUtils.isNull(jsdm)) {
				whereSql.append(" and jsdm = ?");
				valuesT.add(jsdm);
			} else {
				whereSql.append(" and jsdm is null");
			}
			if (!StringUtils.isNull(xm)) {
				whereSql.append(" and xm = ?");
				valuesT.add(xm);
			} else {
				whereSql.append(" and xm is null");
			}
			
			String[] sLen = dao.getOneRs(sql+whereSql, valuesT != null ? valuesT
					.toArray(new String[0]) : new String[] {}, colList);
			if (sLen != null) {
				for (int i = 0; i < colList.length; i++) {
					stuList.put(colList[i], sLen[i] != null ? sLen[i]
							.toString() : "");
				}// end for
			}
		} else {
			stuList = getJkrxxByGuid(model.getGuid());
		}
		return stuList;
	}
	
	/**
	 * 删除捐款人信息
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delJkrxx(String[] pkT,HttpServletRequest request){
		String[] sqlT = new String[pkT.length*2];
		int j = 0;
		for (int i = 0; i < pkT.length; i++) {
			sqlT[j] = "delete nbzy_syjj_jkjl where jkrid='" + pkT[i] + "'";
			j++;
			sqlT[j] = "delete nbzy_syjj_jkrxxb where guid='" + pkT[i] + "'";
			j++;
		}
		try {
			dao.runBatch(sqlT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 捐款人信息结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJkrRes(QueryModel queryModel,HttpServletRequest request,XszzNbzyjsxyActionForm actionForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		String[] colList = new String[] { "pkT", "xm", "jsmc",
				"sfzh", "dw", "bm", "jkje" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql = "select pkT,xm,jsmc,sfzh,dw,bm,jkje from (select guid pkT,rownum r,xm,jsmc,sfzh,dw,bm,jkje from view_nbzy_syjj_jkrxxb where 1=1"
				+ whereSql
				+ ") where r<="
				+ (actionForm.getPages().getStart()) + (actionForm.getPages().getPageSize())
				+ " and r>"
				+ actionForm.getPages().getStart();
		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 捐款人统计结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJkrTjRes(QueryModel queryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		String[] colList = new String[] { "bm", "rs", "cs", "zje" };
		
		if ("3".equalsIgnoreCase(queryModel.getTjlx())) {
			dao.getProVal("{call pro_nbzy_syjj_jkrxxTj_zy(?)}",
					new String[] { queryModel.getXydm() }, new int[] {});
		} else {
			dao.getProVal("{call pro_nbzy_syjj_jkrxxTj(?)}",
					new String[] { queryModel.getTjlx() }, new int[] {});
		}

		sql = "select bm,rs,cs,zje from xszz_nbzyjsxy_syjjtj order by xh";
		resList = dao.rsToVator(sql, new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 捐款人信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getJkrxxResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sql = "select count(*) num from view_nbzy_syjj_jkrxxb where 1=1";
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 捐款人捐款记录结果
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJkjlList(String pkVal) {
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();
		String sql = "";

		String[] colList = new String[] { "hh", "pkT", "jkrq", "jkje",
				"sjh", "jkfs" };

		sql = "select rownum hh,guid pkT,jkrq,jkje,sjh,jkfs from nbzy_syjj_jkjl where jkrid=?";
		resList = dao.getList(sql, new String[] {pkVal}, colList);
		return resList;
	}
	
	/**
	 * 保存捐款人信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJkrxx(JkrxxModel model, String act, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String xhyhm = Base.chgNull(model.getXhyhm(), "", 1);
		String jsdm = Base.chgNull(model.getJsdm(), "", 1);
		String xm = Base.chgNull(model.getXm(), "", 1);
		String dw = Base.chgNull(model.getDw(), "", 1);
		String bm = Base.chgNull(model.getBm(), "", 1);
		String ly = Base.chgNull(model.getLy(), "", 1);
		String dwlxdh = Base.chgNull(model.getDwlxdh(), "", 1);
		String sj = Base.chgNull(model.getSj(), "", 1);
		String dwdz = Base.chgNull(model.getDwdz(), "", 1);
		String jtdz = Base.chgNull(model.getJtdz(), "", 1);
		String sfzh = Base.chgNull(model.getSfzh(), "", 1);
		String dwyb = Base.chgNull(model.getDwyb(), "", 1);
		String jtyb = Base.chgNull(model.getJtyb(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);

		if ("mod".equalsIgnoreCase(act)) {
			bFlag = StandardOperation.update("nbzy_syjj_jkrxxb", new String[] {
					"xhyhm", "jsdm", "xm", "dw", "bm", "ly", "dwlxdh", "sj",
					"dwdz", "jtdz", "sfzh", "dwyb", "jtyb", "bz" },
					new String[] { xhyhm, jsdm, xm, dw, bm, ly, dwlxdh, sj,
							dwdz, jtdz, sfzh, dwyb, jtyb, bz }, "guid", guid,
					request);
		} else {
			bFlag = StandardOperation.insert("nbzy_syjj_jkrxxb", new String[] {
					"xhyhm", "jsdm", "xm", "dw", "bm", "ly", "dwlxdh", "sj",
					"dwdz", "jtdz", "sfzh", "dwyb", "jtyb", "bz" },
					new String[] { xhyhm, jsdm, xm, dw, bm, ly, dwlxdh, sj,
							dwdz, jtdz, sfzh, dwyb, jtyb, bz }, request);
		}
		return bFlag;
	}
	
	/**
	 * 保存捐款记录
	 * @param param
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public boolean saveJklu(List<HashMap<String, String>> param, String guid)
			throws Exception {
		int length = param.size() + 1;
		String[] sqlT = new String[length];
		boolean doFlag = false;
		sqlT[0] = "delete nbzy_syjj_jkjl where jkrid='" + guid + "'";
		for (int i = 1; i < length; i++) {
			String jkrq = Base.chgNull(param.get(i - 1).get("jkrq"), "", 0);
			String jkje = Base.chgNull(param.get(i - 1).get("jkje"), "", 0);
			String sjh = Base.chgNull(param.get(i - 1).get("sjh"), "", 0);
			String jkfs = Base.chgNull(param.get(i - 1).get("jkfs"), "", 0);

			sqlT[i] = "insert into nbzy_syjj_jkjl(jkrid,jkrq,jkje,sjh,jkfs) values('"
					+ guid
					+ "','"
					+ jkrq
					+ "','"
					+ jkje
					+ "','"
					+ sjh
					+ "','"
					+ jkfs + "')";
		}
		int[] array = null;
		array = dao.runBatch(sqlT);
		doFlag = dao.checkBatch(array);
		return doFlag;
	}
	
	/**
	 * 获取思源基金信息
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getSyjjxxByGuid(String guid) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select guid,xh,xm,xb,csny,rxny,mzmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtyjsr,sqje,jtzz,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,spje,bjrxm,bjrdh,bjrsh,bjrshyj,xysh,xyshyj,xxsh,xxshyj,jkcs,jkzje from view_nbzyjsxy_syjj where guid = ?";
		String[] colList = new String[] { "guid", "xh", "xm", "xb", "csny",
				"rxny", "mzmc", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "lxdh", "chhzjl", "jthk", "jtyjsr", "sqje", "jtzz",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
				"jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
				"sqly", "sqsj", "spje", "bjrxm", "bjrdh", "bjrsh", "bjrshyj",
				"xysh", "xyshyj", "xxsh", "xxshyj", "jkcs", "jkzje" };
		String[] sLen = dao.getOneRs(sql, new String[] { guid }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		
		return stuList;
	}
	
	/**
	 * 保存思源基金申请信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSyjjxx(SyjjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String chhzjl = Base.chgNull(model.getChhzjl(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtyjsr = Base.chgNull(model.getJtyjsr(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(model.getJtcy1_gzdw(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(model.getJtcy2_gzdw(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(model.getJtcy3_gzdw(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(model.getJtcy4_gzdw(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(model.getJtcy5_gzdw(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		if (!"".equalsIgnoreCase(guid)) {
			String sHave = isSyjjDataCf(guid);
			if (!"3".equalsIgnoreCase(sHave)) {
				bFlag = StandardOperation.update("nbzyjsxy_syjj",
						new String[] { "xh", "lxdh", "chhzjl", "jthk",
								"jtyjsr", "sqje", "jtzz", "jtcy1_xm",
								"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
								"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
								"jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
								"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
								"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
								"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
								"jtcy5_gzdw", "sqly", "spje", "sqsj", "bjrxm",
								"bjrdh", "bjrsh", "bjrshyj", "xysh", "xyshyj",
								"xxsh", "xxshyj" }, new String[] { xh, lxdh,
								chhzjl, jthk, jtyjsr, sqje, jtzz, jtcy1_xm,
								jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy2_xm,
								jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy3_xm,
								jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy4_xm,
								jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy5_xm,
								jtcy5_nl, jtcy5_gx, jtcy5_gzdw, sqly, "", now,
								"", "", "未审核", "", "未审核", "", "未审核", "" },
						"guid", guid, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		} else {
			bFlag = StandardOperation.insert("nbzyjsxy_syjj", new String[] {
					"xh", "lxdh", "chhzjl", "jthk", "jtyjsr", "sqje", "jtzz",
					"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly" },
					new String[] { xh, lxdh, chhzjl, jthk, jtyjsr, sqje, jtzz,
							jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy2_xm,
							jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl,
							jtcy3_gx, jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_gx,
							jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, sqly }, request);
		}
		return bFlag;
	}
	
	/**
	 * 判断思源基金是否通过审核:-1 没有数据，0 有数据但没审核，1 保荐人审核通过，2 学院审核通过，3 学校审核通过
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public String isSyjjDataCf(String guid) throws Exception {
		String sFlag = "-1";
		String sql = "select bjrsh,xysh,xxsh from nbzyjsxy_syjj where guid = ?";
		String[] sT = dao.getOneRs(sql, new String[] { guid }, new String[]{"bjrsh", "xysh", "xxsh"});
		if (sT != null) {
			if ("通过".equalsIgnoreCase(sT[2])) {
				sFlag = "3";
			} else if ("通过".equalsIgnoreCase(sT[1])) {
				sFlag = "2";
			} else if ("通过".equalsIgnoreCase(sT[0])) {
				sFlag = "1";
			} else {
				sFlag = "0";
			}
		}
		return sFlag;
	}
	
	/**
	 * 获取思源基金信息
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getSyjjxxByModel(SyjjModel model) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		if (null == model.getGuid() || "".equals(model.getGuid())){
			String sql = "select guid,xh,xm,xb,csny,rxny,mzmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtyjsr,sqje,jtzz,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,spje,bjrxm,bjrdh,bjrsh,bjrshyj,xysh,xyshyj,xxsh,xxshyj,jkcs,jkzje from view_nbzyjsxy_syjj where rownum=1";
			String[] colList = new String[] { "guid", "xh", "xm", "xb", "csny",
					"rxny", "mzmc", "nj", "xydm", "xymc", "zydm", "zymc",
					"bjdm", "bjmc", "lxdh", "chhzjl", "jthk", "jtyjsr", "sqje",
					"jtzz", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly",
					"sqsj", "spje", "bjrxm", "bjrdh", "bjrsh", "bjrshyj",
					"xysh", "xyshyj", "xxsh", "xxshyj", "jkcs", "jkzje" };
			
			StringBuffer whereSql = new StringBuffer(" ");
			List<String> valuesT = new ArrayList<String>();// 查询条件值
			String xh = Base.chgNull(model.getXh(), "", 1);
			String chhzjl = Base.chgNull(model.getChhzjl(), "", 1);
			String jthk = Base.chgNull(model.getJthk(), "", 1);
			String jtyjsr = Base.chgNull(model.getJtyjsr(), "", 1);
			String sqje = Base.chgNull(model.getSqje(), "", 1);
			String jtzz = Base.chgNull(model.getJtzz(), "", 1);
			String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
			String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
			String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
			String jtcy1_gzdw = Base.chgNull(model.getJtcy1_gzdw(), "", 1);
			String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
			String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
			String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
			String jtcy2_gzdw = Base.chgNull(model.getJtcy2_gzdw(), "", 1);
			String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
			String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
			String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
			String jtcy3_gzdw = Base.chgNull(model.getJtcy3_gzdw(), "", 1);
			String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
			String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
			String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
			String jtcy4_gzdw = Base.chgNull(model.getJtcy4_gzdw(), "", 1);
			String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
			String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
			String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
			String jtcy5_gzdw = Base.chgNull(model.getJtcy5_gzdw(), "", 1);
			String sqly = Base.chgNull(model.getSqly(), "", 1);
			
			if (!StringUtils.isNull(sqly)) {
				whereSql.append(" and sqly = ?");
				valuesT.add(sqly);
			} else {
				whereSql.append(" and sqly is null");
			}
			if (!StringUtils.isNull(jtcy5_xm)) {
				whereSql.append(" and jtcy5_xm = ?");
				valuesT.add(jtcy5_xm);
			} else {
				whereSql.append(" and jtcy5_xm is null");
			}
			if (!StringUtils.isNull(jtcy5_nl)) {
				whereSql.append(" and jtcy5_nl = ?");
				valuesT.add(jtcy5_nl);
			} else {
				whereSql.append(" and jtcy5_nl is null");
			}
			if (!StringUtils.isNull(jtcy5_gx)) {
				whereSql.append(" and jtcy5_gx = ?");
				valuesT.add(jtcy5_gx);
			} else {
				whereSql.append(" and jtcy5_gx is null");
			}
			if (!StringUtils.isNull(jtcy5_gzdw)) {
				whereSql.append(" and jtcy5_gzdw = ?");
				valuesT.add(jtcy5_gzdw);
			} else {
				whereSql.append(" and jtcy5_gzdw is null");
			}
			if (!StringUtils.isNull(jtcy4_xm)) {
				whereSql.append(" and jtcy4_xm = ?");
				valuesT.add(jtcy4_xm);
			} else {
				whereSql.append(" and jtcy4_xm is null");
			}
			if (!StringUtils.isNull(jtcy4_nl)) {
				whereSql.append(" and jtcy4_nl = ?");
				valuesT.add(jtcy4_nl);
			} else {
				whereSql.append(" and jtcy4_nl is null");
			}
			if (!StringUtils.isNull(jtcy4_gx)) {
				whereSql.append(" and jtcy4_gx = ?");
				valuesT.add(jtcy4_gx);
			} else {
				whereSql.append(" and jtcy4_gx is null");
			}
			if (!StringUtils.isNull(jtcy4_gzdw)) {
				whereSql.append(" and jtcy4_gzdw = ?");
				valuesT.add(jtcy4_gzdw);
			} else {
				whereSql.append(" and jtcy4_gzdw is null");
			}
			if (!StringUtils.isNull(jtcy3_xm)) {
				whereSql.append(" and jtcy3_xm = ?");
				valuesT.add(jtcy3_xm);
			} else {
				whereSql.append(" and jtcy3_xm is null");
			}
			if (!StringUtils.isNull(jtcy3_nl)) {
				whereSql.append(" and jtcy3_nl = ?");
				valuesT.add(jtcy3_nl);
			} else {
				whereSql.append(" and jtcy3_nl is null");
			}
			if (!StringUtils.isNull(jtcy3_gx)) {
				whereSql.append(" and jtcy3_gx = ?");
				valuesT.add(jtcy3_gx);
			} else {
				whereSql.append(" and jtcy3_gx is null");
			}
			if (!StringUtils.isNull(jtcy3_gzdw)) {
				whereSql.append(" and jtcy3_gzdw = ?");
				valuesT.add(jtcy3_gzdw);
			} else {
				whereSql.append(" and jtcy3_gzdw is null");
			}
			if (!StringUtils.isNull(jtcy2_xm)) {
				whereSql.append(" and jtcy2_xm = ?");
				valuesT.add(jtcy2_xm);
			} else {
				whereSql.append(" and jtcy2_xm is null");
			}
			if (!StringUtils.isNull(jtcy2_nl)) {
				whereSql.append(" and jtcy2_nl = ?");
				valuesT.add(jtcy2_nl);
			} else {
				whereSql.append(" and jtcy2_nl is null");
			}
			if (!StringUtils.isNull(jtcy2_gx)) {
				whereSql.append(" and jtcy2_gx = ?");
				valuesT.add(jtcy2_gx);
			} else {
				whereSql.append(" and jtcy2_gx is null");
			}
			if (!StringUtils.isNull(jtcy2_gzdw)) {
				whereSql.append(" and jtcy2_gzdw = ?");
				valuesT.add(jtcy2_gzdw);
			} else {
				whereSql.append(" and jtcy2_gzdw is null");
			}
			if (!StringUtils.isNull(jtcy1_xm)) {
				whereSql.append(" and jtcy1_xm = ?");
				valuesT.add(jtcy1_xm);
			} else {
				whereSql.append(" and jtcy1_xm is null");
			}
			if (!StringUtils.isNull(jtcy1_nl)) {
				whereSql.append(" and jtcy1_nl = ?");
				valuesT.add(jtcy1_nl);
			} else {
				whereSql.append(" and jtcy1_nl is null");
			}
			if (!StringUtils.isNull(jtcy1_gx)) {
				whereSql.append(" and jtcy1_gx = ?");
				valuesT.add(jtcy1_gx);
			} else {
				whereSql.append(" and jtcy1_gx is null");
			}
			if (!StringUtils.isNull(jtcy1_gzdw)) {
				whereSql.append(" and jtcy1_gzdw = ?");
				valuesT.add(jtcy1_gzdw);
			} else {
				whereSql.append(" and jtcy1_gzdw is null");
			}
			if (!StringUtils.isNull(jtzz)) {
				whereSql.append(" and jtzz = ?");
				valuesT.add(jtzz);
			} else {
				whereSql.append(" and jtzz is null");
			}
			if (!StringUtils.isNull(sqje)) {
				whereSql.append(" and sqje = ?");
				valuesT.add(sqje);
			} else {
				whereSql.append(" and sqje is null");
			}
			if (!StringUtils.isNull(jtyjsr)) {
				whereSql.append(" and jtyjsr = ?");
				valuesT.add(jtyjsr);
			} else {
				whereSql.append(" and jtyjsr is null");
			}
			if (!StringUtils.isNull(jthk)) {
				whereSql.append(" and jthk = ?");
				valuesT.add(jthk);
			} else {
				whereSql.append(" and jthk is null");
			}
			if (!StringUtils.isNull(chhzjl)) {
				whereSql.append(" and chhzjl = ?");
				valuesT.add(chhzjl);
			} else {
				whereSql.append(" and chhzjl is null");
			}
			if (!StringUtils.isNull(xh)) {
				whereSql.append(" and xh = ?");
				valuesT.add(xh);
			} else {
				whereSql.append(" and xh is null");
			}
			whereSql.append(" and bjrsh='未审核'");
			
			
			String[] sLen = dao.getOneRs(sql + whereSql,
					valuesT != null ? valuesT.toArray(new String[0])
							: new String[] {}, colList);
			if (sLen != null) {
				for (int i = 0; i < colList.length; i++) {
					stuList.put(colList[i], sLen[i] != null ? sLen[i]
							.toString() : "");
				}// end for
			}
		} else {
			stuList = getSyjjxxByGuid(model.getGuid());
		}
		return stuList;
	}

	/**
	 * 删除思源基金信息
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delXyjj(String[] pkT, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nbzyjsxy_syjj where guid='" + pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "delete nbzyjsxy_syjj where guid='" + pkT[i]
							+ "' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete nbzyjsxy_syjj where guid='" + pkT[i]
							+ "' and xysh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改思源基金审核结果
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void modXyjj(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nbzyjsxy_syjj set xxsh='" + shjg
						+ "' where guid='" + pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update nbzyjsxy_syjj set xysh='" + shjg
							+ "' where guid='" + pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update nbzyjsxy_syjj set bjrsh='" + shjg
							+ "',bjrxm='" + userName
							+ "',spje=sqje where guid='" + pkT[i]
							+ "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 思源基金信息结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXyjjRes(QueryModel queryModel,HttpServletRequest request,XszzNbzyjsxyActionForm actionForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		String sql = "";

		String[] colList = new String[] { "bgcolor", "pkT", "xh", "xm", "sqje",
				"sqsj", "spje", "bjrsh", "xysh", "xxsh", "jkcs", "jkzje", "jkye" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,pkT,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from (select guid pkT,rownum r,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from view_nbzyjsxy_syjj where 1=1"
					+ whereSql
					+ ") where r<="
					+ (actionForm.getPages().getStart())
					+ (actionForm.getPages().getPageSize())
					+ " and r>"
					+ actionForm.getPages().getStart();
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,pkT,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from (select guid pkT,rownum r,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from view_nbzyjsxy_syjj where bjrsh='通过'"
							+ whereSql
							+ ") where r<="
							+ (actionForm.getPages().getStart())
							+ (actionForm.getPages().getPageSize())
							+ " and r>"
							+ actionForm.getPages().getStart();
				} else {
					sql = "select (case when nvl(bjrsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,pkT,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from (select guid pkT,rownum r,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from view_nbzyjsxy_syjj where 1=1"
							+ whereSql
							+ ") where r<="
							+ (actionForm.getPages().getStart())
							+ (actionForm.getPages().getPageSize())
							+ " and r>"
							+ actionForm.getPages().getStart();
				}
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,pkT,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from (select guid pkT,rownum r,xh,xm,sqje,sqsj,spje,bjrsh,xysh,xxsh,jkcs,jkzje,jkye from view_nbzyjsxy_syjj where xysh='通过'"
						+ whereSql
						+ ") where r<="
						+ (actionForm.getPages().getStart())
						+ (actionForm.getPages().getPageSize())
						+ " and r>"
						+ actionForm.getPages().getStart();
			}
		}
		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 思源基金统计结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXyjjTjRes(String userType,String userDep,ArrayList<String> userBj) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		
		String sql = "";

		String[] colList = new String[] { "bm", "zs", "zje" };
		
		if ("bjr".equalsIgnoreCase(userType)) {
			sql = "SELECT a.bjmc bm,(SELECT COUNT(*) FROM view_nbzyjsxy_syjj b WHERE b.xxsh='通过' AND b.bjdm=a.bjdm) zs,NVL((SELECT SUM(b.spje) FROM view_nbzyjsxy_syjj b WHERE b.xxsh='通过' AND b.bjdm=a.bjdm),'0') zje FROM view_njxyzybj a  WHERE 1=1 ";
			StringBuffer whereSQL = new StringBuffer(" bjdm in (");
			for (String bjdm : userBj) {
				whereSQL.append("'");
				whereSQL.append(bjdm);
				whereSQL.append("',");
			}
			sql += whereSQL.substring(0, whereSQL.lastIndexOf(",")) + ") GROUP BY bjdm,bjmc ORDER BY bjdm";
			resList = dao.rsToVator(sql, new String[] {}, colList);
		} else if ("xy".equalsIgnoreCase(userType)) {
			sql = "SELECT a.zymc bm,(SELECT COUNT(*) FROM view_nbzyjsxy_syjj b WHERE b.xxsh='通过' AND b.zydm=a.zydm) zs,NVL((SELECT SUM(b.spje) FROM view_nbzyjsxy_syjj b WHERE b.xxsh='通过' AND b.zydm=a.zydm),'0') zje FROM view_njxyzybj a  WHERE a.xydm=? GROUP BY zydm,zymc ORDER BY zydm";
			resList = dao.rsToVator(sql, new String[] { userDep }, colList);
		} else {
			sql = "SELECT a.xymc bm,(SELECT COUNT(*) FROM view_nbzyjsxy_syjj b WHERE b.xxsh='通过' AND b.xydm=a.xydm) zs,NVL((SELECT SUM(b.spje) FROM view_nbzyjsxy_syjj b WHERE b.xxsh='通过' AND b.xydm=a.xydm),'0') zje FROM view_njxyzybj a  GROUP BY xydm,xymc ORDER BY xydm";
			resList = dao.rsToVator(sql, new String[] {}, colList);
		}
		
		return resList;
	}
	
	/**
	 * 思源基金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXyjjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sql = "select count(*) num from view_nbzy_syjj_jkrxxb where 1=1";
		
		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select count(*) num from view_nbzyjsxy_syjj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql = "select count(*) num from view_nbzyjsxy_syjj where bjrsh='通过'";
				} else {
					sql = "select count(*) num from view_nbzyjsxy_syjj where 1=1";
				}
			} else {
				sql = "select count(*) num from view_nbzyjsxy_syjj where xysh='通过'";
			}
		}
		
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}

	/**
	 * 保存思源基金审核信息，成功返回TRUE，反之返回FALSE saveSyjjShxx ---- 保存思源基金审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSyjjShxx(SyjjModel model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType").toString();//SESSION中获取用户类型
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String bjrxm = Base.chgNull(model.getBjrxm(), "", 1);
		String bjrdh = Base.chgNull(model.getBjrdh(), "", 1);
		String bjrsh = Base.chgNull(model.getBjrsh(), "", 1);
		String bjrshyj = Base.chgNull(model.getBjrshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String spje = Base.chgNull(model.getSpje(), "", 1);
		String sHave = isSyjjDataCf(guid);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nbzyjsxy_syjj", new String[] {
					"xxsh", "xxshyj", "spje" }, new String[] { xxsh, xxshyj,
					spje }, "guid", guid, request);
		} else {
			if (userBj.size() == 0) {
				if ("3".equalsIgnoreCase(sHave)) {
					request.setAttribute("xxshjg", "pass");
				} else {
					bFlag = StandardOperation.update("nbzyjsxy_syjj",
							new String[] { "xysh", "xyshyj", "spje" },
							new String[] { xysh, xyshyj, spje }, "guid", guid,
							request);
				}
			} else {
				if ("2".equalsIgnoreCase(sHave)) {
					request.setAttribute("xyshjg", "pass");
				} else {
					bFlag = StandardOperation.update("nbzyjsxy_syjj",
							new String[] { "bjrxm", "bjrdh", "bjrsh",
									"bjrshyj", "spje" }, new String[] { bjrxm,
									bjrdh, bjrsh, bjrshyj, spje }, "guid",
							guid, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获得学生获得思源基金记录
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyjjList(String xh) {
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();
		String sql = "";

		String[] colList = new String[] { "hh", "sqsj", "sqje", "spje" };

		sql = "select rownum hh,sqsj,sqje,spje from view_nbzyjsxy_syjj where xh=? and xxsh='通过' order by sqsj";
		resList = dao.getList(sql, new String[] {xh}, colList);
		return resList;
	}
}

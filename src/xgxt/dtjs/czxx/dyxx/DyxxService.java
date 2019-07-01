package xgxt.dtjs.czxx.dyxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.DtjsTyService;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.form.SaveForm;
import xgxt.pjpy.czxx.PjpyDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class DyxxService extends DtjsTyService{

	DyxxDAO dao = new DyxxDAO();

	/**
	 * 设置所需列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CzxxDtjsForm myForm, HttpServletRequest request)
			throws Exception {
		dao.setList(myForm, request);
	}
	
	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	/**
	 * 获得党员相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, DyxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getDyxxList(tableName, model, colList);
	}

	/**
	 * 获得党员相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, String[] zd,
			String[] zdz, String[] colList) {
		ArrayList<String[]> list = null;
		if (zd != null && zdz != null && zd.length == zdz.length) {
			list = dao.getDyxxList(tableName, zd, zdz, colList);
		}
		return list;
	}
	
	/**
	 * 获得党员相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDyxxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getDyxxInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除党员相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delDyxx(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}

	/**
	 * 批量保存党员信息相关信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param arrzd(批量字段)
	 * @param onezd(单一字段)
	 * @param notnull(非空字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveDyxx(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存党员信息相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveDyxx(SaveForm form, DyxxModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}
	
	/**
	 * 更新党员相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean updateDyxx(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}
	
	/**
	 * 修改党员类型
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean changeDylx(DyxxModel model,
			HttpServletRequest request)
			throws Exception {
		
		boolean flag = false;
		String kssj = request.getParameter("gzkssj");	
		String lx = model.getLx();
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		String nd = model.getNd();
		String bz = model.getBz();
		String ybkssj = model.getKssj();
		String ybjssj = kssj;
		
		xn = Base.isNull(xn) ? Base.currXn : xn;
		xq = Base.isNull(xq) ? Base.currXq : xq;
		nd = Base.isNull(nd) ? Base.currNd : nd;
		String pkValue = xn + xq + xh;
		String query = "zzzt = 'yes'";
		
		String[] colList = new String[] { "kssj", "bz" };
		String[] inputList = null;
		String[] tableName = null;
		
		model.setKssj(kssj);
		model.setXn(xn);
		model.setXq(xq);
		model.setNd(nd);
		
		if ("入党申请".equalsIgnoreCase(lx)) {
			String jjfzrs = dao.getDyxxRs("rdjjfzxxb", "xh", xh, query);
			String fzdxrs = dao.getDyxxRs("czxx_fzdxb", "xh", xh, query);
			String ybdyrs = dao.getDyxxRs("ybdyxxb", "xh", xh, query);
			String zsdyrs = dao.getDyxxRs("dyxxb", "xh", xh, query);
			if (!"0".equalsIgnoreCase(jjfzrs)) {
				if (!"0".equalsIgnoreCase(fzdxrs)||!"0".equalsIgnoreCase(ybdyrs)||!"0".equalsIgnoreCase(zsdyrs)) {
					colList = new String[] { "kssj", "bz", "zzzt" };
					model.setZzzt("no");
					inputList = FormModleCommon.modelToStrings(colList, model);
				}
				flag = StandardOperation.update("rdjjfzxxb", colList, inputList,
						"xn||xq||xh", pkValue, request);
			}else{
				if (!"0".equalsIgnoreCase(fzdxrs)||!"0".equalsIgnoreCase(ybdyrs)||!"0".equalsIgnoreCase(zsdyrs)) {
					colList = new String[] { "xh", "xn", "xq", "nd",
							"kssj", "bz", "zzzt" };
					model.setZzzt("no");
				} else {
					colList = new String[] { "xh", "xn", "xq", "nd",
							"kssj", "bz" };
				}
				inputList = FormModleCommon.modelToStrings(colList, model);

				flag = StandardOperation.insert("rdjjfzxxb", colList, inputList,
						request);
			}	
		} else if ("积极分子".equalsIgnoreCase(lx)) {
			tableName = new String[] { "rdjjfzxxb" };
			String fzdxrs = dao.getDyxxRs("czxx_fzdxb", "xn||xq||xh", pkValue, "");
			String ybdyrs = dao.getDyxxRs("ybdyxxb", "xh", xh, query);
			String zsdyrs = dao.getDyxxRs("dyxxb", "xh", xh, query);
			if (!"0".equalsIgnoreCase(fzdxrs)) {
				if (!"0".equalsIgnoreCase(ybdyrs)||!"0".equalsIgnoreCase(zsdyrs)) {
					colList = new String[] { "kssj", "bz", "zzzt" };
					model.setZzzt("no");
					inputList = FormModleCommon.modelToStrings(colList, model);
				}
				flag = StandardOperation.update("czxx_fzdxb", colList, inputList,
						"xn||xq||xh", pkValue, request);
			}else{
				if (!"0".equalsIgnoreCase(ybdyrs)||!"0".equalsIgnoreCase(zsdyrs)) {
					colList = new String[] { "xh", "xn", "xq", "nd",
							"kssj", "bz", "zzzt" };
					model.setZzzt("no");
				} else {
					colList = new String[] { "xh", "xn", "xq", "nd",
							"kssj", "bz" };
				}
				inputList = FormModleCommon.modelToStrings(colList, model);

				flag = StandardOperation.insert("czxx_fzdxb", colList, inputList,
						request);
			}
		} else if ("发展对象".equalsIgnoreCase(lx)) {
			tableName = new String[] { "rdjjfzxxb", "czxx_fzdxb" };
			String ybdyrs = dao.getDyxxRs("ybdyxxb", "xn||xq||xh", pkValue, "");
			String zsdyrs = dao.getDyxxRs("dyxxb", "xh", xh, query);
			if (!"0".equalsIgnoreCase(ybdyrs)) {
				if (!"0".equalsIgnoreCase(zsdyrs)) {
					colList = new String[] { "kssj", "bz", "zzzt" };
					model.setZzzt("no");
					inputList = FormModleCommon.modelToStrings(colList, model);
				}
				flag = StandardOperation.update("ybdyxxb", colList, inputList,
						"xn||xq||xh", pkValue, request);
			}else{
				if (!"0".equalsIgnoreCase(zsdyrs)) {
					colList = new String[] { "xh", "xn", "xq", "nd",
							"kssj", "bz", "zzzt" };
					model.setZzzt("no");
				} else {
					colList = new String[] { "xh", "xn", "xq", "nd",
							"kssj", "bz" };
				}
				inputList = FormModleCommon.modelToStrings(colList, model);

				flag = StandardOperation.insert("ybdyxxb", colList, inputList,
						request);
			}
			
		} else if ("预备党员".equalsIgnoreCase(lx)) {
			
			tableName = new String[] { "rdjjfzxxb", "czxx_fzdxb","ybdyxxb" };
			String zsdyrs = dao.getDyxxRs("dyxxb", "xn||xq||xh", pkValue, "");
			
			if (!"0".equalsIgnoreCase(zsdyrs)) {
				flag = StandardOperation.update("dyxxb", new String[] { 
						"ybdykssj", "ybdyjssj", "rdsj", "zzsj", "bz" },
						new String[] { ybkssj, ybjssj, ybjssj, ybjssj, bz },
						"xn||xq||xh", pkValue, request);
			} else {
				flag = StandardOperation.insert("dyxxb", new String[] { "nd",
						"xn", "xq", "xh", "ybdykssj", "ybdyjssj", "rdsj",
						"zzsj", "bz" }, new String[] { nd, xn, xq, xh, ybkssj,
						ybjssj, ybjssj, ybjssj, bz }, request);
			}
			
		}

		if (flag) {
			flag = dao.saveOtherDylx(tableName, xh);
		}
		
		return flag;
	}
	
	/**
	 * 修改党员类型
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean changeDylx(String[] key, String lx,
			HttpServletRequest request) throws Exception {
		
		boolean flag = false;
		String[] colList = null;
		String[] table = null;
		String tableName = "";
		String pk = "";
		String pkValue = "";
		String query = "zzzt = 'yes'";
		String[] arrSql = new String[key.length * 2];
		
		String kssj = request.getParameter("gzkssj");
		String xh = "";
		String xn = "";
		String xq = "";
		String nd = "";
		String bz = "";
		String zzzt = "";
		String ybkssj = "";
		String ybjssj = kssj;
		
		int n = 0;
		
		List<HashMap<String, String>> dyxxList = null;
		if ("入党申请".equalsIgnoreCase(lx)) {
			tableName = "czxx_rdsqb";
			pk = "xh||sqsj";
			colList = new String[] { "xh" };
			dyxxList = dao.getDyxx(key, colList, tableName, pk,"distinct");
			if (dyxxList != null && dyxxList.size() > 0) {
				for (int i = 0; i < dyxxList.size(); i++) {
					HashMap<String, String> map = dyxxList.get(i);
					
					xh = map.get("xh");
					xn = Base.currXn;
					xq = Base.currXq;
					nd = Base.currNd;
					//bz = map.get("bz");
					pkValue = xn + xq + xh;
					
					String jjfzrs = dao.getDyxxRs("rdjjfzxxb", "xn||xq||xh", pkValue, "");
					String fzdxrs = dao.getDyxxRs("czxx_fzdxb", "xh", xh, query);
					String ybdyrs = dao.getDyxxRs("ybdyxxb", "xh", xh, query);
					String zsdyrs = dao.getDyxxRs("dyxxb", "xh", xh, query);
					zzzt = ("0".equalsIgnoreCase(fzdxrs) && "0".equalsIgnoreCase(ybdyrs) && "0".equalsIgnoreCase(zsdyrs)) ? "yes" : "no";
									
					if (!"0".equalsIgnoreCase(jjfzrs)) {
						arrSql[n] = "update rdjjfzxxb set zzzt='no' where xh='"
								+ xh + "'";
						n++;
						arrSql[n] = "update rdjjfzxxb set bz ='" + bz
								+ "',kssj ='" + kssj + "',zzzt='" + zzzt
								+ "' where xn||xq||xh='" + xn + xq + xh + "'";
						n++;
					} else {
						arrSql[n] = "insert into rdjjfzxxb (nd,xn,xq,xh,bz,kssj,zzzt) values ('"
								+ nd
								+ "','"
								+ xn
								+ "', '"
								+ xq
								+ "','"
								+ xh
								+ "','"
								+ bz
								+ "','"
								+ kssj
								+ "','"
								+ zzzt
								+ "')";
						n++;
					}
				}
			}
		
		} else if ("积极分子".equalsIgnoreCase(lx)) {
			table = new String[] { "rdjjfzxxb" };
			tableName = "rdjjfzxxb";
			pk = "xn||xq||xh";
			colList = new String[] { "xh", "xn", "xq", "nd", "bz" };
			dyxxList = dao.getDyxx(key, colList, tableName, pk,"");
			if (dyxxList != null && dyxxList.size() > 0) {
				for (int i = 0; i < dyxxList.size(); i++) {
					HashMap<String, String> map = dyxxList.get(i);
					
					xh = map.get("xh");
					xn = map.get("xn");
					xq = map.get("xq");
					nd = map.get("nd");
					//bz = map.get("bz");
					pkValue = xn + xq + xh;
					
					String fzdxrs = dao.getDyxxRs("czxx_fzdxb", "xn||xq||xh", pkValue, "");
					String ybdyrs = dao.getDyxxRs("ybdyxxb", "xh", xh, query);
					String zsdyrs = dao.getDyxxRs("dyxxb", "xh", xh, query);
					zzzt = ("0".equalsIgnoreCase(ybdyrs)&&"0".equalsIgnoreCase(zsdyrs)) ? "yes" : "no";
					
					if (!"0".equalsIgnoreCase(fzdxrs)) {
						arrSql[n] = "update czxx_fzdxb set zzzt='no' where xh='"
								+ xh + "'";
						n++;
						arrSql[n] = "update czxx_fzdxb set bz ='" + bz
								+ "',kssj ='" + kssj + "',zzzt='" + zzzt
								+ "' where xn||xq||xh='" + xn + xq + xh + "'";
						n++;
					} else {
						arrSql[n] = "insert into czxx_fzdxb (nd,xn,xq,xh,bz,kssj,zzzt) values ('"
								+ nd
								+ "','"
								+ xn
								+ "', '"
								+ xq
								+ "','"
								+ xh
								+ "','"
								+ bz
								+ "','"
								+ kssj
								+ "','"
								+ zzzt
								+ "')";
						n++;
					}
				}
			}
		} else if ("发展对象".equalsIgnoreCase(lx)) {
			table = new String[] { "rdjjfzxxb", "czxx_fzdxb" };
			tableName = "czxx_fzdxb";
			pk = "xn||xq||xh";
			colList = new String[] { "xh", "xn", "xq", "nd", "bz" };
			dyxxList = dao.getDyxx(key, colList, tableName, pk,"");
			if (dyxxList != null && dyxxList.size() > 0) {
				for (int i = 0; i < dyxxList.size(); i++) {
					HashMap<String, String> map = dyxxList.get(i);
					
					xh = map.get("xh");
					xn = map.get("xn");
					xq = map.get("xq");
					nd = map.get("nd");
					//bz = map.get("bz");
					pkValue = xn + xq + xh;
					
					String ybdyrs = dao.getDyxxRs("ybdyxxb", "xn||xq||xh", pkValue, "");
					String zsdyrs = dao.getDyxxRs("dyxxb", "xh", xh, query);
					zzzt = "0".equalsIgnoreCase(zsdyrs) ? "yes" : "no";
					
					if (!"0".equalsIgnoreCase(ybdyrs)) {
						arrSql[n] = "update ybdyxxb set zzzt='no' where xh='"
								+ xh + "'";
						n++;
						arrSql[n] = "update ybdyxxb set bz ='" + bz
								+ "',kssj ='" + kssj + "',zzzt='" + zzzt
								+ "' where xn||xq||xh='" + xn + xq + xh + "'";
						n++;
					} else {
						arrSql[n] = "insert into ybdyxxb (nd,xn,xq,xh,bz,kssj,zzzt) values ('"
								+ nd
								+ "','"
								+ xn
								+ "', '"
								+ xq
								+ "','"
								+ xh
								+ "','"
								+ bz
								+ "','"
								+ kssj
								+ "','"
								+ zzzt
								+ "')";
						n++;
					}
				}
			}
		} else if ("预备党员".equalsIgnoreCase(lx)) {
			table = new String[] { "rdjjfzxxb", "czxx_fzdxb", "ybdyxxb" };
			tableName = "ybdyxxb";
			pk = "xn||xq||xh";
			colList = new String[] { "xh", "xn", "xq", "nd", "bz","kssj" };
			dyxxList = dao.getDyxx(key, colList, tableName, pk,"");
			if (dyxxList != null && dyxxList.size() > 0) {
				for (int i = 0; i < dyxxList.size(); i++) {
					HashMap<String, String> map = dyxxList.get(i);
					
					xh = map.get("xh");
					xn = map.get("xn");
					xq = map.get("xq");
					nd = map.get("nd");
					//bz = map.get("bz");
					pkValue = xn + xq + xh;
					
					String zsdyrs = dao.getDyxxRs("dyxxb", "xn||xq||xh", pkValue, "");
					ybkssj = map.get("kssj");
					zzzt = "yes";
					
					if (!"0".equalsIgnoreCase(zsdyrs)) {
						arrSql[n] = "update dyxxb set zzzt='no' where xh='"
								+ xh + "'";
						n++;
						arrSql[n] = "update dyxxb set bz ='" + bz
								+ "',ybdyjssj ='" + ybjssj + "', ybdykssj = '"
								+ ybkssj + "', rdsj = '" + kssj
								+ "', zzsj = '" + kssj
								+ "',zzzt='yes' where xn||xq||xh='" + xn + xq
								+ xh + "'";
						n++;
					} else {
						arrSql[n] = "insert into dyxxb (nd,xn,xq,xh,bz,rdsj,zzsj,ybdykssj,ybdyjssj) values ('"
								+ nd
								+ "','"
								+ xn
								+ "', '"
								+ xq
								+ "','"
								+ xh
								+ "','"
								+ bz
								+ "','"
								+ kssj
								+ "','"
								+ kssj
								+ "','"
								+ ybkssj
								+ "','"
								+ ybjssj + "')";
						n++;
					}
				}
			}
		}

		flag = dao.saveArrDate(arrSql);
		if (flag) {
			flag = dao.saveOtherDylx(table, pk, key);
		}
		return flag;
	}

	/**
	 * @describe 删除所上传文件
	 * @author luo
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String scdz, String pk,
			String pkValue) throws Exception {
		return dao.fileDel(tableName, scdz, pk, pkValue);
	}
	
	/**
	 * 判断是否优秀团员
	 * 
	 * @author luojw
	 */
	public boolean isYxty(String xh) {
		return dao.isYxty(xh);
	}

	/**
	 * 获得思想汇报次数
	 * 
	 * @author luo
	 */
	public String getSxhb(String xh, String xn, String xq) {
		return dao.getSxhb(xh, xn, xq);
	}
	
	/**
	 * 获得培训成绩列表
	 * 
	 * @author luo
	 */
	public String getPxcjjsj(String xh) {
		StringBuffer sb = new StringBuffer();
		List<HashMap<String, String>> list = dao.getPxcjList(xh);
		for(int i=0; i<list.size();i++){
			HashMap<String, String> map = list.get(i);
			sb.append("培训时间：");
			sb.append(map.get("pxsj"));
			sb.append(",成绩：") ;
			sb.append( (Base.isNull(map.get("pxcj"))==true ? "无成绩" : map.get("pxcj")));
			sb.append(";");
		}
		return sb.toString();
	}
	
	/**
	 * 获取入党积极份子基本信息和入党申请时间
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryRdsqjbxx(String pk, String pkValue){
		return dao.selectRdsqjbxx(pk,pkValue);
	}
	
	/**
	 * 按学期查询学生所有相关信息
	 * @param String xh
	 * @param String xn
	 * @param String xq
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXsxgxxByXq(String xh, String xn, String xq){
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyDAO pjpyDao = new  PjpyDAO();
		
		//学习成绩班级排名
		map.put("xxcjbjpm",pjpyDao.queryStucjbjpmByxh(xh,xn,xq));
		//综合测评班级排名
		map.put("zhcpbjpm", pjpyDao.queryStuZcpmByxh(xh, xn, xq));
		//思想汇报份数
		map.put("sxhbfs", getSxhb(xh, xn, xq));
		//有无不及格科目
		map.put("bjgkm", pjpyDao.queryStuBjgkmByxh(xh, xn, xq));
		//考级考证情况
		map.put("kjkz", pjpyDao.queryStuDjksxxByxh(xh, xn, xq));
		//获奖情况
		map.put("hjxj", pjpyDao.queryStuHjxxByxh(xh, xn, xq));
		//有无违纪情况
		map.put("wjcf", pjpyDao.queryStuWjcfxxByxh(xh, xn, xq));		
		return map;
	}
	/**
	 * 更新至基本信息
	 * @return
	 * @throws Exception 
	 */
	public boolean gxzJbxx() throws Exception{
		boolean b=false;
		b=dao.gxzJbxx();
		return b;
	}
}

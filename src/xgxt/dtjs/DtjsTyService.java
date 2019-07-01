package xgxt.dtjs;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;
import common.GlobalsVariable;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.zjlg.zbgl.ZbglService;
import xgxt.dtjs.zjlg.zsdy.ZsdyService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyDAO;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class DtjsTyService{

	DtjsDAO dao = new DtjsDAO();

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(DtjsForm myForm, HttpServletRequest request,
			String manu)
			throws Exception {

		String xxdm = Base.xxdm;

		// ========================培训项目=============================
		if ("pxxx".equalsIgnoreCase(manu)) {
			List<HashMap<String, String>> pxxmList = dao.getDtjsList("pxxmdmb",
					"pxxmdm", "pxxmmc", "", "", "");// 培训项目
			List<HashMap<String, String>> zsywList = dao.getSelectList("ywlx");// 证书有无
			request.setAttribute("pxxmList", pxxmList);
			request.setAttribute("zsywList", zsywList);
		}
		// ========================培训项目 end=============================

		// ========================发展对象=============================
		else if ("fzdx".equalsIgnoreCase(manu)) {
			List<HashMap<String, String>> zsywList = dao.getSelectList("ywlx");// 证书有无
			request.setAttribute("zsywList", zsywList);
		}
		// ========================发展对象 end=============================

		// ========================支部党员=============================
		else if ("zbdy".equalsIgnoreCase(manu)) {

			// 浙江理工
			if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
				// 支部信息列表
				ZbglService zbService = new ZbglService();
				String xydm = myForm.getXydm();
				List<HashMap<String, String>> zbList = zbService
						.getZbList(xydm);
				request.setAttribute("zbList", zbList);

				// 支部党员类型
				List<HashMap<String, String>> dylxList = dao
						.getSelectList("zjlg_dylx");
				request.setAttribute("dylxList", dylxList);
			}
		}
		// ========================支部党员 end=============================
		
		// ========================团员信息=============================
		else if ("tyxx".equalsIgnoreCase(manu)) {
			
			// 学历列表
			List<HashMap<String, String>> xlList = dao.getSelectList("xllx");
			request.setAttribute("xlList", xlList);
			
			// 户口所在信息
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "hkshen", "hkshi", "hkxian",
					"jg" };
			map = CommonQueryDAO
					.commonQueryOne("view_xsxxb", colList, "xh", xh);
			
			String jg = map.get("jg");
			if (!Base.isNull(jg)) {
				String[] arrDq = jg.split("/");
				map.put("hkshen", (arrDq.length >= 1) ? arrDq[0] : "");
				map.put("hkshi", (arrDq.length >= 2) ? arrDq[1] : "");
				map.put("hkxian", (arrDq.length >= 3) ? arrDq[2] : "");
			}
			
			// 省列表
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);

			// 户口所在市列表
			List<HashMap<String, String>> hkshiList = stuDao.getShiList(
					map.get("hkshen")).get("shiList");
			request.setAttribute("hkshiList", hkshiList);

			// 户口所在县列表
			List<HashMap<String, String>> hkxianList = stuDao.getShiList(
					map.get("hkshi")).get("xianList");
			request.setAttribute("hkxianList", hkxianList);
		}
		// ========================团员信息 end=============================
		
		// ========================通用 =============================
		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {//浙江理工
			FormModleCommon.setNjXyZyBjListForDzb(request);
		} else {
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		}
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm", "mz", "zzmm" },
				request);// 自定义(目前：部门代码,民族政治面貌)

		// 在职状态
		List<HashMap<String, String>> zzztList = dao.getSelectList("yesorno");
		request.setAttribute("zzztList", zzztList);

		// 有无列表
		List<HashMap<String, String>> ywlxList = dao.getSelectList("ywlx");
		request.setAttribute("ywlxList", ywlxList);
		// ========================通用 end=============================
	}

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}

	/**
	 * 获得党员建设信息（列表）
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDtjsList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getDtjsList(tableName, model, colList, other_query);
	}

	/**
	 * 获得党员建设相关信息（详细）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 */
	public HashMap<String, String> getDtjsInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getDtjsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除党员建设信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delDtjs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * 保存党员建设相关信息（批量）
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
	public boolean saveDtjs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存党员信息相关信息（单条）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveDtjs(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新党员建设相关信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean updateDtjs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * @describe 删除所上传文件
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param dzzd(地址字段)
	 * 
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		return dao.fileDel(tableName, dzzd, pk, pkValue);
	}
	
	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		return dao.getNowTime(lx);
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	/**
	 * 党员身份转移（例：发展对象——>预备党员）
	 * 
	 * @author luojw
	 */
	public boolean changeDylx(String befTab, String aftTab, String[] pk,
			String[] zd, String[] key, String[] before, String[] after,String zyzd,String zysj)
			throws Exception {
		
		boolean flg = false;
		
		String groupBy = "xh";
		String orderBy = "xh";
		String distinct = "";
		String other_query = " and zzzt = 'yes'";
		String[] flag = new String[key.length];
		String[] arrXh = new String[key.length];
		String[] arrSql = new String[key.length * 2];
		String pkZd = "";
		int n = 0;
		if (key != null && key.length > 0) {
			for (int i = 0; i < pk.length; i++) {
				if (i == 0) {
					pkZd += pk[i];
				} else {
					pkZd += "||" + pk[i];
				}
			}
			
			List<HashMap<String, String>> befList = dao.getDyxx(key, zd, befTab, pkZd, distinct, orderBy);
			
			if (befList != null && befList.size() > 0) {
				for (int i = 0; i < befList.size(); i++) {
					arrXh[i] = befList.get(i).get("xh");
				}

				for (int i = 0; i < after.length; i++) {
					List<HashMap<String, String>> list = dao.getDyxxRs(key,
							new String[] { groupBy }, after[i], pkZd, distinct,
							groupBy, other_query);
					for (int j = 0; j < key.length; j++) {
						for (int k = 0; k < list.size(); k++) {
							if (arrXh[j]
									.equalsIgnoreCase(list.get(k).get("xh"))) {
								if (Integer.parseInt(list.get(k).get("num")) > 0) {
									if (!"true".equalsIgnoreCase(flag[j])) {
										flag[j] = "true";
									}
								}
							}
						}
						if (!"true".equalsIgnoreCase(flag[j])) {
							flag[j] = "false";
						}
					}
				}

				List<HashMap<String, String>> aftList = dao.getDyxxRs(key, new String[]{"xn","xq","xh"},aftTab, pkZd, distinct, "xn,xq,xh","");
				
				for (int i = 0; i < befList.size(); i++) {
					HashMap<String, String> map = befList.get(i);
					
					String zzzt = "true".equalsIgnoreCase(flag[i]) ? "no" : "yes";				
					boolean cz = false;
					
					if (aftList != null && aftList.size() > 0) {
						for (int j = 0; j < aftList.size(); j++) {
							if(cz == false){
								for (int k = 0; k < pk.length; k++) {
									if(aftList.get(j).get(pk[k]).equalsIgnoreCase(map.get(pk[k]))){
										cz = true;
									}else{
										cz = false;
										break;
									}
								}
							}
						}
					}
					
					if(cz == true){
						arrSql[n] = "update "+ aftTab+" set zzzt='no' where xh='"+map.get("xh")+"'";
						n++;
						
						StringBuffer sb = new StringBuffer();
						sb.append("update " + aftTab + " set ");
						for (int j = 0; j < zd.length; j++) {
							sb.append(zd[j]);
							sb.append("='");
							sb.append(map.get(zd[j]));
							sb.append("',");
						}
						sb.append("zzzt ='" + zzzt + "',");
						sb.append(zyzd + "='" + zysj + "'");
						
						for (int j = 0; j < pk.length; j++) {
							if(j==0){
								sb.append(" where ");
							}else{
								sb.append(" and ");
							}
							sb.append(pk[j]);
							sb.append("='");
							sb.append(map.get(pk[j]));
							sb.append("'");
						}
						
						arrSql[n] = sb.toString();
						n++;
					}else{
						StringBuffer sb = new StringBuffer();
						sb.append("insert into " + aftTab);
						
						for (int j = 0; j < zd.length; j++) {
							if (j == 0) {
								sb.append(" (");
								sb.append(zd[j]);
								sb.append(",");
								sb.append(zyzd);
							} else if (j == zd.length - 1) {
								sb.append(",");
								sb.append(zd[j]);
								sb.append(") ");
							} else {
								sb.append(",");
								sb.append(zd[j]);
							}
						}
						
						for (int j = 0; j < zd.length; j++) {
							if (j == 0) {
								sb.append(" values (");
								sb.append("'");
								sb.append(map.get(zd[j]));
								sb.append("'");
								sb.append(",'");
								sb.append(zysj);
								sb.append("'");	
							} else if (j == zd.length - 1) {
								sb.append(",'");
								sb.append(map.get(zd[j]));
								sb.append("')");
							} else {
								sb.append(",'");
								sb.append(map.get(zd[j]));
								sb.append("'");
							}
						}
						
						arrSql[n] = sb.toString();
						n++;
					}
				}
			}
		}
		flg = dao.saveArrDate(arrSql);
		
		if (flg) {
			flg = dao.saveOtherDylx(before, pkZd, key);
		}
		
		return flg;
	}
	
	/**
	 * 党员身份转移（例：发展对象——>预备党员）
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveZhdj(String pk, String[] pkValue, String bjmc,
			String zhdj, String zhsj) throws Exception {

		String xn = Base.currXn;//当前学年
		String xq = Base.currXq;//当前学期
		String nd = Base.currNd;//当前年度
		
		// 获取需要转移者的学号
		String tableName = getZhdjTable(bjmc);
		List<String> xhList = dao.getZyInfoList(tableName, pk, pkValue);
		
		String[] zyxh = null;
		
		if (xhList != null && xhList.size() > 0) {
			zyxh = new String[xhList.size()];
			for (int i = 0; i < xhList.size(); i++) {
				zyxh[i]=xhList.get(i);
			}
		}
		// 获得党团等级列表
		List<HashMap<String, String>> djList = dao.getDjList();
		
		// 获得学生党员信息列表
		List<HashMap<String, String>> xsdxInfoList = dao.getXsDyInfoList(zyxh);
		
		boolean result = false;

		StringBuffer sql = new StringBuffer();
		String[] exce = new String[xhList.size() * 6];
		int n = 0;

		if (xsdxInfoList != null && xsdxInfoList.size() > 0) {
			// 学生党团等级信息
			for (int j = 0; j < xsdxInfoList.size(); j++) {

				HashMap<String, String> map = xsdxInfoList.get(j);

				String xh = map.get("xh");

				if (djList != null && djList.size() > 0) {

					// 比转化等级高的级别是否有数据
					boolean flag = false;
					// 转换表
					String zhTable = "";

					for (int k = 0; k < djList.size(); k++) {

						HashMap<String, String> djMap = djList.get(k);
						// 级别
						String jb = djMap.get("jb");
						// 表名
						String table = djMap.get("tablename");
						// 数量
						String num = map.get(table);

						if (!Base.isNull(jb)) {

							// 转换等级
							if (jb.equalsIgnoreCase(zhdj)) {

								//获得转移目的表
								zhTable = table;
								//目标表所需要的时间字段
								String[] sj = getZhdjSj(table);
								
								sql = new StringBuffer();
								sql.append(" update ");
								sql.append(table);
								sql.append(" set zzzt = 'no'");
								sql.append(" where xh = '" + xh + "'");

								exce[n] = sql.toString();
								n++;

								sql = new StringBuffer();
								sql.append(" delete from ");
								sql.append(table);
								sql.append(" where xn = '" + xn + "'");
								sql.append(" and xq = '" + xq + "'");
								sql.append(" and xh = '" + xh + "'");

								exce[n] = sql.toString();
								n++;

								sql = new StringBuffer();
								sql.append(" insert into ");
								sql.append(table);
								sql.append(" (xn,xq,nd,xh");
								
								//拼接时间字段
								if (sj != null && sj.length > 0) {
									for (int i = 0; i < sj.length; i++) {
										sql.append("," + sj[i]);
									}
								}
								
								sql.append(" )values(");
								sql.append(" '" + xn + "',");
								sql.append(" '" + xq + "',");
								sql.append(" '" + nd + "',");
								sql.append(" '" + xh + "'");

								// 拼接时间字段
								if (sj != null && sj.length > 0) {
									for (int i = 0; i < sj.length; i++) {
										sql.append(",'" + zhsj + "'");
									}
								}
								
								sql.append(")");
								exce[n] = sql.toString();
								n++;
							}

							// 小于转移级别的级别
							if (Integer.parseInt(jb) < Integer.parseInt(zhdj)) {

								// 本表非入党申请表
								if (!GlobalsVariable.DTJS_RDSQB
										.equalsIgnoreCase(table)) {

									sql = new StringBuffer();
									sql.append(" update ");
									sql.append(table);
									sql.append(" set zzzt = 'no' ");
									sql.append(" where xh = '" + xh + "'");

									exce[n] = sql.toString();
									n++;
								}
							}

							// 大于转移级别的级别
							if (Integer.parseInt(jb) > Integer.parseInt(zhdj)) {

								if (!"0".equalsIgnoreCase(num)) {
									flag = true;
								}
							}
						}
					}

					if (flag) {
						sql = new StringBuffer();
						sql.append(" update ");
						sql.append(zhTable);
						sql.append(" set zzzt = 'no'");
						sql.append(" where xh = '" + xh + "'");

						exce[n] = sql.toString();
						n++;
					}
				}
			}

			result = dao.saveArrDate(exce);
		}
		return result;
	}
	
	/**
	 * 获得转换级别的表名
	 * 
	 * @author luojw
	 */
	public String getZhdjTable(String djmc) {

		String tableName = "";
		
		if ("入党申请".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_RDSQB;
		}else if("积极分子".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_JJFZB;
		}else if("发展对象".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_FZDXB;
		}else if("预备党员".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_YBDYB;
		}else if("正式党员".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_DYXXB;
		}
		
		return tableName;
	}
	
	/**
	 * 获得转换级别时间字段
	 * 
	 * @author luojw
	 */
	public String[] getZhdjSj(String tableName) {

		String[] sj = null;

		// 如果是正式党员的话需要返回两个时间字段
		if (GlobalsVariable.DTJS_DYXXB.equalsIgnoreCase(tableName)) {
			sj = new String[2];
		} else {
			sj = new String[1];
		}

		if (GlobalsVariable.DTJS_JJFZB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_JJFZB_KSSJ;
		} else if (GlobalsVariable.DTJS_FZDXB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_FZDXB_KSSJ;
		} else if (GlobalsVariable.DTJS_YBDYB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_YBDYB_KSSJ;
		} else if (GlobalsVariable.DTJS_DYXXB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_DYXXB_RDSJ;
			sj[1] = GlobalsVariable.DTJS_DYXXB_ZZSJ;
		}

		return sj;
	}
	
	/**
	 * 设置Request 的值
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String writeAble = request.getParameter("writeAble");
		String title = rForm.getTitle();

		// 视图名
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// 表名
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// 模块路径
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// 用户类型
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// 用户名
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// 用户所在部门
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(path)) {
			request.setAttribute("userDep", userDep);
		}

		// 操作类型
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}

		// 读写权相关
		if (Base.isNull(writeAble)) {
			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0]
					: "";
			if (Base.isNull(title)) {
				title = message != null && message.length >= 2 ? message[1]
						: "";
			}
		}
		request.setAttribute("writeAble", writeAble);

		// 模块标题
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// 主键
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// 模塊類型
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// 详细信息
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// 详细列表信息
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
		}

		// 其他字段
		String[] qtzd = rForm.getQtzd();
		// 其他字段值
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}
	
	/**
	 * 获得党团建设相关人数（接口）
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDtjsRsJk(DtjsForm myForm,String lx) {

		HashMap<String, String> map = new HashMap<String, String>();

		//班级团员数 
		if("ty".equalsIgnoreCase(lx)){
			String tyrs = getBjtyRs(myForm);
			map.put("tyrs", tyrs);
		}
		//班级党员数
		else if("dy".equalsIgnoreCase(lx)){
			map = getBjDyRs(myForm);
		}
		//入党和入党积极分子的人数、比例
		else if ("dybl".equalsIgnoreCase(lx)) {

			map = getBjDyRs(myForm);
			//班级人数
			String bjrs = dao.getBjRs(myForm);
			//党员人数
			String dyrs = map.get("dyrs");
			//预备党员人数
			String ybdyrs = map.get("ybdyrs");
			//积极分子人数
			String jjfzrs = map.get("jjfzrs");
			// 党员比例
			String dybl = "0";
			// 预备党员比例
			String ybdybl = "0";
			// 积极分子比例
			String jjfzbl = "0";
			// 计算比例
			if (!Base.isNull(bjrs) && !"0".equalsIgnoreCase(bjrs)) {
				dybl = String.valueOf(Math.round(Float.parseFloat(dyrs)
						/ Float.parseFloat(bjrs)*100))
						+ "%";
				ybdybl = String.valueOf(Math.round(Float.parseFloat(ybdyrs)
						/ Float.parseFloat(bjrs)*100))
						+ "%";
				jjfzbl = String.valueOf(Math.round(Float.parseFloat(jjfzrs)
						/ Float.parseFloat(bjrs)*100))
						+ "%";
			}
			map.put("bjrs", bjrs);
			map.put("dybl", dybl);
			map.put("ybdybl", ybdybl);
			map.put("jjfzbl", jjfzbl);
		}
		
		return map;
	}
	
	/**
	 * 获得班级团员人数
	 * 
	 * @author luojw
	 */
	public String getBjtyRs(DtjsForm myForm) {
		
		return dao.getBjtyRs(myForm);
	}
	

	/**
	 * 获得班级党员人数(包括预备党员,入党积极分子)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjDyRs(DtjsForm myForm) {
		
		return dao.getBjDyRs(myForm);
	}
	
	/**
	 * 设置用户类型MAP
	 * 
	 * @param request
	 * @param userName
	 * @param service
	 * @return
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> setUserTypeMap(HttpServletRequest request) {

		HttpSession session = request.getSession();

		// 用户名
		String userName = session.getAttribute("userName").toString();

		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 用户是否支部负责人
		String zbdm = getOneValue("zjlg_zbmc", "zbdm", "zgh", userName);
		boolean isZbfzr = Base.isNull(zbdm) ? false : true;

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("fdy", String.valueOf(fdyQx));
		map.put("bzr", String.valueOf(bzrQx));
		map.put("zbfzr", String.valueOf(isZbfzr));

		return map;
	}
	
	
	/**
	 * 获得用户类型相关限制条件
	 * 
	 * @param map
	 * @author luojw
	 */
	public String getUserTypeQuery(HashMap<String, String> map) {
		return dao.getUserTypeQuery(map);
	}
}

package xgxt.xsgygl.zgdzdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import xgxt.action.Base;
import xgxt.xsgygl.GyglTyForm;
import xgxt.xsgygl.GyglTyService;


public class GyglZgddService extends GyglTyService {

	GyglZgddDAO dao = new GyglZgddDAO();

	/**
	 * 设置图表统计结果集
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<Object> setTbtj(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 统计信息列表
		List<HashMap<String, String>> infoList = setTjxxList(model);

		List<Object> rs = new ArrayList<Object>();
		// 校区楼栋信息
		List<HashMap<String, String>> xqldList = getXqldInfoList(infoList);
		// 楼栋层数信息
		List<HashMap<String, String>> ldcsList = getLdcsInfoList(infoList);
		// 寝室信息
		List<HashMap<String, String>> csqsList = getQsInfoList(infoList);
		// 第一层
		if (xqldList != null && xqldList.size() > 0) {

			for (int i = 0; i < xqldList.size(); i++) {

				HashMap<String, Object> map = new HashMap<String, Object>();
				HashMap<String, String> xqld = xqldList.get(i);

				map.put("xqmc", xqld.get("xqmc"));
				map.put("ldmc", xqld.get("ldmc"));

				// 第二层
				List<Object> csList = new ArrayList<Object>();

				for (int j = 0; j < ldcsList.size(); j++) {
					HashMap<String, String> ldcs = ldcsList.get(j);
					if (ldcs.get("xqdm").equalsIgnoreCase(xqld.get("xqdm"))
							&& ldcs.get("lddm").equalsIgnoreCase(
									xqld.get("lddm"))) {

						HashMap<String, Object> obMap = new HashMap<String, Object>();

						// 第三层
						List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

						for (int k = 0; k < csqsList.size(); k++) {
							HashMap<String, String> qs = csqsList.get(k);
							if (ldcs.get("xqdm").equalsIgnoreCase(
									qs.get("xqdm"))
									&& ldcs.get("lddm").equalsIgnoreCase(
											qs.get("lddm"))
									&& ldcs.get("cs").equalsIgnoreCase(
											qs.get("cs"))) {
								qsList.add(qs);
							}
						}
						obMap.put("ldcs", ldcs);
						obMap.put("qsList", qsList);

						csList.add(obMap);

					}
				}

				map.put("csList", csList);

				rs.add(map);
			}
		}

		return rs;
	}
	
	/**
	 * 获得校区楼栋信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXqldInfoList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getXqldInfoList(model);
	}
	
	/**
	 * 获得校区楼栋信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXqldInfoList(
			List<HashMap<String, String>> list) {

		List<HashMap<String, String>> xqldList = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < list.size(); i++) {

			HashMap<String, String> after_map = list.get(i);
			// 校区代码
			String after_xqdm = after_map.get("xqdm");
			// 校区名称
			String after_xqmc = after_map.get("xqmc");
			// 楼栋代码
			String after_lddm = after_map.get("lddm");
			// 楼栋名称
			String after_ldmc = after_map.get("ldmc");

			boolean flag = true;
			
			if (i > 0) {
				
				HashMap<String, String> before_map = list.get(i - 1);
				// 校区代码
				String before_xqdm = before_map.get("xqdm");
				// 校区名称
				String before_xqmc = before_map.get("xqmc");
				// 楼栋代码
				String before_lddm = before_map.get("lddm");
				// 楼栋名称
				String before_ldmc = before_map.get("ldmc");
				
				if(after_xqdm.equalsIgnoreCase(before_xqdm) &&
					after_xqmc.equalsIgnoreCase(before_xqmc) &&
					after_lddm.equalsIgnoreCase(before_lddm) &&
					after_ldmc.equalsIgnoreCase(before_ldmc) ){
					
					flag = false;
					
				}
			}
			
			if(flag){
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xqdm", after_xqdm);
				map.put("xqmc", after_xqmc);
				map.put("lddm", after_lddm);
				map.put("ldmc", after_ldmc);
				
				xqldList.add(map);
			}
		}
		
		return xqldList;
	}
	
	/**
	 * 获得楼栋层数信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getLdcsInfoList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getLdcsInfoList(model);
	}
	
	/**
	 * 获得楼栋层数信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getLdcsInfoList(
			List<HashMap<String, String>> list) {

		List<HashMap<String, String>> ldcsList = new ArrayList<HashMap<String, String>>();

		// 不需要统计的列
		String[] noTj = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {

			if (!"no".equalsIgnoreCase(noTj[i])) {
				HashMap<String, String> before_map = list.get(i);

				// 校区代码
				String before_xqdm = before_map.get("xqdm");
				// 楼栋代码
				String before_lddm = before_map.get("lddm");
				// 层数
				String before_cs = before_map.get("cs");
				// 寝室数目
				int fjs = 1;
				// 床位数
				String before_cws = before_map.get("cws");
				// 空床位数
				String before_kcws = before_map.get("kcws");

				for (int j = i + 1; j < list.size(); j++) {

					HashMap<String, String> after_map = list.get(j);

					// 校区代码
					String after_xqdm = after_map.get("xqdm");
					// 楼栋代码
					String after_lddm = after_map.get("lddm");
					// 层数
					String after_cs = after_map.get("cs");
					// 床位数
					String after_cws = after_map.get("cws");
					// 空床位数
					String after_kcws = after_map.get("kcws");

					if (after_xqdm.equalsIgnoreCase(before_xqdm)
							&& after_lddm.equalsIgnoreCase(before_lddm)
							&& after_cs.equalsIgnoreCase(before_cs)) {

						noTj[j] = "no";

						// 累加房间数
						fjs++;
						// 累加床位数
						before_cws = String.valueOf(Integer.parseInt(before_cws)
								+ Integer.parseInt(after_cws));
						// 累加空床位数
						before_kcws = String.valueOf(Integer
								.parseInt(before_kcws)
								+ Integer.parseInt(after_kcws));
					}
				}
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xqdm", before_xqdm);
				map.put("lddm", before_lddm);
				map.put("cs", before_cs);
				map.put("fjs", String.valueOf(fjs));
				map.put("cws", String.valueOf(before_cws));
				map.put("kcws", String.valueOf(before_kcws));

				ldcsList.add(map);
			}
		}

		return ldcsList;
	}
	
	/**
	 * 获得寝室信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getQsInfoList(List<HashMap<String, String>> list)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		List<HashMap<String, String>> qsInfolist = new ArrayList<HashMap<String, String>>();
		
		for (HashMap<String, String> map : list) {
			
//			String xqdm = map.get("xqdm");//校区代码
//			String ssbh = map.get("ssbh");//宿舍编号
//			String lddm = map.get("lddm");//楼栋代码
//			String cs = map.get("cs");//层数
//			String qsh = map.get("qsh");//寝室号
			//String sxnj = map.get("sxnj");//缩写年级
			String xymc = map.get("xymc");//学院名称
			String qtxy = map.get("qtxy");//其他学院
			//String color = map.get("color");//颜色
			
			//存在混住情况
			if (!Base.isNull(qtxy) && qtxy.length() > 0) {
				
				String[] fp = Base.isNull(xymc) ? null : xymc.split("/");
				String[] qt = qtxy.split("/");

				int size_fp = (fp != null && fp.length > 0) ? fp.length : 0;
				int size_qt = (qt != null && qt.length > 0) ? qt.length : 0;
				int n = 0;
				
				String[][] arr_xynj = new String[size_fp + size_qt][2];
				
				if (fp != null && fp.length > 0) {

					for (int i = 0; i < fp.length; i++) {
						String xynj = fp[i];
						String xy = "";
						xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								0, xynj.length() - 4);
						String nj = "";
						nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								xynj.length() - 3, xynj.length() - 1);

						arr_xynj[n][0] = xy;
						arr_xynj[n][1] = nj;

						n++;
					}

					for (int i = 0; i < qt.length; i++) {

						String xynj = qt[i];
						String xy ="";
						String nj = "";
						xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								0, xynj.length() - 4);
						nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								xynj.length() - 3, xynj.length() - 1);
						
						arr_xynj[n][0] = xy;
						arr_xynj[n][1] = nj;

						n++;
					}
				} else {
					for (int i = 0; i < qt.length; i++) {
						String xynj = qt[i];
						String xy = "";
						String nj = "";
						xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								0, xynj.length() - 4);
						nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								xynj.length() - 3, xynj.length() - 1);
						arr_xynj[i][0] = xy;
						arr_xynj[i][1] = nj;
					}
				}
				xymc = getXymc(arr_xynj);
			}
			//不存在混住情况
			else{
				
				String[] fp = Base.isNull(xymc) ? null : xymc.split("/");

				int size_fp = (fp != null && fp.length > 0) ? fp.length : 0;
				int n = 0;

				String[][] arr_xynj = new String[size_fp][2];
				
				for (int i = 0; i < size_fp; i++) {
					String xynj = fp[i];
					//System.out.println(xynj);
					String xy = "";
					xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(0,
							xynj.length() - 4);
					String nj = "";
					nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
							xynj.length() - 3, xynj.length() - 1);

					arr_xynj[n][0] = xy;
					arr_xynj[n][1] = nj;

					n++;
				}
				
				xymc = getXymc(arr_xynj);
			}
			map.put("xymc", xymc);
			qsInfolist.add(map);
		}
		
		return qsInfolist;
	}
	
	/**
	 * 获得拼接学院
	 * 
	 * @author luojw
	 */
	public String getXymc(String[][] xynj) {
		
		StringBuffer qsxy = new StringBuffer();//寝室学院;
		
		if (xynj != null && xynj.length > 0) {

			String[] xy = new String[xynj.length];
			String[][] nj = new String[xynj.length][2];

			int n = 0;
			int m = 0;
			
			for (int i = 0; i < xynj.length; i++) {
				
				String xymc = Base.isNull(xynj[i][0]) ? "" : xynj[i][0];
				String sxnj = Base.isNull(xynj[i][0]) ? "" : xynj[i][1];

				boolean flag = true;
				
				for (int j = 0; j < n; j++) {
					if (xymc.equalsIgnoreCase(xy[j])) {
						flag = false;
						break;
					}
				}
				nj[m][0] = xymc;
				nj[m][1] = sxnj;
				m++;
				
				if (flag) {
					xy[n] = xymc;
					n++;
				}
			}
			
			if (xy != null && xy.length > 0) {

				for (int i = 0; i < xy.length; i++) {

					if (!Base.isNull(xy[i])) {
						if (i != 0) {
							qsxy.append("/");
						}
						qsxy.append(xy[i]);
						qsxy.append("(");
						if (nj != null && nj.length > 0) {

							StringBuffer sb_nj = new StringBuffer();// 寝室年级;

							for (int j = 0; j < nj.length; j++) {
								if (!Base.isNull(nj[j][0])) {
									if (nj[j][0].equalsIgnoreCase(xy[i])) {
										if (!Base.isNull(sb_nj.toString())) {
											sb_nj.append(",");
										}
										sb_nj.append(nj[j][1]);
									}
								}
							}
							qsxy.append(sb_nj);
						}
						qsxy.append(")");
					}
				}
			}
		}
		
		return qsxy.toString();
		
	}
	
	/**
	 * 获得统计基本信息
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getTjjbxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getTjjbxxList(model);
	}
	
	/**
	 * 获得其他信息
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getQtxyxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getQtxyxxList(model);
	}
	
	/**
	 * 拼接统计信息
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> setTjxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		//统计基本信息
		List<HashMap<String, String>> list =getTjjbxxList(model);
		List<HashMap<String, String>> tjjbxxList = new ArrayList<HashMap<String, String>>();
		//统计其他信息
		List<HashMap<String, String>> qtxxList =getQtxyxxList(model);
		//合并后的统计信息
		List<HashMap<String, String>> infoList = new ArrayList<HashMap<String, String>>();
		
		// 不需要统计的列
		String[] noTj = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {

			if (!"no".equalsIgnoreCase(noTj[i])) {
				HashMap<String, String> before_map = list.get(i);

				String before_bxynjrs = before_map.get("bxynjrs");
				String before_cs = before_map.get("cs");
				String before_cws = before_map.get("cws");
				String before_fpbj = before_map.get("fpbj");
				String before_lddm = before_map.get("lddm");
				String before_ldmc = before_map.get("ldmc");
				String before_nj = before_map.get("nj");
				String before_qsh = before_map.get("qsh");
				String before_ssbh = before_map.get("ssbh");
				String before_wxsrs = before_map.get("wxsrs");
				String before_xqdm = before_map.get("xqdm");
				String before_xqmc = before_map.get("xqmc");
				String before_xydm = before_map.get("xydm");
				String before_xymc = before_map.get("xymc");

				for (int j = i + 1; j < list.size(); j++) {
					HashMap<String, String> after_map = list.get(j);
					String after_cs = after_map.get("cs");
					String after_lddm = after_map.get("lddm");
					String after_qsh = after_map.get("qsh");
					String after_xymc = after_map.get("xymc");
					
					if (after_qsh.equalsIgnoreCase(before_qsh)
							&& after_lddm.equalsIgnoreCase(before_lddm)
							&& after_cs.equalsIgnoreCase(before_cs)) {

						noTj[j] = "no";

						// 累加学院
						before_xymc = before_xymc + "/" + after_xymc;
					}
				}
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bxynjrs", before_bxynjrs);
				map.put("cs", before_cs);
				map.put("cws", before_cws);
				map.put("fpbj", before_fpbj);
				map.put("lddm", before_lddm);
				map.put("ldmc", before_ldmc);
				map.put("nj", before_nj);
				map.put("qsh", before_qsh);
				map.put("ssbh", before_ssbh);
				map.put("wxsrs", before_wxsrs);
				map.put("xqdm", before_xqdm);
				map.put("xqmc", before_xqmc);
				map.put("xydm", before_xydm);
				map.put("xymc", before_xymc);

				tjjbxxList.add(map);
			}
		}
		
		for (HashMap<String, String> jbMap : tjjbxxList) {
			
			String xqdm = jbMap.get("xqdm");
			String lddm = jbMap.get("lddm");
			String cs = jbMap.get("cs");
			String qsh = jbMap.get("qsh");
			String cws = jbMap.get("cws");
			String bxynjrs = jbMap.get("bxynjrs");
			String wxsrs = jbMap.get("wxsrs");
			String fpbj = jbMap.get("fpbj");
			
			//其他学院人数
			String qtxynjrs = "0";
			
			//其他学院名称
			String qtxy = "";
			
			for (HashMap<String, String> qtMap : qtxxList) {
				
				String qt_xqdm = qtMap.get("xqdm");
				String qt_lddm = qtMap.get("lddm");
				String qt_cs = qtMap.get("cs");
				String qt_qsh = qtMap.get("qsh");
				String qt_yzrs = qtMap.get("yzrs");
				String qt_xymc = qtMap.get("xymc");
					
				//同寝室
				if(xqdm.equalsIgnoreCase(qt_xqdm) &&
						lddm.equalsIgnoreCase(qt_lddm) &&
						cs.equalsIgnoreCase(qt_cs) &&
						qsh.equalsIgnoreCase(qt_qsh)){
					
					// 入住总人数
					qtxynjrs = String.valueOf(Integer.parseInt(qtxynjrs)
							+ Integer.parseInt(qt_yzrs));
					
					// 其他学院
					qtxy += qt_xymc+"/";
				}
				
				//不同寝室
				if ((!xqdm.equalsIgnoreCase(qt_xqdm)
						|| !lddm.equalsIgnoreCase(qt_lddm)
						|| !cs.equalsIgnoreCase(qt_cs)
						|| !qsh.equalsIgnoreCase(qt_qsh))
						&& !"0".equalsIgnoreCase(qtxynjrs)) {
					break;
				}
			}
			
			qtxy = Base.isNull(qtxy) ? "" : qtxy
					.substring(0, qtxy.length() - 1);
			
			//空床位
			String kcws = String.valueOf(Integer.parseInt(cws)
					- Integer.parseInt(qtxynjrs) - Integer.parseInt(bxynjrs)
					- Integer.parseInt(wxsrs));
			
			//颜色
			String color = "";
			
			if (Integer.parseInt(kcws) < 0) {// 异常数据寝室
				color = "blue";
			} else if ("保留".equalsIgnoreCase(fpbj)) {// 是否保留
				color = "green";
			} else if (Integer.parseInt(wxsrs) > 0) {// 是否存在外校生
				color = "#800080";
			} else if ("0".equalsIgnoreCase(qtxynjrs)
					&& "0".equalsIgnoreCase(bxynjrs)
					&& "0".equalsIgnoreCase(wxsrs)) {// 是否空寝室
				color = "red";
			} else if (Integer.parseInt(kcws) > 0) {// 是否空闲寝室
				color = "orange";
			} else{
				color = "black";
			}
			
			jbMap.put("qtxynjrs", qtxynjrs);
			jbMap.put("qtxy", qtxy);
			jbMap.put("kcws", kcws);
			jbMap.put("color", color);
			
			infoList.add(jbMap);
		}
		
		return infoList;
	}
}

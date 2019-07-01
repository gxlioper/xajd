package xgxt.gygl.qsgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;
import xgxt.gygl.cwgl.GyglCwglDAO;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.gygl.gywh.DelDataDetect;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.utils.CommonQueryDAO;

import common.exception.SystemException;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_寝室管理_Service类
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

public class GyglQsglService extends GyglCommService {

	GyglQsglDAO dao = new GyglQsglDAO();

	/**
	 * 获得自动分配部门列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpBmList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 分配对象
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		ArrayList<String[]> list = null;

		SearchService searchService = new SearchService();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院

			list = dao.getZdfpXyList(model, user, colList, searchTj, inputV);

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院

			list = dao.getZdfpNjXyList(model, user, colList, searchTj, inputV);

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业

			list = dao.getZdfpNjZyList(model, user, colList, searchTj, inputV);

		} else {// 分配对象为班级

			list = dao.getZdfpBjList(model, user, colList, searchTj, inputV);

		}

		return list;
	}

	/**
	 * 创建床位信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void creatGyglCwxx(GyglQsglForm model, User user) throws Exception {

		// 宿舍信息表
		String tableName = "ssxxb";
		String[] colList = new String[] { "lddm", "cs", "qsh", "cws" };
		String query = " order by lddm,cs,qsh";
		List<HashMap<String, String>> qsList = getRsList(tableName, query,
				new String[] {}, colList, "");

		// 批量字段
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "cwh" };

		// 楼栋
		ArrayList<String> ldList = new ArrayList<String>();
		// 层数
		ArrayList<String> csList = new ArrayList<String>();
		// 寝室号
		ArrayList<String> qshList = new ArrayList<String>();
		// 床位号
		ArrayList<String> cwhList = new ArrayList<String>();

		if (qsList != null && qsList.size() > 0) {

			for (int i = 0; i < qsList.size(); i++) {

				HashMap<String, String> map = qsList.get(i);
				// 楼栋代码
				String lddm = map.get("lddm");
				// 楼栋代码
				String cs = map.get("cs");
				// 楼栋代码
				String qsh = map.get("qsh");
				// 可住人床位数
				String cws = map.get("cws");

				if (!Base.isNull(cws)) {
					for (int j = 0; j < Integer.parseInt(cws); j++) {
						ldList.add(lddm);
						csList.add(cs);
						qshList.add(qsh);
						cwhList.add(String.valueOf(j+1));
					}
				}
			}

			model.setLddm(ldList.toArray(new String[] {}));
			model.setCs(csList.toArray(new String[] {}));
			model.setQsh(qshList.toArray(new String[] {}));
			model.setCwh(cwhList.toArray(new String[] {}));

			tableName = "xg_gygl_cwxxb";
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk("1");
			saveForm.setPkValue(new String[] { "1" });
			saveForm.setArrzd(arrzd);

			saveInfoToDb(saveForm, model, user);
		}
	}

	/**
	 * 保存寝室自动分配
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String saveQszdfp(GyglQsglForm model, User user) throws Exception {

		// 分配对象
		String fpdx = model.getFpdx();
		// 分配对象代码
		String[] fpdx_dm = model.getPrimarykey_checkVal();
		// 需分配人数
		String[] xfprs = model.getXfprs();
		// 寝室分配表
		String tableName = "xg_gygl_qsfpb";
		// 主键
		String pk = "lddm||cs||qsh";
		// 主键值
		ArrayList<String> pkValueList = new ArrayList<String>();
		// 单个字段
		String[] onezd = new String[] { "fpdx", "sjly" };
		// 批量字段
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "nj", "bmdm" };
		// 数据来源
		String sjly = "自动";

		List<HashMap<String, String>> kfpQsList = getKfpQsList(model,user);

		// 楼栋
		ArrayList<String> ldList = new ArrayList<String>();
		// 层数
		ArrayList<String> csList = new ArrayList<String>();
		// 寝室号
		ArrayList<String> qsList = new ArrayList<String>();
		// 年级
		ArrayList<String> njList = new ArrayList<String>();
		// 部门
		ArrayList<String> bmList = new ArrayList<String>();	

		// 已分配寝室
		int yfpqs = 0;
		
		if (fpdx_dm != null && fpdx_dm.length > 0) {

			for (int i = 0; i < fpdx_dm.length; i++) {

				// 分配年级
				String fpnj = "";
				// 分配部门
				String fpbm = "";
				// 需分配人数
				int xfp = Integer.parseInt(xfprs[i]);
				// 已分配
				int yfp = 0;
				
				if (fpdx_dm[i].split("!!@@!!").length > 1) {
					fpnj = fpdx_dm[i].split("!!@@!!")[0];
					fpbm = fpdx_dm[i].split("!!@@!!")[1];
				} else {
					fpbm = fpdx_dm[i].split("!!@@!!")[0];
				}
					
				if (kfpQsList != null && kfpQsList.size() > 0) {

					for (int j = 0; j < kfpQsList.size(); j++) {
						HashMap<String, String> map = kfpQsList.get(j);
						String fpbj = map.get("fpbj");
						if (!"yes".equalsIgnoreCase(fpbj)) {
							// 楼栋代码
							String lddm = map.get("lddm");
							// 楼栋代码
							String cs = map.get("cs");
							// 楼栋代码
							String qsh = map.get("qsh");
							// 可住人床位数
							String kzrcws = map.get("kzrcws");

							if (yfp >= xfp) {
								break;
							} else {
								kfpQsList.get(j).put("fpbj", "yes");
								yfp = yfp + Integer.parseInt(kzrcws);

								ldList.add(lddm);
								csList.add(cs);
								qsList.add(qsh);
								njList.add(fpnj);
								bmList.add(fpbm);
								pkValueList.add(lddm + cs + qsh);
								
								yfpqs++;
							}
						}
					}
				}
			}
		}

		model.setLddm(ldList.toArray(new String[]{}));
		model.setCs(csList.toArray(new String[]{}));
		model.setQsh(qsList.toArray(new String[]{}));
		model.setNj(njList.toArray(new String[]{}));
		model.setBmdm(bmList.toArray(new String[]{}));
		model.setFpdx(fpdx);
		model.setSjly(sjly);
				
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValueList.toArray(new String[] {}));
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		//执行保存操作

		if (kfpQsList != null && kfpQsList.size() > 0) {
			saveInfoToDb(saveForm, model, user);
		}

		return String.valueOf(yfpqs);
	}
	
	/**
	 * 保存寝室自动分配
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String saveQsfpAuto(GyglQsglForm model, User user) throws Exception {
		
		// 分配对象
		String fpdx = model.getFpdx();
		// 分配对象代码
		String[] fpdx_dm = model.getPrimarykey_checkVal();
		// 寝室分配表
		String tableName = "xg_gygl_qsfpb";
		// 主键
		String pk = "lddm||cs||qsh";
		// 主键值
		ArrayList<String> pkValueList = new ArrayList<String>();
		// 单个字段
		String[] onezd = new String[] { "fpdx", "sjly", "kffp" };
		// 批量字段
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "nj", "bmdm" };
		// 数据来源
		String sjly = "自动";
		//性别
		String xbxz = model.getXb();
		
		// 可分配寝室列表
		List<HashMap<String, String>> kfpQsList = getKfpQsList(model,user);
		// 部门寝室分配信息
		List<HashMap<String, String>> bmqsfpList = dao.getBmqsfpList(fpdx_dm,
				fpdx);
		
		// 楼栋
		ArrayList<String> ldList = new ArrayList<String>();
		// 层数
		ArrayList<String> csList = new ArrayList<String>();
		// 寝室号
		ArrayList<String> qsList = new ArrayList<String>();
		// 年级
		ArrayList<String> njList = new ArrayList<String>();
		// 部门
		ArrayList<String> bmList = new ArrayList<String>();	

		// 已分配寝室
		int yfpqs = 0;
		
		if (bmqsfpList != null && bmqsfpList.size() > 0) {
			for (int i = 0; i < bmqsfpList.size(); i++) {
				HashMap<String, String> bmInfo = bmqsfpList.get(i);
				// 部门年级
				String bmnj = !Base.isNull(bmInfo.get("nj")) ? bmInfo.get("nj")
						: "";
				// 部门代码
				String bmdm = "";
				// 男生数
				int mam = Integer.parseInt(bmInfo.get("man"));
				// 女生数
				int womam = Integer.parseInt(bmInfo.get("woman"));
				// 男生床位数
				int mancws = Integer.parseInt(bmInfo.get("mancws"));
				// 女生床位数
				int womancws = Integer.parseInt(bmInfo.get("womancws"));
				
				if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
					bmdm = bmInfo.get("xydm");
				} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
					bmdm = bmInfo.get("xydm");
				} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
					bmdm = bmInfo.get("zydm");
				} else {// 分配对象为班级
					bmdm = bmInfo.get("bjdm");
				}
				
				// 需分配(男)
				int xfpMan = mam - mancws;
				// 需分配(女)
				int xfpWoman = womam - womancws;
				// 已分配(男)
				int yfpMan = 0;
				// 已分配(女)
				int yfpWoman = 0;
				
				if (kfpQsList != null && kfpQsList.size() > 0) {
					for (int j = 0; j < kfpQsList.size(); j++) {
						HashMap<String, String> qsInfo = kfpQsList.get(j);
						// 楼栋代码
						String lddm = qsInfo.get("lddm");
						// 层数
						String cs = qsInfo.get("cs");
						// 寝室号
						String qsh = qsInfo.get("qsh");
						// 性别限制
						String xb = qsInfo.get("xb");
						// 可住人床位数
						String kzrcws = qsInfo.get("kzrcws");
						//分配标记
						String fpbj = qsInfo.get("fpbj");
						
						if (!"yes".equalsIgnoreCase(fpbj)) {
							if ("男".equalsIgnoreCase(xbxz) && yfpMan >= xfpMan) {
								break;
							} else if ("女".equalsIgnoreCase(xbxz)
									&& yfpWoman > xfpWoman) {
								break;
							} else if (yfpMan >= xfpMan && yfpWoman > xfpWoman) {
								break;
							} else {

								if ("男".equalsIgnoreCase(xb) && yfpMan < xfpMan) {// 男寝室
									
									kfpQsList.get(j).put("fpbj", "yes");
									
									yfpMan += Integer.parseInt(kzrcws);
									
									ldList.add(lddm);
									csList.add(cs);
									qsList.add(qsh);
									njList.add(bmnj);
									bmList.add(bmdm);
									pkValueList.add(lddm + cs + qsh);
									
									yfpqs++;
								} else if("女".equalsIgnoreCase(xb) && yfpWoman < xfpWoman){// 女寝室
									
									kfpQsList.get(j).put("fpbj", "yes");
									
									yfpWoman += Integer.parseInt(kzrcws);
									
									ldList.add(lddm);
									csList.add(cs);
									qsList.add(qsh);
									njList.add(bmnj);
									bmList.add(bmdm);
									pkValueList.add(lddm + cs + qsh);
									
									yfpqs++;
								}

								
							}
						}
					}
				}
			}
		}
		
		model.setLddm(ldList.toArray(new String[]{}));
		model.setCs(csList.toArray(new String[]{}));
		model.setQsh(qsList.toArray(new String[]{}));
		model.setNj(njList.toArray(new String[]{}));
		model.setBmdm(bmList.toArray(new String[]{}));
		model.setFpdx(fpdx);
		model.setSjly(sjly);
				
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValueList.toArray(new String[] {}));
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		//执行保存操作

		if (kfpQsList != null && kfpQsList.size() > 0) {
			saveInfoToDb(saveForm, model, user);
		}

		return String.valueOf(yfpqs);
	}
	
	/**
	 * 获得可分配寝室列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKfpQsList(GyglQsglForm model,
			User user) {
		return dao.getKfpQsList(model, user);
	}
	
	/**
	 * 获得寝室自动分配结果列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpjgList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		model.setSjly("自动");

		ArrayList<String[]> list = getQsfpInfoList(model, user, colList,
				request);

		return list;
	}

	/**
	 * 取消寝室分配
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean cancelQsfp(GyglQsglForm model,HttpServletRequest request) throws Exception {
		
		boolean blog=false;
		DelDataDetect delData = new DelDataDetect();
		DelDetectModel detectModel = new DelDetectModel();
		
		detectModel.setPath("gygl_qsgl_fpjg.do");
		//将需要删除的数据主键传入删除检测
		String[] pkValue=model.getPrimarykey_checkVal();
		detectModel.setPkValue(pkValue);
		
		delData.dataDetect(detectModel, request);
		
		if (detectModel.isBool()) {
			
			blog=dao.cancelQsfp(model);
		
		} else {

			request.setAttribute("delMessage", detectModel.getMessage());
		}
		
		return blog;
	}

	/**
	 * 获得寝室自动分配结果列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	public ArrayList<String[]> getQsfpInfoList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		// 分配对象
		String fpdx = model.getFpdx();
		// 数据来源
		String sjly = model.getSjly();
		
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;


		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(Base.isNull(fpdx) ? "" : " and fpdx = '" + fpdx + "'");
		query.append(Base.isNull(sjly) ? "" : " and sjly = '" + sjly + "'");
		query.append(searchTj);
		query.append(" order by ssbh");

		ArrayList<String[]> list =(ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy("xg_view_gygl_qsfp",
				query.toString(), inputV, colList, "", model);

		return list;
	}

	/**
	 * 获得寝室自动分配结果列表(不需要入住状态)
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public ArrayList<String[]> getQsfpInfoListForNoRzzt(GyglQsglForm model,
			User user, String[] colList, HttpServletRequest request)
			throws Exception {

		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		// 分配对象
		String fpdx = model.getFpdx();
		// 数据来源
		String sjly = model.getSjly();
		// 分配方式
		String fpfs = jbszModel.getFpfs();
		
		String path = model.getSearchModel().getPath()+"&searchType="+fpdx;
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;


		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		
		// 分配方式为：学校->学院->班级
		if ("2".equalsIgnoreCase(fpfs)) {
			if ("xx".equalsIgnoreCase(user.getUserStatus())) {
				query.append( " and fpdx = 'xy' ");
			}else{
				query.append( " and fpdx = 'bj'");
				query.append( " and xydm = '"+user.getUserDep()+"'");
			}
		}else{
			query.append( " and fpdx = '"+fpdx+"' ");
		}
		
		//query.append(Base.isNull(sjly) ? "" : " and sjly = '" + sjly + "'");
		query.append(searchTj);
		query.append(" order by ssbh");
		System.out.println(query);
		ArrayList<String[]> list = getRsArrList("xg_view_gygl_qsfp", query
				.toString(), inputV, colList, "", model);

		return list;
	}
	
	/**
	 * 获得寝室手动分配结果列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 * @throws Exception
	 */
	public ArrayList<String[]> getSdfpjgList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		ArrayList<String[]> list = new ArrayList<String[]>();

		ArrayList<String[]> fpInfoList = null;

		// 不需要入住状态过滤
		if (model.getRzzt() == null || model.getRzzt().length == 0) {
			fpInfoList = getQsfpInfoListForNoRzzt(model, user, colList, request);
		} else {
			fpInfoList = getQsfpInfoList(model, user, colList, request);
		}

		// 入住状态
		String[] search_tj_rzzt = model.getRzzt();

		// 入住情况列表
		List<HashMap<String, String>> rzqkList = null;

		if (fpInfoList != null && fpInfoList.size() > 0) {

			String[] ssbh = new String[fpInfoList.size()];

			for (int i = 0; i < fpInfoList.size(); i++) {
				ssbh[i] = fpInfoList.get(i)[0];
			}

			model.setSsbh(ssbh);

			rzqkList = dao.getQsrzInfoList(model);
		}

		if (fpInfoList != null && fpInfoList.size() > 0) {

			for (int i = 0; i < fpInfoList.size(); i++) {

				ArrayList<String> rs = new ArrayList<String>();

				String[] fpInfo = fpInfoList.get(i);

				rs.addAll(new ArrayList<String>(Arrays.asList(fpInfo)));

				String ssbh = fpInfo[0];

				boolean flag = true;

				if (rzqkList != null && rzqkList.size() > 0) {

					for (int j = 0; j < rzqkList.size(); j++) {

						HashMap<String, String> map = rzqkList.get(j);

						// 楼栋代码
						String lddm = map.get("lddm");
						// 层数
						String cs = map.get("cs");
						// 寝室号
						String qsh = map.get("qsh");
						// 床位数
						String cws = map.get("cws");
						// 不可住人床位数
						String bkzrcws = map.get("bkzrcws");
						// 已住人数
						String yzrs = map.get("yzrs");

						if (ssbh.equalsIgnoreCase(lddm + "-" + qsh)) {

							String rzzt = "";

							if ("0".equalsIgnoreCase(yzrs)) {// 已住人数为0
								rzzt = "空的";
							} else if (cws.equalsIgnoreCase(String
									.valueOf(Integer.parseInt(bkzrcws)
											+ Integer.parseInt(yzrs)))) {// 已住人数+不可住人=寝室床位
								rzzt = "满的";
							} else {// 其他（错误数据不管）
								rzzt = "闲的";
							}

							// 判断入住状态是否是过滤条
							if (search_tj_rzzt != null
									&& search_tj_rzzt.length > 0) {
								for (int k = 0; k < search_tj_rzzt.length; k++) {
									if (rzzt
											.equalsIgnoreCase(search_tj_rzzt[k])) {
										flag = true;
										break;
									} else {
										flag = false;
									}
								}
							}

							rs.add(rzzt);

							break;
						}
					}
				}

				if (flag) {
					list.add(rs.toArray(new String[] {}));
				}
			}
		}

		// 需要入住状态过滤
		if (model.getRzzt() != null && model.getRzzt().length > 0) {
			list = getResultList(list, model.getPages());
		}
		
		return list;
	}

	/**
	 * 获得分配对象列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, Object>> getFpdxList(GyglQsglForm model,
			GyglJbszForm jbszModel,User user) {

		// 分配对象
		String fpdx = jbszModel.getFpdx();
		
		String userStatus = user.getUserStatus(); 
		String userName= user.getUserName(); 
		String userDep= user.getUserDep(); 
		
		// 年级列表
		List<HashMap<String, String>> njList = dao.getBmList(new String[]{"nj","",""}, userStatus,
				userName, userDep);

		// 学院列表
		List<HashMap<String, String>> xyList = dao.getBmList(new String[]{"xy","",""}, userStatus,
				userName, userDep);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 分配对象：xy（学院）
		if ("xy".equalsIgnoreCase(fpdx)) {
			// 年级
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("liName", "Child");
			map.put("bmList", xyList);
			list.add(map);
		}
		// 分配对象：njxy（年级+学院）
		else if ("njxy".equalsIgnoreCase(fpdx)) {
			// 年级
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}
		// 分配对象：njzy（年级+专业）
		else if ("njzy".equalsIgnoreCase(fpdx)) {
			// 年级
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}
		// 分配对象：bj（班级）
		else if ("bj".equalsIgnoreCase(fpdx)) {
			// 年级
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}

		return list;
	}
	
	/**
	 * 获得公寓结构列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, Object>> getGyjgList(GyglQsglForm model,
			User user) {

		// 从属关系
		//String csgx = jbszModel.getCsgx();

		// 校区列表
		//List<HashMap<String, String>> xqList = dao.getXqList(model, jbszModel);

		// 园区列表
		//List<HashMap<String, String>> yqList = dao.getYqList(model, jbszModel);

		// 楼栋列表
		List<HashMap<String, String>> ldList = dao.getLdList(model, user);
//		List<HashMap<String, String>> ldInfoList = getLdInfoList(ldList,
//				jbszModel);

		// 层数列表
		List<HashMap<String, String>> csList=new ArrayList<HashMap<String,String>>();
		if("yes".equalsIgnoreCase(model.getWfpqs())){
			 csList = getWfpLcList(model,user);
		}else{
			 csList = getCsList(ldList,model);
		}

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 楼栋
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Lv", "ld");
		map.put("infoList", ldList);
		list.add(map);

		// 层数
		map = new HashMap<String, Object>();
		map.put("Lv", "cs");
		map.put("infoList", csList);
		list.add(map);

		return list;
	}
	
	/**
	 * 获得楼栋信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getLdInfoList(
			List<HashMap<String, String>> ldList, GyglJbszForm jbszModel) {

		// 从属关系
		String csgx = jbszModel.getCsgx();

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (ldList != null && ldList.size() > 0) {
			for (int i = 0; i < ldList.size(); i++) {
				HashMap<String, String> map = ldList.get(i);
				// 校区代码
				String xqdm = map.get("xqdm");
				// 园区代码
				String yqdm = map.get("yqdm");
				// 楼栋代码
				String lddm = map.get("lddm");
				// 楼栋名称
				String ldmc = map.get("ldmc");
				// 层数
				String cs = map.get("cs");
				// 上级代码
				String sjdm = "";
				if ("1".equalsIgnoreCase(csgx) || "3".equalsIgnoreCase(csgx)) {
					sjdm = yqdm;
				} else if ("2".equalsIgnoreCase(csgx)) {
					sjdm = xqdm;
				}

				HashMap<String, String> ldInfo = new HashMap<String, String>();
				ldInfo.put("dm", lddm);
				ldInfo.put("mc", ldmc);
				ldInfo.put("sjdm", sjdm);

				list.add(ldInfo);
			}
		}

		return list;
	}
	
	/**
	 * 获取未分配寝室的楼栋楼层
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getWfpLcList(GyglQsglForm model,
			User user){
		
		return dao.getWfpLcList(model, user);
	}
	
	
	/**
	 * 获得层数信息列表
	 * 
	 * @author 伟大的骆
	 * 今天中五百万修改 2011.8.12
	 */
	public List<HashMap<String, String>> getCsList(
			List<HashMap<String, String>> ldList, GyglQsglForm model) {
		GyglCwglForm cwglForm=new GyglCwglForm();
		GyglCwglDAO cwglDAO=new GyglCwglDAO();
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		String[] pk = model.getPrimarykey_checkVal();
		ArrayList<HashMap<String,String>> csList = new ArrayList<HashMap<String,String>>();
		
		
		
		if (pk != null && pk.length > 0) {
			List<HashMap<String,String>>ldlcList=splitLdLc(pk);
			for(int i=0;i<ldlcList.size();i++){
				HashMap<String,String>ldlcMap=ldlcList.get(i);
				boolean flag = true;
				if (ldlcList != null && ldlcList.size() > 0) {
					for (int j = 0; j < csList.size(); j++) {
						HashMap<String,String>csMap=csList.get(j);
						if(ldlcMap.get("cs").equalsIgnoreCase(csMap.get("cs"))
								&& ldlcMap.get("lddm").equalsIgnoreCase(csMap.get("lddm"))){
							flag=false;
							break;
						}
					}
				}
				
				if (flag) {
					csList.add(ldlcMap);
				}
			}
			
		}	
		
		if (ldList != null && ldList.size() > 0) {

			for (int i = 0; i < ldList.size(); i++) {

				HashMap<String, String> map = ldList.get(i);
				// 楼栋代码
				String lddm = map.get("lddm");
				// 层数
				String cs = map.get("cs");
				// 楼栋代码key(前台判断)
				String lddmKey = map.get("lddmKey");
				if (!Base.isNull(cs)) {
					cwglForm.setLddm(lddm);
					List<HashMap<String,String>>ldcsList=cwglDAO.getLdlcByQsfp(cwglForm);
					for (int j = 0; j < ldcsList.size(); j++) {
						HashMap<String,String>ldcsMap=ldcsList.get(j);
						HashMap<String, String> csInfo = new HashMap<String, String>();
						csInfo.put("dm", ldcsMap.get("cs"));
						csInfo.put("mc", ldcsMap.get("cs"));
						csInfo.put("lddm", lddm);
						csInfo.put("lddmKey", lddmKey);
						boolean flag = true;
						
						if (csList != null && csList.size() > 0) {
							for (int k = 0; k < csList.size(); k++) {
								HashMap<String,String>csMap=csList.get(k);
								if(!ldcsMap.get("cs").equalsIgnoreCase(csMap.get("cs"))
										&& lddm.equalsIgnoreCase(csMap.get("lddm"))){
									flag=false;
									break;
								}
							}
						}
						
						if (flag) {
							list.add(csInfo);
						}
					}
				}
			}
		}

		return list;
	}
	
	/**
	 * 保存寝室手动分配
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveQssdfp(GyglQsglForm model, User user) throws Exception {

		// 分配对象
		String fpdx = model.getFpdx();
		// 寝室分配表
		String tableName = "xg_gygl_qsfpb";
		// 主键
		String pk = "lddm||cs||qsh";
		// 主键值
		ArrayList<String> pkValueList = new ArrayList<String>();
		// 单个字段
		String[] onezd = new String[] { "fpdx", "sjly","kffp" };
		// 批量字段
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "nj", "bmdm" };
		// 非空字段
		String[] notnull = new String[] { "bmdm" };
		// 数据来源
		String sjly = "手动";

		// 楼栋
		String[] lddm = model.getLddm();
		// 层数
		String[] cs = model.getCs();
		// 寝室号
		String[] qsh = model.getQsh();
		// 年级
		String[] nj = model.getNj();
		// 部门代码
		String[] bmdm = model.getBmdm();
		
		// 楼栋
		ArrayList<String> ldList = new ArrayList<String>();
		// 层数
		ArrayList<String> csList = new ArrayList<String>();
		// 寝室号
		ArrayList<String> qsList = new ArrayList<String>();
		// 年级
		ArrayList<String> njList = new ArrayList<String>();
		// 部门
		ArrayList<String> bmList = new ArrayList<String>();	

		boolean flag = true;
		
		if (lddm != null && lddm.length > 0) {
			
			for (int i = 0; i < lddm.length; i++) {
				//if (!Base.isNull(bmdm[i])) {
					ldList.add(lddm[i]);
					csList.add(cs[i]);
					qsList.add(qsh[i]);
					njList.add(nj[i]);
					bmList.add(bmdm[i]);
					pkValueList.add(lddm[i] + cs[i] + qsh[i]);
				//}
			}

			model.setLddm(ldList.toArray(new String[] {}));
			model.setCs(csList.toArray(new String[] {}));
			model.setQsh(qsList.toArray(new String[] {}));
			model.setNj(njList.toArray(new String[] {}));
			model.setBmdm(bmList.toArray(new String[] {}));
			model.setFpdx(fpdx);
			model.setSjly(sjly);

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValueList.toArray(new String[] {}));
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setNotnull(notnull);

			GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
			// 分配方式
			String fpfs = jbszModel.getFpfs();

			// 分配方式为：学校->学院->班级
			if ("2".equalsIgnoreCase(fpfs)) {
				if ("xx".equalsIgnoreCase(user.getUserStatus())) {
					flag = saveInfoToDb(saveForm, model, user);
				} else {
					flag = dao.saveQsfpToBj(model, user);
				}
			} else {
				flag = saveInfoToDb(saveForm, model, user);
			}
		}

		return flag;
	}
	
	/**
	 * 获得未配寝室数量
	 * 
	 * @author 伟大的骆
	 */
	public String getWfpQsNum() {

		// 未分配寝室数
		String wfpQsNum = "0";
		// 寝室数量
		String qsNum = "0";
		// 已分配寝室数量
		String yfpQsNum = "0";

		List<HashMap<String, String>> list = dao.getQsNumList();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					qsNum = list.get(0).get("num");
				} else {
					yfpQsNum = list.get(i).get("num");
				}
			}
		}

		if (Integer.parseInt(qsNum) > Integer.parseInt(yfpQsNum)) {
			wfpQsNum = String.valueOf(Integer.parseInt(qsNum)
					- Integer.parseInt(yfpQsNum));
		}
		
		return wfpQsNum;
	}
	
	/**
	 * 获得寝室分配统计列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getQsfpTjList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request) {

		// 分配对象
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();
		ArrayList<String[]> tjList = null;

		try {

			// 高级查询条件
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			// 高级查询输入值
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			// 权限控制
			HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
			notCtrlStatus.put("gygly", "yes");
			user.setNotCtrlStatus(notCtrlStatus);
			String searchTjByUser = searchService.getSearchTjByUser("a", user);

			searchTj += searchTjByUser;

			// 统计列表
			tjList = dao.getQsfpTjList(fpdx, searchTj, inputV, model);
			
		} catch (Exception e) {
			throw new SystemException("Error-1024");
		}

		// 统计列表
		// List<HashMap<String, String>> tjList = dao.getBmTjList(fpdx, bmList);

		// ArrayList<String[]> list = setBmTjList(fpdx, bmList, tjList);

		return tjList;
	}

	/**
	 * 设置部门统计列表
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> setBmTjList(String fpdx,
			ArrayList<String[]> bmList, List<HashMap<String, String>> tjList) {

		ArrayList<String[]> list = new ArrayList<String[]>();

		if (bmList != null && bmList.size() > 0) {
			for (int i = 0; i < bmList.size(); i++) {
				//显示内容
				ArrayList<String> nrList = new ArrayList<String>();
				String[] bmInfo = bmList.get(i);

				//年级
				String nj = "";
				// 部门代码
				String bmdm = "";
				
				if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
					bmdm = bmInfo[0];
					// 学院代码
					nrList.add(bmInfo[0]);
					// 学院名称
					nrList.add(bmInfo[1]);
				} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
					nj = bmInfo[0];
					bmdm = bmInfo[1];
					// 年级+学院代码
					nrList.add(nj + "!!@@!!" + bmdm);
					// 年级
					nrList.add(bmInfo[0]);
					// 学院名称
					nrList.add(bmInfo[2]);
				} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
					nj = bmInfo[0];
					bmdm = bmInfo[3];
					// 年级+专业代码
					nrList.add(nj + "!!@@!!" + bmdm);
					// 年级
					nrList.add(bmInfo[0]);
					// 专业名称
					nrList.add(bmInfo[4]);
				} else {// 分配对象为班级
					nj = bmInfo[0];
					bmdm = bmInfo[5];
					// 班级代码
					nrList.add(bmInfo[5]);
					// 班级名称
					nrList.add(bmInfo[6]);
				}
				
				// 已分配寝室数
				int fpqss = 0;
				// 男寝室（可混）
				int manhqss = 0;
				// 男寝室（不可混）
				int manbhqss = 0;
				// 女寝室（可混）
				int womanhqss = 0;
				// 女寝室（不可混）
				int womanbhqss = 0;
				
				//统计信息列表
				if (tjList != null && tjList.size() > 0) {
					for (int j = 0; j < tjList.size(); j++) {
						HashMap<String,String> map = tjList.get(j);
						// 年级
						String tjnj = map.get("nj");
						// 部门代码
						String tjbm = map.get("bmdm");
						// 性别限制
						String xb = map.get("xb");
						// 可否混住
						String kfhz = map.get("kfhz");
						// 寝室数
						String qsnum = map.get("qsnum");
						
						int num = Base.isNull(qsnum)?0:Integer.parseInt(qsnum);
						
						// 分配对象为学院
						if ("xy".equalsIgnoreCase(fpdx) && tjbm.equalsIgnoreCase(bmdm)) {
							if ("男".equalsIgnoreCase(xb) && "可以".equalsIgnoreCase(kfhz)) {
								manhqss+=num;
							}else if ("男".equalsIgnoreCase(xb) && "不可".equalsIgnoreCase(kfhz)) {
								manbhqss+=num;
							}else if ("女".equalsIgnoreCase(xb) && "可以".equalsIgnoreCase(kfhz)) {
								womanhqss+=num;
							}else if ("女".equalsIgnoreCase(xb) && "不可".equalsIgnoreCase(kfhz)) {
								womanbhqss+=num;
							}
							fpqss+=num;
						}
						// 分配对象为非学院
						else if (tjbm.equalsIgnoreCase(bmdm) && tjnj.equalsIgnoreCase(nj)) {
							if ("男".equalsIgnoreCase(xb) && "可以".equalsIgnoreCase(kfhz)) {
								manhqss+=num;
							}else if ("男".equalsIgnoreCase(xb) && "不可".equalsIgnoreCase(kfhz)) {
								manbhqss+=num;
							}else if ("女".equalsIgnoreCase(xb) && "可以".equalsIgnoreCase(kfhz)) {
								womanhqss+=num;
							}else if ("女".equalsIgnoreCase(xb) && "不可".equalsIgnoreCase(kfhz)) {
								womanbhqss+=num;
							}
							fpqss+=num;
						}
					}
				}
				
				// 部门已分配寝室
				nrList.add(String.valueOf(fpqss));
				// 男可混
				nrList.add(String.valueOf(manhqss));
				// 男不可混
				nrList.add(String.valueOf(manbhqss));
				// 女可混
				nrList.add(String.valueOf(womanhqss));
				// 女不可混
				nrList.add(String.valueOf(womanbhqss));
				
				list.add(nrList.toArray(new String[]{}));
			}
		}

		return list;
	}
	
	/**
	 * 获得寝室手动分配分配对象列表
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, Object>> getFpdxByQssdfp(GyglQsglForm model) {
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Lv", "1");
		map.put("liName", "Child");
		map.put("bmList", dao.getBmxxByFpdx(model));
		list.add(map);
		
		return list;
	}
	
	/**
	 * 获得楼栋信息列表（未分配寝室数）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getLdForWfpQssList(User user) {
		return dao.getLdForWfpQssList(user);
	}
	
	/**
	 * 重构楼栋楼层列表
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String, String>> splitLdLc(String[] pkValue) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < pkValue.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			String lddm = pkValue[i].split("-")[0];
			String cs = pkValue[i].split("_")[1];
			map.put("lddm", lddm);
			map.put("cs", cs);
			list.add(map);
		}
		return list;
	}
}
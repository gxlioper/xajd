package xgxt.xtwh.sysz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;
import xgxt.xtwh.XtwhService;

import com.zfsoft.basic.BasicService;
import com.zfsoft.xgxt.base.util.SearchUtil;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_首页设置_Service类
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

public class SyszService extends XtwhService {

	SyszDAO dao = new SyszDAO();

	
	/**
	 * 查询下载专区文件信息（取前10条）
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>> getFilesInfoList(SyszForm myForm, User user){
		String sql = new StringBuilder("select newsid,towho,filemc,filepath,rownum r from ")
						.append("(select newsid,towho,filemc,filepath from xg_xtwh_xzzqb")
						.append(" order by scsj desc) where 1=1 ").toString();
		int maxSize = 5; 
		Pages pages = myForm.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> listNew=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> list = CommonQueryDAO.commonPageQueryForMap(pages, sql, new String[]{});
		for(HashMap<String, String> hm:list){
			String newsId=hm.get("newsid");
			String toWho=hm.get("towho");
			if(SearchUtil.getInstance().isHaveQx(user, newsId, toWho)){
				listNew.add(hm);
			}
		}
		int size = maxSize - listNew.size();
		for (int i = 0; i < size; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("newsid", "");
			map.put("filemc", "");
			map.put("filepath", "");
			listNew.add(map);
		}
		return listNew;
	}
	
	
	
	
	
	
	/**
	 * 获得下载专区查询结果列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXzzqRsList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String userType = user.getUserType();
		//文件所属
		String filess = model.getFiless();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();

		// 表
		String tableName = "xg_view_xtwh_xzzq";
		// 查询条件
		StringBuilder query = new StringBuilder();
		String[] queryList = new String[] { "filelx" };
		String[] queryLikeList = new String[] { "filemc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		query.append(myQuery.getQueryString());
		query.append(Base.isNull(filess) ? "" : " and filess = '" + filess + "'");
		
		query.append(Base.isNull(kssj) ? "" : " and scsj >= '" + kssj
				+ "000000'");
		if (!Base.isNull(jssj)) {
			query.append(" and (scsj < '" + jssj + "'");
			query.append(" or scsj like '" + jssj + "%')");
		}

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}


	/**
	 * 获得下载专区
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXzzqMoreList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		//文件所属
		String filess = model.getFiless();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();

		// 表
		String tableName = "xg_view_xtwh_xzzq";
		// 查询条件
		StringBuilder query = new StringBuilder();
		String[] queryList = new String[] { "filelx" };
		String[] queryLikeList = new String[] { "filemc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		query.append(myQuery.getQueryString());

		query.append(Base.isNull(filess) ? "" : " and filess = '" + filess + "'");
		
		query.append(Base.isNull(kssj) ? "" : " and scsj >= '" + kssj
				+ "000000'");
		if (!Base.isNull(jssj)) {
			query.append(" and (scsj < '" + jssj + "'");
			query.append(" or scsj like '" + jssj + "%')");
		}

		// 输入值
		String[] inPutList = myQuery.getInputList();
		ArrayList<String[]> listNew=new ArrayList<String[]>();
		ArrayList<String[]> list = getRsArrList(tableName, query.toString(), inPutList, colList, "", model);
		for(String[] hm:list){
			String newsId=hm[0];
			String toWho=hm[4];
			if("全校师生".equals(toWho)){
				toWho = "all_teastu";
			}else if("指定师生".equals(toWho)){
				toWho = "some_teastu";
			}else if("全校教师".equals(toWho)){
				toWho = "all_tea";
			}else if("指定教师".equals(toWho)){
				toWho = "some_tea";
			}else if("全校学生".equals(toWho)){
				toWho = "all_stu";
			}else if("指定学生".equals(toWho)){
				toWho = "some_stu";
			}else{
				toWho = "";
			}
			if(SearchUtil.getInstance().isHaveQx(user, newsId, toWho)){
				listNew.add(hm);
			}
		}
		return listNew;
	}
	
	/**
	 * 保存下载专区
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXzzq(SyszForm model, User user,
			HttpServletRequest request) throws Exception {
		String newsId = request.getParameter("newsId");
		String toWho = request.getParameter("toWho");
		// 保存表
		String tableName = "xg_xtwh_xzzqb";
		// 主键
		String pk = "newsid";
		if(StringUtils.isNull(newsId)){
			newsId = GetSysData.getGuid();
		}
		model.setNewsid(newsId);
		model.setTowho(toWho);
		// 主键值
		String[] pkValue = new String[] { newsId };
		// 单一字段
		String[] onezd = new String[] { "newsid", "filepath", "filemc", "filelx",
				"filess", "filesm", "towho", "bz", "scr" };

		model.setScr(user.getUserName());

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		model.setBz(((String)model.getBz()).replaceAll("\\n", "<br/>"));
		model.setFilesm((((String)model.getFilesm()).replaceAll("\\n", "<br/>")));
		
		Boolean b = saveInfoToDb(saveForm, model, request);
		if(b){
			SearchUtil su=SearchUtil.getInstance();
			if(toWho.contains("some")){
				String qxfwid = request.getParameter("qxfwid");
				if(StringUtils.isNotNull(qxfwid)){
					su.updateSearchModel(request, newsId);
				}else{
					su.saveSearchModel(request, newsId);
				}
			}else{
				su.deleteSearch(request, newsId);
			}
		}
		return b;
	}

	/**
	 * 删除下载专区
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delXzzq(SyszForm model, User user, HttpServletRequest request)
			throws Exception {

		// 删除DB
		boolean flag = delXzzqInDB(model, user, request);

		if (flag) {
			// 删除文件
			delUpLoadFile(model.getPrimarykey_checkVal());
		}

		return flag;

	}

	/**
	 * 删除下载专区
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private Boolean delXzzqInDB(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_xtwh_xzzqb";
		// 主键
		String pk = "newsid";
		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * 获得下载专区文件信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getXzzqInfo(SyszForm model) {

		// 保存表
		String tableName = "xg_xtwh_xzzqb";
		// 主键
		String pk = "newsid";
		// 主键值
		String pkValue = model.getNewsid();
		// 单一字段
		String[] colList = new String[] { "newsid", "filepath", "filemc", "filelx",
				"filesm","filess", "toWho", "bz", "scr" };

		return getRsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 获得下载专区可下载的文件列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXzzqFileList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String userType = user.getUserType();
		//限制对象
		String xzdx = "stu".equalsIgnoreCase(userType) ? "学生" : "老师";
		//文件所属
		String filess = model.getFiless();
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputValue = SearchService.getTjInput(model.getSearchModel());
		
		// 表
		String tableName = "xg_view_xtwh_xzzq";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append("and (xzdx = '全部' or xzdx = '" + xzdx + "')");
		query.append(Base.isNull(filess) ? "" : " and filess = '" + filess + "'");
		query.append(searchTj);
		
		return getRsArrList(tableName, query.toString(), inputValue, colList,
				"", model);
	}

	/**
	 * 获得首页调查查询结果列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getSydcRsList(SyszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "sfqy" };
		String[] queryLikeList = new String[] { "dcnr" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String kssj = model.getKssj();
		String jssj = model.getJssj();

		// 表
		String tableName = "xg_view_xtwh_sydc";
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(Base.isNull(kssj) ? "" : " and dcsj >= '" + kssj
				+ "000000'");
		if (!Base.isNull(jssj)) {
			query.append(" and (dcsj < '" + jssj + "'");
			query.append(" or dcsj like '" + jssj + "%')");
		}

		// 输入值
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * 获得首页调查信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getSzdcInfo(SyszForm model) {

		// 保存表
		String tableName = "xg_view_xtwh_sydc";
		// 主键
		String pk = "dcid";
		// 主键值
		String pkValue = model.getDcid();
		// 单一字段
		String[] colList = new String[] { "dcid", "dcnr", "sfqy", "bz" };

		return getRsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 保存首页调查
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveSydc(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 调查ID
		String dcid = model.getDcid();
		// 是否启用
		String sfqy = model.getSfqy();

		// 保存调查
		boolean flag = saveSydcnr(model, user, request);

		if (flag) {

			if (Base.isNull(dcid)) {
				dcid = dao.getDcid(model, user);
				model.setDcid(dcid);
			}

			// 保存选项
			flag = saveSydcxx(model, user, request);

			if (flag && "是".equalsIgnoreCase(sfqy)) {
				// 设置其他调查为关闭
				flag = dao.setOtherDcClose(model, user);
			}
		}

		return flag;

	}

	/**
	 * 保存调查内容
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private Boolean saveSydcnr(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 调查ID
		String dcid = model.getDcid();
		// 保存表
		String tableName = "xg_xtwh_sydcb";
		// 主键
		String pk = "dcid";
		// 主键值
		String[] pkValue = new String[] { dcid };
		// 单一字段
		String[] onezd = Base.isNull(dcid) ? new String[] { "dcnr", "sfqy",
				"bz", "dcr" } : new String[] { "dcid", "dcnr", "sfqy", "bz",
				"dcr" };

		model.setDcr(user.getUserName());

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * 保存调查选项
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private Boolean saveSydcxx(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 调查ID
		String dcid = model.getDcid();
		// 保存表
		String tableName = "xg_xtwh_sydcxxb";
		// 主键
		String pk = "dcid";
		// 主键值
		String[] pkValue = new String[] { dcid };
		// 单一字段
		String[] onezd = new String[] { "dcid" };
		// 批量字段
		String[] arrzd = new String[] { "xxid", "xxnr" };
		// 非空字段
		String[] notnull = new String[] { "xxnr" };

		model.setDcr(user.getUserName());

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setNotnull(notnull);

		return saveInfoToDb(saveForm, model, user);
	}

	/**
	 * 保存首页调查是否启用
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveSydcSfqy(SyszForm model, User user) throws Exception {

		return dao.saveSydcSfqy(model, user);

	}

	/**
	 * 删除首页调查
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delSydc(SyszForm model, User user, HttpServletRequest request)
			throws Exception {

		// 删除首页调查内容
		boolean flag = delSydcnr(model, user, request);

		if (flag) {

			// 删除首页调查选项
			flag = delSydcxx(model, user, request);

			if (flag) {

				// 删除首页调查统计
				flag = delSydctj(model, user, request);
			}
		}

		return flag;

	}

	/**
	 * 删除调查内容
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private Boolean delSydcnr(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_xtwh_sydcb";
		// 主键
		String pk = "dcid";
		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * 删除调查选项
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private Boolean delSydcxx(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_xtwh_sydcxxb";
		// 主键
		String pk = "dcid";
		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * 删除调查统计
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	private Boolean delSydctj(SyszForm model, User user,
			HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_xtwh_sydcjgb";
		// 主键
		String pk = "dcid";
		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delInfoInDb(saveForm, model, user);

	}

	/**
	 * 获得调查统计
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSydcTjList(SyszForm model, User user) {

		List<HashMap<String, String>> list = dao.getSydcTjList(model, user);

		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {

			String all = "0";

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				// 数量
				String num = map.get("num");

				all = String.valueOf(Integer.parseInt(all)
						+ Integer.parseInt(num));
			}

			HashMap<String, String> allMap = new HashMap<String, String>();
			allMap.put("all", all);
			resList.add(allMap);

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				// 数量
				String num = map.get("num");

				String bl = String.valueOf(Math.round((Float.parseFloat(num)
						/ Float.parseFloat(all) * 100)));

				map.put("bl", bl);

				resList.add(map);
			}
		}

		return resList;
	}

	// =======================以上made by 伟大的骆=====================

	// =======================以下made by qlj=======================
	
	/**
	 * 获取初始化列表信息
	 * author 潇洒的裘
	 * @throws Exception 
	 */
	public void getInitList(HttpServletRequest request,SyszForm myForm,String lx) throws Exception {
		
		SyszDAO syszDao=new SyszDAO();
		
		if("xsxm".equalsIgnoreCase(lx)){
			// 显示方式
			List<HashMap<String, String>> xsfsList = initList("xsfs");
			request.setAttribute("xsfsList", xsfsList);
			// 是否显示
			List<HashMap<String, String>> sfxsList = initList("sfxs");
			request.setAttribute("sfxsList", sfxsList);
		}else if("dbsx".equalsIgnoreCase(lx)){
			// 模块类型
			List<HashMap<String, String>> mklxList = syszDao.getMklb(myForm.getUserName());
			if(mklxList.size()>0 && ("".equalsIgnoreCase(myForm.getMklx())
					|| Base.isNull(myForm.getMklx()))){
				myForm.setMklx((mklxList.get(0)).get("dm"));
			}
			request.setAttribute("mklxList", mklxList);
			// 项目列表
			List<HashMap<String, String>> xmList=syszDao.getDbsxXmList(myForm);
			request.setAttribute("xmList", xmList);
		}
	}
	
	/**
	 * 获取初始化列表信息
	 * author 潇洒的裘
	 * @throws Exception 
	 */
	public void getXssqInit(HttpServletRequest request,SyszForm myForm) throws Exception {
		
		SyszDAO syszDao=new SyszDAO();
	
		// 模块类型
		List<HashMap<String, String>> mklxList = syszDao.getXssqMklb();
		request.setAttribute("mklxList", mklxList);
		// 申请项目列表
		request.setAttribute("xmList", getSqxxList(myForm));
	}

	/**
	 * 获取下拉列表(数组转LIST)
	 * author 潇洒的裘
	 * @param xslx 显示类型
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> initList(String xslx) {
		DAO commonDAO = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		// 显示方式
		if ("xsfs".equalsIgnoreCase(xslx)) {
			dm = new String[] { "联系方式" };
			mc = new String[] { "联系方式"};
		}

		// 是否显示
		if ("sfxs".equalsIgnoreCase(xslx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		}

		// 模块类型
		if ("mklx".equalsIgnoreCase(xslx)) {
			dm = new String[] { "学生资助","文件管理" };
			mc = new String[] { "学生资助","文件管理" };
		}

		return commonDAO.arrayToList(dm, mc);
	}

	/**
	 * 获取学生申请信息
	 * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 * @throws Exception
	 */
	public List<String[]> getXssqInfo(SyszForm myForm) throws Exception {
		return dao.getXssqInfo(myForm);
	}

	/**
	 * 待办事项信息
	 * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 * @throws Exception
	 */
	public List<String[]> getDbsxInfo(SyszForm myForm) throws Exception {
		return dao.getDbsxInfo(myForm);
	}

	/**
	 * 获取学生申请项目列表
	 * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqxxList(SyszForm myForm)
			throws Exception {

		return dao.getSqxxList(myForm);
	}

	/**
	 * 获取待办事项项目列表
	 * author 潇洒的裘 
	 * @param myForm
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDbsxXmList(SyszForm myForm)
			throws Exception {
		return dao.getDbsxXmList(myForm);
	}

	/**
	 * 项目列表
	 * author 潇洒的裘
	 * @param mklx
	 * @param userName
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getXmList(String mklx, String userName) {
		return dao.getXmList(mklx, userName);
	}
	
	/**
	 * 显示项目中是否显示开关 批量保存功能 
	 * author 潇洒的裘
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSfxs(SyszForm myForm) throws Exception {

		return dao.updateSfxs(myForm);
	}
	
	
	public void getTop(HttpServletRequest request, SyszForm myForm) {
		BasicService basicService = new BasicService();

		String[] topTr =  { "项目名称", "需审批人数" };
		String tableName = "";

		if ("学生资助".equalsIgnoreCase(myForm.getMklx())) {
			tableName = "xszz_shztb";
			topTr = new String[] { "项目名称", "需审批人数" };
		} else if ("文件管理".equalsIgnoreCase(myForm.getMklx())) {
			tableName = "wjsc_scwjxxb";
			topTr = new String[] { "文件号","文件名称", "文件发放时间" };
		}
		request.setAttribute("topTr", basicService.getTopTr(tableName, topTr));

	}
	
	public boolean deleteFile(String filePath) throws Exception{
		return dao.deleteFile(filePath);
	}
	
	/**
	 * 获取 评奖评优待办事项(通用)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 * @throws Exception 
	 */
	public List<String[]>getPjpyDbsx(SyszForm myForm) throws Exception{
		SyszDAO dao = new SyszDAO();
		List<String[]>pjdbList=dao.getPjpyDbsx(myForm);
		List<String[]>dbxxList=new ArrayList<String[]>();
		for(int i=0;i<pjdbList.size();i++){
			String[]pjdbArr=pjdbList.get(i);
			String[]dbxxArr=new String[3];
			String[]shxxArr=pjdbArr[4].split(",");
			String[]gwxxArr=pjdbArr[5].split(",");
			String[]jbxxArr=pjdbArr[6].split(",");
			StringBuilder html=new StringBuilder();
			dbxxArr[0]=pjdbArr[0];
			dbxxArr[2]=pjdbArr[2];
			html.append(pjdbArr[2]);
			for(int j=0;j<shxxArr.length;j++){
				
				html.append(" <a href=\"#\"onclick=\"pjpySh('"+pjdbArr[1]+"','"+gwxxArr[j]+"','"+jbxxArr[j]+"')\" style=\"cursor:hand\"");
				html.append(" <U><font color=\"blue\" >【"+shxxArr[j]+"】</font></U></a> ");
				
			}
			dbxxArr[1]=html.toString();
			dbxxList.add(dbxxArr);;
		}
		return  dbxxList;
	}
	
	/**
	 * 根据首页调查ID获取调查内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String>getSydcInfo(SyszForm myForm) throws Exception {
		return dao.getSydcInfo(myForm);
	}
	
	//	 =======================以上made by 勇猛的大神玩=======================
	
}
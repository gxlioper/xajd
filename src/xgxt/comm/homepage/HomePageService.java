package xgxt.comm.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.search.SearchForm;
import xgxt.comm.search.SearchTjByRoles;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.xtwh.sysz.SyszDAO;

import com.zfsoft.xgxt.xtwh.ksdh.KsdhService;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglService;
import common.Globals;

public class HomePageService {

	/**
	 * 首页数据初始化（老师）
	 * 
	 * author 伟大的骆
	 */
	public void setTeaList(HttpServletRequest request) throws Exception {
		HomePageDAO homePageDAO = new HomePageDAO();
		User user = getUser(request);// 用户对象

		// 待办事项
		//request.setAttribute("dbsxList", getDbsxList(user));
		
		// 江西科技师范大学 增加校区统计
		if("11318".equals(Base.xxdm)){
			
			List<HashMap<String, String>> zjuxqmcList = homePageDAO.getXsrsXqtj(user);
			request.setAttribute("zjuxqmcList", zjuxqmcList);
		}
		
		// 江西航空职业技术学院  增加中高职人数统计 (培训方向)
		if("13871".equals(Base.xxdm)){
			List<HashMap<String, String>> zgzrsList = homePageDAO.getZgzrsList(user);
			request.setAttribute("zgzrsList", zgzrsList.size()==0?null:zgzrsList);
		}
		
		// 学生人数统计
		String[] xstj = getXsrs(user);
		request.setAttribute("man", xstj[0]);
		request.setAttribute("woman", xstj[1]);
		request.setAttribute("all", xstj[2]);
		request.setAttribute("wzxb", xstj[3]);
		request.setAttribute("zxs", xstj[4]);
		request.setAttribute("fzxs", xstj[5]);
		request.setAttribute("xsxss", xstj[6]);
		request.setAttribute("txxss", xstj[7]);
		request.setAttribute("yxjxss", xstj[8]);
		request.setAttribute("wxjxss", xstj[9]);
		request.setAttribute("sfs", xstj[10]);
		request.setAttribute("fsfs", xstj[11]);
		request.setAttribute("dy", xstj[12]);
		request.setAttribute("ssmz", xstj[13]);
		request.setAttribute("fzxman", xstj[14]);
		request.setAttribute("fzxwoman", xstj[15]);
		// 首页调查内容
		String[] dcnr = getSydcnr(user);
		String dcidS="";
		String dcnrS="";
		String dcnrxxS="";
		if(dcnr!=null){
			dcidS=dcnr[0];
			dcnrS=dcnr[1];
			dcnrxxS=dcnr[2];
		}
		request.setAttribute("dcid", dcidS);
		request.setAttribute("dcnr", dcnrS);
		request.setAttribute("dcnrxx", dcnrxxS);
		
		// 首页调查内容选项
//		List<HashMap<String, String>> sydcList = getSydcList(user, dcidS);
//		request.setAttribute("sydcList", sydcList);
		// 首页显示项目
		List<HashMap<String, String>> xsxmlist = getXsxmList();
		// 友情链接
//		List<HashMap<String, String>> yqljList = getYqljList(xsxmlist);
//		request.setAttribute("yqljList", yqljList);
		// 联系方式
		List<HashMap<String, String>> lxfsList = getLxfsList(xsxmlist);
		request.setAttribute("lxfsList", lxfsList);
		//站内信提醒
		ZnxglService znxglservice = new ZnxglService();
		String username = getUser(request).getUserName();
		request.setAttribute("wdyjs", znxglservice.getZnxTx(username));
		//各年级学生数统计
		List<HashMap<String, String>> njxsData = homePageDAO.getNjXsList();
		JSONArray jsonArray = JSONArray.fromObject(njxsData);
		request.setAttribute("njxsData", jsonArray.toString());
		//政治面貌分类统计
		List<HashMap<String, String>> zzmmData = homePageDAO.getXsZzmmList();
		JSONArray zzmmJson = JSONArray.fromObject(zzmmData);
		request.setAttribute("zzmmData", zzmmJson.toString());
	}

	public void setStuList(HttpServletRequest request)
			throws Exception {
		User user = getUser(request);// 用户对象
		
		//学生基本信息
		Map<String,String> stuInfo = CommonQueryDAO.getStuInfo(user.getUserName());
		request.setAttribute("stuInfo", stuInfo);
		// 学生申请
//		request.setAttribute("xssqList", getXssqList(user));
		// 首页显示项目
		List<HashMap<String, String>> xsxmlist = getXsxmList();
		// 联系方式
		List<HashMap<String, String>> lxfsList = getLxfsList(xsxmlist);
		request.setAttribute("lxfsList", lxfsList);
		ZnxglService znxglservice = new ZnxglService();
		String username = getUser(request).getUserName();
		request.setAttribute("wdyjs", znxglservice.getZnxTx(username));
		// 快捷方式列表
//		List<HashMap<String, String>> kjfsList = getKjfsList(user);
//		request.setAttribute("kjfsList", kjfsList);

	}

	//	===============以下made by 潇洒的裘======================
	/**
	 * 获取教师的待办事项
	 * @param user
	 * @return List<HashMap<String,String>> 
	 * author 潇洒的裘
	 */
	public List<HashMap<String, Object>> getDbsxList(User user) {

		HomePageModel model=new HomePageModel();
		HomePageDAO dao = new HomePageDAO();
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		SyszDAO syszDao=new SyszDAO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		//模块类型
		List<HashMap<String,String>>mklbList=syszDao.getMklb(user.getUserName());
		
		String xxdm=Base.xxdm;
		
		//提示信息
//		HashMap<String,String>tsMap=new HashMap<String,String>();
//		tsMap.put("tsxx","注："+Base.currXn+"学年 "+Base.currXq+"学期 "+Base.currNd+"年度 查看详细信息请点击\"more\"");
//		list.add(tsMap);
		
		model.setMaxSize(10);
		List<HashMap<String, String>> resultList  = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<mklbList.size();i++){
			//从待办事项内容表中抽取
			HashMap<String,Object>map=new HashMap<String,Object>();
			HashMap<String,String>mklbMap=mklbList.get(i);
			if("学生资助".equalsIgnoreCase(mklbMap.get("mc"))
					&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)
					&& model.getMaxSize()>0){
				resultList=dzdxDAO.getZzpjDbsx(user, model);
				map.put("mklx", "学生资助");
				map.put("xmmc", "资助");
				model.setMklx("zz");
				map.put("dbxx", resultList);
				
				// -------------2012.2.22 裘力俊----------------
				// -------------修改内容：此处考虑欠缺---------------
				// ----------不应该写成10-resultList.size()---------
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//中国地质大学 资助
				list.add(map);
			}else if("评奖评优".equalsIgnoreCase(mklbMap.get("mc"))
					&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)
					&& model.getMaxSize()>0){
				resultList=dzdxDAO.getZzpjDbsx(user,model);
				map.put("mklx", "评奖评优");
				map.put("xmmc", "评奖");
				model.setMklx("pj");
				map.put("dbxx", resultList);
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//中国地质大学评奖
				list.add(map);
			}else if("学生资助".equalsIgnoreCase(mklbMap.get("mc"))
					&& model.getMaxSize()>0){
				resultList=dao.getXszzDbsx(user,model);
				map.put("mklx", "学生资助");
				map.put("xmmc", "资助");
				map.put("dbxx", resultList);
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//学生资助待办事项
				list.add(map);
			}else if("文件管理".equalsIgnoreCase(mklbMap.get("mc"))
					&& model.getMaxSize()>0){
				resultList=dao.getWjmcList(user,model);
				map.put("mklx", "文件管理");
				map.put("xmmc", "文件");
				map.put("dbxx", resultList);
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//文件管理待办事项
				list.add(map);
			}else if("评奖评优".equalsIgnoreCase(mklbMap.get("mc"))
					&& model.getMaxSize()>0){
				//评奖
				List<HashMap<String,Object>>resultL=getPjpyDbsx(user);
				model.setMaxSize(model.getMaxSize()-resultL.size());
				list.addAll(resultL);
			}
		}
		
		//当数据不足11条时,自动补足
		if(model.getMaxSize()<=10){
			for(int i=0;i<model.getMaxSize();i++){
				HashMap<String,Object>hashMap=new HashMap<String,Object>();
				hashMap.put("xmmc", null);
				hashMap.put("xsh", null);
				list.add(hashMap);
			}
		}
		
//		else{
//			//当多余7条时去掉后面的
//			List<HashMap<String, Object>> dblist = new ArrayList<HashMap<String, Object>>();
//			for(int i=0;i<10;i++){
//				dblist.add(list.get(i));
//			}
//			return dblist;
//		}
		return list;
	}

	/**
	 * 获取学生申请信息
	 *
	 * @param user
	 * @return List<HashMap<String,String>> 
	 * author 潇洒的裘
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXssqList(User user) throws Exception {

		HomePageDAO dao = new HomePageDAO();
		SyszDAO syszDao=new SyszDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		// 学生资助 申请信息
		//模块类型
		List<HashMap<String,String>>mklbList=syszDao.getXssqMklb();
		for(int i=0;i<mklbList.size();i++){
			//从待办事项内容表中抽取
			HashMap<String,String>mklbMap=mklbList.get(i);
			if("学生资助".equalsIgnoreCase(mklbMap.get("mc"))){
				// 学生资助待办事项
				list.addAll(dao.getXszzXssq(user));
			}else if("事物办理".equalsIgnoreCase(mklbMap.get("mc"))){
				//文件管理待办事项
				list.addAll(dao.getSwblXssq(user));
			}else if("评奖评优".equalsIgnoreCase(mklbMap.get("mc"))){
				//评奖
				list.addAll(dao.getPjpyXssq(user));
			}else if("违纪处分".equalsIgnoreCase(mklbMap.get("mc"))){
				//违纪处分信息 学生申请
				list.addAll(dao.getXswjxxInfo(user));
			}
		}
		
		int len=list.size();
		//当数据不足7条时,自动补足
		if(list.size()<7){
			for(int i=0;i<7-len;i++){
				HashMap<String,String>hashMap=new HashMap<String,String>();
				hashMap.put("xmmc", "");
				hashMap.put("shjg", "");
				list.add(hashMap);
			}
			return list;
		}else{
			//当多余7条时去掉后面的
			List<HashMap<String, String>> sqlist = new ArrayList<HashMap<String, String>>();
			for(int i=0;i<7;i++){
				sqlist.add(list.get(i));
			}
			return sqlist;
		}
		
	}
	
	public List<HashMap<String,String>>getXsxmList(){
		HomePageDAO dao=new HomePageDAO();
		
		return dao.getSyxsxmList();
	}
	
	public List<HashMap<String,String>>getYqljList(List<HashMap<String,String>> list){
		
		List<HashMap<String,String>> yqljlist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> ljlist=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<list.size();i++){
			HashMap<String,String>hashMap=list.get(i);
			if("友情链接".equalsIgnoreCase(hashMap.get("xsfs"))){
				
				// 大师说不需要判断
//				if(hashMap.get("xmnr").indexOf("http://")==0){
//					yqljlist.add(hashMap);
//				}else{
//					hashMap.put("xmnr","http://"+hashMap.get("xmnr"));
//					yqljlist.add(hashMap);
//				}
				
				yqljlist.add(hashMap);
			}
		}
		
		int len=yqljlist.size();
		if(yqljlist.size()<5){
			ljlist.addAll(yqljlist);
			for(int i=0;i<5-len;i++){
				HashMap<String,String>hashMap=new HashMap<String,String>();
				hashMap.put("xmmc", "");
				hashMap.put("xmnr", "");
				ljlist.add(hashMap);
			}
		}else{
			for(int i=0;i<5;i++){
				HashMap<String,String>yqljMap=yqljlist.get(i);
				ljlist.add(yqljMap);
			}
		}
		return ljlist;
	}
	
	public List<HashMap<String,String>>getLxfsList(List<HashMap<String,String>> list){
		
		List<HashMap<String,String>> lxfslist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>lxlist=new ArrayList<HashMap<String,String>>();
		
		for(int i=0;i<list.size();i++){
			HashMap<String,String>hashMap=list.get(i);
			if("联系方式".equalsIgnoreCase(hashMap.get("xsfs"))){
				lxfslist.add(hashMap);
			}
		}
		
		/*int len=lxfslist.size();
		if(lxfslist.size()<4){
			lxlist.addAll(lxfslist);
			for(int i=0;i<5-len;i++){
				HashMap<String,String>hashMap=new HashMap<String,String>();
				hashMap.put("xmmc", "");
				hashMap.put("xmnr", "");
				lxlist.add(hashMap);
			}
		}else{
			for(int i=0;i<4;i++){
				HashMap<String,String>yqljMap=lxfslist.get(i);
				lxlist.add(yqljMap);
			}
		}*/
		return lxfslist;
	}
	
	
	public List<HashMap<String,String>>getShlcJb(User user,String xmdm){
		HomePageDAO dao = new HomePageDAO();
		return dao.getShlcJb(user,xmdm);
	}
	
	
	//===============以下made by 伟大的骆======================
	/**
	 * 获取登陆用户所负责学生人数
	 * 
	 * @param user
	 * @return
	 */
	public String[] getXsrs(User user) {
		HomePageDAO dao = new HomePageDAO();
		String[] rs = dao.getXsrs(user);
		return rs;
	}

	/**
	 * 获取首页调查内容
	 * 
	 * @param user
	 * @return
	 */
	public String[] getSydcnr(User user) {
		HomePageDAO dao = new HomePageDAO();
		String[] rs = dao.getSydcnr(user);
		return rs;
	}

	/**
	 * 获取首页调查内容选项
	 * 
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getSydcList(User user, String dcid) {
		HomePageDAO dao = new HomePageDAO();
		return dao.getSydcList(user, dcid);
	}

	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsList(User user) {

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();

		List<HashMap<String, String>> list = getKjfsMenuList(userName);

//		if ("stu".equalsIgnoreCase(userType)) {
//
//			if (list != null && list.size() > 0) {
//
//				int num = list.size();
//
//				HashMap<String, String> map = new HashMap<String, String>();
//				list.add(map);
//			}
//		}

		return list;
	}

	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsMenuList(String userName) {

		// 表
		String tableName = "xg_view_xtwh_yhkjfs";
		// 用户名
		String yhm = userName;
		// 查询条件
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// 输入字段
		String[] inPutList = new String[] { yhm };
		// 输出字段
		String[] colList = new String[] { "picpath", "gnmk", "showmk","mkms" };
		// 快捷方式列表
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");

		return list;
	}

	/**
	 * 获取用户相关信息
	 * 
	 * @param request
	 * @return User
	 */
	public User getUser(HttpServletRequest request) {
		// ================== 修改前 begin=========================
		/*
		User user = new User();
		HttpSession session = request.getSession();
		String jyweb = session.getAttribute("jyweb") != null ? session.getAttribute("jyweb").toString() : "";

		if (!Base.isNull(jyweb) && "yes".equals(jyweb)) {
			user.setUserName((String) session.getAttribute("jyweb_userName"));
			user.setUserType((String) session.getAttribute("jyweb_userType"));
		} else {
			user.setUserName((String) session.getAttribute("userName"));
			user.setUserType((String) session.getAttribute("userType"));
			user.setIsFdy(session.getAttribute("isFdy") != null ? session.getAttribute("isFdy").toString() : "");
			user.setUserDep(session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString() : "");
			user.setUserMac(session.getAttribute("userMac") != null ? session.getAttribute("userMac").toString() : "");
			// ===========2010.10.25 edit by luojw===========
			// 辅导员权限
			user.setFdyQx(session.getAttribute("fdyQx") != null ? session.getAttribute("fdyQx").toString() : "");
			user.setBzrQx(session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString() : "");
		}

		user.setHost(request.getRemoteHost());
		user.setIp(request.getRemoteAddr());

		return user;
		*/
		// ================== 修改前 end=========================
		
		User user = new User();
		HttpSession session = request.getSession();
		String jyweb = session.getAttribute("jyweb") != null ? session.getAttribute("jyweb").toString() : "";
		
		if (!Base.isNull(jyweb) && "yes".equals(jyweb)) {
			user.setUserName((String) session.getAttribute("jyweb_userName"));
			user.setUserType((String) session.getAttribute("jyweb_userType"));
		} else {
			user.setUserName((String)session.getAttribute("userName"));
			user.setUserType((String)session.getAttribute("userType"));
			user.setIsFdy(String.valueOf(session.getAttribute("isFdy")));
			user.setUserDep((String)session.getAttribute("userDep"));
			user.setUserMac((String) (session.getAttribute("userMac")) != null ? (String)session.getAttribute("userMac") : "");
			user.setRealName((String)(session.getAttribute("userNameReal") != null ? (String)session.getAttribute("userNameReal") :""));
			
			// ===========2011.3.16 edit by luojw===========
			// 用户类型
			String userType = session.getAttribute("userType") != null ? (String)session.getAttribute("userType") : "";
			// 辅导员权限
			String fdyQx = session.getAttribute("fdyQx") != null ? String.valueOf(session.getAttribute("fdyQx")) : "";
			// 班主任权限
			String bzrQx = session.getAttribute("bzrQx") != null ? String.valueOf(session.getAttribute("bzrQx")) : "";
			// 用户身份
			String userStatus = "";
			
			 // 使用用户角色
			String userRolesApply = user.getUserRolesApply();
			String userRoles = (String)(session.getAttribute("userRoles") != null ? (String)session.getAttribute("userRoles") : "");
			if(!Base.isNull(userRoles)){
				user.setUserRoles(userRoles.split("!!"));
			}
			
			if ("yes".equalsIgnoreCase(userRolesApply)) {
							
				String path = session.getAttribute("clickPath") != null ? session.getAttribute("clickPath").toString() : "";
				
				SearchTjByRoles rolesService = new SearchTjByRoles();
				
				SearchForm searchForm = new SearchForm();
				searchForm.setPath(path);
				
				// 用户角色
				List<HashMap<String, String>> userRolesList = null;
				
				try {
					userRolesList = rolesService.getUserGnmkRoles(searchForm, user);
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				// 用户身份
				userStatus = rolesService.getUserStatus(userRolesList, user);
				
			} else {
				if (Boolean.parseBoolean(bzrQx) && Boolean.parseBoolean(fdyQx)) {
					userStatus = "jd";// 班主任兼辅导员
				} else if (Boolean.parseBoolean(fdyQx)) {
					userStatus = "fdy";// 辅导员
				} else if (Boolean.parseBoolean(bzrQx)) {
					userStatus = "bzr";// 班主任
				} else if ("xy".equalsIgnoreCase(userType)) {
					userStatus = "xy";// 学院
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					userStatus = "xx";// 学校用户（管理员）
				} else {
					userStatus = "stu";// 学生
				}
			}

			String gyglyQx = session.getAttribute("gyglyQx") != null ? session.getAttribute("gyglyQx").toString() : "";
			String syQx = session.getAttribute("syQx") != null ? session.getAttribute("syQx").toString() : "";
			
			user.setFdyQx(fdyQx);
			user.setBzrQx(bzrQx);
			user.setGyglyQx(gyglyQx);
			user.setSyQx(syQx);
			user.setUserStatus(userStatus);

			session.setAttribute("userStatus", userStatus);
		}
		
		user.setHost(request.getRemoteHost());
		user.setIp(request.getRemoteAddr());
				
		return user;
	}
	
	/**
	 * 获取 评奖评优待办事项(通用)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String,Object>>getPjpyDbsx(User user){
		HomePageDAO dao = new HomePageDAO();
		List<HashMap<String,String>>pjdbList=dao.getPjpyDbsx(user);
		List<HashMap<String,Object>>dbxxList=new ArrayList<HashMap<String,Object>>();
		
		for(int i=0;i<pjdbList.size();i++){
			
			HashMap<String,String>pjdbMap=pjdbList.get(i);
			
			HashMap<String,Object>dbxxMap=new HashMap<String,Object>();
		
			dbxxMap.putAll(pjdbMap);
	
			dbxxList.add(dbxxMap);
		}
		return dbxxList;
	}
	
	/**
	 * 
	 * @描述:学生首页弹出对话框用
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-15 下午01:59:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * void 返回类型 
	 * @throws
	 */
	public void setStuznxlist(HttpServletRequest request){
		ZnxglService znxglservice = new ZnxglService();
		String username = getUser(request).getUserName();
		request.setAttribute("wdyjs", znxglservice.getZnxTx(username));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:首页我的应用展示列表
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-21 上午09:14:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * void 返回类型 
	 * @throws
	 */
	public void setGncdList(HttpServletRequest request) throws Exception{
		KsdhService ksdhService = new KsdhService();
		User user = getUser(request);
		request.setAttribute("gncdlist",ksdhService.getGncdSzList(user));
	}
}

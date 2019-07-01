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
	 * ��ҳ���ݳ�ʼ������ʦ��
	 * 
	 * author ΰ�����
	 */
	public void setTeaList(HttpServletRequest request) throws Exception {
		HomePageDAO homePageDAO = new HomePageDAO();
		User user = getUser(request);// �û�����

		// ��������
		//request.setAttribute("dbsxList", getDbsxList(user));
		
		// �����Ƽ�ʦ����ѧ ����У��ͳ��
		if("11318".equals(Base.xxdm)){
			
			List<HashMap<String, String>> zjuxqmcList = homePageDAO.getXsrsXqtj(user);
			request.setAttribute("zjuxqmcList", zjuxqmcList);
		}
		
		// ��������ְҵ����ѧԺ  �����и�ְ����ͳ�� (��ѵ����)
		if("13871".equals(Base.xxdm)){
			List<HashMap<String, String>> zgzrsList = homePageDAO.getZgzrsList(user);
			request.setAttribute("zgzrsList", zgzrsList.size()==0?null:zgzrsList);
		}
		
		// ѧ������ͳ��
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
		// ��ҳ��������
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
		
		// ��ҳ��������ѡ��
//		List<HashMap<String, String>> sydcList = getSydcList(user, dcidS);
//		request.setAttribute("sydcList", sydcList);
		// ��ҳ��ʾ��Ŀ
		List<HashMap<String, String>> xsxmlist = getXsxmList();
		// ��������
//		List<HashMap<String, String>> yqljList = getYqljList(xsxmlist);
//		request.setAttribute("yqljList", yqljList);
		// ��ϵ��ʽ
		List<HashMap<String, String>> lxfsList = getLxfsList(xsxmlist);
		request.setAttribute("lxfsList", lxfsList);
		//վ��������
		ZnxglService znxglservice = new ZnxglService();
		String username = getUser(request).getUserName();
		request.setAttribute("wdyjs", znxglservice.getZnxTx(username));
		//���꼶ѧ����ͳ��
		List<HashMap<String, String>> njxsData = homePageDAO.getNjXsList();
		JSONArray jsonArray = JSONArray.fromObject(njxsData);
		request.setAttribute("njxsData", jsonArray.toString());
		//������ò����ͳ��
		List<HashMap<String, String>> zzmmData = homePageDAO.getXsZzmmList();
		JSONArray zzmmJson = JSONArray.fromObject(zzmmData);
		request.setAttribute("zzmmData", zzmmJson.toString());
	}

	public void setStuList(HttpServletRequest request)
			throws Exception {
		User user = getUser(request);// �û�����
		
		//ѧ��������Ϣ
		Map<String,String> stuInfo = CommonQueryDAO.getStuInfo(user.getUserName());
		request.setAttribute("stuInfo", stuInfo);
		// ѧ������
//		request.setAttribute("xssqList", getXssqList(user));
		// ��ҳ��ʾ��Ŀ
		List<HashMap<String, String>> xsxmlist = getXsxmList();
		// ��ϵ��ʽ
		List<HashMap<String, String>> lxfsList = getLxfsList(xsxmlist);
		request.setAttribute("lxfsList", lxfsList);
		ZnxglService znxglservice = new ZnxglService();
		String username = getUser(request).getUserName();
		request.setAttribute("wdyjs", znxglservice.getZnxTx(username));
		// ��ݷ�ʽ�б�
//		List<HashMap<String, String>> kjfsList = getKjfsList(user);
//		request.setAttribute("kjfsList", kjfsList);

	}

	//	===============����made by ��������======================
	/**
	 * ��ȡ��ʦ�Ĵ�������
	 * @param user
	 * @return List<HashMap<String,String>> 
	 * author ��������
	 */
	public List<HashMap<String, Object>> getDbsxList(User user) {

		HomePageModel model=new HomePageModel();
		HomePageDAO dao = new HomePageDAO();
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		SyszDAO syszDao=new SyszDAO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		//ģ������
		List<HashMap<String,String>>mklbList=syszDao.getMklb(user.getUserName());
		
		String xxdm=Base.xxdm;
		
		//��ʾ��Ϣ
//		HashMap<String,String>tsMap=new HashMap<String,String>();
//		tsMap.put("tsxx","ע��"+Base.currXn+"ѧ�� "+Base.currXq+"ѧ�� "+Base.currNd+"��� �鿴��ϸ��Ϣ����\"more\"");
//		list.add(tsMap);
		
		model.setMaxSize(10);
		List<HashMap<String, String>> resultList  = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<mklbList.size();i++){
			//�Ӵ����������ݱ��г�ȡ
			HashMap<String,Object>map=new HashMap<String,Object>();
			HashMap<String,String>mklbMap=mklbList.get(i);
			if("ѧ������".equalsIgnoreCase(mklbMap.get("mc"))
					&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)
					&& model.getMaxSize()>0){
				resultList=dzdxDAO.getZzpjDbsx(user, model);
				map.put("mklx", "ѧ������");
				map.put("xmmc", "����");
				model.setMklx("zz");
				map.put("dbxx", resultList);
				
				// -------------2012.2.22 ������----------------
				// -------------�޸����ݣ��˴�����Ƿȱ---------------
				// ----------��Ӧ��д��10-resultList.size()---------
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//�й����ʴ�ѧ ����
				list.add(map);
			}else if("��������".equalsIgnoreCase(mklbMap.get("mc"))
					&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)
					&& model.getMaxSize()>0){
				resultList=dzdxDAO.getZzpjDbsx(user,model);
				map.put("mklx", "��������");
				map.put("xmmc", "����");
				model.setMklx("pj");
				map.put("dbxx", resultList);
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//�й����ʴ�ѧ����
				list.add(map);
			}else if("ѧ������".equalsIgnoreCase(mklbMap.get("mc"))
					&& model.getMaxSize()>0){
				resultList=dao.getXszzDbsx(user,model);
				map.put("mklx", "ѧ������");
				map.put("xmmc", "����");
				map.put("dbxx", resultList);
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//ѧ��������������
				list.add(map);
			}else if("�ļ�����".equalsIgnoreCase(mklbMap.get("mc"))
					&& model.getMaxSize()>0){
				resultList=dao.getWjmcList(user,model);
				map.put("mklx", "�ļ�����");
				map.put("xmmc", "�ļ�");
				map.put("dbxx", resultList);
				model.setMaxSize(model.getMaxSize()-resultList.size());
				//�ļ������������
				list.add(map);
			}else if("��������".equalsIgnoreCase(mklbMap.get("mc"))
					&& model.getMaxSize()>0){
				//����
				List<HashMap<String,Object>>resultL=getPjpyDbsx(user);
				model.setMaxSize(model.getMaxSize()-resultL.size());
				list.addAll(resultL);
			}
		}
		
		//�����ݲ���11��ʱ,�Զ�����
		if(model.getMaxSize()<=10){
			for(int i=0;i<model.getMaxSize();i++){
				HashMap<String,Object>hashMap=new HashMap<String,Object>();
				hashMap.put("xmmc", null);
				hashMap.put("xsh", null);
				list.add(hashMap);
			}
		}
		
//		else{
//			//������7��ʱȥ�������
//			List<HashMap<String, Object>> dblist = new ArrayList<HashMap<String, Object>>();
//			for(int i=0;i<10;i++){
//				dblist.add(list.get(i));
//			}
//			return dblist;
//		}
		return list;
	}

	/**
	 * ��ȡѧ��������Ϣ
	 *
	 * @param user
	 * @return List<HashMap<String,String>> 
	 * author ��������
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXssqList(User user) throws Exception {

		HomePageDAO dao = new HomePageDAO();
		SyszDAO syszDao=new SyszDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		// ѧ������ ������Ϣ
		//ģ������
		List<HashMap<String,String>>mklbList=syszDao.getXssqMklb();
		for(int i=0;i<mklbList.size();i++){
			//�Ӵ����������ݱ��г�ȡ
			HashMap<String,String>mklbMap=mklbList.get(i);
			if("ѧ������".equalsIgnoreCase(mklbMap.get("mc"))){
				// ѧ��������������
				list.addAll(dao.getXszzXssq(user));
			}else if("�������".equalsIgnoreCase(mklbMap.get("mc"))){
				//�ļ������������
				list.addAll(dao.getSwblXssq(user));
			}else if("��������".equalsIgnoreCase(mklbMap.get("mc"))){
				//����
				list.addAll(dao.getPjpyXssq(user));
			}else if("Υ�ʹ���".equalsIgnoreCase(mklbMap.get("mc"))){
				//Υ�ʹ�����Ϣ ѧ������
				list.addAll(dao.getXswjxxInfo(user));
			}
		}
		
		int len=list.size();
		//�����ݲ���7��ʱ,�Զ�����
		if(list.size()<7){
			for(int i=0;i<7-len;i++){
				HashMap<String,String>hashMap=new HashMap<String,String>();
				hashMap.put("xmmc", "");
				hashMap.put("shjg", "");
				list.add(hashMap);
			}
			return list;
		}else{
			//������7��ʱȥ�������
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
			if("��������".equalsIgnoreCase(hashMap.get("xsfs"))){
				
				// ��ʦ˵����Ҫ�ж�
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
			if("��ϵ��ʽ".equalsIgnoreCase(hashMap.get("xsfs"))){
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
	
	
	//===============����made by ΰ�����======================
	/**
	 * ��ȡ��½�û�������ѧ������
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
	 * ��ȡ��ҳ��������
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
	 * ��ȡ��ҳ��������ѡ��
	 * 
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getSydcList(User user, String dcid) {
		HomePageDAO dao = new HomePageDAO();
		return dao.getSydcList(user, dcid);
	}

	/**
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsList(User user) {

		// �û���
		String userName = user.getUserName();
		// �û�����
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
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsMenuList(String userName) {

		// ��
		String tableName = "xg_view_xtwh_yhkjfs";
		// �û���
		String yhm = userName;
		// ��ѯ����
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// �����ֶ�
		String[] inPutList = new String[] { yhm };
		// ����ֶ�
		String[] colList = new String[] { "picpath", "gnmk", "showmk","mkms" };
		// ��ݷ�ʽ�б�
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");

		return list;
	}

	/**
	 * ��ȡ�û������Ϣ
	 * 
	 * @param request
	 * @return User
	 */
	public User getUser(HttpServletRequest request) {
		// ================== �޸�ǰ begin=========================
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
			// ����ԱȨ��
			user.setFdyQx(session.getAttribute("fdyQx") != null ? session.getAttribute("fdyQx").toString() : "");
			user.setBzrQx(session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString() : "");
		}

		user.setHost(request.getRemoteHost());
		user.setIp(request.getRemoteAddr());

		return user;
		*/
		// ================== �޸�ǰ end=========================
		
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
			// �û�����
			String userType = session.getAttribute("userType") != null ? (String)session.getAttribute("userType") : "";
			// ����ԱȨ��
			String fdyQx = session.getAttribute("fdyQx") != null ? String.valueOf(session.getAttribute("fdyQx")) : "";
			// ������Ȩ��
			String bzrQx = session.getAttribute("bzrQx") != null ? String.valueOf(session.getAttribute("bzrQx")) : "";
			// �û����
			String userStatus = "";
			
			 // ʹ���û���ɫ
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
				
				// �û���ɫ
				List<HashMap<String, String>> userRolesList = null;
				
				try {
					userRolesList = rolesService.getUserGnmkRoles(searchForm, user);
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				// �û����
				userStatus = rolesService.getUserStatus(userRolesList, user);
				
			} else {
				if (Boolean.parseBoolean(bzrQx) && Boolean.parseBoolean(fdyQx)) {
					userStatus = "jd";// �����μ渨��Ա
				} else if (Boolean.parseBoolean(fdyQx)) {
					userStatus = "fdy";// ����Ա
				} else if (Boolean.parseBoolean(bzrQx)) {
					userStatus = "bzr";// ������
				} else if ("xy".equalsIgnoreCase(userType)) {
					userStatus = "xy";// ѧԺ
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					userStatus = "xx";// ѧУ�û�������Ա��
				} else {
					userStatus = "stu";// ѧ��
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
	 * ��ȡ �������Ŵ�������(ͨ��)
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
	 * @����:ѧ����ҳ�����Ի�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-15 ����01:59:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * void �������� 
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
	 * @����:��ҳ�ҵ�Ӧ��չʾ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-21 ����09:14:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * void �������� 
	 * @throws
	 */
	public void setGncdList(HttpServletRequest request) throws Exception{
		KsdhService ksdhService = new KsdhService();
		User user = getUser(request);
		request.setAttribute("gncdlist",ksdhService.getGncdSzList(user));
	}
}

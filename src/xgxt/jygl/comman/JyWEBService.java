package xgxt.jygl.comman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.jygl.njjs.NjjsJywebService;
import xgxt.sztz.SztzForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class JyWEBService {

	JyWEBDAO dao = new JyWEBDAO();
	
	private static JyWEBDAO jywebDAO = new JyWEBDAO();
	private static HashMap<String,String> lxfxMap = null;
	private static List<HashMap<String,String>> tpljList = null; 
	private static List<HashMap<String,String>> wzljList = null;
	private static List<HashMap<String,String>> dwList = null;
	private static List<HashMap<String, String>> filesList = null;
	private static List<HashMap<String,String>> newsList = null;
	private static List<HashMap<String,String>> informList = null;
	private static List<HashMap<String,String>> rcList = null;
	private static List<HashMap<String,String>> zpxxList = null;
	private static List<HashMap<String,String>> zphList = null;
	private static List<HashMap<String,String>> xnzpList = null;
	
	static {
		System.out.println("-------就业网初始化工作开始---------");
		setLxfxMap();
		System.out.println("-------联系方式加载完成-------");
		setTpljList();
		System.out.println("-------友情链接加载完成-------");
		//setDwList();
		//System.out.println("-------企业目录加载完成-------");
		setFilesList();
		System.out.println("-------下载专区加载完成--------");
		setNewsList();
		System.out.println("-------新闻加载完成--------");
		setRcList();
		System.out.println("-------人才列表加载完成--------");
		setZpxxList();
		System.out.println("-------招聘信息加载完成--------");
		setZphList();
		System.out.println("-------招聘会加载完成--------");
		setXnzpList();
		System.out.println("-------校内招聘加载完成--------");
		System.out.println("-----就业网初始化操作完成--------");
	}
	
	
	/**
	 * 招聘会列表
	 *
	 */
	public static void setZphList() {
		zphList = jywebDAO.getZphList();
	}
	
	/**
	 * 招聘信息列表
	 *
	 */
	public static void setZpxxList() {
		zpxxList = jywebDAO.getZpxx();
	}
	
	/**
	 * 人才列表
	 *
	 */
	public static void setRcList() {
		rcList = jywebDAO.getRctjList();
	}
	
	/**
	 * 联系方式
	 *
	 */
	public static void setLxfxMap() {
		lxfxMap = jywebDAO.getLxfs();
	}
	
	/**
	 * 友情链接
	 *
	 */
	public static void setTpljList() {
		tpljList = jywebDAO.getTplj();
		wzljList = jywebDAO.getWzlj();
	}

	/**
	
	/**
	 * 企业目录
	 * @throws SQLException 
	 *
	 */
	public static void setDwList() {
		try {
			dwList = jywebDAO.getDwList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载专区
	 *
	 */
	public static void setFilesList() {
		filesList = jywebDAO.getFiles();
	}
	
	
	/**
	 * 就业新闻
	 *
	 */
	public static void setNewsList() {
		newsList = jywebDAO.getNews("1");
		informList = jywebDAO.getNews("3");
	}
	
	
	/**
	 * 校内招聘
	 *
	 */
	public static void setXnzpList() {
		xnzpList = jywebDAO.getZtzpList();
		
	}
	
	
	/**
	 * 就业网初始化
	 * @param request
	 * @param userType
	 * @throws SQLException 
	 */
	public void jywebInit(HttpServletRequest request,String userType) throws SQLException {
		DAO jydao = DAO.getInstance();
		
		//联系方式
		request.setAttribute("lxfxMap", lxfxMap);

		//图片链接
		request.setAttribute("tpljList", tpljList);
		
		//文字链接
		request.setAttribute("wzljList", wzljList);
		
		//就业新闻
		request.setAttribute("newsList", newsList);
		
		//通知公告
		request.setAttribute("informList", informList);
		
		//人才推荐
		request.setAttribute("rcList", rcList);
		
		//下载专区
		request.setAttribute("filesList", filesList);
		
		//招聘岗位信息
		request.setAttribute("zpxxList", zpxxList);
		
		//招聘信息
		request.setAttribute("zphList", zphList);
		
		//校内招聘
		request.setAttribute("xnzpList", xnzpList);
		
		//招聘栏目
		List<HashMap<String, String>> zplmList = jydao.getListNotOut("select * from xg_jyweb_zplxb order by dm", new String[]{});
		request.setAttribute("zplmList",zplmList );
	}
	
	
	/**
	 * 修改密码
	 * @return
	 */
	public boolean updateMm(Model model){
		boolean flag = false;
		DAO myDao = DAO.getInstance();
		String sql = "update jygl_dwxxb set mm=? where yhm=?";
		
		String[] input = new String[]{model.getMm(), model.getYhm()};
		
		try {
			flag = myDao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 就业网下拉列表
	 * @param request
	 * @param flg
	 */
	public void setJywebList(HttpServletRequest request, String flg) {
		DAO jydao = DAO.getInstance();
		if ("new".equals(flg)) {
			List<HashMap<String, String>> newLxList = null;
			if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_BJQNZZXY)){
				newLxList = dao.getSelectList("bjqnjyweb");
			} else {
				newLxList = dao.getSelectList("jyweb");
			}
			request.setAttribute("newLxList", newLxList);
		} else if("company".equals(flg)) {
			//单位性质
			List<HashMap<String, String>> dwxzList = jydao.getWhList("dmk_dwxz2", "dwxzdm", "dwxz", "", "", "");
			request.setAttribute("dwxzList", dwxzList);
			//行业分类
			List<HashMap<String, String>> hyflList = jydao.getWhList("dmk_hyfl", "hyfldm", "hyfl", "", "", "");
			request.setAttribute("hyflList", hyflList);
		} else if("resume".equals(flg)) {
			//民族
			List<HashMap<String, String>> mzList = jydao.getWhList("mzdmb", "mzdm", "mzmc", "", "", "");
			request.setAttribute("mzList", mzList);
			
			//政治面貌
			List<HashMap<String, String>> zzmmList = jydao.getWhList("dmk_zzmm", "zzmmdm", "zzmm", "", "", "");
			request.setAttribute("zzmmList", zzmmList);
		} else if("work".equals(flg)){
			// 招聘信息
			List<HashMap<String, String>> xbList = dao.getSelectList("xb");
			request.setAttribute("xbList", xbList);
			
			//工作性质
			List<HashMap<String, String>> gzxzList = dao.getSelectList("gzxz");
			request.setAttribute("gzxzList", gzxzList);
			
			//专业对照
			List<HashMap<String, String>> zyList = jydao.getWhList("dmk_zydmdzb", "jygl_zydm", "jygl_zymc", "", "", "");
			request.setAttribute("zyList", zyList);
			
		} else if ("zpgl".equals(flg)){
			//招聘类型
			List<HashMap<String, String>> zplxList = getZplxList();
			request.setAttribute("zplxList", zplxList);
		}
		
		request.setAttribute("nowTime", getNow());
		//审核状态
		List<HashMap<String, String>> shztList = dao.getSelectList("shzt");
		request.setAttribute("shztList", shztList);
		//审核结果
		List<HashMap<String, String>> shjgList = dao.getSelectList("shjg");
		request.setAttribute("shjgList", shjgList);
	}
	
	
	/**
	 * 招聘类型
	 * @return
	 */
	public List<HashMap<String,String>> getZplxList(){
		
		return dao.getZplxList();
	}
	
	
	/**
	 * 当前时间
	 * @return
	 */
	public  String getNow() {
		
		return dao.getNow();
	}
	
	
	/**
	 * 登录
	 * @param userType
	 * @param userName
	 * @param password
	 * @return
	 */
	protected String[] login(String userType,String userName, String password) {
		
		String[] yhInfo = null;
		
		if(!Base.isNull(userType) && "dw".equals(userType)) {
			yhInfo = dao.dwLogin(userName, password);
		} else if(!Base.isNull(userType) && "xy".equals(userType)) {
			yhInfo = dao.xyLogin(userName, password);
		} else if(!Base.isNull(userType) && "stu".equals(userType)) {
			yhInfo = dao.stuLogin(userName, password);
		} else if (!Base.isNull(userName) && "admin".equals(userType)) {
			yhInfo = dao.adminLogin(userName, password);
		}
		
		return yhInfo;
	}
	
	
	/**
	 * 获取就业网title
	 * @param path
	 * @return
	 */
	public String getTitle(String path) {
		
		return dao.getTitle(path);
	}
	
	
	/**
	 * 新闻信息
	 * @param pkValue
	 * @return
	 */
	protected HashMap<String, String> getNewInfo(String pkValue,String wjbt){
		
		if(!Base.isNull(pkValue) && !("wjbt").equals(wjbt)) {
			try {
				dao.setReadTimes(pkValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dao.getNewInfo(pkValue);
		} else if (!Base.isNull(wjbt)) {
			try {
				dao.setReadTimesByWjbt(pkValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dao.getNewInfoByWjbt(pkValue);
		} else {
			return null;
		}
	}
	
	
	/**
	 * 文件下载次数加1
	 * @param pkValue
	 * @throws Exception
	 */
	public boolean setFileXzcs(String pkValue) {
		try {
			 return dao.setFileXzcs(pkValue);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * 获取指定单位的招聘信息
	 * @param dwmc
	 * @return
	 */
	protected List<HashMap<String,String>> getZpxx(String pkValue){
		return dao.getZpxx(pkValue);
	}

	
	/**
	 * 获取导出的全部列
	 * @param tableName
	 * @return
	 */
	public String[] getColumn(String tableName) {
		
		return dao.getColumn(tableName);
	}
	
	
	/**
	 * 就业网设置disabled 、class
	 * @param request
	 * @param flg
	 */
	protected void setCustomAudiColumn(HttpServletRequest request, String flg){
		StringBuilder sb = new StringBuilder();
		
		if ("shzt".equals(flg)) {
			sb.append(" (case when shzt='通过' or shzt='不通过' then 'disabled' when shzt='退回' then 'th' else '' end) disabled, ");
		} else	if ("xxsh".equals(flg)) {
			sb.append(" (case when xxsh='通过' or xxsh='不通过' then 'disabled' when xxsh='退回' then 'th' else '' end) disabled, ");
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}

	
	/**
	 * 根据用户类型获取操作菜单
	 * @param userType
	 * @return
	 */
	public List<HashMap<String,String>> getMenus(String userType){
		
		return dao.getMenus(userType);
	}
	
	
	/**
	 * 用户注册
	 * @param model
	 * @return
	 */
	public String insertUser(Model model){
		
		String msg = "注册失败！";
		DAO myDao = DAO.getInstance();
		String tableName = "jygl_dwxxb";
		
		String[] fields = new String[]{"yhm", "mm", "dwmc", "qyfr", "lxdh", "tswt1",
				"tswt2", "da1", "da2", "dwlx"};
		String[] input = new String[]{model.getYhm(), model.getMm(), model.getDwmc(),
				model.getQyfr(), model.getLxdh(), model.getTswt1(), model.getTswt2(),
				model.getDa1(), model.getDa2(), model.getDwlx()};
	
		try{
			boolean flag = myDao.runInsert(tableName, fields, input);
			if(flag){
				msg = "注册成功！";
			}
			
		}catch (Exception e) {
			if(e.getMessage().contains("ORA-00001")){//违反唯一约束
				msg = "用户名或单位名已存在！";
			} else{
				e.printStackTrace();
			}
			
		}
		
		return msg;
	}
	

	/**
	 * 根据用户名获得公司信息
	 */
	public Map<String, String> getCompanyInfo(String pk, String pkValue, String[] colList){
		String tableName = "view_jygl_dwxxb";
		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	
	}
	
	
	/**
	 * 学生岗位收藏、浏览、投递简历
	 * @param pkValue
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean saveStuJobs(String pkValue,String xh,String type) throws Exception {
		
		if ("浏览".equals(type)){
			//保存岗位浏览记录
			if (dao.deleteStuJobs(pkValue, xh)) {
				return dao.saveStuJobs(pkValue, xh, type);
			}
		} else {
			//判断是否申请或收藏过此岗位
			List<HashMap<String,String>> list = dao.getStuJobs(xh, type, pkValue);
			
			if (list.size() == 0){
				boolean flg = dao.saveStuJobs(pkValue, xh, type);
				
				//南京技师的个性化需求（加一条审核记录）
				if (flg && Globals.XXDM_NJJS.equals(Base.xxdm)  && "申请".equals(type)){
					NjjsJywebService njjsJywebService = new NjjsJywebService();
					return njjsJywebService.saveXsbmsh(xh, pkValue);
				}
				return flg;
			} 
		}
		
		return false;
	}
	
	
	/**
	 * 判断学生是否有简历
	 * @param xh
	 * @return
	 */
	public boolean hasResume(String xh) {
		String count = dao.getStuResume(xh);
		
		if ("0".equals(count)) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * 获取年级，专业，班级列表
	 * @param request
	 */
	public void setNjXyZyBjList(HttpServletRequest request) {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("jyweb_userType");
		String userDep = (String) session.getAttribute("jyweb_userDep");
		String xy = request.getParameter("xydm");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		
		if ("xy".equalsIgnoreCase(userType)) {
			xy = userDep;
		}
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", null==(Base.getZyMap()).get(xy) ? new ArrayList<HashMap<String,String>>() : (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", null==(Base.getBjMap()).get(bjKey) ? new ArrayList<HashMap<String,String>>() : (Base.getBjMap()).get(bjKey));
	}
	

	/**
	 * 保存友情链接
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public boolean saveYqlj(String[] values) throws Exception {
		
		return dao.saveYqlj(values);
	}
	
	
	/**
	 *友情链接单个删除 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delYqlj(String pkValue) throws Exception {
		if (delFile(new String[] {pkValue},"tp")) {
			return dao.delYqlj(pkValue);
		} else {
			return false;
		}
		
	}
	
	
	/**
	 * 删除文件
	 * @param pkValues
	 * @return
	 */
	public boolean delFile(String[] pkValues,String flg) {
		
		boolean result = true;
		String[] files = new String[] {};
		
		if (null != pkValues && pkValues.length>0) {
			try {
				//获取文件绝对路径
				files = "file".equals(flg) ? dao.getFiles(pkValues) : dao.getTps(pkValues);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0 ; i< files.length;i++) {
			if (!Base.isNull(files[i])) {
				File file = new File(files[i]);
				if(null != file && file.exists()) {
					//删除文件
					result = file.delete();
				} else {
					result = false;
				}	
			}
		}
		
		return result;
		
	}
	
	
	/**
	 * 保存文件记录
	 * @param form
	 * @return
	 */
	public boolean saveWjsc(JyglForm form) {
		
		try {
			return dao.saveWjsc(form);
		} catch (Exception e) {
			return false;
		}
	}
	
	
	 /** 文件浏览次数加1
	  * @param pkValue
	  * @throws Exception
	  */
	public boolean setFileLlcs(String pkValue)  {
		try {
			return dao.setFileLlcs(pkValue);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 招聘会浏览次数加1 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	protected boolean setZphLlcs(String pkValue){
		try {
			return dao.setZphLlcs(pkValue);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 图片上传
	 * @param myForm
	 * @param request
	 * @param path
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String upload(JyglForm myForm,String dirPath,HttpServletRequest request) 
		throws FileNotFoundException, IOException {
		String path = "";
		FormFile file = myForm.getFile();
		//如果文件名不为空进入上传文件
		if (null != file && !Base.isNull(file.getFileName())) {
			//取绝对路径
//			String dirPath = "";
			File f = new File(dirPath);
		    if (!f.exists()) {
			   f.mkdir();
		    }//创建文件夹
			String guid = StringUtils.getGuid();//获取随机ID
			int size = file.getFileSize();//文件大小
			if (size > 1024*1024) {
				//超过1M不上传并发送message
				request.setAttribute("message", "上传图片大小不能超过1MB!");
			} else {
				String fName = guid + file.getFileName();//创建文件名
				myForm.setFileName(fName);
				InputStream in = file.getInputStream();//获取输入流
				path = dirPath + "/" + fName;//创建文件绝对路径加文件名
				OutputStream out = new FileOutputStream(path);//创建输出流
				int data = 0;
				byte[] buffer = new byte[size];
				while ((data = in.read(buffer, 0, size)) != -1) {
					//写文件到本地磁盘
					out.write(buffer, 0, data);
				}
				out.close();
				in.close();
				
			}
		}
		return path;
	}
	
	
	/**
	 * 文件上传
	 * @param myForm
	 * @param request
	 * @throws IOException
	 */
	public void fileUpload(JyglForm myForm,HttpServletRequest request) throws IOException {
		FormFile file = myForm.getFile();
		
		if (null != file && !Base.isNull(file.getFileName())) {
			//如果文件名不为空进入上传文件
			if (null != file && !Base.isNull(file.getFileName())) {
				//取绝对路径
				String dirPath = request.getRealPath("")+".upload";
				
				File f = new File(dirPath);
			    if (!f.exists()) {
				   f.mkdir();
			    }//创建文件夹
			    
			    String filePath = dirPath+"/jyweb";
				
				f = new File(filePath);
			    if (!f.exists()) {
				   f.mkdir();
			    }	
			    
				String guid = StringUtils.getGuid();//获取随机ID
				int size = file.getFileSize();//文件大小
				if (size > 10485760) {
					//超过10M不上传并发送message
					request.setAttribute("message", "上传文件大小不能超过10MB!");
				} else {
					String fName = guid + file.getFileName();//创建文件名
					InputStream in = file.getInputStream();//获取输入流
					String path = dirPath + "/" + fName;//创建文件绝对路径加文件名
					OutputStream out = new FileOutputStream(path);//创建输出流
					int data = 0;
					byte[] buffer = new byte[size];
					while ((data = in.read(buffer, 0, size)) != -1) {
						//写文件到本地磁盘
						out.write(buffer, 0, data);
					}
					out.close();
					in.close();
					//设置form值
					myForm.setWjm(file.getFileName());
					myForm.setWjlj(path);
					myForm.setGuid(guid);
					request.setAttribute("message", saveWjsc(myForm) ? "操作成功!" : "操作失败!");
				}
			}
		}
	}
	
	
	/**
	 * 插入回复信息
	 * @param model
	 * @return
	 */
	public boolean insertHfxx(Model model){
		boolean flag = false;
		DAO myDao = DAO.getInstance();
		String[] fields = new String[]{"xh", "dwmc", "gwmc", "hfxx", "czlx","gwfbsj"};
		String[] input = new String[]{model.getXh(), model.getDwmc(),
				model.getGwmc(), model.getHfxx(), model.getCzlx(),model.getGwfbsj()};
		
		try {
			flag = myDao.runInsert("jyweb_zpxxtjb", fields, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	/**
	 * 插入浏览统计数据
	 * @return
	 */
	public boolean insertTj(Map<String, String> map){
		boolean flag = false;
		DAO myDao = DAO.getInstance();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		
		String[] sqlArr = new String[2];
		sql1.append("delete from jyweb_zpxxtjb where xh='")
			.append(map.get("xh"))
			.append("' and dwmc='")
			.append(map.get("dwmc"))
			.append("' and czlx='")
			.append(map.get("czlx"))
			.append("' and sj=(to_char(sysdate,'yyyymmdd'))");
		
		sql2.append("insert into jyweb_zpxxtjb(xh,dwmc,gwmc,czlx) values('")
			.append(map.get("xh"))
			.append("','")
			.append(map.get("dwmc"))
			.append("','")
			.append(map.get("gwmc"))
			.append("','")
			.append(map.get("czlx"))
			.append("')");
		
		sqlArr[0] = sql1.toString();
		sqlArr[1] = sql2.toString();
		
		try {
			myDao.runBatch(sqlArr);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	
	/**
	 * 判断用户是否存在s
	 * @return
	 */
	protected boolean isUserExists(String userName, String password){
		boolean flag = false;
		String[] yhInfo = dao.dwLogin(userName, password);
		
		if(yhInfo != null && StringUtils.isNotNull(yhInfo[0])){
			flag = true;
		}
		
		return flag;
	}

	
	/**
	 * 单位相关统计
	 * @param form
	 * @return
	 */
	public List<String[]> getDwtj(JyglForm form){
		
		return dao.getDwtj(form);
	}
	
	
	/**
	 * 招聘信息相关统计
	 * @param form
	 * @return
	 */
	public List<String[]> getZpxxTj(JyglForm form){
		
		return dao.getZpxxTj(form);
	}
	
	
	/**
	 * 学生相关统计-按学院
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByXy(JyglForm form) {

		return dao.getStuTjByXy(form);
	}

	
	/**
	 * 学生相关统计-按专业
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByZy(JyglForm form) {

		return dao.getStuTjByZy(form);
	}

	
	/**
	 * 学生相关统计-按班级
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByBj(JyglForm form) {

		return dao.getStuTjByBj(form);
	}
	
	
	/**
	 * 学生相关统计-按学生
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByStu(JyglForm form) {

		return dao.getStuTjByStu(form);
	}

	
	/**
	 * 新闻保存
	 * @param form
	 * @return
	 */
	public boolean saveNews(JyglForm form) {
		
		return dao.saveNews(form);
	}
	
	
	/**
	 * 新闻修改
	 * @param form
	 * @return
	 */
	public boolean updateNews(JyglForm form,String pkVlaue) {
		
		return dao.updateNews(form,pkVlaue);
	}
	
	
	
	/**
	 * 单位列表（岗位信息库使用）
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getDwList(JyglForm model){
		
		return dao.getDwList(model);
	}
	
	
	
	/**
	 * 岗位列表（岗位信息库使用）
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getGwList(JyglForm model){
		
		return dao.getGwList(model);
	}
	
	
	
	/**
	 * 保存主题招聘关联信息
	 * @param id
	 * @param tempDwmc
	 * @param tempGwValue
	 * @return
	 */
	public boolean saveZtzp(String id, String tempDwmc,String tempGwValue){
		String[] tempDwmcArr = Base.isNull(tempDwmc) ? new String[] {} : tempDwmc.split("!!@!!");
		String[] tempGwValueArr = tempGwValue.split("!!@!!");
		
		Set<String> dwmc = new HashSet<String>() ;
		List<String> gwValue = new ArrayList<String>() ;
			
		
		if (null != tempDwmcArr && null != tempGwValueArr){
			
			for (int i = 0 ; i < tempDwmcArr.length ; i++){
				dwmc.add(tempDwmcArr[i]);
			}
			
			for (int i = 0 ; i < tempGwValueArr.length ; i++){
				gwValue.add(tempGwValueArr[i]);
			}
			
		}
		try {
			return dao.saveZtzp(id, dwmc.toArray(new String[]{}), gwValue);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * 获取主题招聘岗位列表 
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getZtzpGwList(String pkValue){
		
		return dao.getZtzpGwList(pkValue);
	}
	
	
	
	/**
	 * 获取主题招聘中单位名称
	 * @param pkValue
	 * @return
	 */
	public String getTempDwmc(String pkValue){
		DAO jydao = DAO.getInstance();
		StringBuilder tempDwmc = new StringBuilder();
		
		 List<HashMap<String,String>> dwmc = jydao.getWhList("xg_jyweb_ztzpgsb", "ztid", "dwid", "", "ztid", pkValue);
		 
		 for (int i = 1 ; i < dwmc.size() ; i++){
			 tempDwmc.append(dwmc.get(i).get("mc"));
			 if (i != dwmc.size()-1){
				 tempDwmc.append("!!@!!");
			 }
		 }
		 return tempDwmc.toString();
	}
	
	
	
	/**
	 * 获取主题招聘中岗位名称
	 * @param pkValue
	 * @return
	 */
	public String getTempGwValue(String pkValue){
		DAO jydao = DAO.getInstance();
		StringBuilder tempDwmc = new StringBuilder();
		
		 List<HashMap<String,String>> dwmc = jydao.getWhList("xg_jyweb_ztzpgwb", "ztid", "gwid", "", "ztid", pkValue);
		 
		 for (int i = 1 ; i < dwmc.size() ; i++){
			 tempDwmc.append(dwmc.get(i).get("mc"));
			 if (i != dwmc.size()-1){
				 tempDwmc.append("!!@!!");
			 }
		 }
		 return tempDwmc.toString();
	}

	
	/**
	 * 主题招聘浏览次数加1
	 * @param pkValue
	 * @return
	 */
	public boolean setLlcs(String pkValue){
		try {
			return dao.setLlcs(pkValue);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	/**
	 * 删除主题招聘关联数据
	 * @param pkValues
	 * @return
	 */
	public boolean delZtzp(String[] pkValues) {
		
		if (null == pkValues) {
			return false;
		}
		
		try {
			return dao.delZtzp(pkValues);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * 根据单位名称获取用户名
	 * @param dwmc
	 * @return
	 */
	public String getYhm(String dwmc) {
		
		return dao.getYhm(dwmc);
	}
	
	
	/**
	 * 修改单位回复的是否删除状态
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSfsc(String[] pkValues) {
		
		if (null != pkValues) {
			try {
				return dao.updateSfsc(pkValues);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 * 获取指定表的指定列
	 * @param realTable
	 * @param colList
	 * @return
	 */
	public String[] getColumn(String realTable,String[] colList){
		
		return dao.getColumnNameCN(colList, realTable);
	}



	/**
	 * 中心介绍查询
	 * @param lx
	 * @return
	 */
	public String getZxjs(String lx){
		
		return dao.getZxjs(lx);
	}


	/**
	 * 通用保存
	 */
	public boolean saveJyweb(SaveForm form, JyglForm model,
			HttpServletRequest request) throws Exception {
		
		return dao.submitData(form, model, request);
	}

}

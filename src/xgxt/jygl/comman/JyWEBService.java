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
		System.out.println("-------��ҵ����ʼ��������ʼ---------");
		setLxfxMap();
		System.out.println("-------��ϵ��ʽ�������-------");
		setTpljList();
		System.out.println("-------�������Ӽ������-------");
		//setDwList();
		//System.out.println("-------��ҵĿ¼�������-------");
		setFilesList();
		System.out.println("-------����ר���������--------");
		setNewsList();
		System.out.println("-------���ż������--------");
		setRcList();
		System.out.println("-------�˲��б�������--------");
		setZpxxList();
		System.out.println("-------��Ƹ��Ϣ�������--------");
		setZphList();
		System.out.println("-------��Ƹ��������--------");
		setXnzpList();
		System.out.println("-------У����Ƹ�������--------");
		System.out.println("-----��ҵ����ʼ���������--------");
	}
	
	
	/**
	 * ��Ƹ���б�
	 *
	 */
	public static void setZphList() {
		zphList = jywebDAO.getZphList();
	}
	
	/**
	 * ��Ƹ��Ϣ�б�
	 *
	 */
	public static void setZpxxList() {
		zpxxList = jywebDAO.getZpxx();
	}
	
	/**
	 * �˲��б�
	 *
	 */
	public static void setRcList() {
		rcList = jywebDAO.getRctjList();
	}
	
	/**
	 * ��ϵ��ʽ
	 *
	 */
	public static void setLxfxMap() {
		lxfxMap = jywebDAO.getLxfs();
	}
	
	/**
	 * ��������
	 *
	 */
	public static void setTpljList() {
		tpljList = jywebDAO.getTplj();
		wzljList = jywebDAO.getWzlj();
	}

	/**
	
	/**
	 * ��ҵĿ¼
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
	 * ����ר��
	 *
	 */
	public static void setFilesList() {
		filesList = jywebDAO.getFiles();
	}
	
	
	/**
	 * ��ҵ����
	 *
	 */
	public static void setNewsList() {
		newsList = jywebDAO.getNews("1");
		informList = jywebDAO.getNews("3");
	}
	
	
	/**
	 * У����Ƹ
	 *
	 */
	public static void setXnzpList() {
		xnzpList = jywebDAO.getZtzpList();
		
	}
	
	
	/**
	 * ��ҵ����ʼ��
	 * @param request
	 * @param userType
	 * @throws SQLException 
	 */
	public void jywebInit(HttpServletRequest request,String userType) throws SQLException {
		DAO jydao = DAO.getInstance();
		
		//��ϵ��ʽ
		request.setAttribute("lxfxMap", lxfxMap);

		//ͼƬ����
		request.setAttribute("tpljList", tpljList);
		
		//��������
		request.setAttribute("wzljList", wzljList);
		
		//��ҵ����
		request.setAttribute("newsList", newsList);
		
		//֪ͨ����
		request.setAttribute("informList", informList);
		
		//�˲��Ƽ�
		request.setAttribute("rcList", rcList);
		
		//����ר��
		request.setAttribute("filesList", filesList);
		
		//��Ƹ��λ��Ϣ
		request.setAttribute("zpxxList", zpxxList);
		
		//��Ƹ��Ϣ
		request.setAttribute("zphList", zphList);
		
		//У����Ƹ
		request.setAttribute("xnzpList", xnzpList);
		
		//��Ƹ��Ŀ
		List<HashMap<String, String>> zplmList = jydao.getListNotOut("select * from xg_jyweb_zplxb order by dm", new String[]{});
		request.setAttribute("zplmList",zplmList );
	}
	
	
	/**
	 * �޸�����
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
	 * ��ҵ�������б�
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
			//��λ����
			List<HashMap<String, String>> dwxzList = jydao.getWhList("dmk_dwxz2", "dwxzdm", "dwxz", "", "", "");
			request.setAttribute("dwxzList", dwxzList);
			//��ҵ����
			List<HashMap<String, String>> hyflList = jydao.getWhList("dmk_hyfl", "hyfldm", "hyfl", "", "", "");
			request.setAttribute("hyflList", hyflList);
		} else if("resume".equals(flg)) {
			//����
			List<HashMap<String, String>> mzList = jydao.getWhList("mzdmb", "mzdm", "mzmc", "", "", "");
			request.setAttribute("mzList", mzList);
			
			//������ò
			List<HashMap<String, String>> zzmmList = jydao.getWhList("dmk_zzmm", "zzmmdm", "zzmm", "", "", "");
			request.setAttribute("zzmmList", zzmmList);
		} else if("work".equals(flg)){
			// ��Ƹ��Ϣ
			List<HashMap<String, String>> xbList = dao.getSelectList("xb");
			request.setAttribute("xbList", xbList);
			
			//��������
			List<HashMap<String, String>> gzxzList = dao.getSelectList("gzxz");
			request.setAttribute("gzxzList", gzxzList);
			
			//רҵ����
			List<HashMap<String, String>> zyList = jydao.getWhList("dmk_zydmdzb", "jygl_zydm", "jygl_zymc", "", "", "");
			request.setAttribute("zyList", zyList);
			
		} else if ("zpgl".equals(flg)){
			//��Ƹ����
			List<HashMap<String, String>> zplxList = getZplxList();
			request.setAttribute("zplxList", zplxList);
		}
		
		request.setAttribute("nowTime", getNow());
		//���״̬
		List<HashMap<String, String>> shztList = dao.getSelectList("shzt");
		request.setAttribute("shztList", shztList);
		//��˽��
		List<HashMap<String, String>> shjgList = dao.getSelectList("shjg");
		request.setAttribute("shjgList", shjgList);
	}
	
	
	/**
	 * ��Ƹ����
	 * @return
	 */
	public List<HashMap<String,String>> getZplxList(){
		
		return dao.getZplxList();
	}
	
	
	/**
	 * ��ǰʱ��
	 * @return
	 */
	public  String getNow() {
		
		return dao.getNow();
	}
	
	
	/**
	 * ��¼
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
	 * ��ȡ��ҵ��title
	 * @param path
	 * @return
	 */
	public String getTitle(String path) {
		
		return dao.getTitle(path);
	}
	
	
	/**
	 * ������Ϣ
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
	 * �ļ����ش�����1
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
	 * ��ȡָ����λ����Ƹ��Ϣ
	 * @param dwmc
	 * @return
	 */
	protected List<HashMap<String,String>> getZpxx(String pkValue){
		return dao.getZpxx(pkValue);
	}

	
	/**
	 * ��ȡ������ȫ����
	 * @param tableName
	 * @return
	 */
	public String[] getColumn(String tableName) {
		
		return dao.getColumn(tableName);
	}
	
	
	/**
	 * ��ҵ������disabled ��class
	 * @param request
	 * @param flg
	 */
	protected void setCustomAudiColumn(HttpServletRequest request, String flg){
		StringBuilder sb = new StringBuilder();
		
		if ("shzt".equals(flg)) {
			sb.append(" (case when shzt='ͨ��' or shzt='��ͨ��' then 'disabled' when shzt='�˻�' then 'th' else '' end) disabled, ");
		} else	if ("xxsh".equals(flg)) {
			sb.append(" (case when xxsh='ͨ��' or xxsh='��ͨ��' then 'disabled' when xxsh='�˻�' then 'th' else '' end) disabled, ");
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}

	
	/**
	 * �����û����ͻ�ȡ�����˵�
	 * @param userType
	 * @return
	 */
	public List<HashMap<String,String>> getMenus(String userType){
		
		return dao.getMenus(userType);
	}
	
	
	/**
	 * �û�ע��
	 * @param model
	 * @return
	 */
	public String insertUser(Model model){
		
		String msg = "ע��ʧ�ܣ�";
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
				msg = "ע��ɹ���";
			}
			
		}catch (Exception e) {
			if(e.getMessage().contains("ORA-00001")){//Υ��ΨһԼ��
				msg = "�û�����λ���Ѵ��ڣ�";
			} else{
				e.printStackTrace();
			}
			
		}
		
		return msg;
	}
	

	/**
	 * �����û�����ù�˾��Ϣ
	 */
	public Map<String, String> getCompanyInfo(String pk, String pkValue, String[] colList){
		String tableName = "view_jygl_dwxxb";
		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	
	}
	
	
	/**
	 * ѧ����λ�ղء������Ͷ�ݼ���
	 * @param pkValue
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean saveStuJobs(String pkValue,String xh,String type) throws Exception {
		
		if ("���".equals(type)){
			//�����λ�����¼
			if (dao.deleteStuJobs(pkValue, xh)) {
				return dao.saveStuJobs(pkValue, xh, type);
			}
		} else {
			//�ж��Ƿ�������ղع��˸�λ
			List<HashMap<String,String>> list = dao.getStuJobs(xh, type, pkValue);
			
			if (list.size() == 0){
				boolean flg = dao.saveStuJobs(pkValue, xh, type);
				
				//�Ͼ���ʦ�ĸ��Ի����󣨼�һ����˼�¼��
				if (flg && Globals.XXDM_NJJS.equals(Base.xxdm)  && "����".equals(type)){
					NjjsJywebService njjsJywebService = new NjjsJywebService();
					return njjsJywebService.saveXsbmsh(xh, pkValue);
				}
				return flg;
			} 
		}
		
		return false;
	}
	
	
	/**
	 * �ж�ѧ���Ƿ��м���
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
	 * ��ȡ�꼶��רҵ���༶�б�
	 * @param request
	 */
	public void setNjXyZyBjList(HttpServletRequest request) {
		// ��request�����꼶ѧԺרҵ�༶List�ķ���
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
	 * ������������
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public boolean saveYqlj(String[] values) throws Exception {
		
		return dao.saveYqlj(values);
	}
	
	
	/**
	 *�������ӵ���ɾ�� 
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
	 * ɾ���ļ�
	 * @param pkValues
	 * @return
	 */
	public boolean delFile(String[] pkValues,String flg) {
		
		boolean result = true;
		String[] files = new String[] {};
		
		if (null != pkValues && pkValues.length>0) {
			try {
				//��ȡ�ļ�����·��
				files = "file".equals(flg) ? dao.getFiles(pkValues) : dao.getTps(pkValues);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0 ; i< files.length;i++) {
			if (!Base.isNull(files[i])) {
				File file = new File(files[i]);
				if(null != file && file.exists()) {
					//ɾ���ļ�
					result = file.delete();
				} else {
					result = false;
				}	
			}
		}
		
		return result;
		
	}
	
	
	/**
	 * �����ļ���¼
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
	
	
	 /** �ļ����������1
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
	 * ��Ƹ�����������1 
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
	 * ͼƬ�ϴ�
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
		//����ļ�����Ϊ�ս����ϴ��ļ�
		if (null != file && !Base.isNull(file.getFileName())) {
			//ȡ����·��
//			String dirPath = "";
			File f = new File(dirPath);
		    if (!f.exists()) {
			   f.mkdir();
		    }//�����ļ���
			String guid = StringUtils.getGuid();//��ȡ���ID
			int size = file.getFileSize();//�ļ���С
			if (size > 1024*1024) {
				//����1M���ϴ�������message
				request.setAttribute("message", "�ϴ�ͼƬ��С���ܳ���1MB!");
			} else {
				String fName = guid + file.getFileName();//�����ļ���
				myForm.setFileName(fName);
				InputStream in = file.getInputStream();//��ȡ������
				path = dirPath + "/" + fName;//�����ļ�����·�����ļ���
				OutputStream out = new FileOutputStream(path);//���������
				int data = 0;
				byte[] buffer = new byte[size];
				while ((data = in.read(buffer, 0, size)) != -1) {
					//д�ļ������ش���
					out.write(buffer, 0, data);
				}
				out.close();
				in.close();
				
			}
		}
		return path;
	}
	
	
	/**
	 * �ļ��ϴ�
	 * @param myForm
	 * @param request
	 * @throws IOException
	 */
	public void fileUpload(JyglForm myForm,HttpServletRequest request) throws IOException {
		FormFile file = myForm.getFile();
		
		if (null != file && !Base.isNull(file.getFileName())) {
			//����ļ�����Ϊ�ս����ϴ��ļ�
			if (null != file && !Base.isNull(file.getFileName())) {
				//ȡ����·��
				String dirPath = request.getRealPath("")+".upload";
				
				File f = new File(dirPath);
			    if (!f.exists()) {
				   f.mkdir();
			    }//�����ļ���
			    
			    String filePath = dirPath+"/jyweb";
				
				f = new File(filePath);
			    if (!f.exists()) {
				   f.mkdir();
			    }	
			    
				String guid = StringUtils.getGuid();//��ȡ���ID
				int size = file.getFileSize();//�ļ���С
				if (size > 10485760) {
					//����10M���ϴ�������message
					request.setAttribute("message", "�ϴ��ļ���С���ܳ���10MB!");
				} else {
					String fName = guid + file.getFileName();//�����ļ���
					InputStream in = file.getInputStream();//��ȡ������
					String path = dirPath + "/" + fName;//�����ļ�����·�����ļ���
					OutputStream out = new FileOutputStream(path);//���������
					int data = 0;
					byte[] buffer = new byte[size];
					while ((data = in.read(buffer, 0, size)) != -1) {
						//д�ļ������ش���
						out.write(buffer, 0, data);
					}
					out.close();
					in.close();
					//����formֵ
					myForm.setWjm(file.getFileName());
					myForm.setWjlj(path);
					myForm.setGuid(guid);
					request.setAttribute("message", saveWjsc(myForm) ? "�����ɹ�!" : "����ʧ��!");
				}
			}
		}
	}
	
	
	/**
	 * ����ظ���Ϣ
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
	 * �������ͳ������
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
	 * �ж��û��Ƿ����s
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
	 * ��λ���ͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getDwtj(JyglForm form){
		
		return dao.getDwtj(form);
	}
	
	
	/**
	 * ��Ƹ��Ϣ���ͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getZpxxTj(JyglForm form){
		
		return dao.getZpxxTj(form);
	}
	
	
	/**
	 * ѧ�����ͳ��-��ѧԺ
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByXy(JyglForm form) {

		return dao.getStuTjByXy(form);
	}

	
	/**
	 * ѧ�����ͳ��-��רҵ
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByZy(JyglForm form) {

		return dao.getStuTjByZy(form);
	}

	
	/**
	 * ѧ�����ͳ��-���༶
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByBj(JyglForm form) {

		return dao.getStuTjByBj(form);
	}
	
	
	/**
	 * ѧ�����ͳ��-��ѧ��
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByStu(JyglForm form) {

		return dao.getStuTjByStu(form);
	}

	
	/**
	 * ���ű���
	 * @param form
	 * @return
	 */
	public boolean saveNews(JyglForm form) {
		
		return dao.saveNews(form);
	}
	
	
	/**
	 * �����޸�
	 * @param form
	 * @return
	 */
	public boolean updateNews(JyglForm form,String pkVlaue) {
		
		return dao.updateNews(form,pkVlaue);
	}
	
	
	
	/**
	 * ��λ�б���λ��Ϣ��ʹ�ã�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getDwList(JyglForm model){
		
		return dao.getDwList(model);
	}
	
	
	
	/**
	 * ��λ�б���λ��Ϣ��ʹ�ã�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getGwList(JyglForm model){
		
		return dao.getGwList(model);
	}
	
	
	
	/**
	 * ����������Ƹ������Ϣ
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
	 * ��ȡ������Ƹ��λ�б� 
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getZtzpGwList(String pkValue){
		
		return dao.getZtzpGwList(pkValue);
	}
	
	
	
	/**
	 * ��ȡ������Ƹ�е�λ����
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
	 * ��ȡ������Ƹ�и�λ����
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
	 * ������Ƹ���������1
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
	 * ɾ��������Ƹ��������
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
	 * ���ݵ�λ���ƻ�ȡ�û���
	 * @param dwmc
	 * @return
	 */
	public String getYhm(String dwmc) {
		
		return dao.getYhm(dwmc);
	}
	
	
	/**
	 * �޸ĵ�λ�ظ����Ƿ�ɾ��״̬
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
	 * ��ȡָ�����ָ����
	 * @param realTable
	 * @param colList
	 * @return
	 */
	public String[] getColumn(String realTable,String[] colList){
		
		return dao.getColumnNameCN(colList, realTable);
	}



	/**
	 * ���Ľ��ܲ�ѯ
	 * @param lx
	 * @return
	 */
	public String getZxjs(String lx){
		
		return dao.getZxjs(lx);
	}


	/**
	 * ͨ�ñ���
	 */
	public boolean saveJyweb(SaveForm form, JyglForm model,
			HttpServletRequest request) throws Exception {
		
		return dao.submitData(form, model, request);
	}

}

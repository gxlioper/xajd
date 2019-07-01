package xgxt.comm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.szdw.nbzy.BjtsxmDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicDAO;
import com.zfsoft.utils.StringUtil;

public class CommService {

	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private long maxszie = Long.valueOf(resource.getString("filesys.maxsize"));
	
	CommDAO dao = new CommDAO();

	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CommForm myForm, HttpServletRequest request, String manu)
			throws Exception {

		// =====================ͨ��=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// �Զ���(Ŀǰ�����Ŵ���)

	}

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommList commList = new CommList();
		commList.setList(model, rForm, request);
	}
	
	/**
	 * �����꼶ѧԺרҵ�༶�б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setAllList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommList commList = new CommList();
		commList.setAllList(model, rForm, request);
	}

	/**
	 * ����Request ��ֵ
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String) session.getAttribute("userName");
		String doType = request.getParameter("doType");
		String writeAble = request.getParameter("writeAble");
		String title = rForm.getTitle();

		// ��ͼ��
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// ����
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// ģ��·��
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// �û�����
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// �û���
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// �û����ڲ���
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(path)) {
			request.setAttribute("userDep", userDep);
		}

		// ��������
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}

		// ��дȨ���
//		if (Base.isNull(writeAble)) {
			FormModleCommon.commonRequestSet(request);
//			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
//			writeAble = message != null && message.length >= 1 ? message[0]
//					: "";
//			if (Base.isNull(title)) {
//				title = message != null && message.length >= 2 ? message[1]
//						: "";
//			}
//		}

		// ģ�����
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// ����
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// ģ�K���
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// ��ͷ��Ϣ
		List<HashMap<String, String>> topTr = rForm.getTopTr();
		if (topTr != null && topTr.size() > 0) {
			request.setAttribute("topTr", topTr);
		}

		// ��ϸ��Ϣ
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// ��ϸ�б���Ϣ
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
		}

		// ��ϸ�б���Ϣ
		ArrayList<String[]> rsArrList = rForm.getRsArrList();
		if (rsArrList != null && rsArrList.size() > 0) {
			request.setAttribute("rsArrList", rsArrList);
			request.setAttribute("rsNum", rsArrList.size());
		}

		// ��ʾ��Ϣ
		String message = rForm.getMessage();
		if (!Base.isNull(message)) {
			request.setAttribute("message", message);
		}

		// �����ֶ�
		String[] qtzd = rForm.getQtzd();
		// �����ֶ�ֵ
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}

	/**
	 * ����Request ��ֵ
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, User user,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		String userType = user.getUserType();
		String userDep = user.getUserDep();
		String gyglyQx = user.getGyglyQx();
		String userName = user.getUserName();
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		String writeAble = (String)request.getAttribute("writeAble");
		if(Base.isNull(writeAble)){
			 writeAble = request.getParameter("writeAble");
		}
		String title = rForm.getTitle();

		// ��ͼ��
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// ����
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// ģ��·��
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// �û�����
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// ѧ��
		if ("stu".equalsIgnoreCase(userType)) {

			String sql = "select xm from view_xsjbxx where xh = ? ";
			request.setAttribute("xh", userName);
			request.setAttribute("xm", dao.getOneRs(sql,
					new String[] { userName }, "xm"));
		}

		// �û���
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// �û����ڲ���
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(userDep)) {
			request.setAttribute("userDep", userDep);
		}

		// �û����
		userStatus = Base.isNull(userStatus) ? rForm.getUserStatus()
				: userStatus;
		if (!Base.isNull(userStatus)) {
			request.setAttribute("userStatus", userStatus);
		}

		// ��Ԣ����ԱȨ��
		if (!Base.isNull(gyglyQx)) {
			request.setAttribute("gyglyQx", gyglyQx);
		}
		
		// ��������
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}
		String[] message = FormModleCommon.getWriteAbleAndTitle(request);
		// ��дȨ���
		if (Base.isNull(writeAble)) {
			writeAble = message != null && message.length >= 1 ? message[0]: "";
		}
		if (Base.isNull(title)) {
			title = message != null && message.length >= 2 ? message[1]: "";
		}
		request.setAttribute("writeAble", writeAble);

		// ģ�����
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// ����
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// ģ�K���
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// ��ͷ��Ϣ
		List<HashMap<String, String>> topTr = rForm.getTopTr();
		if (topTr != null && topTr.size() > 0) {
			request.setAttribute("topTr", topTr);
		}

		// ��ϸ��Ϣ
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// ��ϸ�б���Ϣ
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
			request.setAttribute("rsNum", rsList.size());
		}

		// ��ϸ�б���Ϣ
		ArrayList<String[]> rsArrList = rForm.getRsArrList();
		if (rsArrList != null && rsArrList.size() > 0) {
			request.setAttribute("rsArrList", rsArrList);
			request.setAttribute("rsNum", rsArrList.size());
		}

		// ��ʾ��Ϣ
		String tsmessage = rForm.getMessage();
		if (!Base.isNull(tsmessage)) {
			request.setAttribute("message", tsmessage);
		}

		// �Ƿ��ѯ
		String search = rForm.getSearch();
		if (!Base.isNull(search)) {
			request.setAttribute("search", search);
		}

		// ��ѯ����
		String searchType = rForm.getSearchType();
		if (!Base.isNull(searchType)) {
			request.setAttribute("searchType", searchType);
		}
		
		// ================��ҳ==================
		Pages pages = rForm.getPages();
		//request.setAttribute("pageSize", pages.getPageSize());
		request.setAttribute("pObj", pages);
		// ================��ҳ end==================

		// ===============ͨ������=================
		CommSetting commSetting = rForm.getCommSetting();

		// ���������
		String rsName = commSetting.getRsName();
		if (!Base.isNull(rsName)) {
			request.setAttribute("rsName", rsName);
		}

		// �Ƿ���Ҫcheckbox
		String isCheckBox = commSetting.getIsCheckBox();
		if (!Base.isNull(isCheckBox)) {
			request.setAttribute("isCheckBox", isCheckBox);
		}

		// ��ʾ����ʼ��
		String startNum = commSetting.getStartNum();
		if (!Base.isNull(startNum)) {
			request.setAttribute("startNum", startNum);
		}

		// ��ʾ������
		String showNum = commSetting.getShowNum();
		if (!Base.isNull(showNum)) {
			request.setAttribute("showNum", showNum);
		}

		String[] colList = rForm.getColList();
		if (!Base.isNull(rsName)) {
			if ("rsArrList".equalsIgnoreCase(rsName)) {
				// �Ƿ�Ϊ��
				if (rsArrList != null && rsArrList.size() > 0) {
					String hadRs = "yes";
					request.setAttribute("hadRs", hadRs);
				} else {
					rsArrList = setDefaultLIst(colList, pages);
					String hadRs = "no";
					request.setAttribute("hadRs", hadRs);
				}
			} else if ("rsList".equalsIgnoreCase(rsName)) {
				// �Ƿ�Ϊ��
				if (rsList != null && rsList.size() > 0) {
					String hadRs = "yes";
					request.setAttribute("hadRs", hadRs);
				} else {
					rsList = setDefaultListForMap(colList, pages);
					String hadRs = "no";
					request.setAttribute("hadRs", hadRs);
				}
			}
		}
		// ===============ͨ������ end =================

		// �����ֶ�
		String[] qtzd = rForm.getQtzd();
		// �����ֶ�ֵ
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}

	/**
	 * �ж��Ƿ�ѧԺ
	 * 
	 * @param user
	 * @param request
	 * @author luojw
	 */
	public Boolean isXy(User user) {

		String userType = user.getUserType();
		boolean fdyQx = Boolean.parseBoolean(user.getFdyQx());
		boolean bzrQx = Boolean.parseBoolean(user.getBzrQx());
		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// ѧԺ�û�
			isXy = true;
		}

		return isXy;
	}

	/**
	 * ���������Ϣ��������
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveInfoToDb(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		return dao.submitData(form, model, request);
	}

	/**
	 * ���������Ϣ��������
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param arrzd(�����ֶ�)
	 * @param onezd(��һ�ֶ�)
	 * @param notnull(�ǿ��ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveInfoToDb(SaveForm form, Object model, User user)
			throws Exception {
		return dao.saveData(form, model, user);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean updateInfoInDb(SaveForm form, Object model, User user)
			throws Exception {
		return dao.updateData(form, model, user);
	}

	/**
	 * ����ɾ��DB�е�����
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delInfoInDb(SaveForm form, Object model, User user)
			throws Exception {
		return dao.delDate(form, model, user);
	}

	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql) throws Exception {
		return dao.saveArrDate(sql);
	}

	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String sql, List<String[]> params,
			String tableName, User user) throws Exception {
		return dao.saveArrDate(sql, params, tableName, user);
	}
	
	/**
	 * ���ݲ�ѯ��HashMap��
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getRsInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getRsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ���ݲ�ѯ��ArrayList<String[]>��
	 * 
	 * @param tableName(����)
	 * @param query(����)
	 * @param inPutList(����ֵ)
	 * @param colList(���ֵ)
	 * @param sql(���Ի�sql)
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getRsArrList(String tableName, String query,
			String[] inPutList, String[] colList, String sql, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getRsArrList(tableName, query, inPutList, colList, sql,
				model);
	}

	/**
	 * ���ݲ�ѯ��List<HashMap>��
	 * 
	 * @param tableName(����)
	 * @param query(����)
	 * @param inPutList(����ֵ)
	 * @param colList(���ֵ)
	 * @param sql(���Ի�sql)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getRsList(String tableName,
			String query, String[] inPutList, String[] colList, String sql) {
		return dao.getRsList(tableName, query, inPutList, colList, sql);
	}

	//===================��ṹ��� begin===============================
	/**
	 * ��ѯ����ֶ���Ϣ
	 * 
	 * @param tableName
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectTableInfo(String tableName) {
		BasicDAO dao = new BasicDAO();
		return dao.selectTableInfo(tableName);
	}
	
	/**
	 * ��ѯָ�����Comment
	 * 
	 * @param tableName
	 * @return List<HashMap<String, String>>
	 */
	public String[] getTableComment(String tableName, String[] zd) {
		List<HashMap<String, String>> list = dao.getTableInfo(tableName);
		ArrayList<String> comment = new ArrayList<String>();

		if (zd != null && zd.length > 0) {
			for (int i = 0; i < zd.length; i++) {
				
				boolean flag = true;
				
				for (int j = 0; j < list.size(); j++) {
					
					HashMap<String, String> map = list.get(j);
					
					if (zd[i].equalsIgnoreCase(map.get("zdm"))) {
						
						flag = false;
						
						if (Base.isNull(map.get("zwm"))) {
							comment.add("");
							break;
						} else {
							comment.add(map.get("zwm"));
							break;
						}
					}
				}
				
				if(flag){
					comment.add("");
				}
			}
		}

		return comment.toArray(new String[] {});
	}
	//===================��ṹ��� end===============================
	
	/**
	 * ��üٷ�ҳ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getResultList(
			List<HashMap<String, String>> list, Pages pages) {

		// ��ҳ
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {

			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}

	/**
	 * ��üٷ�ҳ
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			Pages pages) {

		// ��ҳ
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}

	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {	
		return dao.getNowTime(lx);
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue) {
		DAO dao = DAO.getInstance();
		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}

	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @pararm isnull���Ƿ���Ҫ��ʾ��ѡ��
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName, String dm,
			String mc, String msg, String pk, String pkValue, boolean isnull) {
		DAO dao = DAO.getInstance();
		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue, isnull);
	}
	
	/**
	 * ���ָ������ֶ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {
		return dao.getTableZd(tableName);
	}

	/**
	 * �ļ��ϴ�
	 * 
	 * @param request
	 * @param myForm
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String upLoadFile(HttpServletRequest request, FormFile file,
			String mklx) throws FileNotFoundException, IOException {
		return upLoadFile(request, file, mklx, maxszie);
	}
	public String upLoadFile(HttpServletRequest request, FormFile file,
			String mklx,long maxSize) throws FileNotFoundException, IOException {
		String filelx = (String)request.getAttribute("filelx");

		String filePath = "";
		String fName = "";
		if (file != null) {
			String dir = "/upload/" + mklx;
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			int size = file.getFileSize();
			if (filelx.equalsIgnoreCase("003"))
			{
				maxSize = 520971520;
			}
			if (size < maxSize) {

				fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;//"e:" + dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			} else {
				String message = "�ļ������ϴ�ʧ��";
				request.setAttribute("message", message);
			}
		}
		return filePath;
	}
	/**
	 * @describe ɾ�����ϴ��ļ�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean delUpLoadFile(String[] filepath) {

		boolean flag = true;

		if (filepath != null && filepath.length > 0) {

			for (int i = 0; i < filepath.length; i++) {

				File file = new File(filepath[i]);

				if (file.exists()) {

					flag = file.delete();

					if (!flag) {

						return flag;

					}
				}
			}
		}

		return flag;
	}

	/**
	 * ���EXCEL
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expToExcel(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ����ͷ
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		// �������
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ���EXCEL
	 * 
	 * @param title
	 *            ��ʾ��sheet����
	 * @param topTr
	 *            excel�ĵ�һ������������
	 * @param list
	 *            ��������
	 * @param os
	 *            ��
	 * @param hbzt
	 *            �ϲ�״̬��hb���ϲ�����Ҫ�ϲ����У�bhb�����ϲ����У�������Ҫ�ϲ�������Ϊ�գ����ϲ����е�Ԫ��
	 * @param hbwz
	 *            �ϲ�λ��
	 * @author luo
	 * 
	 * @throws Exception
	 */
	public void expToExcel(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os, String hbzt, int[] hbwz)
			throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.TOP, true, Border.ALL);

		// ����ͷ
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		// �������
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}

			if (hbwz != null && hbwz.length > 0) {
				// �ϲ���Ԫ��
				if ("hb".equalsIgnoreCase(hbzt)) {// �ϲ���¼���λ��
					hbdyg(list, hbwz, ws);
				} else if ("bhb".equalsIgnoreCase(hbzt)) {// ���ϲ���¼���λ�ã�������Ҫ�ϲ���
					// ��ͷ�ļ�
					if (topTr != null && topTr.size() > 0
							&& topTr.size() > hbwz.length) {

						int[] newWz = new int[topTr.size() - hbwz.length];
						int n = 0;
						for (int i = 0; i < topTr.size(); i++) {

							boolean flag = true;

							for (int j = 0; j < hbwz.length; j++) {
								if (i == hbwz[j]) {
									flag = false;
								}
							}

							if (flag) {
								newWz[n] = i;
								n++;
							}
						}
						hbdyg(list, newWz, ws);
					}
				} else {// ������

				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * �ϲ���Ԫ�� list ��ѯ��� hbwz ��Ҫ�ϲ����еı�� int[] hbwz={0,2,4}(�ϲ�0,2,4������ͬ������)
	 * 
	 * @author qlj
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws Exception
	 */
	public void hbdyg(List<String[]> list, int[] hbwz, WritableSheet ws)
			throws RowsExceededException, WriteException {
		if (hbwz != null && hbwz.length > 0) {
			int len = 1;
			for (int i = 0; i < hbwz.length; i++) {
				int wz = hbwz[i];
				int hb[] = getLines(list, len, wz);

				for (int j = 0; j < hb.length; j++) {
					if (hb.length == 1) {
						ws.mergeCells(wz, hb[j], wz, 2 + list.size() - 1);
					} else if (j == hb.length - 1) {
						ws.mergeCells(wz, hb[j], wz, hb[j]);
					} else {
						ws.mergeCells(wz, hb[j], wz, hb[j + 1] - 1);
					}
				}
			}
		}
	}

	/**
	 * �ϲ���Ԫ�� list ��ѯ��� hbwz ��Ҫ�ϲ����еı�� int[] hbwz={0,2,4}(�ϲ�0,2,4������ͬ������) qsh
	 * ��Ҫ�ӵڼ��п�ʼ�ϲ���Ԫ��
	 * 
	 * @author qlj
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws Exception
	 */
	public void hbdyg(List<String[]> list, int[] hbwz, int qsh, WritableSheet ws)
			throws RowsExceededException, WriteException {
		if (hbwz != null && hbwz.length > 0 && list!=null && list.size()>0) {
			int len = 1;
			for (int i = 0; i < hbwz.length; i++) {
				int wz = hbwz[i];
				int hb[] = getLines(list, len, wz);

				for (int j = 0; j < hb.length; j++) {
					if (hb.length == 1) {
						ws.mergeCells(wz, hb[j], wz, qsh + list.size() - 1);
					} else if (j == hb.length - 1) {
						ws.mergeCells(wz, hb[j], wz, hb[j]);
					} else {
						ws.mergeCells(wz, qsh+hb[j]-2, wz,qsh+hb[j + 1] -3);
					}
				}
			}
		}
	}

	/**
	 * ��úϲ���Ϣ
	 * 
	 * @author qlj
	 * @throws Exception
	 */
	public int[] getLines(List<String[]> list, int len, int wz) {
		int[] length = new int[list.size() + 1];
		int m = len;
		int n = 0;
		length[n] = m;
		n++;
		if (list.size() > 1) {
			for (int i = 0; i < list.size() - 1; i++) {
				String[] wzsi = list.get(i);//
				String[] wzsj = list.get(i + 1);
				if (i == list.size() - 1) {
					length[n] = ++m;
				} else {
					if (wzsi[wz].equals(wzsj[wz])) {
						length[n] = ++m;
					} else {
						length[n] = ++m;
						n++;
					}
				}
			}
		}

		int[] getLen = new int[n + 1];
		for (int i = 0; i < n; i++) {
			getLen[i] = length[i];
		}
		getLen[n] = list.size() + len;
		return getLen;
	}

	/**
	 * ����ܴ��б�
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getZcList(String tableName,
			String zczd) {

		DAO dao = DAO.getInstance();
		String zzc = dao.getOneValue(tableName, zczd, "rownum", "1");// ���ܴ�

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (!Base.isNull(zzc)) {
			for (int i = 1; i <= Integer.parseInt(zzc); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", String.valueOf(i));
				map.put("mc", "��" + String.valueOf(i) + "��");
				list.add(map);
			}
		}

		return list;
	}

	/**
	 * ���쵼�������ͷ
	 * 
	 * @author luoj
	 */
	public List<HashMap<String, String>> getTopList(String[] topName) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		if (topName != null && topName.length > 0) {

			for (int i = 0; i < topName.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("cn", topName[i]);
				topTr.add(map);
			}
		}
		return topTr;
	}

	/**
	 * �������ֵ
	 * 
	 * @author luoj
	 */
	public String[] getOutValue(String[] outValue) {

		String[] outZd = null;

		if (outValue != null && outValue.length > 0) {

			int n = 0;

			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					n++;
				}
			}

			outZd = new String[n];

			int m = 0;
			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					outZd[m] = outValue[i];
					m++;
				}
			}
		}
		return outZd;
	}

	/**
	 * �������ֵ
	 * 
	 * @author luoj
	 */
	public ArrayList<String[]> setDefaultLIst(String[] colList, Pages pages) {

		int size = pages.getPageSize();

		String[] arr = new String[colList.length];

		for (int i = 0; i < colList.length; i++) {
			arr[i] = "";
		}

		ArrayList<String[]> list = new ArrayList<String[]>();

		for (int i = 0; i < size; i++) {
			list.add(arr);
		}

		return list;
	}

	/**
	 * �������ֵ
	 * 
	 * @author luoj
	 */
	public  List<HashMap<String, String>>  setDefaultListForMap(String[] colList, Pages pages) {

		int size = pages.getPageSize();



		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();

		for (int i = 0; i < size; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			list.add(map);
		}

		return list;
	}
	
	/**
	 * ��ȡ�û������Ϣ
	 * 
	 * @param request
	 * @return User
	 */
	public User getUser(HttpServletRequest request) {

		User user = new User();
		HttpSession session = request.getSession();

		user.setUserName(session.getAttribute("userName") != null ? session.getAttribute("userName").toString():"");
		user.setUserType(session.getAttribute("userType") != null ? session.getAttribute("userType").toString():"");
		user.setIsFdy(session.getAttribute("isFdy") != null ? session.getAttribute("isFdy").toString():"");
		user.setUserDep(session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString() :"");
		user.setUserMac(session.getAttribute("userMac") != null ? session.getAttribute("userMac").toString():"");

		// �û�����
		String userType = session.getAttribute("userType") != null ? session.getAttribute("userType").toString() :"";
		// ����ԱȨ��
		String fdyQx = session.getAttribute("fdyQx") != null ? session.getAttribute("fdyQx").toString():"";
		// ������Ȩ��
		String bzrQx = session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString():"";
		// �û����
		String userStatus = "";

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

		String gyglyQx = session.getAttribute("gyglyQx") == null ? 
									dao.getGyglyQx(user.getUserName()) : (String)session.getAttribute("gyglyQx");
		
		user.setFdyQx(fdyQx);
		user.setBzrQx(bzrQx);
		user.setGyglyQx(gyglyQx);
		user.setUserStatus(userStatus);

		user.setHost(request.getRemoteHost());
		user.setIp(request.getRemoteAddr());

		String userRoles = session.getAttribute("userRoles") != null ? session.getAttribute("userRoles").toString() : "";
		if(!Base.isNull(userRoles)){
			user.setUserRoles(userRoles.split("!!"));
		}
		
		return user;
	}

	/**
	 * ���������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public void createRs(SearchRsModel rsModel, Pages pages,
			HttpServletResponse response) throws IOException {
		
		// ��ѡ������
		String checkBox = rsModel.getCheckBox();
		// ��ѡ������(�����)
		String checkBoxRs = rsModel.getCheckBoxRs();
		// ����
		List<HashMap<String, String>> topTr = rsModel.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = rsModel.getRsArrList();
		// �������
		String spHtml = rsModel.getSpHtml();
		
		//�Ƿ����ӡ���ѯ�������
		String showTitle  = rsModel.getShowTitle();
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		
		if("yes".equalsIgnoreCase(showTitle)){
			html.append("<h3 class=\"datetitle_01\">");
			html.append("<span id=\"span_note\" width=\"100%\">");
			html.append("��ѯ���&nbsp;&nbsp;");		
			html.append("</span>");
			html.append("</h3>");
		}
		
		html.append("<div class=\"con_overlfow\" style=\"width:100%;overflow-x:auto;overflow-y:hidden;\">");
		html.append("<span class=\"formbox\">");
		html.append("<table class=\"dateline\" width=\"100%\" id=\"table_rs\" style=\"\">");
		// =========================����===========================
		html.append("<thead>");
		html.append("<tr>");	

		if ("yes".equalsIgnoreCase(checkBox)) {
			html.append("<td width=\"5px\">");
			html.append("<input type=\"checkbox\" id=\"selall\" ");
			html.append("name=\"selall\" onclick=\"selAll()\" />");
			html.append("</td>");
		}
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				
				html.append("<td  nowrap=\"nowrap\" ");
//				if(i == 0){
//					html.append("<td width=\"5px\" nowrap=\"nowrap\" ");
//				}else{
//					html.append("<td style=\"width:"+(100/(topTr.size()-1))+"%\"  nowrap=\"nowrap\" ");
//				}			
				
				//����
				String arrange = rsModel.getArrange();
				if("yes".equalsIgnoreCase(arrange)){
					html.append("id=\""+topTr.get(i).get("en")+"\"");
					html.append("onclick=\"arrangeRs(this)\"");
				}
				html.append(">");
				html.append(topTr.get(i).get("cn"));
				html.append("</td>");
			}
		}

		html.append("</tr>");
		html.append("</thead>");
		// =========================���� end===========================
		
		// =========================����� =========================
		html.append("<tbody>");
		if (!Base.isNull(spHtml)) {
			html.append(spHtml);
		} else {
			if (rsArrList != null && rsArrList.size() > 0) {
				for (int i = 0; i < rsArrList.size(); i++) {
					String[] rs = rsArrList.get(i);
					html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

					if (Base.isNull(checkBoxRs)) {
						html.append("<td align=\"left\" width=\"5px\">");
						html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
						html.append("value=\"" + rs[0] + "\"/>");
						html.append("</td>");
					}

					for (int j = 1; j < rs.length; j++) {
						html.append("<td align=\"left\" style=\"width:"+(100/(topTr.size()-1))+"%\"  nowrap=\"nowrap\" >");
						html.append(rs[j]);
						html.append("</td>");
					}
					html.append("</tr>");
				}
			}
		}
		
		// ����
		int rows = 0;

		if (rsArrList != null && rsArrList.size() > 0) {
			rows = rsArrList.size();
		} else if (!Base.isNull(rsModel.getRows())) {
			rows= Integer.parseInt(rsModel.getRows());
		}
		
		int spaceRow = pages.getPageSize();
		
		spaceRow = spaceRow - rows;
		
		String noSpace = rsModel.getNoSpace();
		
		// ������
		if (spaceRow != 0 && !"no".equalsIgnoreCase(noSpace)) {

			for (int i = 0; i < spaceRow; i++) {
				html.append("<tr>");

				if ("yes".equalsIgnoreCase(checkBox)) {
					html.append("<td width=\"5px\">");
//					html.append("<input type=\"checkbox\" id=\"selall\" ");
//					html.append("name=\"selall\" onclick=\"selAll()\" />");
					html.append("</td>");
				}

				if (topTr != null && topTr.size() > 0) {

					// IE�汾
					String ie = rsModel.getIe();

					for (int j = 0; j < topTr.size(); j++) {

						html.append("<td width=\"5px\"");
						// IE8
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}

						html.append(">");
						html.append("&nbsp;");
						html.append("</td>");
					}
				}

				html.append("</tr>");
			}
		}
		
		html.append("</tbody>");
		// =========================����� end=========================
							
		html.append("</table>");
		html.append("</span>");
		html.append("</div>");
		
		html.append("<input type=\"hidden\" id=\"ajax_max_record\" value=\""+pages.getMaxRecord()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_page\" value=\""+pages.getMaxPage()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_max_current\" value=\""+pages.getCurrentPage()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_size\" value=\""+pages.getPageSize()+"\"/>");
		html.append("<input type=\"hidden\" id=\"ajax_page_maxpage\" value=\""+pages.getMaxPage()+"\"/>");
		
		response.getWriter().print(html.toString());
	}
	
	
	
	/**
	 * ��ȡ�߼���ѯModel�ع���ԭ����setSearchModel��
	 * @param request
	 * @return
	 */
	public SearchModel getSearchModel(HttpServletRequest request){
		RequestForm rForm = new RequestForm();
		String path = request.getParameter("path");
		
		try {
			rForm.setPath(path);
			return setSearchModel(rForm,request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	
	/**
	 * ���ø߼���ѯModel
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public SearchModel setSearchModel(RequestForm rForm,
			HttpServletRequest request) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, UnsupportedEncodingException {

		SearchModel searchModel = new SearchModel();

		// ����·��
		String path = rForm.getPath();
		searchModel.setPath(path);
		//������ѯ
		String plcx_xh = request.getParameter("plcx_xh");
		String plcx_xm = request.getParameter("plcx_xm");
		if(!Base.isNull(plcx_xh)){ 
			searchModel.setPlcx_xh(plcx_xh);
		}
		if(!Base.isNull(plcx_xm)){ 
			searchModel.setPlcx_xm(plcx_xm);
		}
		// ģ����ѯ
		String input_mhcx = request.getParameter("input_mhcx");
		String mhcx_lx = request.getParameter("mhcx_lx");
		if(!Base.isNull(input_mhcx)){ 
			input_mhcx = unicode2Gbk(input_mhcx);
		}
		
		searchModel.setInput_mhcx(input_mhcx);
		searchModel.setMhcx_lx(mhcx_lx);
		
		// ����
		String arrange = request.getParameter("arrange");
		String fashion = request.getParameter("fashion");
		searchModel.setArrange(arrange);
		searchModel.setFashion(fashion);
		
		
		String searchLxs = request.getParameter("searchLx");
		String searchTjs = request.getParameter("searchTj");
		String searchTjzs = request.getParameter("searchTjz");
		
		// ��������
		String[] searchLx = StringUtil.isNull(searchLxs) ? new String[]{} : searchLxs.split("!!@@!!");
		String[] searchTj = StringUtil.isNull(searchTjs) ? new String[]{} : searchTjs.split("!!@@!!");
		String[] searchTjz = StringUtil.isNull(searchTjzs) ? new String[]{} : searchTjzs.split("!!@@!!");

		if (searchTj != null && searchTj.length > 0) {

			for (int i = 0; i < searchLx.length; i++) {

				ArrayList<String> valueList = new ArrayList<String>();
				String lx = "search_tj_" + searchLx[i];

				for (int j = 0; j < searchTj.length; j++) {
					
					String tj = searchTj[j];
					String tjz = unicode2Gbk(searchTjz[j]);
					tjz=tjz.replace("!!DDBGG!!", "");
					if (lx.equalsIgnoreCase(tj.replace("searchModel.", ""))) {
						valueList.add(tjz);
					}
					
				}

				Class myClass = searchModel.getClass();

				String setMethod = "setS" + lx.substring(1, lx.length());
				String[] newValue = valueList.toArray(new String[] {});

				if (newValue != null && newValue.length > 0) {

					Method methodName = myClass.getMethod(setMethod,
							new Class[] { String[].class });

					methodName.invoke(searchModel, (Object) newValue);

				}
			}
		}

		return searchModel;
	}
	
	/**
	 * ���÷�ҳModel
	 * 
	 * @author ΰ�����
	 * 
	 */
	public Pages setPages(String defaultSize,HttpServletRequest request) {

		Pages pages = new Pages();

		// �������ʾ�ֶ�
		String editPageSize = request.getParameter("editPageSize");
		// �������ʾ�ֶ�
		String pagesize = request.getParameter("pagesize");

		if(Base.isNull(defaultSize)){
			defaultSize = "11";
		}
		
		if (!"yes".equalsIgnoreCase(editPageSize)) {
			pages.setPageSize(Integer.parseInt(defaultSize));
			request.setAttribute("autoPageSize", defaultSize);
		} else {
			pages.setPageSize(Integer.parseInt(pagesize));
			request.setAttribute("autoPageSize", pagesize);
		}

		// ��ǰҳ
		String currentPage = request.getParameter("currentPage");
		pages.setCurrentPage(Integer.parseInt(currentPage));

		return pages;
	}
	
	// �����ַ�
	private String[] lmzf = new String[] { "%20", "%21", "%25", "%5E", "%26",
			"%28", "%29", "%7B", "%7D", "%5B", "%5D", "%3C", "%3E", "%3F",
			"%0A", "%3B", "%3D","%2C","%27","%5C","%23","%22","%7E","%24","%7C","%0D","%3A"};

	// ת���ַ�
	private String[] zmzf = new String[] { " ", "!", "%", "^", "&", "(", ")",
			"{", "}", "[", "]", "<", ">", "?", " ", ";", "=","��","��","��","#","��","~","��","|","<br/>",":"};

	/**
	 * ת��
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String unicode2Gbk(String receiveData) {
		StringBuffer rtn = new StringBuffer();
		while (receiveData!=null && receiveData.length() >= 1) {
			if (receiveData.startsWith("%u")) {
				// �����ͷ���Ǻ���
				String temp = receiveData.substring(0, 6);
				// ��ȡǰ��λ���д���
				temp = temp.replace("%u", "");
				// ����������u��web url ����ת����%ȥ��
				int j = Integer.parseInt(temp, 16);
				// ��ʮ�����Ƶ��ַ���ת��������
				rtn.append((char) j);
				// ������ǿ��ת���ַ�������ӵ����ض�����
				receiveData = receiveData.substring(6, receiveData.length());
				// ��ʣ����ַ���ȡ����������һ������
			} else {// �����ͷ�Ĳ��Ǻ��֣�Ӣ�Ļ����֣�
				rtn.append(receiveData.substring(0, 1));
				// ֱ����ӵ������ַ�������
				receiveData = receiveData.substring(1, receiveData.length());
				// ��ʣ����ַ���ȡ����������һ������
			}
		}
		
		String reslut = rtn.toString();

		for(int i=0;i<lmzf.length;i++){
			try {
				reslut = reslut.replaceAll(lmzf[i], zmzf[i]);
			} catch (Exception e) {
				// TODO: handle exception
				//System.out.println(zmzf[i]);
			}
		}
		
		return reslut;
	}
	
	/**
	 * �ж��Ƿ�೤
	 * 
	 * @author ΰ�����
	 * 
	 */
	public boolean isBz(String xh) {
		BjtsxmDAO dao = new BjtsxmDAO();
		return dao.isBz(xh);
	}
	
	/**
	 * �ж��Ƿ����
	 * 
	 * @author ΰ���·
	 * @throws Exception
	 */
	public Boolean isExists(String table, String pk, String pkValue) {

		boolean flag = false;

		try {
			flag = dao.isExists(table, pk, pkValue);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ִ�в������
	 * 
	 * @author ΰ���·
	 * @throws Exception
	 */
	public String fillZero(String value) {

		if (!Base.isNull(value)) {

			// ����ĸ
			String first = value.substring(0, 1);

			if (".".equalsIgnoreCase(first)) {
				value = "0" + value;
			}
		}

		return value;
	}
	// ====================�������==============================
	
	/**
	 * ���������е��ظ�����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String[] getRepeatArrValue(String[] arr1, String[] arr2) {

		ArrayList<String> list = new ArrayList<String>();

		if (arr1 != null && arr1.length > 0 && arr2 != null && arr2.length > 0) {

			for (int i = 0; i < arr1.length; i++) {

				for (int j = 0; j < arr2.length; j++) {

					if (arr1[i].equalsIgnoreCase(arr2[j])) {
						list.add(arr1[i]);
						break;
					}

				}
			}
		}

		return list.toArray(new String[] {});
	}

	/**
	 * ���������еĲ��ظ�����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String[] getNoRepeatArrValue(String[] arr1, String[] arr2) {

		ArrayList<String> list = new ArrayList<String>();

		if (arr1 != null && arr1.length > 0 && arr2 != null && arr2.length > 0) {

			for (int i = 0; i < arr1.length; i++) {

				boolean flag = true;

				for (int j = 0; j < arr2.length; j++) {

					if (arr1[i].equalsIgnoreCase(arr2[j])) {
						flag = false;
						break;
					}				
				}
				
				if (flag) {
					list.add(arr1[i]);
				}
			}
		}

		return list.toArray(new String[] {});
	}
	
	/**
	 * �ṩ�ַ��Ƿ������������
	 * 
	 * @author ΰ�����
	 * 
	 */
	public Boolean isExistInArr(String[] arr, String variable) {

		boolean flag = false;

		if (arr != null && arr.length > 0) {

			for (int i = 0; i < arr.length; i++) {

				if (variable.equalsIgnoreCase(arr[i])) {

					flag = true;

					break;
				}
			}
		}

		return flag;
	}
	
	// �����������Ϊһ������
	public String[] unionArray(String[] array1, String[] array2) {
		if (array1 != null) {
			if (array2 != null) {
				String array[] = new String[array1.length + array2.length];
				copyArray(array1, array);
				for (int i = 0; i < array2.length; i++) {
					array[array1.length + i] = array2[i];
				}
				return array;
			} else {
				return array1;
			}
		} else {
			return array2;
		}
	}

	// ��һ������copy����һ������
	public String[] copyArray(String[] fromArray, String[] toArray2) {
		if (fromArray != null && toArray2 != null) {
			int min = fromArray.length <= toArray2.length ? fromArray.length
					: toArray2.length;
			for (int i = 0; i < min; i++) {
				toArray2[i] = fromArray[i];
			}
			return toArray2;
		} else {
			if (toArray2 == null) {
				return fromArray;
			} else {
				return toArray2;
			}
		}
	}
	// ====================������� end==============================
	
	/**
	 * ���ָ������ֶ�
	 * 
	 * @author qlj
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableZdList(String tableName)
			throws Exception {
		return dao.getTableZdList(tableName);
	}

	/**
	 * ��ȡָ��ѧ������������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXszzList(String xh){
		
		String tableName = "xszz_zzxmb";
		String[] colList = new String[]{"xmdm","xmmc","xmb","sqzq","shjb","xmlb"};
		List<HashMap<String, String>> zzInfo = dao.getTableListInfo(tableName, colList);
		
		if (null != zzInfo && zzInfo.size() > 0){
			int size = zzInfo.size();
			String[] xmdm = new String[size];
			String[] xmmc = new String[size]; 
			String[] xmb = new String[size]; 
			String[] sqzq = new String[size]; 
			String[] shjb = new String[size]; 
			String[] xmlb = new String[size];
			
			for (int i = 0 ; i < size ; i++){
				xmdm[i] = zzInfo.get(i).get("xmdm");
				xmmc[i] = zzInfo.get(i).get("xmmc");
				xmb[i] = zzInfo.get(i).get("xmb");
				sqzq[i] = zzInfo.get(i).get("sqzq");
				shjb[i] = zzInfo.get(i).get("shjb");
				xmlb[i] = zzInfo.get(i).get("xmlb");
			}
			
			return dao.getLnXszzList(xmb, xmdm, xmmc, sqzq, shjb,xmlb,xh);
		}
		
		return null;
	}
	
	/**
	 * ������Դ�ش����ȡ��Դ������<br>
	 * ����������ѧ��������Ϣ��洢��ʡ/��/�ص�ת��
	 * @param syd ��Դ�ش���  ʡ/��/��
	 * @param splitFlg �������Դ�ش��밴ʲô�ָ�ģ� '/'?
	 * @param returnSplitFlg ���ص���Դ��������Ҫ�ָ�ô������Ҫ���վ��С�
	 * 						  ����null,�ָ�����Ĳ�����Ҳ��˦��~
	 * @return String
	 */
	public String getSydmc(String syd,String splitFlg,String returnSplitFlg){
		
		if (StringUtils.isNotNull(syd) && syd.split(splitFlg) != null
				&& syd.split(splitFlg).length == 3) {
			return dao.getSydmc(syd, splitFlg, returnSplitFlg);
		}
		return "";
	}
	
	/**
	 * ��request�е�����ת��Ϊmap
	 * @param request
	 * @param prefix ����ǰ׺
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request){
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap() );
		}		
		//�������Ϊmultipart/form-data ��ȡ�������ݵ�����
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		HashMap<String, String> valueMap = new HashMap<String, String>();
		
		
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			Object value = entry.getValue();
			
				valueMap.put(paramName, value.getClass().isArray() 
					      ? (String)Array.get(value, 0) 
					      : value.toString());//�ֶ�ֵ
				
									
		}
		
		return valueMap;
	}
	
	/**
	 * ���ݱ�������ȡrequest�б��ֶε�ֵ
	 * @param request
	 * @param table ����
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request,String table){
		
		//���ݱ�����ȡ���ֶ���
		List<HashMap<String,String>>tableInfo=selectTableInfo(table);
		//��ȡ��request��ȡ����map��Ϣ
		HashMap<String, String> paramMap =(HashMap<String, String>)getValueMap(request);
		
		HashMap<String, String> valueMap = new HashMap<String, String>();
		//���ݱ��ֶδ�map�л�ȡ�����ֶ�
		for(int i=0;i<tableInfo.size();i++){
			
			HashMap<String,String>tableMap=tableInfo.get(i);
			//��ȡ���ֶ�
			String comments=tableMap.get("name").toLowerCase();
			//���ݱ��ֶλ�ȡֵ
			String commentsValue=paramMap.get(comments);
			
			//ֵ�ǿ�ʱ�ż���
			if(!Base.isNull(commentsValue)){
				valueMap.put(comments, commentsValue);
			}
		}
		return valueMap;
	}
	
	/**
	 * ���ݱ�������ȡrequest�б��ֶε�ֵ
	 * @param request
	 * @param table ����
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request,String[]zd){
		
		
		//��ȡ��request��ȡ����map��Ϣ
		HashMap<String, String> paramMap =(HashMap<String, String>)getValueMap(request);
		
		HashMap<String, String> valueMap = new HashMap<String, String>();
		//���ݱ��ֶδ�map�л�ȡ�����ֶ�
		for(int i=0;i<zd.length;i++){
			
			//��ȡ���ֶ�
			String comments=zd[i].toLowerCase();
			//���ݱ��ֶλ�ȡֵ
			String commentsValue=paramMap.get(comments);
			
			//ֵ�ǿ�ʱ�ż���
			if (commentsValue != null ) { 
				valueMap.put(comments, commentsValue);
			}
			
		}
		return valueMap;
	}
	
	/**
	 * ���ݱ�������ȡrequest�б��ֶε�ֵ(ֵΪObject)
	 * @param request
	 * @param table ����
	 * */
	public HashMap<String, Object> getValueMapByObj(HttpServletRequest request,String[]zd){
		
		//��ȡ��request��ȡ����map��Ϣ
		HashMap<String, String> paramMap =(HashMap<String, String>)getValueMap(request);
		
		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
		//���ݱ��ֶδ�map�л�ȡ�����ֶ�
		for(Map.Entry<String, String> entry : paramMap.entrySet()){
			
			//��ȡ��ֵ
			String comments=entry.getKey();
			//��ȡֵ
			String commentsValue=entry.getValue();
			
			if(zd!=null && zd.length>0){
				
				setValueMap(valueMap, comments, commentsValue, zd);
				
			}else {
				
				setValueMap(valueMap, comments, commentsValue);
				
			}
			
		}
		return valueMap;
	}
	
	public void setValueMap(HashMap<String, Object> valueMap,String comments,String commentsValue,String[] zd){

		
		//��ȡ��һ���»��ߵ�λ��
		int firstIndex=comments.indexOf("_");
		//�ж��Ƿ���� ��_�� �������»���ʱ�Ž��з�װ����
		if(firstIndex!=-1){
				
			//�»���ǰ��ʾ���͵Ĳ���
			String format=comments.substring(0,firstIndex);
				
			//��ȥ�»����Լ��»���ǰ�Ĳ���
			comments=comments.replace(format+"_","");
			for(int i=0;i<zd.length;i++){
					
				if(comments.equalsIgnoreCase(zd[i])){
					//���������ж� ��װmodel��ֵ
					if("str".equalsIgnoreCase(format)){
						String value=commentsValue;
						value=unicode2Gbk(value);
						valueMap.put(comments, commentsValue);
						
						valueMap.put("str_"+comments, commentsValue);
					}else if("array".equalsIgnoreCase(format)){
						
						String values=commentsValue;
						values=unicode2Gbk(values);
						String[]value=values.split("!!array!!");
						valueMap.put(comments, value);
						valueMap.put("array_"+comments, value);
					}
				}
			}
		}
		
	}
	
	
	public void setValueMap(HashMap<String, Object> valueMap,String comments,String commentsValue){

		
		//��ȡ��һ���»��ߵ�λ��
		int firstIndex=comments.indexOf("_");
		//�ж��Ƿ���� ��_�� �������»���ʱ�Ž��з�װ����
		if(firstIndex!=-1){
				
			//�»���ǰ��ʾ���͵Ĳ���
			String format=comments.substring(0,firstIndex);
				
			//��ȥ�»����Լ��»���ǰ�Ĳ���
			comments=comments.replace(format+"_","");	
				
			//���������ж� ��װmodel��ֵ
			if("str".equalsIgnoreCase(format)){
				String value=commentsValue;
				value=unicode2Gbk(value);
				valueMap.put(comments, commentsValue);
				
			}else if("array".equalsIgnoreCase(format)){
				
				String values=commentsValue;
				values=unicode2Gbk(values);
				String[]value=values.split("!!array!!");
				valueMap.put(comments, value);
			}
				
		}
		
	}
	
	/**
	 * ���ݱ�������ȡrequest�б��ֶε�ֵ(ֵΪObject)
	 * @param request
	 * @param table ����
	 * */
	public HashMap<String, Object> getValueMapByObj(HttpServletRequest request){
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap() );
		}		
		//�������Ϊmultipart/form-data ��ȡ�������ݵ�����
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
		
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){


			getValueByFormat(entry, valueMap);
									
		}
		
		return valueMap;
	}
	
	/**
	 * ����ָ���ĸ�ʽ��ȡ����
	 * @return
	 */
	public void getValueByFormat(Map.Entry<String, Object>entry,
			HashMap<String, Object> valueMap){
		//����
		String key = entry.getKey();
		//ֵ
		Object value = entry.getValue();
		
		int firstIndex=key.indexOf("_");
		//�ж��Ƿ���� ��_�� 
		if(firstIndex!=-1){
			
				valueMap.put(key, value.getClass().isArray() 
					      ? (String)Array.get(value, 0) 
					      : value.toString());//�ֶ�ֵ
				
			
		}
		
	}
		
	/**
	 * ��map�е�ֵ��װ��model��
	 * map����request �е�ֵ��װΪmap��
	 * ��Ҫ��װ��model����
	 * ע:��ʱֻ֧�� �ַ����������ַ�����
	 * ���ַ�����array_,�ַ�:str_��
	 * @author qlj
	 * @throws Exception
	 */
	public void getModelValue(Object model,HashMap<String,Object>map)
			throws Exception {

		for(Map.Entry<String, Object> entry : map.entrySet()){
			
			//��ȡ��ֵ
			String key=entry.getKey();
			//��ȡֵ
			Object obj=entry.getValue();
			
			//��ȡ��һ���»��ߵ�λ��
			int firstIndex=key.indexOf("_");
			//�ж��Ƿ���� ��_�� �������»���ʱ�Ž��з�װ����
			if(firstIndex!=-1){
				
				//�»���ǰ��ʾ���͵Ĳ���
				String format=key.substring(0,firstIndex);
				
				//��ȥ�»����Լ��»���ǰ�Ĳ���
				key=key.replace(format+"_","");
				
				//���������ж� ��װmodel��ֵ
				if("str".equalsIgnoreCase(format)){
					String value=(String)obj;
					value=unicode2Gbk(value);
					String methodName="set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
					model.getClass().getMethod(methodName,
							(Class[]) new Class[] { String.class }).invoke(model,value);
					
				}else if("array".equalsIgnoreCase(format)){
					String values=(String)obj;
					values=unicode2Gbk(values);
					String methodName="set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
					String[]value=values.split("!!array!!");
					model.getClass().getMethod(methodName, new Class[] { String[].class })
					.invoke(model, (Object) value);
				}
				
			}
			
		}
	}
	
	/**
	 * ��map�е�ֵ��װ��model��
	 * map����request �е�ֵ��װΪmap��
	 * ��Ҫ��װ��model����
	 * ע:��ʱֻ֧�� �ַ����������ַ�����
	 * ���ַ�����array_,�ַ�:str_��
	 * @author qlj
	 * @throws Exception
	 */
	public void getModel(Object model,HashMap<String,String>map)
			throws Exception {

		for(Map.Entry<String, String> entry : map.entrySet()){
			
			//��ȡ��ֵ
			String key=entry.getKey();
			//��ȡֵ
			String str=entry.getValue();
				
			String methodName="set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
			model.getClass().getMethod(methodName,
					(Class[]) new Class[] { String.class }).invoke(model,str);			
		}
	}
	
	/**
	 * ��request�е�ֵ��װ��ָ����model��
	 * ע�����װ���Ա���ָ�����ͣ�
	 * �ַ���str_,�ַ�������array_
	 * @param model
	 * @param request
	 */
	public void getModelValue(Object model,HttpServletRequest request){
		
		HashMap<String,Object>map=getValueMapByObj(request);
		
		try {
			getModelValue(model, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ʼ�� page��searchModel��user����
	 * 
	 * @param rForm
	 * @param model
	 * @param request
	 * @param user
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void commInit(RequestForm rForm, Object model,
			HttpServletRequest request, User user) throws SecurityException,
			IllegalArgumentException, UnsupportedEncodingException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		// ==================��ҳ begin===================
		Pages pages = setPages("", request);
		// ==================��ҳ end========================

		// ==================�߼���ѯ begin========================
		SearchModel searchModel = setSearchModel(rForm, request);
		// ==================�߼���ѯ end=======================

		// ---------------------�� page��searchModel��user�������model��
		// begin-------------------
		model.getClass().getMethod("setPages",
				(Class[]) new Class[] { Pages.class }).invoke(model, pages);

		model.getClass().getMethod("setSearchModel",
				(Class[]) new Class[] { SearchModel.class }).invoke(model,
				searchModel);

		model.getClass().getMethod("setUser",
				(Class[]) new Class[] { User.class }).invoke(model, user);
		// ---------------------�� page��searchModel��user�������model�� end-------------------
	}
	
	//==================ţB begin==========================
	/**
	 * ��ţB�����������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param model
	 * @param saveMap
	 * @param user
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean saveTable(Object model, HashMap<String, Object> saveMap,
			User user) throws Exception {

		Class myClass = model.getClass();

		// ������
		String tableName = (String) myClass.getMethod("getSave_table",
				(Class[]) null).invoke(model, (Object[]) null);
		// ����
		String[] primary_key = (String[]) myClass.getMethod("getPrimary_key",
				(Class[]) null).invoke(model, (Object[]) null);
		// ��һ�ֶ�
		String[] save_string_zd = (String[]) myClass.getMethod(
				"getSave_string_zd", (Class[]) null).invoke(model,
				(Object[]) null);
		// �����ֶ�
		String[] save_array_zd = (String[]) myClass.getMethod(
				"getSave_array_zd", (Class[]) null).invoke(model,
				(Object[]) null);

		//==================�ж��Ƿ���� begin============================
		StringBuilder pk = new StringBuilder();
		if (primary_key != null && primary_key.length > 0) {
			for (int i = 0; i < primary_key.length; i++) {
				if (i != 0) {
					pk.append("||");
				}
				pk.append(primary_key[i]);
			}
		}

		StringBuilder pkValue = new StringBuilder();
		if (primary_key != null && primary_key.length > 0) {
			for (int i = 0; i < primary_key.length; i++) {
				if (save_string_zd != null && save_string_zd.length > 0) {
					for (int j = 0; j < save_string_zd.length; j++) {
						if (primary_key[i].equalsIgnoreCase(save_string_zd[j])) {
							String key = "str_" + save_string_zd[j];
							String value = (String)saveMap.get(key);
							pkValue.append(unicode2Gbk(value));
						}
					}
				}
			}
		}

		//�ж��Ƿ����
		boolean flag = isExists(tableName, pk.toString(), pkValue.toString());
		
		//==================�ж��Ƿ���� end============================
		
		// ==================����������� begin============================
		ArrayList<String> value = new ArrayList<String>();
		if (save_string_zd != null && save_string_zd.length > 0) {
			for (int i = 0; i < save_string_zd.length; i++) {
				String key = "str_" + save_string_zd[i];
				value.add(unicode2Gbk((String) saveMap.get(key)));
			}
		}
		
		String[] key = unionArray(save_string_zd, save_array_zd);
		// ==================����������� end============================
		
		if (flag) {
			// �޸�
			flag = dao.updateTable(tableName, key, value
					.toArray(new String[] {}), pk.toString(), pkValue
					.toString(), user);
		} else {
			// ����
			flag = dao.insertTable(tableName, key, value
					.toArray(new String[] {}), user);
		}

		return flag;
	}
	
	/**
	 * ��ţB�����������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getDetail(Object model)
			throws Exception {

		Class myClass = model.getClass();

		// ��ѯ��
		String tableName = (String) myClass.getMethod("getSearch_table",
				(Class[]) null).invoke(model, (Object[]) null);
		// ��ʾ�ֶ�
		String[] key = (String[]) myClass.getMethod("getDetail_zd",
				(Class[]) null).invoke(model, (Object[]) null);

		// ����ֵ
		String[] pkValue = (String[]) myClass.getMethod("getPkValue",
				(Class[]) null).invoke(model, (Object[]) null);

		HashMap<String, String> map = dao.getDetail(tableName, key, pkValue[0]);

		return map;
	}
	
	/**
	 * ��ţB��ɾ�������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean deleteTable(Object model, User user) throws Exception {

		Class myClass = model.getClass();

		// ������
		String tableName = (String) myClass.getMethod("getSave_table",
				(Class[]) null).invoke(model, (Object[]) null);

		// ����
		String[] primary_key = (String[]) myClass.getMethod("getPrimary_key",
				(Class[]) null).invoke(model, (Object[]) null);

		// ����ֵ
		String[] pkValue = (String[]) myClass.getMethod("getPkValue",
				(Class[]) null).invoke(model, (Object[]) null);

		StringBuilder pk = new StringBuilder();
		if (primary_key != null && primary_key.length > 0) {
			for (int i = 0; i < primary_key.length; i++) {
				if (i != 0) {
					pk.append("||");
				}
				pk.append(primary_key[i]);
			}
		}

		boolean flag = dao.deleteTable(tableName, pk.toString(), pkValue, user);

		return flag;
	}
	
	/**
	 * ��ţB����ȡRequest��ַ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getRequestURL(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String url = "";
		url = request.getContextPath();
		url = url + request.getServletPath();

		java.util.Enumeration names = request.getParameterNames();
		int i = 0;
		if (!"".equals(request.getQueryString())
				|| request.getQueryString() != null) {
			url = url + "?" + request.getQueryString();
		}

		if (names != null) {
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				if (i == 0) {
					url = url + "?";
				} else {
					url = url + "&";
				}
				i++;

				String value = request.getParameter(name);
				if (value == null) {
					value = "";
				}

				url = url + name + "=" + value;
				try {
					// java.net.URLEncoder.encode(url, "ISO-8859");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			// String enUrl = java.net.URLEncoder.encode(url, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return url;
	}
	//==================ţB end============================
}
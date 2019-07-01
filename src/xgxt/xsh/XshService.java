package xgxt.xsh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.bdsz.BdszDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.String.StringUtils;

public class XshService {
	XshDAO dao = new XshDAO();

	/**
	 * ��ȡ���ֶ�
	 * 
	 * @param realTable
	 * @param myForm
	 */
	public void getBdZd(String realTable, XshForm myForm) {

		List<HashMap<String, String>> list = dao.getBdZd(realTable);
		HashMap<String, ArrayList<HashMap<String, String>>> opslist = dao
				.getOps(realTable);
		String[] zdyZd = new String[list.size()];
		String[] zdyZdz = new String[list.size()];
		String[] zdyZdlx = new String[list.size()];
		String[] zdyZdcd = new String[list.size()];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String zd = map.get("zdid");
				String zdz = map.get("zdmc");
				String zdlx = map.get("zdlx");
				String zdcd = map.get("zdcd");
				zdyZd[i] = zd;
				zdyZdz[i] = zdz;
				zdyZdlx[i] = zdlx;
				zdyZdcd[i] = zdcd;
			}
		}
		myForm.setArrZd(zdyZd);
		myForm.setArrZdz(zdyZdz);
		myForm.setArrZdlx(zdyZdlx);
		myForm.setArrZdcd(zdyZdcd);
		myForm.setOpslist(opslist);
	}


	/**
	 * ��ȡ�Զ����ֶ��б�
	 * 
	 * @param tableName
	 * @param xn
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> zdyColList(String tableName)
			throws SQLException {

		return dao.zdyColList(tableName);
	}

	/**
	 * ����Զ����ֶβ�ѯ��ͷ
	 * 
	 * @param tableName
	 * @param colList
	 * @param zdyCol
	 * @param zdyColCN
	 * @return
	 */
	public List<HashMap<String, String>> getZdyTopTr(String tableName,
			String[] colList, String[] zdyCol, String[] zdyColCN) {
		DAO dao = DAO.getInstance();
		int colLen = colList.length;
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		String[] zcolList = new String[colLen + zdyCol.length];
		String[] zcolListCN = new String[colLen + zdyCol.length];
		for (int i = 0; i < colLen; i++) {
			zcolList[i] = colList[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolList[colLen + i] = zdyCol[i];
		}
		for (int i = 0; i < colLen; i++) {
			zcolListCN[i] = colListCN[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolListCN[colLen + i] = zdyColCN[i];
		}
		List<HashMap<String, String>> topTr = dao.arrayToList(zcolList,
				zcolListCN);
		return topTr;
	}

	/**
	 * ��ѯ���Զ����ֶ�����
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getDataList(String tableName, XshModel model,
			String[] colList, String[] zdyCol, String realTable, String[] pk)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZdyQueryList(tableName, model, colList, zdyCol,
				realTable, pk);
	}

	/**
	 * ����ɾ��
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean delData(String realTable, String[] pkV, String pk)
			throws SQLException {

		return dao.delData(realTable, pkV, pk);
	}


	/**
	 * ��������
	 * 
	 * @return
	 */
	public HashMap<String, String> getOneData(String tableName,
			String realTable, String[] colList, String[] zdyzdList,
			String pkCol, String pk) {

		return dao.getOneData(tableName, realTable, colList, zdyzdList, pkCol,
				pk);
	}

	/**
	 * �ļ� �ϴ�
	 * 
	 * @param request
	 * @param myForm
	 * @param model
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void fileUpload(HttpServletRequest request, XshForm myForm,
			XshModel model) throws FileNotFoundException, IOException {
		// �����ļ��ϴ�
		FormFile file = myForm.getUploadFile();
		String filePath = "";
		String fName = "";
		if (file != null && StringUtils.isNotNull(file.getFileName())) {
			String dir = "/upload/xsh";
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}

			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			int size = file.getFileSize();
			if (size > 10485760) {
				request.setAttribute("message", "�ļ�����10M,���ܳɹ��ϴ���");
			} else {
				fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				model.setFjlj(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			}
		}
	}

	/**
	 * ����ѧ����
	 * @param form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateXsh(SaveForm form, XshModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	
	/**
	 * �����б� 
	 * @param request
	 * @param type
	 */
	public void setList(HttpServletRequest request,int type) {
		DAO dao = DAO.getInstance();
		
		List list= dao.getChkList(type);
		request.setAttribute("list", list);
	}
	
	
	/**
	 * ��ȡ��ʦ�б�
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getJs(XshModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getJs(model);
	}
	
	/**
	 * ��øɲ��б�
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGbList(HashMap<String, String> map) {
		// isst (�Ƿ�����)��yes no
		// stmc(��������)��no ty ������ѧԺ501000
		// gblx(�ɲ�����)��ȡ��bjxh_xsgblxb
		return dao.getGbList(map);
	}
	
	
	/**
	 * �����б�
	 * @return
	 */
	public List<HashMap<String, String>>  getStList() {
		return dao.getStList();
	}
	
	
	/**
	 * ������ѧ����������
	 * @return
	 * @throws SQLException
	 */
	public boolean saveStcy(String[] xh, String stmc) throws SQLException {
		return dao.saveStcy(xh, stmc);
	}
	
	/**
	 * ������ѧ����������
	 * @return
	 * @throws SQLException
	 */
	public boolean saveStcy(String[] xh, String stmc,String nd) throws SQLException {
		return dao.saveStcy(xh, stmc,nd);
	}

	
	/**
	 * �޸�ְ��
	 * @param pkValue
	 * @param zwdm
	 * @return
	 * @throws SQLException
	 */
	public boolean updateZw(String[] pkValue, String zwdm) throws SQLException {
		return dao.updateZw(pkValue, zwdm);
	}
	
	/**
	 * �����Ƿ��������Ÿɲ�
	 * @param pkValue
	 * @param sfyxstgb
	 * @return
	 * @throws SQLException
	 */
	public boolean updateYxstgb(String[] pkValue, String sfyxstgb) throws SQLException {
		return dao.updateSfyxgb(pkValue, sfyxstgb);
	}
	
	
	/**
	 * �����û���ȡ����������Ϣ
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getStInfo(String userName) {
		if (!Base.isNull(userName)) {
			return dao.getStInfo(userName);
		} else {
			return null;
		}
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return dao.getStuInfo(xh);
	}
	
	/**
	 * ��ȡ���Ÿɲ�������Ĳ���
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getStgbst(String xh){
		
		return dao.getStgbst(xh);
	}
}

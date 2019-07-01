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
	 * 获取表单字段
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
	 * 获取自定义字段列表
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
	 * 获得自定义字段查询表头
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
	 * 查询含自定义字段数据
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
	 * 批量删除
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean delData(String realTable, String[] pkV, String pk)
			throws SQLException {

		return dao.delData(realTable, pkV, pk);
	}


	/**
	 * 单条数据
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
	 * 文件 上传
	 * 
	 * @param request
	 * @param myForm
	 * @param model
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void fileUpload(HttpServletRequest request, XshForm myForm,
			XshModel model) throws FileNotFoundException, IOException {
		// 处理文件上传
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
				request.setAttribute("message", "文件大于10M,不能成功上传！");
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
	 * 更新学生会
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
	 * 下拉列表 
	 * @param request
	 * @param type
	 */
	public void setList(HttpServletRequest request,int type) {
		DAO dao = DAO.getInstance();
		
		List list= dao.getChkList(type);
		request.setAttribute("list", list);
	}
	
	
	/**
	 * 获取教师列表
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
	 * 获得干部列表
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGbList(HashMap<String, String> map) {
		// isst (是否社团)：yes no
		// stmc(社团名称)：no ty 篮球社学院501000
		// gblx(干部类型)：取表bjxh_xsgblxb
		return dao.getGbList(map);
	}
	
	
	/**
	 * 社团列表
	 * @return
	 */
	public List<HashMap<String, String>>  getStList() {
		return dao.getStList();
	}
	
	
	/**
	 * 批量将学生加入社团
	 * @return
	 * @throws SQLException
	 */
	public boolean saveStcy(String[] xh, String stmc) throws SQLException {
		return dao.saveStcy(xh, stmc);
	}
	
	/**
	 * 批量将学生加入社团
	 * @return
	 * @throws SQLException
	 */
	public boolean saveStcy(String[] xh, String stmc,String nd) throws SQLException {
		return dao.saveStcy(xh, stmc,nd);
	}

	
	/**
	 * 修改职务
	 * @param pkValue
	 * @param zwdm
	 * @return
	 * @throws SQLException
	 */
	public boolean updateZw(String[] pkValue, String zwdm) throws SQLException {
		return dao.updateZw(pkValue, zwdm);
	}
	
	/**
	 * 设置是否优秀社团干部
	 * @param pkValue
	 * @param sfyxstgb
	 * @return
	 * @throws SQLException
	 */
	public boolean updateYxstgb(String[] pkValue, String sfyxstgb) throws SQLException {
		return dao.updateSfyxgb(pkValue, sfyxstgb);
	}
	
	
	/**
	 * 根据用户名取所在社团信息
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
	 * 获取学生信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 获取社团干部所管理的部门
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getStgbst(String xh){
		
		return dao.getStgbst(xh);
	}
}

package xsgzgl.xsxx.general.zxxs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.DAO.PicDAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_在校学生_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxZxxsService extends CommService implements XsxxZxxsInterface {

	XsxxZxxsDAO dao = new XsxxZxxsDAO();

	/**
	 * 获得表头文件(在校学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsxxZxxsTop(XsxxZxxsModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"xjztm" };
		String[] cn = new String[] { "", "学号", "姓名", "性别", "年级", "班级", "学籍状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集(在校学生)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXsxxZxxsList(myForm, user);
	}

	/**
	 * 构建结果集(在校学生)
	 * 
	 * @author 伟大的骆
	 */
	public String createXsxxZxxsHTML(SearchRsModel rsModel,
			XsxxZxxsModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"showXsxxDetail('"+pk+"')\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					html.append(rs[j]);
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	/**
	 * 保存学生照片
	 * 
	 * @author 伟大的骆
	 */
	public String saveStuPic(XsxxGeneralForm model, User user) {

		PicDAO picDao = new PicDAO();

		// 学号
		String xh = model.getXh();
		
		// 文件
		FormFile file = model.getStuPic();
		String fileName = file.getFileName();

		boolean isAllowType = fileName.endsWith("jpg")||fileName.endsWith("jpeg") 
						   || fileName.endsWith("gif") 
						   || fileName.endsWith("png")
						   || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF") || fileName.endsWith("PNG")|| fileName.endsWith("BMP");
		
		if (!isAllowType){
			return "上传失败，非法的文件格式！";
		}
		
		if (file.getFileSize() > 1024 * 1024){
			return "上传失败，文件大小超过1M！";
		} else {
			try {
				picDao.savePic(file.getInputStream(), xh, "stu");
				return "上传成功！";
			} catch (Exception e) {
				e.printStackTrace();
				return "上传失败，请重新上传！";
			} 
		}
		
		
	}
	/**
	 * 
	 * @描述:华中师范大学高考照片保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-2 上午09:33:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String saveStuGkPic(XsxxGeneralForm model, User user) {

		PicDAO picDao = new PicDAO();

		// 学号
		String xh = model.getXh();
		
		// 文件
		FormFile file = model.getStuGkPic();
		String fileName = file.getFileName();

		boolean isAllowType = fileName.endsWith("jpg") 
						   || fileName.endsWith("gif") 
						   || fileName.endsWith("png")
						   || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF") || fileName.endsWith("PNG")|| fileName.endsWith("BMP");
		
		if (!isAllowType){
			return "上传失败，非法的文件格式！";
		}
		
		if (file.getFileSize() > 1024 * 1024){
			return "上传失败，文件大小超过1M！";
		} else {
			try {
				picDao.saveGkPic_stu(file.getInputStream(), xh);
				return "上传成功！";
			} catch (Exception e) {
				e.printStackTrace();
				return "上传失败，请重新上传！";
			} 
		}
		
		
	}

	/**
	 * 获得学生基本信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getZxxsInfo(XsxxZxxsModel model, User user) {

		// 学号
		String xh = model.getXh();

		HashMap<String, String> map = dao.getZxxsInfo(xh, user);

		return map;
	}

	public boolean saveBycl(XsxxZxxsModel model, User user) throws Exception {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean saveXsxx(XsxxZxxsModel model, User user) {
		// TODO 自动生成方法存根
		return false;
	}

	
}
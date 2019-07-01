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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��Уѧ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxZxxsService extends CommService implements XsxxZxxsInterface {

	XsxxZxxsDAO dao = new XsxxZxxsDAO();

	/**
	 * ��ñ�ͷ�ļ�(��Уѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXsxxZxxsTop(XsxxZxxsModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"xjztm" };
		String[] cn = new String[] { "", "ѧ��", "����", "�Ա�", "�꼶", "�༶", "ѧ��״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����(��Уѧ��)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXsxxZxxsList(myForm, user);
	}

	/**
	 * ���������(��Уѧ��)
	 * 
	 * @author ΰ�����
	 */
	public String createXsxxZxxsHTML(SearchRsModel rsModel,
			XsxxZxxsModel model, ArrayList<String[]> rsArrList, User user) {

		// IE�汾
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
	 * ����ѧ����Ƭ
	 * 
	 * @author ΰ�����
	 */
	public String saveStuPic(XsxxGeneralForm model, User user) {

		PicDAO picDao = new PicDAO();

		// ѧ��
		String xh = model.getXh();
		
		// �ļ�
		FormFile file = model.getStuPic();
		String fileName = file.getFileName();

		boolean isAllowType = fileName.endsWith("jpg")||fileName.endsWith("jpeg") 
						   || fileName.endsWith("gif") 
						   || fileName.endsWith("png")
						   || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF") || fileName.endsWith("PNG")|| fileName.endsWith("BMP");
		
		if (!isAllowType){
			return "�ϴ�ʧ�ܣ��Ƿ����ļ���ʽ��";
		}
		
		if (file.getFileSize() > 1024 * 1024){
			return "�ϴ�ʧ�ܣ��ļ���С����1M��";
		} else {
			try {
				picDao.savePic(file.getInputStream(), xh, "stu");
				return "�ϴ��ɹ���";
			} catch (Exception e) {
				e.printStackTrace();
				return "�ϴ�ʧ�ܣ��������ϴ���";
			} 
		}
		
		
	}
	/**
	 * 
	 * @����:����ʦ����ѧ�߿���Ƭ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-2 ����09:33:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String saveStuGkPic(XsxxGeneralForm model, User user) {

		PicDAO picDao = new PicDAO();

		// ѧ��
		String xh = model.getXh();
		
		// �ļ�
		FormFile file = model.getStuGkPic();
		String fileName = file.getFileName();

		boolean isAllowType = fileName.endsWith("jpg") 
						   || fileName.endsWith("gif") 
						   || fileName.endsWith("png")
						   || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF") || fileName.endsWith("PNG")|| fileName.endsWith("BMP");
		
		if (!isAllowType){
			return "�ϴ�ʧ�ܣ��Ƿ����ļ���ʽ��";
		}
		
		if (file.getFileSize() > 1024 * 1024){
			return "�ϴ�ʧ�ܣ��ļ���С����1M��";
		} else {
			try {
				picDao.saveGkPic_stu(file.getInputStream(), xh);
				return "�ϴ��ɹ���";
			} catch (Exception e) {
				e.printStackTrace();
				return "�ϴ�ʧ�ܣ��������ϴ���";
			} 
		}
		
		
	}

	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getZxxsInfo(XsxxZxxsModel model, User user) {

		// ѧ��
		String xh = model.getXh();

		HashMap<String, String> map = dao.getZxxsInfo(xh, user);

		return map;
	}

	public boolean saveBycl(XsxxZxxsModel model, User user) throws Exception {
		// TODO �Զ����ɷ������
		return false;
	}

	public boolean saveXsxx(XsxxZxxsModel model, User user) {
		// TODO �Զ����ɷ������
		return false;
	}

	
}
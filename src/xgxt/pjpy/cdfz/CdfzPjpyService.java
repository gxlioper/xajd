package xgxt.pjpy.cdfz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.views.utils.ArrayUtil;

import xgxt.DAO.DAO;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.comm.CommService;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

/**
 * �ɶ���֯����SERVICE
 * lt
 * 2011-11-23
 */
public class CdfzPjpyService extends CommService {

	CdfzPjpyDao dao = new CdfzPjpyDao();

	/**
	 * ������������
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyRsList(CdfzPjpyForm model) {

		return dao.getPjpyRsList(model);
	}

	/**
	 * ������Ϣ����
	 * 
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getPjhzList(CdfzPjpyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		List<HashMap<String, String>> cprsList = getPjpyRsList(model);
		ArrayList<String[]> pjhzList = dao.getPjhzList(model);
		String[] zjArr = null;

		if (pjhzList != null && cprsList != null && pjhzList.size() > 0
				&& cprsList.size() > 0) {
			String[] firstArr = pjhzList.get(0);
			zjArr = new String[firstArr.length];
			zjArr[0] = "zj";
			zjArr[1] = "�ܼ�";
			for (int i = 0; i < pjhzList.size(); i++) {
				String[] pjhzArr = pjhzList.get(i);
				for (int j = 0; j < cprsList.size(); j++) {
					HashMap<String, String> cprsMap = cprsList.get(j);
					if (cprsMap.get("dm").equalsIgnoreCase(pjhzArr[0])) {
						pjhzArr[2] = cprsMap.get("rs");

						for (int z = 2; z < pjhzArr.length; z++) {

							if (StringUtils.isEmpty(zjArr[z])) {
								zjArr[z] = "0";
							}
							zjArr[z] = String.valueOf((Integer
									.parseInt(zjArr[z]) + Integer
									.parseInt(pjhzArr[z])));
						}
						break;
					}
				}
			}
			pjhzList.add(zjArr);
		}else{
			
			pjhzList=null;
		}
		return pjhzList;
	}

	/**
	 * ����ͳ�����ͻ�ȡ������Ϣ�б�
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyXmList(CdfzPjpyForm model) {

		return dao.getPjpyXmList(model);
	}

	/**
	 * Method printNjfbtj �������Ž������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public void printJxhz(CdfzPjpyForm myForm, HttpServletRequest request,
			WritableWorkbook wwb) throws Exception {
		
		PjpyXmszService pjService=new PjpyXmszService();
		List<String[]> pjhzList = getPjhzList(myForm);
		List<HashMap<String, String>> xmList = getPjpyXmList(myForm);
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String tjlx = myForm.getTjlx();
		String bmlb = "";

		String xqmc=pjService.getXqmc(Base.currXq);
		if ("xy".equalsIgnoreCase(tjlx)) {
			bmlb = "ϵ��";
		} else if ("zy".equalsIgnoreCase(tjlx)) {
			bmlb = "רҵ";
		} else if ("bj".equalsIgnoreCase(tjlx)) {
			bmlb = "�༶";
		}

		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.createSheet("������Ŀ����", 0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0, 5 + xmList.size() * 2, 0);
			ws.addCell(new Label(0, 0, xn + "ѧ��ȵ�" + xqmc + "ѧ��ѧУ��ѧ��" + bmlb
					+ "ͳ�Ʊ�", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
			// ���ñ��߿�
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			ws.mergeCells(0, 1, 0, 2);
			ws.addCell(new Label(0, 1, bmlb, wcfTytle));

			ws.mergeCells(1, 1, 1, 2);
			ws.addCell(new Label(1, 1, "��������", wcfTytle));

			for (int i = 0; i < xmList.size(); i++) {
				HashMap<String, String> xmMap = xmList.get(i);
				ws.mergeCells(2 + i * 2, 1, 3 + i * 2, 1);
				ws.addCell(new Label(2 + i * 2, 1, xmMap.get("mc"), wcfTytle));
			}
			int max = 2 + xmList.size() * 2;
			ws.mergeCells(max, 1, max + 1, 1);
			ws.addCell(new Label(2 + xmList.size() * 2, 1, "�ϼ�", wcfTytle));
			max += 1;

			ws.mergeCells(max + 1, 1, max + 1, 2);
			ws.addCell(new Label(max + 1, 1, "������ʦ", wcfTytle));
			max += 1;
			ws.mergeCells(max + 1, 1, max + 1, 2);
			ws.addCell(new Label(max + 1, 1, "������ǩ��", wcfTytle));

			for (int i = 0; i < xmList.size(); i++) {
				ws.addCell(new Label(2 + i * 2, 2, "����", wcfTytle));
				ws.addCell(new Label(3 + i * 2, 2, "���", wcfTytle));
			}
			ws.addCell(new Label(2 + xmList.size() * 2, 2, "����", wcfTytle));
			ws.addCell(new Label(3 + xmList.size() * 2, 2, "���", wcfTytle));

			for (int i = 0; i < pjhzList.size(); i++) {

				String[] pjhzArr = pjhzList.get(i);
				for (int j = 1; j < pjhzArr.length; j++) {

					ws.addCell(new Label(j - 1, i + 3, pjhzArr[j], wcfTytle));

				}
				ws.addCell(new Label(pjhzArr.length - 1, i + 3, " ", wcfTytle));
				ws.addCell(new Label(pjhzArr.length, i + 3, " ", wcfTytle));
			}
			max = 2 + xmList.size() * 2;
			ws.mergeCells(max + 2, 2 + pjhzList.size(), max + 3, 2 + pjhzList
					.size());
			ws.addCell(new Label(max + 2, 2 + pjhzList.size(), "ʱ�䣺"
					+ GetTime.getNowTime(), wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	// =========================��������д����==================================
	/**
	 * ��ѯѧ�����˻���Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXshjmxResult(CdfzPjpyForm form, User user) throws Exception {
		return dao.queryXshjmxResult(form, user);
	}
	
	/**
	 * ��ѯѧ�����˻񽱱�ͷ �����б�
	 * @param list
	 * @return
	 */
	public List<HashMap<String, String>> queryXshjmxTitle(List<String[]> list) {
		String[] array = {"ѧ��", "����", "�༶"};
		List<String> result = new ArrayList<String>(){};
		if (list != null && list.size() > 0) {
			String[] oneArray = list.get(0);
			if (oneArray != null && oneArray.length > 3) {
				for (int i=3;i<oneArray.length;i++) {
					result.add("��ѧ��"+(i-2));
				}
			}
		}
		DAO myDao = DAO.getInstance();
		array = myDao.unionArray(array, result != null ? result.toArray(new String[0]) : new String[]{});
		return myDao.arrayToList(array, array);
	}
	
	/**
	 * ��ѯѧ�����˻񽱱�ͷ�������� 
	 * @param list
	 * @return
	 */
	public String[] queryXshjmxTop(List<String[]> list) {
		String[] array = {"ѧ��", "����", "�༶"};
		List<String> result = new ArrayList<String>(){};
		if (list != null && list.size() > 0) {
			String[] oneArray = list.get(0);
			if (oneArray != null && oneArray.length > 3) {
				for (int i=3;i<oneArray.length;i++) {
					result.add("��ѧ��"+(i-2));
				}
			}
		}
		DAO myDao = DAO.getInstance();
		array = myDao.unionArray(array, result != null ? result.toArray(new String[0]) : new String[]{});
		return array;
	}
	
	/**
	 * ��ѯѧ�����˻񽱱�ͷ�������� �����б�
	 * @param list
	 * @return
	 */
	public List<HashMap<String, String>> queryXshjmxTitleExport(List<String[]> list) {
		String[] array = {"ѧ��", "ѧ��", "ѧ��", "����", Base.YXPZXY_KEY, "רҵ", "�༶", "�꼶"};
		List<String> result = new ArrayList<String>(){};
		if (list != null && list.size() > 0) {
			String[] oneArray = list.get(0);
			if (oneArray != null && oneArray.length > 8) {
				for (int i=8;i<oneArray.length;i++) {
					result.add("��ѧ��"+(i-7));
				}
			}
		}
		DAO myDao = DAO.getInstance();
		array = myDao.unionArray(array, result != null ? result.toArray(new String[0]) : new String[]{});
		return myDao.arrayToList(array, array);
	}
	
	/**
	 * ��ѯѧ�����˻񽱱�ͷ�������� ��������
	 * @param list
	 * @return
	 */
	public String[] queryXshjmxTitleExportArray(List<String[]> list) {
		String[] array = {"ѧ��", "ѧ��", "ѧ��", "����", Base.YXPZXY_KEY, "רҵ", "�༶", "�꼶"};
		List<String> result = new ArrayList<String>(){};
		if (list != null && list.size() > 0) {
			String[] oneArray = list.get(0);
			if (oneArray != null && oneArray.length > 8) {
				for (int i=8;i<oneArray.length;i++) {
					result.add("��ѧ��"+(i-7));
				}
			}
		}
		DAO myDao = DAO.getInstance();
		return myDao.unionArray(array, result != null ? result.toArray(new String[0]) : new String[]{});
	}
	
	/**
	 * ��ѯѧ�����˻���Ϣ��������
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXshjmxResultExport(CdfzPjpyForm form, User user) throws Exception {
		List<String[]> result = dao.queryXshjmxResultExport(form, user);
		String[] array = queryXshjmxTitleExportArray(result);
		List<String[]> rs = new ArrayList<String[]>(){};
		rs.add(array);
		if (result != null && result.size() > 0) {
			for (String[] one : result) {
				rs.add(one);
			}
		}
		return rs;
	}
}

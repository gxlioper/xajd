package xgxt.xtwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;
import xgxt.comm.CommSetting;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά����ʼ����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * 
 * @version 1.0
 */

public class XtwhInit {

	/**
	 * ��ݷ�ʽ_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getXtwhKjfsInit(RequestForm rForm, HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xtwh";
		// ������������
		String menu = "kjfssz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����·��
		String path = "";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setUserType(userType);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}

	/**
	 * ��ҳ����_����ר��_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getXtwhXzzqInit(RequestForm rForm, SyszForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xtwh";
		// ������������
		String menu = "xzzq";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "xtwh_xzzqwh.do";
		// ��ͼ
		String tableName = "xg_view_xtwh_xzzq";
		// ����ֶ�
		String[] colList = new String[] { "pk", "filemc", "ss", "lx", "towho", "scr",
				"scsj", "filepath" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// ��ҳ
		Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);
		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPk(pk);
		rForm.setUserType(userType);
		rForm.setTableName(tableName);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);
	}

	/**
	 * ��ҳ����_����ר��(��ҳ)_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getMainXzzqInit(RequestForm rForm, SyszForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xtwh";
		// ������������
		String menu = "xzzq";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����
		String pk = request.getParameter("pk");
		// �ļ�����
		String filess = request.getParameter("filess");
		// ����·��
		String path = "xtwhSysz.do?method=xzzqView";
		// ��ͼ
		String tableName = "xg_view_xtwh_xzzq";
		// ����ֶ�
		String[] colList = new String[] { "filepath", "filesm", "filemc", "ss",
				"lx" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		// �����ֶ�
		String[] qtzd = new String[] { "filess" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { filess };

		// ��ҳ
		Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);
		// ===============ͨ������ end =================

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPk(pk);
		rForm.setUserType(userType);
		rForm.setTableName(tableName);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
	}

	/**
	 * ��ҳ����_��ҳ����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getXtwhSydcInit(RequestForm rForm, SyszForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xtwh";
		// ������������
		String menu = "sydc";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "xtwh_sydcwh.do";
		// ��ͼ
		String tableName = "xg_view_xtwh_sydc";
		// ����ֶ�
		String[] colList = new String[] { "dcid", "shownr", "dcr", "dcsj",
				"sfqy", "dcnr" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
	
		// ��ҳ
		Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// ��ʾ������
		String showNum = "4";
		commSetting.setShowNum(showNum);
		// ===============ͨ������ end =================

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPk(pk);
		rForm.setUserType(userType);
		rForm.setTableName(tableName);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);

	}
}

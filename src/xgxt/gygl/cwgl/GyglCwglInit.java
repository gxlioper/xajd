package xgxt.gygl.cwgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.utils.SearchUtils;

public class GyglCwglInit {
	/**
	 * �Զ�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getQszdfpRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// �������
		String fpdx = model.getFpdx();

		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			initForXy(rForm, model, request);
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			initForNjXy(rForm, model, request);
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			initForNjZy(rForm, model, request);
		} else if ("bj".equalsIgnoreCase(fpdx)) {// �������Ϊ�༶
			initForBj(rForm, model, request);
		}
	}
	
	/**
	 * �������ѧԺ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initForXy(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "cwzdfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_cwgl_zdfp.do";
		// ��ѯ����
		String searchType = "xy";
		// ����ֶ�
		String[] colList = new String[] { "xydm", "xymc", "bmrs", "yzrcws",
				"wzrcws" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "xy");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

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

		// ��ʾ����
		String showNum = "4";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * ��������꼶+ѧԺ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initForNjXy(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qszdfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_zdfp.do";
		// ��ѯ����
		String searchType = "njxy";
		// ����ֶ�
		String[] colList = new String[] { "nj||'!!@@!!'||xydm", "nj", "xymc",
				"bmrs", "yzrcws","wzrcws" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "njxy");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

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

		// ��ʾ����
		String showNum = "5";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * ��������꼶+רҵ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initForNjZy(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qszdfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_zdfp.do";
		// ��ѯ����
		String searchType = "njzy";
		// ����ֶ�
		String[] colList = new String[] { "nj||'!!@@!!'||zydm", "nj", "xymc",
				"zymc", "bmrs","yzrcws","wzrcws"};
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "njzy");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

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

		// ��ʾ����
		String showNum = "6";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * �������༶
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initForBj(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qszdfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_zdfp.do";
		// ��ѯ����
		String searchType = "bj";
		// ����ֶ�
		String[] colList = new String[] { "bjdm", "nj", "xymc", "zymc", "bjmc",
				"bmrs", "yzrcws","wzrcws" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "bj");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

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

		// ��ʾ����
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}

	/**
	 * ��ò�ͬ�������ı�ͷ
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ

			colListCN = new String[] { "����", Base.YXPZXY_KEY+"����", "��������", "�ѷ��䴲λ��", "δ���䴲λ��" };

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ

			colListCN = new String[] { "����", "�꼶", Base.YXPZXY_KEY+"����", "��������", "�ѷ��䴲λ��",
					"δ���䴲λ��" };

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ

			colListCN = new String[] { "����", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "��������",
					"�ѷ��䴲λ��", "δ���䴲λ��" };

		} else if ("bj".equalsIgnoreCase(fpdx)) {// �������Ϊ�༶

			colListCN = new String[] { "����", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����",
					"��������", "�ѷ��䴲λ��", "δ���䴲λ��" };
		
		} else if ("sdcw".equalsIgnoreCase(fpdx)) {
			
			colListCN = new String[] {"����", "����", "ѧ��", "¥��", "����", "���Һ�", "�����Ա�", "��λ��",
					"��סѧ��", "���ҷ������", "��ס״̬" };
		
		} else if ("axsfp".equalsIgnoreCase(fpdx)) {
			
			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶", "�༶" };
		
		} else if ("xscwfp".equalsIgnoreCase(fpdx)) {
			
			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶", "�༶" };
		
		} else if("cwfpxxcx".equalsIgnoreCase(fpdx)){
			
			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶",Base.YXPZXY_KEY,
					"רҵ", "�༶","������","¥��","����","���Һ�","��λ��","��סʱ��" };
			
		}
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * ��ò�ͬ�������ı�ͷ
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getCwjgTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", Base.YXPZXY_KEY,
					"������","¥��","����","���Һ�","��λ��","��ס����"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶",Base.YXPZXY_KEY,
					"������","¥��","����","���Һ�","��λ��","��ס����"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶",Base.YXPZXY_KEY,
					"רҵ","������","¥��","����","���Һ�","��λ��","��ס����"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶",Base.YXPZXY_KEY,
						"רҵ", "�༶","������","¥��","����","���Һ�","��λ��","��ס����"};
		}
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * ��ò�ͬ�������ı�ͷ
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getCwtjTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����",Base.YXPZXY_KEY,"��������(��/Ů)","����������(��/Ů)","��ס�˴�λ(��/Ů)","δס�˴�λ(��/Ů)","��ס�˴�λ(��/Ů)"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����","�꼶",Base.YXPZXY_KEY,"��������(��/Ů)","����������(��/Ů)","��ס�˴�λ(��/Ů)","δס�˴�λ(��/Ů)","��ס�˴�λ(��/Ů)"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����","�꼶","רҵ","��������(��/Ů)","����������(��/Ů)","��ס�˴�λ(��/Ů)","δס�˴�λ(��/Ů)","��ס�˴�λ(��/Ů)"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����","�༶","��������(��/Ů)","����������(��/Ů)","��ס�˴�λ(��/Ů)","δס�˴�λ(��/Ů)","��ס�˴�λ(��/Ů)"};
		}
		
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * �������ѧԺ
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniCwsdfpRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "cwsdfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_cwgl_sdfp.do";
		// ��ѯ����
		String searchType = model.getFpdx();
		// ����ֶ�
		String[] colList = new String[] {"disabled","pkValue","xh","ldmc","cs","qsh","xb","cwh","rzxs","qsfpdx","cwfp"};
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "sdcw");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "3";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(colList.length-3);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѧ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniAxsfpRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "axsfpcw";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_cwgl_sdfp.do";
		// ��ѯ����
		String searchType = "axsfpcw";
		// ����ֶ�
		String[] colList = new String[] {"pkValue","xh","xm","xb","nj","bjmc"};
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "axsfp");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

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

		// ��ʾ����
		String showNum =String.valueOf(colList.length-1);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѧ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniXscwfpRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "xscwfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_cwgl_sdfp.do";
		// ��ѯ����
		String searchType = "xscwfp";
		// ����ֶ�
		String[] colList = new String[] {"xh","xm"};
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "xscwfp");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(colList.length);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѧ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniSdfpcwRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "xscwfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_cwgl_sdfp.do";
		// ��ѯ����
		String searchType = "xscwfp";
		// ����ֶ�
		String[] colList = new String[] {"pkValue","xh","xm","xb","nj","bjmc"};
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "xscwfp");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

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

		// ��ʾ����
		String showNum =String.valueOf(colList.length);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * �������༶
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getCwfpxxcxRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "cwfpjg";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_cwgl_fpjg.do";
		//��ѯ����
		String searchType = model.getFpdx(); 
		String fpdx= model.getFpdx();
		// ����ֶ�
		String[] colList =null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colList = new String[] { "pkValue", "xh", "xm", "xb", "xymc",
					"ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colList = new String[] { "pkValue", "xh", "xm", "xb", "nj","xymc",
					"ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colList = new String[] { "pkValue", "xh", "xm", "xb", "nj","xymc",
					"zymc","ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			 colList = new String[] { "pkValue", "xh", "xm", "xb", "nj","xymc",
						"zymc", "bjmc","ssbh","ldmc","cs","qsh","cwh","rzrq"};
		}

		// ��ͷ
		List<HashMap<String, String>> topTr = getCwjgTopTr(colList, fpdx);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

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

		// ��ʾ����
		String showNum =String.valueOf(colList.length-1);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setSearchType(searchType);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѧ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniCwtjRForm(RequestForm rForm, GyglCwglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "cwfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_cwgl_cwfp.do";
		// ��ѯ����
		String searchType = model.getFpdx();
		// ����ֶ�
		
		String[] colList =null;
		if("xy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}else if("njxy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}else if("njzy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","zymc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}else if("bj".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","bjmc","bmrs","fpqss","kzrcws","wzrcws","yzrcws"};
		}
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getCwtjTopTr(colList, searchType);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(colList.length);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
}

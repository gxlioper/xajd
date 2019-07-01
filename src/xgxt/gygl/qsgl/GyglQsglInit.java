package xgxt.gygl.qsgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.SearchUtils;

public class GyglQsglInit {

	/**
	 * �Զ�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getQszdfpRForm(RequestForm rForm, GyglQsglForm model,
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
	public void initForXy(RequestForm rForm, GyglQsglForm model,
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
		String searchType = "xy";
		// ����ֶ�
		String[] colList = new String[] { "xydm", "xymc", "bmrs", "fpqss",
				"kzrcws", "xfprs" };
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
	public void initForNjXy(RequestForm rForm, GyglQsglForm model,
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
				"manNum", "manZqss", "manZcws", "womanNum", "womanZqss",
				"womanZcws" };
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
	public void initForNjZy(RequestForm rForm, GyglQsglForm model,
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
				"zymc", "bmrs", "fpqss", "kzrcws", "xfprs" };
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
	public void initForBj(RequestForm rForm, GyglQsglForm model,
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
				"bmrs", "fpqss", "kzrcws", "xfprs" };
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
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, String>> getTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;

		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ

			colListCN = new String[] { "����",Base.YXPZXY_KEY+"����", Base.YXPZXY_KEY+"����", "����������", "��ס�˴�λ��" };

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ

			colListCN = new String[] { "����", "�꼶", Base.YXPZXY_KEY+"����", Base.YXPZXY_KEY+"����", "����������",
					"��ס�˴�λ��" };

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ

			colListCN = new String[] { "����", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "רҵ����",
					"����������", "��ס�˴�λ��" };

		} else if ("bj".equalsIgnoreCase(fpdx)) {// �������Ϊ�༶

			colListCN = new String[] { "����", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����",
					"�༶����", "����������", "��ס�˴�λ��" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

	/**
	 * �Զ�����(���)_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getQszdfpjgRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qszdfpjg";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_zdfpjg.do";
		// ����ֶ�
		String[] colList = new String[] { "ssbh", "ldmc", "cs", "qsh", "cws",
				"xb", "kfhz", "fpbm" };
		// ��ѯ����
		String searchType = model.getFpdx();
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_gygl_qsfp", colList, null);
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
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzd = new String[] { "fpdx" };
		String[] qtzdz = new String[] { model.getFpdx() };

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
	 * �ֶ�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getQssdfpRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		
		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qssdfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_sdfp.do";
		// ����ֶ�
		String[] colList = new String[] { "ssbh", "ldmc", "cs", "qsh", "cws",
				"xb", "kfhz", "fpbm","sjly" };
		// ��ѯ����
		String searchType = model.getFpdx();
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_gygl_qsfp", colList, null);
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
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
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "10";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzd = new String[] { "fpdx" };
		String[] qtzdz = new String[] { model.getFpdx() };

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
	 * ������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getQsfpjgRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		
		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qsfpjg";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_fpjg.do";
		// ����ֶ�
		String[] colList = new String[] { "ssbh", "ldmc", "cs", "qsh", "cws",
				"xb", "kfhz", "fpbm","sjly" };
		// ��ѯ����
		String searchType = model.getFpdx();
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_gygl_qsfp", colList, null);
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
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
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "10";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzd = new String[] { "fpdx" };
		String[] qtzdz = new String[] { model.getFpdx() };

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

	// ====================2011.6.23 edit by luojw ========================
	// ====================ps:���쵼��������޸�============================
	/**
	 * ���ҷ���_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getQsfpRForm(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// �������
		String fpdx = model.getFpdx();

		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			initQsfpForXy(rForm, model, request);
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			initQsfpForNjXy(rForm, model, request);
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			initQsfpForNjZy(rForm, model, request);
		} else if ("bj".equalsIgnoreCase(fpdx)) {// �������Ϊ�༶
			initQsfpForBj(rForm, model, request);
		}
	}
	
	/**
	 * �������ѧԺ
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initQsfpForXy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qsfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_qsfp.do";
		// ��ѯ����
		String searchType = "xy";
		// ����ֶ�
		String[] colList = new String[] { "xydm", "xymc", "bmrs", "manNum",
				"manZqss", "manZcws", "womanNum", "womanZqss", "womanZcws" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "xy");
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
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

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
	public void initQsfpForNjXy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qsfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_qsfp.do";
		// ��ѯ����
		String searchType = "njxy";
		// ����ֶ�
		String[] colList = new String[] { "nj||'!!@@!!'||xydm", "nj", "xymc",
				"bmrs", "manNum", "manZqss", "manZcws", "womanNum",
				"womanZqss", "womanZcws" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "njxy");
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
		String showNum = "9";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

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
	public void initQsfpForNjZy(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qsfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_qsfp.do";
		// ��ѯ����
		String searchType = "njzy";
		// ����ֶ�
		String[] colList = new String[] { "nj||'!!@@!!'||zydm", "nj", "zymc",
				"bmrs", "manNum", "manZqss", "manZcws", "womanNum",
				"womanZqss", "womanZcws" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "njzy");
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
		String showNum = "9";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

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
	public void initQsfpForBj(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "qsfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_qsgl_qsfp.do";
		// ��ѯ����
		String searchType = "bj";
		// ����ֶ�
		String[] colList = new String[] { "bjdm", "bjmc", "bmrs", "manNum",
				"manZqss", "manZcws", "womanNum", "womanZqss", "womanZcws" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getQsfpTopTr(colList, "bj");
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
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzd = new String[] { "xfprs", "fpdx" };
		String[] qtzdz = new String[] {
				String.valueOf(Integer.parseInt(showNum) + 1), model.getFpdx() };

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
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, String>> getQsfpTopTr(String[] colList,
			String fpdx) {

		String[] colListCN = null;

		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ

			colListCN = new String[] { "����", Base.YXPZXY_KEY+"����", "������", "������", "�ѷ���������",
					"����ס������", "Ů����", "�ѷ���Ů����", "����סŮ����" };

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ

			colListCN = new String[] { "����", "�꼶", Base.YXPZXY_KEY+"����", "������", "������",
					"�ѷ���������", "����ס������", "Ů����", "�ѷ���Ů����", "����סŮ����" };

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ

			colListCN = new String[] { "����", "�꼶", "רҵ����", "������", "������",
					"�ѷ���������", "����ס������", "Ů����", "�ѷ���Ů����", "����סŮ����" };

		} else if ("bj".equalsIgnoreCase(fpdx)) {// �������Ϊ�༶

			colListCN = new String[] { "����", "�༶����", "������", "������", "�ѷ���������",
					"����ס������", "Ů����", "�ѷ���Ů����", "����סŮ����" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
}

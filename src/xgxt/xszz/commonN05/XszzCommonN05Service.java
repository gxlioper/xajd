package xgxt.xszz.commonN05;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.szdw.utils.MakeQuery;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ Description: N05ѧ������Service Copyright: Copyright (c) 2009
 * Company: zfsoft Author: ���� Version: 1.0 Time: 2009-10-13
 */
public class XszzCommonN05Service {

	XszzCommonN05DAO dao = null;// ���ݲ���DAO

	private List<HashMap<String, String>> makeTitList(String[] enList,
			String[] cnList) {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ��ȡ�б�����
	 * 
	 * @param int
	 *            num
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getChkList(int num) {
		DAO dao = DAO.getInstance();
		return dao.getChkList(num);
	}

	/**
	 * ��ȡ��Ŀ��˼���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getShjb(String xmb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getShjb(xmb);
	}

	/**
	 * ��ȡ��Ŀ�Ƿ������������������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSfkns(String xmb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getSfkns(xmb);
	}

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getStu(xh);
	}

	/**
	 * ��ȡxszz_com_xfjm1�������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfjm1cs(String xh) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm1cs(xh);
	}

	/**
	 * ��ȡѧ��xszz_com_knsrdb1���ѵȼ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKns1Rdqk(String xh, String xn) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKns1Rdqk(xh, xn);
	}

	/**
	 * �õ�����Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSqQx(String zzType, String sUserType, String xh)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getSqQx(zzType, sUserType, xh);
	}

	/**
	 * ��Ŀ���ò�ѯ��ͷ getXmszTit ---- ��Ŀ���ò�ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmszTit() throws Exception {
		String[] enList = new String[] { "pk", "xmmc", "shjb", "sfkns" };
		String[] cnList = new String[] { "����", "��Ŀ", "��˼���", "�Ƿ����Ϊ������" };
		return makeTitList(enList, cnList);
	}

	/**
	 * ��Ŀ���ò�ѯ��� Xmszres ---- ��Ŀ����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXmszRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXmszRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �õ���Ŀ�б�
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getXmList() throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXmList();
	}

	/**
	 * ��ȡ��Ŀ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXmszxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXmszxx(pkVal);
	}

	/**
	 * ɾ��������ѧ����Ŀ��Ϣ
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delWszxjxm(String[] pk, HttpServletRequest request) {
		dao = new XszzCommonN05DAO();
		dao.delWszxjxm(pk, request);
	}

	/**
	 * ������ѧ����Ŀ��Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxjxmTit() throws Exception {
		String[] enList = new String[] { "pk", "xmdm", "xmmc" };
		String[] cnList = new String[] { "����", "��Ŀ����", "��Ŀ����" };
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ����Ŀ��Ϣ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxjxmRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxjxmRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * ��ȡ������ѧ����Ŀ��ϸ��Ϣ
	 * 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxjxmByXmdm(String xmdm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjxmByXmdm(xmdm);
	}

	/**
	 * ������ѧ����Ŀ����¼���
	 * 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxjXmjeList(String xmdm) {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjXmjeList(xmdm);
	}

	/**
	 * ����������ѧ����Ŀ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxjxm(String xmdm, String xmmc,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxjxm(xmdm, xmmc, request);
	}

	/**
	 * ����������ѧ�����¼
	 * 
	 * @param param
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxjje(List<HashMap<String, String>> param, String xmdm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxjje(param, xmdm);
	}

	/**
	 * ��ȡ��ͥ�������1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdc1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getJtqkdc1xx(pkVal);
	}

	/**
	 * �����ͥ�������1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdc1Sqxx(Jtqkdc1Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveJtqkdc1Sqxx(model, request);
	}

	/**
	 * ɾ����ͥ�������1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delJtqkdc1xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delJtqkdc1xx(cbVal, request);
	}

	/**
	 * ��ͥ�������1��ѯ��ͷ Jtqkdc1tit ---- ��ͥ�������1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdc1Tit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "txsj" };
		String[] cnList = new String[] { "����", "ѧ��", "����", "�Ա�", "���֤��",
				Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "��дʱ��" };
		return makeTitList(enList, cnList);
	}

	/**
	 * ��ͥ�������1��ѯ��� Jtqkdc1res ---- ��ͥ�������1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdc1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJtqkdc1Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * ��ͥ�������1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getJtqkdc1ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getJtqkdc1ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * ��ͥ�������1���� Jtqkdc1Exp ---- ��ͥ�������1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdc1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_jtqkdc1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_jtqkdc1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ��ȡ�������϶�1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1xx(pkVal);
	}

	/**
	 * ��ȡ�������϶�1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1yjxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1yjxx(pkVal);
	}

	/**
	 * �����������϶�1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Yj(String yj, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd1Yj(yj, request);
	}

	/**
	 * �����������϶�1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Sqxx(Knsrdb1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd1Sqxx(model, request, shjb);
	}

	/**
	 * ɾ���������϶�1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd1xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnsrd1xx(cbVal, request);
	}

	/**
	 * �����޸��������϶�1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd1xx(cbVal, shjg, request);
	}

	/**
	 * �������϶�1��ѯ��ͷ Knsrd1tit ---- �������϶�1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd1Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
				"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
				"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�1��ѯ��� Knsrd1res ---- �������϶�1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd1Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�1��ѯ��� getKnsrd1ResByFdy ---- �������϶�1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd1ResByFdy(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd1ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd1ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �������϶�1���� Knsrd1Exp ---- �������϶�1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsrd1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * �����������϶�1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Shxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd1Shxx(model, request);
	}

	/**
	 * �������϶�1��ѯ��ͷ getKnsrd1TitForDM ---- �������϶�1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd1TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "tjr", "tjsj", "xyshjg", "xxshjg" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "�༶����",
					"����", "�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "tjr", "tjsj", "xxshjg" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", Base.YXPZXY_KEY+"����",
					"����", "�ύ��", "�ύʱ��", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�1��ѯ��ͷ getKnsrd1TitForStush ---- �������϶�1��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd1TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				"sqsj", "fdysh", "xyshjg", "xxshjg" };
		cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����",
				"����ʱ��", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };

		return makeTitList(enList, cnList);
	}

	/**
	 * ��ȡ�������϶�1����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1shtjjg(pkVal, userType, shjb);
	}

	/**
	 * ��ȡ�������϶�1����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd1Stush(pkVal, userType, shjb);
	}

	/**
	 * �����޸��������϶�1��˽��(���������)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiKnsrd1ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd1xxBybj(model, request);// �޸�ѧ�������Ϣ
		dao.modKnsrd1Bmshtjb(model, request);// �޸Ĳ��������Ϣ
	}

	/**
	 * �ύѧԺ��˽��
	 * 
	 * @param String
	 *            userDep
	 * @param String
	 *            userName
	 * @param String
	 *            tjxm
	 * @param String
	 *            zj
	 * @param String
	 *            zjz
	 * @return boolean
	 */
	public boolean commitResult(String userDep, String userName, String tjxm,
			String zj, String zjz) {
		dao = new XszzCommonN05DAO();
		return dao.modXytjshjg(userDep, userName, tjxm, zj, zjz);
	}

	/**
	 * ��ȡѧԺ�ύ״̬
	 * 
	 * @param String
	 *            userDep
	 * @param String
	 *            zjz
	 * @param String
	 *            tjxm
	 * @return String
	 */
	public String queryXyshjg(String userDep, String zjz, String tjxm) {
		dao = new XszzCommonN05DAO();
		return dao.selectXyshjg(userDep, zjz, tjxm);
	}

	/**
	 * �����޸��������϶�1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd1xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸��������϶�1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addKnsrd1shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"knsrd1", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjknsrd1", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * �������϶�1��ѯ��� getKnsrd1ResForDM ---- �������϶�1(������ģʽ��ѯ)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd1ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ��ȡ�������϶�3��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd3xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd3xx(pkVal);
	}

	/**
	 * ��ȡ�������϶�3�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd3yjxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd3yjxx(pkVal);
	}

	/**
	 * �����������϶�3�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Yj(String yj, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd3Yj(yj, request);
	}

	/**
	 * �����������϶�3������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Sqxx(Knsrdb3Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd3Sqxx(model, request);
	}

	/**
	 * ɾ���������϶�3��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd3xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnsrd3xx(cbVal, request);
	}

	/**
	 * �����޸��������϶�3��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd3xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd3xx(cbVal, shjg, request);
	}

	/**
	 * ��������Ϊ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public void plPdkns(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.plPdkns(cbVal, request);
	}

	/**
	 * �������϶�3��ѯ��ͷ Knsrd3tit ---- �������϶�3��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd3Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
				"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
				"����������", "�����϶����", "ѧУ���" };
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�3��ʾ��ͷ Knsrd3gstit ---- �������϶�3��ʾ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd3gsTit() throws Exception {
		String[] enList = new String[] { "xn", "xh", "xm", "xb", "xymc",
				"zymc", "bjmc", "sqsj", "xxsh" };
		String[] cnList = new String[] { "ѧ��", "ѧ��", "����", "�Ա�", Base.YXPZXY_KEY+"����",
				"רҵ����", "�༶����", "����ʱ��", "�������" };
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�3�����ͷ Knsrd3yjtit ---- �������϶�3�����ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd3yjTit() throws Exception {
		String[] enList = new String[] { "yj", "txsj" };
		String[] cnList = new String[] { "���", "��дʱ��" };
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�3��ѯ��� Knsrd3res ---- �������϶�3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd3Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�3��ʾ��� Knsrd3gsres ---- �������϶�3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3gsRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd3gsRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�3������ Knsrd3yjres ---- �������϶�3���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3yjRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd3yjRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�3��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd3ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �������϶�3��ʾ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3gsResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd3gsResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �������϶�3�����ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3yjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd3yjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �������϶�3���� Knsrd3Exp ---- �������϶�3����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsrd3Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb3", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb3");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * �����������϶�3�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Shxx(Knsrdb3Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd3Shxx(model, request);
	}

	/**
	 * ��ȡ�������϶�2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd2xx(pkVal);
	}

	/**
	 * �����������϶�2������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd2Sqxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd2Sqxx(model, request);
	}

	/**
	 * ɾ���������϶�2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd2xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnsrd2xx(cbVal, request);
	}

	/**
	 * �����޸��������϶�2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd2xx(cbVal, shjg, request);
	}

	/**
	 * ��ȡ�������϶�2����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd2shtjjg(String pkVal, String userType)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd2shtjjg(pkVal, userType);
	}

	/**
	 * ��ȡ�������϶�2����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnsrd2Stush(pkVal, userType, shjb);
	}

	/**
	 * �����޸��������϶�2��˽��(���������)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiKnsrd2ForDep(String[] cbVal, String shjg, String shyj,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd2xxBybj(cbVal, shjg, request, shjb);// �޸�ѧ�������Ϣ
		dao.modKnsrd2Bmshtjb(cbVal, shjg, shyj, request, shjb);// �޸Ĳ��������Ϣ
	}

	/**
	 * �����޸��������϶�2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnsrd2xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸��������϶�2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addKnsrd2shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"knsrd2", "1" };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjknsrd2", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * �������϶�2��ѯ��ͷ getKnsrd2TitForStush ---- �������϶�2��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd2TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "sqsj", "fdysh", "xyshjg", "xxshjg" };
		String[] cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����",
				"רҵ����", "�༶����", "����ʱ��", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };

		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�2��ѯ��ͷ Knsrd2tit ---- �������϶�2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd2Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
				"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
				"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�2��ѯ��ͷ Knsrd2tit ---- �������϶�2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrd2TitForDM(String userType)
			throws Exception {
		String[] enList = null;
		String[] cnList = null;
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"xyshjg" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "�༶����",
					Base.YXPZXY_KEY+"��˽��" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "zymc",
					"xxshjg" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "רҵ����",
					"ѧУ��˽��" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�2��ѯ��� Knsrd2res ---- �������϶�2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd2Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�2��ѯ��� getKnsrd2ResByFdy ---- �������϶�2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd2ResByFdy(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�2��ѯ��� getKnsrd2ResForDM ---- �������϶�2(������ģʽ��ѯ)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrd2ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * �������϶�2��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd2ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsrd2ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �������϶�2���� Knsrd2Exp ---- �������϶�2����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsrd2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * �����������϶�2�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd2Shxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnsrd2Shxx(model, request);
	}

	/**
	 * ��ȡ�������϶�4��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getknsrd4xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getknsrd4xx(pkVal);
	}

	/**
	 * �����������϶�4������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveknsrd4Sqxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveknsrd4Sqxx(model, request);
	}

	/**
	 * ɾ���������϶�4��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delknsrd4xx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delknsrd4xx(cbVal, request);
	}

	/**
	 * �����޸��������϶�4��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modknsrd4xx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modknsrd4xx(cbVal, shjg, request);
	}

	/**
	 * �������϶�4��ѯ��ͷ knsrd4tit ---- �������϶�4��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getknsrd4Tit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
				"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
				"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		return makeTitList(enList, cnList);
	}

	/**
	 * �������϶�4��ѯ��� knsrd4res ---- �������϶�4
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getknsrd4Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getknsrd4Res(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������϶�4��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getknsrd4ResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getknsrd4ResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �������϶�4���� knsrd4Exp ---- �������϶�4����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getknsrd4Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knsrdb1", request, true);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knsrdb1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * �����������϶�4�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveknsrd4Shxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveknsrd4Shxx(model, request);
	}

	/**
	 * ��ȡ���ҽ�ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjjxj1xx(pkVal);
	}

	/**
	 * ������ҽ�ѧ��1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxj1Sqxx(Gjjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjjxj1Sqxx(model, request, shjb);
	}

	/**
	 * ɾ�����ҽ�ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjjxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjjxj1xx(cbVal, request, shjb);
	}

	/**
	 * �����޸Ĺ��ҽ�ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjjxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ���ҽ�ѧ��1��ѯ��ͷ Gjjxj1tit ---- ���ҽ�ѧ��1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
					"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���",
					"ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ���ҽ�ѧ��1��ѯ��� Gjjxj1res ---- ���ҽ�ѧ��1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ���ҽ�ѧ��1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjjxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjjxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ���ҽ�ѧ��1��ѯ��ͷ getGjjxj1TitForDM ---- ���ҽ�ѧ��1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxj1TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "yjjl", "xjjl", "sjjl", "zje", "tjr", "tjsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "�༶����",
					"����", "Ժ������", "У������", "ʡ������", "�ܽ��", "�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���",
					"ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "yjjl", "xjjl", "sjjl", "zje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", Base.YXPZXY_KEY+"����",
					"����", "Ժ������", "У������", "ʡ������", "�ܽ��", "�ύ��", "�ύʱ��", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ���ҽ�ѧ��1��ѯ��ͷ getGjjxj1TitForStush ---- ���ҽ�ѧ��1��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxj1TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "yjjl", "xjjl", "sjjl", "zje", "xysh",
					"xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "Ժ������", "У������", "ʡ������", "�ܽ��", Base.YXPZXY_KEY+"���",
					"ѧУ���" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "yjjl", "xjjl", "sjjl", "zje", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "Ժ������", "У������", "ʡ������", "�ܽ��", "ѧУ���" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * ���ҽ�ѧ��1��ѯ��� getGjjxj1ResByFdy ---- ���ҽ�ѧ��1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxj1ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ��ȡ���ҽ�ѧ��1����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxj1shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjjxj1shtjjg(pkVal, userType, shjb);
	}

	/**
	 * ��ȡ���ҽ�ѧ��1����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjjxj1Stush(pkVal, userType, shjb);
	}

	/**
	 * �����޸Ĺ��ҽ�ѧ��1��˽��(���������)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiGjjxj1ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjjxj1xxBybj(model, request);// �޸�ѧ�������Ϣ
		dao.modGjjxj1Bmshtjb(model, request);// �޸Ĳ��������Ϣ
	}

	/**
	 * �����޸Ĺ��ҽ�ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjjxj1xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸Ĺ��ҽ�ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addGjjxj1shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"gjjxj1", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjgjjxj1", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * ���ҽ�ѧ��1��ѯ��� getGjjxj1ResForDM ---- ���ҽ�ѧ��1(������ģʽ��ѯ)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxj1ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ���ҽ�ѧ��1���� Gjjxj1Exp ---- ���ҽ�ѧ��1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjjxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjjxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjjxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ������ҽ�ѧ��1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxj1Shxx(Gjjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjjxj1Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ������ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj1xx(pkVal);
	}

	/**
	 * ���������ѧ��1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj1Sqxx(Gjzxj1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj1Sqxx(model, request);
	}

	/**
	 * ɾ��������ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjzxj1xx(cbVal, request, shjb);
	}

	/**
	 * �����޸Ĺ�����ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ������ѧ��1��ѯ��ͷ Gjzxj1tit ---- ������ѧ��1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", "�ȼ�",
					"���", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", "�ȼ�",
					"���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��1��ѯ��� Gjzxj1res ---- ������ѧ��1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ������ѧ��1���� Gjzxj1Exp ---- ������ѧ��1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjzxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjzxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ���������ѧ��1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj1Shxx(Gjzxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj1Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ������ѧ��2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj2xx(pkVal);
	}

	/**
	 * ���������ѧ��2������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj2Sqxx(Gjzxj1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj2Sqxx(model, request);
	}

	/**
	 * ɾ��������ѧ��2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjzxj2xx(cbVal, request, shjb);
	}

	/**
	 * �����޸Ĺ�����ѧ��2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ������ѧ��2��ѯ��ͷ Gjzxj2tit ---- ������ѧ��2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "rdqk", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", "�ȼ�",
					"���", "�϶����", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "rdqk", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", "�ȼ�",
					"���", "�϶����", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��2��ѯ��� Gjzxj2res ---- ������ѧ��2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��2��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ������ѧ��2���� Gjzxj2Exp ---- ������ѧ��2����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjzxj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjzxj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ���������ѧ��2�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj2Shxx(Gjzxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj2Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ������ѧ��3��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj3xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj3xx(pkVal);
	}

	/**
	 * ���������ѧ��3������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj3Sqxx(Gjzxj3Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj3Sqxx(model, request, shjb);
	}

	/**
	 * ɾ��������ѧ��3��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj3xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjzxj3xx(cbVal, request, shjb);
	}

	/**
	 * �����޸Ĺ�����ѧ��3��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj3xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ������ѧ��3��ѯ��ͷ Gjzxj3tit ---- ������ѧ��3��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj3Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", "�ȼ�",
					"���", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", "�ȼ�",
					"���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��3��ѯ��� Gjzxj3res ---- ������ѧ��3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj3Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��3��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj3ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxj3ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ������ѧ��3��ѯ��ͷ getGjzxj3TitForDM ---- ������ѧ��3��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj3TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "zxjje", "tjr", "tjsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "�༶����",
					"����", "���", "�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "zxjje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", Base.YXPZXY_KEY+"����",
					"����", "���", "�ύ��", "�ύʱ��", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��3��ѯ��ͷ getGjzxj3TitForStush ----������ѧ��3��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxj3TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "�ȼ�", "���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "zxjdj", "zxjje", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "�ȼ�", "���", "ѧУ���" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��3��ѯ��� getGjzxj3ResByFdy ---- ������ѧ��3
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj3ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ��ȡ������ѧ��3����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj3shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj3shtjjg(pkVal, userType, shjb);
	}

	/**
	 * ��ȡ������ѧ��3����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxj3Stush(pkVal, userType, shjb);
	}

	/**
	 * �����޸Ĺ�����ѧ��3��˽��(���������)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiGjzxj3ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj3xxBybj(model, request);// �޸�ѧ�������Ϣ
		dao.modGjzxj3Bmshtjb(model, request);// �޸Ĳ��������Ϣ
	}

	/**
	 * �����޸Ĺ�����ѧ��3��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjzxj3xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸Ĺ�����ѧ��3��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addGjzxj3shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn", model.getXn(), model.getBjdm(), "bj",
				"gjzxj3", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getBjdm() + "bjgjzxj3", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * ������ѧ��3��ѯ��� getGjzxj3ResForDM ---- ������ѧ��3(������ģʽ��ѯ)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxj3ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��3���� Gjzxj3Exp ---- ������ѧ��3����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxj3Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjzxj3", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjzxj3");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ���������ѧ��3�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj3Shxx(Gjzxj3Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjzxj3Shxx(model, request, shjb);
	}

	/**
	 * �õ�������ѧ���б�
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjzxjList();
	}

	/**
	 * ��ȡ������־��ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjlzjxj1xx(pkVal);
	}

	/**
	 * ���������־��ѧ��1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj1Sqxx(Gjlzjxj1Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj1Sqxx(model, request);
	}

	/**
	 * ɾ��������־��ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjlzjxj1xx(cbVal, request, shjb);
	}

	/**
	 * �����޸Ĺ�����־��ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjlzjxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ������־��ѧ��1��ѯ��ͷ Gjlzjxj1tit ---- ������־��ѧ��1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
					"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���",
					"ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������־��ѧ��1��ѯ��� Gjlzjxj1res ---- ������־��ѧ��1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ������־��ѧ��1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjlzjxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ������־��ѧ��1���� Gjlzjxj1Exp ---- ������־��ѧ��1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjlzjxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjlzjxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ���������־��ѧ��1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj1Shxx(Gjlzjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj1Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ������־��ѧ��2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGjlzjxj2xx(pkVal);
	}

	/**
	 * ���������־��ѧ��2������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj2Sqxx(Gjlzjxj2Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj2Sqxx(model, request);
	}

	/**
	 * ɾ��������־��ѧ��2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delGjlzjxj2xx(cbVal, request, shjb);
	}

	/**
	 * �����޸Ĺ�����־��ѧ��2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modGjlzjxj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ������־��ѧ��2��ѯ��ͷ Gjlzjxj2tit ---- ������־��ѧ��2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
					"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���",
					"ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������־��ѧ��2��ѯ��� Gjlzjxj2res ---- ������־��ѧ��2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ������־��ѧ��2��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjlzjxj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ������־��ѧ��2���� Gjlzjxj2Exp ---- ������־��ѧ��2����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_gjlzjxj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_gjlzjxj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ���������־��ѧ��2�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj2Shxx(Gjlzjxj2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveGjlzjxj2Shxx(model, request, shjb);
	}

	/**
	 * ��ȡѧ�Ѽ���1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm1xx(pkVal);
	}

	/**
	 * ����ѧ�Ѽ���1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm1Sqxx(Xfjm1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm1Sqxx(model, request);
	}

	/**
	 * ɾ��ѧ�Ѽ���1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfjm1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfjm1xx(cbVal, request, shjb);
	}

	/**
	 * �����޸�ѧ�Ѽ���1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjm1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ѧ�Ѽ���1��ѯ��ͷ Xfjm1tit ---- ѧ�Ѽ���1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
					"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
					"xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���",
					"ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�Ѽ���1��ѯ��� Xfjm1res ---- ѧ�Ѽ���1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ѧ�Ѽ���1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfjm1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfjm1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ѧ�Ѽ���1���� Xfjm1Exp ---- ѧ�Ѽ���1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXfjm1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfjm1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfjm1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ����ѧ�Ѽ���1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm1Shxx(Xfjm1Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm1Shxx(model, request, shjb);
	}

	/**
	 * ��ȡѧ�Ѽ���2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm2xx(pkVal);
	}

	/**
	 * ��ȡѧ�Ѽ���2����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm2shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm2shtjjg(pkVal, userType, shjb);
	}

	/**
	 * ��ȡѧ�Ѽ���2����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfjm2Stush(pkVal, userType, shjb);
	}

	/**
	 * ����ѧ�Ѽ���2������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm2Sqxx(Xfjm2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm2Sqxx(model, request, shjb);
	}

	/**
	 * ɾ��ѧ�Ѽ���2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfjm2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfjm2xx(cbVal, request, shjb);
	}

	/**
	 * �����޸�ѧ�Ѽ���2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸�ѧ�Ѽ���2��˽��(���������)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiXfjm2ForDep(XszzCommonN05ActionForm actionForm,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm2xxBybj(actionForm, request);// �޸�ѧ�������Ϣ
		dao.modXfjm2Bmshtjb(actionForm, request);// �޸Ĳ��������Ϣ
	}

	/**
	 * �����޸�ѧ�Ѽ���2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfjm2xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸�ѧ�Ѽ���2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addXfjs2shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn||xq", model.getXn() + model.getXq(),
				model.getBjdm(), "bj", "xfjm2", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getXq() + model.getBjdm() + "bjxfjm2", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * ѧ�Ѽ���2��ѯ��ͷ Xfjm2tit ---- ѧ�Ѽ���2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqjmje",
					"sqsj", "pzjmje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "���������",
					"����ʱ��", "��׼������", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqjmje",
					"sqsj", "pzjmje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "���������",
					"����ʱ��", "��׼������", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�Ѽ���2��ѯ��ͷ getXfjm2TitForDM ---- ѧ�Ѽ���2��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm2TitForDM(String shjb,
			String userType) throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {// ѧԺ�û�
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"bjmc", "rs", "pzjmje", "tjr", "tjsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"�༶����", "����", "��׼���", "�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {// ѧУ�û�
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xymc", "rs", "pzjmje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					Base.YXPZXY_KEY+"����", "����", "��׼���", "�ύ��", "�ύʱ��", "ѧУ���" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�Ѽ���2��ѯ��ͷ getXfjm2TitForStush ---- ѧ�Ѽ���2��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjm2TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xysh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "������", "��׼���", Base.YXPZXY_KEY+"���" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "������", "��׼���", "ѧУ���" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�Ѽ���2��ѯ��� Xfjm2res ---- ѧ�Ѽ���2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ѧ�Ѽ���2��ѯ��� getXfjm2ResByFdy ---- ѧ�Ѽ���2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm2ResByFdy(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * ѧ�Ѽ���2��ѯ��� getXfjm2ResForDM ---- ѧ�Ѽ���2(������ģʽ��ѯ)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjm2ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ѧ�Ѽ���2��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfjm2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfjm2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ѧ�Ѽ���2���� Xfjm2Exp ---- ѧ�Ѽ���2����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXfjm2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfjm2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfjm2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ����ѧ�Ѽ���2�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm2Shxx(Xfjm2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfjm2Shxx(model, request, shjb);
	}

	/**
	 * ��ȡѧ�ѻ���1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfhj1xx(pkVal);
	}

	/**
	 * ����ѧ�ѻ���1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj1Sqxx(Xfhj1Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj1Sqxx(model, request);
	}

	/**
	 * ɾ��ѧ�ѻ���1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfhj1xx(cbVal, request, shjb);
	}

	/**
	 * �����޸�ѧ�ѻ���1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfhj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ѧ�ѻ���1��ѯ��ͷ Xfhj1tit ---- ѧ�ѻ���1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjje", "hjqx",
					"sqsj", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�������", "��������",
					"����ʱ��", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjje", "hjqx",
					"sqsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�������", "��������",
					"����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�ѻ���1��ѯ��� Xfhj1res ---- ѧ�ѻ���1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ѧ�ѻ���1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfhj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfhj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ѧ�ѻ���1���� Xfhj1Exp ---- ѧ�ѻ���1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXfhj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfhj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfhj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ����ѧ�ѻ���1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj1Shxx(Xfhj1Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj1Shxx(model, request, shjb);
	}

	/**
	 * ��ȡѧ�ѻ���2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getXfhj2xx(pkVal);
	}

	/**
	 * ��ȡ���ݴ�ѧǷ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getGzdxQfxx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getGzdxQfxx(pkVal);
	}

	/**
	 * ���ù��ݴ�ѧǷ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getXsqfInfo(String xh, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		String[] colList = new String[] { "yjxfje", "sjjfje", "qxfje",
				"yjzsfje", "sjjzsfje", "qzsfje", "yjqtfyje", "sjqtfyje",
				"qjqtfyje" };
		
		List<HashMap<String, String>> list = dao.getGzdxQfxx(xh, colList);
		float sum=0.0f;
		for(int i=0;i<colList.length;i++){
			sum=0.0f;
			for(int j=0;j<list.size();j++){
				HashMap<String,String>hashMap=list.get(j);
				if(!Base.isNull(hashMap.get(colList[i]))){
					sum+=Float.parseFloat(hashMap.get(colList[i]));
				}
			}
			request.setAttribute(colList[i], sum);
		}
	}

	/**
	 * ����ѧ�ѻ���2������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj2Sqxx(Xfhj2Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj2Sqxx(model, request);
	}

	/**
	 * ɾ��ѧ�ѻ���2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delXfhj2xx(cbVal, request, shjb);
	}

	/**
	 * �����޸�ѧ�ѻ���2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modXfhj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ѧ�ѻ���2��ѯ��ͷ Xfhj2tit ---- ѧ�ѻ���2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjxf",
					"hjzsf", "hjxzfqx", "sqsj", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ѧ��",
					"����ס�޷�", "��������", "����ʱ��", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjxf",
					"hjzsf", "hjxzfqx", "sqsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ѧ��",
					"����ס�޷�", "��������", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�ѻ���2��ѯ��� Xfhj2res ---- ѧ�ѻ���2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ѧ�ѻ���2��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfhj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXfhj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ѧ�ѻ���2���� Xfhj2Exp ---- ѧ�ѻ���2����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXfhj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_xfhj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_xfhj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ѧ�ѻ���2�޹�Ƿ��������ͷ getXfhj2WgqfTit ---- ѧ�ѻ���2�޹�Ƿ������ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj2WgqfTit() throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		enList = new String[] { "xymc", "zymc", "xh", "xm", "qxfqj", "qxfzje" };
		cnList = new String[] { Base.YXPZXY_KEY, "רҵ", "ѧ��", "����", "Ƿѧ������", "Ƿѧ�ѽ��" };
		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�ѻ���2��ѧ�ѻ��ɼ��޹�Ƿ��ͳ�Ʊ�ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhj2HjtjTit() throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		enList = new String[] { "xh", "xy", "tgsqzrs", "tghjsqdfknsrs",
				"wgqfrs", "qfrs", "bz" };
		cnList = new String[] { "���", Base.YXPZXY_KEY, "ͨ������������", "ͨ����������ķ�����������",
				"�޹�Ƿ������", "Ƿ������", "��ע" };
		return makeTitList(enList, cnList);
	}

	/**
	 * ѧ�ѻ���2�޹�Ƿ��������� Xfhj2Wgqfres ---- ѧ�ѻ���2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2WgqfRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = dao.getXfhj2WgqfRes(queryModel, request,
				actionForm, shjb);
		return resList;
	}

	/**
	 * ѧ�ѻ���2��ѧ�ѻ��ɼ��޹�Ƿ��ͳ�ƽ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2HjtjRes(String xn) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = dao.getXfhj2HjtjRes(xn);
		return resList;
	}

	/**
	 * ����ѧ�ѻ���2�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj2Shxx(Xfhj2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveXfhj2Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ��������ʱ����1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnslsbz1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getKnslsbz1xx(pkVal);
	}

	/**
	 * ������������ʱ����1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsbz1Sqxx(Knslsbz1Model model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnslsbz1Sqxx(model, request);
	}

	/**
	 * ��ӡ��������ʱ����1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void knslsbz1Dy(WritableWorkbook wwb, String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<HashMap<String, String>> list = dao.getKnslsbz1Dyxx(pkVal);

		WritableSheet ws = wwb.getSheet(0);
		Label titleCell = new Label(0, 2, StandardOperation.getXxmc()
				+ "����ѧ����ʱ����������");
		WritableCellFormat wcFormat = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 18);
		wcFormat.setFont(font);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 10);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		String[] valKay = { "xymc", "bjmc", "xm", "xh", "kndj" };
		int i = 4;
		int sqzje = 0;
		for (HashMap<String, String> hs : list) {
			for (int j = 0; j < valKay.length; j++) {
				titleCell = new Label(j, i, hs.get(valKay[j]));
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
			}

			titleCell = new Label(5, i, hs.get("sqly"));
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(5, i, 6, i);

			titleCell = new Label(7, i, hs.get("sqje"));
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(7, i, 8, i);

			i++;
			sqzje += Integer.parseInt(hs.get("sqje"));
		}

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 11);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		titleCell = new Label(0, i, "�ϼƽ��");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);

		titleCell = new Label(1, i, sqzje + "    Ԫ");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);
		ws.mergeCells(1, i, 3, i);

		String[] sT = { Base.YXPZXY_KEY+"���", "ѧ����������     ���ĸ������", "ѧ�����쵼���", "ѧ�����쵼����" };
		int h = 0;
		for (int n = 0; n < sT.length; n++) {
			titleCell = new Label(0, i + h + 1, sT[n]);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(0, i + h + 1, 0, i + h + 2);
			h += 2;
		}

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 11);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.LEFT);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		titleCell = new Label(
				4,
				i,
				"�����ˣ�                                                                                  ��        ��        ��");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);
		ws.mergeCells(4, i, 8, i);

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 11);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.BOTTOM);

		for (int n = 1; n < 8;) {
			titleCell = new Label(
					1,
					i + n,
					"                                                                                                                                          ��        ��        ��");
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			ws.mergeCells(1, i + n, 8, i + n + 1);
			n += 2;
		}

		wcFormat = new WritableCellFormat();
		font = new WritableFont(WritableFont.ARIAL, 9);
		wcFormat.setFont(font);
		wcFormat.setWrap(true);
		wcFormat.setBorder(Border.TOP, BorderLineStyle.THIN);
		wcFormat.setAlignment(Alignment.LEFT);
		wcFormat.setVerticalAlignment(VerticalAlignment.BOTTOM);

		titleCell = new Label(0, i + 9, "           ��ע����"+Base.YXPZXY_KEY+"�ɸ��ݾ����������������ѧ����Ϣ��������");
		titleCell.setCellFormat(wcFormat);
		ws.addCell(titleCell);
		ws.mergeCells(0, i + 9, 8, i + 9);

		ExcelMethods.submitWritableWorkbookOperations(wwb);// ������ͻ���
	}

	/**
	 * ɾ����������ʱ����1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnslsbz1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delKnslsbz1xx(cbVal, request, shjb);
	}

	/**
	 * �����޸���������ʱ����1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnslsbz1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modKnslsbz1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ��������ʱ����1��ѯ��ͷ Knslsbz1tit ---- ��������ʱ����1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnslsbz1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "kndj", "sqje",
					"sqsj", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "���ѵȼ�",
					"���벹�����", "����ʱ��", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "kndj", "sqje",
					"sqsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "���ѵȼ�",
					"���벹�����", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ��������ʱ����1��ѯ��� Knslsbz1res ---- ��������ʱ����1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnslsbz1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnslsbz1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ��������ʱ����1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnslsbz1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnslsbz1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ��������ʱ����1���� Knslsbz1Exp ---- ��������ʱ����1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnslsbz1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knslsbz1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knslsbz1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ������������ʱ����1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsbz1Shxx(Knslsbz1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveKnslsbz1Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ��ʱ����2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbz2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getLsbz2xx(pkVal);
	}

	/**
	 * ������ʱ����2������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbz2Sqxx(Lsbz2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveLsbz2Sqxx(model, request, shjb);
	}

	/**
	 * ɾ����ʱ����2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsbz2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delLsbz2xx(cbVal, request, shjb);
	}

	/**
	 * �����޸���ʱ����2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modLsbz2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ��ʱ����2��ѯ��ͷ Lsbz2tit ---- ��ʱ����2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbz2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje",
					"sqsj", "pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "������",
					"����ʱ��", "��׼���", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje",
					"sqsj", "pzje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "������",
					"����ʱ��", "��׼���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ��ʱ����2��ѯ��ͷ Lsbz2tit ---- ��ʱ����2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbz2TitForDM(String shjb,
			String userType) throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			if ("3".equalsIgnoreCase(shjb)) {
				enList = new String[] { "disabled", "bgcolor", "pk", "xn",
						"xqmc", "bjmc", "rs", "pzje", "tjr", "tjsj", "xysh",
						"xxsh" };
				cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
						"ѧ��", "�༶����", "����", "��׼���", "�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���",
						"ѧУ���" };
			} else {
				enList = new String[] { "disabled", "bgcolor", "pk", "xn",
						"xqmc", "bjmc", "rs", "pzje", "tjr", "tjsj", "xysh",
						"xxsh" };
				cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
						"ѧ��", "�༶����", "����", "��׼���", "�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���",
						"ѧУ���" };
			}
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc",
					"xymc", "rs", "pzje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					Base.YXPZXY_KEY+"����", "����", "��׼���", "�ύ��", "�ύʱ��", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ��ʱ����2��ѯ��ͷ getLsbz2TitForStush ---- ��ʱ����2��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbz2TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "������", "�������", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xysh", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "������", "�������", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * ��ȡ��ʱ����2����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbz2shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getLsbz2shtjjg(pkVal, userType, shjb);
	}

	/**
	 * ��ȡ��ʱ����2����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getLsbz2Stush(pkVal, userType, shjb);
	}

	/**
	 * �����޸���ʱ����2��˽��(���������)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiLsbz2ForDep(XszzCommonN05ActionForm actionForm,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modLsbz2xxBybj(actionForm, request);// �޸�ѧ�������Ϣ
		dao.modLsbz2Bmshtjb(actionForm, request);// �޸Ĳ��������Ϣ
	}

	/**
	 * �����޸���ʱ����2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modLsbz2xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸���ʱ����2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addLsbz2shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn||xq", model.getXn() + model.getXq(),
				model.getBjdm(), "bj", "lsbz2", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getXq() + model.getBjdm() + "bjlsbz2", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * ��ʱ����2��ѯ��� getXfjm2ResForDM ---- ��ʱ����2(������ģʽ��ѯ)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbz2ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ��ʱ����2��ѯ��� Lsbz2res ---- ��ʱ����2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbz2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ��ʱ����2��ѯ��� getLsbz2ResByFdy ---- ��ʱ����2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbz2ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ��ʱ����2��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getLsbz2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getLsbz2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ��ʱ����2���� Lsbz2Exp ---- ��ʱ����2����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getLsbz2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_knslsbz2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_knslsbz2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ������ʱ����2�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbz2Shxx(Lsbz2Model model, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveLsbz2Shxx(model, request, shjb);
	}

	/**
	 * ������Ŀ����õ�������ѧ����Ŀ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getWszxjmc(String xmdm) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjmc(xmdm);
	}

	/**
	 * ��ȡ������ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj1xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj1xx(pkVal);
	}

	/**
	 * ����������ѧ��1������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj1Sqxx(Wszxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj1Sqxx(model, request, shjb);
	}

	/**
	 * ɾ��������ѧ��1��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delWszxj1xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delWszxj1xx(cbVal, request, shjb);
	}

	/**
	 * �����޸�������ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj1xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ������ѧ��1��ѯ��ͷ Wszxj1tit ---- ������ѧ��1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj1Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje", "sqsj",
					"pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "������", "����ʱ��",
					"��׼���", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "������", "����ʱ��",
					"��׼���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��1��ѯ��� Wszxj1res ---- ������ѧ��1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj1Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��1��ѯ��� Wszxj1res ---- ������ѧ��1
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj1ResByFdy(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��1��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getWszxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getWszxj1ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ������ѧ��1��ѯ��ͷ getWszxj1TitForDM ---- ������ѧ��1��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj1TitForDM(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc",
					"rs", "pzje", "tjr", "tjsj", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "�༶����",
					"����", "��׼���", "�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc",
					"rs", "pzje", "tjr", "tjsj", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", Base.YXPZXY_KEY+"����",
					"����", "��׼���", "�ύ��", "�ύʱ��", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��1��ѯ��ͷ getWszxj1TitForStush ---- ������ѧ��1��ѯ��ͷ(���������ģʽ)
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj1TitForStush(String userType)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};

		if ("xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xysh", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "������", "��׼���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "sqje", "pzje", "xxsh" };
			cnList = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "������", "��׼���", "ѧУ���" };
		}

		return makeTitList(enList, cnList);
	}

	/**
	 * ��ȡ������ѧ��1����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj1shtjjg(String pkVal,
			String userType, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj1shtjjg(pkVal, userType, shjb);
	}

	/**
	 * ��ȡ������ѧ��1����ύ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1Stush(String pkVal, String userType,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj1Stush(pkVal, userType, shjb);
	}

	/**
	 * �����޸�������ѧ��1��˽��(���������)
	 * 
	 * @return
	 * @throws Exception
	 */
	public void audiWszxj1ForDep(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj1xxBybj(model, request);// �޸�ѧ�������Ϣ
		dao.modWszxj1Bmshtjb(model, request);// �޸Ĳ��������Ϣ
	}

	/**
	 * �����޸�������ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1xxByDepModel(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj1xxByDepModel(cbVal, shjg, request, shjb);
	}

	/**
	 * �����޸�������ѧ��1��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean addWszxj1shtj(XszzCommonN05ActionForm model,
			HttpServletRequest request) throws Exception {
		String[] columns = { "zj", "zjz", "dm", "bm", "tjxm", "tjzt", "tjr" };
		String[] values = { "xn||zxjdm", model.getXn() + model.getZxjdm(),
				model.getBjdm(), "bj", "wszxj1", "1", model.getUserName() };
		StandardOperation.delete("xszz_com_bmshtjb", "zjz||dm||bm||tjxm", model
				.getXn()
				+ model.getZxjdm() + model.getBjdm() + "bjwszxj1", request);
		return StandardOperation.insert("xszz_com_bmshtjb", columns, values,
				request);
	}

	/**
	 * ������ѧ��1��ѯ��� getWszxj1ResForDM ---- ������ѧ��1(������ģʽ��ѯ)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj1ResForDM(queryModel, request, actionForm,
					shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��1���� Wszxj1Exp ---- ������ѧ��1����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getWszxj1Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_wszxj1", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_wszxj1");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * �õ�������ѧ�����б�
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getWszxjJeList(String zxjdm)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxjJeList(zxjdm);
	}

	/**
	 * ����������ѧ��1�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj1Shxx(Wszxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj1Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ������ѧ��2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj2xx(String pkVal) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.getWszxj2xx(pkVal);
	}

	/**
	 * ����������ѧ��2������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj2Sqxx(Wszxj2Model model, HttpServletRequest request)
			throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj2Sqxx(model, request);
	}

	/**
	 * ɾ��������ѧ��2��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delWszxj2xx(String[] cbVal, HttpServletRequest request,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.delWszxj2xx(cbVal, request, shjb);
	}

	/**
	 * �����޸�������ѧ��2��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modWszxj2xx(String[] cbVal, String shjg,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		dao.modWszxj2xx(cbVal, shjg, request, shjb);
	}

	/**
	 * ������ѧ��2��ѯ��ͷ Wszxj2tit ---- ������ѧ��2��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxj2Tit(String shjb)
			throws Exception {
		String[] enList = new String[] {};
		String[] cnList = new String[] {};
		if ("3".equalsIgnoreCase(shjb)) {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje", "sqsj",
					"pzje", "fdysh", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "������", "����ʱ��",
					"��׼���", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		} else {
			enList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh",
					"xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
					"zxjdj", "zxjje", "xysh", "xxsh" };
			cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��", "ѧ��",
					"����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "������", "����ʱ��",
					"��׼���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		}
		return makeTitList(enList, cnList);
	}

	/**
	 * ������ѧ��2��ѯ��� Wszxj2res ---- ������ѧ��2
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getWszxj2Res(queryModel, request, actionForm, shjb);
		}
		return resList;
	}

	/**
	 * ������ѧ��2��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getWszxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getWszxj2ResNum(queryModel, request, shjb);
		}
		return sT;
	}

	/**
	 * ������ѧ��2���� Wszxj2Exp ---- ������ѧ��2����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getWszxj2Exp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String shjg) throws Exception {
		dao = new XszzCommonN05DAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_com_wszxj2", request, "3"
				.equalsIgnoreCase(shjg));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_com_wszxj2");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ����������ѧ��2�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj2Shxx(Wszxj2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		dao = new XszzCommonN05DAO();
		return dao.saveWszxj2Shxx(model, request, shjb);
	}

	/**
	 * ��ȡ����Ա����ύ����Ϣ
	 * 
	 * @param String
	 *            tableName
	 * @param XszzCommonN05ActionForm
	 *            model
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getFdyshtjbxx(String xm, String bjdm,
			String zjz) {
		dao = new XszzCommonN05DAO();
		return dao.selectFdyshtjb(xm, bjdm, zjz);
	}

	/**
	 * ���ѧ��Ƿ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXsqfxxList(String userType,
			XszzCommonN05ActionForm form, String[] colList) {
		dao = new XszzCommonN05DAO();
		return dao.getXsqfxxList(userType, form, colList);
	}

	/**
	 * ��ȡ��˲�ѯ��Ҫ�Զ�����ֶ�
	 * 
	 * @param yhlx
	 * @return String
	 */
	public String getCustomAudiColumn(String yhlx) {
		StringBuilder sb = new StringBuilder();
		if (yhlx.equalsIgnoreCase("xy")) {
			sb
					.append(" (case when xxsh='δ���' then '' else 'disabled' end) disabled,");
			sb
					.append(" (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		} else if (yhlx.equalsIgnoreCase("fdy")) {
			sb
					.append(" (case when xysh='δ���' then '' else 'disabled' end) disabled, ");
			sb
					.append(" (case when nvl(fdysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		} else {
			sb.append(" '' disabled, ");
			sb
					.append(" (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}

		return sb.toString();
	}

	/**
	 * �ж��Ƿ���������
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean isKns(String xh) {
		DAO dao = DAO.getInstance();
		return dao.isKns(xh);
	}

	/**
	 * ��ȡѧ����У������ѧ��
	 * 
	 * @param xh
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getStuZxxn(String xh) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		XsxxglService service = new XsxxglService();
		HashMap<String, String> xsxx = service.selectStuinfo(xh);
		String rxsj = xsxx.get("rxrq");

		rxsj = StringUtils.isNotNull(rxsj) ? rxsj.substring(0, 4) : xsxx
				.get("nj");
		int length = 3;// �����ѧ��
		for (int i = 0; i < length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotNull(rxsj)) {
				map.put("xn", StringUtils.joinStr(rxsj, "-", (Integer
						.parseInt(rxsj) + 1)
						+ ""));
				rs.add(map);
				rxsj = (Integer.parseInt(rxsj) + 1) + "";
			}
		}

		return rs;
	}

	/**
	 * ������һְҵ����ѧԺ�ڹ���ѧ���ʱ����ӡ
	 * 
	 * @param wwb
	 * @return void
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public void printNbtyKnsrd2(QueryModel queryModel,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		dao = new XszzCommonN05DAO();
		String[] colList = { "xn", "xm", "xh", "xb", "sfzh", "xysh", "xydm",
				"xxsh", "nj", "zydm", "bjdm" };
		StringBuffer strBuffer = MakeQuery.makeQuery(colList, queryModel);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			strBuffer.append("and xydm=" + userDep);
		}
		List<Map<String, String>> list = dao.getKnsrd2(strBuffer);
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(24);
			wcfTytle.setFont(wfTytle);

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
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, 3 + i, String.valueOf(i + 1),
								wcfTytle));
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 3 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(3, 3 + i, map.get("zymc") + map.get("nj")
						+ map.get("bjmc"), wcfTytle));
				if ("��������".equalsIgnoreCase(map.get("xxsh"))) {
					ws.addCell(new Label(4, 3 + i, "��", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "", wcfTytle));
				} else if ("����".equalsIgnoreCase(map.get("xxsh"))) {
					ws.addCell(new Label(4, 3 + i, "", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "��", wcfTytle));
					ws.addCell(new Label(6, 3 + i, "", wcfTytle));
				} else if ("һ������".equalsIgnoreCase(map.get("xxsh"))) {
					ws.addCell(new Label(4, 3 + i, "", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "", wcfTytle));
					ws.addCell(new Label(6, 3 + i, "��", wcfTytle));
				} else {
					ws.addCell(new Label(4, 3 + i, "", wcfTytle));
					ws.addCell(new Label(5, 3 + i, "", wcfTytle));
					ws.addCell(new Label(6, 3 + i, "", wcfTytle));
				}
				ws.addCell(new Label(7, 3 + i, "", wcfTytle));
				ws.addCell(new Label(8, 3 + i, "", wcfTytle));
				ws.addCell(new Label(9, 3 + i, "", wcfTytle));
				ws.addCell(new Label(10, 3 + i, "", wcfTytle));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������һְҵ����ѧԺ���ҽ�ѧ��1
	 * 
	 * @param wwb
	 * @return void
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public void printNbtyGjjxj1(QueryModel queryModel,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		dao = new XszzCommonN05DAO();
		String[] colList = { "xn","xh","xm","sfzh", "nj", "xb", "zydm", "xydm", "bjdm"};
		StringBuffer strBuffer = MakeQuery.makeQuery(colList, queryModel);
		List<Map<String, String>> list = dao.getGjjxj1(strBuffer);
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
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
			ws.mergeCells(1, 0,1, 9);
			String xhx="";
			if("".equalsIgnoreCase(queryModel.getXn())){
				xhx="_________";
			}
			ws.addCell(new Label(0,1,queryModel.getXn()+xhx+"ѧ�����ͨ�ߵ�ѧУ���ҽ�ѧ���ѧ������������",wcfTytle));

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
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),
								wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("sfzh"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("xymc"),wcfTytle));
				ws.addCell(new Label(4, 4 + i, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(6, 4 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(7, 4 + i,map.get("mzmc"), wcfTytle));
				ws.addCell(new Label(8, 4 + i,map.get("rxrq"), wcfTytle));
			}
			WritableCellFormat endwcfTytle = new WritableCellFormat();
			WritableFont wf=new WritableFont(WritableFont.ARIAL);
			wf.setBoldStyle(WritableFont.BOLD);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 4+list.size(), 3, 4+list.size());
			ws.addCell(new Label(0,4+list.size(),"��ע���˱���У��д��",endwcfTytle));
			endwcfTytle = new WritableCellFormat();
			wf=new WritableFont(WritableFont.ARIAL);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 5+list.size(), 1, 5+list.size());
			ws.addCell(new Label(0,5+list.size(),"������:",endwcfTytle));
			ws.mergeCells(2, 5+list.size(), 3, 5+list.size());
			ws.addCell(new Label(2,5+list.size(),"��ϵ�绰��",endwcfTytle));
			ws.mergeCells(4, 5+list.size(), 5, 5+list.size());
			ws.addCell(new Label(4,5+list.size(),"���棺",endwcfTytle));
			ws.mergeCells(6, 5+list.size(), 7, 5+list.size());
			ws.addCell(new Label(6,5+list.size(),"�������䣺",endwcfTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������һְҵ����ѧԺ���ҽ�ѧ��1����
	 * 2010.9.21 qlj
	 * @param wwb
	 * @return void
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public void printNbtyGjjxj1hz(QueryModel queryModel,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		dao = new XszzCommonN05DAO();
		String[] colList = { "xn","xh","xm","sfzh", "nj", "xb", "zydm", "xydm", "bjdm"};
		StringBuffer strBuffer = MakeQuery.makeQuery(colList, queryModel);
		List<Map<String, String>> list = dao.getGjjxj1(strBuffer);
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(18);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(1, 0, 1, 9);
			String xhx="";
			if("".equalsIgnoreCase(queryModel.getXn())){
				xhx="_________";
			}
			ws.addCell(new Label(0,1,queryModel.getXn()+xhx+"ѧ�����ͨ�ߵ�ѧУ���ҽ�ѧ���ѧ�������������ܱ�",wcfTytle));

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
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),
								wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("sfzh"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("xymc"),wcfTytle));
				ws.addCell(new Label(4, 4+ i, map.get("zymc"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(6, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(7, 4 + i, map.get("xb"), wcfTytle));
				ws.addCell(new Label(8, 4+ i,map.get("mzmc"), wcfTytle));
				ws.addCell(new Label(9, 4 + i,map.get("rxrq"), wcfTytle));
			}
			WritableCellFormat endwcfTytle = new WritableCellFormat();
			WritableFont wf=new WritableFont(WritableFont.ARIAL);
			wf.setBoldStyle(WritableFont.BOLD);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 4+list.size(), 5, 4+list.size());
			ws.addCell(new Label(0,4+list.size(),"ע���˱������йز��š���ʡ����������ֱϽ�У����ƻ������н���������д",endwcfTytle));
			 endwcfTytle = new WritableCellFormat();
			wf=new WritableFont(WritableFont.ARIAL);
			endwcfTytle.setFont(wf);
			ws.mergeCells(0, 5+list.size(), 1, 5+list.size());
			ws.addCell(new Label(0,5+list.size(),"������:",endwcfTytle));
			ws.mergeCells(2, 5+list.size(), 3, 5+list.size());
			ws.addCell(new Label(2,5+list.size(),"��ϵ�绰��",endwcfTytle));
			ws.mergeCells(4, 5+list.size(), 5, 5+list.size());
			ws.addCell(new Label(4,5+list.size(),"���棺",endwcfTytle));
			ws.mergeCells(6, 5+list.size(), 7, 5+list.size());
			ws.addCell(new Label(6,5+list.size(),"�������䣺",endwcfTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��ѯѧ��ѧ����Ŀ���
	 * 
	 * @param xnList
	 * @param xh
	 * @param xmmc
	 * @return Map<String, String>
	 */
	public Map<String, String> queryXnje(List<HashMap<String, String>> xnList,
			String xh, String xmmc) {
		XszzCommonN05DAO dao = new XszzCommonN05DAO();
		return dao.selectXnje(xnList, xh, xmmc);
	}

	public List<HashMap<String, String>> getGjzxjdj() {
		String[] output = new String[] { "zxjdm", "zxjdj" };
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"xszz_com_gjzxj1dmb", "", new String[] {}, output, "");
//		List<LabelValueBean> options = new ArrayList<LabelValueBean>();
//		
//		if(list != null){
//			for(int i=0;i<list.size();i++){
//				HashMap<String, String> map = list.get(i);
//				options.add(new LabelValueBean(map.get("zxjdj"),map.get("zxjdj")));
//			}
//		}
		
		return list;
	}
	
	/**
	 * �������������Ϣ�б�
	 * 
	 * @author luojw
	 */
	public void getLnZzInfoList(XszzCommonN05ActionForm model,
			HttpServletRequest request) {
		List<HashMap<String, String>> list = dao.getLnZzInfoList(model);

		request.setAttribute("lnzzInfo", list);
	}
	
	/**
	 * ��������������֤
	 * 
	 * @author luojw
	 * @throws SQLException 
	 */
	public String zzsqTjyz(XszzCommonN05ActionForm model) throws SQLException {

		dao = new XszzCommonN05DAO();
		
		// ������Ϣ
		String message = "";
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = model.getXn();

		String tableName = "xszz_zztjb";

		String[] colList = new String[] { "tjzd", "tjlx", "tjz" };
		String query = " where xmdm = ? ";

		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, new String[] { xmdm }, colList, "");

		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				// �����ֶ�
				String tjzd = map.get("tjzd");
				// ��������
				String tjlx = map.get("tjlx");
				// ����ֵ
				String tjz = map.get("tjz");

				// ��Υ�����
				if ("nowj".equalsIgnoreCase(tjzd) && "��".equalsIgnoreCase(tjz)) {
					boolean isWj = dao.isWj(model);

					if (isWj) {
						message = "�����˱�ѧ����Υ��������޷����룬��ȷ�ϡ�";
						return message;
					}
				}

				// �޲������Ŀ
				if ("nobjg".equalsIgnoreCase(tjzd) && "��".equalsIgnoreCase(tjz)) {

					boolean isbjg = dao.isBjg(model);

					if (isbjg) {
						message = "�����˱�ѧ���в������Ŀ���޷����룬��ȷ�ϡ�";
						return message;
					}
				}
				
				// ƽ���ɼ�
				if ("avgcj".equalsIgnoreCase(tjzd)) {

					String avgCj = dao.getAvgCj(model);

					if (!Base.isNull(avgCj)) {

						float tjcj = Float.parseFloat(tjz);
						float cj = Float.parseFloat(avgCj);
						boolean flag = false;

						if (">".equalsIgnoreCase(tjlx) && cj <= tjcj) {
							flag = true;
						} else if (">=".equalsIgnoreCase(tjlx) && cj < tjcj) {
							flag = true;
						} else if ("=".equalsIgnoreCase(tjlx) && cj != tjcj) {
							flag = true;
						} else if ("<".equalsIgnoreCase(tjlx) && cj >= tjcj) {
							flag = true;
						} else if ("<=".equalsIgnoreCase(tjlx) && cj > tjcj) {
							flag = true;
						}

						if (flag) {
							message = "�����˱�ѧ��ƽ���ɼ�δ�ﵽҪ���޷����룬��ȷ�ϡ�";
							return message;
						}
					}else{
						message = "�����˱�ѧ���޿γ̳ɼ����޷����룬��ȷ�ϡ�";
						return message;
					}
				}
				
				// ÿһ�Ƴɼ�
				if ("mincj".equalsIgnoreCase(tjzd)) {

					List<String> cjList = dao.getXsCj(model);

					if (cjList != null && cjList.size() > 0) {

						for (int j = 0; j < cjList.size(); j++) {
							
							float tjcj = Float.parseFloat(tjz);
							float cj = Float.parseFloat(cjList.get(j));
							boolean flag = false;

							if (">".equalsIgnoreCase(tjlx) && cj <= tjcj) {
								flag = true;
							} else if (">=".equalsIgnoreCase(tjlx) && cj < tjcj) {
								flag = true;
							} else if ("=".equalsIgnoreCase(tjlx) && cj != tjcj) {
								flag = true;
							} else if ("<".equalsIgnoreCase(tjlx) && cj >= tjcj) {
								flag = true;
							} else if ("<=".equalsIgnoreCase(tjlx) && cj > tjcj) {
								flag = true;
							}

							if (flag) {
								message = "�����˱�ѧ��γ̳ɼ�δ�ﵽҪ���޷����룬��ȷ�ϡ�";
								return message;
							}
						}
					}else{
						message = "�����˱�ѧ���޿γ̳ɼ����޷����룬��ȷ�ϡ�";
						return message;
					}
				}
				
				// �۲�����
				if ("zcpm".equalsIgnoreCase(tjzd)) {

					DAO tyDao = DAO.getInstance();

					// �༶����
					String bjpm = tyDao.getOneValue("view_gzdx_zhszcp", "bjpm",
							"xh||xn", xh + xn);
					
					if (!Base.isNull(bjpm)) {
						// �༶����
						String bjrs = dao.getBjrs(model);
						// ��������
						float szpm = Math.round(Float.parseFloat(bjrs)
								* Float.parseFloat(tjz) / 100);
						// ��ʵ����
						float pm = Base.isNull(bjpm) ? 0 : Float
								.parseFloat(bjpm);
						boolean flag = true;

						if (">".equalsIgnoreCase(tjlx) && pm < szpm) {
							flag = false;
						} else if (">=".equalsIgnoreCase(tjlx) && pm <= szpm) {
							flag = false;
						} else if ("=".equalsIgnoreCase(tjlx) && pm == szpm) {
							flag = false;
						} else if ("<".equalsIgnoreCase(tjlx) && pm > szpm) {
							flag = false;
						} else if ("<=".equalsIgnoreCase(tjlx) && pm >= szpm) {
							flag = false;
						}

						if (flag) {
							message = "�����˱�ѧ���۲�༶����δ�ﵽҪ���޷����룬��ȷ�ϡ�";
							return message;
						}
					}else{
						message = "�����˱�ѧ���۲��������޷����룬��ȷ�ϡ�";
						return message;
					}
				}
			}
		}

		return message;
	}
	
	/**
	 * �����Ŀ��������
	 * @param model
	 * @return String
	 * @author lr (2010.11.29)
	 * */
	public String checkXmxztj(XszzCommonN05ActionForm model){
		dao = new XszzCommonN05DAO();
		
		// ������Ϣ
		String message = "";
		// ��Ŀ����
		String xmdm = model.getXmdm();
		boolean xmkns = dao.checkZzxmIsKnsxm(xmdm);
		if(xmkns){
			DAO pDao = DAO.getInstance();
			if(!pDao.isKns(model.getXh())){
				message = "����Ŀ�������������ſ����룡";
			}
		}
		return message;
	}
}

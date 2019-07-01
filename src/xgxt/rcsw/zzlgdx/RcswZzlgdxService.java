package xgxt.rcsw.zzlgdx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭��Service</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-11-27</p>
 */
public class RcswZzlgdxService {

	RcswZzlgdxDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ɾ����У֤����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZxzmxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		dao.delZxzmxx(cbVal);
	}
	
	/**
	 * ��У֤����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxzmTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "lrsj" };
		return makeTitList(enList, StringUtils.splitStr(
				"����-ѧ��-����-�Ա�-���֤��-�꼶-ѧԺ����-רҵ����-�༶����-¼��ʱ��", "-"));
	}
	
	/**
	 * ��У֤����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxzmRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxzmRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ��У֤����Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZxzmResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getZxzmResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ��У֤������
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZxzmExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_zxzmxx", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_zxzmxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ��У֤����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxzmxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getZxzmxx(pkVal);
	}
	
	private List<HashMap<String, String>> makeTitList(String[] enList, String[] cnList) {
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
	 * ������У֤����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxzmxx(ZxzmModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveZxzmxx(model, request);
	}
	
	/**
	 * ɾ������(��)��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delCgjxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.delCgjxx(cbVal);
	}
	
	/**
	 * ����(��)��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCgjTit() throws Exception {
		String[] enList = new String[] { "disabled", "pk", "xh", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "sqqx", "sqsj", "xysh", "xxsh" };
		return makeTitList(enList, StringUtils.splitStr(
				"disabled-����-ѧ��-����-�Ա�-�꼶-ѧԺ����-רҵ����-�༶����-����ȥ��-����ʱ��-ѧԺ���-ѧУ���", "-"));
	}
	
	/**
	 * ����(��)��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCgjRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm,
			boolean b) throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getCgjRes(queryModel, request, actionForm, b);
		}
		return resList;
	}
	
	/**
	 * ����(��)��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getCgjResNum(QueryModel queryModel,
			HttpServletRequest request, boolean b) throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getCgjResNum(queryModel, request, b);
		}
		return sT;
	}
	
	/**
	 * ����(��)��ѯ��ͷ-ѧ��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCgjStuTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "sqqx", "cgrq",
				"fhrq", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
		return makeTitList(enList, StringUtils.splitStr(
				"����-ѧ��-����-����ȥ��-��������-��������-����ʱ��-ѧԺ���-ѧԺ������-ѧУ���-ѧУ������", "-"));
	}
	
	/**
	 * ����(��)��ѯ���-ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCgjStuRes(String xh)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = dao.getCgjStuRes(xh);
		return resList;
	}
	
	/**
	 * ����(��)����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getCgjExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_cgjzm", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_cgjzm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ����(��)����
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getCgjShExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpShDate(queryModel, "view_rcsw_zzlgdx_cgjzm", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_cgjzm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ����(��)��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCgjxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getCgjxx(pkVal);
	}
	
	/**
	 * �������(��)������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveCgjSqxx(CgjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveCgjSqxx(model, request);
	}
	
	/**
	 * �����޸ĳ���(��)��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modCgjxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.modCgjxx(cbVal, shjg, request);
	}
	
	/**
	 * �������(��)�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveCgjShxx(CgjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveCgjShxx(model, request);
	}

	/**
	 * ɾ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delQjxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.delQjxx(cbVal);
	}
	
	/**
	 * ��ٲ�ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQjTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "qjlx", "sqsj", "shjg" };
		return makeTitList(enList, StringUtils.splitStr(
				"����-ѧ��-����-�Ա�-�꼶-ѧԺ����-רҵ����-�༶����-�������-����ʱ��-��˽��", "-"));
	}
	
	/**
	 * ��ٲ�ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getQjRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getQjRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * �����Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getQjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getQjResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ��ٲ�ѯ��ͷ-ѧ��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQjStuTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "qjlx", "qjksrq",
				"qjjzrq", "sqsj", "shjg", "shyj" };
		return makeTitList(enList, StringUtils.splitStr(
				"����-ѧ��-����-�������-��ٿ�ʼ����-��ٽ�ֹ����-����ʱ��-��˽��-������", "-"));
	}
	
	/**
	 * ��ٲ�ѯ���-ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getQjStuRes(String xh)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = dao.getQjStuRes(xh);
		return resList;
	}
	
	/**
	 * ��ٵ���
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getQjExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_qjsqb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_qjsqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQjxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getQjxx(pkVal);
	}
	
	/**
	 * �������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveQjSqxx(QjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveQjSqxx(model, request);
	}
	
	/**
	 * �����޸������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modQjxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.modQjxx(cbVal, shjg, request);
	}
	
	/**
	 * ������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveQjShxx(QjModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveQjShxx(model, request);
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delRwxx(String[] cbVal)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.delRwxx(cbVal);
	}
	
	/**
	 * �����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRwTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "sqsj", "shjg" };
		return makeTitList(enList, StringUtils.splitStr(
				"����-ѧ��-����-�Ա�-�꼶-ѧԺ����-רҵ����-�༶����-����ʱ��-��˽��", "-"));
	}
	
	/**
	 * �����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRwRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getRwRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ������Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getRwResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getRwResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * �����ѯ��ͷ-ѧ��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRwStuTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "sj", "sqsj",
				"shjg", "shyj" };
		return makeTitList(enList, StringUtils.splitStr(
				"����-ѧ��-����-�ֻ�-����ʱ��-��˽��-������", "-"));
	}
	
	/**
	 * �����ѯ���-ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRwStuRes(String xh)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		List<String[]> resList = dao.getRwStuRes(xh);
		return resList;
	}
	
	/**
	 * ���鵼��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getRwExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_rcsw_zzlgdx_rwxx", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_rcsw_zzlgdx_rwxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRwxx(String pkVal) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.getRwxx(pkVal);
	}
	
	/**
	 * ��������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveRwSqxx(RwModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveRwSqxx(model, request);
	}
	
	/**
	 * �����޸�������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modRwxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.modRwxx(cbVal, shjg, request);
	}
	
	/**
	 * �������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveRwShxx(RwModel model,
			HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		return dao.saveRwShxx(model, request);
	}
	
	
	public void setList(HttpServletRequest request,String flg) {
		DAO dao = DAO.getInstance();
		
		if ("cxlx".equalsIgnoreCase(flg)) {
			//��������
			List<HashMap<String, String>> cxlxList = dao.getWhList("rcsw_cxlx", "dm", "mc", "", "", "");
			request.setAttribute("cxlxList", cxlxList);
		} else {
			
			List<HashMap<String, String>> xmmcList = dao.getWhList("rcsw_zxzm_xmdmb", "dm", "mc", "", "", "");
			request.setAttribute("xmmcList", xmmcList);
			
			List<HashMap<String, String>> shlxList = getSelectList("shlx");
			request.setAttribute("shlxList", shlxList);
			
			List<HashMap<String, String>> shztList = getSelectList("shzt");
			request.setAttribute("shztList", shztList);
		}
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
	}
	
	
	
	public List<HashMap<String, String>> getSelectList(String lx) {
		DAO dao = DAO.getInstance();
		String[] dm = null;
		String[] mc = null;
		if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "Ժ��", "У��" };
			mc = new String[] { "Ժ��", "У��" };
		} else if ("shzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ���", "��ͨ��","��ͨ��" };
			mc = new String[] { "δ���", "��ͨ��" ,"��ͨ��"};
		}
		return dao.arrayToList(dm, mc);
	}
	
	
	/**
	 * ��ȡ���ֶ�
	 * @param realTable
	 * @param myForm
	 */
	public void getBdZd(String realTable, RcswZzlgdxActionForm myForm) {
		dao = new RcswZzlgdxDAO();
		
		List<HashMap<String, String>> list = dao.getBdZd(realTable);
		HashMap<String, ArrayList<HashMap<String, String>>> opslist = dao.getOps(realTable);
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
	
	
	public boolean saveData(String realTable, String[] colList, String pkV,
			ZxzmModel model, HttpServletRequest request) throws Exception {
		dao = new RcswZzlgdxDAO();
		
		boolean updata = dao.saveData(realTable, colList, model, request);
		
		if (updata) {
			updata = dao.updataZdyDdData(realTable, pkV, request);
		}
		return updata;
	}
	
	
	/**
	 * ��ȡ�Զ����ֶ��б�
	 * @param tableName
	 * @param xn
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> zdyColList(String tableName) throws SQLException {
		dao = new RcswZzlgdxDAO();
		return dao.zdyColList(tableName);
	}
	
	
	
	/**
	 * ����Զ����ֶβ�ѯ��ͷ
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
	 * @param tableName
	 * @param model
	 * @param colList
	 * @param zdyCol
	 * @param realTable
	 * @param pk
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getDataList(String tableName, ZxzmModel model,
			String[] colList, String[] zdyCol, String realTable, String[] pk)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		dao = new RcswZzlgdxDAO();
		
		return dao.getZdyQueryList(tableName, model, colList, zdyCol,realTable, pk);
	}
	
	
	/**
	 * ����ɾ��
	 * @param realTable
	 * @param pkV
	 * @param pk
	 * @return
	 * @throws SQLException
	 */
	public boolean delData(String realTable, String[] pkV, String pk)
			throws SQLException {
		dao = new RcswZzlgdxDAO();
		return dao.delData(realTable, pkV, pk);
	}
	
	
	public boolean updateZxzm(SaveForm form, ZxzmModel model) throws Exception {
		DAO dao = DAO.getInstance();
		
		return dao.updateData(form, model);
	}
	
	
	/**
	 * ��������
	 * @param tableName
	 * @param realTable
	 * @param colList
	 * @param zdyzdList
	 * @param pkCol
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> getOneData(String tableName,
			String realTable, String[] colList, String[] zdyzdList,
			String pkCol, String pk) {
		dao = new RcswZzlgdxDAO();
		
		return dao.getOneData(tableName, realTable, colList, zdyzdList, pkCol,
				pk);
	}
	
	
	
	/**
	 * ���º��Զ����ֶ�����
	 * @param realTable
	 * @param pk
	 * @param model
	 * @param pkValue
	 * @param colList
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(String realTable, String pk, ZxzmModel model,
			String pkValue, String[] colList, HttpServletRequest request)
			throws Exception {
		dao = new RcswZzlgdxDAO();
		
		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.updateNolog(realTable, colList,
				inputList, pk, pkValue);
		
		if (updata) {
			updata = dao.updataZdyDdData(realTable, pkValue,	request);
		}
		return updata;
	}
}

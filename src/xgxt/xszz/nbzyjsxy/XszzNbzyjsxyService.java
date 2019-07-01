package xgxt.xszz.nbzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ
 * Description: ����ְҵ����ѧԺѧ������Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-07-13
 */
public class XszzNbzyjsxyService {
	
	private static String JKRXX_TIT = "����-����-��ɫ-���֤��-��λ-����-����ܽ��";
	private static String JKRXYTJXX_TIT = Base.YXPZXY_KEY+"-�������-����˴�-����ܽ��";
	private static String JKRZYTJXX_TIT = "רҵ-�������-����˴�-����ܽ��";
	private static String JKRJSTJXX_TIT = "��ɫ-�������-����˴�-����ܽ��";
	private static String SPLIT_TYPE = "-";

	XszzNbzyjsxyDAO dao = null;// ���ݲ���DAO
	
	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String pkVal) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getStu(pkVal);
	}
	
	/**
	 * ��ȡ�����Ϊѧ�����û������Ϣ
	 * 
	 * @param xhyhm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByXhyhm(String xhyhm) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrxxByXhyhm(xhyhm);
	}
	
	/**
	 * ��ȡ����������Ϣ
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByGuid(String guid) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrxxByGuid(guid);
	}
	
	/**
	 * ��ȡ����������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJkrxxByModel(JkrxxModel model) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrxxByModel(model);
	}
	
	/**
	 * ɾ���������Ϣ
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delJkrxx(String[] pkT,HttpServletRequest request){
		dao = new XszzNbzyjsxyDAO();
		dao.delJkrxx(pkT, request);
	}

	/**
	 * �������Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJkrTit() throws Exception {
		String[] enList = new String[] { "pkT", "xm", "jsmc",
				"sfzh", "dw", "bm", "jkje" };
		return makeTitList(enList, StringUtils.splitStr(JKRXX_TIT,SPLIT_TYPE));
	}
	
	/**
	 * �������Ϣͳ�Ʊ�ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJkrTjTit(String tjlx) throws Exception {
		String[] enList = new String[] { "bm", "rs", "cs", "zje" };
		String titleStr = null;
		//"����-�������-����˴�-����ܽ��"
		if ("1".equalsIgnoreCase(tjlx)) {//ѧԺ
			titleStr = JKRXYTJXX_TIT;
		} else if ("2".equalsIgnoreCase(tjlx)) {//��ɫ
			titleStr = JKRJSTJXX_TIT;
		} else if ("3".equalsIgnoreCase(tjlx)) {//רҵ
			titleStr = JKRZYTJXX_TIT;
		} else {
			//ռ��
		}
		return makeTitList(enList, StringUtils.splitStr(titleStr,SPLIT_TYPE));
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
	 * �������Ϣ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJkrRes(QueryModel queryModel,HttpServletRequest request,XszzNbzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkrRes(queryModel,request,actionForm);
	}
	
	/**
	 * �����ͳ�ƽ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJkrTjRes(QueryModel queryModel)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJkrTjRes(queryModel);
		}
		return resList;
	}
	
	/**
	 * �������Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getJkrxxResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getJkrxxResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * �������Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getJkrxxExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nbzy_syjj_jkrxxb",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nbzy_syjj_jkrxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ����˾���¼���
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJkjlList(String pkVal) {
		dao = new XszzNbzyjsxyDAO();
		return dao.getJkjlList(pkVal);
	}
	
	/**
	 * ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJkrxx(JkrxxModel model, String act,
			HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveJkrxx(model, act, request);
	}
	
	/**
	 * �������¼
	 * @param param
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public boolean saveJklu(List<HashMap<String, String>> param,
			String guid) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveJklu(param, guid);
	}
	
	/**
	 * ��ȡ˼Դ������Ϣ
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getSyjjxxByGuid(String guid) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getSyjjxxByGuid(guid);
	}
	
	/**
	 * ����˼Դ����������Ϣ
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSyjjxx(SyjjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveSyjjxx(model, request);
	}
	
	/**
	 * ��ȡ˼Դ������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getSyjjxxByModel(SyjjModel model) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getSyjjxxByModel(model);
	}
	
	/**
	 * ɾ��˼Դ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXyjj(String[] pkT, HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		dao.delXyjj(pkT, request);
	}
	
	/**
	 * �����޸�˼Դ������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXyjj(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		dao.modXyjj(pkT, shjg, request);
	}
	
	/**
	 * ˼Դ������Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXyjjTit() throws Exception {
		String[] enList = new String[] { "bgcolor", "pkT", "xh", "xm", "sqje",
				"sqsj", "spje", "bjrsh", "xysh", "xxsh", "jkcs", "jkzje", "jkye" };
		return makeTitList(enList, StringUtils.splitStr(
								"bgcolor-����-ѧ��-����-������-����ʱ��-�������-���������-ϵ���-ѧУ���-������-����ܽ��-������",
								SPLIT_TYPE));
	}
	
	/**
	 * ˼Դ������Ϣͳ�Ʊ�ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXyjjTjTit(String userType) throws Exception {
		String[] enList = new String[] { "bm", "zs", "zje" };
		String titleStr = null;
		if ("bjr".equalsIgnoreCase(userType)) {
			titleStr = "�༶-����˴�-�ܽ��";
		} else if ("xy".equalsIgnoreCase(userType)) {
			titleStr = "רҵ-����˴�-�ܽ��";
		} else {
			titleStr = Base.YXPZXY_KEY+"-����˴�-�ܽ��";
		}
		return makeTitList(enList, StringUtils.splitStr(titleStr, SPLIT_TYPE));
	}
	
	/**
	 * ˼Դ������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXyjjRes(QueryModel queryModel,HttpServletRequest request,XszzNbzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXyjjRes(queryModel,request,actionForm);
		}
		return resList;
	}
	
	/**
	 * ˼Դ����ͳ�ƽ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXyjjTjRes(String userType,String userDep,ArrayList<String> userBj)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.getXyjjTjRes(userType,userDep,userBj);
	}
	
	/**
	 * ˼Դ�����ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXyjjResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNbzyjsxyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXyjjResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * ˼Դ������Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getSyjjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nbzyjsxy_syjj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nbzyjsxy_syjj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ����˼Դ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveSyjjShxx(SyjjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNbzyjsxyDAO();
		return dao.saveSyjjShxx(model, request);
	}

	/**
	 * ���ѧ�����˼Դ�����¼
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyjjList(String xh) {
		dao = new XszzNbzyjsxyDAO();
		return dao.getSyjjList(xh);
	}
}

package xgxt.jygl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.CommanForm;
import xgxt.jygl.dao.ZgldgxxyDao;
import xgxt.jygl.model.JyglModel;

/**
 * The Class�й��Ͷ���ϵѧԺservice.
 */
public class ZgldgxxyService {
	
	ZgldgxxyDao dao ;
	public ZgldgxxyService(){
		dao = new ZgldgxxyDao();
	}
	/**
	 * Meth ���õ���ֵ����ȥ.
	 * 
	 * @param form JyglForm����
	 * 
	 * @return void
	 */
	public HashMap<String, String> map_back(CommanForm form) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xymc", DealString.toGBK(form.getXymc()));
		map.put("xyjbqk", DealString.toGBK(form.getXyjbqk()));
		map.put("zymc", DealString.toGBK(form.getZymc()));
		map.put("rs1", DealString.toGBK(form.getRs1()));
		map.put("pycc", DealString.toGBK(form.getPycc()));
		map.put("xz", DealString.toGBK(form.getXz()));
		map.put("xw", DealString.toGBK(form.getXw()));
		map.put("pymb", DealString.toGBK(form.getPymb()));
		map.put("zykc", DealString.toGBK(form.getZykc()));
		map.put("jyqj", DealString.toGBK(form.getJyqj()));
		map.put("zydm", DealString.toGBK(form.getZydm()));
		map.put("xydm", DealString.toGBK(form.getXydm()));
		map.put("xh", DealString.toGBK(form.getXh()));
		map.put("bmxm", DealString.toGBK(form.getBmxm()));
		map.put("xm", DealString.toGBK(form.getXm()));
		map.put("xb", DealString.toGBK(form.getXb()));
		map.put("syd", DealString.toGBK(form.getSyd()));
		map.put("bkqx", DealString.toGBK(form.getBkqx()));
		map.put("lxfs", DealString.toGBK(form.getLxfs()));
		map.put("bz", DealString.toGBK(form.getBz()));
		map.put("xb1", DealString.toGBK(form.getXb1()));
		map.put("xxsh", DealString.toGBK(form.getXxsh()));
		map.put("xydm", DealString.toGBK(form.getXydm()));
		map.put("zplx", DealString.toGBK(form.getZplx()));
		map.put("zpcs", DealString.toGBK(form.getZpcs()));
		map.put("dwsl", DealString.toGBK(form.getDwsl()));
		map.put("gwsl", DealString.toGBK(form.getGwsl()));
		map.put("zpsj", DealString.toGBK(form.getZpsj()));
		map.put("xh", DealString.toGBK(form.getXh()));
		map.put("xm", DealString.toGBK(form.getXm()));
		map.put("xb1", DealString.toGBK(form.getXb1()));
		map.put("xydm", DealString.toGBK(form.getXydm()));
		map.put("zydm", DealString.toGBK(form.getZydm()));
		map.put("ky", DealString.toGBK(form.getKy()));
		map.put("bkgwy", DealString.toGBK(form.getBkgwy()));
		map.put("szyf", DealString.toGBK(form.getSzyf()));
		map.put("xbjh", DealString.toGBK(form.getXbjh()));
		map.put("zgz", DealString.toGBK(form.getZgz()));
		map.put("zzcy", DealString.toGBK(form.getZzcy()));
		map.put("zgzs", DealString.toGBK(form.getZgzs()));
		map.put("bynd", form.getBynd());
		map.put("gzjcs", DealString.toGBK(form.getGzjcs()));
		map.put("zywt", DealString.toGBK(form.getZywt()));
		map.put("jyxsfx", DealString.toGBK(form.getJyxsfx()));
		map.put("lxr", DealString.toGBK(form.getLxr()));
		map.put("lxdh", DealString.toGBK(form.getLxdh()));
		map.put("tbsm", DealString.toGBK(form.getTbsm()));
		map.put("nd", DealString.toGBK(form.getNd()));
		map.put("xsxh", DealString.toGBK(form.getXsxh()));
		map.put("name", DealString.toGBK(form.getName()));
		map.put("xymc", DealString.toGBK(form.getXymc()));
		map.put("bynf", form.getBynf());
		map.put("byyf", form.getByyf());
		map.put("bmbynd", form.getBmbynd());
		return map;
	}	
	
	/**
	 * Method תGBK.
	 * 
	 * @param form JyglForm����
	 * 
	 * @return void
	 */
	public HashMap<String, String> yxjzyjs_add(CommanForm form) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xymc", DealString.toGBK(form.getXymc()));
		map.put("xyjbqk", DealString.toGBK(form.getXyjbqk()));
		map.put("zymc", DealString.toGBK(form.getZymc()));
		map.put("rs1", DealString.toGBK(form.getRs1()));
		map.put("pycc", DealString.toGBK(form.getPycc()));
		map.put("xz", DealString.toGBK(form.getXz()));
		map.put("xw", DealString.toGBK(form.getXw()));
		map.put("pymb", DealString.toGBK(form.getPymb()));
		map.put("zykc", DealString.toGBK(form.getZykc()));
		map.put("jyqj", DealString.toGBK(form.getJyqj()));
		map.put("zydm", DealString.toGBK(form.getZydm()));
		map.put("xydm", DealString.toGBK(form.getXydm()));
		return map;
	}	
	/** 
	 * Method Ժϵ��רҵ���� ת��
	 * @param form JyglForm����
	 * @return void
	 */
	public void yxjzyjs_toGBK(CommanForm form) {
		form.setXymc(DealString.toGBK(form.getXymc()));
		form.setXyjbqk(DealString.toGBK(form.getXyjbqk()));
		form.setZymc(DealString.toGBK(form.getZymc()));
		form.setRs1(DealString.toGBK(form.getRs1()));
		form.setPycc(DealString.toGBK(form.getPycc()));
		form.setXz(DealString.toGBK(form.getXz()));
		form.setXw(DealString.toGBK(form.getXw()));
		form.setPymb(DealString.toGBK(form.getPymb()));
		form.setZykc(DealString.toGBK(form.getZykc()));
		form.setJyqj(DealString.toGBK(form.getJyqj()));
		form.setZydm(DealString.toGBK(form.getZydm()));
		form.setXydm(DealString.toGBK(form.getXydm()));
	}	
	/**
	 * ��ѯ��ͷ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	public List<HashMap<String, String>> queryTitle() throws Exception {
		String[] enList = new String[]{"pk","r","xymc","zymc","rs1","pycc","xz","xw","xxsh"};
		String[] cnList = new String[]{"pk","�к�",Base.YXPZXY_KEY+"����", "רҵ����","����", "�������", "ѧ��","ѧλ","ѧУ���"};
		return dao.getTitle(enList, cnList);
	}
	public List<HashMap<String, String>> querybysjytjTitle() throws Exception {
		String[] enList = new String[]{"pk","r","xsxh","name","xb","id","csrq","zzmm","sydq","lxdh","sjhm"};
		String[] cnList = new String[]{"pk","�к�","ѧ��", "����","�Ա�", "���֤��", "��������","������ò","��Դ����","��ϵ�绰","�ֻ�����"};
		return dao.getTitle(enList, cnList);
	}
	public List<HashMap<String, String>> bysbmTitle(String bkxm) throws Exception {
		String[] enList = new String[]{"pk","xm","xb","xydm","zydm","syd","bkqx","lxfs","bz"};
		String[] cnList;
		if("����".equals(bkxm)){
			cnList = new String[]{"pk","����","�Ա�", Base.YXPZXY_KEY+"����", "רҵ����","��Դ��","����ѧУ","��ϵ��ʽ","��ע"};
		}else if("���".equals(bkxm)){
			cnList = new String[]{"pk","����","�Ա�",  Base.YXPZXY_KEY+"����", "רҵ����","��Դ��","��������","��ϵ��ʽ","��ע"};
		}else if("��������".equals(bkxm)){
			cnList = new String[]{"pk","����","�Ա�",  Base.YXPZXY_KEY+"����", "רҵ����","��Դ��","��������","��ϵ��ʽ","��ע"};
		}else{
			cnList = new String[]{"pk","����","�Ա�",  Base.YXPZXY_KEY+"����", "רҵ����","��Դ��","�������","��ϵ��ʽ","��ע"};
		}
		return dao.getTitle(enList, cnList);
	}
	public List<HashMap<String, String>> jyjzqkTitle() throws Exception {
		String[] enList = new String[]{"pk","lxr","lxdh"};
		String[] cnList = new String[]{"pk","��ϵ��", "��ϵ�绰"};
		return dao.getTitle(enList, cnList);
	}
	public List<HashMap<String, String>> jyyxTitle() throws Exception {
		String[] enList = new String[]{"xh","xm","xb1","ky","bkgwy","szyf","xbjh","zgz","zzcy","zgzs"};
		String[] cnList = new String[]{"ѧ��","����","�Ա�","����","��������Ա","��֧һ��","�����ƻ�","�ҹ���","������ҵ","������רҵ�ʸ�֤��"};
		return dao.getTitle(enList, cnList);
	}
	public List<HashMap<String, String>> zphcsTitle() throws Exception {
		String[] enList = new String[]{"pk","xydm","zplx","zpcs","dwsl","gwsl","zpsj"};
		String[] cnList = new String[]{"pk", Base.YXPZXY_KEY+"����", "��Ƹ���","��Ƹ����","��λ����", "��λ����", "��Ƹʱ��"};
		return dao.getTitle(enList, cnList);
	}
	public HashMap<String, String> getTopTr() throws Exception {
		String[] enList = new String[]{"xh","xm","xb1","ky","bkgwy","szyf","xbjh","zgz","zzcy","zgzs","jyyx","gjj","bjs","dfss","wd","bj"};
		String[] cnList = new String[]{"ѧ��","����","�Ա�","����","��������Ա","��֧һ��","�����ƻ�","�ҹ���","������ҵ","������רҵ�ʸ�֤��","��ҵ����","���Ҽ�","������","�ط�ʡ��","���","����"};
		return dao.getTopTr(enList, cnList);
	}
	/**
	 * ��ѯ��ҳ��service
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public int queryListNum(JyglModel model,HttpServletRequest request) throws Exception {
		return dao.queryListNum(model,request);
	}
	public int querybysjytjNum(JyglModel model,HttpServletRequest request) throws Exception {
		return dao.querybysjytjNum(model,request);
	}
	public int jyyxdcListNum(JyglModel model,HttpServletRequest request) throws Exception {
		return dao.jyyxdcListNum(model,request);
	}
	public int bysbmListNum(JyglModel model,HttpServletRequest request) throws Exception {
		return dao.bysbmListNum(model,request);
	}
	public int jyqkbsListNum(JyglModel model,HttpServletRequest request) throws Exception {
		return dao.jyqkbsListNum(model,request);
	}
	public int zphcsListNum(JyglModel model,HttpServletRequest request) throws Exception {
		return dao.zphcsListNum(model,request);
	}
	/** 
	 * Method ����
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public boolean yxjzyjs_save(JyglModel model,HttpServletRequest request) throws Exception {
		boolean bool = dao.addXyjzyjs(model, request);
		return bool;
	}	
	public boolean bysjytj_save(JyglModel model,HttpServletRequest request) throws Exception {
		boolean bool = dao.addBysjytjb(model, request);
		return bool;
	}	
	public boolean addJyyxdx(JyglModel model,HttpServletRequest request) throws Exception {
		boolean bool = dao.addJyyxdx(model, request);
		return bool;
	}	
	public boolean bysbm_save(JyglModel model,HttpServletRequest request) throws Exception {
		boolean bool = dao.addBysbm(model, request);
		return bool;
	}	
	public boolean Jyjzqkbs_save(JyglModel model,HttpServletRequest request) throws Exception {
		boolean bool = dao.addJyjzqkbs(model, request);
		return bool;
	}	
	/** 
	 * Method �޸�
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public boolean yxjzyjs_modify(JyglModel model,String pk,HttpServletRequest request) throws Exception {
		boolean bool = dao.yxjzyjs_modify(model,pk,request);
		return bool;
	}	
	public boolean bysjytj_modify(JyglModel model,String pk,HttpServletRequest request) throws Exception {
		boolean bool = dao.bysjytj_modify(model,pk,request);
		return bool;
	}	
	public boolean bysbm_modify(JyglModel model,String pk,HttpServletRequest request) throws Exception {
		boolean bool = dao.bysbm_modify(model,pk,request);
		return bool;
	}	
	public boolean jyjzqk_modify(JyglModel model,String pk,HttpServletRequest request) throws Exception {
		boolean bool = dao.jyjzqk_modify(model,pk,request);
		return bool;
	}
	public boolean zphcs_modify(JyglModel model,String pk,HttpServletRequest request) throws Exception {
		boolean bool = dao.zphcs_modify(model,pk,request);
		return bool;
	}	
	/** 
	 * Method ��ѯ
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public ArrayList<String[]> getQueryList(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<String[]> rs = dao.getQueryList(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<String[]> getJybjbList(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<String[]> rs = dao.getJybjbList(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<String[]> getjyyxdcList(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<String[]> rs = dao.getjyyxdcList(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<HashMap<String, String>> getjyyxdcListtj(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<HashMap<String, String>> rs = dao.getjyyxdcListtj(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<HashMap<String, String>> getjyjzqkbsbListtj(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<HashMap<String, String>> rs = dao.getjyjzqkbsbListtj(model, dataSearchForm,request);
		return rs;
	}	
	public HashMap<String, String> getjyyxdchjListtj(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		HashMap<String, String> rs = dao.getjyyxdchjListtj(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<HashMap<String, String>> getjykndzyzyListtj(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<HashMap<String, String>> rs = dao.getjykndzyzyListtj(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<String[]> getBysbmList(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<String[]> rs = dao.getBysbmList(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<String[]> getJyjzqkbsList(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<String[]> rs = dao.getJyjzqkbsList(model, dataSearchForm,request);
		return rs;
	}	
	public ArrayList<String[]> getzhhcsList(JyglModel model, CommanForm dataSearchForm,HttpServletRequest request) throws Exception {
		ArrayList<String[]> rs = dao.getzhhcsList(model, dataSearchForm,request);
		return rs;
	}	
	/** 
	 * Method �鿴��Ϣ
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public ArrayList<HashMap<String, String>> getViewList(String pk) throws Exception {
		ArrayList<HashMap<String, String>> rs = dao.getViewList(pk);
		return rs;
	}	
	public ArrayList<HashMap<String, String>> getjytjViewList(String pk) throws Exception {
		ArrayList<HashMap<String, String>> rs = dao.getjytjViewList(pk);
		return rs;
	}	
	public ArrayList<HashMap<String, String>> getBysbmViewList(String pk) throws Exception {
		ArrayList<HashMap<String, String>> rs = dao.getBysbmViewList(pk);
		return rs;
	}	
	public ArrayList<HashMap<String, String>> getJyjzqkViewList(String pk) throws Exception {
		ArrayList<HashMap<String, String>> rs = dao.getJyjzqkViewList(pk);
		return rs;
	}	
	/** 
	 * Method ɾ��
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public boolean getDelList(String pk,HttpServletRequest request) throws Exception {
		boolean bool = dao.getDelList(pk, request);
		return bool;
	}	
	public boolean getDelBysbmList(String pk,HttpServletRequest request) throws Exception {
		boolean bool = dao.getDelBysbmList(pk, request);
		return bool;
	}	
	/** 
	 * Method ����ɾ��
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public String getAllDelList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAllDelList(pks, request);
		return whichpk;
	}
	public String getAllbysjytjDelList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAllbysjytjDelList(pks, request);
		return whichpk;
	}
	public String getAlljyyxdcList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAlljyyxdcList(pks, request);
		return whichpk;
	}
	public String getAllDelBysbmList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAllDelBysbmList(pks, request);
		return whichpk;
	}
	public String getAllDelJyjzqkList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAllDeljyjzqkbsList(pks, request);
		return whichpk;
	}
	public String getAllDelzphcsList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAllDelzphcsList(pks, request);
		return whichpk;
	}
	/** 
	 * Method ѧУ���
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public HashMap<String, String> getAuditingList(String pk,HttpServletRequest request) throws Exception {
		HashMap<String, String> map = dao.getAuditingList(pk, request);
		return map;
	}	
	/** 
	 * Method �������
	 * @param form JyglForm����
	 * @return void
	 * @throws Exception 
	 */
	public String getAllAuditingList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAllAuditingList(pks, request);
		return whichpk;
	}	
	//����
	public void getExpList(JyglModel model,
			CommanForm dataSearchForm, HttpServletResponse response) throws Exception {
		dao.getExpList(model,dataSearchForm,response);
	}	
	/** 
	 * Method getTabTopMap ��Ӣ��ת��
	 * @param String[] keys
	 * @param String[] values
	 * @return String
	 */
	public HashMap<String,String> getTabTopMap(String[] keys,String[] values){
		HashMap<String,String> map = new HashMap<String,String>();
		if(keys != null){
			for(int i=0;i<keys.length;i++){
				map.put(keys[i], values[i]);
			}
		}
		return map;
	}
	/** 
	 * Method ��ҵ��������Ϣͳ��
	 * @param String[] keys
	 * @param String[] values
	 * @return String
	 */
	public ArrayList<HashMap<String, String>> getBysjcxxtj(String xydm,String zydm,String nd){
			return dao.getBysjcxxtj(xydm, zydm, nd);
	}
	/**
	 * ������ҵ�������ͳ��
	 * @param wwb
	 * @return void	 
	 * @throws Exception 
	 * */
	public void printByxj(WritableWorkbook wwb, CommanForm myform,ArrayList<HashMap<String, String>> list,HashMap<String, String> map) throws Exception {
		dao.printJyyxdc(wwb, myform,list,map);
	}
}

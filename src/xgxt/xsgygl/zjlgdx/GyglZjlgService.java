/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:���ս�����ҵѧԺ��Ԣ����Servieces </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-7-1 ����01:20:32</p>
 */
package xgxt.xsgygl.zjlgdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.GetTime;

public class GyglZjlgService {
	
	/**
	 * ������һ������A���������
	 */
	public HashMap<String,String> getAjqssqqk_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getAjqssqqk_db(xh);
	}
	/**
	 * ����A������
	 */
	public boolean saveAjqs_ser(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.saveAjqs_db(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ��ѯ��ǰ�Ƿ�A������
	 * @throws Exception 
	 */
	public boolean isAjqs_ser(String xh) {
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.isAjqs_db(xh);
	}
	
	/**
	 * ��ѯ�Ƿ���ϱ����й�A�����ҵ�����
	 * @throws Exception 
	 */
	public boolean isAjqsCondition_ser(String xh,String lb) {
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.isAjqsCondition_db(xh,lb);
	}
	//	��ϱ�ͷ
	public List<HashMap<String,String>> getTopName(String lb){
		String[] colEn = null;
		String[] colCn = null;
		if("ajqssh".equals(lb)){
			colEn = new String[] { "pk", "xn", "xq","ssbh","ldmc","lc","qsh","lxdh","sqsj","xxsh","shsj","sfcx","cxsj" };
			colCn = new String[] { "pk", "ѧ��", "ѧ��",
						"������", "¥������", "¥��","���Һ�","��ϵ�绰","����ʱ��", "ѧУ���","���ʱ��", "�Ƿ���","����ʱ��" };
		}
		if("wmqssh".equals(lb)){
			colEn = new String[] { "pk", "xn", "xq","ssbh","ldmc","lc","qsh","lxdh","sqsj","xysh","xxsh" };
			colCn = new String[] { "pk", "ѧ��", "ѧ��",
						"������", "¥������", "¥��","���Һ�","��ϵ�绰","����ʱ��", "ѧԺ���", "ѧУ���" };
		}
		if("tsqssh".equals(lb)){
			colEn = new String[] { "pk", "xn","ssbh","ldmc","lc","qsh","lxdh","sqsj","xysh","xxsh" };
			colCn = new String[] { "pk", "ѧ��", 
						"������", "¥������", "¥��","���Һ�","��ϵ�绰","����ʱ��", "ѧԺ���", "ѧУ���" };
		}
		
		return getTabName_ser(colEn, colCn);
	}
	//��ϱ�ͷ
	public List<HashMap<String, String>> getTabName_ser(String[] en,String[] cn){
		DAO comdao = DAO.getInstance();
		return comdao.arrayToList(en, cn);
	}
	
	/**
	 * ���A�����Ҳ�ѯ
	 */
	public List<HashMap<String,String>> queryAjqsSh_ser(GyglZjlgForm form,GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryAjqsSh_db(form, model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ���A�����Ҽ�ά��
	 */
	public boolean operation_ser(GyglZjlgModel model,String doType,String pks){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		boolean flag = false;
		try {
			if("pass".equals(doType) || "nopass".equals(doType)){
				flag = dao.ajqssh_db(model, doType, pks);
			}else if("cx".equals(doType)|| "jccx".equals(doType)){
				flag = dao.ajqscx_db(pks,doType);
			}else if("autocx".equals(doType)){
				flag = dao.ajqscxAuto_db();
			}else if("dele".equals(doType)){
				flag = dao.ajqsdele_db(pks);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * �鿴��������������Ϣ
	 */
	public HashMap<String,Object> viewSqxx_ser(String pkValue,String xn,String xq){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		String ssbh = "";
		String sqsj = "";
		HashMap<String,Object> map = new HashMap<String,Object>();
		if(!Base.isNull(pkValue)){
			ssbh = pkValue.substring(0,pkValue.length()-8);
			sqsj = pkValue.substring(pkValue.length()-8);
		}
		map.putAll(dao.getQssqxx_db(pkValue)==null ? new HashMap<String,String>() 
				: dao.getQssqxx_db(pkValue));
		map.put("qscy", dao.getQscy_db(ssbh));
		map.put("qscj", dao.getTwoWeekWscj_db(ssbh, sqsj, xn, xq));
		return map;
	}
	
	/**
	 * A��������˽����ѯ
	 */
	public List<HashMap<String,String>> queryAjqsShcx_ser(GyglZjlgForm form,GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryAjqsShcx_db(form, model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * A��������˽����ѯ(ѧ��)
	 */
	public List<HashMap<String,String>> xsQueryAjqsShcx_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.xsQueryAjqsShcx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * �����������ѧԺ
	 */
	public String getQsxymc_ser(String ssbh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getQsxymc_db(ssbh);
	}
	
	/**
	 * ������һ�����������������
	 */
	public HashMap<String,String> getWmqssqqk_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getWmqssqqk_db(xh);
	}
	
	/**
	 * �����������ɫ���Ҽ�ά��
	 */
	public boolean WmAndTsOperation_ser(String tableName,String doType,String pks,String userType){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		boolean flag = false;
		try {
			if("pass".equals(doType) || "nopass".equals(doType)){
				flag = dao.wmtsqssh_db(tableName, doType, pks,userType);
			}else if("dele".equals(doType)){
				flag = dao.wmtsqsdele_db(pks,userType,tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * ������������
	 */
	public boolean saveWmqs_ser(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.saveWmqs_db(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ����������Ҳ�ѯ
	 */
	public List<HashMap<String,String>> queryWmqsSh_ser(GyglZjlgForm form,GyglZjlgModel model,String doType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryWmqsSh_db(form, model,doType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * �鿴��������������Ϣ
	 * @throws Exception 
	 */
	public HashMap<String,Object> viewWmqsSqxx_ser(String pkValue,String xn,String xq) throws Exception{
		GyglZjlgDAO dao = new GyglZjlgDAO();
		String ssbh = "";
		String sqsj = "";
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<String,String> temmap = new HashMap<String,String>();
		if(!Base.isNull(pkValue)){
			ssbh = pkValue.substring(0,pkValue.length()-8);
			sqsj = pkValue.substring(pkValue.length()-8);
		}
		map.putAll(dao.getWmqssqxx_db(pkValue)==null ? new HashMap<String,String>() 
				: dao.getWmqssqxx_db(pkValue));
		map.put("qscy", dao.getQscy_db(ssbh));
		temmap = dao.getSqwmqsCondition_db(ssbh, sqsj, xn, xq);
		map.put("qstj",temmap );
		List<String[]> cjlist = dao.getQscycj_db(ssbh);
		if(temmap.get("shsj") !=null && temmap.get("shsj").length()==8){
			map.put("zs",GetTime.getDqzs(temmap.get("shsj")));
		}	
		if(cjlist != null && cjlist.size()>0){
			map.put("cj", cjlist);
		}
		return map;
	}
	
	/**
	 * ����������˽����ѯ
	 */
	public List<HashMap<String,String>> queryWmqsShcx_ser(GyglZjlgForm form,GyglZjlgModel model,String userType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryWmqsShcx_db(form, model,userType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ����������˽����ѯ(ѧ��)
	 */
	public List<HashMap<String,String>> xsQueryWmqsShcx_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.xsQueryWmqsShcx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ɫ������˽����ѯ(ѧ��)
	 */
	public List<HashMap<String,String>> xsQueryTsqsShcx_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.xsQueryTsqsShcx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ������һ��������ɫ�������
	 */
	public HashMap<String,String> getTsqssqqk_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getTsqssqqk_db(xh);
	}
	
	/**
	 * ������ɫ����
	 */
	public boolean saveTsqs_ser(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.saveTsqs_db(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * �����ɫ���Ҳ�ѯ
	 */
	public List<HashMap<String,String>> queryTsqsSh_ser(GyglZjlgForm form,GyglZjlgModel model,String doType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryTsqsSh_db(form, model,doType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * �鿴��ɫ����������Ϣ
	 * @throws Exception 
	 */
	public HashMap<String,Object> viewTsqsSqxx_ser(String pkValue,String xn) throws Exception{
		GyglZjlgDAO dao = new GyglZjlgDAO();
		String ssbh = "";
		String sqsj = "";
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<String,String> temmap = new HashMap<String,String>();
		if(!Base.isNull(pkValue)){
			ssbh = pkValue.substring(0,pkValue.length()-8);
			sqsj = pkValue.substring(pkValue.length()-8);
		}
		map.putAll(dao.getTsqssqxx_db(pkValue)==null ? new HashMap<String,String>() 
				: dao.getTsqssqxx_db(pkValue));
		map.put("qscy", dao.getQscy_db(ssbh));
		temmap = dao.getSqtsqsCondition_db(ssbh, sqsj, xn);
		map.put("qstj",temmap );
		if(temmap.get("sqsj") !=null && temmap.get("sqsj").length()==8){
			map.put("zs",GetTime.getDqzs(temmap.get("sqsj")));
		}	
		return map;
	}
	
	/**
	 * ��ɫ������˽����ѯ
	 */
	public List<HashMap<String,String>> queryTsqsShcx_ser(GyglZjlgForm form,GyglZjlgModel model,String userType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryTsqsShcx_db(form, model,userType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ¥�㳤�����ѯ
	 */
	public ArrayList<String[]>ser_dormCadreStat(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.dao_dormCadreStat(model);
	}
	/**
	 * ¥�㳤�����ѯ��ͷ
	 */
	public List<HashMap<String,String>> getDormCadreStat(){
		String[] colEn = null;
		String[] colCn = null;
		colEn = new String[] { "r","xqmc","ldmc","cs","qsh","gyfdy","lz","cz","qsz" };
		colCn = new String[] { "�к�","У��", "¥������", "¥��","���Һ�","��Ԣ����Ա","¥��", "�㳤","���ҳ�"};
		return getTabName_ser(colEn, colCn);
	}
	/**
	 * ¥�㳤�����ѯ����
	 */
	public void ser_expDormCadreStat(WritableWorkbook wwb,GyglZjlgModel model) throws Exception {
		GyglZjlgDAO dao = new GyglZjlgDAO();
		ArrayList<String[]> vec = new ArrayList<String[]>();
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		String[] ColumnName =  new String[] { "r","xqmc","ldmc","cs","qsh","gyfdy","lz","cz","qsz" };
		String[] ColumnNameCN  = new String[] { "�к�","У��", "¥������", "¥��","���Һ�","��Ԣ����Ա","¥��", "�㳤","���ҳ�"};
		for (int m = 0; m < ColumnNameCN.length; m++) {
			ws.addCell(new Label(m, 0, ColumnNameCN[m]));
		}
		vec = dao.dao_dormCadreStat(model);
		int k = ColumnName.length;
		for (int i = 0; i < vec.size(); i++) {
			String[] tmp = (String[]) vec.toArray()[i];
			for (int j = 0; j < k; j++) {
				ws.addCell(new Label(j, i + 1, tmp[j]));
			}
		}
	}
	
	
	public String getQszBySsbh(String ssbh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		
		return dao.getQszBySsbh(ssbh); 
	}
}

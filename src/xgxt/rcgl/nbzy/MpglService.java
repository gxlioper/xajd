package xgxt.rcgl.nbzy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;
import xgxt.szdw.utils.MakeQuery;

public class MpglService {
	MpglDAO dao = new MpglDAO();
	public ArrayList<String[]> getXyxx(MpglModel myModel) {
		// TODO �Զ����ɷ������
		return null;
	}

	public ArrayList<String[]> getBysmpxxwh(MpglModel myModel) {
		// �õ���ҵ��ά����Ϣ
		String tableName = "view_xyqkyl";
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String nj = DealString.toGBK(myModel.getNj());
		String xb = DealString.toGBK(myModel.getXb());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, "", "", xb, "", xm, nj, "", "", ""));
		String[] colList = new String[] { "pk","xm","xb","grdh","xymc", "dw","gw","nsrsp"};
		ArrayList<String[]> rs = dao.commonQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}
	
	public List getBysmpxxwhTopTr() {
		// �õ���ҵ��ά����Ϣ��ͷ
		DAO dao = DAO.getInstance();
		String tableName = "view_xyqkyl";
		String[] colList = new String[] { "pk","xm","xb","grdh","xymc", "dw","gw","nsrsp"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ
		return topTr;
	}
	
	public HashMap<String, String> getBysmpOne(String pk) {
		// �õ�������ҵ��ά����ϸ��Ϣ
		String tableName = "view_xyqkyl";
		String[] colList = new String[] { "bz","djcjy","xb","dw","dwdh","grdh","gw","jtdz","nj","nsrsp","pk","sfdk","xm","xydm","xymc","zw"};
		HashMap<String, String> rs = dao.commonQueryOne(tableName, colList, "pk",
				pk);
		return rs;
	}

	public boolean updataBysmp(MpglModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// ���浥����ҵ��ά��
		String tableName = "xyqkylb";
		String pkComment = "xm||grdh";
		String[] colList = new String[] {"bz","djcjy","xb","dw","dwdh","grdh","gw","jtdz","nj","nsrsp","sfdk","xm","xydm","zw"};
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}
	
	public boolean deleteBysmpOne(String pk, HttpServletRequest request)
	throws Exception {
		// ɾ����ҵ����Ƭ��Ϣ
		String tableName = "xyqkylb";
		String pkComment = "xm||grdh";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public boolean updataXyftjm(MpglModel myModel, String pk, HttpServletRequest request) throws Exception {
		// ��ְԺ��Լ��Ϣ¼�뱣��
		String tableName = "xyftjmdjb";
		String pkComment = "fttm||ftsj";
		boolean inserted = true;
		if(pk.equalsIgnoreCase("")){
			String[] colList = new String[] {"bz","ftdd","ftjbnr","ftsj","fttm","jmfzr","sfjbf","xydm","sfjbfdh","sfjbfzw","sfjbs","sfjbsdh","sfjbszw","sfjbt","sfjbtdh","sfjbtzw","xgbyj","zbbmyj","zcr"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			inserted = StandardOperation.insert(tableName, colList, inputList,request);
		}else{
			String[] colList = new String[] {"bz","ftdd","ftjbnr","ftsj","fttm","jmfzr","sfjbf","sfjbfdh","sfjbfzw","sfjbs","sfjbsdh","sfjbszw","sfjbt","sfjbtdh","sfjbtzw","xgbyj","xxsh","xysh","zbbmyj","zcr"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			inserted = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		}
		return inserted;
	}
	
	public ArrayList<String[]> getXyftjm(MpglModel myModel) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	// ��ְԺ��Լ��Ϣ
		String tableName = "view_xyftjmdj";
		StringBuffer query = new StringBuffer(MakeQuery.makeQuery(new String[]{"xydm","xysh","xxsh"},myModel));
		String[] colList = new String[] { "pk","xymc","fttm","ftsj","ftdd", "zcr","xysh","xxsh"};
		ArrayList<String[]> rs = dao.commonQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}
	
	public List getXyftjmTopTr() {
		// �õ���ҵ��ά����Ϣ��ͷ
		DAO dao = DAO.getInstance();
		String tableName = "view_xyftjmdj";
		String[] colList = new String[] { "pk","xymc","fttm","ftsj","ftdd", "zcr","xysh","xxsh"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ
		return topTr;
	}
	
	public HashMap<String, String> getXyftjmsh(String pk) {
		//		 ��ְԺ��Լ��Ϣ�������
		String tableName = "view_xyftjmdj";
		String[] colList = new String[] { "bz","ftdd","ftjbnr","ftsj","fttm","jmfzr","pk","sfjbf","sfjbfdh","sfjbfzw","sfjbs","sfjbsdh","sfjbszw","sfjbt","sfjbtdh","sfjbtzw","xgbyj","xxsh","xydm","xymc","xysh","zbbmyj","zcr"};
		HashMap<String, String> rs = dao.commonQueryOne(tableName, colList, "pk",pk);
		return rs;
	}
	
	public boolean deleteXyftjmshOne(String pk, HttpServletRequest request)
	throws Exception {
		// ɾ�� ������ְԺ��Լ��Ϣ
		String tableName = "xyftjmdjb";
		String pkComment = "fttm||ftsj";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}
	/*
	 * ����ѧԺ�����ڹ���
	 * 
	 */
	
	//������Ϣ��ѯ
	public List<HashMap<String, String>> queryHkxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.queryHkxx(form);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//������Ϣ��ѯ�ܼ�¼��
	public String queryHkxxCount_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.queryHkxxCount(form);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//��ñ�ͷ
	public List<HashMap<String,String>> getTableTop(String lb){
		String[] ens = null;
		String[] cns = null;
		DAO dao = DAO.getInstance();
		if("xshkgl".equals(lb)){
			ens = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","hkzt"};
			cns = new String[]{"pk","ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����","����״̬"};
		}
		if("sfzbb".equals(lb)){
			ens = new String[]{"pk","xn","xq","xh","xm","xb","nj","xymc","zymc",
					           "bjmc","bbzt","fdysh"};
			cns = new String[]{"pk","ѧ��","ѧ��","ѧ��","����","�Ա�","�꼶",
					Base.YXPZXY_KEY+"����","רҵ����","�༶����","����״̬","����Ա���"};
		}
		return dao.arrayToList(ens, cns);
	}
	
	//��ñ�ͷ
	public List<HashMap<String,String>> getTableQueryTop(String lb){
		String[] ens = null;
		String[] cns = null;
		DAO dao = DAO.getInstance();
		if("xshkgl".equals(lb)){
			ens = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","hkzt"};
			cns = new String[]{"pk","ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����","����״̬"};
		}
		if("sfzbb".equals(lb)){
			ens = new String[]{"pk","xn","xq","xh","xm","xb","nj","xymc","zymc",
					           "bjmc","fdysh","bbzt","sflq","lqr","lqsj"};			
			cns = new String[]{"pk","ѧ��","ѧ��","ѧ��","����","�Ա�","�꼶",
					Base.YXPZXY_KEY+"����","רҵ����","�༶����","����Ա���",
			           "����״̬","�Ƿ���ȡ", "��ȡ��","��ȡʱ��"};
		}
		return dao.arrayToList(ens, cns);
	}
	//���ѧ���ĵĻ�����Ϣ
	public HashMap<String,String> getXsJbxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.getXsJbxx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//���ѧ���ĵĻ�����Ϣ
	public HashMap<String,String> getXsHkxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.getXsHkxx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//����ѧ���ĵĻ�����Ϣ
	public boolean saveXsHkxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.saveXsHkxx_db(form);
	}
	
	//�޸�ѧ���ĵĻ�����Ϣ
	public boolean updateXsHkxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.updateXsHkxx_db(form);
	}
	
	//ɾ��ѧ���ĵĻ�����Ϣ
	public boolean deleteXsHkxx_ser(String pkvals) {
		MpglDAO dao = new MpglDAO();
		return dao.deleteXsHkxx_db(pkvals);
	}
	
	//���ѧ���ĵĻ�����Ϣ
	public HashMap<String,String> getXsSfzJbxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.getXsSfzJbxx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//ѧ���Ƿ�Ϊ��ס����
	public boolean isCzhk_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		return dao.isCzhk_db(xh);
	}
	
	//ѧ��������������(ѧ��)
	public boolean saveXsBbsq_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.saveXsBbsq_db(form);
	}
	
	//���ѧ���ĵĻ�����Ϣ(��ʦ)
	public HashMap<String,String> getXsxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		return dao.getXsxx_db(xh);
	}
	
	//ѧ��������������(��ʦ)
	public boolean saveXsBbsqByTeacher_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.saveXsBbsqByTeacher_db(form);
	}
	
	//���ѧ���ĵ����֤������Ϣ
	public HashMap<String,String> getXsSfzbbxx_ser(String pk) {
		MpglDAO dao = new MpglDAO();
		return dao.getXsSfzbbxx_db(pk);
	}
	
	//�޸�ѧ���ĵ����֤������Ϣ
	public boolean updateXsSfzsqxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.updateXsSfzsqxx_db(form);
	}
	
	//ɾ��ѧ�����֤������Ϣ
	public boolean deleteXsSfzsqxx_ser(String pkvals) {
		MpglDAO dao = new MpglDAO();
		return dao.deleteXsSfzsqxx_db(pkvals);
	}
	
	//���֤������˲�ѯ
	public List<HashMap<String, String>> querySfzbbSh_ser(MpglForm form,String userName,String userType) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.querySfzbbSh_db(form,userName,userType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ���֤�����ѯ
	 * @param form
	 * @param userName
	 * @param userType
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> querySfzbb_ser(MpglForm form,String userName,String userType) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.querySfzbb_db(form,userName,userType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//���֤�������
	public boolean sfzbbSh_ser(String pkvals,String fdysh) {
		MpglDAO dao = new MpglDAO();
		return dao.sfzbbSh_db(pkvals, fdysh);
	}
	
	//���֤������˽����ѯ��ѧ����
	public List<HashMap<String, String>> sfzbbShxscx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		return dao.sfzbbShxscx_db(xh);
	}
	
	/**
	 * ���滧����֪
	 * */
	public boolean saveHkxz(String title, String content, HttpServletRequest request,String infoType) {
		boolean flag = false;
		String tableName = "xzb";
		try {
			flag = StandardOperation.delete(tableName, "title", title, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			flag = StandardOperation.insert(tableName, new String[]{"dm","title", "nr"}, new String[]{infoType,title,content},request);
		}		
		return flag;
	}
	
	/**
	 * ��û�����֪
	 * */
	public String getHkxz(String lb) {
		MpglDAO dao = new MpglDAO();
		return dao.getHkxz(lb);
	}
}

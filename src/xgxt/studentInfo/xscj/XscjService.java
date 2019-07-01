package xgxt.studentInfo.xscj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.ahjg.PjpyAhjgActionForm;
import xgxt.pjpy.ahjg.PjpyAhjgDAO;
import xgxt.pjpy.ahjg.PjpyAhjgXscjQryModel;
import xgxt.pjpy.comm.zhcp.zczf.ZhcpZczfForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;


/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: ����ѧԺѧ���ɼ�ά��service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: honglin
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2012-03-14
 * </p>
 */
public class XscjService extends CommService{
	
	private XscjDAO dao = null;//���ݿ����DAO
	/**
	 * ��ѯ����汾 1Ϊѧ��,0Ϊ����,����Ϊ������˾����
	 * @return
	 */
	public String getJwFlag() {
		dao = new XscjDAO();
		return dao.getJwFlag();
	}
	
	/**
	 * ���ѧ�����������б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getKsxzList() {
		dao = new XscjDAO();
		return dao.getKsxzList();
	}
	
	public List<HashMap<String, String>> getDjksmc() {
		dao = new XscjDAO();
		return dao.getDjksmc();
	}

	/**
	 * ͨ������TYPE��ѡ��ͬ��ҳ���ѯTITLE
	 * @param enCol
	 * @param cnCol
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(int iType) throws Exception {
		dao = new XscjDAO();
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();
		String[] enCol = null;
		String[] cnCol = null;
		if (iType == 1) {//�����1�����ѧ���ɼ���ѯ��ͷ
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "kcsbm","kcxz", "cj"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����","�꼶",  Base.YXPZXY_KEY+"����","�༶����","�γ�����","�γ�����","�ɼ�"};
		}//end if
		else if (iType == 2) {//�����2������༶�����ʲ�ѯ��ͷ
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"����", "bgcolor", "�к�", "ѧ��",  Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�೤����", "ѧ������", "������", "�༶������"};
		}//end if
		else if (iType == 3) {//�����3�������ٳ����ʲ�ѯ��ͷ
			enCol = new String[]{"xn||bjdm", "bgcolor", "rownum", "xn", "xq", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "zccql"};
			cnCol = new String[]{"����", "bgcolor", "�к�", "ѧ��", "ѧ��",  Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�೤����", "ѧ������", "������", "��ٳ�����"};
		}//end if
		else if (iType == 4) {//�����4������Ƚ��������ȷ����ͷ
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"�к�","�����ƺŴ���","�����ƺ�����","��ѡ����", "��ѡ�༶����"};
		}
		else if (iType == 5) {
			enCol = new String[]{"rownum","rychdm","rychmc","pxbl","bjzs"};
			cnCol = new String[]{"�к�","�����ƺŴ���","�����ƺ�����","��ѡ����", "��ѡ��������ռ����"};
		}
		else if (iType == 6) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "nj", "xymc", "bjmc", "djksmc", "ksrq", "cj"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����","�꼶",  Base.YXPZXY_KEY+"����","�༶����","�ȼ���������", "��������" , "�ɼ�"};
		}
		else if (iType == 7) {//�����1�����ѧ���ɼ���ѯ��ͷ
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz","khfs","cj","xf","pjf"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����", Base.YXPZXY_KEY+"����","�༶����","�γ�����","�γ�����","���˷�ʽ","�ɼ�","ѧ��", "�ܿ�ƽ����"};
		}
		else if (iType == 8) {//�����1�����ѧ���ɼ���ѯ��ͷ
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc","zymc", "bjmc", "kcsbm","khfs","cj","pjf"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����", Base.YXPZXY_KEY+"����","רҵ����","�༶����","�γ�����","���˷�ʽ","�ɼ�", "�ܿ�ƽ����"};
		} else if (iType==9) {
			enCol = new String[]{"xn", "xq", "xh", "xm", "xymc", "bjmc", "kcsbm","kcxz", "cj", "kscj", "kccj"};
			cnCol = new String[]{"ѧ��","ѧ��","ѧ��","����", Base.YXPZXY_KEY+"����","�༶����","�γ�����","�γ�����","�ɼ�", "���Կ�ƽ���ɼ�", "�����ƽ���ɼ�"};
		} else if (iType == 10) {
			enCol = new String[] { "xn", "xqmc", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "xf", "cj", "bkcx" };
			cnCol = new String[] { "ѧ��", "ѧ��", "ѧ��", "����",  Base.YXPZXY_KEY+"����", "�༶����",
					"�γ�����", "�γ�����", "ѧ��", "�ɼ�", "����������" };
			// TODO
		}
		listTopTr = dao.getSearchTitle(enCol, cnCol);
		return listTopTr;
	}
	/**
	 * �ȼ����Գɼ���ѯ���
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKscjResult(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm,User user,String type) throws Exception {
		dao = new XscjDAO();
		String[] colList= severTop(getToptr(dataSearchForm,user,type),"en");
		return dao.getKscjResult(xscjModel, dataSearchForm,user,colList);
	}
	/**
	 * �ȼ����Գɼ���ѯ�����
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public int getKscjResultNum(XscjModel xscjModel) throws Exception {
		dao = new XscjDAO();
		return dao.getKscjResultNum(xscjModel);
	}
	/**
	 * ѧ���ɼ���ѯ���
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscjResult(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm, User user,String type) throws Exception {
		dao = new XscjDAO();
		String[] colList= severTop(getToptr(dataSearchForm,user,type),"en");
		List<String[]> listRs = dao.getXscjResult(xscjModel,
				dataSearchForm,user,colList);
		return listRs;
	}
	/**
	 * ����top�б� ���ݻ�ȡ���ͻ�ȡ��en,cn��
	 * 
	 * @param topList
	 * @param hqlx
	 * @return List<HashMap<String, String>>
	 */
	public String[] severTop(List<HashMap<String, String>> topList, String hqlx) {

		List<String> outList = new ArrayList<String>();
		for (int i = 0; i < topList.size(); i++) {
			HashMap<String, String> topMap = topList.get(i);
			//System.out.println(topMap.get(hqlx));
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}
	
	/**
	 * ѧ���ɼ���ѯ�����
	 * 
	 * @param xscjModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public int getXscjResultNum(
			XscjModel xscjModel, String userType, String userName) throws Exception {
		dao = new XscjDAO();
		return dao.getXscjResultNum(xscjModel,userType,userName);
	}
	
	/**
	 * ��ȡ��
	 * @param model
	 * @param user
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getKindChoose(XscjActionForm model, User user,String type) {
		DAO commDao=DAO.getInstance();
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		
		if(type=="1"){
			colArr.add("xn");
			topArr.add("ѧ��");
			
			colArr.add("xqmc");
			topArr.add("ѧ��");
			
			colArr.add("xh");
			topArr.add("ѧ��");
			
			colArr.add("xm");
			topArr.add("����");
			
			colArr.add("nj");
			topArr.add("�꼶");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"����");
			
			colArr.add("bjmc");
			topArr.add("�༶����");
			
			colArr.add("kcmc");
			topArr.add("�γ�����");
			
			colArr.add("kcxz");
			topArr.add("�γ�����");
			
			colArr.add("cj");
			topArr.add("�ɼ�");
		}else{//�ɼ��ȼ�
			
			colArr.add("xn");
			topArr.add("ѧ��");
			
			colArr.add("xqmc");
			topArr.add("ѧ��");
			
			colArr.add("xh");
			topArr.add("ѧ��");
			
			colArr.add("xm");
			topArr.add("����");
			
			colArr.add("nj");
			topArr.add("�꼶");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"����");
			
			colArr.add("bjmc");
			topArr.add("�༶����");
			
			colArr.add("djksmc");
			topArr.add("�ȼ���������");
			
			colArr.add("ksrq");
			topArr.add("��������");
			
			colArr.add("cj");
			topArr.add("�ɼ�");
			
		}
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}

	/**
	 * ������ѡ
	 * @param model
	 * @param user
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveKindChoose(XscjActionForm model,User user,String type) throws Exception{
		
		DAO commDao=DAO.getInstance();
		dao = new XscjDAO();
		String yhm=user.getUserName();
		String path="xsxx_xscjQuery.do";
		String yhlx="tea";
		StringBuilder xszd=new StringBuilder();
		String []bxszd={"xh","xm"};
		String []xszdArr=null;
		if("stu".equalsIgnoreCase(user.getUserType())){
			yhlx="stu";
		}
		xszdArr=commDao.unionArray(bxszd, model.getXszdArr());
		for(int i=0;i<xszdArr.length;i++){
			if(i!=0){
				xszd.append(",");
			}
			xszd.append(xszdArr[i]);
		}
		
		return checkBoolean(dao.saveKindChoose(yhm, yhlx, xszd.toString(), path,type));
	}
	/**
	 * �ж����������Ƿ�ɹ�
	 * @param returnV
	 * @return
	 */
	public boolean checkBoolean(int []returnV){
		boolean blog=true;
		for(int i=0;i<returnV.length;i++){
			if(returnV[i]==0){
				blog=false;
			}
		}
		return blog;	
	}
	/**
	 * ��ȡָ���û���������ʾ��
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getCheckKind(XscjActionForm model, User user,String type) {
		// ��ȡָ���û�ѡ�е���
		dao = new XscjDAO();
		HashMap<String, String> kind = dao.getCheckKind(model, user,type);
		List<HashMap<String, String>> checkKind = new ArrayList<HashMap<String, String>>();
		// ����ʾ�ֶ�
		String xszd = kind.get("xxszd");
		String[] xszdArr = null;
		if (!Base.isNull(xszd)) {
			xszdArr = xszd.split(",");
			for (int i = 0; i < xszdArr.length; i++) {
				HashMap<String, String> kindMap = new HashMap<String, String>();
				kindMap.put("zd", xszdArr[i]);
				checkKind.add(kindMap);
			}
		}
		return checkKind;
	}
	/**
	 * ����Request ��ֵ
	 * 
	 * @param form
	 * @param request
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String writeAble = request.getParameter("writeAble");
		String title = rForm.getTitle();

		// ��ͼ��
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// ����
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// ģ��·��
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// �û�����
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// �û���
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// �û����ڲ���
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(path)) {
			request.setAttribute("userDep", userDep);
		}

		// ��������
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}

		// ��дȨ���
		if (Base.isNull(writeAble)) {
			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0]
					: "";
			if (Base.isNull(title)) {
				title = message != null && message.length >= 2 ? message[1]
						: "";
			}
		}
		request.setAttribute("writeAble", writeAble);

		// ģ�����
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// ����
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// ģ�K���
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// ��ͷ��Ϣ
		List<HashMap<String, String>> topTr = rForm.getTopTr();
		if (topTr != null && topTr.size() > 0) {
			request.setAttribute("topTr", topTr);
		}

		// ��ϸ��Ϣ
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// ��ϸ�б���Ϣ
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
		}

		// ��ϸ�б���Ϣ
		ArrayList<String[]> rsArrList = rForm.getRsArrList();
		if (rsArrList != null && rsArrList.size() > 0) {
			request.setAttribute("rsArrList", rsArrList);
			request.setAttribute("rsNum", rsArrList.size());
		}

		// ��ʾ��Ϣ
		String message = rForm.getMessage();
		if (!Base.isNull(message)) {
			request.setAttribute("message", message);
		}

		// �����ֶ�
		String[] qtzd = rForm.getQtzd();
		// �����ֶ�ֵ
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}
	/**
	 * ��ȡ��ͷ��ʾ�ֶ�
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getToptr(XscjActionForm model, User user,String type) {
		//��ȡָ���û���������ʾ��
		List<HashMap<String, String>> kindList = getCheckKind(model, user,type);
		List<HashMap<String, String>> top=getTop(model,user,type);
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		HashMap<String, String>map=new  HashMap<String, String>();
//		map.put("en", "pkValue");
//		map.put("cn", "����");
//		topTr.add(map);
		//ȡ����
		if (kindList != null && kindList.size() > 0) {
			for (int i = 0; i < kindList.size(); i++) {
				HashMap<String, String> kindMap = kindList.get(i);
				HashMap<String, String> topTrMap = new HashMap<String, String>();
				for (int j = 0; j < top.size(); j++) {
					HashMap<String, String> topMap = top.get(j);

					if (kindMap.get("zd").equalsIgnoreCase(topMap.get("en"))) {
						topTrMap.put("en", topMap.get("en"));
						topTrMap.put("cn", topMap.get("cn"));
						topTr.add(topTrMap);
						break;
					}
				}
				
			}
			
		}else{
			return top;
		}
		
		if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm)
				&& !Base.isNull(model.getKcmc())){
			
			HashMap<String, String> topMap = new HashMap<String, String>();
			
			topMap.put("en","pm");
			topMap.put("cn","�γ�����");
			topTr.add(topMap);
		}
		return topTr;
	}
	/**
	 * ��ȡ�۲��ֱܷ�ͷ�ֶ�
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTop(XscjActionForm model, User user,String type) {
		DAO commDao=DAO.getInstance();
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		if(type=="1"){
			colArr.add("xn");
			topArr.add("ѧ��");
			
			colArr.add("xqmc");
			topArr.add("ѧ��");
			
			colArr.add("xh");
			topArr.add("ѧ��");
			
			colArr.add("xm");
			topArr.add("����");
			
			colArr.add("nj");
			topArr.add("�꼶");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"����");
			
			colArr.add("bjmc");
			topArr.add("�༶����");
			
			colArr.add("kcmc");
			topArr.add("�γ�����");
			
			colArr.add("kcxz");
			topArr.add("�γ�����");
			
			colArr.add("cj");
			topArr.add("�ɼ�");
		}else{//�ɼ��ȼ�
			
			colArr.add("xn");
			topArr.add("ѧ��");
			
			colArr.add("xqmc");
			topArr.add("ѧ��");
			
			colArr.add("xh");
			topArr.add("ѧ��");
			
			colArr.add("xm");
			topArr.add("����");
			
			colArr.add("nj");
			topArr.add("�꼶");
			
			colArr.add("xymc");
			topArr.add(Base.YXPZXY_KEY+"����");
			
			colArr.add("bjmc");
			topArr.add("�༶����");
			
			colArr.add("djksmc");
			topArr.add("�ȼ���������");
			
			colArr.add("ksrq");
			topArr.add("��������");
			
			colArr.add("cj");
			topArr.add("�ɼ�");
			
		}
		
		
		if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm)
				&& !Base.isNull(model.getKcmc())){
			colArr.add("pm");
			topArr.add("�γ�����");
		}
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡĳѧ�ꡢĳѧ��xx�༶�γ�
	 */
	public List<HashMap<String,String>>getBjkcInfo(XscjActionForm model){
		dao = new XscjDAO();
		return dao.getBjkcInfo(model);
	}
	
//	 -----------------------2012.4.9 qlj begin --------------------------
	/**
	 * ѧ���ɼ���ѯ���(�㶫����ְҵ����ѧԺ���Ի�)
	 * @param xscjModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscjList(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm, User user,String type) throws Exception {
		dao = new XscjDAO();
		String[] colList= severTop(getToptr(dataSearchForm,user,type),"en");
		return dao.getXscjList(xscjModel, dataSearchForm, user, colList);
	}
}

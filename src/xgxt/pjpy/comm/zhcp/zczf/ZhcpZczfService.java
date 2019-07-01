package xgxt.pjpy.comm.zhcp.zczf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.utils.date.DateUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_�۲��ܷ�_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class ZhcpZczfService extends PjpyCommService {
	
	ZhcpZczfDAO dao = new ZhcpZczfDAO();
	
	/**
	 * ��ȡ�۲��ֱܷ�ͷ�ֶ�
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTop(ZhcpZczfForm model, User user) {
		String zcpm=model.getZcpm();
		String zypm=model.getZypm();
		DAO commDao=DAO.getInstance();
		//��ȡ���ñ������õ��ֶΡ��ֶ�����
		List<HashMap<String,String>>colList=dao.getZczfTop(model, user);
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		//��ȡ��չ�ֶ���Ϣ
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
	
		colArr.add("pkValue");
		topArr.add("����");
		
		colArr.add("xh");
		topArr.add("ѧ��");
		
		colArr.add("xm");
		topArr.add("����");
		
		colArr.add("nj");
		topArr.add("�꼶");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("רҵ");
		
		colArr.add("bjmc");
		topArr.add("�༶");
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("�꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("�꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("�༶����");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("�������꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("�������꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("�����ְ༶����");
		}
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	
	/**
	 * ��ȡ�۲��ģ����ѡ�ֶ�
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getKindChoose(ZhcpZczfForm model, User user) {
		
		String zcpm=model.getZcpm();
		String zypm=model.getZypm();
		DAO commDao=DAO.getInstance();
		//��ȡ���ñ������õ��ֶΡ��ֶ�����
		List<HashMap<String,String>>colList=dao.getZczfTop(model, user);
		//�ֶδ��루COLLIST��
		List<String>colArr=new ArrayList<String>();
		//�ֶ����ƣ�TOP��
		List<String>topArr=new ArrayList<String>();
		//��ȡ��չ�ֶ���Ϣ
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		// ------------------------Ĭ����ʾ�ֶ� begin------------------------------
		colArr.add("xh");
		topArr.add("ѧ��");
		
		colArr.add("xm");
		topArr.add("����");
		
		colArr.add("nj");
		topArr.add("�꼶");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("רҵ");
		
		colArr.add("bjmc");
		topArr.add("�༶");
		// ------------------------Ĭ����ʾ�ֶ� end------------------------------
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		// ---------------------�۲�������Ϣ ����flowControl.xml�ļ�����ȡ begin---------------------
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("�꼶"+Base.YXPZXY_KEY+"����");
		}
		
		
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("�꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("�༶����");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("�������꼶"+Base.YXPZXY_KEY+"����");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("�������꼶רҵ����");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("�����ְ༶����");
		}
		// ---------------------�۲�������Ϣ ����flowControl.xml�ļ�����ȡ end---------------------
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡָ���û���������ʾ��
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getCheckKind(ZhcpZczfForm model, User user) {
		// ��ȡָ���û�ѡ�е���
		HashMap<String, String> kind = dao.getCheckKind(model, user);
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
	 * ��ȡ�۲��ܷ���Ϣ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZhcpZczf(ZhcpZczfForm model,User user) throws Exception{
		
		String[]colList=severTop(getToptr(model,user),"en");
		
		return dao.getZhcpZczf(model, user, colList);
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
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}
	
	/**
	 * ������ѡ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveKindChoose(ZhcpZczfForm model,User user) throws Exception{
	
		DAO commDao=DAO.getInstance();
		String yhm=user.getUserName();
		String path="zhcp_zczf_zccx.do";
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
		
		return checkBoolean(dao.saveKindChoose(yhm, yhlx, xszd.toString(), path));
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
	 * ��ȡ��ͷ��ʾ�ֶ�
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getToptr(ZhcpZczfForm model, User user) {
		//��ȡָ���û���������ʾ��
		List<HashMap<String, String>> kindList = getCheckKind(model, user);
		List<HashMap<String, String>> top=getTop(model,user);
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		HashMap<String, String>map=new  HashMap<String, String>();
		map.put("en", "pkValue");
		map.put("cn", "����");
		topTr.add(map);
		
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
		
		return topTr;
	}
	
	/**
	 * ��ȡ��׼���Ŀ��Ϣ
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getDjxmList(ZhcpZczfForm model,User user){
		return dao.getDjxmList(model, user);
	}
	
	/**
	 * ��ȡ��׼���Ŀ����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean djxmjs(ZhcpZczfForm model,User user) throws Exception{
		return dao.djxmjs(model, user);
	}
	
	/**
	 * ��ȡ�۲����������б�������
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBldmList(ZhcpZczfForm model,User user){

		return dao.getBldmList(model, user);
	}
	
	/**
	 * ��ȡ������Ŀ����Ŀ�б�
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXjsxmList(ZhcpZczfForm model,User user){

		return dao.getXjsxmList(model, user);
	}
	
	/**
	 * ��ȡ�۲���Ŀ��ϸ������Ϣ
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getZcxmInfo(ZhcpZczfForm model,User user){
		
		return dao.getZcxmInfo(model, user);
		
	}
	
	/**
	 * �۲���Ŀ�ּ���
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zcxmjs(ZhcpZczfForm model,User user) throws Exception {
		
		boolean blog=true;
		// ====================������ͼ������Ŀ��=============================
		//��ȡ��Ҫ�������׼�����Ŀ
//		List<HashMap<String,String>>djxmList=getDjxmList(model,user);
//		boolean blog=true;
//		for(int i=0;i<djxmList.size();i++){
//			HashMap<String,String>djxmMap=djxmList.get(i);
//			try {
//				model.setXmdm(djxmMap.get("xmdm"));
//				model.setLyb(djxmMap.get("lyb"));
//				model.setZd(djxmMap.get("zd"));
//				model.setGlxn(djxmMap.get("glxn"));
//				model.setGlxq(djxmMap.get("glxq"));
//				model.setGlnd(djxmMap.get("glnd"));
//				blog=djxmjs(model,user);
//			} catch (Exception e) {
//				
//				return false;	
//			}
//		}
		// ====================������ͼ������Ŀ�� end=========================
		
		// =============������Ŀ����Ŀ�ּ���===================
		List<HashMap<String,String>>zcjsList=dao.getZcjsSql(model, user);
		
		blog=zcxmfjs(zcjsList);
		// =============������Ŀ����Ŀ�ּ���end===================
		return blog;
	}
	
	/**
	 * ������Ŀ����Ŀ�ּ���
	 * @param zcjsList
	 * @return boolean
	 */
	public boolean zcxmfjs(List<HashMap<String,String>>zcjsList){
		
		List<HashMap<String, String>> xmjbList = dao.getXmjbList();
		
		boolean flag = false;
		
		for (int i = 0; i < xmjbList.size(); i++) {

			HashMap<String, String> xmjbMap = xmjbList.get(i);
			List<String> sql = new ArrayList<String>();
			for (int j = 0; j < zcjsList.size(); j++) {

				
				HashMap<String, String> zcjsMap = zcjsList.get(j);

				if (xmjbMap.get("xmjb").equalsIgnoreCase(zcjsMap.get("xmjb"))
						&& Base.isNull(zcjsMap.get("ytj"))) {
					zcjsMap.put("ytj", "yes");
					sql.add(zcjsMap.get("sql"));
				}
			}

			try {

				flag = dao.saveArrDate(sql.toArray(new String[] {}));

			} catch (Exception e) {

				return false;

			}

		}

		return flag;	
	}
	
	/**
	 * �����۲�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getPlace(ZhcpZczfForm model,User user) throws Exception{
		//������ѡ����������㷽ʽ��������
		String[]pmjs=model.getPmjs();
		boolean blog=false;
		for(int i=0;i<pmjs.length;i++){
			//�꼶ѧԺ����
			if("njxy".equalsIgnoreCase(pmjs[i])){
				blog=xypmjs(model,user);
			}
			//�꼶רҵ����
			else if("njzy".equalsIgnoreCase(pmjs[i])){
				blog=zypmjs(model,user);
			}
			//�༶����
			else if("bj".equalsIgnoreCase(pmjs[i])){
				blog=bjpmjs(model,user);
			}
		}
	
		return blog;
	}
	
	/**
	 * ������������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getZyPlace(ZhcpZczfForm model,User user) throws Exception{

		//������ѡ����������㷽ʽ��������
		String[]zypmjs=model.getZypmjs();
		boolean blog=false;
		for(int i=0;i<zypmjs.length;i++){
			//�꼶ѧԺ����
			if("njxy".equalsIgnoreCase(zypmjs[i])){
				blog=xyZypmjs(model,user);
			}
			//�꼶רҵ����
			else if("njzy".equalsIgnoreCase(zypmjs[i])){
				blog=zyZypmjs(model,user);
			}
			//�༶����
			else if("bj".equalsIgnoreCase(zypmjs[i])){
				blog=bjZypmjs(model,user);
			}
		}
	
		return blog;
	}
	
	
	/**
	 * �༶��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjpmjs(ZhcpZczfForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.bjpmjs(model, user);
		if (flag) {
			flag = dao.updateBjpm(model, user);
		}
		return flag;
	}
	
	/**
	 * �꼶ѧԺ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xypmjs(ZhcpZczfForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njxypmjs(model, user);
		if (flag) {
			flag = dao.updateXypm(model, user);
		}
		return flag;
	}
	
	/**
	 * �꼶רҵ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zypmjs(ZhcpZczfForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njzypmjs(model, user);
		if (flag) {
			flag = dao.updateZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * �꼶ѧԺ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xyZypmjs(ZhcpZczfForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njxyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjXyZypm(model, user);
		}
		return flag;
	}
	

	/**
	 * �༶��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjZypmjs(ZhcpZczfForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.bjZypmjs(model, user);
		if (flag) {
			flag = dao.updateBjZypm(model, user);
		}
		return flag;
	}
	
	
	/**
	 * �꼶רҵ��������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zyZypmjs(ZhcpZczfForm model, User user) throws Exception {

		// ���༶����������ʱ��
		boolean flag = dao.njzyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjZyZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * ��ȡ�۲�������Ϣ
	 * 
	 * @param xh
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZcfInfoList(String xh) {
		return dao.getZcfInfoList(xh);
	}
	
	/**
	 * ����۲���Ŀ��չ�ֶ���Ϣ��������ѧ�ڣ�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getKzzdList(User user) {

		return dao.getKzzdList(user);
	}
	
	
	/**
	 * ����ӡ����ת��Ϊ�б�ԭ�������Ρ�
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public Object[]  getPrintData(String xn,String xq,String bjdm) throws Exception{
		
		Object[] data = new Object[3]; 
		
		String[] xhValue = dao.getStuByBjdm(bjdm);
		String[] kcvalue = dao.getBxkcArr(xn, xq, bjdm);
		List<String[]> cjList = dao.getBxcjList(xn, xq, bjdm);
		List<String[]> zhcpList = dao.getZhcpList(xn, xq, bjdm);
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		
		
		//����ѯ����ת��Ϊ�б�
		if (cjList != null) {
			if (xhValue != null) {
				for (int j = 0; j < xhValue.length; j++) {
					HashMap<String, String> oneRs = new HashMap<String, String>();
					int index = 0;
					for (int k = 0; k < kcvalue.length; k++) {
						for (int l = 0; l < cjList.size(); l++) {
							String[] oneData = cjList.get(l);
							if (oneData != null && oneData.length == 6) {
								if (kcvalue[k].equalsIgnoreCase(oneData[2]) && xhValue[j].equalsIgnoreCase(oneData[0])) {

									oneRs.put("xh", oneData[0]);
									oneRs.put("xm", oneData[1]);
									oneRs.put("kccj" + (index + 1), oneData[3]);
									index++;

									if (zhcpList != null) {
										for (int m=0;m<zhcpList.size();m++) {
											String[] zhcpOne = zhcpList.get(m);
											if (zhcpOne != null && zhcpOne.length==7) {
												if (xhValue[j].equalsIgnoreCase(zhcpOne[0])) {
													oneRs.put("zcjzf", zhcpOne[1]);
													oneRs.put("tcjzf", zhcpOne[2]);
													oneRs.put("dcjzf", zhcpOne[3]);
													oneRs.put("kpf", zhcpOne[4]);
													oneRs.put("cxdj", zhcpOne[5]);
													oneRs.put("bjpm", zhcpOne[6]);
													break;
												}
											}
										}
									}
									break;

								}
							}
						}
					}
					if (!oneRs.isEmpty()){
						rs.add(oneRs);
					}
				}
			}
		}
		
		//��ͷ�༶������Ϣ
		HashMap<String,String> map = new HashMap<String,String>();
		if (cjList != null && cjList.size() > 0) {
			map.put("xymc", cjList.get(0) != null && cjList.get(0).length == 6 ? cjList.get(0)[4] : "");
			map.put("bjmc", cjList.get(0) != null && cjList.get(0).length == 6 ? cjList.get(0)[5] : "");
		}
		map.put("year", DateUtils.getYear()+" ��" + DateUtils.getMonth() + " ��" + DateUtils.getDayOfMonth() + " ��");
		map.put("kclen", kcvalue != null ? String.valueOf(kcvalue.length) : "1");
		map.put("len", kcvalue != null ? String.valueOf(kcvalue.length+1) : "1");
		map.put("trlen", kcvalue != null ? String.valueOf(kcvalue.length+1 + 12) : "1");
		
		//ѧ���γ���Ϣ
		HashMap<String, String> rskc = new HashMap<String, String>();
		if(kcvalue == null){
			for(int i =0;i <13; i++){
				rskc.put("kc"+(i+1), "");
			}
		}else{
			for(int i =0;i <kcvalue.length; i++){
				rskc.put("kc"+(i+1), kcvalue[i]);
			}
		}
		
		data[0] = rs;
		data[1] = map;
		data[2] = rskc;
		return data;
	}
	
}
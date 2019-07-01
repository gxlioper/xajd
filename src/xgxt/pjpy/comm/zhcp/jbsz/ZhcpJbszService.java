package xgxt.pjpy.comm.zhcp.jbsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.szdw.xmlg.fdyyp.FdyypService;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_��������_Service��
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

public class ZhcpJbszService extends PjpyCommService {
	
	ZhcpJbszDAO dao = new ZhcpJbszDAO();
	
	/**
	 * ��õ���������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public HashMap<String, Object> getDypfInfo(ZhcpJbszForm model, User user)
			throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		// ��ø���Ա������Ϣ
		FdyypService fdyService = new FdyypService();
		String[] colList = new String[] { "zgh", "xm", "xb", "bmmc" };
		String zgh = user.getUserName();
		HashMap<String, String> fdyInfo = fdyService.getFdyxx(colList, zgh);

		// ��ø���Ա�����༶��Ϣ
		List<HashMap<String, String>> list = dao.getFdyDbList(model, user);

		int defNum = 5;//Ĭ������
		
		if (list != null && list.size() < defNum) {
			
			int listSize = list.size();//�б�����
			
			for (int i = 0; i < defNum - listSize; i++) {
				HashMap<String, String> spaceMap = new HashMap<String, String>();
				list.add(spaceMap);
			}
		}

		map.putAll(fdyInfo);
		map.put("bjList", list);

		return map;
	}

	/**
	 * ����������ֿ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveDypfKgzt(ZhcpJbszForm model, User user) throws Exception {
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// ְ����
		String zgh = user.getUserName();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �༶
		String[] bjdm = model.getBjdm();
		
		// ���ҷ����
		String tableName = "xg_pjpy_dypfszb";
		// ����
		String pk = "xn||xq||nd||bjdm";
		// ����ֵ
		ArrayList<String> pkValueList = new ArrayList<String>();
		// �����ֶ�
		String[] onezd = new String[] { "xn", "xq", "nd","szr" };
		// �����ֶ�
		String[] arrzd = new String[] { "bjdm", "kgzt" };
				
		for (int i = 0; i < bjdm.length; i++) {
			pkValueList.add(pjxn + pjxq + pjnd + bjdm[i]);
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValueList.toArray(new String[] {}));
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);
		model.setSzr(zgh);
		
		return saveInfoToDb(saveForm, model, user);
	}
	
	/**
	 * ����۲�ӷ���Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws Exception //ZhcpJbszForm
	 */
	public List<HashMap<String, Object>> getZcjfInfoList(ZhcpJbszForm model,
			User user) throws Exception {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		// ������Ŀ�б�
		model.setXmjb("2");
		List<HashMap<String, String>> lv2List = getZcmrxmList(model);
		
		// �ӷ���Ŀ�б�
		List<HashMap<String, String>> jfxmList = dao.getJfxmList(model);
		
		if (lv2List != null && lv2List.size() > 0) {
			
			int count = 0;
			
			for (int i = 0; i < lv2List.size(); i++) {
				//������Ŀ��Ϣ
				HashMap<String, String> lv2Info = lv2List.get(i);
				String xmdm = lv2Info.get("xmdm");//��Ŀ����
				String xmmc = lv2Info.get("xmmc");//��Ŀ����
				
				List<HashMap<String, String>> xmList = new ArrayList<HashMap<String,String>>();
				
				if(jfxmList!=null && jfxmList.size()>0){
					
					int n = 1;

					for (int j = 0; j < jfxmList.size(); j++) {
						// �ӷ���Ŀ��Ϣ
						HashMap<String, String> jfxmInfo = jfxmList.get(j);
						String jfxmdm = jfxmInfo.get("xmdm");// �ӷ���Ŀ����
						String sqfs = jfxmInfo.get("sqfs");// �������
						
						if (xmdm.equalsIgnoreCase(jfxmdm)) {
							jfxmInfo.put("num", String.valueOf(n));
							xmList.add(jfxmInfo);

							if(!Base.isNull(sqfs)){
								count++;
							}
							n++;
						}
					}
				}
				
				//�ӷ���Ŀ��
				int jfxmNum = 0;
				String sqly = "";
				if (xmList != null && xmList.size() > 0) {
					jfxmNum = xmList.size();
					sqly=xmList.get(0).get("sqly");
				}
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("xmdm", xmdm);
				map.put("xmmc", xmmc);
				map.put("jfxmNum", jfxmNum);
				map.put("count", count);
				map.put("sqly", sqly);
				map.put("xmList", xmList);
				list.add(map);
				
				count = 0;
			}
		}
		
		return list;
	}
	
	/**
	 * �����۲�ӷ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveZcjfsz(ZhcpJbszForm model, User user) throws Exception {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		// ���ҷ����
		String tableName = "xg_pjpy_zcxmjfb";
		// ����
		String pk = "xn||xq||nd";
		// ����ֵ
		String pkValue = pjxn + pjxq + pjnd;
		// �����ֶ�
		String[] onezd = new String[] { "xn", "xq", "nd" };
		// �����ֶ�
		String[] arrzd = new String[] { "xmdm", "jfdm", "jfmc", "jjf", "xxf",
				"sxf" };
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);
		
		return saveInfoToDb(saveForm, model, user);
	}
	// ======================���� made by ΰ�����==============================
	
	// =================����made by �����������======================
	/**
	 * ��ȡ�����б�
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getZctreeList(ZhcpJbszForm model){
		
		//�۲���Ŀ�б�
		List<HashMap<String,String>>zcxmList=dao.getZcxmList(model);

		return zcxmList;
	}
	
	/**
	 * ��ȡ�����б�
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getZcmrxmList(ZhcpJbszForm model){
		
		//�۲���Ŀ�б�
		List<HashMap<String,String>>zcxmList=dao.getZcmrxmList(model);

		return zcxmList;
	}
	
	/**
	 * ��ȡ�ϼ�����
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getSjdmList(ZhcpJbszForm model){
		
		return dao.getSjdmList(model);
	}
	
	/**
	 * ��ȡ�۲���������б�
	 * ZhcpJbszForm model
	 * return HashMap<String,String>
	 */
	public List<HashMap<String,String>>getZcbllxList(ZhcpJbszForm model){
		
		return dao.getZcbllxList(model);
	}
	
	/**
	 * ��ȡ�۲���Ŀ��Ϣ�б�
	 * ZhcpJbszForm model
	 * return HashMap<String,String>
	 */
	public List<HashMap<String,String>>getZcxmxxList(ZhcpJbszForm model){
		
		return dao.getZcxmxxList(model);
	}
	
	/**
	 * �����۲���Ŀ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean upateZcxmmc(ZhcpJbszForm model,User user) throws Exception{
		
		return  dao.updateZcxmmc(model, user);
		
	}
	
	/**
	 * �����۲�ֱ���
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveZcbl(ZhcpJbszForm model,User user) throws Exception{
		
		ZhcpJbszModel jbszModel=new ZhcpJbszModel();
		// �������ݲ��� �ı���
		String realTable = "xg_pjpy_zcblb";
		
		String[] arrzd = new String[] { "xmdm","bldm", "bl"};
		String[] onezd = new String[] { "xn","xq","nd"};
		
		int len=model.getXmdmArr().length;
		String[]xmdm=new String[len];
		String[]bldm=new String[len];
		String[]bl=new String[len];
		String[]pkValue=new String[len];
		
		String xn=model.getPjxn();
		String xq=model.getPjxq();
		String nd=model.getPjnd();
		
		xmdm=model.getXmdmArr();
		bldm=model.getBldmArr();
		bl=model.getBlArr();
		
		String pk="xn||xq||nd||xmdm||bldm";
		for(int i=0;i<len;i++){
			pkValue[i]=xn+xq+nd+xmdm[i]+bldm[i];
		}
		
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		jbszModel.setXmdm(xmdm);
		jbszModel.setBldm(bldm);
		jbszModel.setBl(bl);
		jbszModel.setXn(xn);
		jbszModel.setXq(xq);
		jbszModel.setNd(nd);
		
		return  this.saveInfoToDb(saveForm, jbszModel, user);
	}

	
	/**
	 * �ۺϲ�����ʼ��
	 * @param String sql
	 * @return boolean
	 */
	public boolean zhcpIni(ZhcpJbszForm model, User user) {
		DAO dao = DAO.getInstance();
		
		
		String xn = ZhcpJbszForm.zcjbszModel.getPjxn();
		String xq = ZhcpJbszForm.zcjbszModel.getPjxq();
		String nd = ZhcpJbszForm.zcjbszModel.getPjnd();

		String[] input = { xn, xq, nd, model.getRyk() };

		boolean blog = false;
		try {
			blog = dao.runProcuder("{call pro_xg_zhcp_csh(?,?,?,?)}", input);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return blog;

	}
	
	/**
	 * �����۲���Ŀ
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean copyZcxm(ZhcpJbszForm model,User user){
		//�۲�����
		String zczq = XMLReader.getFlowControl("pjpy", "zczq");
		
		//�۲���ϸ����
		String xn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String xq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String nd=ZhcpJbszForm.zcjbszModel.getPjnd();
		//��һ�۲�����
		String upXn="��";
		String upXq="��";
		String upNd="��";
		if ("xn".equalsIgnoreCase(zczq)) {
			upXn=ZhcpJbszForm.zcjbszModel.getUpXn();
		} else if ("xq".equalsIgnoreCase(zczq)) {
			upXn=ZhcpJbszForm.zcjbszModel.getUpXn();
			upXq=ZhcpJbszForm.zcjbszModel.getUpXq();
		} else if ("nd".equalsIgnoreCase(zczq)) {
			//����Ϊ���ʱ,��ȡ��һ�����Ϣ
			upNd = ZhcpJbszForm.zcjbszModel.getUpNd();
		}
		
		//�ж��۲���Ŀ�����Ƿ������һ���ڵ���Ϣ
		if(checkZcByZq(upXn,upXq,upNd)){
			boolean blog=false;
			try {
				DAO dao=DAO.getInstance();
				String[]input={xn,xq,nd,upXn,upXq,upNd};
				blog= dao.runProcuder("{call pro_xg_zhcp_copysz(?,?,?,?,?,?)}",input);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return blog;
		}else{
			
			return false;
		}
	}
	
	/**
	 * ��һ�۲�ѧ��
	 * @param xn
	 * @return
	 */
	public String getUpxn(String xn) {

		String upXn = "";
		String xnSta = xn.substring(0, 4);
		String xnEnd = xn.substring(5, 9);
		upXn = String.valueOf(Integer.parseInt(xnSta) - 1) + "-"
				+ String.valueOf(Integer.parseInt(xnEnd) - 1);
		return upXn;
	}
	
	/**
	 * ��һ�۲�ѧ��
	 * @return
	 */
	public HashMap<String,String> getUpxq() {
		
		HashMap<String,String>upXqMap=dao.getUpXqxx();
		
		return upXqMap;
	}
	
	/**
	 * ��һ�۲����
	 * @return
	 */
	public String getUpnd(String nd) {

		String upNd = "";
		
		int ndInt=Integer.parseInt(nd);
		ndInt--;
		upNd=String.valueOf(ndInt);
		
		return upNd;
	}
	
	
	/**
	 * �ж��ض������Ƿ�����۲���Ϣ
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean checkZcByZq(String upXn, String upXq, String upNd) {
		
		String num = dao.checkZcByZq(upXn, upXq, upNd);
		
		if ("0".equalsIgnoreCase(num)) {
		
			return false;
		
		} else {
		
			return true;
		
		}
	}
	
	// =================����made by �����������======================


	/**
	 * �����ǰ�������ڵ��۲���Ŀ
	 * author ³��
	 */
	public boolean removeZcxm(){
		DAO dao=DAO.getInstance();
		String sql = "delete from xg_pjpy_zcxmb where xn=?";
		
		try {
			return dao.runUpdate(sql, new String[]{PjxtszModel.pjxtszModel.getPjxn()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
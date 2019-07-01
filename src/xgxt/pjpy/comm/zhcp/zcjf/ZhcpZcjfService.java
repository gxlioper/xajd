package xgxt.pjpy.comm.zhcp.zcjf;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.exception.SystemException;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_�۲�ӷ�_Service��
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

public class ZhcpZcjfService extends PjpyCommService {
	
	ZhcpZcjfDAO dao = new ZhcpZcjfDAO();
	
	BasicDAO basicDao= new BasicDAO();
	
	/**
	 * ����۲�ӷ�������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public HashMap<String, Object> getZcjfSqInfo(ZhcpZcjfForm model, User user)
			throws Exception {
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// ��Ա��
		String ryk = model.getRyk();
		// �û�����
		String userType = user.getUserType();
		// ������
		String xh = "stu".equalsIgnoreCase(userType) ? user.getUserName()
				: model.getXh();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("czr", user.getUserName());
		
		//ѧ��������Ϣ
		HashMap<String, String> stuInfo = new HashMap<String, String>();

		if (!Base.isNull(xh)) {
			
			if ("ss".equalsIgnoreCase(ryk)) {// ʵʱ��Ա��
				XsxxglService stuService = new XsxxglService();
				stuInfo = stuService.selectStuinfo(xh);
			} else {
				HashMap<String,String>valueMap=new HashMap<String,String>();
				BasicModel basicModel=new BasicModel();
				basicModel.setTableName("xg_view_pjpy_pjryk");
				valueMap.put("query", " and xh=? ");
				basicModel.setQueryV(new String[]{xh});
				basicModel.setValueMap(valueMap);
				stuInfo = basicDao.getBasicData(basicModel);
			}

			map.putAll(stuInfo);

			ZhcpJbszService jbszService = new ZhcpJbszService();

			// �ӷ���Ŀ
			jbszModel.setXh(xh);
			List<HashMap<String, Object>> jfxmList = jbszService
					.getZcjfInfoList(jbszModel, user);

			map.put("zcxmList", jfxmList);

			// �ӷ�������Ϣ
			String tableName = "xg_pjpy_zcjfsqb";
			String pk = "xn||xq||nd||xh";
			String pkValue = pjxn + pjxq + pjnd + xh;
			String[] colList = new String[] { "shzt1", "shr1", "shsj1",
					"shyj1", "sftj1" };

			HashMap<String, String> sqInfo = getRsInfo(tableName, pk, pkValue,
					colList);
			map.putAll(sqInfo);
		}
		
		return map;
	}
	
	/**
	 * �����۲�ӷ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveZcjf(ZhcpZcjfForm model, User user,String lx,
			HttpServletRequest request) throws Exception {

		boolean flag = false;

		if ("sq".equalsIgnoreCase(lx)) {// �������

			flag = saveZcjfsq(model, user, request);

			if (flag) {
				// �����۲�ӷַ���
				flag = saveZcjffs(model, user);
			}
		} else if ("sh".equalsIgnoreCase(lx)) {// ��˲���

			flag = updateZcjfsq(model, user);

			if (flag) {
				// �����۲�ӷ���˷���
				flag = updateZcjffs(model, user);
			}
		} else if ("tj".equalsIgnoreCase(lx)) {// �ύ����
			model.setSftj1("��");
			flag = updateZcjfsq(model, user);
			
//			if (flag) {
//				// �����۲�ӷ���˷���
//				flag = updateZcjffs(model, user);
//			}
			
			model.setCheckVal(null);
			model.setCheckVal(new String[]{model.getXh()});
			if(flag){
				//��ȡ��Ҫ�ύ�ķ���
				List<HashMap<String,String>>tjzfList=dao.getTjZf(model, user);
				//��������
				flag=dao.zsjfCommit(tjzfList);
			}
		} else if ("pltj".equalsIgnoreCase(lx)) {// �����ύ
			model.setSftj1("��");
			flag = updateZcjfsqBatch(model, user);
			if(flag){
				//��ȡ��Ҫ�ύ�ķ���
				List<HashMap<String,String>>tjzfList=dao.getTjZf(model, user);
				//��������
				flag=dao.zsjfCommit(tjzfList);
			}
		}

		return flag;
	}
	
	/**
	 * �����۲�ӷ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveZcjfsq(ZhcpZcjfForm model, User user,
			HttpServletRequest request) throws Exception {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		// �û�����
		String userType = user.getUserType();
		// ������
		String xh = "stu".equalsIgnoreCase(userType) ? user.getUserName()
				: model.getXh();

		// ���ҷ����
		String tableName = "xg_pjpy_zcjfsqb";
		// ����
		String pk = "xn||xq||nd||xh";
		// ����ֵ
		String pkValue = pjxn + pjxq + pjnd + xh;
		// �����ֶ�
		String[] onezd = new String[] { "xn", "xq", "nd", "xh" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setOnezd(onezd);

		model.setXh(xh);
		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);

		return saveInfoToDb(saveForm, model, request);
	}
	
	/**
	 * �޸��۲�ӷ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateZcjfsq(ZhcpZcjfForm model, User user) throws Exception {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ������
		String xh = model.getXh();
		// �����
		String shr = user.getUserName();
		// �����
		String shzt = "ͨ��";
		// ���ʱ��
		String shsj = getNowTime("YYYYMMDD");

		// ���ҷ����
		String tableName = "xg_pjpy_zcjfsqb";
		// ����
		String pk = "xn||xq||nd||xh";
		// ����ֵ
		String pkValue = pjxn + pjxq + pjnd + xh;
		// �����ֶ�
		String[] onezd = new String[] { "shzt1", "shr1", "shsj1", "sftj1" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setOnezd(onezd);

		model.setShzt1(shzt);
		model.setShr1(shr);
		model.setShsj1(shsj);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * �޸��۲�ӷ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateZcjfsqBatch(ZhcpZcjfForm model, User user) throws Exception {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// �����
		String shr = user.getUserName();
		// �����
		String shzt = "ͨ��";
		// ���ʱ��
		String shsj = getNowTime("YYYYMMDD");
		// ��ѡ��
		String[] checkVal = model.getCheckVal();

		// ���ҷ����
		String tableName = "xg_pjpy_zcjfsqb";
		// ����
		String pk = "xn||xq||nd||xh";
		// ����ֵ
		ArrayList<String> pkValue = new ArrayList<String>();//pjxn + pjxq + pjnd + xh;
		// �����ֶ�
		String[] onezd = new String[] { "shzt1", "shr1", "shsj1", "sftj1" };

		if (checkVal != null && checkVal.length > 0) {
			for (int i = 0; i < checkVal.length; i++) {
				pkValue.add(pjxn + pjxq + pjnd + checkVal[i]);
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue.toArray(new String[] {}));
		saveForm.setOnezd(onezd);

		model.setShzt1(shzt);
		model.setShr1(shr);
		model.setShsj1(shsj);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * �����۲�ӷַ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveZcjffs(ZhcpZcjfForm model, User user) throws Exception {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();

		// �û�����
		String userType = user.getUserType();
		// ������
		String xh = "stu".equalsIgnoreCase(userType) ? user.getUserName()
				: model.getXh();

		// ���ҷ����
		String tableName = "xg_pjpy_zcjffsb";
		// ����
		String pk = "xn||xq||nd||xh";
		// ����ֵ
		String pkValue = pjxn + pjxq + pjnd + xh;
		// �����ֶ�
		String[] onezd = new String[] { "xn", "xq", "nd", "xh" };
		// �����ֶ�
		String[] arrzd = new String[] { "xmdm", "jfdm", "sqfs", "sqly" };
		//		 �ǿ��ֶ�
		String[] notnull = new String[] { "sqfs" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setNotnull(notnull);

		model.setXh(xh);
		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);

		return saveInfoToDb(saveForm, model, user);
	}
	
	/**
	 * �޸��۲�ӷַ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateZcjffs(ZhcpZcjfForm model, User user) throws Exception {

		return dao.updateZcjffs(model, user);
	}
	
	/**
	 * ��üӷ�����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getJfshList(ZhcpZcjfForm model, User user,
			List<HashMap<String, String>> xmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ��Ա��
		String ryk = model.getRyk();

		ArrayList<String[]> rsLisr = new ArrayList<String[]>();

	
		List<HashMap<String, String>> list = dao.getJfshList(model, user,
				xmList);
		
		String nofy = model.getNofy();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> value = new ArrayList<String>();
				HashMap<String, String> map = list.get(i);
				String xh = map.get("xh");//ѧ��
				value.add(xh);
				String xm = map.get("xm");//����
				value.add(xm);
				String nj = map.get("nj");//�꼶
				value.add(nj);
				String bjmc = map.get("bjmc");//�༶����
				value.add(bjmc);
				
				if(xmList!=null && xmList.size()>0){
					for (int j = 0; j < xmList.size(); j++) {
						HashMap<String, String> xmInfo = xmList.get(j);
						String xmdm = xmInfo.get("xmdm");
						String sq = map.get(xmdm + "sq");// �����
						String sh = map.get(xmdm + "sh");// ��˷�
						String fs = "�����(" + sq + ")";
						if(Base.isNull(nofy)){
							fs += "</br>";
						}
						fs += "��˷�(" + sh + ")";
						value.add(fs);
					}
				}
				
				String shr = map.get("shr");//�༶����
				value.add(shr);
				String sftj = map.get("sftj");//�༶����
				value.add(sftj);
				
				rsLisr.add(value.toArray(new String[]{}));
			}
		}
		
		return rsLisr;
	}
	
	/**
	 * �����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmList() {

		ZhcpJbszForm model = new ZhcpJbszForm();
		ZhcpJbszService service = new ZhcpJbszService();

		// ������Ŀ�б�
		model.setXmjb("2");
		List<HashMap<String, String>> list = service.getZctreeList(model);

		return list;
	}
	
	/**
	 * ��ӡ�۲�ӷ�����
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printZcjfsq(ZhcpZcjfForm model, RequestForm rForm, User user,
			List<HashMap<String, String>> xmList, OutputStream os)
			throws Exception {

		String title = "�۲�ӷ�����";
		
		List<HashMap<String, String>> topTr = rForm.getTopTr();
		model.setNofy("yes");
		ArrayList<String[]> list = getJfshList(model, user, xmList);
		
		expToExcel(title, topTr, list, os);
	}
	
	/**
	 * �ж���˷�
	 * @param model
	 * @param xh
	 * @return
	 */
	public boolean checkShfs(ZhcpZcjfForm model,String xh){
		
		String num=dao.checkShfs(model, xh);
		
		if(Base.isNull(num)){
			
			return false;
		
		}else if("0".equalsIgnoreCase(num)){
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * �ж���˷�
	 * @param model
	 * @param xh
	 * @return
	 */
	public boolean checkShfIsModi(ZhcpZcjfForm model,HashMap<String,Object>map){
		
		String num=dao.checkShfIsModi(model, map);
		
		if(Base.isNull(num)){
			
			return false;
		
		}else if(!"0".equalsIgnoreCase(num)){
			
			return false;
		}
		
		return true;
	}
}
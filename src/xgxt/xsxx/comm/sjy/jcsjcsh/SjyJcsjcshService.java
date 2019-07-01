package xgxt.xsxx.comm.sjy.jcsjcsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.utils.MakeQuery;
import xgxt.xsxx.comm.XsxxCommService;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.sjy.jcsjsz.SjyJcsjszForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_����Դ_�������ݳ�ʼ��_Service��
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

public class SjyJcsjcshService extends XsxxCommService {

	SjyJcsjcshDAO dao = new SjyJcsjcshDAO();

	/**
	 * ��ó�ʼ����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getCshXmList(SjyJcsjcshForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", "xy");
		map.put("xmmc", "������Ϣ");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("xmdm", "zy");
		map.put("xmmc", "רҵ��Ϣ");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("xmdm", "bj");
		map.put("xmmc", "�༶��Ϣ");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("xmdm", "xsjbxx");
		map.put("xmmc", "ѧ��������Ϣ");
		list.add(map);

//		map = new HashMap<String, String>();
//		map.put("xmdm", "xsqtxx");
//		map.put("xmmc", "ѧ��������Ϣ");
//		list.add(map);

		return list;
	}

	/**
	 * ��ó�ʼ������б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getCshInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = null;

		// ������Ŀ
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//ѧԺ
			list = getXyInfoList(model, user, colList);
		}else if ("zy".equalsIgnoreCase(czxm)) {//רҵ
			list = getZyInfoList(model, user, colList);
		}else if ("bj".equalsIgnoreCase(czxm)) {//�༶
			list = getBjInfoList(model, user, colList);
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {//ѧ��������Ϣ
			list = getStuInfoList(model, user, colList);
		}


		return list;
	}

	/**
	 * ���ѧԺ��Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXyInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "bmjb", "bmlb" };
		String[] queryLikeList = new String[] { "bmdm", "bmmc", "bmfdm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��
		String tableName = "xg_view_jcsj_bmxx";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * ���רҵ��Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZyInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] {"bmdm"};
		String[] queryLikeList = new String[] { "zydm", "zymc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��
		String tableName = "xg_view_jcsj_zyxx";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * ��ð༶��Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getBjInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "nj", "zydm" };
		String[] queryLikeList = new String[] { "bjdm", "bjmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��
		String tableName = "xg_view_jcsj_bjxx";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * ���ѧ��������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getStuInfoList(SjyJcsjcshForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "nj", "xydm", "bjdm", "zydm" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��
		String tableName = "xg_view_xsjbxx_temp";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}
	
	/**
	 * ͬ����Ϣ
	 * 
	 * @throws Exception
	 */
	public boolean tbInfo(SjyJcsjcshForm model, User user) throws Exception {

		boolean flag = false;
		// ������Ŀ
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {// ѧԺ
			flag = dao.tbBmInfo();
		} else if ("zy".equalsIgnoreCase(czxm)) {// רҵ
			flag = dao.tbZyInfo();
		} else if ("bj".equalsIgnoreCase(czxm)) {// �༶
			flag = dao.tbBjInfo();
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {// ѧ��������Ϣ
			flag = dao.tbStuInfo();
		}

		return flag;
	}

	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 */
	public boolean allSubmit(SjyJcsjcshForm model, User user) throws Exception {

		boolean flag = false;
		// ������Ŀ
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//������Ϣ
			flag = dao.allSubmitByXy();
		}else if ("zy".equalsIgnoreCase(czxm)) {//רҵ
			flag = dao.allSubmitByZy();
		}else if ("bj".equalsIgnoreCase(czxm)) {//�༶
			flag = dao.allSubmitByBj();
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {//ѧ����Ϣ
			flag = dao.allSubmitByStu();
		}

		return flag;
	}
	
	/**
	 * ��ѡ�ύ
	 * 
	 * @throws Exception
	 */
	public boolean submitCheckInfo(SjyJcsjcshForm model, User user) throws Exception {

		boolean flag = false;
		// ������Ŀ
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//������Ϣ
			flag = dao.submitXyInfo(model);
		}else if ("zy".equalsIgnoreCase(czxm)) {//רҵ
			flag = dao.submitZyInfo(model);
		}else if ("bj".equalsIgnoreCase(czxm)) {//�༶
			flag = dao.submitBjInfo(model);
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {//ѧ��������Ϣ
			flag = dao.submitStuInfo(model);
		}

		return flag;
	}
	
	/**
	 * ִ�й���
	 * 
	 * @throws Exception
	 */
	public boolean doRule(SjyJcsjcshForm model, User user) throws Exception {

		return dao.doRule();
	}
	
	/**
	 * ���������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getRuleList(SjyJcsjcshForm model)
			throws Exception {

		List<HashMap<String, Object>> list = null;

		// ��΢����һ������
		List<HashMap<String, String>> oriList = new ArrayList<HashMap<String, String>>();

		// ������Ŀ
		String czxm = model.getCzxm();

		if ("xy".equalsIgnoreCase(czxm)) {//ѧԺ

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "bmdmb_bmlb");
			map.put("mc", "�������");
			map.put("color", judgeBmlb());// �жϲ�������Ƿ�Ϸ�

			oriList.add(map);

			list = createTableList(oriList, czxm);

		} else if ("zy".equalsIgnoreCase(czxm)) {//רҵ

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "zydmb_bmdm");
			map.put("mc", "�ϼ�Ժϵ");
			map.put("color", judgeSjxy());// �ж��ϼ������Ƿ�Ϸ�

			oriList.add(map);

			list = createTableList(oriList, czxm);
			
		}else if ("bj".equalsIgnoreCase(czxm)) {//�༶

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "bjdmb_zydm");
			map.put("mc", "����רҵ");
			map.put("color", judgeZydm());// �ж�����רҵ�Ƿ�Ϸ�

			oriList.add(map);

			list = createTableList(oriList, czxm);
		} else if ("xsjbxx".equalsIgnoreCase(czxm)) {// ѧ��������Ϣ

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "xsxxb_bjdm");
			map.put("mc", "�����༶");
			map.put("color", judgeBjdm());// �ж�ѧ���Ա��Ƿ�Ϸ�

			oriList.add(map);

			map = new HashMap<String, String>();
			map.put("dm", "xsxxb_xb");
			map.put("mc", "�Ա�");
			map.put("color", judgeXb());// �ж�ѧ���Ա��Ƿ�Ϸ�

			oriList.add(map);
			
			map = new HashMap<String, String>();
			map.put("dm", "xsxxb_xjztm");
			map.put("mc", "ѧ��״̬");
			map.put("color", judgeXjzt());// �ж�ѧ��ѧ���Ƿ�Ϸ�

			oriList.add(map);
			
			map = new HashMap<String, String>();
			map.put("dm", "xsxxb_xzqkm");
			map.put("mc", "��������");
			map.put("color", judgeXzqk());// �ж����������Ƿ�Ϸ�

			oriList.add(map);
			
			list = createTableList(oriList, czxm);
		}


		return list;
	}

	/**
	 * ����tanbleList
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, Object>> createTableList(
			List<HashMap<String, String>> oriList, String czxm) {

		// ������б�
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		if (oriList != null && oriList.size() > 0) {

			// Ĭ������
			int defTrNum = 12;
			// ������
			int trNum = 0;
			// ������
			int tdNum = 4;
			// ���ո���
			int spaceNum = 0;
			// �����ݵ������
			int lastTr = 0;
			// ����
			String dm = "dm";
			// ����
			String mc = "mc";

			if (oriList.size() % tdNum == 0) {
				trNum = oriList.size() / tdNum;
			} else {
				trNum = oriList.size() / tdNum + 1;
				spaceNum = tdNum - oriList.size() % tdNum;
			}

			lastTr = trNum;

			if (trNum < defTrNum) {
				trNum = defTrNum;
			}

			for (int i = 1; i <= trNum; i++) {

				HashMap<String, Object> trMap = new HashMap<String, Object>();

				List<HashMap<String, String>> tdList = new ArrayList<HashMap<String, String>>();

				int n = 0;

				for (int j = 0; j < oriList.size(); j++) {
					if (!"yes".equalsIgnoreCase(oriList.get(j).get("used"))) {
						if (n < tdNum) {
							tdList.add(oriList.get(j));
							oriList.get(j).put("used", "yes");
							n++;
						} else {
							break;
						}
					}
				}

				if (i == lastTr) {
					for (int j = 0; j < spaceNum; j++) {
						HashMap<String, String> spaceMap = new HashMap<String, String>();
						spaceMap.put(dm, "");
						spaceMap.put(mc, "&nbsp;");
						tdList.add(spaceMap);
					}
				}

				trMap.put("trNum", String.valueOf(i));

				if (tdList != null && tdList.size() > 0) {
					trMap.put("tdList", tdList);
				} else {
					for (int j = 0; j < tdNum; j++) {
						HashMap<String, String> spaceMap = new HashMap<String, String>();
						spaceMap.put(dm, "");
						spaceMap.put(mc, "&nbsp;");
						tdList.add(spaceMap);
					}
					trMap.put("tdList", tdList);
				}

				list.add(trMap);
			}

		}
		return list;
	}

	/**
	 * ��������ƶ�
	 * 
	 * @throws Exception
	 */
	public boolean saveRule(SjyJcsjcshForm model, User user) throws Exception {

		// ������Ϣ��
		String tableName = "xg_jcsj_gzzdb";
		// ����
		String pk = "zd||lyb||zdq";
		// ����ֵ
		List<String> pkList = new ArrayList<String>();
		// �����ֶ�
		String[] onezd = new String[] { "zd", "lyb" };
		// �����ֶ�
		String[] arrzd = new String[] { "zdq", "zdh" };
		// ����Ϊ��
		String[] notnull = new String[] { "zdh" };

		boolean flag = true;
		
		// ָ��ǰ
		String[] zdq = model.getZdq();

		if (zdq != null && zdq.length > 0) {

			String pkValue = "";

			for (int i = 0; i < zdq.length; i++) {
				pkValue = model.getZd() + model.getLyb() + zdq[i];
				pkList.add(pkValue);
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkList.toArray(new String[] {}));
			saveForm.setOnezd(onezd);
			saveForm.setArrzd(arrzd);
			saveForm.setNotnull(notnull);

			flag = saveInfoToDb(saveForm, model, user);
		}
		
		return flag;
	}
	
	// ====================��ͬ�������ȷ����ж�=========================================
	
	/**
	 * ���ع�����ɫ��green���Ϸ� red���Ƿ���
	 * 
	 * @author ΰ�����
	 */
	private String getRuleColor(String[] rule, List<HashMap<String, String>> list) {
		// �Ƿ�Ϸ�
		boolean isHf = true;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				String bmlb = list.get(i).get("ruledm");

				boolean flag = false;

				for (int j = 0; j < rule.length; j++) {
					if (bmlb.equalsIgnoreCase(rule[j])) {
						flag = true;
						break;
					}
				}

				if (!flag) {
					isHf = false;
				}
			}
		}

		if (isHf) {
			return "green";
		} else {
			return "red";
		}
	}
	
	/**
	 * �жϲ�������Ƿ���Ϲ���
	 * 
	 * @author ΰ�����
	 */
	private String judgeBmlb() {

		// �Ϸ��������(1:У�� 5:Ժ��)
		String[] rule = new String[] { "1", "5" };

		// ��ʱ���еĲ������
		List<HashMap<String, String>> list = dao.getBmlb();

		return getRuleColor(rule, list);
	}

	/**
	 * �ж��ϼ�Ժϵ�Ƿ���Ϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	private String judgeSjxy() throws Exception {

		List<HashMap<String, String>> xyList = dao.getXyList();
		// �Ϸ�ѧԺ(����Դȡ�� xg_jcsj_bmdmb )
		List<String> rule = new ArrayList<String>();

		if (xyList != null && xyList.size() > 0) {
			for (int i = 0; i < xyList.size(); i++) {
				rule.add(xyList.get(i).get("bmdm"));
			}
		}
		// ��ʱ���е��ϼ�����
		List<HashMap<String, String>> list = dao.getBmdm();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * �ж�����רҵ�Ƿ���Ϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	private String judgeZydm() throws Exception {

		List<HashMap<String, String>> zyList = dao.getZyList();
		// �Ϸ�ѧԺ(����Դȡ�� xg_jcsj_zydmb )
		List<String> rule = new ArrayList<String>();

		if (zyList != null && zyList.size() > 0) {
			for (int i = 0; i < zyList.size(); i++) {
				rule.add(zyList.get(i).get("zydm"));
			}
		}
		// ��ʱ���е�����רҵ
		List<HashMap<String, String>> list = dao.getZydm();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * �ж������༶�Ƿ���Ϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	private String judgeBjdm() throws Exception {

		List<HashMap<String, String>> bjList = dao.getBjList();
		// �Ϸ�ѧԺ(����Դȡ�� xg_jcsj_bjdmb )
		List<String> rule = new ArrayList<String>();

		if (bjList != null && bjList.size() > 0) {
			for (int i = 0; i < bjList.size(); i++) {
				rule.add(bjList.get(i).get("bjdm"));
			}
		}
		// ��ʱ���е�����רҵ
		List<HashMap<String, String>> list = dao.getBjdm();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * �ж�ѧ���Ա��Ƿ���Ϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private String judgeXb() throws Exception {

		// �Ϸ��������(�У�Ů)
		String[] rule = new String[] { "��", "Ů" };

		// ��ʱ���е��Ա�
		List<HashMap<String, String>> list = dao.getStuXb();

		return getRuleColor(rule, list);
	}
	
	/**
	 * �ж�ѧ��״̬�Ƿ���Ϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	private String judgeXjzt() throws Exception {

		List<HashMap<String, String>> xjztList = dao.getXjztList();
		// �Ϸ�ѧԺ(����Դȡ�� dm_zju_xjzt )
		List<String> rule = new ArrayList<String>();

		if (xjztList != null && xjztList.size() > 0) {
			for (int i = 0; i < xjztList.size(); i++) {
				rule.add(xjztList.get(i).get("zxdmmc"));
			}
		}
		// ��ʱ���е�ѧ��״̬
		List<HashMap<String, String>> list = dao.getXjzt();

		return getRuleColor(rule.toArray(new String[]{}), list);
	}
	
	/**
	 * �ж����������Ƿ���Ϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private String judgeXzqk() throws Exception {

		// ��ʱ���е���������
		List<HashMap<String, String>> list = dao.getXzqk();

		String colour = "green";

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// ʡ����
				String ssx = map.get("ssx");
				// ʡ
				String sheng = map.get("sheng");
				String shengmc = map.get("shengmc");
				// ��
				String shi = map.get("shi");
				String shimc = map.get("shimc");
				// ��
				String xian = map.get("xian");
				String xianmc = map.get("xianmc");
				// �ƶ�ǰ
				String zdq = map.get("zdq");
				// �ƶ���
				String zdh = map.get("zdh");
				
				//δ�ƶ�
				if(Base.isNull(zdh)){
					if(!Base.isNull(sheng) && Base.isNull(shengmc)){
						colour = "red";
						break;
					}else if(!Base.isNull(shi) && Base.isNull(shimc)){
						colour = "red";
						break;
					}else if(!Base.isNull(xian) && Base.isNull(xianmc)){
						colour = "red";
						break;
					}
				}
			}
		}

		return colour;
	}
	// ====================��ͬ�������ȷ����ж�end======================================

	// ====================��ͬ����Ĺ����ƶ�=========================================
	/**
	 * ���������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRuleZdList(SjyJcsjcshForm model)
			throws Exception {

		List<HashMap<String, String>> list = null;

		// ����
		String rule = model.getRule();

		if ("bmdmb_bmlb".equalsIgnoreCase(rule)) {// �������
			list = getBmlbRuleList();
		} else if ("zydmb_bmdm".equalsIgnoreCase(rule)) {// �ϼ�Ժϵ
			list = getBmdmRuleList();
		} else if ("bjdmb_zydm".equalsIgnoreCase(rule)) {// רҵ����
			list = getZydmRuleList();
		}else if ("xsxxb_xb".equalsIgnoreCase(rule)) {// �Ա�
			list = getXbRuleList();
		}else if ("xsxxb_bjdm".equalsIgnoreCase(rule)) {// �༶����
			list = getBjdmRuleList();
		}else if ("xsxxb_xjztm".equalsIgnoreCase(rule)) {// ѧ��״̬
			list = getXjztRuleList();
		}else if ("xsxxb_xzqkm".equalsIgnoreCase(rule)) {// ��������
			list = getXzqkRuleList();
		}

		return list;
	}

	/**
	 * ���������б�
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> setRuleList(String[] hfdm,
			String[] hfmc, List<HashMap<String, String>> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				String ruledm = list.get(i).get("ruledm");

				boolean flag = false;

				for (int j = 0; j < hfdm.length; j++) {
					if (hfdm[j].equalsIgnoreCase(ruledm)) {
						list.get(i).put("mc", hfmc[j]);
						flag = true;
						break;
					}
				}

				if (flag) {
					list.get(i).put("isHf", "yes");
				} else {
					list.get(i).put("isHf", "no");
				}
			}
		}

		return list;
	}
	
	/**
	 * ��ò����������б�
	 * 
	 * @author ΰ�����
	 */
	private List<HashMap<String, String>> getBmlbRuleList() {

		// �Ϸ��������(1:У�� 5:Ժ��)
		String[] hfdm = new String[] { "1", "5" };
		String[] hfmc = new String[] { "У��(1)", "Ժ��(5)" };
		
		// ��ʱ���еĲ������
		List<HashMap<String, String>> list = dao.getBmlb();

		return setRuleList(hfdm, hfmc, list);
	}
	
	/**
	 * ��ò��Ŵ�������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getBmdmRuleList() throws Exception {

		List<HashMap<String, String>> xyList = dao.getXyList();
		// �Ϸ�ѧԺ(����Դȡ�� xg_jcsj_bmdmb )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (xyList != null && xyList.size() > 0) {
			for (int i = 0; i < xyList.size(); i++) {
				hfdm.add(xyList.get(i).get("bmdm"));
				hfmc.add(xyList.get(i).get("bmmc"));
			}
		}

		// ��ʱ���еĲ������
		List<HashMap<String, String>> list = dao.getBmdm();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * ���רҵ��������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getZydmRuleList() throws Exception {

		List<HashMap<String, String>> zyList = dao.getZyList();
		// �Ϸ�ѧԺ(����Դȡ�� xg_jcsj_bmdmb )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (zyList != null && zyList.size() > 0) {
			for (int i = 0; i < zyList.size(); i++) {
				hfdm.add(zyList.get(i).get("zydm"));
				hfmc.add(zyList.get(i).get("zymc"));
			}
		}

		// ��ʱ���е�רҵ�б�
		List<HashMap<String, String>> list = dao.getZydm();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * ��ð༶��������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getBjdmRuleList() throws Exception {

		List<HashMap<String, String>> bjList = dao.getBjList();
		// �Ϸ�ѧԺ(����Դȡ�� xg_jcsj_bmdmb )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (bjList != null && bjList.size() > 0) {
			for (int i = 0; i < bjList.size(); i++) {
				hfdm.add(bjList.get(i).get("bjdm"));
				hfmc.add(bjList.get(i).get("bjmc"));
			}
		}

		// ��ʱ���еİ༶�б�
		List<HashMap<String, String>> list = dao.getBjdm();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * ����Ա�����б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getXbRuleList() throws Exception {

		// �Ϸ��Ա�
		String[] hfdm = new String[] { "��", "Ů" };
		String[] hfmc = new String[] { "��", "Ů" };

		// ��ʱ���еĲ������
		List<HashMap<String, String>> list = dao.getStuXb();

		return setRuleList(hfdm, hfmc, list);
	}
	
	/**
	 * ���ѧ��״̬�����б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getXjztRuleList() throws Exception {

		List<HashMap<String, String>> xjztList = dao.getXjztList();
		// �Ϸ�ѧԺ(����Դȡ�� dm_zju_xjzt )
		List<String> hfdm = new ArrayList<String>();
		List<String> hfmc = new ArrayList<String>();

		if (xjztList != null && xjztList.size() > 0) {
			for (int i = 0; i < xjztList.size(); i++) {
				hfdm.add(xjztList.get(i).get("zxdmmc"));
				hfmc.add(xjztList.get(i).get("zxdmmc"));
			}
		}

		// ��ʱ���е�ѧ��״̬�б�
		List<HashMap<String, String>> list = dao.getXjzt();

		return setRuleList(hfdm.toArray(new String[] {}), hfmc
				.toArray(new String[] {}), list);
	}
	
	/**
	 * ���������������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getXzqkRuleList() throws Exception {

		// ��ʱ���е���������
		List<HashMap<String, String>> list = dao.getXzqk();

		List<HashMap<String, String>> ruleList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// ʡ����
				String ssx = map.get("ssx");
				// ʡ
				String sheng = map.get("sheng");
				String shengmc = map.get("shengmc");
				shengmc = Base.isNull(shengmc) ? "" : shengmc;
				// ��
				String shi = map.get("shi");
				String shimc = map.get("shimc");
				shimc = Base.isNull(shimc) ? "" : shimc;
				// ��
				String xian = map.get("xian");
				String xianmc = map.get("xianmc");
				xianmc = Base.isNull(xianmc) ? "" : xianmc;
				// �ƶ�ǰ
				String zdq = map.get("zdq");
				// �ƶ���
				String zdh = map.get("zdh");
				
				String zdsheng = map.get("zdsheng");
				zdsheng = Base.isNull(zdsheng) ? "" : zdsheng;
				String zdshi = map.get("zdshi");
				zdshi = Base.isNull(zdshi) ? "" : zdshi;
				String zdxian = map.get("zdxian");
				zdxian = Base.isNull(zdxian) ? "" : zdxian;
				
				boolean flag = true;
				
				String mc = shengmc + shimc + xianmc;
				
				// δ�ƶ�
				if (Base.isNull(zdh)) {
					
					boolean shen_flag = true;
					boolean shi_flag = true;
					boolean xian_flag = true;
					
					if (!Base.isNull(sheng) && Base.isNull(shengmc)) {
						shen_flag = false;
					}
					if (!Base.isNull(shi) && Base.isNull(shimc)) {
						shi_flag = false;
					}
					if (!Base.isNull(xian) && Base.isNull(xianmc)) {
						xian_flag = false;
					}
					
					if (shen_flag && shi_flag && xian_flag) {
						flag = true;
					}else{
						flag = false;
					}
					
					mc = shengmc + shimc + xianmc;
				}else{
					mc = zdsheng + zdshi + zdxian;
				}
				
				
				
				map.put("dm", ssx);
				map.put("mc", mc);
				map.put("isHf", flag ? "yes" : "no");
				
				if(!Base.isNull(ssx)){
					ruleList.add(map);
				}
			}
		}

		return ruleList;
	}
	// ====================��ͬ����Ĺ����ƶ�end======================================
	
	/**
	 * ����¼���ȡ�����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBmOption(String bmmc) {
		return dao.getBmOption(bmmc);
	}
	
	/**
	 * ����¼���ȡ�꼶�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getNjOption(String nj) {
		return dao.getNjOption(nj);
	}
	
	/**
	 * ����¼���ȡרҵ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZyOption(String zymc,
			String[] searchTj) {
		return dao.getZyOption(zymc, searchTj);
	}
	
	
	/**
	 * ����¼���ȡ�༶�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getBjOption(String bjmc,
			String[] searchTj) {
		return dao.getBjOption(bjmc, searchTj);
	}
	
	
	/**
	 * ����¼���ȡѧ��״̬�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXjOption(String xjzt) {
		return dao.getXjOption(xjzt);
	}
	
	/**
	 * ����¼���ȡ���������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXzqkOption(String xzqmc, String lx,
			String[] searchTj) {
		return dao.getXzqkOption(xzqmc, lx, searchTj);
	}
}
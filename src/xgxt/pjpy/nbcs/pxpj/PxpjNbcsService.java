package xgxt.pjpy.nbcs.pxpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.nbcs.PjpyNbcsService;
import xgxt.utils.CommonQueryDAO;

public class PxpjNbcsService extends PjpyNbcsService {

	PxpjNbcsDAO dao = new PxpjNbcsDAO();

	/**
	 * �������˱���
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveDjrbl(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "xn", "bl", "bz" };

		String pk = "xn";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXn() });

		return savePjpy(saveForm, model, request);
	}

	/**
	 * ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveDjr(PjpyTyForm model, String tableName) throws Exception {

		String[] arrzd = new String[] { "djr" };
		String[] onezd = new String[] { "xn" };

		// ѧ��
		String xn = model.getXn();
		// �����
		String[] djr = model.getDjr();

		String pk = "xn||djr";
		String[] pkValue = new String[djr.length];

		// ��������
		for (int i = 0; i < djr.length; i++) {
			pkValue[i] = xn + djr[i];
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}

	/**
	 * �����ʾ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWjfp(PjpyTyForm model) throws Exception {
		return dao.saveWjfp(model);
	}

	/**
	 * ����ʾ������Ϣ
	 * 
	 * @author luojw
	 */
	public void setWjZjInfo(PjpyTyForm model, HttpServletRequest request) {

		// �ʾ���
		String id = model.getId();

		List<HashMap<String, String>> zjlxList = getWjstlxList(id);
		request.setAttribute("zjlxList", zjlxList);

		// �ʾ�ش����
		List<HashMap<String, String>> wjhdList = setWjHdInfo(model);
		// �����ͳ��
		List<HashMap<String, String>> datjList = getDaTjList(model);
		// ��ѡ����Ϣ
		List<Object> oneChooseList = new ArrayList<Object>();

		// ���������Ϣ
		setChooseList(model, zjlxList, oneChooseList, wjhdList, datjList);

		// ��ѡ������
		if (oneChooseList != null && oneChooseList.size() > 0) {
			request.setAttribute("oneSs", oneChooseList.size());
		}

		request.setAttribute("oneChooseList", oneChooseList);

	}

	/**
	 * ����ѡ�����б�
	 * 
	 * @param zjlxList
	 *            ������ͣ���ѡ����ѡ���ʴ�ȣ�
	 * @param oneChooseList
	 *            ��ѡ��
	 * @param wjhdList
	 *            �ش�
	 * @param datjList
	 *            ��ͳ��
	 * @param bzdaList
	 *            ��׼��
	 * @author luojw
	 */
	private void setChooseList(PjpyTyForm model,
			List<HashMap<String, String>> zjlxList, List<Object> oneChooseList,
			List<HashMap<String, String>> wjhdList,
			List<HashMap<String, String>> datjList) {

		// �ʾ���
		String id = model.getId();

		for (HashMap<String, String> map : zjlxList) {

			// ��������
			String stlx = map.get("lxdm");
			model.setStlx(stlx);

			// ��������
			List<HashMap<String, String>> mcList = getWjstmcList(id, stlx);
			// �����
			List<HashMap<String, String>> daList = getWjstdaList(id, stlx);
			// ��������
			List<HashMap<String, String>> stssList = getStssList(model);
			// ���
			int n = 1;

			for (HashMap<String, String> ssMap : stssList) {

				HashMap<String, Object> rsMap = new HashMap<String, Object>();

				rsMap.put("num", changNumLx(String.valueOf(n)));
				rsMap.put("stss", ssMap.get("stss"));
				rsMap.put("ssmc", ssMap.get("ssmc"));

				int stnum = 0;
				n++;

				List<HashMap<String, Object>> stList = new ArrayList<HashMap<String, Object>>();

				for (HashMap<String, String> mcMap : mcList) {

					if (rsMap.get("stss").equals(mcMap.get("stss"))) {

						stnum++;

						HashMap<String, Object> info = new HashMap<String, Object>();

						String stbh = mcMap.get("stbh");
						String stmc = mcMap.get("stmc");

						List<HashMap<String, String>> stdaList = new ArrayList<HashMap<String, String>>();

						for (HashMap<String, String> daMap : daList) {

							if (daMap.get("stbh").equalsIgnoreCase(stbh)) {

								daMap.put("isChecked", "false");

								// �жϸô��Ƿ�ѡ��
								for (HashMap<String, String> hdMap : wjhdList) {

									if (hdMap.get("fpbh")
											.equalsIgnoreCase(stbh)
											&& hdMap.get("dabh")
													.equalsIgnoreCase(
															daMap.get("dabh"))) {
										daMap.put("isChecked", "true");
										daMap.put("danr", hdMap.get("danr"));
									}
								}

								stdaList.add(daMap);
							}
						}

						// ������
						String danum = (stdaList != null && stdaList.size() > 0) ? String
								.valueOf(stdaList.size())
								: "0";

						info.put("stbh", stbh);// ������
						info.put("stmc", stmc);// ��������
						info.put("danum", danum);// ������
						info.put("stda", stdaList);// ���б�

						stList.add(info);
					}
				}

				rsMap.put("stnum", stnum);
				rsMap.put("stList", stList);

				oneChooseList.add(rsMap);

			}
		}
	}
	
	/**
	 * ����ƽ������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean savePxpj(PjpyTyForm model, RequestForm rForm)
			throws Exception {

		String[] onezd = new String[] { "xhzgh", "lx", "wjbh", "bpjr" };
		String[] arrzd = new String[] { "fpbh", "dabh" };

		// ����
		String tableName = rForm.getRealTable();
		//�����ֶ�
		String pk = "xhzgh||lx||wjbh||bpjr||fpbh||dabh";
		// ��ѡ����
		String[] oneChooseBh = model.getOneChooseBh();
		// ��ѡ���
		String[] oneChooseDa = model.getOneChooseDa();
		
		// �ش���ѧ�ţ�ְ���ţ�
		String xhzgh = rForm.getUserName();
		// �ش�������
		String lx = rForm.getUserType();
		// �ʾ���
		String wjbh = model.getId();
		// ��������
		String bpjr = model.getBpjr();
		
		// ȷ�������������ݵ�����
		int size = (oneChooseDa != null && oneChooseDa.length > 0) ? oneChooseDa.length
				: 0;	
		
		// ����
		String[] pkValue = new String[size];
		// ������
		String[] fpbh = new String[size];
		// �𰸱��
		String[] dabh = new String[size];
		
		int n = 0;

		//��ѡ��
		if(oneChooseDa != null && oneChooseDa.length>0){
			for(int i=0;i<oneChooseDa.length;i++){
				fpbh[n] = oneChooseBh[i];
				dabh[n] = oneChooseDa[i];
				pkValue[n] = xhzgh + lx + wjbh + bpjr + oneChooseBh[i]
						+ oneChooseDa[i];
				n++;
			}
		}	
		
		// ������Ҫ������ֵ
		model.setXhzgh(xhzgh);
		model.setLx(lx);
		model.setWjbh(wjbh);
		model.setFpbh(fpbh);
		model.setDabh(dabh);
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	/**
	 * ������ش���б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> setWjHdInfo(PjpyTyForm model) {
		return dao.setWjHdInfo(model);
	}
	
	/**
	 * �ж��Ƿ�����
	 * 
	 * @author luojw
	 */
	public Boolean isDjr(PjpyTyForm model) {
		return dao.isDjr(model);
	}
	
	/**
	 * ������ش���б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getTjjgList(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		//�༶��Ϣ
		List<HashMap<String, String>> tjbjList =dao.getTjbjList(model);
		//ѧ����Ϣ
		List<HashMap<String, String>> tjxsList =dao.getTjxsList(model);
		//����ֵ
		List<HashMap<String, String>> list =new ArrayList<HashMap<String, String>>();
		
		if (tjbjList != null && tjbjList.size() > 0) {

			for (int i = 0; i < tjbjList.size(); i++) {
				
				HashMap<String, String> bjMap = tjbjList.get(i);
				
				// �ʾ���
				String id = bjMap.get("id");
				// �༶����
				String bjdm = bjMap.get("bjdm");
				// �༶����
				String bjrs = bjMap.get("bjrs");
				// A����Ŀ
				int a_level_num = 0;
				// ӵ��A��
				boolean a_level = false;
				// ӵ��B��
				boolean b_level = false;
				// ӵ��C��
				boolean c_level = false;
				
				if (tjxsList != null && tjxsList.size() > 0) {

					for (int j = 0; j < tjxsList.size(); j++) {
						
						HashMap<String, String> xsMap = tjxsList.get(j);
						
						// �ʾ���
						String wjbh = xsMap.get("wjbh");
						// �༶����
						String xsbj = xsMap.get("bjdm");
						// ����
						String pj = xsMap.get("pj");
						
						if (id.equalsIgnoreCase(wjbh)
								&& bjdm.equalsIgnoreCase(xsbj)) {

							if ("A".equalsIgnoreCase(pj)) {
								a_level_num++;
								a_level = true;
							} else if ("B".equalsIgnoreCase(pj)) {
								b_level = true;
							} else if ("C".equalsIgnoreCase(pj)) {
								c_level = true;
							}
						}
					}
				}
				
				// A������
				float a_level_bl = (Base.isNull(bjrs) || "0"
						.equalsIgnoreCase(bjrs)) ? 0 : a_level_num
						/ Float.parseFloat(bjrs) * 100;

				String msg = "ͨ��";
				
				// ��������40%��ȫ�������Ч���÷���Ŀǰ��������
				if (a_level_bl > 40) {
					msg = "A����������40%";
				}
				// ABC��������Ҫ�У�����÷�����Ч
				if (!a_level || !b_level || !c_level) {
					msg = "û��ͬʱӵ��ABC��������ķ���";
				}
				
				// �Ƿ���Ч
				String sfyx = ("ͨ��".equalsIgnoreCase(msg)) ? "��" : "��";

				bjMap.put("sfyx", sfyx);
				bjMap.put("msg", msg);

				list.add(bjMap);
			}

		}
		
		// ���ֵ
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		// �Ƿ���Ч
		String yx = model.getSfkq();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);

				// �ʾ���
				String sfyx = map.get("sfyx");
				if (!Base.isNull(yx)) {
					if (sfyx.equalsIgnoreCase(yx)) {
						resultList.add(map);
					}
				} else {
					resultList.add(map);
				}
			}
		}

		return getResultList(resultList, model);
	}
	
	/**
	 * ���ѧ�������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxList(PjpyTyForm model) {
		return dao.getXsxxList(model);
	}
	
	/**
	 * ���ѧ�������б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @ ע����Ҫ��model���xh,xn����ţ�Ҫ�ٶȿ����԰�xydm��zydm��bjdm��njҲ�Ž�ȥ
	 */
	public List<HashMap<String, String>> getPxpjInfo(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// ѧ��
		String xh = model.getXh();

		// ���ֵ
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		// �༶��Ϣ
		List<HashMap<String, String>> bjxxList = getTjjgList(model);
		// ͳ���б�
		List<HashMap<String, String>> xsxxList = dao.getTjxsList(model);

		if (xsxxList != null && xsxxList.size() > 0) {
			
			for (int i = 0; i < xsxxList.size(); i++) {
				
				HashMap<String, String> xsMap = xsxxList.get(i);
				// �༶����
				String bjdm = xsMap.get("bjdm");
				// ��������
				String bpjr = xsMap.get("bpjr");
				
				boolean flag = false;
				
				if (bpjr.equalsIgnoreCase(xh)) {

					if (bjxxList != null && bjxxList.size() > 0) {

						for (int j = 0; j < bjxxList.size(); j++) {
							
							HashMap<String, String> bjMap = bjxxList.get(j);
							
							if ((bjdm.equalsIgnoreCase(bjMap.get("bjdm")))
									&& ("��".equalsIgnoreCase(bjMap.get("sfyx")))) {
								flag = true;
							}
						}
					}
				}
				
				if(flag){
					xsMap.put("pj", "��");
					xsMap.put("pjf", "0");
				}
				
				resultList.add(xsMap);
			}
		}
		
		return resultList;
	}
	
	/**
	 * �������۷�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean savePjf(PjpyTyForm model, String tableName) throws Exception {

		String[] arrzd = new String[] { "pjf", "fpxh" };
		String[] onezd = new String[] { "wjbh" };

		// �ʾ���
		String wjbh = model.getWjbh();
		// ���۷�
		String[] fpxh = model.getFpxh();

		String pk = "wjbh||fpxh";
		String[] pkValue = new String[fpxh.length];

		// ��������
		for (int i = 0; i < fpxh.length; i++) {
			pkValue[i] = wjbh + fpxh[i];
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	
	/**
	 * ���ѧ�������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXscfList(PjpyTyForm model) {
		return dao.getXscfList(model);
	}
}

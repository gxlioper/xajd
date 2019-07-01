package xgxt.pjpy.nbcs;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.GlobalsVariable;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.date.MoneyFormat;

public class PjpyNbcsService extends PjpyTyService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	PjpyNbcsDAO dao = new PjpyNbcsDAO();
	
	/**
	 * �����ʾ���Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWjInfo(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "xn", "xq", "nd", "wjmc", "jlsj","sfkq", "bz" };

		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getId() });

		return savePjpy(saveForm, model, request);
	}
	
	/**
	 * �����ʾ���Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateWjInfo(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "wjmc", "sfkq", "bz" };

		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getId() });

		return updatePjpy(saveForm, model);
	}

	/**
	 * ������������
	 * 
	 * @author luojw
	 */
	public String getStbh() {
		return dao.getStbh();
	}
	
	/**
	 * ����������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveStInfo(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "stbh", "stlx", "stmc", "stss", "bz" };

		String pk = "stbh";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getStbh() });

		return savePjpy(saveForm, model, request);
	}
	
	/**
	 * �������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveDaInfo(PjpyTyForm model) throws Exception {

		String[] arrzd = new String[] { "dabh", "damc","bzda" };
		String[] onezd = new String[] { "stbh" };

		String tableName="wjdc_stdab";
		String stlx = model.getStlx();
		
		String[] bzda = null;
		String[] damc = null;
		String[] dabh = null;
		
		if("oneChoose".equalsIgnoreCase(stlx)){//��ѡ��
			dabh = model.getDabhoneChoose();
			damc = model.getDamconeChoose();
			bzda = model.getBzdaoneChoose();
		}else if("allChoose".equalsIgnoreCase(stlx)){//��ѡ��
			dabh = model.getDabhallChoose();
			damc = model.getDamcallChoose();
			bzda = model.getBzdaallChoose();
		}else if("questions".equalsIgnoreCase(stlx)){//�ʴ���
			dabh = model.getDabh();
			damc = model.getDamc();
			bzda = model.getBzda();
		}
		
		// ��׼�𰸸�ֵ
		if (dabh != null && dabh.length > 0
				&& !"questions".equalsIgnoreCase(stlx)) {
			
			String[] arr_bzda = new String[dabh.length];
			
			for (int i = 0; i < dabh.length; i++) {
				
				arr_bzda[i] = "no";
				
				if (bzda != null && bzda.length > 0) {
					
					for (int j = 0; j < bzda.length; j++) {
						
						if (dabh[i].equalsIgnoreCase(bzda[j])) {
							
							arr_bzda[i] = "yes";
							
							break;
							
						}
					}
				}
			}
			model.setBzda(arr_bzda);
		}
		
		model.setDabh(dabh);
		model.setDamc(damc);
		
		String pk = "stbh";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getStbh() });

		return savePjpy(saveForm, model);
	}
	
	/**
	 * ɾ��������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delStInfo(PjpyTyForm model) throws Exception {

		boolean result = false;

		String[] stbh = model.getPrimarykey_checkVal();
		String realTable = "wjdc_stxxb";

		if (stbh != null && stbh.length > 0) {
			String pk = "stbh";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(stbh);

			result = delPjpy(saveForm, model);
		}

		return result;
	}
	
	/**
	 * ɾ������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delDaInfo(PjpyTyForm model) throws Exception {
		
		boolean result = false;

		String[] stbh = model.getPrimarykey_checkVal();
		String realTable = "wjdc_stdab";

		if (stbh != null && stbh.length > 0) {
			String pk = "stbh";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(stbh);

			result = delPjpy(saveForm, model);
		}

		return result;
	}

	/**
	 * ���������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZjInfo(PjpyTyForm model,String tableName) throws Exception {

		String[] arrzd = new String[] { "fpbh" };
		String[] onezd = new String[] { "id" };
		
		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getId() });

		return savePjpy(saveForm, model);
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
		List<HashMap<String, String>> wjhdList = setWjHdList(model);
		// �����ͳ��
		List<HashMap<String, String>> datjList=getDaTjList(model);
		// ��׼��
		List<HashMap<String, String>> bzdaList = getBzdaList(model);
		// ��ѡ����Ϣ
		List<Object> oneChooseList = new ArrayList<Object>();
		// ��ѡ����Ϣ
		List<Object> allChooseList = new ArrayList<Object>();
		// �ʴ�����Ϣ
		List<Object> questionsList = new ArrayList<Object>();
		
		// ���������Ϣ
		setChooseList(model, zjlxList, oneChooseList, allChooseList,
				questionsList, wjhdList, datjList, bzdaList);

		// ��ѡ������
		if (oneChooseList != null && oneChooseList.size() > 0) {
			request.setAttribute("oneSs", oneChooseList.size());
		}
		
		// ��ѡ������
		if (allChooseList != null && allChooseList.size() > 0) {
			request.setAttribute("allSs", allChooseList.size());
		}
		
		// �ʴ�������
		if (questionsList != null && questionsList.size() > 0) {
			request.setAttribute("queSs", questionsList.size());
		}
		
		request.setAttribute("oneChooseList", oneChooseList);
		request.setAttribute("allChooseList", allChooseList);
		request.setAttribute("questionsList", questionsList);

	}

	/**
	 * ����ѡ�����б�
	 * 
	 * @param zjlxList
	 *            ������ͣ���ѡ����ѡ���ʴ�ȣ�
	 * @param oneChooseList
	 *            ��ѡ��
	 * @param allChooseList
	 *            ��ѡ��
	 * @param questionsList
	 *            �ʴ���
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
			List<Object> allChooseList, List<Object> questionsList,
			List<HashMap<String, String>> wjhdList,
			List<HashMap<String, String>> datjList,
			List<HashMap<String, String>> bzdaList) {
		
		// �ʾ���
		String id = model.getId();
		
		for (HashMap<String, String> map : zjlxList) {
			
			//��������
			String stlx = map.get("lxdm");
			model.setStlx(stlx);
			
			// ��������
			List<HashMap<String, String>> mcList = getWjstmcList(id, stlx);
			// �����
			List<HashMap<String, String>> daList = getWjstdaList(id, stlx);
			// �ش��
			List<HashMap<String, String>> hddaList = getHddaList(model);
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
				
				List<HashMap<String,Object>> stList = new ArrayList<HashMap<String,Object>>();
				
				for (HashMap<String, String> mcMap : mcList) {
		
					if (rsMap.get("stss").equals(mcMap.get("stss"))) {
						
						stnum++;
						
						HashMap<String, Object> info = new HashMap<String, Object>();

						String stbh = mcMap.get("stbh");
						String stmc = mcMap.get("stmc");

						// ��׼��
						String bzda = "";

						for (HashMap<String, String> bzdaMap : bzdaList) {
							if (bzdaMap.get("stbh").equalsIgnoreCase(stbh)) {
								bzda += bzdaMap.get("dabh") + ",";
							}
						}

						// �ش��
						String hdda = "";

						for (HashMap<String, String> hddaMap : hddaList) {
							if (hddaMap.get("stbh").equalsIgnoreCase(stbh)) {
								hdda += hddaMap.get("dabh") + ",";
							}
						}

						List<HashMap<String, String>> stdaList = new ArrayList<HashMap<String, String>>();

						for (HashMap<String, String> daMap : daList) {

							if (daMap.get("stbh").equalsIgnoreCase(stbh)) {

								daMap.put("isChecked", "false");

								// ��ͳ��
								for (HashMap<String, String> tjMap : datjList) {
									if (tjMap.get("stbh")
											.equalsIgnoreCase(stbh)
											&& tjMap.get("dabh")
													.equalsIgnoreCase(
															daMap.get("dabh"))) {

										daMap.put("num", tjMap.get("num"));
										daMap.put("rs", tjMap.get("rs"));
									}
								}

								// �жϸô��Ƿ�ѡ��
								for (HashMap<String, String> hdMap : wjhdList) {
									
									if (hdMap.get("fpbh").equalsIgnoreCase(stbh)
											&& hdMap.get("dabh").equalsIgnoreCase(daMap.get("dabh"))) {
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

						bzda = Base.isNull(bzda) ? bzda : bzda.substring(0,
								bzda.length() - 1);
						hdda = Base.isNull(hdda) ? hdda : hdda.substring(0,
								hdda.length() - 1);
						// ���Ƿ���ȷ
						String isTrue = (isPp(bzda, hdda)) ? "�ش���ȷ"
								: "�ش����";

						info.put("stbh", stbh);// ������
						info.put("stmc", stmc);// ��������
						info.put("bzda", bzda);// ��׼��
						info.put("isTrue", isTrue);// �Ƿ���ȷ
						info.put("danum", danum);// ������
						info.put("stda", stdaList);// ���б�

						stList.add(info);
					}
				}
				
				rsMap.put("stnum", stnum);
				rsMap.put("stList", stList);
				
				if (GlobalsVariable.WJDC_ONECHOOSE.equalsIgnoreCase(stlx)) {// ��ѡ��
					oneChooseList.add(rsMap);
				} else if (GlobalsVariable.WJDC_ALLCHOOSE.equalsIgnoreCase(stlx)) {// ��ѡ��
					allChooseList.add(rsMap);
				}else if (GlobalsVariable.WJDC_QUESTIONS.equalsIgnoreCase(stlx)) {// �ʴ���
					questionsList.add(rsMap);
				}
			}
		}
	}
	
	/**
	 * ���ָ���ʾ����������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstlxList(String id) {
		return dao.getWjstlxList(id);
	}

	/**
	 * ���ָ���ʾ��������������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstmcList(String id, String stlx) {
		return dao.getWjstmcList(id, stlx);
	}
	
	/**
	 * ����������Ϣ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstdaList(String id, String stlx){
		return dao.getWjstdaList(id, stlx);
	}

	/**
	 * ����ش��ߴ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveHdzDa(PjpyTyForm model, RequestForm rForm)
			throws Exception {

		String[] onezd = new String[] { "xhzgh", "lx", "wjbh" };
		String[] arrzd = new String[] { "fpbh", "dabh", "danr" };

		// ����
		String tableName = rForm.getRealTable();
		// �����ֶ�
		String pk = "xhzgh||lx||wjbh||fpbh";
		// ��ѡ����
		String[] oneChooseBh = model.getOneChooseBh();
		// ��ѡ���
		String[] oneChooseDa = model.getOneChooseDa();
		// ��ѡ����
		String[] allChooseBh = model.getAllChooseBh();
		// ��ѡ���
		String[] allChooseDa = model.getAllChooseDa();
		// �ʴ�����
		String[] questionsBh = model.getQuestionsBh();
		// �ʴ����
		String[] questionsDa = model.getQuestionsDa();
		// �ش���ѧ�ţ�ְ���ţ�
		String xhzgh = rForm.getUserName();
		// �ش�������
		String lx = rForm.getUserType();
		// �ʾ���
		String wjbh = model.getId();

		// ȷ�������������ݵ�����
		int size = 0;

		if (allChooseDa != null && allChooseDa.length > 0) {
			for (String dxda : allChooseDa) {
				if (!Base.isNull(dxda)) {
					String[] arrDa = dxda.split("!!@@!!");
					size += (arrDa != null && arrDa.length > 0) ? arrDa.length
							: 0;
				}
			}
		}

		if (oneChooseDa != null && oneChooseDa.length > 0) {
			size += oneChooseDa.length;
		}

		if (questionsBh != null && questionsBh.length > 0) {
			size += questionsBh.length;
		}

		// ����
		String[] pkValue = new String[size];
		// ������
		String[] fpbh = new String[size];
		// �𰸱��
		String[] dabh = new String[size];
		// ������
		String[] danr = new String[size];

		int n = 0;

		// ��ѡ��
		if (oneChooseDa != null && oneChooseDa.length > 0) {
			for (int i = 0; i < oneChooseDa.length; i++) {
				fpbh[n] = oneChooseBh[i];
				dabh[n] = oneChooseDa[i];
				danr[n] = "";
				pkValue[n] = xhzgh + lx + wjbh + oneChooseBh[i];
				n++;
			}
		}

		// ��ѡ��
		if (allChooseDa != null && allChooseDa.length > 0) {

			for (int i = 0; i < allChooseDa.length; i++) {
				// ��ѡ��
				String dxda = allChooseDa[i];

				if (!Base.isNull(dxda)) {

					String[] arrDa = dxda.split("!!@@!!");

					for (String da : arrDa) {
						fpbh[n] = allChooseBh[i];
						dabh[n] = da;
						danr[n] = "";
						pkValue[n] = xhzgh + lx + wjbh + allChooseBh[i];
						n++;
					}

				}
			}
		}

		// �ʴ���
		if (questionsBh != null && questionsBh.length > 0) {
			for (int i = 0; i < questionsBh.length; i++) {
				fpbh[n] = questionsBh[i];
				dabh[n] = "answer";
				danr[n] = questionsDa[i];
				pkValue[n] = xhzgh + lx + wjbh + questionsBh[i];
				n++;
			}
		}

		// ������Ҫ������ֵ
		model.setXhzgh(xhzgh);
		model.setLx(lx);
		model.setWjbh(wjbh);
		model.setFpbh(fpbh);
		model.setDabh(dabh);
		model.setDanr(danr);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	/**
	 * ����ʾ�ش����
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> setWjHdList(PjpyTyForm model) {
		return dao.setWjHdInfo(model);
	}
	
	/**
	 * ��������ͳ��
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDaTjList(PjpyTyForm model) {
		return dao.getDaTjList(model);
	}
	
	/**
	 * ��������׼��
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzdaList(PjpyTyForm model) {
		return dao.getBzdaList(model);
	}
	
	/**
	 * ������ش���б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getHddaList(PjpyTyForm model) {
		return dao.getHddaList(model);
	}
	
	/**
	 * ������������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getStssList(PjpyTyForm model) {
		return dao.getStssList(model);
	}
	
	/**
	 * ɾ�������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delZjxx(PjpyTyForm model, String pk) throws Exception {

		String[] pkValue = model.getPrimarykey_checkVal();
		boolean flag = false;

		if (pkValue != null && pkValue.length > 0) {

			String tableName = "wjdc_zjb";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			flag = delPjpy(saveForm, model);

		}

		return flag;
	}

	/**
	 * ɾ���ش���Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delHdxx(PjpyTyForm model, String pk) throws Exception {

		String[] pkValue = model.getPrimarykey_checkVal();
		boolean flag = false;

		if (pkValue != null && pkValue.length > 0) {

			String tableName = "wjdc_hdb";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			flag = delPjpy(saveForm, model);

		}

		return flag;
	}
	
	/**
	 * ת�����ָ�ʽΪ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String changNumLx(String num) {	

		// ���ִ�д
		String[] dx = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��", "ʰ",
				"��", "Ǫ", "��", "��" };

		// ������Э
		String[] xx = { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ",
				"��", "ǧ", "��", "��" };
		
		String chineseNum = MoneyFormat.format(String.valueOf(num));
		
		chineseNum = chineseNum.replace("Ԫ��", "");
		
		if(!Base.isNull(chineseNum)){
			
			char[] charNum =  new char[chineseNum.length()];
			String [] arrNum =  new String[chineseNum.length()];
			
			for (int i = 0; i < chineseNum.length(); i++) {
				charNum[i] = chineseNum.charAt(i);
			}
			
			for (int i = 0; i < charNum.length; i++) {

				for (int j = 0; j < dx.length; j++) {

					if (String.valueOf(charNum[i]).equalsIgnoreCase(dx[j])) {
						arrNum[i] = xx[j];
					}
				}
			}
			
			if (arrNum != null && arrNum.length > 0) {
				chineseNum = "";
				for (String number : arrNum) {
					chineseNum += number;
				}
			}
		}
		
		return chineseNum;
	}
	
	/**
	 * ȷ����׼����ش���Ƿ�ƥ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean isPp(String bzda, String hdda) {

		boolean flag = false;

		if (!Base.isNull(bzda) && !Base.isNull(hdda)
				&& bzda.length() == hdda.length()) {

			// ��׼��
			String[] arr_bzda = bzda.split(",");
			// �ش��
			String[] arr_hdda = hdda.split(",");
			// �Ƿ�ƥ��
			String[] pp = new String[arr_bzda.length];

			for (int i = 0; i < arr_hdda.length; i++) {
				pp[i] = "no";
				for (int j = 0; j < arr_bzda.length; j++) {
					if (arr_hdda[i].equalsIgnoreCase(arr_bzda[j])) {
						pp[i] = "yes";
						break;
					}
				}
			}

			flag = true;
			for (int i = 0; i < pp.length; i++) {
				if ("no".equalsIgnoreCase(pp[i])) {
					flag = false;
					;
				}
			}
		}
		return flag;
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
	 * �����������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void hsqkToExcel(PjpyTyForm model, OutputStream os) throws Exception {

		// =====================��ó�ʼֵ====================

		// ͳ�Ʊ�
		String tableName = "view_wjdc_hsqktj";
		// ͳ���б�
		String[] colList = new String[] { "nj", "xydm", "xymc", "hsnum",
				"ffnum", "njnum" };

		String[] queryList = new String[] { "id" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		List<HashMap<String, String>> tjList = CommonQueryDAO
				.commonQueryforList(tableName, query, myQuery.getInputList(),
						colList, "");
		// �꼶�б�
		List<HashMap<String, String>> njList = getNewZdList(tjList, "nj");
		int njSize = (njList != null && njList.size() > 0) ? njList.size() : 0;
		// ������
		int size = 4 + njSize;
		// �ʾ���
		String id = model.getId();
		// �ʾ�����
		String wjmc = getOneValue("wjdc_wjxxb", "wjmc", "id", id);
		// ����
		String title = wjmc + "���������";
		// =====================end====================

		// =====================��ʼ��Excel��ʽ====================
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// =====================end====================

		// =====================������====================

		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, size, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// =====================end====================

		// =====================����ͷ====================
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		ws.addCell(new Label(0, 2, "���", wcf));
		ws.addCell(new Label(1, 2, Base.YXPZXY_KEY, wcf));

		if (njSize > 0) {// ѭ������꼶
			for (int i = 0; i < njList.size(); i++) {

				HashMap<String, String> niMap = njList.get(i);

				ws.addCell(new Label(2 + i, 2, niMap.get("njdm") + "��", wcf));
			}
		}

		ws.addCell(new Label(2 + njSize, 2, "���պϼ�", wcf));
		ws.addCell(new Label(3 + njSize, 2, "�����ʾ�", wcf));
		ws.addCell(new Label(4 + njSize, 2, "������", wcf));

		// =====================end====================

		// =====================���ͳ������====================

		ws.addCell(new Label(0, 2, "���", wcf));
		ws.addCell(new Label(1, 2,Base.YXPZXY_KEY, wcf));

		if (njSize > 0) {// ѭ������꼶

			// ���
			int n = 0;
			// ���պϼ�
			float hshj = 0;
			// ���źϼ�
			float ffhj = 0;
			// �Ƚ�ѧԺ
			String bjxy = "";
			// ʵʱ�꼶��Ŀ
			int nowNum = 0;
			
			// �����꼶�ܼ�
			int[] njzj = new int[njSize];
			// �����ܼ�
			float hszj = 0;
			// �����ܼ�
			float ffzj = 0;
			
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> map = tjList.get(i);

				// �꼶��Ŀ
				String njnum = map.get("njnum");
				// �꼶
				String nj = map.get("nj");
				// ѧԺ����
				String xydm = map.get("xydm");
				// ѧԺ����
				String xymc = map.get("xymc");
				// ������Ŀ
				String hsnum = map.get("hsnum");
				// ������Ŀ
				String ffnum = map.get("ffnum");

				// ���
				ws.addCell(new Label(0, 3 + n, String.valueOf(n + 1), wcf));
				// ѧԺ����
				ws.addCell(new Label(1, 3 + n, xymc, wcf));

				for (int j = 0; j < njList.size(); j++) {

					ws.addCell(new Label(2 + j, 3 + n, "0", wcf));// ������Ŀ
					
					if (nj.equalsIgnoreCase(njList.get(j).get("njdm"))) {

						hshj += Float.parseFloat(hsnum);// �ۼӻ���
						ffhj += Float.parseFloat(ffnum);// �ۼӷ���
						
						njzj[j] += Integer.parseInt(hsnum);
						
						ws.addCell(new Label(2 + j, 3 + n, hsnum, wcf));// ������Ŀ

					} 
				}						
				
				if(Base.isNull(bjxy)){
					bjxy = xydm;
					nowNum += Integer.parseInt(njnum);
				}
				
				if (i == nowNum - 1) {
					
					// ���պ�
					ws.addCell(new Label(2 + njSize, 3 + n, String
							.valueOf(hshj), wcf));
					// ���źϼ�
					ws.addCell(new Label(3 + njSize, 3 + n, String
							.valueOf(ffhj), wcf));

					// ������
					String hsl = "0";
					if (ffhj != 0) {
						hsl = String.valueOf((hshj / ffhj) * 100);
						hsl = (hsl.length() > 5) ? hsl.substring(0, 5) : hsl;
					}
					ws.addCell(new Label(4 + njSize, 3 + n, hsl + "%", wcf));
					
					hszj += hshj;
					ffzj += ffhj;
					
					hshj = 0;
					ffhj = 0;
					bjxy = "";
					
					n++;
				}
			}
			
			// ���һ�кϼ�
			ws.addCell(new Label(0, 3 + n, "�ϼ�", wcf));
			ws.mergeCells(0, 3 + n, 1, 3 + n);
			ws.addCell(new Label(2 + njSize, 3 + n, String.valueOf(hszj), wcf));
			ws.addCell(new Label(3 + njSize, 3 + n, String.valueOf(ffzj), wcf));
			
			// ������
			String hsl = "0";
			if (ffzj != 0) {
				hsl = String.valueOf((hszj / ffzj) * 100);
				hsl = (hsl.length() > 5) ? hsl.substring(0, 5) : hsl;
			}
			
			ws.addCell(new Label(4 + njSize, 3 + n, hsl + "%", wcf));
			
			for (int i = 0; i < njList.size(); i++) {
				ws.addCell(new Label(2 + i, 3 + n, String.valueOf(njzj[i]), wcf));
			}			
		}
		// =====================end====================	
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}	
	
	/**
	 * �������ͳ��
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void jgtjToExcel(PjpyTyForm model, OutputStream os,HttpServletRequest request) throws Exception {

		// =====================��ó�ʼֵ====================
		// �ʾ���
		String id = model.getId();
		// �ʾ�����
		String wjmc = getOneValue("wjdc_wjxxb", "wjmc", "id", id);
		// ����
		String title = wjmc + "ѡ��Ƶ��ͳ�Ʊ�";
		// ������ͳ����Ϣ
		setWjZjInfo(model, request);
		// ��ѡ��
		List<HashMap<String, Object>> oneChooseList = (List<HashMap<String, Object>>) request
				.getAttribute("oneChooseList");
		// ��ѡ��
		List<HashMap<String, Object>> allChooseList = (List<HashMap<String, Object>>) request
				.getAttribute("allChooseList");
		// =====================end====================

		// =====================��ʼ��Excel��ʽ====================
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat nrWcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		nrWcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		// =====================end====================

		// =====================������====================

		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// =====================end====================
		
		// =====================����ͷ====================

		ws.addCell(new Label(0, 2, "���", wcf));
		ws.addCell(new Label(1, 2, "���", wcf));
		ws.addCell(new Label(2, 2, "��������", wcf));;
		ws.addCell(new Label(3, 2, "��������", wcf));
		ws.addCell(new Label(4, 2, "ѡ��", wcf));
		ws.addCell(new Label(5, 2, "ѡ������", wcf));
		ws.addCell(new Label(6, 2, "������", wcf));
		ws.addCell(new Label(7, 2, "��ռ����", wcf));
		
		// =====================end====================
		
		// =====================���ͳ������====================
		// ��¼��
		int n = 0;
		// ���
		int xuh = 1;
		// �ۺ�
		int zhb = 0; 
		
		//��ѡ��
		if (oneChooseList != null && oneChooseList.size() > 0) {
			
			for (int i = 0; i < oneChooseList.size(); i++) {
				
				HashMap<String, Object> ssMap = oneChooseList.get(i);
				
				// ��������
				List<HashMap<String, Object>> stList = (List<HashMap<String, Object>>) ssMap
						.get("stList");
				
				if (stList != null && stList.size() > 0) {

					for (int j = 0; j < stList.size(); j++) {

						HashMap<String, Object> stMap = stList.get(j);

						// ������
						String stbh = (String) stMap.get("stbh");
						// ��������
						String stmc = (String) stMap.get("stmc");
						// ������
						String danum = (String) stMap.get("danum");
						// �����
						List<HashMap<String, Object>> daList = (List<HashMap<String, Object>>) stMap
								.get("stda");					

						// ��ʼ�ϲ�
						int hbnum =Base.isNull(danum) ? 0 : Integer
								.parseInt(danum);
						
						ws.mergeCells(0, 3 + zhb, 0, 2 + +zhb + hbnum);
						ws.mergeCells(1, 3 + zhb, 1, 2 + +zhb + hbnum);
						ws.mergeCells(2, 3 + zhb, 2, 2 + +zhb + hbnum);
						ws.mergeCells(3, 3 + zhb, 3, 2 + +zhb + hbnum);
						
						zhb += hbnum;
						
						if (daList != null && daList.size() > 0) {

							for (int k = 0; k < daList.size(); k++) {
								
								HashMap<String, Object> daMap = daList.get(k);
								
								// �𰸱��
								String dabh = (String) daMap.get("dabh");
								// ѡ������
								String num = (String) daMap.get("num");
								// ������
								String rs = (String) daMap.get("rs");
								// ��ռ����
								String bl = "";
								// ������ռ����
								if(!Base.isNull(rs)&& !"0".equalsIgnoreCase(rs)){
									bl = String.valueOf((Float.parseFloat(num)/Float.parseFloat(rs)*100));
								}
														
								ws.addCell(new Label(0, 3 + n, String.valueOf(xuh),wcf));// ���
								ws.addCell(new Label(1, 3 + n, stbh, nrWcf));// ������
								ws.addCell(new Label(2, 3 + n, "��ѡ��", nrWcf));// ��������
								ws.addCell(new Label(3, 3 + n, stmc, nrWcf));// ��������
								ws.addCell(new Label(4, 3 + n, dabh, nrWcf));// �𰸱��
								ws.addCell(new Label(5, 3 + n, num, nrWcf));// ѡ������
								ws.addCell(new Label(6, 3 + n, rs, nrWcf));// ������
								ws.addCell(new Label(7, 3 + n, bl + "%", nrWcf));// ����
								
								n++;
							}
							
							xuh ++;
						}	
					}
				}
			}
		}
		
		// ��ѡ��
		if (allChooseList != null && allChooseList.size() > 0) {
			
			for (int i = 0; i < allChooseList.size(); i++) {
				
				HashMap<String, Object> ssMap = allChooseList.get(i);
				
				// ��������
				List<HashMap<String, Object>> stList = (List<HashMap<String, Object>>) ssMap
						.get("stList");
				
				if (stList != null && stList.size() > 0) {

					for (int j = 0; j < stList.size(); j++) {

						HashMap<String, Object> stMap = stList.get(j);

						// ������
						String stbh = (String) stMap.get("stbh");
						// ��������
						String stmc = (String) stMap.get("stmc");
						// ������
						String danum = (String) stMap.get("danum");
						// �����
						List<HashMap<String, Object>> daList = (List<HashMap<String, Object>>) stMap
								.get("stda");					

						// ��ʼ�ϲ�
						int hbnum =Base.isNull(danum) ? 0 : Integer
								.parseInt(danum);
						
						ws.mergeCells(0, 3 + zhb, 0, 2 + +zhb + hbnum);
						ws.mergeCells(1, 3 + zhb, 1, 2 + +zhb + hbnum);
						ws.mergeCells(2, 3 + zhb, 2, 2 + +zhb + hbnum);
						ws.mergeCells(3, 3 + zhb, 3, 2 + +zhb + hbnum);
						
						zhb += hbnum;
						
						if (daList != null && daList.size() > 0) {

							for (int k = 0; k < daList.size(); k++) {
								
								HashMap<String, Object> daMap = daList.get(k);
								
								// �𰸱��
								String dabh = (String) daMap.get("dabh");
								// ѡ������
								String num = (String) daMap.get("num");
								// ������
								String rs = (String) daMap.get("rs");
								// ��ռ����
								String bl = "";
								// ������ռ����
								if(!Base.isNull(rs)&& !"0".equalsIgnoreCase(rs)){
									bl = String.valueOf((Float.parseFloat(num)/Float.parseFloat(rs)*100));
								}
														
								ws.addCell(new Label(0, 3 + n, String.valueOf(xuh),wcf));// ���
								ws.addCell(new Label(1, 3 + n, stbh, nrWcf));// ������
								ws.addCell(new Label(2, 3 + n, "��ѡ��", nrWcf));// ��������
								ws.addCell(new Label(3, 3 + n, stmc, nrWcf));// ��������
								ws.addCell(new Label(4, 3 + n, dabh, nrWcf));// �𰸱��
								ws.addCell(new Label(5, 3 + n, num, nrWcf));// ѡ������
								ws.addCell(new Label(6, 3 + n, rs, nrWcf));// ������
								ws.addCell(new Label(7, 3 + n, bl + "%", nrWcf));// ����
								
								n++;
							}
							
							xuh ++;
						}	
					}
				}
			}
		}
		// =====================end====================
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������������µ��б�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getNewZdList(
			List<HashMap<String, String>> list, String zd) {
		
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				HashMap<String, String> newMap = new HashMap<String, String>();
				
				String newZd = map.get(zd);
				
				if (!Base.isNull(newZd)) {
					
					newMap.put(zd + "dm", newZd);
					newMap.put(zd + "mc", newZd);
					
					boolean flag = true;
					
					if (newList != null && newList.size() > 0) {
						for (int j = 0; j < newList.size(); j++) {
							if (newZd.equalsIgnoreCase(newList.get(j).get(
									zd + "dm"))) {
								flag = false;
							}
						}
					}
					
					if (flag) {
						newList.add(newMap);
					}
				}
			}
		}
		
		return newList;
	}
	
	/**
	 * ���ѧ���ش��ʾ��б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getWjhdList(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		ArrayList<String[]> list = dao.getWjhdList(model);
		return getResultList(list, model);
	}
}

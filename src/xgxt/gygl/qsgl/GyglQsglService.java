package xgxt.gygl.qsgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;
import xgxt.gygl.cwgl.GyglCwglDAO;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.gygl.gywh.DelDataDetect;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.utils.CommonQueryDAO;

import common.exception.SystemException;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_���ҹ���_Service��
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

public class GyglQsglService extends GyglCommService {

	GyglQsglDAO dao = new GyglQsglDAO();

	/**
	 * ����Զ����䲿���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpBmList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// �������
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		ArrayList<String[]> list = null;

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ

			list = dao.getZdfpXyList(model, user, colList, searchTj, inputV);

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ

			list = dao.getZdfpNjXyList(model, user, colList, searchTj, inputV);

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ

			list = dao.getZdfpNjZyList(model, user, colList, searchTj, inputV);

		} else {// �������Ϊ�༶

			list = dao.getZdfpBjList(model, user, colList, searchTj, inputV);

		}

		return list;
	}

	/**
	 * ������λ��Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void creatGyglCwxx(GyglQsglForm model, User user) throws Exception {

		// ������Ϣ��
		String tableName = "ssxxb";
		String[] colList = new String[] { "lddm", "cs", "qsh", "cws" };
		String query = " order by lddm,cs,qsh";
		List<HashMap<String, String>> qsList = getRsList(tableName, query,
				new String[] {}, colList, "");

		// �����ֶ�
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "cwh" };

		// ¥��
		ArrayList<String> ldList = new ArrayList<String>();
		// ����
		ArrayList<String> csList = new ArrayList<String>();
		// ���Һ�
		ArrayList<String> qshList = new ArrayList<String>();
		// ��λ��
		ArrayList<String> cwhList = new ArrayList<String>();

		if (qsList != null && qsList.size() > 0) {

			for (int i = 0; i < qsList.size(); i++) {

				HashMap<String, String> map = qsList.get(i);
				// ¥������
				String lddm = map.get("lddm");
				// ¥������
				String cs = map.get("cs");
				// ¥������
				String qsh = map.get("qsh");
				// ��ס�˴�λ��
				String cws = map.get("cws");

				if (!Base.isNull(cws)) {
					for (int j = 0; j < Integer.parseInt(cws); j++) {
						ldList.add(lddm);
						csList.add(cs);
						qshList.add(qsh);
						cwhList.add(String.valueOf(j+1));
					}
				}
			}

			model.setLddm(ldList.toArray(new String[] {}));
			model.setCs(csList.toArray(new String[] {}));
			model.setQsh(qshList.toArray(new String[] {}));
			model.setCwh(cwhList.toArray(new String[] {}));

			tableName = "xg_gygl_cwxxb";
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk("1");
			saveForm.setPkValue(new String[] { "1" });
			saveForm.setArrzd(arrzd);

			saveInfoToDb(saveForm, model, user);
		}
	}

	/**
	 * ���������Զ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String saveQszdfp(GyglQsglForm model, User user) throws Exception {

		// �������
		String fpdx = model.getFpdx();
		// ����������
		String[] fpdx_dm = model.getPrimarykey_checkVal();
		// ���������
		String[] xfprs = model.getXfprs();
		// ���ҷ����
		String tableName = "xg_gygl_qsfpb";
		// ����
		String pk = "lddm||cs||qsh";
		// ����ֵ
		ArrayList<String> pkValueList = new ArrayList<String>();
		// �����ֶ�
		String[] onezd = new String[] { "fpdx", "sjly" };
		// �����ֶ�
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "nj", "bmdm" };
		// ������Դ
		String sjly = "�Զ�";

		List<HashMap<String, String>> kfpQsList = getKfpQsList(model,user);

		// ¥��
		ArrayList<String> ldList = new ArrayList<String>();
		// ����
		ArrayList<String> csList = new ArrayList<String>();
		// ���Һ�
		ArrayList<String> qsList = new ArrayList<String>();
		// �꼶
		ArrayList<String> njList = new ArrayList<String>();
		// ����
		ArrayList<String> bmList = new ArrayList<String>();	

		// �ѷ�������
		int yfpqs = 0;
		
		if (fpdx_dm != null && fpdx_dm.length > 0) {

			for (int i = 0; i < fpdx_dm.length; i++) {

				// �����꼶
				String fpnj = "";
				// ���䲿��
				String fpbm = "";
				// ���������
				int xfp = Integer.parseInt(xfprs[i]);
				// �ѷ���
				int yfp = 0;
				
				if (fpdx_dm[i].split("!!@@!!").length > 1) {
					fpnj = fpdx_dm[i].split("!!@@!!")[0];
					fpbm = fpdx_dm[i].split("!!@@!!")[1];
				} else {
					fpbm = fpdx_dm[i].split("!!@@!!")[0];
				}
					
				if (kfpQsList != null && kfpQsList.size() > 0) {

					for (int j = 0; j < kfpQsList.size(); j++) {
						HashMap<String, String> map = kfpQsList.get(j);
						String fpbj = map.get("fpbj");
						if (!"yes".equalsIgnoreCase(fpbj)) {
							// ¥������
							String lddm = map.get("lddm");
							// ¥������
							String cs = map.get("cs");
							// ¥������
							String qsh = map.get("qsh");
							// ��ס�˴�λ��
							String kzrcws = map.get("kzrcws");

							if (yfp >= xfp) {
								break;
							} else {
								kfpQsList.get(j).put("fpbj", "yes");
								yfp = yfp + Integer.parseInt(kzrcws);

								ldList.add(lddm);
								csList.add(cs);
								qsList.add(qsh);
								njList.add(fpnj);
								bmList.add(fpbm);
								pkValueList.add(lddm + cs + qsh);
								
								yfpqs++;
							}
						}
					}
				}
			}
		}

		model.setLddm(ldList.toArray(new String[]{}));
		model.setCs(csList.toArray(new String[]{}));
		model.setQsh(qsList.toArray(new String[]{}));
		model.setNj(njList.toArray(new String[]{}));
		model.setBmdm(bmList.toArray(new String[]{}));
		model.setFpdx(fpdx);
		model.setSjly(sjly);
				
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValueList.toArray(new String[] {}));
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		//ִ�б������

		if (kfpQsList != null && kfpQsList.size() > 0) {
			saveInfoToDb(saveForm, model, user);
		}

		return String.valueOf(yfpqs);
	}
	
	/**
	 * ���������Զ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String saveQsfpAuto(GyglQsglForm model, User user) throws Exception {
		
		// �������
		String fpdx = model.getFpdx();
		// ����������
		String[] fpdx_dm = model.getPrimarykey_checkVal();
		// ���ҷ����
		String tableName = "xg_gygl_qsfpb";
		// ����
		String pk = "lddm||cs||qsh";
		// ����ֵ
		ArrayList<String> pkValueList = new ArrayList<String>();
		// �����ֶ�
		String[] onezd = new String[] { "fpdx", "sjly", "kffp" };
		// �����ֶ�
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "nj", "bmdm" };
		// ������Դ
		String sjly = "�Զ�";
		//�Ա�
		String xbxz = model.getXb();
		
		// �ɷ��������б�
		List<HashMap<String, String>> kfpQsList = getKfpQsList(model,user);
		// �������ҷ�����Ϣ
		List<HashMap<String, String>> bmqsfpList = dao.getBmqsfpList(fpdx_dm,
				fpdx);
		
		// ¥��
		ArrayList<String> ldList = new ArrayList<String>();
		// ����
		ArrayList<String> csList = new ArrayList<String>();
		// ���Һ�
		ArrayList<String> qsList = new ArrayList<String>();
		// �꼶
		ArrayList<String> njList = new ArrayList<String>();
		// ����
		ArrayList<String> bmList = new ArrayList<String>();	

		// �ѷ�������
		int yfpqs = 0;
		
		if (bmqsfpList != null && bmqsfpList.size() > 0) {
			for (int i = 0; i < bmqsfpList.size(); i++) {
				HashMap<String, String> bmInfo = bmqsfpList.get(i);
				// �����꼶
				String bmnj = !Base.isNull(bmInfo.get("nj")) ? bmInfo.get("nj")
						: "";
				// ���Ŵ���
				String bmdm = "";
				// ������
				int mam = Integer.parseInt(bmInfo.get("man"));
				// Ů����
				int womam = Integer.parseInt(bmInfo.get("woman"));
				// ������λ��
				int mancws = Integer.parseInt(bmInfo.get("mancws"));
				// Ů����λ��
				int womancws = Integer.parseInt(bmInfo.get("womancws"));
				
				if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
					bmdm = bmInfo.get("xydm");
				} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
					bmdm = bmInfo.get("xydm");
				} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
					bmdm = bmInfo.get("zydm");
				} else {// �������Ϊ�༶
					bmdm = bmInfo.get("bjdm");
				}
				
				// �����(��)
				int xfpMan = mam - mancws;
				// �����(Ů)
				int xfpWoman = womam - womancws;
				// �ѷ���(��)
				int yfpMan = 0;
				// �ѷ���(Ů)
				int yfpWoman = 0;
				
				if (kfpQsList != null && kfpQsList.size() > 0) {
					for (int j = 0; j < kfpQsList.size(); j++) {
						HashMap<String, String> qsInfo = kfpQsList.get(j);
						// ¥������
						String lddm = qsInfo.get("lddm");
						// ����
						String cs = qsInfo.get("cs");
						// ���Һ�
						String qsh = qsInfo.get("qsh");
						// �Ա�����
						String xb = qsInfo.get("xb");
						// ��ס�˴�λ��
						String kzrcws = qsInfo.get("kzrcws");
						//������
						String fpbj = qsInfo.get("fpbj");
						
						if (!"yes".equalsIgnoreCase(fpbj)) {
							if ("��".equalsIgnoreCase(xbxz) && yfpMan >= xfpMan) {
								break;
							} else if ("Ů".equalsIgnoreCase(xbxz)
									&& yfpWoman > xfpWoman) {
								break;
							} else if (yfpMan >= xfpMan && yfpWoman > xfpWoman) {
								break;
							} else {

								if ("��".equalsIgnoreCase(xb) && yfpMan < xfpMan) {// ������
									
									kfpQsList.get(j).put("fpbj", "yes");
									
									yfpMan += Integer.parseInt(kzrcws);
									
									ldList.add(lddm);
									csList.add(cs);
									qsList.add(qsh);
									njList.add(bmnj);
									bmList.add(bmdm);
									pkValueList.add(lddm + cs + qsh);
									
									yfpqs++;
								} else if("Ů".equalsIgnoreCase(xb) && yfpWoman < xfpWoman){// Ů����
									
									kfpQsList.get(j).put("fpbj", "yes");
									
									yfpWoman += Integer.parseInt(kzrcws);
									
									ldList.add(lddm);
									csList.add(cs);
									qsList.add(qsh);
									njList.add(bmnj);
									bmList.add(bmdm);
									pkValueList.add(lddm + cs + qsh);
									
									yfpqs++;
								}

								
							}
						}
					}
				}
			}
		}
		
		model.setLddm(ldList.toArray(new String[]{}));
		model.setCs(csList.toArray(new String[]{}));
		model.setQsh(qsList.toArray(new String[]{}));
		model.setNj(njList.toArray(new String[]{}));
		model.setBmdm(bmList.toArray(new String[]{}));
		model.setFpdx(fpdx);
		model.setSjly(sjly);
				
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValueList.toArray(new String[] {}));
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		//ִ�б������

		if (kfpQsList != null && kfpQsList.size() > 0) {
			saveInfoToDb(saveForm, model, user);
		}

		return String.valueOf(yfpqs);
	}
	
	/**
	 * ��ÿɷ��������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKfpQsList(GyglQsglForm model,
			User user) {
		return dao.getKfpQsList(model, user);
	}
	
	/**
	 * ��������Զ��������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpjgList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		model.setSjly("�Զ�");

		ArrayList<String[]> list = getQsfpInfoList(model, user, colList,
				request);

		return list;
	}

	/**
	 * ȡ�����ҷ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean cancelQsfp(GyglQsglForm model,HttpServletRequest request) throws Exception {
		
		boolean blog=false;
		DelDataDetect delData = new DelDataDetect();
		DelDetectModel detectModel = new DelDetectModel();
		
		detectModel.setPath("gygl_qsgl_fpjg.do");
		//����Ҫɾ����������������ɾ�����
		String[] pkValue=model.getPrimarykey_checkVal();
		detectModel.setPkValue(pkValue);
		
		delData.dataDetect(detectModel, request);
		
		if (detectModel.isBool()) {
			
			blog=dao.cancelQsfp(model);
		
		} else {

			request.setAttribute("delMessage", detectModel.getMessage());
		}
		
		return blog;
	}

	/**
	 * ��������Զ��������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	public ArrayList<String[]> getQsfpInfoList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		// �������
		String fpdx = model.getFpdx();
		// ������Դ
		String sjly = model.getSjly();
		
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;


		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(Base.isNull(fpdx) ? "" : " and fpdx = '" + fpdx + "'");
		query.append(Base.isNull(sjly) ? "" : " and sjly = '" + sjly + "'");
		query.append(searchTj);
		query.append(" order by ssbh");

		ArrayList<String[]> list =(ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy("xg_view_gygl_qsfp",
				query.toString(), inputV, colList, "", model);

		return list;
	}

	/**
	 * ��������Զ��������б�(����Ҫ��ס״̬)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public ArrayList<String[]> getQsfpInfoListForNoRzzt(GyglQsglForm model,
			User user, String[] colList, HttpServletRequest request)
			throws Exception {

		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		// �������
		String fpdx = model.getFpdx();
		// ������Դ
		String sjly = model.getSjly();
		// ���䷽ʽ
		String fpfs = jbszModel.getFpfs();
		
		String path = model.getSearchModel().getPath()+"&searchType="+fpdx;
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;


		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		
		// ���䷽ʽΪ��ѧУ->ѧԺ->�༶
		if ("2".equalsIgnoreCase(fpfs)) {
			if ("xx".equalsIgnoreCase(user.getUserStatus())) {
				query.append( " and fpdx = 'xy' ");
			}else{
				query.append( " and fpdx = 'bj'");
				query.append( " and xydm = '"+user.getUserDep()+"'");
			}
		}else{
			query.append( " and fpdx = '"+fpdx+"' ");
		}
		
		//query.append(Base.isNull(sjly) ? "" : " and sjly = '" + sjly + "'");
		query.append(searchTj);
		query.append(" order by ssbh");
		System.out.println(query);
		ArrayList<String[]> list = getRsArrList("xg_view_gygl_qsfp", query
				.toString(), inputV, colList, "", model);

		return list;
	}
	
	/**
	 * ��������ֶ��������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 * @throws Exception
	 */
	public ArrayList<String[]> getSdfpjgList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		ArrayList<String[]> list = new ArrayList<String[]>();

		ArrayList<String[]> fpInfoList = null;

		// ����Ҫ��ס״̬����
		if (model.getRzzt() == null || model.getRzzt().length == 0) {
			fpInfoList = getQsfpInfoListForNoRzzt(model, user, colList, request);
		} else {
			fpInfoList = getQsfpInfoList(model, user, colList, request);
		}

		// ��ס״̬
		String[] search_tj_rzzt = model.getRzzt();

		// ��ס����б�
		List<HashMap<String, String>> rzqkList = null;

		if (fpInfoList != null && fpInfoList.size() > 0) {

			String[] ssbh = new String[fpInfoList.size()];

			for (int i = 0; i < fpInfoList.size(); i++) {
				ssbh[i] = fpInfoList.get(i)[0];
			}

			model.setSsbh(ssbh);

			rzqkList = dao.getQsrzInfoList(model);
		}

		if (fpInfoList != null && fpInfoList.size() > 0) {

			for (int i = 0; i < fpInfoList.size(); i++) {

				ArrayList<String> rs = new ArrayList<String>();

				String[] fpInfo = fpInfoList.get(i);

				rs.addAll(new ArrayList<String>(Arrays.asList(fpInfo)));

				String ssbh = fpInfo[0];

				boolean flag = true;

				if (rzqkList != null && rzqkList.size() > 0) {

					for (int j = 0; j < rzqkList.size(); j++) {

						HashMap<String, String> map = rzqkList.get(j);

						// ¥������
						String lddm = map.get("lddm");
						// ����
						String cs = map.get("cs");
						// ���Һ�
						String qsh = map.get("qsh");
						// ��λ��
						String cws = map.get("cws");
						// ����ס�˴�λ��
						String bkzrcws = map.get("bkzrcws");
						// ��ס����
						String yzrs = map.get("yzrs");

						if (ssbh.equalsIgnoreCase(lddm + "-" + qsh)) {

							String rzzt = "";

							if ("0".equalsIgnoreCase(yzrs)) {// ��ס����Ϊ0
								rzzt = "�յ�";
							} else if (cws.equalsIgnoreCase(String
									.valueOf(Integer.parseInt(bkzrcws)
											+ Integer.parseInt(yzrs)))) {// ��ס����+����ס��=���Ҵ�λ
								rzzt = "����";
							} else {// �������������ݲ��ܣ�
								rzzt = "�е�";
							}

							// �ж���ס״̬�Ƿ��ǹ�����
							if (search_tj_rzzt != null
									&& search_tj_rzzt.length > 0) {
								for (int k = 0; k < search_tj_rzzt.length; k++) {
									if (rzzt
											.equalsIgnoreCase(search_tj_rzzt[k])) {
										flag = true;
										break;
									} else {
										flag = false;
									}
								}
							}

							rs.add(rzzt);

							break;
						}
					}
				}

				if (flag) {
					list.add(rs.toArray(new String[] {}));
				}
			}
		}

		// ��Ҫ��ס״̬����
		if (model.getRzzt() != null && model.getRzzt().length > 0) {
			list = getResultList(list, model.getPages());
		}
		
		return list;
	}

	/**
	 * ��÷�������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, Object>> getFpdxList(GyglQsglForm model,
			GyglJbszForm jbszModel,User user) {

		// �������
		String fpdx = jbszModel.getFpdx();
		
		String userStatus = user.getUserStatus(); 
		String userName= user.getUserName(); 
		String userDep= user.getUserDep(); 
		
		// �꼶�б�
		List<HashMap<String, String>> njList = dao.getBmList(new String[]{"nj","",""}, userStatus,
				userName, userDep);

		// ѧԺ�б�
		List<HashMap<String, String>> xyList = dao.getBmList(new String[]{"xy","",""}, userStatus,
				userName, userDep);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// �������xy��ѧԺ��
		if ("xy".equalsIgnoreCase(fpdx)) {
			// �꼶
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("liName", "Child");
			map.put("bmList", xyList);
			list.add(map);
		}
		// �������njxy���꼶+ѧԺ��
		else if ("njxy".equalsIgnoreCase(fpdx)) {
			// �꼶
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}
		// �������njzy���꼶+רҵ��
		else if ("njzy".equalsIgnoreCase(fpdx)) {
			// �꼶
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}
		// �������bj���༶��
		else if ("bj".equalsIgnoreCase(fpdx)) {
			// �꼶
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}

		return list;
	}
	
	/**
	 * ��ù�Ԣ�ṹ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, Object>> getGyjgList(GyglQsglForm model,
			User user) {

		// ������ϵ
		//String csgx = jbszModel.getCsgx();

		// У���б�
		//List<HashMap<String, String>> xqList = dao.getXqList(model, jbszModel);

		// ԰���б�
		//List<HashMap<String, String>> yqList = dao.getYqList(model, jbszModel);

		// ¥���б�
		List<HashMap<String, String>> ldList = dao.getLdList(model, user);
//		List<HashMap<String, String>> ldInfoList = getLdInfoList(ldList,
//				jbszModel);

		// �����б�
		List<HashMap<String, String>> csList=new ArrayList<HashMap<String,String>>();
		if("yes".equalsIgnoreCase(model.getWfpqs())){
			 csList = getWfpLcList(model,user);
		}else{
			 csList = getCsList(ldList,model);
		}

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// ¥��
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Lv", "ld");
		map.put("infoList", ldList);
		list.add(map);

		// ����
		map = new HashMap<String, Object>();
		map.put("Lv", "cs");
		map.put("infoList", csList);
		list.add(map);

		return list;
	}
	
	/**
	 * ���¥����Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLdInfoList(
			List<HashMap<String, String>> ldList, GyglJbszForm jbszModel) {

		// ������ϵ
		String csgx = jbszModel.getCsgx();

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (ldList != null && ldList.size() > 0) {
			for (int i = 0; i < ldList.size(); i++) {
				HashMap<String, String> map = ldList.get(i);
				// У������
				String xqdm = map.get("xqdm");
				// ԰������
				String yqdm = map.get("yqdm");
				// ¥������
				String lddm = map.get("lddm");
				// ¥������
				String ldmc = map.get("ldmc");
				// ����
				String cs = map.get("cs");
				// �ϼ�����
				String sjdm = "";
				if ("1".equalsIgnoreCase(csgx) || "3".equalsIgnoreCase(csgx)) {
					sjdm = yqdm;
				} else if ("2".equalsIgnoreCase(csgx)) {
					sjdm = xqdm;
				}

				HashMap<String, String> ldInfo = new HashMap<String, String>();
				ldInfo.put("dm", lddm);
				ldInfo.put("mc", ldmc);
				ldInfo.put("sjdm", sjdm);

				list.add(ldInfo);
			}
		}

		return list;
	}
	
	/**
	 * ��ȡδ�������ҵ�¥��¥��
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getWfpLcList(GyglQsglForm model,
			User user){
		
		return dao.getWfpLcList(model, user);
	}
	
	
	/**
	 * ��ò�����Ϣ�б�
	 * 
	 * @author ΰ�����
	 * ������������޸� 2011.8.12
	 */
	public List<HashMap<String, String>> getCsList(
			List<HashMap<String, String>> ldList, GyglQsglForm model) {
		GyglCwglForm cwglForm=new GyglCwglForm();
		GyglCwglDAO cwglDAO=new GyglCwglDAO();
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		String[] pk = model.getPrimarykey_checkVal();
		ArrayList<HashMap<String,String>> csList = new ArrayList<HashMap<String,String>>();
		
		
		
		if (pk != null && pk.length > 0) {
			List<HashMap<String,String>>ldlcList=splitLdLc(pk);
			for(int i=0;i<ldlcList.size();i++){
				HashMap<String,String>ldlcMap=ldlcList.get(i);
				boolean flag = true;
				if (ldlcList != null && ldlcList.size() > 0) {
					for (int j = 0; j < csList.size(); j++) {
						HashMap<String,String>csMap=csList.get(j);
						if(ldlcMap.get("cs").equalsIgnoreCase(csMap.get("cs"))
								&& ldlcMap.get("lddm").equalsIgnoreCase(csMap.get("lddm"))){
							flag=false;
							break;
						}
					}
				}
				
				if (flag) {
					csList.add(ldlcMap);
				}
			}
			
		}	
		
		if (ldList != null && ldList.size() > 0) {

			for (int i = 0; i < ldList.size(); i++) {

				HashMap<String, String> map = ldList.get(i);
				// ¥������
				String lddm = map.get("lddm");
				// ����
				String cs = map.get("cs");
				// ¥������key(ǰ̨�ж�)
				String lddmKey = map.get("lddmKey");
				if (!Base.isNull(cs)) {
					cwglForm.setLddm(lddm);
					List<HashMap<String,String>>ldcsList=cwglDAO.getLdlcByQsfp(cwglForm);
					for (int j = 0; j < ldcsList.size(); j++) {
						HashMap<String,String>ldcsMap=ldcsList.get(j);
						HashMap<String, String> csInfo = new HashMap<String, String>();
						csInfo.put("dm", ldcsMap.get("cs"));
						csInfo.put("mc", ldcsMap.get("cs"));
						csInfo.put("lddm", lddm);
						csInfo.put("lddmKey", lddmKey);
						boolean flag = true;
						
						if (csList != null && csList.size() > 0) {
							for (int k = 0; k < csList.size(); k++) {
								HashMap<String,String>csMap=csList.get(k);
								if(!ldcsMap.get("cs").equalsIgnoreCase(csMap.get("cs"))
										&& lddm.equalsIgnoreCase(csMap.get("lddm"))){
									flag=false;
									break;
								}
							}
						}
						
						if (flag) {
							list.add(csInfo);
						}
					}
				}
			}
		}

		return list;
	}
	
	/**
	 * ���������ֶ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveQssdfp(GyglQsglForm model, User user) throws Exception {

		// �������
		String fpdx = model.getFpdx();
		// ���ҷ����
		String tableName = "xg_gygl_qsfpb";
		// ����
		String pk = "lddm||cs||qsh";
		// ����ֵ
		ArrayList<String> pkValueList = new ArrayList<String>();
		// �����ֶ�
		String[] onezd = new String[] { "fpdx", "sjly","kffp" };
		// �����ֶ�
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "nj", "bmdm" };
		// �ǿ��ֶ�
		String[] notnull = new String[] { "bmdm" };
		// ������Դ
		String sjly = "�ֶ�";

		// ¥��
		String[] lddm = model.getLddm();
		// ����
		String[] cs = model.getCs();
		// ���Һ�
		String[] qsh = model.getQsh();
		// �꼶
		String[] nj = model.getNj();
		// ���Ŵ���
		String[] bmdm = model.getBmdm();
		
		// ¥��
		ArrayList<String> ldList = new ArrayList<String>();
		// ����
		ArrayList<String> csList = new ArrayList<String>();
		// ���Һ�
		ArrayList<String> qsList = new ArrayList<String>();
		// �꼶
		ArrayList<String> njList = new ArrayList<String>();
		// ����
		ArrayList<String> bmList = new ArrayList<String>();	

		boolean flag = true;
		
		if (lddm != null && lddm.length > 0) {
			
			for (int i = 0; i < lddm.length; i++) {
				//if (!Base.isNull(bmdm[i])) {
					ldList.add(lddm[i]);
					csList.add(cs[i]);
					qsList.add(qsh[i]);
					njList.add(nj[i]);
					bmList.add(bmdm[i]);
					pkValueList.add(lddm[i] + cs[i] + qsh[i]);
				//}
			}

			model.setLddm(ldList.toArray(new String[] {}));
			model.setCs(csList.toArray(new String[] {}));
			model.setQsh(qsList.toArray(new String[] {}));
			model.setNj(njList.toArray(new String[] {}));
			model.setBmdm(bmList.toArray(new String[] {}));
			model.setFpdx(fpdx);
			model.setSjly(sjly);

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValueList.toArray(new String[] {}));
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setNotnull(notnull);

			GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
			// ���䷽ʽ
			String fpfs = jbszModel.getFpfs();

			// ���䷽ʽΪ��ѧУ->ѧԺ->�༶
			if ("2".equalsIgnoreCase(fpfs)) {
				if ("xx".equalsIgnoreCase(user.getUserStatus())) {
					flag = saveInfoToDb(saveForm, model, user);
				} else {
					flag = dao.saveQsfpToBj(model, user);
				}
			} else {
				flag = saveInfoToDb(saveForm, model, user);
			}
		}

		return flag;
	}
	
	/**
	 * ���δ����������
	 * 
	 * @author ΰ�����
	 */
	public String getWfpQsNum() {

		// δ����������
		String wfpQsNum = "0";
		// ��������
		String qsNum = "0";
		// �ѷ�����������
		String yfpQsNum = "0";

		List<HashMap<String, String>> list = dao.getQsNumList();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					qsNum = list.get(0).get("num");
				} else {
					yfpQsNum = list.get(i).get("num");
				}
			}
		}

		if (Integer.parseInt(qsNum) > Integer.parseInt(yfpQsNum)) {
			wfpQsNum = String.valueOf(Integer.parseInt(qsNum)
					- Integer.parseInt(yfpQsNum));
		}
		
		return wfpQsNum;
	}
	
	/**
	 * ������ҷ���ͳ���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getQsfpTjList(GyglQsglForm model, User user,
			String[] colList, HttpServletRequest request) {

		// �������
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		SearchService searchService = new SearchService();
		ArrayList<String[]> tjList = null;

		try {

			// �߼���ѯ����
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			// �߼���ѯ����ֵ
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			// Ȩ�޿���
			HashMap<String,String> notCtrlStatus = new HashMap<String,String>();
			notCtrlStatus.put("gygly", "yes");
			user.setNotCtrlStatus(notCtrlStatus);
			String searchTjByUser = searchService.getSearchTjByUser("a", user);

			searchTj += searchTjByUser;

			// ͳ���б�
			tjList = dao.getQsfpTjList(fpdx, searchTj, inputV, model);
			
		} catch (Exception e) {
			throw new SystemException("Error-1024");
		}

		// ͳ���б�
		// List<HashMap<String, String>> tjList = dao.getBmTjList(fpdx, bmList);

		// ArrayList<String[]> list = setBmTjList(fpdx, bmList, tjList);

		return tjList;
	}

	/**
	 * ���ò���ͳ���б�
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> setBmTjList(String fpdx,
			ArrayList<String[]> bmList, List<HashMap<String, String>> tjList) {

		ArrayList<String[]> list = new ArrayList<String[]>();

		if (bmList != null && bmList.size() > 0) {
			for (int i = 0; i < bmList.size(); i++) {
				//��ʾ����
				ArrayList<String> nrList = new ArrayList<String>();
				String[] bmInfo = bmList.get(i);

				//�꼶
				String nj = "";
				// ���Ŵ���
				String bmdm = "";
				
				if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
					bmdm = bmInfo[0];
					// ѧԺ����
					nrList.add(bmInfo[0]);
					// ѧԺ����
					nrList.add(bmInfo[1]);
				} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
					nj = bmInfo[0];
					bmdm = bmInfo[1];
					// �꼶+ѧԺ����
					nrList.add(nj + "!!@@!!" + bmdm);
					// �꼶
					nrList.add(bmInfo[0]);
					// ѧԺ����
					nrList.add(bmInfo[2]);
				} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
					nj = bmInfo[0];
					bmdm = bmInfo[3];
					// �꼶+רҵ����
					nrList.add(nj + "!!@@!!" + bmdm);
					// �꼶
					nrList.add(bmInfo[0]);
					// רҵ����
					nrList.add(bmInfo[4]);
				} else {// �������Ϊ�༶
					nj = bmInfo[0];
					bmdm = bmInfo[5];
					// �༶����
					nrList.add(bmInfo[5]);
					// �༶����
					nrList.add(bmInfo[6]);
				}
				
				// �ѷ���������
				int fpqss = 0;
				// �����ң��ɻ죩
				int manhqss = 0;
				// �����ң����ɻ죩
				int manbhqss = 0;
				// Ů���ң��ɻ죩
				int womanhqss = 0;
				// Ů���ң����ɻ죩
				int womanbhqss = 0;
				
				//ͳ����Ϣ�б�
				if (tjList != null && tjList.size() > 0) {
					for (int j = 0; j < tjList.size(); j++) {
						HashMap<String,String> map = tjList.get(j);
						// �꼶
						String tjnj = map.get("nj");
						// ���Ŵ���
						String tjbm = map.get("bmdm");
						// �Ա�����
						String xb = map.get("xb");
						// �ɷ��ס
						String kfhz = map.get("kfhz");
						// ������
						String qsnum = map.get("qsnum");
						
						int num = Base.isNull(qsnum)?0:Integer.parseInt(qsnum);
						
						// �������ΪѧԺ
						if ("xy".equalsIgnoreCase(fpdx) && tjbm.equalsIgnoreCase(bmdm)) {
							if ("��".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								manhqss+=num;
							}else if ("��".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								manbhqss+=num;
							}else if ("Ů".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								womanhqss+=num;
							}else if ("Ů".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								womanbhqss+=num;
							}
							fpqss+=num;
						}
						// �������Ϊ��ѧԺ
						else if (tjbm.equalsIgnoreCase(bmdm) && tjnj.equalsIgnoreCase(nj)) {
							if ("��".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								manhqss+=num;
							}else if ("��".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								manbhqss+=num;
							}else if ("Ů".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								womanhqss+=num;
							}else if ("Ů".equalsIgnoreCase(xb) && "����".equalsIgnoreCase(kfhz)) {
								womanbhqss+=num;
							}
							fpqss+=num;
						}
					}
				}
				
				// �����ѷ�������
				nrList.add(String.valueOf(fpqss));
				// �пɻ�
				nrList.add(String.valueOf(manhqss));
				// �в��ɻ�
				nrList.add(String.valueOf(manbhqss));
				// Ů�ɻ�
				nrList.add(String.valueOf(womanhqss));
				// Ů���ɻ�
				nrList.add(String.valueOf(womanbhqss));
				
				list.add(nrList.toArray(new String[]{}));
			}
		}

		return list;
	}
	
	/**
	 * ��������ֶ������������б�
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, Object>> getFpdxByQssdfp(GyglQsglForm model) {
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Lv", "1");
		map.put("liName", "Child");
		map.put("bmList", dao.getBmxxByFpdx(model));
		list.add(map);
		
		return list;
	}
	
	/**
	 * ���¥����Ϣ�б�δ������������
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getLdForWfpQssList(User user) {
		return dao.getLdForWfpQssList(user);
	}
	
	/**
	 * �ع�¥��¥���б�
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String, String>> splitLdLc(String[] pkValue) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < pkValue.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			String lddm = pkValue[i].split("-")[0];
			String cs = pkValue[i].split("_")[1];
			map.put("lddm", lddm);
			map.put("cs", cs);
			list.add(map);
		}
		return list;
	}
}
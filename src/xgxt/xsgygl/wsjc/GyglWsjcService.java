package xgxt.xsgygl.wsjc;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.GyglTyForm;
import xgxt.xsgygl.GyglTyService;

public class GyglWsjcService extends GyglTyService {

	GyglWsjcDAO dao = new GyglWsjcDAO();

	/**
	 * ��ȡ����������
	 * 
	 * @param request
	 * @return User
	 */
	public GyglWsjcModel getWsjc() {

		GyglWsjcModel wsjc = new GyglWsjcModel();

		// ����������Ϣ
		HashMap<String, String> map = getCsszInfo();
		// �ȼ��б�
		String tableName = "gygl_wsjc_wsfdjb";
		String query = " order by to_number(djpx) ";
		String[] colList = new String[] { "wsfxx", "wsfsx", "wsfdj" };
		List<HashMap<String, String>> list = dao.getRsList(tableName, query,
				new String[] {}, colList, "");

		// �������
		wsjc.setJczq(map.get("jczq"));
		// ¼����ʽ
		wsjc.setLrxs(map.get("lrxs"));
		// ��ʼ����
		wsjc.setQsrq(map.get("qsrq"));
		// �ܹ��ܴ�
		wsjc.setZgzc(map.get("zgzc"));
		// �����ȼ�
		wsjc.setGldj(map.get("gldj"));
		// ��������
		wsjc.setGlfs(map.get("glfs"));
		// ������
		wsjc.setJcf(map.get("jcf"));
		// �ȼ��б�
		wsjc.setWsdjList(list);

		return wsjc;
	}

	/**
	 * ��ò��������������
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getCsszInfo() {

		String tableName = "gygl_wsjc_csszb";
		// ����
		String pk = "rownum";
		// ����ֵ��
		String pkValue = "1";
		// ����ֶ�
		String[] colList = new String[] { "gldj", "glfs", "jcf", "jczq",
				"lrxs", "qsrq", "zgzc" };

		// ��Ŀ�����Ϣ
		HashMap<String, String> map = dao.getRsInfo(tableName, pk, pkValue,
				colList);

		setCsszDef(map);

		return map;
	}

	/**
	 * ���ò�������Ĭ��ֵ
	 * 
	 * @author luojw
	 */
	public void setCsszDef(HashMap<String, String> map) {

		// �������
		String jczq = map.get("jczq");

		if (Base.isNull(jczq)) {
			map.put("jczq", "��");
			map.put("lrxs", "����");
			map.put("gldj", "��");
			map.put("glfs", "��");
		}
	}

	/**
	 * ������������������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveCssz(GyglTyForm model, User user,
			HttpServletRequest request) throws Exception {

		DAO dao = DAO.getInstance();

		// ����
		String tableName = "gygl_wsjc_csszb";
		// ��һ�ֶ�
		String[] onezd = new String[] { "gldj", "glfs", "jcf", "jczq", "lrxs",
				"qsrq", "zgzc" };
		// �������
		String jczq = model.getJczq();
		// ����Ϊ�յ������������ܴκ���ʼʱ��
		if ("��".equalsIgnoreCase(jczq)) {
			model.setZgzc("");
			model.setQsrq("");
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk("1");
		saveForm.setPkValue(new String[] { "1" });
		saveForm.setOnezd(onezd);

		boolean flag = dao.submitData(saveForm, model, request);

		if (flag) {
			flag = saveCsszFsDj(model, user);
		}
		return flag;
	}

	/**
	 * ���������ȼ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveCsszFsDj(GyglTyForm model, User user) throws Exception {

		// ����
		String tableName = "gygl_wsjc_wsfdjb";
		// �����ֶ�
		String[] arrzd = new String[] { "djpx", "wsfsx", "wsfxx", "wsfdj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk("1");
		saveForm.setPkValue(new String[] { "1" });
		saveForm.setArrzd(arrzd);

		boolean flag = dao.saveData(saveForm, model, user);

		return flag;
	}

	/**
	 * �����������հױ���
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void expWsjcKbbb(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os) throws Exception {

		String title = "�������";

		// �ܴ�
		String zc = model.getZc();
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �ܹ��ܴ�
		String zgzc = wsjcModel.getZgzc();

		// �̶���
		String[] gdName = new String[] { "ѧ��", "ѧ��", "ѧ������", "���", Base.YXPZXY_KEY+"����",
				Base.YXPZXY_KEY+"����", "У������", "У������", "¥������", "¥������", "����", "���Һ�" };

		int count = "��".equalsIgnoreCase(jczq) ? gdName.length + 2
				: gdName.length + 2;
		// ȫ����
		String[] topName = new String[count];

		for (int i = 0; i < count; i++) {
			if (i < gdName.length) {
				topName[i] = gdName[i];
			} else if ("��".equalsIgnoreCase(jczq) && i != count - 1) {
				topName[i] = "�ܴ�";
			} else if ("��".equalsIgnoreCase(jczq) && i != count - 1) {
				topName[i] = "����";
			} else {
				topName[i] = "����".equalsIgnoreCase(lrxs) ? "������(������)" : "�����ȼ�";
			}
		}

		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(topName);
		// ��������Ĺ̶�����
		ArrayList<String[]> gdlist = dao.getExpKbbbList(model, wsjcModel);
		// ���յ�������
		ArrayList<String[]> list = new ArrayList<String[]>();

		if (gdlist != null && gdlist.size() > 0) {
			if ("��".equalsIgnoreCase(jczq)) {
				if (Base.isNull(zc) && !Base.isNull(zgzc)) {
					for (int i = 0; i < gdlist.size(); i++) {
						for (int j = 1; j <= Integer.parseInt(zgzc); j++) {
							String[] qsInfo = gdlist.get(i);
							ArrayList<String> info = new ArrayList<String>(
									Arrays.asList(qsInfo));
							info.add(String.valueOf(j));
							info.add("");
							list.add(info.toArray(new String[] {}));
						}
					}
				} else {
					for (int i = 0; i < gdlist.size(); i++) {
						String[] qsInfo = gdlist.get(i);
						ArrayList<String> info = new ArrayList<String>(Arrays
								.asList(qsInfo));
						info.add(zc);
						info.add("");
						list.add(info.toArray(new String[] {}));
					}
				}
			} else {
				list = gdlist;
			}
		}
		expToExcel(title, topTr, list, os);
	}

	/**
	 * ���������¼���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWsflrTop(GyglWsjcModel wsjcModel) {
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �̶���
		String[] gdName = new String[] { Base.YXPZXY_KEY+"����", "У������", "¥������", "����", "���Һ�" };

		int count = "��".equalsIgnoreCase(jczq) ? gdName.length + 2
				: gdName.length + 1;
		// ȫ����
		String[] topName = new String[count];

		for (int i = 0; i < count; i++) {
			if (i < gdName.length) {
				topName[i] = gdName[i];
			} else if ("��".equalsIgnoreCase(jczq) && i != count - 1) {
				topName[i] = "�ܴ�";
			} else {
				topName[i] = "����".equalsIgnoreCase(lrxs) ? "������" : "�����ȼ�";
			}
		}

		return getTopList(topName);
	}
	
	/**
	 * ���������¼���б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsflrList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// ������Ϣ
		ArrayList<String[]> ssxxList = dao.getSsldList(model, wsjcModel);
		// ��������Ϣ
		ArrayList<String[]> wsfList = dao.getWsfxxList(model);

		ArrayList<String[]> list = new ArrayList<String[]>();

		if (ssxxList != null && ssxxList.size() > 0) {

			for (int i = 0; i < ssxxList.size(); i++) {

				ArrayList<String> ssxx = new ArrayList<String>(Arrays
						.asList(ssxxList.get(i)));

				String sspk = ssxxList.get(i)[0];

				if (wsfList != null && wsfList.size() > 0) {

					for (int j = 0; j < wsfList.size(); j++) {
						
						String[] wsfInfo = wsfList.get(j);

						if (wsfInfo[0].equalsIgnoreCase(sspk)) {
							if ("����".equalsIgnoreCase(lrxs)) {
								ssxx.add(wsfInfo[2]);
								ssxx.add(wsfInfo[4]);// ��������
								ssxx.add(wsfInfo[5]);// ��鲿��
								ssxx.add(wsfInfo[6]);// ��ע
							} else if ("�ȼ�".equalsIgnoreCase(lrxs)) {
								ssxx.add(wsfInfo[3]);
								ssxx.add(wsfInfo[4]);// ��������
								ssxx.add(wsfInfo[5]);// ��鲿��
								ssxx.add(wsfInfo[6]);// ��ע
							}
						}
					}
				}

				list.add(ssxx.toArray((new String[] {})));
			}
		}

		return list;
	}

	/**
	 * ����������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWsf(GyglTyForm model, GyglWsjcModel wsjcModel, User user)
			throws Exception {

		// ����
		String tableName = "gygl_wsjc_wsfwhb";
		// �������
		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �����ȼ�
		String gldj = wsjcModel.getGldj();
		// �ǿ��ֶ�
		String[] notnull = null;
		
		// �����ֶ�
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add("jcld");
		arrList.add("jccs");
		arrList.add("jcqs");
		
		if ("����".equalsIgnoreCase(lrxs)) {
			arrList.add("wsffs");
			if ("��".equalsIgnoreCase(gldj)) {
				arrList.add("wsfdj");
			}
		} else {
			arrList.add("wsfdj");
		}

		String[] arrzd = arrList.toArray(new String[] {});

		// ��һ�ֶ�
		ArrayList<String> oneList = new ArrayList<String>();
		oneList.add("lrr");
		oneList.add("lrrlx");
		oneList.add("lrsj");
		oneList.add("xn");
		oneList.add("xq");
		oneList.add("nd");
		oneList.add("jcsj");
		if ("��".equalsIgnoreCase(jczq)) {
			oneList.add("jczc");
		}
		String[] onezd = oneList.toArray(new String[] {});

		String pk = "jcld||jccs||jcqs";
		if ("��".equalsIgnoreCase(jczq)) {
			pk += "||jczc";
		} else {
			pk += "||jcsj";
		}
		// ���¥��
		String[] jcld = model.getJcld();
		// ������
		String[] jccs = model.getJccs();
		// �������
		String[] jcqs = model.getJcqs();
		// ���ʱ��
		String jcsj = model.getJcsj();
		// ����ܴ�
		String jczc = model.getJczc();
		// ��������
		String[] pkValue = new String[jcld.length];
		for (int i = 0; i < jcld.length; i++) {
			if ("��".equalsIgnoreCase(jczq)) {
				pkValue[i] = jcld[i] + jccs[i] + jcqs[i] + jczc;
			} else {
				pkValue[i] = jcld[i] + jccs[i] + jcqs[i] + jcsj;
			}
		}

		model.setLrr(user.getUserName());
		model.setLrrlx(user.getUserType());
		model.setLrsj(getNowTime("YYYYMMDD"));
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		boolean flag = dao.saveData(saveForm, model, user);

		if (flag) {

			flag = dao.saveJcbm(model, saveForm);
		}

		return flag;
	}
	
	/**
	 * �޸�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateWsf(GyglTyForm model, GyglWsjcModel wsjcModel, User user)
			throws Exception {

		// ����
		String tableName = "gygl_wsjc_wsfwhb";
		// �������
		String jczq = wsjcModel.getJczq();

		// ��һ�ֶ�
		String[] onezd =new String[]{"jcbm","bz"};

		String pk = "jcld||jccs||jcqs";
		if ("��".equalsIgnoreCase(jczq)) {
			pk += "||jczc";
		} else {
			pk += "||jcsj";
		}
		// ����
		String[] ssbh = model.getPrimarykey_checkVal();
		// ���ʱ��
		String jcsj = model.getJcsj();
		// ����ܴ�
		String jczc = model.getJczc();
		// ��������
		String[] pkValue = new String[ssbh.length];
		for (int i = 0; i < ssbh.length; i++) {
			if ("��".equalsIgnoreCase(jczq)) {
				pkValue[i] = ssbh[i] + jczc;
			} else {
				pkValue[i] = ssbh[i] + jcsj;
			}
		}

		model.setJcbm(model.getBmdm());
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = dao.updateData(saveForm, model, user);

		return flag;
	}
	
	/**
	 * �Ƿ����ò�������
	 * 
	 * @author luojw
	 * 
	 */
	public Boolean isSz() {

		String jczq = dao.getOneValue("gygl_wsjc_csszb", "jczq", "1", "1");

		boolean flag = Base.isNull(jczq) ? false : true;

		return flag;
	}
	
	/**
	 * ��ȡϵͳʱ�������ܴ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getNowZc(String nowTime, GyglWsjcModel wsjcModel)
			throws Exception {

		// ��ʼ����
		String qsrq = wsjcModel.getQsrq();
		// �������
		String days = dao.getBetweenDate(nowTime, qsrq);

		String zc = String.valueOf(1 + Integer.parseInt(days) / 7);

		return zc;
	}
	
	/**
	 * ����������������
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getQsWsfInfo(GyglTyForm model,
			GyglWsjcModel wsjcModel) {
		return dao.getQsWsfInfo(model, wsjcModel);
	}
	
	/**
	 * ���������ס���
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getQsrzInfo(GyglTyForm model) {
		return dao.getQsrzInfo(model);
	}

	/**
	 * ��������ֽ���б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsjcJgList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		List<HashMap<String, String>> djList = wsjcModel.getWsdjList();
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// ����
		String lx = model.getLx();
		// ��ѯ�ȼ�
		String cxdj = model.getDj();
		// ��ѯ��������
		String cxxx = model.getFsxx();
		// ��ѯ��������
		String cxsx = model.getFssx();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �Ƿ�����ȼ�
		String gldj = wsjcModel.getGldj();
		// �Ƿ��������
		String glfs = wsjcModel.getGlfs();
		
		if ("qs".equalsIgnoreCase(lx)) {//����
			
			list = dao.getQsWsjcInfoList(model, wsjcModel);
			
			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {

					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(list.get(i)));

					boolean flag = true;

					if ("����".equalsIgnoreCase(lrxs)
							&& "��".equalsIgnoreCase(gldj)) {
						String fs = list.get(i)[10];

						//��ȡ�������������ĵȼ�
						for (int j = 0; j < djList.size(); j++) {

							String xx = djList.get(j).get("wsfxx");
							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (Float.parseFloat(fs) >= Float.parseFloat(xx)
									&& Float.parseFloat(fs) <= Float
											.parseFloat(sx)) {
								arrList.add(dj);
								break;
							}
						}

						//�ж��Ƿ���Ҫ�����������ĵȼ����в�ѯ
						if (!Base.isNull(cxdj)
								&& !cxdj.equalsIgnoreCase(arrList.get(arrList
										.size() - 1))) {
							flag = false;
						}
					}

					if ("�ȼ�".equalsIgnoreCase(lrxs)
							&& "��".equalsIgnoreCase(glfs)) {
						
						String wsdj = list.get(i)[11];

						//��ȡ���ȼ��������ķ���
						for (int j = 0; j < djList.size(); j++) {

							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (dj.equalsIgnoreCase(wsdj)) {
								arrList.add(sx);
								break;
							}
						}
						
						// �ж��Ƿ���Ҫ�����������ķ������в�ѯ
						String fs = arrList.get(arrList.size() - 1);

						if (!Base.isNull(cxxx)
								&& Float.parseFloat(fs) < Float
										.parseFloat(cxxx)) {
							flag = false;
						}

						if (!Base.isNull(cxsx)
								&& Float.parseFloat(fs) > Float
										.parseFloat(cxsx)) {
							flag = false;
						}
					}
					
					if (flag) {
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
			
		}else{//ѧ��
			list = dao.getXsWsjcInfoList(model, wsjcModel);
			
			for (int i = 0; i < list.size(); i++) {

				ArrayList<String> arrList = new ArrayList<String>(Arrays
						.asList(list.get(i)));

				boolean flag = true;
				
				if ("����".equalsIgnoreCase(lrxs)
						&& "��".equalsIgnoreCase(gldj)) {
					String fs = list.get(i)[12];

					//��ȡ�������������ĵȼ�
					for (int j = 0; j < djList.size(); j++) {

						String xx = djList.get(j).get("wsfxx");
						String sx = djList.get(j).get("wsfsx");
						String dj = djList.get(j).get("wsfdj");

						if (Float.parseFloat(fs) >= Float.parseFloat(xx)
								&& Float.parseFloat(fs) <= Float
										.parseFloat(sx)) {
							arrList.add(dj);
							break;
						}
					}

					//�ж��Ƿ���Ҫ�����������ĵȼ����в�ѯ
					if (!Base.isNull(cxdj)
							&& !cxdj.equalsIgnoreCase(arrList.get(arrList
									.size() - 1))) {
						flag = false;
					}
				}
				
				if ("�ȼ�".equalsIgnoreCase(lrxs)
						&& "��".equalsIgnoreCase(glfs)) {
					
					String wsdj = list.get(i)[13];

					//��ȡ���ȼ��������ķ���
					for (int j = 0; j < djList.size(); j++) {

						String sx = djList.get(j).get("wsfsx");
						String dj = djList.get(j).get("wsfdj");

						if (dj.equalsIgnoreCase(wsdj)) {
							arrList.add(sx);
							break;
						}
					}
					
					// �ж��Ƿ���Ҫ�����������ķ������в�ѯ
					String fs = arrList.get(arrList.size() - 1);

					if (!Base.isNull(cxxx)
							&& Float.parseFloat(fs) < Float
									.parseFloat(cxxx)) {
						flag = false;
					}

					if (!Base.isNull(cxsx)
							&& Float.parseFloat(fs) > Float
									.parseFloat(cxsx)) {
						flag = false;
					}
				}
				
				if (flag) {
					rsList.add(arrList.toArray(new String[] {}));
				}
			}
		}
		
		return rsList;
	}
	
	/**
	 * ����������������(�����ѯ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getWsfjgInfo(GyglTyForm model,
			GyglWsjcModel wsjcModel) {

		String lx = model.getLx();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("qs".equalsIgnoreCase(lx)) {
			map = dao.getQsWsfjg(model, wsjcModel);
		} else {
			map = dao.getXsWsfjg(model, wsjcModel);
		}

		return map;
	}
	
	/**
	 * �޸�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean editWsf(GyglTyForm model, HttpServletRequest request)
			throws Exception {

		String tableName = "gygl_wsjc_wsfwhb";

		boolean flag = StandardOperation.update(tableName, new String[] {
				"wsffs", "bz" },
				new String[] { model.getFssx(), model.getBz() },
				"jcld||jccs||jcqs||jcsj||jczc", model.getPkValue(), request);

		return flag;
	}
	
	/**
	 * ɾ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delWsf(GyglTyForm model, User user) throws Exception {

		String tableName = "gygl_wsjc_wsfwhb";
		String pk = "jcld||jccs||jcqs||jcsj||jczc";
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		boolean flag = dao.delDate(saveForm, model, user);

		return flag;
	}
	
	/**
	 * ��������ֽ���б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsfTjList(GyglTyForm model,
			GyglWsjcModel wsjcModel, List<HashMap<String, String>> topTr,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// ͳ�Ʒ�ʽ
		String tjfs = model.getTjfs();

		if ("qs".equalsIgnoreCase(tjfs)) {// ����
			
			ArrayList<String[]> zqsList = dao.getQszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getQspmTjList(model, wsjcModel);
			
			if (zqsList != null && zqsList.size() > 0) {

				for (int i = 0; i < zqsList.size(); i++) {
					
					boolean flag = true;
					
					if (pmList != null && pmList.size() > 0) {
						
						ArrayList<String> arrList = new ArrayList<String>(Arrays
								.asList(zqsList.get(i)));

						String[] qsInfo = zqsList.get(i);
						
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (qsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//��������
								String zqss = qsInfo[qsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									
									arrList.add(pmInfo[k]);
									
									if (k != 0 && k % 2 == 0) {
										// �ȼ���ռ����
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zqss) * 100)+"";
										if(!"0".equalsIgnoreCase(bl)){
											bl+="%";
										}
										
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								arrList.add("��");
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		} else {// ѧ��
			ArrayList<String[]> zrsList = dao.getXszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getXspmTjList(model, wsjcModel);
			
			if (zrsList != null && zrsList.size() > 0) {

				for (int i = 0; i < zrsList.size(); i++) {
					
					boolean flag = true;
					
					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(zrsList.get(i)));
					
					if (pmList != null && pmList.size() > 0) {

						String[] xsInfo = zrsList.get(i);
									
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (xsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//������
								String zrs = xsInfo[xsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									arrList.add(pmInfo[k]);
									if (k != 0 && k % 2 == 0) {
										// �ȼ���ռ����
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zrs) * 100)
												+ "";
										if (!"0".equalsIgnoreCase(bl)) {
											bl += "%";
										}
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								arrList.add("��");
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		}
		
		return rsList;
	}
	
	/**
	 * ���ͳ�������ͷ
	 * 
	 * @author luo
	 */
	public List<HashMap<String, String>> getWsfTjTop(GyglTyForm model,GyglWsjcModel wsjcModel) {

		DAO dao = DAO.getInstance();

		ArrayList<String> colListCN = new ArrayList<String>();
		ArrayList<String> colListEN = new ArrayList<String>();

		// ͳ�Ʒ�ʽ
		String tjfs = model.getTjfs();
		// ͳ�Ʒ�Χ
		String tjfw = model.getTjfw();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// У��
		String xqdm = model.getXqdm();
		// ¥��
		String lddm = model.getLddm();
		// ����
		String cs = model.getCs();
		// ����
		String qsh = model.getQsh();

		if ("nj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���꼶��
			colListCN.add("�꼶");
			colListEN.add("nj");
		} else if ("xy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��ѧԺ��
			colListCN.add("Ժϵ");
			colListEN.add("xymc");
		} else if ("zy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��רҵ��
			colListCN.add("רҵ");
			colListEN.add("zymc");
		} else if ("bj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���༶��
			colListCN.add("�༶");
			colListEN.add("bjmc");
		}
		
		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			colListCN.add("�꼶");
			colListEN.add("nj");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			colListCN.add("Ժϵ����");
			colListEN.add("xymc");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			colListCN.add("רҵ����");
			colListEN.add("zymc");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			colListCN.add("�༶����");
			colListEN.add("bjmc");
		}

		if ("qs".equalsIgnoreCase(tjfs)) {
			colListCN.add("������");
			colListEN.add("zqs");
		} else {
			colListCN.add("������");
			colListEN.add("zrs");
		}
		

		String dj = model.getDj();
		
		if (Base.isNull(dj)) {
			// �ȼ��б�
			List<HashMap<String, String>> wsdjList = wsjcModel.getWsdjList();

			if (wsdjList != null && wsdjList.size() > 0) {
				for (int i = 0; i < wsdjList.size(); i++) {
					
					String wsfdj = wsdjList.get(i).get("wsfdj");

					if ("qs".equalsIgnoreCase(tjfs)) {
						colListCN.add(wsfdj + "������");
						colListEN.add("djqss");
					} else {
						colListCN.add(wsfdj + "����");
						colListEN.add("djqss");
					}

					if ("qs".equalsIgnoreCase(tjfs)) {// ����
						colListCN.add(wsfdj + "����������");
						colListEN.add("djpm");
					} else {
						colListCN.add(wsfdj + "����������");
						colListEN.add("djpm");
					}
					
					colListCN.add(wsfdj + "��");
					colListEN.add("djqsl");
				}
			}
		} else {

			if ("qs".equalsIgnoreCase(tjfs)) {
				colListCN.add(dj + "������");
				colListEN.add("djqss");
			} else {
				colListCN.add(dj + "����");
				colListEN.add("djqss");
			}

			if ("qs".equalsIgnoreCase(tjfs)) {// ����
				colListCN.add(dj + "����������");
				colListEN.add("djpm");
			} else {
				colListCN.add(dj + "��������");
				colListEN.add("djpm");
			}

			colListCN.add(dj + "��");
			colListEN.add("djqsl");

		}
		
		return dao.arrayToList(colListEN.toArray(new String[] {}), colListCN
				.toArray(new String[] {}));
	}
	
	/**
	 * �������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void wsjcExp(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os) throws Exception {
		
		String lx = model.getLx();

		if ("qs".equalsIgnoreCase(lx)) {
			wsjcQsExp(model, wsjcModel, os);
		}
	}
	
	/**
	 * �������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void wsjcQsExp(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os) throws Exception {

		String title = "�������";

		// ����
		String lx = model.getLx();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �����ȼ�
		String gldj = wsjcModel.getGldj();
		// ��������
		String glfs = wsjcModel.getGlfs();

		ArrayList<String> topName = new ArrayList<String>();
		
		//topName.add("����");
		if ("xs".equalsIgnoreCase(lx)) {
			topName.add("ѧ��");
			topName.add("����");
		}
		topName.add("ѧ��");
		topName.add("ѧ��");
		topName.add("ѧ������");
		topName.add("���");
		topName.add("У������");
		topName.add("У������");
		topName.add("¥������");
		topName.add("¥������");
		topName.add("����");
		topName.add("���Һ�");
		topName.add("����ܴ�");
		topName.add("���ʱ��");
		topName.add("ʱ������");
		topName.add("��鲿��");
		topName.add("��������");
		if ("����".equalsIgnoreCase(lrxs)) {
			topName.add("������");
			if ("��".equalsIgnoreCase(gldj)) {
				topName.add("�����ȼ�");
			}
		}
		if ("�ȼ�".equalsIgnoreCase(lrxs)) {
			topName.add("�����ȼ�");
			if ("��".equalsIgnoreCase(glfs)) {
				topName.add("������");
			}
		}
		
		// ���������ͷ
		List<HashMap<String, String>> topTr = getTopList(topName.toArray(new String[]{}));
		// ��������
		ArrayList<String[]> list = getWsjcExpList(model, wsjcModel);

		expToExcel(title, topTr, list, os);
	}
	
	/**
	 * ��������ֵ����б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	private ArrayList<String[]> getWsjcExpList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		List<HashMap<String, String>> djList = wsjcModel.getWsdjList();
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// ����
		String lx = model.getLx();
		// ��ѯ�ȼ�
		String cxdj = model.getDj();
		// ��ѯ��������
		String cxxx = model.getFsxx();
		// ��ѯ��������
		String cxsx = model.getFssx();
		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �Ƿ�����ȼ�
		String gldj = wsjcModel.getGldj();
		// �Ƿ��������
		String glfs = wsjcModel.getGlfs();
		
		if ("qs".equalsIgnoreCase(lx)) {//����
			
			list = dao.getQsWsjcExpList(model, wsjcModel);
			
			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {

					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(list.get(i)));

					boolean flag = true;

					if ("����".equalsIgnoreCase(lrxs)) {
						
						arrList.remove(arrList.size() - 1);
						
						if ("��".equalsIgnoreCase(gldj)) {

							String fs = list.get(i)[15];

							boolean isDj = false;
							// ��ȡ�������������ĵȼ�
							for (int j = 0; j < djList.size(); j++) {

								String xx = djList.get(j).get("wsfxx");
								String sx = djList.get(j).get("wsfsx");
								String dj = djList.get(j).get("wsfdj");

								if (Float.parseFloat(fs) >= Float
										.parseFloat(xx)
										&& Float.parseFloat(fs) <= Integer
												.parseInt(sx)) {
									arrList.add(dj);
									isDj = true;
									break;
								}
							}

							if(!isDj){
								arrList.add("");
							}
							
							// �ж��Ƿ���Ҫ�����������ĵȼ����в�ѯ
							if (!Base.isNull(cxdj)
									&& !cxdj.equalsIgnoreCase(arrList
											.get(arrList.size() - 1))) {
								flag = false;
							}
						}
					}

					if ("�ȼ�".equalsIgnoreCase(lrxs)) {
						
						arrList.remove(arrList.size() - 2);
						
						if ("��".equalsIgnoreCase(glfs)) {
							String wsdj = list.get(i)[16];

							// ��ȡ���ȼ��������ķ���
							for (int j = 0; j < djList.size(); j++) {

								String sx = djList.get(j).get("wsfsx");
								String dj = djList.get(j).get("wsfdj");

								if (dj.equalsIgnoreCase(wsdj)) {
									arrList.add(sx);
									break;
								}
							}

							// �ж��Ƿ���Ҫ�����������ķ������в�ѯ
							String fs = arrList.get(arrList.size() - 1);

							if (!Base.isNull(cxxx)
									&& Float.parseFloat(fs) < Integer
											.parseInt(cxxx)) {
								flag = false;
							}

							if (!Base.isNull(cxsx)
									&& Float.parseFloat(fs) > Integer
											.parseInt(cxsx)) {
								flag = false;
							}
						}
					}
					
					if (flag) {
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
			
		}else{//ѧ��
			list = dao.getXsWsjcExpList(model, wsjcModel);
			
			for (int i = 0; i < list.size(); i++) {

				ArrayList<String> arrList = new ArrayList<String>(Arrays
						.asList(list.get(i)));

				boolean flag = true;
				
				if ("����".equalsIgnoreCase(lrxs)) {

					arrList.remove(arrList.size() - 1);

					if ("��".equalsIgnoreCase(gldj)) {

						String fs = list.get(i)[17];

						boolean isDj = false;

						// ��ȡ�������������ĵȼ�
						for (int j = 0; j < djList.size(); j++) {

							String xx = djList.get(j).get("wsfxx");
							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (Float.parseFloat(fs) >= Float.parseFloat(xx)
									&& Float.parseFloat(fs) <= Integer
											.parseInt(sx)) {
								arrList.add(dj);
								isDj = true;
								break;
							}
						}

						if (!isDj) {
							arrList.add("");
						}

						// �ж��Ƿ���Ҫ�����������ĵȼ����в�ѯ
						if (!Base.isNull(cxdj)
								&& !cxdj.equalsIgnoreCase(arrList.get(arrList
										.size() - 1))) {
							flag = false;
						}
					}
				}
				
				if ("�ȼ�".equalsIgnoreCase(lrxs)) {
					
					arrList.remove(arrList.size() - 2);
					
					if ("��".equalsIgnoreCase(glfs)) {
						
						String wsdj = list.get(i)[18];

						// ��ȡ���ȼ��������ķ���
						for (int j = 0; j < djList.size(); j++) {

							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (dj.equalsIgnoreCase(wsdj)) {
								arrList.add(sx);
								break;
							}
						}

						// �ж��Ƿ���Ҫ�����������ķ������в�ѯ
						String fs = arrList.get(arrList.size() - 1);

						if (!Base.isNull(cxxx)
								&& Float.parseFloat(fs) < Integer
										.parseInt(cxxx)) {
							flag = false;
						}

						if (!Base.isNull(cxsx)
								&& Float.parseFloat(fs) > Integer
										.parseInt(cxsx)) {
							flag = false;
						}
					}
				}
				
				if (flag) {
					rsList.add(arrList.toArray(new String[] {}));
				}
			}
		}
		
		return rsList;
	}
	
	/**
	 * �����������ͳ����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void wsjcTjExp(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os, List<HashMap<String, String>> topTr,
			HttpServletRequest request) throws Exception {

		String title = "�������ͳ��";

		// ���������ͷ
		topTr = getWsfTjTop(model, wsjcModel);
		// ��������
		ArrayList<String[]> list = getWsfTjExpList(model, wsjcModel, topTr,
				request);

		expToExcel(title, topTr, list, os);
	}
	
	/**
	 * ��������ֽ���б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsfTjExpList(GyglTyForm model,
			GyglWsjcModel wsjcModel, List<HashMap<String, String>> topTr,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// ͳ�Ʒ�ʽ
		String tjfs = model.getTjfs();

		if ("qs".equalsIgnoreCase(tjfs)) {// ����
			
			ArrayList<String[]> zqsList = dao.getQszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getQspmTjList(model, wsjcModel);
			
			if (zqsList != null && zqsList.size() > 0) {

				for (int i = 0; i < zqsList.size(); i++) {
					
					boolean flag = true;
					
					if (pmList != null && pmList.size() > 0) {
						
						String[] qsInfo = zqsList.get(i);
						
						ArrayList<String> arrList = new ArrayList<String>(Arrays
								.asList(zqsList.get(i)));
		
						arrList.remove(0);
						
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (qsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//��������
								String zqss = qsInfo[qsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									if (k != 0) {
										arrList.add(pmInfo[k]);
									}
									if (k % 2 == 1) {
										// �ȼ���ռ����
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zqss) * 100)
												+ "%";
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								if (k != 0) {
									arrList.add("��");
								}
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		} else {// ѧ��
			ArrayList<String[]> zrsList = dao.getXszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getXspmTjList(model, wsjcModel);
			
			if (zrsList != null && zrsList.size() > 0) {

				for (int i = 0; i < zrsList.size(); i++) {
					
					boolean flag = true;
					
					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(zrsList.get(i)));
					
					arrList.remove(0);
					
					if (pmList != null && pmList.size() > 0) {

						String[] xsInfo = zrsList.get(i);
									
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (xsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//������
								String zrs = xsInfo[xsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									if (k != 0) {
										arrList.add(pmInfo[k]);
									}
									if (k % 2 == 1) {
										// �ȼ���ռ����
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zrs) * 100)
												+ "%";
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								if (k != 0) {
									arrList.add("0");
								}
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		}
		
		return rsList;
	}
	

	/**
	 * ͬ���������Լ��ȼ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void tbWsfAndDj(GyglTyForm model, GyglWsjcModel wsjcModel)
			throws Exception {

		// ¼����ʽ
		String lrxs = wsjcModel.getLrxs();
		// �����ȼ�
		String gldj = wsjcModel.getGldj();
		// �����ֵȼ�
		List<HashMap<String, String>> djList = wsjcModel.getWsdjList();
		
		if ("����".equalsIgnoreCase(lrxs) && "��".equalsIgnoreCase(gldj)) {
			ArrayList<String[]> list = dao.getWsfbPk(model, wsjcModel);

			if (list != null && list.size() > 0) {

				String[] sql = new String[list.size()];
				
				for (int i = 0; i < list.size(); i++) {
					
					String pk = list.get(i)[0];
					String fs = list.get(i)[1];

					String wsfdj = "";

					// ��ȡ�������������ĵȼ�
					for (int j = 0; j < djList.size(); j++) {

						String xx = djList.get(j).get("wsfxx");
						String sx = djList.get(j).get("wsfsx");
						String dj = djList.get(j).get("wsfdj");

						if (Float.parseFloat(fs) >= Float.parseFloat(xx)
								&& Float.parseFloat(fs) <= Float.parseFloat(sx)) {
							wsfdj = dj;
							break;
						}
					}

					sql[i] = "update gygl_wsjc_wsfwhb set wsfdj = '" + wsfdj
							+ "' where jcld||jccs||jcqs||jcsj||jczc = '" + pk
							+ "'";
				}
				
				dao.saveArrDate(sql);
			}
		}

	}
	
	/**
	 * ���ѧ��������¼���ͷ
	 * 
	 */
	public List<HashMap<String, String>> getXslrfTop(GyglWsjcModel wsjcModel, String mk) {
		// �������
//		String jczq = wsjcModel.getJczq();
		// ¼����ʽ
//		String lrxs = wsjcModel.getLrxs();
		// �̶���
		String[] gdName = null;
		if("lr".equalsIgnoreCase(mk)){
			gdName = new String[] { "ѧ��", "����", Base.YXPZXY_KEY+"����", "���Һ�", "��λ��", "���˼ӷ�", "���˼���"};
		}else if("ck".equalsIgnoreCase(mk)) {
			gdName = new String[] {"ѧ��", "����", "ѧ��", "ѧ��", Base.YXPZXY_KEY+"����", "���Һ�", "��λ��", "������", "���˼ӷ�", "���˼���", "ѧ�ڵ÷�"};
		}
		return getTopList(gdName);
	}
	
	/**
	 * ��ȡס��ѧ�����һ���¼���
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getXslrfInfo(GyglTyForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		DAO dao = DAO.getInstance();
		Pages pages = model.getPages();
		
		
		String[] queryList = new String[] { "xydm", "xqdm", "lddm", "cs", "qsh"};
		String[] queryLikeList = new String[] {"xh", "xm"};
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String queryString = myQuery.queryStr();
		String[] inputs = myQuery.getInputList();
		
		String[] outputs = "��".equalsIgnoreCase(model.getJczq()) ?
							new String[]{"xh", "xm", "xymc", "qsh","cwh", "grjf", "grkf"} :
							new String[]{"xh", "xm", "xymc", "qsh","cwh", "grjf", "grkf"};
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.* from (");
		sql.append("select rownum r, a.xh,a.xm,a.xydm,a.xqdm,a.lddm,a.cs,");
		sql.append("a.qsh,a.cwh,a.xymc,a.qswsf,a.jczc,a.jcsj,");
		sql.append("(case when instr(to_char(a.grjf),'.',1,1)=1 then '0'||to_char(a.grjf) else to_char(a.grjf) end) grjf,");
		sql.append("(case when instr(to_char(a.grkf),'.',1,1)=1 then '0'||to_char(a.grkf) else to_char(a.grkf) end) grkf ");
		sql.append("from (");
		sql.append(getXslrfSql(model, queryString));
		sql.append(") a) a where r>=");
		sql.append(pages.getStart());
		sql.append(" and r<");
		sql.append(pages.getStart() + pages.getPageSize());
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from (");
		countSql.append(getXslrfSql(model, queryString));
		countSql.append(")");
		
		String count = dao.rsToVator(countSql.toString(), inputs, new String[]{"num"}).get(0)[0];
		pages.setMaxRecord(Integer.parseInt(count));
		
		return dao.rsToVator(sql.toString(), inputs, outputs);
	}
	
	
	/**
	 * ��ȡѧ��¼���sql���
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String getXslrfSql(GyglTyForm model, String queryString) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder sql = new StringBuilder();
		
		// ���˼ӷ�
		sql.append("select a.*,nvl((select sum(fs) from xg_view_gygl_xsfslr b ");
		sql.append("where b.xh=a.xh and b.xmxz='�ӷ�' and b.xn='");
		sql.append(model.getXn());
		sql.append("' and b.xq='");
		sql.append(model.getXq());
		sql.append("' and b.nd='");
		sql.append(model.getNd());
		sql.append("' ");
		sql.append(getCondition(model));
		sql.append(" group by xh");
		sql.append("),0) grjf,");
		
		// ���˼���
		sql.append("nvl((select sum(fs) from xg_view_gygl_xsfslr b ");
		sql.append("where b.xh=a.xh and b.xmxz='����' and b.xn='");
		sql.append(model.getXn());
		sql.append("' and b.xq='");
		sql.append(model.getXq());
		sql.append("' and b.nd='");
		sql.append(model.getNd());
		sql.append("' ");
		sql.append(getCondition(model));
		sql.append(" group by xh");
		sql.append("),0) grkf ");
		
		sql.append("from (");
		sql.append("select a.xh,a.xm,a.xydm,a.xqdm,a.lddm,a.cs,a.qsh,a.cwh,a.xymc,nvl(b.wsffs,0) qswsf,b.jczc,b.jcsj ");
		sql.append("from view_xszsxx a left join gygl_wsjc_wsfwhb b on ");
		sql.append("a.qsh=b.jcqs and b.xn='");
		sql.append(model.getXn());
		sql.append("' and b.xq='");
		sql.append(model.getXq());
		sql.append("' and b.nd='");
		sql.append(model.getNd());
		sql.append("' ");
		sql.append(getCondition(model));
		
		sql.append(" order by lddm,qsh,cwh");
		sql.append(") a ");
		
		sql.append(queryString);
		sql.append(dao.getGyfdyQuery(model, "a"));
		
		return sql.toString();
	}
	
	
	/**
	 * ��ȡ���Ի�����
	 * @param model
	 * @return
	 * @arthor sjf
	 */
	private String getCondition(GyglTyForm model){
		StringBuilder sql = new StringBuilder();
		String lx = model.getJczq();
		
		if("��".equalsIgnoreCase(lx)){
			sql.append("and b.jczc='");
			sql.append(model.getJczc());
		} else{
			sql.append("and b.jcsj='");
			sql.append(model.getJcsj());
			
			sql.append("' and b.jczc='��");
		}
		
		sql.append("'");
		
		return sql.toString();
	}
	
	/**
	 * ���ѧ������������Ŀ��Ϣ
	 * @param xmxz
	 * @return
	 */
	public List<HashMap<String, String>> getWsjcInfo(String xmxz){
		return dao.getWsjcInfo(xmxz);
	}
	
	/**
	 * ���ѧ��¼���
	 * @param model
	 * @return
	 */
	public boolean saveXslrf(GyglWsjcXslrfModel model, String jczq){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		
		String tableName = "XG_GYGL_XSFSLRB";
		String pk = null;
		String pkValue[] = null;
		
		// ���ݼ�����ھ���ɾ��������
		if("��".equalsIgnoreCase(jczq)){
			pk = "xh||xn||xq||nd||jczc";
			pkValue = new String[]{model.getXh() + model.getXn() + model.getXq() + model.getNd() + model.getJczc()};
		}else {
			pk = "xh||xn||xq||nd||jczc||jcsj";
			pkValue = new String[]{model.getXh() + model.getXn() + model.getXq() + model.getNd() + "��" + model.getJcsj()};
		}
		
		String[] arrzd = new String[]{"fs", "xmdm"};
		
		String[] onezd = "��".equalsIgnoreCase(jczq) ?
						 new String[]{"xh", "xn", "xq", "nd", "jczc", "lrr", "lrsj"} :
						 new String[]{"xh", "xn", "xq", "nd", "jcsj", "lrr", "lrsj"};
		
		SaveForm saveForm = new SaveForm();
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName(tableName);
		
		try {
			dao.saveData(saveForm, model);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ��ȡס��ѧ�����һ���¼���
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getXsxqlrfInfo(GyglTyForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		DAO dao = DAO.getInstance();
		Pages pages = model.getPages();
		
		String[] queryList = new String[] { "xydm", "xqdm", "lddm", "cs", "qsh","xn","xq"};
		String[] queryLikeList = new String[] {"xh", "xm"};
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String queryStr = myQuery.queryStr();
		String[] inputs = myQuery.getInputList();
		
		String[] outputs = "��".equalsIgnoreCase(model.getJczq()) ?
							new String[]{"xh", "xm", "xn", "xqm", "xq", "xymc", "qsh", "cwh", "jcf","jffs", "kffs", "grdf"} :
							new String[]{"xh", "xm", "xn", "xqm", "xq", "xymc", "qsh", "cwh", "jcf","jffs", "kffs", "grdf"};
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.* from (");
		sql.append("select rownum r, a.* from (");
		sql.append(getXsxqlrfSql(model, queryStr));
		sql.append(") a) a where r>=");
		sql.append(pages.getStart());
		sql.append(" and r<");
		sql.append(pages.getStart() + pages.getPageSize());
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from (");
		countSql.append(getXsxqlrfSql(model, queryStr));
		countSql.append(")");
		
		String count = dao.rsToVator(countSql.toString(), inputs, new String[]{"num"}).get(0)[0];
		pages.setMaxRecord(Integer.parseInt(count));
		
		return dao.rsToVator(sql.toString(), inputs, outputs);
	}
	
	/**
	 * ��ȡס��ѧ��ÿѧ�����ҷ�sql
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String getXsxqlrfSql(GyglTyForm model, String queryString) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder sql = new StringBuilder();
		
//		select a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xqdm,a.xqmc,a.yqdm,a.yqmc,a.lddm,a.ldmc,a.cs,a.ssbh,a.qsh,a.cwh,b.*
//		from view_xszsxx a left join
//		(select xh,
//					 xn,
//					 xq,
//					 (select sum(fs)
//							from xg_view_gygl_xsfslr b
//						 where a.xh = b.xh
//							 and a.xq = b.xq
//							 and a.xn = b.xn
//							 and b.xmxz = '�ӷ�') jffs,
//					 (select sum(fs)
//							from xg_view_gygl_xsfslr b
//						 where a.xh = b.xh
//							 and a.xq = b.xq
//							 and a.xn = b.xn
//							 and b.xmxz = '����') kffs
//			from (select xh, xn, xq from xg_view_gygl_xsfslr group by xh, xn, xq)a) b on a.xh=b.xh
		
		// ��������������
		String condition = "";
		
		if("��".equalsIgnoreCase(model.getJczq())){
			condition = "and b.jczc<>'��' ";
		}else{
			condition = "and b.jczc='��' ";
		}
		
		sql.append("select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqm,(a.jcf+a.jffs-a.kffs) grdf from ");
		sql.append("(select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xqdm,a.xqmc,a.yqdm,a.yqmc,a.lddm,a.ldmc,");
		sql.append("a.cs,a.ssbh,a.qsh,a.cwh,b.xn,b.xq,nvl((select jcf from gygl_wsjc_csszb),0) jcf,");
		sql.append("nvl((case when instr(to_char(b.jffs),'.',1,1)=1 then '0'||to_char(b.jffs) else to_char(b.jffs) end),0) jffs,");
		sql.append("nvl((case when instr(to_char(b.kffs),'.',1,1)=1 then '0'||to_char(b.kffs) else to_char(b.kffs) end),0) kffs ");
		sql.append("from view_xszsxx a,");
		sql.append("(select xh,xn,xq,");
		sql.append("(select sum(fs) from xg_view_gygl_xsfslr b where a.xh = b.xh and a.xq = b.xq and a.xn = b.xn and b.xmxz = '�ӷ�' ");
		sql.append(condition);
		sql.append(") jffs,");
		
		sql.append("(select sum(fs) from xg_view_gygl_xsfslr b where a.xh = b.xh and a.xq = b.xq and a.xn = b.xn and b.xmxz = '����' ");
		sql.append(condition);
		sql.append(") kffs ");
		sql.append("from (select xh, xn, xq from xg_view_gygl_xsfslr b where 1=1 " ); 
		sql.append(condition);
		sql.append("group by xh, xn, xq)a");
		
		sql.append(")b where a.xh=b.xh)a "); 
		sql.append(queryString);
		sql.append(dao.getGyfdyQuery(model, "a"));
		
  		return sql.toString();
	}
	
	/**   
	 * ��ȡĳ��ѧ��ĳһѧ�ڵ����м������
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public List<HashMap<String, String>> getXsfsInfo(String xh, String xn, String xq, String jczq){
		String tableName = "xg_view_gygl_xsfslr";
		
		String[] output = null;
		StringBuilder query = new StringBuilder();
		
		if("��".equalsIgnoreCase(jczq)){
			output = new String[]{"jczc", "xmmc", "xmxz", "fs", "lrr"};
			query.append(" where jczc<>'��'");
			query.append(" and xh=? and xn=? and xq=?");
			query.append(" order by jczc");
		}else {
			output = new String[]{"jcsj", "xmmc", "xmxz", "fs", "lrr"};
			query.append(" where jczc = '��'");
			query.append(" and xh=? and xn=? and xq=?");
			query.append(" order by jcsj");
		}
		
		
		return CommonQueryDAO.commonQueryforList(tableName, query.toString(), new String[]{xh,xn,xq}, output, "");
		
	}
	
	
	/**
	 * ѧ��������ͳ��
	 * @param os
	 * @param nj
	 * @throws Exception 
	 */
	public void xswsfTj(GyglTyForm model, OutputStream os) throws Exception {
		String bjmc = model.getBjmc();
		String xn = model.getXn();
		String xqdm = model.getXq();
		
		GyglWsjcModel wsjcModel = getWsjc();
		int zgzc = Integer.parseInt(wsjcModel.getZgzc());
		
		int cols = 8 + zgzc;
		
		// ����excel����
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(bjmc, 0);
		
		// ��ȡѧ��ͳ�Ʒ�������
		List<HashMap<String, String>> list = getXsfstjData(model);
		Map<String, String> rsTj = getRstjData(model);
		
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			String xqmc = BasicExtendService.xqMap.get(xqdm);
			if(xqmc != null && xqmc.length()>2){
				xqmc = xqmc.substring(0,xqmc.indexOf("ѧ��"));
			}
			// ����
			excel.printTitle(ws, bjmc + "(" + xn + "��" + xqmc + "ѧ������÷�)", 23, 400);
			
			// ������
			ws.addCell(new Label(0, 1, "�Һ�", wcfTytle));
			ws.addCell(new Label(1, 1, "����", wcfTytle));
			ws.addCell(new Label(2, 1, "����", wcfTytle));
			ws.mergeCells(3, 1, 4, 1);
			ws.addCell(new Label(3, 1, "�÷�", wcfTytle));
			ws.addCell(new Label(5, 1, "�ܷ�", wcfTytle));
			for(int i=1; i<=zgzc; i++){
				ws.addCell(new Label(5+i, 1, i+"", wcfTytle));
			}
			ws.addCell(new Label(6+zgzc, 1, "�ܿ۷�", wcfTytle));

			ws.addCell(new Label(7+zgzc, 1, "����", wcfTytle));
			
			int xqIndx = "01".equalsIgnoreCase(xqdm) ? 3 : 4;
			
			// ���ݽ�Ҫ������к�
			int row = 2;
			for(int i =0; i<list.size(); i++){
				Map<String, String> currObj = list.get(i);
				
				// ���ǵ�һ�����ݣ�ֱ�Ӳ���excel����ȥ
				if(i==0){
					insertExcel(zgzc, ws, wcfTytle, xqIndx, row, currObj);
				}else{
					// ��ȡ��һ����������뵱ǰ�����ѧ����ͬ�����������ֻ�Ǳ����˲�ͬ�ܴεķ�����
					// ��չ��ͬѧ�ŵ��������ݵ���ȥ���������һ���µ����ݡ�
					Map<String, String> befObj = list.get(i-1);
					if(currObj.get("xh").equalsIgnoreCase(befObj.get("xh"))){
						if(StringUtils.isNotNull(currObj.get("jczc"))){
							int zc = Integer.parseInt(currObj.get("jczc"));
							ws.addCell(new Label(5+zc, row, currObj.get("fs"), wcfTytle));
						}
					}else{
						row++;
						insertExcel(zgzc, ws, wcfTytle, xqIndx, row, currObj);
					}
				}
			}
			coalitionCols(ws, wcfTytle, 2, 2, cols-1, row+3);
			ExcelMB.mergeCells(ws, row-1, 0, 3);
			
			ws.addCell(new Label(0, row+1, "ס��", wcfTytle));
			ws.addCell(new Label(1, row+1, rsTj.get("ns"), wcfTytle));
			ws.addCell(new Label(2, row+1, rsTj.get("vs"), wcfTytle));
			ws.addCell(new Label(3, row+1, "��", wcfTytle));
			ws.addCell(new Label(4, row+1, "��", wcfTytle));
	
			ws.addCell(new Label(0, row+2, "�ϼ�", wcfTytle));
			ws.addCell(new Label(1, row+2, rsTj.get("zs"), wcfTytle));
			ws.addCell(new Label(0, row+3, "�߶�", wcfTytle));
			ws.addCell(new Label(1, row+3, rsTj.get("wz"), wcfTytle));
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ����Excelһ����¼
	 * @param zgzc
	 * @param ws
	 * @param wcfTytle
	 * @param xqIndx
	 * @param row
	 * @param currObj
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void insertExcel(int zgzc, WritableSheet ws,
			WritableCellFormat wcfTytle, int xqIndx, int row,
			Map<String, String> currObj) throws WriteException,
			RowsExceededException {
		ws.addCell(new Label(0, row, currObj.get("qsh"), wcfTytle));
		ws.addCell(new Label(1, row, currObj.get("cwh"), wcfTytle));
		ws.addCell(new Label(2, row, currObj.get("xm"), wcfTytle));
		ws.addCell(new Label(5, row, currObj.get("jcf"), wcfTytle));
		
		int df = 100;
		if(StringUtils.isNotNull(currObj.get("zkf")) && StringUtils.isNotNull(currObj.get("jcf"))){
			df = Integer.parseInt(currObj.get("jcf")) + Integer.parseInt(currObj.get("zkf")) ;
		}

		ws.addCell(new Label(xqIndx, row, df+"", wcfTytle));
		ws.addCell(new Label(6+zgzc, row, currObj.get("zkf"), wcfTytle));
		
		if(StringUtils.isNotNull(currObj.get("jczc"))){
			int zc = Integer.parseInt(currObj.get("jczc"));
			ws.addCell(new Label(5+zc, row, currObj.get("fs"), wcfTytle));
		}
	}
	
	
	public void coalitionCols(WritableSheet ws, WritableCellFormat wcfTytle, int x, int y, int x1, int y1) throws RowsExceededException, WriteException{
		// ����
		int rows = y1 - y + 1;
		
		// ����
		int cols = x1 - x + 1;
		
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				int currX = x + j;
				int currY = y + i;
				
				WritableCell a1 = ws.getWritableCell(currX, currY);
				if(StringUtils.isNull(a1.getContents())){
					ws.addCell(new Label(currX, currY, "", wcfTytle));
				}
			}
		}
	}
	
	private Map<String, String> getRstjData(GyglTyForm model){
//		select (select count(*) from view_xsjbxx where bjdm='306302702') zs,
//		(select '��' || count(*) from view_xszsxx where bjdm='306302702' and xb='��') ns,
//		(select 'Ů' || count(*) from view_xszsxx where bjdm='306302702' and xb='Ů') vs,
//		(select count(*) num from view_xsjbxx a where bjdm='306302702' and not exists (select 1 from view_xszsxx b where b.xh=a.xh)) wz from dual;
		
		StringBuilder sql = new StringBuilder();
		String bjCondition = "bjdm='" + model.getBjdm() + "'";
		
		sql.append("select (select count(*) from view_xsjbxx where ");
		sql.append(bjCondition);
		sql.append(") zs,");
		sql.append("(select '��' || count(*) from view_xszsxx where xb='��' and ");
		sql.append(bjCondition);
		sql.append(") ns,");
		sql.append("(select 'Ů' || count(*) from view_xszsxx where xb='Ů' and ");
		sql.append(bjCondition);
		sql.append(") vs,");
		sql.append("(select count(*) num from view_xsjbxx a where ");
		sql.append(bjCondition);
		sql.append(" and not exists (select 1 from view_xszsxx b where b.xh=a.xh)) wz from dual");
		
		return DAO.getInstance().getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * ���ͳ������
	 * @param model
	 * @return
	 */
	private List<HashMap<String, String>> getXsfstjData(GyglTyForm model) throws Exception{
//		select a.xh,a.xm,a.bjmc,a.qsh,a.cwh,a.zkf,b.jczc,b.fs from 
//		(select a.qsh, a.cwh, a.xh, a.xm, a.bjmc, a.bjdm,
//		(select sum((case xmxz when '����' then '-'||fs else fs end))
//		from xg_view_gygl_xsfslr where jczc<>'��' and xn='2010-2011' and xh=a.xh) zkf
//			from view_xszsxx a
//		 where a.bjdm = '306302702')a left join
//		(select a.xh,a.jczc,sum(fs)fs from
//		(select xh,jczc,xn,xq,(case xmxz when '����' then '-'||fs else fs end)fs
//		from xg_view_gygl_xsfslr where jczc<>'��' and xn='2010-2011')a group by xh,jczc)b on a.xh=b.xh order by qsh,cwh;
		String[] colList = new String[]{"xh", "qsh", "cwh", "xm", "zkf", "jczc", "fs", "jcf"};
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,a.xm,a.bjmc,a.qsh,a.cwh,nvl(a.zkf,'') zkf,nvl(b.jczc,'') jczc,nvl(b.fs,'') fs,");
		sql.append("(select jcf from gygl_wsjc_csszb) jcf from ");
		sql.append("(select a.qsh, a.cwh, a.xh, a.xm, a.bjmc, a.bjdm,");
		sql.append("(select sum((case xmxz when '����' then '-'||fs else fs end))");
		sql.append("from xg_view_gygl_xsfslr where jczc<>'��' and ");
		sql.append("xn='");
		sql.append(model.getXn());
		sql.append("' and xh=a.xh) zkf ");
		sql.append("from view_xszsxx a ");
		sql.append("where a.bjdm='");
		sql.append(model.getBjdm());
		sql.append("')a left join ");
		sql.append("(select a.xh,a.jczc,sum(fs)fs from ");
		sql.append("(select xh,jczc,xn,xq,(case xmxz when '����' then '-'||fs else fs end)fs ");
		sql.append("from xg_view_gygl_xsfslr where jczc<>'��' and xn='");
		sql.append(model.getXn());
		sql.append("')a group by xh,jczc)b on a.xh=b.xh order by qsh,cwh");
		
		return CommonQueryDAO.commonQueryforList("", "", new String[]{}, colList, sql.toString());
	}
}

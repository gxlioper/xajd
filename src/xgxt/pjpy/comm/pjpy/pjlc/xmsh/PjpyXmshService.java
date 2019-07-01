package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqForm;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ���_Service��
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

public class PjpyXmshService extends PjpyPjlcService {

	PjpyXmshDAO dao = new PjpyXmshDAO();

	/**
	 * ��������Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXhxmList(PjpyXmshForm model,
			User user) {

		List<HashMap<String, String>> xmList = dao.getXhxmList(model, user);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				
				HashMap<String, String> map = xmList.get(i);
				// ��Ŀ����
				String xmdm = map.get("xmdm");
				// ����ѧ��
				String pjxn = model.getJbszModel().getPjxn();
				// ����ѧ��
				String pjxq = model.getJbszModel().getPjxq();
				// �������
				String pjnd = model.getJbszModel().getPjnd();

				String pkValue = pjxn + pjxq + pjnd + xmdm;
				
				PjpyXmszModel xmszModel = getXmszForXmdm(pkValue);
				
				//����id
				String lcid = xmszModel.getLcid();
				//��½�û�
				String userName = user.getUserName();
				
				boolean flag = false;
				
				if(!Base.isNull(lcid)){
					// ������Ϣ
					List<HashMap<String, String>> lcInfoList = dao
							.getLcInfo(lcid);
					// ��λ��Ϣ
					List<HashMap<String, String>> gwInfoList = dao
							.getGwInfo(userName,lcid);
					
					if (lcInfoList != null && lcInfoList.size() > 0) {
						for (int j = 0; j < lcInfoList.size(); j++) {
							String spgw = lcInfoList.get(j).get("spgw");
							if (gwInfoList != null && gwInfoList.size() > 0) {
								for (int k = 0; k < gwInfoList.size(); k++) {
									String yhgw = gwInfoList.get(k).get("spgw");
									if (spgw.equalsIgnoreCase(yhgw)) {
										flag = true;
										break;
									}
								}
							}
							if (flag) {
								break;
							}
						}
					}
				}
				
				if (flag) {
					list.add(map);
				}
			}
		}

		return list;
	}

	/**
	 * �����Ŀ���ѧ���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getXmshXsList(PjpyXmshForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getXmshXsList(model, user, colList, request);

	}

	/**
	 * �������״̬
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXmshzt(PjpyXmshForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// ������Ŀ��˱�
		String tableName = "xg_pjpy_pjxmshb";
		// ����
		String pk = "xmdm||pjxn||pjxq||pjnd||xh||xtgwid";
		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();
		// �޸��ֶ�
		String[] onezd = new String[] { "shzt", "shsj", "shr","shyj" };
		// /���ʱ��
		String shsj = dao.getNowTime("YYYYMMDD");
		// ���״̬
		String shzt = model.getShzt();
		// �����
		String shr = user.getUserName();

		model.setShzt(shzt);
		model.setShr(shr);
		model.setShsj(shsj);
		if (Base.isNull(model.getShyj())) {
			model.setShyj("");
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * �������״̬(������)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXmcszt(PjpyXmshForm model, User user) throws Exception {

		// ������Ŀ��˱�
		String tableName = "xg_pjpy_pjxmshb";
		// ����
		String pk = "xmdm||pjxn||pjxq||pjnd||xh||xtgwid";
		// ����ֵ
		String[] pkValue = dao.getXmthPk(model);
		// �޸��ֶ�
		String[] onezd = new String[] { "shzt", "shsj", "shr" };
		// /���ʱ��
		DAO dao = DAO.getInstance();
		String shsj = dao.getNowTime("YYYYMMDD");
		// �����
		String shr = user.getUserName();

		model.setShzt("������");
		model.setShr(shr);
		model.setShsj(shsj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * �������״̬(������)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXmycszt(PjpyXmshForm model, User user) throws Exception {

		// ������Ŀ��˱�
		String tableName = "xg_pjpy_pjxmshb";
		// ����
		String pk = "xmdm||pjxn||pjxq||pjnd||xh||xtgwid";
		// ����ֵ
		String[] pkValue = dao.getXmcsPk(model);
		// �޸��ֶ�
		String[] onezd = new String[] { "shzt", "shsj", "shr" };
		// /���ʱ��
		DAO dao = DAO.getInstance();
		String shsj = dao.getNowTime("YYYYMMDD");
		// �����
		String shr = user.getUserName();

		model.setShzt("δ���");
		model.setShr(shr);
		model.setShsj(shsj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * �����Ŀ��˼������գ�
	 * 
	 * @author ΰ�����
	 */
	public String getXmzzshjb(PjpyXmshForm model) {
		return dao.getXmzzshjb(model);
	}
	
	/**
	 * �����Ŀ�������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String getXmxzInfo(PjpyXmshForm model) throws Exception {

		// ��Ŀ���ö���
		PjpyXmszModel xmszModel = model.getXmszModel();
		// ���Ʒ�Χ
		String kzfw = xmszModel.getKzfw();
		// �������
		List<HashMap<String, String>> bshrList = dao.getXmshBshr(model);
		// ������˲��Ŵ���
		String[] bmdm = dao.getBshrbm(bshrList, kzfw);
		// ��Ŀ��������б�
		List<HashMap<String, String>> bmrsList = dao.getXmshrs(model, bmdm);
		// ��Ŀ�Ǽ���б�
		List<HashMap<String, String>> xmfjdList = dao.getXmjdList(model);
		// ��������ѻ�ý�ѧ���б�
		List<HashMap<String, String>> yhdjxjList = dao.getYhdjxjList(model,
				bshrList);

		// ��ʾ��Ϣ
		String message = "";

		// �ж���Ŀ��������
		message = judgeRsxz(model, bmrsList, bshrList);

		if (!Base.isNull(message)) {
			return message;
		}

		// �ж���Ŀ�������
		message = judgeJdxz(model, xmfjdList, yhdjxjList);

		if (!Base.isNull(message)) {
			return message;
		}

		return null;
	}

	/**
	 * �ж���Ŀ��������
	 * 
	 * @author ΰ�����
	 */
	private String judgeRsxz(PjpyXmshForm model,List<HashMap<String, String>> bmrsList,
			List<HashMap<String, String>> bshrList) {

		// ��Ŀ���ö���
		PjpyXmszModel xmszModel = model.getXmszModel();
		// ���Ʒ�Χ
		String kzfw = xmszModel.getKzfw();
		// ��������
		String rssz = xmszModel.getRssz();

		if ("��".equalsIgnoreCase(rssz) && bmrsList != null
				&& bmrsList.size() > 0) {
			for (int i = 0; i < bmrsList.size(); i++) {
				HashMap<String, String> bmInfo = bmrsList.get(i);
				// ���Ŵ���
				String dm = bmInfo.get("bmdm");
				// ���ű���������
				int bmrs = Integer.parseInt(bmInfo.get("bmrs"));
				// ��ͨ������
				int ytgrs = Integer.parseInt(bmInfo.get("ytgrs"));

				// ��������
				if (bshrList != null && bshrList.size() > 0) {
					for (int j = 0; j < bshrList.size(); j++) {
						HashMap<String, String> bshrInfo = bshrList.get(j);
						// �꼶
						String nj = bshrInfo.get("nj");
						// ѧԺ����
						String xydm = bshrInfo.get("xydm");
						// רҵ����
						String zydm = bshrInfo.get("zydm");
						// �༶����
						String bjdm = bshrInfo.get("bjdm");
						// ѧԺ����
						String xymc = bshrInfo.get("xymc");
						// רҵ����
						String zymc = bshrInfo.get("zymc");
						// �༶����
						String bjmc = bshrInfo.get("bjmc");
						// ��������
						String sybm = "";
						// ��������
						String bmmc = "";
						// ���Ʒ�Χ
						if ("nj".equalsIgnoreCase(kzfw)) {
							sybm = nj;
							bmmc = nj + "��";
						} else if ("xy".equalsIgnoreCase(kzfw)) {
							sybm = xydm;
							bmmc = xymc;
						} else if ("zy".equalsIgnoreCase(kzfw)) {
							sybm = nj + zydm;
							bmmc = nj + "��" + zymc;
						} else if ("bj".equalsIgnoreCase(kzfw)) {
							sybm = bjdm;
							bmmc = bjmc;
						}

						if (sybm.equalsIgnoreCase(dm)) {
							// ������ͨ������
							ytgrs++;

							if (ytgrs > bmrs) {

								String xmmc = xmszModel.getXmmc();

								StringBuilder message = new StringBuilder();
								message.append(bmmc);
								message.append("��" + xmmc + "��");
								message.append("���ͨ������Ϊ");
								message.append(bmrs);
								message.append("��,\n");
								message.append("���ͨ���������࣬��ȷ�ϣ�");

								return message.toString();
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * �ж���Ŀ�������
	 * 
	 * @author ΰ�����
	 */
	private String judgeJdxz(PjpyXmshForm model,
			List<HashMap<String, String>> xmfjdList,
			List<HashMap<String, String>> yhdjxjList) {

		// ��Ŀ����
		String xmdm = model.getShxm();
		// ����ѧ��
		String xn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String xq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String nd = PjxtszModel.pjxtszModel.getPjnd();
		
		if (yhdjxjList != null && yhdjxjList.size() > 0) {
			for (int i = 0; i < yhdjxjList.size(); i++) {
				HashMap<String, String> yhInfo = yhdjxjList.get(i);
				// �ѻ����Ŀ����
				String yhddm = yhInfo.get("xmdm");
				// �������ѧ��
				String xh = yhInfo.get("xh");
				// �����������
				String xm = yhInfo.get("xm");			
				// ѧ��
				String pjxn = yhInfo.get("pjxn");
				// ѧ��
				String pjxq = yhInfo.get("pjxq");
				// ���
				String pjnd = yhInfo.get("pjnd");
				// ��������
				String sqzq = yhInfo.get("sqzq");
		
				if (("xn".equalsIgnoreCase(sqzq) && xn.equalsIgnoreCase(pjxn))
					|| ("nd".equalsIgnoreCase(sqzq) && nd.equalsIgnoreCase(pjnd))
					|| ("xq".equalsIgnoreCase(sqzq) && xn.equalsIgnoreCase(pjxn) && xq.equalsIgnoreCase(pjxq))) {

					if (xmfjdList != null && xmfjdList.size() > 0) {
						for (int j = 0; j < xmfjdList.size(); j++) {
							HashMap<String, String> xmfjdInfo = xmfjdList
									.get(j);
							// �Ի����Ŀ����
							String dm = xmfjdInfo.get("xmdm");
							// �Ǽ�ô���
							String fjddm = xmfjdInfo.get("fjddm");
							// ��Ŀ����
							String fjdmc = xmfjdInfo.get("fjdmc");

							if (xmdm.equalsIgnoreCase(dm)
									&& yhddm.equalsIgnoreCase(fjddm)) {

								StringBuilder message = new StringBuilder();
								message.append(xh);
								message.append("(" + xm + ")");
								message.append("�ѻ��");
								message.append("��" + fjdmc + "��,\n");
								message.append("��������Ŀ���ɼ��");

								return message.toString();
							}
						}
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * �����Ŀ�����Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmshInfoList(PjpyXmshForm model) {

		List<HashMap<String, String>> infoList = dao.getXmshInfoList(model);

		return infoList;
	}
	
	/**
	 * �����Ŀ�����б��б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSyxmList(PjpyXmshForm model) {

		return dao.getSyxmList(model);
	}
	
	/**
	 * �ɷ�˳��
	 * 
	 * @author ΰ�����
	 */
	public Boolean getKfsy(PjpyXmshForm model) {
		
		// ��Ŀ����
		String xmdm = model.getSyxm();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pk);
		
		return dao.getKfsy(model, xmszModel);
	}
	
	/**
	 * ������Ŀ˳��
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveXmsy(PjpyXmshForm model, User user) {

		PjpyXmsqService xmsqService = new PjpyXmsqService();
		PjpyXmsqForm xmsqModel = new PjpyXmsqForm();

		// ��Ŀ����
		String xmdm = model.getSyxm();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ѧ��
		String xh = model.getXh();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pk);

		
		DAO dao = DAO.getInstance();
		
		String sqsj = dao.getNowTime("YYYYMMDD");
		xmsqModel.setXh(xh);
		xmsqModel.setSqly("���ݡ�"+xmmc+"�����������˳�ӵ�����Ŀ��");
		xmsqModel.setSqsj(sqsj);
		xmsqModel.setTjr(user.getUserName());

		return xmsqService.saveXssq(xmszModel, xmsqModel);
	}
}
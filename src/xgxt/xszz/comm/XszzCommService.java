package xgxt.xszz.comm;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.guizhdx.GuizhdxDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.xszz.XszzDAO;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.hzny.XszzHznyService;
import xgxt.xszz.nnzy.XszzNnzyService;
import xgxt.xszz.tjgy.XszzPrintService;
import xgxt.xszz.xysf.XszzXysfService;
import xgxt.xszz.zjkj.XszzZjkjService;
import xgxt.xszz.zjyd.XszzZjydService;

import common.Globals;
import common.XszzXmdm;

public class XszzCommService extends XszzService {

	XszzCommDAO dao = new XszzCommDAO();

	/**
	 * �۲�����
	 * 
	 * @return
	 */
	private String[] getZcfTitle() {
		String[] title = null;
		if (Globals.XXDM_MJXY.equals(Base.xxdm)) {
			title = new String[] { "Ʒ����Ϊ���������÷�", "רҵѧϰ���������÷�", "����ʵ�����������÷�" };
		} else {
			title = new String[] { "����������", "����������", "���������" };
		}
		return title;
	}

	// =======================����made by ΰ���luo==========================

	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {

		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;

		String[] arr_lx = lx.split("!!@@!!");
		String xmdm = "";
		if (arr_lx != null && arr_lx.length == 2) {
			lx = arr_lx[0];
			xmdm = arr_lx[1];
		}
		// ��������_ѧԺ
		if ("rssz_xy".equalsIgnoreCase(lx)) {
			colListCN = new String[] { Base.YXPZXY_KEY+"����", "��������" };
			colListEN = new String[] { "xymc", "szrs" };
		}
		// ��������_רҵ
		else if ("rssz_zy".equalsIgnoreCase(lx)) {

			colListCN = new String[] { Base.YXPZXY_KEY+"����", "�꼶", "רҵ����", "��������" };
			colListEN = new String[] { "xymc", "nj", "zymc", "szrs" };
		}
		// ��������_�༶
		else if ("rssz_bj".equalsIgnoreCase(lx)) {

			colListCN = new String[] { Base.YXPZXY_KEY+"����", "�꼶", "רҵ����", "�༶����", "��������" };
			colListEN = new String[] { "xymc", "nj", "zymc", "�༶����", "szrs" };
		}
		// ��Ŀ����
		else if ("xmsq".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "��Ŀ����", "��Ŀ���", "��˼���", "���Ƽ���", "��������",
					"��������", "Ŀǰ���״̬", "����" };
			colListEN = new String[] { "xmmc", "xmlb", "shjb", "kzjb", "szrs",
					"rssx", "shzt", "cz" };
		}
		// ��Ŀ���
		else if ("xmsh".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "��Ŀ����", "��Ŀ���", "��Ŀ��������", "���������",
					"���������" };
			colListEN = new String[] { "xmmc", "xmlb", "sqrs", "xshrs", "shrs" };
		}
		// ��Ŀ���(ѧ��)
		else if ("xmsh_xs".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", "רҵ����",
					"�༶����", "����ʱ��", "���״̬" };
			colListEN = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "shzt1" };
		}
		// �����ѯ
		else if ("jgcx".equalsIgnoreCase(lx)) {

			// ��������
			String sqzq = "";
			// �Ƿ�ּ�
			String sffj = "";
			// ��˼���
			String shjb = "";
			// �Ƿ��漰���
			String sfje = "";

			XszzTyForm model = new XszzTyForm();
			model.setPkValue(xmdm);
			HashMap<String, String> map = getXmxgInfo(model);
			xmdm = map.get("xmdm");

			if (!Base.isNull(xmdm)) {
				sfje = map.get("sfje");
				sffj = map.get("sffj");
				shjb = map.get("shjb");
				sqzq = map.get("sqzq");
			}

			ArrayList<String> cnList = new ArrayList<String>();
			ArrayList<String> enList = new ArrayList<String>();
			cnList.add("ѧ��");
			enList.add("xh");
			cnList.add("����");
			enList.add("xm");
			cnList.add("�Ա�");
			enList.add("xb");
			cnList.add("�꼶");
			enList.add("nj");
			cnList.add(Base.YXPZXY_KEY+"����");
			enList.add("xymc");
			cnList.add("רҵ����");
			enList.add("zymc");
			cnList.add("�༶����");
			enList.add("bjmc");
			cnList.add("��Ŀ����");
			if ("ѧ��".equalsIgnoreCase(sqzq)) {
				cnList.add("ѧ��");
				enList.add("xn");
			} else if ("ѧ��".equalsIgnoreCase(sqzq)) {
				cnList.add("ѧ��");
				enList.add("xn");
				cnList.add("ѧ��");
				enList.add("xq");
			} else if ("���".equalsIgnoreCase(sqzq)) {
				cnList.add("���");
				enList.add("nd");
			}
			enList.add("xmmc");
			cnList.add("����ʱ��");
			enList.add("sqsj");

			String xxdm = Base.xxdm;
			//������������
			if (Globals.XXDM_BJLHLYDX.equalsIgnoreCase(xxdm)) {
				// ��ɫͨ��
				if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {

					cnList.add("�Ƿ�ͱ�");
					enList.add("sfdb");

					cnList.add("�Ƿ������");
					enList.add("kzzd3");
					
					cnList.add("�˾�������");
					enList.add("jtrjsr");
				}
				
			}
			
			
			if ("�ּ�".equalsIgnoreCase(sffj)) {
				// �������϶�
				if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {
					cnList.add("�϶����");
					enList.add("xmzzjb");
				} else {
					cnList.add("��Ŀ����");
					enList.add("xmzzjb");
				}
			}
			
			if ("��Ҫ".equalsIgnoreCase(sfje)) {
				cnList.add("���");
				enList.add("xmzzje");
			}
			
			// ����ʦ��
			if (Globals.XXDM_XYSFXY.equalsIgnoreCase(xxdm)) {
				// ��ɫͨ��
				if (XszzXmdm.XSZZ_LSTD.equalsIgnoreCase(xmdm)) {

					cnList.add("Ӧ�ɽ��");
					enList.add("yjje");

					cnList.add("ʵ�ɽ��");
					enList.add("hjje");
				}
				
				// ������ѧ����
				if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {

					cnList.add("�������");
					enList.add("dknd");

					cnList.add("������");
					enList.add("dkje");
				}
			}
			
			if ("һ�����".equalsIgnoreCase(shjb)) {
				cnList.add("���״̬");
				enList.add("shzt");
			} else if ("�������".equalsIgnoreCase(shjb)) {
				cnList.add("һ�����");
				enList.add("shzt1");
				cnList.add("�������");
				enList.add("shzt2");
			} else if ("�������".equalsIgnoreCase(shjb)) {
				cnList.add("һ�����");
				enList.add("shzt1");
				cnList.add("�������");
				enList.add("shzt2");
				cnList.add("�������");
				enList.add("shzt3");
			} else {
				cnList.add("���״̬");
				enList.add("shzt");
			}

			colListCN = cnList.toArray(new String[] {});
			colListEN = enList.toArray(new String[] {});

		}
		// ------------2010.12.02 qph ��������-----------------------
		else if ("zzxb".equalsIgnoreCase(lx)) {

			XszzTyForm model = new XszzTyForm();
			model.setPkValue(xmdm);
			HashMap<String, String> map = getXmxgInfo(model);
			xmdm = map.get("xmdm");

			ArrayList<String> cnList = new ArrayList<String>();
			ArrayList<String> enList = new ArrayList<String>();
			cnList.add("ѧ��");
			enList.add("xh");
			cnList.add("����");
			enList.add("xm");
			cnList.add("�Ա�");
			enList.add("xb");
			cnList.add("�꼶");
			enList.add("nj");
			cnList.add(Base.YXPZXY_KEY+"����");
			enList.add("xymc");
			cnList.add("רҵ����");
			enList.add("zymc");
			cnList.add("�༶����");
			enList.add("bjmc");
			cnList.add("����ʱ��");
			enList.add("sqsj");
			cnList.add("����״̬");
			enList.add("isxb");

			colListCN = cnList.toArray(new String[] {});
			colListEN = enList.toArray(new String[] {});
		}
		// -----------end ��������--------------------------

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * ��������
	 * 
	 * @author luojw
	 */
	public String getXmbh(String tableName, String zd) {
		return dao.getXmbh(tableName, zd);
	}

	/**
	 * ������Ŀά��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmwh(XszzTyForm model, HashMap<String, String> tjMap,
			HttpServletRequest request) throws Exception {

		// ������Ŀ�����Ϣ
		boolean flag = saveXmxgInfo(model, request);

		if (flag) {
			// ������Ŀ���������Ϣ
			flag = saveXmjbInfo(model);
		}
		if (flag) {
			// ������Ŀ���������Ϣ
			flag = saveXmtjInfo(model, tjMap);
		}

		if (flag){
			//���������Ϣ
			flag = dao.saveBkjdxm(model);
		}
		
		return flag;
	}

	/**
	 * ������Ŀ���
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmxgInfo(XszzTyForm model, HttpServletRequest request)
			throws Exception {

		String[] onezd = new String[] { "xmdm", "xmmc", "xmb", "mrxm", "xxdm",
				"xmsm", "sfje", "sffj", "jelx", "kgzt", "shjb", "bzrsh",
				"fdysh", "xysh", "xxsh", "rskz", "bzrkz", "fdykz", "xykz",
				"xxkz", "kzjb", "sqzq", "rssx", "xmlb", "pdsj", "xssx" };

		String tableName = "xszz_zzxmb";

		String pk = "xmdm";

		// ��˼���
		String shjb = model.getShjb();
		// �����
		String[] shr = model.getShr();
		// ��ʦ���
		String lssh = model.getLssh();
		// ������˼���
		if (shr != null && shr.length > 0 && !"�������".equalsIgnoreCase(shjb)) {
			for (int i = 0; i < shr.length; i++) {
				if ("ѧУ���".equalsIgnoreCase(shr[i])) {
					model.setXxsh("��");
				} else if ("ѧԺ���".equalsIgnoreCase(shr[i])) {
					model.setXysh("��");
				} else {
					if ("������".equalsIgnoreCase(lssh)) {
						model.setBzrsh("��");
					} else {
						model.setFdysh("��");
					}
				}
			}
		}

		// ������
		String[] kzr = model.getKzr();
		// ��������
		String rskz = model.getRskz();
		// ���ÿ��Ƽ���
		if (kzr != null && kzr.length > 0 && "��Ҫ".equalsIgnoreCase(rskz)) {
			for (int i = 0; i < kzr.length; i++) {
				if ("ѧУ���".equalsIgnoreCase(kzr[i])) {
					model.setXxkz("��");
				} else if ("ѧԺ���".equalsIgnoreCase(kzr[i])) {
					model.setXykz("��");
				} else {
					model.setBzrkz("��");
					model.setFdykz("��");
				}
			}
		}
		if ((kzr == null || kzr.length == 0) && "��Ҫ".equalsIgnoreCase(rskz)) {
			// ��δָ�����������һ���𣬾Ϳ���ѧԺ���
			model.setXykz("��");
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXmdm() });

		return saveXszz(saveForm, model, request);
	}

	/**
	 * ������Ŀ������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmjbInfo(XszzTyForm model) throws Exception {

		String[] arrzd = new String[] { "fjmc", "fjxxje", "fjsxje", "fjqdje" };
		String[] onezd = new String[] { "xmdm" };

		String tableName = "xszz_xmfjqkb";

		String pk = "xmdm";

		// ����model��ֵ
		setXmjbModel(model);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXmdm() });

		return saveXszz(saveForm, model);
	}

	/**
	 * ������Ŀ����moel��ֵ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	private void setXmjbModel(XszzTyForm model) {
		// �Ƿ���Ҫ���
		String sfje = model.getSfje();
		// �Ƿ���Ҫ�ּ�
		String sffj = model.getSffj();
		// ����Ƿ���Ҫ����
		String jelx = model.getJelx();

		String[] fjmc = null;
		// �ּ����޽��
		String[] fjxxje = null;
		// �ּ����޽��
		String[] fjsxje = null;
		// �ּ�ȷ�����
		String[] fjqdje = null;
		// ���ȷ��ֵ
		String noInfo = "��";

		// ��Ҫ���
		if ("��Ҫ".equalsIgnoreCase(sfje)) {
			// ����Ҫ�ּ�
			if ("���ּ�".equalsIgnoreCase(sffj)) {
				// �ּ�����
				fjmc = new String[] { noInfo };
				// �ּ����޽��
				fjxxje = new String[] { noInfo };
				// �ּ����޽��
				fjsxje = new String[] { noInfo };
				// �ּ�ȷ�����
				fjqdje = new String[] { noInfo };

				if ("ȷ��ֵ".equalsIgnoreCase(jelx)) {
					// ȷ�����
					String nofjje = model.getNofjje();
					fjqdje = new String[] { nofjje };
				}
				// �������ֵ
				else {
					// ȷ�����޽��
					String nofjsx = model.getNofjsx();
					fjsxje = new String[] { nofjsx };
					// ȷ�����޽��
					String nofjxx = model.getNofjxx();
					fjxxje = new String[] { nofjxx };

				}
			}
			// �ּ�
			else {
				// ȷ��ֵ
				if ("ȷ��ֵ".equalsIgnoreCase(jelx)) {
					// �ּ�����
					fjmc = model.getQdjemc();
					// �ּ�ȷ�����
					fjqdje = model.getFjqdje();
					if (fjmc != null && fjmc.length > 0) {
						// �ּ����޽��
						fjxxje = new String[fjmc.length];
						// �ּ����޽��
						fjsxje = new String[fjmc.length];

						for (int i = 0; i < fjmc.length; i++) {
							fjxxje[i] = noInfo;
							fjsxje[i] = noInfo;
						}
					}
				}
				// ����
				else {
					// �ּ�����
					fjmc = model.getQjjemc();
					// �ּ����޽��
					fjxxje = model.getFjxxje();
					// �ּ����޽��
					fjsxje = model.getFjsxje();
					if (fjmc != null && fjmc.length > 0) {
						// �ּ�ȷ��
						fjqdje = new String[fjmc.length];

						for (int i = 0; i < fjmc.length; i++) {
							fjqdje[i] = noInfo;
						}
					}
				}
			}
		}
		// ����Ҫ���
		else {
			// ��Ҫ�ּ�
			if ("�ּ�".equalsIgnoreCase(sffj)) {
				// �ּ�����
				fjmc = model.getNojemc();
				if (fjmc != null && fjmc.length > 0) {
					// �ּ�ȷ��
					fjqdje = new String[fjmc.length];
					// �ּ����޽��
					fjxxje = new String[fjmc.length];
					// �ּ����޽��
					fjsxje = new String[fjmc.length];
					for (int i = 0; i < fjmc.length; i++) {
						fjqdje[i] = noInfo;
						fjxxje[i] = noInfo;
						fjsxje[i] = noInfo;
					}
				}
			}
		}

		model.setFjmc(fjmc);
		model.setFjxxje(fjxxje);
		model.setFjsxje(fjsxje);
		model.setFjqdje(fjqdje);
	}

	/**
	 * ������Ŀ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmtjInfo(XszzTyForm model, HashMap<String, String> tjMap)
			throws Exception {

		String[] arrzd = new String[] { "xmtjb", "xmtj", "xmtjz" };
		String[] onezd = new String[] { "xmdm" };

		String tableName = "xszz_xmtjb";

		String pk = "xmdm";

		if (!tjMap.isEmpty()) {

			// ����
			String[] xmtj = new String[tjMap.size()];
			// ����ֵ
			String[] xmtjz = new String[tjMap.size()];
			// �������ڱ�
			String[] xmtjb = new String[tjMap.size()];
			String[] temp_xmtjb = model.getXmtjb();
			int n = 0;

			for (String key : tjMap.keySet()) {

				xmtj[n] = key;
				xmtjz[n] = tjMap.get(key);

				if (temp_xmtjb != null && temp_xmtjb.length > 0) {
					for (int j = 0; j < temp_xmtjb.length; j++) {
						String[] arr_table = temp_xmtjb[j].split("@");
						if (arr_table != null && arr_table.length > 1
								&& key.equalsIgnoreCase(arr_table[0])) {
							xmtjb[n] = arr_table[1];
							break;
						}
					}
				}
				n++;
			}
			model.setXmtjb(xmtjb);
			model.setXmtj(xmtj);
			model.setXmtjz(xmtjz);

		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXmdm() });

		return saveXszz(saveForm, model);
	}

	/**
	 * �����Ŀά����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmwhfo(XszzTyForm model, String tableName) {

		// ��Ŀȫ����Ϣ
		HashMap<String, String> map = new HashMap<String, String>();

		// ��Ŀ�����Ϣ
		HashMap<String, String> xmMap = getXmxgInfo(model);

		if (!xmMap.isEmpty()) {

			map.putAll(xmMap);

			model.setXmdm(map.get("xmdm"));

			// ��Ŀ������Ϣ
			HashMap<String, String> tjMap = getXmtjInfo(model);

			if (!tjMap.isEmpty()) {
				map.putAll(tjMap);
			}

		} else {

			// �������Ϣ������ʼֵ

			// ��Ŀ����
			String xmdm = model.getXmdm();
			// ѧУ����
			String xxdm = Base.xxdm;
			// ��Ŀ��
			String xmb = "xszz_comm_zzwsb";
			// Ĭ����Ŀ
			String mrxm = "��";

			map.put("xmdm", xmdm);
			map.put("xxdm", xxdm);
			map.put("xmb", xmb);
			map.put("mrxm", mrxm);

			map.put("sfje", "����Ҫ");
			map.put("sffj", "���ּ�");
			map.put("jelx", "ȷ��ֵ");
			map.put("kgzt", "��������");
			map.put("shjb", "ѧУ���");
			map.put("sqzq", "������");
		}

		return map;
	}

	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXsSqInfo(XszzTyForm model,
			String tableName) {

		// ����
		String pk = "xh||sqsj";
		// ����ֵ��
		String pkValue = model.getPkValue();
		// ����ֶ�
		String[] colList = new String[] { "xn", "xq", "nd", "sqsj", "xh",
				"bzrsh", "fdysh", "xysh", "xxsh" };

		// ��Ŀ�����Ϣ
		HashMap<String, String> map = (Base.isNull(pkValue)) ? new HashMap<String, String>()
				: getXszzInfo(tableName, pk, pkValue, colList);

		return map;
	}

	/**
	 * �����Ŀ������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmtjInfo(XszzTyForm model) {

		String tableName = "xszz_xmtjb";
		String dm = "xmtj";
		String mc = "xmtjz";
		String pk = "xmdm";
		String pkValue = model.getXmdm();

		// ��Ŀ������Ϣ
		List<HashMap<String, String>> tjList = dao.getWhList(tableName, dm, mc,
				"", pk, pkValue);
		tjList.remove(0);

		HashMap<String, String> map = new HashMap<String, String>();

		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> tj = tjList.get(i);

				String xmtj = tj.get("dm");
				String xmtjz = tj.get("mc");

				map.put("save_" + xmtj, xmtjz);

			}
		}

		return map;
	}

	/**
	 * �޸Ŀ���״̬
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateKgzt(XszzTyForm model) throws Exception {
		return dao.updateKgzt(model);
	}

	/**
	 * �����Ŀ�����б�����Ŀ�رգ�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZzxmList() {
		return dao.getZzxmList();
	}

	/**
	 * ������������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveRssz(XszzTyForm model) throws Exception {

		boolean flag = false;

		// ��˿��Ƽ���
		String kzjb = model.getKzjb();

		// ���䷽ʽ
		String fpfs = model.getFpfs();
		// ��Ŀ�����Ϣ
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		// ����������
		if ("����".equalsIgnoreCase(fpfs)) {
			// ѧԺ
			if ("ѧԺ".equalsIgnoreCase(kzjb)) {
				flag = saveXyBlrs(model, map);
			}
			// רҵ
			else if ("רҵ".equalsIgnoreCase(kzjb)) {
				flag = saveZyBlrs(model, map);
			}
			// �༶
			else if ("�༶".equalsIgnoreCase(kzjb)) {
				flag = saveBjBlrs(model, map);
			}
		}
		// ����������
		else {
			// ѧԺ
			if ("ѧԺ".equalsIgnoreCase(kzjb)) {
				model.setBmjb("xy");
			}
			// רҵ
			else if ("רҵ".equalsIgnoreCase(kzjb)) {
				model.setBmjb("zy");
			}
			// �༶
			else if ("�༶".equalsIgnoreCase(kzjb)) {
				model.setBmjb("bj");
			}
			flag = saveRsszRsfp(model, map);
		}

		return flag;
	}

	/**
	 * ����ѧԺ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXyBlrs(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveXyBlrs(model, map);
	}

	/**
	 * ����רҵ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZyBlrs(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveZyBlrs(model, map);
	}

	/**
	 * ����༶��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveBjBlrs(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveBjBlrs(model, map);
	}

	/**
	 * �����������ã��������䣩
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveRsszRsfp(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveRsszRsfp(model, map);
	}

	/**
	 * ���������Ŀ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getRsszList(XszzTyForm model) {

		// ��˿��Ƽ���
		String kzjb = model.getKzjb();
		// ��Ŀ�����Ϣ
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		// ѧԺ
		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			model.setBmjb("xy");
		}
		// רҵ
		else if ("רҵ".equalsIgnoreCase(kzjb)) {
			model.setBmjb("zy");
		}
		// �༶
		else if ("�༶".equalsIgnoreCase(kzjb)) {
			model.setBmjb("bj");
		}

		ArrayList<String[]> list = dao.getRsszList(model, map);

		return getResultList(list, model);
	}

	/**
	 * �޸���������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateRssz(XszzTyForm model) throws Exception {

		// ��Ŀ�����Ϣ
		HashMap<String, String> map = getXmxgInfo(model);

		// ��˿��Ƽ���
		String kzjb = model.getKzjb();

		// ѧԺ
		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			model.setBmjb("xy");
		}
		// רҵ
		else if ("רҵ".equalsIgnoreCase(kzjb)) {
			model.setBmjb("zy");
		}
		// �༶
		else if ("�༶".equalsIgnoreCase(kzjb)) {
			model.setBmjb("bj");
		}

		return dao.updateRssz(model, map);
	}

	/**
	 * ���������Ŀ�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmrsInfo(XszzTyForm model) {
		// ��Ŀ�����Ϣ
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		return dao.getXmrsInfo(model, map);
	}

	/**
	 * �ж��Ƿ񳬹���������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String isCgRssx(XszzTyForm model) throws Exception {
		// ���沿��
		String[] bmdm = model.getPrimarykey_checkVal();
		// ��������
		String[] szrs = model.getSzrs();
		// �����꼶
		String[] sznj = model.getSznj();
		// ��˿��Ƽ���
		String kzjb = model.getKzjb();
		// /��Ŀ���
		HashMap<String, String> xmrsInfo = getXmrsInfo(model);
		// ��������
		String rssx = xmrsInfo.get("rssx");
		// �ѷ����������
		List<HashMap<String, String>> yfpList = getYfprsList(model);

		// ������
		float sumRs = 0;

		if (yfpList != null && yfpList.size() > 0) {

			for (int i = 0; i < yfpList.size(); i++) {
				HashMap<String, String> map = yfpList.get(i);
				// ������������
				String rs = map.get("szrs");
				// ���ż���+�꼶+����
				String dm = map.get("bmjb") + map.get("nj") + map.get("bmdm");

				if (bmdm != null && bmdm.length > 0) {
					for (int j = 0; j < bmdm.length; j++) {
						// ���ż���+�꼶
						String jbnj = "";
						// ѧԺ
						if ("ѧԺ".equalsIgnoreCase(kzjb)) {
							jbnj = "xyȫ��";
						}
						// רҵ
						else if ("רҵ".equalsIgnoreCase(kzjb)) {
							jbnj = "zy" + sznj[j];
						}
						// �༶
						else {
							jbnj = "bj" + sznj[j];
						}
						if (dm.equalsIgnoreCase(jbnj + bmdm[j])) {
							rs = szrs[j];
							break;
						}
					}
				}
				sumRs += Float.parseFloat(rs);
			}
		} else {
			for (int j = 0; j < bmdm.length; j++) {
				sumRs += Float.parseFloat(szrs[j]);
			}
		}

		String message = "";

		if (!Base.isNull(rssx) && sumRs > Float.parseFloat(rssx)) {
			message = "����Ŀ��������Ϊ" + rssx + "�ˣ�\n������������" + sumRs
					+ "��,\n�ѳ������ޣ���ȷ�ϣ�";
		}
		return message;
	}

	/**
	 * ����ѷ�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYfprsList(XszzTyForm model)
			throws Exception {
		// ��Ŀ�����Ϣ
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		return dao.getYfprsList(model, map);
	}

	/**
	 * �����Ŀ�����б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXmsqList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// TODO
		XsxxglService stuService = new XsxxglService();

		// ѧ��
		String xh = model.getXh();

		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);

		// ������Ŀ�б�
		List<HashMap<String, String>> allXmList = getAllXmList(model);

		// ���ѧ������ѧԺ��רҵ�༶�������ͨ��ѧ���б�
		ArrayList<String[]> xmYtgList = getXmYtgList(allXmList, stuInfo, model);

		// �����Ŀ���������б�
		List<HashMap<String, String>> xmRsszList = getXmRsszList(allXmList,
				model);
		// �����Ŀ�����б�
		List<HashMap<String, String>> xmTjList = getXmTjList(model);

		// ѧ����������Ŀ�б�
		List<HashMap<String, String>> xsYsqXmList = getXsYsqXmList(allXmList,
				model);

		// �Ƴ���Ҫ�������ƶ���������δ���ã�����Ϊ0������Ŀ
		List<HashMap<String, String>> xqxmlist = removeRsszList(model, stuInfo,
				allXmList, xmRsszList);

		// �Ƴ���Ŀ�ﵽ���޵���Ŀ
		xqxmlist = removeXmSxList(model, xmYtgList, xqxmlist);

		// �Ƴ��������������õ���Ŀ
		xqxmlist = removeBfhTjXm(model, xmTjList, xqxmlist);

		// ������Ŀ������Ϣ
		xqxmlist = setXmOtherList(xqxmlist);

		// ������Ŀ��������Ϣ
		xqxmlist = setYsqXm(model, xsYsqXmList, xqxmlist);

		
		if (Globals.XXDM_TJGYDX.equals(Base.xxdm)){
			setXmDisabled(xqxmlist);
		}
		
		
		
		return getResultList(xqxmlist, model);
	}

	
	private void setXmDisabled(List<HashMap<String, String>> xqxmList){
		
		boolean sfsq = false;
		
		for (int i = 0 ; i < xqxmList.size() ; i++){
			
			HashMap<String,String> map = xqxmList.get(i);
			
			if (map.get("xmdm").equals("5001")){
				
				if (!"yes".equals(map.get("ysq"))){
					sfsq = true;
				}
				
				break;
			} else {
				continue;
			}
		}
		
		for (int i = 0 ; i < xqxmList.size() ; i++){
			xqxmList.get(i).put("disabled", String.valueOf(sfsq));
		}
	}
	
	
	
	/**
	 * ������Ŀ������Ϣ
	 * 
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> setXmOtherList(
			List<HashMap<String, String>> xqxmlist) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			for (int i = 0; i < xqxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xqxmlist.get(i);

				// ��������
				String rskz = xmInfo.get("rskz");
				// ���Ƽ���
				String kzjb = xmInfo.get("kzjb");
				// ��������
				String szrs = xmInfo.get("szrs");
				szrs = Base.isNull(szrs) ? "0" : szrs;
				// ��ͨ������
				String rs = xmInfo.get("rs");
				rs = Base.isNull(rs) ? "0" : rs;

				String syrs = "0";

				if ("��Ҫ".equalsIgnoreCase(rskz)) {
					syrs = String.valueOf(Integer.parseInt(szrs)
							- Integer.parseInt(rs));
				} else {
					szrs = "����������";
					szrs = "����������";
					syrs = "����������";
					kzjb = "�޼������";
				}
				xmInfo.put("szrs", szrs);
				xmInfo.put("syrs", syrs);
				xmInfo.put("kzjb", kzjb);

				list.add(xmInfo);
			}
		}

		return list;
	}

	/**
	 * ������Ŀ��������Ϣ
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> setYsqXm(XszzTyForm model,
			List<HashMap<String, String>> xsYsqXmList,
			List<HashMap<String, String>> xqxmlist) {

		// ��ǰѧ��
		String xn = model.getXn();
		// ��ǰѧ��
		String xq = model.getXq();
		// ��ǰ���
		String nd = model.getNd();
		// ����ʱ��
		String nowsj = model.getSqsj();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			for (int i = 0; i < xqxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xqxmlist.get(i);
				// ��Ŀ����
				String xmdm = xmInfo.get("xmdm");
				// ��������
				String sqzq = xmInfo.get("sqzq");
				// ��˼���
				String shjb = xmInfo.get("shjb");
				// ������
				String ysq = "no";
				// �����
				String ysh = "no";
				// ��������Ŀ����ʱ��
				String ysqsj = "";
				// ������
				String shqk = !"�������".equalsIgnoreCase(shjb) ? "��δ���" : "�������";
				// ���״̬
				String shzt = "";
				// ����ʱ��
				String pdsj = xmInfo.get("pdsj");
				// ����ʱ��Ϊǰ��
				if ("ǰ��".equalsIgnoreCase(pdsj)) {
					HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq,
							pdsj, model);
					xn = befInfo.get("xn");
					xq = befInfo.get("xq");
					nd = befInfo.get("nd");
				}
				// --------2010.9.27 lr--------
				else {
					// ��ǰѧ��
					xn = model.getXn();
					// ��ǰѧ��
					xq = model.getXq();
					// ��ǰ���
					nd = model.getNd();
				}
				// -----------end 2010.9.27 lr------
				if (xsYsqXmList != null && xsYsqXmList.size() > 0) {

					for (int j = 0; j < xsYsqXmList.size(); j++) {

						HashMap<String, String> ysqInfo = xsYsqXmList.get(j);

						// ������Ŀ
						String sqxm = ysqInfo.get("xmdm");
						// ����ѧ��
						String sqxn = ysqInfo.get("xn");
						// ����ѧ��
						String sqxq = ysqInfo.get("xq");
						// �������
						String sqnd = ysqInfo.get("nd");
						// ����ʱ��
						String sqsj = ysqInfo.get("sqsj");
						// ���������
						String bzrsh = ysqInfo.get("bzrsh");
						// ����Ա���
						String fdysh = ysqInfo.get("fdysh");
						// ѧԺ���
						String xysh = ysqInfo.get("xysh");
						// ѧУ���
						String xxsh = ysqInfo.get("xxsh");

						shzt = ysqInfo.get("shzt");

						// ��Ŀ����
						if (xmdm.equalsIgnoreCase(sqxm)) {
							// �������ڷ��������״̬Ϊͨ��
							if ((("ѧ��".equalsIgnoreCase(sqzq) && xn
									.equalsIgnoreCase(sqxn))
									|| ("���".equalsIgnoreCase(sqzq) && nd
											.equalsIgnoreCase(sqnd)) || ("ѧ��"
									.equalsIgnoreCase(sqzq)
									&& xn.equalsIgnoreCase(sqxn) && xq
									.equalsIgnoreCase(sqxq)))
									|| ("������".equalsIgnoreCase(sqzq) && nowsj
											.equalsIgnoreCase(sqsj))
									|| ("��һ��".equalsIgnoreCase(sqzq))) {

								ysq = "yes";
								ysqsj = sqsj;

								if (!"�������".equalsIgnoreCase(shjb)) {
									if (!Base.isNull(bzrsh)
											&& !"δ���".equalsIgnoreCase(bzrsh)
											|| !Base.isNull(fdysh)
											&& !"δ���".equalsIgnoreCase(fdysh)
											|| !Base.isNull(xysh)
											&& !"δ���".equalsIgnoreCase(xysh)
											|| !Base.isNull(xxsh)
											&& !"δ���".equalsIgnoreCase(xxsh)) {

										ysh = "yes";

										shqk = (!Base.isNull(bzrsh) && !"δ���"
												.equalsIgnoreCase(bzrsh)) ? "���������"
												+ bzrsh
												: shqk;
										shqk = (!Base.isNull(fdysh) && !"δ���"
												.equalsIgnoreCase(fdysh)) ? "����Ա���"
												+ fdysh
												: shqk;
										shqk = (!Base.isNull(xysh) && !"δ���"
												.equalsIgnoreCase(xysh)) ? "Ժϵ���"
												+ xysh
												: shqk;
										shqk = (!Base.isNull(xxsh) && !"δ���"
												.equalsIgnoreCase(xxsh)) ? "ѧУ���"
												+ xxsh
												: shqk;
									}
								} else {
									shqk = "�������";
								}
							}
						}
					}
				}

				xmInfo.put("ysq", ysq);
				xmInfo.put("ysh", ysh);
				xmInfo.put("shqk", shqk);
				xmInfo.put("shzt", shzt);
				xmInfo.put("ysqsj", ysqsj);
				list.add(xmInfo);

			}
		}

		return list;
	}

	/**
	 * �Ƴ��������������õ���Ŀ
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> removeBfhTjXm(XszzTyForm model,
			List<HashMap<String, String>> xmTjList,
			List<HashMap<String, String>> xqxmlist) {

		// ѧ��
		String xh = model.getXh();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			for (int i = 0; i < xqxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xqxmlist.get(i);

				// ��Ŀ����
				String xmdm = xmInfo.get("xmdm");

				boolean flag = true;

				if (xmTjList != null && xmTjList.size() > 0) {

					for (int j = 0; j < xmTjList.size(); j++) {

						HashMap<String, String> tjInfo = xmTjList.get(j);

						// ������Ŀ
						String tjxm = tjInfo.get("xmdm");
						// ��Ŀ������
						String xmtjb = tjInfo.get("xmtjb");
						// ��Ŀ����
						String xmtj = tjInfo.get("xmtj");
						// ����ֵ
						String xmtjz = tjInfo.get("xmtjz");

						// ��Ŀ����
						if (tjxm.equalsIgnoreCase(xmdm)) {
							if("0014".equalsIgnoreCase(xmdm)){
								int a=0;
								a++;
							}
							if (!"������".equalsIgnoreCase(xmtjz)) {
								flag = dao.isFhXmTj(xh, xmtjb, xmtj, xmtjz,
										xmdm);

								if (!flag) {
									break;
								}
							}
						}

					}
				}
				if (flag) {
					list.add(xmInfo);
				}
			}
		}

		return list;
	}

	/**
	 * �Ƴ���Ŀ�ﵽ���޵���Ŀ
	 * 
	 * @param model
	 * @param xh
	 * @param xmYtgList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> removeXmSxList(XszzTyForm model,
			ArrayList<String[]> xmYtgList,
			List<HashMap<String, String>> xqxmlist) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			// ѧ��
			String xh = model.getXh();
			// ��ǰѧ��
			String xn = model.getXn();
			// ��ǰѧ��
			String xq = model.getXq();
			// ��ǰ���
			String nd = model.getNd();

			for (int i = 0; i < xqxmlist.size(); i++) {

				boolean flag = true;

				HashMap<String, String> xmInfo = xqxmlist.get(i);

				// ��Ŀ����
				String xmdm = xmInfo.get("xmdm");
				// ��������
				String sqzq = xmInfo.get("sqzq");
				// ���������Ƿ���Ҫ
				String rskz = xmInfo.get("rskz");
				// ��˼���
				String shjb = xmInfo.get("shjb");
				// ����ʱ��
				String pdsj = xmInfo.get("pdsj");
				// ����ʱ��Ϊǰ��
				if ("ǰ��".equalsIgnoreCase(pdsj)) {
					HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq,
							pdsj, model);
					xn = befInfo.get("xn");
					xq = befInfo.get("xq");
					nd = befInfo.get("nd");
				}

				// ��������
				String szrs = xmInfo.get("szrs");
				int int_szrs = Base.isNull(szrs) ? 0 : Integer.parseInt(szrs);

				if ("��Ҫ".equalsIgnoreCase(rskz) && xmYtgList != null
						&& xmYtgList.size() > 0) {

					// ��ͨ������
					int rs = 0;

					for (int j = 0; j < xmYtgList.size(); j++) {

						String[] ytgInfo = xmYtgList.get(j);
						// ����ѧ��
						String sqxn = ytgInfo[0];// ytgInfo.get("xn");
						// ����ѧ��
						String sqxq = ytgInfo[1];// ytgInfo.get("xq");
						// �������
						String sqnd = ytgInfo[2];// ytgInfo.get("nd");
						// ������Ŀ
						String sqxm = ytgInfo[3];// ytgInfo.get("xmdm");
						// ��Ŀͨ������
						String tgrs = ytgInfo[4];// ytgInfo.get("xmdm");
						// ����ѧ��
						// String sqxh = ytgInfo[4];//ytgInfo.get("xh");
						// ���״̬
						String shzd = "";
						// if("һ�����".equalsIgnoreCase(shjb)){
						// shzd = "shzt1";
						// }else if("�������".equalsIgnoreCase(shjb)){
						// shzd = "shzt2";
						// }else if("�������".equalsIgnoreCase(shjb)){
						// shzd = "shzt3";
						// }

						// String shzt = ytgInfo.get(shzd);

						// ��Ŀ����
						if (xmdm.equalsIgnoreCase(sqxm)) {
							// �������ڷ���
							if ((("ѧ��".equalsIgnoreCase(sqzq) && xn
									.equalsIgnoreCase(sqxn))
									|| ("���".equalsIgnoreCase(sqzq) && nd
											.equalsIgnoreCase(sqnd)) || ("ѧ��"
									.equalsIgnoreCase(sqzq)
									&& xn.equalsIgnoreCase(sqxn) && xq
									.equalsIgnoreCase(sqxq))) ) {
								rs = Integer.parseInt(tgrs);
							}
						}
					}
					// �����Ƿ�ﵽ����
					if (rs >= int_szrs) {
						flag = false;
					}

					xmInfo.put("rs", String.valueOf(rs));
				}

				if (flag) {
					list.add(xmInfo);
				}
			}
		}

		return list;
	}

	/**
	 * �Ƴ���Ҫ�������ƶ���������δ���ã�����Ϊ0������Ŀ
	 * 
	 * @author luojw
	 * @param model
	 * @param stuInfo
	 * @param allXmList
	 * @param xmRsszList
	 * @return
	 */
	private List<HashMap<String, String>> removeRsszList(XszzTyForm model,
			HashMap<String, String> stuInfo,
			List<HashMap<String, String>> allXmList,
			List<HashMap<String, String>> xmRsszList) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (allXmList != null && allXmList.size() > 0) {

			// ��ǰѧ��
			String xn = model.getXn();
			// ��ǰѧ��
			String xq = model.getXq();
			// ��ǰ���
			String nd = model.getNd();
			// ѧԺ
			String xydm = stuInfo.get("xydm");
			// �꼶
			String nj = stuInfo.get("nj");
			// רҵ
			String zydm = stuInfo.get("zydm");
			// �༶
			String bjdm = stuInfo.get("bjdm");

			for (int i = 0; i < allXmList.size(); i++) {

				boolean flag = true;

				HashMap<String, String> xmInfo = allXmList.get(i);
				// ��Ŀ����
				String xmdm = xmInfo.get("xmdm");
				// ��������
				String sqzq = xmInfo.get("sqzq");
				// ���������Ƿ���Ҫ
				String rskz = xmInfo.get("rskz");
				// ����ʱ��
				String pdsj = xmInfo.get("pdsj");
				// ����ʱ��Ϊǰ��
				if ("ǰ��".equalsIgnoreCase(pdsj)) {
					HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq,
							pdsj, model);
					xn = befInfo.get("xn");
					xq = befInfo.get("xq");
					nd = befInfo.get("nd");
				}
				if ("��Ҫ".equalsIgnoreCase(rskz) && xmRsszList != null
						&& xmRsszList.size() > 0) {

					for (int j = 0; j < xmRsszList.size(); j++) {

						HashMap<String, String> rsszInfo = xmRsszList.get(j);

						// ����ѧ��
						String szxn = rsszInfo.get("xn");
						// ����ѧ��
						String szxq = rsszInfo.get("xq");
						// �������
						String sznd = rsszInfo.get("nd");
						// ��Ŀ����
						String szxm = rsszInfo.get("xmdm");
						// ���ż���
						String bmjb = rsszInfo.get("bmjb");
						// �����꼶
						String sznj = rsszInfo.get("nj");
						// ���Ŵ���
						String bmdm = rsszInfo.get("bmdm");
						// ��������
						String szrs = rsszInfo.get("szrs");

						// ��Ŀ����
						if (xmdm.equalsIgnoreCase(szxm)) {
							// ���ŷ���,�������ڷ���
							if ((("xy".equalsIgnoreCase(bmjb) && xydm
									.equalsIgnoreCase(bmdm))
									|| ("zy".equalsIgnoreCase(bmjb)
											&& zydm.equalsIgnoreCase(bmdm) && nj
											.equalsIgnoreCase(sznj)) || ("bj"
									.equalsIgnoreCase(bmjb) && bjdm
									.equalsIgnoreCase(bmdm)))
									&& ((("ѧ��".equalsIgnoreCase(sqzq) && xn
											.equalsIgnoreCase(szxn))
											|| ("���".equalsIgnoreCase(sqzq) && nd
													.equalsIgnoreCase(sznd)) || ("ѧ��"
											.equalsIgnoreCase(sqzq)
											&& xn.equalsIgnoreCase(szxn) && xq
											.equalsIgnoreCase(szxq)))
									|| ("������".equalsIgnoreCase(sqzq)))) {
								if (Base.isNull(szrs)
										|| "0".equalsIgnoreCase(szrs)) {
									flag = false;
								} else {
									xmInfo.put("szrs", szrs);
									flag = true;
									break;
								}
							} else {
								flag = false;
							}
						}
					}
				}

				if ("��Ҫ".equalsIgnoreCase(rskz)
						&& !(xmRsszList != null && xmRsszList.size() > 0)) {
					flag = false;
				}

				if (flag) {
					list.add(xmInfo);
				}
			}
		}
		return list;
	}

	/**
	 * ���������Ŀ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getAllXmList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xszz_comm_xmwh";

		String[] colList = new String[] { "pk", "xmdm", "xmmc", "sqzq", "xmsm",
				"xmb", "rskz", "kzjb", "shjb", "bzrsh", "fdysh", "xysh",
				"xxsh", "rssx", "xmlb", "pdsj" };
		String[] queryList = new String[] { "kgzt", "xmdm", "xmlb" };
		String[] queryLikeList = new String[] {};

		model.setKgzt("��������");

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(getFlowControlSql(model));//����������ģ�����
		
		List<HashMap<String, String>> allXmList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		return allXmList;
	}

	/**
	 * ���ѧ����������Ŀ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsYsqXmList(
			List<HashMap<String, String>> allXmList, XszzTyForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (allXmList != null && allXmList.size() > 0) {
			// ������Ŀ��
			String[] xmb = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				HashMap<String, String> xmInfo = allXmList.get(i);
				xmb[i] = xmInfo.get("xmb");
			}

			list = dao.getXsYsqXmList(model, xmb);
		}

		return list;
	}

	/**
	 * �����Ŀ���������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmRsszList(
			List<HashMap<String, String>> allXmList, XszzTyForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (allXmList != null && allXmList.size() > 0) {
			// ������Ŀ����
			String[] xmdm = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				HashMap<String, String> xmInfo = allXmList.get(i);
				xmdm[i] = xmInfo.get("xmdm");
			}

			list = dao.getXmRsszList(model, xmdm);
		}

		return list;
	}

	/**
	 * ���ѧ������ѧԺ��רҵ�༶�������ͨ��ѧ���б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getXmYtgList(
			List<HashMap<String, String>> allXmList,
			HashMap<String, String> stuInfo, XszzTyForm model) {

		ArrayList<String[]> list = new ArrayList<String[]>();

		if (allXmList != null && allXmList.size() > 0) {
			// ������Ŀ����
			String[] xmdm = new String[allXmList.size()];
			// ������Ŀ��
			String[] xmb = new String[allXmList.size()];
			// ������Ŀ�Ŀ��Ƽ���
			String[] kzjb = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				HashMap<String, String> xmInfo = allXmList.get(i);
				xmdm[i] = xmInfo.get("xmdm");
				xmb[i] = xmInfo.get("xmb");
				kzjb[i] = xmInfo.get("kzjb");
			}

			list = dao.getXmYtgList(model, stuInfo, xmb, xmdm, kzjb);
		}

		return list;
	}

	/**
	 * �����Ŀ�����б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmTjList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "xszz_xmtjb";

		String[] colList = new String[] { "xmdm", "xmtjb", "xmtj", "xmtjz" };
		String[] queryList = new String[] {};
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by xmdm");

		// ��Ŀ����
		List<HashMap<String, String>> xmTjList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		return xmTjList;
	}

	/**
	 * �����Ŀ���������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getXmSqNrList(XszzTyForm model)
			throws Exception {
		// TODO
		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ��
		String xmb = model.getXmb();

		// �ֶ��б�
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		// �ֶ��б�
		List<HashMap<String, String>> zdList = setXmzdList(model);

		// ����ѧ��������Ϣ
		setXsInfo(model, resultList, zdList);

		// ���ü�ͥ�������
		setJtjbInfo(model, resultList, zdList);

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)
				|| Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {// ����������
			setZcfInfo(model, resultList, zdList);
		}

		// ������Ϣ
		setKnInfo(model, resultList, zdList);

//		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)||Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {// �ش�
			// ����������Ϣ
			setQtInfo(model, resultList, zdList);
//		}

		if (!"jtqkdcb".equalsIgnoreCase(xmb)) {
			// ������Ŀ�������
			setXmzdInfo(model, resultList, zdList);
		}

		// // �������
		// setFileInfo(model, resultList, zdList);

		return resultList;
	}

	/**
	 * ������Ŀչʾ�ֶ�
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> setXmzdList(XszzTyForm model)
			throws Exception {

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �ֶ��б�
		List<HashMap<String, String>> zdList = null;

		// ��ͥ���
		if (XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_JTQK_LIST;
			}
			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_JTQK_LIST = zdList;
			}
		}
		// ������
		else if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_KNS_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_KNS_LIST = zdList;
			}
		}
		// ������ѧ����
		else if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJZXDK_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJZXDK_LIST = zdList;
			}
		}
		// ���ҽ�ѧ��
		else if (XszzXmdm.XSZZ_GJJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJJXJ_LIST = zdList;
			}
		}
		// ������־��ѧ��
		else if (XszzXmdm.XSZZ_GJLZJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJLZJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJLZJXJ_LIST = zdList;
			}
		}
		// ������ѧ��
		else if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJZXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJZXJ_LIST = zdList;
			}
		}
		// ������ѧ��һ�ȣ�
		else if (XszzXmdm.XSZZ_GJZXJ_LV1.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZXJLV1_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZXJLV1_LIST = zdList;
			}
		}
		// ������ѧ�𣨶��ȣ�
		else if (XszzXmdm.XSZZ_GJZXJ_LV2.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZXJLV2_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZXJLV2_LIST = zdList;
			}
		}
		// ������ѧ�����ȣ�
		else if (XszzXmdm.XSZZ_GJZXJ_LV3.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZXJLV3_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZXJLV3_LIST = zdList;
			}
		}
		// ѧ������ѧ��
		else if (XszzXmdm.XSZZ_XFHJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_XFHJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_XFHJ_LIST = zdList;
			}
		}
		// ��ɫͨ��
		else if (XszzXmdm.XSZZ_LSTD.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_LSTD_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_LSTD_LIST = zdList;
			}
		}
		// У����ѧ���
		else if (XszzXmdm.XSZZ_XNZXJK.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_XNZXJK_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_XNZXJK_LIST = zdList;
			}
		}
		// ��ʱ���Ѳ���
		else if (XszzXmdm.XSZZ_LSKNBZ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_LSKNBZ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_LSKNBZ_LIST = zdList;
			}
		}
		// ѧ�Ѽ���
		else if (XszzXmdm.XSZZ_XFJM.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_XFJM_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_XFJM_LIST = zdList;
			}
		}
		// ����ѧ�����������Ϣ
		else if (XszzXmdm.XSZZ_QTXX.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_QTXX_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_QTXX_LIST = zdList;
			}
		}
		// ���ϴ�ѧ�ο���ѧ��
		else if (XszzXmdm.XSZZ_HKJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_HKJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_HKJXJ_LIST = zdList;
			}
		}
		// ���ϴ�ѧ������չ����
		else if (XszzXmdm.XSZZ_JXFZJJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_JXFZJJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_JXFZJJ_LIST = zdList;
			}
		}
		// ���ϴ�ѧ�к�����
		else if (XszzXmdm.XSZZ_ZHYD.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZHYD_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZHYD_LIST = zdList;
			}
		}
		// ����ʡ��У����ƶ������ѧ��
		else if (XszzXmdm.XSZZ_HNSGXYXPKSJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_HNSGXYXPKSJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_HNSGXYXPKSJXJ_LIST = zdList;
			}
		}
		// ��ά˹��ѧ��
		else if (XszzXmdm.XSZZ_ZWSZXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZWSZXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZWSZXJ_LIST = zdList;
			}
		}

		if (zdList == null || zdList.size() == 0) {
			zdList = getXmZdList(model);
		}

		return zdList;
	}

	/**
	 * �ۺ����ʲ���
	 * 
	 * @param model
	 * @param resultList
	 * @param zdList
	 */
	private void setZcfInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) {
		GuizhdxDAO dao = new GuizhdxDAO();
		String xxdm = Base.xxdm;// ѧУ����
		// ���ѧ���۲���Ϣ
		HashMap<String, String> zcMap = new HashMap<String, String>();
		String mk = "zcf";
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			// ���ϴ�ѧ
			zcMap = getXsZhcpInfo(model);// ��ȡ�۲������Ϣ
		} else if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			// ����ѧԺ
			zcMap.put("xh", model.getXh());
			zcMap.put("xn", Base.currXn);

			List<HashMap<String, String>> zcfList = dao.getZcf(zcMap);
			String zyrs = dao.getZyrs(model.getXh());

			zcMap.put("zyzrs", zyrs);// ͬ�꼶��רҵ������
			zcMap.put("cjpm", getCjpm(model.getXh()));// ����ɼ�����
			if (!"0".equals(zyrs)) {
				zcMap.put("cjpmbl", String.valueOf(Math.rint(Double
						.parseDouble(getCjpm(model.getXh()))
						/ Double.parseDouble(zyrs)))
						+ "%");
				for (HashMap<String, String> temp : zcfList) {
					if ("�ܷ�".equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("zjfpm", temp.get("pm"));
						zcMap.put("zjfpmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					} else if (getZcfTitle()[0].equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("dypmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					} else if (getZcfTitle()[1].equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("zypmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					} else if (getZcfTitle()[2].equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("wtpmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					}
				}
			} else {
				zcMap.put("cjpmbl", "0.0%");
				zcMap.put("zjfpm", "0");
				zcMap.put("zjfpmBl", "0.0%");
				zcMap.put("dypmBl", "0.0%");
				zcMap.put("wtpmBl", "0.0%");
			}
		}

		// ѧ�������Ϣ�б�
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();
		// ռһ����Ԫ����ֶ�
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// ��Դ��
				String lyb = map.get("lyb");
				// �ֶ�
				String zd = map.get("zd");
				// �ֶ�����
				String zdlx = map.get("zdlx");

				// ѧ����Ϣչ������
				if (mk.equalsIgnoreCase(lyb)) {

					// �ֶ�ֵ
					String zdz = zcMap.get(zd);

					map.put("zdz", zdz);

					// һ����Ԫ��
					if ("short".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}
					// �����Ԫ��
					else if ("long".equalsIgnoreCase(zdlx)) {
						longList.add(map);
					}
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// ������һ���ֶε�λ��
		if (shortList != null && shortList.size() > 0) {

			shortList.get(shortList.size() - 1).put("lastZd", "yes");

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		if ((shortList != null && shortList.size() > 0)
				|| (longList != null && longList.size() > 0)) {
			resultList.add(map);
		}
	}

	/**
	 * ��������Ϣ
	 * 
	 * @param model
	 * @param resultList
	 * @param zdList
	 */
	private void setKnInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) {
		// ���ѧ��������Ϣ
		HashMap<String, String> knMap = new HashMap<String, String>();
		String mk = "xszz_knsb";
		knMap = getXsKnInfo(model);// ��ȡ������Ϣ

		// ѧ�������Ϣ�б�
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();
		// ռһ����Ԫ����ֶ�
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// ��Դ��
				String lyb = map.get("lyb");
				// �ֶ�
				String zd = map.get("zd");
				// �ֶ�����
				String zdlx = map.get("zdlx");

				// ѧ����Ϣչ������
				if (mk.equalsIgnoreCase(lyb)) {

					// �ֶ�ֵ
					String zdz = knMap.get(zd);

					map.put("zdz", zdz);

					// һ����Ԫ��
					if ("short".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}
					// �����Ԫ��
					else if ("long".equalsIgnoreCase(zdlx)) {
						longList.add(map);
					}
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// ������һ���ֶε�λ��
		if (shortList != null && shortList.size() > 0) {

			shortList.get(shortList.size() - 1).put("lastZd", "yes");

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		if ((shortList != null && shortList.size() > 0)
				|| (longList != null && longList.size() > 0)) {
			resultList.add(map);
		}
	}

	/**
	 * ����ѧ��������Ϣ
	 * 
	 * @author luojw
	 * @param zdList
	 */
	private void setXsInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) {

		XsxxglService stuService = new XsxxglService();

		// ģ��
		String mk = "view_xsjbxx";
		// ѧ��
		String xh = model.getXh();
		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// ѧ����ͥ��Ϣ
		HashMap<String, String> jtInfo = stuService.getStuJtxx(xh);
		// ���ѧ��ס����Ϣ
		HashMap<String, String> zsInfo = getXsZxInfo(model);

		// ѧ�������Ϣ�б�
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();

		// ռһ����Ԫ����ֶ�
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList == null || zdList.size() == 0) {
			model.setXmdm("��");
			try {
				zdList = getXmZdList(model);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < zdList.size(); i++) {

			HashMap<String, String> map = zdList.get(i);

			// ��Դ��
			String lyb = map.get("lyb");
			// �ֶ�
			String zd = map.get("zd");
			// �ֶ�����
			String zdlx = map.get("zdlx");

			// ѧ����Ϣչ������
			if (mk.equalsIgnoreCase(lyb)) {

				// �ֶ�ֵ
				String zdz = stuInfo.get(zd);
				zdz = Base.isNull(zdz) ? jtInfo.get(zd) : zdz;
				zdz = Base.isNull(zdz) ? zsInfo.get(zd) : zdz;

				map.put("zdz", zdz);

				// һ����Ԫ��
				if ("short".equalsIgnoreCase(zdlx)) {
					shortList.add(map);
				}
				// �����Ԫ��
				else if ("long".equalsIgnoreCase(zdlx)) {
					longList.add(map);
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// ������һ���ֶε�λ��
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		resultList.add(map);
	}

	/**
	 * ������Ŀ�ֶ���Ϣ
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	private void setXmzdInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// ģ��
		String mk = "xmxgInfo";
		// ��Ŀ��Ϣ
		HashMap<String, String> wsInfo = setXmzdInfo(model);

		// ��ͥ�����Ϣ�б�
		List<HashMap<String, Object>> wsInfolist = new ArrayList<HashMap<String, Object>>();

		// ռһ����Ԫ����ֶ�
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();

		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		// ����
		List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();

		String xmdm = model.getXmdm();

		boolean flag = true;

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// ��Դ��
				String lyb = map.get("lyb");
				// �ֶ�
				String zd = map.get("zd");
				// �ֶ�����
				String zdlx = map.get("zdlx");

				// ��ͥ������Ϣչ������
				if (!"jtqkdcb".equalsIgnoreCase(lyb)
						&& !"view_xsjbxx".equalsIgnoreCase(lyb)
						&& !"zcf".equals(lyb)
						&& !"zzxb".equals(lyb)
						&& !"xsqtxxb".equals(lyb)
						&& (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm) || !"xszz_knsb"
								.equals(lyb))) {

					// �ֶ�ֵ
					String zdz = wsInfo.get(zd);

					map.put("zdz", zdz);
					map.put("kjlx", zdlx);

					// һ����Ԫ��
					if ("text".equalsIgnoreCase(zdlx)
							|| "select".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}// ����
					else if ("file".equalsIgnoreCase(zdlx) && flag) {
						map = new HashMap<String, String>();
						map.put("filename", wsInfo.get("filename"));
						map.put("filepath", wsInfo.get("filepath"));
						map.put("zdlx", "file");
						fileList.add(map);
						flag = false;
					}
					// �����Ԫ��
					else if ("textarea".equalsIgnoreCase(zdlx)
							|| "long".equalsIgnoreCase(zdlx)) {
						longList.add(map);
					}
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("zdlx", "long");
		map.put("zdList", longList);
		wsInfolist.add(map);

		map = new HashMap<String, Object>();
		map.put("zdlx", "file");
		map.put("zdList", fileList);
		wsInfolist.add(map);

		// ������һ���ֶε�λ��
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			wsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", wsInfolist);

		resultList.add(map);
	}

	/**
	 * �㽭�Ƽ���ѧ����
	 * 
	 * @param model
	 * @param resultList
	 * @param zdList
	 * @throws Exception
	 */
	private void setZxdkInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// ģ��
		String mk = "zzxb";
		HashMap<String, String> zzxbMap = dao.getZxdkInfo(model);
		// ѧ�������Ϣ�б�
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();
		// ռһ����Ԫ����ֶ�
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// ��Դ��
				String lyb = map.get("lyb");
				// �ֶ�
				String zd = map.get("zd");
				// �ֶ�����
				String zdlx = map.get("zdlx");

				if (mk.equalsIgnoreCase(lyb)) {

					// �ֶ�ֵ
					String zdz = zzxbMap.get(zd);
					map.put("zdz", zdz);
					map.put("kjlx", "text");
					// һ����Ԫ��
					if ("text".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// ������һ���ֶε�λ��
		if (shortList != null && shortList.size() > 0) {

			shortList.get(shortList.size() - 1).put("lastZd", "yes");

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		if ((shortList != null && shortList.size() > 0)
				|| (longList != null && longList.size() > 0)) {
			resultList.add(map);
		}
	}

	/**
	 * ���ü�ͥ�������
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	private void setJtjbInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// ģ��
//		String mk = "xszz_knsb";
		String mk = "jtqkdcb";

		// ��ͥ��Ϣ
		HashMap<String, String> jtInfo = getJtqkInfo(model);

		// ��ͥ�����Ϣ�б�
		List<HashMap<String, Object>> jtInfolist = new ArrayList<HashMap<String, Object>>();

		// ռһ����Ԫ����ֶ�
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();

		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList == null || zdList.size() == 0) {
			model.setXmdm("��");
			try {
				zdList = getXmZdList(model);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < zdList.size(); i++) {

			HashMap<String, String> map = zdList.get(i);

			// ��Դ��
			String lyb = map.get("lyb");
			// �ֶ�
			String zd = map.get("zd");
			// �ֶ�����
			String zdlx = map.get("zdlx");

			// ��ͥ������Ϣչ������
			if (mk.equalsIgnoreCase(lyb)) {

				// �ֶ�ֵ
				String zdz = jtInfo.get(zd);

				map.put("zdz", zdz);
				map.put("kjlx", zdlx);

				// һ����Ԫ��
				if ("text".equalsIgnoreCase(zdlx)
						|| "select".equalsIgnoreCase(zdlx)) {
					shortList.add(map);
				}
				// �����Ԫ��
				else if ("textarea".equalsIgnoreCase(zdlx)
						|| "long".equalsIgnoreCase(zdlx)
						|| "file".equalsIgnoreCase(zdlx)) {
					longList.add(map);
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (longList != null && longList.size() > 0) {
			map.put("zdlx", "long");
			map.put("zdList", longList);
			jtInfolist.add(map);
		}

		// ������һ���ֶε�λ��
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			jtInfolist.add(map);
		}

		if (jtInfolist != null && jtInfolist.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", jtInfolist);
		}

		resultList.add(map);
	}

	/**
	 * ������Ŀ�������
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setFileInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// ģ��
		String mk = "file";

		// ��ͥ�����Ϣ�б�
		List<HashMap<String, Object>> fileInfolist = new ArrayList<HashMap<String, Object>>();
		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();

		if (resultList != null && resultList.size() > 0) {

			for (HashMap<String, Object> resMap : resultList) {

				List<HashMap<String, Object>> infoList = (List<HashMap<String, Object>>) resMap
						.get("nrList");

				if (infoList != null && infoList.size() > 0) {

					for (HashMap<String, Object> infoMap : infoList) {

						String zdlx = (String) infoMap.get("zdlx");

						if ("long".equalsIgnoreCase(zdlx)) {

							List<HashMap<String, String>> list = (List<HashMap<String, String>>) infoMap
									.get("zdList");

							if (list != null && list.size() > 0) {

								for (HashMap<String, String> map : list) {

									String lx = map.get("zdlx");

									if ("file".equalsIgnoreCase(lx)) {
										fileList.add(map);
									}
								}
							}
						}
					}
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (fileList != null && fileList.size() > 0) {
			map.put("zdlx", "file");
			map.put("zdList", fileList);
			fileInfolist.add(map);

			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", fileInfolist);

			resultList.add(map);
		}

	}

	/**
	 * ����������Ϣ
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	private void setQtInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// ģ��
		String mk = "xsqtxxb";

		// ��ͥ��Ϣ
		HashMap<String, String> qtInfo = getQtInfo(model);

		// ��ͥ�����Ϣ�б�
		List<HashMap<String, Object>> qtInfolist = new ArrayList<HashMap<String, Object>>();

		// ռһ����Ԫ����ֶ�
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();

		// ռ�����Ԫ����ֶ�
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList == null || zdList.size() == 0) {
			model.setXmdm("��");
			try {
				zdList = getXmZdList(model);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < zdList.size(); i++) {

			HashMap<String, String> map = zdList.get(i);

			// ��Դ��
			String lyb = map.get("lyb");
			// �ֶ�
			String zd = map.get("zd");
			// �ֶ�����
			String zdlx = map.get("zdlx");

			// ��ͥ������Ϣչ������
			if (mk.equalsIgnoreCase(lyb)) {

				// �ֶ�ֵ
				String zdz = qtInfo.get(zd);

				map.put("zdz", zdz);
				map.put("kjlx", zdlx);

				// һ����Ԫ��
				if ("text".equalsIgnoreCase(zdlx)
						|| "select".equalsIgnoreCase(zdlx)) {
					shortList.add(map);
				}
				// �����Ԫ��
				else if ("textarea".equalsIgnoreCase(zdlx)
						|| "long".equalsIgnoreCase(zdlx)) {
					longList.add(map);
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (longList != null && longList.size() > 0) {
			map.put("zdlx", "long");
			map.put("zdList", longList);
			qtInfolist.add(map);
		}

		// ������һ���ֶε�λ��
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			qtInfolist.add(map);
		}

		if (qtInfolist != null && qtInfolist.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", qtInfolist);
		}

		resultList.add(map);
	}

	/**
	 * �����Ŀ�����ֶ��б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmZdList(XszzTyForm model)
			throws Exception {

		String tableName = "xszz_xmnrzdb";
		String xmdm = model.getXmdm();
		String mrxm = model.getMrxm();

		String[] colList = getTableZd(tableName);
		String[] queryList = new String[] { "xmdm" };
		String[] queryLikeList = new String[] {};

		if ("��".equalsIgnoreCase(mrxm)) {
			model.setXmdm("��");
		}

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by lyb,zdlx,to_number(zdsx)");

		List<HashMap<String, String>> zdList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		model.setXmdm(xmdm);

		return zdList;
	}

	/**
	 * 
	 * ���ѧ��ס����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXsZxInfo(XszzTyForm model) {

		String tableName = "view_xszsxx";

		String[] colList = new String[] { "xqmc", "ssbh", "qsdh" };

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(tableName,
				colList, "xh", model.getXh());

		return map;
	}

	/**
	 * 
	 * ����ۺϲ�����Ϣ
	 * 
	 * @author lr
	 */
	public HashMap<String, String> getXsZhcpInfo(XszzTyForm model) {
		HashMap<String, String> map = new HashMap<String, String>();
		String tableName = "view_hndx_zhcp";

		String[] colList = new String[] { "zhcppm", "fsbjpm", "fsnjpm",
				"fsnjzypm" };
		model.setXn(StringUtils.isNull(model.getXn()) ? Base.currXn : model
				.getXn());
		map = CommonQueryDAO.commonQueryOne(tableName, colList, "xh||xn", model
				.getXh()
				+ model.getXn());
		return map;
	}

	/**
	 * 
	 * �����������Ϣ
	 * 
	 * @author lr
	 */
	public HashMap<String, String> getXsKnInfo(XszzTyForm model) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String tableName = "xszz_knsb";
		String pk = "xh";
		String pkValue = model.getXh();
		String[] colList = new String[] { "xmzzjb" };

		// ��Ŀ��������
		String xmsqzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		if ("���".equalsIgnoreCase(xmsqzq)) {
			pk += "||nd";
			model.setNd(StringUtils.isNull(model.getNd()) ? Base.currNd : model
					.getNd());
			pkValue += model.getNd();
		} else if ("ѧ��".equalsIgnoreCase(xmsqzq)) {
			pk += "||xn";
			model.setXn(StringUtils.isNull(model.getXn()) ? Base.currXn : model
					.getXn());
			pkValue += model.getXn();
		} else if ("ѧ��".equalsIgnoreCase(xmsqzq)) {
			pk += "||xn||xq";
			model.setXn(StringUtils.isNull(model.getXn()) ? Base.currXn : model
					.getXn());
			model.setXq(StringUtils.isNull(model.getXq()) ? Base.currXq : model
					.getXq());
			pkValue += model.getXn() + model.getXq();
		}

		String query = getShtgtj(model.getXmdm()).toString();
		query += " order by sqsj ";

		map = CommonQueryDAO.commonQueryOne2(tableName, colList, pk, pkValue,
				query);
		return map;
	}

	/**
	 * ��ȡ���ͨ������
	 * 
	 * @param xmdm
	 * @return StringBuilder
	 */
	private StringBuilder getShtgtj(String xmdm) {
		DAO dao = DAO.getInstance();
		StringBuilder sb = new StringBuilder();
		String shjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm", xmdm);

		if ("һ�����".equalsIgnoreCase(shjb)) {
			sb.append(" and shzt1='ͨ��'");
		} else if ("�������".equalsIgnoreCase(shjb)) {
			sb.append(" and shzt2='ͨ��'");
		} else if ("�������".equalsIgnoreCase(shjb)) {
			sb.append(" and shzt3='ͨ��'");
		}
		return sb;
	}

	/**
	 * ���ѧ����ͥ���������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkInfo(XszzTyForm model)
			throws Exception {

		DAO dao = DAO.getInstance();

		String tableName = "jtqkdcb";
		String xmdm = model.getXmdm();
		String xh = model.getXh();
		String[] colList = dao.getRs(
				"select * from xszz_xmnrzdb where xmdm = ? and lyb = ?",
				new String[] { xmdm, tableName }, "zd");// getTableZd(tableName);
		String query = " and sqsj = (select max(sqsj) from " + tableName
				+ " where xh = '" + xh + "')";

		HashMap<String, String> map = new HashMap<String, String>();
		 if(colList.length>0){
			map = CommonQueryDAO.commonQueryOne2(tableName, colList, "xh", xh,
					query);
		 }else{
			 xmdm="��";
			 
			 colList = dao.getRs(
						"select * from xszz_xmnrzdb where xmdm = ? and lyb = ?",
						new String[] { xmdm, tableName }, "zd");// getTableZd(tableName);
			 map = CommonQueryDAO.commonQueryOne2(tableName, colList, "xh", xh,
						query);
		 }
		 //����ʦ��ѧԺ
		 if(Base.xxdm.equals(Globals.XXDM_HZSFXY)){
			 //��������ͨѶ��ַ
			if(null!=map.get("kzzd2") && !"".equals(map.get("kzzd2"))){
				map.put("mzbmtxdz", map.get("kzzd2"));
			}
			//����������������
			if(null!=map.get("kzzd3") && !"".equals(map.get("kzzd3"))){
				map.put("mzbmyzbm", map.get("kzzd3"));
			}
			//����������ϵ�绰
			if(null!=map.get("kzzd4") && !"".equals(map.get("kzzd4"))){
				map.put("mzbmlxdh", map.get("kzzd4"));
			}
			//�����ͥ����
			if(null!=map.get("kzzd5") && !"".equals(map.get("kzzd5"))){
				map.put("snjtsr", map.get("kzzd5"));
			}
		 }
		return map;
	}

	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getQtInfo(XszzTyForm model) throws Exception {
		
		DAO dao = DAO.getInstance();
		String tableName = "xsqtxxb";
		String xmdm = model.getXmdm();
		String xh = model.getXh();
		String[] colList =  dao.getRs(
				"select * from xszz_xmnrzdb where (xmdm = ? or xmdm='��') and lyb = ?",
				new String[] { xmdm, tableName }, "zd");//getTableZd(tableName);
		String query = " and sqsj = (select max(sqsj) from " + tableName
				+ " where xh = '" + xh + "')";

		HashMap<String, String> map = new HashMap<String,String>();
		if (colList != null && colList.length > 0) {
			map = CommonQueryDAO.commonQueryOne2(tableName, colList, "xh", xh,
					query);
		}

		return map;
	}

	/**
	 * ���������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsInfo(XszzTyForm model)
			throws Exception {

		String tableName = "xszz_knsb";
		String xh = model.getXh();
		String[] colList = getTableZd(tableName);
		String query = " and sqsj = (select max(sqsj) from " + tableName
				+ " where xh = '" + xh + "')";

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne2(tableName,
				colList, "xh", xh, query);

		return map;
	}

	/**
	 * �����Ŀ������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmInfo(XszzTyForm model) throws Exception {

		String tableName = model.getXmb();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ѧ��
		String xh = model.getXh();
		// ����ʱ��
		String sqsj = model.getSqsj();

		String[] colList = getTableZd(tableName);
		String query = "";

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne2(tableName,
				colList, "xmdm||xh||sqsj", xmdm + xh + sqsj, query);

		return map;
	}

	/**
	 * �����Ŀ�ֶ���Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> setXmzdInfo(XszzTyForm model)
			throws Exception {

		String tableName = model.getXmb();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ѧ��
		String xh = model.getXh();
		// ����ʱ��
		String sqsj = model.getSqsj();

		String[] colList = getTableZd(tableName);

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(tableName,
				colList, "xmdm||xh||sqsj", xmdm + xh + sqsj);

		return map;
	}

	/**
	 * �����Ŀ�ּ�����б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXmfjqkList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "xszz_xmfjqkb";

		String[] colList = new String[] { "fjmc", "fjxxje", "fjsxje", "fjqdje" };
		String[] queryList = new String[] { "xmdm" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by fjxxje, fjsxje, fjqdje");

		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query.toString(), myQuery.getInputList(), colList,
				"");

		return list;
	}
	
	/**
	 * ��ø�����·��
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public String getFile(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		String sql = "select scdz from xszz_knsb where xh= ? and sqsj = ?";
		String  filePath = dao.getOneRs(sql, new String[]{model.getXh(),model.getSqsj()}, "scdz");
		return filePath;
	}

	/**
	 * �����Ŀ����б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXmshList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ������Ŀ�б�
		List<HashMap<String, String>> xmList = getShXmList(model);

		// �Ƴ�����Ҫ��˵���Ŀ
		List<HashMap<String, String>> xhxmlist = removeShtjXm(model, xmList);

		xhxmlist = getXmxgRsList(xhxmlist, model);

		return xhxmlist;
	}

	/**
	 * ���������Ŀ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getShXmList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xszz_comm_xmwh";
		String mklx = model.getMklx();

		if (!Base.isNull(mklx)) {
			tableName = "pj".equalsIgnoreCase(mklx) ? "xg_view_xszz_pjpy_xmwh"
					: "xg_view_xszz_xmwh";
		}

		String[] colList = new String[] { "pk", "xmdm", "xmmc", "sqzq", "xmsm",
				"xmb", "rskz", "kzjb", "shjb", "bzrsh", "fdysh", "xysh",
				"xxsh", "rssx", "kgzt", "xmlb", "pdsj" };
		String[] queryList = new String[] { "xmlb" };
		String[] queryLikeList = new String[] { "xmmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��ѯ����
		StringBuilder query = new StringBuilder();

		query.append(" where kgzt <> '��Ŀ�ر�'");
		query.append(getFlowControlSql(model));//�������϶���Ϊ����ģ�����
		// -----edit quph 2010.9.19--begin--------
		// �������й�����ѧ�����ˡ������ѯ�е���ģ�飡��
		if (Globals.XXDM_NBCSZYJSXY.equals(Base.xxdm)) {
			query.append(" and xmdm <> '");
			query.append(XszzXmdm.XSZZ_GJZXDK);
			query.append("' ");
		}
		
		if(!Base.isNull(model.getXmlb())){
			query.append(" and xmlb = '");
			query.append(model.getXmlb());
			query.append("' ");
		}
		// -----------end---------
		query.append(" order by mrxm desc,to_number(xssx)");
		List<HashMap<String, String>> allXmList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(),
						new String[] {}, colList, "");

		return allXmList;
	}

	/**
	 * �Ƴ������������������Ŀ
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> removeShtjXm(XszzTyForm model,
			List<HashMap<String, String>> xmList) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// ��½�û����
		String lx = model.getLx();

		if (xmList != null && xmList.size() > 0) {

			for (int i = 0; i < xmList.size(); i++) {

				HashMap<String, String> xmInfo = xmList.get(i);

				// ����״̬
				String xmdm = xmInfo.get("xmdm");
				// ����״̬
				String kgzt = xmInfo.get("kgzt");
				// ��˼���
				String shjb = xmInfo.get("shjb");
				// �û�����
				xmInfo.put("lx", lx);

				// ���������Ŀ�رյ���Ŀ
				if ("��Ŀ�ر�".equalsIgnoreCase(kgzt)) {
					continue;
				}
				// �������������˵���Ŀ
				if ("�������".equalsIgnoreCase(shjb)) {
					continue;
				}
				String[] jb = new String[3];
				String[] jbmc = new String[3];
				getXmShjb(xmInfo, jb, jbmc);

				// �ж��û��Ƿ����ʸ����
				boolean flag = false;

				if (jb != null && jb.length > 0) {

					for (int j = 0; j < jb.length; j++) {

						String[] arr_jb = jb[j].split("-");

						if (arr_jb != null && arr_jb.length > 0) {

							for (int k = 0; k < arr_jb.length; k++) {

								if ((lx + "sh").equalsIgnoreCase(arr_jb[k])) {
									flag = true;
								} else if ("jd".equalsIgnoreCase(lx)
										&& ("bzrsh".equalsIgnoreCase(arr_jb[k]) || "fdysh"
												.equalsIgnoreCase(arr_jb[k]))) {
									flag = true;
								}
							}
						}

					}
				}

				if (flag) {
					xmInfo.put("tjjbOne", jb[0]);
					xmInfo.put("tjjbTwo", jb[1]);
					xmInfo.put("tjjbThr", jb[2]);
					list.add(xmInfo);
				}
			}
		}

		return list;
	}

	/**
	 * �����Ŀ��˼���
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	public String[] getXmShjb(HashMap<String, String> map, String[] jb,
			String[] jbmc) {

		// ��˼���
		String shjb = map.get("shjb");
		// ���������
		String bzrsh = map.get("bzrsh");
		// ����Ա���
		String fdysh = map.get("fdysh");
		// ѧԺ���
		String xysh = map.get("xysh");
		// ѧУ���
		String xxsh = map.get("xxsh");
		// �û�����
		String lx = map.get("lx");

		boolean flag = false;

		int n = 0;

		// ͳ�Ƽ���1
		String tjjbOne = "";
		String mcOne = "";
		// ͳ�Ƽ���2
		String tjjbTwo = "";
		String mcTwo = "";
		// ͳ�Ƽ���3
		String tjjbThr = "";
		String mcThr = "";

		// �ж��û��Ƿ������Ȩ��
		if ("��".equalsIgnoreCase(xxsh)) {
			n++;
			if (!flag) {
				flag = "xx".equalsIgnoreCase(lx) ? true : false;
			}
			if (flag) {
				tjjbOne = "xxsh";
			}
		}
		if ("��".equalsIgnoreCase(xysh)) {
			n++;
			if (!flag) {
				flag = "xy".equalsIgnoreCase(lx) ? true : false;
			}
			if (flag) {
				tjjbOne = "xysh";
			}
		}

		if ("��".equalsIgnoreCase(fdysh)) {
			n++;
			if (!flag) {
				flag = ("fdy".equalsIgnoreCase(lx) || "jd".equalsIgnoreCase(lx)) ? true
						: false;
			}
			if (flag) {
				tjjbOne = "fdysh";
			}
		}
		if ("��".equalsIgnoreCase(bzrsh)) {
			n++;
			if (!flag) {
				flag = ("bzr".equalsIgnoreCase(lx) || "jd".equalsIgnoreCase(lx)) ? true
						: false;
			}
			if (flag) {
				tjjbOne = "bzrsh";
			}
		}

		// һ����˵����
		if ("һ�����".equalsIgnoreCase(shjb)) {
			flag = true;
			// δ�����ļ�����ˣ�Ĭ�� ˭������
			if (Base.isNull(bzrsh) && Base.isNull(fdysh) && Base.isNull(xysh)
					&& Base.isNull(xxsh)) {
				tjjbOne = "bzrsh-fdysh-xysh-xxsh";
			} else {

			}
			mcOne = "";
		}
		// ������˵����
		if ("�������".equalsIgnoreCase(shjb)) {
			// δ���嶨������
			if (n != 2) {
				// δ��������������ˣ�Ĭ�� ѧԺ --> ѧУ
				if (Base.isNull(bzrsh) && Base.isNull(fdysh)
						&& Base.isNull(xysh) && Base.isNull(xxsh)) {
					if (!flag) {
						flag = ("xx".equalsIgnoreCase(lx) || "xy"
								.equalsIgnoreCase(lx)) ? true : false;
					}
					if (flag) {
						tjjbOne = "xysh";
						mcOne = "ѧԺ";
						tjjbTwo = "xxsh";
						mcTwo = "ѧУ";

					}
				}
				// ֻ������ѧԺ��ˣ��ڶ���Ĭ��ΪѧУ
				if ("��".equalsIgnoreCase(xysh) && Base.isNull(xxsh)) {
					if (!flag) {
						flag = "xx".equalsIgnoreCase(lx) ? true : false;
					}
					if (flag) {
						tjjbOne = "xysh";
						mcOne = "ѧԺ";
						tjjbTwo = "xxsh";
						mcTwo = "ѧУ";
					}
				}
				// ֻ�����˰༶��ˣ��ڶ���ѧԺ����ѧУ
				if (("��".equalsIgnoreCase(bzrsh) || "��".equalsIgnoreCase(fdysh))
						&& Base.isNull(xysh) && Base.isNull(xxsh)) {
					if (!flag) {
						flag = ("xx".equalsIgnoreCase(lx) || "xy"
								.equalsIgnoreCase(lx)) ? true : false;
					}
					if (flag) {
						tjjbOne = "bzrsh-fdysh";
						mcOne = "�����Σ�����Ա";
						tjjbTwo = "xysh-xxsh";
						mcTwo = "ѧԺ��ѧУ";
					}
				}
				// ֻ������ѧУ��ˣ���һ��ѧԺ���߰����Σ�����Ա�Կ�
				if ("��".equalsIgnoreCase(xxsh) && Base.isNull(bzrsh)
						&& Base.isNull(fdysh) && Base.isNull(xysh)) {
					if (!flag) {
						flag = ("xy".equalsIgnoreCase(lx)
								|| "fdy".equalsIgnoreCase(lx)
								|| "bzr".equalsIgnoreCase(lx) || "jd"
								.equalsIgnoreCase(lx)) ? true : false;
					}
					if (flag) {
						tjjbOne = "bzrsh-fdysh-xysh";
						mcOne = "�����Σ�����Ա, ѧԺ";
						tjjbTwo = "xxsh";
						mcTwo = "ѧУ";
					}
				}
			} else {// ���嶨����������
				// �����˰༶���
				if ("��".equalsIgnoreCase(bzrsh) || "��".equalsIgnoreCase(fdysh)) {

					tjjbOne = "bzrsh-fdysh";
					mcOne = "�����Σ�����Ա";
					if ("��".equalsIgnoreCase(xysh)) {
						tjjbTwo = "xysh";
						mcTwo = "ѧԺ";
					} else {
						tjjbTwo = "xxsh";
						mcTwo = "ѧУ";
					}
				} else {
					tjjbOne = "xysh";
					mcOne = "ѧԺ";
					tjjbTwo = "xxsh";
					mcTwo = "ѧУ";
				}
			}
		}

		// ������˵����
		if ("�������".equalsIgnoreCase(shjb)) {

			if ("��".equalsIgnoreCase(bzrsh)) {
				tjjbOne = "bzrsh";
				mcOne = "������";
			} else {
				tjjbOne = "fdysh";
				mcOne = "����Ա";
			}

			tjjbTwo = "xysh";
			mcTwo = "ѧԺ";
			tjjbThr = "xxsh";
			mcThr = "ѧУ";
		}

		jb[0] = tjjbOne;
		jb[1] = tjjbTwo;
		jb[2] = tjjbThr;

		jbmc[0] = mcOne;
		jbmc[1] = mcTwo;
		jbmc[2] = mcThr;

		return jb;
	}

	/**
	 * �����Ŀ�������
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	public List<HashMap<String, String>> getXmxgRsList(
			List<HashMap<String, String>> xhxmlist, XszzTyForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xhxmlist != null && xhxmlist.size() > 0) {

			// ��Ŀ����
			String[] xmdm = new String[xhxmlist.size()];
			// ��Ŀ���
			String[] xmlb = new String[xhxmlist.size()];
			// ��Ŀ����
			String[] xmmc = new String[xhxmlist.size()];
			// ������Ŀ��
			String[] xmb = new String[xhxmlist.size()];
			// ͳ�Ƽ���1
			String[] tjjbOne = new String[xhxmlist.size()];
			// ͳ�Ƽ���2
			String[] tjjbTwo = new String[xhxmlist.size()];
			// ͳ�Ƽ���3
			String[] tjjbThr = new String[xhxmlist.size()];
			// ��˼���
			String[] shjb = new String[xhxmlist.size()];
			// ��������
			String[] sqzq = new String[xhxmlist.size()];
			// ����ʱ��
			String[] pdsj = new String[xhxmlist.size()];

			for (int i = 0; i < xhxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xhxmlist.get(i);

				xmdm[i] = xmInfo.get("xmdm");
				xmlb[i] = xmInfo.get("xmlb");
				xmmc[i] = xmInfo.get("xmmc");
				xmb[i] = xmInfo.get("xmb");
				sqzq[i] = xmInfo.get("sqzq");
				shjb[i] = xmInfo.get("shjb");
				pdsj[i] = xmInfo.get("pdsj");
				tjjbOne[i] = xmInfo.get("tjjbOne");
				tjjbTwo[i] = xmInfo.get("tjjbTwo");
				tjjbThr[i] = xmInfo.get("tjjbThr");
			}

			list = dao.getXmSqShList(model, xmdm, xmlb, xmmc, xmb, sqzq, shjb,
					pdsj, tjjbOne, tjjbTwo, tjjbThr);
		}

		return list;
	}

	/**
	 * ��õ�½�û����ʸ���˵�ѧ���б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsShList(XszzTyForm model,
			HashMap<String, String> map) {

		// �û�����
		String lx = model.getLx();
		map.put("lx", lx);
		// ��˼���
		String[] jb = new String[3];
		String[] jbmc = new String[3];
		getXmShjb(map, jb, jbmc);
		model.setJb(jb);
		model.setJbmc(jbmc);

		List<HashMap<String, String>> list = null;

		if (jb != null && jb.length == 3) {
			list = dao.getXsShList(model, map);
		}

		return getResultList(list, model);
	}

	/**
	 * �����Ŀ���������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getXmShNrList(XszzTyForm model,
			HashMap<String, String> xmInfo) throws Exception {

		String mklx = model.getMklx();

		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ��
		String xmb = model.getXmb();

		// �ֶ��б�
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		// �ֶ��б�
		List<HashMap<String, String>> zdList = getXmZdList(model);
		// ����ѧ��������Ϣ
		setXsInfo(model, resultList, zdList);
		// ���ü�ͥ�������
		setJtjbInfo(model, resultList, zdList);
		// �����۲���Ϣ
		setZcfInfo(model, resultList, zdList);
		// ������Ϣ
		setKnInfo(model, resultList, zdList);

		//if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)||Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {
			// ����������Ϣ
			setQtInfo(model, resultList, zdList);
		//}

		if (!"jtqkdcb".equalsIgnoreCase(xmb)) {
			// ������Ŀ�������
			setXmzdInfo(model, resultList, zdList);
		}

		// �㽭�Ƽ���ѧ����
		if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)
				&& "gjzxdkb".equalsIgnoreCase(xmb)) {
			setZxdkInfo(model, resultList, zdList);
		}

		model.setXmdm(xmdm);
		// ������˻������
		if ("jg".equalsIgnoreCase(mklx)) {
			setJgInfo(model, resultList, xmInfo);
		} else {
			setShInfo(model, resultList, xmInfo);
		}

		return resultList;
	}

	/**
	 * ������˻������
	 * 
	 * @author luojw
	 * @param zdList
	 */
	private void setShInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			HashMap<String, String> xmInfo) {

		// ģ��
		String mk = "shInfo";
		// �û�����
		String lx = model.getLx();
		xmInfo.put("lx", lx);
		// ��˼���
		String[] jb = new String[3];
		String[] jbmc = new String[3];
		getXmShjb(xmInfo, jb, jbmc);
		model.setJb(jb);
		model.setJbmc(jbmc);

		HashMap<String, Object> map = new HashMap<String, Object>();

		List<HashMap<String, String>> shInfoList = dao.getShInfoList(model,
				xmInfo);

		if (shInfoList != null && shInfoList.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", shInfoList);
		}

		resultList.add(map);
	}

	/**
	 * ������˻������
	 * 
	 * @author luojw
	 * @param zdList
	 */
	private void setJgInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			HashMap<String, String> xmInfo) {

		// ģ��
		String mk = "shInfo";

		HashMap<String, Object> map = new HashMap<String, Object>();

		List<HashMap<String, String>> shInfoList = dao.getJgInfoList(model,
				xmInfo);

		if (shInfoList != null && shInfoList.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", shInfoList);
		}

		resultList.add(map);
	}

	/**
	 * �������
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	public boolean savePlsh(XszzTyForm model, String tableName,
			HashMap<String, String> map) throws Exception {

		String[] zd = null;

		if (!map.isEmpty()) {

			zd = new String[map.size()];

			int n = 0;

			for (String key : map.keySet()) {

				if (!Base.isNull(map.get(key)) && !"sqsj".equalsIgnoreCase(key)) {

					zd[n] = key;

					n++;
				}

			}
		}

		int n = 0;

		for (int i = 0; i < zd.length; i++) {
			if (!Base.isNull(zd[i])) {
				n++;
			}
		}

		String[] onezd = new String[n];

		for (int i = 0; i < onezd.length; i++) {

			onezd[i] = zd[i];
			if ("xmzzje".equalsIgnoreCase(onezd[i])) {
				model.setXmzzje(map.get("xmzzje"));
			}
			if ("xmzzjb".equalsIgnoreCase(onezd[i])) {
				model.setXmzzjb(map.get("xmzzjb"));
			}
			if ("bzrsh".equalsIgnoreCase(onezd[i])) {
				model.setBzrsh(map.get("bzrsh"));
			}
			if ("fdysh".equalsIgnoreCase(onezd[i])) {
				model.setFdysh(map.get("fdysh"));
			}
			if ("xysh".equalsIgnoreCase(onezd[i])) {
				model.setXysh(map.get("xysh"));
			}
			if ("xxsh".equalsIgnoreCase(onezd[i])) {
				model.setXxsh(map.get("xxsh"));
			}
			if ("shzt1".equalsIgnoreCase(onezd[i])) {
				model.setShzt1(map.get("shzt1"));
			}
			if ("shzt1yj".equalsIgnoreCase(onezd[i])) {
				model.setShzt1yj(map.get("shzt1yj"));
			}
			if ("shzt2".equalsIgnoreCase(onezd[i])) {
				model.setShzt2(map.get("shzt2"));
			}
			if ("shzt2yj".equalsIgnoreCase(onezd[i])) {
				model.setShzt2yj(map.get("shzt2yj"));
			}
			if ("shzt3".equalsIgnoreCase(onezd[i])) {
				model.setShzt3(map.get("shzt3"));
			}
			if ("shzt3yj".equalsIgnoreCase(onezd[i])) {
				model.setShzt3yj(map.get("shzt3yj"));
			}
			
			if ("shsj1".equalsIgnoreCase(onezd[i])) {
				model.setShsj1(map.get("shsj1"));
			}
			if ("shsj2".equalsIgnoreCase(onezd[i])) {
				model.setShsj2(map.get("shsj2"));
			}
			if ("shsj3".equalsIgnoreCase(onezd[i])) {
				model.setShsj3(map.get("shsj3"));
			}
		}

		String pk = "xh||sqsj||xmdm";
		String[] pkValue = null;
		String shpk = model.getShpk();

		if (!Base.isNull(shpk)) {

			pkValue = new String[shpk.length()];

			String[] arr_shpk = shpk.split("!!@@!!");

			for (int i = 0; i < arr_shpk.length; i++) {
				pkValue[i] = arr_shpk[i];
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateXszz(saveForm, model);
	}

	/**
	 * ��ý����ѯ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getJgcxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ������Ŀ�б�
		List<HashMap<String, String>> allXmList = getShXmList(model);

		String[] xmb = null;

		if (allXmList != null && allXmList.size() > 0) {

			xmb = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				xmb[i] = allXmList.get(i).get("xmb");
			}
		}

		// ��������б�
		List<HashMap<String, String>> jgList = dao.getXmshJgList(model, xmb);

		if (allXmList != null && allXmList.size() > 0) {

			for (int i = 0; i < allXmList.size(); i++) {

				HashMap<String, String> xmInfo = allXmList.get(i);

				String shjb = xmInfo.get("shjb");

				if (jgList != null && jgList.size() > 0) {

					for (int j = 0; j < jgList.size(); j++) {

						HashMap<String, String> jgInfo = jgList.get(j);

						String shzt1 = jgInfo.get("shzt1");
						String shzt2 = jgInfo.get("shzt2");
						String shzt3 = jgInfo.get("shzt3");

						if (xmInfo.get("xmdm").equalsIgnoreCase(
								jgInfo.get("xmdm"))) {

							jgInfo.put("shjb", shjb);

							if ("δ���".equalsIgnoreCase(shzt1)
									&& "δ���".equalsIgnoreCase(shzt2)
									&& "δ���".equalsIgnoreCase(shzt3)) {
								jgInfo.put("delete", "yes");
							} else {
								jgInfo.put("delete", "no");
							}

							// list.add(jgInfo);
						}
					}

				}
			}
		}
		return jgList;
	}
	
	

	/**
	 * ���������ѯ�б�
	 * 
	 * @author qph
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getZzxbList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZzxmList(model);
	}

	/**
	 * �����ͥ��Ա
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String saveJtcy(XszzTyForm model) throws Exception {

		String tableName = "xszz_jtcyb";

		String pk = "xh";

		String[] zd = getTableZd(tableName);

		int n = 0;

		// ������Ҫ���������
		for (int i = 0; i < zd.length; i++) {
			if (!"id".equalsIgnoreCase(zd[i]) && !"xh".equalsIgnoreCase(zd[i])) {
				String methodName = "get" + zd[i].substring(0, 1).toUpperCase()
						+ zd[i].substring(1);

				String[] arr = (String[]) model.getClass().getMethod(
						methodName, (Class[]) null).invoke(model,
						(Object[]) null);
				if (arr != null && arr.length > 0) {
					n++;
				}
			}
		}

		String[] arrzd = new String[n];
		int m = 0;
		for (int i = 0; i < zd.length; i++) {
			if (!"id".equalsIgnoreCase(zd[i]) && !"xh".equalsIgnoreCase(zd[i])) {
				String methodName = "get" + zd[i].substring(0, 1).toUpperCase()
						+ zd[i].substring(1);

				String[] arr = (String[]) model.getClass().getMethod(
						methodName, (Class[]) null).invoke(model,
						(Object[]) null);
				if (arr != null && arr.length > 0) {
					arrzd[m] = zd[i];
					m++;
				}
			}
		}

		String[] onezd = new String[] { "xh" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXh() });

		boolean flag = saveXszz(saveForm, model);

		String msg = flag ? "" : "��ͥ��Ա�������ʧ��";

		return msg;
	}

	/**
	 * ��ü�ͥ��ϵ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getJtgxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xszz_jtgx";

		String[] colList = new String[] { "cydh", "cygx", "cygzdw", "cyjkzk",
				"cynl", "cynsr", "cynzc", "cyxm", "cyzy", "id", "mc", "xh",
				"cyyb", "cyysr" };
		String[] queryList = new String[] { "xh" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by cygx");

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		/*
		 * �������Ա������Ӱ�ť�������е�xszz_jtgx������������ * @sjf
		 */
		if (StringUtils.isNotNull(model.getXh())) {
			list = CommonQueryDAO.commonQueryforList(tableName, query
					.toString(), myQuery.getInputList(), colList, "");
		}

		return list;
	}

	/**
	 * ���ѧ����Ϣ�б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		String tableName = "view_xsjbxx";
		String lx = model.getLx();
		String zgh = model.getZgh();

		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zymc", "zydm", "bjmc", "bjdm" };
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// TODO
		if ("fdy".equalsIgnoreCase(lx)) {
			query.append(" and exists(select 1 from fdybjb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm ");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
		} else if ("bzr".equalsIgnoreCase(lx)) {
			query.append(" and exists(select 1 from bzrbbb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
		} else if ("jd".equalsIgnoreCase(lx)) {
			query.append(" and (");
			query.append(" exists(select 1 from fdybjb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");

			query.append(" or exists(select 1 from bzrbbb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");

			query.append(" )");
		}

		// ѧ���б�
		List<HashMap<String, String>> xsList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		// ��Ŀ�б�
		List<HashMap<String, String>> allXmList = getAllXmList(model);
		//		
		// // ���ѧ������ѧԺ��רҵ�༶�������ͨ��ѧ���б�
		// List<HashMap<String, String>> xmYtgList = getXmYtgList(allXmList,
		// stuInfo, model);
		//
		// // �����Ŀ���������б�
		// List<HashMap<String, String>> xmRsszList = getXmRsszList(allXmList,
		// model);
		// // �����Ŀ�����б�
		// List<HashMap<String, String>> xmTjList = getXmTjList(model);
		//
		// // ѧ����������Ŀ�б�
		// List<HashMap<String, String>> xsYsqXmList = getXsYsqXmList(allXmList,
		// model);

		for (int i = 0; i < allXmList.size(); i++) {

			HashMap<String, String> xmInfo = allXmList.get(i);

			// ��ǰѧ��
			String xn = model.getXn();
			// ��ǰѧ��
			String xq = model.getXq();
			// ��ǰ���
			String nd = model.getNd();
			// ��Ŀ����
			String xmdm = xmInfo.get("xmdm");
			// ��������
			String sqzq = xmInfo.get("sqzq");
			// ���������Ƿ���Ҫ
			String rskz = xmInfo.get("rskz");

			for (int j = 0; j < xsList.size(); j++) {

				HashMap<String, String> stuInfo = xsList.get(j);

				// ѧԺ
				String xydm = stuInfo.get("xydm");
				// �꼶
				String nj = stuInfo.get("nj");
				// רҵ
				String zydm = stuInfo.get("zydm");
				// �༶
				String bjdm = stuInfo.get("bjdm");
			}

			// �Ƴ���Ҫ�������ƶ���������δ���ã�����Ϊ0������Ŀ
		}
		// if (xsList != null && xsList.size() > 0) {
		//
		// for (int i = 0; i < xsList.size(); i++) {
		//
		// HashMap<String, String> stuInfo = xsList.get(i);
		//
		// if (xsList != null && xsList.size() > 0) {
		//
		// for (int j = 0; j < allXmList.size(); j++) {
		//
		// HashMap<String, String> xmInfo = allXmList.get(j);
		//
		// // �Ƴ���Ŀ�ﵽ���޵���Ŀ
		// xqxmlist = removeXmSxList(model, xmYtgList, xqxmlist);
		//
		// // �Ƴ��������������õ���Ŀ
		// xqxmlist = removeBfhTjXm(model, xmTjList, xqxmlist);
		//
		// // ������Ŀ������Ϣ
		// xqxmlist = setXmOtherList(xqxmlist);
		//
		// // ������Ŀ��������Ϣ
		// xqxmlist = setYsqXm(model, xsYsqXmList, xqxmlist);
		// }
		// }
		// }
		// }

		list = xsList;
		return getResultList(list, model);
	}

	/**
	 * �ж��Ƿ񳬹���������
	 * 
	 * @author luojw
	 */
	public String isCgrssx(HashMap<String, String> map, XszzTyForm model) {

		XsxxglService stuService = new XsxxglService();

		String msg = "";

		// ѧ��
		String xh = model.getXh();

		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// ��������
		String rskz = map.get("rskz");
		map.putAll(stuInfo);

		if ("��Ҫ".equalsIgnoreCase(rskz)) {

			// ��������
			String szrs = dao.getXmszRs(model, map);
			// ��ͨ������
			String rs = dao.getXmshRs(model, map);

			if (Base.isNull(szrs) || "0".equalsIgnoreCase(szrs)) {
				msg = "��δ��������������������Ϊ0����ȷ����";
			} else if (!Base.isNull(rs)) {
				if (Integer.parseInt(rs) >= Integer.parseInt(szrs)) {
					msg = "����Ŀ���ͨ�������Ѿ��ﵽ���ޣ���ȷ����";
				}
			}
		}

		return msg;
	}

	/**
	 * �ж��Ƿ񳬹���������(����)
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public String isCgrssxPl(HashMap<String, String> map, XszzTyForm model)
			throws SQLException {

		String msg = "";

		// ѧ��+����ʱ��
		String shpk = model.getShpk();

		String[] pkValue = null;

		if (!Base.isNull(shpk)) {

			pkValue = new String[shpk.length()];

			String[] arr_shpk = shpk.split("!!@@!!");

			if (arr_shpk != null && arr_shpk.length > 0) {

				pkValue = new String[arr_shpk.length];

				for (int i = 0; i < arr_shpk.length; i++) {
					pkValue[i] = arr_shpk[i];
				}
			}

		}

		model.setCheckVal(pkValue);

		// ���Ƽ���
		String kzjb = map.get("kzjb");
		// ��������
		String rskz = map.get("rskz");

		if ("��Ҫ".equalsIgnoreCase(rskz)) {

			// ��������
			List<HashMap<String, String>> szrsList = dao.getXmszRsList(model,
					map);
			// ��ͨ������
			List<HashMap<String, String>> rsList = dao
					.getXmshRsList(model, map);
			// �������
			List<HashMap<String, String>> shList = dao.getShRsList(model, map);

			if (szrsList == null || szrsList.size() == 0) {
				msg = "��δ��������������������Ϊ0����ȷ����";
			} else {

				boolean flag = false;

				for (int i = 0; i < szrsList.size(); i++) {

					HashMap<String, String> szrsInfo = szrsList.get(i);

					// String bmjb = szrsInfo.get("bmjb");
					String nj = szrsInfo.get("nj");
					String bmdm = szrsInfo.get("bmdm");
					String num = szrsInfo.get("num");

					if (rsList != null && rsList.size() > 0) {

						for (int j = 0; j < rsList.size(); j++) {

							HashMap<String, String> rs = rsList.get(j);

							String rsnj = rs.get("nj");
							String xydm = rs.get("xydm");
							String zydm = rs.get("zydm");
							String bjdm = rs.get("bjdm");
							String szrs = rs.get("num");

							if (shList != null && shList.size() > 0) {

								for (int k = 0; k < shList.size(); k++) {
									HashMap<String, String> shInfo = shList
											.get(k);

									String shnj = shInfo.get("nj");
									String shxy = shInfo.get("xydm");
									String shzy = shInfo.get("zydm");
									String shbj = shInfo.get("bjdm");
									String shnum = shInfo.get("num");

									if ("ѧԺ".equalsIgnoreCase(kzjb)) {
										if (bmdm.equalsIgnoreCase(xydm)
												&& bmdm.equalsIgnoreCase(shxy)) {
											if (Integer.parseInt(shnum)
													+ Integer.parseInt(szrs) > Integer
													.parseInt(num)) {

												String bmmc = getOneValue(
														"view_njxyzybj",
														"xymc", "xydm", xydm);

												msg = bmmc
														+ "����Ŀ���ͨ�������Ѿ��ﵽ���ޣ���ȷ����";

												flag = true;

												break;
											}
										}
									}

									if ("רҵ".equalsIgnoreCase(kzjb)) {
										if (bmdm.equalsIgnoreCase(zydm)
												&& nj.equalsIgnoreCase(rsnj)
												&& bmdm.equalsIgnoreCase(shzy)
												&& nj.equalsIgnoreCase(shnj)) {
											if (Integer.parseInt(shnum)
													+ Integer.parseInt(szrs) > Integer
													.parseInt(num)) {

												String bmmc = getOneValue(
														"view_njxyzybj",
														"zymc", "zydm", zydm);

												msg = nj
														+ "�꼶"
														+ bmmc
														+ "����Ŀ���ͨ�������Ѿ��ﵽ���ޣ���ȷ����";

												flag = true;

												break;
											}
										}
									}

									if ("�༶".equalsIgnoreCase(kzjb)) {
										if (bmdm.equalsIgnoreCase(bjdm)
												&& bmdm.equalsIgnoreCase(shbj)) {
											if (Integer.parseInt(shnum)
													+ Integer.parseInt(szrs) > Integer
													.parseInt(num)) {

												String bmmc = getOneValue(
														"view_njxyzybj",
														"bjmc", "bjdm", bjdm);

												msg = bmmc
														+ "����Ŀ���ͨ�������Ѿ��ﵽ���ޣ���ȷ����";

												flag = true;

												break;
											}
										}
									}
								}
							}

							if (flag) {
								break;
							}
						}
					} else {
						if (shList != null && shList.size() > 0) {

							for (int k = 0; k < shList.size(); k++) {
								HashMap<String, String> shInfo = shList.get(k);

								String shnj = shInfo.get("nj");
								String shxy = shInfo.get("xydm");
								String shzy = shInfo.get("zydm");
								String shbj = shInfo.get("bjdm");
								String shnum = shInfo.get("num");

								if ("ѧԺ".equalsIgnoreCase(kzjb)) {
									if (bmdm.equalsIgnoreCase(shxy)) {
										if (Integer.parseInt(shnum) > Integer
												.parseInt(num)) {

											String bmmc = getOneValue(
													"view_njxyzybj", "xymc",
													"xydm", shxy);

											msg = bmmc + "����Ŀ���ͨ�������Ѿ��ﵽ���ޣ���ȷ����";

											flag = true;

											break;
										}
									}
								}

								if ("רҵ".equalsIgnoreCase(kzjb)) {
									if (bmdm.equalsIgnoreCase(shzy)
											&& nj.equalsIgnoreCase(shnj)) {
										if (Integer.parseInt(shnum) > Integer
												.parseInt(num)) {

											String bmmc = getOneValue(
													"view_njxyzybj", "zymc",
													"zydm", shzy);

											msg = nj + "�꼶" + bmmc
													+ "����Ŀ���ͨ�������Ѿ��ﵽ���ޣ���ȷ����";

											flag = true;

											break;
										}
									}
								}

								if ("�༶".equalsIgnoreCase(kzjb)) {
									if (bmdm.equalsIgnoreCase(shbj)) {
										if (Integer.parseInt(shnum) > Integer
												.parseInt(num)) {

											String bmmc = getOneValue(
													"view_njxyzybj", "bjmc",
													"bjdm", shbj);

											msg = bmmc + "����Ŀ���ͨ�������Ѿ��ﵽ���ޣ���ȷ����";

											flag = true;

											break;
										}
									}
								}
							}
						}
					}

					if (flag) {
						break;
					}
				}
			}
		}

		return msg;
	}

	/**
	 * ɾ����Ŀ������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delXmsqInfo(XszzTyForm model) throws Exception {

		return dao.delXmsqInfo(model);
	}

	/**
	 * ����״̬��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void addZtInfo(HashMap<String, String> map) {

		String tableName = "xszz_shztb";
		String xh = map.get("xh");
		String xmdm = map.get("xmdm");
		String sqsj = map.get("sqsj");
		String xn = map.get("xn");
		String xq = map.get("xq");
		String nd = map.get("nd");

		String[] columns = new String[] { "xh", "xmdm", "sqsj", "xn", "xq",
				"nd" };
		String[] values = new String[] { xh, xmdm, sqsj, xn, xq, nd };

		try {
			StandardOperation.insertNoLog(tableName, columns, values);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * �޸�״̬��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void updateZtInfo(HashMap<String, String> map) {

		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();

		String xh = map.get("xh");
		String xmdm = map.get("xmdm");
		String sqsj = map.get("sqsj");

		setZtzdInfo(map, "xmzzjb", columns, values);
		setZtzdInfo(map, "xmzzje", columns, values);

		setZtzdInfo(map, "bzrsh", columns, values);
		setZtzdInfo(map, "fdysh", columns, values);
		setZtzdInfo(map, "xysh", columns, values);
		setZtzdInfo(map, "xxsh", columns, values);

		setZtzdInfo(map, "shzt1", columns, values);
		setZtzdInfo(map, "shzt2", columns, values);
		setZtzdInfo(map, "shzt3", columns, values);

		String tableName = "xszz_shztb";
		String primaryKey = "xh||sqsj||xmdm";
		String pkValue = xh + sqsj + xmdm;

		try {
			StandardOperation.updateNolog(tableName, columns
					.toArray(new String[] {}), values.toArray(new String[] {}),
					primaryKey, pkValue);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * �޸�״̬��Ϣ(����)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void updateZtInfoPl(HashMap<String, String> map, XszzTyForm model) {

		ArrayList<String> onezd = new ArrayList<String>();

		if (!Base.isNull(map.get("xmzzjb"))) {
			onezd.add("xmzzjb");
			model.setXmzzjb(map.get("xmzzjb"));
		}
		if (!Base.isNull(map.get("xmzzje"))) {
			onezd.add("xmzzje");
			model.setXmzzje(map.get("xmzzje"));
		}

		if (!Base.isNull(map.get("bzrsh"))) {
			onezd.add("bzrsh");
			model.setBzrsh(map.get("bzrsh"));
		}
		if (!Base.isNull(map.get("fdysh"))) {
			onezd.add("fdysh");
			model.setFdysh(map.get("fdysh"));
		}
		if (!Base.isNull(map.get("xysh"))) {
			onezd.add("xysh");
			model.setXysh(map.get("xysh"));
		}
		if (!Base.isNull(map.get("xxsh"))) {
			onezd.add("xxsh");
			model.setXxsh(map.get("xxsh"));
		}

		if (!Base.isNull(map.get("shzt1"))) {
			onezd.add("shzt1");
			model.setShzt1(map.get("shzt1"));
		}
		if (!Base.isNull(map.get("shzt2"))) {
			onezd.add("shzt2");
			model.setShzt2(map.get("shzt2"));
		}
		if (!Base.isNull(map.get("shzt3"))) {
			onezd.add("shzt3");
			model.setShzt3(map.get("shzt3"));
		}

		String tableName = "xszz_shztb";
		String pk = "xh||sqsj||xmdm";
		String[] pkValue = null;
		String shpk = model.getShpk();

		if (!Base.isNull(shpk)) {

			pkValue = new String[shpk.length()];

			String[] arr_shpk = shpk.split("!!@@!!");

			for (int i = 0; i < arr_shpk.length; i++) {
				pkValue[i] = arr_shpk[i];
			}
		}

		try {
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setOnezd(onezd.toArray(new String[] {}));

			updateXszz(saveForm, model);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @param map
	 * @param columns
	 * @param values
	 */
	private void setZtzdInfo(HashMap<String, String> map, String zd,
			ArrayList<String> columns, ArrayList<String> values) {
		String zdValue = map.get(zd);
		if (!Base.isNull(zdValue)) {
			columns.add(zd);
			values.add(zdValue);
		}
	}

	/**
	 * �����ѧ��ѧ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getBeforeXnXqNd(String sqzq, String pdsj,
			XszzTyForm model) {
		return dao.getBeforeXnXqNd(sqzq, pdsj, model);
	}

	/**
	 * ɾ����Ŀ�����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delXmxgInfo(XszzTyForm model) throws Exception {
		return dao.delXmxgInfo(model);
	}

	/**
	 * ͬ��״̬��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean tbZtInfo() throws Exception {
		return dao.tbZtInfo();
	}

	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public String getUserLx(User user) {
		// �û�����
		String userType = user.getUserType();
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(user.getBzrQx());

		String lx = "";

		if (bzrQx && fdyQx) {// �����μ渨��Ա
			lx = "jd";
		} else if (fdyQx) {// ����Ա
			lx = "fdy";
		} else if (bzrQx) {// ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			lx = "xx";
		} else {
			lx = "stu";
		}

		return lx;
	}

	// =======================����made by ΰ���luo==========================

	/**
	 * ����ѧԺ�۲�ִ�ӡ����
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> getMjxyZcf(String xh, String xn) {
		GuizhdxDAO dao = new GuizhdxDAO();
		HashMap<String, String> zcMap = new HashMap<String, String>();
		zcMap.put("xh", xh);
		zcMap.put("xn", xn);

		List<HashMap<String, String>> zcfList = dao.getZcf(zcMap);
		String zyrs = dao.getZyrs(xh);
		zcMap.put("zyzrs", zyrs);

		zcMap.put("cjpm", getCjpm(xh));// ����ɼ�����
		zcMap.put("cjpmbl", String.valueOf(Math.rint(Double
				.parseDouble(getCjpm(xh))
				/ Double.parseDouble(zyrs))));

		for (HashMap<String, String> temp : zcfList) {
			if ("�ܷ�".equals(temp.get("mc")) && !Base.isNull(temp.get("pm"))) {
				zcMap.put("zjfpm", temp.get("pm"));
				zcMap.put("zjfpmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			} else if (getZcfTitle()[0].equals(temp.get("mc"))
					&& !Base.isNull(temp.get("pm"))) {
				zcMap.put("dypmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			} else if (getZcfTitle()[1].equals(temp.get("mc"))
					&& !Base.isNull(temp.get("pm"))) {
				zcMap.put("zypmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			} else if (getZcfTitle()[2].equals(temp.get("mc"))
					&& !Base.isNull(temp.get("pm"))) {
				zcMap.put("wtpmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			}
		}
		return zcMap;
	}

	/**
	 * ����ѧϰ�ɼ�����
	 * 
	 * @param xh
	 * @return
	 */
	public String getCjpm(String xh) {
		String pm = dao.getCjpm(xh);

		if (Base.isNull(pm) || Base.isNull(xh)) {
			return "0";
		} else {
			return pm;
		}
	}

	/**
	 * ��������ͳ�ƣ���������
	 * 
	 * @param model
	 * @param os
	 */
	public void printKns(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		String xydm = model.getXydm();
		String title = "����ѧԺ��ͥ��������ѧ����Ϣ���ܱ�V1.2";

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ͥ��������ѧ��", 0);

		try {
			excel.printTitle(ws, title, 26, 800);// ����

			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ

			// ��ͷ
			ws.addCell(new Label(0, 1, "���", wcfTytle));
			ws.addCell(new Label(1, 1, "ѧ��", wcfTytle));
			ws.addCell(new Label(2, 1, "����	", wcfTytle));
			ws.addCell(new Label(3, 1, "�Ա�", wcfTytle));
			ws.addCell(new Label(4, 1, "����	", wcfTytle));
			ws.addCell(new Label(5, 1, "�꼶", wcfTytle));
			ws.addCell(new Label(6, 1, "��ҵʱ��", wcfTytle));
			ws.addCell(new Label(7, 1, "רҵ", wcfTytle));
			ws.addCell(new Label(8, 1, "����", wcfTytle));
			ws.addCell(new Label(9, 1, "����Ա", wcfTytle));
			ws.addCell(new Label(10, 1, "��ͥ���", wcfTytle));
			ws.addCell(new Label(11, 1, "�������", wcfTytle));
			ws.addCell(new Label(12, 1, "�����", wcfTytle));
			ws.addCell(new Label(13, 1, "Υ�����	", wcfTytle));
			ws.addCell(new Label(14, 1, "�����������	", wcfTytle));
			ws.addCell(new Label(15, 1, "���ѳ̶�	", wcfTytle));
			ws.addCell(new Label(16, 1, "�������(Ԫ)	", wcfTytle));
			ws.addCell(new Label(17, 1, "����", wcfTytle));
			ws.addCell(new Label(18, 1, "������ϵ�绰	", wcfTytle));
			ws.addCell(new Label(19, 1, "���п���	", wcfTytle));
			ws.addCell(new Label(20, 1, "���֤��	", wcfTytle));
			ws.addCell(new Label(21, 1, "��ͥ��ַ�ʱ�	", wcfTytle));
			ws.addCell(new Label(22, 1, "��ͥ��ַ	", wcfTytle));
			ws.addCell(new Label(23, 1, "��ͥ�̶��绰	", wcfTytle));
			ws.addCell(new Label(24, 1, "��  ע	", wcfTytle));
			ws.addCell(new Label(25, 1, "ѧ���˶�ǩ��", wcfTytle));

			List<HashMap<String, String>> xzData = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> tzData = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> scData = new ArrayList<HashMap<String, String>>();
			// �༭����
			editKnsData(xmdm, xzData, tzData, scData, xydm);
			// �����ݷ���һ��������
			List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
			data.addAll(xzData);
			data.addAll(tzData);
			data.addAll(scData);
			for (int i = 0; i < data.size(); i++) {

				ws
						.addCell(new Label(0, 2 + i, String.valueOf(i + 1),
								wcfTytle));
				ws
						.addCell(new Label(1, 2 + i, data.get(i).get("xh"),
								wcfTytle));
				ws
						.addCell(new Label(2, 2 + i, data.get(i).get("xm"),
								wcfTytle));
				ws
						.addCell(new Label(3, 2 + i, data.get(i).get("xb"),
								wcfTytle));
				ws.addCell(new Label(4, 2 + i, data.get(i).get("mzmc"),
						wcfTytle));
				ws
						.addCell(new Label(5, 2 + i, data.get(i).get("nj"),
								wcfTytle));
				ws.addCell(new Label(6, 2 + i, data.get(i).get("bysj"),
						wcfTytle));
				ws.addCell(new Label(7, 2 + i, data.get(i).get("zymc"),
						wcfTytle));
				ws
						.addCell(new Label(8, 2 + i, data.get(i).get("jg"),
								wcfTytle));
				ws.addCell(new Label(9, 2 + i, data.get(i).get("zzmmmc"),
						wcfTytle));
				ws.addCell(new Label(10, 2 + i, data.get(i).get("jtqkjj"),
						wcfTytle));
				ws.addCell(new Label(11, 2 + i, data.get(i).get("bkqk"),
						wcfTytle));
				ws.addCell(new Label(12, 2 + i, data.get(i).get("hjqk"),
						wcfTytle));
				ws.addCell(new Label(13, 2 + i, data.get(i).get("wjqk"),
						wcfTytle));
				ws.addCell(new Label(14, 2 + i, data.get(i).get("szzqk"),
						wcfTytle));
				ws.addCell(new Label(15, 2 + i, data.get(i).get("xmzzjb"),
						wcfTytle));
				ws.addCell(new Label(16, 2 + i, data.get(i).get("xmzzje"),
						wcfTytle));
				ws.addCell(new Label(17, 2 + i, data.get(i).get("ssbh"),
						wcfTytle));
				ws.addCell(new Label(18, 2 + i, data.get(i).get("lxdh"),
						wcfTytle));
				ws.addCell(new Label(19, 2 + i, data.get(i).get("yhkh"),
						wcfTytle));
				ws.addCell(new Label(20, 2 + i, data.get(i).get("sfzh"),
						wcfTytle));
				ws.addCell(new Label(21, 2 + i, data.get(i).get("jtyb"),
						wcfTytle));
				ws.addCell(new Label(22, 2 + i, data.get(i).get("jtdz"),
						wcfTytle));
				ws.addCell(new Label(23, 2 + i, data.get(i).get("jtdh"),
						wcfTytle));

				if (i < xzData.size()) {
					ws.addCell(new Label(24, 2 + i, "����", wcfTytle));
				} else if (i < xzData.size() + tzData.size()) {
					ws.addCell(new Label(24, 2 + i, "����", wcfTytle));
				} else {
					ws.addCell(new Label(24, 2 + i, "ɾ��", wcfTytle));
				}
				ws.addCell(new Label(25, 2 + i, "", wcfTytle));
			}

			wcfTytle = ExcelMB.getWritableCellFormat(14, false, Alignment.LEFT,
					VerticalAlignment.CENTRE, Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, data.size() + 2, 25, data.size() + 2);
			ws.addCell(new Label(0, data.size() + 2,
					"��ע������ע���������,��ɾ��ѧ����Ϣ�������", wcfTytle));
			ws.mergeCells(0, data.size() + 3, 25, data.size() + 4);
			// ��ѧ�ڸ�������������ͳ��
			List<HashMap<String, String>> sxqData = dao.getSxqTj(xmdm, "sxq",
					xydm);
			int count = 0;
			StringBuilder str = new StringBuilder();
			for (HashMap<String, String> map : sxqData) {
				str.append(map.get("fjmc"));
				str.append(map.get("count"));
				str.append("��");
				str.append(",");
				count += Integer.parseInt(map.get("count"));
			}
			str.deleteCharAt(str.length() - 1);// ɾ�����һ������
			// ��ѧ�ڸ�������������ͳ��
			List<HashMap<String, String>> bxqData = dao.getSxqTj(xmdm, "bxq",
					xydm);
			int bxqCount = 0;
			StringBuilder bxqStr = new StringBuilder();
			for (HashMap<String, String> map : bxqData) {
				bxqStr.append(map.get("fjmc"));
				bxqStr.append("ѧ�� ");
				bxqStr.append(map.get("count"));
				bxqStr.append(" ��");
				bxqStr.append(",");
				bxqCount += Integer.parseInt(map.get("count"));
			}

			bxqStr.deleteCharAt(str.length() - 1);// ɾ�����һ������
			// ͳ�Ʋ����ַ���ƴ��
			StringBuilder temp = new StringBuilder();
			temp.append("��ϵ��ѧ��( ");
			if ("01".equals(Base.currXq)) {
				temp.append(Base.currNd);
				temp.append(" ��");
			} else {
				temp.append(Integer.parseInt(Base.currNd) - 1);
				temp.append(" ��");
			}
			temp.append("��)��ͥ��������ѧ����");
			temp.append(count);
			temp.append(" ��,����");
			temp.append(str);
			temp.append(" ���������ȼ� ");
			temp.append(tzData.size());
			temp.append(" ��,���� ");
			temp.append(xzData.size());
			temp.append(" �ˣ�ɾ�� ");
			temp.append(scData.size());
			temp.append(" �ˣ���ѧ�ڼ�ͥ������������ ");
			temp.append(xzData.size() + tzData.size());
			temp.append(" ��,����");
			temp.append(bxqStr);
			temp.append(" ��");
			ws
					.addCell(new Label(0, data.size() + 3, temp.toString(),
							wcfTytle));
			ws.mergeCells(0, data.size() + 5, 25, data.size() + 5);// ���һ�кϲ���Ԫ��
			ws
					.addCell(new Label(
							0,
							data.size() + 5,
							"�����ˣ�_____________________   "
									+ "Ժ��ϵ��ѧ�����������飨������ǩ���Ӹ�Ժ<ϵ>�£���_______________________       "
									+ GetTime.getNowTime(), wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��������ͳ�ƣ�����ɫͨ��
	 * 
	 * @param model
	 * @param os
	 */
	public void printLstd(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		int hjje = 0;
		String title = "����ѧԺ������ѧ����ɫͨ��������ǼǱ�V1";
		List<String[]> data = dao.getLstdData(xmdm, model.getXydm());
		String xymc = dao.getOneValue("zxbz_xxbmdm", "bmmc", "bmdm", model
				.getXydm());
		;

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ɫͨ��", 0);

		try {
			excel.printTitle(ws, title, 9, 800);// ����
			ws.mergeCells(0, 1, 4, 1);// �ڶ��еĺϲ�Ԫ��
			ws.mergeCells(5, 1, 8, 1);// �ڶ��еĺϲ�Ԫ��
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.addCell(new Label(0, 1, xymc + " Ժϵ", wcfTytle));
			ws.addCell(new Label(5, 1, "���ʱ�䣺 " + GetTime.getNowTime(),
					wcfTytle));
			// ��ͷ
			ws.addCell(new Label(0, 2, "��  ��", wcfTytle));
			ws.addCell(new Label(1, 2, "��  ��", wcfTytle));
			ws.addCell(new Label(2, 2, "��  ��", wcfTytle));
			ws.addCell(new Label(3, 2, "ϵ  ��", wcfTytle));
			ws.addCell(new Label(4, 2, "��  ��", wcfTytle));
			ws.addCell(new Label(5, 2, "��  ��", wcfTytle));
			ws.addCell(new Label(6, 2, "��ͥ���", wcfTytle));
			ws.addCell(new Label(7, 2, "�������", wcfTytle));
			ws.addCell(new Label(8, 2, "��ͥ��Ա���", wcfTytle));

			// ѭ���������
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length - 1; j++) {
					ws
							.addCell(new Label(j, 3 + i, data.get(i)[j + 1],
									wcfTytle));
				}
				// �������ͳ��
				hjje += Integer.parseInt(data.get(i)[data.get(i).length - 1]);
				/*
				 * ��ͥ��Ա���
				 */
				model.setXh(data.get(i)[0]);
				List<HashMap<String, String>> cyList = getJtgxList(model);
				StringBuilder cyString = new StringBuilder();
				for (HashMap<String, String> map : cyList) {
					cyString.append("������");
					cyString.append(Base.isNull(map.get("cyxm")) ? " " : map
							.get("cyxm"));
					cyString.append("����ϵ��");
					cyString.append(Base.isNull(map.get("cygx")) ? " " : map
							.get("cygx"));
					cyString.append(",�绰��");
					cyString.append(Base.isNull(map.get("cydh")) ? " " : map
							.get("cydh"));
					cyString.append("����λ��");
					cyString.append(Base.isNull(map.get("cygzdw")) ? " " : map
							.get("cygzdw"));
					cyString
							.append(";                                                                            ");
				}
				WritableCellFormat leftStyle = ExcelMB.getWritableCellFormat(
						14, false, Alignment.LEFT, VerticalAlignment.CENTRE,
						Border.ALL);// ��Ԫ����ʽ
				ws.addCell(new Label(8, 3 + i, cyString.toString(), leftStyle));

			}
			/*
			 * int ����int ����int �н�����int �н��� ��ע���ֺϲ�
			 */
			ws.mergeCells(0, data.size() + 3, 0, data.size() + 4);
			ws.mergeCells(1, data.size() + 3, 8, data.size() + 3);
			ws.mergeCells(1, data.size() + 4, 8, data.size() + 4);

			// ��ע����
			String bz1 = "1����Ժ��ϵ��Ӧ������鿴�й�֤�����ϣ��˽��ʵ�й������������������ȡ�";
			String bz2 = "2����������һ��Ӧд���ѽ����ã������̲Ĵ���ѡ�ס�޷ѡ�ѧ�ѣ��Լ����뻺����";
			ws.addCell(new Label(0, data.size() + 3, "��ע", wcfTytle));

			wcfTytle = ExcelMB.getWritableCellFormat(14, false, Alignment.LEFT,
					VerticalAlignment.CENTRE, Border.ALL);// ��Ԫ����ʽ

			ws.addCell(new Label(1, data.size() + 3, bz1, wcfTytle));
			ws.addCell(new Label(1, data.size() + 4, bz2, wcfTytle));

			wcfTytle = ExcelMB.getWritableCellFormat(14, false, Alignment.LEFT,
					VerticalAlignment.CENTRE, Border.NONE);// ��Ԫ����ʽ
			ws.mergeCells(0, data.size() + 5, 8, data.size() + 7);
			StringBuilder tjnr = new StringBuilder();

			tjnr.append("��Ժ��ϵ���ܼ�������ɫͨ��");
			tjnr.append(data.size());
			tjnr.append("�ˣ��������(��д)");
			tjnr.append(0 == hjje ? "��Ԫ" : MoneyFormat.format(String
					.valueOf(hjje)));
			tjnr.append(",Сд");
			tjnr.append(hjje);
			tjnr.append("Ԫ");

			ws
					.addCell(new Label(0, data.size() + 5, tjnr.toString(),
							wcfTytle));

			ws.mergeCells(0, data.size() + 8, 8, data.size() + 10);
			tjnr = new StringBuilder();

			tjnr.append("���죺_______________");
			tjnr.append("            ");
			tjnr.append(" Ժϵ���������飨������ǩ���Ӹ�ϵ�£���");
			tjnr.append("_________________");

			ws
					.addCell(new Label(0, data.size() + 8, tjnr.toString(),
							wcfTytle));

			// ws.mergeCells(0, data.size()+11, 8, data.size()+13);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��������ͳ�ƣ���������־��ѧ��
	 * 
	 * @param model
	 * @param os
	 */
	public void printLzjxj(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		List<String[]> data = dao.getLzjxjData(xmdm, model.getXn(),
				getZcfTitle());

		StringBuilder title = new StringBuilder();
		title.append("����ѧԺ");
		title.append(model.getXn().substring(0, 4));
		title.append("��");
		title.append(model.getXn().substring(5, 9));
		title.append("ѧ��ȹ�����־��ѧ�����");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������־��ѧ��", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// ����
			ws.mergeCells(0, 1, 9, 1);// �ڶ��еĺϲ�Ԫ��
			ws.mergeCells(10, 1, 18, 1);// �ڶ��еĺϲ�Ԫ��
			WritableCellFormat leftStyle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ
			WritableCellFormat rightStyle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.RIGHT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ

			ws.addCell(new Label(0, 1, "ѧУ������ѧԺ�����£�", leftStyle));
			ws.addCell(new Label(10, 1, "������ڣ�" + GetTime.getNowTime(),
					rightStyle));

			/*
			 * �������
			 */
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "����", wcfTytle));
			ws.addCell(new Label(2, 2, "���֤��", wcfTytle));
			ws.addCell(new Label(3, 2, "Ժϵ", wcfTytle));
			ws.addCell(new Label(4, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(5, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(6, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(7, 2, "����", wcfTytle));
			ws.addCell(new Label(8, 2, "��ѧ����", wcfTytle));
			ws.addCell(new Label(9, 2, "ְ��", wcfTytle));
			ws.addCell(new Label(10, 2, "���п���", wcfTytle));
			ws.addCell(new Label(11, 2, "���ѳ̶�", wcfTytle));
			ws.addCell(new Label(12, 2, "����רҵ����", wcfTytle));
			ws.addCell(new Label(13, 2, "�ۺϲ�������", wcfTytle));
			ws.addCell(new Label(14, 2, "�ۺϲ�������", wcfTytle));
			ws.addCell(new Label(15, 2, "��������", wcfTytle));
			ws.addCell(new Label(16, 2, "��������", wcfTytle));
			ws.addCell(new Label(17, 2, "��������", wcfTytle));
			ws.addCell(new Label(18, 2, "�����", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��������ͳ�ƣ���ѧ������ѧ��
	 * 
	 * @param model
	 * @param os
	 */
	public void printHjxf(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		String title = "����ѧԺѧ������ѧ�����ͳ�Ʊ�V1";
		List<String[]> data = dao.getHjxfData(xmdm);
		int dkrs = 0;// ��������
		int jmrs = 0;// ��������
		int hjrs = 0;// ��������
		int dkje = 0;// ������
		int hjje = 0;// �������
		int jmje = 0;// ������

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ������ѧ��", 0);

		try {
			excel.printTitle(ws, title.toString(), 12, 800);
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			/*
			 * ��һ�����кϲ���Ԫ��
			 */
			for (int i = 0; i < 9; i++) {
				ws.mergeCells(i, 1, i, 2);
			}
			ws.mergeCells(9, 1, 11, 1);

			/*
			 * ��ͷ
			 */
			ws.addCell(new Label(0, 1, "Ժϵ", wcfTytle));
			ws.addCell(new Label(1, 1, "����", wcfTytle));
			ws.addCell(new Label(2, 1, "�Ա�", wcfTytle));
			ws.addCell(new Label(3, 1, "�꼶רҵ�༶", wcfTytle));
			ws.addCell(new Label(4, 1, "������Դ", wcfTytle));
			ws.addCell(new Label(5, 1, "��ǰ�ۼ�Ƿ�ѣ�Ԫ��", wcfTytle));
			ws.addCell(new Label(6, 1, "��ͥ���", wcfTytle));
			ws.addCell(new Label(7, 1, "����", wcfTytle));
			ws.addCell(new Label(8, 1, "������ϵ�绰", wcfTytle));
			ws.addCell(new Label(9, 1, "������𼰽��", wcfTytle));
			ws.addCell(new Label(9, 2, "�������Ԫ��", wcfTytle));
			ws.addCell(new Label(10, 2, "ѧ�Ѽ����Ԫ��", wcfTytle));
			ws.addCell(new Label(11, 2, "ѧ�ѻ�����Ԫ��", wcfTytle));

			/*
			 * ѭ���������
			 */
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}

				if (Integer.parseInt(data.get(i)[9]) > 0) {
					dkrs++;
					dkje += Integer.parseInt(data.get(i)[9]);
				}
				if (Integer.parseInt(data.get(i)[10]) > 0) {
					jmrs++;
					jmje += Integer.parseInt(data.get(i)[10]);
				}
				if (Integer.parseInt(data.get(i)[11]) > 0) {
					hjrs++;
					hjje += Integer.parseInt(data.get(i)[11]);
				}
			}

			/*
			 * ͳ�Ʋ��ֺϲ���Ԫ��
			 */
			for (int i = 0; i < 4; i++) {
				ws.mergeCells(0, data.size() + 3 + i, 2, data.size() + 3 + i);
				ws.mergeCells(3, data.size() + 3 + i, 5, data.size() + 3 + i);
				ws.mergeCells(7, data.size() + 3 + i, 11, data.size() + 3 + i);
			}
			/*
			 * ͳ������
			 */
			ws.addCell(new Label(0, data.size() + 3, "�����������ˣ�", wcfTytle));
			ws.addCell(new Label(3, data.size() + 3, String.valueOf(dkrs),
					wcfTytle));
			ws.addCell(new Label(6, data.size() + 3, "�����Ԫ��", wcfTytle));
			ws.addCell(new Label(7, data.size() + 3, String.valueOf(dkje),
					wcfTytle));

			ws.addCell(new Label(0, data.size() + 4, "������������)", wcfTytle));
			ws.addCell(new Label(3, data.size() + 4, String.valueOf(jmrs),
					wcfTytle));
			ws.addCell(new Label(6, data.size() + 4, "�����Ԫ��", wcfTytle));
			ws.addCell(new Label(7, data.size() + 4, String.valueOf(jmje),
					wcfTytle));

			ws.addCell(new Label(0, data.size() + 5, "�����������ˣ�", wcfTytle));
			ws.addCell(new Label(3, data.size() + 5, String.valueOf(hjrs),
					wcfTytle));
			ws.addCell(new Label(6, data.size() + 5, "������Ԫ��", wcfTytle));
			ws.addCell(new Label(7, data.size() + 5, String.valueOf(hjje),
					wcfTytle));

			ws.addCell(new Label(0, data.size() + 6, "���������ˣ�", wcfTytle));
			ws.addCell(new Label(3, data.size() + 6, String
					.valueOf(data.size()), wcfTytle));
			ws.addCell(new Label(6, data.size() + 6, "�ܽ�Ԫ��", wcfTytle));
			ws.addCell(new Label(7, data.size() + 6, String.valueOf(dkje + jmje
					+ hjje), wcfTytle));

			/*
			 * ��β
			 */
			ws.mergeCells(0, data.size() + 8, 2, data.size() + 8);
			ws.mergeCells(5, data.size() + 8, 6, data.size() + 8);
			ws.mergeCells(8, data.size() + 8, 11, data.size() + 8);
			ws.addCell(new Label(0, data.size() + 8, "�����ˣ�", wcfTytle));
			ws.addCell(new Label(5, data.size() + 8, "Ժϵ���������飨������ǩ���Ӹ�ϵ�£���",
					wcfTytle));
			ws.addCell(new Label(8, data.size() + 8, GetTime.getNowTime(),
					wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ����--ͳ��--������ѧ��
	 * 
	 * @param model
	 * @param os
	 */
	public void printGjzxj(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		String xn = model.getXn();
		String xq = model.getXq();
		String xqmc = DAO.getInstance()
				.getOneValue("xqdzb", "xqmc", "xqdm", xq);
		String title = "��У������ѧ��������ܱ�";
		String[] knjb = dao.getKnjbData();
		List<String[]> data = dao.getGjzxjData(xmdm, xn, xq);

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������ѧ��", 0);

		StringBuilder sb = new StringBuilder();// ������ͷ
		sb.append(xn);
		sb.append("          ѧ��           ");
		sb.append(xqmc);
		sb.append("          ѧ��           ");
		sb.append("           ��λ����");

		try {
			excel.printTitle(ws, title,
					null == knjb ? 6 : 8 + 12 * knjb.length, 800);
			ws.mergeCells(0, 1, null == knjb ? 5 : 7 + 12 * knjb.length, 1);// �ڶ��кϲ���Ԫ��
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ

			ws.mergeCells(0, 2, 0, 4);// Ժϵ�ϲ�
			ws.mergeCells(1, 2, 1, 4);// ���ϲ�
			ws.mergeCells(5, 2, 5, 4);// �����������ϲ�
			ws.mergeCells(2, 3, 2, 4);// С�ƺϲ�
			ws.mergeCells(3, 3, 3, 4);// ���ƺϲ�
			ws.mergeCells(4, 3, 4, 4);// ר�ƺϲ�
			ws.mergeCells(2, 2, 4, 2);// ��ר��ѧ�����ϲ�

			ws.addCell(new Label(0, 1, sb.toString(), wcfTytle));
			ws.addCell(new Label(0, 2, "Ժ��ϵ��", wcfTytle));
			ws.addCell(new Label(1, 2, "���", wcfTytle));
			ws.addCell(new Label(2, 2, "ѧУȫ���Ʊ���ר��ѧ����", wcfTytle));
			ws.addCell(new Label(2, 3, "С��", wcfTytle));
			ws.addCell(new Label(3, 3, "����", wcfTytle));
			ws.addCell(new Label(4, 3, "ר��", wcfTytle));
			ws.addCell(new Label(5, 2, "����������", wcfTytle));

			if (null != knjb && knjb.length > 0) {

				for (int i = 0; i < knjb.length; i++) {
					int n = i == 0 ? 0 : 12;

					ws.mergeCells(6 + n * i, 2, 17 + n * i, 2);
					ws.addCell(new Label(6 + n * i, 2, knjb[i] + "ѧ������",
							wcfTytle));
					// �ϼƲ���
					ws.mergeCells(6 + n * i, 3, 6 + n * i, 4);
					ws.addCell(new Label(6 + n * i, 3, "�ϼ�", wcfTytle));
					// ռѧ����������
					ws.mergeCells(7 + n * i, 3, 7 + n * i, 4);
					ws.addCell(new Label(7 + n * i, 3,
							knjb[i] + "ѧ��ռ��У��ר��������%", wcfTytle));
					// ���Ʋ���
					ws.mergeCells(8 + n * i, 3, 13 + n * i, 3);
					ws.addCell(new Label(8 + n * i, 3, "����", wcfTytle));
					// ר�Ʋ���
					ws.mergeCells(14 + n * i, 3, 17 + n * i, 3);
					ws.addCell(new Label(14 + n * i, 3, "ר��", wcfTytle));

					ws.addCell(new Label(8 + n * i, 4, "С��", wcfTytle));
					ws.addCell(new Label(9 + n * i, 4, "һ�꼶", wcfTytle));
					ws.addCell(new Label(10 + n * i, 4, "���꼶", wcfTytle));
					ws.addCell(new Label(11 + n * i, 4, "���꼶", wcfTytle));
					ws.addCell(new Label(12 + n * i, 4, "���꼶", wcfTytle));
					ws.addCell(new Label(13 + n * i, 4, "", wcfTytle));
					ws.addCell(new Label(14 + n * i, 4, "С��", wcfTytle));
					ws.addCell(new Label(15 + n * i, 4, "һ�꼶", wcfTytle));
					ws.addCell(new Label(16 + n * i, 4, "���꼶", wcfTytle));
					ws.addCell(new Label(17 + n * i, 4, "���꼶", wcfTytle));

				}

				ws.mergeCells(6 + 12 * knjb.length, 2, 6 + 12 * knjb.length, 4);
				ws.mergeCells(7 + 12 * knjb.length, 2, 7 + 12 * knjb.length, 4);

				ws.addCell(new Label(6 + 12 * knjb.length, 2, "�ܼ�", wcfTytle));
				ws.addCell(new Label(7 + 12 * knjb.length, 2, "����ѧ���Ƿ񾭹�ʾ",
						wcfTytle));

				int row = 6;
				for (int i = 0; i < data.size(); i++) {
					ws.mergeCells(0, 5 + i * 3, 0, 7 + i * 3);// ѧԺ�ϲ���Ԫ��
					ws
							.addCell(new Label(0, 5 + i * 3, data.get(i)[0],
									wcfTytle));
					ws.addCell(new Label(1, 5 + i * 3, "�ϼ�", wcfTytle));

					HashMap<String, String> xsData = dao.getLsXs(
							data.get(i)[0], "xs");
					HashMap<String, String> lsData = dao.getLsXs(
							data.get(i)[0], "ls");
					ws.addCell(new Label(1, 6 + i * 3, "����", wcfTytle));
					ws.addCell(new Label(1, 7 + i * 3, "����", wcfTytle));

					ws.addCell(new Label(2, 6 + i * 3, lsData.get("xj"),
							wcfTytle));
					ws.addCell(new Label(3, 6 + i * 3, lsData.get("bks"),
							wcfTytle));
					ws.addCell(new Label(4, 6 + i * 3, lsData.get("zks"),
							wcfTytle));
					ws.addCell(new Label(5, 6 + i * 3, lsData.get("sqzxj"),
							wcfTytle));

					ws.addCell(new Label(2, 7 + i * 3, xsData.get("xj"),
							wcfTytle));
					ws.addCell(new Label(3, 7 + i * 3, xsData.get("bks"),
							wcfTytle));
					ws.addCell(new Label(4, 7 + i * 3, xsData.get("zks"),
							wcfTytle));
					ws.addCell(new Label(5, 7 + i * 3, xsData.get("sqzxj"),
							wcfTytle));

					for (int j = 1; j < 5; j++) {
						ws.addCell(new Label(j + 1, 5 + i * 3, data.get(i)[j],
								wcfTytle));
					}

					int lszj = 0;
					int xszj = 0;

					for (int d = 0; d < knjb.length; d++) {

						int n = d == 0 ? 0 : 12;
						int e = d == 0 ? 0 : 11;

						int bkxj = Integer.valueOf(data.get(i)[9 + e])
								+ Integer.valueOf(data.get(i)[10 + e])
								+ Integer.valueOf(data.get(i)[11 + e]);
						int zkxj = Integer.valueOf(data.get(i)[14 + e])
								+ Integer.valueOf(data.get(i)[15 + e]);
						String lsbl = "0".equals(lsData.get("xj")) ? "0"
								: String.valueOf((bkxj + zkxj)
										/ Integer.parseInt(lsData.get("xj"))
										* 100);
						String xsbl = "0".equals(xsData.get("xj")) ? "0"
								: String.valueOf((Integer
										.valueOf(data.get(i)[8 + e]) + Integer
										.valueOf(data.get(i)[13 + e]))
										/ Integer.parseInt(xsData.get("xj"))
										* 100);

						ws.addCell(new Label(6 + n * d, 5 + i * 3,
								data.get(i)[5 + e], wcfTytle));// �ϼ�-�ϼ�
						ws.addCell(new Label(7 + n * d, 5 + i * 3,
								data.get(i)[6 + e], wcfTytle));// ����
						ws.addCell(new Label(7 + n * d, 6 + i * 3, lsbl,
								wcfTytle));// ��������
						ws.addCell(new Label(7 + n * d, 7 + i * 3, xsbl,
								wcfTytle));// ��������

						ws.addCell(new Label(6 + n * d, 6 + i * 3, String
								.valueOf(bkxj + zkxj), wcfTytle));// �����ϼ�
						ws
								.addCell(new Label(
										6 + n * d,
										7 + i * 3,
										String
												.valueOf(Integer.valueOf(data
														.get(i)[8 + e])
														+ Integer
																.valueOf(data
																		.get(i)[13 + e])),
										wcfTytle));// �����ϼ�

						lszj += bkxj + zkxj;
						xszj += Integer.valueOf(data.get(i)[8 + e])
								+ Integer.valueOf(data.get(i)[13 + e]);

						// ��������
						ws.addCell(new Label(8 + n * d, 5 + i * 3,
								data.get(i)[7 + e], wcfTytle));
						ws.addCell(new Label(8 + n * d, 7 + i * 3,
								data.get(i)[8 + e], wcfTytle));// ��������С��
						ws.addCell(new Label(9 + n * d, 5 + i * 3,
								data.get(i)[8 + e], wcfTytle));// ����һ�꼶
						ws.addCell(new Label(9 + n * d, 7 + i * 3,
								data.get(i)[8 + e], wcfTytle));// ��������һ�꼶
						// ��������
						ws.addCell(new Label(8 + n * d, 6 + i * 3, String
								.valueOf(bkxj), wcfTytle));// ��������С��

						ws.addCell(new Label(10 + n * d, 5 + i * 3,
								data.get(i)[9 + e], wcfTytle));
						ws.addCell(new Label(11 + n * d, 5 + i * 3,
								data.get(i)[10 + e], wcfTytle));
						ws.addCell(new Label(12 + n * d, 5 + i * 3,
								data.get(i)[11 + e], wcfTytle));
						ws.addCell(new Label(10 + n * d, 6 + i * 3,
								data.get(i)[9 + e], wcfTytle));
						ws.addCell(new Label(11 + n * d, 6 + i * 3,
								data.get(i)[10 + e], wcfTytle));
						ws.addCell(new Label(12 + n * d, 6 + i * 3,
								data.get(i)[11 + e], wcfTytle));

						ws.addCell(new Label(13 + n * d, 5 + i * 3, "",
								wcfTytle));// �հ׵�Ԫ��

						// ר������
						ws.addCell(new Label(14 + n * d, 5 + i * 3,
								data.get(i)[12 + e], wcfTytle));
						ws.addCell(new Label(14 + n * d, 7 + i * 3,
								data.get(i)[13 + e], wcfTytle));// ר������С��
						ws.addCell(new Label(15 + n * d, 5 + i * 3,
								data.get(i)[13 + e], wcfTytle));// ר��һ�꼶
						ws.addCell(new Label(15 + n * d, 7 + i * 3,
								data.get(i)[13 + e], wcfTytle));// ר������һ�꼶

						// ר������
						ws.addCell(new Label(14 + n * d, 6 + i * 3, String
								.valueOf(zkxj), wcfTytle));

						ws.addCell(new Label(16 + n * d, 5 + i * 3,
								data.get(i)[14 + e], wcfTytle));
						ws.addCell(new Label(17 + n * d, 5 + i * 3,
								data.get(i)[15 + e], wcfTytle));
						ws.addCell(new Label(16 + n * d, 6 + i * 3,
								data.get(i)[14 + e], wcfTytle));
						ws.addCell(new Label(17 + n * d, 6 + i * 3,
								data.get(i)[15 + e], wcfTytle));

					}
					ws.addCell(new Label(knjb.length * 12 + 6, 5 + i * 3, data
							.get(i)[data.get(i).length - 1], wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 6, 6 + i * 3,
							String.valueOf(lszj), wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 6, 7 + i * 3,
							String.valueOf(xszj), wcfTytle));

					ws.addCell(new Label(knjb.length * 12 + 7, 5 + i * 3, "",
							wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 7, 6 + i * 3, "",
							wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 7, 7 + i * 3, "",
							wcfTytle));

					row = 7 + i * 3;

				}

				ws.mergeCells(0, row + 1, 7 + 12 * knjb.length, row + 1);
				ws.mergeCells(0, row + 2, 7 + 12 * knjb.length, row + 2);
				ws.mergeCells(0, row + 3, 7 + 12 * knjb.length, row + 3);

				wcfTytle = ExcelMB.getWritableCellFormat(14, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, Border.ALL);// ��Ԫ����ʽ

				ws.addCell(new Label(0, row + 1, "�����ˣ�						", wcfTytle));
				ws.addCell(new Label(0, row + 2, "Ժϵǩ�£�						", wcfTytle));
				ws.addCell(new Label(0, row + 3,
						"�ʱ�䣺" + GetTime.getNowTime(), wcfTytle));
			} else {
				throw new Exception("������������ά�����Ѽ��𣡣���");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ��������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expInfo(XszzTyForm model, OutputStream os) throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = model.getXmdm();
		model.setPkValue(xmdm);
		HashMap<String, String> xmInfo = getXmxgInfo(model);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		model.setXmb(xmb);
		// ��Ŀ����
		String title = xmInfo.get("xmmc");
		// �����ֶ�
		String[] outValue = getOutValue(getTableZd(xmb));

		// ���ı���
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(xmb,
				outValue, null);

		// ѧ��λ��
		int xhNun = 0;

		if (outValue != null && outValue.length > 0) {
			for (int i = 0; i < outValue.length; i++) {
				if ("xh".equalsIgnoreCase(outValue[i])) {
					xhNun = i;
					break;
				}
			}
		}
		// ��Ӹ����ֶ�
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("en", "xmmc");
		map.put("cn", "��Ŀ����");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "����");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "xb");
		map.put("cn", "�Ա�");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "nj");
		map.put("cn", "�꼶");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "xymc");
		map.put("cn", "Ժϵ����");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "zymc");
		map.put("cn", "רҵ����");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "bjmc");
		map.put("cn", "�༶����");
		topTr.add(map);

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			map = new HashMap<String, String>();
			map.put("en", "bjpm");
			map.put("cn", "�༶����");
			topTr.add(map);
		}

		// ������������
		ArrayList<String[]> list = getExpList(model, outValue);

		List<String> xhList = new ArrayList<String>();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				// ��������
				String[] jcData = list.get(i);

				// ѧ��
				String xh = jcData[xhNun];

				xhList.add(xh);
			}
		}

		List<HashMap<String, String>> expXsList = dao
				.getExpXsList(xmdm, xhList);

		// ��������
		ArrayList<String[]> expList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				// ��������
				String[] jcData = list.get(i);

				// ѧ��
				String xh = jcData[xhNun];

				// ��������
				String[] expData = new String[jcData.length + 7];
				if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
					expData = new String[jcData.length + 8];
				}
				if (jcData != null && jcData.length > 0) {
					for (int j = 0; j < jcData.length; j++) {
						expData[j] = jcData[j];
					}
				}

				if (expXsList != null && expXsList.size() > 0) {
					for (int j = 0; j < expXsList.size(); j++) {
						HashMap<String, String> xsInfo = expXsList.get(j);
						if (xh.equalsIgnoreCase(xsInfo.get("xh"))) {
							expData[jcData.length] = title;
							expData[jcData.length + 1] = expXsList.get(j).get(
									"xm");
							expData[jcData.length + 2] = expXsList.get(j).get(
									"xb");
							expData[jcData.length + 3] = expXsList.get(j).get(
									"nj");
							expData[jcData.length + 4] = expXsList.get(j).get(
									"xymc");
							expData[jcData.length + 5] = expXsList.get(j).get(
									"zymc");
							expData[jcData.length + 6] = expXsList.get(j).get(
									"bjmc");
							if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
								expData[jcData.length + 7] = expXsList.get(j)
										.get("zcbjpm");
							}
							expList.add(expData);

							break;
						}
					}
				}
			}
		}

		expXszzData(title, topTr, expList, os);
	}

	/**
	 * ��õ��������б�
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getExpList(XszzTyForm model, String[] outValue) {
		return dao.getExpList(model, outValue);
	}

	/**
	 * �Ƿ���޸�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean canEdit(XszzTyForm model) {

		boolean flag = false;

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��˼���
		String shjb = model.getShjb();
		// ѧ��
		String xh = model.getXh();
		// ����ʱ��
		String sqsj = model.getSqsj();
		// ��Ŀ��
		String xmb = model.getXmb();

		// ����
		String pk = "xh||sqsj||xmdm";
		// ����ֵ��
		String pkValue = xh + sqsj + xmdm;
		// ����ֶ�
		String[] colList = new String[] { "shzt1", "shzt2", "shzt3" };

		// ��Ŀ�����Ϣ
		HashMap<String, String> map = getXszzInfo(xmb, pk, pkValue, colList);

		String shzt1 = map.get("shzt1");
		String shzt2 = map.get("shzt2");
		String shzt3 = map.get("shzt3");

		if ("�������".equalsIgnoreCase(shjb)) {
			flag = true;
		} else if ("һ�����".equalsIgnoreCase(shjb)) {
			if ("δ���".equalsIgnoreCase(shzt1)) {
				flag = true;
			}
		} else if ("�������".equalsIgnoreCase(shjb)) {
			if ("δ���".equalsIgnoreCase(shzt1) && "δ���".equalsIgnoreCase(shzt2)) {
				flag = true;
			}
		} else if ("�������".equalsIgnoreCase(shjb)) {
			if ("δ���".equalsIgnoreCase(shzt1) && "δ���".equalsIgnoreCase(shzt2)
					&& "δ���".equalsIgnoreCase(shzt3)) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * @param outValue
	 * @return
	 */
	public String[] getOutValue(String[] outValue) {

		String[] outZd = null;

		if (outValue != null && outValue.length > 0) {

			int n = 0;

			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					n++;
				}
			}

			outZd = new String[n];

			int m = 0;
			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					outZd[m] = outValue[i];
					m++;
				}
			}
		}
		return outZd;
	}

	/**
	 * ���ظ��������Ѽ���
	 * 
	 * @param shjb
	 * @param sqzq
	 * @param xn
	 * @param xh
	 * @param xq
	 * @return
	 */

	public String getKnjbForXh(String shjb, String sqzq, String xn, String xh,
			String xq) {

		return dao.getKnjbForXh(shjb, sqzq, xn, xh, xq);

	}

	/**
	 * ����������ͳ��
	 * 
	 * @param xmdm
	 * @param xzData
	 * @param tzData
	 * @param scData
	 * @param xydm
	 */
	private void editKnsData(String xmdm, List<HashMap<String, String>> xzData,
			List<HashMap<String, String>> tzData,
			List<HashMap<String, String>> scData, String xydm) {
		List<HashMap<String, String>> data = dao.getKnsData(xmdm, xydm);
		String sqzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", xmdm);
		List<HashMap<String, String>> sxqData = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> bxqData = new ArrayList<HashMap<String, String>>();

		String xn = Base.currXn;
		String xq = Base.currXq;

		HashMap<String, String> xnxqMap = new HashMap<String, String>();
		xnxqMap.put("xn", xn);
		xnxqMap.put("xq", xq);
		xnxqMap = DAO.getInstance().getBeforeXq(xnxqMap);

		String beforeXq = xnxqMap.get("xq");
		String beforeXn = xnxqMap.get("xn");

		// ��������Ϊѧ��
		if ("ѧ��".equals(sqzq)) {
			for (HashMap<String, String> map : data) {
				if (xn.equals(map.get("xn")) && xq.equals(map.get("xq"))) {
					bxqData.add(map);// ��ӵ���ѧ�����ݼ�
				}

				if (beforeXq.equals(map.get("xq"))
						&& beforeXn.equals(map.get("xn"))) {
					sxqData.add(map);// ��ѧ�����ݼ�
				}
			}
		} else {// ��������Ϊѧ��
			beforeXn = Base.beforXn;
			for (HashMap<String, String> map : data) {
				if (xn.equals(map.get("xn"))) {
					bxqData.add(map);// ��ӵ���ѧ�����ݼ�
				}

				if (beforeXn.equals(map.get("xn"))) {
					sxqData.add(map);// ��ѧ�����ݼ�
				}
			}
		}

		data.removeAll(bxqData);// ��data���ݼ�ɾ����ѧ������

		// ��ѧ�����ݣ�������dataѧ����������������Ϊ����
		for (HashMap<String, String> map : bxqData) {
			String xh = map.get("xh");
			if (!isExists(data, xh, "xh")) {
				xzData.add(map);
			} else {
				tzData.add(map);
			}

		}

		// data���ݼ����ѧ�����ڱ�ѧ�����ݼ���ɾ��
		for (HashMap<String, String> map : data) {
			String xh = map.get("xh");
			if (!isExists(bxqData, xh, "xh")) {
				scData.add(map);
			}

		}
	}

	/**
	 * �жϼ������Ƿ����ĳ��ֵ
	 * 
	 * @param data
	 * @param value
	 * @param key
	 * @return
	 */
	public boolean isExists(List<HashMap<String, String>> data, String value,
			String key) {
		for (HashMap<String, String> map : data) {
			if (value.equals(map.get(key))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �Ͷ��ڼ��ۼƲ��Ƹ���޿�Ŀ��
	 * 
	 * @param xh
	 * @return
	 */
	public String getBjgs(String xh) {

		return dao.getBjgs(xh);
	}

	/**
	 * �Ƿ�Υ��
	 * 
	 * @param model
	 * @return
	 */
	public String isWj(XszzTyForm model) {
		// ��Ŀ�����Ϣ
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		return dao.isWj(model, map);
	}

	/**
	 * ���������ϰ������������
	 * 
	 * @param model
	 * @return
	 */
	public String sxnDyf(XszzTyForm model) {
		return dao.sxnDyf(model);
	}

	/**
	 * ��ѯѧ���ɼ���Ϣ
	 */
	public List<HashMap<String, String>> getXscjList(String xxdm, String xmb,
			XszzTyForm myform) {
		List<HashMap<String, String>> list = null;
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			// ���ϴ�ѧ������־��ѧ��
			list = dao.getXscjList(myform);
		}

		return list;
	}

	/**
	 * �������
	 * 
	 * @param dm
	 * @param ids
	 * @return
	 */
	public List<HashMap<String, String>> getQxmc(String[] dm, String[] ids) {
		StringBuilder sql = new StringBuilder();
		for (int i = 0; i < dm.length; i++) {
			sql.append("select qxmc,'").append(ids[i]).append(
					"' id from dmk_qx where qxdm=?");
			if (i != dm.length - 1) {
				sql.append(" union all ");
			}
		}

		return CommonQueryDAO.commonQueryforList("", "", dm, new String[] {
				"qxmc", "id" }, sql.toString());
	}

	public HashMap<String, String> getXxcjZcxx(String xxdm, String xmb,
			HashMap<String, String> map) {
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// ���ϴ�ѧ
			if ("xszz_gjjxjb".equalsIgnoreCase(xmb)) {// ���ҽ�ѧ��
				// ��ѧ����޿γ���
				// ������
				// ������
				map.putAll(dao.getXnbxkxx(map));
				// רҵ�ɼ�����
				// ��ʱ��֪�����ȡ

				// �ۺϿ����ɼ�
				// �ۺϿ����ɼ�����
				map.putAll(dao.getZhszcpxx(map));
			}
		}
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {// ����ʦ��
			if ("xszz_gjjxjb".equalsIgnoreCase(xmb) || "gjlzjxj".equalsIgnoreCase(xmb) ) {// ���ҽ�ѧ��͹�����־��ѧ��
				// ��ѧ����޿γ���
				// ������
				// ������
				map.putAll(dao.getXnbxkxx(map));
				// רҵ�ɼ�����
				// ��ʱ��֪�����ȡ

				// �ۺϿ����ɼ�
				// �ۺϿ����ɼ�����
				map.putAll(dao.getZhszcpxx(map));
			}
		}
		//
		return map;
	}

	/**
	 * ��ӡ����ͳ�Ʊ���
	 * 
	 * @param form
	 * @param os
	 */
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		String xxdm = StandardOperation.getXxdm();// ѧУ����
		XszzCommTjbbService service = null;
//		String className="";
//		
//		Class clazz = Class.forName(className);
		
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			// ���ϴ�ѧ
			//service = new aaa.xxx.xxdm.XszzHndxService();
		}// end ���ϴ�ѧ
		// ����ѧУ��new һ���µ�service,��service��ʵ����Ӧ�ķ���

		if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)) {
			service = new XszzZjkjService();
		}

		// �й��ش�
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			service = new XszzZjkjService();
		}

		// ����ְҵ
		if (Globals.XXDM_NNZYJSXY.equalsIgnoreCase(xxdm)) {
			service = new XszzNnzyService();
		}

		// ����ʦ��
		if (Globals.XXDM_XYSFXY.equalsIgnoreCase(xxdm)) {
			service = new XszzXysfService();
		}

		// �㽭�ʵ�
		if (Globals.XXDM_ZJYDZYJSXY.equalsIgnoreCase(xxdm)) {
			service = new XszzZjydService();
		}

		//���ҵ��ѧ
		if (Globals.XXDM_TJGYDX.equals(xxdm)){
			service = new XszzPrintService();
		}
		
		//����ũҵ��ѧ
		if (Globals.XXDM_HZNYDX.equals(xxdm)){
			service = new XszzHznyService();
		}
		
		if (service != null) {
			service.printZztjbb(form, os);
		}
	}

	/**
	 * ɾ����Ŀ���״̬
	 * 
	 * @param pkValue
	 * @param xmb
	 * @return
	 * @throws Exception
	 */
	public boolean delXmshZt(String[] pkValue, String xmb) throws Exception {

		if (null != pkValue && pkValue.length > 0) {
			return dao.delXmshZt(pkValue, xmb);
		} else {
			return false;
		}
	}

	/**
	 * ������Ŀ�����ȡ����Ŀ����ϸ��Ϣ
	 * 
	 * @author qph
	 * @param xmdm
	 * @return
	 */
	public HashMap<String, String> getXmIfno(String xmdm) {

		return new XszzDAO().getXszzInfoByXmdm(xmdm);
	}

	/**
	 * ������Ŀ��������
	 * 
	 * @param pkValues
	 * @param model
	 * @return
	 */
	public boolean saveZzxb(String[] pkValues, XszzTyForm model) {

		if (null != pkValues && pkValues.length > 0) {
			try {
				return dao.saveZzxb(pkValues, model);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * ����ѧ�ڴ����ȡѧ������
	 * 
	 * @param xqdm
	 * @return
	 */
	public String getXqMc(String xqdm) {
		return XszzCommDAO.getXqMc(xqdm);
	}
	
	/**
	 * ɾ��jtqkdcb���в�����
	 * ��ѧ���ļ�ͥ��Ա��¼
	 * @return boolean
	 * @throws Exception
	 * author qlj
	 */
	public boolean delXsJtcy() throws Exception{
		
		return dao.delXsJtcy();
	}
	
	
	/**
	 * ���������϶���Ϊ����ģ�飬�����������϶������˼�ͥ�������ʱ������ͬ��
	 * @return
	 */
	public boolean saveJtqkdcFromKns(String xh,String sqsj){
		
		try {
			
			if (StringUtils.isNotNull(xh) && StringUtils.isNotNull(sqsj)){
				dao.delJtqkdc(xh, sqsj);
			} else {
				dao.delAllJtqkdc();
			}
			
			return dao.saveJtqkdcDataFromKns() && dao.saveJtqkdcShztFromKns();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * ��ѧ�ꡢѧ�Ų�ѯ������ɼ�
	 * @param xn
	 * @return
	 */
	public List<HashMap<String,String>> getBjgcjByXn(String xn,String xh){
		return dao.getBjgcjByXn(xn, xh);
	}
	
	
	/**
	 * ����ѧ�Ų�ѯ��������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getKnsInfo(String xh){
		
		return dao.getKnsInfo(xh);
	}
	
	public List<HashMap<String,String>>getPjpyInfo(String xmlx,String xh){
		
		List<HashMap<String,String>>pjpyList=dao.getPjpyInfo(xh);
		List<HashMap<String,String>>pjList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<pjpyList.size();i++){
			HashMap<String,String>jxjMap=pjpyList.get(i);
			if(xmlx.equalsIgnoreCase(jxjMap.get("xmlx"))){
				pjList.add(jxjMap);
			}
		}
		return pjList;
	}
	
	
	/**
	 * ��ȡ���ɼ����Ŀ
	 * @param xmdm
	 * @return
	 */
	public String[] getBkjdxm(String xmdm){
		
		try {
			return dao.getBkjdxm(xmdm);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * �����Ѿ����ڵĲ��ɼ����Ŀ
	 */
	public Map<String,String> getExistsBkjdxm(String xh , String xmdm){
		return dao.getExistsBkjdxm(xh, xmdm);
	}
	
	/**
	 * �ж���Ŀ�Ƿ����ٴ�����
	 * @param xh
	 * @param xmdm
	 * @return
	 * @author honglin
	 * @date 2013-3-12
	 */
	public List<HashMap<String,String>> getSfccsq(String xh , String xmdm){
		return dao.getSfccsq(xh,xmdm);
	}

}
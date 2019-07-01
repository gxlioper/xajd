package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.hngydx.service.HngydxXsqgzxService.saveFreeTime;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: �й����ʴ�ѧ�ڹ���ѧService
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-09-23
 * </p>
 */
public class QgzxZgdzdxService {
	QgzxZgdzdxDAO dao = new QgzxZgdzdxDAO();

	/**
	 * ��ѯ�ڹ���ѧ��������Ϣ
	 * 
	 * @param model
	 * @return List
	 */
	public List getHmdInfo(QgzxZgdzdxForm model) {
		return dao.getHmdInfo(model);
	}

	/**
	 * ��ѯ�ڹ���ѧ��������ͷ��Ϣ
	 * 
	 * @return List
	 */
	public List getHmdTopTr() {
		String[] input = { "xh", "xm", "nj", "xymc", "zymc", "bjmc","jrsj" };
		String[] colCN = dao.getColumnNameCN(input, "view_qgzx_zgdzdx_hmdb");

		return dao.arrayToList(input, colCN);
	}

	/**
	 * ��ѯ����ѧ����������Ϣ
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getOneHmd(String pkValue) {

		return dao.getOneHmd(pkValue);
	}

	/***************************************************************************
	 * �����������Ϣ
	 * 
	 * @param model
	 * @return boolean
	 * @throws Exception
	 **************************************************************************/
	public boolean saveHmd(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String tableName = "qgzx_zgdzdx_hmdb";
		String xh = model.getXh();
		String bz = DealString.toGBK(model.getBz());
		String[] columns = { "xh", "bz" };
		String[] values = { xh, bz };
		int num = dao.getCount(model);

		if (num > 0) {
			// update
			flag = StandardOperation.update(tableName, columns, values, "xh",
					xh, request);
		} else {
			// insert
			flag = StandardOperation
					.insert(tableName, columns, values, request);
		}
		return flag;
	}

	/**
	 * ɾ����������Ϣ
	 * 
	 * @param values
	 * @param request
	 * @return int
	 */
	public int deleteHmd(String[] values, HttpServletRequest request) {
		String tableName = "qgzx_zgdzdx_hmdb";
		for (int i = 0; i < values.length; i++) {
			try {
				StandardOperation.delete(
						"delete from qgzx_zgdzdx_hmdb where xh='" + values[i]
								+ "'", tableName, request);
			} catch (Exception e) {
				e.printStackTrace();
				return i;
			}
		}
		return 0;
	}

	/**
	 * У���ڹ���ѧ��λ��Ϣ����
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws SQLException 
	 */
	public boolean saveGwxx(QgzxZgdzdxForm model, HttpServletRequest request) throws SQLException {
		
		DAO dao=DAO.getInstance();
		
		boolean flag = true;
		String tableName = "qgzx_xwgwxxb";

		String gwmc = model.getGwmc();
		String sqsj = model.getSqsj();
		String sqdwmc = model.getSqdwmc();
		String sqdwdz = model.getSqdwdz();
		String gzkssj = model.getGzkssj();
		String gzjssj = model.getGzjssj();
		String jcfs = model.getJcfs();
		String jcbz = model.getJcbz();
		String lxdh = model.getLxdh();
		String xyrs = model.getXyrs();
		String xyknsrs = model.getXyknsrs();
		String gzsj = model.getGzsj();
		String gznr = model.getGznr();
		String gzyq = model.getGzyq();
		String bz = model.getBz();
		String xn = Base.currXn;
		String nd = Base.currNd;
		String xq = Base.currXq;
		
//		String[] columns = { "gwmc", "sqdwmc", "sqdwdz", "gzkssj", "gzjssj",
//				"xyrs", "xyknsrs", "jcfs", "jcbz", "lxdh", "gzsj", "gznr",
//				"gzyq", "bz", "xn", "nd", "xq" };
//		String[] values = { gwmc, sqdwmc, sqdwdz, gzkssj, gzjssj, xyrs,
//				xyknsrs, jcfs, jcbz, lxdh, gzsj, gznr, gzyq, bz, xn, nd, xq };
//		if (sqsj == null || sqsj.equals("")) {
//			// insert
//			flag = StandardOperation.insert(tableName, columns, values, request);
//		} else {
//			// update
//			try {
//				flag = StandardOperation.update(tableName, columns, values, "gwmc||sqsj", gwmc + sqsj, request);
//			} catch (Exception e) {
//				// TODO �Զ����� catch ��
//				flag = false;
//				e.printStackTrace();
//			}
//		}
		String[]sqlArr=new String[2];
		sqlArr[0]=" delete from qgzx_xwgwxxb where gwmc||sqsj= '"+gwmc + sqsj+"'";
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into qgzx_xwgwxxb(sqsj,gwmc, sqdwmc, sqdwdz, gzkssj, gzjssj,xyrs, xyknsrs, jcfs, jcbz, lxdh, gzsj, gznr,gzyq, bz, xn, nd, xq) ");
		sql.append(" values ('"+sqsj+"','"+gwmc+"','"+sqdwmc+"','"+sqdwdz+"','"+gzkssj+"','"+gzjssj+"','"+xyrs+"','"+xyknsrs+"','"+jcfs+"','"+jcbz+"','"+lxdh+"','"+gzsj+"','"+gznr+"','"+gzyq+"','"+bz+"','"+xn+"','"+nd+"','"+xq+"')  ");
		sqlArr[1]=sql.toString();
		int[]bool=dao.runBatch(sqlArr);
		if(bool[1]==0){
				flag=false;
		}
		return flag;
	}

	/**
	 * ��ѯ����У���ڹ���ѧ��λ��Ϣ
	 * 
	 * @param model
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getXwgwxx(QgzxZgdzdxForm model) {
		return dao.getXwgwxx(model);
	}

	/**
	 * ��ȡУ�ⵥλ�б�
	 * 
	 * @return List
	 */
	public List getXwsqdwList() {
		return dao.getXwsqdwList();
	}

	/**
	 * У���λ��Ϣ��ѯ
	 * 
	 * @param model
	 * @return List
	 */
	public List searchXwgw(QgzxZgdzdxForm model) {
		return dao.searchXwgw(model);
	}

	/**
	 * ��ȡУ���λ��Ϣ��ͷ
	 * 
	 * @return List
	 */
	public List<HashMap<String,String>> getXwgwToptr() {
		String[] input = { "����", "xn", "nd", "xq", "gwmc", "sqdwmc", "xyrs","xyknsrs", "sqsj", "gzsj", "gznr" };
		String[] colCN = dao.getColumnNameCN(input, "qgzx_xwgwxxb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * ɾ��У���λ��Ϣ
	 * 
	 * @param value
	 * @param request
	 * @return int
	 */
	public int deleteXwGwxx(String[] value, HttpServletRequest request) {
		int num = 0;
		for (int i = 0; i < value.length; i++) {
			try {
				StandardOperation.delete("qgzx_xwgwxxb", "gwmc||sqsj",DealString.toGBK(value[i]), request);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
				return i;
			}
		}
		return num;
	}

	/**
	 * ��ȡУ���λ��Ϣ�б�
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwList() {
		return dao.getXwgwList();
	}
	
	/**
	 * ��ȡУ���λ��Ϣ�б�
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwByOld() {
		return dao.getXwgwByOld();
	}

	/**
	 * ��ȡѧ��������Ϣ
	 * 
	 * @param xh
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return dao.getStuInfo(xh);
	}

	/**
	 * �ж�ѧ���Ƿ����˺�����
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean isHmdMember(String xh) {
		return dao.isHmdMember(xh);
	}

	/**
	 * ����ѧ������У���λ��Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveGwsq(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String tableName = "xsxwgwxxb";
		String xh = model.getXh();
		String gwmc = DealString.toGBK(model.getGwmc());// ������gwmc ��sqsj
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String kcjqgzxsj = DealString.toGBK(model.getKcjqgzxsj());
		String lxdh = model.getLxdh();
		String sqly = DealString.toGBK(model.getSqly());
		String bz = DealString.toGBK(model.getBz());
		HashMap<String, String> gwmap = dao.getGwmap(gwmc);

		String vGwmc = gwmap.get("gwmc");
		String vGwsqsj = gwmap.get("sqsj");

		String[] input = { "xh", "xn", "nd", "xq", "kcjqgzxsj", "lxdh", "sqly", "bz", "gwmc", "gwsqsj" };
		String[] value = { xh, xn, nd, xq, kcjqgzxsj, lxdh, sqly, bz, vGwmc, vGwsqsj };
		if (dao.xwgwsqExists(xh, gwmc)) {
			// update
			flag = StandardOperation.update(tableName, input, value, "xh||gwmc||gwsqsj", xh + gwmc, request);
		} else {
			// insert
			flag = StandardOperation.insert(tableName, input, value, request);
		}

		return flag;
	}
	
	/**
	 * �����λ������Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveGwfp(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		HashMap<String, String> gwMap = dao.getGwInfo(model.getGwdm());
		HashMap<String, String> time = dao.getGwsqsjInfo();

		boolean flag = false;
		String pk = model.getPk();
		String[] pkValue = pk.split("!!");
		String tableName = "xsgwxxb";
		int syrs = dao.getGwSyrs(model);

		if (syrs < pkValue.length - 1) {
			request.setAttribute("msg", "�˸ڻ�ʣ" + syrs + "������밴�����������ύ!");
			flag = false;
		} else {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					if (dao.checkExists(tableName, "xh||gwdm||'-'||gwsbsj", pkValue[i] + model.getGwdm())) {// �޸�
						flag = StandardOperation.update(tableName,
								new String[] { "xxyj" },
								new String[] { "ͨ��" },
								"xh||gwdm||'-'||gwsbsj", pkValue[i] + model.getGwdm(), request);

					} else {// ����

						flag = StandardOperation.insert(tableName,
								new String[] { "xh", "gwdm", "gwsbsj", "xn", "nd", "xq", "xyyj", "xxyj" },
								new String[] { pkValue[i],
										gwMap.get("gwmc"),
										gwMap.get("gwsbsj"),
										time.get("xn"), time.get("nd"),
										time.get("xq"), "ͨ��", "ͨ��" },
								request);
						/**�й����ʴ�ѧ�ڹ���ѧֱ�ӷ����λ�ɹ���������λ�����¼ȫ����Ϊ��˲�ͨ��*/
						if(flag){
							StandardOperation.updateXsgwxx(new String[] { pkValue[i], time.get("xn"), time.get("nd"), time.get("xq")});
						}
					}
				}
			}

		}

		return flag;
	}

	/**
	 * ����ѧ�������ڹ���ѧ֮����Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxzx(QgzxZgdzdxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String tableName = "qgzxzxsqb";
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String zwjd = model.getZwjd();

		String[] input = { "xh", "xn", "nd", "xq", "zwjd" };
		String[] value = { xh, xn, nd, xq, zwjd };
		if (dao.checkExists("xsgwxxb", "xh||xn||nd||xq||xxyj", xh + xn + nd + xq + "ͨ��")) {// �ж��Ƿ���У���ڹ���ѧѧ��
			if (dao.checkExists(tableName, "xh||xn||nd||xq", xh + xn + nd + xq)) {
				// update
				flag = StandardOperation.update(tableName, input, value, "xh||xn||nd||xq", xh + xn + nd + xq, request);
			} else {
				// insert
				flag = StandardOperation.insert(tableName, input, value, request);
			}
		} else {
			request.setAttribute("noPass", "yes");
		}
		return flag;
	}

	/**
	 * ��ȡѧ����λ��������ѯ��ͷ
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwsqToptr() {
		String[] input = { "����", "xn", "nd", "xq", "xh", "xm", "xymc", "bjmc", "gwmc", "xxsh", "zpsj", "zpdz" };
		String[] colCN = dao.getColumnNameCN(input, "view_xsxwgwxxb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * ��ȡѧ���ڹ���ѧ֮����������ѯ��ͷ
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getQgzxzxToptr() {
		String[] input = { "����", "xn", "nd", "xq", "xh", "xm", "bjmc", "sqsj", "yrdwsh", "xysh", "xxsh" };
		String[] colCN = dao.getColumnNameCN(input, "view_qgzxzx");
		return dao.arrayToList(input, colCN);
	}

	public List<HashMap<String, String>> getQgzxzxShTopTr() {
		String[] input = { "color", "����", "xn", "nd", "xq", "xh", "xm", "bjmc", "sqsj", "yrdwsh", "xysh", "xxsh" };
		String[] colCN = dao.getColumnNameCN(input, "view_qgzxzx");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * ѧ����λ��������ѯ
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getXwGwsq(QgzxZgdzdxForm model) {
		return dao.getXwGwsq(model);
	}

	public List<String[]> qgzxzxSearch(QgzxZgdzdxForm model) {
		return dao.qgzxzxSearch(model);
	}

	/**
	 * ����������ȡѧ�������λ����Ϣ
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqInfo(String pkValue) {
		return dao.getGwsqInfo(pkValue);
	}

	/**
	 * ɾ��ѧ�������λ��Ϣ
	 * 
	 * @param value
	 * @param request
	 * @return int
	 */
	public int deleteXwGwsq(String[] value, HttpServletRequest request) {
		int result = 0;
		String tableName = "xsxwgwxxb";
		String pk = "xn||nd||xq||xh||gwmc||gwsqsj";
		for (int i = 0; i < value.length; i++) {
			try {
				StandardOperation.delete(tableName, pk, DealString
						.toGBK(value[i]), request);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
				return i;
			}
		}
		return result;
	}

	/**
	 * ��ѯѧУ��˽��
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getXwGwsqSh(QgzxZgdzdxForm model) {
		return dao.getXwGwsqSh(model);
	}

	/**
	 * ��ȡѧ����λ������˲�ѯ��ͷ
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getXwgwsqShToptr() {
		String[] input = { "color", "����", "xn", "nd", "xq", "xh", "xm", "xymc",
				"bjmc", "gwmc", "xxsh" };
		String[] colCN = dao.getColumnNameCN(input, "view_xsxwgwxxb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * ��λ���������Ϣ��ѯ
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqshInfo(String pkValue) {
		return dao.getGwsqshInfo(pkValue);
	}

	/**
	 * ��ȡ����б�
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getChkList() {
		return dao.getChkList(3);
	}

	/**
	 * ѧ������У���λ��˱���
	 */
	public boolean saveXwgwsqsh(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;
		String xxsh = DealString.toGBK(model.getXxsh());
		String zpsj = DealString.toGBK(model.getZpsj());
		String zpdz = DealString.toGBK(model.getZpdz());

		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String gwmc = DealString.toGBK(model.getGwmc());
		String sqsj = model.getGwsqsj();
		try {
			flag = StandardOperation.update("xsxwgwxxb", new String[] { "xxsh",
					"zpsj", "zpdz" }, new String[] { xxsh, zpsj, zpdz },
					"xh||xn||nd||xq||gwmc||gwsqsj", xh + xn + nd + xq + gwmc
							+ sqsj, request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * ��ѯУ���λ��ϸ��Ϣ
	 * 
	 * @return pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwxxDetail(String pkValue) {
		return dao.getGwxxDetail(pkValue);
	}

	/**
	 * ��ѯѧԺ������������
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> getQgzxxy(QgzxZgdzdxForm model) {
		return dao.getQgzxxy(model);
	}

	/**
	 * ��ȡѧԺ���������ѯ�ı�ͷ
	 */
	public List<HashMap<String, String>> getTopTrOfDisperson() {
		String xxdm = StandardOperation.getXxdm();
		String[] input = { "���Ŵ���", "ѧ��", "���", "ѧ��", "��������", "��������" };
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) || Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			input = new String[]{ "���Ŵ���", "ѧ��", "���", "ѧ��", "��λ����","��������", "��������" };
		}
		return dao.arrayToList(input, input);
	}

	/**
	 * ����ѧԺ��������Ľ��
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveDistribute(QgzxZgdzdxForm model,
			HttpServletRequest request) {
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		String xn = model.getXn().trim();
		String nd = model.getNd().trim();
		String xq = model.getXq().trim();
		String fprs = model.getFprs();
		String xydm = model.getXydm();
		String gwdm = model.getGwdm();
		String gwsbsj = model.getGwsbsj();
		String tableName = "qgzx_xyrsb";
		String[] input = new String[] { "xn", "nd", "xq", "xydm", "fprs" };
		String[] value = new String[] { xn, nd, xq, xydm, fprs };
		String pk = "xn||nd||xq||xydm";
		String pkValue = xn+nd+xq+xydm;
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) || Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			pk = "xn||nd||xq||xydm||gwdm||gwsbsj";
			pkValue = xn+nd+xq+xydm+gwdm+gwsbsj;
			input = new String[]{ "xn", "nd", "xq", "xydm", "fprs", "gwdm", "gwsbsj" };
			value = new String[]{ xn, nd, xq, xydm, fprs, gwdm, gwsbsj };
		}
		if (dao.checkExists(tableName, pk, pkValue)) {
			try {
				flag = StandardOperation.update(tableName, new String[] { "fprs" }, new String[] { fprs }, pk, pkValue, request);
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		} else {
			flag = StandardOperation.insert(tableName, input, value, request);
		}
		return flag;
	}

	/**
	 * �����ڹ���ѧ����
	 * 
	 * @param model
	 * @param reqeust
	 * @return boolean
	 */
	public boolean saveQgzxsq(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;		
		String xh = model.getXh();
		String lxdh = model.getLxdh();
		String kcjqgzxsj = DealString.toGBK(model.getKcjqgzxsj());
		String sqly = DealString.toGBK(model.getSqly());
		String bz = DealString.toGBK(model.getBz());
		String yhtc = DealString.toGBK(model.getYhtc());
		String zzmmdm = DealString.toGBK(model.getZzmmdm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String kcsgz = model.getKcsgz();
		String jjqk = model.getJjqk();
		String gwxzdm = model.getGwxzdm();
		gwxzdm = StringUtils.isNull(gwxzdm) ? "##" : gwxzdm;
		
		String tableName = "qgzxsqb";
		if (dao.isExistsQgzxsq(model)) {//�����Ѿ����ڣ������޸Ĳ���
			try {
				flag = StandardOperation.update(tableName, new String[] {
						"lxdh", "kcjqgzxsj", "sqly", "bz" ,"zzmmdm","yhtc","kcsgz","jjqk","gwxzdm"}, new String[] {
						lxdh, kcjqgzxsj, sqly, bz ,zzmmdm, yhtc,kcsgz,jjqk,gwxzdm}, "xh||xn||nd||xq", xh + xn
						+ nd + xq, request);
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		} else {//���ݲ����ڣ����в������
			flag = StandardOperation.insert(tableName, new String[] { "xh",
					"xn", "nd", "xq", "lxdh", "kcjqgzxsj", "sqly", "bz","zzmmdm", "yhtc", "kcsgz","jjqk", "gwxzdm" },
					new String[] { xh, xn, nd, xq, lxdh, kcjqgzxsj, sqly, bz ,zzmmdm, yhtc, kcsgz, jjqk, gwxzdm},
					request);
		}
		if(flag){//����ѧ���ɲμ��ڹ���ѧʱ����Ϣ
			saveFreeTime sft = new saveFreeTime(request,xh,4,5);
			new Thread(sft).start();
		}
		return flag;
	}

	/**
	 * �����ڹ���ѧ����(���ݴ�ѧ)
	 * 
	 * @author luojw
	 * @param model
	 * @param reqeust
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean saveQgzxsqGzdx(QgzxZgdzdxForm model,
			HttpServletRequest request) throws SQLException {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		String[] checkVal = model.getCheckVal();
		String xh = model.getXh();
		String lxdh = model.getLxdh();
		String kcjqgzxsj = DealString.toGBK(model.getKcjqgzxsj());
		String sqly = DealString.toGBK(model.getSqly());
		String bz = DealString.toGBK(model.getBz());
		String yhtc = DealString.toGBK(model.getYhtc());
		String zzmmdm = DealString.toGBK(model.getZzmmdm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String kcsgz = model.getKcsgz();
		String jjqk = model.getJjqk();
		String gwxzdm = model.getGwxzdm();

		String tableName = "qgzxsqb";

		StringBuffer sql = new StringBuffer();

		if (checkVal != null && checkVal.length > 0) {
			
			String[] exec = new String[checkVal.length + 1];
			int n = 0;
			
			sql.append(" delete from ");
			sql.append(tableName);
			sql.append(" where xn = '" + xn + "' ");
			sql.append(" and xq = '" + xq + "' ");
			sql.append(" and nd = '" + nd + "' ");
			sql.append(" and xh = '" + xh + "' ");
			exec[n] = sql.toString();
			n++;

			for (int i = 0; i < checkVal.length; i++) {
				
				String pk = "xn||xq||nd||xh||gwxzdm";
				String pkValue = xn + xq + nd + xh + checkVal[i];
				String shSql = "select xysh,xxsh from " + tableName + " where " + pk
						+ " = ?";
				String[] sh = dao.getOneRs(shSql, new String[] { pkValue },
						new String[] { "xysh", "xxsh" });
				
				String xysh = "δ���"; 
				String xxsh = "δ���"; 
				
				if (sh != null && sh.length > 1) {
					xysh = sh[0];
					xxsh = sh[1];
				}
				
				sql = new StringBuffer();
				sql.append(" insert into ");
				sql.append(tableName);
				sql.append("(xh, xn, nd, xq, lxdh, kcjqgzxsj, sqly,");
				sql.append(" bz,zzmmdm, yhtc, kcsgz,jjqk,xysh,xxsh, gwxzdm)");
				sql.append(" values(");
				sql.append("'" + xh + "'");
				sql.append(",'" + xn + "'");
				sql.append(",'" + nd + "'");
				sql.append(",'" + xq + "'");
				sql.append(",'" + lxdh + "'");
				sql.append(",'" + kcjqgzxsj + "'");
				sql.append(",'" + sqly + "'");
				sql.append(",'" + bz + "'");
				sql.append(",'" + zzmmdm + "'");
				sql.append(",'" + yhtc + "'");
				sql.append(",'" + kcsgz + "'");
				sql.append(",'" + jjqk + "'");
				sql.append(",'" + xysh + "'");
				sql.append(",'" + xxsh + "'");
				sql.append(",'" + checkVal[i] + "'");
				sql.append(")");
				exec[n] = sql.toString();
				n++;
			}

			int[] res = dao.runBatch(exec);

			for (int i = 0; i < res.length; i++) {
				flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		}

		if (flag) {// ����ѧ���ɲμ��ڹ���ѧʱ����Ϣ
			saveFreeTime sft = new saveFreeTime(request, xh, 4, 5);
//			new Thread(sft).start();
			sft.run();
		}
		
		return flag;
	}
	
	/**
	 * �ж���һѧ�����εĹҿ�
	 * ���Ƿ��������
	 * 2010.9.14 qlj
	 */
	public boolean checkGks(String xh){
		List<HashMap<String,String>>list=dao.checkGks(xh);
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			int sxnkks=Integer.parseInt(hashMap.get("sxnkks"));
			if(sxnkks>=2){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	/**
	 * �ж�ѧ���Ƿ���
	 * Υ�����
	 * 2010.9.14 qlj
	 */
	public boolean checkWjcf(String xh){
		List<HashMap<String,String>>list=dao.checkWjqk(xh);
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			int wjcfcs=Integer.parseInt(hashMap.get("wjcfcs"));
			if(wjcfcs>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	/**
	 * ���ݴ�ѧ
	 * ȷ���Ƿ����ʸ�
	 * ����У���ڹ���ѧ
	 * 2010.9.14 qlj
	 */
	public boolean checkXnqgzxsq(String xh,String[] gwxzdm){
		QgzxZgdzdxService service=new QgzxZgdzdxService();
		//У��
		boolean blog=true;
		for(int i=0;i<gwxzdm.length;i++){
			if("001".equalsIgnoreCase(gwxzdm[i])){
				if(!service.checkGks(xh) || !service.checkWjcf(xh)){
					blog =false;
				}
			}
		}
		return blog;
	}
	
	/**
	 * ���ݴ�ѧ
	 * ���ʱ���Ӹ���Ա����
	 */
	public String getFdyXm(String zgh){
		List<HashMap<String,String>>list=dao.getFdyXm(zgh);
		String xm="";
		if(list.size()>0){
			HashMap<String,String>hashMap=list.get(0);
			xm=hashMap.get("xm");
		}else{
			xm="������";
		}
		return xm;
	}
	
	/**
	 * ��ѯ�ڹ���ѧ������Ϣ
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> selectQgzxsqshInfo(QgzxZgdzdxForm model) {
		return dao.selectQgzxsqshInfo(model);
	}
	
	/**
	 * ��ѯ�ڹ���ѧ������Ϣ
	 * 
	 * @param model
	 * @return List
	 */
	public List<String[]> queryQgzxsqb(QgzxZgdzdxForm model) {
		return dao.selectQgzxsqb(model);
	}

	/**
	 * ��ȡ�ڹ���ѧ������Ϣ��ͷ
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getQgzxsqTopTr() {
		String xxdm = StandardOperation.getXxdm();
		String[] input = { "color", "����", "xn", "nd", "xqmc", "xh", "xm", "xymc",
				"bjmc", "lxdh", "xysh", "xxsh" };
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			input = new String[]{ "color", "����", "xn", "nd", "xqmc", "xh", "xm", "xymc",
					"bjmc", "gwxzmc", "lxdh", "xysh", "xxsh" };
		}
		String[] colCN = dao.getColumnNameCN(input, "view_qgzxsqb");
		return dao.arrayToList(input, colCN);
	}

	/**
	 * ��ȡ�ڹ���ѧ������ϸ��Ϣ
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxsqInfo(QgzxZgdzdxForm model) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getQgzxsqInfo(model); 
		return map;
	}

	/**
	 * ��ȡ�ڹ���ѧ������ϸ��Ϣ
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 * @author luo
	 */
	public HashMap<String, String> getQgzxsqZjfp(QgzxZgdzdxForm model) {
		return dao.getQgzxsqZjfp(model);
	}

	/**
	 * ����ѧԺ����ڹ���ѧ������Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxsqsh(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;
		String tableName = "qgzxsqb";
		String yesNo = DealString.toGBK(model.getYesNo());
		String[] columns = { "xysh" };
		String[] values = { yesNo };
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();

		String userType = model.getUserType();

		if (userType != null
				&& ("xx".equalsIgnoreCase(userType) || "admin"
						.equalsIgnoreCase(userType))) {
			columns = new String[] { "xxsh" };
		}

		try {
			if (yesNo != null && yesNo.equalsIgnoreCase("ͨ��")) {
				if (this.isHmdMember(xh)) {
					request.setAttribute("hmdMember", "true");
					return false;
				}
				if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
					if (dao.checkPostCount(model)) {
						request.setAttribute("mes", "�����Ѿ�������������������ʧ�ܣ�");
						return false;
					}
				}
			}
			flag = StandardOperation.update(tableName, columns, values,
					"xh||xn||nd||xq", xh + xn + nd + xq, request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * �����ڹ���ѧ�����Ϣ 
	 * @param model
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxsqshByGzdx(QgzxZgdzdxForm model, HttpServletRequest request) {
		boolean flag = false;
		String tableName = "qgzxsqb";
		String yesNo = DealString.toGBK(model.getYesNo());		
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String fdyname=model.getFdyname();
		String[] xmdm = model.getXmdm().split("!!");
		String gwshColumn = "xxyj";
		String gwdm = model.getGwdm();
		String gwdmgwsbsj = model.getXmdm();
		String isFdy=model.getIsFdy();
		
		String[] columns = {"zdgwdm", "zdgwdmgwsbsj", "xysh"};
		String[] values = { gwdm, gwdmgwsbsj, yesNo };
		
		if("true".equalsIgnoreCase(isFdy)){
			 columns =new String[] {"zdgwdm", "zdgwdmgwsbsj", "xysh","fdyname"};
			 values =new String[]  { gwdm, gwdmgwsbsj, yesNo,fdyname};
			
		}
		
		String userType = model.getUserType();

		if (userType != null && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))) {
			columns = new String[] {"zdgwdm", "zdgwdmgwsbsj", "xxsh" };
		}

		try {
			if (yesNo != null && yesNo.equalsIgnoreCase("ͨ��")) {	
				for(int  i=0; i<xmdm.length; i++){
					String tmp = xmdm[i];
					if(!StringUtils.isNull(tmp)){
						model.setGwdm(tmp.split("-")[0]);
						model.setGwsbsj(tmp.split("-")[1]);
//						if(userType != null && "xy".equalsIgnoreCase(userType)){
//							//ѧԺ�û�
//							if (dao.checkPostCountByGzdx(model)) {
//								request.setAttribute("mes", "'" + model.getGwdm() + "'�����Ѿ�������������������ʧ�ܣ�");
//								return false;
//							}
//							gwshColumn = "xyyj";
//						}
//						//��ѧ����λ��Ϣ�������µ���λ��Ϣ����
//						String pk = "xh||gwdm||gwsbsj";
//						String pkValue = xh+model.getGwdm()+model.getGwsbsj();
//						if(dao.checkExists("xsgwxxb", pk,pkValue)){
//							//��¼�Ѿ����ڣ������޸Ĳ�����
//							StandardOperation.update("xsgwxxb", new String[]{gwshColumn,"sfyx"}, new String[]{"ͨ��","1"}, pk, pkValue, request);//sfyx='1' ��Ч
//						}else{
//							//��¼�����ڣ����в��������
//							StandardOperation.insert("xsgwxxb",new String[]{"xh","xn","nd","xq","gwdm","gwsbsj",gwshColumn,"sfyx"}, new String[]{xh,xn,nd,xq,model.getGwdm(),model.getGwsbsj(),"ͨ��","1"}, request);////sfyx='1' ��Ч
//						}
						
						// ---------2010/5/17 edit by luojw -----------
						String pk = "xh||gwdm||gwsbsj";
						String pkValue = xh + model.getGwdm()
								+ model.getGwsbsj();

						HashMap<String, String> map = dao.getGwsqXx(pkValue);
						
						String xyyj = Base.isNull(map.get("xyyj")) ? "δ���"
								: map.get("xyyj");
						String xxyj = "ͨ��";
						
						flag = StandardOperation.delete("xsgwxxb", pk, pkValue,
								request);

						if (flag) {

							if (userType != null
									&& "xy".equalsIgnoreCase(userType)) {
								// ѧԺ�û�
								if (dao.checkPostCountByGzdx(model)) {
									request.setAttribute("mes", "'"
											+ model.getGwdm()
											+ "'�����Ѿ�������������������ʧ�ܣ�");
									return false;
								}
								gwshColumn = "xyyj";
								xyyj = "ͨ��";
								// ��¼�����ڣ����в��������
								StandardOperation.insert("xsgwxxb", new String[] {
										"xh", "xn", "nd", "xq", "gwdm", "gwsbsj",
										"xyyj", "sfyx" }, new String[] { xh,
										xn, nd, xq, model.getGwdm(),
										model.getGwsbsj(), xyyj,"1" }, request);// sfyx='1'
																					// ��Ч
							}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
							
							// ��¼�����ڣ����в��������
							StandardOperation.insert("xsgwxxb", new String[] {
									"xh", "xn", "nd", "xq", "gwdm", "gwsbsj",
									"xxyj", "sfyx" }, new String[] { xh,
									xn, nd, xq, model.getGwdm(),
									model.getGwsbsj(),xxyj, "1" }, request);// sfyx='1'
																				// ��Ч
							}
						}

						// ---------end -----------
					}
				}					
			}
			//flag = StandardOperation.update(tableName, columns, values, "xh||xn||nd||xq", xh + xn + nd + xq, request);
			String pk = "xh||xn||nd||xq||gwxzdm";
			String pkValue = xh + xn + nd + xq + model.getGwxzdm();
			flag = StandardOperation.update(tableName, columns, values, pk,
					pkValue, request);
			DAO dao=DAO.getInstance();
			StringBuilder sql = new StringBuilder();
			if("xy".equalsIgnoreCase(userType) 
					&& !"ͨ��".equalsIgnoreCase(yesNo)){
				sql = new StringBuilder();
				sql.append("update xsgwxxb a set xyyj='"+yesNo+"' where exists ");
				sql.append(" (select * from view_qgzxsqb b where  ");
				sql.append(pk + "='" + pkValue + "'");
				sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
				dao.runUpdate(sql.toString(), new String[]{});
			}else if(("xx".equalsIgnoreCase(userType)|| "admin".equalsIgnoreCase(userType))
					&& !"ͨ��".equalsIgnoreCase(yesNo)){
				sql = new StringBuilder();
				sql.append("update xsgwxxb a set xxyj='"+yesNo+"' where exists ");
				sql.append(" (select * from view_qgzxsqb b where  ");
				sql.append(pk + "='" + pkValue + "'");
				sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
				dao.runUpdate(sql.toString(), new String[]{});
			}	
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * �ж��Ƿ�������ʱ����
	 * 
	 * @return boolean
	 */
	public boolean checkTime() throws SQLException {
		return dao.checkTime();
	}

	/**
	 * �ж�ѧ���Ƿ�ͨ��ѧԺ�Ƽ�
	 * 
	 * @param xh
	 * @return boolean
	 */
	public boolean checkStuPass(String xh) {
		return dao.checkStuPass(xh);
	}

	/**
	 * ��ȡ��λ��Ϣ�б�
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getGwList() {
		return dao.getGwList();
	}
	
	/**
	 * ��ȡ��λ��Ϣ�б�
	 * @param String userName 
	 * @return List
	 */
	public List<HashMap<String, String>> getGwList(String userName) {
		return dao.getGwList(userName);
	}
	
	/**
	 * ��ȡ��λ��Ϣ�б�
	 * 
	 * @param String
	 *            userName
	 * @return List
	 */
	public List<HashMap<String, String>> getRsfpgwList(String userName,
			String gwxzmc, HttpServletRequest request) {
		String xxdm = StandardOperation.getXxdm();
		List<HashMap<String, String>> list = dao.getRsfpgwList(userName,
				gwxzmc, request);
		if (Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
			list = dao.getRsfpgwListOfNbtyxy(userName);
		}
		return list;
	}

	/**
	 * ��ȡ�Ѱ��������б�
	 * 
	 * @return List
	 * @author luo
	 */
	public List<HashMap<String, String>> getApList() {
		return dao.getGwApList();
	}

	/**
	 * ��ȡ����ʹ�������б�
	 * 
	 * @return List
	 * @author luo
	 */
	public List<HashMap<String, String>> getSqList() {
		return dao.getGwSqList();
	}
	
	/**
	 * ��ѯ�ڹ���ѧ֮��������ϸ��Ϣ
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxzxInfo(String pkValue) {
		return dao.getQgzxzxInfo(pkValue);
	}

	/**
	 * ɾ���ڹ���ѧ֮����Ϣ
	 * 
	 * @param String[]
	 *            values
	 * @param request
	 * @return int
	 */
	public int deleteQgzxzx(String[] values, HttpServletRequest request) {
		String tableName = "qgzxzxsqb";
		for (int i = 0; i < values.length; i++) {
			try {
				StandardOperation.delete(
						"delete from qgzxzxsqb where xh||xn||nd||xq='"
								+ values[i] + "'", tableName, request);
			} catch (Exception e) {
				e.printStackTrace();
				return i;
			}
		}
		return 0;
	}

	/**
	 * �ڹ���ѧ֮����˲�ѯ
	 * 
	 * @param model
	 * @param userType
	 * @param userName
	 * @return List
	 */
	public List<String[]> getQgzxzxSh(QgzxZgdzdxForm model, String userType,
			String userName) {
		return dao.getQgzxzxSh(model, userType, userName);
	}
	
	/**
	 * �ж��Ƿ������˵�λ�û�
	 * @param String userName
	 * @return boolean
	 * */
	public boolean isYrdwUser(String userName){
		return dao.checkExists("yrdwdmb", "dlm", userName);
	}
	
	/**
	 * ��ѯ�ڹ���ѧ֮�������ϸ��Ϣ
	 * 
	 * @param pkValue
	 * @param userType
	 * @param userName
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxzxShInfo(String pkValue,
			String userType, String userName) {
		return dao.getQgzxzxShInfo(pkValue, userType, userName);
	}

	/**
	 * �ڹ���ѧ֮����˱���
	 * 
	 * @param model
	 * @param userType
	 * @param userName
	 * @param request
	 * @return boolean
	 */
	public boolean saveQgzxzxsh(QgzxZgdzdxForm model, String userType,
			String userName, HttpServletRequest request) throws Exception {
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String szbmyj = DealString.toGBK(model.getSzbmyj());
		String xyyj = DealString.toGBK(model.getXyyj());
		String xxyj = DealString.toGBK(model.getXxyj());
		String yesNo = DealString.toGBK(model.getYesNo());
		String[] values = { szbmyj, xyyj, xxyj, yesNo };
		String[] columns = null;

		if (userType != null && userType.equalsIgnoreCase("xy")) {
			columns = new String[] { "xyyj", "xysh" };
			values = new String[] { xyyj, yesNo };
		} else {
			columns = new String[] { "xxyj", "xxsh" };
			values = new String[] { xxyj, yesNo };
		}
		if (dao.checkExists("yrdwdmb", "dlm", userName)) {
			columns = new String[] { "szbmyj", "yrdwsh" };
			values = new String[] { szbmyj, yesNo };
		}
		return StandardOperation.update("qgzxzxsqb", columns, values,
				"xh||xn||nd||xq", xh + xn + nd + xq, request);
	}

	/**
	 * �ڹ���ѧ֮���������
	 * 
	 * @param String[]
	 *            values
	 * @param userType
	 * @param userName
	 * @param yesNo
	 * @param request
	 * @return boolean
	 */
	public boolean batchQgzxzxsh(String[] values, String userType,
			String userName, String yesNo, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String[] columns = null;
		if (userType != null && userType.equalsIgnoreCase("xy")) {
			columns = new String[] { "xysh" };
		} else {
			columns = new String[] { "xxsh" };
		}
		if (dao.checkExists("yrdwdmb", "dlm", userName)) {
			columns = new String[] { "yrdwsh" };
		}
		for (int i = 0; i < values.length; i++) {
			flag = StandardOperation.update("qgzxzxsqb", columns,
					new String[] { yesNo }, "xh||xn||nd||xq", values[i],
					request);
		}
		return flag;
	}

	public boolean saveWorkPayAudit(HttpServletRequest request) {
		QgzxDao qgzxDao = new QgzxDao();
		boolean flag = false;
		String mes = "";
		String count = request.getParameter("count");
		// String nd = request.getParameter("nd");
		// String yf = request.getParameter("yf");
		String gwdm = DealString.toGBK(request.getParameter("gwdm"));
		String gwsbsj = DealString.toGBK(request.getParameter("gwsbsj"));
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		String myzgbc = qgzxDao.getSqsjInfo().get("myzgbc");

		// yf = yf == null || "".equalsIgnoreCase(yf) ? "0" : yf;
		// yf = String.valueOf(Integer.parseInt(yf));
		// �ж��Ƿ��б�������趨ֵ
		if (myzgbc != null && !myzgbc.equalsIgnoreCase("")) {
			for (int i = 0; i < Integer.parseInt(count); i++) {
				String cjje = request.getParameter("cjje" + i);
				cjje = cjje == null || "".equalsIgnoreCase(cjje) ? "0" : cjje;
				if (Integer.parseInt(cjje) > Integer.parseInt(myzgbc)) {
					mes = request.getParameter("xh" + i)
							+ "���������趨��ÿ����߱�����ȷ�ϣ�";
					request.setAttribute("mes", mes);
					return flag;
				}
			}
		}
		// ���������Ϣ
		for (int i = 0; i < Integer.parseInt(count); i++) {
			String xh = request.getParameter("xh" + i);
			String cjje = request.getParameter("cjje" + i);
			String xxsh = request.getParameter("xxsh" + i);
			String nd = request.getParameter("nd" + i);
			String yf = request.getParameter("yf" + i);
			xxsh = xxsh != null && xxsh.equalsIgnoreCase("1") ? "ͨ��" : "��ͨ��";
			try {
				flag = StandardOperation.update("xscjffb", new String[] {
						"cjje", "xxsh" }, new String[] { cjje, xxsh },
						"xh||nd||yf||gwdm||sqsj", xh + nd + yf + gwdm + gwsbsj,
						request);
			} catch (Exception e) {
				mes += xh + "���ʧ�ܣ�\n";
				e.printStackTrace();
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}

	/**
	 * ��ӡ��𱨱�
	 * 
	 * @param WritableWorkbook
	 *            wwb
	 * @param String
	 *            nd
	 * @param yf
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void printWorkPay(WritableWorkbook wwb, String nd, String yf) {
		nd = nd == null || "".equalsIgnoreCase(nd) ? Base.currNd : nd;
		yf = yf == null || "".equalsIgnoreCase(yf) ? dao.currYf() : yf;
		HashMap<String, String> map = dao.getWorkPayMap(nd, yf);
		String count = map.get("count");
		String zje = map.get("zje");
		String ygzzxs = map.get("gzxs");
		zje = zje == null ? "" : zje;
		ygzzxs = ygzzxs == null ? "" : ygzzxs;
		List cjList = dao.getCjffInfo(nd, yf);

		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat wcfTytle = new WritableCellFormat();

		try {
			// ��ͷ
			wcfTytle.setAlignment(Alignment.CENTRE);
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(20);
			wcfTytle.setFont(wfTytle);
			ws.addCell(new Label(0, 0, String.valueOf("(" + nd + ")��(" + yf
					+ ") ���ڹ���ѧ���񱨳귢�ű�"), wcfTytle));

			// ������
			wcfTytle = new WritableCellFormat();
			wcfTytle.setAlignment(Alignment.LEFT);
			wfTytle = new WritableFont(WritableFont.COURIER);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);
			ws.addCell(new Label(0, 2, String.valueOf("������:" + count + "      �ܽ��: " + zje + "     "
							+ "������ʦǩ������:                ������ʦ��ϵ�绰:         "), wcfTytle));
			// ����
			wcfTytle = new WritableCellFormat();
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			for (int i = 0; i < cjList.size(); i++) {
				HashMap<String, String> tmp = (HashMap<String, String>) cjList.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),wcfTytle));
				ws.addCell(new Label(1, 4 + i,String.valueOf(tmp.get("dwmc") == null ? "" : tmp.get("dwmc")), wcfTytle));
				ws.addCell(new Label(2, 4 + i,
						String.valueOf(tmp.get("bmmc") == null ? "" : tmp
								.get("bmmc")), wcfTytle));
				ws.addCell(new Label(3, 4 + i,
						String.valueOf(tmp.get("bjmc") == null ? "" : tmp
								.get("bjmc")), wcfTytle));
				ws.addCell(new Label(4, 4 + i, String
						.valueOf(tmp.get("xh") == null ? "" : tmp.get("xh")),
						wcfTytle));
				ws.addCell(new Label(5, 4 + i, String
						.valueOf(tmp.get("xm") == null ? "" : tmp.get("xm")),
						wcfTytle));
				ws.addCell(new Label(6, 4 + i,
						String.valueOf(tmp.get("gzsj") == null ? "" : tmp
								.get("gzsj")), wcfTytle));
				ws.addCell(new Label(7, 4 + i,
						String.valueOf(tmp.get("cjje") == null ? "" : tmp
								.get("cjje")), wcfTytle));
				ws.addCell(new Label(8, 4 + i,
						String.valueOf(tmp.get("lxdh") == null ? "" : tmp
								.get("lxdh")), wcfTytle));
				ws.addCell(new Label(9, 4 + i, String.valueOf(""), wcfTytle));
				ws.addCell(new Label(10, 4 + i, String.valueOf(""), wcfTytle));
			}

			// С��
			ws.addCell(new Label(0, 4 + cjList.size(), String.valueOf("С��"),
					wcfTytle));
			ws.addCell(new Label(1, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(2, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(3, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(4, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(5, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(6, 4 + cjList.size(), String.valueOf(ygzzxs),
					wcfTytle));
			ws.addCell(new Label(7, 4 + cjList.size(), String.valueOf(zje),
					wcfTytle));
			ws.addCell(new Label(8, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(9, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));
			ws.addCell(new Label(10, 4 + cjList.size(), String.valueOf(""),
					wcfTytle));

		} catch (RowsExceededException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ����Ƿ���У��ѧ����λ����ʱ��֮��
	 * 
	 * @return boolean
	 */
	public boolean checkOutTime() {
		return dao.checkOutTime();
	}

	/**
	 * ����Ƿ��ڿ�ֱ�ӷ����λ��ʱ�䷶Χ��
	 * 
	 * @param model
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean checkAllow(QgzxZgdzdxForm model) throws SQLException {
		boolean flag = false;
		String userName = model.getUserName();
		String userType = model.getUserType();
		boolean isYrdw = dao.isYrdw(userName); 

		if (isYrdw && checkTime()) {
			flag = false;
		} else if (userType != null && !isYrdw
				&& ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) && !checkTime()) {
			// ѧУֱ�ӷ����λ��ʱ���ڿ������λʱ�䷶Χ֮��
			flag = false;
		} else {
			flag = true;
		}

		return flag;
	}

	/**
	 * ��ȡ���˵�λ�б�
	 * 
	 * @return List
	 */
	public List getYrdwList() {
		return dao.getYrdwList();
	}

	public List getGwzjfpTopTr() {
		// ================ begin ���ΰ 2009/3/25 ==============
		String[] outputValue = { "����", "xn", "nd", "xq", "xh", "xm", "xb", "nj", 
				                 "bjmc", "sfygw", "gwdm", "ssbh", "zzmmmc", "lxdh", 
				                 "xymc","gwzydj" };
		String[] colCN = { "����", "ѧ��", "���", "ѧ��", "ѧ��", "����", "�Ա�", 
				           "�꼶", Base.YXPZXY_KEY+"����", "�༶����", "�Ƿ��и�λ", "��λ����", 
				           "������", "������ò����", "��ϵ�绰","��λ־Ը�ȼ�" };
		// ================ end ���ΰ 2009/3/25 ==============
		return dao.arrayToList(outputValue, colCN);
	}

	/**
	 * ֱ�ӷ����λ��Ϣ��ѯ
	 * 
	 * @param model
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getGwzjfpList(QgzxZgdzdxForm model) {
		List<HashMap<String, String>> rs = null;
		rs = dao.selectGwzjfpXxList(model);

		return rs;
	}
	
	/**
	 * ��λֱ�ӷ������˵�λ�û���ѯѧ����Ϣ�ܼ�¼��
	 * 
	 * @param model
	 * @return List
	 */
	public int selectGwzjfpXxNum(QgzxZgdzdxForm model) {
		return dao.selectGwzjfpXxNum(model);
	}

	/**
	 * ��ȡ�ɷ���ĸ�λ�б�
	 * 
	 * @param model
	 * @return List
	 */
	public List getKfpgwList(QgzxZgdzdxForm model) {
		String userName = model.getUserName();
		List rs = null;
		// String userType = model.getUserType();
		String yrdwdm = dao.checkUserIsYrdw(userName);
		if (yrdwdm != null && !"".equalsIgnoreCase(yrdwdm)) {// ���˵�λ
			rs = dao.getYrdwKfpgw(yrdwdm);
		} else {
			rs = dao.getGwList();
		}
		return rs;
	}

	/**
	 * ��ȡ�ڹ���ѧ����������Ϣ
	 * 
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwsqsjInfo() {
		return dao.getGwsqsjInfo();
	}

	/**
	 * �ڹ���ѧ������������жϸ�λ����
	 * 
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	public boolean saveQgzxshBatch(QgzxZgdzdxForm model, String yesNo,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String pkString = model.getPk();
		String[] pkValue = pkString.split("!!SplitOneSplit!!");
		String userType = model.getUserType();
		String message = "";
		String tableName = "qgzxsqb";
		String[] columns = { "xysh" };
		String[] values = { yesNo };

		if (userType != null
				&& ("admin".equalsIgnoreCase(userType) || "xx"
						.equalsIgnoreCase(userType))) {
			columns = new String[] { "xxsh" };
		}

		for (int i = 0; i < pkValue.length; i++) {
			if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
				if (yesNo != null && "ͨ��".equalsIgnoreCase(yesNo) && !"xy".equalsIgnoreCase(userType)) {
					if (!dao.checkPostNumber(pkValue[i], userType)) {
						HashMap<String, String> map = dao .getQgzxsqStuInfo(pkValue[i]);
						message += map.get("xm") + "���ʧ��," + map.get("xymc")
								+ map.get("xn") + "ѧ��" + map.get("nd") + "���"
								+ map.get("xqmc") + "ѧ��" + "���ͨ�������Ѿ��ﵽ��������!\n";
					} else {
						flag = StandardOperation.update(tableName, columns,
								values, "xh||xn||nd||xq", pkValue[i], request);
					}
				} else {
					flag = StandardOperation.update(tableName, columns, values,
							"xh||xn||nd||xq", pkValue[i], request);
				}
			}
		}
		request.setAttribute("mes", message);
		return flag;
	}
	
	/**
	 * ���ݴ�ѧ�ڹ���ѧ�����������
	 * 
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	public boolean saveQgzxshBatchByGzdx(QgzxZgdzdxForm model, String yesNo,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String pkString = model.getPk();
		String[] pkValue = pkString.split("!!SplitOneSplit!!");
		String userType = model.getUserType();
		String isFdy=model.getIsFdy();
		String message = "";
		String[] xmdm = StringUtils.isNull(model.getXmdm()) ? null : model
				.getXmdm().split("!!");
		String tableName = "qgzxsqb";
		String[] columns = { "zdgwdm", "zdgwdmgwsbsj", "xysh" };
		String xmdmStr=model.getXmdm()==null ? "" :model.getXmdm();
		String[] values = { model.getGwdm(), xmdmStr.replace("!!",""), yesNo };
		String gwshColumn = "xxyj";
		String xxdm=Base.xxdm;
		String gwxzdm = request.getParameter("gwxzdm");
		if ("true".equalsIgnoreCase(isFdy)){
			columns = new String[] { "zdgwdm", "zdgwdmgwsbsj", "xysh","fdyname" };
		    values = new String[]{ model.getGwdm(), xmdmStr.replace("!!", ""),yesNo, model.getFdyname() };
		}
		if (userType != null
				&& ("admin".equalsIgnoreCase(userType) || "xx"
						.equalsIgnoreCase(userType))) {
			columns = new String[] { "zdgwdm", "zdgwdmgwsbsj", "xxsh" };
		}
		// TODO
		// ---------2010/5/17 edit by luojw -----------
		int n = 0;
		model.setPlczrs(pkValue.length);
		if (pkValue != null && pkValue.length > 0) {

			String[] exec = null;
			if("ͨ��".equalsIgnoreCase(yesNo) 
					&& "001".equals(gwxzdm) 
					&& ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
				exec = new String[pkValue.length * 2];
			} else if ("ͨ��".equalsIgnoreCase(yesNo)) {
				exec = new String[pkValue.length * (xmdm.length + 1)];
			} else if(Globals.XXDM_GZDX.equals(xxdm)){
				exec = new String[pkValue.length * 2];
			}else{
				exec = new String[pkValue.length];
			}
			StringBuffer sql = new StringBuffer();

			for (int i = 0; i < pkValue.length; i++) {

				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					HashMap<String, String> map = new HashMap<String, String>();
					QgzxZgdzdxForm form = new QgzxZgdzdxForm();
					form.setPkValue(pkValue[i]);
					map = dao.selectQgzxsqInfoOne(form);

					model.setXn(map.get("xn"));
					model.setNd(map.get("nd"));
					model.setXq(map.get("xq"));
					model.setXh(map.get("xh"));
					if (null != yesNo && yesNo.equalsIgnoreCase("ͨ��")
							&& null != xmdm ) {
						for (int j = 0; j < xmdm.length; j++) {
							if(!"".equalsIgnoreCase(xmdm[j])){
							String tmp = xmdm[j];
							if (!StringUtils.isNull(tmp)) {
								model.setGwdm(tmp.split("-")[0]);
								model.setGwsbsj(tmp.split("-")[1]);
								if (userType != null
										&& "xy".equalsIgnoreCase(userType)) {
									// ѧԺ�û�
									if (dao.checkPostCountByGzdx(model)) {
										request.setAttribute("mes", "'"
												+ model.getGwdm()
												+ "'�����Ѿ�������������������ʧ�ܣ�");
										return false;
									}
									gwshColumn = "xyyj";
								}
								// ��ѧ����λ��Ϣ�������µ���λ��Ϣ����
								String pk = "xh||gwdm||gwsbsj";
								String pkV = map.get("xh") + model.getGwdm()
										+ model.getGwsbsj();

								if (dao.checkExists("xsgwxxb", pk, pkV)) {
									// ��¼�Ѿ����ڣ������޸Ĳ�����
									sql = new StringBuffer();
									sql.append("update xsgwxxb set ");
									sql.append(gwshColumn);
									sql.append("='ͨ��',sfyx = '1' where ");
									sql.append(pk + "='" + pkV + "'");

									exec[n] = sql.toString();
									n++;

								} else {
									// ��¼�����ڣ����в��������

									sql = new StringBuffer();
									sql.append("insert into xsgwxxb ");
									sql.append("(xh, xn, nd, xq, gwdm");
									sql.append(",gwsbsj, " + gwshColumn
											+ ", sfyx)");
									sql.append("values('" + map.get("xh") + "'");
									sql.append(",'" + map.get("xn") + "'");
									sql.append(",'" + map.get("nd") + "'");
									sql.append(",'" + map.get("xq") + "'");
									sql.append(",'" + model.getGwdm() + "'");
									sql.append(",'" + model.getGwsbsj() + "'");
									sql.append(",'ͨ��'");
									sql.append(",'1')");

									exec[n] = sql.toString();
									n++;

								}
							}
						}
						}
						sql = new StringBuffer();
						sql.append("update " + tableName);
						for (int k = 0; k < columns.length; k++) {
							if (k == 0) {
								sql.append(" set " + columns[k] + "='" + values[k]
										+ "' ");
							} else {
								sql.append(", " + columns[k] + "='" + values[k]
										+ "' ");
							}
						}
						sql.append(" where xh||xn||nd||xq||gwxzdm = '" + pkValue[i]
								+ "'");
						exec[n] = sql.toString();
						n++;
					}else if( yesNo.equalsIgnoreCase("��ͨ��") 
									||yesNo.equalsIgnoreCase("δ���")){
						String shzd="xxsh";
						if (userType != null
								&& "xy".equalsIgnoreCase(userType)) {
							shzd = "xysh";
							gwshColumn="xyyj";
						}
						
						String pk = " b.xh||b.xn||b.nd||b.xq||b.gwxzdm ";
						String pkV = form.getPkValue();
							sql = new StringBuffer();
							sql.append("update  xsgwxxb a set "+gwshColumn+"='"+yesNo+"' where exists ");
							sql.append(" (select * from view_qgzxsqb b where  ");
							sql.append(pk + "='" + pkV + "'");
							sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
							exec[n] = sql.toString();
							n++;
							
						sql = new StringBuffer();
						sql.append("update " + tableName);
						sql.append(" set " + shzd + "='"+yesNo+"' ");
						sql.append(" where xh||xn||nd||xq||gwxzdm = '" + pkValue[i]
								+ "'");
						exec[n] = sql.toString();
						n++;
					}else {
						String pk = " b.xh||b.xn||b.nd||b.xq||b.gwxzdm ";
						String pkV = form.getPkValue();
						sql = new StringBuffer();
						sql.append("update xsgwxxb a set xxyj='ͨ��' where exists ");
						sql.append(" (select * from view_qgzxsqb b where  ");
						sql.append(pk + "='" + pkV + "'");
						sql.append(" and a.gwdm||'-'||a.gwsbsj=b.zdgwdmgwsbsj and a.xh=b.xh) ");
						exec[n] = sql.toString();
						n++;
						
						String shzd="xxsh";
						if (userType != null
								&& "xy".equalsIgnoreCase(userType)) {
							shzd = "xysh";
						}
						sql = new StringBuffer();
						sql.append("update " + tableName);
						sql.append(" set " + shzd + "='ͨ��' ");
						sql.append(" where xh||xn||nd||xq||gwxzdm = '" + pkValue[i]
								+ "'");
						exec[n] = sql.toString();
						n++;
					}
					
				}
			}
			int[] res = dao.runBatch(exec);

			for (int i = 0; i < res.length; i++) {
				flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		}
		// ---------end -----------

		request.setAttribute("mes", message);
		return flag;
	}
	
	/**
	 * ��ȡ��ѯ����
	 * @param models
	 * @return String
	 */
	public String getExportGwzjfpSql(QgzxZgdzdxForm model) {
		return dao.getWhereExportSql(model).toString();
	}

	/**
	 * ��ȡ�ɵ�����������
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getColumn2ExportGwzjfp() {
		String[] col = { "xn", "nd", "xq", "xh", "xm", "xb", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "lxdh","�Ƿ��и�λ", "��λ����" };
		String[] colCN = { "ѧ��", "���", "ѧ��", "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY+"����", Base.YXPZXY_KEY+"����", "רҵ����",
				"רҵ����", "�༶����", "�༶����","��ϵ�绰", "�Ƿ��и�λ", "��λ����" };
		return dao.arrayToList(col, colCN);
	}

	/**
	 * ��ϲ�ѯ���ֶεĲ�ѯ���
	 * 
	 * @param tableName
	 * @param key
	 * @param form
	 * @return String
	 */
	public String getColumnSql2Exp(String[] key, QgzxZgdzdxForm form) {
		String userName = form.getUserName();
		String sql = "select ";
		StringBuffer column = new StringBuffer();// ��ѯ�ֶ�
		String tableName = "";

		if (dao.isYrdw(userName)) {
			String yrdwdm = dao.checkUserIsYrdw(userName);
			form.setYrdwdm(yrdwdm);
			form.setUserType("yrdw");

			tableName = dao.getTableSql(form);
		} else {
			form.setUserType("xx");
			tableName = dao.getTableSql(form);
		}

		for (int i = 0; i < key.length; i++) {// ���Ҫ�����ľ����ֶ�
			column.append(key[i]);
			column.append(",");
		}
		column.deleteCharAt(column.length() - 1);// ȥ������","��
		sql += column;
		sql += " from (" + tableName + ") ";
		return sql;
	}
	
	/**
	 * ��ȡ������ò�����б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getZzmmList(){
		return dao.getZzmmList();
	}
	
	/**
	 * ��ѯ����ѧԺ����������Ϣ
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXyrsfpxx(QgzxZgdzdxForm model){
		return dao.selectXyrsfpxx(model);
	}
	
	/**
	 * ����������ѯ�ڹ���ѧ������Ϣ
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxsqInfoOne(QgzxZgdzdxForm model){
		return dao.selectQgzxsqInfoOne(model);
	}
	
	/**
	 * ɾ���ڹ���ѧ������Ϣ
	 * @param QgzxZgdzdxForm model
	 * @return HashMap<String, String>
	 * */
	public boolean delQgzxsq(QgzxZgdzdxForm model){
		return dao.deleteQgzxsq(model);
	}
	
	/**
	 * ���ݱ��ֶλ�ȡ�ֶ�ע��
	 * @param String[] input
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] input,String tableName){
		return dao.getColumnNameCN(input, tableName);
	}
	
	public List<String[]> queryXsqgzxsqForExport(QgzxZgdzdxForm model,String[] colList){
		return dao.selectXsqgzxsqForExp(model,colList);
	}
	
	/**
	 * ����������λʣ������
	 * 
	 * @author luojw
	 */
	public String getGwsyrs(QgzxZgdzdxForm model) {

		return dao.getGwsyrs(model);
	}
	
	/**
	 * �����ʱ��λ�б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getLsgwList() {

		return dao.getLsgwList();
	}
	
	/**
	 * ������ʱ��λ��Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param arrzd(�����ֶ�)
	 * @param onezd(��һ�ֶ�)
	 * @param notnull(�ǿ��ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveLsgw(QgzxZgdzdxForm model) throws Exception {
		
		DAO tyDAO = DAO.getInstance();
		
		String[] pkValue = model.getCbv();// ����

		List<HashMap<String, String>> qgzxsqInfoList = dao
				.getQgzxsqInfoList(pkValue);

		boolean flag = false;
		
		if (qgzxsqInfoList != null && qgzxsqInfoList.size() > 0) {
			
			StringBuffer sql = new StringBuffer();
			String[] exec = new String[qgzxsqInfoList.size()*4];
			int n = 0;
			
			String gwxzdm = dao.getOneValue("gwxzdmb", "gwxzdm", "gwxzmc", "��ʱ��λ");
			String fdysh = "ͨ��";
			String xysh = "ͨ��";
			String xxsh = "ͨ��";
			String zdgwdm = model.getLsgw();// ��ʱ��λ
			String gwsbsj = dao.getOneValue("gwxxb", "gwsbsj", "gwxz||gwdm",
					gwxzdm + zdgwdm);
			String sqsj = tyDAO.getNowTime("2");
			String sfyx = "1";
			
			for (int i = 0; i < qgzxsqInfoList.size(); i++) {
				
				HashMap<String, String> map = qgzxsqInfoList.get(i);
				
				String xn = map.get("xn");
				String xq = map.get("xq");
				String nd = map.get("nd");
				String xh = map.get("xh");
				String lxdh = map.get("lxdh");
				lxdh = Base.isNull(lxdh) ? "" : lxdh;
				String kcjqgzxsj = map.get("kcjqgzxsj");
				kcjqgzxsj = Base.isNull(kcjqgzxsj) ? "" : kcjqgzxsj;
				String sqly = map.get("sqly");
				sqly = Base.isNull(sqly) ? "" : sqly;
				String bz = map.get("bz");
				bz = Base.isNull(bz) ? "" : bz;
				String yhtc = map.get("yhtc");
				yhtc = Base.isNull(yhtc) ? "" : yhtc;
				String zzmmdm = map.get("zzmmdm");
				zzmmdm = Base.isNull(zzmmdm) ? "" : zzmmdm;
				String kcsgz = map.get("kcsgz");
				kcsgz = Base.isNull(kcsgz) ? "" : kcsgz;
				String jjqk = map.get("jjqk");
				jjqk = Base.isNull(jjqk) ? "" : jjqk;
				
				sql = new StringBuffer();
				sql.append("delete from qgzxsqb ");
				sql.append("where xn = '" + xn + "' ");
				sql.append("and xq = '" + xq + "' ");
				sql.append("and nd = '" + nd + "' ");
				sql.append("and xh = '" + xh + "' ");
				sql.append("and gwxzdm = '" + gwxzdm + "' ");
				exec[n] = sql.toString();
				n++;
				
				sql = new StringBuffer();
				sql.append(" insert into qgzxsqb ");
				sql.append("(xh, xn, nd, xq, lxdh, kcjqgzxsj, sqly,");
				sql.append(" bz,zzmmdm, yhtc, kcsgz,jjqk,xysh,xxsh,gwxzdm,zdgwdm)");
				sql.append(" values(");
				sql.append("'" + xh + "'");
				sql.append(",'" + xn + "'");
				sql.append(",'" + nd + "'");
				sql.append(",'" + xq + "'");
				sql.append(",'" + lxdh + "'");
				sql.append(",'" + kcjqgzxsj + "'");
				sql.append(",'" + sqly + "'");
				sql.append(",'" + bz + "'");
				sql.append(",'" + zzmmdm + "'");
				sql.append(",'" + yhtc + "'");
				sql.append(",'" + kcsgz + "'");
				sql.append(",'" + jjqk + "'");
				sql.append(",'" + xysh + "'");
				sql.append(",'" + xxsh + "'");
				sql.append(",'" + gwxzdm + "'");
				sql.append(",'" + zdgwdm + "'");
				sql.append(")");
				exec[n] = sql.toString();
				n++;
				
				sql = new StringBuffer();
				sql.append("delete from xsgwxxb ");
				sql.append("where xh = '" + xh + "' ");
				sql.append("and gwdm = '" + zdgwdm + "' ");
				sql.append("and gwsbsj = '" + gwsbsj + "' ");
				exec[n] = sql.toString();
				n++;
				
				sql = new StringBuffer();
				sql.append(" insert into xsgwxxb ");
				sql.append("(xh, gwdm,gwsbsj, sqsj, fdyyj,xyyj, xxyj, xn, xq,nd,sfyx)");
				sql.append(" values(");
				sql.append("'" + xh + "'");
				sql.append(",'" + zdgwdm + "'");
				sql.append(",'" + gwsbsj + "'");
				sql.append(",'" + sqsj + "'");
				sql.append(",'" + fdysh + "'");
				sql.append(",'" + xysh + "'");
				sql.append(",'" + xxsh + "'");
				sql.append(",'" + xn + "'");
				sql.append(",'" + xq + "'");
				sql.append(",'" + nd + "'");
				sql.append(",'" + sfyx + "'");
				sql.append(")");
				exec[n] = sql.toString();
				n++;
			}
			
			int[] res = dao.runBatch(exec);

			for (int i = 0; i < res.length; i++) {
				flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		}
		
		return flag;
	}
	
	public HashMap<String, String> qgzxzsPrint(HashMap<String, String> rs) {
		return dao.qgzxzsPrint(rs);
	}
	
	/**
	 * ����ڹ���ѧ������Ϣ
	 * 
	 * @author luojw
	 * 
	 */
	public HashMap<String, String> getQgzxsqInfo(String pkValue) {
		String tableName = "view_qgzxsqb";
		String pk = "xh||xn||nd||xq||gwxzdm";
		String[] colList = new String[] { "kcjqgzxsj", "yhtc", "sqly", "bz" };
		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * ����������ڹ���ѧ��ѧ��
	 * @param model
	 * @return
	 */
	public List<String[]> queryQgzxStu(CommanForm model){
		List<String[]> list = new ArrayList<String[]>();
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colLikeList = new String[]{"xh", "xm"};
		String[] colList = new String[]{"nd", "xn", "xq", "nj", "xydm", "zydm", "bjdm"};
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			
			String[] outputs = new String[]{"xh","nd","xn","xqmc","xm","xymc","zymc","bjmc"};
			
			String sql = "select rownum r, a.* from view_qgzxsqb a" + makeQuery.getQueryString();
			String query = " and xxsh='ͨ��'";
			
			String[] inputs = makeQuery.getInputList();
			list = CommonQueryDAO.commonQuery(sql, query, inputs, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Map<String, String> getQgzxInfo(){
		String sql = "select xn,nd,xq,knsbl from gwsqsjb where rownum=1";
		
		return dao.getMapNotOut(sql, new String[]{});
	}
}

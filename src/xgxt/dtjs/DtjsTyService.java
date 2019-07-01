package xgxt.dtjs;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;
import common.GlobalsVariable;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.zjlg.zbgl.ZbglService;
import xgxt.dtjs.zjlg.zsdy.ZsdyService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyDAO;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class DtjsTyService{

	DtjsDAO dao = new DtjsDAO();

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(DtjsForm myForm, HttpServletRequest request,
			String manu)
			throws Exception {

		String xxdm = Base.xxdm;

		// ========================��ѵ��Ŀ=============================
		if ("pxxx".equalsIgnoreCase(manu)) {
			List<HashMap<String, String>> pxxmList = dao.getDtjsList("pxxmdmb",
					"pxxmdm", "pxxmmc", "", "", "");// ��ѵ��Ŀ
			List<HashMap<String, String>> zsywList = dao.getSelectList("ywlx");// ֤������
			request.setAttribute("pxxmList", pxxmList);
			request.setAttribute("zsywList", zsywList);
		}
		// ========================��ѵ��Ŀ end=============================

		// ========================��չ����=============================
		else if ("fzdx".equalsIgnoreCase(manu)) {
			List<HashMap<String, String>> zsywList = dao.getSelectList("ywlx");// ֤������
			request.setAttribute("zsywList", zsywList);
		}
		// ========================��չ���� end=============================

		// ========================֧����Ա=============================
		else if ("zbdy".equalsIgnoreCase(manu)) {

			// �㽭��
			if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
				// ֧����Ϣ�б�
				ZbglService zbService = new ZbglService();
				String xydm = myForm.getXydm();
				List<HashMap<String, String>> zbList = zbService
						.getZbList(xydm);
				request.setAttribute("zbList", zbList);

				// ֧����Ա����
				List<HashMap<String, String>> dylxList = dao
						.getSelectList("zjlg_dylx");
				request.setAttribute("dylxList", dylxList);
			}
		}
		// ========================֧����Ա end=============================
		
		// ========================��Ա��Ϣ=============================
		else if ("tyxx".equalsIgnoreCase(manu)) {
			
			// ѧ���б�
			List<HashMap<String, String>> xlList = dao.getSelectList("xllx");
			request.setAttribute("xlList", xlList);
			
			// ����������Ϣ
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "hkshen", "hkshi", "hkxian",
					"jg" };
			map = CommonQueryDAO
					.commonQueryOne("view_xsxxb", colList, "xh", xh);
			
			String jg = map.get("jg");
			if (!Base.isNull(jg)) {
				String[] arrDq = jg.split("/");
				map.put("hkshen", (arrDq.length >= 1) ? arrDq[0] : "");
				map.put("hkshi", (arrDq.length >= 2) ? arrDq[1] : "");
				map.put("hkxian", (arrDq.length >= 3) ? arrDq[2] : "");
			}
			
			// ʡ�б�
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);

			// �����������б�
			List<HashMap<String, String>> hkshiList = stuDao.getShiList(
					map.get("hkshen")).get("shiList");
			request.setAttribute("hkshiList", hkshiList);

			// �����������б�
			List<HashMap<String, String>> hkxianList = stuDao.getShiList(
					map.get("hkshi")).get("xianList");
			request.setAttribute("hkxianList", hkxianList);
		}
		// ========================��Ա��Ϣ end=============================
		
		// ========================ͨ�� =============================
		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {//�㽭��
			FormModleCommon.setNjXyZyBjListForDzb(request);
		} else {
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		}
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm", "mz", "zzmm" },
				request);// �Զ���(Ŀǰ�����Ŵ���,����������ò)

		// ��ְ״̬
		List<HashMap<String, String>> zzztList = dao.getSelectList("yesorno");
		request.setAttribute("zzztList", zzztList);

		// �����б�
		List<HashMap<String, String>> ywlxList = dao.getSelectList("ywlx");
		request.setAttribute("ywlxList", ywlxList);
		// ========================ͨ�� end=============================
	}

	/**
	 * ���ѧ����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}

	/**
	 * ��õ�Ա������Ϣ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDtjsList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getDtjsList(tableName, model, colList, other_query);
	}

	/**
	 * ��õ�Ա���������Ϣ����ϸ��
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 */
	public HashMap<String, String> getDtjsInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getDtjsInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ɾ����Ա������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delDtjs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * ���浳Ա���������Ϣ��������
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
	public boolean saveDtjs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * ���浳Ա��Ϣ�����Ϣ��������
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveDtjs(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * ���µ�Ա���������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean updateDtjs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param dzzd(��ַ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		return dao.fileDel(tableName, dzzd, pk, pkValue);
	}
	
	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		return dao.getNowTime(lx);
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	/**
	 * ��Ա���ת�ƣ�������չ���󡪡�>Ԥ����Ա��
	 * 
	 * @author luojw
	 */
	public boolean changeDylx(String befTab, String aftTab, String[] pk,
			String[] zd, String[] key, String[] before, String[] after,String zyzd,String zysj)
			throws Exception {
		
		boolean flg = false;
		
		String groupBy = "xh";
		String orderBy = "xh";
		String distinct = "";
		String other_query = " and zzzt = 'yes'";
		String[] flag = new String[key.length];
		String[] arrXh = new String[key.length];
		String[] arrSql = new String[key.length * 2];
		String pkZd = "";
		int n = 0;
		if (key != null && key.length > 0) {
			for (int i = 0; i < pk.length; i++) {
				if (i == 0) {
					pkZd += pk[i];
				} else {
					pkZd += "||" + pk[i];
				}
			}
			
			List<HashMap<String, String>> befList = dao.getDyxx(key, zd, befTab, pkZd, distinct, orderBy);
			
			if (befList != null && befList.size() > 0) {
				for (int i = 0; i < befList.size(); i++) {
					arrXh[i] = befList.get(i).get("xh");
				}

				for (int i = 0; i < after.length; i++) {
					List<HashMap<String, String>> list = dao.getDyxxRs(key,
							new String[] { groupBy }, after[i], pkZd, distinct,
							groupBy, other_query);
					for (int j = 0; j < key.length; j++) {
						for (int k = 0; k < list.size(); k++) {
							if (arrXh[j]
									.equalsIgnoreCase(list.get(k).get("xh"))) {
								if (Integer.parseInt(list.get(k).get("num")) > 0) {
									if (!"true".equalsIgnoreCase(flag[j])) {
										flag[j] = "true";
									}
								}
							}
						}
						if (!"true".equalsIgnoreCase(flag[j])) {
							flag[j] = "false";
						}
					}
				}

				List<HashMap<String, String>> aftList = dao.getDyxxRs(key, new String[]{"xn","xq","xh"},aftTab, pkZd, distinct, "xn,xq,xh","");
				
				for (int i = 0; i < befList.size(); i++) {
					HashMap<String, String> map = befList.get(i);
					
					String zzzt = "true".equalsIgnoreCase(flag[i]) ? "no" : "yes";				
					boolean cz = false;
					
					if (aftList != null && aftList.size() > 0) {
						for (int j = 0; j < aftList.size(); j++) {
							if(cz == false){
								for (int k = 0; k < pk.length; k++) {
									if(aftList.get(j).get(pk[k]).equalsIgnoreCase(map.get(pk[k]))){
										cz = true;
									}else{
										cz = false;
										break;
									}
								}
							}
						}
					}
					
					if(cz == true){
						arrSql[n] = "update "+ aftTab+" set zzzt='no' where xh='"+map.get("xh")+"'";
						n++;
						
						StringBuffer sb = new StringBuffer();
						sb.append("update " + aftTab + " set ");
						for (int j = 0; j < zd.length; j++) {
							sb.append(zd[j]);
							sb.append("='");
							sb.append(map.get(zd[j]));
							sb.append("',");
						}
						sb.append("zzzt ='" + zzzt + "',");
						sb.append(zyzd + "='" + zysj + "'");
						
						for (int j = 0; j < pk.length; j++) {
							if(j==0){
								sb.append(" where ");
							}else{
								sb.append(" and ");
							}
							sb.append(pk[j]);
							sb.append("='");
							sb.append(map.get(pk[j]));
							sb.append("'");
						}
						
						arrSql[n] = sb.toString();
						n++;
					}else{
						StringBuffer sb = new StringBuffer();
						sb.append("insert into " + aftTab);
						
						for (int j = 0; j < zd.length; j++) {
							if (j == 0) {
								sb.append(" (");
								sb.append(zd[j]);
								sb.append(",");
								sb.append(zyzd);
							} else if (j == zd.length - 1) {
								sb.append(",");
								sb.append(zd[j]);
								sb.append(") ");
							} else {
								sb.append(",");
								sb.append(zd[j]);
							}
						}
						
						for (int j = 0; j < zd.length; j++) {
							if (j == 0) {
								sb.append(" values (");
								sb.append("'");
								sb.append(map.get(zd[j]));
								sb.append("'");
								sb.append(",'");
								sb.append(zysj);
								sb.append("'");	
							} else if (j == zd.length - 1) {
								sb.append(",'");
								sb.append(map.get(zd[j]));
								sb.append("')");
							} else {
								sb.append(",'");
								sb.append(map.get(zd[j]));
								sb.append("'");
							}
						}
						
						arrSql[n] = sb.toString();
						n++;
					}
				}
			}
		}
		flg = dao.saveArrDate(arrSql);
		
		if (flg) {
			flg = dao.saveOtherDylx(before, pkZd, key);
		}
		
		return flg;
	}
	
	/**
	 * ��Ա���ת�ƣ�������չ���󡪡�>Ԥ����Ա��
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveZhdj(String pk, String[] pkValue, String bjmc,
			String zhdj, String zhsj) throws Exception {

		String xn = Base.currXn;//��ǰѧ��
		String xq = Base.currXq;//��ǰѧ��
		String nd = Base.currNd;//��ǰ���
		
		// ��ȡ��Ҫת���ߵ�ѧ��
		String tableName = getZhdjTable(bjmc);
		List<String> xhList = dao.getZyInfoList(tableName, pk, pkValue);
		
		String[] zyxh = null;
		
		if (xhList != null && xhList.size() > 0) {
			zyxh = new String[xhList.size()];
			for (int i = 0; i < xhList.size(); i++) {
				zyxh[i]=xhList.get(i);
			}
		}
		// ��õ��ŵȼ��б�
		List<HashMap<String, String>> djList = dao.getDjList();
		
		// ���ѧ����Ա��Ϣ�б�
		List<HashMap<String, String>> xsdxInfoList = dao.getXsDyInfoList(zyxh);
		
		boolean result = false;

		StringBuffer sql = new StringBuffer();
		String[] exce = new String[xhList.size() * 6];
		int n = 0;

		if (xsdxInfoList != null && xsdxInfoList.size() > 0) {
			// ѧ�����ŵȼ���Ϣ
			for (int j = 0; j < xsdxInfoList.size(); j++) {

				HashMap<String, String> map = xsdxInfoList.get(j);

				String xh = map.get("xh");

				if (djList != null && djList.size() > 0) {

					// ��ת���ȼ��ߵļ����Ƿ�������
					boolean flag = false;
					// ת����
					String zhTable = "";

					for (int k = 0; k < djList.size(); k++) {

						HashMap<String, String> djMap = djList.get(k);
						// ����
						String jb = djMap.get("jb");
						// ����
						String table = djMap.get("tablename");
						// ����
						String num = map.get(table);

						if (!Base.isNull(jb)) {

							// ת���ȼ�
							if (jb.equalsIgnoreCase(zhdj)) {

								//���ת��Ŀ�ı�
								zhTable = table;
								//Ŀ�������Ҫ��ʱ���ֶ�
								String[] sj = getZhdjSj(table);
								
								sql = new StringBuffer();
								sql.append(" update ");
								sql.append(table);
								sql.append(" set zzzt = 'no'");
								sql.append(" where xh = '" + xh + "'");

								exce[n] = sql.toString();
								n++;

								sql = new StringBuffer();
								sql.append(" delete from ");
								sql.append(table);
								sql.append(" where xn = '" + xn + "'");
								sql.append(" and xq = '" + xq + "'");
								sql.append(" and xh = '" + xh + "'");

								exce[n] = sql.toString();
								n++;

								sql = new StringBuffer();
								sql.append(" insert into ");
								sql.append(table);
								sql.append(" (xn,xq,nd,xh");
								
								//ƴ��ʱ���ֶ�
								if (sj != null && sj.length > 0) {
									for (int i = 0; i < sj.length; i++) {
										sql.append("," + sj[i]);
									}
								}
								
								sql.append(" )values(");
								sql.append(" '" + xn + "',");
								sql.append(" '" + xq + "',");
								sql.append(" '" + nd + "',");
								sql.append(" '" + xh + "'");

								// ƴ��ʱ���ֶ�
								if (sj != null && sj.length > 0) {
									for (int i = 0; i < sj.length; i++) {
										sql.append(",'" + zhsj + "'");
									}
								}
								
								sql.append(")");
								exce[n] = sql.toString();
								n++;
							}

							// С��ת�Ƽ���ļ���
							if (Integer.parseInt(jb) < Integer.parseInt(zhdj)) {

								// ������뵳�����
								if (!GlobalsVariable.DTJS_RDSQB
										.equalsIgnoreCase(table)) {

									sql = new StringBuffer();
									sql.append(" update ");
									sql.append(table);
									sql.append(" set zzzt = 'no' ");
									sql.append(" where xh = '" + xh + "'");

									exce[n] = sql.toString();
									n++;
								}
							}

							// ����ת�Ƽ���ļ���
							if (Integer.parseInt(jb) > Integer.parseInt(zhdj)) {

								if (!"0".equalsIgnoreCase(num)) {
									flag = true;
								}
							}
						}
					}

					if (flag) {
						sql = new StringBuffer();
						sql.append(" update ");
						sql.append(zhTable);
						sql.append(" set zzzt = 'no'");
						sql.append(" where xh = '" + xh + "'");

						exce[n] = sql.toString();
						n++;
					}
				}
			}

			result = dao.saveArrDate(exce);
		}
		return result;
	}
	
	/**
	 * ���ת������ı���
	 * 
	 * @author luojw
	 */
	public String getZhdjTable(String djmc) {

		String tableName = "";
		
		if ("�뵳����".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_RDSQB;
		}else if("��������".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_JJFZB;
		}else if("��չ����".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_FZDXB;
		}else if("Ԥ����Ա".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_YBDYB;
		}else if("��ʽ��Ա".equalsIgnoreCase(djmc)) {
			tableName = GlobalsVariable.DTJS_DYXXB;
		}
		
		return tableName;
	}
	
	/**
	 * ���ת������ʱ���ֶ�
	 * 
	 * @author luojw
	 */
	public String[] getZhdjSj(String tableName) {

		String[] sj = null;

		// �������ʽ��Ա�Ļ���Ҫ��������ʱ���ֶ�
		if (GlobalsVariable.DTJS_DYXXB.equalsIgnoreCase(tableName)) {
			sj = new String[2];
		} else {
			sj = new String[1];
		}

		if (GlobalsVariable.DTJS_JJFZB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_JJFZB_KSSJ;
		} else if (GlobalsVariable.DTJS_FZDXB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_FZDXB_KSSJ;
		} else if (GlobalsVariable.DTJS_YBDYB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_YBDYB_KSSJ;
		} else if (GlobalsVariable.DTJS_DYXXB.equalsIgnoreCase(tableName)) {
			sj[0] = GlobalsVariable.DTJS_DYXXB_RDSJ;
			sj[1] = GlobalsVariable.DTJS_DYXXB_ZZSJ;
		}

		return sj;
	}
	
	/**
	 * ����Request ��ֵ
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String writeAble = request.getParameter("writeAble");
		String title = rForm.getTitle();

		// ��ͼ��
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// ����
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// ģ��·��
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// �û�����
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// �û���
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// �û����ڲ���
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(path)) {
			request.setAttribute("userDep", userDep);
		}

		// ��������
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}

		// ��дȨ���
		if (Base.isNull(writeAble)) {
			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0]
					: "";
			if (Base.isNull(title)) {
				title = message != null && message.length >= 2 ? message[1]
						: "";
			}
		}
		request.setAttribute("writeAble", writeAble);

		// ģ�����
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// ����
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// ģ�K���
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// ��ϸ��Ϣ
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// ��ϸ�б���Ϣ
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
		}

		// �����ֶ�
		String[] qtzd = rForm.getQtzd();
		// �����ֶ�ֵ
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}
	
	/**
	 * ��õ��Ž�������������ӿڣ�
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDtjsRsJk(DtjsForm myForm,String lx) {

		HashMap<String, String> map = new HashMap<String, String>();

		//�༶��Ա�� 
		if("ty".equalsIgnoreCase(lx)){
			String tyrs = getBjtyRs(myForm);
			map.put("tyrs", tyrs);
		}
		//�༶��Ա��
		else if("dy".equalsIgnoreCase(lx)){
			map = getBjDyRs(myForm);
		}
		//�뵳���뵳�������ӵ�����������
		else if ("dybl".equalsIgnoreCase(lx)) {

			map = getBjDyRs(myForm);
			//�༶����
			String bjrs = dao.getBjRs(myForm);
			//��Ա����
			String dyrs = map.get("dyrs");
			//Ԥ����Ա����
			String ybdyrs = map.get("ybdyrs");
			//������������
			String jjfzrs = map.get("jjfzrs");
			// ��Ա����
			String dybl = "0";
			// Ԥ����Ա����
			String ybdybl = "0";
			// �������ӱ���
			String jjfzbl = "0";
			// �������
			if (!Base.isNull(bjrs) && !"0".equalsIgnoreCase(bjrs)) {
				dybl = String.valueOf(Math.round(Float.parseFloat(dyrs)
						/ Float.parseFloat(bjrs)*100))
						+ "%";
				ybdybl = String.valueOf(Math.round(Float.parseFloat(ybdyrs)
						/ Float.parseFloat(bjrs)*100))
						+ "%";
				jjfzbl = String.valueOf(Math.round(Float.parseFloat(jjfzrs)
						/ Float.parseFloat(bjrs)*100))
						+ "%";
			}
			map.put("bjrs", bjrs);
			map.put("dybl", dybl);
			map.put("ybdybl", ybdybl);
			map.put("jjfzbl", jjfzbl);
		}
		
		return map;
	}
	
	/**
	 * ��ð༶��Ա����
	 * 
	 * @author luojw
	 */
	public String getBjtyRs(DtjsForm myForm) {
		
		return dao.getBjtyRs(myForm);
	}
	

	/**
	 * ��ð༶��Ա����(����Ԥ����Ա,�뵳��������)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjDyRs(DtjsForm myForm) {
		
		return dao.getBjDyRs(myForm);
	}
	
	/**
	 * �����û�����MAP
	 * 
	 * @param request
	 * @param userName
	 * @param service
	 * @return
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> setUserTypeMap(HttpServletRequest request) {

		HttpSession session = request.getSession();

		// �û���
		String userName = session.getAttribute("userName").toString();

		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �û��Ƿ�֧��������
		String zbdm = getOneValue("zjlg_zbmc", "zbdm", "zgh", userName);
		boolean isZbfzr = Base.isNull(zbdm) ? false : true;

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("fdy", String.valueOf(fdyQx));
		map.put("bzr", String.valueOf(bzrQx));
		map.put("zbfzr", String.valueOf(isZbfzr));

		return map;
	}
	
	
	/**
	 * ����û����������������
	 * 
	 * @param map
	 * @author luojw
	 */
	public String getUserTypeQuery(HashMap<String, String> map) {
		return dao.getUserTypeQuery(map);
	}
}

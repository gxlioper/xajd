
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �����������������Ƚ��༶SERVICE
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-20</p>
 */
public class XjbjService extends PjpyYnysService {

	XjbjDAO dao = null;//�Ƚ��༶DAO
	
	/**
	 * �����Ƚ��༶��Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYnys_xjbjb(XjbjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XjbjDAO();
		return dao.saveYnys_xjbjb(model, request);
	}
	
	/**
	 * �Ƚ��༶��ӡ
	 * @param xn
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> printXjbj(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.printXjbj(pkValue);
		/*HashMap<String, String> rsMap = dao.printXjbj(bjdm); //��ȡ��ӡ����
		WritableSheet ws = wwb.getSheet(0);//д�뵽��һ��sheet
		String tbrq = rsMap.get("tbrq");
		if (!StringUtils.isNull(tbrq) && tbrq.length() == 8) {
			tbrq = "�������:  " + tbrq.substring(0, 4) + "��"
					+ tbrq.subSequence(4, 6) + "��" + tbrq.subSequence(6, 8)
					+ "��";
		}
		//WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
 	    WritableCellFormat tStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);//����������ݾ���
	   // tStyle.setAlignment(Alignment.LEFT);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//���ñ߿򼰱߿�����
	    WritableFont wf = new WritableFont(WritableFont.ARIAL);//���������
		wf.setPointSize(13);
		wcfStyle.setFont(wf);
		tStyle.setFont(wf);
		tStyle.setAlignment(alignMent);
	    //tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    if (!StringUtils.isNull(bjdm) && !StringUtils.isNull(rsMap.get("bjmc")) ) {
	    	ws.addCell(new Label(5, 2, tbrq, tStyle));//�������
		    ws.addCell(new Label(1, 3, rsMap.get("xymc"), wcfStyle));
		    ws.addCell(new Label(4, 3, rsMap.get("nj"), wcfStyle));
		    ws.addCell(new Label(7, 3, rsMap.get("bjmc"), wcfStyle));
		    ws.addCell(new Label(1, 4, rsMap.get("zymc"), wcfStyle));
		    ws.addCell(new Label(5, 4, rsMap.get("bjrs"), wcfStyle));
		    ws.addCell(new Label(7, 4, rsMap.get("nj"), wcfStyle));
		    ws.addCell(new Label(1, 5, rsMap.get("bjdbqk"), wcfStyle));
		    ws.addCell(new Label(1, 6, rsMap.get("bzryj"), wcfStyle));
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
*/	}
	
	/**
	 * �����Ŀ�б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShxmList() throws Exception {
		dao = new XjbjDAO();
		return dao.getShxmList();
	}
	
	/**
	 * �Ƚ��༶��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitle(String userType, String realTable) throws Exception {
		dao = new XjbjDAO();
		String[] enList = null;
		String[] cnList = null;
		String xxdm = StandardOperation.getXxdm();
		if (StringUtils.isEqual("xy", userType)) {//ѧԺ
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//�Ƚ��༶
				enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
						"bjmc", "bjch", "bzr", "yxsh" };
				cnList = new String[] {"pk", "�к�", "ѧ��", "�꼶", "ѧԺ����", "רҵ����",
						"�༶����", "�༶����", "������", "Ժϵ���" };
				if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm)) {
					enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
							"bjmc", "bjch", "bzr","jtch", "yxsh" };
					cnList = new String[] {"pk", "�к�", "ѧ��", "�꼶", "ѧԺ����", "רҵ����",
							"�༶����", "�༶����","����ƺ�", "������", "Ժϵ���" };
				}
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//�����ҵ��
				enList = new String[] {"pk", "rownum", "xh", "xm", "xn", "nj", "xymc", "zymc",
						"bjmc", "grch", "yxsh" };
				cnList = new String[] {"pk", "�к�","ѧ��", "����", "ѧ��", "�꼶", "ѧԺ����", "רҵ����",
						"�༶����", "��������", "Ժϵ���" };
			}
		} else {//ѧУ
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//�Ƚ��༶
				enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
						"bjmc", "bjch", "bzr", "xxsh" };
				cnList = new String[] {"pk", "�к�", "ѧ��", "�꼶", "ѧԺ����", "רҵ����",
						"�༶����", "�༶����", "������", "ѧУ���" };
				if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm)) {
					enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
							"bjmc", "bjch", "bzr", "","xxsh" };
					cnList = new String[] {"pk", "�к�", "ѧ��", "�꼶", "ѧԺ����", "רҵ����",
							"�༶����", "�༶����","����ƺ�", "������", "ѧУ���" };
				}
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//�����ҵ��
				enList = new String[] {"pk", "rownum", "xh", "xm", "xn", "nj", "xymc", "zymc",
						"bjmc", "grch", "xxsh" };
				cnList = new String[] {"pk", "�к�","ѧ��", "����",  "ѧ��", "�꼶", "ѧԺ����", "רҵ����",
						"�༶����", "��������", "ѧУ���" };
			}
			
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * �Ƚ��༶��ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjList(XjbjModel model, String userType, String realTable)
			throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {//ѧԺ
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//�Ƚ��༶
				return dao.getXjbjListByyx(model);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//�����ҵ��
				return dao.getYxbysByxy(model);
			}
			return null;
		} else {//ѧУ
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//�Ƚ��༶
				return dao.getXjbjListByxx(model);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//�����ҵ��
				return dao.getYxbysByxx(model);
			}
			return null;
		}
	}
	
	/**
	 * �Ƚ��༶��˽��
	 * @param keys
	 * @param userType
	 * @param sJg
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xjbjShResult(String[] keys, String userType, String sJg,
			HttpServletRequest request, String realTable) throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {//Ժϵ���
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {
				return dao.yxshXjbjResult(keys, sJg, request);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {
				return dao.yxshYxbysResult(keys, sJg, request);
			}
			return "�������Ŀ!";
		} else if (StringUtils.isEqual("xx", userType)
				|| StringUtils.isEqual("admin", userType)) {// ѧУ���
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {
				return dao.xxshXjbjResult(keys, sJg, request);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {
				return dao.xxshYxbysResult(keys, sJg, request);
			}
			return "�������Ŀ!";
		} else {//�����û�ֱ�ӷ���
			return "";
		}
	}
	
	/**
	 * ��ʾ�Ƚ��༶���������Ϣ
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewXjbjshOne(String pkValue, String userType, String realTable)
			throws Exception {
		dao = new XjbjDAO();
		if (!StringUtils.isNull(realTable)) {
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {
				return dao.viewXjbjshOne(pkValue, userType);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {
				return dao.viewYxbysOne(pkValue, userType);
			}
		}
		return null;
	}
	
	/**
	 * ����б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShList() throws Exception {
		dao = new XjbjDAO();
		return dao.getShList();
	}
	
	/**
	 * ѧУ��������Ƚ��༶���
	 * @param pkValue
	 * @param yesNo
	 * @param yj
	 * @param xyyj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjbjshone(String pkValue, String userType, String yesNo,
			String yj, String xyyj, HttpServletRequest request)
			throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {//ѧԺ���
			return dao.saveXjbjshone(pkValue, yesNo, yj, request);
		} else {//�������
			return dao.saveXjbjshone(pkValue, yesNo, yj, xyyj, request);
		}
	}
	
	/**
	 * �����ҵ��������˽��
	 * @param pkValue
	 * @param userType
	 * @param yesNo
	 * @param yj
	 * @param xyyj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysshone(String pkValue, String userType,
			String yesNo, String yj, String jytyj, HttpServletRequest request)
			throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {// ѧԺ���
			return dao.saveYxbysshone(pkValue, yesNo, yj, request);
		} else {// �������
			return dao.saveYxbysshone(pkValue, yesNo, yj, jytyj, request);
		}
	}
	
	/**
	 * �Ƚ��༶��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjQryTitle(String xxdm) throws Exception {
		dao = new XjbjDAO();
		String[] enList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
				"zymc", "bjmc", "bjch", "bzr", "yxsh", "xxsh" };
		String[] cnList = new String[] { "pk", "�к�", "ѧ��", "�꼶", "ѧԺ����",
				"רҵ����", "�༶����", "�༶����", "������", "Ժϵ���", "ѧ�������" };
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			cnList = new String[] { "pk", "�к�", "ѧ��", "�꼶", "ѧԺ����",
					"רҵ����", "�༶����", "�༶����", "������", "Ժ��ί���", "ѧ�������" };
		} 
		if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm)) {
			enList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
					"zymc", "bjmc", "bjch", "bzr","", "yxsh", "xxsh" };
			cnList = new String[] { "pk", "�к�", "ѧ��", "�꼶", "ѧԺ����",
					"רҵ����", "�༶����", "�༶����","����ƺ�", "������", "Ժϵ���", "ѧ�������" };
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * �Ƚ��༶��ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjQryResult(XjbjModel model, String xxdm) throws Exception {
		dao = new XjbjDAO();
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			return dao.nbzyXjbjQryResult1(model);
		} else if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			return dao.getXjbjQryResult(model); 
		} else {
			return dao.nbzyXjbjQryResult(model);
		}
		
	}
	
	/**
	 * ��ʾ�Ƚ����޸���Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewXjbjxx(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.viewXjbjxx(pkValue);
	}
	
	public HashMap<String, String> nbzyXjbjxx(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.nbzyXjbjxx(pkValue);
	}
	
	public HashMap<String, String> zjjdXjbjxx(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.zjjdXjbjxx(pkValue);
	}
	
	/**
	 * �����޸���Ϣ
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjbjxx(String pkValue, XjbjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XjbjDAO();
		return dao.saveXjbjxx(pkValue, model, request);
	}
	
	/**
	 * ɾ���Ƚ��༶��Ϣ
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delXjbjxx(String[] keys, HttpServletRequest request) throws Exception {
		dao = new XjbjDAO();
		return dao.delXjbjxx(keys, request);
	}
}

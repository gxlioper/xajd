
package xgxt.pjpy.csmz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class PjpyCsmzService {
	
	 PjpyCsmzDAO dao = null;//���ݲ���DAO
	
	/**
	 * ��Ŀ�б�ѡ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqXmList(int type) throws Exception {
		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		if (type == 1) {
			en = new String[] {"shjxj" , "qtjxj"};
			cn = new String[] {"��άѧ��" ,"������ѧ��"};
		}//end if
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			xmList.add(tempMap);//��ѧ����ҳѡ��������Ϣ
		}//end for
		return xmList;
	}
    
	/**
	 * ��ȡѧ�������Ϣ���༶��רҵ��ѧԺ���Ա��꼶,������ѧ�ţ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getStuInfo(xh);
	}
	
	/**
	 * ��ȡ��ѧ�������Ϣ(��ѧ����룬���ƣ������)
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjInfo(HashMap<String, String> jxjMap) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getJxjInfo(jxjMap);
	}
	
	/**
	 * �ж������Ƿ��ظ����ظ�����TRUE����֮����FALSE
	 * isdatacf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param jxjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isDataCf(String xh, String jxjdm, String xn) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.isDataCf(xh, jxjdm, xn);
	}
	
	/**
	 * ������άѧ�𣬳ɹ�����TRUE����֮����FALSE
	 * issaveshjxj ---- ������άѧ�� 
	 * @return
	 * @throws Exception
	 */
	public boolean isSaveShJxj(SaveShJxjModel shjxjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.isSaveShJxj(shjxjModel, request);
	}
	
	/**
	 * ��ѧ���ѯ��ͷ
	 * shjxjtit ---- ��άѧ���ͷ 
	 * @param iType ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShJxjTit(int iType, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyCsmzDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		switch (iType){
		case 1 :{
			topList = dao.getShJxjTit();//��άѧ���ͷ 
			break;
		}
		case 2 :{
			topList = dao.getShJxjTit2(sUserType, sIsFdy);//��άѧ����˲�ѯ��ͷ 
			break;
		}
		}//end case
		return topList;
	}
	
	/**
	 * ��άѧ���ѯ���
	 * shjxjres ----  ��άѧ����
	 * @param shjxjModel(��άѧ���ѯMODEL)
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes(QueryShJxjModel shjxjModel) throws Exception {
		dao = new PjpyCsmzDAO();
		List<String[]> resList = new ArrayList<String[]>();
		resList = dao.getShJxjRes(shjxjModel);
		return resList;
	}

	/**
	 * ͨ��������ȡ��άѧ����Ϣ
	 * shjxjbyPkval ---- ͨ��������ȡ��άѧ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal(String pkValue) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getShJxjByPkVal(pkValue);
	}
	
	/**
	 * ���÷���������ɾ��
	 * @param keys ����
	 * @return
	 * @throws Exception
	 */
	public String delInfoByPk(String[] keys) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.delInfoByPk(keys);
	}

	/**
	 * ��άѧ����˲�ѯ���
	 * shjxjres ---- ��άѧ����˽��
	 * @param shjxjModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes2(QueryShJxjModel shjxjModel, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getShJxjRes2(shjxjModel, sUserType, sIsFdy);
	}
	
	/**
	 * ��άѧ����� (����Ա��ѧԺ��ѧУ)
	 * shjxjsh ---- ��άѧ�����
	 * @param shjxjmodel ��άѧ�����MODEL
	 * @param keys ����
	 * sUserType �û�����
	 * sIsFdy �Ƿ��Ǹ���Ա
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjSh (ShShJxjModel shjxjModel, String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		String sUserType = shjxjModel.getUserType();
		String sIsFdy = shjxjModel.getIsFdy();
		boolean bRes = false;
		if (StringUtils.isEqual(sIsFdy, "true")) {
			dao.shjxjByFdySh(shjxjModel, keys, request);
		}//end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			dao.shjxjByXySh(shjxjModel, keys, request);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			dao.shjxjByXxSh(shjxjModel, keys, request);
		}//end if ѧУ�û�
		return bRes;
	}
	
	/**
	 * ����б���Ϣ
	 * 
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getChkList(iType);
	}
	
	/**
	 * ͨ��������ȡ��άѧ����Ϣ
	 * shjxjbyPkval ---- ͨ��������ȡ��άѧ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal2(String pkValue, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyCsmzDAO();
		String sql = "";
		if (StringUtils.isEqual(sIsFdy, "true")) {
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,drzw,dcj,zcj,tcj,cjmc,zhpfmc,fdysh shjg,fdyyj shyj from view_xsjxjb where xh||jxjdm||xn = ?";
		}//end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,drzw,dcj,zcj,tcj,cjmc,zhpfmc,xysh shjg,xyshyj shyj from view_xsjxjb where xh||jxjdm||xn = ?";
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,drzw,dcj,zcj,tcj,cjmc,zhpfmc,xxsh shjg,xxshyj shyj from view_xsjxjb where xh||jxjdm||xn = ?";
		}// end if ѧУ�û�,����Ա
	    return dao.getShJxjByPkVal2(sql, pkValue);
	}
	
	/**
	 * ��άѧ�𵥸����
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveshjxj(ShShJxjModel shjxjModel, String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		String sUserType = shjxjModel.getUserType();
		String sIsFdy = shjxjModel.getIsFdy();
		boolean bFlag = false;
		if (StringUtils.isEqual(sIsFdy, "true")) {
			bFlag = dao.shjxjByFdySh1(shjxjModel, pkValue, request);
		}//end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			bFlag = dao.shjxjByXySh1(shjxjModel, pkValue, request);
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			bFlag = dao.shjxjByXxSh1(shjxjModel, pkValue, request);
		}// end if ѧУ�û�,����Ա
		return bFlag;
	}
	
	/**
	 * ��ѧ������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjLb() throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getJxjLb();
	}
	
	/**
	 * ����ǰ��������Ƿ��ظ�
	 * @param pk
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pk, String pkValue, String tableName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.chkDataExists(pk, pkValue, tableName);
	}
	
	/**
	 * ���뱣��
	 * @param tableName
	 * @param jxjdmModel
	 * @param rychdmModel
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean dmSave(String tableName, JxjdmModel jxjdmModel, RychdmModel rychdmModel, JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		boolean bFlag = false;
		//��ѧ����������
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			bFlag = dao.saveJxjdm(jxjdmModel, request);
		}
		//�����ƺŴ��������
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			bFlag = dao.saveRychdm(rychdmModel, request);
		}
		//��ѵ���������
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			bFlag = dao.saveJxjxdm(jxjxdmModel, request);
		}
		return bFlag;
	}
	
	/**
	 * ����ɾ��
	 * @param tableName
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean dmDel(String tableName, String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		boolean bFlag = false;
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			bFlag = dao.delJxjdm(pkValue, request);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			bFlag = dao.delRychdm(pkValue, request);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			bFlag = dao.delJxjxdm(pkValue, request);
		}
		return bFlag;
	}
	
	/**
	 * ����ȫ��ɾ��
	 * @param realTable
	 * @param request
	 * @throws Exception
	 */
	public void dmAllDel(String realTable, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		dao.delDm(realTable, request);
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getDmInfo(String pkValue, String tableName) throws Exception {
		dao = new PjpyCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			resMap = dao.getJxjdmInfo(pkValue);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			resMap = dao.getRychdmInfo(pkValue);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			resMap = dao.getJxjxdmInfo(pkValue);
		}
		return resMap;
	}
	
	/**
	 * ��ȡѧ��������
	 * @return
	 * @throws Exception
	 */
	public String getXgbdm() throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getXgbdm();
	}
	
	/**
	 * ���뱣��
	 * @param tableName
	 * @param jxjdmModel
	 * @param rychdmModel
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean dmSave1(String tableName, JxjdmModel jxjdmModel, RychdmModel rychdmModel, JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		boolean bFlag = false;
		//��ѧ����������
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			bFlag = dao.saveJxjdm1(jxjdmModel, request);
		}
		//�����ƺŴ��������
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			bFlag = dao.saveRychdm1(rychdmModel, request);
		}
		//��ѵ���������
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			bFlag = dao.saveJxjxdm1(jxjxdmModel, request);
		}
		return bFlag;
	}
	
	/**
	 * ����û��Ƿ���Ȩ�޸Ĵ���Ϣ
	 * @param xydm
	 * @param dm
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkUserWritable(String xydm, String dm, String pkValue, String tableName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.chkUserWritable(xydm, dm, pkValue, tableName);
	}
	
	/**
	 * ����Ա��ȡ��Ӧ�༶�б�
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetBjList(String fdyName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.fdyGetBjList(fdyName);
	}
	
	/**
	 * ����Ա��ȡ��Ӧרҵ�б�
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetZyList(String fdyName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.fdyGetZyList(fdyName);
	}
	
	/**
	 * ��ѧ�𱨱��ӡ
	 * @param wwb
	 * @param xh
	 * @param jxjdm
	 * @throws Exception
	 */
	public void jxjPrint(WritableWorkbook wwb, String xh, String jxjdm) throws Exception {
		dao = new PjpyCsmzDAO();
		HashMap<String, String> resMap1 = dao.getJxjexpData(xh, jxjdm);
		List<String[]> resList = dao.getJxjexpData1(xh);
		WritableSheet ws = wwb.getSheet(0);//д�뵽��һ��sheet
		
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);//����������ݾ���
	    tStyle.setAlignment(Alignment.LEFT);
	   // wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//���ñ߿򼰱߿�����
	    tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    if (resMap1 != null) {
	    	ws.addCell(new Label(0, 0, String.format("��ɳ����ְҵ����ѧԺ %1$s ѧ��Ƚ�ѧ������ǼǱ�", resMap1.get("xn")), wcfStyle));//�����ͷ
	    	ws.addCell(new Label(1, 1, resMap1.get("xh"), wcfStyle));//���
	    	ws.addCell(new Label(1, 2, resMap1.get("xm"), wcfStyle));
	    	ws.addCell(new Label(1, 3, resMap1.get("xb"), wcfStyle));
	    	ws.addCell(new Label(1, 4, resMap1.get("nj"), wcfStyle));
	    	ws.addCell(new Label(1, 5, resMap1.get("xymc"), wcfStyle));
	    	ws.addCell(new Label(1, 6, resMap1.get("zymc"), wcfStyle));
	    	ws.addCell(new Label(1, 7, resMap1.get("bjmc"), wcfStyle));
	    	ws.addCell(new Label(1, 8, resMap1.get("wysp"), wcfStyle));
	    	
	    	ws.addCell(new Label(4, 1, resMap1.get("nd"), wcfStyle));
	    	ws.addCell(new Label(4, 2, resMap1.get("xn"), wcfStyle));
	    	ws.addCell(new Label(4, 3, resMap1.get("jxjmc"), wcfStyle));
	    	ws.addCell(new Label(4, 4, resMap1.get("jxjlb"), wcfStyle));
	    	ws.addCell(new Label(4, 5, resMap1.get("jlje"), wcfStyle));
	    	ws.addCell(new Label(4, 6, resMap1.get("drzw"), wcfStyle));
	    	ws.addCell(new Label(4, 7, resMap1.get("dnshjxj"), wcfStyle));
	    	ws.addCell(new Label(4, 8, resMap1.get("sjhm"), wcfStyle));
	    	
	    	ws.addCell(new Label(1, 15, resMap1.get("xxjl"), wcfStyle));
	    	ws.addCell(new Label(1, 16, resMap1.get("kycg"), wcfStyle));
	    	ws.addCell(new Label(1, 17, resMap1.get("sqly"), wcfStyle));
	    }
	    
	    if (resList != null) {
	    	String[] tmpList = resList.get(0);
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 11, tmpList[i], wcfStyle));
	    	}
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 12, tmpList[i+7], wcfStyle));
	    	}
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 13, tmpList[i+14], wcfStyle));
	    	}
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 14, tmpList[i+21], wcfStyle));
	    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * �Զ���ȡ��ѧ�������ƺŴ���
	 * @return
	 * @throws Exception
	 */
	public String getAutoJxjId() throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getAutoJxjId();
	}
	
	/**
	 * ��ѧ���޸���Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.viewJxjxx(pkValue);
	}
	
	public boolean stujxjDel(String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.stujxjDel(pkValue, request);
	}
	
	public boolean sturychDel(String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.sturychDel(pkValue, request);
	}
}

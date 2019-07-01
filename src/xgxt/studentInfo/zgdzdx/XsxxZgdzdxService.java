package xgxt.studentInfo.zgdzdx;

import java.io.OutputStream;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.zgkd.XsxxZgkdDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й����ʴ�ѧѧ����ϢService
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-21</p>
 */
public class XsxxZgdzdxService {
	
	/**
	 * ѧ����Ϣ��ѯ
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsxx(XsxxZgdzdxForm model){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		return dao.selectXsxx(model);
	}
	
	/**
	 * ��ȡѧ����Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsxxSearchTopTr(){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		String[] colList = dao.getCols();
		String[] colCNList = dao.getColumnNameCN(colList, "view_xsxxb_zgdzdx");
		return dao.arrayToList(colList, colCNList);
	}
	
	/**
	 * ����Ƿ�����޸�ѧ����Ϣ
	 * */
	public boolean checkXsxxModify(){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		return dao.checkModifyXsxx();
	}
	
	/**
	 * ����ѧԺ�޸ĵ���Ϣ
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoByXy(String yhjs,String xh,String tableName, HttpServletRequest request){
		XsxxZgkdDAO dao = new XsxxZgkdDAO();
		boolean flag = false;
		String[] zdCol = dao.getZdInfoByTab(yhjs, tableName);
		String[] zdVal = new String[zdCol.length];
		
		for(int i=0; i<zdCol.length; i++){
			zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
		}
		try {	
			//����ѧ��������Ϣ
			if(tableName != null && tableName.equalsIgnoreCase("xsxxb")){
				if(!dao.isExists(xh, tableName)){
					if(!dao.isExists(xh, "view_xsjbxx")){
						flag = StandardOperation.insert(tableName, zdCol, zdVal, request);
					}else{
						flag = StandardOperation.update(tableName, "insert into xsxxb(xh,xm,xb,nj,xydm,zydm,bjdm,xz,xy,zymc,bjmc)(select xh,xm,xb,nj,xydm,zydm,bjdm,xz,xymc,zymc,bjmc from view_xsjbxx where xh='"+xh+"')", request);
					}
				}
				flag = StandardOperation.update(tableName, zdCol, zdVal, "xh", xh, request);
			}
			//����ѧ����ͥ��Ϣ
			zdCol = dao.getZdInfoByTab(yhjs, tableName);//������ѧ���ֶ�
			zdVal = new String[zdCol.length];
			String[] zd = new String[zdCol.length+1];
			String[] val = new String[zdCol.length+1];
			for(int i=0; i<zdCol.length; i++){//��ȡҳ���ֶε�ֵ
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				zd[i] = zdCol[i];
				val[i] = zdVal[i];
			}
			zd[zd.length-1] = "xh";
			val[zd.length-1] = xh;
			
			if(tableName !=null && tableName.equalsIgnoreCase("xsfzxxb")){
				if(!dao.isExists(xh, tableName)){//ѧ����ͥ��Ϣ�в����ڸ�ѧ������Ϣ
					flag = StandardOperation.insert(tableName, zd, val, request);
				}else{
					flag = StandardOperation.update(tableName, zd, val, "xh", xh, request);
				}
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ѧ��ע��
	 * @param pkValue
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean stuRegister(String pkValue,HttpServletRequest request) throws Exception{		
		boolean flag = false;
		StuInfoDAO stuDao = new StuInfoDAO();
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		String[] pkValues = pkValue.split("!!");
		String mes = "";
		for(int i=1; i<pkValues.length; i++){
			if(stuDao.zgdzdxCheckZc(pkValues[i])){
				if(!dao.checkExists("xsxxb", "xh", pkValues[i])){//��xsxxb�в������Ȳ���
					StandardOperation.delete("xsxxb", "xh", pkValues[i], request);
					StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,mz,zzmm,csrq,sfzh,syd,lxdh,dzyx,xjztm,pycc,kh,xz)" +
							"(select xh,xm,xb,xydm,zydm,bjdm,nj,mz,zzmm,csrq,sfzh,syd,lxdh,dzyx,xjztm,pycc,kh,xz from view_xsxxb where xh='"+pkValues[i]+"' )", request);
				}
				flag = StandardOperation.update("xsxxb", new String[]{"sfzc"}, new String[]{"��"}, "xh", pkValues[i], request);
			}else{
				mes += "��" + i + "����¼��" + pkValues[i] + "������������ע�ᣬ��ȷ�Ϸ����Ƿ����!\n";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * ѧ������ע��
	 * @param pkValue
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean stuAllRegister(String nj, String xy, String zy, String bj,
			HttpServletRequest request, OutputStream os) throws Exception {
		boolean flag = false;
		StuInfoDAO stuDao = new StuInfoDAO();
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();

		StringBuffer sb = new StringBuffer();
		StringBuffer sq = new StringBuffer();
		
		sq.append(" where 1=1");
		sq.append(Base.isNull(nj)?" and 1=1":" and nj ='"+nj+"'");
		sq.append(Base.isNull(xy)?" and 1=1":" and xydm ='"+xy+"'");
		sq.append(Base.isNull(zy)?" and 1=1":" and zydm ='"+zy+"'");
		sq.append(Base.isNull(bj)?" and 1=1":" and bjdm ='"+bj+"'");
		
		String sql = "select xh from view_xsjbxx " + sq.toString();
		List<String> xhList = dao.getList(sql, new String[]{}, "xh");
	
		String[] mes = null;
		
		if (xhList != null && xhList.size() > 0) {
			mes = new String[xhList.size()];
			for (int i = 0; i < xhList.size(); i++) {
				String xh = xhList.get(i);
				if (stuDao.zgdzdxCheckZc(xh)) {
					sql = "delete from xsxxb where xh='" + xh + "'";
					sb.append(sql);
					sb.append("!!#!!");

					sql = "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,mz,zzmm,csrq,sfzh,"
							+ "syd,lxdh,dzyx,xjztm,pycc,kh,xz) (select xh,xm,xb,xydm,zydm,bjdm,nj,mz,zzmm,"
							+ "csrq,sfzh,syd,lxdh,dzyx,xjztm,pycc,kh,xz from view_xsxxb where xh='"
							+ xh + "' )";
					sb.append(sql);
					sb.append("!!#!!");

					sql = "update xsxxb set sfzc = '��' where xh='" + xh + "'";
					sb.append(sql);
					sb.append("!!#!!");

					mes[i] = "��" + (i + 1) + "����¼��ѧ��" + xh + " ���ע��!";
				} else {
					mes[i] = "��" + (i + 1) + "����¼��ѧ��" + xh
							+ " ������������ע�ᣬ��ȷ�Ϸ����Ƿ����!";
				}
			}
		}
		String[] pksql = sb.toString().split("!!#!!");
		int[] res = dao.runBatch(pksql);
		
		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		printTsExcel(mes,  os);
		return flag;
	}
	
	/**
	 * ѧ����ҵ
	 * @param pkValue
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean stuComplete(String pkValue,HttpServletRequest request) throws Exception{		
		boolean flag = false;
		StuInfoDAO stuDao = new StuInfoDAO();
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		
		String[] pkValues = pkValue.split("!!");
		String mes = "";
		String time = GetTime.getSystemTime();
		for(int i=1; i<pkValues.length; i++){
			if(stuDao.zgdzdxCheckBy(pkValues[i])){
				if(!dao.checkExists("xsxxb", "xh", pkValues[i])){//��xsxxb�в������Ȳ���
					StandardOperation.delete("xsxxb", "xh", pkValues[i], request);
					StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,mz,zzmm,csrq,sfzh,syd,lxdh,dzyx,xjztm,pycc,kh,xz)" +
							"(select xh,xm,xb,xydm,zydm,bjdm,nj,mz,zzmm,csrq,sfzh,syd,lxdh,dzyx,xjztm,pycc,kh,xz from view_xsxxb where xh='"+pkValues[i]+"' )", request);
				}
				flag = StandardOperation.update("xsxxb", new String[]{"nfby","byny","sfyby"}, new String[]{"��",time,"yes"}, "xh", pkValues[i], request);
			}else{
				mes += "��" + i + "����¼��" + pkValues[i] + "�����������ܱ�ҵ����ȷ��!\n";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	/**
	 * ѧ�������ҵ
	 * @param pkValue
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean stuAllComplete(String nj, String xy, String zy, String bj,HttpServletRequest request,OutputStream os) throws Exception{
		
		boolean flag = false;
		StuInfoDAO stuDao = new StuInfoDAO();
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();

		StringBuffer sb = new StringBuffer();
		StringBuffer sq = new StringBuffer();
		
		sq.append(" where 1=1");
		sq.append(Base.isNull(nj)?" and 1=1":" and nj ='"+nj+"'");
		sq.append(Base.isNull(xy)?" and 1=1":" and xydm ='"+xy+"'");
		sq.append(Base.isNull(zy)?" and 1=1":" and zydm ='"+zy+"'");
		sq.append(Base.isNull(bj)?" and 1=1":" and bjdm ='"+bj+"'");
		String sql = "select xh from view_xsjbxx " + sq.toString();
		List<String> xhList = dao.getList(sql, new String[]{}, "xh");
	
		String time = GetTime.getSystemTime();
		String[] mes = null;
		
		if (xhList != null && xhList.size() > 0) {
			mes = new String[xhList.size()];
			for (int i = 0; i < xhList.size(); i++) {
				String xh = xhList.get(i);
				if (stuDao.zgdzdxCheckBy(xh)) {
					if (!dao.checkExists("xsxxb", "xh", xh)) {// ��xsxxb�в������Ȳ���
						sql = "delete from xsxxb where xh='" + xh + "'";
						sb.append(sql);
						sb.append("!!#!!");

						sql = "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,mz,zzmm,csrq,sfzh,"
								+ " syd,lxdh,dzyx,xjztm,pycc,kh,xz) (select xh,xm,xb,xydm,zydm,bjdm,"
								+ " nj,mz,zzmm,csrq,sfzh,syd,lxdh,dzyx,xjztm,pycc,kh,xz from view_xsxxb where xh='"
								+ xh + "' )";
						sb.append(sql);
						sb.append("!!#!!");
					}
					sql = "update xsxxb set nfby= '��',sfyby='yes',byny='"
							+ time + "' where xh='" + xh + "'";
					sb.append(sql);
					sb.append("!!#!!");

					mes[i] = "��" + (i + 1) + "����¼��ѧ��" + xh + " ��ɲ���!\n";
				} else {
					mes[i] = "��" + (i + 1) + "����¼��ѧ��" + xh + " �����������ܱ�ҵ����ȷ��!\n";
				}
			}
		}
		String[] pksql = sb.toString().split("!!#!!");
		int[] res = dao.runBatch(pksql);
		
		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		printTsExcel(mes, os);

		return flag;
	}
	
	/*
	 * ��ʾexcel
	 */
	public void printTsExcel(String[] msg, OutputStream os) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ʾ��Ϣ��", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		if (msg != null && msg.length > 0) {
			for (int i = 0; i < msg.length; i++) {
				ws.addCell(new Label(0, i, msg[i], wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ȡѧ�����͡��������й����ʴ�ѧ��
	 * @param type "lx","lb"
	 * @return
	 */	 
	public List<HashMap<String, String>>  serv_getStuType(String type){
		DAO dao = DAO.getInstance();
		return dao.getStuType(type);
	}
	
	/**
	 * ��ȡ�û��ļ�����
	 * */
	public String getSpeType(String userType){
		String result = "";
		if("xy".equalsIgnoreCase(userType)){
			result = "xy";
		}else if("student".equalsIgnoreCase(userType)){
			result = "student";
		}else{
			result = "xx";
		}
		return result;
	}
	
	/**
	 * ��ѯ�춯������
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYdlbList(){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		return dao.getYdlbList();
	}
	
	/**
	 * ��ʼ��ѧ��ȷ����Ϣ��־
	 * * @return boolean
	 * */
	public boolean initXsqrxxbz(){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		return dao.modifyXsqrxxbz();
	}
	
	/**
	 * �����û������ֶ���Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveBtzdxx(XsxxZgdzdxForm model,HttpServletRequest request){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		return dao.saveYhbtzdxx(model, request);
	}
	
	public boolean saveByxgxx(XsxxZgdzdxForm model,
			boolean isFdy, String userName){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		return dao.updateByxgxx(model, isFdy, userName);
	}
	
	public boolean saveByxgxxByData(XsxxZgdzdxForm model){
		XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
		return dao.updateByxgxxByData(model);
		
	}
}

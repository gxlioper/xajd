/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-24 ����01:45:28 
 */  
package com.zfsoft.xgxt.xszz.zzxmjg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import common.exception.SystemException;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: service 
 * @���ߣ� maxh 
 * @ʱ�䣺 2013-4-24 ����01:45:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class ZzxmjgService extends SuperServiceImpl<ZzxmjgForm, ZzxmjgDao>{

    private ZzxmjgDao dao = new ZzxmjgDao();
	
	public ZzxmjgService(){
		super.setDao(dao);
	}
	/**
	 * 
	 * @����:Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ���Ŀ���ƣ�
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-24 ����04:47:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByZzxmjg(ZzxmjgForm model, String type)throws Exception{
		if("save".equalsIgnoreCase(type)){
			String num=dao.checkExistForSave(model);
			return Integer.valueOf(num) > 0;
		} else {
			String num=dao.checkExistForUpdate(model);
			return Integer.valueOf(num) > 0;
		}
	    		
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�鿴����������Ŀ�����Ϣ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-25 ����06:39:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneZzxmjgList(String  guid) {
		
		
		return dao.getOneZzxmjgList(guid);
	}
	
	/**
	 * �����������б�
	 */
	public void exportHjmdtj(ZzxmjgExportModel model , OutputStream os, User user) throws Exception{
		//ѧ��
		String xn = model.getXn();
		//ѧ��
		String xq = model.getXq();
		//ѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		//��Ŀ���
		String[] xmlb = model.getXmlb();
		//ѧԺ����
//		String[] xydm = model.getXydm();
		//��Ŀ����
		String[] xmmc = model.getXmmc();
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("������", 0);
		
		// ѧԺ�б�
		List<String[]> xymcList = dao.getXyList(xn, xq, xmlb, xmmc,user);
		// ����ѧ�������
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlb, xmmc, user);
		// ������
		List<String[]> xmList = dao.getHjmdList(xn, xq, xmlb, xmmc);
		
		//��Ŀ���
		List<String> xmlbmc = dao.getXmlbmc(xmlb);
		String lbmc = null;
		for (int i = 0; i < xmlbmc.size(); i++) {
			if(i==0){
				lbmc=xmlbmc.get(i);
			}else{
				lbmc=lbmc+","+xmlbmc.get(i);
			}
		}
		
		//��ѧ��������ʽ
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		String title = StandardOperation.getXxmc() + xn + "ѧ��";
		if(!StringUtils.isNull(xq)){ 	
			title += xqmc;
		}
		title += lbmc + "���������";
		ws.addCell(new Label(0, 0, title, jxjFormat));
		ws.mergeCells(0, 0,10, 0);
		
		//xmList ������ 
		//jxjdmList ��Ŀ������
		//xymcList ѧԺ������
		//�����һ����ѧ�����ƺͻ�����
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {//һ����ʼ---------------------------------------------------------
				//����ѧ������д�뵽EXCEL��
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (��" +jxjdmArr[1] +"��)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//���� ��ʼ ����ڶ�����ѧԺ����д��EXCEL��---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
						for (String[] xmArr : xmList) {//���� ��ʼ ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 3
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//���ѧԺ����
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[2] + "��)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//�����������ѧԺ����Ļ��������
						writeHjmdhzExcel(ws, properties, xmByXyList,0);//��������д�뵽EXCEL��
					}
				}//���� ����---------------------------------------------------------
			}//һ������ʼ---------------------------------------------------------
		}
		
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}

	public List<HashMap<String,String>> getXmzzqkhz(ZzxmjgForm model, User user) throws Exception {
		return dao.getXmzzqkhz(model,user);
	}

	public List<HashMap<String,String>> getZzxmInfoByXhXn(String xh, String xn, String xq) {
		return dao.getZzxmjgByXhXnList(xh,xn,xq);
	}

    public List<HashMap<String,String>> getJgExportList(ZzxmjgForm model, User user) throws Exception {
		return dao.getJgExportList(model,user);
    }

    private class JxjExportProperties{
		 int x_axis = 0;//X����
		 int row = 1;//�б�
		 int rowCellCount = 1;//ÿ�е�cell��������
		 int[] maxLength = {7,7,7,7,7,7,7};//Ĭ�ϵ��п�
		public int getX_axis() {
			return x_axis;
		}
		@SuppressWarnings("unused")
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	private void writeHjmdhzExcel(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// ��ʼд������
		properties.x_axis = rowCellCount;// ��ʼ�½�ѧ�������
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				String cellContent = xmList.get(index - 1);
				if (properties.rowCellCount == 8) {
					properties.row++;// ��10���ͻ���
					properties.x_axis = 0;
					properties.rowCellCount = 1;// ����ÿ�м���
				}
				// �ж���������,Ȼ����ݳ��ȿ��ƺϲ���Ԫ�񣬼��������ȵ�����½��л���
//				if (cellContent.length() > 3) {
//					int xmLength = cellContent.length() / 3
//							+ (cellContent.length() % 3 == 0 ? 0 : 1);
//					int pre_x_axis = properties.x_axis;// �ϲ�ǰ��x��ֵ
//					if (pre_x_axis + xmLength > 10) {
//						properties.row++;// ��������������
//						properties.x_axis = rowCellCount;
//						properties.rowCellCount = 1;// ����ÿ�м���
//						pre_x_axis = properties.x_axis;
//					}
//					Label cell = new Label(++properties.x_axis, properties.row,
//							cellContent);
//					ws.addCell(cell);
//					ws.setColumnView(properties.x_axis, 7);
//					pre_x_axis++;
//					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis
//							+ xmLength - 1, properties.row);
//					properties.x_axis = pre_x_axis + xmLength - 1;
//					properties.rowCellCount++;
//				} else {
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
//				}
			}
		}
		properties.row += 1;// �½�ѧ����
	}

	/**
	 * 
	 * @����:������Ŀ���ƣ���ѧ�ꡢѧ��ͳ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-28 ����02:48:35
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		if(xmmc == null){
			logger.debug("��������Ϊ�գ�");
			throw new SystemException("��������Ϊ�գ�");
		}else{
			xmmc = xmmc.trim();
		}
		List<HashMap<String, String>> result = dao.tjrs(xmmc);
		return result;
	}
	/**
	 * 
	 * @����:����ʦ�� ��ѧ���Ի�֤������ȡ(֤�����������+��Ŀ����+��ˮ�ţ�Ĭ�ϴ�0001��ʼ(���꼶��ѧԺ))
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-4����01:38:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return String ��������
	 * @throws
	 */
	public String getZsbm(ZzxmjgForm model) {
		StringBuffer zsbm = new StringBuffer();
		List<HashMap<String, String>> zsbmList = dao.getZsbm(model);
		if (zsbmList.size() > 0) {
			int zsbh=0;
			zsbh = Integer.parseInt(zsbmList.get(0).get("zsbm"))+1;
			StringBuffer zsbhstr=new StringBuffer();
			if(zsbh>99&&zsbh<1000){
				zsbhstr.append("0").append(zsbh);
			}
			else if(zsbh>9&&zsbh<100){
				zsbhstr.append("00").append(zsbh);
			}
			else{
				zsbhstr.append("000").append(zsbh);
			}
			zsbm.append(model.getPdxn()).append(model.getXmdm()).append(zsbhstr);
		} else {
			zsbm.append(model.getPdxn()).append(model.getXmdm()).append("0001");
		}
		return zsbm.toString();
	}
	/**
	 * 
	 * @����:������Ŀ���ƻ�ȡ��Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-34����03:22:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * string �������� 
	 * @throws
	 */
	public String getXmdm(ZzxmjgForm model){
		return dao.getXmdm(model);
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ������Ŀ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgInfoList(String xh) {
		return dao.getZzxmjgInfoList(xh);
	}
	
	public List<HashMap<String, String>> getZzxmhzList(ZzxmjgForm model,User user)  throws Exception{
		return dao.getZzxmhzList(model,user);
	}
	
	//������ѯ������Ŀ������Ϣ
	public List<HashMap<String, String>> zzxmhzView(ZzxmjgForm model,User user,Boolean fyFlag) throws Exception{
		return dao.zzxmhzView(model, user,fyFlag);
	}
	
	//������ѯ������Ŀ������Ϣ������ʦ��ѧԺ���Ի���
	public List<HashMap<String, String>> zzxmhzView_10402(ZzxmjgForm model,User user) throws Exception{
		return dao.zzxmhzView_10402(model, user);
	}
	
	//��ѯѧ��ְ��
	public String getZwForXh(String xh){
		return dao.getZwForXh(xh);
	}
	/**
	 * 
	 * @����:ɸѡ�ɸ��Ƶ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-18 ����03:21:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param knsjgzq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getkfzZqList(String zzjgzq) {
		List<HashMap<String, String>> list = dao.getzzjgZqList();
		for (HashMap<String, String> hm : list) {
			if (hm.get("xn").equals(zzjgzq)) {
				list.remove(hm);
				break;
			}
		}
		return list;
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-18 ����03:37:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lyxn
	 * @param lyxq
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean copy(String lyxn, String mbxn)
			throws Exception {
		boolean result = true;
		List<HashMap<String, String>> list = dao.getZzxmjgOfXnList(lyxn,mbxn);
		ZzxmjgForm model = new ZzxmjgForm();
		for (HashMap<String, String> hm : list) {
			BeanUtils.copyProperties(model, hm);
			model.setGuid(null);
			model.setXn(mbxn);
			result = runInsert(model);
		}
		return result;
	} 
	public ZzxmjgForm getModel(String guid)
	throws Exception {
		return dao.getModel(guid);
	}
	
	public Map<String, String> getJzgInfo(User user){
		return dao.getJzgInfo(user);
	}
	
	public List<HashMap<String,String>> getZzxmjgHzList() {
		return dao.getZzxmjgHzList();
	}
	
	//ɽ��������ҽְҵѧԺ���Ի���ӡ(�����ѧ����ܱ�)
	public File getXmGxhDy_12947_shzxjhzexcel(String parameters,String xn,String xq)
		throws Exception {
	Map<String,Object> data = new HashMap<String,Object>();
	List<HashMap<String, String>> xsxxlist = null;
    String[] xn1 = xn.trim().split(",");
    String[] xq1 = xq.trim().split(",");
    String[] value = parameters.split(",");
    String values = value[0];
    if(xn1.length>xq1.length){
    	for(int i=0;i<xn1.length;i++){
    		HashMap<String, String> xsxxmap = new HashMap<String, String>();
        	for(int j=0;j<xq1.length;j++){
        	 xsxxmap = dao.getShzxjhz(values, xn1[i], xq1[i]);
        	 continue;
        	}
        	 xsxxlist.add(xsxxmap);
    	}    
    }else if(xn1.length == xq1.length){
    	for(int i=0;i<xn1.length;i++){
    		HashMap<String, String> xsxxmap = new HashMap<String, String>();
        	for(int j=0;j<xq1.length;j++){
        	 xsxxmap = dao.getShzxjhz(values, xn1[i], xq1[j]);
        	}
        	xsxxlist.add(xsxxmap);
    	}    
    }else{
    	for(int i=0;i<xq1.length;i++){
    		HashMap<String, String> xsxxmap = new HashMap<String, String>();
        	for(int j=0;j<xn1.length;j++){
        	 xsxxmap = dao.getShzxjhz(values, xn1[j], xq1[i]);
        	 continue;
        	}
        	xsxxlist.add(xsxxmap);
    	}    
    }
	HashMap<String, String> totalxx = dao.getshzxjTotal(values);
	data.put("xsxxlist",xsxxlist);
	data.putAll(totalxx);
	if(xsxxlist != null){
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_shzxjhzb.xml", "�����ѧ��ѧ�����ܱ�");
		return excelFile;
	}
	
	return null;

    }
	
	/*ɽ��������ҽְҵѧԺ���Ի���ӡ(������־��ѧ����ܱ�)
	public File getXmGxhDy_12947_gjlzjhzexcel(String[] parameters,User user)
		throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> xsxxlist = dao.getShzxjHzbxxList(parameters);
		data.put("xsxxlist",xsxxlist);
		String jbr = user.getRealName();
		String username = user.getUserName();
		String lxdh = (dao.getlxfs(username)).get("lxdh");
	    data.put("jbr", jbr);
	    data.put("lxdh", lxdh);
	    data.put("xx",Base.xxmc);
	    SimpleDateFormat datenow = new SimpleDateFormat("yyyy-MM-dd");
	    String date = datenow.format(new Date());
	    String year = date.substring(0, 4);
	    String month = date.substring(5, 7);
	    String day = date.substring(8, 10);
	    data.put("year", year);
	    data.put("month", month);
	    data.put("day", day);
	    data.put("xn", Base.currXn);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_gjlzjhz.xml", "������־��ѧ��ѧ�����ܱ�");
		return excelFile;

    }*/
	
	//ɽ��������ҽְҵѧԺ���Ի���ӡ(������ѧ�𱸰����ܱ�)
	public List<HashMap<String, String>> getXmGxhDy_12947_gjzxjbahzexcel(String parameters,String xn,String xq)
		throws Exception {
		List<HashMap<String, String>> xsxxlist = dao.getShzxjHzbxxList(parameters,xn,xq);
		return xsxxlist;
    }
	
	public File getXmGxhDy_12947_gjlzjhzmbexcel(String[] parameters)
		throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> xsxxlist = dao.getLzjHzXsxx(parameters);
		data.put("xsxxlist",xsxxlist);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_gjlzjhzmb.xml", "������־��ѧ�����ģ��");
		return excelFile;
	}
	
	/**
	 * 
	 * @����:��ȡ�ڹ���ѧlist
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-6 ����11:53:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQgzxList(String xh){
		return dao.getQgzxList(xh);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ����list
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-6 ����03:11:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getMjxList(String xh,String xmmc){
		return dao.getMjxList(xh, xmmc);
	}
	
	/**
	 * 
	 * @����:�Ƿ������ѧ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-7 ����02:15:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSfzxDk(String xh){
		return dao.getSfzxDk(xh);
	}
	
	/**
	 * 
	 * @����:�ж��Ƿ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-7 ����03:44:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>  getSfxs(){
		return dao.getSfxs();
	}
	
	/**
	 * 
	 * @����:�ж��Ƿ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-7 ����03:49:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSfkns(String xh,String xn){
		return dao.getSfkns(xh, xn);
	}
	
	/**
	 * 
	 * @����:��ȡȫƴƴ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-8 ����09:55:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPinyin(String str){
		return  dao.getPinyin(str);
	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר������Ŀ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-27 ����04:40:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDxscbInfoList(ZzxmjgForm model,User user) throws Exception{
		return dao.getDxscbInfoList(model, user);
	}
	
	/**
	 * @����:TODO(��������ְҵ����ѧԺ���Ի�)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-8-5 ����02:53:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLnjdzyjsxyList(ZzxmjgForm model,User user) throws Exception{
		return dao.getLnjdzyjsxyList(model, user);
	}
	
	/**
	 * 
	 * @����: �Ϻ�����������Ŀ���ܱ��ܽ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-14 ����05:14:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String[] getZje_shty(String[] param){
		return dao.getZje_shty(param);
	}
	
	/**
	 * 
	 * @����:�Ϻ�����������Ŀ���ܱ�רҵ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-14 ����05:34:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getzyhz_shty(String[] param){
		return dao.getzyhz_shty(param);
	}
	
	/**
	 * 
	 * @����:�Ϻ�����������Ŀ���ܱ��꼶���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����09:25:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjhz_shty(String[] param){
		return dao.getnjhz_shty(param);
	}
	/**
	 * 
	 * @����: �Ϻ�����ѧԺרҵ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����10:13:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getxyzyList_shty(){
		return dao.getxyzyList_shty();
	}
	
	/**
	 * 
	 * @����:�Ϻ�����ѧԺ�꼶�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����10:18:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjList(){
		return dao.getnjList();
	}
	
	/**
	 * 
	 * @����: �Ϻ������꼶ѧԺ������Ŀ����������ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����10:21:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjxyzy_shty(String[] param){
		return dao.getnjxyzy_shty(param);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �Ϻ�����������Ŀ����ͳ�Ʋ�ѯ����excel
	 * @ʵ�֣�һ����ȫ��ȡ��дexcel��Ҫ�����ݣ�����ѭ����ȥ���õײ��ѯ����
	 * дexcel��ȡǶ�׵����ķ�ʽ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����11:11:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @return
	 * WritableWorkbook �������� 
	 * @throws
	 */
	public  void createWwb(String[] param,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		/**
		 * ��ȡ����excel���ĺ�̨����
		 */
		//�꼶List
		List<HashMap<String, String>> njList = this.getnjList();
		//רҵList
		List<HashMap<String, String>> xyzyList = this.getxyzyList_shty();
		//����ѯ����
		List<HashMap<String, String>> zcxtjList = this.getnjxyzy_shty(param);
		//�꼶��������
		List<HashMap<String, String>> njjeList = this.getnjhz_shty(param);
		//רҵ��������
		List<HashMap<String, String>> zyjeList = this.getzyhz_shty(param);
		//ѧԺList
		List<HashMap<String, String>> xyList = this.getXyList();
		//�ܽ��
		String[] zhsj = this.getZje_shty(param);
		int njNum = njList.size();
		int xyNum = xyList.size();
		int zyNum = xyzyList.size();
		int zcxNum = zcxtjList.size();
		int njjeNum = njjeList.size();
		/**
		 * һ���꼶ռ���У�����njNum*2
		 */
		int tableColumnNum = njNum*2+4;
		/**
		 * ȡ��������ʽ
		 */
		WritableCellFormat fonthead =  this.getFontStyle("head");
		WritableCellFormat fontbodyright = this.getFontStyle("bodyright");
		WritableCellFormat fontbodyleft = this.getFontStyle("bodyleft");
		WritableCellFormat fonttitle = this.getFontStyle("title");
		 WritableSheet ws = wwb.createSheet("������Ŀ����������", 0);
		 /**
		  * д�����
		  */
		 //���ֻ�е�����Ŀ����ӡ������Ŀ���������������Ŀ
		 String titilemc = "������Ŀ";
		 if(param.length == 1){
			 titilemc = this.getZzxmMc(param[0]);
		 }
		 Label labelTitle= new Label(0, 0, "�Ϻ�����ѧԺ"+titilemc+"�����������ܱ�",fonttitle);
		 //�ϲ���Ԫ������Ϊ����������s
		 ws.mergeCells(0, 0, tableColumnNum-1, 0);
		 ws.addCell(labelTitle);
		 
		 /**
		  * ��ͷ
		  */
		 /**
		  * ��ͷǰ���й̶���=ֱ�Ӽ�
		  */
		 
		 Label row2col1= new Label(0, 1, "",fonthead);
		 Label row2col2= new Label(1, 1, "",fonthead);
		 Label row3col1= new Label(0, 2, "Ժϵ/��",fonthead);
		 Label row3col2= new Label(1, 2, "רҵ",fonthead);
		 ws.addCell(row2col1);
		 ws.addCell(row2col2);
		 ws.addCell(row3col1);
		 ws.addCell(row3col2);
		 /**
		  * �ӵ����п�ʼ������������Ϊֹ���ǲ��̶��ģ���Ϊ�꼶�����Ƕ�̬�ģ���ȷ��
		  */
		 for (int i = 2; i < 2+njNum*2; i=i+2) {
			 //�꼶��һ��
			 if(i+1 % 2 !=0){
					 Label label = new Label(i,1,njList.get(i-2-(i-2)/2).get("nj"),fonthead);
					 ws.addCell(label);
			 }
//			 Label label = new Label(i,1,njList.get(i-2).get("nj"),fonthead);
			
			 ws.mergeCells(i, 1, i+1, 1);
			 for (int j = i; j < i+2; j++) {
				 if((j+1)%2 != 0){
					 Label labelZb = new Label(j,2,"����",fonthead);
					 ws.addCell(labelZb);
				 }else{
					 Label labelZb = new Label(j,2,"���",fonthead);
					 ws.addCell(labelZb);
				 }
				 
			 }
		 }
		 //��ͷ�������
		 Label labelLastTwo = new Label(2+njNum*2,1,"����С��", fonthead);
		 Label labelLastOne = new Label(2+njNum*2+1,1,"���С��", fonthead);
		 ws.addCell(labelLastTwo);
		 ws.addCell(labelLastOne);
		 ws.mergeCells(2+njNum*2, 1, 2+njNum*2, 2);
		 ws.mergeCells(2+njNum*2+1, 1, 2+njNum*2+1, 2);
		 //������ʼֵ
		 int countRow = 3;
		 //һ��ѭ�������ѧԺ
		 for(int i=0;i<xyNum;i++){
			 HashMap<String, String> nowXy = xyList.get(i);
			 int zygs = Integer.parseInt(nowXy.get("zygs"));
			 Label labelXy = new Label(0, countRow, nowXy.get("xymc"), fontbodyleft);
			 ws.addCell(labelXy);
			 ws.mergeCells(0, countRow,0, countRow+zygs-1);
			 //�������������רҵList��ȡ����ѧԺ�µ�����רҵ
			 List<HashMap<String, String>> tempZyList = new ArrayList<HashMap<String,String>>(); 
			 for (int zylen = 0; zylen < zyNum; zylen++) {
				if(xyzyList.get(zylen).get("xydm").equals(nowXy.get("xydm"))){
					tempZyList.add(xyzyList.get(zylen));
				}
			 }
			 //����ѭ�������רҵ
			 for (int j = 0; j < zygs; j++) {
				Label labelzy = new Label(1, countRow+j, tempZyList.get(j).get("zymc"),fontbodyleft);
				ws.addCell(labelzy);
				//����ѭ�����������ÿ��רҵ���� ����������������С�ƣ����С�ƣ�����רҵ�������ݣ�������
				//ÿ��רҵ������ͬ�꼶��ֵ��������ѭ���꼶
				for (int k = 0; k < njNum; k++) {
					HashMap<String, String> nowNj = njList.get(k);
					HashMap<String, String> comareObj = new HashMap<String, String>();
					String mhrs = "0";
					String mhje = "0.00";
					for (int k2 = 0; k2 < zcxNum; k2++) {
						 comareObj = zcxtjList.get(k2);
						 mhrs = comareObj.get("mhrs");
						 mhje = comareObj.get("mhje");
						boolean flag = comareObj.get("xydm").equals(nowXy.get("xydm"))&&
						comareObj.get("zydm").equals(tempZyList.get(j).get("zydm"))&&comareObj.get("nj").equals(nowNj.get("nj"));
					    if(flag){
						   break;
					    }
					}
					   Label labelrs = new Label(2+(k-0)*2, countRow+j, mhrs, fontbodyright);
					   ws.addCell(labelrs);
					   Label labelje = new Label(2+(k-0)*2+1, countRow+j, mhje, fontbodyright);
					   ws.addCell(labelje);
					//���ÿ��רҵ�������ȫ���Ѿ�д�꣬�ٲ���ÿ��רҵ���������ݣ�����С�ƣ����С�ƣ�
					 if(k==njNum-1){
						String zyrs = "0";
						String zyje = "0.00";
						
						for (int l = 0; l < zyjeList.size(); l++) {
							if(zyjeList.get(l).get("zydm").equals(tempZyList.get(j).get("zydm"))){
								zyrs = zyjeList.get(l).get("zyrs");
								zyje =  zyjeList.get(l).get("zyje");
							}
						}
						Label labelzyhzrs = new Label(2+(k-0)*2+1+1, countRow+j, zyrs, fontbodyright);
						ws.addCell(labelzyhzrs);
						Label labelzyhzje = new Label(2+(k-0)*2+1+1+1, countRow+j, zyje, fontbodyright);
						ws.addCell(labelzyhzje);
					}
				}
			 }
			 countRow = countRow+zygs;
		 }
		 //�꼶��������
		 for (int i = 0; i < njjeNum; i++) {
			HashMap<String, String> njjeMap = njjeList.get(i);
			Label njrs = new Label(2+(i-0)*2, zyNum+3, njjeMap.get("njrs"), fontbodyright);
			Label njje = new Label(2+(i-0)*2+1, zyNum+3, njjeMap.get("njje"), fontbodyright);
			ws.addCell(njrs);
			ws.addCell(njje);
		 }
		 //�ܺ�
		 Label sumrs = new Label(tableColumnNum-2, zyNum+3, zhsj[1], fontbodyright);
		 Label sumje = new Label(tableColumnNum-1, zyNum+3, zhsj[0], fontbodyright);
		 ws.addCell(sumrs);
		 ws.addCell(sumje);
		 WritableCellFormat hj = this.getFontStyle("bodyleft");
		 hj.setAlignment(jxl.format.Alignment.CENTRE);
		 ws.addCell(new Label(0,zyNum+3,"��  ��", hj));
		 try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
				
		 }
	}
	
	/**
	 * @throws WriteException 
	 * 
	 * @����: ��ȡ��Ԫ��������ʽ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����01:27:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paras
	 * @return
	 * WritableFont �������� 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras) throws WriteException{
		/** 
         * ���嵥Ԫ����ʽ 
         */ 
		if("title".equals(paras)){
			 WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 20,  
             WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
             jxl.format.Colour.BLACK); // �����ʽ ���� �»��� б�� ���� ��ɫ  
			 WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // ��Ԫ����  
	         wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // ���ö��뷽ʽ  
	         return wcf_title;
		}else if("head".equals(paras)){
		     WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK); 
			  WritableCellFormat wcf_head = new WritableCellFormat(wf_head);   
		      wcf_head.setAlignment(jxl.format.Alignment.CENTRE);
		      wcf_head.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		      return wcf_head;
		}else if("bodyright".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 8,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK); 
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
	          //wcf_table.setBackground(jxl.format.Colour.BLACK);   
	          wcf_table.setAlignment(jxl.format.Alignment.RIGHT);  
	          return wcf_table;
		}else if("bodyleft".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 8,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.LEFT);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}
     
        return null;
	}
	
	/**
	 * 
	 * @����: �Ϻ�����ѧУ��ȡѧԺ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����02:20:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyList(){
	    return dao.getXyList();
	}
	
	/**
	 * 
	 * @����: ������Ŀ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-17 ����04:19:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paras
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZzxmMc(String paras){
		return dao.getZzxmMc(paras);
	}
	
	/** 
	 * @����:�������(�ൺ�Ƶ����ѧԺ���Ի�)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-2 ����05:46:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String,Object> saveDrForQdjd(ZzxmjgForm form) throws FileNotFoundException, IOException, SQLException, BiffException {
		InputStream is = form.getDrmb().getInputStream();
		Workbook wb = Workbook.getWorkbook(is);
		HashMap<String,Object> map = new HashMap<String, Object>();
		Sheet sheet = wb.getSheet(0);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		if(cols < 9 && rows < 2){
			map.put("result", "null");
			return map;
		}else {
			List<ZzxmjgForm> list = new ArrayList<ZzxmjgForm>();
			int num = 0;
			for(int i = 1;i<rows;i++){
				ZzxmjgForm zf = new ZzxmjgForm();
				String xh = sheet.getCell(0, i).getContents();
				String sfzh = sheet.getCell(1, i).getContents();
				String xn = sheet.getCell(2, i).getContents();
				String xqmc = sheet.getCell(3, i).getContents();
				String pdxn = sheet.getCell(4, i).getContents();
				String pdxqmc = sheet.getCell(5, i).getContents();
				String lbmc = sheet.getCell(6, i).getContents();
				String xmmc = sheet.getCell(7, i).getContents();
				String je = sheet.getCell(8, i).getContents();
				if("".equalsIgnoreCase(xh) && "".equalsIgnoreCase(sfzh) && "".equalsIgnoreCase(xn) && "".equalsIgnoreCase(xqmc) && "".equalsIgnoreCase(pdxn) && "".equalsIgnoreCase(pdxqmc) && "".equalsIgnoreCase(lbmc) && "".equalsIgnoreCase(xmmc) && "".equalsIgnoreCase(je)){
					num++;
					continue;
				}
				zf.setXh(xh);
				zf.setSfzh(sfzh);
				zf.setXn(xn);
				zf.setXqmc(xqmc);
				zf.setPdxn(pdxn);
				zf.setPdxqmc(pdxqmc);
				zf.setLbmc(lbmc);
				zf.setXmmc(xmmc);
				zf.setJe(je);
				list.add(zf);				
			}
			if(num == rows-1) {//���ȫΪ����
				map.put("result", "null");
				return map;
			}else{
				HashMap<String,Object> resultMap = checkEveryRow(list);
				if(resultMap.get("errorList") != null){//����д�������
					map.put("result", "false");
					List<ZzxmjgForm> errorList = (List<ZzxmjgForm>) resultMap.get("errorList");
					String gid = upLoadErrorXx(form.getFilepath(), errorList);//�ϴ�������Ϣ��excel�ļ�
					map.put("gid", gid);//�����ļ�·��
					map.put("cws", String.valueOf(errorList.size()));//��������
				}else{
					map.put("result", "true");
				}
				List<ZzxmjgForm> trueList = (List<ZzxmjgForm>) resultMap.get("trueList");
				if(null != trueList && trueList.size()>0){//��ȷ����
					List<String[]> insertList = new ArrayList<String[]>();
					for(ZzxmjgForm model : trueList){
						String[] params = new String[8];
						params[0] = model.getXh();
						params[1] = model.getXn();
						params[2] = model.getXq();
						params[3] = model.getPdxn();
						params[4] = model.getPdxq();
						params[5] = model.getLbdm();
						params[6] = model.getXmmc();
						params[7] = model.getJe();
						insertList.add(params);
					}
					int[] p = dao.DrForInsert(insertList);//����������֤ͨ��������
					map.put("cgs", String.valueOf(p.length));//����ɹ�����
				}
			}
		}
		return map;		
	}
	
	/** 
	 * @����:��֤ÿ����¼(�ൺ�Ƶ����ѧԺ���Ի�)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-2 ����05:49:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,Object> checkEveryRow(List<ZzxmjgForm> list){
		HashMap<String,Object> map = new HashMap<String, Object>();
		List<ZzxmjgForm> trueList = new ArrayList<ZzxmjgForm>();//��ȷ������
		List<ZzxmjgForm> errorList = new ArrayList<ZzxmjgForm>();//���������
		for(ZzxmjgForm form : list){
			if(checkBtzdForEveryRow(form)){
				trueList.add(form);
				map.put("trueList", trueList);
			}else{
				errorList.add(form);
				map.put("errorList", errorList);
			}
		}		
		return map;
	}
	
	/** 
	 * @����:��֤ÿ�е�ÿ���ֶ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����10:58:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkBtzdForEveryRow(ZzxmjgForm form){
		boolean result = true;
		if("".equals(form.getXh()) && "".equals(form.getSfzh())){
			form.setYlzd4("����дѧ�Ż����֤��");
			return false;
		}else if(!"".equalsIgnoreCase(form.getXh()) && "".equalsIgnoreCase(form.getSfzh())){
			result = dao.checkXhExist(form);
			if(!result){
				form.setYlzd4("ѧ�Ų�����");
				return false;
			}
		}else if(!"".equalsIgnoreCase(form.getSfzh()) && "".equalsIgnoreCase(form.getXh())){
			result = dao.checkSfz(form);
			if(!result){
				form.setYlzd4("���֤�Ų�����");
				return false;
			}
		}else if(!"".equalsIgnoreCase(form.getSfzh()) && !"".equalsIgnoreCase(form.getXh())){
			result = dao.checkXhAndSfzh(form);
			if(!result){
				form.setYlzd4("ѧ�������֤�Ų���");
				return false;
			}
		}
		
			if("".equalsIgnoreCase(form.getXn())){
				form.setYlzd4("����д����ѧ��");
				return false;
			}
			if("".equalsIgnoreCase(form.getXqmc())){
				form.setYlzd4("����д����ѧ��");
				return false;
			}else{
				result = dao.checkXq(form,"xq");
				if(!result){
					form.setYlzd4("��������ȷ��ѧ��");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getPdxn())){
				form.setYlzd4("����д����ѧ��");
				return false;
			}
			if("".equalsIgnoreCase(form.getPdxqmc())){
				form.setYlzd4("����д����ѧ��");
				return false;
			}else{
				result = dao.checkXq(form,"pdxq");
				if(!result){
					form.setYlzd4("��������ȷ������ѧ��");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getLbmc())){
				form.setYlzd4("����д�������");
				return false;
			}else{
				result = dao.checkXmlb(form);
				if(!result){
					form.setYlzd4("��������ȷ���������");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getXmmc())){
				form.setYlzd4("����д��Ŀ����");
				return false;
			}else{
				if(form.getXmmc().length()>50){
					form.setYlzd4("��Ŀ������������50����");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getJe())){
				form.setYlzd4("����д���");
				return false;
			}else{
				if(!StringUtils.isNumeric(form.getJe())){
					form.setYlzd4("�������������");
					return false;
				}
			}				
		return true;
	}
	
	/**
	 * @throws IOException  
	 * @����:�ϴ������ļ���Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����02:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param path
	 * @param errorList
	 * void �������� 
	 * @throws 
	 */
	public String upLoadErrorXx(String path,List<ZzxmjgForm> errorList) throws IOException{
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(path+fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		WritableWorkbook book = Workbook.createWorkbook(file);
		WritableSheet sheet = book.createSheet("������Ϣ", 0);
		 Label labelxh= new Label(0, 0, "ѧ��");//��ʾ��
         Label labelsfzh= new Label(1, 0, "���֤��");
         Label labelsqxn= new Label(2, 0, "����ѧ��");
         Label labelxqmc= new Label(3, 0, "����ѧ������");
         Label labelpdxn= new Label(4, 0, "��Ŀ����ѧ��");
         Label labelpdxq= new Label(5, 0, "��Ŀ����ѧ������");
         Label labellbmc= new Label(6, 0, "�������");
         Label labelxmmc= new Label(7, 0, "��Ŀ����");
         Label labelje = new Label(8, 0, "���");
         Label labelxx = new Label(9,0,"������ʾ");
         
         try {
			 sheet.addCell(labelxh);
			 sheet.addCell(labelsfzh);
			 sheet.addCell(labelsqxn);
			 sheet.addCell(labelxqmc);
			 sheet.addCell(labelpdxn);
			 sheet.addCell(labelpdxq);
			 sheet.addCell(labellbmc);
			 sheet.addCell(labelxmmc);
			 sheet.addCell(labelje);
			 sheet.addCell(labelxx);
		} catch (RowsExceededException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		 try {
			 WritableCellFormat wcf = new WritableCellFormat();
			 WritableFont wf = new WritableFont(WritableFont.ARIAL);
			 wf.setColour(Colour.RED);
			 wcf.setFont(wf);
			 wcf.setAlignment(Alignment.CENTRE);
			for(int i = 0;i<errorList.size();i++){
				 Label labelxh1= new Label(0, i+1,errorList.get(i).getXh());//
			     Label labelsfzh1= new Label(1, i+1, errorList.get(i).getSfzh());
			     Label labelsqxn1= new Label(2, i+1, errorList.get(i).getXn());
			     Label labelxqmc1= new Label(3, i+1, errorList.get(i).getXqmc());
			     Label labelpdxn1= new Label(4, i+1, errorList.get(i).getPdxn());
			     Label labelpdxq1= new Label(5, i+1, errorList.get(i).getPdxqmc());
			     Label labellbmc1= new Label(6, i+1, errorList.get(i).getLbmc());
			     Label labelxmmc1= new Label(7, i+1, errorList.get(i).getXmmc());
			     Label labelje1 = new Label(8, i+1, errorList.get(i).getJe());
			     Label labelxx1 = new Label(9, i+1, errorList.get(i).getYlzd4(),wcf);//������Ϣ 
			     sheet.addCell(labelxh1);
				 sheet.addCell(labelsfzh1);
				 sheet.addCell(labelsqxn1);
				 sheet.addCell(labelxqmc1);
				 sheet.addCell(labelpdxn1);
				 sheet.addCell(labelpdxq1);
				 sheet.addCell(labellbmc1);
				 sheet.addCell(labelxmmc1);
				 sheet.addCell(labelje1);
				 sheet.addCell(labelxx1);
			}
		} catch (RowsExceededException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}finally{
			book.write();
			try {
				book.close();
			} catch (WriteException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return fileName;
         
	}
	
	/**
	 * @�����������������n��
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��9��6��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public List<HashMap<String,String>> getZzjgList(String xh,int n){
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		List<HashMap<String, String>> list = dao.getZzjgList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	public HashMap<String, String> getZjlyAxjjMap(String xh, String xn) {
		return dao.getZjlyAxjjMap(xh, xn);
	}
	
	public HashMap<String, String> getZjlyGeaxMap(String xh, String xn) {
		return dao.getZjlyGeaxMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyXfjmMap(String xh, String xn) {
		return dao.getZjlyXfjmMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyShfjmMap(String xh, String xn) {
		return dao.getZjlyShfjmMap(xh,xn);
	}
	/** 
	 * �㽭����-��ҵ���ղ���
	 */
	public HashMap<String, String> getZjlySybxbzMap(String xh, String xn) {
		return dao.getZjlySybxbzMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyJtbzMap(String xh, String xn) {
		return dao.getZjlyJtbzMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyJnkzfbzmapMap(String xh, String xn) {
		return dao.getZjlyJnkzfbzmapMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyXfjmmapMap(String xh, String xn) {
		return dao.getZjlyXfjmmapMap(xh,xn);
	}
	public HashMap<String, String> getZjlyZxjmapMap(String xh, String xn) {
		return dao.getZjlyZxjmapMap(xh,xn);
	}
	public HashMap<String, String> getWzdxbxkms(String xh,String xn ) {
		return dao.getWzdxbxkms(xh,xn);
	}
	public List<HashMap<String, String>> getJxtczzxmList(ZzxmjgForm model,
			User user) {
		return dao.getJxtczzxmList(model, user);
	}
	/**
	 * ���칤�̸��Ի����
	 */
	public HashMap<String, String> getQtzzje(String xh,String xn ) {
		return dao.getQtzzje(xh,xn);
	}public HashMap<String, String> getKncsAndJe(String xh,String xn ) {
		return dao.getKncsAndJe(xh,xn);
	}
	public List<HashMap<String, String>> getShyjList(String xh, String xn, String xq, String xmdm) {
		return dao.getShyjList(xh, xn, xq, xmdm);
	}
	//�´�
	public List<HashMap<String, String>> getWzdxzzxmList(ZzxmjgForm model,
			User user) {
		return dao.getWzdxzzxmList(model, user);
	}
	
	/**
	 * @description	�� ���ҽ�ѧ�𵼳�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-19 ����08:36:49
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getGjjxjdc(ZzxmjgForm t, User user) throws Exception {
		return dao.getGjjxjdc(t, user);
	}
	
	/**
	 * @description	�� ������ѧ�𵼳�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-19 ����01:57:55
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjzxjdc(ZzxmjgForm t, User user) throws Exception{
		return dao.getGjzxjdc(t, user);
	}
	
	/**
	 * @description	�� ������־��ѧ�𵼳�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-24 ����04:05:42
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjlzjdc(ZzxmjgForm t, User user) throws Exception{
		return dao.getGjlzjdc(t, user);
	}
	
	
	/**
	 * 
	 * @����:TODO(����guid��xg_xszz_new_zzxmjgbȡҵ������id,��Ӧ�����xg_xszz_new_zzxmsqb���guid)
	 * @���ߣ��´���[���ţ�1620]
	 * @���ڣ�2018-9-5 ����03:48:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getSqbIdByYwid(String ywid) {
		return dao.getSqbIdByYwid(ywid);
	}
}

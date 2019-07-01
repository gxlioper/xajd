package xgxt.qgzx.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.dao.QgzxGwglDAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ���λ����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-05-11</p>
 */
public class QgzxGwglService {
	QgzxGwglDAO dao = new QgzxGwglDAO();
	
	/**
	 * ��ѯѧ����λ������ϸ��Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwsqxx(QgzxForm model){
		String xh = model.getXh();
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.putAll(dao.getGwsqxx(model));//ѧ��������Ϣ
		map.put("sfpk", dao.isKns(xh)==true ? "��" : "��");//��������Ϣ
		map.putAll(dao.getGwxx(model));//ѧ������ĸ�λ��Ϣ
		
		return map;
	}
	
	/**
	 * ���ݱ���ֶλ�ȡ��Ӧ������˵��
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList,String tableName){
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * ��ѯѧ����λ������Ϣ����
	 * @param QgzxForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwtzxxForExport(QgzxForm model,String[] colList){
		return dao.selectXsgwtzxxForExport(model,colList);
	}
	
	/**
	 * �޸��ڸ�ѧ����Ϣ
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean modifyXsgwxxb(QgzxForm model,HttpServletRequest request) throws Exception{
		String[] colList = {"bdsj","lzsj","fjwjmc"};
		String[] colValue= {model.getBdsj(),model.getLzsj(),model.getFjwjmc()};
		if(StringUtils.isNull(model.getFjwjmc())){
			colList = new String[]{"bdsj","lzsj"};
			colValue= new String[]{model.getBdsj(),model.getLzsj()};
		}
		
		return StandardOperation.update("xsgwxxb", colList, colValue, "xh||gwdm||sqsj", model.getPkValue(), request);
	}
	
	/**
	 * ��ӡ������һְҵ����ѧԺ��λ��Ȼ��ܱ�
	 * @param WritableWorkbook wwb
	 * @param String nd	 
	 * */
	public void printNbtyGwhzb(WritableWorkbook wwb,String nd){
		QgzxDao qgzxDao = new QgzxDao();
		nd = StringUtils.isNull(nd) ? qgzxDao.getSqsjInfo().get("nd") : nd;
		List<HashMap<String, String>> list = dao.selectXsgwhzxx(nd);
		try{
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			
			wcfTytle.setVerticalAlignment(vag);
		    wcfTytle.setAlignment(alignMent);
		 
		    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		    wfTytle.setBoldStyle(WritableFont.BOLD);
		    wfTytle.setPointSize(20);
		    wcfTytle.setFont(wfTytle);
		 
		    ws.addCell(new Label(0,1,nd+ "ѧ���ѧ���ڹ���ѧ�ù���λ������˱�",wcfTytle));
		    
		    wcfTytle = new WritableCellFormat();
		    vag = VerticalAlignment.CENTRE;
		    wcfTytle.setVerticalAlignment(vag);
		    wcfTytle.setAlignment(alignMent);
		    wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		    wcfTytle.setWrap(true);
		    wfTytle = new WritableFont(WritableFont.ARIAL);
		    wfTytle.setPointSize(10);
		    wcfTytle.setFont(wfTytle);
		    //д������
		    for(int i=0; i<list.size();i++){
		    	HashMap<String, String> map  = list.get(i);
		    	String gwxzmc = map.get("gwxzmc");
		    	String tempGwxzmc = "";
		    	if("�̶�".equalsIgnoreCase(gwxzmc)){
		    		tempGwxzmc = "A";
		    	}
		    	if("��ʱ".equalsIgnoreCase(gwxzmc)){
		    		tempGwxzmc = "B";
		    	}
		    	ws.addCell(new Label(0,i+3,(i+1)+"",wcfTytle));
		    	ws.addCell(new Label(1,i+3,map.get("yrdwmc"),wcfTytle));
		    	ws.addCell(new Label(2,i+3,map.get("gwdm"),wcfTytle));
		    	ws.addCell(new Label(3,i+3,tempGwxzmc,wcfTytle));
		    	ws.addCell(new Label(4,i+3,map.get("xyrs"),wcfTytle));
		    	ws.addCell(new Label(5,i+3,map.get("gznr"),wcfTytle));
		    	ws.addCell(new Label(6,i+3,(StringUtils.isNull(map.get("gzksrq")) ? "" : map.get("gzksrq"))+"��" + (StringUtils.isNull(map.get("gzjsrq")) ? "" : map.get("gzjsrq")),wcfTytle));
		    	ws.addCell(new Label(7,i+3,map.get("fzr"),wcfTytle));
		    	ws.addCell(new Label(8,i+3,map.get("lxdh"),wcfTytle));
		    }
		    ws.mergeCells(0, list.size()+3, 1, list.size()+3);
		    ws.addCell(new Label(0,list.size()+3,"���´�����",wcfTytle)); 
		    ws.mergeCells(2, list.size()+3, 4, list.size()+3);
		    ws.addCell(new Label(2,list.size()+3,"ǩ��                     ��  ��  ��",wcfTytle));
		    ws.addCell(new Label(5,list.size()+3,Base.YXPZXY_KEY+"���",wcfTytle));
		    ws.mergeCells(6, list.size()+3, 8, list.size()+3);
		    ws.addCell(new Label(6,list.size()+3,"ǩ��                     ��  ��  ��",wcfTytle));
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public ArrayList<String[]> accountGwpksbl(ArrayList<String[]> rs, int blIndex, int pkIndex){
		for(int i=0; i<rs.size(); i++){
//			String[] tmpArr = rs.get(i);
			float gwrs = dao.getGwrsCount(rs.get(i)[pkIndex]);
			float gwknsrs = dao.getGwknsrsCount(rs.get(i)[pkIndex]);
			String bl = "0.00";
			if(gwrs !=0){
				bl = new java.text.DecimalFormat("##0.00").format((gwknsrs/gwrs)*100);
			}
			if(rs.get(i).length>blIndex){
				rs.get(i)[blIndex] = bl+"%";
			}
		}
		return rs;
	}
	
	public void printYrdwyrqkb(User user,OutputStream os){
		WritableWorkbook wwb = null;
		HashMap<String, String> yrdwMap = dao.getYrdwInfo(user.getUserName());
		List<HashMap<String, String>> gwhzList = dao.getYrdwGwhzList(yrdwMap.get("yrdwdm"));
		try{
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("�ù���λ���������", 0);
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// ������
			ExcelMB ex = new ExcelMB();
			ws.mergeCells(0, 0, 7, 1);
			ex.printToOneCell_multy(ws, "�ù���λ���������", 0, 0, 20, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650, Border.NONE);
			
			//д������
			ws.mergeCells(0, 2, 2, 2);							
			ws.addCell(new Label(0, 2, "���˵�λ��" + (StringUtils.isNull(yrdwMap.get("yrdwmc")) ? "" : yrdwMap.get("yrdwmc")), wcf));
			ws.mergeCells(3, 2, 5, 2);	
			ws.addCell(new Label(3, 2, "������ʦ��" + (StringUtils.isNull(yrdwMap.get("lxr")) ? "" : yrdwMap.get("lxr")), wcf));
			ws.mergeCells(6, 2, 7, 2);
			ws.addCell(new Label(6, 2, "����ʱ�䣺", wcf));			
			
			ws.mergeCells(0, 3,1, 3);
			ws.addCell(new Label(0, 3, "���˸�λ", wcf));
			ws.mergeCells(2, 3,3, 3);
			ws.addCell(new Label(2, 3, "��������", wcf));
			ws.mergeCells(4, 3,5, 3);
			ws.addCell(new Label(4, 3, "���˽��", wcf));
			ws.mergeCells(6, 3,7, 3);
			ws.addCell(new Label(6, 3, "��ע", wcf));
			
			int base = 4;
			for(int j=0; j<gwhzList.size();j++){
				//д��ѧ�������Ϣ
				ws.mergeCells(0, base,1, base);
				ws.addCell(new Label(0, base, (StringUtils.isNull(gwhzList.get(j).get("gwdm")) ? "" : gwhzList.get(j).get("gwdm")), wcf));
				ws.mergeCells(2, base,3, base);
				ws.addCell(new Label(2, base, (StringUtils.isNull(gwhzList.get(j).get("rs")) ? "" : gwhzList.get(j).get("rs")), wcf));
				ws.mergeCells(4, base,5, base);
				ws.addCell(new Label(4, base, (StringUtils.isNull(gwhzList.get(j).get("cjje")) ? "" : gwhzList.get(j).get("cjje")), wcf));
				ws.mergeCells(6, base,7, base);
				ws.addCell(new Label(6, base, "", wcf));
				base++;	
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
			
		//д�뵽�ͻ���
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}

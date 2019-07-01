
package xgxt.studentInfo.zzdx;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݴ�ѧ�߻�����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-26</p>
 */
public class XsxxZzdxService {
	
	XsxxZzdxDao zzdxDao = new XsxxZzdxDao();
	
	/**
	 * Method getReportList ��ȡ�����б� 
	 * @return list
	 */
	public List getReportList(){		
		List list= zzdxDao.getReportList();
		return list;
	}
	
	/**
	 *Method printGjbb222 ����Excel����
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb222(WritableWorkbook wwb){
		//�����ܼ����ݼ���
		List levCountList = zzdxDao.getLevCountListOfGjbb222();
		//�����ܼ����ݼ���
		List verCountList = zzdxDao.getVerCountListOfGjbb222("");
		//��ȥ�۰�̨�ĵ������ݼ���
		List sydList = zzdxDao.getSydListOfGjbb222();
		//�۰�̨�������ݼ���
		List gatList = null;
		//�������������������������
		List standardList = null;
        //���������۰�̨����ͨ����ר��������
		List bzkGatList = null;
		
		HashMap<String, String> hmap = new HashMap<String, String>();
		HashMap<String, String> tmap = new HashMap<String, String>();
		String sTemp = "";
		
		//���۰�̨�ܵ���Уѧ����
		String sTotalStu = zzdxDao.getTotalStudentCount();
		//�۰�̨�ܵ���Уѧ��ѧ����
		String sGatTotal = zzdxDao.getGatTotalStuCount();
		//�ܵ���ͨ����ר��������
		String sBzkTotal = zzdxDao.getBzkTotalStuCount();
		//�۰�̨�ܵ���ͨ����ר��������
		String sBzkGatTotal = zzdxDao.getBzkGatTotal();
		
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();
		    
		    //���۰�̨�ܵ���Уѧ����
			ws.addCell(new Label(2,5,sTotalStu,wcfTytle));
			//�۰�̨�ܵ���Уѧ��ѧ����
			ws.addCell(new Label(2,37,sGatTotal,wcfTytle));
            //�ܵ���ͨ����ר��������
			ws.addCell(new Label(9,5,sBzkTotal,wcfTytle));
			//�۰�̨�ܵ���ͨ����ר��������
			ws.addCell(new Label(9,37,sBzkGatTotal,wcfTytle));
			
			//Excel������ܼ�����
			for(int i=0; i<levCountList.size(); i++){
				hmap = (HashMap<String, String>) levCountList.get(i);
				if(i<levCountList.size()-2){
					tmap = (HashMap<String, String>) levCountList.get(i+1);
				}
				sTemp = String.valueOf((Integer.parseInt(hmap.get("count")) + Integer.parseInt(tmap.get("count"))));
				if(i==3){
					ws.addCell(new Label(6,5,sTemp,wcfTytle));
				}else if(i==4){
					
				}else if(i==5){					
					ws.addCell(new Label(7,5,sTemp,wcfTytle));
				}else if(i==6){
					
				}else if(i==7){					
					ws.addCell(new Label(8,5,sTemp,wcfTytle));
				}else if(i==8){
					
				}else{
					ws.addCell(new Label(3+i,5,hmap.get("count"),wcfTytle));
				}
			}
			
			//Excel����۰�̨�ĵ�����
			for(int i=0;i<sydList.size();i++){
				hmap = (HashMap<String, String>) sydList.get(i);				
				sTemp = (i<8) ? "0"+(i+Integer.parseInt("2")) : String.valueOf(i+Integer.parseInt("2"));
				ws.addCell(new Label(0,6+i,hmap.get("sydq"),wcfTytle));
				ws.addCell(new Label(1,6+i,sTemp,wcfTytle));
			}
			
			//Excel�������ܼ�����
			for(int i=0; i<verCountList.size(); i++){
				hmap = (HashMap<String, String>) verCountList.get(i);
				ws.addCell(new Label(2,6+i,hmap.get("count"),wcfTytle));
			}
			
			//Excel����������ѧ��������
			
			String[] condition = new String[]{" where pycc='��ʿ'"," where pycc='˶ʿ'"," where pycc='�о���'", 
				                  " where pycc='��ͨ����' or pycc='��ͨר��'"," where pycc='���˱���' or pycc='����ר��'",
				                  " where pycc='���籾��' or pycc='����ר��'"};			
			for(int j=0; j<condition.length; j++){
				standardList = zzdxDao.getVerCountListOfGjbb222(condition[j]);
				for(int i=0; i<31; i++){					
				hmap = (HashMap<String, String>) standardList.get(i);
				ws.addCell(new Label(3+j,6+i,hmap.get("count"),wcfTytle));
			    }
		    }
			
			//Excel��۰�̨��������
			gatList = zzdxDao.getGatListOfGjbb222();
			for(int i=0; i<gatList.size(); i++){
			    hmap = (HashMap<String, String>) gatList.get(i);
			    if(i<gatList.size()-2){
			    	tmap = (HashMap<String, String>) gatList.get(i+1);
			    }
			    if(i==3){
					ws.addCell(new Label(6,37,hmap.get("count"),wcfTytle));
				}else if(i==4){
					
				}else if(i==5){					
					ws.addCell(new Label(7,37,hmap.get("count"),wcfTytle));
				}else if(i==6){
					
				}else if(i==7){					
					ws.addCell(new Label(8,37,hmap.get("count"),wcfTytle));
				}else if(i==8){
					
				}else{
					ws.addCell(new Label(3+i,37,hmap.get("count"),wcfTytle));
				}			    
			}
			
			//���۰�̨����ͨ����ר��������
			bzkGatList = zzdxDao.getBzkListOfGjbb222();
			for(int i=0;i<bzkGatList.size(); i++){
				hmap = (HashMap<String, String>)bzkGatList.get(i);
				ws.addCell(new Label(9,6+i,hmap.get("count"),wcfTytle));
			}
			
			//��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	/**
	 *Method printGjbb24 ����Excel���� ��Уѧ�����������
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb24(WritableWorkbook wwb){
		List list = null;
		HashMap<String, String> map = new HashMap<String, String>();
		int iCount = 0;
		
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();
		    
		    //������Ա ������Ա  ��������
		    //TODO ���������Ƿ��ǳ��������͹����ŵ�
		    String[] condition = {" and zzmm = '01'"," and zzmm='02'"};
		    for(int j=0 ;j<condition.length; j++){
			    list = zzdxDao.getOtherInfo(condition[j]);
			    for(int i =0; i<list.size(); i++){
			    	map = (HashMap<String, String>)list.get(i);
			    	iCount += Integer.parseInt(map.get("count"));
			    	ws.addCell(new Label(2+j,5+i,map.get("count"),wcfTytle));
			    }
			    ws.addCell(new Label(2+j,4,String.valueOf(iCount),wcfTytle));
			    iCount = 0;
		    }
		   		        
		    
		    //TODO ����
		    
		    
		    //TODO �۰�̨
		    
		    //��������
		    list = zzdxDao.getOtherInfo(" and '01'<mz and mz<'57'");
		    for(int i =0; i<list.size(); i++){
		    	map = (HashMap<String, String>)list.get(i);
		    	iCount += Integer.parseInt(map.get("count"));
		    	ws.addCell(new Label(7,5+i,map.get("count"),wcfTytle));
		    }
		    ws.addCell(new Label(7,4,String.valueOf(iCount),wcfTytle));
		    iCount = 0;
		   
		    
		    //TODO �м���
		    //�ؽ�ѧԺ��ѧ����
		    list = zzdxDao.getCjrInfo();
		    for(int i=0; i<list.size(); i++){
		    	map = (HashMap<String, String>) list.get(i);
		    	iCount += Integer.parseInt(map.get("count"));
		    	ws.addCell(new Label(8,5+i,map.get("count"),wcfTytle));
		    }
		    ws.addCell(new Label(8,4,String.valueOf(iCount),wcfTytle));
		    iCount = 0;
		    //��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		    
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *Method printGjbb221 ����Excel���� ��Уѧ���������
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb221(WritableWorkbook wwb){
		List list = null;
		List othList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> tmap = new HashMap<String, String>();
		String condition = "";
		
		int iCell = 0;
		int iRow = 0;
		int iRowCount = 0;
		int iCellCount = 0;
		
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();		    
		    
		    //������θ���ε�ѧ����
		    for(int i=0; i<16; i++){
		    	iRowCount = 0;
			    iCellCount = 0;
		    	condition = " and nl=" + (Integer.parseInt("17")+i-1);
		    	if(i==0){
		    		condition = " and (nl>0 and nl<200)";
		    	}
		    	if(i==1){
		    		condition = " and (nl<=17 and nl>0)";
		    	}
		    	if(i==15){
		    		condition = " and (nl>30 and nl<200)";
		    	}
		    	list = zzdxDao.getStuAgeInfo(condition);
		    	othList = zzdxDao.getStuAgeInfo(condition + " and (xb='2' or xb='Ů')");
		    	for(int j=0; j<9; j++){
		    		map = (HashMap<String, String>)list.get(j);
		    		tmap = (HashMap<String, String>)othList.get(j);
		    		iRowCount = iRowCount + Integer.parseInt(map.get("count"));
		    		iCellCount = iCellCount + Integer.parseInt(tmap.get("count"));
		    		
		    		if(j==0){
		    			//������
		    			ws.addCell(new Label(2+i,5+j+1,map.get("count"),wcfTytle));
		    			ws.addCell(new Label(2+i,7,tmap.get("count"),wcfTytle));
		    			iRow = 5+j+1;
		    			iCell = 5+j+1;
		    		}else{
		    			//ż����
		    			iRow = iRow+2;
		    			iCell = iRow + 1;
		    			ws.addCell(new Label(2+i,iRow,map.get("count"),wcfTytle));
		    			ws.addCell(new Label(2+i,iCell,tmap.get("count"),wcfTytle));
		    		}
		    	}
		    	ws.addCell(new Label(2+i,4, String.valueOf(iRowCount),wcfTytle));
		    	ws.addCell(new Label(2+i,5,String.valueOf(iCellCount),wcfTytle));
		    }
		    
		    //��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *Method printGjbb212 ����Excel���� ��ͨ���ơ�ר�Ʒ�רҵѧ����
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb212(WritableWorkbook wwb){
		HashMap<String, String> map = new HashMap<String, String>();
		int iCount = 0;
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();
		    //רҵѧ����Ϣ
		    List zymcList = zzdxDao.getZymcList();
		    //��ҵ������
		    List bysList = zzdxDao.getBysCountByZy();// byjr='��ҵ'
		    //����ѧλ����
		    List xwCountList = zzdxDao.getXwCountList();//xwzsh is not null
		    //����ѧλŮ����
		   // int iTotalGirlOfXw = zzdxDao.getTotalGirlOfXw();
		    //��������
		    List zsList = zzdxDao.getZsList(Base.currNd);
		    //Ӧ����
		    List yjsList = zzdxDao.getYjsList(Base.currNd);
		    //��������
		    List cjzsList = zzdxDao.getCjzsList(Base.currNd);
		    //��רҵ��Уѧ����
		    List zxxsList = zzdxDao.getZxListByZy(Base.currNd);
		    //һ�꼶 
		    List firstGrade = zzdxDao.getZxxsByGrade(" and dqnj = 1");
		    //���꼶
		    List secondeGrade = zzdxDao.getZxxsByGrade(" and dqnj = 2");
		    //���꼶 
		    List threeGrade = zzdxDao.getZxxsByGrade(" and dqnj = 3");
		    //���꼶
		    List fourGrade = zzdxDao.getZxxsByGrade(" and dqnj = 4");
		    //���꼶
		    List fiveGrade = zzdxDao.getZxxsByGrade(" and dqnj >= 5");
		    //Ԥ�Ʊ�ҵ������
		    List yjbysList = zzdxDao.getYjbysList();
		    //TODO��ѯ����д�뵽Excel��
		    //дרҵ����,רҵ����,ѧ����
		    for(int i=0; i<zymcList.size(); i++){
		    	map = (HashMap<String, String>)zymcList.get(i);
		    	ws.addCell(new Label(0,8+i, String.valueOf(map.get("zymc")==null ? "" : map.get("zymc")),wcfTytle));
		    	ws.addCell(new Label(1,8+i, String.valueOf(map.get("zydm")==null ? "" : map.get("zydm")),wcfTytle));
		    	ws.addCell(new Label(2,8+i, String.valueOf(map.get("xz")==null ? "" : map.get("xz")),wcfTytle));
		    }
		    
		    //TODO��רҵ��ҵ����
		    for(int i=0; i<bysList.size(); i++){
		    	map = (HashMap<String, String>)bysList.get(i);
		    	ws.addCell(new Label(3,8+i, String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));		    	
		    }		
		    //�ܱ�ҵ����
		    ws.addCell(new Label(3,6, String.valueOf(iCount),wcfTytle));
		    //�ܱ�ҵŮ����
		    ws.addCell(new Label(3,7, String.valueOf(zzdxDao.getByCountOfGirl()),wcfTytle));
		    iCount= 0;
		   
		    //TODO��רҵ����ѧλ��
		    for(int i=0; i<xwCountList.size(); i++){
		    	map = (HashMap<String, String>)xwCountList.get(i);
		    	ws.addCell(new Label(4,8+i, String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));		    	
		    }
		    //������ѧλ��
		    ws.addCell(new Label(4,6, String.valueOf(iCount),wcfTytle));
		    //����Ů��ѧλ��
		    ws.addCell(new Label(4,7, String.valueOf(zzdxDao.getTotalGirlOfXw()),wcfTytle));
		    iCount = 0;
		    
		    //��רҵ������
		    for(int i=0; i<zsList.size(); i++){
		    	map = (HashMap<String, String>)zsList.get(i);
		    	ws.addCell(new Label(5,8+i, String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //��������
		    ws.addCell(new Label(5,6, String.valueOf(iCount),wcfTytle));
		    //��Ů������
		    ws.addCell(new Label(5,7, String.valueOf(zzdxDao.getTotalGirlOfZs(Base.currNd)),wcfTytle));
		    iCount = 0;
		    
		    //Ӧ����������
		    for(int i=0; i<yjsList.size(); i++){
		    	map = (HashMap<String, String>) yjsList.get(i);
		    	ws.addCell(new Label(6,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //������Ӧ������
		    ws.addCell(new Label(6,6, String.valueOf(iCount),wcfTytle));
		    //������Ӧ��Ů����
		    ws.addCell(new Label(6,7, String.valueOf(zzdxDao.getTotalGirlOfZsyjs(Base.currNd)),wcfTytle));
		    iCount = 0;
		    
		    //��������
		    for(int i=0; i<cjzsList.size(); i++){
		    	map = (HashMap<String, String>) cjzsList.get(i);
		    	ws.addCell(new Label(7,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //������������
		    ws.addCell(new Label(7,6, String.valueOf(iCount),wcfTytle));
		    //��������Ů������
		    ws.addCell(new Label(7,7, String.valueOf(zzdxDao.getTotalGirlOfCjzs(Base.currNd)),wcfTytle));
		    iCount = 0;
		    
		    //��Уѧ����
		    for(int i=0; i<zxxsList.size(); i++){
		    	map = (HashMap<String, String>) zxxsList.get(i);
		    	ws.addCell(new Label(8,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //��Уѧ������
		    ws.addCell(new Label(8,6, String.valueOf(iCount),wcfTytle));
		    //��УŮ������
		    ws.addCell(new Label(8,7, String.valueOf(zzdxDao.getTotalGirlOfZx()),wcfTytle));
		    iCount = 0;
		    
		    //���꼶����
		    int iCountFir = 0;
		    int iCountSec = 0;
		    int iCountThr = 0;
		    int iCountFou = 0;
		    int iCountFiv = 0;
		    for(int i=0; i<firstGrade.size(); i++){
		    	//һ�꼶����
		    	map = (HashMap<String, String>)firstGrade.get(i);
		    	ws.addCell(new Label(9,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountFir += Integer.parseInt(map.get("count"));
		    	//���꼶����
		    	map = (HashMap<String, String>)secondeGrade.get(i);
		    	ws.addCell(new Label(10,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountSec += Integer.parseInt(map.get("count"));
		    	//���꼶����
		    	map = (HashMap<String, String>)threeGrade.get(i);
		    	ws.addCell(new Label(11,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountThr += Integer.parseInt(map.get("count"));
		    	//���꼶����
		    	map = (HashMap<String, String>)fourGrade.get(i);
		    	ws.addCell(new Label(12,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountFou += Integer.parseInt(map.get("count"));
		    	//���꼶����������
		    	map = (HashMap<String, String>)fiveGrade.get(i);
		    	ws.addCell(new Label(13,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCountFiv += Integer.parseInt(map.get("count"));
		    }
		    //һ�꼶��У����
		    ws.addCell(new Label(9,6, String.valueOf(iCountFir),wcfTytle));
		    //һ�꼶��УŮ������
		    ws.addCell(new Label(9,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 1")),wcfTytle));
		    //���꼶��У����
		    ws.addCell(new Label(10,6, String.valueOf(iCountSec),wcfTytle));
		    //���꼶��УŮ������
		    ws.addCell(new Label(10,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 2")),wcfTytle));
		    //���꼶��У����
		    ws.addCell(new Label(11,6, String.valueOf(iCountThr),wcfTytle));
		    //���꼶��УŮ������
		    ws.addCell(new Label(11,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 3")),wcfTytle));
		    //���꼶��У����
		    ws.addCell(new Label(12,6, String.valueOf(iCountFou),wcfTytle));
		    //���꼶��УŮ������
		    ws.addCell(new Label(12,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj = 4")),wcfTytle));
		    //���꼶��������У����
		    ws.addCell(new Label(13,6, String.valueOf(iCountFiv),wcfTytle));
		    //���꼶��������УŮ������
		    ws.addCell(new Label(13,7, String.valueOf(zzdxDao.getTotalGrilByGrade(" and dqnj >= 5")),wcfTytle));
		    
		    //Ԥ�Ʊ�ҵ����
		    for(int i=0; i<yjbysList.size();i++){
		    	map = (HashMap<String, String>) yjbysList.get(i);
		    	ws.addCell(new Label(14,8+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	iCount += Integer.parseInt(map.get("count"));
		    }
		    //Ԥ�Ʊ�ҵ������
		    ws.addCell(new Label(14,6, String.valueOf(iCount),wcfTytle));
		    //Ԥ�Ʊ�ҵ��Ů������
		    ws.addCell(new Label(14,7, String.valueOf(zzdxDao.getGirlOfYjby()),wcfTytle));
		    
		    //��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Method printGjbb231 ����Excel���� ѧ���䶯�����
	 * @param WritableWorkbook
	 * **/
	@SuppressWarnings("unchecked")
	public void printGjbb231(WritableWorkbook wwb){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
		    WritableSheet ws = wwb.getSheet(0);
		    WritableCellFormat wcfTytle = new WritableCellFormat();		    
		    //TODO��ѯ����д�뵽Excel��
		    //��ѧ���������Уѧ����
		    List superYear = zzdxDao.getSuperData();
		    //����ѧ�����ܼ�
		    List increaseList = zzdxDao.getIncrease();
		    //�ֲ��������
		    List zsList = zzdxDao.getZszxByCc();
		    //��ѧѧ����
		    List fxList = zzdxDao.getYdxxByCc(" and ydlbmc='��ѧ'");
		    //ת��ѧ����
		    List zrList = zzdxDao.getYdxxByCc(" and ydlbmc='ת��'");
		    //����ѧ�����ܼ�
		    List jsxxList = zzdxDao.getJsxxList();
		    //��ҵѧ����
		    List byList = zzdxDao.getBjyList(" and byjr = '��ҵ'");
		    //��ҵѧ����
		    List jyList = zzdxDao.getBjyList(" and byjr = '��ҵ'");
		    //��ѧѧ����
		    List xxList = zzdxDao.getYdxxByCc(" and ydlbmc = '��ѧ'");
		    //��ѧѧ����
		    List txList = zzdxDao.getYdxxByCc(" and ydlbmc = '��ѧ'");
		    //����ѧ����
		    List kcList = zzdxDao.getYdxxByCc(" and ydlbmc = '����'");
		    //����ѧ����
		    List swList = zzdxDao.getYdxxByCc(" and ydlbmc = '����'");
		    //ת��ѧ����
		    List zcList = zzdxDao.getYdxxByCc(" and ydlbmc = 'ת��'");
		    //��ѧ���������Уѧ����
		    List currList = zzdxDao.getCurrYear();
		    
		    for(int i=0; i<superYear.size(); i++){
		    	//��ѧ���������Уѧ����
		    	map = (HashMap<String, String>) superYear.get(i);
		    	ws.addCell(new Label(2,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//����ѧ�����ܼ�
		    	map = (HashMap<String, String>) increaseList.get(i);
		    	ws.addCell(new Label(3,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//������
		    	map = (HashMap<String, String>) zsList.get(i);
		    	ws.addCell(new Label(4,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//��ѧѧ����
		    	map = (HashMap<String, String>) fxList.get(i);
		    	ws.addCell(new Label(5,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//ת��ѧ����
		    	map = (HashMap<String, String>) zrList.get(i);
		    	ws.addCell(new Label(6,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//����ѧ�����ܼ�
		    	map = (HashMap<String, String>) jsxxList.get(i);
		    	ws.addCell(new Label(8,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//��ҵѧ����
		    	map = (HashMap<String, String>) byList.get(i);
		    	ws.addCell(new Label(9,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//��ҵѧ����
		    	map = (HashMap<String, String>) jyList.get(i);
		    	ws.addCell(new Label(10,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//��ѧѧ����
		    	map = (HashMap<String, String>) xxList.get(i);
		    	ws.addCell(new Label(11,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//��ѧѧ����
		    	map = (HashMap<String, String>) txList.get(i);
		    	ws.addCell(new Label(12,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//����ѧ����
		    	map = (HashMap<String, String>) kcList.get(i);
		    	ws.addCell(new Label(13,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//����ѧ����
		    	map = (HashMap<String, String>) swList.get(i);
		    	ws.addCell(new Label(14,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//ת��ѧ����
		    	map = (HashMap<String, String>) zcList.get(i);
		    	ws.addCell(new Label(15,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    	//��ѧ���������Уѧ����
		    	map = (HashMap<String, String>) currList.get(i);
		    	ws.addCell(new Label(17,5+i,String.valueOf(map.get("count")==null ? "0" : map.get("count")),wcfTytle));
		    }
		    //��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}

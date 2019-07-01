package xgxt.rcgl.nbcs;

import java.io.OutputStream;
import java.text.DecimalFormat;
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
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class XsfwzdzxglService {
	XsfwzdzxglDAO dao = new XsfwzdzxglDAO();
	
	/**
	 * ��ȡ��������
	 * @param String tableName
	 * @param String[] col
	 * @return String[]
	 * */
	public String[] getColCN(String tableName,String[] col){
		return dao.getColumnNameCN(col, tableName);
	}
	
	/**
	 * ��ѯѧ��������������Ϣ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryXslfslbmdmb(){
		return dao.selectXslfslbmdmb();
	}
	
	/**
	 * ��ȡѧ��������������Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXslfslrTopTr(){
		String[] colList = {"����","slbmmc","slryhm","slrxm","slrlxdh"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_xslfbmslrb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ȡѧ��������Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXslfTopTr(){
		String[] colList = {"����","xh","nd","yf","slr","slsj","lfsy","hfr","hfsj","bjsj","fwzl"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_xslfglb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ȡ������Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getCqxxTopTr(){
		String[] colList = {"����","nd","yf","slbmmc","ykfts","jscqts","xscqts","bz"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszdfwzxcqb");
		return dao.arrayToList(colList, colListCN);
	}	
	
	/**
	 * ��ѯѧ�����ò�����������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXslfbmslrb(XsfwzdzxglForm model){
		return dao.selectXslfbmslrb(model);
	}
	
	/**
	 * ��ѯѧ�����ò�����������Ϣ����
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXslfbmslrbForExp(XsfwzdzxglForm model){
		return dao.selectXslfbmslrbForExp(model);
	}
	
	
	/**
	 * ����������ѯѧ�����ò�����������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXslfbmslrbOne(XsfwzdzxglForm model){
		return dao.selectXslfbmslrbOne(model);
	}
	
	/**
	 * ����ѧ�����ò�����������Ϣ
	 * @param XsfwzdzxglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXslfbmslrb(XsfwzdzxglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "xslfbmslrb";
		String primaryKey = "slbmdm||slryhm";
		String pkValue = model.getSlbmdm()+model.getSlryhm();
		String[] columns = {"slbmdm","slryhm","slrxm","slrlxdh"};
		String[] values = {model.getSlbmdm(),model.getSlryhm(),model.getSlrxm(),model.getSlrlxdh()};
		
		if(dao.checkExists(tableName, primaryKey,pkValue )){//��¼�Ѿ�����			
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{
			result = StandardOperation.insert(tableName, columns, values, request);
		}		
		return result;
	}
	
	/**
	 * ɾ��ѧ�����ò�����������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return boolean
	 * */
	public boolean delXslfbmslrb(XsfwzdzxglForm model){
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		
		for(int i=0; i<pkV.length; i++){
			if(StringUtils.isNotNull(pkV[i])){
				sqlArr[i] = "delete from xslfbmslrb where slbmdm||slryhm='"  + pkV[i] + "'";
			}
		}
		
		try{
			dao.runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	 * ����ѧ������������Ϣ
	 * @param XsfwzdzxglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXslfglb(XsfwzdzxglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "xslfglb";
		String primaryKey = "xh||lfrq";
		String pkValue = model.getXh()+model.getLfrq();
		String[] columns = {"nd","yf","xh","lffs","lfrq","lfsy","slbmdm","slr","lxdh"};
		String[] values = {model.getNd(),model.getYf(),model.getXh(),model.getLffs(),model.getLfrq(),model.getLfsy(),model.getSlbmdm(),model.getSlr(),model.getLxdh()};
		
		if(dao.checkExists(tableName, primaryKey,pkValue )){//��¼�Ѿ�����			
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{
			result = StandardOperation.insert(tableName, columns, values, request);
		}		
		return result;
	}
	
	/**
	 * ����ѧ�����ûط���Ϣ
	 * @param XsfwzdzxglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXslfhf(XsfwzdzxglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "xslfglb";
		String primaryKey = "xh||lfrq";
		String pkValue = model.getXh()+model.getLfrq();
		String[] columns = {"hfr","hfsj","bjsj","fwzl","bz"};
		String[] values = {model.getHfr(),model.getHfsj(),model.getBjsj(),model.getFwzl(),model.getBz()};
		
		result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);	
		return result;
	}
	
	
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXslfglb(XsfwzdzxglForm model){
		return dao.selectXslfglb(model);
	}
	
	/**
	 * ����������ѯѧ��������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXslfglbOne(XsfwzdzxglForm model){
		return dao.selectXslfglbOne(model);
	}
	
	/**
	 * ��ѯѧ��������Ϣ����
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXslfglbForExp(XsfwzdzxglForm model){
		return dao.selectXslfglbForExp(model);
	}
	
	/**
	 * ɾ��ѧ��������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return boolean
	 * */
	public boolean delXslfglb(XsfwzdzxglForm model){
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		
		for(int i=0; i<pkV.length; i++){
			if(StringUtils.isNotNull(pkV[i])){
				sqlArr[i] = "delete from xslfglb where xh||lfrq='"  + pkV[i] + "'";
			}
		}
		
		try{
			dao.runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	 * ���������Ϣ
	 * @param XsfwzdzxglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXszdfwzxcqb(XsfwzdzxglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "xszdfwzxcqb";
		String primaryKey = "nd||yf||slbmdm";
		String pkValue = model.getNd()+model.getYf()+model.getSlbmdm();
		String[] columns = {"nd","yf","slbmdm","ykfts","jscqts","xscqts","bz"};
		String[] values = {model.getNd(),model.getYf(),model.getSlbmdm(),model.getYkfts(),model.getJscqts(),model.getXscqts(),model.getBz()};
		
		if(dao.checkExists(tableName, primaryKey,pkValue )){//��¼�Ѿ�����			
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{
			result = StandardOperation.insert(tableName, columns, values, request);
		}		
		return result;
	}
	
	
	/**
	 * ��ѯ������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXszdfwzxcqb(XsfwzdzxglForm model){
		return dao.selectXszdfwzxcqb(model);
	}
	
	/**
	 * ����������ѯ������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXszdfwzxcqbOne(XsfwzdzxglForm model){
		return dao.selectXszdfwzxcqbOne(model);
	}
	
	/**
	 * ��ѯ������Ϣ����
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXszdfwzxcqbForExp(XsfwzdzxglForm model){
		return dao.selectXszdfwzxcqbForExp(model);
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param XsfwzdzxglForm model
	 * @return boolean
	 * */
	public boolean delXszdfwzxcqb(XsfwzdzxglForm model){
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		
		for(int i=0; i<pkV.length; i++){
			if(StringUtils.isNotNull(pkV[i])){
				sqlArr[i] = "delete from xszdfwzxcqb where nd||yf||slbmdm='"  + pkV[i] + "'";
			}
		}
		
		try{
			dao.runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	 * ��ӡ�ط���Ϣͳ�Ʊ�
	 * @param OutputStream os
	 * @param XsfwzdzxglForm model
	 * @throws Exception
	 * */
	public void printHftjb(OutputStream os,XsfwzdzxglForm model) throws Exception{
		List<HashMap<String, String>> list = null;
		HashMap<String, String> cMap = new HashMap<String, String>();
		String nd = model.getNd();
		String yf = model.getYf();
		String currYf = GetTime.getNowMonth().length() == 1 ? 0+GetTime.getNowMonth() : GetTime.getNowMonth();//��ȡ�����ֶγ��ȵ��·�
		
		String bzsj = "";
		if(StringUtils.isNotNull(nd) && StringUtils.isNull(yf)){//��ȱ���
			list = dao.selectNdHfxx(model);
			//�ϼ�����
			cMap = dao.selectNdHfhjxx(model);
			bzsj = model.getNd() + "��";
		}else{//�·ݱ���
			//û��ѡ����Ⱥ��·�����ʱ��Ĭ��ͳ��ʱ��Ϊϵͳ��ǰ��ȵ�ǰ�·�
			model.setNd(StringUtils.isNull(nd) ? Base.currNd : nd) ;
			model.setYf(StringUtils.isNull(yf) ? currYf : yf);
			
			list = dao.selectHfxx(model);
			//�ϼ�����
			cMap = dao.selectHfhjxx(model);		
			bzsj = model.getNd() + "��"+model.getYf() +"�·�";
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("�ط�ͳ�Ʊ�", 0);
		try{
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			
			ws.mergeCells(0, 0, 10, 0);
			ws.addCell(new Label(0,0,"ѧ��ָ����������" +bzsj+"�ط�ͳ�Ʊ�",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//�����ͷ
			ws.mergeCells(0, 1, 0, 2);
			ws.addCell(new Label(0,1,"����������",wcfTytle));
			ws.mergeCells(1, 1, 1, 2);
			ws.addCell(new Label(1,1,"��������",wcfTytle));
			ws.mergeCells(2, 1, 2, 2);
			ws.addCell(new Label(2,1,"�ط�����",wcfTytle));
			ws.mergeCells(3, 1, 5, 1);
			ws.addCell(new Label(3,1,"���ʱ��",wcfTytle));
			ws.addCell(new Label(3,2,"��ʱ",wcfTytle));
			ws.addCell(new Label(4,2,"����ʱ",wcfTytle));
			ws.addCell(new Label(5,2,"δ��",wcfTytle));
			ws.mergeCells(6, 1, 8, 1);
			ws.addCell(new Label(6,1,"��������",wcfTytle));
			ws.addCell(new Label(6,2,"����",wcfTytle));
			ws.addCell(new Label(7,2,"��������",wcfTytle));
			ws.addCell(new Label(8,2,"������",wcfTytle));
			ws.mergeCells(9, 1, 10, 1);
			ws.addCell(new Label(9,1,"���÷�ʽ",wcfTytle));
			ws.addCell(new Label(9,2,"����",wcfTytle));
			ws.addCell(new Label(10,2,"����",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//д����
			DecimalFormat df = new DecimalFormat("0.00");
			int count = 0;
			for(int i=0; i<list.size(); i++){				
				HashMap<String, String> map = list.get(i);
				String slzs = map.get("slzs");
				String hfzs = map.get("hfzs");
				String js = map.get("js");
				String bjs = map.get("bjs");
				String wbl = map.get("wbl");
				String my = map.get("my");
				String jbmy = map.get("jbmy");
				String bmy = map.get("bmy");
				String ld = map.get("ld");
				String lr = map.get("lr");
				
				ws.mergeCells(0, 3+i+count, 0, 3+i+count+1);
				ws.addCell(new Label(0,3+i+count,map.get("slbmmc"),wcfTytle));
				if(StringUtils.isNotNull(slzs) && !"0".equalsIgnoreCase(slzs)){
					ws.addCell(new Label(1,3+i+count,slzs,wcfTytle));		
					ws.addCell(new Label(1,3+i+count+1,"",wcfTytle));
				}else{
					ws.addCell(new Label(1,3+i+count,"",wcfTytle));
					ws.addCell(new Label(1,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(hfzs) && !"0".equalsIgnoreCase(hfzs)){
					ws.addCell(new Label(2,3+i+count,hfzs,wcfTytle));
					ws.addCell(new Label(2,3+i+count+1,df.format((Float.parseFloat(hfzs)/Float.parseFloat(slzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(2,3+i+count,"",wcfTytle));
					ws.addCell(new Label(2,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(js) && !"0".equalsIgnoreCase(js)){
					ws.addCell(new Label(3,3+i+count,js,wcfTytle));
					ws.addCell(new Label(3,3+i+count+1,df.format((Float.parseFloat(js)/Float.parseFloat(hfzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(3,3+i+count,"",wcfTytle));
					ws.addCell(new Label(3,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(bjs) && !"0".equalsIgnoreCase(bjs)){
					ws.addCell(new Label(4,3+i+count,bjs,wcfTytle));
					ws.addCell(new Label(4,3+i+count+1,df.format((Float.parseFloat(bjs)/Float.parseFloat(hfzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(4,3+i+count,"",wcfTytle));
					ws.addCell(new Label(4,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(wbl) && !"0".equalsIgnoreCase(wbl)){
					ws.addCell(new Label(5,3+i+count,wbl,wcfTytle));
					ws.addCell(new Label(5,3+i+count+1,df.format((Float.parseFloat(wbl)/Float.parseFloat(hfzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(5,3+i+count,"",wcfTytle));
					ws.addCell(new Label(5,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(my) && !"0".equalsIgnoreCase(my)){
					ws.addCell(new Label(6,3+i+count,my,wcfTytle));
					ws.addCell(new Label(6,3+i+count+1,df.format((Float.parseFloat(my)/Float.parseFloat(hfzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(6,3+i+count,"",wcfTytle));
					ws.addCell(new Label(6,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(jbmy) && !"0".equalsIgnoreCase(jbmy)){
					ws.addCell(new Label(7,3+i+count,jbmy,wcfTytle));
					ws.addCell(new Label(7,3+i+count+1,df.format((Float.parseFloat(jbmy)/Float.parseFloat(hfzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(7,3+i+count,"",wcfTytle));
					ws.addCell(new Label(7,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(bmy) && !"0".equalsIgnoreCase(bmy)){
					ws.addCell(new Label(8,3+i+count,bmy,wcfTytle));
					ws.addCell(new Label(8,3+i+count+1,df.format((Float.parseFloat(bmy)/Float.parseFloat(hfzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(8,3+i+count,"",wcfTytle));
					ws.addCell(new Label(8,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(ld) && !"0".equalsIgnoreCase(ld)){
					ws.addCell(new Label(9,3+i+count,ld,wcfTytle));
					ws.addCell(new Label(9,3+i+count+1,df.format((Float.parseFloat(ld)/Float.parseFloat(slzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(9,3+i+count,"",wcfTytle));
					ws.addCell(new Label(9,3+i+count+1,"",wcfTytle));
				}
				if(StringUtils.isNotNull(lr) && !"0".equalsIgnoreCase(lr)){
					ws.addCell(new Label(10,3+i+count,lr,wcfTytle));
					ws.addCell(new Label(10,3+i+count+1,df.format((Float.parseFloat(lr)/Float.parseFloat(slzs))*100)+"%",wcfTytle));
				}else{
					ws.addCell(new Label(10,3+i+count,"",wcfTytle));
					ws.addCell(new Label(10,3+i+count+1,"",wcfTytle));
				}
				count++;
			}
			//�ϼ�
			ws.mergeCells(0, 3+list.size()+count, 0, 3+list.size()+count+1);
			ws.addCell(new Label(0,3+list.size()+count,"�ϼ�",wcfTytle));
			String hfl = "0".equalsIgnoreCase(cMap.get("hfzs")) ? "" : df.format(Float.parseFloat(cMap.get("hfzs"))/Float.parseFloat(cMap.get("slzs")) *100)+"%";
			String jsl = "0".equalsIgnoreCase(cMap.get("jf")) ? "" : df.format(Float.parseFloat(cMap.get("js"))/Float.parseFloat(cMap.get("hfzs")) *100)+"%";
			String bjsl = "0".equalsIgnoreCase(cMap.get("bjs")) ? "" : df.format(Float.parseFloat(cMap.get("bjs"))/Float.parseFloat(cMap.get("hfzs")) *100)+"%";
			String wbll = "0".equalsIgnoreCase(cMap.get("wbl")) ? "" : df.format(Float.parseFloat(cMap.get("wbl"))/Float.parseFloat(cMap.get("hfzs")) *100)+"%";
			String myl = "0".equalsIgnoreCase(cMap.get("my")) ? "" : df.format(Float.parseFloat(cMap.get("my"))/Float.parseFloat(cMap.get("hfzs")) *100)+"%";
			String jbmyl = "0".equalsIgnoreCase(cMap.get("jbmy")) ? "" : df.format(Float.parseFloat(cMap.get("jbmy"))/Float.parseFloat(cMap.get("hfzs")) *100)+"%";
			String bmyl = "0".equalsIgnoreCase(cMap.get("bmy")) ? "" : df.format(Float.parseFloat(cMap.get("bmy"))/Float.parseFloat(cMap.get("hfzs")) *100)+"%";
			String ldl = "0".equalsIgnoreCase(cMap.get("ld")) ? "" : df.format(Float.parseFloat(cMap.get("ld"))/Float.parseFloat(cMap.get("slzs")) *100)+"%";
			String lrl = "0".equalsIgnoreCase(cMap.get("lr")) ? "" : df.format(Float.parseFloat(cMap.get("lr"))/Float.parseFloat(cMap.get("slzs")) *100)+"%";
			String slzs = "0".equalsIgnoreCase(cMap.get("slzs")) ? "" : cMap.get("slzs");
			String hfzs = "0".equalsIgnoreCase(cMap.get("hfzs")) ? "" : cMap.get("hfzs");
			String js = "0".equalsIgnoreCase(cMap.get("jf")) ? "" : cMap.get("jf");
			String bjs = "0".equalsIgnoreCase(cMap.get("bjs")) ? "" : cMap.get("bjs");
			String wbl = "0".equalsIgnoreCase(cMap.get("wbl")) ? "" : cMap.get("wbl");
			String my = "0".equalsIgnoreCase(cMap.get("my")) ? "" : cMap.get("my");
			String jbmy = "0".equalsIgnoreCase(cMap.get("jbmy")) ? "" : cMap.get("jbmy");
			String bmy = "0".equalsIgnoreCase(cMap.get("bmy")) ? "" : cMap.get("bmy");
			String ld = "0".equalsIgnoreCase(cMap.get("ld")) ? "" : cMap.get("ld");
			String lr = "0".equalsIgnoreCase(cMap.get("lr")) ? "" : cMap.get("lr");
			
			ws.addCell(new Label(1,3+list.size()+count,slzs,wcfTytle));
			ws.addCell(new Label(1,3+list.size()+count+1,"",wcfTytle));
			ws.addCell(new Label(2,3+list.size()+count,hfzs,wcfTytle));
			ws.addCell(new Label(2,3+list.size()+count+1,hfl,wcfTytle));
			ws.addCell(new Label(3,3+list.size()+count,js,wcfTytle));
			ws.addCell(new Label(3,3+list.size()+count+1,jsl,wcfTytle));
			ws.addCell(new Label(4,3+list.size()+count,bjs,wcfTytle));
			ws.addCell(new Label(4,3+list.size()+count+1,bjsl,wcfTytle));
			ws.addCell(new Label(5,3+list.size()+count,wbl,wcfTytle));
			ws.addCell(new Label(5,3+list.size()+count+1,wbll,wcfTytle));
			ws.addCell(new Label(6,3+list.size()+count,my,wcfTytle));
			ws.addCell(new Label(6,3+list.size()+count+1,myl,wcfTytle));
			ws.addCell(new Label(7,3+list.size()+count,jbmy,wcfTytle));
			ws.addCell(new Label(7,3+list.size()+count+1,jbmyl,wcfTytle));
			ws.addCell(new Label(8,3+list.size()+count,bmy,wcfTytle));
			ws.addCell(new Label(8,3+list.size()+count+1,bmyl,wcfTytle));
			ws.addCell(new Label(9,3+list.size()+count,ld,wcfTytle));
			ws.addCell(new Label(9,3+list.size()+count+1,ldl,wcfTytle));
			ws.addCell(new Label(10,3+list.size()+count,lr,wcfTytle));
			ws.addCell(new Label(10,3+list.size()+count+1,lrl,wcfTytle));			
			//��ע
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			
			String ykfts = (StringUtils.isNull(cMap.get("ykfts")) ? "0" : cMap.get("ykfts"));
			String rjcljs = df.format(Float.parseFloat(slzs)/Float.parseFloat(ykfts))+"";
			ws.mergeCells(0, 3+list.size()+count+2, 10, 3+list.size()+count+2);
			ws.addCell(new Label(0,3+list.size()+count+2,
					"��ע��" + bzsj + "����" + ykfts+"�죬�������¼�" + slzs+ "����" +
					"�վ�����" + rjcljs + "������������" + ld +"����ռ " + ldl+"            ��" +
					"����"+lr+"����ռ" + lrl+"��",wcfTytle));
			ws.mergeCells(0, 3+list.size()+count+3, 10, 3+list.size()+count+3);
			ws.addCell(new Label(0,3+list.size()+count+3,"2���ط�" + hfzs+ "�����ط���Ϊ" + hfl +"��",wcfTytle));
			//��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * ��ӡ�ط�δ�����������Ϣͳ�Ʊ�
	 * @param OutputStream os
	 * @param XsfwzdzxglForm model
	 * @throws Exception
	 * */
	public void printHfwbxxb(OutputStream os,XsfwzdzxglForm model) throws Exception{
		String nd = model.getNd();
		String yf = model.getYf();
		String currYf = GetTime.getNowMonth().length() == 1 ? 0+GetTime.getNowMonth() : GetTime.getNowMonth();
		String bzsj = "";
		
		if(StringUtils.isNotNull(nd) && StringUtils.isNull(yf)){//��ȱ���
			bzsj = model.getNd() + "��";
		}else{//�·ݱ���
			//û��ѡ����Ⱥ��·�����ʱ��Ĭ��ͳ��ʱ��Ϊϵͳ��ǰ��ȵ�ǰ�·�
			model.setNd(StringUtils.isNull(nd) ? Base.currNd : nd) ;
			model.setYf(StringUtils.isNull(yf) ? currYf : yf);
			
			bzsj = model.getNd() + "��"+model.getYf() +"�·�";
		}
		
		List<HashMap<String, String>> list = dao.selectXslfglbForWbl(model);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("δ��������¼�ͳ�Ʊ�", 0);
		try{
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);
			
			ws.mergeCells(1, 0, 8, 0);
			ws.addCell(new Label(1,0,"ѧ��ָ����������" + bzsj + "δ���������¼�ͳ�Ʊ�",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			ws.addCell(new Label(0,1,"���",wcfTytle));
			ws.addCell(new Label(1,1,"������",wcfTytle));
			ws.addCell(new Label(2,1,"����",wcfTytle));
			ws.addCell(new Label(3,1,"�༶",wcfTytle));
			ws.addCell(new Label(4,1,"�绰",wcfTytle));
			ws.addCell(new Label(5,1,"����",wcfTytle));
			ws.addCell(new Label(6,1,"������",wcfTytle));
			ws.addCell(new Label(7,1,"����ʱ��",wcfTytle));
			ws.addCell(new Label(8,1,"���÷�ʽ",wcfTytle));
			ws.addCell(new Label(9,1,"��ע",wcfTytle));
			
			for(int i=0; i<list.size(); i++){		
				HashMap<String, String> map = list.get(i);
				ws.addCell(new Label(0,2+i,(i+1)+"",wcfTytle));
				ws.addCell(new Label(1,2+i,map.get("slbmmc"),wcfTytle));
				ws.addCell(new Label(2,2+i,map.get("xm"),wcfTytle));
				ws.addCell(new Label(3,2+i,map.get("bjmc"),wcfTytle));
				ws.addCell(new Label(4,2+i,map.get("lxdh"),wcfTytle));
				ws.addCell(new Label(5,2+i,map.get("lfsy"),wcfTytle));
				ws.addCell(new Label(6,2+i,map.get("slr"),wcfTytle));
				ws.addCell(new Label(7,2+i,map.get("slsj"),wcfTytle));
				ws.addCell(new Label(8,2+i,map.get("lffs"),wcfTytle));
				ws.addCell(new Label(9,2+i,map.get("bz"),wcfTytle));
			}
			 //��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ��ӡ����ͳ�Ʊ�
	 * @param OutputStream os
	 * @param XsfwzdzxglForm model
	 * @throws Exception
	 * */
	public void printCqtjb(OutputStream os,XsfwzdzxglForm model) throws Exception{
		String nd = model.getNd();
		String yf = model.getYf();
		String currYf = GetTime.getNowMonth().length() == 1 ? 0+GetTime.getNowMonth() : GetTime.getNowMonth();
	
		
		model.setNd(StringUtils.isNull(nd) ? Base.currNd : nd) ;
		model.setYf(StringUtils.isNull(yf) ? currYf : yf);
		
		List<HashMap<String, String>> list = dao.selectXszdfwzxcqbForPrint(model);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("����ͳ�Ʊ�", 0);
		try{
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);
			
			ws.mergeCells(0, 0, 6, 0);
			ws.addCell(new Label(0,0,"ѧ��ָ����������" +model.getNd()+"��"+model.getYf()+"�·ݳ���ͳ�Ʊ�",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			ws.addCell(new Label(0,2,"������",wcfTytle));
			ws.addCell(new Label(1,2,"�¿�������",wcfTytle));
			ws.addCell(new Label(2,2,"��ʦ���ڣ��죩",wcfTytle));
			ws.addCell(new Label(3,2,"ѧ�����ڣ��죩",wcfTytle));
			ws.addCell(new Label(4,2,"ȱ�ڣ��죩",wcfTytle));
			ws.addCell(new Label(5,2,"������",wcfTytle));
			ws.addCell(new Label(6,2,"��ע",wcfTytle));
			
			DecimalFormat df = new DecimalFormat("0.00");
			for(int i=0; i<list.size(); i++){				
				HashMap<String, String> map = list.get(i);
				String ykfts = StringUtils.isNull(map.get("ykfts")) ? "0" : map.get("ykfts");
				String jscqts = StringUtils.isNull(map.get("jscqts")) ? "0" : map.get("jscqts");
				String xscqts = StringUtils.isNull(map.get("xscqts")) ? "0" : map.get("xscqts");
				String qqts = Float.parseFloat(ykfts) -(Float.parseFloat(jscqts)+Float.parseFloat(xscqts)) + ""; 
				float cql = (Float.parseFloat(jscqts)+Float.parseFloat(xscqts))/Float.parseFloat(ykfts);
				String cqlStr = df.format(cql*100)+"%";
				if(Float.parseFloat(ykfts)==0){
					cqlStr="";
					qqts="";
				}
				
				ws.addCell(new Label(0,3+i,map.get("slbmmc"),wcfTytle));
				ws.addCell(new Label(1,3+i,map.get("ykfts"),wcfTytle));
				ws.addCell(new Label(2,3+i,map.get("jscqts"),wcfTytle));
				ws.addCell(new Label(3,3+i,map.get("xscqts"),wcfTytle));
				ws.addCell(new Label(4,3+i,qqts+"",wcfTytle));
				ws.addCell(new Label(5,3+i,cqlStr,wcfTytle));
				ws.addCell(new Label(6,3+i,map.get("bz"),wcfTytle));
			}
			 //��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

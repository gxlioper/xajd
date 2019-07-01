package xsgzgl.dtjs.dtxxgl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

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
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xsgzgl.dtjs.comm.DtjsCommService;

public class DtxxglService extends DtjsCommService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	private DtxxglDAO dao = new DtxxglDAO();
	
	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public List<String[]> dtxxQuery(User user,DtxxglForm form,String type,HttpServletRequest request) throws Exception{
		
		/* 2013-08-24 zh ԭ�����ʱ����ҳ��Ͳ�ѯҳ�湲�ò�ѯ��������֪���˴�Ϊ�η���ɵ���������ѯ����
		 * ���޸Ļ���������ҳ��ֻ��ѯ����ǰ�׶����ݣ���ѯҳ���ѯȫ���׶����� */
		/*if("manage".equalsIgnoreCase(type)){
			return dao.dtxxQuery(user,form,type,request);
		}else if("query".equalsIgnoreCase(type)){
			return dao.dtxxQuery2(user,form,type,request);
		}else{
			return null;
		}*/
		
		return dao.dtxxQuery(user,form,type,request);
	}
	/**
	ѧԺ��ѯͳ��
	 * 
	 */
	public List<String[]> xytjQuery(User user, DtxxglForm myForm, String type,
			HttpServletRequest request) throws Exception{
		// TODO �Զ����ɷ������
		
		return dao.xytjQuery(user,myForm,type,request);
		
	}
	/**
	 * ����ɾ��ѧ��������Ϣ
	 * @param pk
	 * @return
	 */
	public boolean dtxxDel(String[] pk){
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return dao.dtxxDel(params);
	}
	
	/**
	 * ����ɾ��ѧ��������Ϣ
	 * @param pk
	 * @return
	 */
	public boolean delDtxx(String[] pk){
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return dao.delDtxx(params);
	}
	
	/**
	 * ��ȡ���������еĵ��Ž���׶�list
	 * */
	public List<HashMap<String,String>> getJdList() {
		return dao.getJdList();
	}
	
	/**
	 * ��ȡѧ���ĵ��Ž���׶�list
	 * */
	public List<HashMap<String,String>> getXsjdList(String xh){
		return dao.getXsjdList(xh);
	}
	/**
	 * ��ȡѧ����ǰ�׶εĽ׶�������Ϣ
	 * */
	public HashMap<String,String> getXsjdOther(String xh){
		return dao.getXsjdOther(xh);
	}
	
	/**
	 * ��ȡѧ����ǰ�׶εĽ׶���Ϣ
	 * */
	public HashMap<String,String> getDqjd(String xh){
		return dao.getDqjd(xh);
	}
	
	/**
	 * ���浳����Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveDtxx(DtxxglForm model) throws Exception{
		return dao.saveDtxx(model);
	}
	
	/**
	 * �޸ĵ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateDtxx(DtxxglForm model) throws Exception{
		return dao.updateDtxx(model);
	}
	
	/**
	 * ��������
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return dao.importData(request,response);
	}
	
	/**
	 * ģ������
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String mbxz(HttpServletResponse response) throws Exception{
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		List<HashMap<String,String>> list=getJdList();
		//ҳǩ���� start
		List<String> title=new ArrayList<String>();
		title.add("����ģ��");
		title.add("�׶δ����");
		//ҳǩ���� end
		//��������� start
		List<Integer> ColumnNum=new ArrayList<Integer>();
		ColumnNum.add(2+list.size()*2);
		ColumnNum.add(2);
		//��������� end
		//���ݼ���  start
		List<List<String[]>> dateList=new ArrayList<List<String[]>>();
		List<String[]> dateList1=new ArrayList<String[]>();
		List<String[]> dateList2=new ArrayList<String[]>();
		for(int i=0;i<list.size();i++){
			dateList2.add(new String[]{list.get(i).get("jddm"),list.get(i).get("jdmc")});
		}
		dateList.add(dateList1);
		dateList.add(dateList2);
		//���ݼ��� end
		//��������Ƽ��� start
		List<String[]> colListCN=new ArrayList<String[]>();
		List<String> colListCN1=new ArrayList<String>();
		colListCN1.add("ѧ��");
		colListCN1.add("��ǰ�׶δ���");
		
		for(int i=0;i<list.size();i++){
			colListCN1.add(list.get(i).get("jdmc")+"��ʼʱ��");
			colListCN1.add(list.get(i).get("jdmc")+"����ʱ��");
		}
		List<String> colListCN2=new ArrayList<String>();
		colListCN2.add("�׶δ���");
		colListCN2.add("�׶�����");
		colListCN.add(colListCN1.toArray(new String[]{}));
		colListCN.add(colListCN2.toArray(new String[]{}));
		//��������Ƽ��� end
		Excel2Oracle.exportDataMoreSheet(title,dateList,ColumnNum,colListCN, response.getOutputStream());
		return "";
	}
	
	/**
	 * ����������Ϣ
	 * @param form
	 * @return
	 */
	public List<String[]> exportData(DtxxglForm form,String type,HttpServletResponse response,User user) throws Exception{
		if(type.equalsIgnoreCase("manage")){
			return dao.exportData(form,type,response,user);
		}else if(type.equalsIgnoreCase("query")){
			return dao.exportData2(form,type,response);
		}else
			return null;
		
	}
	
	/**
	 * ͬ������������ò
	 * @return
	 * @throws Exception 
	 */
	public boolean tbgxzzmm() throws Exception{
		return dao.tbgxzzmm();
	}
	
	/**
	 * ͳ�ƽ������
	 * @param response
	 * @param tjlx
	 * @param jdArray
	 * @param tjList
	 * @param output
	 */
	public  void exportData(HttpServletResponse response, String tjlx,
			String[] jdArray, List tjList, String[] output,OutputStream os) throws Exception{
		// TODO �Զ����ɷ������
		Date date=new Date();
		SimpleDateFormat formate=new SimpleDateFormat("yyyy.MM.dd");
		String currentDate=formate.format(date);
		WritableWorkbook wwb =ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		
		int cols=output.length;
		try{
			WritableSheet ws=wwb.createSheet("11", 0);
			WritableCellFormat wcFormate=new WritableCellFormat();
			
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			//��ʽ
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			//����
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcFormate.setFont(wfTytle);
			
			//����
			ws.mergeCells(0, 0,cols-1, 3);
			ws.addCell(new Label(0,0,"����ʦ��ѧ���������ͳ�Ʊ�",wcFormate));
			ws.mergeCells(cols-4,4,cols-1,4);
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcFormate.setFont(wfTytle);
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setWrap(true);
//			 ���ñ��߿�
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcFormate.setFont(wfTytle);
			ws.addCell(new Label(cols-4,4,"ͳ��ʱ�䣺"+currentDate,wcFormate));
			ws.mergeCells(0, 5,0, 6);
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			ws.addCell(new Label(0,5,Base.YXPZXY_KEY+"����",wcFormate));
			ws.mergeCells(1, 5,1, 6);
			ws.addCell(new Label(1,5,"ѧ��������",wcFormate));
			//int tmp=0;
			for (int i = 0; i < jdArray.length; i++) {
				ws.mergeCells(2+2*i, 5,3+2*i, 5);
				ws.addCell(new Label(2+2*i,5,jdArray[i],wcFormate));
				ws.addCell(new Label(2+2*i,6,"����",wcFormate));
				ws.addCell(new Label(3+2*i,6,"����",wcFormate));
			}
			for (int i = 0; i < tjList.size(); i++) {
				String[] str=(String[]) tjList.get(i);
				for (int j = 0; j < str.length; j++) {
					ws.addCell(new Label(j,i+7,str[j],wcFormate));
				}
				//tmp=i+5;
			}
//			ws.addCell(new Label(0,tmp+1,"ͳ��ʱ��",wcFormate));
//			ws.mergeCells(1, tmp+1,3, tmp+1);
//			ws.addCell(new Label(1,tmp+1,currentDate,wcFormate));

			
			
			
		}catch(Exception e){
			
		}finally{
			wwb.write();
			wwb.close();
		}
		
	}

	/**
	 * �Զ��嵼��
	 * @param form
	 * @param type
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> dtxxglExportData(DtxxglForm form,String type,User user) throws Exception{
		if(type.equalsIgnoreCase("manage")){
			return dao.xsxxglExportData(form);
		}else if(type.equalsIgnoreCase("query")){
			return dao.xsdtxxcxExportData(form,user);
		}else
			return null;
		
	}
}

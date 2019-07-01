/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-6-1 ����02:19:51</p>
 */
package xgxt.xsgygl.whlghxxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.format.Border;
import jxl.format.BorderLineStyle;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;




import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.CommanForm;
import xgxt.sztz.bjlhdx.QuerryModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.dao.gyglDao;



@SuppressWarnings("unused")
public class GyglWhlghxxyAction extends DispatchAction {
	/**
	 * 
	 *��������ͳ�ƹ�����ҳ 
	 * @throws Exception 
	 */
	public ActionForward nwwsStatIndex(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		GyglWhlghxxyForm bean = (GyglWhlghxxyForm)form;
		String titName = "��Ԣ���� - ͳ�Ʒ��� - ��������ͳ��";
		request.setAttribute("titName", titName);
		return mapping.findForward("nwstatindex");
	}
	/**
	 * 
	 * ���ܡ�ϵ������Աͳ������
	 * @throws Exception
	 */
	public ActionForward showzxfdyTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		gyglDao gydao = new gyglDao();
		GyglWhlghxxyForm bean = (GyglWhlghxxyForm)form;
		String xn = bean.getXn();
		String nj = bean.getNj();
		String xq = bean.getXq();
		String zs = bean.getZs();
		if(Base.isNull(xn)){
			xn=Base.currXn;
			bean.setXn(xn);
		}
		if(Base.isNull(xq)){
			xq = Base.currXq;
			bean.setXq(xq);
		}
		request.setAttribute("njList",Base.getNjList());
		request.setAttribute("zsList",gyglDao.dao_zsList());
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("anzxfdy");
	}
	/**
	 *���ܡ�ϵ������Աͳ��
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxfdyTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ExcelMB excelMb = new ExcelMB();
		GyglWhlghxxyService service = new GyglWhlghxxyService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String nj = request.getParameter("nj");
		String zs = request.getParameter("zs");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("ѧ�����������������", 0);

		WritableCellFormat wcf = new WritableCellFormat();		  //����ָ�������ʽ�ĵ�Ԫ���ʽ
		WritableFont wf= new WritableFont(WritableFont.ARIAL);   //���������ʽ
		wf.setPointSize(12);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		wcf.setFont(wf);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

		boolean run =false;
		List<HashMap<String, String>> nwwsdj = service.serv_Nwwsdj();
		int djrow = Integer.parseInt((nwwsdj==null)?"0":String.valueOf(nwwsdj.size()));//���������ȼ��� 

		//����
		excelMb.printTitle(ws,nj+"��ѧ�����������������",3+djrow*2,600);
		//˵��
		ws.mergeCells(0, 1, 2+djrow*2, 0); // ����ָ����-�кϲ���Ԫ��
		ws.setRowView(1, 500); // ����ָ���и�		
		//ws.addCell(new Label(0, 1,"������=����������/��������",wcf)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		excelMb.printToOneCell(ws, "������=����������/��������", 0, 1, 10, false,Alignment.LEFT, true);
		//��˵��
		ws.mergeCells(0, 2, 2+djrow*2, 0); // ����ָ����-�кϲ���Ԫ��
		excelMb.printToOneCell(ws, "��"+zs+"��", 0, 2, 10, false,Alignment.RIGHT, true);
		//ws.addCell(new Label(0, 2,"��"+zs+"��")); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		//��ͷ
		ws.setRowView(1, 200);
		ws.setRowView(1, 200);
		ws.setColumnView(0, 6);
		ws.setColumnView(1, 18);
		ws.setColumnView(2, 19);
		ws.setColumnView(3, 12);
		ws.setColumnView(4, 12);
		ws.setColumnView(5, 12);
		ws.setColumnView(6, 12);
		ws.addCell(new Label(0, 3, "ϵ��",wcf));
		ws.addCell(new Label(1, 3, "����Ա",wcf));
		ws.addCell(new Label(2, 3, "��������",wcf));
		for(int i=0;i<djrow;i++){
			ws.mergeCells((i+1)*2+1,3,(i+1)*2+2,3);// ����ָ����-�кϲ���Ԫ��
			ws.addCell(new Label((i+1)*2+1, 3, nwwsdj.get(i).get("nwwsdjmc"),wcf));					
		}
		//try{
		run  = service.serv_createZXFdytjb(nj,xn, xq, zs);			
		String[] collist = service.ser_getTjbCol("whlgdxhxxy_azxfdytjb");
		List<HashMap<String, String>> tjValueList = service.ser_getAzxfTjValue();
		List<HashMap<String, String>> mergeCol =  service.ser_getMergeCol("whlgdxhxxy_azxfdytjb");//
		String yxm = "";
		String yxm2 = "";
		int num = 0;
		for(int i=0;i<tjValueList.size();i++){
			for(int j=0;j<collist.length;j++){	
				//���õ�Ԫ��ϲ�����
				yxm = tjValueList.get(i).get("YX");
				if(i>0){
					yxm2 = tjValueList.get(i-1).get("YX");
				}
				for(int m=0;m<mergeCol.size();m++){
					if(yxm.equalsIgnoreCase(mergeCol.get(m).get("yx"))){
						num = Integer.parseInt(mergeCol.get(m).get("cout"));//����Ժϵ��¼��						
						continue;
					}
				}

				String str  = tjValueList.get(i).get(collist[j]); 
				if(collist[j].indexOf("V")!=-1){//С����ǰ��'0"
					str = Base.isNull(tjValueList.get(i).get(collist[j]))?"0.00":tjValueList.get(i).get(collist[j]).trim();
					if(str.indexOf(".")==0){//��strС��1ʱ
						str ="0"+str;
					}
				}
				if(!yxm.equalsIgnoreCase(yxm2)){
					if(num>1){
						ws.mergeCells(0,4+i,0,3+i+num);// ����ָ����-�кϲ���Ԫ��
						if(collist[j].indexOf("V")!=-1){
							for(int k=0;k<djrow;k++){
								ws.mergeCells((k+1)*2+2,4+i,(k+1)*2+2,3+i+num);// ����ָ����-�кϲ���Ԫ��ӵ�5�е�5�п�ʼ�ϲ�
							}
						}
					}
					ws.addCell(new Label(j,4+i,str,wcf));					
				}else{
					ws.addCell(new Label(j,4+i,str,wcf));
				}
			}
		}	
		//}finally{

		//}
		//������һ�кϼ�ֵ
		int colnums = djrow*2+3;//������
		for(int i=2;i<colnums;i++){
			double hz =0;
			int flag= 0;
			String hzV= "";
			for(int j=0;j<tjValueList.size();j++){
				String str = ws.getCell(i,j+4).getContents().toString();
				str = Base.isNull(str)?"0.00":str.trim();
				if(str.indexOf("%")!=-1){
//					str=str.replaceAll("%","");
//					flag = 1;
				}else{//ͳ�ƷǱ�����
					hz += Double.parseDouble(str);
				}
//				if(Base.isNull(str)){
//					str = "0";
//				}
//				if(str.indexOf(".")==0){//��strС��1ʱ
//					str ="0"+str;
//				}
				           		
//			}
//			if(flag==1){
//				hzV = hz+"%";
//			}else{
//				hzV = hz+"";
			}	
			hzV = hz+"";
			if(i%2==0&&i>2){//ͳ�Ʊ�����
				hz = Double.parseDouble(ws.getCell(i-1,tjValueList.size()+4).getContents().toString())/Double.parseDouble(ws.getCell(2,tjValueList.size()+4).getContents().toString());			   
			    hzV = new java.text.DecimalFormat("#,##0.00").format(hz*100)+"%";
			}	
			
			ws.addCell(new Label(i,tjValueList.size()+4,hzV,wcf));
		}	

		ws.mergeCells(0, tjValueList.size()+4, 1, tjValueList.size()+4); // ����ָ����-�кϲ���Ԫ��
		ws.addCell(new Label(0,tjValueList.size()+4,"�������ݻ���",wcf));
		if(run){
			service.ser_delTable("whlgdxhxxy_azxfdytjb");
		}
		wwb.write();
		wwb.close();
		return mapping.findForward("success");
	}
	/**
	 * 
	 * ���ܡ�ϵͳ������
	 * @throws Exception
	 */
	public ActionForward showzxTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		gyglDao gydao = new gyglDao();
		GyglWhlghxxyForm bean = (GyglWhlghxxyForm)form;
		String xn = bean.getXn();
		String xq = bean.getXq();
		String zs = bean.getZs();
		if(Base.isNull(xn)){
			xn=Base.currXn;
			bean.setXn(xn);
		}
		if(Base.isNull(xq)){
			xq = Base.currXq;
			bean.setXq(xq);
		}
		request.setAttribute("zsList",gyglDao.dao_zsList());
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("anzxty");
	}
	/**
	 *���ܡ�ϵͳ��
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ExcelMB excelMb = new ExcelMB();
		GyglWhlghxxyService service = new GyglWhlghxxyService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String zs = DealString.toGBK(request.getParameter("zs"));
		String xn = DealString.toGBK(request.getParameter("xn"));
		String xq = DealString.toGBK(request.getParameter("xq"));

		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("ѧ�����������������", 0);
		List<HashMap<String, String>> nwwsdj =  service.serv_Nwwsdj();
		int djrow = Integer.parseInt((nwwsdj==null)?"0":String.valueOf(nwwsdj.size()));//���������ȼ��� 
		
		WritableCellFormat wcf1 = new WritableCellFormat();		  //����ָ�������ʽ�ĵ�Ԫ���ʽ
		WritableFont wf1 = new WritableFont(WritableFont.ARIAL);   //���������ʽ
		wf1.setPointSize(12);
		wcf1.setAlignment(Alignment.CENTRE);
		//wcf1.setWrap(true);
		wcf1.setFont(wf1);
		wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		//����
		excelMb.printTitle(ws,xn+"ѧ�� "+service.getXqmc(xq)+"ѧ��"+" ��"+zs+"�� ѧ�����������������",2+djrow*2,600);
				
		//��ͷ		
		ws.setRowView(1, 200);
		ws.setRowView(1, 200);
		ws.setColumnView(0, 6);
		ws.setColumnView(1, 18);
		ws.setColumnView(2, 19);
		ws.setColumnView(3, 12);
		ws.setColumnView(4, 12);
		ws.setColumnView(5, 12);
		ws.setColumnView(6, 12);
		ws.addCell(new Label(0, 2, "ϵ��",wcf1));
		ws.addCell(new Label(1, 2, "��������",wcf1));
		boolean run = false;
//		try{
		run  = service.serv_createZXtjb(xn,xq,zs);
		for(int i=0;i<djrow;i++){
			ws.addCell(new Label(2*i+2, 2, nwwsdj.get(i).get("nwwsdjmc"),wcf1));
			ws.addCell(new Label(2*i+3, 2, "����",wcf1));
		}
		String[] collist = service.ser_getTjbCol("whlgdxhxxy_azxtjb");
		List<HashMap<String, String>> tjValueList = service.ser_getAzxTjValue();

		for(int i=0;i<tjValueList.size();i++){
			for(int j=0;j<collist.length;j++){
				String str  = Base.isNull(tjValueList.get(i).get(collist[j]))?"0.00":tjValueList.get(i).get(collist[j]).trim();
				if(str.indexOf(".")==0){//��strС��1ʱ
					str ="0"+str;
				}
				ws.addCell(new Label(j,3+i,str,wcf1));				
			}
		}

//		}finally{

//		������һ�кϼ�ֵ
		int colnums = ws.getColumns();
		for(int i=1;i<colnums;i++){
			double hz =0;
			int flag= 0;
			String hzV= "";
			for(int j=0;j<tjValueList.size();j++){
				String str = ws.getCell(i,j+3).getContents().toString();
				str = Base.isNull(str)?"0.00":str.trim();
				if(str.indexOf("%")!=-1){
					str=str.replaceAll("%","");
					flag = 1;
				}
				
				if(str.indexOf(".")==0){//��strС��1ʱ
					str ="0"+str;
				}
				hz += Double.parseDouble(str);           		
			}
			if(flag==1){
				hzV = hz+"%";
			}else{
				hzV = hz+"";
			}
			ws.addCell(new Label(i,tjValueList.size()+3,hzV,wcf1));
		}			
		ws.addCell(new Label(0,tjValueList.size()+3,"�ϼ�",wcf1));
		if(run){
			service.ser_delTable("whlgdxhxxy_azxtjb");
		}
		wwb.write();
		wwb.close();
//		}
		return mapping.findForward("success");
	}

	public ActionForward nwwsAztj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
		GyglWhlghxxyForm bean = (GyglWhlghxxyForm)form;
		GyglWhlghxxyService service = new GyglWhlghxxyService();
		String xn = bean.getXn();
		String xq = bean.getXq();
		if(Base.isNull(xn)){
			xn=Base.currXn;
			bean.setXn(xn);
		}
		if(Base.isNull(xq)){
			xq = Base.currXq;
			bean.setXq(xq);
		}
		List xqList = service.findXqList();
		List ldList = service.findLdList();
		List xnList = service.findXnList();
		request.setAttribute("xqList", xqList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("xnList", xnList);
		return mapping.findForward("nwwsAztj");
	}
	public ActionForward nwwsAytj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
		GyglWhlghxxyForm bean = (GyglWhlghxxyForm)form;
		GyglWhlghxxyService service = new GyglWhlghxxyService();
		List ldList = service.findLdList();
		List yfList = service.findYfList();
		String dqnd = Base.currNd;
		request.setAttribute("ldList", ldList);
		request.setAttribute("yfList", yfList);
		request.setAttribute("dqnd", dqnd);
		return mapping.findForward("nwwsAytj");
	}
	public ActionForward nwwsAyExpData(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		GyglWhlghxxyForm dcForm = (GyglWhlghxxyForm)form;
		GyglWhlghxxyService service = new GyglWhlghxxyService();
		ExcelMB excelMb = new ExcelMB();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("sheet1", 0);
		String ldmc = service.getLdmc(dcForm.getLddm());
		ExcelMB exl = new ExcelMB();

		WritableCellFormat wcf = new WritableCellFormat();		  //����ָ�������ʽ�ĵ�Ԫ���ʽ
		WritableFont wf = new WritableFont(WritableFont.ARIAL);   //���������ʽ
		wf.setBoldStyle(WritableFont.BOLD);	
		wf.setPointSize(18);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		wcf.setFont(wf);

		WritableCellFormat wcf1 = new WritableCellFormat();		  //����ָ�������ʽ�ĵ�Ԫ���ʽ
		WritableFont wf1 = new WritableFont(WritableFont.ARIAL);   //���������ʽ
		wf1.setPointSize(12);
		wcf1.setAlignment(Alignment.CENTRE);
		//wcf1.setWrap(true);
		wcf1.setFont(wf1);
		wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);		
		int cwnum = Integer.parseInt(service.ser_getCws());	
		boolean run = false;
//		try{
		run  = service.serv_createAytjb(dcForm.getLddm(), dcForm.getDqnd(), dcForm.getYf());
		String[] collist = service.ser_getTjbCol("whlgdxhxxy_wsjc_aytjb");

		//����
		excelMb.printTitle(ws,"ѧ����Ԣ" + ldmc + "ס�����" + "( " + dcForm.getDqnd() + "��" + dcForm.getYf() + "��" + " )",4+collist.length,600);						
		ws.addCell(new Label(1,3,"���Һ�",wcf1));
		ws.addCell(new Label(2,3,"ϵ��",wcf1));
		ws.addCell(new Label(3,3,"רҵ�༶",wcf1));
		ws.addCell(new Label(4,3,"����",wcf1));

		for(int i=1;i<=collist.length-5;i++){
			ws.addCell(new Label(i+4,3,"��λ"+i,wcf1));
		}
		ws.addCell(new Label(collist.length,3,"����Ա",wcf1));
		List<HashMap<String, String>> tjValueList = service.serv_getAYTjValue();
		for(int i=0;i<tjValueList.size();i++){
			for(int j=0;j<collist.length;j++){												
				ws.addCell(new Label(j+1,4+i,tjValueList.get(i).get(collist[j]),wcf1));				
			}
		}
//		}finally{
		if(run){
			service.ser_delTable("whlgdxhxxy_wsjc_aytjb");
		}
		wwb.write();
		wwb.close();
//		}
		return mapping.findForward("success");
	}

	public ActionForward nwwsAzExpData(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		GyglWhlghxxyForm dcForm = (GyglWhlghxxyForm)form;
		GyglWhlghxxyService service = new GyglWhlghxxyService();
		String ldmc = service.getLdmc(dcForm.getLddm());
		String xqmc = service.getXqmc(dcForm.getXq());
		String xn   = request.getParameter("xn");
		String xq   = request.getParameter("xq");
		String lddm = request.getParameter("lddm");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("sheet1", 0);
		String title = dcForm.getXn() + "ѧ��" + xqmc + "ѧ�������������" + "(" + ldmc + " )";
		WritableCellFormat wcf = new WritableCellFormat();		  //����ָ�������ʽ�ĵ�Ԫ���ʽ
		WritableFont wf = new WritableFont(WritableFont.ARIAL);   //���������ʽ
		wf.setBoldStyle(WritableFont.BOLD);	
		wf.setPointSize(15);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		wcf.setFont(wf);
		ws.mergeCells(1, 0, 14, 0); 
		ws.setRowView(0, 500); // ����ָ���иߣ��������߶ȣ�
		ws.addCell(new Label(1,0,title,wcf)); //(���������������⡢λ��)

		WritableCellFormat wcf1 = new WritableCellFormat();		  //����ָ�������ʽ�ĵ�Ԫ���ʽ
		WritableFont wf1 = new WritableFont(WritableFont.ARIAL);   //���������ʽ
		wf1.setPointSize(12);
		wcf1.setAlignment(Alignment.CENTRE);
		wcf1.setWrap(true);
		wcf1.setFont(wf1);
		wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);
		ws.setColumnView(2, 20);
		ws.addCell(new Label(1,1,"���Һ�",wcf1));
		ws.addCell(new Label(2,1,"רҵ�༶",wcf1));
		boolean run = false;
		run  = service.serv_createZtjb(xn,xq,lddm);

		
		//List<HashMap<String,String>> csList = service.findCs();
//		List<HashMap<String,String>> qsbjList = service.findQsbj(dcForm);
//		List<HashMap<String,String>> aztjList = service.azExpDataList(dcForm);
		for(int i = 0;i < 25;i++){//һѧ�����25��
			ws.addCell(new Label(3 + i,1,"��" + (1+i) +"��",wcf1));
		}
//		for(int j = 0;j < qsbjList.size();j++){
//			ws.setRowView(2+j,600);
//			for(int i = 0;i < 25;i++){
//				ws.addCell(new Label(3 + i,2+j,"",wcf1));
//				ws.setColumnView(3 + i, 9);
//			}
//		}		
//		int z = 0;
//		for(int i = 0;i < qsbjList.size();i++){
//			int y = Integer.parseInt(aztjList.get(z).get("cs"));
//			z += y;
//			for(int x = z-y;x < z;x++){
//				ws.addCell(new Label(1,2+i,aztjList.get(x).get("ssbh"),wcf1));
//				ws.addCell(new Label(2,2+i,aztjList.get(x).get("bjmc"),wcf1));
//				ws.addCell(new Label(2+Integer.parseInt(aztjList.get(x).get("zs")),2+i,aztjList.get(x).get("fs")+"��",wcf1));
//			}
//		}
		
		String[] colList = service.ser_getTjbCol("whlgdxhxxy_wsjc_aztjb");
		List<HashMap<String, String>> tjValueList = service.serv_getAZTjValue();
		for(int i=0;i<tjValueList.size();i++){
			for(int j=0;j<colList.length;j++){												
				ws.addCell(new Label(j+1,2+i,tjValueList.get(i).get(colList[j]),wcf1));				
			}
		}		
		
		if(run){
			service.ser_delTable("whlgdxhxxy_wsjc_aztjb");
		}
		wwb.write();
		wwb.close();
		return null;
	}	
	/**
	 *�����������¼�� 
	 */
	public ActionForward wsjcInput(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		GyglWhlghxxyForm dcForm = (GyglWhlghxxyForm)form;
		GyglWhlghxxyService service = new GyglWhlghxxyService();
		String xn = dcForm.getXn();
		String xq = dcForm.getXq();
		if(xn==null){
			xn = Base.currXn;
			dcForm.setXn(xn);
		}
		if(xq==null){
			xq = Base.currXq;
			dcForm.setXq(xq);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			List<HashMap<String, String>> rs = service.serv_listvalue(dcForm);
			request.setAttribute("rsNum", (rs != null && !"".equals(rs))?rs.size():"0");   		 
	        request.setAttribute("rs", rs);
		}
		List<HashMap<String,String>>djList = service.serv_getDjList();
		request.setAttribute("djList", djList);
		request.setAttribute("bmList",service.ser_getJcBmList());
		request.setAttribute("zsList",gyglDao.dao_zsList());
		FormModleCommon.setNdXnXqList(request);
		gyglDao.getLdLcQshList(request);
		
		String[] jcz=dcForm.getJcz();
		String[] jcsj=dcForm.getJcsj();
		String[] dj=dcForm.getDj();
		String[] fs=dcForm.getFs();
		String[] jcbm=dcForm.getJcbm();
		String[] ssbhv=dcForm.getSsbhv();
		String jczstr = "";
		String jcsjstr = "";
		String djstr = "";
		String fsstr = "";
		String jcbmstr = "";
		String ssbhvstr="";
		if(ssbhv!=null){
			for(int i=0;i<ssbhv.length;i++){
				jczstr+=jcz[i]+"!!";
				jcsjstr+=jcsj[i]+"!!";
				djstr+=dj[i]+"!!";
				fsstr+=fs[i]+"!!";
				jcbmstr+=jcbm[i]+"!!";
				ssbhvstr+=ssbhv[i]+"!!";
			}
		}
		request.setAttribute("jczstr",jczstr);
		request.setAttribute("jcsjstr",jcsjstr);
		request.setAttribute("djstr",djstr);
		request.setAttribute("fsstr",fsstr);
		request.setAttribute("jcbmstr",jcbmstr);
		request.setAttribute("ssbhvstr",ssbhvstr);
		return mapping.findForward("wsjcInput");
	}
	public ActionForward inputSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException {
		GyglWhlghxxyForm dcForm = (GyglWhlghxxyForm)form;
		GyglWhlghxxyService service = new GyglWhlghxxyService();		
		service.serv_inputSave(dcForm);
		return wsjcInput(mapping, form, request, response);
	}
}

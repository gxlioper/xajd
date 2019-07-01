package xgxt.pjpy.comm;

import java.io.File;
import java.util.Map;

import jxl.write.Label;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xtwh.zpgl.XtwhZpglService;

public class PjpyPrintService {
	
	/**
	 * У�����ҵ����ӡ
	 * @param wwb
	 * @param map
	 */
	public void printYxbys(WritableWorkbook wwb, Map<String,String> map) {


		try {
			WritableSheet ws = wwb.getSheet(0);
			//����
			ws.addCell(new Label(1,0,"���ҵ��ѧ"+map.get("nd")+"�������ҵ���ǼǱ�",ws.getCell(1, 0).getCellFormat()));
			//����
			ws.addCell(new Label(3,1,map.get("xm"),ws.getCell(3, 1).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(5,1,xb,ws.getCell(5, 1).getCellFormat()));
			//����
			ws.addCell(new Label(8,1,map.get("mzmc"),ws.getCell(8, 1).getCellFormat()));
			//������ò
			ws.addCell(new Label(10,1,map.get("zzmmmc"),ws.getCell(10, 1).getCellFormat()));
			//ѧԺ
			ws.addCell(new Label(3,2,map.get("xymc"),ws.getCell(3, 2).getCellFormat()));
			//ѧ��
			ws.addCell(new Label(8,2,map.get("xlmc"),ws.getCell(8, 2).getCellFormat()));
			//ѧ��
			ws.addCell(new Label(10,2,map.get("xz"),ws.getCell(10, 2).getCellFormat()));
			//רҵ���༶
			ws.addCell(new Label(3,3,map.get("zymc")+"��"+map.get("bjmc"),ws.getCell(3, 3).getCellFormat()));
			//��������
			ws.addCell(new Label(8,3,map.get("csrq"),ws.getCell(8, 3).getCellFormat()));
			//�����س�
			String grtc = "";
			if(map.get("�����س�")==null || map.get("�����س�").equals("null")){
				grtc = "";
			}else{
				grtc = map.get("�����س�");
			}
			ws.addCell(new Label(10,3,grtc,ws.getCell(10, 3).getCellFormat()));
			//��ͥסַ
			ws.addCell(new Label(3,4,map.get("jtdz"),ws.getCell(3, 4).getCellFormat()));
			//�����
			String hjqk = "";
			if(map.get("�����")==null || map.get("�����").equals("null")){
				hjqk = "";
			}else{
				hjqk = map.get("�����");
			}
			ws.addCell(new Label(2,5,hjqk,ws.getCell(2, 5).getCellFormat()));
			//���ķ���
			String shgz = "";
			if(map.get("��Ṥ�������ķ������")==null || map.get("��Ṥ�������ķ������").equals("null")){
				shgz = "";
			}else{
				shgz = map.get("��Ṥ�������ķ������");
			}
			ws.addCell(new Label(2,6,shgz,ws.getCell(2, 6).getCellFormat()));
			//�ۺ�����
			String zhpj = "";
			if(map.get("�ۺ�����")==null || map.get("�ۺ�����").equals("null")){
				zhpj = "";
			}else{
				zhpj = map.get("�ۺ�����");
			}
			ws.addCell(new Label(2,7,zhpj,ws.getCell(2, 7).getCellFormat()));
			//ѧԺ���
			ws.addCell(new Label(2,9,map.get("xyyj"),ws.getCell(2, 9).getCellFormat()));
			//ѧУ���
			ws.addCell(new Label(7,9,map.get("xxyj"),ws.getCell(7, 9).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
		
	
	
	
	/**
	 * ������ѧ����ӡ
	 * @param wwb
	 * @param map
	 */
	public void printAljxj(WritableWorkbook wwb, Map<String,String> map){
		
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧУ����
			ws.addCell(new Label(2,4,Base.xxmc,ws.getCell(2, 4).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(2,5,map.get("zymc"),ws.getCell(2, 5).getCellFormat()));
			//�༶����
			ws.addCell(new Label(2,6,map.get("bjmc"),ws.getCell(2, 6).getCellFormat()));
			//�����ϵ�����
			ws.addCell(new Label(2,7,map.get("xm"),ws.getCell(2, 7).getCellFormat()));
			//����ϵ�����
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){}else{
			WritableImage wi=new WritableImage(8,12,1,3,file);   
			ws.addImage(wi);
			}
			//������ò
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//��Դʡ��
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//�ۺϲ�������
			String zhcpmc = "";
			if(map.get("�ۺϲ�������")==null || map.get("�ۺϲ�������").equals("null")){
				zhcpmc = "";
			}else{
				zhcpmc = map.get("�ۺϲ�������");
			}
			ws.addCell(new Label(2,15,zhcpmc,ws.getCell(2, 15).getCellFormat()));
			//רҵ����
			String zymc = "";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zymc = "";
			}else{
				zymc = map.get("רҵ����");
			}
			ws.addCell(new Label(5,15,zymc,ws.getCell(5, 15).getCellFormat()));
			//�س�
			String tc = "";
			if(map.get("�س�")==null || map.get("�س�").equals("null")){
				tc = "";
			}else{
				tc = map.get("�س�");
			}
			ws.addCell(new Label(8,15,tc,ws.getCell(8, 15).getCellFormat()));
			//��ѧ�ڼ�����
			String hjqk = "";
			if(map.get("�����")==null || map.get("�����").equals("null")){
				hjqk = "";
			}else{
				hjqk = map.get("�����");
			}
			ws.addCell(new Label(1,16,hjqk,ws.getCell(1, 16).getCellFormat()));
			//��������
			ws.addCell(new Label(0,23,map.get("sqly"),ws.getCell(0, 23).getCellFormat()));
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,27,bjfdyyj,ws.getCell(1, 27).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ɣ�齱ѧ���ӡ
	 * @param wwb
	 * @param map
	 */
	public void printSmjxj(WritableWorkbook wwb, Map<String,String> map){
		
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧУ����
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//�༶����
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//�����ϵ�����
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//����ϵ�����
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){}else{
			WritableImage wi=new WritableImage(8,12,1,3,file);   
			ws.addImage(wi);
			}
			//������ò
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//��Դʡ��
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//�ۺϲ�������
			String zhcpmc = "";
			if(map.get("�ۺϲ�������")==null || map.get("�ۺϲ�������").equals("null")){
				zhcpmc = "";
			}else{
				zhcpmc = map.get("�ۺϲ�������");
			}
			ws.addCell(new Label(2,15,zhcpmc,ws.getCell(2, 15).getCellFormat()));
			//רҵ����
			String zymc = "";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zymc = "";
			}else{
				zymc = map.get("רҵ����");
			}
			ws.addCell(new Label(5,15,zymc,ws.getCell(5, 15).getCellFormat()));
			//�س�
			String tc = "";
			if(map.get("�س�")==null || map.get("�س�").equals("null")){
				tc = "";
			}else{
				tc = map.get("�س�");
			}
			ws.addCell(new Label(8,15,tc,ws.getCell(8, 15).getCellFormat()));
			//�걨�ȼ�
			String sbdj = "";
			if(map.get("�걨�ȼ�")==null || map.get("�걨�ȼ�").equals("null")){
				sbdj = "";
			}else{
				sbdj = map.get("�걨�ȼ�");
			}
			ws.addCell(new Label(2,16,sbdj,ws.getCell(2, 16).getCellFormat()));
			//�ڼ��λ��ɣ�齱ѧ��
			String smjxj = "";
			if(map.get("�ڼ��λ��ɣ�齱ѧ��")==null || map.get("�ڼ��λ��ɣ�齱ѧ��").equals("null")){
				smjxj = "";
			}else{
				smjxj = map.get("�ڼ��λ��ɣ�齱ѧ��");
			}
			ws.addCell(new Label(7,16,smjxj,ws.getCell(7, 16).getCellFormat()));
			//��ѧ�ڼ�����
			String hjqk = "";
			if(map.get("�����")==null || map.get("�����").equals("null")){
				hjqk = "";
			}else{
				hjqk = map.get("�����");
			}
			ws.addCell(new Label(1,17,hjqk,ws.getCell(1, 17).getCellFormat()));
			//��������
			ws.addCell(new Label(0,21,map.get("sqly"),ws.getCell(0, 21).getCellFormat()));
			//������
			ws.addCell(new Label(1,27,map.get("shyj1"),ws.getCell(1, 27).getCellFormat()));
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,28,bjfdyyj,ws.getCell(1, 28).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���˲���ѧ�𱨱��ӡ
	 * @param wwb
	 * @param map
	 */
	public void printWkqjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			//ѧУ����
			ws.addCell(new Label(1,1,Base.xxmc,ws.getCell(1, 1).getCellFormat()));
			//��ѧ������
			ws.addCell(new Label(6,1,map.get("xmmc"),ws.getCell(1, 1).getCellFormat()));
			//����
			ws.addCell(new Label(1,2,map.get("xm"),ws.getCell(1, 2).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,2,xb,ws.getCell(3, 2).getCellFormat()));
			//��������
			ws.addCell(new Label(5,2,map.get("csrq"),ws.getCell(5, 2).getCellFormat()));
			//�꼶
			ws.addCell(new Label(1,3,map.get("nj"),ws.getCell(1, 3).getCellFormat()));
			//ѧԺ����
			ws.addCell(new Label(3,3,map.get("xymc"),ws.getCell(3, 3).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(5,3,map.get("zymc"),ws.getCell(5, 3).getCellFormat()));
			//������Ƭ
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){}else{
			WritableImage wi = new WritableImage(6, 2, 1, 4, file);//��x,y,x�ϲ�������y�ϲ���������ȡ����Ƭ�Ǹ�File���͵��ļ���
			ws.addImage(wi);
			}
			String ct="";
			if( map.get("�س�")==null || map.get("�س�").equals("null")){
				ct="";
			}else{
				ct=map.get("�س�");
			}
			String zysj="";
			if(map.get("��Ҫ�¼�")==null || map.get("��Ҫ�¼�").equals("null")){
				zysj="";
			}else{
				zysj=map.get("��Ҫ�¼�");
			}
			String xskjcg="";
			if(map.get("ѧ���Ƽ��ɹ�")==null || map.get("ѧ���Ƽ��ɹ�").equals("null")){
				xskjcg="";
			}else{
				xskjcg=map.get("ѧ���Ƽ��ɹ�");
			}
			String xnzcjpm="";
			if(map.get("ѧ���ܳɼ����꼶��רҵ����λ��")==null || map.get("ѧ���ܳɼ����꼶��רҵ����λ��").equalsIgnoreCase("null")){
				xnzcjpm="";
			}else{
				xnzcjpm=map.get("ѧ���ܳɼ����꼶��רҵ����λ��");
			}
			String xnzhcppm="";
			if(map.get("ѧ���ۺϲ������꼶��רҵ����λ��")==null || map.get("ѧ���ۺϲ������꼶��רҵ����λ��").equalsIgnoreCase("null")){
				xnzhcppm="";
			}else{
				xnzhcppm=map.get("ѧ���ۺϲ������꼶��רҵ����λ��");
			}
			//���ְ��
			ws.addCell(new Label(1,4,map.get("xszw"),ws.getCell(1, 4).getCellFormat()));
			//�س�
			ws.addCell(new Label(3,4,ct,ws.getCell(3, 4).getCellFormat()));
			//������ò
			ws.addCell(new Label(5,4,map.get("zzmmmc"),ws.getCell(5, 4).getCellFormat()));
			//��ͥסַ
			ws.addCell(new Label(1,5,map.get("jtdz"),ws.getCell(1, 5).getCellFormat()));
			//��ͥ�ʱ�
			ws.addCell(new Label(5,5,map.get("jtyb"),ws.getCell(5, 5).getCellFormat()));
			//��Ҫ�¼�
			ws.addCell(new Label(1,6,zysj,ws.getCell(1, 6).getCellFormat()));
			//ѧ���Ƽ��ɹ�
			ws.addCell(new Label(1,9,xskjcg,ws.getCell(1, 9).getCellFormat()));
			
			//ѧ���ܳɼ����꼶��רҵ����λ��
			ws.addCell(new Label(4,7,xnzcjpm,ws.getCell(4, 7).getCellFormat()));
			//ѧ���ۺϲ������꼶��רҵ����λ��
			ws.addCell(new Label(4,8,xnzhcppm,ws.getCell(4, 8).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��֮֯�⽱ѧ��
	 * @param wwb
	 * @param map
	 */
	public void printFzzgjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			
			WritableSheet ws = wwb.getSheet(0);
			
			
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			//������Ƭ
			if(file==null){}else{
			WritableImage wi = new WritableImage(7, 15, 1, 3, file);
			ws.addImage(wi);
		}
			//ѧУ����
			ws.addCell(new Label(3,6,Base.xxmc,ws.getCell(3, 6).getCellFormat()));
			//����
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3,6).getCellFormat()));
			
			//����
			ws.addCell(new Label(2,15,map.get("xm"),ws.getCell(2, 15).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(4,15,xb,ws.getCell(4, 15).getCellFormat()));
			//��������
			ws.addCell(new Label(6,15,map.get("csrq"),ws.getCell(6, 15).getCellFormat()));
			//��ѧ����
			ws.addCell(new Label(6,16,map.get("rxrq"),ws.getCell(4, 15).getCellFormat()));
			//����
			ws.addCell(new Label(2,16,map.get("mzmc"),ws.getCell(2, 15).getCellFormat()));
			//������ò
			ws.addCell(new Label(4,16,map.get("zzmmmc"),ws.getCell(4, 16).getCellFormat()));
			//ѧԺ
			ws.addCell(new Label(2,17,map.get("xymc"),ws.getCell(2, 17).getCellFormat()));
			//רҵ
			ws.addCell(new Label(5,17,map.get("zymc"),ws.getCell(5, 17).getCellFormat()));
			
			if(!Base.isNull(map.get("������Ṥ��"))){
				ws.addCell(new Label(2,18,map.get("������Ṥ��"),ws.getCell(2, 18).getCellFormat()));
			}
			
			if(!Base.isNull(map.get("��ʱ�ε��ܺα���"))){
				ws.addCell(new Label(2,21,map.get("��ʱ�ε��ܺα���"),ws.getCell(2, 21).getCellFormat()));
			}
			
			if(!Base.isNull(map.get("�����¼�"))){
				ws.addCell(new Label(0,27,map.get("�����¼�"),ws.getCell(0, 27).getCellFormat()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������֯��ѧ��������ӡ
	 * @param wwb
	 * @param map
	 */
	public void printDmzzjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			String zypm="";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zypm="";
			}else{
				zypm=map.get("רҵ����");
			}
			String sbdj="";
			if(map.get("�걨�ȼ�")==null || map.get("�걨�ȼ�").equals("null")){
				sbdj="";
			}else{
				sbdj=map.get("�걨�ȼ�");
			}
			String dxhjqk="";
			if(map.get("�ڴ�ѧ�ڼ�����")==null || map.get("�ڴ�ѧ�ڼ�����").equals("null")){
				dxhjqk="";
			}else{
				dxhjqk=map.get("�ڴ�ѧ�ڼ�����");
			}
			String shhdjl="";
			if(map.get("��Ҫ�������")==null || map.get("��Ҫ�������").equals("null")){
				shhdjl="";
			}else{
				shhdjl=map.get("��Ҫ�������");
			}
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//����
			ws.addCell(new Label(1,3,map.get("xm"),ws.getCell(1, 3).getCellFormat()));
			//�Ա�
			ws.addCell(new Label(3,3,xb,ws.getCell(3, 3).getCellFormat()));
			//������ò
			ws.addCell(new Label(5,3,map.get("zzmmmc"),ws.getCell(5, 3).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(7,3,zypm,ws.getCell(7, 3).getCellFormat()));
			//ѧ��
			ws.addCell(new Label(1,5,map.get("xh"),ws.getCell(1, 5).getCellFormat()));
			//����
			ws.addCell(new Label(3,5,map.get("mzmc"),ws.getCell(3, 5).getCellFormat()));
			//��������
			ws.addCell(new Label(5,5,map.get("csrq"),ws.getCell(5, 5).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(7,5,map.get("xszw"),ws.getCell(7, 5).getCellFormat()));
			//רҵ
			ws.addCell(new Label(1,7,map.get("zymc")+map.get("bjmc"),ws.getCell(1, 7).getCellFormat()));
			//��ϵ�绰
			ws.addCell(new Label(5,7,map.get("lxdh"),ws.getCell(5, 7).getCellFormat()));
			//��ͥ��ַ
			ws.addCell(new Label(1,9,map.get("jtdz"),ws.getCell(1, 9).getCellFormat()));
			//�ʱ�
			ws.addCell(new Label(7,9,map.get("jtyb"),ws.getCell(7, 9).getCellFormat()));
			//�걨�ȼ�
			ws.addCell(new Label(9,9,sbdj,ws.getCell(9, 9).getCellFormat()));
			//�ڴ�ѧ�ڼ�����
			ws.addCell(new Label(1,14,dxhjqk,ws.getCell(1, 14).getCellFormat()));
			//��Ҫ�������
			ws.addCell(new Label(1,16,shhdjl,ws.getCell(1, 16).getCellFormat()));
			
			//������Ƭ
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){
			}else{
				WritableImage wi = new WritableImage(8, 3, 2, 6, file);//��x,y,x�ϲ�������y�ϲ���������ȡ����Ƭ�Ǹ�File���͵��ļ���
				ws.addImage(wi);
			}
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,18,bjfdyyj,ws.getCell(1, 18).getCellFormat()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ��ٺϽ�ѧ��������ӡ
	 * @param wwb
	 * @param map
	 */
	public void printJbhjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			String zypm="";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zypm="";
			}else{
				zypm=map.get("רҵ����");
			}
			String sbdj="";
			if(map.get("�걨�ȼ�")==null || map.get("�걨�ȼ�").equals("null")){
				sbdj="";
			}else{
				sbdj=map.get("�걨�ȼ�");
			}
			String dxhjqk="";
			if(map.get("�ڴ�ѧ�ڼ�����")==null || map.get("�ڴ�ѧ�ڼ�����").equals("null")){
				dxhjqk="";
			}else{
				dxhjqk=map.get("�ڴ�ѧ�ڼ�����");
			}
			String shhdjl="";
			if(map.get("��Ҫ�������")==null || map.get("��Ҫ�������").equals("null")){
				shhdjl="";
			}else{
				shhdjl=map.get("��Ҫ�������");
			}
			//����
			ws.addCell(new Label(1,3,map.get("xm"),ws.getCell(1, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,3,xb,ws.getCell(3, 3).getCellFormat()));
			//������ò
			ws.addCell(new Label(5,3,map.get("zzmmmc"),ws.getCell(5, 3).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(7,3,zypm,ws.getCell(7, 3).getCellFormat()));
			//ѧ��
			ws.addCell(new Label(1,5,map.get("xh"),ws.getCell(1, 5).getCellFormat()));
			//����
			ws.addCell(new Label(3,5,map.get("mzmc"),ws.getCell(3, 5).getCellFormat()));
			//��������
			ws.addCell(new Label(5,5,map.get("csrq"),ws.getCell(5, 5).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(7,5,map.get("xszw"),ws.getCell(7, 5).getCellFormat()));
			//רҵ
			ws.addCell(new Label(1,7,map.get("zymc")+map.get("bjmc"),ws.getCell(1, 7).getCellFormat()));
			//��ϵ�绰
			ws.addCell(new Label(5,7,map.get("lxdh"),ws.getCell(5, 7).getCellFormat()));
			//��ͥ��ַ
			ws.addCell(new Label(1,9,map.get("jtdz"),ws.getCell(1, 9).getCellFormat()));
			//�ʱ�
			ws.addCell(new Label(7,9,map.get("jtyb"),ws.getCell(7, 9).getCellFormat()));
			//�걨�ȼ�
			ws.addCell(new Label(9,9,sbdj,ws.getCell(9, 9).getCellFormat()));
			//�ڴ�ѧ�ڼ�����
			ws.addCell(new Label(1,14,dxhjqk,ws.getCell(1, 14).getCellFormat()));
			//��Ҫ�������
			ws.addCell(new Label(1,16,shhdjl,ws.getCell(1, 16).getCellFormat()));
			//������
			ws.addCell(new Label(1,18,map.get("shyj1"),ws.getCell(1, 18).getCellFormat()));
			//������Ƭ
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){
			}else{
				WritableImage wi = new WritableImage(8, 3, 2, 6, file);//��x,y,x�ϲ�������y�ϲ���������ȡ����Ƭ�Ǹ�File���͵��ļ���
				ws.addImage(wi);
			}
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,18,bjfdyyj,ws.getCell(1, 18).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ��γ��ѧ��������ӡ
	 * @param wwb
	 * @param map
	 */
	public void printSwjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			String zypm="";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zypm="";
			}else{
				zypm=map.get("רҵ����");
			}
			String sbdj="";
			if(map.get("�걨�ȼ�")==null || map.get("�걨�ȼ�").equals("null")){
				sbdj="";
			}else{
				sbdj=map.get("�걨�ȼ�");
			}
			String dxhjqk="";
			if(map.get("�ڴ�ѧ�ڼ�����")==null || map.get("�ڴ�ѧ�ڼ�����").equals("null")){
				dxhjqk="";
			}else{
				dxhjqk=map.get("�ڴ�ѧ�ڼ�����");
			}
			String shhdjl="";
			if(map.get("��Ҫ�������")==null || map.get("��Ҫ�������").equals("null")){
				shhdjl="";
			}else{
				shhdjl=map.get("��Ҫ�������");
			}
			//����
			ws.addCell(new Label(1,3,map.get("xm"),ws.getCell(1, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,3,xb,ws.getCell(3, 3).getCellFormat()));
			//������ò
			ws.addCell(new Label(5,3,map.get("zzmmmc"),ws.getCell(5, 3).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(7,3,zypm,ws.getCell(7, 3).getCellFormat()));
			//ѧ��
			ws.addCell(new Label(1,5,map.get("xh"),ws.getCell(1, 5).getCellFormat()));
			//����
			ws.addCell(new Label(3,5,map.get("mzmc"),ws.getCell(3, 5).getCellFormat()));
			//��������
			ws.addCell(new Label(5,5,map.get("csrq"),ws.getCell(5, 5).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(7,5,map.get("xszw"),ws.getCell(7, 5).getCellFormat()));
			//רҵ
			ws.addCell(new Label(1,7,map.get("zymc")+map.get("bjmc"),ws.getCell(1, 7).getCellFormat()));
			//��ϵ�绰
			ws.addCell(new Label(5,7,map.get("lxdh"),ws.getCell(5, 7).getCellFormat()));
			//��ͥ��ַ
			ws.addCell(new Label(1,9,map.get("jtdz"),ws.getCell(1, 9).getCellFormat()));
			//�ʱ�
			ws.addCell(new Label(7,9,map.get("jtyb"),ws.getCell(7, 9).getCellFormat()));
			//�걨�ȼ�
			ws.addCell(new Label(9,9,sbdj,ws.getCell(9, 9).getCellFormat()));
			//�ڴ�ѧ�ڼ�����
			ws.addCell(new Label(1,14,dxhjqk,ws.getCell(1, 14).getCellFormat()));
			//��Ҫ�������
			ws.addCell(new Label(1,16,shhdjl,ws.getCell(1, 16).getCellFormat()));
			//������
			ws.addCell(new Label(1,18,map.get("shyj1"),ws.getCell(1, 18).getCellFormat()));
			//������Ƭ
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){
			}else{
				WritableImage wi = new WritableImage(8, 3, 2, 6, file);//��x,y,x�ϲ�������y�ϲ���������ȡ����Ƭ�Ǹ�File���͵��ļ���
				ws.addImage(wi);
			}
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,18,bjfdyyj,ws.getCell(1, 18).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ��Ȼ��ѧ��������ӡ
	 * @param wwb
	 * @param map
	 */
	public void printTrjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧУ����
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//�༶����
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//�����ϵ�����
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//����ϵ�����
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){
			}else{
				WritableImage wi=new WritableImage(8,12,1,7,file);   
				ws.addImage(wi);
			}
			//������ò
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//ѧ��
			ws.addCell(new Label(3,13,map.get("xlmc"),ws.getCell(3, 13).getCellFormat()));
			//רҵ
			ws.addCell(new Label(6,13,map.get("zymc"),ws.getCell(6, 13).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(1,15,map.get("xszw"),ws.getCell(1, 15).getCellFormat()));
			//רҵ����
			String zypm="";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zypm="";
			}else{
				zypm=map.get("רҵ����");
			}
			ws.addCell(new Label(3,15,zypm,ws.getCell(3, 15).getCellFormat()));
			//����ȼ�
			String sqdj="";
			if(map.get("����ȼ�")==null || map.get("����ȼ�").equals("null")){
				sqdj="";
			}else{
				sqdj=map.get("����ȼ�");
			}
			ws.addCell(new Label(6,15,sqdj,ws.getCell(6, 15).getCellFormat()));
			//��ͥ��ַ
			ws.addCell(new Label(1,17,map.get("jtdz"),ws.getCell(1, 17).getCellFormat()));
			//��ѧ�ڼ�����
			String hjqk="";
			if(map.get("�����")==null || map.get("�����").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("�����");
			}
			ws.addCell(new Label(1,19,hjqk,ws.getCell(1, 19).getCellFormat()));
			//������Ҫ�¼�
			String grzysj="";
			if(map.get("������Ҫ�¼�")==null || map.get("������Ҫ�¼�").equals("null")){
				grzysj="";
			}else{
				grzysj=map.get("������Ҫ�¼�");
			}
			ws.addCell(new Label(0,21,grzysj,ws.getCell(0, 21).getCellFormat()));
			//������
			ws.addCell(new Label(1,28,map.get("shyj1"),ws.getCell(1, 28).getCellFormat()));
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,28,bjfdyyj,ws.getCell(1, 28).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ����ʵҵ��ѧ��������ӡ
	 * @param wwb
	 * @param map
	 */
	public void printFtsyjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧУ����
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//�༶����
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//�����ϵ�����
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//����ϵ�����
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(8,12,1,4,file);   
			ws.addImage(wi);
			}
			//������ò
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//��Դʡ��
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//������̲��������ȼ�
			String dygccxpddj="";
			if(map.get("������̲��������ȼ�")==null || map.get("������̲��������ȼ�").equals("null")){
				dygccxpddj="";
			}else{
				dygccxpddj=map.get("������̲��������ȼ�");
			}
			ws.addCell(new Label(3,15,dygccxpddj,ws.getCell(3, 15).getCellFormat()));
			//רҵ����
			String zymc="";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zymc="";
			}else{
				zymc=map.get("רҵ����");
			}
			ws.addCell(new Label(6,15,zymc,ws.getCell(6, 15).getCellFormat()));
			//���������������
			String wyjsjgjqk="";
			if(map.get("���������������")==null || map.get("���������������").equals("null")){
				wyjsjgjqk="";
			}else{
				wyjsjgjqk=map.get("���������������");
			}
			ws.addCell(new Label(3,16,wyjsjgjqk,ws.getCell(3, 16).getCellFormat()));
			//�س�
			String tc="";
			if(map.get("�س�")==null || map.get("�س�").equals("null")){
				tc="";
			}else{
				tc=map.get("�س�");
			}
			ws.addCell(new Label(6,16,tc,ws.getCell(6, 16).getCellFormat()));
			//��ѧ�ڼ�����
			String hjqk="";
			if(map.get("�����")==null || map.get("�����").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("�����");
			}
			ws.addCell(new Label(1,17,hjqk,ws.getCell(1, 17).getCellFormat()));
			//��������
			ws.addCell(new Label(0,21,map.get("sqly"),ws.getCell(0, 21).getCellFormat()));
			//������
			ws.addCell(new Label(1,28,map.get("shyj1"),ws.getCell(1, 28).getCellFormat()));
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,28,bjfdyyj,ws.getCell(1, 28).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * �㴨������ѧ��������ӡ
	 * @param wwb
	 * @param map
	 */
	public void printHcjsjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧУ����
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//�༶����
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//�����ϵ�����
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//����ϵ�����
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(8,12,1,3,file);   
			ws.addImage(wi);
			}
			//������ò
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//��Դʡ��
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//�걨�ȼ�
			ws.addCell(new Label(2,15,map.get("sbdj")+"��",ws.getCell(2, 15).getCellFormat()));
			//רҵ����
			String zymc="";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zymc="";
			}else{
				zymc=map.get("רҵ����");
			}
			ws.addCell(new Label(5,15,zymc,ws.getCell(5, 15).getCellFormat()));
			//�س�
			String tc="";
			if(map.get("�س�")==null || map.get("�س�").equals("null")){
				tc="";
			}else{
				tc=map.get("�س�");
			}
			ws.addCell(new Label(8,15,tc,ws.getCell(8, 15).getCellFormat()));
			//�ڼ��λ�û㴨������ѧ��
			String hcjsjxj="";
			if(map.get("�ڼ��λ�û㴨������ѧ��")==null || map.get("�ڼ��λ�û㴨������ѧ��").equals("null")){
				hcjsjxj="";
			}else{
				hcjsjxj=map.get("�ڼ��λ�û㴨������ѧ��");
			}
			ws.addCell(new Label(7,16,hcjsjxj,ws.getCell(7, 16).getCellFormat()));
			//��ѧ�ڼ�����
			String hjqk="";
			if(map.get("�����")==null || map.get("�����").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("�����");
			}
			ws.addCell(new Label(1,17,hjqk,ws.getCell(1, 17).getCellFormat()));
			//��������
			ws.addCell(new Label(0,21,map.get("sqly"),ws.getCell(0, 21).getCellFormat()));
			//������
			ws.addCell(new Label(1,28,map.get("shyj1"),ws.getCell(1, 28).getCellFormat()));
			//�Ƚ�
			ws.addCell(new Label(1,31,"    ����ˣ���ͬѧ��������ַ�������������ͬ������񡰻㴨������ѧ�� "+
					map.get("sbdj")+" �Ƚ���",ws.getCell(1, 31).getCellFormat()));
			ws.addCell(new Label(1,36,"    ����ˣ���ͬѧ��������ַ�������������ͬ������񡰻㴨������ѧ�� "+
					map.get("sbdj")+" �Ƚ���",ws.getCell(1, 36).getCellFormat()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ��˹�ؽ�ѧ��������ӡ
	 * @param wwb
	 * @param map
	 */
	public void printWstjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧУ����
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//רҵ����
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//�༶����
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//�����ϵ�����
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//����ϵ�����
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//��������
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(8,12,1,4,file);   
			ws.addImage(wi);
			}
			//������ò
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//ѧ��
			ws.addCell(new Label(3,13,map.get("xlmc"),ws.getCell(3, 13).getCellFormat()));
			//רҵ
			ws.addCell(new Label(6,13,map.get("zymc"),ws.getCell(6, 13).getCellFormat()));
			//����ְ��
			ws.addCell(new Label(1,14,map.get("xszw"),ws.getCell(1, 14).getCellFormat()));
			//רҵ����
			String zypm="";
			if(map.get("רҵ����")==null || map.get("רҵ����").equals("null")){
				zypm="";
			}else{
				zypm=map.get("רҵ����");
			}
			ws.addCell(new Label(3,14,zypm,ws.getCell(3, 14).getCellFormat()));
			//�걨�ȼ�
			ws.addCell(new Label(6,14,map.get("sbdj"),ws.getCell(6, 14).getCellFormat()));
			//��ͥ��ַ
			ws.addCell(new Label(1,15,map.get("jtdz"),ws.getCell(1, 15).getCellFormat()));
			//��ѧ�ڼ�����
			String hjqk="";
			if(map.get("�����")==null || map.get("�����").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("�����");
			}
			ws.addCell(new Label(1,16,hjqk,ws.getCell(1, 16).getCellFormat()));
			//������Ҫ�¼�
			String grzysj="";
			if(map.get("������Ҫ�¼�")==null || map.get("������Ҫ�¼�").equals("null")){
				grzysj="";
			}else{
				grzysj=map.get("������Ҫ�¼�");
			}
			ws.addCell(new Label(0,20,grzysj,ws.getCell(0, 20).getCellFormat()));
			//������
			ws.addCell(new Label(1,27,map.get("shyj1"),ws.getCell(1, 27).getCellFormat()));
			//�༶����Ա���
			String bjfdyyj = "";
			if(map.get("�༶����Ա���")==null || map.get("�༶����Ա���").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("�༶����Ա���");
			}
			ws.addCell(new Label(1,27,bjfdyyj,ws.getCell(1, 27).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * У������ѧ����ӡ
	 * @param wwb
	 * @param map
	 */
	public void printXjyxxs(WritableWorkbook wwb, Map<String, String> map) {
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧԺ����
			ws.addCell(new Label(3,2,map.get("xymc"),ws.getCell(3, 2).getCellFormat()));
			//���ڰ༶
			ws.addCell(new Label(7,2,map.get("bjmc"),ws.getCell(7, 2).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(9,2,1,3,file);   
			ws.addImage(wi);
			}
			//����
			ws.addCell(new Label(3,3,map.get("xm"),ws.getCell(3, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(6,3,xb,ws.getCell(6, 3).getCellFormat()));
			//��������
			ws.addCell(new Label(8,3,map.get("csrq"),ws.getCell(8, 3).getCellFormat()));
			//������ò
			ws.addCell(new Label(3,4,map.get("zzmmmc"),ws.getCell(3, 4).getCellFormat()));
			//���ְ��
			ws.addCell(new Label(5,4,map.get("xszw"),ws.getCell(5, 4).getCellFormat()));
			//��ϵ��ʽ
			ws.addCell(new Label(3,5,map.get("jtdh"),ws.getCell(3, 5).getCellFormat()));
			//�س�
			String tc="";
			if(map.get("�س�")==null || map.get("�س�").equals("null")){
				tc="";
			}else{
				tc=map.get("�س�");
			}
			ws.addCell(new Label(8,5,tc,ws.getCell(8, 5).getCellFormat()));
			//��������
			String shry="";
			if(map.get("��������")==null || map.get("��������").equals("null")){
				shry="";
			}else{
				shry=map.get("��������");
			}
			ws.addCell(new Label(2,6,shry,ws.getCell(2, 6).getCellFormat()));
			//ѧ���ܳɼ����꼶��רҵ����λ��
			String xnzcjpm="";
			if(map.get("ѧ���ܳɼ����꼶��רҵ����λ��")==null || map.get("ѧ���ܳɼ����꼶��רҵ����λ��").equals("null")){
				xnzcjpm="";
			}else{
				xnzcjpm=map.get("ѧ���ܳɼ����꼶��רҵ����λ��");
			}
			ws.addCell(new Label(5,7,xnzcjpm,ws.getCell(5, 7).getCellFormat()));
			//ѧ���ۺϲ������꼶��רҵ����λ��
			String xnzhcp="";
			if(map.get("ѧ���ۺϲ������꼶��רҵ����λ��")==null || map.get("ѧ���ۺϲ������꼶��רҵ����λ��").equals("null")){
				xnzhcp="";
			}else{
				xnzhcp=map.get("ѧ���ۺϲ������꼶��רҵ����λ��");
			}
			ws.addCell(new Label(5,8,xnzhcp,ws.getCell(5, 8).getCellFormat()));
			//��Ҫ�¼�
			String zysj="";
			if(map.get("��Ҫ�¼�")==null || map.get("��Ҫ�¼�").equals("null")){
				zysj="";
			}else{
				zysj=map.get("��Ҫ�¼�");
			}
			ws.addCell(new Label(2,9,zysj,ws.getCell(2, 9).getCellFormat()));
			//ѧԺ���
			ws.addCell(new Label(2,10,map.get("xyyj"),ws.getCell(2, 10).getCellFormat()));
			//ѧУ���
			ws.addCell(new Label(7,10,map.get("xxyj"),ws.getCell(7, 10).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ����ѧ����ӡ
	 * @param wwb
	 * @param map
	 */
	public void printYxxs(WritableWorkbook wwb, Map<String, String> map) {
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//ѧУ����
			ws.addCell(new Label(3,2,Base.xxmc,ws.getCell(3, 2).getCellFormat()));
			//ϵ��רҵ���꼶
			ws.addCell(new Label(8,2,map.get("xymc")+"/"+map.get("zymc")+"/"+map.get("nj"),ws.getCell(8, 2).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//��Ƭ
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(10,2,1,3,file);   
			ws.addImage(wi);
			}
			//����
			ws.addCell(new Label(3,3,map.get("xm"),ws.getCell(3, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="��";
			}else if(map.get("xb").equals("2")){
				xb="Ů";
			}else if(map.get("xb").equals("��")){
				xb="��";
			}else if(map.get("xb").equals("Ů")){
				xb="Ů";
			}
			//�Ա�
			ws.addCell(new Label(7,3,xb,ws.getCell(7, 3).getCellFormat()));
			//��������
			ws.addCell(new Label(9,3,map.get("csrq"),ws.getCell(9, 3).getCellFormat()));
			//������ò
			ws.addCell(new Label(3,4,map.get("zzmmmc"),ws.getCell(3, 4).getCellFormat()));
			//���ְ��
			ws.addCell(new Label(6,4,map.get("xszw"),ws.getCell(6, 4).getCellFormat()));
			//�س�
			String tc="";
			if(map.get("�س�")==null || map.get("�س�").equals("null")){
				tc="";
			}else{
				tc=map.get("�س�");
			}
			ws.addCell(new Label(9,4,tc,ws.getCell(9, 4).getCellFormat()));
			//����ͨѶ��
			String yjtxc="";
			if(map.get("����ͨѶ��")==null || map.get("����ͨѶ��").equals("null")){
				yjtxc="";
			}else{
				yjtxc=map.get("����ͨѶ��");
			}
			ws.addCell(new Label(4,5,yjtxc,ws.getCell(4, 5).getCellFormat()));
			//��Ҫ�¼�
			String zysj="";
			if(map.get("��Ҫ�¼�")==null || map.get("��Ҫ�¼�").equals("null")){
				zysj="";
			}else{
				zysj=map.get("��Ҫ�¼�");
			}
			ws.addCell(new Label(2,6,zysj,ws.getCell(2, 6).getCellFormat()));
			//ѧ���ܳɼ����꼶��רҵ����λ��
			String xnzcjpm="";
			if(map.get("ѧ���ܳɼ����꼶��רҵ����λ��")==null || map.get("ѧ���ܳɼ����꼶��רҵ����λ��").equals("null")){
				xnzcjpm="";
			}else{
				xnzcjpm=map.get("ѧ���ܳɼ����꼶��רҵ����λ��");
			}
			ws.addCell(new Label(6,7,xnzcjpm,ws.getCell(6, 7).getCellFormat()));
			//ѧ���ۺϲ������꼶��רҵ����λ��
			String xnzhcppm="";
			if(map.get("ѧ���ۺϲ������꼶��רҵ����λ��")==null || map.get("ѧ���ۺϲ������꼶��רҵ����λ��").equals("null")){
				xnzhcppm="";
			}else{
				xnzhcppm=map.get("ѧ���ۺϲ������꼶��רҵ����λ��");
			}
			ws.addCell(new Label(6,8,xnzhcppm,ws.getCell(6, 8).getCellFormat()));
			//ѧ���Ƽ��ɹ�����
			String xskjcg="";
			if(map.get("ѧ���Ƽ��ɹ�����")==null || map.get("ѧ���Ƽ��ɹ�����").equals("null")){
				xskjcg="";
			}else{
				xskjcg=map.get("ѧ���Ƽ��ɹ�����");
			}
			ws.addCell(new Label(2,9,xskjcg,ws.getCell(2, 9).getCellFormat()));
			//ѧԺ���
			ws.addCell(new Label(2,10,map.get("shyj1"),ws.getCell(2, 10).getCellFormat()));
			//����ί�����
			ws.addCell(new Label(8,10,map.get("shyj2"),ws.getCell(8, 10).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
}


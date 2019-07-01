package xgxt.xsxx.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.utils.ExcelMethods;
import xgxt.xsxx.dao.XsxxRstjDAO;
import xgxt.xsxx.form.XsxxRstjForm;

/**
 * 
* 
* �����ƣ�XsxxRstjService 
* �������� ѧ����Ϣ����ͳ��Service 
* �����ˣ�yijd 
* ����ʱ�䣺2012-7-05 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class XsxxRstjService {
	XsxxRstjDAO dao=new XsxxRstjDAO();
	
	/**
	 * ��ѯNJ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxXsxxRstj() throws Exception{
		String[] nj=dao.cxXsxxNj();
		return dao.cxRstjXyzynj(nj);
	}
	/**
	 * ��ѯ����Ա�����ѧ������ͳ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ccxbmzXsxxRstj(String[] cc,String[] xb,String[] mz) throws Exception {
		return dao.ccxbmzXsxxRstj(cc,xb,mz);
	}
	
	/**
	 * ��ѯѧ����Ϣ�꼶
	 * @return
	 * @throws Exception
	 */
	public String[] cxXsxxNj() throws Exception{
		//String
		return dao.cxXsxxNj();
	}
	/**
	 * yeyp   У��ѧԺ�꼶
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void xqxynj(XsxxRstjForm model, OutputStream os) throws Exception {
		// ����excel����
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ������ͳ��", 0);
		
		String[] njs=dao.cxXsxxNj();
		String[] xqs=dao.cxXq();
		List<HashMap<String, String>> rsList=dao.cxRstjXqxynj(njs, xqs);
		
		int xygs=rsList.size();//ѧԺ����
		int njgs=njs.length;
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			// ����
			ws.mergeCells(0, 0, 0, 2); 
			ws.setRowView(0, 400); // ����ָ���и�
			ws.addCell(new Label(0, 0,"ѧԺ", wcfTytle));
			
			int ejlen=njgs+1;//��������
			for (int i = 0; i < xqs.length; i++) {
				ws.mergeCells((i*ejlen+1), 0, ((i+1)*ejlen), 0); 
				ws.setRowView(0, 400); // ����ָ���и�
				ws.addCell(new Label((i*ejlen+1), 0,xqs[i], wcfTytle));
				//�꼶
				ws.mergeCells((i*(ejlen)+1), 1, ((i+1)*(ejlen)-1), 1); 
				ws.setRowView(0, 400); // ����ָ���и�
				ws.addCell(new Label((i*ejlen+1), 1,"�꼶", wcfTytle));
				
				//�꼶 �ϼ�
				ws.mergeCells((i+1)*(ejlen), 1, (i+1)*(ejlen), 2); 
				ws.setRowView(0, 400); // ����ָ���и�
				ws.addCell(new Label((i+1)*(ejlen), 1,"�ϼ�", wcfTytle));
				
				for (int j = 0; j < njs.length; j++) {
					//�꼶��
					ws.addCell(new Label((i*ejlen+1+j), 2,njs[j], wcfTytle));
				}
			}
			
			for (int i = 0; i < rsList.size(); i++) {
				//ѧԺ����
				ws.addCell(new Label(0, 3+i,rsList.get(i).get("xymc"), wcfTytle));
				for (int j = 0; j < xqs.length; j++) {
					for (int z = 0; z < njs.length; z++) {
						//�꼶�÷�
						ws.addCell(new Label(njgs*j+z+1+j, 3+i,rsList.get(i).get("xq_"+(j+1)+"_"+(z+1)), wcfTytle));
					}
					//�꼶�ϼ�
					ws.addCell(new Label(njgs*(j+1)+1+j, 3+i,rsList.get(i).get("xq_"+(j+1)), wcfTytle));
				}
			}
			//Ԥ�ƽ����ҵ����
			ws.addCell(new Label(0, xygs+3,"Ԥ�ƽ����ҵ����", wcfTytle));
			for (int i = 0; i < xqs.length; i++) {
				ws.mergeCells((njgs+1)*i+1, xygs+3, (njgs+1)*(i+1), xygs+3); 
				ws.setRowView(0, 400); // ����ָ���и�
				ws.addCell(new Label((njgs+1)*(i)+1, xygs+3,"", wcfTytle));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * yeyp   ʡ�в���Ա�����
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void ssccxbmc(XsxxRstjForm model, OutputStream os) throws Exception {
		// ����excel����
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ������ͳ��", 0);
		
		
		//��ò�Σ��Ա������������
		String [] ccmc= dao.cxXsxxCCmc();
		String [] cc= dao.cxXsxxCC();
		String [] xb= dao.cxXsxxXb();
		String [] mz= dao.cxXsxxMz();
		// ��ȡѧ��ͳ����������
		List<HashMap<String, String>> rsList=ccxbmzXsxxRstj(cc,xb,mz);
		//��ñ�ͷ
		String [] columns = dao.uniteArrays(new String[]{"�ܼ�"},ccmc,new String[]{"�ܼ�"},xb,new String[]{"�ܼ�"},mz);

		
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.addCell(new Label(0, 0,"��Ŀ", wcfTytle));
			ws.mergeCells(0, 0, 1, 1);
			ws.addCell(new Label(2, 0,"���", wcfTytle));
			ws.mergeCells(2, 0, cc.length+2, 0);
			ws.addCell(new Label(cc.length+3, 0, "�Ա�", wcfTytle));
			ws.mergeCells(cc.length+3, 0, xb.length+cc.length+3, 0);
			ws.addCell(new Label(cc.length+xb.length+4,0,"����", wcfTytle));
			ws.mergeCells(cc.length+xb.length+4,0, xb.length+cc.length+mz.length+4, 0);
			for (int i = 0; i < columns.length; i++) {
				ws.addCell(new Label(i+2,1, columns[i], wcfTytle));
			}
			ws.addCell(new Label(0,2,"��Դ��ʡ��", wcfTytle));
			ws.mergeCells(0,2,0,rsList.size()+1);
			for(int i = 0;i < rsList.size(); i++){
				//��Դ����
				ws.addCell(new Label(1,i+2, rsList.get(i).get("sydsmc"), wcfTytle));
				//���
				ws.addCell(new Label(2,i+2,rsList.get(i).get("rscc"), wcfTytle));
				for(int j = 0; j < cc.length;j++){
					ws.addCell(new Label( 3+j,i+2,rsList.get(i).get("zd_"+(j+1)), wcfTytle));
				}
				//�Ա�
				ws.addCell(new Label( 3+cc.length,i+2,rsList.get(i).get("rsxb"), wcfTytle));
				for(int j = cc.length; j < cc.length+xb.length;j++){
					ws.addCell(new Label( 4+j,i+2,rsList.get(i).get("zd_"+(j+1)), wcfTytle));
				}
				//����
				ws.addCell(new Label( 4+cc.length+xb.length,i+2,rsList.get(i).get("rsmz"), wcfTytle));
				for(int j = cc.length+xb.length; j < cc.length+xb.length+mz.length;j++){
					ws.addCell(new Label(5+j,i+2, rsList.get(i).get("zd_"+(j+1)), wcfTytle));
				}
				
			}
			//�ϲ�ѧԺ��
		} catch(Exception e){
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * yijd   ѧԺרҵ�꼶
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void xyzynj(XsxxRstjForm model, OutputStream os) throws Exception {
		// ����excel����
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ������ͳ��", 0);
		//���õ�Ԫ�����
		ws.setColumnView(3, 35);
		// ��ȡѧ��ͳ����������
		List<HashMap<String, String>> rsList=cxXsxxRstj();
		String[] njs=cxXsxxNj();
		
		int rows=rsList.size();
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			WritableCellFormat leftTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			// ����
			ws.mergeCells(0, 0, 3, 1); 
			ws.setRowView(0, 400); // ����ָ���и�
			ws.addCell(new Label(0, 0,"��Ŀ", wcfTytle));
			
			ws.mergeCells(4, 0, (njs.length+4), 0); 
			ws.setRowView(0, 400); // ����ָ���и�
			ws.addCell(new Label(4, 0,"�꼶", wcfTytle));
			ws.addCell(new Label(4, 1,"�ܼ�", wcfTytle));
			
			for (int i = 0; i < njs.length; i++) {
				ws.addCell(new Label((i+5), 1,njs[i], wcfTytle));
			}
			for(int i=0;i<rsList.size();i++){
				ws.addCell(new Label(0, (i+2),"ѧԺ", wcfTytle));
				ws.addCell(new Label(1, (i+2),rsList.get(i).get("xymc"), wcfTytle));
				ws.addCell(new Label(2, (i+2),rsList.get(i).get("tjzy"), wcfTytle));
				ws.addCell(new Label(3, (i+2),rsList.get(i).get("zymc"), leftTytle));
				ws.addCell(new Label(4, (i+2),rsList.get(i).get("rs"), wcfTytle));
				for (int j = 0; j < njs.length; j++) {
					ws.addCell(new Label((j+5), (i+2),rsList.get(i).get("nj_"+(j+1)), wcfTytle));
				}
				
			}
			//�ϲ�ѧԺ��
			mergeCells(ws, "0", 0, 2, rows);
			//ѧԺ����
			mergeCells(ws, "0", 1, 2, rows);
			//רҵ�����ϲ�
			mergeCells(ws, "0", 2, 2, rows);
			//�ܼƺϲ�
			mergeCells(ws, "1", 1, 2, 3);
			//�ϼƺϲ�
			for (int i = 0; i < (rows-1); i++) {
				mergeCells(ws, "1", 2, (3+i), 2);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * yijd   ѧԺרҵ�꼶�Ա�  �� ��
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void xyzynjxb(XsxxRstjForm model, OutputStream os) throws Exception {
		String[] nj=dao.cxXsxxNj();
		List<HashMap<String, String>> rsList= dao.cxRstjXqxynjxb(nj);
		
		// ����excel����
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ������ͳ��", 0);
		//���õ�Ԫ�����
		ws.setColumnView(3, 35);
		// ��ȡѧ��ͳ����������
		String[] njs=cxXsxxNj();
		
		int rows=rsList.size();
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			WritableCellFormat leftTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			// ����
			ws.mergeCells(0, 0, 3, 3); 
			ws.setRowView(0, 400); // ����ָ���и�
			ws.addCell(new Label(0, 0,"��Ŀ", wcfTytle));
			
			ws.mergeCells(4, 0, ((njs.length*4)+4), 0); 
			ws.setRowView(0, 400); // ����ָ���и�
			ws.addCell(new Label(4, 0,"�꼶", wcfTytle));
			
			ws.mergeCells(4, 1, 4, 3); 
			ws.setRowView(0, 400); // ����ָ���и�
			ws.addCell(new Label(4, 1,"�ܼ�", wcfTytle));
			
			//ws.mergeCells(5, 1, 8, 1); 
			//ws.setRowView(0, 400); // ����ָ���и�
			for (int i = 0; i < njs.length; i++) {
				ws.mergeCells(((i*4)+5), 1, (((i*4)+5)+3), 1); 
				ws.setRowView(0, 400); // ����ָ���и�
				ws.addCell(new Label(((i*4)+5), 1,njs[i], wcfTytle));
				
				ws.mergeCells(((i*4)+5), 2, ((i*4)+5), 3); 
				ws.setRowView(0, 400); // ����ָ���и�
				ws.addCell(new Label(((i*4)+5), 2,"�ϼ�", wcfTytle));
				
				ws.mergeCells(((i*4)+6), 2, ((i*4)+6)+2, 2); 
				ws.setRowView(0, 400); // ����ָ���и�
				ws.addCell(new Label(((i*4)+6), 2,"�Ա�", wcfTytle));
				
				ws.addCell(new Label(((i*4)+6), 3,"С��", wcfTytle));
				ws.addCell(new Label(((i*4)+7), 3,"��", wcfTytle));
				ws.addCell(new Label(((i*4)+8), 3,"Ů", wcfTytle));
			}
			for(int i=0;i<rsList.size();i++){
				ws.addCell(new Label(0, (i+4),"У��", wcfTytle));
				ws.addCell(new Label(1, (i+4),rsList.get(i).get("xqmc"), wcfTytle));
				ws.addCell(new Label(2, (i+4),rsList.get(i).get("tjxy"), wcfTytle));
				ws.addCell(new Label(3, (i+4),rsList.get(i).get("xymc"), leftTytle));
				ws.addCell(new Label(4, (i+4),rsList.get(i).get("rs"), wcfTytle));
				for (int j = 0; j < njs.length; j++) {
					ws.addCell(new Label(((j*4)+5), (i+4),rsList.get(i).get("nj_"+(j+1)), wcfTytle));
					ws.addCell(new Label(((j*4)+6), (i+4),rsList.get(i).get("nj_"+(j+1)), wcfTytle));
					ws.addCell(new Label(((j*4)+7), (i+4),rsList.get(i).get("nj_"+(j+1)+"_nan"), wcfTytle));
					ws.addCell(new Label(((j*4)+8), (i+4),rsList.get(i).get("nj_"+(j+1)+"_nv"), wcfTytle));
				}
				
			}
			//�ϲ�У����
			mergeCells(ws, "0", 0, 4, rows);
			//ѧԺ�����ϲ�
			mergeCells(ws, "0", 1, 4, rows);
			//ѧԺ���ƺϲ�
			mergeCells(ws, "0", 2, 4, rows);
			//�ܼƺϲ�
			mergeCells(ws, "1", 1, 4, 3);
			//�ϼƺϲ�
			for (int i = 0; i < (rows-1); i++) {
				mergeCells(ws, "1", 2, (5+i), 2);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		//��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * ������  �ϲ����ڵ���ͬ���ݵ� ��Ԫ��
	 * @param sheet		
	 * @param type  0,�кϲ�     1,�кϲ�
	 * @param beginX	�ϲ���Ԫ����ʼ����X
	 * @param beginY	�ϲ���Ԫ����ʼ����Y
	 * @param length 	�ϲ���Ԫ������
	 */
	private void mergeCells(WritableSheet sheet,String type, int beginX,
			int beginY, int length) throws Exception{
		String val="";
		String nextVal="";
		List<int[]> list=new ArrayList<int[]>();
		int indexBX=beginX;
		int indexBY=beginY;
		int indexEX=beginX;
		int indexEY=beginY;
		int[] mergeData=null;
		Cell cell=null;
		if("0".equals(type)){
			for (int i = 1; i <= length; i++) {
				cell=sheet.getCell(indexEX, indexEY);
				if("".equals(val)){
					val=cell.getContents();
				}else{
					nextVal=cell.getContents();
				}
				
				if(!val.equals(nextVal)){
					if(!"".equals(nextVal)){
						val=nextVal;
						mergeData=new int[]{indexBX,indexBY,indexEX,(indexEY-1)};
						list.add(mergeData);
						mergeData=null;
						//��ʼ����ʼλ��
						indexBY=indexEY;
						
					}
				}
				if(i==length){
					mergeData=new int[]{indexBX,indexBY,indexEX,indexEY};
					list.add(mergeData);
				}
				indexEY++;
			}
			
		}else if("1".equals(type)){
			for (int i = 1; i <= length; i++) {
				cell=sheet.getCell(indexEX, indexEY);
				if("".equals(val)){
					val=cell.getContents();
				}else{
					nextVal=cell.getContents();
				}
				
				if(!val.equals(nextVal)){
					if(!"".equals(nextVal)){
						val=nextVal;
						mergeData=new int[]{indexBX,indexBY,(indexEX-1),indexEY};
						list.add(mergeData);
						mergeData=null;
						//��ʼ����ʼλ��
						indexBX=indexEX;
						
					}
				}
				if(i==length){
					mergeData=new int[]{indexBX,indexBY,indexEX,indexEY};
					list.add(mergeData);
				}
				indexEX++;
			}
		}else{
			
		}
		// �ϲ���Ԫ��
		for (int i = (list.size()-1); i >=0 ; i--) {
			if(list.get(i)[0] == list.get(i)[2] && list.get(i)[1] == list.get(i)[3]){
				continue;
			}
			sheet.mergeCells(list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3]);
		}
	}
	
}

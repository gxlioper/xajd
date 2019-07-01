package com.zfsoft.xgxt.dekt.xfsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhForm;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Boolean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DektxfsqService extends SuperServiceImpl<DektxfsqForm,DektxfsqDao> {
	private DektxfsqDao dao= new DektxfsqDao();
	private ZdydrService zdydrService = new ZdydrService();
	private DektxmwhService dektxmwhService = new DektxmwhService();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public DektxfsqService(){
		super.setDao(dao);
	 }
	 
	public boolean checkExist(DektxfsqForm form) throws Exception {
		return dao.checkExist(form);
	}
	
	public boolean saveSq(DektxfsqForm form) throws Exception {
		return dao.saveSq(form);
	}
	
	public boolean tjSq(DektxfsqForm form)throws Exception{
		String splc=dao.getSplc(form);
		boolean result=dao.updateShzt(form.getSqid(),Constants.YW_SHZ);
		if(result){
			result = shlc.runSubmit(form.getSqid(), splc, form.getXh(), "dekt_xfsh_list.do", "dekt_xfsq_sqlb.do");
		}
		return result;
	}

	public Map<String, String> getView(DektxfsqForm model) throws Exception {
		return dao.getView(model);
	}

	/**
	 *  ���Ի�����ģ�棬���ɹ�������Ϊ���������Ӧ.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 14:38
	 * @param outputStream
	 * @return void
	 * @throw
	 */
	public void createWwb(ServletOutputStream outputStream,String drmkdm) {

		// ���ļ�
		WritableWorkbook book = null;
		try {
			//��ȡ�������д��excel��������ע
			List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

			book = Workbook.createWorkbook(outputStream);
			// ���ɵ��������0��ʾsheet1��imporΪ������
			WritableSheet sheet1 = book.createSheet("import", 0);

			//��䵼���м���ע��ʾ�������
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			//���ñ�����ɫ;
			cellFormat1.setBackground(Colour.GREEN);
			for(int i=0;i<drgzxxList.size();i++){
				//���Ǹ�����������Ӧ��ȣ���δʵ��...

				Label label = new Label(i,0, drgzxxList.get(i).get("drlmc"),cellFormat1);
//				sheet1.setColumnView(1, drgzxxList.get(i).get("drlmc").length());

				/*
				 * ������Ψһ(sfzj/sfwy)�������ظ�
				 * ����(sfbt)������Ϊ��
				 * ��󳤶�(zdcd)����󳤶�Ϊ?
				 */
				List<String> pznrList = new ArrayList<String>();
				if("1".equals(drgzxxList.get(i).get("sfwy"))){
					pznrList.add("�����ظ�");
				}
				if("1".equals(drgzxxList.get(i).get("sfbt"))){
					pznrList.add("����Ϊ��");
				}
				if(!StringUtil.isNull(drgzxxList.get(i).get("zdcd"))){
					pznrList.add("��󳤶�Ϊ��"+drgzxxList.get(i).get("zdcd"));
				}

				String pznr = "";
				for(int j=0;j<pznrList.size();j++){
					pznr = pznr+(j+1)+"."+pznrList.get(j);
					if(j!=pznrList.size()-1) pznr+="\r\n";
				}

				WritableCellFeatures cellFeatures = new WritableCellFeatures();
				cellFeatures.setComment(pznr);
				label.setCellFeatures(cellFeatures);

				sheet1.addCell(label);
			}

			//��ȡ��������Ϣ�����ɲ���丨����
			List<HashMap<String,Object>> drfzxxAndFzdmxxList = zdydrService.getDrfzxxAndFzdmxxList(drmkdm);

			//ѭ�����ɸ�����sheet
			for(int k=0;k<drfzxxAndFzdmxxList.size();k++){
				HashMap<String,Object> drfzxxAndFzdmxx = drfzxxAndFzdmxxList.get(k);
				String dm = (String)drfzxxAndFzdmxx.get("fzdmxx_dm");
				String mc = (String)drfzxxAndFzdmxx.get("fzdmxx_mc");
				WritableSheet sheet = book.createSheet((String) drfzxxAndFzdmxx.get("fzmc"),k+1);
				List<HashMap<String,String>> fzdmxxList = (List<HashMap<String,String>>)drfzxxAndFzdmxx.get("fzdmxxList");
				for(int x=0;x<fzdmxxList.size();x++){
					Label label1 = new Label(0, x, fzdmxxList.get(x).get(dm));
					Label label2 = new Label(1, x, fzdmxxList.get(x).get(mc));
					sheet.addCell(label1);
					sheet.addCell(label2);
				}
			}

			//��ȡ�϶���Ŀ��Ϣ
			DektxmwhForm xmwhForm=new DektxmwhForm();
			List<HashMap<String,String>> xmlist=dektxmwhService.getAllList(xmwhForm);

			//���Ի��������ֶ�ƴд�϶���Ŀ��Ϣ
			WritableSheet sheet_sdfz = book.createSheet("�϶���Ŀ��׼",drfzxxAndFzdmxxList.size()+1);
			Label label_lx = new Label(0, 0, "����",cellFormat1);
			Label label_rdxm = new Label(1, 0, "�϶���Ŀ",cellFormat1);
			Label label_rdnrbz = new Label(2,0,"�϶����ݼ���׼",cellFormat1);
			Label label_dj = new Label(3,0,"�ȼ�",cellFormat1);
			Label label_xf = new Label(4,0,"ѧ��",cellFormat1);
			Label label_yjsm = new Label(5,0,"�϶����ݼ�����",cellFormat1);

			sheet_sdfz.addCell(label_lx);
			sheet_sdfz.addCell(label_rdxm);
			sheet_sdfz.addCell(label_rdnrbz);
			sheet_sdfz.addCell(label_dj);
			sheet_sdfz.addCell(label_xf);
			sheet_sdfz.addCell(label_yjsm);

			for(int x=0;x<xmlist.size();x++){
				Label label1 = new Label(0, x+1, xmlist.get(x).get("lx"));
				Label label2 = new Label(1, x+1, xmlist.get(x).get("rdxm"));
				Label label3 = new Label(2, x+1, xmlist.get(x).get("rdnrbz"));
				Label label4 = new Label(3, x+1, xmlist.get(x).get("dj"));
				Label label5 = new Label(4, x+1, xmlist.get(x).get("xf"));
				Label label6 = new Label(5, x+1, xmlist.get(x).get("yjsm"));

				sheet_sdfz.addCell(label1);
				sheet_sdfz.addCell(label2);
				sheet_sdfz.addCell(label3);
				sheet_sdfz.addCell(label4);
				sheet_sdfz.addCell(label5);
				sheet_sdfz.addCell(label6);
			}

			sheet_sdfz.setColumnView(0, 20);
			sheet_sdfz.setColumnView(1, 20);
			sheet_sdfz.setColumnView(2, 40);
			sheet_sdfz.setColumnView(3, 20);
			sheet_sdfz.setColumnView(4, 10);
			sheet_sdfz.setColumnView(5, 100);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// д�����ݲ��ر��ļ�
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *  ���浼����Ϣ.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-10 20:24
	 * @param inputStream
	 * @param path
	 * @param drmkdm
	 * @return java.util.HashMap<java.lang.String,java.lang.Object>
	 * @throw
	 */
	public HashMap<String,Object> saveImport(InputStream inputStream, String path, String drmkdm) throws SQLException{

		HashMap<String, Object> resultMap = null;
		//��ȡ�����й���
		List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

		Workbook wb = null;
		Sheet sheet = null;
		try {
			wb = Workbook.getWorkbook(inputStream);
			sheet = wb.getSheet(0);
			//��֤ģ��ͷ����  error:01
			resultMap = zdydrService.checkImportHeader(sheet, drgzxxList);
			if((Boolean) resultMap.get("result")){
				//ģ��������������Ĳ���
				//��ȡ�����д���List<HashMap<String,String>>  ÿ�ж�Ӧһ��HashMap
				List<HashMap<String,String>> excelDataList = zdydrService.getExcelDataList(sheet,drgzxxList);

				if(excelDataList.isEmpty()){
					resultMap.put("totalCount", excelDataList.size());
					return resultMap;
				}

				//���Ի�������֤��װ
				List<HashMap<String,Object>> lhList = new ArrayList<HashMap<String, Object>>();
				HashMap<String,Object> xhxmlhMap = new HashMap<String, Object>();
				xhxmlhMap.put("tableName","xsxxb");
				xhxmlhMap.put("drlArr",new String []{"xh","xm"});
				xhxmlhMap.put("drlmcArr",new String []{"ѧ��","����"});

				HashMap<String,Object> rdnrlhMap = new HashMap<String, Object>();
				rdnrlhMap.put("tableName","xg_dekt_xmdmb");
				rdnrlhMap.put("drlArr",new String []{"lx","rdxm","rdnrbz","dj"});
				rdnrlhMap.put("drlmcArr",new String []{"����","�϶���Ŀ","�϶����ݼ���׼","�ȼ�"});

				lhList.add(xhxmlhMap);
				lhList.add(rdnrlhMap);

				//������������ڲ�ѯ��֤�ظ���
				String tableName = "(SELECT a.*,b.lx,b.RDXM,b.RDNRBZ,b.DJ FROM XG_DEKT_DEKTXFSQB a " +
						"LEFT JOIN XG_DEKT_XMDMB b ON a.XMID = b.XMID)";

				//����ǰ������������֤ error:02
				resultMap = zdydrService.checkExcelDataList(excelDataList,drgzxxList,tableName,lhList);
				if((Boolean) resultMap.get("result")){
					//��֤ͨ��������Ҫ�ж�excel�����б����Ƿ����ظ�
					resultMap = zdydrService.checkExcelDataRepeat(excelDataList,drgzxxList);
					if((Boolean) resultMap.get("result")){
						//�������ظ���������Ĳ������ݵ����ݿ�Ĳ���
						boolean insertResult = this.insertDataIntoDB(excelDataList);
						if(insertResult){
							resultMap.put("totalCount", excelDataList.size());
						}
					}else{
						//�����ظ������ݴ���������ʾ����excel�ļ���������error:03
						String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
						resultMap.put("errorTipsExcelName", errorTipsExcelName);
					}
				}else{
					//��֤��ͨ�������ݴ���������ʾ����excel�ļ���������
					String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
					resultMap.put("errorTipsExcelName", errorTipsExcelName);
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 *  ����֤ͨ��������ݲ������ݿ�.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-11 16:56
	 * @param excelDataList
	 * @return boolean
	 * @throw
	 */
	private boolean insertDataIntoDB(List<HashMap<String, String>> excelDataList) throws SQLException {

		List<String []> paraList = new ArrayList<String[]>();
		for(HashMap<String,String> excelData:excelDataList){
			String xh = excelData.get("xh");
			String cyfs = excelData.get("cyfs");
			String hjsj = excelData.get("hjsj");
			String sqsm = excelData.get("sqsm");
			String lx = excelData.get("lx");
			String rdxm = excelData.get("rdxm");
			String rdnrbz = excelData.get("rdnrbz");
			String dj = excelData.get("dj");
			paraList.add(new String [] {xh,cyfs,hjsj,sqsm,lx,rdxm,rdnrbz,dj});
		}
		return dao.batchInsertDataIntoDB(paraList);
	}
}



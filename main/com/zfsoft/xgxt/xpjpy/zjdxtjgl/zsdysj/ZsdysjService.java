/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-1-31 ����04:14:16 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.zsdysj;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ֤���ӡ
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-1-31 ����04:14:16 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZsdysjService extends SuperServiceImpl<ZsdysjForm,ZsdysjDao>{
	
	private ZsdysjDao dao = new ZsdysjDao();

	public ZsdysjService(){
		super.setDao(dao);
	}
	
	/**
	 * @����: �������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-2 ����02:17:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param response
	 * @param filePath
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void exportZsNew(ZsdysjForm t, User user, HttpServletResponse response, String filePath) 
		throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = workbook.getSheetAt(1);
		List<HashMap<String, String>> dataList = viewZsdyAll(t, user);
		
		//�޸Ĵ�ӡģ����ʾ����
		HSSFSheet firstSheet = workbook.getSheetAt(0);
		//replace ѧ������
		HSSFRow row4 = firstSheet.getRow(3);// ��ù������ĵ�4��
		HSSFCell cell44 = row4.getCell(3);// ��õ�4�еĵ�4����Ԫ��
		cell44.setCellValue(dataList.get(0).get("xm"));// ����Ԫ��ֵ
		//replace ѧ��
		HSSFCell cell46 = row4.getCell(5);// ��õ�4�еĵ�6����Ԫ��
		cell46.setCellValue(dataList.get(0).get("xh"));// ����Ԫ��ֵ
		//replace ѧ��
		HSSFRow row5 = firstSheet.getRow(4);// ��ù������ĵ�5��
		HSSFCell cell53 = row5.getCell(2);// ��õ�5�еĵ�3����Ԫ��
		cell53.setCellValue(" �� "+dataList.get(0).get("xn")+" ѧ �� �� �� �� �� �㣬�� �� ��");// ����Ԫ��ֵ
		//replace ����
		HSSFRow row6 = firstSheet.getRow(5);// ��ù������ĵ�6��
		HSSFCell cell64 = row6.getCell(3);// ��õ�6�еĵ�4����Ԫ��
		cell64.setCellValue(dataList.get(0).get("xmmc"));// ����Ԫ��ֵ
		//replace ����ƴ��
		HSSFRow row9 = firstSheet.getRow(8);// ��ù������ĵ�9��
		HSSFCell cell94 = row9.getCell(3);// ��õ�9�еĵ�4����Ԫ��
		cell94.setCellValue(dataList.get(0).get("xmpy"));// ����Ԫ��ֵ
		//replace ����Ӣ������
		HSSFRow row10 = firstSheet.getRow(9);// ��ù������ĵ�10��
		HSSFCell cell104 = row10.getCell(3);// ��õ�10�еĵ�4����Ԫ��
		cell104.setCellValue(dataList.get(0).get("xmywmc"));// ����Ԫ��ֵ
		//replace ѧ��
		HSSFRow row11 = firstSheet.getRow(10);// ��ù������ĵ�11��
		HSSFCell cell114 = row11.getCell(3);// ��õ�11�еĵ�4����Ԫ��
		cell114.setCellValue("   Awarded on   "+dataList.get(0).get("xn"));// ����Ԫ��ֵ
		//replace ��ǰʱ��
		HSSFRow row19 = firstSheet.getRow(18);// ��ù������ĵ�19��
		HSSFCell cell197 = row19.getCell(7);// ��õ�19�еĵ�7����Ԫ��	
		cell197.setCellValue(DateUtils.getYear()+"/"+DateUtils.getMonth());// ����Ԫ��ֵ

		
		// ����д��
		int row = 1;
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String, String> dataMap = dataList.get(m);
			HSSFRow hSSFRow = sheet.createRow(row);
			hSSFRow.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xm"));
			hSSFRow.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xh"));
			hSSFRow.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmmc"));
			hSSFRow.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmpy"));
			hSSFRow.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xmywmc"));
			row++;
		}
		OutputStream os = response.getOutputStream();
		workbook.write(os);  
		os.flush();
		os.close();
	}
	
	/**
	 * @����: ֤���ӡ������ҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-2 ����02:35:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> viewZsdyAll(ZsdysjForm t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getPageList(t, user);
	}
}

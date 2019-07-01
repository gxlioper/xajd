package xsgzgl.xsxx.xsxxhz.zjkj;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.xtwh.zpgl.XtwhZpglService;
import xsgzgl.xsxx.xsxxhz.XsxxXxhzbService;

public class XsxxZjkjService extends XsxxXxhzbService {

	XsxxZjkjDAO dao=new XsxxZjkjDAO();
	
	public void printXsxx(WritableWorkbook wwb, String xh) {
		
		XsxxglService stuService = new XsxxglService();
		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// ����������Ϣ
		List<HashMap<String, String>> pjInfo = dao.getPjInfo(xh, "5");
		// ѧ���춯��Ϣ
		List<HashMap<String, String>> xjydInfo = dao.getXjydInfo(xh, "3");
		// �ڹ���ѧ��Ϣ
		List<HashMap<String, String>> qtInfo = dao.getQtInfo(xh, "4");
		// Υ�ʹ�����Ϣ
		List<HashMap<String, String>> wjInfo = dao.getWjInfo(xh, "3");
		// ѧ��������Ϣ
		List<HashMap<String, String>> zzInfo = dao.getZzInfo(xh, "4");
		// ������Ϣ
		HashMap<String, String> dtInfo = dao.getDtInfo(xh);
		// ��ͥ��Ա��Ϣ
		HashMap<String, String> jtcyMap = dao.getJtcyInfo(xh);
		// �����������
		HashMap<String, String> pyccMap = dao.getPyccInfo(stuInfo.get("pycc"));
		// ʵϰ��ҵ��Ϣ
		HashMap<String, String> sxjyMap = dao.getSxjyInfo(xh);
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			XtwhZpglService service = new XtwhZpglService();

			// ��ͷ��Ϣ
			ws.addCell(new Label(0, 0, Base.xxmc + "ѧ����Ϣ���ܱ�", ws.getCell(0, 0)
					.getCellFormat()));

			File file = service.getPhotoFile(stuInfo.get("xh"));
			// ������Ƭ
			if (file == null) {
			} else {
				WritableImage wi = new WritableImage(8, 2, 1, 5, file);
				ws.addImage(wi);
			}

			// --------------------------first line--------------------------------------
			// ѧ��
			ws.addCell(new Label(1, 2, stuInfo.get("xh"), ws.getCell(1,2)
					.getCellFormat()));
			// ����
			ws.addCell(new Label(3, 2, stuInfo.get("xm"), ws.getCell(3, 2)
					.getCellFormat()));
			// �Ա�
			ws.addCell(new Label(5, 2, stuInfo.get("xb"), ws.getCell(5, 2)
					.getCellFormat()));
			// ��������
			ws.addCell(new Label(7, 2, stuInfo.get("csrq"), ws.getCell(7, 2)
					.getCellFormat()));

			// --------------------------secound line--------------------------------------
			// ѧԺ
			ws.addCell(new Label(1, 3, stuInfo.get("xymc"), ws.getCell(1, 3)
					.getCellFormat()));
			// רҵ
			ws.addCell(new Label(3, 3, stuInfo.get("zymc"), ws.getCell(3, 3)
					.getCellFormat()));
			// �༶
			ws.addCell(new Label(5, 3, stuInfo.get("bjmc"), ws.getCell(5, 3)
					.getCellFormat()));
			// ��������
			ws.addCell(new Label(7, 3, stuInfo.get("mzmc"), ws.getCell(7, 3)
					.getCellFormat()));

			// --------------------------third line--------------------------------------
			// ѧ��
			ws.addCell(new Label(1, 4, stuInfo.get("xz"), ws.getCell(1, 4)
					.getCellFormat()));
			// ��ѧ����
			ws.addCell(new Label(3, 4, stuInfo.get("rxrq"), ws.getCell(3, 4)
					.getCellFormat()));
			// ��ҵ����
			ws.addCell(new Label(5, 4, stuInfo.get("byny"), ws.getCell(5, 4)
					.getCellFormat()));
			// ����
			ws.addCell(new Label(7, 4, stuInfo.get("jg"), ws.getCell(7, 4)
					.getCellFormat()));

			// --------------------------fourth line--------------------------------------
			// ������ò
			ws.addCell(new Label(1, 5, stuInfo.get("zzmmmc"), ws.getCell(1, 5)
					.getCellFormat()));
			// �������
			ws.addCell(new Label(3, 5, pyccMap.get("pyccmc"), ws.getCell(3, 5)
					.getCellFormat()));
			// �������ڵ�
			ws.addCell(new Label(5, 5, stuInfo.get("hkszd"), ws.getCell(5,5)
					.getCellFormat()));
			// ��Դ��
			ws.addCell(new Label(7, 5, stuInfo.get("syd"), ws.getCell(7, 5)
					.getCellFormat()));

			// --------------------------fifth line--------------------------------------
			// ������
			ws.addCell(new Label(1, 6, stuInfo.get("cym"), ws.getCell(1, 6)
					.getCellFormat()));
			// ���֤��
			ws.addCell(new Label(3, 6, stuInfo.get("sfzh"), ws.getCell(3, 6)
					.getCellFormat()));	
			
			
			// �ֻ�����
			ws.addCell(new Label(1, 8, stuInfo.get("sjhm"), ws.getCell(1,8)
					.getCellFormat()));
			// ��ͥ�绰
			ws.addCell(new Label(3, 8, jtcyMap.get("lxdh1"), ws.getCell(3, 8)
					.getCellFormat()));
			// QQ����
			ws.addCell(new Label(5, 8, stuInfo.get("qqhm"), ws.getCell(5, 8)
					.getCellFormat()));
			// ��������
			ws.addCell(new Label(7, 8, stuInfo.get("dzyx"), ws.getCell(7, 8)
					.getCellFormat()));

			// --------------------------��ͥ��Ա��Ϣ begin--------------------------------------

			ws.addCell(new Label(1, 9, jtcyMap.get("jtszd"), ws.getCell(1, 9)
					.getCellFormat()));
			ws.addCell(new Label(7, 9, jtcyMap.get("yb"), ws.getCell(7, 9)
					.getCellFormat()));

			ws.addCell(new Label(0, 12, jtcyMap.get("jtcy1_xm"), ws.getCell(0,
					12).getCellFormat()));
			ws.addCell(new Label(1, 12, jtcyMap.get("jtcy1_gx"), ws.getCell(1,
					12).getCellFormat()));
			ws.addCell(new Label(2, 12, jtcyMap.get("jtcy1_nl"), ws.getCell(2,
					12).getCellFormat()));
			ws.addCell(new Label(3, 12, jtcyMap.get("zzmm1"), ws.getCell(3, 12)
					.getCellFormat()));
			ws.addCell(new Label(4, 12, jtcyMap.get("gzdwdz1"), ws.getCell(4,
					12).getCellFormat()));
			ws.addCell(new Label(8, 12, jtcyMap.get("jtcy1_lxdh1"), ws.getCell(8,
					12).getCellFormat()));

			ws.addCell(new Label(0, 13, jtcyMap.get("jtcy2_xm"), ws.getCell(0,
					13).getCellFormat()));
			ws.addCell(new Label(1, 13, jtcyMap.get("jtcy2_gx"), ws.getCell(1,
					13).getCellFormat()));
			ws.addCell(new Label(2, 13, jtcyMap.get("jtcy2_nl"), ws.getCell(2,
					13).getCellFormat()));
			ws.addCell(new Label(3, 13, jtcyMap.get("zzmm2"), ws.getCell(3, 13)
					.getCellFormat()));
			ws.addCell(new Label(4, 13, jtcyMap.get("gzdwdz2"), ws.getCell(4, 13)
					.getCellFormat()));
			ws.addCell(new Label(8, 13, jtcyMap.get("jtcy2_lxdh1"), ws.getCell(8,
					13).getCellFormat()));
		
			ws.addCell(new Label(0, 14, jtcyMap.get("jtcy3_xm"), ws.getCell(0,
					14).getCellFormat()));
			ws.addCell(new Label(1, 14, jtcyMap.get("jtcy3_gx"), ws.getCell(1,
					14).getCellFormat()));
			ws.addCell(new Label(2, 14, jtcyMap.get("jtcy3_nl"), ws.getCell(2,
					14).getCellFormat()));
			ws.addCell(new Label(3, 14, jtcyMap.get("zzmm3"), ws.getCell(3, 14)
					.getCellFormat()));
			ws.addCell(new Label(4, 14, jtcyMap.get("gzdwdz3"), ws.getCell(4, 14)
					.getCellFormat()));
			ws.addCell(new Label(8, 14, jtcyMap.get("jtcy3_lxdh1"), ws.getCell(8,
					14).getCellFormat()));
			// --------------------------��ͥ��Ա��Ϣ end--------------------------------------

			// -----------------------ѧ���춯��Ϣ begin--------------------------------
			
			if(xjydInfo!=null && xjydInfo.size()>0){
				for (int i = 0; i < xjydInfo.size(); i++) {
	
					HashMap<String, String> xjydMap = xjydInfo.get(i);
	
					ws.addCell(new Label(0, 19 + i,xjydMap.get("ydlbmc"), ws
							.getCell(0, 19 + i).getCellFormat()));
					ws.addCell(new Label(1, 19 + i,xjydMap.get("ydrq"), ws
							.getCell(1, 19 + i).getCellFormat()));
					ws.mergeCells(2,19+i,3, 19+i);
					ws.addCell(new Label(2, 19 + i, xjydMap.get("clwh"), ws
							.getCell(2, 19 + i).getCellFormat()));
					ws.mergeCells(4,19+i,5, 19+i);
					ws.addCell(new Label(4, 19 + i, xjydMap.get("ydyy"), ws
							.getCell(4, 19 + i).getCellFormat()));
					
					ws.addCell(new Label(6, 19 + i, xjydMap.get("ydsm"), ws
							.getCell(6, 19 + i).getCellFormat()));
					
					ws.mergeCells(7,19+i,8, 19+i);
					ws.addCell(new Label(7, 19 + i,  xjydMap.get("bz"), ws.getCell(
							7, 19).getCellFormat()));
				}
			}else{
				
				ws.mergeCells(0,19,8, 23);
				
				ws.addCell(new Label(0, 19,"��ֹ����ӡʱ���ѧ�����κμ�¼", ws.getCell(
						0, 19).getCellFormat()));
			}
			// -----------------------ѧ���춯��Ϣ end--------------------------------

			
			// -----------------------������Ϣ begin--------------------------------
			ws.addCell(new Label(1,25, dtInfo.get("yydx"), ws.getCell(1, 25)
					.getCellFormat()));
			ws.addCell(new Label(3, 25, dtInfo.get("rdjjfz"), ws.getCell(3, 25)
					.getCellFormat()));
			ws.addCell(new Label(5, 25, dtInfo.get("ybdy"), ws.getCell(5, 25)
					.getCellFormat()));
			ws.addCell(new Label(7, 25, dtInfo.get("zsdy"), ws.getCell(7, 25)
					.getCellFormat()));
			// -----------------------������Ϣ end --------------------------------
			
			
			// -------------------------������Ϣ begin -----------------------------
			for (int i = 0; i < pjInfo.size(); i++) {
				HashMap<String, String> pjpyMap = pjInfo.get(i);

				ws.addCell(new Label(0, 28 + i, pjpyMap.get("xn"), ws
						.getCell(0, 28 + i).getCellFormat()));
				ws.addCell(new Label(1, 28 + i, pjpyMap.get("zcf"), ws
						.getCell(1, 28 + i).getCellFormat()));
				ws.addCell(new Label(2, 28 + i, pjpyMap.get("zcfbjpm"), ws
						.getCell(2, 28 + i).getCellFormat()));
				ws.addCell(new Label(3, 28 + i, pjpyMap.get("zcfnjzypm"), ws
						.getCell(3, 28 + i).getCellFormat()));
				ws.addCell(new Label(4, 28 + i, pjpyMap.get("jxj"), ws
						.getCell(4, 28 + i).getCellFormat()));
				ws.addCell(new Label(8, 28 + i, pjpyMap.get("rych"), ws
						.getCell(8, 28 + i).getCellFormat()));

			}
			// -------------------------������Ϣ end -----------------------------
			
			// -------------------------����������Ϣ begin -----------------------------
			for (int i = 0; i < qtInfo.size(); i++) {
				HashMap<String, String> qtInfoMap = qtInfo.get(i);

				ws.addCell(new Label(0, 35 + i, qtInfoMap.get("xn"), ws
						.getCell(0, 35 + i).getCellFormat()));
				ws.addCell(new Label(1, 35 + i, qtInfoMap.get("jlnr"), ws
						.getCell(1, 35 + i).getCellFormat()));
				ws.addCell(new Label(6, 35 + i, qtInfoMap.get("txgbjl"), ws
						.getCell(6, 35 + i).getCellFormat()));

			}
			// -------------------------������Ϣ end -----------------------------
			
			// -------------------------Υ�ʹ��� begin -----------------------------
			if(wjInfo!=null && wjInfo.size()>0){
				
				ws.mergeCells(0,42,1, 42);
				ws.mergeCells(2,42,3, 42);
				ws.mergeCells(4,42,5, 42);
				ws.mergeCells(6,42,7, 42);
				
				ws.mergeCells(0,43,1, 43);
				ws.mergeCells(2,43,3, 43);
				ws.mergeCells(4,43,5, 43);
				ws.mergeCells(6,43,7, 43);
				
				ws.mergeCells(0,44,1, 45);
				ws.mergeCells(2,44,3, 45);
				ws.mergeCells(4,44,5, 45);
				ws.mergeCells(6,44,7, 45);
				ws.mergeCells(8,44,8, 45);
				for (int i = 0; i < wjInfo.size(); i++) {
	
					HashMap<String, String> wjMap = wjInfo.get(i);
					
					ws.addCell(new Label(0, 42 + i, wjMap.get("xn"), ws.getCell(0,
							42 + i).getCellFormat()));
					ws.addCell(new Label(2, 42 + i, wjMap.get("cflbmc"), ws
							.getCell(2, 42 + i).getCellFormat()));
					ws.addCell(new Label(4, 42 + i, wjMap.get("cfyymc"), ws
							.getCell(4, 42 + i).getCellFormat()));
					ws.addCell(new Label(6, 42 + i, wjMap.get("cfsj"), ws.getCell(
							6, 42 + i).getCellFormat()));
					ws.addCell(new Label(8, 42 + i, wjMap.get("cfwh"), ws.getCell(
							8, 42 + i).getCellFormat()));
				}
			}else{
				ws.mergeCells(0,42,8, 45);
				
				ws.addCell(new Label(0, 42,"��ֹ����ӡʱ���ѧ�����κμ�¼", ws.getCell(
						0, 42).getCellFormat()));
			}
			// -------------------------Υ�ʹ��� end -----------------------------
			
			
			// -------------------------ѧ������ begin -----------------------------
			for (int i = 0; i < zzInfo.size(); i++) {

				HashMap<String, String> zzMap = zzInfo.get(i);

				ws.addCell(new Label(0, 48 + i, zzMap.get("xn"), ws.getCell(0,
						48 + i).getCellFormat()));
				ws.addCell(new Label(2, 48 + i, zzMap.get("xmzzjb"), ws
						.getCell(2, 48 + i).getCellFormat()));
				ws.addCell(new Label(4, 48 + i, zzMap.get("xmmc"), ws.getCell(
						4, 48 + i).getCellFormat()));
				ws.addCell(new Label(8, 48 + i, zzMap.get("xmzzje"), ws
						.getCell(8, 48 + i).getCellFormat()));
			}
			// -------------------------ʵϰ��� end -----------------------------
			ws.addCell(new Label(0,54,sxjyMap.get("sxqk"), ws.getCell(
					0, 54).getCellFormat()));
			ws.addCell(new Label(6,54,sxjyMap.get("jydw"), ws.getCell(
					6, 54).getCellFormat()));
			// -----------------------��ӡʱ��-----------------------
			ws.addCell(new Label(8,58, GetTime.getNowTime2(), ws.getCell(7, 58).getCellFormat()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}


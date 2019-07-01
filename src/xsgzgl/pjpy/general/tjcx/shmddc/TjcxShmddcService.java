package xsgzgl.pjpy.general.tjcx.shmddc;

import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.utils.ExcelMethods;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxShmddcInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_�����������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ��ǿ
 * @version 1.0
 */

public class TjcxShmddcService extends CommService implements
		TjcxShmddcInterface {

	TjcxShmddcDAO dao = new TjcxShmddcDAO();

	/**
	 * ��������������
	 * 
	 * @author ��ǿ
	 */
	public void expShmddc(TjcxShmddcModel model, OutputStream ops)
			throws Exception {
		
		//�꼶
		String arr_nj[] = model.getNj();
		//ѧԺ����
		String arr_xydm[] = model.getXydm();
		//רҵ����
		String arr_zydm[] = model.getZydm();
		//�༶����
		String arr_bjdm[] = model.getBjdm();
		//���״̬
		String arr_shzt[] = model.getShzt();
		//��Ŀ����
		String xmdm = model.getXmdm();
		//������λ
		String spgw = model.getSpgw();
		//ģ����ѯ����ֵ
		String mhcxz = model.getMhcxz();
		//ģ����ѯ����
		String mhcxlx = model.getMhcxlx();
		
		String xmmc = dao.getXmmc(xmdm);
		
		Label titleCell  =  new Label(0, 0, Base.xxmc+ xmmc+"�Ƽ�����");
		WritableWorkbook wwb = Workbook.createWorkbook(ops);
		WritableSheet ws = wwb.createSheet("�������", 0);
		
		//������һ��
		WritableCellFormat wcFormat = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 15);
		font.setBoldStyle(WritableFont.BOLD);
		wcFormat.setFont(font);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleCell.setCellFormat(wcFormat);
		ws.mergeCells(0, 0, 25, 1);
		ws.addCell(titleCell);
		List<String[]>  list = dao.getShmddcList(model,arr_nj,arr_xydm,arr_zydm,arr_bjdm,arr_shzt,xmdm,spgw,mhcxz,mhcxlx);
		String[] sT = {"ѧԺ����","�༶����","ѧ��","����","�Ա�","��Դ��","������ò","���֤��","����","�۲��ܷ�","�������(��ͨ��ϵͳ��ѡ)","ѧ����������(ѧ����д)",
				"�ȼ����Գɼ�","������Ŀ����","���޴���","�ܷ�ʱ�����ѧȫ���ƻ�","��ҵ���(����)��չ�����Ԥ�ڽ��","��������","���������״̬","������������","�����������", 
				"ѧԺѧ�������칫�����״̬","ѧԺѧ�������칫��������","ѧԺѧ�������칫�������","ѧ�������״̬","ѧ����������","ѧ���������"};
		for (int i = 0; i < sT.length; i++) {
			titleCell = new Label(i, 2, sT[i]);
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 10);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
		}
		if(null!=list&&list.size()>0){
		int n = 1;
		for (String[] rs : list) {
			for (int i = 0; i < sT.length; i++) {
				titleCell = new Label(i, n+2, rs[i]);
				wcFormat = new WritableCellFormat();
				font = new WritableFont(WritableFont.ARIAL, 10);
				wcFormat.setFont(font);
				wcFormat.setAlignment(Alignment.CENTRE);
				wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
				}
				n++;
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
		
	}

	public void expShmddc(PjpyGeneralForm myForm, TjcxShmddcModel model,
			OutputStream os) throws Exception {
		// TODO �Զ����ɷ������
		
	}
}
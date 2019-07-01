package xgxt.action.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class DataManService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/**
	 * ѧ�罨�赼������
	 * */
	public String appendXfjsCondition(CommanForm model,String tableName,String sql){
		StringBuffer sb = new StringBuffer();
		if(!StringUtils.isNull(tableName) && ("view_pjpy_xfjs_bjccqkb".equalsIgnoreCase(tableName.toLowerCase()) || "view_pjpy_xfjs_xsjljcb".equalsIgnoreCase(tableName.toLowerCase()))){
			String ccrq = model.getCcrq();
			String jclxdm = model.getJclxdm();
			String wjlxdm = model.getWjlxdm();
			
			if(!StringUtils.isNull(ccrq)){
				sb.append(" and ccrq like '%");
				sb.append(ccrq.replace("'", "��"));
				sb.append("%'");
			}
			
			if(!StringUtils.isNull(jclxdm)){
				sb.append(" and jclxdm ='");
				sb.append(jclxdm.replace("'", "��"));
				sb.append("'");
			}
			
			if(!StringUtils.isNull(wjlxdm)){
				sb.append(" and wjlxdm ='");
				sb.append(wjlxdm.replace("'", "��"));
				sb.append("'");
			}
		}
		sql += sb.toString();
		return sql;
	}
	
	/**
	 * �ж�ѧ���Ƿ�Ҫģ����ѯ
	 * */
	public boolean checkXhEqOrLike(String tableName){
		boolean result = true;
		if(!StringUtils.isNull(tableName) 
				&& ("view_pjpy_xfjs_bjccqkb".equalsIgnoreCase(tableName.toLowerCase()) 
				|| "view_pjpy_xfjs_xsjljcb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_qgzxsqb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_czxx_ftypxxxb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_czxx_typxxxb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_yxtyxxb".equalsIgnoreCase(tableName.toLowerCase())
				)){
			result = false;
		}
		return result;
	}

	/** 
	 * @����:ͬ�ô�ѧ�㽭��Ժ���Ի���������
	 * @���ߣ�cq[���ţ�785]
	 * @���ڣ�2014-3-26 ����02:13:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param exporModel
	 * @param outputStream
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public void bbdcExport(CommanForm dataSearchForm, OutputStream os, User user)
			throws Exception {

		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// ������ͳ���б�
		List<HashMap<String, String>> list = getbbdcExport(dataSearchForm,user);
		int x=0,y=0;   //x:��������   y:Ů������
		for(HashMap<String, String> hm :list){
			if("��".equals(hm.get("xb"))){
				x++;
			}else if("Ů".equals(hm.get("xb"))){
				y++;
			}else{
				//����δ������
			}
		}
		
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append("ѧ��������״������");
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet( "�±���", 1);

		
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.createFont("����"), 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(1, 0, 18, 1);
		ex.printToOneCell_multy(ws, title.toString(), 1, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 450,
				Border.ALL);
		
		//�ڶ��кϲ�
		ws.mergeCells(1, 2, 2, 3);
		ws.mergeCells(3, 2, 6, 3);
		ws.mergeCells(7, 2, 8, 3);
		ws.mergeCells(9, 2, 10, 3);
		ws.mergeCells(11, 2, 12, 3);
		ws.mergeCells(13, 2, 14, 3);
		ws.mergeCells(15, 2, 16, 3);
		ws.mergeCells(17, 2, 18, 3);
		
		//�ڶ����������
		ws.addCell(new Label(1,2,"ѧ������",wcf2));
		ws.addCell(new Label(3,2,String.valueOf(list.size()),wcf2));
		ws.addCell(new Label(7,2,"��������",wcf2));
		ws.addCell(new Label(9,2,String.valueOf(x),wcf2));
		ws.addCell(new Label(11,2,"Ů������",wcf2));
		ws.addCell(new Label(13,2,String.valueOf(y),wcf2));
		ws.addCell(new Label(15,2,"�ص��ע�������У�Ů��",wcf2));
		ws.addCell(new Label(17,2,"",wcf2));
		
		//�����кϲ�
		ws.mergeCells(1, 4, 1, 14);
		ws.mergeCells(2, 4, 18, 14);
		
		//�������������
		ws.addCell(new Label(1,4,"ѧ���������",wcf2));
		ws.addCell(new Label(2,4,"",wcf2));
		
		//�����кϲ�
		ws.mergeCells(1, 15, 1, 16);
		ws.mergeCells(2, 15, 2, 16);
		ws.mergeCells(3, 15, 3, 16);
		ws.mergeCells(3, 15, 3, 16);
		ws.mergeCells(4, 15, 4, 16);
		ws.mergeCells(5, 15, 5, 16);
		ws.mergeCells(6, 15, 6, 16);
		ws.mergeCells(7, 15, 12, 16);
		ws.mergeCells(13, 15, 18, 16);
		
		
		//�������������
		ws.addCell(new Label(1,15,Base.YXPZXY_KEY,wcf2));
		ws.addCell(new Label(2,15,"����",wcf2));
		ws.addCell(new Label(3,15,"�Ա�",wcf2));
		ws.addCell(new Label(4,15,"�༶",wcf2));
		ws.addCell(new Label(5,15,"����",wcf2));
		ws.addCell(new Label(6,15,"��������",wcf2));
		ws.addCell(new Label(7,15,"�������Ϊ�仯",wcf2));
		ws.addCell(new Label(13,15,"��Ԥ��ʩ",wcf2));
		
		//�����кϲ�
		if(list.size()<7){
			ws.mergeCells(1,17,1,16+list.size()+7);
		}else{
			ws.mergeCells(1,17,1,16+list.size());
		}
		
		//�������������
		
		ws.addCell(new Label(1,17,"�ص��עѧ��",wcf2));
		
		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
		
				HashMap<String, String> map = list.get(i);
		
				ws.setColumnView(1,5);
				ws.setColumnView(2,10);
				ws.setColumnView(3,13);
				ws.setColumnView(4,10);
				ws.setColumnView(5,18);
				ws.setColumnView(6,18);
				
				ws.addCell(new Label(2, 17 + i, map.get("xm"), wcf2));// ����
				ws.addCell(new Label(3, 17 + i, map.get("xb"), wcf2));// �Ա�
				ws.addCell(new Label(4, 17 + i, map.get("bjmc"), wcf2));// �༶
				ws.addCell(new Label(5, 17 + i, map.get("ssbh"), wcf2));// ����
				ws.addCell(new Label(6, 17 + i, map.get("tbgxxslbmc"), wcf2));// ��������
				ws.addCell(new Label(7, 17 + i, map.get("zxqjzysj"), wcf2));// �������Ϊ�仯
				ws.addCell(new Label(13, 17 + i, map.get("xytbgxcs"), wcf2));// ���ڴ�ʩ
				ws.mergeCells(7, 17 + i, 12, 17 + i);
				ws.mergeCells(13, 17 + i, 18, 17 + i);
			}
		
		}
		
		//������ݼ�С��7������7�пհ�
		
		if(list.size()<7){
			for(int i = 0; i<7; i++){
				ws.addCell(new Label(2, 17 +list.size()+ i, "", wcf2));// ����
				ws.addCell(new Label(3, 17 +list.size()+ i, "", wcf2));// �Ա�
				ws.addCell(new Label(4, 17 +list.size()+ i, "", wcf2));// �༶
				ws.addCell(new Label(5, 17 +list.size()+ i, "", wcf2));// ����
				ws.addCell(new Label(6, 17 +list.size()+ i, "", wcf2));// ��������
				ws.addCell(new Label(7, 17 +list.size()+ i, "", wcf2));// �������Ϊ�仯
				ws.addCell(new Label(13, 17 +list.size()+ i, "", wcf2));// ���ڴ�ʩ
				ws.mergeCells(7, 17 +list.size()+ i, 12, 17 + i);
				ws.mergeCells(13, 17 +list.size()+ i, 18, 17 + i);
			}
		}
		
		//���һ�кϲ�
		if(list.size()<7){
			ws.mergeCells(1, 17+list.size()+7, 1, 17+list.size()+20+7);
			ws.mergeCells(2, 17+list.size()+7, 18, 17+list.size()+20+7);
		}else{
			ws.mergeCells(1, 17+list.size(), 1, 17+list.size()+20);
			ws.mergeCells(2, 17+list.size(), 18, 17+list.size()+20);
		}
		
		//���һ���������
		if(list.size()<7){
			ws.addCell(new Label(1,17+list.size()+7,"��ѧУ���������������Ľ�������",wcf2));
			ws.addCell(new Label(2,17+list.size()+7,"",wcf2));
		}else{
			ws.addCell(new Label(1,17+list.size(),"��ѧУ���������������Ľ�������",wcf2));
			ws.addCell(new Label(2,17+list.size(),"",wcf2));
		}
		
		//����ϲ���
		if(list.size()<7){
			ws.mergeCells(0, 0, 0, 17+list.size()+20+7);
		}else{
			ws.mergeCells(0, 0, 0, 17+list.size()+20);
		}
		
		//���һ���������
		ws.setColumnView(0,5);
		ws.addCell(new Label(0,0,"ͬ�ô�ѧ�㽭ѧԺѧ��������״������",wcf2));
		
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 
	 * @����:ͬ�ô�ѧ�㽭��Ժ���Ի���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-3-26 ����03:33:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getbbdcExport(CommanForm dataSearchForm, User user) {
		
		DAO dao = DAO.getInstance();
		

		StringBuilder sql = new StringBuilder();  
		
		
		sql.append("select * from (select xh||tbgxxslbdm ����,rownum r,a.* from view_xytbgxxsxx a where 1=1 ) a where 1=1 ");
		
		if(!dataSearchForm.getNj().isEmpty()){
			sql.append(" and nj = '"+ dataSearchForm.getNj()+"'");
		}
		if(!dataSearchForm.getXh(). isEmpty()){
			sql.append(" and xh = '"+ dataSearchForm.getXh()+"'");
		}
		if(!dataSearchForm.getXm().isEmpty()){
			sql.append(" and xm = '"+ dataSearchForm.getXm()+"'");
		}
		if(!dataSearchForm.getNd().isEmpty()){
			sql.append(" and xn = '"+ dataSearchForm.getXn()+"'");
		}
		if(!dataSearchForm.getXq().isEmpty()){
			sql.append(" and nd = '"+ dataSearchForm.getNd()+"'");
		}
		if(!dataSearchForm.getXq().isEmpty()){
			sql.append(" and xq = '"+ dataSearchForm.getXq()+"'");
		}
		if(!dataSearchForm.getXydm().isEmpty()){
			sql.append(" and xydm = '"+ dataSearchForm.getXydm()+"'");
		}
		if(!dataSearchForm.getZydm().isEmpty()){
			sql.append(" and zydm = '"+ dataSearchForm.getZydm()+"'");
		}
		if(!dataSearchForm.getBjdm().isEmpty()){
			sql.append(" and bjdm = '"+ dataSearchForm.getBjdm()+"'");
		}
		if(!dataSearchForm.getLydm().isEmpty()){
			sql.append(" and lydm = '"+ dataSearchForm.getLydm()+"'");
		}
		
		
		String[] colArr=new String[] { "xm","xb","bjmc","ssbh","tbgxxslbmc","zxqjzysj","xytbgxcs"};
		
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), 
				new String[]{} , colArr);

		return list;
	}
}

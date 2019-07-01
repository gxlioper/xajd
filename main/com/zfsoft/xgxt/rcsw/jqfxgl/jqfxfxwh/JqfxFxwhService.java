/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����04:02:24 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxfxwh;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszForm;
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����04:02:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JqfxFxwhService  extends SuperServiceImpl<JqfxFxwhForm, JqfxFxwhDao> {
	public static String JQFXWWHZT = "0";
	public static String JQFXWHZT = "1";
	
	/**
	 * 
	 * @����:TODO(��У����)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFxmc()throws Exception {
			return dao.getFxmc();
	}
		
	/**
	 * 
	 * @����:TODO(δ��У)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneFxgljgList(String  xh) {
		    return dao.getOneFxgljgList(xh);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡδ��У��Ϣ��¼)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneWfxxwhjgList(String  xh) {	
		 Map<String, String> newMap =  dao.getOneWfxxwhjgList(xh);
		 
		 //�����ѧ�ŵ�ѧ��δ����
		 if(newMap.size() == 0){
			 newMap.put("fxztmc", "δ����");
		 }
		 if(newMap.get("wfxyy") == null){
			 newMap.put("wfxyy", "");		 	 
		 }
		 if(newMap.get("sfqdlx") == null){
			 newMap.put("sfqdlx", "��");		 	 
		 }
		  return newMap;
	}
	
	/**
	 * 
	 * @����:TODO(���淵Уά��)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJqFxwh(JqfxFxwhForm model) throws Exception {	
		boolean flag=true;
		//model.setXq("��".equals(model.getXq())?"01":"02");
		
		model.setFxdm(getFxdm());		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){
			//���÷�У״̬Ϊ��У
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//�ÿ�δ��Уԭ��

			//�޸ļ���δ��Уά��
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else {
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}
		 
		/*if(JQFXWWHZT.equals(model.getFxzt())){
			//���÷�У״̬Ϊ��У
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//�ÿ�δ��Уԭ��
			//�޸ļ���δ��Уά��
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else{
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}	*/
		return flag;
	}

	

	/**
	 * 
	 * @����:TODO(�������δ��У)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJqWfxwh(JqfxFxwhForm model) throws Exception {	
		model.setXq("��".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());
		//���շ�У
		boolean flag=true;		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){
					if(JQFXWHZT.equals(model.getFxzt())){
						model.setFxsj("");
						model.setFxzt(JQFXWWHZT);
			}
			//�޸ļ���δ��Уά��
			boolean updateResult = super.runUpdate(model);
			flag = updateResult;
		}else{			
			//���÷�У״̬Ϊ��У
			model.setFxzt(JQFXWWHZT);	
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}		
		return flag;
	}
		
	/** 
	 * @����:TODO(��ȡ���ڷ�У���������еķ�У����)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * JqfxjcszForm �������� 
	 * @throws 
	 */
	public String getFxdm() throws Exception {
		JqfxJbszService jqfxjbszservice = new JqfxJbszService();
		JqfxJbszForm jcszModel = jqfxjbszservice.getModel();
		String fxdm = jcszModel.getFxdm();
		return fxdm;
	}
	
	/**
	 * 
	 * @����:TODO(������ȡ���ݿ������δ��У��¼)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCountNum(JqfxFxwhForm model,User user)throws Exception {
		return dao.getCountNum(model,user);
	}
	
	/**
	 * 
	 * @����:TODO(�����������ά��)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean plSaveJqwh(JqfxFxwhForm model,User user) throws Exception {					
		boolean flag = false;
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //��ǰѧ��	
		model.setXn(currXn);
		model.setXq(Base.currXq);	
		model.setFxzt(JQFXWHZT);
		flag = dao.plSaveJqwh(model,user);		
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(�������������ڷ�У)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pldgSaveJqwh(JqfxFxwhForm model,User user) throws Exception{
		 
			boolean flag = false;
			model.setFxdm(getFxdm());
			String currXn = Base.currXn; //��ǰѧ��	
			model.setXn(currXn);
			model.setXq("01".equals(Base.currXq)?"��":"��");	
			model.setFxzt(JQFXWHZT);			
				
			flag = dao.pldgSaveJqwh(model,user);		
			return flag;
	 }
	
	/**
	 * 
	 * @����:TODO(����ѧ������δ��Уά��)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:24 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveplxsJqWfxwh(JqfxFxwhForm model) throws Exception {
		
		model.setXq("��".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());		
		//���շ�У
		boolean flag=true;
			
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //��ǰѧ��	
		model.setXn(currXn);
		model.setXq("01".equals(Base.currXq)?"��":"��");	
		model.setFxzt(JQFXWWHZT);		
		dao.pldgSaveJqwfx(model);	
		return flag;
		
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-4-24 ����05:32:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllWfxyy() {
		
		return dao.getAllWfxyy();
	}
	public String getWfxyyMc(String dm) {
		
		return dao.getWfxyyMc(dm);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-4-27 ����02:26:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @param hashMap
	 * @return
	 * File �������� 
	 * @throws 
	 */
		public File getBdqktjFile(List<HashMap<String, String>> resultList) throws IOException, Exception {
			//��Excel
			String fileName = System.currentTimeMillis() + ".xls";
			File file = new File(System.getProperty("java.io.tmpdir"),fileName);
			
			
			if(!file.exists()){
				file.createNewFile();
			}	
			
			//����������
			WritableWorkbook  wwb = Workbook.createWorkbook(file);
			
			//������ظ�ʽ
			WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�������
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
			
			
			WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
			body_cf.setWrap(true);
			head_cf.setWrap(true);//�Զ�����
			title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
			title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ��ⵥԪ��߿�
//			title_cf.setBackground(Colour.YELLOW);	//���ñ��ⱳ��ɫ
//			body_hj_cf.setAlignment(jxl.format.Alignment.LEFT);
//			body_hj_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ô�ֱ����
//			body_hj_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷˮƽ����
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
//			body_sp_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//����ˮƽ����
//			body_sp_cf.setAlignment(jxl.format.Alignment.CENTRE);
			
			body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
			body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//����ˮƽ����
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
//			body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
//			body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
				//List<Map<String,String>> list = iterator.next();	//�༶��Ӧ�����ѧ���б�
				String title = Base.currXn+"-"+Base.currXq+"ѧ�ڱ������ͳ�Ʊ�";	//����
				
				//����������
				WritableSheet sheet = wwb.createSheet("sheet 1", 0);
				//���ø����п�
				sheet.setColumnView(0, 20);
				sheet.setColumnView(1, 10);
				sheet.setColumnView(2, 10);
				sheet.setColumnView(3, 10);
				sheet.setColumnView(4, 10);
				sheet.setColumnView(5, 10);
				sheet.setColumnView(6, 10);
				sheet.setColumnView(7, 10);
				sheet.setColumnView(8, 10);
				sheet.setColumnView(9, 10);
				sheet.setColumnView(11, 10);
				sheet.setColumnView(12, 10);
				sheet.setColumnView(13, 10);
				sheet.setColumnView(14, 10);
				sheet.setColumnView(15, 10);
				sheet.setColumnView(16, 20);
				sheet.setRowView(0, 700, false);
				sheet.setRowView(1, 500, false);
				sheet.setRowView(2, 500, false);
				
				//�ϲ���Ԫ��
				//��һ��������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
				//�ڶ���������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
				//������������Ҫ�ϲ��ĵ�Ԫ�����ҽǵ��кţ�
				//���ĸ�������Ҫ�ϲ��ĵ�Ԫ�������½ǵ���
				sheet.mergeCells(0, 0, 16, 0);	//����
				sheet.mergeCells(0, 1, 0, 2);//ѧԺ
				sheet.mergeCells(1, 1, 1, 2);//�ڼ�����
				sheet.mergeCells(2, 1, 2, 2);//����ѧ
				sheet.mergeCells(3, 1, 3, 2);//�ѳ���������̨��
				sheet.mergeCells(7, 1,15, 1);//δ��ԭ��
				sheet.mergeCells(16, 1,16, 2);//��ע
				//�������⼰��ͷ
				Label t_0_0 = new Label(0, 0,title,title_cf);
				Label h_0_1 = new Label(0,1,"ѧԺ",head_cf);
				Label h_1_1 = new Label(1,1,"�ڼ�����",head_cf);
				Label h_2_1 = new Label(2,1,"����ѧ",head_cf);
				Label h_3_1 = new Label(3,1,"�ѳ���������̨��",head_cf);
				Label h_4_1 = new Label(4,1,"Ӧ��",head_cf);
				Label h_4_2 = new Label(4,2,"����",head_cf);
				Label h_5_1 = new Label(5,1,"ʵ��",head_cf);
				Label h_5_2 = new Label(5,2,"����",head_cf);
				Label h_6_1 = new Label(6,1,"δ��",head_cf);
				Label h_6_2 = new Label(6,2,"����",head_cf);
				Label h_7_1 = new Label(7,1,"δ��ԭ��",head_cf);
				Label h_7_2 = new Label(7,2,"�¼�",head_cf);
				Label h_8_2 = new Label(8,2,"����",head_cf);
				Label h_9_2 = new Label(9,2,"����",head_cf);
				Label h_10_2 = new Label(10,2,"������",head_cf);
				Label h_11_2 = new Label(11,2,"����",head_cf);
				Label h_12_2 = new Label(12,2,"������",head_cf);
				Label h_13_2 = new Label(13,2,"��;��",head_cf);
				Label h_14_2 = new Label(14,2,"ԭ����",head_cf);
				Label h_15_2 = new Label(15,2,"����ԭ��",head_cf);
				Label h_16_1 = new Label(16,1,"��ע",head_cf);
				
				sheet.addCell(t_0_0);
				sheet.addCell(h_0_1);
				sheet.addCell(h_1_1);
				sheet.addCell(h_2_1);
				sheet.addCell(h_3_1);
				sheet.addCell(h_4_1);
				sheet.addCell(h_4_2);
				sheet.addCell(h_5_1);
				sheet.addCell(h_5_2);
				sheet.addCell(h_6_1);
				sheet.addCell(h_6_2);
				sheet.addCell(h_7_1);
				sheet.addCell(h_7_2);
				sheet.addCell(h_8_2);
				sheet.addCell(h_9_2);
				sheet.addCell(h_10_2);
				sheet.addCell(h_11_2);
				sheet.addCell(h_12_2);
				sheet.addCell(h_13_2);
				sheet.addCell(h_14_2);
				sheet.addCell(h_15_2);
				sheet.addCell(h_16_1);
				
				//����������Ԫ��
				int size = resultList.size();
				if(size>0){
					for(int j=0;j<size;j++){
						Map<String,String> map = resultList.get(j);
						Map<String,String> wbdqkMap = new HashMap<String, String>();
						wbdqkMap.put("�¼�", "");
						wbdqkMap.put("����", "");
						wbdqkMap.put("����", "");
						wbdqkMap.put("����", "");
						wbdqkMap.put("��;��", "");
						wbdqkMap.put("ԭ����", "");
						wbdqkMap.put("����ԭ��", "");
						wbdqkMap.put("�ѳ���������̨��", "");
						wbdqkMap.put("����ѧ", "");
						String[] wbdqkArr = map.get("wbdqk").split(";");
						for (String s : wbdqkArr) {
							String[] temp = s.split("_");
							wbdqkMap.put(temp[0], temp[1]);
						}
						int xyzrsint = Integer.valueOf(map.get("xyzrs"));
						int yxint = Integer.valueOf("".equals(wbdqkMap.get("����"))?"0":wbdqkMap.get("����"));
						int ytint = Integer.valueOf("".equals(wbdqkMap.get("����"))?"0":wbdqkMap.get("����"));
						//DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();  
						 DecimalFormat df = new DecimalFormat("0.0000%");  
				        //ģʽ ������������  
				        df.setRoundingMode(RoundingMode.HALF_UP);  
				        String yxlStr="" , ytlStr="";
				       
						if(yxint!=0){
							 double yxl_d = (yxint*1.00) / (xyzrsint*1.00);  
							 yxlStr = df.format(yxl_d)+"";
						}
						if(yxint!=0){
							 double ytl_d = (ytint*1.00) /( xyzrsint*1.00);
							 ytlStr = df.format(ytl_d)+"";
						}
				       
				       
				        
						Integer sdrsInt = dao.getSdrs(map.get("xsxydm"));
						Label xymc = new Label(0, j+3, map.get("xymc"), body_cf);		//ѧԺ
						Label zjrs = new Label(1, j+3, map.get("yxjrs"), body_cf);		//�ڼ�����--
						Label ysx = new Label(2, j+3, wbdqkMap.get("����ѧ"), body_cf);		//����ѧ
						Label ycg = new Label(3, j+3, wbdqkMap.get("�ѳ���������̨��"), body_cf);		//����������̨��
						Label ydrs = new Label(4, j+3, map.get("xyzrs"), body_cf);		//Ӧ������
						Label sdrs = new Label(5, j+3, sdrsInt.toString(), body_cf);		//ʵ������--
						Label wdrs = new Label(6, j+3, map.get("wbdzrs"), body_cf);		//δ������
						Label sj = new Label(7, j+3, wbdqkMap.get("�¼�"), body_cf);		
						Label bj = new Label(8, j+3, wbdqkMap.get("����"), body_cf);		
						Label yt = new Label(9, j+3, wbdqkMap.get("����"), body_cf);		
						Label ytl = new Label(10, j+3, ytlStr, body_cf);		//������--
						Label yx = new Label(11, j+3, wbdqkMap.get("����"), body_cf);		
						Label yxl = new Label(12, j+3, yxlStr, body_cf);		//������--
						Label ztz = new Label(13, j+3, wbdqkMap.get("��;��"), body_cf);
						Label yybm = new Label(14, j+3, wbdqkMap.get("ԭ����"), body_cf);		
						Label qtyy = new Label(15, j+3, wbdqkMap.get("����ԭ��"), body_cf);		
						Label bz = new Label(16, j+3, "", body_cf);	//��ע--	
						sheet.addCell(xymc);
						sheet.addCell(zjrs);
						sheet.addCell(ysx);
						sheet.addCell(ycg);
						sheet.addCell(ydrs);
						sheet.addCell(sdrs);
						sheet.addCell(wdrs);
						sheet.addCell(sj);
						sheet.addCell(bj);
						sheet.addCell(yt);
						sheet.addCell(ytl);
						sheet.addCell(yx);
						sheet.addCell(yxl);
						sheet.addCell(ztz);
						sheet.addCell(yybm);
						sheet.addCell(qtyy);
						sheet.addCell(bz);
						sheet.setRowView(j+3, 500, false);
					}
				}
			
			//�������Ϊ��
			if(resultList==null||resultList.size()==0){
				//����������
				WritableSheet sheetNull = wwb.createSheet("���ε�������Ϊ��", 0);
				sheetNull.setColumnView(0, 15);
				Label msg = new Label(0, 0,"�������ݣ�");
				sheetNull.addCell(msg);
			}
			
			wwb.write();
			wwb.close();
				
			return file;
		}

	/**
	 * @throws Exception 
	 * @param model  
	 * @����:��ȡδ�����������(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-4-27 ����02:35:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBdqktjList(JqfxFxwhForm model,User user) throws Exception {
		
		return dao.getBdqktjList(model,user);
	}

	
	
}

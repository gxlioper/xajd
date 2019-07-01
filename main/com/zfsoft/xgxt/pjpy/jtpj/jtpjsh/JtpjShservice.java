/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-4 ����02:30:12 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjsh;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjjg.JtpjJgForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjjg.JtpjJgService;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqService;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-4 ����02:30:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjShservice extends JtpjSqService {
	private JtpjShDao jsd = new JtpjShDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz = new BaseDbcz();

	public JtpjShservice() {
		setDao(jsd);
		dbcz.setShPath("jtpjshbase.do");
		dbcz.setGnmkMc("������������");
		dbcz.setXmmc("��������");
	}

	/**
	 * 
	 * @����:���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:47:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 * @throws
	 */
	public boolean saveSh(JtpjShForm form, User user) {
		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		// model.setSqrid(form.getXh());
		model.setTzlj("jtpjshbase.do");
		model.setTzljsq("jtpjsqbase.do");
		
		if(Base.xxdm.equalsIgnoreCase("10704")){
			model.setZd1("��������");
			model.setZd3(form.getRdfs());
		}

		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			form.setShzt(zhzt);
			reuslt = jsd.updateShzt(form);
			// ���״̬Ϊͨ��
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				// ��ȡ���ݿ���������
				JtpjSqForm data = getModel(form.getSqid());
				JtpjJgForm jjf = new JtpjJgForm();
				BeanUtils.copyProperties(jjf, data);
				JtpjJgService jjs = new JtpjJgService();
				JtpjSzService jss = new JtpjSzService();
				JtpjSzForm jsf = jss.getModel(jjf.getJxid());
				jjf.setPdxn(jsf.getPdxn());
				jjf.setPdxq(jsf.getPdxq());
				if(Base.xxdm.equalsIgnoreCase("10704")){//�����Ƽ���ѧ���Ի�
					jjf.setRdfs(form.getRdfs());
				}
				jjs.shtgSave(jjf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

public String savePlsh(JtpjShForm form, User user) {
		
		String[] sqids = form.getSqids();
		String[] gwids = form.getGwids();
		String[] splcs = form.getSplcs();
		String[] pjjtmcs=form.getPjjtmcs();

		List<String> failPjjtmcs = new ArrayList<String>();

		for (int i = 0, n = sqids.length; i < n; i++) {
			JtpjShForm model=new JtpjShForm();
			model.setSqid(sqids[i]);
			model.setGwid(gwids[i]);
			model.setSplcid(splcs[i]);
			
			model.setShyj(form.getShyj());
			model.setShjg(form.getShzt());

			boolean isSuccess = saveSh(model, user);

			if (!isSuccess) {
				failPjjtmcs.add(pjjtmcs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(pjjtmcs);

		if (failPjjtmcs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(form.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json	.toString());
		}
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-5 ����03:17:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean cancel(String shlc, String sqid) throws Exception {
		boolean result = false;
		// �������״̬����
		JtpjSqForm upForm = new JtpjSqForm();
		upForm.setSqid(sqid);
		upForm.setShzt(Constants.YW_SHZ);
		JtpjJgService jjs = new JtpjJgService();
		result = dao.runUpdate(upForm, sqid)
				&& jjs.deleteForCx(upForm.getSqid()) ? true : false;

		upForm = dao.getModel(sqid);
		// ���ô�����Ϣ
		dbcz.cancel(upForm.getSqid(), upForm.getSplcid());
		return result;
	}
	
	/** 
	 * @����:��ȡ�������������������Ƽ���ѧ���Ի���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-20 ����10:39:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getPdfs(JtpjShForm model){
		return jsd.getPdfs(model);
	}
	
	/**
	 * @description	�� ��ȡ�����ļ�list
	 * @author 		�� ������1282��
	 * @date 		��2018-1-10 ����09:55:59
	 * @return
	 * @throws Exception 
	 */
	public List<File> getDcFileList(JtpjShForm model) throws Exception{
		List<File> fileList = new ArrayList<File>();
		List<HashMap<String, String>> jtpjmd = jsd.getJtpjmd(model);
		String xydm = null;
		List<HashMap<String, String>> mdList = null;
		for(int i = 0;i < jtpjmd.size();i++){
			HashMap<String,String> map = jtpjmd.get(i);
			if(StringUtils.isNull(xydm)){
				xydm = map.get("xydm");
				mdList = new ArrayList<HashMap<String,String>>();
				mdList.add(map);
			}else{				
				if(xydm.equals(map.get("xydm"))){
					mdList.add(map);
				}else{
					File file = getExcelFile(mdList);
					fileList.add(file);
					xydm = map.get("xydm");
					mdList = new ArrayList<HashMap<String,String>>();
					mdList.add(map);
				}
			}				
		}
		File file = getExcelFile(mdList);
		fileList.add(file);
		return fileList;		
	}
	
	/**
	 * @description	�� ��ȡ����excel�ļ�
	 * @author 		�� ������1282��
	 * @date 		��2018-1-10 ����10:35:03
	 * @return
	 */
	public File getExcelFile(List<HashMap<String,String>> list) throws Exception{
		String xxmc = Base.xxmc;
		String jxmc = list.get(0).get("jxmc");
		String xymc = list.get(0).get("xymc");
		String xn = list.get(0).get("sqxn");
		String xq = list.get(0).get("sqxqmc");
		
		String fileName = xymc + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		//��ȡ����
		Calendar instance = Calendar.getInstance();
		int year = instance.get(Calendar.YEAR);
		int month = instance.get(Calendar.MONTH) + 1;
		int date = instance.get(Calendar.DAY_OF_MONTH);
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		 WritableWorkbook  wwb = Workbook.createWorkbook(file);
		 WritableSheet sheet = wwb.createSheet(xymc, 0);
		 
		 WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�������
		 WritableFont headFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
		 WritableFont headFont_secondary = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
		 WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
		 
		 WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
		 WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
		 WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������			 
		 WritableCellFormat title_secondary = new WritableCellFormat(headFont_secondary);//���õڶ������ⵥԪ������
		 WritableCellFormat title_third = new WritableCellFormat(headFont_secondary);
		 WritableCellFormat body_sqly = new WritableCellFormat(bodyFont);
		 WritableCellFormat body_bottom = new WritableCellFormat(headFont_secondary);
		 
		 title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
		 title_cf.setWrap(true);
		 
		 head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		 head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷˮƽ����
		 head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 head_cf.setWrap(true);
		 
		 
		 body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
		 body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		 body_cf.setVerticalAlignment(VerticalAlignment.CENTRE);
		 body_cf.setWrap(true);
		 
		 body_bottom.setAlignment(jxl.format.Alignment.LEFT);//�������ĵ�Ԫ�����
		 body_bottom.setBorder(jxl.format.Border.NONE, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		 body_bottom.setVerticalAlignment(VerticalAlignment.CENTRE);
		 body_bottom.setWrap(true);
		 
		 title_secondary.setAlignment(jxl.format.Alignment.GENERAL);
		 title_secondary.setVerticalAlignment(VerticalAlignment.CENTRE);
		 title_secondary.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 title_secondary.setWrap(true);
		 
		 title_third.setAlignment(jxl.format.Alignment.CENTRE);
		 title_third.setVerticalAlignment(VerticalAlignment.CENTRE);
		 title_third.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 title_third.setWrap(true);
		 
		 body_sqly.setAlignment(jxl.format.Alignment.LEFT);//�������ĵ�Ԫ�����
		 body_sqly.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		 body_sqly.setVerticalAlignment(VerticalAlignment.CENTRE);
		 body_sqly.setWrap(true);
		 
		 sheet.setRowView(0, 600, false);
		 
		 sheet.setRowView(1, 400, false);
		 
		 sheet.setRowView(2, 300, false);
		 
		 //���ø����п�
		 sheet.setColumnView(0, 6);
		 sheet.setColumnView(1, 20);
		 sheet.setColumnView(2, 12);
		 sheet.setColumnView(3, 16);
		 sheet.setColumnView(4, 60);
		 
		 sheet.mergeCells(0, 0, 4, 0);
		 Label label_title0 = new Label(0, 0, xxmc,title_cf);
		 sheet.addCell(label_title0);
		 
		 sheet.mergeCells(0, 1, 4, 1);
		 Label label_title1 = new Label(0, 1, jxmc+"������",head_cf);
		 sheet.addCell(label_title1);
		 
		 sheet.mergeCells(0, 2, 4, 2);
		 Label label_title2 = new Label(0, 2, xymc+"              "+xn.substring(2,4)+"/"+xn.substring(7, 9)+"ѧ��                                   "+xq+"                                  "+String.valueOf(year)+"��"+String.valueOf(month)+"��"+String.valueOf(date)+"��",title_secondary);
		 sheet.addCell(label_title2);
		 
		 
		 Label label_head0 = new Label(0, 3, "���",title_third);
		 Label label_head1 = new Label(1, 3, "�༶",title_third);
		 Label label_head2 = new Label(2, 3, "������",title_third);
		 Label label_head3 = new Label(3, 3, "ѧ�ڿ��˳ɼ�",title_third);
		 Label label_head4 = new Label(4, 3, "��Ҫ�¼����",title_third);
		 
		 sheet.addCell(label_head0);
		 sheet.addCell(label_head1);
		 sheet.addCell(label_head2);
		 sheet.addCell(label_head3);
		 sheet.addCell(label_head4);
		 
		 for(int i = 0;i < list.size(); i++){
			 //sheet.mergeCells(4, 4+i, 8, 4+i);
			 
			 HashMap<String,String> mapp = list.get(i);
			 HashMap<String,String> map= (HashMap<String, String>) StringUtils.formatData(mapp);
			 Label label_xh = new Label(0, 4+i, String.valueOf(i+1),body_cf);
			 Label label_bjmc = new Label(1, 4+i, map.get("bjmc"),body_cf);
			 Label label_bzr = new Label(2, 4+i, map.get("bzr"),body_cf);
			 Label label_cj = new Label(3, 4+i, "",body_cf);
			 Label label_sqly = new Label(4, 4+i, map.get("sqly"),body_sqly);
			 
			 sheet.addCell(label_xh);
			 sheet.addCell(label_bjmc);
			 sheet.addCell(label_bzr);
			 sheet.addCell(label_cj);
			 sheet.addCell(label_sqly);
		 }
		 
		 sheet.mergeCells(0, 4+list.size(), 6, 4+list.size());
		 sheet.addCell(new Label(0, 4+list.size(),"",body_bottom));
		 
		 
		 sheet.mergeCells(0, 5+list.size(), 6, 5+list.size());
		 Label bottom = new Label(0, 4+list.size(), "ϵ���Σ�            ��      ��      ��               ѧ������ˣ�              ��      ��      ��                 У����������               ��     ��     ��",body_bottom);
		 sheet.addCell(bottom);
		 wwb.write();
		 wwb.close();
		 
		 return file;
	}
	
	/**
	 * @description	�� ��֤���������Ƿ��ܵ���
	 * @author 		�� ������1282��
	 * @date 		��2018-1-10 ����05:10:22
	 * @return
	 * @throws Exception 
	 */
	public boolean checkJtpjshdc(JtpjShForm form) throws Exception{
		List<HashMap<String,String>> list = jsd.getJtpjmd(form);
		return null != list && list.size() > 0 ? true:false;
	}
}

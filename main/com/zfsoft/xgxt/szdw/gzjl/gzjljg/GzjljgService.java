/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:32:43 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjljg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ResourceUtils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;

import com.zfsoft.xgxt.szdw.gzjl.lbgl.GzjlLbglDao;

import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:32:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjljgService extends SuperServiceImpl<GzjljgForm, GzjljgDao> {
	/**
	 * 
	 * @����:��ȡ��ʦ������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-25 ����05:25:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJsjbxx(String zgh){

		if (StringUtil.isNull(zgh)) {
			logger.error("zgh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getJsjbxx(zgh);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}
	public boolean checkExistForSave(GzjljgForm model){
		return dao.checkExistForSave(model);
	}
	/**
	 * 
	 * @����:������¼�������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����12:00:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savegzjljg(GzjljgForm model) throws Exception{
		boolean flag=false;
		if("save".equals(model.getType())){
			flag=dao.runInsert(model);
		}else{
			flag=dao.runUpdate(model);
		}
		return flag;
	}
	
	public List<HashMap<String,String>> getJsxxList(GzjljgForm model,User user) throws Exception{
		return dao.getJsxxList(model,user);
	}
	/**
	 * 
	 * @����:������¼ͳ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-2 ����04:26:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param exporModel
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGzjltjList(GzjljgForm exporModel,User user) throws Exception{
		List<HashMap<String, String>> lblist= new GzjlLbglDao().getGzjllbList();
		return dao.getGzjltjList(exporModel,lblist,user);
	}
	/**
	 * 
	 * @����:excel����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-2 ����04:27:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public File gzjltjExport(List<HashMap<String,String>> resultList, OutputStream os, User user,String type,GzjljgForm exporModel) throws Exception {
		File file = createFile();
		FileOutputStream outputStream = new FileOutputStream(file);
		List<HashMap<String, String>> lblist= new GzjlLbglDao().getGzjllbList();
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		String title = null;//������
		String lkr = null;//�����
		int xys=0;//ѧԺ��
		int xys_one=0;
		int k=0;
		int avg1=0;
		int avg2=0;
		int avg3=0;
		int avg4=0;
		int avg5=0;
		int avg6=0;
		int avg7=0;
		int avg8=0;
		int avg9=0;
		int avg10=0;
		int avg11=0;
		
		String kssj = null==exporModel.getSearchModel().getSearch_tj_kssj()?"":exporModel.getSearchModel().getSearch_tj_kssj()[0];
		String jssj = null==exporModel.getSearchModel().getSearch_tj_jssj()?"":exporModel.getSearchModel().getSearch_tj_jssj()[0];
		kssj=DateTranCnDate.fomartDateToCn(kssj,FomartDateType.day);
		jssj=DateTranCnDate.fomartDateToCn(jssj,FomartDateType.day);
		String xymc = dao.getXymc(user.getUserDep());
		
		if("js".equals(type)){
			title=user.getRealName()+kssj+"��"+jssj+"���Ÿ�һ�� �����������������ܱ�";
			lkr="����ǩ����";
		}else if("xy".equals(type)){
			title=Base.xxmc+xymc+kssj+"��"+jssj+"����Ա�������Σ����Ÿ�һ�� �����������������ܱ�";
			lkr=xymc+"ѧ�������칫�ң����£�";
		}else{
			title=Base.xxmc+kssj+"��"+jssj+"����Ա�������Σ����Ÿ�һ�� �����������������ܱ�";
			lkr="��ίѧ������ѧ�����������£�";
			
		}
		
		WritableSheet ws = wwb.createSheet("�����������������ܱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 11, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, title, 0, 0, 11, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ws.mergeCells(0, 0, 12, 0);
		ws.mergeCells(1, 1, 1, 1);
		ws.mergeCells(2, 1, 2, 1);
		ws.mergeCells(3, 1, 3, 1);
		ws.mergeCells(4, 1, 4, 1);
		ws.mergeCells(5, 1, 5, 1);
		ws.mergeCells(6, 1, 6, 1);
		ws.mergeCells(7, 1, 7, 1);
		ws.mergeCells(8, 1, 8, 1);
		ws.mergeCells(9, 1, 9, 1);
		ws.mergeCells(10, 1, 10, 1);
		ws.mergeCells(11, 1, 11, 1);
		
		if("xx".equals(type)){
			ws.addCell(new Label(0, 1, "����", wcf2));
			ws.addCell(new Label(1, 1, "ѧԺ", wcf2));
			ws.addCell(new Label(2, 1, "�������1(������", wcf2));
			ws.addCell(new Label(3, 1, "�������2(������", wcf2));
			ws.addCell(new Label(4, 1, "�������3(������", wcf2));
			ws.addCell(new Label(5, 1, "�������4(������", wcf2));
			ws.addCell(new Label(6, 1, "�������5(������", wcf2));
			ws.addCell(new Label(7, 1, "�������6(������", wcf2));
			ws.addCell(new Label(8, 1, "�������7(������", wcf2));
			ws.addCell(new Label(9, 1, "�������8(������", wcf2));
			ws.addCell(new Label(10, 1, "�������9(������", wcf2));
			ws.addCell(new Label(11, 1, "�������10(������", wcf2));
			ws.addCell(new Label(12, 1, "�������11(������", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 15);
					ws.setColumnView(2, 10);
					ws.setColumnView(3, 10);
					ws.setColumnView(4, 10);
					ws.setColumnView(5, 10);
					ws.setColumnView(6, 10);
					ws.setColumnView(7, 10);
					ws.setColumnView(8, 10);
					ws.setColumnView(9, 10);
					ws.setColumnView(10, 10);
					ws.setColumnView(11, 10);
					ws.setColumnView(12, 10);
					if(i!=0&&!map.get("xymc").equals(resultList.get(i-1).get("xymc"))){
						xys_one=0;
						xys++;
						ws.mergeCells(0,2 + i+k, 1, 2 + i+k);
						ex.printToOneCell_multy(ws, "ƽ������ͳ�Ʒ���", 0, 2 + i+k, 11, false,
								Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
								Border.ALL);
						ws.addCell(new Label(2, 2 + i+k, String.valueOf(Math.round((float)avg1/i)), wcf2));
						ws.addCell(new Label(3, 2 + i+k, String.valueOf(Math.round((float)avg2/i)), wcf2));
						ws.addCell(new Label(4, 2 + i+k, String.valueOf(Math.round((float)avg3/i)), wcf2));
						ws.addCell(new Label(5, 2 + i+k, String.valueOf(Math.round((float)avg4/i)), wcf2));
						ws.addCell(new Label(6, 2 + i+k, String.valueOf(Math.round((float)avg5/i)), wcf2));
						ws.addCell(new Label(7, 2 + i+k, String.valueOf(Math.round((float)avg6/i)), wcf2));
						ws.addCell(new Label(8, 2 + i+k, String.valueOf(Math.round((float)avg7/i)), wcf2));
						ws.addCell(new Label(9, 2 + i+k, String.valueOf(Math.round((float)avg8/i)), wcf2));
						ws.addCell(new Label(10, 2 + i+k, String.valueOf(Math.round((float)avg9/i)), wcf2));
						ws.addCell(new Label(11, 2 + i+k, String.valueOf(Math.round((float)avg10/i)), wcf2));
						ws.addCell(new Label(12, 2 + i+k, String.valueOf(Math.round((float)avg11/i)), wcf2));
						avg1=0;
						 avg2=0;
						 avg3=0;
						 avg4=0;
						 avg5=0;
						 avg6=0;
						 avg7=0;
						 avg8=0;
						 avg9=0;
						 avg10=0;
						 avg11=0;
						k++;
					}
					ws.addCell(new Label(0, 2 + i+k, map.get("xm"), wcf2));
					ws.addCell(new Label(1, 2 + i+k, null==map.get("xymc")?"":map.get("xymc"), wcf2));
					ws.addCell(new Label(2, 2 + i+k, null==map.get("gzlb0")?"":map.get("gzlb0"), wcf2));
					ws.addCell(new Label(3, 2 + i+k, null==map.get("gzlb1")?"":map.get("gzlb1"), wcf2));
					ws.addCell(new Label(4, 2 + i+k, null==map.get("gzlb2")?"":map.get("gzlb2"), wcf2));
					ws.addCell(new Label(5, 2 + i+k, null==map.get("gzlb3")?"":map.get("gzlb3"), wcf2));
					ws.addCell(new Label(6, 2 + i+k, null==map.get("gzlb4")?"":map.get("gzlb4"), wcf2));
					ws.addCell(new Label(7, 2 + i+k, null==map.get("gzlb5")?"":map.get("gzlb5"), wcf2));
					ws.addCell(new Label(8, 2 + i+k, null==map.get("gzlb6")?"":map.get("gzlb6"), wcf2));
					ws.addCell(new Label(9, 2 + i+k, null==map.get("gzlb7")?"":map.get("gzlb7"), wcf2));
					ws.addCell(new Label(10, 2 + i+k, null==map.get("gzlb8")?"":map.get("gzlb8"), wcf2));
					ws.addCell(new Label(11, 2 + i+k, null==map.get("gzlb9")?"":map.get("gzlb9"), wcf2));
					ws.addCell(new Label(12, 2 + i+k, null==map.get("gzlb10")?"":map.get("gzlb10"), wcf2));
					avg1+=Integer.parseInt(null==map.get("gzlb0")?"0":map.get("gzlb0"));
					avg2+=Integer.parseInt(null==map.get("gzlb1")?"0":map.get("gzlb1"));
					avg3+=Integer.parseInt(null==map.get("gzlb2")?"0":map.get("gzlb2"));
					avg4+=Integer.parseInt(null==map.get("gzlb3")?"0":map.get("gzlb3"));
					avg5+=Integer.parseInt(null==map.get("gzlb4")?"0":map.get("gzlb4"));
					avg6+=Integer.parseInt(null==map.get("gzlb5")?"0":map.get("gzlb5"));
					avg7+=Integer.parseInt(null==map.get("gzlb6")?"0":map.get("gzlb6"));
					avg8+=Integer.parseInt(null==map.get("gzlb7")?"0":map.get("gzlb7"));
					avg9+=Integer.parseInt(null==map.get("gzlb8")?"0":map.get("gzlb8"));
					avg10+=Integer.parseInt(null==map.get("gzlb9")?"0":map.get("gzlb9"));
					avg11+=Integer.parseInt(null==map.get("gzlb10")?"0":map.get("gzlb10"));
					xys_one++;
					
				}
				ws.mergeCells(0,2 + resultList.size()+k, 1, 2 + resultList.size()+k);
				ex.printToOneCell_multy(ws, "ƽ������ͳ�Ʒ���", 0, 2 + resultList.size()+k, 11, false,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);
				ws.addCell(new Label(2, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg1/xys_one)), wcf2));
				ws.addCell(new Label(3, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg2/xys_one)), wcf2));
				ws.addCell(new Label(4, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg3/xys_one)), wcf2));
				ws.addCell(new Label(5, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg4/xys_one)), wcf2));
				ws.addCell(new Label(6, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg5/xys_one)), wcf2));
				ws.addCell(new Label(7, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg6/xys_one)), wcf2));
				ws.addCell(new Label(8, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg7/xys_one)), wcf2));
				ws.addCell(new Label(9, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg8/xys_one)), wcf2));
				ws.addCell(new Label(10, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg9/xys_one)), wcf2));
				ws.addCell(new Label(11, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg10/xys_one)), wcf2));
				ws.addCell(new Label(12, 2 + resultList.size()+k, String.valueOf(Math.round((float)avg11/xys_one)), wcf2));
			}
		}else{
		ws.addCell(new Label(0, 1, "����", wcf2));
		ws.addCell(new Label(1, 1, "�������1(������", wcf2));
		ws.addCell(new Label(2, 1, "�������2(������", wcf2));
		ws.addCell(new Label(3, 1, "�������3(������", wcf2));
		ws.addCell(new Label(4, 1, "�������4(������", wcf2));
		ws.addCell(new Label(5, 1, "�������5(������", wcf2));
		ws.addCell(new Label(6, 1, "�������6(������", wcf2));
		ws.addCell(new Label(7, 1, "�������7(������", wcf2));
		ws.addCell(new Label(8, 1, "�������8(������", wcf2));
		ws.addCell(new Label(9, 1, "�������9(������", wcf2));
		ws.addCell(new Label(10, 1, "�������10(������", wcf2));
		ws.addCell(new Label(11, 1, "�������11(������", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				
				ws.setColumnView(0, 10);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 10);
				ws.setColumnView(11, 10);
				ws.addCell(new Label(0, 2 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(1, 2 + i, null==map.get("gzlb0")?"":map.get("gzlb0"), wcf2));
				ws.addCell(new Label(2, 2 + i, null==map.get("gzlb1")?"":map.get("gzlb1"), wcf2));
				ws.addCell(new Label(3, 2 + i, null==map.get("gzlb2")?"":map.get("gzlb2"), wcf2));
				ws.addCell(new Label(4, 2 + i, null==map.get("gzlb3")?"":map.get("gzlb3"), wcf2));
				ws.addCell(new Label(5, 2 + i, null==map.get("gzlb4")?"":map.get("gzlb4"), wcf2));
				ws.addCell(new Label(6, 2 + i, null==map.get("gzlb5")?"":map.get("gzlb5"), wcf2));
				ws.addCell(new Label(7, 2 + i, null==map.get("gzlb6")?"":map.get("gzlb6"), wcf2));
				ws.addCell(new Label(8, 2 + i, null==map.get("gzlb7")?"":map.get("gzlb7"), wcf2));
				ws.addCell(new Label(9, 2 + i, null==map.get("gzlb8")?"":map.get("gzlb8"), wcf2));
				ws.addCell(new Label(10, 2 + i, null==map.get("gzlb9")?"":map.get("gzlb9"), wcf2));
				ws.addCell(new Label(11, 2 + i, null==map.get("gzlb10")?"":map.get("gzlb10"), wcf2));
			}
		}
		}
		ws.mergeCells(0,5+k+ resultList.size(), 4, 5+k+ resultList.size());
		ex.printToOneCell_multy(ws, "�������ע:", 0, 5+k+ resultList.size(), 11, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		for (int i = 0; i < lblist.size(); i++) {
			ws.mergeCells(0,6+i+k+ resultList.size(), 4, 6+i+k+ resultList.size());
			ex.printToOneCell_multy(ws, i+1+"��"+lblist.get(i).get("gzlbmc"), 0, 6+i+k+ resultList.size(), 11, false,
					Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
					Border.NONE);
		}
		ws.mergeCells(9, resultList.size()+4+k, 12, resultList.size()+4+k);
		ws.mergeCells(9, resultList.size()+3+k, 12, resultList.size()+3+k);
		ex.printToOneCell_multy(ws, lkr, 9, resultList.size()+k+3, 11, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, false, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, GetTime.getNowTime(), 9, resultList.size()+4+k, 11, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return file;
	}
	
	public File print(GzjljgForm myForm) throws Exception{
		GzjljgForm jljgForm=dao.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(jljgForm));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rs",myForm);
		return getWord(data,myForm);
	}
	private File getWord(Map<String, Object> data,GzjljgForm myForm) throws FileNotFoundException {
		String templatePath = Constants.TEP_DIR+"szdw/gzjldjb.xml";
		File wordFile = null;
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "szdw", "gzjldjb.xml", FreeMarkerUtil.getFileName(myForm.getZgh() +"["+myForm.getXm()+"]"));
			
		}catch (Exception e) {
			
			
		}

		return wordFile;
	
	}
	private File createFile() {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// ���������ļ�
		File file = new File(tempDir.getPath() + "/"
				+ "��������" + ".xls");
		file.setWritable(true);
		return file;
	}
	
	/** 
	 * @����:�õ�̸������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-18 ����10:47:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getThdxList(String[] xhArr) {
		return dao.getThdxList(xhArr);		
	}
	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-4-19 ����01:49:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZghList(GzjljgForm model, User user) throws Exception {
		// TODO �Զ����ɷ������
		return dao.getZghList(model, user);
	}
	/**
	 * @throws Exception  
	 * @����:����ְ���Ż�ȡ����ʱ���ڵĹ�����¼���(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�982]
	 * @���ڣ�2018-4-19 ����02:14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param searchModel
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGzjlListByZgh(String zgh,String lbmc,
			SearchModel searchModel, User user) throws Exception {
		return dao.getGzjlListByZgh(zgh,lbmc,searchModel, user);
	}
	


}

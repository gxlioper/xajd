/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Boolean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhForm;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

import javax.servlet.ServletOutputStream;

/**
 * @className	�� HdbljgService
 * @description	�� TODO(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-22 ����09:55:30
 * @version 	V1.0 
 */

public class HdbljgService extends SuperServiceImpl<HdbljgForm, HdbljgDao> {
	private ZdydrService zdydrService = new ZdydrService();
	/**
	 * @description	������
	 * @author 		��������1282��
	 * @date 		��2018-1-22 ����11:07:17
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runInsert(HdbljgForm model) throws Exception{
		
		String[] hdbqs = model.getHdbqs();/*��ȡ���ǩ*/
		String[] nlbqs = model.getNlbqs();/*��ȡ������ǩ*/
		String jgid = model.getJgid();
		
		boolean result = dao.runInsertNotCommit(model);
		/*������ǩ*/
		if(null != hdbqs && hdbqs.length > 0){			
			result = dao.BatchInsertHdbqx(jgid, hdbqs);
		}
		/*����������ǩ*/
		if(null != nlbqs && nlbqs.length > 0){
			result = dao.BatchInsertNlbqx(jgid, nlbqs);
		}

		return result;
	}
	
	/**
	 * @description	�� ɾ��
	 * @author 		�� ������1282��
	 * @date 		��2018-1-22 ����05:30:25
	 * @param values
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runDeleteJg(String[] values) throws Exception {
		
		boolean result = true;
		/*ɾ�����ǩ������*/
		result = dao.deleteHdbq(values);
		/*ɾ��������ǩ������*/
		result = dao.deleteNlbq(values);
		if(result){
			return dao.deleteHdjg(values);
		}
		return result;
		
	}
	
	/**
	 * @description	�� �޸�
	 * @author 		��������1282��
	 * @date 		��2018-1-23 ����03:23:30
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runUpdate(HdbljgForm model) throws Exception{
		boolean result = true;
		/*ɾ�����ǩ������*/
		result = dao.deleteHdbq(new String[]{model.getJgid()});
		/*ɾ��������ǩ������*/
		result = dao.deleteNlbq(new String[]{model.getJgid()});
		if(result){
			String[] hdbqs = model.getHdbqs();/*��ȡ���ǩ*/
			String[] nlbqs = model.getNlbqs();/*��ȡ������ǩ*/
			String jgid = model.getJgid();
			if(hdbqs != null && hdbqs.length>0){
				/*������ǩ*/
				result = dao.BatchInsertHdbqx(jgid, hdbqs);
			}
			if(nlbqs != null && nlbqs.length>0){
				/*����������ǩ*/
				result = dao.BatchInsertNlbqx(jgid, nlbqs);
			}
			if(result){
				return dao.runUpdate(model);
			}
		}
		return result;
	}
	
	/**
	 * @description	�� ��������id��ȡ���id
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����03:18:17
	 * @param sqid
	 * @return
	 */
	public String getJgidBySqid(String sqid){
		return dao.getJgidBySqid(sqid);
	}
	
	/**
	 * @description	�� ��ȡmodel
	 * @author 		��������1282��
	 * @date 		��2018-1-23 ����04:14:05
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public HdbljgForm getModelForJg(HdbljgForm model) throws Exception{
		HdbljgForm model2 = getModel(model);
		if(null==model2){
			model2 = new HdbljgForm();
		}
		if(StringUtils.isNotNull(model.getXh())){ //ֻ��������¼ʱ�����вŻ�Я��xh
			model2.setXh(model.getXh());
		}
		HashMap<String, String> modelInfo = dao.getModelInfo(model);
		if(null!=modelInfo){
			if(StringUtils.isNotNull(model.getLy())){
				HdblsqshService service = new HdblsqshService();
				HashMap<String,String> map = service.gethdInfo(model.getHdid());
				map.remove("fjpath");//���滻ԭ�и���
				modelInfo.putAll(map);
			}
			BeanUtils.copyProperties(model2, StringUtils.formatData(modelInfo));
		}
		/*ѧ������*/
		if(StringUtils.isNotNull(modelInfo.get("xqmc"))){
			model2.setXqmc(modelInfo.get("xqmc"));
		}
		/*���������*/
		if(StringUtils.isNotNull(modelInfo.get("hdlxmc"))){
			model2.setHdlxmc(modelInfo.get("hdlxmc"));
		}
		/*������������*/
		if(StringUtils.isNotNull(modelInfo.get("jzlxmc"))){
			model2.setJzlxmc(modelInfo.get("jzlxmc"));
		}
		if(StringUtils.isNotNull(modelInfo.get("zxkclxmc"))){
			model2.setZxkclxmc(modelInfo.get("zxkclxmc"));
		}
		/*���ǩ*/
		if(StringUtils.isNotNull(modelInfo.get("hdbq"))){
			model2.setHdbq(modelInfo.get("hdbq"));
			model2.setHdbqs(modelInfo.get("hdbq").split(","));
			model2.setHdbqmc(modelInfo.get("hdbqmc"));
		}
		/*������ǩ*/
		if(StringUtils.isNotNull(modelInfo.get("nlbq"))){
			model2.setNlbq(modelInfo.get("nlbq"));
			model2.setNlbqs(modelInfo.get("nlbq").split(","));
			model2.setNlbqmc(modelInfo.get("nlbqmc"));
		}
		return model2;
	}
	
	/**
	 * @����: ��ȡ���ǩ�б�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-6-6 ����10:12:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getHdbqList(){
		return dao.getHdbqList();
	}
	
	/**
	 * @����: ��ȡ���������б�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-6-6 ����10:12:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJzlxList(){
		return dao.getJzlxList();
	}
	public List<HashMap<String,String>> getZxkcDmList(){
		return dao.getZxkcDmList();
	}
	/**
	 * @����: ��ȡ������ǩ�б�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-6-6 ����10:12:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAbilityLabelList(){
		return dao.getAbilityLabelList();
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
	public void createWwb(ServletOutputStream outputStream, String drmkdm) {

		// ���ļ�
		WritableWorkbook book = null;
		try {
			//��ȡ�������д��excel��������ע
			List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

			book = Workbook.createWorkbook(outputStream);
			// ���ɵ��������0��ʾsheet1��imporΪ������
			WritableSheet sheet1 = book.createSheet("import", 0);

			//��䵼���м���ע��ʾ�������
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
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
	public HashMap<String,Object> saveImport(InputStream inputStream, String path, String drmkdm) throws Exception {

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

				//����ǰ������������֤ error:02
				resultMap = zdydrService.checkExcelDataList(excelDataList,drgzxxList,new String(),new ArrayList<HashMap<String, Object>>());
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
	private boolean insertDataIntoDB(List<HashMap<String, String>> excelDataList) throws Exception {

		List<String []> paraList = new ArrayList<String[]>();
		boolean result = false;
		for(HashMap<String,String> excelData:excelDataList){
			HdbljgForm hdbljgForm = new HdbljgForm();
			hdbljgForm.setXh(excelData.get("xh"));
			hdbljgForm.setXn(excelData.get("xn"));
			hdbljgForm.setXq(excelData.get("xq"));
			hdbljgForm.setHdmc(excelData.get("hdmc"));
			hdbljgForm.setHdsj(excelData.get("hdsj"));
			hdbljgForm.setHdxs(excelData.get("hdxs"));
			hdbljgForm.setHdlx(excelData.get("hdlx"));
			hdbljgForm.setHddd(excelData.get("hddd"));
			hdbljgForm.setCjlx(excelData.get("cjlx"));
			hdbljgForm.setZdzw(excelData.get("zdzw"));
			hdbljgForm.setHdzw(excelData.get("hdzw"));
			hdbljgForm.setHdjx(excelData.get("hdjx"));
			hdbljgForm.setHdxf(excelData.get("hdxf"));
			hdbljgForm.setJzlx(excelData.get("jzlx"));
			hdbljgForm.setXsxxlx(excelData.get("xsxxlx"));
			hdbljgForm.setHdkclx(excelData.get("hdkclx"));
			hdbljgForm.setBz(excelData.get("bz"));
			hdbljgForm.setZbf(excelData.get("zbf"));
			hdbljgForm.setZjrxm(excelData.get("zjrxm"));
			hdbljgForm.setZjrdw(excelData.get("zjrdw"));
			hdbljgForm.setZjrzc(excelData.get("zjrzc"));
			hdbljgForm.setZjrzw(excelData.get("zjrzw"));
			hdbljgForm.setZjrjs(excelData.get("zjrjs"));
			hdbljgForm.setJzjb(excelData.get("jzjb"));
			hdbljgForm.setZyxss(excelData.get("zyxxs"));
			hdbljgForm.setZjrzw(excelData.get("zjrzw"));
			hdbljgForm.setJgid(UniqID.getInstance().getUniqIDHash());
			String hdbq = excelData.get("hdbq");
			if(!StringUtil.isNull(hdbq)){
				hdbljgForm.setHdbqs(hdbq.split(","));
			}else {
				hdbljgForm.setHdbqs(new String[]{});
			}
			String nlbq = excelData.get("nlbq");
			if(!StringUtil.isNull(nlbq)){
				hdbljgForm.setNlbqs(nlbq.split(","));
			}else {
				hdbljgForm.setNlbqs(new String[]{});
			}
			result = insert(hdbljgForm);
		}
		return result;
	}

	@TransactionControl
	public boolean insert(HdbljgForm model) throws Exception{

		String[] hdbqs = model.getHdbqs();/*��ȡ���ǩ*/
		String[] nlbqs = model.getNlbqs();/*��ȡ������ǩ*/
		String jgid = model.getJgid();

		boolean result = dao.runInsert(model);
		/*������ǩ*/
		if(null != hdbqs && hdbqs.length > 0){
			result = dao.BatchInsertHdbqx(jgid, hdbqs);
		}
		/*����������ǩ*/
		if(null != nlbqs && nlbqs.length > 0){
			result = dao.BatchInsertNlbqx(jgid, nlbqs);
		}

		return result;
	}
}

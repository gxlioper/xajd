/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-11 ����09:09:33 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.dmwh.DmwhDao;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgForm;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧϰ���۹���ģ��
 * @�๦������: ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-11 ����09:09:20 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjsqService extends SuperServiceImpl<XspjsqForm,XspjsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @����: ���ѧ���꼶�������ѧ���û��Ļ��������2017����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-11 ����10:54:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean getCheckStuNj(User user) throws Exception{
		boolean flag = false;
		if("stu".equals(user.getUserType())){
			flag = dao.getCheckStuNj(user.getUserName());
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @����: ��ȡ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-11 ����11:06:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sb
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSplcForParam(String sb){
		return dao.getSplcForParam(sb);
	}
	
	
	/**
	 * @����: ��ȡ��ѧ����ϢList
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-12 ����07:25:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDxqInfoList(){
		return dao.getDxqInfoList();
	}
	
	/**
	 * @����: ���뱣��(����ݸ塢�ύ����)
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-12 ����05:45:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveFormXspjsq(XspjsqForm model,User user) throws Exception{
		
		boolean rs = true;
		
		/*����Ψһ��ʶ��*/
		String sqid = UniqID.getInstance().getUniqIDHash();
		/*ȡ��������*/
		String splcid = this.getSplcForParam("sb").get("splc");
		
		/*�ж�Ψһ����ѧ��(xh),��Ŀ����(xmmc),����ʱ��(cysj)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*�жϲ�����(ѧ��)�Ƿ����xsxxb*/
		if(!dao.checkXh(model.getXh())){
			throw new SystemException(MessageKey.PJPY_CPFWH_XHNULL);
		}
		
		/*��ǰ������Ա�û�������*/
		model.setSjlrr(user.getUserName());
		
		/*��ȡ��ǰ����ʱ��������У���ϲ����˹��ŷ�ֹ��ʦˣ��*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*�����������״̬����*/
		if("save".equals(model.getSaveFlag())){
			/*��� ����ݸ� ��ť���״̬Ϊδ�ύ��0��*/
			model.setShzt(Constants.YW_WTJ);
		}else{
			/*��� �ύ���� ��ť���״̬Ϊ����С�5��*/
			model.setShzt(Constants.YW_SHZ);
		}
		
		/*�жϸ������Ƿ�Ϊ�޸�����*/
		if(StringUtils.isNotNull(model.getSqid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*����ΨһID*/
			model.setSqid(sqid);
			model.setSplcid(splcid);
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		
		/*�����ύʱ����sqid,�������̡���������.do�����������*/
		if("submit".equals(model.getSaveFlag())){
			rs = shlc.runSubmit(model.getSqid(),splcid,model.getXh(), "xspj_xspj_xspjsh.do", "xspj_xspj_xspjsq.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @����: �ж�Ψһ����ѧ��(xh),��Ŀ����(xmmc),����ʱ��(cysj)
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-12 ����05:58:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjsqForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @����: ɾ��ʱ�������״̬���е���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����10:36:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		return dao.delShztbData(sqids);
	}
	
	/**
	 * @����: ��������еļ�¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����11:33:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @����: ����sqid��ȡ�����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����02:13:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr)throws Exception{
		return dao.getInfoBySqid(sqidArr);
	}
	
	/**
	 * @����: �����ύ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����02:15:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param splc
	 * @param fzr
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean plSubmit(String sqid,String splcid,String xh) throws Exception{
		boolean flag = false;
		XspjsqForm model = new XspjsqForm();
		model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splcid);
		model.setSqid(sqid);
		flag = dao.runUpdate(model);
		if(flag){
			shlc.runSubmit(sqid,splcid,xh, "xspj_xspj_xspjsh.do", "xspj_xspj_xspjsq.do");
		}
		return flag;
	}
	
	/**
	 * @����: ����ָ�����Ŵ���ȡ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����04:48:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdbmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBmmcByZdbmdm(String zdbmdm){
		return dao.getBmmcByZdbmdm(zdbmdm);
	}
	
	/**
	 * @����: ��������ID���ѧ��������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-16 ����03:16:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid) throws Exception{
		return dao.getInfoBySqid(sqid);
	}
	
	/**
	 * @����: ���ص���ģ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-22 ����08:57:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void createWwb(OutputStream os) throws Exception{
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		/**sheet1*/
		WritableSheet ws = wwb.createSheet("���ݵ���ģ��", 0);
		
		/**���ñ��̧ͷ�ĸ�ʽ*/
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("����"),10,WritableFont.NO_BOLD );
		/**��Ԫ�������ʽд��*/
		WritableCellFormat body = new WritableCellFormat(bodyFont);
		/**���õ�Ԫ�����*/
		/*ˮƽ����*/
		body.setAlignment(jxl.format.Alignment.CENTRE);
		/*��ֱ����*/
		body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		
		/**�����п�*/
		ws.setColumnView(0, 16);/*��Ŀ����*/
		ws.setColumnView(1, 16);/*ָ������*/
		ws.setColumnView(2, 16);/*��Ŀ���*/
		ws.setColumnView(3, 34);/*����ʱ��*/
		ws.setColumnView(4, 14);/*ѧ��*/
		ws.setColumnView(5, 28);/*ѧ��*/
		ws.setColumnView(6, 24);/*��ѧ��*/
		ws.setColumnView(7, 12);/*��ֵ*/
		ws.setColumnView(8, 12);/*������*/
		ws.setColumnView(9, 18);/*��������ϵ��ʽ*/
		ws.setColumnView(10, 14);/*ָ����ʦ*/
		ws.setColumnView(11, 20);/*ָ����ʦ��ϵ��ʽ*/
		ws.setColumnView(12, 24);/*��������*/
		
		
		/*��һ��̧ͷ*/
		Label row1col1= new Label(0, 0, "��Ŀ����(����)",body);
		Label row1col2= new Label(1, 0, "ָ������(����)",body);
		Label row1col3= new Label(2, 0, "��Ŀ���(����)",body);
		Label row1col4= new Label(3, 0, "����ʱ��(����),���磺2018-01-01",body);
		Label row1col5= new Label(4, 0, "ѧ��(����)",body);
		Label row1col6= new Label(5, 0, "ѧ��(����),���磺2017-2018",body);
		Label row1col7= new Label(6, 0, "��ѧ��(����),���磺��",body);
		Label row1col8= new Label(7, 0, "��ֵ(����)",body);
		Label row1col9= new Label(8, 0, "������",body);
		Label row1col10= new Label(9, 0, "��������ϵ��ʽ",body);
		Label row1col11= new Label(10, 0, "ָ����ʦ",body);
		Label row1col12= new Label(11, 0, "ָ����ʦ��ϵ��ʽ",body);
		Label row1col13= new Label(12, 0, "��������",body);
	    ws.addCell(row1col1);
	    ws.addCell(row1col2);
	    ws.addCell(row1col3);
	    ws.addCell(row1col4);
	    ws.addCell(row1col5);
	    ws.addCell(row1col6);
	    ws.addCell(row1col7);
	    ws.addCell(row1col8);
	    ws.addCell(row1col9);
	    ws.addCell(row1col10);
	    ws.addCell(row1col11);
	    ws.addCell(row1col12);
	    ws.addCell(row1col13);
	    
	    /**sheet2����Ŀ�����ձ�*/
	    DmwhDao dmwhDao = new DmwhDao();
	    WritableSheet ws1 = wwb.createSheet("��Ŀ�����ձ�", 1);
	    List<HashMap<String, String>> xmlbList = dmwhDao.getXmlbListByAll();
	    Label ws1row1col1= new Label(0, 0, "��Ŀ�������",body);
	    ws1.addCell(ws1row1col1);
	    for (int i = 0; i < xmlbList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xmlbList.get(i).get("xmlbmc"),body);
	    	 ws1.addCell(tempLabel);
		}
	    
	    /**sheet3��ָ�����Ŷ��ձ�*/
	    WritableSheet ws2 = wwb.createSheet("ָ�����Ŷ��ձ�", 2);
	    List<HashMap<String, String>> xqList = dao.getZdbmList();
	    Label ws2row1col1= new Label(0, 0, "ָ������",body);
	    ws2.addCell(ws2row1col1);
	    for (int i = 0; i < xqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xqList.get(i).get("zdbmmc"),body);
	    	 ws2.addCell(tempLabel);
		}
	    
	    /**sheet4����ѧ�ڴ����*/
	    WritableSheet ws3 = wwb.createSheet("��ѧ�ڶ��ձ�", 3);
	    List<HashMap<String, String>> dxqList = dao.getDxqList();
	    Label ws3row1col1= new Label(0, 0, "��ѧ��",body);
	    ws3.addCell(ws3row1col1);
	    for (int i = 0; i < dxqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, dxqList.get(i).get("dxqmc"),body);
	    	ws3.addCell(tempLabel);
		}
		try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
			 
		 }
	}
	
	/**
	 * @����: ������Ϣ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-22 ����09:46:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param xspjsqForm
	 * @param user
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,XspjsqForm xspjsqForm,User user){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap = this.DrExcelInfoCheck(is, xspjsqForm,user);
			//�ж�excel����Ƿ�Ϊ��
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				this.saveDrDataIntoDb(paralist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),xspjsqForm.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb((List<String[]>) resultMap.get("drlist"));
				logger.info("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-22 ����09:48:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @����: ������Ϣ��֤
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-22 ����10:09:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param xspjsqForm
	 * @param user
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,XspjsqForm xspjsqForm,User user){
		//�����¼����
		List<String[]> drlist = new ArrayList<String[]>();
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>();
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		
		Workbook rwb = null;
		try{
			rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0);
			/*�õ����е���*/
		    int clos = rs.getColumns();
		    /*�õ����е���*/
	        int rows = rs.getRows();
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){//��֤������Ƿ����ظ�����(��Ŀ���ơ�����ʱ�䡢ѧ�Ų����ظ�)
	        	resultMap.put("result", "excelrepeat");
	        }else{
	        	for(int i = 1; i < rows; i++){
	        		/*ȡ��ÿ����֤���ݣ�����lsmap*/
	        		HashMap<String,String> lsmap = new HashMap<String,String>();
	        		String xmmc = rs.getCell(0, i).getContents();
	        		String zdbmmc = rs.getCell(1, i).getContents();
	        		String xmlbmc = rs.getCell(2, i).getContents();
	        		String cysj = rs.getCell(3, i).getContents();
	        		String xh = rs.getCell(4, i).getContents();
	        		String xn = rs.getCell(5, i).getContents();
	        		String dxqmc = rs.getCell(6, i).getContents();
	        		String fz = rs.getCell(7, i).getContents();
	        		String fzrxm = rs.getCell(8, i).getContents();
	        		String fzrlxfs = rs.getCell(9, i).getContents();
	        		String zdlsxm = rs.getCell(10, i).getContents();
	        		String zdlslxfs = rs.getCell(11, i).getContents();
	        		String sqly = rs.getCell(12, i).getContents();
	        		lsmap.put("xmmc", xmmc);
	        		lsmap.put("zdbmmc", zdbmmc);
	        		lsmap.put("xmlbmc", xmlbmc);
	        		lsmap.put("cysj", cysj);
	        		lsmap.put("xh", xh);
	        		lsmap.put("xn", xn);
	        		lsmap.put("dxqmc", dxqmc);
	        		lsmap.put("fz", fz);
	        		lsmap.put("fzrxm", fzrxm);
	        		lsmap.put("fzrlxfs", fzrlxfs);
	        		lsmap.put("zdlsxm", zdlsxm);
	        		lsmap.put("zdlslxfs", zdlslxfs);
	        		lsmap.put("sqly", sqly);
	        		//�����ж�
  	        	    if(this.checkNullRow(lsmap)){
  	        	    	continue;
  	        	    }
  	        	    HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap,xspjsqForm,user);
  	        	    ArrayList<String> paralist = new ArrayList<String>();
  	        	    if("false".equals(resultmap.get("result"))){
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmmc"));
  	        	    	paralist.add(lsmap.get("xmlbmc"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("xh"));
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("dxqmc"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
  	        	    	if(resultmap.get("resultmap") != null ){
  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
  	        				for (Map.Entry<String, String> entry : map.entrySet()){
  	        					if(entry.getKey().indexOf("yz") != -1){
  	        						paralist.add(entry.getValue());
  	        					}
  	        					
  	        				}
  	        			}
  	        			errorlist.add(paralist.toArray(new String[]{}));
  	        			resultMap.put("result", "false");
  	        	    }else{
  	        	    	/*�༭һ��id�����ݲ���*/
  	        			String sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
  	        	    	String splcid = this.getSplcForParam("sb").get("splc");
  	        	    	paralist.add(sqid);
  	        	    	paralist.add(lsmap.get("xh"));
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("dxqdm"));
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmdm"));
  	        	    	paralist.add(lsmap.get("xmlbdm"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
  	        	    	paralist.add(xspjsqForm.getSjlrr());
  	        	    	paralist.add("0");
  	        	    	paralist.add(splcid);
  	        	    	paralist.add(xspjsqForm.getSjlrsj());
  	        	    	drlist.add(paralist.toArray(new String[]{}));
  	        	    }
	        	}
	          resultMap.put("drlist", drlist);
        	  resultMap.put("errorlist", errorlist);
	        }
			
		}catch (BiffException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @����: ��֤������Ƿ�����ظ�����(��Ŀ���ơ�����ʱ�䡢ѧ�Ų����ظ�)
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-22 ����02:53:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rs
	 * @param cols
	 * @param rows
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExcelRepeat(Sheet rs,int cols,int rows){
		ArrayList<String> compareList = new ArrayList<String>();
		boolean flag = true;
		for (int i = 0; i < rows; i++) {
			String xmmc = rs.getCell(0,i).getContents();
			String cysj = rs.getCell(3,i).getContents();
			String xh = rs.getCell(4, i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xmmc)){
				str = str + xmmc.trim();
			}
			if(StringUtils.isNotNull(cysj)){
				str = str + cysj.trim();
			}
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNull(str)){
				continue;
			}
			compareList.add(str);
		}
	    for (int i = 0; i < compareList.size(); i++) {
			for (int j = i+1; j < compareList.size(); j++) {
				if(compareList.get(i).equals(compareList.get(j))){
					flag = false;
					break;
				}
			}
			if(!flag){
				break;
			}
		}
		return flag;
	}
	
	/**
	 * @����: �жϿ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-22 ����02:53:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yzMap
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNullRow(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNotNull(yzMap.get(key))){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @����: ������excel���д�������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-22 ����02:54:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist,String filepath) throws Exception{
		 String fileName = System.currentTimeMillis()+"error.xls";
		 String path = filepath + fileName;
		 File file=new File(path);
         if (!file.exists()) {
            file.createNewFile();
         }
         WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
			// ����������
	        WritableSheet ws = wwb.createSheet("��������", 0);
	       	WritableCellFormat titlefont =  this.getFontStyle("title");
	   		/**
	   		 * sheet1
	   		 */
	   	    Label row1col1= new Label(0, 0, "��Ŀ����",titlefont);
	   	    Label row1col2= new Label(1, 0, "ָ������",titlefont);
	   	    Label row1col3= new Label(2, 0, "��Ŀ���",titlefont);
	   	    Label row1col4= new Label(3, 0, "����ʱ��",titlefont);
	   		Label row1col5= new Label(4, 0, "ѧ��",titlefont);
	   		Label row1col6= new Label(5, 0, "ѧ��",titlefont);
	   		Label row1col7= new Label(6, 0, "��ѧ��",titlefont);
	   		Label row1col8= new Label(7, 0, "��ֵ",titlefont);
	   		Label row1col9= new Label(8, 0, "������",titlefont);
	   		Label row1col10= new Label(9, 0, "��������ϵ��ʽ",titlefont);
	   		Label row1col11= new Label(10, 0, "ָ����ʦ",titlefont);
	   		Label row1col12= new Label(11, 0, "ָ����ʦ��ϵ��ʽ",titlefont);
	   		Label row1col13= new Label(12, 0, "��������",titlefont);
            try {
	           	ws.addCell(row1col1);
	       		ws.addCell(row1col2);
	       		ws.addCell(row1col3);
	       		ws.addCell(row1col4);
	       		ws.addCell(row1col5);
	       		ws.addCell(row1col6);
	       		ws.addCell(row1col7);
	       		ws.addCell(row1col8);
	       		ws.addCell(row1col9);
	       		ws.addCell(row1col10);
	       	    ws.addCell(row1col11);
	       	    ws.addCell(row1col12);
	       	    ws.addCell(row1col13);
			} catch (RowsExceededException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
           
           for (int i = 0; i < errorlist.size(); i++) {
                for(int j = 0;j<errorlist.get(i).length;j++){
	               	 Label labelId_i= null;
	               	 if(j<=12){
	               		  labelId_i= new Label(j, i+1, errorlist.get(i)[j]+"");
	               	 }else{
	               		 WritableCellFormat wcf = new WritableCellFormat();
	        				WritableFont wf = new WritableFont(WritableFont.ARIAL);
	        				try {
								wf.setColour(Colour.RED);
								wcf.setFont(wf);
								wcf.setAlignment(Alignment.CENTRE);
								labelId_i = new Label(j, i+1, errorlist.get(i)[j],wcf);
							} catch (RowsExceededException e) {
								// TODO �Զ����� catch ��
								e.printStackTrace();
							} catch (WriteException e) {
								// TODO �Զ����� catch ��
								e.printStackTrace();
							}
	               	 }
	           		try{
						ws.addCell(labelId_i);
					}catch(RowsExceededException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
					} catch (WriteException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
					}
                }
           }
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	/**
	 * @����: ��֤ÿ�м�¼
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-22 ����03:10:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsmap
	 * @param xspjsqForm
	 * @param user
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap, XspjsqForm xspjsqForm,User user){
		/**
		 * ��֤����
		 * 1����Ŀ���ơ�ָ�����š���Ŀ��𡢲���ʱ�䡢ѧ�š�ѧ�ꡢ��ѧ�ڡ���ֵ����Ϊ��
		 * 2����ָ֤�������Ƿ����
		 * 3����֤��Ŀ����Ƿ����
		 * 4����֤����ʱ���ʽ�Ƿ���ȷ
		 * 5��ѧ���Ƿ����ѧ����Ϣ��
		 * 6����֤��ѧ���Ƿ����
		 * 7��Ψһ����֤
		 */
		String message = "true";
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "��Ŀ���ơ�ָ�����š���Ŀ��𡢲���ʱ�䡢ѧ�š�ѧ�ꡢ��ѧ�ڡ���ֵ����Ϊ�գ�");
		}else{
			//��֤֪�������Ƿ����
			String zdbmdm = dao.checkZdbm(lsmap.get("zdbmmc"));
			if(StringUtils.isNull(zdbmdm)){
				message = "false";
				lsmap.put("zdbmyz", "ָ�����Ų����ڣ�");
			}
			
			//��֤��ѧԺֻ�ܵ�����ѧԺ����
			if(!user.getUserDepName().equals(lsmap.get("zdbmmc"))){
				message = "false";
				lsmap.put("zdbmyz", "ֻ�ܵ��뱾ѧԺ(԰)���ݣ�");
			}
			
			//��֤��Ŀ����Ƿ����
			String xmlbdm = dao.checkXmlb(lsmap.get("xmlbmc"));
			if(StringUtils.isNull(xmlbdm)){
				message = "false";
				lsmap.put("xmlbyz", "��Ŀ��𲻴��ڣ�");
			}
			
			//��֤����ʱ���ʽ�Ƿ���ȷ
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try{
				Date date = (Date)formatter.parse(lsmap.get("cysj"));
				if(!lsmap.get("cysj").equals(formatter.format(date))){
					message = "false";
					lsmap.put("cysjgsyz", "����ʱ���ʽ����Ϊyyyy-MM-dd��");
				}
			}catch (ParseException e){
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			
			//��֤ѧ���Ƿ����ѧ����Ϣ��
			if(!dao.checkXhisTrue(lsmap.get("xh"))){
				message = "false";
				lsmap.put("xsxhyz", "ѧ�Ų����ڻ��пո�");
			}
			
			//��֤��ѧ���Ƿ����
			String dxqdm = dao.checkDxq(lsmap.get("dxqmc"));
			if(StringUtils.isNull(dxqdm)){
				message = "false";
				lsmap.put("dxqyz", "��ѧ�ڲ����ڣ�");
			}
			
			//������֤
			if(!dao.checkIsNotExists(lsmap.get("xmmc"), lsmap.get("cysj"), lsmap.get("xh"), null)){
				message = "false";
				lsmap.put("zjyz", "ͬһ��ѧ����ͬһʱ���걨������Ŀ����ȷ�ϣ�");
			}
			
			//��ע��֤
			if(lsmap.get("sqly").trim().length() > 200){
				message = "false";
				lsmap.put("sqlyyz", "��ע���ܳ���200�֣�");
			}
			
			if("true".equals(message)){
				lsmap.put("zdbmdm", zdbmdm);
				lsmap.put("xmlbdm", xmlbdm);
				lsmap.put("dxqdm", dxqdm);
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @����: ��֤�ǿ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-22 ����03:10:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yzMap
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNull(yzMap.get(key)) && !("fzrxm").equals(key) && !("fzrlxfs").equals(key) && !("zdlsxm").equals(key) && !("zdlslxfs").equals(key) && !("sqly").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @����: excel����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-22 ����03:10:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat �������� 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras) throws WriteException{
		
		/** 
         * ���嵥Ԫ����ʽ 
         */ 
		if("title".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);  
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("errorinfo".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.RED);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}
     
        return null;
	}
}

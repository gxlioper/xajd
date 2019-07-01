/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-19 ����04:44:04 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import java.util.HashMap;
import java.util.List;
import java.io.OutputStream;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ������ѯ-��ѯʦ�Ű����(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-8-19 ����04:35:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxspbService extends SuperServiceImpl<ZxspbForm, ZxspbDao> {
	
	private ZxspbDao dao = new ZxspbDao();
	private static final String DATE_FORMAT  = "yyyy-mm-dd hh24:mi:ss";
	public ZxspbService() {
		super.setDao(dao);
	}
	

	/**
	 * ����ְ���Ų�ѯ��ѯʦ�Ű���Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getZxspbInfoByZgh(String zgh){
		
		return dao.getZxspbInfoByZgh(zgh);
	}
	
	/**
	 * �������ڲ�ѯ�Ű����
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoByDate(String date){
		
		return dao.getPbInfoByDate(date);
	}
	/**
	 * ������ѯ��ѯʦ�Ű���Ϣ������չʾ��ʽ��
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getZxspbAsRl(String queryDate) throws Exception{
		
		return dao.getZxspbAsRl(queryDate);
	}
	
	/**
	 * ������ѯ��ѯʦ�Ű���Ϣ������չʾ��ʽ��
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getZxspbAsRlForXs(String xh,String queryDate) throws Exception{
		
		return dao.getZxspbAsRlForXs(xh,queryDate);
	}
	
	/**
	 * �����Ű��Ų�ѯ�Ű���Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoById(String id){
		
		return dao.getPbInfoById(id);
	}
	/**
	 * ��������ѯʦ�Ű��
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveZxspbInfo(ZxspbForm model)
			throws Exception {
		return dao.saveZxspbInfo(model);
	}
	
	/**
	 * �����Ű�
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveBatchZxspbInfo(String[] pbdate,String zgh,String bz)
	throws Exception{
		
		return dao.saveBatchZxspbInfo(pbdate, zgh, bz);
	}
	/**
	 * ɾ����ѯʦ�Ű���Ϣ(��������
	 */
	public int delZxspbById(String id) throws Exception {
		
		return dao.delZxspbById(id);
	}
	
	/**
	 * ɾ����ѯʦ���Ű���Ϣ
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public int delZxspbByZgh(String[] zgh) throws Exception {
		
		return dao.delZxspbByZgh(zgh);
	}
	/**
	 * �޸���ѯʦ���Ű���Ϣ
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean updateZxspbInfo(ZxspbForm model) throws Exception{
		
		return dao.updateZxspbInfo(model);
	}
	/**
	 * �������ڻ�ȡ��Ӧ����
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public 	HashMap<String, String> getWeekdayByDate(String date){
		
		return dao.getWeekdayByDate(date);
	}
	/**
	 * ��ȡ��ֹ�����ڶ�Ӧ��������M������
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDateByWeekBetweenDate(String startDate,String endDate) throws Exception{
		
		return dao.getDateByWeekBetweenDate(startDate, endDate);
	}
	/**
	 * @����: ���ϳ���ѧԺ���Ի�����EXCLE
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-6-29 ����01:53:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void exportConfig_11527(ZxspbForm model,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);//����һ������������������Excel�ĵ�
		WritableSheet ws = wwb.createSheet("Sheet", 0);//����һ��������
		ws.setRowView(0, 850);//��1�и߶�
		ws.setRowView(1, 600);//��2�и߶�
		//1��7�еĿ��
		for (int s = 0; s < 7; s++){ 
			ws.setColumnView(s, 26);
	    }
		//��3���Ժ�ĸ߶�
		for (int d = 2; d < 9; d++){ 
			ws.setRowView(d, 900);
	    }
		
		String dd = model.getDqny();//��һ��˵��Ҫ�ĵ������·�
		
		// ��1��
		WritableCellFormat r1Format = new WritableCellFormat();
		WritableFont r1Font = new WritableFont(WritableFont.createFont("����"),18);//����Ϊ���壬�ֺ�18��
		r1Format.setFont(r1Font);//������awt�е����������������, �����Ը�����ɫ,�ֺŻ�Ӵ�б���
		r1Font.setBoldStyle(WritableFont.BOLD);//��������Ϊ�����壩
		r1Format.setAlignment(Alignment.CENTRE);//���ݾ���
		ws.mergeCells(0, 0, 6, 0);//�ϲ���һ�е�һ�е������е�һ��
		ws.addCell(new Label(0, 0, dd , r1Format));//��ʾ����һ���е�����
		
		//���� ��2�еĸ�ʽ
		WritableCellFormat r2Format = new WritableCellFormat();
		WritableFont r2Font = new WritableFont(WritableFont.createFont("����"),12);
		r2Format.setFont(r2Font);
		r2Font.setBoldStyle(WritableFont.BOLD);
		r2Format.setAlignment(Alignment.CENTRE);
		
		//��ʾ�ڶ��е�����
		int rownum = 1;
		ws.addCell(new Label(0, rownum,"����", r2Format));
		ws.addCell(new Label(1, rownum,"��һ", r2Format));
		ws.addCell(new Label(2, rownum,"�ܶ�", r2Format));
		ws.addCell(new Label(3, rownum,"����", r2Format));
		ws.addCell(new Label(4, rownum,"����", r2Format));
		ws.addCell(new Label(5, rownum,"����", r2Format));
		ws.addCell(new Label(6, rownum,"����", r2Format));
		rownum++;
		
		// �ӵ����п�ʼ������ѭ��
		WritableCellFormat r3Format = new WritableCellFormat();
		WritableFont r3Font = new WritableFont(WritableFont.createFont("����"),11);
		r3Format.setWrap(true);//���е������Զ����У�������
		r3Format.setFont(r3Font);
		r3Format.setAlignment(Alignment.CENTRE);
		r3Font.setBoldStyle(WritableFont.NO_BOLD);
		
		String dqny = model.getDqny().replace("��", "-").replace("��", "");//��ȡ��ѡ�񵼳����ꡢ��
		String[] sz = dqny.split("-");
		String y =sz[0];
		String m = sz[1];
		int year =Integer.parseInt(y);
		int month =Integer.parseInt(m);
		int daynumber = DateUtils.getMDay(year, month);
		String strMonth = String.valueOf(month);
		if(month < 10){
		    strMonth = "0" + strMonth;
		}
	    int firstnumber = DateUtils.getDayOfWeek_num(year + strMonth + "01");
	    int lastnumber = DateUtils.getDayOfWeek_num(year + strMonth + daynumber);
	    int weeknumber = (daynumber - (7 - firstnumber) - (lastnumber + 1)) / 7; 
	    int day = 1; 

	    String dateMy = year + "-" + strMonth+ "-";
	    String date = "";
	    String zghInfo = "";
	    for (int i = firstnumber-1; i < 7; i++){
			if(day < 10){
				date  =  dateMy + "0" + day;
			} else {
				date  =  dateMy + day;
			}
			zghInfo = getzghInfo(date);
	    	ws.addCell(new Label(i, rownum ,String.valueOf(day)+zghInfo, r3Format));
	        day++; 
	    }
	    rownum++;
	    for (int i = 0; i < weeknumber; i++){ //�������� 
	        for (int k = 0; k < 7; k++) { 
				if(day < 10){
					date  =  dateMy + "0" + day;
				} else {
					date  =  dateMy + day;
				}
				zghInfo = getzghInfo(date);
	        	ws.addCell(new Label(k, rownum ,String.valueOf(day)+zghInfo, r3Format));
		        day++;
	        } 
	        rownum++;
	    }
	    for (int i = 0; i < lastnumber; i++){
			if(day < 10){
				date  =  dateMy + "0" + day;
			} else {
				date  =  dateMy + day;
			}
			zghInfo = getzghInfo(date);
	    	ws.addCell(new Label(i, rownum ,String.valueOf(day)+zghInfo, r3Format));
	        day++; 
	    }
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
	/**
	 * @����: ��ȡ��ʦ��Ϣ
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-6-29 ����01:52:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	private String getzghInfo(String date) throws Exception{
		List<HashMap<String,String>> zghInfoList= getZghInfoList(date);
		String zghInfo = "";
		HashMap<String,String> zghMap = null;
		for(int i =0;i<zghInfoList.size();i++){
			zghMap = zghInfoList.get(i);
			zghInfo +="\r\n";
			zghInfo += zghMap.get("xm").toString();
			zghInfo += "["+zghMap.get("xb").toString()+"]";
			zghInfo += "["+zghMap.get("bmmc").toString()+"]";
			zghInfo += "["+zghMap.get("zgh").toString()+"]";
		}
		return zghInfo;
	}
	/**
	 * ��Ҫ��getzghInfo����
	 */
	private List<HashMap<String,String>> getZghInfoList(String date) throws Exception{
		ZxsglService zxsglSv = new ZxsglService();
		List<HashMap<String,String>> zghInfoList= new ArrayList<HashMap<String,String>>();
		HashMap<String, String> zxspbInfo = dao.getPbInfoByDate(date);
		if(zxspbInfo!=null && zxspbInfo.size()>0){
			String _zgh = zxspbInfo.get("zgh");
			if(!StringUtil.isNull(_zgh)){
				String[] zgh = _zgh.split(",");
				zghInfoList = zxsglSv.getZxsInfoByZgh(zgh);
			}
		}
		return zghInfoList;
	}
	
	/**
	 * 
	 * @����:��ȡ�Ű෽ʽ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-20 ����10:37:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPbfs(){
		return dao.getPbfs();
	}
	
	/**
	 * 
	 * @����: ��ȡ��ʱ���������ʦ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-20 ����02:11:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pbdate
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxspbList(String pbdate){
		return dao.getZxspbList(pbdate);
	}
	
	/**
	 * 
	 * @����: 
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-20 ����02:33:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pbdate
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxspbListSjd(String pbdate){
		return dao.getZxspbListSjd(pbdate);
	}
	
	/**
	 * 
	 * @����: ��ʱ����Ű��齨��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-20 ����03:05:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pbdate
	 * @return
	 * JSONObject �������� 
	 * @throws
	 */
	public JSONArray createSjdPbInitList(String pbdate){
		List<HashMap<String, String>> pbList = this.getZxspbList(pbdate);
		List<HashMap<String, String>> pbListDetail = this.getZxspbListSjd(pbdate);
		List<HashMap<String, Object>> zhList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < pbList.size(); i++) {
			List<HashMap<String, String>> tempList = new ArrayList<HashMap<String,String>>();
			HashMap<String, String> tempCompareMap = pbList.get(i);
			if(pbListDetail != null && !pbListDetail.isEmpty()){
				for (int j = 0; j < pbListDetail.size(); j++) {
					HashMap<String, String> tempMap = pbListDetail.get(j);
					if(tempCompareMap.get("zgh").equals(tempMap.get("zgh"))){
						tempList.add(tempMap);
					}
				}
			}
			HashMap<String, Object> tempZhMap = new HashMap<String, Object>();
			tempZhMap.putAll(tempCompareMap);
			tempZhMap.put("sjdList", tempList);
			zhList.add(tempZhMap);
		}
		return  JSONArray.fromObject(zhList);
	}
	
	/**
	 * 
	 * @����: ��ȡʱ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-20 ����05:21:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSjdList(){
		return dao.getSjdList();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�����Ű���Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-22 ����02:25:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean savePbxxb(ZxspbForm zxspbform) throws Exception{
		String id = zxspbform.getId();
		String createsj = GetTime.getTimeByFormat(DATE_FORMAT);
		String bz = zxspbform.getBz();
		String[] zghs = zxspbform.getZghs();
		String[] xqdms = zxspbform.getXqdm();
		String[] sjddm = zxspbform.getSjdm();
		String pbdate = zxspbform.getPbdate();
		String sfCopypb = zxspbform.getSfCopyPb();
		String startDate = (StringUtils.isNotNull(sfCopypb)&&"1".equals(sfCopypb))?zxspbform.getStartDate():pbdate;
		String endDate = (StringUtils.isNotNull(sfCopypb) &&"1".equals(sfCopypb))?zxspbform.getEndDate():pbdate;
		List<HashMap<String, String>> pbdateList = dao.getDateByWeekBetweenDate(startDate, endDate);
		boolean rs = true;
		boolean isAdd = StringUtils.isNull(id);
		//���idΪ�գ������Ӳ�������ִ��ɾ������
		if(!dao.checkIsNotExistsInPbb(pbdateList,id)){
			throw new SystemException(MessageKey.XG_XLZX_PB_ADD_REPEAT);
		}
		//��ϲ���
		List<String[]> pbDelList = new ArrayList<String[]>();
		List<String[]> sjdDelList = new ArrayList<String[]>();
		List<String[]> sjdSaveList = new ArrayList<String[]>();
		List<String[]> pbSaveList = new ArrayList<String[]>();
		for (int i = 0; i < pbdateList.size(); i++) {
			String temppbdate = pbdateList.get(i).get("tdate");
			String tempid = (temppbdate.equals(pbdate)&&StringUtils.isNotNull(id)) ? id : UniqID.getInstance().getUniqIDHash().toUpperCase();
			pbDelList.add(new String[]{temppbdate});
			pbSaveList.add(new String[]{tempid,temppbdate,null,createsj,bz});
			for (int j = 0; j < zghs.length; j++) {
				sjdDelList.add(new String[]{zghs[j],tempid});
				if(sjddm != null && sjddm.length > 0 && !"none".equals(sjddm[j])){
				   String temsjdStr = sjddm[j];
					if(StringUtils.isNotNull(temsjdStr)){
						String[] tempSjdArr = temsjdStr.split(";");
						for (int k = 0; k < tempSjdArr.length; k++) {
							//������ҽҩ���Ի��ֶ�У�����룬�Ǳ�����ҽҩ���ֶ�ȡ��
							String tempXqdm = ("10026").equals(Base.xxdm)?xqdms[j]:null;
							if("10026".equals(Base.xxdm)){
								sjdSaveList.add(new String[]{tempid,zghs[j],tempXqdm,tempSjdArr[k]});
							}else{
								sjdSaveList.add(new String[]{tempid,zghs[j],tempXqdm,tempSjdArr[k]});
							}
							
						}
					}
				}
			}
		}
		//�������ݿ�
		rs = this.savePbxxIntoDb(pbDelList, sjdDelList, sjdSaveList, pbSaveList, isAdd);
		return rs;
		
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����Ű���Ϣ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-23 ����10:31:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean savePbxxIntoDb(List<String[]>pbDelList,List<String[]> sjdDelList,
			List<String[]> sjdSaveList,List<String[]> pbSaveList,boolean isAdd) throws Exception{
		//��������Ӳ���������ִ��ɾ������
		boolean rs = true;
		if(!isAdd){
			rs = dao.delPbxx(sjdDelList, pbDelList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		
		if(isAdd && (sjdSaveList == null || sjdSaveList.size() == 0)){
			throw new SystemException(MessageKey.XG_XLZX_ADD_PBDATE_CAN_NOTNULL);//ʱ��α��빴ѡ
		}else if((sjdSaveList != null && sjdSaveList.size() > 0)){
			rs = dao.saveZxsPb(sjdSaveList, pbSaveList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			return rs;
		}
		return rs;
		
		
	}
	
	/**
	 * 
	 * @����: ѧ������ԤԼ��ѯ(��ʱ���)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-24 ����03:57:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param queryDate
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
    public List<HashMap<String, String>> getZxspbAsRlForXsSjd(String xh,String queryDate) throws Exception{
		return dao.getZxspbAsRlForXsSjd(xh, queryDate);
		
	}
	
    /**
     * 
     * @����: ʱ����Ű�
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2017-3-27 ����10:07:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getZxsjbxxForSjdPb(String pbdate){
    	return dao.getZxsjbxxForSjdPb(pbdate);
    }
    
    /**
	 * �������ڲ�ѯ�Ű����[ʱ���ԤԼ]
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoByDateForsjd(String date){
		return dao.getPbInfoByDateForsjd(date);
	}
	
	/**
	 * 
	 * @����:��ȡУ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-31 ����07:06:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXqmc(String date,String zgh){
		return dao.getXqmc(date, zgh);
	}
}
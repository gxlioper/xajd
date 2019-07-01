package xsgzgl.gygl.gyglry;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

import com.ctc.wstx.util.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrForm;

public class GyglryService extends GyglNewService{
	
	private GyglryDao dao=new GyglryDao();
	//����ϵͳ����Ŀ¼
	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	public static final String FILE_EMPTY="�����ļ�Ϊ�գ�";
	public static final String FILE_REPEAT="excel�д����ظ����ݣ�����ϸ��飡";
//	public static final String 
	
//	public String[] getTopTr(GyglryForm model){
//		String[] top=new String[]{"¥��","���","���Һ�","����","�Ա�","ѧ��","����","��ϵ�绰","����绰","��ע"};
//		return top;
//	}
	
	/**
	 * ��ȡ��Ԣ������Ա��Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGyglryList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		return dao.getGyglryList(model,request,user);
	}
	
	/**
	 * ��ȡ��סѧ���б�
	 */
	public List<String[]> getRzxsxxList(GyglryForm model) throws Exception{
		return dao.getRzxsxxList(model);
	}
	
	/**
	 * ��ȡ����б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getChList(GyglryForm model){
		return dao.getChList(model);
	}
	
	/**
	 * ��ȡ���Һ��б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getQshList(GyglryForm model){
		return dao.getQshList(model);
	}
	
	/**
	 * ������Ա����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryFp(GyglryForm model) throws Exception{
		boolean flag = this.fpFullFlag(model);
		return dao.gyglryFp(model,flag);
	}
	
	/**
	 * ��Ԣ������Աȡ������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryQxfp(GyglryForm model) throws Exception{
		return dao.gyglryQxfp(model);
	}
	
	/**
	 * ͬ���ɲ���Ϣ��¥�����㳤�����ҳ���
	 * @return boolean
	 */
	public boolean tbgbInfo(){
		String sql="{call pro_xg_gygl_bgbtb()}";
		return dao.runPro(sql);
	}
	
	/**
	 * ���ҳ�ά���Զ��嵼������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGyglryExportList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		return dao.getGyglryExportList(model,request,user);
	}
	/**
	 * ��ȡ��Ԣ������Ա¥����Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLzInfoList(String lddm) throws Exception{
		
		return dao.getLzInfoList(lddm);
	}
	/**
	 * ��ȡ��Ԣ������Ա�㳤��Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCzInfoList(String lddm,String ch) throws Exception{
		
		return dao.getCzInfoList(lddm,ch);
	}
	
	/**
	 * ������Ա�����Ƿ�ﵽ��trueֻ�ܸ��£�false�ɸ��¿�׷�ӣ�
	 * jQuery -  ����
	 */
	public boolean fpFullFlag(GyglryForm model)
			throws Exception {
		boolean fpFullFlag = false;
		if(Base.isNull(model.getCh()) && Base.isNull(model.getQsh())){//����¥��
			List<HashMap<String, String>> lzInfoList = this.getLzInfoList(model.getLddm());
			if(lzInfoList!=null && lzInfoList.size()>0){
				if(lzInfoList.size()>=Integer.parseInt(GyglNewInit.LZFPNUM)){
					fpFullFlag = true;
				}
			}else{
				fpFullFlag = true;
			}
		}else if(!Base.isNull(model.getCh()) &&  Base.isNull(model.getQsh())){//����㳤
			List<HashMap<String, String>> czInfoList = this.getCzInfoList(model.getLddm(),model.getCh());
			if(czInfoList!=null && czInfoList.size()>0){
				if(czInfoList.size()>=Integer.parseInt(GyglNewInit.LZFPNUM)){
					fpFullFlag = true;
				}
			}else{
				fpFullFlag = true;
			}
		}else if(!Base.isNull(model.getFp_ch()) && !Base.isNull(model.getFp_qsh())){//�������ҳ���ֻ�ܷ���һ�����������ƣ�
			fpFullFlag = true;
		}
		return fpFullFlag;
	}
	
	/** 
	 * @����:��ȡ��Ԣ������Ա���ҳ���Ϣ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-31 ����10:48:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getQszInfo(String lddm,String qsh) {
		return dao.getQszInfo(lddm, qsh);
	}
	
	/**
	 * @throws WriteException 
	 * @throws IOException 
	 * 
	 * @����: ���ص���ģ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-22 ����09:53:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File downloadMb() throws Exception{
		File file = new File(TEMP_DIR +"/xg_gyglydr.xls");
		if(!file.exists()){
			file.createNewFile();
		}
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		createWookBook(wwb);
        wwb.write();
        wwb.close();
		return file;
	}
	
	public void createWookBook(WritableWorkbook wwb) throws RowsExceededException, WriteException{
		  // ����������
	        WritableSheet ws = wwb.createSheet("��Ԣ����Ա����ģ��", 0);
	        Label labelXh= new Label(0, 0, "ѧ��");
	        setComment("������ѧ�ű�����ڣ�",labelXh);
	        Label labelLddm= new Label(1, 0, "¥��");
	        setComment("������¥��������ڣ�",labelLddm);
	        Label labelCh= new Label(2, 0, "���");
	        setComment("�����Ҳ�ű�����ڻ���#��",labelCh);
	        Label labelQsh= new Label(3, 0, "���Һ�");
	        setComment("���������Һű�����ڻ���#��",labelQsh);
//	        Label labelRzzt= new Label(4, 0, "��ְ״̬");
//	        setComment("����,ֻ����д����/���Σ�",labelRzzt);
	        Label labelRzksrq = new Label(4, 0, "��ְ��ʼ����");
	        setComment("�����Ҹ�ʽ����2018-01-01��",labelRzksrq);
//	        Label labelRzjsrq = new Label(5, 0, "��ְ��������");
//	        setComment("��ʽ����2018-01-01��",labelRzjsrq);
	        Label labelBz = new Label(5, 0, "��ע");
	        setComment("��ע���ȱ���С��100��",labelBz);
	        Label labelLxdh = new Label(6, 0, "��ϵ�绰");
	        setComment("��ϵ�绰���ȱ���С��15��",labelLxdh);
	        ws.addCell(labelXh);
	        ws.addCell(labelLddm);
	        ws.addCell(labelCh);
	        ws.addCell(labelQsh);
	        ws.addCell(labelRzksrq);
	        ws.addCell(labelBz);
	        ws.addCell(labelLxdh);
	        //���õ�Ԫ���ı���ʽ
	        WritableCellFormat wcfF = new WritableCellFormat(
	        NumberFormats.TEXT); 
	        CellView cv = new CellView(); 
	        cv.setFormat(wcfF);
	        cv.setSize(10*265);
	        for(int i=0;i<7;i++){
	        	ws.setColumnView(i, cv);
	        }
	}
	
	/**
	 * 
	 * @����: ���ע��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-22 ����11:30:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public void setComment(String content,Label label){
		WritableCellFeatures wcf = new WritableCellFeatures();
		wcf.setComment(content);
		label.setCellFeatures(wcf);
	}
	
	/**
	 * 
	 * @����:���浼������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-22 ����04:27:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public HashMap<String, String> getAndSaveDrData(InputStream is)throws Exception{
		Workbook rwb = null;
		//�����¼����
		List<String[]> drAddlist = new ArrayList<String[]>();
		List<String[]> drUplist = new ArrayList<String[]>();
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>() ;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
		    int cols=rs.getColumns();//�õ����е���
	        int rows=rs.getRows();//�õ����е���
	        isEmptyWookBook(cols,rows);
	        checkRepeatExcel(cols, rows, rs);
	        for (int i = 1; i < rows; i++) {
	        	//ȡ��ÿ����֤���ݣ�����lsmap
	        	HashMap<String, String>  lsmap = new HashMap<String, String>();
	        	String xh = rs.getCell(0, i).getContents();
	        	String ldmc = rs.getCell(1, i).getContents();
	        	String ch = rs.getCell(2,i).getContents();
	        	String qsh = rs.getCell(3,i).getContents();
	        	String rzksrq = rs.getCell(4,i).getContents();
	        	String bz = rs.getCell(5,i).getContents();
	        	String lxdh = rs.getCell(6,i).getContents();
	        	lsmap.put("xh", xh);
	        	lsmap.put("ldmc", ldmc);
	        	lsmap.put("ch", ch);
	        	lsmap.put("qsh", qsh);
	        	lsmap.put("rzksrq", rzksrq);
	        	lsmap.put("bz", bz);
	        	lsmap.put("lxdh", lxdh);
	        	//ȫ��������
	        	if( checkBlankCellAll(lsmap)){
	        		continue;
	        	}
	        	//��֤ÿ������
	        	checkEveryRowRecord(lsmap);
	        	//��֤�Ƿ��д�������
	        	ArrayList<String> paralist = new ArrayList<String>();
	        	ArrayList<String> paraUplist = new ArrayList<String>();
	        	if("false".equals(lsmap.get("message"))){
	        		paralist.add(lsmap.get("xh"));
	        		paralist.add(lsmap.get("ldmc"));
	        		paralist.add(lsmap.get("ch"));
	        		paralist.add(lsmap.get("qsh"));
	        		paralist.add(lsmap.get("rzksrq"));
	        		paralist.add(lsmap.get("bz"));
	        		paralist.add(lsmap.get("lxdh"));
	        		for (Map.Entry<String, String> entry : lsmap.entrySet()){
      					if(!entry.getKey().equals("xh") && !entry.getKey().equals("ldmc")
	        					&&	!entry.getKey().equals("ch") && !entry.getKey().equals("qsh") && !entry.getKey().equals("rzksrq")	
	        					&& !entry.getKey().equals("bz") && !entry.getKey().equals("lxdh")&& !entry.getKey().equals("lddm") && !entry.getKey().equals("message")){
      						paralist.add(entry.getValue());
      					}
      					
      				}
	        		errorlist.add(paralist.toArray(new String[]{}));
	        	}else{
	        		paralist.add(lsmap.get("xh"));
	        		paralist.add(lsmap.get("lddm"));
	        		paralist.add(lsmap.get("ch"));
	        		paralist.add(lsmap.get("qsh"));
	        		paralist.add(lsmap.get("rzksrq"));
	        		paralist.add(lsmap.get("bz"));
	        		paralist.add(lsmap.get("lxdh"));
	        		paraUplist.add(lsmap.get("lddm"));
	        		paraUplist.add(lsmap.get("ch"));
	        		paraUplist.add(lsmap.get("qsh"));
	        		drAddlist.add(paralist.toArray(new String[]{}));
	        		drUplist.add(paraUplist.toArray(new String[]{}));
	        	}
			}
	        //�����ȷ���ϲ�Ϊ�գ��������ݱ���
	        int drNum = drUplist != null ? drUplist.size():0;
	        if(drNum > 0){
	        	  dao.saveDrDataForUpdate(drUplist);
	        	  dao.saveDrData(drAddlist);
	        	 
	        }
	        //������󼯺ϲ�Ϊ��,�ڷ��������������Ϣ�Թ�����
	        int errNum = errorlist != null ? errorlist.size():0;
	        String fileName = "";
	        if(errorlist != null && errorlist.size() > 0){
	        	 fileName =  uploadErrorExcel(errorlist);
	        }
	        HashMap<String, String> rsmap = new HashMap<String, String>();
	        rsmap.put("drNum",drNum+"" );
	        rsmap.put("errNum",errNum+"" );
	        rsmap.put("fileName",fileName);
	        return rsmap;
		}catch (NullPointerException e) {
			throw new SystemException(getJsonMessage(FILE_EMPTY));
		}catch (BiffException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return new HashMap<String, String>();
	}
	
	/**
	 * ��message��װΪjson����
	 * @param message
	 * @return
	 */
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	
	public void isEmptyWookBook(int cols,int rows){
		 //���ж�
        if(cols < 7 || rows < 2){
        	throw new SystemException(getJsonMessage(FILE_EMPTY));
        }
	}
	
	/**
	 *
	 * @����: �հ׷����ж�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-23 ����10:45:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkBlankCellAll(HashMap<String,String> lsmap){
		//�հ׷����ж�
    	boolean emptyflag = true;
    	for (String key : lsmap.keySet()) {
			if(StringUtils.isNotNull(lsmap.get(key))){
				emptyflag = false;
				break;
			}
		}
    	return emptyflag;
	}
	
	/**
	 * 
	 * @����: ��֤�ǿ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-23 ����01:33:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String,String> lsmap) throws Exception{
		HashMap<String,String> destMap = new HashMap<String, String>();
		for (String key : lsmap.keySet()) {
			destMap.put(key, lsmap.get(key));
		}
		//ȥ���Ǳ�����
		destMap.remove("rzjsrq");
		destMap.remove("bz");
		destMap.remove("lxdh");
    	boolean nullflag = true;
    	for (String key : destMap.keySet()) {
			if(StringUtils.isNull(destMap.get(key))){
				nullflag = false;
				break;
			}
		}
    	return nullflag;
	}
	
	/**
	 *
	 * @����: ��֤ÿһ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-23 ����11:14:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsmap
	 * @param gyglryForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public void checkEveryRowRecord(HashMap<String, String> lsmap)throws Exception{
		//������֤������ĺ���ȷ�ķֱ������������
		String message = "true";
		//����֤
		if(!checkNotNull(lsmap)){
			message = "false";
			lsmap.put("nullyz","ѧ��,¥��,���,���Һ�,��ְ��ʼ���ڱ��");
		}
		//ѧ���Ƿ������֤(�ݶ���Чѧ��Ϊ��Уѧ������У�����ݲ�����)
		if(dao.checkXhIsExist(lsmap.get("xh"))){
			message = "false";
			lsmap.put("xhyz","ѧ�Ų����ڻ��ѧ������У��");
		}
		//¥���Ƿ������֤
		String lddm = dao.checkLdmcIfExist(lsmap.get("ldmc"),lsmap.get("xh"));
		if(StringUtils.isNull(lddm)){
			message = "false";
			lsmap.put("ldmcyz","¥�������ڻ�ѧ�����ڸ�¥����");
		}else{
			lsmap.put("lddm", lddm);
		}
		//����Ƿ������֤,ch=#������¥��
		if(!"#".equals(lsmap.get("ch"))){
			if(!dao.checkChIfExist(lddm, lsmap.get("ch"),lsmap.get("xh"))){
				message = "false";
				lsmap.put("chyz","��Ų����ڻ�ѧ�����ڸò㣡");
			}
		}
		//���Һ��Ƿ������֤,qsh=#�����ǲ㳤
		if(!"#".equals(lsmap.get("qsh"))){
			if(!dao.checkQshIfExist(lddm, lsmap.get("ch"),lsmap.get("qsh"),lsmap.get("xh"))){
				message = "false";
				lsmap.put("qshyz","���ҺŲ����ڻ�ѧ�����ڸ����ң�");
			}
		}
		//��ְ��ʼ���ڡ���ְ����������֤  ��֤��ʽ�Ƿ�Ϊ2018-01-01
		String rzksrq = lsmap.get("rzksrq");
		if(StringUtils.isNotNull(rzksrq)){
			if(!common.newp.StringUtil.checkDateFormatAndValite("yyyy-MM-dd", rzksrq)){
				message = "false";
				lsmap.put("rzksrqyz","��ְ��ʼ���ڸ�ʽ����'2018-01-01'��");
			}
			  
		}
		String rzjsrq = lsmap.get("rzjsrq");
		if(StringUtils.isNotNull(rzjsrq)){
			if(!common.newp.StringUtil.checkDateFormatAndValite("yyyy-mm-dd", rzjsrq)){
				message = "false";
				lsmap.put("rzjsrqyz","��ְ��ʼ���ڸ�ʽ����'2018-01-01'��");
			}
		}
		//��ע��֤  ��֤�����Ƿ񳬹�100
		String bz = lsmap.get("bz");
		if(StringUtils.isNotNull(bz) && bz.length() > 100){
			message = "false";
			lsmap.put("bzyz","��ע���ܳ���100�֣�");
		}
		//��ϵ�绰��֤ 
		String lxdh = lsmap.get("lxdh");
		if(StringUtils.isNotNull(lxdh) && !common.newp.StringUtil.checkTelePhone(lxdh)){
			message = "false";
			lsmap.put("lxdhyz","��������ȷ�ĵ绰���룡");
		}
		lsmap.put("message", message);
	}
	
	/**
	 * 
	 * @����: �ϴ���������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-24 ����04:37:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws IOException
	 * String �������� 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist) throws IOException{
		 String fileName = System.currentTimeMillis()+"error.xls";
		 String path = TEMP_DIR+"\\"+fileName;
        File file=new File(path);
        if (!file.exists()) {
            boolean rs = file.createNewFile();
            System.out.print("rs="+rs);
        }
        WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
			
			  // ����������
           WritableSheet ws = wwb.createSheet("��������", 0);
           Label labelXh= new Label(0, 0, "ѧ��");
           Label labelLddm= new Label(1, 0, "¥��");
           Label labelCh= new Label(2, 0, "���");
           Label labelQsh= new Label(3, 0, "���Һ�");
           Label labelRzksrq = new Label(4, 0, "��ְ��ʼ����");
           Label labelBz = new Label(5, 0, "��ע");
           Label labelLxdh = new Label(6, 0, "��ϵ�绰");
           try {
        	   	ws.addCell(labelXh);
	   	        ws.addCell(labelLddm);
	   	        ws.addCell(labelCh);
	   	        ws.addCell(labelQsh);
	   	        ws.addCell(labelRzksrq);
	   	        ws.addCell(labelBz);
	   	        ws.addCell(labelLxdh);
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
               	 if(j<=6){
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
               		try {
							ws.addCell(labelId_i);
						} catch (RowsExceededException e) {
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
	 * 
	 * @����: ��֤�ظ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-31 ����04:46:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public void checkRepeatExcel(int cols,int rows,Sheet rs ) throws Exception{
		boolean result = true;
		for (int i = 1; i < rows; i++) {
			String flagStr = rs.getCell(1, i).getContents()+rs.getCell(2, i).getContents()+rs.getCell(3, i).getContents();
			if(StringUtils.isNull(flagStr)){
				continue;
			}
			for (int j = i+1; j < rows; j++) {
				String compareStr = rs.getCell(1, j).getContents()+rs.getCell(2, j).getContents()+rs.getCell(3, j).getContents();
				if(StringUtils.isNull(compareStr)){
					continue;
				}
				if(flagStr.equals(compareStr)){
					result = false;
					break;
				}
				
			}
			if(!result){
				throw new SystemException(getJsonMessage(FILE_REPEAT));
			}
		}
	}
	
}

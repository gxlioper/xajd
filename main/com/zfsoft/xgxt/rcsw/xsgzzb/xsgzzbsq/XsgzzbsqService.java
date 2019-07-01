package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;
import org.mortbay.http.HttpRequest;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class XsgzzbsqService extends SuperServiceImpl<XsgzzbsqForm, XsgzzbsqDao> {

	private XsgzzbsqDao dao = new XsgzzbsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	
	//�Ĵ���Ϣְҵ����ѧԺ�����ϴ�����
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};
	public XsgzzbsqService() {
		super.setDao(dao);
	}
	
	/**
	 * ��̬ȡ��
	 */
	protected void setTableInfo(XsgzzbsqForm t) {
		dao.setTableInfo(t);
	}
	
	/**
	 * �༶�ܱ�
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbsqForm t, User user)
		throws Exception {
		return dao.getPageListBj(t, user);
	}

	/**
	 * @����:�����ܱ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsgzzbsq(XsgzzbsqForm model) throws Exception {
		String gzzblx = model.getGzzblx();
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID(gzzblx);
		model.setSplc(splc);
		dao.setTableInfo(model); // ��̬ȡ��
		String filegid = UniqID.getInstance().getUniqIDHash();//��̬����guid
		model.setFilegid(filegid);
		boolean insertResult = super.runInsert(model);
        if(Base.xxdm.equalsIgnoreCase("13815")){
			this.uploadFile(model);
		}
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
			String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
			if("bj".equals(gzzblx)){
				sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
				shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
			}
			//�����������
			result = shlc.runSubmit(model.getSqid(),splc,model.getLrr(),shURL,sqURL);
		}
		return result;
	}
	
	/**
	 * �༶�ܱ�
	 */
	public XsgzzbsqForm getModelBj(XsgzzbsqForm t) throws Exception {
		return dao.getModelBj(t);
	}
	
	/**
	 * @����:��ǰѧ������
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCurrentXqmc(XsgzzbsqForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
	
	/**
	 * @����: ��ȡѧԺ�б�
	 */
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}
	/**
	 * @����: ��ȡ�༶�б�
	 */
	public List<HashMap<String, String>> getBjList(XsgzzbsqForm t, User user)
		throws Exception {
		return dao.getBjList(t, user);
	}
	
	/**
	 * @����: ��ȡ�����ܴε���ֹ����
	 */
	public List<HashMap<String, String>> getZcList(){
		// �õ�ѧ������,������ʼ����
		HashMap<String, String> zcMap = dao.getZc();
		String xqzs = zcMap.get("xqzs");
		String qsrq = zcMap.get("qsrq");
		if(StringUtils.isNull(xqzs) || StringUtils.isNull(qsrq)){
			return new ArrayList<HashMap<String, String>>();
		}
		xqzs = xqzs.trim();
		qsrq = qsrq.trim();
		int xqzsNum = Integer.parseInt(xqzs);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		List<HashMap<String, String>>  zcList = new ArrayList<HashMap<String, String>>();
		try {
			for (int i = 0; i < xqzsNum; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				// ������ʼ����
				c.setTime(DateFormat.getDateInstance().parse(qsrq));
				// ��i��
				c.add(Calendar.WEEK_OF_YEAR, i);
				// ��i�ܿ�ʼ����
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				if(i == 0){ // ��һ�ܿ�ʼ����Ϊ���ݿ����õ���ʼ����
					map.put("ksrq", qsrq);
				}else{
					map.put("ksrq", f.format(c.getTime()));
				}
				// ��i�ܽ�������
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				map.put("jsrq", f.format(c.getTime()));
				map.put("dm", String.valueOf(i + 1));
				map.put("mc", "��" + (i + 1) + "��");
				zcList.add(map);
			}
		} catch (Exception e) {
			logger.error("ѧ��������������ʼ���ڻ�δ��ʼ����");
			e.printStackTrace();
		}
		return zcList;
	}
	
	/**
	 * @����:�޸��ܱ�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsgzzbsq(XsgzzbsqForm model) throws Exception {
		String gzzblx = model.getGzzblx();
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID(gzzblx));
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}
		dao.setTableInfo(model);
		String previousgid = this.getFileGID(model.getSqid());
		if(StringUtils.isNull(previousgid)){
			String filegid = UniqID.getInstance().getUniqIDHash();//��̬����guid
			model.setFilegid(filegid);
		}else{
			model.setFilegid(previousgid);
		}
		boolean insertResult = super.runUpdate(model);
		if(Base.xxdm.equalsIgnoreCase("13815") && model.getScbz().equalsIgnoreCase("yfj")){
			this.uploadFile(model);
		}
		boolean result = true;
		if (insertResult && SUBMIT.equals(model.getType())) {
			shlc.deleteShjl(model.getSqid());
			String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
			String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
			if("bj".equals(gzzblx)){
				sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
				shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
			}
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getLrr(),shURL,sqURL);
		}
		return insertResult && result;
	}
	
	/**
	 * @����:�ύ�ܱ�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitXsgzzbsq(XsgzzbsqForm model) throws Exception {
		String gzzblx = model.getGzzblx();
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID(gzzblx));
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultXsgzzbsq = dao.updateXsgzzbsq(model);
		boolean result = false;
		if(resultXsgzzbsq){
			String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
			String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
			if("bj".equals(gzzblx)){
				sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
				shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
			}
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getLrr(),shURL,sqURL);
		}
		return result;
	}
	
	/**
	 * @����:�����ܱ�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelXsgzzbsq(XsgzzbsqForm model) throws Exception {
		boolean resultXsgzzbsq = dao.updateXsgzzbsq(model);
		return resultXsgzzbsq;
	}
	
	/**
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	
	
	@Override
	public List<HashMap<String, String>> getAllList(XsgzzbsqForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		String gzzblx = t.getGzzblx();
		if("bj".equals(gzzblx)){
			return dao.getPageListBj(t, user);
		}
		return dao.getPageList(t, user);
	}

	/**
	 * @����:�Ƿ��Ѿ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean isExistByXszbbsq(XsgzzbsqForm model, User user) throws Exception {
		boolean flag = false;
		String gzzblx = model.getGzzblx();
		String num = "";
		if("bj".equals(gzzblx)){
			num = dao.checkExistForSaveBj(model, user);
		}else{
			num = dao.checkExistForSave(model, user);
		}
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�Ĵ���Ϣְҵ����ѧԺ�����ϴ����Ի�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-19 ����02:20:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean uploadFile(XsgzzbsqForm model){
		String[] wjlxdms=model.getWjlxdms();
		if(wjlxdms == null || wjlxdms.length == 0){
			return true;
		}
		int arrLen = wjlxdms.length;
		XsgzzbsqDao dao = new XsgzzbsqDao();
		//��ȡ��������
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		//������ʵ�����
		XsgzzbsqForm fjmodel = new XsgzzbsqForm();
		boolean insertResult = true;
		if(wjlxdms != null){
			for(int i=0;i<arrLen;i++){
				fjmodel.setWjlxdm(wjlxdms[i]);
				//������
				FormFile file = (FormFile) files.get("fjid"+i);
				if (file != null && !StringUtil.isNull(file.getFileName())){
					String basePath = resource.getString("filesys.local.dir");
					//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
					if(StringUtils.isNull(basePath)){
						basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
					}
					String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
					
					if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
						File srcFile = null;
						try {
							srcFile = FileUtil.conversionFormFile(file);
						} catch (IOException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
						}
						File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
						try {
							FileUtils.copyFile(srcFile, destFile);
						} catch (IOException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
						}
						fjmodel.setFjlj(destFile.getAbsolutePath());
						fjmodel.setFjmc(file.getFileName());
						fjmodel.setFilegid(model.getFilegid());
					}
				}
				
				try {
					insertResult = dao.savefjb(fjmodel);
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
		}
		
		return insertResult;
	}
	
	/**
	 * �Ĵ���Ϣְҵ����ѧԺ�����ɾ��ʱ��ȥ��ȡ��ɾ����¼��filegid
	 */
	public String getFileGID(String sqid) throws Exception{
		return dao.getFileGID(sqid);
	}
	
	/**
	 * �Ĵ���Ϣְҵ����ѧԺ�����ɾ��ʱ��ȥ��ȡ��ɾ����¼��fjlj
	 */
	public List<HashMap<String, String>>  getfjlj(String[]filegids) throws Exception{
		return dao.getfjlj(filegids);
	}
	
	/**
	 * �Ĵ���Ϣְҵ����ѧԺ�����ɾ��ʱ��ɾ���������е�����
	 * @throws Exception 
	 */
	public boolean Delfile_13815(String[] filegids) throws Exception{
		return dao.Delfile_13815(filegids);
	}
	
	/**
	 * �Ĵ���Ϣְҵ����ѧԺɾ���������е����ݳɹ������ֱ��ɾ���������ϵ���ʵ�����ļ�
	 * 
	 */
	public boolean Delfile_13815_realfile(List<HashMap<String, String>> fjljs){
		int fjljslen = fjljs.size();
		boolean result = true;
		for(int i = 0;i < fjljslen;i++){
			File file = new File(fjljs.get(i).get("fjlj"));
			if (file.exists()){
				result = file.delete();
			}
		}
		return result;
	}
	
	/**
	 * �޸ĵ�ʱ���ѯ�Ƿ���filegid���ж��Ƿ���ԭ�ļ�
	 */
	public String getUpdateFilegid(String sqid){
		return dao.getUpdateFilegid(sqid);
	}
	
	/**
	 * �޸Ľ��浥��ɾ����������ɾ�������¼���������ȡ�����ļ����ļ�fjlj
	 */
	public HashMap<String, String> onefjlj(String fileid){
		return dao.onefjlj(fileid);
	}
	
	/**
	 * ɾ����������
	 */
	public boolean delonefjlj(String fileid) throws Exception{
		return dao.delonefjlj(fileid);
	}
}

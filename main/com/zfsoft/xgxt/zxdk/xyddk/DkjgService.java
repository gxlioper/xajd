/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-28 ����02:13:05 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.xyddk.hsdxd.HsdxdService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-28 ����02:13:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DkjgService extends SuperServiceImpl<XyddkModel, DkjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";

	/**
	 * 
	 * @����: ����ͬ��Ų�ѯ�Ŵ���¼
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-9 ����02:21:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getFdxxList(String htbh){
		
		return dao.getFdxxList(htbh);
	}
	
	/**
	 * 
	 * @����: ��ѧ�Ų�ѯ�����¼
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-11 ����02:25:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * XyddkModel �������� 
	 * @throws
	 */
	public XyddkModel getModelByXh(String xh) throws Exception{
		return dao.getModelByXh(xh);
	}
	
	/**
	 * 
	 * @����: ��ѯ�ɻ�����Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-11-6 ����09:18:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhkList(String xh){
		
		return dao.getKhkList(xh);
	}
	
	
	
	/**
	 * 
	 * @����: ����ͬ��Ų�ѯ���������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-11 ����02:41:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXdsqList(String htbh){
		return dao.getXdsqList(htbh);
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-11 ����04:08:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXdsq(XyddkModel model) throws Exception{
		return dao.saveXdsq(model);
	}
	
	
	/**
	 * 
	 * @����: ��ѧ�Ų�ѯ������ѧ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-27 ����10:21:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getGjdkList(String xh){
		
		return dao.getGjdkList(xh);
	}

	/** 
	 * @����:����ID��ѯ�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-25 ����04:24:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getDkxxSq(String id) {
		return dao.getDkxxSq(id);
	}
	
	
	/**
	 * 
	 * @����: ��ѧ�š�ѧ���ѯ��¼����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-3-23 ����05:10:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCountByXhAndXn(XyddkModel t){
		
		return dao.getCountByXhAndXn(t);
	}
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯ�Ŵ���Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-28 ����04:17:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFdxxListForXh(String xh){
		return dao.getFdxxListForXh(xh);
	}

	/**
	 * @throws Exception  
	 * @����:ȡ�ô�����ͳ�Ʊ�
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-11-30 ����02:45:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDkjgtj(XyddkModel t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getDkjgtj(t,user);
	}

	/** 
	 * @����:����ͳ�Ʊ�
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-11-30 ����03:59:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dkjglist
	 * @param user
	 * @param response
	 * @param filePath
	 * void �������� 
	 * @throws 
	 */
	public void exportHzbNew(List<HashMap<String, String>> dkjglist, User user,
			HttpServletResponse response, String filePath) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int row = 4;
		for (int i = 0; i < dkjglist.size(); i++) {
			HashMap<String, String> dataMap = dkjglist.get(i);
			HSSFRow hSSFRow = sheet.createRow(row);
			hSSFRow.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("rownum"));
			hSSFRow.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xm"));
			hSSFRow.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xh"));
			hSSFRow.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("sfzh"));
			hSSFRow.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("xymc"));
			hSSFRow.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("dkje"));
			hSSFRow.createCell(6, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("sjhm"));
			hSSFRow.createCell(7, HSSFCell.CELL_TYPE_STRING).setCellValue(dataMap.get("lxdzxx"));
			row++;
		}
		
		OutputStream os = response.getOutputStream();
		workbook.write(os);  
		os.flush();
		os.close();
	}
	
	/**
	 * 
	 * @����: �������뱣��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����02:15:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXdsq(XyddkModel model, User user) throws Exception {
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		String splc = csszModel.getXydxdshlc();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		String id = "";
		if(!"10511".equals(Base.xxdm)){
			 id = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(id);
		}else{
			id = model.getId();
		}
		boolean flag = dao.saveXdsq(model);
	    ShlcInterface shlc = new CommShlcImpl();
		if(model.getType().equals("submit")){
			if (flag) {
				/**
				 * ����ǻ�ʦ��
				 */
				if("10511".equals(Base.xxdm)){
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsqnew.do");
				}else{
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsq.do");
				}
			
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����02:38:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXdxx(String id){
		return dao.getXdxx(id);
	}
	
	//�ύ
	public boolean submitBusi(HashMap<String, String> model, User user)  throws Exception {
		model.put("shzt", Constants.YW_SHZ);
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		String splc = csszModel.getXydxdshlc();
		model.put("splc", splc);
		boolean flag = dao.updateXdxx(model);
		if(flag){
			ShlcInterface shlc = new CommShlcImpl();
			if("10511".equals(Base.xxdm)){
				 shlc.runSubmit(model.get("id"), splc, user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsqnew.do");
			}else{
				 shlc.runSubmit(model.get("id"), splc, user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsq.do");

			}
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		ShlcInterface shlc = new CommShlcImpl();
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �޸�������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����03:25:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xdxx
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXdxxYwzd(XyddkModel xdxx) throws Exception{
        return dao.updateXdxxYwzd(xdxx);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ��������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����06:39:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delxdxx(String[] ids) throws Exception{
		return dao.delxdxx(ids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ������˲�ѯ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����10:15:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXdshList(XyddkModel t,User user) throws Exception{
		return dao.getXdshList(t, user);
	}
	
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *��˱���
	 */
	public boolean saveSh(XyddkModel form, User user) {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		//model.setZd1("��Ч��ʱ");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getId());
		model.setSqrid(form.getXh());
		model.setTzlj("zxdk_gjdk_xdsh.do");
		if("10511".equals(Base.xxdm)){
			model.setTzljsq("zxdk_gjdk_xdsqnew.do");
		}else{
			model.setTzljsq("zxdk_gjdk_xdsq.do");
		}
		
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("splc", form.getSplc());
			paraMap.put("id", form.getId());
			paraMap.put("shzt", zhzt);
			reuslt = this.updateXdxx(paraMap);
			if(Constants.YW_TG.equals(zhzt) && "10511".equals(Base.xxdm)){
				HsdxdService service = new  HsdxdService();
				service.insertIntoFdb(form.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//�������
	public String savePlsh(XyddkModel t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		XyddkModel model = new XyddkModel();
		String[] ids = t.getIds();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setId(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setSplc(splcs[i]);

			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	public boolean cancel(XyddkModel myForm) throws Exception {
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("splc", myForm.getSplc());
		paraMap.put("id", myForm.getId());
		paraMap.put("shzt", Constants.YW_SHZ);
		boolean reuslt = this.updateXdxx(paraMap);
		/**
		 * ��ʦ����Ի����������һ������֮��ɾ���Ŵ�����Ϣ
		 */
		if("10511".equals(Base.xxdm)){
			/**
			 * ɾ���Ŵ���
			 */
			HsdxdService service = new HsdxdService();
			service.delFdb(myForm.getId());
		}
		return reuslt;
	}

	public String cxshnew(String ywid, XyddkModel model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("splc", model.getShlc());
		paraMap.put("id", ywid);
		paraMap.put("shzt", Constants.YW_SHZ);
		 this.updateXdxx(paraMap);
		return cancelFlag;

	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����02:45:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xdxx
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXdxx(HashMap<String, String> xdxx) throws Exception{
		return dao.updateXdxx(xdxx);
	}
	
	/**
	 * 
	 * @����: �鿴�͵������ʱ������ʾ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����01:57:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getViewCk(String id){
	  return dao.getViewCk(id);
		
	}
	
	/**
	 * 
	 * @����: �������鿴ҳ����ʾ����ͨ���ļ�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-12 ����03:44:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param htbh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDkjgXdxxTg(String htbh){
		return dao.getDkjgXdxxTg(htbh);
	}
	
	/**
	 * 
	 * @����: �������뱣��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-11 ����02:15:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXdsqUpdate(XyddkModel model, User user) throws Exception {
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		String splc = csszModel.getXydxdshlc();
		model.setSplc(splc);
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		String id = model.getId();
		boolean flag = dao.updateXdxxYwzd(model);
	    ShlcInterface shlc = new CommShlcImpl();
		if(model.getType().equals("updatesubmit")){
			if (flag) {
				if("10511".equals(Base.xxdm)){
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsqnew.do");
				}else{
					flag = shlc.runSubmit(id, splc,user.getUserName(), "zxdk_gjdk_xdsh.do", "zxdk_gjdk_xdsq.do");
				}
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����: ��ʦ����ǰ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-21 ����05:45:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhkListHsd(String xh){
		return dao.getKhkListHsd(xh);
	}
	
	/**
	 * 
	 * @����: �Ŵ���¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-22 ����05:04:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getFdxxListHsd(String htbh){
		return dao.getFdxxListHsd(htbh);
	}
	
	/**
	 * 
	 * @����:��֤��ͬ����Ƿ��ظ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-2 ����03:30:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param htbh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkHtbhIsNotExists(String id,String htbh){
		return dao.checkHtbhIsNotExists(id, htbh);
	}
	
	/** 
	 * @����:�Ƿ����������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-19 ����11:01:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean countBk(String xh){
		return dao.countBk(xh);
	}
	
	/** 
	 * @����:ȡ��ѧ�굥����ͷ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-19 ����04:38:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int getLowestLastYear(String xh){
		return dao.getLowestLastYear(xh);
	}
}

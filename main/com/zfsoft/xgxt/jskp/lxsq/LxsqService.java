package com.zfsoft.xgxt.jskp.lxsq;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshDao;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshForm;

public class LxsqService extends SuperServiceImpl<LxsqForm,LxsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @����: ��������Ϣ(ѧ��)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-7 ����11:53:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxStu(String username){
		return dao.getFzrxxStu(username);
	}
	
	/**
	 * 
	 * @����: ��������Ϣ(��ʦ)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-7 ����11:54:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxTea(String username){
		return dao.getFzrxxTea(username);
	}
	
	/**
	 * 
	 * @����: ��ȡ�ɻ�õ�ѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-7 ����05:11:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAvailableXhList(String[] xhs,String sqid,String ryflag){
		return dao.getAvailableXhList(xhs,sqid,ryflag);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: 
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-11 ����08:57:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean  saveForLxsq(LxsqForm lxsq,User user) throws Exception{
		boolean rs = true;
		
		String sqid1 = UniqID.getInstance().getUniqIDHash().toUpperCase();
		
		/*��������Ϊ��ʱȡ���ϱ�������*/
		if("0".equals(new CsszService().getSfsh())){
			lxsq.setSplcid(new CsszService().getSplc("sb").get("splc"));
			/*Ψһ������ѧ�š���Ŀ���ơ�����ʱ��*/
			if(!this.checkForPara(lxsq)){
				throw new SystemException(MessageKey.XG_ZJDX_JSKP_REPEAT);
			}
		}else{
			if(!this.checkIsNotRepeat(lxsq)){
				throw new SystemException(MessageKey.XG_ZJDX_JSKP_REPEAT);
			}
			lxsq.setSplcid(new CsszService().getSplc("lx").get("splc"));
		}

		if("submit".equals(lxsq.getSaveFlag())){
			lxsq.setShzt(Constants.YW_SHZ);
		}else{
			lxsq.setShzt(Constants.YW_WTJ);
		}
		
		if(StringUtils.isNotNull(lxsq.getSqid())){
			rs = dao.delRy(lxsq.getSqid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdate(lxsq);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*����������Ϊ�񣬴�ʱ��ֵ������ҳ���Ѿ������Σ�Ϊ��Ӱ�����Ĺ���ʹ�ã�Ĭ�ϰ���С�ֺ���������Ϊ0-10*/
			if("0".equals(new CsszService().getSfsh())){
				lxsq.setZxf("0");
				lxsq.setZdf("10");
				/*�ڲ���һ���ֶ�ֵ�����ж����������Դ���Է�������ݣ��Ժ�����ж���������*/
				lxsq.setSjly("NO");
			}else{
				lxsq.setSjly("YES");
			}
			/*��ǰѧ��*/
			lxsq.setLxxn(Base.currXn);
			/*�������ɵ�id����form*/
			lxsq.setSqid(sqid1);
			/*���ݿͻ�Ҫ��Ҫ����������ʱ�����¼�����ݵ��û������ֶΣ�����¼���ˡ�sjlrr������form*/
			lxsq.setSjlrr(user.getUserName());
			rs = dao.runInsert(lxsq);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> params = new ArrayList<String[]>();
		String sbsj = GetTime.getTimeByFormat(DATE_FORMAT);
		if("1".equals(new CsszService().getSfsh())){
			String[] xhs = lxsq.getXhs();
			if(xhs != null && xhs.length > 0){
				for (int i = 0; i < xhs.length; i++) {
					params.add(new String[]{xhs[i],lxsq.getSqid(),lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),Constants.YW_WTJ,"0" });
				}
				rs = dao.saveRy(params);
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
		}else{
			if(!"Edit".equals(lxsq.getTjsf())){
				/*����ҳ���ύ�ж�*/
				params.add(new String[]{sqid1,lxsq.getXh(),sqid1,lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),Constants.YW_SHZ,"1",lxsq.getFilepath()});
				rs = dao.saveRyOne(params);
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}else{
				/*�޸�ҳ���ύ�ж�*/
				params.add(new String[]{lxsq.getSqid(),lxsq.getXh(),lxsq.getSqid(),lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),Constants.YW_SHZ,"1",lxsq.getFilepath()});
				rs = dao.saveRyOne(params);
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
		}
		if("submit".equals(lxsq.getSaveFlag())){
			if("1".equals(new CsszService().getSfsh())){
				rs = shlc.runSubmit(lxsq.getSqid(),lxsq.getSplcid(),lxsq.getFzr(), "pjpy_jskp_lxsh.do", "pjpy_jskp_lxsq.do");
			}else{
				if(!"Edit".equals(lxsq.getTjsf())){
					rs = shlc.runSubmit(sqid1,lxsq.getSplcid(), lxsq.getFzr(), "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
				}else{
					rs = shlc.runSubmit(lxsq.getSqid(),lxsq.getSplcid(), lxsq.getFzr(), "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
				}
				
			}
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-11 ����02:36:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(LxsqForm lxsq){
		return dao.checkIsNotRepeat(lxsq);
	}
	
	/**
	 * ��֤ѧ�š���Ŀ���ơ�����ʱ���Ƿ��ظ�
	 */
	public boolean checkForPara(LxsqForm lxsq){
		return dao.checkForPara(lxsq);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ������Ա��ѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-12 ����02:31:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmcyryXhs(String xmid){
		return dao.getXmcyryXhs(xmid);
	}
	
	/**
	 * 
	 * @����:ɾ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-12 ����03:11:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRys(String[] sqids) throws Exception{
		return dao.delRys(sqids);
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @����: �����ύ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-7 ����04:23:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusi(LxsqForm model, User user)  throws Exception {
		String sfsh = new CsszDao().getSfsh();
		boolean flag = false;
		String splc = null;
		
		if("1".equals(sfsh)){
			splc = new CsszService().getSplc("lx").get("splc");
	        if(Constants.YW_YTH.equals(model.getShzt())){
	        	splc = model.getSplcid();
			}
	        model.setShzt(Constants.YW_SHZ);
			model.setSplcid(splc);
			flag = dao.runUpdate(model);
		}else{
			JskpXmsbshForm jskpXmsbshForm = new JskpXmsbshForm();
			JskpXmsbshDao jskpXmsbshDao = new JskpXmsbshDao();
			splc = new CsszService().getSplc("sb").get("splc");
			jskpXmsbshForm.setShzt(Constants.YW_SHZ);
			jskpXmsbshForm.setSplcid(splc);
			jskpXmsbshForm.setSqid(model.getSqid());
			flag = jskpXmsbshDao.runUpdate(jskpXmsbshForm);
		}
		
		if(flag){
			if("1".equals(sfsh)){
				shlc.runSubmit(model.getSqid(), splc, model.getFzr(), "pjpy_jskp_lxsh.do", "pjpy_jskp_lxsq.do");
			}else{
				shlc.runSubmit(model.getSqid(), splc, model.getFzr(), "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * �����ύ
	 */
	public boolean plSubmit(String sqid,String splc,String fzr) throws Exception{
		boolean flag = false;
		JskpXmsbshForm jskpXmsbshForm = new JskpXmsbshForm();
		JskpXmsbshDao jskpXmsbshDao = new JskpXmsbshDao();
		jskpXmsbshForm.setShzt(Constants.YW_SHZ);
		jskpXmsbshForm.setSplcid(splc);
		jskpXmsbshForm.setSqid(sqid);
		flag = jskpXmsbshDao.runUpdate(jskpXmsbshForm);
		if(flag){
			shlc.runSubmit(sqid,splc,fzr, "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-12 ����05:46:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getRyszList(LxsqForm t, User user) throws Exception{
		return dao.getRyszList(t, user);
	} 
	
	/**
	 * @throws Exception 
	 * 
	 * @����:������Ա����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-13 ����09:48:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveRysz(LxsqForm lxsq) throws Exception{
		String sbsj = GetTime.getTimeByFormat(DATE_FORMAT);
		String[] xhs = lxsq.getXhs();
		String lxzt = "1".equals(lxsq.getShzt()) ? "1" :"0";
		String shzt = "1".equals(lxsq.getShzt()) ? Constants.YW_SHZ:Constants.YW_WTJ;
		lxsq = this.getModel(lxsq);
		List<String[]> params = new ArrayList<String[]>();
		List<String> sqidList = new ArrayList<String>();
		for (int i = 0; i < xhs.length; i++) {
			String sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			params.add(new String[]{sqid,xhs[i],lxsq.getSqid(),lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),shzt,lxzt });
			sqidList.add(sqid);
		}
		boolean rs = dao.saveRys(params);
		if("1".equals(lxsq.getShzt()) && rs){
			rs = shlc.runSubmitBatchNotCommit(sqidList.toArray(new String[]{}), new CsszService().getSplc("sb").get("splc"), xhs, "jskp_xmsh.do", "jskp_xmsb.do");
		}
		return rs;
	}
	
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-13 ����03:20:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBmmc(String bmdm){
		return dao.getBmmc(bmdm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-20 ����04:24:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXmry(String[] sqids) throws Exception{
		return dao.delXmry(sqids);
	}
	
	/**
	 * 
	 * @����: �Ƿ����м�¼��δ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-21 ����09:34:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isNotHaveShjl(String[] ywids){
		return dao.isNotHaveShjl(ywids);
	}
	
	/**
	 * @����: �ж�ѧ�����꼶�Ƿ���ڵ���2017
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-17 ����04:38:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isStandardStu(User user) throws Exception{
		boolean flag = false;
		if("stu".equals(user.getUserType())){
			flag = dao.isStandardStu(user.getUserName());
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @����: ���ص���ģ��,����excel��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-23 ����06:17:01
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
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("����"),11,WritableFont.NO_BOLD );
		
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
	    ws.setColumnView(2, 16);/*��Ŀ�������*/
	    ws.setColumnView(3, 30);/*����ʱ��(��:2018-01-01)*/
	    ws.setColumnView(4, 16);/*������*/
	    ws.setColumnView(5, 20);/*��������ϵ��ʽ*/
	    ws.setColumnView(6, 16);/*ָ����ʦ*/
	    ws.setColumnView(7, 26);/*ָ����ʦ��ϵ��ʽ*/
	    ws.setColumnView(8, 15);/*������ѧ��*/
	    ws.setColumnView(9, 36);/*��������*/
		
		/*��һ��̧ͷ*/
		Label row1col1= new Label(0, 0, "��Ŀ����",body);
		Label row1col2= new Label(1, 0, "ָ������",body);
		Label row1col3= new Label(2, 0, "��Ŀ�������",body);
		Label row1col4= new Label(3, 0, "����ʱ��(��:2018-01-01)",body);
		Label row1col5= new Label(4, 0, "������",body);
		Label row1col6= new Label(5, 0, "��������ϵ��ʽ",body);
		Label row1col7= new Label(6, 0, "ָ����ʦ",body);
		Label row1col8= new Label(7, 0, "ָ����ʦ��ϵ��ʽ",body);
		Label row1col9= new Label(8, 0, "������ѧ��",body);
		Label row1col10= new Label(9, 0, "��������",body);
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
	    
	    /**sheet2����Ŀ�����ձ�*/
	    WritableSheet ws1 = wwb.createSheet("��Ŀ�����ձ�", 1);
	    List<HashMap<String, String>> xmlbList = dao.getXmlbList();
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
		try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
			 
		 }
	}
	
	/**
	 * @����: ����е����ݱ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-24 ����10:07:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param lxsqForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,LxsqForm lxsqForm,User user){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap = this.DrExcelInfoCheck(is, lxsqForm,user);
			/**�ж�Excle����Ƿ�Ϊ��*/
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				/**�����ݵ��������*/
				List<String[]> paraSqblist = (List<String[]>) resultMap.get("drSqblist");
				if( paraSqblist == null ||paraSqblist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrSqbData(paraSqblist);
			  	 
			  	 /**�ϱ���*/
			  	List<String[]> paraSbblist = (List<String[]>) resultMap.get("drSbblist");
				if( paraSbblist == null ||paraSbblist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrSbbData(paraSbblist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),lxsqForm.getExclePath());
				resultMap.put("gid", gid);
				this.saveDrSqbData((List<String[]>) resultMap.get("drSqblist"));
				this.saveDrSqbData((List<String[]>) resultMap.get("drSbblist"));
				logger.info("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	/**
	 * @����: ������Ϣ��֤
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-24 ����10:37:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param lxsqForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,LxsqForm lxsqForm,User user){
		
		/**�����¼����*/
		/*�������������*/
		List<String[]> drSqblist = new ArrayList<String[]>();
		/*�����ϱ�������*/
		List<String[]> drSbblist = new ArrayList<String[]>();
		
		
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		Workbook rwb = null;
		
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
			/*�õ����е���*/
		    int clos=rs.getColumns();
		    /*�õ����е���*/
	        int rows=rs.getRows();
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){
	        	resultMap.put("result", "excelrepeat");
	        }else{
	        	/*��*/
	        	for (int i = 1; i < rows; i++) {
					/*ȡ��ÿ����֤���ݣ�����lsmap*/
					HashMap<String, String>  lsmap = new HashMap<String, String>();
					String xmmc = rs.getCell(0, i).getContents();
					String zdbmmc = rs.getCell(1, i).getContents();
					String xmlbmc = rs.getCell(2, i).getContents();
					String cysj = rs.getCell(3, i).getContents();
					String fzrxm = rs.getCell(4, i).getContents();
					String fzrlxfs = rs.getCell(5, i).getContents();
					String zdls = rs.getCell(6, i).getContents();
					String zdlslxfs = rs.getCell(7, i).getContents();
					String xh = rs.getCell(8, i).getContents();
					String lxly = rs.getCell(9, i).getContents();
					lsmap.put("xmmc", xmmc);
					lsmap.put("zdbmmc", zdbmmc);
					lsmap.put("xmlbmc", xmlbmc);
					lsmap.put("cysj", cysj);
					lsmap.put("fzrxm", fzrxm);
					lsmap.put("fzrlxfs",fzrlxfs);
					lsmap.put("zdls",zdls);
					lsmap.put("zdlslxfs", zdlslxfs);
					lsmap.put("xh", xh);
					lsmap.put("lxly", lxly);
					//�����ж�
					if(this.checkNullRow(lsmap)){
						continue;
					}
  	        		
  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, lxsqForm);
  	        		ArrayList<String> paralist = new ArrayList<String>();
  	        		/*�ϱ��������õ�paralistSbb*/
  	        		ArrayList<String> paralistSbb = new ArrayList<String>();
  	        		
  	        		if("false".equals(resultmap.get("result"))){
  	        			paralist.add(lsmap.get("xmmc"));
  	        			paralist.add(lsmap.get("zdbmmc"));
  	        			paralist.add(lsmap.get("xmlbmc"));
  	        			paralist.add(lsmap.get("cysj"));
  	        			paralist.add(lsmap.get("fzrxm"));
  	        			paralist.add(lsmap.get("fzrlxfs"));
  	        			paralist.add(lsmap.get("zdls"));
  	        			paralist.add(lsmap.get("zdlslxfs"));
  	        			paralist.add(lsmap.get("xh"));
  	        			paralist.add(lsmap.get("lxly"));
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
  	        			/*ȡ�ϱ�����������*/
  	        			String splc = new CsszService().getSplc("sb").get("splc");
  	        			/*ȥϵͳ��ǰʱ��*/
  	        			
  	        			/**��һ���������������*/
  	        			paralist.add(sqid);                   /*ÿ��ѭ��ǰ������һ��sqid*/
  	        			paralist.add(lsmap.get("xmmc"));      /*��Ŀ����*/
  	        			paralist.add(lsmap.get("zdbmdm"));    /*ָ�����Ŵ���*/
  	        			paralist.add(lsmap.get("xmlbdm"));    /*��Ŀ������*/
  	        			paralist.add(lsmap.get("cysj"));      /*����ʱ�䣨����ʱ�䣩*/
  	        			paralist.add(lsmap.get("fzrgh"));     /*�����˹��Ż���ѧ��*/
  	        			paralist.add(lsmap.get("fzrlxfs"));   /*��������ϵ��ʽ*/
  	        			paralist.add(lsmap.get("zdls"));      /*ָ����ʦ*/
  	        			paralist.add(lsmap.get("zdlslxfs"));  /*ָ����ʦ��ϵ��ʽ*/
  	        			paralist.add(lsmap.get("lxly"));      /*��������*/
  	        			paralist.add(splc);	                  /*��������*/
  	        			paralist.add("0");					  /*���״̬*/
  	        			paralist.add("10");					  /*����*/
  	        			paralist.add("0");					  /*��С��*/
  	        			paralist.add("NO");					  /*��С��*/
  	        			paralist.add(Base.currXn);			  /*��ǰѧ��*/
  	        			paralist.add(user.getUserName());	  /*��ȡ��ǰ��¼�õ��û���������ϵͳ����¼˭������������¼*/
  	        			drSqblist.add(paralist.toArray(new String[]{}));
  	        			
  	        			/**�ڶ��������ϱ�����*/
  	        			paralistSbb.add(sqid);				  /*ÿ��ѭ��ǰ������һ��sqid*/
  	        			paralistSbb.add(lsmap.get("xh"));	  /*������ѧ��*/
  	        			paralistSbb.add(sqid);				  /*��ĿID����ʵҲ��sqid*/
  	        			paralistSbb.add(lsmap.get("cysj"));	  /*����ʱ��*/
  	        			paralistSbb.add(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));/*ϵͳ��ǰʱ�䣬�ϱ�ʱ��*/
  	        			paralistSbb.add(splc);				  /*��������*/
  	        			paralistSbb.add("0");				  /*���״̬*/
  	        			paralistSbb.add("1");				  /*����״̬*/
  	        			drSbblist.add(paralistSbb.toArray(new String[]{}));
  	        		}
  				}
        	  resultMap.put("drSqblist", drSqblist);
        	  resultMap.put("drSbblist", drSbblist);
        	  resultMap.put("errorlist", errorlist);
	        }
	      
			} catch (BiffException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return resultMap;
	}
	
	/**
	 * @����: ��֤������Ƿ����ظ����ݣ������ˡ�����ʱ�䡢������Ŀ���ƣ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-24 ����11:16:31
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
			/*��Ŀ����*/
			String xmmc = rs.getCell(0, i).getContents();
			/*����ʱ�䣨����ʱ�䣩*/
			String lxsj = rs.getCell(3,i).getContents();
			/*ѧ�ţ������ˣ�*/
			String xh = rs.getCell(8,i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNotNull(xmmc)){
				str = str + xmmc.trim();
			}
			if(StringUtils.isNotNull(lxsj)){
				str = str + lxsj.trim();
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
	 * @����: ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-24 ����11:49:02
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
	 * @����: ��֤ÿ�еļ�¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-24 ����11:53:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsmap
	 * @param lxsqForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,LxsqForm lxsqForm){
		
		/**
		 * 1��ѧ���Ƿ����xsxxb�У�
		 * 2��ָ�����Ų����ڣ����飡
		 * 3����Ŀ��𲻴��ڣ����飡
		 * 4������ʱ���ʽ���Ϸ����밴�գ�2018-01-01����ʽ��д��
		 * 5����������ϵͳ�в����ڣ�
		 * 6����ϵ��ʽ����ȷ
		 */
		String message = "true";
		
		/**��֤��Ŀ���ơ�ָ�����š���Ŀ��𡢲���ʱ�䡢�����ˡ��������Ƿ�Ϊ��*/
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "��Ŀ���ơ�ָ�����š���Ŀ��𡢲���ʱ�䡢�����ˡ������˲���Ϊ�գ�");
		}
		
		/**��ָ֤�������Ƿ����*/
		String zdbm = dao.getZdbmdm(lsmap.get("zdbmmc"));
		if(StringUtils.isNull(zdbm)){
			message = "false";
			lsmap.put("zdbmyz", "ָ�����Ų����ڻ���ڿո���˶ԣ�");
		}
		
		/**��֤��Ŀ����Ƿ����*/
		String xmlb = dao.getXmlbdm(lsmap.get("xmlbmc"));
		if(StringUtils.isNull(xmlb)){
			message = "false";
			lsmap.put("xmlbyz", "��Ŀ��𲻴��ڻ���ڿո���˶ԣ�");
		}
		
		/**��֤����ʱ���Ƿ���ȷ*/
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = (Date)formatter.parse(lsmap.get("cysj"));
			if(!lsmap.get("cysj").equals(formatter.format(date))){
				message = "false";
				lsmap.put("cysjyz", "����ʱ���ʽ����Ϊyyyy-mm-dd��");
			}
		} catch (ParseException e) {
			message = "false";
			lsmap.put("cysjyz", "����ʱ���ʽ����Ϊyyyy-mm-dd��");
		} 
		
		/**��֤�������Ƿ����*/
		String fzr = dao.getFzr(lsmap.get("fzrxm"));
		if(StringUtils.isNull(fzr)){
			message = "false";
			lsmap.put("fzryz", "�����˲����ڻ��ߴ��ڿո���˶ԣ�");
		}
		
		/**��֤�������Ƿ����*/
		String cyr = dao.getCyr(lsmap.get("xh"));
		if(StringUtils.isNull(cyr)){
			message = "false";
			lsmap.put("cyryz", "������ѧ�Ų��棬��˶ԣ�");
		}
		
		/**������֤*/
		if(!dao.checkIsNotExists(lsmap.get("xh"), lsmap.get("xmmc"), lsmap.get("cysj"))){
			message = "false";
		    lsmap.put("zjyz", "ѧ����ͬһ�����Ѿ����������Ŀ�����飡");
		}
		
		/**��������֤��û������ʱ��*/
		if("true".equals(message)){
			lsmap.put("zdbmdm", zdbm);/*ָ�����Ŵ���*/
			lsmap.put("fzrgh", fzr);/*�����˹���*/
			lsmap.put("xmlbdm", xmlb);/*�����˹���*/
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @����: ��֤�ǿ���(�����ֶ���xmmc��zdbmmc��xmlbmc��cysj��fzrxm��xh)
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-24 ����02:28:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yzMap
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String,String> yzMap){
		
		boolean rs = true;
		for(String key : yzMap.keySet()){
			if(StringUtils.isNull(yzMap.get(key)) && !("lxly").equals(key) && !("zdlslxfs").equals(key) && !("zdls").equals(key) && !("fzrlxfs").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @����: ����������Ŀ�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-26 ����09:51:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDrSqbData(List<String[]> paraSqblist) 
		throws Exception{
		if(paraSqblist != null && paraSqblist.size() > 0){
			return dao.saveDrSqbData(paraSqblist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @����: ����������Ŀ�ϱ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-26 ����11:06:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paraSqblist
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDrSbbData(List<String[]> paraSqblist) 
		throws Exception{
		if(paraSqblist != null && paraSqblist.size() > 0){
			return dao.saveDrSbbData(paraSqblist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @����: ������excel���д�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-26 ����11:34:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist,String filepath)
		throws Exception{
		
		String fileName = System.currentTimeMillis()+"error.xls";
		String path = filepath+fileName;
		File file=new File(path);
		
        if (!file.exists()) {
            file.createNewFile();
        }
        WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
			/*����������*/
			WritableSheet ws = wwb.createSheet("��������", 0);
       		WritableCellFormat titlefont =  this.getFontStyle("title");
   		/**
   		 * sheet1
   		 */
   	    Label row1col1= new Label(0, 0, "��Ŀ����",titlefont);
   	    Label row1col2= new Label(1, 0, "ָ������",titlefont);
   	    Label row1col3= new Label(2, 0, "��Ŀ�������",titlefont);
   	    Label row1col5= new Label(3, 0, "����ʱ��",titlefont);
   		Label row1col6= new Label(4, 0, "������",titlefont);
   		Label row1col7= new Label(5, 0, "��������ϵ��ʽ",titlefont);
   		Label row1col8= new Label(6, 0, "ָ����ʦ",titlefont);
   		Label row1col9= new Label(7, 0, "ָ����ʦ��ϵ��ʽ",titlefont);
   		Label row1col10= new Label(8, 0, "������ѧ��",titlefont);
   		Label row1col11= new Label(9, 0, "��������",titlefont);
   		
           try {
           	ws.addCell(row1col1);
       		ws.addCell(row1col2);
       		ws.addCell(row1col3);
       		ws.addCell(row1col5);
       		ws.addCell(row1col6);
       		ws.addCell(row1col7);
       		ws.addCell(row1col8);
       		ws.addCell(row1col9);
       		ws.addCell(row1col10);
       	    ws.addCell(row1col11);
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
               	 if(j<=9){
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
	 * @����: ����excle����������ʽ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-26 ����11:39:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat �������� 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras)
		throws WriteException{

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

	/**
	 * @����: ����ID��ѯѧ���걨��Ŀ����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-3 ����05:59:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuSbDataList(String[] sqidArr)throws Exception{
		return dao.getStuSbDataList(sqidArr);
	}
}

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����10:35:32 
 */  
package com.zfsoft.xgxt.rcsw.jqlx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqDao;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqDAO;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import org.springframework.util.ResourceUtils;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-������У
 * @�๦������: ������Уservice
 * @���ߣ� 945
 * @ʱ�䣺 2013-12-30 ����10:35:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JqlxService extends SuperServiceImpl<JqlxModel, JqlxDao> {
	
	private JqlxDao dao = new JqlxDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public JqlxService() {
		super.setDao(dao);
	}
	
	public JqlxModel getModel(JqlxModel t) throws Exception {
		return dao.getModel(t);
	}
	
	public boolean allowUpdateSetting() {
		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getSfcfCount(JqlxModel t){
		try {
			return dao.getSfcfCount(t);
		}catch (SQLException e) {
			return -1;
		}
	}
	
	/** 
	 * ��λ�б�
	 */
	public List<HashMap<String, String>> getCwxxList(JqlxModel myForm, User user) throws Exception{
		return dao.getCwxxList(myForm,user);
	}
	
	/**
	 * ��������
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return dao.importData(request,response);
	}
	/**
	 * ���ص���ģ��
	 */
	public void downloadXls(JqlxModel t, User user, HttpServletRequest request, WritableWorkbook wwb) throws Exception{
		List<HashMap<String, String>> dataList = getDownloadResultList(t, user);
		WritableSheet ws = wwb.getSheet(0);
		int row = 1;
		// ���ݼ�д��
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String, String> dataMap = dataList.get(m);
			ws.addCell(new Label(0, row, dataMap.get("xn")));
			ws.addCell(new Label(1, row, dataMap.get("xqmc")));
			ws.addCell(new Label(2, row, dataMap.get("xh")));
			ws.addCell(new Label(3, row, dataMap.get("lxkssj")));
			ws.addCell(new Label(4, row, dataMap.get("lxjzsj")));
			ws.addCell(new Label(5, row, dataMap.get("ldmc")));
			ws.addCell(new Label(6, row, dataMap.get("qsh")));
			ws.addCell(new Label(7, row, dataMap.get("cwh")));
			row++;
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 
	 * @����:���������У����
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����04:51:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savaJqlxsq(JqlxModel model) throws Exception {
		
		if(model.getType().equals("submit") && !Constants.YW_YTH.equalsIgnoreCase(model.getSqzt())){
			// ��ȡ����������
			String splc = dao.getShlcID();
			model.setLcid(splc);
		}
		
		//�û�����������������
		if(StringUtil.isNull(model.getLcid())){
			// ��ȡ����������
			String splc = dao.getShlcID();
			model.setLcid(splc);
			model.setSqzt(Constants.YW_WTJ);
		}
		
		if(model.getType().equals("submit")){
			model.setSqzt(Constants.YW_SHZ);//�����
		}
		
		boolean insertResult = false;
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSjlx("1");//������
		if(model.getSqid()==null||model.getSqid().equals("")){
			String bbsqid = UniqID.getInstance().getUniqIDHash();
			model.setSqid(bbsqid);
			insertResult = super.runInsert(model);
		}else{
			insertResult = super.runUpdate(model);
		}
		if (insertResult && "submit".equalsIgnoreCase(model.getType())) {
			//�����������
			shlc.runSubmit(model.getSqid(),model.getLcid(),model.getXh(),"rcsw_jqlxsh.do","rcsw_jqlxsq.do");
		}
		return insertResult;
	}
	
	/**
	 * 
	 * @����:���������У���ά��
	 * @���ߣ�945
	 * @���ڣ�2014-1-3 ����08:54:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savaJqlxJg(JqlxModel model) throws Exception {
		model.setSqzt(Constants.YW_TG);//pass
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setLcid(splc);
		boolean insertResult = false;
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSjlx("0");//ֱ��¼��

		if(!"10026".equals(Base.xxdm)){
			// =========== ����λ��Ϣ begin =======
			String cwxx = model.getCwxx();
			if(cwxx!=null && !"".equals(cwxx)){
				String[] cwxxArr = cwxx.split("_");
				YdsqDao ydsqDao = new YdsqDao();
				HashMap<String, String> cwxxMap = ydsqDao.getCwxx(cwxxArr[0], cwxxArr[1], cwxxArr[2]);
				model.setLddm(cwxxMap.get("lddm"));
				model.setQsh(cwxxMap.get("qsh"));
				model.setCwh(cwxxMap.get("cwh"));
			}
			// =========== ����λ��Ϣ end =======
		}

		if(model.getSqid()==null||model.getSqid().equals("")){
			String bbsqid = UniqID.getInstance().getUniqIDHash();
			model.setSqid(bbsqid);
			insertResult = super.runInsert(model);
		}else{
			insertResult = super.runUpdate(model);
		}
		return insertResult;
	}
	/**
	 * �޸�������Ĵ�λ��Ϣ
	 */
	public boolean savaJqlxJgShlc(JqlxModel model) throws Exception {
		// =========== ����λ��Ϣ begin =======
		String cwxx = model.getCwxx();
		if(cwxx!=null && !"".equals(cwxx)){
			String[] cwxxArr = cwxx.split("_");
			YdsqDao ydsqDao = new YdsqDao();
			HashMap<String, String> cwxxMap = ydsqDao.getCwxx(cwxxArr[0], cwxxArr[1], cwxxArr[2]);
			model.setLddm(cwxxMap.get("lddm"));
			model.setQsh(cwxxMap.get("qsh"));
			model.setCwh(cwxxMap.get("cwh"));
		}
		// =========== ����λ��Ϣ end =======
		return super.runUpdate(model);
	}
	
	/**
	 * 
	 * @����:ɾ��������У����
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����04:51:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteJqlxsq(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?jqlxsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:"-1";
		return new String[]{String.valueOf(i),str};
	}
	
	public String[] deleteJqlxjg(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.jgCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?jqlxsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:"-1";
		return new String[]{String.valueOf(i),str};
	}
	
	private int jqlxsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @����:����״̬
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����04:51:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateModel(JqlxModel model) throws Exception {
		return super.runUpdate(model);
	}
	
	/**
	 * 
	 * @����:�ύ����
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����04:51:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitSq(JqlxModel model) throws Exception {
		
		//���˻����ⶼ���»�ȡ��������
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getSqzt())){
			String lcid = dao.getShlcID();
			model.setLcid(lcid);
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultBbsq = dao.updateSq(model);
		boolean result = false;
		if(resultBbsq){
			result = shlc.runSubmit(model.getSqid(), model.getLcid(),model.getXh(),"rcsw_jqlxsh.do","rcsw_jqlxsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:�����ܷ���
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����04:52:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * 
	 * @����:����б�
	 * @���ߣ�945
	 * @���ڣ�2014-1-2 ����10:57:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAuddingList(JqlxModel t, User user) throws Exception {
		return dao.getAuddingList(t, user);
	}
	@TransactionControl
	public String savePlsh(JqlxModel t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] lcids = t.getLcids();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			JqlxModel model = new JqlxModel();
			model.setSqid(ids[i]);
			model.setGwid(gwids[i]);
			model.setXh(xhs[i]);
			model.setLcid(lcids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());

			boolean isSuccess = singleSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	
	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�945
	 * @���ڣ�2014-1-2 ����05:37:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean singleSh(JqlxModel form,User user) throws Exception{
		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getLcid());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		model.setGwid(form.getGwid());
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_jqlxsh.do");
		model.setTzljsq("rcsw_jqlxsq.do");
		
		boolean result = false;
		try {
			String zhzt = shlc.runAuditingNotCommit(model);
			form.setSqzt(zhzt);
			result = dao.runUpdateNotCommit(form);
			// ���һ�����ͨ��ʱ
			if(result && zhzt.equals(Constants.SH_TG)){
				if(!"10026".equals(Base.xxdm)){
					JqlxModel jgForm = new JqlxModel();
					jgForm.setSqid(form.getSqid());
					WdgwsqDAO wdgwsqDAO = new WdgwsqDAO();
					HashMap<String, String> qsxx = wdgwsqDAO.getQsxx(form.getXh());
					if("10344".equals(Base.xxdm)){
						if(qsxx.get("lddm") != null && qsxx.get("qsh") != null && qsxx.get("cwh") != null){
							jgForm.setLddm(qsxx.get("lddm"));
							jgForm.setQsh(qsxx.get("qsh"));
							jgForm.setCwh(qsxx.get("cwh"));
							result = dao.runUpdateNotCommit(jgForm);
						}
					} else {
						jgForm.setLddm(qsxx.get("lddm") == null ? "" : qsxx.get("lddm"));
						jgForm.setQsh(qsxx.get("qsh") == null ? "" : qsxx.get("qsh"));
						jgForm.setCwh(qsxx.get("cwh") == null ? "" : qsxx.get("cwh"));
						result = dao.runUpdateNotCommit(jgForm);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
	//����ҵ��״̬
	private boolean updateBsiness(String sqid,String shzt) throws Exception{
		JqlxModel upForm = new JqlxModel();
		upForm.setSqid(sqid);
		upForm.setSqzt(shzt);
		return dao.runUpdate(upForm);
	}
	
	/**
	 * 
	 * @����:��˹����еĳ���
	 * @���ߣ�945
	 * @���ڣ�2014-1-2 ����05:43:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(String sqid) throws Exception{
		boolean result = updateBsiness(sqid,Constants.YW_SHZ);
		if(result){
			if(!"10026".equals(Base.xxdm)){
				result = dao.cancelQsxx(sqid);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @����:������б�
	 * @���ߣ�945
	 * @���ڣ�2014-1-2 ����05:38:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getResultList(JqlxModel t, User user) throws Exception {
		return dao.getResultList(t, user);
	}
	/**
	 * ����ģ���������
	 */
	public List<HashMap<String, String>> getDownloadResultList(JqlxModel t, User user) throws Exception {
		return dao.getDownloadResultList(t, user);
	}
	
	/**
	 * 
	 * @����:�����ĵ���
	 * @���ߣ�945
	 * @���ڣ�2014-1-3 ����10:04:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getResultAllList(JqlxModel t, User user)
		throws Exception {
		return dao.getResultAllList(t, user);
	}
	
	/**
	 * ��˵���
	 */
	public List<HashMap<String, String>> getResultAllListSqsh(JqlxModel t, User user) throws Exception {
		return dao.getResultAllListSqsh(t, user);
	}

	/** 
	 * @����:TODO��ӡ��У�����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2015-12-24 ����03:40:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsjbxx
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File printForWord(HashMap<String, String> xsjbxx) {
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.putAll(xsjbxx);
		return getWord(data);
	}

	/** 
	 * @����:�õ����ص�word�ļ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2015-12-24 ����03:44:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return
	 * File �������� 
	 * @throws 
	 */
	File getWord(Map<String, Object> data) {
		String templatePath = Constants.TEP_DIR+"rcsw/jqlx_"+Base.xxdm+".xml";
		
		File wordFile = null;
		String filename=data.get("xh") +"["+data.get("xm")+"]";
		if("12309".equals(Base.xxdm)){
			filename="��ʱͨ��֤";
		}
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "jqlx_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(filename));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "jqlx.xml", FreeMarkerUtil.getFileName(filename));
		}

		return wordFile;
	}
	
	/** 
	 * @����:�õ���Уԭ���б�����ѧԺ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-15 ����09:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLxyyList(){
		return dao.getLxyyList();
	}
	public List<HashMap<String, String>> getLxtjList(){
		return dao.getLxtjList();
	}
	/**
	 * @����:��ȡ����У���������ƣ��㽭��ýѧԺ��
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-4-27 ����10:26:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> LsxqList(){
		return dao.getLsxqList();
	}
	
	/**
	 * 
	 * @����: ��ȡ��У���������б��´���Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-10 ����04:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxsqList(){
		return dao.getLxsqList();
	}
	
	public List<HashMap<String, String>> getJgdcList(String id){
		return dao.getJgdcList(id);
	}
	
	/**
	 * @description	�� ��ȡ԰���б�
	 * @author 		�� ������1282��
	 * @date 		��2017-12-23 ����11:19:38
	 * @return
	 */
	public List<HashMap<String,String>> getYqList(){
		return dao.getYqList();
	}

	public String importData_10344(HttpServletRequest request,
			HttpServletResponse response) {
		return dao.importData_10344(request,response);
	}

	public String getKnsxx(String xh) {
		return dao.getKnsxx(xh);
	}

}

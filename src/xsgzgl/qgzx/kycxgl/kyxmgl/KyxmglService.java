/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:40:07 
 */
package  xsgzgl.qgzx.kycxgl.kyxmgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KyxmglService extends SuperServiceImpl<KyxmglForm, KyxmglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String KYCX_XMLB_KYGl = "kygl";
	private KyxmglDao dao = new KyxmglDao();
	
	public void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new ArrayList<HashMap<String, String>>();
			bmList=dao.getBmList();
		request.setAttribute("bmList", bmList);
		request.setAttribute("xmsxList", dao.getXmsxList());
		
	}
	public List<HashMap<String, String>> getPageListOfSh(KyxmglForm t, User user) throws Exception{
		return dao.getPageListOfSh(t,user);
	}
	
	public List<HashMap<String, String>> getKycxList(String xh) throws Exception{
		return dao.getKycxList(xh);
	}
	/**
	 * @throws Exception
	 * 
	 * @����:������Ŀ������ӱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����09:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 * @throws
	 */
	public boolean editKyxmgl(KyxmglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xmid = UniqID.getInstance().getUniqIDHash();
			model.setXmid(xmid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	public boolean isHaveKyxm(KyxmglForm model) {
		return dao.isHaveKyxm(model);
	}
	


	/**
	 * @throws Exception
	 * 
	 * @����:������Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKyxmgl(KyxmglForm t) throws Exception {
		return dao.getKyxmgl(t);
	}

	
	/**
	 * ��ȡ��Ŀ��Ա�б�
	 */
	public List<HashMap<String,String>> getCyList(String xmdm) throws Exception {
		return dao.getCyList(xmdm);
	}
	/**
	 * 
	 * @����:ָ����ʦ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-1 ����05:59:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getTeaList(String xmdm) throws Exception {
		return dao.getTeaList(xmdm);
	}
	
	public List<HashMap<String,String>> getYsxxList(String xmdm) throws Exception {
		return dao.getYsxxList(xmdm);
	}
	/**
	 * 
	 * @����:����״̬�����¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-4 ����10:15:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBgztList(String xmdm) throws Exception {
		return dao.getBgztList(xmdm);
	}
	/**
	 * 
	 * @����:����״̬�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-4 ����10:35:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYxztList() throws Exception {
		return dao.getYxztList();
	}
	
	public List<HashMap<String,String>> getXmfyList(String xmdm) throws Exception {
		return dao.getXmfyList(xmdm);
	}
	
	
	
	public List<HashMap<String, String>> showStudents(KyxmglForm model, User user) throws Exception {
		return dao.showStudents(model, user);

	}
	public List<HashMap<String, String>> getXsxxList(KyxmglForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)||null==xhArr){
			model.setXhs(new String[]{});
		}
		else{
			model.setXhs(xhArr.split(","));
		}
		return dao.getXsxxList(model, user);

	}
	public List<HashMap<String, String>> getTeaList(KyxmglForm model,String zghArr) throws Exception {
		if("".equals(zghArr)||null==zghArr){
			model.setZghs(new String[]{});
		}
		else{
			model.setZghs(zghArr.split(","));
		}
		return dao.getTeaList(model);

	}
	
	public boolean editXmwh(KyxmglForm model,User user,List<KyxmglXsxxForm> xsxxList, List<KyxmglJsxxForm> jsxxList,List<KyxmglJfysForm> jfysList) throws Exception {
		boolean result = true;
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID(KYCX_XMLB_KYGl);
			model.setSplcid(splc);
			result = runUpdate(model);
			
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmid(), splc, user.getUserName(), "qgzx_kycx_kygl_kyxmsh.do", "qgzx_kycx_kygl_kyxmsq.do");
			}
		}else{
			result=dao.runUpdate(model);
			}
		 //��ɾ���
		 dao.delJs(model.getXmid());// ɾ��ָ����ʦ
		 dao.delXs(model.getXmid());// ɾ����Ա
		 dao.delYs(model.getXmid());// ɾ��Ԥ��
		List<String[]> xsList = new ArrayList<String[]>();
		List<String[]> jsList = new ArrayList<String[]>();
		List<String[]> jfList = new ArrayList<String[]>();
		String[] xsxx = null;
		String[] jsxx = null;
		String[] jfxx = null;
		for (KyxmglXsxxForm xsForm : xsxxList) {
			xsxx = new String[4];
			xsxx[0] = model.getXmid();
			xsxx[1] = xsForm.getXh();
			xsxx[2] = xsForm.getXmfg();
			xsxx[3] = xsForm.getSfsfs();
			xsList.add(xsxx);
		}
		for (KyxmglJsxxForm jsForm : jsxxList) {
			jsxx = new String[4];
			jsxx[0] = model.getXmid();
			jsxx[1] = jsForm.getZgh();
			jsxx[2] = jsForm.getZc();
			jsxx[3] = jsForm.getYjfx();
			jsList.add(jsxx);
		}
		for (KyxmglJfysForm jsForm : jfysList) {
			jfxx = new String[4];
			jfxx[0] = model.getXmid();
			jfxx[1] = jsForm.getZclb();
			jfxx[2] = jsForm.getYsje();
			jfxx[3] = jsForm.getZyyt();
			jfList.add(jfxx);
		}
		if (result) {
			//��������
			dao.xsPlbc(xsList);
			dao.jsPlbc(jsList);
			dao.ysPlbc(jfList);
		}
		return result;

	}
	public boolean editCywh(KyxmglForm model,List<KyxmglXsxxForm> xsxxList, List<KyxmglJsxxForm> jsxxList) throws Exception {
		boolean result = true;
		
		 //��ɾ���
		 dao.delJs(model.getXmid());// ɾ��ָ����ʦ
		 dao.delXs(model.getXmid());// ɾ����Ա
		 
		List<String[]> xsList = new ArrayList<String[]>();
		List<String[]> jsList = new ArrayList<String[]>();
		
		String[] xsxx = null;
		String[] jsxx = null;
		
		for (KyxmglXsxxForm xsForm : xsxxList) {
			xsxx = new String[4];
			xsxx[0] = model.getXmid();
			xsxx[1] = xsForm.getXh();
			xsxx[2] = xsForm.getXmfg();
			xsxx[3] = xsForm.getSfsfs();
			xsList.add(xsxx);
		}
		for (KyxmglJsxxForm jsForm : jsxxList) {
			jsxx = new String[4];
			jsxx[0] = model.getXmid();
			jsxx[1] = jsForm.getZgh();
			jsxx[2] = jsForm.getZc();
			jsxx[3] = jsForm.getYjfx();
			jsList.add(jsxx);
		}
		
		if (result) {
			//��������
			dao.xsPlbc(xsList);
			dao.jsPlbc(jsList);
		}
		return result;

	}
	public boolean submitXmwh(KyxmglForm model,User user) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID(KYCX_XMLB_KYGl);
			model.setSplcid(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmid(), splc,user.getUserName() , "qgzx_kycx_kygl_kyxmsh.do", "qgzx_kycx_kygl_kyxmsq.do");
			}
			return result;
	}
	
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public boolean editFywh(KyxmglForm model, List<KyxmglXmfyForm> fywhList) throws Exception {
		boolean result = true;
		dao.runUpdate(model);
		 //��ɾ���
		 dao.delXmfy(model.getXmid());// ɾ����Ŀ����ά��
		List<String[]> fyList = new ArrayList<String[]>();
		
		String[] fyxx = null;
		
		for (KyxmglXmfyForm fyForm : fywhList) {
			fyxx = new String[6];
			fyxx[0] = model.getXmid();
			fyxx[1] = fyForm.getFylb();
			fyxx[2] = fyForm.getFymc();
			fyxx[3] = fyForm.getFyje();
			fyxx[4] = fyForm.getBxsj();
			fyxx[5] = fyForm.getBz();
			fyList.add(fyxx);
		}
		if (result) {
			//��������
			dao.fywhPlbc(fyList);
		}
		return result;

	}
	/**
	 * 
	 * @����:״̬���ñ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-4 ����10:56:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean ztsz(KyxmglForm model) throws Exception {
		boolean result = true;
		result=dao.runUpdate(model);
		result=dao.insertZtBg(model);
		return result;

	}
	
	public boolean saveSh(KyxmglForm form, User user) {
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
		model.setZd1("����ȼ�");
		model.setZd2(form.getLxdj());
		model.setZd3(form.getLxdj()=="1"?"�ص���Ŀ":"һ����Ŀ");
		model.setZd4("��׼����");
		model.setZd6(form.getPzjf());
		
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getXmid());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_kycx_kygl_kyxmsh.do");
		model.setTzljsq("qgzx_kycx_kygl_kyxmsq.do");
		boolean reuslt = true;
		try {
			String zhzt = shlc.runAuditing(model);
			KyxmglForm xmForm = new KyxmglForm();
			xmForm.setXmid(form.getXmid());
				xmForm.setShzt(zhzt);
				xmForm.setLxdj(form.getLxdj());
				xmForm.setPzjf(form.getPzjf());
				reuslt = dao.runUpdate(xmForm);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����03:22:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return String ��������
	 * @throws
	 */
	public String savePlsh(KyxmglForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			t.setXmid(ids[i]);
			Map<String, String> result = dao.getKyxmgl(t);
			KyxmglForm model = new KyxmglForm();
			model.setSplcid(result.get("splcid"));
			model.setLxdj(t.getLxdj());
			model.setPzjf(t.getPzjf());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setXmid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	public boolean cancel(KyxmglForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm);
		return result;
	}
	
	public String cxshnew(String ywid, KyxmglForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();

		// �ж��������
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(ywid, user.getUserName(), model.getShlc(), "cx");

		// ���ǰһ��������ȼ�����׼����
		String lxdj = shxx.get("zd2") == null ? "" : shxx.get("zd2");
		String pzjf = shxx.get("zd6") == null ? "" : shxx.get("zd6");
		String shzt = Constants.YW_SHZ;
		model.setShzt(shzt);
		model.setXmid(ywid);
		model.setLxdj(lxdj);
		model.setPzjf(pzjf);
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		// �ع�������Ŀ������ȼ�
		dao.runUpdate(model);
		return cancelFlag;

	}
}

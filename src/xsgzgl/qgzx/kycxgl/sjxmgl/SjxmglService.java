/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:40:07 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl;

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

public class SjxmglService extends SuperServiceImpl<SjxmglForm, SjxmglDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String KYCX_XMLB_SJGl = "sjgl";
	private SjxmglDao dao = new SjxmglDao();
	
	public void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new ArrayList<HashMap<String, String>>();
			bmList=dao.getBmList();
		request.setAttribute("bmList", bmList);
		request.setAttribute("xmsxList", dao.getXmsxList());
		request.setAttribute("gwlbList", dao.getGwlbList());
		
	}
	public List<HashMap<String, String>> getPageListOfSh(SjxmglForm t, User user) throws Exception{
		return dao.getPageListOfSh(t,user);
	}
	/**
	 * @throws Exception
	 * 
	 * @����:ʵ����Ŀ������ӱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����09:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 * @throws
	 */
	public boolean editSjxmgl(SjxmglForm model,List<SjxmglGwxxForm> gwxxList) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xmid = UniqID.getInstance().getUniqIDHash();
			model.setXmid(xmid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		 //��ɾ���
		 dao.delGw(model.getXmid());// ɾ����λ
	
		List<String[]> gwList = new ArrayList<String[]>();
	
		String[] xsxx = null;
		
		for (SjxmglGwxxForm gwForm : gwxxList) {
			xsxx = new String[4];
			xsxx[0] = model.getXmid();
			xsxx[1] = gwForm.getGwlb();
			xsxx[2] = gwForm.getGwgzzy();
			xsxx[3] = gwForm.getZcyrs();
			gwList.add(xsxx);
		}
		if (result) {
			//��������
			dao.gwPlbc(gwList);
		}
		return result;
		
	}
	
	public boolean isHaveSbjg(SjxmglForm model) {
		return dao.isHaveSbjg(model);
	}


	/**
	 * @throws Exception
	 * 
	 * @����:ʵ����Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-30 ����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getSjxmgl(String xmid,String ffyf) throws Exception {
		return dao.getSjxmgl(xmid,ffyf);
	}
	
	public HashMap<String,String> getSjxm(String xmid) throws Exception {
		return dao.getSjxm(xmid);
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
	
	public List<HashMap<String,String>> getGwxxList(String xmdm) throws Exception {
		return dao.getGwxxList(xmdm);
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
	
	
	
	public List<HashMap<String, String>> getXsxxList(SjxmglForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)||null==xhArr){
			model.setXhs(new String[]{});
		}
		else{
			model.setXhs(xhArr.split(","));
		}
		return dao.getXsxxList(model, user);

	}
	public List<HashMap<String, String>> getTeaList(SjxmglForm model,String zghArr) throws Exception {
		if("".equals(zghArr)||null==zghArr){
			model.setZghs(new String[]{});
		}
		else{
			model.setZghs(zghArr.split(","));
		}
		return dao.getTeaList(model);

	}
	
	public boolean editXmwh(SjxmglForm model,User user,List<SjxmglXsxxForm> xsxxList, List<SjxmglJsxxForm> jsxxList) throws Exception {
		boolean result = true;
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID(KYCX_XMLB_SJGl);
			model.setSplcid(splc);
			result = runUpdate(model);
			
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmid(), splc, user.getUserName(), "qgzx_kycx_sjgl_sjxmsh.do", "qgzx_kycx_sjgl_sjxmsq.do");
			}
		}else{
			result=dao.runUpdate(model);
			}
		 //��ɾ���
		 dao.delJs(model.getXmid());// ɾ��ָ����ʦ
		 dao.delXs(model.getXmid());// ɾ����Ա
		List<String[]> xsList = new ArrayList<String[]>();
		List<String[]> jsList = new ArrayList<String[]>();
		String[] xsxx = null;
		String[] jsxx = null;
		for (SjxmglXsxxForm xsForm : xsxxList) {
			xsxx = new String[4];
			xsxx[0] = model.getXmid();
			xsxx[1] = xsForm.getXh();
			xsxx[2] = xsForm.getXmfg();
			xsxx[3] = xsForm.getXmnzt();
			xsList.add(xsxx);
		}
		for (SjxmglJsxxForm jsForm : jsxxList) {
			jsxx = new String[4];
			jsxx[0] = model.getXmid();
			jsxx[1] = jsForm.getZgh();
			jsxx[2] = jsForm.getZc();
			jsxx[3] = jsForm.getYjfx();
			jsList.add(jsxx);
		}
		if (result) {
			//��������
			dao.jsPlbc(jsList);
			dao.xsPlbc(xsList);
		}
		return result;

	}
	public boolean editCywh(SjxmglForm model,List<SjxmglXsxxForm> xsxxList, List<SjxmglJsxxForm> jsxxList) throws Exception {
		boolean result = true;
		 //��ɾ���
		 dao.delJs(model.getXmid());// ɾ��ָ����ʦ
		 dao.delXs(model.getXmid());// ɾ����Ա
		 
		List<String[]> xsList = new ArrayList<String[]>();
		List<String[]> jsList = new ArrayList<String[]>();
		
		String[] xsxx = null;
		String[] jsxx = null;
		
		for (SjxmglXsxxForm xsForm : xsxxList) {
			xsxx = new String[4];
			xsxx[0] = model.getXmid();
			xsxx[1] = xsForm.getXh();
			xsxx[2] = xsForm.getXmfg();
			xsxx[3] = xsForm.getXmnzt();
			xsList.add(xsxx);
		}
		for (SjxmglJsxxForm jsForm : jsxxList) {
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
	public boolean submitXmwh(SjxmglForm model,User user) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID(KYCX_XMLB_SJGl);
			model.setSplcid(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmid(), splc,user.getUserName() , "qgzx_kycx_sjgl_sjxmsh.do", "qgzx_kycx_sjgl_sjxmsq.do");
			}
			return result;
	}
	
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
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
	public boolean ztsz(SjxmglForm model) throws Exception {
		boolean result = true;
		result=dao.runUpdate(model);
		return result;

	}
	
	public boolean saveSh(SjxmglForm form, User user) {
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

		model.setZd1("��׼����");
    	model.setZd3(form.getPzjf());
		
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getXmid());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_kycx_sjgl_sjxmsh.do");
		model.setTzljsq("qgzx_kycx_sjgl_sjxmsq.do");
		boolean reuslt = true;
		try {
			String zhzt = shlc.runAuditing(model);
			SjxmglForm xmForm = new SjxmglForm();
			xmForm.setXmid(form.getXmid());
			
				xmForm.setShzt(zhzt);
				//xmForm.setLxdj(form.getLxdj());
				xmForm.setPzjf(form.getPzjf());
				reuslt = dao.runUpdate(xmForm);
		}
		 catch (Exception e) {
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
	public String savePlsh(SjxmglForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			t.setXmid(ids[i]);
			Map<String, String> result = dao.getSjxmgl(t.getXmid(),"");
			SjxmglForm model = new SjxmglForm();
			model.setSplcid(result.get("splcid"));
			//model.setLxdj(t.getLxdj());
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
	
	public boolean cancel(SjxmglForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm);
		return result;
	}
	
	public String cxshnew(String ywid, SjxmglForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();

		// �ж��������
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(ywid, user.getUserName(), model.getShlc(), "cx");

		// ���ǰһ��������ȼ�����׼����
		String lxdj = shxx.get("zd2") == null ? "" : shxx.get("zd2");
		String pzjf = shxx.get("zd6") == null ? "" : shxx.get("zd6");
		String shzt = Constants.YW_SHZ;
		model.setShzt(shzt);
		model.setXmid(ywid);
		//model.setLxdj(lxdj);
		model.setPzjf(pzjf);
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		// �ع�ʵ����Ŀ������ȼ�
		dao.runUpdate(model);
		return cancelFlag;

	}
	
	public List<HashMap<String,String>> getGwlbList()throws Exception{
	return dao.getGwlbList();
	}
}

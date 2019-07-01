/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:40:07 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl.fysb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
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

public class SjxmFysbService extends SuperServiceImpl<SjxmFysbForm, SjxmFysbDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String KYCX_XMLB_SJGl = "sjgl";
	private SjxmFysbDao dao = new SjxmFysbDao();
	
	public void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new ArrayList<HashMap<String, String>>();
			bmList=dao.getBmList();
		request.setAttribute("bmList", bmList);
		request.setAttribute("xmsxList", dao.getXmsxList());
		request.setAttribute("gwlbList", dao.getGwlbList());
		
	}
	public List<HashMap<String, String>> getPageListOfFyff(SjxmFysbForm t, User user) throws Exception{
		return dao.getPageListOfFyff(t,user);
	}
	
	public List<HashMap<String, String>> ffcx(SjxmFysbForm model,User user) throws Exception {
		return dao.ffcx(model,user);
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
	public boolean editFysb(SjxmFysbForm model,List<SjxmglXsxxForm> sbxxList) throws Exception {
		boolean result = true;
		String sbid=UniqID.getInstance().getUniqIDHash();
		model.setSbid(sbid);
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_TG);// �����
		}else{
			model.setShzt(Constants.YW_WTJ);
		}
		dao.runInsert(model);
		 //ɾ�����÷�����Ϣ
		 dao.delFyff(model);// �ϱ���Ϣ
		List<String[]> ffList = new ArrayList<String[]>();
		String[] ffxx = null;
		for (SjxmglXsxxForm sbForm : sbxxList) {
			ffxx = new String[6];
			ffxx[0] = model.getSbid();
			ffxx[1] = model.getXmid();
			ffxx[2] = sbForm.getXh();
			ffxx[3] = model.getSbyf();
			ffxx[4] = sbForm.getGs();
			ffxx[5] = sbForm.getCjje();
			ffList.add(ffxx);
		}
		if (result) {
			//��������
			dao.ffPlbc(ffList);
		}
		return result;
	}
	
	public boolean saveEditFysb(SjxmFysbForm model,List<SjxmglXsxxForm> sbxxList) throws Exception {
		boolean result = true;
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_TG);// �����
		}
		dao.runUpdate(model);
		 //ɾ�����÷�����Ϣ
		 dao.delFyff(model);// �ϱ���Ϣ
		List<String[]> ffList = new ArrayList<String[]>();
		String[] ffxx = null;
		for (SjxmglXsxxForm sbForm : sbxxList) {
			ffxx = new String[6];
			ffxx[0] = model.getSbid();
			ffxx[1] = model.getXmid();
			ffxx[2] = sbForm.getXh();
			ffxx[3] = model.getSbyf();
			ffxx[4] = sbForm.getGs();
			ffxx[5] = sbForm.getCjje();
			ffList.add(ffxx);
		}
		if (result) {
			//��������
			dao.ffPlbc(ffList);
		}
		return result;
	}



	
	/**
	 * ��ȡ��Ŀ��Ա�б�
	 */
	public List<HashMap<String,String>> getCyList(String xmdm,String ffny) throws Exception {
		return dao.getCyList(xmdm,ffny);
	}
	public boolean submitFysb(SjxmFysbForm model,User user) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_TG);// �����
			result = runUpdate(model);
			return result;
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
	
	public List<HashMap<String,String>> getSbxmList(User user) throws Exception {
		return dao.getSbxmList(user);
	}
	
	public List<HashMap<String, String>> getFfyfList(String xmid) throws Exception {
		return dao.getFfyfList(xmid);
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
	/**
	 * 
	 * @����:ɾ����Ŀ�����걨
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-12 ����11:36:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delFysb(SjxmFysbForm myForm, String values) throws Exception {
		 dao.runDelete(values.split(","));
		return dao.delFyff(values.split(","));
	}

	
	public boolean cancel(SjxmFysbForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_WTJ);
		boolean result = dao.runUpdate(myForm);
		return result;
	}
	
	
	public List<HashMap<String,String>> getGwlbList()throws Exception{
	return dao.getGwlbList();
	}
}

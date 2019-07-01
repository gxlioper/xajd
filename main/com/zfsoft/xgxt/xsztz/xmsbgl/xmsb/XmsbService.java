/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����05:04:19 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����05:04:19
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmsbService extends SuperServiceImpl<XmsbForm, XmsbDao> {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	XmsbDao dao = new XmsbDao();

	/**
	 * 
	 * @����:��ȡ��Ŀ��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����09:02:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getXmxx(String xmdm) {
		return dao.getXmxx(xmdm);
	}

	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����02:26:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveXmsb(XmsbForm model,List<XmjxForm> jxxxList,String[] cyxyArr) throws Exception {
		String xmdm = UniqID.getInstance().getUniqIDHash();
		model.setXmdm(xmdm);
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		// ����������Ϣ
		boolean result = dao.runInsert(model);
		//�������ѧԺ��Ϣ
		cyxyPlbc(cyxyArr,xmdm);
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(xmdm, splc, model.getXmdm(), "sztz_xmsbgl_xmsh.do", "sztz_xmsbgl_xmsb.do");
			}
		}
		return jxxxPlbc(model, jxxxList);
	}
	/**
	 * 
	 * @����:������Ŀ������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����03:08:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param jxxxList
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	private boolean jxxxPlbc(XmsbForm model, List<XmjxForm> jxxxList) throws SQLException {
		List<String[]> xmxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		String jgid = null;
		if(null==jxxxList){
			return true;
		}
		for (XmjxForm xmjxForm : jxxxList) {
			
			jxxx = new String[5];
			jgid=UniqID.getInstance().getUniqIDHash();
			jxxx[0] = jgid;
			jxxx[1] = model.getXmdm();
			jxxx[2] = xmjxForm.getFjxf();
			jxxx[3] = xmjxForm.getJxmc();
			jxxx[4] = xmjxForm.getXssx();
		
			xmxxList.add(jxxx);
		}
		return dao.jxxxPlbc(xmxxList);
		
	}

	/**
	 * 
	 * @����:��Ŀ�ϱ��޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����02:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveEditXmsb( XmsbForm model,List<XmjxForm> jxxxList,String[] cyxyArr) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmdm(), splc, model.getXmdm(), "sztz_xmsbgl_xmsh.do", "sztz_xmsbgl_xmsb.do");
			}
		} else {
			result = runUpdate(model);
		}
		//�������ѧԺ��Ϣ
		cyxyPlbc(cyxyArr,model.getXmdm());
		//ɾ����Ŀ������Ϣ���ٲ���
		result = dao.delXmjx(model.getXmdm());
		
		return jxxxPlbc(model, jxxxList);
	
	}
	/**
	 * 
	 * @����:��Ŀ�ϱ��ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����03:17:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitXmsb(XmsbForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmdm(), splc, model.getXmdm(), "sztz_xmsbgl_xmsh.do", "sztz_xmsbgl_xmsb.do");
			}
			return result;
	}

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����03:02:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveSbJl(XmsbForm model, String czlx) throws Exception {
		return dao.isHaveSbJl(model, czlx);
	}
    /**
     * 
     * @����:��ʼ�������б�
     * @���ߣ�����[���ţ�1104]
     * @���ڣ�2015-7-10 ����10:59:11
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param request
     * @param user
     * @throws Exception
     * void �������� 
     * @throws
     */
	public void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> dwMap = new HashMap<String,String>();
		if(!"xx".equals(user.getUserStatus())){
			dwMap.put("xydm", user.getUserDep());
			dwMap.put("xymc", dao.getBmmc(user.getUserDep()));
			bmList.add(dwMap);
		}else{
			bmList=dao.getBmList();
		}
		request.setAttribute("sbrxm", user.getRealName());
		request.setAttribute("xyList", Base.getXyNewList());
		request.setAttribute("bmList", bmList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("yhbmdm", user.getUserDep());
		request.setAttribute("xmjbList", dao.getXmjbList());
		request.setAttribute("xmkmList", dao.getXmkmList());
		request.setAttribute("bkgsList", dao.getBkgsList());
		
	}
	public List<HashMap<String, String>> getXmjxList(String xmdm) {
		return dao.getXmjxList(xmdm);

	}
	public List<HashMap<String, String>> getCyxyListForView(String xmdm) {
		return dao.getCyxyListForView(xmdm);

	}
	/**
	 * 
	 * @����:ɾ����Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-14 ����03:07:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delPlXmjx(String[] ids) throws Exception {
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[1];
			idArr[0]=ids[i];
			jxxxList.add(idArr);
		}
		return dao.delPlXmjx(jxxxList);
	}
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @����:����ѧԺ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-18 ����05:36:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cyxyArr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean cyxyPlbc(String[] cyxyArr,String xmdm) throws Exception{
		List<String[]> cyxyList = new ArrayList<String[]>();
		String[] cyxys = null;
		if(null==cyxyArr||"".equals(cyxyArr[0])){
			return true;
		}
		for (String cyxy : cyxyArr) {
			
			cyxys = new String[2];
			cyxys[0] = xmdm;
			cyxys[1] = cyxy;
		
			cyxyList.add(cyxys);
		}
		dao.delCyxy(xmdm);
		return dao.cyxyPlbc(cyxyList);
		
	}
		
	}


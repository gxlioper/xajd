/**
s * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: �������service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjsqService extends SuperServiceImpl<QjsqForm, QjsqDao> {
	// ��������� �ύ״̬
	public static String _SQZT_TJZT = "1";
	public static String _SQZT_CGZT="0";
	//�����Ϣ��ʦ����
	public static String _QJXX_LSCZ="1";
	//����ʦ���������Ϣ����Ȩ���޸� ɾ����
	public static String _QJXX_FLSCZ="0";
	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID="-1";
	QjsqDao dao = new QjsqDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public QjsqService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean save(QjsqForm model) throws Exception {
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		HashMap<String, String> hm = getSplc(model);
		/**
		 * ���ڽ�������
		 */
		if (null==hm) {
			model.setSplcid("�������");
		}else{
			model.setSplcid(hm.get("splcid"));
		}
		
		//��ʦ����Ի���ٱ��
		if("10511".equals(Base.xxdm)){
			String qjbh = dao.getQjbh(model.getSqsj());
			model.setQjbh(qjbh);
		}
		boolean result = true;
		// ��Ҫ��˵�,�Ҳ�����ʦ����
		if ((StringUtils.isNull(model.getAddtype())||!model.getAddtype().equals(_QJXX_LSCZ))) {
			model.setShzt(Constants.YW_WTJ);
		} else {
			model.setShzt("");
/*			// 1����������ʦ����
			if (isAddToQjjg(model)) {
				// ��ȡ���ݿ���������
				QjjgForm qf = new QjjgForm();
				QjjgService qs = new QjjgService();
				// ��Ӧ���Ը��Ƶ������
				BeanUtils.copyProperties(qf, StringUtils.formatData(model));
				qf.setQjzt(_QJXX_FLSCZ);
				result = qs.save(qf);
			}*/
		}
		if(StringUtil.isNull(model.getQjsqid())){
			model.setQjsqid(UniqID.getInstance().getUniqIDHash());
			result = super.runInsert(model);
		} else {
			result = super.runUpdate(model);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @����: ���������ϸ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-11-25 ����01:52:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param mxrq
	 * @param mxxmList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQjmx(QjsqForm model,String[] mxrq,List<String[]> mxxmList) throws Exception{
		
		List<String[]> params = new ArrayList<String[]>();
		
		for (int i = 0 ; i < mxxmList.size() ; i++){
			if (mxxmList.get(i) != null){
				
				String[] param = new String[]{model.getQjsqid(),mxrq[i],StringUtils.joinArr(mxxmList.get(i))};
				params.add(param);
			}
		}
		
		dao.delQjmx(model.getQjsqid());
		return dao.saveQjmx(params);
	}
	
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-18 ����05:03:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return boolean �������� 
	 * @throws Exception 
	 */
	public boolean tj(QjsqForm qjsq) throws Exception{
		
		QjsqForm t = getModel(qjsq);		
		//BeanUtils.copyProperties(t, qjsq);	modified by xiaxia 2014-12-19 getModel()�ѻ�ȡֵ
		t.setSplcid(qjsq.getSplcid());	
		t.setQjzt(_SQZT_TJZT);
		if ("�������".equals(t.getSplcid())) {
			t.setShzt("");
		}
		boolean insertResult= runUpdate(t);
		if(insertResult){
			//������������ͬ���������
			if ("�������".equals(t.getSplcid())) {
				// ��ȡ���ݿ���������
				QjjgForm qf = new QjjgForm();
				QjjgService qs = new QjjgService();
				// ��Ӧ���Ը��Ƶ������
				BeanUtils.copyProperties(qf, StringUtils.formatData(t));
				qf.setQjzt(_QJXX_FLSCZ);
				insertResult= qs.save(qf);
			}else{
				//�����ύʱ�Ų����������
				if (insertResult) {
					// �����������
					insertResult = shlc.runSubmit(t.getQjsqid(), t.getSplcid(),t.getXh(),"qjshbase.do","qjsqbase.do");
					t.setShzt(Constants.YW_SHZ);
					runUpdate(t);
				}
			}
		}
		return insertResult;
	}
	@Override
	public QjsqForm getModel(QjsqForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// ��ѧ�ڴ���ת��Ϊѧ������
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		if(Base.xxdm.equals("10695")){//���������ѧר��
			t.setJtgjmc(getJtgjmc(t.getJtgj()));
		}
		return t;
	}
	/**
	 * 
	 * @����:�Ƿ���Ҫͬ���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-16 ����04:11:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean �������� 
	 */
	private boolean isAddToQjjg(QjsqForm model){
		if(StringUtils.isNull(model.getQjzt())){
			return false;
		}
		//�ǲݸ�״̬
		if(model.getQjzt().equals(_SQZT_CGZT)){
			return false;
		}
		//����ʦ����
		if(StringUtils.isNotNull(model.getAddtype())&&model.getAddtype().equals(_QJXX_LSCZ)){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-14 ����07:17:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return int �������� 
	 * @throws Exception
	 */
	private int qjsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	/**
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:31:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return String[] ��������   String����[0]Ϊ�ɹ�ɾ������ [1]Ϊ����ɾ����ʾ��Ϣ
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception 
	 */
	public String[] delete(String[] ids) throws Exception{
		return delete(ids,false);
	}
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-16 ����07:40:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids ɾ��id����
	 * @param isQjjg �Ƿ��ǽ�������
	 * @return String[] �������� 
	 * @throws Exception
	 */
	public String[] delete(String[] ids,boolean isQjjg) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		List<String> delSqId=new ArrayList<String>();//��Ӧ��ɾ������������id
		
		StringBuffer noDel=new StringBuffer();
		StringBuffer notFound=new StringBuffer();

		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			QjsqForm qf=getModel(str);
			if(dao.isCanDel(str)||isQjjg){
				delId.add(str);//��¼ɾ��id
				if(null!=qf){
				delSqId.add(qf.getQjsqid());
				}
			}else{
				HashMap<String, String> hm=dao.getQjsq(str);
				if(null==hm||hm.size()<=0){
					notFound.append("ѧ��");
					notFound.append("&nbsp;");
					notFound.append(qf.getXh());
					notFound.append(",</br>");
					continue;
				}
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xn"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xqmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		if(notFound.length()>0){
			//ȥ����β����
			notFound.delete(notFound.length()-",</br>".length(),notFound.length());
			notFound.append("</br>");
			notFound.append("<font color='black'>�Ѿ������ڣ�</font></br>");
		}
		notFound.append(noDel);//�ϲ���Ϣ
		int i=delId.size()>0?qjsqDelete(delId.toArray(new String[]{})):0;
		String str=notFound.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		if(!_BCZSCID.equals(str)){
			str=str.substring(0, str.length()-",</br>".length());
		}
		return new String[]{String.valueOf(i),str};
	}
	/**
	 * 
	 * @����:��������޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����04:53:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean update(QjsqForm model) throws Exception {
		boolean result = true;
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
//		HashMap<String, String> hm = getSplc(model);
//		if (null==hm) {
//			model.setSplcid("�������");
//		}else{
//			model.setSplcid(hm.get("splcid"));
//		}
		// ��Ҫ��˵�,�Ҳ�����ʦ����
		if (!"�������".equals(model.getSplcid())&&(StringUtils.isNull(model.getAddtype())||!model.getAddtype().equals(_QJXX_LSCZ))) {
			//model.setShzt(Constants.YW_WTJ);
		} else {
			model.setShzt("");
			// 1����������ʦ����
			if (isAddToQjjg(model)) {
				// ��ȡ���ݿ���������
				QjjgForm qf = new QjjgForm();
				QjjgService qs = new QjjgService();
				// ��Ӧ���Ը��Ƶ������
				BeanUtils.copyProperties(qf, StringUtils.formatData(model));
				dao.deletQjjgForQjsqid(model.getQjsqid());
				qf.setQjzt(_QJXX_FLSCZ);
				result = qs.save(qf);
			}
		}
		boolean insertResult = super.runUpdate(model);
		//������ύ״̬�򲻴���������Ϣ
		if (insertResult && result&&model.getQjzt().equals(_SQZT_TJZT)) {
			shlc.deleteShjl(model.getQjsqid());
			result = shlc.runSubmit(model.getQjsqid(), model.getSplcid(),model.getXh(),"qjshbase.do","qjsqbase.do");
			model.setShzt(Constants.YW_SHZ);
			runUpdate(model);
		}
		return result;
	}

	/**
	 * 
	 * @����:��ȡ��Ӧ����ٹ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����04:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param isShow �Ƿ�����ʾģʽ
	 * @return  String ��������
	 * @throws Exception
	 */
	public HashMap<String, String> getSplc(QjsqForm model,boolean isShow) throws Exception {
		HashMap<String,String> data=null;
		//�������ʾ��ȥ��֤����������ʾ�����Ӧ�����Ѿ��޸ģ�
		//by yxy ����ԭ��ע�ͣ��鿴ʱisShow��Ϊtrue
		if(isShow){
			data= new HashMap<String, String>();
			data.put("splcid", "�������");
			// ��ȡ�����Ӧ��ٹ���
			if(StringUtils.isNotNull(model.getSplcid())){//�Ѿ����ڶ�Ӧ����id��ֱ�ӷ���
				data.put("splcid",model.getSplcid());
				return data ;
			}
		}
//		QjgzService qjgz = new QjgzService();
//		List<HashMap<String, String>> list = qjgz.getAllList(new QjgzForm());
		/**
		 * �ֱ�ȡ�������ͺ���������ٹ���List����
		 */
		List<HashMap<String, String>> list = this.getYlxQjgzList();
		List<HashMap<String, String>> wlxlist = this.getWlxQjgzList();
		
	
		/**
		 * ��֤����
		 * ��һ��������֤������ͺ������Ƿ�����ٹ����е���Ŀƥ�䣬������ͺ�����ƥ�������жϣ������λ�ù������������
		 * �ڶ������ڵ�һ�����κ���Ŀƥ�������£������Ƿ��������ƥ�����������Ϊ�յ�ƥ������������λ�ù������������
		 * ���������ڵ�һ���͵ڶ�����ƥ�������£�ֱ�Ӷ�Ϊ"�������";
		 */
		/*ԭ�����ж�*/
		/*for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid()) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid())) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj) && qjlxid.equalsIgnoreCase(model.getQjlxid())) {
				return hm;
			}
		}*/
		//��������ѧԺ����ԭ���Ļ����ϣ����ж�����ѧԺ���Ƿ����
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid()) &&
					ssxydm.equalsIgnoreCase(model.getSsxydm())) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& qjlxid.equalsIgnoreCase(model.getQjlxid())
					&& ssxydm.equalsIgnoreCase(model.getSsxydm())) {
				return hm;
			}
		}
		//���ж�ȫУ��
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid()) &&
					"qx".equalsIgnoreCase(ssxydm)) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& qjlxid.equalsIgnoreCase(model.getQjlxid())
					&& "qx".equalsIgnoreCase(ssxydm)) {
				return hm;
			}
		}
		/*ԭ�����ж�*/
		/*for (HashMap<String, String> hm : wlxlist) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) ) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj) ) {
				return hm;
			}
		}*/
		//��������ѧԺ����ԭ���Ļ����ϣ����ж�����ѧԺ���Ƿ����
		for (HashMap<String, String> hm : wlxlist) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj)
					&& ssxydm.equalsIgnoreCase(model.getSsxydm()) ) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& ssxydm.equalsIgnoreCase(model.getSsxydm()) ) {
				return hm;
			}
		}
		//���ж�ȫУ��
		for (HashMap<String, String> hm : wlxlist) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj)
					&& "qx".equalsIgnoreCase(ssxydm) ) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& "qx".equalsIgnoreCase(ssxydm) ) {
				return hm;
			}
		}
		return data;
	}
	public HashMap<String, String> getSplc(QjsqForm model) throws Exception {
		//Ĭ�ϲ�����ʾģʽ
		return getSplc(model,false);
	}
	/**
	 * 
	 * @����:
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����03:49:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getShxxAndXjxx(
			List<HashMap<String, String>> list) {
		return list;
	}
	/**
	 * 
	 * @����:��ȡ��δ�ύ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-18 ����03:54:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String �������� 
	 * @throws Exception 
	 */
	public String getWtjs(QjsqForm t, User user) throws Exception{
		Integer i=dao.getWtjts(t,user);
		return i.toString();
	}
	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t));
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t, User user)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t, user));
	}

	/**
	 * 
	 * @����:�Ƿ���Խ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����07:19:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 */
	public boolean isCanAdd(QjsqForm qf) {
		return dao.checkQjlx(qf);
	}

	/**
	 * ��ѯѧ��������Ϣ
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}

	/**
	 * 
	 * @����:��ȡ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:44:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getQjlx() {
		return dao.getQjlx();
	}

	/**
	 * 
	 * @����:�Ƿ���ڶ�Ӧ�������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����06:58:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return  boolean ��������
	 * @throws Exception
	 */
	public boolean isHaveSplcForTs(QjsqForm model) throws Exception {
		HashMap<String, String> splc = getSplc(model);
		if (null == splc) {
			return false;
		}
		return true;
	}
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @����:��word��ʽ��ӡ
	 * @���ߣ������[���ţ�1004]
	 * @���ڣ�2013-12-17 ����10:13:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsxx ѧ����Ϣ
	 * @param jtqkmodel  ��ͥ��Ϣ
	 * @param xxmc  ѧУ����
	 * @param knsdc	����������
	 * @param rddc rddc
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File printForWord(HashMap<String, String> xsxx) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsxx == null){
			xsxx = new HashMap<String, String>();
		}
		data.putAll(xsxx);
		return getWord(data);
	}
	/**
	 * @throws FileNotFoundException 
	 * 
	 * @����:��ȡ�ĵ�
	 * @���ߣ������
	 * @���ڣ�2013-12-17 ����10:13:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return File ��������
	 * @throws
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"rcsw/qjsq_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	/**
	 * 
	 * @����:��ʦ����������ӡ����Ա���汨��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-11-22 ����02:26:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File �������� 
	 * @throws
	 */
	public File getWordOfFdy(Map<String, Object> data) throws FileNotFoundException {

		String templatePath = Constants.TEP_DIR+"rcsw/qjsq_fdy_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq_fdy_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	/**
	 * 
	 * @����:��ȡѧ����ǰѧ��ѧ�ڵĿγ�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-11-18 ����02:48:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjkcList(QjsqForm model) throws Exception{
		return dao.getQjkcList(model);
	}
	/**
	 * 
	 * @����:��ȡ��ٿγ���Ϣ
	 * @���ߣ�xiaixa [���ţ�1104]
	 * @���ڣ�2014-11-19 ����10:08:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKcList(String kcbh) throws Exception{
		if(null==kcbh||""==kcbh){
			return new ArrayList<HashMap<String, String>>();
		}else{
		String[] kcbhs = kcbh.split(";");
		return dao.getkcList(kcbhs);
		}
	}
	
	public List<HashMap<String,String>> getQjmxList(String id){
		
		List<HashMap<String,String>> qjmxList = dao.getQjmxList(id);
		
		return qjmxList;
	}
	
	/**
	 * 
	 * @����:��ʦ����Ի���ٱ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-11-25 ����06:51:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqsj
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getQjbh(String sqsj){
		return dao.getQjbh(sqsj);
	}
	//���������ʽ��
	private String qjtsFormat(String qjts) {
		int lastStr = qjts.indexOf(".");
		if (qjts.length() - 1 == lastStr) {
			String[] gsArr = qjts.split("\\.");
			qjts = gsArr[0];
		}
		return qjts;
	}

	public boolean getDupStatusSerivce(QjsqForm model) {
		return dao.getDupStatusDao(model);
	}
	
	/**
	 * 
	 * @����:�й�����ٹ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-30 ����11:31:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYlxQjgzList(){
		return dao.getYlxQjgzList();
	}
	
	/**
	 * 
	 * @����:�޹�����ٹ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-30 ����11:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWlxQjgzList(){
		return dao.getWlxQjgzList();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ĩ���߽ڼ���ѡ��ʱ����ȷ���ж�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-18 ����11:42:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjlxid
	 * @param kssj
	 * @param jssj
	 * @return
	 * String �������� 
	 * @throws
	 */
	public HashMap<String, String> checkJjrOrWeekend(String qjlxid,String kssj,String jssj) throws Exception{
		QjlxForm qjlx = new QjlxService().getModel(qjlxid);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		boolean jjrRs = dao.checkJjr(kssj, jssj);
		
		/**
		 * �����ж���ѡ�������Ƿ�Ϊ�ڼ��գ���������ڼ��ǽڼ��գ�
		 * ��ѡ�������Ƿǽڼ��գ��ж�Ϊfalse,���ش���
		 */
		if(!qjlx.getQjlxmc().equals("��ĩ��") && !qjlx.getQjlxmc().equals("�ڼ��ռ�")){
			return dataMap;
		}else if(qjlx.getQjlxmc().equals("��ĩ��") && jjrRs){
			
				dataMap.put("rs", "false");
				dataMap.put("message", "���������ѡ��ڼ��ռ٣�");
		   
		}else if(qjlx.getQjlxmc().equals("��ĩ��") && !dao.checkWeekDay(kssj, jssj)){
			dataMap.put("rs", "false");
			dataMap.put("message", "��ѡ���ʱ�����ĩʱ�䣡");
		}else if(qjlx.getQjlxmc().equals("��ĩ��") && !dao.checkIsOneWeekend(kssj, jssj)){
			dataMap.put("rs", "false");
			dataMap.put("message", "��ĩ�ٿ�ʼʱ��ͽ���ʱ�������ͬһ�ܣ�");
			
		}else if(!jjrRs && qjlx.getQjlxmc().equals("�ڼ��ռ�")){
		
			dataMap.put("rs", "false");
			dataMap.put("message", "��ѡ���ʱ��ǽڼ���ʱ�䣡");
			
		}else{
			dataMap.put("rs", "true");
		}
		return dataMap;
		
	}
	
	/** 
	 * @����:��ȡ��ͨ��������(���������ѧר��)
	 * @���ߣ�lj[���ţ�1282]
	 * @���ڣ�2017-7-7 ����01:38:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getJtgjmc(String dm) {
		return dao.getJtgjmc(dm);
	}
	
	/**
	 * @����: ���ݻ�ȡ��id��ѯ�����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����08:04:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQjsqxxForQjsqid(String qjsqid){
		return dao.getQjsq(qjsqid);
	}
	
	/**
	 * 
	 * @����: ����ҽҩ���Ի��ж�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-12 ����10:36:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String checkIfOverTime(String startDate,String endDate){
		return dao.checkIfOverTime(startDate, endDate);
	}
}

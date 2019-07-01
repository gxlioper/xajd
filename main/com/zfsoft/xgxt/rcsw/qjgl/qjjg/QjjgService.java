/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjjg;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: �������service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjjgService extends SuperServiceImpl<QjjgForm, QjjgDao> {

	private static String YJXJ = "1";// �Ѿ�����
	private static String SWXJ = "0";// ��δ����
	// �����Ϣ��ʦ����
	public static String _QJXX_LSCZ = "1";
	/**
	 * ���״̬ �ݸ�״̬��������д�������������Ϣ��
	 */
	public static String _SQZT_CGZT = "0";

	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID = "-1";
	QjjgDao dao = new QjjgDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public QjjgService() {
		this.setDao(dao);
	}
	/**
	 * @throws Exception
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws
	 */
	public boolean save(QjjgForm model) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setShzt("");
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setQjjgid(UniqID.getInstance().getUniqIDHash());
		// ���浽����
		// Ϊ����Ϊ��ʦ�����������ӣ�ͬ����ѧ������
		QjsqService qjsqService = new QjsqService();
		QjsqForm qf = new QjsqForm();
		model.setQjbh(qjsqService.getQjbh(model.getSqsj()));
		BeanUtils.copyProperties(qf, model);
		if (StringUtils.isNull(qf.getQjsqid())) {
			qf.setAddtype(_QJXX_LSCZ);// ��ʦ����
			qf.setSplcid("�������");// �������
			qf.setShzt("");
			qjsqService.save(qf);
		}
		model.setQjbh(qf.getQjbh());
		model.setQjsqid(qf.getQjsqid());
		model.setSplcid("�������");
		//��������ظ��ύ
		if(dao.isYtj(qf.getQjsqid())){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		boolean insertResult = super.runInsert(model);
		boolean result = insertResult;
/*		if (insertResult) {
			// �����������
			result = shlc.runSubmit(model.getQjsqid(), model.getSplcid());
		}*/
		return result;
	}
	@Override
	public QjjgForm getModel(QjjgForm t) throws Exception {
		t = dao.getModel(t);
		if(t!=null){
			// ��ѧ�ڴ���ת��Ϊѧ������
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		if(Base.xxdm.equals("10695")){//���������ѧר��
			QjsqService sqService = new QjsqService();
			t.setJtgjmc(sqService.getJtgjmc(t.getJtgj()));
		}
		return t;
	}

	/**
	 * 
	 * @����:��������ʷ��¼
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-16 ����03:22:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getHistory(QjjgForm model)
			throws Exception {
		// ��ȡ��ѧ��������ټ�¼
		List<HashMap<String, String>> history = dao.getListForXh(model.getXh());
		for (HashMap<String, String> hm : history) {
			if (hm.get("qjsqid")==null||model.getQjsqid().equals(hm.get("qjsqid"))) {
				history.remove(hm);
				break;
			}
		}
		return history;
	}
	/**
	 * 
	 */
	public HashMap<String, String> getQjjgForPrint(String qjjgid)
	throws Exception {
	return dao.getQjjgForPrint(qjjgid);
	}
	
	public boolean haveNewSqjl(QjjgForm model)
	throws Exception {
		return dao.haveNewSqjl(model);
	}
	/**
	 * @throws Exception
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:31:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] �������� String���� 0Ϊ�ɹ�ɾ������Ϊ����ɾ����
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// ��ɾ����id����
		List<String> delSqId = new ArrayList<String>();// ��Ӧ��ɾ������������id

		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
			if (dao.isCanDel(str)) {
				delId.add(str);// ��¼ɾ��id
				delSqId.add(getModel(str).getQjsqid());
				// del.append(str);
				// del.append(",");
			} else {
				HashMap<String, String> hm = dao.getQjjg(str);
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xn"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xqmc"));
				noDel.append(",</br>");
				isHaveNoId = true;
			}
		}
		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		if (i > 0) {
			// ɾ����Ӧ����������Ϣ
			for (String id : ids) {
				shlc.deleteShjl(id);
			}
			// ɾ����Ӧ�������Ϣ
			deleteQjsq(delSqId.toArray(new String[] {}));
		}
		String str = noDel.toString();
		// ȥ�������ය��
		str = isHaveNoId ? str : _BCZSCID;
		return new String[] { String.valueOf(i), str };
	}

	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-14 ����07:11:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @throws Exception void ��������
	 * @throws
	 */
	private void deleteQjsq(String[] values) throws Exception {
		QjsqService qjsq = new QjsqService();
		qjsq.delete(values, true);
	}

	/**
	 * 
	 * @����:�����븴�Ƶ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-12 ����04:33:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean copyForSq(QjjgForm qf) throws Exception {
		qf.setQjzt(_SQZT_CGZT);// ������˵�����
		return super.runInsert(qf);
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
	 * @throws
	 */
	public boolean update(QjjgForm model) throws Exception {
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		// ���û�ж�Ӧ����id��Ϊ�������� ����Ҫ������������
		if (StringUtil.isNull(model.getQjsqid())) {
			model.setShzt(Constants.YW_WTJ);
			return super.runUpdate(model);
		}
		/*
		 * if(!"�������".equals(hm.get("splcid"))){ model.setShzt(Constants.WSH);
		 * }else{
		 */
		model.setShzt("");
		// }

		// Ϊ����Ϊ��ʦ�����������ӣ�ͬ����ѧ������
		QjsqService qjsqService = new QjsqService();
		QjsqForm qf = new QjsqForm();
		BeanUtils.copyProperties(qf, model);
		qf.setAddtype(_QJXX_LSCZ);// ��ʦ����
		
		qjsqService.update(qf);
		boolean insertResult = super.runUpdate(model);
		boolean result = insertResult;
/*		if (insertResult) {
			shlc.deleteShjl(model.getQjsqid());
			result = shlc.runSubmit(model.getQjsqid(), model.getSplcid());
		}*/
		return result;
	}

	/**
	 * 
	 * @����:���ٴ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-12 ����10:20:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean xjcl(QjjgForm model,User user) throws Exception {
		// QjjgForm qf=getModel(model);
		// BeanUtils.copyProperties(model,qf);
		model.setXjzt(YJXJ);
		model.setXjr(user.getUserName());
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		model.setXjsj(time);
		boolean insertResult = super.runUpdate(model);
		return insertResult;
	}

	/**
	 * 
	 * @����:��ȡ��Ӧ����ٹ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����04:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param isShow �Ƿ�����ʾ
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public HashMap<String, String> getSplc(QjjgForm model, boolean isShow) throws Exception {
		HashMap<String,String> data=null;
		//�������ʾ��ȥ��֤����������ʾ�����Ӧ�����Ѿ��޸ģ�
		if(isShow){
			data= new HashMap<String, String>();
			data.put("splcid", "�������");
			// ��ȡ�����Ӧ��ٹ���
			if(StringUtils.isNotNull(model.getSplcid())){//�Ѿ����ڶ�Ӧ����id��ֱ�ӷ���
				data.put("splcid",model.getSplcid());
				return data ;
			}
		}
		QjgzService qjgz = new QjgzService();
		List<HashMap<String, String>> list = qjgz.getAllList(new QjgzForm());
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			//Integer qjtsInt = Integer.parseInt(model.getQjts());
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj)) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)) {
				return hm;
			}
		}
		return data;
	}
	public HashMap<String, String> getSplc(QjjgForm model) throws Exception {
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
	 * @throws
	 */
	public List<HashMap<String, String>> getShxxAndXjxx(
			List<HashMap<String, String>> list) {
		return list;
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjjgForm t)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t));
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjjgForm t, User user)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t, user));
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

	public List<HashMap<String, String>> getQjlx() {
		return dao.getQjlx();
	}

	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-12 ����11:06:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             QjjgForm ��������
	 * @throws
	 */
	public QjjgForm getXjxx(String qjsqid) throws IllegalAccessException,
			InvocationTargetException {
		HashMap<String, String> hm = dao.getXjxx(qjsqid);
		QjjgForm qf = new QjjgForm();
		if (null == hm || hm.size() <= 0) {
			qf.setXjzt(SWXJ);
		} else {
			qf.setXjzt(YJXJ);
			BeanUtils.copyProperties(qf, hm);
		}
		return qf;
	}

	/**
	 * 
	 * @����:�Ƿ���ڶ�Ӧ�������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����06:58:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean isHaveSplcForTs(QjjgForm model) throws Exception {
		HashMap<String, String> splc = getSplc(model);
		if (null == splc) {
			return false;
		}
		return true;
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
	public File printForWord(HashMap<String, String> xsjbxx,HashMap<String, String> qjjgMap) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String qjsj="��  "+DateTranCnDate.fomartDateToCn(qjjgMap.get("kssj"),FomartDateType.day);
		qjsj+="  ��  "+DateTranCnDate.fomartDateToCn(qjjgMap.get("jssj"),FomartDateType.day);
		qjsj+=" ��  "+qjjgMap.get("qjts")+" ��";
		if("10351".equals(Base.xxdm)){
			qjsj+=" ��  "+qjjgMap.get("qjjs")+" ��";
		}
		qjjgMap.put("qjsj",qjsj);
		
		qjjgMap.put("qjsy", HtmlUtil.xmlZy(qjjgMap.get("qjsy")));
		qjjgMap.remove("xh");
		qjjgMap.put("sqsj",DateTranCnDate.fomartDateToCn(qjjgMap.get("sqsj"),FomartDateType.day));
		qjjgMap.put("shsj1",DateTranCnDate.fomartDateToCn(qjjgMap.get("shsj1"),FomartDateType.day));
		qjjgMap.put("shsj2",DateTranCnDate.fomartDateToCn(qjjgMap.get("shsj2"),FomartDateType.day));
		qjjgMap.put("shsj3",DateTranCnDate.fomartDateToCn(qjjgMap.get("shsj3"),FomartDateType.day));
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.putAll(xsjbxx);
		data.putAll(qjjgMap);
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
	 * @���ڣ�2014-11-22 ����03:26:39
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
	
	
	/**��ѯ�����Ŀ�б�*/
	public List<HashMap<String,String>> getQjxmList(){
		return dao.getQjxmList();
	}
	
	public HashMap<String,String> getCssz(){
		return dao.getCssz();
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
	
	/**
	 * 
	 * @����:ȡ�����ڶ����������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-8 ����09:44:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> xjrpdTenD(String sqid) {
		return dao.xjrpdTenD(sqid);
	}
	
	/**
	 * 
	 * @����:ȡ���һ�����������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-8 ����09:44:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> xjrpdOneToN(String sqid) {
		return dao.xjrpdOneToN(sqid);
	}
	
	/**
	 * @����: ����qjjgid��ѯѧ����ٽ����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-13 ����02:21:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQjjgxxForQjsqid(String qjjgid){
		return dao.getQjjg(qjjgid);
	}
	
	/**
	 * �ƶ�������
	 */
	public List<HashMap<String, String>> getPageListXj(QjjgForm t, User user)
	throws Exception {
		return dao.getPageListXj(t, user);
	}
}

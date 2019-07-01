/**
s * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import xgxt.action.Base;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-10-25 ����10:40:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DtxxsqService extends SuperServiceImpl<DtxxsqForm, DtxxsqDao> {
	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID="-1";
	/**
	 * �޵�ǰ�׶���Ϣ
	 */
	public static String _WDQJDXX="-1";
	DtxxsqDao dao = new DtxxsqDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";

	public DtxxsqService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����10:41:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 */
	public boolean save(DtxxsqForm model) throws Exception {
		// yyyy-mm-dd HH24:MI:SS
		if(StringUtils.isNull(model.getSqsj())){
			model.setSqsj(GetTime.getNowTime4());
		}
		//��ȡ�������̲�����
		ShlcpzService ss=new ShlcpzService();
		ShlcpzForm sf=ss.getModel(model.getJddm());
		model.setSplc(sf.getSplc());
		//model.setShzt(Constants.YW_WTJ);
		if(model.getType().equals("submit")){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		model.setDtxxsqid(UniqID.getInstance().getUniqIDHash());
		boolean result = runInsert(model);
		if(result&&model.getType().equals("submit")){
			// �����������                                     
			result = shlc.runSubmit(model.getDtxxsqid(),model.getSplc(),model.getXh(),"dtxxshbase.do","dtxxsqbase.do");
		}
		return result;
	}
	
	public boolean submitDtxxsq(DtxxsqForm model) throws Exception {
		
		model.setShzt(Constants.YW_SHZ);
		DtxxsqForm modelGet = dao.getModel(model.getDtxxsqid());

		// ���˻صļ�¼ȡ�ϵ��������ID;�����˻ؼ�¼����ȥȡ�������
		if(!Constants.YW_YTH.equals(modelGet.getShzt())){

			ShlcpzService service = new ShlcpzService();
			ShlcpzForm shlcpz = new ShlcpzForm();
			shlcpz.setJddm(modelGet.getJddm());
			shlcpz = service.getModel(shlcpz);
			if(shlcpz!=null){
				model.setSplc(shlcpz.getSplc());
			}
		}else{
			model.setSplc(modelGet.getSplc());
		}
		
		boolean resultDtxxsq = dao.updateDtxxsq(model);
		boolean result = false;
		if(resultDtxxsq){
		// ��ȡ��������
		/*	ShlcpzForm sf=ss.getModel(model.getJddm());
			model.setSplc(sf.getSplc());*/
		//String splc = dao.getShlcID(model.getRcxwlbdldm());
		//�����������
		result = shlc.runSubmit(model.getDtxxsqid(), model.getSplc(),model.getXh(),"dtxxshbase.do","dtxxsqbase.do");
		}
		return result;
	}
	
	public int rundel(String[] ids) throws Exception{
		int i=0;
		for(String str:ids){
			//ɾ��������
			i+=runDelete(new String[]{str});
			//ɾ����Ӧ������Ϣ
			BaseDbcz dbcz=new BaseDbcz("dtxxsqbase.do","dtxxshbase.do");
			dbcz.setGnmkMc("������Ϣ����");
			dbcz.setXmmc("������Ϣ����");
			dbcz.remove(str);
		}
		return i;
	}
	/**
	 * 
	 * @����:��ȡ������׶�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����04:49:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getSqJdList(String xh, String jddmGet) throws Exception {
		List<HashMap<String, String>> listnew=new ArrayList<HashMap<String,String>>();
		String dqjddm=_WDQJDXX;
		if(StringUtils.isNotNull(xh)){
			dqjddm=dao.getDqjddm(xh);
		}
		List<HashMap<String, String>> list=dao.getSqJdList(jddmGet);
		listnew.addAll(list);
		for(HashMap<String, String> hm:list){
			String jddm=hm.get("jddm");
			//������ǵ�ǰ�׶κ�Ľ׶���ɾ��
			if(Integer.parseInt(jddm)<=Integer.parseInt(dqjddm)){
				listnew.remove(hm);
			}else if("13871".equals(Base.xxdm)){
				listnew.clear();
				listnew.add(hm);
				return listnew;
			}
		}
		return listnew;
	}
	
	@Override
	public DtxxsqForm getModel(DtxxsqForm t) throws Exception {
		t = super.getModel(t);
		HashMap<String, String> otherP=getAllProperty(t.getDtxxsqid());
		t.setJdmc(otherP.get("jdmc"));
		return t;
	}
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����07:40:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids ɾ��id����
	 * @param isQjjg �Ƿ��ǽ�������
	 * @return String[] �������� 
	 * @throws Exception
	 */
	public String[] delete(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			DtxxsqForm  df=getModel(str);
			if(isCanDel(df)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=getAllProperty(str);
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("sqsj"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("jdmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?rundel(delId.toArray(new String[]{})):0;
		
		if(delId.size()>0){
			
			//�h��������������
			for(String id:delId){
				shlc.deleteShjl(id);
			}
			//�h��������Ϣ
			BaseDbcz dbcz=new BaseDbcz();
			dbcz.setGnmkMc("������Ϣ����");
			dbcz.remove(delId.toArray(new String[] {}));
		}
		
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	/**
	 * 
	 * @����:��ȡ����������ԣ�������ֻ��form�����ֵ��
	 * 		 ����ҳ��list��ѯsql��ȡ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-24 ����02:05:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 */
	public HashMap<String, String> getAllProperty(String id) throws Exception{
		HashMap<String, String> property=null;
		DtxxsqForm hm=new DtxxsqForm();
		hm.setDtxxsqid(id);
		List<HashMap<String, String>> list=getAllList(hm);
		/*	for(HashMap<String, String> hmap:list){
			String shzt=hmap.get("shzt");
			//���ص�ǰ�������������е����ݣ�ֻ��һ������ ��Ϊ�������ݣ�
			if(!Constants.TG.equals(shzt)){
				property=hmap;
			}
		}*/
		if(null!=list&&list.size()==1){//���������� ֻ��һ������ ��Ϊ��������
			property=list.get(0);
		}
		return property;
	}
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����04:53:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean update(DtxxsqForm model) throws Exception {
		
		//�ж��Ƿ����˽׶�
		String sqid = model.getDtxxsqid();
		DtxxsqForm old = dao.getModel(sqid);	

		ShlcpzService ss=new ShlcpzService();
		ShlcpzForm sf=ss.getModel(model.getJddm());
		model.setSplc(sf.getSplc());
		model.setSqsj(GetTime.getNowTime4());
		
		//���ó�ʼ���״ֵ̬
		if(SUBMIT.equalsIgnoreCase(model.getType())){
			model.setShzt(Constants.SHZ);
		}
		boolean result =runUpdate(model);
		/*if(result && !old.getJddm().equals(model.getJddm())){
			//�h��ԭ������������
			shlc.deleteShjl(sqid);
			//�h��ԭ������Ϣ
			BaseDbcz dbcz=new BaseDbcz();
			dbcz.setGnmkMc("������Ϣ����");
			dbcz.remove(new String[] {sqid});

			// �����������
			result = shlc.runSubmit(sqid, model.getSplc(),model.getXh(),"dtxxsqbase.do","dtxxshbase.do");
//			//���ô�����Ϣ
//			dbcz=new BaseDbcz("dtxxsqbase.do","dtxxshbase.do");
//			dbcz.setGnmkMc("������Ϣ����");
//			dbcz.setXmmc("������Ϣ����");
//			dbcz.sqPush(sqid,model.getXh(),model.getSplc());
		}*/
		
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			//�h��ԭ������������
		
			// �����������
			result = shlc.runSubmit(sqid, model.getSplc(),model.getXh(),"dtxxsqbase.do","dtxxshbase.do");
			if (result) {
				//���ô�����Ϣ
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("������Ϣ����");
				dbcz.remove(new String[]{sqid});
				dbcz.setShPath("dtxxsqbase.do");
				dbcz.setSqPath("dtxxshbase.do");
				dbcz.setXmmc("������Ϣ����");
				dbcz.sqPush(sqid, model.getXh(), model.getSplc());
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @����:�Ƿ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-24 ����01:46:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * boolean �������� 
	 */
	public boolean isCanDel(DtxxsqForm qf) {
		//���δ��������ɾ��
		if(Constants.YW_WTJ.equals(qf.getShzt())||Constants.SH_TH.equals(qf.getShzt())){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @����: �Ƿ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����05:39:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 */
	public boolean isCanAdd(DtxxsqForm qf) throws Exception{
		//�������id��Ϊ����Ϊ�޸� ֱ�ӷ���true
		if(!StringUtils.isNull(qf.getDtxxsqid())){return true;};
		String shzt=null;
		List<HashMap<String, String>> list=this.getAllList(qf);
		for(HashMap<String, String> hm:list){
			shzt=hm.get("shzt");
			//������ݲ����Ѿ����������ɵģ�ͨ����ͨ����
			if(!shzt.equals(Constants.YW_BTG)&&!shzt.equals(Constants.YW_TG)){
				return false;
			}
		}
		return true;
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
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-3 ����09:18:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	
	/**
	 * 
	 * @����:TODO(���µ�����Ϣ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-9 ����10:03:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateDtxxsq(DtxxsqForm model) throws Exception {
		
		boolean resultDtxxsq = dao.updateDtxxsq(model);
		
		return resultDtxxsq;
		
	}
	
	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:18:56
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkSfktj(DtxxsqForm model) {

		String num = dao.checkSfktj(model.getJddm());
		return Integer.valueOf(num) > 0;
	}
	
	public String getYbdyDm(){
		return dao.getYbdyDm();
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ϣ��flag(sq:����;jg:���)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:00:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param flag
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxxMap(String id,String flag){
		return dao.getXsjbxxMap(id, flag);
	}
	
	/**
	 * 
	 * @����: ��ȡ�������List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:17:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjjgList(String xh){
		return dao.getPjjgList(xh);
	}
	
	/**
	 * 
	 * @����: ��ȡΥ�ʹ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:40:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjcfList(String xh){
		return dao.getWjcfList(xh);
	}
	/**
	 * 
	 * @����: ��ȡѧ��ְ��
	 * @���ߣ�����[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:40:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getZwmc(String xh){
		return dao.getZwmc(xh);
	}
	public HashMap<String,String> getBjgkc(String xh){
		return dao.getBjgkc(xh);
	}
	public List<HashMap<String,String>> getZcfListByXh(String xh,String xn,String xq){
		return dao.getZcfListByXh(xh,xn,xq);
	}

	//�ɳ������ڻ������
	public  int getAge(String s) throws Exception {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

		Date birthDay=simpleDateFormat.parse(s);


		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay) || StringUtils.isNull(s)) {
			return 0;
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

	public String getJdmc(String jddm) {
		return dao.getJdmc(jddm);
	}
}

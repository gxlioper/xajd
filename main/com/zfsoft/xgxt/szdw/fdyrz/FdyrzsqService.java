/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-4 ����05:06:08 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ�� 
 * @�๦������: TODO ����Ա��ְ���� ��ְ����
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-6-4 ����04:56:01 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class FdyrzsqService extends SuperServiceImpl<FdyrzsqForm, FdyrzsqDAO>{

	private FdyrzsqDAO dao = new FdyrzsqDAO();
	
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	
	public FdyrzsqService(){
		super.setDao(dao);
	}
	
	public HashMap<String,String> getFdyjbxx(String zgh){
		return dao.getFdyxx(zgh);
	}
	/**
	 * @����:TODO��ѯ�༶��Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-6 ����10:48:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param bjlist
	 * @param type
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjxxBybjdm(String bjdm,List<HashMap<String,String>> bjlist,String type){
		if(type !=null){
			if(type.equals("sq")){
				HashMap<String,String> map = null;//new BjglService().getBjxxBybjdm(bjdm);
				bjlist.add(map);
			}else{
				Iterator<HashMap<String,String>> ite = bjlist.iterator();
				while(ite.hasNext()){
					HashMap<String,String> xmap = ite.next();
					if(bjdm.equals(xmap.get("bjdm"))){
						ite.remove();
					}
				}
			}
		}else{
			bjlist = new ArrayList<HashMap<String,String>>();
		}
		return bjlist;
	}
	/**
	 * @����:
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-6 ����02:09:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param bjList
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean fdyrzsq(FdyrzsqForm myForm,User user) throws Exception{
		String guid = UniqID.getInstance().getUniqIDHash();
		myForm.setSqid(guid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}else{
			myForm.setShzt(Constants.YW_WTJ);
		}
		
		//��ȡ��������
		SzdwCsszService cssz = new SzdwCsszService();
		String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
		myForm.setSplc(splc);
		
		boolean result = super.runInsert(myForm);
		dao.updateFdyxxZjz(myForm.getZgh(),myForm.getZjz());
		if(result && SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),splc,myForm.getZgh(),"szdw_fdyrz_sh.do?method=gjcxRzsh","szdw_fdyrz_sq.do?method=gjcxWdsq");
		}
		return result;
	}
	/**
	 * @����:��ȡ�༶�б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-6 ����02:11:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<String> �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<String> getBjList(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("bjList");
		List<HashMap<String,String>> bjlist =  new ArrayList<HashMap<String,String>>();
		List<String> bjList = new ArrayList<String>();
		if(obj!=null && obj instanceof List){
			bjlist =(List<HashMap<String,String>>) obj;
			Iterator<HashMap<String,String>> ite = bjlist.iterator();
			while(ite.hasNext()){
				HashMap<String,String> map = ite.next();
				bjList.add(map.get("bjdm"));
			}
		}
		return bjList;
	}
	/**
	 * @����:ȡ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-8 ����05:04:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param spids
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean fdyrzqxsq(String[] spids) throws SQLException{
		if(spids == null){
			return false;
		}else{
			int[] result = dao.updateFdyrzsq(spids);
			for (int i = 0; i < result.length; i++) {
				if(result[i]!=-2){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @����:��֤��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-4 ����02:41:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject ��������
	 */
	public JSONObject yzcssz(FdyrzsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		String message = cssz.yzCssz(CsszUtil.SZDW_FDYRZSQ);
		if(message.equals("true")){
			int count = dao.getFdyrzsqCount(myForm.getZgh());
			if(count>0){
				message=MessageUtil.getText("szdw_fdyrz_sqyz");
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	
	/**
	 * 
	 * @����:TODO(ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-10 ����08:50:27
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
	
	public boolean updateFdyrzsq(FdyrzsqForm model) throws Exception{
		return dao.updateFdyrzsq(model)>0?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(�ύ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-10 ����09:32:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitFdyrzsq(FdyrzsqForm myForm) throws Exception{
		
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			//��ȡ��������
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
			myForm.setSplc(splc);
		}
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateFdyrzsq(myForm)>0?true:false;
		//dao.updateFdyxxZjz(myForm.getZgh(),myForm.getZjz());
		if(result){
			//�����������
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getZgh(),"szdw_fdyrz_sh.do?method=gjcxRzsh","szdw_fdyrz_sq.do?method=gjcxWdsq");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(���¸���Ա��ְ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-31 ����04:22:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateFdyrzsq(FdyrzsqForm myForm,User user) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			//��ȡ��������
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
			if(!Constants.YW_YTH.equals(myForm.getShzt())){
				myForm.setSplc(splc);
			}
			myForm.setShzt(Constants.YW_SHZ);
		}else{
			myForm.setShzt(Constants.YW_WTJ);
		}
		//��ȡ��������
		//SzdwCsszService cssz = new SzdwCsszService();
		//String splc =cssz.getModel(CsszUtil.SZDW_FDYRZSQ).getSplc();
		//myForm.setSplc(splc);
		boolean result = super.runUpdate(myForm);
		dao.updateFdyxxZjz(myForm.getZgh(),myForm.getZjz());
		if(result && SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getZgh(),"szdw_fdyrz_sh.do?method=gjcxRzsh","szdw_fdyrz_sq.do?method=gjcxWdsq");
		}
		return result;
		
	}
	
	
	/**
	 * 
	 * @����:��֤����ʱ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-7-8 ����04:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	public JSONObject yzsqTime() throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		String message = cssz.yzCssz(CsszUtil.SZDW_FDYRZSQ);
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	/**
	 * 
	 * @����:ʱ�俪����֤
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-27 ����11:04:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	public JSONObject timeSwitch(FdyrzsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		String message = cssz.yzCssz(CsszUtil.SZDW_FDYRZSQ);
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
		
	}
}

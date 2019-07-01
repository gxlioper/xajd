/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����4:16:36 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszForm;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ���� service
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-24 ����4:15:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdypxXmsqService extends SuperServiceImpl<FdypxXmsqForm, FdypxXmsqDAO> {

	private FdypxXmsqDAO dao = new FdypxXmsqDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	
	public FdypxXmsqService() {
		// TODO �Զ����ɷ������
		super.setDao(dao);
	}
	/**
	 * @����:����Ա��ѵ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����2:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean fdypxxmsq(FdypxXmsqForm myForm) throws Exception{
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
		String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
		myForm.setSplc(splc);
		boolean result = super.runInsert(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),splc,myForm.getFbr(),"szdw_fdypxxmsh.do?method=fdypxxmList","szdw_fdypxxmsq.do?method=fdypxxmsqList");
		}
		return result;
	}
	/**
	 * @����:��֤����Ա��ѵ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����2:11:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject ��������
	 */
	public JSONObject yzFdypxsq(FdypxXmsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		//SzdwCsszForm csszForm =  cssz.getShlcsqkzb(CsszUtil.SZDW_XSGBZWSQ);
		SzdwCsszForm csszForm =  cssz.getShlcsqkzb(CsszUtil.SZDW_FDYPXSQ);
		//SzdwCsszForm cssz =
		String message = "true";
		if(csszForm.getSplc()==null || "".equals(csszForm.getSplc())){
			message = MessageUtil.getText("szdw_xsgbgl_zwsqyz");
		}
		if(message.equals("true")){
			//��֤��ѵ��Ŀ�Ƿ��Ѿ�����
			int i = dao.getSqCount(myForm.getSqr(),myForm.getXmdm());
			if(i>0){
				message = MessageUtil.getText("szdw_fdypx_sqyz");;
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	/**
	 * @����:ȡ������Ա��ѵ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����3:15:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param spids
	 * @return
	 * @throws SQLException
	 * boolean ��������
	 */
	public boolean fdypxqxsq(String[] spids) throws SQLException{
		if(spids == null){
			return false;
		}else{
			int[] result = dao.updateFdypxsq(spids);
			for (int i = 0; i < result.length; i++) {
				if(result[i]!=-2){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @����:������Ŀ��Ų�ѯ����Ŀ�Ƿ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-5 ����3:07:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getSqCountByPxxm(String[] xmdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		if(xmdm.length>0){
			String tt= "";
			for (int i = 0; i < xmdm.length; i++) {
				tt = "'"+xmdm[i]+"',";
			}
			int m = tt.lastIndexOf(",");
			tt = tt.substring(0,m);
			sql.append(tt);
		}else{
			sql.append("''");
		}
		return dao.getSqCountByPxxm(sql.toString());
	}
	
	
	
	public boolean submitFdypxsq(FdypxXmsqForm myForm) throws Exception{
		
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			
			//��ȡ��������
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
			myForm.setSplc(splc);
		}
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateFdypxsq(myForm)>0?true:false;
		if(result){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getFbr(),"szdw_fdypxxmsh.do?method=fdypxxmList","szdw_fdypxxmsq.do?method=fdypxxmsqList");
		}
		return result;
	}
	
	/**
	 * @����:����Ա��ѵ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-25 ����2:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean fdypxxmsqXg(FdypxXmsqForm myForm) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())&& !Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			//��ȡ��������
			SzdwCsszService cssz = new SzdwCsszService();
			String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
			myForm.setSplc(splc);
		}
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}
		
		boolean result = super.runUpdate(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getFbr(),"szdw_fdypxxmsh.do?method=fdypxxmList","szdw_fdypxxmsq.do?method=fdypxxmsqList");
		}
		return result;
		
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
	
	public boolean updateFdypxsq(FdypxXmsqForm myForm) throws Exception{
		return dao.updateFdypxsq(myForm)>0?true:false;
	}
	
	/**
	 * 
	 * @����:�ж���Ŀ�����Ƿ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-29 ����06:25:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean getXmkg(String xmdm){
		
		return Integer.valueOf(dao.getXmkg(xmdm))>0;
	}
	
}

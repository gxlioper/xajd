/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����4:16:36 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import net.sf.json.JSONObject;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszForm;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-8 ����2:30:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZwsqService extends SuperServiceImpl<ZwsqForm, ZwsqDAO> {

	private ZwsqDAO dao = new ZwsqDAO();


	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	private ZwwhService zwwhservice = new ZwwhService();
	private ZwlxDAO zwlxdao = new ZwlxDAO();
	public ZwsqService() {
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
	public boolean zwsq(ZwsqForm myForm) throws Exception{
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
//		SzdwCsszService cssz = new SzdwCsszService();
		//------------����������
		//String splc =cssz.getModel(CsszUtil.SZDW_FDYPXSQ).getSplc();
	
		String lxdm = zwwhservice.getModel(myForm.getZwid()).getLxdm();
		String splc = zwlxdao.getModel(lxdm).getSplc();
		myForm.setSplc(splc);
		boolean result = super.runInsert(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(guid,splc,myForm.getXh(),"szdw_zwsh.do?method=zwshList","szdw_zwsq.do?method=zwsqList");
		}
		return result;
	}
	/**
	 * @����:��֤��ɲ�ְ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-9 ����3:44:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * JSONObject ��������
	 */
	public JSONObject yzZwsq(ZwsqForm myForm) throws Exception{
		Map<String,String> resultmap = new HashMap<String,String>();
		SzdwCsszService cssz = new SzdwCsszService();
		SzdwCsszForm csszForm =  cssz.getShlcsqkzb(CsszUtil.SZDW_XSGBZWSQ);
		
		String message = "true";
		
		if(myForm.getType()==null){
			if(csszForm.getSplc()==null || "".equals(csszForm.getSplc())){
				message = "false";
			}
			if(message.equals("true")){
				int i = dao.getSqCount(myForm.getXh(),myForm.getZwid());
				if(i>0){
					message = MessageUtil.getText("szdw_xsgbgl_zwsq");
				}
			}else{
				message = MessageUtil.getText("szdw_xsgbgl_zwsqyz");;
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap); 
	}
	/**
	 * @����:ȡ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:38:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param spids
	 * @return
	 * @throws SQLException
	 * boolean ��������
	 */
	public boolean qxsq(String[] spids) throws SQLException{
		if(spids == null){
			return false;
		}else{
			int[] result = dao.updateSq(spids);
			for (int i = 0; i < result.length; i++) {
				if(result[i]!=-2){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @����:����ְ������Ƿ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:36:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zwid
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getSqCountByZwid(String[] zwid) throws SQLException{
		StringBuffer sql = new StringBuffer();
		if(zwid.length>0){
			String tt= "";
			for (int i = 0; i < zwid.length; i++) {
				tt = "'"+zwid[i]+"',";
			}
			int m = tt.lastIndexOf(",");
			tt = tt.substring(0,m);
			sql.append(tt);
		}else{
			sql.append("''");
		}
		return dao.getSqCountByZwid(sql.toString());
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
	
	public boolean updateZwsq(ZwsqForm model) throws Exception{
		return dao.updateZwsq(model)>0?true:false;
	}
	
	public boolean submitZwsq(ZwsqForm myForm) throws Exception{
		
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())){
			//��ȡ��������
			String zwid = new ZwsqDAO().getModel(myForm.getSqid()).getZwid();
			String lxdm = zwwhservice.getModel(zwid).getLxdm();
			String splc = zwlxdao.getModel(lxdm).getSplc();
			myForm.setSplc(splc); 
		}
		
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateZwsq(myForm)>0?true:false;
		if(result){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getXh(),"szdw_zwsh.do?method=zwshList","szdw_zwsq.do?method=zwsqList");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(�޸�ѧ���ɲ�ְ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-2 ����05:48:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean zwsqXg(ZwsqForm myForm) throws Exception{
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		myForm.setSqsj(sdf.format(new Date()));
		if(!Constants.YW_YTH.equalsIgnoreCase(myForm.getShzt())&&SUBMIT.equalsIgnoreCase(myForm.getType())){
			//��ȡ��������
			String lxdm = zwwhservice.getModel(myForm.getZwid()).getLxdm();
			String splc = zwlxdao.getModel(lxdm).getSplc();
			myForm.setSplc(splc);
		}
		
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}
		
		boolean result = super.runUpdate(myForm);
		if(result&& SUBMIT.equalsIgnoreCase(myForm.getType())){
			result = shlc.runSubmit(myForm.getSqid(),myForm.getSplc(),myForm.getXh(),"szdw_zwsh.do?method=zwshList","szdw_zwsq.do?method=zwsqList");
		}
		return result;
		
	}
	
	/**
	 * @����:��ȡ�ĵ�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-13 ����03:35:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return File ��������
	 * @throws
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"szdw/zwsq_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "szdw", "zwsq_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "szdw", "zwsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
}

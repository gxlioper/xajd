/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-17 ����09:16:17 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import net.sf.json.JSONObject;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xsgzgl.qgzx.cssz.QgzxCsszService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ѧ����λ-�ҵĸ�λ����
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-6-17 ����09:09:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WdgwsqService extends SuperServiceImpl<WdgwsqForm, WdgwsqDAO> {

	private WdgwsqDAO dao = new WdgwsqDAO();

	private ShlcInterface shlc = new CommShlcImpl();

	public WdgwsqService() {
		super.setDao(dao);
	}

	@Override
	public WdgwsqForm getModel(WdgwsqForm t) throws Exception {

		return dao.getModel(t);
	}

	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	public boolean submitRecord(String pkValue, String lcid, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(pkValue, lcid, xh,
				"qgzx_xsgwsh.do?method=xsgwshCx",
				"qgzx_wdgwsq.do?method=wdgwsqCx");
		return result;
	}

	/**
	 * @����:��ȡ��λ�б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����03:29:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGwPageList(WdgwsqForm model,
			User user) throws Exception {
		return dao.getGwPageList(model, user);
	}

	
	//==================================================//
	/**
	 * ��ȡ����
	 */
	public List<HashMap<String, String>> getGwsqPageListStu(WdgwsqForm model,User user) throws Exception {
		return dao.getGwsqPageListStu(model, user);
	}
	/**
	 * 
	 * @����:TODO��ѯ�ڹ���ѧѧ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-29 ����03:55:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQgzxStuList(WdgwsqForm model, User user)
	throws Exception {
		return dao.getQgzxStuList(model, user);
	}
	
	/**
	 * 
	 * @����:��������Ŀ
	 * @���ߣ���С��[���ţ�1036]
	 */
	public List<HashMap<String, String>> getGwsqPageListStuYsq(WdgwsqForm model,
			User user) throws Exception {
		return dao.getGwsqPageListStuYsq(model, user);
	}
	//==================================================//
	public List<HashMap<String,String>> getGwList(WdgwsqForm t,User user) throws Exception {
		return dao.getGwList(t,user);
	}
	public List<HashMap<String,String>> getWdgwsqjlList(WdgwsqForm t,User user) throws Exception {
		return dao.getWdgwsqjlList(t,user);
	}
	public HashMap<String,String> getGwxxByGwdm(String gwdm) throws Exception {
		return dao.getGwxxByGwdm(gwdm);
	}
	public HashMap<String,String> getGwxxByXhAndGwdm(String gwdm,String xh) throws Exception {
		return dao.getGwxxByXhAndGwdm(gwdm,xh);
	}
	public List<HashMap<String,String>> getGzmxList(String gwdm,String xh){
		return dao.getGzmxList(gwdm,xh);
	}
	public List<HashMap<String,String>> getWdGwList(WdgwsqForm t,User user) throws Exception {
		return dao.getWdGwList(t,user);
	}
	/**
	 * @����:TODO�ύ���� �������뵥 ���ύ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-17 ����06:25:22
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean saveSq(WdgwsqForm model) throws Exception {
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		
		//�ж�ѧ�ź͸�λID�Ƿ���ڣ�������ھͲ��������С���ע���ڹ������˸����⣬�����۲����ǣ����������ع�
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		
		// ��ȡ��������
//		String splc = dao.getShlcID();
		String dwlb = dao.getYrdwlb(model.getGwdm());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//У�ڵ�λ
			model.setSplc(splcMap.get("xsgwsqsplc"));
		} else {//У�ⵥλ
			model.setSplc(splcMap.get("xwxsgwsqsplc"));
		}

		boolean insertResult = super.runInsert(model);
		return insertResult;
	}
	//�ж��Ƿ���У�ڸ�λ
	public boolean isXndw(String gwdm){
		String dwlb = dao.getYrdwlb(gwdm);
		return "01".equals(dwlb);//01У�ڣ�02У��
	}
	public boolean update(WdgwsqForm model) throws Exception {
		
		//�ж�ѧ�ź͸�λID�Ƿ���ڣ�������ھͲ��������С���ע���ڹ������˸����⣬�����۲����ǣ����������ع�
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		// ��ȡ��������
		String dwlb = dao.getYrdwlb(model.getGwdm());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//У�ڵ�λ
			model.setSplc(splcMap.get("xsgwsqsplc"));
		} else {//У�ⵥλ
			model.setSplc(splcMap.get("xwxsgwsqsplc"));
		}
		//ShlcDao shlcDao = new ShlcDao();
		//model.setShgw(shlcDao.getFirstGwid(splc));
		model.setShgw("");
		boolean insertResult = super.runUpdate(model);
		if (insertResult && Constants.YW_SHZ.equalsIgnoreCase(model.getShzt())) {
			insertResult = shlc.runSubmit(model.getSqbh(), model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xsgwshCx",
					"qgzx_wdgwsq.do?method=wdgwsqCx");
		}
		return insertResult;
	}

	public String saveSq(WdgwsqForm model, String type) throws Exception {
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		model.setSqsj(sdf.format(now));
		model.setShzt(Constants.YW_WTJ);

		String dwlb = dao.getYrdwlb(model.getGwdm());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//У�ڵ�λ
			model.setSplc(splcMap.get("xsgwsqsplc"));
		} else {//У�ⵥλ
			model.setSplc(splcMap.get("xwxsgwsqsplc"));
		}
		// ��������������趨��ʼֵ
		if (model.getSplc() != null && !"".equals(model.getSplc())) {
			model.setShzt(Constants.YW_SHZ);// �����
		}
		boolean insertResult=false;
		String guid = UniqID.getInstance().getUniqIDHash();
		if(!StringUtil.isNull(model.getSqbh())){
			guid=model.getSqbh();
			insertResult = super.runUpdate(model);
		}else{
			model.setSqbh(guid);
			insertResult = super.runInsert(model);
		}

		if (insertResult) {
			insertResult = shlc.runSubmit(guid, model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xsgwshCx",
					"qgzx_wdgwsq.do?method=wdgwsqCx");

			//start��ʱ����
			//ѧ����λ����ɹ�������1Сʱ�ڽ������ԣ�
			//��������ʦ���и�λ���ͨ���󣬸�ѧ����ʽ�ϸڣ�
			//�糬��1Сʱ��ø�λ�Զ����ͷ�
			/*if(!TASK_MAP.containsKey(model.getSqbh())){
				Future f = schedule.startTask(new XsgwsqTimeOutTask(model));
				TASK_MAP.put(model.getSqbh(),f);
			}*/
		}
		return String.valueOf(insertResult);
	}

	public boolean saveLzSq(WdgwsqForm model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_SHZ);
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		model.setSplc(splcMap.get("xslzsplc"));
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		boolean flag = dao.insertLzxx(model);
		if(flag){
			flag = shlc.runSubmit(guid, model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xslzshCx",
					"qgzx_wdgwsq.do?method=wdqggwCx");
		}
		return flag;
	}
	public boolean updateModel(WdgwsqForm model) throws Exception {
		return super.runUpdate(model);
	}

	/**
	 * @����:TODO������������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-19 ����04:07:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return JSONObject ��������
	 */
	public JSONObject yzcssz(WdgwsqForm model) throws Exception {
		Map<String, String> resultmap = new HashMap<String, String>();
		HashMap<String, String> map = dao.getCsszb();
		
		//��λ��Ϣ
		HashMap<String, String> gwMap = dao.getGwInfo(model.getGwdm());
		String message;
		try {
			String sfkfxsgwsq = map.get("sfkfxsgwsq");

			String xsgwsqsplc = map.get("xsgwsqsplc");
			String xsxsgws = map.get("xsxsgws");
			String xsgws = map.get("xsgws");
			String gwxqrs = StringUtils.isNull(gwMap.get("xqrs")) ? "0" : gwMap.get("xqrs");//��λ��Ƹ����

			// ��ȡѧ�����������
			int sqCount = dao.getSqCount2(model.getXh(),gwMap.get("xn"));
			//��ȡ�ø�λѧ��������
			int xsCount = dao.getXsCount(model.getGwdm(),gwMap.get("xn"));
			// ��ȡѧ�����ڸڸ�λ����
			int xszgsl = dao.getXszgsl(model.getXh());
			// ѧ���ڸڵĸ�λ�Ƿ�����ʽ�ڣ���λ����ֻ��0 �� 1��һ��ѧ��ֻ����һ����ʽ�ڣ�1���������ʱ�ڣ�0��
			//list��size���ֻ����2
			List<HashMap<String,String>> list = dao.getZgGwxxz(model.getXh());

			//ѧ���Ƿ���ҵ����
			boolean isHmdXs = dao.isHmdXs(model.getXh(),model.getGwdm());

			if (sfkfxsgwsq == null || !"on".equals(sfkfxsgwsq)) {// �����Ѿ�����
				message = "ѧ����λ����û�п���";
			} else if (isHmdXs) {
				message = "���ѱ��ĵ�λ���ڣ���������õ�λ�ĸ�λ��";
			}  else if (sqCount >= Integer.parseInt(xsxsgws)
					&& !"0".equals(xsxsgws)) {// ѧ����������λ��,0������������
				message = "һ��ѧ���������" + xsxsgws + "����λ��";
			} else if (xszgsl >= Integer.parseInt(xsgws)
					&& !"0".equals(xsgws)) {// ѧ������ø�λ����0�����ƻ����
				message = "һ��ѧ�����ɻ��" + xsgws + "����λ,����ѧ����ӵ��" + xsgws + "����λ��";
			} else if (xsgwsqsplc == null || "".equals(xsgwsqsplc)) {
				message = "��û�ж����������̲�������";
			} else if(!"0".equals(gwxqrs) && xsCount >= Integer.parseInt(gwxqrs)){
				message = "�ĸ�λ����������������������λ";
			} else if(!CollectionUtils.isEmpty(list)
					&& "1".equals(list.get(0).get("gwxzdm"))
					&& "1".equals(gwMap.get("gwxzdm"))){
				message = "һ��ѧ��ֻ����һ����ʽ��λ��������������ʱ��λ";
			} else {
				message = "true";
			}
		} catch (Exception e) {
			message = "ѧ����λ����δ��ͨ";
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap);
	}

	/**
	 * @����:��֤����ĸ�λ�Ƿ��Ѿ��ﵽ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-1 ����11:00:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @return
	 * @throws SQLException
	 *             String ��������
	 */
	public String yzgwxx(String gwdm, String xh) throws Exception {
		String message = "";
		HashMap<String, String> gwxx = dao.getGwxx(gwdm);
		String xqrs = gwxx.get("xqrs");// ����������
//		String knsrs = gwxx.get("knsrs");// ������������
		String gwrs = gwxx.get("gwrs");// �ڸ�������
		String zgknss = gwxx.get("zgknss");// �ڸ������� ��
//		int xqfkns = Integer.parseInt(xqrs) - Integer.parseInt(knsrs);
		int zgfkns = Integer.parseInt(gwrs) - Integer.parseInt(zgknss);

		int iskns = dao.isKns(gwdm, xh);
/*		if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs)) {
			message = "��λ��Ա����";
		} else */
		/*if (xqfkns - zgfkns <= 0 && iskns == 0){
			message = "�ø�λ�ķ������������Ѵﵽ���ޣ�������������λ��"; 
		}else */if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs) && iskns != 0) {
			message = "��λ�����Ѵﵽ���ޣ�������������λ��";
		} else {
			message = "true";
		}
		return message;
	}

	/**
	 * 
	 * @����: ��֤���Ƽ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-4 ����01:54:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @param yzgw
	 * @param user
	 * @return
	 * @throws SQLException
	 *             String ��������
	 */
	public String yzjb(String xh,String splc,String gwdm, String yzgw, String shgw,boolean isTj)
			throws SQLException {
		String message = "true";
		// ��ǰ�û�����֤��λ �򲻽�����֤�� ������ύʹ���������֤
		if (isCheck(splc, yzgw, shgw)||isTj) {
			HashMap<String, String> gwxx = dao.getGwxx(gwdm);
			String sfsgwsqsxz = gwxx.get("sfsgwsqsxz");
			String xqrs = gwxx.get("xqrs");// ����������
			Integer gwshtgrsI=dao.getGwShtgRs(splc,shgw,gwdm);//��λ���ͨ������
			if(gwshtgrsI>=Integer.parseInt(xqrs)&&!"0".equals(xqrs)){
				message = "�ѳ�����ǰ��˸�λ����������";
			}else{
				HashMap<String, String> map = getCsszb();
				String xsgws = map.get("xsgws");//ѧ���ɻ�ø�λ��
				Integer xshqGws=dao.getTgRs(splc,xh,shgw);//ѧ����ȡ��λ��
				if ("1".equals(sfsgwsqsxz) && xshqGws >= Integer.parseInt(xsgws)&&!"0".equals(xsgws)) {
					message = "��ѧ���Ѿ���" + xshqGws + "����λ������ѧ������λ��";
				}
			}
		}
		return message;
	}
	/**
	 * 
	 * @����:�Ƿ���Ҫ��֤
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-12 ����05:04:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param yzgw
	 * @param shgw
	 * @return boolean ��������
	 */
	public boolean isCheck(String splc, String yzgw, String shgw) {
		if (StringUtils.isNull(splc) || StringUtils.isNull(yzgw)
				|| StringUtils.isNull(shgw)) {
			return false;
		}
		//��������� ��˸�λ����Ϊ -1 ʱ Ϊ�˻ظ�������
		if("-1".equals(shgw)){
			return false;
		}
		String yzxh = dao.getGwSpXh(splc, yzgw);

		String shxh = dao.getGwSpXh(splc, shgw);
		if (Integer.parseInt(yzxh) <= Integer.parseInt(shxh)) {
			return true;
		}
		return false;
	}

	/**
	 * @����:TODO��ȡ��������ID
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-19 ����04:08:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 */
	public String getShlcID() {
		return dao.getShlcID();
	}
	
	public String getYjShlcID() {
		return dao.getYjShlcID();
	}


	/**
	 * @����:TODO��ȡѧ���ڸ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����10:28:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getXszggwsl(WdgwsqForm model) throws SQLException {
		return dao.getXszggwsl(model.getGwdm(), model.getXh());
	}

	/**
	 * @����:TODO��ȡѧ����������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����10:27:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getXssqsl(WdgwsqForm model) throws SQLException {
		return dao.getXssqsl(model.getGwdm(), model.getXh());
	}

	/**
	 * @����:��ȡ��ѯ���ñ�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����11:07:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 *             HashMap<String,String> ��������
	 */
	public HashMap<String, String> getCsszb(){
		return dao.getCsszb();
	}

	/**
	 * @����:��ȡѧ���ڸ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����11:13:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws SQLException int ��������
	 */
	public int getXszgsl(WdgwsqForm model) throws SQLException {
		return dao.getXszgsl(model.getXh());
	}

	/**
	 * @����:���ݸ�λ�����ȡ��λ���������������������ڸ����������ڸ���������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-1 ����10:43:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @return
	 * @throws SQLException
	 *             HashMap<String,String> ��������
	 */
	public HashMap<String, String> getGwxx(String gwdm) throws SQLException {
		return dao.getGwxx(gwdm);
	}

	/**
	 * @����: ѧ����λ����ɾ��
	 * @���ߣ�HongLin
	 * @���ڣ�2014-1-17 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return int
	 * @throws SQLException int ��������
	 */
	public int runDelete(String[] values) throws Exception {
		return dao.runDelete(values);
	}
	/**
	 * Word��ʽ��ӡ
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1104
	 * @���ڣ�2014-6-20 ����09:57:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsxx
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	
	public File printForWord(Map<String, Object> data) throws Exception {
//		Map<String, Object> data = new HashMap<String, Object>();
//		if(xsxx == null){
//			xsxx = new HashMap<String, Object>();
//		}
//		//������Ϣ
//		data.putAll(xsxx);
		return getWord(data);
	}
	private File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"qgzx/gwbmb_"+Base.xxdm+".xml";
		
		File wordFile = null;
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "qgzx", "gwbmb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "qgzx", "gwbmb.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�ж�ѧ���Ƿ����ڹ���ѧѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-29 ����05:19:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isQgzxStu(String xh) throws Exception{
		return dao.isQgzxStu(xh).size()>0;
	}
	public HashMap<String, String> getQsxx(String xh) throws Exception{
		return dao.getQsxx(xh);
	}
	/**
	 * 
	 * @����:��ȡƶ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-25 ����02:24:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPkxjb(String xh)throws Exception{
		String pkxx = dao.getPkxjb(xh);
		
		if(null==pkxx||"".equals(pkxx)){
			return "��";
		}else{
			return "��-"+pkxx;
		}
			
	}

	/**
	 * ������У-��ȡס����Ϣ��ר�á�
	 */
	public String getQsxxJqlx(String xh) throws Exception{
		HashMap<String, String> qsxx = dao.getQsxx(xh);
		String xqmc = StringUtil.isNull(qsxx.get("xqmc")) ? "" : (qsxx.get("xqmc") + " ");
		String yqmc = StringUtil.isNull(qsxx.get("yqmc")) ? "" : (qsxx.get("yqmc") + " ");
		String qsbh=null;
		if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")||null==qsxx.get("cwh")){
			qsbh="";
		}else{
			qsbh=qsxx.get("ldmc")+" "+qsxx.get("qsh")+"�� "+qsxx.get("cwh")+"��";
		}
		String qsxxStr = xqmc + yqmc + qsbh;
		return qsxxStr;
	}
	
	
	/**
	 * 
	 * @����:�����ڹ���ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-23 ����03:48:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param mxrq
	 * @param mxxmList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQgmx(WdgwsqForm model,String[] mxrq,List<String[]> mxxmList) throws Exception{
		
		List<String[]> params = new ArrayList<String[]>();
		
		for (int i = 0 ; i < mxrq.length ; i++){
			String[] param = new String[]{model.getXh(),mxrq[i],StringUtils.joinArr(mxxmList.get(i)),i+""};
			params.add(param);
		}
		
		dao.delQgmx(model.getXh());
		return dao.saveQgmx(params);
	}

	/** 
	 * @����:�ڹ���ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-23 ����04:39:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getQgmxList(String id){
		
		List<HashMap<String,String>> qgmxList = dao.getQgmxList(id);
		
		return qgmxList;
	}
	/** 
	 * �ڹ���ϸ(��ӡ��ר��)
	 */
	public HashMap<String,String> getQgmxByQgrqQgmx(String[] qgrqArr, String[] qgmxArr, String xh){
		return dao.getQgmxByQgrqQgmx(qgrqArr, qgmxArr, xh);
	}
	
	
	/******copy from mobile*********/
	public List<HashMap<String, String>> getGwWsqPageList(WdgwsqForm model,
			User user) throws Exception {
		return dao.getGwWsqPageList(model, user);
	}
	
	/**
	 * 
	 * @����:�㽭��ҽҩ��ѧ�������ӡѧ��������Ϣ����λ��Ϣ��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����10:00:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBmbxx(String sqbh){
		return dao.getBmbxx(sqbh);
	}
	
	/**
	 * 
	 * @����:�㽭��ҽҩ�ڹ��������ӡ-������ϸ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����11:47:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKxsjMx(String xh){
		return dao.getKxsjMx(xh);
	}
	
	/** 
	 * @����:��ȡ����ѧԺ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-18 ����01:56:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXyms(WdgwsqForm form){
		return dao.getXyms(form);
	}
	
	/** 
	 * @����:�˸��Ƿ���һ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-20 ����11:20:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isTgInOneYear(String xh){
		return dao.isTgInOneYear(xh);
	}
	//���˵�λ���
	public String getYrdwlb(String gwdm){
		return dao.getYrdwlb(gwdm);
	}
	//��ȡĳѧ�����ڹ���ʷ��¼
	public List<HashMap<String,String>> getQglsjl(String xh){
		return dao.getQglsjl(xh);
	}
}

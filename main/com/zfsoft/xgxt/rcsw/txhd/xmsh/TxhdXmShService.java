/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-26 ����01:57:41 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgDao;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgForm;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgService;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-6-26 ����01:57:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXmShService extends
		SuperServiceImplExtend<TxhdXmShForm, TxhdXmShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private BaseDbcz dbcz = new BaseDbcz();
	/**
	 * @throws Exception 
	 * 
	 * @����:���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:47:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(TxhdXmShForm form, User user) throws Exception {
		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_txhd_xmsh.do");
		model.setTzljsq("rcsw_txhd_xmsq_js.do");
		boolean reuslt = false;
		
		//���Ϊͨ����֤�����Ƿ񳬹�����
		if(Constants.SH_TG.equals(form.getShzt())){
			checkRskz(form.getXmdm(),form.getGwid());
		}
		
		
			String zhzt = shlc.runAuditingNotCommit(model);
			TxhdXmShForm upForm = new TxhdXmShForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getSqid());
			// ���״̬Ϊͨ�������ճ���Ϊ������б����������
			if (zhzt.equalsIgnoreCase(Constants.SH_TG)) {
				// ��ȡ���ݿ���������
				TxhdXmShForm data = getModel(form);
				TxhdXmjgForm qf = new TxhdXmjgForm();
				TxhdXmjgDao txs = new TxhdXmjgDao();
				// ��Ӧ���Ը��Ƶ������
				BeanUtils.copyProperties(qf, StringUtils.formatData(data));
				HashMap<String, String> map=dao.getTxXmxx(data).get(0);
				qf.setXmmc(map.get("xmmc"));
				qf.setHddd(map.get("hddd"));
				qf.setLbdm(map.get("lbdm"));
				qf.setHdkssj(map.get("hdkssj"));
				qf.setHdjssj(map.get("hdjssj"));
				qf.setHdsm(map.get("hdsm"));
				qf.setSjly("1");
				BeanUtils.copyProperties(qf,dao.getTxXmxx(data));
				qf.setSqid(data.getSqid());
				qf.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				txs.runInsertNotCommit(qf);
			}
		
		return reuslt;
	}
	/**
	 * 
	 * @����:��ȡ��ѧ��Ŀ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-6-26 ����04:58:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTxXmxx(TxhdXmShForm t){
		return dao.getTxXmxx(t);
	}
	@Override
	public TxhdXmShForm getModel(TxhdXmShForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// ��ѧ�ڴ���ת��Ϊѧ������
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	/**
	 * 
	 * @����: ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-5 ����03:17:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean cancel(String shlc, String sqid) throws Exception {
		boolean result = false;
		// �������״̬����
		TxhdXmShForm upForm = new TxhdXmShForm();
		upForm.setSqid(sqid);
		upForm.setShzt(Constants.YW_SHZ);
		TxhdXmjgService jjs = new TxhdXmjgService();
		result = dao.runUpdate(upForm, sqid)
				&& jjs.deleteForSh(sqid) ? true : false;
		upForm = dao.getModel(sqid);
		// ���ô�����Ϣ
		dbcz.cancel(upForm.getSqid(), upForm.getSplc());
		return result;
	}
	
	
	/**
	 * @throws Exception  
	 * @����:�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����06:06:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	private void checkRskz(String xmdm, String gwid) throws Exception {
		
		TxhdXmszDao txhdXmszDao = new TxhdXmszDao();
		Map<String,String> xmszMap = txhdXmszDao.getInfoById(xmdm);
		
		//����������ޡ�������š���������
		String shrssx = xmszMap.get("shrssx");	//�����������
		String xmszXh = xmszMap.get("xh");   // ��Ŀ����������
		String shlc = xmszMap.get("shlc");	//��Ŀ���õ��е��������
		
		//δ���þͲ�����
		if (StringUtil.isNull(shrssx)){
			return ;
		}
		
		//��ȡ��ǰ��˸�λ���
		ShlcDao shlcDao = new ShlcDao();
		String dqshXh = shlcDao.getGwxh(shlc,gwid); 
		
		//��ǰ��˼����Ƿ���ڵ�����Ŀ���Ƽ���
		if(Integer.valueOf(dqshXh)>=Integer.valueOf(xmszXh)){
			
			//��ͨ������
			String tgrs = dao.getTgrsByXmdm(xmdm, gwid);
			
			if (!(Integer.valueOf(tgrs) < Integer.valueOf(shrssx))){
				
				throw new SystemException(MessageKey.RSKZ_FAIL,tgrs);
			}
		}

	}
	
}

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-5-22 ����05:53:44 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgDao;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgForm;
import net.sf.json.JSONArray;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: ��������-�ҵ�����-�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-5-22 ����05:42:24 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmshService extends SuperServiceImpl<XmshForm,XmshDao>{
	private XmshDao dao = new XmshDao();
	public static final String DEFAULT_PMFS = "fs";// Ĭ��������ʽ
	public static final String SQSH = "1";
	private ShlcInterface shlc = new CommShlcImpl();
	public XmshService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: ��˲�ѯ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-25 ����03:32:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAudingListSingle(XmshForm t,User user) 
		throws Exception {
		CsszDao csszDao = new CsszDao();
		CsszForm csszForm = csszDao.getModel();

		List<HashMap<String, String>> zcxmList = dao.getZcxmList(csszForm.getXn());
		return dao.getAudingListSingle(t, user, DEFAULT_PMFS, zcxmList);
	}

	/** 
	 * @����: �����ϸ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-24 ����02:28:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmshShmx(XmshForm model, User user) throws Exception {
		return dao.getXmshShmx(model,user);
	}
	
	/**
	 * @����: ��˲���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-25 ����03:34:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String saveZdPlsh(XmshForm t, User user) throws Exception {
		/*��ѯ������ID�����״̬���GWID��ѧ�š����״̬���LCID*/
		List<HashMap<String, String>> shjgList = dao.getZdshjgList(t,user);
		if(null==shjgList||shjgList.size()==0){
			return MessageUtil.getText("��ѯ���Ϊ��");
		}
		String[] ids = new String[shjgList.size()];
		String[] gwids = new String[shjgList.size()];
		String[] xhs = new String[shjgList.size()];
		String[] splcs = new String[shjgList.size()];
		
		for (int i = 0; i < shjgList.size(); i++) {
			ids[i] = shjgList.get(i).get("id");
			gwids[i] = shjgList.get(i).get("gwid");
			xhs[i] = shjgList.get(i).get("xh");
			splcs[i] = shjgList.get(i).get("lcid");
		}
		
		List<String> failXhs = new ArrayList<String>();
		List<String> bbccXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			XmshForm model = new XmshForm();
			/*����id*/
			model.setId(ids[i]);
			
			HashMap<String, String> tmpMap  = new HashMap<String,String>();
			tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			XmshForm s = dao.getModel(model);
			if (tmpMap.get("xjzd2") == null || tmpMap.get("xjzd2").equals("")) {
				model.setXmdm(s.getXmdm());
				model.setPdjx(s.getXmdm());
			} else {
				model.setXmdm(tmpMap.get("xjzd2"));
				model.setPdjx(s.getXmdm());
			}
			
			model.setGwid(gwids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setThgw(null);
			model.setSplc(tmpMap.get("lcid"));
			model.setXh(s.getXh());
			model.setXn(s.getXn());
			model.setFjxx(s.getFjxx()); // ����id
			/*��ӵ�����ֶ�*/
			model.setWysp(s.getWysp());
			model.setSsdh(s.getSsdh());
			model.setGzzw(s.getGzzw());
			model.setGrxxjl(s.getGrxxjl());
			model.setCjkyqk(s.getCjkyqk());
			model.setDwrs(s.getDwrs());
			
			Map<String, Object> resultMap = runAudingNotCheck(model, user);
			/*��resultMap��result����ֵΪfalse��ʱ����ô���ش���ѧ��*/
			if(!(Boolean)resultMap.get("result")){
				failXhs.add(xhs[i]);
				/*��resultMap��result����ֵΪfalse��ʱ��(��ʱΪ5������ж�ֵ)����ô���ش���ѧ��*/
				if(resultMap.get("msg").equals("false")){
					bbccXhs.add(xhs[i]);
				}
			}
		}
		
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			if(bbccXhs.isEmpty()){
				return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
			}else{
				/*������жϷ��ش����ѧ�Ų�Ϊ��ʱ������message*/
				return MessageUtil.getText(MessageKey.PJPY_WDPJ_JXRYSH_FAIL, JSONArray.fromObject(bbccXhs).toString());
			}
			
		}
		
	}
	
	/**
	 * @����: ��˲���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-25 ����04:30:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public Map<String,Object> runAudingNotCheck(XmshForm t,User user) throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		/*��˲���Model��ʼ��*/
		ShxxModel model = new ShxxModel();
		
		model.setShlc(t.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getGwid());
		model.setYwid(t.getId());
		
		model.setSqrid(t.getXh());
		model.setTzlj("xpjpy_wdpj_pjsh.do");
		model.setTzljsq("xpjpy_wdpj_pjsq.do");
		model.setZd1("��������");
		model.setZd2(t.getPdjx());
		XmwhForm dcForm = new XmwhForm();
		XmwhDao xmdao = new XmwhDao();
		dcForm.setXmdm(t.getPdjx());
		dcForm = xmdao.getModel(dcForm);
		model.setZd3(dcForm.getXmmc());
		
		/*�жϵ�ǰ�����Ƿ���������л�ͨ����¼*/
		String checkXm = dao.checkXhsqsh(t.getXn(),t.getPdjx(), t.getXh(),t.getId());
		if(Integer.valueOf(checkXm) > 0){
			throw new SystemException(MessageKey.PJPY_FAIL);
		}
		
		boolean result = false;
		try {
			/*�ж��������*/
			HashMap<String,String> shxx =ShlcDao.getDqjbZdByCondition(t.getId(), user.getUserName(), t.getSplc(), "sh");
			/*���ǰһ�������Ŀ����*/
			String tzhxmdm = "";
			/*��ȡ���������� ��ţ���Ҫ����˿��Ƽ�������*/
			String rskzxh = dao.getRskzXh(t.getPdjx());
			
			/*������ͨ����������˼�����ڵ��ڿ��Ƽ��𣬸��µ�������Ŀ����*/
			if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!=null) && new Integer(shxx.get("xh")).intValue() >= new Integer(rskzxh).intValue()){
				tzhxmdm = t.getPdjx();
			}
			
			/*===============5����������￪ʼ�ж�{��Ṥ�������´�ҵ��������񡢶��⽻�����������}=================*/
			/* 1�����ݿ��Ƽ����ж��Ǵ���һ����5���������
			 * 2����˲���Ϊͨ���Ž����ж�
			 * 3����Ų�Ϊ��
			 * 4����ǰ��˲�������ź�������Ŀ���������Ƽ������һ��
			 */
			if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!= null) && rskzxh.equals(shxx.get("xh"))){
				/*��˲����ж�5������ܺ��Ƿ񳬹�ѧԺ��������������35%*/
				String shgzbb = "��Ṥ�����";
				String cxcybb = "���´�ҵ���";
				String gyfwbb = "���������";
				String dwjlbb = "���⽻�����";
				String wthdbb = "�������";
				String xmmc = dcForm.getXmmc();
				if(shgzbb.equals(xmmc) || cxcybb.equals(xmmc) || gyfwbb.equals(xmmc) || dwjlbb.equals(xmmc) || wthdbb.equals(xmmc)){
					/*ѧ������ѧԺ���ܲ���������35%(����С��),{5�������ԭ��������}*/
					float yzsrs = Float.parseFloat(dao.getXyCprsAll(t.getXh()));
					int yzsrsNum = (int)yzsrs;
					/*�е�ѧԺ����������35%��С����ӦѧУҪ����С����+1����*/
					yzsrsNum += 1;
					
					/*ѧ������ѧԺ�Ѿ�ͨ����5�� �����ͨ������*/
					float bbytgcs = Float.parseFloat(dao.getBbYtgCs(t.getXh(),t.getGwid()));
					int bbytgcsNum = (int)bbytgcs;
					/*��Ϊ�������Ѿ����С����+1������������������ͨ������������ô��1�ٽ����ж�*/
					if(bbytgcsNum + 1 > yzsrsNum){
						/*���ص�resultΪfalse��������*/
						resultMap.put("result", result);
						/*���ص�resultΪfalse���ַ���*/
						resultMap.put("msg", "false");
						return resultMap;
					}
				}
			}
			/*===============5���������������ж�{��Ṥ�������´�ҵ��������񡢶��⽻�����������}=================*/
			
			
			String zhzt = shlc.runAuditing(model);
			XmshForm upForm = new XmshForm();
			upForm.setId(t.getId());
			upForm.setShzt(zhzt);
			
			upForm.setTzhxmdm(tzhxmdm);
			upForm.setDqxmdm(t.getPdjx());
			result = dao.runUpdate(upForm, t.getId());
			if(result && zhzt.equals(Constants.SH_TG)){
				/*���뵽�����*/
				XmshForm  viewModel = dao.getModel(t.getId());
				XmwhForm xmwhModel = new XmwhDao().getModel(t.getPdjx());
				PjjgForm pjjgModel = new PjjgForm();
				/*�������������Դ��0:���롢1:�������� 2:��̨���ӡ�*/
				pjjgModel.setSjly("1");
				pjjgModel.setId(viewModel.getId());
				pjjgModel.setXmdm(t.getPdjx());
				pjjgModel.setXmje(xmwhModel.getXmje());
				pjjgModel.setXmmc(xmwhModel.getXmmc());
				pjjgModel.setXn(viewModel.getXn());
				pjjgModel.setSqly(viewModel.getSqly());
				pjjgModel.setSqsj(viewModel.getSqsj());
				pjjgModel.setXh(viewModel.getXh());
				pjjgModel.setXzdm(xmwhModel.getXzdm());
				pjjgModel.setLxdm(xmwhModel.getLxdm());
				pjjgModel.setLylcywid(t.getId());
				/*��ӵ�����ֶ�*/
				pjjgModel.setWysp(viewModel.getWysp());
				pjjgModel.setSsdh(viewModel.getSsdh());
				pjjgModel.setGzzw(viewModel.getGzzw());
				pjjgModel.setGrxxjl(viewModel.getGrxxjl());
				pjjgModel.setCjkyqk(viewModel.getCjkyqk());
				pjjgModel.setDwrs(viewModel.getDwrs());
				/*ѧ�������༶*/
				HashMap<String, String> cpbjxx = dao.getCpbjListByXh(t.getXh(), t.getXn());
				pjjgModel.setCpnj(cpbjxx.get("nj"));
				pjjgModel.setCpxymc(cpbjxx.get("xymc"));
				pjjgModel.setCpzymc(cpbjxx.get("zymc"));
				pjjgModel.setCpbjmc(cpbjxx.get("bjmc"));
				pjjgModel.setCpxydm(cpbjxx.get("xydm"));
				pjjgModel.setCpzydm(cpbjxx.get("zydm"));
				pjjgModel.setCpbjdm(cpbjxx.get("bjdm"));
				
				pjjgModel.setLrr(user.getUserName()); //����¼����(��������ָ�����)
				pjjgModel.setYlzd5(t.getFjxx()); // ����id
				PjjgDao pjjgDao = new PjjgDao();
				try {
					pjjgDao.runInsert(pjjgModel);
				} catch (Exception e) {
					logger.debug("������˲�����ʽ��ʧ�ܣ�"+pjjgModel.getId());
				}
			}
			
		}catch (Exception e) {
			resultMap.put("result", false);
			resultMap.put("msg", e.getMessage());
		}
		/*���ûʲô����Ļ�������resultΪtrue*/
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * @����: �������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-26 ����11:44:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwid
	 * @param xmdm
	 * @param xh
	 * @param type
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	private void checkRskz(String gwid, String xmdm, String xh, String type) throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		Map<String, String> xmwhMap = xmwhDao.getDataById(xmdm);
		/*�������Ʒ�Χ/����*/
		String rskzfw = xmwhMap.get("rsfpfs");
		String xn = xmwhMap.get("xn");
		Map<String, String> rsszMap = dao.getRsszOne(xmdm, xh, xn);
		String xzrs = rsszMap.get("zzme");
		// δ���þͲ�����
		if (StringUtil.isNull(xzrs)) {
			return;
		}
		String tgrs = null;
		tgrs = dao.getTgrsByXy(xn,gwid, xmdm, xh);
		if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))) {
			if("sh".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL, tgrs);
			}else if("cx".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL_CANCEL, tgrs);
			}
		}
	}
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-26 ����11:28:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, XmshForm model, User user)
		throws Exception {
		ShlcInterface service = new CommShlcImpl();
		// �ж��������
		HashMap<String, String> shxx = ShlcDao.getDqjbZdByCondition(ywid, user.getUserName(), model.getSplc(), "cx");
		// ���ǰһ�������Ŀ����
		String tzhxmdm = shxx.get("zd2");
		String rskzxh = dao.getRskzXh(tzhxmdm);
		String dqxmdm = shxx.get("sjxmdm"); // �ϼ������Ŀ����
		String shzt = Constants.YW_SHZ;
		// �����Ŀ����͵�ǰ�û���Ŀ���벻һ�£��ж��Ƿ��������ͨ����¼
		String checkXm = dao.checkXhsqsh(model.getXn(),shxx.get("zd2"), model.getXh(),model.getId());

		if (Integer.valueOf(checkXm) > 0) {
			throw new SystemException(MessageKey.PJPY_FAIL);
		}
		//�����ǰ��¼����˲�ͨ����������˼�����ڵ��ڿ��Ƽ���
		int shxxXh = new Integer(shxx.get("xh"));
		
		if(Constants.SH_BTG.equals(shxx.get("shzt"))&& shxxXh > 1 && shxxXh >= new Integer(rskzxh).intValue()){
			String spgw = new ShlcDao().getBeforeGwid(shxx.get("splc"), shxx.get("gwid"));
			checkRskz(spgw,dqxmdm,model.getXh(), "cx");
		}
		if (new Integer(shxx.get("xh")).intValue() <= new Integer(rskzxh).intValue()) {
			tzhxmdm = "";
		}
		String message = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		// �ع�������еĵ�������Ŀ
		dao.updateSqjl(ywid, dqxmdm, shzt);
		return message;
	}
	
	
	
	/**
	 * @����: ��󼶵ĳ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-26 ����03:29:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param ywid
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(String shlc, String ywid, User user) throws Exception {
		XmshForm upForm = new XmshForm();
		upForm.setId(ywid);
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, ywid);
		if (result) {
			// �ع�����������
			PjjgDao jgdao = new PjjgDao();
			jgdao.delJgb(ywid);
		}
		return result;
	}
}

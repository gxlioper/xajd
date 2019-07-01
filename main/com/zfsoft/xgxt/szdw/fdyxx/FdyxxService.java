/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����02:47:42 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.PicDAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.xgsz.CsszDao;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XgzdModel;

/** 
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-7-9 ����02:47:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyxxService extends SuperAuditService<FdyxxModel, FdyxxDao> {

	private ShlcInterface shlc = new CommShlcImpl();
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(FdyxxModel model) {
		return false;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	@Override
	public boolean deleteResult(FdyxxModel model) {
		return false;
	}
	
	
	public HashMap<String,String> getFdyInfo(String zgh){
		return dao.getFdyInfo(zgh);
	}
	public HashMap<String,String> getFdyInfo1(String zgh){
		return dao.getFdyInfo1(zgh);
	}
	/**
	 * @throws Exception  
	 * @����:�����޸�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-7-16 ����09:20:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fdyxxForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public boolean saveXgsq(FdyxxModel fdyxxForm) throws Exception {
		
		boolean result = true;
		if(!Constants.SH_TH.equals(fdyxxForm.getShjg())){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			fdyxxForm.setSqid(guid);
			fdyxxForm.setShjg(Constants.YW_WTJ);
		}
		
//		dao.deleteShlc(fdyxxForm);
		dao.deleteXgsq(fdyxxForm);
		String xgzd = fdyxxForm.getXgzdJson();
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, fdyxxForm.getSqid());
		}
		//�����״̬��Ϊ���˻ء�ʱ���Ž��в���
		if(!Constants.SH_TH.equals(fdyxxForm.getShjg())){
			dao.insertXgsq(fdyxxForm);	
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public boolean saveTjsq(FdyxxModel model) throws Exception {
		boolean result = true;
		if(!Constants.SH_TH.equals(model.getShjg())){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			model.setSqid(guid);
			model.setShjg(Constants.YW_SHZ);
		}

		CsszDao csszDao = new CsszDao();
		CsszModel cssz = csszDao.getModel();
		String shlid = cssz.getSplc();
		String xgzd = model.getXgzdJson();
		
		dao.deleteXgsq(model);
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		 
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, model.getSqid());
		}
//		if (StringUtil.isNull(shlid)  || "wxsh".equals(shlid)) {// �������
//			model.setShjg(Constants.YW_TG);
//			dao.runUpdate(model);
//		} else {
			result = shlc.runSubmit(model.getSqid(), shlid, model.getXh(),"szdw_fdyxx_xgsh.do", "szdw_fdyxx_xgsq.do");
//		}
		if(!Constants.SH_TH.equals(model.getShjg())){		
			dao.insertXgsq(model);	//���������¼	
		} else {
			dao.updateShzt(model.getSqid(), Constants.YW_SHZ);  //�������������״̬
		}
		return result;
	}
	
	

	/** 
	 * @����:�޸��ֶ���Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-7-16 ����10:24:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXgzdList(String sqid) {
		
		return dao.getXgzdList(sqid);
	}

	public HashMap<String,String> getBmdm(String bmdm){
		return dao.getBmdm(bmdm);
	}
	/** 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-7-16 ����10:29:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getDshDataByZgh(String zgh) {
		HashMap<String, String> map = dao.getDshByZgh(zgh);
		String sqid = null;
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}

	/** 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-7-16 ����10:29:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getShzDataByZgh(String zgh) {
		HashMap<String, String> map = dao.getDataByZgh(zgh, Constants.YW_SHZ);
		String sqid = null;
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}

	/** 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-7-16 ����10:29:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getShxxByZgh(String zgh) {
		return dao.getShxxByXh(zgh);
	}
	
	
	public List<HashMap<String, String>> getWclPageList(FdyxxModel model, User user) throws Exception{
		return dao.getWclPageList(model, user);
	}
	
	
	/*****����Ա��Ϣ�޸����*********/
	@SuppressWarnings("rawtypes")
	@TransactionControl
	public boolean savePlsh(ShxxModel model, String dataJson) throws Exception{
		
		List list = JsonUtil.jsonToList("{data:" + dataJson + "}");
		
		for (Object object : list) {
			net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;
			String gwid = (String) bean.get("gwid");
			String ywid = (String) bean.get("ywid");
			String zgh = (String) bean.get("zgh");
			
			model.setGwid(gwid);
			model.setYwid(ywid);
			model.setSqrid(zgh);
			saveXgsh(model);
		}
		
		return true;
	}
	
	
	/*****����Ա��Ϣ�޸����*********/
	@SuppressWarnings("unchecked")
	@TransactionControl
	public boolean saveXgsh(ShxxModel model) throws Exception {
		boolean result = true;
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		String shlid = csszModel.getSplc();
		model.setShlc(shlid);
		model.setTzlj("szdw_fdyxx_xgsh.do");
		model.setTzljsq("szdw_fdyxx_xgsq.do");
		String shzt = shlc.runAuditingNotCommit(model);
		if (shzt != null) {
			result = dao.updateShztNotCommit(model.getYwid(), shzt);
			
			if (shzt.equals(Constants.SH_TG)) {
				String sqid = model.getYwid();
				List<HashMap<String,String>> infoList = dao.getXgzdList(sqid);
				FdyxxModel fdyxxModel = new FdyxxModel();
				String zgh = model.getSqrid();
				fdyxxModel.setZgh(zgh);
				boolean flag = false;
				for (HashMap<String,String> map : infoList){
					String property = map.get("zd");
					
					//��ͥ��Ա
					if ("jtcyList".equals(property)){
//						List<HjjlModel> hjxxList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",HjjlModel.class);
						List<JtcyModel> jtcyList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JtcyModel.class);
						dao.updateJtcyxx(zgh,jtcyList);
					} else if ("gwydList".equals(property)){
						//��λ�춯
						List<GwydModel> gwydList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GwydModel.class);
						dao.updateGwydxx(zgh,gwydList);
					} else if ("jtjlList".equals(property)){
						//�������
						List<JtjlModel> jtjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JtjlModel.class);
						dao.updateJtjlxx(zgh,jtjlList);
					} else if ("zyjszwList".equals(property)){
						//רҵ����ְ����Ϣ
						List<ZyjsgwModel> zyjszwList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZyjsgwModel.class);
						dao.updateZyjsgwxx(zgh,zyjszwList);
					} else if ("xxjlList".equals(property)){
						//ѧϰ������Ϣ
						List<XxjlModel> xxjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",XxjlModel.class);
						dao.updateXxjlxx(zgh,xxjlList);
					} else if ("gzjlList".equals(property)) {
						//����������Ϣ
						List<GzjlModel> gzjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GzjlModel.class);
						dao.updateGzjlxx(zgh,gzjlList);
					} else if ("zyzgzsList".equals(property)) {
						//ְҵ�ʸ�֤��
						List<ZyzgzsModel> zyzgzsList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZyzgzsModel.class);
						dao.deleteZyzgzs(zgh);
						dao.insertZyzgzs(zgh, zyzgzsList);	
					} else if ("zzjcqkList".equals(property)) {
						//�������̲����
						List<ZzjcqkModel> zzjcqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZzjcqkModel.class);
						dao.deleteZzjcqk(zgh);
						dao.insertZzjcqk(zgh, zzjcqkList);
					} else if ("fmczList".equals(property)) {
						//������������
						List<FmczModel> fmczList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",FmczModel.class);
						dao.deleteFmcz(zgh);
						dao.insertFmcz(zgh, fmczList);
					} else if ("jxkyList".equals(property)) {
						//��ѧ���гɹ����������������
						List<JxkyModel> jxkyList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JxkyModel.class);
						dao.deleteJxky(zgh);
						dao.insertJxky(zgh, jxkyList);
					} else if ("fdyxzgzList".equals(property)) {
						//����Ա����������
						List<FdyxzgzModel> fdyxzgzList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",FdyxzgzModel.class);
						dao.deleteFdyxzgz(zgh);
						dao.insertFdyxzgz(zgh, fdyxzgzList);
					} else if ("gzqkList".equals(property)) {
						//��������
						List<GzqkModel> gzqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GzqkModel.class);
						dao.deleteGzjl(zgh);
						dao.insertGzjl(zgh, gzqkList);
					} else if ("zypxList".equals(property)) {
						//ְҵ��ѵ
						List<ZypxModel> zypxList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZypxModel.class);
						dao.deleteZypx(zgh);
						dao.insertZypx(zgh, zypxList);
					}else if ("xmxxList".equals(property)) {
						//��Ŀѧϰ
						List<XmxxModel> xmxxList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",XmxxModel.class);
						dao.deleteXmxx(zgh);
						dao.insertXmxx(zgh, xmxxList);
					}else if ("gxjlList".equals(property)) {
						//��У����
						List<GxjlModel> gxjlList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",GxjlModel.class);
						dao.deleteGxjl(zgh);
						dao.insertGxjl(zgh, gxjlList);
					}else if ("jxqkList".equals(property)) {
						//��ѧ���
						List<JxqkModel> jxqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",JxqkModel.class);
						dao.deleteJxqk(zgh);
						dao.insertJxqk(zgh, jxqkList);
					}else if ("kylwfbList".equals(property)) {
						//����������ķ���
						List<KylwfbModel> kylwfbList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",KylwfbModel.class);
						dao.deleteKylwfb(zgh);
						dao.insertKylwfb(zgh, kylwfbList);
					}else if ("kyktfbList".equals(property)) {
						//����������ķ���
						List<KyktfbModel> kyktfbList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",KyktfbModel.class);
						dao.deleteKyktfb(zgh);
						dao.insertKyktfb(zgh, kyktfbList);
					}else if ("zgzsList".equals(property)) {
						//�ʸ�֤��
						List<ZgzsModel> zgzsList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZgzsModel.class);
						dao.deleteZgzs(zgh);
						dao.insertZgzs(zgh, zgzsList);
					}else if ("gryyqkList".equals(property)) {
						//����Ա�����ڼ�����õĸ����������
						List<ZxhjqkModel> zxhjqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",ZxhjqkModel.class);
						dao.deleteZxhjqk(zgh);
						dao.insertZxhjqk(zgh, zxhjqkList);
					}else if ("xxryqkList".equals(property)) {
						//����Ա�����ڼ�����õĸ����������
						List<BjyyqkModel> bjyyqkList = JsonUtil.jsonToList("{data:" +map.get("zdz")+ "}",BjyyqkModel.class);
						dao.deleteBjyyqk(zgh);
						dao.insertBjyyqk(zgh, bjyyqkList);
					}else {
//						try{
							Method setMethod = fdyxxModel.getClass().getMethod("set"+property.substring(0, 1).toUpperCase()+property.substring(1),String.class);
							setMethod.invoke(fdyxxModel, map.get("zdz"));
							flag = true;
//						}catch(Exception e){
//							logger.error("model���޴��ֶΡ���>"+map.get("zd"));
//						}
					}
				}
				if(flag){
					result = dao.runUpdateNotCommit(fdyxxModel);
				}
			}
		}
		return result;
	}
	
	
	/**�񽱾���***/
	public List<HashMap<String,String>> getHjxxList(String zgh){
		return dao.getHjxxList(zgh);
	}
	
	/**��ѵ����***/
	public List<HashMap<String,String>> getPxjlList(String zgh){
		return dao.getPxjlList(zgh);
	}
	
	/**�����ɹ�***/
	public List<HashMap<String,String>> getQtcgList(String zgh){
		return dao.getQtcgList(zgh);
	}
	
	/**������Ŀ***/
	public List<HashMap<String,String>> getKyxmList(String zgh){
		return dao.getKyxmList(zgh);
	}
	
	/**��������***/
	public List<HashMap<String,String>> getFblwList(String zgh){
		return dao.getFblwList(zgh);
	}
	
	/**��ѧ����***/
	public List<HashMap<String,String>> getJxgzList(String zgh){
		return dao.getJxgzList(zgh);
	}
	
	/**ְҵ�ʸ�֤��***/
	public List<HashMap<String,String>> getZyzgzsList(String zgh){
		return dao.getZyzgzsList(zgh);
	}
	
	/**�������̲����***/
	public List<HashMap<String,String>> getZzjcqkList(String zgh){
		return dao.getZzjcqkList(zgh);
	}
	
	/**������������***/
	public List<HashMap<String,String>> getFmczList(String zgh){
		return dao.getFmczList(zgh);
	}
	
	/**��ѧ���гɹ����������������***/
	public List<HashMap<String,String>> getJxkyList(String zgh){
		return dao.getJxkyList(zgh);
	}

	/**����Ա����������***/
	public List<HashMap<String,String>> getFdyxzgzList(String zgh){
		return dao.getFdyxzgzList(zgh);
	}
	/**����Ա��������*/
	public List<HashMap<String,String>> getGzjlList(String zgh){
		return dao.getGzjlList(zgh);
	}
	/**����Աְҵ��ѵ*/
	public List<HashMap<String,String>> getZypxList(String zgh){
		return dao.getZypxList(zgh);
	}
	/**����Աѧϰ��Ϣ*/
	public List<HashMap<String,String>> getXmxxList(String zgh){
		return dao.getXmxxList(zgh);
	}
	/**����Ա��У����*/
	public List<HashMap<String,String>> getGxjlList(String zgh){
		return dao.getGxjlList(zgh);
	}
	/**����Ա��ѧ���*/
	public List<HashMap<String,String>> getJxqkList(String zgh){
		return dao.getJxqkList(zgh);
	}
	/**����Ա�������ķ���*/
	public List<HashMap<String,String>> getKylwfbList(String zgh){
		return dao.getKylwfbList(zgh);
	}
	/**����Ա���п��ⷢ��*/
	public List<HashMap<String,String>> getKyktfbList(String zgh){
		return dao.getKyktfbList(zgh);
	}
	/**����Ա�ʸ�֤��*/
	public List<HashMap<String,String>> getZgzsList(String zgh){
		return dao.getZgzsList(zgh);
	}
	/**����Ա�����ڼ�����õĸ����������*/
	public List<HashMap<String,String>> getZxhjqkList(String zgh){
		return dao.getZxhjqkList(zgh);
	}
	/**����Ա�����ڼ�����õİ༶�������*/
	public List<HashMap<String,String>> getBjyyqkList(String zgh){
		return dao.getBjyyqkList(zgh);
	}
	/**
	 * 
	 * @����: ��ѵ�����޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-16 ����10:17:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updatePxjl(String zgh, List<PxjlModel> list) throws Exception {
		return dao.updatePxjl(zgh, list);
	}
	
	/**
	 * 
	 * @����: ��ѧ�����޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-16 ����10:17:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateJxgz(String zgh, List<JxgzModel> list) throws Exception {
		return dao.updateJxgz(zgh, list);
	}
	
	/**
	 * 
	 * @����: ְҵ�ʸ�֤���޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-16 ����10:17:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateZyzgzs(String zgh, List<ZyzgzsModel> list) throws Exception {
		return dao.updateZyzgzs(zgh, list);
	}
	
	/**
	 * 
	 * @����: �������̲�����޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-16 ����04:21:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateZzjcqk(String zgh, List<ZzjcqkModel> list) throws Exception {
		return dao.updateZzjcqk(zgh, list);
	}
	
	/**
	 * 
	 * @����: �������������޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-17 ����09:26:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateFmcz(String zgh, List<FmczModel> list) throws Exception {
		return dao.updateFmcz(zgh, list);
	}
	
	/**
	 * 
	 * @����: ��ѧ���гɹ�����������������޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-17 ����02:01:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateJxky(String zgh, List<JxkyModel> list) throws Exception {
		return dao.updateJxky(zgh, list);
	}
	
	/**
	 * 
	 * @����: ������޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-17 ����03:20:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateHjjl(String zgh, List<HjjlModel> list) throws Exception {
		return dao.updateHjjl(zgh, list);
	}
	
	/**
	 * 
	 * @����: ���ķ�������޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-17 ����04:28:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateFblw(String zgh, List<FblwModel> list) throws Exception {
		return dao.updateFblw(zgh, list);
	}
	
	/**
	 * 
	 * @����: �����ɹ��޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-17 ����04:28:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateQtcg(String zgh, List<QtcgModel> list) throws Exception {
		return dao.updateQtcg(zgh, list);
	}
	
	/**
	 * 
	 * @����: ������Ŀ����޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-18 ����09:03:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateKyxm(String zgh, List<KyxmModel> list) throws Exception {
		return dao.updateKyxm(zgh, list);
	}
	
	/**
	 * 
	 * @����: ����Ա�����������޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-18 ����10:46:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateFdyxzgz(String zgh, List<FdyxzgzModel> list) throws Exception {
		return dao.updateFdyxzgz(zgh, list);
	}
	
	//fdyxxbȡֵ���������
	public HashMap<String, String> getFdyxxMap(String zgh){
		return dao.getFdyxxMap(zgh);
	}
	
	//ͨ��word�������ʦ��Ƭ
	public String getPhoto(String zgh) throws Exception{
		PicDAO picDAO = new PicDAO();
	    InputStream is = picDAO.getPic(zgh, "fdy");
	    File photoFile = FileUtil.inputstreamToFile(is, "doc");
	    String photo = FileUtil.getBASE64(photoFile);
	    return photo;
	}
	
	/**
	 * 
	 * @����:������ҽҩ��ѧ��ʦ��ݱ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-19 ����02:05:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zghs
	 * @param jssf
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savejssfPlwh(String[] zghs,String jssf) throws Exception{
		return dao.savejssfPlwh(zghs, jssf);
	}
	
	/** 
	 * @����:��ȡ�����ڼ�ѧ������(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-30 ����02:56:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsryList(String zgh) {
		
		return dao.getXsryList(zgh);
	}
	/** 
	 * @����:����Ա�ڼ��������(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-30 ����02:56:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGrryList(String zgh) {
		
		return dao.getGrryList(zgh);
	}

	/** 
	 * @������������� ����(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-30 ����03:11:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKylwList(String zgh) {
		return dao.getKylwList(zgh);
	}
	/** 
	 * @�����������������(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-30 ����03:11:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKyktList(String zgh) {
		return dao.getKyktList(zgh);
	}
	
	
	/** 
	 * @��������������(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-30 ����03:11:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGzjlList(String zgh,String num) {
		return dao.getGzjlList(zgh,num);
	}

	/**
	 * �յ�list
	 */
	public void addKList(List<HashMap<String,String>> list, int size){
		if(list.size()<size){
			for (int i = 0 ; i <= size-list.size() ; i++){
				list.add(new HashMap<String, String>());
			}
		}
	}

	/** 
	 * @����:����������(������һ�仰�����������������)
	 * @���ߣ�linguoxia[���ţ�1553]
	 * @���ڣ�2018-2-6 ����04:44:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getDbzrs(String zgh) {
		return dao.getDbzrs(zgh);
	}
	//1529 jz
	/**������ͨ��������*/
	public boolean updateGzjl(String zgh, List<GzqkModel> list) throws Exception {
		return dao.updateGzjl(zgh, list);
	}
	/**������ְͨҵ��ѵ*/
	public boolean updateZypx(String zgh, List<ZypxModel> list) throws Exception {
		return dao.updateZypx(zgh, list);
	}
	/**������ͨ��Ŀѧϰ*/
	public boolean updateXmxx(String zgh, List<XmxxModel> list) throws Exception {
		return dao.updateXmxx(zgh, list);
	}
	/**������ͨ��У����*/
	public boolean updateGxjl(String zgh, List<GxjlModel> list) throws Exception {
		return dao.updateGxjl(zgh, list);
	}
	/**������ͨ��ѧ���*/
	public boolean updateJxqk(String zgh, List<JxqkModel> list) throws Exception {
		return dao.updateJxqk(zgh, list);
	}
	/**������ͨ�������ķ���*/
	public boolean updateKylwfb(String zgh, List<KylwfbModel> list) throws Exception {
		return dao.updateKylwfb(zgh, list);
	}
	/**������ͨ���п��ⷢ��*/
	public boolean updateKyktfb(String zgh, List<KyktfbModel> list) throws Exception {
		return dao.updateKyktfb(zgh, list);
	}
	/**������ͨ�ʸ�֤��*/
	public boolean updateZgzs(String zgh, List<ZgzsModel> list) throws Exception {
		return dao.updateZgzs(zgh, list);
	}
	/**������ͨ����Ա�����ڼ�����õĸ����������*/
	public boolean updateZxhjqk(String zgh, List<ZxhjqkModel> list) throws Exception {
		return dao.updateZxhjqk(zgh, list);
	}
	/**������ͨ����Ա�����ڼ�����õİ༶ͬѧ�������*/
	public boolean updateBjyyqk(String zgh, List<BjyyqkModel> list) throws Exception {
		return dao.updateBjyyqk(zgh, list);
	}
	//������ͨ����Ƭ����
	public String checkxszpSfcz(String zgh){
		String  zp =  dao.checkxszpSfcz(zgh);
		return "0".equalsIgnoreCase(zp)?"false":"true";
	}
	//�鿴�����������ж���
	public String fdydbrs(String zgh , String zgh1){
		return dao.fdydbrs(zgh, zgh1);
	}
	
	/**
	 * @����: �жϴ��������Ƕ��٣��ж��Ƿ����
	 * @���ߣ�  jz[���ţ�1529]
	 * @���ڣ�2018-2-7 ����02:30:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param zgh1
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String sfdb(String zgh , String zgh1){
		String dbrs = dao.fdydbrs(zgh, zgh1);
		return "0".equalsIgnoreCase(dbrs)?"��":"��";
	}
	//�������ƺʹ�������
	public List<HashMap<String, String>> bjmcAbjrs(String zgh , String zgh1){
		 List<HashMap<String, String>> bjmcabjrs = dao.bjmcAbjrs(zgh, zgh1);
		return dao.bjmcAbjrs(zgh, zgh1);
	}
/**
 * @description	�� ���֤��
 * @author 		�� CP��1352��
 * @date 		��2018-3-9 ����02:00:45
 * @param zgh
 * @return
 */
	public List<HashMap<String,String>> getHdzsList(String zgh) {
		return dao.getHdzsList(zgh);
	}
	public boolean updateHdzs(String zgh, List<HdzsModel> hdzsList) throws Exception {
		return dao.updateHdzs(zgh,hdzsList);
	}

	public boolean updateYjcg(String zgh, List<YjcgModel>  yjcgList) throws Exception {
		return dao.updateYjcg(zgh,yjcgList);
	}

	public List<HashMap<String,String>> getYjcgList(String zgh) {
		return dao.getYjcgList(zgh);
	}

	public boolean updateJtcyxx(String zgh,List<JtcyModel> list) throws Exception {
		return dao.updateJtcyxx(zgh, list);
	}

	public boolean updateGwydxx(String zgh,List<GwydModel> list) throws Exception {
		return dao.updateGwydxx(zgh, list);
	}

	public boolean updateJtjlxx(String zgh,List<JtjlModel> list) throws Exception {
		return dao.updateJtjlxx(zgh, list);
	}

	public boolean updateZyjsgwxx(String zgh,List<ZyjsgwModel> list) throws Exception {
		return dao.updateZyjsgwxx(zgh, list);
	}

	public boolean updateXxjlxx(String zgh,List<XxjlModel> list) throws Exception {
		return dao.updateXxjlxx(zgh, list);
	}

	public boolean updateGzjlxx(String zgh,List<GzjlModel> list) throws Exception {
		return dao.updateGzjlxx(zgh, list);
	}

	public List<HashMap<String,String>> getJtcyxx(String zgh){
		return dao.getJtcyxx(zgh);
	}

	public List<HashMap<String,String>> getGwydxx(String zgh){
		return dao.getGwydxx(zgh);
	}

	public List<HashMap<String,String>> getJtjlxx(String zgh){
		return dao.getJtjlxx(zgh);
	}

	public List<HashMap<String,String>> getZyjsgwxx(String zgh){
		return dao.getZyjsgwxx(zgh);
	}

	public List<HashMap<String,String>> getXxjlxx(String zgh){
		return dao.getXxjlxx(zgh);
	}

	public List<HashMap<String,String>> getGzjlxx(String zgh){
		return dao.getGzjlxx(zgh);
	}

	public List<HashMap<String,String>> getGwxx(String gwdjdm){
		return dao.getGwxx(gwdjdm);
	}
}

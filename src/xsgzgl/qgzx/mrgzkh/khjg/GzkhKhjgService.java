package xsgzgl.qgzx.mrgzkh.khjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglDAO;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cssz.QgzxCsszDAO;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class GzkhKhjgService extends SuperServiceImpl<GzkhKhjgForm, GzkhKhjgDao>{
	GzkhKhjgDao dao = new GzkhKhjgDao();
	
	/**
	 * ��ȡѧ���ڹ���λ
	 */
	public List<HashMap<String,String>> getGwxx(GzkhKhjgForm model){
		return dao.getGwxx(model);
	}
	/**
	 * ��ȡѧ���ڹ���λ��������
	 */
	public List<HashMap<String, String>> getYrbm(GzkhKhjgForm model){
		return dao.getYrbm(model);
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����11:47:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
    public boolean Cjcl(GzkhKhjgForm model) throws Exception{
    	GzkhKhjgForm khjgForm = dao.getKhjg(model);
    	
    	boolean result=true;
    	if(null!=khjgForm){
	    		// ����ѧ�š�ѧ�ꡢ��λ������д����ɾ�������������
	    		dao.delKhjg(model);
    	}else{
    		khjgForm = new GzkhKhjgForm();
    	}
    	//��ȡѧ����𷢷���Ϣ
		QgzxCjglDAO cjglDao = new QgzxCjglDAO();
		QgzxCjglForm cjglForm = cjglDao.getCjffXx(model);
		if(null!=cjglForm){
			//���¼���ѧ�������Ϣ
			Double gs=null;
//			 ֱ��¼�룬�����޸�ʱ����Ҫ��ȥ�޸�ǰ�Ĺ�ʱ
				double khjgFormGs = khjgForm.getGs() == null ? 0 : Double.parseDouble(khjgForm.getGs());
				double cjbz = khjgForm.getCjbz() == null ? 0 : Double.parseDouble(khjgForm.getCjbz());
				gs = Double.parseDouble(cjglForm.getGs())-khjgFormGs
				+Double.parseDouble(model.getGs());

			String je = String.valueOf(Double.parseDouble(cjglForm.getJe())-khjgFormGs*cjbz+Double.parseDouble(model.getGs())*Double.parseDouble(model.getCjbz()));
			cjglForm.setGs(String.valueOf(gs));
			cjglForm.setJe(je);
			result=cjglDao.runUpdate(cjglForm);
		}
		
    	else{
    		String id = UniqID.getInstance().getUniqIDHash();
    		String je = String.valueOf(Double.parseDouble(model.getGs())*Double.parseDouble(model.getCjbz()));
    		result=dao.saveCjxx(id,je, model);
    	}
    	return result;
    	
		
	}
    /**
     * ѧ�������Ϣ���¼���
     */
    public boolean xsCjxxJs(GzkhKhjgForm model)throws Exception{
    	//��ȡѧ����𷢷���Ϣ
		QgzxCjglDAO cjglDao = new QgzxCjglDAO();
		QgzxCjglForm cjglForm = new QgzxCjglForm();
		//��˲�ͨ��������һ����˵�����³����������
		try {
			cjglForm = cjglDao.getCjffXx(model);
		} catch (NullPointerException e) {
			// TODO �Զ����� catch ��
			return true;
		}
		if(null==cjglForm){
			return true;
		}
		//���¼���ѧ�������Ϣ
		Double gs = Double.parseDouble(cjglForm.getGs())-Double.parseDouble(model.getGs());
		QgzxCsszDAO csszDao = new QgzxCsszDAO();
    	HashMap<String,String> csszMap  = csszDao.getCssz();
		String je = String.valueOf(gs*Double.parseDouble(csszMap.get("cjbz")));
		cjglForm.setGs(String.valueOf(gs));
		cjglForm.setJe(je);
		return cjglDao.runUpdate(cjglForm);
    }

	// ����
	public boolean saveKhjg(GzkhKhjgForm model, User user) throws Exception {
		//��ȡ�ڹ���ѧ��������
    	QgzxCsszDAO csszDao = new QgzxCsszDAO();
    	HashMap<String,String> csszMap  = csszDao.getCssz();
		String id = model.getId();
		model.setGs(gsFormat(model.getGs()));
		if ("save".equalsIgnoreCase(model.getType())) {
			id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			model.setCjbz(csszMap.get("cjbz"));
			model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
			
		}else{
			model.setCjbz(dao.getModel(id).getCjbz());
		}
		String[] gwxx = model.getGwdm().split(",");
		model.setGwdm(gwxx[0]);
		model.setXn(gwxx[1]);
		model.setCzyh(user.getUserName());
		model.setCjsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		// �ȸ���н��
		boolean flag = Cjcl(model);
		// �ٱ�������
			flag = dao.runInsert(model);
		return flag;
	}
	
	/**
	 * �жϵ�ǰ��λ�Ƿ�����д��¼
	 */
	public boolean checkExistForSave(GzkhKhjgForm model){
		String[] gwxx = model.getGwdm().split(",");
		model.setXn(gwxx[1]);
		return dao.checkExistForSave(model);
	}
	
	public String gsFormat(String gs){
		int lastStr = gs.indexOf(".");
		if(gs.length()-1==lastStr){
			String[] gsArr= gs.split("\\.");
			gs=gsArr[0] ;
		}
		return gs;
	}
	
	/**
	 * 
	 * @����: ���˽�����ӳ��ʱ�����ж�(����)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-20 ����11:25:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param gs
	 * @param gzrq
	 * @param cjbz
	 * @param sqid
	 * @param gwdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkZjeAndGsJG(String xh, String xn, String gs, String gzrq, String cjbz, String sqid, String gwdm) throws Exception {

		QgzxCsszDAO csszDao = new QgzxCsszDAO();
		GzkhKhsqDao gzkhKhsqDAO = new GzkhKhsqDao();
		HashMap<String, String> gwxxMap = dao.getGwxx(gwdm);
		HashMap<String, String> csszMap = csszDao.getCssz();
		if (null == cjbz) {
			cjbz = csszMap.get("cjbz");
		}
		HashMap<String, String> xsCjxx = gzkhKhsqDAO.getCjffXxOfStu(xh, xn, gzrq);
		String cjgs = xsCjxx.get("gs") == null ? "0" : xsCjxx.get("gs");
		String je = xsCjxx.get("je") == null ? "0" : xsCjxx.get("je");
		String ffje = gzkhKhsqDAO.getCjByGwdm(xh, xn, gwdm, gzrq);
		if ("gs".equals(csszMap.get("sxzd")) && (Double.parseDouble(cjgs) + Double.parseDouble(gs) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		if ("yes".equals(csszMap.get("sfsdgwcjsx"))&&StringUtils.isNotNull(gwxxMap.get("gwcjsx"))
				&& (Double.parseDouble(ffje) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(gwxxMap.get("gwcjsx")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GWJESXOUT, new String[] { gzrq.substring(0, 7), gwxxMap.get("gwmc"), gwxxMap.get("gwcjsx") });
		}
		if ("je".equals(csszMap.get("sxzd"))
				&& (Double.parseDouble(je) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_JEOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		return "true";
	}
	
	/**
	 * 
	 * @����: ���˽�����ӳ��ʱ�����ж�(�޸�)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-20 ����01:48:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param gs
	 * @param gzrq
	 * @param cjbz
	 * @param id
	 * @param gwdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkZjeAndGsJGUp(String xh, String xn, String gs, String gzrq, String cjbz, String id, String gwdm) throws Exception {

		QgzxCsszDAO csszDao = new QgzxCsszDAO();
		GzkhKhsqDao gzkhKhsqDAO = new GzkhKhsqDao();
		HashMap<String, String> gwxxMap = dao.getGwxx(gwdm);
		HashMap<String, String> csszMap = csszDao.getCssz();
		HashMap<String, String> updateMap = dao.checkExistForUpdate(id);
		if (null == cjbz) {
			cjbz = csszMap.get("cjbz");
		}
		HashMap<String, String> xsCjxx = gzkhKhsqDAO.getCjffXxOfStu(xh, xn, gzrq);
		String cjgs = xsCjxx.get("gs") == null ? "0" : xsCjxx.get("gs");
		String je = xsCjxx.get("je") == null ? "0" : xsCjxx.get("je");
		String ffje = gzkhKhsqDAO.getCjByGwdm(xh, xn, gwdm, gzrq);
		if ("gs".equals(csszMap.get("sxzd")) && (Double.parseDouble(cjgs) + Double.parseDouble(gs) - Double.parseDouble(updateMap.get("gs")) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		if ("yes".equals(csszMap.get("sfsdgwcjsx"))&&StringUtils.isNotNull(gwxxMap.get("gwcjsx"))
				&& (Double.parseDouble(ffje) + Double.parseDouble(gs) * Double.parseDouble(cjbz) - Double.parseDouble(updateMap.get("je")) > Double.parseDouble(gwxxMap.get("gwcjsx")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GWJESXOUT, new String[] { gzrq.substring(0, 7), gwxxMap.get("gwmc"), gwxxMap.get("gwcjsx") });
		}
		if ("je".equals(csszMap.get("sxzd"))
				&& (Double.parseDouble(je) + Double.parseDouble(gs) * Double.parseDouble(cjbz) - Double.parseDouble(updateMap.get("je")) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_JEOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		return "true";
	}
}

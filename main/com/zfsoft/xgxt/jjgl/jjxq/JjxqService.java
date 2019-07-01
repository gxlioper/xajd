/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-19 ����02:23:39 
 */  
package com.zfsoft.xgxt.jjgl.jjxq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jjgl.cssz.CsszService;
import com.zfsoft.xgxt.jjgl.xqwh.XqwhForm;
import com.zfsoft.xgxt.jjgl.xqwh.XqwhService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import java.util.*;

/** 
 * @�๦������: �ҽ�����
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-19 ����02:23:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjxqService extends SuperServiceImpl<JjxqForm, JjxqDao> {

	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @����:��ȡmodelMap
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����10:22:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String id) throws Exception{
		return dao.getModelMap(id);
	}
	
	/**
	 * 
	 * @����: �ҽ������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-27 ����10:43:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJjxqList(JjxqForm t,User user) throws Exception{
		return dao.getJjxqList(t,user);
	}
	
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-2 ����04:31:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXssq(JjxqForm t, User user) throws Exception{

		String sqid = UniqID.getInstance().getUniqIDHash();
		t.setSqid(sqid);
		//��ȡ�������������õ��������
		Map<String,String> configMap = new CsszService().getConfigMap();
		String splc = configMap.get("jjtdsplc");
		t.setSplc(splc);
		t.setShzt(Constants.YW_SHZ);// �����

		// ����������Ϣ
		boolean result = dao.saveXssq(t, user);
		// ���������Ϣ
		if (result) {
			result = shlc.runSubmit(sqid, splc, user.getUserName(),
					"jjgl_jjczsh.do", "xsjj_jjsc.do");
		}

		return result;
	}
	
	
	/**
	 * 
	 * @����: �����ҽ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-2 ����04:31:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJsjj(JjxqForm t,User user) throws Exception{

	    boolean result = false;
	    String splc = null;
	    //����pjjsqid jjcz shzt=3��һ���Ƿ����˻ؼ�¼ �еĻ�ֱ���ύ
        //û�����½�һ��
        Map<String,String> tjjSqxxMap = dao.getTjjSqxx(t);

        if(tjjSqxxMap != null&&tjjSqxxMap.size()!=0){
            t.setSqid(tjjSqxxMap.get("sqid"));
            t.setShzt(Constants.YW_SHZ);// �����
            //�޸�����״̬
            result = dao.updateTjjShztBySqid(t);
        }else{
            String sqid = UniqID.getInstance().getUniqIDHash();
            t.setSqid(sqid);
            //��ȡ�������������õ��������
            Map<String,String> configMap = new CsszService().getConfigMap();
            splc = configMap.get("jjtdsplc");
            t.setSplc(splc);
            t.setShzt(Constants.YW_SHZ);// �����

            // ����������Ϣ
            result = dao.saveJsjj(t, user);
        }


		// ���������Ϣ
		if (result) {
			result = shlc.runSubmit(t.getSqid(), splc, user.getUserName(),
					"jjgl_jjczsh.do", "xsjj_wdjj.do");
		}

		return result;
	}
	
	/**
	 * 
	 * @����: ��ѯѧ��������Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-2 ����04:31:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXssqInfo(String sqid){
		return dao.getXssqInfo(sqid);
	}

	/**
	 * ��ȡ�ҽ̲�������б�����
	 * @param jjxqForm
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> getJjczshList(JjxqForm jjxqForm, User user) throws Exception {

		return dao.getJjczshList(jjxqForm,user);
	}

	/**
	 * �ҽ̲�����˵ı���
	 * @param jjxqForm
	 * @param user
	 * @return
	 */
	public boolean jjczshSave(JjxqForm jjxqForm, User user) {

		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(jjxqForm.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(jjxqForm.getShyj());
		// ���״̬
		model.setShzt(jjxqForm.getShjg());
		//��λ�˻�
		model.setThgw(jjxqForm.getThgw());
		// ��˸�λID
		model.setGwid(jjxqForm.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(jjxqForm.getSqid());
		//������id
		model.setSqrid(jjxqForm.getXh());

		model.setTzlj("jjgl_jjczsh.do");
		if("1".equals(jjxqForm.getJjcz())){
            model.setTzljsq( "xsjj_jjsc.do");
        }else {
			model.setTzljsq("xsjj_wdjj.do");
        }

		boolean result = false;
		try {
			String shzt = shlc.runAuditing(model);
			jjxqForm.setShzt(shzt);

			// ���һ�����ͨ�����޸ļҽ�״̬�����ݼҽ̲����жϣ� �ҳ��ҽ������
			// ��Ҫ�����ɳ�ʱ�� ѧ�������
			if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
				//�������״̬���ɳ�ʱ��
                //�ɼҽ�
                if("1".equals(jjxqForm.getJjcz())){
                    jjxqForm.setPcsj(DateUtils.getCurrTime());
                    result = dao.updateShztBySqid(jjxqForm);
                //�˼ҽ�
                }else if("0".equals(jjxqForm.getJjcz())){
					result = dao.updateTjjShztBySqid(jjxqForm);
                //�رռҽ�
                }else if("4".equals(jjxqForm.getJjcz())){
                    jjxqForm.setGbsj(DateUtils.getCurrTime());
                    //���޸��ɼҽ������Ĺر�ʱ�䡡��Ҫ�޸ļҽ̲������˹أ����������״̬
					result = dao.updateGbsjBySqid(jjxqForm);
					if(result){
						result = dao.updateTjjShztBySqid(jjxqForm);
					}
                }

				if(result){
					// �ɼҽ� �˼ҽ� �رռҽ�
					// ��Ҫ����֮ǰ�ļҽ�״̬���������һ��������
					XqwhForm xqwhForm = new XqwhForm();
					xqwhForm.setXqid(jjxqForm.getXqid());
					xqwhForm.setJjzt(jjxqForm.getJjcz());
					result = new XqwhService().runUpdateJjzt(xqwhForm);
				}
			}else {
				//�������״̬
				result = dao.updateShztBySqid(jjxqForm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ����ɼҽ�������Ϣmap
	 * @param sqid
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getJjxqMap(String sqid) throws Exception {
		return dao.getJjxqMap(sqid);
	}

	/**
	 * ����˼ҽ̡��رռҽ�������Ϣmap
	 * @param sqid
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getTjjxqMap(String sqid) {
		return dao.getTjjxqMap(sqid);
	}

	/**
	 * ����pjjsqid��jjcz�жϵ�ǰ�ҽ̲����Ƿ��Ѿ���������
	 * @param jjxqForm
	 * @return
	 */
	public boolean isJjczExist(JjxqForm jjxqForm) {

		String count = dao.isJjczExist(jjxqForm);
		return Integer.parseInt(count) > 0;
	}

	/**
	 * �ҽ̲���������һ������
	 * @param jjxqForm
	 * @return
	 */
	public boolean cancelShLast(JjxqForm jjxqForm) throws Exception {

		jjxqForm.setShzt(Constants.YW_SHZ);
		boolean result = false;
		//�ɼҽ�
		if("1".equals(jjxqForm.getJjcz())){
			result = dao.updateShztBySqid(jjxqForm);
		//�˼ҽ�
		}else if("0".equals(jjxqForm.getJjcz())){
			result = dao.updateTjjShztBySqid(jjxqForm);
		//�رռҽ�
		}else if("4".equals(jjxqForm.getJjcz())){
			result = dao.updateTjjShztBySqid(jjxqForm);
		}
		//��ԭ��֮ǰ�ļҽ�״̬
		if (result) {
			result = new XqwhService().updateJjztForCancel(jjxqForm.getXqid());
		}
		return result;
	}

	public HashMap<String,String> getPjxxMap(String xqid) {

		return dao.getPjxxMap(xqid);
	}

	public Boolean jjpjSave(String pjid,String xqid, String pjzs, String py) throws Exception {

		if(StringUtils.isNull(pjid)){
			return dao.jjpjInsert(xqid,pjzs,py);
		}else {
			return dao.jjpjUpdate(pjid,xqid,pjzs,py);
		}
	}

	public List<HashMap<String,String>> getJjgsxxList(String xqid) throws Exception {

		return dao.getJjgsxxList(xqid);
	}

	public boolean jjgsSave(String jjbh, String jjny, String jjgs) throws Exception {

		return dao.jjgsSave(jjbh,jjny,jjgs);
	}

	public boolean isJjgsExist(String jjbh, String jjny) {

		String count = dao.getJjgsjlCount(jjbh,jjny);
		return Integer.parseInt(count) > 0;
	}
}

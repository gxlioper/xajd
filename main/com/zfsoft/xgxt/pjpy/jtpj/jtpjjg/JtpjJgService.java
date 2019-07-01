/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-5 ����10:34:33 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-5 ����10:34:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjJgService extends
		SuperServiceImplExtend<JtpjJgForm, JtpjJgDao> {
	private JtpjJgDao jjd = new JtpjJgDao();
	/**
	 * �����
	 */
	private final String SJLY_JGK = "0";
	/**
	 * ��������
	 */
	private final String SJLY_LC = "1";

	public JtpjJgService() {
		super.setDao(jjd);
	}

	/**
	 * 
	 * @����: ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-4 ����09:42:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean save(JtpjJgForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		myForm.setSjly(SJLY_JGK);
		return runInsert(myForm);
	}

	/**
	 * @����: ���ͨ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-5 ����02:34:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean shtgSave(JtpjJgForm myForm) throws Exception {
		//myForm.setSqxn(Base.currNd);
		//myForm.setSqxq(Base.currXq);
		myForm.setSjly(SJLY_LC);
		return runInsert(myForm);
	}

	/**
	 * 
	 * @����: �޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-4 ����09:42:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean update(JtpjJgForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		myForm.setSjly(SJLY_JGK);
		return runUpdate(myForm);
	}
	/**
	 * 
	 * @����: ����ɾ���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-5 ����03:16:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean deleteForCx(String sqid) throws Exception {
		return dao.deleteJgForSqid(sqid);
	}
	
	/**
	 * @����: ��ӡWord�ǼǱ��Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-4 ����11:30:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String jgid) {
		return dao.getDjbInfo(jgid);
	}
	
	public HashMap<String, String> getDjb(String jgid) {
		return dao.getDjb(jgid);
	}
	
	//ɽ��������ҽְҵѧԺ���Ի���ӡ(�Ƚ��༯���Ƽ���)
	public File getXmGxhDy_12947_xjbjt(String[] parameters)
		throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> Bzrxxlist = this.getBzrxxlist_12947(parameters);
		data.put("bzrxxlist",Bzrxxlist);
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//jtpj", "sdxmsy_12947_xjbjttjb.xml", "�Ƚ��༯���Ƽ���" );
		return wordFile;

   }
	/**
	 * 
	 * @����:ɽ������ְҵ���Ի������Ƚ��༯���Ƽ���
	 * @���ߣ��Ų�·[���ţ�1206]
	 * @���ڣ�2015-6-5 ����02:17:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	private List<HashMap<String, String>> getBzrxxlist_12947(String[] values){
		return dao.getBzrxxlist(values);
	}
	
	//ɽ��������ҽ�Ƚ��༯�����������ѧ����
	public HashMap<String, String> getRxrq(String jgid){
		StringBuilder sql = new StringBuilder();
		return dao.getRxrq(jgid);
	}
	
	/**
	 * @�������ǼǱ��Զ������������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��7�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param djbMap
	 * void ��������
	 */
	public HashMap<String, String> fillData(HashMap<String, String> djbMap){
		djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
		PjjgDao pjjgDao=new PjjgDao();
		JtpjJgDao jtpjJgDao = new JtpjJgDao();
		HashMap<String, String> splcmap=pjjgDao.getSpxxInfo2ji(djbMap.get("sqid"));
		djbMap.putAll(splcmap);
		if("bj".equals(djbMap.get("jtpjdw"))){
			djbMap.putAll(dao.getBjxx(djbMap.get("pjjtid")));
			djbMap.putAll(dao.getfdyxx(djbMap.get("pjjtid")));
		}
		djbMap.put("currYe", DateUtils.getYear());
		djbMap.put("currMo", DateUtils.getMonth());
		djbMap.put("currDa", DateUtils.getDayOfMonth());
		djbMap.put("sysDate", DateUtils.getLyr()); //xxxx��xx��xx��
		
		return djbMap;
	}
	
	/** 
	 * @����:�Ƿ�Ϊ�༶����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-20 ����03:09:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isBjjx(String jxid){
		return dao.isBjjx(jxid);
	}

	public HashMap<String,String> gethbgydxDjb(String jgid) {
		return dao.gethbgydxDjb(jgid);
	}
}

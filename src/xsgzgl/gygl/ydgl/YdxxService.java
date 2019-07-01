/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-9-5 ����10:26:00 
 */  
package xsgzgl.gygl.ydgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �õ����ģ��
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-9-5 ����10:26:00 
 */

public class YdxxService extends SuperServiceImpl<YdxxForm, YdxxDao>{
YdxxDao ydxxDao = new YdxxDao();
	public boolean isExistQysj(YdxxForm myForm) {
		String num = dao.checkExistForSave(myForm);
		return Integer.valueOf(num) > 0;
	
}

	/** 
	 * @����:��ȡ¥����Ϣ
	 * @throws 
	 */
	public Object getLdList() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderBy", "order by lddm");
		
		return ydxxDao.getRsList("xg_gygl_new_ldxxb", map, new String[]{"lddm", "ldmc"});
	}
	public String getCwhForQs(String lddm, String qsh)throws Exception {
		// TODO �Զ����ɷ������
		return ydxxDao.getCwhForQs(lddm, qsh);
	}

	/**
	 * ��ȡָ��������Ϣ
	 * @param pk
	 * @return
	 */
	public Map<String, String> getQsForPk(String pk){
		return ydxxDao.getQsForPk(pk);
	}
	/**
	 * ��ȡ���ҵĴ�λ��Ϣ
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<HashMap<String, String>> getCwForQs(String pkValue) {
		String[] inputValue = new String[]{pkValue};
		String[] outputValue = new String[]{"xh","xm","xsbjmc","cwh"};
		return ydxxDao.getCwForQs(inputValue, outputValue);
	}
	public List<String[]> getCwForQs1(String pkValue){
		String[] inputValue = new String[]{pkValue};
		String[] outputValue = new String[]{"xh","xm","xsbjmc","cwh"};
		return ydxxDao.getCwForQs1(inputValue, outputValue);
	}
	public boolean saveDataYdxx(YdxxForm t)throws Exception {
		return dao.saveDataYdxx(t);
	}

	public boolean updateDataYdxx(YdxxForm t)throws Exception {
		return dao.updateDataYdxx(t);
	}

	/**
	 * ��ȡĳ¥�������е�����
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String, String>> getQshForLd(String lddm, String ch){
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("lddm", lddm);
		
		if(StringUtils.isNotNull(ch) && !"null".equalsIgnoreCase(ch)){
			queryMap.put("ch", ch);
		}
		queryMap.put("orderBy", " order by qsh ");
		return ydxxDao.getRsList("xg_gygl_new_qsxxb", queryMap, new String[]{"qsh"});
	}

	public List<HashMap<String, String>> getLdxxList(YdxxForm t) throws Exception {
		// TODO �Զ����ɷ������
		return ydxxDao.getLdxxList(t);
	}

	
}

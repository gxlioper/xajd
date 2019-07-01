package xgxt.pjpy.comm.pjpy.pjlc.jgcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.search.SearchService;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.utils.String.StringUtils;

public class PjpyJgcxService {

	PjpyJgcxDAO dao = new PjpyJgcxDAO();
	
	/**
	 * ���������ѯ
	 * @param model
	 * @param colList
	 * @param topTr
	 * @return
	 */
	public Map<String,Object> queryPjjg(PjpyJgcxForm model,String[] colList,String[] topTr,HttpServletRequest request){
		
		List<String[]> data = null;
		Map<String,Object> result = new HashMap<String,Object>();
		SearchService searchService = new SearchService();
		//�û���ѯ��Χ
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		
		if (StringUtils.isNotNull(model.getXmdm())){
			try {
				String[] lcmc = dao.getLcmcByXmdm(model);//��ѯĳ����Ŀ����Щ��˸�λ
				colList = StringUtils.joinStrArr(colList,lcmc);//�ϲ�������
				topTr = StringUtils.joinStrArr(topTr,lcmc);//�ϲ���ͷ
				
				//��̬��ȡ������˹�������
				String[] lcmcValue = new String[lcmc.length];
				for (int i = 0 ; i < lcmc.length ; i++){
					lcmcValue[i] = request.getParameter(lcmc[i]);
				}
				model.setLcmcValue(lcmcValue);
				data = dao.findPjjgByXmdm(model,colList,lcmc,searchTjByUser);//��ѯ
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			colList = StringUtils.joinStrArr(colList,new String[]{"shzt"});//�ϲ�������
			topTr = StringUtils.joinStrArr(topTr,new String[]{"�������"});//�ϲ���ͷ
			try {
				//��ѯ
				data = dao.findPjjg(model,colList,searchTjByUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		result.put("rs", data);
		result.put("topTr", topTr);
		return result;
	}
	
	/**
	 * ɾ��������¼
	 * @param pkValues
	 * @return
	 */
	public boolean delPjjl(String[] pkValues){
		if (null != pkValues && pkValues.length > 0){
			try {
				return dao.delPjjl(pkValues);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * ɾ���������롢��˼�¼
	 * @param pkValues
	 * @return
	 * @throws Exception
	 */
	public String checkDel(DelDetectModel model){
		return dao.checkDel(model);
	}
}

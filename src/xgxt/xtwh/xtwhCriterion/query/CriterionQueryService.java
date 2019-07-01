package xgxt.xtwh.xtwhCriterion.query;

import java.util.List;

import xgxt.utils.MakeQuery;
import xgxt.xtwh.xtwhCriterion.CriterionService;
import xgxt.xtwh.xtwhCriterion.QxwhForm;
import xgxt.xtwh.xtwhCriterion.jsgl.JsglDAO;

/**
 * @author luning 
 * @describe ͨ�ò�ѯ��service������Ȩ��ά������ģ��ȫ���̳и���
 */
public class CriterionQueryService extends CriterionService{
	private CriterionQueryDAO dao = new CriterionQueryDAO();
	
/**
 * @author luning
 * @describe ��ȡ��ɫ������Χ�б�
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public List getJsczfwList(){
		//����ҵ����ڽ�ɫ����DAO��ʵ��
		JsglDAO jsglDAO = new JsglDAO();
		
		return jsglDAO.getJsczfwList();
		
	}
	
/**
 * @author luning
 * @describe ��ȡ��ɫ�����б�
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public List getJslxList(){
		//����ҵ����ڽ�ɫ����DAO��ʵ��
		JsglDAO jsglDAO = new JsglDAO();
		
		return jsglDAO.getJslxList();
		
	}
	
	/**
	 * ��ѯ��ɫ�û�List
	 * @param model
	 * @return
	 */
	public List<String[]> getJsyhList(QxwhForm model){
		return dao.getJsyhList(model);
	}
	
	
	/**
	 * ��ѯ��ɫȨ��List
	 * @param model
	 * @return
	 */
	public List<String[]> getJsqxList(QxwhForm model){
		return dao.getJsqxList(model);
	}
	
	/**
	 * ��ѯ�û�Ȩ��List
	 * @param model
	 * @return
	 */
	public List<String[]> getYhqxList(QxwhForm model){
		return dao.getYhqxList(model);
	}
}

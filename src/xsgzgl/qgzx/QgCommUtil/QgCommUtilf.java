/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-22 ����11:23:04 
 */  
package xsgzgl.qgzx.QgCommUtil;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.Request;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2017-2-22 ����11:36:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QgCommUtilf{
	/**
	 * 
	 * @����: ��������path·�����͸߼���ѯ�����й�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-2-22 ����11:36:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * void �������� 
	 * @throws
	 */
	public static void setCzpath(HttpServletRequest request,SearchModel searchModel){
		String qgzq =  QgCommUtilf.getQgzq();
		request.setAttribute("qgzq", qgzq);
		if( StringUtils.isNotNull(qgzq) && qgzq.equals("xq")){
			String czpath = (String) request.getAttribute("path");
			request.setAttribute("czpath", czpath.replace(".do", "_xq.do"));
			if(searchModel != null){
				searchModel.setSearch_tj_xq(new String[]{Base.currXq});
			}
		}
	}
	
	/**
	 * 
	 * @����: ��ȡ�ڹ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-2-22 ����11:36:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public static String getQgzq(){
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		return cs.get("qgzq");
	}
	
	/**
	 * 
	 * @����: ��ȡcssz���е����ݲ�����request��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-2-22 ����03:13:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public static void setCssz(HttpServletRequest request){
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		request.setAttribute("cs", cs);
	}
}

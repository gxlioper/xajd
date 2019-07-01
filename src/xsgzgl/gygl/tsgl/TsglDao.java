package xsgzgl.gygl.tsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewService;

public class TsglDao extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	/**
	 * ��ȡ������Ϣ���ݼ�
	 * @author zhanghui
	 */
	public ArrayList<String[]> getTsglInfoList(TsglForm myForm, HttpServletRequest request) 
		throws Exception{		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		//Ȩ�޿���
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		
		String sql = "select rownum r,a.* from (select a.xh||a.czsj pk,a.*," +
				"a.ldmc||'_'||a.qsh||'_'||a.cwh tsqs,(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz from xg_gygl_new_tsxxb a order by tssj desc) a where 1=1 ";		
		String[] colList = new String[]{"pk","xh","xm","nj","xymc","xb","tsqs","rzsj","tssj","tsyy","subbz","bz"};		
		
		return  commonQuery(sql,searchTj+searchTjQx,inputV,colList,myForm);
	}
	
	/**
	 * �鿴������Ϣ
	 * @param pk
	 * @return
	 */
	public HashMap<String,String> viewTsxx(String pk){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,b.qsh dqqsh,b.cwh dqcwh,c.ldmc dqldmc ");
		sql.append("from xg_gygl_new_tsxxb a ");
		sql.append("left join xg_gygl_new_cwxxb b on a.xh=b.xh ");
		sql.append("left join xg_gygl_new_ldxxb c on b.lddm=c.lddm ");
		sql.append("where a.xh||a.czsj = ?");
		
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteTsglInfo(TsglForm model) throws Exception{
		
		String[]pkArr=model.getPrimarykey_checkVal();
		
		String[]delete=new String[pkArr.length];
		for(int i=0;i<pkArr.length;i++){
			delete[i]=" delete from xg_gygl_new_tsxxb where xh||czsj='"+pkArr[i]+"' ";
		}
		
		return dao.saveArrDate(delete);
	}
	
	/**
	 * ������Ϣ��ѯ �Զ��嵼��
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getTsglInfoExportList(TsglForm myForm, HttpServletRequest request) 
	throws Exception{		
	// �߼���ѯ����
	String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
	// �߼���ѯ����ֵ
	String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	//Ȩ�޿���
	String searchTjQx="";
	
	SearchService searchService=new SearchService();
	String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
	GyglNewService gyglNewService = new GyglNewService();
	String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա		
	
	if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
		searchTjQx = searchTjByGyfdy;
	}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
		searchTjQx = searchTjByUser;
	}
	
	/*String sql = "select rownum r,a.* from (select a.xh||a.czsj pk,a.*," +
			"a.ldmc||'_'||a.qsh||'_'||a.cwh tsqs from xg_gygl_new_tsxxb a order by tssj desc) a where 1=1 ";		*/
	String sql = "select rownum r , a.* from VIEW_NEW_DC_GYGL_TSXXGL a where 1=1 ";
	
	String[] colList = new String[]{"pk","xh","xm","nj","xymc","xb","tsqs","rzsj","tssj","tsyy"};		
	
	return  commonQueryforExportList(sql,searchTj+searchTjQx,inputV,colList,myForm);
}

}

package xgxt.qtsj.shgc.dao;

import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qtsj.shgc.form.ShgcTbxxForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Ϻ����̱��չ���ģ��DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-17</p>
 */
public class TbxxDAO {
	DAO dao = DAO.getInstance();
	
	/**
	 * Method getBxxzList��ȡ���������б�
	 * @return List
	 * */	
	public List getBxxzList(){		
		List bxxzList = null;
		String sql = "select distinct bxxzdm,bxxzmc from bxxzb order by bxxzdm";
		
		bxxzList = dao.getList(sql, new String[]{}, new String[]{"bxxzdm","bxxzmc"});
		return bxxzList;
	}
	
	/**
	 * Method getBxgsList��ȡ���չ�˾�б�
	 * @return List
	 * */
	public List getBxgsList(){
		List bxgsList = null;
		String sql = "select distinct bxgsdm,bxgsmc from bxgsdmb order by bxgsdm";
		
		bxgsList = dao.getList(sql, new String[]{}, new String[]{"bxgsdm","bxgsmc"});
		return bxgsList;
	}
	
	/**
	 * Method isExists �жϱ�����Ϣ�Ƿ����
	 * */
	public boolean isExists(String pkValue){
		boolean flag = false;		
		String sql = "select count(*)count from xsbxb where xh||nd=?";
		String sCount = dao.getOneRs(sql, new String[]{pkValue}, "count");
		flag = (Integer.parseInt(sCount)>0)? true : false;
		return flag;
	}
	
	/**
	 * ��ȡͶ�������б�
	 * @param model
	 * */
	public List getTbxzList(ShgcTbxxForm model){		
		List tbxzList = null;
		String xh = model.getXh();
		String nd = model.getNd();
		
		String sql = "select tbxzdm,bf,bxpzh from view_xsbxxx where xh=? and nd=?";
		tbxzList = dao.getList(sql, new String[]{xh,nd}, new String[]{"bxpzh","tbxzdm","bf"});
		
		return tbxzList;
	}
}

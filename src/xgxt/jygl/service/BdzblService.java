package xgxt.jygl.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.jygl.dao.BdzblDao;
import xgxt.jygl.form.BdzblForm;
import xgxt.studentInfo.service.ArchiveService;
import xgxt.utils.CommonUpdata;
import xgxt.utils.String.StringUtils;


public class BdzblService {
	BdzblDao dao = new BdzblDao();
	
	/**
	 * ��ȡ��ͷ
	 * @param String tableName
	 * @param String[] colList
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String tableName, String[] colList){
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ȡ�е�����
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName){
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * ���汨��֤����������Ϣ
	 * @param BdzblForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveBdzblsqb(BdzblForm model, HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "bdzblsqb";
		String[] columns = {"xh","nd","bdzlx","bdzkwdwmc","dajwdwbm","dajwdwmc","lxdz","lxfs","sjhm","lxyb"};
		String[] values = {model.getXh(),model.getNd(),model.getBdzlx(),model.getBdzkwdwmc(),model.getDajwdwbm(),model.getDajwdwmc(),model.getLxdz(),model.getLxfs(),model.getSjhm(),model.getLxyb()};
		
		if(dao.checkExists(tableName, "xh", model.getXh())){
			//�Ѿ����ڼ�¼�������޸Ĳ���
			columns = new String[]{"nd","bdzlx","bdzkwdwmc","dajwdwmc","dajwdwbm","lxdz","lxyb","lxfs","sjhm","fdysh","xysh"};
			values = new String[]{model.getNd(),model.getBdzlx(),model.getBdzkwdwmc(),model.getDajwdwmc(),model.getDajwdwbm(),model.getLxdz(),model.getLxyb(),model.getLxfs(),model.getSjhm(),"δ���","δ���"};
			result = StandardOperation.update(tableName, columns, values, "xh", model.getXh(), request);
		}else{
			//��������
			result = StandardOperation.insert(tableName, columns, values, request);
		}
		
		return result;
	}
	
	/**
	 * ����������ѯ����֤����������Ϣ
	 * @param BdzblForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBdzblsqbOne(BdzblForm model){
		return dao.selectBdzblsqbOne(model);
	}
	
	/**
	 * ɾ������֤������Ϣ
	 * @param BdzblForm model
	 * @return boolean
	 * */
	public boolean deleteBdzblsqb(BdzblForm model){
		return dao.deleteBdzblsqb(model);
	}
	
	/**
	 * ����֤������Ϣ��ѯ
	 * @param BdzblForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryBdzblsqb(BdzblForm model,String[] colList){
		return dao.selectBdzblsqb(model,colList);
	}
	
	/**
	 * ����֤������Ϣ��ѯ
	 * @param BdzblForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryBdzblsqForExport(BdzblForm model,String[] colList){
		return dao.selectBdzblsqForExport(model,colList);
	}
	
	/**
	 * �ж��Ƿ�����޸�
	 * @param BdzblForm model
	 * @return boolean
	 * */
	public boolean checkModify(BdzblForm model){
		boolean flag = false;
		if(dao.checkExists("bdzblsqb", "xh", model.getXh())){
			flag = dao.selectBdzblsqb(model,new String[]{"xh"}).size()==1 ? false : true;
		}
		return flag;
		
	}
	/**
	 * ����֤�����������
	 * @param BdzblForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean audiBdzbl(BdzblForm model,HttpServletRequest request) throws Exception{
		ArchiveService archive = new ArchiveService();
		String[] pkV = model.getPkV();
		boolean result = false;
		
		for(int i=0; i<pkV.length; i++){	
			if(StringUtils.isNotNull(pkV[i])){
				BdzblForm bdzblxx = new BdzblForm();
				bdzblxx.setXh(pkV[i]);
				bdzblxx.setFdysh(model.getFdysh());
				bdzblxx.setXysh(model.getXysh());
				//����bdzblsqb�е���Ϣ					
				result = CommonUpdata.commonUpdata(null,bdzblxx, "bdzblsqb", "xh", pkV[i], request);
				
				HashMap<String, String> map = queryBdzblsqbOne(bdzblxx);
				bdzblxx.setNd(map.get("nd"));
				bdzblxx.setDajwdwmc(map.get("dajwdwmc"));
				//ѧԺ���ͨ����ת����Ϣ���뵽������Ϣ����
				if(result && "ͨ��".equalsIgnoreCase(bdzblxx.getXysh())){
					String tableName = "stu_archives_apply";
					if(dao.checkExists(tableName, "xh", bdzblxx.getXh())){
						//ɾ��			
						//result = StandardOperation.delete(tableName, "xh", bdzblxx.getXh(), request);
						BdzblForm form = new BdzblForm();
						form.setPkV(new String[]{bdzblxx.getXh()});
						result = dao.runUpdate("delete from " + tableName + " where xh=?", new String[]{bdzblxx.getXh()});
					}
					//����
					//result = StandardOperation.insert(tableName, new String[]{"xh","nd","zdlb","zddwmc","zcbh","xxsh","xysh"}, new String[]{bdzblxx.getXh(),bdzblxx.getNd(),"��ҵ",bdzblxx.getDajwdwmc(),archive.getZcbh(),"ͨ��","ͨ��"}, request);
					result = dao.insert("insert into " + tableName + " (xh,nd,zdlb,zddwmc,zcbh,xxsh,xysh)values (?,?,?,?,?,?,?)", new String[]{bdzblxx.getXh(),bdzblxx.getNd(),"��ҵ",bdzblxx.getDajwdwmc(),archive.getZcbh(),"ͨ��","ͨ��"});
				}
			}			
		}		
		return result;
	}
	
	/**
	 * ����֤������Ϣ�޸�
	 * @param BdzblForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean modifyBdzblsqb(BdzblForm model,HttpServletRequest request) throws Exception{
		return  CommonUpdata.commonUpdata(null,model, "bdzblsqb", "xh", model.getXh(), request);
	}
}



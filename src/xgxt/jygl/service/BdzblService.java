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
	 * 获取表头
	 * @param String tableName
	 * @param String[] colList
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String tableName, String[] colList){
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * 获取列的中文
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName){
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * 保存报到证办理申请信息
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
			//已经存在记录，进行修改操作
			columns = new String[]{"nd","bdzlx","bdzkwdwmc","dajwdwmc","dajwdwbm","lxdz","lxyb","lxfs","sjhm","fdysh","xysh"};
			values = new String[]{model.getNd(),model.getBdzlx(),model.getBdzkwdwmc(),model.getDajwdwmc(),model.getDajwdwbm(),model.getLxdz(),model.getLxyb(),model.getLxfs(),model.getSjhm(),"未审核","未审核"};
			result = StandardOperation.update(tableName, columns, values, "xh", model.getXh(), request);
		}else{
			//插入数据
			result = StandardOperation.insert(tableName, columns, values, request);
		}
		
		return result;
	}
	
	/**
	 * 根据主键查询报到证办理申请信息
	 * @param BdzblForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBdzblsqbOne(BdzblForm model){
		return dao.selectBdzblsqbOne(model);
	}
	
	/**
	 * 删除报到证申请信息
	 * @param BdzblForm model
	 * @return boolean
	 * */
	public boolean deleteBdzblsqb(BdzblForm model){
		return dao.deleteBdzblsqb(model);
	}
	
	/**
	 * 报到证申请信息查询
	 * @param BdzblForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryBdzblsqb(BdzblForm model,String[] colList){
		return dao.selectBdzblsqb(model,colList);
	}
	
	/**
	 * 报到证申请信息查询
	 * @param BdzblForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryBdzblsqForExport(BdzblForm model,String[] colList){
		return dao.selectBdzblsqForExport(model,colList);
	}
	
	/**
	 * 判断是否可以修改
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
	 * 报到证办理申请审核
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
				//更新bdzblsqb中的信息					
				result = CommonUpdata.commonUpdata(null,bdzblxx, "bdzblsqb", "xh", pkV[i], request);
				
				HashMap<String, String> map = queryBdzblsqbOne(bdzblxx);
				bdzblxx.setNd(map.get("nd"));
				bdzblxx.setDajwdwmc(map.get("dajwdwmc"));
				//学院审核通过则将转档信息插入到档案信息表中
				if(result && "通过".equalsIgnoreCase(bdzblxx.getXysh())){
					String tableName = "stu_archives_apply";
					if(dao.checkExists(tableName, "xh", bdzblxx.getXh())){
						//删除			
						//result = StandardOperation.delete(tableName, "xh", bdzblxx.getXh(), request);
						BdzblForm form = new BdzblForm();
						form.setPkV(new String[]{bdzblxx.getXh()});
						result = dao.runUpdate("delete from " + tableName + " where xh=?", new String[]{bdzblxx.getXh()});
					}
					//插入
					//result = StandardOperation.insert(tableName, new String[]{"xh","nd","zdlb","zddwmc","zcbh","xxsh","xysh"}, new String[]{bdzblxx.getXh(),bdzblxx.getNd(),"毕业",bdzblxx.getDajwdwmc(),archive.getZcbh(),"通过","通过"}, request);
					result = dao.insert("insert into " + tableName + " (xh,nd,zdlb,zddwmc,zcbh,xxsh,xysh)values (?,?,?,?,?,?,?)", new String[]{bdzblxx.getXh(),bdzblxx.getNd(),"毕业",bdzblxx.getDajwdwmc(),archive.getZcbh(),"通过","通过"});
				}
			}			
		}		
		return result;
	}
	
	/**
	 * 报到证办理信息修改
	 * @param BdzblForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean modifyBdzblsqb(BdzblForm model,HttpServletRequest request) throws Exception{
		return  CommonUpdata.commonUpdata(null,model, "bdzblsqb", "xh", model.getXh(), request);
	}
}



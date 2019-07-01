package xgxt.xljk.whlgdx.service;

import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
//import xgxt.base.DealString;
import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.whlgdx.dao.Xljk_xskhdxwhDao;
import xgxt.xljk.whlgdx.form.Xljk_XskhdxwhForm;

public class Xljk_XskhdxwhService {
	
	private DAO dao = DAO.getInstance();
	
	private static Xljk_XskhdxwhService service = new Xljk_XskhdxwhService();
	
	private Xljk_xskhdxwhDao mydao = Xljk_xskhdxwhDao.getInstance();
	
	public static Xljk_XskhdxwhService getInstance(){
		return service;
	}
	/**initial the columns */
	private static final String[] COLUMNS = 
		{"xn_id","xh","nj","xm","xb","xymc","zymc","bjmc","remark","sfzh","lxdh","xz","ssbh","sjhm","qsdh","nl","jg","zyxlwt","yyzdqk","xgqk","mqzt","xjyd","fqxm","mqxm","fmlxfs"};
	
	/**initial the condi*/
	private static final String[] CONDIARRAYCOl = {"xydm","zydm","bjdm","xh","xm"};
	
	/**只供在对话框中的学生选择使用*/
	private static final String[] CHOICE_CONDIARRAYCOl = {"nj","xydm","zydm","bjdm","xh","xm"};
	private static final String[] CHOICE_COLUMNS = {"xh","xm","xb","nj","xz","bjmc"};
	
	
	/**根据条件返回相应的学生的信息*/
	public List<HashMap<String,String>> getStusByCondi(Xljk_XskhdxwhForm myForm){
		String[] outputValue = {"xn_id","xh","nj","xm","xb","xymc","zymc","bjmc"};
		StringBuffer sql = new StringBuffer();
		sql.append("select * from VIEW_XLJK_WHLGDX_KHXX where 1=1 ");
		String[] condiArray = new String[]{myForm.getXydm(),myForm.getZydm(),myForm.getBjdm(),
				myForm.getXh(),myForm.getXm()};
		sql.append(makeQueryCondition(condiArray,CONDIARRAYCOl));
		return mydao.getStusByCondi(sql.toString(), outputValue);
	}
	
	/**返回单个学生的数据*/
	public HashMap<String,String> getStuInfo(Xljk_XskhdxwhForm myForm){
		String sql = "select * from VIEW_XLJK_WHLGDX_KHXX  where xn_id=?";
		return mydao.getStuInfo(sql, COLUMNS,myForm.getXn_id());
	}
	
	/**保存学生的数据*/
	public boolean saveStuInfo(Xljk_XskhdxwhForm myForm) throws Exception {
		New_Random_ID newid = new New_Random_ID();
		String xn_id = newid.new_xnid("xljk_khxx");   //create xn_id primary
		String[] values = new String[]{xn_id,myForm.getXh(),myForm.getRemark()};
		String sql = "insert into XLJK_XLKHXSXXB(xn_id,xh) values(?,?,?)";
		return mydao.saveStuInfo(sql, values);
	}
	
	/**删除学生的数据*/
	public boolean delStuInfo(Xljk_XskhdxwhForm myForm) throws Exception{
		String xn_id = myForm.getXn_id();
		String sql = "delete from XJLK_WHLGDX_KHXX where xn_id=?";
		return mydao.delStuInfo(sql, xn_id);
	}
	
	/**修改学生的数据*/
	public boolean modiStuInfo(Xljk_XskhdxwhForm myForm) throws Exception{
		//do modify data
		//String sql = "update XLJK_XLKHXSXXB set ";
		//return mydao.modiStuInfo(sql, input);
		return true;
	}
	
	/**检查学号是否存在*/
	public boolean isStuExists(String xh){
		String sql = "select xh from VIEW_XSJBXX where xh=?";
		String[] stuArray = dao.getOneRs(sql, new String[]{xh},new String[]{"xh"});
		return stuArray == null ? false : true;
	}
	
	/**
	 * @param myForm
	 * @return 只供在对话框中的学生选择使用
	 */
	public List<HashMap<String,String>> getChoiceStusByCondi(Xljk_XskhdxwhForm myForm){
		StringBuffer sql = new StringBuffer("select * from( select rownum r,a.* ");
		sql.append(" from VIEW_XSJBXX a where 1=1 ");
		String[] condiArray = new String[]{myForm.getNj(),myForm.getXydm(),myForm.getZydm(),
				myForm.getBjdm(),myForm.getXh(),myForm.getXm()};
		sql.append(makeQueryCondition(condiArray,CHOICE_CONDIARRAYCOl));
		sql.append(" ) ");
		sql.append(" where r<= ");
		sql.append(myForm.getPages().getStart() + myForm.getPages().getPageSize());
		sql.append(" and r> ");	
		sql.append(myForm.getPages().getStart());
		return mydao.getStusByCondi(sql.toString(), CHOICE_COLUMNS);	
	}
	
	/**得到年级列表*/
	public List<HashMap<String,String>> getNjList(){
		return dao.getNjList();
	}
	
	/**得到符合条件的所有学生的数据量*/
	public String getChoiceNumStusByCondi(Xljk_XskhdxwhForm myForm){
		myForm.getPages().setPageSize(13);   //set page size 
		StringBuffer sql = new StringBuffer("select count(*) count ");
		sql.append(" from VIEW_XSJBXX a where 1=1 ");
		String[] condiArray = new String[]{myForm.getNj(),myForm.getXydm(),myForm.getZydm(),
				myForm.getBjdm(),myForm.getXh(),myForm.getXm()};
		sql.append(makeQueryCondition(condiArray,CHOICE_CONDIARRAYCOl));
		String[] countArray = dao.getOneRs(sql.toString(), new String[]{},new String[]{"count"});
		return countArray == null ? "0" : countArray[0];
	}
	
	/**根据学号返回学生的信息*/
	public Xljk_XskhdxwhForm getChoicePerStuInfo(Xljk_XskhdxwhForm myForm) throws Exception{
		String sql = "select * from VIEW_XSJBXX where xh=?";
		String[] output = new String[]{"xm","xb","xymc","zymc","bjmc","nj"};
		String[] rsArray = dao.getOneRs(sql, new String[]{myForm.getXh()},output);
		setProperties(output,rsArray,myForm); //set property
		return myForm;
	}
	//================================not business method=======================
	public List<HashMap<String,String>> getColumnCN(){
		String[] outputValue = {"xn_id","xh","nj","xm","xb","xymc","zymc","bjmc"};
		String[] cnArray = dao.getColumnNameCN(outputValue, "VIEW_XLJK_WHLGDX_KHXX");
		return dao.arrayToList(outputValue, cnArray);
	}
	
	public List<HashMap<String,String>> getChoice_ColumnCN(){
		String[] cnArray = dao.getColumnNameCN(CHOICE_COLUMNS, "VIEW_XSJBXX");
		return dao.arrayToList(CHOICE_COLUMNS, cnArray);
	}
	//=================================private method===========================
	
	/*get string by condi*/
	private String makeQueryCondition(String[] condiArray,String[] condiArrayCol){
	StringBuffer condi = new StringBuffer();
	for(int i=0;i<condiArray.length;i++){
	if(!StringUtils.isNull(condiArray[i])){
		condi.append(" and ");
		condi.append(condiArrayCol[i] + "='");
		condi.append(condiArray[i]);
		condi.append("' ");
	}
	}
	return condi.toString();
	}
	
	/**for set property*/
	private static void setProperties(String [] colList,String[] rsArray,Object model) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	Class myClass = model.getClass();
	for(int i = 0 ;i<colList.length;i++){
		String methodName = "set"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
		myClass.getMethod(methodName,(new String()).getClass()).invoke(model, rsArray[i]); //参数限定为String类型
	}	
}
}

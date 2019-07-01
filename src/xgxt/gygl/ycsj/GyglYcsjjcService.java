package xgxt.gygl.ycsj;

import java.sql.SQLException;
import java.util.HashMap;

import xgxt.gygl.GyglCommForm;
import xgxt.utils.String.StringUtils;

public class GyglYcsjjcService {

	GyglYcsjjcDAO dao = new GyglYcsjjcDAO();
	
	/**
	 * 异常数据信息
	 * @param model
	 * @return
	 */
	public HashMap<String,Object> getYcsjByType(GyglCommForm model){
		
		HashMap<String,Object> ycsj = null;
		
		switch(YcsjlxEnum.valueOf(model.getYclx().toUpperCase())){
			case DRZTC://多人住同一张床的异常数据
				ycsj = dao.getZsxxByDrtcw(model);
				ycsj.put("prompt", "多个人被分配在同一个床位");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case RZRSCGCWS://宿舍住的人超标了
				ycsj = dao.getZsxxByZgcws(model);
				ycsj.put("prompt", "以下学生所住宿舍的总人数超过总床位数");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case BLQSBFP://保留寝室被分配
				ycsj = dao.getFpxxByFpblqs(model);
				ycsj.put("prompt", "以下保留寝室被分配了");
				ycsj.put("tableName", "xg_gygl_qsfpb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
			case BLQSBZR://保留寝室住人
				ycsj = dao.getZsxxByBlqszr(model);
				ycsj.put("prompt", "以下学生被分配在保留寝室了");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case XLCWBZR://行李床位住人
				ycsj = dao.getZsxxByXlcwfp(model);
				ycsj.put("prompt", "以下学生被分配在行李床位了");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case YXBFCSS://因性别不符分错宿舍
				ycsj = dao.getZsxxByXbyw(model);
				ycsj.put("prompt", "以下学生被分配在错误性别的寝室");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case BFHLDXB://宿舍不符合楼栋要求性别
				ycsj = dao.getSsxxByLdxbbf(model);
				ycsj.put("prompt", "以下寝室与楼栋所设性别限制不符");
				ycsj.put("tableName", "ssxxb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
			case SSCGZGC://超过所在楼栋最高层数的宿舍
				ycsj = dao.getSsxxByCgzgcs(model);
				ycsj.put("prompt", "以下宿舍信息超过所在楼栋最高楼层");
				ycsj.put("tableName", "ssxxb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
			case CWCGZGC://超过所在楼栋最高层数的床位
				ycsj = dao.getCwxxByCgzgcs(model);
				ycsj.put("prompt", "以下床位信息超过所在楼栋最高楼层");
				ycsj.put("tableName", "xg_gygl_cwxxb");
				ycsj.put("pkKey", "lddm||cs||qsh||cwh");
				break;
			case QTCWCGZGC://超过所在楼栋最高层数的其它床位
				ycsj = dao.getQtcwxxByCgzgcs(model);
				ycsj.put("prompt", "以下其它床位信息超过所在楼栋最高楼层");
				ycsj.put("tableName", "xg_gygl_qtcwxxb");
				ycsj.put("pkKey", "lddm||cs||qsh||cwh");
				break;
			case FCBM://因部门不可混住而分配错宿舍
				ycsj = dao.getSsxxBybmbkhz(model);
				ycsj.put("prompt", "以下学生被分配在不可混住且非本部门的寝室了");
				ycsj.put("tableName", "xszsxxb");
				ycsj.put("pkKey", "xh");
				break;
			case FPXXBFJBSZ://分配信息不符合基本设置中分配方式
				ycsj = dao.getFbxxByJbszbf(model);
				ycsj.put("prompt", "以下宿舍分配信息不符合基本设置中的分配方式");
				ycsj.put("tableName", "xg_gygl_qsfpb");
				ycsj.put("pkKey", "lddm||cs||qsh");
				break;
		}
		return ycsj;
	}
	
	
	
	/**
	 * 根据异常类型查询异常数据记录数
	 * @param ycsjlx
	 * @return
	 * @throws SQLException
	 */
	public int getCountByType(String ycsjlx) throws SQLException{
			
			int count = 0;
		
			switch(YcsjlxEnum.valueOf(ycsjlx.toUpperCase())){
				case DRZTC://多人住同一张床的异常数据
					count = dao.getCountByDrtcw();
					break;
				case RZRSCGCWS://宿舍住的人超标了
					count = dao.getCountByZgcws();
					break;
				case BLQSBFP://保留寝室被分配
					count = dao.getCountByFpblqs();
					break;
				case BLQSBZR://保留寝室住人
					count = dao.getCountByBlqszr();
					break;
				case XLCWBZR://行李床位住人
					count = dao.getCountByXlcwfp();
					break;
				case YXBFCSS://因性别不符分错宿舍
					count = dao.getCountByXbyw();
					break;
				case BFHLDXB://宿舍不符合楼栋要求性别
					count = dao.getCountByLdxbbf();
					break;
				case SSCGZGC://超过所在楼栋最高层数的宿舍
					count = dao.getCountByCgzgcs();
					break;
				case CWCGZGC://超过所在楼栋最高层数的床位
					count = dao.getCountByCgzgcscw();
					break;
				case QTCWCGZGC://超过所在楼栋最高层数的其它床位
					count = dao.getCountByCgzgcsqtcw();
					break;
				case FCBM://因部门不可混住而分配错宿舍
					count = dao.getCountBybmbkhz();
					break;
				case FPXXBFJBSZ://分配信息不符合基本设置中分配方式
					count = dao.getCountByJbszbf();
					break;
			}
			return count;
		}
	
	
	/**
	 * 删除异常数据
	 * @param tableName
	 * @param pkKey
	 * @param pkValues
	 * @return
	 */
	public boolean delYcsj(String tableName, String pkKey, String[] pkValues) {

		if (StringUtils.isNotNull(tableName) && StringUtils.isNotNull(pkKey)
				&& null != pkValues && pkValues.length > 0) {
			try {
				return dao.delYcsj(tableName, pkKey, pkValues);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

	}
	
}

package xgxt.gygl.ycsj;

/**
 * 公寓管理-异常数据类型枚举
 * @author 鲁大
 */
public enum YcsjlxEnum {

	DRZTC,//多个人住同一张床
	BLQSBZR,//保留寝室被住人
	XLCWBZR,//行李床位被住人
	RZRSCGCWS,//入住人数超过床位数
	BLQSBFP,//保留寝室被分配
	YXBFCSS,//因性别不符分错宿舍
	BFHLDXB,//不符合楼栋性别的宿舍
	SSCGZGC,//宿舍分配超过最高楼层
	CWCGZGC,//床位分配超过最高楼层
	QTCWCGZGC,//其它床位分配超过最高楼层
	FCBM,//分错部门（不可混住，非本部门宿舍）
	FPXXBFJBSZ//分配信息不符合基本设置
}

/**
 * 初始化
 */
jQuery(function(){
	searchRs();
});

/**
 * 列表展示
 * @return
 */
function getGridSettiong(){
	var gridSetting = {
		caption:"奖学金名额分配一览表",
		pager:"pager",
		url:"xpjpy_jxjfp.do?method=getJxjfpListData",
		params:getSuperSearch(),
		multiselect:false
	};

	var colList=[
	   {label:'部门名称',name:'bmmc', index: 'bmmc',width:'15%'}
	];				
		
	var pjxm = jQuery("font[name=pjxm]");
	jQuery.each(pjxm,function(i,n){
		var pjxmJson = {
				label:"&nbsp;&nbsp;"+jQuery(n).attr("xmmc")+"<br/>(总人数/调整人数)",
				name:"jx"+i,
				index:"jx"+i
		};
		colList.push(pjxmJson);
	});

	var jjeList = {label:'奖金额',name:'jje', index: 'jje',width:'15%',key:true};
	var bmtzjeList = {label:'部门调整金额',name:'bmtzje', index: 'bmtzje',width:'15%',key:true};
	var zrsList = {label:'部门学生总数',name:'zrs', index: 'zrs',width:'15%',key:true};
	colList.push(jjeList);
	colList.push(bmtzjeList);
	colList.push(zrsList);
	
	gridSetting["colList"] = colList;
	
	return gridSetting;				
}

/**
 * 查询
 * @return
 */
function searchRs(){
	var xn_num = document.getElementsByName("a_name_xn").length;
	var notFirst = jQuery("#notFirst").val();
	if("yes"==notFirst&&0==xn_num){
		showAlertDivLayer("请先选择一个学年！");
		return false;
	}
	jQuery.ajaxSetup({async:false});
	initPjxm();
	var gridSetting = getGridSettiong();
	jQuery("#dataTable").initGrid(gridSetting);
	jQuery.ajaxSetup({async:true});
}

/*初始化需统计评奖项目*/
function initPjxm(){
	var url="xpjpy_jxjfp.do?method=initPjxm";
	var xn = jQuery("[name=tj_xn][class=selectedValue]").attr("id");
	if(undefined!=xn){
		xn=xn.split("_")[2];
	}
	jQuery.post(url,{"xn":xn},function(data){
		dataObj=data;
		createPjxmDiv();
		},'json');
	
	}

/**
 * 创建动态的奖学金名称(列表)
 * @return
 */
function createPjxmDiv(){
	var pjDiv=jQuery("#pjxmDiv");
	jQuery("font",pjDiv).remove();
	var pjxmHtml = "";
	for ( var i = 0; i < dataObj.length; i++) {
		var o = dataObj[i];
		pjxmHtml+="<font style='display:none;' xmdm="+o.xmdm+" xmmc="+o.xmmc+" name='pjxm'></font>";
	}
	pjDiv.html(pjxmHtml);
	jQuery("#notFirst").val("yes")
}

/**
 * 奖学金分配一览表数据导出
 * @return
 */
function jxjfpExport(){
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	if(xn_num != "1"){
		showAlertDivLayer("学年条件不可为空，且只能选择一个！");
		return false;
	}
	
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_jxjfp.do?method=jxjfpExport"
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 发放汇总导出(后台需要执行一个存储过程)
 * @return
 */
function ffhzExport(){
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	if(xn_num != "1"){
		showAlertDivLayer("学年条件不可为空，且只能选择一个！");
		return false;
	}
	/*设置高级查询条件*/
	setSearchTj();//
	var url = "xpjpy_jxjfp.do?method=ffhzExport"
	/*设置高级查询参数*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//发放汇总导出
function gjjxjhzExport(){
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	if(xn_num != "1"){
		showAlertDivLayer("学年条件不可为空，且只能选择一个！");
		return false;
	}
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_jxjfp.do?method=gjjxjhzExport"
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
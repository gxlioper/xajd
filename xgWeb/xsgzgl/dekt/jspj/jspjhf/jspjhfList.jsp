<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);
			});
			var gridSetting = {
			caption:"教师评价未评价列表",
			pager:"pager",
			url:"dekt_jspjglyyxx.do?method=jspjyyxxList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'10%'},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'性别',name:'xb', index: 'xb',width:'4%'},
			   {label:'年级',name:'nj', index: 'nj',width:'6%'},
			   {label:'学院代码',name:'xydm', index: 'xydm',hidden:true,width:'15%'},
			   {label:'学院',name:'xymc', index: 'xymc',width:'12%'},
                {label:'书院代码',name:'sydm', index: 'sydm',hidden:true,width:'15%'},
                {label:'书院',name:'symc', index: 'symc',width:'8%'},
			   {label:'专业代码',name:'zydm', index: 'zydm',hidden:true,width:'15%'},
			   {label:'专业',name:'zymc', index: 'zymc',width:'12%'},
			   {label:'班级代码',name:'bjdm', index: 'bjdm',hidden:true,width:'15%'},
			   {label:'行政班级',name:'bjmc', index: 'bjmc',width:'4%'},
                {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'4%'},
			   {label:'身份证号',name:'sfzh', index: 'sfzh',width:'12%'},
			   {label:'政治面貌代码',name:'zzmm', index: 'zzmm',hidden:true,width:'10%'},
			   {label:'政治面貌',name:'zzmmmc', index: 'zzmmmc',width:'8%'},
			   {label:'民族代码',name:'mz', index: 'mz',hidden:true,width:'15%'},
			   {label:'民族',name:'mzmc', index: 'mzmc',width:'4%'},
			   {label:'回复',name:'', index: '',width:'4%',formatter:xhLink},	
			   {label:'是否需预约字段',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'学生是否预约老师',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'教师是否同意',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'}
			],
			params:{pjlx:"dpj"},//默认待评价
		 	radioselect:false
}
var gridSetting2 = {
		caption:"教师评价已评价列表",
		pager:"pager",
		url:"dekt_jspjglyyxx.do?method=jspjyyxxList&type=query",
		colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'10%'},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'性别',name:'xb', index: 'xb',width:'4%'},
			   {label:'年级',name:'nj', index: 'nj',width:'6%'},
			   {label:'学院代码',name:'xydm', index: 'xydm',hidden:true,width:'15%'},
			   {label:'学院',name:'xymc', index: 'xymc',width:'12%'},
            {label:'书院代码',name:'sydm', index: 'sydm',hidden:true,width:'15%'},
            {label:'书院',name:'symc', index: 'symc',width:'8%'},
			   {label:'专业代码',name:'zydm', index: 'zydm',hidden:true,width:'15%'},
			   {label:'专业',name:'zymc', index: 'zymc',width:'12%'},
			   {label:'班级代码',name:'bjdm', index: 'bjdm',hidden:true,width:'15%'},
			   {label:'行政班级',name:'bjmc', index: 'bjmc',width:'4%'},
            {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'4%'},
			   {label:'身份证号',name:'sfzh', index: 'sfzh',width:'12%'},
			   {label:'政治面貌代码',name:'zzmm', index: 'zzmm',hidden:true,width:'10%'},
			   {label:'政治面貌',name:'zzmmmc', index: 'zzmmmc',width:'8%'},
			   {label:'民族代码',name:'mz', index: 'mz',hidden:true,width:'15%'},
			   {label:'民族',name:'mzmc', index: 'mzmc',width:'4%'},
			   {label:'回复',name:'', index: '',width:'4%',formatter:xhLink},	
			   {label:'是否需预约字段',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'学生是否预约老师',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'教师是否同意',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'}
			],
		params:{pjlx:"ypj"},
	 	radioselect:true
}
	
function searchRs(){
	var map = getSuperSearch();
	var pjlx = jQuery("#pjlx").val();
	if (pjlx != ""){
		map["pjlx"] = pjlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
			
//点击学号查看
function view(sqid, xh) {
	showDialog("学生预约回复",700,350, "dekt_jspjglyyxx.do?method=view&sqid=" + sqid+ "&xh=" + xh);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\",\"" + rowObject["xh"] + "\");'>" + "回复"
			+ "</a>";
}

//切换lab
function selectTab(obj,pjlx){
	jQuery("#pjlx").val(pjlx);
	var xxdm = jQuery("#xxdm").val();
	if (pjlx == "dpj"){
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}


		</script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" value="dpj" id="pjlx"/>
		<input type="hidden" value="${zzshen}" id="zzshen"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_jspjglsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dpj');"><span>待回复</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ypj');"><span>已回复</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>教师评价列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

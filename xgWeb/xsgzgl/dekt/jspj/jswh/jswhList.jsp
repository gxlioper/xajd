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
					jQuery("#li_qx").css("display","none");
					jQuery("#li_sfkyy").css("display","none");
			});
			var gridSetting = {
			caption:"未选教师列表",
			pager:"pager",
			url:"dekt_jswhgl.do?method=jswhList&type=query",
			colList:[
			  {label:'key',name:'zgh', index: 'zgh',key:true,hidden: true },
			  {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			  {label:'性别',name:'xb', index: 'xb',width:'3%'},
			  {label:'所属部门',name:'bmmc', index: 'bmmc',width:'10%'},
			  {label:'教师类别',name:'jslbmc', index: 'jslbmc',width:'10%'},
			  {label:'联系电话',name:'lxdh', index: 'lxdh',width:'8%'}
			],
			params:{jslx:"wwh"},//默认未添加
		 	radioselect:false
}
var gridSetting2 = {
		caption:"已选教师列表",
		pager:"pager",
		url:"dekt_jswhgl.do?method=jswhList&type=query",
		colList:[
		   	  {label:'key',name:'zgh', index: 'zgh',key:true,hidden: true },
			  {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			  {label:'性别',name:'xb', index: 'xb',width:'3%'},
			  {label:'所属部门',name:'bmmc', index: 'bmmc',width:'10%'},
			  {label:'教师类别',name:'jslbmc', index: 'jslbmc',width:'10%'},
			  {label:'联系电话',name:'lxdh', index: 'lxdh',width:'8%'},
			  {label:'是否需预约',name:'zzshen', index: 'zzshen',width:'3%'}
		],
		params:{jslx:"ywh"},//已添加
	 	radioselect:false
}
	
function searchRs(){
	var map = getSuperSearch();
	var jslx = jQuery("#jslx").val();
	if (jslx != ""){
		map["jslx"] = jslx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	

//切换lab
function selectTab(obj,jslx){
	jQuery("#jslx").val(jslx);
	if (jslx == "wwh"){
		jQuery("#li_fp").css("display","");
		jQuery("#li_qx").css("display","none");
		jQuery("#li_sfkyy").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_fp").css("display","none");
		jQuery("#li_qx").css("display","");
		jQuery("#li_sfkyy").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function dekt_10698(){
	var array = new Array();
	var zgh = jQuery("#dataTable").getSeletIds();
	jQuery("[name=grid_key]:checked").each(function(i){
		array[i] = escape(jQuery(this).val());
	});
	if(zgh.length == 0){
		showAlertDivLayer("请勾选<font color='blue'>至少一条记录</font>");
		return false;
	}
	var zgh = zgh.join('!!array!!');
	showDialog('100名教师设置',450,150,'dekt_jswhgl.do?method=dekt_10698&zgh='+zgh);
}

//分配
function fp(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要分配的教师！");
		return false;
	}
	showConfirmDivLayer("您确定要分配以上教师吗？", {
		"okFun" : function() {
			jQuery.post("dekt_jswhgl.do?method=saveFp", {
				zghs : ids.toString()
			},
			function(data){
				if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    		 	if (parent.window) {
							jQuery("#dataTable").initGrid(gridSetting);
						}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}
//取消分配
function cancelfp(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要取消分配的教师！");
		return false;
	}
	showConfirmDivLayer("您确定要取消分配以上教师吗？", {
		"okFun" : function() {
			jQuery.post("dekt_jswhgl.do?method=cancelFp", {
				zghs : ids.toString(),
			},
			function(data){
				if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window) {
							jQuery("#dataTable").initGrid(gridSetting2);
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}
//西安交通大学
function sfkyy(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要设置预约的教师！");
		return false;
	}
	showConfirmDivLayer("您确定要设置预约以上教师吗？", {
		"okFun" : function() {
			jQuery.post("dekt_jswhgl.do?method=saveSfkyy", {
				zghs : ids.toString(),
			},
			function(data){
				if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    		 	if (parent.window) {
							jQuery("#dataTable").initGrid(gridSetting2);
						}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}

var DCCLBH = "xg_dekt_jswhgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	var jslx = jQuery("#jslx").val();
	setSearchTj();//设置高级查询条件
	var url = "dekt_jswhgl.do?method=exportData&dcclbh=" + DCCLBH + "&jslx=" + jslx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
		</script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" value="wwh" id="jslx"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_jspjglsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_fp">
							<a href="javascript:void(0);" onclick="fp();return false;" class="btn_shuc">分配</a>
						</li>
						<li id="li_qx">
							<a href="javascript:void(0);" onclick="cancelfp();return false;" class="btn_sr">取消分配</a>
						</li>
						<logic:equal value="10698" name="xxdm">
							<li id="li_sfkyy">
								<a href="javascript:void(0);" onclick="dekt_10698();return false;" class="btn_shuc">是否需预约</a>
							</li>
						</logic:equal>	
						</logic:equal>		
								
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>			
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wwh');"><span>未分配</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ywh');"><span>已分配</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>教师分配列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

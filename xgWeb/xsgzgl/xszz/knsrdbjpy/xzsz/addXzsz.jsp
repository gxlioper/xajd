<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"学生列表",
					pager:"pager",
					url:"xszz_knsrdbjpy_xzszgl.do?method=addXzsz&type=query&bjdm=${rs.bjdm}",
					colList:[      
				         {label:'key',name:'xh', index: 'xh',hidden:true,key:true},
						   {label:'学号',name:'xh', index: 'xh',width:'10%'},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'年级',name:'nj', index: 'nj',width:'7%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},
						   {label:'专业',name:'zymc', index: 'zydm',width:'22%'},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'22%'},
						   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true}
						],
						sortname: "xh",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function addXzszBc() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0) {
				showAlertDivLayer("请选择学生！");
			} else {
				jQuery.post("xszz_knsrdbjpy_xzszgl.do?method=addXzszBc", { values : ids.toString(), bjdm: "${rs.bjdm}" },
						function(data) {
							if(data["message"]=="保存成功！"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
										if (parent.window){
											refershParent();
										}
									}});
					    	 }else{
					    		 showAlert(data["message"]);
					    	 }
						}, 'json');
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_knsrdbjpy_xzszgl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<font color="red">增加</font>学生后，班级评议小组的提交状态改为<font color="red">未提交</font>，请重新进行<font color="red">提交</font>操作。
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_ccg" onclick="addXzszBc();return false;" >保存</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

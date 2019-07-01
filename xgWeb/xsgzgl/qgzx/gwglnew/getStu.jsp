<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script>
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		//初始化
		jQuery(document).ready(function(){ 
			var url = "qgzx_gwglnew.do?method=getStu&type=query&pkValue="+jQuery("#pkValue").val();
			//勤工助学个性化
			var xn = jQuery("#xn").val();
			url = url+"&xn="+xn+"&sfxyzgsc="+jQuery("#sfxyzgsc").val();
			
			var gridSetting = {
					caption:"学生列表",
					pager:"pager",
					url:url,
					colList:[
		                {label:'key',name:'xh', index: 'xh',hidden:true,key:true},
						{label:'行号',name:'r', index: 'r',width:'4%'},
						{label:'学号',name:'xh', index: 'xh',width:'12%'},
						{label:'姓名',name:'xm', index: 'xm',width:'9%'},
						{label:'性别',name:'xb', index: 'xb',width:'7%'},
						{label:'年级',name:'nj', index: 'nj',width:'7%'},
						{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
						{label:'专业',name:'zymc', index: 'zymc',width:'16%'},
						{label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
						{label:'是否困难生',name:'sfkns', index: 'sfkns',width:'10%'}
					],
					sortname: "r",
				 	sortorder: "asc"
				}
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		//添加保存学号
		function zjBcStu(){
			var api = frameElement.api,W = api.get('parentDialog');
			var str = "";
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length>=1){
				for(var i=0;i<rows.length;i++){
					var pkValue = rows[i]["xh"];
					str += pkValue+"!!@@!!";
				}
				W.document.getElementById("xhs").value=str;
				api.close();
				W.document.getElementById("btn_getXsxx").onclick();
			}else{
				showAlert("请选择您要添加的学生！");
			}
		}
		</script>
	</head>
	<body>

		<html:form action="/qgzx_gwglnew" method="post">
			
			<!-- 个性化隐藏域 -->
			<input type="hidden" name="xn" id="xn" value="${xn}"/>
			<!-- 个性化隐藏域 -->
			<input type="hidden" name="sfxyzgsc" id="sfxyzgsc" value="${sfxyzgsc}"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="zjBcStu();return false;" class="btn_zj">添加</a>
						</li>
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>学生列表&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
		</html:form>
	</body>
</html>

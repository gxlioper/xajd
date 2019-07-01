<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"辅导员信息列表",
				pager:"pager",
				url:"bjlh_fdycpwj.do?method=cpwjglWjlr&type=query",
				colList:[
				   {label:'职工号',name:'zgh', index: 'zgh',width:'20%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'20%'},
				   {label:'类别',name:'lx', index: 'lx',width:'20%'},
				   {label:'是否已测评',name:'sfycp', index: 'sfycp',width:'20%'}
				],
				sortname: "zgh",
			 	sortorder: "asc"
			}


			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function fdykh(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要测评的记录！");
				} else {
					var url = 'bjlh_fdycpwj.do?method=cpwjglWjlrSingle&fdyid='+rows[0]["zgh"];
					var title = "教师测评";
					//showDialog(title,800,520,url);
					url+='&flag=fh';
					refreshForm(url);
				}
				
			}


			
		</script>
	</head>

	<body>
	<html:form action="/bjlh_fdycpwj">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="fdykh();" class="btn_xg">测评</a></li>
				</ul>
			</div>
			
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 辅导员测评信息列表 </span>
			</h3>

			<table id="dataTable"></table>

		</div>
		</html:form>
	</body>
</html>

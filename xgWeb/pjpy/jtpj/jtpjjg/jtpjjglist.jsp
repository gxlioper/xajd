<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjjg/js/jtpjjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var flag = false;
					 for(var i = 0; i < rows.length; i++){
						 if(rows[i]["jtpjdw"] != 'bj'){
							 flag = true;
						 }
					 }
					 if(flag){
						 showAlertDivLayer("请选择以班级为单位的记录！");
						 return false;
					 }
					var ids = jQuery("#dataTable").getSeletIds();
					var url="jtpjjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					 if(rows[0]["jtpjdw"] != 'bj'){
						 showAlertDivLayer("请选择以班级为单位的记录！");
						 return false;
					 }
					var url="jtpjjg.do?method=getDjbWord&jgid="+rows[0]["jgid"];
					window.open(url);
			     }
			}
			
			//通用登记表下载功能
			function getDjb(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="jtpjjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="jtpjjg.do?method=getDjbWord&jgid="+rows[0]["jgid"];
					window.open(url);
			     }
			}
			jQuery(function(){
			     var gridSetting = {
							pager:"pager",
							url:"jtpjjg.do?method=list&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'结果id',name:'jgid', index: 'jgid',key:true,hidden:true},
							   {label:'集体名称 ',name:'pjjtmc', index: 'pjjtmc',formatter:pjjtmc},
							   {label:'人数',name:'rs', index: 'rs'},
							   {label:'奖项名称',name:'jxmc', index: 'jxmc'},
							   {label:'申请学年',name:'sqxn', index: 'sqxn',hidden:true},
							   {label:'申请学期',name:'sqxqmc', index: 'sqxqmc',hidden:true},
							   {label:'申请周期',name:'sqzq', index: 'sqxn,sqxq',formatter:sqzq},
							   <logic:equal name="xxdm" value="10466">
								{label:'申请时间',name:'sqsj',index:'sqsj',width:'15%'},
							   </logic:equal>
							   {label:'评定学年',name:'pdxn', index: 'pdxn',hidden:true},
							   {label:'评定学期',name:'pdxq', index: 'pdxq',hidden:true},
							   {label:'评定学期',name:'pdxqmc', index: 'pdxqmc',hidden:true},
							   {label:'集体评奖单位',name:'jtpjdw', index: 'jtpjdw',hidden:true},
							   {label:'评定周期',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
							   {label:'审核流数据',name:'sjly', index: 'sjly',formatter:sjly}
							],
							sortname: "pdxn,pdxq",
						 	sortorder: "desc"
						}
					jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jtpjjg.do?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">	
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" onclick="drxx();return false;" class="btn_csh">导入</a>
							</li>
							</logic:equal>
							<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
							<!-- 上海电机学院 begin -->
							<logic:equal name="xxdm" value="11458">	
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>	
							</logic:equal>
							<!-- 上海电机学院 end -->
							
							<!-- 潍坊学院 begin -->
							<logic:equal name="xxdm" value="11067">	
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">先进班集体登记表打印</a></li>	
							</logic:equal>
							<!-- 潍坊学院 end -->
							<logic:notEqual name="xxdm" value="11458">	
							<logic:notEqual name="xxdm" value="11067">	
								<li><a href="javascript:void(0);" onclick="getDjb();return false;" class="btn_down">下载登记表</a></li>	
							</logic:notEqual>
							</logic:notEqual>
						</ul>
					</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">集体评奖结果列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

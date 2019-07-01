<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
			var gridSetting = {
				caption:"教职员工信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"szdw_showJzyg.do?type=query",
				colList:[
				   {label:'职工号',name:'zgh', index: 'xh',width:'20%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'20%'},
				   {label:'性别',name:'xb', index: 'xb',width:'8%'},
				   {label:'部门',name:'bmmc', index: 'bmmc',width:'32%'},
				   {label:'操作',name:'cz', index: 'cz',width:'20%',formatter:dcmcLink}
				],
				dblclick:dbShow,
				sortname: "zgh",
			 	sortorder: "asc"
			};
			
			function dcmcLink(cellValue, rowObject) {
				return "<button type=\"button\" onclick=\"onShow('"+rowObject["zgh"]+"','"+rowObject["xm"]+"','"+rowObject["xb"]+"','"+rowObject["bmmc"]+"');\" class=\"btn_01\" >选择</button>";
			}
			
			function onShow(zgh,xm,xb,bmmc){
				var api = frameElement.api,W = api.get('parentDialog');
				W.jQuery("#zgh").val(zgh);
				W.document.getElementById("xmsp").innerHTML=xm;
				W.document.getElementById("xb").innerHTML=xb;
				W.document.getElementById("bmmc").innerHTML=bmmc;
				iFClose();
			}
			
			function dbShow(rowObject){
				var api = frameElement.api,W = api.get('parentDialog');
				W.jQuery("#zgh").val(rowObject["zgh"]);
				W.document.getElementById("xmsp").innerHTML=rowObject["xm"];
				W.document.getElementById("xb").innerHTML=rowObject["xb"];
				W.document.getElementById("bmmc").innerHTML=rowObject["bmmc"];
				iFClose();
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
	
		<html:form action="/szdw_new">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>教职员工信息列表
					<font color="red">请双击一条记录选择教职员工</font>
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzkff/js/zzkff.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"资助款发放列表",
				pager:"pager",
				url:"xszz_zzkff.do?method=zzkffCx&type=query",
				colList:[
				   {label:'资助款发放id',name:'id', index: 'id',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'14%'},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'学期',name:'xqmc', index: 'xq',width:'10%'},
				   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'12%'},
				   {label:'金额',name:'je', index: 'je',width:'6%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			function xhLink(cellValue,rowObject){
				var id = rowObject["id"];
				return "<a href='javascript:void(0);' onclick=\"zzkffShow('"+id+"')\" class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
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
		<html:form action="/xszz_zzkff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="zzkffAdd()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="zzkffUpdate();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="zzkffDelete()" class="btn_sc">删除</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a></li>
						</logic:equal>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 资助款发放列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

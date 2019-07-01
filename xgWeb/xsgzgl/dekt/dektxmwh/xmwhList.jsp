<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/dektxmwh/js/xmwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "项目维护",
				pager : "pager",
				url : "dekt_xmwh.do?method=getXmwhList",
				colList : [
					{label:'xmid',name:'xmid',index :'xmid',key:true,hidden:true},
					/* {label:'所属学院',name:'xymc',index:'xymc',width:'10%'},
					{label:'项目大类',name:'xmdl',index:'xmdl',width:'10%'}, */
					{label:'类型',name:'lx',index:'lx',width:'14%'},
					{label:'认定项目',name:'rdxm',index:'rdxm',width:'12%'},
					{label:'认定内容标准',name:'rdnrbz',index:'rdnrbz',width:'23%'},
					{label:'等级',name:'dj',index:'dj',width:'9%'},
					{label:'学分',name:'xf',index:'xf',width:'6%'},
					{label:'审批流程',name:'lcxx',index:'lcxx',width:'34%'},
					{label:'splc',name:'splc',index:'splc',hidden:true}
				],
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_xmwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="splcEdit();" class="btn_xg">修改审批流程</a></li>
						<%-- <logic:equal name="writeAble" value="yes">
						 <li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >导入</a></li>
						</logic:equal> 
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	--%>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end--> 
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>项目维护</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

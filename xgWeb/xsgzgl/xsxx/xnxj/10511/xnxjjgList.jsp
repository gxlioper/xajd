<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxjjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/xszz_knsjg" >

			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knsjgb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">填写小结</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改小结</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除小结</a></li>						
						</logic:equal>
						<li><a href="#" onclick="ck();return false;" id="btn_ck" class="btn_ck">查看</a></li>
						<logic:equal name="writeAble" value="yes">	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printXnxjSq('xsxx_xnxj_xjjggl.do?method=doPrint');return false;" class="btn_down">下载小结表</a></li>	
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
				<span>学年小结结果列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

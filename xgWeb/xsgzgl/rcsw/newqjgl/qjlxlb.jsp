<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/js/qjlx.js"></script>
				<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"��������б�",
									pager:"pager",
									url:"qjlx.do?method=list&type=query",
									colList:[
									   {label:'������ʹ���',name:'qjlxid', index: 'qjlxid',key:true},
									   {label:'�����������',name:'qjlxmc', index: 'qjlxmc'},
									   {label:'����',name:'openzt', index: 'qjlxmc',formatter:openLink}
									],
									sortname: "qjlxid",
								 	sortorder: "asc"
								}
					jQuery("#dataTable").initGrid(gridSetting);
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/qjlx?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
				</ul>
			</div>
			</logic:equal>
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��������б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

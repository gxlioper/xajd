<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		        <script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript" src="js/xsgzgl/dtjs/dxbmgl/dxpxgl.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"��ѵ��Ϣ����б�",
						pager:"pager",
						url:"dtjs_dxbmgl_dxpxglCx.do?type=query",
						colList:[
						   {label:'id',name:'id', index: 'id',key:true,hidden:true},
						   {label:'sjyl',name:'sjyl', index: 'sjyl',hidden:true},
						   {label:'��ѵ�ڴ�',name:'pxqc', index: 'pxqc'},
						   {label:'��ѵʱ��',name:'pxsj', index: 'xm'},
						   {label:'������ʼʱ��',name:'bmkssj', index: 'bmkssj'},
						   {label:'��������ʱ��',name:'bmjssj', index: 'bmjssj'},
						   {label:'������',name:'fbrxm', index: 'fbrxm'},
						   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
						   {label:'�ɼ���������',name:'', index: '',formatter:setCjbl}
						],
						sortname: "pxsj",
					 	sortorder: "asc"
					}
					jQuery(function(){
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
	<html:form action="dxbmgl_dxpxgl.do">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- ��ť -->
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
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ѵ��Ϣ����б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

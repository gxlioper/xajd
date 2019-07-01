<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjgz/js/qjgz.js"></script>
				<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"��ٹ����б�",
									pager:"pager",
									url:"qjgz.do?method=list&type=query",
									colList:[
									   {label:'��ٹ���id',name:'qjgzid', index: 'qjgzid',key:true,hidden:true},
									   {label:'kssj',name:'kssj', index: 'kssj',hidden:true},
									   {label:'jssj',name:'jssj', index: 'jssj',hidden:true},
                                       {label:'ssxydm',name:'ssxydm', index: 'ssxydm',hidden:true},
									   {label:'�������',name:'qjqj', index: 'qjqj',formatter:dcmcLink,width:'40%'},
									   {label:'�������',name:'qjlxmc', index: 'qjlxmc',width:'10%'},
                                        {label:'����ѧԺ',name:'ssxymc', index: 'ssxymc',width:'10%'},
									   {label:'�������',name:'lcxx', index: 'lcxx',width:'35%'},
									   {label:'����',name:'openzt', index: 'openzt',width:'10%',formatter:openLink}
									],
									sortname: "jssj",
								 	sortorder: "asc"
								};
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
	<html:form action="/qjgz?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden"  name="userXy" id="userXy" value="${userXy}"/>
		<input type="hidden"  name="userXymc" id="userXymc" value="${userXymc}"/>
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
						<li>
							<a href="javascript:void(0);" onclick="View();return false;" class="btn_ck">�鿴</a>
						</li>
				</ul>
			</div>
			</logic:equal>
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ٹ����б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

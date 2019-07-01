<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/gygl/gypynew/yxxs/js/yxxs.js"></script>
				<script type="text/javascript">
					jQuery(function(){
						var query=jQuery("#query").val();
					     var gridSetting = {
									caption:"����ѧ��",
									pager:"pager",
									url:"gypy.do?method=yxxs&type=query",
									colList:[
									   {label:'id',name:'gypyid', index: 'gypyid',key:true,hidden:true},
									   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',formatter:dcmcLink},
									   {label:'����',name:'xm', index: 'xm',width:'10%'},
									   {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
									   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
									   {label:'¥��',name:'ldmc', index: 'lddm',width:'15%'},
									   {label:'���Һ�',name:'qsh', index: 'qsh',width:'15%'},
									   {label:'ѧ��',name:'xn', index: 'xb',width:'10%'},
									   {label:'ѧ��',name:'xqmc', index: 'xqdm',width:'10%'}
									],
									sortname: "xn",
								 	sortorder: "desc"
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
	<html:form action="/gypy.do?method=yxxs&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>����ѧ�� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

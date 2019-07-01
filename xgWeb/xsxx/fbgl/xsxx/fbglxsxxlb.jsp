<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/xsxx/js/fbglxsxx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"�ְ����ѧ����Ϣ�б�",
									pager:"pager",
									url:"fbglxsxx.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
									   {label:'������',name:'ksh', index: 'ksh',formatter:dcmcLink},
									   {label:'����',name:'xm', index: 'xm'},
									   {label:'�Ա�',name:'xb', index: 'xb'},
									   {label:'�꼶',name:'nj', index: 'nj'},
									   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
									   {label:'רҵ',name:'zymc', index: 'zymc'},
									   {label:'Ͷ���ɼ�',name:'tdcj', index: 'tdcj'},
									   {label:'��Դ��',name:'syd', index: 'syd',hidden:true},
									   {label:'ѧ��',name:'xz', index: 'xz'},
									   {label:'���',name:'pyccmc', index: 'pyccmc'},
									   {label:'�༶',name:'bjmc', index: 'bjmc'},
									   {label:'ѧ��',name:'xh', index: 'xh'}
									]
									
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
		<html:form action="/fbglxsxx?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;"
								class="btn_sc">ɾ��</a>
						</li>
						<li><a href="#" onclick="drxx();return false;" class="btn_dr">����</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">�ְ����ѧ����Ϣ <a id="title"
					href="javascript:;"
					style="float: right; margin-right: 30px; color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

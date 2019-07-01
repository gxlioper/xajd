<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rwdj/js/rwdj.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			     var gridSetting = {
							pager:"pager",
							url:"rwdj.do?method=list&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'rwdjid',name:'rwdjid', index: 'rwdjid',key:true,hidden:true},
							   {label:'ѧ�� ',name:'xh', index: 'xh',formatter:dcmcLink},
							   {label:'����',name:'xm', index: 'xm'},
							   {label:'�Ա�',name:'xb', index: 'xb'},
							   {label:'�꼶',name:'nj', index: 'nj'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
							   {label:'רҵ',name:'zymc', index: 'zymc'},
							   {label:'�༶',name:'bjmc', index: 'bjmc'},
							   {label:'����ʱ��',name:'rwsj', index: 'rwsj'},
							   {label:'���۲���',name:'fybd', index: 'fybd'},
							    {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
							],
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
		<html:form action="/rwdj.do?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="search_go" onclick="reload()" />
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
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
							<li>
								<a href="#" onclick="drxx();return false;" class="btn_csh">����</a>
							</li>
							<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
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
				<span style="width: 50%">��ѯ�б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

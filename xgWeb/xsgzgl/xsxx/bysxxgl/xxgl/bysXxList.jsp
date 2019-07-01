<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxgl/js/xxgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
				    		 caption:"��ҵ����Ϣ�б�",
								pager:"pager",
								url:"xsxx_new_bysxx_xxgl.do?method=cxBysXxList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true,formatter:dcmcLink},
								   {label:'����',name:'xm', index: 'xm',width:'10%'},
								   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
								   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
								   {label:'��ҵ���',name:'bynd', index: 'bynd',width:'8%'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
								   {label:'רҵ',name:'zymc', index: 'zymc',width:'12%'},
								   {label:'�༶',name:'bjmc', index: 'bjmc',width:'12%'},
								   {label:'�������',name:'pyccmc', index: 'pyccmc',width:'5%'},
								   {label:'���֤��',name:'sfzh', index: 'sfzh',width:'15%'}
								],
								sortname: "xh",
							 	sortorder: "asc",
							}
						var map = getSuperSearch();
						gridSetting["params"] = map;
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
	<html:form action="/xsxx_bysxx_xxgl.do?method=cxBysXxList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="bysXxAdd();return false;" class="btn_zj">���׼��ҵ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="bysXxDel();return false;" class="btn_zj">ȡ��׼��ҵ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="bysXxEdit();return false;" class="btn_xg">�޸�</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);"onclick="printByjdb('xsxx_bysxx_xxgl.do?method=printByjdb');return false;" class="btn_down">���ر�ҵ��������</a></li>	
						<%-- 
						<li><a href="javascript:void(0);" onclick="printQjjgb('qjjg.do?method=printQjjgb');return false;" class="btn_down">���ؽ����</a></li> 
						--%>  
						<%--
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>
				--%></ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ҵ����Ϣ�б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

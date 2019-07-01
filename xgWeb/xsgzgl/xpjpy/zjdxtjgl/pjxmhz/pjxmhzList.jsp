<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxtjgl/pjxmhz/js/pjxmhz.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"������Ŀ�����б�",
				pager:"pager",
				url:"xpjpy_pjxmhz.do?method=getPjxmhzList&type=query",
				colList:[
				   {label:'ѧ��',name:'xn', index: 'xn',width:'6%'},
				   {label:'��Ŀ���ʹ���',name:'lxdm', index: 'lxdm',hidden:true},
				   {label:'��Ŀ���ʴ���',name:'xzdm', index: 'xzdm',hidden:true},
				   {label:'��Ŀ����',name:'xmlxmc', index: 'xmlxmc',width:'8%'},
				   {label:'��Ŀ����',name:'xmxzmc', index: 'xmxzmc',width:'8%'},
				   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'16%'},
				   {label:'������',name:'hjrs', index: 'hjrs',width:'6%',formatter:rsLink}
				],
				sortname: "xn",
			 	sortorder: "desc"
			}		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span color="blue"> ������Ŀ�����б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
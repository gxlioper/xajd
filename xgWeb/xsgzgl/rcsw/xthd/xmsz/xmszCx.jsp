
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsz/js/xmsz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				gridSetting["params"]=getSuperSearch();
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
	<html:form action="/rcsw_txhd_xmszCx?method=list&type=query" styleId="TxhdXmszForm">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="copystxx();" class="btn_fz">����</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="sjkg();" class="btn_sz">��Ŀ��������</a></li>						
					    </logic:equal>
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
				<span id="title">��Ŀ�б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src='xsgzgl/xlzx/thjl/js/thjlManage.js'></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_thjl" styleId="form">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<logic:notEqual name="userStatus" value="stu">
								<li>
									<a href="#" onclick="addThjl();return false;" class="btn_zj">����</a>
								</li>
								<li>
									<a href="#" onclick="updateThjl();return false;" class="btn_xg">�޸�</a>
								</li>
								<li>
									<a href="#" onclick="deleteThjl();return false;" class="btn_sc">ɾ��</a>
								</li>
								<logic:equal value="10346" name="xxdm">
									<li><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">����</a></li>
								</logic:equal>
							</logic:notEqual>
						</logic:equal>
						<li>
							<a href="#" onclick="viewThjl();return false;" class="btn_ck">�鿴</a>
						</li>
						<li>
							<a href="#" onclick="exportThjl();return false;" class="btn_dc">����</a>
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
				<span> ̸����¼��Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

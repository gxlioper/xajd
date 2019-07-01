<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/daxxgl/daxxglList.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/daxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="addDaxxgl();return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="#" onclick="updateDaxxgl();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" onclick="delDaxxgl();return false;" class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="qdwhDaxxgl();return false;" class="btn_sz">�嵥ά��</a>
							</li>
							<li>
								<a href="#" onclick="drDaxxgl();return false;" class="btn_dr">����</a>
							</li>
						
							<li>
								<a href="#" onclick="dcDaxxgl();return false;" class="btn_dc">����</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ��� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

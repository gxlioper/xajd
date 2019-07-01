<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
			<script language="javascript" src="xsgzgl/xlzx/yysqnew/js/yysqManage.js"></script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_yysqnew" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			
				<div class="toolbox">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="addYyzxInfo();return false;" class="btn_zj">����ԤԼ</a>
							</li>
							<li>
								<a href="#" onclick="updateYyzxInfo();return false;" class="btn_xg">�޸�ԤԼ</a>
							</li>
							<li>
								<a href="#" onclick="qxYyzxInfo();return false;" class="btn_sc">ȡ��ԤԼ</a>
							</li>
							<li>
								<a href="#" onclick="pjYyzxInfo();return false;" class="btn_xg">��ѯ����</a>
							</li>
						</logic:equal>
							<li>
								<a href="#" onclick="ckYyzxInfo();return false;" class="btn_ck">�鿴ԤԼ</a>
							</li>
						</ul>
					</div> 
				</div>
		  		<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ԤԼ��ѯ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

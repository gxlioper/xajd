<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khbgl/js/khbgl.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
		
		</script>
		
	</head>
	<body>
	<html:form action="/khglKhbgl" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" class="btn_cx" id="search_go" onclick="searchRs();closeMore();return false;"/>
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="khnrwh();return false;" class="btn_sz" >��������ά��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="khnryl();return false;" class="btn_yl" >Ԥ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="khbfz();return false;" class="btn_fz" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qySz('1');return false;" class="btn_plqy" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qySz('2');return false;" class="btn_plty" >ͣ��</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>���˱��б�</font> </span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>

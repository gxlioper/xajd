<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxjjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xszz_knsjg" >

			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knsjgb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">��дС��</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�С��</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��С��</a></li>						
						</logic:equal>
						<li><a href="#" onclick="ck();return false;" id="btn_ck" class="btn_ck">�鿴</a></li>
						<logic:equal name="writeAble" value="yes">	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printXnxjSq('xsxx_xnxj_xjjggl.do?method=doPrint');return false;" class="btn_down">����С���</a></li>	
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
				<span>ѧ��С�����б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

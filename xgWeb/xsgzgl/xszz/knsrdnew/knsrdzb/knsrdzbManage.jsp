<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xszz/knsrdnew/knsrdzb/js/knsrdzbManage.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		
			
		<script type="text/javascript">
		
		
		</script>
	</head>
	<body>
	<html:form action="/rcsw_xszbb_bblxwhgl" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>	
					</logic:equal>
						<li><a href="javascript:void(0);" onclick="view();" class="btn_xg">�鿴</a></li>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="openQy();" class="btn_sc">����</a></li>	
						<li><a href="javascript:void(0);" onclick="copy();" class="btn_sc">����</a></li>	
					</logic:equal>						
				</ul>
			</div>
		</div>
			<div class="formbox">
			<button type="button" style="display:none" class="btn_cx" id="search_go" onclick="query()">
			</button>
			<!--����start-->
				<h3 class="datetitle_01">
					<span> �������϶�ָ���б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>

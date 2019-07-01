<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var newsid=jQuery("#newsid").val();
			var tablename=jQuery("#tablename").val();
			var gridSetting = {
				caption : "���Ķ���Ա�б�",
				pager : "pager",
				multiselect:false,
				url : "xtwh_news.do?method=yydmdView&type=query",
				colList : [
							{ label : 'key', name : 'lljlid', index : 'lljlid',key : true, hidden : true },
							{ label : '�û���', name : 'yhm', index : 'yhm',width:"20%"},
							{ label : '���ʱ��', name : 'llsj', index : 'llsj',width:"20%"},
							{ label : '����', name : 'xm', index : 'xm',width:"20%"},
							{ label : '����', name : 'bmmc', index : 'bmmc',width:"20%"},
							{ label : '�û�����', name : 'yhlxmc', index : 'yhlxmc', width : '10%'}
							],
					sortname : "llsj", sortorder : "desc" }
			var map = {
					"newsid":newsid,
					"tablename":tablename
				}
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

		</script>
	</head>

	<body>
		<html:form action="/xtwh_news">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="newsid" styleId="newsid"/>
			<html:hidden property="tablename" styleId="tablename"/>
			<div>
				</div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color="blue">���Ķ���Ա�б�</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

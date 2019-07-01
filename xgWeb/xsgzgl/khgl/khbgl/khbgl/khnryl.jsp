<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
		jQuery(function(){
			var gridSetting = {
				caption : "���˱��б�",
			 pager : "pager",
			 multiselect:false,
			url : "khglKhnrgl.do?method=getKhnrglList&type=query&khbid="+jQuery("#khbid").val(),
			colList : [ 
			   {label : 'zbmxid',name : 'zbmxid',index : 'zbmxid',key : true,hidden:true},
			   {label : 'Khnrid',name : 'Khnrid',index : 'Khnrid',hidden:true},
			   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true}, 
			   {label : 'sfnz',name : 'sfnz',index : 'sfnz',hidden:true}, 
			   {label : '��������',name : 'khnr',index : 'khnr',width : '60%'},
			   {label : 'һ��ָ��',name : 'yjzb',index : 'yjzb',width : '10%'},
			   {label : '����ָ��',name : 'ejzb',index : 'ejzb',width : '10%'},
			   {label : '��ֵ',name : 'fzqj',index : 'fzqj',width : '10%'},
			   {label : '��������',name : 'pflxmc',index : 'pflxmc',width : '10%'}
			   ]
		}
			var map = {};
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
	</head>
	<body>
	<html:form action="/khglKhnrgl" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="khbid" id="khbid" value="${khbid }"/>
	<div class="tab_cur">
		</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>${khbmc}</span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
					</div>
				</div>		
	</body>
</html>

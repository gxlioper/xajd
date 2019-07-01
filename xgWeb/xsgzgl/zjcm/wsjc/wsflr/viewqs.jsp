<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"ѧ����Ϣ�б�",
					pager:"pager",
					rowNum:10,
					url:"cjWsflr.do?method=viewqs&type=query",
					colList:[
					   {label:'�������',name:'ccny', index: 'ccny',width:'13%',key:true},
					   {label:'¥��',name:'ldmc', index: 'ldmc',width:'15%'},
					   {label:'���Һ�',name:'qsh', index: 'qsh',width:'10%'},
					   {label:'�����꼶',name:'nj', index: 'nj',width:'10%'},
					   {label:'����ѧԺ',name:'bmmc', index: 'bmmc',width:'20%'},
					   {label:'��λ��',name:'cws', index: 'cws',width:'10%'},
					   {label:'��ס����',name:'rzrs', index: 'rzrs',width:'15%'},
					   {label:'��ֵ',name:'fz', index: 'fz',width:'10%'},
					   {label:'�ȼ�',name:'dj', index: 'dj',width:'10%'}				   
					],
					params:{
						tjzt:jQuery("#tjzt").val(),
						ccny:jQuery("#ccny").val()
					}
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				map["tjzt"] = jQuery("#tjzt").val();
				map["ccny"] = jQuery("#ccny").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/cjWsfDfgz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="tjzt" name="tjzt" value="${tjzt}"/>
			<input type="hidden" id = "ccny" name="ccny" value="${ccny}"
			   
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ���
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>

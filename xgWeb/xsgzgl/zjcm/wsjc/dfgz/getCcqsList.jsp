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
					url:"cjWsfDfgz.do?method=getCcqsList&type=query",
					colList:[
					   {label:'�������',name:'ccny', index: 'ccny',width:'11%',key:true},
					   {label:'¥��',name:'ldmc', index: 'ldmc',width:'13%'},
					   {label:'���Һ�',name:'qsh', index: 'qsh',width:'8%'},
					   {label:'�����꼶',name:'nj', index: 'nj',width:'8%'},
					   {label:'����ѧԺ',name:'xymc', index: 'xymc',width:'22%'},
					   {label:'��λ��',name:'cws', index: 'cws',width:'8%'},
					   {label:'��ס����',name:'rzrs', index: 'rzrs',width:'8%'},
					   {label:'��ֵ',name:'fz', index: 'fz',width:'7%'},
					   {label:'������',name:'pfzmc', index: 'pfzmc',width:'15%'}
					   
					],
					params:{
						tjzt:jQuery("#tjzt").val(),
						dfszid:jQuery("#dfszid").val()
					}
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				map["tjzt"] = jQuery("#tjzt").val();
				map["dfszid"] = jQuery("#dfszid").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/cjWsfDfgz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="tjzt" name="tjzt" value="${tjzt}"/>
			<input type="hidden" id = "dfszid" name="dfszid" value="${dfszid }"
			   
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

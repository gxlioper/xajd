<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/pfzgl/js/pfzwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"��ѯ���",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"khglPfzgl.do?method=viewPfcy&type=query&pflx="+'${pflx}'+"&pfzid="+'${pfzid}'+"&khlx="+'${khlx}'+"&xh="+'${xh}'+"&zgh="+'${zgh}',
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'sfydf',name:'sfydf', index: 'sfydf',width:'5%',hidden:true},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'�Ƿ��ɲ�',name:'sfbgbmc', index: 'sfbgbmc',width:'10%'},
				   {label:'�Ƿ�����������',name:'sfsypfz', index: 'sfsypfz',width:'10%'}
						],
				sortname: "sfsypfz",
			 	sortorder: "desc",
			}
			var gridSetting2 = {
					caption:"��ѯ���",
					pager:"pager",
					multiselect:false,
					rowNum:10,
					url:"khglPfzgl.do?method=viewPfcy&type=query&pflx="+'${pflx}'+"&pfzid="+'${pfzid}'+"&khlx="+'${khlx}'+"&zgh="+'${zgh}'+"&xh="+'${xh}',
					colList:[
							   {label:'ְ����',name:'zgh', index: 'zgh',width:'10%',key:true},
							   {label:'sfydf',name:'sfydf', index: 'sfydf',width:'5%',hidden:true},
							   {label:'����',name:'xm', index: 'xm',width:'10%'},
							   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
							   {label:'��������',name:'bmmc', index: 'bmmc',width:'20%'},
							   {label:'�Ƿ�����������',name:'sfsypfz', index: 'sfsypfz',width:'10%'}
							],
							sortname: "sfsypfz",
						 	sortorder: "desc",
				}
			
		jQuery(function(){
			var map = getSuperSearch();
			var gridSetting={};
			var pflx=${pflx};
			if("1"==pflx){
				gridSetting=gridSetting1;
				}
			else{
				gridSetting=gridSetting2;
			}
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglPfzgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<input type="hidden" id ="pflx" value="${pflx}"/>
			<input type="hidden" id ="khlx" value="${khlx}"/>
			<input type="hidden" id ="xh" value="${xh}"/>
			<input type="hidden" id ="zgh" value="${zgh}"/>	
			<div class="toolbox">
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
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>

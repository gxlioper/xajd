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
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"khglPfzgl.do?method=showPfcy&type=query&pflx="+'${pflx}'+"&pfzid="+'${pfzid}'+"&khlx="+'${khlx}'+"&xh="+'${xh}'+"&zgh="+'${zgh}',
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
			
		jQuery(function(){
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglPfzgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid}"/>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<input type="hidden" id ="pflx" value="${pflx}"/>
			<input type="hidden" id ="khlx" value="${khlx}"/>
			<input type="hidden" id ="xh" value="${xh}"/>
			<input type="hidden" id ="zgh" value="${zgh}"/>
			<input type="hidden" id ="sfqx" value="${sfqx}"/>
			<input type="hidden" id ="khdxrs" value="${khdxrs}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="savePfcy('save');return false;" class="btn_sz">��������</a>
						</li>
						<logic:notEqual value="1" name="sfqx">
						<li>
							<a href="javascript:void(0);" onclick="savePfcy('del');return false;" class="btn_sz">ȡ������</a>
						</li>
						</logic:notEqual>
				</ul>
				</div>
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/bbgl/js/fbglbbgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function($){
				 var gridSetting = {
							caption:"��ѯ���",
							url:"fbglbbgl.do?method=fbgltbxx&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
							   {label:'bjbh',name:'bjbh', index: 'bjbh',hidden:true},
							   {label:'�༶����',name:'bjdm', index: 'bjdm',formatter:bjdmFormatter},
							   {label:'�༶����',name:'bjmc', index: 'bjmc',formatter:bjmcFormatter},
							   {label:'�Զ��ְ���������',name:'bjrssx', index: 'bjrssx',formatter:rssxTbFormatter},
							   {label:'ѧ����',name:'xss', index: 'xss'}
							],
							sortname: "nj",
						 	sortorder: "asc",
						 	selectFormatter:removeCheck
				}
					var pk=jQuery("#pk").val();
			    	 var map = getSuperSearch();
			    	 map["pk"]=pk;
			    	gridSetting["params"] = map;
					$("#dataTable").initGrid(gridSetting);
					$("#gjcx").hide();
					//�������� ɾ��
					var td=$(".nowrap").find("td").eq(0);
					$(td).html($("#mybutton").html());
			});
			function removeCheck(rowObject){
				return rowObject["bjbh"];
			}
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=fbgltbxx&type=query">
				<input type="hidden" id="pk" value="${pk}"/>
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<%@ include file="/comm/hiddenValue.jsp"%>
				<div id="gjcx" style="display: none;">
					<!-- �������� -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- �������� end-->
			</html:form>
			<div id="mybutton" style="display: none;">
				<img src="xsxx/fbgl/images/add-icons-1.gif" alt="����" onclick="addfb();return false;"/>
				<img src="xsxx/fbgl/images/close-icons.gif" alt="ɾ��" onclick="delfb();return false;"/>
			</div>
			<div class="toolbox">
				<table id="dataTable" name="tbxx" />
			</div>
		</div>
	</body>
</html>

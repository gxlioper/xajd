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
			jQuery(function(){
			     var gridSetting = {
							caption:"��ѯ���",
							pager:"pager",
							url:"fbglbbgl.do?method=fbglbb&type=query",
							params:getSuperSearch(),
							multiselect:false,
							colList:[
							   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
							   {label:'�꼶',name:'nj', index: 'nj'},
							   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
							   {label:'רҵ',name:'zymc', index: 'zymc'},
							   {label:'����',name:'rs', index: 'rs'},
							   {label:'���',name:'pyccmc', index: 'pyccmc'},
							   {label:'ѧ��',name:'xz', index: 'xz'},
							   ${autoGrid}
							   {label:'<font color=\'red\'>*</font>�༶����',name:'bjgs', index: 'bjgs',formatter:bjgsFormatter},
							   {label:'��ˮ��',name:'lsh', index: 'lsh',formatter:lshFormatter},
							   {label:'�Զ��ְ���������',name:'rssx', index: 'rssx',formatter:rssxFormatter}
							],
							sortname: "nj",
						 	sortorder: "asc"
						}
					//Ĭ�ϲ�ѯ�������ҿ��������Һ����ᡣ
					var pk=jQuery("#pk").val();
					var pzgzid=jQuery("#pzgzid").val();
			    	 var map = getSuperSearch();
			    	 map["pk"]=pk;
			    	 map["pzgzid"]=pzgzid;
			    	 gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
					//����ҳ��߼���ѯ����ҳ
					jQuery("#gjcx").hide();
					jQuery("#pager").hide();
			});
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=add&type=query">
				<input type="hidden" id="pzgzid" value="${pzgzid}"/>
				<input type="hidden" id="bbzt" value="${bbzt}"/>
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<%@ include file="/comm/hiddenValue.jsp"%>
				<div id="gjcx" style="display: none">
					<!-- �������� -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- �������� end-->
			</html:form>
			<div class="toolbox">
				<table id="dataTable"></table>
				<div id="pager" style="display: none"></div>
			</div>
		</div>
	</body>
</html>

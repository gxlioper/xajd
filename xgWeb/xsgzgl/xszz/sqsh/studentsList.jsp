<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ����Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"xszz_sqsh.do?method=getStudentsByShqk&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%'},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
				   {label:'���״̬',name:'shztmc', index: 'shzt',width:'12'},
				   {label:'shsj',name:'shsj', index: 'shsj',hidden:true},
				   {label:'shyj',name:'shyj', index: 'shyj',hidden:true}
<%--				   {label:'����',name:'shr', index: 'shr',width:'10%',formatter:doShow,noSort:true}--%>
				],
				params:{
						shzt:"${xszzSqshForm.shzt}",
						xtgwid:"${xszzSqshForm.xtgwid}",
						xmdm:"${xszzSqshForm.xmdm}"
				},
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function doShow(cellValue,rowObject){
				return "<a href='javascript:void(0);' title='����ˣ�"+rowObject["shr"]+"\r\n���ʱ�䣺"+rowObject["shsj"]+"\r\n��������"+rowObject["shyj"]+"' class='mouse-tsk'>��ϸ</a>";
				
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				map["shzt"] = jQuery("#shzt").val();
				map["xtgwid"] = jQuery("#xtgwid").val();
				map["xmdm"] = jQuery("#xmdm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
	
		<html:form action="/xszz_sqsh">
			<html:hidden property="xtgwid" styleId="xtgwid" />
			<html:hidden property="shzt" styleId="shzt" />
			<html:hidden property="xmdm" styleId="xmdm" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б�
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

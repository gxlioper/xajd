<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"qgzx_kycxsjxmfysb.do?method=ffcx&type=query&xmid="+jQuery("#xmid").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'��ϵ�绰',name:'sjhm', index: 'sjhm',width:'15%'},
				   {label:'�����·�',name:'ffyf', index: 'ffyf',width:'10%'},
				   {label:'��Ŀ�ڷֹ�',name:'xmfg', index: 'xmfg',width:'10%'},
				   {label:'��ʱ',name:'gs', index: 'gs',width:'5%'},
				   {label:'��Ԫ��',name:'cjje', index: 'cjje',width:'8%'}
				],
				sortname: "xh",
			 	sortorder: "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/qgzx_kycxsjxmfysb">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xmid" name="xmid" value='${xmid }'/>
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
		<div class="formbox" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>

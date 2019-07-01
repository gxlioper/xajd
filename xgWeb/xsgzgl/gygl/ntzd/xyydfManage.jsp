<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"<bean:message key="lable.xb" />�¿��˵÷��б�",
					pager:"pager",
					url:"gygl_ntzd.do?method=xyydfManage&type=query",
					colList:[
					   {label:'����',name:'ny', index: 'ny',width:'10%',key:true},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
					   {label:'�¿��˵÷�',name:'ykhdf', index: 'to_number(ykhdf)',width:'10%'},
					   {label:'ѧУ����',name:'xxpm', index: 'to_number(xxpm)',width:'10%'},
					   {label:'ѧУ<bean:message key="lable.xb" />��',name:'xxxys', index: 'xxxys',width:'10%'}
					],
					sortname: "ny desc ,to_number(xxpm) ",
				 	sortorder: "asc"
			}
			function exportConfig() {
				customExport("gygl_ntzd_xyydf.do", exportData,650,500);
			}
			// ��������
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "gygl_ntzd.do?method=exportXyydfData&dcclbh=" + "gygl_ntzd_xyydf.do";//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_dc").click(exportConfig);
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_ntzd.do?method=xyydfManage">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" id="btn_dc" class="btn_dc">����</a></li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><bean:message key="lable.xb" />�¿��˵÷��б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

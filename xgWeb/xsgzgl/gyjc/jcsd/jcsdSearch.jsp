<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcsd/js/jcsd.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_jcsd.do?method=jcsdList&type=query",
				colList : [ {
					label : 'key',
					name : 'xydm',
					index : 'xydm',
					key : true,
					hidden : true
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xymc',
					width : '10%'
					//formatter : xhLink
				}, {
					label : '¥�����',
					name : 'ldnum',
					index : 'ldnum',
					width : '20%',
					formatter : ldLink
				}, {
					label : '���������',
					name : 'wsjcr',
					index : 'wsjcr',
					width : '10%'
				}, {
					label : '��ȫ�����',
					name : 'aqjcr',
					index : 'aqjcr',
					width : '10%'
				}, {
					label : '���ɼ����',
					name : 'jljcr',
					index : 'jljcr',
					width : '10%'
				},
				<logic:equal name="userType" value="xx">
				{
					label : '�����',
					name : 'ccr',
					index : 'ccr',
					width : '15%'
				},
				</logic:equal>
				{
					label : '���ֱ�׼',
					name : 'pfnum',
					index : 'pfnum',
					width : '17%',
					formatter:pfLink
				},
				{
					label : 'chnum',
					name : 'chnum',
					index : 'chnum',
					hidden : true
				},
				{
					label : 'qshnum',
					name : 'qshnum',
					index : 'qshnum',
					hidden : true
				}],
				sortname : "xydm",
				sortorder : "asc",
				radioselect:true
			}
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gyjc_jcsd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="fpjcr('1');return false;"  >�������������</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="fpjcr('2');return false;" class="btn_sz" >���䰲ȫ�����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="fpjcr('3');return false" class="btn_sz" >������ɼ����</a>
						</li>
						<logic:equal name="userType" value="xx">
							<li><a href="#" class="btn_sz" onclick="fpjcr('0');return false;">��������</a></li>	
							
						</logic:equal>
						<li><a href="#" class="btn_sz" onclick="pfbzwh();return false;">���ֱ�׼ά��</a></li>	
						</logic:equal>
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

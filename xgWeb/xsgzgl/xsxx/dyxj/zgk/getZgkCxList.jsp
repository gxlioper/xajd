<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/zgk/js/zgk.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "�б�",
				pager : "pager",
				url : "dyxj_dyzgk.do?method=getDyzgkCx&type=query",
				colList : [ {
					label : 'key',
					name : 'xh',
					index : 'xh',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%'
					//,formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				}, {
					label : 'רҵ',
					name : 'zymc',
					index : 'zydm',
					width : '15%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '15%'
				}],
				sortname : "xh",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
		</div>
		<html:form action="/dyxj_dyzgk" styleId="zgkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="save();return false;" class="btn_zj">���</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

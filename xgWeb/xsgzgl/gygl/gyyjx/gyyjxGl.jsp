<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
		<script type="text/javascript" >
		var gridSetting_gl = {
				caption : "��ѯ���",
				pager : "pager",
				url : "gygl_gyyjxstu.do?method=listGl&type=query",
				colList : [
						{ name : 'gyyjid', index : 'gyyjid', key : true , hidden : true},
						{ label : 'ѧ��', name : 'xh', index : 'xh',  width : '12%' ,formatter:xhLink_stu},
						{ label : '����', name : 'xm', index : 'xm', width : '12%' },
						{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
						{ label : '����¥��', name : 'ldmc', index : 'ldmc', width : '13%' },
						{ label : '�������', name : 'yjflmc', index : 'yjflmc', width : '15%' },
						{ label : '�����ʱ��', name : 'yjsj', index : 'yjsj', width : '12%' },
						{ label : '�������', name : 'fkqkmc', index : 'fkqkmc', width : '8%' },
						{ name : 'fkqk', index : 'fkqk',  hidden : true}],
				sortname : "yjsj", sortorder : "desc" };
		jQuery(function() {
			jQuery("#dataTable").initGrid(gridSetting_gl);
		});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_gyyjxstu">

		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="fk_gl();return false;" >����</a>
						</li>
						<li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</li>
					</ul>
				</div>
				</logic:equal>
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

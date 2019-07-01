<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "ȫУѧ����Ϣ��ѯ����б�",
				pager : "pager",
				url : "xsxx_zjdx_qxxscxgl.do?method=getQxxscxList&type=query",
				colList : [
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '9%' },
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '2%' },
							{ label : '��ϵ�绰', name : 'sjhm', index : 'sjhm', width : '8%' },
							{ label : '�꼶', name : 'nj', index : 'nj', width : '2%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xydm', width : '10%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '12%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '12%' }],
					sortname : "xh",
				    sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
			// �߼���ѯ
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		
		<html:form action="/xsxx_zjdx_qxxscxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť  --> 
<!--				<div class="buttonbox">-->
<!--					<ul>										-->
<!--						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >����</a></li>						-->
<!--					</ul>-->
<!--				</div>-->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ȫУѧ����Ϣ��ѯ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stzhwh/js/stzhwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
	jQuery(function(){
			var gridSetting = {
				caption : "���ų�Ա�ɼ���ϸ",
				pager : "pager",
				url : "stglStzhwh.do?method=getStcycjList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'xmlbdm', name : 'xmlbdm', index : 'xmlbdm', hidden : true },
							{ label : 'stlbdm', name : 'stlbdm', index : 'stlbdm', hidden : true },
							{ label : 'stid', name : 'stid', index : 'stid', hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '10%' },
							{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '15%' },
							{ label : 'רҵ', name : 'zymc', index : 'zydm', width : '15%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xydm', hidden : true },
							{ label : '�������', name : 'stlbmc', index : 'stlbmc', width : '10%' },
							{ label : '��Ŀ���', name : 'xmlbmc', index : 'xmlbmc', width : '15%' },
							{ label : '������Ŀ����', name : 'stxmmc', index : 'stxmmc', width : '15%'  },
							{ label : 'ָ����ʦ', name : 'zdlsxm', index : 'zdlsxm', width : '12%' },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '12%' },
							{ label : '���˽��', name : 'cjpd', index : 'cjpd', width : '10%'}
							],
					sortname : "xn",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="usertype" value="${usertype}">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/stglRtjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ų�Ա�ɼ���ϸ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

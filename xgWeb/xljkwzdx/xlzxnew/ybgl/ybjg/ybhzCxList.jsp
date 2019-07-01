<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/ybgl/ybjg/js/ybjg.js"></script>
		<script type="text/javascript">
	
	jQuery(function() {
		var gridSetting = {
				pager : "pager",
				url : "xlzxnew_ybjg.do?method=getYbhzCxList&type=query",
				colList : [
						{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true},
						{ label : 'ybid', name : 'ybid', index : 'ybid',hidden : true},
						{ label : 'ѧ��', name : 'xn', index : 'xn', width : '25%' },
						{ label : '�¶�', name : 'yfmc', index : 'yfmc', width : '20%' },
						{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '30%',formatter:yblink},
						{ label : '�±����', name : 'ybqk', index : 'ybqk', width : '25%'}
						],
				 };
		 var map = getSuperSearch();
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	});

</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzxnew_ybjg" >
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportHzConfig();return false;" class="btn_dc">����</a>
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
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

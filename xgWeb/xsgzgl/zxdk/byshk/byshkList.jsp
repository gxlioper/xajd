<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/byshk/js/hk.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��ҵ��δ�����ѯ����б�",
				pager : "pager",
				url : "zxdk_byshkcx.do?method=getByshkList&type=query",
				colList : [
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%' },
							{ label : '����', name : 'xm', index : 'xm', width : '10%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : '�꼶', name : 'nj', index : 'nj', width : '8%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xydm', width : '25%' },
							{ label : '������Ŀ', name : 'dkxm', index : 'dkxm', width : '20%' },
							{ label : '���', name : 'sqje', index : 'sqje', width : '10%' },
							{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' }],
					sortname : "xh",
				    sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
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
		<html:form action="/zxdk_byshkcx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="gbhkzt();return false;" >����</a>
						</li>
						</logic:equal>	
						
						<logic:equal value="zf01" name="userName">
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;" >����</a>
						</li>	
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
				<span>��ҵ��δ�����ѯ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

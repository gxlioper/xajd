<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dycjgl/dycjgl/js/dycjgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "dycjwh_dycjgl.do?method=dycjglListQuery",
				colList : [
							{ label : 'key', name : 'bjdm', index : 'bjdm', hidden : true },
							{ label : 'ѧ��', name : 'xq', index : 'xq' , width : '7%'},
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '7%'},
							{ label : '�꼶', name : 'nj', index : 'nj', width : '8%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '11%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '11%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '11%' },
							{ label : '��¼��/�༶����', name : 'rszb', index : 'rszb' ,width : '11%'}],
					sortname : "bjmc",
				    sortorder : "desc" }
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
		
		<html:form action="/dycjwh_dycjgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="bjmdfswh();return false;" class="btn_xg" >�༶����������ά��</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="viewBjmd();" class="btn_ck">�鿴</a>
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
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

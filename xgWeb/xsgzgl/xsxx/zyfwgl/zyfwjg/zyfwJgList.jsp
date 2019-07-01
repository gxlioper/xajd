<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/zyfwgl/zyfwjg/js/zyfwJg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "${gnmkmc}�б�",
				pager : "pager",
				url : "xsxx_zyfwgl_jg.do?method=getZyfwJgListData",
				colList : [
							{ label : 'key', name : 'fwid', index : 'fwid',key : true, hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '11%', formatter : xhLink},
							{ label : '����', name : 'xm', index : 'xn', width : '10%' },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '11%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xq', width : '10%' },
							{ label : '����ʼʱ��', name : 'fwkssj', index : 'fwkssj', width : '16%' },
							{ label : '�������ʱ��', name : 'fwjssj', index : 'fwjssj', width : '16%' },
							{ label : '����ص�', name : 'fwddxxdz', index : 'fwddxxdz', width : '20%',formatter : fwddSubString },
							{ label : '����Сʱ��', name : 'fwxss', index : 'fwxss', width : '6%' }],
					sortname : "xn,xq,fwkssj",
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
		
		<html:form action="/xsxx_zyfwgl_jg">
		<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
						</li>
						</logic:equal>
										
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
				<span>${gnmkmc}�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

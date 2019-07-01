<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xjjt/wmqs/js/wmqs.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				pager:"pager",
				url:"xpjpy_wmqs.do?method=getDataList&type=query",
				colList:[
					{ label : 'key', name : 'guid', index : 'guid',key : true, hidden : true },
					{ label : 'ѧ��', name : 'xn', index : 'xn', width : '4%'},
					{ label : 'У������', name : 'xqmc', index : 'xqmc', width : '11%'},
					{ label : '¥��', name : 'ldmc', index : 'ldmc', width : '11%'},
					{ label : '���Һ�', name : 'qsh', index : 'qsh', width : '11%'},
				  ],
					sortname : "xqmc",
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
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/xpjpy_wmqs">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<%--<logic:equal name="writeAble" value="yes">
							--%><li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="updateWmqs();return false;" >�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="delWmqs();return false;" >ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_dr" onclick="importWmqs();return false;" >����</a>
							</li>
						<%--</logic:equal>
						
						--%><logic:equal value="zf01" name="userName">
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
				<span>���������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xjjt/xjbj/js/xjbj.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				pager:"pager",
				url:"xpjpy_xjbj.do?method=getDataList&type=query",
				colList:[
					{ label : 'key', name : 'guid', index : 'guid',key : true, hidden : true },
					{ label : 'ѧ��', name : 'xn', index : 'xh', width : '4%'},
					{ label : '�Ƚ��༶', name : 'bjmc', index : 'bjmc', width : '11%'},
					{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '11%'},
					{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '11%'},
					{ label : '�꼶', name : 'nj', index : 'nj', width : '4%'}
				  ],
					sortname : "bjmc",
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
				<em>���ĵ�ǰλ�ã�</em><a>����������-���㼯��-�Ƚ��༶</a>
			</p>
		</div>
		<html:form action="/xpjpy_xjbj">
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
								<a href="javascript:void(0);" class="btn_xg" onclick="updateXjbj();return false;" >�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="del();return false;" >ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_dr" onclick="importXjbj();return false;" >����</a>
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
				<span>�Ƚ��༶�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
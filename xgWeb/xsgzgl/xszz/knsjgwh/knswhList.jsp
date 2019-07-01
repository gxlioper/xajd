<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjgwh/js/knsjgwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "������������ά���б�",
				pager : "pager",
				url : "xszz_knsdcwh.do?method=getKnsWhList&type=query",
				colList : [
							{ label : 'id', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '���', name : 'nd', index : 'nd', width : '4%' },
							{ label : 'Ժϵ����', name : 'xymc', index : 'xymc', width : '10%' },
							{ label : 'רҵ����', name : 'zymc', index : 'zymc', hidden : true },
							{ label : '�༶����', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '����', name : 'xm', index : 'xm', width : '5%' },
							{ label : '���֤��', name : 'sfzh', index : 'sfzh', width : '13%'},
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '8%',formatter:bjLink },
							{ label : '�ܷ�', name : 'zf', index : 'zf', width : '4%' },
							{ label : '״̬', name : 'zt', index : 'zt', width : '8%' }
						  ],
						  sortname: "nd,xymc,zymc,bjmc",
					 	  sortorder: "asc"
			};
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
		<html:form action="/xszz_knsdcwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="importXf();return false;" class="btn_dr" >����</a>
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
				<span>������������ά���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xsdkqf/js/xsdkqf.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "ѧ������Ƿ���б�",
				pager : "pager",
				url : "zxdk_xsdkqf.do?method=getXsdkqfList&type=query",
				colList : [
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%' },
							{ label : '����', name : 'xm', index : 'xm', width : '10%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xydm', width : '15%' },
							{ label : '��������', name : 'dkxm', index : 'dkxm', width : '25%' },
							{ label : '�Ƿ����', name : 'sfjq', index : 'sfjq', width : '10%' }],
					sortname : "xh",
				    sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		
		function importSydk(){
				toImportDataNew("ZXDK_SFJQDK");
				return false;
			}
		function fsznx(){
			var url = "xsxx_xsxxgl.do?method=ycsjTs";
			showConfirmDivLayer("����ͬ���쳣����", {
				"okFun" : function() {
					jQuery.post(url, {
					}, function(data) {
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			});
		}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		
		<html:form action="/zxdk_xsdkqf">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			
				<logic:notEqual value="stu" name="userType">
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_dr" onclick="importSydk();return false;">���������Ϣ</a></li>
							<logic:equal value="xx" name="usersf">
								<li><a href="#" class="btn_dr" onclick="fsznx();return false;">�����ʼ�</a></li>
							</logic:equal>
						</ul>
					</div>
				</logic:notEqual>
				
				<!--<logic:equal value="zf01" name="userName">
				 ��ť 
				<div class="buttonbox">
					<ul>										
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >����</a></li>						
					</ul>
				</div>
				</logic:equal>-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ��Ƿ�Ѳ�ѯ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
	</body>
</html>

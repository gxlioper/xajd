<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gzwpcmdjgl/js/gzwpcmdj.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		
		<script type="text/javascript">
			var gridSetting = {
			caption : "������Ʒ���ŵǼ��б�",
			pager : "pager",
			url : "gygl_gzwpcmdj.do?method=gzwpcmdjManage&type=query",
			colList : [
					{ name : 'gzwpdjid', index : 'gzwpdjid', hidden : true, key : true },
					{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
					{ label : '����', name : 'xm', index : 'xm', width : '8%' },
					{ label : '¥������', name : 'ldmc', index : 'ldmc', width : '15%' },
					{ label : '���Һ�', name : 'qsh', index : 'qsh', width : '7%' },
					{ label : '��Ʒ����', name : 'wpmc', index : 'wpmc', width : '10%' },
					{ label : '����ʱ��', name : 'cmsj', index : 'cmsj', width : '8%' }, 
					{ label : 'ֵ����Ա', name : 'zbry', index : 'zbry', width : '8%' }, 
					{ label : '��ע', name : 'bz', index : 'bz', width : '12%' }],
			sortname : "gzwpdjid", sortorder : "asc" };


			jQuery(function() {
				
				//gridSetting["params"] = getSuperSearch();
				
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
		<html:form action="/gygl_gzwpcmdj">
			
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addUpdateGzwp('add');return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="addUpdateGzwp('update');return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteGzwp();return false;" class="btn_sc"  >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>	
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ʒ���ŵǼ��б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

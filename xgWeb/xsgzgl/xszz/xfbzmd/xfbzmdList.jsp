<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="xsgzgl/xszz/xfbzmd/js/xfbzmd.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- �߼���ѯʱ����Ҫ -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ�Ѳ�����ѯ�б�",
				pager:"pager",
				url:"xszz_zzxmjg_xfbz.do?method=xfbzmdList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
				   {label:'ѧ��',name:'xz', index: 'xz',width:'5%'},
				   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'8%'},
				   {label:'����רҵȷ��ǰѧԺ',name:'zxzyqrqxy', index: 'zxzyqrqxy',width:'8%'},
				   {label:'��ע',name:'bz', index: 'bz',width:'10%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			
				<logic:equal value="zf01" name="userName">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</ul>
					</div>
				</logic:equal>
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ�Ѳ���������ѯ�б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				var gridSetting = {
						caption:"ѧ�ڿ����б�",
						pager:"pager",
						params:getSuperSearch(),
						url:"rcsw_rcxwwh_rcxwxqkhgl.do?method=rcxwxqkhManage&type=query",
						colList:[
						   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
						   {label:'ѧ��',name:'yfxn', index: 'yfxn',width:'10%'},
						   {label:'ѧ��',name:'yfxqmc', index: 'yfxqmc',width:'5%'},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
						   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'24%'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'25%'},
						   {label:'ѧ�ڵȼ�',name:'xqdjmc', index: 'xqdjmc',width:'9%'},
						   {label:'yfxq',name:'yfxq', index: 'yfxq' ,hidden:true}
						],
						sortname: "yfxn,yfxqmc,xh",
					 	sortorder: "asc"
					}
				jQuery("#dataTable").initGrid(gridSetting);
	
			});
			function searchRs() {
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xnLength == 0){
					showAlertDivLayer("��ѡ������һ��ѧ�꣡");
					return false;
				}
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function zxsxxView(xh){
				showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
			}

			var DCCLBH = "rcsw_rcxwwh_rcxwxqkh.do";// dcclbh,�������ܱ��
	
			// �Զ��嵼�� ����
			function exportConfig() {
				// DCCLBH�������ܱ��,ִ�е�������
				customExport(DCCLBH, rcxwxqkhExportData);
			}
	
			// ��������
			function rcxwxqkhExportData() {
				setSearchTj();// ���ø߼���ѯ����
				var url = "rcsw_rcxwwh_rcxwxqkhgl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
				url = addSuperSearchParams(url);// ���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
	
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwxqkhgl">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>				
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
				<span>ѧ�ڿ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

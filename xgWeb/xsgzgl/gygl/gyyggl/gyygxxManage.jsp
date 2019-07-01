<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ԢԱ���б�",
				pager:"pager",
				url:"gygl_gyygxxgl.do?type=query",
				colList:[
						   {label:'Ա�����',name:'ygbh', index: 'ygbh',width:'10%',key:true },
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'����',name:'nl', index: 'nl',width:'5%'},
						   {label:'�Ա�',name:'xbmc', index: 'xbmc',width:'5%'},
						   {label:'zwdm',name:'zwdm', index: 'zwdm',hidden:true},
						   {label:'Ƹ������',name:'pyrq', index: 'pyrq',width:'10%'},
						   {label:'���֤��',name:'sfzh', index: 'sfzh',width:'18%'},
						   {label:'�ָ�',name:'zwmc', index: 'zwmc',width:'10%'},
						   {label:'�Ƿ��ڸ�',name:'zgztmc', index: 'zgzt',width:'2%'},
						   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'15%'}
						],
				sortname: "ygbh",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function zjyg(){
				showDialog('������Ƹ��Ա',600,400,'gyglnew_gyygxxgl.do?method=zjYg');
				}

			function xgyg(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸���Ƹ��Ա',600,400,'gyglnew_gyygxxgl.do?method=xgYg&ygbh='+rows[0]["ygbh"]);
				}
			}

			function ckyg(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				} else {
					showDialog('�鿴��Ƹ��Ա',600,400,'gyglnew_gyygxxgl.do?method=ckYg&ygbh='+rows[0]["ygbh"]);
				}
				}

			function scyg(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				
				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("gyglnew_gyygxxgl.do?method=scYg",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
					}});
				}
			}

			function exportConfig() {
				customExport("gyglnew_gyygxxgl.do", sjkwhExportData,700,480);
		}
				
				
		// ��������
		function sjkwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_gyygxxgl.do?method=sjkwhExportData&dcclbh=" + "gyglnew_gyygxxgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
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
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="zjyg();return false;" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="xgyg();return false;" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="scyg();" class="btn_sc">ɾ��</a></li>
						
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="ckyg();return false;" class="btn_ck">�鿴</a></li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>	
		
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ԢԱ���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>

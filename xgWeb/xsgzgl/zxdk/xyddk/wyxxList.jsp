<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"��ѧ����ΥԼ�б� ",
					pager:"pager",
					url:"zxdkWygl.do?method=getWyxxList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckDkjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                        {label:'��Ժ',name:'symc', index: 'sydm',width:'13%'},
                        {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybj',width:'13%'},
					   {label:'��ͬ���',name:'htbh', index: 'htbh'},
					   {label:'ΥԼ����',name:'wyxq',index:'wyxq'},
					   {label:'��ע',name:'bz',index:'bz'}
					]
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function importWyxx(){
				toImportData("ZXDK_XYDDK_WYXX");
				return false;
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			//����
			function exportConfig(){
				var DCCLBH='zxdk_wyxx.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='zxdk_wyxx.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "zxdkWygl.do?method=dcwy&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function ckDkjg(id){
				showDialog("�鿴",800,520,"zxdkDkjg.do?method=ckDkjg&id="+id);
			}
			
			function editWyxq(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					showDialog("ΥԼ����",500,240,"zxdkWygl.do?method=wybz&htbh="+rows[0]["htbh"]);
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkXyddk" method="post" styleId="wyxxForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_dr" onclick="importWyxx();return false;">����ΥԼ����</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
							<li><a href="#" class="btn_xg" onclick="editWyxq();return false;">ΥԼ����</a></li>
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
				<span>��ѧ����ΥԼ�б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

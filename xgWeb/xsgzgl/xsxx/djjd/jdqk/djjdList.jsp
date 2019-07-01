<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"jddj_jdqk.do?method=getJdqkList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',formatter:function(v,r){
					   return "<a class='name' href='javascript:cksq(\""+r["id"]+"\")'>"+v+"</a>";
				   }},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'ѧԺ',name:'xymc', index: 'xydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'��Ŀ',name:'xmmc', index: 'xmmc'},
				   {label:'����',name:'jbmc', index: 'jbmc'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
				   {label:'֤����',name:'zsbh', index: 'zsbh'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog('�鿴',600,330,'jddj_jdqk.do?method=jdqkView&id='+id);
			}

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					
					if (rows[0]["sjly"] == "1"){
						alertInfo("�������ݲ����޸ģ�");
						return false;
					}
					showDialog('�޸�',700,380,'jddj_jdqk.do?method=jdqkEdit&id='+rows[0]["id"]);
				}
			}

			function djjdDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("�������ݲ���ɾ����");
							return false;
						}
					}
					
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("jddj_jdqk.do?method=jdqkDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDjjd(){
				showDialog('���ӵȼ��������',700,380,'jddj_jdqk.do?method=jdqkAdd');;
			}
			
			function importJdqk(){
				toImportData("xsxx_ntgm_djjd");
				return false;
			}
			
			//����
			function exportConfig(){
				var DCCLBH='ntgm_jjdj_jdqk.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='ntgm_jjdj_jdqk.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "jddj_jdqk.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
		<html:form action="/jddj_jdqk" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="addDjjd()" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="djjdDel();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">����</a></li>						
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>						
				</ul>
			</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �ȼ���������б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>

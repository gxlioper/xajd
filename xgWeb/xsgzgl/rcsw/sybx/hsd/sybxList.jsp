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
				url:"hsd_sybx.do?method=getSybxList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh'},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'ѧԺ',name:'xymc', index: 'xydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'������',name:'bdh', index: 'bdh'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸�',700,450,'hsd_sybx.do?method=sybxEdit&id='+rows[0]["id"]);
				}
			}

			function djjdDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("hsd_sybx.do?method=sybxDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDjjd(){
				showDialog('������ҵ����',700,450,'hsd_sybx.do?method=sybxAdd');;
			}
			
			function importJdqk(){
				toImportData("rcsw_hsd_sybx");
				return false;
			}
			
			//����
			function exportConfig(){
				var DCCLBH='rcsw_hsd_sybx.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='rcsw_hsd_sybx.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "hsd_sybx.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
		<html:form action="/hsd_sybx" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
					<li><a href="javascript:void(0);" onclick="addDjjd()" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="djjdDel();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">����</a></li>
					</logic:equal>						
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
				<span>��ҵ����ά���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>

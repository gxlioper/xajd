<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"dkbc_bcjg.do?method=getBcjgList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:ckjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'ѧԺ',name:'xymc', index: 'xydm'},
                    {label:'��Ժ',name:'symc', index: 'sydm'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybj'},
				   {label:'�������',name:'dclbmc', index: 'dclbmc'},
				   {label:'�������',name:'dcje', index: 'dcje'},
//				   {label:'ѧ�ѽ��',name:'xfje', index: 'xfje'},
//				   {label:'�����',name:'dkbj', index: 'dkbj'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
				   {label:'����ʱ��',name:'sqsj',index:'sqsj',width:'13%'}
				],
				sortname: "sqsj",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function ckjg(id){
				showDialog('�鿴',700,500,'dkbc_bcjg.do?method=bcjgView&id='+id);
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
					
					showDialog('�޸�',700,500,'dkbc_bcjg.do?method=bcjgEdit&id='+rows[0]["id"]);
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
						jQuery.post("dkbc_bcjg.do?method=bcjgDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDjjd(){
				showDialog('���Ӵ����',700,500,'dkbc_bcjg.do?method=bcjgAdd');;
			}
			
			function importJdqk(){
				toImportDataNew("IMPORT_ZXDK_BYDC");
				return false;
			}
			
			//����
			function exportConfig(){
				var DCCLBH='zxdk_dkbc.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='zxdk_dkbc.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "dkbc_bcjg.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function importBfje(){
				toImportDataNew("IMPORT_ZXDK_BYDC_BFQX");
				return false;
			}
			
			function printSqb(){
				var url = "dkbc_bcjg.do?method=printSqb";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&ids=" +guid;
					window.open(url);
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
		<html:form action="/jddj_jdqk" method="post">
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
					<%--<li><a href="javascript:void(0);" onclick="importBfje();" class="btn_dr">���벦���������</a></li>--%>
					</logic:equal>
					
					<logic:equal value="10335" name="xxdm">
						<logic:equal value="zf01" name="userName">						
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>	
						</logic:equal>
					</logic:equal>
					<logic:notEqual value="10335" name="xxdm">
								<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>
					</logic:notEqual>
					
					<li><a href="javascript:void(0);" onclick="printSqb();" class="btn_dy">��ӡ�����</a></li>						
				</ul>
			</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ������б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>

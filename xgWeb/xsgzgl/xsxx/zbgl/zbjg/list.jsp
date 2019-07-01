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
					pager:"pager",
					url:"zbglZbjg.do?method=getZbjgList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'�༶',name:'bjmc', index: 'bjdm'},
					   {label:'ѧ��',name:'xn', index: 'xn'},
					   {label:'����ʱ��',name:'sqsj',index:'sqsj'},
					   {label:'sjly',name:'sjly',index:'sjly',hidden:true}
					],
					sortname:"sqsj",
					sortorder:"desc"
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog("�鿴",700,460,"zbglZbjg.do?method=view&id="+id);
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					
					if (rows[0]["sjly"] == "1"){
						showAlertDivLayer("�������ݲ����޸ģ�");
						return false;
					}
					showDialog('�޸�',700,500,'zbglZbjg.do?method=edit&id='+rows[0]["id"]);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("�������ݲ���ɾ����");
							return false;
						}
					}
					
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("zbglZbjg.do?method=delete",{ids:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('����',700,500,'zbglZbjg.do?method=add');;
			}
			
			function importZbjg(){
				toImportData("xsxx_zbgl");
				return false;
			}

			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="zbglZbjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="zbglZbjg.do?method=getDjbWord&id="+rows[0]["id"];
					window.open(url);
			     }
			}
			
			//����
			function exportConfig(){
				var DCCLBH='xsxx_zbgl.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='xsxx_zbgl.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "zbglZbjg.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
	
		<html:form action="/zxdkSyddk" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" class="btn_zj"
								   onclick="add();return false;" 
								>
								����
								</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
								>�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
								>ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="importZbjg();return false;" class="btn_dr"
								>����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc"
								>����</a>
							</li>
							<logic:equal name="xxdm" value="14073">	
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>	
							</logic:equal>
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
				<span>��������б�  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

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
				url:"ybgzzCywh.do?method=getList",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
					   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
				   }},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'�����������',name:'sqjrsj',index:'sqjrsj'},
				   {label:'����ʱ��',name:'sqsj',index:'sqsj'},
				   {label:'�Ƿ��˳�',name:'sftc',index:'sftc',formatter:function(c,r){
					   return c=="��" ? "<a href=\"javascript:cktc('"+r["id"]+"');\" class='name'>��</a>" : "��";
				   }},
				   {label:'sjly',name:'sjly',index:'sjly',hidden:true}
				],
				sortname:"sqsj",
				sortorder:"desc"
			};

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog('�鿴',600,330,'ybgzzCywh.do?method=view&id='+id);
			}
			
			function cktc(id){
				showDialog('�鿴',400,150,'ybgzzCywh.do?method=viewExit&id='+id);
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
					showDialog('�޸�',700,380,'ybgzzCywh.do?method=edit&id='+rows[0]["id"]);
				}
			}

			function del(){
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
						jQuery.post("ybgzzCywh.do?method=del",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('�����װ๤��վ��Ա',700,380,'ybgzzCywh.do?method=add');;
			}
			
			function importJdqk(){
				toImportData("ybgzz_jgwh");
				return false;
			}
			
			//����
			function exportConfig(){
				var DCCLBH='ybgzz_cywh.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='ybgzz_cywh.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "ybgzzCywh.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function tcgzz(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("��ѡ����Ҫ�����ļ�¼��");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sftc'] == '��') {
							showAlertDivLayer("���˳���Ա�����ظ��˳���");
							return false;
						}
					}
					
					showDialog('�˳��װ๤��վ',500,220,'ybgzzCywh.do?method=exit&ids='+ids.toString());
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
	
		<html:form action="/zxdkSyddk" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
<%--							<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">����</a></li>						--%>
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>						
							<li><a href="javascript:void(0);" onclick="tcgzz();" class="btn_gb_shut">�˳�����վ</a></li>						
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
				<span>�װ๤��վ��Ա�б�  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>

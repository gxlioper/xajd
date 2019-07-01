<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"xpj_xmwh_tjsz.do?method=getbfhList&type=query&xmdm="+jQuery("#xmdm").val(),
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'sqid',name:'sqid', index: 'sqid',width:'10%',hidden:true},
				   {label:'splc',name:'splc', index: 'splc',width:'10%',hidden:true},
				   {label:'gwid',name:'gwid', index: 'gwid',width:'10%',hidden:true}
						],
				sortname: "xh",
			 	sortorder: "asc",
			}
			var map = getSuperSearch();
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
			
		});
		
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		function tuihui(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫ�˻صļ�¼��");
				return false;
			}
			showConfirmDivLayer("��ȷ��Ҫ�˻�ѡ��ļ�¼��",{"okFun":function(){
				savePlsh();
			}});
		}
		
		function savePlsh(){
			var rows = jQuery("#dataTable").getSeletRow();
			var guid = new Array();
			var gwids = new Array();
			var xhs = new Array();
			var splcs =new Array();
			
			jQuery.each(rows,function(i,row){
				guid.push(row["sqid"]);
				gwids.push(row["gwid"]);
				xhs.push(row["xh"]);
				splcs.push(row["splc"]);
			});
			
			var map = getSuperSearch();
			map["shzt"]="3";
			map["id"]=guid;
			map["gwids"]=gwids;
			map["xhs"]=xhs;
			map["splcs"]=splcs;
			map["shyj"]="�����������˻�";

			jQuery.post(
				"xpj_sqsh.do?method=savePlsh",
				map,function(data){
					
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				},
				'json'
			);

		}
		</script>
	</head>

	<body>
		<html:form action="/xpj_sqsh">
		<input type="hidden" id="xmdm" value="${xmdm}"/>
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��<font color="red">${xmmc}</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>	
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="tuihui()" class="btn_qxsh">�˻�</a></li>
					</ul>
				</div>
			
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ���
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>

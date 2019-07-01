<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"ѧ���б�",
					pager:"pager",
					url:"ttgl_stgl.do?method=searchForStu",
					colList:[
					   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',key:true},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
					   {label:'��Ժ',name:'symc', index: 'symc',width:'10%'},
					   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
					   {label:'רҵ',name:'zymc', index: 'zymc',width:'10%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
					   {label:'sjhm',name:'sjhm', index: 'sjhm',hidden:true}
					],
					sortname: "xh",
				 	sortorder: "desc"
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function save() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("������ѡ��һ��ѧ����");
					return false;
				}
				var html = "";
				var W;
				var api = frameElement.api;
				if (api) {
					if (api.get('childDialog')) {
						W = api.get('parentDialog')
					} else {
						W = api.opener;
					}
				} else if (parent.window) {
					W = parent.window;
				}
				for(var i = 0; i < rows.length; i++){
					var xh = rows[i]["xh"] == null ? "" : rows[i]["xh"];
					var xm = rows[i]["xm"] == null ? "" : rows[i]["xm"];
					var bjmc = rows[i]["bjmc"] == null ? "" : rows[i]["bjmc"];
					var sjhm = rows[i]["sjhm"] == null ? "" : rows[i]["sjhm"];
					var symc = rows[i]["symc"] == null ? "" : rows[i]["symc"];
					var xymc = rows[i]["xymc"] == null ? "" : rows[i]["xymc"];
					var zymc = rows[i]["zymc"] == null ? "" : rows[i]["zymc"];
						html += "<tr name='deltr'>";
						html += "<td style='text-align:center'><input type='checkbox' name='chk'></td>"
                    if(${appandTableId eq "tablebody"}){
                        html += "<td style='text-align:center'><input name='xh' type='hidden' value='"+xh+"' style='width:90%'/><label name = 'xhname'>"+xh+"</label></td>";
                    } else {
                        html += "<td style='text-align:center'><input name='tzsxh' type='hidden' value='"+xh+"' style='width:90%'/><label name = 'xhname'>"+xh+"</label></td>";
                    }
						html += "<td style='text-align:center'><label name = 'xm'>"+xm+"</label></td>";
						html += "<td style='text-align:center'><label name = 'symc'>"+symc+"</label></td>";
						html += "<td style='text-align:center'><label name = 'xymc'>"+xymc+"</label></td>";
						html += "<td style='text-align:center'><label name = 'zymc'>"+zymc+"</label></td>";
						html += "<td style='text-align:center'><label name = 'bjmc'>"+bjmc+"</label></td>";
                    if(${appandTableId eq "tablebody"}){
                        html += "<td style='text-align:center'><label name = 'fz'>������</label></td>";
					} else {
                        html += "<td style='text-align:center'><label name = 'fz'>��֧��</label></td>";
					}
						html += "<td style='text-align:center'><label name = 'sjhm'>"+sjhm+"</label></td>";
						html += "</tr>";
				}
				debugger
				jQuery("#${appandTableId}", W.document).append(html);
				iFClose();
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/ttgl_stgl" onsubmit="return false;">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="save();return false;" class="btn_zj">���</a>
						</li>
					</ul>
				</div>
					<!-- �������� -->	
						<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
			<h3 class="datetitle_01">
				<span>ѧ���б�</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>

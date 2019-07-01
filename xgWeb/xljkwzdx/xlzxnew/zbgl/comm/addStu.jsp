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
					url:"xlzxnew_zbsb.do?method=searchForStu&xhs="+jQuery("#xhs").val()+"&bjdm="+jQuery("#bjdm").val(),
					colList:[
					   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',key:true},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
					   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'}
					],
					sortname: "xh",
				 	sortorder: "desc"
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = {};
				map['xm'] = jQuery("#xm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			//�س��¼�
			function keydown_search(keyEvent){
				  if(keyEvent.keyCode == 13){
					  searchRs();
				  }else{
					  return false;
				  }
			}
			function save() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("������ѡ��һ��ѧ����");
					return false;
				}
				var html = "";
				for(var i = 0; i < rows.length; i++){
					var xh = rows[i]["xh"] == null ? "" : rows[i]["xh"];
					var xm = rows[i]["xm"] == null ? "" : rows[i]["xm"];
					var xb = rows[i]["xb"] == null ? "" : rows[i]["xb"];

					html += "<tr name='deltr'>";
					html += "<td style='text-align:center'><input type='checkbox' name='chk'></td>"
					html += "<td style='text-align:center'><input name='xh' type='hidden' value='"+xh+"' style='width:90%'/><label name = 'xhname'>"+xh+"</label></td>";
					html += "<td style='text-align:center'><label name = 'xm'>"+xm+"</label></td>";
					html += "<td style='text-align:center'><label name = 'xb'>"+xb+"</label></td>";
					html += "<td style='text-align:center'><textarea name = 'zbwtms' style='width:90%' rows='2'></textarea></td>";
					html += "</tr>";
				}
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
				jQuery("#tablebody", W.document).append(html);
				iFClose();
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/xlzxnew_zbsb" onsubmit="return false;">
		<html:hidden property="xhs" styleId="xhs" value="${xhs}"/>
		<html:hidden property="bjdm" styleId="bjdm" value="${bjdm}"/>
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
					<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">ѧ��/����</th>
						<td>
							<input type="text" id="xm" name="xm"  onkeydown="keydown_search(event)" />
						</td>
						<td>
							<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false"">
										�� ѯ
									</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"�����б�",
						pager:"pager",
						url:"ttgl_strtsq.do?method=stList&type=query",
						colList:[
						   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'��������',name:'stqc', index: 'stqc',width:'8%',formatter:stLink},
						   {label:'ҵ��ָ����λ',name:'bmmc', index: 'bmmc',width:'15%'},
						   {label:'����ָ����ʦ',name:'stzdlsxm', index: 'stzdlsxm',width:'8%'},
						   {label:'��������',name:'strs', index: 'strs',width:'15%'},
						   {label:'sfcz',name:'sfcz', index: 'sfcz',hidden:true},//�Ƿ�����ڳ�Ա��
						   {label:'��ǰ״̬',name:'zt', index: 'zt',width:'15%'},//״̬
						   {label:'����',name:'', index: '',width:'15%',formatter:czLink}
						],
						sortname: "stqc",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

function query(){
	var map = {};
	map["stqc"] = jQuery("#stqc").val();
	jQuery("#dataTable").reloadGrid(map);
}
function sq(jgid) {
	var url = "ttgl_strtsq.do?method=stsq&jgid="+jgid;
	showDialog("��������",790,530,url);
}	
function cx(jgid) {
	showConfirmDivLayer("ȷ��Ҫ��������������", {
			"okFun" : function() {
				var url = "ttgl_strtsq.do?method=cancelSq";
				jQuery.post(url, {jgid:jgid}, function(data) {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
						}, 'json');
					}
				});
}	

function czLink(cellValue,rowObject){
	var sfcz = rowObject.sfcz;
	var jgid = rowObject.jgid;
	var zt = rowObject.zt;
	if(sfcz==0){
		return "<button type='button' onclick='sq(\""+jgid+"\");'>��������</button>";
	}else{
		if(zt=="������" ||zt=="��Ա"||zt=="�Ѿܾ�"){
			return "<label>��</label>";
		}else{
			return "<button type='button' onclick='cx(\""+jgid+"\");'>��������</button>";
		}
	}
}

function stLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["jgid"] + "\");'>" + cellValue
			+ "</a>";
}
function View(jgid) {
	showDialog("�鿴������Ϣ", 790,476, "ttgl_strtsq.do?method=view&jgid=" + jgid);
}
</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
		<html:form action="/ttgl_strtsq" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								��������
							</th>
							<td>
								<input type="text" id="stqc" onkeypress="if(event.keyCode==13){query();return false;}" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�����б�</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

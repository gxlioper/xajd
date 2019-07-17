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
						caption:"社团列表",
						pager:"pager",
						url:"ttgl_strtsq.do?method=stList&type=query",
						colList:[
						   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'社团名称',name:'stqc', index: 'stqc',width:'8%',formatter:stLink},
						   {label:'业务指导单位',name:'bmmc', index: 'bmmc',width:'15%'},
						   {label:'社团指导老师',name:'stzdlsxm', index: 'stzdlsxm',width:'8%'},
						   {label:'社团人数',name:'strs', index: 'strs',width:'15%'},
						   {label:'sfcz',name:'sfcz', index: 'sfcz',hidden:true},//是否存在于成员表
						   {label:'当前状态',name:'zt', index: 'zt',width:'15%'},//状态
						   {label:'操作',name:'', index: '',width:'15%',formatter:czLink}
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
	showDialog("入团申请",790,530,url);
}	
function cx(jgid) {
	showConfirmDivLayer("确定要撤销入团申请吗？", {
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
		return "<button type='button' onclick='sq(\""+jgid+"\");'>申请入团</button>";
	}else{
		if(zt.indexOf("负责人")>=0 ||zt=="成员"||zt=="已拒绝"){
			return "<label>无</label>";
		}else{
			return "<button type='button' onclick='cx(\""+jgid+"\");'>撤销申请</button>";
		}
	}
}

function stLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["jgid"] + "\");'>" + cellValue
			+ "</a>";
}
function View(jgid) {
	showDialog("查看社团信息", 790,476, "ttgl_strtsq.do?method=view&jgid=" + jgid);
}
</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
		<html:form action="/ttgl_strtsq" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								社团名称
							</th>
							<td>
								<input type="text" id="stqc" onkeypress="if(event.keyCode==13){query();return false;}" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										查 询
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
				<span>社团列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

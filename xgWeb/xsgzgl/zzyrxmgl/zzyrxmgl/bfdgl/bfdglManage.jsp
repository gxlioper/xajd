<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"信息列表",
						pager:"pager",
						url:"zzyrxmglbfdgl.do?method=bfdglManage&type=query",
						colList:[
							{label:'key',name:'fdfbid', index: 'fdfbid',key:true ,hidden:true},
							{label:'fdxxid',name:'fdxxid', index: 'fdxxid',hidden:true},
						 	{label:'学员学号',name:'bfdrxh', index: 'bfdrxh',width:'10%'},
							{label:'学员姓名',name:'bfdrxm', index: 'bfdrxm',width:'10%'},
							{label:'学员所在学院',name:'bfdrxymc', index: 'bfdrxymc',width:'14%'},
							{label:'助教学号',name:'fdrxh', index: 'fdrxh',width:'10%'},
							{label:'助教姓名',name:'fdrxm', index: 'fdrxm',width:'10%'},
							{label:'助教所在学院',name:'fdrxymc', index: 'fdrxymc',width:'14%'},
						 	{label:'辅导项目',name:'fdkm', index: 'fdkm',width:'8%'},
						 	{label:'辅导时间',name:'fdsj', index: 'fdsj',width:'15%'},
						 	{label:'备注',name:'fbbz', index: 'fbbz',width:'12%',formatter:bzLink},
						 	{label:'审核状态',name:'shzt', index: 'shzt',width:'8%',formatter:shLink}
						],
						params:{fdkm:fdkm},
						sortname: "fdrxh",
					 	sortorder: "asc"
				};
				var fdkm = jQuery("#fdkm").val();
				var userType = "${userType }";
				if(!"stu" == userType){
					gridSetting["params"]=getSuperSearch();					
				}
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function query(){
				var map = {};
				map["fdkm"] = jQuery("#fdkm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			function bzLink(c,r){
				var fbbz = r.fbbz;
				if(fbbz != "" && fbbz != null && fbbz.length > 8){
					return "<a href='javascript:void(0);' title='"+fbbz+"'>"+fbbz.substr(0,8)+"...</a>";
				}
				return fbbz;
			}
			function shLink(c,r){
				var shzt = r.shzt;
				if(shzt=="1"){
					return "同意";
				}else if(shzt=="0"){
					return "不同意";
				}else{
					return "审核中";
				}
				return fbbz;
			}
			function add(){
<%--				var sqkg = jQuery("#sqkg").val();--%>
<%--				if(sqkg == '0' || sqkg == 0){--%>
<%--					showAlertDivLayer("发布申请已关闭，请于每月的前五天发布！");--%>
<%--					return false;--%>
<%--				}--%>
				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				var url = "zzyrxmglbfdgl.do?method=addBfdgl";
				var title = "增加";
				showDialog(title,750,465,url);
			}
			function mod() {
				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var fdrxh = rows[0]["fdrxh"];
					if(fdrxh != "" && fdrxh != null){
						showAlertDivLayer("已审核的记录不允许进行修改！");
						return false;
					}
					var url = 'zzyrxmglbfdgl.do?method=updateBfdgl&fdfbid=' + rows[0]["fdfbid"];
					var title = "修改";
					showDialog(title,750,465,url);
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("zzyrxmglbfdgl.do?method=delBfdgl", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			function addPj(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_pj").val());
				} else {
					var shzt = rows[0]["shzt"];
					if(shzt == "0"){
						showAlertDivLayer("未同意辅导不能评价！");
						return false;
					}
					var fdxxid = rows[0]["fdxxid"];
					if(fdxxid == "" || fdxxid == null){
						showAlertDivLayer("暂时不能评价！");
						return false;
					}
					var url = "zzyrxmglbfdgl.do?method=checkCanpj";
					jQuery.post(url, {
						fdxxid : fdxxid,fdlx : '1'
					}, function(data) {
						if (data["message"] == "true") {
							var ul = 'zzyrxmglbfdgl.do?method=addBfdglpj&fdxxid=' + fdxxid +'&fdlx=1';
							var title = "评价";
							showDialog(title,450,155,ul);
						} else {
							showAlertDivLayer("辅导记录填写2次及2次以上方可评价！");
						}
					}, 'json');
				}
			}
			
			function addJl(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_tx").val());
				} else {
					var shzt = rows[0]["shzt"];
					if(shzt != "1"){
						showAlertDivLayer("未同意辅导不能填写记录！");
						return false;
					}
					var fdxxid = rows[0]["fdxxid"];
					if(fdxxid == "" || fdxxid == null){
						showAlertDivLayer("暂时不能填写记录！");
						return false;
					}
					var url = 'zzyrxmglbfdgl.do?method=addBfdgljl&fdxxid=' + fdxxid +'&fdlx=1';
					var title = "填写记录";
					showDialog(title,750,465,url);
				}
			}
			function view() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_ck").val());
				} else {
					var url = 'zzyrxmglbfdgl.do?method=viewBfdgl&fdfbid=' + rows[0]["fdfbid"];
					var title = "查看";
					showDialog(title,750,465,url);
				}
			}
			
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>提示：</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!--<input type="hidden" id="sqkg" value="${sfsq}" />-->
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<html:form action="/zzyrxmglbfdgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">发布</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="mod();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="addJl();return false;" class="btn_zj">填写记录</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="addPj();return false;" class="btn_zj">评价</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">查看</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<!-- 学生用户 -->
			<logic:equal name="userType" value="stu">
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">辅导项目</th>
						<td width="20%">
							<input type="text" id="fdkm" name="fdkm" maxlength="20" onkeypress="if(pressEnter(event)){query();return false;}"/>
						</td>
						<td>
							<div class="btn" style="float: left;">
								<button type="button" class="btn_cx" id="search_go" onclick="query();" >
									查 询
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
			</logic:equal>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"我的发布信息列表",
						pager:"pager",
						url:"zzyrxmglfdgl.do?method=fdglManage&type=query&fblx=0",
						colList:[
							{label:'key',name:'fdfbid', index: 'fdfbid',key:true ,hidden:true},
						 	{label:'助教学号',name:'fbrxh', index: 'fbrxh',width:'12%'},
							{label:'助教姓名',name:'fbrxm', index: 'fbrxm',width:'8%'},
							{label:'助教所在学院',name:'fbrxymc', index: 'fbrxymc',width:'14%'},
						 	{label:'优势学科及方向',name:'fdkm', index: 'fdkm',width:'10%'},
						 	{label:'辅导时间',name:'fdsj', index: 'fdsj',width:'19%'},
						 	{label:'报名人数/限定人数/通过人数',name:'xdrs', index: 'xdrs',width:'10%',formatter:czLink}
						],
						params:{fdkm:fdkm},
						sortname: "fbrxh",
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
				var url = "zzyrxmglfdgl.do?method=addFdgl";
				var title = "发布";
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
					var xdrs = rows[0]["xdrs"].substr(0,1);
					if(xdrs != "0"){
						showAlertDivLayer("已有人申请辅导该记录，无法进行修改！");
						return false;
					}
					var url = 'zzyrxmglfdgl.do?method=updateFdgl&fdfbid=' + rows[0]["fdfbid"];
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
						jQuery.post("zzyrxmglfdgl.do?method=delFdgl", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			function czLink(c,r){
				var xdrs = r.xdrs;
				var fdfbid = r.fdfbid;
				return "<a href='javascript:void(0);' style='color: blue;text-decoration: underline;' onclick='viewBmrs(\""+fdfbid+"\");'>"+xdrs+"</a>"
			}
			function viewBmrs(fdfbid){
				var url = 'zzyrxmglfdgl.do?method=viewBmrs&fdfbid=' + fdfbid;
				var title = "查看";
				showDialog(title,750,465,url);
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
		<!--<input type="hidden" id="sqkg" value="${sfsq}"/>-->
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<html:form action="/zzyrxmglfdgl?method=fdglManage&type=query&fblx=0">
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
						</logic:equal>
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
								<button type="button" class="btn_cx" id="search_go" onclick="query();">
									查 询
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
			</logic:equal>
		</html:form>
		<div class="compTab" id="card">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<li class="ha">
								<a href="javascript:void(0)"
									onclick="refreshForm('zzyrxmglfdgl.do?method=fdglManage&fblx=0')">
									<span>已发布</span> </a>
							</li>
							<li>
								<a href="javascript:void(0)"
									onclick="refreshForm('zzyrxmglfdgl.do?method=fdglManage&fblx=1')">
									<span>待辅导</span> </a>
							</li>
						</ul>
					</div>
			</div>
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

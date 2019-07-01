<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"公共辅导信息列表",
						pager:"pager",
						url:"zzyrxmglxmgl.do?method=xmglManage&type=query&fblx=0",
						colList:[
							{label:'key',name:'fdfbid', index: 'fdfbid',key:true ,hidden:true},
						 	{label:'助教学号',name:'fbrxh', index: 'fbrxh',width:'8%'},
							{label:'助教姓名',name:'fbrxm', index: 'fbrxm',width:'8%'},
							{label:'助教所在学院',name:'fbrxymc', index: 'fbrxymc',width:'14%'},
						 	{label:'擅长辅导项目',name:'fdkm', index: 'fdkm',width:'10%'},
						 	{label:'辅导时间',name:'fdsj', index: 'fdsj',width:'18%'},
						 	{label:'备注',name:'fbbz', index: 'fbbz',width:'12%',formatter:bzLink},
						 	{label:'sfsq',name:'sfsq', index: 'sfsq',hidden:true},
							{label:'操作',name:'', index: '',width:'12%',formatter:czLink}
						],
						params:{fdkm:fdkm}
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
			function view() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'zzyrxmglxmgl.do?method=viewXmgl&fdfbid=' + rows[0]["fdfbid"];
					var title = "查看";
					showDialog(title,750,465,url);
				}
			}
			function bzLink(c,r){
				var fbbz = r.fbbz;
				if(fbbz != "" && fbbz != null && fbbz.length > 8){
					return "<a href='javascript:void(0);' title='"+fbbz+"'>"+fbbz.substr(0,8)+"...</a>";
				}
				return fbbz;
			}

			function czLink(c,r){
				var sfsq = r.sfsq;
				var fdfbid = r.fdfbid;
				if(sfsq == "0"){
					return "<button type='button' onclick='sq(\""+fdfbid+"\");'>申请</button>";
				}else{
					return "<button type='button' onclick='qxsq(\""+fdfbid+"\");'>撤销申请</button>";
				}
			}

			function sq(fdfbid){				
				var url = "zzyrxmglxmgl.do?method=sqXmgl";
				jQuery.post(url, {
					fdfbid : fdfbid
				}, function(data) {
					if (data["message"] == "保存成功！") {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
					} else {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
					}
				}, 'json');
			}
			function qxsq(fdfbid){
				var url = "zzyrxmglxmgl.do?method=qxsqXmgl";
				jQuery.post(url, {
					fdfbid : fdfbid
				}, function(data) {
					if (data["message"] == "保存成功！") {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
					} else {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
					}
				}, 'json');
			}
			
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zzyrxmglxmgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
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
		<div class="compTab" id="card">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<li class="ha">
								<a href="javascript:void(0)"
									onclick="refreshForm('zzyrxmglxmgl.do?method=xmglManage&fblx=0')">
									<span>公共辅导</span> </a>
							</li>
							<li>
								<a href="javascript:void(0)"
									onclick="refreshForm('zzyrxmglxmgl.do?method=xmglManage&fblx=1')">
									<span>公共需求</span> </a>
							</li>
						</ul>
					</div>
			</div>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>公共辅导信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

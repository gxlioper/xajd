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
					caption:"团队列表",
					pager:"pager",
					url:"grttxm_jdsz.do?method=getTtcy&type=query&xhs="+jQuery("#xhs").val()+"&xmdm="+jQuery("#xmdm").val(),
					colList:[
					   {label:'团队名称',name:'tdmc', index: 'tdmc',width:'12%'},
					   {label:'队长学号',name:'dzxh', index: 'dzxh',width:'12%',key:true},
					   {label:'队长姓名',name:'xm', index: 'xm',width:'8%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'20%'},
					   {name:'ttjgid', index: 'ttjgid',width:'20%',hidden:true}
					   
					],
					sortname: "tdmc",
				 	sortorder: "asc"
				}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function save() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("请至少选择一个团队！");
					return false;
				}
				//构建数组
				var jdcys = new Array();
				for(var i = 0; i < rows.length; i++){
					var jdcy = rows[i]["ttjgid"] == null ? "" : rows[i]["ttjgid"];
					jdcys.push(jdcy);
				}
				jQuery.post("grttxm_jdsz.do?method=saveJdszCy", {
					jdcys : jdcys,
					xmdm : jQuery("#xmdm").val(),
					jdid : jQuery("#jdid").val(),
					jbf : jQuery("#jdf").val()
				}, function(data) {
					if(data["message"] == '保存成功！'){
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
                        W.doQuery();
						iFClose();
					}else{
						showAlertDivLayer(data["message"]);
					}
					
					
				}, 'json');
				
				
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/jyglnew_jygl_cyyqglgl">
		<html:hidden property="xhs" styleId="xhs" value="${xhs}"/>
		<html:hidden property="xmdm" styleId="xmdm" value="${xmdm}"/>
		<html:hidden property="jdid" styleId="jdid" value="${jdid}"/>
		<html:hidden  property="jdf" styleId="jdf" value="${jdf}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="save();return false;" class="btn_zj">添加</a>
						</li>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
		</html:form>
		<div class="toolbox">
			<h3 class="datetitle_01">
				<span>团队列表</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>

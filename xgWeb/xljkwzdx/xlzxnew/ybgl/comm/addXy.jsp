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
					caption:"学生列表",
					pager:"pager",
					url:"xlzxnew_ybjg.do?method=searchForXy",
					colList:[
					   {label:'学院代码',name:'xydm', index: 'xydm',width:'50%',key:true},
					   {label:'学院名称',name:'xymc', index: 'xymc',width:'50%'},
					],
					radioselect:true
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = {};
				map['xymc'] = jQuery("#xymc").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			//回车事件
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
					showAlertDivLayer("请选择一个学院！");
					return false;
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
				jQuery("#xydm", W.document).val(rows[0]['xydm']);
				jQuery("#xymc", W.document).val(rows[0]['xymc']);
				iFClose();
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/xlzxnew_zbjg" onsubmit="return false;">
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
					<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">学院名称</th>
						<td>
							<input type="text" id="xymc" name="xymc"  onkeydown="keydown_search(event)" />
						</td>
						<td>
							<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false"">
										查 询
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
				<span>学生列表</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>

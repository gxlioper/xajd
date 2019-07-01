<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var jsonStr = jQuery("#jsonStr").val();
				var map = JSON.parse(jsonStr);
				map["sqid"] = jQuery("#id").val();
				var gridSetting = {
					caption:"评奖审核",
					pager:"pager",
					rowNum:10,
					url:"bzjl_sqsh.do?method=viweShmx",
					colList:[
				       {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				       {label:'key',name:'key', index: 'key',key:true ,hidden:true},
				       {label:'学院代码',name:'xydm', index: 'xydm',hidden:true},
				       {label:'项目代码',name:'xmdm', index: 'xmdm',hidden:true},
				       {label:'项目名称',name:'xmmc', index: 'xmmc',width:'10%'},
				       {label:'设置人数',name:'zzme', index: 'zzme',width:'10%'},
				       {label:'已通过人数',name:'ytgrs', index: 'ytgrs',width:'10%'},
				       {label:'剩余可通过人数',name:'syktgrs', index: 'syktgrs',width:'10%'},
				       {label:'本次审核人数',name:'bcshrs', index: 'bcshrs',width:'10%'}
					],
					params:map
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				map["bjdm"] = jQuery("#bjdm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		
			function saveZdPlsh(shzt){
				
				var shyj = jQuery("#shyj").val();
				var xyXmdm = jQuery("#dataTable").getSeletIds();
				var jsonStr = jQuery("#jsonStr").val();
				var id = jQuery("#id").val();
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				
				if (shyj == ""){
					showAlert("请填写审核意见！");
					return false;
				}

				if (rows.length == 0){
					showAlertDivLayer("请选择您要审核通过的记录！");
					return false;
				}

				var api = frameElement.api,W = api.opener;
				W.saveZdPlsh(shzt,shyj,xyXmdm.toString(),jsonStr,id);
				closeDialog();
			}

			/**
			 * 奖项审核查询
			 */
			function doQuery(){
				
				var jsonStr = jQuery("#jsonStr").val();
				var map = {};
				if(jsonStr){
					map = JSON.parse(jsonStr);
				}
				map["sqid"] = jQuery("#id").val();
				map["xyXmdm"] = jQuery("#xyXm").val();
				jQuery("#dataTable").reloadGrid(map);
				
			}

			
		</script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh" method="post" onsubmit="return false;">
		<input type="hidden" value='${jsonStr }' id="jsonStr" />
		<input type="hidden" value='${id }' id="id" />
			<div class="tab">
				<table class="formlist">
				<!-- 过滤条件 -->	
					<div class="searchtab">
						<table width="100%" border="0">
							<tr>
								<th width="12%">学院/项目名称</th>
								<td>
									<input type="text" id="xyXm" name="xyXm" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
								</td>
								<td>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="doQuery();">
											查 询
										</button>
									</div>
								</td>
							</tr>					
						</table>
					</div>
				</table>
				<!-- 过滤条件 end-->
				<table id="dataTable" style="width:100%;"></table>
				<div id="pager"></div>
				<table class="formlist">
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=pjpy&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
							</td>
						</tr>
						<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveZdPlsh('1');">
											通过
										</button>
										<button type="button" onclick="saveZdPlsh('2');">
											不通过
										</button>
										<button type="button" onclick="saveZdPlsh('3');">
											退回
										</button>
										<button type="button" name="关 闭" onclick="closeDialog();">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</div>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

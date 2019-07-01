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
				var gridSetting = {
					caption:"评奖审核",
					pager:"pager",
					url:"xpj_sqsh.do?method=viewZdtj&type=query",
					colList:[
				       {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc',width:'17%'},
				       {label:'国奖',name:'gjrs', index: 'gjrs',width:'6%'},
				       {label:'一等名额',name:'one', index: 'one',width:'7%'},
				       {label:'人数',name:'g1', index: 'g1',width:'6%'},
				       {label:'二等名额',name:'tw', index: 'tw',width:'7%'},
				       {label:'人数',name:'g2', index: 'g2',width:'6%'},
				       {label:'三等名额',name:'th', index: 'th',width:'7%'},
				       {label:'人数',name:'g3', index: 'g3',width:'6%'},
				       {label:'社会工作',name:'g3', index: 'g3',width:'7%'},
				       {label:'人数',name:'g4', index: 'g4',width:'6%'},
				       {label:'社会实践',name:'js', index: 'js',width:'7%'},
				       {label:'人数',name:'g5', index: 'g5',width:'6%'},
				       {label:'文体',name:'wt', index: 'wt',width:'6%'},
				       {label:'人数',name:'g6', index: 'g6',width:'6%'}
					],
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});

			/**
			 * 奖项审核查询
			 */
			function doQuery(){
				var map = {};
				map["xyXmdm"] = jQuery("#xyXmdm").val();
				jQuery("#dataTable").reloadGrid(map);
				
			}
			
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh" method="post" styleId="zcxmForm" onsubmit="return false;">
			<div class="tab">
				<table class="formlist">
				<!-- 过滤条件 -->	
					<div class="searchtab">
						<table width="100%" border="0">
							<tr>
								<th width="12%">学院</th>
								<td>
									<input type="text" id="xyXmdm" name="xyXmdm" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
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
			</div>
		</html:form>
	</body>
</html>

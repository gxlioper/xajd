<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/wsflr/js/wsflr.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/sq/js/jcftzsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
		function query(){
			var map = {};
			map["cxmc"] = jQuery("#cxmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		jQuery(function(){
			var gridSetting = {
				caption:"参评学生列表 ",
				pager:"pager",
				url:"jcftz_sq.do?method=TtrenDing&type=query&xmdm=${xmdm}"
			};
				
			var colList=[
				   {label:'key',name:'ttjgid', index: 'ttjgid',hidden:true,key:true},
				   {label:'团队名称',name:'tdmc', index: 'tdmc',width:'10%'},
				   {label:'队长姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'成员数',name:'cys', index: 'cys',width:'5%'},
				   {label:'队长所</br>在院系',name:'xymc', index: 'xymc',width:'10%'},
				   {label:'项目</br>基础分',name:'jcxf', index: 'jcxf',width:'8%'},
				   {label:'提交状态',name:'xfrdsqzt', index: 'xfrdsqzt',hidden:true},
				   {label:'jxdm',name:'jxdm', index: 'jxdm',hidden:true},
				   {label:'sfqq',name:'sfqq', index: 'sfqq',hidden:true},
				   {label:'jxmc',name:'jxmc', index: 'jxmc',hidden:true}
				];
						
				var json = {label:'调整后</br>基础分',
							name:"tzhjcf",
							index:"tzhjcf"							
							};
					json["formatter"] = function(cell,rowObject){					
							var html="";													
							if(rowObject["xfrdsqzt"] == '1'){
								return cell;
							}else{
								html+=cell==null ? rowObject["jcxf"] : cell;
								return html;
							}																		
					}				
			colList.push(json);
			colList.push({label:'获得奖项',	name:"jxmc",index:"jxmc"	});
			colList.push({label:'是否</br>缺勤',	name:"sfqqmc",index:"sfqqmc"});
			colList.push({label:'获得</br>总学分',name:'zf', index: 'zf',width:'7%'});
			//colList.push({label:'提交状态',name:'tjztmc', index: 'tjztmc',width:'7%'});
			
			<logic:equal name="xxdm" value="13627">
			colList.push({label:'备注1',name:'bz1',index:'bz1',width:'10%'});
			colList.push({label:'备注2',name:'bz2',index:'bz2',width:'10%'});
			colList.push({label:'备注3',name:'bz3',index:'bz3',width:'10%'});
			colList.push({label:'备注4',name:'bz4',index:'bz4',width:'10%'});
			colList.push({label:'备注5',name:'bz5',index:'bz5',width:'10%'});
			</logic:equal>
			
			gridSetting["colList"] = colList;
			jQuery("#dataTable").initGrid(gridSetting);
			jQuery(".nowrap").find("th").eq(8).append("<font color='red'>*</font>");
		});
		</script>
		
		<style>
			/*当终测项目过多时，本页列表会显示不全。增加列表滚动条显示*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>	
	<html:form action="/jcftz_sq" method="post">	
		<div class="toolbox">
			<!-- 按钮 -->
			<input type="hidden" id="jgid" value="${rs.jgid}" />
			<input type="hidden" id="xmdm" value="${xmdm}" />
			<input type="hidden" id="csms" value="${rs.csms}" />
			<%@ include file="/comm/hiddenValue.jsp"%>				
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								学号/姓名
							</th>
							<td>
								<input type="text" id="cxmc"
									onkeypress="if(event.keyCode==13){query();}" />
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
			
		</div>
		<div class="formbox" style="overflow: auto;">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${rs.xn}&nbsp;${rs.xqmc}&nbsp;${rs.xmmc}</font> </span>
			</h3>
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>

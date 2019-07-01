<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		function query(){
			var map = {};
			map["cxmc"] = jQuery("#cxmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		
			jQuery(function(){
				var gridSetting = {
					caption:"学分认定列表",
					pager:"pager",
					rowNum:10,
					url:"jcftz_sq.do?method=renDing&type=query&xmdm=${xmdm}&way=view",
					colList:[
					   {label:'key',name:'jgid',index:'jgid',hidden:true,key:true},
				   	   {label:'姓名',name:'xm',index:'xm',width:'10%'},
				       {label:'班级',name:'bjmc',index:'bjmc',width:'10%'},
				       {label:'项目</br>基础分',name:'jcxf',index:'jcxf',width:'6%'},
				       {label:'调整后</br>基础分',name:'tzhjcf',index:'tzhjcf',width:'6%'},
					   {label:'获得奖项',name:'jxmc',index:'jxmc',width:'10%'},
					   {label:'是否</br>缺勤',name:'sfqqrs',index:'sfqqmc',width:'6%'},
					   {label:'获得</br>总学分',name:'zf',index:'zf',width:'6%'}
					   <logic:equal name="xxdm" value="13627">
					   ,{label:'备注1',name:'bz1',index:'bz1',width:'10%'},
					   {label:'备注2',name:'bz2',index:'bz2',width:'10%'},
					   {label:'备注3',name:'bz3',index:'bz3',width:'10%'},
					   {label:'备注4',name:'bz4',index:'bz4',width:'10%'},
					   {label:'备注5',name:'bz5',index:'bz5',width:'10%'}
					   </logic:equal>
					],
					sortname:"zf",
					sortorder:"desc"
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body>
		<div class="toolbox">
		<input type="hidden" id="jgid" value="${rs.jgid}" />
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
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
			<div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
	</body>
</html>

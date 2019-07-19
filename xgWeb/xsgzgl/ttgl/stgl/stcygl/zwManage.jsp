<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		var gridSetting = {
					caption:"社团人员列表",
					pager:"pager",
					url:"ttgl_stcygl.do?method=zwManage&type=query",
					colList:[      
				           {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
						   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
						   {label:'书院',name:'symc', index: 'symc',width:'10%'},
						   {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
						   {label:'专业',name:'zymc', index: 'zydm',width:'10%'},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'10%'},
						   {label:'报名时间',name:'sqsj', index: 'sqsj',width:'10%'},
						   {label:'分组',name:'tnzw', index: 'tnzw',width:'8%'},
						   {label:'状态',name:'shzt', index: 'shzt',width:'8%',hidden:true},
						   {label:'职务变更',name:'shzt', index: 'shzt',width:'8%',formatter:ttLink}
						],
						params:{fpzt:"ysh",jgid:'${jgid}'},
						sortname: "nj,xymc,zymc,bjmc",
					 	sortorder: "asc"
				};
				
		jQuery(function(){
			var map = getSuperSearch();
			map["glzt"]="ysh";
			map["jgid"]='${jgid}';
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
		});	
		function View(guid) {
			showDialog("查看申请信息", 790,300, "ttgl_stcygl.do?method=viewsqxx&guid=" + guid);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='View(\""+ rowObject["guid"] + "\");'>" + cellValue
					+ "</a>";
		}
		function ttLink(cellValue, rowObject) {
			return "<button  onclick='zwbg(\""+ rowObject["guid"] + "\",\"" + rowObject["xh"] + "\");'>变更</button>";
		}
		function zwbg(guid,xh) {
			var jgid = jQuery("#jgid").val();
			var url = "ttgl_stcygl.do?method=zwChange&guid="+guid+"&xh="+xh+"&jgid="+jgid;
			showDialog("职务变更", 700,500, url);
		}
		
		function searchRs(){
			var map = getSuperSearch();
			map["glzt"]="ysh";
			map["sydm"]='${sydm}';
			jQuery("#dataTable").reloadGrid(map);
		}

		</script>
	</head>

	<body>
		<html:form action="/ttgl_stcygl">
			<input type="hidden" id="glzt" value="ysh"/>
			<input type="hidden" id="jgid" value="${jgid}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>社团人员审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow" style="overflow-y: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>

		<div style="height:30px;"></div>
		<div>
			 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
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

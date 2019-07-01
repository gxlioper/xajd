<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/jdwhsz/js/jdwhsz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "grttxm_jdsz.do?method=getJdszGrList&type=query",
				colList : [ {
					label : 'key',
					name : 'jdwhid',
					index : 'jdwhid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '10%'
				},{
					label : '阶段分',
					name : 'jbf',
					index : 'jbf',
					width : '10%'
				},{
					label : '活动时长',
					name : 'hdsc',
					index : 'hdsc',
					width : '35%'
				},{
					label : '备注',
					name : 'bz',
					index : 'bz'
				},
				{
					name : 'jdcy',
					index : 'jdcy',
					hidden : true
				},
				{
					name : 'jdid',
					index : 'jdid',
					hidden : true
				},
				{
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}
				],
				radioselect:false
			}
			var map = getSuperSearch();
			map['jdid']=jQuery('#jdid').val();
			map['xh']=jQuery('#xh').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="jdid" id="jdid" value="${jdid}"/>
		<input type="hidden" name="jdf" id="jdf" value="${jdf}"/>
		<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
		<div class="tab_cur">
		</div>
		<html:form action="/cxdd_pysb" onsubmit="return false;" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">学号 / 姓名</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button"  class="btn_cx" id="search_go" onclick="doQuery();">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		
				<!-- 过滤条件 -->	
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color = "blue">${xmmc}</font> ${jdmc}&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

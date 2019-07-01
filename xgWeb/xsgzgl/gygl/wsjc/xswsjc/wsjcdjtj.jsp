<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script	type="text/javascript">
		/* jQuery(function(){
			_w_table_rowspan("#gxssfb",1);
			_w_table_rowspan_merge("#gxssfb",16,2);
			_w_table_rowspan_merge("#gxssfb",1,6);
			_w_table_rowspan_merge("#gxssfb",16,7);
			_w_table_rowspan_merge("#gxssfb",1,8);
			_w_table_rowspan_merge("#gxssfb",16,9);
			_w_table_rowspan_merge_sum("#gxssfb",1,10);
			_w_table_rowspan_merge_sum("#gxssfb",16,11);
			_w_table_rowspan_merge_sum("#gxssfb",1,12);
			_w_table_rowspan_merge_sum("#gxssfb",16,13);
			_w_table_rowspan_merge_sum("#gxssfb",1,14);
			_w_table_rowspan_merge_sum("#gxssfb",16,15);
		}); */
		
		jQuery(function(){
			var gridSetting = {
				caption : "卫生等级统计",
				url : "gyglnew_xswsjc.do?method=getWsjcdjtb",
				colList : [
					{label:'',name:'xymc0',index :'xymc0'},
					{label:'',name:'fdyxm0',index:'fdyxm0'},
					{label:'',name:'pyccmc',index:'pyccmc'},
					{label:'',name:'xqmc',index:'xqmc'},
					{label:'',name:'ydxsrs',index:'ydxsrs'},
					{label:'',name:'sjcqrs',index:'sjcqrs'},
					{label:'',name:'cql',index:'cql'},
					{label:'',name:'wsdja',index:'wsdja'},
					{label:'',name:'wsdjb',index:'wsdjb'},
					{label:'',name:'wsdjc',index:'wsdjc'},
					{label:'',name:'wsdjd',index:'wsdjd'},
					{label:'',name:'wsyxl',index:'wsyxl'},
					{label:'',name:'wsbhgl',index:'wsbhgl'},
					{label:'',name:'ybgs',index:'ybgs'},
					{label:'',name:'wg',index:'wg'},
					
				],
				multiselect:false,
				usedefined:true,
				usercols:15
			}
			jQuery("#dataTable").initGrid(gridSetting);
		})
		
		function query(){
			var jcrqSta= jQuery("#jcrqSta").val();
			var jcrqEnd= jQuery("#jcrqEnd").val();
			if(""==jcrqSta||""==jcrqEnd){
				showAlertDivLayer("请填写检查日期！");
				return false;
			}
			var map = {};
			map['jcrqSta']=jcrqSta;
			map['jcrqEnd']=jcrqEnd;
			jQuery("#dataTable").reloadGrid(map);
		}
		
		function exportFile(url){
			var jcrqSta= jQuery("#jcrqSta").val();
			var jcrqEnd= jQuery("#jcrqEnd").val();
			if(""==jcrqSta||""==jcrqEnd){
				showAlertDivLayer("请填写检查日期！");
				return false;
			}
			document.forms[0].action=url+"&jcrqSta="+jcrqSta+"&jcrqEnd="+jcrqEnd;
			document.forms[0].submit();
			
		}
		
		</script>
	</head>
	<body>
	<html:form action="/gyglnew_xswsjc" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportFile('gyglnew_xswsjc.do?method=getExportFile');return false;" class="btn_dc" >导出</a>
						</li>
					</ul>
				</div>
			</div>
		<div class="toolbox">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody id="tbody_search_query">
						<tr>
							<th>
								检查日期
							</th>
							<td>
								<input type="text"  id="jcrqSta" style="width:100px" readonly	onfocus="return showCalendar('jcrqSta','yyyyMMdd',true,'jcrqEnd');"/>
								至
								<input type="text"  id="jcrqEnd" style="width:100px" readonly	onfocus="return showCalendar('jcrqEnd','yyyyMMdd',false,'jcrqSta');"/>
							</td>	
							<td >
								<div class="btn">
									<button type="button"  class="btn_cx" id="search_go" onclick="query();return false;">
										查 询
									</button>
							<!-- 		&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
									</button> -->
								</div>
							</td>						
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div style='width:100%;'>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>卫生检查等级统计图</span>
						</th>
					</tr>
				</thead>
			</table>
		<!-- 	<table id="wsjcdjtb" width="100%" align="center" border="2">
			</table> -->
			<table id="dataTable" >
				<thead>
					<tr class="nowrap">
						<th width="12%" style="text-align:center" rowspan = "2" >学院</th>
						<th width="8%" style="text-align:center" rowspan = "2" >辅导员</th>
						<th width="8%" style="text-align:center" rowspan = "2" >学生<br />类型</th>
						<th width="8%" style="text-align:center" rowspan = "2" >校区</th>
					    <th width="16%" style="text-align:center" colspan = "3">上课出勤人数</th>
						<th width="28%" style="text-align:center" colspan = "6">寝室卫生</th>
						<th width="8%" style="text-align:center" colspan = "2">夜不归<br />宿、晚归</th>
					</tr>
					<tr>
						<th width="4%" >应到<br />学生<br />人数</th>
						<th width="4%" >实际<br />出勤<br />人数</th>
						<th width="8%" >出勤率<br />(%)</th>
						<th width="4%" >优秀<br />(A)</th>
						<th width="4%" >良好<br />(B)</th>
						<th width="4%" >合格<br />(C)</th>
						<th width="4%" >不合<br />格(D)</th>
						<th width="8%" >优秀率<br />(%)</th>
						<th width="8%" >不合格<br />率(%)</th>
						<th width="4%" >夜不<br />归宿(人次)</th>
						<th width="4%" >晚归<br />(人次)</th>
					</tr>
				</thead>
				</table>
		</div>
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>


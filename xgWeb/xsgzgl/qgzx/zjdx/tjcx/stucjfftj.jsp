<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "tjcx_zjdx.do?method=getStudentQgDetailTjCx&type=query",
				colList : [ {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '15%'
				},{
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '5%'
				},{
					label : '学院',
					name : 'xymc',
					index : 'xymc',
					width : '15%'
				},{
					label : '1月',
					name : 'janje',
					index : 'janje',
					width : '5%'
				},{
					label : '2月',
					name : 'febje',
					index : 'febje',
					width : '5%'
				},{
					label : '3月',
					name : 'marchje',
					index : 'marchje',
					width : '5%'
				},{
					label : '4月',
					name : 'aprje',
					index : 'aprje',
					width : '5%'
				},{
					label : '5月',
					name : 'mayje',
					index : 'mayje',
					width : '5%'
				},{
					label : '6月',
					name : 'junje',
					index : 'junje',
					width : '5%'
				},{
					label : '7月',
					name : 'julje',
					index : 'julje',
					width : '5%'
				},{
					label : '8月',
					name : 'augje',
					index : 'augje',
					width : '5%'
				},{
					label : '9月',
					name : 'sepje',
					index : 'sepje',
					width : '5%'
				},{
					label : '10月',
					name : 'octje',
					index : 'octje',
					width : '5%'
				},{
					label : '11月',
					name : 'novje',
					index : 'novje',
					width : '5%'
				},{
					label : '12月',
					name : 'decmje',
					index : 'decmje',
					width : '5%'
				},{
					label : '合计',
					name : 'rowje',
					index : 'rowje',
					width : '15%'
				}],
				multiselect:false,
				usedefined : true,
				uselastrow:true,
				uselastid:"dataTable1",
				usercols:16,
				useurl:"tjcx_zjdx.do?method=getStudentQgDetailTjCxSum"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		function searchRs() {
			if(jQuery("[name='tj_nd']").parent().find(".selectedValue").length != 1){
				showAlertDivLayer("年度高级查询条件有且只能选择一个！");
				return false;
			}
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		var DCCLBH = "cjff_tjcx_zjdx_stucjfftj.do";//dcclbh,导出功能编号

	//自定义导出 功能
	function exportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport(DCCLBH, xyzsjgExportData);
	}

	//导出方法
	function xyzsjgExportData() {
		setSearchTj();//设置高级查询条件
		var url = "tjcx_zjdx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				
				<logic:equal value="true" name="sfqggly">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				</logic:equal>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>个人报酬发放明细列表&nbsp;&nbsp;<font color="blue">单位(元)</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" >
					<thead>
					<tr class="nowrap">
						<th width="15%" style="text-align:center" sortname="xh" rowspan = "2" >学号</th>
						<th width="5%" style="text-align:center" sortname="xm" rowspan = "2" >姓名</th>
						<th width="15%" style="text-align:center" sortname="xymc" rowspan = "2" >学院</th>
						<th width="60%" style="text-align:center" colspan="12">明细统计</th>
					
						<th width="15%" style="text-align:center" rowspan = "2">合计</th>
					</tr>
					<tr>
						<th width="5%" sortname="janje">1月</th>
						<th width="5%" sortname="febje">2月</th>
						<th width="5%" sortname="marchje">3月</th>
						<th width="5%" sortname="aprje" >4月</th>
						<th width="5%" sortname="mayje" >5月</th>
						<th width="5%" sortname="junje" >6月</th>
						<th width="5%" sortname="julje" >7月</th>
						<th width="5%" sortname="augje" >8月</th>
						<th width="5%" sortname="sepje" >9月</th>
						<th width="5%" sortname="octje" >10月</th>
						<th width="5%" sortname="novje" >11月</th>
						<th width="5%" sortname="decmje" >12月</th>
					</tr>
				</thead>
				</table>
				<div style="display:none">
				<table  class="dateline" >
					<tbody id="dataTable1">
					
					<tr >
								<td style="word-break:break-all;" width="35%" colspan="3"><font color="red" style="font-weight:bold">总计</font></td>
								<td style="word-break:break-all;" width="5%" name="jan">${hj.jan}</td>
								<td style="word-break:break-all;" width="5%" name="feb">${hj.feb}</td>
								<td style="word-break:break-all;" width="5%" name="march">${hj.march}</td>
								<td style="word-break:break-all;" width="5%" name="apr">${hj.apr}</td>
								<td style="word-break:break-all;" width="5%" name="may">${hj.may}</td>
								<td style="word-break:break-all;" width="5%" name="jun">${hj.jun}</td>
								<td style="word-break:break-all;" width="5%" name="jul">${hj.jul}</td>
								<td style="word-break:break-all;" width="5%" name="aug">${hj.aug}</td>
								<td style="word-break:break-all;" width="5%" name="sep">${hj.sep}</td>
								<td style="word-break:break-all;" width="5%" name="oct">${hj.oct}</td>
								<td style="word-break:break-all;" width="5%" name="nov">${hj.nov}</td>
								<td style="word-break:break-all;" width="5%" name="decm">${hj.decm}</td>
								<td style="word-break:break-all;" width="5%" name="row">${hj.row}</td>
							</tr>
						</tbody>
				</table>
					</div>
				
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

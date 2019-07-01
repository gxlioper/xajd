<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学分查询",
				pager : "pager",
				url : "dekt_xfjg.do?method=getXfjgList",
				colList : [
					{label:'jgid',name:'jgid',index :'jgid',key:true,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'认定内容',name:'rdnr', index: 'rdnr',width:'46%'},
					{label:'获奖时间',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'学分',name:'xf', index: 'xf',width:'10%'}
				],
				sortname: "hjsj",
			 	sortorder: "desc"
			};
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);

			jQuery("#pf").bind({click:function(){
				pf();
			}});
		})
		
		function viewXfjg(jgid, xh) {
			showDialog("学分查询", 700, 480, "dekt_xfjg.do?method=xfjgView&jgid=" + jgid+ "&xh=" + xh);
		}
		
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXfjg(\""
					+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue	+ "</a>";
		}
		
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport('dekt_xfjg_list.do', ExportDatas);
		}

		//导出方法
		function ExportDatas() {
			setSearchTj();//设置高级查询条件
			var url = "dekt_xfjg.do?method=exportData&dcclbh=" + 'dekt_xfjg_list.do';//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		function pf(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要评价的记录！");
				return false;
			}
			var url = 'dekt_xfjg.do?method=pf&jgid=' + rows[0]["jgid"];
			var title = "评价";
			showDialog(title, 400, 200, url);
		}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_xmwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<%-- <logic:equal name="writeAble" value="yes">
						 <li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >导入</a></li>
						</logic:equal> --%>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
<%--						<logic:equal value="10346" name="xxdm">--%>
							<li>
								<a href="javascript:void(0);" class="btn_sz" id="pf">评价</a> 
							</li>
<%--						</logic:equal>	--%>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>
					学分查询&nbsp;&nbsp;
					<logic:present name="xf">
						<logic:notEmpty name="xf">
							总学分：
							<font color="red">
								${xf}
							</font>
						</logic:notEmpty>
					</logic:present>
				</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

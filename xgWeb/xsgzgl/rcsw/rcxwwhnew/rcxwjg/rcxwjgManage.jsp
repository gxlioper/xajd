<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwhnew/rcxwjg/rcxwjg.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				
				var gridSetting = {
						caption:"日常行为信息结果列表",
				pager:"pager",
				params:getSuperSearch(),
				url:"rcsw_rcxwwhnew_rcxwjggl.do?method=rcxwjgManage&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'12%'},
				   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
                    {label:'行为大类',name:'dlxx', index: 'dlxx',width:'15%'},
                    {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%' ,hidden:true},
				   {label:'行为小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				   {label:'发生时间',name:'fssj', index: 'fssj',width:'10%'},
				   {label:'评定分值',name:'fz', index: 'fz',width:'8%'},
				   {label:'学年学期',name:'xnxq', index: 'xnxq',width:'15%'},
				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true},
				   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
				],
				sortname: "rcxwjlsj",
			 	sortorder: "desc"
			}	
				//西安科技大学个性化
				if(jQuery("#xxdm").val() == '10704'){
					gridSetting["caption"] = "综合测评信息结果列表";
					gridSetting["colList"] = [
						       				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						    				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						    				   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
						    				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						    				   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
						    				   {label:'班级',name:'bjmc', index: 'bjmc',width:'12%'},
						    				   {label:'综合测评类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
						    				   {label:'综合测评大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
						    				   {label:'综合测评小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
						    				   {label:'发生时间',name:'fssj', index: 'fssj',width:'10%'},
						    				   {label:'评定分值',name:'fz', index: 'fz',width:'8%'},
						    				   {label:'学年学期',name:'xnxq', index: 'xnxq',width:'15%'},
						    				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
						    				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
						    				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true},
						    				   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
					    					];
					
				}
				jQuery("#dataTable").initGrid(gridSetting);
	
			});
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
	
			function add() {
				var url = "rcsw_rcxwwhnew_rcxwjggl.do?method=addXwjg";
				var title = "增加日常行为结果";
				if(${xxdm=="12970"}){
					title = "增加素质学分结果";
				}
				if(jQuery("#xxdm").val() == '10704'){
					title = "增加综合测评结果";
				}
				showDialog(title, 950, 450, url);
			}
	
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
	
				if (rows.length != 1) {
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else{
					var sjly = rows[0]["sjly"];
					if (sjly == '1') {
						showAlertDivLayer("审批流数据不允许修改！");
						return false;
					} 
					var url = 'rcsw_rcxwwhnew_rcxwjggl.do?method=updateXwjg&id='
							+ rows[0]["id"] + '&xh=' + rows[0]["xh"] + '&rcxwlbdldm='
							+ rows[0]["rcxwlbdldm"] + '&rcxwlbdm=' + rows[0]["rcxwlbdm"]
							+ '&xn=' + rows[0]["xn"] + '&xq=' + rows[0]["xq"];
					var title = "修改日常行为结果";
					if(${xxdm=="12970"}){
						title = "修改素质学分结果";
					}
					if(jQuery("#xxdm").val() == "10704"){
						title = "修改综合测评结果";
					}
					showDialog(title, 850, 450, url);
				}
	
			}
	
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
	
				if (ids.length == 0) {
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("流程数据不能删除！");
							return false;
						}
					}
	
					showConfirmDivLayer("您确定要删除选择的记录吗？", {
						"okFun" : function() {
							jQuery.post("rcsw_rcxwwhnew_rcxwjggl.do?method=delXwjg", {
								values : ids.toString()
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
	
				}
			}
	
			function xwjgView(id, xh) {
				var title = "查看日常行为结果";
				if(${xxdm=="12970"}){
					title = "查看素质学分结果";
				}
				if(jQuery("#xxdm").val() == '10704'){
					title = "查看综合测评结果";
				}
				showDialog(title, 720, 520,
						"rcsw_rcxwwhnew_rcxwjggl.do?method=viewXwjg&id=" + id + "&xh=" + xh);
			}
	
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
						+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
						+ "</a>";
			}

            function view() {
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length == 0) {
                    showAlertDivLayer("请选择您要预览的记录！");
                }else{
                    var title = "查看日常行为结果";
                    showDialog(title, 720, 520,
                        "rcsw_rcxwwhnew_rcxwjggl.do?method=viewXwjg&id=" + rows[0]["id"] + "&xh=" + rows[0]["xh"]);
				}
            }

            var DCCLBH = "rcsw_rcxwwhnew_rcxwjg.do";// dcclbh,导出功能编号
	
			// 自定义导出 功能
			function exportConfig() {
				// DCCLBH导出功能编号,执行导出函数
				customExport(DCCLBH, rcxwjgExportData);
			}
	
			// 导出方法
			function rcxwjgExportData() {
				setSearchTj();// 设置高级查询条件
				var url = "rcsw_rcxwwhnew_rcxwjggl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
				url = addSuperSearchParams(url);// 设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
	
			//温大文明品行实践导出
			function xsPxsjDc(){
				var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=xsPxsjDc";
				var xqLength=jQuery("a[name=a_name_xq]").length;
				var xnLength=jQuery("a[name=a_name_xn]").length;
				var yqLength=jQuery("a[name=a_name_yqdm]").length;
				if(xqLength != "1"){
					showAlertDivLayer("请选择一个学期查询条件！");
					return false;
				}
				if(xnLength != "1"){
					showAlertDivLayer("请选择一个学年查询条件！");
					return false;
				}
				if(yqLength != "1"){
					showAlertDivLayer("请选择一个学区查询条件！");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
				
			}
			
			function rcxwsjDc() {
				var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=rcxwsjDc";
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xnLength != "1"){
					showAlertDivLayer("请选择一个学年查询条件！");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			//日常行为总分导出
			function rcxwtjbDc() {
				var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=rcxwtjbDc";
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xnLength != "1"){
					showAlertDivLayer("请选择一个学年查询条件！");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			// 导入方法
			function importData() {
				toImportDataNew("IMPORT_N730204_NEW");
				return false;
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwhnew_rcxwjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwjg"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>删除</a>
						</li>
						<li><a href="#" onclick="importData();return false;" class="btn_dr">导入</a></li>	
						</logic:equal>
						<!-- 读写权 -->
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<li><a href="javascript:void(0);" onclick="view();" class="btn_xg">查看</a></li>
						<logic:equal value="13779" name="xxdm">
						<li><a href="#" class="btn_dy" onclick="printXfjl();return false;">学生学分记录表下载</a></li>
						</logic:equal>
						<%-- <logic:equal value="10868" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="sxpdcjhzDc();return false;">成绩汇总导出</a></li>
						</logic:equal>		 --%>
<%--						<logic:equal value="13023" name="xxdm">--%>
<%--							<li><a href="#" class="btn_dc" onclick="rcxwsjDc();return false;">素质测评分导出</a></li>--%>
<%--						</logic:equal>--%>
<%--						<logic:equal value="10071" name="xxdm">--%>
<%--							<li><a href="#" class="btn_dc" onclick="rcxwtjbDc();return false;">日常行为统计表导出</a></li>--%>
<%--						</logic:equal>--%>
						<!-- 未修改 end-->
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<logic:equal name="xxdm" value="12970">	
					<span>素质学分信息结果列表&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="12970">
					<span>
						<logic:equal value="10704" name="xxdm">
							 综合测评信息结果列表
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							日常行为信息结果列表&nbsp;&nbsp;							
						</logic:notEqual>
					</span>
				</logic:notEqual>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

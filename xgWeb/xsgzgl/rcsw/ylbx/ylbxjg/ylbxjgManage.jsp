<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/ylbx/ylbxjg/js/ylbxjgManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"医疗保险结果列表",
						pager:"pager",
						url:"rcsw_ylbx_ylbxjggl.do?method=ylbxjgManage&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
							<logic:notEqual name="xxdm" value="14073">
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   </logic:notEqual>
						   <logic:equal name="xxdm" value="14073">
						   {label:'年度',name:'zd5', index: 'zd5',width:'10%'},
						   </logic:equal>
						   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'年级',name:'nj', index: 'nj',width:'6%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'15%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'17%'},
						   <logic:equal name="xxdm" value="13573">
						   {label:'低保标志',name:'sfkns', index: 'sfkns',width:'6%'},
						   </logic:equal>
						   <logic:equal name="xxdm" value="10335">
						   {label:'保险公司',name:'zd8mc', index: 'zd8mc',width:'10%'},
						   {label:'投保年限',name:'zd2', index: 'zd2',width:'10%'},
						   {label:'投保金额',name:'zd3', index: 'zd3',width:'10%'},
						   </logic:equal>
						   {label:'流程数据',name:'sjly', index: 'sjly',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var url = "rcsw_ylbx_ylbxjggl.do?method=addYlbxsqjg";
			var title = "增加"+jQuery("#gnmkmc").val();
			showDialog(title,790,476,url);
		}



		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			var sjly = rows[0]["sjly"];
			
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			}else if(sjly == '1'){
				showAlertDivLayer("流程数据不能修改！");
			}else {
				var url = 'rcsw_ylbx_ylbxjggl.do?method=updateYlbxjg&jgid='+ rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
				var title = "修改"+jQuery("#gnmkmc").val();
				showDialog(title, 790,470, url);
			}

		}

		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0){
				showAlertDivLayer("请选择您要删除的记录！");
			}else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['sjly']=='1'){
						showAlertDivLayer("流程数据不能删除！");
						return false;
					}
				}
				
				showConfirmDivLayer("您确定要删除选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("rcsw_ylbx_ylbxjggl.do?method=delYlbxjg", {
							values : ids.toString()
						}, function(data) {
							var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
							mes+="</br>";
							if(data["nodel"]!="-1"){
								mes+="<font color='red'>"+data["nodel"]+"</font>";
								mes+="流程数据不能修改！";
							}
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}

		function ylbxjgView(jgid, xh) {
			showDialog(jQuery("#gnmkmc").val()+"查看", 700, 401, "rcsw_ylbx_ylbxjggl.do?method=viewOneYlbxjg&jgid=" + jgid
					+ "&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ylbxjgView(\""
					+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_ylbx_ylbxjg.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, ylbxjgExportData);
		}

		// 导出方法
		function ylbxjgExportData() {
			setSearchTj();//设置高级查询条件
			var url = "rcsw_ylbx_ylbxjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		
		// 导出
		function zpdc() {
			showDialog("", 400, 150, 'rcsw_ylbx_ylbxjggl.do?method=zpdc');
		}
		// 导出
		function dszmdy() {
			var ids = jQuery("#dataTable").getSeletIds();
			if(ids.length!=1){
				showAlertDivLayer("请选择一条您要打印的记录！");
				return false;
				}
			var url="rcsw_ylbx_ylbxjggl.do?method=dszmdy&jgid="+ids;
			window.open(url);
		}

		
		function exportZpxx(mmfs,zpType){
			setSearchTj();//设置高级查询条件
			var url = "rcsw_ylbx_ylbxjggl.do?method=zpdc&type=exp&photoNameType="+mmfs+"&zpType="+zpType;
				url = addSuperSearchParams(url);
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
		}

		//新版导入
		function dr() {
			// 调用通用的导入function，参数是导入功能模块代码。
			toImportDataNew("IMPORT_N732605_YLBXGLNEW");
			return false;

		}
				
		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
			
		<logic:equal name="xxdm" value="13573">
			<!-- 提示信息 start-->
			<div  id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						照片导出是根据<font color="red">高级查询条件结果集</font>导出的！
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
		</logic:equal>
		
		<html:form action="/rcsw_ylbx_ylbxjggl">
		
		<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
		<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual name="userType" value="stu">
				  		    <logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">导入</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
							<logic:equal name="xxdm" value="13573">
								<li><a href="javascript:void(0);" onclick="zpdc();return false;" class="btn_dc">照片导出</a></li>
							</logic:equal>
							<logic:equal name="xxdm" value="12688">
								<li><a href="javascript:void(0);" onclick="zpdc();return false;" class="btn_dy">照片导出</a></li>
							</logic:equal>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10335">
							<li><a href="javascript:void(0);" onclick="dszmdy();return false;" class="btn_dy">丢失证明打印</a></li>
						</logic:equal>						
					</ul>
				</div>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>${gnmkmc}列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

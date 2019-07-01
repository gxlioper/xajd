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
					caption:"申请列表",
					pager:"pager",
					url:"xszz_xszzbjpy_jgcxgl.do?method=jgcxManage&type=query&pyyxbl=${jcszModel.pyyxbl }",
					colList:[      
				         {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
				         {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
						   {label:'性别',name:'xb', index: 'xb',width:'4%'},
						   //{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
						   //{label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
						   {label:'申请学年',name:'xn', index: 'xn',width:'8%'},
						   {label:'申请学期',name:'xqmc', index: 'xqmc',width:'3%'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'11%'},
						   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'8%'},
						   {label:'已评人数<br/>/总人数',name:'yprsmc', index: 'yprsmc',width:'8%'},
						   {label:'评议统计',name:'pyjgtjmc', index: 'pyjgtjmc',width:'11%'},
						   {label:'评议结果',name:'pyjgtempmc', index: 'pyjgtempmc',width:'10%'},
						   {label:'审核状态',name:'shztbjpymc', index: 'shztbjpymc',width:'5%'},
						   {label:'评议结果代码',name:'pyjgtempdm', index: 'pyjgtempdm',hidden:true},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',hidden:true},
						   {label:'学期',name:'xq', index: 'xq',hidden:true},
						   {label:'是否全部评完',name:'yprsflag', index: 'yprsflag',hidden:true},
						   {label:'项目代码',name:'xmdm', index: 'xmdm',hidden:true},
						   {label:'班级评议状态',name:'shztbjpy', index: 'shztbjpy',hidden:true}
						],
						callBack:setBjmc,
						sortname: "sqsj",
					 	sortorder: "desc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);

			var userStatus = jQuery("#userStatus").val();
			if(userStatus != "stu"){
				jQuery("#btn_submit").hide();
				jQuery("#btn_cancel").hide();
			}
		});

		function jgcxView(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一个学生！");
			} else {
				var url = "xszz_xszzbjpy_jgcxgl.do?method=jgcxView&xn=" + rows[0]["xn"] + "&xq=" + rows[0]["xq"] + "&sqr=" + rows[0]["xh"] + "&shztbjpy=" + rows[0]["shztbjpy"] + "&xmdm=" + rows[0]["xmdm"];
				var title = "查看评议详情";
				showDialog(title,800,500,url);
			}
		}
		function setBjmc(){
			var html = "<span>申请列表&nbsp;&nbsp; </span>";
			var userStatus = jQuery("#userStatus").val();
			if(userStatus == "stu"){
				html += "<font color='#0000ff'>${xsxxMap.xymc} ${xsxxMap.bjmc}</font>";
			}
			jQuery("#jgcx_manage_title").html(html);
		}
		function xhLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='xszzbjpyView(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
		}
		function xszzbjpyView(guid){
			showDialog('资助申请查看',780,520,'xszz_sqsh.do?method=viewXmsq&guid='+guid);
		}
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基本设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放班级评议，请联系管理员！");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一个学生！");
			} else {
				// 设置评议结果，便于子页面取值
				jQuery("#pyjgtempmc_hid").val(rows[0]["pyjgtempmc"]);
				jQuery("#pyjgtempdm_hid").val(rows[0]["pyjgtempdm"]);

				var ids = jQuery("#dataTable").getSeletIds();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='6'){
						showAlertDivLayer("请选择未提交的记录！");
						return false;
					}
				}
				
				if(rows[0]["yprsflag"] == 'false'){
					showAlertDivLayer("部分人员未评议！");
					return false;
				}
				var url = "xszz_xszzbjpy_jgcxgl.do?method=jgcxTj&sqid=" + rows[0]["guid"];
				var title = "提交班级评议结果";
				showDialog(title,790,500,url);
			}
		}
		function cancleRst(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1 ) {
				showAlertDivLayer("请选择一条您要撤销的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']=='6'){
						showAlertDivLayer("该记录尚未提交，无需撤销！");
						return false;
					}else{
						if(rows[i]['shzt']!='5' && rows[i]['shzt']!='7'){
							showAlertDivLayer("该记录已被审核，无法撤销！");
							return false;
						}
					}
				}
				showConfirmDivLayer("您确定要撤销选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("xszz_xszzbjpy_jgcxgl.do?method=jgcxCx", {
							values : rows[0]["xh"],
							sqid : rows[0]["guid"]
						}, function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}
		//导出
		function exportConfig(){
			var DCCLBH='xszz_xszzbjpy_jgcx.do';
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			var DCCLBH='xszz_xszzbjpy_jgcx.do';
			setSearchTj();//设置高级查询条件
			var url = "xszz_xszzbjpy_jgcxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_xszzbjpy_jgcxgl">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.bjpyisopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" id="pyjgtempmc_hid" value=""/>
			<input type="hidden" id="pyjgtempdm_hid" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="btn_submit">
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc" >提交</a>
						</li>
						<li id="btn_cancel">
							<a href="javascript:void(0);" onclick="cancleRst();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="jgcxView();return false;" class="btn_ck">查看评议详情</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01 id="jgcx_manage_title">
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

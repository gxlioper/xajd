<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/dektxfsh/js/xfsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"第二课堂学分审核",
				pager:"pager",
				url:"dekt_xfsh.do?method=getXfshList",
				colList:[
					{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'认定内容',name:'rdnr', index: 'rdnr',width:'40%'},
					{label:'获奖时间',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'16%'},
					{label:'审批流程',name:'splc', index: 'splc',hidden:true},
					{label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
					{label:'审核Id',name:'shid', index: 'shid',hidden:true},
					{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},//默认待审核
				sortname: "hjsj",
			 	sortorder: "desc",
			 	radioselect:false
		};

		var gridSetting2 = {
				caption:"第二课堂学分审核",
				pager:"pager",
				url:"dekt_xfsh.do?method=getXfshList",
				colList:[
					{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'认定内容',name:'rdnr', index: 'rdnr',width:'40%'},
					{label:'获奖时间',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'16%'},
					{label:'审批流程',name:'splc', index: 'splc',hidden:true},
					{label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
					{label:'审核Id',name:'shid', index: 'shid',hidden:true},
					{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"ysh"},//默认待审核
				sortname: "hjsj",
			 	sortorder: "desc",
			 	radioselect:false
		};
			
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function xfshDgsh() {
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = jQuery("#shzt").val();
			if (rows.length == 0) {
				showAlertDivLayer("请选择您要审核的记录")
				return false;
			}
			if (shzt == "ysh") {
				showAlertDivLayer("已处理记录不能再次审核");
				return false;
			} else if (rows.length == 1) {
				var url = "dekt_xfsh.do?method=xfshDgsh&sqid=" + rows[0]["sqid"] +'&xh=' + rows[0]["xh"]+ '&shid=' + rows[0]["shid"];
				showDialog("审核", 900, 525, url);
			} else {
				showAlertDivLayer("请勾选一条需要审核的数据");
				//showDialog("批量审核", 500, 250, "qgzx_cjffsq.do?method=cjxxPlsh");
			}
		}
		
		function cxsh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要撤消的审核记录！");
			}else{
				var splc = rows[0]["splc"];
				var shid = rows[0]["shid"];
				var sqid = rows[0]["sqid"];
				var shzt = rows[0]["shzt"];
				showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
					jQuery.post("dekt_xfsh.do?method=cxsh",{splc:splc,shid:shid,sqid:sqid,shzt:shzt},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
				},'json');
				}});
			}
		}
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_xfsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xfshDgsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cxsh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="shLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<!-- <logic:equal name="writeAble" value="yes">	   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>		-->		
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>第二课堂学分审核&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

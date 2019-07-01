<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
		<script type="text/javascript">
			function getDclGird(){
				var colList = [
							   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
							   {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
							   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
							   {label:'性别',name:'xb', index: 'xb',width:'5%'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
								{label:'书院',name:'symc', index: 'symc',width:'13%'},
								{label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
								{label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
							   {label:'申请周期',name:'sqzq', index: 'sqzq',width:'12%'},
							   {label:'申请学年',name:'xn', index: 'xn',hidden:true},
							   {label:'申请学期',name:'xqmc', index: 'xq',hidden:true},
							   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
							   {label:'项目名称',name:'xmmc', index: 'xmdm',width:'11%'},
							   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'gwid',name:'xtgwid', index: 'xtgwid',hidden:true},
							   {label:'申请金额',name:'ylzd1', index: 'ylzd1',width:'11%'}
							];
	
				var zcxm = jQuery("[name=zcxm]");
				jQuery.each(zcxm,function(i,n){
					var checked = jQuery(n).prop("checked");
					var v = jQuery(n).val();
					var xmfsJson = {
			 				label:jQuery(n).attr("xmmc"),
			 				name:v,
			 				index:v,
			 				hidden:(!checked)
			 		};
					colList.push(xmfsJson);
				});
	
				colList.push({label:'审核状态',name:'shztmc', index: 'shzt',width:'11%'});
				if(jQuery("#xxdm").val() == "13627"){
					colList.push({label:'辅导员',name:'fdyxm', index: 'fdyxm',width:'8%'});
				}
				return {
					caption:"资助审核列表",
					pager:"pager",
					url:"xszz_sqsh.do?method=xmshManage&type=query",
					colList:colList,
					params:{shzt:"dsh"},//默认待审核
					sortname: "sqsj",
				 	sortorder: "desc"
				};
			}
			function getYclGrid(){
				var colList = [
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'9%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'6%'},
					   {label:'性别',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'书院',name:'symc', index: 'symc',width:'13%'},
                    {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'申请周期',name:'sqzq', index: 'sqzq',width:'12%'},
					   {label:'学年',name:'xn', index: 'xn',hidden:true},
					   {label:'学期',name:'xqmc', index: 'xq',hidden:true},
					   {label:'项目名称',name:'xmmc', index: 'xmdm',width:'11%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'shid',name:'shid', index: 'shid',hidden:true},
					   {label:'gwid',name:'xtgwid', index: 'xtgwid',hidden:true},
					   {label:'申请金额',name:'ylzd1', index: 'ylzd1',width:'11%'}
					];
	
				var zcxm = jQuery("[name=zcxm]");
				jQuery.each(zcxm,function(i,n){
					var checked = jQuery(n).prop("checked");
					var v = jQuery(n).val();
					var xmfsJson = {
			 				label:jQuery(n).attr("xmmc"),
			 				name:v,
			 				index:v,
			 				hidden:(!checked)
			 		};
					colList.push(xmfsJson);
				});
	
				colList.push({label:'审核时间',name:'shsj', index: 'shsj',width:'13%'});
				colList.push({label:'审核状态',name:'shztmc', index: 'shzt',width:'9%'});
				if(jQuery("#xxdm").val() == "13627"){
					colList.push({label:'辅导员',name:'fdyxm', index: 'fdyxm',width:'8%'});
				}
				return {
					caption:"资助审核列表",
					pager:"pager",
					url:"xszz_sqsh.do?method=xmshManage&type=query",
					colList:colList,
					params:{shzt:"ysh"},//已审核
					sortname: "shsj",
				 	sortorder: "desc"
				};
			}

			//装载cookie
			function loadCookie(){
				var indexStr = jQuery.cookie("xszzSqshGrid");
				if(indexStr != null && indexStr != undefined){
					var indexArray = indexStr.split("-");
					
					jQuery.each(indexArray,function(i,n){
						
						if (n != ""){
							jQuery("[name=zcxm][index="+n+"]").attr("checked",true);
						}
					});
				}
				
			}
			
			//设置cookie
			function setJxshCookie(){
				var chekedZcxm = jQuery("[name=zcxm]:checked:not(:disabled)");
				var indexStr = "";
				
				jQuery.each(chekedZcxm,function(i,n){
					var index = jQuery(n).attr("index");
					indexStr += index+"-";
				});
				jQuery.cookie("xszzSqshGrid",indexStr, { expires: 7});
			}
			
			jQuery(function(){
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var liName = jQuery("#tabUl li.ha").attr("clzt");
					
					if ("dcl" == liName){
						var gridSetting = getDclGird();
						jQuery("#dataTable").initGrid(gridSetting);
					} else {
						var gridSetting = getYclGrid();
						jQuery("#dataTable").initGrid(gridSetting);
					}
				});
				
				loadCookie();
				
				var gridSetting = getDclGird(); 
				var map = getSuperSearch();
				map["shzt"]="dsh";
				gridSetting["params"] = map;
				
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body onunload="setJxshCookie();">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >使用帮助</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						建议先使用高级查询过滤资助项目名称，如果一人多岗，建议批量选择同一岗位的记录进行批量审核。
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="zzxmSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelXmsh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>
						</logic:equal>						
						<li><a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>			
						<li><a href="javascript:void(0);" onclick="zzxmShqk();return false;" 
							   title="点击查看审核情况汇总统计。"
							   class="btn_tj">审核统计</a></li>
							   
						<li>
							<a href="#" class="btn_dc" onclick="exportConfigSh();return false;">导出</a>
						</li>
						<%--华东理工个性化 --%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">下载申请审批表</a></li>	
						</logic:equal>
						<logic:notEqual value="13431" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">下载登记表</a></li>			
						</logic:notEqual>
								
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tabUl">
			        <li class="ha" clzt="dcl"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li clzt="ycl"><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>资助项目申请列表&nbsp;&nbsp; </span>
			</h3>
			<logic:notEqual name="xxdm" value="10530">	
			<input type="checkbox" xmmc="调整后项目" value="tzxmmc" name="zcxm" index="0" />调整后项目
			<input type="checkbox" xmmc="调整后金额" value="tzxmje" name="zcxm" index="1" />调整后金额
			</logic:notEqual>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

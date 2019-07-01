<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			var gridSetting;
			var gridSetting2;
			jQuery(function(){
				gridSetting = {
						caption:"周报待审核",
						pager:"pager",
						url:"rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&type=query&gzzblx=bj",
						colList:[
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
						   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
						   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
						   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
						   {label:'填写人用户名',name:'lrr', index: 'lrr',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'学期',name:'xqmc', index: 'xqmc',width:'7%'},
						   {label:'周次',name:'zcmc', index: 'zcmc',width:'7%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'13%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'13%'},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'13%',formatter:bjmcLink},
						   {label:'应到/实到/请假/未到<br/>（人）',name:'rstj', index: 'rstj',width:'9%'},
						   {label:'填写时间',name:'lrsj', index: 'lrsj',width:'9%'},
						   {label:'填写人',name:'lrrxm', index: 'lrrxm',width:'9%'},
						   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"dsh"},
						sortname: "lrsj",
					 	sortorder: "desc",
					 	radioselect:false
				};
				gridSetting2 = {
						caption:"周报已审核",
						pager:"pager",
						url:"rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&type=query&gzzblx=bj",
						colList:[
							{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
						   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
						   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
						   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
						   {label:'填写人用户名',name:'lrr', index: 'lrr',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'学期',name:'xqmc', index: 'xqmc',width:'7%'},
						   {label:'周次',name:'zcmc', index: 'zcmc',width:'7%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'13%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'13%'},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'13%',formatter:bjmcLink},
						   {label:'应到/实到/请假/未到<br/>（人）',name:'rstj', index: 'rstj',width:'9%'},
						   {label:'填写时间',name:'lrsj', index: 'lrsj',width:'9%'},
						   {label:'填写人',name:'lrrxm', index: 'lrrxm',width:'9%'},
						   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"ysh"},
						sortname: "lrsj",
					 	sortorder: "desc",
					 	radioselect:true
				};
				var searchJson = getSuperSearch();
				searchJson.shzt = "dsh";
				gridSetting["params"] = searchJson;
				jQuery("#dataTable").initGrid(gridSetting);
			});
				
			function searchRs(){
				var map = getSuperSearch();
				var shzt = jQuery("#shzt").val();
				if (shzt != ""){
					map["shzt"] = shzt;
				}
				jQuery("#dataTable").reloadGrid(map);
			}
				
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);
				var map = getSuperSearch();
				map["shzt"] = shzt;
				if (shzt == "dsh"){
					jQuery("#li_sh").css("display","");
					jQuery("#li_qx").css("display","none");
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
				} else {			
					jQuery("#li_sh").css("display","none");
					jQuery("#li_qx").css("display","");
					gridSetting2["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}
			// 审核
			function xsgzzbSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = jQuery("#shzt").val();
				if(shzt=="ysh"){
					showAlertDivLayer("已处理记录不能再次审核");
					return false;
				} else if(rows.length == 0){
					showAlertDivLayer("请选择一条您要审核的记录！");
					return false;
				} else if (rows.length == 1){
					var url = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbDgsh&gzzblx=bj&sqid="+rows[0]["sqid"]+'&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"] ;
					showDialog("周报审核",700,495,url);
				} else{
					showDialog("周报批量审核",500,220,"rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbPlsh&gzzblx=bj");
				} 
			}
	
			// 批量审核保存
			function savePlsh(shzt,shyj){
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var splc = new Array();
				var gwid = new Array();
				var lrr = new Array();
				jQuery.each(rows,function(i,row){
					guid.push(row["sqid"]);
					splc.push(row["splc"]);
					gwid.push(row["gwid"]);
					lrr.push(row["lrr"]);
				});
				jQuery.post("rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbPlsh&type=save&gzzblx=bj",
					{
					 id:guid,
					 splcs:splc,
					 gwids:gwid,
					 lrrs:lrr,
					 shzt:shzt,
					 shyj:shyj
					},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					}, 'json');
			}
	
			function xsgzzbshLcinfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer("请选择一条流程跟踪记录！");
				} else {		
					showDialog("周报审批流程跟踪",550,450,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
	
			function cancelShnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要撤消的审核记录！");
				} else {
					var splc = rows[0]["splc"];
					var shid = rows[0]["shid"];
					var sqid = rows[0]["sqid"];
					showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
							// 判断是否最后一级撤销(1:最后一级撤销成功）
							if("1" == data["cancelFlg"]){
								jQuery.post("rcsw_xsgzzb_xsgzzbshgl.do?method=cancelXsgzzbsh&gzzblx=bj",{shlc:splc,shid:shid,sqid:sqid},function(result){
									showAlertDivLayer(result["message"],{},{"clkFun":function(){
										jQuery("#dataTable").reloadGrid();
									}});
								},'json');
							}else{
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							}
					},'json');
					}});
				}
			}
	
			function viewXsgzzbsh(sqid) {
				showDialog("周报查看", 700, 445, "rcsw_xsgzzb_xsgzzbsqgl.do?method=viewXsgzzbsq&gzzblx=bj&sqid=" + sqid);
			}

			function bjmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewXsgzzbsh(\""
						+ rowObject["sqid"] + "\");'>" + cellValue
						+ "</a>";
			}
	
			var DCCLBH = "rcsw_bjgzzb_bjgzzbsh.do";//dcclbh,导出功能编号
	
			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, xsgzzbshExportData);
			}
	
			// 导出方法
			function xsgzzbshExportData() {
				var shlx = jQuery("#shzt").val();
				setSearchTj();//设置高级查询条件
				var url = "rcsw_xsgzzb_xsgzzbshgl.do?method=exportData&gzzblx=bj&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body style="min-height: 800px;">
		<html:form action="/rcsw_xsgzzb_xsgzzbshgl">
			<input type="hidden" value="dsh" id="shzt"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title}</a>
				</p>
			</div>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xsgzzbSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="xsgzzbshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<logic:equal name="writeAble" value="yes">	   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>				
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
				<span>周报审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

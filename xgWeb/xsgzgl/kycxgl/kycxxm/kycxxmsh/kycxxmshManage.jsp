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
						caption:"科研项目审核列表",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmshManage&type=query",
						colList:[
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
						   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
						   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'科研类别代码',name:'lbdm', index: 'lbdm',hidden:true},
						   {label:'操作人',name:'czr', index: 'czr',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'科研项目名称',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'科研类别',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'申请时间',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'项目申请人',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'指导老师',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"dsh"},
						sortname: "xmsqsj",
					 	sortorder: "desc",
					 	radioselect:false
				};
				gridSetting2 = {
						caption:"科研项目审核列表",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmshManage&type=query",
						colList:[
							{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
						   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
						   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'科研类别代码',name:'lbdm', index: 'lbdm',hidden:true},
						   {label:'操作人',name:'czr', index: 'czr',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'科研项目名称',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'科研类别',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'申请时间',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'项目申请人',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'指导老师',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"ysh"},
						sortname: "xmsqsj",
					 	sortorder: "desc",
					 	radioselect:true
				};
				var searchJson = getSuperSearch();
				searchJson.shzt = "dsh";
				gridSetting["params"] = searchJson;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function viewKycxxmsh(sqid) {
				showDialog("科研项目查看", 727,460, "kycxgl_kycxxm_kycxxmsqgl.do?method=viewKycxxmsq&sqid=" + sqid);
			}
			function xmmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewKycxxmsh(\"" + rowObject["sqid"] + "\");'>" + cellValue + "</a>";
			}
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
			function kycxxmSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = jQuery("#shzt").val();
				if(shzt=="ysh"){
					showAlertDivLayer("已处理记录不能再次审核！");
					return false;
				} else if(rows.length == 0){
					showAlertDivLayer("请选择一条您要审核的记录！");
					return false;
				} else if (rows.length == 1){
					var url = "kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmDgsh&sqid="+rows[0]["sqid"]+'&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"] ;
					showDialog("科研项目审核",727,495,url);
				} else{
					showDialog("科研项目批量审核",530,255,"kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmPlsh");
				} 
			}
			function savePlsh(shzt,shyj,xbjf){
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var splc = new Array();
				var gwid = new Array();
				var czr = new Array();
				jQuery.each(rows,function(i,row){
					guid.push(row["sqid"]);
					splc.push(row["splc"]);
					gwid.push(row["gwid"]);
					czr.push(row["czr"]);
				});
				jQuery.post("kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmPlsh&type=save",
					{
					 id:guid,
					 splcs:splc,
					 gwids:gwid,
					 czrs:czr,
					 shzt:shzt,
					 shyj:shyj,
					 xbjf:xbjf
					},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					}, 'json');
			}
			function kycxxmshLcinfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer(jQuery("#lable_one_lcgz").val());
				} else {		
					showDialog("审批流程跟踪",550,450,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
			function cancelShnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要撤销的审核记录！");
				} else {
					var splc = rows[0]["splc"];
					var shid = rows[0]["shid"];
					var sqid = rows[0]["sqid"];
					showConfirmDivLayer("您确定要撤销对该记录的审核操作吗？",{"okFun":function(){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
							// 判断是否最后一级撤销(1:最后一级撤销成功）
							if("1" == data["cancelFlg"]){
								jQuery.post("kycxgl_kycxxm_kycxxmshgl.do?method=cancelKycxxmsh",{shlc:splc,shid:shid,sqid:sqid},function(result){
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
		</script>
	</head>
	<body style="min-height: 800px;">
		<html:form action="/kycxgl_kycxxm_kycxxmshgl">
			<input type="hidden" value="dsh" id="shzt"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title}</a>
				</p>
			</div>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="kycxxmSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="选中一条记录，点击该按钮您可以撤销失误的审核操作。"
							   class="btn_qxsh">撤销</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="kycxxmshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
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
				<span>科研项目审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

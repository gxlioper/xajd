<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var gridSetting = {
					caption:"审核列表",
					pager:"pager",
					url:"qgzx_cjffsq_ajax.do?method=cjxxSh",
					colList : [
								{ label : 'pkvalue', name : 'pkvalue', index : 'pkvalue',hidden : true },
								{ label : 'sqid', name : 'sqid', index : 'sqid',hidden : true },
								{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
								{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
								<logic:equal name="qgzq" value="xq">
								{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
								</logic:equal>		
								{ label : '用人单位名称', name : 'yrdwmc', index : 'yrdwmc', width : '15%' },
								{ label : '发放年月', name : 'ffny', index : 'ffny', width : '5%' },
								{ label : '研究生（个）', name : 'yjsrs', index : 'yjsrs', width : '8%' },
								{ label : '本科生（个）', name : 'bksrs', index : 'bksrs', width : '8%' },
								{ label : '发放金额<元>', name : 'ffje', index : 'ffje', width : '10%' },
								{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '16%' },
								{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
								{ label : '审核Id', name : 'shid', index : 'shid', hidden : true},
								{ label : '岗位Id', name : 'gwid', index : 'gwid', hidden : true}
								],
					params:{shzt:"dsh"},
					sortname: "ffny",
				 	sortorder: "desc",
				 	radioselect:false
			};
			gridSetting2 = {
					caption:"审核列表",
					pager:"pager",
					url:"qgzx_cjffsq_ajax.do?method=cjxxSh",
					colList : [
								{ label : 'pkvalue', name : 'pkvalue', index : 'pkvalue',hidden : true },
								{ label : 'sqid', name : 'sqid', index : 'sqid',hidden : true },
								{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
								{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
								<logic:equal name="qgzq" value="xq">
								{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
								</logic:equal>		
								{ label : '用人单位名称', name : 'yrdwmc', index : 'yrdwmc', width : '15%' },
								{ label : '发放年月', name : 'ffny', index : 'ffny', width : '5%' },
                                { label : '研究生（个）', name : 'yjsrs', index : 'yjsrs', width : '8%' },
                                { label : '本科生（个）', name : 'bksrs', index : 'bksrs', width : '8%' },
								{ label : '发放金额<元>', name : 'ffje', index : 'ffje', width : '10%' },
								{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '16%' },
								{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
								{ label : '审核Id', name : 'shid', index : 'shid', hidden : true},
								{ label : '岗位Id', name : 'gwid', index : 'gwid', hidden : true}
								],
					params:{shzt:"dsh"},
					sortname: "ffny",
				 	sortorder: "desc",
				 	radioselect:false
			}	

			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
		function searchRs() {
			var map = getSuperSearch();
			var shzt = jQuery("#shzt").val();
			if (null!=shzt&&shzt != "") {
				map["shzt"] = shzt;
			}else{
				map["shzt"] = "dsh";
			}
			jQuery("#dataTable").reloadGrid(map);
		}
		
		function selectTab(obj, shzt) {
			jQuery("#shzt").val(shzt);
			if (shzt == "dsh") {
				jQuery("#li_sh").css("display", "");
				jQuery("#li_qx").css("display", "none");
				var map = getSuperSearch();
				map["shzt"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			} else {
				jQuery("#li_sh").css("display", "none");
				jQuery("#li_qx").css("display", "");
				var map = getSuperSearch();
				map["shzt"]="ysh";
				gridSetting2["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
		}
		
		function showView(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请勾选一条需要查看的数据")
				return false;
			}
			var url="qgzx_cjffsq.do?method=cjxxCk&pkValue="+rows[0]["pkvalue"]+"&shck=1&sqid="+rows[0]['sqid'];
			showDialog('', 900, 525, url);
		}
		
		function sbsh() {
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
				var url = "qgzx_cjffsq.do?method=cjxxShCk&pkValue=" + rows[0]["pkvalue"] + '&shid=' + rows[0]["shid"];
				showDialog("审核", 900, 525, url);
			} else {
				showAlertDivLayer("请勾选一条需要审核的数据");
				//showDialog("批量审核", 500, 250, "qgzx_cjffsq.do?method=cjxxPlsh");
			}
		}
		
		function savePlsh(shjg, shyj) {
			var rows = jQuery("#dataTable").getSeletRow();
			var sqid = new Array();
			var gwid = new Array();
			var splc = new Array();
			jQuery.each(rows, function(i, row) {
				sqid.push(row["sqid"]);
				gwid.push(row["gwid"]);
				splc.push(row["splc"]);
			});
			jQuery.post("qgzx_cjffsq_ajax.do?method=sbPlsh&type=save", {
				shjg : shjg,
				sqid : sqid,
				gwids : gwid,
				shyj:shyj,
			}, function(data) {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}, 'json');
		}
		
		function splcInfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (1!=ids.length){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {		
				showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
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
					jQuery.post("qgzx_cjffsq_ajax.do?method=cxsh",{splc:splc,shid:shid,sqid:sqid,shzt:shzt},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
				},'json');
				}});
			}
		}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_cjffsq">
			<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sbsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cxsh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<li>
							<a href="#" onclick="showView();" class="btn_ck">查看明细</a>
						</li>
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
				<span>审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

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
			jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);
			});
			var gridSetting = {
			caption:"社团管理未审核列表",
			pager:"pager",
			url:"ttgl_stglsh.do?method=stglShList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'转正业务ID',name:'zzywid', index: 'zzywid',hidden:true},
			   {label:'审核类型',name:'shlx', index: 'shlx',hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',hidden:true},
			   {label:'团体名称',name:'stqc', index: 'stqc',width:'8%',formatter:stLink},
			   {label:'团体类型',name:'stlx', index: 'stlx',width:'8%'},
			   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'8%'},
			   {label:'状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
			   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
			   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
			   {label:'审批流程',name:'splc',index:'splc',hidden:true}
			],
			params:{shzt:"dsh"},//默认待审核
		 	radioselect:false
}
var gridSetting2 = {
		caption:"社团管理已审核列表",
		pager:"pager",
		url:"ttgl_stglsh.do?method=stglShList&type=query",
		colList:[
		       {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
		       {label:'转正业务ID',name:'zzywid', index: 'zzywid',hidden:true},
		       {label:'审核类型',name:'shlx', index: 'shlx',hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',hidden:true},
			   {label:'团体名称',name:'stqc', index: 'stqc',width:'8%',formatter:stLink},
			   {label:'团体类型',name:'stlx', index: 'stlx',width:'8%'},
			   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'8%'},
			   {label:'状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
			   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
			   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
			   {label:'审批流程',name:'splc',index:'splc',hidden:true}
		],
		params:{shzt:"ysh"},//默认待审核
	 	radioselect:true
}
	
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	

//点击学号查看
function view(sqid) {
    var height = jQuery(window).height()-210;
	showDialog("查看社团信息", 790,height, "ttgl_stglsh.do?method=view&sqid=" + sqid);
}
function stLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\");'>" + cellValue
			+ "</a>";
}

//审核
function Sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
    var height = jQuery(window).height()-210;
	if(shzt=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}else if (rows.length == 0){
		showAlertDivLayer("请选择您要审核的记录");
		return false;
	}else if (rows.length == 1){
		if(rows[0]['shlx']==0){
			var url = "ttgl_stglsh.do?method=stglDgsh&sqid="+rows[0]["sqid"]+'&shid=' +rows[0]["shid"];
			showDialog("审核社团申请信息",700,height,url);
		}else{
			var url = "ttgl_stglsh.do?method=stzzDgsh&sqid="+rows[0]["sqid"]+'&zzywid='+rows[0]["zzywid"]+'&shid=' +rows[0]["shid"];
			showDialog("审核社团转正信息",700,height,url);
		}
	} else{
		showDialog("批量审核",500,300,"ttgl_stglsh.do?method=stglPlsh");
	} 
}

//撤销审核
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var zzywid = rows[0]["zzywid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					if(rows[0]['shlx']==0){
						jQuery.post("ttgl_stglsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
							showAlertDivLayer(result["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						},'json');
					}else{
						jQuery.post("ttgl_stglsh.do?method=cancelZzSh",{sqid:sqid,zzywid:zzywid,shzt:shzt},function(result){
							showAlertDivLayer(result["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						},'json');
					
					}
					
				}else{
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}
		},'json');
		}});
	}
}

//批量审核
function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var sqid = new Array();
	var zzywid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	var shlx = new Array();
	jQuery.each(rows,function(i,row){
		sqid.push(row["sqid"]);
		zzywid.push(row["zzywid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
		shlx.push(row["shlx"]);
	});

	jQuery.post(
		"ttgl_stglsh.do?method=stglPlsh&type=save",
		{
		 shzt:shzt,
		 id:sqid,
		 zzid:zzywid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 splcs:splc,
		 shlxs:shlx
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}







//切换lab
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	var xxdm = jQuery("#xxdm").val();
	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
			jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}




function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {	
		if(rows[0]['shlx']==0){
			showDialog("社团申请审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
		}else{
			showDialog("社团转正审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['zzywid']+"&splc="+rows[0]['splc']);
		}	
	}
}



var DCCLBH = "xg_ttgl_stglsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "ttgl_stglsh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
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
		<html:form action="/ttgl_stglsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="Sh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="shLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>			
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
				<span>读书管理审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

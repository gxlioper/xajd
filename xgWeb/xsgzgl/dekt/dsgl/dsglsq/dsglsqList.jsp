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
	jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#li_sq").css("display","");
		jQuery("#li_xg").css("display","none");
		jQuery("#li_sc").css("display","none");
		jQuery("#li_tj").css("display","none");
		jQuery("#li_cx").css("display","none");
		jQuery("#li_lcgz").css("display","none");
		jQuery("#li_dc").css("display","none");
});
var gridSetting = {
			caption:"未读书单列表",
			pager:"pager",
			url:"dekt_dsglsq.do?method=dsglSqList&type=query",
			colList:[
			   {label:'key',name:'smid', index: 'smid',key:true ,hidden:true},
			   {label:'年级',name:'nj', index: 'nj',width:'8%'},
			   {label:'书名ID',name:'smid', index: 'smid',hidden:true},
			   {label:'书名',name:'ssm', index: 'ssm',width:'10%',formatter:smLink},
			   {label:'出版社',name:'cbs', index: 'cbs',width:'15%'},
			   {label:'作者',name:'author', index: 'author',width:'8%'},
			   {label:'类型',name:'lx', index: 'lx',width:'8%'},
			   {label:'电子书链接',name:'ebook', index: 'ebook',width:'15%',formatter:ebookLink}
			],
			params:{ydzt:"wdsm"},//默认未读
		 	radioselect:false
		}
var gridSetting2 = {
			caption:"已读书单列表",
			pager:"pager",
			url:"dekt_dsglsq.do?method=dsglSqList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'学院',name:'xymc', index: 'xymc',width:'15%'},
			   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
			   {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
			   {label:'书名',name:'ssm', index: 'ssm',width:'15%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true}
			],
			params:{ydzt:"ydsm"},//已读
		 	radioselect:false
		}
	
function searchRs(){
	var map = getSuperSearch();
	var ydzt = jQuery("#ydzt").val();
	if (ydzt != ""){
		map["ydzt"] = ydzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}

//切换lab
function selectTab(obj,ydzt){
jQuery("#ydzt").val(ydzt);
	if (ydzt == "wdsm"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		
		
		jQuery("#li_sq").css("display","");
		jQuery("#li_xg").css("display","none");
		jQuery("#li_sc").css("display","none");
		jQuery("#li_tj").css("display","none");
		jQuery("#li_cx").css("display","none");
		jQuery("#li_lcgz").css("display","none");
		jQuery("#li_dc").css("display","none");
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#li_sq").css("display","none");
		jQuery("#li_xg").css("display","");
		jQuery("#li_sc").css("display","");
		jQuery("#li_tj").css("display","");
		jQuery("#li_cx").css("display","");
		jQuery("#li_lcgz").css("display","");
		jQuery("#li_dc").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function viewsm(smid) {
	showDialog("查看书本信息",700,300, "dekt_smwhgl.do?method=view&smid=" + smid);
}

function smLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewsm(\""
			+ rowObject["smid"] + "\");'>" + cellValue
			+ "</a>";
}

function view(sqid,xh) {
	showDialog("查看读书信息", 720, 520, "dekt_dsglsq.do?method=view&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function ebookLink(cellValue, rowObject) {
return "<a href='javascript:void(0);' class='name' onclick='ebook(\""
			+ rowObject["ebook"] + "\");'>" + cellValue
			+ "</a>";
}
function ebook (url){
window.open(url);
}
function add(){
	var datasum =  jQuery("#rowConut").text();
	if(datasum==0){
			var url = "dekt_dsglsq.do?method=add";
			var title = "增加读书信息";
			showDialog(title,790,550,url);
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if(rows.length == 1){
				var url = "dekt_dsglsq.do?method=add&ssm="+ encodeURI(encodeURI(rows[0]["ssm"]));
				var title = "增加读书信息";
				showDialog(title,790,550,url);
		}else{
				showAlertDivLayer("请选择一条记录！");
		}
	}

}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'dekt_dsglsq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("修改读书信息", 790,550, url);
		}else{
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
	}

}


//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("dekt_dsglsq.do?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的申请已经提交不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function submit(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		var url = "dekt_dsglsq.do?method=submit";
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			jQuery.post(url,
				{
				 values:ids.toString(),
				 xh : rows[0]['xh'],
				 splc : rows[0]['splc'],
				 shzt : rows[0]['shzt']
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancel(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
			jQuery.post("dekt_dsglsq.do?method=cancel",
				{
				 values:ids.toString(),
				 splcid : rows[0]['splc'] 
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function Lcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {	
		var shzt = rows[0]["shzt"];
		if ("0" == shzt){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}	
		showDialog("读书申请审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "xg_dekt_dsglsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var url = "dekt_dsglsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
		
</script>
	</head>
	<body>
	<input type="hidden" value="wdsm" id="ydzt"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_dsglsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sq">
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">申请</a>
						</li>
						<li id="li_xg">
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li id="li_sc">
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li id="li_tj">
							<a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">提交</a>
						</li>
						<li id="li_cx">
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li id="li_lcgz">
							<a href="javascript:void(0);" onclick="Lcinfo();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>				
						<li id="li_dc"><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wdsm');"><span>未读书单</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ydsm');"><span>已读书单</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>读书信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

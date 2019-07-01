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
					jQuery("#dataTable").initGrid(gridSetting3);
					jQuery("#li_qx").css("display","none");
			});
			var gridSetting = {
			caption:"待评价列表",
			pager:"pager",
			url:"dekt_jspjglsq.do?method=jspjSqList&type=query",
			colList:[
			   {label:'职工号',name:'zgh', index: 'zgh',width:'10%'},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'所在部门',name:'bmmc', index: 'bmmc',width:'15%'},
			   {label:'教师类别',name:'jslbmc', index: 'jslbmc',width:'15%'},
			   {label:'预约状态',name:'yyzt', index: 'yyzt',width:'15%'},
			   {label:'是否需预约字段',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'学生是否预约老师',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'教师是否同意',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'},
			   {label:'操作',name:'', index: '',width:'12%',formatter:function(cellValue,rowObject){
                   return "<button type='button' onclick='add(\""+rowObject["zgh"]+ "\",\"1\",\""+rowObject["xm"]+ "\");'>评价</button>";
			   }}
			],
			params:{pjlx:"dpj"},//默认待评价
		 	radioselect:false
}
var gridSetting2 = {
		caption:"已评价列表",
		pager:"pager",
		url:"dekt_jspjglsq.do?method=jspjSqList&type=query",
		colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'评价人学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'评价人姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'被评价老师职工号',name:'pjjszgh', index: 'pjjszgh',hidden:true},
			   {label:'被评价老师',name:'pjjsxm', index: 'pjjsxm',width:'8%'},
			   {label:'教师所属部门',name:'bmmc', index: 'bmmc',width:'8%'},
			   {label:'教师类别',name:'jslbmc', index: 'jslbmc',width:'8%'},
			   {label:'评价时间',name:'xpjsj', index: 'xpjsj',width:'15%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true}
			],
		params:{pjlx:"ypj"},
	 	radioselect:true
}

			var gridSetting3 = {
			caption:"待预约列表",
			pager:"pager",
			url:"dekt_jspjglsq.do?method=jspjSqList&type=query",
			colList:[
			   {label:'职工号',name:'zgh', index: 'zgh',width:'10%'},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'所在部门',name:'bmmc', index: 'bmmc',width:'15%'},
			   {label:'教师类别',name:'jslbmc', index: 'jslbmc',width:'15%'},
			   {label:'预约状态',name:'yyzt', index: 'yyzt',width:'15%'},
			   {label:'是否需预约字段',name:'zzshen', index: 'zzshen',hidden:true,width:'15%'},
			   {label:'学生是否预约老师',name:'xssfyy', index: 'xssfyy',hidden:true,width:'15%'},
			   {label:'教师是否同意',name:'jssfty', index: 'jssfty',hidden:true,width:'15%'},
			   {label:'操作',name:'', index: '',width:'12%',formatter:function(cellValue,rowObject){
			       if(rowObject["yyzt"] == '是(已预约)'){
                       return "<button id='\""+rowObject["zgh"]+ "\"' type='button' onclick='deladd(\""+rowObject["zgh"]+ "\",\"1\",\""+rowObject["xm"]+ "\",this,\""+rowObject["zzshen"]+ "\");'>取消预约</button>";
				   } else {
                       return "<button id='\""+rowObject["zgh"]+ "\"' type='button' onclick='addyy(\""+rowObject["zgh"]+ "\",\""+rowObject["xm"]+ "\",\""+rowObject["zzshen"]+ "\");'>预约</button>";
				   }
			   }}
			],
			params:{pjlx:"dyy"},//默认待评价
		 	radioselect:false
	}
	
function searchRs(){
	var map = getSuperSearch();
	var pjlx = jQuery("#pjlx").val();
	if (pjlx != ""){
		map["pjlx"] = pjlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}

function addyy (zgh,xm,zzshen) {
	showDialog("学生预约信息",650,200, "dekt_jspjglsq.do?method=jspjyy_10698&zgh=" + zgh+ "&xm=" + xm + "&zzshen=" + zzshen);
}
			
//点击学号查看
function view(sqid, xh) {
	showDialog("查看评价信息",700,350, "dekt_jspjglsq.do?method=view&sqid=" + sqid+ "&xh=" + xh);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//function addyy(zgh,lx,xm,obj,zzshen){
//	var url = "dekt_jspjglsq.do?method=addyy&pjjszgh="+zgh+"&lx="+lx+"&pjjsxm="+encodeURI(encodeURI(xm))+"&zzshen="+zzshen;
//	showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
//		jQuery.post(url,
//			function(data){
//			showAlertDivLayer(data["message"]);
//			jQuery("#dataTable").reloadGrid();
//		},'json');
//	}});
	//alert(zgh); 
	//document.getElementById(zgh).disabled = true;
	//alert(jQuery(this).html());
	///alert(jQuery("#"+zgh).text());
	//alert(jQuery(this).text());
	//alert(jQuery(obj).text());
	//jQuery(obj).prop("disabled",true);
//}

function deladd(zgh,lx,xm,obj,zzshen){
	var url = "dekt_jspjglsq.do?method=deladd&pjjszgh="+zgh+"&lx="+lx+"&pjjsxm="+encodeURI(encodeURI(xm))+"&zzshen="+zzshen;
	showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
		jQuery.post(url,
			function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	//alert(zgh); 
	//document.getElementById(zgh).disabled = true;
	//alert(jQuery(this).html());
	///alert(jQuery("#"+zgh).text());
	//alert(jQuery(this).text());
	//alert(jQuery(obj).text());
	//jQuery(obj).prop("disabled",true);
}

function add(zgh,lx,xm){
	var url = "dekt_jspjglsq.do?method=add&pjjszgh="+zgh+"&lx="+lx+"&pjjsxm="+encodeURI(encodeURI(xm));
	var title = "增加评价信息";
	showDialog(title,700,350,url);
	
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'dekt_jspjglsq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("修改评价信息", 700,350, url);
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
				jQuery.post("dekt_jspjglsq.do?method=del", {
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
		var url = "dekt_jspjglsq.do?method=submit";
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
			jQuery.post("dekt_jspjglsq.do?method=cancel",
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
		showDialog("教师评价审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}








//切换lab
function selectTab(obj,pjlx){
	jQuery("#pjlx").val(pjlx);
	var xxdm = jQuery("#xxdm").val();
	if (pjlx == "dpj"){
		jQuery("#li_qx").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting);
	} else if (pjlx == "ypj"){			
		jQuery("#li_qx").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}else {
		jQuery("#li_qx").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting3);
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
		showDialog("读书申请审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}



var DCCLBH = "xg_dekt_dsglsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}


		</script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" value="dyy" id="pjlx"/>
		<input type="hidden" value="${zzshen}" id="zzshen"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_jspjglsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes"><!--	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add('','0','');return false;">申请</a>
						</li>
						--><div id="li_qx">
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="Lcinfo();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						</div>			
						</logic:equal>
						<!--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					--></ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			     	<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dyy');"><span>待预约</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'dpj');"><span>待评价</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ypj');"><span>已评价</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>教师评价列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>

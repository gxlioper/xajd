var action="jtpjsh.do";
var gridSetting = {
	caption:"集体评奖待审核列表",
	pager:"pager",
	url:"jtpjsh.do?method=list&type=query",
	colList:[
	   {label:'奖项申请id',name:'sqid', index: 'sqid',key:true,hidden:true},
	   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
	   {label:'申请集体 ',name:'pjjtmc', index: 'pjjtmc',formatter:pjjtmc},
	   {label:'人数',name:'rs', index: 'rs'},
	   {label:'申请奖项名称',name:'jxmc', index: 'jxmc'},
	   {label:'评定学年',name:'pdxn', index: 'pdxn',hidden:true},
	   {label:'评定学期',name:'pdxqmc', index: 'pdxqmc',hidden:true},
	   {label:'奖项评定周期',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
	   {label:'申请时间',name:'sqsj', index: 'sqsj'},
	   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
	   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
	   {label:'审核状态',name:'shztmc', index: 'shzt',formatter:shzt},
	   {label:'审核人',name:'shr', index: 'shr',hidden:true},
	   {label:'审核人名称',name:'mc', index: 'mc',hidden:true},
	   {label:'shid',name:'shid', index: 'shid',hidden:true}
			],
	sortname: "sqsj",
 	sortorder: "asc"
}
function pjjtmc(cellValue, rowObject) {
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["sqid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(sqid) {
	var url = action + "?method=showView&sqid=" + sqid;
	showDialog("奖项信息", 800, 500, url);
}

function jxpdzq(cellValue, rowObject) {
	var pdxq=rowObject["pdxqmc"];
	if(!pdxq){
		pdxq="";
	}
	return rowObject["pdxn"] + pdxq;
}
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var mc = rowObject["mc"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "通过";
		break;
	case "2":
		shztmc = "不通过";
		break;
	case "3":
		shztmc = "已退回";
		break;
	case "5":
		shztmc = "审核中";
		break;
	default:
		shztmc = "待审核";
		break;
	}
	return mc+"["+shztmc+"]";
}
//切换待处理/已处理页面
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("集体评奖待审核列表");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		jQuery("#dataTable").reloadGrid(map);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("集体评奖已审核列表");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		jQuery("#dataTable").reloadGrid(map);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
//审核
function jtpjsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm=jQuery("#xxdm").val();
	if (rows.length != 1) {
		if("10704"==xxdm){
			showAlertDivLayer("请选择一条您要审核的记录！");
		}else if (rows.length== 0) {
			showAlertDivLayer("请选择您要审核的记录！");
		}else{
			showDialog("批量审核",500,250,"jtpjsh.do?method=jtpjPlsh");
		}
	} else {
		var xh = rows[0]["xh"];
		var url = action + '?method=jtpjsh&sqid='
		+ rows[0]["sqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("集体评奖审核",800,500,url);
	}
}
function save_sh(){
	var shzt=jQuery("#shjg").val();
	jQuery("#shzt").val(shzt);
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	var text=jQuery("#shjg").find("option:selected").text();
	//提交审核
	showConfirmDivLayer("您确定<font color='red'>[" + text + "]</font>该申请吗？",{"okFun":function(){
			zx(shzt,text);
	}});
	
}
function zx(shzt,text){
	var url = "jtpjsh.do?method=jtpjsh&type=save&tt="+new Date();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "保存成功！") {
				showAlert("<font color='red'>["+text+"]</font>操作成功！", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				showAlert("<font color='red'>["+text+"]</font>操作失败！");
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
}
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {
		showDialog("集体评奖审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}
function reloadsh(){
	jQuery("#dataTable").reloadGrid();
}

function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var sqids = new Array();
	var gwids = new Array();
	var splcs = new Array();
	var pjjtmcs=new Array();
	jQuery.each(rows,function(i,row){
		sqids.push(row["sqid"]);
		gwids.push(row["gwid"]);
		splcs.push(row["splcid"]);
		pjjtmcs.push(row["pjjtmc"]);
	});
	jQuery.post(
		"jtpjsh.do?method=jtpjPlsh&type=save",
		{
			sqids:sqids,
			gwids:gwids,
			splcs:splcs,
			pjjtmcs:pjjtmcs,
			shzt:shzt,
			shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

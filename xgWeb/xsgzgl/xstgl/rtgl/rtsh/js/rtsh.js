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

//流程跟踪
function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['rtid']+"&splc="+rows[0]['splc']);
	}
}

//单个审核
function rtsh() {
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
		var url = "stglRtsh.do?method=Rtdgsh&rtid=" + rows[0]["rtid"] + '&xh='
				+ rows[0]["xh"] + '&shid=' + rows[0]["shid"];
		showDialog("审核", 770, 535, url);
	} else {
		showDialog("批量审核", 500, 250, "stglRtsh.do?method=Rtplsh");
	}
}
// 批量审核
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcs = new Array();
	var stids = new Array();

	jQuery.each(rows, function(i, row) {
		guid.push(row["rtid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcs.push(row["splc"]);
		stids.push(row["stid"]);
	});
	jQuery.post("stglRtsh.do?method=Rtplsh&type=save", {
		shzt : shzt,
		id : guid,
		gwids : gwid,
		xhs : xhs,
		shyj : shyj,
		splcs :splcs,
		stids : stids
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//审核撤销
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["rtid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("stglRtsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("stglRtsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtsjhView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function RtsjhView(rtid, xh) {
	showDialog("查看", 770, 510, "stglRtsh.do?method=RtshView&rtid="
			+ rtid + "&xh=" + xh);
}

//toggle收起展开
function showPfzmx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	jQuery(obj).attr("class",newClass);
	jQuery("#tbody_toggle").toggle();
    //指导教师信息
    if(className=='down')
    {
        document.getElementById("zdlsthead").style.display = "";
        document.getElementById("zdlstbody").style.display = "";
    }
    else{
        document.getElementById("zdlsthead").style.display = "none";
        document.getElementById("zdlstbody").style.display = "none";
    }
    //异步拼表格
    var stid = jQuery("#stid").val();
    jQuery('.aa').remove();
    jQuery.post("stglRtsq.do?method=getZdlsInfo",{"stid":stid},function(data){
        var liHtml="";
        for (var i = 0 ; i < data.length ; i++){
            liHtml+="<tr class='aa'>";
            liHtml += "<td><label name = 'xm'>"+data[i]["xm"]+"</label></td>";
            liHtml += "<td><label name = 'bmmc'>"+data[i]["bmmc"]+"</label></td>";
            liHtml += "<td><label name = 'lxdh'>"+data[i]["lxdh"]+"</label></td>";
            liHtml += "<td><label name = 'zcmc'>"+data[i]["zcmc"]+"</label></td>";
            liHtml+="</tr>";
        }
        jQuery("#nr").append(liHtml);
    },"json");
}
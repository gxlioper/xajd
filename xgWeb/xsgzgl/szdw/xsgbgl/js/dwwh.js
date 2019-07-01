
function ZwdwwhExportConfig() {
	customExport("szdw_xsgb_dwwh.do", zwdwwhExportData,650,550);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function add(){
	showDialog("增加学生干部队伍",900,550,"szdw_xsgb_dwwh.do?method=dwwhAdd");

}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	}else{
		showDialog("修改学生干部队伍",900,550,"szdw_xsgb_dwwh.do?method=dwwhUpdate&bjdm="+rows[0]["bjdm"]);
	}
}
// 导出方法
function zwdwwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "szdw_xsgb_dwwh.do?method=dwwhExportData&dcclbh=" + "szdw_xsgb_dwwh.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function go_ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要查看的记录！");
	}  else{
		showDialog("学生干部职务查看",760,500,"szdw_xsgb_dwwh.do?method=dwwhView&&bjdm="+rows[0]["bjdm"]);
	}
}
function on_change(){
	jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView&lxdm="+jQuery("#lxdm").val());
}
function on_getZwwh_model(){
	jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView&zwid="+jQuery("#zwid").val());
}
function go_back(){
	refreshForm("szdw_xsgb_dwwh.do?method=dwwhList");
}
function save(method){
    if(jQuery("#bjmc").val() == ""){
        showAlertDivLayer("请选择班级！");
        return false;
    }
    if(jQuery("#bzxh").val() == ""){
        showAlertDivLayer("请选择班长职务人员！");
        return false;
    }
	// if(yanzheng()){
		// jQuery.post("szdw_zwsq.do?method=yzZwsq",{zwid:jQuery("#zwid").val(),xh:jQuery("#xh").val(),type:'yz'},function(data){
		// 	if(data.message!="true"){
		// 		alertInfo(data.message);
		// 	}else{
				//验证成功后才能进行保存
				var url = "szdw_xsgb_dwwh.do?method="+method+"&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					if(data["message"]!="保存成功！"){
						alertInfo(data["message"]);
					}else{
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					}
				});
		// 	}
		// },'json');
	// }
}
function yanzheng(){
	var xh = jQuery("#xh").val();
	var zwid = jQuery("#zwid").val();
	var zzzt =jQuery("input[name='zzzt']:checked").val();
	var rzsj =jQuery("#rzsj").val();
	var lzsj =jQuery("#lzsj").val();
	if(xh=="" || xh == undefined){
		alertInfo("请选择一个学生");
	}else if(zwid=="" || zwid == undefined){
		alertInfo("请选择申请的职务");
	}else if(zzzt=="" || zzzt == undefined){
		alertInfo("请选择在职状态");
	}else if(rzsj=="" || rzsj == undefined){
		alertInfo("请输入任职时间");
	}else{
		if(zzzt==1){
			jQuery("#lzsj").val("");
		}
		if(zzzt==0){
			if(lzsj=="" || lzsj == undefined){
				alertInfo("请输入离职时间");
				return false;
			}else if(lzsj.replace("-","")<rzsj.replace("-","")){
					alertInfo("离职时间不得早于任职时间");
					return false;
			}
		}
		return true;
	}
	return false;
}

//在职时间和离职时间切换
function on_zzxx(){
	var zzzt =jQuery("input[name='zzzt']:checked").val();
	if(zzzt==1){
		jQuery("#lzsj_th").hide();
		jQuery("#lzsj_td").hide();
	}else{
		jQuery("#lzsj_th").show();
		jQuery("#lzsj_td").show();
	}
}
//隐藏职务选择信息
function sjly_disabled(sjly){
		if(sjly==1){
			jQuery("#lxdm").attr("disabled","true");
			jQuery("#zwid").attr("disabled","true");
		}
}
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		// var rows = jQuery("#dataTable").getSeletRow();
		// var check = false;
		/*jQuery(rows).each(function(i){
			var shzt = rows[i]['sjly'];
			if(shzt==1){
				alertInfo("流程数据不能删除");
				return false;
			}else{
				check = true;
			}
		});*/
		// if(check){
			confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
				if(ty=="ok"){
					jQuery.post("szdw_xsgb_dwwh.do?method=dwwhDelete",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
		// }
	}
}
//打印备案表
function printXsgbbab(url){
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				xh +=rows[i]["xh"];
			}else{
				xh +=rows[i]["xh"]+",";
			}
		}
		var url = url + "&xh=" +xh;
		window.open(url);
	}
}
var clickRowId;
function getZwxx(type){
	jQuery.post("szdw_xsgb_dwwh.do?method=getZwxx",{},function(data){
		if(data.length > 0){
			var str = "";
			for(var i=0;i<data.length;i++){
				str+="<tr id='"+i+"'>";
				str+="<th>";
				if("班长"==data[i]["zwmc"]){
					str+= "<span class=\"red\">*</span>";str+=data[i]["zwmc"];
                    str+="<input type='hidden' name='zwids' value='"+data[i]["zwid"]+"'></th>";
                    str+="<td>";
                    str+="<input name=\"xm\" style=\"width:159px;\" readonly=\"readonly\"class=\"text_nor\">";
                    str+="<input type=\"hidden\" name='xhs' id='bzxh'/>";
				} else {
                    str+=data[i]["zwmc"];
                    str+="<input type='hidden' name='zwids' value='"+data[i]["zwid"]+"'></th>";
                    str+="<td>";
                    str+="<input name=\"xm\" style=\"width:159px;\" readonly=\"readonly\"class=\"text_nor\">";
                    str+="<input type=\"hidden\" name='xhs' />";
				}

				if(type != "view"){
                    str+="<button class=\"btn_01\" type=\"button\" onclick=\"selectStu(this);\">选择</button>";
				}
                str+="</td><th>联系电话</th>";
                str+="<td><span id='lxdh'></span></td></tr>";
			}
			jQuery("#tbody_cyxx").append(str);
			if(type=="update" || type=="view"){
                getBgbData();
			}
		}
	},'json');
}
//获取班干部data
function getBgbData(){
    jQuery.post("szdw_xsgb_dwwh.do?method=getBgbData",{bjdm:jQuery("#bjdm").val()},function(data){
        for(var i=0;i<data.length;i++){
        	var tr = jQuery("input[value="+data[i]["zwid"]+"]").parent().parent();
            tr.find("input[name='xm']").val(data[i]["xm"]);
            tr.find("input[name='xhs']").val(data[i]["xh"]);
            tr.find("span[id='lxdh']").html(data[i]["lxdh"]);
		}
	},'json');
}

function selectStu(btn){

	if(jQuery("#bjmc").val() == ""){
        showAlertDivLayer("请选择班级！");
        return false;
	}
    clickRowId = jQuery(btn).parent().parent().attr("id");
	var url = "szdw_xsgb_dwwh.do?method=getXsxxList&bjdm="+jQuery("#bjdm").val();
    showDialog('请选择一个学生',800,500,url);
}
function setXsxxCallBack(row){

	var tr = jQuery("#"+clickRowId);
	tr.find("input[name='xm']").val(row["xm"]);
    tr.find("input[name='xhs']").val(row["xh"]);
    tr.find("span[id='lxdh']").html(row["lxdh"]);
}
function setBjxxCallBack(row){
    jQuery("#bjmc").val(row[0]['bjmc']);
    jQuery("#bjdm").val(row[0]['bjdm']);
    jQuery("#syTd").html(row[0]['symc']);
    jQuery("#njTd").html(row[0]['nj']);
    jQuery("#fdyTd").html(row[0]['fdyxm']);
    jQuery("#lxdhTd").html(row[0]['lxdhTd']);
}
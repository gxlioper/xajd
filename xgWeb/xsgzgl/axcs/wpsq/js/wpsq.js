// 查询
function searchRs(){
	
	var url = "axcswpsqxs.do?method=wpsqList&type=query";
	jQuery.post(url, {
		xn:jQuery("#xn").val(),
		xmlb:jQuery("#xmlb").val(),
		xmmc:jQuery("#xmmc").val(),
		sqzt:jQuery("#sqzt").val()
	}, function(data) {
		dataObj = data;
			createHtml();
		}, 'json');
}
function createHtml() {
	var parTbody = jQuery("#sqTbody");
	jQuery("tr",parTbody).remove();
	var sHtml = "";
	var xmmc="";
	var sqly="";
	var sqtj="";
	var Btn_Left_Name = "";
	var Btn_Right_Name = "";
	for ( var i = 0; i < dataObj.length; i++) {
		var o = dataObj[i];
		xmmc=o.xmmc;
		if(i%2==0){
			sHtml+="<tr>";
		}
		if(o.xmmc.length>10){
			xmmc= o.xmmc.substring(0,8)+"...";
		}
		sHtml+="";
		sHtml+="<td style='height:160px;'><div class='resources' style='width:381px;height:176px;float:none;'><p class='pic' style='float:left;padding-left:10px;padding-top: 10px;'>";
		sHtml+="<img style='width:170px;height:148px;margin:0;float:left;padding-right: 2px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+o.xmdm+"&type=view'></p>";
		sHtml+="<ul style='padding-left:70px;height:170px; margin-top:5px; padding-top:5px; float: left; width: 45%;'><li title="+o.xmmc+"><span>物品名称:"+xmmc+"</span></li>";
		sHtml+="<li><span>物品类别:"+o.xmlbmc+"</span></li>";
		
		if("ysq"==jQuery("#sqzt").val()){
			jQuery(".xnSea").show();
			sqly=o.sqly;
			if(null!=o.sqly&&o.sqly.length>7){
				sqly=o.sqly.substr(0, 6) + "···";
			}
			if("5"==o.shzt){
				Btn_Left_Name="撤销";
			}
			if("1"==o.shzt){
				Btn_Left_Name="已通过";
			}
			if("2"==o.shzt){
				Btn_Left_Name="不通过";
			}
			sHtml+="<li><span>申请时间:"+o.sqsj+"</span></li>";
			sHtml+="<li><span>审核状态:"+o.shztmc+"</span></li>";
			sHtml+="<li><span title='"+o.sqly+"'>申请理由:"+sqly+"</span></li>";
			sHtml+="<li><span><button type='button' onclick='ysqOperate(\""+o.sqid+"\",\""+o.splc+"\",\""+o.shzt+"\");return false;'>"+Btn_Left_Name+"</button>";
			sHtml+="<button type='button' onclick='sqxqck(\""+o.sqid+"\",\""+o.xh+"\");return false;'>查看详情</button></span></li>";	
		}
		else{
			jQuery(".xnSea").hide();
			sqtj=o.sqtj;
			if(null!=o.sqtj&&o.sqtj.length>7){
				sqtj=o.sqtj.substr(0, 6) + "···";
			}
			
			if("0"==o.shzt){
				Btn_Left_Name="提交";
			}
			if("3"==o.shzt){
				Btn_Left_Name="提交";
			}
			if(""==o.shzt||null==o.shzt){
				Btn_Left_Name="申请";
			}
			sHtml+="<li><span title='"+o.sqtj+"'>申请条件:"+sqtj+"</span></li>";
			sHtml+="<li><span>申请时间:"+o.sqsjfw+"</span></li>";
			
			sHtml+="<li style='width:100%'><span><button type='button' onclick='wsqOperate(\""+o.xmdm+"\",\""+o.shzt+"\",\""+o.isopen+"\",\""+o.sqid+"\");return false;'>"+Btn_Left_Name+"</button></span>";
			sHtml+="<span><button type='button' onclick='Wpck(\""+o.xmdm+"\");return false;'>查看</button></span>";
			
			if("0"==o.shzt){
			sHtml+="<span id='scBtn'><button type='button' onclick='del(\""+o.sqid+"\");return false;'>删除</button></span>"	;
			}
			sHtml+="</li>";
			
		}
		
		sHtml+="</ul></div></td>";
		if(i%2==1){
			sHtml+="</tr>";	
		}
		parTbody.html(sHtml);
	}
}

function del(sqid) {
	
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=delWpsq", {
					values : sqid
				},
						function(data) {
							var mes = "成功删除了<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>条数据";
							showAlertDivLayer(mes);
							searchRs();
						}, 'json');
			}
		});
	
}
function wsqOperate(xmdm,shzt,isopen,sqid) {
	var flag=true;
	jQuery.ajaxSetup({async:false});
	jQuery.post("axcswpsqxs.do?method=checkSqTj&xmdm="+xmdm, {
		xh:jQuery("#xh").val(),
		xmdm:xmdm
	}, function(data) {
		dataObj = data;
			if(dataObj.length>0&&(dataObj[0].result=="false")){
				flag=false;
			}
		}, 'json');
	if(false==flag){
		showAlertDivLayer("不满足申请条件，请确认后再申请！");
		return false;
	}
	
	if ('3' != shzt && "false" == isopen) {
		showAlertDivLayer("当前物品已关闭申请，请与管理员联系！");
		return false;
	}
	else if("0"==shzt){
		submitBusi(xmdm,shzt,sqid);
	}else{
	var url = "axcswpsqxs.do?method=wpsqxsZj&xmdm="+xmdm;
	var title = "物品申请";
	showDialog(title, 700, 500, url);
	}
	jQuery.ajaxSetup({async:true});
}



/**
 * 切换tab页
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, sqzt) {
	jQuery("#sqzt").val(sqzt);
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function saveWpsq(type) {
	var xh = jQuery("#xh").val();
	if (jQuery("#sqly").val() == "" || xh == "") {
		showAlert("请将必填项填写完整！");
		return false;
	}
	
	if (jQuery("#xmdm").val() == "" || null == jQuery("#xmdm").val()) {
		showAlert("请选择申请物品！");
		return false;
	}
	if (jQuery("#sqly").val().length>500) {
		showAlert("申请理由最多输入500字！");
		return false;
	}
	var url = "axcswpsqxs.do?method=saveWpsq&type=" + type;
	ajaxSubFormWithFun("WpsqForm", url, function(data) {
		showAlert(data["message"], {}, {
			"clkFun" : function() {
				if (parent.window) {
					refershParent();
				}
			}
		});
	});
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {

		var shzt = rows[0]["shzt"];

		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'axcswpsqxs.do?method=wpsqUpdate&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "物品申请修改";
		showDialog(title, 800, 600, url);
	}

}

function Wpck(xmdm){
	showDialog("物品详情查看",600,300,"axcsWpsz.do?method=ckWp&xmdm="+xmdm);
}

function sqxqck(sqid,xh){
	showDialog("爱心超市申请查询", 700, 480, "axcswpsqjs.do?method=wpsqView&sqid="
			+ sqid + "&xh=" + xh);
	
}

// 提交
function submitBusi(xmdm,shzt,sqid) {

	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();

	if (shzt!= "0" && shzt != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
	 else {
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("axcswpsqxs.do?method=saveUpdateWpsq&type=tj", {
					values : sqid,
					xmdm : xmdm
				}, function(data) {
					showAlertDivLayer(data["message"]);
					searchRs();
				}, 'json');
			}
		});
	}

}

function ysqOperate(sqid,splc,shzt) {
		if("1"==shzt){
			showAlertDivLayer("该物品申请已通过！");
			return false;
		}
		if("2"==shzt){
			showAlertDivLayer("该物品申请未通过！");
			return false;
		}
		if("5"==shzt){
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("axcswpsqxs.do?method=cancelWpsq", {
					values : sqid,
					splcid : splc
				}, function(data) {
					showAlertDivLayer(data["message"]);
					searchRs();
				}, 'json');
			}
		});
		}
	}




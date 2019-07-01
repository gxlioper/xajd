//参加活动列表
function searchRs(cxzt){//cxzt:1：点击查询  0：分页查询
	if(cxzt == '1'){
		jQuery("#currentPage").val("1");
	}
	var url = "zyhd.do?method=ycjhdList&type=query";
	ajaxSubFormWithFun("qnzyhdForm", url, function(data) {
			createHtml(data);
	});
}

function createHtml(obj){
	var listTbody = jQuery("#activity-list");
	jQuery(listTbody).empty();
	if(obj.length < 1){
		jQuery(listTbody).html("<div><p align='center'>未找到任何记录！</p></div>");
		jQuery("#total").text(0);
		jQuery("#pageno").val(0);
		jQuery("#pageCount").text(0);
		jQuery("#next").attr("href","javascript:void(0)");
		jQuery("#last").attr("href","javascript:void(0)");
		jQuery("#pre").attr("href","javascript:void(0)");
		jQuery("#first").attr("href","javascript:void(0)");
	}else{
		var content = "<ul>";
		for ( var i = 0; i < obj.length; i++) {
			if(i == obj.length -1){
				var total = obj[i]["total"];
				jQuery("#total").empty();
				jQuery("#total").text(total);
				jQuery("#pageno").val((obj[0]["rowindex"]-1)/jQuery("#pagesize").val()+1);//第几页
				var temp = total/jQuery("#pagesize").val();
				var pageCont;
				if (total%jQuery("#pagesize").val() == 0 && temp != 0){
					pageCont = temp;
				} else {
					pageCont = temp == 0 ? temp : parseInt(temp)+1;
				}
				jQuery("#pageCount").empty();
				jQuery("#pageCount").text(pageCont);
				if(jQuery("#pageno").val() == pageCont){
					jQuery("#next").attr("href","javascript:void(0)");
					jQuery("#last").attr("href","javascript:void(0)");
				}else{
					jQuery("#next").attr("href","javascript:submitNextPage()");
					jQuery("#last").attr("href","javascript:submitLastPage()");
				}
			}
			var o = obj[i];
			content+='<li><div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 padding-l0" style="margin-bottom:20px">';
			content+='<img src="'+ (o['fjpath'] == null ? 'default_dekt.jpg' : o['fjpath'])+ '" style="width:170px;height:130px"></div>';
			//content+='<img src="'+o['fjpath']+'" style="width:170px;height:130px"></div>';
			content+='<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">';
			content+='<h4 class="title">'+o['hdmc']+'</h4>';
			content+='<div class="row activity-num">';
			content+='<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12" style="font-size:14px;">负责人：'+o['fzrxm']+(o['hdfzrlxfs'] != null ? '/'+o['hdfzrlxfs']+'</div>' : '</div>');
			content+='<div class="col-lg-5 col-md-4 col-sm-6 col-xs-8" style="font-size:14px;">已报/限定人数：【<span class="num">'+o['ybrs']+'</span>/'+o['xdrs']+'】</div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" style="margin-top:10px;font-size:14px;">组织部门：'+o['zzbm']+'</div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" style="margin-top:10px;font-size:14px;">活动时间：'+o['hdkssj'];
			if(o['hdjssj'] != null){
				content+=' 至  ';
				content+=o["hdjssj"];
			}
			content+='</div>';
			content+='<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" style="margin-top:10px;font-size:14px;">活动地点：'+o['hddd']+'</div>';
			content+='</div></div>';
			content+='<table style="width:100%;margin-top:30px;" class="table table-bordered text-center"><tr align="center">';
			content+='<td width="15%">报名时间</td>';
			content+='<td width="10%">参加人</td>';
			content+='<td width="10%">报名状态</td>';
			content+='<td width="20%">基本服务工时</td>';
			content+='<td width="20%">服务工时审核状态</td>';
			content+='<td width="30%">操作</td></tr>';
			content+='<tr align="center">';
			content+='<td>'+o["bmsj"]+'</td>';
			content+='<td>'+o["xm"]+'</td>';
			content+='<td>'+o['bmztmc']+'</td>';
//			if(o["gsshzt"] == '1' || o["jxzt"] == 'noBegining' || o["bmzt"] == '2' || o["bmzt"] == '0'){//工时审核通过或者活动未开始			
//				content+='<td><input type="text" value="'+o["gss"]+'" style="width:40%" disabled="disabled"/>小时</td>';
//			}
//			else{
//				content+='<td><input type="text" value="'+o["gss"]+'" style="width:40%" maxlength="5" onkeyup="checkTimeNumForKeyup(this);" onblur="saveGs(\''+o["id"]+'\',this,\''+o["bmzt"]+'\',\''+o["sftjzt"]+'\')"/>小时</td>';
//			}
			content+='<td>'+o['gss']+'小时</td>';
			content+='<td><span title="'+o["gsshyj"]+'">'+o["gsshztmc"]+'</span></td>';
			content+='<td>';
//			if((o["gsshzt"] == '0' && o["bmzt"] == '1' && o["jxzt"] != 'noBegining') || (o["gsshzt"] == '2' && o["sftjzt"] == '0')){
//				content+='<input type="button" value="提交工时" class="btn blue-bg-btn btn-xs" onclick="tjgs(\''+o["id"]+'\',this)"></input>';
//				content+='<input type="button" value="提交" class="btn blue-bg-btn btn-xs" onclick="tjgs(\''+o["id"]+'\',this)"></input>';
//			}
			if(o["qxzt"] == '1'){				
				content+='<button class="btn red-bg-btn btn-xs" type="button" style="width:60px;margin-left:10px;margin-top:0px;" onclick="qx(\''+o["id"]+'\',\''+o["gsshzt"]+'\',\''+o["bmzt"]+'\')">取消</button>';
			}
			content+='</td>'
			content+='</tr></table>';
			content+='</li>';
		}
		content+="</ul>";
		jQuery(listTbody).append(content);
	}	
} 

//提交工时
function tjgs(id,obj){
	var url = 'zyhdry.do?method=tjgs';
	var row = jQuery(obj).parents("tr:eq(0)");
	var gs = jQuery(row).find("td").eq(3).find("input:eq(0)").val().trim();
	if(gs == null || gs == ''){
		showAlertDivLayer("请填写服务工时！");
		return false;
	}
	showConfirmDivLayer("您确定要提交工时吗？", {
		"okFun" : function() {
			var params = {id:id,gs:gs}
			jQuery.post(url,params,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					searchRs('0');
					//return;
				}});
			}, 'json')
		}
	});
}

//保存工时
function saveGs(id,obj,bmzt,sftjzt){
	if(obj.value.indexOf(".") == obj.value.length-1){
		obj.value = obj.value.substring(0,obj.value.length-1);
	}
	if((obj.value.indexOf(".") < 0 && obj.value.indexOf("0") == 0)){
		showAlertDivLayer("工时必须大于0!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		})
	}
	if((obj.value == null || obj.value == '')){
		return;
	}
	if(sftjzt != '1'){		
		var url = 'zyhdry.do?method=saveGs';
		var row = jQuery(obj).parents("tr:eq(0)");
		var params = {id:id,gs:obj.value}
		jQuery.post(url,params,function(data){
			return;
		}, 'json')
	}
}

//取消参与项目
function qx(id,gsshzt,bmzt){
	if(gsshzt == '1'){
		showAlertDivLayer("服务工时已通过审核，不允许取消！");
		return false;
	}
	if(bmzt == '2'){
		showAlertDivLayer("您的报名审核未通过，不允许取消！");
		return false;
	}
	showConfirmDivLayer("您确定要取消吗？", {
		"okFun" : function() {
		 var url = 'zyhdry.do?method=qxjl';
		 var params = {id:id}
		 jQuery.post(url,params,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					searchRs('1');
				}});
			}, 'json')
		}
	});
	
}





function splc_qr_th(){
	/*var api = frameElement.api.get('parentDialog');
	var go_path = api.jQuery("#go_path").val();
	var sqid = api.jQuery("#sqid").val();
	var shid = api.jQuery("#shid").val();
	var gwid = api.jQuery("#gwid").val();
	var splc = api.jQuery("#splc").val();
	var shzt = api.jQuery("#shzt").val();
	var shyj = api.jQuery("#shyj").val();
	var thgw = jQuery("input[name='thgw']:checked").val();
	jQuery("#sqid").val(sqid);
	jQuery("#shid").val(shid);
	jQuery("#gwid").val(gwid);
	jQuery("#splc").val(splc);
	jQuery("#shzt").val(shzt);
	jQuery("#shyj").val(shyj);
	alert(go_path);
	showConfirmDivLayer("您确定退回该申请吗？",{"okFun":function(){
		var url = go_path+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});*/
	var shid = jQuery("#shid").val();
	var shlc = jQuery("#shlc").val();
	var shyj = jQuery("#shyj").val();
	var thgw = jQuery("input[name='thgw']:checked").val();
	confirmInfo("您确定要退回审核吗?",function(ty){
		//alert(ty);
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=lcth",{shlc:shlc,shid:shid,thgw:thgw,shyj:shyj},function(data){
				frameElement.api.get('parentDialog').refershParent();
				iFClose();
			},'json');
		}
	});
}
/*
 * 审批流程退回 
 * gwid 当前审批岗位id
 * splc 审批流程 id
 */
function splc_th(shid,shlc,shyj){
	showDialog("审批流程退回",530,310,'comm_spl.do?method=shth&shid='+shid+"&shlc="+shlc+"&shyj="+shyj);
}
/*
 * 审批流程撤销
 * shid 审核id
 * splc 审批流程id 
 */
function splc_cx(splc,shid){
	confirmInfo("您确定要撤销操作吗?",function(ty){
		//alert(ty);
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=cxsh",{shlc:splc,shid:shid},function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					//if (parent.window){
						//refersh();
						jQuery("#dataTable").reloadGrid();
					//}
				}});
			},'json');
		}
	});
}
/*
 * 撤销[最后一级不可撤销]
 */
function cxsh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要审核的记录！");
	} else {
		splc_cx(rows[0]["splc"],rows[0]["shid"]);
	}
}

/*
 * 撤销[最后一级可撤销]
 */
function cxshnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要撤销审核的记录！");
	} else {
		splc_cx_new(rows[0]["splc"],rows[0]["shid"]);
	}
}
/*
 * 撤销
 */
function cxshnew_splc(obj){
	var sfkq=obj.data.sfkq;
	var rows = jQuery("#dataTable").getSeletRow();
	if(sfkq=="1"){//开启 则最后一级可撤销
		if (rows.length != 1){
			alertInfo("请选择一条您要撤销审核的记录！");
		} else {
			splc_cx_new(rows[0][obj.data.splc],rows[0]["shid"]);
		}
	}else{
		splc_cx(rows[0][obj.data.splc],rows[0]["shid"]);

	}
}
/*
 * 审批流程撤销[最后一级可撤销]
 * shid 审核id
 * splc 审批流程id 
 */
function splc_cx_new(splc,shid){
	//最后一级撤销审核后调用的路径
	var cancelPath = jQuery("#cancelPath").val();
	confirmInfo("您确定要撤销操作吗?",function(ty){
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
					// 判断是否最后一级撤销(1:最后一级撤销成功）
					if("1" == data["cancelFlg"]){
						jQuery.post(cancelPath,{splc:splc,shid:shid},function(result){
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
		}
	});
}
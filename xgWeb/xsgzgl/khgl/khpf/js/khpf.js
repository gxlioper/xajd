

/**
 * 开始评分
 * @return
 */

function quePf(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "khglKhpf.do?method=quePf&xmid=";
	if(ids.length != 1){
		showAlertDivLayer("请选择一个项目");
		return false;
	}
	if(rows[0]["sfjs"]=='1'){
		showAlertDivLayer("该项目评分已结束，不允许评分");
		return false;
	}else if(rows[0]["sfjs"]=='2'){
		showAlertDivLayer("该项目评分未开始，不允许评分");
		return false;
	}
	
		var rows = jQuery("#dataTable").getSeletRow();
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khlx="+rows[0]["khlx"];
		document.location.href=url;
}

/**
 * 开始评分
 */
function quePfe(xmid,khbid,khlx){
	
	var url = "khglKhpf.do?method=quePf&xmid="+xmid+"&khbid="+khbid+"&khlx="+khlx;
	document.location.href=url;
}


/**
 * 评分
 * @return
 */
function addPf(){
	var ids = jQuery("#dataTable").getSeletIds();
	var url = "khglKhpf.do?method=addPf&xmid=";
	if(ids.length == 1){
		var rows = jQuery("#dataTable").getSeletRow();
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["khdxr"]+"&xmszid="+rows[0]["xmszid"];
		if(jQuery("#xxdm").length>0){			
			if((jQuery("#xxdm").val()=='12309' || jQuery("#xxdm").val()=='33333') && jQuery("#khlx").length>0){
				url+="&khlx="+jQuery("#khlx").val();
			}
		}
		showDialog("评分",800,520,url,{close:function(){
			if (jQuery("#search_go")){
				jQuery("#search_go").click();
			}
		}});
	}else{
		showAlertDivLayer("请选择一个需要考核的人员");
		return false;
	}
}
//合并相同的一级指标
function autoRowSpan(){
	var xxdm = jQuery("#xxdm").val();
	var colV = 8;
	if(xxdm == "11527"){
		colV = 7;
	}
	var lastValue=""; 
	var value=""; 
	var pos=1; 
	for(var i=1;i<dataTable.rows.length;i++){ 
	value = dataTable.rows[i].cells[colV].innerText;
	if(lastValue == value){ 
	dataTable.rows[i].cells[colV].style.display="none";
	dataTable.rows[i-pos].cells[colV].rowSpan =dataTable.rows[i-pos].cells[colV].rowSpan+1;
	pos++; 
	}else{ 
	lastValue = value; 
	pos=1; 
	} 
	}
}			
/**
 * 保存考核分数
 * @param obj
 * @param zbmxid	指标明细ID
 * @return
 */
function saveKhfs(obj){
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var xmszid=jQuery("#xmszid").val();
    var pflx = jQuery("#pflx").val();
	var fs = obj.value;
	var zbmxid = jQuery(obj).attr("zb");
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	if (max != "" && fs != "" && Number(fs) > Number(max)){
		showAlertDivLayer("录入分数不能大于最大分："+max,{},{"clkFun":function(){
			obj.focus(); 
		}});
		return false;
	}
	if (min != "" && fs != "" && Number(fs) < Number(min)){
		showAlertDivLayer("录入分数不能低于最小分："+min,{},{"clkFun":function(){
			obj.focus();
		}});
		return false;
	}
	clickToTotal();


    jQuery.post("khglKhpf.do?method=saveKhfs",{xmid:xmid,khbid:khbid,khdxr:khdxr,zbmxid:zbmxid,xmszid:xmszid,fs:fs,pflx:pflx},function(data){
        if (data != null){
            showAlert(data["message"]);
        }
    },"json");

}

function saveBz(obj,zbmxid){
	
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var bz = obj.value;
	clickToTotal();
	jQuery.post("khglKhpf.do?method=saveBz",{xmid:xmid,khbid:khbid,khdxr:khdxr,zbmxid:zbmxid,bz:bz},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
	
}

/**
 * 保存意见建议（武昌首义个性化）
 */
function saveYjjy(obj){
	if(checkLenForYjjy(obj,1000)){		
		var xmid = jQuery("#xmid").val();
		var khbid = jQuery("#khbid").val();
		var khdxr = jQuery("#khdxr").val();
		var yjjy = obj.value;
		jQuery.post("khglKhpf.do?method=saveKhYjjy",{xmid:xmid,khbid:khbid,khdxr:khdxr,yjjy:yjjy},function(data){
			if (data){
				showAlert(data["message"]);
			}
		},"json");
	}else{
		return;
	}
};

//审核意见字数验证
function checkLenForYjjy(obj,leng){
	var flag = true;
	if(obj.value.length > leng){
		flag = false;
		showAlert("该输入项最大字数为"+leng+",现已经超过，请确认！",{},{"clkFun":function(){
				obj.focus();
		}});
	}
	return flag;
}


/**
 * 输入框 上、下、左、右键事件
 * @param obj
 */
function toNext(obj,event){
	var event = event || window.event;
	//左   
	if (event.keyCode==37){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index-1).select();
	}
	
	//上      
	if (event.keyCode==38){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
	}
	
	//右   或者 回车
	if (event.keyCode==39 || event.keyCode==13){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index+1).select();
	}
	
	//下 
	if (event.keyCode==40){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
	}
	
}



/**
 * 切换待评、已评人员
 * @param obj
 * @param sftj
 */
function selectTab(obj,sftj){
	
	var map = getSuperSearch();
	map["xmid"] = jQuery("#xmid").val();
	map["khbid"] = jQuery("#khbid").val();
	map["sftj"] = sftj;
	if (sftj == "2"){
		jQuery("#li_pf").css("display","");
		jQuery("#li_ck").css("display","none");
		jQuery("#li_xz").css("display","none");
		jQuery("#li_qx").css("display","none");
		jQuery("#sftj").val("2");
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_pf").css("display","none");
		jQuery("#li_ck").css("display","");
		jQuery("#li_xz").css("display","");
		jQuery("#li_qx").css("display","");
		jQuery("#sftj").val("1");
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

/**
 * ！！！弃用 20170925
 * 提交前验证
 * @param xmid	项目id
 * @param khbid	考核表id
 * @param khdxr	考核对象
 * @return
 */
function checkIsCanSubmit(xmid,khbid,khdxr){
	
	var bool = false;
	
	jQuery.post("khglKhpf.do?method=checkIsCanSubmit",{xmid:xmid,khbid:khbid,khdxr:khdxr},function(data){
		if (data == "true"){
			bool = true;
		} 
	});
	
	return bool;
}

/**
 * 提交内部调用
 */
function submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr) {
    jQuery.ajaxSetup({async:false});
    showConfirmDivLayer("考核表提交后将不允许修改，您确定要提交吗？",{"okFun":function(){
        jQuery.post("khglKhpf.do?method=submitTj",{xmid:xmid,khbid:khbid,khdxr:khdxr,pflx:pflx,xmszid:xmszid,zbmxidArr:zbmxidArr,fsArr:fsArr},
			function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					window.location.reload();
					refershParent();
				}});
			},"json");
    }});
    jQuery.ajaxSetup({async:true});
}

/**
 * 提交
 * @returns {Boolean}
 */
function submitTj(){
	
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var xmszid = jQuery("#xmszid").val();
	var xxdm = jQuery("#xxdm").val();
	var pflx = jQuery("#pflx").val();
	//指标项id数组
	var zbmxidArr = [];
	//分值
	var fsArr = [];

    var flag = false;
    //非空验证
    jQuery(".data-type").each(function(){
        var v = jQuery(this).val();
        if(v == ""||v == null){
            flag = true;
            return false;
        }
        zbmxidArr.push(jQuery(this).attr("zb"));
        fsArr.push(v);
    });
    
    if(flag){
        showAlertDivLayer("请完成考核表当中所有细项分！");
        return false;
    }
    if(xxdm == "11799"){
	    if(fsArr.length>1){
	    	for (var i = 0 ; i < fsArr.length ; i++){
	    		if(fsArr[0] !=fsArr[i]){
	    			break;
	    		}
	    		if(i == fsArr.length-1 ){
	    			showAlertDivLayer("测评分值不能全部相同！");
	    			return false;
	    		}
	    	}
	    }
    }
	//前级未提交验证
	if (xxdm == "11527"&&pflx == "bzpf"&&jQuery("#sftj").val() != "1"){
		showConfirmDivLayer("自评分未提交，是否强制审核通过？",{"okFun":function () {
			submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr);
		}});
	}else if (xxdm == "11527"&&pflx == "bzrpf"&&jQuery("#bztjzt").val() != "1"){
		showConfirmDivLayer("班组评分未提交，是否强制审核通过？",{"okFun":function () {
			submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr);
		}});
	}else {
		submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr);
	}
}

/**
 * submitNext内部调用
 */
function submitNextInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr,map) {
    jQuery.ajaxSetup({async:false});
    showConfirmDivLayer("考核表提交后将不允许修改，您确定要提交吗？",{"okFun":function(){
        jQuery.post("khglKhpf.do?method=submitTj",{xmid:xmid,khbid:khbid,khdxr:khdxr,xmszid:xmszid,pflx:pflx,zbmxidArr:zbmxidArr,fsArr:fsArr},
			function(data){
				if (data["message"] == "提交成功！"){
					jQuery.post("khglKhpf.do?method=submitNext",map,function(data){
						if (data == "null"){
							showAlertDivLayer("当前结果无下一个考核对象",{},{"clkFun":function(){
								refershParent();
							}});
						}else{
							var url = "khglKhpf.do?method=xgpf&xmid="+xmid+"&khbid="+khbid+"&khdxr="+data+"&xmszid="+xmszid+"&pflx="+pflx;
							document.location.href=url;
						}
					});
				} else {
					showAlertDivLayer(data["message"]);
				}
        },"json");
    }});
    jQuery.ajaxSetup({async:true});
}

/**
 * 提交并下一个
 * @return
 */
function submitNext(){
	
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var xmszid = jQuery("#xmszid").val();
    var xxdm = jQuery("#xxdm").val();
    var pflx = jQuery("#pflx").val();

	var api = frameElement.api,W = api.opener;
	var map = W.getSuperSearch();
	map["xmid"] = xmid;
	map["khbid"] = khbid;
	map["khdxr"] = khdxr;
	map["xmszid"] = xmszid;
	map["pflx"] = pflx;

    //指标项id数组
    var zbmxidArr = [];
    //分值
    var fsArr = [];

    var flag = false;
    //非空验证
    jQuery(".data-type").each(function(){
        var v = jQuery(this).val();
        if(v == ""||v == null){
            flag = true;
            return false;
        }
        zbmxidArr.push(jQuery(this).attr("zb"));
        fsArr.push(v);
    });
    if(flag){
        showAlertDivLayer("请完成考核表当中所有细项分！");
        return false;
    }

	//前级未提交验证
	if (xxdm == "11527"&&pflx == "bzpf"&&jQuery("#sftj").val() != "1"){
			showConfirmDivLayer("自评分未提交，是否强制审核通过？",{"okFun":function () {
				submitNextInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr,map);
			}});
	}else if (xxdm == "11527"&&pflx == "bzrpf"&&jQuery("#bztjzt").val() != "1"){
			showConfirmDivLayer("班组评分未提交，是否强制审核通过？",{"okFun":function () {
				submitNextInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr,map);
			}});
	}else {
        jQuery.ajaxSetup({async:false});
        showConfirmDivLayer("考核表提交后将不允许修改，您确定要提交吗？",{"okFun":function(){
            jQuery.post("khglKhpf.do?method=submitTj",{xmid:xmid,khbid:khbid,khdxr:khdxr,xmszid:xmszid,pflx:pflx,zbmxidArr:zbmxidArr,fsArr:fsArr},
                function(data){
                    if (data["message"] == "提交成功！"){
                        jQuery.post("khglKhpf.do?method=submitNext",map,function(data){
                            if (data == "null"){
                                showAlertDivLayer("当前结果无下一个考核对象",{},{"clkFun":function(){
                                    refershParent();
                                }});
                            }else{
                                var url = "khglKhpf.do?method=addPf&xmid="+xmid+"&khbid="+khbid+"&khdxr="+data+"&xmszid="+xmszid+"&pflx="+pflx;
								if((xxdm=='12309' || xxdm=='33333') && jQuery("#khlx").length>0){
									url+="&khlx="+jQuery("#khlx").val();
								}
                                document.location.href=url;
                            }
                        });
                    } else {
                        showAlertDivLayer(data["message"]);
                    }
                },"json");
        }});
        jQuery.ajaxSetup({async:true});
	}
}

/**
 * 评分详情
 * @return
 */
function viewPf(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm = jQuery("#xxdm").val();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
        var khdxr = rows[0]["khdxr"];
        var xmszid = rows[0]["xmszid"];
        if(xxdm=="11527"){
            khdxr = rows[0]["xh"];
            xmszid=jQuery("#xmszid").val();
        }
		showDialog("查看",800,520,"khglKhpf.do?method=viewPf&xmid="+rows[0]["xmid"]+"&pflx="+jQuery("#pflx").val()+"&xmszid="+xmszid+"&khbid="+rows[0]["khbid"]+"&khdxr="+khdxr);
	}	
}

/**
 * 计算总分
 * @return
 */
function clickToTotal(){
	var total = 0;
	jQuery(".data-type").each(function(i,n){
		var m = jQuery(n).attr('lx');
		if("2" == m ){
			total = total-new Number(jQuery(n).val());
		}else{
			total= total+new Number(jQuery(n).val());
		}
	});
	jQuery("#total").html(total.toFixed(2));
}


/**
 * 查看页面总分计算
 * @return
 */
function viewTotal(){
	
	var total = 0;
	
	jQuery(".data-type").each(function(i,n){
		var m = jQuery(n).attr('lx');
		var v = jQuery(n).html();
		if(v==null||v==""){
			v = 0;
		}
		if("2" == m ){
			total= total-new Number(v);
		}else{
			total= total+new Number(v);
		}
		
	});
    jQuery("#total").html(total.toFixed(2));
}

function setZt(cellValue, rowObject){
	var ypcount = rowObject.ypcount;
	var sfjs=rowObject.sfjs;//评分是否结束
	var value = "未评完";
	var color;
	if ("2"==sfjs) {
		value="未开始";
		color = "have";
	} else if("1"==sfjs){
		value="已结束";
		color = "have";
	}else if (cellValue - ypcount == '0') {
		value="已评完";
		color = "have";
	}else  {
		color = "non";
	}
	if ("non"==color){
		value = "<a  href='javascript:void(0);' onclick='return quePfe(\"" + rowObject.xmid
		+ "\",\"" + rowObject.khbid+ "\",\""+rowObject.khlx+"\");' ><font class='" + color + "'>" + value + "</font></a>";
	} else {
		value = "<font class='" + color + "'>" + value + "</font>";
	}
	 return value;
}

function setYl(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='khbYl(\""+rowObject.khbid+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function khbYl(khbid,khbmc){
	var url="khglKhbgl.do?method=khnryl&khbid="+khbid+"&khbmc="+khbmc;
	showDialog('考核内容预览',800,550,url);
}

function getCpcjWord(){
	var khdxrs=[];
	var url="khglKhpf.do?method=getCpcjWord&xmid=";
	
	var rows = jQuery("#dataTable").getSeletRow();
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	 } else if (rows.length > 1){
		 var ids = jQuery("#dataTable").getSeletIds();
		 for ( var int = 0; int < ids.length; int++) {
			 khdxrs.push(rows[int]["xh"]);
		}
		 url="khglKhpf.do?method=getCpcjWordZip&xmid=";
		 url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&value="+khdxrs.toString()+"&xmszid="+rows[0]["xmszid"]+"&xn="+rows[0]["xn"];
	 } else {
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["xh"]+"&xmszid="+rows[0]["xmszid"]+"&xn="+rows[0]["xn"];
     }
	 	url = addSuperSearchParams(url);
		document.forms[0].action = url;
		document.forms[0].submit();
}

function getStuCptjWord(){
	var url="khglKhpf.do?method=getCpcjWord&xmid=";
	var xmid=jQuery("#xmid").val();
	var khbid=jQuery("#khbid").val();
	var khdxr=jQuery("#khdxr").val();
	var xmszid=jQuery("#xmszid").val();
	url+=xmid+"&khbid="+khbid+"&khdxr="+khdxr+"&xmszid="+xmszid;
	document.forms[0].action = url;
	document.forms[0].submit();
}

function bzsh(){
	var warnMessage="确定要审核通过吗？";
	var xmid=jQuery("#xmid").val();
	var khbid=jQuery("#khbid").val();
	var xmszid = jQuery("#xmszid").val();
	var rows = jQuery("#dataTable").getSeletRow();
	 var ids = jQuery("#dataTable").getSeletIds();
	var pflx = jQuery("#pflx").val();
	 if (rows.length == 0){
		showAlertDivLayer("请选择您需要审核的记录！");
		return false;
	 }
	 for ( var int = 0; int < rows.length; int++) {
	 	if(pflx=="bzpf"){
            if(rows[int]["bzshztmc"]=="审核通过"){
                showAlertDivLayer("不能选择已经审核的记录！");
                return false;
            }else{
                if(rows[int]["zpyp"]=="未提交"){
                    warnMessage="有自评分未提交的记录，是否强制审核通过？";
                    break;
                }
			}
		}else {
            if(rows[int]["bzrshztmc"]=="审核通过"){
                showAlertDivLayer("不能选择已经审核的记录！");
                return false;
            }else {
                if(rows[int]["bzyp"]=="未提交"){
                    warnMessage="有班组评分未提交的记录，是否强制审核通过？";
                    break;
                }
			}
		}
	}

	showConfirmDivLayer(warnMessage,{"okFun":function(){
		jQuery.post("khglKhpf.do?method=sh",{xmid:xmid,khbid:khbid,xmszid:xmszid,khdxr:ids.toString(),pflx:pflx},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#search_go").click();
						}});
					
		},"json");
	}});	 
}

/**
 * 湖南城市 撤消审核
 * @returns {boolean}
 */
function cxsh(){
    var warnMessage="确定要撤消审核吗？";
    var xmid=jQuery("#xmid").val();
    var khbid=jQuery("#khbid").val();
    var xmszid = jQuery("#xmszid").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var pflx = jQuery("#pflx").val();
    if (rows.length == 0){
        showAlertDivLayer("请选择您需要撤消的记录！");
        return false;
    }
    for ( var int = 0; int < rows.length; int++) {
        if(pflx=="bzpf"){
            if(rows[int]["bzshztmc"]=="未审核"){
                showAlertDivLayer("不能选择未审核的记录！");
                return false;
            }else{
                if(rows[int]["bzrshzt"]=="1"){
                    showAlertDivLayer("班主任已审核通过，无法撤消！");
                    return false;
                }
			}
        }else {
            if(rows[int]["bzrshztmc"]=="未审核"){
                showAlertDivLayer("不能选择未审核的记录！");
                return false;
            }
        }
    }

    showConfirmDivLayer(warnMessage,{"okFun":function(){
        jQuery.post("khglKhpf.do?method=cx",{xmid:xmid,khbid:khbid,xmszid:xmszid,khdxr:ids.toString(),pflx:pflx},function(data){
            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                jQuery("#search_go").click();
            }});

        },"json");
    }});
}

/**
 * 湖南城市 撤消学生自评
 * @returns {boolean}
 */
function cxxszp(){
    var warnMessage="确定要撤消学生自评吗？";
    var xmid=jQuery("#xmid").val();
    var khbid=jQuery("#khbid").val();
    var xmszid = jQuery("#xmszid").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    // var pflx = jQuery("#pflx").val();
	var pflx = "xszp";
    if (rows.length == 0){
        showAlertDivLayer("请选择您需要撤消的记录！");
        return false;
    }
    for ( var int = 0; int < rows.length; int++) {
		if(rows[int]["zpyp"]=="未提交"){
			showAlertDivLayer("不能选择学生自评未提交的记录！");
			return false;
		}
    }

    showConfirmDivLayer(warnMessage,{"okFun":function(){
        jQuery.post("khglKhpf.do?method=cx",{xmid:xmid,khbid:khbid,xmszid:xmszid,khdxr:ids.toString(),pflx:pflx},function(data){
            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                jQuery("#search_go").click();
            }});

        },"json");
    }});
}

/**
 * 取消提交
 * @return
 */
function cancelTjRecord(){
	var ids = jQuery("#dataTable").getSeletIds();
	var url = "khglKhpf.do?method=cancelTjRecord&xmid=";
	if(ids.length == 1){
		var rows = jQuery("#dataTable").getSeletRow();
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["khdxr"]+"&xmszid="+rows[0]["xmszid"];
		showConfirmDivLayer("您确定要取消已提交记录吗？",{"okFun":function(){
			jQuery.post(url,{},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}else{
		showAlertDivLayer("请选择一条需要取消提交的记录！");
		return false;
	}
}

/**
 *生成班组评分密码
 */
function scbzpfmm(){
	showDialog("生成班组评分密码",500,280,"khglKhpf.do?method=scbzpfmm");
}

/**
 * 生成班组评分密码的保存
 */
function scbzpfmmSave () {
	//保存前验证
	if(!checkPassword()){
        return false;
    }

    ajaxSubFormWithFun("scbzpfmmForm", "khglKhpf.do?method=scbzpfmmSave", function(data) {
		showAlert(data["message"]);
    });
}

function checkPassword ()  {
	var flag = true;
	jQuery("input.password").each(
		function () {
			var psd = jQuery(this).val();
			if(psd != ""){
                if(!checkPsw(psd)){
                	flag = false;
                    return false;
                }
                if(!CheckChineseByString(psd)){
                	flag = false;
					return false;
				}
			}
        }
	);
	return flag;
}

/**
 * 湖南城市学院班级综测成绩总表下载
 * @returns {boolean}
 */
function bjzccjzbxz(){
    var url ="khglKhpf.do?method=bjzccjzbxz";

    var xnLength=jQuery("a[name=a_name_xn]").length;
    var xyLength=jQuery("a[name=a_name_xy]").length;
    // var bjLength=jQuery("a[name=a_name_bj]").length;

    if(xnLength != "1"){
        showAlertDivLayer("请选择一个学年查询条件！");
        return false;
    }

    if(xyLength != "1"){
        showAlertDivLayer("请选择一个学院查询条件！");
        return false;
    }

    setSearchTj();
    url = addSuperSearchParams(url);
    document.forms[0].action = url;
    document.forms[0].submit();
}
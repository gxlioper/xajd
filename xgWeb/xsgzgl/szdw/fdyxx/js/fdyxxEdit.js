var gndm = "szdw_fdyxx_update";// 功能代码

function onShow(flag) {
	var url = "szdw_fdyxx.do?method=getFdyxx";
	var xxdm = jQuery("#xxdm").val();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			zgh : jQuery("#zgh").val()
		},
		dataType : "json",
		success : function(data) {
			zdybdInit(gndm, data,null,flag);
			//是否在编补丁
			var sfzbSelect = jQuery("#sfzb");
			var sfzbTr = sfzbSelect.parent().parent();
			//选择否显示备注
			var bztr = "<tr id='bzbbzTr'><th>非在编备注</th><td colspan='4'><textarea id='bzbbz' name='bzbbz' rows='4' style='width:97%'></textarea></td></tr>"
            sfzbTr.after(bztr);
            sfzbChange();
            sfzbSelect.bind('change',sfzbChange);
            //设值
            jQuery("#bzbbz").val(data["bzbbz"]);

            //3+2辅导员补丁
			var sfdrsjzfdySelect = jQuery("#sfdrsjzfdy")
			var sfdrsjzfdyTr = sfdrsjzfdySelect.parent().parent();
			var lxdmsjTr = "<tr id='lxdmsjTr'><th><span class='red'>*</span>担任\"2+3\"辅导员时间</th><td><input type='text' id='drsj' name='drsj' onfocus='WdatePicker({dateFmt:\"yyyy\"})' class='text_nor'></td>";
            lxdmsjTr += "<th><span class='red'>*</span>留校部门</th><td colspan='2'><select id='lxbm' name='lxbm'><option value=''>---请选择---</option>";
            var bmdms = jQuery("#bmdm").children();
            var option;
            bmdms.each(function(i,e){
                option = "<option value='"+bmdms[i].value+"'>"+bmdms[i].text+"</option>";
                lxdmsjTr += option;
            });
            lxdmsjTr +="</select></td></tr>";
            sfdrsjzfdyTr.after(lxdmsjTr);

            sfdrsjzfdyChange();
            sfdrsjzfdySelect.bind('change',sfdrsjzfdyChange);
            jQuery("#drsj").val(data["drsj"]);
            jQuery("#lxbm").val(data["lxbm"]);

		}
	});
}
function sfdrsjzfdyChange(){
	var sfdrsjzfdy = jQuery("#sfdrsjzfdy").val();
	if(sfdrsjzfdy == '是'){
        jQuery("#lxdmsjTr").show();
	} else {
        jQuery("#lxdmsjTr").hide();
	}
}
function sfzbChange(){
    var sfzb = jQuery("#sfzb").val();
    if(sfzb == '是'){
        jQuery("#bzbbzTr").hide();
    } else if(sfzb == '否'){
        jQuery("#bzbbzTr").show();
    } else {
        jQuery("#bzbbzTr").hide();
    }
}
function initXgsh() {
	var url = "szdw_fdyxx.do?method=getFdyxx";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			zgh : jQuery("#zgh").val()
		},
		dataType : "json",
		success : function(data) {

			zdybdInit(gndm, data,{xs:true});
			jQuery("#zdybdfl_szdw_fdyxx_qtxx td:last").attr("width","85%");
			setAuditXgzd();// 设置修改字段
		}
	});
}


function saveForm() {
	if (!zdybdCheck()) {
		return;
	}
	var sfdrsjzfdy = jQuery("#sfdrsjzfdy").val();
	if(sfdrsjzfdy == "是"){
		var drsj = jQuery("#drsj").val();
		if(drsj == ""){
            showAlert("请填写担任\"2+3\"辅导员时间！");
            return;
		}
		var lxbm = jQuery("#lxbm").val();
		if(lxbm == ""){
            showAlert("请选择留校部门！");
            return;
		}
	}
//	var sfbt = jQuery('#zpsfbt').val();
//	var sfcz = jQuery('#zpsfcz').val();
//	if (sfbt == "y" && "false" == sfcz) {
//		alertError("请先上传一张照片！")
//		return false;
//	}

	var url = "szdw_fdyxx.do?method=fdyxxXg";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});
}

//显示上传相片
function showZpscDiv(){

	var zgh = jQuery("#zgh").val();
	
	if(zgh == ""){
		alertError("请先填写职工号！");
	}else{
		
		var html = '<div class="open_win01">';
			html += '<table align="center" class="formlist">';
			html += '	<thead>';
			html += '	<tr>';
			html += '<th colspan="2">';
			html += '<span>上传照片</span>';
			html += '</th>';
			html += '</tr>';
			html += '</thead>';
			html += '<tbody>';
			html += '<tr>';
			html += '<td>';
			html += '<input type="file" id="teaPic" name="teaPic" style="width:90%" onchange=\'uploadTeaPic();ymPrompt.close();\'/><br/>';
			html += '<span style="color:red">注：请上传jpg，gif，png，bmp 格式的文件，限 1 M 。</span>';
			html += '</td>';
			html += '</tr>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
		
		
		tipsWindown("上传照片",html,"380","130","true","","true","id");
		//tipsWindownNew("上传照片","id:addPic",380,130);
	}
}

function uploadTeaPic(){
	
	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});
	
	var zgh = jQuery("#zgh").val();
	jQuery.ajaxFileUpload({
		  url:'szdw_teaInfo.do?method=uploadTeaPic&zgh='+zgh,//服务器端程序
		  secureuri:false,
		  fileElementId:'teaPic',//input框的ID
		  success:function(data,type){
			if (type=='success'){
				jQuery("#jszp").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
				jQuery("#zhaopian").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
				jQuery('#zpsfcz').attr("value", "true");
				jQuery('#sczp').attr("value", "1");
				showAlert(data);
			}
		  }
	});
}

function save(){
	
	/*if (!zdybdCheck()) {
		return;
	}*/
    //仅仅获取表单修改值，不验证
    getXgzd();

	if (!getXgzdJson()) {
	}
	
	var url = "szdw_fdyxx.do?method=bcsq";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						document.location.href="szdw_fdyxx_xgsq.do";
					}
				}
			});
		}
	});
}

function saveAndSubmit(){
	if (!zdybdCheck()) {
		return;
	}
	
	if (!getXgzdJson()) {
	}
	
	var url = "szdw_fdyxx.do?method=tjsq";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						document.location.href="szdw_fdyxx_xgsq.do";
					}
				}
			});
		}
	});
}

function setXgzd(flag) {

	var sqid = jQuery("#shzSqid").val();
	if (sqid == null || sqid == "") {
		sqid = jQuery("#dshSqid").val();
	}
	if (sqid == null || sqid == "") {
		return;
	}
	var url = "szdw_fdyxx.do?method=getXgzdList";
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {

			zdybdReplaceZd(data,flag);
			//
			for(var q=0;q<data.length;q++){
				if("bzbbz"==data[q].zd){
					jQuery("#bzbbz").val(data[q].zdz);
				}
				if("drsj"==data[q].zd){
                    jQuery("#drsj").val(data[q].zdz);
				}
				if("lxbm"==data[q].zd){
                    jQuery("#lxbm").val(data[q].zdz);
				}
			}
		}
	});
}

function setAuditXgzd() {
	var sqid = jQuery("#shzSqid").val();
	if (sqid == null || sqid == "") {
		sqid = jQuery("#dshSqid").val();
	}
	if (sqid == null || sqid == "") {
		return;
	}
	var url = "szdw_fdyxx.do?method=getXgzdList";
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {

			var flag="sh";
			zdybdXgzd(data,flag);
			/*单独处理非在编备注、担任"2+3"辅导员时间、留校部门*/
			var addFlag = true;
			for(var p=0;p<data.length;p++){
				if(data[p].zd == "drsj" || data[p].zd == "lxbm"){

					if(addFlag){
                        var tr = jQuery("th[name='zdybdcon_th_sfdrsjzfdy']").parent();
                        var ht = "<tr><th>担任\"2+3\"辅导员时间</th><td name='sjedrsjTd'></td><th>留校部门</th><td name='lxbmTd' colspan='2'><a href=\"javascript:void(0);\">" +
                            "<font color=\"red\" title=\"修改前信息为：\" alt=\"修改前信息为：\"></font></a></td></tr>";
                        tr.after(ht);
                        addFlag = false;

					}
                    if(data[p].zd == "drsj"){
                        var strA = "<a href=\"javascript:void(0);\"><font color=\"red\" title=\"修改前信息为："+data[p].xgqz+"\" alt=\"修改前信息为："+data[p].xgqz+"\">"+data[p].zdz+"</font></a>";
                        jQuery("td[name='sjedrsjTd']").append(strA);
                    } else {
                    	//获取留校部门名称
						jQuery.post("szdw_fdyxx.do?method=getBmmc",{bmdm:data[p].zdz},function(rs){
                            var strA = "<a href=\"javascript:void(0);\"><font color=\"red\">"+rs.bmmc+"</font></a>";
                            jQuery("td[name='lxbmTd']").append(strA);
						},'json');
                    }
				} else if(data[p].zd == "bzbbz"){
                    var tr = jQuery("th[name='zdybdcon_th_sfzb']").parent();
                    var ht = "<tr><th>非在编备注</th><td colspan='4'><a href=\"javascript:void(0);\"><font color=\"red\" title=\"修改前信息为："+data[p].xgqz+"\" alt=\"修改前信息为："+data[p].xgqz+"\">"+data[p].zdz+"</font></a></td></tr>";
                    tr.after(ht);
				}
			}
		}
	});
}




function saveAudit() {
	// 提交审核
	var url = "szdw_fdyxx.do?method=saveAudit";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				refershParent();
			}
		});
	});

}

//江西师范科技学院辅导员身份显示与切换
function onChange() {
	if ("专职辅导员" == jQuery("#kzzd16").val()) {
		jQuery("th[name = 'zdybdcon_th_kzzd17']").show();
		jQuery("td[name = 'zdybdcon_td_kzzd17']").show();
		jQuery("td[name = 'zdybdcon_td_kzzd12']").attr("colspan", "2");
	} else if ("兼职辅导员" == jQuery("#kzzd16").val()
			|| "班主任" == jQuery("#kzzd16").val()) {
		jQuery("#kzzd17").val(jQuery('#kzzd17 option:first').val());
		jQuery("td[name = 'zdybdcon_td_kzzd12']").attr("colspan", "4");
		jQuery("th[name = 'zdybdcon_th_kzzd17']").hide();
		jQuery("td[name = 'zdybdcon_td_kzzd17']").hide();
	}
}

//教师身份(北京中医药大学)
function jssfValueShow(){
	var jssf = jQuery("#jssfs").val();
	//这行代码是为了防止通用js处理checkbox值注入bug而写（通用自定义表单注入值时无法处理以单个字段存储的checkbox值，在修改时无法进行准确回写）
	jQuery("[name='zdybdcon_td_jssf']").html(jQuery("label"));
	//遍历值，对选中的值打勾
	jQuery("input[name='jssf']").each(function(i){
		var value = this.value;
		if(jssf.indexOf(value) != -1 ){
			jQuery(this).attr("checked","checked");
		}
		
	})
}

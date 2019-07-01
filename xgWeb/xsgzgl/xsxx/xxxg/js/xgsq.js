var gndm = "xsxx_update";// 功能代码
var isQzxg = false;

//浙江工业短信发送个性化字段
var InterValObj; //timer变量，控制时间
var count = 300; //间隔函数，1秒执行
var curCount;//当前剩余秒数


jQuery(function() {
	onShow("xg");
	if("10530" == jQuery("#xxdm").val()){
		xiangtan_ssxxqr_change_10530();
	}else if ("40030" == jQuery("#xxdm").val()) {
		onChange();
	}else if ("14073" == jQuery("#xxdm").val()) {
		onChange_14073();
	}else if("12883"==jQuery("#xxdm").val()){
		onChange_12883();
	}else if ("12727" == jQuery("#xxdm").val()) {
		onChange_12727();
	}else if("10264" == jQuery("#xxdm").val()){
		var jtdhqh = '<font color="red" style="padding-left:20px">（请添加区号）</font>';
		var zmr = '<font color="red" style="padding-left:20px">（从中学开始填写）</font>';
		jQuery("#zdybdfl_xsxx_update_xlshjlxxxg>table:first").find('span').append(zmr);
		jQuery("td[name=zdybdcon_td_jtdh]").append(jtdhqh);
		
	}else if ("10718" == jQuery("#xxdm").val()) {
		/*陕西师范大学个性化字段联动判断及处理*/
		contentHide_10718();
		onChange_10718();
	}else if ("12867" == jQuery("#xxdm").val()) { 
		//浙江旅游
		//寒暑假火车乘车区间
		var fHtml = "";
		var zd4Val=jQuery("#zd4").val();
		var tHtml="<input type='text' name='zd4' value='"+zd4Val+"' id='zd4' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd4']").html(fHtml+tHtml);
		jQuery("#zd4").parent().append("&nbsp;&nbsp;&nbsp;如:杭州东");	
		citySelect("zd4");
	}else if ("11458" == jQuery("#xxdm").val()) {
		jQuery("#byxx").parents("tr").hide();
		jQuery("#bz").parents("tr").hide();
		jQuery("#jtdz").css("width","300px");
		jQuery("#zsjj").css("margin-top", "5px");
		var helpMsg = '东部地区包括北京、天津、河北、辽宁、上海、江苏、浙江、福建、山东、广东和海南等11个省市；\n';
		helpMsg += '中部地区包括山西、吉林、黑龙江、安徽、江西、河南、湖北、湖南等8省；\n';
		helpMsg += '西部地区包括重庆、四川、贵州、云南、西藏、陕西、甘肃、青海、宁夏、新疆、广西、内蒙古等12个省、自治区。';
		var helpHtml = '<div class="tab_cur" style="display: inline;background-image: none;" title="'+helpMsg+'">';
		helpHtml += '<p class="help" style="margin-right:36px;">';
		helpHtml += '<a href="#" onclick="return false;" onmousedown ="showHelpDiv();" >帮助</a>';
		helpHtml += '</p>';
		helpHtml += '</div>';
		var msg = '东部地区包括北京、天津、河北、辽宁、上海、江苏、浙江、福建、山东、广东等10个省市；</br>';
		msg += '中部地区包括山西、吉林、黑龙江、安徽、江西、河南、湖北、湖南和海南等9省；</br>';
		msg += '西部地区包括重庆、四川、贵州、云南、西藏、陕西、甘肃、青海、宁夏、新疆、广西、内蒙古等12个省、自治区。</br>';
		jQuery("#zsjj").parent().prepend(msg);
//		jQuery("#zsjj").parent().append(helpHtml);
		jQuery("#rxfs").parent().append("&nbsp;&nbsp;&nbsp;如:X宿XXX");
		var zmr = '<font color="red" style="padding-left:20px">如：小学：XXXX年XX月—XXXX年XX月    XXXX学校学习</font>';
		jQuery("#zdybdfl_xsxx_update_xlshjlxxxg>table:first").find('span').append(zmr);
		//寒暑假火车乘车区间
		var fHtml = "上海<—>";
		var zd4Val=jQuery("#zd4").val();
		var tHtml="<input type='text' name='zd4' value='"+zd4Val+"' id='zd4' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd4']").html(fHtml+tHtml);
		jQuery("#zd4").parent().append("&nbsp;&nbsp;&nbsp;如:济南西");
		citySelect("zd4");
		//家庭成员默认2行
		var jtcyTr=2;
		if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1){
			for ( var int = 0; int < jtcyTr; int++) {
				addConMoreUpdateTr("xsxx_update_jtcyxxxg");
			}
		}
		//教育信息默认3行
		var jyxxTr=3;
		if(jQuery("#zdybdcon_table_xsxx_update_xlshjlxxxg tbody tr").length == 1){
			for ( var int = 0; int < jyxxTr; int++) {
				addConMoreUpdateTr("xsxx_update_xlshjlxxxg");
			}
		}
		
	}else if("10279" == jQuery("#xxdm").val()) {		
		var yhtsxx = '<br /><font color="red">备注：请填写与学校财务关联的学费银行卡。</font>';
		jQuery("td[name=zdybdcon_td_yhdm]").append(yhtsxx);
		var dsxxsm = '<br /><font color="red">备注：本科生可不填写。</font>';
		jQuery("td[name=zdybdcon_td_xzxm]").append(dsxxsm);
		
	}else if ("10351" == jQuery("#xxdm").val()){

		if ("" == jQuery("#bz").val()) {
			jQuery("#bz").val("可填写自我评价，包括性格特点，兴趣安好，家庭情况等描述。。。");
			jQuery("#bz").css("color", "#7D7D7D");
		}
		jQuery("#bz").focus(function() {
			if ("可填写自我评价，包括性格特点，兴趣安好，家庭情况等描述。。。" == jQuery("#bz").val()) {
				jQuery("#bz").val("");
				jQuery("#bz").css("color", "");
			}
		});
		jQuery("#bz").blur(function() {
			if ("" == jQuery("#bz").val()) {
				jQuery("#bz").val("可填写自我评价，包括性格特点，兴趣安好，家庭情况等描述。。。");
				jQuery("#bz").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#sjhm").val()) {
			jQuery("#sjhm").val("请填写手机长号");
			jQuery("#sjhm").css("color", "#7D7D7D");
		}
		jQuery("#sjhm").focus(function() {
			if ("请填写手机长号" == jQuery("#sjhm").val()) {
				jQuery("#sjhm").val("");
				jQuery("#sjhm").css("color", "");
			}
		});
		jQuery("#sjhm").blur(function() {
			if ("" == jQuery("#sjhm").val()) {
				jQuery("#sjhm").val("请填写手机长号");
				jQuery("#sjhm").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#jtdh").val()) {
			jQuery("#jtdh").val("紧急情况联系人号码");
			jQuery("#jtdh").css("color", "#7D7D7D");
		}
		jQuery("#jtdh").focus(function() {
			if ("紧急情况联系人号码" == jQuery("#jtdh").val()) {
				jQuery("#jtdh").val("");
				jQuery("#jtdh").css("color", "");
			}
		});
		jQuery("#jtdh").blur(function() {
			if ("" == jQuery("#jtdh").val()) {
				jQuery("#jtdh").val("紧急情况联系人号码");
				jQuery("#jtdh").css("color", "#7D7D7D");
			}
		});
	} else if("10335" == jQuery("#xxdm").val()){
		if(jQuery("input:radio[name='shgxzw1']:checked").val() == '是'){
			jQuery("th[name='zdybdcon_th_shgxdwdh1'] ").html("<span class='red'>*</span>校外住宿地址");
		}else{
			jQuery("th[name='zdybdcon_th_shgxdwdh1']").remove();
			jQuery("td[name='zdybdcon_td_shgxdwdh1']").remove();
			//為了防止有原來的錯誤數據，贏該加上隱藏域
			jQuery("#zdybdcon_table_xsxx_update_lxfs > tbody ").append("<input type='hidden' name='shgxdwdh1' value='' id='shgxdwdh1' class='text_nor' maxlength='25'>");
		};
		//在护照号码
		jQuery("#shgxxm2").focus(function() {
			if ("如果没有请填写“无”" == jQuery("#shgxxm2").val()) {
				jQuery("#shgxxm2").val("");
				jQuery("#shgxxm2").css("color", "");
			}
		});
		jQuery("#shgxxm2").blur(function() {
			if ("" == jQuery("#shgxxm2").val()) {
				jQuery("#shgxxm2").val("如果没有请填写“无”");
				jQuery("#shgxxm2").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#shgxxm2").val()) {
			jQuery("#shgxxm2").val("如果没有请填写“无”");
			jQuery("#shgxxm2").css("color", "#7D7D7D");
		}
		//港澳通行证号码
		jQuery("#shgxxm1").focus(function() {
			if ("如果没有请填写“无”" == jQuery("#shgxxm1").val()) {
				jQuery("#shgxxm1").val("");
				jQuery("#shgxxm1").css("color", "");
			}
		});
		jQuery("#shgxxm1").blur(function() {
			if ("" == jQuery("#shgxxm1").val()) {
				jQuery("#shgxxm1").val("如果没有请填写“无”");
				jQuery("#shgxxm1").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#shgxxm1").val()) {
			jQuery("#shgxxm1").val("如果没有请填写“无”");
			jQuery("#shgxxm1").css("color", "#7D7D7D");
		}
		//台湾通行证号码
		jQuery("#shgxsjhm1").focus(function() {
			if ("如果没有请填写“无”" == jQuery("#shgxsjhm1").val()) {
				jQuery("#shgxsjhm1").val("");
				jQuery("#shgxsjhm1").css("color", "");
			}
		});
		jQuery("#shgxsjhm1").blur(function() {
			if ("" == jQuery("#shgxsjhm1").val()) {
				jQuery("#shgxsjhm1").val("如果没有请填写“无”");
				jQuery("#shgxsjhm1").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#shgxsjhm1").val()) {
			jQuery("#shgxsjhm1").val("如果没有请填写“无”");
			jQuery("#shgxsjhm1").css("color", "#7D7D7D");
		}
		jQuery("td[name='zdybdcon_td_yhkh']").append("<span class='red'>&nbsp;&nbsp;&nbsp;交学费银行卡</span>");
		jQuery("input:radio[name='shgxzw1']").change(function(){
			if(this.value == '是'){
				jQuery("#shgxdwdh1").remove();
				jQuery("td[name='zdybdcon_td_shgxzw1']").parent().append("<th width='15%' name='zdybdcon_th_shgxdwdh1'><span class='red'>*</span>校外住宿地址</th><td width='35%' name='zdybdcon_td_shgxdwdh1' ><input type='text' name='shgxdwdh1' value='' id='shgxdwdh1' class='text_nor' maxlength='25'></td>");
			}else{
				jQuery("th[name='zdybdcon_th_shgxdwdh1']").remove();
				jQuery("td[name='zdybdcon_th_shgxdwdh1']").remove();
				jQuery("td[name='zdybdcon_td_shgxdwdh1']").remove();
				jQuery("#shgxdwdh1").remove();
				jQuery("#zdybdcon_table_xsxx_update_lxfs > tbody ").append("<input type='hidden' name='shgxdwdh1' value='' id='shgxdwdh1' class='text_nor' maxlength='25'>");
			}
		});
	}else if("12871" == jQuery("#xxdm").val()){
		var sHtml_dxyz =  "<tr> <th>短信验证码<font color='red'>*</font></th><td colspan='3'>";
		sHtml_dxyz = sHtml_dxyz + "<input type='text' class='text_nor' id='dxyzId'/>";
		sHtml_dxyz = sHtml_dxyz + "<button style='margin-left:15px;' id='sendCode' type='button'>发送</button>";
		sHtml_dxyz = sHtml_dxyz + "<span id='sendCodeMsg' style='margin-left:15px;color:blue;font-size:12px;'>请点击发送获取验证码</span>";
		sHtml_dxyz = sHtml_dxyz + "</td></tr>";
		jQuery("#zdybdcon_table_xsxx_update_lxfs").children("tbody").append(sHtml_dxyz);
		jQuery("#sendCode").click(function(){
			sendCodeF();
		});
	}else if("10125" == jQuery("#xxdm").val()){//山西财经
		jQuery("#zdybdfl_xsxx_update_jnzs")
		.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>上传技能证书附件</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><input type='file' id='commonfileupload' name='uploadFile'/><span id='tips' style='color: red'></span>" +
				"<span id='tips' style='color: red'></span><div id='commonfileupload-list' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >处理中...</span></div>" +
				"</td></tr></tbody></table>");
		jQuery('#fjid').val(jQuery('#yyfj').val());
		initData_10125();
		if(jQuery("#shgxdwdh1").val() == "否"){
			jQuery("[name='zdybdcon_th_shgxgx2']").html("");
			jQuery("#shgxgx2").hide();
			jQuery("#shgxgx2").val("");
		}else{
			jQuery("[name='zdybdcon_th_shgxgx2']").html("<span class='red'>*</span>残疾证编号");
			jQuery("#shgxgx2").show();
		}
		jQuery("#shgxdwdh1").change(function(){
			if(jQuery("#shgxdwdh1").val() == "否"){
				jQuery("[name='zdybdcon_th_shgxgx2']").html("");
				jQuery("#shgxgx2").hide();
				jQuery("#shgxgx2").val("");
			}else{
				jQuery("[name='zdybdcon_th_shgxgx2']").html("<span class='red'>*</span>残疾证编号");
				jQuery("#shgxgx2").show();
			}
		})
	}else if("10596" == jQuery("#xxdm").val()){
		if ("是" == jQuery("#zd1").val()) {
			jQuery("#zd2").show();
		} else {
			jQuery("#zd2").hide();
		}
		jQuery("#zd1").change(function() {
			if ("是" == jQuery("#zd1").val()) {
				jQuery("#zd2").show();
			} else {
				jQuery("#zd2").hide();
			}
		});
	}else if("10704" == jQuery("#xxdm").val()){/*西安科技大学个性化附件上传，2017-11-20，Meng.Wei*/
		jQuery("#zdybdfl_xsxx_update_fjxx_10704")
		.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>上传附件</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><input type='file' id='commonfileupload' name='uploadFile'/><span id='tips' style='color: red'></span>" +
				"<span id='tips' style='color: red'></span><div id='commonfileupload-list' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >处理中...</span></div>" +
				"</td></tr></tbody></table>");
		jQuery('#fjid').val(jQuery('#zd6_fj').val());
		showAge();
	}
	jQuery("#zdybdfl_li_xsxx_update_zwjd").remove();
	jQuery("#zdybdfl_xsxx_update_zwjd").remove();
	jQuery("#zdybdfl_xsxx_update_byxx").remove();
	jQuery("#zdybdfl_li_xsxx_update_byxx").remove();
	xsGkPic();
	if("10718" != jQuery("#xxdm").val()){
		jQuery("#zdybdfl_xsxx_update_jtcyxxxg > table:eq(0) > thead > tr > th > span").append("<lable style='font-weight:normal'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家庭成员人数(除本人外)&nbsp;&nbsp;</lable>" +
		"<input type='text' name='checkjtrs'  id='checkjtrs' class='text_nor' onkeyup='checkInput(this)' style='width:35px;'>");
		var jtrs = jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg > tbody > tr").length-1;
		if(parseInt(jtrs) > 0){
			jQuery("#checkjtrs").val(jtrs);
		}
		jQuery("#checkjtrs").keydown(function(event){
	        if(event.keyCode == 13){ 
	        	var txrs = jQuery(this).val();
	        	var xyrs = jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg > tbody > tr").length-1;
	        	var checknum =parseInt(txrs) - parseInt(xyrs);
	        	if(checknum < 0){
	        		showAlert("填写的家庭成员人数小于现在已有的家庭成员数量，请确认！");
	        		return false;
	        	}
	            for(i = 0;i<checknum;i++){
	            	addConMoreUpdateTr('xsxx_update_jtcyxxxg');
	            } 
	        }
	    }); 
	}
	if(jQuery("td[name = 'zdybdcon_td_jtdzx']").length == 1){
		var jtdz = jQuery("td[name = 'zdybdcon_td_jtdz']").html();
		var jtdzval = jQuery("#jtdz").val();
		jQuery("td[name = 'zdybdcon_td_jtdz']").find("#jtdz").remove();
		jQuery("th[name = 'zdybdcon_th_jtdz']").html("");
		jQuery("td[name = 'zdybdcon_td_jtdzx']").append(jtdz);
		jQuery("#jtdz").val(jtdzval);
	}
	if("11354" == jQuery("#xxdm").val()) {
		if ("是" == jQuery("#sftb").val()) {
			jQuery("#ylbxh").show();
			jQuery("th[name='zdybdcon_th_ylbxh']").empty().append("医保卡号");
		}else{
			jQuery("#ylbxh").hide();
			jQuery("#ylbxh").val("");
			jQuery("th[name='zdybdcon_th_ylbxh']").empty();
		}
		onChange_11354();
	}
	//北京中医药大学个性化
	if("10026" == jQuery("#xxdm").val()){
		var fHtml = "北京<—>";
		var zd1Val=jQuery("#zd1").val();
		var tHtml="<input type='text' name='zd1' value='"+zd1Val+"' id='zd1' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd1']").html(fHtml+tHtml);
		jQuery("#zd1").parent().append("&nbsp;&nbsp;&nbsp;如:济南西");
		
		citySelect("zd1");
	}
	//湖南城市个性化
	if("11527" == jQuery("#xxdm").val()){
		var fHtml = "长沙<—>";
		var zd4Val=jQuery("#zd4").val();
		var tHtml="<input type='text' name='zd4' value='"+zd4Val+"' id='zd4' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd4']").html(fHtml+tHtml);
		jQuery("#zd4").parent().append("&nbsp;&nbsp;&nbsp;如:济南西");
		
		citySelect("zd4");
	}
});
//黑龙江农垦职业学院
function onChange_12727() {
	jQuery("#jkzk").change(function() {
		if ("健康" == jQuery(this).val()) {
			jQuery("#zd3").val("");
			jQuery("#zd3").parents("tr").hide();
		}else{
			jQuery("#zd3").parents("tr").show();
		}
	});
	jQuery("#jkzk").change();
}
//北京经济管理职业学院
function onChange_14073() {
	jQuery("#sfzsb").change(function() {
		if ("退出服役" == jQuery(this).val()) {
			jQuery("#sfzfx").parents("tr").show();
		}else{
			jQuery("#sfzfx").val("");
			jQuery("#sfzfx").parents("tr").hide();
		}
	});
	jQuery("#sfzsb").change();
}
//天津交通职业
function onChange_12883() {
	if ("是" == jQuery("#zd1").val()) {
		jQuery("#zd2").parents("tr").show();
	} else {
		jQuery("#zd2").parents("tr").hide();
	}
	jQuery("#zd1").change(function() {
		if ("否" == jQuery("#zd1").val()) {
			jQuery("#zd2").val("");
			jQuery("#zd2").parents("tr").hide();
		} else {
			jQuery("#zd2").parents("tr").show();
		}
	});
}
//北京商贸学校学生保险投保个性化
function onChange(){
	
	if ("是" == jQuery("#zd1").val()) {
		jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
		jQuery("#zd3").css("color", "#7D7D7D");
		jQuery("#zd3").parents("tr").show();
		jQuery("#zd2").parents("tr").show();
	} else {
		jQuery("#zd3").parents("tr").hide();
		jQuery("#zd2").parents("tr").hide();
	}
	if ("否" == jQuery("#sftb").val()) {
		jQuery("#tbsj").parents("tr").hide();
		jQuery("#sfyqrzs").parents("tr").show();
	} else {
		jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
		jQuery("#tbsj").css("color", "#7D7D7D");
		jQuery("#tbsj").parents("tr").show();
		jQuery("#sfyqrzs").parents("tr").hide();
	}
	jQuery("#sftb").change(function() {
		if ("否" == jQuery("#sftb").val()) {
			jQuery("#tbsj").val("");
			jQuery("#bxxz").val("");
			jQuery("#tbsj").parents("tr").hide();
			jQuery("#sfyqrzs").parents("tr").show();
		} else {
			jQuery("#qtyy").val("");
			jQuery("#sfyqrzs").val("");
			jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
			jQuery("#tbsj").parents("tr").show();
			jQuery("#sfyqrzs").parents("tr").hide();
		}
	});
	jQuery("#zd1").change(function() {
		if ("是" == jQuery("#zd1").val()) {
			jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
			jQuery("#zd3").parents("tr").show();
			jQuery("#zd2").parents("tr").show();
		} else {
			jQuery("#zd3").val("");
			jQuery("#zd2").val("");
			jQuery("#zd3").parents("tr").hide();
			jQuery("#zd2").parents("tr").hide();
		}
	});
	jQuery("#tbsj").focus(function() {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
			jQuery("#tbsj").css("color", "");
		}
	});
	jQuery("#tbsj").blur(function() {
		if ("" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
		}
	});
	if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#tbsj").val()) {
		jQuery("#tbsj").css("color", "#7D7D7D");
	}
	jQuery("#zd3").focus(function() {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
			jQuery("#zd3").css("color", "");
		}
	});
	jQuery("#zd3").blur(function() {
		if ("" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
		}
	});
	if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#zd3").val()) {
		jQuery("#zd3").css("color", "#7D7D7D");
	}
	jQuery("#qtyy").attr("readonly","readonly");
	jQuery("#qtyy").attr("title","该部分由班主任填写");
}
//华中师范高考照片个性化
function xsGkPic(){
	 if("10511" != jQuery("#xxdm").val()) {
			jQuery("#stuGkImg").css("display", "none");
			}
		    else{
		    	jQuery("#stuGkImg").css("display", "");
			    }
	        jQuery("#zpscbtn").css("display", "");
		    jQuery("#gkzpscbtn").css("display", "none");
}
function onShow(flag) {
	var url = "xsxx_xsxxxg.do?method=xgsq&type=query";
	var xxdm = jQuery("#xxdm").val();
	
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			xh : jQuery("#xh").val()
		},
		dataType : "json",
		success : function(data) {
			var xm = data.xm;
			jQuery("#xmView").html(xm);
			var params = {js:'xs'};//学生角色
			zdybdInit(gndm, data,params,flag);

			setXgzd(flag);// 设置修改字段
			
			//重庆理工个性化  回复函编号XG_2014-0020  第3点 --- 家庭成员信息默认显示一行且必填，可增加多行，即每个学生都至少填写一条家庭成员信息
			// 或者【家庭成员有必填字段】
			if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
					&& ("11660" == xxdm||"11067"==xxdm||"10351"==xxdm || checkJtcyxxxgSfbt())
					){ // 检查家庭成员是否有必填字段
				addConMoreUpdateTr("xsxx_update_jtcyxxxg");
			}
	}
	});

	if (parent.document.getElementById("ifm") == null) {// 强制修改页面
		isQzxg = true;
	}

	jsts();// 针对导航定位
	var kfxg = jQuery("#kfxg").val();// 可否修改,y,n
	var shjg = jQuery("#shjg").val();// 最新的记录审核结果
	var sqid = jQuery("#shzSqid").val();
	var shyj = jQuery("#shyj").val();
	var sqcs = jQuery("#sqcs").val();
	var shjgzt = jQuery("#shjgzt").val();
	if (kfxg != "n") {// 开启修改功能
		if (sqid != null && sqid != "") {
			var tip = "当前修改信息处于<font color='red'>审核中</font>，不能进行操作！";
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#zpscbtn").hide();
			if(shjgzt==""||shjgzt==null){
				jQuery("#btnTable").show();
				jQuery("#buttonSave").hide();
			}
		} else if("2"==shjg){
			var tip = "当前信息修改申请<font color='red'>不通过</font>！<br/>审核意见："+shyj;
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#btnTable").show();
		}
		else if("3"==shjg){
			var tip = "当前信息修改申请<font color='red'>已退回</font>！<br/>审核意见："+shyj;
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#btnTable").show();
			
		} else if("1"==shjg && "1"==sqcs){
			var tip = "您<font color='red'>已经修改</font>过学生信息，不能进行操作！";
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#btnTable").show();
			jQuery("#buttonSave").hide();
			jQuery("#buttonSubmit").hide();
		}
		else{
			jQuery("#btnTable").show();
		}
	} else {// 未开启修改功能
		var tip = "学生信息修改状态已关闭！";
		jQuery("#pts p").html(tip);
		jQuery("#pts").show();
		jQuery("#zpscbtn").hide();
		jQuery("#btnTable").show();
		jQuery("#buttonSave").hide();
		jQuery("#buttonSubmit").hide();
	}
}

/**
 * 设置修改字段
 * 
 * @return
 */
function setXgzd(flag) {
	var sqid = jQuery("#shzSqid").val();
	if (sqid == null || sqid == "") {
		sqid = jQuery("#dshSqid").val();
	}
	if (sqid == null || sqid == "") {
		return;
	}
	var url = "xsxx_xsxxxg.do?method=getXgzdList";
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
		}
	});
}
//北京商贸个性化投保，军训字段值重置
function initParam(){
	if ("40030" == jQuery("#xxdm").val()) {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
		}
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
		}
	} else if ("10351" == jQuery("#xxdm").val()){
		if ("可填写自我评价，包括性格特点，兴趣安好，家庭情况等描述。。。" == jQuery("#bz").val()) {
			jQuery("#bz").val("");
		}
		if ("请填写手机长号" == jQuery("#sjhm").val()) {
			jQuery("#sjhm").val("");
		}
		if ("紧急情况联系人号码" == jQuery("#jtdh").val()) {
			jQuery("#jtdh").val("");
		}
	}
	
}
function saveForm(type) {
	var xxdm = jQuery("#xxdm").val();
	if("12871" == xxdm){
		var codeFlag = checkCode();
		if(!codeFlag) return false;
	}
	initParam();
	if (!zdybdCheck()) {
		return;
	}
	//杭州职业个性化
	if ("12872" == jQuery("#xxdm").val()) {
		if(jQuery("#sjhm").val() == jQuery("#jtdh").val()){
			alertError("联系电话与家庭电话不能相同，请修改！");
			return false;
		}
	}
	
	/*浙大个性化判断校外住宿个性化判断*/
	if("10335" == jQuery("#xxdm").val()){
		if( jQuery("th[name='zdybdcon_th_shgxdwdh1']").length == 1 && jQuery.trim(jQuery("#shgxdwdh1").val()) == ''){
			alertError("校外住宿地址不可为空！");
			jQuery("input[id=shgxdwdh1]").focus();
			jQuery("input[id=shgxdwdh1]").css("border", "4px solid #15bcaa");
			jQuery("input[id=shgxdwdh1]").keyup(function(){jQuery('input[id=shgxdwdh1]').removeAttr('style');});
			return false;
		}
		if(jQuery("#shgxgzdw1").val().length != 0 && jQuery("#shgxgzdw1").val().length != 5 && jQuery("#shgxgzdw1").val().length != 6){
			alertError("校园卡账号位数控制在5-6位！");
			return false;
		}
	}

	/*山西财经保存个性化判断*/
	if("10125" == jQuery("#xxdm").val()){
		//当是否残疾字段为是时，残疾证编号才为必填。
		if(jQuery("#shgxdwdh1").val() == "是" && jQuery.trim(jQuery("#shgxgx2").val()) == ""){
			alertError("残疾证编号不可为空！");
			return false;
		}
		if(jQuery("#shgxsjhm1").val() == "是" && jQuery.trim(jQuery("#zd1").val()) == ""){
			alertError("户口迁入不可为空！");
			return false;
		}
		if(jQuery("#shgxsjhm1").val() == "否" && jQuery.trim(jQuery("#zd2").val()) == ""){
			alertError("现户口所在地不可为空！");
			return false;
		}
		if(jQuery("#shgxgzdw1").val() == "其他(填写具体保险名称)" && jQuery.trim(jQuery("#zd3").val()) == ""){
			alertError("医保名称不可为空！");
			return false;
		}
		demoHtml = "请按如下格式输入:\n从小学填至现阶段，格式为：*年*月-*年*月，*学校，曾担任学生干部职务（未担任填学生）";
		if(jQuery("#rxqwhcd").val()==demoHtml){
			jQuery("#rxqwhcd").val("");
		}
	}
	var sfbt = jQuery('#zpsfbt').val();
	var sfcz = jQuery('#zpsfcz').val();
	if (sfbt == "y" && "false" == sfcz) {
		alertError("请先上传一张照片！")
		return false;
	}
	if (!getXgzdJson()) {
//		alertError("请修改您想调整的内容！")
//		return false;
	}
	
	
	//重庆理工个性化  回复函编号XG_2014-0020  第3点 --- 家庭成员信息默认显示一行且必填，可增加多行，即每个学生都至少填写一条家庭成员信息
	// 或者【家庭成员有必填字段】
	if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
			&& ("11660" == xxdm||"11067"==xxdm||"10351"==xxdm || checkJtcyxxxgSfbt())
			){ // 检查家庭成员是否有必填字段
		alertError("至少填写一条家庭成员信息！")
		return false;
	}
	
	//江西科技师范大学  至少填写两条学历社会经历信息
	if(jQuery("#zdybdcon_table_xsxx_update_xlshjlxxxg tbody tr").length < 3
			&& ("11318" == xxdm)
			){
		alertError("至少填写两条学历社会经历信息！")
		return false;
	}
	
	if("10718" != xxdm){
		if(jQuery("#checkjtrs").val() != (jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg > tbody > tr").length-1)){
			showAlert("家庭成员人数与填写的家庭成员条数不一致！");
			return false;
		}
	}

	var url = "xsxx_xsxxxg.do?method=xgsq&type="+type;
	url += "&timestamp=" + new Date().getTime();

	jQuery("#btnTable button").attr("disabled", "disabled");
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlertDivLayer(data["message"]);
		} else {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						//强制修改
						if (isQzxg) {
						//	refershParent();
							location.href = "/xgxt/" + "stuPage.jsp";
						} else {
							jQuery("#btnTable button").removeAttr("disabled");
							var xsxgsplc = jQuery("#xsxgsplc").val();
							if (xsxgsplc != null && xsxgsplc != '' && xsxgsplc!= 'wxsh') {
								if("submit" == type) {
									var tip = "当前修改信息处于<font color='red'>审核中</font>，不能进行操作！";
									jQuery("#pts p").html(tip);
									jQuery("#pts").show();
									jQuery("#btnTable").show();
									jQuery("#buttonSave").hide();
								}
							}
					//	window.location.reload();
						}
					}
				}
			});
		}
	});

}

/*
 * 针对导航定位
 */
function jsts() {
	jQuery("a").each(function() {
		var link = jQuery(this);
		var href = link.attr("href");
		if (href && href[0] == "#") {
			var name = href.substring(1);
			jQuery(this).click(function() {
				var nameElement = jQuery("[name='" + name + "']");
				var idElement = jQuery("#" + name);
				var element = null;
				if (nameElement.length > 0) {
					element = nameElement;
				} else if (idElement.length > 0) {
					element = idElement;
				}
				if (element) {
					var offset = element.offset();
					window.parent.scrollTo(offset.left, offset.top + 85);// 85
				}
				return false;
			});
		}
	});
}
/*陕西师范大学，伤残等级，和谁一起生活页面加载默认隐藏*/
function contentHide_10718(){
	if("jkzt_002" != jQuery("#jkzk").val()){
		jQuery("#jtdzs").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_jtdzs']").empty();
	}
	if("是" != jQuery("#sfzsb").val()){
		jQuery("#zyjb").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_zyjb']").empty();
	}
	
}
/* 陕西师范大学,健康状况<-->伤残等级联动，是否孤儿<-->和谁一起生活联动 */
function onChange_10718(){
	jQuery("#jkzk").change(function() {
		if ("jkzt_002" == jQuery(this).val()) {
			jQuery("#jtdzs").show();
			jQuery("th[name='zdybdcon_th_jtdzs']").empty().append("伤残等级");
		}else{
			jQuery("#jtdzs").hide();
			jQuery("#jtdzs").val("");
			jQuery("th[name='zdybdcon_th_jtdzs']").empty();
		}
	});
	
	jQuery("#sfzsb").change(function() {
		if ("是" == jQuery(this).val()) {
			jQuery("#zyjb").show();
			jQuery("th[name='zdybdcon_th_zyjb']").empty().append("和谁一起生活");
		}else{
			jQuery("#zyjb").hide();
			jQuery("#zyjb").val("");
			jQuery("th[name='zdybdcon_th_zyjb']").empty()
		}
	});
}

function onChange_11354(){
	jQuery("#sftb").change(function() {
		if ("是" == jQuery("#sftb").val()) {
			jQuery("#ylbxh").show();
			jQuery("th[name='zdybdcon_th_ylbxh']").empty().append("医保卡号");
		}else{
			jQuery("#ylbxh").hide();
			jQuery("#ylbxh").val("");
			jQuery("th[name='zdybdcon_th_ylbxh']").empty();
		}
	});
}


/*湘潭大学学生宿舍信息确认*/
function xiangtan_ssxxqr_change_10530(){
	jQuery("#sfhq").change(function() {
		if ("否" == jQuery(this).val()) {
			showAlert("如果确认当前系统公寓宿舍信息与实际不符，请到公寓管理的宿舍异动功能中去申请宿舍异动！");
		}
	});
	jQuery("#zdybdfl_xsxx_update_new_hjqk")
	.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>上传获奖证书附件</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><input type='file' id='commonfileupload' name='uploadFile'/><span id='tips' style='color: red'></span>" +
			"<span id='tips' style='color: red'></span><div id='commonfileupload-list' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >处理中...</span></div>" +
			"</td></tr></tbody></table>");
	jQuery('#fjid').val(jQuery('#yyfj').val());
}

function initData_10125(){	
	demoHtml = "请按如下格式输入:\n";
	demoHtml += "从小学填至现阶段，格式为：*年*月-*年*月，*学校，曾担任学生干部职务（未担任填学生）";
	if(jQuery("#rxqwhcd").val()==""){
		jQuery("#rxqwhcd").val(demoHtml);
		jQuery("#rxqwhcd").css("color", "#999");
	}
	
	jQuery("#rxqwhcd").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#rxqwhcd").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
	jQuery("td[name=zdybdcon_td_zd1]").append('<br/>格式: 由___迁入___');
	
	if(jQuery("#shgxsjhm1").val() == "是" ){
		jQuery("#zd1").attr("readonly",false);
		jQuery("#zd2").attr("readonly",true);
		jQuery("th[name=zdybdcon_th_zd1]").html('<span class="red">*</span>户口迁入');
		jQuery("th[name=zdybdcon_th_zd2]").html('现户口所在地');
	}else{
		jQuery("#zd1").attr("readonly",true);
		jQuery("#zd2").attr("readonly",false);
		jQuery("th[name=zdybdcon_th_zd1]").html('户口迁入');
		jQuery("th[name=zdybdcon_th_zd2]").html('<span class="red">*</span>现户口所在地');
	}
	jQuery("#shgxsjhm1").change(function(){
		if(jQuery(this).val() == "是" ){
			jQuery("#zd1").attr("readonly",false);
			jQuery("#zd2").attr("readonly",true);
			jQuery("#zd2").val("");
			jQuery("th[name=zdybdcon_th_zd1]").html('<span class="red">*</span>户口迁入');
			jQuery("th[name=zdybdcon_th_zd2]").html('现户口所在地');
		}else{
			jQuery("#zd1").attr("readonly",true);
			jQuery("#zd2").attr("readonly",false);
			jQuery("#zd1").val("");
			jQuery("th[name=zdybdcon_th_zd1]").html('户口迁入');
			jQuery("th[name=zdybdcon_th_zd2]").html('<span class="red">*</span>现户口所在地');
		}
	})
	if(jQuery("#shgxgzdw1").val() == "其他(填写具体保险名称)" ){
		jQuery("#zd3").attr("readonly",false);
		jQuery("th[name=zdybdcon_th_zd3]").html('<span class="red">*</span>医保名称');
	}else{
		jQuery("#zd3").attr("readonly",true);
		jQuery("th[name=zdybdcon_th_zd3]").html('医保名称');
	}
	jQuery("#shgxgzdw1").change(function(){
		if(jQuery("#shgxgzdw1").val() == "其他(填写具体保险名称)" ){
			jQuery("#zd3").attr("readonly",false);
			jQuery("th[name=zdybdcon_th_zd3]").html('<span class="red">*</span>医保名称');
		}else{
			jQuery("#zd3").attr("readonly",true);
			jQuery("#zd3").val("");
			jQuery("th[name=zdybdcon_th_zd3]").html('医保名称');
		}
	})
	
}

function sendCodeF() {
   var sjhm = jQuery("#sjhm").val();
   if(null==sjhm||""==sjhm||"undefind"==sjhm||sjhm.length!=11){
	   alertError("请输入正确的手机号码!");
	   return false;
   }
  　 curCount = count;
   var url = "xsxx_xsxxxg.do?method=sendCode";
   var flag = false;
   jQuery.ajaxSetup( {async : false});
   jQuery.post(url,{'sjhm':sjhm},function(data){
	   if(data==true){
		   flag = true;
	   }
   },"json");
   jQuery.ajaxSetup( {async : true});
   if(flag){
	   jQuery("#sendCode").attr("disabled", "disabled");
	   jQuery("#sendCode").css({'background':'#ccc'});
	   jQuery("#sendCodeMsg").html("验证码已经发送,请在" + curCount + "秒内输入验证码");
	   InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
   }else{
	   alertError("获取验证码失败!");
	   return false;
   }
  
  
}

function checkCode() {
	   var flag = true;
	   var url = "xsxx_xsxxxg.do?method=checkCode";
	   var code = jQuery("#dxyzId").val();
	   if(null==code||""==code||"undefind"==code){
		   alertError("请输入短信验证码!")
		   return false;
	   }
	   jQuery.ajaxSetup( {async : false});
	   jQuery.post(url,{'code':code},function(data){
		   if(data==false){
			   alertError("验证码无效或已过时,请重新获取!");
			   flag = false;
		   }
	   },"json");
	   jQuery.ajaxSetup( {async : true});
	   return flag;
	}

	//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {                
        window.clearInterval(InterValObj);//停止计时器
        jQuery("#sendCode").removeAttr("disabled");//启用按钮
        jQuery("#sendCode").text("重新发送验证码");
        jQuery("#sendCode").css({'background':''});
        jQuery("#sendCodeMsg").html("请点击重新发送按钮获取验证码!");
    }
    else {
        curCount--;
        jQuery("#sendCodeMsg").html("验证码已经发送,请在" + curCount + "秒内输入验证码,过期无效");
    }
}

function showAge(){
	var age = calculateAges(jQuery("#csrq").val());
	var jgtr = jQuery("[name='jg']").parents("tr").eq(0);
	jgtr.before("<tr><th width='15%'>年龄</th><td width='35%'>"+age+"</td><th width='15%'></th><td width='35%' colspan='2'></td></tr>");
}

//根据出生日期计算年龄
function calculateAges(str)   
{   
      var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
      if(r==null)return "";     
      var d= new Date(r[1],r[3]-1,r[4]);     
      if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4])   
      {   
            var Y = new Date().getFullYear();   
            return((Y-r[1]));   
      }   
      return("输入的日期格式错误！");   
}  
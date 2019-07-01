var countAdd = 0;
var zjs = [];

//保存增加岗位信息，浙江旅游学院个性化   xxdm=12867
function zjBcGwsqForZjlyzy(type){
	var flg = true;
	var jbtbodys = jQuery("tbody[name='tbody_jbxx']");
	var tbodys = jQuery("tbody[name='xgTbody']");
	var zjtbodys = jQuery("tbody[name='zjTbody']");
	
	if(jbtbodys.length > 0){
			var lxr = jQuery("#lxr").val();
			var lxPhone = jQuery("#lxPhone").val();
			var flgg = checkGwsqrxx(lxr.trim(),lxPhone.trim());
			if(!flgg){
				flg = false;
				return false;
			}else{
					var zjxx = lxr+','+lxPhone+'';
					zjs.push(zjxx);
				}
			
			if(!flg){
				showAlertDivLayer("请填写联系人和联系电话！");
				return false;
			}
	}
	if(tbodys.length > 0){
		jQuery.each(tbodys,function(i,n){
			var inputs = jQuery(n).find("input");
			var gwmc = jQuery(inputs).eq(0).val();
			var xqrs = jQuery(inputs).eq(1).val();
			var obj = document.getElementById("gwlx");
			for (i = 0; i < obj.length; i++) {
				if(obj[i].selected == true) {
					var gwlx = obj[i].value;
				}
			}
			var knsrs = jQuery(inputs).eq(2).val();
			var gwshr = jQuery(inputs).eq(3).val();
			var gwshrxm = jQuery(inputs).eq(4).val();
			var textareas = jQuery(n).find("textarea");
			var gwryyq = jQuery(textareas).eq(0).val();
			var gznr = jQuery(textareas).eq(1).val();
			var flgg = checkGwsqxx(gwmc.trim(),xqrs.trim(),gwlx.trim(),knsrs.trim(),gwshr.trim(),gwshrxm.trim(),gwryyq.trim());
			if(!flgg){
				flg = false;
				return false;
			}else{			
				var xsxx = gwmc+','+xqrs+','+gwlx+','+knsrs+','+gwshr+','+gwshrxm+','+gwryyq+',';
				
				if(gznr == null || gznr == ''){
					xsxx+='blank';
				}else{
					xsxx+=gznr;
				}
				zjs.push(xsxx);
			}
			if(!flg){
				showAlertDivLayer("请填写带*必填项！");
				return false;
			}
		})
		
	}
	if(zjtbodys.length>0){
		//var gwlx = jQuery("#gwlx").val();
		jQuery.each(zjtbodys,function(i,n){
			var inputs = jQuery(n).find("input");
			var selects = jQuery(n).find("select");
			var gwmc = jQuery(inputs).eq(0).val();
			var xqrs = jQuery(inputs).eq(1).val();
			var gwlx =  jQuery(selects).eq(0).val();
			var knsrs = jQuery(inputs).eq(2).val();
			var gwshr = jQuery(inputs).eq(3).val();
			var gwshrxm = jQuery(inputs).eq(4).val();
			var textareas = jQuery(n).find("textarea");
			var gwryyq = jQuery(textareas).eq(0).val();
			var gznr = jQuery(textareas).eq(1).val();
			var flgg = checkGwsqxxZj(gwmc.trim(),xqrs.trim(),gwlx.trim(),knsrs.trim(),gwshr.trim());
			if(!flgg){
				flg = false;
				return false;
			}else{		
				if(gwryyq == null || gwryyq == ''){
					flg = false;
					return false;
				}else if(parseInt(xqrs.trim())<parseInt(knsrs.trim())){
					alertInfo(gwmc + "岗位的困难生数不能大于需求人数!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}else {
					var zjxx = gwmc+','+xqrs+','+gwlx+','+knsrs+','+gwshr+','+gwshrxm+','+gwryyq+',';
					
					if(gznr == null || gznr == ''){
						zjxx+='blank';
						}else{
						zjxx+=gznr;
						}
					zjs.push(zjxx);
					}
				}
			if(!flg){
				showAlertDivLayer("请填写带*必填项！");
				return false;
			}
		})
	}
	var url = 'qgzx_gwglnew_ajax.do?method=bcZjGwsqForZjlyzy&type=' + type;
	var params = {};
	if(zjs.length>0){
		params["zjs"] = zjs;
	}
	jQuery.ajaxSetup({async:false});
	jQuery.post(url,params,function(data){
		if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   		 	if(parent.window){
   		 		refershParent();
				}
   			 var zjbodys = jQuery("tbody[name='zjTbody']");
   			 if(zjbodys.length > 0){
   				 jQuery(zjbodys).each(function(i,n){
   					jQuery(zjbodys[i]).attr("name","xgTbody");
   				 })
   			 }
   			 zjs = [];
		 }});
   	 	}else{
   		 showAlert(data["message"]);
   		}
	},'json');
	jQuery.ajaxSetup({async:true});
	
}

//增加
function showAdd(){
	
	var kycz = jQuery("#kycz").val();
	alert(kycz);
	if("true"!=kycz){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
 		return false;
	}
	//showTopWin('qgzx_gwglnew.do?method=gwsqZj',720,500);
	showDialog('岗位申请', 760, 520, 'qgzx_gwglnew.do?method=gwsqZj');
}


//验证岗位申请信息
function checkGwsqxx(gwmc,xqrs,gwlx,knsrs,gwshr,gwshrxm,gwryyq){
	if(gwmc == null || gwmc == '' || xqrs == null || xqrs == '' || gwlx == null || gwlx == '' || knsrs == null || knsrs == '' || gwshr == null || gwshr == '' || gwshrxm == null || gwshrxm == '' || gwryyq ==null || gwryyq == ''){
		return false;
	}else{
		return true;
	}
}

//验证岗位申请信息
function checkGwsqxxZj(gwmc,xqrs,gwlx,knsrs,gwshr){
	if(gwmc == null || gwmc == '' || xqrs == null || xqrs == '' || gwlx == null || gwlx == '' || knsrs == null || knsrs == '' || gwshr == null || gwshr == ''){
		return false;
	}else{
		return true;
	}
}

//验证岗位申请人信息
function checkGwsqrxx(lxr,lxPhone){
	if(lxr == null || lxr == '' || lxPhone == null || lxPhone == ''){
		return false;
	}else{
		return true;
	}
}

//在每一次需求岗位数失去焦点的时候，判断多条增加岗位信息是否大于需求岗位数
function changeGwxx(type){
	var count = jQuery("#xqgws").val();
	if(count >20){
		alertInfo("需求岗位数不能超过20个!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if(count < countAdd){
		alertInfo("需求岗位数不能小于已增加岗位申请个数!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	zjGwxx("firstZj");
}
//
function zjGwxx(type){
	var count = jQuery("#xqgws").val();
	if("jiahao" == type){
		count++;
		jQuery("#xqgws").val(count);
	}
	for (; countAdd < count;) {
		addRow();
	}
	
}

//浙江旅游学院个性化     用人单位岗位申请
function addRow(){
	var count = jQuery("#xqgws").val();
			html="";
			html+="<tbody id='tr_"+countAdd+"' name='zjTbody'>"
			html+="<tr name='tr_"+countAdd+"'>";
			html+="<th width='16%'><span><font color='red'>*</font></span>岗位名称</th>";
			html+="<td width='32%'><input type='text' name='gwmc' maxlength='10'></input></td>";
		    html+="<th width='16%'><span><font color='red'>*</font></span>需求人数</th>";
		    html+="<td width='20%'><input type='text' name='xqrs' maxlength='10'></input></td>";
		    html+="<th width='16%' style='text-align: center;'>操作</th>";
		    html+="</tr>";
		    html+="<tr name='tr_"+countAdd+"'>";
		    html+="<th><span><font color='red'>*</font></span>岗位类型</th>";
			html+="<td><select name='gwlx' id='gwlx'><option value=''></option><option value='固定岗'>固定岗</option><option value='实习岗'>实习岗</option></select></td>";	
			html+="<th><span><font color='red'>*</font></span>困难生数</th>";
			html+="<td><input type='text' name='knsrs' maxlength='20'></input></td>";
			html+="<td rowspan='5' style='text-align: center'><a href='javascript:void(0)' onclick='scZj(\"tr_"+countAdd+"\")'>删除</a></td></tr>";
			html+="<tr id=\"zxsInfo\" name='tr_"+countAdd+"'>";
			html+="<th><span><font color='red'>*</font></span>岗位审核人工号</th>";
			html+="<td ><input type='text'  id='gwshr_"+countAdd+"' class='gwshr'  name='gwshr_"+countAdd+"' style='width:80px;' readonly='readonly' /><button type='button' " +
					"onclick=\"showDialog('教师选择',680,480,'qgzx_gwglnew.do?method=showFdysDg&idxh="+countAdd+"');return false;\" class='btn_01' id='buttonFindStu'>选择</button></td>";
			html+="<th><span><font color='red'>*</font></span>岗位审核人姓名</th>";
			html+="<td width='30%' class='xm'><input type='text' id='gwshrxm_"+countAdd+"' class='gwshrxm' name='gwshrxm_"+countAdd+"' style='width:120px;' readonly='readonly'></input></td></tr>";
			html+="<tr name='tr_"+countAdd+"'>";
			html+="<th><span><font color='red'>*</font></span>岗位要求<br /><font color='red'>&lt;限1000字&gt;</th>";
			html+="<td colspan='3'><textarea rows='4' style='width: 99%' onblur='checkLen(this,1000);'></textarea></td></tr>";
			html+="<tr name='tr_"+countAdd+"'>";
			html+="<th><span><font color='red'>*</font></span>工作内容<br /><font color='red'>&lt;限500字&gt;</th>";
			html+="<td colspan='3'><textarea rows='4' style='width: 99%' onblur='checkLen(this,500);'></textarea></td></tr>";
			html+="</tbody>";
			jQuery("#gwsqxx").append(html);
			countAdd++;
}
function scZj(tr){
	var count = jQuery("#xqgws").val();
	if(jQuery("#"+tr).find("input[type='hidden']").length>1){
		var input = jQuery("#"+tr).find("input").eq(0);
		var delId = input.val();
		delIds.push(delId);
		
	}
	jQuery("#xqgws").val(count*1-1);
	jQuery("#"+tr).remove();
	countAdd--;
	
}

//验证需求人数
function checkXqrs(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}


/*
 * 验证电话号码
 */
function checkPhone(obj) {
	var text = obj.value;
	if (null !== text && ''!= text) {
		if(text.match(/^[1][3,4,5,7,8][0-9]{9}$/)==null){
			showAlertDivLayer("您输入的电话不符合规则!",{},{"clkFun":function(){
				obj.focus();
			}});
		 return false;
	 }
	}
}


//验证修改岗位信息
function bcXgGwsqForZjlyzy(type){
	if($("lxr") && $("lxr").value==""){
		alertInfo("联系人不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	if($("lxPhone") && $("lxPhone").value==""){
		alertInfo("联系电话不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	if($("gwmc") && $("gwmc").value==""){
		alertInfo("岗位名称不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	/*var message = "";//checkBcInfo("update");
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}*/
	if($("gwlx") && $("gwlx").value==""){
		alertInfo("岗位类型不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	if($("xqrs") && $("xqrs").value==""){
		alertInfo("需求人数不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	if($("knsrs") && $("knsrs").value==""){
		alertInfo("困难生数不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		alertInfo("困难生数不能大于需求人数!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if($("gwms") && $("gwms").value==""){
		alertInfo("工作内容不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	if($("gwryyq") && $("gwryyq").value==""){
		alertInfo("岗位要求不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
		}
	saveXgGwxxInfo(type);	
}

//保存修改岗位信息
function saveXgGwxxInfo(type){
	// 得到JSON对象
  var parameter ={};
  	parameter["lxr"]=jQuery("#lxr").val().trim();
  	parameter["lxPhone"]=escape(jQuery("#lxPhone").val().trim());
  	parameter["pkValue"]=escape(jQuery("#pkValue").val());
  	parameter["sqsj"]=jQuery("#sqsj").val();
	parameter["gwmc"]=jQuery("#gwmc").val().trim();
	parameter["gwxzdm"]=jQuery("#gwxzdm").val();
	parameter["xqrs"]=escape(jQuery("#xqrs").val().trim());
	parameter["knsrs"]=escape(jQuery("#knsrs").val().trim());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=jQuery("#gwshrxm").val().trim();
	parameter["gwms"]=jQuery("#gwms").val().trim();
	parameter["gwryyq"]=jQuery("#gwryyq").val().trim();
	parameter["type"]=encodeURI(encodeURI(type));
	var url = "qgzx_gwglnew_ajax.do?method=bcXgGwsqInfo&type="+type;
	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.ajaxSetup({async:false});	
	
	ajaxSubFormWithFun("form",url,function(data){
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
	jQuery.ajaxSetup({async:true});
}



/**
 * 查询
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "jcftz_jg.do?method=addJcftzJg";
	var title = "增加";
    showDialog(title, 900, 450, url);	
}

//查看
function xmmcLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["xmckjgid"] + "\");'>" + cellValue + "</a>";
}

function XmsbjgView(jgid){
	showDialog("项目申报查看", 800, 500, "xmsbXmsbjg.do?method=viewXmsbjg&jgid="
			+ jgid);
}

//查看学生链接
function xhLink(cellValue, rowObject){
	if(rowObject["csmsmc"] == "个人"){
		return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
		+ rowObject["xsckjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
		+ "</a>";
	}else if(rowObject["csmsmc"] == "团体"){
		return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
		+ rowObject["xmdm"] + "\",\"" + cellValue + "\");'>" + cellValue
		+ "</a>";
	}else{
		return cellValue;
	}
	
}

function SqjgView(jgid, xh){
	showDialog("查看", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
			+ jgid + "&xh=" + xh);
}

function TtsqView(xmdm, xh) {
	showDialog("团体拓展项目结果查看", 770, 450, "ttxm_jg.do?method=TtsqView2&xmdm="
			+ xmdm+"&xh="+xh);
}

//增加结果保存
function saveSqjg(type){
	var ids = "xmdm";
	var csms = jQuery("#csms").val();
	var flg = true;
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var xhs = [];
	var jxdms = [];
	var sfqqs = [];
	var tzhjcfs = [];
	var jgids = [];
	var trs = jQuery("#tbody_dhryxx").find("tr");
	jQuery.each(trs,function(i,n) {
		if(type == 'update' || (type == 'save' && csms == "2")){			
			jgids.push(jQuery(n).find("td").eq(0).find("input").eq(1).val());
		}
		xhs.push(jQuery(n).find("td").eq(1).html());
		if(jQuery(n).find("td").eq(4).find("input").eq(0).val() == '' || jQuery(n).find("td").eq(4).find("input").eq(0).val() == null){
			flg = false;
			return false;
		}
		tzhjcfs.push(jQuery(n).find("td").eq(4).find("input").eq(0).val());
		if(jQuery(n).find("td").eq(5).find("select").eq(0).val() == null || jQuery(n).find("td").eq(5).find("select").eq(0).val() == ''){
			jxdms.push("0");
		}else{
			jxdms.push(jQuery(n).find("td").eq(5).find("select").eq(0).val());
		}		
		sfqqs.push(jQuery(n).find("td").eq(6).find("select").eq(0).val());
	})
	if(!flg){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
		}
	jQuery("#xhs").val(xhs);
	jQuery("#jxdms").val(jxdms);
	jQuery("#sfqqs").val(sfqqs);
	jQuery("#tzhjcfs").val(tzhjcfs);
	if(type == 'update' || (type == 'save' && csms == "2")){
		jQuery("#jgids").val(jgids);
	}	
	var url = "jcftz_jg.do?method=savejg&type=" + type;
	ajaxSubFormWithFun("jcftzjgForm", url, function(data) {
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
}

function saveSqjgUpdate(type){
	if(null==jQuery("#jxid").val()){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "jxgl_xnjxsq.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XnjxsqForm", url, function(data) {
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
}

//删除结果
function del(){
	var flg = true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	jQuery.each(rows,function(i,n){
		if(n["lylcywid"] != null && n["lylcywid"] != ''){
			flg = false;
			return false;
		}
	})
	if(!flg){
		showAlertDivLayer("不能删除来自于审核流的记录！");
		return false;
	}else {
		var xmdms = [];
		jQuery.each(rows,function(i,n){
			xmdms.push(n["xmdm"]);
		})
		showConfirmDivLayer("此操作将删除相关项目下的所有学分认定？",{"okFun":function(){
			jQuery.post("jcftz_jg.do?method=delJg",{values:ids.toString(),xmdms:xmdms.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		if (rows[0]['lylcywid'] != null && rows[0]['lylcywid'] != "") {
			showAlertDivLayer("审批流程过来的记录不能修改！");
			return false;
		}	
		var url = 'jcftz_jg.do?method=editJcftzJg&xmdm=' + rows[0]["xmdm"];
		var title = "修改";
		showDialog(title, 900, 500, url);
	}
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//function auotSetCanCheck(){
//	jQuery("tr[name=checkxm]").each(function(){
//		var kcyrs = parseInt(jQuery.trim(jQuery(this).find("td[name=kcyrs]").text()));
//		var tgrs  = parseInt(jQuery.trim(jQuery(this).find("td[name=syme]").text()));
//		if(kcyrs-tgrs == 0){
//			jQuery(this).find("[name=checkbox]").attr("disabled",true);
//		}
//	});
//}

/**
 * 选择项目页，切换已申请、未申请
 * @param obj
 * @param tabId
 * @return
 */
//function selectSqxm(obj,tabId){
//
//	jQuery("#comp_title li").removeClass("ha");
//	jQuery(obj).parent().addClass("ha");
//
//	jQuery("#xmList tbody").css("display","none");
//	jQuery("#"+tabId).css("display","");
//	
//	if (tabId == "wrd"){
//		jQuery("#titleTr td").last().css("display","none");
//	} else {
//		jQuery("#titleTr td").last().css("display","");
//	}
//}

/**
 * 确定选择项目
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("input[name='checkbox']:checked");
	var test = api.get('parentDialog');
	if(selectBox.length != 1){
		showAlert("请选择一项活动，进行选择！");
		return false;
	}
		var xmdm = jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmdm']").val();
	  	var xmmc = jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmmc']").val();
		test.jQuery("#xmjbmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmjbmc']").val());
		test.jQuery("#sbbmmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sbbmmc']").val());
		test.jQuery("#lxdh").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'lxdh']").val());
		test.jQuery("#sskmmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sskmmc']").val());
		test.jQuery("#jcxf").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'jcxf']").val());
		test.jQuery("#kcyrs").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'kcyrs']").val());
		test.jQuery("#sbr").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sbrxm']").val());
		test.jQuery("#xmkssj").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmkssj'] ").val());
		test.jQuery("#sfsljxmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sfsljxmc'] ").val());
		test.jQuery("input[name='xmdm']").val(jQuery(selectBox).parent().parent().find("td:eq(0) input").val());
		test.jQuery("#xmmc").val(xmmc);
		test.jQuery("#jgid").val(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'jgid']").val());
		test.jQuery("#xn").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xn']").val());
		test.jQuery("#xq").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xqmc']").val());
		test.jQuery("#csms").val(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'csms']").val());
		var csms = jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'csms']").val();
		test.jQuery("#xmlx").text(csms=='1' ? "个人" : "团体");
		var xxdm=test.jQuery("#xxdm").val();
		var jxhtml = "";
    	var rdhtml = "";
    	var jxids = [];
		var jxmcs = [];
		var fjfss = [];
		jQuery.ajaxSetup({async:false});
		jQuery.post("jxgl_xnjxsq.do?method=getjxxx",{xmdm:xmdm},function(data){
            	for(var i=0;i<data.length;i++){           		
            		jxhtml+="<tr><input type='hidden' name='jxId' value='"+data[i].jgid+"'/><td>"+data[i].jxmc+"</td><td>"+data[i].fjxf+"</td><td>"+data[i].xssx+"</td><tr>";
            		jxids.push(data[i].jgid);
					jxmcs.push(data[i].jxmc);
					fjfss.push(data[i].fjxf);
            	}
            	test.jQuery("#tbody_xmjxxx").html(jxhtml);
			}, 'json');
		jQuery.post("jcftz_jg.do?method=getXsListForRenDingForAjax", {
			xmdm:xmdm,csms:csms
		}, function(data) {	
			if(csms == "1"){
				test.jQuery("#grthead").show();
				test.jQuery("#ttthead").hide();
				for(var i=0;i<data.length;i++){           		
            		rdhtml+="<tr><td><input type='checkbox'/></td><td>"+data[i].xh+"<input type='hidden' name= 'xh' value='"+data[i].xh+"'/></td><td>"+data[i].xm+"</td><td>"+data[i].bjmc+"</td><td>";
            		rdhtml+= "<input name='tzhjcf' onblur=\"savefs(this,'";
            		rdhtml+=data[i].jgid;
            		rdhtml+="','";
            		rdhtml+=data[i].jcxf;
            		rdhtml+="')\" type='text'  onkeyup='checkInputNum(this);'";
            		rdhtml+=" style='width:100%;' maxlength='4' value='";
            		rdhtml+=data[i].tzhjcf==null ? data[i].jcxf : data[i].tzhjcf;
            		rdhtml+="' min='0' max='";
            		rdhtml+=data[i].jcxf;
            		rdhtml+="' /><td>";
            		rdhtml+= "<select name='jxdm' onblur=\"saveJx(this,'";
            		rdhtml+=data[i].jgid;
            		rdhtml+="')\"";
            		rdhtml+=" style='width:100px;' value='";
            		rdhtml+=data[i].jxdm==null?"":data[i].jxdm;
            		rdhtml+="'" 
            		rdhtml+=" >";
            		rdhtml+="<option value=''></option>"
					for(var j = 0;j<jxids.length;j++){
						rdhtml+="<option value ='";
						rdhtml+=jxids[j];
						rdhtml+="'";
						if(data[i].jxdm==jxids[j]){
							rdhtml+="selected='selected'"
						}
						rdhtml+=">";
						rdhtml+=jxmcs[j];
						rdhtml+="</option>";
					}
					rdhtml+="</select></td><td>";
					rdhtml+= "<select onblur=\"saveQq(this,'";
					rdhtml+=data[i].jgid;
					rdhtml+="')\"";
					rdhtml+=" style='width:40px;' name='sfqq' value='";
					rdhtml+=data[i].sfqq==null ? "0" : data[i].sfqq;
					rdhtml+="'" 
					rdhtml+=" >";
					if(data[i].sfqq == '1'){
						rdhtml+="<option value ='0'>否</option>";
						rdhtml+="<option value ='1' selected='selected'>是</option>";
					}else{
						rdhtml+="<option value ='0' selected='selected'>否</option>";
						rdhtml+="<option value ='1'>是</option>";
					}			
            		rdhtml+="</td><td>"+data[i].zf+"</td>";
            		
            		//备注1-5
            		if("13627"==xxdm){
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz1')\" type='text' name='bz1' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz1==null?"":data[i].bz1;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz2')\" type='text' name='bz2' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz2==null?"":data[i].bz2;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz3')\" type='text' name='bz3' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz3==null?"":data[i].bz3;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz4')\" type='text' name='bz4' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz4==null?"":data[i].bz4;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz5')\" type='text' name='bz5' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz5==null?"":data[i].bz5;
            		rdhtml+="' /></td>";
            		}
            		rdhtml+="</tr>";
            	}
			}else if(csms == "2"){
				test.jQuery("#grthead").hide();
				test.jQuery("#ttthead").show();
				for(var i=0;i<data.length;i++){           		
            		rdhtml+="<tr><td><input type='checkbox'/><input type='hidden'  name='jgidss' value='"+data[i].jgid+"'/></td><td>"+data[i].tdmc+"</td><td>"+data[i].xm+"</td><td>"+data[i].xymc+"</td><td>";
            		rdhtml+= "<input onblur=\"savefs(this,'";
            		rdhtml+=data[i].jgid;
            		rdhtml+="','";
            		rdhtml+=data[i].jcxf;
            		rdhtml+="')\" type='text' onkeyup='checkInputNum(this);'";
            		rdhtml+=" style='width:50px;' name='tzhjcf' maxlength='4' value='";
            		rdhtml+=data[i].tzhjcf==null ? data[i].jcxf : data[i].tzhjcf;
            		rdhtml+="' min='0' max='";
            		rdhtml+=data[i].jcxf;
            		rdhtml+="' /><td>";
            		rdhtml+= "<select name='jxdm' onblur=\"saveJx(this,'";
            		rdhtml+=data[i].jgid;
            		rdhtml+="')\"";
            		rdhtml+=" style='width:100px;' value='";
            		rdhtml+=data[i].jxdm==null?"":data[i].jxdm;
            		rdhtml+="'" 
            		rdhtml+=" >";
            		rdhtml+="<option value=''></option>"
					for(var j = 0;j<jxids.length;j++){
						rdhtml+="<option value ='";
						rdhtml+=jxids[j];
						rdhtml+="'";
						if(data[i].jxdm==jxids[j]){
							rdhtml+="selected='selected'"
						}
						rdhtml+=">";
						rdhtml+=jxmcs[j];
						rdhtml+="</option>";
					}
					rdhtml+="</select></td><td>";
					rdhtml+= "<select onblur=\"saveQq(this,'";
					rdhtml+=data[i].jgid;
					rdhtml+="')\"";
					rdhtml+=" style='width:40px;' name='sfqq' value='";
					rdhtml+=data[i].sfqq==null ? "0" : data[i].sfqq;
					rdhtml+="'" 
					rdhtml+=" >";
					if(data[i].sfqq == '1'){
						rdhtml+="<option value ='0'>否</option>";
						rdhtml+="<option value ='1' selected='selected'>是</option>";
					}else{
						rdhtml+="<option value ='0' selected='selected'>否</option>";
						rdhtml+="<option value ='1'>是</option>";
					}			
            		rdhtml+="</td><td>"+data[i].zf+"</td>";
            		
            		//备注1-5
            		if("13627"==xxdm){
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz1')\" type='text' name='bz1' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz1==null?"":data[i].bz1;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz2')\" type='text' name='bz2' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz2==null?"":data[i].bz2;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz3')\" type='text' name='bz3' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz3==null?"":data[i].bz3;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz4')\" type='text' name='bz4' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz4==null?"":data[i].bz4;
            		rdhtml+="' /></td>";
            		rdhtml+= "<td><input onblur=\"savebz(this,'"+data[i].jgid+"','bz5')\" type='text' name='bz5' ";
            		rdhtml+=" style='width:50px;' maxlength='20' value='";
            		rdhtml+=data[i].bz5==null?"":data[i].bz5;
            		rdhtml+="' /></td>";
            		}
            		rdhtml+="</tr>";
            	}
				
			}else{
				rdhtml ="";
			}
            	
            	test.jQuery("#tbody_dhryxx").html(rdhtml);
			}, 'json');
		jQuery.ajaxSetup({async:true});		
	    iFClose();
}

function jxLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jxView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

function jxView(id) {
	showDialog("奖项申请查看", 800, 500, "jxgl_xnjxsq.do?method=viewJx&id="+id);
}


function showxmxx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_xmxx").toggle();
}

function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	
	
	if (tabId == "yrd"){
		jQuery.ajaxSetup({async:false});
		jQuery.post("jcftz_jg.do?method=getXmSelectListForAjax", {
			type:"yrd"
		}, function(data) {
			    var html ="";      		
            	for(var i=0;i<data.length;i++){           		
            		html+="<tr><td style='display:none'></td><td>"+data[i].xmmc+"</td><td>"+data[i].xmjbmc+"</td><td>"+data[i].sskmmc+"</td><td>"+data[i].xmkssj+"</td><td>"+data[i].sbbmmc+"</td><td>"+data[i].sbrxm+"</td></tr>";          		
            	}
            	jQuery("#yrd").html(html);
			}, 'json');
		jQuery.ajaxSetup({async:true});
		jQuery("#titleTr td").eq(0).css("display","none");
		jQuery("#yrd").css("display","");
		jQuery("#wrd").css("display","none");
		jQuery("#qd").css("display","none");
	} else {
		var url = "jcftz_jg.do?method=getXmSelectListForAjax&type=wrd";
		jQuery("#yrd").css("display","none");
		jQuery("#wrd").css("display","");
		jQuery("#titleTr td").eq(0).css("display","");
		jQuery("#qd").css("display","");
	}
}

function savefs(obj,jgid,jcf){
	var str = jQuery(obj).val();
	var csms = jQuery("#csms").val();
	if(str != '' && str != null){
		if(parseFloat(jQuery(obj).val())<0){
			showAlertDivLayer("不允许输入负数！",{},{"clkFun":function(){
				obj.focus();
			}});
			return false;
		}
		if(parseFloat(jQuery(obj).val())>1){
			if (!/^[1-9]\d*([.][0-9])?$/.test(str)) {
				showAlertDivLayer("不能输入首位为0的数字！",{},{"clkFun":function(){
					obj.focus();
				}});
				return false;
			 }
		}
		if(parseFloat(jQuery(obj).val())<1){
			if (!/^[0]\d*([.][1-9])?$/.test(str)) {
				showAlertDivLayer("不能输入为0的数字！",{},{"clkFun":function(){
					obj.focus();
				}});
				return false;
			 }
		}		
		var reg = /^[0-9]+(.[0-9]{1})?$/;
		var result = reg.test(str);
		if(!result){
			showAlertDivLayer("分值只允许输入一位小数！",{},{"clkFun":function(){
				obj.focus();
			}});
			return false;
		}
		if(parseFloat(jQuery(obj).val()) > parseFloat(jcf)){
			showAlertDivLayer("调整后基础分不能大于原先基础分，请确认！",{},{"clkFun":function(){
				obj.focus();
			}});		
			return false;
		}
	}else{
		showAlertDivLayer("调整后基础分不能为空，请输入！",{},{"clkFun":function(){
			obj.focus();
		}});
		    return false;
	}		
	var fz = obj.value;
	jQuery(obj).val(fz);	
	jQuery.ajaxSetup({async:false});
	var jxdm = jQuery(obj).parent().parent().find("td").eq(5).find("select").eq(0).val();
	if(jxdm== ''|| jxdm == null){
		jQuery(obj).parent().parent().find("td").eq(7).html(fz);
	}else{
		jQuery.post("jcftz_sq.do?method=getFs",{jxdm:jxdm},function(data){
			jQuery(obj).parent().parent().find("td").eq(7).html(Number(fz)+Number(data));	
		},'json');
	}	 
	jQuery.ajaxSetup({async:true});
	
}

function saveQq(obj,jgid){
	var sfqq = obj.value;
	jQuery(obj).val(sfqq);
}
	
function saveJx(obj,jgid){
	var jxdm = obj.value;
	jQuery.ajaxSetup({async:false});
	var fz = jQuery(obj).parent().parent().find("td").eq(4).find("input").eq(0).val();
	if(jxdm== ''|| jxdm == null){
		jQuery(obj).parent().parent().find("td").eq(7).html(fz);
	}else{
		jQuery.post("jcftz_sq.do?method=getFs",{jxdm:jxdm},function(data){
			jQuery(obj).parent().parent().find("td").eq(7).html(Number(fz)+Number(data));	
		},'json');
	}	 
	jQuery.ajaxSetup({async:true});		
	jQuery(obj).val(jxdm);
}
	
function checkNumberinput(obj){
	if(parseInt(jQuery(obj).val()) > 100){
		showAlert("分值最大为100，请确认！");
		return false;
	}
	
}

//保存备注
function savebz(obj,jgid,xm){
//	var csms = jQuery("#csms").val();
//	var str = jQuery(obj).val();
//	//临时将xm设定为要修改的列名，bz1为修改的值
//	jQuery.post("jcftz_sq.do?method=saveJcftzSq",{jgid:jgid,csms:csms,xm:xm,bz1:str},function(data){
//		if (data){
//			showAlert(data["message"]);
//		}
//	},"json");
}


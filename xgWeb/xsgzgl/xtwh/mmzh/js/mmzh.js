function checkuser(){
	            if(jQuery("#yhm").val() == ''){
	            	showAlert("用户名不可为空！");
	            	return false;
	            }
				var url = "mmzhgl_mmzh.do?method=checkYh&type=check";
				ajaxSubFormWithFun("MmZhForm", url, function(data) {
					 if(data["message"]=="用户名验证成功！"){
			    			 window.location = "mmzhgl_mmzh.do?method=zhfs&yhm="+jQuery("#yhm").val();
			    	 }else{
			    		// jQuery("td[name='tdyz']").html("");
			    		 showAlert(data["message"]);
			    		}
					});
}

function checkwtda(){
    if(jQuery("#wtda").val() == ''){
    	showAlert("密保答案不可为空！");
    	return false;
    }
    if (jQuery("#yzm_t").val() == "" || jQuery("#yzm_t").val().length != 4) {
		showAlert("验证码不合法！");
		jQuery("#yzm_t").focus();
		return false;
	} 
	var url = "mmzhgl_mmzh.do?method=checkMmbh&type=check";
	ajaxSubFormWithFun("MmZhForm", url, function(data) {
		 if(data["message"]=="密保问题验证成功！"){
   			 window.location = "mmzhgl_mmzh.do?method=Updatemm&type=view&yhm="+jQuery("#yhm").val();
   	 }else{
   		 showAlert(data["message"]);
   		 jQuery("#yzmImg").attr("src","yzm.jsp?rand="+new Date().getTime());
   		}
		});
}


function tj(){
	var url = "mmzhgl_mmzh.do?method=xgmm&type=update";
	ajaxSubFormWithFun("MmZhForm", url, function(data) {
		 if(data["message"]=="密码更新成功，请重新登录！"){
   		     showAlert(data["message"],{},{"clkFun":function(){
   		    	 window.close();
		  }});
   	 }else{
   		 showAlert(data["message"]);
   		}
    });
}

function mbsz(){
    if(jQuery("#wtid").val() == ''){
        showAlertDivLayer("密保问题不可为空！");
        return false;
    }
    if(jQuery("#wtda").val() == ''){
    	showAlertDivLayer("密保答案不可为空！");
    	return false;
    }
	var url = "mmzhgl_mmzh.do?method=MbSz&type=sz";
	ajaxSubFormWithFun("MmZhForm", url, function(data) {
		 if(data["message"]=="密保问题设置成功！"){
			 showAlertDivLayer(data["message"],{},{"clkFun":function(){
   		    	window.location.reload();
		     }});
   	     }else{
   		    showAlertDivLayer(data["message"]);
   		 }
    });
}
function bc(){
    var url = "mmzhgl_mmzh.do?method=mmzhfs&type=bc";
    ajaxSubFormWithFun("MmZhForm", url, function(data) {
        if(data["message"]=="保存成功！"){
            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                window.location.reload();
            }});
        }else{
            showAlertDivLayer(data["message"]);
        }
    });
}
function checkZhfs(){
    var a = jQuery("input[type='radio']:checked");
    if(a.length == 0){
        jQuery.alert("请选择找回方式！")
    } else{
        var url = "mmzhgl_mmzh.do?method=zhfs&type=check&zhfs="+a[0].value;
        ajaxSubFormWithFun("MmZhForm", url, function(data) {
            if(data["success"]=="true"){
                window.location.href="mmzhgl_mmzh.do?method=yzxx&yhm="+jQuery("#yhm").val() + "&zhfs="+a[0].value;
            }else{
                var tip = jQuery("#verifTip1");
                if("email" == a[0].value){
                    tip.html("账号未预留电子邮箱,请更换其他密码找回方式!");
                } else {
                    tip.html("账号未预留手机号码,请更换其他密码找回方式!");
                }
                tip.attr('style',"display:block");
            }
        });
    }
}

function checkyzxx(){
    if("email"==jQuery("#zhfsHd").val()){
        if(jQuery("#zhfszh").val() == ''){
            showAlert("请输入邮箱！");
            return false;
        }
        if(!isEmail(jQuery("#zhfszh").val())){
            showAlert("邮箱格式不正确！");
            return false;
        }
    } else {
        if(jQuery("#zhfszh").val() == ''){
            showAlert("请输入联系电话！");
            return false;
        }
        if(!isTelephone("zhfszh")){
            showAlert("手机号格式不正确！");
            return false;
        }
    }
    var url = "mmzhgl_mmzh.do?method=yzxx&type=check";
    ajaxSubFormWithFun("MmZhForm", url, function(data) {
        if(data["success"]=="true"){
            invokeSettime("#btn");
            jQuery("#nextBtn").removeAttr("disabled");
        }else{
            showAlertDivLayer(data["message"]);
        }
    });
}

function invokeSettime(obj){
    var countdown=60;
    settime(obj);
    function settime(obj) {
        if (countdown == 0) {
            jQuery(obj).attr("disabled",false);
            jQuery(obj).text("获取验证码");
            countdown = 60;
            return;
        } else {
            jQuery(obj).attr("disabled",true);
            jQuery(obj).text("剩余" + countdown + "秒");
            countdown--;
        }
        setTimeout(function() {
                settime(obj) }
            ,1000)
    }
}

function next(){
    if("email"==jQuery("#zhfsHd").val()){
        if(jQuery("#zhfszh").val() == ''){
            showAlert("请输入邮箱！");
            return false;
        }
    } else {
        if(jQuery("#zhfszh").val() == ''){
            showAlert("请输入联系电话！");
            return false;
        }
    }
    if(jQuery("#captcha").val() == ''){
        showAlert("请输入验证码！");
        return false;
	}
    var url = "mmzhgl_mmzh.do?method=yzxx&type=checkYzm";
    ajaxSubFormWithFun("MmZhForm", url, function(data) {
        if(data["success"]=="true"){
            window.location.href="mmzhgl_mmzh.do?method=xgmm&yhm="+jQuery("#yhm").val();
        }else{
            showAlertDivLayer(data["message"]);
        }
    });
}
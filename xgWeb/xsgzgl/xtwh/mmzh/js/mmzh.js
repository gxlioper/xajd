function checkuser(){
	            if(jQuery("#yhm").val() == ''){
	            	showAlert("�û�������Ϊ�գ�");
	            	return false;
	            }
				var url = "mmzhgl_mmzh.do?method=checkYh&type=check";
				ajaxSubFormWithFun("MmZhForm", url, function(data) {
					 if(data["message"]=="�û�����֤�ɹ���"){
			    			 window.location = "mmzhgl_mmzh.do?method=zhfs&yhm="+jQuery("#yhm").val();
			    	 }else{
			    		// jQuery("td[name='tdyz']").html("");
			    		 showAlert(data["message"]);
			    		}
					});
}

function checkwtda(){
    if(jQuery("#wtda").val() == ''){
    	showAlert("�ܱ��𰸲���Ϊ�գ�");
    	return false;
    }
    if (jQuery("#yzm_t").val() == "" || jQuery("#yzm_t").val().length != 4) {
		showAlert("��֤�벻�Ϸ���");
		jQuery("#yzm_t").focus();
		return false;
	} 
	var url = "mmzhgl_mmzh.do?method=checkMmbh&type=check";
	ajaxSubFormWithFun("MmZhForm", url, function(data) {
		 if(data["message"]=="�ܱ�������֤�ɹ���"){
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
		 if(data["message"]=="������³ɹ��������µ�¼��"){
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
        showAlertDivLayer("�ܱ����ⲻ��Ϊ�գ�");
        return false;
    }
    if(jQuery("#wtda").val() == ''){
    	showAlertDivLayer("�ܱ��𰸲���Ϊ�գ�");
    	return false;
    }
	var url = "mmzhgl_mmzh.do?method=MbSz&type=sz";
	ajaxSubFormWithFun("MmZhForm", url, function(data) {
		 if(data["message"]=="�ܱ��������óɹ���"){
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
        if(data["message"]=="����ɹ���"){
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
        jQuery.alert("��ѡ���һط�ʽ��")
    } else{
        var url = "mmzhgl_mmzh.do?method=zhfs&type=check&zhfs="+a[0].value;
        ajaxSubFormWithFun("MmZhForm", url, function(data) {
            if(data["success"]=="true"){
                window.location.href="mmzhgl_mmzh.do?method=yzxx&yhm="+jQuery("#yhm").val() + "&zhfs="+a[0].value;
            }else{
                var tip = jQuery("#verifTip1");
                if("email" == a[0].value){
                    tip.html("�˺�δԤ����������,��������������һط�ʽ!");
                } else {
                    tip.html("�˺�δԤ���ֻ�����,��������������һط�ʽ!");
                }
                tip.attr('style',"display:block");
            }
        });
    }
}

function checkyzxx(){
    if("email"==jQuery("#zhfsHd").val()){
        if(jQuery("#zhfszh").val() == ''){
            showAlert("���������䣡");
            return false;
        }
        if(!isEmail(jQuery("#zhfszh").val())){
            showAlert("�����ʽ����ȷ��");
            return false;
        }
    } else {
        if(jQuery("#zhfszh").val() == ''){
            showAlert("��������ϵ�绰��");
            return false;
        }
        if(!isTelephone("zhfszh")){
            showAlert("�ֻ��Ÿ�ʽ����ȷ��");
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
            jQuery(obj).text("��ȡ��֤��");
            countdown = 60;
            return;
        } else {
            jQuery(obj).attr("disabled",true);
            jQuery(obj).text("ʣ��" + countdown + "��");
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
            showAlert("���������䣡");
            return false;
        }
    } else {
        if(jQuery("#zhfszh").val() == ''){
            showAlert("��������ϵ�绰��");
            return false;
        }
    }
    if(jQuery("#captcha").val() == ''){
        showAlert("��������֤�룡");
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
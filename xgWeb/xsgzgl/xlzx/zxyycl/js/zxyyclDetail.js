

function saveyyzxInfo(){
		if(!jQuery("tr[name=yyfkId]").is(":hidden")  && jQuery("#zxrq").val()==''){
			return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		}
		
		if(!jQuery("tr[name=yysbyytr]").is(":hidden") && jQuery("#yysbyy").val()==''){
			return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		}
		//非预约成功的、所选择的预约不是预约成功的，已接待人数不能大于可接待人数。
		if(jQuery("#status").val()!="2" && jQuery("#yystatus").val()=="2" && parseInt(jQuery("#yjdrs").val())>=parseInt(jQuery("#kjdrs").val())){
			return showAlert("已达到日预约上限！");
		}
		var zxurl='';
		var yyurl='';
		var zxParameter ={};
		var yyParameter ={};
		//预约反馈
		if(jQuery("#doType").val()=='yyfk'){
			yyurl = "xlzx_yysq.do?method=updateYysqInfo";
			zxurl = "xlzx_zxyycl.do?method=saveXlzxInfo";
			var zxUpurl = "xlzx_zxyycl.do?method=updateXlzxInfo";

			zxParameter ={
		    	yyid:jQuery("#yyid").val(),
				zxrq:jQuery("#zxrq").val(),
				qssj:jQuery("#zxqssj").val(),
				jssj:jQuery("#zxjssj").val(),
				zxtell:jQuery("#zxtell").val(),
				zxdz:jQuery("#zxdz").val(),
				zxstatus: 1,//新增咨询信息，将状态置为1待咨询
				bz:jQuery("#bz").val(),
				//xspjzt:1//选择咨询信息，学生评价状态1待评价
				id:jQuery("#zxid").val()//諮詢ID
			};
			
			showConfirm("确认保存信息？",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
				//预约成功
				if(jQuery("#yystatus").val()=="2"){
					yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val()};
					//预约状态设置
					jQuery.post(yyurl,yyParameter,function(result){
						if(result=="true"){
							if(jQuery("#zxid").val()!=""){

								//咨询信息更新至咨询表
								jQuery.post(zxUpurl,zxParameter,function(data){
									if(data == true){
												showAlert("保存成功！",{},{"clkFun":function(){
													frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
													iFClose();
												}
											});
									}else{
										return showAlert("保存失败！");
									}
								},'json');
							}else{
								//咨询信息插入至咨询表
								jQuery.post(zxurl,zxParameter,function(data){
									if(data == true){
												showAlert("保存成功！",{},{"clkFun":function(){
													frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
													iFClose();
												}
											});
									}else{
										return showAlert("保存失败！");
									}
								},'json');
							}
						}else{
							return showAlert("保存失败！");						
						}
					});

				//预约失败
				}else if(jQuery("#yystatus").val()=="5"){
					//预约失败，则删除咨询信息
					jQuery.post("xlzx_zxyycl.do?method=delZxInfoByYyid",{yyid:jQuery("#yyid").val()},function(result){
										});
					yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val(),yysbyy:jQuery("#yysbyy").val()};
					//预约状态设置
					jQuery.post(yyurl,yyParameter,function(data){
						if(data == "true"){
							showAlert("保存成功！",{},{"clkFun":function(){
								frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
								iFClose();
							}});
						}else{
							return showAlert("保存失败！");						
						}
					});
				}
				jQuery.ajaxSetup({async:true});
			}});
		}

		//咨询反馈
		if(jQuery("#doType").val()=='zxfk'){
			
			zxParameter={};
			meetFlag();
			if(jQuery("#zxzt").val()==""||jQuery("#zxzt").val()=="1"){
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}
			//咨询状态取得判断
			if(jQuery("#xspjzt").val()=="2"){
				return showAlert("该咨询学生已经评价、无法修改咨询反馈！");
			}

			zxParameter["zxstatus"]=jQuery("#zxzt").val();//1待咨询2已咨询3未咨询
			if(jQuery("#zxzt").val()=="2"){
				zxParameter["xspjzt"]=1;
			}
			
			zxParameter["yyid"]=jQuery("#yyid").val();
			zxParameter["zxsfk"]=jQuery("#zxsfk").val();
		
			zxurl = "xlzx_zxyycl.do?method=updateXlzxInfo";
			
			showConfirm("确认保存信息？",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
					//咨询信息插入至咨询表	
					jQuery.post(zxurl,zxParameter,function(data){
						if(data == true){
									showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
										iFClose();
									}
								});
						}else{
							return showAlert("保存失败！");
						}
					},'json');
					jQuery.ajaxSetup({async:true});
			}});
		}
}

function showNewJdrs(obj){
	if(delValidate()){
		var url = "xlzx_yysq.do?method=yyzxDetail&doType=yyfk&id="+jQuery("#yyid").val()+"&zxrq="+jQuery("#zxrq").val();
		refreshForm(url);
	}
	
}

function delValidate(){
	var flag = false;
	var xh = jQuery("#xh").val();
	var zgh = jQuery("#zgh").val();
	var date = jQuery("#zxrq").val();
	var pbdate = jQuery("#pbdate").val();
	jQuery.ajaxSetup({async:false});
	jQuery.post("xlzx_zxspb.do?method=getSfkyFlag&xh="+xh,{pbdate:date,zgh:zgh},function(data){
		if(data["message"]==""){
			flag = true;
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				jQuery("#zxrq").val(pbdate);
				flag = false;
			}});
		}
	},'json');
	jQuery.ajaxSetup({async:true});
	return flag;
}

function getYjdrs(){
	var count=0;
	var zgh = jQuery("#zgh").val();
    var date =jQuery("#pbdate").val();
	var url = "xlzx_yysq.do?method=getZxsYjdrsByDate&zgh="+zgh+"&date="+date;
	jQuery.ajaxSetup({async:false});
	jQuery.post(url,{},function(data){
		count = data;
	});
	jQuery.ajaxSetup({async:true});
	return count;
}


function addYyInfo(zgh){
	var pbdate = jQuery("#pbdate").val();
	showDialog("预约信息",640,260,"xlzx_yysq.do?method=addyyzxInfo&yyzxrq="+pbdate+"&zgh="+zgh);
}

function disabledView(){
	
	if(jQuery("#doType").val()=="view"){
		jQuery("input,select,text,textarea").each(function(){
			jQuery(this).attr("readOnly","true");
		});
	}
}


function init(){
	sfAgree();
	
	//jQuery("input[type='radio'][name='meet'][value='"+jQuery("#status").val()+"']").attr("checked",true);
	if(jQuery("#status").val()=="2"){
		//jQuery("input[type='radio'][name='meet']").attr("disabled",true);
	}
	meetFlag();
	if(jQuery("#doType").val()=="view"){

		jQuery("#btx").hide();
		jQuery("#buttonSave").hide();
	}
}

function sfAgree(){
	if(jQuery("#status").val()=="2"){
		//jQuery("tr[name=yyfkId]").show();
		jQuery("tr[name=yysbyytr]").hide();		
	}
	else if(jQuery("#status").val()=="5"){
	    jQuery("tr[name=yyfkId]").hide();
	    //jQuery("tr[name=yysbyytr]").show();
	    jQuery("thead[name=zxxgInfo]").hide();	
		jQuery("tbody[name=zxxgInfo]").hide();
	}else{
	 	jQuery("tr[name=yyfkId]").hide();
		jQuery("tr[name=yysbyytr]").hide();		
		jQuery("thead[name=zxxgInfo]").hide();	
		jQuery("tbody[name=zxxgInfo]").hide();
	}
	
	if(jQuery("input[type='radio'][name='sfty'][value='1']").prop("checked")==true){
		jQuery("tr[name=yyfkId]").show();
		jQuery("tr[name=yysbyytr]").hide();
		jQuery("#yystatus").val("2");
	}else if(jQuery("input[type='radio'][name='sfty'][value='2']").prop("checked")==true){
		jQuery("tr[name=yyfkId]").hide();
		jQuery("tr[name=yysbyytr]").show();
		jQuery("#yystatus").val("5");
	}
}

function meetFlag(){
	if(jQuery("input[type='radio'][name='zxzt'][value='1']").prop("checked")==true){
		jQuery("#zxzt").val("1");
	}else if(jQuery("input[type='radio'][name='zxzt'][value='2']").prop("checked")==true){
		jQuery("#zxzt").val("2");
	}else if(jQuery("input[type='radio'][name='zxzt'][value='3']").prop("checked")==true){
		jQuery("#zxzt").val("3");
	}
}
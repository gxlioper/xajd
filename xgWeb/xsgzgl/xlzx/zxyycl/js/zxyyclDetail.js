

function saveyyzxInfo(){
		if(!jQuery("tr[name=yyfkId]").is(":hidden")  && jQuery("#zxrq").val()==''){
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		}
		
		if(!jQuery("tr[name=yysbyytr]").is(":hidden") && jQuery("#yysbyy").val()==''){
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		}
		//��ԤԼ�ɹ��ġ���ѡ���ԤԼ����ԤԼ�ɹ��ģ��ѽӴ��������ܴ��ڿɽӴ�������
		if(jQuery("#status").val()!="2" && jQuery("#yystatus").val()=="2" && parseInt(jQuery("#yjdrs").val())>=parseInt(jQuery("#kjdrs").val())){
			return showAlert("�Ѵﵽ��ԤԼ���ޣ�");
		}
		var zxurl='';
		var yyurl='';
		var zxParameter ={};
		var yyParameter ={};
		//ԤԼ����
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
				zxstatus: 1,//������ѯ��Ϣ����״̬��Ϊ1����ѯ
				bz:jQuery("#bz").val(),
				//xspjzt:1//ѡ����ѯ��Ϣ��ѧ������״̬1������
				id:jQuery("#zxid").val()//�JԃID
			};
			
			showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
				//ԤԼ�ɹ�
				if(jQuery("#yystatus").val()=="2"){
					yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val()};
					//ԤԼ״̬����
					jQuery.post(yyurl,yyParameter,function(result){
						if(result=="true"){
							if(jQuery("#zxid").val()!=""){

								//��ѯ��Ϣ��������ѯ��
								jQuery.post(zxUpurl,zxParameter,function(data){
									if(data == true){
												showAlert("����ɹ���",{},{"clkFun":function(){
													frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
													iFClose();
												}
											});
									}else{
										return showAlert("����ʧ�ܣ�");
									}
								},'json');
							}else{
								//��ѯ��Ϣ��������ѯ��
								jQuery.post(zxurl,zxParameter,function(data){
									if(data == true){
												showAlert("����ɹ���",{},{"clkFun":function(){
													frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
													iFClose();
												}
											});
									}else{
										return showAlert("����ʧ�ܣ�");
									}
								},'json');
							}
						}else{
							return showAlert("����ʧ�ܣ�");						
						}
					});

				//ԤԼʧ��
				}else if(jQuery("#yystatus").val()=="5"){
					//ԤԼʧ�ܣ���ɾ����ѯ��Ϣ
					jQuery.post("xlzx_zxyycl.do?method=delZxInfoByYyid",{yyid:jQuery("#yyid").val()},function(result){
										});
					yyParameter={id:jQuery("#yyid").val(),status:jQuery("#yystatus").val(),yysbyy:jQuery("#yysbyy").val()};
					//ԤԼ״̬����
					jQuery.post(yyurl,yyParameter,function(data){
						if(data == "true"){
							showAlert("����ɹ���",{},{"clkFun":function(){
								frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
								iFClose();
							}});
						}else{
							return showAlert("����ʧ�ܣ�");						
						}
					});
				}
				jQuery.ajaxSetup({async:true});
			}});
		}

		//��ѯ����
		if(jQuery("#doType").val()=='zxfk'){
			
			zxParameter={};
			meetFlag();
			if(jQuery("#zxzt").val()==""||jQuery("#zxzt").val()=="1"){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
			//��ѯ״̬ȡ���ж�
			if(jQuery("#xspjzt").val()=="2"){
				return showAlert("����ѯѧ���Ѿ����ۡ��޷��޸���ѯ������");
			}

			zxParameter["zxstatus"]=jQuery("#zxzt").val();//1����ѯ2����ѯ3δ��ѯ
			if(jQuery("#zxzt").val()=="2"){
				zxParameter["xspjzt"]=1;
			}
			
			zxParameter["yyid"]=jQuery("#yyid").val();
			zxParameter["zxsfk"]=jQuery("#zxsfk").val();
		
			zxurl = "xlzx_zxyycl.do?method=updateXlzxInfo";
			
			showConfirm("ȷ�ϱ�����Ϣ��",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
					//��ѯ��Ϣ��������ѯ��	
					jQuery.post(zxurl,zxParameter,function(data){
						if(data == true){
									showAlert("����ɹ���",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_zxyycl_zxyycl.do");
										iFClose();
									}
								});
						}else{
							return showAlert("����ʧ�ܣ�");
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
	showDialog("ԤԼ��Ϣ",640,260,"xlzx_yysq.do?method=addyyzxInfo&yyzxrq="+pbdate+"&zgh="+zgh);
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
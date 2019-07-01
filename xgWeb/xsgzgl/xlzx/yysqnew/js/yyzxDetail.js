function saveyyzxInfo(){
				if(jQuery("#xstell").val()==''){
					showAlert("手机号码不能为空！");
					return false;
				}
				if(jQuery("#yyzxzt").val()==''){
					showAlert("咨询主题不能为空！");
					return false;
				}
				
				// 得到JSON对象
			    var parameter ={
					id:$("yyid").value,
					xstell:jQuery("#sjhm").val(),
					qssj:jQuery("#qssj").val(),
					jssj:jQuery("#jssj").val(),
					yyzxzt:jQuery("#yyzxzt").val(),
					yyzxxq:jQuery("#yyzxxq").val()
				};
				var url = "xlzx_yysqnew.do?method=updateYysqInfo";
				
				showConfirm("确认保存预约信息？",{"okFun":function(){
						jQuery.ajaxSetup({async:false});
							jQuery.post(url,parameter,function(data){
								if(data == true){
									showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
										iFClose();
								}});
								}else{
									showAlert("保存失败！",{},{"clkFun":function(){
									}});
								}
							},'json');
						jQuery.ajaxSetup({async:true});
						}});
		}
	
		function addYyInfo(zgh){
			var yyzxrq = $("yyzxrq").value;
			showDialog("预约信息",640,260,"xlzx_yysqnew.do?method=addyyzxInfo&yyzxrq="+yyzxrq+"&zgh="+zgh);
		}
		
		function disabledView(){
			if($("doType").value=="view"){
				jQuery("input,select,text,textarea").each(function(){
					jQuery(this).attr("readOnly","true");
				});
			}
		}
		
		function init(){
			if($("status").value=="1" || $("status").value=="3" || $("status").value=="4"){
				jQuery("tr[name=yyqkInfo]").hide();
				jQuery("tr[name=yysbyytr]").hide();
				jQuery("thead[name=zxxgInfo]").hide();
				jQuery("tbody[name=zxxgInfo]").hide();
			}else if($("status").value=="5"){
				//jQuery("tr[name=yysbyytr]").show();
				jQuery("tr[name=yyqkInfo]").hide();
				jQuery("thead[name=zxxgInfo]").hide();
				jQuery("tbody[name=zxxgInfo]").hide();
			}else{
				jQuery("tr[name=yysbyytr]").hide();
			}
			if($("doType").value=="view"){
				jQuery("#buttonSave").hide();
				jQuery("#btx").hide();
			}
		}
function saveZxInfo(){
			var parameter={yyid:$("yyid").value,xspj:jQuery("#xspj").val(),xspjzt:"2"};
				var url = "xlzx_zxyyclnew.do?method=updateXlzxInfo";
				jQuery.ajaxSetup({async:false});
					jQuery.post(url,parameter,function(data){
						if(data == true){
							showAlert("保存成功！",{},{"clkFun":function(){
								frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
								iFClose();
						}});
						}else{
							showAlert("保存预约信息失败！",{},{"clkFun":function(){
							}});
						}
					},'json');
				jQuery.ajaxSetup({async:true});
			}

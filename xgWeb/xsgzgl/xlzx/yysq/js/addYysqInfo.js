function saveYysqInfo(){
			if(jQuery("#xstell").val()=='' || jQuery("#yyzxzt").val()==''){
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}
			// 得到JSON对象
		    var parameter ={
				xh:jQuery("#xh").val(),
				zgh:jQuery("#zgh").val(),
				status:1,//新增预约申请信息，将状态置为1预约中.
				xstell:jQuery("#xstell").val(),
				yyzxrq:jQuery("#yyzxrq").val(),
				qssj:jQuery("#qssj").val(),
				jssj:jQuery("#jssj").val(),
				yyzxzt:jQuery("#yyzxzt").val(),
				yyzxxq:jQuery("#yyzxxq").val()
			};
			var url = "xlzx_yysq.do?method=saveYysqInfo";
			showConfirm("确认保存预约信息？",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,function(data){
					if(data == true){
						showAlert("保存成功！",{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
							iFClose();
					}});
					}else{
						showAlert("保存预约信息失败！",{},{"clkFun":function(){
						}});
					}
				},'json');
				jQuery.ajaxSetup({async:true});
			}});
		}
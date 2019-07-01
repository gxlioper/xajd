
		//保存预约申请表、咨询信息表
		function saveYysqInfo(){
			
				if(jQuery("#xh").val()=="" || jQuery("#zxrq").val()==""){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
				var zxurl = "xlzx_zxyycl.do?method=saveYyzxInfo";
							
				var	 zxParameter ={
						zxrq:jQuery("#zxrq").val(),
						qssj:jQuery("#zxqssj").val(),
						jssj:jQuery("#zxjssj").val(),
						xh:jQuery("#xh").val(),
						zgh:jQuery("#zgh").val(),
						xstell:jQuery("#sjhm").val(),
						zxstatus: 1,//咨询状态   1待咨询2已咨询
						zxtell:jQuery("#zxtell").val(),
						zxdz:jQuery("#zxdz").val(),
						bz:jQuery("#bz").val()
					};	
					jQuery.ajaxSetup({async:false});
						jQuery.post(zxurl,zxParameter,function(data){
							if(data == true){
								showAlert("保存成功！",{},{"clkFun":function(){
									frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
									window.close();
								}});
							}else{
								showAlert("保存失败！");
							}
						},'json');
					jQuery.ajaxSetup({async:true});
		}
					
		function selectXh(){
			var gotoPath = jQuery("#path").val();
//			if(jQuery("#zgh").val()!=""){
//				gotoPath +="$zgh="+jQuery("#zgh").val();
//			}
			showDialog("请选择一个学生",800,600,"xlzx_tsxs.do?method=getTsxsInfo&gotoPath="+gotoPath);
		}
		
		function selectZxs(){
			var gotoPath = jQuery("#path").val();
			
			if(jQuery("#xh").val()!=""){
				gotoPath +="$xh="+jQuery("#xh").val();
			}
			showDialog("请选择一个咨询师",800,600,"xlzx_zxs.do?method=getZxsInfo&gotoPath="+gotoPath);
		}
		
		
		function delValidate(){
			var flag = false;
			var xh = jQuery("#xh").val();
			var zgh = jQuery("#zgh").val();
			var date = jQuery("#zxrq").val();
			if(zgh!="" && date!=""){
				jQuery.ajaxSetup({async:false});
				jQuery.post("xlzx_zxspb.do?method=getSfkyFlag&xh="+xh,{pbdate:date,zgh:zgh},function(data){
					if(data["message"]==""){
						flag = true;
					}else{
						showAlert(data["message"],{},{"clkFun":function(){
							jQuery("#zxrq").val("");
							flag = false;
						}});
					}
				},'json');
				
				jQuery.ajaxSetup({async:true});
			}
		}
		
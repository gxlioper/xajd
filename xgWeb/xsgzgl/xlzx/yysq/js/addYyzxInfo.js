
		//����ԤԼ�������ѯ��Ϣ��
		function saveYysqInfo(){
			
				if(jQuery("#xh").val()=="" || jQuery("#zxrq").val()==""){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}
				var zxurl = "xlzx_zxyycl.do?method=saveYyzxInfo";
							
				var	 zxParameter ={
						zxrq:jQuery("#zxrq").val(),
						qssj:jQuery("#zxqssj").val(),
						jssj:jQuery("#zxjssj").val(),
						xh:jQuery("#xh").val(),
						zgh:jQuery("#zgh").val(),
						xstell:jQuery("#sjhm").val(),
						zxstatus: 1,//��ѯ״̬   1����ѯ2����ѯ
						zxtell:jQuery("#zxtell").val(),
						zxdz:jQuery("#zxdz").val(),
						bz:jQuery("#bz").val()
					};	
					jQuery.ajaxSetup({async:false});
						jQuery.post(zxurl,zxParameter,function(data){
							if(data == true){
								showAlert("����ɹ���",{},{"clkFun":function(){
									frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
									window.close();
								}});
							}else{
								showAlert("����ʧ�ܣ�");
							}
						},'json');
					jQuery.ajaxSetup({async:true});
		}
					
		function selectXh(){
			var gotoPath = jQuery("#path").val();
//			if(jQuery("#zgh").val()!=""){
//				gotoPath +="$zgh="+jQuery("#zgh").val();
//			}
			showDialog("��ѡ��һ��ѧ��",800,600,"xlzx_tsxs.do?method=getTsxsInfo&gotoPath="+gotoPath);
		}
		
		function selectZxs(){
			var gotoPath = jQuery("#path").val();
			
			if(jQuery("#xh").val()!=""){
				gotoPath +="$xh="+jQuery("#xh").val();
			}
			showDialog("��ѡ��һ����ѯʦ",800,600,"xlzx_zxs.do?method=getZxsInfo&gotoPath="+gotoPath);
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
		
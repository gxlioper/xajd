function saveYysqInfo(){
			if(jQuery("#xstell").val()=='' || jQuery("#yyzxzt").val()==''){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
			// �õ�JSON����
		    var parameter ={
				xh:jQuery("#xh").val(),
				zgh:jQuery("#zgh").val(),
				status:1,//����ԤԼ������Ϣ����״̬��Ϊ1ԤԼ��.
				xstell:jQuery("#xstell").val(),
				yyzxrq:jQuery("#yyzxrq").val(),
				qssj:jQuery("#qssj").val(),
				jssj:jQuery("#jssj").val(),
				yyzxzt:jQuery("#yyzxzt").val(),
				yyzxxq:jQuery("#yyzxxq").val()
			};
			var url = "xlzx_yysqnew.do?method=saveYysqInfo";
			showConfirm("ȷ�ϱ���ԤԼ��Ϣ��",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,function(data){
					if(data == true){
						showAlert("����ɹ���",{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
							iFClose();
					}});
					}else{
						showAlert("����ԤԼ��Ϣʧ�ܣ�",{},{"clkFun":function(){
						}});
					}
				},'json');
				jQuery.ajaxSetup({async:true});
			}});
		}
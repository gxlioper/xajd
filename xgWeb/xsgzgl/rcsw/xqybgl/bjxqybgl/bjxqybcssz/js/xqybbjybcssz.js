		jQuery(document).ready(function(){ 
			changeSqkg();
		});

		//���¸�λ����
		function changeSqkg(){
			var sqkg = jQuery("[name=sqkg]:checked").val();
			if("1"==sqkg){
				jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled",false);
				
			}else if("0"==sqkg){
				jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled","disabled");
				
			}
		}

		function saveCssz(){
			var sqkglength = jQuery("[name=sqkg]:checked").length;
			
			if(sqkglength==0){
				showAlertDivLayer("���������뿪��!");
				return false;
			}
			
			var splc = jQuery("#splc").val();
			var sqkg = jQuery("[name=sqkg]:checked").val();
			
			if ("1"==sqkg && (splc == "" || splc == null)){
				showAlertDivLayer("��ѡ���������!");
				return false;
			}
			
//			ȡ�����õ��е�ʱ�������
//			if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
//				showAlertDivLayer("����ʱ��ͽ���ʱ�������д!");		
//				return false;
//			}

			
			var url = "rcsw_xqybgl_bjxqybgl_bjxqybcsszgl.do?method=saveBjxqybcssz";
			ajaxSubFormWithFun("bjxqybcsszForm",url,function(data){
				showAlertDivLayer(data["message"]);
			});
		}
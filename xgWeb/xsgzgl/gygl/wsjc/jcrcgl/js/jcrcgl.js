//��ѯ�����
function searchRs(){

	var url = "gyglnew_jcrcgl_ajax.do?method=jcrcglCx";

	var ie = "10.0";

	var otherValue = [ie];

	searchRsByAjax(url,otherValue);
	
	setTimeout("setDivHeight()","1000")
}

function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

function save(tag){
	if(tag=="ok"){

		//����
		var pkValue=new Array();

		jQuery.ajaxSetup({async:false});
		
		// �õ�JSON����
        var parameter ={};;
	
		jQuery("[name=div_pkValue]:checked").each(function(i){
					
			pkValue[i] =escape(jQuery(this).val());
					
		});
		
		var xh=new Array();
		jQuery("[name=div_pkValue]:checked").each(function(i){
					
			xh[i] =escape(jQuery(this).val());
					
		});
		
		var url = "xljk_hzny_ajax.do?method=zxsglModi";
      	 
	 	parameter["str_byjd"]=escape(byjdV);
		parameter["array_pkValue"]=pkValue.join("!!array!!");
		parameter["array_xh"]=xh.join("!!array!!");

	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result,function(tag){
				
					if(tag=="ok"){
				
						closeWindown();	
						searchRs();
						
					}
				});
				
				
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
	

}

function showModi(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	
	if(len==1){	

		var nullflg = false;
		jQuery("[name=div_pkValue]:checked").each(function(i){
			 var sfwh = jQuery(this).attr("sfwh");
			 if(sfwh!=""){
				 nullflg = true;
				}
		});
		if(nullflg){
			alertInfo("��ѡ���ճ̵������м�¼¼�룬�������޸ģ�");
			return false;
		}else{
			var pkValue=jQuery("[name=div_pkValue]:checked").val();
			
			var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
			
			var url="gyglnew_jcrcgl.do?method=jcrcglXg";
			
			url+="&pkValue="+pkValue;
			
			//showTopWin(url,600,450);
			showDialog('�޸ļ���ճ�', 600, 380, url);
		}
	}else{
		
		alertInfo("�빴ѡһ����Ҫ�޸ĵļ�¼��");
		
		return false;
	}
}

function showView(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
		
		var url="gyglnew_jcrcgl.do?method=jcrcglCk";
		
		url+="&pkValue="+pkValue;
		showDialog('�鿴����ճ�', 600, 380, url);
		//showTopWin(url,600,450);
	}else{
		
		alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
		
		return false;
	}
}

function deleteJcrcgl(){
	
	var n=jQuery("[name=div_pkValue]:checked").length;
	
	var blog=true;
	if(n>0){
		var nullflg = false;
		jQuery("[name=div_pkValue]:checked").each(function(i){
			 var sfwh = jQuery(this).attr("sfwh");
			 if(sfwh!=""){
				 nullflg = true;
				}
		});
		if(nullflg){
			blog = false;
			alertInfo("��ѡ���ճ̵������м�¼¼�룬������ɾ����");
			return false;
		}
		if(blog){
			confirmInfo("�ò�������ɾ����������ճ���Ϣ���Ƿ�ȷ������������",function(tag){

				if(tag=="ok"){
					
					var pkValue=new Array();
					
					var xh=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						
						pkValue[i]=jQuery(this).val();
					
					});
					
					var parameter={}

					parameter["pkValue"]=escape(pkValue.join("!!array!!"));
					
					var url= "gyglnew_jcrcgl_ajax.do?method=delete";
					
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result,function(tag){
								
								if(tag=="ok"){
									searchRs();
								}
							
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
		}
	}else{
		
		alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

//�ύ
function tijiao(){
	var n=jQuery("[name=div_pkValue]:checked").length;
	if(n>0){
		confirmInfo("�ò�������������������ճ̣����������¼�룬�Ƿ�ȷ������������",function(tag){
			
			if(tag=="ok"){
				
				var pkValue=new Array();
				
				var xh=new Array();
				
				jQuery("[name=div_pkValue]:checked").each(function(i){
					
					pkValue[i]=jQuery(this).val();
				
				});
				
				var parameter={}

				parameter["pkValue"]=escape(pkValue.join("!!array!!"));
				
				var url= "gyglnew_jcrcgl.do?method=tjJcrcgl";
				
				jQuery.ajaxSetup({async:false});	
				
				jQuery.post(url,
					parameter,
					function(result){
					
						alertInfo(result,function(tag){
							
							if(tag=="ok"){
								searchRs();
							}
						
						});
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		
		alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

//ȡ���ύ
function qxtj(){
	var n=jQuery("[name=div_pkValue]:checked").length;
	if(n>0){
		confirmInfo("�ò�������ȡ����������ճ��������������¼�룬�Ƿ�ȷ������������",function(tag){
			
			if(tag=="ok"){
				
				var pkValue=new Array();
				
				var xh=new Array();
				
				jQuery("[name=div_pkValue]:checked").each(function(i){
					
					pkValue[i]=jQuery(this).val();
				
				});
				
				var parameter={}

				parameter["pkValue"]=escape(pkValue.join("!!array!!"));
				
				var url= "gyglnew_jcrcgl.do?method=qxtjJcrcgl";
				
				jQuery.ajaxSetup({async:false});	
				
				jQuery.post(url,
					parameter,
					function(result){
					
						alertInfo(result,function(tag){
							
							if(tag=="ok"){
								searchRs();
							}
						
						});
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		
		alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

jQuery(function(){
	onShow();
})

function jcrcglExportConfig() {
//DCCLBH�������ܱ��,ִ�е������� 
customExport("gyglnew_wsjc_jcrcgl.do", jcrcglExportData);
}
	

	
// ��������
function jcrcglExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gyglnew_jcrcgl_ajax.do?method=jcrcglExportData&dcclbh=" + "gyglnew_wsjc_jcrcgl.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
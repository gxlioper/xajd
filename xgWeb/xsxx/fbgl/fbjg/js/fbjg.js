 
 function searchRs() {
	var map = getSuperSearch();
	var fbzt = jQuery("#tjzt").val();
	if (fbzt != "") {
		map["tjzt"] = fbzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//�л�
 function selectTab(obj, bbzt) {
 	if (bbzt == "wtj") {
 		var map = getSuperSearch();
 		map["tjzt"] = "wtj";
 		gridSetting["params"] = map;
 		jQuery("#dataTable").initGrid(gridSetting);
 		jQuery("#cx").hide();
 		jQuery("#tj").show();
 	} else {
 		var map = getSuperSearch();
 		map["tjzt"] = "ytj";
 		gridSetting2["params"] = map;
 		jQuery("#dataTable").initGrid(gridSetting2);
 		jQuery("#cx").show();
 		jQuery("#tj").hide();
 	}
 	jQuery(".ha").removeClass("ha");
 	jQuery(obj).parent().addClass("ha");
 	// searchRs();
 }
 function tj(){
		showDialog("�ύ", 500,300, "fbglfbjg.do?method=tjzsk");
 }
 function cx(){
		showDialog("����", 500,300, "fbglfbjg.do?method=cxzsk");
}
 /**
  * �Զ�����ѧ�������Ϣ
  * @return
  */
 function autoSetXsxx(){
	 	var nj=jQuery("#nj").val();
	 	 if(!nj){
	 		 return ;
	 	 }
	 	jQuery.ajax({
			url:"fbglfbjg.do?method=getTjxx",
			data:{nj:nj},
			type:"post",
			dataType:"json",
			success:function(data){
				jQuery("#ytj").text(data.ytj);
				jQuery("#wtj").text(data.wtj);
			}
		});	
 }
 /**
  * ������ʽ��
  * @return
  */
 function cxzsk(){
	 var nj=jQuery("#nj").val();
	 if(!nj){
		return showAlert("��ѡ����Ҫ�������꼶��");
	 }
	 var barkey="cxzsk"+nj;
	 lock();
	 jQuery("#nj").attr("disabled","disabled");
	 jQuery("#nj").css({color:"#cccccc"});
 	jQuery.ajax({
		url:"fbglfbjg.do?method=cxzsk",
		data:{nj:nj,type:'save'},
		type:"post",
		dataType:"json",
		success:function(data){
		}
	 });	
	var nowValue=parseInt(jQuery("#ytj").text());
	var wtj=parseInt(jQuery("#wtj").text());
 	loadBar(barkey,function(data){
 		jQuery("#ytj").text(nowValue-data.now);
 		jQuery("#wtj").text(data.now+wtj);
 		if(data.finish){
			showAlert("������ɣ�", {}, {
				"clkFun" : function() {
					var api = frameElement.api;
					api.reload();
				}
			});
 		}
 		return true;
 	});
 }
 /**
  * �ύ��ʽ��
  * @return
  */
 function tjzsk(){
	 var nj=jQuery("#nj").val();
	 if(!nj){
		return showAlert("��ѡ����Ҫ�ύ���꼶��");
	 }
	 var barkey="tjzsk"+nj;
	 lock();
	 jQuery("#nj").attr("disabled","disabled");
	 jQuery("#nj").css({color:"#cccccc"});
	 	jQuery.ajax({
			url:"fbglfbjg.do?method=tjzsk",
			data:{nj:nj,type:'save'},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.message=="-1"){
					jQuery("#error").show();
					stopBar();
				}else if(data.message=="0"){
					jQuery("#error").hide();
					showAlert("��ѡ���ύ<font style='color:red'>["+nj+"]</font>�꼶��ѧ��δ����δ��ѧ�ţ���ȷ�ϣ�");
					stopBar();
				}else if(data.message=="2"){
					jQuery("#error").hide();
					showAlert("��ѡ���ύ<font style='color:red'>["+nj+"]</font>�꼶��ѧ�������ظ�ѧ�ţ���ȷ�ϣ�");
					stopBar();
				}else{
					jQuery("#error").hide();
				}
			}
		});	
	 	//���ؽ�����
	 	var nowValue=parseInt(jQuery("#ytj").text());
		var wtj=parseInt(jQuery("#wtj").text());
	 	loadBar(barkey,function(data){
	 		jQuery("#ytj").text(data.now+nowValue);
	 		jQuery("#wtj").text(wtj-data.now);
	 		if(data.finish){
				showAlert("�ύ�ɹ�!", {}, {
					"clkFun" : function() {
						var api = frameElement.api;
						api.reload();
					}
				});
	 		}
	 		return true;
	 	});
 }
function showTopWin(url, w, h,scrollbar) {
	var info = "Status:YES;dialogWidth:" + w + "px;dialogHeight:" + h + "px;help:no;";
	if (scrollbar != null){
		showModalDialog(url, window, info,scrollbar);
		return false;
	}
	showModalDialog(url, window, info);
}

//���˼������ά���޸�
function checkDwwhUpdate(){
    var row = jQuery("#dataTable").getSeletRow();
	// var num = check.length;
	if(row.length == 1){
		var zgh = row[0]["zgh"];
		//showTopWin("/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh="+zgh,830,600); .
		showDialog('', 830, 700, "szdw_fdyxx.do?method=fdyxxEdit&zgh="+zgh);
		//window.open ('/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh='+zgh);
	}else{
		alertError("�빴ѡ<font color='blue'>һ����¼</font>�����޸�");
		return false;
	}
}
//�������ά��
function saveDwwh(lx){
	var xxdm = jQuery("#xxdm").val();
	var zgh = jQuery("#text_zgh").val();
	var xm = jQuery("#text_xm").val();
	
	if(zgh == ""){
		showAlert("ְ���Ų���Ϊ�գ���ȷ��");
		return false;
	}
	var isExisting = checkInputIsExisting("fdyxxb","zgh",zgh,"");

	if(lx == "insert" && isExisting == "true"){
		alertError("��ְ�����Ѵ��ڣ�����ȷ��");
		return false;
	}
	
	if(xm == ""){
		alertError("��������Ϊ�գ���ȷ��");
		return false;
	}
	
	//·��
	var url = "szdw_dwwh.do?method=saveDwwh";
	//����
 	var parameter = {};
 	if("10698"==xxdm){
 		parameter = {
 				"str_zgh":escape(jQuery("#text_zgh").val()),
 				"str_xm":escape(jQuery("#text_xm").val()),
 				"str_xb":escape(jQuery("#select_xb").val()),
 				"str_bmdm":escape(jQuery("#select_bmdm").val()),
 				"str_sydm":escape(jQuery("#select_sydm").val()),
 				"str_lxdh":escape(jQuery("#text_lxdh").val())
 		};
 	}else{
 		parameter = {
 				"str_zgh":escape(jQuery("#text_zgh").val()),
 				"str_xm":escape(jQuery("#text_xm").val()),
 				"str_xb":escape(jQuery("#select_xb").val()),
 				"str_bmdm":escape(jQuery("#select_bmdm").val()),
 				"str_lxdh":escape(jQuery("#text_lxdh").val())
 		};
 	}
	
 	//jQuery.ajaxSetup({async:false});
 	
 	jQuery.post(url,
			parameter,
			function(result){
	 		 showAlert(result,{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}else{
						iFClose();
					}
				}});
			}
		);
		
	jQuery.ajaxSetup({async:true});
}

function updateDwwh(lx){
	var fields = jQuery("#dwwh_form").serializeArray();
	var zgh = jQuery("#zgh").val();
	var xm = jQuery("#xm").val();
	//jQuery.each(fields, function(i, field){
	//	alert(jQuery(field).attr("name")+"=="+jQuery(field).val());
    //});
	var jtzz = jQuery("#jtzz").val();
	
	var zyzz = jQuery("#zyzz").val();
	
	var grhjqk = jQuery("#grhjqk").val();
	
	var txdz = jQuery("#txdz").val();
	
	var gzjl = jQuery("#gzjl").val();
	
	var bz = jQuery("#bz").val();
	
	var pjpy = jQuery("#kzzd4").val();
	var fblw = jQuery("#fblw").val();
	var pxqk = jQuery("#pxqk").val();
	
	var sfzh = jQuery("#sfzh").val();
	var khyh = jQuery("#khyh").val();
	var yhzh = jQuery("#yhzh").val();
	var hyzk = jQuery("#hyzk").val();
	var fdyzbs = jQuery("#fdyzbs").val();
	var fdyrzrq = jQuery("#fdyrzrq").val();
	var zyjnzs = jQuery("#zyjnzs").val();
	var csrq = jQuery("#csrq").val();
	var age = jQuery("#age").val();
	

	var reg = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
	
	if (sfzh!="" && reg != "" && !reg.exec(sfzh)) {
		alertError("���֤�����벻��ȷ");
		return false;
	}
	if(zgh == ""){
		alertError("ְ���Ų���Ϊ�գ���ȷ��");
		return false;
	}
	if(xm == ""){
		alertError("��������Ϊ�գ���ȷ��");
		return false;
	}
	
	var isExisting = checkInputIsExisting("fdyxxb","zgh",zgh,"");
	
	if(lx == "insert" && isExisting == "true"){
		alertError("��ְ�����Ѵ��ڣ�����ȷ��");
		return false;
	}
	if(jtzz.length>150){
		alertError("��ͥסַֻ������150����");
		return false;
	}else if(zyzz.length>200){
		alertError("��Ҫְ��ֻ������200����");
		return false;
	}else if(grhjqk.length>2000){
		alertError("�����ֻ������2000����");
		return false;
	}else if(txdz.length>50){
		alertError("ͨѶ��ַֻ������50����");
		return false;
	}else if(gzjl.length>2000){
		alertError("��������ֻ������2000����");
		return false;
	}else if(pjpy.length>2000){
		alertError("��������ֻ������2000����");
		return false;
	}else if(fblw.length>2000){
		alertError("��������ֻ������2000����");
		return false;
	}else if(pxqk.length>300){
		alertError("�μ���ѵֻ������300����");
		return false;
	}else if(bz.length>2000){
		alertError("��עֻ������2000����");
		return false;
	}else if(khyh.length>40){
		alertError("��������ֻ������40����");
		return false;
	}else if(yhzh.length>40){
		alertError("�����˻�ֻ������40����");
		return false;
	}else if(fdyzbs.length>40){
		alertError("����Աֵ����ֻ������40����");
		return false;
	}else if(zyjnzs.length>40){
		alertError("ְҵ����ֻ������40����");
		return false;
	}
	
	//·��
	var url = "szdw_dwwh.do?method=updateDwwh";
	
	jQuery.ajax({
		  type: 'POST',
		  url: url,
		  dataType: "text",
		  contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		  data: fields,
		  success: function(datas){
		 showAlert(datas,{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		  }
	});

}

//��������ά��DIV
function createDwwhDiv(lx){

	var zgh = "";
	var row = jQuery("#dataTable").getSeletRow();

	if(lx == "update"){
        if(row.length != 1){
            alertError("��ѡ��һ�����ݣ�");
        }
		zgh = row[0]["zgh"];
	}
	
	//·��
	var url = "szdw_dwwh.do?method=createDwwhDiv";
	//����
 	var parameter = {
		"str_lx":lx,
		"str_zgh":zgh
	};
 	url+="&str_lx="+lx+"&str_zgh="+zgh;
 	showDialog('', 400, 270, url);
 /*	jQuery("#div_dwwh").load(
		url,
		parameter,
		function(){
			//tipsWindown(" ","id:div_dwwh","400","300","true","","true","id");
			showDialogDiv("","400","300","div_dwwh");
		}
	);*/
}


//ɾ������ά��
function deleteDwwh(){

	// var len=jQuery("[name=primarykey_checkVal]:checked").length;
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length!=0){
		var flag = true;	
		//jQuery("[name=primarykey_checkVal]:checked").each(function(){
		//	var shzt = jQuery(this).parents().children("td").eq(8).html();
		//	if(shzt != "δ���"){
		//		flag = false;
		//	}
		//});
		
		if(!flag){
			//alertError("ֻ��ɾ��<font color='blue'>δ���</font>�ļ�¼������ȷ��");
		}
		
		if(flag){
		
			confirmInfo("����ȷ��<font color='blue'>�Ƿ�ɾ��</font>����ѡ�Ľ�ʦ��¼��ע���h���Ľ�ʦ�˻��޷��ٵ�¼ϵͳ",function(tag){
				if(tag=="ok"){
					var url = "szdw_dwwh.do?method=deleteDwwh";
					// var pkValue=new Array();
					var i=0;
					
					// jQuery("input[name=primarykey_checkVal]:checked").each(function(){
					// 	pkValue[i]=jQuery(this).val();
					// 	i++;
					// });
			
				 	// $("divWaiting").style.display="";
					// $("divDisable").style.display="";
					
					// jQuery.ajaxSetup({async:false});
					
					jQuery.post(url,
						{"zghs":ids.toString()},
						function(result){
							// $("divWaiting").style.display="none";
							// $("divDisable").style.display="none";
							searchRs();
							alertInfo(result);
							// closeWindown();
						}
					);
					// jQuery.ajaxSetup({async:true});
				}
			});
		}
	}else{	
		alertError("��<font color='blue'>��ѡ</font>��ϣ��ɾ���ļ�¼��");	
		return false;
	}
}

//�����û���DIV
function createYhkDiv(){
	
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == 0){
		alertInfo("����<font color='blue'>��ѡ</font>ϣ����ӵ��û���Ľ�ʦ��¼");
		return false;
	}
	
	//·��
	var url = "szdw_dwwh.do?method=createYhkDiv";
	//����
 	var parameter = {
		
	};
	
 	jQuery("#div_yhk").load(
		url,
		parameter,
		function(){
			tipsWindown(" ","id:div_yhk","400","150","true","","true","id");
		}
	);
}

//���浽�û���
function saveYhk(){
	
	var len=jQuery("[name=primarykey_checkVal]:checked").length;

	if(len!=0){	
			
		var url = "szdw_dwwh.do?method=saveYhk";
		var pkValue=new Array();
		var i=0;
		
		jQuery("input[name=primarykey_checkVal]:checked").each(function(){
			pkValue[i]=jQuery(this).val();
			i++;
		});
		
		var parameter={};
		parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
		parameter["str_zdm"]=escape(jQuery("#select_zdm").val());
		
	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.ajaxSetup({async:false});
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				searchRs();
				alertInfo(result);
				closeWindown();		
			}
		);

		jQuery.ajaxSetup({async:true});
		
	}else{	
		alertError("��<font color='blue'>��ѡ</font>��ϣ����ӵ��û���ļ�¼��");	
		return false;
	}
}

//����Ժϵ����DIV
function createYxjrDiv(){
	
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 0){
		alertInfo("����<font color='blue'>��ѡ</font>ϣ������Ժϵ��������Ľ�ʦ��¼");
		return false;
	}
	
	var tr = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").parents("tr");
	var fdyDbs = jQuery("input[name=fdyDbs]",tr).val();
	var bzrDbs = jQuery("input[name=bzrDbs]",tr).val();
	if (Number(fdyDbs)== 0 && Number(bzrDbs) == 0){
		alertInfo("��������иò���");
		return false;
	}
	
	//·��
	var url = "szdw_dwwh.do?method=createYxjrDiv";
	
	var pkValue=new Array();
	var i=0;
	jQuery("input[name=primarykey_checkVal]:checked").each(function(){
		pkValue[i]=jQuery(this).val();
		i++;
	});
	url+="&array_pkValue="+escape(pkValue.join("!!array!!"));
	//var parameter={};
	//parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
	//parameter["str_sfjryx"]=jQuery("input[name=radio_yxjr]:checked").eq(0).val();
	
 	showDialog('ԺУ�����������', 450,180, url);
 	/*jQuery("#div_yxjr").load(
		url,
		parameter,
		function(){
			tipsWindown(" ","id:div_yxjr","450","200","true","","true","id");
		}
	);*/
}

//����ԺУ����
function saveYxjr(){
			
	var url = "szdw_dwwh.do?method=saveYxjr";
	//var pkValue=new Array();
	//var i=0;
//	jQuery("input[name=primarykey_checkVal]:checked").each(function(){alert(111);
	//	pkValue[i]=jQuery(this).val();
//		i++;
	//});
	var parameter={};
	parameter["array_pkValue"]=jQuery("#selectId").val();
	parameter["str_sfjryx"]=jQuery("input[name=radio_yxjr]:checked").eq(0).val();
	jQuery.post(url,
		parameter,
		function(result){
		 showAlert(result,{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
				iFClose();
			}});
		}
	);

	jQuery.ajaxSetup({async:true});
}

//ǰ��˼������ά��
function goDwwh(){
	var url = "szdw_general_dwwh.do?ty=2";
	refreshForm(url);
}

//�����꼶Div
function createNjLvDiv(){
	
	var lx = jQuery("#hidden_lx").val();
	var zgh = jQuery("#hidden_zgh").val();
	
	//·��
	var url = "szdw_dwwh.do?method=createNjLvDiv";
	//����
 	var parameter = {
 		"str_lx":lx,
 		"str_zgh":zgh
 	};

	jQuery.ajaxSetup({async: false});
	
 	jQuery("#div_nj").load(url,parameter,function(){});
 	
 	jQuery.ajaxSetup({async: true});
}

//������������Div
function createOtherLvDiv(type){
	
	var url = "szdw_dwwh.do?method=createOtherLvDiv";

	var njV = jQuery("#njV").val();
	var checked_nj = $("checkbox_nj_"+njV).checked;
	var lx = jQuery("#hidden_lx").val();
	var zgh = jQuery("#hidden_zgh").val();
	
	//����
 	var parameter ={
 		"str_njV":njV,
 		"str_checked_nj":checked_nj,
 		"str_lx":lx,
 		"str_zgh":zgh,
 		"str_type":type
 	};
	
	jQuery.ajaxSetup({async: false});
	
	jQuery("#div_other").load(url,parameter,function(){
		//������
		var dbs = jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).length;
		
		jQuery("#span_dbs").html(dbs);	
		
		var node = $("div_fpbj_new");
		
		jQuery("input[type=checkbox]:checked").each(function(){
			
			var bjid = jQuery(this).attr("id");
			
			if(bjid.split("_")[1] == "bj"){
			
				if(!$("hidden_bjdm_"+jQuery(this).val())){
					var tmp = document.createElement("input");
						tmp.type = "text";
						tmp.name = "hidden_bjdm";
						tmp.id = "hidden_bjdm_"+jQuery(this).val();
						tmp.value = jQuery(this).val();
						node.appendChild(tmp);	
				}	
			}
		});
		
		createBjClick();
	});
	
	jQuery.ajaxSetup({async: true});
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
}

//�����༶���
function createBjClick(){
	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
		var bjdm = jQuery(this).val();
		if($("checkbox_bj_"+bjdm)){
			jQuery("#checkbox_bj_"+bjdm).attr("checked",true);
		}
	});
}

//����꼶
function clickNj(obj,nj,count,lx){
	
	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	var id=obj.id;
	
	var as = getElementsByClass('current',document,'li');
	for(var i=0;i<as.length;i++){
		as[i].className = "";
	}
	
	obj.parentNode.className = "current";
	
	jQuery("#njV").val(nj);
	jQuery("#hidden_nj").val(count);
	
	//�μ������
	setTimeout("createOtherLvDiv('"+lx+"')",100);
}

//���ѧԺ
function clickXyCheckBox(bzdm){
	
	var checked = $("checkbox_xy_"+bzdm).checked;		
	
	jQuery("input[name=checkbox_zy_"+bzdm+"]").each(
		function(){		
			var id = jQuery(this).attr("id");				
			var checked_zy = $(id).checked;	
			
			var zydm = id.replace("checkbox_zy_","");
			
			if(checked){				
				if(checked_zy){
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",true);
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);
					});
				}else{
					jQuery(this).attr("checked",true);
					
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",true);
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);
					});
				}								
			}else{	
				if(checked_zy){
					jQuery(this).attr("checked",false);	
					
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",false);
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);
					});	
				}else{
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",false);	
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);								
					});	
				}		
							
			}
	});
}

//���רҵ
function clickZyCheckBox(bzdm,sjdm){
	
	var checked = $("checkbox_zy_"+bzdm).checked;
	var checked_xy = $("checkbox_xy_"+sjdm).checked;	
	
	if(checked){
		jQuery("input[name=checkbox_bj_"+bzdm+"]").each(
			function(){
				jQuery(this).attr("checked",true);
				var id = jQuery(this).attr("id");
				var bjdm = id.split("_")[2];
				clickBjCheckBox(bjdm);					
		});
	}else{
		jQuery("input[name=checkbox_bj_"+bzdm+"]").each(
			function(){
				jQuery(this).attr("checked",false);
				var id = jQuery(this).attr("id");
				var bjdm = id.split("_")[2];
				clickBjCheckBox(bjdm);		
		});
	}
}

//����༶
function clickBjCheckBox(bzdm){

	var checked_bj = $("checkbox_bj_"+bzdm).checked;
	
	if(checked_bj){
		
		if(!$("hidden_bjdm_"+bzdm)){
			var node = $("div_fpbj_new");
		
			var tmp = document.createElement("input");
				tmp.type = "text";
				tmp.name = "hidden_bjdm";
				tmp.id = "hidden_bjdm_"+bzdm;
				tmp.value = bzdm;
				node.appendChild(tmp);
		}
	}else{
		if($("hidden_bjdm_"+bzdm)){
			jQuery("#hidden_bjdm_"+bzdm).remove();
		}
	}
}

//�������༶
function disfrockFpbj(){
	
//	var bjdm = new Array();//�༶����
//	var i=0;
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	var url = "szdw_dwwh.do?method=saveFpbj";
//
//	//����
// 	var parameter = {
// 		"array_bjdm":bjdm.join("!!array!!"),
// 		"str_zgh":jQuery("#hidden_zgh").val(),
// 		"str_lx":jQuery("#hidden_lx").val()
//	};
//
//	jQuery.ajaxSetup({async:true});
//	
//	jQuery.post(url,
//		parameter,
//		function(result){
//		
//			alertInfo(result);
//			
//			//�����꼶Div
//			createNjLvDiv();
//			
//			jQuery("#div_fpbj_new").html("");					
//			
//			var index = jQuery("#hidden_nj").val();
//		
//			var id = "";
//			
//			if(index == ""){
//				id = "a_nj_0";
//			}else{
//				id = "a_nj_"+index;
//			}
//					
//			if($(id)){
//				$(id).click();
//			}	
//		}
//	);
//
//	jQuery.ajaxSetup({async:true});
	
	var zgh = jQuery("#select_jzg").val();
	
	if(zgh == "" || zgh == null){
		alertError("���ڽ�ְ���б���ѡ��һλ��ϣ��������ʦ");
		return false;
	}

	var bjdm_my = new Array();//���˰༶
	
	var i=0;

	jQuery("input[name=checkVal]:checked").each(function(){
		bjdm_my[i]= encodeURI(encodeURI(jQuery(this).val()));
		i++;
	});
	
	if(i==0){
		alertInfo("�빴ѡ��Ҫ�����İ༶��");
		return false;
	}
	
	//����
 	var parameter = {
 		"str_zgh":jQuery("#select_jzg").val(),
 		"array_bjdm_my":bjdm_my.join("!!array!!"),
 		"str_lx":jQuery("#hidden_lx").val()
	};

 	confirmInfo("ȷ��Ҫ��������ʦ��ѡ�а༶�ı���ϵ��?",function(ok){
		if(ok=="ok"){
			
			jQuery.ajaxSetup({async:true});
			
			var url = "szdw_dwwh.do?method=disfrockFpbj";
			
			jQuery.post(url,
				parameter,
				function(result){	
					alertInfo(result);
					onShow(zgh);
				}
			);
		
			jQuery.ajaxSetup({async:true});
		}
 	});
}


//�������༶
function saveFpbj(){
	
//	var bjdm = new Array();//�༶����
//	var i=0;
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	var url = "szdw_dwwh.do?method=saveFpbj";
//
//	//����
// 	var parameter = {
// 		"array_bjdm":bjdm.join("!!array!!"),
// 		"str_zgh":jQuery("#hidden_zgh").val(),
// 		"str_lx":jQuery("#hidden_lx").val()
//	};
//
//	jQuery.ajaxSetup({async:true});
//	
//	jQuery.post(url,
//		parameter,
//		function(result){
//		
//			alertInfo(result);
//			
//			//�����꼶Div
//			createNjLvDiv();
//			
//			jQuery("#div_fpbj_new").html("");					
//			
//			var index = jQuery("#hidden_nj").val();
//		
//			var id = "";
//			
//			if(index == ""){
//				id = "a_nj_0";
//			}else{
//				id = "a_nj_"+index;
//			}
//					
//			if($(id)){
//				$(id).click();
//			}	
//		}
//	);
//
//	jQuery.ajaxSetup({async:true});
	
	var zgh = jQuery("#select_jzg").val();
	
	if(zgh == "" || zgh == null){
		alertError("���ڽ�ְ���б���ѡ��һλ��ϣ��������ʦ");
		return false;
	}
	
	var bjdm_other = new Array();//�����༶
	
	var bjdm_my = new Array();//���˰༶
	
	var i=0;
	jQuery("input[name=checkVal][checked=false]").each(function(){
		bjdm_other[i]= encodeURI(encodeURI(jQuery(this).val()));
		i++;
	});
	
	i=0;
	jQuery("input[name=checkVal]:checked").each(function(){
		bjdm_my[i]= encodeURI(encodeURI(jQuery(this).val()));
		i++;
	});
	
	//����
 	var parameter = {
 		"str_zgh":jQuery("#select_jzg").val(),
 		"array_bjdm_other":bjdm_other.join("!!array!!"),
 		"array_bjdm_my":bjdm_my.join("!!array!!"),
 		"str_lx":jQuery("#hidden_lx").val()
	};

	jQuery.ajaxSetup({async:true});
	
	var url = "szdw_dwwh.do?method=saveFpbj";
	
	jQuery.post(url,
		parameter,
		function(result){	
			alertInfo(result);
			onShow(zgh);
		}
	);

	jQuery.ajaxSetup({async:true});
}

//ǰ������Ա���
function goFdybb(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer("��ǰδ���ű�࣬����ϵ����Ա��");
		return false;
	}
	
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 1){
		var zgh = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		var url = "general_szdw.do?method=szdwRybb&lx=fdy&zgh="+zgh;
		refreshForm(url);
	}else{
		alertError("��<font color='blue'>��ѡһ��</font>��ϣ�����Ľ�ʦ��¼");
		return false;
	}		
}

//ǰ�������α��
function goBzrbb(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer("��ǰδ���ű�࣬����ϵ����Ա��");
		return false;
	}
	
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 1){
		var zgh = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		var url = "general_szdw.do?method=szdwRybb&lx=bzr&zgh="+zgh;
		refreshForm(url);
	}else{
		alertError("��<font color='blue'>��ѡһ��</font>��ϣ�����Ľ�ʦ��¼");
		return false;
	}	
}

//��ʾ�༶��Ϣ
function showBjxx12(zgh,lx,num){
	if(num == 0){
		alertError("������Ϊ0���޷��鿴��ϸ");
		return false;
	}else{
	
		//·��
		var url = "szdw_dwwh.do?method=createBjxxDiv";
		//����
	 	var parameter = {
			"str_lx":lx,
			"str_zgh":zgh
		};
		
	 	jQuery("#div_bjxx").load(
			url,
			parameter,
			function(){
				tipsWindown(" ","id:div_bjxx","700","360","true","","true","id");
			}
		);
	}
}

/**
 * �޸ĵ�����ҳ��
 * @param zgh
 * @param lx
 * @param num
 * @return
 */
function showBjxx(zgh,lx,num){
	if(num == 0){
		alertError("������Ϊ0���޷��鿴��ϸ");
		return false;
	}else{
		var url = "szdw_dwwh.do?method=createBjxx&zgh="+zgh+"&lx="+lx;
		showDialog("", 700, 360, url);
	}
}

//��ʾ��ְ����Ϣ
function showJzgxx(bjdm,lx,num){
	if(num == 0){
		alertError("��ʦ��Ϊ0���޷��鿴��ϸ");
		return false;
	}else{
	
		//·��
		var url = "szdw_dwwh.do?method=createJzgxxDiv";
		//����
	 	var parameter = {
			"str_lx":lx,
			"str_bjV":bjdm
		};
		
	 	jQuery("#div_jzgxx").load(
			url,
			parameter,
			function(){
				tipsWindown(" ","id:div_jzgxx","700","360","true","","true","id");
			}
		);
	}
}

//���ݳ������ڼ�������
function calculateAges(str)   
{   
      var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
      if(r==null)return null;     
      var d= new Date(r[1],r[3]-1,r[4]);     
      if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4])   
      {   
            var Y = new Date().getFullYear();   
            return((Y-r[1]));   
      }   
      return("��������ڸ�ʽ����");   
}  

//��ʦ�������ά��
function jssfPlwh(){
	var check = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
	var num = check.length;
	if(num >= 1){
		var zghs = "";
		jQuery(check).each(function(i){
			zghs += jQuery(this).val();
		    if(i != num-1){
		    	zghs += ",";
		    }
		});
		//showTopWin("/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh="+zgh,830,600); .
		showDialog('��ʦ�������ά��', 350, 200, "szdw_fdyxx.do?method=jssfPlwh&zghs="+zghs);
		//window.open ('/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh='+zgh);
	}else{
		alertError("�빴ѡ<font color='blue'>��¼</font>���ٽ��в�����");
		return false;
	}
}

//�����ʦ�������ά����
function savejssfPlwh(){
	if(jQuery("[name='jssf']:checked").length == 0){
		showAlert("�뽫��<font color='red'>*</font>�ı�������д������");
		return false;
	}
	var url = "szdw_fdyxx.do?method=savejssfPlwh" ;
	ajaxSubFormWithFun("fdyxxModel", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

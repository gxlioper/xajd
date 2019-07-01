//�����Ѿ�������Ϣ
function loadYfpInfo(){
	
	// �������
	var fplx=jQuery("#hid_fplx").val();
	
	// ����Ա���
	if(fplx=="fdy"){
		
		loadYfpFdy();
	
	// �����α��
	}else if(fplx=="bzr"){
		
		loadYfpBzr();
		
	}
}

//�ѷ��丨��Ա��Ϣ����
function loadYfpFdy(){

	var ie = "ie";
	
	var bjdm=jQuery("#bj").val();
	
	var url="szdw_szbb.do?method=loadYfpFdy";
	
	//��������
 	var parameter = {
 		"ie":ie,
 		"stylePath":stylePath,
		"bjdm":bjdm
	};
	
	jQuery("#yfp_tbody").load(url,parameter,function(){});
}

//�ѷ����������Ϣ����
function loadYfpBzr(){
	
	
	var ie = "ie";
	
	var bjdm=jQuery("#bj").val();
	
	var url="szdw_szbb.do?method=loadYfpBzr";
	
	//��������
 	var parameter = {
 		"ie":ie,
 		"stylePath":stylePath,
		"bjdm":bjdm
	};
	
	jQuery("#yfp_tbody").load(url,parameter,function(){});
}

// Ĭ�ϼ���
function onShow(){

	// �ж��Ƿ���ѡ���༶
	if($("bj") && $("bj").length>0 && ($("bj").value==null || $("bj").value=="")){// ��
		// ȥ����һ�пհ�������
		$("bj").removeChild($("bj").options[0]);
		// ѡ�е�һ��
	}else if($("bj") && $("bj").length>0){// ��
		// ȥ����һ�пհ�������
		$("bj").removeChild($("bj").options[0]);
	} else {
        initBjListBySzbb();
	}

	loadYfpInfo();
	searchRs();
	titleNjxyzybj();
	
}

//��ѯ���
function searchRs(){

	jQuery.ajaxSetup({async:false});
	// ˼�����
	var url = "szdw_szbb.do?method=searchSetting";
	var ie = "ie";

	// �ְ�ѡ�еİ༶����
	var bjdm=jQuery("#bj").val();
	var parameter = {"ie":ie,"bjdm":bjdm};
	
	// ��ѯ����
	jQuery("select,input",jQuery("#tbody_search_query")).each(function(){
		parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
	})
	
	// �������
	parameter["hid_fplx"]=jQuery("#hid_fplx").val();

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	// ��ѯ����
	searchGo(url,parameter);

	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// ˼������ ��ѯ��Ϊ��ѡ���༶��
// ����Ա ��������Ϣ
function searchGo(url,parameter){
	
	// ��ҳ
	var currentPage = "1";
	if($("currentPage")){
		currentPage = $("currentPage").value;
	}
	
	var editPageSize = "";
	if($("editPageSize")){
		editPageSize = $("editPageSize").value;	
	}
		
	var pagesize = "";
	if($("pagesize")){
		pagesize = $("pagesize").value;
	}


	parameter["currentPage"]=currentPage;
	parameter["editPageSize"]=editPageSize;
	parameter["pagesize"]=pagesize;

 	$("divWaiting").style.display="";
	$("divDisable").style.display="";

	jQuery("#div_rs").load(url,parameter,function(){
		
		jQuery("#table_rs td").each(function(){
			// �и߿���
			jQuery(this).attr("height","10");
			jQuery(this).css("text-align","center");
		});
		setTimeout("setPageInfo()",500);
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
	});
}

// ȡ�������α��
function cancelBzrbb(zgh,bjdm){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=cancelBzrbb";

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// ȡ������Ա���
function cancelFdybb(zgh,bjdm){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=cancelFdybb";

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// ����Ա���
function setFdybb(zgh){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=setFdybb";

	var bjdm=jQuery("#bj").val();

	if(bjdm==null || bjdm==""){
		alertInfo("��ѡ����Ҫ����İ༶��",function(){});
		return false;
	}

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// �����α��
function setBzrbb(zgh){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=setBzrbb";

	var bjdm=jQuery("#bj").val();
	
	if(bjdm==null || bjdm==""){
		alertInfo("��ѡ����Ҫ����İ༶��",function(){});
		return false;
	}

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// ����
function goHome(fplx){
    var url;
	if("fdy" == fplx){
        url = "szdw_general_szbb.do";
	} else {
		url = "szdw_general_szbzrbb.do";
	}

	refreshForm(url);
}

// ˼����� �༶�б����
function initBjListBySzbb(){
    //dataLoad(true);
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var userType = "";
	var fbqk= "";
	var isFdy="false";
	var isBzr="false";
	var fplx="";
	var sydm="";
	if($("userName")){userName = $("userName").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("fbqk")){fbqk=$("fbqk").value};
	if($("isFdy")){isFdy=$("isFdy").value};
	if(jQuery("#hid_fplx")){
        fplx = jQuery("#hid_fplx").val();
	}
	if($("sy")){sydm = $("sy").value};
	if($("userType")&&$("userType")!=null){userType = $("userType").value};
	if("bzr"==userType){
		isFdy = "true";
	}
	if($("isBzr")){isBzr = $("isBzr").value};
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}
	if(fbqk == null || fbqk == ""){
		fbqk = "%";
	} else{
		fbqk = "%" + fbqk + "%";
	}
    if(sydm == null || sydm == ""){
        sydm = "%";
    } else{
        sydm = "%" + sydm + "%";
    }
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;
    query += "!!-!!";
    query += sydm;
	query += "!!-!!";
	query += fbqk;
		GetListData.getBjListBySzbb(query,userName,isFdy,isBzr,fplx,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"dm","mc");
					if($("ysbjdm")){//ԭʼ�༶
						DWRUtil.removeAllOptions("ysbjdm");
						DWRUtil.addOptions("ysbjdm",data,"dm","mc");
						$("ysbjdm").options[0].value = "";
					}
					if($("mkbjdm")){//ģ��༶
						DWRUtil.removeAllOptions("mkbjdm");
						DWRUtil.addOptions("mkbjdm",data,"dm","mc");						
						$("mkbjdm").options[0].value = "";
					}
					
					if($(objId + "V")){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
					
					if($("bj").options.length>0 ){
						$("bj").options[0].selected=true;
					}
					loadYfpInfo();
					searchRs();
					titleNjxyzybj();
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
}

function titleLoad(id){
	
	if(jQuery("#"+id)){
	
		jQuery("#"+id).children("option").each(function(){
			jQuery(this).attr("title",jQuery(this).text());
		});
	}
}

function titleNjxyzybj(){

	titleLoad('nj');
	setTimeout("titleLoad('xy')",500);
	setTimeout("titleLoad('zy')",500);
	setTimeout("titleLoad('bj')",500);
}
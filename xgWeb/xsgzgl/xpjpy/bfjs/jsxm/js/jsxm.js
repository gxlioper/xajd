/**
 * ��Ŀ���Ͱ��¼�
 */
function bindXmlxEvent(){
	jQuery(":radio").bind("click",function(){
		if(jQuery(":radio:checked").val() == "4"){
			jQuery("#mrfTr").hide();
			jQuery("#qzbTr").hide();
			jQuery("#zdfTr").hide();
			jQuery("#zxfTr").hide();
		}else if (jQuery(":radio:checked").val() == "3"){
			jQuery("#mrfTr").show();
			jQuery("#qzbTr").hide();
			jQuery("#zdfTr").hide();
			jQuery("#zxfTr").hide();
		} else {
			jQuery("#mrfTr").hide();
			jQuery("#qzbTr").show();
			jQuery("#zdfTr").show();
			jQuery("#zxfTr").show();
		}
	});
}
function isHaveSubmit(){
	var isHaveSubmit = false;
	jQuery.ajaxSetup({async:false});
	
	jQuery.post("xpjpyBfjsFswh.do?method=isHaveSubmitClass",{},function(data){
		isHaveSubmit = data == "true";
	});
	jQuery.ajaxSetup({async:true});
	return isHaveSubmit;
}



/**
 * ���Ӿ�����Ŀ
 * @param obj
 */
function addBfjsJsxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("���а༶�ύ�����֣����ܵ���������Ŀ��");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	showDialog("���Ӿ�����Ŀ",450,300,"xpjpyBfjsJsxm.do?method=addBfjsJsxm&fjdm="+xmdm);
}

/**
 * �޸ľ�����Ŀ
 * @param obj
 */
function editBfjsJsxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("���а༶�ύ�����֣����ܵ���������Ŀ��");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	showDialog("�޸ľ�����Ŀ",450,300,"xpjpyBfjsJsxm.do?method=editBfjsJsxm&xmdm="+xmdm);
}

/**
 * ɾ��������Ŀ
 * @param obj
 */
function delBfjsJsxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("���а༶�ύ�����֣����ܵ���������Ŀ��");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	var zjxmArray = jQuery("div[fjdm="+xmdm+"]");
	var yjXmdm = jQuery("div[fjdm='N']").attr("xmdm");
	var ejxmArray = jQuery("div[fjdm="+yjXmdm+"]");
	
	if ( yjXmdm == jQuery(obj).parents("div .OrgBox").attr("fjdm") && jQuery("div[fjdm='"+yjXmdm+"']").length <= 1){
		showAlert("�����ٱ���һ�����������Ŀ!");
		return false;
	}
	
	if (zjxmArray.length != 0){
		showConfirm("ɾ������Ŀ������Ӽ���Ŀһ��ɾ������ȷ��Ҫ��������",{"okFun":function(){
			jQuery.post("xpjpyBfjsJsxm.do?method=delBfjsJsxm",{xmdm:xmdm},function(data){
				showAlert(data["message"]);
				document.location.href = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
			},"json");
		}});
	} else {
		showConfirm("��ȷ��Ҫɾ������Ŀ��",{"okFun":function(){
			jQuery.post("xpjpyBfjsJsxm.do?method=delBfjsJsxm",{xmdm:xmdm},function(data){
				showAlert(data["message"]);
				document.location.href = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
			},"json");
		}});
	}
}

/**
 * ��ʼ��������Ŀ
 */
function initJsxm(){
	var addButton = "<a href=\"#\" title=\"����\" class=\"add\" onclick=\"addBfjsJsxm(this);\"></a>";
	var editButton = "<a href=\"#\" title=\"�༭\" class=\"edit\" onclick=\"editBfjsJsxm(this);\"></a>";
	var delButton = "<a href=\"#\" title=\"ɾ��\" class=\"del\" onclick=\"delBfjsJsxm(this);\"></a>";
	
	jQuery.post("xpjpyBfjsJsxm.do?method=getBfjsJsxm",{},function(data){
		if (data["N"] == null){
			jQuery("body").append("<font color='red'>����ѡ���������ڣ�</font>");
			return false;
		}
		
		//��߼�--�����ܷ�
		var zczfXmdm = data["N"][0]["xmdm"];
		var zczf=new OrgNode();
		zczf.customParam.label=data["N"][0]["xmmc"];
		zczf.customParam.type="����:"+data["N"][0]["qzbl"]+"%";
		zczf.customParam.min="";
		zczf.customParam.xmdm=zczfXmdm;
		zczf.customParam.fjdm="N";
		zczf.customParam.button=addButton;
		
		//����������Ŀ
		var ejxmArray = data[zczfXmdm];
		for (var i = 0 ; i < ejxmArray.length ; i++){
			var ejxm = new OrgNode();
			var ejxmmc = ejxmArray[i]["xmmc"];
			var ejbutton = editButton;
			
			//������Ŀ
			var sjxmArray = data[ejxmArray[i]["xmdm"]];
			
			if (ejxmArray[i]["xmlx"] == "3"){
				ejxm.customParam.type="Ĭ�Ϸ�:"+ejxmArray[i]["mrfs"];
				ejbutton+=delButton;
			} else if(ejxmArray[i]["xmlx"] == "4"){
				ejxm.customParam.type="( �ȼ� )";
				ejbutton+=delButton;
			}else {
				ejxmmc += ejxmArray[i]["xmlx"] == "1" ? "(+)" : "(-)";
				ejxm.customParam.type="����:"+ejxmArray[i]["qzbl"]+"%";
				ejbutton+=addButton+delButton;
				if (sjxmArray == null || sjxmArray.length == 0){
					ejxm.customParam.type+="<br/><br/>��С��:"+ejxmArray[i]["zxfz"]+"<br/><br/>����:"+ejxmArray[i]["zdfz"];
				}
			}
			ejxm.customParam.label=ejxmmc;
			ejxm.customParam.xmdm=ejxmArray[i]["xmdm"];
			ejxm.customParam.fjdm=zczfXmdm;
			ejxm.customParam.button=ejbutton;
			zczf.Nodes.Add(ejxm);
			
			if (sjxmArray != null && sjxmArray.length > 0){
				for (var j = 0 ; j < sjxmArray.length ; j++){
					var sjxm = new OrgNode();
					var sjxmmc = sjxmArray[j]["xmmc"];
					var sjButton = editButton;
					
					if (sjxmArray[j]["xmlx"] == "3"){
						sjxm.customParam.type="Ĭ�Ϸ�:"+sjxmArray[j]["mrfs"];
						sjButton+=delButton;
					} else if(sjxmArray[j]["xmlx"] == "4"){
						sjxm.customParam.type="( �ȼ� )";
						sjButton+=delButton;
					}else {
						sjxmmc += sjxmArray[j]["xmlx"] == "1" ? "(+)" : "(-)";
						sjxm.customParam.type="����:"+sjxmArray[j]["qzbl"]+"%"+"<br/><br/>��С��:"+sjxmArray[j]["zxfz"]+"<br/><br/>����:"+sjxmArray[j]["zdfz"];
						sjButton+=delButton;
					}
					sjxm.customParam.label=sjxmmc;
					sjxm.customParam.xmdm=sjxmArray[j]["xmdm"];
					sjxm.customParam.fjdm=ejxmArray[i]["xmdm"];
					sjxm.customParam.button=sjButton;
					ejxm.Nodes.Add(sjxm);
				}
			}
		}
		
		var OrgShows=new OrgShow(zczf);
		OrgShows.Top=15;
		OrgShows.Left=100;
		OrgShows.IntervalWidth=12;
		OrgShows.IntervalHeight=20;
		OrgShows.ShowType=2;
		OrgShows.BoxHeight=100;
		OrgShows.BoxTemplet="<div id=\"{Id}\" xmdm=\"{xmdm}\"  fjdm=\"{fjdm}\" class=\"OrgBox\">";
		OrgShows.BoxTemplet+="<div id=\"OrgBox1\">{button}</div><span>{label}</span>";
		OrgShows.BoxTemplet+="<br/><br/>{type}</div>";
		OrgShows.Run();
	},"json");
}

/**
 * ���ӱ��澺����Ŀ
 */
function saveBfjsJsxm(){
	
	var xmlx = jQuery(":radio:checked").val();
		if (jQuery("#xmmc").val()=="" || 
			(xmlx!="3" && (jQuery("#qzbl").val() == "" || jQuery("#zdfz").val() == "" || jQuery("#zxfz").val() ==""))  
			|| (xmlx =="3" && jQuery("#mrfs").val() == "")){
			showAlert("�뽫��������д������");
			return false;
		}
	var url = "xpjpyBfjsJsxm.do?method=saveBfjsJsxm";
	ajaxSubFormWithFun("BfjsJsxmModel",url,function(data){
		if (data["success"] == "false"){
			showAlert("������Ŀ�����Ѵ��ڣ�");
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				if(frameElement.api){
					var api = frameElement.api,W = api.opener;
					W.document.location = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
					closeDialog();
				}
			}});
		}
		
	});
	
	
}


/**
 * �޸ı��澺����Ŀ
 */
function updateBfjsJsxm(){
	
	var xmlx = jQuery(":radio:checked").val();

	if (jQuery("#xmmc").val()=="" || 
		(xmlx!="3"&&xmlx!="4"&& (jQuery("#qzbl").val() == "" || jQuery("#zdfz").val() == "" || jQuery("#zxfz").val() ==""))  
		|| (xmlx =="3" && jQuery("#mrfs").val() == "")){
		showAlert("�뽫��������д������");
		return false;
	}
	
	var isUpdate = jQuery("#isUpdate").val();
	var Jsxmjb = jQuery("#Jsxmjb").val();
	var xmjbmc;
	
	if (Jsxmjb == "1")
		xmjbmc = "�꼶";
	if (Jsxmjb == "2")
		xmjbmc = "Ժϵ";
	
	if ("true" == isUpdate){
		showConfirm("�Ƿ񽫱���ͳһ���µ���"+xmjbmc+"��",
			{"okFun":function(){
				var url = "xpjpyBfjsJsxm.do?method=updateBfjsJsxm&tbbl=yes";
				ajaxSubFormWithFun("BfjsJsxmModel",url,function(data){
					if(frameElement.api){
						var api = frameElement.api,W = api.opener;
						W.document.location = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
						closeDialog();
					}
				});
			},"cancelFun":function(){
				var url = "xpjpyBfjsJsxm.do?method=updateBfjsJsxm";
				ajaxSubFormWithFun("BfjsJsxmModel",url,function(data){
					if(frameElement.api){
						var api = frameElement.api,W = api.opener;
						W.document.location = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
						closeDialog();
					}
				});
			}},{okTxt: '��',	cancelTxt: '��'});
		return ;
	}
	
	var url = "xpjpyBfjsJsxm.do?method=updateBfjsJsxm&tbbl=yes";
	ajaxSubFormWithFun("BfjsJsxmModel",url,function(data){
		if (data["success"] == "false"){
			showAlert("������Ŀ�����Ѵ��ڣ�");
		}else{
			if(frameElement.api){
				var api = frameElement.api,W = api.opener;
				W.document.location = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
				closeDialog();
			}
		}
	});
}
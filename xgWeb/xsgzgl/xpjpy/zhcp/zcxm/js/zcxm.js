/**
 * 项目类型绑定事件
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

/**
 * 根据是否有班级提交了综测分，判断是否允许增、删、改 综测结构
 * @returns {Boolean}
 */
function isHaveSubmit(){
	var isHaveSubmit = false;
	jQuery.ajaxSetup({async:false});
	
	jQuery.post("xpj_zcfs.do?method=isHaveSubmitClass",{},function(data){
		isHaveSubmit = data == "true";
	});
	jQuery.ajaxSetup({async:true});
	return isHaveSubmit;
}



/**
 * 增加综测项目
 * @param obj
 */
function addZcxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("已有班级提交综测分，不能调整综测项目。");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	showDialog("增加综测项目",450,300,"xpj_zcxm.do?method=addZcxm&fjdm="+xmdm);
}

/**
 * 修改综测项目
 * @param obj
 */
function editZcxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("已有班级提交综测分，不能调整综测项目。");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	showDialog("修改综测项目",450,300,"xpj_zcxm.do?method=editZcxm&xmdm="+xmdm);
}

/**
 * 删除综测项目
 * @param obj
 */
function delZcxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("已有班级提交综测分，不能调整综测项目。");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	var zjxmArray = jQuery("div[fjdm="+xmdm+"]");
	var yjXmdm = jQuery("div[fjdm='N']").attr("xmdm");
	var ejxmArray = jQuery("div[fjdm="+yjXmdm+"]");
	
	if ( yjXmdm == jQuery(obj).parents("div .OrgBox").attr("fjdm") && jQuery("div[fjdm='"+yjXmdm+"']").length <= 1){
		showAlert("请至少保留一项二级综测项目!");
		return false;
	}
	
	if (zjxmArray.length != 0){
		showConfirm("删除该项目将会把子级项目一起删除，您确定要这样做吗？",{"okFun":function(){
			jQuery.post("xpj_zcxm.do?method=delZcxm",{xmdm:xmdm},function(data){
				showAlert(data["message"]);
				document.location.href = "xpj_zcxm.do?method=viewZcxm";
			},"json");
		}});
	} else {
		showConfirm("您确定要删除该项目吗？",{"okFun":function(){
			jQuery.post("xpj_zcxm.do?method=delZcxm",{xmdm:xmdm},function(data){
				showAlert(data["message"]);
				document.location.href = "xpj_zcxm.do?method=viewZcxm";
			},"json");
		}});
	}
}

/**
 * 初始化综测项目
 */
function initZcxm(){
	var addButton = "<a href=\"#\" title=\"增加\" class=\"add\" onclick=\"addZcxm(this);\"></a>";
	var editButton = "<a href=\"#\" title=\"编辑\" class=\"edit\" onclick=\"editZcxm(this);\"></a>";
	var delButton = "<a href=\"#\" title=\"删除\" class=\"del\" onclick=\"delZcxm(this);\"></a>";
	
	jQuery.post("xpj_zcxm.do?method=getZcxm",{},function(data){
		if (data["N"] == null){
			jQuery("body").append("<font color='red'>请先选择评奖周期！</font>");
			return false;
		}
		
		//最高级--综测总分
		var zczfXmdm = data["N"][0]["xmdm"];
		var zczf=new OrgNode();
		zczf.customParam.label=data["N"][0]["xmmc"];
		zczf.customParam.type="比例:"+data["N"][0]["qzbl"]+"%";
		zczf.customParam.min="";
		zczf.customParam.xmdm=zczfXmdm;
		zczf.customParam.fjdm="N";
		zczf.customParam.button=addButton;
		
		//二级综测项目
		var ejxmArray = data[zczfXmdm];
		for (var i = 0 ; i < ejxmArray.length ; i++){
			var ejxm = new OrgNode();
			var ejxmmc = ejxmArray[i]["xmmc"];
			var ejbutton = editButton;
			
			//三级项目
			var sjxmArray = data[ejxmArray[i]["xmdm"]];
			
			if (ejxmArray[i]["xmlx"] == "3"){
				ejxm.customParam.type="默认分:"+ejxmArray[i]["mrfs"];
				ejbutton+=delButton;
			} else if(ejxmArray[i]["xmlx"] == "4"){
				ejxm.customParam.type="( 等级 )";
				ejbutton+=delButton;
			}else {
				ejxmmc += ejxmArray[i]["xmlx"] == "1" ? "(+)" : "(-)";
				ejxm.customParam.type="比例:"+ejxmArray[i]["qzbl"]+"%";
				ejxmArray[i]["jktb"]==null ? ejbutton+=addButton+delButton : "";
				
				if (sjxmArray == null || sjxmArray.length == 0){
					ejxm.customParam.type+="<br/><br/>最小分:"+ejxmArray[i]["zxfs"]+"<br/><br/>最大分:"+ejxmArray[i]["zdfs"];
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
						sjxm.customParam.type="默认分:"+sjxmArray[j]["mrfs"];
						sjButton+=delButton;
					} else if(sjxmArray[j]["xmlx"] == "4"){
						sjxm.customParam.type="( 等级 )";
						sjButton+=delButton;
					}else {
						sjxmmc += sjxmArray[j]["xmlx"] == "1" ? "(+)" : "(-)";
						sjxm.customParam.type="比例:"+sjxmArray[j]["qzbl"]+"%"+"<br/><br/>最小分:"+sjxmArray[j]["zxfs"]+"<br/><br/>最大分:"+sjxmArray[j]["zdfs"];
						sjxmArray[j]["jktb"]==null ? sjButton+=delButton : "";
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
 * 增加保存综测项目
 */
function saveZcxm(){
	
	var xmlx = jQuery(":radio:checked").val();
	
	if(xmlx!="4"){
		if (jQuery("#xmmc").val()=="" || 
			(xmlx!="3" && (jQuery("#qzbl").val() == "" || jQuery("#zdfs").val() == "" || jQuery("#zxfs").val() ==""))  
			|| (xmlx =="3" && jQuery("#mrfs").val() == "")){
			showAlert("请将必填项填写完整。");
			return false;
		}
	}else{ //如果综测类型为“等级”清空其余字段
		jQuery("#qzbl").attr("");
		jQuery("#zdfs").attr("");
		jQuery("#zxfs").attr("");
		jQuery("#mrfs").attr("");
	}
	
	
	var url = "xpj_zcxm.do?method=saveZcxm";
	ajaxSubFormWithFun("zcxmForm",url,function(data){
		if (data["success"] == "false"){
			showAlert("综测项目名称已存在！");
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				if(frameElement.api){
					var api = frameElement.api,W = api.opener;
					W.document.location = "xpj_zcxm.do?method=viewZcxm";
					closeDialog();
				}
			}});
		}
		
	});
	
	
}


/**
 * 修改保存综测项目
 */
function updateZcxm(){
	
	var xmlx = jQuery(":radio:checked").val();

	if (jQuery("#xmmc").val()=="" || 
		(xmlx!="3"&&xmlx!="4"&& (jQuery("#qzbl").val() == "" || jQuery("#zdfs").val() == "" || jQuery("#zxfs").val() ==""))  
		|| (xmlx =="3" && jQuery("#mrfs").val() == "")){
		showAlert("请将必填项填写完整。");
		return false;
	}
	
	var isUpdate = jQuery("#isUpdate").val();
	var zcxmjb = jQuery("#zcxmjb").val();
	var xmjbmc;
	
	if (zcxmjb == "1")
		xmjbmc = "年级";
	if (zcxmjb == "2")
		xmjbmc = "院系";
	
	if ("true" == isUpdate){
		showConfirm("是否将比例统一更新到各"+xmjbmc+"？",
			{"okFun":function(){
				var url = "xpj_zcxm.do?method=updateZcxm&tbbl=yes";
				ajaxSubFormWithFun("zcxmForm",url,function(data){
					if(frameElement.api){
						var api = frameElement.api,W = api.opener;
						W.document.location = "xpj_zcxm.do?method=viewZcxm";
						closeDialog();
					}
				});
			},"cancelFun":function(){
				var url = "xpj_zcxm.do?method=updateZcxm";
				ajaxSubFormWithFun("zcxmForm",url,function(data){
					if(frameElement.api){
						var api = frameElement.api,W = api.opener;
						W.document.location = "xpj_zcxm.do?method=viewZcxm";
						closeDialog();
					}
				});
			}},{okTxt: '是',	cancelTxt: '否'});
		return ;
	}
	
	var url = "xpj_zcxm.do?method=updateZcxm&tbbl=yes";
	ajaxSubFormWithFun("zcxmForm",url,function(data){
		if (data["success"] == "false"){
			showAlert("综测项目名称已存在！");
		}else{
			if(frameElement.api){
				var api = frameElement.api,W = api.opener;
				W.document.location = "xpj_zcxm.do?method=viewZcxm";
				closeDialog();
			}
		}
	});
}
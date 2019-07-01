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
 * 增加竞赛项目
 * @param obj
 */
function addBfjsJsxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("已有班级提交竞赛分，不能调整竞赛项目。");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	showDialog("增加竞赛项目",450,300,"xpjpyBfjsJsxm.do?method=addBfjsJsxm&fjdm="+xmdm);
}

/**
 * 修改竞赛项目
 * @param obj
 */
function editBfjsJsxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("已有班级提交竞赛分，不能调整竞赛项目。");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	showDialog("修改竞赛项目",450,300,"xpjpyBfjsJsxm.do?method=editBfjsJsxm&xmdm="+xmdm);
}

/**
 * 删除竞赛项目
 * @param obj
 */
function delBfjsJsxm(obj){
	
	if (isHaveSubmit()){
		showAlertDivLayer("已有班级提交竞赛分，不能调整竞赛项目。");
		return false;
	}
	
	var xmdm = jQuery(obj).parents("div .OrgBox").attr("xmdm");
	var zjxmArray = jQuery("div[fjdm="+xmdm+"]");
	var yjXmdm = jQuery("div[fjdm='N']").attr("xmdm");
	var ejxmArray = jQuery("div[fjdm="+yjXmdm+"]");
	
	if ( yjXmdm == jQuery(obj).parents("div .OrgBox").attr("fjdm") && jQuery("div[fjdm='"+yjXmdm+"']").length <= 1){
		showAlert("请至少保留一项二级竞赛项目!");
		return false;
	}
	
	if (zjxmArray.length != 0){
		showConfirm("删除该项目将会把子级项目一起删除，您确定要这样做吗？",{"okFun":function(){
			jQuery.post("xpjpyBfjsJsxm.do?method=delBfjsJsxm",{xmdm:xmdm},function(data){
				showAlert(data["message"]);
				document.location.href = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
			},"json");
		}});
	} else {
		showConfirm("您确定要删除该项目吗？",{"okFun":function(){
			jQuery.post("xpjpyBfjsJsxm.do?method=delBfjsJsxm",{xmdm:xmdm},function(data){
				showAlert(data["message"]);
				document.location.href = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
			},"json");
		}});
	}
}

/**
 * 初始化竞赛项目
 */
function initJsxm(){
	var addButton = "<a href=\"#\" title=\"增加\" class=\"add\" onclick=\"addBfjsJsxm(this);\"></a>";
	var editButton = "<a href=\"#\" title=\"编辑\" class=\"edit\" onclick=\"editBfjsJsxm(this);\"></a>";
	var delButton = "<a href=\"#\" title=\"删除\" class=\"del\" onclick=\"delBfjsJsxm(this);\"></a>";
	
	jQuery.post("xpjpyBfjsJsxm.do?method=getBfjsJsxm",{},function(data){
		if (data["N"] == null){
			jQuery("body").append("<font color='red'>请先选择评定周期！</font>");
			return false;
		}
		
		//最高级--竞赛总分
		var zczfXmdm = data["N"][0]["xmdm"];
		var zczf=new OrgNode();
		zczf.customParam.label=data["N"][0]["xmmc"];
		zczf.customParam.type="比例:"+data["N"][0]["qzbl"]+"%";
		zczf.customParam.min="";
		zczf.customParam.xmdm=zczfXmdm;
		zczf.customParam.fjdm="N";
		zczf.customParam.button=addButton;
		
		//二级竞赛项目
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
				ejbutton+=addButton+delButton;
				if (sjxmArray == null || sjxmArray.length == 0){
					ejxm.customParam.type+="<br/><br/>最小分:"+ejxmArray[i]["zxfz"]+"<br/><br/>最大分:"+ejxmArray[i]["zdfz"];
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
						sjxm.customParam.type="比例:"+sjxmArray[j]["qzbl"]+"%"+"<br/><br/>最小分:"+sjxmArray[j]["zxfz"]+"<br/><br/>最大分:"+sjxmArray[j]["zdfz"];
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
 * 增加保存竞赛项目
 */
function saveBfjsJsxm(){
	
	var xmlx = jQuery(":radio:checked").val();
		if (jQuery("#xmmc").val()=="" || 
			(xmlx!="3" && (jQuery("#qzbl").val() == "" || jQuery("#zdfz").val() == "" || jQuery("#zxfz").val() ==""))  
			|| (xmlx =="3" && jQuery("#mrfs").val() == "")){
			showAlert("请将必填项填写完整。");
			return false;
		}
	var url = "xpjpyBfjsJsxm.do?method=saveBfjsJsxm";
	ajaxSubFormWithFun("BfjsJsxmModel",url,function(data){
		if (data["success"] == "false"){
			showAlert("竞赛项目名称已存在！");
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
 * 修改保存竞赛项目
 */
function updateBfjsJsxm(){
	
	var xmlx = jQuery(":radio:checked").val();

	if (jQuery("#xmmc").val()=="" || 
		(xmlx!="3"&&xmlx!="4"&& (jQuery("#qzbl").val() == "" || jQuery("#zdfz").val() == "" || jQuery("#zxfz").val() ==""))  
		|| (xmlx =="3" && jQuery("#mrfs").val() == "")){
		showAlert("请将必填项填写完整。");
		return false;
	}
	
	var isUpdate = jQuery("#isUpdate").val();
	var Jsxmjb = jQuery("#Jsxmjb").val();
	var xmjbmc;
	
	if (Jsxmjb == "1")
		xmjbmc = "年级";
	if (Jsxmjb == "2")
		xmjbmc = "院系";
	
	if ("true" == isUpdate){
		showConfirm("是否将比例统一更新到各"+xmjbmc+"？",
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
			}},{okTxt: '是',	cancelTxt: '否'});
		return ;
	}
	
	var url = "xpjpyBfjsJsxm.do?method=updateBfjsJsxm&tbbl=yes";
	ajaxSubFormWithFun("BfjsJsxmModel",url,function(data){
		if (data["success"] == "false"){
			showAlert("竞赛项目名称已存在！");
		}else{
			if(frameElement.api){
				var api = frameElement.api,W = api.opener;
				W.document.location = "xpjpyBfjsJsxm.do?method=viewBfjsJsxm";
				closeDialog();
			}
		}
	});
}
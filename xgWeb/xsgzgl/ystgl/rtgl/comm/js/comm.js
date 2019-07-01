function selYst(){
    var xh = jQuery("#xh").val() || jQuery("a[class='name']").text();
	if(xh == ''){
		showAlert("请先填写基本信息！");
		return false;
	}else{
	  var url = "ystglRtsq.do?method=getYstxmList&xh="+xh;
		showDialog('艺术团选择',770,550,url);
	}
}



function setYst(rows) {
	var api = frameElement.api;
	//获取父页面body对象，用于赋值
	var bodyobj = api.get('parentDialog').jQuery("#ystxm_body");	
	var toggle = api.get('parentDialog').jQuery("#tbody_toggle");	
    jQuery(bodyobj).find("input[name='ystxmmc']").val(rows[0]["ystxmmc"]);
    jQuery(bodyobj).find("input[name='ystid']").val(rows[0]["ystid"]);
    jQuery(bodyobj).find("td[name='ystlb']").text(rows[0]["ystlbmc"]);
    jQuery(bodyobj).find("td[name='gkdwmc']").text(rows[0]["gkdwmc"]);
    jQuery(bodyobj).find("td[name='xmlb']").text(rows[0]["xmlbmc"]);
    jQuery(bodyobj).find("td[name='xn']").text(rows[0]["xn"]);
    jQuery(bodyobj).find("td[name='lxdh']").text(rows[0]["lxdh"]);
    jQuery(toggle).find("td[name='stfzrxm']").text(rows[0]["stfzrxm"]);
    jQuery(toggle).find("td[name='fzrlb']").text(rows[0]["fzrlb"]);
    jQuery(toggle).find("td[name='zdlsxm']").text(rows[0]["zdlsxm"]);
    jQuery(toggle).find("td[name='zcmc']").text(rows[0]["zcmc"]);
    jQuery(toggle).find("td[name='zdlslxfs']").text(rows[0]["zdlslxfs"]);
    jQuery(toggle).find("td[name='ssbmmc']").text(rows[0]["ssbmmc"]);
    
    jQuery(toggle).find("td[name='ystclsj']").text(rows[0]["ystclsj"]);
    jQuery(toggle).find("td[name='ystjj']").text(rows[0]["ystjj"]);
    jQuery(toggle).find("td[name='ysthjqk']").text(rows[0]["ysthjqk"]);
    jQuery(toggle).find("td[name='sqsj']").text(rows[0]["sqsj"]);
	iFClose();
}

//toggle收起展开
function showYstmx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	jQuery(obj).attr("class",newClass);
	jQuery("#tbody_toggle").toggle();
}

//艺术团报名表下载
function getYstbmb(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xhs="";
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要下载的记录！");
	 } else if (rows.length > 1){
		 for ( var int = 0; int < rows.length; int++) {
			 if(int==rows.length-1){
				 xhs += rows[int]["xh"];
				}else{
					xhs += rows[int]["xh"]+",";
					
				}
		}
		var url="ystglRtjg.do?method=getYstbmbZip&value="+xhs;
		 window.open(url);
	 } else {
		var url="ystglRtjg.do?method=getYstbmb&xh="+rows[0]["xh"];
		url = addSuperSearchParams(url);
		document.forms[0].action = url;
		document.forms[0].submit();
     }
}
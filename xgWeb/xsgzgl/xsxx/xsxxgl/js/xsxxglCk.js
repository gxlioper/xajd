var gndm = "xsxx_query";// ���ܴ���
var extend_mid = "XSXX"; //��չ��Ϣ����id
jQuery(function() {
	onShow();
	xsGkPic();
	var xxdm = jQuery("#xxdm").val();
	jQuery("#zdybdfl_xsxx_query_xsxx_byxx").remove();
	jQuery("#zdybdfl_xsxx_query_xsxx_zwjd").remove();
	jQuery("#zdybdfl_li_xsxx_query_xsxx dd:has(a[href='#zdybdfl_href_xsxx_query_xsxx_zwjd'])").remove();
	if("1"!=jQuery("#mklb").val()){
		rcxwjl();  //�ճ���Ϊ��¼չʾ
	}
	
	//����ϼ�
	var jxjs = jQuery("#jxjs").val();
	var jxzje = jQuery("#jxzje").val();
	var pjzje = jQuery("#pjzje").val();
	if(jQuery("#xxdm").val() == '10335'&&jxjs>0){
		jQuery("#zdybdfl_xsxx_query_pjpy_hjqk table tr th span")
		.append("<font color='008DC2'>&nbsp;&nbsp;&nbsp;�ܽ�</font><font color='EA7605'>"+pjzje+"</font><font color='008DC2'>Ԫ</font>")
	}
	if(jQuery("#xxdm").val() != '10335'&&jxjs>0){
		jQuery("#zdybdfl_xsxx_query_pjpy_hjqk table tr th span")
		.append("<font color='008DC2'>&nbsp;&nbsp;&nbsp; ��Ŀ����" +
				"</font><font color='EA7605'>"+jxjs+"</font><font color='008DC2'>&nbsp;&nbsp;&nbsp;�ܽ�</font><font color='EA7605'>"+jxzje+"</font><font color='008DC2'>Ԫ</font>");
	}
	
	//��չ��Ϣչʾ
	if(jQuery('#zdybdfl_xsxx_query_tzxx').length > 1){
		jQuery('#zdybdfl_xsxx_query_tzxx').dataExtend( {
			"mid"      : extend_mid,
			"dataId"   : jQuery('#kzxxid').val(),
			"dataType" : "1",
			"xh"       : jQuery('#xh').val(),
			"bdpzid"   : "xsxxgl",
			"naviBar"  : false,
			"mode"     : "view",
			"actionBar": false
		});
		

		jQuery('#zdybdfl_xsxx_query_tzxx h3:first').hide();
		jQuery('#zdybdfl_xsxx_query_tzxx #extend_content').css('margin-top','0');
		jQuery('#zdybdfl_xsxx_query_tzxx #extend_content #Extend_XSJBXX').hide();
	}
	
	//��չ��Ϣչʾ
	 /*������ҽҩ��ѧ���˻���Ϣ���Ի��鿴*/
	//־Ը����Сʱ���ϼ�
	hjzyfwxss();

    xsyc('zdybdfl_xsxx_query_tbtxxx,zdybdfl_xsxx_query_tsstxxx');
    if (jQuery("#sfzd").length == 1 && jQuery("#zd2").length) {
        var sfzd = jQuery("#sfzd").val();
        var xwdzTr = jQuery("#zd2").parent().parent();
        if(sfzd == '��'){
            xwdzTr.show();
        }else{
            xwdzTr.hide();
        }
    }

    if (jQuery("td[name='zdybdcon_td_hkszd']").length == 1) {
        var td = jQuery("td[name='zdybdcon_td_hkszd']");
        var html = "<button style=\"height: 23px;line-height: 23px;width: 80px;margin-left: 20px;\"" +
			" name=\"�ر�\" onclick=\"zdlsjl('hkszd','�������ڵ�');\" type=\"button\">��ʷ��¼</button>";
        td.append(html);
    }

    if (jQuery("td[name='zdybdcon_td_xwzsxxdz']").length == 1) {
        var td = jQuery("td[name='zdybdcon_td_xwzsxxdz']");
        var html = "<button style=\"height: 23px;line-height: 23px;width: 80px;margin-left: 20px;\"" +
            " name=\"�ر�\" onclick=\"zdlsjl('xwzsxxdz','�־�ס��');\" type=\"button\">��ʷ��¼</button>";
        td.append(html);
    }

    if (jQuery("#zdybdfl_xsxx_query_xsxx_jtcyxx").length == 1) {
        var table = jQuery("#zdybdfl_xsxx_query_xsxx_jtcyxx").find("table").eq(0);
        var th = table.find("th").eq(0);
        var html = "<button style=\"height: 23px;line-height: 23px;width: 80px;margin-left: 20px;\"" +
            " name=\"�ر�\" onclick=\"jtcyLsjl();\" type=\"button\">��ʷ��¼</button>";
        th.append(html);
    }

});

function zdlsjl(zd,zdmc){
	var xh=jQuery("#xh").val();
    showDialog(zdmc+'��ʷ��¼',780,550,'xsxx_xsxxgl.do?method=viewZdLsjl&xh='+xh+"&zd="+zd);
}

function jtcyLsjl(zd,zdmc){
    var xh=jQuery("#xh").val();
    showDialog('��ͥ��Ա��ʷ��¼',780,550,'xsxx_xsxxgl.do?method=viewJtcyLsjl&xh='+xh+"&zd="+zd);
}
//��ʾ����
function xsyc(ids){
    var arr = ids.split(",");
    for(var i=0;i<arr.length;i++){
        var id=arr[i];
        var div = jQuery("#"+id);
        if (div.length>0){
        	//Ĭ������
            div.children("table:last-child").hide();
            //������ʾ/���ذ�ť
            var table0 = div.children("table:first-child");
            var th0 = table0.find("th").eq(0);
            var html = "<div style=\"margin: 4px 0 0 130px;\">" +
				"<a style=\"color:#00F;\" href='javascript:void(0);' name='show_hide' onclick=\"hideOrShow(this,'"+id+"')\">��ʾ</a>" +
				"</div>";
            th0.append(html);
        }
    }

}
function hideOrShow(obj,id){

    var div = jQuery("#"+id);
    var table = div.children("table:last-child");
    if(!table.is(':visible')){
        table.show();
        jQuery(obj).html("����");
	}else{
        table.hide();
        jQuery(obj).html("��ʾ");
	}

}
//־Ը����Сʱ���ϼ�
function hjzyfwxss(){
	var s = jQuery("#zdybdfl_xsxx_query_xsxx_zyfwjl").find("table").eq(1).find("tr").eq(1).find("td").size();
	if(s>1){
		var hjfwxss = 0;
		jQuery("#zdybdfl_xsxx_query_xsxx_zyfwjl").find("table").eq(1).find("tr:gt(0)").each(function(){
			var fwxss = jQuery(this).find("td").eq(4).text();
			hjfwxss+=Number(fwxss);
		});
		var tr = "<tr><td align=\"center\">�ϼƷ���Сʱ��</td><td colspan=\"4\" align=\"center\">"+hjfwxss+"</td></tr>";
		jQuery("#zdybdfl_xsxx_query_xsxx_zyfwjl").find("table").eq(1).append(tr);
	}
}

//����ʦ���߿���Ƭ���Ի�
function xsGkPic(){
	if("10511"!=jQuery("#xxdm").val()){
	jQuery("#stuGkImg").css("display","none");
	}
	
}
//���ͨ
function xsxxDis(){
	if ("��" == jQuery("td[name='zdybdcon_td_zd1']").text().trim()) {
		jQuery("td[name='zdybdcon_td_zd2']").parents("tr").show();
	}
	else{
		jQuery("td[name='zdybdcon_td_zd2']").parents("tr").hide();
	}
}
function onShow() {

	var url = "xsxx_xsxxgl.do?method=xsxxglCk&type=query";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			xh : jQuery("#xh").val()
		},
		dataType : "json",
		success : function(data) {
			var xm = data.xm;
			jQuery("#xmView").html(xm);
			zdybdInit(gndm, data);
		}
	});
}

//�ճ���Ϊ��¼
function showInfo(obj) {		
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	
	jQuery(obj).attr("class",newClass);
	var data = jQuery(obj).attr("data");
	if("down"==newClass){
		getRcxwxx(data);
		}
	
	jQuery("#tab_rcxw").find("[data='" + data + "'][type='detail-data']").toggle();
	}
function getRcxwxx(xwxxObj){
	var url = "rcsw_rcxwwhnew_rcxwjggl.do?method=getRcxwMx";
	
	var xwxx=xwxxObj.split("_");
	var xwdlObj=jQuery("#tab_rcxw").find("[data='" + xwxxObj + "'][type='detail-data']");
	xwdlObj.children().children(".tbody_rcxw").html("");
	var html="";
	jQuery.post(url, {xh:xwxx[0],xq:xwxx[1],xn:xwxx[2],rcxwlbdm:xwxx[3]}, function(data) {
		if(0==data.length){
			html += "<tr><td colspan='4' align='center'>����Ϊ��¼��Ϣ��</td>";
		}
		else{
		for ( var int = 0; int < data.length; int++) {
			html += "<tr><td align='center'>" + data[int]["rcxwlbdlmc"] + "</td>";
			html += "<td align='center'>" + data[int]["rcxwlbxlmc"] + "</td>";
			html+="<td align='center'>" + data[int]["fssj"] + "</td>";
			html+="<td align='center'>" + data[int]["fz"] + "</td></tr>";
		}
		}
		xwdlObj.children().children(".tbody_rcxw").append(html);
		}, 'json');
	
	
	}

//�ճ���Ϊ��¼
function rcxwjl(){
	var index=4;
	if("1"==jQuery("#zq").val()){
		index=3;
		}
	jQuery("#tab_rcxw").find("tr").each(function(){
		var rcxwjlsj=jQuery(this).find("td").eq(index).text();
		if(jQuery.trim(rcxwjlsj)=="0"){
			jQuery(this).find("td").eq(index-2).attr("colspan","3");
			jQuery(this).find("td").eq(index-1).hide();
			jQuery(this).find("td").eq(index).hide();
			}
		if(jQuery.trim(rcxwjlsj)=="9999999999"){
			var obj=jQuery(this);
			jQuery(this).attr("style","cursor:pointer");
			jQuery(this).find("td").eq(index-2).attr("colspan","3");
			jQuery(this).find("td").eq(index-1).hide();
			jQuery(this).find("td").eq(index).hide();
			jQuery(this).bind("click",function(){
				jQuery(obj).nextAll("tr").each(function(){
					var rcxw=jQuery(this).find("td").eq(index).text();
					if(jQuery.trim(rcxw)!="0"&&jQuery.trim(rcxw)!="9999999999"){
						if(jQuery(this).is(":hidden")){
							jQuery(this).show();
						}else{
							jQuery(this).hide();
						}
					}else{
						return false;
					}
				});
			});
		}
	});	
	jQuery("#zdybdfl_xsxx_query_rcsw_rcxwjl table tr th span").append("<font size='0' color='blue'>&nbsp;&nbsp;&nbsp;�����Ϊ�����С��ɲ鿴��ϸ</font>");
	
	jQuery("#tab_rcxw").find("tr").click();
}

// ��ӡ����
function printZxsxx() {
	jQuery("#dySpan").show();
	var xh = jQuery('#xh').val();
	var url = "xsxx_tygl.do?method=printJsp";
	//url += "&xh=" + xh;
	document.forms[0].action = url;
   	document.forms[0].target = "_blank";
   	document.forms[0].submit();
	document.forms[0].target = "_self";
	//window.open(url);
}
/*¥���ŷ���*/
function ldhFy(){
	var result = jQuery("#shgxzw1").val();
	
}

/*������ҽҩ�����ϴ��鿴*/
function getFjscData(){
	var obj = null;
	var url = "xsxx_xsxxgl.do?method=getFileData";
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'xh='+jQuery("#xh").val(),
			async: false,
			success:function(result){
				if(result==null||result=="null"){
					return false;
				}else{
					obj = result;
				}
			}
			
		});
	   return obj;
	
}

/**
 * ����html
 * @return
 */
function createHtml(jsonlist){
	var html = "";
	html +="<table width='100%' border='0' style='margin-bottom:5px' class='formlist'>";
	html +="<tbody>";
	html +="<tr>";
	html +="<th><div align='center'>��ʱ��</div></th>"
	html +="<th><div align='center'>��������</div></th>"
	html +="<th><div align='center'>�佱��λ</div></th>"
	html +="<th><div align='center'>�����</div></th>"
	html +="<th><div align='center'>�������</div></th>"
	html +="<th><div align='center'>����</div></th>"
	html +="</tr>";
		
	for(var i=0;i<jsonlist.length;i++){
		html +="<tr>";
		html +="<td align='center'>"+jsonlist[i]["hjsj"]+"</td>"
		html +="<td align='center'>"+jsonlist[i]["jxmc"]+"</td>"
		html +="<td align='center'>"+jsonlist[i]["bjdwmc"]+"</td>"
		html +="<td align='center'>"+jsonlist[i]["jxjb"]+"</td>"
		html +="<td align='center'>"+jsonlist[i]["jxlb"]+"</td>"
		var gid = jsonlist[i]["gid"];
		var xh = jsonlist[i]["xh"];
		var hjid = jsonlist[i]["hjid"];
		if(gid == null || gid == "null" || !gid || jQuery.trim(gid) == ""){
			html +="<td align='center'><font color='blue'>��</font></td>";
		}else{
		  html +="<td align='center' ><a style='color:blue' href='javascript:void(0)' onclick='viewUpload(\""
			+ xh + "\",\"" + hjid + "\")'>�鿴</a></td>"
		}
		
		html +="</tr>";
	}
		
	html +="</tbody>";
	html +="</table>";
	return html;
}

function viewUpload(xh,hjid){
		var url = 'xsxx_xsxxxg.do?method=uploadfjsc&hjid=' + hjid
				+ '&xh=' + xh+"&type=view";
		var title = "�񽱸�����Ϣ�鿴";
		showDialog(title, 770, 552, url);
	
}

function showAgeForXakj(){
	var age = calculateAgesForXakj(jQuery("[name='zdybdcon_td_csrq']").text().trim());
	var jgtr = jQuery("[name='zdybdcon_th_jg']").parents("tr").eq(0);
	jgtr.before("<tr><th width='15%'>����</th><td width='35%'>"+age+"</td><th width='15%'></th><td width='35%' colspan='2'></td></tr>");
}

//���ݳ������ڼ�������
function calculateAgesForXakj(str){
	  if(str.indexOf("-") == -1){
		  str = str.substring(0,4)+"-"+str.substring(5,7)+"-"+str.substring(7,8);
	  }
      var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
      if(r==null)return "";     
      var d= new Date(r[1],r[3]-1,r[4]);     
      if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4])   
      {   
            var Y = new Date().getFullYear();   
            return((Y-r[1]));   
      }   
      return("��������ڸ�ʽ����");   
} 

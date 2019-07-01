var gndm = "xsxx_query";// 功能代码
jQuery(function() {
	onShow();
	xsGkPic();
	var xxdm = jQuery("#xxdm").val();
	if (xxdm == "11660") {//重庆工学院
		jQuery("#dySpan").show();
	}else if ("11458" == jQuery("#xxdm").val()) {
		jQuery("td[name='zdybdcon_td_byxx']").parents("tr").hide();
	}else if (xxdm == "10335") {//浙江大学
		var stuImgUrl = "http://api.zju.edu.cn:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + jQuery("#xh").val();
		jQuery("#stuImg").find("img").eq(0).attr("src", stuImgUrl);
		if(jQuery.trim(jQuery("td[name='zdybdcon_td_shgxzw1']").text()).indexOf('否') != -1){
			jQuery("th[name = 'zdybdcon_th_shgxdwdh1']").parent().remove();
		}
	}
	jQuery("#zdybdfl_xsxx_query_xsxx_zwjd").remove();
	jQuery("#zdybdfl_xsxx_query_xsxx_byxx").remove();
	jQuery("#zdybdfl_li_xsxx_query_xsxx dd:has(a[href='#zdybdfl_href_xsxx_query_xsxx_zwjd'])").remove();
	rcxwjl();  //日常行为记录展示
	/*陕西师范大学资助项目个性化*/
	 if(jQuery("#xxdm").val() == '10718'){
		 var zje = jQuery("#zzxmzje").val();
		 var sl = jQuery("#zzxmsl").val();
		 if(sl != 0){
			 jQuery("#zdybdfl_xsxx_query_xszz_zzxmxx").find("table").eq(0).find("th span").append("<font color='008DC2'>&nbsp;&nbsp;&nbsp; 项目数：</font><font color='EA7605'>"+sl+"</font><font color='008DC2'>&nbsp;&nbsp;&nbsp;总金额：</font><font color='EA7605'>"+zje+"</font>");
		 }
	 }
	 if("10125" == jQuery("#xxdm").val()){//山西财经
			if(jQuery("[name='zdybdcon_td_shgxdwdh1']").text().indexOf("否") != "-1"){
				jQuery("[name='zdybdcon_th_shgxgx2']").html("");
				jQuery("[name='zdybdcon_td_shgxgx2']").html("");
			}else{
				jQuery("[name='zdybdcon_th_shgxgx2']").html("残疾证编号");
			}
			jQuery("#zdybdfl_xsxx_query_xsxx_jnzs").children().eq(2)
			.append("<tr><th>证书附件</th><td colspan='3'><input type='hidden' name='zd6' value='' id='fjid'/><div id='commonfileupload-list-q' style='padding: 5px;'></div>" +
					"</td></tr>");
			getfj();
		}
	 /*北京中医药大学个人获奖信息个性化查看*/
	 if(xxdm == '10026'){
		 var obj = getFjscData();
		 if(obj != null && obj != "" && obj != false && obj.size != '0'){
			 jQuery("#zdybdfl_xsxx_query_xsxx_new_grhjqk").find("table").eq(1).remove();
			 var html = createHtml(obj.filedata);
			 jQuery("#zdybdfl_xsxx_query_xsxx_new_grhjqk").append(html);
		 }
		 
		 
	 }
	 
	 if(xxdm == '10704'){
		 showAge();
	 }
});

//华中师范高考照片个性化
function xsGkPic(){
	 if("10511" != jQuery("#xxdm").val()) {
			jQuery("#stuGkImg").css("display", "none");
			}
		    else{
		    	jQuery("#stuGkImg").css("display", "");
			    }
	       
		    jQuery("#gkzpscbtn").css("display", "none");
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
	jsts();//针对导航定位
}

/*
 * 针对导航定位
 */
function jsts() {
	jQuery("a").each(function() {
		var link = jQuery(this);
		var href = link.attr("href");
		if (href && href[0] == "#") {
			var name = href.substring(1);
			jQuery(this).click(function() {
				var nameElement = jQuery("[name='" + name + "']");
				var idElement = jQuery("#" + name);
				var element = null;
				if (nameElement.length > 0) {
					element = nameElement;
				} else if (idElement.length > 0) {
					element = idElement;
				}
				if (element) {
					var offset = element.offset();
					window.parent.scrollTo(offset.left, offset.top + 85);
				}
				return false;
			});
		}
	});
}

//日常行为记录
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
	jQuery("#zdybdfl_xsxx_query_rcsw_rcxwjl table tr th span").append("<font size='0' color='blue'>&nbsp;&nbsp;&nbsp;点击行为大类行、可查看明细</font>");
	
	jQuery("#tab_rcxw").find("tr").click();
}

//打印报表
function printZxsxx() {
	jQuery("#dySpan").show();
	var xh = jQuery('#xh').val();
	var url = "xsxx_tygl.do?method=printJsp";
	url += "&xh=" + xh;
	window.open(url);
}

/*北京中医药附件上传查看*/
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
 * 创建html
 * @return
 */
function createHtml(jsonlist){
	var html = "";
	html +="<table width='100%' border='0' style='margin-bottom:5px' class='formlist'>";
	html +="<tbody>";
	html +="<tr>";
	html +="<th><div align='center'>获奖时间</div></th>"
	html +="<th><div align='center'>奖项名称</div></th>"
	html +="<th><div align='center'>颁奖单位</div></th>"
	html +="<th><div align='center'>奖项级别</div></th>"
	html +="<th><div align='center'>奖项类别</div></th>"
	html +="<th><div align='center'>附件</div></th>"
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
			html +="<td align='center'><font color='blue'>无</font></td>";
		}else{
		  html +="<td align='center' ><a style='color:blue' href='javascript:void(0)' onclick='viewUpload(\""
			+ xh + "\",\"" + hjid + "\")'>查看</a></td>"
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
		var title = "获奖附件信息查看";
		showDialog(title, 770, 552, url);
	
}

//根据出生日期计算年龄
function calculateAges(str)   
{   
    var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
    if(r==null)return "";     
    var d= new Date(r[1],r[3]-1,r[4]);     
    if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4])   
    {   
          var Y = new Date().getFullYear();   
          return((Y-r[1]));   
    }   
    return("输入的日期格式错误！");   
} 

function showAge(){
	var age = calculateAges(jQuery("[name='zdybdcon_td_csrq']").text().trim());
	var jgtr = jQuery("[name='zdybdcon_td_jg']").parents("tr").eq(0);
	jgtr.before("<tr><th width='15%'>年龄</th><td width='35%'>"+age+"</td><th width='15%'></th><td width='35%' colspan='2'></td></tr>");
}

 

var isqy=false;
//初始化
jQuery(document).ready(function(){ 
	if($("pkValue") && $("pkValue").value==""){
		getFFny();
	}
	jQuery("#ffny").bind("change",function(){
//		jQuery("#tsny").text(jQuery(this).val()+"月");
	});
	jQuery("#ffny").change();
	searchRs();
});


//岗位代码名称
function getGwdm(){
	jQuery.ajaxSetup({async:false});
	var parameter ={};
	parameter["xn"]=escape(jQuery("#xn").val());
	parameter["xq"]=escape(jQuery("#xq").val());
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
	jQuery.getJSON('qgzx_cjffsq_ajax.do?method=getGwdm',parameter,function(data){
		jQuery('#gwdm').empty();
		jQuery('#gwdm').append("<option value=''>--------请选择--------</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].gwdm + "\">" + data[i].gwmc + "</option>";
				jQuery('#gwdm').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
}

function autoJsje(tar){
	var gs = jQuery(tar).val();
    var tr = jQuery(tar).closest("tr");
    var col_mxsgz = tr.find("td:eq(7)").text();
	var col_je = tr.find("td:eq(11)").find("input:eq(0)");
	if(gs != ""){
        col_je.val(Number(gs) * col_mxsgz);
	} else {
        col_je.val(0);
	}
}
//获得发放年月
function getFFny(){
	jQuery.ajaxSetup({cache: false});
	jQuery.ajaxSetup({async:false});
	var parameter ={};
	parameter["xn"]=escape(jQuery("#xn").val());	
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
	jQuery.getJSON('qgzx_cjffsq_ajax.do?method=getFFny',parameter,function(data){
		jQuery('#ffny').empty();
		if(data != null && data.length != 0){
			
			for(var i=0; i<data.length; i++){
				var option = "<option value='" + data[i].ffny + "'>" + data[i].ffny + "</option>";
				if(jQuery('#yf').val()==data[i].ffny){
					option = "<option value='" + data[i].ffny + "' selected='selected'>" + data[i].ffny + "</option>";
				
				}
				jQuery('#ffny').append(option);
			}
		}else{
			var option = "<option value=''>--当前部门暂无发放年月--</option>";
			jQuery('#ffny').append(option);
		}
	});
	jQuery.ajaxSetup({async:true});				
}


function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

//查询前验证
function onShow(){
	searchRs();
}
//查询
function searchRs(){
	var url = "qgzx_cjffsq_ajax.do?method=getGwryList";
	url+="&xn="+jQuery("#xn").val();
	url+="&yrbm="+jQuery("#yrbm").val();
	url+="&gwdm="+jQuery("#gwdm").val();
	url+="&zgzt="+jQuery("#zgzt").val();
	url+="&ffny="+jQuery("#ffny").val();
	url+="&xq="+jQuery("#xq").val();
	url+="&sqzt="+jQuery("#sqzt").val();
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000");
    setTimeout("jlffxh()","1000");
    setTimeout("sfkff()","1000");
}
function sfkff(){
	var ffsj=jQuery("#ffny").val();
	ffsj+="-31";
	jQuery("#table_rs").find("tr").each(function(){
		var sgsj=jQuery(this).find("td").eq(5).html();
		if(compareDate(ffsj,sgsj,"-")==2){
			jQuery(this).find("input").attr("disabled","disabled");
			jQuery(this).find("input").val("");
		}
	});
}
function compareDate(aDate,bDate,splitStr){
	if(splitStr==null||""==splitStr){
		splitStr="-";
	}
	var aDates=aDate.split(splitStr);
	//把时间a和b转换为int值进行相减
	var a=aDates[0]+aDates[1]+aDates[2];
	//两者的差值
	var c=parseInt(a)-parseInt(bDate);
	if(c==0){
		return "0";
	}else if(c>0){
		return "1";
	}else if(c<0){
		return "2";
	}
	return "3";
}
//浙江传媒(根据是否困难生，岗位性质来生成自己的酬金标准)
function scCjbz(ind){
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#gwdm_"+ind).val());
    parameter["xh"]=escape(jQuery("#xh_"+ind).val());
    parameter["xn"]=escape(jQuery("#xn").val());
	var url = "qgzx_cjffsq_ajax.do?method=scCjbz";
	jQuery.post(url,parameter,
		function(result){
		jQuery("#cjbz").val(result);
	});
	jQuery.ajaxSetup({async:true});
}
/**
 * 检查金额
 * @return
 */
function checkJe(){
	/**
	 * 如果列表中找不到岗位最高酬金标准输入框，意味着参数设置中没有设置最高酬金标准，直接跳过判断
	 */
	var parameter = {};
	if(jQuery("#table_rs>tbody").find("tr").find("input[name=pzje]").length == 0){
		 parameter = {isok:true,pksyz:true,ptyz:true};
		  return parameter;
	}else{
		var isok=true;
		var flag = jQuery("#xxdm").val() == "10351";
		var je = "";
		var pzje = "";
		var pksyz = true;
		var ptyz = true;
		jQuery("#table_rs>tbody").find("tr").each(function(){
			if(flag && jQuery(this).find("input[name=sfkns]").val() == '困难生'){
				 je = jQuery(this).find("input[name=je]").val();
				 pzje = jQuery("#pkscjzgsx").val();
				 if(parseInt(je)>parseInt(pzje)){
						jQuery(this).find("input[name=je]").attr("style","border:1px solid #FF99CC;");
						isqy=true;
						isok=false;
						pksyz=false;
				 }
			}else{
				 je=jQuery(this).find("input[name=je]").val();
				 pzje=jQuery(this).find("input[name=pzje]").val();
				 if(parseInt(je)>parseInt(pzje)){
					jQuery(this).find("input[name=je]").attr("style","border:1px solid #FF99CC;");
					isqy=true;
					ptyz = false;
					isok=false;
				 }
			}
			
		});
		 parameter = {isok:isok,pksyz:pksyz,ptyz:ptyz};
		return parameter;
	}
}
function autoSetColor(obj){
	if(isqy){
		var je=jQuery(obj).val();
		var pzje=jQuery(obj).parents("tr").find("lable[name=pzje]").text();
		if(parseInt(je)>parseInt(pzje)){
			jQuery(obj).attr("style","border:1px solid #FF99CC;");
		}else{
			jQuery(obj).attr("style","border:1px solid #4D93EA;");
		}
	}
}

//武昌首义个性化
function Jsje(ind,jfhb,zc,gwcjbz){
	var gs = (jQuery("#gs_"+ind).val()*100).toFixed();
	if(""==gs){
		gs=0;
	}
	//计算金额   岗位酬金标准 X 工时
	var cjbz = (gwcjbz*100).toFixed();
	var zje = parseFloat(gs)*parseFloat(cjbz);
	zje = zje/10000;
	if(""==gs){
		zje = "";
	}
	jQuery("#je_"+ind).val(zje);
	//计算 经费划拨金额   经费划拨 X 工时
	var jfhbje = (jfhb*100).toFixed();
	var jfhbzje =parseFloat(gs)*parseFloat(jfhbje);
	
	jfhbzje = jfhbzje/10000;
	if(""==gs){
		jfhbzje = "";
	}
	jQuery("#jfhb_"+ind).val(jfhbzje);
	//计算 自筹金额   自筹 X 工时
	var zcje = (zc*100).toFixed();
	var zczje =parseFloat(gs)*parseFloat(zcje);
	
	zczje = zczje/10000;
	if(""==gs){
		zczje = "";
	}
	jQuery("#zc_"+ind).val(zczje);
	
}
//计算结果
function getResult(ind){
	var gs = (jQuery("#gs_"+ind).val()*100).toFixed();
	
	if(""==gs){
		gs=0;
	}
	var cjbz = (jQuery("#cjbz").val()*100).toFixed();
	/**
	 * 温州大学个性化
	 */
	if(jQuery("#xxdm").val() == "10351" && jQuery("#gs_"+ind).parent().parent().find("input[name='sfkns']").val() == "困难生"){
		cjbz = (jQuery("#pkscjbz").val()*100).toFixed();
	}

	var result = parseFloat(gs)*parseFloat(cjbz);
	
	result = result/10000;
	
	
	
	if(""==gs){
		result = "";
	}
	/**if("0"==result){
		result = "";
	}**/
	//不能大于酬金上限，大于自动设置为最大
	///var pzje=jQuery("#gs_"+ind).parents("tr").find("lable[name=pzje]").text();
	//if(result>=pzje){
	//	result=pzje;
	//}
	jQuery("#je_"+ind).val(result);
}	
//记录已经发放 学号 岗位
function jlffxh(){
	var xh="";
	var gw="";
	var gwid=jQuery("input[name=gwdm]");
	jQuery("input[name=xh]").each(function(i,e){
		var je=jQuery(this).parents("tr").find("td").eq(6).find("input[name=je]").val();
		if(je!=""){
			xh+=jQuery(this).val();
			xh+="!!@@!!";
			gw+=jQuery(gwid[i]).val();
			gw+="!!@@!!";
		}
	});
	jQuery("#yffxh").val(xh);
	jQuery("#yffgw").val(gw);
}
function checkTjzt(){
	var flag = false;
	var tjzt =jQuery("#ffzt").val();
	if("1"==tjzt){
		flag=true;
	}
	return flag;
}
// 保存
function saveCjxxff(){
	jlffxh();
	var xxdm = jQuery("#xxdm").val();
	var ffny = jQuery("#ffny").val();
	/*if("102770"==xxdm){
		if(checkTjzt()){
			showAlertDivLayer("部门"+ffny+"酬金发放已提交，请勿重复提交！");
			return false;
		};
	}*/
	if($("xn") && $("xn").value==""){
 		alertInfo("学年不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("yrbm") && $("yrbm").value==""){
 		alertInfo("用人部门不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("ffny") && $("ffny").value==""){
 		alertInfo("发放年月不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	/*if("12309"==xxdm){
		var cjsx=null;
		var xh = jQuery("input[name=xh]");
		var gwdm = jQuery("input[name=gwdm]");
		var gs = jQuery("input[name=gs]");
		var je = jQuery("input[name=je]");
		var bz = jQuery("input[name=bz]");
		var khdj = jQuery("select[name=khdj]");
		
		var gwcjbz =jQuery("input[name=gwcjbz]");
		var jfhb =jQuery("input[name=jfhb]");
		var zc =jQuery("input[name=zc]");
		var gwcjsx =jQuery("input[name=gwcjsx]");
		
		var xhs = "";
		var gwdms = "";
		var gss = "";
		var jes = "";
		var bzs = "";
		var khdjs="";
		var jfhbs ="";
		var zcs="";
		
		if(xh.length==0){
			alertInfo("该岗位月份无人员发放，无需保存或提交!");
	 		return false;
		}
		//补丁结束
		for(var i = 0;i <xh.length;i++){
			if(jQuery(je[i]).val()!=""){//如果金额字段不为空，去判断  岗位酬金标准X工时 大不大于 岗位月标准
				 if(/!*sxzd=="je"&&*!/parseFloat(jQuery(je[i]).val())>parseFloat(jQuery(gwcjsx[i]).val())){
					alertInfo("学号<font color='red'>["+jQuery(xh[i]).val()+"]</font>发放金额不能大于该岗位每月酬金上限("+jQuery(gwcjsx[i]).val()+"元)!");
		     		return false;
				}
				if(jQuery(gs[i]).val()!=null&&jQuery(gs[i]).val()!=""){
					gss+=jQuery(gs[i]).val()+"!!@@!!";
				}
				if(jQuery(je[i]).val()!=null&&jQuery(je[i]).val()!=""){
					jes+=jQuery(je[i]).val()+"!!@@!!";
				}
				if(jQuery(jfhb[i]).val()!=""){
					jfhbs+=jQuery(jfhb[i]).val()+"!!@@!!";
				}
				if(jQuery(zc[i]).val()!=null&&jQuery(zc[i]).val()!=""){
					zcs+=jQuery(zc[i]).val()+"!!@@!!";
				}
				xhs+=jQuery(xh[i]).val()+"!!@@!!";
				gwdms+=jQuery(gwdm[i]).val()+"!!@@!!";
				
				bzs+=jQuery(bz[i]).val()+"!!@@!!";
				khdjs+=jQuery(khdj[i]).val()+"!!@@!!";
			
				
			}
		}
		var gs = gss.split("!!@@!!"); 
		var jfhb = jfhbs.split("!!@@!!");
		var zc = zcs.split("!!@@!!");
		var je = jes.split("!!@@!!");
		if(gs.length!=jfhb.length||gs.length!=zc.length||gs.length!=je.length){
			alertInfo("工时和金额必须同时发放或者同时不发放!");
			return false;
		}
		if(""==jes){
			alertInfo("该岗位月份无发放金额，无需保存!");
	 		return false;
		}
		//可以为空，打个标记
		gss+="end";
		bzs+="end";
	    jQuery("#xhs").val(xhs);
	    jQuery("#gwdms").val(gwdms);
	    jQuery("#gss").val(gss);
	    jQuery("#jes").val(jes);
	    jQuery("#bzs").val(bzs);
	    jQuery("#khdjs").val(khdjs);
		jQuery("#cjsx").val(cjsx);
		jQuery("#jfhbs").val(jfhbs);
		jQuery("#zcs").val(zcs);
		var message = checkTjInfo();
		if(""!=message){		
			showAlert(message,{},{"clkFun":function(){
					if (parent.window){
						//refershParent();
						return false;
					}
				}});
			return false;
		}
		saveCjffForWCSY();
		
		
	}else{*/
	
	var cjsx=null;
	var xh = jQuery("input[name=xh]");
	var gwdm = jQuery("input[name=gwdm]");
	var gs = jQuery("input[name=gs]");
	var je = jQuery("input[name=je]");
	var bz = jQuery("input[name=bz]");
	var khdj = jQuery("select[name=khdj]");
	var zhdlgs = jQuery("input[name=zhdlgs]");
	var jcdlgs = jQuery("input[name=jcdlgs]");
	var xhs = "";
	var gwdms = "";
	var gss = "";
	var jes = "";
	var bzs = "";
	var zhdlgss = "";
	var jcdlgss = "";
	var khdjs="";
	if(xh.length==0){
		alertInfo("该岗位月份无人员发放，无需保存或提交!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	//酬金上限check，982
	var jsonYzRs = checkJe();
	if(!jsonYzRs["isok"]){
		if(!jsonYzRs["ptyz"]){
			alertInfo("金额不能超过<font color='blue'>岗位酬金上限</font>,请确认");
		}
		if(!jsonYzRs["pksyz"]){
			alertInfo("金额不能超过<font color='blue'>贫困生岗位酬金上限</font>,请确认");
		}
 		return false;
	}else{
		isqy=false;
	}
	//丑陋的补丁 982，下面的代码实在不敢动，打个补丁吧（勤俭节约是美德！）
	var sxzd = jQuery("#sxzd").val();
	for(var i = 0;i <xh.length;i++){
		cjsx=jQuery("#sxsz").val();
		var dyje=0;
		var xhgss=0;
		if(!jQuery(je[i]).val()){
			continue;
		}
		
		/*从参数配置表中去【酬金发放工时显示】，0:不显示,1:显示*/
		var csz = jQuery("#csz").val();
		if("1" == csz){
			for(var j = 0;j <xh.length;j++){
				if(""!=jQuery(je[j]).val()){
					if(""==jQuery(gs[j]).val()){
						alertInfo("工时不能空!");
						return false;
					}
					if(jQuery(xh[i]).val()==jQuery(xh[j]).val()){
	/*					if(sxzd=="je"){
							dyje=parseFloat(dyje)+parseFloat(jQuery(je[j]).val());
						}else if(sxzd=="gs"){
							dyje=dyje+parseFloat(jQuery(gs[i]).val())*parseFloat(jQuery(je[j]).val());
							xhgss=parseFloat(xhgss)+parseFloat(jQuery(gs[i]).val());
						}*/
						dyje=parseFloat(dyje)+parseFloat(jQuery(je[j]).val());
						if(sxzd=="gs"){
							xhgss=parseFloat(xhgss)+parseFloat(jQuery(gs[i]).val());
						}
					}
				}
			}
			if(sxzd=="gs"&&parseFloat(xhgss)>parseFloat(jQuery("#sxsz").val())){
				alertInfo("<font color='red'>["+jQuery(xh[i]).val()+"]</font>发放工时不能大于每月学生工时上限("+jQuery("#sxsz").val()+"小时)!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
			}
			//我们都是补丁 如果设置的是工时，则当前最大酬金上限为 上限乘以工时
			if(sxzd=="gs"){
				cjsx=parseFloat(jQuery("#cjbz").val())*parseFloat(cjsx);
			}
			if(sxzd=="gs" && jQuery("#xxdm").val() == "10351" && jQuery(xh[i]).parent().parent().find("input[name='sfkns']").val() =='困难生'){
				cjsx=parseFloat(jQuery("#pkscjbz").val())*parseFloat(jQuery("#sxsz").val());
			}
		}
		if(parseFloat(dyje)>parseFloat(cjsx) && jQuery("#xxdm").val() != "11647" && jQuery("#xxdm").val() != "10424"){
			var title = "<font color='red'>["+jQuery(xh[i]).val()+"]</font>发放金额不能大于学生每月酬金上限("+cjsx+"元)!"
			if(jQuery("#xxdm").val() == "10351" && jQuery(xh[i]).parent().parent().find("input[name='sfkns']").val() =='困难生'){
				 title = "<font color='red'>["+jQuery(xh[i]).val()+"]</font>发放金额不能大于贫困生每月酬金上限("+cjsx+"元)!"
			}
			alertInfo(title,function(tag){
				if(tag=="ok"){
					return false;
				}
			});
		}
	}
	//补丁结束
	for(var i = 0;i <xh.length;i++){
		/*从参数配置表中去【酬金发放工时显示】，0:不显示,1:显示*/
		var csz = jQuery("#csz").val();
		if(""!=jQuery(je[i]).val() /*&& "0"!=jQuery(je[i]).val()*/){
			if(jQuery("#xxdm").val() != '10351'){
				var sxzd = jQuery("#sxzd").val();
				if("1" == csz){
					if(sxzd=="gs"&&parseFloat(jQuery(gs[i]).val())>parseFloat(jQuery("#sxsz").val())){
						alertInfo("<font color='red'>["+jQuery(xh[i]).val()+"]</font>发放工时不能大于学生每月工时上限("+jQuery("#sxsz").val()+"小时)!",function(tag){
			     			if(tag=="ok"){
			     				return false;
			     			}
			     		});
			     		return false;
					}
				}else if(sxzd=="je"&&parseFloat(jQuery(je[i]).val())>parseFloat(jQuery("#sxsz").val()) && jQuery("#xxdm").val() != "10424"){
					alertInfo("<font color='red'>["+jQuery(xh[i]).val()+"]</font>发放金额不能大于学生每月酬金上限("+jQuery("#sxsz").val()+"元)!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
				}
			}
			xhs+=jQuery(xh[i]).val()+"!!@@!!";
			gwdms+=jQuery(gwdm[i]).val()+"!!@@!!";
			/*当酬金发放工时显示为0时，插入工时为【0】*/
			if("1" == csz){
				gss+=jQuery(gs[i]).val()+"!!@@!!";
			}else{
				gss+="0"+"!!@@!!";
			}
			jes+=jQuery(je[i]).val()+"!!@@!!";
			bzs+=jQuery(bz[i]).val()+"!!@@!!";
			khdjs+=jQuery(khdj[i]).val()+"!!@@!!";
			jcdlgss+=jQuery(jcdlgs[i]).val()+"!!@@!!";
			zhdlgss+=jQuery(zhdlgs[i]).val()+"!!@@!!";
			
			
		}
	}
	if(""==jes){
		alertInfo("该岗位月份无发放金额，无需保存!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	//可以为空，打个标记
	gss+="end";
	bzs+="end";
	jcdlgss+="end";
	zhdlgss+="end";
    jQuery("#xhs").val(xhs);
    jQuery("#gwdms").val(gwdms);
    jQuery("#gss").val(gss);
    jQuery("#jes").val(jes);
    jQuery("#bzs").val(bzs);
    // jQuery("#jcdlgss").val(jcdlgss);
    // jQuery("#zhdlgss").val(zhdlgss);
    // jQuery("#khdjs").val(khdjs);
    /*从参数配置表中去【酬金发放工时显示】，0:不显示,1:显示*/
	var csz = jQuery("#csz").val();
	if("1" == csz){
		 if(sxzd=="gs"){
			cjsx=parseFloat(jQuery("#cjbz").val())*parseFloat(jQuery("#sxsz").val());
		 }
	}
	jQuery("#cjsx").val(cjsx);
	var message = checkTjInfo();
	if(""!=message){		
		showAlert(message,{},{"clkFun":function(){
				if (parent.window){
					//refershParent();
					return false;
				}
			}});
		return false;
	}
	saveCjff();
	// }
}
//保存提交(武昌首义独立出来)
function saveCjffForWCSY(){
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["xq"]=escape(jQuery("#xq").val());
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
    parameter["ffny"]=escape(jQuery("#ffny").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["gwdm"]=escape(jQuery("#gwdms").val());
    parameter["gs"]=escape(jQuery("#gss").val());
    parameter["je"]=escape(jQuery("#jes").val());
    parameter["bz"]=escape(jQuery("#bzs").val());
    parameter["khdj"]=escape(jQuery("#khdjs").val());
    parameter["cjffr"]=escape(jQuery("#cjffr").val());
    parameter["jfhb"]=escape(jQuery("#jfhbs").val());
	parameter["zc"]=escape(jQuery("#zcs").val());
	
    //已经发放过的学生，记录修改前的学生
    parameter["yffxh"]=escape(jQuery("#yffxh").val());
    parameter["yffgw"]=escape(jQuery("#yffgw").val());
    parameter["cjsx"] = escape(jQuery("#cjsx").val());
    parameter["sxzd"] = escape(jQuery("#sxzd").val());
    parameter["sxsz"] = escape(jQuery("#sxsz").val());
	var url = "qgzx_cjffsq_ajax.do?method=saveCjffxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";			
			showAlert(result,{},{"clkFun":function(){
				if("保存成功"!=result){
					return false;
				}
				else if(parent.window){
 					refershParent();
 				}
 			}});
			}
	);
	jQuery.ajaxSetup({async:true});
}
//保存提交
function saveCjff(){
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["xq"]=escape(jQuery("#xq").val());
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
    parameter["ffny"]=escape(jQuery("#ffny").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["gwdm"]=escape(jQuery("#gwdms").val());
    parameter["gs"]=escape(jQuery("#gss").val());
    parameter["je"]=escape(jQuery("#jes").val());
    parameter["bz"]=escape(jQuery("#bzs").val());
    parameter["khdj"]=escape(jQuery("#khdjs").val());
    parameter["cjffr"]=escape(jQuery("#cjffr").val());
    parameter["jcdlgs"]=escape(jQuery("#jcdlgss").val());
    parameter["zhdlgs"]=escape(jQuery("#zhdlgss").val());
    
    //已经发放过的学生，记录修改前的学生
    parameter["yffxh"]=escape(jQuery("#yffxh").val());
    parameter["yffgw"]=escape(jQuery("#yffgw").val());
    parameter["cjsx"] = escape(jQuery("#cjsx").val());
    parameter["sxzd"] = escape(jQuery("#sxzd").val());
    parameter["sxsz"] = escape(jQuery("#sxsz").val());
	var url = "qgzx_cjffsq_ajax.do?method=saveCjffxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";			
			showAlert(result,{},{"clkFun":function(){
				if("保存成功"!=result){
					return false;
				}
				else if(parent.window){
 					refershParent();
 				}
 			}});
			}
	);
	jQuery.ajaxSetup({async:true});
}

//验证提交信息
function checkTjInfo(){
	var xxdm = jQuery("#xxdm").val();
	var data = "";
	var parameter={};
	var url="qgzx_cjffsq_ajax.do?method=checkTjInfo";	
	parameter["xn"]=escape(jQuery("#xn").val());
	parameter["gwdm"]=escape(jQuery("#gwdm").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["ffny"]=escape(jQuery("#ffny").val());
	parameter["je"]=escape(jQuery("#jes").val());
	if("12309"==xxdm){
		parameter["jfhb"]=escape(jQuery("#jfhbs").val());
		parameter["zc"]=escape(jQuery("#zcs").val());
	}
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}

//浙江理工个性化计算总工时

function getZgs(ind){
	
	var jcdlgs = jQuery("#jcdlgs_"+ind).val();  //基础工时	
	var jcdl = jQuery("#jcdl_"+ind).val();		//基础当量
	var zhdlgs = jQuery("#zhdlgs_"+ind).val();	//综合工时
	var zhdl = jQuery("#zhdl_"+ind).val();		//综合当量
	var kndjdl = jQuery("#kndjdl_"+ind).val();	//困难等级当量
	
	jcdlgs = jcdlgs == ""?0:jcdlgs;
	jcdl = jcdl == ""?0:jcdl;
	zhdlgs = zhdlgs == ""?0:zhdlgs;
	zhdl = zhdl == ""?0:zhdl;
	kndjdl = kndjdl == ""?0:kndjdl;
	
	//(基础工时x基础当量+综合工时x综合当量)x困难等级当量
	var result = ((parseFloat(jcdlgs)*parseFloat(jcdl)+parseFloat(zhdlgs)*parseFloat(zhdl))*parseFloat(kndjdl)).toFixed(2);
	
	jQuery("#gs_"+ind).val(result);
	getResult(ind);
}



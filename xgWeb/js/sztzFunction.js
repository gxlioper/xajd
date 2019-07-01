var intV = 0;
var intV2 = 0;
var strV = "";
var count=0;
function MoreDo(doType) {
	var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;
	if(realTable=="sztz_bjxssqb"){
		url = "/xgxt/tzbj_sq.do?doType=";
	}else if(realTable=="sztz_bjxsjyxxb"){
		url = "/xgxt/tzbj_jy.do?doType=";
	}
	if(doType=="add"){		
		url += doType;
		url += "&pkValue=";
		url += "";
		showTopWin(url, w, h);
	}else{
		if (curr_row == null) {
			alert("请选择要操作的行！");
			return false;
		} else {			
			url += doType;
			url += "&pkValue=";
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += "&xh=";
			url += curr_row.cells[0].innerText;
			if(doType=="modi"){
				if(confirm("确定要修改该条记录？")){					
					showTopWin(url, w, h);
				}else{
					return false;
				}
			}else if(doType=="del"){
				if(confirm("确定要删除选择的数据吗？")){
					refreshForm(url);
				}else{
					return false;
				}
			}else{
				showTopWin(url, w, h);
			}		
	}	
  }
}

function MoreCheck(){
    var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;
	if(realTable=="sztz_bjxssqb"){
		url = "/xgxt/tzbj_sh.do";
	}
	url += "?pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += "&doType=viewOne";
	showTopWin(url, w, h);	
}
function sztzDataDoSavePub(url, pkFields) {
	if (allFilled()) {
		if(url.search('doType=save')){
			var kssj = document.getElementById("xmkssj").value;
			var jssj = document.getElementById("xmjssj").value;
			var jzsj = document.getElementById("sqjzrq").value;
			if(kssj >jssj){
				alert("活动开始时间大于活动结束时间！！");
				return false;
			}else if(jzsj>jssj){			
				alert("申请截止时间大于活动结束时间！！");
				return false;
			}
		}
		var eles = pkFields.split("-");
		var valu = "";
		for (i = 0; i < eles.length; i++) {
			valu += document.getElementById(eles[i]).value;
		}
		url = url + valu;
		document.forms[0].action = url;
		document.forms[0].submit();
		Close();
	  window.dialogArguments.document.getElementById("search_go").click();
	}
}

function allFilled() {
	var inp = document.getElementsByTagName("input");
	var sel = document.getElementsByTagName("select");
	var area = document.getElementsByTagName("textarea");
	for (i = 0; i < inp.length; i++) {
		if (inp[i].value == "" || inp[i].value == null) {
			alert("所有选项均不得为空！");
			//alert(inp[i].id);
			return false;
		}
	}
	for (i = 0; i < sel.length; i++) {
		if (sel[i].value == "" || sel[i].value == null) {
			alert("所有选项均不得为空！");
			//alert(sel[i].id);
			return false;
		}
	}
	for (i = 0; i < area.length; i++) {
		if (area[i].value == "" || area[i].value == null) {
			alert("所有选项均不得为空！");
			//alert(area[i].id);
			return false;
		}
	}
	return true;
}
function sztzXfSbAutoChk(url) {
	
	if (document.forms[0].nj.value == "") {
		alert("整批审核将以某年级某"+jQuery("#xbmc").val()+"、专\n业或班级为单位进行通过审核!\n" +
			 "请选择要整批审核的年级！");
		return false;
	} else if (document.forms[0].xy.value == "") {
		alert("请选择要整批审核的"+jQuery("#xbmc").val()+"！");
		return false;
	}	
		var confirmTxt = "整批审核将以";		
		if (document.forms[0].bj.value != "") {
			confirmTxt += "\"班级\"为单位进行审核";
		} else {
			if (document.forms[0].zy.value != "") {
				confirmTxt += "\"专业\"为单位进行审核";
			} else {
				confirmTxt += "\""+jQuery("#xbmc").val()+"\"为单位进行审核";
			}
		}
		confirmTxt += "\n\n整批审核将耗费较长的时间，确定要开始自动审核吗？";
		if (confirm(confirmTxt)) {		
		    alert("点击\"确定\"后开始审核！");		    
		    sztzShowTips('正在进行整批审核，请稍候......');
			refreshForm(url);
			return true;
		} else {
			return false;
		}
}
function xcxyXfSbAutoChk(url) {//西昌学院学分申报自动审核
        if(allSelEmpty()&&document.forms[0].xh.value==""){      		
       		 confirmTxt = "未选择任何条件，此次操作\n\n将对所有素质拓展成果进行整批通过审核";
        }else{
        	 confirmTxt = "将对满足以上条件素质拓展成果进行整批通过审核";
        }
        confirmTxt += "\n\n整批审核将耗费较长的时间，确定要开始自动审核吗？";
		if (confirm(confirmTxt)) {				    
		    sztzShowTips('正在进行整批审核，请稍候......');
			refreshForm(url);
			return true;
		} else {
			return false;
		}
}
function sztzHz(doType){
	var url="/xgxt/sztz_cj_hz.do?act=";
	if(curr_row == null ){
			alert("请选要汇总的学生\n单击一行记录即可！");
			return false;
		} else {
       url+=doType;
	   url+="&xh=";
	   url+=curr_row.cells[0].innerText;
	   url+="&xm=";
	   url+=curr_row.cells[1].innerText;
	   url+="&xb=";
	   url+=curr_row.cells[2].innerText;
	   url+="&nj=";
	   url+=curr_row.cells[3].innerText;
	   url+="&xy=";
	   url+=curr_row.cells[4].innerText;
	   url+="&bj=";
	   url+=curr_row.cells[5].innerText;
	   w=800;
	   h=550;	  
	  showTopWin(url,w,h, 1);
		return true;
		}	  
}

function SztzchkCode(mustFill) {
	var inputV = document.getElementsByName("xmdm");
	if(inputV[0].value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("代码必需为数字！");
		return false;
	}
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	var ksrq = document.getElementById('sqkssj').value.replace(" ","");
	var jsrq = document.getElementById('sqjssj').value.replace(" ","");
	if(ksrq>=jsrq){
		alert('申请开始时间晚于结束时间！');
		return false;
	}
	var my_LyAarry = ["lydm","lymc","lynr"];
	var my_LbAarry = ["lbdm","lbmc","lbxf"];
	for(i=0;i<10;i++){
		var n=i+1;
		if($(my_LyAarry[0]+n).value!=""){
			if($(my_LyAarry[1]+n).value==""){
				alert("第"+n+"个理由名称为空！")
				 if($('ly').style.display=="none"){
				    $('ly').style.display="";
			     }				
				$(my_LyAarry[1]+n).focus();	
				return false;			
				break;
			}
			if($(my_LyAarry[2]+n).value==""){
				alert("第"+n+"个理由内容为空！");	
				 if($('ly').style.display=="none"){
				    $('ly').style.display="";
			     }										
				$(my_LyAarry[2]+n+"").focus();
				return false;
	            break;		
			}
		}
		if($(my_LbAarry[0]+n).value!=""){
			if($(my_LbAarry[1]+n).value==""){
				alert("第"+n+"个类别名称为空！");
			if($('lb').style.display=="none"){
				$('lb').style.display="";
			}								
				$(my_LbAarry[1]+n).focus();	
				return false;			
				break;
			}
			if($(my_LbAarry[2]+n).value==""){
				alert("第"+n+"个类别学分为空！");	
			if($('lb').style.display=="none"){
				$('lb').style.display="";
			}							
				$(my_LbAarry[2]+n+"").focus();
				return false;
	            break;		
			}
		}
	}
	if(confirm("确定提交输入数据？")){
	refreshForm("/xgxt/SavaSztzHdsb.do");
	$("buttonSave").disabled=true;
	}
}

function modiTzhd(url){
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {
		url +=  curr_row.cells[1].innerText;
		if(url.indexOf("del")==-1){
	    	showTopWin(url,"830","600");			
		}else{
		    if(confirm('是否要删除该记录?')){
			refreshForm(url);
			}
		}				
	}	
}
function checkTzhd(url, w, h) {
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {
		url +=  curr_row.cells[1].innerText;		
		showTopWin(url, w, h);	
	}	
}

function sztzHjjb(url){
	var pkValue = "";
	var tmp = document.forms[0].realTable.value;
	url += "?realTable=";
	url += tmp;
	url += "&doType=";
	url += document.forms[0].doType.value;
	url += "&tableName=";
	url += document.forms[0].tableName.value;
	url += "&pk=";
	url += document.forms[0].pk.value;	
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&act=do";
	refreshForm(url);
}
function sztzAllNotEmpThenGo(url) {		
	if (allSelEmpty()) {
		if (confirm("您没有选择任何条件，此次操作将返回全部数据，可能会耗费相当长的时间。确定要继续吗？")){ 
			document.forms[0].go.value = "go";			
			//alert(url);
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	} else {
		document.forms[0].go.value = "go";
		refreshForm(url);
		return true;
	}
}

function sztzShowTips(msg) {
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "hidden";
	}
	
	var dd_html = "";		
	tipsConv = document.createElement("DIV");
	tipsConv.id = "tipsConv";
	tipsConv.oncontextmenu = function(){return false;};
	tipsConv.onSelectstart = function(){return false;};	
	tipsConv.style.cssText = "background-color:#CCCCCC;position:absolute;z-index:100;filter:alpha(opacity=20);";
	tipsConv.style.width = document.body.clientWidth;
	tipsConv.style.height = document.body.scrollHeight;
	tipsConv.style.pixelTop = 0;
	tipsConv.style.left = 0;
	tipsConv.style.display = "block";
	document.body.appendChild(tipsConv);	
	
	dd_html += "<table border=0 cellpadding=0 cellspacing=1 bgcolor=\"#000000\"";
	dd_html += "width=\"100%\" height=\"100%\"><tr>";
	dd_html += "<td bgcolor=#5C8DBE>";
	dd_html += "<marquee align=\"middle\" behavior=\"alternate\" scrollamount=\"2\" style=\"font-size:9pt\">";
	dd_html += "<font color=yellow>"
	dd_html += msg + "</font>";
	dd_html += "</marquee></td></tr></table>";
	tips = document.createElement("DIV");
	tips.id = "tipDiv";
	tips.innerHTML = dd_html;
	tips.style.cssText = "width:200px;height:30px;position:absolute;z-index:100;filter:alpha(opacity=70);";
	tips.style.pixelTop = lastScrollY + 120;
	tips.style.left = (document.body.clientWidth - 200) / 2;
	tips.style.display = "block";
	document.body.appendChild(tips);
	
	tipsF = document.createElement("IFRAME");
	tipsF.style.cssText = "width:200px;height:30px;position:absolute;filter:alpha(opacity=50);";
	tipsF.scr = "javascript:false;";
	tipsF.scrolling = "no";
	tipsF.id = "tipIFrame";
	tipsF.frameborder = "0";
    tipsF.style.width = tips.offsetWidth;
    tipsF.style.height = tips.offsetHeight;
    tipsF.style.top = tips.style.top;
    tipsF.style.left = tips.style.left;
    tipsF.style.zIndex = tips.style.zIndex - 1;
	document.body.appendChild(tipsF);
}
//只能输入数字
function OnlyNumVal(obj){	
	if(event.keyCode >= 48 && event.keyCode <= 57){						
		return true;				
	} else {		
		return false;
	}
   	
}
	
function InputOnlyNum(obj){
	var xmdmVal = document.getElementById("xmdm").value;
	if(xmdmVal==""){
	   		alert("项目代码为空！");
	   		document.forms[0].xmdm.focus();
		    return false;	   
	}else{
		if(event.keyCode >= 48 && event.keyCode <= 57){						
		   return true;		
	    } else {		   	   
		   return false;
	    }
	}
}

function AutoFillVal(obj,tag){
	var xmdmVal = document.getElementById("xmdm").value;//alert(parseFloat(xmdmVal)+"aaaaaa")
	if(xmdmVal==""){
	   		alert("项目代码为空！");
	   		document.forms[0].xmdm.focus();
		    return false;	   
	}else{
//	      lydmMaxValue = "";
//	      lbdmMaxValue = "";
//	      for(i=1;i<10;i++){	      
//	         if(i==1){
//	           lydmMaxValue =$("lydm"+i).value;
//	           lbdmMaxValue =$("lbdm"+i).value;
//	         }	           	         	         	    
//	         if(lydmMaxValue<$("lydm"+(i+1)).value){
//	           lydmMaxValue = $("lydm"+(i+1)).value;	           
//	         }
//	         if(lbdmMaxValue<$("lbdm"+(i+1)).value){
//	            lbdmMaxValue =$("lbdm"+(i+1)).value;
//	         }
//              lydmMaxValue = $("lydm"+i).value;
//              lbdmMaxValue =$("lbdm"+i).value;
//             for(j=2;j<=10;j++){	         	          	         
//                if(lydmMaxValue < $("lydm"+j).value){
//                   lydmMaxValue = $("lydm"+j).value;
//                }
//                if(lbdmMaxValue<$("lbdm"+(j)).value){
//                   lbdmMaxValue = $("lbdm"+j).value;
//                }
//             }
//	      } 
//	      if(tag=="lydm"&&obj.value==""){
//	      	  intV++;
//	          obj.value = (lydmMaxValue=="")?parseFloat(xmdmVal)+parseFloat(intV):parseFloat(lydmMaxValue)+1;	
//	      }else if(tag=="lbdm"&&obj.value==""){
//	      	  intV2++;
//	          obj.value = (lbdmMaxValue=="")?parseFloat(xmdmVal)+parseFloat(intV2):parseFloat(lbdmMaxValue)+1;	
//	      }
//          for(i=0;i<10;i++){
            var str = "0";
            var objid = obj.id;
            if(objid.indexOf("lydm")!=-1){
               str = obj.id.replace("lydm","");
            }else{
               str = obj.id.replace("lbdm","");
            }
            if(parseInt(str)<10){
                str="0"+str;
            }
            obj.value = xmdmVal+str;
//          }
	}
}

function ChekLydm(obj){
	var val = obj.value;
		getSztzData.getDataEx("sztz_sqsblyb","lydm",val,function(str){
		         if(str!=""){
		         	//var tagarr = new Aarry[10];
		            alert("代码库中已经存在理由代码\""+str+"\"，不能录入相同的代码！");
		            obj.focus();
			        return false;
		         }
		});
		
}
function ChekLbdm(obj){
	var val = obj.value;
		getSztzData.getDataEx("sztz_hjjbdmb","jbdm",val,function(str){
		         if(str!=""){
		         	//var tagarr = new Aarry[10];
		            alert("代码库中已经存在获奖类别代码\""+str+"\"，不能录入相同的代码！");
		            obj.focus();
			        return false;
		         }
		});
}

function ChekXmdm(obj){
	var val = obj.value;
	    getSztzData.getDataEx("sztz_xmdmb","xmdm",val,function(str){//判断项目代码表中是否存在该代码
		         if(str!=""){
			        alert("代码库中已经存在项目代码\""+str+"\"，不能录入相同的代码！");
		            obj.focus();
			        return false;
		         }else {
		         	getSztzData.getDataEx("sztz_xmsbxxb","xmdm",val,function(str){//判断项目申报表中该代码是否被占用
		         if(str!=""){
		         	strV = str;
		            alert("该项目代码\""+str+"\"已被申报，请重新输入代码！");
		            obj.focus();
			        return false;
		         }
		         });	
		         }
		       });		
				 
}

function saveSztzParaSet(sqB, sqE, xn, nd, url){
	var a1 = document.all(sqB + "1").value;
	var a2 = (document.all(sqB + "2").value != "" && document.all(sqB + "2").value.length != 2) ? ("0" + document.all(sqB + "2").value) : document.all(sqB + "2").value;
	var a3 = (document.all(sqB + "3").value != "" && document.all(sqB + "3").value.length != 2) ? ("0" + document.all(sqB + "3").value) : document.all(sqB + "3").value;
	var a4 = (document.all(sqB + "4").value != "" && document.all(sqB + "4").value.length != 2) ? ("0" + document.all(sqB + "4").value) : document.all(sqB + "4").value;
	var b1 = document.all(sqE + "1").value;
	var b2 = (document.all(sqE + "2").value != "" && document.all(sqE + "2").value.length != 2) ? ("0" + document.all(sqE + "2").value) : document.all(sqE + "2").value;
	var b3 = (document.all(sqE + "3").value != "" && document.all(sqE + "3").value.length != 2) ? ("0" + document.all(sqE + "3").value) : document.all(sqE + "3").value;
	var b4 = (document.all(sqE + "4").value != "" && document.all(sqE + "4").value.length != 2) ? ("0" + document.all(sqE + "4").value) : document.all(sqE + "4").value;
	a1 = (a1 == "") ? "1900-01-01" : a1;
	a2 = (a2 == "" || isNaN(parseInt(a2)) || parseInt(a2) > 24 || parseInt(a2) < 0) ? "00" : a2;
	a3 = (a3 == "" || isNaN(parseInt(a3)) || parseInt(a3) > 60 || parseInt(a3) < 0) ? "00" : a3;
	a4 = (a4 == "" || isNaN(parseInt(a4)) || parseInt(a4) > 60 || parseInt(a4) < 0) ? "00" : a4;
	b1 = (b1 == "") ? "1900-01-01" : b1;
	b2 = (b2 == "" || isNaN(parseInt(b2)) || parseInt(b2) > 24 || parseInt(b2) < 0) ? "00" : b2;
	b3 = (b3 == "" || isNaN(parseInt(b3)) || parseInt(b3) > 60 || parseInt(b3) < 0) ? "00" : b3;
	b4 = (b4 == "" || isNaN(parseInt(b4)) || parseInt(b4) > 60 || parseInt(b4) < 0) ? "00" : b4;
	var a = a1 + a2 + a3 + a4;
	var b = b1 + b2 + b3 + b4;
	if (a > b) {
		alert("开始时间晚于结束时间，无法保存！");
		return false;
	} else {
		document.getElementById(sqB).value = replaceChar(a, "-", "");
		document.getElementById(sqE).value = replaceChar(b, "-", "");
		if($('xn')&&$('nd')){
		   if (checkXnNd(xn, nd)) {
			   if (url != null) {
				  refreshForm(url);
			   }
			   return true;
		    } else {
			   return false;
	        }
		}else{
			 if (url != null) {
				  refreshForm(url);
			   }
		}
	}
}
function sztzSavePub(url, mustFill) {	
	var kssj = document.getElementById("kssj").value;
	var jssj = document.getElementById("jssj").value;
	var pkV = document.getElementById("pkValue").value;
	var xn = document.getElementById("xn").value;
	var xq = document.getElementById("xq").value;
	var dm = document.getElementById("dm").value;
	var tem = xn+xq+dm;
	if(kssj >jssj){
		alert("结业时间早于开课时间！！");
		return false;
		}
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		  if (document.getElementById(eles[i]).value == "") {
			  alert("请将带\"*\"号的项目输入完整！");
			  return false;
		  }
	}
	if(pkV!=""){
        if(pkV==tem){
		     document.forms[0].action = url;
	         document.forms[0].submit();  	
        }else{
        	getSztzData.getDataEx("sztz_bjfbb","xn||xq||dm",tem,function(str){
		         if(str!=""){		         	
		            alert("已经存在该学年、该学期该班级信息！");		          
			        return false;
		         }else{
		            document.forms[0].action = url;
	                document.forms[0].submit();
		         }
    	      });
        }
	}else{
		getSztzData.getDataEx("sztz_bjfbb","xn||xq||dm",tem,function(str){
		         if(str!=""){		         	
		            alert("该学年、学期该班级信息已发布，不能再发布！");		          
			        return false;
		         }else{
		            document.forms[0].action = url;
	                document.forms[0].submit();
		         }
    	});		
	}
}

function domoreInfo(url,doType,w,h){
	var tmp = document.forms[0].realTable.value;
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}
    if(doType=="add"){
    	showTopWin(url, w, h);	
    }else if(doType=="del"){
    	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
    	}else{
    		if (confirm("确定要删除选择的数据吗？")) {
    			url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				url += "&doType=";
				url += doType;
				refreshForm(url);
    		}else{
    			return false;
    		}
    	}
    }else if(doType=="view"){
		url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += "&doType=";
		url += doType;
		showTopWin(url, w, h);	
	}else if(doType=="modi"){
		if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
		}else{
			if(confirm("确定要修改该条记录?")){
				url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		        url += "&doType=";
		        url += doType;	
		        showTopWin(url, w, h);	
			}else{
			    return false;	
			}
	    }	
	} 
}
function toSave(url,mustFill) {
	var pkV = document.getElementById("pkValue").value;
	var xh = document.getElementById("xh").value;
	var xn = document.getElementById("xn").value;
	var xq = document.getElementById("xq").value;
	var dm = document.getElementById("dm").value;
	var tem = xh+xn+xq+dm;
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
//	if(pkV!=""){
//        if(pkV==tem){
//		     document.forms[0].action = url;
//	         document.forms[0].submit();  	
//        }else{
//        	getSztzData.getDataEx("sztz_bjxssqb","xn||xq||dm",tem,function(str){
//		         if(str!=""){		         	
//		            alert("已经存在该学年、该学期该班级信息！");		          
//			        return false;
//		         }else{
//		            document.forms[0].action = url;
//	                document.forms[0].submit();
//		         }
//    	      });
//        }
//	}else{
		getSztzData.getInfoEx("sztz_bjxssqb","xh||xn||xq||dm",tem,"fdysh='通过'",function(str){
		         if(str){		         	
		            alert("所提交信息已通过审核\n或正在审核中，不能再申请！");		          
			        return false;
		         }else{
		            document.forms[0].action = url;
	                document.forms[0].submit();
		         }
    	});		
//	}

}

function shReadOnly(){
	if(document.forms[0].shType.value=="fdysh"){
		document.forms[0].xyyj.readOnly = true;
		document.forms[0].xxyj.readOnly = true;
		};
	if(document.forms[0].shType.value=="xysh"){
		document.forms[0].fdyyj.readOnly = true;
		document.forms[0].xxyj.readOnly = true;
		};
	if(document.forms[0].shType.value=="xxsh"){
		document.forms[0].xyyj.readOnly = true;
		document.forms[0].fdyyj.readOnly = true;
		
		};	
		
}

function bjDisabled(ele) {
    var userType ="";
    if($("userType")){
       userType = $("userType").value;
    }
	if (userType == "stu"||userType=="student") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			if(document.getElementById(tmp[i])){
			 document.getElementById(tmp[i]).disabled = true;
			}
		}
	}
}

  function MyMoreDo(doType) {
	var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;	
	var userType = $("userType").value;
	if(realTable=="csmz_tzxmb"){
		url = "/xgxt/csmz_sztz.do?method=tzxm_sb&doType=";		
	}else if(realTable=="csmz_tzcgb"){
	    url = "/xgxt/csmz_sztz.do?method=tzcg_sb&doType=";	   
	}else if(realTable=="csmz_tzcjb"){
	    url = "/xgxt/csmz_sztz.do?method=xxwh_tzcgcj&doType=";
	}
	if(doType=="add"){		
		url += doType;
		url += "&pkValue=";
		url += "";
		showTopWin(url, w, h);	
	}else{
		if (curr_row == null) {
			alert("请选择要操作的行！");
			return false;
		} else {
		  var pkValue  = curr_row.cells[0].getElementsByTagName("input")[0].value;
	      var ret = true;		    			
			url += doType;
			url += "&pkValue=";
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;

			if(doType=="modi"||doType=="view"){
						url += "&xh=";
			url += curr_row.cells[0].innerText;
			    if(realTable=="csmz_tzcgb"||realTable=="csmz_tzcjb"){
				        url += "&xmdm="	
					    url += curr_row.cells[0].getElementsByTagName("input")[0].value.replace(curr_row.cells[0].innerText.replace(" ","").replace(" ",""),"");
				}
			    if(doType=="modi"){
			        if(realTable=="csmz_tzxmb"){//申报项目查询
	                    if(userType=="xx"||userType=="admin"){
		                       if(confirm("确定要修改该条记录？")){				    
					               showTopWin(url, w, h);
				               }else{
					               return false;
				               }
	                    }else{    			        
	                      getSztzData.getInfoEx("csmz_tzxmb","id",pkValue,"xmsbr='"+$("userName").value+"'",function(str){
		                      if(str){		         	
		                            if(confirm("确定要修改该条记录？")){				    
					                    showTopWin(url, w, h);
				                    }else{
					                    return false;
				                    }
		                      }else{		           
		                         alert("只有该项目的申报人或系统管理员，才能进行此操作！");
		           	          	 return false;		        		            
		                      }
    	                   });	
    	                }   	
	                }else{
	                   if(confirm("确定要修改该条记录？")){				    
					          showTopWin(url, w, h);
				           }else{
					          return false;
				           }
			        }				    				    
			    }else{			
			        showTopWin(url, w, h);			        			        
			    }				
			}else if(doType=="del"){
			        if(realTable=="csmz_tzxmb"){//申报项目查询
	                    if(userType=="xx"||userType=="admin"){
		                       if(confirm("确定要删除选择的数据吗？")){
					               refreshForm(url);
				               }else{
					               return false;
				               }
	                    }else{    
	                        getSztzData.getInfoEx("csmz_tzxmb","id",pkValue,"xmsbr='"+$("userName").value+"'",function(str){
		                      if(str){		         	
		                           if(confirm("确定要删除选择的数据吗？")){
					                  refreshForm(url);
				                   }else{
					                  return false;
				                   }
		                      }else{		           
		                         alert("只有该项目的申报人或系统管理员，才能进行此操作！");
		           	          	 return false;		        		            
		                      }
		                      
    	                      });
    	                }		
	                }else{
		                if(confirm("确定要删除选择的数据吗？")){
					         refreshForm(url);
				        }else{
					        return false;
				        }
			        }
				
			}else{
				showTopWin(url,w,h);
			}		
	  }	
   }
}
function wxsChekXh(obj){
	var val = obj.value.replace(" ","");
		getSztzData.getDataEx("wxs_xszsxxb","xh",val,function(str){
		         if(str!=""){		         	
		            alert("已存在该学号:\""+str+"\"，不能录入相同的学号！");
		            obj.focus();
			        return false;
		         }
		});
		
}

function changerecord(str,url){
   
	var index = window.dialogArguments.curr_row.rowIndex;
	var length = window.dialogArguments.rsTable.rows.length;
	var obj;
	var view = '';
	var select;
	if(str == 'up' && index >1){
    	obj = window.dialogArguments.rsTable.rows[index-1];
    }else if(str == 'next' && index <length-1){
    	obj = window.dialogArguments.rsTable.rows[index+1];
    }else{
    	return false;
    }    
	window.dialogArguments.curr_row.style.backgroundColor = "FFFFFF";
    window.dialogArguments.curr_row = obj;
    window.dialogArguments.curr_row.style.backgroundColor = '#ffdead';
    index = window.dialogArguments.curr_row.rowIndex;    
    if(index == '1'){
    	view = 'up';
    }else if (index == length-1){
    	view = 'next';
    }
    var line = obj.cells[0].getElementsByTagName('input')[0];
    if(view == ''){
    	select = obj.checked;
    }        
    refreshForm(url+'&pkValue='+line.value+'&view='+view+'&select='+select+'&sel='+line.checked); 
     
}
function shot(obj){
	window.dialogArguments.curr_row.cells[0].getElementsByTagName('input')[0].checked = obj.checked;
}
function initCheck(){ 
    var index = window.dialogArguments.curr_row.rowIndex;
	var length = window.dialogArguments.rsTable.rows.length;
	if(index==1){
	   document.getElementById("up").disabled=true;
	}
	if(index==length-1){
	   document.getElementById("next").disabled=true;
	} 
	if(index==1&&index==length-1){
	   document.getElementById("up").disabled=true;
	   document.getElementById("next").disabled=true;
	}
	document.getElementById("selected").checked=window.dialogArguments.curr_row.cells[0].getElementsByTagName('input')[0].checked;
}


function showHide(str){
      var disp = document.getElementById(str).style.display;      
      document.getElementById(str).style.display=(document.getElementById(str).style.display=="none")?"":"none";	 	
}

function add(the_tab){
    var len = document.getElementById(the_tab).rows.length;
    count=len; 
	var cellfu =[
	function(data){
	return count;
	},
	function(data){	    
	return "<input type='text' style='width:100px' name='xmmc/"+the_tab+"/"+count+"' maxlength='50'>";
    },	
	function(data){	    
	return "<input type='text' style='width:90px' name='cj/"+the_tab+"/"+count+"'  maxlength='5'>";
    },
    function(data){
    return "<select name='js/"+the_tab+"/"+count+"'><option value='组织'>组织</option><option value='参与'>参与</option></select>"
    },
    function(data){	    
	return "<select name='jb/"+the_tab+"/"+count+"'><option value='班'>班</option><option value='院'>院</option><option value='校'>校</option><option value='市'>市</option><option value=''>省</option><option value='国家'>国家</option></select>";
    },
    function(data){	    
	return "<input style='width:150px' type='text' name='cjsj/"+the_tab+"/"+count+"' maxlength='10'>";
    }	
	];	
   DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});     
}
function del(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==1){
       return false;
    }
    if(confirm("确定要删除行？")){      
       tabobj.deleteRow(tabobj.rows.length-1);
    }
}
function infoSave(){
    //var kmdmstr = new Array(); 
    var kmdmstr = $("kmdmstr").value.split("/");     
    //var kmmcstr = new Array();
    var kmmcstr = $("kmmcstr").value.split("/");
    var kmdm ="";
    var kmcm = "";
    var rowLen ="";
    var hdmc ="";
    var cj ="";
    var js = "";
    var jb ="";
    var cjsj = "";
    var xh = "";
    var xn = "";
    var xq = "";
    
    if($("xh")){
       xh = $("xh").value;
       if(xh==""){
           alert("学号不能为空！");
           return false;
       }
    }
    if($("xn")){
       xn = $("xn").value; 
       if(xn==""){
           alert("学年不能为空！");
           return false;
       }
    }
    if($("xq")){
       xq = $("xq").value;
       if(xq==""){
           alert("学期不能为空！");
           return false;
       }
    }
    var pkValue = xn+xq+xh;
    var nullCout = 0;
    for(i=0;i<kmdmstr.length-1;i++){
        kmdm = kmdmstr[i];
        kmmc = kmmcstr[i];
        rowLen = document.getElementById(kmdm).rows.length-1;   
        if(rowLen==0){
           nullCout++;                   
        }
        for(j=1;j<=rowLen;j++){
            hdmc = document.getElementById(kmdm).rows[j].cells[1].getElementsByTagName('input')[0].value;
            cj   = document.getElementById(kmdm).rows[j].cells[2].getElementsByTagName('input')[0].value;
            js   = document.getElementById(kmdm).rows[j].cells[3].getElementsByTagName('select')[0].value;
            jb   = document.getElementById(kmdm).rows[j].cells[4].getElementsByTagName('select')[0].value;
            cjsj = document.getElementById(kmdm).rows[j].cells[5].getElementsByTagName('input')[0].value;
            
            if(hdmc==""){
                alert("“"+kmmc+"”栏中第"+j+"行‘活动名称’为空！");
                return false;
            }
            if(cj==""){
                alert("“"+kmmc+"”栏中第"+j+"行‘成绩’为空！");
               return false;      
            }
            if(cjsj==""){
                alert("“"+kmmc+"”栏中第"+j+"行‘时间’为空！");
               return false;     
            }                
        }
    }
 
    if(nullCout==kmdmstr.length-1){      
           alert("请填写参加拓展活动！");
           return false;           
    }
    getSztzData.getInfoEx("zgkd_tzcjb ","xn||xq||xh",pkValue," (xysh='通过' or xxsh='通过') ",function(str){
		  if(str){                  
		      alert("该学年、学期、该生的个人拓展信息\n\n已通过上级部门审核或正在审核中，\n\n不能再操作！");		          
			  return false;
		  }else{
              refreshForm('/xgxt/zgkydx_sztz.do?method=infoSave');
              if($("buttonSave")){
                 $("buttonSave").disabled=true;
              }
		  }
    });	        
}
 
function getYhSzbmInfo(){
    var yhm = ""
    if($("xmsbr1")){
       yhm = $("xmsbr1").value;
    }else if($("xmsbr")){
       yhm = $("xmsbr").value
    }
	getSztzData.getyhbmmc(yhm,function(data){
       if(data!=null){
        $('yhssbm').innerText=data;      	
       }
    });
}



function showClearPoint() {
showMessage('showDiv',true,'#C7DEFC')
}	
function closeClearPoint() {  
    $("kfbtnSave").disabled=true;
    $("kfbtnClose").disabled=true;
	refreshForm('/xgxt/csmz_sztz.do?method=plCheck&check=no');
}
function savePoint(str) {  
     var kcxf =$("kcfv").value;
     if(kcxf.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		   alert("扣分值需为数字！");
		   return false;
	 }
    $("kfbtnSave").disabled=true;
    $("kfbtnClose").disabled=true;
	refreshForm('/xgxt/csmz_sztz.do?method=plCheck&check=no&kcxf='+kcxf);
}

//function showDiv3(d_html, w, h) {
//	i = document.getElementsByTagName("select").length;
//	for (j = 0; j < i; j++) {
//		document.getElementsByTagName("select")[j].style.visibility = "hidden";
//	}
//	var d_width = document.body.clientWidth;
//	var d_height = document.body.clientHeight;
//	var d_left = 0;
//	var d_top = 0;
//	var d_color = "#EEF4F9";
//	var d_width_top = w;
//	var d_height_top = h;
//	var d_left_top = (d_width - d_width_top) / 2;
//	var d_top_top = (d_height - d_height_top) / 2;
//	var d_color_top = "#EEF4F9";
//	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
//	dd_html += "</div>";
//	dd_html += "<div styleId='moveid' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
//	dd_html += "<br>";
//	dd_html += d_html;
//	dd_html += "<br>";
//	dd_html += "</div>";
//	tmpdiv.innerHTML = dd_html;
//	window.setInterval("moveposition('tmpdiv')",3000);
//}

//function initBTNTool(obj) {
//	var diffY = document.body.scrollTop;
//	var percent = 0.1 * (diffY - lastScrollY);
//	if (percent > 0) {
//		percent = Math.ceil(percent);
//	} else {
//		percent = Math.floor(percent);
//	}
//	$(obj).style.width = document.body.clientWidth - 2;
//	if($("tipDiv")){
//		$("tipDiv").style.pixelTop += percent;
//	}
//	if($("tipIFrame")){
//		$("tipIFrame").style.pixelTop += percent;
//	}	
//	if($("tipsConv")){
//		$("tipsConv").style.height = document.body.scrollHeight;
//	}
//	
//	$(obj).style.pixelTop += percent;
//	lastScrollY = lastScrollY + percent;
//}

//if($("btn") && !window.dialogArguments){
//		$("btn").style.pixelTop = document.body.offsetHeight - $("btn").offsetHeight;
//		$("btn").style.width = document.body.clientWidth - 2;
//		$("btn").style.left = "1";
//		window.setInterval("initBTNTool('btn')",1);	
//}
//  function moveposition(obj){
//  alert($(obj));
//  	$(obj).style.top =2000;
//  	var diffY = document.body.scrollTop;
//	var percent = 0.1 * (diffY - lastScrollY);
//	if (percent > 0) {
//		percent = Math.ceil(percent);
//	} else {
//		percent = Math.floor(percent);
//	}
//	$(obj).style.width = document.body.clientWidth - 2;	
//	$(obj).style.pixelTop += percent;
//	lastScrollY = lastScrollY + percent;
//  }


function initXhList(){
        var bjdm = "";
        if($("bjdm")){bjdm = $("bjdm").value};
		getSztzData.getXsList(bjdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "xmsbr";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");								
				}
			}
		});
}
function initYhList(){
        var xydm = "";
        if($("xydm")){xydm = $("xydm").value};
		getSztzData.getYhList(xydm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "xmsbr1";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");								
				}
			}
		});
}
//验证数据格式是否是数字
function ckinpdata(obj){
		if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) ){
			alert('数据格式不正确，只能是数字和小数点！');
			obj.value = '';
			return false;
		}
		return true;
}






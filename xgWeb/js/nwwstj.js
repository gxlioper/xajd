function showAytj(w,h){
 		var url = "/xgxt/nwwsAytj.do";
 		//var info = window.showModalDialog(url,null,"Status:YES;dialogWidth:" + w + "px;dialogHeight:" + h + "px;help:no;");
 		var w = "400" ;
        var h = "250" 
 		showOpenWindow(url, w, h);
}
	
function showAztj(w,h){
 		var url = "/xgxt/nwwsAztj.do";
 		var w = "400" ;
        var h = "250" 
 		showOpenWindow(url, w, h);
 		//var info = window.showModalDialog(url,null,"Status:YES;dialogWidth:" +w + "px;dialogHeight:" + h + "px;help:no;");
	}
function showZXFDYTj(){
    var url = "/xgxt/whlghxxy_Gygl.do?method=showzxfdyTj";
    var w = "400" ;
    var h = "250" ;
    showOpenWindow(url, w, h);  
}
function toZXFDYTj(){
    var nj = $("nj").value;
    var zs = document.forms[0].zs.options[document.forms[0].zs.selectedIndex].text;
    var xn = $("xn").value;
    var xq = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
    if(confirm("确定要统计 "+xn+"学年 "+xq+"学期 "+zs+" "+nj+"年级\n学生寝室内务卫生情况？")){
      document.forms[0].action = "/xgxt/whlghxxy_Gygl.do?method=zxfdyTj";
	  document.forms[0].target = "_blank";
	  document.forms[0].submit();    
    }
}
function showZXTj(){
    var url = "/xgxt/whlghxxy_Gygl.do?method=showzxTj";
    var w = "400" ;
    var h = "250" 
    showOpenWindow(url, w, h);
    ///showTopWin(url, w, h);  
}
function toZXTj(){
    var zs = document.forms[0].zs.options[document.forms[0].zs.selectedIndex].text;
    var xn = document.forms[0].xn.options[document.forms[0].xn.selectedIndex].text;
    var xq = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
    if(confirm("确定要统计 "+xn+"学年"+xq+"学期"+zs+"内学生寝室内务卫生情况？")){
      document.forms[0].action = "/xgxt/whlghxxy_Gygl.do?method=zxTj";
	  document.forms[0].target = "_blank";
	  document.forms[0].submit();    
    }
}
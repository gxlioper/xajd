//学生违纪处分数据查询
function wjcfInfoBylxck(){
	var xh = document.getElementById("xh").value;
        var xm = document.getElementById("xm").value;
        var tabType = document.getElementById('tabType').value;
        var pkValue = document.getElementById("pkValue").value;
        var url = "/xgxt/wjcf_info_lxck.do?xh=";
            url += xh;
            url += "&xm=";
            url += xm;
            url += "&pkValue=";
            url += pkValue;
            url += "&tabType=";
            url += tabType;
        if(xh == ""){
            alert("学号为空！");
            return false;
        }else{      
            showTopWin(url,750,550);
            return true;
      }
}

//撤消处分打印
function cxcfprint(url){
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += '&xh=';
	url += curr_row.cells[0].getElementsByTagName("input")[1].value;
	refreshForm(url);
}
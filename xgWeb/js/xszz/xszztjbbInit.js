
var disEle = ["xnTr","ndTr","xqTr","njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
var nullEle = ["xn", "nd", "xq", "nj", "xy", "zy", "bj"];

//将页面的字段都设置为不可见
function disabledElement(){
	for(var i=0; i<disEle.length; i++){
		if(ele(disEle[i])){
			ele(disEle[i]).style.display = "none";
		}
	}
}

//显示对象
function showElement(obj){
	for(var i=0; i<obj.length; i++){
		if(ele(obj[i])){
			ele(obj[i]).style.display = "";
		}
	}
}

//更改报表
function changeBb(value){
	var xxdm = $("xxdm").value;
	disabledElement(disEle);	
	//获取表报要显示的字段
	var btzd = "";
	//中国地大
	if(xxdm == "10491"){
		if(value != ""){
			var sqzq = getSqzq(value);
			if(sqzq == "学年"){
				disEle = ["xnTr","njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
			}else if(sqzq == "年度"){
				disEle = ["ndTr","njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
			}else if(sqzq == "学期"){
				disEle = ["xnTr","xqTr","njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
			}else{
				disEle = ["njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
			}
			showElement(disEle);
		}else{
			disabledElement();
		}
	} else{
		xszzComm.getTjbbZd(value,function(data){
			if(data != null){
				for(var i=0; i<data.length; i++){
					var map = data[i];
					if(map.zd != null && map.zd != ""){
						showElement([map.zd+"Tr"]);
					}
					if(map.sfbt != null && map.sfbt == "1"){
						if(!elementNotNull([map.zd])){
							appendNotNullFlag([map.zd]);
						}
						btzd += map.zd + ",";
					}
					if(map.sfbt != null && map.sfbt == "0"){
						if(elementNotNull([map.zd])){
							cancleNotNullFlag([map.zd]);
						}
					}
				}
			}
			//获取到必填字段
			btzd = btzd + "tjbbdm";
			setVal("btzd", btzd);
		});
	}
}

//打印报表
function printBb(){
	var nullCol = val("btzd").split(",");
	if(!filedNotNull(nullCol)){
		alert("请将带*号的项目填写完整！");
		return false;
	}
	
	var url = "/xgxt/commXszz.do?method=printTjbbManage";

	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
		
}

function getSqzq(xmdm){

	dwr.engine.setAsync(false);
	
	var tableName = "xszz_zzxmb";
	var colList = ["sqzq"];
	var pk = "xmdm";
	var pkValue = xmdm;
	var query = "";
	var sqzq = "";
	getOtherData.getTableInfo(tableName, colList, pk, pkValue,query,function(data){
		if( data != null && data.length > 0){
			sqzq = data[0];
		}
	});
	dwr.engine.setAsync(true);
	
	return sqzq;
}
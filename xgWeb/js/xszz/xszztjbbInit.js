
var disEle = ["xnTr","ndTr","xqTr","njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
var nullEle = ["xn", "nd", "xq", "nj", "xy", "zy", "bj"];

//��ҳ����ֶζ�����Ϊ���ɼ�
function disabledElement(){
	for(var i=0; i<disEle.length; i++){
		if(ele(disEle[i])){
			ele(disEle[i]).style.display = "none";
		}
	}
}

//��ʾ����
function showElement(obj){
	for(var i=0; i<obj.length; i++){
		if(ele(obj[i])){
			ele(obj[i]).style.display = "";
		}
	}
}

//���ı���
function changeBb(value){
	var xxdm = $("xxdm").value;
	disabledElement(disEle);	
	//��ȡ��Ҫ��ʾ���ֶ�
	var btzd = "";
	//�й��ش�
	if(xxdm == "10491"){
		if(value != ""){
			var sqzq = getSqzq(value);
			if(sqzq == "ѧ��"){
				disEle = ["xnTr","njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
			}else if(sqzq == "���"){
				disEle = ["ndTr","njTr","xyTr","zyTr","bjTr","xhTr","xmTr"];
			}else if(sqzq == "ѧ��"){
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
			//��ȡ�������ֶ�
			btzd = btzd + "tjbbdm";
			setVal("btzd", btzd);
		});
	}
}

//��ӡ����
function printBb(){
	var nullCol = val("btzd").split(",");
	if(!filedNotNull(nullCol)){
		alert("�뽫��*�ŵ���Ŀ��д������");
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
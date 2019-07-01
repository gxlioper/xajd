//报表连打


//url 格式为路径+pkValue
//pkid 页面复选框的ID
 function zsldprint(url,pkid) {
			var boxes = document.getElementsByName(pkid);
			var canModi = false;
			for(var i = 0; i < boxes.length; i++){
				if(boxes[i].checked){
					canModi = true;
					break;
				}
			}
			if(canModi){
			    BatAlert.MyAlert("确定要做此操作吗？此操作将连续打印选定的学生数据","",function(){
			 	   BatAlert.showTips("数据操作中，请稍候...");
			    	var pk;
					if($("rsTable").rows.length > 1){
						//for(i = 1;i<$("tabPri").rows.length;i++){
							rowOnClick($("rsTable").rows[1]);
							var temp = kshTaoPrint(pkid);
							var tempArray = temp.split(',');
							pk=tempArray[0];
							tempArray.splice(0,1);
							window.open(url+pk+'&pks='+tempArray.join(','));
						
						//}
						BatAlert.closeTips();
					 } else{
					    BatAlert.MyAlert("没有可打印的数据！");
						return false;
					 }
				},true);
				return false;  
			}else{
				BatAlert.MyAlert("此操作需要有选中的复选框，请选择！");
				return false;
			} 
     } 
     
function kshTaoPrint(pkid) {
	var boxes = document.getElementsByName(pkid);
	var modiZxdm = new Array();
	for(var i = 0; i < boxes.length; i++){
		if(boxes[i].checked){
			modiZxdm[modiZxdm.length] = boxes[i].value;
		}
	}
	return modiZxdm.join(",");
}


function Preview() 
{
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  
  document.all.eprint.marginTop=10;
  document.all.eprint.marginLeft=10;
  document.all.eprint.marginRight=5;
  document.all.eprint.marginBottom=5;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  //document.all.eprint.paperSize = "16K";
  document.all.eprint.Preview();//打印预览
}

function Print() {  
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  document.all.eprint.marginTop=10;
  document.all.eprint.marginLeft=10;
  document.all.eprint.marginRight=5;
  document.all.eprint.marginBottom=5;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  //document.all.eprint.pageWidth = 197mm; 
  //document.all.eprint.pageHeight = 293mm;
  //document.all.eprint.paperSize = "16K";
  document.all.eprint.Print(true);//直接打印
}
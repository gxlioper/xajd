//��������


//url ��ʽΪ·��+pkValue
//pkid ҳ�渴ѡ���ID
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
			    BatAlert.MyAlert("ȷ��Ҫ���˲����𣿴˲�����������ӡѡ����ѧ������","",function(){
			 	   BatAlert.showTips("���ݲ����У����Ժ�...");
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
					    BatAlert.MyAlert("û�пɴ�ӡ�����ݣ�");
						return false;
					 }
				},true);
				return false;  
			}else{
				BatAlert.MyAlert("�˲�����Ҫ��ѡ�еĸ�ѡ����ѡ��");
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
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch
  
  document.all.eprint.marginTop=10;
  document.all.eprint.marginLeft=10;
  document.all.eprint.marginRight=5;
  document.all.eprint.marginBottom=5;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  //document.all.eprint.paperSize = "16K";
  document.all.eprint.Preview();//��ӡԤ��
}

function Print() {  
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch
  document.all.eprint.marginTop=10;
  document.all.eprint.marginLeft=10;
  document.all.eprint.marginRight=5;
  document.all.eprint.marginBottom=5;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  //document.all.eprint.pageWidth = 197mm; 
  //document.all.eprint.pageHeight = 293mm;
  //document.all.eprint.paperSize = "16K";
  document.all.eprint.Print(true);//ֱ�Ӵ�ӡ
}
function mvTo(src,tar){
	var source = DWRUtil.byId(src);
	var target = DWRUtil.byId(tar);
	var existFlag = false;
	for(var i=0;i<target.options.length;i++){
		if(existFlag = source.value.toLowerCase()==target.options[i].value.toLowerCase()? true : false)
			break;
	}
	if(existFlag){
		alert("��ѡ����ֶ��ڱ��ر����Ѿ����ڣ�����");
		return false;
	} else{
		moveOpToTargetSel(target,source.options[source.selectedIndex]);
	}
}
function checkInputTime(){
	
	DWRUtil.byId('hh').value = DWRUtil.byId('hh').value.replace(/[^0-9]/g,'');
	DWRUtil.byId('mi').value = DWRUtil.byId('mi').value.replace(/[^0-9]/g,'');
	DWRUtil.byId('ss').value = DWRUtil.byId('ss').value.replace(/[^0-9]/g,'');
	DWRUtil.byId('hour').value = DWRUtil.byId('hour').value.replace(/[^0-9]/g,'');
	DWRUtil.byId('minute').value = DWRUtil.byId('minute').value.replace(/[^0-9]/g,'');
	DWRUtil.byId('day').value = DWRUtil.byId('day').value.replace(/[^0-9]/g,'');
	
	var hh = DWRUtil.byId('hh').value;
	var mi = DWRUtil.byId('mi').value;
	var ss = DWRUtil.byId('ss').value;
	var hour = DWRUtil.byId('hour').value;
	var minute = DWRUtil.byId('minute').value;
	var day = DWRUtil.byId('day').value;
	if(hh.length>2){
		alert("���ͬ����ʼʱ�������еġ�Сʱ�����ȳ�����2��Ӧ��2λ����");
		return false;
	}
	if(mi.length>2){
		alert("���ͬ����ʼʱ�������еġ����ӡ����ȳ�����2��Ӧ��2λ����");
		return false;
	}
	if(ss.length>2){
		alert("���ͬ����ʼʱ�������еġ��롱���ȳ�����2��Ӧ��2λ����");
		return false;
	}
	
	if(day.length>3){
		alert("���ͬ�����ʱ�������еġ����������ȳ�����2��Ӧ��2λ����");
		return false;
	}
	if(hour.length>3){
		alert("���ͬ�����ʱ�������еġ�Сʱ�����ȳ�����2��Ӧ��2λ����");
		return false;
	}
	if(minute.length>3){
		alert("���ͬ�����ʱ�������еġ����ӡ����ȳ�����2��Ӧ��2λ����");
		return false;
	}
	document.forms[0].action='/xgxt/basedata.do?method=saveSynchronizeParams';
	document.forms[0].submit();
}

function getDate(){
	var date = new Date();
	var year = date.getYear().toString();
	var month = (date.getMonth()+1).toString();
	var day = date.getDate().toString();
	var result = year + ((month.length>1)?month:'0'+month) + day;
	return result;
}
//�������ϴ�ѧ�ı������ݽṹ
function saveTableStructure(objName){
	var tableNameStr = DWRUtil.byId(objName).value;
	var localTable ="";
	var remoteTable = "";
	if(tableNameStr.split("!@") && tableNameStr.split("!@").length==2){
		localTable = tableNameStr.split("!@")[0];
		remoteTable = tableNameStr.split("!@")[1];
	}
	var localTableColumns = new Array();
	var localSel = DWRUtil.byId('local').options;
	for(var i=0;i<localSel.length;i++){
		localTableColumns[i]=localSel[i].value;
	}
	getBaseData.saveTableStructure(localTable,remoteTable,localTableColumns,function(data){
		if(data.length>0){
			var wrong = data.join(",");
			alert("�� ["+wrong+"] û�����ӵ��������ݿ⣡������־��");
		} else {
			alert("�������ݽṹ�ɹ�����");
		}
	});
}

//�ֶ�ִ��ͬ������
function synchronizeData(objName){
	var tableNameStr = DWRUtil.byId(objName).value;
	var localTable ="";
	var remoteTable = "";
	var global ;
	if(tableNameStr.split("!@") && tableNameStr.split("!@").length==2){
		localTable = tableNameStr.split("!@")[0];
		remoteTable = tableNameStr.split("!@")[1];
	}
	
	if(localTable !== null && remoteTable !== null ){
		DWRUtil.byId("showMes").style.position="absolute";
		DWRUtil.byId("showMes").style.display="block";
		DWRUtil.byId("showMes").style.height="10px";
		DWRUtil.byId("showMes").style.top=0;
		DWRUtil.byId("showMes").style.right=0;
		global = window.setInterval(function(){//��״̬����Ķ�̬��ʾ������������ʾ
			if(DWRUtil.byId("showMes").innerText.length==15){
				DWRUtil.byId("showMes").innerText=DWRUtil.byId("showMes").innerText.replace(/\./g,'');
			} else {
				DWRUtil.byId("showMes").innerText=DWRUtil.byId("showMes").innerText+".";
			}
		},1000);
		
		if($("webSerTb")){
			var data =getBaseData.synchronizeTableDataForZFweb(tableNameStr.toLowerCase(),function(data){
			if(data == true){
				alert("success");
				window.clearInterval(global);
				DWRUtil.byId("showMes").style.display="none";
			}else {
				alert("false");
				window.clearInterval(global);
				DWRUtil.byId("showMes").style.display="none";
			}
			});
		}else{
		var data =getBaseData.synchronizeTableData(localTable.toLowerCase(),remoteTable.toLowerCase(),function(data){
			if(data == true){
				alert("success");
				window.clearInterval(global);
				DWRUtil.byId("showMes").style.display="none";
			}else {
				alert("false");
				window.clearInterval(global);
				DWRUtil.byId("showMes").style.display="none";
			}
		});
		}	
	}
}
function initJxjList(){
	var jxjfl = document.getElementById("jxjfl").value;
	getJxjList.getJxjListByJxjfl(jxjfl,function(data){
		if(data!=null && typeof data == 'object'){			
			DWRUtil.removeAllOptions("jxjdm");
			DWRUtil.addOptions("jxjdm",data,"jxjdm","jxjmc");	
		}
	});
}


function initItem(){
	var sdfs = document.getElementById("sdfs").value;
	if(sdfs=="����"){
		document.getElementById("rs").style.display='';
		document.getElementById("jyrsbl").style.display='none';
	}else if(sdfs=="��������"){
		document.getElementById("rs").style.display='none';
		document.getElementById("jyrsbl").style.display='';
	}	
}

function initMode(){
	if($("sdfs")){
		var jxjrs = document.getElementById("jxjrs").value;
		var jyrs = document.getElementById("jyrs").value;
		if(jyrs!="" && jyrs!=null && parseInt(jyrs)!=0){
			document.getElementById("jyrsbl").style.display='';
			document.getElementById("rs").style.display='none';
			document.getElementById("sdfs").value="��������";
		}else{
			document.getElementById("jyrsbl").style.display='none';
			document.getElementById("rs").style.display='';
			document.getElementById("sdfs").value="����";
		}
	}
}

function timeSave(){
	if($('jyrs')&&$('jyrsbl').style.display==''){
		if($('jyrs').value > 100 || $('jyrs').value == ''){
			alert('�����������������ϱ�׼��');
			return false;
		}else{
			if(checkTime('xssqkssj','xssqjssj','shkssj','shjzdj') && confirm('ȷ��Ҫ������')){
				document.forms[0].submit();
				return true;
			}
			return false;
		}
	}else{
		if(checkTime('xssqkssj','xssqjssj','shkssj','shjzdj') && confirm('ȷ��Ҫ������')){
			document.forms[0].submit();
			return true;
		}
		return false;
	}
}


function viewFpb(){
	var xydm = document.getElementById("xydm").value;
	var url ='pjpy_whlgdx.do?method=viewFpb';
	if(xydm=="all" || xydm=="" ||xydm==null){
		alert("��ѡ��"+jQuery("#xbmc").val()+"!");
		return false;
	}
	url += '&xydm=' + xydm;
	showTopWin(url,700,600);
}

function initXmflList(){
	var xmdm = document.getElementById("xmdm").value;
	getJxjList.getXmflList(xmdm,function(data){
		if(data!=null && typeof data == 'object'){			
			DWRUtil.removeAllOptions("jxjfl");
			DWRUtil.addOptions("jxjfl",data,"jxjfldm","jxjflmc");	
			initXmList();
		}
	});
}

function initXmList(){
	var xmdm = document.getElementById("xmdm").value;
	var jxjfl = document.getElementById("jxjfl").value;
	getJxjList.getXmList(xmdm,jxjfl,function(data){
		if(data!=null && typeof data == 'object'){			
			DWRUtil.removeAllOptions("jxjdm");
			DWRUtil.addOptions("jxjdm",data,"jxjdm","jxjmc");	
		}
	});
}

function loadJxjje(obj){
	var jxjdm = obj.value;
	if(jxjdm!=null){
		getJxjList.getJxjInfo(jxjdm,function(data){
			if(data!=null){
				var jlje = data.jlje;		
				if(data.jlje!=undefined){
					document.getElementById("jlje").value=jlje;
				}else{
					document.getElementById("jlje").value="";
				}
			}
		});
	}
}

function initJxjdmList(tableName){
	var jxjfl = document.getElementById("jxjfl").value;
	getJxjList.getXmList(tableName,jxjfl,function(data){
		if(data!=null && typeof data == 'object'){			
			DWRUtil.removeAllOptions("jxjdm");
			DWRUtil.addOptions("jxjdm",data,"jxjdm","jxjmc");	
		}
	});
}

function showHiddenColum(obj){
var name = obj.options[obj.selectedIndex].text;
if(name!=null && name == "ѧ�����н�"){
	document.getElementById("lwqk").style.display='';
	document.getElementById("sjzz").style.display='';
}

if(name!=null && name == "������ѧ��"){
	document.getElementById("gk").style.display='';
}

if(name!="ѧ�����н�"){
	document.getElementById("lwqk").style.display='none';
	document.getElementById("sjzz").style.display='none';
}
if(name!="������ѧ��"){
	document.getElementById("gk").style.display='none';
}
}

//��ѧ�����뱣��
function jxjCommit(filed){
var value = filed.split("-");
var xh = document.getElementById("xh").value;
var jxjdm = document.getElementById("jxjdm").value;
var jxjmc = document.getElementById("jxjdm").options[document.getElementById("jxjdm").selectedIndex].text;
for(var i=0; i<value.length; i++){
	if(document.getElementById(value[i]).value==""){
		alert("�뽫��\*�ŵ���Ŀ��д������");
		return false;
	}
}

whlgPjpy.checkDemand(xh,jxjdm,"jxjdmb",function(data){	
	if(jxjmc=="������ѧ��" || jxjmc=="������"){
		whlgPjpy.checkIsNewStu(xh,function(date){
		if(date==false){			
			alert("�������޷�����"+jxjmc+ "!");
			return false;
		}else{
			if(data!=null && data!=" "){
				whlgPjpy.getXmfl("jxjdmb",jxjdm,function(data){			
					if(data=="ѧУ��ѧ��" || data=="���ҽ�ѧ��" || data=="ѧУ�����ҽ�ѧ��" || data=="���ҡ�ѧУ��ѧ��"){
						alert("�㲻����"+jxjmc +"����������������ѧ��");
						return false;
					}else{				
						if(confirm("�㲻����"+jxjmc + "������ȷ������������ѧ��?")){
							refreshForm('pjpy_whlgdx.do?method=jxjsqSave&tjFlag=��');
						}				
					}
				});		
			}else{
				//ѧ���Ƿ����ύ���ж�
				refreshForm('pjpy_whlgdx.do?method=jxjsqSave&tjFlag=��');
			}	
		}
		});
	}
	});
}
//��ѧ���������
function appLoad(){
	var jxjdm = document.getElementById("jxjdm").value;
	var jxjmc = document.getElementById("jxjdm").options[document.getElementById("jxjdm").selectedIndex].text.trim();
	if(jxjmc!=null && jxjmc!="" && jxjmc=="��ǿ�ҵȽ�ѧ��"){//ѧ�����н�
		document.getElementById("lwqk").style.display='';
		document.getElementById("sjzz").style.display='';
	}
	
	if(jxjmc!=null && jxjmc!="" && jxjmc=="ѧϰ��ѧ��"){//������ѧ��
		document.getElementById("gk").style.display='none';
	}
}

function rychCommit(filed){
	var value = filed.split("-");
	var xh = document.getElementById("xh").value;
	var rychdm = document.getElementById("rychdm").value;
	for(var i=0; i<value.length; i++){
		if(document.getElementById(value[i]).value==""){
			alert("�뽫��\*�ŵ���Ŀ��д������");
			return false;
		}
	}		
	whlgPjpy.checkDemand(xh,rychdm,"rychdmb",function(data){
	if(data!=null && data!=" "){
		whlgPjpy.getXmfl("rychdmb",rychdm,function(xmfl){
			if(xmfl=="�����ƺ�"){
				alert(data + "�������������㲻��������������ƺţ�");
				return false;
			}else{
				if(confirm(data + "��������������ȷ������������������ƺ�?")){
					refreshForm('pjpy_whlgdx.do?method=rychsqSave');
				}
			}
		});
	}else{
		//ѧ���Ƿ����ύ���ж�
		refreshForm('pjpy_whlgdx.do?method=rychsqSave');
	}
	});	
}

function showHidden(obj){
	var rychdm = obj.value;
	whlgPjpy.getXmfl("rychdmb",rychdm,function(data){
		if(data=="�����ҵ��"){
			document.getElementById("dlsq").style.display="";
			document.getElementById("dlsqly").style.display="";
		}else{
			document.getElementById("dlsq").style.display="none";
			document.getElementById("dlsqly").style.display="none";
		}
	});
}

function checkAll(str){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if(flag){
		if(confirm('��ȷ��ִ�иò�����')){
			BatAlert.showTips('���ڲ�������ȴ�...');	
			refreshForm('pjpy_whlgdx.do?method=checkRychAll&yesNo='+str);
		}
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}
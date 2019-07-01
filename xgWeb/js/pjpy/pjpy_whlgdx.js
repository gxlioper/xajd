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
	if(sdfs=="人数"){
		document.getElementById("rs").style.display='';
		document.getElementById("jyrsbl").style.display='none';
	}else if(sdfs=="人数比例"){
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
			document.getElementById("sdfs").value="人数比例";
		}else{
			document.getElementById("jyrsbl").style.display='none';
			document.getElementById("rs").style.display='';
			document.getElementById("sdfs").value="人数";
		}
	}
}

function timeSave(){
	if($('jyrs')&&$('jyrsbl').style.display==''){
		if($('jyrs').value > 100 || $('jyrs').value == ''){
			alert('建议人数比例不符合标准！');
			return false;
		}else{
			if(checkTime('xssqkssj','xssqjssj','shkssj','shjzdj') && confirm('确定要保存吗？')){
				document.forms[0].submit();
				return true;
			}
			return false;
		}
	}else{
		if(checkTime('xssqkssj','xssqjssj','shkssj','shjzdj') && confirm('确定要保存吗？')){
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
		alert("请选择"+jQuery("#xbmc").val()+"!");
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
if(name!=null && name == "学术科研奖"){
	document.getElementById("lwqk").style.display='';
	document.getElementById("sjzz").style.display='';
}

if(name!=null && name == "新生奖学金"){
	document.getElementById("gk").style.display='';
}

if(name!="学术科研奖"){
	document.getElementById("lwqk").style.display='none';
	document.getElementById("sjzz").style.display='none';
}
if(name!="新生奖学金"){
	document.getElementById("gk").style.display='none';
}
}

//奖学金申请保存
function jxjCommit(filed){
var value = filed.split("-");
var xh = document.getElementById("xh").value;
var jxjdm = document.getElementById("jxjdm").value;
var jxjmc = document.getElementById("jxjdm").options[document.getElementById("jxjdm").selectedIndex].text;
for(var i=0; i<value.length; i++){
	if(document.getElementById(value[i]).value==""){
		alert("请将带\*号的项目填写完整！");
		return false;
	}
}

whlgPjpy.checkDemand(xh,jxjdm,"jxjdmb",function(data){	
	if(jxjmc=="新生奖学金" || jxjmc=="新生奖"){
		whlgPjpy.checkIsNewStu(xh,function(date){
		if(date==false){			
			alert("非新生无法申请"+jxjmc+ "!");
			return false;
		}else{
			if(data!=null && data!=" "){
				whlgPjpy.getXmfl("jxjdmb",jxjdm,function(data){			
					if(data=="学校奖学金" || data=="国家奖学金" || data=="学校、国家奖学金" || data=="国家、学校奖学金"){
						alert("你不符合"+jxjmc +"条件，不能申请该项奖学金！");
						return false;
					}else{				
						if(confirm("你不符合"+jxjmc + "条件，确定继续申请该项奖学金?")){
							refreshForm('pjpy_whlgdx.do?method=jxjsqSave&tjFlag=否');
						}				
					}
				});		
			}else{
				//学生是否能提交的判断
				refreshForm('pjpy_whlgdx.do?method=jxjsqSave&tjFlag=是');
			}	
		}
		});
	}
	});
}
//奖学金申请加载
function appLoad(){
	var jxjdm = document.getElementById("jxjdm").value;
	var jxjmc = document.getElementById("jxjdm").options[document.getElementById("jxjdm").selectedIndex].text.trim();
	if(jxjmc!=null && jxjmc!="" && jxjmc=="自强乙等奖学金"){//学术科研奖
		document.getElementById("lwqk").style.display='';
		document.getElementById("sjzz").style.display='';
	}
	
	if(jxjmc!=null && jxjmc!="" && jxjmc=="学习奖学金"){//新生奖学金
		document.getElementById("gk").style.display='none';
	}
}

function rychCommit(filed){
	var value = filed.split("-");
	var xh = document.getElementById("xh").value;
	var rychdm = document.getElementById("rychdm").value;
	for(var i=0; i<value.length; i++){
		if(document.getElementById(value[i]).value==""){
			alert("请将带\*号的项目填写完整！");
			return false;
		}
	}		
	whlgPjpy.checkDemand(xh,rychdm,"rychdmb",function(data){
	if(data!=null && data!=" "){
		whlgPjpy.getXmfl("rychdmb",rychdm,function(xmfl){
			if(xmfl=="荣誉称号"){
				alert(data + "不符合条件，你不能申请该项荣誉称号！");
				return false;
			}else{
				if(confirm(data + "不符合条件，你确定继续申请该项荣誉称号?")){
					refreshForm('pjpy_whlgdx.do?method=rychsqSave');
				}
			}
		});
	}else{
		//学生是否能提交的判断
		refreshForm('pjpy_whlgdx.do?method=rychsqSave');
	}
	});	
}

function showHidden(obj){
	var rychdm = obj.value;
	whlgPjpy.getXmfl("rychdmb",rychdm,function(data){
		if(data=="优秀毕业生"){
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
		if(confirm('您确定执行该操作吗？')){
			BatAlert.showTips('正在操作，请等待...');	
			refreshForm('pjpy_whlgdx.do?method=checkRychAll&yesNo='+str);
		}
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}
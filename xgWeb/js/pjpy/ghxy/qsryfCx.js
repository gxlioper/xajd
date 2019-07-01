function initCs(){
	var userName="";
	var userDep="";
	var lddm="";
	var userType="";
	var isFdy="";
	if($("userName")){
		userName=$("userName").value;
	}
	if($("userDep")){
		userDep=$("userDep").value;
	}
	if($("lddm")){
		lddm=$("lddm").value;
	}
	if($("userType")){
		userType=$("userType").value;
	}
	if($("isFdy")){
		isFdy=$("isFdy").value;
	}
	initQsxx.initCs(lddm,userType,userName,isFdy,userDep,getCs);
}

function initQsh(){
	var userName="";
	var userDep="";
	var lddm="";
	var userType="";
	var isFdy="";
	var cs="";
	if($("lc")){
		cs=$("lc").value;
	}
	if($("userName")){
		userName=$("userName").value;
	}
	if($("userDep")){
		userDep=$("userDep").value;
	}
	if($("lddm")){
		lddm=$("lddm").value;
	}
	if($("userType")){
		userType=$("userType").value;
	}
	if($("isFdy")){
		isFdy=$("isFdy").value;
	}
	initQsxx.initQsh(lddm,cs,userType,userName,isFdy,userDep,getQsh);
}


function initCsByQx(){
	var userName="";
	var userDep="";
	var lddm="";
	var userType="";
	var isFdy="";
	var blog=false;
	var yhqxfp = document.getElementsByName("yhqxfp");
	var checks=new Array();
	if($("userName")){
		userName=$("userName").value;
	}
	if($("userDep")){
		userDep=$("userDep").value;
	}
	if($("lddm")){
		lddm=$("lddm").value;
	}
	if($("userType")){
		userType=$("userType").value;
	}
	if($("isFdy")){
		isFdy=$("isFdy").value;
	}
	for(i=0;i<yhqxfp.length;i++){
		if(document.getElementsByName("yhqxfp")[i].checked){
			blog=true;
			checks[i]=yhqxfp[i].value;
		}else{
			checks[i]="";
		}
	}	
	if(blog){
		initQsxx.initCsByQx(lddm,userType,userName,isFdy,userDep,checks,getCs);
	}
}

function initQshByQx(){
	var userName="";
	var userDep="";
	var lddm="";
	var userType="";
	var isFdy="";
	var cs="";
	var blog=false;
	var yhqxfp = document.getElementsByName("yhqxfp");
	var checks=new Array();
	if($("lc")){
		cs=$("lc").value;
	}
	if($("userName")){
		userName=$("userName").value;
	}
	if($("userDep")){
		userDep=$("userDep").value;
	}
	if($("lddm")){
		lddm=$("lddm").value;
	}
	if($("userType")){
		userType=$("userType").value;
	}
	if($("isFdy")){
		isFdy=$("isFdy").value;
	}
	
	for(i=0;i<yhqxfp.length;i++){
		if(document.getElementsByName("yhqxfp")[i].checked){
			blog=true;
			checks[i]=yhqxfp[i].value;
		}else{
			checks[i]="";
		}
	}	
	if(blog){
		initQsxx.initQshByQx(lddm,cs,userType,userName,isFdy,userDep,checks,getQsh);
	}
}
function getCs(data){
	DWRUtil.removeAllOptions($("lc"));		
	DWRUtil.addOptions($("lc"),data,"dm","mc");
}

function getLd(data){
	DWRUtil.removeAllOptions($("lddm"));		
	DWRUtil.addOptions($("lddm"),data,"lddm","ldmc");
}

function getQsh(data){
	DWRUtil.removeAllOptions($("qsh"));		
	DWRUtil.addOptions($("qsh"),data,"dm","mc");
}

function changeList(){
	var checks = new Array(4);
	var yhqxfp = document.getElementsByName("yhqxfp");
	var userName=$("userName").value;
	var userDep=$("userDep").value;	
	var blog=false;
	for(i=0;i<yhqxfp.length;i++){
		if(document.getElementsByName("yhqxfp")[i].checked){
			checks[i]=yhqxfp[i].value;
			blog=true;
		}else{
			checks[i]="";
		}
	}	
	if(blog){
		initQsxx.getSsByQx("ld",userDep,userName,checks,getLd);
	}

}

function checkBox(){
	var blog=false;
	var yhqxfp = document.getElementsByName("yhqxfp");
	for(i=0;i<yhqxfp.length;i++){
		if(document.getElementsByName("yhqxfp")[i].checked){
			blog=true;
		}
	}	
	return blog;
}


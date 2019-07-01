/**
 * �����Ƿ�Ϊ����µ�����
 */
function chkNumInput(obj){ 
    var inValue = $(obj).value;
    if(inValue!=""&&inValue.match(/^\d+\.{0,1}\d{0,3}$/)==null){			
	   return true;
	}else{
	   return false;
	}
}

/**
 * �����ⲻ����Ϊ�������ַ�����ȫ���Ա�����Ϊ��������
 * 
 * @param obj
 * @return
 */
function chgPkValue(obj){
	var value = obj.value;
	var key = event.keyCode;

	// ���ң�����
	if (key == 37 || key == 39){
		return true;
	}
	
	obj.value = value.replace(/%/g,"��").replace(/&/g,"��")
					 .replace(/[+]/g,"��").replace(/[#]/g,"��")
					 .replace(/[']/g,"��").replace(/[(]/g,"��")
					 .replace(/[)]/g,"��").trim();
}

/**
 * �Ƿ�����
 */
function isMobile(s){ 
	s = trim(s); 
	var p = /1\d{10}/; 
	return p.test(s);
}

/**
 * ���ֵ��Ϊ��������Ϊ��
 */
function checkInputData(obj){
	obj.value = obj.value.replace(/[^\d]/g,'').replace(/^(0)(.*)/g,'$1');
}
/**
 * �򵥵���ϵ��ʽ��֤
 */
function checkInputLxfx(obj){
	obj.value = obj.value.replace(/[^\d-]/g,'');
}
function checkABC(obj){
	if(!/^[A-Z]+$/.test(obj.value) ){
		obj.value ="";
	}
}

function checkInput(obj){
	obj.value = obj.value.replace(/[^\d]/g,'');
}

/**
 * ������ֿ��Դ�С����
 * 
 * @param obj
 * @return
 */
function checkInputNum(obj){
	 // �Ȱѷ����ֵĶ��滻�����������ֺ�.
	obj.value = obj.value.replace(/[^\d.]/g,"");
	// ���뱣֤��һ��Ϊ���ֶ�����.
	obj.value = obj.value.replace(/^\./g,"");
	// ��ֻ֤�г���һ��.��û�ж��.
	obj.value = obj.value.replace(/\.{2,}/g,".");
	// ��֤.ֻ����һ�Σ������ܳ�����������
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	
	if(obj.value.length > 1 && obj.value.substr(0,1)==0 && obj.value.substr(1,2) != '.'){  
		obj.value = parseFloat(obj.value);
		//obj.value = obj.value.replace(/\b(0+)/gi,"");
	} 
}
/**
 * ֻ��������������С�����1λ
 * 
 * @param obj
 * @return
 */
function clearNoNum(obj) {
    obj.value = obj.value.replace(/[^\d.]/g, ""); //���"����"��"."������ַ�
    obj.value = obj.value.replace(/^\./g, ""); //��֤��һ���ַ������ֶ�����
    obj.value = obj.value.replace(/\.{2,}/g, "."); //ֻ������һ��. ��������
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/, '$1$2.$3'); //ֻ����������С��
};
/**
 * �Ƿ�double���͵���ֵ
 */
function isDouble(s){
			var p = /^\d+(?=\.{0,1}\d+$|$)/;
			return p.test(s); 
}

/**
 * ��֤�Ƿ���eEmail��ʽ
 */
function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
}


/*
 * ��֤�绰����
 */
function checkPhone(obj) {
	var text = obj.value;
	if (null !== text && ''!= text) {
		if(text.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			showAlertDivLayer("������ĵ绰�����Ϲ���!",{},{"clkFun":function(){
				obj.focus();
			}});
		 return false;
	 }
	}
}

/*
 * ��֤���
 */
function checkMoney(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (!/^[0-9]\d*\.[0-9]{2}$|^[0-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text)) {
			
			showAlertDivLayer("����������ֲ����Ϲ���!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
	}
}

/*
 * ��֤������λ��Ϊ0
 */
function checkNumNotZero(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (!/^(0|[1-9][0-9]*)$/.test(text)) {
			obj.value="";

	}
	}
}



/*
 * ��֤����
 */
function checkInt(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (/[^\d]/.test(text)) {
			
			showAlertDivLayer("����������ֲ����Ϲ���!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
	}
}



/**
 * �ж�ѧ���Ƿ����
 */
function chkIsStu(obj,tableName,zd){
		
	var xh = obj.value;
			
	if(xh !=""){
			
		var colList = zd.split("-");
		var pk = "xh";
		var pkValue = xh;
	
		getDtjsInfo.getStuDefInfo(tableName, pk, pkValue,colList,function(data){
			if( data != null && data.length > 0){
				for (i = 0; i < colList.length; i++) {
					var id = colList[i];
					if($(id)){
						$(id).value = data[i];
					}
				}
			}else{
				alert("��ѧ�������ڣ���ȷ�ϣ�");
				obj.focus();
			}
		});
	}
}

/**
 * ����ʱ��ֵ�Ƿ������жϣ��������������뷨�����ƹ�?
 */
function chkInput(obj,evt){
	var evt=evt?evt:(window.event?window.event:null);
	var key = evt.keyCode;
	if((key>=48&&key<=57)||key==46||key==127||key==8||(key>=96&&key<=105)||key==110){
	}
	else{
		var data=obj.value;
		var reg = /^\d+\.{0,1}\d{0,3}$/;
		var role = data.match(reg);
		if(role==null&&data!=""){
			alert("������Ӣ��״̬�µ�����!");
            obj.value="0";
            return false;
        }
	}
}

/**
 * ֻ�������˸��maxlength='20'
 */
function onlyBackSpace(obj,evt){
	var evt=evt?evt:(window.event?window.event:null);
	if(evt.keyCode == 8){
		obj.value = "";
		return true;
	}else {
		return false;
	}
}

/**
 * ֻ�����������޶�
 */
function chkonlynum(evt) {
	var evt=evt?evt:(window.event?window.event:null);
	if ((evt.keyCode >=48) && (evt.keyCode<=57)) {
		evt.returnValue=true;
	}
	else {
		evt.returnValue=false;
	}
}

/**
 * ��֤�����ֶ��Ƿ����
 */
function checkAllInput(inputStr){
	var input = inputStr.split("!!");
	for(var i=0;i<input.length;i++){
		var temp = document.getElementById(input[i]);
		if(temp==null || temp.value==null || temp.value=="" || trim(temp.value)==""){
			alert("�뽫������Ϣ����д������");
			return false;
		}
	}
	return true;
}

/**
 * ��֤�Ƿ���ֵ����ֵ��Ϊ0
 */
function checkNull(obj){
	if(jQuery.trim(obj.value) == ""){
		obj.value = "0";
	}
}

/**
 * ���幫�÷���:����Ӵ���
 */
function checkWinType() {
	if (window.dialogArguments == null || window.parent == null) {
		alert("�Ƿ����ʣ����ڼ����رգ�");
		Close();
		return false;
	}
}

/**
 * ��֤ѧ�꣬����Ƿ�һ��
 */
function checkXnNd(xn, nd) {
	if (document.getElementById(xn).tagName.toLowerCase() == "select" && document.getElementById(nd).tagName.toLowerCase() == "select") {
		if (document.getElementById(xn).value == "") {
			alert("��ѡ��ѧ�ꡢ���!");
			return false;
		}
		if (!inArray(document.getElementById(nd).value, document.getElementById(xn).value.split("-"))) {
			alert("ѧ�ꡢ��Ȳ�һ��,������Ҫ��������ݣ�");
			return false;
		}
	}
	return true;
}

/**
 * ��ָ��id��ֵ����divid��
 */
function textAreaFormat(id,divid){
	var value = document.getElementById(id).value;
	document.getElementById(divid).innerText = value;
}

/**
 * �ж������Ƿ���
 */
function checkInputIshz(obj) {
	if (obj.value !="" && !obj.value.match(/[^\u4e00-\u9fa5]/g)) {
	  alertInfo("ֻ�������ַ�,������������!");
	  obj.value = "";
	  return false;
  	}
}

/**
 * �ж������Ƿ���
 */
function CheckChinese(obj){     
	����var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	����if(reg.test(obj.value)){     
		showAlert("�������뺺�֣�",{},{"clkFun":function(){
	       obj.value = "";  
		   obj.focus();     
	    }});
		      
  }
}

/**
 * �ж������Ƿ���
 */
function CheckChineseByString(s){
    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    if(reg.test(s)){
        alertInfo("������������!");
		return false;
    }
    return true;
}


/**
 * ����ҳ����ѡ��ѧ�ź�����ٴζ�ѧ�Ž����޸���ô������������Ա��꼶����Ϣ
 */
function checkXhExists(idArr) {
		if (idArr == null || idArr == '') {
			idArr = ['xm','xb','nj','xymc','zymc','bjmc'];
		} else {
			idArr = idArr.split("-");
		}
		for (var i = 0; i < idArr.length; i++) {
			if ($(idArr[i])) {
				document.getElementById(idArr[i]).value = "";
			}
		}
	}

/**
 * �ж�ѧ���Ƿ����
 */
function chkIsStu(obj,tableName,zd){
		
	var xh = obj.value;
			
	if(xh !=""){
			
		var colList = zd.split("-");
		var pk = "xh";
		var pkValue = xh;
		var query = "";
		
		getOtherData.getTableInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){
				for (i = 0; i < colList.length; i++) {
					var id = colList[i];
					if($(id)){
						$(id).value = data[i];
					}
				}
			}else{
				alert("��ѧ�������ڣ���ȷ�ϣ�");
				obj.focus();
			}
		});
	}
}

/**
 * ����checkName�������еĺ�checkName��ֵ��ͬname��ֵ��checkboxȫ��ѡ�� luning 2010-6-23
 */ 
function checkedALL(obj,checkName){
	   if(obj.checked){
      	 for(i=0;i<document.getElementsByName(checkName).length;i++){
      	   	document.getElementsByName(checkName)[i].checked=true;
         }
	   }else{
	   	 for(i=0;i<document.getElementsByName(checkName).length;i++){
      	   	document.getElementsByName(checkName)[i].checked=false;
         }
	   }
}

/**
 * �����ϴ��ļ���id�����ϴ��ļ������ͣ�Ϊ����Ĭ��Ϊ'.xls'���� luning 2010-6-24
 */ 
function check_File(fileId,fileFormat) {
	// �ļ�·��
	var filePath= document.getElementById(fileId).value;
	if(fileFormat==""){
		fileFormat="xls";
	}
	if (filePath.split(".")[1]!=fileFormat) {
		alert("��ѡ����ļ����Ϸ���������ѡ��");
		document.getElementById(fileId).value = "";
		return false;
	}else{
		return true;
	}
}

/**
 * ������ʱ�ļ���Ƿ���ѡ�е���
 */
function checkTzhd(url, w, h) {
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}
	if (curr_row == null) {
		alert("��ѡ��Ҫ�������У�");
		return false;
	} else {
		url +=  curr_row.cells[1].innerText;		
		showTopWin(url, w, h);	
	}	
}

/**
 * ��������е�Ԫ���Ƿ����ظ�
 */
function checkArrayEleRepeat(array,splitStr){
	if(typeof array == "string"){
		array = array.split(splitStr);
	}
	for(var i=0; i<array.length; i++){
		for(var j=i+1; j<array.length-i+2; j++){
			if(array[i] == array[j]){
				return true;
			}
		}
	}
	return false;
}

/**
 * ����ƴ���ַ���,�ж��ַ�������Ӧ�������Ƿ�Ϊ��
 */
function checkNotNull(str) {
	
	var result = true;
	
	var spt = str.split("-");
	for (var i = 0; i < spt.length; i++) {
		if(spt[i] != null && spt[i] != ""){
			//2015-09-28 �޸��ˣ�yxy �ж�ǰ��trim()
			if (jQuery.trim(jQuery("#"+spt[i]).val()) == "") {
				result = false;
				break;
			}
		}
	}
	return result;
}

/**
 * ��֤���������ݲ��ܳ�����������(ָ��������)
 */
function checkLenN(lblname, obj, leng) {
	if(obj.value.length > leng){
		showAlert("��" + lblname + "���������������Ϊ"+leng+",���Ѿ���������ȷ�ϣ�",{},{"clkFun":function(){
				obj.focus();
		}});
		// alert("����������"+leng+"�֣�");
	}
}

/**
 * ��֤���������ݲ��ܳ�����������
 */
function checkLen(obj, leng) {
	if(obj.value.length > leng){
		showAlert("���������������Ϊ"+leng+",���Ѿ���������ȷ�ϣ�",{},{"clkFun":function(){
				obj.focus();
		}});
		// alert("����������"+leng+"�֣�");
	}
}

/**
 * ��֤ĳ����������������leng1��leng2֮��
 */
function checkLenBtwX(lblname,obj, leng1,leng2) {
	if(obj.value.length < leng1 || obj.value.length > leng2){
		showAlert("��" + lblname + "��������������ΧΪ"+leng1+"��"+leng2+"����ȷ�ϣ�",{},{"clkFun":function(){
				obj.focus();
		}});
	}
}

/**
 * ��֤��������������leng1��leng2֮��
 */
function checkLenBtw(obj, leng1,leng2) {

	if(obj.value.length < leng1||obj.value.length > leng2){
		showAlert("��������������ΧΪ"+leng1+"��"+leng2+"����ȷ�ϣ�",{},{"clkFun":function(){
				obj.focus();
		}});
	}
}

function chCount(obj,leng1,leng2) {
    var para = document.getElementById("chCount");
    if(para == null){
        para = document.createElement("div");
        para.id = "chCount";
    }
    para.innerText = "��������"+obj.value.length+"���ַ�";

    obj.parentNode.insertBefore(para,obj);
    if(obj.value.length < leng1||obj.value.length > leng2){
        para.style = "color:red";
    }else {
        para.style = "color:green";
    }
}

/**
 * ��ǰʹ�õ�����¼��ļ�� ����ı�����������Ƿ��� δ������TRUE ����֮ FALSE zdList Ҫ����ı�ģ��ID�б� ��ʽ xh-xm-xb
 * zdmcList ��ʾ��Ϣ�е������б� ��ʽ ѧ��-����-�Ա� zsnum Ҫ����ı�ģ�����������ֵ ��ʽ ������
 */
function checkTextAreaLength(zdList,zdmcList,zsnumList) {
	if (zdList == null ||zdmcList == null|| zsnumList == null) {
		return false;
	}
	var zdArray = zdList.split("-");
	var zdmcArray = zdmcList.split("-");
	var zsnumArray = zsnumList.split("-");
	for (var i=0;i<zdArray.length;i++) {
		if ($(zdArray[i])) {
			var textValue = document.getElementById(zdArray[i]).value;
			if (textValue != '' && textValue.length > zsnumArray[i]) {
				showAlert("����" + zdmcArray[i] +"����������,�������" + zsnumArray[i] + "����.");
				return false;
			}
		}
	}
	return true;
}

/**
 * ʹ��������Ч
 */
function disableChildren(obj) {
	if (obj) {
		obj.disabled = (!bEditMode);
		for (var i = 0; i < obj.children.length; i++) {
			disableChildren(obj.children[i]);
		}
	}
}

/**
 * ��ҳ����ָ��id(��ele���)�Ķ�����Ϊdisplay luning 2010-6-24
 */
function isNotSee(ele,dis){
	var tmp = ele.split("-");      
	if(dis=="dis"){
	  for (i = 0; i < tmp.length; i++) {
		ocument.getElementById(tmp[i]).style.display = "none";
	  }
	}else{
	  for (i = 0; i < tmp.length; i++) {
		document.getElementById(tmp[i]).style.display = "";
	  }
	}		
}

/**
 * �ж��Ƿ�ѡ���и�ѡ��
 */	
function isSelect(name) {
	var checkBoxArr = document.getElementsByName(name);
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (!flag) {
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�����");
		return false;
	} else {
		return true;
	}
}


/** ���幫�÷���:�趨һ��tab������Ϊdisable */
function setEleDisable(sTagName){
	var subNum = document.getElementsByTagName(sTagName).length;
	for(var i = 0; i < subNum; i++){
		document.getElementsByTagName(sTagName)[i].disabled = true;
	}
}

/**
 * ָ������ѧ���ֶ����ݳ��� luojw 2010-6-28
 */
 
// function sendXx(){
// //ҳ���������ָ������ѯ���ݳ��������磨<input type="hidden" id="tableName" name="tableName"
// value="view_ybdyxx"/>��
// var tableName=$("tableName").value;
// //����Դ��������չʾjsp�ı�ͷ�����磨<input type="hidden" id="lx" name="lx" value="Ԥ����Ա"/>��
// var lx="";
// //����Դ����������������:
// //<input type="hidden" id="zd" name="zd" value="sfty-xxsh"/>
// //<input type="hidden" id="va" name="va" value="��-ͨ��"/>
// var zd="";
// var va="";
// var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
//	
// if($("lx")){
// lx=$("lx").value
// };
// if($("zd")){
// zd=$("zd").value
// };
// if($("va")){
// va=$("va").value
// };
//	
// url+="&tableName="+tableName;
// if(lx !=""){
// url+="&lx="+lx;
// }
// if(zd !=""){
// url+="&zd="+zd;
// }
// if(va !=""){
// url+="&va="+va;
// }
// showTopWin(url,800,600);
// }

/**
 * ���Ϊ������Ϊ0,�����ύ��ֵ luojw 2010-6-28
 */

function checkNullInput(obj){
	if(obj.value == ""){
		obj.value = "0";
	}
}


/** ���幫�÷���:�������֤���� */
function chkSfzh(obj) {
	
	if ("" == obj.value){
		return ;
	}
	
	var sfzh = obj.value.toLowerCase();
	var OldID;
	if(sfzh.length == 15){
/*
 * if(sfzh.substring(6,8)<'80'){
 * BatAlert.MyAlert("����������֤�����ǣ�������ǰ�ģ������뿼�����˵����֤���룡","",function(){
 * obj.select(); obj.focus(); }); return false; }else{ OldID = sfzh; return
 * true; }
 */
		OldID = sfzh;
		return true;
	}else if(sfzh.length == 18){
/*
 * if(sfzh.substring(6,10)<'1980'){
 * BatAlert.MyAlert("����������֤�����ǣ�����������ǰ�ģ������뿼�����˵����֤���룡","",function(){
 * obj.select(); obj.focus(); }); return false; }else{ OldID = sfzh.substring(0,
 * 6) + sfzh.substring(8,sfzh.length-1); }
 */
		
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	}else{
		showAlert("��������ȷ�����֤���룡");
		obj.select();
		obj.focus();
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "x","9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		showAlert("��������ȷ�����֤���룡");
		obj.select();
		obj.focus();
		return false;
	}
	return true;
}

/** ���幫�÷���:�������֤���� */
function checkSfzh(obj) {
	var num = obj.value;
	if (num==''||num==null) return true;
    num = num.toUpperCase();  
    // ���֤����Ϊ15λ����18λ��15λʱȫΪ���֣�18λǰ17λΪ���֣����һλ��У��λ������Ϊ���ֻ��ַ�X��
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))   
    { 
    	showAlert('��������֤�ų��Ȳ��ԣ����ߺ��벻���Ϲ涨��\n15λ����ӦȫΪ���֣�18λ����ĩλ����Ϊ���ֻ�X��'); 
    	obj.value="";
        return false; 
    } 
    // У��λ����ISO 7064:1983.MOD 11-2�Ĺ涨���ɣ�X������Ϊ������10��
    // ����ֱ�����������ں�У��λ
    var len, re; 
    len = num.length; 
    if (len == 15) 
    { 
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
        var arrSplit = num.match(re); 

        // ������������Ƿ���ȷ
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
        var bGoodDay; 
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
        if (!bGoodDay) 
        { 
            showAlert('��������֤����������ڲ��ԣ�'); 
            obj.value="";
            return false; 
        } 
        else 
        { 
                // ��15λ���֤ת��18λ
                // У��λ����ISO 7064:1983.MOD 11-2�Ĺ涨���ɣ�X������Ϊ������10��
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
                var nTemp = 0, i;   
                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6); 
                for(i = 0; i < 17; i ++) 
                { 
                    nTemp += num.substr(i, 1) * arrInt[i]; 
                } 
                num += arrCh[nTemp % 11];   
                return true;   
        }   
    } 
    if (len == 18) 
    { 
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
        var arrSplit = num.match(re); 

        // ������������Ƿ���ȷ
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
        var bGoodDay; 
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
        if (!bGoodDay) 
        { 
            showAlert('��������֤����������ڲ��ԣ�'); 
        	obj.value="";
            return false; 
        } 
    else 
    { 
        // ����18λ���֤��У�����Ƿ���ȷ��
        // У��λ����ISO 7064:1983.MOD 11-2�Ĺ涨���ɣ�X������Ϊ������10��
        var valnum; 
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
        var nTemp = 0, i; 
        for(i = 0; i < 17; i ++) 
        { 
            nTemp += num.substr(i, 1) * arrInt[i]; 
        } 
        valnum = arrCh[nTemp % 11]; 
        if (valnum != num.substr(17, 1)) 
        { 
        	showAlert('��������֤�ŵ����һλ����ȷ��Ӧ��Ϊ��' + valnum); 
        	obj.value="";
            return false; 
        } 
        return true; 
    } 
    } 
    return true; 
}


/***************************���֤��֤��ʼ***********************************************/
function chkSfzhNew(obj) { 
    var idCard = obj.value;               //ȥ���ַ���ͷβ�ո�                     
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);       //����15λ���֤����֤    
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");                // �õ����֤����   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //����18λ���֤�Ļ�����֤�͵�18λ����֤
        	
            return true;   
        }else {
        	alert("��������ȷ�����֤���룡");
    		obj.select();
    		obj.focus();
            return false;   
        }   
    } else {
    	alert("��������ȷ�����֤���룡");
		obj.select();
		obj.focus();
        return false;   
    }   
}   
/**  
 * �ж����֤����Ϊ18λʱ������֤λ�Ƿ���ȷ  
 * @param a_idCard ���֤��������  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {
	var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // ��Ȩ����   
	var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // ���֤��֤λֵ.10����X   
    var sum = 0;                             // ������Ȩ��ͱ���   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;                    // �����λΪx����֤���滻Ϊ10�����������   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];            // ��Ȩ���   
    }   
    valCodePosition = sum % 11;                // �õ���֤����λ��   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * ��֤18λ�����֤�����е������Ƿ�����Ч����  
  * @param idCard 18λ�����֤�ַ���  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // ������getFullYear()��ȡ��ݣ�����ǧ�������   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
  /**  
   * ��֤15λ�����֤�����е������Ƿ�����Ч����  
   * @param idCard15 15λ�����֤�ַ���  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // ���������֤�е����������迼��ǧ��������ʹ��getYear()����   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }  
  /***************************���֤��֤����***********************************************/
  

 /**
	 * ���𳵳��θ�ʽ quph 2010-7-19
	 */
function checkCc(obj) {
		var text = obj.value;
		if ('' != text) {
			if (!/^[A-Z]\d{2,4}$|^[1-9]\d{2,4}$/.test(text)) {
				alert('���θ�ʽ����ȷ��');
				obj.focus();
				return false;
			}
		}
}		

 /**
	 * ��֤ʱ���ʽ��23:00�� quph 2010-7-19
	 */
function checkTime(obj) {
		var text = obj.value;
		if ('' != text) {
			if (!/^[0-23]{2}\:[0-59]{2}$/.test(text)) {
				alert('�Ƿ���ʱ���ʽ');
				obj.focus();
				 return false;
			}
		}
	}
	
// add author:lt time:2010-7-28
// �ڽ��в���ǰ������ѯ�����еı�ѡ�ֶ��Ƿ���ѡ��
// idҪѡ�е�ID�б��ַ��� example: xh-xm-xb
// msgҪ��ʾ����Ϣ�б��ַ��� example: ѧ��-����-�Ա�
// id �� msg ���ַ���ƴ�ӱ���һ��
function checkListIsSelect(id, msg) {
		var idArray = id.split("-");
		var msgArray = msg.split("-");
		for (var i=0;i<idArray.length;i++) {
			if ($(idArray[i]).value == null || $(idArray[i]).value == "") {
				alert("����ѡ��" + msgArray[i] + "������Ȼ���ٽ��в���!");
				return false;
			}
		}	
	return true;
}



// ��֤��ʼ����ʱ��
function checkSearchTj(kssjId,jssjId){
	var kssj = jQuery("#"+kssjId).value;
	var jssj = jQuery("#"+jssjId).value;
	
	if(kssj != "" && jssj != "" && kssj > jssj){
		alertInfo("ʱ����Ⱥ������ȷ����ȷ�ϣ�");
		return false;
	}
	return true;
	
}

// ��֤��ʼ����ʱ��
function checkSjTj(kssjId,kssjMc,jssjId,jssjMc){
	var kssj = jQuery("#"+kssjId).value;
	var jssj = jQuery("#"+jssjId).value;
	if(kssj != "" && jssj != "" && kssj > jssj){
		alertError(kssjMc+"��������"+jssjMc+"����ȷ��");
		return false;
	}
	return true;
	
}

// ��֤��ʼ����ʱ��
function checkTimeRule(obj,kssjId,kssjMc,jssjId,jssjMc){
	var kssj = jQuery("#"+kssjId).value;
	var jssj = jQuery("#"+jssjId).value;
	
	if(kssj != "" && jssj != "" && kssj > jssj){
		alert(kssjMc+"��������"+jssjMc+"����ȷ��");
		obj.value="";
		return false;
	}
	return true;
	
}

function isPhone(phone){
	var p = /^\d{0,4}\-{0,1}\d{7,8}$/;
	return p.test(phone);
}


// ������֤ ��1-120��
function isAge(age){
	var p = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
	
	return p.test(age);
}



// ��֤��ַhttp+\:\/\/
function isURL(url){
	var p =/([\w\-]+\.)+[\w-]+(\/[\w- \.\/?%&=]*)?/;
	return p.test(url);
}



/*
 * ����zfstyle_v4��ʽ��֤�ı����ʽ ���水ť��id ����ΪsaveButton ��֤��ַ����ʾ������Ϣ��DIV id����ΪurlErrow
 * author:�����
 */
function checkURL(obj){
	var text = obj.value;
	if(''!=text && !isURL(text)) {
		$('urlErrow').className='msg_error';
		$('saveButton').disabled=true;
		obj.focus();
	}else {
		$('urlErrow').className='hide';
		$('saveButton').disabled=false;
	}
}



/*
 * ����zfstyle_v4��ʽ��֤�ı����ʽ ���水ť��id ����ΪsaveButton ��֤�����ʽ����ʾ������Ϣ��DIV id����ΪemaliErrow
 * author:�����
 */
function checkEmaile(obj){
	var text = obj.value;
	if(''!=text && !isEmail(text)) {
		$('emaliErrow').className='msg_error';
		if($('saveButton')){
			$('saveButton').disabled=true;
		}
		obj.focus();
	}else {
		$('emaliErrow').className='hide';
		if($('saveButton')){
			$('saveButton').disabled=false;
		}
	}
}



/*
 * ����zfstyle_v4��ʽ��֤�ı����ʽ ���水ť��id ����ΪsaveButton ��֤�绰��ʽ����ʾ������Ϣ��DIV id����ΪphoneErrow
 * author:�����
 */	
function checkPhoneV4(obj){
	var text = obj.value;
	if(''!=text && !isPhone(text)){
		$("phoneErrow").className='msg_error';
		$('saveButton').disabled=true;
		obj.focus();
	} else{
		$("phoneErrow").className='hide';
		$('saveButton').disabled=false;
	} 
}

/*
 * ����zfstyle_v4��ʽ��֤�ı����ʽ ���水ť��id ����ΪsaveButton ��֤�绰��ʽ����ʾ������Ϣ��DIV author:�����
 */	
function checkPhoneError(obj,errorId){
	var text = obj.value;
	if(''!=text && !isPhone(text)){
		$(errorId).className='msg_error';
		$('saveButton').disabled=true;
		obj.focus();
	} else{
		$(errorId).className='hide';
		$('saveButton').disabled=false;
	} 
}

/*
 * ����zfstyle_v4��ʽ��֤�ı����ʽ ���水ť��id ����ΪsaveButton ��֤�ı����ʽ����ʾ������Ϣ��DIV id����Ϊ
 * author:�����
 */	
function checkTextError(obj,errorId){
	var text = obj.value;
	if(''==text){
		return true;
	}
	if(''!=text && !isPhone(text)){
		$(errorId).className='msg_error';
		if($('saveButton')){
			$('saveButton').disabled=true;
		}
		obj.focus();
	} else{
		$(errorId).className='hide';
		if($('saveButton')){
			$('saveButton').disabled=true;
		}
	} 
}


function checkPsw(s) {
	if (s.length < 6) {
		alertError('���벻��С��6λ');
		return false; // ����6λ
	}
	if (/^(\d)\1+$/.test(s) ) {
		alertError('���벻��Ϊ��ͬ�ַ�');
		return false;  // ȫһ��
	}
	if (/^([a-z A-Z])\1+$/.test(s) ) {
		alertError('���벻��Ϊ��ͬ�ַ�');
		return false;  // ȫһ��
	}
	var str = s.replace(/\d/g, function($0, pos) {return parseInt($0)-pos;});     
	if (/^(\d)\1+$/.test(str)) {
		alertError('���벻��Ϊ˳����ֵ');
		return false;  // ˳��
	}
	str = s.replace(/\d/g, function($0, pos) {return parseInt($0)+pos;});     
	if (/^(\d)\1+$/.test(str)) {
		alertError('���벻��Ϊ˳����ֵ');
		return false;   // ˳��
	}
	return true;
}



/*
 * ��֤���
 */
function checkMoney2(text) {
	if (null !== text && ''!= text) {
		if (!/^[0-9]\d*\.[0-9]{2}$|^[0-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text)) {
			 return false;
		}
	}
	return true;
}


/**
 * �����Ƿ�Ϊ����µ�����
 */
function chkNumInput2(value){
    if(value!=""&&value.match(/^\d*$/)==null){
	   return true;
	}else{
	   return false;
	}
}


/**
 * ��֤����
 * 
 * @param obj
 * @return
 */
function checkAge(obj){
	if (obj.value != "" && !isAge(obj.value)){
		showAlertDivLayer("����������䲻���Ϲ���",{},{"clkFun":function(){
			obj.focus();
		}});
		return false;
	}
}

function isNotChar(obj){
	obj.value = obj.value.replace(/[^\u0000-\u00ff]/g,'');
	return true;
}
/**
 * ͬ�������� ��A�������b������ͬ����ʹ���߲���ѡ��ͬһѡ�
 * 
 * @param id1
 *            a������ id
 * @param id2
 *            b������ id
 * @user [982] zhangCl
 * @return
 */
function tbxlk(id1,id2){
	var jlscx="-1";// ��¼ɾ����
	var jlscx2="-1";
	// ��a���������Ӹ����¼���ͬ��ɾ����b��������ͬ��ֵ�������Ǹ���value�Ƿ���ͬ�ж�
	jQuery("#"+id1).bind("change",function(){
		var yhdm=jQuery(this).val();
		if(yhdm!=""){
			jQuery("#"+id2).append(jlscx);
			jlscx=jQuery("#"+id2+" option[value="+yhdm+"]");
		
			jQuery("#"+id2+" option[value="+yhdm+"]").remove();
		}
	});
	// ��b����
	jQuery("#"+id2).bind("change",function(){
		if(yhdm!=""){
			var yhdm=jQuery(this).val();

			jQuery("#"+id1).append(jlscx2);
			jlscx2=jQuery("#"+id1+" option[value="+yhdm+"]");
			jQuery("#"+id1+" option[value="+yhdm+"]").remove();
		}
	});
}

var firstNull;// ��һ���ն���
/**
 * �ն�����
 * 
 * @param ids
 *            ����id�ַ��� �� - �ָ�
 * @author 982 �Ų�·
 * @return
 */
function checkNull(ids){
	var isok=true;
	var id = ids.split("-");
	jQuery.each(id,function(i){
		var arry=getObject(id[i]);
		jQuery.each(arry,function(j){
			if(!checkFunction(arry[j])){
				isok=false;
			}
		});
	});
	if(!isok){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������", {}, {
			"clkFun" : function() {
				jQuery(firstNull).focus();
				firstNull=null;
			}
		});
	}
	return isok;
}
/**
 * ��ȡ���������Ҫ���name֧��
 * 
 * @param obj
 * @return
 */
function getObject(obj){
	var object=jQuery("#"+obj);
	var list=new Array();
	if(!object.val()){
		jQuery("[name='"+obj+"']").each(function(){
			list.push(jQuery(this));
		});
	}else{
		list.push(object);
	}
	return list;
}
/**
 * ��ȡ�������ָ���� radio��checkbox�����⴦��
 * 
 * @param obj
 * @return
 */
function getObjValue(obj){
	var type=jQuery(obj).attr("type");
	if(type=="radio"||type=="checkbox"){
		var name=jQuery(obj).attr("name");
		if(!jQuery("[name="+name+"]:checked").val()){
			return false;
		}
	}
	return jQuery(obj).val();
}
/**
 * ��ȡ�ն������Ӽ���
 * 
 * @param obj
 * @return
 */
function checkFunction(obj){
	return checkObject(obj,function(){
		var v=getObjValue(obj);
		if(!v||jQuery.trim(v)==""){
			isok=false;
			if(!firstNull){
				firstNull=obj;
			}
			addEvent(obj);
			return false;
		}
		return true;
	});
}
/**
 * ��Ӽ����¼�
 * 
 * @param obj
 * @return
 */
function addEvent(obj){
	jQuery(obj).bind("keyup keydown change",function(){
		checkObject(obj,function(){
			var v=getObjValue(obj);
			if(!v||jQuery.trim(v)==""){
				return false;
			}
			return true;
		});
	});
}
/**
 * ���ö�Ӧ��ʽ
 * 
 * @param obj
 * @param check
 * @return
 */
function checkObject(obj,check){
	var type=jQuery(obj).attr("type");
	if(type=="radio"||type=="checkbox"){
		obj=jQuery(obj).parent();
	}
	if(!check()){
		jQuery(obj).css("border","1px solid #FF9999");
		return false;
	}else{
		jQuery(obj).css("border","");
		return true;
	}
}
/**
 * ������ť(�����ύ���ݺ��ֹ����ύ)
 * @author [982] �Ų�·
 * @return
 */
function lock(){
	jQuery("button").each(function(){
		jQuery(this).attr("disabled","disabled");
		jQuery(this).css({color:"#cccccc"});
	});
}
/**
 * �������
 * @author [982] �Ų�·
 * @return
 */
function unlock(){
	jQuery("button").each(function(){
		jQuery(this).removeAttr("disabled");
		jQuery(this).css({color:"#ffffff"});
	});
}
/**
 * ����json�Զ�����ҳ��ֵ
 * 
 * @param data
 *            json����
 * @param isCover
 *            Ϊtrue���Ӧƥ��id���ã�Ϊfalse��ֻ�Զ�Ӧ����Ϊ��ֵ������
 * @author [982] �Ų�·
 * @return
 */
function autoSetParam(data,isCover){
	for(var param in data){
		if(isCover){
			if(jQuery("#"+param)){
				jQuery("#"+param).val(data[param]);
			}
		}else{
			if(jQuery("#"+param)&&jQuery("#"+param).val()==""){
				var str=data[param];
				if(jQuery("#"+param).is("textarea")){
					str = str.replace(/<br\s*\/?>/g, "\n");
				}
				jQuery("#"+param).val(str);
			}
		}
	}
}


//��֤���� by yxy 2015-09-24
function checkzsinput_yz(obj,num){
	if(jQuery(obj).val().length > num){
		showAlert("�������Ϊ"+num+"�����Ѿ���������ȷ�ϣ�");
		return false;
	}
}

/*
 * ��֤��ֻ���������0������������λС����
 */
function checkMoneyBykeyUp(obj) {
	obj.value = obj.value.replace(/[^\d.]/g,'');
	var text = obj.value;
	if (null !== text && ''!= text) {
		
		obj.value = obj.value.replace(/^(.*)/g,'$1');
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		obj.value = obj.value.replace(/([0-9]+\.[0-9]{2})[0-9]*/, "$1");
			
	}

}

/**
 * ��֤��ֻ���������0������������λС����keyup��blur���
 */
function checkMoneyForKeyup(obj){    
    
     //�Ȱѷ����ֵĶ��滻�����������ֺ�.     
     obj.value = obj.value.replace(/[^\d\.]/g,'');       
     //���뱣֤��һ��Ϊ���ֶ�����.       
     obj.value = obj.value.replace(/^\./g,'');       
     //��ֻ֤�г���һ��.��û�ж��.       
     obj.value = obj.value.replace(/\.{2,}/g,'.');       
     //��֤.ֻ����һ�Σ������ܳ�����������       
     obj.value = obj.value.replace('.','$#$').replace(/\./g,'').replace('$#$','.');  
     //ȥ����ͷ��0����������С���㣩
     obj.value = obj.value.replace(/^0+(?=[0-9])/, '');
     //ֻ��������λС��  
     obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');  
     
} 

/**
 * ��֤��ֻ���������0������������λС����blur��keyup���
 */
function checkMoneyForBlur(obj){    
	checkMoneyForKeyup(obj);
	
    //�������һ��С����
	obj.value = obj.value.replace(/\.$/, '');
} 

/**
 * ��֤Сʱ����ֻ���������0����������0/5��β��һλС����keyup��blur���
 */
function checkTimeNumForKeyup(obj){
	obj.value=obj.value.replace(/(?:\D*)?(\d*)?(\.)?(0|5)?(?:\d*)?/ig,'$1$2$3');
}

/**
 * ��֤Сʱ����ֻ���������0����������0/5��β��һλС����blur��keyup���
 */
function checkTimeNumForBlur(obj){
	checkTimeNumForKeyup(obj);
	//�������һ��С����
	obj.value = obj.value.replace(/\.$/, '');
}

//��ֻ֤����������
function validateNUM(val){  
 if(!/^[0-9]*$/.test(val)){  
     return false; 
 }else{
	 return true;
 }
}  

//�滻ǰ��0
function replaceAboveZero(obj){
	var len = jQuery(obj).val().length;
	var Value =  jQuery(obj).val();
	var value = "";
	var flag = "";
	if(len > 1){
		for(var i=0;i<len-1;i++){
			if(Value.substring(i,i+1) != '0'){
				flag = i;
				break;
			}
			flag=i;
		}
		value =Value.substring(i,len);
		jQuery(obj).val(value);
	}
}

/**
 * 1λС��
 */
function checkMoneyBykeyUpByone(obj) {
	obj.value = obj.value.replace(/[^\d.]/g,'');
	var text = obj.value;
	if (null !== text && ''!= text) {
		
		obj.value = obj.value.replace(/^(.*)/g,'$1');
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		obj.value = obj.value.replace(/([0-9]+\.[0-9]{1})[0-9]*/, "$1");
			
	}

}

function checkZs(obj) {
	var num = jQuery(obj).val().trim();
	if(num != ''){
		if(!/^100$|^(\d|[1-9]\d)$/.test(num)){
			showAlert("������0-100������", {}, {
				"clkFun" : function() {
					obj.focus();
					return false;
				}
			});
		}else{
			return true;
		}	
	}else{
		return true;
	}
}

//��֤����(�����������������λ��Ϊ��)
function checkNumForPeople(obj){
	replaceAboveZero(obj);
	obj.value = obj.value.replace(/[^\d\.]/g,'');
	if(obj.value.indexOf(".") != -1){
		if(obj.value.indexOf(".") == 0){
			obj.value = obj.value.substring(1);
		}else{							
			var position = obj.value.indexOf(".");
			obj.value = obj.value.substring(0,position);		
		}
	}
	if(obj.value == "0"){
		showAlertDivLayer("�޶������������0!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		})
	}	
}

//��֤��ʱ(�������һλС������λ����Ϊ�㣬��ĩβΪС���㣬��ȡ��С����)
function checkTimeForDekt(obj){
	if(obj.value.indexOf(".") == obj.value.length-1){
		obj.value = obj.value.substring(0,obj.value.length-1);
	}
	if((obj.value.indexOf(".") < 0 && obj.value.indexOf("0") == 0) || (obj.value == null || obj.value == '')){
		showAlertDivLayer("��ʱ�������0!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		})
		return false;
	}
	return true;
}

/**
 * �����ֻ����̶��绰�������ţ���������
 * @param objId
 * @returns {boolean}
 */
function isTelephone(objId) {
    var obj = document.getElementById(objId);
    var pattern=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}1[0-9]{10}$)/;
    return pattern.test(obj.value);
}

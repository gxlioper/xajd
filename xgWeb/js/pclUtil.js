var count = 1;
var max = 0;
 var da = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
 
//���Ӽ���
function trAdd(the_tab,type,numAdd,lx){

	dwr.engine.setAsync(false);
	var len = document.getElementById(the_tab).rows.length+1;
	var num = $(numAdd).value;
	var mklx = $("mklx").value;

	var cellfu = new Array();

	if( mklx == "sh" ){
		cellfu = getNrTitle(lx);
	}else{
		cellfu = getNrTitle(lx);
	}

	count=len;  

	if(type=='add'){   
		max++;
		DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
	}else{
		if(num==""||num==null){	
			return false;
		}
		for(i=count;i<=num;i++){        
			max++;
			DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
			count++;
		}
		$(numAdd).value = "";
	}    
	dwr.engine.setAsync(true);
}

//ɾ����ѡTR
function trDel(the_tab,delRow){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    var checkbox = document.getElementsByName(delRow);
    var checkArray = new Array();
    var size = 0

    for(i=0;i<checkbox.length;i++){
    	if(checkbox[i].checked == true){
    		checkArray[size++] = eval(checkbox[i].value);
    	}	
    }
   
    if(checkArray.length == 0){
    	alert('��û��ѡ��Ҫ�����ļ�¼����ѡ��');
    	return false;
    }
    	
   
    if(confirm('ȷ��Ҫɾ��ѡ�еļ�¼��')){
    	for(i=0;i<checkArray.length;i++){
    		tabobj.deleteRow(checkArray[i]-1);
    		for(j=0;j<checkArray.length;j++){
    			checkArray[j] += -1;
    		}
   	 }
    
   	 for(i=0;i<checkbox.length;i++){
    		checkbox[i].value=i+1;
  	  }
    }
}

//ɾ������TR
function trDelAll(the_tab,numDel){
	var tabobj = document.getElementById(the_tab);
	var length = tabobj.rows.length;
	var num = $(numDel).value; 
	if(length==0){
		$(numDel).value = "";
		return false;     
	}
	if(num==""||num==null){	
		return false;
	}
	if(num>length){
		num = length;
	}
	if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
		for(i=1;i<=num;i++){           
			length--;
		}
		for(i=1;i<=num;i++){
			length--;
			tabobj.deleteRow(tabobj.rows.length-1);
		}
	}
	$(numDel).value = "";
}	

//ɾ��ȫ��TR
function trDelAllTr(the_tab,numDel){
	var tabobj = document.getElementById(the_tab);
	var length = tabobj.rows.length;
	var num = $(numDel).value; 
	if(length==0){
		$(numDel).value = "";
		return false;     
	}
	if(num==""||num==null){	
		return false;
	}
	if(num>length){
		num = length;
	}

	for(i=1;i<=num;i++){           
		length--;
	}
	for(i=1;i<=num;i++){
		length--;
		tabobj.deleteRow(tabobj.rows.length-1);
	}

	$(numDel).value = "";
}	

//����
function getNrTitle(lx){
	
	var xxdm = $("xxdm").value;
	
	var cellfu = "";

	if(xxdm == "11647" && "gybxcl" == lx){//�㽭��ý(��Ԣ����)
		cellfu = getZjcmGybxTitle();
	}else if("gygl_wsjc_fsgldj" == lx){//��Ԣ���� ������� �������� ���������ȼ�
		cellfu = getGyglWsjcGldjTitle();
	}else if("gygl_wsjc_djglfs" == lx){//��Ԣ���� ������� �������� �ȼ���������
		cellfu = getGyglWsjcGlfsTitle();
	}else if("gygl_wsjc_djbglfs" == lx){//��Ԣ���� ������� �������� �ȼ�����������
		cellfu = getGyglWsjcBglfsTitle();
	}else if("xtwh_sysz_sydc" == lx){//ϵͳά�� ��ҳ���� ��ҳ����
		cellfu = getXywhSyszSydcTitle();
	}else if("szyq_dshdjzb11" == lx){//���ݹ�ҵ԰�� �ۺ��������ɿ� ����
		cellfu = getXywhSyszSydcTitle();
	}else{//ͨ��
		cellfu = getZjcmGybxTitle();
	}
	
	return cellfu;
}

//��ʼ������
function setNr(tableName,pk,pkValue,query,tbId,btnId,lx){

	var gd_num = "";
	if($("gd_num")){
		gd_num = $("gd_num").value;
	}
	
	dwr.engine.setAsync(false);

	var colList = new Array();
	getOtherData.getTableZd(tableName,function(data){
		if( data != null){
			var n=0;
			for(var i=0;i<data.length;i++){
				colList[n] = data[i];
				n++;
			}
		}
	});

	getOtherData.getTableListArrInfo(tableName, colList,pk, pkValue,query,function(data){
		if( data != null && data.length > 0){
		
			var rowLen = data.length;
			
			if(gd_num != ""){
				$(btnId).value = gd_num;
			}else{
				$(btnId).value = rowLen;
			}
			
			trAdd(tbId,'madd',btnId,lx);
			for(var i=0;i<=rowLen;i++){
				for(var j=0;j<=colList.length;j++){
					if($(colList[j]+ i)){
						var nr = data[i-1][j];
						if(nr != null){
							$(colList[j]+ i).value = nr;
						}else{
							$(colList[j]+ i).value = "";
						}
					}
				}
			}	
		}
	});
	
	dwr.engine.setAsync(true);
}

//�㽭��ý��Ԣ����
function getZjcmGybxTitle(){
	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />"
			return htmlText;
		},
		function(data){
			var htmltext = "<select style='width:100px'  name='cllx' id='cllx" + max + "'>";
			  		htmltext+= $('cl').innerHTML;
			return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:100px'  name='clmc' id='clmc" + max + "'";
		    	htmltext += " maxLength = '25'/>";	
		    return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' name='clsl' id='clsl" + max + "' onblur='setClzj("+max+")'";
				htmltext += " onkeypress='return onlyNum(this,5)' style='width:100px;ime-mode:disabled'/>";
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100px'  name='cldj' id='cldj" + max + "' onblur='setClzj("+max+")'";
				htmltext += " onkeypress='return sztzNumInputValue(this,5,event)' style='width:100px;ime-mode:disabled'/>";
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100px'  name='zj' id='zj" + max + "'";
				htmltext += " maxLength = '10' readOnly = 'true'/>";	
			return htmltext;
		}
		];
		
	return cellfu;
}

//��Ԣ���� ������� �������� ���������ȼ�
function getGyglWsjcGldjTitle(){
	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />"
			htmlText += "<input type='hidden' name='djpx' value='"+max+"' />";
			return htmlText;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:100px'  name='wsfxx' id='wsfxx" + max + "' onblur='checkWsf("+max+");chMax(this,100)' ";
		    	htmltext += " maxLength = '3' onkeydown='return onlyNum(this,3)' onmousedown='return onlyNum(this,3)'/>";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:100px'  name='wsfsx' id='wsfsx" + max + "' onblur='checkWsf("+max+");chMax(this,100)'";
		    	htmltext += " maxLength = '3' onkeydown='return onlyNum(this,3)' onmousedown='return onlyNum(this,3)'/>";	
		    return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' name='wsfdj' id='wsfdj" + max + "'";
				htmltext += " maxLength = '10' style='width:100px;' onkeyup='chgPkValue(this)'/>";
			return htmltext;
		}
		];
		
	return cellfu;
}

//��Ԣ���� ������� �������� �ȼ���������
function getGyglWsjcGlfsTitle(){
	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />"
			htmlText += "<input type='hidden' name='wsfxx' value='' />";
			htmlText += "<input type='hidden' name='djpx' value='"+max+"' />";
			return htmlText;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:150px'  name='wsfdj' id='wsfdj" + max + "'";
		    	htmltext += " maxLength = '10' onkeyup='chgPkValue(this)' />";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:150px'  name='wsfsx' id='wsfsx" + max + "' onblur='chMax(this,100)'";
		    	htmltext += " maxLength = '3' onkeydown='return onlyNum(this,3)' onmousedown='return onlyNum(this,3)'/>";	
		    return htmltext;
		}
		];
		
	return cellfu;
}

//��Ԣ���� ������� �������� �ȼ�����������
function getGyglWsjcBglfsTitle(){
	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />";
			htmlText += "<input type='hidden' name='djpx' value='"+max+"' />";
			htmlText += "<input type='hidden' name='wsfxx' value='' />";
			htmlText += "<input type='hidden' name='wsfsx' value='' />";
			return htmlText;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:150px'  name='wsfdj' id='wsfdj" + max + "'";
		    	htmltext += " maxLength = '10' onkeyup='chgPkValue(this)'/>";	
		    return htmltext;
		}
		];
		
	return cellfu;
}

//ϵͳά�� ��ҳ���� ��ҳ����
function getXywhSyszSydcTitle(){
	var cellfu =[
//		function(data){
//			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
//			htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />";
//			return htmlText;
//		},
		function(data){
		    var htmltext = "<input type='text' name='xxid' id='xxid" + count + "' style='width:10px'";	
		   		htmltext += "value='"+da[count-1]+"' readOnly='true'/>";
		    return htmltext;
	    },
		function(data){
		    var htmltext = "<input type='text' style='width:90%'  name='xxnr' id='xxnr" + max + "'";
		    	htmltext += " maxLength = '50'/>";	
		    return htmltext;
		}
		];

	return cellfu;
}
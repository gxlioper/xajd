

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/PjpyTybDAO.js'></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>

	<script type="text/javascript">	 
	var count = 1; //��ʼ���
	var counts = 1;
	
	function checkDataUnion(obj){
		if (obj.value != "" && obj.value != null) {
			PjpyTybDAO.checkDmDataUnion(obj.value,function(data) {
				if (data) {
					alert("���룺" + obj.value + " �Ѿ����ڣ�����������!");
					obj.value = "";
					return false;
				}
			});
		}
	}
	
	function loadchange(){
			var tab = document.getElementById("titName").value;
			document.getElementById(tab+"l").className = "xxk_on_l";
			document.getElementById(tab+"m").className = "xxk_on_m";
			document.getElementById(tab+"r").className = "xxk_on_r";
	    }
	    
	    function changePage(obj){
	    	var id = obj.parentNode.id;
	    	id = id.substring(0,id.length-2);
	    	var len = 0;
	    	len =toInt(document.getElementById('xxkDiv').children.length); 
	    	for(var i=0; i<len; i++){
	    		nodeId = document.getElementById('xxkDiv').children[i].id;
	    		nodeId = nodeId.substring(0,nodeId.length-2);
	    		
	    		document.getElementById(nodeId+"l").className = "xxk_off_l";
	    		document.getElementById(nodeId+"m").className = "xxk_off_m";
	    		document.getElementById(nodeId+"r").className = "xxk_off_r";
	    		if(document.getElementById(nodeId)){
	    			document.getElementById(nodeId).style.display = "none";	    			
	    		}
	    	}		
			document.getElementById(id+"l").className = "xxk_on_l";
			document.getElementById(id+"m").className = "xxk_on_m";
			document.getElementById(id+"r").className = "xxk_on_r";
			document.getElementById(id+"ul").style.display = "";
			id = id.replace("ul", "");
			refreshForm('pjpy_tyb_zctjsz.do?id=' + id);
	    }
	    
 //����һ��
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    var cellfu = new Array();

    count=len;  
		cellfu =[
		function(data){
			return count+ "<input type='hidden' style='width:50px' name='_xuh' value='"+count+"'>";
		},
		function(data){
			return "<input type='text' style='width:50px'  name='ejdm' maxlength='3' onkeyup='checkDataUnion(this)'  id='ejdm" + count + "'>";
		},
		function(data){
			return "<input type='text'  name='ejmc'  id='ejmc" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:50px'  name='ejbl' maxlength='4' onkeyup='ckinpdata(this)'  id='ejbl" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:50px'  name='ejxzf' maxlength='4' onkeyup='ckinpjedata(this)' id='ejxzf" + count + "'>";
		},
		function(data){	    
			var htmltext = "<select style=''  name='ejlb' id='ejlb" + count + "'>";
		  		htmltext+= "<option value='+'>�ӷ�</option>";
		  		htmltext+= "<option value='-'>����</option></select>";
			return htmltext;
	    },
	    function(data){
			return  "<input type='hidden' style='' value='${rs.dm}' readonly='true'  name='ejfdm'  id='ejfdm" + count + "'><input type='text'  value='${rs.mc}' readonly='true'  name='ejfdmmc'  id='ejfdmmc" + count + "'>";
		}
		];
	
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $("numAdd").value = "";
    }    
}

function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"����")){
         tabobj.deleteRow(tabobj.rows.length-1);    
    }
}
function trDelAll(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDel").value; 
    if(length==0){
       $("numDel").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("ȷ��Ҫɾ�����"+num+"����")){ 
         for(i=1;i<=num;i++){           
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
}	

function save2jtj(the_tab,type){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	
	if(rowLen == 0){
		alert("���������������ò���Ϊ��!");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
	
		if($("ejdm"+i)){
			if($("ejdm"+i).value == "" ){
				alert("���������������õ�"+i+"�д���Ϊ�գ������룡");
				return false;
			}
		}
		if($("ejmc"+i)){
			if($("ejmc"+i).value == ""){
				alert("���������������õ�"+i+"������Ϊ�գ������룡");
				return false;
			}
		}
		if($("ejbl"+i)){
			if($("ejbl"+i).value == ""){
				alert("���������������õ�"+i+"�б���Ϊ�գ������룡");
				return false;
			}
		}
		if($("ejfdm"+i)){
			if($("ejfdm"+i).value == ""){
				alert("���������������õ�"+i+"�и�����Ϊ�գ������룡");
				return false;
			}
		}
		if($("ejxzf"+i)){
			if($("ejxzf"+i).value == ""){
				alert("���������������õ�"+i+"��������Ʒ�Ϊ�գ������룡");
				return false;
			}
		}
	}
	refreshForm('pjpy_tyb_zctjsz.do?act=save&type=3&id=' + $('titName').value);
}

function onShow(){
		dwr.engine.setAsync(false);
		var tableName="pjpy_zctjszb";
		var colList =["dm","mc","bl","xzf","lb", "fdm"];
		var pk = "fdm";
		var pkValue = $('dm').value;
		var query =" order by dm";
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){	
				$("numAdd").value=data.length;
				trAdd('flag','madd');			
				for(var i=1;i<=data.length;i++){
					if($("ejdm"+i)){
						$("ejdm"+i).value = data[i-1].dm;
					}
					if($("ejmc"+i)){
						$("ejmc"+i).value = data[i-1].mc;
					}
					if($("ejbl"+i)){
						$("ejbl"+i).value = data[i-1].bl;
					}
					if($("ejxzf"+i)){
						$("ejxzf"+i).value = data[i-1].xzf;
					}
					if($("ejlb"+i)){
						$("ejlb"+i).value = data[i-1].lb;
					}
					if($("ejfdm"+i)){
						$("ejfdm"+i).value = data[i-1].fdm;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
}  

 //����һ��
 function trAdds(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdds").value;
    var cellfu = new Array();
	
    count=len;  
		cellfu =[
		function(data){
			return count+ "<input type='hidden' style='width:40px'  name='_xuh' value='"+count+"'>";
		},
		function(data){	    
			var htmltext = "<select style=''  name='sjfdm' id='sjfdm" + count + "'>";
		  		htmltext+= "<option value=''></option>";
		  		htmltext+= "</select>";
			return htmltext;
	    },
		function(data){
			return "<input type='text' style='width:40px'  name='sjdm' maxlength='3' onkeyup='checkDataUnion(this)' id='sjdm" + count + "'>";
		},
		function(data){
			return "<input type='text' style='width:120px' name='sjmc'  id='sjmc" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:40px'  name='sjbl' maxlength='4' onkeyup='ckinpdata(this)'  id='sjbl" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:40px'  name='sjxzf' maxlength='4' onkeyup='ckinpjedata(this)' id='sjxzf" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:40px'  name='sjmrf' maxlength='4' onkeyup='ckinpjedata(this)' id='sjmrf" + count + "'>";
		},
		function(data){	    
			var htmltext = "<select style=''  name='sjlb' id='sjlb" + count + "'>";
		  		htmltext+= "<option value='+'>�ӷ�</option>";
		  		htmltext+= "<option value='-'>����</option></select>";
			return htmltext;
	    },
	    function(data){	    
			var htmltext = "<select  name='sjsfwh' id='sjsfwh" + count + "'>";
		  		htmltext+= "<option value='1'>��</option>";
		  		htmltext+= "<option value='0'>��</option></select>";
			return htmltext;
	    },
	    function(data){
			return  "<input type='text' style='width:120px'  name='sjbm' maxlength='50' id='sjbm" + count + "'>";
		},
		//function(data){
		//	return  "<input type='text' style='width:90px'  name='sjzj' maxlength='20' title='��ʽ��: xn||xq' id='sjzj" + count + "'>";
		//},
		//function(data){
		//	return  "<input type='text' style='width:90px'  name='sjzjz' maxlength='20' title='��ʽ��: \"2008-200901\"' id='sjzjz" + count + "'>";
		//},
		function(data){
			return  "<input type='text' style='width:60px'  name='sjzd' maxlength='20' id='sjzd" + count + "'>";
		},
		function(data){	    
			var htmltext = "<select style=''  name='sjshjb' id='sjshjb" + count + "'>";
		  		htmltext+= "<option value='0'>�����</option><option value='1'>һ��</option>";
		  		htmltext+= "<option value='2'>����</option><option value='3'>����</option></select>";
			return htmltext;
	    },
	    function(data){
			return  "<input type='text' style='width:130px'  name='sjtj' maxlength='30' title='��ʽ��: where xxsh = \"ͨ��\"' id='sjtj" + count + "'>";
		}
		//function(data){	    
		//	var htmltext = "<select style='' name='sjsfplzj' id='sjsfplzj" + count + "'>";
		//  		htmltext+= "<option value='1'>��</option>";
		//  		//htmltext+= "<option value='0'>��</option>";
		//  		htmltext+= "</select>";
		//	return htmltext;
	    //},
	   
		];
	
	var dm = $('titName').value;
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
       objId = "sjfdm"+count;
		      dwr.engine.setAsync(false);
		      PjpyTybDAO.query3jZcdm(dm,function(data4){
		         DWRUtil.removeAllOptions(objId);
			     DWRUtil.addOptions(objId,data4,"dm","mc");	
			  });
		      dwr.engine.setAsync(true); 
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          objId = "sjfdm"+count;
		      dwr.engine.setAsync(false);
		      PjpyTybDAO.query3jZcdm(dm,function(data4){
		         DWRUtil.removeAllOptions(objId);
			     DWRUtil.addOptions(objId,data4,"dm","mc");	
			  });
		      dwr.engine.setAsync(true); 
          count++;
       }
       $("numAdds").value = "";
    }    
}
function trDels(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"����")){
         tabobj.deleteRow(tabobj.rows.length-1);    
    }
}
function trDelAlls(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDels").value; 
    if(length==0){
       $("numDels").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("ȷ��Ҫɾ�����"+num+"����")){ 
         for(i=1;i<=num;i++){           
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDels").value = "";
}	

function save3jtj(the_tab,type){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	
	if(rowLen == 0){
		alert("�ļ������������ò���Ϊ��!");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
	
		if($("sjdm"+i)){
			if($("sjdm"+i).value == "" ){
				alert("�ļ������������õ�"+i+"�д���Ϊ�գ������룡");
				return false;
			}
		}
		if($("sjmc"+i)){
			if($("sjmc"+i).value == ""){
				alert("�ļ������������õ�"+i+"������Ϊ�գ������룡");
				return false;
			}
		}
		if($("sjbl"+i)){
			if($("sjbl"+i).value == ""){
				alert("�ļ������������õ�"+i+"�б���Ϊ�գ������룡");
				return false;
			}
		}
		if($("sjmrf"+i)){
			if($("sjmrf"+i).value == ""){
				alert("�ļ������������õ�"+i+"��Ĭ�Ϸ���Ϊ�գ������룡");
				return false;
			}
		}
		if($("sjbm"+i)){
			if($("sjbm"+i).value == ""){
				alert("�ļ������������õ�"+i+"�б���Ϊ�գ������룡");
				return false;
			}
		}
		//if($("sjzj"+i)){
		//	if($("sjzj"+i).value == ""){
		//		alert("�ļ������������õ�"+i+"������Ϊ�գ������룡");
		//		return false;
		//	}
		//}
		//if($("sjzjz"+i)){
		//	if($("sjzjz"+i).value == ""){
		//		alert("�ļ������������õ�"+i+"������ֵΪ�գ������룡");
		//		return false;
		//	}
		//}
		if($("sjzd"+i)){
			if($("sjzd"+i).value == ""){
				alert("�ļ������������õ�"+i+"���ֶ�Ϊ�գ������룡");
				return false;
			}
		}
		if($("sjfdm"+i)){
			if($("sjfdm"+i).value == ""){
				alert("�ļ������������õ�"+i+"�и�����Ϊ�գ������룡");
				return false;
			}
		}
		if($("sjxzf"+i)){
			if($("sjxzf"+i).value == ""){
				alert("�ļ������������õ�"+i+"��������Ʒ�Ϊ�գ������룡");
				return false;
			}
		}
		
	}
	showTips('���ݱ����У���ȴ�......');
	refreshForm('pjpy_tyb_zctjsz.do?act=save&type=4&id=' + $('titName').value);
}

function onShows(){
		dwr.engine.setAsync(false);
		var tableName="pjpy_zctjszb";
//		var colList =["dm","mc","bl","mrf", "xzf","lb","bm", "zd", "tj", "sfplzj", "shjb", "fdm", "zj", "zjz", "sfwh"];
		var colList =["dm","mc","bl","mrf", "xzf","lb","bm", "zd", "tj", "shjb", "fdm", "sfwh"];
		var pk = "1";
		var pkValue = "1";
		var dm = $('sjdmstr').value.split("!@");
		var query ="";
		if (dm != null && dm.length>0) {
			query =" and fdm in ('";
			for (var i=0;i<dm.length;i++) {
				query += dm[i];
				if (i<dm.length-1) {					
					query+= "','";
				} else {
					query+="'";
				}
			}
			query += ") order by dm";
		}
		
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data1){
			if( data1 != null && data1.length > 0){	
				$("numAdds").value=data1.length;
				trAdds('flags','madd');			
				for(var i=1;i<=data1.length;i++){
					if($("sjdm"+i)){
						$("sjdm"+i).value = data1[i-1].dm;
					}
					if($("sjmc"+i)){
						$("sjmc"+i).value = data1[i-1].mc;
					}
					if($("sjbl"+i)){
						$("sjbl"+i).value = data1[i-1].bl;
					}
					if($("sjmrf"+i)){
						$("sjmrf"+i).value = data1[i-1].mrf;
					}
					if($("sjxzf"+i)){
						$("sjxzf"+i).value = data1[i-1].xzf;
					}
					if($("sjlb"+i)){
						$("sjlb"+i).value = data1[i-1].lb;
					}
					if($("sjfdm"+i)){
						$("sjfdm"+i).value = data1[i-1].fdm;
					}
					if($("sjsfwh"+i)){
						var tj = data1[i-1].sfwh;
						if (tj==null) {
							tj = "1";
						}
						$("sjsfwh"+i).value = tj;
					}
					if($("sjbm"+i)){
						$("sjbm"+i).value = data1[i-1].bm;
					}
					//if($("sjzj"+i)){
					//	var tj = data1[i-1].zj;
					//	if (tj==null) {
					//		tj = "";
					//	}
					//	$("sjzj"+i).value = tj;
					//}
					//if($("sjzjz"+i)){
					//	var tj = data1[i-1].zjz;
					//	if (tj==null) {
					//		tj = "";
					//	}
					//	$("sjzjz"+i).value = tj;
					//}
					if($("sjzd"+i)){
						$("sjzd"+i).value = data1[i-1].zd;
					}
					if($("sjtj"+i)){
						var tj = data1[i-1].tj;
						if (tj==null) {
							tj = "";
						}
						$("sjtj"+i).value = tj;
					}
					if($("sjmrf"+i)){
						var val = data1[i-1].mrf;
						if (val == null) {
							val = "";
						}
						$("sjmrf"+i).value = val;
					}
					//if($("sjsfplzj"+i)){
					//	$("sjsfplzj"+i).value = data1[i-1].sfplzj;
					//}
					if($("sjshjb"+i)){
						$("sjshjb"+i).value = data1[i-1].shjb;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
}  
    function changePagePj(obj,num){
	    	obj.parentNode.className = 'ha';
	    	
	    	var elementLi = ele('ul1').children;
	    	for(li in elementLi){
	    		if(li.id != obj.id){
	    			li.className = 'ha';
	    		}
	    	}
			//id = id.replace("ul", "");
			refreshForm('pjpy_tyb_zctjsz.do?id='+obj.id);
	    }
	   function loadchangePj(){
			var table = val('id');
			var tab = table != '' ? table : ele('ul1').children[0].children[0].id;
			ele(tab).parentNode.className = "ha";
			document.getElementById("id").value=tab;
	    }
</script>
	<body onload="xyDisabled('xy');loadchangePj();onShow();onShows();">
		<html:form action="/pjpyTybZctjsz" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<input type="hidden" name="tableName" id="tableName"
				value="${tableName }" />
			<input type="hidden" name="realTable" id="realTable"
				value="${realTable }" />
			<input type="hidden" name="userName" id="userName"
				value="${userName }" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="titName" name="titName" value="${tabName }" />
			<input type="hidden" id="pt" value="pt" />
			<input type="hidden" id="sjdmstr" value="${sjdmstr }" />
			<input type="hidden" name="id" id="id" value="${tabName }"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - �������� - �ۺ����ʲ�����������</a>
				</p>
			</div>

			<div class="rightcontent">
				<table width="99%" align="center" class="formlist">
					<tr>
						<td>
							<div class="compTab" id="card" style="width: 100%">
								<div class="comp_title" id="div1">

									<ul id="ul1">
										<logic:notEmpty name="pages">
											<logic:iterate id="card" name="pages" scope="request">
												<li>
													<a href="#"
														onclick="changePagePj(this,'${card.en }');"
														id="${card.en}"> <span>${card.cn}</span> </a>
												</li>
											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="pages">
											<p align="center">
												���޼�¼!
											</p>
										</logic:empty>
									</ul>
								</div>
							</div>
						</td>
					</tr>
					<logic:notEmpty name="pages">
						<tr>
							<td>
									<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="5">
						<span>����������������</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
											<td align="center">
												<font color="red">*</font>����
											</td>
											<td align="center">
												<font color="red">*</font>����
											</td>
											<td align="center">
												<font color="red">*</font>��ռ����<font color="red">(%)</font>
											</td>
											<td align="center">
												��߷�����
											</td>
											<td align="center">
												<font color="red">*</font>�Ӽ������
											</td>
										</tr>
										<tr>
											<td align="center">
												<html:text property="dm" styleId="dm" style="width:50px"
													maxlength="10" readonly="true" value="${rs.dm }"></html:text>
											</td>
											<td align="center">
												<html:text property="mc" styleId="mc" maxlength="20"
													readonly="true" value="${rs.mc }"></html:text>
											</td>
											<td align="center">
												<html:text property="bl" styleId="bl" style="width:50px"
													onkeyup="ckinpdata(this)" value="${rs.bl }" maxlength="4"></html:text>
											</td>
											<td align="center">
												<html:text property="xzf" styleId="xzf" style="width:50px"
													onkeyup="ckinpjedata(this)" value="${rs.xzf }"
													maxlength="4"></html:text>
											</td>
											<td align="center">
												<html:select property="lb" styleId="lb" value="${rs.lb }">
													<html:option value="+">�ӷ�</html:option>
													<html:option value="-">����</html:option>
												</html:select>
											</td>
										</tr>
										<tr>
											<td colspan="5" align="right">
												<button type="button" id="btn_save" class="button2"
													onclick="saveinfo('pjpy_tyb_zctjsz.do?act=save&type=2&id='+$('titName').value,'dm-mc-bl-lb')">
													�� ��
												</button>
												&nbsp;&nbsp;
												<button type="button" id="btn_check" class="button2"
													title="����۲�����������Ϣ�г��ֵ�����"
													onclick="window.open('pjpy_tyb_zctjpzCheck.do')">
													���ݼ��
												</button>
											</td>
										</tr>
										</tbody>
									</table>
							</td>
						</tr>
					</logic:notEmpty>

					<!-- �������벻Ϊ�� -->
					<logic:notEmpty name="pages">
						<tr>
							<td align="right">
								<table width="100%" border="1" class="formlist">
									<tr>
										<td bgcolor="#e8f0fb">
											<div id="main4" style="color:blue;cursor:hand"
												onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';">
												<div align="center" class="style1 style3">
													<strong>����������������</strong>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td>
								<div id="child4" style="display:none;">
										<p>
											<!-- ��ѯ�õ�����������ʾ���� -->
											<!-- ���� -->
											&nbsp;&nbsp;
											<button type="button"  onclick="trAdd('flag','add')"
												style="width: 40px" >+</button>
											<button type="button"  onclick="trDel('flag')"
												style="width: 40px" >-</button>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
											<input type="text" name="numAdd" id="numAdd"
												style="width: 20px" onblur="trAdd('flag','madd')">
											&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
											
											<input type="text" name="numDel" id="numDel"
												style="width: 20px" onblur="trDelAll('flag')">
											&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</p>


										
										<table class="formlist" align="center" width="98%" id="tTb">
											<tr>
												<td>
													<div class="mid_box">
														<table align="center" style="width: 100%" id="rsT"
															class="tbstyle">
															<!-- ��ӡʱ��һ�в���ʾ- -->
															<thead style="height: 23px">
																<tr>
					<th colspan="7">
						<span>����������������<font color="red">��ʾ������¼����ɣ��������水ť���б��������</font></span>
					</th>
				</tr>
																<tr align="center">
																	<td align="center" nowrap="nowrap" style='width:5%'>
																		���
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>����
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>����
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>��ռ����(%)
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>��߷�����
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>�Ӽ������
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>�ϼ���Ŀ
																	</td>
																</tr>
															</thead>
															<tbody width="100%" class="tbstyle" id="flag"
																align="center">
															</tbody>
															<tr>
																<td colspan="7" align="right">
																	<button type="button" id="btn_save" class="button2"
																		onclick="save2jtj('flag','add');">
																		�� ��
																	</button>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																</td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
										</table>
								</div>
							</td>
						</tr>

						<tr>
							<td align="right">
								<table width="100%" border="1" class="formlist">
									<tr>
										<td bgcolor="#e8f0fb">
											<div id="main4" style="color:blue;cursor:hand"
												onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
												<div align="center" class="style1 style3">
													<strong>�ļ�������������</strong>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td>
								<div id="child5" style="display:none;">
									
										<p>
											<!-- ��ѯ�õ�����������ʾ���� -->
											<!-- ���� -->
											&nbsp;&nbsp;
											<button type="button" 
												onclick="trAdds('flags','add')" style="width: 40px">+</button>
											<button type="button" onclick="trDels('flags')"
												style="width: 40px" >-</button>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
											<input type="text" name="numAdds" id="numAdds"
												style="width: 20px" onblur="trAdds('flags','madd')">
											&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
											
											<input type="text" name="numDels" id="numDels"
												style="width: 20px" onblur="trDelAlls('flags')">
											&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</p>
										  <div class="con_overlfow">
										<table class="formlist" align="center" width="98%" id="tTb">
											<tr>
												<td>
													<div class="mid_box">
														<table align="center" style="width: 100%" id="rsT"
															class="tbstyle">
															<!-- ��ӡʱ��һ�в���ʾ- -->
															<thead style="height: 23px">
															<tr>
					<th colspan="13">
						<span>�ļ�������������<font color="red">��ʾ������¼����ɣ��������水ť���б��������</font></span>
					</th>
				</tr>
																<tr align="center">
																	<td align="center" nowrap="nowrap" style='width:5%'>
																		���
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>�ϼ���Ŀ
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>����
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>����
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>��ռ����(%)
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>��߷�����
																	</td>

																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>Ĭ�Ϸ���
																	</td>

																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>�Ӽ������
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>�Ƿ�ά��
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>����
																	</td>
																	<%--															<td align="center" nowrap="nowrap" style=''>--%>
																	<%--																<font color="red">*</font>����--%>
																	<%--															</td>--%>
																	<%--															<td align="center" nowrap="nowrap" style=''>--%>
																	<%--																<font color="red">*</font>����ֵ--%>
																	<%--															</td>--%>
																	<td align="center" nowrap="nowrap" style=''>
																		<font color="red">*</font>�ֶ�
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		��˼���
																	</td>
																	<td align="center" nowrap="nowrap" style=''>
																		��������
																	</td>
																	<%--															<td align="center" nowrap="nowrap" style=''>--%>
																	<%--																�Ƿ���������--%>
																	<%--															</td>--%>

																</tr>
															</thead>
															<tbody width="100%" class="tbstyle" id="flags"
																align="center">
															</tbody>
															<tr>
																<td colspan="16" align="right">
																	<button type="button" id="btn_save" class=""
																		onclick="save3jtj('flags','add');">
																		�� ��
																	</button>

																</td>
															</tr>
														</table>
														</div>
												</td>
											</tr>
										</table>
								</div>
							</td>
						</tr>
					</logic:notEmpty>
				</table>

			</div>
		</html:form>

		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");				
			</script>
			</logic:equal>
		</logic:present>
	</body>
</html>

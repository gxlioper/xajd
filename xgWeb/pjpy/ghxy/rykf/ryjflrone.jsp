<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>

	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjfunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getLdxx.js'></script>
	<script type="text/javascript">

		
 var count = 1;
 var max = 0;
 function trAdd(the_tab,type,numAdd,lb){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(numAdd).value;
    var doType = $("doType").value;
    var cellfu = getCellfu(lb);
	
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
}
function time(id){
	return showCalendar(id,'y-mm-dd');
}

function scollChange(obj){
	obj.style.posHeight=obj.scrollHeight;
}

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

function getCellfu(lb){
	var cellfu = new Array();
	if(lb == 'rzqk'){
			cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<select style='width:100%'  name='xmdm' id='xmdm" + max + "'";
	    		htmltext+=" style='cursor:hand;'>";
				htmltext+=$('lrList').innerHTML;
				htmltext+="</select>";
	 
	    	return htmltext;
	    },
	    function(data){
			var htmltext = "<textarea rows='2' cols='2' name='jfsy' id='jfsy" + max +"' ";
				htmltext += "style='width:99%' onblur='checkLen(this,200)'/>";
	  		return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' onblur='checkInputData(this);' style='width:100%'  name='jf' id='jf" + max + "'/>";
	    	return htmltext;
	    },
	    function(data){
			var htmltext = "<textarea rows='2' cols='2' name='bz' id='bz" + max + "' "; 
				htmltext += "style='width:99%' onblur='checkLen(this,200)'/>";
	  		return htmltext;
	    },
	   	function(data){	    
			var htmltext = "<input type='text' style='width:100%'  name='sj' id='sj" + max + "' ";
	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	    	return htmltext;
	    }
		];
	}
	
	return cellfu;
}

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

function save(url){
	var rowLen = max;
	var nullCout = 0;
	var xh = $("xh").value;
	
	var tabLen = document.getElementById('flag1').rows.length;
	
	if(xh == null || xh == ""){
		alert("ѧ�Ų���Ϊ��");
		return false;
	}
	
	for(var i=0;i<max;i++){
		
	if($("jf"+i)){
		if($("jf"+i).value == ""){
			alert("�������ֲ���Ϊ�գ���ȷ��");
			return false;
		}
	}
	}
	
	showTips('���������У���ȴ�......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function getXmList(){
	var xm = $('lrList');
	var arr = new Array();
	for(var i=0;i<xm.options.length;i++){
		arr[i] = {xmdm:xm.options[i].value,xmmc:xm.options[i].text};
	}
	
	return arr;
}

function onShow(){
//	var option = $('option').value;
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;

		if(xh != "" && xn != "" && xq != ""){
			dwr.engine.setAsync(false);
			var xmList = getXmList();			
			var shzt = ["δͨ��","δ���"];
			getLdxx.getXsjfxx(xh, xn, xq, shzt, xmList,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					
					trAdd('flag1','madd','numAdd1','rzqk');			
					for(var i=1;i<=data.length;i++){
						if($("xmdm"+i)){
							var xmdm = data[i-1].xmdm;
							if(xmdm == null){
								xmdm = "";
							}
							$("xmdm"+i).value = xmdm;
						}
						if($("jfsy"+i)){
							var jfsy = data[i-1].jfsy;
							if(jfsy == null){
								jfsy = "";
							}
							$("jfsy"+i).value = jfsy;
						}
						if($("jf"+i)){
							var jf = data[i-1].jf;
							if(jf == null){
								jf = "";
							}
							$("jf"+i).value = jf;
						}
						if($("bz"+i)){
							var bz = data[i-1].bz;
							if(bz == null){
								bz = "";
							}
							$("bz"+i).value = bz;
						}
							
						if($("sj"+i)){
							var sj = data[i-1].sj;
							if(sj == null){
								sj = "";
							}
							$("sj"+i).value = sj;
						}				
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}
		
	jQuery(function(){
		onShow();
	})
	</script>
</head>

<body >
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã���������-������������¼��
		</div>
	</div>
	<html:form action="/ghxy_rykf.do?method=ryjflrone" method="post">
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
		<input type="hidden" id="doType" value="" />
		<input type="hidden" name="dqqx" value="${dqqx }" />
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="20%">
					ѧ��
				</td>
				<td align="left" width="30%">
					<input type="hidden" id="xh" name="xh" value="${rs.xh }" />
					${rs.xh }
				</td>
				<td width="20%">
					<div align="center">
						�꼶
					</div>
				</td>
				<td width="30%">
					${rs.nj }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					${rs.xm }
				</td>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					${rs.xb }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<input type="hidden" id="xn" name="xn" value="${xn }" />
					${xn }
				</td>

				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<input type="hidden" id="xq" name="xq" value="${xqdm }" />
					${xqmc }
				</td>
			</tr>
			
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xb" />
					</div>
				</td>
				<td>
					${rs.xymc }
				</td>

				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
					${rs.bjmc }
				</td>
			</tr>
		</table>

		<div style="display: none">
			<select id="lrList">
				<logic:iterate id="xm" name="xmList">
					<option value="${xm.xmdm }">
						${xm.xmmc }
					</option>
				</logic:iterate>
			</select>

		</div>
		<fieldset>
			<legend>
				�ѱ����ͨ����Ŀ(���ͨ����Ŀ�����޸�)
			</legend>
			<table class="tbstyle" align="center" width="100%" id="tTb">
				<tr>
					<td>
						<div class="mid_box">
							<table align="center" style="width: 100%" id="rsT"
								class="tbstyle">
								<!-- ��ӡʱ��һ�в���ʾ- -->
			
									<thead style="height: 10px">
									<tr>
										<td align="center" nowrap="nowrap" style='width: 8%'>
											�к�
										</td>
										<td align="center" nowrap="nowrap" style='width: 15%'>
											��Ŀ����
										</td>
										<td align="center" nowrap="nowrap" style='width: 26%'>
											��������
											<font color="red">(��������100)</font>
										</td>
										<td align="center" nowrap="nowrap" style='width: 8%'>
											����
										</td>
										<td align="center" nowrap="nowrap" style='width: 29%'>
											��ע
											<font color="red">(��������200)</font>
										</td>
										<td align="center" nowrap="nowrap" style='width: 15%'>
											ʱ��
										</td>
									</tr>
								</thead>
								<tbody width="100%" align="center" class="tbstyle">
											<logic:iterate id="map" name="jftgxx" indexId="index">
											<tr>
											<td>
												${index }
											</td>
											<td>
													${map.xmmc }
											</td>
											<td>
												<textarea rows="2" cols="2" style="width: 90%" readonly="readonly">${map.jfsy }</textarea>											
											</td>
											<td>
												<input type="text" maxlength="5" style="width: 40px" readonly="readonly" value="${map.jf }" />
											</td>
											
											<td>
												<textarea rows="2" cols="4" style="width: 90%" readonly="readonly">${map.bz }</textarea>											
											</td>
											
											<td>
												<input type="text" readonly="readonly" style="width: 70px" value="${map.sj }" />
											</td>
											</tr>
											</logic:iterate>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
				<legend>
					��Ŀ¼��
				</legend>
				<p>
					<input  value="+"
						onclick="trAdd('flag1','add','numAdd1','rzqk');"
						style="width: 20px" />
					<input  value="-" onclick="trDel('flag1','delRow1');"
						style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
						onblur="trAdd('flag1','madd','numAdd1','rzqk')">
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel1" style="width: 20px"
						onblur="trDelAll('flag1','numDel1')">
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 10px">
									<tr>
										<td align="center" nowrap="nowrap" style='width: 5%'>
											ѡ��ɾ����
										</td>
										<td align="center" nowrap="nowrap" style='width: 20%'>
											��Ŀ����
										</td>
										<td align="center" nowrap="nowrap" style='width: 26%'>
											��������
											<font color="red">(��������100)</font>
										</td>
										<td align="center" nowrap="nowrap" style='width: 8%'>
											����
										</td>
										<td align="center" nowrap="nowrap" style='width: 27%'>
											��ע
											<font color="red">(��������200)</font>
										</td>
										<td align="center" nowrap="nowrap" style='width: 15%'>
											ʱ��
										</td>
									</tr>
								</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
									
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			

		<div class="buttontool" align="center">
			<button type="button" class="button2" onclick="save('ghxy_rykf.do?method=ryjflrone&doType=save');"
				style="width: 80px" id="buttonSave">
				�� ��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" onclick=Close(); style="width: 80px"
				id="buttonClose">
				�� ��
			</button>
		</div>
	</html:form>
	<logic:present name="msg">
		<input type="hidden" id="message" value="${msg }" />
		<script type="text/javascript">
	alert(document.getElementById('message').value);
	Close();
	if (window.dialogArguments
			&& window.dialogArguments.document.all("search_go")) {
		window.dialogArguments.document.getElementById('search_go').click();
	}
</script>
	</logic:present>
</body>
</html:html>

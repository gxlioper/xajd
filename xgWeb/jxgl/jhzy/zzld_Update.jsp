<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/jxglJhzyDAO.js'></script>
	<script type="text/javascript">
	var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
		return count+ "<input type='hidden' style='width:100%'  name='_xuh' value='"+count+"'>";
	},
	function(data){	
	    var htmltext = "<input type='text' style='width:100%'  name='cyxm' id='cyxm" + count + "' maxlength='10'/>";
    	return htmltext;
    },	
	function(data){	    
		var htmltext = "<select style='width:100%'  name='cyxb' id='cyxb" + count + "'>";
	  		htmltext+= "<option value='��'>��</option>";
	  		htmltext+= "<option value='Ů'>Ů</option></select>";
		return htmltext;
    },
    function(data){
    	var htmltext = "<select name='zw' id ='zw" + count + "'>";
    		htmltext += $('zwdm').innerHTML;
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='lxdh' id='lxdh" + count + "'maxlength='20' onblur='onlyNumInput(this)'/>";
    	return htmltext;
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
function time(id){
	return showCalendar(id,'y-mm-dd');
}
function repYh(obj){
	
}
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){
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
    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
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
function ztbhSave(the_tab){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	var lddm=$("lddm").value;
	var zzmc=$("zzmc").value;
	
	if(lddm == ""){
		alert("��ȷ����ά�����ӣ���");
		return false;
	}
	if(zzmc == ""){
		alert("��ȷ����֯���ƣ���");
		return false;
	}
	if(rowLen == 0){
		alert("��ά�����ݲ���Ϊ�գ���");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		if($("cyxm"+i)){
			if($("cyxm"+i).value == ""){
				alert("��"+i+"������Ϊ�գ���ȷ��");
				return false;
			}
		}
	}

   showTips('���������У���ȴ�......');
   refreshForm('/xgxt/jxgl_jhzy.do?method=zzldUpdate&doType=save');
   $("buttonSave").disabled=true;
}

function onShow(){
	var pk = $("pk").value;
	if(pk != ""){
		dwr.engine.setAsync(false);
		jxglJhzyDAO.getZzlxXxList(pk,function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');
				for(var i=1;i<=data.length;i++){
					if($("cyxm"+i)){
						$("cyxm"+i).value = data[i-1].cyxm;
					}
					if($("cyxb"+i)){
						$("cyxb"+i).value = data[i-1].cyxb;
					}
					if($("zw"+i)){
						$("zw"+i).value = data[i-1].zw;
					}
					if($("lxdh"+i)){
						var dh= "";
						if(data[i-1].lxdh != null){
							dh = data[i-1].lxdh;
						}
						$("lxdh"+i).value = dh;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}

	function chBzList(jxnd){
		jxglJhzyDAO.getLdList(jxnd,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
				$(objId).options[0].value = "";
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
	}
function onchpk(){
	var zzmc = $("zzmc").value;
	var lddm = $("lddm").value;
	if(lddm != "" && zzmc != ""){
		var pk= lddm+zzmc;
		$("pk").value = pk;
		onShow();
	}
}
	</script>
	<body onload="onShow()">
		<html:form action="/jxgl_jhzy">
			<input type="hidden" id="url" name="url" value="/pjpyszyqwh.do?method=szyc_5sAdd"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<input type="hidden" id="pk" name="pk" value="${pk}"/>
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<input type="hidden" id="doType" name="doType" value="${doType}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title"/>
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">
					<td align="right">
						��֯���ƣ�
					</td>
					<td align="left" colspan="3">
						<logic:equal name="doType" value="add">
							<html:text name="rs" property="zzmc" styleId="zzmc" maxlength="10" style='width:100%' onblur="onchpk()"/>		
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name="rs" property="zzmc" styleId="zzmc" style='width:100%' readonly="true"/>		
						</logic:notEqual>		
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="20%">
						��ѵ�꼶��
					</td>
					<td align="left" width="30%">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="nj" styleId="nj" onchange="chBzList(this.value)">
								<html:options collection="njList" labelProperty="njdm" property="njmc"/>
							</html:select>	
						</logic:equal>		
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="nj"/>
							<html:select name="rs" property="nj" styleId="nj" onchange="chBzList(this.value)" disabled="true">
								<html:options collection="njList" labelProperty="njdm" property="njmc"/>
							</html:select>		
						</logic:notEqual>							
					</td>
					<td align="right" width="20%">
						�ѽ������ӣ�
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="lddm" style="" styleId="lddm" onchange="onchpk()">
								<html:options collection="ldList" property="bzdm"labelProperty="bzmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="lddm"/>
							<html:select name="rs" property="lddm" style="" styleId="lddm" disabled="true">
								<html:options collection="ldList" property="bzdm"labelProperty="bzmc"/>
							</html:select>	
						</logic:notEqual>			
						<html:select name="rs" property="zwdm" style="display:none" styleId="zwdm">
							<html:options collection="zwList" property="zwdm"labelProperty="zwmc" />
						</html:select>
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					<p>
						<!-- ��ѯ�õ�����������ʾ���� -->
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 20px"
							onblur="trAdd('flag','madd')">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 20px"
							onblur="trDelAll('flag')">
						&nbsp;��
					</p>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:5%'>
												���
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												����
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												�Ա�
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												ְ��
											</td>
											<td align="center" nowrap="nowrap" style=''>
												��ϵ�绰
											</td>
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="ztbhSave('flag');" style="width: 80px">
						�� ��
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="buttonClose"
						 onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();" 
						 style="width: 80px" id="buttonClose">
						�� ��
					</button> </span>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

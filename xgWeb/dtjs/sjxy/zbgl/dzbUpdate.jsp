<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script language="javascript"  src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script type="text/javascript">	
var count = 1;
function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){ 
		var htmltext = "<input type='checkbox' name='checkVal' id='id" + count + "'/>";	
			htmltext += "<input type='hidden' name='id' id='pkValue" + count + "'/>";	
		return htmltext;
	},
	function(data){
		return count+ "<input type='hidden' style='width:100%'  name='_xuh' value='"+count+"'/>";
	},
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='sszb' id='sszb" + count + "' maxlength='25'/>";
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='sj' id='sj" + count + "' maxlength='25'/>";
    	return htmltext;
    },	   
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='fsj' id='fsj" + count + "' maxlength='25'/>";
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='zzwy' id='zzwy" + count + "' maxlength='25'/>";
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='xcwy' id='xcwy" + count + "' maxlength='25'/>";
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='jjwy' id='jjwy" + count + "' maxlength='25'/>";
    	return htmltext;
    },
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='xsrs' id='xsrs" + count + "' maxlength='25' readonly='true'/>";
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

function dzbSave(the_tab){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;

	if(rowLen == 0){
		alert("�������Ϊ��");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		var empty = 5;
		if($("sszb"+i)){
			if($("sszb"+i).value == ""){
				alert("��"+i+"��֧������Ϊ�գ���ȷ��");
				return false;
			}
		}
		if($("sj"+i)){
			if($("sj"+i).value == ""){
				empty--;
			}
		}
		if($("fsj"+i)){
			if($("fsj"+i).value == ""){
				empty--;
			}
		}
		if($("zzwy"+i)){
			if($("zzwy"+i).value == ""){
				empty--;
			}
		}
		if($("xcwy"+i)){
			if($("xcwy"+i).value == ""){
				empty--;
			}
		}
		if($("jjwy"+i)){
			if($("jjwy"+i).value == ""){
				empty--;
			}
		}
		if(empty == 0){
			alert("��"+i+"��֧����Ϣȫ��Ϊ�գ���ȷ��");
			return false;
		}
	}

   showTips('���������У���ȴ�......');
   saveUpdate('/xgxt/sjxyDtjsZbgl.do?method=dzbUpdate&doType=save','');
   $("buttonSave").disabled=true;
}

function onShow(){
	var pk = $("pk").value;
	if(pk != ""){
		dwr.engine.setAsync(false);
		getSjxyDtjsDAO.getDzbxxList(pk,function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');
				for(var i=1;i<=data.length;i++){
					if($("id"+i)){
						var id = data[i-1].id;
						if(id == null){
							id = "";
						}
						$("id"+i).value = id;
					}
					if($("pkValue"+i)){
						var pkValue = data[i-1].id;
						if(pkValue == null){
							pkValue = "";
						}
						$("pkValue"+i).value = pkValue;
					}
					if($("sszb"+i)){
						var sszb = data[i-1].sszb;
						if(sszb == null){
							sszb = "";
						}
						$("sszb"+i).value = sszb;
					}
					if($("sj"+i)){
						var sj = data[i-1].sj;
						if(sj == null){
							sj = "";
						}
						$("sj"+i).value = sj;
					}
					if($("fsj"+i)){
						var fsj = data[i-1].fsj;
						if(fsj == null){
							fsj = "";
						}
						$("fsj"+i).value = fsj;
					}
					if($("zzwy"+i)){
						var zzwy = data[i-1].zzwy;
						if(zzwy == null){
							zzwy = "";
						}
						$("zzwy"+i).value = zzwy;
					}
					if($("xcwy"+i)){
						var xcwy = data[i-1].xcwy;
						if(xcwy == null){
							xcwy = "";
						}
						$("xcwy"+i).value = xcwy;
					}
					if($("jjwy"+i)){
						var jjwy = data[i-1].jjwy;
						if(jjwy == null){
							jjwy = "";
						}
						$("jjwy"+i).value = jjwy;
					}
					if($("xsrs"+i)){
						var xsrs = data[i-1].xsrs;
						if(jjwy == null){
							xsrs = "";
						}
						$("xsrs"+i).value = xsrs;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}
	</script>
	<body onload="onShow()">
		<html:form action="/sjxyDtjsZbgl">
			<input type="hidden" id="doType" name="doType" value="${ doType}"/>
			<input type="hidden" id="realTable" name="realTable" value="${ realTable}"/>
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}"/>
			<input type="hidden" id="pk" name="pk" value="${ pk}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							��֧����Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						����<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left" width="30%">
						<html:hidden name='rs' property="xydm" styleId="xydm"/>
						<html:select name="rs" property="xydm" style="" styleId="xydm" disabled="true">
							<html:option value="">-----��ѡ��-----</html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right" width="20%">
						����֯���ƣ�
					</td>
					<td align="left">
						<html:text name='rs' property="dzzmc" styleId="dzzmc" maxlength="25" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						ѧ��������
					</td>
					<td align="left">
						<html:text name='rs' property="xsrs" styleId="xsrs" readonly="true"/>
					</td>
					<td align="right">
						��Ա������
					</td>
					<td align="left">
						<html:text name='rs' property="dyrs" styleId="dyrs" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						��Ա���������
					</td>
					<td align="left">
						<html:text name='rs' property="khqk" styleId="khqk" readonly="true"/>
					</td>
					<td align="right">
						�����뵳������
					</td>
					<td align="left">
						<html:text name='rs' property="sqrs" styleId="sqrs" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						��ע��<br><font color="red">(��250��)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" readonly="true"/>
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
						&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</legend>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:2%'>
												<input type="checkbox" id="selall" name="selall" onclick="selAll()">
											</td>
											<td align="center" nowrap="nowrap" style='width:3%'>
												���
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												֧������
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												��֧�����
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												��֧�������
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												��֯ίԱ
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												����ίԱ
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												�ͼ�ίԱ
											</td>
											<td align="center" nowrap="nowrap" style='width:5%'>
												��Ͻѧ������
											</td>
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr bgcolor="EEF4F9" align="center">
						<td>
						<logic:notEqual name="doType" value="view">
							<button type="button" class="button2" id="buttonSave"
								onclick="dzbSave('flag');"
								style="width: 80px">
								��	��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="buttonSave"
								onclick="sumitInfo('/xgxt/sjxyDtjsZbgl.do?method=dzbUpdate','del')"
								style="width: 80px">
								ɾ	��
							</button>
						</logic:notEqual>
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="tmpdiv1"></div>
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
			</logic:empty>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

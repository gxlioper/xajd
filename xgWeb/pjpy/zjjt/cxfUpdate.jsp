<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
<script type="text/javascript">
 var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    var doType = $("doType").value;
    var cellfu = new Array();
    
    count=len;  
          
    if("sh" == doType){
		cellfu =[
		function(data){
			return count+ "<input type='hidden' style='width:100%'  name='_xuh' value='"+count+"'>";
		},
		function(data){	    
			var htmltext = "<select style='width:100%'  name='jjf' id='jjf" + count + "'>";
		  		htmltext+= "<option value='�ӷ�'>�ӷ�</option>";
		  		htmltext+= "<option value='����'>����</option></select>";
			return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='fz' id='fz" + count + "'";
	    		htmltext += "onkeypress='return onlyNum(this,5)' style='width:100%;ime-mode:disabled'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='rq' id='rq" + count + "'";
	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){  
			var htmltext = "<textarea type='text' style='width:400px' name='sy' id='sy"+count+"'";
				htmltext+= "rows='2' onblur='chLeng(this,500);' onpropertychange='scollChange(this)'/>";	  
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<select style='width:100%'  name='shjg' id='shjg" + count + "'>";
	  		htmltext+= "<option value='δ���'>δ���</option>";
	  		htmltext+= "<option value='δͨ��'>δͨ��</option>";
	  		htmltext+= "<option value='ͨ��'>ͨ��</option>";
			return htmltext;    
	    }
		];
	}else{   
		cellfu =[
		function(data){
			return count+ "<input type='hidden' style='width:100%'  name='_xuh' value='"+count+"'>";
		},
		function(data){	    
			var htmltext = "<select style='width:100%'  name='jjf' id='jjf" + count + "'>";
		  		htmltext+= "<option value='�ӷ�'>�ӷ�</option>";
		  		htmltext+= "<option value='����'>����</option></select>";
			return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='fz' id='fz" + count + "'";
	    		htmltext += "onkeypress='return onlyNum(this,5)' style='width:100%;ime-mode:disabled'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='rq' id='rq" + count + "'";
	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){  
			var htmltext = "<textarea type='text' style='width:400px' name='sy' id='sy"+count+"'";
				htmltext+= "rows='2' onblur='chLeng(this,500);' onpropertychange='scollChange(this)'/>";	  
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%' name='shjg' id='shjg"+count+"' readonly='true' value='δ���'/>";  
	    	return htmltext;
	    }
		];
	}
	
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

function scollChange(obj){
	obj.style.posHeight=obj.scrollHeight;
}

function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){
       if($("shjg"+length).value!="δ���"){
         alert("�ü�¼�Ѿ�����ز�����ˣ����ܽ���ɾ��������");   
         return false;
       }else{
         tabobj.deleteRow(tabobj.rows.length-1);    
       }       
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
            if($("shjg"+length).value!="δ���"){
              alert("���Ѿ�����ز�����˵ļ�¼�����ܽ���ɾ��������");   
              return false;
            }
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
}	

function saveCxf(the_tab,type){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	
	if(rowLen == 0){
		alert("���зּӼ���Ŀ����Ϊ��");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
	
		if($("fz"+i)){
			if($("fz"+i).value == ""){
				alert("��"+i+"�з�ֵΪ�գ���ȷ��");
				return false;
			}
		}
		if($("rq"+i)){
			if($("rq"+i).value == ""){
				alert("��"+i+"������Ϊ�գ���ȷ��");
				return false;
			}
		}
		if($("sy"+i)){
			if($("sy"+i).value == ""){
				alert("��"+i+"������Ϊ�գ���ȷ��");
				return false;
			}
		}
	}
	var url = "/xgxt/zjjtPjpy.do?method=cxfUpdate&doType=save&type="+type;
	showTips('���������У���ȴ�......');
	saveUpdate(url,"");
}

function onShow(){
	
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;

	if(xh != "" && xn != "" && xq != ""){
		dwr.engine.setAsync(false);
		var tableName="zjjt_cxfb";
		var colList =["jjf","fz","rq","sy","shjg"];
		var pk = "xn||xq||pjxh";
		var pkValue = xn + xq + xh;
		var query =" order by shjg";
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){	
				$("numAdd").value=data.length;
				trAdd('flag','madd');			
				for(var i=1;i<=data.length;i++){
					if($("jjf"+i)){
						$("jjf"+i).value = data[i-1].jjf;
					}
					if($("fz"+i)){
						var fz = data[i-1].fz;
						if(fz == null){
							fz = "";
						}
						$("fz"+i).value = fz;
					}
					if($("rq"+i)){
						var rq = data[i-1].rq;
						if(rq == null){
							rq = "";
						}
						$("rq"+i).value = rq;
					}
					if($("sy"+i)){
						var sy = data[i-1].sy;
						if(sy == null){
							sy = "";
						}
						$("sy"+i).value = sy;
					}
					if($("shjg"+i)){
						var shjg = data[i-1].shjg;
						if(shjg == null){
							shjg = "";
						}
						$("shjg"+i).value = shjg;
						if(shjg != "δ���"){
							$("fz"+i).readOnly = "true";
							$("rq"+i).readOnly = "true";
							$("sy"+i).readOnly = "true";
							$("rq"+i).onclick = "";
							
							var jjfindex = $("jjf"+i).options.length;	
							for(var j=0;j<jjfindex;j++){	
								if(j==(jjfindex-1)){
									$("jjf"+i).options[0].value = data[i-1].jjf;
									$("jjf"+i).options[0].text = data[i-1].jjf;
								}else{
									$("jjf"+i).options[0] = null;
								}
							}
						}
					}
				}
			}		
		});
		
		dwr.engine.setAsync(true);
	}
}
</script>
	<body onload="onShow()">
		<html:form action="/zjjtPjpy">
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="zd" name="zd" value=""/>
			<input type="hidden" id="va" name="va" value=""/>
			<input type="hidden" id="lx" name="lx" value="ѧ����Ϣ"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<input type="hidden" id="url" name="url" value="/xgxt/zjjtPjpy.do?method=cxfUpdate&doType=add"/>
			<input type="hidden" id="doType" name="doType" value="${doType}"/>
			<input type="hidden" id="type" name="type" value="${type}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�${title }
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<logic:equal name="userType" value="stu">
							${rs.xh }
							<html:hidden name='rs' property="xh" styleId="xh"/>
						</logic:equal>
						<logic:notEqual name="userType" value="stu">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal name="doType" value="add">
								<button onclick="sendXx();return false;"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
						</logic:notEqual>
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<html:hidden name='rs' property="xn" styleId="xn"/>
							<html:select name="rs" property="xn" style="" styleId="xn" disabled="true">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						${rs.xb }
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<html:hidden name='rs' property="xq" styleId="xq"/>
						<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						������
					</td>
					<td align="left">
						${rs.xm }
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						${rs.zymc }
					</td>
					<td align="right">
						�༶��
					</td>
					<td align="left">
						${rs.bjmc }
					</td>
				</tr>
			</table>
			<fieldset>
					
					<p>
						<!-- ��ѯ�õ�����������ʾ���� -->
						<!-- ���� -->
						<logic:equal name="doType"value="add">
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 20px"
							onblur="trAdd('flag','madd')">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 20px"
							onblur="trDelAll('flag')">
						&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">ע�����ͨ������Ŀ�����޸��Լ�ɾ��</font>
						</logic:equal>
						<!-- ��ˣ��鿴 -->
						<logic:notEqual name="doType"value="add">
						<input type="text" name="numAdd" id="numAdd" style="display: none" onblur="trAdd('flag','madd')">
						</logic:notEqual>
					</p>
					
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
											<td align="center" nowrap="nowrap" style='width:15%'>
												�Ӽ���
											</td>
											<td align="center" nowrap="nowrap" style='width:10%'>
												��ֵ
											</td>
											<td align="center" nowrap="nowrap" style='width:10%'>
												����
											</td>
											<td align="center" nowrap="nowrap" style=''>
												����
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												���״̬
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
						<logic:notEqual name="doType" value="sh">
							<button class="button2" id="buttonSave" onclick="saveCxf('flag','add');" style="width: 80px">
								�� ��
							</button> 
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
					</logic:notEqual>
					<logic:equal name="doType" value="sh">
							<button class="button2" id="buttonSave" onclick="if (confirm('ȷ����ѧ���ļӼ��ֵ����״̬?')) {saveCxf('flag','sh');}" style="width: 80px">
								�� ��
							</button> 
							&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<logic:notEqual name="doType" value="add">
						<logic:notEqual name="type" value="add">
							<button class="button2" id="buttonClose"
								 onclick="window.close();return false;" 
								 style="width: 80px" id="buttonClose">
								�� ��
							</button>
						</logic:notEqual>
					</logic:notEqual>
				</span>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						var type = $("type").value;
						alert('�����ɹ���');
						if(type != "add"){
							dialogArgumentsQueryChick();
							window.close();
						}
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

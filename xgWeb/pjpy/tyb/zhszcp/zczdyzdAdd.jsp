<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss"  />
	<script language="javascript">
	function saveBdsz(obj){
		
		if($("zdid").value == ""){
			alert("�ֶβ���Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("zdmc").value == ""){
			alert("�ֶ�������Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("zdlx").value == ""){
			alert("�ֶ����Ͳ���Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		showTips('���������У���ȴ�......');
		refreshForm('pjpy_tyb_zczdyzdAdd.do?doType=save');
		obj.disabled=true;
	}
	
	var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
      function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='opmc' id='opmc" + count + "' maxlength='50'/>";
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
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length-1)+"�У�")){
         tabobj.deleteRow(tabobj.rows.length-1);    
    }
}

function changeCon(){
	var zdlx = $("zdlx").value;
	if(zdlx=="003"){
		$("optr").style.display="";
	}else{
		$("optr").style.display="none";
	}
	if(zdlx=="002"||zdlx=="003"){
		$("zdcd").value="";
		$("zdcdTr").style.display="none";
	}else{
		$("zdcdTr").style.display="";
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
function cxsxDis(){
	if($('cxxs').checked){
		$("cxxspx").style.display="";
	}else{
		$("cxxspx").style.display="none";
	}
}
function xzfDis() {
	if ($('sfnum').value == '1') {
		$("zgxzf").style.display="";
	} else {
		$("zgxzf").style.display="none";
	}
}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="changeCon();cxsxDis();xzfDis()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCsszDAO.js'></script>
		<html:form action="/pjpyTybZctjsz" method="post">
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - �������� - �ۺ����ʲ����Զ����ֶ�ά��</a>
			</p>
		</div>
		
				<input type="hidden" id="pk" name="pk"value="${pk }" />
				<input type="hidden" id="zdyTable" name="zdyTable"value="${zdyTable }" />
				<input type="hidden" name="zt" id="zt" value="${zt }"/>
				<div class="tab">
				<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>�ֶι���</span>
					</th>
				</tr>
			</thead>
			<tbody>
						<tr>
							<logic:notEqual value="view" name="doType">
							<td align="right" style="width:50%">
								ģ������
							</td>
							<td align="left" style="width:50%">
										<html:select name="rs" property="tabname" style="">
											<html:option value=""></html:option>
											<html:options collection="mkList" property="dm" 	labelProperty="mc" />
										</html:select>
							</td>
							</logic:notEqual>
							<logic:equal value="view" name="doType">
								<td align="right" style="width:50%">
									ģ������
								</td>
								<td>
									<html:select name="rs" property="tabname" style="">
											<html:option value=""></html:option>
											<html:options collection="mkList" property="dm" 	labelProperty="mc" />
										</html:select>
								</td>
							</logic:equal>
						</tr>
						<tr>
							<td align="right">
								�ֶ�
							</td>
							<td align="left">
								<logic:equal name="doType" value="add">
								<html:text name='rs' property="zdid" styleId="zdid" onkeyup="value=value.replace(/[^a-z]/g,'')"
								 maxlength="10"  />
								</logic:equal>
								 <logic:notEqual name="doType" value="add">
								 <bean:write name='rs' property="zdid"/>
								 <input type="hidden" name ="zdid" value="<bean:write name='rs' property="zdid"/>"/>
								 </logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֶ���
							</td>
							<td align="left">
								<html:text name='rs' property="zdmc" styleId="zdmc" />
							</td>
						</tr>
						<tr id = "zdcdTr">
							<td align="right">
								�ֶγ���
							</td>
							<td align="left">
								<html:text name='rs' property="zdcd" styleId="zdcd" onblur="onlyNumInput(this)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֶ�����
							</td>
							<td align="left">
								<html:select name="rs" property="zdlx" onchange="changeCon()" styleId="zdlx">
									<html:option value="001">�ı�</html:option>
									<html:option value="002">ʱ��</html:option>
									<html:option value="003">������</html:option>
									<html:option value="004">���ı�(ռ��һ�е��ı�)</html:option>
									<html:option value="005">�ı���</html:option>
								</html:select>
							</td>
						</tr>
						<tr id="optr" style="display: none">
							<td colspan="2">
								<legend>
									<p align="center">
										<!-- ��ѯ�õ�����������ʾ���� -->
										<button type="button"  onclick="trAdd('flag','add')"
											style="wdith:40px">+</button>
										<button type="button"  onclick="trDel('rsT')"
											style="width: 40px" >-</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
										<input type="text" name="numAdd" id="numAdd"
											style="width: 20px" onblur="trAdd('flag','madd')">
										&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
										<input type="text" name="numDel" id="numDel"
											style="width: 20px" onblur="trDelAll('rsT')">
										&nbsp;��
									</p>
								</legend>
								<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="formList">
						<thead style="height: 23px">
							<tr>
								<td align="center" nowrap="nowrap" style='width:60%' colspan="2">
									ѡ������
								</td>
							</tr>
							<logic:iterate name="opList" id="s">
								<tr>
									<td align="center" nowrap="nowrap" style='width:60%'>
										<input type='text' style='width:400px'  name='opmc' id='opmc<bean:write name = 's' property='opxh'/>' value="<bean:write name = 's' property='opmc'/>" maxlength='50'/>
									</td>
								</tr>
							</logic:iterate>
						</thead>
						<tbody width="100%" class="tbstyle" id="flag">
						</tbody>
					</table>
					</div>
					</td>
					</tr>
					<tr>
							<td align="right">
								�ֶ�����
							</td>
							<td align="left">
								<html:text name = "rs" property="zdpx" styleId="zdpx" onblur="onlyNumInput(this)" />
							</td>
					</tr>
					<tr>
							<td align="right">
								��ѯ��ʾ
							</td>
							<td align="left">
								<html:radio name = "rs" property="cxxs" onclick = "cxsxDis()" value="��ʾ" styleId="cxxs">��ʾ</html:radio>
								<html:radio name = "rs" property="cxxs" onclick = "cxsxDis()" value="����ʾ">����ʾ</html:radio>
							</td>
					</tr>
					<tr id = "cxxspx" style="display: none">
							<td align="right">
								��ѯ��ʾ˳��
							</td>
							<td align="left">
								<html:text name = "rs" property="cxxspx" styleId="cxxspx"  onblur="onlyNumInput(this)" />
							</td>
					</tr>
					
					<tr id = "cxxspx" style="">
							<td align="right">
								�Ƿ�Ϊ����
							</td>
							<td align="left">
								<html:select property="sfnum" name="rs" styleId="sfnum" onchange="xzfDis()">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
							</td>
					</tr>
					
					<tr id = "zgxzf" style="display: none;">
							<td align="right">
								���Ʒ�
							</td>
							<td align="left">
								<html:text name="rs" property="xzf" styleId="xzf" maxlength="5" onkeyup="ckinpjedata(this)"></html:text>
							</td>
					</tr>
					
					<tr id = "cxxspx" style="">
							<td align="right">
								�Ƿ����Ϊ��
							</td>
							<td align="left">
								<html:select property="sfnull" name="rs" styleId="sfnull">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
							</td>
					</tr>
					
					<tr>
						<td colspan="2">
							
						</td>
					</tr>
					</tbody>
					<tfoot>
		      <tr>
		        <td colspan="4">
		        <div class="bz">
		        	<span>
		        	<font color="red">ע�ֶν�������ƴ����Ӣ����д������ָ����ʦ��Ϊzdls��������¼�봿���ֻ��֣�</font>
							<font color="red">�ֶ����벻���ظ����ֶ�������ʾ���������ִ�СΪ����ʽ</font>
							<font color="red">�ֶ�������¼��������ĺ��ֻ�Ӣ�ġ�</font>
							</span>
				</div>
		          <div class="btn">
		            <logic:notEqual value="view" name="act">
						<logic:notEqual name="doType" value="view">
						<button type="button" class="" onclick="saveBdsz(this);" id="buttonSave">
							����
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					</logic:notEqual>
					<button type="button" class="" onclick="Close();return false;" 
						id="buttonClose">
						�ر�
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
					
					</table>
					</div>
</html:form>
			<logic:present name="result">
				<logic:equal name="result" value="true">
					<script>
				    alert("�ύ�ɹ���");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
				    alert("�ύʧ�ܣ�");
				    </script>
				</logic:equal>
				<logic:equal name="result" value="yzsb">
					<script>
				    alert("���������и��ֶ�id,���޸��ֶ�id����¼�룡");
				    </script>
				</logic:equal>
			</logic:present>
	
	</body>
</html>


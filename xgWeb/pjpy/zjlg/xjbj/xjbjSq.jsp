<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	
<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpyFunction.js"></script>
<script type="text/javascript" src="js/AjaxFunction.js"></script>
<script type="text/javascript">
/*
 ��ʾ�꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ���༶�б� 
*/
function showItems(){
	var items = document.getElementById("items");
	items.style.left = (screen.availwidth)/6;
	items.style.top = ((screen.availheight)/6)-20;
	items.style.display = "block";
	if ($("userType").value == "xy") {
		var tmp = 'xy';
		document.getElementById('xy').disabled = true;
	}
}
/*
 �����꼶��<bean:message key="lable.xsgzyxpzxy" />��רҵ���༶�б� 
*/
function hideItems(){
	var items = document.getElementById("items");
	//items.style.left = (screen.availWidth)/2;
	//items.style.top = (screen.availHeigh)/2;
	items.style.display = "none";
	document.getElementById("bjdm").value = document.getElementById("bj").value;
	refreshForm("/xgxt/zjlgPjpy.do?method=xjbjSq");
}

function xjbjSave(){
    var bjdm = "";
    var bjqk = "";
    var xn   = "";
    if($("bjdm")){
       bjdm = $("bjdm").value;
    }
    if($("xn")){
       xn=$("xn").value;
    }
    if($("bjqk")){
       bjqk = $("bjqk").value;
    }
    if(bjdm==""){
       alert("�༶���벻��Ϊ�գ�");
       return false;
    }
    if(xn==""){
       alert("ѧ�겻��Ϊ�գ�");
       return false;
    }
    if(bjqk.length>500){
       alert("�༶�������������500���ڣ�");
       return false;
    }
    var tem = bjdm+xn;
     getPjpyDao.getInfoEx("zjlg_xjbjb","bjdm||xn",tem," yxsh='ͨ��' ",function(tag){
		     if(tag){
		        alert("��ѧ�ꡢ�ð༶������,����ͨ����ز������\n�����������,�����ٴ����룡");		           	         			        
		     }else{
		        if(confirm("ȷ��Ҫ�������ݣ�")){
		           refreshForm("/xgxt/zjlgPjpy.do?method=xjbjSq&doType=save");
                   if($("buttonSave")){
                     $("buttonSave").disabled =true;
                   }
		        }              
		     }
    	});	    
}
</script>
</head>
<body onload="">
	<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope="session"/>" />
		<input type="hidden" id="isFdy" name="isFdy"
			value="<bean:write name="fdyQx" scope="session"/>" />
		<input type="hidden" id="userName" name="userName"
			value="<bean:write name="userName" scope="session"/>" />
		<input type="hidden" name="njV" id="njV"/>
		<input type="hidden" name="xyV" id="xyV"/>
		<input type="hidden" name="zyV" id="zyV"/>
		<input type="hidden" name="bjV" id="bjV"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - �����ƺ����� - �Ƚ��༶����</a>
			</p>
		</div>
		<div id="items" style="display:none; position: absolute;background-color: #AFEEEE; ">
			<table class="tbstyle">
				<tr>
					<td>
						�꼶
					</td>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList();"
							styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						<html:select property="xydm" onchange="initZyList();initBjList();"
							styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						רҵ
					</td>
					<td>
						<html:select property="zydm" onchange="initBjList();" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						�༶
					</td>
					<td>
						<html:select property="bj" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<button type="button" class="button2" onclick="hideItems();">
							ȷ ��
						</button>
					</td>
				</tr>
			</table>
		</div>
		<div class="tab">
		<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>��д�����</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					<font color="red">*</font>�༶����
				</th>
				<td align="left" width="30%">
					<html:text property="bjdm" readonly="true" onclick="showItems()"
						styleId="bjdm" />
				</td>
				<th>
					ѧ��
				</th>
				<td align="left">
					<html:select property="xn" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					�꼶
				</th>
				<td align="left">
					${rs.nj}
				</td>
				<th>
					�༶����
				</th>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc}
				</td>
				<th>
					����Ա
				</th>
				<td align="left">
					${fdy}
				</td>
			</tr>
			<tr>
				<th>
					�༶ƽ���ɼ�
				</th>
				<td align="left">
					${bjpjf}
				</td>
				<th>
					��������
				</th>
				<td align="left">
					${bjbjdl}
				</td>
			</tr>
			<tr>
				<th>
					�������Ҹ���
				</th>
				<td align="left">
					<html:text property="wmqsgs" styleId="wmqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
				<th>
					A�����Ҹ���
				</th>
				<td align="left">
					<html:text property="ajqsgs" styleId="ajqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
			</tr>
			<tr>
				<th>
					�Ƿ�����༶
				</th>
				<td align="left">
					<html:select property="sfyxbj" styleId="sfyxbj"
						style="width:120px;">
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select>
				</td>
				<th></th><td></td>
			</tr>
			<tr>
				<th>
					�༶���
					<span style="color: red">(��500��)</span>
				</th>
				<td colspan="3" scope="row" align="left">
					<html:textarea rows="12" style="width:98%" property="bjqk" />
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						  <button type="button" id="buttonSave" onclick="xjbjSave()">
							�ύ����
						  </button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
<logic:equal value="true" name="done">
	<script type="text/javascript">
    alert("�ɹ��ύ��");
  </script>
</logic:equal>
<logic:equal value="false" name="done">
	<script type="text/javascript">
    alert("�ύʧ�ܣ�");
  </script>
</logic:equal>
<logic:equal name="pass" value="no">
	<script>
	alert("�ð༶�������Ƚ��༶����������");			    
   </script>
</logic:equal>
</html>

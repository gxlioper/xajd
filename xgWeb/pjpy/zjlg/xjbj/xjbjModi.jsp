<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<script type="text/javascript">
		function xjbjModiSave(){
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
		     getPjpyDao.getInfoEx("zjlg_xjbjb","bjdm||xn",tem," yxsh='ͨ��'",function(tag){
				     if(tag){
				        alert("��ѧ�ꡢ�ð༶����Ϣ�Ѿ�ͨ����ˣ������ٽ����޸Ĳ�����");   	         			        
				     }else{
				        if(confirm("ȷ��Ҫ�������ݣ�")){
				           refreshForm("/xgxt/zjlgPjpy.do?method=xjbjModi&doType=save");
		                   if($("buttonSave")){
		                      $("buttonSave").disabled =true;
		                   }
				        }              
				     }
		    	});	    
		}
		</script>
	</head>
	<body>
	<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue"/>" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - �����ƺ����� - �Ƚ��༶�޸�</a>
			</p>
		</div>
		
		<div class="tab">
		<table width="100%" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��д�����</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th scope="col" width="15%">
					<font color="red">*</font>�༶����
				</th>
				<td align="left" width="30%">
				<html:text name="rs" property="bjdm" readonly="true"></html:text>
				</td>
				<th>ѧ��</th>
				<td align="left">
					<html:select name="rs" property="xn" disabled="true">
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
					רҵ
				</th>
				<td align="left">
					${rs.zymc}
				</td>
				<th>
					�༶����
				</th>
				<td align="left">
					${xhnum}
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
					<html:text name="rs" property="wmqsgs" styleId="wmqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
				<th>
					A�����Ҹ���
				</th>
				<td align="left">
					<html:text name="rs" property="ajqsgs" styleId="ajqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
			</tr>
			<tr>
				<th>
					�Ƿ�����༶
				</th>
				<td align="left">
					<html:select name="rs" property="sfyxbj" styleId="sfyxbj"
						style="width:120px;">
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select>
				</td>
				<th></th><td></td>
			</tr>			
			<tr>
				<td width="13%" scope="row" align="right">
					�༶���
					<span style="color: red">(��500��)</span>
				</td>
				<td colspan="3" scope="row" align="left">
					<html:textarea  name="rs" rows="12" style="width:98%" property="bjqk" />
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					  <logic:notEqual value="view" name="act">
						<button type="button" id="buttonSave" onclick="xjbjModiSave()">
							�� ��
						</button>
					  </logic:notEqual>
					  <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
</body>
<logic:equal name="done" value="true">
	<script>
	alert("��ӳɹ���");
	Close();
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	 window.dialogArguments.document.all("search_go").click();  	 
    }			          
    </script>
</logic:equal>
<logic:equal name="done" value="false">
	<script>
	alert("���ʧ�ܣ�");
	</script>
</logic:equal>
<logic:equal name="pass" value="no">
	<script>
	alert("�ð༶�������Ƚ��༶����������");			    
   </script>
</logic:equal>
</html>

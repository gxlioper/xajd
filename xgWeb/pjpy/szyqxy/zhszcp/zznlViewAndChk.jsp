<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type="text/javascript">
       function toChk(str){       
           var pkValue = $("pkValue").value;          
           var url = "/xgxt/pjpyszyqwh.do?method=szyc_zznlViewAndChk&doType=save&check="+str; 
		   var RowsStr="";		  		  	   
		   xyshDone = (str=="yes")?"ͨ��":"��ͨ��";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pk")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    	  }	    	  
		   }		   
		   if (RowsStr==""){
			   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
		   if (confirm("ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
		        if(str=="yes"){
		             getSzPjpyDAO.getPointLimit("zznl",pkVArray,pkValue,function(data){
		                if(data==""){
		                      refreshForm(url);
			                  if($("buttonYes")){
			                    $("buttonYes").disabled=true;
			                  }
			                  if($("buttonNo")){
			                    $("buttonNo").disabled=true;
			                  }	
		                }else{
		                    alert(data);
		                }
		            });
			  	}else{
			  		refreshForm(url);
			        if($("buttonYes")){
			           $("buttonYes").disabled=true;
			        }
			        if($("buttonNo")){
			           $("buttonNo").disabled=true;
			        }	
			  	}	     
		   }         		                  
       }
	</script>
	</head>
	<body>
		<html:form action="/pjpyszyqwh">
			<input type="hidden" name="xh" id="xh" value="${xh}" />
			<input type="hidden" name="xn" id="xn" value="${xn}" />
			<input type="hidden" name="xq" id="xq" value="${xq}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}" />
			<input type="hidden" name="pkVStr" id="pkVStr" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� �� ѧ���ۺ��������ɿ� - ��֯���� - ���</a>
				</p>
			</div>
			<table class="formlist">
				<tr>
					<th>
						ѧ��
					</th>
					<td align="left">
                       <bean:write name="rsxs" property="xh" />
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
						<html:select property="xn" disabled="true"  
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
				<th>
						����
					</th>
					<td align="left">
						<bean:write name="rsxs" property="xm" />
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
						<html:select property="xq"  disabled="true"
								style="padding-left:80px" styleId="xq">								
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
					</td>					
				</tr>
				<tr style="height: 23px">
					<th>
						�Ա�
					</th>
					<td align="left">
						<bean:write name="rsxs" property="xb" />
					</td>
					<th>
						�꼶
					</th>
					<td align="left">
						<bean:write name="rsxs" property="nj" />
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="rsxs" property="xymc" />
					</td>
					<th>
						רҵ
					</th>
					<td align="left">
						<bean:write name="rsxs" property="zymc" />
					</td>
				</tr>
							<tr style="height: 23px">
					<th>
						�༶
					</th>
					<td align="left">
						<bean:write name="rsxs" property="bjmc" />
					</td>
					<th>
						
					</th>
					<td align="left">
						
					</td>
				</tr>				
			</table>
			<div style="overflow: auto;">
				��¼����
					${rsNum}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">�μӻ�б�</font>
				<table align="center" width="99%" class="formlist" >
					<thead style="height: 23px">
						<tr >
						    <th>
								<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
						    </th>
							<th onclick="tableSort(this)">
								���
							</th>
							<th onclick="tableSort(this)">
								�����
							</th>
							<th onclick="tableSort(this)">
								����
							</th>
							<th onclick="tableSort(this)">
								�ȼ�
							</th>
							<th onclick="tableSort(this)">
								�Ӽ���
							</th>
							<th onclick="tableSort(this)">
								����
							</th>
							<th onclick="tableSort(this)">
								������
							</th>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;">										
							<td align=center><input type="checkbox" id="pk" name="pk"
								 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>									   
							</td>																									
							<logic:iterate id="v" name="s" offset="1">
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
			</div>
			<div class="buttontool" align="right">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonYes" onclick="toChk('yes')"
						style="width: 80px">
						ͨ  ��
					</button>
					<button type="button" class="button2" id="buttonNo" onclick="toChk('no')"
						style="width: 80px">
						��ͨ��
					</button>
					<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;"
						style="width: 80px" id="buttonClose">
						��  ��
					</button> </span>
			</div>
			<logic:present name="done">
				<logic:equal value="true" name="done">
					<script>
						alert('�����ɹ���');
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="false" name="done">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

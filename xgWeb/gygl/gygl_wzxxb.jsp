<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='js/check.js'></script>					
	</head>
	<body onload="checkWinType();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ��סѧ������ </a>
			</p>
		</div>
		<!-- ���� end-->
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/stuOutputInfo" method="post">
		    <input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-nj-zymc-bjmc" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/stuOutputInfo.do" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    	alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>				
				<!-- ��סѧ����Ϣά�� -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>��סѧ����Ϣά��</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th>
							ѧ��
							</th>
							<td align="left">
							<html:text name='rs' property="xh" readonly="true" onblur="dctStuXh()"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal name="doType" value="add">
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="" >
									ѡ��
								</button>
							</logic:equal>
							</td>
							<th align="right">
								��ס��ʼѧ��
							</th>
							<td align="left">
								<html:select name="rs" property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
						    <th align="right">
								����
							</th>
							<td align="left">
							<html:text name="rs" property="xm" styleId="xm" readonly="true"></html:text>								
							</td>						
							<th align="right">
								��ס��ʼѧ��
							</th>
							<td align="left">
							  <html:select name="rs" property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								�꼶
							</th>
							<td align="left">
							    <html:text name="rs" property="nj" styleId="nj" readonly="true"></html:text>									
							</td>
							 <th align="right">
								��ס��ʼ����
							</th>
							<td align="left">
                                   <html:text name="rs" property="wzksrq" readonly="true"
									onclick="return showCalendar('wzksrq','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " property="wzksrq"/>							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
							 <html:text name="rs" property="xb" styleId="xb" readonly="true"></html:text>	
							</td>
							<th align="right">
								�ƻ���סʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="jhwzsj" maxlength="20" styleId="jhwzsj" style="width:80px"/><span style="color: red">��12����</span>						
							</td>
						</tr>
						
						<tr>						   
							<th align="right">
								רҵ
							</th>
							<td align="left">
							    <html:text name="rs" property="zymc" styleId="zymc" readonly="true"></html:text>	
							</td>
							<th align="right">
								��ס����
							</th>
							<td align="left">
								<html:select name="rs" property="wzlxdm" styleId="wzlxdm">
									<html:options collection="wzlxList" property="wzlxdm" labelProperty="wzlxmc" />
								</html:select>
							</td>
						</tr>
						<tr>						   
							<th align="right">
								�༶
							</th>
							<td align="left">
							 <html:text name="rs" property="bjmc" styleId="bjmc" readonly="true"></html:text>	
							</td>							
							<th align="right">
								��ס��ַ
							</th>
							<td align="left">
								<html:text name="rs" property="wzdz" styleId="wzdz" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�ֻ�����
							</th>
							<td align="left">
								<html:text property="sjhm" maxlength="30" value="${rs.sjhm}" onkeyup="checkInputData(this);"></html:text>
							</td>							
							<th align="right">
								�Ƿ����üҳ�ͬ��
							</th>
							<td align="left">
								<html:select property="jzsfty" styleId="jzsfty" value="${rs.jzsfty}"> 
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								�̶��绰
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdh"/>
							</td>
							<th align="right">
								
							</th>
							<td align="left">
								
							</td>
						</tr>						
						<tr>						   
							<th align="right">
								��������
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdzxx"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>								
						<tr>
							<th align="right">
								��סԭ��<br/><font color="red">(��200����)</font>
							</th>
							<td align="left" colspan="3">
							    <html:textarea  name="rs" property="wzyy" styleId="wzyy" rows="6"  cols="75" onblur="chLeng(this,200);"></html:textarea>								
							</td>
						</tr>
						</tbody>
						<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button id="buttonSave" 
											onclick="wzDataSave()">
											����
										</button>
									</logic:notEqual>
									<button id="buttonClose" onclick="Close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��,�����������ݣ�");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function wzDataSave(){
            if(mstFill("xh-xn-xq-wzksrq-jhwzsj-wzlxdm-wzdz-wzyy-jzsfty"))
            { 
               if($('wzyy').value.length>200){               
                  alert('��סԭ����������200�֣�');
                  return false;
               }else{		                              
                    var xn = $("xn").value;
                    var xq = $("xq").value;
                    var xh = $("xh").value;
                    var wzksrq = $("wzksrq").value;               
                    pkV = xn+xq+xh+wzksrq;
                    var doType = $("doType").value;
                      getSztzData.getInfoEx("gygl_xswzxxb","xn||xq||xh||wzksrq",pkV,"1=1",function(str){
		               if(str){		         	
		                  if(confirm("��ѧ�ꡢѧ�ڡ������˿�ʼ���ڣ���ס�����Ϣ�Ѵ��ڣ�\n\nȷ��Ҫ���棿\n\n���\"ȷ��\"���������ݲ������Ѵ�����Ϣ��\n���\"ȡ��\"�����������ģ�")){
		                    refreshForm('/xgxt/OutputstuinfoSave.do');
		                    $("buttonSave").disabled=true;                          
		                  }else{
		                     return false;
		                  }	          			              
		               }else{
		                  if(confirm("ȷ��Ҫ���棿\n\n���\"ȷ��\"��������Ϣ��\n���\"ȡ��\"�����������棡")){
		                    refreshForm('/xgxt/OutputstuinfoSave.do'); 
		                    $("buttonSave").disabled=true;                             	                  
		                  }else{
		                     return false;
		                  }	
 		               }
                      }); 
               }
          }    
       }
       function mstFill(mustFill){
           var eles = mustFill.split("-");
	       for (i = 0; i < eles.length; i++) {
		      if($(eles[i])){
			     if (document.getElementById(eles[i]).value == "") {
				    alert("����ѡ���Ϊ�գ�");
				    return false;
			     }
		      }
	       }
	       return true;
       }
	</script>
</html>

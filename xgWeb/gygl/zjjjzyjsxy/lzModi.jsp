<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body onload="showLzRq();loadView();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ¥����Ϣ</a>
			</p>
		</div>
		<!-- ���� end-->
       
		<html:form action="/zjjjzy_Gygl" method="post">

			<input type="hidden" id="isview" name="isview"
				value="<bean:write name="isview" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />

			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">

				<!-- ¥����Ϣ -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>¥����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						
						<td height="22" align="right">
							<font color="red">*</font>¥����
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="lz" styleId="xh" disabled="true" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>¥����
						</td>
						<td height="22" align="left">
						<input type="hidden" name="lddm" value="${rs.lddm}">
							<html:text name="rs" property="ldmc" styleId="ldmc"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							������
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>��ְ���ڣ�
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="rzrq" styleId="rzrq"
								disabled="true" onblur="dateFormatChg(this)"
								onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							�Ա�
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="xb" />
						</td>

						<td height="22" align="right">
							��ϵ��ʽ��
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="lxdh" style="cursor:hand;" maxlength="25"></html:text>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							סַ��
						</td>
						<td height="22" align="left">
							<html:text name="rs" name="rs" property="zz" style="cursor:hand;" maxlength="50"></html:text>
						</td>
						<td height="22" align="right">
							¥�����䣺
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="dzyx" style="cursor:hand;" maxlength="25"></html:text>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">

						</td>
						<td height="22" align="left">

						</td>
						<td height="22" align="right">
							�Ƿ���ְ��
						</td>
						<td height="22" align="left">
						    <html:select name="rs" property="sfzz" style="width:120px"
								styleId="sfzz" onchange="showLzRq()">
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display: none" id="showlz">
						<td height="22" align="right">

						</td>
						<td height="22" align="left">

						</td>
						<td height="22" align="right">
							��ְ���ڣ�
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="lzrq" styleId="lzrq"
								style="cursor:hand;" onblur="dateFormatChg(this)"
								style="cursor:hand;"
								onclick="if($('rzrq').value.replace(' ','')==''){return false;}else{return showCalendar('lzrq','y-mm-dd');}"
								readonly="true" />
						</td>
					</tr>

					<tr align="left">
						<td align="right">
							��ע��<br>
								(��200����)
						</td>
						<td colspan="4">
							<html:textarea name='rs' property='bz' style="width:400px" rows='5' />
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button id="buttonSave" onclick="lzModiSave()"
											style="width: 80px">
											��	��
										</button>
									</logic:notEqual>
									&nbsp;&nbsp;
									<button id="buttonClose" onclick="Close();return false;"
										style="width: 80px">
										��	��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ��,��ϵͳ���Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function lzModiSave(){	 
	          var rzrq="";
	          var lzrq="";
	          var sfzz = "";
	           var lz = $("lz").value;
	          if($("sfzz")){
	             sfzz = $("sfzz").value;
	          }
	          if($("rzrq")){
	             rzrq = $("rzrq").value;
	          }
	          if($("lzrq")){
	             lzrq = $("lzrq").value;
	          }
	          if(sfzz=="��"){
	               if(lzrq==""){
	                  alert("��ְ���ڲ���Ϊ�գ�");
	                  return false;
	               }   
	               if(rzrq>lzrq){
	                  alert("��ְ����������ְ���ڣ�");
	                  return false;
	               }
	          }
	          if($("bz").value.length>200){
	              alert("��ע�������ܳ���200�֣�");
	              return false;
	          }
	          var url = "/xgxt/zjjjzy_Gygl.do?method=lzModi&doType=save";
	          url +="&pkValue=";
	          url +=$("pkValue").value;
	          if(sfzz=="��"){
	             var pkValue=$("lddm").value+lz;
	             gyglShareData.getLcqszZzpd("lzxxb","lddm||lz",pkValue,"lddm||lz||rzrq",$("pkValue").value,function(str){
		             if(str){		         	
		                refreshForm(url);	           		          			        
		             }else{                     
                       alert("����Ŀǰ����ְ��¥¥���������ظ���ְ��");                
		             }
    	         });
    	       }else{
	             refreshForm(url);   
	           }            
	     }
	     function showLzRq(){
	          var sfzz = $("sfzz").value;
	          if(sfzz=="��"){
	             $("showlz").style.display="";
	          }else{
	             $("showlz").style.display="none";
	          }
	     }
	</script>
</html>

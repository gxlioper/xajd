<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ���ҳ���Ϣ</a>
			</p>
		</div>
		<!-- ���� end-->	
		<html:form action="/jhzy_gygl" method="post">
		<input type="hidden" id="url" name="url" value="/jhzy_gygl.do?method=qszAdd" />			
		<input type="hidden" id="getStuInfo" name="getStuInfo"value="xh-xm-xb-nj-xymc-zymc-bjmc" />	
		<input type="hidden" id="ssbh" name="ssbh"value="${ssbh }" />
		<input type="hidden" id="pkValue" name="pkValue"value="${pkValue }" />						
			 
				<!-- ���ҳ���Ϣ -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>���ҳ���Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
                        <th height="22" align="right" width="20%">
							<font color="red">*</font>���ҳ���
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="xh" styleId="xh"  readonly="true"/>
						</td>
						<th height="22" align="right" width="20%">
							¥����
						</th>
						<td height="22" align="left">
								${ldmc }			
						</td>						
					</tr>
					<tr>
                       <th height="22" align="right">
							������
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="xm"/>
						</td>
						<th height="22" align="right">
							¥�㣺
						</th>
						<td height="22" align="left">
							${lc }						
						</td>						
					</tr>
					<tr>
                        <th height="22" align="right">
							�Ա�
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="xb"/>
						</td>
						<th height="22" align="right">
							���ң�
						</th>
						<td height="22" align="left">
							${qsh }									
						</td>						
					</tr>	
					<tr>
                        <th height="22" align="right">
							�꼶��
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="nj"/>
						</td>
						<th height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="xymc"/>					
						</td>						
					</tr>	
					<tr>
                        <th height="22" align="right">
							רҵ��
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="zymc"/>
						</td>
						<th height="22" align="right">
							�༶��
						</th>
						<td height="22" align="left">
							<bean:write name="rs" property="bjmc"/>					
						</td>						
					</tr>					
					<tr>
					    <th height="22" align="right">
							��ϵ��ʽ��
						</th>
						<td height="22" align="left">
						<html:text name="rs" property="lxdh" maxlength="25"></html:text>						
						</td>						
						<th height="22" align="right">
							<font color="red">*</font>��ְ���ڣ�
						</th>
						<td height="22" align="left">
						<html:text name="rs"    property="rzrq" styleId="rzrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true" />
					    </td>												
					</tr>									
					<tr>
					<th height="22" align="right">
							���ҳ����䣺
						</th>
					<td height="22" align="left">
							<html:text name="rs"   property="dzyx" maxlength="25"></html:text>
						</td>	
					<th height="22" align="right">
                         
					</th>					
                    <td height="22" align="left">
							
				    </td>							                        											
					</tr>	
<tr>
						<th height="22" align="right">
							�Ƿ���ְ��
						</th>
						<td height="22" align="left">
							<html:select name="rs" property="sfzz" style="width:120px"
								styleId="sfzz" onchange="showLzRq()">
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<th height="22" align="right">

						</th>
						<td height="22" align="left">

						</td>
					</tr>
					<tr style="display: none" id="showlz">
						<td height="22" align="right">

						</td>
						<td height="22" align="left">

						</td>
						<th height="22" align="right">
							��ְ���ڣ�
						</th>
						<td height="22" align="left">
							<html:text name='rs' property="lzrq" styleId="lzrq"
								style="cursor:hand;" onblur="dateFormatChg(this)"
								style="cursor:hand;"
								onclick="if($('rzrq').value.replace(' ','')==''){return false;}else{return showCalendar('lzrq','y-mm-dd');}"
								readonly="true" />
						</td>
					</tr>																			
					<tr align="left">
							<th align="right">
								��ע��<br>
								(��200����)
							</th>
							<td colspan="4">
								<html:textarea  name="rs"  property='bz' style="width:470px"
									rows='7' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button id="buttonSave" onclick="dataSave('xh-rzrq')"
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
	     function dataSave(mustFill){	           
	          var eles = mustFill.split("-");
	          var rzrq="";
	          var lzrq="";
	          var sfzz = "";
	          var qsz = $("xh").value;
	          if($("sfzz")){
	             sfzz = $("sfzz").value;
	          }
	          if($("rzrq")){
	             rzrq = $("rzrq").value;
	          }
	          if($("lzrq")){
	             lzrq = $("lzrq").value;
	          }
	          for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			           alert("�뽫��\"*\"�ŵ���Ŀ����������");
			           return false;
		           }		
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
              var dzyx = document.getElementById('dzyx').value;
	          if(!isEmail(dzyx) && dzyx!=""){
		          alert("��������ȷ�ĵ�������!");
		          return false;
	          }
              
               if(sfzz=="��"){
	             var pkValue=qsz+$("ssbh").value;
	             gyglShareData.getLcqszZzpd("qszxxb","qsz||ssbh",pkValue,"qsz||ssbh||rzrq",$("pkValue").value,function(str){
		             if(str){		         	
		                refreshForm("/xgxt/jhzy_gygl.do?method=qszModi&doType=Save");           		          			        
		             }else{                     
                       alert("����Ŀǰ����ְ�����ҳ��������ظ���ְ��");                
		             }
    	         });
    	       }else{
	              refreshForm("/xgxt/jhzy_gygl.do?method=qszModi&doType=Save");;   
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

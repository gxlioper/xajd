<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function getQsh(){
			jQuery.setAjax({async:false});

			jQuery.getJSON('',{lddm:jQuery('#lddm').val()},function(){
				
			});

			jQuery.setAjax({async:true});
		}

		function saveRzxx(){
			if(jQuery('#xh').val()==""){
				alertError("��ѡ����סѧ����ѧ�ţ�");
				return false;
			}
			if(jQuery('#rzsj').val()==""){
				alertError("��ѡ����סʱ�䣡");
				return false;
			}
			if(jQuery('#xb').val()!=jQuery('#cwxb').val()){
				alertError("��λ�Ա���ѧ���Ա�һ�£��޷���ס��");
				return false;
			}
			var comfirmValue="";

			if(jQuery('#xydm').val()=="" || jQuery('#nj').val()==""){
				alertError("��סѧ�����꼶��<bean:message key="lable.xsgzyxpzxy" />��Ϣ���������޷���ס��");
				return false;
			}else if(jQuery('#cwxydm').val()=="" && jQuery('#cwnj').val()==""){
				comfirmValue="�ô�λδ���䣬ѧ����סʱϵͳ���Զ�����λ������ѧ������<bean:message key="lable.xsgzyxpzxy" />�꼶��ȷ����ס��";
			}else if((jQuery('#xydm').val()!=jQuery('#cwxydm').val()) 
					||(jQuery('#nj').val()!=jQuery('#cwnj').val())){
				comfirmValue="��סѧ�����꼶<bean:message key="lable.xsgzyxpzxy" />�봲λ�������꼶<bean:message key="lable.xsgzyxpzxy" />��һ�£�ѧ����סʱϵͳ���Զ�����λ�������޸�Ϊ��סѧ�����꼶<bean:message key="lable.xsgzyxpzxy" />��ȷ����ס��";
			}else{
				comfirmValue="ȷ����ס��";
			}
			confirmInfo(comfirmValue, function(tag){
				if(tag == 'ok'){
					document.forms[0].action = "gyglnew_cwgl.do?method=cwwhModi&doType=ruzhu";
					document.forms[0].submit();
				}
			});
		}
		
		function getStu(){
			var url = 'gyglnew_cwgl.do?method=getStuInfo';
			if($('cwxb')){
				url += '&xb='+encodeURI(encodeURI($('cwxb').value));
			}
			
			if($('cwxydm')){
				url += '&xydm='+$('cwxydm').value;
			}
			
			if($('cwnj')){
				url += '&nj='+$('cwnj').value;
			}
			
			//showTopWin(url,800,600);
			showDialog('', 800,500,url);
		}
		
		
	</script>
</head>
<body>
	
	<html:form action="/gyglnew_cwgl" method="post">
		<input type="hidden" id="refreshParent" value="true"/>
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>	
		<input type="hidden" id="tableName" name="tableName" value="view_xg_gygl_new_wzsxsxx"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh"/>
		<input type="hidden" id="url" name="url" value="gyglnew_cwgl.do?method=cwwhModi"/>
		<%--
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ��Ϣ�޸�</a>
			</p>
		</div>	
		<div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>��ʾ��</span></h3>
	       <p>�����Һ��ڱ�¥�����Ѵ��ڣ�<br/></p>
	   	</div>	
		--%>
		
		<div class="tab" style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��λ��Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>¥������
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					${rs.ldmc }
				</td>
				
				<th  width="16%">
					<font color="red">*</font>���Һ�				
				</th>
				<td  width="34%">
					${rs.qsh }
				</td>
			</tr>

			<tr>
				<th>��λ��</th>
				<td>
					${rs.cwh }
				</td>
				<th>�����Ա�</th>
				<td>
					${rs.qsxb }
					<input type="hidden" name="cwxb" id="cwxb" value="${rs.qsxb }"/>
				</td>
			</tr>
			<tr>
				<th>����<bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc }
					<input type="hidden" name="cwxydm" id="cwxydm" value="${rs.xydm }"/>
				</td>
				<th>�����꼶</th>
				<td>
					${rs.nj }
					<input type="hidden" name="cwnj" id="cwnj" value="${rs.nj }"/>
				</td>
			</tr>
			<tr>
				<th>ѧ��</th>
				<td>
					<input type="text" name="yc" value="" style="display: none" />	
					<input type="text" name="xh" value="<bean:write name="stuInfo" property="xh"/>" 
						readonly="readonly" id="xh" />	
					<input  style="display: none" onclick="refreshForm('gyglnew_cwgl.do?method=cwwhModi');" id="disbutton"/>						
					<button type="button" onclick="getStu();" class="btn_01" id="buttonFindStu">
						ѡ��
					</button>					
				</td>
				<th>����</th>
				<td>
					${stuInfo.xm }
				</td>
			</tr>
			<tr>			
				<th>�Ա�</th>
				<td>
					${stuInfo.xb }
					<input type="hidden" name="xb" id="xb" value="${stuInfo.xb }"/>				
				</td>
				<th>�꼶</th>
				<td>
					${stuInfo.nj }
					<input type="hidden" name="nj" id="nj" value="${stuInfo.nj }"/>					
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${stuInfo.xymc }
					<input type="hidden" name="xydm" id="xydm" value="${stuInfo.xydm }"/>				
				</td>
				<th>רҵ</th>
				<td>
					${stuInfo.zymc }
					<input type="hidden" name="zydm" id="zydm" value="${stuInfo.zydm }"/>
					<%--<html:text name="stuInfo" property="zymc" readonly="readonly"></html:text>--%>
				</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>
					${stuInfo.bjmc }
					<%--<html:text name="stuInfo" property="bjmc" readonly="readonly"></html:text>--%>		
					<input type="hidden" name="bjdm" id="bjdm" value="${stuInfo.bjdm }"/>				
				</td>
				<th><font color="red">*</font>��סʱ��</th>
				<td>
					<input type="text" id="rzsj" name="rzsj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>					
				</td>
			</tr>
			<tr >
								<th >
									��סԭ��
								</th>
								<td>
									<html:select property="rzyy" styleId="rzyy">
										<html:optionsCollection name="rzyylist" label="rzyymc" value="rzyydm"/>
									</html:select>
								</td>
							</tr>
			<tr>
				<th>
					��ע
					<br /><font color="red">(������500����)</font>
				</th>
				<td colspan="3" width="84%">
					<html:textarea property='bz' style="width:95%" styleId="bz" rows='7' value="${rs.bz}" onblur="chLeng(this,500);"/>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<table class="formlist" width="95%">
					<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave" onclick="saveRzxx();return false;">����</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">			
			showAlert("����ɹ�",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
			
		</script>
	</logic:present>
</body>
</html>

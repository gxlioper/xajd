<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="initCheck();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>

		<html:form action="/csmz_sztz.do?method=tzxm_sh" method="post">
			<input type="hidden" name="pkValue" id="pkValue"
				value="<bean:write name="pkValue" scope="request"/>">
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
 				<tfoot>
			      <tr>
			      	<td colspan="4">
			          <div class="btn">
			             <button name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>
			          </div>
			          </td>
			      </tr>
			    </tfoot>
					<div align="right" class="btn">
						
						<logic:equal value="up" name="view">
							<button type="button" id="up" value="��һ��" disabled="true">��һ��</button>
						</logic:equal>
						<logic:notEqual value="up" name="view">
							<button type="button" id="up" value="��һ��"
								onclick="changerecord('up','/xgxt/csmz_sztz.do?method=tzxmShView');">��һ��</button>
						</logic:notEqual>
						&nbsp; &nbsp;
						<logic:equal value="next" name="view">
							<button type="button" id="next" value="��һ��" disabled="true">��һ��</button>
						</logic:equal>
						<logic:notEqual value="next" name="view">
							<button type="button" id="next" value="��һ��"
								onclick="changerecord('next','/xgxt/csmz_sztz.do?method=tzxmShView');">��һ��</button>
						</logic:notEqual>
						&nbsp; &nbsp;&nbsp; &nbsp;
						<logic:equal value="true" name="sel">
							<button type="checkbox" id="selected" onclick="shot(this);"
								checked="true" >ѡ��</button>
						</logic:equal>
						<logic:notEqual value="true" name="sel">
							<input type="checkbox" id="selected" onclick="shot(this);" />&nbsp;ѡ��
					</logic:notEqual>
					</div>
					<tr style="height:22px">
						<td colspan="4" align="center">
							������չ��Ŀ�걨���
						</td>
					</tr>
				<tbody>
				<tr style="height:22px">
					<th align="right" width="15%">
						ѧ��
					</th>
					<td align="left" width="35%">
						<bean:write name="rs" property="xn" />
					</td>
					<th align="right" width="15%">
						ѧ��
					</th>
					<td align="left" width="35%">
						<bean:write name="rs" property="xqmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��Ŀ����
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="xmmc" />
					</td>
					<th align="right">
						������Ŀ
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="kmm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��Ŀ����
					</th>
					<td align="left">
						<bean:write name="rs" property="xmjb" />
					</td>
					<th align="right">
						���벿��
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="bmmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						����ʱ��
					</th>
					<td align="left">
						<bean:write name="rs" property="zbsj" />
					</td>
					<th align="right">
						��֯��λ
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="zzdw" />
					</td>
				</tr>
				<logic:equal value="11417" name="xxdm">
					<!-- �������ϴ�ѧѧУ���� -->
					<tr valign="middle">
						<th align="right">
							��Ŀ������
							<br>
							(�걨��)
						</th>
						<td align="left">
							<bean:write name="rs" property="xmsbr" />
						</td>
						<th align="right">
							��������
						</th>
						<td align="left">
							<bean:write name="rs" property="szbm" />
						</td>
					</tr>
				</logic:equal>
				<tr style="height:22px">
					<th align="right">
						��Ŀ����
					</th>
					<td align="left" colspan="3" nowrap>
						<textarea rows="8" cols="80" readonly="true" type="_moz"><bean:write name="rs" property="xmms" /></textarea>

					</td>
				</tr>
				</tbody>
			</table>
			</div>
			<div class="formbox">
			<h3 class="datetitle_01">
			    <span>
			    	��Ŀ����&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			    </span>
			</h3>
			
			 <table summary="" class="dateline" align="" width="100%">
				<thead>
					<tr>
						<td align="center">
							<div class="btn"align="right">
								<button id="xxbut" onclick="showHide()">
									����
								</button>
							</div>
						</td>
					</tr>
				</thead>
				<tr id="ly">
					<td>
						<fieldset>
							<logic:empty name="rsJx">
								<br />
								<br />
								<p align="center">
									δ�ҵ��κμ�¼��
								</p>
							</logic:empty>
							<logic:notEmpty name="rsJx">
								<legend>
									��¼����
									<bean:write name="reNum" />
								</legend>

								 <table summary="" class="dateline" align="" width="99%">
									<thead>
										<tr align="center">
											<th>
												������
											</th>
											<th>
												${fsclin}
											</th>
										</tr>
									</thead>
									<logic:iterate name="rsJx" id="s">
										<tr style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty>
						</fieldset>
					</td>
				</tr>
			</table>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
	         	alert("�����ɹ���");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	         </script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
	</body>
	<script type="text/javascript">
		function showHide(){		
		   if(document.getElementById('ly').style.display==''){
		      document.getElementById('ly').style.display='none';
		      document.getElementById('xxbut').value='��ʾ'
		   }else{
		      document.getElementById('ly').style.display='';
		      document.getElementById('xxbut').value='����'
		   }		
		}		
</script>
</html>

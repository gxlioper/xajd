<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript">
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function tipsAndSave(){
			var zgh = $('zgh').value;
			if(zgh != ""){			
				BatAlert.showTips("���ڱ��棬���Ժ�");
				saveData('xysf_dyjsgl.do?method=addDyjs&doType=save','zgh');
			}else{
				alert("�뽫��\"*\"����Ŀ����������");
			}
		}
	</script>
</head>
<body>
	
	<html:form action="xysf_dyjsgl.do" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ʦ�Ź��� - ������ʦ����</a>
				</p>
			</div>
			
		<input type="hidden" id="url" name="url"
			value="xysf_dyjsgl.do?method=addDyjs" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��ʦ��Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>ְ����
					</th>
					<td align="left" width="30%">
						<html:text property="save_zgh" styleId="zgh" readonly="true"
							onchange="checkXhExists($('getStuInfo').value);"
							onkeypress="autoFillTeaInfo(event.keyCode)" />
						<button type="button" onclick="showTopWin('/xgxt/xysf_dyjsgl.do?method=getTeaInfo',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
					
				<th width="16%">
					<div align="right">
						����
					</div>
				</th>
				<td width="34%">
					${rs.xm }
				</td>
			</tr>
		
			<tr>
				<th>
					<div align="right">
						�Ա�
					</div>
				</th>
				<td>
					${rs.xb }
				</td>
				
				<th>
					<div align="right">
						ְ��
					</div>
				</th>
				<td>
					${rs.zwmc }
				</td>
			</tr>
			
			<tr>
				<th>
					<div align="right">
						������
					</div>
				</th>
				<td>
					${rs.bmmc }
				</td>
				
				<th>
					<div align="right">
						 ѧ��
					</div>
				</th>
				<td>
					${rs.xl }
				</td>
				
			</tr>
			<tr>
				<th>
					<div align="right">
						����
					</div>
				</th>
				<td>
					${rs.mzmc }
				</td>
				
				<th>
					<div align="right">
						��������
					</div>
				</th>
				<td>
					${rs.csrq }
				</td>
			</tr>
	
			<tr align="left" style="height:22px">
							<th align="right">
								��ע
								<br />
								<font color="red">(������400����)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_bz' style="width:99%"
									rows='8' onblur="checkLen(this,400)"/>
							</td>
			</tr>
			</tbody>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" onclick="tipsAndSave()">����</button>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>

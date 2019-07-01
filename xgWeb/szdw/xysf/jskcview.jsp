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
	<script type="text/javascript" src="js/szdw/jskcap.js"></script>
	<script type="text/javascript">
		
		function tipsAndSave(){
			BatAlert.showTips("���ڱ��棬���Ժ�");
			saveData('xysf_dyjsgl.do?method=jskcViewAndModi&doType=save','zgh');
		}
	</script>
</head>
<body>
	
	<html:form action="xysf_dyjsgl.do" method="post">

	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>
					<logic:equal value="view" name="operation">������ʦ�Ź��� - ��ʦ�γ̵����鿴</logic:equal>
					<logic:equal value="modi" name="operation">������ʦ�Ź��� - ��ʦ�γ̵����޸�</logic:equal>
				</a>
			</p>
		</div>
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		<input type="hidden" name="save_id" value="${param.pkValue }"/>
		
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
						<html:text property="save_zgh" styleId="zgh" readonly="true" value="${rs.zgh}"/>
					</td>
					
				<th width="20%">
					<div align="right">
						����
					</div>
				</th>
				<td width="30%">
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
						�ڿ�ʱ��
					</div>
				</th>
				<td>
					<input type="text" onclick="time(this.id)" onblur="dateFormatChg(this)"  id="skdj" name="save_sksj" value="${rs.sksj }"/>
				</td>
				
				<th>
					<div align="right">
						 �ڿεص�
					</div>
				</th>
				<td>
					<input type="text" name="save_skdd" value="${rs.skdd }"/>
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						����
					</div>
				</th>
				<td>
					<input type="text" name="save_zt" value="${rs.zt }"/>
				</td>
				
				<th>
					<div align="right">
						���ģ
					</div>
				</th>
				<td>
					<input type="text" name="save_hdgm" value="${rs.hdgm }"/>
				</td>
			</tr>
	
			<tr align="left" style="height:22px">
							<th align="right">
								�γ̱�ע
								<br />
								<font color="red">(������200����)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_bz' style="width:400px" value="${rs.kcbz}"
									rows='8' onblur="checkLen(this,200)"/>
							</td>
			</tr>
			</tbody>
			
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:equal value="modi" name="operation">
			          		<button type="button" name="�ύ" onclick="tipsAndSave()">����</button>
			         	</logic:equal>
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

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
</head>
<body>
	<html:form action="ntzy_fdyfk.do" method="post">
	
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>${title }</a>
		</p>
	</div>
	
	<div class="tab">
		<table class="formlist" width="99%">
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
						ְ����
					</th>
					<td align="left" width="30%">
						<input type="hidden" name="save_zgh" value="${rs.zgh }"/>
						${rs.zgh }
					</td>
					
				<th width="16%">
					<div align="right">
						����
					</div>
				</th>
				<td width="34%" align="left">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						�Ա�
					</div>
				</th>
				<td align="left">
					${rs.xb }
				</td>
				
				<th>
					<div align="right">
						��������
					</div>
				</th>
				<td align="left">
					${rs.csrq }
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						����
					</div>
				</th>
				<td align="left">
					${rs.mzmc }
				</td>
				
				<th>
					<div align="right">
						����
					</div>
				</th>
				<td align="left">
					${rs.sfmc }
				</td>
			</tr>
			
			<tr>
				<th>
					<div align="right">
						���ڲ���
					</div>
				</th>
				<td align="left">
					${rs.bmmc }
				</td>
				
				<th>
					<div align="right">
						 ѧ��
					</div>
				</th>
				<td align="left">
					${rs.xl }
				</td>		
			</tr>
			
			<tr>
				<th>
					<div align="right">
						������ò
					</div>
				</th>
				<td align="left">
					${rs.zzmm }
				</td>
				
				<th>
					<div align="right">
						��ҵԺУ
					</div>
				</th>
				<td align="left">
					${rs.xl }
				</td>		
			</tr>
			
			<tr>
				<th>
					<div align="right">
						ְ��
					</div>
				</th>
				<td>
					${rs.zwmc }
				</td>
				
				<th>
					<div align="right">
						����������
					</div>
				</th>
				<td>
					${rs.fdyzmc }
				</td>
			</tr>
			</tbody>
			</table>
			<br/>
			
			<table class="formlist" width="100%">
				<thead><tr><th colspan="4"><span>��ѵ��Ϣ</span></th></tr></thead>
				<tbody>
				<tr>
					<th align="right" width="20%">
						<font color="red">*</font>��ѵ��Χ
					</th>
					<td align="left" width="30%">
						<html:select property="save_pxfw" styleId="pxfw">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="pxfwList" property="xmmc" labelProperty="xmmc"/>
						</html:select>
					</td>

					<th width="16%">
						<div align="right">
							<font color="red">*</font>��ѵ����
						</div>
					</th>
					<td width="34%" align="left">
						<html:select property="save_pxlx" styleId="pxlx">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="pxlxList" property="xmmc" labelProperty="xmmc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
						<font color="red">*</font>��ʼʱ��
					</th>
					<td align="left" width="30%">
						<html:text property="save_kssj" styleId="kssj" readonly="true"
						onclick="showCalendar('kssj','y��mm��dd��');"></html:text>
					</td>
					<th width="16%">
						<div align="right">
							<font color="red">*</font>����ʱ��
						</div>
					</th>
					<td width="34%" align="left">
						<html:text property="save_jssj" styleId="jssj" readonly="true"
						onclick="showCalendar('jssj','y��mm��dd��');"></html:text>
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						��ѵ��Ŀ
					</th>
					<td colspan="3">
						<html:text property="save_pxxm" style="width: 524px"></html:text>
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						��������
						<br />
						<font color="red">(������400����)</font>
					</th>
					<th colspan="3">
						<html:textarea property='save_jtnr' style="width:99%" rows='6'
							onblur="checkLen(this,400)" />
					</th>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						��ע
						<br />
						<font color="red">(������800����)</font>
					</th>
					<td colspan="3">
						<html:textarea property='save_bz' style="width:99%;" rows='6'
							onblur="checkLen(this,800)" />
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div id="btn" class="btn">
			      	  <logic:equal value="yes" name="writeAble">
			          		<button type="button" name="�ύ" 
			          		onclick="saveDataShowTips('ntzy_fdyfk.do?method=fdypxfk&doType=save','pxfw-pxlx-kssj-jssj','���ڴ������ݣ����Ժ�');">����</button>
			          </logic:equal>
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

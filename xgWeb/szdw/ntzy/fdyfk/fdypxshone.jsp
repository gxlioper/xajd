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
			<em>���ĵ�ǰλ��:</em><a>��ǰλ�ã�˼������ - ����Ա��ѵ��Ϣ�������</a>
		</p>
	</div>
	
		<input type="hidden" name="pkValue" value="${param.pkValue }" />
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="4">
						<span>��ʦ��Ϣ</span>
					</td>
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
						������
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
				
				<td>
					<div align="right">
						�������ڣ�
					</div>
				</td>
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
					<td align="right" width="20%">
						��ѵ��Χ
					</td>
					<td align="left" width="30%">
						<input type="hidden" name="save_pxfw" value="${rs.pxfw }"/>
						${rs.pxfw }
					</td>

					<th width="16%">
						<div align="right">
							��ѵ����
						</div>
					</th>
					<td width="34%" align="left">
						<input type="hidden" name="save_pxlx" value="${rs.pxlx }"/>
						${rs.pxlx }
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
							��ʼʱ��
					</th>
					<td align="left" width="30%">
						<input type="hidden" name="save_kssj" value="${rs.kssj }"/>
						${rs.kssj }
					</td>
					<th width="16%">
						<div align="right">
							����ʱ��
						</div>
					</th>
					<td width="34%" align="left">
						<input type="hidden" name="save_jssj" value="${rs.jssj }"/>
						${rs.jssj }
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						��ѵ��Ŀ
					</th>
					<td>
						${rs.pxxm }
					</td>
					
					<th align="right">
						ѧУ���
					</th>
					<td>
						<html:select property="save_xxsh" value="${rs.xxsh}">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						��������
						<br />
						<font color="red">(������400����)</font>
					</th>
					<td colspan="3">
						<html:textarea property='save_jtnr' style="width:99%" rows='6' value="${rs.jtnr}"
							onblur="checkLen(this,400)" readonly="true" />
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						��ע
						<br />
						<font color="red">(������800����)</font>
					</th>
					<td colspan="3">
						<html:textarea property='save_bz' style="width:99%" rows='6' value="${rs.bz}"
							onblur="checkLen(this,800)"  readonly="true"/>
					</td>
				</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div id="btn" class="btn">
			          	<button type="button" name="�ύ" onclick="saveDataShowTips('ntzy_fdyfk.do?method=fdypxshone&doType=save','','���ڴ������ݣ����Ժ�');">����</button>
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

<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("�����ֶ�δ��������");
						return false;
					}
				}
				
				var kssj = eval($('qjkssj').value);
				var jssj = eval($('qjjssj').value);
				
				if(kssj>jssj){
					alertInfo("��ٿ�ʼʱ�䲻�ܴ��ڽ���ʱ�䣡");
					return false;
				}
				
				$('buttonSave').disabled = "disabled";

				jQuery.post('/xgxt/cdty_rcsw_qjgl.do?method=checkQjjl',{xh:jQuery('#xh').val(), 
					xn:jQuery('#xn').val(),xq:jQuery('#xq').val(),qjkssj:jQuery('#qjkssj').val(),qjjssj:jQuery('#qjjssj').val()},function(data){
					if(data&&data!="0"){
						alertInfo("����ټ�¼�����룡");
						return false;
					}else{
						document.forms[0].action = url;
						document.forms[0].submit();
					}
				});
			}
			
		</script>
		
		<style type="text/css">
			talbe{
				border-collapse:collapse;
			}
		</style>
	</head>
	<body>
		<html:form action="/cdty_rcsw_qjgl" method="post">
			<input type="hidden" name="url" value="/cdty_rcsw_qjgl.do?method=xsqjUpdate"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xh" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this);" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);" 
								class="btn_01" id="buttonFindStu" >ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<input type="text" id="xh" name="xh" value="${rs.xh }" readonly="readonly" />
						</td>
					</logic:equal>
					<th>
						����
					</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>
						ѧ��
					</th>
					<td>
						<input type="hidden" name="xn" value="${xn }" id="xn" />
						${xn }
					</td>
					
					<th>
						ѧ��
					</th>
					<td>
						<input type="hidden" name="xq" value="${xq }" id="xq" />
						${xqmc }
					</td>
				</tr>
				<tr>
					<th>
						Ժϵ
					</th>
					<td>
						${rs.xymc }
					</td>
					<th>
						רҵ
					</th>
					<td>
						${rs.zymc }
					</td>
					
				</tr>
				<tr>
					<th>
						�༶
					</th>
					<td>
						${rs.bjmc }
					</td>
					<th>��ͥ��ַ</th>
					<td>${rs.jtdz }</td>
				</tr>
				<tr>
					<th>
						��ϵ��ʽ
					</th>
					<td>
						${rs.sjhm }
						<logic:notEqual value="" name="rs" property="lxdh">
							<logic:notEqual value="" name="rs" property="sjhm">
							/
							</logic:notEqual>
						</logic:notEqual>
						${rs.lxdh }
					</td>
					<th>�ҳ���ϵ��ʽ</th>
					<td>
						<html:text property="jzdh" maxlength="20" styleId="jzdh" onblur="checkPhone(this);"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>��ٿ�ʼʱ��
					</th>
					<td>
						<html:text property="qjkssj" styleId="qjkssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
						onclick="return showCalendar('qjkssj','y-mm-dd');" style="cursor:hand "></html:text>
					</td>
					<th>
						<font color="red">*</font>��ٽ���ʱ��
					</th>
					<td>
						<html:text property="qjjssj" styleId="qjjssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
						onclick="return showCalendar('qjjssj','y-mm-dd');" style="cursor:hand "></html:text>
					</td>
				</tr>
				
				<tr>
					<th>
						<font color="red">*</font>�������
					</th>
					<td>
						<html:text property="qjts" styleId="qjts" maxlength="2" onblur="checkInputData(this);" />��
					</td>
					<th>
						����ڼ��Ƿ�סУ
					</th>
					<td>
						<html:select property="sfzx">
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						������ɼ�ȥ��<br/>
						<font color="red">(����¼��200��)</font>
					</th>
					<td colspan="3">
						<html:textarea property="qjsy" style="width: 95%;word-break:break-all;"
						 onblur="chLeng(this,200);" rows='5' />
					</td>
				</tr>
				<tr>
					<th>
						��ע<br/>
						<font color="red">(����¼��100��)</font>
					</th>
					<td colspan="3"><html:textarea property="bz" style="width: 95%;word-break:break-all;"  onblur="chLeng(this,100);" 
							rows='5' />
					</td>
				</tr>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" name="�ύ" onclick="dataSave('/xgxt/cdty_rcsw_qjgl.do?method=xsqjUpdate&doType=save','xh-qjts-qjkssj-qjjssj')">�ύ</button>
						  </logic:notEqual>
			          </div></td>
			      </tr>
			    </tfoot>
			</table> 
		</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>

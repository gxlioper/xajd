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
			//�ύ����
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("�����ֶ�δ��������");
						return false;
					}
				}
				
				var kssj = eval($('kssj').value);
				var jssj = eval($('jssj').value);
				
				if(kssj>jssj){
					alertInfo("��ʼʱ�䲻�ܴ��ڽ���ʱ�䣡");
					return false;
				}
				
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			//�޸�������Ϣ
			function dataUpdate(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("�����ֶ�δ��������");
						return false;
					}
				}
				
				var kssj = eval($('kssj').value);
				var jssj = eval($('jssj').value);
				
				if(kssj>jssj){
					alertInfo("��ʼʱ�䲻�ܴ��ڽ���ʱ�䣡");
					return false;
				}
				
				$('buttonUpdate').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		</script>
		
		<style type="text/css">
			talbe{
				border-collapse:collapse;
			}
			
			table th{
				width:20%
			}
			table td{
				width:30%
			}
		</style>
	</head>
	<body>
		<html:form action="/rcsw_gzdx_lxgl" method="post">
			<input type="hidden" name="url" value="/rcsw_gzdx_lxgl.do?method=lxsqUpdate"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xh" />
			<input type="hidden" id="pkValue" name="pkValue" value="${rs.xh }"/>
			
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
							<span>��У��Ϣ</span>
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
					
					<th>
						��ϵ�绰
					</th>
					<td>
						${rs.sjhm }
						<logic:notEqual value="" name="rs" property="sjhm">
							<logic:notEqual value="" name="rs" property="lxdh">
							/
							</logic:notEqual>
						</logic:notEqual>
						${rs.lxdh }
					</td>
					
				</tr>
				<tr>
					<th>ԭ���Һ�</th>
					<td>
						<html:text property="qsh" styleId="qsh" maxlength="25" value="${rs.qsh }"></html:text>
					</td>
					<th>
						�Ƿ����ҹ��
					</th>
					<td>
						<html:select property="sfnyf" value="${rs.sfnyf }">
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>��У��ʼʱ��
					</th>
					<td>
						<html:text property="kssj" styleId="kssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this);"
						onclick="return showCalendar('kssj','y-mm-dd');" style="cursor:hand" readonly="true" value="${rs.kssj}"></html:text>
					</td>
					<th>
						<font color="red">*</font>��У����ʱ��
					</th>
					<td>
						<html:text property="jssj" styleId="jssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this);"
						onclick="return showCalendar('jssj','y-mm-dd');" style="cursor:hand" readonly="true" value="${rs.jssj}"></html:text>
						
					</td>
				</tr>
				
				<tr>
					<th>
						<font color="red">*</font>��У����
					</th>
					<td>
						<html:text property="ts" styleId="ts" maxlength="2" onblur="checkInputData(this);" value="${rs.ts}"/>��
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>�ҳ���ϵ��ʽ</th>
					<td colspan="3">
						<html:text property="jzlxfs" styleId="jzlxfs" maxlength="150" style="width:88%" value="${rs.jzlxfs}"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						������Уԭ��<br/>
						<font color="red">(����¼��400��)</font>
					</th>
					<td colspan="3">
						<html:textarea property="lxyy" style="width: 95%;word-break:break-all;"
						 onblur="chLeng(this,400);" rows='8' value="${rs.lxyy}"/>
					</td>
				</tr>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������<logic:equal value="yes" name="issh"><span class="red">�����Ѿ���ˣ��޷��ٽ����޸ģ�</span></logic:equal></div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
						  	<!-- �ж��Ƿ��Ѿ����� -->
							<logic:equal value="yes" name="issq">
								<!-- �ж��Ƿ��Ѿ���ˣ��������ʾ -->
								<logic:equal value="yes" name="issh">
									<button type="button" id="buttonUpdate" disabled="disabled"
									onclick="dataUpdate('/xgxt/rcsw_gzdx_lxgl.do?method=lxsqxgUpdate&opera=update','xh-ts-kssj-jssj')">
										�޸�
									</button>
								</logic:equal>
								<logic:notEqual value="yes" name="issh">
									<button type="button" id="buttonUpdate"
									onclick="dataUpdate('/xgxt/rcsw_gzdx_lxgl.do?method=lxsqxgUpdate&opera=update','xh-ts-kssj-jssj')">
										�޸�
									</button>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="yes" name="issq">
							<button type="button" id="buttonSave" name="�ύ" onclick="dataSave('/xgxt/rcsw_gzdx_lxgl.do?method=lxsqUpdate&doType=save','xh-ts-kssj-jssj')">�ύ</button>
							</logic:notEqual>
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

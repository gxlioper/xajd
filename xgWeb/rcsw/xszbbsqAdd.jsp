<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
	</head>

	<body onload="checkWinType();">
		<html:form action="/rcsw_xszgl.do" method="post">
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
    					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
			<input type="hidden" id="url" name="url" value="/rcsw_xszgl.do?method=xszbbsqAdd" />
			

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button class="button2"
										onclick="saveData('rcsw_xszgl.do?method=xszbbsqAdd&type=save','xh-bblx')">
										�� ��
									</button>
									<button class="button2" onclick="window.close();return false;">
										�� ��
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text  property="save_xh" readonly="readonly" value="${rs.xh }"
									styleId="xh"  onkeypress="autoFillStuInfo(event.keyCode,this);"  />
								<logic:notEqual value="student" name="userOnLine">
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
								<input type="hidden" id="sqsj" name="save_sqsj"
									value="${rs.save_sqsj}" />
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
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
								����
							</th>
							<td>
								${rs.mzmc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								������ò
							</th>
							<td>
								${rs.zzmmmc }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								${rs.csrq }
							</td>
							<th>
								��ѧʱ��
							</th>
							<td>
								${rs.rxrq }
							</td>
						</tr>
						<tr>
							<th>
								���֤��
							</th>
							<td>
								${rs.sfzh }
							</td>
							<th>
								�ֻ�����
							</th>
							<td>
								${rs.sjhm }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����֤��
							</th>
							<td colspan="3">
								<html:select property="save_bblxdm" value="${rs.bblxdm }" styleId="bblx">
									<html:options collection="bblxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:text property="save_sqly" name='rs' value="${rs.sqly }" style="width:95%" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='save_bz' style="width:95%"
									rows='5' onblur="chLeng(this,250)" value="${rs.bz }"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		dialogArgumentsQueryChick();
			 		window.close();
			 	}
			</script>
		</logic:present>
	</body>
</html>

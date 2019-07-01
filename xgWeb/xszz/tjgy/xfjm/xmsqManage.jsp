<%@ page language="java"  pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>

	<body >
		<html:form action="/tjgy_xfjm" method="post">
			
			<button type="button" id="search_go" style="display:none" onclick="refreshForm('tjgy_xfjm.do?method=xmsqManage');"></button>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>			
			<!-- ���� end-->
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ѧ�����Ѿ���ѧ�Ѽ�����Ŀ��<bean:message key="lable.xb" />���ͨ��,����������������Ŀ���뵽�����ѯҳ��鿴��ϸ��Ϣ��
				</p>
				<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->

			<div class="formbox">

				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td onclick="tableSort(this)">
									������Ŀ
								</td>
								<td onclick="tableSort(this)">
									���뿪ʼʱ��
								</td>
								<td onclick="tableSort(this)">
									�������ʱ��
								</td>
								<td>
									����
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="xmsqList">
								<logic:iterate name="xmsqList" id="x">
									<tr onclick="rowOnClick(this)">
										<td>${x.xmmc }</td>
										<td>${x.sqkssj }</td>
										<td>${x.sqjssj }</td>
										<td>
											<logic:notPresent name="x" property="xysh">
												<logic:notEqual value="zsqsj" name="x" property="sfzsqsj">
													<font color="red">��������ʱ����</font>
												</logic:notEqual>
												<logic:equal value="zsqsj" name="x" property="sfzsqsj">
													<button type="button" class="btn_01" onclick="showTopWin('tjgy_xfjm.do?method=xmsq&xmid=${x.xmid }&xh=${x.xh }',600,500)">����</button>
												</logic:equal>
											</logic:notPresent>
											<logic:present name="x" property="xysh">
												<logic:equal value="δ���" name="x" property="xysh">
													<logic:notEqual value="zsqsj" name="x" property="sfzsqsj">
														<font color="red">��������ʱ����</font>
													</logic:notEqual>
													<logic:equal value="zsqsj" name="x" property="sfzsqsj">
														<button type="button" class="btn_01" onclick="showTopWin('tjgy_xfjm.do?method=xmsq&xmid=${x.xmid }&xh=${x.xh }',600,500)">�޸�</button>
													</logic:equal>
												</logic:equal>
												<logic:notEqual value="δ���" name="x" property="xysh">
													<font color="blue"><bean:message key="lable.xb" />���:${x.xysh }</font>
													<logic:equal value="ͨ��" name="x" property="xysh">
														<script defer>
															jQuery('button').attr('disabled',true);
															jQuery('button').attr('class','disabled');
															jQuery('button').text('���ɲ���');
															jQuery('#prompt').css('display','');
														</script>
													</logic:equal>
												</logic:notEqual>
											</logic:present>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
			</div>
			
			<logic:present name="message">
				<script defer="defer">
					alertInfo('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>

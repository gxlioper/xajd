<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/qsdsgl/js/qsdswh.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_qsdswh" method="post" styleId="sqdswhForm">
			<html:hidden property="lddm" value="${qsdsxx.lddm}"/>
			<html:hidden property="qsh" value="${qsdsxx.qsh}"/>
			<html:hidden property="xqfdylxdh" styleId="xqfdylxdh" value="${qsdsxx.xqfdylxdh}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>¥����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >
								¥������
							</th>
							<td>
								${qsdsxx.ldmc}
							</td>
							<th>
								¥���Ա�
							</th>
							<td id="ldxb">
								${qsdsxx.ldxb}
							</td>
						</tr>
						
						<tr>
							<th>
								¥������
							</th>
							<td id="ldcs">
								${qsdsxx.ldcs}		
							</td>
							<th>
								¥����ʼ��
							</th>
							<td id="qsch">
								${qsdsxx.qsch}
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ�0��
							</th>
							<td id="sfhlc">
								${qsdsxx.sfhlc}
							</td>
						</tr>
					</tbody>

					<thead>
						<tr>
							<th colspan="4">
								<span>���ҵ�ʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								���
							</th>
							<td>
								${qsdsxx.ch}
							</td>
							<th>
								���Һ�
							</th>
							<td>
								${qsdsxx.qsh}
							</td>
						</tr>
						<tr>
							<th>
								���ҳ�
							</th>
							<td>
								${qszInfo.qszxm }
							</td>
							<th>
								���ҳ���ϵ��ʽ
							</th>
							<td>
								${qszInfo.qszlxdh }
							</td>
						</tr>
						
						<tr>
							<th width="18%">
								<span class="red">*</span>��ʦְ����
							</th>
							<td width="32%">
								<html:text property="zgh" styleId="zgh" styleClass="text_nor" readonly="true" style="width:110px;" value="${qsdsxx.zgh}"/>
								<button onclick="showDialog('��ʦѡ��',680,480,'szdw_fdyjtff.do?method=showFdysNotF5');return false;" class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<th width="18%">
								��ʦ����
							</th>
							<td width="32%" id="dsxm">
								${qsdsxx.dsxm}
							</td>
						</tr>
						
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td id="dslxdh">
								${qsdsxx.lxdh}
							</td>
							<th>
								��λ
							</th>
							<td id="dsbmmc">
								${qsdsxx.dw}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th>
								ѧ������Ա
							</th>
							<td>
								<html:text property="xqfdyxm" styleId="xqfdyxm" styleClass="text_nor" style="width:110px;" value="${qsdsxx.xqfdyxm}" readonly="true"/>
								<button onclick="showDialog('ѧ������Աѡ��',680,480,'szdw_fdyjtff.do?method=showFdysAnother');return false;" class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<th>
								����Ա��ϵ�绰
							</th>
							<td width="32%" name="xqfdylxdh">
							${qsdsxx.xqfdylxdh}
							</td>
						</tr>
						<tr>
							<th>
								���ڿ�ʼʱ��
							</th>
							<td>
								<html:text property="rqkssj" styleId="rqkssj" onfocus="showCalendar('rqkssj','y-mm-dd',true,'rqjssj');" value="${qsdsxx.rqkssj}"></html:text>
							</td>
							<th>
								���ڽ���ʱ��
							</th>
							<td>
								<html:text property="rqjssj" styleId="rqjssj" onfocus="showCalendar('rqjssj','y-mm-dd',false,'rqkssj');" value="${qsdsxx.rqjssj}"></html:text>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(��100��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="bz" styleId="bz" style="width:95%;" rows="5" value="${qsdsxx.bz}"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="save_button" type="button"
										onclick="updateQsdsxx();">
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>


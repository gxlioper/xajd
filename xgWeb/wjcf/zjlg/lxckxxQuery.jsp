<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
	function sendinfo() {
		var pkValue = curr_row.getElementsByTagName("input")[0].value;
		window.close();
		window.dialogArguments.document.forms[0].action="wjcf_zjlg_addLxckxx.do?pkValue=" + pkValue;
		window.dialogArguments.document.forms[0].submit();
		return;
	}
</script>
	</head>
	<body onload="">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - �����У�쿴 - ����ά��</a>
			</p>
		</div>


		<html:form action="/wjcfzjlgwh.do" method="post">
			<input type="hidden" name="operType" value="query" />
			<input type="hidden" name="failInfo" id="failInfo"
				value="${failinfo }" />

			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm"></html:text>
								</td>
								<th>
									��У�쿴���ʱ��
								</th>
								<td>
									<html:text property="lxcksj" styleId="lxcksj"
										style="width:100px"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('wjcf_zjlg_lxckxxquery.do')">
											��ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> <logic:notEmpty name="rs">
								<br/>
								<font color="blue">��ʾ��Ĭ�ϲ�ѯ��������������У�쿴ʱ��Ϊ��ǰʱ�������;Ҳ������������У�쿴ʱ����в�ѯ(�ꡢ�¡��վ���)</font>
							</logic:notEmpty> </span>
					</h3>
					<logic:notEmpty name="rs">
						<table class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" length="9">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onmousemove="rowOnClick(this)" style="cursor:hand;"
										align="center" ondblclick="sendinfo()">
										<input type="hidden" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="1" length="9">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
	</body>
	</html>
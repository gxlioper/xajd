<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
function modi(obj) {
	var guid = obj.parentNode.parentNode.id;
	
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=rwsq&type=modi&pkVal="+guid;
    document.forms[0].submit();
}
function del(obj) {
	var guid = obj.parentNode.parentNode.id;
	
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=rwDate&go=del&guid="+guid;
    document.forms[0].submit();
}
function add() {
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=rwsq";
    document.forms[0].submit();
}
function query(obj) {
	var guid = obj.parentNode.parentNode.id;
	
	document.forms[0].action="/xgxt/zzlgdx_rcsw.do?method=rwQuery&pkVal="+guid;
    document.forms[0].submit();
}
</script>
</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ����� - ���� - ������Ϣ</a>
			</p>
		</div>
	
		<html:form action="/zzlgdx_rcsw.do?method=rwDate" method="post">
		
		
		<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> </span>
					</h3>

						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
									<td nowrap>
										������<input  value="����" name="button_print"
													onclick="add();" />
									</td>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr style="cursor:hand;"
										id=<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>>
										<logic:iterate id="v" name="s" offset="1" length="4">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="shjg" name="s" offset="5" length="1">
											<td align=center>
												<bean:write name="shjg" />
											</td>
										</logic:iterate>
										<logic:iterate id="shyj" name="s" offset="6" length="1">
											<td align=center>
												<bean:write name="shyj" />
											</td>
										</logic:iterate>
										<td align=center>
											<logic:equal name="shjg" value="δ���">
												<a onclick="javascript:modi(this);">�޸�</a>&nbsp;/&nbsp;
												<a onclick="javascript:del(this);">ɾ��</a>&nbsp;/&nbsp;
											</logic:equal>
											<a onclick="javascript:query(this);">�鿴</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
						</table>
				</div>
			</div>
		
		</html:form>
	</body>
</html>

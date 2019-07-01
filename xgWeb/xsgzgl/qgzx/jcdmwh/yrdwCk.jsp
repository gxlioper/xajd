<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="comm/editor/kindeditor-min.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		jQuery(function(){
            var dwlb = ${model.dwlb};
            if(dwlb == "01"){
                jQuery(".xnxx").attr("style","");
                jQuery(".xwxx").attr("style","display:none");
            } else {
                jQuery(".xnxx").attr("style","display:none");
                jQuery(".xwxx").attr("style","");
            }
		})
		</script>
	</head>
	<body >

		<html:form styleId="qgzxJcdmwhForm" action="/qgzx_jcdmwh_ajax" method="post" onsubmit="return false;">
			<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4" style="width: 25%">
								<span>���˵�λ����ά��</span>
							</th>
						</tr>
					</thead>
				<tbody>
				<tr>
					<th>
						��λ���
					</th>
					<td colspan="3">
						<input type="radio" name="dwlb" value="01" <logic:equal name="model" property="dwlb" value="01">checked="checked" </logic:equal> disabled>У�ڵ�λ</input>
						<input type="radio" name="dwlb" value="02" <logic:equal name="model" property="dwlb" value="02">checked="checked" </logic:equal> disabled>У����ҵ</input>
					</td>
				</tr>
				</tbody>
					<tbody>
					<tr class="xnxx" >
						<th>
							ѧԺ(����)
						</th>
						<td>
							<select name="xydm" id="xydm" disabled>
								<option value=""></option>
								<logic:iterate id="xy" name="xyList">
									<option value="${xy.xydm}" <logic:equal name="model" property="xydm" value="${xy.xydm}">selected="selected"</logic:equal>>${xy.xymc}</option>
								</logic:iterate>
							</select>
						</td>
						<th>����</th>
						<td>
							${model.zgh}
						</td>
					</tr>
					<tr class="xwxx" style="display: none">
						<th>
							��λ����
						</th>
						<td>
							${model.yrdwmc}
						</td>
						<th>
							��ҵ
						</th>
						<td>
							${model.hy}
						</td>
					</tr>
					<tr>
						<th>
							����������
						</th>
						<td id="xmtr">
								${model.xm}
						</td>
						<th>
							��ϵ�绰
						</th>
						<td>
							${model.lxdh}
						</td>
					</tr>
					<tr class="xwxx" style="display: none">
						<th>
							�û���
						</th>
						<td>
							${model.yhm}
						</td>
						<th>
							����
						</th>
						<td>
							${model.mm}
						</td>
					</tr>
					<tr>
						<th>
							�칫�ص�
						</th>
						<td>
							${model.bgdd}
						</td>
						<th>
							�칫�绰
						</th>
						<td>
							${model.bgdh}
						</td>
					</tr>
					<tr>
						<th>
							�����ʼ�
						</th>
						<td>
							${model.dzyx}
						</td>
						<th>
							QQ
						</th>
						<td>
							${model.qq}
						</td>
					</tr>
					<tr>
						<th class="xnxx">
							�걨IP
						</th>
						<td class="xnxx">
							${model.sbip}
						</td>
						<th class="xwxx" style="display: none">
							���֤��
						</th>
						<td class="xwxx" style="display: none">
							${model.sfzh}
						</td>
						<th>
							��������
						</th>
						<td>
							${model.gzsx}
						</td>
					</tr>
					<tr>
						<th>
							��ϵѧ��
						</th>
						<td>
							${model.lxxs}
						</td>
						<th>
							ѧ���ֻ�
						</th>
						<td>
							${model.xssh}
						</td>
					</tr>
					<tr>
						<th class="xnxx">
							ѧԺ�����ţ����</br><span class="red">(������2000����)</span>
						</th>
						<th class="xwxx" style="display: none">
							ѧ��ҵ���</br><span class="red">(������2000����)</span>
						</th>
						<td colspan="3">
							${model.jj}
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�ر�" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>

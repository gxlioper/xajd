<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<logic:equal value="1" name="lxmdwhjl" property="czlx">
									<span>���Ӽ�¼��Ϣ</span>
								</logic:equal>
								<logic:equal value="2" name="lxmdwhjl" property="czlx">
									<span>ԭ��¼��Ϣ</span>
								</logic:equal>
								<logic:equal value="3" name="lxmdwhjl" property="czlx">
									<span>ɾ����¼��Ϣ</span>
								</logic:equal>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="15%">
							ѧ��
						</th>
						<td align="left">
							<a href="javascript:void(0);" class="name" 
						   	   onclick="showDialog('ѧ����ϸ��Ϣ',750,500,'xsxx_tygl.do?method=ckZxsxx&xh=${lxmdwhjl.xh }')"
						       style="margin-left: 1px;"
						 	>${lxmdwhjl.xh }</a>
						</td>
						<th align="right" width="15%">
							����
						</th>
						<td align="left">
							${lxmdwhjl.xm }
						</td>
					</tr>
					<tr>
						<th align="right" width="15%">
							��У��Ŀ����
						</th>
						<td align="left">
							${lxmdwhjl.xmmc }
						</td>
						<th align="right" width="15%">
							��У��ֹʱ��
						</th>
						<td align="left">
							 ${lxmdwhjl.qzsj }
						</td>
					</tr>
					<tr>
						<th align="right" width="15%">
							��У��Ŀ˵��
						</th>
						<td colspan="3">
							${lxmdwhjl.lxxmsm }
						</td>
					</tr>
					<tr>
						<th align="right" width="15%">
							��У���˵��
						</th>
						<td colspan="3">
							${lxmdwhjl.czlx=='1'?lxmdwhjl.xghlxqksm:lxmdwhjl.xgqlxqksm }
						</td>
					</tr>
				</tbody>
				
				<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="15%">
							������
						</th>
						<td align="left">
							${lxmdwhjl.czrmc }
						</td>
						<th align="right" width="15%">
							����ʱ��
						</th>
						<td align="left">
							${lxmdwhjl.czsj }
						</td>
					</tr>
					<tr>
						<th align="right" width="15%">
							��������
						</th>
						<td colspan="3">
							${lxmdwhjl.czlxmc }
						</td>
					</tr>
					<logic:equal value="2" name="lxmdwhjl" property="czlx">
						<tr>
							<th align="right" width="15%">
								��������
							</th>
							<td colspan="3">
								${lxmdwhjl.czxq }
							</td>
						</tr>
					</logic:equal>
				</tbody>
			</table>
		</div>
	</body>
</html>

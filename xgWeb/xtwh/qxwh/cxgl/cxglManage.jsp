<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	
	<body>
		<html:form action="/qxcxManage">
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-Ȩ����ز�ѯ</a>
				</p>
			</div>
			   
			<div>
			 	<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯѡ��</span>
							</th>
						</tr>
					</thead>
					<tbody align="left">
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="yhjscx"/>��ѯ��ɫӵ�е��û�</td>
						</tr>
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="gnjscx"/>��ѯ��������ӵ�еĽ�ɫ</td>
						</tr>
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="jsqxcx"/>��ѯ��ɫӵ�е�Ȩ��</td>
						</tr>
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="yhqxcx"/>��ѯ�û���ӵ�еĹ���ģ��</td>
						</tr>
					</tbody>
					
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
							  <button type="button" name="" onclick="refreshForm('qxcxManage.do?method=cxglManage&doType=view');">��һ��</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
			</div>	
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>

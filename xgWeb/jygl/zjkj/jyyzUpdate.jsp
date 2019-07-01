<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" /></title>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	<html:form action="jygl.do" method="post">
	<input type="hidden" id="url" value="/jygl.do?method=jyyzUpdate"/>
	<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
		</div>
		<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��д������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">
					<div>
						<font color="red">*</font>ѧ��
					</div>
				</th>
				<td width="30%">
					<html:text property="save_xh" styleId="xh" value="${rs.xh }" readonly="true" onkeypress="autoFillStuInfo(event.keyCode,this)"/>	
					<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="button2" id="buttonFindStu">
						&gt;&gt;
					</button>
				</td>
				
				<th width="20%">����</th>
				<td width="30%">${rs.xm }</td>
			</tr>
			<tr>
				<th>������ò</th>
				<td>${rs.zzmmmc }</td>
				<th><bean:message key="lable.xb" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th>�Ƽ����Ե�λ</th>
				<td><html:text property="save_tjdw"></html:text></td>
				<th>Ԯ�����</th>
				<td><html:select property="save_yzjg">
					<html:option value=""></html:option>
					<html:options collection="yzjgList" property="xmdm" labelProperty="xmmc"/>
				</html:select></td>
			</tr>
			<tr>
				<th>����ѧ��<br/>
					<font color="red">(������800����)</font>
				</th>
				<td colspan="3">
					<html:textarea property='save_cyxs'
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,800)" rows='8'/>
				</td>
			</tr>
			<tr>
				<th>��ע<br/>
					<font color="red">(������400����)</font>
				</th>
				<td colspan="3">
					<html:textarea property='save_bz'
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,400)" rows='8'/>
				</td>
			</tr>
			</tbody>
			
			 <tfoot>
			    <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
				         <button class="button2" onclick="saveDataShowTips('jygl.do?method=jyyzUpdate&doType=save',
							'xh','���ڴ������ݣ����Ժ�');">
							����
						 </button>
						 <button onclick="reset()">����</button>
			          </div></td>
			      </tr>
			   </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html>

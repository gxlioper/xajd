<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript">
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function tipsAndSave(){
			var zgh = $('zgh').value;
			if(zgh != ""){			
				BatAlert.showTips("正在保存，请稍后！");
				saveData('xysf_dyjsgl.do?method=addDyjs&doType=save','zgh');
			}else{
				alert("请将带\"*\"的项目输入完整！");
			}
		}
	</script>
</head>
<body>
	<html:form action="xysf_fdykywh.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="url" name="url"
			value="xysf_fdykywh.do?method=kyxxwh" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>辅导员科研信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					<font color="red">*</font>职工号
				</th>
				<td align="left" width="30%">
					<html:text property="save_zgh" styleId="zgh" value="${rs.zgh}" readonly="true"></html:text>
				</td>
					
				<th>
					<div align="right">
						姓名
					</div>
				</th>
				<td width="34%">
					${rs.xm }
				</td>
			</tr>
		
			<tr>
				<th>
					性别
				</th>
				<td>
					${rs.xb }
				</td>
				
				<th>
					职务
				</th>
				<td>
					${rs.zwmc }
				</td>
			</tr>
			
			<tr>
				<th>
					负责部门
				</th>
				<td>
					${rs.bmmc }
				</td>
				
				<th>
					学历
				</th>
				<td>
					${rs.xl }
				</td>
			</tr>
			
			<tr>
				<th>
					<font color="red">*</font>文章名称
				</th>
				<td>
					<html:text property="save_wzmc" styleId="wzmc" value="${rs.wzmc}" onkeyup="chgPkValue(this);"></html:text>
				</td>
				
				<th>
					<font color="red">*</font>发表期刊
				</th>
				<td>
					<html:text property="save_fbqk" styleId="fbqk" value="${rs.fbqk }" onkeyup="chgPkValue(this);"></html:text>
				</td>
			</tr>
			
			<tr>
				<th>
					期刊期数
				</th>
				<td>
					<html:text property="save_qkqs" value="${rs.qkqs }"></html:text>
				</td>
				
				<th>
					期刊级别
				</th>
				<td>
					<html:text property="save_qkjb" value="${rs.qkjb }"></html:text>
				</td>
			</tr>
			</tbody>
			
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button type="button" name="提交" onclick="saveDataShowTips('xysf_fdykywh.do?method=kyxxView&doType=save','zgh-wzmc-fbqk');">保存</button>
		          	  	</logic:notEqual>
		          	  	<button type="button" onclick="Close();return false;">关闭</button>
		          	  </div>
	          		</td>
			      </tr>
				 </tfoot>
		</table>
		</div>
		
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
	</logic:present>
</body>
</html>

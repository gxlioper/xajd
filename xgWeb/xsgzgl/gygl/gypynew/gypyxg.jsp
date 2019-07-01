<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/gygl/gypynew/js/gypy.js"></script>
				<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script>
			jQuery(function(){
				loadLdxx();
			});
		</script>
	</head>
	<body>
		<html:form action="/gypy" method="post" styleId="form">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>修改文明宿舍</a>
			</p>
		</div>
		<html:hidden property="pylx" value="${data.pylx}"/>
		<html:hidden property="xydm" styleId="${data.xydm}"/>
		<html:hidden property="gypyid" value="${data.gypyid}"/>
		<div style='tab'>
			<table class="formlist"  width="95%">
			<tbody>
				<tr>
					<th width="20%" align="right">楼栋：</th>
					<td width="30%">
						${data.ldmc}
					</td>
					<th width="20%">楼层：</th>
					<td width="30%">
						${data.ch}
					</td>
				</tr>
				<tr>
					<th>寝室号：</th>
					<td>
						${data.qsh}
					</td>
					<th>寝室电话：</th>
					<td>
						${data.qsdh}
					</td>
				</tr>
				<tr>
					<th>学年：</th>
					<td>
						${data.xn}
					</td>
					<th>学期:</th>
					<td>
						${data.xqmc}
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>评选理由：</th>
					<td colspan="3">
						<html:textarea styleId="pyly" property="pyly" rows="4" style="width:95%" onblur="checkLength(this,100)" value="${data.pyly}"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="save('gypy.do?method=update&type=save','pyly');">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</table>
		</div>
		</html:form>
	</body>

</html>
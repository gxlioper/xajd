<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript">
<!--
//控制对于页面只有一个文本框,回车提交事件 重写onkeydown事件,注意会覆盖所有的回车事件
		function document.onkeydown() {     
			  var e=event.srcElement;     
			  if(event.keyCode==13) {
				  	return false;     
			  }     
		} 
        //END
</script>
<body>
	<html:form action="/wjcfzjcmwh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：违纪处分 - 处分审核 - 留校察看时间设置
			</div>
		</div>
		<fieldset >
			<br><br>
			<div align="center">
			<table width="95%"  class="tbstyle" >
				<thead>
					<tr height="35">
						<td align="right" width="50%">
							留校察看时间段(天)：
						</td>
						<td width="50%">
							<html:text property="sj" styleId="sj" style="width:50px" maxlength="3" onkeyup="chkIsNum(this)"></html:text>
						</td>
					</tr>
				</thead>
				<logic:equal value="yes" name="writeAble">
				<tr height="35">
					<td align="center" colspan="2">
						<button type="button" class="button2" onclick="refreshForm('wjcf_zjcm_lxcksjsz.do?act=save')"
							style="width: 60px">
							保 存
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="refreshForm('wjcf_zjcm_lxcksjsz.do')"
							style="width: 60px">
							重 置 
						</button>
					</td>
				</tr>
				</logic:equal>
			</table>
			</div>
		</fieldset>
		<logic:present name="inserted">
		<logic:equal value="true" name="inserted">
			<script>
				alert('保存成功!');
			</script>
		</logic:equal>
		<logic:equal value="false" name="inserted">
			<script>
				alert('保存失败!');
			</script>
		</logic:equal>
		</logic:present>
		</html:form>
	</body>

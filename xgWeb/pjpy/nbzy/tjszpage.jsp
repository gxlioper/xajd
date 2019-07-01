<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/nbzy/nbzyJs.js"></script>
<body onload="">
	<html:form action="/pjpynbzywh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 参数设置 - 条件设置
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="search_go" onclick="refreshForm('pjpy_nbzy_tjsz.do');"/>
    	<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td align="left">
							学年：<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn" onchange="refreshForm('pjpy_nbzy_tjsz.do');">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							&nbsp;&nbsp;
							奖学金:
							<html:select property="jxjdm" styleId="jxjdm" onchange="refreshForm('pjpy_nbzy_tjsz.do');" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
							</html:select>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								条件：<html:select property="tj" styleId="tj">
									<html:option value=""></html:option>
									<html:option value="zysyf">职业素养分</html:option>
									<html:option value="zyjnsyf">职业技能素养分</html:option>
									<html:option value="kcxfzsyf">可持续发展分</html:option>
									<html:option value="zhcpzf">综合测评总分</html:option>
									<html:option value="zk">职业技能与可持发展分之和</html:option>
								</html:select>
								&nbsp;&nbsp;
								---&gt;
								&nbsp;&nbsp;
								<html:text property="bl" styleId="bl" style="width:100px" onblur="ckinpdata(this)"></html:text>
								<font color="red">(排名)</font>
								&nbsp;&nbsp;
								<button id="btn_save" class="button2" onclick="savetj()">保存条件</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<div id="tmpdiv"></div>
				</div>
				<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button class="button2" id="btn_tg" style="width:80px"
								onclick="deldata1('tjDelres.do');">
								删除条件
							</button>
						</div>
					</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="deleted">
	 	<logic:equal value="yes" name="deleted">
	 		<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="deleted">
	 		<script>
	 			alert('操作失败!');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveZxdkSq(){
			if($("xh") && $("xh").value==""){
				 $("xh").focus();
				alertInfo("ѧ�Ų���Ϊ�գ�");
				return false;
			}
			
			jszje();
			
			if($("dkzje").value=="0"){
				alertInfo("����д������Ϣ!");
				return false;
			}
			var sfysq=$("sfysq").value;
			var url="zxdk_xnmz.do?method=zxdkSq&doType=save&sfysq="+sfysq;
			refreshForm(url);
		}
		
		function updateZxdkSq(){
			
			var url="zxdk_xnmz.do?method=zxdkModi&doType=save";
			refreshForm(url);
		}
		
		function displayTbody(id){
			if($(id).style.display=="none"){
				$(id).style.display="";
			}else{
				$(id).style.display="none"
			}
		}

		function jszje() {
			var dkzje=jQuery("#dkzje").val();dkzje=0;
			jQuery("#tbody_dkxx").find("input").each(function(){
				if(jQuery(this).attr("name")!="dkzje"){
					dkzje=parseInt(dkzje)+parseInt(jQuery(this).val() != "" ? jQuery(this).val() : 0);
				}
			});
			jQuery("#dkzje").val(dkzje);
		}
		
<%--		function jszje(){--%>
<%--			$("onexnzje").value=0;--%>
<%--			$("twoxnzje").value=0;--%>
<%--			$("threexnzje").value=0;--%>
<%--			$("fourxnzje").value=0;--%>
<%--			$("fivexnzje").value=0;--%>
<%--		--%>
<%--			if($("onexnxfje").value!=""){--%>
<%--				$("onexnzje").value=eval($("onexnzje").value)+eval($("onexnxfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("onexnqsfje").value!=""){--%>
<%--				$("onexnzje").value=eval($("onexnzje").value)+eval($("onexnqsfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("onexnshf").value!=""){--%>
<%--				$("onexnzje").value=eval($("onexnzje").value)+eval($("onexnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			--%>
<%--			if($("twoxnxfje").value!=""){--%>
<%--				$("twoxnzje").value=eval($("twoxnzje").value)+eval($("twoxnxfje").value);--%>
<%--			}--%>
<%--	--%>
<%--			if($("twoxnqsfje").value!=""){--%>
<%--				$("twoxnzje").value=eval($("twoxnzje").value)+eval($("twoxnqsfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("twoxnshf").value!=""){--%>
<%--				$("twoxnzje").value=eval($("twoxnzje").value)+eval($("twoxnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			--%>
<%--			if($("threexnxfje").value!=""){--%>
<%--				$("threexnzje").value=eval($("threexnzje").value)+eval($("threexnxfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("threexnqsfje").value!=""){--%>
<%--				$("threexnzje").value=eval($("threexnzje").value)+eval($("threexnqsfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("threexnshf").value!=""){--%>
<%--				$("threexnzje").value=eval($("threexnzje").value)+eval($("threexnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("fourxnxfje").value!=""){--%>
<%--				$("fourxnzje").value=eval($("fourxnzje").value)+eval($("fourxnxfje").value);--%>
<%--			}--%>
<%--			if($("fourxnqsfje").value!=""){--%>
<%--				$("fourxnzje").value=eval($("fourxnzje").value)+eval($("fourxnqsfje").value);--%>
<%--			}--%>
<%--			if($("fourxnshf").value!=""){--%>
<%--				$("fourxnzje").value=eval($("fourxnzje").value)+eval($("fourxnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("fivexnxfje").value!=""){--%>
<%--				$("fivexnzje").value=eval($("fivexnzje").value)+eval($("fivexnxfje").value);--%>
<%--			}--%>
<%--			if($("fivexnqsfje").value!=""){--%>
<%--				$("fivexnzje").value=eval($("fivexnzje").value)+eval($("fivexnqsfje").value);--%>
<%--			}--%>
<%--			if($("fivexnshf").value!=""){--%>
<%--				$("fivexnzje").value=eval($("fivexnzje").value)+eval($("fivexnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			$("dkzje").value=0;--%>
<%--			if($("onexnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("onexnzje").value);--%>
<%--			}--%>
<%--			if($("twoxnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("twoxnzje").value);--%>
<%--			}--%>
<%--			if($("threexnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("threexnzje").value);--%>
<%--			}--%>
<%--			if($("fourxnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("fourxnzje").value);--%>
<%--			}--%>
<%--			if($("fivexnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("fivexnzje").value);--%>
<%--			}--%>
<%--			--%>
<%--		}--%>
		</script>
	</head>
	<body onload="">
		<html:form action="/zxdk_xnmz" method="post">
			<!-- ������ -->
			<input type="hidden" name="url" id="url"
				value="zxdk_xnmz.do?method=zxdkSq" />
			<input type="hidden" name="sfysq" id="sfysq" value='${rs.sfysq}' />
			<!-- �޸�ʱ -->
			<input type="hidden" name="shzt" id="shzt" value='${rs.shzt}' />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<div id="xxPrompt" class="prompt">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							����������Ϣ���޷��ظ����룡
						</p>
						<a class="close" title="����"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:equal>
			</logic:equal>
			<logic:equal name="doType" value="update">
				<logic:equal name="rs" property="sfsh" value="ysh">
					<div id="xxPrompt" class="prompt">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							��ѧ������ѧ����������Ϣ����˲��������޸ģ�
						</p>
						<a class="close" title="����"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:equal>
			</logic:equal>
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button class="button2" id="btn_bc" onclick="saveZxdkSq();return false;" >
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<input type="hidden" name="guid" id="guid" value="${rs.guid }"/>
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button class="button2" id="btn_bc" onclick="updateZxdkSq();return false;">
											�� ��
										</button>
										</logic:notEqual>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead onclick="displayTbody('tbody_jbxx')">
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>

					<tbody id="tbody_jbxx">
						<tr>
							<th style="width:16%">
								<font color="red">*</font>ѧ��
							</th>
							<td style="width:34%">
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<logic:notEqual name="userType"  value="stu">
									<button class="btn_01" id="" onclick="sendXx();return false;">
										ѡ ��
									</button>
									</logic:notEqual>
								</logic:equal>
							</td>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�Ա�
							</th>
							<td style="width:34%">
								${rs.xb }
							</td>
							<th style="width:16%">
								�꼶
							</th>
							<td style="width:34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td id="" style="width:34%">
								${rs.xymc }
							</td>

							<th>
								רҵ
							</th>
							<td id="" style="width:34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�༶
							</th>
							<td style="width:34%">
								${rs.zymc }
							</td>
							<th style="width:16%">
								���֤��
							</th>
							<td style="width:34%">
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.mzmc }
							</td>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								${rs.csrq }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.nl }
							</td>
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��ҵ����
							</th>
							<td style="width:34%">
								${rs.byny }
							</td>
							<th style="width:16%">
								��ϵ�绰
							</th>
							<td style="width:34%">
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								OICQ��
							</th>
							<td style="width:34%">
								${rs.qqhm }
							</td>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ѧ����ͥ��ַ
							</th>
							<td style="width:34%">
								${rs.jtdz }
							</td>
							<th style="width:16%">
								ѧ����ͥ��ϵ�绰
							</th>
							<td style="width:34%">
								${rs.jtdh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ѧ����ͥ��������
							</th>
							<td style="width:34%">
								${rs.jtyb }
							</td>
							<th style="width:16%">
								��ͥ�˾�������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="jtysr"
									onblur="checkInputNum(this)" maxlength="10" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								${rs.hkxz }
							</td>
							<th style="width:16%">

							</th>
							<td style="width:34%">

							</td>
						</tr>

					</tbody>
					<thead onclick="displayTbody('tbody_jtxx')">
						<tr>
							<th colspan="4">
								<span>��ͥ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jtxx">
						<tr>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqxm" maxlength="25" />
							</td>
							<th style="width:16%">
								�������֤��
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqsfzh" maxlength="18"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								���׹�����λ
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqgzdw" maxlength="100" />
							</td>
							<th style="width:16%">
								������ϵ��ʽ
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqlxfs" maxlength="25" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ĸ������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqxm" maxlength="25" />
							</td>
							<th style="width:16%">
								ĸ�����֤��
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqsfzh" maxlength="18"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ĸ�׹�����λ
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqgzdw" maxlength="100" />
							</td>
							<th style="width:16%">
								ĸ����ϵ��ʽ
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqlxfs" maxlength="25" />
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_lxrxx')">
						<tr>
							<th colspan="4">
								<span>��ϵ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_lxrxx">
						<tr>
							<th>
								��ϵ������
							</th>
							<td>
								<html:text name="rs" property="lxrxm" maxlength="25" />
							</td>
							<th>
								��ϵ�˼�ͥ��ַ
							</th>
							<td>
								<html:text name="rs" property="lxrjtzz" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�˹�����λ
							</th>
							<td>
								<html:text name="rs" property="lxrgzdw" maxlength="50" />
							</td>
							<th>
								��ϵ�˹̶��绰
							</th>
							<td>
								<html:text name="rs" property="lxrgddh"
									onkeydown="return onlyNum(this,15)"
									onmousedown="return onlyNum(this,15)" style="ime-mode:disabled" />
							</td>
						</tr>
						<tr>
							<th>
								��ϵ���ƶ��绰
							</th>
							<td>
								<html:text name="rs" property="lxryddh" maxlength="15"
									onkeydown="return onlyNum(this,15)"
									onmousedown="return onlyNum(this,15)" style="ime-mode:disabled" />
							</td>
							<th>

							</th>
							<td>
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_jzrxx')">
						<tr>
							<th colspan="4">
								<span>��֤��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jzrxx">
						<tr>
							<th>
								��֤������
							</th>
							<td>
								<html:text name="rs" property="jzrxm" maxlength="25" />
							</td>
							<th>
								��֤��֤����
							</th>
							<td>
								<html:text name="rs" property="jzrzjh" maxlength="18"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
						</tr>
						<tr>
							<th>
								��֤��֤�����ʹ���
							</th>
							<td>
								<html:select name="rs"  property="jzrzjlxdm" styleId="rychdm">
									<option value=""></option>
									<html:options collection="zjlxList" property="zjlxdm"
										labelProperty="zjlxmc" />
								</html:select>
							</td>
							<th>
								��֤�˼�ͥ��ַ
							</th>
							<td>
								<html:text name="rs" property="jzrdz" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								��֤���������˹�ϵ
							</th>
							<td>
								<html:text name="rs" property="jzrgx" maxlength="20" />
							</td>
							<th>
								��֤���ʱ�
							</th>
							<td>
								<html:text name="rs" property="jzryb" maxlength="20" />
							</td>
						</tr>
						<tr>
							<th>
								��֤�˵绰
							</th>
							<td>
								<html:text name="rs" property="jzrdh" maxlength="20" />
							</td>
							<th>
								��֤�����
							</th>
							<td>
								<html:text name="rs" property="jzryj" maxlength="50" />
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_dkxx')">
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_dkxx">
						<tr>
							<th>
								��һѧ��ѧ�Ѵ���
							</th>
							<td>
								<html:hidden name="rs" property="onexnzje" styleId="onxnzje" />
								<html:text name="rs" property="onexnxfje" styleId="onexnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								��һѧ�����ҷѴ���
							</th>
							<td>
								<html:text name="rs" property="onexnqsfje" styleId="onexnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								��һѧ������Ѵ���
							</th>
							<td>
								<html:text name="rs" property="onexnshf" styleId="onexnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								�ڶ�ѧ��ѧ�Ѵ���
							</th>
							<td>
								<html:hidden name="rs" property="twoxnzje" styleId="twoxnzje" />
								<html:text name="rs" property="twoxnxfje" styleId="twoxnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								�ڶ�ѧ�����ҷѴ���
							</th>
							<td>
								<html:text name="rs" property="twoxnqsfje" styleId="twoxnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								�ڶ�ѧ������Ѵ���
							</th>
							<td>
								<html:text name="rs" property="twoxnshf" styleId="twoxnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>
								����ѧ��ѧ�Ѵ���
							</th>
							<td>
								<html:hidden name="rs" property="threexnzje" 
									styleId="threexnzje" />
								<html:text name="rs" property="threexnxfje" styleId="threexnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								����ѧ�����ҷѴ���
							</th>
							<td>
								<html:text name="rs" property="threexnqsfje" styleId="threexnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								����ѧ������Ѵ���
							</th>
							<td>
								<html:text name="rs" property="threexnshf" styleId="threexnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								����ѧ��ѧ�Ѵ���
							</th>
							<td>
								<html:hidden name="rs" property="fourxnzje" styleId="fourxnzje" />
								<html:text name="rs" property="fourxnxfje" styleId="fourxnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								����ѧ�����ҷѴ���
							</th>
							<td>
								<html:text name="rs" property="fourxnqsfje" styleId="fourxnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								����ѧ������Ѵ���
							</th>
							<td>
								<html:text name="rs" property="fourxnshf" styleId="fourxnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
						</tr>
						<tr>

							<th>
								����ѧ��ѧ�Ѵ���
							</th>
							<td>
								<html:hidden name="rs" property="fivexnzje" styleId="fivexnzje" />
								<html:text name="rs" property="fivexnxfje" styleId="fivexnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								����ѧ�����ҷѴ���
							</th>
							<td>
								<html:text name="rs" property="fivexnqsfje" styleId="fivexnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								����ѧ������Ѵ���
							</th>
							<td>
								<html:text name="rs" property="fivexnshf" styleId="fivexnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								�����ܽ��
							</th>
							<td>
								<html:text name="rs" property="dkzje" styleId="dkzje"
									readonly="true" />
							</td>
						</tr>

					</tbody>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<script type="text/javascript">
						$("btn_bc").disabled="true";
					</script>
				</logic:equal>
			</logic:equal>
		</html:form>
	</body>
</html>

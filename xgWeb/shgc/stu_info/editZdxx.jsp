<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<!-- ͷ�ļ� -->
	<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript">
			function editCommit(){
				refreshForm('/xgxt/archives_deal.do?doType=save');
			}
		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		function changeValue(obj){
			$('hztp').value = obj.value;
		}

		function viewPic(){
			var hztp = $('hztp').value;
			var url = "archives_deal.do?hztp=" + hztp + "&doType=viewhztp";
			window.open(url);
		}
		</script>
</head>
<body>
	<html:form action="/archives_deal" method="post" enctype="multipart/form-data">
		<input type="hidden" name="zdlb" id="zdlb" value="${zdlb}"/>
		<input type="hidden" name="hztp" id="hztp" value="${rs.hztp }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ת������ - �޸�ת����Ϣ</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist" id="rsT">
			<tbody>
				<tr>
					<th>ѧ��</th>
					<td>
						<input type="text" id="xh" name="xh"
							value="${rs.xh}"
							readonly="readonly" />
					</td>
					<th>����</th>
					<td>
						${rs.xm}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
					</td>
					<th>�꼶</th>
					<td>
						${rs.nj}
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.zymc}
					</td>
					<th>�༶</th>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<tr>
					<th>
						<logic:equal value="��ѧ" name="rs" property="zdlb">
								¼ȡѧУ����
							</logic:equal>
						<logic:equal value="תѧ" name="rs" property="zdlb">
								תѧѧУ����
							</logic:equal>
						<logic:equal value="��ѧ" name="rs" property="zdlb">
								��ѧ�ļ���
						</logic:equal>
					</th>
					<td>
						<logic:equal value="��ѧ" name="rs" property="zdlb">
							<html:text name="rs" property="xxmc" styleId="xxmc"
								maxlength="50" />
						</logic:equal>
						<logic:equal value="תѧ" name="rs" property="zdlb">
							<html:text name="rs" property="xxmc" styleId="xxmc"
								maxlength="50" />
						</logic:equal>
						<logic:equal value="��ѧ" name="rs" property="zdlb">
							<html:text name="rs" property="wjh" styleId="wjh" maxlength="50" />
						</logic:equal>
					</td>
					<th>���������ĵ�ַ</th>
					<td>
						<html:text name="rs" property="zddwdz" styleId="zddwdz"
							maxlength="50" />
					</td>
				</tr>
				<tr>
					<th>��λ����</th>
					<td>
						<html:text name="rs" property="zddwmc" styleId="zddwmc"
							maxlength="50" />
					</td>
					<th>�ʱ�</th>
					<td>
						<html:text name="rs" property="zddwyb" styleId="zddwyb"
							maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') " />
					</td>
				</tr>
				<logic:equal value="zx" name="zdlb">
					<tr>
						<th>ʡ������תѧ�ļ���</th>
						<td>
							<html:text name="rs" property="wjh" styleId="wjh" maxlength="50" />
						</td>
						<th></th>
						<td>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<th>����ת��ʱ��</th>
					<td>
						<html:text name="rs" property="dazcsj" styleId="dazcsj"
							readonly="true" maxlength="10"
							onclick="this.value='';return showCalendar('dazcsj','y-mm-dd');"
							onblur="getRqVal('dazcsj');"></html:text>
					</td>
					<th>����ת����ʽ</th>
					<td>
						<html:text name="rs" property="dazcfs" styleId="dazcfs"
							maxlength="30" />
					</td>
				</tr>
				<tr>
					<th>��Ҫ��</th>
					<td>
						<html:text name="rs" property="jyh" styleId="jyh" maxlength="30" />
					</td>
					<th>�Ƿ��л�ִ</th>
					<td>
						<html:select name="rs" property="sfyhz" style="width:80px" styleId="sfyhz">
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��ִͼƬ</th>
					<td colspan="3">
						<html:text name="rs" property="hztp" style="width:60%"
							styleId="hztp" />
						<html:file property="uploadFile" style="width:1px"
							onchange="changeValue(this)"></html:file>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<logic:notEmpty name="rs" property="hztp">
						<a href="#" onclick="viewPic();">�鿴��ִͼƬ</a>
						</logic:notEmpty>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <logic:notEqual value="view" name="doType">
						<button class="button2" onclick="editCommit();">
							�� ��
						</button>
						</logic:notEqual>
						<button class="button2" onclick="Close();return false;">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�");						
					dialogArgumentsQueryChick();
					Close();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��");							
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>

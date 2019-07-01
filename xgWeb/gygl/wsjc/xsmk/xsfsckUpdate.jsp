<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getGyglWsjcDAO.js"></script>
	<script type="text/javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/gygl/xsfslrUpdate.js" defer="defer"></script>
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
				BatAlert.showTips("���ڱ��棬���Ժ�");
				saveData('xysf_dyjsgl.do?method=addDyjs&doType=save','zgh');
			}else{
				alert("�뽫��\"*\"����Ŀ����������");
			}
		}
	</script>
</head>
<body>
	<html:form action="/commWsjc" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
		
		<input type="hidden" name="xh" value="${rs.xh }"/>
		<input type="hidden" name="xn" value="${xn }"/>
		<input type="hidden" name="xq" value="${xq }"/>
		<input type="hidden" name="nd" value="${nd }"/>
		<input type="hidden" name="lrr" value="${userName }"/>
		<input type="hidden" name="lrsj" value="${lrsj }"/>
		<input type="hidden" name="jczq" value="${jczq }"/>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ѧ����Ϣ��������Ϣ</span><p class="floatright normal">ѧ�꣺${xn }&nbsp;&nbsp;&nbsp;&nbsp;��ѧ�ڣ�${xqmc }��&nbsp;&nbsp;</p>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%">
				  	${rs.xh }
				</td>
				<th width="20%">
					����
				</th>
				<td width="30%">
					${rs.xm }
				</td>
			</tr>
			
			<tr>
				<th>
					<bean:message key="lable.xb" />
				</th>
				<td>
					${rs.xymc }
				</td>
				
				<th>
					רҵ
				</th>
				<td>
					${rs.zymc }
				</td>
			</tr>
			
			<tr>
				<th>
					�༶
				</th>
				<td>
					${rs.bjmc }
				</td>
				
				<th>
					¥��
				</th>
				<td>
					${rs.ldmc}
				</td>
				
			</tr>
			<tr>
				<th>
					���Һ�
				</th>
				<td>
					${rs.qsh }
				</td>
				
				<th>
					��λ��
				</th>
				<td>
					${rs.cwh }
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4"><span>���������Ŀ</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
		      <td colspan="4" class="tdbox">
				<table width="100%" class="dateline">
	              <thead>
	                  <tr>
	                  	<logic:equal value="��" name="jczq">
							<td>����ܴ�</td>
						</logic:equal>
						<logic:notEqual value="��" name="jczq">
							<td>���ʱ��</td>
						</logic:notEqual>
						<td>ԭ��</td>
						<td>����</td>
						<td>����</td>
						<td>¼����</td>
					</tr>
				  </thead>
				  <tbody>
				  	<logic:iterate id="fs" name="xsfsInfo">
				  		<tr>
					  		<logic:equal value="��" name="jczq">
								<td>��${fs.jczc }��</td>
							</logic:equal>
							<logic:notEqual value="��" name="jczq">
								<td>${fs.jcsj }</td>
							</logic:notEqual>
				  			<td>${fs.xmmc }</td>
				  			<td>${fs.xmxz }</td>
				  			<td>${fs.fs }</td>
				  			<td>${fs.lrr }</td>
				  		</tr>
				  	</logic:iterate>
				  </tbody>
				</table>
			  </td>
		     </tr>
			</tbody>
			<tfoot>
			  <tr>
		        <td colspan="4">
		          <div class="btn">
					  <button name="�ر�" onclick="window.close();return false;">�ر�</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		
		</div>
	</html:form>
</body>
</html>

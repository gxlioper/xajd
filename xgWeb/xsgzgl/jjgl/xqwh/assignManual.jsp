<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript">
		function assignManualSubmit(){
			if(jQuery('input[name="xh"]').val() == "" || jQuery('input[name="xh"]').val() == null ){
				showAlert("��ѡ��һλ�ҽ���ʦ��");
				return false;
			}
			
			//�ύ
			showConfirmDivLayer("��ȷ��Ҫ�ύ��",{"okFun" : function(){
				var url = "jjgl_xqwhgl.do?method=assignManualSubmit";
				ajaxSubFormWithFun("jjglXqwhForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
			<html:hidden property="xqid" value="${xqwhMap.xqid }"/>
			<div class='tab' style='width:100%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>�ҽ���Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="18%">�ҽ̱��</th>
						<td width="82%" colspan="3">
								${xqwhMap.xqid}
						</td>
					</tr>
					<tr>
						<th width="18%">
							�ҳ�����
						</th>
						<td width="32%">
								${xqwhMap.jzxm}
						</td>
						<th width="18%">
							�Ǽ���
						</th>
						<td width="32%">
								${xqwhMap.djr}
						</td>
					</tr>
					<tr>
						<th width="18%">
							��Ů����
						</th>
						<td width="32%">
								${xqwhMap.znxb}
						</td>
						<th width="18%">
							��Ů�Ա�
						</th>
						<td width="32%">
								${xqwhMap.znxb}
						</td>
					</tr>

					<tr>
						<th width="18%">
							�貹��Ŀ
						</th>
						<td width="32%">
								${xqwhMap.jjxk}
						</td>
						<th width="18%">
							��Ů�꼶
						</th>
						<td width="32%">
								${xqwhMap.jjnj}
						</td>
					</tr>

					<tr>
						<th width="18%">
							�ҽ̵ص�
						</th>
						<td width="32%">
								${xqwhMap.jjdd}
						</td>
						<th width="18%">
							���ʱн
						</th>
						<td width="32%">
								${xqwhMap.jjsx}
						</td>
					</tr>

					<tr>
						<th width="18%">
							��ϵ�绰
						</th>
						<td width="32%" colspan="3">
								${xqwhMap.lxdh}
						</td>
					</tr>

					<tr>
						<th width="18%">
							�ҽ�Ҫ��
						</th>
						<td colspan="3" width="82%">
								${xqwhMap.jjlsyq}
						</td>

					</tr>
					<tr>
						<th width="18%">
							��ע
						</th>
						<td colspan="3" width="82%">
								${xqwhMap.bz}
						</td>

					</tr>
					</tbody>
					
					
					 <thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���ʦ
									<button class="btn_01" type="button"  
										onclick="showDialog('ѡ��һλ�ҽ���ʦ',800,500,'xsxx_xsgl.do?method=showStudentsForJjlsk&goto=${path}');">ѡ��</button>
									<input type="hidden" name="xh" value="${jjlsxxMap.xh}"/>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notPresent name="jjlsxxMap">
							<tr>
						    	<td colspan="4" style="text-align: center">
						    		δѡ��
						    	</td>
						    </tr>
						</logic:notPresent>
						<logic:present name="jjlsxxMap">
							<tr>
					    	<th width="20%">����</th>
					    	<td width="30%">${jjlsxxMap.xm }</td>
					    	<th width="20%">�Ա�</th>
					    	<td width="30%">${jjlsxxMap.xb }</td>
						    </tr>
						    <tr>
						    	<th width="20%">ѧԺ</th>
						    	<td width="30%">${jjlsxxMap.xymc }</td>
						    	<th width="20%">רҵ</th>
						    	<td width="30%">${jjlsxxMap.zymc }</td>
						    </tr>
						    <tr>
						    	<th width="20%">�༶</th>
						    	<td width="30%">${jjlsxxMap.bjmc}</td>
						    	<th width="20%">�꼶</th>
						    	<td width="30%">${jjlsxxMap.nj }</td>
						    </tr>
						     <tr>
						     	<th width="20%">��ϵ�绰</th>
						    	<td width="30%">${jjlsxxMap.lxdh}</td>
						    	<th width="20%">����꼶</th>
						    	<td width="30%">${jjlsxxMap.jjnjmc }</td>
						    </tr>
						    <tr>
						    	<th width="20%">�ó���Ŀ</th>
						    	<td colspan="3">${jjlsxxMap.sckmmcs }</td>
						    </tr>
						</logic:present>
					 </tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btqd" onclick="assignManualSubmit();">
										�ύ
									</button>
									<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
  </body>
</html>

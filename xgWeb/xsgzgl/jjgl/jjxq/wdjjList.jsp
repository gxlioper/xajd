<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�ҵļҽ��б�",
				pager:"pager",
				rowNum:10,
				url:"jjgl_jjxq.do?method=getWdjjList",
				colList:[
				   {label:'ID',name:'sqid', index: 'sqid',hidden:true,key:true},
				   {label:'�ҽ̱��',name:'xqid', index: 'xqid',formatter:xqidLink},
				   {label:'�ҽ��꼶',name:'jjnj', index: 'jjnj'},
				   {label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
				   {label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
				   {label:'�ҽ̿�ʼʱ��',name:'kssj', index: 'kssj'},
				   {label:'�ҽ̽���ʱ��',name:'jssj', index: 'jssj'},
				   {label:'�ҽ�״̬',name:'jjzt', index: 'jjzt',hidden:true},
				   {label:'�ҽ�״̬',name:'jjztmc', index: 'jjzt'},
				   {label:'jjczshzt',name:'jjczshzt', index: 'jjczshzt',hidden:true},
				   {label:'jjczsqid',name:'jjczsqid', index: 'jjczsqid',hidden:true},
				   {label:'tjjcz',name:'tjjcz', index: 'tjjcz',hidden:true},
				   {label:'�ҽ̲���״̬',name:'jjczshztmc', index: 'jjczshzt'}
				]
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function jsjj(){
                //jjczshzt = �ջ�3���˻أ���������
			    //δ��Э���鲻�ܽ����ҽ� �ѽ������رգ��ҽ̲��ܽ����ҽ�
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
                    return false;
                }
                if(rows[0]["jjzt"] == '4'){
                    showAlertDivLayer("�ҽ��ѽ�����");
                    return false;
                }
                //�в���������в���������
				if(rows[0]["jjczshzt"] == '5'){
                    showAlertDivLayer("�в�����������У��ݲ��ܽ��иò�����");
                    return false;
				}
                //��ͬ�Ĳ���ͨ����ͨ��������������
                if(rows[0]["tjjcz"] == '4'&&(rows[0]["jjczshzt"] == '1' || rows[0]["jjczshzt"] == '2')){
                    showAlertDivLayer("�ò�������ɣ������ٴβ�����");
                    return false;
                }
                //���˼ҽ̲���ִ�йرղ���
                if(rows[0]["tjjcz"] == '0'&&(rows[0]["jjczshzt"] == '1')){
                    showAlertDivLayer("���˼ҽ̣�����ִ�иò�����");
                    return false;
                }


				showDialog("�����ҽ�",700,480,"jjgl_jjxq.do?method=jsjj&jjcz=4&xqid="+rows[0]["xqid"]+"&sqid="+rows[0]["sqid"]);
			}

            function tjj(){
			    //�ѽ�Э���鲻���˼ҽ� �ѽ������رգ��ҽ̲��ܽ����ҽ�
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
                    return false;
                }
                if(rows[0]["jjzt"] == '4'){
                    showAlertDivLayer("�ҽ��ѽ�����");
                    return false;
                }
                if(rows[0]["jjzt"] == '3'){
                    showAlertDivLayer("�ѽ�Э���鲻���˼ҽ̣�");
                    return false;
                }
                //�в���������в���������
                if(rows[0]["jjczshzt"] == '5'){
                    showAlertDivLayer("�в�����������У��ݲ��ܽ��иò�����");
                    return false;
                }
                //��ͬ�Ĳ���ͨ����ͨ��������������
                if(rows[0]["tjjcz"] == '0'&&(rows[0]["jjczshzt"] == '1' || rows[0]["jjczshzt"] == '2')){
                    showAlertDivLayer("�ò�������ɣ������ٴβ�����");
                    return false;
                }

                showDialog("�˼ҽ�",700,480,"jjgl_jjxq.do?method=jsjj&jjcz=0&xqid="+rows[0]["xqid"]+"&sqid="+rows[0]["sqid"]);
            }
			
			function ckjj(sqid){
				showDialog("�鿴",500,350,"jjgl_jjxq.do?method=cksq&sqid="+sqid);
			}
			
			function searchRs(){
				var map = {};
				map["jjxk"] = jQuery("#jjxk").val();
				map["jjnj"] = jQuery("#jjnj").val();
				map["jjzt"] = jQuery("#jjzt").val();
				jQuery("#dataTable").reloadGrid(map);
			}

            function xqidLink(v,r){

                return "<a class='name' href='javascript:void(0);' onclick='ck("+v+")'>"+v+"</a>";
            }

            function ck(value){

                var url = "jjgl_xqwhgl.do?method=ck&xqid="+value;
                var title = "�鿴������Ϣ";
                showDialog(title,800,400,url);
            }

            /**
			 * �ҽ�ʱ��ά��
             */
            function jjscwh() {

                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
                    return false;
                }
                showDialog("�ҽ�ʱ��ά��",560,450,"jjgl_jjxq.do?method=jjgswh&xqid="+rows[0]["xqid"]);
            }

            /**
			 * �ҽ�����
			 * �Ǽҽ���ʦ�Լҳ�������
             */
            function jjpj() {

                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
                    return false;
                }
                showDialog("�ҽ�����",560,300,"jjgl_jjxq.do?method=jjpj&xqid="+rows[0]["xqid"]);
            }
		</script>
	</head>

	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
	
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="jsjj();return false;"  class="btn_xg">�����ҽ�</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="tjj();return false;"  class="btn_xg">�˼ҽ�</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="jjscwh();return false;"  class="btn_xg">�ҽ�ʱ��ά��</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="jjpj();return false;"  class="btn_xg">�ҽ�����</a>
					</li>
				</ul>
			</div>
			<!-- �������� -->
			<div class="searchtab">
				<html:form action="/jjgl_jjxq" method="post" >
					<table width="100%" border="0">
						<tr>
							<th width="10%">�ҽ̿�Ŀ</th>
							<td>
								<html:select property="jjxk" styleId="jjxk" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxk" labelProperty="jjxk"/>
					    		</html:select>
							</td>
							<th width="10%">�꼶</th>
							<td>
								<html:select property="jjnj" styleId="jjnj" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnj" labelProperty="jjnj"/>
					    		</html:select>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>					
					</table>
				</html:form>
			</div>
		</div>
	
		<div>
			<h3 class="datetitle_01">
				<span>�ҵļҽ��б�</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>

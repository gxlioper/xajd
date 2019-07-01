<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"�ҽ�ע���û��б�",
            pager:"pager",
            multiselect:false,
            radioselect:true,
            rowNum:20,
            url:"jjgl_zcyhgl.do?method=queryZcyhList&type=w",
            colList:[
                {label:'�û���',name:'yhm', index: 'yhm',key:true},
                {label:'����',name:'xm', index: 'xm'},
                {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
                {label:'��ͥסַ',name:'jtzz', index: 'jtzz'},
                {label:'ע��ʱ��',name:'zcsj', index: 'zcsj'},
                {label:'����',name:'yhm', index: 'yhm',formatter:czLink}
            ]
        };

        function searchRs(){
            var map = {};
            map["yhm"] = jQuery("#yhm").val();
            map["xm"] = jQuery("#xm").val();
            jQuery("#dataTable").reloadGrid(map);
        }

        /**
         *��ʼ��
         */

        jQuery(function(){
            jQuery("#dataTable").initGrid(gridSetting);
        });

        /**
         *���¼�������
         */
        function reloadWindow(){
            jQuery("#dataTable").reloadGrid();
        }

        function czLink(cellValue,rowObject){
            var xm = rowObject["xm"];
            var lxdh = rowObject["lxdh"];
            return "<button class='btn_01' onclick=\"selectJzxx('"+cellValue+"','"+xm+"','"+lxdh+"')\" class='name'>"+"ѡ��"+"</button>";
        }

        function selectJzxx(yhm,xm,lxdh) {
            var api = frameElement.api;
            var wd = api.get('parentDialog').document;

            //��ѯ�ҳ�����Ů�����Ϣ�����Ե���ҳ��
            jQuery.post("jjgl_zcyhgl.do?method=getJzxxByYhm",{yhm:yhm},function (data) {
                jQuery("#sqr",wd).val(yhm);
                jQuery("#jzxm",wd).val(xm);
                jQuery("#lxdh",wd).text(lxdh);

                var html = "<option value=''></option>";
                if(data != null){
                    for(i=0;i<data.length;i++){
                        var jzxx = data[i];
                        html += "<option value="+data[i]["znid"]+" znxb="+data[i]["xb"]+">"+data[i]["xm"]+"</option>"
                    }
                }

                jQuery("#znid",wd).empty().append(html);
                jQuery("#znxb",wd).empty();
                api.close();

            },"JSON");
        }
    </script>
</head>
<body>
<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>

<div class="toolbox">
    <!-- �������� -->
    <div class="searchtab">
        <html:form action="/jjgl_zcyhgl" method="post" >

            <table width="100%" border="0">
                <tr>
                    <th width="10%">�û���</th>
                    <td>
                        <html:text property="yhm" styleId="yhm" ></html:text>
                    </td>
                    <th width="10%">����</th>
                    <td>
                        <html:text property="xm" styleId="xm" ></html:text>
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
<div class="formbox">
    <div>
        <h3 class="datetitle_01">
					<span id="listName">
						�ҳ���Ϣ�б�
					</span>
        </h3>
    </div>
    <table id="dataTable"></table>
</div>
<div id="pager"></div>

</body>
</html>

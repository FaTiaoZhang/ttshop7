<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--DOM结构--%>
<a id="import" class="easyui-linkbutton">一键导入商品数据到索引库</a>
<span id="importmsg"></span>

<%--js代码--%>
<script>
    $(function(){
        $("#import").click(function(){
            //点击之后不可用
            $('#import').linkbutton('disable');
            $('#importmsg').html('正在导入，请稍后！')
            $.post(
                "item/search/import",
                function (data) {
                    console.log(typeof data);
                    if (data.success){
                        $.messager.alert('温馨提示',data.message);
                    }else{
                        $.messager.alert('温馨提示','导入失败！请刷新后再尝试！');
                    }
                    $('#importmsg').html('')
                }
            );
        });
    });
</script>

<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=gbk" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<%
    String path = request.getContextPath();
    // �����Ŀ��ȫ·�������������Ŀ��MyApp����ô��õ��ĵ�ַ����http://localhost:8080/MyApp/��:
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <!-- base��Ҫ�ŵ�head�� -->
    <base href=" <%=basePath%>">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>΢��</title>
</head>
<body>
<script type="application/javascript">
    window.location.href = "index/to_index";
</script>
</body>
</html>

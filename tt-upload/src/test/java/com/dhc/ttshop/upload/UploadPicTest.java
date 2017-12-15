package com.dhc.ttshop.upload;

/**
 * User: DHC
 * Date: 2017/11/29
 * Time: 9:51
 * Version:V1.0
 */
//public class UploadPicTest {
//    @Test
//    public void testFTPClient() throws Exception {
//        //创建FTP客户端
//        FTPClient ftpClient = new FTPClient();
//        //创建连接 等价于ftp 10.31.164.63
//        ftpClient.connect("10.31.164.63",21);
//        //登录
//        ftpClient.login("ftpuser1", "dhc890dhc");
//        //文件输入流
//        FileInputStream fis = new FileInputStream(new File("d:\\87890.jpg"));
//        //设置图片上传参数(1 设置上传后的位置 2 类型：二进制)
//        ftpClient.changeWorkingDirectory("/home/ftpuser1/www/images");
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//        //上传文件
//        boolean bl = ftpClient.storeFile("hello.jpg", fis);
//        System.out.println(bl);
//        //关闭资源
//        fis.close();
//        ftpClient.logout();
//    }
//    @Test
//    public void testFTPUtils() throws Exception {
//        FileInputStream fis = new FileInputStream(new File("d:\\87890.jpg"));
//        boolean bool = FtpUtils.uploadFile("10.31.164.63", 21, "ftpuser1", "dhc890dhc", "/home/ftpuser1/www/images", "/2017/11/29", "hello2.jpg", fis);
//        System.out.println(bool);
//    }
//
//}

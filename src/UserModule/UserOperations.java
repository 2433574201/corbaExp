package UserModule;


/**
* UserModule/UserOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从D:/javaworkStation/corbaExp/src/idl/user.idl
* 2019年6月9日 星期日 上午11时59分51秒 GMT+08:00
*/

public interface UserOperations 
{
  boolean add (String startTime, String endTime, String label);
  String query (String startTime, String endTime);
  boolean delete (String key);
  boolean clear ();
  String show ();
} // interface UserOperations

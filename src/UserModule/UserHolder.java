package UserModule;

/**
* UserModule/UserHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从D:/javaworkStation/corbaExp/src/idl/user.idl
* 2019年6月9日 星期日 上午11时59分51秒 GMT+08:00
*/

public final class UserHolder implements org.omg.CORBA.portable.Streamable
{
  public UserModule.User value = null;

  public UserHolder ()
  {
  }

  public UserHolder (UserModule.User initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = UserModule.UserHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    UserModule.UserHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return UserModule.UserHelper.type ();
  }

}

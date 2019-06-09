package CreatorModule;

/**
* CreatorModule/CreatorHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从D:/javaworkStation/corbaExp/src/idl/creator.idl
* 2019年6月9日 星期日 上午11时58分39秒 GMT+08:00
*/

public final class CreatorHolder implements org.omg.CORBA.portable.Streamable
{
  public CreatorModule.Creator value = null;

  public CreatorHolder ()
  {
  }

  public CreatorHolder (CreatorModule.Creator initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CreatorModule.CreatorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CreatorModule.CreatorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CreatorModule.CreatorHelper.type ();
  }

}

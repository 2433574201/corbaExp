package CreatorModule;


/**
* CreatorModule/CreatorOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��D:/javaworkStation/corbaExp/src/idl/creator.idl
* 2019��6��9�� ������ ����11ʱ58��39�� GMT+08:00
*/

public interface CreatorOperations 
{
  boolean login (String name, String password);
  boolean register (String name, String password);
} // interface CreatorOperations

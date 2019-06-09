package UserModule;


import CreatorModule.CreatorPOA;
import model.Person;
import org.omg.CORBA.ORB;
import server.ToDoListServer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
* UserModule/_UserStub.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��D:/javaworkStation/corbaExp/src/idl/user.idl
* 2019��6��9�� ������ ����11ʱ59��51�� GMT+08:00
*/

public class _UserStub extends org.omg.CORBA.portable.ObjectImpl implements UserModule.User
{

  public boolean add (String startTime, String endTime, String label)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add", true);
                $out.write_string (startTime);
                $out.write_string (endTime);
                $out.write_string (label);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return add (startTime, endTime, label        );
            } finally {
                _releaseReply ($in);
            }
  } // add

  public String query (String startTime, String endTime)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("query", true);
                $out.write_string (startTime);
                $out.write_string (endTime);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return query (startTime, endTime        );
            } finally {
                _releaseReply ($in);
            }
  } // query

  public boolean delete (String key)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("delete", true);
                $out.write_string (key);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return delete (key        );
            } finally {
                _releaseReply ($in);
            }
  } // delete

  public boolean clear ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("clear", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return clear (        );
            } finally {
                _releaseReply ($in);
            }
  } // clear

  public String show ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("show", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return show (        );
            } finally {
                _releaseReply ($in);
            }
  } // show

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:UserModule/User:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }

    /**
     * Created by huxijie on 17-5-3.
     * 实现用户管理creator.idl中定义的接口
     */
    public static class CreatorImpl extends CreatorPOA {
        private Map<String, Person> personsMap;
        private String name = null;
        private ORB orb;
        private ToDoListServer toDoListServer;
        private UserImpl userImpl;

        public CreatorImpl() {
            init();
        }

        //初始化
        private void init() {
            //从文件中读取用户列表,转化为HashMap
            try {
                FileInputStream fin = new FileInputStream("person.file");
                ObjectInputStream oin = new ObjectInputStream(fin);
                try {
                    Object object = oin.readObject();
                    personsMap = (HashMap<String, Person>) object;
                } catch (ClassNotFoundException e) {
                    System.out.println("object cast error");
                    personsMap = new HashMap<String, Person>();
                }
                oin.close();
                fin.close();
            } catch (Exception e) {
                personsMap = new HashMap<String, Person>();
            }
        }

        //将用户表保存到本地文件中
        private void saveData() {
            try {
                FileOutputStream fout = new FileOutputStream("person.file");
                ObjectOutputStream oout = new ObjectOutputStream(fout);
                oout.writeObject(personsMap);
                oout.flush();
                fout.flush();
                oout.close();
                fout.close();
            } catch (Exception e) {
                System.out.println("save error.");
                e.printStackTrace();
            }
        }

        public void setORB(ORB orb) {
            this.orb = orb;
        }

        public void setToDoListServer(ToDoListServer toDoListServer) {
            this.toDoListServer = toDoListServer;
        }

        //对用户名进行注册服务
        private void registerService(String name) {
            toDoListServer.registerUserName(name);
        }

        @Override
        public boolean login(String name, String password) {
            Person p = personsMap.get(name);
            if (p != null && p.getPassword().equals(password)) {
                this.name = name;

                //登录成功后对用户名进行注册服务
                registerService(name);

                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean register(String name, String password) {
            Person person = personsMap.get(name);
            if (person != null) {   //表中用户名为name的已存在
                return false;
            } else {
                personsMap.put(name, new Person(name, password));
                saveData();
                return true;
            }
        }
    }
} // class _UserStub

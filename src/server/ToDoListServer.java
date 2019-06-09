package server;

import CreatorModule.Creator;
import CreatorModule.CreatorHelper;
import UserModule.User;
import UserModule.UserHelper;
import UserModule.UserImpl;
import UserModule._UserStub;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;


public class ToDoListServer {
    private ORB orb;
    private POA rootPOA;
    private org.omg.CORBA.Object obj;
    private _UserStub.CreatorImpl creatorImpl;
    private UserImpl userImpl;
    private org.omg.CORBA.Object ref;
    private Creator creatorhref;
    private User userhref;
    private org.omg.CORBA.Object objRef;
    private NamingContextExt ncRef;

    public static void main(String[] args) {
        ToDoListServer toDoListServer = new ToDoListServer();
        toDoListServer.init();
    }

    //初始化,注册Creator到服务中
    private void init() {
        try {
            String[] args = {};
            Properties properties = new Properties();
            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            properties.put("org.omg.CORBA.ORBInitialPort", "8080");


            orb = ORB.init(args, properties);

            obj = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(obj);
            rootPOA.the_POAManager().activate();


            creatorImpl = new _UserStub.CreatorImpl();
            creatorImpl.setToDoListServer(this);


            ref = rootPOA.servant_to_reference(creatorImpl);
            creatorhref = CreatorHelper.narrow(ref);


            objRef = orb.resolve_initial_references("NameService");
            ncRef = NamingContextExtHelper.narrow(objRef);


            String name = "Creator";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, creatorhref);

            System.out.println("server is running....");

            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //对用户进行注册服务
    public void registerUserName(String name) {
        try {

            userImpl = new UserImpl(name);
            userImpl.setORB(orb);

            //从服务中得到对象的引用,并注册到服务中
            ref = rootPOA.servant_to_reference(userImpl);
            userhref = UserHelper.narrow(ref);

            //在命名上下文中绑定这个对象
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, userhref);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package cherry.demo.main;

import cherry.DefaultPluginFactory;
import cherry.PluginFactory;
import cherry.SingleJarPluginWrapper;
import cherry.demo.api.HelloService;
import cherry.demo.api.UserService;
import cherry.demo.api.model.User;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        DefaultPluginFactory pluginFactory = new DefaultPluginFactory("classpath:plugins.xml");
        //PluginFactory pluginFactory = new DefaultPluginFactory(new File("/home/plugins.xml"));

        HelloService helloService1 = (HelloService) pluginFactory.getPlugin("helloService");

        SingleJarPluginWrapper wrapper = new SingleJarPluginWrapper("helloService", "E:\\plugins\\lib\\cherry-demo-plugin2-1.0.0-SNAPSHOT.jar", pluginFactory);
        HelloService helloService = (HelloService) wrapper.instance();
        System.out.println(helloService);

        helloService.hello("cherry");
        helloService.echo("cherry");

        helloService1.hello("cherry");
        helloService1.echo("cherry");

//        UserService userService = pluginFactory.getPlugin(UserService.class);
//        System.out.println(userService);
//
//        User user = userService.getUser("cherry");
//        System.out.println(user);
//
//        List<User> list = userService.getUsers();
//        System.out.println(list);

        //关闭
        pluginFactory.close();
        wrapper.destroy();
    }
}

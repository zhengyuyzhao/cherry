package cherry.demo.plugin;

import cherry.config.PluginConfig;
import cherry.demo.api.HelloService;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 */
public class HelloServiceImpl implements HelloService {
    private PluginConfig config;

    @Override
    public String echo(String msg) {
        System.out.println("222--echo [" + msg + "]");
        this.config.getPluginContext().setAttribute("echo", msg);
        return "222--echo [" + msg + "]";
    }

    @Override
    public void hello(String msg) {
        System.out.println("222--hello "+msg);
        System.out.println("222--encoding="+config.getInitParameter("encoding"));
        this.config.getPluginContext().setAttribute("hello", msg);
        System.out.println("222--attr:"+this.config.getPluginContext().getAttribute("hello"));
    }

    @Override
    public void init(PluginConfig config) {
        this.config = config;
        System.out.println("222--HelloServiceImpl init...");
    }

    @Override
    public void destroy() {
        System.out.println("222--HelloServiceImpl destroy...");
    }
}

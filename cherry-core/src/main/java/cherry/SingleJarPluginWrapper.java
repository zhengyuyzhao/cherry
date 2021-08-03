package cherry;

import cherry.config.DefaultPluginConfig;
import cherry.config.PluginConfig;
import cherry.config.PluginDefinition;
import cherry.exception.PluginException;

import java.io.File;
import java.util.Set;

public  class SingleJarPluginWrapper<T extends Plugin> implements Plugin{
    private volatile PluginJarClassLoader classLoader = null;
    private String jarPath;
    private volatile T origin;
    private String name;
    private PluginFactory pluginFactory;


    public SingleJarPluginWrapper(String name, String jarPath, PluginFactory factory){
        this.jarPath = jarPath;
        this.name = name;
        this.pluginFactory = factory;
        this.classLoader = new PluginJarClassLoader(new File(jarPath), factory.getClassLoader().getParent());
    }

    public T instance() {
        if(origin != null){
            return origin;
        }
        synchronized (this) {
            try {
                this.origin = (T)classLoader.loadClass(this.pluginFactory.getPluginDefinition(name).getClazz()).newInstance();
                this.origin.init(new DefaultPluginConfig(pluginFactory.getPluginDefinition(name), pluginFactory.getPluginContext()));
            } catch (Exception e) {
               throw new PluginException(e);
            }
        }
        return origin;
    }

    @Override
    public void init(PluginConfig config) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        origin.init(config);
    }

    @Override
    public void destroy() {
        origin.destroy();
    }
}

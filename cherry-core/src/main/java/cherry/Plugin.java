package cherry;

import cherry.config.PluginConfig;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 */
public interface Plugin {

    void init(PluginConfig config) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    void destroy();
}

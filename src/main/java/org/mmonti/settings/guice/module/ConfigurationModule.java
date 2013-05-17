package org.mmonti.settings.guice.module;

import com.google.inject.AbstractModule;
import org.mmonti.settings.Settings;
import org.mmonti.settings.SettingsBuilder;
import org.mmonti.settings.guice.ConfigurationContributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Module responsible to bind configuration contributors.
 *
 * @author: monti.mauro
 */
public class ConfigurationModule extends AbstractModule {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationModule.class);

    private SettingsBuilder settingsBuilder = null;
    private Settings settings = null;
    private List<ConfigurationContributor> contributors = null;

    /**
     *
     * @param settingsBuilder
     */
    public ConfigurationModule(final SettingsBuilder settingsBuilder) {
        this.settingsBuilder = settingsBuilder;
        this.contributors = new ArrayList<>();
    }

    @Override
    protected void configure() {
        if (settings != null) {
            logger.debug("Contributors already bind.");
            return;
        }

        logger.debug("building settings");
        settings = settingsBuilder.build();

        logger.debug("binding contributors");
        for (final ConfigurationContributor contributor : contributors) {
            contributor.bind(binder(), settings);
        }
    }

    /**
     *
     * @param configurationContributor
     * @throws java.io.IOException
     */
    public void addContributor(final ConfigurationContributor configurationContributor) throws IOException {
        configurationContributor.contribute(settingsBuilder);

        logger.debug("registering contributor=[{}]", configurationContributor.getClass().getCanonicalName());
        this.contributors.add(configurationContributor);
    }

    /**
     *
     * @return
     */
    public Settings getSettings() {
        return settings;
    }
}
package org.mmonti.settings.guice;

import com.google.inject.Binder;
import org.mmonti.settings.Settings;
import org.mmonti.settings.SettingsBuilder;

import java.io.IOException;

/**
 * @author mauro.monti
 */
public interface ConfigurationContributor {

    /**
     *
     * @param settingsBuilder
     * @throws java.io.IOException
     */
    void contribute(final SettingsBuilder settingsBuilder) throws IOException;

    /**
     *
     * @param binder
     */
    void bind(final Binder binder, final Settings settings);
}
